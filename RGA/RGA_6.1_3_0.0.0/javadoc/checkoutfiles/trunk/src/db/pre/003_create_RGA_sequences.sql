declare
  vstring     varchar2(4000);
  verror_code varchar2(4000) := 'NO ERROR';
  verror_desc varchar2(4000) := 'NO ERROR';
begin
  for i in (select 'create sequence ' || us.sequence_name ||
                   ' minvalue 1 maxvalue 9999999999999999999999999999 start with 100040 increment by 1 nocache' query1
              from user_sequences us
             where us.sequence_name <> 'MS_RGA_WRP_XML'
               and us.sequence_name like '%RGA%'
                or us.sequence_name = 'SEQ_1') LOOP
    BEGIN
      execute immediate (i.query1);
    exception
      when others then
        verror_code := SQLCODE;
        verror_desc := SQLERRM;
        insert into post_install_scripts
          (error_code, error_desc, script_name, creation_date)
        values
          (verror_code, verror_desc, 'CREATE_SEQUENCE', sysdate);
        commit;
    end;
  END LOOP;
exception
  when others then
    verror_code := SQLCODE;
    verror_desc := SQLERRM;
    insert into post_install_scripts
      (error_code, error_desc, script_name, creation_date)
    values
      (verror_code, verror_desc, 'CREATE_SEQUENCE', sysdate);
    commit;
end;
