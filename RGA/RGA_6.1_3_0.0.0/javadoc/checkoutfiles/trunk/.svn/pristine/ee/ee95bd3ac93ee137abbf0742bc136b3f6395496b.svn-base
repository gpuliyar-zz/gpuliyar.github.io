 declare

  vstatus number;

begin

  for i in (select * from ms_rga_subs_channel_chn) loop
  
    select channel_status
      into vstatus
      from ms_rga_channel_dtls t
     where t.object_id = i.channel_name;
  
      
    update ms_rga_subs_channel_chn
       set custom_field10 = (select channel_name
                               from ms_rga_channel_dtls
                              where object_id = i.channel_name)
     where channel_name = i.channel_name;
  
    update ms_rga_my_subscription_v
       set custom_field10 = (select channel_name
                               from ms_rga_channel_dtls
                              where object_id = i.channel_name)
     where channel_name = i.channel_name;
  
  end loop
  
  commit;

exception
  when others then
    dbms_output.put_line('ERROR ENCOUNTERED');
end;
