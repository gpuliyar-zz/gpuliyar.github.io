package com.metricstream.systemi.jpa.entity;

import java.io.Serializable;

import javax.persistence.*;

import com.metricstream.systemi.jpa.util.JPAConstants;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the MS_RGA_CHANNEL_DTLS database table.
 * 
 */
@Entity
@Table(name=JPAConstants.CHANNEL_DTLS)
@NamedQuery(name="MsRgaChannelDtl.findAll", query="SELECT m FROM MsRgaChannelDtl m")
public class MsRgaChannelDtl implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The object id. */
	@Column(name=JPAConstants.OBJECT_ID)
	private String objectId;

	/** The channel created by. */
	@Column(name=JPAConstants.CHANNEL_CREATED_BY)
	private String channelCreatedBy;

	/** The channel created on. */
	@Temporal(TemporalType.DATE)
	@Column(name=JPAConstants.CHANNEL_CREATED_ON)
	private Date channelCreatedOn;

	/** The channel id. */
	@Id
	@Column(name=JPAConstants.CHANNEL_ID)
	private String channelId;

	/** The channel name. */
	@Column(name=JPAConstants.CHANNEL_NAME)
	private String channelName;

	/** The channel status. */
	@Column(name=JPAConstants.CHANNEL_STATUS)
	private String channelStatus;

	/** The channel type. */
	@Column(name=JPAConstants.CHANNEL_TYPE)
	private String channelType;

	/** The custom field1. */
	@Column(name=JPAConstants.CUSTOM_FIELD1)
	private String customField1;

	/** The custom field2. */
	@Column(name=JPAConstants.CUSTOM_FIELD2)
	private String customField2;

	/** The custom field3. */
	@Column(name=JPAConstants.CUSTOM_FIELD3)
	private BigDecimal customField3;

	/** The custom field4. */
	@Column(name=JPAConstants.CUSTOM_FIELD4)
	private BigDecimal customField4;

	/** The custom field5. */
	@Temporal(TemporalType.DATE)
	@Column(name=JPAConstants.CUSTOM_FIELD5)
	private Date customField5;

	/** The dd created by. */
	@Column(name=JPAConstants.DD_CREATED_BY)
	private String ddCreatedBy;

	/** The dd created on. */
	@Temporal(TemporalType.DATE)
	@Column(name=JPAConstants.DD_CREATED_ON)
	private Date ddCreatedOn;

	/** The dd edit flag. */
	@Column(name=JPAConstants.DD_EDIT_FLAG)
	private String ddEditFlag;

	/** The dd instance id. */
	@Column(name=JPAConstants.DD_INSTANCE_ID)
	private BigDecimal ddInstanceId;

	/** The dd modified by. */
	@Column(name=JPAConstants.DD_MODIFIED_BY)
	private String ddModifiedBy;

	/** The dd modified on. */
	@Temporal(TemporalType.DATE)
	@Column(name=JPAConstants.DD_MODIFIED_ON)
	private Date ddModifiedOn;

	/** The dd object type. */
	@Column(name=JPAConstants.DD_OBJECT_TYPE)
	private String ddObjectType;

	/** The dd process code. */
	@Column(name=JPAConstants.DD_PROCESS_CODE)
	private String ddProcessCode;

	/** The dd process instance id. */
	@Column(name=JPAConstants.DD_PROCESS_INSTANCE_ID)
	private BigDecimal ddProcessInstanceId;

	/** The dd status flag. */
	@Column(name=JPAConstants.DD_STATUS_FLAG)
	private String ddStatusFlag;

	/** The effective end date. */
	@Column(name=JPAConstants.EFFECTIVE_END_DATE)
	private String effectiveEndDate;

	/** The effective start date. */
	@Temporal(TemporalType.DATE)
	@Column(name=JPAConstants.EFFECTIVE_START_DATE)
	private Date effectiveStartDate;

	/** The hdn chnl id cnt. */
	@Column(name=JPAConstants.HDN_CHNL_ID_CNT)
	private String hdnChnlIdCnt;

	/** The hdn cidcntfrmsubsreq. */
	@Column(name=JPAConstants.HDN_CIDCNTFRMSUBSREQ)
	private String hdnCidcntfrmsubsreq;

	/** The object name. */
	@Column(name=JPAConstants.OBJECT_NAME)
	private String objectName;

	/** The save attachments. */
	@Column(name=JPAConstants.SAVE_ATTACHMENTS)
	private String saveAttachments;

	/** The st con infolet. */
	@Column(name=JPAConstants.ST_CON_INFOLET)
	private String stConInfolet;

	/** The st notify users. */
	@Column(name=JPAConstants.ST_NOTIFY_USERS)
	private String stNotifyUsers;

	/** The struct cont hand infolet. */
	@Column(name=JPAConstants.STRUCT_CONT_HAND_INFOLET)
	private String structContHandInfolet;

	/**
	 * Instantiates a new ms rga channel dtl.
	 */
	public MsRgaChannelDtl() {
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
	 * Gets the channel created by.
	 *
	 * @return the channel created by
	 */
	public String getChannelCreatedBy() {
		return this.channelCreatedBy;
	}

	/**
	 * Sets the channel created by.
	 *
	 * @param channelCreatedBy the new channel created by
	 */
	public void setChannelCreatedBy(String channelCreatedBy) {
		this.channelCreatedBy = channelCreatedBy;
	}

	/**
	 * Gets the channel created on.
	 *
	 * @return the channel created on
	 */
	public Date getChannelCreatedOn() {
		return this.channelCreatedOn;
	}

	/**
	 * Sets the channel created on.
	 *
	 * @param channelCreatedOn the new channel created on
	 */
	public void setChannelCreatedOn(Date channelCreatedOn) {
		this.channelCreatedOn = channelCreatedOn;
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
	 * Gets the channel status.
	 *
	 * @return the channel status
	 */
	public String getChannelStatus() {
		return this.channelStatus;
	}

	/**
	 * Sets the channel status.
	 *
	 * @param channelStatus the new channel status
	 */
	public void setChannelStatus(String channelStatus) {
		this.channelStatus = channelStatus;
	}

	/**
	 * Gets the channel type.
	 *
	 * @return the channel type
	 */
	public String getChannelType() {
		return this.channelType;
	}

	/**
	 * Sets the channel type.
	 *
	 * @param channelType the new channel type
	 */
	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}

	/**
	 * Gets the custom field1.
	 *
	 * @return the custom field1
	 */
	public String getCustomField1() {
		return this.customField1;
	}

	/**
	 * Sets the custom field1.
	 *
	 * @param customField1 the new custom field1
	 */
	public void setCustomField1(String customField1) {
		this.customField1 = customField1;
	}

	/**
	 * Gets the custom field2.
	 *
	 * @return the custom field2
	 */
	public String getCustomField2() {
		return this.customField2;
	}

	/**
	 * Sets the custom field2.
	 *
	 * @param customField2 the new custom field2
	 */
	public void setCustomField2(String customField2) {
		this.customField2 = customField2;
	}

	/**
	 * Gets the custom field3.
	 *
	 * @return the custom field3
	 */
	public BigDecimal getCustomField3() {
		return this.customField3;
	}

	/**
	 * Sets the custom field3.
	 *
	 * @param customField3 the new custom field3
	 */
	public void setCustomField3(BigDecimal customField3) {
		this.customField3 = customField3;
	}

	/**
	 * Gets the custom field4.
	 *
	 * @return the custom field4
	 */
	public BigDecimal getCustomField4() {
		return this.customField4;
	}

	/**
	 * Sets the custom field4.
	 *
	 * @param customField4 the new custom field4
	 */
	public void setCustomField4(BigDecimal customField4) {
		this.customField4 = customField4;
	}

	/**
	 * Gets the custom field5.
	 *
	 * @return the custom field5
	 */
	public Date getCustomField5() {
		return this.customField5;
	}

	/**
	 * Sets the custom field5.
	 *
	 * @param customField5 the new custom field5
	 */
	public void setCustomField5(Date customField5) {
		this.customField5 = customField5;
	}

	/**
	 * Gets the dd created by.
	 *
	 * @return the dd created by
	 */
	public String getDdCreatedBy() {
		return this.ddCreatedBy;
	}

	/**
	 * Sets the dd created by.
	 *
	 * @param ddCreatedBy the new dd created by
	 */
	public void setDdCreatedBy(String ddCreatedBy) {
		this.ddCreatedBy = ddCreatedBy;
	}

	/**
	 * Gets the dd created on.
	 *
	 * @return the dd created on
	 */
	public Date getDdCreatedOn() {
		return this.ddCreatedOn;
	}

	/**
	 * Sets the dd created on.
	 *
	 * @param ddCreatedOn the new dd created on
	 */
	public void setDdCreatedOn(Date ddCreatedOn) {
		this.ddCreatedOn = ddCreatedOn;
	}

	/**
	 * Gets the dd edit flag.
	 *
	 * @return the dd edit flag
	 */
	public String getDdEditFlag() {
		return this.ddEditFlag;
	}

	/**
	 * Sets the dd edit flag.
	 *
	 * @param ddEditFlag the new dd edit flag
	 */
	public void setDdEditFlag(String ddEditFlag) {
		this.ddEditFlag = ddEditFlag;
	}

	/**
	 * Gets the dd instance id.
	 *
	 * @return the dd instance id
	 */
	public BigDecimal getDdInstanceId() {
		return this.ddInstanceId;
	}

	/**
	 * Sets the dd instance id.
	 *
	 * @param ddInstanceId the new dd instance id
	 */
	public void setDdInstanceId(BigDecimal ddInstanceId) {
		this.ddInstanceId = ddInstanceId;
	}

	/**
	 * Gets the dd modified by.
	 *
	 * @return the dd modified by
	 */
	public String getDdModifiedBy() {
		return this.ddModifiedBy;
	}

	/**
	 * Sets the dd modified by.
	 *
	 * @param ddModifiedBy the new dd modified by
	 */
	public void setDdModifiedBy(String ddModifiedBy) {
		this.ddModifiedBy = ddModifiedBy;
	}

	/**
	 * Gets the dd modified on.
	 *
	 * @return the dd modified on
	 */
	public Date getDdModifiedOn() {
		return this.ddModifiedOn;
	}

	/**
	 * Sets the dd modified on.
	 *
	 * @param ddModifiedOn the new dd modified on
	 */
	public void setDdModifiedOn(Date ddModifiedOn) {
		this.ddModifiedOn = ddModifiedOn;
	}

	/**
	 * Gets the dd object type.
	 *
	 * @return the dd object type
	 */
	public String getDdObjectType() {
		return this.ddObjectType;
	}

	/**
	 * Sets the dd object type.
	 *
	 * @param ddObjectType the new dd object type
	 */
	public void setDdObjectType(String ddObjectType) {
		this.ddObjectType = ddObjectType;
	}

	/**
	 * Gets the dd process code.
	 *
	 * @return the dd process code
	 */
	public String getDdProcessCode() {
		return this.ddProcessCode;
	}

	/**
	 * Sets the dd process code.
	 *
	 * @param ddProcessCode the new dd process code
	 */
	public void setDdProcessCode(String ddProcessCode) {
		this.ddProcessCode = ddProcessCode;
	}

	/**
	 * Gets the dd process instance id.
	 *
	 * @return the dd process instance id
	 */
	public BigDecimal getDdProcessInstanceId() {
		return this.ddProcessInstanceId;
	}

	/**
	 * Sets the dd process instance id.
	 *
	 * @param ddProcessInstanceId the new dd process instance id
	 */
	public void setDdProcessInstanceId(BigDecimal ddProcessInstanceId) {
		this.ddProcessInstanceId = ddProcessInstanceId;
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
	 * Gets the effective end date.
	 *
	 * @return the effective end date
	 */
	public String getEffectiveEndDate() {
		return this.effectiveEndDate;
	}

	/**
	 * Sets the effective end date.
	 *
	 * @param effectiveEndDate the new effective end date
	 */
	public void setEffectiveEndDate(String effectiveEndDate) {
		this.effectiveEndDate = effectiveEndDate;
	}

	/**
	 * Gets the effective start date.
	 *
	 * @return the effective start date
	 */
	public Date getEffectiveStartDate() {
		return this.effectiveStartDate;
	}

	/**
	 * Sets the effective start date.
	 *
	 * @param effectiveStartDate the new effective start date
	 */
	public void setEffectiveStartDate(Date effectiveStartDate) {
		this.effectiveStartDate = effectiveStartDate;
	}

	/**
	 * Gets the hdn chnl id cnt.
	 *
	 * @return the hdn chnl id cnt
	 */
	public String getHdnChnlIdCnt() {
		return this.hdnChnlIdCnt;
	}

	/**
	 * Sets the hdn chnl id cnt.
	 *
	 * @param hdnChnlIdCnt the new hdn chnl id cnt
	 */
	public void setHdnChnlIdCnt(String hdnChnlIdCnt) {
		this.hdnChnlIdCnt = hdnChnlIdCnt;
	}

	/**
	 * Gets the hdn cidcntfrmsubsreq.
	 *
	 * @return the hdn cidcntfrmsubsreq
	 */
	public String getHdnCidcntfrmsubsreq() {
		return this.hdnCidcntfrmsubsreq;
	}

	/**
	 * Sets the hdn cidcntfrmsubsreq.
	 *
	 * @param hdnCidcntfrmsubsreq the new hdn cidcntfrmsubsreq
	 */
	public void setHdnCidcntfrmsubsreq(String hdnCidcntfrmsubsreq) {
		this.hdnCidcntfrmsubsreq = hdnCidcntfrmsubsreq;
	}

	/**
	 * Gets the object name.
	 *
	 * @return the object name
	 */
	public String getObjectName() {
		return this.objectName;
	}

	/**
	 * Sets the object name.
	 *
	 * @param objectName the new object name
	 */
	public void setObjectName(String objectName) {
		this.objectName = objectName;
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
	 * Gets the st con infolet.
	 *
	 * @return the st con infolet
	 */
	public String getStConInfolet() {
		return this.stConInfolet;
	}

	/**
	 * Sets the st con infolet.
	 *
	 * @param stConInfolet the new st con infolet
	 */
	public void setStConInfolet(String stConInfolet) {
		this.stConInfolet = stConInfolet;
	}

	/**
	 * Gets the st notify users.
	 *
	 * @return the st notify users
	 */
	public String getStNotifyUsers() {
		return this.stNotifyUsers;
	}

	/**
	 * Sets the st notify users.
	 *
	 * @param stNotifyUsers the new st notify users
	 */
	public void setStNotifyUsers(String stNotifyUsers) {
		this.stNotifyUsers = stNotifyUsers;
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