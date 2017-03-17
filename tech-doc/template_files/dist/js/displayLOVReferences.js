function displayData(lovId){

var out= lovReferences[lovId];
if(out!=null){
    document.getElementById('data-Modal').innerHTML=out;
}else{
    document.getElementById('data-Modal').innerHTML="<div class=\"modal-dialog\" style=\"width:1000px;overflow:auto;\"><div class=\"modal-content\"><div class=\"modal-header\"><button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-hidden=\"true\">&times;</button><h4 class=\"modal-title\" id=\"myModalLabel\">References</h4></div><div class=\"modal-body\" style=\"height:520px; overflow:auto;\"><br><h5> No References </h5>        </div>          </div>    <!-- /.modal-content --></div><!-- /.modal-dialog -->";
}
$(".tableData").mCustomScrollbar({
                    theme:"minimal",
                    mouseWheel:{ scrollAmount: "300px" }
                });
  
};