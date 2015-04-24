  DECLARE
       vnum  number(10);
       vchar varchar2(2000);
       
BEGIN
       select metric_id into vnum 
       from si_metrics_t where metric_name = 'MS_RGA_GRCI_LOG';
       
       vchar := 'create or replace view ms_rga_log_summary_v as
                 select REASON,STATUS,MISSED,REJECTD,ACCEPTD,UPDATES,ELAPSE,END,STAR,PREV,SERVER,
                 TYPE,THREAD,INSTANCE_REC_NUM,INSTANCE_ID,METRIC_ID,METRIC_RUN_DATE,METRIC_COMPLETED_DATE,
                 LATEST_FLAG,CREATED_BY,CREATION_DATE,PROCESS_FLOW_STATUS,PROCESS_INSTANCE_ID,
                 MULTIROW_GROUP_NAME,ELECTRONICALLY_SIGNED,MULTIROW_REGION_ID from si_'||vnum||'_t
                 order by instance_rec_num desc';
       
      EXECUTE IMMEDIATE vchar;
      
      dbms_output.put_line('sus');
      
       
EXCEPTION

       WHEN OTHERS THEN
       DBMS_OUTPUT.PUT_LINE('Error in Creation of view');
       
END;
