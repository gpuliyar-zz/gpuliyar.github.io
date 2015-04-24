begin
  delete from si_activities t
   where exists (select *
            from si_activities t1
           where t.activity_name = t1.activity_name
           and t1.activity_name in 
           (
           'MS RGA Alert Subscriptions','MS RGA Create Channels'
           )
             and t.rowid > t1.rowid);
   commit;          
exception
  when others then
    dbms_output.put_line('error encountered');
end;
