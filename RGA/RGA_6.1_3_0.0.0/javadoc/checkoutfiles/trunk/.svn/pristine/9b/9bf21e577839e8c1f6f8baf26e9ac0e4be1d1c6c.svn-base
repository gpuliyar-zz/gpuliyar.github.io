package com.metricstream.systemi.jpa.util;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.metricstream.log.Logger;
import com.metricstream.systemi.ext.infolet.ChannelFeedData;
import com.metricstream.systemi.ext.infolet.FeedXtraxnStats;
import com.metricstream.systemi.jpa.entity.MsQsParameterType;
import com.metricstream.systemi.jpa.entity.MsRgaAggregator;
import com.metricstream.systemi.jpa.entity.MsRgaChannelDtl;
import com.metricstream.systemi.jpa.entity.MsRgaChannelDtlsSrc;
import com.metricstream.systemi.jpa.entity.MsRgaContentBrowserOperV;
import com.metricstream.systemi.jpa.entity.MsRgaUrlLatestTimestamp;
import com.metricstream.systemi.jpa.entity.MsRgaUsers;
import com.metricstream.systemi.jpa.entity.SiMetricsT;
import com.metricstream.systemi.rga.dao.IDBHelper;
import com.metricstream.systemi.rga.domain.Alert;
import com.metricstream.systemi.rga.domain.Channel;
import com.metricstream.systemi.rga.domain.Source;
import com.metricstream.systemi.rga.domain.SourceInfo;
import com.metricstream.systemi.rga.exception.GrciException;
import com.metricstream.systemi.rga.exception.IllegalChannelStateException;
import com.metricstream.systemi.rga.grcobject.GRCFMandatoryFields;
import com.metricstream.systemi.rga.grcobject.GRCFObjectDetails;
import com.metricstream.systemi.server.common.Controller;
import com.metricstream.systemi.sql.SQLHelper;
import com.metricstream.systemi.utils.Utils;
import com.msi.grcintelligence.utility.encryption.EncryptionUtilities;
import com.msi.grcintelligence.utility.mongodb.ContentWrapperObj;



/**
 * The implementation class for JPA .
 *
 * @author asif.u
 */
public class JPAManager implements IDBHelper {
	/** The Constant CLASS_ID. */
	
	
	private static final String CLASS_ID = "JPAManager";
	
	/**
	 * Instantiates a new JPA manager.
	 */
	private JPAManager(){}
	
	
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
	 * Gets the user id.
	 *
	 * @return the user id
	 * @throws SQLException the SQL exception
	 */
	@Override
	public int getUserId() throws SQLException {
		Logger.debug(CLASS_ID, "inside getUserId", null);
		  EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		  int userId = -1;
		try {
			 Query q = em.createQuery("select u from Users u where u.userName =?");
			 q.setParameter(1, "SYSTEMI");
			    @SuppressWarnings("unchecked")
				List<MsRgaUsers> todoList = q.getResultList();
			    if(todoList!=null && todoList.size()>0)
			    	userId= todoList.get(0).getUserId().intValue();
			
			return userId;

		} finally {
			PersistenceManager.INSTANCE.closeEM(em);
		}
	}
	
/*	*//**
 * Gets the instance id.
 *
 * @param query the query
 * @param val1 the val1
 * @param val2 the val2
 * @param val3 the val3
 * @param val4 the val4
 * @return the instance id
 *//*
	public long getInstanceId() throws SQLException {
		Connection con = null;
		try {
			con = getConnection();
			return SQLHelper.getInstanceId(con, "SI_METRIC_RESULTS_S.NEXTVAL");
		} finally {
			returnConnection(con);
		}
	}*/
	

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
	 * Gets the all properties for RGA module.
	 *
	 * @return the all properties
	 */
	@Override
	public Properties getAllProperties() {
		//	 Query q = em.createQuery("select u from Users u where u.userName =?");
		String firstQuery="select u from MsQsParameterType u where u.parameterType = ?";// 'MS_RGA_CONFIG'";
		
		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		BigDecimal parameterId=null;
		 Query q = em.createQuery(firstQuery);
		 q.setParameter(1, "MS_RGA_CONFIG");
		    @SuppressWarnings("unchecked")
			List<MsQsParameterType> todoList = q.getResultList();
		    if(todoList!=null && todoList.size()>0)
		    	parameterId= todoList.get(0).getParameterTypeId();
		
		    String query = "SELECT  n.parameterName, v.parameterValue,n.parameterTypeId from  MsQsParameterName n, MsQsParameterValue v where n.parameterId = v.parameterId and  n.parameterTypeId = ?";
		try {
			Properties prop = new Properties();
			 Query qq = em.createQuery(query);
			    qq.setParameter(1, parameterId);
				@SuppressWarnings("rawtypes")
				List res= qq.getResultList();
			    for (Object object : res) {
			    	Object[] l=(Object[])object;
			    	Logger.debug(CLASS_ID, "getAllProperties--rs.getString(1): " + getValue(l[0]), null);
					Logger.debug(CLASS_ID, "getAllProperties--rs.getString(2): " + getValue(l[1]), null);
					prop.put(getValue(l[0]), getValue(l[1]));	
					}
				
	
			return prop;
		} catch (Exception e) {
			Logger.error(CLASS_ID, "recieved SQL Exception for query:" + query, e);
			return null;
		} finally {
			PersistenceManager.INSTANCE.closeEM(em);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.metricstream.systemi.jpa.util.IDBHelper#getActivationCode(java.lang.String)
	 */
	@Override
	public List<String> getActivationCode(String channelId) {
		List<String> activationCode = new ArrayList<String>();
		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		try {
			String query = "select m from MsRgaChannelDtlsSrc m where m.objectId= ?";
			// {? = CALL balance(?)}
			Logger.debug(CLASS_ID, " aaaaaaaaaaaa  " + query, null);
			Query q=em.createQuery(query);
			q.setParameter(1, channelId);
			List<MsRgaChannelDtlsSrc> src = q.getResultList();
			for (MsRgaChannelDtlsSrc msRgaChannelDtlsSrc : src) {
				activationCode.add(msRgaChannelDtlsSrc.getActivationCode());
			}
			Logger.debug(CLASS_ID, " executed ", null);
			
			Logger.debug(CLASS_ID, " executed ", null);
		} catch (Exception ex) {
			Logger.debug(CLASS_ID, "error while getting channel id from  ms_rga_channel_dtls_src table for activation code " + activationCode, ex);
		} finally {
			PersistenceManager.INSTANCE.closeEM(em);
		}
		return activationCode;
	}
	
	/**
	 * Writting all the feeds to aggregation table.
	 *
	 * @param feeds the feeds
	 * @return the total number of feeds
	 */
	@Override
	public int writeToAggregatorTable(ArrayList<ChannelFeedData> feeds) {
		int retVal = -1;
		Connection con = null;
		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			con = Controller.getConnectionPool().getTransactionalConnection(CLASS_ID);
			int i=0;
			Logger.debug(CLASS_ID + " : writeToAggregatorTable", "Writing " + feeds.size() + " to MS_RGA_AGGREGATOR table ", null);
			for (ChannelFeedData feed : feeds) {
				i+=1;
				 MsRgaAggregator agg=new MsRgaAggregator();
				 agg.setAttachment(feed.getAttachmentIDs());
				 agg.setBody(feed.getBody());
				 agg.setChannelId(feed.getChannelId());
				 agg.setCreationDate(new java.sql.Date(feed.getSentDate().getTime()));
				 agg.setFeedDatdId(0);
				 agg.setFeedSentDate(new java.sql.Date(feed.getSentDate().getTime()));
				 agg.setFeedSequence(getSequenceNum(con));
				 agg.setMessageUID(feed.getMsgUID());
				 agg.setProcessedFlag("");
				 agg.setRecipient(feed.getRecipient());
				 agg.setSender(feed.getSender());
				 agg.setServer(feed.getServer());
				 agg.setSrkPk(feed.getSourceId());
				 agg.setStrutConentId(Utils.getIntValue(feed.getStructuredContentInfoletId()));
				 agg.setSubject(feed.getSubject());
				 Logger.debug(CLASS_ID + " : writeToAggregatorTable", "Writing " + i + " to MS_RGA_AGGREGATOR table "+agg, null);
				 em.persist(agg);
				 em.flush();
			}
			
			
			tx.commit();
			Logger.debug(CLASS_ID + " : writeToAggregatorTable", "Writing " + i + " inserted the record for "+i, null);
		} catch (Exception e) {
			tx.rollback();
			Logger.error(CLASS_ID, "An Exception happened while writing feeds to aggregator table : " + e.getMessage(), e);
		} finally {
			if(con!=null){
				try{
					con.close();
				}catch(Exception ex){}
			}
			
			PersistenceManager.INSTANCE.closeEM(em);
		}
		return retVal;
	}
	
	
	/**
	 * This method is used to retrieve the source Ids for the passed in username.
	 *
	 * @param userName the user name
	 * @return Comma seperated list of source IDs
	 */
	@Override
	public String getSourceDetailsForUser(String userName) {
		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		StringBuilder sourceIdsForUser = new StringBuilder("");
		String sourceIds =null;
		try {
			// Executing the query to retrieve source details of user
			String query="select B.srcPk from MsRgaSubsChannelChn A,MsRgaChannelDtlsSrc B where B.objectId = A.channelName AND A.chnCreatedBy = ?";
			Query q=em.createQuery(query);
			q.setParameter(1, userName);
			Logger.info(CLASS_ID, "Executing the query to retrieve source details of user :-" + userName, null);
			
			Logger.debug(CLASS_ID, " executed ", null);
			
			List res= q.getResultList();
		    for (Object object : res) {
		    	Logger.debug(CLASS_ID, "getAllProperties--rs.getString(1): " + getValue(object), null);
		    	sourceIdsForUser.append(getValue(object)).append(",");
				}
		 // Converting to string and removing the comma at the end if present
		     sourceIds = sourceIdsForUser.toString();
			if (sourceIds.length() > 0) {
				sourceIds = sourceIds.substring(0, sourceIds.length() - 1);
			}
			
		} catch (Exception se) {
			Logger.error(CLASS_ID, "Error returned when retrieving list of sources", se);
		} finally {
			if (em != null) {
				try {
					Logger.debug(CLASS_ID, "Returning connection...", null);
					PersistenceManager.INSTANCE.closeEM(em);
				} catch (Exception e) {
					Logger.error(CLASS_ID, "Error while returning connection to the pool...", null);
				}
			}
		}
		
		
		return sourceIds;
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
		return " AND a.sourceId in " + srcListforQuery.toString();
	}
	
	/**
	 * Gets the source type for the given source id.
	 *
	 * @param sourceId the source id
	 * @return the source type
	 */
	public String getSourceType(String sourceId){
		if("1".equalsIgnoreCase(sourceId))
			return JPAConstants.EMAIL;
		else if("2".equalsIgnoreCase(sourceId))
			return JPAConstants.RSS;
		else
			return JPAConstants.AMQ;
		
	}
	
	/**
	 * Gets the Email server type for given value.
	 *
	 * @param type the type
	 * @return the email server type
	 */
	public String getEmailServerType(String type){
		if("1".equalsIgnoreCase(type))
			return JPAConstants.IMAP;
		else 
			return JPAConstants.POP3;
	}
	
	/**
	 * Load channel details which need to aggregate the source data.
	 *
	 * @param srcIDs            the src i ds
	 * @return the list
	 */
	@Override
	public List<SourceInfo> loadChannelFeeds(String srcIDs) {
		// First - execute the SQL query to load up all the channels
		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		
		String query = "SELECT DISTINCT " + " a.sourceType , a.serverAddUrl, a.scrUserName,"
				+ " b.channelPasswordEncr, " + " a.serverParameters,a.structContHandInfolet,a.saveAttachments,a.channelId,a.queueName,"
						+ "a.queueUrl,b.srcPk,b.emailType  FROM MsRgaChannelDetail a,MsRgaChannelDtlsSrc b WHERE " + " a.channelId=b.objectId AND a.sourceId=b.srcPk AND a.active = '1' AND  a.serverAddUrl IS NOT NULL";
		if (srcIDs != null) {
			query += srcIDsQueryTrail(srcIDs);
		}
		
		Query q=em.createQuery(query);
		Logger.debug(CLASS_ID, " executed ", null);
		List<SourceInfo> list=new ArrayList<SourceInfo>();
		List res= q.getResultList();
		list= getSourceListObject(res, true);
	    
	    PersistenceManager.INSTANCE.closeEM(em);
		Logger.debug(CLASS_ID, "ChannelFeed Count = " + list.size(), null);
		return list;
	}

	/**
	 * Gets the value.
	 *
	 * @param str the str
	 * @return the value
	 */
	public static String getValue(Object str){
		return (str==null?"":str.toString());
	}

	

	/**
	 * Method to load the timestamp for given server address in hash map.
	 *
	 * @param updatedFeedTimestamp            the updated feed timestamp
	 */
	@Override
	public void loadFeedTimestampsHashMap(Map<String, String> updatedFeedTimestamp) {
		
		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		Logger.debug(CLASS_ID, " executed ", null);
		List<MsRgaUrlLatestTimestamp> objec= em.createNamedQuery("MsRgaUrlLatestTimestamp.findAll", MsRgaUrlLatestTimestamp.class).getResultList();
		
		if(objec!=null && objec.size()>0){
			Logger.debug(CLASS_ID, "Preparing HashMap of Timestamps ...", null);
			
			for (MsRgaUrlLatestTimestamp slu : objec) {
				updatedFeedTimestamp.put(slu.getServerAddUrl(), slu.getLastResponseTimestamp());
			}
			
			Logger.info(CLASS_ID, "Persisted Timestamps \n " + updatedFeedTimestamp.keySet() + " : " + updatedFeedTimestamp.values(), null);
		}
		PersistenceManager.INSTANCE.closeEM(em);
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
		String query = "SELECT DISTINCT " + " a.sourceType, a.serverAddUrl, a.scrUserName, a.scrPassword, " +
		 " a.serverParameters,a.structContHandInfolet,a.saveAttachments,a.channelId,a.queueName,a.queueName,b.srcPk  FROM MsRgaChannelDetail "
		 + "a,MsRgaChannelDtlsSrc b WHERE " + " a.channelId=b.objectId AND a.sourceId=b.srcPk AND active = '1' AND  a.serverAddUrl IS NOT NULL";
		if (srcIDs != null) {
			query += srcIDsQueryTrail(srcIDs);
		}
		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		List<SourceInfo> list=new ArrayList<SourceInfo>();
		try{
		Query q=em.createQuery(query);
		Logger.debug(CLASS_ID, " executed ", null);
		
		List res= q.getResultList();
		list= getSourceListObject(res, false);
		Logger.debug(CLASS_ID, "ChannelFeed Count = " + list.size(), null);
		}
		catch(Exception ex){
			Logger.error(CLASS_ID, "Error while load loadAMQFileChannelFeeds method= " , ex);
		}
		finally{
		PersistenceManager.INSTANCE.closeEM(em);
		}
		return list;
	}
	
	
	/**
	 * Gets the list SourceInfo object for given list of Object string
	 *
	 * @param res the res
	 * @return the list object
	 */
	public List<SourceInfo>  getSourceListObject(List res,boolean isContainEmailType){
		List<SourceInfo> list=new ArrayList<SourceInfo>();
	    for (Object object : res) {
	    	Object[] l=(Object[])object;
	    	Logger.debug(CLASS_ID, "getAllProperties--rs.getString(1): " + getValue(l[0]), null);
	    		SourceInfo info=new SourceInfo();
	    		info.setSourceType(getSourceType(getValue(l[0])));
	    		info.setServerURL(getValue(l[1]));
	    		info.setUserName(getValue(l[2]));
	    		if(getValue(l[3]).equalsIgnoreCase(""))
	    			info.setPassword(getValue(l[3]));
	    		else
	    			info.setPassword(EncryptionUtilities.decrypt(getValue(l[3])));
	    		info.setParameters(getValue(l[4]));
	    		info.setStructureInfolet(getValue(l[5]));
	    		info.setSaveAttachments(getValue(l[6]));
	    		info.setChannelId(getValue(l[7]));
	    		info.setQueueName(getValue(l[8]));
	    		info.setQueueURL(getValue(l[9]));
	    		info.setSrcPk(getValue(l[10]));
	    		if(isContainEmailType)
	    			info.setEmailType(getEmailServerType(getValue(l[11])));
	    		list.add(info);
			}
	    return list;
	}
	
	/**
	 * Method to update the time stamp for given sources
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
				rs = stmt.getResultSet();
				try{
					while (rs.next()) {
						Logger.debug(CLASS_ID, "Updated " + rs.getInt(1) + "records against Server/Url " + pairs.getKey(), null);
					}
				}catch(Exception ex){
					Logger.error(CLASS_ID,"Exception occured "+ex.getMessage(),null);
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
					Logger.error(CLASS_ID, "Error while closing conectio", e);
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
		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		String query = "select t.metricId from SiMetricsT t where t.metricName = ?";
		long metricId = -1L;
		try {
		
			Query q=em.createQuery(query);
			Logger.debug(CLASS_ID, " executed ", null);
			List<SiMetricsT> res= q.getResultList();
		    
			for (SiMetricsT siMetricsT : res) {
				metricId = siMetricsT.getMetricId().longValue();
			}
			return metricId;
		} catch (Exception e) {
			Logger.error("DBManager", "recieved SQL Exception for query:" + query, e);
			return -1L;
		} finally {
			PersistenceManager.INSTANCE.closeEM(em);
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
	 * Method to add the Alert to MsRgaAggregator table
	 *
	 * @param alert
	 *            the alert
	 * @throws GrciException
	 *             the grci exception
	 */
	@Override
	public void addAlert(Alert alert) throws GrciException {
		Connection con = null;
		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		long metricId = getMetricId("MS RGA Channel Aggregator");
		Logger.debug("DBManager", "metricId  " + metricId, null);
		if (metricId < 0L) {
			throw new GrciException("Please ensure an infolet by the exact name \"MS RGA Channel Aggregator\" (without quotes) is installed and is enabled");
		}
		try {
			con = getConnection();
			 MsRgaAggregator agg=new MsRgaAggregator();
			 agg.setAttachment(alert.getAttachment());
			 agg.setBody(alert.getBody());
			 agg.setChannelId(alert.getChannel().getId());
			 agg.setCreationDate(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
			 agg.setFeedDatdId(0);
			 agg.setFeedSentDate(new java.sql.Date(alert.getFeedSentDate().getTime()));
			 agg.setFeedSequence(getSequenceNum(con));
			 agg.setMessageUID(alert.getMsgId());
			 agg.setProcessedFlag("");
			 agg.setRecipient(alert.getRecipient());
			 agg.setSender(alert.getSender());
			 agg.setServer(alert.getChannel().getServer());
			 //agg.setSrkPk(alert.getChannel().getSourceid());
			 agg.setStrutConentId(-1);
			 agg.setSubject(alert.getSubject());
			 em.persist(agg);
			
		} catch (Exception e) {
			Logger.error("DBManager", "recieved SQL Exception", e);
			throw new GrciException(e);
		} finally {
			 PersistenceManager.INSTANCE.closeEM(em);
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
	 * Method to create a channel.
	 *
	 * @param c the MsRgaChannelDtl object
	 * @throws GrciException             the grci exception
	 */
	@Override
	public void createChannel(MsRgaChannelDtl c) throws GrciException {
		Logger.error("DBManager", "inside createChannel", null);
		String channelId = getNewChannelId();
		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		String query = "insert into MS_RGA_CHANNEL_DTLS("
				+ "HDN_CHNL_ID_CNT,CHANNEL_TYPE,CHANNEL_STATUS,CHANNEL_NAME,DD_STATUS_FLAG,"
				+ "CUSTOM_FIELD2,OBJECT_ID,SAVE_ATTACHMENTS,DD_OBJECT_TYPE,DD_PROCESS_INSTANCE_ID,"
				+ "DD_INSTANCE_ID,DD_CREATED_ON,DD_CREATED_BY,DD_MODIFIED_ON,DD_MODIFIED_BY,"
				+ "DD_PROCESS_CODE) values('0','1','1',?,'Y','2',?,'yes','MS_RGA_CHANNEL_DTLS',?,? ,SYSDATE,'SYSTEMI',SYSDATE,'SYSTEMI','MS_RGA_CHANNEL_DTLS')";
		Logger.error("DBManager", "query  " + query, null);
		try {
			MsRgaChannelDtl d=new MsRgaChannelDtl();
			
			d.setChannelCreatedBy(c.getChannelCreatedBy());
			d.setChannelCreatedOn(d.getChannelCreatedOn());
			d.setChannelId(c.getChannelId());
			d.setChannelName(c.getChannelName());
			d.setChannelStatus(c.getChannelStatus());
			d.setChannelType(c.getChannelType());
			d.setDdCreatedBy(d.getDdCreatedBy());
			d.setDdCreatedOn(c.getDdCreatedOn());
			d.setDdEditFlag(c.getDdEditFlag());
			d.setDdObjectType(c.getDdObjectType());
			d.setDdProcessCode(c.getDdProcessCode());
			d.setDdInstanceId(c.getDdInstanceId());
			d.setDdStatusFlag(c.getDdStatusFlag());
			d.setEffectiveStartDate(c.getEffectiveStartDate());
			d.setEffectiveEndDate(c.getEffectiveEndDate());
			d.setHdnChnlIdCnt(c.getHdnChnlIdCnt());
			d.setObjectId(c.getObjectId());
			d.setObjectName(c.getObjectName());
			d.setSaveAttachments(c.getSaveAttachments());
			d.setStConInfolet(c.getStConInfolet());
			d.setStructContHandInfolet(c.getStructContHandInfolet());
			d.setChannelId(channelId);
			em.persist(d);
			Logger.error("DBManager", "updates = " , null);
		} catch (Exception e) {
			Logger.error("DBManager", "recieved SQL Exception", e);
			throw new GrciException(e);
		} finally {
			 PersistenceManager.INSTANCE.closeEM(em);
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
			closeResouces(null, null, con);
		}
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
		return JPAConstants.CHN + id;
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
	 * Close resouces.
	 *
	 * @param stmt
	 *            the stmtemenet Object
	 * @param rs
	 *            the result set object
	 * @param con
	 *            the connection object
	 */
	private void closeResouces(Statement stmt, ResultSet rs, Connection con) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				Logger.error(CLASS_ID, "SQLException", e);
			}
		}
		
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				Logger.error(CLASS_ID, "SQLException", e);
			}
		}
		
		if (con != null) {
			Logger.debug(CLASS_ID, "Returning connection...", null);
			Controller.getConnectionPool().returnConnection(con);
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
		return JPAConstants.SRC + id;
	}
	
	/**
	 * Method to Add new sources to a channel
	 *
	 * @param objectId            the object id
	 * @param src the source details
	 * @throws GrciException             the grci exception
	 */
	@Override
	public void addSource(String objectId, MsRgaChannelDtlsSrc src) throws GrciException {
		PreparedStatement pstmt = null;
		String sourceId = getNewSourceId();
		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		try {
			MsRgaChannelDtlsSrc s=new MsRgaChannelDtlsSrc();
			s.setActivationCode(src.getActivationCode());
			s.setChannelKeywords(src.getChannelKeywords());
			s.setChannelPassword(src.getChannelPassword());
			s.setChannelPasswordEncr(src.getChannelPasswordEncr());
			s.setChannelUsername(src.getChannelUsername());
			s.setContentCategory(src.getContentCategory());
			s.setContentType(src.getContentType());
			s.setDdStatusFlag(src.getDdStatusFlag());
			s.setDeliveryFormat(src.getDeliveryFormat());
			s.setDeliverySchedule(src.getDeliverySchedule());
			s.setEmailFrom(src.getEmailFrom());
			s.setExpiryDate(src.getExpiryDate());
			s.setFlagSubmitted(src.getFlagSubmitted());
			s.setObjectId(objectId);
			s.setQueueName(src.getQueueName());
			s.setQueueUrl(src.getQueueUrl());
			s.setServerAddress(src.getServerAddress());
			s.setServerParameters(src.getServerParameters());
			s.setSourceType(src.getSourceType());
			s.setSrcCreatedBy(src.getSrcCreatedBy());
			s.setSrcCreatedOn(src.getSrcCreatedOn());
			s.setSrcPk(sourceId);
			em.persist(s);
			
			Logger.error("DBManager", "updates = " + pstmt.getUpdateCount(), null);
		} catch (Exception e) {
			Logger.error("DBManager", "recieved SQL Exception", e);
			throw new GrciException(e);
		} finally {
			PersistenceManager.INSTANCE.closeEM(em);
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
		EntityManager em=PersistenceManager.INSTANCE.getEntityManager();
		String query = "select mstr.channelId as OBJECT_ID,src.serverAddress as SERVER from MsRgaChannelMaster mstr, MsRgaChannelDtlsSrc src where mstr.channelType = 1 and mstr.active=1 and upper(mstr.channelName) = ? and mstr.channelId = src.objectId";
		Logger.error("DBManager", "query = " + channel.getName(), null);
		try {
		
			 Query q = em.createQuery(query);
			 q.setParameter(1, name.toUpperCase());
			    @SuppressWarnings("unchecked")
			    List res= q.getResultList();
			    for (Object object : res) {
			    	Object[] l=(Object[])object;
			    	Logger.debug(CLASS_ID, "getAllProperties--rs.getString(1): " + getValue(l[0]), null);
					Logger.debug(CLASS_ID, "getAllProperties--rs.getString(2): " + getValue(l[1]), null);
					channel.setId(getValue(l[0]));
					channel.setServer(getValue(l[1]));
			}
		} catch (Exception e) {
			Logger.error("DBManager", "recieved SQL Exception for query:" + query, e);
			throw new GrciException(e);
		} finally {
			PersistenceManager.INSTANCE.closeEM(em);
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
		EntityManager em=PersistenceManager.INSTANCE.getEntityManager();
		String query = "select src.serverAddress,src.srkPk from MsRgaChannelDtlsSrc src where src.objectId = ?";
		List<Source> sourceList = new ArrayList<Source>();
		try {
			 Query q = em.createQuery(query);
			 q.setParameter(1, channelId);
			
			 List<MsRgaChannelDtlsSrc> list= q.getResultList();
			 for (MsRgaChannelDtlsSrc msRgaChannelDtlsSrc : list) {
				Source src=new Source();
				src.setChannelId(channelId);
				src.setServer(msRgaChannelDtlsSrc.getServerAddress());
				src.setSrcId(msRgaChannelDtlsSrc.getSrcPk());
				sourceList.add(src);
			}
			Logger.error("DBManager", "number of sources for channel =" + sourceList.size(), null);
		} catch (Exception e) {
			Logger.error("DBManager", "recieved SQL Exception for query:" + query, e);
		} finally {
			PersistenceManager.INSTANCE.closeEM(em);
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
		String query = "SELECT v" + " FROM MsRgaContentBrowserOperV v" + " WHERE v.processInstanceId=?";
		Logger.debug(CLASS_ID, "Query:" + query, null);
		GRCFObjectDetails grcfObj = null;
		EntityManager em=PersistenceManager.INSTANCE.getEntityManager();
		try {
			grcfObj = new GRCFObjectDetails();
			Query q=em.createQuery(query);
			 q.setParameter(1, pid);
			List<MsRgaContentBrowserOperV> l = q.getResultList();
			for (MsRgaContentBrowserOperV m : l) {
				
				String strContentProvider = m.getContentProvider();
				String strContentCategory = m.getContentCategory();
				String strContentVersion =m.getContentVersion();
				//String clbContentJson = 
				//long len = clbContentJson.length();
				String strContentJson = m.getContentJson();
				Logger.debug(CLASS_ID, "JSON String:" + strContentJson, null);
				List<ContentWrapperObj> wrapperList = (List<ContentWrapperObj>) new Gson().fromJson(strContentJson, new TypeToken<List<ContentWrapperObj>>() {
				}.getType());
				grcfObj.setSelectedObj(wrapperList);
				String strAocOwnerOrg = m.getAocOwnerOrg();
				String strAocOwner = m.getAocOwner();
				String strAocRestrictAccess = m.getAocRestrictAccessTo();
				String strReqOwnerOrg = m.getReqOwnerOrg();
				String strReqOwner = m.getReqOwner();
				String strReqRestrictAccess = m.getReqRestrictAccessTo();
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
			
			
		} catch (Exception e) {
			Logger.error("DBManager", "recieved SQL Exception for query:" + query, e);
		} finally {
			PersistenceManager.INSTANCE.closeEM(em);
		}
		return grcfObj;
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
	@Override
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
					Logger.error(CLASS_ID, "Error while returning connection...", e);
				}
			}
		}
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
}
