 declare

  v_view user_views.view_name%type;

begin

  select uv.view_name
    into v_view
    from user_views uv
   where uv.view_name = 'MS_RGA_FEED_ATTACHMENT_V';

  if (v_view is not null) then
  
    execute immediate ('drop view ' || v_view);
  
  end if;
  dbms_output.put_line('dropped');

exception
  when others then
  
    dbms_output.put_line('error encountered');
  
end;
