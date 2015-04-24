DECLARE
BEGIN
  DELETE FROM ms_apps_mdf_registry
   WHERE blueprint_code = 'MS_RGA_MY_SUBSCRIPTION_B' 
     AND target_type = 'TRANSITIONS';

  COMMIT;

  Insert into MS_APPS_MDF_REGISTRY
    (OBJECT_NAME,
     OBJECT_CODE,
     OBJECT_ORDER,
     CREATION_DATE,
     OBJECT_TYPE,
     TARGET_TYPE,
     TARGET_NAME,
     EDITABLE_FLAG,
     BLUEPRINT_CODE,
     BLUEPRINT_VERSION)
  Values
    ('ms_mdf_ms_rga_2_tran_1_pkg',
     'ms_mdf_ms_rga_2_tran_1_pkg',
     0,
     sysdate,
     'PACKAGE',
     'TRANSITIONS',
     'MS_RGA_MY_SUBSCRIPTION_B',
     'N',
     'MS_RGA_MY_SUBSCRIPTION_B',
     1);
  COMMIT;

EXCEPTION
  WHEN OTHERS THEN
    DBMS_OUTPUT.put_line('Error Message is ' || SQLERRM);
END;