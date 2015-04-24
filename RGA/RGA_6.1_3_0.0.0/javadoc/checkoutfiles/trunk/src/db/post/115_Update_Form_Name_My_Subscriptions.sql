 DECLARE
  x_metric_id VARCHAR2(1000);
BEGIN
  SELECT metric_id
    INTO x_metric_id
    FROM si_metrics_t
   WHERE metric_name = 'MS_RGA_MY_SUBSCRIPTION'
     and metric_type = 10;

  UPDATE si_metrics_t
     SET forM_name = 'My Subscriptions'
   WHERE metric_id = x_metric_id;

  COMMIT;
EXCEPTION
  WHEN OTHERS THEN
    DBMS_OUTPUT.put_line('FORM NAME NOT UPDATED IN SI_METRICS_T TABLE FOR MS RGA SUBSCRIBE CHANNEL FORM.');
END;
