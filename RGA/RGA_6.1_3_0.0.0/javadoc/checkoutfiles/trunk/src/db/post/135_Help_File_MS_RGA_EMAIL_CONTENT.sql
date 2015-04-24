 DECLARE

BEGIN
  UPDATE si_metrics_t
     SET help_file = 'MS_RGA/MS_GRCI_UserGuide/Governance_Risk_Compliance_Intelligence/GRCI_Email_Notifications.htm'
   WHERE metric_name = 'MS_RGA_EMAIL_CONTENT' 
     and metric_type = 10;

  COMMIT;
END;
