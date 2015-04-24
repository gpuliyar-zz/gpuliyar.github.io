  declare 

vmetric_id si_metrics_t.metric_id%type;

begin
 
select metric_id
  into vmetric_id
  from si_metrics_t
 where metric_name = 'MS_RGA_VIEW_ALERT_SUMMARY_RPT';
 
update si_report_filters srf
set srf.custom_filter_seq=1
where srf.filter_name='Channel Name'
and srf.metric_id=vmetric_id;

update si_report_filters srf
set srf.custom_filter_seq=2
where srf.filter_name='Alert Title'
and srf.metric_id=vmetric_id;

update si_report_filters srf
set srf.custom_filter_seq=3
where srf.filter_name='Alerts Received Until'
and srf.metric_id=vmetric_id;

update si_report_filters srf
set srf.custom_filter_seq=4
where srf.filter_name='Alerts Received From'
and srf.metric_id=vmetric_id;
 
update si_report_filters srf
set srf.custom_filter_seq=5
where srf.filter_name='Alerts Followed Up Status'
and srf.metric_id=vmetric_id; 

update si_report_filters srf
set srf.custom_filter_seq=6
where srf.filter_name='Alerts Reviewed Status'
and srf.metric_id=vmetric_id; 

commit;

exception when others then

dbms_output.put_line('error encountered');
end;
