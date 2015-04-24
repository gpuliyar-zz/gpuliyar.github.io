 begin

begin

dbms_output.put_line('Script to remove the foreign keys associated with ms_rga_subscription_response');

execute immediate('alter table ms_rga_subscription_response drop constraint MS_RGA_SUBSCRIPTION_RESPON_R01');

exception when others then

dbms_output.put_line('Error Encountered while dropping constraint MS_RGA_SUBSCRIPTION_RESPON_R01');

end;

begin

execute immediate('alter table ms_rga_subscription_response drop constraint MS_RGA_SUBSCRIPTION_RESPON_R02');

exception when others then

dbms_output.put_line('Error Encountered while dropping constraint MS_RGA_SUBSCRIPTION_RESPON_R02');

end;

exception when others then

dbms_output.put_line('Error Encountered');

end;
