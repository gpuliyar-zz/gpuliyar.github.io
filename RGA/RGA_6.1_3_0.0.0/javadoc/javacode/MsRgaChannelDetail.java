package com.metricstream.systemi.jpa.entity;

import java.io.Serializable;

import javax.persistence.*;

import com.metricstream.systemi.jpa.util.JPAConstants;

import java.util.Date;


/**
 * The persistent class for the MS_RGA_CHANNEL_DETAILS database table.
 * 
 */
@Entity
@Table(name=JPAConstants.CHANNEL_DETAILS)
@NamedQuery(name="MsRgaChannelDetail.findAll", query="SELECT m FROM MsRgaChannelDetail m")
public class MsRgaChannelDetail implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The active. */
	private String active;

	/** The channel id. */
	@Id
	@Column(name=JPAConstants.CHANNEL_ID)
	private String channelId;

	/** The created by. */
	@Column(name=JPAConstants.CREATED_BY)
	private String createdBy;

	/** The created on. */
	@Temporal(TemporalType.DATE)
	@Column(name=JPAConstants.CREATED_ON)
	private Date createdOn;

	/** The email from. */
	@Column(name=JPAConstants.EMAIL_FROM)
	private String emailFrom;

	/** The keywords. */
	private String keywords;

	/** The modified by. */
	@Column(name=JPAConstants.MODIFIED_BY)
	private String modifiedBy;

	/** The modified on. */
	@Temporal(TemporalType.DATE)
	@Column(name=JPAConstants.MODIFIED_ON)
	private Date modifiedOn;

	/** The queue name. */
	@Column(name=JPAConstants.QUEUE_NAME)
	private String queueName;

	/** The queue url. */
	@Column(name=JPAConstants.QUEUE_URL)
	private String queueUrl;

	/** The save attachments. */
	@Column(name=JPAConstants.SAVE_ATTACHMENTS)
	private String saveAttachments;

	/** The scr password. */
	@Column(name=JPAConstants.SCR_PASSWORD)
	private String scrPassword;

	/** The scr user name. */
	@Column(name=JPAConstants.SCR_USER_NAME)
	private String scrUserName;

	/** The server add url. */
	@Column(name=JPAConstants.SERVER_ADD_URL)
	private String serverAddUrl;

	/** The server parameters. */
	@Column(name=JPAConstants.SERVER_PARAMETERS)
	private String serverParameters;

	/** The source description. */
	@Column(name=JPAConstants.SOURCE_DESCRIPTION)
	private String sourceDescription;

	/** The source id. */
	@Column(name=JPAConstants.SOURCE_ID)
	private String sourceId;

	/** The source type. */
	@Column(name=JPAConstants.SOURCE_TYPE)
	private String sourceType;

	/** The struct cont hand infolet. */
	@Column(name=JPAConstants.STRUCT_CONT_HAND_INFOLET)
	private String structContHandInfolet;


	/**
	 * Instantiates a new ms rga channel detail.
	 */
	public MsRgaChannelDetail() {
	}

	/**
	 * Gets the active.
	 *
	 * @return the active
	 */
	public String getActive() {
		return this.active;
	}

	/**
	 * Sets the active.
	 *
	 * @param active the new active
	 */
	public void setActive(String active) {
		this.active = active;
	}

	/**
	 * Gets the channel id.
	 *
	 * @return the channel id
	 */
	public String getChannelId() {
		return this.channelId;
	}

	/**
	 * Sets the channel id.
	 *
	 * @param channelId the new channel id
	 */
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	/**
	 * Gets the created by.
	 *
	 * @return the created by
	 */
	public String getCreatedBy() {
		return this.createdBy;
	}

	/**
	 * Sets the created by.
	 *
	 * @param createdBy the new created by
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * Gets the created on.
	 *
	 * @return the created on
	 */
	public Date getCreatedOn() {
		return this.createdOn;
	}

	/**
	 * Sets the created on.
	 *
	 * @param createdOn the new created on
	 */
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	/**
	 * Gets the email from.
	 *
	 * @return the email from
	 */
	public String getEmailFrom() {
		return this.emailFrom;
	}

	/**
	 * Sets the email from.
	 *
	 * @param emailFrom the new email from
	 */
	public void setEmailFrom(String emailFrom) {
		this.emailFrom = emailFrom;
	}

	/**
	 * Gets the keywords.
	 *
	 * @return the keywords
	 */
	public String getKeywords() {
		return this.keywords;
	}

	/**
	 * Sets the keywords.
	 *
	 * @param keywords the new keywords
	 */
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	/**
	 * Gets the modified by.
	 *
	 * @return the modified by
	 */
	public String getModifiedBy() {
		return this.modifiedBy;
	}

	/**
	 * Sets the modified by.
	 *
	 * @param modifiedBy the new modified by
	 */
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	/**
	 * Gets the modified on.
	 *
	 * @return the modified on
	 */
	public Date getModifiedOn() {
		return this.modifiedOn;
	}

	/**
	 * Sets the modified on.
	 *
	 * @param modifiedOn the new modified on
	 */
	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	/**
	 * Gets the queue name.
	 *
	 * @return the queue name
	 */
	public String getQueueName() {
		return this.queueName;
	}

	/**
	 * Sets the queue name.
	 *
	 * @param queueName the new queue name
	 */
	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

	/**
	 * Gets the queue url.
	 *
	 * @return the queue url
	 */
	public String getQueueUrl() {
		return this.queueUrl;
	}

	/**
	 * Sets the queue url.
	 *
	 * @param queueUrl the new queue url
	 */
	public void setQueueUrl(String queueUrl) {
		this.queueUrl = queueUrl;
	}

	/**
	 * Gets the save attachments.
	 *
	 * @return the save attachments
	 */
	public String getSaveAttachments() {
		return this.saveAttachments;
	}

	/**
	 * Sets the save attachments.
	 *
	 * @param saveAttachments the new save attachments
	 */
	public void setSaveAttachments(String saveAttachments) {
		this.saveAttachments = saveAttachments;
	}

	/**
	 * Gets the scr password.
	 *
	 * @return the scr password
	 */
	public String getScrPassword() {
		return this.scrPassword;
	}

	/**
	 * Sets the scr password.
	 *
	 * @param scrPassword the new scr password
	 */
	public void setScrPassword(String scrPassword) {
		this.scrPassword = scrPassword;
	}

	/**
	 * Gets the scr user name.
	 *
	 * @return the scr user name
	 */
	public String getScrUserName() {
		return this.scrUserName;
	}

	/**
	 * Sets the scr user name.
	 *
	 * @param scrUserName the new scr user name
	 */
	public void setScrUserName(String scrUserName) {
		this.scrUserName = scrUserName;
	}

	/**
	 * Gets the server add url.
	 *
	 * @return the server add url
	 */
	public String getServerAddUrl() {
		return this.serverAddUrl;
	}

	/**
	 * Sets the server add url.
	 *
	 * @param serverAddUrl the new server add url
	 */
	public void setServerAddUrl(String serverAddUrl) {
		this.serverAddUrl = serverAddUrl;
	}

	/**
	 * Gets the server parameters.
	 *
	 * @return the server parameters
	 */
	public String getServerParameters() {
		return this.serverParameters;
	}

	/**
	 * Sets the server parameters.
	 *
	 * @param serverParameters the new server parameters
	 */
	public void setServerParameters(String serverParameters) {
		this.serverParameters = serverParameters;
	}

	/**
	 * Gets the source description.
	 *
	 * @return the source description
	 */
	public String getSourceDescription() {
		return this.sourceDescription;
	}

	/**
	 * Sets the source description.
	 *
	 * @param sourceDescription the new source description
	 */
	public void setSourceDescription(String sourceDescription) {
		this.sourceDescription = sourceDescription;
	}

	/**
	 * Gets the source id.
	 *
	 * @return the source id
	 */
	public String getSourceId() {
		return this.sourceId;
	}

	/**
	 * Sets the source id.
	 *
	 * @param sourceId the new source id
	 */
	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	/**
	 * Gets the source type.
	 *
	 * @return the source type
	 */
	public String getSourceType() {
		return this.sourceType;
	}

	/**
	 * Sets the source type.
	 *
	 * @param sourceType the new source type
	 */
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	/**
	 * Gets the struct cont hand infolet.
	 *
	 * @return the struct cont hand infolet
	 */
	public String getStructContHandInfolet() {
		return this.structContHandInfolet;
	}

	/**
	 * Sets the struct cont hand infolet.
	 *
	 * @param structContHandInfolet the new struct cont hand infolet
	 */
	public void setStructContHandInfolet(String structContHandInfolet) {
		this.structContHandInfolet = structContHandInfolet;
	}

}