package com.metricstream.rga;

import com.metricstream.log.Logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GenericSC
{
  public static final String CLASS_ID = "GenericSC";
  
  public GenericSC()
  {
    Logger.debug("GenericSC", "inside constructor", null);
  }
  
  public Object process(String server, String sender, String recipient, String subject, String body, String attachmentId, String saveAttachments, String infoletId, String instanceId)
  {
    Logger.debug("GenericSC", "inside process", null);
    


    return logDetails(server, sender, recipient, subject, body, attachmentId, saveAttachments, infoletId, instanceId);
  }
  
  private Object logDetails(String server, String sender, String recipient, String subject, String body, String attachmentId, String saveAttachments, String infoletId, String instanceId)
  {
    String grci = System.getProperty("METRICSTREAM.HOME") + "/GRCI/";
    
    File dir = new File(grci);
    if (!dir.exists()) {
      dir.mkdirs();
    }
    BufferedWriter writer = null;
    try
    {
      writer = new BufferedWriter(new FileWriter(grci + "/" + subject + "-" + instanceId, true));
      
      writer.write(body);
      writer.flush();
      return "SUCCESS";
    }
    catch (Exception e)
    {
      Logger.error("GenericSC", "exception", e);
      return "FAILURE";
    }
    finally
    {
      if (writer != null) {
        try
        {
          writer.close();
        }
        catch (IOException e)
        {
          Logger.error("GenericSC", "exception", e);
        }
      }
    }
  }
}
