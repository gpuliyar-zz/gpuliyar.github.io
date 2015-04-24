package com.metricstream.rga.sc.dao;

import com.metricstream.log.Logger;
import com.metricstream.systemi.interfaces.ConnectionPool;
import com.metricstream.systemi.server.common.Controller;
import com.metricstream.systemi.sql.SQLHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.TreeSet;

public class Dao
{
  private static final String CLASS_ID = "DBManager";
  private static Dao dbMgr = null;
  private static NavigableSet<String> idList = null;
  
  public static Dao getInstance()
  {
    if (dbMgr == null) {
      dbMgr = new Dao();
    }
    return dbMgr;
  }
  
  private Connection getConnection()
    throws SQLException
  {
    return Controller.getConnectionPool().getTransactionalConnection("DBManager");
  }
  
  private static void closeResouces(Statement stmt, Connection con, ResultSet rs)
  {
    SQLHelper.closeStatement(stmt);
    SQLHelper.closeResultSet(rs);
    Controller.getConnectionPool().returnConnection(con);
  }
  
  private static void cacheIds()
    throws SQLException
  {
    Connection con = null;
    String query = "Select task_id from sc_infolet_check_2";
    Statement stmt = null;
    ResultSet rs = null;
    idList = new TreeSet();
    try
    {
      con = Controller.getConnectionPool().getTransactionalConnection("DBManager");
      
      stmt = con.createStatement();
      rs = stmt.executeQuery(query);
      while (rs.next()) {
        idList.add(rs.getString(1));
      }
    }
    finally
    {
      closeResouces(stmt, con, rs);
    }
  }
  
  public String updateValues(String server, String sender, String recipient, String subject, String body, String attachmentid, String saveAttachments, String infoletId, String instanceId)
  {
    Connection con = null;
    String query = "update SC_INFOLET_CHECK_2  set server=?,sender=?,recipient=?,subject=?,ATTACHMENTID =?,SAVEATTACHMENTS =?,infoletid=?,instanceid=? where task_id =?";
    PreparedStatement pstmt = null;
    try
    {
      con = getConnection();
      pstmt = con.prepareStatement(query);
      for (Iterator i$ = idList.descendingSet().iterator(); i$.hasNext();)
      {
        String id = (String)i$.next();
        if (subject.contains(id))
        {
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
    }
    catch (Exception e)
    {
      String id;
      Logger.error("DBManager", "Exception! for " + query, e);
      return "FAILURE";
    }
    finally
    {
      closeResouces(pstmt, con, null);
    }
  }
  
  public String insertValues(String server, String sender, String recipient, String subject, String body, String attachmentid, String saveAttachments, String infoletId, String instanceId)
  {
    Connection con = null;
    String query = "insert into Generic_SCI_CHECK values (?,?,?,?,?,?,?,?,?)";
    PreparedStatement pstmt = null;
    try
    {
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
      pstmt.setString(9, body);
      
      pstmt.executeUpdate();
      return "SUCCESS";
    }
    catch (Exception e)
    {
      Logger.error("DBManager", "Exception! for " + query, e);
      return "FAILURE";
    }
    finally
    {
      closeResouces(pstmt, con, null);
    }
  }
}