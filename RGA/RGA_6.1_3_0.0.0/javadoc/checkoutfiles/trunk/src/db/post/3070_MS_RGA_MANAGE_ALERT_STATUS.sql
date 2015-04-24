Begin
delete from MS_RGA_MANAGE_ALERT_STATUS;
Insert into MS_RGA_MANAGE_ALERT_STATUS (From_stage,To_stage,Actions,Status) values ('CREATE_EDIT','L2_APPROVE','SUB_CLR','MOD');
Insert into MS_RGA_MANAGE_ALERT_STATUS (From_stage,To_stage,Actions,Status) values ('L1_APPROVE','PUBLISH','APP','COM');
Insert into MS_RGA_MANAGE_ALERT_STATUS (From_stage,To_stage,Actions,Status) values ('L2_APPROVE','PUBLISH','APP','COM');
Insert into MS_RGA_MANAGE_ALERT_STATUS (From_stage,To_stage,Actions,Status) values ('L1_APPROVE','REQUEST_CANCEL','CNCL','REJECT');
Insert into MS_RGA_MANAGE_ALERT_STATUS (From_stage,To_stage,Actions,Status) values ('L2_APPROVE','REQUEST_CANCEL','CNCL','REJECT');
Insert into MS_RGA_MANAGE_ALERT_STATUS (From_stage,To_stage,Actions,Status) values ('L1_APPROVE','CREATE_EDIT','REQ_CLR','CLR_REQ');
Insert into MS_RGA_MANAGE_ALERT_STATUS (From_stage,To_stage,Actions,Status) values ('CREATE_EDIT','L1_APPROVE','SND_APP','APP_PEND');
Insert into MS_RGA_MANAGE_ALERT_STATUS (From_stage,To_stage,Actions,Status) values ('CREATE_EDIT','L1_APPROVE','SUB_CLR','MOD');
Insert into MS_RGA_MANAGE_ALERT_STATUS (From_stage,To_stage,Actions,Status) values ('L1_APPROVE','L2_APPROVE','APP','APP_PEND');
Insert into MS_RGA_MANAGE_ALERT_STATUS (From_stage,To_stage,Actions,Status) values ('L2_APPROVE','CREATE_EDIT','REQ_CLR','CLR_REQ');
Insert into MS_RGA_MANAGE_ALERT_STATUS (From_stage,To_stage,Actions,Status) values ('L1_APPROVE','L1_APPROVE','REQ_CLR','CLR_REQ');
commit;
end;