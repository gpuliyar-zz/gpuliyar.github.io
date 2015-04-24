package com.metricstream.systemi.rga.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.metricstream.systemi.ext.infolet.ChannelFeedData;
import com.metricstream.systemi.ext.infolet.FeedXtraxnStats;
import com.metricstream.systemi.jpa.entity.MsRgaChannelDtl;
import com.metricstream.systemi.jpa.entity.MsRgaChannelDtlsSrc;
import com.metricstream.systemi.rga.domain.Alert;
import com.metricstream.systemi.rga.domain.Channel;
import com.metricstream.systemi.rga.domain.Source;
import com.metricstream.systemi.rga.domain.SourceInfo;
import com.metricstream.systemi.rga.exception.GrciException;
import com.metricstream.systemi.rga.exception.IllegalChannelStateException;
import com.metricstream.systemi.rga.grcobject.GRCFObjectDetails;

/**
 * The Interface IDBHelper.
 * @author asif.u
 */
public interface IDBHelper {

		/**
		 * Gets the user id.
		 *
		 * @return the user id
		 * @throws SQLException the SQL exception
		 */
		public int getUserId() throws SQLException;
		

		/**
		 * Send failure notifications.
		 *
		 * @param invalidFeedStats the invalid feed stats
		 */
		public void sendFailureNotifications(List<FeedXtraxnStats> invalidFeedStats);
		
		/**
		 * Call pl sql infolet.
		 *
		 * @param callString the call string
		 */
		public void callPlSqlInfolet(String callString);
		
		/**
		 * Send notifications.
		 *
		 * @param notifyMap the notify map
		 */
		public void sendNotifications(Map<Long, ChannelFeedData> notifyMap);
		
		/**
		 * Call pl sql function.
		 *
		 * @param query the query
		 * @param val1 the val1
		 * @param val2 the val2
		 * @param val3 the val3
		 * @param val4 the val4
		 * @return the string
		 */
		public String callPLSqlFunction(String query, String val1, String val2, String val3, String val4);
		
		/**
		 * Gets the invocation status.
		 *
		 * @param infoletId the infolet id
		 * @param instanceId the instance id
		 * @return the invocation status
		 */
		public String getInvocationStatus(long infoletId, long instanceId);
		/**
		 * Gets the all properties.
		 *
		 * @return the all properties
		 */
		public Properties getAllProperties();
		
		/**
		 * Gets the activation code for the given channel ID
		 *
		 * @param channelId the channel id
		 * @return the activation code
		 */
		public List<String> getActivationCode(String channelId);		
		/**
		 * Write to aggregator table.
		 *
		 * @param feeds
		 *            the feeds
		 * @return the int
		 */
		public int writeToAggregatorTable(ArrayList<ChannelFeedData> feeds);
		
		
		/**
		 * This method is used to retrieve the source Ids for the passed in username.
		 *
		 * @param userName the user name
		 * @return Comma seperated list of source IDs
		 */
		public String getSourceDetailsForUser(String userName);
		
		/**
		 * Format the comma seperated source id list as needed by the query.
		 *
		 * @param srcIDs
		 *            : A comma or ";" separated list of source IDs from channel details table
		 * @return the string
		 */
		public String srcIDsQueryTrail(String srcIDs);
		
		/**
		 * Load channel feeds.
		 *
		 * @param srcIDs
		 *            the src i ds
		 * @return the list
		 */
		public List<SourceInfo> loadChannelFeeds(String srcIDs) ;

		

		/**
		 * Load feed timestamps hash map.
		 *
		 * @param updatedFeedTimestamp
		 *            the updated feed timestamp
		 */
		public void loadFeedTimestampsHashMap(Map<String, String> updatedFeedTimestamp) ;

		/**
		 * loadAMQFileChannelFeeds gets list of AMQ File channels.
		 *
		 * @param srcIDs
		 *            the src i ds
		 * @return List<SourceInfo>
		 */
		public List<SourceInfo> loadAMQFileChannelFeeds(String srcIDs) ;
		
		/**
		 * Update t stamps.
		 *
		 * @param latestFeedTStamps
		 *            the latest feed t stamps
		 */
		public void updateTStamps(Map<String, String> latestFeedTStamps) ;
		
		
		/**
		 * Gets the metric id.
		 *
		 * @param metricName
		 *            the metric name
		 * @return the metric id
		 */
		public long getMetricId(String metricName) ;
		
		/**
		 * Gets the connection.
		 *
		 * @return the connection
		 * @throws SQLException
		 *             the SQL exception
		 */
		public Connection getConnection() throws SQLException;
		
		/**
		 * Adds the alert.
		 *
		 * @param alert
		 *            the alert
		 * @throws GrciException
		 *             the grci exception
		 */
		public void addAlert(Alert alert) throws GrciException ;
		
		/**
		 * Gets the sequence num.
		 *
		 * @param con
		 *            the con
		 * @return the sequence num
		 */
		public int getSequenceNum(Connection con);
		
		/**
		 * Creates the channel.
		 *
		 * @param c the c
		 * @throws GrciException             the grci exception
		 */
		public void createChannel(MsRgaChannelDtl c) throws GrciException;
		
		/**
		 * Gets the process instance id.
		 *
		 * @return the process instance id
		 */
		public long getProcessInstanceId() ;
		
		/**
		 * Gets the instance id.
		 *
		 * @return the instance id
		 * @throws SQLException
		 *             the SQL exception
		 */
		public long getInstanceId() throws SQLException ;
		/**
		 * Gets the new channel id.
		 *
		 * @return the new channel id
		 * @throws GrciException
		 *             the grci exception
		 */
		public String getNewChannelId() throws GrciException ;
		
		/**
		 * Gets the next sequence.
		 *
		 * @param sequenceName
		 *            the sequence name
		 * @return the next sequence
		 */
		public long getnextSequence(String sequenceName) ;
		

	


		 /*
		 *
		 * @return the new source id
		 * @throws GrciException
		 *             the grci exception
		 */
		/**
 		 * Gets the new source id.
 		 *
 		 * @return the new source id
 		 * @throws GrciException the grci exception
 		 */
 		public String getNewSourceId() throws GrciException ;
		
		/**
		 * Adds the source.
		 *
		 * @param objectId            the object id
		 * @param src the src
		 * @throws GrciException             the grci exception
		 */
		public void addSource(String objectId, MsRgaChannelDtlsSrc src) throws GrciException ;
		
		/**
		 * Validate channel.
		 *
		 * @param channel
		 *            the channel
		 * @throws IllegalChannelStateException
		 *             the illegal channel state exception
		 */
		public void validateChannel(Channel channel) throws IllegalChannelStateException;

		
		/**
		 * Construct channel details.
		 *
		 * @param channel
		 *            the channel
		 * @throws GrciException
		 *             the grci exception
		 */
		public void constructChannelDetails(Channel channel) throws GrciException ;
		
		/**
		 * Gets the all server for channel.
		 *
		 * @param channelId
		 *            the channel id
		 * @return the all server for channel
		 */
		public List<Source> getAllServerForChannel(String channelId) ;
		
		/**
		 * This method reads the content details from the client oracle db.
		 *
		 * @param pid
		 *            Process Id
		 * @return Object representation
		 */
		public GRCFObjectDetails getContentBrowserDetails(String pid) ;

		
		public void populateNotificationTable(Map<Long, Object[]> notifyMap);

}
