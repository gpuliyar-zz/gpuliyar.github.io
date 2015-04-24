Begin
Begin
EXECUTE IMMEDIATE 'DELETE from MS_APPS_MDF_FLOW_XML WHERE FLOW_CODE='''||'MS_RGA_MY_SUBSCRIPTION'||'''';
Exception
  When Others Then
  Dbms_Output.Put_Line('Encountered exception while deleting the workflow :MS_RGA_MY_SUBSCRIPTION.'); 
End;


Begin
EXECUTE IMMEDIATE 'DELETE from MS_APPS_PD_FLOW_UI_XML WHERE FLOW_CODE='''||'MS_RGA_MY_SUBSCRIPTION'||'''';
Exception
  When Others Then
  Dbms_Output.Put_Line('Encountered exception while deleting the flow_ui :MS_RGA_MY_SUBSCRIPTION.');
End;


Begin
EXECUTE IMMEDIATE 'DELETE from MS_APPS_MDF_BLUEPRINT WHERE BLUEPRINT_CODE='''||'MS_RGA_MY_SUBSCRIPTION'||'''';
Exception
  When Others Then
  Dbms_Output.Put_Line('Encountered exception while deleting the Blueprint :MS_RGA_MY_SUBSCRIPTION.');
End;
commit;
Exception
When Others Then
  Dbms_Output.Put_Line('Encountered exception while deleting the blueprint,workflow,flowui MS_RGA_MY_SUBSCRIPTION.');
End;
