Declare 
  x_Error_Code    Number;
  x_Error_Message Varchar2(4000);
Begin
  x_Error_Code    := Null;
  x_Error_Message := Null;
 
  Ms_Apps_Utilities.Create_Infolet_View('INFOLET', 
                                        'MS_RGA_CONTENT_BROWSER',
                                        'MS_RGA_CONTENT_BROWSER_V',
                                        x_Error_Code,
                                        x_Error_Message);
Exception
  When Others Then
    Null;
End;