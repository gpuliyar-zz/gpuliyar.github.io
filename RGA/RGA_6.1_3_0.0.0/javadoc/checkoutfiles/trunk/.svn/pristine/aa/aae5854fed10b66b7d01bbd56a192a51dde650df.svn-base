Declare
  x_rec_cnt Number := 0;
Begin
  -- Script for updating Source_Metric_Id in Si_Parameters to -4.
  -- Purpose - To use profile parameters.
  For Si_Metrics_Rec In (Select *
                           From Si_Metrics
                          Where Metric_Name In
                                ('MS RGA Restrict Subscriptions',
                                 'MS_RGA_CHANNEL_NAME_ALERT_MLOV_RPT')) Loop
    Begin
      Update Si_Parameters z
         Set z.Source_Metric_Id = -4
       Where z.parameter_name = 'USER_NAME'
         And z.Metric_Id = Si_Metrics_Rec.Metric_Id;
      Commit;
      x_rec_cnt := x_rec_cnt + 1;
    Exception
      When Others Then
        Null;
    End;
  End Loop;
  Dbms_Output.Put_Line('Successfully updated ' || x_rec_cnt ||
                       ' records in Si_Parameters.');
Exception
  When Others Then
    Null;
End;
 