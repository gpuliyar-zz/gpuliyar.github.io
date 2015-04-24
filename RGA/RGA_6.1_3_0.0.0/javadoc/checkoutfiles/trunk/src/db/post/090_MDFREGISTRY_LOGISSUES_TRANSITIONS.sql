 DECLARE
BEGIN
  DELETE FROM ms_apps_mdf_registry
   WHERE blueprint_code = 'MS_RGA_LOG_ISSUES'
     AND target_type = 'TRANSITIONS';

  COMMIT;

  INSERT INTO ms_apps_mdf_registry
    (object_name,
     object_code,
     object_order,
     creation_date,
     object_type,
     target_type,
     target_name,
     editable_flag,
     blueprint_code,
     blueprint_version)
  VALUES
    ('ms_mdf_ms_rga_l_tran_1_pkg',
     'ms_mdf_ms_rga_l_tran_1_pkg',
     0,
     TO_DATE('09/11/2012 12:32:43', 'MM/DD/YYYY HH24:MI:SS'),
     'PACKAGE',
     'TRANSITIONS',
     'MS_RGA_LOG_ISSUES',
     'N',
     'MS_RGA_LOG_ISSUES',
     1);
EXCEPTION
  WHEN OTHERS THEN
    DBMS_OUTPUT.put_line('Error Message is ' || SQLERRM);
END;
