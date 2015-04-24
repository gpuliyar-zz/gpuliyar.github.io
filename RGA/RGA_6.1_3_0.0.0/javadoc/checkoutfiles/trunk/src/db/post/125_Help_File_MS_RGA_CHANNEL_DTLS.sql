 DECLARE

BEGIN
  UPDATE si_metrics_t
     SET help_file = 'MS_RGA/MS_GRCI_UserGuide/Governance_Risk_Compliance_Intelligence/MS_RGA_CHANNEL_DTLS.htm'
   WHERE metric_name = 'MS_RGA_CHANNEL_DTLS'; 

  COMMIT;
END;
