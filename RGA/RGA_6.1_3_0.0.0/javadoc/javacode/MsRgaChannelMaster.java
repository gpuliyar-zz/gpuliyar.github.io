package com.metricstream.systemi.jpa.entity;
import com.metricstream.systemi.jpa.util.JPAConstants;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The View class for the MS_RGA_CHANNEL_MASTER database table.
 * @author asif.u
 */
@Entity
@Table(name=JPAConstants.CHANNEL_MASTER)
@NamedQuery(name="MsRgaChannelMaster.findAll", query="SELECT m FROM MsRgaChannelMaster m")
public class MsRgaChannelMaster implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The active. */
	private String active;

	/** The channel description. */
	@Column(name=JPAConstants.CHANNEL_DESCRIPTION)
	private String channelDescription;

	/** The channel id. */
	@Id
	@Column(name=JPAConstants.CHANNEL_ID)
	private String channelId;

	/** The channel name. */
	@Column(name=JPAConstants.CHANNEL_NAME)
	private String channelName;

	/** The channel type. */
	@Column(name=JPAConstants.CHANNEL_TYPE)
	private String channelType;

	/** The created by. */
	@Column(name=JPAConstants.CREATED_BY)
	private String createdBy;

	/** The created on. */
	@Temporal(TemporalType.DATE)
	@Column(name=JPAConstants.CREATED_ON)
	private Date createdOn;

	/** The infolet name. */
	@Column(name=JPAConstants.INFOLET_NAME)
	private String infoletName;

	/** The modified by. */
	@Column(name=JPAConstants.MODIFIED_BY)
	private String modifiedBy;

	/** The modified on. */
	@Temporal(TemporalType.DATE)
	@Column(name=JPAConstants.MODIFIED_ON)
	private Date modifiedOn;

	/** The pid. */
	private BigDecimal pid;

	/** The src pk. */
	@Column(name=JPAConstants.SRC_PK)
	private String srcPk;

	/**
	 * Instantiates a new ms rga channel master.
	 */
	public MsRgaChannelMaster() {
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
	 * Gets the channel description.
	 *
	 * @return the channel description
	 */
	public String getChannelDescription() {
		return this.channelDescription;
	}

	/**
	 * Sets the channel description.
	 *
	 * @param channelDescription the new channel description
	 */
	public void setChannelDescription(String channelDescription) {
		this.channelDescription = channelDescription;
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
	 * Gets the infolet name.
	 *
	 * @return the infolet name
	 */
	public String getInfoletName() {
		return this.infoletName;
	}

	/**
	 * Sets the infolet name.
	 *
	 * @param infoletName the new infolet name
	 */
	public void setInfoletName(String infoletName) {
		this.infoletName = infoletName;
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
	 * Gets the pid.
	 *
	 * @return the pid
	 */
	public BigDecimal getPid() {
		return this.pid;
	}

	/**
	 * Sets the pid.
	 *
	 * @param pid the new pid
	 */
	public void setPid(BigDecimal pid) {
		this.pid = pid;
	}

	/**
	 * Gets the src pk.
	 *
	 * @return the src pk
	 */
	public String getSrcPk() {
		return this.srcPk;
	}

	/**
	 * Sets the src pk.
	 *
	 * @param srcPk the new src pk
	 */
	public void setSrcPk(String srcPk) {
		this.srcPk = srcPk;
	}

}