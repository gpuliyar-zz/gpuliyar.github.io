package com.metricstream.systemi.jpa.entity;

import java.io.Serializable;

import javax.persistence.*;

import com.metricstream.systemi.jpa.util.JPAConstants;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the MS_RGA_SUBS_CHANNEL_CHN database table.
 * 
 */
@Entity
@Table(name=JPAConstants.SUBS_CHANNEL_CHN)
@NamedQuery(name="MsRgaSubsChannelChn.findAll", query="SELECT m FROM MsRgaSubsChannelChn m")
public class MsRgaSubsChannelChn implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The channel id. */
	@Column(name=JPAConstants.CHANNEL_ID)
	private String channelId;

	/** The channel name. */
	@Column(name=JPAConstants.CHANNEL_NAME)
	private String channelName;

	/** The chn created by. */
	@Column(name=JPAConstants.CHN_CREATED_BY)
	private String chnCreatedBy;

	/** The chn created on. */
	@Temporal(TemporalType.DATE)
	@Column(name=JPAConstants.CHN_CREATED_ON)
	private Date chnCreatedOn;

	/** The chn modified by. */
	@Column(name=JPAConstants.CHN_MODIFIED_BY)
	private String chnModifiedBy;

	/** The chn modified on. */
	@Temporal(TemporalType.DATE)
	@Column(name=JPAConstants.CHN_MODIFIED_ON)
	private Date chnModifiedOn;

	/** The custom field10. */
	@Column(name=JPAConstants.CUSTOM_FIELD10)
	private String customField10;

	/** The custom field6. */
	@Column(name=JPAConstants.CUSTOM_FIELD6)
	private String customField6;

	/** The custom field7. */
	@Column(name=JPAConstants.CUSTOM_FIELD7)
	private BigDecimal customField7;

	/** The custom field8. */
	@Column(name=JPAConstants.CUSTOM_FIELD8)
	private BigDecimal customField8;

	/** The custom field9. */
	@Temporal(TemporalType.DATE)
	@Column(name=JPAConstants.CUSTOM_FIELD9)
	private Date customField9;

	/** The dd status flag. */
	@Column(name=JPAConstants.DD_STATUS_FLAG)
	private String ddStatusFlag;

	/** The filter keywords. */
	@Column(name=JPAConstants.FILTER_KEYWORDS)
	private String filterKeywords;

	/** The notify by email. */
	@Column(name=JPAConstants.NOTIFY_BY_EMAIL)
	private String notifyByEmail;

	/** The object id. */
	@Column(name=JPAConstants.OBJECT_ID)
	private String objectId;

	/** The status. */
	private String status;

	/** The subscription id. */
	@Id
	@Column(name=JPAConstants.SUBSCRIPTION_ID)
	private String subscriptionId;

	/** The subscription status. */
	@Column(name=JPAConstants.SUBSCRIPTION_STATUS)
	private String subscriptionStatus;

	/**
	 * Instantiates a new ms rga subs channel chn.
	 */
	public MsRgaSubsChannelChn() {
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
	 * Gets the channel name.
	 *
	 * @return the channel name
	 */
	public String getChannelName() {
		return this.channelName;
	}

	/**
	 * Sets the channel name.
	 *
	 * @param channelName the new channel name
	 */
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	/**
	 * Gets the chn created by.
	 *
	 * @return the chn created by
	 */
	public String getChnCreatedBy() {
		return this.chnCreatedBy;
	}

	/**
	 * Sets the chn created by.
	 *
	 * @param chnCreatedBy the new chn created by
	 */
	public void setChnCreatedBy(String chnCreatedBy) {
		this.chnCreatedBy = chnCreatedBy;
	}

	/**
	 * Gets the chn created on.
	 *
	 * @return the chn created on
	 */
	public Date getChnCreatedOn() {
		return this.chnCreatedOn;
	}

	/**
	 * Sets the chn created on.
	 *
	 * @param chnCreatedOn the new chn created on
	 */
	public void setChnCreatedOn(Date chnCreatedOn) {
		this.chnCreatedOn = chnCreatedOn;
	}

	/**
	 * Gets the chn modified by.
	 *
	 * @return the chn modified by
	 */
	public String getChnModifiedBy() {
		return this.chnModifiedBy;
	}

	/**
	 * Sets the chn modified by.
	 *
	 * @param chnModifiedBy the new chn modified by
	 */
	public void setChnModifiedBy(String chnModifiedBy) {
		this.chnModifiedBy = chnModifiedBy;
	}

	/**
	 * Gets the chn modified on.
	 *
	 * @return the chn modified on
	 */
	public Date getChnModifiedOn() {
		return this.chnModifiedOn;
	}

	/**
	 * Sets the chn modified on.
	 *
	 * @param chnModifiedOn the new chn modified on
	 */
	public void setChnModifiedOn(Date chnModifiedOn) {
		this.chnModifiedOn = chnModifiedOn;
	}

	/**
	 * Gets the custom field10.
	 *
	 * @return the custom field10
	 */
	public String getCustomField10() {
		return this.customField10;
	}

	/**
	 * Sets the custom field10.
	 *
	 * @param customField10 the new custom field10
	 */
	public void setCustomField10(String customField10) {
		this.customField10 = customField10;
	}

	/**
	 * Gets the custom field6.
	 *
	 * @return the custom field6
	 */
	public String getCustomField6() {
		return this.customField6;
	}

	/**
	 * Sets the custom field6.
	 *
	 * @param customField6 the new custom field6
	 */
	public void setCustomField6(String customField6) {
		this.customField6 = customField6;
	}

	/**
	 * Gets the custom field7.
	 *
	 * @return the custom field7
	 */
	public BigDecimal getCustomField7() {
		return this.customField7;
	}

	/**
	 * Sets the custom field7.
	 *
	 * @param customField7 the new custom field7
	 */
	public void setCustomField7(BigDecimal customField7) {
		this.customField7 = customField7;
	}

	/**
	 * Gets the custom field8.
	 *
	 * @return the custom field8
	 */
	public BigDecimal getCustomField8() {
		return this.customField8;
	}

	/**
	 * Sets the custom field8.
	 *
	 * @param customField8 the new custom field8
	 */
	public void setCustomField8(BigDecimal customField8) {
		this.customField8 = customField8;
	}

	/**
	 * Gets the custom field9.
	 *
	 * @return the custom field9
	 */
	public Date getCustomField9() {
		return this.customField9;
	}

	/**
	 * Sets the custom field9.
	 *
	 * @param customField9 the new custom field9
	 */
	public void setCustomField9(Date customField9) {
		this.customField9 = customField9;
	}

	/**
	 * Gets the dd status flag.
	 *
	 * @return the dd status flag
	 */
	public String getDdStatusFlag() {
		return this.ddStatusFlag;
	}

	/**
	 * Sets the dd status flag.
	 *
	 * @param ddStatusFlag the new dd status flag
	 */
	public void setDdStatusFlag(String ddStatusFlag) {
		this.ddStatusFlag = ddStatusFlag;
	}

	/**
	 * Gets the filter keywords.
	 *
	 * @return the filter keywords
	 */
	public String getFilterKeywords() {
		return this.filterKeywords;
	}

	/**
	 * Sets the filter keywords.
	 *
	 * @param filterKeywords the new filter keywords
	 */
	public void setFilterKeywords(String filterKeywords) {
		this.filterKeywords = filterKeywords;
	}

	/**
	 * Gets the notify by email.
	 *
	 * @return the notify by email
	 */
	public String getNotifyByEmail() {
		return this.notifyByEmail;
	}

	/**
	 * Sets the notify by email.
	 *
	 * @param notifyByEmail the new notify by email
	 */
	public void setNotifyByEmail(String notifyByEmail) {
		this.notifyByEmail = notifyByEmail;
	}

	/**
	 * Gets the object id.
	 *
	 * @return the object id
	 */
	public String getObjectId() {
		return this.objectId;
	}

	/**
	 * Sets the object id.
	 *
	 * @param objectId the new object id
	 */
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getStatus() {
		return this.status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Gets the subscription id.
	 *
	 * @return the subscription id
	 */
	public String getSubscriptionId() {
		return this.subscriptionId;
	}

	/**
	 * Sets the subscription id.
	 *
	 * @param subscriptionId the new subscription id
	 */
	public void setSubscriptionId(String subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	/**
	 * Gets the subscription status.
	 *
	 * @return the subscription status
	 */
	public String getSubscriptionStatus() {
		return this.subscriptionStatus;
	}

	/**
	 * Sets the subscription status.
	 *
	 * @param subscriptionStatus the new subscription status
	 */
	public void setSubscriptionStatus(String subscriptionStatus) {
		this.subscriptionStatus = subscriptionStatus;
	}

}