package com.metricstream.systemi.rga.sc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.NavigableSet;
import java.util.TreeSet;

import com.metricstream.log.Logger;
import com.metricstream.systemi.server.common.Controller;
import com.metricstream.systemi.sql.SQLHelper;

// TODO: Auto-generated Javadoc
/*
 * NOT FOR PRODUCTION
 */
/**
 * Class to represent DAO operation for structured contents
 */
public class Dao {

	/** The Constant CLASS_ID. */
	private static final String CLASS_ID = "DBManager";
	
	/** The db mgr. */
	private static Dao dbMgr = null;
	
	/** The id list. */
	private static NavigableSet<String> idList = null;

	/**
	 * Instantiates a new dao.
	 */
	private Dao() {
	}

	/**
	 * Gets the single instance of Dao.
	 *
	 * @return single instance of Dao
	 */
	public static Dao getInstance() {
		if (dbMgr == null) {
			dbMgr = new Dao();
		}
		return dbMgr;
	}

	/**
	 * Method to get the connection.
	 *
	 * @return the connection
	 * @throws SQLException the SQL exception
	 */
	private Connection getConnection() throws SQLException {
		return Controller.getConnectionPool().getTransactionalConnection(
				CLASS_ID);
	}

	/**
	 * Method to Close the resouces.
	 *
	 * @param stmt the stmt
	 * @param con the con
	 * @param rs the rs
	 */
	private static void closeResouces(Statement stmt, Connection con,
			ResultSet rs) {
		SQLHelper.closeStatement(stmt);
		SQLHelper.closeResultSet(rs);
		Controller.getConnectionPool().returnConnection(con);
	}

	/**
	 * Method to cache the Cache ids.
	 *
	 * @throws SQLException the SQL exception
	 */
	private static void cacheIds() throws SQLException {
		Connection con = null;
		String query = "Select task_id from sc_infolet_check_2";
		Statement stmt = null;
		ResultSet rs = null;
		idList = new TreeSet<String>();
		try {
			con = Controller.getConnectionPool().getTransactionalConnection(
					CLASS_ID);
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				idList.add(rs.getString(1));
			}
		} finally {
			closeResouces(stmt, con, rs);
		}
	}

	/**
	 * Method to Update values of email/attachments
	 *
	 * @param server the server
	 * @param sender the sender
	 * @param recipient the recipient
	 * @param subject the subject
	 * @param body the body
	 * @param attachmentid the attachmentid
	 * @param saveAttachments the save attachments
	 * @param infoletId the infolet id
	 * @param instanceId the instance id
	 * @return the string
	 */
	public String updateValues(String server, String sender, String recipient,
			String subject, String body, String attachmentid,
			String saveAttachments, String infoletId, String instanceId) {
		try {
			cacheIds();
		} catch (SQLException e) {
			Logger.error(CLASS_ID, "Exception! ", e);
		}
		Connection con = null;
		String query = "update SC_INFOLET_CHECK_2  set server=?,sender=?,recipient=?,subject=?,ATTACHMENTID =?,SAVEATTACHMENTS =?,infoletid=?,instanceid=? where task_id =?";
		PreparedStatement pstmt = null;

		try {
			con = getConnection();
			pstmt = con.prepareStatement(query);
			for (String id : idList.descendingSet()) {
				if (subject.contains(id)) {
					pstmt.setString(1, server);
					pstmt.setString(2, sender);
					pstmt.setString(3, recipient);
					pstmt.setString(4, subject);
					pstmt.setString(5, attachmentid);
					pstmt.setString(6, saveAttachments);
					pstmt.setString(7, infoletId);
					pstmt.setString(8, instanceId);
					pstmt.setString(9, id);
					pstmt.executeUpdate();
					break;
				}
			}
			return "SUCCESS";
		} catch (Exception e) {
			Logger.error(CLASS_ID, "Exception! for " + query, e);
			return "FAILURE";
		} finally {
			closeResouces(pstmt, con, null);
		}
	}

	/**
	 * Method to Insert values for email/attachments
	 *
	 * @param server the server
	 * @param sender the sender
	 * @param recipient the recipient
	 * @param subject the subject
	 * @param body the body
	 * @param attachmentid the attachmentid
	 * @param saveAttachments the save attachments
	 * @param infoletId the infolet id
	 * @param instanceId the instance id
	 * @return the string
	 */
	public String insertValues(String server, String sender, String recipient,
			String subject, String body, String attachmentid,
			String saveAttachments, String infoletId, String instanceId) {
		Connection con = null;
		String query = "insert into SC_INFOLET_CHECK values (?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = null;

		try {
			con = getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, server);
			pstmt.setString(2, sender);
			pstmt.setString(3, recipient);
			pstmt.setString(4, subject);
			pstmt.setString(5, attachmentid);
			pstmt.setString(6, saveAttachments);
			pstmt.setString(7, infoletId);
			pstmt.setString(8, instanceId);

			pstmt.executeUpdate();
			return "SUCCESS";
		} catch (Exception e) {
			Logger.error(CLASS_ID, "Exception! for " + query, e);
			return "FAILURE";
		} finally {
			closeResouces(pstmt, con, null);
		}
	}
}
