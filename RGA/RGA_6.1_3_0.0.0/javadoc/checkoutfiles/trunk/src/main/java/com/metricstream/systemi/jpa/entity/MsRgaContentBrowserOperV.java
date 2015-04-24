package com.metricstream.systemi.jpa.entity;


import java.io.Serializable;

import javax.persistence.*;

import com.metricstream.systemi.jpa.util.JPAConstants;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the MS_RGA_CONTENT_BROWSER_OPER_V database table.
 * @author asif.u
 */
@Entity
@Table(name=JPAConstants.CONTENT_BROWSER_OPER_V)
@NamedQuery(name="MsRgaContentBrowserOperV.findAll", query="SELECT m FROM MsRgaContentBrowserOperV m")
public class MsRgaContentBrowserOperV implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The aoc comments. */
	@Column(name=JPAConstants.AOC_COMMENTS)
	private String aocComments;

	/** The aoc owner. */
	@Column(name=JPAConstants.AOC_OWNER)
	private String aocOwner;

	/** The aoc owner org. */
	@Column(name=JPAConstants.AOC_OWNER_ORG)
	private String aocOwnerOrg;

	/** The aoc restrict access to. */
	@Column(name=JPAConstants.AOC_RESTRICT_ACCESS_TO)
	private String aocRestrictAccessTo;

	/** The content category. */
	@Column(name=JPAConstants.CONTENT_CATEGORY)
	private String contentCategory;

	/** The content json. */
	@Lob
	@Column(name=JPAConstants.CONTENT_JSON)
	private String contentJson;

	/** The content provider. */
	@Column(name=JPAConstants.CONTENT_PROVIDER)
	private String contentProvider;

	/** The content version. */
	@Column(name=JPAConstants.CONTENT_VERSION)
	private String contentVersion;

	/** The created by. */
	@Column(name=JPAConstants.CREATED_BY)
	private BigDecimal createdBy;

	/** The creation date. */
	@Temporal(TemporalType.DATE)
	@Column(name=JPAConstants.CREATION_DATE)
	private Date creationDate;

	/** The dd current user name. */
	@Column(name=JPAConstants.DD_CURRENT_USER_NAME)
	private String ddCurrentUserName;

	/** The dd event user name. */
	@Column(name=JPAConstants.DD_EVENT_USER_NAME)
	private String ddEventUserName;

	/** The instance id. */
	@Column(name=JPAConstants.INSTANCE_ID)
	private BigDecimal instanceId;

	/** The process instance id. */
	@Id
	@Column(name=JPAConstants.PROCESS_INSTANCE_ID)
	private BigDecimal processInstanceId;

	/** The req comments. */
	@Column(name=JPAConstants.REQ_COMMENTS)
	private String reqComments;

	/** The req owner. */
	@Column(name=JPAConstants.REQ_OWNER)
	private String reqOwner;

	/** The req owner org. */
	@Column(name=JPAConstants.REQ_OWNER_ORG)
	private String reqOwnerOrg;

	/** The req restrict access to. */
	@Column(name=JPAConstants.REQ_RESTRICT_ACCESS_TO)
	private String reqRestrictAccessTo;

	/**
	 * Instantiates a new content browser oper v.
	 */
	public MsRgaContentBrowserOperV() {
	}

	/**
	 * Gets the aoc comments.
	 *
	 * @return the aoc comments
	 */
	public String getAocComments() {
		return this.aocComments;
	}

	/**
	 * Sets the aoc comments.
	 *
	 * @param aocComments the new aoc comments
	 */
	public void setAocComments(String aocComments) {
		this.aocComments = aocComments;
	}

	/**
	 * Gets the aoc owner.
	 *
	 * @return the aoc owner
	 */
	public String getAocOwner() {
		return this.aocOwner;
	}

	/**
	 * Sets the aoc owner.
	 *
	 * @param aocOwner the new aoc owner
	 */
	public void setAocOwner(String aocOwner) {
		this.aocOwner = aocOwner;
	}

	/**
	 * Gets the aoc owner org.
	 *
	 * @return the aoc owner org
	 */
	public String getAocOwnerOrg() {
		return this.aocOwnerOrg;
	}

	/**
	 * Sets the aoc owner org.
	 *
	 * @param aocOwnerOrg the new aoc owner org
	 */
	public void setAocOwnerOrg(String aocOwnerOrg) {
		this.aocOwnerOrg = aocOwnerOrg;
	}

	/**
	 * Gets the aoc restrict access to.
	 *
	 * @return the aoc restrict access to
	 */
	public String getAocRestrictAccessTo() {
		return this.aocRestrictAccessTo;
	}

	/**
	 * Sets the aoc restrict access to.
	 *
	 * @param aocRestrictAccessTo the new aoc restrict access to
	 */
	public void setAocRestrictAccessTo(String aocRestrictAccessTo) {
		this.aocRestrictAccessTo = aocRestrictAccessTo;
	}

	/**
	 * Gets the content category.
	 *
	 * @return the content category
	 */
	public String getContentCategory() {
		return this.contentCategory;
	}

	/**
	 * Sets the content category.
	 *
	 * @param contentCategory the new content category
	 */
	public void setContentCategory(String contentCategory) {
		this.contentCategory = contentCategory;
	}

	/**
	 * Gets the content json.
	 *
	 * @return the content json
	 */
	public String getContentJson() {
		return this.contentJson;
	}

	/**
	 * Sets the content json.
	 *
	 * @param contentJson the new content json
	 */
	public void setContentJson(String contentJson) {
		this.contentJson = contentJson;
	}

	/**
	 * Gets the content provider.
	 *
	 * @return the content provider
	 */
	public String getContentProvider() {
		return this.contentProvider;
	}

	/**
	 * Sets the content provider.
	 *
	 * @param contentProvider the new content provider
	 */
	public void setContentProvider(String contentProvider) {
		this.contentProvider = contentProvider;
	}

	/**
	 * Gets the content version.
	 *
	 * @return the content version
	 */
	public String getContentVersion() {
		return this.contentVersion;
	}

	/**
	 * Sets the content version.
	 *
	 * @param contentVersion the new content version
	 */
	public void setContentVersion(String contentVersion) {
		this.contentVersion = contentVersion;
	}

	/**
	 * Gets the created by.
	 *
	 * @return the created by
	 */
	public BigDecimal getCreatedBy() {
		return this.createdBy;
	}

	/**
	 * Sets the created by.
	 *
	 * @param createdBy the new created by
	 */
	public void setCreatedBy(BigDecimal createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * Gets the creation date.
	 *
	 * @return the creation date
	 */
	public Date getCreationDate() {
		return this.creationDate;
	}

	/**
	 * Sets the creation date.
	 *
	 * @param creationDate the new creation date
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * Gets the dd current user name.
	 *
	 * @return the dd current user name
	 */
	public String getDdCurrentUserName() {
		return this.ddCurrentUserName;
	}

	/**
	 * Sets the dd current user name.
	 *
	 * @param ddCurrentUserName the new dd current user name
	 */
	public void setDdCurrentUserName(String ddCurrentUserName) {
		this.ddCurrentUserName = ddCurrentUserName;
	}

	/**
	 * Gets the dd event user name.
	 *
	 * @return the dd event user name
	 */
	public String getDdEventUserName() {
		return this.ddEventUserName;
	}

	/**
	 * Sets the dd event user name.
	 *
	 * @param ddEventUserName the new dd event user name
	 */
	public void setDdEventUserName(String ddEventUserName) {
		this.ddEventUserName = ddEventUserName;
	}

	/**
	 * Gets the instance id.
	 *
	 * @return the instance id
	 */
	public BigDecimal getInstanceId() {
		return this.instanceId;
	}

	/**
	 * Sets the instance id.
	 *
	 * @param instanceId the new instance id
	 */
	public void setInstanceId(BigDecimal instanceId) {
		this.instanceId = instanceId;
	}

	/**
	 * Gets the process instance id.
	 *
	 * @return the process instance id
	 */
	public BigDecimal getProcessInstanceId() {
		return this.processInstanceId;
	}

	/**
	 * Sets the process instance id.
	 *
	 * @param processInstanceId the new process instance id
	 */
	public void setProcessInstanceId(BigDecimal processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	/**
	 * Gets the req comments.
	 *
	 * @return the req comments
	 */
	public String getReqComments() {
		return this.reqComments;
	}

	/**
	 * Sets the req comments.
	 *
	 * @param reqComments the new req comments
	 */
	public void setReqComments(String reqComments) {
		this.reqComments = reqComments;
	}

	/**
	 * Gets the req owner.
	 *
	 * @return the req owner
	 */
	public String getReqOwner() {
		return this.reqOwner;
	}

	/**
	 * Sets the req owner.
	 *
	 * @param reqOwner the new req owner
	 */
	public void setReqOwner(String reqOwner) {
		this.reqOwner = reqOwner;
	}

	/**
	 * Gets the req owner org.
	 *
	 * @return the req owner org
	 */
	public String getReqOwnerOrg() {
		return this.reqOwnerOrg;
	}

	/**
	 * Sets the req owner org.
	 *
	 * @param reqOwnerOrg the new req owner org
	 */
	public void setReqOwnerOrg(String reqOwnerOrg) {
		this.reqOwnerOrg = reqOwnerOrg;
	}

	/**
	 * Gets the req restrict access to.
	 *
	 * @return the req restrict access to
	 */
	public String getReqRestrictAccessTo() {
		return this.reqRestrictAccessTo;
	}

	/**
	 * Sets the req restrict access to.
	 *
	 * @param reqRestrictAccessTo the new req restrict access to
	 */
	public void setReqRestrictAccessTo(String reqRestrictAccessTo) {
		this.reqRestrictAccessTo = reqRestrictAccessTo;
	}

}