 declare

  vmetric_id  si_metrics.METRIC_ID%type;
  vstring_sql varchar2(4000);

begin

  select metric_id
    into vmetric_id
    from si_metrics_t
   where metric_name = 'MS_RGA_ATTACHMENT'
     and metric_type = 10;

  vstring_sql := 'create or replace view MS_RGA_ATTACHMENT_V as SELECT "CUSTOM_FIELD3",
       "CUSTOM_FIELD2",
       "CUSTOM_FIELD1",
       "FEED_DATA_ID",
       "ATTACHMENT",
       "DD_PROCESS_CODE",
       "DD_CURRENT_STAGE",
       "DD_ENTERPRISE_INFO",
       "DD_EVENT_USER_NAME",
       "DD_CURRENT_USER_NAME",
       "DD_OBJECT_TYPE",
       "INSTANCE_REC_NUM",
       "INSTANCE_ID",
       "METRIC_ID",
       "METRIC_RUN_DATE",
       "METRIC_COMPLETED_DATE",
       "LATEST_FLAG",
       "CREATED_BY",
       "CREATION_DATE",
       "PROCESS_FLOW_STATUS",
       "PROCESS_INSTANCE_ID",
       "MULTIROW_GROUP_NAME",
       "ELECTRONICALLY_SIGNED",
       "MULTIROW_REGION_ID"
  FROM SI_' || vmetric_id || '_T';
  
  execute immediate(vstring_sql);

exception
  when others then
  
  dbms_output.put_line('error encountered');

end;
