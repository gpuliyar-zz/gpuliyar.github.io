
$(document).ready(function(){
$("#searchBox").keyup(function fun(e){
var searchField=document.getElementById("searchBox").value;
if(searchField==""){
$("#menuDivScroll li").show();
}else{
 $("#menuDivScroll li").hide();
 $("#searchBoxli").show();
}

if (e.keyCode === 32) {
            $(this).val($(this).val()+" ");
        }
var searchValue = $(this).val().toLowerCase(),
        matchSub = false;

     $('.nav-second-level').each(function() {

			var itemValue;

                var Sub = $(this).children('li').children('a');
				
				
                for(var i = 0; i < Sub.length; i++) {     //for SubMenu
                    itemValue = $.trim(Sub.eq(i).text()).toLowerCase();

                    if(itemValue.search(searchValue) >= 0) {

                        Sub.eq(i).show();
						Sub.eq(i).parent().show();
						
                        matchSub = true;
                    }
                    else{
                        Sub.eq(i).hide();
						Sub.eq(i).parent().hide();
                    }
                }
                
                if(matchSub) {
                    $(this).parent().show();
                    matchSub = false;
                }
                else {
                   $(this).parent().hide();
                }
           // }
            
        });


});

//#globalSearch
$("#uik").keyup(function fun(e){

//var globalField=document.getElementById("globalSearch").value;
alert('hi');
 
//x[0]="hi";
});


$("#globalSearch").keyup(function fun(e){
 var Sub = $("#DataTables_Table_0_filter").next('label');
 var elements = $("[aria-controls='DataTables_Table_0']"); 


 
//x[0]="hi";
});

});


