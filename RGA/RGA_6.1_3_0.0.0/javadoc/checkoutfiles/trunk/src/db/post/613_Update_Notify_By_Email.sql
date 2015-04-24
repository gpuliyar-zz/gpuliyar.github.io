 DECLARE
  x_metric_id NUMBER;
BEGIN
  SELECT metric_id
    INTO x_metric_id
    FROM si_metrics_t
   WHERE metric_name = 'MS_RGA_MY_SUBSCRIPTION'
     AND metric_type = 10;

  UPDATE si_metric_columns
     SET user_column_name = 'Notify by Email'
   WHERE metric_id = x_metric_id
     AND result_column_name = 'NOTIFY_BY_EMAIL';

  COMMIT;

  UPDATE si_labels_t
     SET VALUE = 'Notify by Email'
   WHERE KEY = 'NOTIFY_BY_EMAIL'
     AND page_id = x_metric_id;

  COMMIT;
END;
