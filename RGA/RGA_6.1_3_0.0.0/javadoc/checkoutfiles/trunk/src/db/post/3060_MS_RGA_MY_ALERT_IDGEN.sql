DECLARE
   x_app_id_rec             ms_apps_setups.application_id_rec;
   x_error_code             NUMBER;
   x_error_message          VARCHAR2 (4000);
   x_module_name            VARCHAR2(100) := 'RGA';
   x_id_name                VARCHAR2(100) := 'MS_RGA_MY_ALERTS_IDGEN';
   i                        NUMBER := 0;
   j                        NUMBER := 0;
BEGIN

    -- ID Header
    x_app_id_rec.id_header.module_name      := x_module_name;
    x_app_id_rec.id_header.id_name          := x_id_name;
    x_app_id_rec.id_header.delemiter        := '-';
    x_app_id_rec.id_header.reset_frequency  := '';
    x_app_id_rec.id_header.end_date         := '';
    x_app_id_rec.id_header.created_by       := 100002;
    x_app_id_rec.id_header.creation_date    := SYSDATE;
    x_app_id_rec.id_header.last_updated_by  := 100002;
    x_app_id_rec.id_header.last_updated_date := SYSDATE;
    x_app_id_rec.id_header.comments         := '';

    -- Component
    i := i + 1;
    x_app_id_rec.id_components(i).module_name      := x_module_name;
    x_app_id_rec.id_components(i).id_name          := x_id_name;
    x_app_id_rec.id_components(i).component_name   := 'Object Preference';
    x_app_id_rec.id_components(i).component_type   := 2; 
    x_app_id_rec.id_components(i).column_seq       := 1;
    x_app_id_rec.id_components(i).key_component    := 'N';
    x_app_id_rec.id_components(i).static_value     := 'ALERT';
    -- Component
    i := i + 1;
    x_app_id_rec.id_components(i).module_name      := x_module_name;
    x_app_id_rec.id_components(i).id_name          := x_id_name;
    x_app_id_rec.id_components(i).component_name   := 'Object Sequence';
    x_app_id_rec.id_components(i).component_type   := 1; 
    x_app_id_rec.id_components(i).column_seq       := 2;
    x_app_id_rec.id_components(i).key_component    := 'N';
    x_app_id_rec.id_components(i).seq_size         := 8;
    x_app_id_rec.id_components(i).seq_start_value  := 1000;
    x_app_id_rec.id_components(i).seq_increment_by := 1;
    
	
	ms_apps_setups.insert_application_id
        (i_application_id_rec => x_app_id_rec,
         o_error_code         => x_error_code,
         o_error_message      => x_error_message,
         i_create_pid         => 'Y');

EXCEPTION 
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error Message is '||x_error_message);
        
 END;
