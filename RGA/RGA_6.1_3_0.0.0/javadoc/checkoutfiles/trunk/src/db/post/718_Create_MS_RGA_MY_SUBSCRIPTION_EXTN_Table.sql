 begin

begin
execute immediate('drop table MS_RGA_MY_SUBSCRIPTION_EXTN');
exception when others then
dbms_output.put_line('Error encountered while dropping the table MS_RGA_MY_SUBSCRIPTION_EXTN');
end;

begin
execute immediate('create table MS_RGA_MY_SUBSCRIPTION_EXTN as select * from MS_RGA_SUBS_CHANNEL_CHN');
exception when others then
dbms_output.put_line('Error encountered while creating the table MS_RGA_MY_SUBSCRIPTION_EXTN');
end;

exception when others then
dbms_output.put_line('Error');
end;
