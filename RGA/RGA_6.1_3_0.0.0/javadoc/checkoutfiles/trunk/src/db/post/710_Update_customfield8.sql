 begin

  dbms_output.put_line('This script is provided to update the custom_field8 value to 2 for all the inactive channels');

  update ms_rga_subs_channel_chn ms
     set ms.custom_field8 = 2
   where ms.channel_name in
         (select m1.channel_name
            from ms_rga_subs_channel_chn m1, ms_rga_channel_dtls m2
           where m1.channel_name = m2.object_id
             and m2.channel_status = 2);
             
  commit;           

exception
  when others then
  
    dbms_output.put_line('Error encountered');
  
end;
