package com.metricstream.systemi.jpa.entity;

import java.io.Serializable;

import javax.persistence.*;

import com.metricstream.systemi.jpa.util.JPAConstants;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the SI_METRICS_T database table.
 * 
 */
@Entity
@Table(name=JPAConstants.SI_METRICS_T)
@NamedQuery(name="SiMetricsT.findAll", query="SELECT s FROM SiMetricsT s")
public class SiMetricsT implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The alert id. */
	@Column(name=JPAConstants.ALERT_ID)
	private BigDecimal alertId;

	/** The allow concurrent update flag. */
	@Column(name=JPAConstants.ALLOW_CONCURRENT_UPDATE_FLAG)
	private String allowConcurrentUpdateFlag;

	/** The allow save option. */
	@Column(name=JPAConstants.ALLOW_SAVE_OPTION)
	private String allowSaveOption;

	/** The application id. */
	@Column(name=JPAConstants.APPLICATION_ID)
	private BigDecimal applicationId;

	/** The archive flag. */
	@Column(name=JPAConstants.ARCHIVE_FLAG)
	private String archiveFlag;

	/** The archive period interval. */
	@Column(name=JPAConstants.ARCHIVE_PERIOD_INTERVAL)
	private BigDecimal archivePeriodInterval;

	/** The archive period interval type. */
	@Column(name=JPAConstants.ARCHIVE_PERIOD_INTERVAL_TYPE)
	private BigDecimal archivePeriodIntervalType;

	/** The assignment text. */
	@Column(name=JPAConstants.ASSIGNMENT_TEXT)
	private String assignmentText;

	/** The comments. */
	private String comments;

	/** The composite expression. */
	@Column(name=JPAConstants.COMPOSITE_EXPRESSION)
	private String compositeExpression;

	/** The composite view name. */
	@Column(name=JPAConstants.COMPOSITE_VIEW_NAME)
	private String compositeViewName;

	/** The created by. */
	@Column(name=JPAConstants.CREATED_BY)
	private BigDecimal createdBy;

	/** The creation date. */
	@Temporal(TemporalType.DATE)
	@Column(name=JPAConstants.CREATION_DATE)
	private Date creationDate;

	/** The customized flag. */
	@Column(name=JPAConstants.CUSTOMIZED_FLAG)
	private String customizedFlag;

	/** The data setup form. */
	@Column(name=JPAConstants.DATA_SETUP_FORM)
	private String dataSetupForm;

	/** The description. */
	private String description;

	/** The electronic signature required. */
	@Column(name=JPAConstants.ELECTRONIC_SIGNATURE_REQUIRED)
	private String electronicSignatureRequired;

	/** The end date. */
	@Temporal(TemporalType.DATE)
	@Column(name=JPAConstants.END_DATE)
	private Date endDate;

	/** The enterprise id. */
	@Column(name=JPAConstants.ENTERPRISE_ID)
	private BigDecimal enterpriseId;

	/** The form name. */
	@Column(name=JPAConstants.FORM_NAME)
	private String formName;

	/** The help file. */
	@Column(name=JPAConstants.HELP_FILE)
	private String helpFile;

	/** The incremental flag. */
	@Column(name=JPAConstants.INCREMENTAL_FLAG)
	private String incrementalFlag;

	/** The last update date. */
	@Temporal(TemporalType.DATE)
	@Column(name=JPAConstants.LAST_UPDATE_DATE)
	private Date lastUpdateDate;

	/** The last updated by. */
	@Column(name=JPAConstants.LAST_UPDATED_BY)
	private BigDecimal lastUpdatedBy;

	/** The metric category. */
	@Column(name=JPAConstants.METRIC_CATEGORY)
	private BigDecimal metricCategory;

	/** The metric id. */
	@Id
	@Column(name=JPAConstants.METRIC_ID)
	private BigDecimal metricId;

	/** The metric name. */
	@Column(name=JPAConstants.METRIC_NAME)
	private String metricName;

	/** The metric output type. */
	@Column(name=JPAConstants.METRIC_OUTPUT_TYPE)
	private BigDecimal metricOutputType;

	/** The metric ready flag. */
	@Column(name=JPAConstants.METRIC_READY_FLAG)
	private String metricReadyFlag;

	/** The metric run mode. */
	@Column(name=JPAConstants.METRIC_RUN_MODE)
	private BigDecimal metricRunMode;

	/** The metric sql. */
	@Column(name=JPAConstants.METRIC_SQL)
	private String metricSql;

	/** The metric sub category. */
	@Column(name=JPAConstants.METRIC_SUB_CATEGORY)
	private BigDecimal metricSubCategory;

	/** The metric type. */
	@Column(name=JPAConstants.METRIC_TYPE)
	private BigDecimal metricType;

	/** The metric validated flag. */
	@Column(name=JPAConstants.METRIC_VALIDATED_FLAG)
	private String metricValidatedFlag;

	/** The offline flag. */
	@Column(name=JPAConstants.OFFLINE_FLAG)
	private String offlineFlag;

	/** The offline template name. */
	@Column(name=JPAConstants.OFFLINE_TEMPLATE_NAME)
	private String offlineTemplateName;

	/** The original metric id. */
	@Column(name=JPAConstants.ORIGINAL_METRIC_ID)
	private BigDecimal originalMetricId;

	/** The parameter flag. */
	@Column(name=JPAConstants.PARAMETER_FLAG)
	private String parameterFlag;

	/** The push provider. */
	@Column(name=JPAConstants.PUSH_PROVIDER)
	private BigDecimal pushProvider;

	/** The reassignment infolet id. */
	@Column(name=JPAConstants.REASSIGNMENT_INFOLET_ID)
	private BigDecimal reassignmentInfoletId;

	/** The result database name. */
	@Column(name=JPAConstants.RESULT_DATABASE_NAME)
	private String resultDatabaseName;

	/** The result store table name. */
	@Column(name=JPAConstants.RESULT_STORE_TABLE_NAME)
	private String resultStoreTableName;

	/** The result table name. */
	@Column(name=JPAConstants.RESULT_TABLE_NAME)
	private String resultTableName;

	/** The selfstoring flag. */
	@Column(name=JPAConstants.SELFSTORING_FLAG)
	private String selfstoringFlag;

	/** The start date. */
	@Temporal(TemporalType.DATE)
	@Column(name=JPAConstants.START_DATE)
	private Date startDate;

	/** The submission template name. */
	@Column(name=JPAConstants.SUBMISSION_TEMPLATE_NAME)
	private String submissionTemplateName;

	/** The task altering condition id. */
	@Column(name=JPAConstants.TASK_ALTERING_CONDITION_ID)
	private BigDecimal taskAlteringConditionId;

	/** The task id. */
	@Column(name=JPAConstants.TASK_ID)
	private BigDecimal taskId;

	/** The template name. */
	@Column(name=JPAConstants.TEMPLATE_NAME)
	private String templateName;

	/** The template type. */
	@Column(name=JPAConstants.TEMPLATE_TYPE)
	private String templateType;

	/** The threshold id. */
	@Column(name=JPAConstants.THRESHOLD_ID)
	private BigDecimal thresholdId;

	/** The use for reassignment flag. */
	@Column(name=JPAConstants.USE_FOR_REASSIGNMENT_FLAG)
	private String useForReassignmentFlag;

	/** The ws flag. */
	@Column(name=JPAConstants.WS_FLAG)
	private String wsFlag;

	/** The xml. */
	private String xml;

	/**
	 * Instantiates a new si metrics t.
	 */
	public SiMetricsT() {
	}

	/**
	 * Gets the alert id.
	 *
	 * @return the alert id
	 */
	public BigDecimal getAlertId() {
		return this.alertId;
	}

	/**
	 * Sets the alert id.
	 *
	 * @param alertId the new alert id
	 */
	public void setAlertId(BigDecimal alertId) {
		this.alertId = alertId;
	}

	/**
	 * Gets the allow concurrent update flag.
	 *
	 * @return the allow concurrent update flag
	 */
	public String getAllowConcurrentUpdateFlag() {
		return this.allowConcurrentUpdateFlag;
	}

	/**
	 * Sets the allow concurrent update flag.
	 *
	 * @param allowConcurrentUpdateFlag the new allow concurrent update flag
	 */
	public void setAllowConcurrentUpdateFlag(String allowConcurrentUpdateFlag) {
		this.allowConcurrentUpdateFlag = allowConcurrentUpdateFlag;
	}

	/**
	 * Gets the allow save option.
	 *
	 * @return the allow save option
	 */
	public String getAllowSaveOption() {
		return this.allowSaveOption;
	}

	/**
	 * Sets the allow save option.
	 *
	 * @param allowSaveOption the new allow save option
	 */
	public void setAllowSaveOption(String allowSaveOption) {
		this.allowSaveOption = allowSaveOption;
	}

	/**
	 * Gets the application id.
	 *
	 * @return the application id
	 */
	public BigDecimal getApplicationId() {
		return this.applicationId;
	}

	/**
	 * Sets the application id.
	 *
	 * @param applicationId the new application id
	 */
	public void setApplicationId(BigDecimal applicationId) {
		this.applicationId = applicationId;
	}

	/**
	 * Gets the archive flag.
	 *
	 * @return the archive flag
	 */
	public String getArchiveFlag() {
		return this.archiveFlag;
	}

	/**
	 * Sets the archive flag.
	 *
	 * @param archiveFlag the new archive flag
	 */
	public void setArchiveFlag(String archiveFlag) {
		this.archiveFlag = archiveFlag;
	}

	/**
	 * Gets the archive period interval.
	 *
	 * @return the archive period interval
	 */
	public BigDecimal getArchivePeriodInterval() {
		return this.archivePeriodInterval;
	}

	/**
	 * Sets the archive period interval.
	 *
	 * @param archivePeriodInterval the new archive period interval
	 */
	public void setArchivePeriodInterval(BigDecimal archivePeriodInterval) {
		this.archivePeriodInterval = archivePeriodInterval;
	}

	/**
	 * Gets the archive period interval type.
	 *
	 * @return the archive period interval type
	 */
	public BigDecimal getArchivePeriodIntervalType() {
		return this.archivePeriodIntervalType;
	}

	/**
	 * Sets the archive period interval type.
	 *
	 * @param archivePeriodIntervalType the new archive period interval type
	 */
	public void setArchivePeriodIntervalType(BigDecimal archivePeriodIntervalType) {
		this.archivePeriodIntervalType = archivePeriodIntervalType;
	}

	/**
	 * Gets the assignment text.
	 *
	 * @return the assignment text
	 */
	public String getAssignmentText() {
		return this.assignmentText;
	}

	/**
	 * Sets the assignment text.
	 *
	 * @param assignmentText the new assignment text
	 */
	public void setAssignmentText(String assignmentText) {
		this.assignmentText = assignmentText;
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
	 * Gets the composite expression.
	 *
	 * @return the composite expression
	 */
	public String getCompositeExpression() {
		return this.compositeExpression;
	}

	/**
	 * Sets the composite expression.
	 *
	 * @param compositeExpression the new composite expression
	 */
	public void setCompositeExpression(String compositeExpression) {
		this.compositeExpression = compositeExpression;
	}

	/**
	 * Gets the composite view name.
	 *
	 * @return the composite view name
	 */
	public String getCompositeViewName() {
		return this.compositeViewName;
	}

	/**
	 * Sets the composite view name.
	 *
	 * @param compositeViewName the new composite view name
	 */
	public void setCompositeViewName(String compositeViewName) {
		this.compositeViewName = compositeViewName;
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
	 * Gets the customized flag.
	 *
	 * @return the customized flag
	 */
	public String getCustomizedFlag() {
		return this.customizedFlag;
	}

	/**
	 * Sets the customized flag.
	 *
	 * @param customizedFlag the new customized flag
	 */
	public void setCustomizedFlag(String customizedFlag) {
		this.customizedFlag = customizedFlag;
	}

	/**
	 * Gets the data setup form.
	 *
	 * @return the data setup form
	 */
	public String getDataSetupForm() {
		return this.dataSetupForm;
	}

	/**
	 * Sets the data setup form.
	 *
	 * @param dataSetupForm the new data setup form
	 */
	public void setDataSetupForm(String dataSetupForm) {
		this.dataSetupForm = dataSetupForm;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the electronic signature required.
	 *
	 * @return the electronic signature required
	 */
	public String getElectronicSignatureRequired() {
		return this.electronicSignatureRequired;
	}

	/**
	 * Sets the electronic signature required.
	 *
	 * @param electronicSignatureRequired the new electronic signature required
	 */
	public void setElectronicSignatureRequired(String electronicSignatureRequired) {
		this.electronicSignatureRequired = electronicSignatureRequired;
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
	 * Gets the form name.
	 *
	 * @return the form name
	 */
	public String getFormName() {
		return this.formName;
	}

	/**
	 * Sets the form name.
	 *
	 * @param formName the new form name
	 */
	public void setFormName(String formName) {
		this.formName = formName;
	}

	/**
	 * Gets the help file.
	 *
	 * @return the help file
	 */
	public String getHelpFile() {
		return this.helpFile;
	}

	/**
	 * Sets the help file.
	 *
	 * @param helpFile the new help file
	 */
	public void setHelpFile(String helpFile) {
		this.helpFile = helpFile;
	}

	/**
	 * Gets the incremental flag.
	 *
	 * @return the incremental flag
	 */
	public String getIncrementalFlag() {
		return this.incrementalFlag;
	}

	/**
	 * Sets the incremental flag.
	 *
	 * @param incrementalFlag the new incremental flag
	 */
	public void setIncrementalFlag(String incrementalFlag) {
		this.incrementalFlag = incrementalFlag;
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
	 * Gets the metric category.
	 *
	 * @return the metric category
	 */
	public BigDecimal getMetricCategory() {
		return this.metricCategory;
	}

	/**
	 * Sets the metric category.
	 *
	 * @param metricCategory the new metric category
	 */
	public void setMetricCategory(BigDecimal metricCategory) {
		this.metricCategory = metricCategory;
	}

	/**
	 * Gets the metric id.
	 *
	 * @return the metric id
	 */
	public BigDecimal getMetricId() {
		return this.metricId;
	}

	/**
	 * Sets the metric id.
	 *
	 * @param metricId the new metric id
	 */
	public void setMetricId(BigDecimal metricId) {
		this.metricId = metricId;
	}

	/**
	 * Gets the metric name.
	 *
	 * @return the metric name
	 */
	public String getMetricName() {
		return this.metricName;
	}

	/**
	 * Sets the metric name.
	 *
	 * @param metricName the new metric name
	 */
	public void setMetricName(String metricName) {
		this.metricName = metricName;
	}

	/**
	 * Gets the metric output type.
	 *
	 * @return the metric output type
	 */
	public BigDecimal getMetricOutputType() {
		return this.metricOutputType;
	}

	/**
	 * Sets the metric output type.
	 *
	 * @param metricOutputType the new metric output type
	 */
	public void setMetricOutputType(BigDecimal metricOutputType) {
		this.metricOutputType = metricOutputType;
	}

	/**
	 * Gets the metric ready flag.
	 *
	 * @return the metric ready flag
	 */
	public String getMetricReadyFlag() {
		return this.metricReadyFlag;
	}

	/**
	 * Sets the metric ready flag.
	 *
	 * @param metricReadyFlag the new metric ready flag
	 */
	public void setMetricReadyFlag(String metricReadyFlag) {
		this.metricReadyFlag = metricReadyFlag;
	}

	/**
	 * Gets the metric run mode.
	 *
	 * @return the metric run mode
	 */
	public BigDecimal getMetricRunMode() {
		return this.metricRunMode;
	}

	/**
	 * Sets the metric run mode.
	 *
	 * @param metricRunMode the new metric run mode
	 */
	public void setMetricRunMode(BigDecimal metricRunMode) {
		this.metricRunMode = metricRunMode;
	}

	/**
	 * Gets the metric sql.
	 *
	 * @return the metric sql
	 */
	public String getMetricSql() {
		return this.metricSql;
	}

	/**
	 * Sets the metric sql.
	 *
	 * @param metricSql the new metric sql
	 */
	public void setMetricSql(String metricSql) {
		this.metricSql = metricSql;
	}

	/**
	 * Gets the metric sub category.
	 *
	 * @return the metric sub category
	 */
	public BigDecimal getMetricSubCategory() {
		return this.metricSubCategory;
	}

	/**
	 * Sets the metric sub category.
	 *
	 * @param metricSubCategory the new metric sub category
	 */
	public void setMetricSubCategory(BigDecimal metricSubCategory) {
		this.metricSubCategory = metricSubCategory;
	}

	/**
	 * Gets the metric type.
	 *
	 * @return the metric type
	 */
	public BigDecimal getMetricType() {
		return this.metricType;
	}

	/**
	 * Sets the metric type.
	 *
	 * @param metricType the new metric type
	 */
	public void setMetricType(BigDecimal metricType) {
		this.metricType = metricType;
	}

	/**
	 * Gets the metric validated flag.
	 *
	 * @return the metric validated flag
	 */
	public String getMetricValidatedFlag() {
		return this.metricValidatedFlag;
	}

	/**
	 * Sets the metric validated flag.
	 *
	 * @param metricValidatedFlag the new metric validated flag
	 */
	public void setMetricValidatedFlag(String metricValidatedFlag) {
		this.metricValidatedFlag = metricValidatedFlag;
	}

	/**
	 * Gets the offline flag.
	 *
	 * @return the offline flag
	 */
	public String getOfflineFlag() {
		return this.offlineFlag;
	}

	/**
	 * Sets the offline flag.
	 *
	 * @param offlineFlag the new offline flag
	 */
	public void setOfflineFlag(String offlineFlag) {
		this.offlineFlag = offlineFlag;
	}

	/**
	 * Gets the offline template name.
	 *
	 * @return the offline template name
	 */
	public String getOfflineTemplateName() {
		return this.offlineTemplateName;
	}

	/**
	 * Sets the offline template name.
	 *
	 * @param offlineTemplateName the new offline template name
	 */
	public void setOfflineTemplateName(String offlineTemplateName) {
		this.offlineTemplateName = offlineTemplateName;
	}

	/**
	 * Gets the original metric id.
	 *
	 * @return the original metric id
	 */
	public BigDecimal getOriginalMetricId() {
		return this.originalMetricId;
	}

	/**
	 * Sets the original metric id.
	 *
	 * @param originalMetricId the new original metric id
	 */
	public void setOriginalMetricId(BigDecimal originalMetricId) {
		this.originalMetricId = originalMetricId;
	}

	/**
	 * Gets the parameter flag.
	 *
	 * @return the parameter flag
	 */
	public String getParameterFlag() {
		return this.parameterFlag;
	}

	/**
	 * Sets the parameter flag.
	 *
	 * @param parameterFlag the new parameter flag
	 */
	public void setParameterFlag(String parameterFlag) {
		this.parameterFlag = parameterFlag;
	}

	/**
	 * Gets the push provider.
	 *
	 * @return the push provider
	 */
	public BigDecimal getPushProvider() {
		return this.pushProvider;
	}

	/**
	 * Sets the push provider.
	 *
	 * @param pushProvider the new push provider
	 */
	public void setPushProvider(BigDecimal pushProvider) {
		this.pushProvider = pushProvider;
	}

	/**
	 * Gets the reassignment infolet id.
	 *
	 * @return the reassignment infolet id
	 */
	public BigDecimal getReassignmentInfoletId() {
		return this.reassignmentInfoletId;
	}

	/**
	 * Sets the reassignment infolet id.
	 *
	 * @param reassignmentInfoletId the new reassignment infolet id
	 */
	public void setReassignmentInfoletId(BigDecimal reassignmentInfoletId) {
		this.reassignmentInfoletId = reassignmentInfoletId;
	}

	/**
	 * Gets the result database name.
	 *
	 * @return the result database name
	 */
	public String getResultDatabaseName() {
		return this.resultDatabaseName;
	}

	/**
	 * Sets the result database name.
	 *
	 * @param resultDatabaseName the new result database name
	 */
	public void setResultDatabaseName(String resultDatabaseName) {
		this.resultDatabaseName = resultDatabaseName;
	}

	/**
	 * Gets the result store table name.
	 *
	 * @return the result store table name
	 */
	public String getResultStoreTableName() {
		return this.resultStoreTableName;
	}

	/**
	 * Sets the result store table name.
	 *
	 * @param resultStoreTableName the new result store table name
	 */
	public void setResultStoreTableName(String resultStoreTableName) {
		this.resultStoreTableName = resultStoreTableName;
	}

	/**
	 * Gets the result table name.
	 *
	 * @return the result table name
	 */
	public String getResultTableName() {
		return this.resultTableName;
	}

	/**
	 * Sets the result table name.
	 *
	 * @param resultTableName the new result table name
	 */
	public void setResultTableName(String resultTableName) {
		this.resultTableName = resultTableName;
	}

	/**
	 * Gets the selfstoring flag.
	 *
	 * @return the selfstoring flag
	 */
	public String getSelfstoringFlag() {
		return this.selfstoringFlag;
	}

	/**
	 * Sets the selfstoring flag.
	 *
	 * @param selfstoringFlag the new selfstoring flag
	 */
	public void setSelfstoringFlag(String selfstoringFlag) {
		this.selfstoringFlag = selfstoringFlag;
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
	 * Gets the submission template name.
	 *
	 * @return the submission template name
	 */
	public String getSubmissionTemplateName() {
		return this.submissionTemplateName;
	}

	/**
	 * Sets the submission template name.
	 *
	 * @param submissionTemplateName the new submission template name
	 */
	public void setSubmissionTemplateName(String submissionTemplateName) {
		this.submissionTemplateName = submissionTemplateName;
	}

	/**
	 * Gets the task altering condition id.
	 *
	 * @return the task altering condition id
	 */
	public BigDecimal getTaskAlteringConditionId() {
		return this.taskAlteringConditionId;
	}

	/**
	 * Sets the task altering condition id.
	 *
	 * @param taskAlteringConditionId the new task altering condition id
	 */
	public void setTaskAlteringConditionId(BigDecimal taskAlteringConditionId) {
		this.taskAlteringConditionId = taskAlteringConditionId;
	}

	/**
	 * Gets the task id.
	 *
	 * @return the task id
	 */
	public BigDecimal getTaskId() {
		return this.taskId;
	}

	/**
	 * Sets the task id.
	 *
	 * @param taskId the new task id
	 */
	public void setTaskId(BigDecimal taskId) {
		this.taskId = taskId;
	}

	/**
	 * Gets the template name.
	 *
	 * @return the template name
	 */
	public String getTemplateName() {
		return this.templateName;
	}

	/**
	 * Sets the template name.
	 *
	 * @param templateName the new template name
	 */
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	/**
	 * Gets the template type.
	 *
	 * @return the template type
	 */
	public String getTemplateType() {
		return this.templateType;
	}

	/**
	 * Sets the template type.
	 *
	 * @param templateType the new template type
	 */
	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}

	/**
	 * Gets the threshold id.
	 *
	 * @return the threshold id
	 */
	public BigDecimal getThresholdId() {
		return this.thresholdId;
	}

	/**
	 * Sets the threshold id.
	 *
	 * @param thresholdId the new threshold id
	 */
	public void setThresholdId(BigDecimal thresholdId) {
		this.thresholdId = thresholdId;
	}

	/**
	 * Gets the use for reassignment flag.
	 *
	 * @return the use for reassignment flag
	 */
	public String getUseForReassignmentFlag() {
		return this.useForReassignmentFlag;
	}

	/**
	 * Sets the use for reassignment flag.
	 *
	 * @param useForReassignmentFlag the new use for reassignment flag
	 */
	public void setUseForReassignmentFlag(String useForReassignmentFlag) {
		this.useForReassignmentFlag = useForReassignmentFlag;
	}

	/**
	 * Gets the ws flag.
	 *
	 * @return the ws flag
	 */
	public String getWsFlag() {
		return this.wsFlag;
	}

	/**
	 * Sets the ws flag.
	 *
	 * @param wsFlag the new ws flag
	 */
	public void setWsFlag(String wsFlag) {
		this.wsFlag = wsFlag;
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