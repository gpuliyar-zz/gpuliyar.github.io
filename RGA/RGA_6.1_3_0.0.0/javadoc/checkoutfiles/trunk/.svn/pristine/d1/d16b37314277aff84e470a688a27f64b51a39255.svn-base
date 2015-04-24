 DECLARE
   x_metric_id   NUMBER;
BEGIN
   SELECT metric_id
     INTO x_metric_id
     FROM si_metrics_t
    WHERE metric_name = 'MS RGA NOTIFY USERS' and metric_type=10;

   DELETE FROM si_metric_columns
         WHERE metric_id = x_metric_id;

   COMMIT;

   DELETE FROM si_labels_t
         WHERE page_id = x_metric_id;
   
   DELETE FROM SI_METRICS_T where metric_id=x_metric_id;      
         

   COMMIT;
EXCEPTION
   WHEN OTHERS
   THEN
      DBMS_OUTPUT.put_line ('Data deleted sucessfully');
END;
