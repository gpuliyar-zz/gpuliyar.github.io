 DECLARE
BEGIN
  DELETE FROM ms_apps_mdf_registry
   WHERE blueprint_code = 'MS_RGA_NOTIFY_USERS'
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
    ('ms_mdf_ms_rga_n_tran_1_pkg',
     'ms_mdf_ms_rga_n_tran_1_pkg',
     0,
     TO_DATE('11/05/2012 16:04:15', 'MM/DD/YYYY HH24:MI:SS'),
     'PACKAGE',
     'TRANSITIONS',
     'MS_RGA_NOTIFY_USERS',
     'N',
     'MS_RGA_NOTIFY_USERS',
     1);

  COMMIT;

EXCEPTION
  WHEN OTHERS THEN
    DBMS_OUTPUT.put_line('Error Message is ' || SQLERRM);
END;
