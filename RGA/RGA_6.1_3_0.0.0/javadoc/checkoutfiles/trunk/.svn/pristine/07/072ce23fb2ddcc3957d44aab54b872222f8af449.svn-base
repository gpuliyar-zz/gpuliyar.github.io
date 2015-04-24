 begin

  update ms_rga_channel_dtls_cpu mrcdu
     set mrcdu.notify_users_unsuccessful = (select sut.user_name
                                              from si_users_t sut
                                             where sut.first_name || ' ' ||
                                                   sut.last_name =
                                                   mrcdu.notify_users_unsuccessful);

  update ms_rga_channel_dtls_cps mrcdu
     set mrcdu.notify_users_successful = (select sut.user_name
                                            from si_users_t sut
                                           where sut.first_name || ' ' ||
                                                 sut.last_name =
                                                 mrcdu.notify_users_successful);

  update ms_rga_channel_dtls_v mrcdv
     set mrcdv.NOTIFY_USERS_SUCCESSFUL = (select listagg(sut.user_name, ',') within
                                           group(
                                           order by sut.user_name desc)
                                            from ms_rga_channel_dtls_cps msr,
                                                 si_users                sut
                                           where msr.notify_users_successful =
                                                 sut.first_name || ' ' ||
                                                 sut.last_name
                                             and msr.object_id =
                                                 mrcdv.OBJECT_ID);

  update ms_rga_channel_dtls_v mrcdv
     set mrcdv.NOTIFY_USERS_UNSUCCESSFUL = (select listagg(sut.user_name, ',') within
                                             group(
                                             order by sut.user_name desc)
                                              from ms_rga_channel_dtls_cpu msr,
                                                   si_users                sut
                                             where msr.notify_users_unsuccessful =
                                                   sut.first_name || ' ' ||
                                                   sut.last_name
                                               and msr.object_id =
                                                   mrcdv.OBJECT_ID);

  commit;

exception
  when others then
  
    commit;
  
end;


