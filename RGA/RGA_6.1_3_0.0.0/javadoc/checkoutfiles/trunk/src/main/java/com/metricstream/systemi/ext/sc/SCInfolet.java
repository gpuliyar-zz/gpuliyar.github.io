package com.metricstream.systemi.ext.sc;

import java.sql.SQLException;
import java.util.Properties;

import com.metricstream.systemi.ext.infolet.ChannelFeedData;

import org.omg.CosEventComm.PushConsumer;

import com.metricstream.log.Logger;
import com.metricstream.systemi.interfaces.channel.ChannelListener;
import com.metricstream.systemi.jpa.util.JPAManager;
import com.metricstream.systemi.rga.dao.DBInstance;
import com.metricstream.systemi.rga.dao.DBManager;
import com.metricstream.systemi.rga.dao.IDBHelper;
import com.metricstream.systemi.server.access.InfoletRunner;
import com.metricstream.systemi.server.common.Controller;
import com.metricstream.systemi.utils.Utils;

/**
 * Infolet for Structured Content Data
 */
public class SCInfolet {

	/** The Constant CLASS_ID. */
	private static final String CLASS_ID = "SCInfolet";
	
	/** The db mgr. */
	private IDBHelper dbInstance = DBInstance.getInstance().getDbInstance();

	/**
	 * Instantiates a new SC infolet.
	 */
	public SCInfolet() {
		dbInstance = DBManager.getInstance();
	}

	/**
	 * Invoke infolet to perform business logic
	 *
	 * @param fData the f data
	 * @return the long
	 * @throws Exception the exception
	 */
	public long invokeInfolet(ChannelFeedData fData) throws Exception {

		Logger.debug(CLASS_ID, "invokeInfolet entry", null);
		long instanceId = getInstanceId();
		long processId = -1;

		int userId = getUserId();

		Properties connProps = null;
		boolean overSchedule = false;
		Controller controller = Controller.getThis();
		PushConsumer eventConsumer = null;
		boolean sendNotification = true;
		ChannelListener channelListener = null;

		long infoletId = Utils.getLongValue(fData.getStructuredContentInfoletId());
		if (infoletId == -1) {
			return -1;
		}
		Logger.debug(CLASS_ID, "Invoking infolet:" + infoletId
				+ " with Instanceid:" + instanceId, null);
        Object[] parameters = { fData.getServer(), fData.getSender(), fData.getRecipient(),
                                fData.getSubject(), fData.getBody(), fData.getAttachmentIDs(),
                                fData.getSaveAttachment(),
                                fData.getStructuredContentInfoletId(),
                                instanceId};
		InfoletRunner.run(  infoletId, instanceId, processId, userId, parameters,
                            connProps, overSchedule, controller, eventConsumer,
                            sendNotification, channelListener);
		Logger.debug(CLASS_ID, "done", null);

		return instanceId;
	}

	/**
	 * Gets the user id.
	 *
	 * @return the user id
	 * @throws SQLException the SQL exception
	 */
	private int getUserId() throws SQLException {
		return dbInstance.getUserId();
	}

	/**
	 * Gets the instance id.
	 *
	 * @return the instance id
	 * @throws SQLException the SQL exception
	 */
	private long getInstanceId() throws SQLException {
		return dbInstance.getInstanceId();
	}

}
