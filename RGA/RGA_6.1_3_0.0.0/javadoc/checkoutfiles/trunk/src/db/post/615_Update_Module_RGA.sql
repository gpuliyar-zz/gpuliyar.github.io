 DECLARE
BEGIN
  UPDATE si_register_application
     SET module_name = 'RGA'
   WHERE module_name = 'GRCI';

  COMMIT;
EXCEPTION
  WHEN OTHERS THEN
    DBMS_OUTPUT.put_line('Error in updateing Module name in si_register_application table');
END;
