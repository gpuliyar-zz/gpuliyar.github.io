package com.metricstream.systemi.ext.infolet;

import java.util.List;
import java.util.Map;

import com.metricstream.log.Logger;

/**
 * The Feed Class.
 */
public abstract class Feed {

	/**
	 * The Enum FeedType.
	 */
	enum FeedType {
		
		/** The rss. */
		RSS,
		
		/** The email. */
		EMAIL,
		
		/** The amq. */
		AMQ;
	}

	/** The Constant CLASS_ID. */
	private static final String CLASS_ID = "Feed";
    
    /** The Constant FAILURE. */
    public static final String FAILURE = "failure";
    
    /** The Constant SUCCESS. */
    public static final String SUCCESS = "success";

	/** The source id. */
	public String address, userID, password, propStr, channelID, storeID,
			lastAccessedTimeStamp, structuredContentInfoletId, saveAttachment,queueURL,sourceID,emailType;

	/** The feed type. */
	FeedType feedType;

	/**
	 * Gets the updates.
	 *
	 * @param msgs the msgs
	 * @param feedTimeStamps the feed time stamps
	 * @return the updates
	 * @throws Exception the exception
	 */
	abstract FeedXtraxnStats getUpdates(List<ChannelFeedData> msgs, Map<String, String> feedTimeStamps)
			throws Exception;

	/**
	 * Validate feed
	 *
	 * @param stats the stats
	 * @return true, if successful
	 */
	abstract boolean validate(FeedXtraxnStats stats);

	/**
	 * Close.
	 *
	 * @throws Exception the exception
	 */
	abstract void close() throws Exception;

	/**
	 * Instantiates a new feed.
	 *
	 * @param feedUrl the feed url
	 * @param lastAccessedTimeStamp the last accessed time stamp
	 * @param userID the user id
	 * @param password the password
	 * @param propStr the prop str
	 * @param structuredContentInfoletId the structured content infolet id
	 * @param saveAttachment the save attachment
	 * @param channelID the channel id
	 * @param sourceID the source id
	 * @throws Exception the exception
	 */
	Feed(String feedUrl, String lastAccessedTimeStamp, String userID,
			String password, String propStr, String structuredContentInfoletId,
			String saveAttachment, String channelID, String sourceID, String emailType) throws Exception {
		if ((feedUrl == null) || (feedUrl.length() == 0)) {
			throw new Exception("RSSUrlConns: Missing Feed URL.");
		}

		this.address = feedUrl;
		this.lastAccessedTimeStamp = lastAccessedTimeStamp;
		this.userID = userID;
		this.propStr = propStr;
		this.password = password;
		this.structuredContentInfoletId = structuredContentInfoletId;
		this.saveAttachment = saveAttachment;
		this.channelID = channelID;
		this.sourceID = sourceID;
		this.emailType = emailType;

		Logger.debug(CLASS_ID, "inside with " + feedUrl + "," + userID + ","
				+ password + "," + propStr + "," + lastAccessedTimeStamp + ","
				+ structuredContentInfoletId + "," + saveAttachment + ","
				+ channelID + "," + sourceID + ", " + emailType, null);
	}

	@Override
	public String toString() {
		return address + "[" + userID + "/" + password + "]";
	}

	/**
	 * Gets the prop str.
	 *
	 * @return the prop str
	 */
	String getPropStr() {
		return propStr;
	}

	/**
	 * Get serverURL.
	 *
	 * @return server url
	 */
	String getAddress() {
		return address;
	}

	/**
	 * Get user ID.
	 *
	 * @return user ID
	 */
	String getUserID() {
		return userID;
	}

	/**
	 * Get password.
	 *
	 * @return password
	 */
	String getPassword() {
		return password;
	}

	
	
	/**
	 * Gets the emailType
	 * @return the emailType
	 */
	public String getEmailType() {
		return this.emailType;
	}

	/**
	 * Sets the emailType
	 * @param emailType the emailType to set
	 */
	public void setEmailType(String emailType) {
		this.emailType = emailType;
	}

	/**
	 * Gets the last accessed timestamp.
	 *
	 * @return the last accessed timestamp
	 */
	String getLastAccessedTimestamp() {
		return lastAccessedTimeStamp;
	}

	/**
	 * Gets the structured content infolet id.
	 *
	 * @return the structured content infolet id
	 */
	public String getStructuredContentInfoletId() {
		return structuredContentInfoletId;
	}

	/**
	 * Gets the save attachment.
	 *
	 * @return the save attachment
	 */
	public String getSaveAttachment() {
		return saveAttachment;
	}

	/**
	 * Gets the channel id.
	 *
	 * @return the channel id
	 */
	public String getChannelID() {
		return channelID;
	}

	/**
	 * Gets the source id.
	 *
	 * @return the source id
	 */
	public String getSourceID() {
		return sourceID;
	}

	/**
	 * Sets the source id.
	 *
	 * @param sourceID the new source id
	 */
	public void setSourceID(String sourceID) {
		this.sourceID = sourceID;
	}

	/**
	 * Gets the queue url.
	 *
	 * @return the queue url
	 */
	public String getQueueURL() {
		return queueURL;
	}

	/**
	 * Sets the queue url.
	 *
	 * @param queueURL the new queue url
	 */
	public void setQueueURL(String queueURL) {
		this.queueURL = queueURL;
	}

	/**
	 * Sets the channel id.
	 *
	 * @param channelID the new channel id
	 */
	public void setChannelID(String channelID) {
		this.channelID = channelID;
	};
}

/**
 * Feed Queue
 * @author manish.svk
 *
 */
class FeedQueue {
	private Feed feeds[];
	private int counter = 0;

	FeedQueue(Feed feeds[]) {
		this.feeds = feeds.clone();
	}

	/**
	 * Gets the next feed in the queue
	 * @return next feed
	 */
	synchronized Feed getNextFeed() {
		if ((feeds == null) || (counter >= feeds.length)) {
			return null;
		}

		Feed f = feeds[counter];
		counter++;
		return f;
	}
}