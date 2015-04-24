 DECLARE
BEGIN
  DELETE FROM ms_apps_mdf_registry
   WHERE blueprint_code = 'MS_RGA_CREATE_RELATION_BP'
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
    ('ms_mdf_ms_rga_n_control_pkg',
     'ms_mdf_ms_rga_n_control_pkg',
     0,
     SYSDATE,
     'PACKAGE',
     'FLOW',
     'MS_RGA_CREATE_RELATION_BP',
     'N',
     'MS_RGA_CREATE_RELATION_BP',
     1);

  COMMIT;
EXCEPTION
  WHEN OTHERS THEN
    DBMS_OUTPUT.put_line('Error Message is ' || SQLERRM);
END;
