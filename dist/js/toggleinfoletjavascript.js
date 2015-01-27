 
function toggleInfo(infoletType,infoletName, divID){

var mydata = JSON.parse(data);
var out="";






 for(var i = 0; i < mydata.length; i++) {
var Sub = mydata[i];
	
	

               
				if((Sub.id).localeCompare(infoletName)==0){
				
				if(("filter").localeCompare(infoletType)==0){
				out=Sub.filter;
				$('#'+divID+'_filterBtn').hide("slow");
				$('#'+divID+'_parametersBtn').show("slow");
				$('#'+divID+'_sqlBtn').show("slow");
				}else if(("parameters").localeCompare(infoletType)==0){
				out=Sub.parameters;
				$('#'+divID+'_parametersBtn').hide("slow");
				$('#'+divID+'_filterBtn').show("slow");
				$('#'+divID+'_sqlBtn').show("slow");
				}else if(("sql").localeCompare(infoletType)==0){
				out=Sub.sql;
				$('#'+divID+'_sqlBtn').hide("slow");
				$('#'+divID+'_parametersBtn').show("slow");
				$('#'+divID+'_filterBtn').show("slow");
				}
				
			}
					}
				

 
	
 document.getElementById(divID).innerHTML=out;
 $('.infolet-table').DataTable({
                    responsive: true
                });
 $("#"+divID+" div").fadeIn(2000);
  
};									


