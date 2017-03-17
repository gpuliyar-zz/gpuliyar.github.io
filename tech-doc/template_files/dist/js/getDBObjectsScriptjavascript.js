 
function getDBObjScript(objectName){

var out= dbProgramsAndObjectsScriptData[objectName];

document.getElementById('sciptModal').innerHTML=out;
hljs.initHighlighting.called = false;
hljs.initHighlighting();
$(".sql").mCustomScrollbar({
                    theme:"minimal",
                    mouseWheel:{ scrollAmount: "300px" }
                });
  
};									
