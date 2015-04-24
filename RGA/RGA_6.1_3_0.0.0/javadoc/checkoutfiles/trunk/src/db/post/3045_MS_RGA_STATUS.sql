Begin
DELETE FROM MS_RGA_STATUS;
Insert into MS_RGA_STATUS (Status_code,Status_Name,Locale_id) values ('NEW','New',1009);
Insert into MS_RGA_STATUS (Status_code,Status_Name,Locale_id) values ('REJECT','Rejected',1009);
Insert into MS_RGA_STATUS (Status_code,Status_Name,Locale_id) values ('CLR_REQ','Clarification Requested',1009);
Insert into MS_RGA_STATUS (Status_code,Status_Name,Locale_id) values ('APP_PEND','Approval Pending',1009);
Insert into MS_RGA_STATUS (Status_code,Status_Name,Locale_id) values ('MOD','Upon Modification',1009);
Insert into MS_RGA_STATUS (Status_code,Status_Name,Locale_id) values ('COM','Completed',1009);
COMMIT;
end;