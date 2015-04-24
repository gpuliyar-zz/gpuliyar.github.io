package com.metricstream.systemi.jpa.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.metricstream.systemi.jpa.util.JPAConstants;

/**
 * The Class ChannelDetailsView.
 * @author asif.u
 */
@Entity
@Table(name=JPAConstants.CHANNEL_DETAILS_VIEW)
public class MsRgaChannelDetailsView {

	/** The email from. */
	@Column(name=JPAConstants.EMAIL_FROM)
	private String emailFrom;
	
	/** The keywords. */
	@Column(name=JPAConstants.KEYWORDS)
	private String keywords;
	
	/** The source desc. */
	@Column(name=JPAConstants.SOURCE_DESCRIPTION)
	private char sourceDesc;
	
	/** The active. */
	@Column(name=JPAConstants.ACTIVE)
	private String active;
	
	/** The created on. */
	@Column(name=JPAConstants.CREATED_ON)
	private Date createdOn;
	
	/** The created by. */
	@Column(name=JPAConstants.CREATED_BY)
	private String createdBy;
	
	/** The modified on. */
	@Column(name=JPAConstants.MODIFIED_ON)
	private Date modifiedOn;
	
	/** The modified by. */
	@Column(name=JPAConstants.MODIFIED_BY)
	private String modifiedBy;
	
	/** The server parameter. */
	@Column(name=JPAConstants.SERVER_PARAMETERS)
	private String serverParameter;
	
	/** The conditional infolet. */
	@Column(name=JPAConstants.STRUCT_CONT_HAND_INFOLET)
	private String conditionalInfolet;
	
	/** The save attachments. */
	@Column(name=JPAConstants.SAVE_ATTACHMENTS)
	private String saveAttachments;
	
	/** The queue name. */
	@Column(name=JPAConstants.QUEUE_NAME)
	private String queueName;
	
	/** The queue url. */
	@Column(name=JPAConstants.QUEUE_URL)
	private String queueUrl;
	
	/** The channel id. */
	@Column(name=JPAConstants.CHANNEL_ID)
	private String channelId;
	
	/** The source id. */
	@Id
	@Column(name=JPAConstants.SOURCE_ID)
	private String sourceId;
	
	/** The source type. */
	@Column(name=JPAConstants.SOURCE_TYPE)
	private String sourceType;
	
	/** The server url. */
	@Column(name=JPAConstants.SERVER_ADD_URL)
	private String serverUrl;
	
	/** The src user name. */
	@Column(name=JPAConstants.SCR_USER_NAME)
	private String srcUserName;
	
	/** The src password. */
	@Column(name=JPAConstants.SCR_PASSWORD)
	private String srcPassword;
	
	/**
	 * Gets the email from.
	 *
	 * @return the emailFrom
	 */
	public String getEmailFrom() {
		return emailFrom;
	}
	
	/**
	 * Sets the email from.
	 *
	 * @param emailFrom the emailFrom to set
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
		return keywords;
	}
	
	/**
	 * Sets the keywords.
	 *
	 * @param keywords the keywords to set
	 */
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	
	/**
	 * Gets the source desc.
	 *
	 * @return the sourceDesc
	 */
	public char getSourceDesc() {
		return sourceDesc;
	}
	
	/**
	 * Sets the source desc.
	 *
	 * @param sourceDesc the sourceDesc to set
	 */
	public void setSourceDesc(char sourceDesc) {
		this.sourceDesc = sourceDesc;
	}
	
	/**
	 * Gets the active.
	 *
	 * @return the active
	 */
	public String getActive() {
		return active;
	}
	
	/**
	 * Sets the active.
	 *
	 * @param active the active to set
	 */
	public void setActive(String active) {
		this.active = active;
	}
	
	/**
	 * Gets the created on.
	 *
	 * @return the createdOn
	 */
	public Date getCreatedOn() {
		return createdOn;
	}
	
	/**
	 * Sets the created on.
	 *
	 * @param createdOn the createdOn to set
	 */
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	
	/**
	 * Gets the created by.
	 *
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}
	
	/**
	 * Sets the created by.
	 *
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	/**
	 * Gets the modified on.
	 *
	 * @return the modifiedOn
	 */
	public Date getModifiedOn() {
		return modifiedOn;
	}
	
	/**
	 * Sets the modified on.
	 *
	 * @param modifiedOn the modifiedOn to set
	 */
	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}
	
	/**
	 * Gets the modified by.
	 *
	 * @return the modifiedBy
	 */
	public String getModifiedBy() {
		return modifiedBy;
	}
	
	/**
	 * Sets the modified by.
	 *
	 * @param modifiedBy the modifiedBy to set
	 */
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	
	/**
	 * Gets the server parameter.
	 *
	 * @return the serverParameter
	 */
	public String getServerParameter() {
		return serverParameter;
	}
	
	/**
	 * Sets the server parameter.
	 *
	 * @param serverParameter the serverParameter to set
	 */
	public void setServerParameter(String serverParameter) {
		this.serverParameter = serverParameter;
	}
	
	/**
	 * Gets the conditional infolet.
	 *
	 * @return the conditionalInfolet
	 */
	public String getConditionalInfolet() {
		return conditionalInfolet;
	}
	
	/**
	 * Sets the conditional infolet.
	 *
	 * @param conditionalInfolet the conditionalInfolet to set
	 */
	public void setConditionalInfolet(String conditionalInfolet) {
		this.conditionalInfolet = conditionalInfolet;
	}
	
	/**
	 * Gets the save attachments.
	 *
	 * @return the saveAttachments
	 */
	public String getSaveAttachments() {
		return saveAttachments;
	}
	
	/**
	 * Sets the save attachments.
	 *
	 * @param saveAttachments the saveAttachments to set
	 */
	public void setSaveAttachments(String saveAttachments) {
		this.saveAttachments = saveAttachments;
	}
	
	/**
	 * Gets the queue name.
	 *
	 * @return the queueName
	 */
	public String getQueueName() {
		return queueName;
	}
	
	/**
	 * Sets the queue name.
	 *
	 * @param queueName the queueName to set
	 */
	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}
	
	/**
	 * Gets the queue url.
	 *
	 * @return the queueUrl
	 */
	public String getQueueUrl() {
		return queueUrl;
	}
	
	/**
	 * Sets the queue url.
	 *
	 * @param queueUrl the queueUrl to set
	 */
	public void setQueueUrl(String queueUrl) {
		this.queueUrl = queueUrl;
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
	 * Gets the source id.
	 *
	 * @return the sourceId
	 */
	public String getSourceId() {
		return sourceId;
	}
	
	/**
	 * Sets the source id.
	 *
	 * @param sourceId the sourceId to set
	 */
	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}
	
	/**
	 * Gets the source type.
	 *
	 * @return the sourceType
	 */
	public String getSourceType() {
		return sourceType;
	}
	
	/**
	 * Sets the source type.
	 *
	 * @param sourceType the sourceType to set
	 */
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}
	
	/**
	 * Gets the server url.
	 *
	 * @return the serverUrl
	 */
	public String getServerUrl() {
		return serverUrl;
	}
	
	/**
	 * Sets the server url.
	 *
	 * @param serverUrl the serverUrl to set
	 */
	public void setServerUrl(String serverUrl) {
		this.serverUrl = serverUrl;
	}
	
	/**
	 * Gets the src user name.
	 *
	 * @return the srcUserName
	 */
	public String getSrcUserName() {
		return srcUserName;
	}
	
	/**
	 * Sets the src user name.
	 *
	 * @param srcUserName the srcUserName to set
	 */
	public void setSrcUserName(String srcUserName) {
		this.srcUserName = srcUserName;
	}
	
	/**
	 * Gets the src password.
	 *
	 * @return the srcPassword
	 */
	public String getSrcPassword() {
		return srcPassword;
	}
	
	/**
	 * Sets the src password.
	 *
	 * @param srcPassword the srcPassword to set
	 */
	public void setSrcPassword(String srcPassword) {
		this.srcPassword = srcPassword;
	} 
	
	
}
