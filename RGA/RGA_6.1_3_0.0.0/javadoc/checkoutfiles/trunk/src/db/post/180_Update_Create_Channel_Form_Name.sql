  DECLARE
BEGIN
  UPDATE si_metrics_t
     SET form_name = 'Create Channel'
   WHERE metric_name = 'MS_RGA_CHANNEL_DTLS'
     AND metric_type = 10;

  COMMIT;
END;
