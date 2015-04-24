 DECLARE
BEGIN
  DELETE FROM ms_apps_mdf_registry
   WHERE blueprint_code = 'MS_RGA_CHANNEL_DTLS'
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
    ('ms_mdf_ms_rga_c_tran_1_pkg',
     'ms_mdf_ms_rga_c_tran_1_pkg',
     0,
     TO_DATE('11/06/2012 03:29:32', 'MM/DD/YYYY HH24:MI:SS'),
     'PACKAGE',
     'TRANSITIONS',
     'MS_RGA_CHANNEL_DTLS',
     'N',
     'MS_RGA_CHANNEL_DTLS',
     1);

  COMMIT;

EXCEPTION
  WHEN OTHERS THEN
    DBMS_OUTPUT.put_line('Error Message is ' || SQLERRM);
END;
