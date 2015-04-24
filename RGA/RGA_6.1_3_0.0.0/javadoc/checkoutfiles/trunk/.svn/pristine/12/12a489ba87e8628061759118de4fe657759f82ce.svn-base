package com.metricstream.systemi.jpa.entity;

import java.io.Serializable;

import javax.persistence.*;

import com.metricstream.systemi.jpa.util.JPAConstants;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the MS_QS_PARAMETER_TYPES database table.
 * 
 */
@Entity
@Table(name=JPAConstants.PARAMETER_TYPES)
@NamedQuery(name="MsQsParameterType.findAll", query="SELECT m FROM MsQsParameterType m")
public class MsQsParameterType implements Serializable {
	
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

	/** The editable. */
	private String editable;

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

	/** The parameter scope. */
	@Column(name=JPAConstants.PARAMETER_SCOPE)
	private BigDecimal parameterScope;

	/** The parameter type. */
	@Column(name=JPAConstants.PARAMETER_TYPE)
	private String parameterType;

	/** The parameter type id. */
	@Id
	@Column(name=JPAConstants.PARAMETER_TYPE_ID)
	private BigDecimal parameterTypeId;

	/** The type description. */
	@Column(name=JPAConstants.TYPE_DESCRIPTION)
	private String typeDescription;

	/** The xml. */
	private String xml;

	/**
	 * Instantiates a new ms qs parameter type.
	 */
	public MsQsParameterType() {
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
	 * Gets the editable.
	 *
	 * @return the editable
	 */
	public String getEditable() {
		return this.editable;
	}

	/**
	 * Sets the editable.
	 *
	 * @param editable the new editable
	 */
	public void setEditable(String editable) {
		this.editable = editable;
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
	 * Gets the parameter scope.
	 *
	 * @return the parameter scope
	 */
	public BigDecimal getParameterScope() {
		return this.parameterScope;
	}

	/**
	 * Sets the parameter scope.
	 *
	 * @param parameterScope the new parameter scope
	 */
	public void setParameterScope(BigDecimal parameterScope) {
		this.parameterScope = parameterScope;
	}

	/**
	 * Gets the parameter type.
	 *
	 * @return the parameter type
	 */
	public String getParameterType() {
		return this.parameterType;
	}

	/**
	 * Sets the parameter type.
	 *
	 * @param parameterType the new parameter type
	 */
	public void setParameterType(String parameterType) {
		this.parameterType = parameterType;
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
	 * Gets the type description.
	 *
	 * @return the type description
	 */
	public String getTypeDescription() {
		return this.typeDescription;
	}

	/**
	 * Sets the type description.
	 *
	 * @param typeDescription the new type description
	 */
	public void setTypeDescription(String typeDescription) {
		this.typeDescription = typeDescription;
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