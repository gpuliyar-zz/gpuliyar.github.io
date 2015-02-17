 
function toggleInfo(returnType,infoletName,divID){

var out="";
var infoletDetails = infoletData[infoletName];
				
                if(("columns").localeCompare(returnType)==0){
                    out=infoletDetails.columns
                $('#'+divID+'_colBtn').addClass( "disabled" );
                $('#'+divID+'_queryBtn').removeClass( "disabled" );
				$('#'+divID+'_parametersBtn').removeClass( "disabled" );
				$('#'+divID+'_xmlBtn').removeClass( "disabled" );
				$('#'+divID+'_filterBtn').removeClass( "disabled" );
				$('#'+divID+'_referenceBtn').removeClass( "disabled" );
                }
				else if(("query").localeCompare(returnType)==0){
				out=infoletDetails.query;
                document.getElementById('sql-xml-Modal').innerHTML=out;
                hljs.initHighlighting.called = false;
                hljs.initHighlighting();
                $(".sql").mCustomScrollbar({
                    theme:"minimal",
                    mouseWheel:{ scrollAmount: "300px" }
                });
				$('#'+divID+'_queryBtn').removeClass( "disabled" );
				$('#'+divID+'_parametersBtn').removeClass( "disabled" );
				$('#'+divID+'_xmlBtn').removeClass( "disabled" );
				$('#'+divID+'_filterBtn').removeClass( "disabled" );
				$('#'+divID+'_referenceBtn').removeClass( "disabled" );
                $('#'+divID+'_colBtn').removeClass( "disabled" );
                return;
				}
				else if(("parameters").localeCompare(returnType)==0){
				out=infoletDetails.parameters;
				$('#'+divID+'_parametersBtn').addClass( "disabled" );
				$('#'+divID+'_queryBtn').removeClass( "disabled" );
				$('#'+divID+'_xmlBtn').removeClass( "disabled" );
				$('#'+divID+'_filterBtn').removeClass( "disabled" );
				$('#'+divID+'_referenceBtn').removeClass( "disabled" );
                $('#'+divID+'_colBtn').removeClass( "disabled" );
				}
				else if(("filters").localeCompare(returnType)==0){
				out=infoletDetails.filters;
				$('#'+divID+'_filterBtn').addClass( "disabled" );
				$('#'+divID+'_parametersBtn').removeClass( "disabled" );
				$('#'+divID+'_queryBtn').removeClass( "disabled" );
				$('#'+divID+'_xmlBtn').removeClass( "disabled" );
				$('#'+divID+'_referenceBtn').removeClass( "disabled" );
                $('#'+divID+'_colBtn').removeClass( "disabled" );
				}
				else if(("references").localeCompare(returnType)==0){
				out=infoletDetails.references;
				$('#'+divID+'_referenceBtn').addClass( "disabled" );
				$('#'+divID+'_parametersBtn').removeClass( "disabled" );
				$('#'+divID+'_queryBtn').removeClass( "disabled" );
				$('#'+divID+'_xmlBtn').removeClass( "disabled" );
				$('#'+divID+'_filterBtn').removeClass( "disabled" );
                $('#'+divID+'_colBtn').removeClass( "disabled" );
				}
				else{
				out=infoletDetails.xml;
				document.getElementById('sql-xml-Modal').innerHTML=out;
                hljs.initHighlighting.called = false;
                hljs.initHighlighting();
                $(".xml").mCustomScrollbar({
                    theme:"minimal",
                    mouseWheel:{ scrollAmount: "300px" }
                });
				$('#'+divID+'_queryBtn').removeClass( "disabled" );
				$('#'+divID+'_parametersBtn').removeClass( "disabled" );
				$('#'+divID+'_xmlBtn').removeClass( "disabled" );
				$('#'+divID+'_filterBtn').removeClass( "disabled" );
				$('#'+divID+'_referenceBtn').removeClass( "disabled" );
                $('#'+divID+'_colBtn').removeClass( "disabled" );
                return;
				}
				 
	
 document.getElementById(divID).innerHTML=out;
 $("#"+divID).fadeIn(1000);
  
};									
