 declare 

begin

delete  from MS_RGA_SUM_RPT_FOOLW_FLAG t
 where exists (select *
          from MS_RGA_SUM_RPT_FOOLW_FLAG t1
         where t.review_id = t1.review_id
           and t.rowid > t1.rowid);
           
delete  from MS_RGA_SUM_RPT_REVW_FLAG t
 where exists (select *
          from MS_RGA_SUM_RPT_REVW_FLAG t1
         where t.follow_up_id = t1.follow_up_id
           and t.rowid > t1.rowid);
           
commit;                      

exception when others then

dbms_output.put_line('Error Encountered');

end;