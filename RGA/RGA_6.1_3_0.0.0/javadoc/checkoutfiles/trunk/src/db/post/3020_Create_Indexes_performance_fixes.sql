declare

  verror_code varchar2(4000);
  verror_desc varchar2(4000);
  x_Metric_Id number;

begin

  begin
    execute immediate ('CREATE INDEX MS_RGA_AGGREGATOR_IDX_01 ON MS_RGA_AGGREGATOR (FEED_DATA_ID)');
 exception
    when others then
    
      verror_code := sqlcode;
      verror_desc := sqlerrm;
	  dbms_output.put_line('error while creating index MS_RGA_AGGREGATOR_IDX_02 ON MS_RGA_AGGREGATOR ');
      dbms_output.put_line(verror_desc || '...............' || verror_desc);
    
  end;

  begin
    execute immediate ('CREATE INDEX MS_RGA_AGGREGATOR_IDX_02 ON MS_RGA_AGGREGATOR (FEED_SENT_DATE)');
  exception
    when others then
    
      verror_code := sqlcode;
      verror_desc := sqlerrm;
	  dbms_output.put_line('error while creating index MS_RGA_AGGREGATOR_IDX_02 ON MS_RGA_AGGREGATOR ');
      dbms_output.put_line(verror_desc || '...............' || verror_desc);
    
  end;

  begin
    execute immediate ('CREATE INDEX MS_RGA_AGGREGATOR_IDX_03 ON MS_RGA_AGGREGATOR (channel_id)');
  exception
    when others then
    
      verror_code := sqlcode;
      verror_desc := sqlerrm;
	  dbms_output.put_line('error while creating index MS_RGA_AGGREGATOR_IDX_03 ON MS_RGA_AGGREGATOR ');
      dbms_output.put_line(verror_desc || '...............' || verror_desc);
    
  end;

   begin
    execute immediate ('DROP INDEX MS_RGA_CHNL_RESP_DTLS_IDX_02' );
  exception
    when others then
    
      verror_code := sqlcode;
      verror_desc := sqlerrm;
	  dbms_output.put_line('error while dropping index MS_RGA_CHNL_RESP_DTLS_IDX_02 ON MS_RGA_CHANNEL_RESPONSE_DTLS  ');
      dbms_output.put_line(verror_desc || '...............' || verror_desc);
    
  end;
  
  begin
    execute immediate ('CREATE INDEX MS_RGA_CHNL_RESP_DTLS_IDX_02 ON MS_RGA_CHANNEL_RESPONSE_DTLS (FEED_DATA_ID,PROCESSED)');
  exception
    when others then
    
      verror_code := sqlcode;
      verror_desc := sqlerrm;
	  dbms_output.put_line('error while creating index MS_RGA_CHNL_RESP_DTLS_IDX_02 ON MS_RGA_CHANNEL_RESPONSE_DTLS  ');
      dbms_output.put_line(verror_desc || '...............' || verror_desc);
    
  end;

  begin
    execute immediate ('CREATE INDEX MS_RGA_CHNL_RESP_DTLS_IDX_03 ON MS_RGA_CHANNEL_RESPONSE_DTLS (channel_id)');
  exception
    when others then
    
      verror_code := sqlcode;
      verror_desc := sqlerrm;
	  dbms_output.put_line('error while creating index MS_RGA_CHNL_RESP_DTLS_IDX_03 ON MS_RGA_CHANNEL_RESPONSE_DTLS   ');
      dbms_output.put_line(verror_desc || '...............' || verror_desc);
    
  end;
  
  begin
    execute immediate ('CREATE INDEX MS_RGA_SUBS_CHANNEL_CHN_IDX_01 ON MS_RGA_SUBS_CHANNEL_CHN (CHANNEL_NAME)');
  exception
    when others then
    
      verror_code := sqlcode;
      verror_desc := sqlerrm;
	  dbms_output.put_line('error while creating index MS_RGA_SUBS_CHANNEL_CHN_IDX_01 ON MS_RGA_SUBS_CHANNEL_CHN  ');
      dbms_output.put_line(verror_desc || '...............' || verror_desc);
    
  end;

  begin
    execute immediate ('CREATE INDEX MS_RGA_SUBS_CHANNEL_CHN_IDX_02 ON MS_RGA_SUBS_CHANNEL_CHN (CHN_CREATED_BY)');
  exception
    when others then
    
      verror_code := sqlcode;
      verror_desc := sqlerrm;
	  dbms_output.put_line('error while creating index MS_RGA_SUBS_CHANNEL_CHN_IDX_02 ON MS_RGA_SUBS_CHANNEL_CHN  ');
      dbms_output.put_line(verror_desc || '...............' || verror_desc);
    
  end;

  begin
    execute immediate ('CREATE INDEX MS_RGA_SUBS_CHANNEL_CHN_IDX_03 ON MS_RGA_SUBS_CHANNEL_CHN (STATUS)');
  exception
    when others then
    
      verror_code := sqlcode;
      verror_desc := sqlerrm;
	  dbms_output.put_line('error while creating index MS_RGA_SUBS_CHANNEL_CHN_IDX_03 ON MS_RGA_SUBS_CHANNEL_CHN  ');
      dbms_output.put_line(verror_desc || '...............' || verror_desc);
    
  end;
  
  begin
    execute immediate ('CREATE INDEX ms_rga_feed_user_info_idx_01 on ms_rga_feed_user_info(feed_data_id,USER_NAME)');
  exception
    when others then
    
      verror_code := sqlcode;
      verror_desc := sqlerrm;
	  dbms_output.put_line('error while creating index ms_rga_feed_user_info_idx_01 on ms_rga_feed_user_info ');
      dbms_output.put_line(verror_desc || '...............' || verror_desc);
    
  end;
  
  begin
    execute immediate ('CREATE INDEX MS_RGA_NOTIFY_USERS_idx_01 on MS_RGA_NOTIFY_USERS(DD_CREATED_BY)');
  exception
    when others then
    
      verror_code := sqlcode;
      verror_desc := sqlerrm;
	  dbms_output.put_line('error while creating index MS_RGA_NOTIFY_USERS_idx_01 on MS_RGA_NOTIFY_USERS ');
      dbms_output.put_line(verror_desc || '...............' || verror_desc);
    
  end;
  
  begin
    execute immediate ('CREATE INDEX MS_RGA_LOG_ISSUES_FND_idx_01 on MS_RGA_LOG_ISSUES_FND(ISSUE_CREATED_BY,TO_NUMBER(FEED_DATA_ID))');
  exception
    when others then
    
      verror_code := sqlcode;
      verror_desc := sqlerrm;
	  dbms_output.put_line('error while creating index MS_RGA_LOG_ISSUES_FND_idx_01 on MS_RGA_LOG_ISSUES_FND ');
      dbms_output.put_line(verror_desc || '...............' || verror_desc);
    
  end;
  
 
  
   Begin
   
	x_Metric_Id:= MS_APPS_UTILITIES.GET_INFOLET_ID ('MS_RGA_ALERT_OBJECT_RELATIONS');
	
    Execute Immediate ('CREATE INDEX MS_RGA_ORB_V_IDX01 ON SI_' ||
                      x_Metric_Id || '_T(DD_CURRENT_USER_NAME,TO_NUMBER(ALERT_ID))');
    
  Exception
    When Others Then
      verror_code := sqlcode;
      verror_desc := sqlerrm;
	  dbms_output.put_line('error while creating index MS_RGA_ORB_V_IDX01 ON SI_' ||
                      x_Metric_Id || '_T ');
      dbms_output.put_line(verror_desc || '...............' || verror_desc);
  End;
  
  Begin
   
	x_Metric_Id:= MS_APPS_UTILITIES.GET_INFOLET_ID ('MS_RGA_MY_SUBSCRIPTION');
	
    Execute Immediate ('CREATE INDEX MS_RGA_MY_SUB_IDX_01 ON SI_' ||
                      x_Metric_Id || '_T(PROCESS_INSTANCE_ID, INSTANCE_ID)');
					  
	
  Exception
    When Others Then
      verror_code := sqlcode;
      verror_desc := sqlerrm;
	  dbms_output.put_line('error while creating index MS_RGA_MY_SUB_IDX_01 ON SI_' ||
                      x_Metric_Id || '_T ');
      dbms_output.put_line(verror_desc || '...............' || verror_desc);
  End;
  
  Begin
   
	x_Metric_Id:= MS_APPS_UTILITIES.GET_INFOLET_ID ('MS_RGA_MY_SUBSCRIPTION');
	
	  
	Execute Immediate ('CREATE INDEX MS_RGA_MY_SUB_IDX_02 ON SI_' ||
                      x_Metric_Id || '_T(CHN_CREATED_BY)');			  
    
  Exception
    When Others Then
      verror_code := sqlcode;
      verror_desc := sqlerrm;
	  dbms_output.put_line('error while creating index MS_RGA_MY_SUB_IDX_02 ON SI_' ||
                      x_Metric_Id || '_T ');
      dbms_output.put_line(verror_desc || '...............' || verror_desc);
  End;
  
   Begin
   
	x_Metric_Id:= MS_APPS_UTILITIES.GET_INFOLET_ID ('MS_RGA_LOG_ISSUES');
	
    Execute Immediate ('CREATE INDEX MS_RGA_LOG_ISSUE_IDX_01 ON SI_' ||
                      x_Metric_Id || '_T(PROCESS_INSTANCE_ID, INSTANCE_ID)');
					  
	    
  Exception
    When Others Then
      verror_code := sqlcode;
      verror_desc := sqlerrm;
	  dbms_output.put_line('error while creating index MS_RGA_LOG_ISSUE_IDX_01 ON SI_' ||
                      x_Metric_Id || '_T ');
      dbms_output.put_line(verror_desc || '...............' || verror_desc);
  End;
  
  Begin
   
	x_Metric_Id:= MS_APPS_UTILITIES.GET_INFOLET_ID ('MS_RGA_LOG_ISSUES');
	
 					  
	Execute Immediate ('CREATE INDEX MS_RGA_LOG_ISSUE_IDX_02 ON SI_' ||
                      x_Metric_Id || '_T(ISSUE_CREATED_BY)');

	 Exception
    When Others Then
      verror_code := sqlcode;
      verror_desc := sqlerrm;
	  dbms_output.put_line('error while creating index MS_RGA_LOG_ISSUE_IDX_02 ON SI_' ||
                      x_Metric_Id || '_T ');
      dbms_output.put_line(verror_desc || '...............' || verror_desc);
  End;
  
  Begin
   
	x_Metric_Id:= MS_APPS_UTILITIES.GET_INFOLET_ID ('MS_RGA_LOG_ISSUES');
	
 					  
	Execute Immediate ('CREATE INDEX MS_RGA_LOG_ISSUE_IDX_03 ON SI_' ||
                      x_Metric_Id || '_T(OBJECT_ID)');	
    
  Exception
    When Others Then
      verror_code := sqlcode;
      verror_desc := sqlerrm;
	  dbms_output.put_line('error while creating index MS_RGA_LOG_ISSUE_IDX_03 ON SI_' ||
                      x_Metric_Id || '_T ');
      dbms_output.put_line(verror_desc || '...............' || verror_desc);
  End;
  
    Begin
   
	x_Metric_Id:= MS_APPS_UTILITIES.GET_INFOLET_ID ('MS_RGA_LOG_ISSUES');
	
 					  
	Execute Immediate ('CREATE INDEX MS_RGA_LOG_ISSUE_IDX_04 ON SI_' ||
                      x_Metric_Id || '_T(INSTANCE_ID)');	
    
  Exception
    When Others Then
      verror_code := sqlcode;
      verror_desc := sqlerrm;
	  dbms_output.put_line('error while creating index MS_RGA_LOG_ISSUE_IDX_04 ON SI_' ||
                      x_Metric_Id || '_T ');
      dbms_output.put_line(verror_desc || '...............' || verror_desc);
  End;
  
exception
  when others then
  
    verror_code := sqlcode;
    verror_desc := sqlerrm;
    dbms_output.put_line(verror_desc || '...............' || verror_desc);
  
end;
