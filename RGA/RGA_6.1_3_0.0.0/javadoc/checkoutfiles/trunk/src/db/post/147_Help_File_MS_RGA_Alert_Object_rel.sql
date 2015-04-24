 DECLARE

BEGIN
  UPDATE si_metrics_t
     SET help_file = 'MS_RGA/MS_GRCI_UserGuide/Governance_Risk_Compliance_Intelligence/MS_RGA_ALERT_OBJECT_RELATIONS.htm'
   WHERE metric_name = 'MS_RGA_ALERT_OBJECT_RELATIONS'
     and metric_type = 10;

  COMMIT;
END;
