 
function toggleInfo(returnType,tableName,divID){

var out="";
var dbTableDetails = dbTalbleData[tableName];
				
				if(("columns").localeCompare(returnType)==0){
				out=dbTableDetails.columns;
				$('#'+divID+'_colBtn').addClass( "disabled" );
				$('#'+divID+'_conBtn').removeClass( "disabled" );
				$('#'+divID+'_indBtn').removeClass( "disabled" );
				$('#'+divID+'_keyBtn').removeClass( "disabled" );
				}
				else if(("constraints").localeCompare(returnType)==0){
				out=dbTableDetails.constraints;
				$('#'+divID+'_conBtn').addClass( "disabled" );
				$('#'+divID+'_colBtn').removeClass( "disabled" );
				$('#'+divID+'_indBtn').removeClass( "disabled" );
				$('#'+divID+'_keyBtn').removeClass( "disabled" );
				}
				else if(("index").localeCompare(returnType)==0){
				out=dbTableDetails.index;
				$('#'+divID+'_indBtn').addClass( "disabled" );
				$('#'+divID+'_conBtn').removeClass( "disabled" );
				$('#'+divID+'_colBtn').removeClass( "disabled" );
				$('#'+divID+'_keyBtn').removeClass( "disabled" );
				}
				else{
				out=dbTableDetails.keys;
				$('#'+divID+'_keyBtn').addClass( "disabled" );
				$('#'+divID+'_indBtn').removeClass( "disabled" );
				$('#'+divID+'_conBtn').removeClass( "disabled" );
				$('#'+divID+'_colBtn').removeClass( "disabled" );
				}

 document.getElementById(divID).innerHTML=out;
 $("#"+divID).fadeIn(1000);
  
};									
