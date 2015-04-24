 DECLARE
  LV_SECURITY_INFOLET_ID VARCHAR2(100);
  lv_log_key        number;
  lv_log_seq        NUMBER:=1;
  ln_data_entry_id           number;
BEGIN
 
select metric_id
into lv_security_infolet_id 
from si_metrics_t where metric_name = 'MS_RGA_FORM_SECURITY_INFOLET';

FOR IDX IN (SELECT METRIC_ID
  FROM SI_METRICS_T
  where metric_name in
    (select  metric_name
    from si_metrics_t
    where metric_name in  ('MS_RGA_MY_ALERTS') 
	and metric_type = 10
))
LOOP
DELETE SI_ACCESS_CONTROL_INFOLETS 
WHERE OBJECT_ID = IDX.METRIC_ID;

INSERT INTO SI_ACCESS_CONTROL_INFOLETS ( ACCESS_CONTROL_INFOLET_ID, OBJECT_ID ) 
                                VALUES ( LV_SECURITY_INFOLET_ID, IDX.METRIC_ID );
END LOOP;

commit;
 
EXCEPTION WHEN NO_DATA_FOUND THEN
 ms_apps_product_error_log_pkg.message_log(
                P_LOG_KEY       => lv_log_key,
                p_log_sequence  => lv_log_seq,
                p_module        => 'BCM',
                p_error_code    => null,
                P_ERROR_MESSAGE => 'Error in setting Form security infolet : No Form Security Infolet Found' ||SQLERRM,
                p_program_name  => 'Post Install Script',
                P_LINE_LOCATION => $$PLSQL_LINE,
                p_log_type      => 'ERR');
WHEN OTHERS THEN ms_apps_product_error_log_pkg.message_log(
                P_LOG_KEY       => lv_log_key,
                p_log_sequence  => lv_log_seq,
                p_module        => 'BCM',
                p_error_code    => null,
                P_ERROR_MESSAGE => 'Error in setting Form security infolet : '||SQLERRM,
                p_program_name  => 'Post Install Script',
                P_LINE_LOCATION => $$PLSQL_LINE,
                p_log_type      => 'ERR');
END;  


