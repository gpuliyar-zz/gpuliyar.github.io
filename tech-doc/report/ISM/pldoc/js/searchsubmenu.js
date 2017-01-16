	
		function clearinfo(){
		document.getElementById("inputId").value="";
		
		$(".outerleftsubmenu span").show();
		$(".submenu").show();
		}
		$(document).ready(function(){
$("#inputId").keyup(function (e){
var sF=document.getElementById("inputId").value;

if(sF==""){
$(".outerleftsubmenu span").show();
$(".submenu").show();
}else{
 $(".outerleftsubmenu span").hide();
  $(".submenu").hide();
}
		
var searchValue = $(this).val().toLowerCase(),
        matchSub = false;

     $('.submenu').each(function() {

			var itemValue;

                var Sub = $(this).children('a');
				
				
                for(var i = 0; i < Sub.length; i++) {     //for SubMenu
                    itemValue = $.trim(Sub.eq(i).text()).toLowerCase();

                    if(itemValue.search(searchValue) >= 0) {

                        
						Sub.eq(i).parent().show();
						Sub.eq(i).parent().prevUntil('span').last().prev().show();
						
                        matchSub = true;
                    }
                    else{
                    
						Sub.eq(i).parent().hide();
						//Sub.eq(i).parent().prevUntil('span').last().prev().hide();
                    }
                }
                
                if(matchSub) {
				
                 //  $(this).prevUntil('span').last().prev().show();
                    matchSub = false;
                }
                else {
                //  $(this)..prevUntil('span').last().prev().hide();
				  
                }
          
            
        });


});



});