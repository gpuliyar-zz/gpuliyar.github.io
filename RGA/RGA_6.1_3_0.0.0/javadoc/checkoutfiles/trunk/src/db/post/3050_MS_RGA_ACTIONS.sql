Begin
delete from MS_RGA_ACTIONS;
Insert into MS_RGA_ACTIONS (Action_code,Action_name,Locale_id) values ('SND_APP','Send For Approval',1009);
Insert into MS_RGA_ACTIONS (Action_code,Action_name,Locale_id) values ('APP','Approve',1009);
Insert into MS_RGA_ACTIONS (Action_code,Action_name,Locale_id) values ('REQ_CLR','Request Clarifications',1009);
Insert into MS_RGA_ACTIONS (Action_code,Action_name,Locale_id) values ('SUB_CLR','Submit Clarifications',1009);
Insert into MS_RGA_ACTIONS (Action_code,Action_name,Locale_id) values ('CNCL','Cancel',1009);
commit;
end;