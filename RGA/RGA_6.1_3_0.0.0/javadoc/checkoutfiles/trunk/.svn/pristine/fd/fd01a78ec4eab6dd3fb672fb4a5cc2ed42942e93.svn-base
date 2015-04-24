 begin
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
  
    INSERT INTO MS_APPS_PD_FLOW_UIXML_MIGBKP
      (FLOW_CODE, FLOW_UI_XMl, ENTRY_DATE)
      (select FLOW_CODE, FLOW_UI_xml, SYSDATE
         from MS_APPS_PD_FLOW_UI_XML
        where Flow_code = 'MS_RGA_MY_SUBSCRIPTION');
    COMMIT;
  
    DELETE FROM MS_APPS_PD_FLOW_UI_XML
     Where flow_code = 'MS_RGA_MY_SUBSCRIPTION';
    COMMIT;
    Insert into ms_apps_pd_flow_ui_xml
      (FLOW_CODE,
       FLOW_VERSION,
       FLOW_NAME,
       FLOW_UI_XML,
       ENTERPRISE,
       APPLICATION,
       CREATED_BY,
       CREATED_ON,
       UPDATED_BY,
       UPDATED_ON,
       FLOW_TYPE)
    Values
      ('MS_RGA_MY_SUBSCRIPTION',
       '1',
       'MS_RGA_MY_SUBSCRIPTION',
       empty_clob(),
       'MetricStream',
       'RGA',
       '',
       '31-OCT-12',
       '',
       '28-NOV-12',
       '2')
    RETURNING FLOW_UI_XML INTO l_clob;
    l_data_1 := '<q63xml version="1.0" encoding="UTF-8"q63>
<mxGraphModel><root><mxCell id="0" /><mxCell id="1" parent="0" /><canvasProperties type="canvas" id="1351689535"><CODE>MS_RGA_MY_SUBSCRIPTION</CODE><NAME>MS_RGA_MY_SUBSCRIPTION</NAME><TYPE>2</TYPE><ENTERPRISE>MetricStream</ENTERPRISE><APPLICATION>RGA</APPLICATION><DESCRIPTION /><CREATED_BY>SYSTEMI</CREATED_BY><CREATED_ON>10/31/2012 06:27:32</CREATED_ON><UPDATED_BY>SYSTEMI</UPDATED_BY><UPDATED_ON>11/27/2012 23:30:08</UPDATED_ON><VERSION>1</VERSION><ENABLED>Y</ENABLED><mxCell parent="1" visible="0"><mxGeometry as="geometry" /></mxCell></canvasProperties><startNode label="Start" type="start" id="2703379068"><mxCell style="image;image=/images/icons/control-power.png" parent="1" vertex="1"><mxGeometry x="20" y="20" width="22" height="22" as="geometry" /></mxCell></startNode><processNode label="CREATE_EDIT" type="ProcessStage" clonedcell="false" id="4055068601"><CODE>CREATE_EDIT</CODE><NAME>CREATE_EDIT</NAME><DESCRIPTION><l_cst<P>CREATE_EDIT</P>l_ced></DESCRIPTION><TYPE>0</TYPE><APPROVAL_TYPE>1</APPROVAL_TYPE><OBJECT>MS_RGA_MY_SUBSCRIPTION</OBJECT><VIEW>MS_RGA_MY_SUBSCRIPTION_V</VIEW><DISPLAY_VIEW>MS_RGA_MY_SUBSCRIPTION_DV</DISPLAY_VIEW><ENABLED>Y</ENABLED><mxCell style="innerProcessStage;" parent="1" vertex="1"><mxGeometry x="60" y="100" width="105" height="55" as="geometry" /></mxCell></processNode><portNode label="Rules" type="StageProps" id="5406758134"><mxCell style="port;image=/images/icons/traffic-cone.png;" parent="4055068601" vertex="1" connectable="0"><mxGeometry x="0.75" y="1" width="16" height="16" relative="1" as="geometry"><mxPoint x="-6" y="-8" as="offset" /></mxGeometry></mxCell></portNode><portNode label="Parameters" type="StageProps" id="6758447667"><mxCell style="port;image=/images/icons/bookmark--pencil.png;" parent="4055068601" vertex="1" connectable="0"><mxGeometry x="0.25" y="1" width="16" height="16" relative="1" as="geometry"><mxPoint x="-6" y="-4" as="offset" /></mxGeometry></mxCell></portNode><portNode label="Assignees" type="StageProps" id="8110137200"><mxCell style="port;image=/images/icons/users.png;" parent="4055068601" vertex="1" connectable="0"><mxGeometry x="0.75" width="16" height="16" relative="1" as="geometry"><mxPoint x="-8" y="-8" as="offset" /></mxGeometry></mxCell></portNode><rule cellType="Rules" label="CHANNEL_NAME_NOT_NULL" id="24330411596"><CODE>CHANNEL_NAME_NOT_NULL</CODE><TYPE>1</TYPE><OPERAND1>CHANNEL_NAME</OPERAND1><OPERATOR>12</OPERATOR><OPERAND2 /><mxCell parent="4055068601"><mxGeometry as="geometry" /></mxCell></rule><rule cellType="Rules" label="CUSTOM_FIELD_5_IS_1" id="36504882431"><CODE>CUSTOM_FIELD_5_IS_1</CODE><TYPE>1</TYPE><OPERAND1>CUSTOM_FIELD1</OPERAND1><OPERATOR>1</OPERATOR><OPERAND2>1</OPERAND2><mxCell parent="4055068601"><mxGeometry as="geometry" /></mxCell></rule><mxCell id="9461826733" style="Transition" parent="1" source="2703379068" target="4055068601" edge="1"><mxGeometry relative="1" as="geometry" /></mxCell><processNode label="END_FLOW" type="ProcessStage" clonedcell="false" id="10813516266"><CODE>END_FLOW</CODE><NAME>END_FLOW</NAME><DESCRIPTION><l_cst<P>END_FLOW</P>l_ced></DESCRIPTION><TYPE>0</TYPE><APPROVAL_TYPE>1</APPROVAL_TYPE><OBJECT>MS_RGA_MY_SUBSCRIPTION</OBJECT><VIEW>MS_RGA_MY_SUBSCRIPTION_V</VIEW><DISPLAY_VIEW>MS_RGA_MY_SUBSCRIPTION_DV</DISPLAY_VIEW><ENABLED>Y</ENABLED><mxCell style="innerProcessStage;" parent="1" vertex="1"><mxGeometry x="230" y="170" width="105" height="55" as="geometry" /></mxCell></processNode><portNode label="Rules" type="StageProps" id="12165205799"><mxCell style="port;image=/images/icons/traffic-cone.png;" parent="10813516266" vertex="1" connectable="0"><mxGeometry x="0.75" y="1" width="16" height="16" relative="1" as="geometry"><mxPoint x="-6" y="-8" as="offset" /></mxGeometry></mxCell></portNode><portNode label="Parameters" type="StageProps" id="13516895332"><mxCell style="port;image=/images/icons/bookmark--pencil.png;" parent="10813516266" vertex="1" connectable="0"><mxGeometry x="0.25" y="1" width="16" height="16" relative="1" as="geometry"><mxPoint x="-6" y="-4" as="offset" /></mxGeometry></mxCell></portNode><portNode label="Assignees" type="StageProps" id="14868584865"><mxCell style="port;image=/images/icons/users.png;" parent="10813516266" vertex="1" connectable="0"><mxGeometry x="0.75" width="16" height="16" relative="1" as="geometry"><mxPoint x="-8" y="-8" as="offset" /></mxGeometry></mxCell></portNode><rule cellType="Rules" label="END_FLOW_TO_MODIFY_FLOW_RULE" id="35151790550"><CODE>END_FLOW_TO_MODIFY_FLOW_RULE</CODE><TYPE>1</TYPE><OPERAND1>CUSTOM_FIELD1</OPERAND1><OPERATOR>1</OPERATOR><OPERAND2>2</OPERAND2><mxCell parent="10813516266"><mxGeometry as="geometry" /></mxCell></rule><TransitionNode label="CREATE_EDIT_TO_PUBLISH" type="Transition" id="16220274398"><CODE>CREATE_EDIT_TO_PUBLISH</CODE><DESCRIPTION /><FROMSTAGE>CREATE_EDIT</FROMSTAGE><TOSTAGE>END_FLOW</TOSTAGE><HOOK>MS_RGA_HELPER.Update_Out_Data</HOOK><POSTHOOK /><ETL>MS_RGA_MY_SUBSCRIPTION_P.CALL_ETL</ETL><ENABLED>Y</ENABLED><mxCell style="Transition" parent="1" source="4055068601" target="10813516266" edge="1"><mxGeometry width="16.65" height="16.65" relative="1" as="geometry" /></mxCell></TransitionNode><portNode label="Rule Groups" type="EdgeProps" id="17571963931"><mxCell style="port;image=/images/icons/ruler-crop.png;" parent="16220274398" vertex="1" connectable="0" visible="0"><mxGeometry x="0.65" y="0.65" width="16" height="16" relative="1" as="geometry" /></mxCell></portNode><portNode label="Emails" type="EdgeProps" id="18923653464"><mxCell style="port;image=/images/icons/mail.png;" parent="16220274398" vertex="1" connectable="0" visible="0"><mxGeometry x="-0.65" y="-0.65" width="16" height="16" relative="1" as="geometry" /></mxCell></portNode><portNode label="Assignments" type="EdgeProps" id="20275342997"><mxCell style="port;image=/images/icons/briefcase.gif;" parent="16220274398" vertex="1" connectable="0" visible="0"><mxGeometry x="0.35" y="0.35" width="16" height="16" relative="1" as="geometry" /></mxCell></portNode><rulegroup cellType="Rule Groups" label="SUBSCRIPTION" id="25682101129"><CODE>SUBSCRIPTION</CODE><ENABLED>Y</ENABLED><RULES>24330411596|Y, 36504882431|Y</RULES><RULESINFO><ruleInfo><ruleCode>CHANNEL_NAME_NOT_NULL</ruleCode><ruleEnable>Y</ruleEnable></ruleInfo><ruleInfo><ruleCode>CUSTOM_FIELD_5_IS_1</ruleCode><ruleEnable>Y</ruleEnable></ruleInfo></RULESINFO><mxCell parent="16220274398" visible="0"><mxGeometry as="geometry" /></mxCell></rulegroup><assignment cellType="Assignments" label="CREATE_EDIT_TO_ELD_FLOW_ASSGN" id="47332428959"><CODE>CREATE_EDIT_TO_ELD_FLOW_ASSGN</CODE><TO>SYSTEM_ADMIN</TO><ASSIGNMENTTEXT>Published</ASSIGNMENTTEXT><PRIORITY>2</PRIORITY><RULEGROUP /><DUEDATESOURCETYPE /><DUEDATESOURCE /><ENABLED>Y</ENABLED><mxCell parent="16220274398" visible="0"><mxGeometry as="geometry" /></mxCell></assignment><endNode label="End" type="end" id="21627032530"><mxCell style="image;image=/images/icons/json.png" parent="1" vertex="1"><mxGeometry x="380" y="280" width="22" height="22" as="geometry" /></mxCell></endNode><mxCell id="22978722063" style="Transition" parent="1" source="10813516266" target="21627032530" edge="1"><mxGeometry relative="1" as="geometry" /></mxCell><processNode label="MODIFY_FLOW" type="ProcessStage" clonedcell="false" id="29739423026"><CODE>MODIFY_FLOW</CODE><NAME>MODIFY_FLOW</NAME><DESCRIPTION /><TYPE>0</TYPE><APPROVAL_TYPE>1</APPROVAL_TYPE><OBJECT>MS_RGA_MY_SUBSCRIPTION</OBJECT><VIEW>MS_RGA_MY_SUBSCRIPTION_V</VIEW><DISPLAY_VIEW>MS_RGA_MY_SUBSCRIPTION_DV</DISPLAY_VIEW><ENABLED>Y</ENABLED><mxCell style="innerProcessStage;" parent="1" vertex="1"><mxGeometry x="630" y="40" width="105" height="55" as="geometry" /></mxCell></processNode><portNode label="Rules" type="StageProps" id="31092514907"><mxCell style="port;image=/images/icons/traffic-cone.png;" parent="29739423026" vertex="1" connectable="0" visible="0"><mxGeometry x="0.75" y="1" width="16" height="16" relative="1" as="geometry"><mxPoint x="-6" y="-8" as="offset" /></mxGeometry></mxCell></portNode><portNode label="Parameters" type="StageProps" id="32445606788"><mxCell style="port;image=/images/icons/bookmark--pencil.png;" parent="29739423026" vertex="1" connectable="0" visible="0"><mxGeometry x="0.25" y="1" width="16" height="16" relative="1" as="geometry"><mxPoint x="-6" y="-4" as="offset" /></mxGeometry></mxCell></portNode><portNode label="Assignees" type="StageProps" id="33798698669"><mxCell style="port;image=/images/icons/users.png;" parent="29739423026" vertex="1" connectable="0" visible="0"><mxGeometry x="0.75" width="16" height="16" relative="1" as="geometry"><mxPoint x="-8" y="-8" as="offset" /></mxGeometry></mxCell></portNode><TransitionNode label="END_FLOW_TO_MODIFY_FLOW" type="Transition" id="37857974312"><CODE>END_FLOW_TO_MODIFY_FLOW</CODE><DESCRIPTION /><FROMSTAGE>END_FLOW</FROMSTAGE><TOSTAGE>MODIFY_FLOW</TOSTAGE><HOOK /><POSTHOOK /><ETL>MS_RGA_MY_SUBSCRIPTION_P.CALL_ETL</ETL><ENABLED>Y</ENABLED><mxCell style="Transition" parent="1" source="10813516266" target="29739423026" edge="1"><mxGeometry width="16.65" height="16.65" relative="1" as="geometry" /></mxCell></TransitionNode><portNode label="Rule Groups" type="EdgeProps" id="39211066193"><mxCell style="port;image=/images/icons/ruler-crop.png;" parent="37857974312" vertex="1" connectable="0" visible="0"><mxGeometry x="0.65" y="0.65" width="16" height="16" relative="1" as="geometry" /></mxCell></portNode><portNode label="Emails" type="EdgeProps" id="40564158074"><mxCell style="port;image=/images/icons/mail.png;" parent="37857974312" vertex="1" connectable="0" visible="0"><mxGeometry x="-0.65" y="-0.65" width="16" height="16" relative="1" as="geometry" /></mxCell></portNode><portNode label="Assignments" type="EdgeProps" id="41917249955"><mxCell style="port;image=/images/icons/briefcase.gif;" parent="37857974312" vertex="1" connectable="0" visible="0"><mxGeometry x="0.35" y="0.35" width="16" height="16" relative="1" as="geometry" /></mxCell></portNode><rulegroup cellType="Rule Groups" label="END_FLOW_TO_MODIFY_RULE" id="43270341836"><CODE>END_FLOW_TO_MODIFY_RULE</CODE><ENABLED>Y</ENABLED><RULES>35151790550|Y</RULES><RULESINFO><ruleInfo><ruleCode>END_FLOW_TO_MODIFY_FLOW_RULE</ruleCode><ruleEnable>Y</ruleEnable></ruleInfo></RULESINFO><mxCell parent="37857974312" visible="0"><mxGeometry as="geometry" /></mxCell></rulegroup><assignment cellType="Assignments" label="END_FLOW_MODIFY_FLOW" id="45978341832"><CODE>END_FLOW_MODIFY_FLOW</CODE><TO>SYSTEM_ADMIN</TO><ASSIGNMENTTEXT>Published</ASSIGNMENTTEXT><PRIORITY>2</PRIORITY><RULEGROUP /><DUEDATESOURCETYPE /><DUEDATESOURCE /><ENABLED>Y</ENABLED><mxCell parent="37857974312" visible="0"><mxGeometry as="geometry" /></mxCell></assignment><cassignee cellType="cAssignees" label="SYSTEM_ADMIN" id="44624341834"><CODE>SYSTEM_ADMIN</CODE><TYPE>1</TYPE><SOURCE>SYSTEMI</SOURCE><mxCell parent="1" visible="0"><mxGeometry as="geometry" /></mxCell></cassignee></root></mxGraphModel>';
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
