 begin

  DECLARE
    l_tab_exists NUMBER := 0;
    v_sting      VARCHAR2(4000);
    l_col_exists NUMBER := 0;
  BEGIN
    select count(*)
      into l_tab_exists
      from user_tab_cols
     where table_name = 'MS_APPS_MDF_FLOW_XML_BKP';
    IF l_tab_exists = 0 THEN
      v_sting := 'CREATE TABLE MS_APPS_MDF_FLOW_XML_BKP AS SELECT FLOW_CODE, FLOW_XMl FROM MS_APPS_MDF_FLOW_XML WHERE 1 = 2';
      BEGIN
        EXECUTE IMMEDIATE v_sting;
      END;
      v_sting := 'ALTER TABLE MS_APPS_MDF_FLOW_XML_BKP ADD (ENTRY_DATE DATE)';
      BEGIN
        EXECUTE IMMEDIATE v_sting;
      END;
    ELSE
      SELECT count(*)
        into l_col_exists
        FROM USER_TAB_COLS
       WHERE TABLE_NAME = 'MS_APPS_MDF_FLOW_XML_BKP'
         AND COLUMN_NAME = 'ENTRY_DATE';
      IF l_col_exists = 0 THEN
        v_sting := 'ALTER TABLE MS_APPS_MDF_FLOW_XML_BKP ADD (ENTRY_DATE DATE)';
        BEGIN
          EXECUTE IMMEDIATE v_sting;
        END;
      END IF;
    END IF;
  EXCEPTIOn
    WHEN OTHERS THEN
      DBMS_OUTPUT.PUT_LINE('MS_APPS_MDF_FLOW_XML_BKP TABLE CREATION FAILS');
      NULL;
  END;

  DECLARE
    l_tab_exists NUMBER := 0;
    v_sting      VARCHAR2(4000);
    l_col_exists NUMBER := 0;
  BEGIN
    select count(*)
      into l_tab_exists
      from user_tab_cols
     where table_name = 'MS_APPS_MDF_BLUEPRINT_BKP';
    IF l_tab_exists = 0 THEN
      v_sting := 'CREATE TABLE  MS_APPS_MDF_BLUEPRINT_BKP AS SELECT BLUEPRINT_CODE,BLUEPRINT_XMl FROM MS_APPS_MDF_BLUEPRINT WHERE 1=2';
      BEGIN
        EXECUTE IMMEDIATE v_sting;
      END;
      v_sting := 'ALTER TABLE MS_APPS_MDF_BLUEPRINT_BKP ADD (ENTRY_DATE DATE)';
      BEGIN
        EXECUTE IMMEDIATE v_sting;
      END;
    ELSE
      SELECT count(*)
        into l_col_exists
        FROM USER_TAB_COLS
       WHERE TABLE_NAME = 'MS_APPS_MDF_BLUEPRINT_BKP'
         AND COLUMN_NAME = 'ENTRY_DATE';
      IF l_col_exists = 0 THEN
        v_sting := 'ALTER TABLE MS_APPS_MDF_BLUEPRINT_BKP ADD (ENTRY_DATE DATE)';
        BEGIN
          EXECUTE IMMEDIATE v_sting;
        END;
      END IF;
    END IF;
  EXCEPTIOn
    WHEN OTHERS THEN
      DBMS_OUTPUT.PUT_LINE('MS_APPS_MDF_BLUEPRINT_BKP TABLE CREATION FAILS');
      NULL;
  END;

  DECLARE
    l_clob    CLOB;
    l_data_1  VARCHAR2(32000);
    l_data_2  VARCHAR2(32000);
    l_data_3  VARCHAR2(32000);
    l_data_4  VARCHAR2(32000);
    l_data_5  VARCHAR2(32000);
    l_data_6  VARCHAR2(32000);
    l_data_7  VARCHAR2(32000);
    l_data_8  VARCHAR2(32000);
    l_data_9  VARCHAR2(32000);
    l_data_10 VARCHAR2(32000);
    l_data_11 VARCHAR2(32000);
    l_data_12 VARCHAR2(32000);
    l_data_13 VARCHAR2(32000);
    l_data_14 VARCHAR2(32000);
    l_data_15 VARCHAR2(32000);
    l_data_16 VARCHAR2(32000);
    l_data_17 VARCHAR2(32000);
    l_data_18 VARCHAR2(32000);
    l_data_19 VARCHAR2(32000);
    l_data_20 VARCHAR2(32000);
    l_data_21 VARCHAR2(32000);
    l_data_22 VARCHAR2(32000);
    l_data_23 VARCHAR2(32000);
    l_data_24 VARCHAR2(32000);
    l_data_25 VARCHAR2(32000);
  BEGIN
  
    INSERT INTO MS_APPS_MDF_FLOW_XML_BKP
      (FLOW_CODE, FLOW_XMl, ENTRY_DATE)
      (select FLOW_CODE, FLOW_xml, SYSDATE
         from MS_APPS_MDF_FLOW_XML
        where blueprint_code = 'MS_RGA_LOG_ISSUES');
    COMMIT;
  
    DELETE FROM MS_APPS_MDF_FLOW_XML
     Where blueprint_code = 'MS_RGA_LOG_ISSUES';
    COMMIT;
  
    INSERT INTO MS_APPS_MDF_BLUEPRINT_BKP
      (BLUEPRINT_CODE, BLUEPRINT_XMl, ENTRY_DATE)
      (select BLUEPRINT_CODE, blueprint_xml, SYSDATE
         from ms_apps_mdf_blueprint
        where blueprint_code = 'MS_RGA_LOG_ISSUES');
    COMMIT;
  
    DELETE FROM MS_APPS_MDF_BLUEPRINT
     Where blueprint_code = 'MS_RGA_LOG_ISSUES';
    COMMIT;
    Insert into ms_apps_mdf_blueprint
      (BLUEPRINT_CODE,
       BLUEPRINT_VERSION,
       BLUEPRINT_NAME,
       BLUEPRINT_XML,
       CREATED_BY,
       CREATED_ON,
       UPDATED_BY,
       UPDATED_ON,
       READY_FLAG)
    Values
      ('MS_RGA_LOG_ISSUES',
       '1',
       'MS_RGA_LOG_ISSUES',
       empty_clob(),
       '',
       '',
       '',
       '',
       'Y')
    RETURNING BLUEPRINT_XML INTO l_clob;
    l_data_1 := '<q63xml version="1.0" encoding="UTF-8"q63>
<mdf:flow xmlns:mdf="http://www.metricstream.com/appstudio/mdf" xmlns:html="http://www.w3.org/1999/xhtml">
<mdf:code>MS_RGA_LOG_ISSUES</mdf:code>
<mdf:name>MS_RGA_LOG_ISSUES</mdf:name>
<mdf:type>2</mdf:type>
<mdf:description>RGA LOG ISSUE FORM SAMPLE FLOW</mdf:description>
<mdf:enabled>Y</mdf:enabled>
<mdf:parameters />
<mdf:rules />
<mdf:assignees />
<mdf:emailtemplates />
<mdf:stages>
<mdf:stage>
<mdf:code>CREATE_EDIT</mdf:code>
<mdf:name>CREATE_EDIT</mdf:name>
<mdf:description>To start the flow</mdf:description>
<mdf:type>4</mdf:type>
<mdf:approvaltype>1</mdf:approvaltype>
<mdf:enabled>Y</mdf:enabled>
<mdf:usedY2s>6</mdf:usedY2s>
<mdf:participant />
<mdf:parameters /><mdf:rules>
<mdf:rule><mdf:code>LOG_ISSUE_NOT_NULL</mdf:code>
</mdf:rule></mdf:rules>
<mdf:assignees />
</mdf:stage>
<mdf:stage>
<mdf:code>END_FLOW</mdf:code>
<mdf:name>END_FLOW</mdf:name>
<mdf:description>To end the flow</mdf:description>
<mdf:type>3</mdf:type>
<mdf:approvaltype>1</mdf:approvaltype>
<mdf:enabled>Y</mdf:enabled>
<mdf:usedY2s>6</mdf:usedY2s>
<mdf:participant />
<mdf:parameters />
<mdf:rules />
<mdf:assignees />
</mdf:stage>
</mdf:stages>
<mdf:transitions>
<mdf:transition>
<mdf:code>CREATE_EDIT_TO_PUBLISH</mdf:code>
<mdf:description />
<mdf:from>CREATE_EDIT</mdf:from>
<mdf:to>END_FLOW</mdf:to>
<mdf:enabled>Y</mdf:enabled>
<mdf:rulegroups><mdf:rulegroup>
<mdf:code>LOG_ISSUE_NOT_NULL</mdf:code>
<mdf:enabled>Y</mdf:enabled>
<mdf:rules><mdf:rule>
<mdf:code>LOG_ISSUE_NOT_NULL</mdf:code>
<mdf:enabled>Y</mdf:enabled>
</mdf:rule></mdf:rules>
</mdf:rulegroup></mdf:rulegroups>
<mdf:emails />
<mdf:assignments />
</mdf:transition></mdf:transitions>
</mdf:flow>';
    l_data_1 := replace(l_data_1, 'l_cst', '![CDATA[');
    l_data_1 := replace(l_data_1, 'l_ced', ']]');
    l_data_1 := replace(l_data_1, 'q63', chr(63));
    DBMS_LOB.WRITE(L_CLOB, LENGTH(l_data_1), 1, l_data_1);
    COMMIT;
    dbms_output.put_line('Successfully Migrated');
  EXCEPTION
    WHEN OTHERS THEN
      DBMS_OUTPUT.PUT_LINE('Error Message is ' || sqlerrm);
  END;
end;
