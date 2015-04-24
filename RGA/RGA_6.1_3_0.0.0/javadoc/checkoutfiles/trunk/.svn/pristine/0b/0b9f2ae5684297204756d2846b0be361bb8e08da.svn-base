package com.metricstream.systemi.jpa.entity;


import java.io.Serializable;

import javax.persistence.*;

import com.metricstream.systemi.jpa.util.JPAConstants;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the SI_USERS database table.
 * @author asif.u
 */
@Entity
@Table(name=JPAConstants.SI_USERS)
@NamedQuery(name="MsRgaUsers.findAll", query="SELECT s FROM MsRgaUsers s")
public class MsRgaUsers implements Serializable {
	
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

	/** The default first page. */
	@Column(name=JPAConstants.DEFAULT_FIRST_PAGE)
	private BigDecimal defaultFirstPage;

	/** The department. */
	private String department;

	/** The electronic signature. */
	@Column(name=JPAConstants.ELECTRONIC_SIGNATURE)
	private String electronicSignature;

	/** The email address. */
	@Column(name=JPAConstants.EMAIL_ADDRESS)
	private String emailAddress;

	/** The end date. */
	@Temporal(TemporalType.DATE)
	@Column(name=JPAConstants.END_DATE)
	private Date endDate;

	/** The enterprise id. */
	@Column(name=JPAConstants.ENTERPRISE_ID)
	private BigDecimal enterpriseId;

	/** The esignature salt. */
	@Column(name=JPAConstants.ESIGNATURE_SALT)
	private String esignatureSalt;

	/** The first name. */
	@Column(name=JPAConstants.FIRST_NAME)
	private String firstName;

	/** The forgot pwd question id. */
	@Column(name=JPAConstants.FORGOT_PWD_QUESTION_ID)
	private BigDecimal forgotPwdQuestionId;

	/** The full name. */
	@Column(name=JPAConstants.FULL_NAME)
	private String fullName;

	/** The infocenter flag. */
	@Column(name=JPAConstants.INFOCENTER_FLAG)
	private String infocenterFlag;

	/** The last name. */
	@Column(name=JPAConstants.LAST_NAME)
	private String lastName;

	/** The last update date. */
	@Temporal(TemporalType.DATE)
	@Column(name=JPAConstants.LAST_UPDATE_DATE)
	private Date lastUpdateDate;

	/** The last updated by. */
	@Column(name=JPAConstants.LAST_UPDATED_BY)
	private BigDecimal lastUpdatedBy;

	/** The locale. */
	private String locale;

	/** The location. */
	private String location;

	/** The middle initial. */
	@Column(name=JPAConstants.MIDDLE_INITIAL)
	private String middleInitial;

	/** The pager number. */
	@Column(name=JPAConstants.PAGER_NUMBER)
	private String pagerNumber;

	/** The password. */
	private String password;

	/** The password last update date. */
	@Temporal(TemporalType.DATE)
	@Column(name=JPAConstants.PASSWORD_LAST_UPDATE_DATE)
	private Date passwordLastUpdateDate;

	/** The password salt. */
	@Column(name=JPAConstants.PASSWORD_SALT)
	private String passwordSalt;

	/** The phone number. */
	@Column(name=JPAConstants.PHONE_NUMBER)
	private String phoneNumber;

	/** The report format. */
	@Column(name=JPAConstants.REPORT_FORMAT)
	private BigDecimal reportFormat;

	/** The server generated charts. */
	@Column(name=JPAConstants.SERVER_GENERATED_CHARTS)
	private String serverGeneratedCharts;

	/** The signature last update date. */
	@Temporal(TemporalType.DATE)
	@Column(name=JPAConstants.SIGNATURE_LAST_UPDATE_DATE)
	private Date signatureLastUpdateDate;

	/** The start date. */
	@Temporal(TemporalType.DATE)
	@Column(name=JPAConstants.START_DATE)
	private Date startDate;

	/** The timezone. */
	private BigDecimal timezone;

	/** The user id. */
	@Id
	@Column(name=JPAConstants.USER_ID)
	private BigDecimal userId;

	/** The user name. */
	@Column(name=JPAConstants.USER_NAME)
	private String userName;

	/** The xml. */
	private String xml;

	/**
	 * Instantiates a new users.
	 */
	public MsRgaUsers() {
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
	 * Gets the default first page.
	 *
	 * @return the default first page
	 */
	public BigDecimal getDefaultFirstPage() {
		return this.defaultFirstPage;
	}

	/**
	 * Sets the default first page.
	 *
	 * @param defaultFirstPage the new default first page
	 */
	public void setDefaultFirstPage(BigDecimal defaultFirstPage) {
		this.defaultFirstPage = defaultFirstPage;
	}

	/**
	 * Gets the department.
	 *
	 * @return the department
	 */
	public String getDepartment() {
		return this.department;
	}

	/**
	 * Sets the department.
	 *
	 * @param department the new department
	 */
	public void setDepartment(String department) {
		this.department = department;
	}

	/**
	 * Gets the electronic signature.
	 *
	 * @return the electronic signature
	 */
	public String getElectronicSignature() {
		return this.electronicSignature;
	}

	/**
	 * Sets the electronic signature.
	 *
	 * @param electronicSignature the new electronic signature
	 */
	public void setElectronicSignature(String electronicSignature) {
		this.electronicSignature = electronicSignature;
	}

	/**
	 * Gets the email address.
	 *
	 * @return the email address
	 */
	public String getEmailAddress() {
		return this.emailAddress;
	}

	/**
	 * Sets the email address.
	 *
	 * @param emailAddress the new email address
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	/**
	 * Gets the end date.
	 *
	 * @return the end date
	 */
	public Date getEndDate() {
		return this.endDate;
	}

	/**
	 * Sets the end date.
	 *
	 * @param endDate the new end date
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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
	 * Gets the esignature salt.
	 *
	 * @return the esignature salt
	 */
	public String getEsignatureSalt() {
		return this.esignatureSalt;
	}

	/**
	 * Sets the esignature salt.
	 *
	 * @param esignatureSalt the new esignature salt
	 */
	public void setEsignatureSalt(String esignatureSalt) {
		this.esignatureSalt = esignatureSalt;
	}

	/**
	 * Gets the first name.
	 *
	 * @return the first name
	 */
	public String getFirstName() {
		return this.firstName;
	}

	/**
	 * Sets the first name.
	 *
	 * @param firstName the new first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets the forgot pwd question id.
	 *
	 * @return the forgot pwd question id
	 */
	public BigDecimal getForgotPwdQuestionId() {
		return this.forgotPwdQuestionId;
	}

	/**
	 * Sets the forgot pwd question id.
	 *
	 * @param forgotPwdQuestionId the new forgot pwd question id
	 */
	public void setForgotPwdQuestionId(BigDecimal forgotPwdQuestionId) {
		this.forgotPwdQuestionId = forgotPwdQuestionId;
	}

	/**
	 * Gets the full name.
	 *
	 * @return the full name
	 */
	public String getFullName() {
		return this.fullName;
	}

	/**
	 * Sets the full name.
	 *
	 * @param fullName the new full name
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * Gets the infocenter flag.
	 *
	 * @return the infocenter flag
	 */
	public String getInfocenterFlag() {
		return this.infocenterFlag;
	}

	/**
	 * Sets the infocenter flag.
	 *
	 * @param infocenterFlag the new infocenter flag
	 */
	public void setInfocenterFlag(String infocenterFlag) {
		this.infocenterFlag = infocenterFlag;
	}

	/**
	 * Gets the last name.
	 *
	 * @return the last name
	 */
	public String getLastName() {
		return this.lastName;
	}

	/**
	 * Sets the last name.
	 *
	 * @param lastName the new last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	 * Gets the locale.
	 *
	 * @return the locale
	 */
	public String getLocale() {
		return this.locale;
	}

	/**
	 * Sets the locale.
	 *
	 * @param locale the new locale
	 */
	public void setLocale(String locale) {
		this.locale = locale;
	}

	/**
	 * Gets the location.
	 *
	 * @return the location
	 */
	public String getLocation() {
		return this.location;
	}

	/**
	 * Sets the location.
	 *
	 * @param location the new location
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * Gets the middle initial.
	 *
	 * @return the middle initial
	 */
	public String getMiddleInitial() {
		return this.middleInitial;
	}

	/**
	 * Sets the middle initial.
	 *
	 * @param middleInitial the new middle initial
	 */
	public void setMiddleInitial(String middleInitial) {
		this.middleInitial = middleInitial;
	}

	/**
	 * Gets the pager number.
	 *
	 * @return the pager number
	 */
	public String getPagerNumber() {
		return this.pagerNumber;
	}

	/**
	 * Sets the pager number.
	 *
	 * @param pagerNumber the new pager number
	 */
	public void setPagerNumber(String pagerNumber) {
		this.pagerNumber = pagerNumber;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the password last update date.
	 *
	 * @return the password last update date
	 */
	public Date getPasswordLastUpdateDate() {
		return this.passwordLastUpdateDate;
	}

	/**
	 * Sets the password last update date.
	 *
	 * @param passwordLastUpdateDate the new password last update date
	 */
	public void setPasswordLastUpdateDate(Date passwordLastUpdateDate) {
		this.passwordLastUpdateDate = passwordLastUpdateDate;
	}

	/**
	 * Gets the password salt.
	 *
	 * @return the password salt
	 */
	public String getPasswordSalt() {
		return this.passwordSalt;
	}

	/**
	 * Sets the password salt.
	 *
	 * @param passwordSalt the new password salt
	 */
	public void setPasswordSalt(String passwordSalt) {
		this.passwordSalt = passwordSalt;
	}

	/**
	 * Gets the phone number.
	 *
	 * @return the phone number
	 */
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	/**
	 * Sets the phone number.
	 *
	 * @param phoneNumber the new phone number
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Gets the report format.
	 *
	 * @return the report format
	 */
	public BigDecimal getReportFormat() {
		return this.reportFormat;
	}

	/**
	 * Sets the report format.
	 *
	 * @param reportFormat the new report format
	 */
	public void setReportFormat(BigDecimal reportFormat) {
		this.reportFormat = reportFormat;
	}

	/**
	 * Gets the server generated charts.
	 *
	 * @return the server generated charts
	 */
	public String getServerGeneratedCharts() {
		return this.serverGeneratedCharts;
	}

	/**
	 * Sets the server generated charts.
	 *
	 * @param serverGeneratedCharts the new server generated charts
	 */
	public void setServerGeneratedCharts(String serverGeneratedCharts) {
		this.serverGeneratedCharts = serverGeneratedCharts;
	}

	/**
	 * Gets the signature last update date.
	 *
	 * @return the signature last update date
	 */
	public Date getSignatureLastUpdateDate() {
		return this.signatureLastUpdateDate;
	}

	/**
	 * Sets the signature last update date.
	 *
	 * @param signatureLastUpdateDate the new signature last update date
	 */
	public void setSignatureLastUpdateDate(Date signatureLastUpdateDate) {
		this.signatureLastUpdateDate = signatureLastUpdateDate;
	}

	/**
	 * Gets the start date.
	 *
	 * @return the start date
	 */
	public Date getStartDate() {
		return this.startDate;
	}

	/**
	 * Sets the start date.
	 *
	 * @param startDate the new start date
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * Gets the timezone.
	 *
	 * @return the timezone
	 */
	public BigDecimal getTimezone() {
		return this.timezone;
	}

	/**
	 * Sets the timezone.
	 *
	 * @param timezone the new timezone
	 */
	public void setTimezone(BigDecimal timezone) {
		this.timezone = timezone;
	}

	/**
	 * Gets the user id.
	 *
	 * @return the user id
	 */
	public BigDecimal getUserId() {
		return this.userId;
	}

	/**
	 * Sets the user id.
	 *
	 * @param userId the new user id
	 */
	public void setUserId(BigDecimal userId) {
		this.userId = userId;
	}

	/**
	 * Gets the user name.
	 *
	 * @return the user name
	 */
	public String getUserName() {
		return this.userName;
	}

	/**
	 * Sets the user name.
	 *
	 * @param userName the new user name
	 */
	public void setUserName(String userName) {
		this.userName = userName;
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