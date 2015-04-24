 declare 

begin

update ms_rga_subs_channel_chn t
set t.status=1;

update ms_rga_my_subscription_v t1
set t1.STATUS=1;

commit;

exception when others then

dbms_output.put_line('Error Encountered');

end;