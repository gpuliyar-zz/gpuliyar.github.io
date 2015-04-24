Declare
  x_Metric_Id si_metrics.METRIC_ID%Type;
  x_Success   Boolean := True;
Begin
  Dbms_output.put_line('Post-Install script for creating index on the Si table related to MS RGA Channel Aggregator infolet.');
  Select Metric_Id
    Into x_Metric_Id
    From si_metrics
   Where metric_name = 'MS RGA Channel Aggregator';

  Dbms_output.put_line('Will create index on the Si_' || x_Metric_Id ||
                       '_t table.');

  Begin
    Execute Immediate ('Create Index MS_RGA_FEEDAGG_V_IDX_01 on SI_' ||
                      x_Metric_Id || '_T (SERVER, FEED_DATA_ID)');
  Exception
    When Others Then
      x_Success := False;
  End;
  Begin
    Execute Immediate ('Create Index MS_RGA_FEEDAGG_V_IDX_02 on SI_' ||
                      x_Metric_Id ||
                      '_T (PROCESSED_FLAG, METRIC_RUN_DATE)');
  Exception
    When Others Then
      x_Success := False;
  End;

  If x_Success Then
    Begin
      Execute Immediate ('Create Index SI_' || x_Metric_Id || '_N1 on SI_' ||
                        x_Metric_Id || '_T (INSTANCE_ID)');
     Exception
      When Others Then
        Null;
	End;
	Begin
      Execute Immediate ('Create Index SI_' || x_Metric_Id || '_N2 on SI_' ||
                        x_Metric_Id ||
                        '_T (PROCESS_INSTANCE_ID, INSTANCE_ID)');
	Exception
      When Others Then
        Null;
	End;
	Begin
      Execute Immediate ('Create Index SI_' || x_Metric_Id || '_N3 on SI_' ||
                        x_Metric_Id || '_T (METRIC_COMPLETED_DATE)');
	Exception
      When Others Then
        Null;
	End;
  End If;

  If x_Success Then
    Dbms_output.put_line('Successfully created index on Si_' ||
                         x_Metric_Id || '_t table.');
  Else
    Dbms_output.put_line('Failed to create index on Si_' || x_Metric_Id ||
                         '_t table. Kindly verify.');
  End If;

Exception
  When Others Then
    Null;
End;
 