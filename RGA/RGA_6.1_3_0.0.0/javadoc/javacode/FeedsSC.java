package com.metricstream.rga;

import java.util.Date;

import com.metricstream.log.Logger;
import com.metricstream.systemi.rga.api.AlertApi;
import com.metricstream.systemi.rga.domain.Alert;
import com.metricstream.systemi.rga.domain.Channel;
import com.metricstream.systemi.rga.exception.GrciException;

public class FeedsSC
{
  private static final String CLASS_ID = "FeedsSC";
  
  public Object process(String server, String sender, String recipient, String subject, String body, String attachmentId, String saveAttachments, String infoletId, String instanceId)
  {
	
	  Logger.debug("FeedsSC", "inside process", null);
    try
    {
      addAlerts(server, sender, recipient, subject, body, attachmentId, saveAttachments, infoletId, instanceId);
      return "SUCCESS";
    }
    catch (GrciException e)
    {
      Logger.error("FeedsSC", "grci exception", e);
    }
    return "FAILURE";
  }
  
  private void addAlerts(String server, String sender, String recipient, String subject, String body, String attachmentId, String saveAttachments, String infoletId, String instanceId)
    throws GrciException
  {
    Channel channel = new Channel();
    channel.setName("gmail feeds");
    channel.setId("CHN-000049");
    
    AlertApi alertUtils = new AlertApi();
    Logger.debug("FeedsSC", "inside addAlerts", null);
    Logger.debug("FeedsSC --> subject >> ", subject, null);
    Alert alert = new Alert(sender, recipient, subject, body, attachmentId, null, new Date(), channel);
    
    alertUtils.addAlert(alert);
    //alertUtils.processAlerts();
    Logger.debug("FeedsSC", "inside addAlerts end", null);
  }
  
  public static void main(String[] args)
  {
    System.out.println((int)(Math.random() * 100.0D));
  }
}
