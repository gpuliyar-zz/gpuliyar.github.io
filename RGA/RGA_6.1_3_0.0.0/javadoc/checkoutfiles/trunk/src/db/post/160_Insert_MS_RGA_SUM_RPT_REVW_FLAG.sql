 DECLARE

BEGIN
  Delete from ms_rga_sum_rpt_revw_flag;
  Insert into MS_RGA_SUM_RPT_REVW_FLAG
    (FOLLOW_UP_ID, FOLLOW_UP_NAME)
  Values
    ('1', 'Marked as Reviewed');
  Insert into MS_RGA_SUM_RPT_REVW_FLAG
    (FOLLOW_UP_ID, FOLLOW_UP_NAME)
  Values
    ('0', 'Not Marked as Reviewed');
  COMMIT;

END;
