package com.metricstream.systemi.rga.api;

import com.metricstream.log.Logger;
import com.metricstream.systemi.jpa.util.JPAManager;
import com.metricstream.systemi.rga.dao.DBInstance;
import com.metricstream.systemi.rga.dao.DBManager;
import com.metricstream.systemi.rga.dao.IDBHelper;
import com.metricstream.systemi.rga.domain.Alert;
import com.metricstream.systemi.rga.domain.Channel;
import com.metricstream.systemi.rga.exception.GrciException;
import com.metricstream.systemi.rga.exception.IllegalChannelStateException;

/**
 * Creates an alert in application
 * Created by munavar.basha on 7/8/2014.
 */
public class AlertApi {

	/** The Constant CLASS_ID. */
	private static final String CLASS_ID = "AlertApi";


	private  IDBHelper dbInstance = DBInstance.getInstance().getDbInstance();
	/**
	 * Instantiates a new alert api.
	 */
	public AlertApi() {
	}

	/**
	 * Adds the alert.
	 * @param alert the alert
	 * @throws GrciException the grci exception
	 */
	public void addAlert(Alert alert) throws GrciException {
		Logger.error("AlertApi", "inside addAlert", null);
		if (alert == null) {
			throw new GrciException("Cannot add a null value for alert");
		}
		validate(alert);
		constructChannelDetailsFromChannelName(alert);
		dbInstance.addAlert(alert);
	}

	/**
	 * Process alerts in application.
	 */
	public void processAlerts(){
		// invoke RGA-Channel Response infolet to segregate alerts to respective channels
		dbInstance.callPlSqlInfolet("{ call Ms_Rga_Process_Channel_Resp.Pr_Populate_Channel_Response(?,?) }");
		// invoke RGA-Subbscription Response infolet to segregate alerts to respective subscriptions to channels
		dbInstance.callPlSqlInfolet("{ call Ms_Rga_Process_Subscrptn_Resp.Pr_Populate_Subscrptn_Resp(?,?) }");

		Logger.info(CLASS_ID, "AlertApi::processAlerts is called", null) ;
	}

	/**
	 * Construct channel details from channel name.
	 * @param alert the alert object
	 * @throws GrciException the grci exception
	 */
	private void constructChannelDetailsFromChannelName(Alert alert) throws GrciException {
		Logger.error("AlertApi", "inside constructChannelDetailsFromChannelName", null);
		Channel channel = alert.getChannel();
		dbInstance.constructChannelDetails(channel);
		if (channel.getId().equals("UNKNOWN")) {
			throw new IllegalChannelStateException("Unknown channel:" + channel.getName());
		}
		alert.setChannel(channel);
	}

	/**
	 * Validates an alert
	 * @param alert the alert object
	 * @throws GrciException the grci exception
	 */
	private void validate(Alert alert) throws GrciException {
		Logger.error("AlertApi", "inside validate ", null);
		if ((alert.getBody() == null) || (alert.getBody().isEmpty())) {
			throw new GrciException("Alert body cannot be null or empty.");
		}
	}
}
