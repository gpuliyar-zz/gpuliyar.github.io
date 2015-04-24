 begin

delete from MS_RGA_SUM_RPT_FOOLW_FLAG;

insert into MS_RGA_SUM_RPT_FOOLW_FLAG values(1,'Marked for Follow-Up');
insert into MS_RGA_SUM_RPT_FOOLW_FLAG values(0,'Not Marked for Follow-Up');

commit;

dbms_output.put_line('success');

exception when others then

dbms_output.put_line('failure');

end;



