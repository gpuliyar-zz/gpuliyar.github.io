declare
  vcount number;
begin
  select count(1)
    into vcount
    from user_tables ut
   where ut.table_name = 'POST_INSTALL_SCRIPTS';

  if (vcount = 0) then
  
    execute immediate ('create table POST_INSTALL_SCRIPTS
(
  ERROR_CODE    VARCHAR2(4000),
  ERROR_DESC    VARCHAR2(4000),
  SCRIPT_NAME   VARCHAR2(4000),
  CREATION_DATE DATE
)');
  end if;
end;
