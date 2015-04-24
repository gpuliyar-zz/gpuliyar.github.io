package com.metricstream.systemi.jpa.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.metricstream.systemi.jpa.util.JPAConstants;

/**
 * The persistent class for the MS_RGA_AGGREGATOR database table.
 * @author asif.u
 */
@Entity
@Table(name=JPAConstants.AGGREGATOR)
public class MsRgaAggregator extends EntityDomain implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6504753324371951116L;

	/** The srk pk. */
	@Column(name=JPAConstants.SRC_PK)
	private String srkPk;
	
	/** The channel id. */
	@Column(name=JPAConstants.CHANNEL_ID)
	private String channelId;
	
	/** The strut conent id. */
	@Column(name=JPAConstants.STRUCTURED_CONTENT_ID)
	private int strutConentId;
	
	
	/** The feed sent date. */
	@Column(name=JPAConstants.FEED_SENT_DATE)
	private Date feedSentDate;
	
	/** The message uid. */
	@Column(name=JPAConstants.MSG_UID)
	private String messageUID;
	
	/** The feed datd id. */
	@Column(name=JPAConstants.FEED_DATA_ID)
	private int feedDatdId;
	
	/** The processed flag. */
	@Column(name=JPAConstants.PROCESSED_FLAG)
	private String processedFlag;
	
	/** The attachment. */
	@Column(name=JPAConstants.ATTACHMENT)
	private String attachment;
	
	/** The body. */
	@Column(name=JPAConstants.BODY )
	private String body;
	
	/** The subject. */
	@Column(name=JPAConstants.SUBJECT)
	private String subject;
	
	/** The recipient. */
	@Column(name=JPAConstants.RECIPIENT)
	private String recipient;
	
	/** The sender. */
	@Column(name=JPAConstants.SENDER)
	private String sender;
	
	/** The server. */
	@Column(name=JPAConstants.SERVER)
	private String server;
	
	/** The creation date. */
	@Column(name=JPAConstants.CREATION_DATE)
	private Date creationDate;
	
	/** The feed sequence. */
	@Id
	@Column(name=JPAConstants.FEED_SEQUENCE)
	private int feedSequence;

	/**
	 * Gets the srk pk.
	 *
	 * @return the srkPk
	 */
	public String getSrkPk() {
		return srkPk;
	}

	/**
	 * Sets the srk pk.
	 *
	 * @param srkPk the srkPk to set
	 */
	public void setSrkPk(String srkPk) {
		this.srkPk = srkPk;
	}

	/**
	 * Gets the channel id.
	 *
	 * @return the channelId
	 */
	public String getChannelId() {
		return channelId;
	}

	/**
	 * Sets the channel id.
	 *
	 * @param channelId the channelId to set
	 */
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	/**
	 * Gets the strut conent id.
	 *
	 * @return the strutConentId
	 */
	public int getStrutConentId() {
		return strutConentId;
	}

	/**
	 * Sets the strut conent id.
	 *
	 * @param strutConentId the strutConentId to set
	 */
	public void setStrutConentId(int strutConentId) {
		this.strutConentId = strutConentId;
	}

	/**
	 * Gets the feed sent date.
	 *
	 * @return the feedSentDate
	 */
	public Date getFeedSentDate() {
		return feedSentDate;
	}

	/**
	 * Sets the feed sent date.
	 *
	 * @param feedSentDate the feedSentDate to set
	 */
	public void setFeedSentDate(Date feedSentDate) {
		this.feedSentDate = feedSentDate;
	}

	/**
	 * Gets the message uid.
	 *
	 * @return the messageUID
	 */
	public String getMessageUID() {
		return messageUID;
	}

	/**
	 * Sets the message uid.
	 *
	 * @param messageUID the messageUID to set
	 */
	public void setMessageUID(String messageUID) {
		this.messageUID = messageUID;
	}

	/**
	 * Gets the feed datd id.
	 *
	 * @return the feedDatdId
	 */
	public int getFeedDatdId() {
		return feedDatdId;
	}

	/**
	 * Sets the feed datd id.
	 *
	 * @param feedDatdId the feedDatdId to set
	 */
	public void setFeedDatdId(int feedDatdId) {
		this.feedDatdId = feedDatdId;
	}

	/**
	 * Gets the processed flag.
	 *
	 * @return the processedFlag
	 */
	public String getProcessedFlag() {
		return processedFlag;
	}

	/**
	 * Sets the processed flag.
	 *
	 * @param processedFlag the processedFlag to set
	 */
	public void setProcessedFlag(String processedFlag) {
		this.processedFlag = processedFlag;
	}

	/**
	 * Gets the attachment.
	 *
	 * @return the attachment
	 */
	public String getAttachment() {
		return attachment;
	}

	/**
	 * Sets the attachment.
	 *
	 * @param attachment the attachment to set
	 */
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	/**
	 * Gets the body.
	 *
	 * @return the body
	 */
	public String getBody() {
		return body;
	}

	/**
	 * Sets the body.
	 *
	 * @param body the body to set
	 */
	public void setBody(String body) {
		this.body = body;
	}

	/**
	 * Gets the subject.
	 *
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * Sets the subject.
	 *
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * Gets the recipient.
	 *
	 * @return the recipient
	 */
	public String getRecipient() {
		return recipient;
	}

	/**
	 * Sets the recipient.
	 *
	 * @param recipient the recipient to set
	 */
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	/**
	 * Gets the sender.
	 *
	 * @return the sender
	 */
	public String getSender() {
		return sender;
	}

	/**
	 * Sets the sender.
	 *
	 * @param sender the sender to set
	 */
	public void setSender(String sender) {
		this.sender = sender;
	}

	/**
	 * Gets the server.
	 *
	 * @return the server
	 */
	public String getServer() {
		return server;
	}

	/**
	 * Sets the server.
	 *
	 * @param server the server to set
	 */
	public void setServer(String server) {
		this.server = server;
	}

	/**
	 * Gets the creation date.
	 *
	 * @return the creationDate
	 */
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * Sets the creation date.
	 *
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * Gets the feed sequence.
	 *
	 * @return the feedSequence
	 */
	public int getFeedSequence() {
		return feedSequence;
	}

	/**
	 * Sets the feed sequence.
	 *
	 * @param feedSequence the feedSequence to set
	 */
	public void setFeedSequence(int feedSequence) {
		this.feedSequence = feedSequence;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return super.hashCode();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		
		return super.equals(obj);
	}
	
	
	
	
}
