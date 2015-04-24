package com.metricstream.systemi.ext.infolet;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.metricstream.log.Logger;

import com.metricstream.systemi.rga.cpi.CPIConsumptionUtil;
import com.metricstream.systemi.rga.dao.DBInstance;
import com.metricstream.systemi.rga.dao.DBManager;
import com.metricstream.systemi.rga.domain.FeedInfo;
import com.metricstream.systemi.rga.domain.QueueInfoDomain;
import com.metricstream.systemi.rga.domain.QueueMessageIdInfo;
import com.msi.grcintelligence.cpi.data.CPIDistributionDetails;
import com.msi.grcintelligence.jaxbObj.cfrFileFormat.GrcfObject;
import com.msi.grcintelligence.util.AggregationContentData;
import com.msi.grcintelligence.utility.mongodb.CFRAnnualUtil;

/**
 * The Class is used to consume the date from the Active MQ.
 */
public class AmqFeedAggregatorInfolet extends FeedAggregator {
	/** The queue. */
	final QueueInfo queue = new QueueInfo();

	/**
	 * Instantiates a new amq feed aggregator infolet.
	 * 
	 * @param timeStamps
	 *            time stamps
	 */
	AmqFeedAggregatorInfolet(Map<String, String> timeStamps) {
		super(timeStamps);
	}

	/**
	 * This method is used to get the feed data from
	 * 
	 * @param feedUrlConns
	 *            list of ACtive MQ url's
	 */
	@Override
	public List<Feed> getFeeds(List<FeedInfo> feedUrlConns) throws Exception {
		if (feedUrlConns == null) {
			return null; // Sanity check
		}
		List<Feed> pipes = new ArrayList<Feed>();
		for (FeedInfo pserver : feedUrlConns) {
			Logger.debug("AMq url" + pserver.getUrl(), null, null);
			Logger.debug("AMq infoletid" + pserver.getSCInfoletID(), null, null);
			// Create the new pop server object
			AmqPipe p = new AmqPipe(pserver.getUrl(), "-1L", null, null, pserver.getQueueURL(), pserver.getSCInfoletID(), pserver.getSaveAttachment(), pserver.getChannelId(), pserver.getSourceId());
			pipes.add(p);
		}
		Logger.debug("AMQ pservers count = " + pipes.size(), null, null);
		return pipes;
	}

	/**
	 * Active MQ pipe
	 */
	class AmqPipe extends Feed {
		/**
		 * Instantiates a new amq pipe.
		 *
		 * @param feedUrl
		 *            feed url
		 * @param lastAccessedTimeStamp
		 *            last accessed time stamp
		 * @param userID
		 *            user id
		 * @param password
		 *            password
		 * @param propStr
		 *            property string
		 * @param structuredContentInfoletId
		 *            structured content infolet id
		 * @param saveAttachment
		 *            the save attachment
		 * @param channelID
		 *            channel id
		 * @param sourceId
		 *            the source id
		 * @throws Exception
		 *             exception
		 */
		AmqPipe(String feedUrl, String lastAccessedTimeStamp, String userID, String password, String propStr, String structuredContentInfoletId, String saveAttachment, String channelID, String sourceId) throws Exception {
			super(feedUrl, lastAccessedTimeStamp, userID, password, propStr, structuredContentInfoletId, saveAttachment, channelID, sourceId, "");
		}

		@Override
		boolean validate(FeedXtraxnStats stats) {
			// to be changed to proper validation
			return true;
		}

		@Override
		void close() throws Exception {
		}

		@Override
		/** 
		 * This method is used to get the latest feeds from the  last updated time stamp
		 * @param feeds list of channel feeds
		 * @param feedTimeStamps feeds with their last updated time stamp
		 * @return updated feed records
		 */
		FeedXtraxnStats getUpdates(List<ChannelFeedData> feeds, Map<String, String> feedTimeStamps) throws Exception {
			Logger.info("inside getUpdates for active mq", null, null);
			final FeedXtraxnStats stats = new FeedXtraxnStats();
			List<QueueMessageIdInfo> list=new ArrayList<QueueMessageIdInfo>();
			final String url = getPropStr();
			stats.setStartTime(new Date());
			stats.setThreadName(Thread.currentThread().getName());
			stats.setLastExtrxnTime(lastAccessedTimeStamp);
			stats.setFeedType(FeedType.AMQ);
			stats.setFeedName(url);
			Logger.info("Aggregation", " channel id is  " + this.channelID, null);
			List<String> activationCode = DBInstance.getInstance().getDbInstance().getActivationCode(this.channelID);
			Logger.info("Aggregation", " activation code is  " + activationCode, null);
			List<Object> listObject = new ArrayList<Object>();
			for (String actCode : activationCode) {
				QueueInfoDomain info= queue.getQueueInfo(actCode, url);
				listObject.addAll(info.getList());
				list.addAll(info.getMessageIdList());
			}
			try {
				QueueInfo.updateMessageDetail(list);
				Logger.debug("Queue Info", "Got list of feed for Active mq " + listObject.size(), null);
				for (Object obj : listObject) {
					if (obj instanceof AggregationContentData) {
						Logger.debug("Queue INfo", " its an AggregationContent data", null);
						Logger.debug("FEED type data", null, null);
						AggregationContentData d = (AggregationContentData) obj;
						feeds.add(new AmqFeedData(getAddress(), userID, d, Calendar.getInstance().getTime(), structuredContentInfoletId, saveAttachment, channelID, Calendar.getInstance().getTime(), sourceID));
						stats.incrementedAccepted();
					} else if (obj instanceof GrcfObject) {
						Logger.debug("Queue INfo", " its an CFR Annual data", null);
						GrcfObject grc = (GrcfObject) obj;
						// save it to db
						Logger.info("save the grcf object in mongodb", null, null);
						CFRAnnualUtil obja = new CFRAnnualUtil();
						obja.writeToMongo(grc);
					} else if (obj instanceof CPIDistributionDetails) {
						Logger.info("AmqFeedAggregatorInfolet", "Writing the CPI data to client mongo", null);
						CPIDistributionDetails distributionDetails = (CPIDistributionDetails) obj;
						CPIConsumptionUtil.getInstance().consume(distributionDetails);
						Logger.info("AmqFeedAggregatorInfolet", "CPI Data has been written to client mongo", null);
					} else {
						Logger.info("Queue info", "Content not matching ", null);
					}
				}
				stats.setEndTime(new Date());
				stats.setTotalFound(feeds.size());
			} catch (Exception ex) {
				Logger.error(this.getClass().getName(), "Error occurred in get updates of AMQ ", ex);
				throw new Exception();
			}
			Logger.info("Queue Info", "total number fo stats record " + feeds.size(), null);
			return stats;
		}
	}
}
