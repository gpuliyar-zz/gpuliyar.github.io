$(document).ready(function(){

$("#searchAllInpField").keyup(function fun(e){
var searchAllInpField=document.getElementById("searchAllInpField").value;

if(searchAllInpField==""){
 $("#searchAllInpFieldBtn").addClass('disabled');

}else{
 $("#searchAllInpFieldBtn").removeClass('disabled');
}

});
var mydata = JSON.parse(data);
     
		 

	


$("#searchAllInpFieldBtn").click(function(){
	 
var searchAllInpField=document.getElementById("searchAllInpField");
var out="";

var searchValue = $(searchAllInpField).val().toLowerCase(),
        matchSub = false;

		
   for(var i = 0; i < mydata.length; i++) {

			var itemValue;

                var Sub = mydata[i];
				
				
                   //for SubMenu
                    itemValue = $.trim(Sub.name).toLowerCase();

                    if(itemValue.search(searchValue) >= 0) {
out+="<tr><td>"+Sub.name+"</td><td>"+"name"+"</td><td><a href="+Sub.reference_link+">"+Sub.reference_link+"</a></td></tr>";
                       
						
                        matchSub = true;
                    }
				itemValue = $.trim(Sub.age).toLowerCase();	
					
					if(itemValue.search(searchValue) >= 0) {
out+="<tr><td>"+Sub.age+"</td><td>"+"age"+"</td><td><a href="+Sub.reference_link+">"+Sub.reference_link+"</a></td></tr>";
                       
						
                        matchSub = true;
                    }
					itemValue = $.trim(Sub.sex).toLowerCase();
					if(itemValue.search(searchValue) >= 0) {
out+="<tr><td>"+Sub.sex+"</td><td>"+"sex"+"</td><td><a href="+Sub.reference_link+">"+Sub.reference_link+"</a></td></tr>";
                       
						
                        matchSub = true;
                    }
			
                   
                //end of inner loop
                
               
           // }
            
        }// loop for
		
		 if(matchSub) {
                   document.getElementById("dataLayout").innerHTML=out;
                }
                else {
				out+="<tr><td>"+"Not Found"+"</td><td>"+"Not Found"+"</td><td>"+"Not Found"+"</td></tr>";
                   document.getElementById("dataLayout").innerHTML=out;
                }
		
		});
		 
         


//end of outer loop
});