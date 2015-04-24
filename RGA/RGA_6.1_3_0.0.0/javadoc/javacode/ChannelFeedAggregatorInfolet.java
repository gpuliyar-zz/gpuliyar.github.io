package com.metricstream.systemi.ext.infolet;

/**
 * Class: EmailFeedAggregator
 * Author: B.Vasant
 * Description:
 * This class, when invoked completely reads a given set of POP3 server and returns
 * a set of mail records from all of them.
 */
// Generic imports
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.metricstream.log.Logger;
import com.metricstream.systemi.ext.sc.SCInfolet;
import com.metricstream.systemi.rga.dao.DBInstance;
import com.metricstream.systemi.rga.dao.DBManager;
import com.metricstream.systemi.rga.domain.FeedInfo;
import com.metricstream.systemi.rga.domain.SourceInfo;
import com.metricstream.systemi.utils.Utils;

/**
 * This class reads one or more input channels (email, rss, etc), and aggregates the results. The list of channels to get data from is determined by looking up the channel definition tables. The output is stored in the alerts table.
 * <p>
 */
public class ChannelFeedAggregatorInfolet {
	/** The Constant CLASS_ID. */
	public static final String CLASS_ID = "ChannelFeedAggregatorInfolet";
	/** The Constant ft1. */
	public static final SimpleDateFormat ft1 = new SimpleDateFormat("dd-MMM-yy");
	/** The socket. */
	private static ServerSocket socket;
	/** The grci properties. */
	public Properties grciProperties;
	/** The Constant STATUS_FAILURE. */
	private static final String STATUS_FAILURE = "FAILURE";
	// Make this a synchronized collection since multiple threads update this.
	/** The updated feed timestamp. */
	Map<String, String> updatedFeedTimestamp = Collections.synchronizedMap(new HashMap<String, String>());

	/**
	 * Instantiates a new channel feed aggregator infolet.
	 */
	public ChannelFeedAggregatorInfolet() {
		grciProperties = DBInstance.getInstance().getDbInstance().getAllProperties();
	}

	/**
	 * Gets the latest feed time stmps.
	 * 
	 * @return the latest feed time stmps
	 */
	public Map<String, String> getLatestFeedTStmps() {
		return updatedFeedTimestamp;
	}

	/**
	 * Parses the given feed.
	 * 
	 * @param feedDetails
	 *            the feed details
	 * @param feedType
	 *            the feed type
	 * @return the feed info
	 */
	private FeedInfo parseFeed(SourceInfo feedDetails, String feedType) {
		Logger.info(CLASS_ID, "Parsing Email Feed = " + feedDetails.getServerURL(), null);
		FeedInfo info = new FeedInfo();
		info.setUrl(feedDetails.getServerURL());
		info.setUserName(feedDetails.getUserName());
		info.setPassword(feedDetails.getPassword());
		info.setParameter(feedDetails.getParameters());
		info.setSCInfoletID(feedDetails.getStructureInfolet());
		info.setSaveAttachment(feedDetails.getSaveAttachments());
		info.setChannelId(feedDetails.getChannelId());
		info.setSourceId(feedDetails.getSrcPk());
		info.setEmailType(feedDetails.getEmailType());
		if (feedType.equalsIgnoreCase("email")) {
			info.setType("email");
			String userName = FeedAggregator.getOnlyUserName(info.getUserName()) + "@" + info.getUrl();
			Logger.info("ChannelFeed", "email updatedFeedTimestamp.get(info.getUrl()) " + updatedFeedTimestamp.get(userName), null);
			if (updatedFeedTimestamp.containsKey(userName)) {
				info.setUpdatedFeedTimeStamp(updatedFeedTimestamp.get(userName));
			} else {
				updatedFeedTimestamp.put(userName, "" + getDateToFetchDataFrom().getTime());
				info.setUpdatedFeedTimeStamp("" + getDateToFetchDataFrom().getTime());
			}
		} else if (feedType.equalsIgnoreCase("rss")) {
			info.setType("rss");
			Logger.info("ChannelFeed", "rss updatedFeedTimestamp.get(info.getUrl()) " + updatedFeedTimestamp.get(info.getUrl()), null);
			if (updatedFeedTimestamp.get(info.getUrl()) == null) {
				info.setUpdatedFeedTimeStamp("" + getDateToFetchDataFrom().getTime());
			} else {
				info.setUpdatedFeedTimeStamp(updatedFeedTimestamp.get(info.getUrl()));
			}
		} else if (feedType.equalsIgnoreCase("amq")) {
			info.setType("amq");
			String queueName = (String) feedDetails.getQueueName();
			String queueURL = (String) feedDetails.getQueueURL();
			info.setQueueURL(queueURL + queueName);
		}
		Logger.debug(CLASS_ID, "params[0]-URL = " + info.getUrl() + "; params[3]-Parameters = " + info.getParameter() + "; params[4]-lastTimeStamp = " + info.getUpdatedFeedTimeStamp() + "; params[5]-SC InfoletID = " + info.getSCInfoletID() + "; params[6]-save Attchmnts = " + info.getSaveAttachment() + "; params[7]-ChannelID = " + info.getChannelId(), null);
		return info;
	}

	/**
	 * Gets the feeds list.
	 * 
	 * @param channelFeeds
	 *            the channel feeds
	 * @param rssList
	 *            the rss list
	 * @param emailList
	 *            the email list
	 * @param amqList
	 *            the amq list
	 * @return the feeds list
	 */
	private void getFeedsList(List<SourceInfo> channelFeeds, List<FeedInfo> rssList, List<FeedInfo> emailList, List<FeedInfo> amqList) {
		// String params[], rssParams[], amqParams[];
		int mailSrcCnt = 0, rssSrcCnt = 0, amqSrcCnt = 0;
		// for (int i = 0; i < channelFeeds.length; i++) {
		int i = 0;
		for (SourceInfo cf : channelFeeds) {
			// Make sure we don't pull from same channels again
			Logger.debug(CLASS_ID, "i, source " + i + ", " + cf.getSourceType(), null);
			if (cf.getSourceType().toLowerCase().equals("email")) {
				// Create the channel feed definition
				Logger.info(CLASS_ID, "Parsing Email Feed = " + cf.getServerURL(), null);
				emailList.add(mailSrcCnt++, parseFeed(cf, "email"));
				// Add to list of email servers
			} else if (cf.getSourceType().toLowerCase().equals("rss")) {
				Logger.info(CLASS_ID, "Parsing RSS Feed = " + (String) cf.getServerURL(), null);
				rssList.add(rssSrcCnt++, parseFeed(cf, "rss"));
			} else if (cf.getSourceType().toLowerCase().equals("amq")) { // Feed provided by GRCIntelligence source.
				Logger.info(CLASS_ID, "Parsing AMQ Feed = " + cf.getServerURL(), null);
				amqList.add(amqSrcCnt++, parseFeed(cf, "amq"));
			}
			Logger.debug(CLASS_ID, "Successfully stored feeds for processing", null);
		}
	}

	/**
	 * Gets the date to fetch data from.
	 * 
	 * @return the date to fetch data from
	 */
	private Date getDateToFetchDataFrom() {
		// Extract 30 days prior emails
		int dateFrom = -1;
		if (null != grciProperties.getProperty("Days_From")) {
			dateFrom = Integer.parseInt(grciProperties.getProperty("Days_From"));
		}
		Date today = new Date();
		Calendar cal = new GregorianCalendar();
		cal.setTime(today);
		Logger.debug(CLASS_ID, "getDateToFetchDataFrom Date Value: " + dateFrom, null);
		if (dateFrom > 0) {
			dateFrom *= -1;
			cal.add(Calendar.DAY_OF_MONTH, dateFrom);
		} else {
			cal.add(Calendar.DAY_OF_MONTH, -30); // To avoid negative values added this check.
		}
		return cal.getTime();
	}

	/**
	 * Aggregate all the feeds and all sources in the feed.
	 * 
	 * @return Success : Received <i>n</i> new updates/ Failed / Content refresh already running or No Result
	 */
	public synchronized String aggregateNPopulateFeeds() {
		return aggregateNPopulateFeeds(null); //
	}

	/**
	 * Process all the channel feeds.
	 * 
	 * @return an array of arrays where each array element contains the following data elements Time (Timestamp when feed was received) Server (Email server or RSS Server) Sender (Email address or RSS Server) Recipient (The email account that received the message OR empty for RSS server) Subject Body Attachment IDs (Comma-Separated List)
	 */
	public synchronized Object[][] processChannelFeeds() {
		ArrayList<ChannelFeedData> channelData = aggregateFeeds();
		Object[][] feedResults = FeedAggregator.getResults(channelData); // this returns you a object[][]
		if (feedResults != null) {
			Logger.debug(CLASS_ID, "Merging both email and rss results.", null);
			return mergeResults(feedResults);
		}
		return new String[0][0];
	}

	/**
	 * Aggregate all feeds/ sources from all channels.
	 * 
	 * @return a list of aggregated updates on the listed feeds
	 */
	public synchronized ArrayList<ChannelFeedData> aggregateFeeds() {
		// By default load all source ids of all channels
		return aggregateFeeds(null);
	}

	/**
	 * Aggregate only a subset of Sources as listed in the Source ID list.
	 * 
	 * @param srcIDs
	 *            subset of Sources seperated by ',' or ';'
	 * @return a list of aggregated updates on the listed feeds
	 */
	public synchronized ArrayList<ChannelFeedData> aggregateFeeds(String srcIDs) {
		/*
		 * Tomcat in ECP 6.1 doesn't seem to set appropriate class loader which is affecting following 1. RSS feed loading through RSS 2. Email Body extraction from a message The following fixes both these issues
		 */
		Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());
		Logger.debug(CLASS_ID, "entry  ChannelFeedAggregatorInfolet::aggregateFeeds() ", null);
		/*
		 * Implementing Aggregator level Lock to avoid duplicate alerts. AGGREGATOR_LOCK = 0, Unlocked AGGREGATOR_LOCK = 1, Locked
		 */
		if (isFreshInvoke()) {
			try {
				 DBInstance.getInstance().getDbInstance().loadFeedTimestampsHashMap(updatedFeedTimestamp); // Load persisted timeStamps for each Feed
				 DBInstance.getInstance().getDbInstance().updateTStamps(getLatestFeedTStmps());
				List<SourceInfo> channelFeeds =  DBInstance.getInstance().getDbInstance().loadChannelFeeds(srcIDs);
				// Create the list of channel definitions
				if ((channelFeeds == null) || (channelFeeds.size() == 0)) {
					// No channel feeds
					Logger.debug(CLASS_ID, "Found zero channel feeds, returning ...", null);
					closeSocket();
					return null;
				}
				// Create the channel feeds
				List<FeedInfo> rssList = new ArrayList<FeedInfo>();
				List<FeedInfo> emailList = new ArrayList<FeedInfo>();
				List<FeedInfo> amqList = new ArrayList<FeedInfo>();
				getFeedsList(channelFeeds, rssList, emailList, amqList); // Seggregate the list of feeds based on the feed type
				List<FeedXtraxnStats> statsList = new LinkedList<FeedXtraxnStats>();
				List<Feed> mailFeeds = null, rssFeeds = null, amqFeeds = null;
				;
				EmailFeedAggregatorInfolet agg = new EmailFeedAggregatorInfolet(updatedFeedTimestamp);
				RSSFeedAggregatorInfolet rssAgg = new RSSFeedAggregatorInfolet(updatedFeedTimestamp);
				AmqFeedAggregatorInfolet amqAgg = new AmqFeedAggregatorInfolet(updatedFeedTimestamp); // this is to fetch data from ActiveMQ
				if (emailList.size() != 0) {
					mailFeeds = agg.getFeeds(emailList);
				}
				if (rssList.size() != 0) {
					rssFeeds = rssAgg.getFeeds(rssList);
				}
				// get feeds through ActiveMQ as GRCIntelligence source.
				if (amqList.size() != 0) {
					amqFeeds = amqAgg.getFeeds(amqList);
				}
				List<Feed> feeds = new ArrayList<Feed>();
				if (null != mailFeeds)
					feeds.addAll(mailFeeds);
				if (null != rssFeeds)
					feeds.addAll(rssFeeds);
				if (null != amqFeeds)
					feeds.addAll(amqFeeds);
				ArrayList<ChannelFeedData> feedResults = agg.getUpdatesFromFeeds(feeds, statsList);
				Utils.writeToLog(statsList, true);
				Logger.info(CLASS_ID, "Finished Feed aggregation and Unlocking the Aggregator", null);
				if (feedResults != null) {
					invokeInfolets(feedResults);
					closeSocket();
					return feedResults;
				}
			} catch (Exception ex) {
				Logger.error(CLASS_ID, "Encountered Error while processing channel feeds" + ex.getMessage(), ex);
			} finally {
				closeSocket();
				// Update the timestamps for both RSS & Emails
				// Ideally could have called from try and catch but to ensure
				// this happens, calling from finally
				try {
					DBManager.getInstance().updateTStamps(getLatestFeedTStmps());
				} catch (Exception ex) {
					Logger.error(CLASS_ID, "Error Updating TimeStamp : " + ex.getMessage(), ex);
				}
			}
		} else {
			Logger.error(CLASS_ID, "Aggregator has been Locked by someone. hence returning........", null);
		}
		return null; // new String[0][0];
	}

	/**
	 * Close socket.
	 */
	private void closeSocket() {
		if (socket != null) {
			try {
				socket.close();
			} catch (IOException ex) {
				Logger.error(CLASS_ID, "Error Updating TimeStamp : " + ex.getMessage(), ex);
			}
		}
	}

	/**
	 * Checks if is fresh invoke.
	 * 
	 * @return true, if is fresh invoke
	 */
	private boolean isFreshInvoke() {
		int portNumber = getPortNumber();
		if (portNumber < 49152 && portNumber > 65535) { // ensure a valid port number
			Logger.warning(CLASS_ID, "Invalid port Number : '" + portNumber + "'. Please set a port value >= 49152 & <= 65535 in RGA Configuration parameters... ", null);
			Logger.info(CLASS_ID, "Using default port: 49999 for Aggregator Lock", null);
			portNumber = 49999;
		}
		try {
			Logger.warning(CLASS_ID, "Proceeding with Feed aggregation by locking the Aggregator", null);
			socket = new ServerSocket(portNumber, 0, InetAddress.getByAddress(new byte[] { 127, 0, 0, 1 }));
			Logger.info(CLASS_ID, "Acquired port : " + portNumber + " till the end of this aggregator run", null);
			return true;
		} catch (Exception e) {
			Logger.error(CLASS_ID, "Error while aquiring port" + portNumber + ". aggregator wont run this time", e);
		}
		return false;
	}

	/**
	 * Gets the port number.
	 * 
	 * @return the port number
	 */
	private int getPortNumber() {
		int portNumber = 49999;
		try {
			portNumber = Integer.parseInt(grciProperties.getProperty("Port_Number"));
		} catch (NumberFormatException e) {
			Logger.error(CLASS_ID, "Invalid portNumber:" + portNumber, null);
		}
		return portNumber;
	}

	/**
	 * Merge results.
	 * 
	 * @param feedResults
	 *            the feed results
	 * @return merged feeds
	 */
	private Object[][] mergeResults(Object[][] feedResults) {
		Logger.debug(CLASS_ID, "inside mergeResults", null);
		int resultCnt = feedResults.length;
		Object[][] results = new String[0][];
		for (int k = 0; k < resultCnt; k++) {
			results = DataIntegrationInfolet.addRow(results, feedResults[k]);
		}
		Logger.debug(CLASS_ID, "exit mergeResults", null);
		return results;
	}

	/**
	 * Invoke infolets.
	 * 
	 * @param feedResults
	 *            the feed results
	 */
	private void invokeInfolets(ArrayList<ChannelFeedData> feedResults) {
		SCInfolet infoletHelper = new SCInfolet();
		Logger.debug(CLASS_ID, "feeds clone = " + feedResults.size(), null);
		Map<Long, ChannelFeedData> notifyMap = new HashMap<Long, ChannelFeedData>();
		for (ChannelFeedData fData : feedResults) {
			Logger.debug(CLASS_ID, "invokeInfolets --> inside fData  " + fData.getMsgUID(), null);
			if (fData != null) {
				Logger.debug(CLASS_ID, "param=" + fData, null);
				long infoletId = Utils.getLongValue(fData.getStructuredContentInfoletId());
				Logger.info("infoletId -->", "infoletId --" + infoletId, null);
				if (infoletId > 0) {
					long instanceId = -1;
					try {
						instanceId = infoletHelper.invokeInfolet(fData);
					} catch (Exception e) {
						Logger.error(CLASS_ID, "Exception while invoking infolet :" + infoletId, e);
					}
				}
			}
		}
		if (notifyMap.size() > 0) {
		
			Logger.error(CLASS_ID, "****** calling notifications, total size = " + notifyMap.size(), null);
			long start = System.currentTimeMillis();
			 DBInstance.getInstance().getDbInstance().sendNotifications(notifyMap);
			Logger.error(CLASS_ID, "****** time taken for notification = " + ((System.currentTimeMillis() - start) / 1000) + " secs", null);
		}
	}

	/**
	 * Checks if is failed invocation.
	 * 
	 * @param instanceId
	 *            the instance id
	 * @param infoletId
	 *            the infolet id
	 * @return true, if is failed invocation
	 */
	private boolean isFailedInvocation(long instanceId, long infoletId) {
		Logger.debug(CLASS_ID, "isFailedInvocation entry", null);
		String invocationStatus = DBInstance.getInstance().getDbInstance().getInvocationStatus(infoletId, instanceId);
		if (invocationStatus != null && invocationStatus.toUpperCase().equals(STATUS_FAILURE)) {
			Logger.debug(CLASS_ID, "returning true", null);
			return true;
		}
		return false;
	}

	/**
	 * Gets the max notification number.
	 * 
	 * @return the max notification number
	 */
	private int getMaxNotificationNumber() {
		int maxNumbers = 1000;
		try {
			maxNumbers = Integer.parseInt(grciProperties.getProperty("Maximum_notification_per_run"));
			Logger.debug(CLASS_ID, "Maximum_notification_per_run is " + maxNumbers, null);
		} catch (NumberFormatException e) {
			Logger.error(CLASS_ID, "Invalid Maximum_notification_per_run:" + maxNumbers, null);
		}
		return maxNumbers;
	}

	/**
	 * Returns a hash code value for the object. This method is supported for the benefit of hash tables such as those provided by {@link java.util.HashMap}.
	 * <p/>
	 * The general contract of {@code hashCode} is:
	 * <ul>
	 * <li>Whenever it is invoked on the same object more than once during an execution of a Java application, the {@code hashCode} method must consistently return the same integer, provided no information used in {@code equals} comparisons on the object is modified. This integer need not remain consistent from one execution of an application to another execution of the same application.
	 * <li>If two objects are equal according to the {@code equals(Object)} method, then calling the {@code hashCode} method on each of the two objects must produce the same integer result.
	 * <li>It is <em>not</em> required that if two objects are unequal according to the {@link Object#equals(Object)} method, then calling the {@code hashCode} method on each of the two objects must produce distinct integer results. However, the programmer should be aware that producing distinct integer results for unequal objects may improve the performance of hash tables.
	 * </ul>
	 * <p/>
	 * As much as is reasonably practical, the hashCode method defined by class {@code Object} does return distinct integers for distinct objects. (This is typically implemented by converting the internal address of the object into an integer, but this implementation technique is not required by the Java<font size="-2"><sup>TM</sup></font> programming language.)
	 *
	 * @return a hash code value for this object.
	 * @see Object#equals(Object)
	 * @see System#identityHashCode
	 */
	@Override
	public int hashCode() {
		return super.hashCode();
	}

	/**
	 * Aggregate updates on all sources listed in SrcIDs.
	 * 
	 * @param srcIDs
	 *            : A comma separated list of source IDs to be aggregated
	 * @return Success : Received <i>n</i> new updates/ Failed / Content refresh already running or No Result
	 */
	public synchronized String aggregateNPopulateFeeds(String srcIDs) {
		int retVal = 0;
		ArrayList<ChannelFeedData> feeds = aggregateFeeds();
		if (feeds != null) {
			retVal = DBInstance.getInstance().getDbInstance().writeToAggregatorTable(feeds); // Write feeds to ms_rga_aggregator table
			// invoke RGA-Channel Response infolet to segregate alerts to respective channels
			DBInstance.getInstance().getDbInstance().callPlSqlInfolet("{ call Ms_Rga_Process_Channel_Resp.Pr_Populate_Channel_Response(?,?) }");
			// invoke RGA-Subbscription Response infolet to segregate alerts to respective subscriptions to channels
			DBInstance.getInstance().getDbInstance().callPlSqlInfolet("{ call Ms_Rga_Process_Subscrptn_Resp.Pr_Populate_Subscrptn_Resp(?,?) }");
		} else {
			Logger.debug(CLASS_ID, "No updates on feeds were found. Not need to call write & populate", null);
			return ("Content refresh already running or No Result");
		}
		int feedCountOfUser = getFeedCountForUser(srcIDs, feeds);
		Logger.info(CLASS_ID, "Feed count of user is " + feedCountOfUser, null);
		return ((feedCountOfUser > -1) ? ("Success : " + "Received " + feedCountOfUser + " new updates") : "Failed");
	}

	private synchronized int getFeedCountForUser(String srcIDs, ArrayList<ChannelFeedData> feeds) {
		int feedCountOfUser = 0;
		if (srcIDs != null && srcIDs.length() > 0) {
			String[] sourceIds = srcIDs.split(",");
			if (sourceIds.length > 0) {
				List<String> sourceIdsList = Arrays.asList(sourceIds);
				for (ChannelFeedData feed : feeds) {
					if (sourceIdsList.contains(feed.getSourceId())) {
						feedCountOfUser++;
					}
				}
			}
		}
		return feedCountOfUser;
	}

	/** The Constant sampleData. */
	private static final String[][] sampleData = { { "email", "msi-jamessrv-01.metricstream.com", "debjit3", "welcome*123", "mail.imap.ssl.enable=false" } };
}