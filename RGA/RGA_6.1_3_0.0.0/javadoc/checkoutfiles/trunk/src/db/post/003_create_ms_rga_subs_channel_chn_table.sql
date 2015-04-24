declare
  verror_code varchar2(4000) := 'NO ERROR';
  verror_desc varchar2(4000) := 'NO ERROR';
  vcount      number;
  --this script is created as there is a error in IUP import (error is coming in total_summary_report.html)
begin

  select count(1)
    into vcount
    from USER_TABLES UT
   where UT.TABLE_NAME = 'MS_RGA_SUBS_CHANNEL_CHN';

  if (vcount > 0) then
  
  begin
  execute immediate('drop table ms_rga_subs_temp');
  exception when others then
  dbms_output.put_line('error');
  end;
  
    execute immediate ('create table ms_rga_subs_temp as select * from ms_rga_subs_channel_chn');
    execute immediate ('drop table ms_rga_subs_channel_chn');
    execute immediate ('create table MS_RGA_SUBS_CHANNEL_CHN
(
  CHANNEL_ID          VARCHAR2(4000),
  CHANNEL_NAME        VARCHAR2(4000),
  FILTER_KEYWORDS     VARCHAR2(4000),
  NOTIFY_BY_EMAIL     VARCHAR2(4000),
  SUBSCRIPTION_STATUS VARCHAR2(4000),
  SUBSCRIPTION_ID     VARCHAR2(255),
  STATUS              VARCHAR2(4000),
  CUSTOM_FIELD6       VARCHAR2(4000),
  CUSTOM_FIELD7       NUMBER,
  CUSTOM_FIELD8       NUMBER,
  CUSTOM_FIELD9       DATE,
  CUSTOM_FIELD10      VARCHAR2(4000),
  CHN_CREATED_BY      VARCHAR2(4000),
  CHN_CREATED_ON      DATE,
  CHN_MODIFIED_ON     DATE,
  CHN_MODIFIED_BY     VARCHAR2(4000),
  OBJECT_ID           VARCHAR2(255),
  DD_STATUS_FLAG      VARCHAR2(1)
)');
    execute immediate ('create unique index MS_RGA_SUBS_CHANNEL_CHN_UK on MS_RGA_SUBS_CHANNEL_CHN (SUBSCRIPTION_ID, OBJECT_ID)');
    execute immediate ('insert into MS_RGA_SUBS_CHANNEL_CHN select * from ms_rga_subs_temp');
    commit;
  else
  execute immediate ('create table MS_RGA_SUBS_CHANNEL_CHN
(
  CHANNEL_ID          VARCHAR2(4000),
  CHANNEL_NAME        VARCHAR2(4000),
  FILTER_KEYWORDS     VARCHAR2(4000),
  NOTIFY_BY_EMAIL     VARCHAR2(4000),
  SUBSCRIPTION_STATUS VARCHAR2(4000),
  SUBSCRIPTION_ID     VARCHAR2(255),
  STATUS              VARCHAR2(4000),
  CUSTOM_FIELD6       VARCHAR2(4000),
  CUSTOM_FIELD7       NUMBER,
  CUSTOM_FIELD8       NUMBER,
  CUSTOM_FIELD9       DATE,
  CUSTOM_FIELD10      VARCHAR2(4000),
  CHN_CREATED_BY      VARCHAR2(4000),
  CHN_CREATED_ON      DATE,
  CHN_MODIFIED_ON     DATE,
  CHN_MODIFIED_BY     VARCHAR2(4000),
  OBJECT_ID           VARCHAR2(255),
  DD_STATUS_FLAG      VARCHAR2(1)
)');
  end if;
exception
  when others then
    verror_code := SQLCODE;
    verror_desc := SQLERRM;
    insert into post_install_scripts
      (error_code, error_desc, script_name, creation_date)
    values
      (verror_code,
       verror_desc,
       'CREATE_TABLE_ms_rga_subs_channel_chn',
       sysdate);
    commit;
end;
