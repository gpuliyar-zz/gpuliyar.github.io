 declare

  verror_code varchar2(4000);
  verror_desc varchar2(4000);

begin

  begin
    execute immediate ('create index MS_RGA_AGG_FILT_N4 on MS_RGA_FEED_AGG_FILTERED_T(FEED_DATA_ID)');
  exception
    when others then
    
      verror_code := sqlcode;
      verror_desc := sqlerrm;
      dbms_output.put_line(verror_desc || '...............' || verror_desc);
    
  end;

  begin
    execute immediate ('create index MS_RGA_SCRPTN_RES_IDX_03 on MS_RGA_SUBSCRIPTION_RESPONSE(PID)');
  exception
    when others then
    
      verror_code := sqlcode;
      verror_desc := sqlerrm;
      dbms_output.put_line(verror_desc || '...............' || verror_desc);
    
  end;

  begin
    execute immediate ('create index MS_RGA_SCRPTN_RES_IDX_04 on MS_RGA_SUBSCRIPTION_RESPONSE(USER_ID)');
  exception
    when others then
    
      verror_code := sqlcode;
      verror_desc := sqlerrm;
      dbms_output.put_line(verror_desc || '...............' || verror_desc);
    
  end;

  begin
    execute immediate ('create index MS_RGA_ALRT_SUM_REP_IDX1 on MS_RGA_ALERT_SUMMARY_REPORT_T (USER_ID)');
  exception
    when others then
    
      verror_code := sqlcode;
      verror_desc := sqlerrm;
      dbms_output.put_line(verror_desc || '...............' || verror_desc);
    
  end;

  begin
  
    update ms_rga_subs_channel_chn t set t.status = 1;
  
    commit;
  
  exception
    when others
    
     then
    
      dbms_output.put_line('error encountered');
    
  end;

  Begin
    verror_code    := Null;
    verror_desc := Null;
  
    Ms_Apps_Utilities.Create_Infolet_View('INFOLET',
                                          'MS_RGA_MY_SUBSCRIPTION',
                                          'MS_RGA_MY_SUBSCRIPTION_DV',
                                          verror_code,
                                          verror_desc);
  Exception
    When Others Then
      Null;
  End;

exception
  when others then
  
    verror_code := sqlcode;
    verror_desc := sqlerrm;
    dbms_output.put_line(verror_desc || '...............' || verror_desc);
  
end;
