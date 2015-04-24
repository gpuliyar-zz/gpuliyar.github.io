 begin
  DECLARE
    l_clob       CLOB;
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
        into l_col_exists
        FROM USER_TAB_COLS
       WHERE TABLE_NAME = 'MS_APPS_PD_FLOW_UIXML_MIGBKP'
         AND COLUMN_NAME = 'ENTRY_DATE';
      IF l_col_exists = 0 THEN
        v_sting := 'ALTER TABLE MS_APPS_PD_FLOW_UIXML_MIGBKP ADD (ENTRY_DATE DATE)';
        BEGIN
          EXECUTE IMMEDIATE v_sting;
        END;
      END IF;
    END IF;
  EXCEPTION
    WHEN OTHERS THEN
      DBMS_OUTPUT.PUT_LINE('Error Message is ' || sqlerrm);
  END;

end;
