 begin

update ms_rga_subscription_response s1
set user_id=ms_rga_utilities.get_user_name(user_id)
where not exists
(
select * from si_users_t s where s1.user_id=s.user_name
);


commit;

exception when others then

dbms_output.put_line('Error');

end;