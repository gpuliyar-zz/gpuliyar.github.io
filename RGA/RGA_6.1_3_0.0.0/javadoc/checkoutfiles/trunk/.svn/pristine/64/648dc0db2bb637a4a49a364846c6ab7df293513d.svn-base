package com.metricstream.systemi.jpa.entity;

import java.io.Serializable;

import javax.persistence.*;

import com.metricstream.systemi.jpa.util.JPAConstants;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the MS_QS_PARAMETER_VALUES database table.
 * 
 */
@Entity
@Table(name=JPAConstants.PARAMETER_VALUES)
@NamedQuery(name="MsQsParameterValue.findAll", query="SELECT m FROM MsQsParameterValue m")
public class MsQsParameterValue implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The comments. */
	private String comments;

	/** The created by. */
	@Column(name=JPAConstants.CREATED_BY)
	private BigDecimal createdBy;

	/** The creation date. */
	@Temporal(TemporalType.DATE)
	@Column(name=JPAConstants.CREATION_DATE)
	private Date creationDate;

	/** The last update date. */
	@Temporal(TemporalType.DATE)
	@Column(name=JPAConstants.LAST_UPDATE_DATE)
	private Date lastUpdateDate;

	/** The last updated by. */
	@Column(name=JPAConstants.LAST_UPDATED_BY)
	private BigDecimal lastUpdatedBy;

	/** The lov value id. */
	@Column(name=JPAConstants.LOV_VALUE_ID)
	private BigDecimal lovValueId;

	/** The org entity id. */
	@Column(name=JPAConstants.ORG_ENTITY_ID)
	private BigDecimal orgEntityId;

	/** The parameter id. */
	@Id
	@Column(name=JPAConstants.PARAMETER_ID)
	private BigDecimal parameterId;

	/** The parameter type id. */
	@Column(name=JPAConstants.PARAMETER_TYPE_ID)
	private BigDecimal parameterTypeId;

	/** The parameter value. */
	@Column(name=JPAConstants.PARAMETER_VALUE)
	private String parameterValue;

	/** The xml. */
	private String xml;

	/**
	 * Instantiates a new ms qs parameter value.
	 */
	public MsQsParameterValue() {
	}

	/**
	 * Gets the comments.
	 *
	 * @return the comments
	 */
	public String getComments() {
		return this.comments;
	}

	/**
	 * Sets the comments.
	 *
	 * @param comments the new comments
	 */
	public void setComments(String comments) {
		this.comments = comments;
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
	 * Gets the last update date.
	 *
	 * @return the last update date
	 */
	public Date getLastUpdateDate() {
		return this.lastUpdateDate;
	}

	/**
	 * Sets the last update date.
	 *
	 * @param lastUpdateDate the new last update date
	 */
	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	/**
	 * Gets the last updated by.
	 *
	 * @return the last updated by
	 */
	public BigDecimal getLastUpdatedBy() {
		return this.lastUpdatedBy;
	}

	/**
	 * Sets the last updated by.
	 *
	 * @param lastUpdatedBy the new last updated by
	 */
	public void setLastUpdatedBy(BigDecimal lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	/**
	 * Gets the lov value id.
	 *
	 * @return the lov value id
	 */
	public BigDecimal getLovValueId() {
		return this.lovValueId;
	}

	/**
	 * Sets the lov value id.
	 *
	 * @param lovValueId the new lov value id
	 */
	public void setLovValueId(BigDecimal lovValueId) {
		this.lovValueId = lovValueId;
	}

	/**
	 * Gets the org entity id.
	 *
	 * @return the org entity id
	 */
	public BigDecimal getOrgEntityId() {
		return this.orgEntityId;
	}

	/**
	 * Sets the org entity id.
	 *
	 * @param orgEntityId the new org entity id
	 */
	public void setOrgEntityId(BigDecimal orgEntityId) {
		this.orgEntityId = orgEntityId;
	}

	/**
	 * Gets the parameter id.
	 *
	 * @return the parameter id
	 */
	public BigDecimal getParameterId() {
		return this.parameterId;
	}

	/**
	 * Sets the parameter id.
	 *
	 * @param parameterId the new parameter id
	 */
	public void setParameterId(BigDecimal parameterId) {
		this.parameterId = parameterId;
	}

	/**
	 * Gets the parameter type id.
	 *
	 * @return the parameter type id
	 */
	public BigDecimal getParameterTypeId() {
		return this.parameterTypeId;
	}

	/**
	 * Sets the parameter type id.
	 *
	 * @param parameterTypeId the new parameter type id
	 */
	public void setParameterTypeId(BigDecimal parameterTypeId) {
		this.parameterTypeId = parameterTypeId;
	}

	/**
	 * Gets the parameter value.
	 *
	 * @return the parameter value
	 */
	public String getParameterValue() {
		return this.parameterValue;
	}

	/**
	 * Sets the parameter value.
	 *
	 * @param parameterValue the new parameter value
	 */
	public void setParameterValue(String parameterValue) {
		this.parameterValue = parameterValue;
	}

	/**
	 * Gets the xml.
	 *
	 * @return the xml
	 */
	public String getXml() {
		return this.xml;
	}

	/**
	 * Sets the xml.
	 *
	 * @param xml the new xml
	 */
	public void setXml(String xml) {
		this.xml = xml;
	}

}