package com.metricstream.systemi.ext.infolet;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.metricstream.log.Logger;

import com.metricstream.systemi.rga.dao.DBInstance;
import com.metricstream.systemi.rga.dao.DBManager;
import com.metricstream.systemi.rga.domain.FeedInfo;
import com.metricstream.systemi.rga.domain.QueueInfoDomain;
import com.metricstream.systemi.rga.domain.QueueMessageIdInfo;
import com.msi.grcintelligence.jaxbObj.cfrFileFormat.GrcfObject;
import com.msi.grcintelligence.utility.mongodb.CFRAnnualUtil;

/**
 * Aggregates the Active MQ Files
 * Created by munavar.basha on 11/19/2014.
 */
public class AmqFileAggregation extends FeedAggregator {

    /** The queue. */
    final QueueInfo queue=new QueueInfo();

    /**
     * Instantiates a new amq file aggregation.
     */
    AmqFileAggregation() {
        super(null);
    }

    /**
     * Instantiates a new amq file aggregation.
     *
     * @param timeStamps the time stamps
     */
    AmqFileAggregation(Map<String, String> timeStamps) {
        super(null);
    }

    /**
     * This method returns the feed list from the active mq
     * @param feedUrlConns List of feed url connection details
     * @return feed list
     */
    @Override
    public List<Feed> getFeeds(List<FeedInfo> feedUrlConns) throws Exception {

        if (feedUrlConns == null) {
            return null; // Sanity check
        }

        List<Feed> pipes = new ArrayList<Feed>();

        for(FeedInfo pserver:feedUrlConns){
            Logger.debug("AMq url" + pserver.getUrl(), null, null);
            Logger.debug("AMq infoletid" +pserver.getSCInfoletID(), null,null);

            // Create the new pop server object
            AmqPipe p=new AmqPipe(pserver.getUrl(),"-1L",null,null,pserver.getQueueURL(),
                    pserver.getSCInfoletID(),pserver.getSaveAttachment(),pserver.getChannelId(),pserver.getSourceId());
            pipes.add(p);
        }

        Logger.debug("AMQ pservers count = " + pipes.size(), null,null);
        return pipes;
    }

    /**
     * The Class AmqPipe.
     */
    class AmqPipe extends Feed {
        
        /**
         * Instantiates a new amq pipe.
         *
         * @param feedUrl the feed url
         * @param lastAccessedTimeStamp the last accessed time stamp
         * @param userID the user id
         * @param password the password
         * @param propStr the prop str
         * @param structuredContentInfoletId the structured content infolet id
         * @param saveAttachment the save attachment
         * @param channelID the channel id
         * @param sourceId the source id
         * @throws Exception the exception
         */
        AmqPipe(String feedUrl, String lastAccessedTimeStamp, String userID,
                String password, String propStr,
                String structuredContentInfoletId, String saveAttachment,
                String channelID, String sourceId) throws Exception {
            super(feedUrl, lastAccessedTimeStamp, userID, password, propStr,
                    structuredContentInfoletId, saveAttachment, channelID, sourceId, "");
        }

        @Override
        boolean validate(FeedXtraxnStats stats) {
            return true;
        }

        @Override
        void close() throws Exception {
        }

        /**
         * This method is used to get the latest updated from the feeds from the last time updated time stamp
         * @param feeds
         * @param feedTimeStamps
         */
        @Override
        FeedXtraxnStats getUpdates(List<ChannelFeedData> feeds, Map<String, String> feedTimeStamps) throws Exception {
            Logger.info("inside getUpdates for active mq",null,null);
            List<QueueMessageIdInfo> queueIds= new ArrayList<QueueMessageIdInfo>();
            final FeedXtraxnStats stats = new FeedXtraxnStats();
            final String url = getPropStr();
            stats.setStartTime(new Date());
            stats.setThreadName(Thread.currentThread().getName());
            stats.setLastExtrxnTime(lastAccessedTimeStamp);
            stats.setFeedType(FeedType.AMQ);
            stats.setFeedName(url);

            Logger.info("Aggregation", " channel id is  " + this.channelID, null);
            List<String> activationCode =  DBInstance.getInstance().getDbInstance().getActivationCode(this.channelID);
            Logger.info("Aggregation", " activation code is  " + activationCode, null);
            List<Object> listObject=new ArrayList<Object>();
            for(String actCode:activationCode){
            	QueueInfoDomain infoDomain=queue.getQueueInfo(actCode, url);
                listObject.addAll(infoDomain.getList());
                queueIds.addAll(infoDomain.getMessageIdList());
            }
            try{
            	 QueueInfo.updateMessageDetail(queueIds);
                for(Object obj:listObject){

                	if(obj instanceof GrcfObject){
                        GrcfObject grc = (GrcfObject)obj;
                        // save it to db
                        new CFRAnnualUtil().writeToMongo(grc);
                    }
                }
                stats.setEndTime(new Date());
                stats.setTotalFound(feeds.size());
               
                
            }
            catch(Exception ex){
                Logger.info(this.getClass().getName(), "Error occurred in get updates of AMQ ",ex );
                throw new Exception();
            }
            

            return stats;
        }
    }
}
