DECLARE
  x_rec_cnt NUMBER := 0;
BEGIN
  -- Script for updating Source_Metric_Id in Si_Parameters to -4.
  -- Purpose - To use profile parameters.
  FOR si_metrics_rec IN (SELECT *
                           FROM si_metrics
                          WHERE metric_name IN ('MS_RGA_VIEW_ALERT_SUMMARY_RPT'
                                
                                )) LOOP
    BEGIN
      UPDATE si_parameters z
         SET z.source_metric_id = -4
       WHERE z.parameter_name = 'USER_NAME'
         AND z.metric_id = si_metrics_rec.metric_id;
    
      COMMIT;
      x_rec_cnt := x_rec_cnt + 1;
    EXCEPTION
      WHEN OTHERS THEN
        NULL;
    END;
  END LOOP;

  DBMS_OUTPUT.put_line('Successfully updated ' || x_rec_cnt ||
                       ' records in Si_Parameters.');
EXCEPTION
  WHEN OTHERS THEN
    NULL;
END;
 