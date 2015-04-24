 DECLARE
  x_error_code    NUMBER;
  x_error_message VARCHAR2(4000);
BEGIN
  x_error_code    := NULL;
  x_error_message := NULL;
  ms_apps_utilities.create_infolet_view('INFOLET',
                                        'MS_RGA_LOG_ISSUES',
                                        'MS_RGA_LOG_ISSUES_V',
                                        x_error_code,
                                        x_error_message);
EXCEPTION
  WHEN OTHERS THEN
    NULL;
END;
