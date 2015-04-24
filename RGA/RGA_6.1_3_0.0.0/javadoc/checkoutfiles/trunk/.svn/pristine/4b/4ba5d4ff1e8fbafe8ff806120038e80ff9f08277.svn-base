 DECLARE
  vmetric_id si_metrics.metric_id%TYPE;
  x_view     VARCHAR2(4000);

  error_code varchar2(1000);
  error_desc varchar2(4000);
BEGIN
  SELECT t.metric_id
    INTO vmetric_id
    FROM si_metrics_t t
   WHERE metric_name = 'MS_RGA_VIEW_ALERT_SUMMARY_RPT';

  x_view := 'create or replace view ms_rga_alert_review_follow as
select CUSTUMFIELD5,
       CUSTUMFIELD4,
       CUSTUMFIELD3,
       CUSTUMFIELD2,
       CUSTUMFIELD1,
       ACTIONS,
       BODY,
       ATTACHMENT,
       FEED_DATA_ID,
       LOG_ISSUES,
       NOTIFY_USERS,
       FOLLOW_UP,
       REVIEWED,
       SFOLLOWUP,
       SREVIEWED,
       USER_ID,
       SUBSCRIPTION_ID,
       SUBS_RESPONSE_ID,
       ATTACHMENTS,
       TITLE_SUBJECT,
       SOURCE_ID,
       RESPONSE_DATE,
       RESPONSE_ID,
       CHANNEL_NAME,
       CHANNEL_ID,
       INSTANCE_REC_NUM,
       INSTANCE_ID,
       METRIC_ID,
       METRIC_RUN_DATE,
       METRIC_COMPLETED_DATE,
       LATEST_FLAG,
       CREATED_BY,
       CREATION_DATE,
       PROCESS_FLOW_STATUS,
       PROCESS_INSTANCE_ID,
       MULTIROW_GROUP_NAME,
       ELECTRONICALLY_SIGNED,
       MULTIROW_REGION_ID
  from si_' || vmetric_id ||
            '_t t where t.instance_id = (select max(instance_id) from si_' ||
            vmetric_id || '_t)';

  execute immediate (x_view);

EXCEPTION
  WHEN OTHERS THEN
    error_code := SQLCODE;
    error_desc := SQLERRM;
  
    dbms_output.put_line('error_code' || error_code);
  
    dbms_output.put_line(' error_desc' || error_desc);
  
END;
