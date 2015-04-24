$(document).ready(function(){
$(".anchorStyle").hover(function(){
	
   var id=$(this).attr('id');
   var res = id.split("_");
   var str1="#arrow_"
   var arrowid=str1.concat(res[1]);
   $(arrowid).removeClass("active");
   },
   function(){
   var id=$(this).attr('id');
   var res = id.split("_");
   var str1="#arrow_"
   var arrowid=str1.concat(res[1]);
   $(arrowid).addClass("active");
   });
});
