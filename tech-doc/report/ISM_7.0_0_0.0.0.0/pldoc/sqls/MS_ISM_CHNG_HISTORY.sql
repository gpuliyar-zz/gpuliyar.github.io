Package MS_ISM_CHNG_HISTORY
/**
*=======================================================================<br/>
* Module: ISM<br>
* Description: This package contains various procedures used to populate change history table for change history reports' of ISM
* <br/>
* ======================================================================<br/>
*/
As
    /** This procedure is called when non-multirow change histort details has to be populated
    * @param lt_in_PID to CAPTURE pid of the changed assignment
    * @param lt_in_Object_Type to cpature the object that has undergone change
    * @param lt_in_Issue_ID issue_id of the issue that has been modified
    * @param lt_in_Action_ID action_id of theaction that has been modified
    * @param lt_in_Modified_By user_name of the user who has modified either issue/action
    * @param comments field value added while reopening
    * @param lt_in_Action_Taken action performed by user either reopen/update**/
	PROCEDURE MS_ISM_CHANGE_HIST_NONMULTIROW(lt_in_PID      IN     NUMBER,
      lt_in_Object_Type IN VARCHAR2,
      lt_in_Issue_ID IN VARCHAR2,
      lt_in_Action_ID IN VARCHAR2 DEFAULT NULL,
      lt_in_Modified_By IN VARCHAR2,
      lt_in_Comments IN VARCHAR2,
      lt_in_Action_Taken IN VARCHAR2);	
    /** This procedure is called when multirow change histort details has to be populated
    * @param lt_in_PID to CAPTURE pid of the changed assignment
    * @param lt_in_Object_Type to cpature the object that has undergone change
    * @param lt_in_Issue_ID issue_id of the issue that has been modified
    * @param lt_in_Action_ID action_id of theaction that has been modified
    * @param lt_in_Modified_By user_name of the user who has modified either issue/action
    * @param comments field value added while reopening
    * @param lt_in_Action_Taken action performed by user either reopen/update**/
	PROCEDURE MS_ISM_CHANGE_HIST_MULTIROW(lt_in_PID      IN     NUMBER,
      lt_in_Object_Type IN VARCHAR2,
      lt_in_Issue_ID IN VARCHAR2,
      lt_in_Action_ID IN VARCHAR2 DEFAULT NULL,
      lt_in_Modified_By IN VARCHAR2,
      lt_in_Comments IN VARCHAR2,
      lt_in_Action_Taken IN VARCHAR2);

END MS_ISM_CHNG_HISTORY;
/