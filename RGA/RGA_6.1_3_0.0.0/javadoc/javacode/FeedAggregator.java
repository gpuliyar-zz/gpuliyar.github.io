package com.metricstream.systemi.ext.infolet;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.metricstream.log.Logger;
import com.metricstream.systemi.ext.infolet.FeedXtraxnStats.ExtractionStatus;
import com.metricstream.systemi.ext.infolet.FeedXtraxnStats.ReturnCode;
import com.metricstream.systemi.rga.dao.DBInstance;
import com.metricstream.systemi.rga.dao.IDBHelper;
import com.metricstream.systemi.rga.domain.FeedInfo;
import com.metricstream.systemi.utils.Utils;

/**
 * The Feed Aggregator class.
 */
public abstract class FeedAggregator {

	/** The Constant CLASS_ID. */
	private static final String CLASS_ID = "FeedAggregator";

	// Static definition of # of threads to create at a time (5)
	/** The num concurrent threads. */
	int NUM_CONCURRENT_THREADS = 15;
	
	/** The feed time stamps. */
	Map<String, String> feedTimeStamps; // Store timestamps of previous runs
	
	/** The grci properties. */
	public Properties grciProperties;

	private IDBHelper dbInstance = DBInstance.getInstance().getDbInstance();
	
	/**
	 * Instantiates a new feed aggregator.
	 *
	 * @param timeStamps the time stamps
	 */
	FeedAggregator(Map<String, String> timeStamps) {
		this.feedTimeStamps = timeStamps;
		grciProperties = dbInstance.getAllProperties();
	}

	/**
	 * Gets the feeds.
	 *
	 * @param feedUrlConns the feed url conns
	 * @return the feeds
	 * @throws Exception the exception
	 */
	abstract public List<Feed> getFeeds(List<FeedInfo> feedUrlConns)
			throws Exception;

	/**
	 * Gets the updates from feeds.
	 *
	 * @param feeds the feeds
	 * @param feedStats the feed stats
	 * @return the updates from feeds
	 */
	ArrayList<ChannelFeedData> getUpdatesFromFeeds(List<Feed> feeds, List<FeedXtraxnStats> feedStats) {
		try{
			return aggregateFeeds(feeds, feedStats);
		}catch(Exception e){
			return null;
		}
	}


	/**
	 * Process feeds.
	 *
	 * @param feeds the feeds
	 * @param feedStats the feed stats
	 * @return the object[][]
	 */
	Object[][] processFeeds(List<Feed> feeds, List<FeedXtraxnStats> feedStats) {

		Object[][] results = new String[0][0];
		ArrayList<ChannelFeedData> channelData = null;
		try {
			channelData = aggregateFeeds(feeds, feedStats);
			Logger.info(CLASS_ID, "channelData.size = " + channelData.size(), null);
		} catch (Exception rssEx) {
			Logger.error( CLASS_ID, "Encountered Error while processing rss feeds : "
					+ rssEx.getMessage(), rssEx);
			return new String[0][0];
		}
		results = getResults(channelData);
		return results;
	}

	// this will go and sit in si_<metricId>_t table
	/**
	 * Gets the feed results.
	 *
	 * @param channelData the channel data
	 * @return the results
	 */
	static Object[][] getResults(ArrayList<ChannelFeedData> channelData) {
		Object[][] results = null;

		// Then go through results
		if (channelData != null) {
			ChannelFeedData cData;
			results = new Object[channelData.size()][];

			Logger.debug(CLASS_ID, "---- Processing Feed Data ----", null);
			// Go through the results - and build up the insert statement
			for (int i = 0; i < channelData.size(); i++) {
				// Get next result
				cData = channelData.get(i);

				// (Disclaimer: Based on observation)-
				// Note the ordering. This is the same order that has been
				// defined in the infolet out parameters. Also in
				// si_<metricId>_t table, the column ordering will be reverse.
				// And you can have more params here than the ones defined in
				// infolet, for example, we have save attachment here but that
				// is not included as an out param for the infolet. Ensure any
				// such param comes to the end
				results[i] = new Object[14];
				results[i][0] = cData.getServer();
				results[i][1] = cData.getSender();
				results[i][2] = cData.getRecipient();
				results[i][3] = cData.getSubject();
				results[i][4] = cData.getBody();
				results[i][5] = cData.getAttachmentIDs();
				results[i][6] = ""; // processed_flag
				results[i][7] = ""; // feed_data_id
				results[i][8] = cData.getMsgUID();
				results[i][9] = cData.getSentDate();
				if (cData.getStructuredContentInfoletId() != null
						|| cData.getSaveAttachment() != null) {
					results[i][10] = cData.getStructuredContentInfoletId();
					results[i][11] = cData.getChannelId();
					results[i][12] = cData.getSaveAttachment();
					results[i][13] = cData.getFileName();
				}
			}
		}
		return results;
	}

	/**
	 * Aggregate feeds.
	 *
	 * @param feeds the feeds
	 * @param statsList the stats list
	 * @return the array list
	 * @throws Exception the exception
	 */
	protected ArrayList<ChannelFeedData> aggregateFeeds(List<Feed> feeds,
			List<FeedXtraxnStats> statsList) throws Exception {

		// Create a list object to hold the results
		ArrayList<ChannelFeedData> feedData = new ArrayList<ChannelFeedData>();
		long curTime = System.currentTimeMillis();
		List<Feed> validFeeds = new ArrayList<Feed>();
		List<FeedXtraxnStats> invalidFeedStats = new LinkedList<FeedXtraxnStats>();

		for (Feed f : feeds) {
			// Fill in stats to print INVALIDITY in stats file
			if(null!=f){

				FeedXtraxnStats stats = new FeedXtraxnStats(); // Validate the feed
				// stats.errors = new ArrayList<String>();
				stats.setScInfoletId(f.getStructuredContentInfoletId());
				stats.setSaveAttachments(f.getSaveAttachment());
				stats.setChannelId(f.getChannelID());
				if (f.validate(stats)) {
					validFeeds.add(f);
				} else {
					stats.setFeedType(f.feedType);

					switch (f.feedType) {
					case RSS:
						stats.setFeedName(f.getAddress());
						break;
					case EMAIL:
						stats.setFeedName(getOnlyUserName(f.userID) + "@" + f.address);
						break;
					case AMQ:
						stats.setFeedName(f.address);
						break;
					default:
						Logger.error(CLASS_ID, "Unsupported FeedType " + stats.getFeedType(), null);
						return null;
					}

					stats.setStatus(ExtractionStatus.FAIL);
					stats.getStatus().returnCode = ReturnCode.INVALID_ADDRESS;
					stats.setStartTime(new Date());
					invalidFeedStats.add(stats);
				}
			}
		}
		if (!invalidFeedStats.isEmpty()) {
			sendFailureNotification(invalidFeedStats);
		}
		Utils.writeToLog(invalidFeedStats, false);

		// Create the queue of feed URLs
		FeedQueue fQ = new FeedQueue(validFeeds.toArray(new Feed[validFeeds.size()]));

		if(null != grciProperties.getProperty("Concurrent_Threads")){
			NUM_CONCURRENT_THREADS = Integer.parseInt(grciProperties.getProperty("Concurrent_Threads"));
			Logger.debug(CLASS_ID, "NUM_CONCURRENT_THREADS values = " + NUM_CONCURRENT_THREADS, null);
		}

		ExecutorService es = Executors.newFixedThreadPool(NUM_CONCURRENT_THREADS);

		List<FeedReaderThread> feedReaders = new ArrayList<FeedReaderThread>();

		for (int i = 0; i < validFeeds.size(); i++) {
			feedReaders.add(new FeedReaderThread(fQ));
		}

		try {
			// Executes threads in a pool and waits till finish
			List<Future<FeedXtraxnStats>> futures = es.invokeAll(feedReaders);

			// Get the stats for all the extractions
			for (Future<FeedXtraxnStats> f : futures) {
				if (f != null) {
					statsList.add(f.get());
				}
			}
			es.shutdown();
		} catch (Exception e) {
			Logger.error(CLASS_ID, "Thread-Pool ExecutorService failed with an exception " + e.getMessage(), e);
		}

		for (int i = 0; i < validFeeds.size(); i++) {// Add all data in one list
			// And add its results to the overall results
			feedData.addAll(feedReaders.get(i).getFeedData());
		}

		Logger.debug(CLASS_ID, "Final Feeddata Size = " + feedData.size(), null);
		Logger.debug(CLASS_ID, "Elapsed time: " + (System.currentTimeMillis() - curTime), null);

		return feedData;
	}

	/**
	 * Send failure notification.
	 *
	 * @param invalidFeedStats the invalid feed stats
	 */
	private void sendFailureNotification(List<FeedXtraxnStats> invalidFeedStats) {
		dbInstance.sendFailureNotifications(invalidFeedStats);
	}

	/**
	 * Return only the username Removing trailing "@<server>.com" from an email address
	 *
	 * @param emailAddr the email addr
	 * @return trimmed userName
	 */
	static String getOnlyUserName(String emailAddr) {
		if (emailAddr != null) {
			String[] userNameSplit = emailAddr.split("@");
			return userNameSplit[0];
		}
		return "";
	}

	/**
	 * Local Thread runner to get data from a POP server feed.
	 */
	class FeedReaderThread implements Callable<FeedXtraxnStats> {

		/** The server q. */
		private FeedQueue serverQ; // URL to retrieve data from
		// Retrieved entries as an array of feed message data objects
		/** The feed data. */
		private ArrayList<ChannelFeedData> feedData = new ArrayList<ChannelFeedData>();

		/**
		 * Create the thread.
		 *
		 * @param feedQ the feed q
		 * @throws MalformedURLException the malformed url exception
		 */
		FeedReaderThread(FeedQueue feedQ) throws MalformedURLException {
			this.serverQ = feedQ;
		}

		/**
		 * Go through servers while available in the Queue and add results.
		 *
		 * @return statistics of this run [ messages read, accepted, rejected,
		 *         etc ]
		 */
		@Override
		public FeedXtraxnStats call() {
			FeedXtraxnStats stats = null;
			Feed feedServer = serverQ.getNextFeed();
			Logger.debug(CLASS_ID,
					"Running Thread for user : '" + feedServer.getUserID()
					+ "' on server : " + feedServer.getAddress(), null);

			try {
				stats = feedServer.getUpdates(feedData, feedTimeStamps);// Get the feed input
				feedServer.close();
			} catch (Exception e) {
				Logger.error(
						CLASS_ID,
						"FAILED reading messages from server "
								+ feedServer.getUserID() + "@"
								+ feedServer.getAddress() + "====="
								+ e.getMessage(), null);
			}
			return stats;
		}

		/**
		 * Get the result data.
		 *
		 * @return the feed results as a list of SyndFeed objects
		 */
		List<ChannelFeedData> getFeedData() {
			return feedData;
		}
	}
}
