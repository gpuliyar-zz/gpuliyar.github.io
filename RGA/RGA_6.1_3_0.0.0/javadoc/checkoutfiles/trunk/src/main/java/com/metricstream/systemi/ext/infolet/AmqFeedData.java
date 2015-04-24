package com.metricstream.systemi.ext.infolet;

import java.util.Date;

import com.msi.grcintelligence.util.AggregationContentData;

/**
 * This class represents the feed records in Active MQ
 *
 * @author asif.u
 */
public class AmqFeedData implements ChannelFeedData {

	/** The feed url. */
	private final String feedUrl;
	
	/** The feed content. */
	private AggregationContentData feedContent;
	
	/** The structured content infolet id. */
	private String structuredContentInfoletId;
	
	/** The save attachment. */
	private String saveAttachment;
	
	/** The user id. */
	private String userID;
	
	/** The sent date. */
	private Date sentDate;
	
	/** The channel id. */
	private String channelId;
	
	/** The msg id. */
	private String msgId;
	
	/** The activation code. */
	private String activationCode;
	
	/** The source id. */
	private String sourceId;

	/**
	 * Sets the activation code.
	 *
	 * @param activationCode the new activation code
	 */
	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}

	/**
	 * Instantiates a new amq feed data.
	 *
	 * @param feedUrl the feed url
	 * @param userID the user id
	 * @param feed the feed
	 * @param urlDate the url date
	 * @param structuredContentInfoletId the structured content infolet id
	 * @param saveAttachment the save attachment
	 * @param channelId the channel id
	 * @param sentDate the sent date
	 * @param sourceId the source id
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public AmqFeedData(String feedUrl, String userID, AggregationContentData feed,
			Date urlDate, String structuredContentInfoletId,
			String saveAttachment, String channelId, Date sentDate, String sourceId)
			throws java.io.IOException {

		this.feedUrl = feedUrl;
		this.userID = userID;
		this.structuredContentInfoletId = structuredContentInfoletId;
		this.saveAttachment = saveAttachment;
		this.channelId = channelId;
		this.sentDate = sentDate;
		this.feedContent = feed;
		this.sourceId = sourceId;
	}

	/**
	 * Gets the server.
	 * @return the feedUrl
	 */
	@Override
	public String getServer() {
		return feedUrl;
	}

	/**
	 * Gets the sender.
	 * @return the null
	 */
	@Override
	public String getSender() {
		return null;
	}

	/**
	 * Gets the recipient.
	 * @return the null
	 */
	@Override
	public String getRecipient() {
		return null;
	}


	/**
	 * Gets the subject.
	 * @return the subject
	 */
	@Override
	public String getSubject() {
		if(null!=feedContent && null!=feedContent.getContentFeedTitle())
			return feedContent.getContentFeedTitle().trim();
		else
			return "";
	}

	/**
	 * Gets the body.
	 * @return the body
	 */
	@Override
	public String getBody() {
		if(null!=feedContent && null!=feedContent.getDescription())
			return feedContent.getDescription().trim();
		else
			return "";
	}
	
	/**
	 * Gets the attachment ids.
	 * @return the null
	 */
	@Override
	public String getAttachmentIDs() {
		return null;
	}

	/**
	 * Gets the msg UID.
	 * @return the null
	 */
	@Override
	public String getMsgUID() {
		return null;
	}

	/**
	 * Gets the structured content infolet id.
	 * @return structuredContentInfoletId
	 */
	@Override
	public String getStructuredContentInfoletId() {
		return structuredContentInfoletId;
	}

	/**
	 * Gets the saved attachment.
	 * @return the saveAttachment
	 */
	@Override
	public String getSaveAttachment() {
		return saveAttachment;
	}

	/**
	 * Gets the channel id.
	 * @return the channelId
	 */
	@Override
	public String getChannelId() {
		return channelId;
	}

	/**
	 * Gets the sent date.
	 * @return date
	 */
	@Override
	public Date getSentDate() {
		return new Date();
	}

	/**
	 * Gets the file name.
	 * @return the null
	 */
	@Override
	public String getFileName() {
		return null;
	}

	/**
	 * Gets the activation Code.
	 * @return the activationCode
	 */
	@Override
	public String getActivationCode() {
		return activationCode;
	}

	/**
	 * Gets the source id.
	 * @return the sourceId
	 */
	@Override
	public String getSourceId() {
		return sourceId;
	}

}
