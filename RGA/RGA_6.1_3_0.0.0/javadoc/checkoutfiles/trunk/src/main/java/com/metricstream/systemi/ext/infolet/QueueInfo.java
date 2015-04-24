package com.metricstream.systemi.ext.infolet;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import com.metricstream.log.Logger;
import com.metricstream.systemi.constant.Property;
import com.metricstream.systemi.ext.task.StreamContentDownloadTask;
import com.metricstream.systemi.jpa.util.JPAManager;
import com.metricstream.systemi.rga.dao.DBInstance;
import com.metricstream.systemi.rga.dao.DBManager;
import com.metricstream.systemi.rga.domain.QueueInfoDomain;
import com.metricstream.systemi.rga.domain.QueueMessageIdInfo;
import com.metricstream.systemi.server.common.Controller;
import com.metricstream.systemi.utils.GsonUtil;
import com.msi.grcintelligence.cpi.data.CPIDistributionDetails;
import com.msi.grcintelligence.jaxbObj.cfrFileFormat.GrcfObject;
import com.msi.grcintelligence.jaxbObj.cfrFileFormat.Headerrow;
import com.msi.grcintelligence.jaxbObj.cfrFileFormat.Orbrow;
import com.msi.grcintelligence.streamcontent.StreamDetailsObject;
import com.msi.grcintelligence.util.AggregationContentData;
import com.msi.grcintelligence.util.GRCIConstants;

/**
 * Captures the Active MQ information Created by munavar.basha on 6/19/2014.
 */
public class QueueInfo {
	private static String CUSTOM_TEMPLATE = "/custom_templates";
	private static String CUSTOM_ATTACHMENTS_SCORM = "/Systemi/attachments/Scorm/";
	/** The responses. */
	static Properties responses = new Properties();
	/** The grci properties. */
	public static Properties grciProperties = null;
	static {
		responses.setProperty("200", "If a GET, PUT, or DELETE request succeeds");
		responses.setProperty("303", "If a POST request succeeds.");
		responses.setProperty("304", "If the resource cannot be modified at the current time.");
		responses.setProperty("400", "The request could not be understood by the server due to malformed syntax. The client SHOULD NOT repeat the request without modifications");
		responses.setProperty("404", "The resource cannot be found");
		responses.setProperty("401", "The user does not have access to the resource.");
		responses.setProperty("50x", "An internal server error occurred while processing the request");
		grciProperties =  DBInstance.getInstance().getDbInstance().getAllProperties();
	}

	/**
	 * Method to get the queue info for given queue name This method should be synchronized, if not multiple threads accessing queue which leads to data consumption in inconsistent way. Also added timeout parameter to 1 sec.
	 *
	 * @param selector
	 *            the selector
	 * @param url
	 *            the url
	 * @return the queue info
	 * @throws Exception
	 *             the exception
	 */
	public synchronized QueueInfoDomain getQueueInfo(String selector, String url) throws Exception {
		List<QueueMessageIdInfo> messageIdList = new ArrayList<QueueMessageIdInfo>();
		List<Object> obList = new ArrayList<Object>();
		String urls = url + "?readTimeout=1000&type=queue&clientId=null";
		String removeConUrl = url + "?clientId=null&action=unsubscribe";
		boolean checkForMessages = true;
		// loop till we get no content in response
		while (checkForMessages) {
			// mew code
			URL urlCon = new URL(urls);
			HttpURLConnection connection = (HttpURLConnection) urlCon.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "application/json");
			String queueSelector = "activemq_HYPHEN_selector='" + selector + "'";
			connection.setRequestProperty("selector", queueSelector);
			// Object data = connection.getContent();
			int resp = connection.getResponseCode();
			String responseMsg = responses.getProperty(resp + "");
			if (responseMsg == null) {
				responseMsg = "Unhandled ResponseCode : " + resp;
			}
			Logger.debug("QueueInfo", "Starting to query queue... => " + urls + " selector " + queueSelector, null);
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				StringBuilder sb = new StringBuilder();
				String line;
				while ((line = br.readLine()) != null) {
					sb.append(line + "\n");
				}
				br.close();
				String output = sb.toString();
				switch (resp) {
				case 200: {
					Logger.info(urls + "||" + selector, " Queue content... => " + output, null);
					String messageId = connection.getHeaderField("uniqueId").toString();// connection.getHeaderField("id").toString()
					System.out.println(" unique id " + messageId);
					Logger.debug("QueueInfo", ",unique id... => " + messageId, null);
					// updateMessageDetail(messageId);
					messageIdList.add(new QueueMessageIdInfo(messageId));
					if (output != null || !output.trim().equals("")) {
						QueueMessageObject ob = (QueueMessageObject) GsonUtil.readGson(output, QueueMessageObject.class);
						Logger.debug("Queue INfo", " its an " + ob.getProviderName() + " data", null);
						if (ob.getProviderName().equalsIgnoreCase("CFRAnnual")) {
							GrcfObject obj = (GrcfObject) GsonUtil.readGson(ob.getObject(), GrcfObject.class);
							obList.add(obj);
							Logger.debug("Queue INfo", " adding in " + ob.getProviderName() + " data", null);
						} else if ("Scorm Training Content".equalsIgnoreCase(ob.getProviderName())) {
							StreamDetailsObject streamDetails = (StreamDetailsObject) GsonUtil.readGson(ob.getObject(), StreamDetailsObject.class);
							String contentLocation = streamDetails.getUrl();
							// Getting the download location and creating the directory
							String downloadLocation = Controller.getProperties().getProperty(Property.CUSTOM_TEMPLATE_ROOT, "");
							downloadLocation = downloadLocation.replaceAll(CUSTOM_TEMPLATE, CUSTOM_ATTACHMENTS_SCORM);
							Logger.debug("Queue INfo", "CPI download location:- " + downloadLocation, null);
							File downloadDirectory = new File(downloadLocation);
							if (!downloadDirectory.exists()) {
								downloadDirectory.mkdirs();
							}
							// Read scorm content
							StreamContentDownloadTask task = new StreamContentDownloadTask(contentLocation, downloadLocation + streamDetails.getFileName());
							task.execute();
						} else if (GRCIConstants.CPI_CONTENT_PROVIDER_NAME.equalsIgnoreCase(ob.getProviderName())) {
							Logger.info("QueueInfo", "Getting the CPI data from queue", null);
							CPIDistributionDetails distributionDetails = (CPIDistributionDetails) GsonUtil.readGson(ob.getObject(), CPIDistributionDetails.class);
							Logger.info("QueueInfo", "Read CPI JSON data successfully", null);
							obList.add(distributionDetails);
						} else {
							AggregationContentData aggData = (AggregationContentData) GsonUtil.readGson(ob.getObject(), AggregationContentData.class);
							obList.add(aggData);
							Logger.debug("Queue INfo", " adding in  " + ob.getProviderName() + " data", null);
						}
					} else {
						Logger.warning("QueueInfo", "Null or Empty content received => " + output, null);
					}
					break;
				}
				case 501 - 509:
					// 50x: If an internal server error occurs while processing the request.
					Logger.error("QueueInfo", resp + responses.getProperty("50x"), null);
					// don't break - fall through
				case 204:
				default:
					if (resp == 204) { // handle fall through cases
						Logger.info("QueueInfo", "exiting as there is no content ... => " + resp, null);
					} else {
						Logger.debug("QueueInfo", "Response = " + resp + " : " + responseMsg, null);
					}
					checkForMessages = false;
					removeConsumer(removeConUrl);
					break;
				}
				connection.disconnect();
			} catch (Exception ex) {
				Logger.error("QueueInfo", "Error in takeing data from queue", null, null);
			}
		}
		try {
			removeConsumer(removeConUrl);
		} catch (Exception ex) {
		}
		Logger.debug(" Queue info ", " Queue Remove url  " + removeConUrl, null);
		QueueInfoDomain domain = new QueueInfoDomain();
		domain.setList(obList);
		domain.setMessageIdList(messageIdList);
		return domain;
	}

	/**
	 * Remove consumer from active MQ queue.
	 * 
	 * @param url
	 *            message queue url
	 * @return Removing consumer status code
	 * @throws Exception
	 *             the exception
	 */
	public static int removeConsumer(String url) throws Exception {
		int result = 0;
		try {
			URL url1 = new URL(url);
			HttpURLConnection httpClient = (HttpURLConnection) url1.openConnection();
			httpClient.setRequestMethod("POST");
			httpClient.setRequestProperty("Accept", "application/json");
			System.out.println(httpClient.getResponseCode());
			httpClient.connect();
			System.out.println("QueueInfo" + "Removing consumer status code is " + result);
			httpClient.disconnect();
		} catch (Exception ex) {
			Logger.error("Queue remove ", "error while removing the consumer", ex);
			ex.printStackTrace();
		}
		return result;
	}

	public static void main(String args[]) {
		try {
			// removeConsumer("http://localhost:8161/api/message/a84e71cf-3d1d-4e51-b0ef-40462b42d82d?clientId=null&action=unsubscribe");
			// new QueueInfo().updateMessageDetail("ID:MSI-EEE117-58668-1413728351320-1:6:1:1:1");
			URL urlCon = new URL("http://simpro405:8161/api/message/jms:queue:1e699e87-6a91-497c-bb74-47340be5bc22?type=queue?type=queue");
			HttpURLConnection connection = (HttpURLConnection) urlCon.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "application/json");
			// String queueSelector="activemq_HYPHEN_selector='"+selector+"'";
			// connection.setRequestProperty("selector", queueSelector);
			// Object data = connection.getContent();
			int resp = connection.getResponseCode();
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line + "\n");
			}
			br.close();
			String output = sb.toString();
			if (output != null || !output.trim().equals("")) {
				String messageId = connection.getHeaderField("uniqueId").toString();// connection.getHeaderField("id").toString()
				System.out.println(" unique id " + messageId);
				QueueMessageObject ob = (QueueMessageObject) GsonUtil.readGson(output, QueueMessageObject.class);
				if (ob.getProviderName().equalsIgnoreCase("CFRAnnual")) {
					GrcfObject obj = (GrcfObject) GsonUtil.readGson(ob.getObject(), GrcfObject.class);
					System.out.println(" obj " + obj);
					System.out.println(obj.getGrcfobjectname());
					System.out.println(obj.getVersion());
					List<Headerrow> hr = obj.getHeader().getHeaderrow();
					List<Orbrow> or = obj.getOrb().getOrbrow();
				}
			} else {
				Logger.warning("QueueInfo", "Null or Empty content received => " + output, null);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Method to update the status of message id in mongo db.
	 * 
	 * @param messageId
	 *            message id
	 */
	public static void updateMessageDetail(List<QueueMessageIdInfo> messageIdList) {
		try {
			String date = Calendar.getInstance().getTime().toString();
			String restUrl = grciProperties.getProperty("REST_Distribution_url");
			String restUrlPort = grciProperties.getProperty("REST_Distribution_port");
			String URL = "http://" + restUrl + ":" + restUrlPort + "/distribution/REST/Distribution/monitorUpdate/";
			Logger.debug("Queue info", "Rest url is " + URL, null);
			StringBuilder stringBuilder = new StringBuilder();
			for (QueueMessageIdInfo queueMessageIdInfo : messageIdList) {
				stringBuilder.append(queueMessageIdInfo.getMessageId() + ",");
			}
			String uri = URL + stringBuilder.toString();
			URL url = new URL(uri);
			HttpURLConnection connection;
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Accept", "application/json");
			int status = connection.getResponseCode();
		} catch (Exception e) {
			Logger.error("Active MQ Monitoring", "error while updating delivery time " + e.getMessage(), e);
		}
	}
}
