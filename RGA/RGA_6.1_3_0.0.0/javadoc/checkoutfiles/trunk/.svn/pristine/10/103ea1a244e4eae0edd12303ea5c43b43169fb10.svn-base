BEGIN
  DECLARE
  l_clob CLOB ;
  l_tab_exists NUMBER := 0;
  v_sting      VARCHAR2(4000);
  l_col_exists NUMBER := 0;
  BEGIN  
  select count(*)
    into l_tab_exists
    from user_tab_cols
   where table_name = 'MS_APPS_PD_FLOW_UIXML_MIGBKP';
  IF l_tab_exists = 0 THEN
    v_sting := 'CREATE TABLE MS_APPS_PD_FLOW_UIXML_MIGBKP AS SELECT FLOW_CODE, FLOW_UI_XMl FROM MS_APPS_PD_FLOW_UI_XML WHERE 1 = 2';
    BEGIN
     EXECUTE IMMEDIATE v_sting;
    END;
    v_sting := 'ALTER TABLE MS_APPS_PD_FLOW_UIXML_MIGBKP ADD (ENTRY_DATE DATE)';
    BEGIN
     EXECUTE IMMEDIATE v_sting;
    END;
  ELSE  
    SELECT count(*)
      into l_col_exists FROM USER_TAB_COLS
     WHERE TABLE_NAME='MS_APPS_PD_FLOW_UIXML_MIGBKP'
       AND COLUMN_NAME='ENTRY_DATE';
      IF l_col_exists = 0 THEN
        v_sting := 'ALTER TABLE MS_APPS_PD_FLOW_UIXML_MIGBKP ADD (ENTRY_DATE DATE)';
        BEGIN
          EXECUTE IMMEDIATE v_sting;
        END;
      END IF;   
  END IF;
EXCEPTION WHEN OTHERS THEN DBMS_OUTPUT.PUT_LINE('Error Message is '||sqlerrm);
END;  

DECLARE
l_clob CLOB ;
l_data_1 VARCHAR2(32000);l_data_2 VARCHAR2(32000);l_data_3 VARCHAR2(32000);l_data_4 VARCHAR2(32000);l_data_5 VARCHAR2(32000);l_data_6 VARCHAR2(32000);l_data_7 VARCHAR2(32000);l_data_8 VARCHAR2(32000);l_data_9 VARCHAR2(32000);l_data_10 VARCHAR2(32000);l_data_11 VARCHAR2(32000);l_data_12 VARCHAR2(32000);l_data_13 VARCHAR2(32000);l_data_14 VARCHAR2(32000);l_data_15 VARCHAR2(32000);l_data_16 VARCHAR2(32000);l_data_17 VARCHAR2(32000);l_data_18 VARCHAR2(32000);l_data_19 VARCHAR2(32000);l_data_20 VARCHAR2(32000);l_data_21 VARCHAR2(32000);l_data_22 VARCHAR2(32000);l_data_23 VARCHAR2(32000);l_data_24 VARCHAR2(32000);l_data_25 VARCHAR2(32000);BEGIN  
     --Taking Backup of the existing xml
     INSERT INTO MS_APPS_PD_FLOW_UIXML_MIGBKP (FLOW_CODE,FLOW_UI_XMl,ENTRY_DATE) ( select FLOW_CODE ,FLOW_UI_xml,SYSDATE from MS_APPS_PD_FLOW_UI_XML
     where Flow_code='MS_RGA_EMAIL_CONTENT');
     COMMIT;
     --Delete the existing one
     DELETE FROM MS_APPS_PD_FLOW_UI_XML Where flow_code = 'MS_RGA_EMAIL_CONTENT'; 
     COMMIT;
  Insert into ms_apps_pd_flow_ui_xml (FLOW_CODE,
                                  FLOW_VERSION,
                                  FLOW_NAME,
                                  FLOW_UI_XML,
                                  ENTERPRISE,
                                  APPLICATION,
                                  CREATED_BY,
                                  CREATED_ON,
                                  UPDATED_BY,
                                  UPDATED_ON,
                                  FLOW_TYPE
                                   ) Values ( 'MS_RGA_EMAIL_CONTENT' , '1' , 'MS_RGA_EMAIL_CONTENT' , empty_clob() , 'MetricStream' , 'RGA' , '' , '05-OCT-12' , '' , '05-NOV-12' , '2' ) RETURNING FLOW_UI_XML INTO l_clob;l_data_1 := '<q63xml version="1.0" encoding="UTF-8"q63>
<mxGraphModel><root><mxCell id="0" /><mxCell id="1" parent="0" /><canvasProperties type="canvas" id="1349435866"><CODE>MS_RGA_EMAIL_CONTENT</CODE><NAME>MS_RGA_EMAIL_CONTENT</NAME><TYPE>2</TYPE><ENTERPRISE>MetricStream</ENTERPRISE><APPLICATION>RGA</APPLICATION><DESCRIPTION><l_cst<P>RGA&nbsp;EMAIL CONTENT&nbsp;FORM SAMPLE FLOW</P>l_ced></DESCRIPTION><CREATED_BY>SYSTEMI</CREATED_BY><CREATED_ON>10/05/2012 04:23:50</CREATED_ON><UPDATED_BY>SYSTEMI</UPDATED_BY><UPDATED_ON>11/05/2012 03:39:21</UPDATED_ON><VERSION>1</VERSION><ENABLED>Y</ENABLED><mxCell parent="1" visible="0"><mxGeometry as="geometry" /></mxCell></canvasProperties><startNode label="Start" type="start" id="2698871730"><mxCell style="image;image=/images/icons/control-power.png" parent="1" vertex="1"><mxGeometry x="20" y="20" width="22" height="22" as="geometry" /></mxCell></startNode><processNode label="CREATE_EDIT" type="ProcessStage" clonedcell="false" id="4048307594"><CODE>CREATE_EDIT</CODE><NAME>CREATE_EDIT</NAME><DESCRIPTION><l_cst<P>To start the flow</P>l_ced></DESCRIPTION><TYPE>0</TYPE><APPROVAL_TYPE>1</APPROVAL_TYPE><OBJECT>MS_RGA_EMAIL_CONTENT</OBJECT><VIEW>MS_RGA_EMAIL_CONTENT_V</VIEW><DISPLAY_VIEW>MS_RGA_EMAIL_CONTENT_DV</DISPLAY_VIEW><ENABLED>Y</ENABLED><mxCell style="innerProcessStage;" parent="1" vertex="1"><mxGeometry x="110" y="30" width="105" height="55" as="geometry" /></mxCell></processNode><portNode label="Rules" type="StageProps" id="5397743458"><mxCell style="port;image=/images/icons/traffic-cone.png;" parent="4048307594" vertex="1" connectable="0" visible="0"><mxGeometry x="0.75" y="1" width="16" height="16" relative="1" as="geometry"><mxPoint x="-6" y="-8" as="offset" /></mxGeometry></mxCell></portNode><portNode label="Parameters" type="StageProps" id="6747179322"><mxCell style="port;image=/images/icons/bookmark--pencil.png;" parent="4048307594" vertex="1" connectable="0" visible="0"><mxGeometry x="0.25" y="1" width="16" height="16" relative="1" as="geometry"><mxPoint x="-6" y="-4" as="offset" /></mxGeometry></mxCell></portNode><portNode label="Assignees" type="StageProps" id="8096615186"><mxCell style="port;image=/images/icons/users.png;" parent="4048307594" vertex="1" connectable="0" visible="0"><mxGeometry x="0.75" width="16" height="16" relative="1" as="geometry"><mxPoint x="-8" y="-8" as="offset" /></mxGeometry></mxCell></portNode><rule cellType="Rules" label="EMAIL_CONTENT_NOT_NULL" id="21590973826"><CODE>EMAIL_CONTENT_NOT_NULL</CODE><TYPE>1</TYPE><OPERAND1>EMAIL_SUBJECT</OPERAND1><OPERATOR>12</OPERATOR><OPERAND2 /><mxCell parent="4048307594" visible="0"><mxGeometry as="geometry" /></mxCell></rule><mxCell id="9446051050" parent="1" source="2698871730" target="4048307594" edge="1"><mxGeometry relative="1" as="geometry" /></mxCell><processNode label="END_FLOW" type="ProcessStage" clonedcell="false" id="10795486914"><CODE>END_FLOW</CODE><NAME>END_FLOW</NAME><DESCRIPTION><l_cst<P>To end the flow</P>l_ced></DESCRIPTION><TYPE>0</TYPE><APPROVAL_TYPE>1</APPROVAL_TYPE><OBJECT>MS_RGA_EMAIL_CONTENT</OBJECT><VIEW>MS_RGA_EMAIL_CONTENT_V</VIEW><DISPLAY_VIEW>MS_RGA_EMAIL_CONTENT_DV</DISPLAY_VIEW><ENABLED>Y</ENABLED><mxCell style="innerProcessStage;" parent="1" vertex="1"><mxGeometry x="330" y="140" width="105" height="55" as="geometry" /></mxCell></processNode><portNode label="Rules" type="StageProps" id="12144922778"><mxCell style="port;image=/images/icons/traffic-cone.png;" parent="10795486914" vertex="1" connectable="0" visible="0"><mxGeometry x="0.75" y="1" width="16" height="16" relative="1" as="geometry"><mxPoint x="-6" y="-8" as="offset" /></mxGeometry></mxCell></portNode><portNode label="Parameters" type="StageProps" id="13494358642"><mxCell style="port;image=/images/icons/bookmark--pencil.png;" parent="10795486914" vertex="1" connectable="0" visible="0"><mxGeometry x="0.25" y="1" width="16" height="16" relative="1" as="geometry"><mxPoint x="-6" y="-4" as="offset" /></mxGeometry></mxCell></portNode><portNode label="Assignees" type="StageProps" id="14843794506"><mxCell style="port;image=/images/icons/users.png;" parent="10795486914" vertex="1" connectable="0" visible="0"><mxGeometry x="0.75" width="16" height="16" relative="1" as="geometry"><mxPoint x="-8" y="-8" as="offset" /></mxGeometry></mxCell></portNode><assignee cellType="Assignees" label="PUBLISH_EMAIL_CONTENT" id="26991396426"><CODE>PUBLISH_EMAIL_CONTENT</CODE><TYPE>1</TYPE><SOURCE>DD_CURRENT_USER_NAME</SOURCE><mxCell parent="10795486914" visible="0"><mxGeometry as="geometry" /></mxCell></assignee><TransitionNode label="CREATE_EDIT_TO_PUBLISH" type="Transition" id="16193230370"><CODE>CREATE_EDIT_TO_PUBLISH</CODE><DESCRIPTION /><FROMSTAGE>CREATE_EDIT</FROMSTAGE><TOSTAGE>END_FLOW</TOSTAGE><HOOK>MS_RGA_HELPER.Update_Out_Data</HOOK><POSTHOOK /><ETL>MS_RGA_EMAIL_CONTENT_P.CALL_ETL</ETL><ENABLED>Y</ENABLED><mxCell style="Transition" parent="1" source="4048307594" target="10795486914" edge="1"><mxGeometry width="16.65" height="16.65" relative="1" as="geometry" /></mxCell></TransitionNode><portNode label="Rule Groups" type="EdgeProps" id="17542666234"><mxCell style="port;image=/images/icons/ruler-crop.png;" parent="16193230370" vertex="1" connectable="0"><mxGeometry x="0.65" y="0.65" width="16" height="16" relative="1" as="geometry" /></mxCell></portNode><portNode label="Emails" type="EdgeProps" id="18892102098"><mxCell style="port;image=/images/icons/mail.png;" parent="16193230370" vertex="1" connectable="0"><mxGeometry x="-0.65" y="-0.65" width="16" height="16" relative="1" as="geometry" /></mxCell></portNode><portNode label="Assignments" type="EdgeProps" id="20241537962"><mxCell style="port;image=/images/icons/briefcase.gif;" parent="16193230370" vertex="1" connectable="0"><mxGeometry x="0.35" y="0.35" width="16" height="16" relative="1" as="geometry" /></mxCell></portNode><rulegroup cellType="Rule Groups" label="EMAIL_CONTENT_NOT_NULL" id="22940409690"><CODE>EMAIL_CONTENT_NOT_NULL</CODE><ENABLED>Y</ENABLED><RULES>21590973826|Y</RULES><RULESINFO><ruleInfo><ruleCode>EMAIL_CONTENT_NOT_NULL</ruleCode><ruleEnable>Y</ruleEnable></ruleInfo></RULESINFO><mxCell parent="16193230370"><mxGeometry as="geometry" /></mxCell></rulegroup><assignment cellType="Assignments" label="PUBLISH_EMAIL_CONTENT" id="28343511434"><CODE>PUBLISH_EMAIL_CONTENT</CODE><TO>PUBLISH_EMAIL_CONTENT</TO><ASSIGNMENTTEXT>PUBLISH_EMAIL_CONTENT</ASSIGNMENTTEXT><PRIORITY>2</PRIORITY><RULEGROUP /><DUEDATESOURCETYPE /><DUEDATESOURCE /><ENABLED>Y</ENABLED><mxCell parent="16193230370" visible="0"><mxGeometry as="geometry" /></mxCell></assignment><endNode label="End" type="end" id="24289845554"><mxCell style="image;image=/images/icons/json.png" parent="1" vertex="1"><mxGeometry x="530" y="270" width="22" height="22" as="geometry" /></mxCell></endNode><mxCell id="25639281418" style="Transition" parent="1" source="10795486914" target="24289845554" edge="1"><mxGeometry relative="1" as="geometry" /></mxCell></root></mxGraphModel>';l_data_1:= replace(l_data_1,'l_cst','![CDATA[');
          l_data_1:= replace(l_data_1,'l_ced',']]');
          l_data_1:= replace(l_data_1,'q63',chr(63));
          DBMS_LOB.WRITE(L_CLOB, LENGTH(l_data_1),1, l_data_1);
          COMMIT;
  dbms_output.put_line('Successfully Migrated');
  EXCEPTION WHEN OTHERS THEN DBMS_OUTPUT.PUT_LINE('Error Message is '||sqlerrm);
  END;
  END;