package com.metricstream.systemi.rga.integration.pdms;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import com.metricstream.log.Logger;
import com.metricstream.systemi.server.common.Controller;


// TODO: Auto-generated Javadoc
/**
 * Class to  DAO operation on PDMS
 */
public class PDMSDao {

	/** The Constant CLASS_ID. */
	private static final String CLASS_ID = "PDMSDao";
	
	/** The dao. */
	private static PDMSDao dao = null;
	
	/** The Constant EMPTY_QUOTED_STRING. */
	private static final String EMPTY_QUOTED_STRING = "\"\"";

	/**
	 * Instantiates a new PDMS dao.
	 */
	private PDMSDao() {

	}

	/**
	 * Gets the single instance of PDMSDao.
	 *
	 * @return single instance of PDMSDao
	 */
	public static PDMSDao getInstance() {
		if (dao == null) {
			dao = new PDMSDao();
		}
		return dao;
	}

	/**
	 * Method to Adds the document.
	 *
	 * @param document the document
	 * @return the long
	 * @throws SQLException the SQL exception
	 */
	public long addDocument(PDMSDocument document) throws SQLException {
		Connection con = null;
		try {

			long batchNumber = getNextBatchNumber();
			Logger.error(CLASS_ID, "batchnumber=" + batchNumber, null);
			document.setBatchNumber(batchNumber);
			seedDocument(document);
			validateDocument(document.getUserName(), batchNumber);
			// processSeedDocument(document.getUserName(), batchNumber);
			Logger.error(CLASS_ID,
					"returning true for " + document.getDocumentName(), null);
			return batchNumber;
		} finally {
			closeResouces(null, null, con);
		}
	}

	/**
	 * Method to process seed document.
	 *
	 * @param userName the user name
	 * @param batchNumber the batch number
	 */
	private void processSeedDocument(String userName, long batchNumber) {
		// TODO Auto-generated method stub
		callProcudure(userName, batchNumber, "process_seed_documents_rf");
	}

	/**
	 * Method to validate document.
	 *
	 * @param userName the user name
	 * @param batchNumber the batch number
	 */
	private void validateDocument(String userName, long batchNumber) {
		callProcudure(userName, batchNumber, "validate_seed_documents_rf");

	}

	/**
	 * Method to call procedure.
	 *
	 * @param userName the user name
	 * @param batchNumber the batch number
	 * @param procedureName the procedure name
	 */
	private void callProcudure(String userName, long batchNumber,
			String procedureName) {

		CallableStatement cs = null;

		String query = "{ call " + procedureName + " (?,?,?,?)}";
		Connection con = null;
		try {
			con = getConnection();
			cs = con.prepareCall(query);
			cs.setLong(1, 0L);
			cs.setString(2, EMPTY_QUOTED_STRING);
			cs.registerOutParameter(1, Types.INTEGER);
			cs.registerOutParameter(2, Types.VARCHAR);
			cs.setString(3, batchNumber + "");
			cs.setString(4, userName);
			cs.execute();
			long errCode = cs.getLong(1);
			String errMsg = cs.getString(2);
			Logger.error(CLASS_ID, "for procedure " + procedureName
					+ " : errCode:" + errCode + "errMsg:" + errMsg, null);
			con.commit();
		} catch (SQLException e) {
			Logger.error(CLASS_ID, "SQLException", e);
		} finally {
			closeResouces(cs, null, con);
		}

	}

	/**
	 * Method to get the next batch number.
	 *
	 * @return the next batch number
	 * @throws SQLException the SQL exception
	 */
	private long getNextBatchNumber() throws SQLException {
		PreparedStatement statement = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			statement = con
					.prepareStatement("select MS_DMS_BATCH_NUMBER_S.nextval from dual");
			rs = statement.executeQuery();
			rs.next();
			return rs.getLong(1);
		} finally {
			closeResouces(statement, rs, con);
		}
	}

	/**
	 * Method to create Seed document.
	 *
	 * @param document the document
	 * @throws SQLException the SQL exception
	 */
	private void seedDocument(PDMSDocument document) throws SQLException {

		PreparedStatement pstmt = null;
		String query = "Insert into MS_DMS_SEEDED_DOCUMENTS_RF(DOC_ENTERPRISE_NAME,DOCUMENT_NAME,LIFECYCLE_NAME,CATEGORY,SUB_CATEGORY1,SUB_CATEGORY2,SUB_CATEGORY3,"
				+ "SUB_CATEGORY4,PURPOSE,DESCRIPTION,REVIEW_SCHEDULE_DAYS,SCHEDULE_PERIOD_TYPE,KEYWORDS,USER_NAME,FILE_NAME,FILE_PATH,SEEDED_FLAG,"
				+ "PROCESS_TYPE,AUTHOR,OWNER,NO_OF_UPVERSIONS,OBSOLUTE_SEQ,BATCH_NUMBER,RECORD_NUMBER) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, document.getEnterpriseName());
			pstmt.setString(2, document.getDocumentName());
			pstmt.setString(3, document.getLifecycleName());
			pstmt.setString(4, document.getCategory());
			pstmt.setString(5, document.getSubCategory1());
			pstmt.setString(6, document.getSubCategory2());
			pstmt.setString(7, document.getSubCategory3());
			pstmt.setString(8, document.getSubCategory4());
			pstmt.setString(9, document.getPurpose());
			pstmt.setString(10, document.getDescription());
			pstmt.setInt(11, document.getReviewScheduleDays());
			pstmt.setString(12, document.getReviewScheduleType());
			pstmt.setString(13, document.getKeywords());
			// Ensure date is of DD-Mon-YYYY format?
			// pstmt.setDate(14, new
			// Date(document.getEffectiveDate().getTime()));
			pstmt.setString(14, document.getUserName());
			pstmt.setString(15, document.getAttachmentFileName());
			pstmt.setString(16, document.getAttachmentFilePath());
			pstmt.setString(17, document.getSeededFlag());
			// pstmt.setString(17, "V");
			pstmt.setString(18, document.getProcessType());
			pstmt.setString(19, document.getAuthor());
			pstmt.setString(20, document.getOwner());
			pstmt.setInt(21, document.getNumberOfUpversions());
			pstmt.setString(22, document.getObseleteSeq());
			pstmt.setLong(23, document.getBatchNumber());
			pstmt.setInt(24, 1);
			int retval = pstmt.executeUpdate();
			Logger.error(CLASS_ID, "retval after inserting = " + retval, null);
			con.commit();
		} finally {
			closeResouces(pstmt, null, con);
		}

	}

	/**
	 * Method to close resouces.
	 *
	 * @param stmt the stmt
	 * @param rs the rs
	 * @param con the con
	 */
	private void closeResouces(Statement stmt, ResultSet rs, Connection con) {
		closeResultSet(rs);
		closeStatement(stmt);
		returnConnection(con);
	}

	/**
	 * Method to close statement.
	 *
	 * @param stmt the stmt
	 */
	private void closeStatement(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				Logger.error(CLASS_ID, "SQLException", e);
			}
		}

	}

	/**
	 * Method to close result set.
	 *
	 * @param rs the rs
	 */
	private void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				Logger.error(CLASS_ID, "SQLException", e);
			}
		}

	}

	/**
	 * Method to return connection.
	 *
	 * @param con the con
	 */
	private void returnConnection(Connection con) {
		if (con != null) {
			Logger.error(CLASS_ID, "Returning connection...", null);
			Controller.getConnectionPool().returnConnection(con);
		}
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

}
