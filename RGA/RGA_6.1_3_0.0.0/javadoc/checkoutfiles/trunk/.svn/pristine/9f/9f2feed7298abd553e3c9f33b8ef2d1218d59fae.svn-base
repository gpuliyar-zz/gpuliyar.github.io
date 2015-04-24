 declare
  vnum  number(20);
  vclob long;

begin

  select metric_id
    into vnum
    from si_metrics_t
   where metric_name = 'MS RGA Exception Type';

  vclob := 'CREATE OR REPLACE VIEW SI_' || vnum || '_V
(instance_rec_num, instance_id, metric_id, metric_run_date, metric_completed_date, latest_flag, created_by, creation_date, process_flow_status, process_instance_id, multirow_group_name, electronically_signed, multirow_region_id, stored_value, displayed_value, lov_name, locale_id, enterprise_id, lov_id, lov_value_id, org_entity_id, org_entity_name, start_date, end_date)
AS
SELECT rownum,5796,' || vnum ||
           ',sysdate,sysdate,''Y'',1,sysdate,4,-1,null,null,-1,InfoletQuery."STORED_VALUE",InfoletQuery."DISPLAYED_VALUE",InfoletQuery."LOV_NAME",InfoletQuery."LOCALE_ID",InfoletQuery."ENTERPRISE_ID",InfoletQuery."LOV_ID",InfoletQuery."LOV_VALUE_ID",InfoletQuery."ORG_ENTITY_ID",InfoletQuery."ORG_ENTITY_NAME",InfoletQuery."START_DATE",InfoletQuery."END_DATE" from (SELECT  stored_value, 
         displayed_value, 
         lov_name, 
         locale_id, 
         enterprise_id, 
         lov_id, 
         lov_value_id, 
         org_entity_id,
         org_entity_name, 
         start_date, 
         end_date
    FROM ms_qs_lov_org_values_v
   WHERE NVL (end_date, SYSDATE) >= SYSDATE 
   AND lov_name=''MS RGA List of Exception Types''
   ORDER BY stored_value) InfoletQuery where InfoletQuery.LOCALE_ID = NVL( SI_DB_LOCALE_SV.GETVALUE,1009)';

  execute immediate (vclob);

exception
  when others then
  
    dbms_output.put_line('error');
  
end;
