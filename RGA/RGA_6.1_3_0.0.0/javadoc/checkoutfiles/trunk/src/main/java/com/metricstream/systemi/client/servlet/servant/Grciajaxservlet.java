package com.metricstream.systemi.client.servlet.servant;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.xml.bind.JAXBContext;

import org.springframework.data.mongodb.core.MongoTemplate;

import com.metricstream.log.Logger;
import com.metricstream.systemi.ext.infolet.ChannelFeedAggregatorInfolet;
import com.metricstream.systemi.ext.infolet.EmailIMAPServer;
import com.metricstream.systemi.ext.infolet.EmailPopServer;
import com.metricstream.systemi.ext.infolet.RSSUrlConn;
import com.metricstream.systemi.rga.bean.ActivationBean;
import com.metricstream.systemi.rga.bean.ActivationBeanList;
import com.metricstream.systemi.rga.bean.ValidatedActivationDetailBean;
import com.metricstream.systemi.rga.dao.DBManager;
import com.metricstream.systemi.sql.SQLHelper;
import com.metricstream.systemi.utils.EncryptUtils;
import com.metricstream.systemi.utils.GsonUtil;
import com.msi.grcintelligence.utility.mongodb.CFRAnnualUtil;

/**
 * The class contains the business logic for the ajax calls made from the appstudio forms.
 * 
 * @author manish.svk
 */
public class Grciajaxservlet extends BaseFormProcessor {
	/** The Constant CLASS_ID. */
	private static final String CLASS_ID = "GRCIntelligenceAjaxServlet";
	/** The Constant serialVersionUID. */
	static final long serialVersionUID = 0;
	/** The Constant EMAIL_SOURCE. */
	static final String EMAIL_SOURCE = "1";
	/** The Constant RSS_SOURCE. */
	static final String RSS_SOURCE = "2";
	/** The Constant GRCI_SOURCE. */
	static final String GRCI_SOURCE = "3";
	/** The Constant activationlength. */
	static final int activationlength = 36;
	/** The mongo template. */
	MongoTemplate mongoTemplate;
	/** The grci properties. */
	public Properties grciProperties = DBManager.getInstance().getAllProperties();
	/** The rest url. */
	String restUrl = grciProperties.getProperty("REST_Url");
	/** The rest url port. */
	String restUrlPort = grciProperties.getProperty("REST_URL_Port_Number");
	/** The module name. */
	String moduleName = grciProperties.getProperty("REST_Module_Name");
	/** The validate server. */
	String VALIDATE_SERVER = "http://" + restUrl + ":" + restUrlPort + "/" + moduleName + "/rest/services/validator/";
	/** The activate server. */
	String ACTIVATE_SERVER = "http://" + restUrl + ":" + restUrlPort + "/" + moduleName + "/rest/services/activation/";
	/** The refresh server. */
	String REFRESH_SERVER = "http://" + restUrl + ":" + restUrlPort + "/" + moduleName + "/rest/services/latest/";

	/**
	 * Gets the cancel page.
	 *
	 * @return the cancel page
	 */
	@Override
	String getCancelPage() {
		return null;
	}

	/**
	 * Gets the submit page.
	 *
	 * @return the submit page
	 */
	@Override
	String getSubmitPage() {
		return null;
	}

	/**
	 * Gets the template name.
	 *
	 * @return the template name
	 */
	@Override
	String getTemplateName() {
		return null;
	}

	/**
	 * Produce page data.
	 *
	 * @return the hash map
	 */
	@SuppressWarnings("rawtypes")
	@Override
	HashMap producePageData() {
		return null;
	}

	/**
	 * Validate form data.
	 *
	 * @return the hash map
	 */
	@SuppressWarnings("rawtypes")
	@Override
	HashMap validateFormData() {
		return null;
	}

	/*
	 * 
	 * /** Ajax call to fetch enquiry result from the UI and send back response in string format.
	 * 
	 * @return dow jones xml data
	 * 
	 * @throws Throwable the throwable
	 */
//	public String produceGetDowJonesEnquiryData() throws Throwable{
//		StringBuilder result=new StringBuilder();
//		String name = getStringParameterValue("name", null, 0);
//		String contentSet = getStringParameterValue("contentSet", null, 0);
//		String date = getStringParameterValue("date", null, 0);
//		String firstName=getStringParameterValue("firstName", null, 0);
//		String middleName=getStringParameterValue("middleName", null, 0);
//		String surName=getStringParameterValue("surName", null, 0);
//		String entity=getStringParameterValue("entity", null, 0);
//		String searchType=getStringParameterValue("searchType", null, 0);
//		String id=getStringParameterValue("id", null, 0);
//		String searchOption = getStringParameterValue("searchOption", null, 0);
//		DowJonesRequest sc =null;
//		DowjonesSearch obj = new DowjonesSearch();
//		List<Match> list=null;
//		DowjonesRestCall c = new DowjonesRestCall();
//		
//		if(null!=id && !id.equals("")){
//			// id search
//			try {
//				obj.setContentset(contentSet);
//				obj.setDateofbirth(date);
//				obj.setPeid(id);
//				obj.setDowjonessearchtype(DowjonesConstants.DOWJONES_PERSONNAMESEARCH.toString());
//				sc= com.metricstream.systemi.utils.Utils.convertToCollection(obj);
//				//list="";//c.processRequest(obj, Utils.getType(sc.getDowjonessearchtype()),true);
//				result.append(c.processPeidRequest(obj, Utils.getType(sc.getDowjonessearchtype()),true));
//				return result.toString();				
//			}catch(Exception ex){
//				ex.printStackTrace();
//			}
//		}else{
//			// other search
//			if(searchOption.equalsIgnoreCase("first")){
//				// name search
//				obj.setContentset(contentSet);
//				obj.setDateofbirth(date);
//				obj.setName(name);
//				obj.setDowjonessearchtype(DowjonesConstants.DOWJONES_NAMESEARCH_SEARCH_URL.toString());
//				sc= com.metricstream.systemi.utils.Utils.convertToCollection(obj);
//			}else{
//				// person/entity saerch
//				obj.setContentset(contentSet);
//				obj.setDateofbirth(date);
//				obj.setName(name);
//				obj.setFirstname(firstName);
//				obj.setMiddlename(middleName);
//				obj.setSurname(surName);
//				obj.setEntityname(entity);
//				if(searchType.equalsIgnoreCase("person"))
//					obj.setDowjonessearchtype(DowjonesConstants.DOWJONES_PERSONSEARCH_SEARCH_URL.toString());
//				else
//					obj.setDowjonessearchtype(DowjonesConstants.DOWJONES_ENTITY_SEARCH_URL.toString());
//
//				sc= com.metricstream.systemi.utils.Utils.convertToCollection(obj);
//			}
//		}
//		
//		try {
//			list=c.processRequest(obj, Utils.getType(sc.getDowjonessearchtype()),true);
//			for(Match m:list){
//				ClassLoader cl = com.metricstream.systemi.rga.dowjones.pojo.SearchResultsType.Match.class.getClassLoader();
//				JAXBObjectToXML objectToXML = new JAXBObjectToXML("com.metricstream.systemi.rga.dowjones.pojo.SearchResultsType", cl);
//				File f1=new File("/opt/tempEnq");
//				File f=new File("/opt/tempEnq/test"+m.getPeid()+".xml");
//				try {
//					if(!f1.exists())
//						f1.mkdir();
//					objectToXML.generateXML(f, m);
//				} catch (JAXBException e) {
//					e.printStackTrace();
//				}		
//
//				StringBuilder out = new StringBuilder();
//				try {
//					InputStream in = new FileInputStream(f);
//					BufferedReader reader = new BufferedReader(new InputStreamReader(in));
//
//					String line;
//					while ((line = reader.readLine()) != null) {
//						out.append(line);
//					}
//					String outString = out.toString();
//					result.append("<br><br>");
//					result.append(outString);
//				} catch (Exception e) {
//					Logger.error("GetDowjOnesEnquiry", "Error in downjones enqiry search", e);
//				}
//			}
//		}catch(Exception ex){
//			ex.printStackTrace();
//		}
//		return result.toString();
//	}
	/**
	 * Updates the alert status data.
	 * 
	 * @return success/failure message
	 * @throws Throwable
	 *             the throwable
	 */
	public String produceAlertStatusUpdateData() throws Throwable {
		String ajaxCallType = getStringParameterValue("ajaxCallType", null, 0);
		String subsRespId = getStringParameterValue("subsRespId", null, 0);
		String userName = getStringParameterValue("userName", null, 0);
		String followUpRevValue = getStringParameterValue("followUpValue", null, 0);
		String routineQuery, response = null;
		Logger.debug(CLASS_ID, "Inside Ajax Servlet", null);
		if (ajaxCallType.equalsIgnoreCase("UPDATE_ALERT_REVIEW_STATUS")) {
			routineQuery = "{ call ms_rga_proc_view_manage_alerts.update_reviewed_channels(?,?,?,?,?) }";
			response = updateAlerts(subsRespId, userName, followUpRevValue, routineQuery);
		} else if (ajaxCallType.equalsIgnoreCase("UPDATE_ALERT_FOLLOWUP_STATUS")) {
			routineQuery = "{ call ms_rga_proc_view_manage_alerts.update_followup_channels(?,?,?,?,?) }";
			response = updateAlerts(subsRespId, userName, followUpRevValue, routineQuery);
		}
		return response;
	}

	/**
	 * Updates the alerts information.
	 * 
	 * @param subsRespId
	 *            the resp id
	 * @param userName
	 *            user name
	 * @param followUpValue
	 *            the follow up value
	 * @param routineQuery
	 *            the routine query
	 * @return success/failure message
	 */
	private String updateAlerts(String subsRespId, String userName, String followUpValue, String routineQuery) {
		Connection con = null;
		CallableStatement stmt = null;
		String response = "failure";
		try {
			con = getDefaultConnection(CLASS_ID, false);
			// Execute the SQL statement
			stmt = con.prepareCall(routineQuery);
			stmt.setString(1, subsRespId);
			stmt.setString(2, userName);
			stmt.setString(3, followUpValue);
			stmt.registerOutParameter(4, java.sql.Types.INTEGER);
			stmt.registerOutParameter(5, java.sql.Types.VARCHAR);
			stmt.execute();
			con.commit();
			int errCode = stmt.getInt(4);
			String errMesg = stmt.getString(5);
			if (errCode == 0) {
				response = "success";
			}
			Logger.debug(CLASS_ID, "Error code returned by ms_rga_proc_view_manage_alerts.update_followup/review_channels routine - " + errCode, null);
			Logger.debug(CLASS_ID, "Error message returned by ms_rga_proc_view_manage_alerts.update_followup/review_channels routine - " + errMesg, null);
		} catch (Exception e) {
			Logger.error(CLASS_ID, "An Exception happened while updating Alerts", e);
		} finally {
			SQLHelper.closeStatement(stmt);
			if (con != null) {
				try {
					returnConnection(con);
				} catch (Exception e) {
					Logger.error(CLASS_ID, "An Exception happened while closing connection", e);
				}
			}
		}
		return response;
	}

	/**
	 * Get the alert details from the database formType -> NOTIFY_USERS - for GET_URL_NOTIFY_USERS_FORM formType -> LOG_ISSUES - for GET_URL_LOG_ISSUE_FORM formType -> ORB_REL - for GET_URL_ORB_FORM
	 * 
	 * @return alert details
	 */
	public String producegetAlertSummeryReportDetailsData() {
		String formType = getStringParameterValue("formType", null, 0);
		String param1 = getStringParameterValue("param1", null, 0);
		String param2 = getStringParameterValue("param2", null, 0);
		String param3 = getStringParameterValue("param3", null, 0);
		String param4 = getStringParameterValue("param4", null, 0);
		String query = null;
		Logger.debug(CLASS_ID, "Grciajaxservlet::producegetAlertSummeryReportDetailsData formType --" + formType, null);
		if (formType.equals("NOTIFY_USERS")) {
			query = "{ call ? := ms_rga_utilities.GET_URL_NOTIFY_USERS_FORM(?,?,?,?)}";
		} else if (formType.equals("LOG_ISSUES")) {
			query = "{ call ? := ms_rga_utilities.GET_URL_LOG_ISSUE_FORM(?,?,?,?)}";
		} else if (formType.equals("ORB_REL")) {
			query = "{ call ? := ms_rga_utilities.GET_URL_ORB_FORM(?,?,?,?)}";
		} else if (formType.equals("MANAGE_ALERTS")) {
			query = "{ call ? := MS_RGA_UTILITIES.Get_Url_Manage_Alerts(?,?,?,?)}";
		}
		return DBManager.getInstance().callPLSqlFunction(query, param1, param2, param3, param4);
	}

	/**
	 * getPasswordDetails performs Ajax call to return the encrypted password details.
	 * 
	 * @return encrypted password details
	 */
	public String producegetPasswordDetailsData() {
		String pWord = getStringParameterValue("passWordDetails", null, 0);
		String rVal = EncryptUtils.getEncryptedDetails(pWord);
		Logger.info(CLASS_ID, "producegetPasswordDetailsData  ===> " + rVal, null);
		return rVal;
	}

	/**
	 * This method is called to Aggregate feeds.
	 * 
	 * @param srcIDs
	 *            source ids
	 * @return Action status message - Content refresh already running or No Result / Failed / Received <i>n</i> new updates
	 */
	public String aggregateFeeds(String srcIDs) {
		String srcIDList = getStringParameterValue("sourceIDList", null, 0).trim();
		// Run the aggregator now on the list of source IDs provided
		ChannelFeedAggregatorInfolet agg = new ChannelFeedAggregatorInfolet();
		return agg.aggregateNPopulateFeeds(srcIDs);
	}

	/**
	 * This method validates channel source data.
	 * 
	 * @return failure/activation code details
	 * @throws Throwable
	 *             the throwable
	 */
	public String produceValidateChannelSourceData() throws Throwable {
		String sourceType = getStringParameterValue("sourceType", null, 0).trim();
		Logger.info(CLASS_ID, "sourceType Code ===> " + sourceType, null);
		String address = "";
		String userId = "";
		String password = "";
		String serverParams = "";
		String actCode = "";
		String emailType = "";
		if (sourceType.equalsIgnoreCase("3")) {
			Logger.info(CLASS_ID, "activation Code ===> " + getStringParameterValue("activationCode", null, 0).trim(), null);
			actCode = getStringParameterValue("activationCode", null, 0).trim();
			Logger.info(CLASS_ID, "activation Code ===> " + actCode, null);
		} else {
			Logger.info(CLASS_ID, " other than content server validataion", null);
			address = getStringParameterValue("address", null, 0).trim();
			userId = getStringParameterValue("userId", null, 0).trim();
			password = getStringParameterValue("password", null, 0).trim();
			serverParams = getStringParameterValue("serverParams", null, 0).trim();
			emailType = getStringParameterValue("emailType", null, 0).trim();
		}
		String response = "failure";
		Logger.debug(CLASS_ID, "Inside Ajax Servlet", null);
		if (sourceType.equalsIgnoreCase(EMAIL_SOURCE)) {
			response = validateEmailServer(address, userId, password, serverParams, emailType);
		} else if (sourceType.equalsIgnoreCase(RSS_SOURCE)) {
			response = validateRSSServer(address, userId, password);
		} else if (sourceType.equalsIgnoreCase(GRCI_SOURCE)) {
			response = validateContentServer(actCode);
		}
		return response;
	}

	/**
	 * call agg infolet data.
	 * 
	 * @return Action status message - Content refresh already running or No Result / Failed / Received <i>n</i> new updates
	 * @throws Throwable
	 *             the throwable
	 */
	public String produceCallAggInfoletData() throws Throwable {
		String response = "failure";
		Logger.info(CLASS_ID, "Call aggregator infolet from report", null);
		ChannelFeedAggregatorInfolet infolet = new ChannelFeedAggregatorInfolet();
		try {
			String userName = (String) getSession().getAttribute(USER_LOGIN);
			String sourceIdsForUser = DBManager.getInstance().getSourceDetailsForUser(userName);
			response = infolet.aggregateNPopulateFeeds(sourceIdsForUser);
		} catch (Exception ex) {
			response = "Failure";
			Logger.error(CLASS_ID, "Error while invoking infolet from report ", ex);
		}
		return response;
	}

	/**
	 * Gets the latest activation details
	 * 
	 * @return failure / activation details
	 * @throws Throwable
	 *             the throwable
	 */
	public String produceRefreshSubscriptionDetailsData() throws Throwable {
		String response = "failure";
		Logger.info(CLASS_ID, "server URL --->" + VALIDATE_SERVER, null);
		Logger.info(CLASS_ID, "validateContentServer", null);
		String actCode = getStringParameterValue("activationCode", null, 0).trim();
		if (null != actCode && actCode.length() < activationlength) {
			return response;
		} else {
			try {
				Logger.info(CLASS_ID, "validateContentServer ", null);
				Logger.info(CLASS_ID, "server URL --->" + VALIDATE_SERVER, null);
				String uri = REFRESH_SERVER + actCode;
				URL url = new URL(uri);
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("GET");
				connection.setRequestProperty("Accept", "application/xml");
				JAXBContext jc = JAXBContext.newInstance(ValidatedActivationDetailBean.class);
				InputStream xml = connection.getInputStream();
				ValidatedActivationDetailBean activationBean = (ValidatedActivationDetailBean) jc.createUnmarshaller().unmarshal(xml);
				connection.disconnect();
				if (null == activationBean || activationBean.getContentType().length() == 0) {
					return response;
				} else {
					StringBuffer activationDetails = new StringBuffer();
					activationDetails.append(activationBean.getContentType());
					activationDetails.append("#");
					activationDetails.append(activationBean.getContentCategory());
					activationDetails.append("#");
					activationDetails.append(activationBean.getContentDeliveryFormat());
					activationDetails.append("#");
					activationDetails.append(activationBean.getStatus());
					activationDetails.append("#");
					activationDetails.append(activationBean.getDelivarySchedule());
					activationDetails.append("#");
					activationDetails.append(activationBean.getExpiryDate());
					response = activationDetails.toString();
					Logger.info(CLASS_ID, "Response ---> " + response, null);
				}
			} catch (Exception e) {
				Logger.error(CLASS_ID, "Exception ocurred at validationg Activation Code : ", e);
			}
		}
		return response;
	}

	/**
	 * This method validates the activation code.
	 * 
	 * @param actCode
	 *            activation code code
	 * @return failure/activation details
	 */
	private String validateContentServer(String actCode) {
		String response = "failure";
		Logger.info(CLASS_ID, "server URL --->" + VALIDATE_SERVER, null);
		Logger.info(CLASS_ID, "validateContentServer", null);
		if (null != actCode && actCode.length() < activationlength) {
			return response;
		} else {
			try {
				Logger.info(CLASS_ID, "validateContentServer ", null);
				Logger.info(CLASS_ID, "server URL --->" + VALIDATE_SERVER, null);
				String uri = VALIDATE_SERVER + actCode;
				URL url = new URL(uri);
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("PUT");
				connection.setRequestProperty("Accept", "application/xml");
				JAXBContext jc = JAXBContext.newInstance(ValidatedActivationDetailBean.class);
				InputStream xml = connection.getInputStream();
				ValidatedActivationDetailBean activationBean = (ValidatedActivationDetailBean) jc.createUnmarshaller().unmarshal(xml);
				connection.disconnect();
				if (null == activationBean || activationBean.getContentType().length() == 0) {
					return response;
				} else {
					StringBuffer activationDetails = new StringBuffer();
					activationDetails.append(activationBean.getContentType());
					activationDetails.append("#");
					activationDetails.append(activationBean.getContentCategory());
					activationDetails.append("#");
					activationDetails.append(activationBean.getContentDeliveryFormat());
					activationDetails.append("#");
					activationDetails.append(activationBean.getStatus());
					activationDetails.append("#");
					activationDetails.append(activationBean.getDelivarySchedule());
					activationDetails.append("#");
					activationDetails.append(activationBean.getExpiryDate());
					response = activationDetails.toString();
					Logger.info(CLASS_ID, "Response ---> " + response, null);
				}
			} catch (Exception e) {
				Logger.error(CLASS_ID, "Exception ocurred at validationg Activation Code : ", e);
			}
		}
		return response;
	}

	/**
	 * Activates the given activation codes
	 * 
	 * @return "Enter Valid Activation Code" / "No Details Available" / Activation Details
	 * @throws Throwable
	 *             the throwable
	 */
	public String produceActivateChannelSourceData() throws Throwable {
		String actCodes = getStringParameterValue("actCodeArray", null, 0).trim();
		String response = null;
		response = activateContentServer(actCodes);
		return response;
	}

	/**
	 * Activates the given activation codes
	 * 
	 * @param actCodes
	 *            activation codes
	 * @return "Enter Valid Activation Code" / "No Details Available" / Activation Details
	 */
	private String activateContentServer(String actCodes) {
		String[] actCode = actCodes.split(",");
		String response = "";
		List<ActivationBean> activationBean = new ArrayList<ActivationBean>();
		Logger.info(CLASS_ID, "server URL --->" + ACTIVATE_SERVER, null);
		try {
			String uri = ACTIVATE_SERVER + actCodes;
			URL url = new URL(uri);
			HttpURLConnection connection;
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("PUT");
			connection.setRequestProperty("Accept", "application/xml");
			JAXBContext jc = JAXBContext.newInstance(ActivationBeanList.class);
			InputStream xml = connection.getInputStream();
			Logger.info(CLASS_ID, "Response ---> " + xml, null);
			ActivationBeanList activationBeanList = (ActivationBeanList) jc.createUnmarshaller().unmarshal(xml);
			activationBean = activationBeanList.getActivationCode();
			Logger.info(CLASS_ID, "Response ---> " + activationBean, null);
			connection.disconnect();
		} catch (Exception e) {
			Logger.error(CLASS_ID, "Exception ocurred at Activating Activation Code : ", e);
			e.printStackTrace();
		}
		if (null != activationBean && activationBean.size() > 0) {
			StringBuffer activationDetails = new StringBuffer();
			for (int i = 0; i <= actCode.length - 1; i++) {
				for (ActivationBean bean : activationBean) {
					if (actCode[i].equalsIgnoreCase(bean.getActivationCode())) {
						if (null == actCode[i] && actCode[i].length() < activationlength) {
							response = "Enter Valid Activation Code";
							return response;
						} else {
							try {
								Logger.info(CLASS_ID, "validateContentServer 11111111", null);
								if (null == bean || bean.getActivationCode().length() == 0) {
									response = "No Details Available";
									Logger.info(CLASS_ID, "Response 1111---> " + response, null);
								} else {
									activationDetails.append(bean.getActivationCode());
									activationDetails.append("#");
									activationDetails.append(bean.getActivationCodeGenDate());
									activationDetails.append("#");
									activationDetails.append(bean.getActivationDate());
									activationDetails.append("#");
									activationDetails.append(bean.getActivationExpiryDate());
									activationDetails.append("#");
									activationDetails.append(bean.getActivationStatus());
									activationDetails.append("#");
									activationDetails.append(bean.getQueueName());
									activationDetails.append("#");
									activationDetails.append(bean.getQueueURL());
									if (i != actCode.length - 1) {
										activationDetails.append(";");
									}
								}
							} catch (Exception e) {
								response = e.getMessage();
							}
						}
						break;
					} else {
						Logger.info(CLASS_ID, "Response ---> " + response, null);
					}
				}
				response = activationDetails.toString();
				Logger.info(CLASS_ID, "Activation code nut eqaul ---> ", null);
			}
		} else {
			Logger.info(CLASS_ID, "Details not matched ---> " + response, null);
		}
		return response;
	}

	/**
	 * This method validates the email server.
	 * 
	 * @param address
	 *            email address
	 * @param userID
	 *            user id
	 * @param password
	 *            password
	 * @param serverParams
	 *            server parameters
	 * @return Success/Failure
	 */
	private String validateEmailServer(String address, String userID, String password, String serverParams, String emailType) {
		if(emailType.equalsIgnoreCase("IMAP")){
			return EmailIMAPServer.validate(address, userID, password, serverParams);
		}
		else if(emailType.equalsIgnoreCase("POP3")){
			return EmailPopServer.validate(address, userID, password, serverParams);
		}
		return null;
	}

	/**
	 * This method validates the RSS URL.
	 * 
	 * @param RSS
	 *            url
	 * @param userID
	 *            the user id
	 * @param password
	 *            the password
	 * @return Success/Failure
	 */
	private String validateRSSServer(String address, String userID, String password) {
		return RSSUrlConn.validate(address, userID, password);
	}

	/**
	 * This method gets the level 1 data stored in client mongo.
	 * 
	 * @return List of content details
	 * @throws Throwable
	 *             the throwable
	 */
	public String produceContentBrowserDetailsData() throws Throwable {
		Logger.debug(CLASS_ID, "Inside Ajax Servlet - produceContentBrowserDetailsData", null);
		String response = null;
		String strContentProvider = getStringParameterValue("contentProvider", null, 0);
		String strTitle = getStringParameterValue("title", null, 0);
		String strVersion = getStringParameterValue("version", null, 0);
		String strParentObjName = getStringParameterValue("parentName", null, 0);
		if (strParentObjName.isEmpty()) {
			strParentObjName = null;
		}
		List resultList = new CFRAnnualUtil().getDetails(strContentProvider, strTitle, strVersion, strParentObjName);
		response = GsonUtil.writeGson(resultList);
		Logger.debug(CLASS_ID, "Returning result of size:" + resultList.size(), null);
		return response;
	}
}
