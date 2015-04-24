Declare 
  x_Error_Code    Number;
  x_Error_Message Varchar2(4000);
Begin
  x_Error_Code    := Null;
  x_Error_Message := Null;
 
  Ms_Apps_Utilities.Create_Infolet_View('INFOLET', 
                                        'MS_RGA_CHANNEL_DTLS',
                                        'MS_RGA_CHANNEL_DTLS_V',
                                        x_Error_Code,
                                        x_Error_Message);

  Ms_Apps_Utilities.Create_Infolet_View('INFOLET',
                                        'MS_RGA_CHANNEL_DTLS',
                                        'MS_RGA_CHANNEL_DTLS_DV', 
                                        x_Error_Code,
                                        x_Error_Message);

  /*Ms_Apps_Utilities.Create_Infolet_View('INFOLET',
                                        'MS RGA Channel Aggregator',
                                        'MS_RGA_FEED_AGGREGATOR_V',
                                        x_Error_Code,
                                        x_Error_Message);*/

  Ms_Apps_Utilities.Create_Infolet_View('INFOLET',
                                        'MS_RGA_NOTIFY_USERS',
                                        'MS_RGA_NOTIFY_USERS_V',
                                        x_Error_Code,
                                        x_Error_Message);
  Ms_Apps_Utilities.Create_Infolet_View('INFOLET',
                                        'MS_RGA_NOTIFY_USERS',
                                        'MS_RGA_NOTIFY_USERS_DV',
                                        x_Error_Code,
                                        x_Error_Message);

  Ms_Apps_Utilities.Create_Infolet_View('INFOLET',
                                        'MS_RGA_LOG_ISSUES',
                                        'MS_RGA_LOG_ISSUES_V',
                                        x_Error_Code,
                                        x_Error_Message);
  Ms_Apps_Utilities.Create_Infolet_View('INFOLET',
                                        'MS_RGA_LOG_ISSUES',
                                        'MS_RGA_LOG_ISSUES_DV',
                                        x_Error_Code,
                                        x_Error_Message);

  Ms_Apps_Utilities.Create_Infolet_View('INFOLET',
                                        'MS_RGA_MY_SUBSCRIPTION',
                                        'MS_RGA_MY_SUBSCRIPTION_V',
                                        x_Error_Code,
                                        x_Error_Message);
  Ms_Apps_Utilities.Create_Infolet_View('INFOLET',
                                        'MS_RGA_MY_SUBSCRIPTION',
                                        'MS_RGA_MY_SUBSCRIPTION_DV',
                                        x_Error_Code,
                                        x_Error_Message);
									
    Ms_Apps_Utilities.Create_Infolet_View('INFOLET',
                                        'MS_RGA_ALERT_OBJECT_RELATIONS',
                                        'MS_RGA_ALERT_OBJECT_RELATIO_V',
                                        x_Error_Code,
                                        x_Error_Message); 
										
    Ms_Apps_Utilities.Create_Infolet_View('INFOLET',
                                        'MS_RGA_EMAIL_CONTENT',
                                        'MS_RGA_EMAIL_CONTENT_V',
                                        x_Error_Code,
                                        x_Error_Message);
										
										
	   Ms_Apps_Utilities.Create_Infolet_View('INFOLET',
                                        'MS_RGA_MY_ALERTS',
                                        'MS_RGA_MY_ALERTS_V',
                                        x_Error_Code,
                                        x_Error_Message);
Exception
  When Others Then
    Null;
End; 
 