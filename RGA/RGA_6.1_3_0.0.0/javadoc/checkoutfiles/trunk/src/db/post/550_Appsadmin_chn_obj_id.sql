 DECLARE
  x_app_id_rec1   ms_apps_setups.application_id_rec;
  x_app_id_rec2   ms_apps_setups.application_id_rec;
  x_error_code    NUMBER;
  x_error_message VARCHAR2(4000);
  x_module_name   VARCHAr2(100) := 'RGA';
  x_id_name1      VARCHAR2(100) := 'chn_obj_id';

  i NUMBER := 0;
  j NUMBER := 0;
BEGIN

  x_app_id_rec1.id_header.module_name   := x_module_name;
  x_app_id_rec1.id_header.id_name       := x_id_name1;
  x_app_id_rec1.id_header.delemiter     := '-';
  x_app_id_rec1.id_header.created_by    := -1;
  x_app_id_rec1.id_header.creation_date := SYSDATE;
  x_app_id_rec1.id_header.comments      := 'This is the header record for Object ID';

  i := i + 1;

  x_app_id_rec1.id_components(i).module_name := x_module_name;
  x_app_id_rec1.id_components(i).id_name := x_id_name1;
  x_app_id_rec1.id_components(i).component_name := 'static_text1';
  x_app_id_rec1.id_components(i).column_seq := i;
  x_app_id_rec1.id_components(i).component_type := 2;
  x_app_id_rec1.id_components(i).key_component := 'N';
  x_app_id_rec1.id_components(i).static_value := 'CHN';

  i := i + 1;

  x_app_id_rec1.id_components(i).module_name := x_module_name;
  x_app_id_rec1.id_components(i).id_name := x_id_name1;
  x_app_id_rec1.id_components(i).component_name := 'seq1';
  x_app_id_rec1.id_components(i).column_seq := i;
  x_app_id_rec1.id_components(i).component_type := 1;
  x_app_id_rec1.id_components(i).key_component := 'N';
  x_app_id_rec1.id_components(i).seq_size := 6;
  x_app_id_rec1.id_components(i).seq_start_value := 1;
  x_app_id_rec1.id_components(i).seq_increment_by := 1;

  ms_apps_setups.insert_application_id(i_application_id_rec => x_app_id_rec1,
                                       o_error_code         => x_error_code,
                                       o_error_message      => x_error_message,
                                       i_create_pid         => 'Y');

  DBMS_OUTPUT.put_line('Return Status from insert_application_id for Object id: ' ||
                       x_error_code);
  DBMS_OUTPUT.put_line('Return Message from insert_application_id for Object id: ' ||
                       x_error_message);
  COMMIT;

EXCEPTION
  WHEN OTHERS THEN
    NULL;
END;
