package com.metricstream.systemi.jpa.entity;

import java.io.Serializable;

import javax.persistence.*;

import com.metricstream.systemi.jpa.util.JPAConstants;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the MS_QS_PARAMETER_NAMES database table.
 * 
 */
@Entity
@Table(name=JPAConstants.PARAMETER_NAMES)
@NamedQuery(name="MsQsParameterName.findAll", query="SELECT m FROM MsQsParameterName m")
public class MsQsParameterName implements Serializable {
	
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

	/** The enterprise id. */
	@Column(name=JPAConstants.ENTERPRISE_ID)
	private BigDecimal enterpriseId;

	/** The last update date. */
	@Temporal(TemporalType.DATE)
	@Column(name=JPAConstants.LAST_UPDATE_DATE)
	private Date lastUpdateDate;

	/** The last updated by. */
	@Column(name=JPAConstants.LAST_UPDATED_BY)
	private BigDecimal lastUpdatedBy;

	/** The lov id. */
	@Column(name=JPAConstants.LOV_ID)
	private BigDecimal lovId;

	/** The name description. */
	@Column(name=JPAConstants.NAME_DESCRIPTION)
	private String nameDescription;

	/** The parameter id. */
	@Column(name=JPAConstants.PARAMETER_ID)
	private BigDecimal parameterId;

	/** The parameter name. */
	@Column(name=JPAConstants.PARAMETER_NAME)
	private String parameterName;

	/** The parameter type id. */
	@Id
	@Column(name=JPAConstants.PARAMETER_TYPE_ID)
	private BigDecimal parameterTypeId;

	/** The update id. */
	@Column(name="UPDATE_ID")
	private BigDecimal updateId;

	/** The xml. */
	private String xml;

	/**
	 * Instantiates a new ms qs parameter name.
	 */
	public MsQsParameterName() {
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
	 * Gets the enterprise id.
	 *
	 * @return the enterprise id
	 */
	public BigDecimal getEnterpriseId() {
		return this.enterpriseId;
	}

	/**
	 * Sets the enterprise id.
	 *
	 * @param enterpriseId the new enterprise id
	 */
	public void setEnterpriseId(BigDecimal enterpriseId) {
		this.enterpriseId = enterpriseId;
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
	 * Gets the lov id.
	 *
	 * @return the lov id
	 */
	public BigDecimal getLovId() {
		return this.lovId;
	}

	/**
	 * Sets the lov id.
	 *
	 * @param lovId the new lov id
	 */
	public void setLovId(BigDecimal lovId) {
		this.lovId = lovId;
	}

	/**
	 * Gets the name description.
	 *
	 * @return the name description
	 */
	public String getNameDescription() {
		return this.nameDescription;
	}

	/**
	 * Sets the name description.
	 *
	 * @param nameDescription the new name description
	 */
	public void setNameDescription(String nameDescription) {
		this.nameDescription = nameDescription;
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
	 * Gets the parameter name.
	 *
	 * @return the parameter name
	 */
	public String getParameterName() {
		return this.parameterName;
	}

	/**
	 * Sets the parameter name.
	 *
	 * @param parameterName the new parameter name
	 */
	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
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
	 * Gets the update id.
	 *
	 * @return the update id
	 */
	public BigDecimal getUpdateId() {
		return this.updateId;
	}

	/**
	 * Sets the update id.
	 *
	 * @param updateId the new update id
	 */
	public void setUpdateId(BigDecimal updateId) {
		this.updateId = updateId;
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