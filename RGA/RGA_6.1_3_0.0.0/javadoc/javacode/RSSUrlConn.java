package com.metricstream.systemi.ext.infolet;

import com.metricstream.log.Logger;


import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: praveen.gb
 * Date: 6/20/14
 * Time: 3:06 PM
 * To change this template use File | Settings | File Templates.
 */

/**
 * This class is used to get the updated feeds from the rss url
 * @author praveen.gb
 */
public class RSSUrlConn extends Feed {
    
    /** The Constant CLASS_ID. */
    private static final String CLASS_ID = "RSSURLConnection";
    
    /** The feed. */
    SyndFeed feed;

    /**
     * Instantiates a new RSS url conn.
     *
     * @param feedUrl the feed url
     * @param lastAccessedTimeStamp the last accessed time stamp
     * @param userID the user id
     * @param password the password
     * @param propStr the prop str
     * @param structuredContentInfoletId the structured content infolet id
     * @param saveAttachments the save attachments
     * @param channelId the channel id
     * @param sourceId the source id
     * @throws Exception the exception
     */
    RSSUrlConn(String feedUrl, String lastAccessedTimeStamp, String userID,
               String password, String propStr,
               String structuredContentInfoletId, String saveAttachments,
               String channelId, String sourceId) throws Exception {
        super(feedUrl, lastAccessedTimeStamp, userID, password, propStr,
                structuredContentInfoletId, saveAttachments, channelId, sourceId, "");
        feedType = FeedType.RSS;
    }

    // This validated a RSS feed URL by connecting to it
    /* (non-Javadoc)
     * @see com.metricstream.systemi.ext.infolet.Feed#validate(com.metricstream.systemi.ext.infolet.FeedXtraxnStats)
     */
    @Override
    public boolean validate(FeedXtraxnStats stats) {
        String validity = validate(address, userID, password);
        if( !validity.equals(SUCCESS) )
        {
            stats.addError(validity);
            return false;
        }
        return true;
    }

    /**
     * Validate.
     *
     * @param address the address
     * @param userID the user id
     * @param password the password
     * @return the string
     */
    public static String validate(String address, String userID, String password) {
        URLConnection conn;
        SyndFeed feed;
        String response = FAILURE;
        boolean resp = false;
        try {
            if (address != null) {
                conn = new URL(address).openConnection();
                Logger.info(CLASS_ID, "Validating RSS URL : " + address, null);
                if (conn != null) {
                    if (userID != null && password != null && !userID.isEmpty() && !password.isEmpty()) {
                        Logger.debug(CLASS_ID, "setting user credentials", null);
                        String credentials = userID + ":" + password;
                        String encoding = new sun.misc.BASE64Encoder()
                                .encode(credentials.getBytes());
                        conn.setRequestProperty("Authorization", "Basic "
                                + encoding);
                    }
                    SyndFeedInput input = new SyndFeedInput();
                    feed = input.build(new XmlReader(conn));
                    if (feed != null) {
                        resp = true;
                    }
                }

            }
        } catch (Exception e) {
            Logger.warning(CLASS_ID, "Rejected invalid RSS URL : " + address, e);
        }

        if (resp) {
            response = SUCCESS;
        }

        return response;
    }

    /* 
     * Get the updated feeds after the last updated time stamp
     */
    @Override
    public FeedXtraxnStats getUpdates(List<ChannelFeedData> feeds, Map<String, String> feedTimeStamps)
            throws Exception {

        FeedXtraxnStats stats = new FeedXtraxnStats();
        stats.setStartTime(new Date());
        stats.setThreadName(Thread.currentThread().getName());
        stats.setLastExtrxnTime(lastAccessedTimeStamp);
        stats.setFeedType(FeedType.RSS);
        stats.setFeedName(getAddress());

        int noOfFeeds = 0;
        Date urlPublishedDate = new Date(0); // Dafaulting to EUPOCH so we
        // dont pick unwanted feed
        // data
        try {

            if (feed == null) { // We've already connected. So do only if needed
                URLConnection urlConn = new URL(address).openConnection();
                if (urlConn != null) {
                    if (userID != null && password != null && !userID.isEmpty() && !password.isEmpty()) {
                        Logger.debug(CLASS_ID, "setting user credentials", null);
                        String credentials = userID + ":" + password;
                        String encoding = new sun.misc.BASE64Encoder()
                                .encode(credentials.getBytes());
                        urlConn.setRequestProperty("Authorization", "Basic "
                                + encoding);
                    }
                    SyndFeedInput input = new SyndFeedInput();
                    feed = input.build(new XmlReader(urlConn));
                }
            }

            urlPublishedDate = feed.getPublishedDate(); // Use the URLs published date as default publish date for all the feeds within

            Long maxFeedTimestamp = new Long(0);
            List list = feed.getEntries();
            noOfFeeds = list.size();

            Logger.info(CLASS_ID, "Got " + noOfFeeds+ " feeds from the url " + address, null);

            if (list.size() != 0) {

                String strLastTimestamp = getLastAccessedTimestamp();
                Long lastFeedTimestamp = new Long(0);
                if (strLastTimestamp != null) {
                    lastFeedTimestamp = Long.valueOf(strLastTimestamp);
                }
                Logger.info( CLASS_ID, "Last accessed time : " +
                        RSSFeedAggregatorInfolet.dateFormatter.format(new Date(lastFeedTimestamp)), null);

                for (int i = 0; i < noOfFeeds; i++) {
                    try {
                        SyndEntry entry = (SyndEntry) list.get(i);

                        // Discard those feeds which have already been read.
                        long feedTimestamp = new Long(0);
                        Date sentDate = entry.getPublishedDate();
                        if (sentDate == null) {
                            sentDate = new Date();
                        }
                        long publishedDate = sentDate.getTime();
                        Long updatedDate = (entry.getUpdatedDate() != null) ? entry
                                .getUpdatedDate().getTime() : null;
                        if (publishedDate > -1) {
                            feedTimestamp = publishedDate;
                        } else if (updatedDate != null) {
                            feedTimestamp = updatedDate;
                        } else if (urlPublishedDate != null) {
                            feedTimestamp = urlPublishedDate.getTime();
                        }

                        if (lastFeedTimestamp != null && feedTimestamp > lastFeedTimestamp) {
                            feeds.add(new RSSFeedData(address, userID, entry, urlPublishedDate,structuredContentInfoletId,
                                        saveAttachment, channelID, sentDate, sourceID));
                            if (maxFeedTimestamp < feedTimestamp) {
                                maxFeedTimestamp = feedTimestamp;
                            }
                            stats.incrementedAccepted();
                        } else {
                            Logger.warning(CLASS_ID, "REJECTED feed update : " + entry.getTitle() + "  published@ " +
                                           RSSFeedAggregatorInfolet.dateFormatter.format(new Date( feedTimestamp)) + "  lastFeedTimestamp : "
                                            + RSSFeedAggregatorInfolet.dateFormatter.format(new Date(lastFeedTimestamp)), null);
                            stats.incrementedValidRejections();
                        }
                    } catch (Exception e) {
                        Logger.error(CLASS_ID, "Error reading Feed entry["
                                + i + "] \nError Msg :: " + e.getMessage(),
                                e);
                        stats.setStatus(FeedXtraxnStats.ExtractionStatus.PARTIAL);
                        stats.incrementedAccepted();
                        stats.addError(e.getMessage());
                        continue; // continue reading other updates
                    }
                }

                if (maxFeedTimestamp > 0) {
                    feedTimeStamps.put(getAddress(),
                            maxFeedTimestamp.toString()); // Update URL
                    // timestamp as neccessary
                }
                stats.setStatus(FeedXtraxnStats.ExtractionStatus.PASS); // Set success code
            }
        } catch (Exception e) {
            Logger.error(CLASS_ID,
                    "Error In getFeeds Method While Getting RSS. \n Message "
                            + e.getMessage(), e);
        } finally {
            stats.setEndTime(new Date());
            stats.setTotalFound(noOfFeeds);
        }

        return stats;
    }

    @Override
    public void close() {
    }

}
