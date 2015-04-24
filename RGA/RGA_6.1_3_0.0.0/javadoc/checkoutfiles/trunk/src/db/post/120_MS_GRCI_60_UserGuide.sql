declare
    x_cnt           NUMBER;
BEGIN 
    SELECT COUNT(1) INTO x_cnt FROM si_user_guide_t WHERE application_name = 'GRCI';  
    
    IF x_cnt = 0 THEN
        insert into si_user_guide_t values ('GRCI','MS_GRCI_UserGuide.pdf','GRCI User Guide','Y');
    ELSE
        UPDATE si_user_guide_t
        SET user_guide_file_name = 'MS_GRCI_UserGuide.pdf'
        WHERE application_name='GRCI';    
    END IF;     
    COMMIT;
EXCEPTION
  WHEN others THEN
    NULL;
END;
