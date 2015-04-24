package com.metricstream.systemi.rga.dao;

import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.metricstream.log.Logger;
import com.metricstream.systemi.ext.infolet.ChannelFeedData;
import com.metricstream.systemi.ext.infolet.FeedXtraxnStats;
import com.metricstream.systemi.jpa.entity.MsRgaChannelDtl;
import com.metricstream.systemi.jpa.entity.MsRgaChannelDtlsSrc;
import com.metricstream.systemi.rga.domain.Alert;
import com.metricstream.systemi.rga.domain.Channel;
import com.metricstream.systemi.rga.domain.Source;
import com.metricstream.systemi.rga.domain.SourceInfo;
import com.metricstream.systemi.rga.domain.SourceLastUpdate;
import com.metricstream.systemi.rga.exception.GrciException;
import com.metricstream.systemi.rga.exception.IllegalChannelStateException;
import com.metricstream.systemi.rga.grcobject.GRCFMandatoryFields;
import com.metricstream.systemi.rga.grcobject.GRCFObjectDetails;
import com.metricstream.systemi.server.common.Controller;
import com.metricstream.systemi.sql.SQLHelper;
import com.metricstream.systemi.utils.EncryptUtils;
import com.metricstream.systemi.utils.Utils;
import com.msi.grcintelligence.utility.encryption.EncryptionUtilities;
import com.msi.grcintelligence.utility.mongodb.ContentWrapperObj;

/**
 * The Class DBManager.
 */
public class DBManager implements IDBHelper {
	/** The Constant CLASS_ID. */
	private static final String CLASS_ID = "DBManager";
	/** The db mgr. */
	private static DBManager dbMgr = null;

	/**
	 * Instantiates a new DB manager.
	 */
	private DBManager() {
	}

	/**
	 * Gets the single instance of DBManager.
	 *
	 * @return single instance of DBManager
	 */
	public static DBManager getInstance() {
		if (dbMgr == null) {
			dbMgr = new DBManager();
		}
		return dbMgr;
	}

	/**
	 * Gets the instance id.
	 *
	 * @return the instance id
	 * @throws SQLException
	 *             the SQL exception
	 */
	@Override
	public long getInstanceId() throws SQLException {
		Connection con = null;
		try {
			con = getConnection();
			return SQLHelper.getInstanceId(con, "SI_METRIC_RESULTS_S.NEXTVAL");
		} finally {
			returnConnection(con);
		}
	}

	/**
	 * Gets the user id.
	 *
	 * @return the user id
	 * @throws SQLException
	 *             the SQL exception
	 */
	@Override
	public int getUserId() throws SQLException {
		Logger.debug(CLASS_ID, "inside getUserId", null);
		String query = "select user_id from si_users where user_name = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, "SYSTEMI");
			rs = pstmt.executeQuery();
			int userId = -1;
			while (rs.next()) {
				userId = rs.getInt("user_id");
			}
			return userId;
		} finally {
			closeResouces(pstmt, rs, con);
		}
	}

	/**
	 * callPLSqlFunction is an generic function to execute function with 4 in String parameters.
	 *
	 * @param query
	 *            pl sql query
	 * @param val1
	 *            the val1
	 * @param val2
	 *            the val2
	 * @param val3
	 *            the val3
	 * @param val4
	 *            the val4
	 * @return String
	 */
	@Override
	public String callPLSqlFunction(String query, String val1, String val2, String val3, String val4) {
		CallableStatement cs = null;
		Connection con = null;
		String returnVal = null;
		Logger.debug(CLASS_ID, "DBManager::callPLSqlFunction query --" + query, null);
		try {
			con = getConnection();
			cs = con.prepareCall(query);
			cs.registerOutParameter(1, Types.CHAR);
			cs.setString(2, val1);
			cs.setString(3, val2);
			cs.setString(4, val3);
			cs.setString(5, val4);
			cs.execute();
			returnVal = cs.getString(1);
		} catch (SQLException e) {
			Logger.error(CLASS_ID, "SQLException", e);
		} finally {
			closeResouces(cs, null, con);
		}
		Logger.debug(CLASS_ID, "DBManager::callPLSqlFunction returnVal -- " + returnVal, null);
		return returnVal;
	}

	/**
	 * Send notifications.
	 *
	 * @param notifyMap
	 *            the notify map
	 */
	@Override
	public void sendNotifications(Map<Long, ChannelFeedData> notifyMap) {
		CallableStatement cs = null;
		Connection con = null;
		String query = "{ call ms_rga_utilities.send_email_infolet_inv_status(?,?,?,?,?,SYSDATE,SYSDATE,?,?,?,?,?,?)}";
		try {
			con = getConnection();
			cs = con.prepareCall(query);
			for (Entry<Long, ChannelFeedData> entry : notifyMap.entrySet()) {
				long instanceId = entry.getKey();
				ChannelFeedData fData = entry.getValue();
				long infoletId = Utils.getLongValue(fData.getStructuredContentInfoletId());
				cs.setLong(1, infoletId);
				cs.setString(2, getInvocationStatus(infoletId, instanceId));
				cs.setInt(3, (int) instanceId);
				cs.setInt(4, -1);
				cs.setString(5, fData.getChannelId() + "");// channel id
				cs.setString(6, fData.getSubject() + ""); // Subject
				cs.setString(7, fData.getRecipient() + ""); // Recepient
				cs.setString(8, fData.getSender() + ""); // sender
				cs.setString(9, fData.getServer() + ""); // server
				cs.registerOutParameter(10, Types.VARCHAR);
				cs.registerOutParameter(11, Types.VARCHAR);
				cs.execute();
				Logger.debug(CLASS_ID, "execution status = " + cs.getString(11), null);
				if (cs.getString(11).equals("SUCCESS")) {
					Logger.debug(CLASS_ID, "Succesfully added in mail queue for instance id:" + instanceId + " and infoletid:" + infoletId, null);
				} else {
					Logger.error(CLASS_ID, "procedure call for ms_rga_utilities.send_email_infolet_inv_status failed for instance id:" + instanceId + " and infoletid:" + infoletId, null);
				}
			}
		} catch (SQLException e) {
			Logger.error(CLASS_ID, "SQLException", e);
		} finally {
			closeResouces(cs, null, con);
		}
	}

	/**
	 * Send failure notifications.
	 *
	 * @param invalidFeedStats
	 *            the invalid feed stats
	 */
	@Override
	public void sendFailureNotifications(List<FeedXtraxnStats> invalidFeedStats) {
		CallableStatement cs = null;
		Connection con = null;
		String query = "{ call ms_rga_utilities.send_email_infolet_inv_status(?,?,?,?,?,SYSDATE,SYSDATE,?,?,?,?,?,?)}";
		try {
			con = getConnection();
			cs = con.prepareCall(query);
			for (FeedXtraxnStats stats : invalidFeedStats) {
				cs.setLong(1, Utils.getLongValue(stats.getScInfoletId()));
				cs.setString(2, "FAILURE");
				cs.setInt(3, -1);
				cs.setInt(4, -1);
				cs.setString(5, stats.getChannelId());
				cs.setString(6, null);
				cs.setString(7, null);
				cs.setString(8, null);
				cs.setString(9, stats.getFeedName());
				cs.registerOutParameter(10, Types.VARCHAR);
				cs.registerOutParameter(11, Types.VARCHAR);
				cs.execute();
				Logger.debug(CLASS_ID, "execution status = " + cs.getString(11), null);
				if (cs.getString(11).equals("SUCCESS")) {
					Logger.debug(CLASS_ID, "Succesfuly added in mail queue", null);
				} else {
					Logger.error(CLASS_ID, "procedure call for ms_rga_utilities.send_email_infolet_inv_status failed for channel id:" + stats.getChannelId() + " and infoletid:" + stats.getScInfoletId(), null);
				}
			}
		} catch (SQLException e) {
			Logger.error(CLASS_ID, "SQLException", e);
		} finally {
			closeResouces(cs, null, con);
		}
	}

	/**
	 * Populate notification table.
	 *
	 * @param notifyMap
	 *            the notify map
	 */
	@Override
	public void populateNotificationTable(Map<Long, Object[]> notifyMap) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String query = "Insert into MS_RGA_INFOLET_INVOCATION_DTLS(INFOLET_INVOKED, INFOLET_INVOCATION_STATUS, INSTANCE_ID, PROCESS_INSTANCE_ID, CHANNEL_ID, CREATION_DATE, LAST_UPDATED_DATE, SUBJECT, RECIPIENT, SENDER, SERVER) values (?,?,?,?,?,SYSDATE,SYSDATE,?,?,?,?)";
		int batchSize = 2000;
		int count = 0;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(query);
			for (Entry<Long, Object[]> entry : notifyMap.entrySet()) {
				long instanceId = entry.getKey();
				Object[] params = entry.getValue();
				long infoletId = Utils.getLongValue(params[9]);
				pstmt.setLong(1, infoletId);
				pstmt.setString(2, getInvocationStatus(infoletId, instanceId));
				pstmt.setLong(3, instanceId);
				pstmt.setInt(4, -1);
				pstmt.setString(5, params[11] + "");
				pstmt.setString(6, params[3] + "");
				pstmt.setString(7, params[2] + "");
				pstmt.setString(8, params[1] + "");
				pstmt.setString(9, params[0] + "");
				pstmt.addBatch();
				// execute current batch if size ==batchSize
				if (++count % batchSize == 0) {
					pstmt.executeBatch();
				}
			}
			// execute remaining items in batch
			pstmt.executeBatch();
		} catch (SQLException e) {
			Logger.error(CLASS_ID, "recieved SQL Exception", e);
		} finally {
			closeResouces(pstmt, null, con);
		}
	}

	/**
	 * Gets the invocation status.
	 *
	 * @param infoletId
	 *            the infolet id
	 * @param instanceId
	 *            the instance id
	 * @return the invocation status
	 */
	public String getInvocationStatus(long infoletId, long instanceId) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "select result from si_" + infoletId + "_t where instance_id =" + instanceId;
		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			String result = null;
			while (rs.next()) {
				result = rs.getString(1);
				break;
			}
			return result;
		} catch (SQLException e) {
			Logger.error(CLASS_ID, "recieved SQL Exception for query:" + query, e);
			return null;
		} finally {
			closeResouces(stmt, rs, con);
		}
	}

	/**
	 * Gets the connection.
	 *
	 * @return the connection
	 * @throws SQLException
	 *             the SQL exception
	 */
	@Override
	public Connection getConnection() throws SQLException {
		return Controller.getConnectionPool().getTransactionalConnection(CLASS_ID);
	}

	/**
	 * Close resouces.
	 *
	 * @param stmt
	 *            the stmt
	 * @param rs
	 *            the rs
	 * @param con
	 *            the con
	 */
	public void closeResouces(Statement stmt, ResultSet rs, Connection con) {
		closeResultSet(rs);
		closeStatement(stmt);
		returnConnection(con);
	}

	/**
	 * Close statement.
	 *
	 * @param stmt
	 *            the stmt
	 */
	public void closeStatement(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				Logger.error(CLASS_ID, "SQLException", e);
			}
		}
	}

	/**
	 * Close result set.
	 *
	 * @param rs
	 *            the rs
	 */
	public void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				Logger.error(CLASS_ID, "SQLException", e);
			}
		}
	}

	/**
	 * Return connection.
	 *
	 * @param con
	 *            the con
	 */
	public void returnConnection(Connection con) {
		if (con != null) {
			Logger.debug(CLASS_ID, "Returning connection...", null);
			Controller.getConnectionPool().returnConnection(con);
		}
	}

	/**
	 * Gets the all properties.
	 *
	 * @return the all properties
	 */
	@Override
	public Properties getAllProperties() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		String firstQuery="select parameter_type_id from ms_qs_parameter_types where parameter_type = 'MS_RGA_CONFIG'";
		String query = "select n.parameter_name, v.parameter_value,n.parameter_type_id from  ms_qs_parameter_names n Inner join ms_qs_parameter_values v on n.parameter_id = v.parameter_id where " + " n.parameter_type_id = (select parameter_type_id from ms_qs_parameter_types where parameter_type = 'MS_RGA_CONFIG')";
		try {
			Properties prop = new Properties();
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				Logger.debug(CLASS_ID, "getAllProperties--rs.getString(1): " + rs.getString(1), null);
				Logger.debug(CLASS_ID, "getAllProperties--rs.getString(2): " + rs.getString(2), null);
				prop.put(rs.getString(1), rs.getString(2));
			}
			return prop;
		} catch (SQLException e) {
			Logger.error(CLASS_ID, "recieved SQL Exception for query:" + query, e);
			return null;
		} finally {
			closeResouces(stmt, rs, con);
		}
	}

	/**
	 * Gets the sequence num.
	 *
	 * @param con
	 *            the con
	 * @return the sequence num
	 */
	@Override
	public int getSequenceNum(Connection con) {
		int id = 0;
		try {
			String query = "{? = call ms_rga_utilities.generate_sequence() }";
			// {? = CALL balance(?)}
			CallableStatement stmt = con.prepareCall(query);
			stmt.registerOutParameter(1, java.sql.Types.INTEGER);
			stmt.execute();
			id = stmt.getInt(1);
		} catch (Exception ex) {
			Logger.debug(CLASS_ID, "error while getting sequence for aggre...", ex);
			// Controller.getConnectionPool().returnConnection(con);
		}
		return id;
	}

	/**
	 * Method to get the channel id by activation code.
	 *
	 * @param channelId
	 *            the channel id
	 * @return the activation code
	 */
	@Override
	public List<String> getActivationCode(String channelId) {
		List<String> activationCode = new ArrayList<String>();
		Connection con = null;
		try {
			con = getConnection();
			String query = "select activation_code from ms_rga_channel_dtls_src where object_id= ?";
			// {? = CALL balance(?)}
			Logger.debug(CLASS_ID, " aaaaaaaaaaaa  " + query, null);
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, channelId);
			ResultSet rs = stmt.executeQuery();
			Logger.debug(CLASS_ID, " executed ", null);
			while (rs.next()) {
				activationCode.add(rs.getString(1));
			}
			Logger.debug(CLASS_ID, " executed ", null);
		} catch (Exception ex) {
			Logger.debug(CLASS_ID, "error while getting channel id from  ms_rga_channel_dtls_src table for activation code " + activationCode, ex);
			Controller.getConnectionPool().returnConnection(con);
		} finally {
			Controller.getConnectionPool().returnConnection(con);
		}
		return activationCode;
	}

	/**
	 * Write to aggregator table.
	 *
	 * @param feeds
	 *            the feeds
	 * @return the int
	 */
	public int writeToAggregatorTable(ArrayList<ChannelFeedData> feeds) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int retVal = -1;
		try {
			conn = Controller.getConnectionPool().getTransactionalConnection(CLASS_ID);
			stmt = conn.prepareStatement("insert into MS_RGA_AGGREGATOR (SERVER, SENDER, RECIPIENT, SUBJECT, BODY, ATTACHMENT, PROCESSED_FLAG, " + "FEED_DATA_ID, MSG_UID, FEED_SENT_DATE, STRUCTURED_CONTENT_ID, CHANNEL_ID, FEED_SEQUENCE,CREATION_DATE,SRC_PK) " + "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
			/*
			 * results[i][0] = cData.getServer(); results[i][1] = cData.getSender();results[i][2] = cData.getRecipient(); results[i][3] = cData.getSubject(); results[i][4] = cData.getBody(); results[i][5] = cData.getAttachmentIDs(); results[i][6] = ""; // processed_flag; results[i][7] = ""; // feed_data_id results[i][8] = cData.getMsgUID(); results[i][9] = cData.getSentDate(); if (cData.getStructuredContentInfoletId() != null || cData.getSaveAttachment() != null) { results[i][10] = cData.getStructuredContentInfoletId(); results[i][11] = cData.getChannelId(); results[i][12] = cData.getSaveAttachment(); results[i][13] = cData.getFileName(); }
			 */
			Logger.debug(CLASS_ID + " : writeToAggregatorTable", "Writing " + feeds.size() + " to MS_RGA_AGGREGATOR table ", null);
			for (ChannelFeedData feed : feeds) {
				stmt.setString(1, feed.getServer());
				stmt.setString(2, feed.getSender());
				stmt.setString(3, feed.getRecipient());
				stmt.setString(4, feed.getSubject());
				stmt.setString(6, feed.getAttachmentIDs());
				Logger.debug(CLASS_ID, " : writeToAggregatorTable -- feed.getMsgUID() -- " + feed.getMsgUID(), null);
				stmt.setString(7, "");
				stmt.setInt(8, 0);
				stmt.setString(9, feed.getMsgUID());
				stmt.setDate(10, new java.sql.Date(feed.getSentDate().getTime()));
				stmt.setLong(11, Utils.getLongValue(feed.getStructuredContentInfoletId()));
				stmt.setString(12, feed.getChannelId());
				stmt.setInt(13, getSequenceNum(conn));
				stmt.setDate(14, new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
				stmt.setString(15, feed.getSourceId());
				stmt.setString(5, feed.getBody());
				Logger.debug(CLASS_ID, " : writeToAggregatorTable -- feed.getSourceId() -- " + feed.getSourceId(), null);
				stmt.addBatch();
			}
			try {
				stmt.executeBatch();
			} catch (SQLException se) {
				Logger.error(CLASS_ID, "Error returned by writeToAggregatorTable...", se);
				throw se;
			}
			conn.commit();
			retVal = feeds.size();
		} catch (Exception e) {
			Logger.error(CLASS_ID, "An Exception happened while writing feeds to aggregator table : " + e.getMessage(), e);
		} finally {
			SQLHelper.closeStatement(stmt);
			if (conn != null) {
				try {
					Logger.debug(CLASS_ID, "Returning connection...", null);
					Controller.getConnectionPool().returnConnection(conn);
				} catch (Exception e) {
					Logger.warning(CLASS_ID, "Error while returning connection to the pool...", null);
				}
			}
		}
		return retVal;
	}

	/**
	 * This method is used to retrieve the source Ids for the passed in username
	 * 
	 * @param userName
	 * @return Comma seperated list of source IDs
	 */
	@Override
	public String getSourceDetailsForUser(String userName) {
		Connection connection = null;
		PreparedStatement statement = null;
		StringBuilder sourceIdsForUser = new StringBuilder("");
		try {
			// Executing the query to retrieve source details of user
			connection = Controller.getConnectionPool().getTransactionalConnection(CLASS_ID);
			statement = connection.prepareStatement("select B.SRC_PK from MS_RGA_SUBS_CHANNEL_CHN A,MS_RGA_CHANNEL_DTLS_SRC B where B.OBJECT_ID = A.CHANNEL_NAME AND A.CHN_CREATED_BY = ?");
			statement.setString(1, userName);
			Logger.info(CLASS_ID, "Executing the query to retrieve source details of user :-" + userName, null);
			ResultSet resultSet = statement.executeQuery();
			// Populating sourceNames
			while (resultSet.next()) {
				String sourceId = resultSet.getString(1);
				if (sourceId != null) {
					Logger.info(CLASS_ID, "Retrieved source '" + sourceId + "' for user :-" + userName, null);
					sourceIdsForUser.append(sourceId).append(",");
				}
			}
			connection.commit();
		} catch (SQLException se) {
			Logger.error(CLASS_ID, "Error returned when retrieving list of sources", se);
		} finally {
			SQLHelper.closeStatement(statement);
			if (connection != null) {
				try {
					Logger.debug(CLASS_ID, "Returning connection...", null);
					Controller.getConnectionPool().returnConnection(connection);
				} catch (Exception e) {
					Logger.warning(CLASS_ID, "Error while returning connection to the pool...", null);
				}
			}
		}
		// Converting to string and removing the comma at the end if present
		String sourceIds = sourceIdsForUser.toString();
		if (sourceIds.length() > 0) {
			sourceIds = sourceIds.substring(0, sourceIds.length() - 1);
		}
		return sourceIds;
	}

	/**
	 * Call pl sql infolet.
	 *
	 * @param callString
	 *            the call string
	 */
	@Override
	public void callPlSqlInfolet(String callString) {
		Connection con = null;
		CallableStatement stmt = null;
		Logger.debug(CLASS_ID, "Calling Oracle Proc : " + callString, null);
		try {
			con = Controller.getConnectionPool().getTransactionalConnection(CLASS_ID);
			stmt = con.prepareCall(callString);
			stmt.registerOutParameter(1, java.sql.Types.INTEGER);
			stmt.registerOutParameter(2, java.sql.Types.VARCHAR);
			stmt.execute();
			con.commit();
			int errCode = stmt.getInt(1);
			String errMesg = stmt.getString(2);
			Logger.debug(CLASS_ID, "Error returned by Ms_Rga_Process_Subscrptn_Resp.Pr_Populate_Subscrptn_Resp routine - Code : " + errCode + ": Err Msg = " + errMesg, null);
		} catch (Exception e) {
			Logger.debug(CLASS_ID, "Exception while processing " + callString + " : " + e.getMessage(), e);
		} finally {
			SQLHelper.closeStatement(stmt);
			if (con != null) {
				try {
					Logger.debug(CLASS_ID, "Returning connection...", null);
					Controller.getConnectionPool().returnConnection(con);
				} catch (Exception e) {
					/* nothing */
				}
			}
		}
	}

	/**
	 * Load channel feeds.
	 *
	 * @param srcIDs
	 *            the src i ds
	 * @return the list
	 */
	@Override
	public List<SourceInfo> loadChannelFeeds(String srcIDs) {
		// First - execute the SQL query to load up all the channels
		/*
		 * String query = "SELECT DISTINCT" + " DECODE(SOURCE_TYPE,1,'email',2,'rss',3,'amq') SOURCE_TYPE, SERVER_ADD_URL, SCR_USER_NAME, SCR_PASSWORD, " + " SERVER_PARAMETERS,STRUCT_CONT_HAND_INFOLET,SAVE_ATTACHMENTS,CHANNEL_ID,QUEUE_NAME,QUEUE_URL  FROM MS_RGA_CHANNEL_DETAILS WHERE " + " ACTIVE = '1' AND  SERVER_ADD_URL IS NOT NULL";
		 */
		String query = "SELECT DISTINCT" + " DECODE(a.SOURCE_TYPE,1,'email',2,'rss',3,'amq') SOURCE_TYPE, a.SERVER_ADD_URL, a.SCR_USER_NAME, b.channel_password_encr, " + " a.SERVER_PARAMETERS,a.STRUCT_CONT_HAND_INFOLET,a.SAVE_ATTACHMENTS,a.CHANNEL_ID,a.QUEUE_NAME,a.QUEUE_URL,b.SRC_PK  FROM MS_RGA_CHANNEL_DETAILS a,MS_RGA_CHANNEL_DTLS_SRC b WHERE " + " a.CHANNEL_ID=b.object_id AND a.source_id=b.SRC_PK AND ACTIVE = '1' AND  SERVER_ADD_URL IS NOT NULL";
		if (srcIDs != null) {
			query += srcIDsQueryTrail(srcIDs);
		}
//        loadFeedTimestampsHashMap(); // Load persisted timeStamps for each Feed
//        updateTStamps(getLatestFeedTStmps());
		List<SourceInfo> channelFeeds = queryResults(query);
		Logger.debug(CLASS_ID, "ChannelFeed Count = " + channelFeeds.size(), null);
		return channelFeeds;
	}

	/**
	 * loadAMQFileChannelFeeds gets list of AMQ File channels.
	 *
	 * @param srcIDs
	 *            the src i ds
	 * @return List<SourceInfo>
	 */
	@Override
	public List<SourceInfo> loadAMQFileChannelFeeds(String srcIDs) {
		// First - execute the SQL query to load up all the channels
		/*
		 * String query = "SELECT DISTINCT" + " DECODE(SOURCE_TYPE,1,'email',2,'rss',3,'amq') SOURCE_TYPE, SERVER_ADD_URL, SCR_USER_NAME, SCR_PASSWORD, " + " SERVER_PARAMETERS,STRUCT_CONT_HAND_INFOLET,SAVE_ATTACHMENTS,CHANNEL_ID,QUEUE_NAME,QUEUE_URL  FROM MS_RGA_CHANNEL_DETAILS WHERE " + " ACTIVE = '1' AND  SERVER_ADD_URL IS NOT NULL";
		 */
		String query = "SELECT DISTINCT" + " DECODE(a.SOURCE_TYPE,1,'email',2,'rss',3,'amq') SOURCE_TYPE, a.SERVER_ADD_URL, a.SCR_USER_NAME, a.SCR_PASSWORD, " + " a.SERVER_PARAMETERS,a.STRUCT_CONT_HAND_INFOLET,a.SAVE_ATTACHMENTS,a.CHANNEL_ID,a.QUEUE_NAME,a.QUEUE_URL,b.SRC_PK  FROM MS_RGA_CHANNEL_DETAILS a,MS_RGA_CHANNEL_DTLS_SRC b WHERE " + " a.CHANNEL_ID=b.object_id AND a.source_id=b.SRC_PK AND ACTIVE = '1' AND  SERVER_ADD_URL IS NOT NULL";
		if (srcIDs != null) {
			query += srcIDsQueryTrail(srcIDs);
		}
//        loadFeedTimestampsHashMap(); // Load persisted timeStamps for each Feed
//        updateTStamps(getLatestFeedTStmps());
		List<SourceInfo> channelFeeds = queryResults(query);
		Logger.debug(CLASS_ID, "ChannelFeed Count = " + channelFeeds.size(), null);
		return channelFeeds;
	}

	/**
	 * Format the comma seperated source id list as needed by the query.
	 *
	 * @param srcIDs
	 *            : A comma or ";" separated list of source IDs from channel details table
	 * @return the string
	 */
	@Override
	public String srcIDsQueryTrail(String srcIDs) {
		// Split the source url list in the format required for the query
		String[] srcList = srcIDs.split("[,;]+");
		StringBuffer srcListforQuery = new StringBuffer();
		srcListforQuery.append("(");
		for (String src : srcList) {
			if (srcListforQuery.length() != 1) // this means this is not the first token and there are more channels to be added to the list with a comma seperator
			{
				srcListforQuery.append(",");
			}
			srcListforQuery.append("'" + src + "'");
		}
		srcListforQuery.append(")");
		return " AND SOURCE_ID in " + srcListforQuery.toString();
	}

	/**
	 * Load feed timestamps hash map.
	 *
	 * @param updatedFeedTimestamp
	 *            the updated feed timestamp
	 */
	@Override
	public void loadFeedTimestampsHashMap(Map<String, String> updatedFeedTimestamp) {
		String lastFeedTimestampQry = "SELECT DISTINCT" + " SERVER_ADD_URL, LAST_RESPONSE_TIMESTAMP " + " FROM MS_RGA_URL_LATEST_TIMESTAMP";
		List<SourceLastUpdate> feedTimestamps = queryLastRespTime(lastFeedTimestampQry);
		if ((feedTimestamps != null) && (feedTimestamps.size() > 0)) {
			Logger.debug(CLASS_ID, "Preparing HashMap of Timestamps ...", null);
			// for (int i = 0; i < feedTimestamps.length; i++) {
			for (SourceLastUpdate slu : feedTimestamps) {
				updatedFeedTimestamp.put(slu.getServerURL(), slu.getLastResponseTime());
			}
			StringBuffer sb = new StringBuffer();
			// print the hashmap
			for (String key : updatedFeedTimestamp.keySet()) {
				sb.append("'" + key + "' ==>  " + updatedFeedTimestamp.get(key) + "\n");
			}
			Logger.info(CLASS_ID, "Persisted Timestamps \n " + sb.toString(), null);
		}
	}

	/**
	 * Execute a DB query and return the results as an array of String arrays.
	 *
	 * @param query
	 *            the SQL query string
	 * @return the query results
	 */
	public static List<SourceLastUpdate> queryLastRespTime(String query) {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		List<SourceLastUpdate> result = new ArrayList<SourceLastUpdate>();
		boolean flag = false;
		Logger.debug(CLASS_ID, "Channel selection query = ' " + query + "'", null);
		try {
			con = Controller.getConnectionPool().getTransactionalConnection(CLASS_ID);
			// Execute the SQL statement
			ps = con.prepareStatement(query);
			// Go through the result set
			rs = ps.executeQuery();
			SourceLastUpdate rowData;
			// Then go through the result rows
			while (rs.next()) {
				// Create the array to hold the results
				rowData = new SourceLastUpdate();
				rowData.setLastResponseTime(rs.getString(2));
				rowData.setServerURL(rs.getString(1));
				result.add(rowData);
				// result = DataIntegrationInfolet.addRow(result, rowData);
			}
		} catch (Exception e) {
			flag = false;
			Logger.error(CLASS_ID, "An Exception happened while writing into the file " + e.getMessage(), e);
		} finally {
			SQLHelper.closeResultSet(rs);
			SQLHelper.closeStatement(ps);
			if (con != null) {
				try {
					Logger.debug(CLASS_ID, "Returning connection...", null);
					Controller.getConnectionPool().returnConnection(con);
				} catch (Exception e) {
					/* nothing */
				}
			}
		}
		return result;
	}

	/**
	 * Execute a DB query and return the results as an array of String arrays.
	 *
	 * @param query
	 *            the SQL query string
	 * @return the query results
	 */
	public static List<SourceInfo> queryResults(String query) {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		int numRowsUpdated = 0;
		List<SourceInfo> result = new ArrayList<SourceInfo>();
		boolean flag = false;
		int colCount;
		Logger.debug(CLASS_ID, "Channel selection query = ' " + query + "'", null);
		try {
			con = Controller.getConnectionPool().getTransactionalConnection(CLASS_ID);
			// Execute the SQL statement
			ps = con.prepareStatement(query);
			// Go through the result set
			rs = ps.executeQuery();
			// Get the column count
			ResultSetMetaData mdata = rs.getMetaData();
			colCount = mdata.getColumnCount();
			Logger.debug(CLASS_ID, "Selected channel count = " + colCount, null);
			SourceInfo rowData;
			// Then go through the result rows
			while (rs.next()) {
				// Create the array to hold the results
				rowData = new SourceInfo();
				rowData.setSourceType(rs.getString(1));
				rowData.setServerURL(rs.getString(2));
				rowData.setUserName(rs.getString(3));
				if(null != rs.getString(4)){
					rowData.setPassword(EncryptionUtilities.decrypt(rs.getString(4)));
				}else{
					rowData.setPassword(rs.getString(4));
				}
				rowData.setParameters(rs.getString(5));
				rowData.setStructureInfolet(rs.getString(6));
				rowData.setSaveAttachments(rs.getString(7));
				rowData.setChannelId(rs.getString(8));
				rowData.setQueueName(rs.getString(9));
				rowData.setQueueURL(rs.getString(10));
				rowData.setSrcPk(rs.getString(11));
				rowData.setEmailType(rs.getString(12));
				// Copy the results
				/*
				 * for (int i = 0; i < colCount; i++) { rowData[i] = rs.getString(i + 1); Logger.debug(CLASS_ID, "*************************inside queryResults: " + rowData[i], null); }
				 */
				result.add(rowData);
				// result = DataIntegrationInfolet.addRow(result, rowData);
			}
		} catch (Exception e) {
			flag = false;
			Logger.error(CLASS_ID, "An Exception happened while writing into the file " + e.getMessage(), e);
		} finally {
			SQLHelper.closeResultSet(rs);
			SQLHelper.closeStatement(ps);
			if (con != null) {
				try {
					Logger.debug(CLASS_ID, "Returning connection...", null);
					Controller.getConnectionPool().returnConnection(con);
				} catch (Exception e) {
					/* nothing */
				}
			}
		}
		return result;
	}

	/**
	 * Update t stamps.
	 *
	 * @param latestFeedTStamps
	 *            the latest feed t stamps
	 */
	@Override
	public void updateTStamps(Map<String, String> latestFeedTStamps) {
		Iterator<Entry<String, String>> it = latestFeedTStamps.entrySet().iterator();
		Connection con = null;
		ResultSet rs = null;
		CallableStatement stmt = null;
		String query = "begin ? := Ms_Rga_Utilities.Fn_Update_Feed_Timestamps(?,?); end;";
		try {
			con = Controller.getConnectionPool().getTransactionalConnection(CLASS_ID);
			// Execute the SQL statement
			stmt = con.prepareCall(query);
			Logger.debug(CLASS_ID, " count of source list : " + latestFeedTStamps.size() + " = ", null);
			while (it.hasNext()) {
				Map.Entry<String, String> pairs = it.next();
				Logger.debug(CLASS_ID, " values that going to update : " + pairs.getKey() + " = " + pairs.getValue(), null);
				// Go through the result set
				stmt.registerOutParameter(1, Types.NUMERIC);
				stmt.setString(2, pairs.getKey());
				stmt.setString(3, pairs.getValue());
				stmt.execute();
				try {
					rs = stmt.getResultSet();
					while (rs.next()) {
						Logger.debug(CLASS_ID, "Updated " + rs.getInt(1) + "records against Server/Url " + pairs.getKey(), null);
					}
				} catch (Exception ex) {
					// This exception is not important since
				}
			}
		} catch (Exception e) {
			Logger.error(CLASS_ID, "An Exception happened while writing into the file" + e.getMessage(), e);
		} finally {
			SQLHelper.closeResultSet(rs);
			SQLHelper.closeStatement(stmt);
			if (con != null) {
				try {
					Logger.debug(CLASS_ID, "Returning connection...", null);
					Controller.getConnectionPool().returnConnection(con);
				} catch (Exception e) {
					/* nothing */
				}
			}
		}
	}

	/**
	 * Gets the metric id.
	 *
	 * @param metricName
	 *            the metric name
	 * @return the metric id
	 */
	@Override
	public long getMetricId(String metricName) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String query = "select metric_id from si_metrics_t where metric_name = ?";
		long metricId = -1L;
		try {
			con = getConnection();
			stmt = con.prepareStatement(query);
			stmt.setString(1, metricName);
			rs = stmt.executeQuery();
			if (rs.next()) {
				metricId = rs.getLong(1);
			}
			return metricId;
		} catch (SQLException e) {
			Logger.error("DBManager", "recieved SQL Exception for query:" + query, e);
			return -1L;
		} finally {
			closeResouces(stmt, rs, con);
		}
	}

	/**
	 * Adds the alert.
	 *
	 * @param alert
	 *            the alert
	 * @throws GrciException
	 *             the grci exception
	 */
	@Override
	public void addAlert(Alert alert) throws GrciException {
		Date start = new Date();
		Connection con = null;
		PreparedStatement pstmt = null;
		long metricId = getMetricId("MS RGA Channel Aggregator");
		Logger.error("DBManager", "metricId  " + metricId, null);
		if (metricId < 0L) {
			throw new GrciException("Please ensure an infolet by the exact name \"MS RGA Channel Aggregator\" (without quotes) is installed and is enabled");
		}
		String query = "insert into MS_RGA_AGGREGATOR (SERVER, SENDER, RECIPIENT, SUBJECT, BODY, ATTACHMENT, PROCESSED_FLAG, " + "FEED_DATA_ID, MSG_UID, FEED_SENT_DATE, STRUCTURED_CONTENT_ID, CHANNEL_ID, FEED_SEQUENCE,CREATION_DATE) " + "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
		Logger.error("DBManager", "query  " + query, null);
		try {
			con = getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, alert.getChannel().getServer());
			pstmt.setString(2, alert.getSender());
			pstmt.setString(3, alert.getRecipient());
			pstmt.setString(4, alert.getSubject());
			pstmt.setString(5, alert.getBody());
			pstmt.setString(6, alert.getAttachment());
			pstmt.setString(7, "");
			pstmt.setInt(8, 0);
			pstmt.setString(9, alert.getMsgId());
			pstmt.setDate(10, new java.sql.Date(alert.getFeedSentDate().getTime()));
			pstmt.setLong(11, -1);
			pstmt.setString(12, alert.getChannel().getId());
			pstmt.setInt(13, getSequenceNum(con));
			pstmt.setDate(14, new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
			pstmt.executeUpdate();
			con.commit();
			Logger.error("DBManager", "updates = " + pstmt.getUpdateCount(), null);
		} catch (Exception e) {
			Logger.error("DBManager", "recieved SQL Exception", e);
			throw new GrciException(e);
		} finally {
			closeResouces(pstmt, null, con);
		}
	}

	/**
	 * Creates the channel.
	 *
	 * @param channel
	 *            the channel
	 * @throws GrciException
	 *             the grci exception
	 */
	public void createChannel(Channel channel) throws GrciException {
		Logger.error("DBManager", "inside createChannel", null);
		Connection con = null;
		PreparedStatement pstmt = null;
		String channelId = getNewChannelId();
		String query = "insert into MS_RGA_CHANNEL_DTLS(HDN_CHNL_ID_CNT,CHANNEL_TYPE,CHANNEL_STATUS,CHANNEL_NAME,DD_STATUS_FLAG,CUSTOM_FIELD2,OBJECT_ID,SAVE_ATTACHMENTS,DD_OBJECT_TYPE,DD_PROCESS_INSTANCE_ID,DD_INSTANCE_ID,DD_CREATED_ON,DD_CREATED_BY,DD_MODIFIED_ON,DD_MODIFIED_BY,DD_PROCESS_CODE) values('0','1','1',?,'Y','2',?,'yes','MS_RGA_CHANNEL_DTLS',?,? ,SYSDATE,'SYSTEMI',SYSDATE,'SYSTEMI','MS_RGA_CHANNEL_DTLS')";
		Logger.error("DBManager", "query  " + query, null);
		try {
			con = getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, channel.getName());
			pstmt.setString(2, channelId);
			pstmt.setLong(3, getProcessInstanceId());
			pstmt.setLong(4, getInstanceId());
			pstmt.executeUpdate();
			con.commit();
			Logger.error("DBManager", "updates = " + pstmt.getUpdateCount(), null);
			channel.setId(channelId);
		} catch (Exception e) {
			Logger.error("DBManager", "recieved SQL Exception", e);
			throw new GrciException(e);
		} finally {
			closeResouces(pstmt, null, con);
		}
	}

	/**
	 * Gets the process instance id.
	 *
	 * @return the process instance id
	 */
	@Override
	public long getProcessInstanceId() {
		return getnextSequence("SI_PROCESS_INSTANCE_ID_S");
	}

	/**
	 * Adds the source.
	 *
	 * @param objectId
	 *            the object id
	 * @param server
	 *            the server
	 * @throws GrciException
	 *             the grci exception
	 */
	public void addSource(String objectId, String server) throws GrciException {
		Date start = new Date();
		Connection con = null;
		PreparedStatement pstmt = null;
		String sourceId = getNewSourceId();
		String query = "insert into MS_RGA_CHANNEL_DTLS_SRC(SERVER_ADDRESS,SOURCE_TYPE,SRC_PK,CUSTOM_FIELD6,CUSTOM_FIELD9,SRC_CREATED_BY,OBJECT_ID,DD_STATUS_FLAG) values \n(?,'1',?,'N',1,'100000',?,'Y')";
		Logger.error("DBManager", "query  " + query, null);
		try {
			con = getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, server);
			pstmt.setString(2, sourceId);
			pstmt.setString(3, objectId);
			pstmt.executeUpdate();
			con.commit();
			Logger.error("DBManager", "updates = " + pstmt.getUpdateCount(), null);
		} catch (Exception e) {
			Logger.error("DBManager", "recieved SQL Exception", e);
			throw new GrciException(e);
		} finally {
			closeResouces(pstmt, null, con);
		}
	}

	/**
	 * Gets the new source id.
	 *
	 * @return the new source id
	 * @throws GrciException
	 *             the grci exception
	 */
	@Override
	public String getNewSourceId() throws GrciException {
		long id = getnextSequence("MS_RGA_AUTO_SOURCE_SEQ");
		if (id < 0L) {
			throw new GrciException("Sequence MS_RGA_AUTO_SOURCE_SEQ returned " + id);
		}
		return "SRC-" + id;
	}

	/**
	 * Gets the new channel id.
	 *
	 * @return the new channel id
	 * @throws GrciException
	 *             the grci exception
	 */
	@Override
	public String getNewChannelId() throws GrciException {
		long id = getnextSequence("MS_RGA_AUTO_CHANNEL_SEQ");
		if (id < 0L) {
			throw new GrciException("Sequence MS_RGA_AUTO_CHANNEL_SEQ returned " + id);
		}
		return "CHN-" + id;
	}

	/**
	 * Construct channel details.
	 *
	 * @param channel
	 *            the channel
	 * @throws GrciException
	 *             the grci exception
	 */
	@Override
	public void constructChannelDetails(Channel channel) throws GrciException {
		Logger.error("DBManager", "inside constructChannelDetails", null);
		validateChannel(channel);
		String name = channel.getName();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String query = "select mstr.CHANNEL_ID as OBJECT_ID,src.server_address as SERVER from ms_rga_channel_master mstr, ms_rga_channel_dtls_src src where mstr.channel_type = 1 and mstr.active=1 and upper(mstr.channel_name) = ? and mstr.channel_id = src.object_id";
		Logger.error("DBManager", "query = " + channel.getName(), null);
		try {
			con = getConnection();
			stmt = con.prepareStatement(query);
			stmt.setString(1, name.toUpperCase());
			rs = stmt.executeQuery();
			channel.setId("UNKNOWN");
			while (rs.next()) {
				channel.setId(rs.getString("OBJECT_ID"));
				channel.setServer(rs.getString("SERVER"));
			}
			Logger.error("DBManager", "channel  " + channel, null);
		} catch (SQLException e) {
			Logger.error("DBManager", "recieved SQL Exception for query:" + query, e);
			throw new GrciException(e);
		} finally {
			closeResouces(stmt, rs, con);
		}
	}

	/**
	 * Validate channel.
	 *
	 * @param channel
	 *            the channel
	 * @throws IllegalChannelStateException
	 *             the illegal channel state exception
	 */
	@Override
	public void validateChannel(Channel channel) throws IllegalChannelStateException {
		Logger.error("DBManager", "inside validateChannel", null);
		if (channel == null) {
			throw new IllegalChannelStateException("Channel cannot be null");
		}
		if ((channel.getName() == null) || (channel.getName().isEmpty())) {
			throw new IllegalChannelStateException("Channel name cannot be null or empty");
		}
		String server = channel.getServer();
	}

	/**
	 * Gets the next sequence.
	 *
	 * @param sequenceName
	 *            the sequence name
	 * @return the next sequence
	 */
	@Override
	public long getnextSequence(String sequenceName) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "select " + sequenceName + ".nextval as value from dual";
		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			long value = -1L;
			while (rs.next()) {
				value = rs.getLong("value");
			}
			Logger.error("DBManager", sequenceName + ".nextval returning:" + value, null);
			return value;
		} catch (SQLException e) {
			Logger.error("DBManager", "recieved SQL Exception for query:" + query, e);
			return -1L;
		} finally {
			closeResouces(stmt, rs, con);
		}
	}

	/**
	 * Gets the all server for channel.
	 *
	 * @param channelId
	 *            the channel id
	 * @return the all server for channel
	 */
	@Override
	public List<Source> getAllServerForChannel(String channelId) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String query = "select SERVER_ADDRESS,SRC_PK from MS_RGA_CHANNEL_DTLS_SRC where OBJECT_ID = ?";
		List<Source> sourceList = new ArrayList();
		try {
			con = getConnection();
			stmt = con.prepareStatement(query);
			stmt.setString(1, channelId);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Source src = new Source();
				src.setChannelId(channelId);
				src.setServer(rs.getString("SERVER_ADDRESS"));
				src.setSrcId(rs.getString("SRC_PK"));
				sourceList.add(src);
			}
			Logger.error("DBManager", "number of sources for channel =" + sourceList.size(), null);
		} catch (SQLException e) {
			Logger.error("DBManager", "recieved SQL Exception for query:" + query, e);
		} finally {
			closeResouces(stmt, rs, con);
		}
		return sourceList;
	}

	/**
	 * This method reads the content details from the client oracle db.
	 *
	 * @param pid
	 *            Process Id
	 * @return Object representation
	 */
	@Override
	public GRCFObjectDetails getContentBrowserDetails(String pid) {
		Logger.debug(CLASS_ID, "inside getContentBrowserDetails", null);
		String query = "SELECT CONTENT_PROVIDER,CONTENT_CATEGORY,CONTENT_VERSION,CONTENT_JSON," + "AOC_OWNER_ORG ,AOC_OWNER,AOC_RESTRICT_ACCESS_TO,AOC_COMMENTS," + "REQ_OWNER_ORG,REQ_OWNER,REQ_RESTRICT_ACCESS_TO,REQ_COMMENTS," + "PROCESS_INSTANCE_ID,instance_id,dd_current_user_name,dd_event_user_name,created_by,creation_date" + " FROM MS_RGA_CONTENT_BROWSER_OPER_V" + " WHERE PROCESS_INSTANCE_ID=" + pid;
		Logger.debug(CLASS_ID, "Query:" + query, null);
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		GRCFObjectDetails grcfObj = null;
		try {
			grcfObj = new GRCFObjectDetails();
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				String strContentProvider = rs.getString("CONTENT_PROVIDER");
				String strContentCategory = rs.getString("CONTENT_CATEGORY");
				String strContentVersion = rs.getString("CONTENT_VERSION");
				Clob clbContentJson = rs.getClob("CONTENT_JSON");
				long len = clbContentJson.length();
				String strContentJson = clbContentJson.getSubString(1, (int) len);
				Logger.debug(CLASS_ID, "JSON String:" + strContentJson, null);
				List<ContentWrapperObj> wrapperList = (List<ContentWrapperObj>) new Gson().fromJson(strContentJson, new TypeToken<List<ContentWrapperObj>>() {
				}.getType());
				grcfObj.setSelectedObj(wrapperList);
				String strAocOwnerOrg = rs.getString("AOC_OWNER_ORG");
				String strAocOwner = rs.getString("AOC_OWNER");
				String strAocRestrictAccess = rs.getString("AOC_RESTRICT_ACCESS_TO");
				String strAocComments = rs.getString("AOC_COMMENTS");
				String strReqOwnerOrg = rs.getString("REQ_OWNER_ORG");
				String strReqOwner = rs.getString("REQ_OWNER");
				String strReqRestrictAccess = rs.getString("REQ_RESTRICT_ACCESS_TO");
				String strReqComments = rs.getString("REQ_COMMENTS");
				String strProcessInstanceId = rs.getString("PROCESS_INSTANCE_ID");
				String strInstanceId = rs.getString("instance_id");
				String strCurrentUserName = rs.getString("dd_current_user_name");
				String strEventUserName = rs.getString("dd_event_user_name");
				String strCreatedBy = rs.getString("created_by");
				String strCreationDate = rs.getString("creation_date");
				// frequency and isApprovalRequired is not there in oracle db
				GRCFMandatoryFields aocDetails = new GRCFMandatoryFields();
				aocDetails.setOwnerName(Arrays.asList(strAocOwner.split("\\s*,\\s*")));
				aocDetails.setOwnerOrgName(Arrays.asList(strAocOwnerOrg.split("\\s*,\\s*")));
				aocDetails.setRestricted(strAocRestrictAccess);
				GRCFMandatoryFields reqDetails = new GRCFMandatoryFields();
				reqDetails.setOwnerName(Arrays.asList(strReqOwner.split("\\s*,\\s*")));
				reqDetails.setOwnerOrgName(Arrays.asList(strReqOwnerOrg.split("\\s*,\\s*")));
				reqDetails.setRestricted(strReqRestrictAccess);
				grcfObj.setContentProviderName(strContentProvider);
				grcfObj.setContentCategoryName(strContentCategory);
				grcfObj.setContentVersion(strContentVersion);
				grcfObj.setReqDetails(reqDetails);
				grcfObj.setAocDetails(aocDetails);
			}
		} catch (SQLException e) {
			Logger.error("DBManager", "recieved SQL Exception for query:" + query, e);
		} finally {
			closeResouces(stmt, rs, con);
		}
		return grcfObj;
	}

	@Override
	public void createChannel(MsRgaChannelDtl c) throws GrciException {
		Channel ch=new Channel();
		createChannel(ch);
	}

	@Override
	public void addSource(String objectId, MsRgaChannelDtlsSrc src)
			throws GrciException {
		addSource(objectId, src.getServerAddress());
		
	}
}
