  declare

verror_code varchar2(4000);
verror_desc varchar2(4000);

begin

update si_activities sat
set sat.activity_name='MS RGA Create Channels',sat.activity_short_name='MS RGA Create Channels'
where sat.activity_name='MS GRC Create Channels';

update si_activities sat
set sat.activity_name='MS RGA Alert Subscriptions',sat.activity_short_name='MS RGA Alert Subscriptions'
where sat.activity_name='MS GRC Alert Subscriptions';

update si_activities sat
set sat.activity_name='MS RGA View Manage Alerts',sat.activity_short_name='MS RGA View Manage Alerts'
where sat.activity_name='MS GRC View Manage Alerts';

commit;

dbms_output.put_line('Success');


exception when others then

verror_code := SQLCODE;
verror_desc := SQLERRM;

dbms_output.put_line('Error Encountered');

end;



