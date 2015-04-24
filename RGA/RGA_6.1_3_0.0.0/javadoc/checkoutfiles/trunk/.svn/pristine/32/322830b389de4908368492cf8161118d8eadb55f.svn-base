<head><link id="orbGrid" type="text/css" href="$!{WEBROOT}/ORB/css/orbGrid.css" rel="stylesheet"></link></head>
<script type="text/javascript" src="$!{WEBROOT}/ORB/RowExpander.js"></script>
<script type="text/javascript" src="$!{WEBROOT}/ORB/Ext.ux.Printer.js"></script>
<script type="text/javascript" src="$!{WEBROOT}/ext/GridFilters/GridFilters.js"></script>
<script type="text/javascript" src="$!{WEBROOT}/ext/GridFilters/menu/RangeMenu.js"></script>
<script type="text/javascript" src="$!{WEBROOT}/ext/GridFilters/filter/Filter.js"></script>
<script type="text/javascript" src="$!{WEBROOT}/ext/GridFilters/filter/StringFilter.js"></script>
<script type="text/javascript" src="$!{WEBROOT}/ext/GridFilters/filter/DateFilter.js"></script>
<script type="text/javascript" src="$!{WEBROOT}/ext/GridFilters/filter/ListFilter.js"></script>
<script type="text/javascript" src="$!{WEBROOT}/ext/GridFilters/filter/NumericFilter.js"></script>
<script type="text/javascript" src="$!{WEBROOT}/ext/GridFilters/filter/BooleanFilter.js"></script>
<script>
// Global variable to store region names
var regionArray = new Array();
var mrfieldArray =  Array();
var notrequired =new Array(); 
var level2approverflag;
var validdates='Y';
var Object_Created_On;
var Object_Created_By;
var Object_Modified_On;
var Object_Modified_By;
var glob_invokedFromDataBrowser;
var user_chk_avblty;
var edit_flag;
var edit_flag;
var invokedFromDataBrowser;
var flag = 0;
// Flag that indicates if Level 2 approver is required. Turned off by default.
// For any form where Level2 Approver is required, that form can explicitly
// call F.LEVEL_2_APPROVER.show();
var level2ApproverRequired = 'Y';
//Function to enable the toolbar and the associated buttons
var parvalue='';
var grcReportDiv;
var grcHyperLinkDiv;

function showToolbar_new()
{ 
    var editDisabled = false;
    edit_flag = 'Y';
    invokedFromDataBrowser = false;
	F.activateToolBar();

    // If the form is currently under edit, or if the accessCode is 2 (user doesnt have Edit privileges)
    // then dont show the edit Button in the toolbar
  
    if (typeof(F.getFormParameter("edit_flag")) != 'undefined')
    {
       edit_flag = F.getFormParameter("edit_flag");
       invokedFromDataBrowser = true;
    }
   

    //Allowing reinitiation from list report
    if (accessCode != 99)
    {
 
        if (edit_flag != "Y" || accessCode == 2)
        {
            editDisabled = true;
        }
    }

 
    var myConfiguration = [{
                               iconCls: 'msai_tooldefault',
                               xtype:'button',
                               id: 'msai_tooldefault',
                               hidden: editDisabled || (!invokedFromDataBrowser),
                               tooltip:
                               {
                                   text: "Enables editing the contents of this form",
                                   title: "Edit",
                                   xtype: "quicktip"
                               },
                               handler: function() { editForm(); }
                           },
                           {
                               iconCls:'msai_toolsubmitnew',
                               xtype:'button',
                               id: 'msai_toolsubmitnew',
                               hidden: editDisabled || (invokedFromDataBrowser),
                               width: 30, 
                               tooltip:
                               {
                                   text: '${ToolBarSubmitText}',
                                   title: '${ToolBarSubmit}',
                                   xtype: "quicktip"
                               },
                               handler: function() { checknsubmit(); }
                           },
                           {
                                iconCls:'msai_toolcancelnew',
                                xtype:'button',
                                id: 'msai_toolcancelnew',
                                hidden: false,
                                tooltip:
                                        {
                                            text: '${ToolBarCancelText}',
                                            title: '${ToolBarCancel}',
                                            xtype: "quicktip"
                                        },
                                handler: function() { goConfirmCancel(); }
                            }
                           ];
		F.toolBar.setupControls(myConfiguration );
        F.toolBar.show();
}






function showToolbar()
{
  var editDisabled = false;
  edit_flag = 'Y';
  invokedFromDataBrowser = false;
  F.activateToolBar();

  // If the form is currently under edit, or if the accessCode is 2 (user has does not have the Edit privileges)
  // then dont show the edit Button in the toolbar
  //alert(F.getFormParameter("flag"));
  
  if (typeof(F.getFormParameter("edit_flag")) != 'undefined') {
    edit_flag = F.getFormParameter("edit_flag");
    invokedFromDataBrowser = true;
  }
  
 
  //function check added to enable/disable owner organizations field based on whether logged in user is present or not
if (invokedFromDataBrowser  != true ){
  checkUserInOrgs();
}  

if (accessCode != 99) {
  if ( edit_flag != "Y" || accessCode == 2) {
   editDisabled = true;
  }
}

if ((F.getFormParameter("flag") ==3) && (F.DD_OBJECT_TYPE.read() == 'MS_GRC_KPI_KRI_DEFINITION'))
		  {
		  edit_flag = 'Y';
		  invokedFromDataBrowser = true;
		  F.disableAll();
		  }

if ((F.DD_OBJECT_TYPE.read() == 'MS_GRC_KPI_KRI_DATA_ENTRY') && (invokedFromDataBrowser == true))
		  {
		  invokedFromDataBrowser = true;
		  editDisabled = true;
		  }
		  
//F.log("edit_flag  = " + edit_flag);
//F.log("accessCode =  " + accessCode);
//F.log("editDisabled = " + editDisabled);
//F.log("invokedfromDB = " + invokedFromDataBrowser);

//var reportToRunLater = F.getReportId("Comments History");
var reportToRunLater = F.getReportId('$commentshistory');

//F.log(reportToRunLater);

var reportsMenu = [];
for(var i=0; i < relRepNameArr.length; i++) {

//relRepNameArr[i] != 'Comments History' && relRepNameArr[i] != 'Add One Or More Organizations' && relRepNameArr[i] != 'Add One or More Items')

if ( relRepNameArr[i].length != 0 &&
relRepNameArr[i] == '${ChangeHistoryReport}')
{
//if (relRepNameArr[i] != '') {
    reportsMenu.push({ metricid: relRepMetricArr[i], 
                       reportid: relRepArr[i], 
                       text: relRepNameArr[i], 
                       handler: function(rep) { getReport(rep.metricid, rep.reportid); } 
                    });
  }
}

var saveMenu = [];
	saveMenu.push({
                iconCls:'msai_toolsavenew',
                xtype:'button',
                id: 'msai_toolsave',
                hidden: editDisabled || (invokedFromDataBrowser),
                tooltip:
                {
                  text: "$savetext",
                  title: "$savedraft",
                  xtype: "quicktip"
                },
                handler: function() { checknsave(); }
              });

			  saveMenu.push({
                iconCls:'msai_toolsavedraftandclose',
                xtype:'button',
                id: 'msai_toolsavedraftandclose',
                hidden: editDisabled || invokedFromDataBrowser,
                tooltip:
                {
                  text: "$savedraftclosetext",
                  title: "$savedraftclose",
                  xtype: "quicktip"
                },
                handler: function() { checknsaveclose(); }
              });
	 if (F.DD_OBJECT_TYPE.read() != 'MS_GRC_KPI_KRI_DATA_ENTRY' )
			  {

				var myConfiguration = [
                                {
                                    iconCls: 'msai_tooldefault',
                                    xtype:'button',
                                    id: 'msai_tooldefault',
                                    hidden: editDisabled || (!invokedFromDataBrowser),
                                    tooltip:
                                            {
                                                text: "$edittext",
                                                title: "$edit",
                                                xtype: "quicktip"
                                            },
                                    handler: function() { editForm(); }
                                },
                                {
                                    iconCls:'msai_toolsubmitnew',
                                    xtype:'button',
                                    id: 'msai_toolsubmitnew',
                                    hidden: editDisabled || (invokedFromDataBrowser),
                                    width: 30, 
                                    tooltip:
                                            {
                                                text: "$submittext",
                                                title: "$submittitle",
                                                xtype: "quicktip"
                                            },
                                    handler: function() { checknsubmit(); }
                                },
/*
                                {
                                    iconCls:'msai_toolsave',
                                    //xtype:'button',
                                    id: 'msai_toolsave',
                                    menu: saveMenu,
                                    hidden: editDisabled,
                                    tooltip:
                                            {
                                                text: "Saves the content without processing it to the next step",
                                                title: "Save Draft",
                                                xtype: "quicktip"
                                            },
                                    handler: function() { checknsave(); }
                                },
*/
                                {
                                    iconCls:'msai_toolsavenew',
                                    xtype:'button',
                                    id: 'msai_toolsavenew',
                                    hidden: editDisabled || (invokedFromDataBrowser),
                                    tooltip:
                                            {
                                                text: "$savetext",
                                                title: "$savedraft",
                                                xtype: "quicktip"
                                            },
                                    handler: function() { checknsave(); }
                                },
                                {
                                    iconCls:'msai_toolsavedraftandclose',
                                    xtype:'button',
                                    id: 'msai_toolsavedraftandclose',
                                    hidden: editDisabled || (invokedFromDataBrowser),
                                    width: 30,
                                    tooltip:
                                            {
                                                text: "$savedraftclosetext",
                                                title: "$savedraftclose",
                                                xtype: "quicktip"
                                            },
                                    handler: function() { checknsaveclose(); }
                                },
                                {
        			    //iconCls:'msai_toolbpmdesigner',
						 icon:'/appsui/images/node-selected_Green.png',
                                    xtype:'button',
                                    id: 'msai_toolbpmdesigner',
                                    hidden:true,
                                    tooltip:
                             	            {
                                      		text: "$bpmtext",
                                      		title: "$bpm",
                                      		xtype: "quicktip"
                                    	    },
                                    handler: fnBPMDesigner
                                },
                                {
                                    iconCls:'msai_toolreports',
                                    menu : reportsMenu,
                                    tooltip:
                                            {
                                                text: "$viewreportstext",
                                                title: "$viewreports",
                                                xtype: "quicktip"
                                            },
                                    handler: function() {}
                                },
                                /*{
                                    iconCls:'msai_toolprint',
                                    xtype:'button',
                                    tooltip:
                                            {
											text: "Provides a popup window with a printer friendly representation of the contents of this form",
                                                title: "Print",
                                                xtype: "quicktip"
                                            },
                                    handler: function() { F.showPrint(); }
                                },*/
                                {
                                    iconCls:'msai_toolcancelnew',
                                    xtype:'button',
                                    id: 'msai_toolcancelnew',
                                    hidden: false,
                                    tooltip:
                                            {
                                                text: "$canceltext",
                                                title: "$cancel",
                                                xtype: "quicktip"
                                            },
                                    handler: function() { goConfirmCancel(); }
                                }
                            ];
			}
			else
			  {
			  var myConfiguration = [
                                {
                                    iconCls: 'msai_tooldefault',
                                    xtype:'button',
                                    id: 'msai_tooldefault',
                                    hidden: editDisabled || (!invokedFromDataBrowser),
                                    tooltip:
                                            {
                                                text: "$edittext",
                                                title: "$edit",
                                                xtype: "quicktip"
                                            },
                                    handler: function() { editForm(); }
                                },
                                {
                                    iconCls:'msai_toolsubmitnew',
                                    xtype:'button',
                                    id: 'msai_toolsubmitnew',
                                    hidden: editDisabled || (invokedFromDataBrowser),
                                    width: 30, 
                                    tooltip:
                                            {
                                                text: "$submittext",
                                                title: "$submittitle",
                                                xtype: "quicktip"
                                            },
                                    handler: function() { checknsubmit(); }
                                },
                                {
                                    iconCls:'msai_toolreports',
                                    menu : reportsMenu,
                                    tooltip:
                                            {
                                                text: "$viewreportstext",
                                                title: "$viewreports",
                                                xtype: "quicktip"
                                            },
                                    handler: function() {}
                                },
                                {
                                    iconCls:'msai_toolcancelnew',
                                    xtype:'button',
                                    id: 'msai_toolcancel',
                                    hidden: false,
                                    tooltip:
                                            {
                                                text: "$canceltext",
                                                title: "$cancel",
                                                xtype: "quicktip"
                                            },
                                    handler: function() { goConfirmCancel(); }
                                }
                            ];
			  }
F.toolBar.setupControls(myConfiguration );
F.toolBar.show();
addBPMIcon();
glob_invokedFromDataBrowser=invokedFromDataBrowser;
}
//Function to call the editable version of the form
function callForm(id, procId)
{
  var paramone = SERVLET_URL + "/Pushinfolet?id=" + id + "&submit_back=no&flag=1";
  //var paramone = SERVLET_URL + "/Pushinfolet?id=" + id + "&";
  if (procId)
  {
    paramone = paramone + '&proc=' + procId;
  }
 // window.parent.location = paramone;
 
  window.parent.location = paramone+'&bare='+encodeURIComponent(top.window.top.previousPage);
  
};

function disableMrow(mRow, flag)
{
   mRow.onAddRow = function()
   {
      return false;

   }
   mRow.onDeleteRow = function()
   {
      return false;

   }
   // If the multi - row already has records, then show the first page, otherwise show an empty section
   //msgbox(mRow.getRowCount());
   if (flag == 'SHOW')
   {
      mRow.showFirstPage();
   }
   else
   {
      mRow.makeEmpty();
      mRow.showFirstPage();

   }
   // Disable each of the rows in the multirow
   for(row in mRow.rows())
   {
      mRow.disableRow(row);

   }
   // After each page load, disable the rows in that page, since platform enables it by default
   mRow.afterPageLoad = function(startRow, endRow)
   {
      for (i = startRow; i <= endRow; i ++ )
      {
         mRow.disableRow(i);

      }
   }
}

function enableMrow(mRow, flag)
{

   // Allow the user from adding or deleting rows in the multirow
   mRow.onAddRow = function()
   {
      return true;

   }
   mRow.onDeleteRow = function()
   {
alert('testondeleterow');
      return true;

   }
   // If there are no rows in the multi - row, show a blank section, otherwise show the first page
   if (flag == 'SHOW')
   {	
      mRow.showFirstPage();		
   }
   else
   {  
      mRow.makeEmpty();
      mRow.showFirstPage();
	  
   }

   // Enable all the rows in the multi - row
   for(row in mRow.rows())
   {	
      mRow.enableRow(row);

   }
}

//to update title
function callUpdateTitle()
{

if (F.OBJECT_ID.read() == 'NONE' && F.OBJECT_NAME.read() == '')
	{
		F.toolBar.updateTitle(F.getTitle() + ': ' + F.OBJECT_NAME.read().substring(0,104)+ ' []' );
		
	}
else
	{		
			//F.log(F.OBJECT_NAME.read().substring(0,104) + ' [' + F.OBJECT_ID.read() + ']');
			F.toolBar.updateTitle(F.getTitle() + ': ' + F.OBJECT_NAME.read().substring(0,104) + ' [' + F.OBJECT_ID.read() + ']');
			
	}
}

//to show / hide parent based on level selected
function showHideParent()
{
   if (F.OBJECT_LEVEL.read() == '1' || F.OBJECT_LEVEL.read() == '')
   {
      F.OBJECT_PARENT.makeNotRequired().hide();
   }
   else
   {
      F.OBJECT_PARENT.makeRequired().show();
   }
}

//to call disableMrow function disable the multi row records
function disableMultiRows()
{
if (regionArray.length != 0)
   {
      for (i = 0; i < regionArray.length; i ++ )
      { 
        if (typeof(mrfieldArray[i]) != "undefined" && mrfieldArray[i].read(1) != '' ) {        
         disableMrow(regionArray[i], 'SHOW');
        } else {
         disableMrow(regionArray[i], 'DONTSHOW');
        }      
      }
   }
}

//to call enableMrow function disable the multi row records
function enableMultiRows()
{
	if(F.DD_OBJECT_TYPE.read() != 'MS_GRC_KPI_KRI_DEFINITION')
	{
		if (regionArray.length != 0)
		{
		  for (v = 0; v < regionArray.length; v ++ )
		  {
			if ((typeof(mrfieldArray[v])!= "undefined" && mrfieldArray[v].read(1) != '' )) 
			{
				enableMrow(regionArray[v], 'SHOW');
			} else {
				//F.log('dont show');
				enableMrow(regionArray[v], 'DONTSHOW');
			}
		  }         
		}
	}
	else
	{ 	
		if (regionArray.length != 0)
		{
			for (v = 0; v < regionArray.length; v ++ )
			{
			  //alert(regionArray[v].getName() +'-------------'+ mrfieldArray[v].read(1));
			  if ((typeof(mrfieldArray[v])!= "undefined" && mrfieldArray[v].read(1) != '') || (formStatus =='1' && regionArray[v].getName() == 'ATI')) //Added formStatus condition to resolve the 51695 bug		
			  {
				enableMrow(regionArray[v], 'SHOW');
			  } else {
				//F.log('dont show');
				enableMrow(regionArray[v], 'DONTSHOW');
			  }
			}         
		}
	}
}   




// Function to disable all the fields in the form, including multi - row sections (except for the approval section)
function disableCommonFields()
{
   // Disable all single fields
   F.OBJECT_NAME.disable();
   if (F.DESCRIPTION)
   {
   F.DESCRIPTION.disable();
   }
   F.VALID_FROM.disable();
   F.VALID_UNTIL.disable();
   F.OWNER_ORGANIZATIONS.disable();
   F.OWNERS.disable();
   F.RESTRICT_ACCESS_TO.disable();
   F.LEVEL_1_APPROVER.disable();
   disableMultiRows();
}


// Function to enable all the fields in the form, including the multi - row sections
function enableCommonFields()
{
   // Disable all single fields
   F.OBJECT_NAME.enable();
   if (F.DESCRIPTION && F.DD_OBJECT_TYPE.read()!='MS_GRC_QUESTION_PROCEDURE')
   {
   F.DESCRIPTION.enable();
   }
   F.VALID_FROM.enable();
   F.VALID_UNTIL.enable();
   F.OWNER_ORGANIZATIONS.enable();
   F.OWNERS.enable();
   F.RESTRICT_ACCESS_TO.enable();
   F.LEVEL_1_APPROVER.enable();
   enableMultiRows();

}

//Override the showDefault function to show the editable form
//edit_flag value is passed from the browser or the report. If this value is set to "Y", then the form
//is in an editable state.
//Make a call to the callForm to invoke the editable version of the form
//Otherwise, show an alert that the user cannot modify this form at this time

/* Old version commented to implement form level security
F.showDefault = function()
{
  if (F.getFormParameter("edit_flag") == "Y") {
    msgbox(F.getFormParameter("edit_flag") + ' : ' + F.getFormParameter("id") + ' : ' + F.getFormParameter("proc"));
    callForm(F.getFormParameter("id"), F.getFormParameter("proc"));
  } else {
    msgbox("The Object cannot be edited since another user is modifying it at the moment.");
  }
}
*/

function editForm()
{
//alert(F.getFormParameter("edit_flag"));
  if ((F.getFormParameter("edit_flag") == "Y") || (F.getFormParameter("flag") == "3")){
   // alert(F.getFormParameter("edit_flag") + ' : ' + F.getFormParameter("id") + ' : ' + F.getFormParameter("proc"));
   // alert('AccessCode value returned from form: ' + accessCode);
     if (accessCode == 2)
	{
	F.log("Object cannot be edited due to Security Check Fail.");
	}
	else
	{
	callForm(F.getFormParameter("id"), F.getFormParameter("proc"));
	}
  } else {
    //msgbox("The Object cannot be edited since another user is modifying it at the moment.");
	alert('$Object_cannot_be_edited');
  }
}
	
//function to call for making action comments field to required/optional
function setReqForComments()
{
	if (F.OBJECT_ACTION.read() == 'CNCL' || F.OBJECT_ACTION.read() =='REQ_CLR' )
	{
	 F.ACTION_COMMENTS.makeRequired();
	}
	else
	{
        F.ACTION_COMMENTS.makeNotRequired();

	} 			
}


//below function checks whether level 1 approver field contains any value or not
F.level2Approvercheck = function()
{	
	//**Do not remove F.log line.
	F.log(F.VALID_UNTIL.read());
	if (F.LEVEL_2_APPROVER.read() !="" && F.LEVEL_1_APPROVER.read() =="")
	{ 
		alert('${level1approver}');
	   //msgbox('Level 1 Approver cannot be blank');
	  level2approverflag ='N';	 
	  return false;
	}
	else
	{
	level2approverflag ='Y';	
	return true;
	}
}

//Function to populate NONE into primary key columns
F.regionPkeycheck = function()
{
	for (var i = 0; i < regionArray.length; i ++ )
    {
		var cnt = regionArray[i].getRowCount();
		F.log('number of records in '+ regionArray[i]+' = '+cnt);
		for (var j = 1; j <= cnt; j++)
		{
		F.log('Checking '+ mrpkeyArray[i]+ j + ' record is '+ mrpkeyArray[i].read(j));
F.log(mrpkeyArray[i].read(j));
			if   (mrpkeyArray[i].read(j) == '') {
				mrpkeyArray[i].write('NONE',j);
			}
		}
	}

}
//custom function to compare dates
function validate_start_and_end_date(check_dt,cre_dt)
   {
    var ret_value = '1';

    var my_chk_date = new Date(check_dt);
    var my_cre_date = new Date(cre_dt);

    var ed_month = my_chk_date.getMonth();
    var ed_date  = my_chk_date.getDate();
    var ed_year  = my_chk_date.getFullYear();

    var curr_month = my_cre_date.getMonth();
    var curr_date = my_cre_date.getDate();
    var curr_year = my_cre_date.getFullYear();

    if ( ed_year > curr_year )
    {
        ret_value ='-1';
    }
    else
    {
        if ( ed_year == curr_year )
        {   
            if ( ed_month > curr_month )
            {
                ret_value = '-1';
            }
            else
            {
                if ( ed_month == curr_month)
                {
                    if (   ed_date > curr_date )
                    {
                        ret_value = '-1';
                    }
                }
            }               
        }
    }

    return ret_value;
}

function msgbox(txt,msgType)
{
	//var iconType=Ext.MessageBox.WARNING;
	//if (msgType)
	//{
	//	iconType=msgType;
	//}
	//Ext.Msg.show({title:'Alert',msg:txt,minWidth:200,buttons:Ext.Msg.OK,icon:iconType});
	alert(txt);
	
}

function msgbox1(txt,msgType)
{
	var iconType=Ext.MessageBox.WARNING;
	if (msgType)
	{
		iconType=msgType;
	}
	Ext.Msg.show({title:'Relationship Alert',msg:txt,minWidth:200,buttons:Ext.Msg.OK,icon:iconType});
	//alert(txt);
	
}

function addBPMIcon(){
	if(F.DD_OBJECT_TYPE.read() =="MS_GRC_PROCESS"){
		Ext.Ajax.request({
			url: 'Bpmservlet/ajax/InfoletData',
			method: 'POST',
			params:{
				infoletname:'MS_APPS_BPM'
			},
			success: function(response, options) {
				for(var i=0; i<F.toolBar.configuration.length; i++)
				{
				    if(F.toolBar.configuration[i].id=='msai_toolbpmdesigner')
				    {
					F.toolBar.configuration[i].hidden = false;
					F.toolBar.configuration[i].handler = function() { fnBPMDesigner(response.responseText); }
				    }
				}
				top.setActiveToolbar(F.toolBar.configuration);
			}
		});
	}
}

function fnBPMDesigner(infoletId)
{
   var queryStringObj=Ext.urlDecode(window.location.search.substring(1));
   var allowRedirect=true;
   if (!queryStringObj.edit_flag){
        allowRedirect=false;
      /*Ext.Msg.confirm( 'Confirm','Changes should be saved first to go to BPM page. Do you want to continue?',function(btn, text){
                     if(btn == 'yes'){
                checknsave();
                fnRedirect(infoletId);
                
             }
             else{
                 
                return;
             }
      });*/
      Ext.Msg.show({
           title:'Confirm',
           msg: 'Do you want to save the changes?',
           buttons: Ext.Msg.YESNOCANCEL,
           fn: grcSaveResult,
           icon: Ext.MessageBox.QUESTION
      });
   }
	if(allowRedirect){
        fnRedirect(infoletId);
	}
   		function grcSaveResult(btn) {
        		if(btn=='yes'){
                 	checknsave();
         		fnRedirect(infoletId);
        		}
			else if(btn=='no'){
                	fnRedirect(infoletId);
        		}
			else if(btn=='cancel'){
                	goCancel();
        		}
  		}	
   		function fnRedirect(infoletId){
       		F.getObject('main_container').style.visibility='hidden';
   		var objectId=F.OBJECT_ID.read();
   		var objectName=F.OBJECT_NAME.read();
  		var objectType=F.DD_OBJECT_TYPE.read();
   		var objectInfoletId=F.getInfoletId();
   		var objectLevel=F.OBJECT_LEVEL.read();
   		var objectEditFlag=F.getFormParameter("edit_flag");
  		var objectInstId=F.getFormParameter("instid");
   		var objectProcId=F.getFormParameter("proc");
   		var formmode=(!queryStringObj.edit_flag)?"edit":'view';
  		var objectName=escape(objectName);
   		var objectDrillDown=queryStringObj.OBJECT_DRILLDOWN;
   		var paramone=SERVLET_URL+'/Pushinfolet?flag=1&id='+infoletId+'&OBJECT_ID='+objectId+'&OBJECT_NAME='+objectName+'&OBJECT_TYPE='+objectType+'&OBJECT_ACCESS_CODE='+accessCode+'&OBJECT_FORM_ID='+objectInfoletId+'&OBJECT_FORM_MODE='+formmode+'&OBJECT_LEVEL='+objectLevel+'&OBJECT_EDIT_FLAG='+objectEditFlag+'&OBJECT_INST_ID='+objectInstId+'&OBJECT_PROC_ID='+objectProcId;
                       if(objectDrillDown=='Y')
                             paramone=paramone+"&OBJECT_DRILLDOWN="+objectDrillDown;
                             window.location=paramone+'&bare='+encodeURIComponent(top.window.top.previousPage);
                }
}
function at_submit()
{
//to hide controls on the toolbar after user clicks on submit
	//F.toolBar.hideControls();	
}

function afterFormSave()
{
F.toolBar.showControls();
}

function checkUserInOrgs()
{
if (F.CHECK_USER_IN_ORGS)
	{
	F.log('in check_user_in_orgs');
	var values_in_dd;
    values_in_dd= document.getElementById('id'+F.CHECK_USER_IN_ORGS.getSequence()).options.length - 1;
	F.log('values_in_dd ' + values_in_dd );
	if ((F.OBJECT_ID.read() != '') && (F.DD_CURRENT_USER_NAME.read() != 'SYSTEMI'))
		{
		if (values_in_dd >= 1)
			{
			F.OWNER_ORGANIZATIONS.enable();
			}
		else
			{  
			F.OWNER_ORGANIZATIONS.disable();
			}
		}  
	}
}

function approvercheck()
{
if (accessCode == 4) 
	{
	F.disableAll();
	F.OBJECT_ACTION.enable();
	F.ACTION_COMMENTS.enable();
	}
}

function restrictedaccess()
{
if (accessCode == 5) 
	{
	F.OWNER_ORGANIZATIONS.disable();
	}
}

/*function approvercheck(formName)
{
   if ((F.DD_CURRENT_STAGE.read()=='L1_APPROVE'||F.DD_CURRENT_STAGE.read()=='L2_APPROVE')&&(accessCode == 4)&&(F.OBJECT_ACTION.read() =='R'))
   {
      validdates ='Y'
     if(formName =='MS_GRC_REFERENCE')
	   {
	   alert("TEST!!@#");
	   F.REFERENCE_DOCUMENT.makeNotRequired();
	   F.REFERENCE_URL_TYPE.makeNotRequired();
	   F.REFERENCE_URL.makeNotRequired();
	   F.RESTRICT_ACCESS_TO.makeNotRequired();
	   F.OWNER_ORGANIZATIONS.makeNotRequired();
	   F.REFERENCE_TYPE.makeNotRequired();
	   F.REFERENCE_CATEGORY.makeNotRequired();
	   //F.OWNERS.makeNotRequired();
	   }
	  else if(formName =='MS_GRC_OBJECTIVES')
	   {
	  F.OWNER_ORGANIZATIONS.makeNotRequired();
	   }
	  else if (formName =='MS_GRC_AREA_OF_COMPLIANCE')
	   {
	   F.RESTRICT_ACCESS_TO.makeNotRequired();
	   F.OWNER_ORGANIZATIONS.makeNotRequired();
	   MODEL_TYPE
	   ORGANIZATION_NAME
	   F.OWNERS.makeNotRequired();
       }
	   else if (formName =='MS_GRC_FINANCIAL_ACCOUNTS')
	   {
	   F.RESTRICT_ACCESS_TO.makeNotRequired();
	   OBJ_CATEGORY
	   OBJ_TYPE
	   F.OWNER_ORGANIZATIONS.makeNotRequired();
	   F.OWNERS.makeNotRequired();
       }
	   else if (formName =='MS_GRC_FUNCTION')
	   {
	   F.RESTRICT_ACCESS_TO.makeNotRequired();
	   RELATIONSHIP
	   OTHER_NAME
	   F.OWNER_ORGANIZATIONS.makeNotRequired();
	   F.OWNERS.makeNotRequired();
       }
	   else if (formName =='MS_GRC_MODEL_REFERENCE')
	   {
	   F.RESTRICT_ACCESS_TO.makeNotRequired();
	   REFERENCE_TYPE
	   F.OWNER_ORGANIZATIONS.makeNotRequired();
	   F.OWNERS.makeNotRequired();
       }
	   else if (formName =='MS_GRC_ORG_PROFILE')
	   {
	   F.RESTRICT_ACCESS_TO.makeNotRequired();
	   F.OWNER_ORGANIZATIONS.makeNotRequired();
	   F.OWNERS.makeNotRequired();
       }
	   else if (formName =='MS_GRC_PROCESS')
	   {
	   F.RESTRICT_ACCESS_TO.makeNotRequired();
	   F.OWNER_ORGANIZATIONS.makeNotRequired();
	   F.OWNERS.makeNotRequired();
       }
	   else if (formName =='MS_GRC_QUESTION_PROCEDURE')
	   {
	   F.RESTRICT_ACCESS_TO.makeNotRequired();
	   F.OWNER_ORGANIZATIONS.makeNotRequired();
	   F.OWNERS.makeNotRequired();
       }
	   else if (formName =='MS_GRC_REGULATORY_BODY')
	   {
	   F.RESTRICT_ACCESS_TO.makeNotRequired();
	   F.OWNER_ORGANIZATIONS.makeNotRequired();
	   F.OWNERS.makeNotRequired();
       }
	   else if (formName =='MS_GRC_REQUIREMENT')
	   {
	   F.RESTRICT_ACCESS_TO.makeNotRequired();
	   F.OWNER_ORGANIZATIONS.makeNotRequired();
	   F.OWNERS.makeNotRequired();
       }
	   else if (formName =='MS_GRC_CONTROL')
	   {
	   F.RESTRICT_ACCESS_TO.makeNotRequired();
	   F.OWNER_ORGANIZATIONS.makeNotRequired();
	   F.OWNERS.makeNotRequired();
       }
	   else (formName =='MS_GRC_RISK')
	   {
	   F.RESTRICT_ACCESS_TO.makeNotRequired();
	   F.OWNER_ORGANIZATIONS.makeNotRequired();
	   F.OWNERS.makeNotRequired();
       }
    }
}
*/
/*function approvercheck(formName)
{
   if ((F.DD_CURRENT_STAGE.read()=='L1_APPROVE'||F.DD_CURRENT_STAGE.read()=='L2_APPROVE')&&(accessCode == 4)&&(F.OBJECT_ACTION.read() =='R'))
   {
      validdates ='Y'
     if(formName =='MS_GRC_REFERENCE')
	   {
      var Makenotrequired =new Array(); 
      approvercheck[0]="Saab";       
      approvercheck[1]="Volvo";
      approvercheck[2]="BMW";*/

function GRC_OnLoad()
{	
    //variable to find emd value if form is opened via link in ORB grid
	parvalue=F.getFormParameter('emd')
	//alert(parvalue);
	F.log("location.href.indexOf('wrapper=no')"+location.href.indexOf('wrapper=no'));
	F.log("edit_flag  = " + edit_flag);
	F.log("accessCode =  " + accessCode);
	F.log("invokedfromDB = " + glob_invokedFromDataBrowser);
	displaytoolbar();
	F.OBJECT_NAME.onChange();     	
	InitiateNewObject(); 
	status_updation();
	//not calling function if form is opened via link in ORB grid
	if (parvalue  !='3'){
	showHideComments();}
	objectCreationInfo();
	objectModificationInfo();
	getConfigParams();
	//approvercheck();
	restrictedaccess();
	defaultRestriction();
	enableMultiRows();
	if (F.ORB)
	{	
	includeORB();	
	}
}

function status_updation()
{


	F.OBJ_STATUS_TEMP.write('');
	F.OBJ_STATUS_TEMP.write(F.OBJ_STATUS.read());
         //alert('dd_current_stage'+F.DD_CURRENT_STAGE.read());
	if(F.DD_CURRENT_STAGE.read()=='NONE' || F.DD_CURRENT_STAGE.read()=='')
	{
			F.OBJ_STATUS_TEMP.write('NEW');
			}
			//alert('OBJ_STATUS_temp'+F.OBJ_STATUS_TEMP.read());
			//alert('OBJ_STATUS'+F.OBJ_STATUS.read());
	var cstage=F.DD_CURRENT_STAGE.read();
	var ostatus=F.OBJ_STATUS.read();
        var fparam=F.getFormParameter("edit_flag");
			
       
	//if ((cstage=='CREATE_EDIT') && ((ostatus=='ACT')|| (ostatus=='INACT')) && glob_invokedFromDataBrowser != true)
if ((cstage=='CREATE_EDIT' || cstage =='PUBLISH') && ((ostatus=='ACT')|| (ostatus=='INACT')) && glob_invokedFromDataBrowser == false)

	{
		
		F.OBJ_STATUS_TEMP.write('MOD');
	} 
 
 	F.OBJ_STATUS_TEMP.callInfolet(); 
 	//F.DD_CURRENT_USER_NAME.callInfolet();

}

function GRC_OnSubmit()
{
	var retvalue;	
	//updateRowNum();	
	retvalue = InvokeUserCheck();
	if (retvalue == false)
		return retvalue;
	 //calling  object_Action.onChange to make comments to mandatory when request clarification is selected and form is saved	
	F.OBJECT_ACTION.onChange();   
	F.regionPkeycheck()
	//clarificationcheck();
 	retvalue = F.level2Approvercheck();
 	if (retvalue == false)
		return retvalue;
 	retvalue = datevaliditycheck();

 	return retvalue;
}
//To Find the object's creation date and user (who initiated the Object)

function objectCreationInfo()
{
	var Object_Created_By;
	var date = new Date();
	var d  = date.getDate();
	var day = (d < 10) ? '0' + d : d;
	var m = date.getMonth() + 1;
	var month = (m < 10) ? '0' + m : m;
	var yy = date.getFullYear();
	var year = (yy < 1000) ? yy + 1900 : yy;

        if(F.OBJ_STATUS_TEMP.read()=='NEW')
	{
 		F.OBJ_CREATED_ON.writeValue(month + "/" + day + "/" + year,month + "/" + day + "/" + year);
    		Object_Created_By=F.DD_CURRENT_USER_NAME.read();
 		F.OBJ_CREATED_BY.writeValue(Object_Created_By,Object_Created_By);
                            
       } 
	else
	return;
}


//To find the object's modification Date and user (who modified the Object)

function objectModificationInfo()
{
	var Object_Modified_By;
	var date = new Date();
	var d  = date.getDate();
	var day = (d < 10) ? '0' + d : d;
	var m = date.getMonth() + 1;
	var month = (m < 10) ? '0' + m : m;
	var yy = date.getFullYear();
	var year = (yy < 1000) ? yy + 1900 : yy;              	
	if(F.OBJ_STATUS_TEMP.read()=='MOD')
	{
	F.OBJ_MODIFIED_ON.writeValue(month + "/" + day + "/" + year,month + "/" + day + "/" + year);
        Object_Modified_By=F.DD_CURRENT_USER_NAME.read();
        F.OBJ_MODIFIED_BY.writeValue(Object_Modified_By,Object_Modified_By);        
     	}
        	if(F.OBJ_MODIFIED_ON.read()=="" && F.OBJ_MODIFIED_BY.read()=="")
        	{
        	F.OBJ_MODIFIED_ON.hide();
        	F.OBJ_MODIFIED_BY.hide();
        	}
	       	else
	       	return;
  }
//alert('Edit Flag value' + edit_flag);

function clarificationcheck()
{
if ((F.DD_CURRENT_STAGE.read()=='L1_APPROVE'||F.DD_CURRENT_STAGE.read()=='L2_APPROVE')&&(accessCode == 4)&&(F.OBJECT_ACTION.read() =='R'))
   {
      for (i = 0; i <notrequired.length; i ++ )
      {              	  
         notrequired[i].makeNotRequired();           
      }
   }
}

function getConfigParams()
{

	F.OWN_APP_CONFIG.onChange();
	//alert(F.OWN_APP_CONFIG_TEMP.read());
	if(F.OWN_APP_CONFIG_TEMP.read()=='1')
	{
		F.LEVEL_1_APPROVER.hide();
		F.LEVEL_2_APPROVER.hide();
	}
	else if(F.OWN_APP_CONFIG_TEMP.read()=='2')
	{	
		F.LEVEL_2_APPROVER.hide();
	}

	
	
}
/*
F.OWN_APP_CONFIG.onChange=function()
{

if(F.OWN_APP_CONFIG_TEMP.read()=='')
			F.OWN_APP_CONFIG_TEMP.write(F.OWN_APP_CONFIG.read());
}
*/


function showHideComments()
{
//alert('entering showHideComments');
//alert(F.OBJ_STATUS_TEMP);
if (
		(F.OBJ_STATUS_TEMP.read() =='NEW') || 
		(F.OBJ_STATUS_TEMP.read() =='ACT')|| 
		(F.OBJ_STATUS_TEMP.read() =='MOD')
   )
			{			
			//var divelem = getReportDiv('Comments History');		
			//alert('here.....');
			var divelem = getReportDiv('$commentshistory');	
			//alert(divelem);
			if (divelem != false) divelem.style.display = 'none';
			}
}

function InitiateNewObject()
{
      if(F.DD_CURRENT_STAGE.read()==''&& F.OBJECT_ID.read() == '')
         {	
			F.OBJECT_ID.write('NONE');
			F.DD_CURRENT_STAGE.write('NONE');
            valueisthere(F.DD_CURRENT_STAGE.getSequence());			
         }
//         enableMultiRows();
}		 

function datevaliditycheck()
{
	if ((F.VALID_FROM.read() !='' && F.VALID_UNTIL.read() !='') && (validate_start_and_end_date(F.VALID_FROM.read(),F.VALID_UNTIL.read()) == -1))
	{
		msgbox("$common_datecheck");
		return false;
	}
	else
	{
		return true;
	}

}

// Add additional sections here for field specific validations
/*F.OBJECT_NAME.onChange = function()
   {
   //function if form is opened via link in ORB grid
	 if (parvalue != '3')
	 {
   	callUpdateTitle();
	}
  }*/
	
// function to call for making action comments field to required / optional
/*F.OBJECT_ACTION.onChange = function()
  {
   	setReqForComments();
   	//InvokeUserCheck();

  }	
*/
/*F.OWNER_ORGANIZATIONS.onChange = function()
{

//	InvokeUserCheck();
}*/


function InvokeUserCheck()
{

	if (F.OWNER_ORGANIZATIONS.read() !='' && F.OBJECT_ACTION.read() != '' && F.OBJECT_ACTION != 'CNCL')
	{
		F.OBJECT_ID.callInfolet();
		user_chk_avblty = F.CHECK_USER_AVAIL.read();
		
	}
	if (user_chk_avblty == 1)
		return true;
	else
{
		switch (user_chk_avblty)
		{	
			case 1 : 
				return true;
				break;
			case '-1': 
				//alert('There are no users with Edit rights belonging to the selected Owner Organization(s).');
				msgbox("$no_users");

				return false;
				break;
			case '-2':
				//alert('The initiator does not exist in the system.');
				msgbox("$initiator_not_exists");
				return false;
				break;
			default:	
				return true;

		}
}

}

//Function to set default Restrict Access to as No Restriction for a new form.
function defaultRestriction()
{
	if (F.RESTRICT_ACCESS_TO.read() == '')
	{
		F.RESTRICT_ACCESS_TO.write('N');      

	}
}
function displaytoolbar()
{
   if(location.href.indexOf('wrapper=no') < 1)
   {
      showToolbar();
//      F.OBJECT_NAME.onChange();
   }
   //disable fields if form is opened via link in ORB grid
   if(parvalue == '3')	
		{  		
			F.disableAll();
			disableMultiRows();
            F.OBJECT_ACTION.disable();    		   
       }
}

    //functionality to report link id

    function getReportLinkId() {
        var className = 'msai_attached_report_name';
        var hasClassName = new RegExp("(?:^|\\s)" + className + "(?:$|\\s)");
        var allElements = document.getElementsByTagName("span");
        var results = [];
        var element;
        for (var i = 0;
        (element = allElements[i]) != null; i++) {
            var elementClass = element.className;
            if (elementClass && elementClass.indexOf(className) != -1 && hasClassName.test(elementClass)) {
                results[element.innerHTML] = element.parentNode;
                //alert(element.innerHTML);
				F.log('*element.innerHTML*' +element.innerHTML);
            }
        }
        return results;
    }
    //end of functionality to report link id
    //Start of functionality to get div id of a linked report

    function getReportDiv(repId) {
        grcReportDiv = grcReportDiv || getReportLinkId();
        for (var i in grcReportDiv) {
            if (repId == i) return grcReportDiv[i];
        }
        return false;
    }

    //end of functionality to get div id of a linked report

	
	//functionality to hyperlink link id

    function getHyperLinkId() {
        var className = 'msai_hyperlink';
        var hasClassName = new RegExp("(?:^|\\s)" + className + "(?:$|\\s)");
		F.log('+hasClassName+' + hasClassName);
        var allElements = document.getElementsByTagName("A");
		F.log('+allElements+' + allElements);
        var results = [];
        var element;
        for (var i = 0;
        (element = allElements[i]) != null; i++) {
            var elementClass = element.className;
            if (elementClass && elementClass.indexOf(className) != -1 && hasClassName.test(elementClass)) {
                results[element.innerHTML] = element.parentNode;
                F.log('+element.innerHTML+' +element.innerHTML);
            }
        }
        return results;
    }
    //end of functionality to report link id
    //Start of functionality to get div id of a linked report

    function getHyperLinkDiv(repId) {
        grcHyperLinkDiv = grcHyperLinkDiv || getHyperLinkId();
		F.log('+grcHyperLinkDiv+' + grcHyperLinkDiv);
        for (var i in grcHyperLinkDiv) {
		F.log('+i+' + i);
		F.log('+repId+' + repId);
            if (repId == i) return grcHyperLinkDiv[i];
        }
        return false;
    }
	
/*
function includeORB()
{
	Ext.onReady(extReady);
	showHideReportLinks();	
	var mrDivId='MSAI_MULTI_DIV_'+F.ORB.getID(); 
	F.getObject(mrDivId).style.display='none';//replaced blank with none. sridhar
	

	if (F.REL_SOURCE_ID.read() == '')
	{
	F.REL_SOURCE_ID.write('NONE');
	F.REL_INST_ID.write('NONE',1);
	F.REL_SOURCE_OBJECT_ID.write('NONE'); //defaulting as if there is any field without any value, field is not appeared in xml
	}
	
	for (i=1;i<=F.ORB.getRowCount();i++)
	{
			//var OrgEntry = [F.TGT_OBJ_TYPE.read(i),F.TGT_OBJ_NAME.read(i),F.TGT_OBJ_ID.read(i),F.REL_COMMENTS.read(i),F.REL_VALID_FROM.read(i),F.REL_VALID_UNTIL.read(i),F.ROW_NUM.read(i),F.SELF_REL_TYPE.read(i),F.APPLICABLE_TO.read(i),F.ADDITIONAL_DETAILS.read(i)];
			//instead of row num value, write i value. Issue fixed for bug id 49199
//	alert(" F.TGT_OBJ_ID.read(i) -- " + F.TGT_OBJ_ID.read(i));
	if(F.TGT_OBJ_ID.read(i)!='')
{

   if (F.NATURE_OF_RELATIONSHIP)
   {
	
   var OrgEntry = [decodeHtmlContent(F.TGT_OBJ_TYPE.read(i)),F.TGT_OBJ_NAME.read(i),F.TGT_OBJ_ID.read(i),F.REL_COMMENTS.read(i),F.REL_VALID_FROM.read(i),F.REL_VALID_UNTIL.read(i),i,F.SELF_REL_TYPE.read(i),F.APPLICABLE_TO.read(i),F.ADDITIONAL_DETAILS.read(i),F.ORB_FORM_URL.read(i)
			,F.NATURE_OF_RELATIONSHIP.read(i),F.NATURE_OF_REL_PTOA.read(i),F.TYPE_OF_OWNER.read(i)];
   }
   else
   {
   var OrgEntry = [decodeHtmlContent(F.TGT_OBJ_TYPE.read(i)),F.TGT_OBJ_NAME.read(i),F.TGT_OBJ_ID.read(i),F.REL_COMMENTS.read(i),F.REL_VALID_FROM.read(i),F.REL_VALID_UNTIL.read(i),i,F.SELF_REL_TYPE.read(i),F.APPLICABLE_TO.read(i),F.ADDITIONAL_DETAILS.read(i),F.ORB_FORM_URL.read(i)];  
   }
   console.log('OrgEntry>>>>' + OrgEntry);
			relArr.push(OrgEntry);
			relStore.loadData(relArr);
}
	}

}
*/
function show_report_risk()
{
  #foreach ($report in $reports)
    #if (${report.name} == 'Add Risk(s)')
       getReport($report.metricId,$report.reportId);
    #end
  #end
}


function hideShowFormFields(hideShowFields,flag)

{

//alert(flag);
                
               
                for (var i = 0; i < hideShowFields.length; i ++ )
    {
                  if (flag=='Y'){
                   eval(hideShowFields[i]).hide();
                   }else
                   {
                   eval(hideShowFields[i]).show();
                   }
                                 
                }
          

}


function IsNumeric(strString1,lenStr)   {
		
		var strString =eval(strString1).read();
   //  check for valid numeric strings	
   	//alert('inSide IsNumeric   ' +strString)

   var strValidChars = "0123456789";
   var strChar;
   var blnResult = true;

   
   if (strString.length >lenStr && (lenStr.toString()).length>0){ 
	//alert('Please enter Numeric value of '+ lenStr+' character size');
		eval(strString1).write("");
		eval(strString1).setFocus();
		return false;

	
   }
		
   
   //  test strString consists of valid characters listed above
   for (i = 0; i < strString.length && blnResult == true; i++)
      {
      strChar = strString.charAt(i);
      if (strValidChars.indexOf(strChar) == -1)
         {
   //alert('Please enter Numeric value of '+ lenStr+' character size');
		eval(strString1).write("");
		eval(strString1).setFocus();
		return false;
         }
      }
   return blnResult;
   }
   

</script>
