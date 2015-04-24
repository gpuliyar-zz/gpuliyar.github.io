BEGIN
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
      into l_col_exists FROM USER_TAB_COLS
     WHERE TABLE_NAME='MS_APPS_MDF_FLOW_XML_BKP'
       AND COLUMN_NAME='ENTRY_DATE';
      IF l_col_exists = 0 THEN
        v_sting := 'ALTER TABLE MS_APPS_MDF_FLOW_XML_BKP ADD (ENTRY_DATE DATE)';
        BEGIN
          EXECUTE IMMEDIATE v_sting;
        END;
      END IF;  
  END IF;
EXCEPTIOn WHEN OTHERS THEN DBMS_OUTPUT.PUT_LINE('MS_APPS_MDF_FLOW_XML_BKP TABLE CREATION FAILS'); NULL; END;

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
      into l_col_exists FROM USER_TAB_COLS
     WHERE TABLE_NAME='MS_APPS_MDF_BLUEPRINT_BKP'
       AND COLUMN_NAME='ENTRY_DATE';
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
l_clob CLOB ;
l_data_1 VARCHAR2(32000);l_data_2 VARCHAR2(32000);l_data_3 VARCHAR2(32000);l_data_4 VARCHAR2(32000);l_data_5 VARCHAR2(32000);l_data_6 VARCHAR2(32000);l_data_7 VARCHAR2(32000);l_data_8 VARCHAR2(32000);l_data_9 VARCHAR2(32000);l_data_10 VARCHAR2(32000);l_data_11 VARCHAR2(32000);l_data_12 VARCHAR2(32000);l_data_13 VARCHAR2(32000);l_data_14 VARCHAR2(32000);l_data_15 VARCHAR2(32000);l_data_16 VARCHAR2(32000);l_data_17 VARCHAR2(32000);l_data_18 VARCHAR2(32000);l_data_19 VARCHAR2(32000);l_data_20 VARCHAR2(32000);l_data_21 VARCHAR2(32000);l_data_22 VARCHAR2(32000);l_data_23 VARCHAR2(32000);l_data_24 VARCHAR2(32000);l_data_25 VARCHAR2(32000);BEGIN  
     --Taking Backup of the existing xml
     INSERT INTO MS_APPS_MDF_FLOW_XML_BKP (FLOW_CODE,FLOW_XMl,ENTRY_DATE) ( select FLOW_CODE ,FLOW_xml,SYSDATE from MS_APPS_MDF_FLOW_XML
     where blueprint_code='MS_RGA_NOTIFY_USERS');
     COMMIT;
     --Delete the existing one
     DELETE FROM MS_APPS_MDF_FLOW_XML Where blueprint_code = 'MS_RGA_NOTIFY_USERS'; 
     COMMIT;
     --Deleting the child records coz of foreign key constraints
     INSERT INTO MS_APPS_MDF_BLUEPRINT_BKP(BLUEPRINT_CODE,BLUEPRINT_XMl,ENTRY_DATE) ( select BLUEPRINT_CODE ,blueprint_xml,SYSDATE from ms_apps_mdf_blueprint
     where blueprint_code='MS_RGA_NOTIFY_USERS');
     COMMIT;
     --Delete the existing one
     DELETE FROM MS_APPS_MDF_BLUEPRINT Where blueprint_code = 'MS_RGA_NOTIFY_USERS'; 
     COMMIT;
  Insert into ms_apps_mdf_blueprint ( BLUEPRINT_CODE,
                                                            BLUEPRINT_VERSION,
                                                            BLUEPRINT_NAME,
                                                            BLUEPRINT_XML,
                                                            CREATED_BY,
                                                            CREATED_ON,
                                                            UPDATED_BY,
                                                            UPDATED_ON,
                                                            READY_FLAG ) Values ( 'MS_RGA_NOTIFY_USERS' , '1' , 'MS_RGA_NOTIFY_USERS' , empty_clob() , '' , '' , '' , '04-DEC-14' , 'N' ) RETURNING BLUEPRINT_XML INTO l_clob;l_data_1 := '<q63xml version="1.0" encoding="UTF-8"q63>
<mdf:flow xmlns:mdf="http://www.metricstream.com/appstudio/mdf" xmlns:html="http://www.w3.org/1999/xhtml"><mdf:blueprintcode>MS_RGA_NOTIFY_USERS</mdf:blueprintcode><mdf:blueprintname>MS_RGA_NOTIFY_USERS</mdf:blueprintname><mdf:blueprintdesc /><mdf:readyflag>N</mdf:readyflag><mdf:code /><mdf:enabled>Y</mdf:enabled><mdf:parameters /><mdf:rules /><mdf:assignees><mdf:assignee><mdf:code>SYSTEM_ADMIN</mdf:code><mdf:sourcetype>1</mdf:sourcetype><mdf:source>SYSTEMI</mdf:source><mdf:description /></mdf:assignee></mdf:assignees><mdf:emailtemplates /><mdf:stages><mdf:stage><mdf:code>CREATE_EDIT</mdf:code><mdf:name>CREATE_EDIT</mdf:name><mdf:description /><mdf:type>4</mdf:type><mdf:approvaltype>1</mdf:approvaltype><mdf:usedY2s>6</mdf:usedY2s><mdf:participant /><mdf:enabled>Y</mdf:enabled><mdf:parameters /><mdf:rules><mdf:rule><mdf:code>OBJECT_ID_NOT_NULL</mdf:code><mdf:type /><mdf:object1 /><mdf:description /></mdf:rule></mdf:rules><mdf:assignees /></mdf:stage><mdf:stage><mdf:code>END_FLOW</mdf:code><mdf:name>END_FLOW</mdf:name><mdf:description /><mdf:type>3</mdf:type><mdf:approvaltype>1</mdf:approvaltype><mdf:usedY2s>6</mdf:usedY2s><mdf:participant /><mdf:enabled>Y</mdf:enabled><mdf:parameters /><mdf:rules><mdf:rule><mdf:code>END_FLOW_MODIFY_RULE</mdf:code><mdf:type /><mdf:object1 /><mdf:description /></mdf:rule></mdf:rules><mdf:assignees /></mdf:stage><mdf:stage><mdf:code>MODIFY_NOTIFY_CHANNELS</mdf:code><mdf:name>MODIFY_NOTIFY_CHANNELS</mdf:name><mdf:description /><mdf:type>1</mdf:type><mdf:approvaltype>1</mdf:approvaltype><mdf:usedY2s>6</mdf:usedY2s><mdf:participant /><mdf:enabled>Y</mdf:enabled><mdf:parameters /><mdf:rules><mdf:rule><mdf:code>OBJECT_ID_NOT_NULL</mdf:code><mdf:type /><mdf:object1 /><mdf:description /></mdf:rule></mdf:rules><mdf:assignees /></mdf:stage></mdf:stages><mdf:transitions><mdf:transition><mdf:code>CREATE_EDIT_TO_PUBLISH</mdf:code><mdf:description /><mdf:from>CREATE_EDIT</mdf:from><mdf:to>END_FLOW</mdf:to><mdf:enabled>Y</mdf:enabled><mdf:assignments /><mdf:emails /><mdf:rulegroups /></mdf:transition><mdf:transition><mdf:code>END_FLOW_TO_MODIFY</mdf:code><mdf:description /><mdf:from>END_FLOW</mdf:from><mdf:to>MODIFY_NOTIFY_CHANNELS</mdf:to><mdf:enabled>Y</mdf:enabled><mdf:assignments><mdf:assignment><mdf:code>END_FLOW_PUBLISHED</mdf:code><mdf:to>SYSTEM_ADMIN</mdf:to><mdf:assignmenttext /><mdf:priority>2</mdf:priority><mdf:rulegroup /><mdf:duedate><mdf:sourcetype /><mdf:source /></mdf:duedate><mdf:enabled>Y</mdf:enabled><mdf:description /></mdf:assignment></mdf:assignments><mdf:emails /><mdf:rulegroups><mdf:rulegroup><mdf:code>END_FLOW_MODIFY_RULE_GROUP</mdf:code><mdf:enabled>Y</mdf:enabled><mdf:description /><mdf:rules><mdf:rule><mdf:code>END_FLOW_MODIFY_RULE</mdf:code><mdf:enabled>Y</mdf:enabled></mdf:rule></mdf:rules></mdf:rulegroup></mdf:rulegroups></mdf:transition><mdf:transition><mdf:code>MODIFY_TO_MODIFY</mdf:code><mdf:description /><mdf:from>MODIFY_NOTIFY_CHANNELS</mdf:from><mdf:to>MODIFY_NOTIFY_CHANNELS</mdf:to><mdf:enabled>Y</mdf:enabled><mdf:assignments /><mdf:emails /><mdf:rulegroups><mdf:rulegroup><mdf:code>OBJECT_ID_NOT_NULL</mdf:code><mdf:enabled>Y</mdf:enabled><mdf:description /><mdf:rules><mdf:rule><mdf:code>OBJECT_ID_NOT_NULL</mdf:code><mdf:enabled>Y</mdf:enabled></mdf:rule></mdf:rules></mdf:rulegroup></mdf:rulegroups></mdf:transition></mdf:transitions></mdf:flow>';l_data_1:= replace(l_data_1,'l_cst','![CDATA[');
          l_data_1:= replace(l_data_1,'l_ced',']]');
          l_data_1:= replace(l_data_1,'q63',chr(63));
          DBMS_LOB.WRITE(L_CLOB, LENGTH(l_data_1),1, l_data_1);
          COMMIT;
  dbms_output.put_line('Successfully Migrated');
  EXCEPTION WHEN OTHERS THEN DBMS_OUTPUT.PUT_LINE('Error Message is '||sqlerrm);
  END;
  END;