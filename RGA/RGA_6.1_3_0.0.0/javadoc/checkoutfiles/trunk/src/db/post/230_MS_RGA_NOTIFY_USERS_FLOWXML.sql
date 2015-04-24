DECLARE
  l_clob CLOB ;
  l_tab_exists NUMBER := 0;
  v_string  VARCHAR2(4000);
  LV_COUNT NUMBER :=0;
  LV_ENT_NAME si_ent.ENTERPRISE_NAME%TYPE;l_data_1 VARCHAR2(32000);l_data_2 VARCHAR2(32000);l_data_3 VARCHAR2(32000);l_data_4 VARCHAR2(32000);l_data_5 VARCHAR2(32000);l_data_6 VARCHAR2(32000);l_data_7 VARCHAR2(32000);l_data_8 VARCHAR2(32000);l_data_9 VARCHAR2(32000);l_data_10 VARCHAR2(32000);l_data_11 VARCHAR2(32000);l_data_12 VARCHAR2(32000);l_data_13 VARCHAR2(32000);l_data_14 VARCHAR2(32000);l_data_15 VARCHAR2(32000);l_data_16 VARCHAR2(32000);l_data_17 VARCHAR2(32000);l_data_18 VARCHAR2(32000);l_data_19 VARCHAR2(32000);l_data_20 VARCHAR2(32000);l_data_21 VARCHAR2(32000);l_data_22 VARCHAR2(32000);l_data_23 VARCHAR2(32000);l_data_24 VARCHAR2(32000);l_data_25 VARCHAR2(32000);
  BEGIN  
  --Taking Backup of the existing xml
     INSERT INTO MS_APPS_MDF_FLOW_XML_BKP (FLOW_CODE,FLOW_XMl,ENTRY_DATE) ( select FLOW_CODE ,FLOW_xml,SYSDATE from MS_APPS_MDF_FLOW_XML
     where Flow_code='MS_RGA_NOTIFY_USERS');
     COMMIT;
     --Delete the existing one
     DELETE FROM MS_APPS_MDF_FLOW_XML Where flow_code = 'MS_RGA_NOTIFY_USERS'; 
     COMMIT;
    BEGIN
		SELECT count(1) INTO lv_count FROM si_ent;
		    IF LV_COUNT = 1 THEN
			      SELECT ENTERPRISE_NAME into LV_ENT_NAME FROM si_ent;
			      DBMS_OUTPUT.PUT_LINE('Setting the enterprise name :'|| LV_ENT_NAME);
				 ELSE
			      LV_ENT_NAME := 'METRICSTREAM';
			      DBMS_OUTPUT.PUT_LINE('Setting the enterprise name to METRICSTREAM as there are more than one enterprise name, Hence change the value accordingly.');
		    END IF;
		    EXCEPTION WHEN OTHERS THEN
			      DBMS_OUTPUT.PUT_LINE('Error Message is '||sqlerrm);
    END;
  Insert into ms_apps_mdf_flow_xml (FLOW_CODE,
                                  FLOW_VERSION,
                                  FLOW_NAME,
                                  FLOW_XML,
                                  ENTERPRISE,
                                  APPLICATION,
                                  CREATED_BY,
                                  CREATED_ON,
                                  UPDATED_BY,
                                  UPDATED_ON,
                                  LAST_ID_JSON_STRING,
                                  FLOW_TYPE,
                                  BLUEPRINT_CODE,
                                  READY_FLAG ) Values ( 'MS_RGA_NOTIFY_USERS' , '1' , 'MS_RGA_NOTIFY_USERS' , empty_clob() , LV_ENT_NAME , 'RGA' , 'null' , '' , 'null' , '13-NOV-14' , '' , '2' , 'MS_RGA_NOTIFY_USERS' , 'Y' ) RETURNING FLOW_XML INTO l_clob;l_data_1 := '<q63xml version="1.0" encoding="UTF-8"q63>
<mdf:process xmlns:mdf="http://www.metricstream.com/appstudio/mdf" xmlns:html="http://www.w3.org/1999/xhtml"><mdf:code>MS_RGA_NOTIFY_USERS</mdf:code><mdf:name>MS_RGA_NOTIFY_USERS</mdf:name><mdf:type>2</mdf:type><mdf:description /><mdf:readyflag>Y</mdf:readyflag><mdf:enabled>Y</mdf:enabled><mdf:stages><mdf:stage id="stg_CREATE_EDIT" yaxis="75" xaxis="127"><mdf:code>CREATE_EDIT</mdf:code><mdf:name>CREATE_EDIT</mdf:name><mdf:description /><mdf:object>MS_RGA_NOTIFY_USERS</mdf:object><mdf:view>MS_RGA_NOTIFY_USERS_V</mdf:view><mdf:displayview>MS_RGA_NOTIFY_USERS_DV</mdf:displayview><mdf:type>4</mdf:type><mdf:approvaltype>1</mdf:approvaltype><mdf:enabled>Y</mdf:enabled><mdf:parameters /><mdf:rules><mdf:rule><mdf:code>OBJECT_ID_NOT_NULL</mdf:code><mdf:type>1</mdf:type><mdf:object1>OBJECT_ID</mdf:object1><mdf:object2>12</mdf:object2><mdf:object3 /><mdf:description /></mdf:rule></mdf:rules><mdf:assignees /><mdf:usedY2s>6</mdf:usedY2s><mdf:participant /></mdf:stage><mdf:stage id="stg_END_FLOW" yaxis="75" xaxis="127"><mdf:code>END_FLOW</mdf:code><mdf:name>END_FLOW</mdf:name><mdf:description /><mdf:object>MS_RGA_NOTIFY_USERS</mdf:object><mdf:view>MS_RGA_NOTIFY_USERS_V</mdf:view><mdf:displayview>MS_RGA_NOTIFY_USERS_DV</mdf:displayview><mdf:type>3</mdf:type><mdf:approvaltype>1</mdf:approvaltype><mdf:enabled>Y</mdf:enabled><mdf:parameters /><mdf:rules><mdf:rule><mdf:code>END_FLOW_MODIFY_RULE</mdf:code><mdf:type>1</mdf:type><mdf:object1>FEED_DATA_ID</mdf:object1><mdf:object2>12</mdf:object2><mdf:object3 /><mdf:description /></mdf:rule></mdf:rules><mdf:assignees /><mdf:usedY2s>6</mdf:usedY2s><mdf:participant /></mdf:stage><mdf:stage id="stg_MODIFY_NOTIFY_CHANNELS" yaxis="75" xaxis="127"><mdf:code>MODIFY_NOTIFY_CHANNELS</mdf:code><mdf:name>MODIFY_NOTIFY_CHANNELS</mdf:name><mdf:description /><mdf:object>MS_RGA_NOTIFY_USERS</mdf:object><mdf:view>MS_RGA_NOTIFY_USERS_V</mdf:view><mdf:displayview>MS_RGA_NOTIFY_USERS_DV</mdf:displayview><mdf:type>1</mdf:type><mdf:approvaltype>1</mdf:approvaltype><mdf:enabled>Y</mdf:enabled><mdf:parameters /><mdf:rules><mdf:rule><mdf:code>OBJECT_ID_NOT_NULL</mdf:code><mdf:type>1</mdf:type><mdf:object1>OBJECT_ID</mdf:object1><mdf:object2>12</mdf:object2><mdf:object3 /><mdf:description /></mdf:rule></mdf:rules><mdf:assignees /><mdf:usedY2s>6</mdf:usedY2s><mdf:participant /></mdf:stage></mdf:stages><mdf:transitions><mdf:transition><mdf:code>CREATE_EDIT_TO_PUBLISH</mdf:code><mdf:enabled>Y</mdf:enabled><mdf:hooks><mdf:hook><mdf:name>MS_RGA_HELPER.Update_Out_Data</mdf:name><mdf:enabled>Y</mdf:enabled></mdf:hook><mdf:posthook><mdf:name>MS_RGA_HELPER.POSTHOOK_PROC</mdf:name><mdf:enabled>Y</mdf:enabled></mdf:posthook></mdf:hooks><mdf:rulegroups /><mdf:emails /><mdf:assignments><mdf:assignment><mdf:code>PUBLISH</mdf:code><mdf:to>SYSTEM_ADMIN</mdf:to><mdf:duedate><mdf:sourcetype /><mdf:source /></mdf:duedate><mdf:assignmenttext locale="en_US"><l_cstPublishedl_ced></mdf:assignmenttext><mdf:enabled>Y</mdf:enabled></mdf:assignment></mdf:assignments><mdf:etl><mdf:name>MS_RGA_NOTIFY_USERS_P.CALL_ETL</mdf:name></mdf:etl><mdf:xslmapping>MS_RGA_NOTIFY_USERS_CREATE_EDIT_TO_PUBLISH_Mapping</mdf:xslmapping></mdf:transition><mdf:transition><mdf:code>END_FLOW_TO_MODIFY</mdf:code><mdf:enabled>Y</mdf:enabled><mdf:hooks><mdf:posthook /></mdf:hooks><mdf:rulegroups><mdf:rulegroup><mdf:code>END_FLOW_MODIFY_RULE_GROUP</mdf:code><mdf:enabled>Y</mdf:enabled><mdf:rules><mdf:rule><mdf:code>END_FLOW_MODIFY_RULE</mdf:code><mdf:enabled>Y</mdf:enabled></mdf:rule></mdf:rules></mdf:rulegroup></mdf:rulegroups><mdf:emails /><mdf:assignments><mdf:assignment><mdf:code>END_FLOW_PUBLISHED</mdf:code><mdf:to>SYSTEM_ADMIN</mdf:to><mdf:duedate><mdf:sourcetype /><mdf:source /></mdf:duedate><mdf:assignmenttext locale="en_US"><l_cstPublishedl_ced></mdf:assignmenttext><mdf:enabled>Y</mdf:enabled></mdf:assignment></mdf:assignments><mdf:etl><mdf:name>MS_RGA_NOTIFY_USERS_P.CALL_ETL</mdf:name></mdf:etl><mdf:xslmapping>MS_RGA_NOTIFY_USERS_END_FLOW_TO_MODIFY_Mapping</mdf:xslmapping></mdf:transition><mdf:transition><mdf:code>MODIFY_TO_MODIFY</mdf:code><mdf:enabled>Y</mdf:enabled><mdf:hooks><mdf:posthook><mdf:name>MS_RGA_HELPER.POSTHOOK_PROC</mdf:name><mdf:enabled>Y</mdf:enabled></mdf:posthook></mdf:hooks><mdf:rulegroups><mdf:rulegroup><mdf:code>OBJECT_ID_NOT_NULL</mdf:code><mdf:enabled>Y</mdf:enabled><mdf:rules><mdf:rule><mdf:code>OBJECT_ID_NOT_NULL</mdf:code><mdf:enabled>Y</mdf:enabled></mdf:rule></mdf:rules></mdf:rulegroup></mdf:rulegroups><mdf:emails /><mdf:assignments><mdf:assignment><mdf:code>MODIFY_TO_PUBLISHED</mdf:code><mdf:to>SYSTEM_ADMIN</mdf:to><mdf:duedate><mdf:sourcetype /><mdf:source /></mdf:duedate><mdf:assignmenttext locale="en_US"><l_cstpublishedl_ced></mdf:assignmenttext><mdf:enabled>Y</mdf:enabled></mdf:assignment></mdf:assignments><mdf:etl><mdf:name>MS_RGA_NOTIFY_USERS_P.CALL_ETL</mdf:name></mdf:etl><mdf:xslmapping>MS_RGA_NOTIFY_USERS_MODIFY_TO_MODIFY_Mapping</mdf:xslmapping></mdf:transition></mdf:transitions></mdf:process>';l_data_1:= replace(l_data_1,'l_cst','![CDATA[');
          l_data_1:= replace(l_data_1,'l_ced',']]');
          l_data_1:= replace(l_data_1,'q63',chr(63));
          DBMS_LOB.WRITE(L_CLOB, LENGTH(l_data_1),1, l_data_1);
          COMMIT;
  dbms_output.put_line('Successfully Migrated');
  EXCEPTION WHEN OTHERS THEN DBMS_OUTPUT.PUT_LINE('Error Message is '||sqlerrm);
END;