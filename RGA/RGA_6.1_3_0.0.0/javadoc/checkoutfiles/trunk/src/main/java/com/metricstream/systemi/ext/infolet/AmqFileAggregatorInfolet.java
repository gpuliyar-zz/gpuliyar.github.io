package com.metricstream.systemi.ext.infolet;

import com.metricstream.log.Logger;

import com.metricstream.systemi.rga.dao.DBInstance;
import com.metricstream.systemi.rga.dao.DBManager;
import com.metricstream.systemi.rga.domain.FeedInfo;
import com.metricstream.systemi.rga.domain.SourceInfo;
import com.metricstream.systemi.utils.Utils;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

/**
 * AmqFileAggregatorInfolet main class which is invoked by the JAVA INFOLET to store the File content from Content server. AMQ messages.
 * Created by munavar.basha on 11/14/2014.
 */
public class AmqFileAggregatorInfolet {
    
    /** The Constant CLASS_ID. */
    public static final String CLASS_ID = "AmqFileAggregatorInfolet";
    
    /** The grci properties. */
    public Properties grciProperties;
    
    /** The socket. */
    private static ServerSocket socket;

    /**
     * Process channel feeds.
     */
    public void processChannelFeeds(){
        grciProperties =DBInstance.getInstance().getDbInstance().getAllProperties();
    }


    /**
     * Aggregate only a subset of Sources as listed in the Source ID list.
     *
     * @param srcIDs subset of Sources seperated by ',' or ';'
     * @return a list of aggregated updates on the listed feeds
     */
    public synchronized ArrayList<ChannelFeedData> aggregateFeeds (String srcIDs) {
		/*
		 * Tomcat in ECP 6.1 doesn't seem to set appropriate class loader which
		 * is affecting following 1. RSS feed loading through RSS 2. Email Body
		 * extraction from a message The following fixes both these issues
		 */
        Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());
        Logger.debug(CLASS_ID, "entry  ChannelFeedAggregatorInfolet::aggregateFeeds() ", null);

		/*
		 * Implementing Aggregator level Lock to avoid duplicate alerts.
		 * AGGREGATOR_LOCK = 0, Unlocked AGGREGATOR_LOCK = 1, Locked
		 */
        if (isFreshInvoke()) {
            try {
                List<SourceInfo> channelFeeds = DBInstance.getInstance().getDbInstance().loadAMQFileChannelFeeds(srcIDs);

                // Create the list of channel definitions
                if ((channelFeeds == null) || (channelFeeds.size() == 0)) {
                    // No channel feeds
                    Logger.debug(CLASS_ID, "Found zero channel feeds, returning ...", null);
                    closeSocket();
                    return null;
                }

                // Create the channel feeds
                List<FeedInfo> amqList = new ArrayList<FeedInfo>();
                getFeedsList(channelFeeds, amqList);   // Seggregate the list of feeds based on the feed type

                List<FeedXtraxnStats> statsList = new LinkedList<FeedXtraxnStats>();
                List<Feed> mailFeeds = null, rssFeeds = null, amqFeeds = null;;
                AmqFileAggregation amqAgg = new AmqFileAggregation(null); //this is to fetch data from ActiveMQ

                //get feeds through ActiveMQ as GRCIntelligence source.
                if (amqList.size() != 0) {
                    amqFeeds = amqAgg.getFeeds(amqList);
                }
                List<Feed> feeds =new ArrayList<Feed>();
                if(null!=amqFeeds) {
                    feeds.addAll(amqFeeds);
                }

                Utils.writeToLog(statsList, true);
                Logger.info(CLASS_ID, "Finished Feed aggregation and Unlocking the Aggregator", null);
            } catch (Exception ex) {
                Logger.error(CLASS_ID, "Encountered Error while processing channel feeds" + ex.getMessage(), ex);
            } finally {
                closeSocket();
            }
        } else {
            Logger.error(CLASS_ID, "Aggregator has been Locked by someone. hence returning........", null);
        }
        return null; // new String[0][0];
    }

    /**
     * Checks if port is fresh invoke.
     * @return true, if is fresh invoke
     */
    private boolean isFreshInvoke() {
        int portNumber = getPortNumber();

        if( portNumber < 49152 && portNumber > 65535 ) {// ensure a valid port number
            Logger.warning(CLASS_ID, "Invalid port Number : '" + portNumber
                            + "'. Please set a port value >= 49152 & <= 65535 in RGA Configuration parameters... ", null);
            Logger.info(CLASS_ID, "Using default port: 49999 for Aggregator Lock", null);
            portNumber = 49999;
        }

        try {
            Logger.warning( CLASS_ID, "Proceeding with Feed aggregation by locking the Aggregator",	null);
            socket = new ServerSocket(portNumber, 0,
                    InetAddress.getByAddress(new byte[]{127, 0, 0, 1}));
            Logger.info(CLASS_ID, "Acquired port : " + portNumber + " till the end of this aggregator run", null);
            return true;
        } catch (Exception e) {
            Logger.error(CLASS_ID, "Error while aquiring port" + portNumber + ". aggregator wont run this time", e);
        }
        return false;
    }

    /**
     * Gets the port number.
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
     * Close the socket connection.
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
     * Gets the feeds list.
     * @param channelFeeds the channel feeds
     * @param amqList the amq list
     */
    private void getFeedsList(List<SourceInfo> channelFeeds,  List<FeedInfo> amqList) {
        //  String params[], rssParams[], amqParams[];
        int mailSrcCnt = 0, rssSrcCnt = 0, amqSrcCnt = 0;
        // for (int i = 0; i < channelFeeds.length; i++) {
        int i=0;
        for(SourceInfo cf: channelFeeds) {
            // Make sure we don't pull from same channels again
            Logger.debug(CLASS_ID, "i, source " + i + ", " +  cf.getSourceType(), null);
            if (cf.getSourceType().toLowerCase()
                    .equals("amq")) { // Feed provided by GRCIntelligence source.
                Logger.info(CLASS_ID, "Parsing AMQ Feed = " + cf.getServerURL(), null);
                amqList.add(amqSrcCnt++, parseFeed(cf,"amq") );
            }
            Logger.debug(CLASS_ID, "Successfully stored feeds for processing", null);
        }
    }

    /**
     * Parses the feed.
     * @param feedDetails the feed details
     * @param feedType the feed type
     * @return the feed info
     */
    private FeedInfo parseFeed(SourceInfo feedDetails,String feedType){
        Logger.info(CLASS_ID, "Parsing Email Feed = " +  feedDetails.getServerURL(), null);

        FeedInfo info=new FeedInfo();
        info.setUrl( feedDetails.getServerURL());
        info.setUserName(feedDetails.getUserName());
        info.setPassword( feedDetails.getPassword());
        info.setParameter( feedDetails.getParameters());
        info.setSCInfoletID( feedDetails.getStructureInfolet());
        info.setSaveAttachment( feedDetails.getSaveAttachments());
        info.setChannelId(  feedDetails.getChannelId());
        info.setSourceId( feedDetails.getSrcPk());

        if(feedType.equalsIgnoreCase("amq")) {
        	info.setType("amq");
        	String queueName = (String) feedDetails.getQueueName();
        	String queueURL = (String) feedDetails.getQueueURL();
        	info.setQueueURL(queueURL+queueName);
        }

        // Add here
        Logger.debug(CLASS_ID,  "params[0]-URL = " + info.getUrl() +
        		"; params[3]-Parameters = " + info.getParameter() +
        		"; params[4]-lastTimeStamp = " + info.getUpdatedFeedTimeStamp() +
        		"; params[5]-SC InfoletID = " + info.getSCInfoletID() +
        		"; params[6]-save Attchmnts = " + info.getSaveAttachment() +
        		"; params[7]-ChannelID = " +info.getChannelId() , null);

        return info;
    }
}
