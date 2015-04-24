 DECLARE
BEGIN
  DELETE FROM ms_rga_workflow_config
   WHERE workflow_name IN ('MS_RGA_LOG_ISSUES', 'MS_RGA_NOTIFY_USERS',
          'MS_RGA_CHANNEL_DTLS', 'MS_RGA_MY_SUBSCRIPTION', 'MS_RGA_EMAIL_CONTENT');

  COMMIT;

  INSERT INTO ms_rga_workflow_config
    (workflow_name, object_type)
  VALUES
    ('MS_RGA_NOTIFY_USERS', 'MS_RGA_NOTIFY_USERS');

  COMMIT;

  INSERT INTO ms_rga_workflow_config
    (workflow_name, region_name, field_name, field_check, DEFAULT_VALUE)
  VALUES
    ('MS_RGA_MY_SUBSCRIPTION',
     'CHN',
     'CHANNEL_NAME',
     'SUBSCRIPTION_ID',
     'SUBSCRIPTION_ID');

  COMMIT;

  INSERT INTO ms_rga_workflow_config
    (workflow_name, object_type)
  VALUES
    ('MS_RGA_CHANNEL_DTLS', 'MS_RGA_CHANNEL_DTLS');

  COMMIT;

  INSERT INTO ms_rga_workflow_config
    (workflow_name, region_name, field_name, field_check, DEFAULT_VALUE)
  VALUES
    ('MS_RGA_CHANNEL_DTLS', 'SRC', 'SRC_PK', 'MANDATORY', 'SRC_PK');

  COMMIT;

  INSERT INTO ms_rga_workflow_config
    (workflow_name,
     region_name,
     field_name,
     field_check,
     DEFAULT_VALUE,
     object_type)
  VALUES
    ('MS_RGA_LOG_ISSUES',
     'ISSUE',
     'ISSUE_TITLE_GRCI',
     'ISSUE_ID',
     'ISSUE_ID',
     'MS_RGA_LOG_ISSUES');

  COMMIT;

  INSERT INTO ms_rga_workflow_config
    (workflow_name, object_type)
  VALUES
    ('MS_RGA_EMAIL_CONTENT', 'MS_RGA_EMAIL_CONTENT');

  COMMIT;
EXCEPTION
  WHEN OTHERS THEN
    DBMS_OUTPUT.put_line('Error Message is ' || SQLERRM);
END;
