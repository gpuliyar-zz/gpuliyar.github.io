  begin

  update ms_apps_mdf_flow_xml m
     set m.enterprise = (select enterprise_name from si_ent where rownum = 1);

  commit;

exception

  when others then
  
    dbms_output.put_line('Error While Updating the MS_APPS_MDF_FLOW_XML');
  
end;
