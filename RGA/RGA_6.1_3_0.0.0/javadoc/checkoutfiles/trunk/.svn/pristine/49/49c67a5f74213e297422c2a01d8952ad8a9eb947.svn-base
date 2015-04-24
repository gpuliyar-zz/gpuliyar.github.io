 DECLARE
BEGIN
  DELETE FROM ms_apps_mdf_registry
   WHERE blueprint_code = 'MS_RGA_EMAIL_CONTENT'
     AND target_type = 'FLOW';

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
    ('ms_mdf_ms_rga_e_control_pkg',
     'ms_mdf_ms_rga_e_control_pkg',
     0,
     TO_DATE('11/05/2012 17:17:17', 'MM/DD/YYYY HH24:MI:SS'),
     'PACKAGE',
     'FLOW',
     'MS_RGA_EMAIL_CONTENT',
     'N',
     'MS_RGA_EMAIL_CONTENT',
     1);

  COMMIT;
EXCEPTION
  WHEN OTHERS THEN
    DBMS_OUTPUT.put_line('Error Message is ' || SQLERRM);
END;
