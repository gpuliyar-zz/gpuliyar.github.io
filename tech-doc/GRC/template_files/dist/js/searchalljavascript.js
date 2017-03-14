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
var name="";
var age="";
var sex="";

var out="";

var nameCount=0;
var ageCount=0;
var sexCount=0;

var searchValue = $(searchAllInpField).val().toLowerCase(),
        nmatchSub = false,
		amatchSub = false,
		smatchSub = false,
		
		matchSub=false;

		
   for(var i = 0; i < mydata.length; i++) {

			var itemValue;

                var Sub = mydata[i];
				
				
                   //for SubMenu
                    itemValue = $.trim(Sub.name).toLowerCase();

                    if(itemValue.search(searchValue) >= 0) {
					var n = itemValue.search(searchValue);
					
					var till=searchValue.length+n;
					if(n!=0){
					var prev = itemValue.substring(0, n);

					}
					if((itemValue.length -1)!= ((searchValue.length-1)+n)){
					var next = itemValue.substring((n+searchValue.length), itemValue.length);
					
					}
					
					if((n!=0) && ((itemValue.length -1)!= ((searchValue.length-1)+n))){
					var tot=prev+"<span style=color:hotpink>"+searchValue+"</span>"+next;
					}else if((n==0) && ((itemValue.length -1)!= ((searchValue.length-1)+n))){
					var tot="<span style=color:hotpink>"+searchValue+"</span>"+next;
					} else if((n!=0) && ((itemValue.length -1)== ((searchValue.length-1)+n))) {
					var tot=prev+"<span style=color:hotpink>"+searchValue+"</span>";
					} else{
					var tot="<span style=color:hotpink>"+searchValue+"</span>";
					}
					
name+="<li><a href="+Sub.reference_link+">"+tot+"</a></li><li class=divider></li>";		
                        nmatchSub = true;
						matchSub = true;
						nameCount++;
                    }
				itemValue = $.trim(Sub.age).toLowerCase();	
					
					if(itemValue.search(searchValue) >= 0) {
					
					var n = itemValue.search(searchValue);
					var till=searchValue.length+n;
					if(n!=0){
					var prev = itemValue.substring(0, n);

					}
					if((itemValue.length -1)!= ((searchValue.length-1)+n)){
					var next = itemValue.substring((n+searchValue.length), itemValue.length);
					
					}
					
					if((n!=0) && ((itemValue.length -1)!= ((searchValue.length-1)+n))){
					var tot=prev+"<span style=color:hotpink>"+searchValue+"</span>"+next;
					}else if((n==0) && ((itemValue.length -1)!= ((searchValue.length-1)+n))){
					var tot="<span style=color:hotpink>"+searchValue+"</span>"+next;
					} else if((n!=0) && ((itemValue.length -1)== ((searchValue.length-1)+n))) {
					var tot=prev+"<span style=color:hotpink>"+searchValue+"</span>";
					} else{
					var tot="<span style=color:hotpink>"+searchValue+"</span>";
					}
					
age+="<li><a href="+Sub.reference_link+">"+tot+"</a></li><li class=divider></li>";
                       ageCount++;
						matchSub = true;
                        amatchSub = true;
                    }
					itemValue = $.trim(Sub.sex).toLowerCase();
					if(itemValue.search(searchValue) >= 0) {
					
					var n = itemValue.search(searchValue);
					var till=searchValue.length+n;
					if(n!=0){
					var prev = itemValue.substring(0, n);

					}
					if((itemValue.length -1)!= ((searchValue.length-1)+n)){
					var next = itemValue.substring((n+searchValue.length), itemValue.length);
					
					}
					
					if((n!=0) && ((itemValue.length -1)!= ((searchValue.length-1)+n))){
					var tot=prev+"<span style=color:hotpink>"+searchValue+"</span>"+next;
					}else if((n==0) && ((itemValue.length -1)!= ((searchValue.length-1)+n))){
					var tot="<span style=color:hotpink>"+searchValue+"</span>"+next;
					} else if((n!=0) && ((itemValue.length -1)== ((searchValue.length-1)+n))) {
					var tot=prev+"<span style=color:hotpink>"+searchValue+"</span>";
					} else{
					var tot="<span style=color:hotpink>"+searchValue+"</span>";
					}
					
sex+="<li><a href="+Sub.reference_link+">"+tot+"</a></li><li class=divider></li>";
                       matchSub = true;
						sexCount++;
                        smatchSub = true;
                    }
			
                   
                //end of inner loop
                
               
           // }
            
        }// loop for
		
		 if(nmatchSub) {
		 name="<li class=dropdown><a class=dropdown-toggle data-toggle=dropdown href=#><strong>Name</strong><span style=margin-left:50px; color:goldenrod><em>"+nameCount+" found</em></span><i class=fa fa-caret-down></i></a><ul class=dropdown-menu dropdown-messages>"+name+"</ul></li>";
                  
                }
				
               
		 if(amatchSub) {
		 age="<li class=dropdown><a class=dropdown-toggle data-toggle=dropdown href=#><strong>Age</strong><span style=margin-left:50px; color:goldenrod><em>"+ageCount+" found</em></span><i class=fa fa-caret-down></i></a><ul class=dropdown-menu dropdown-messages>"+age+"</ul></li>";
                  
                }
               		
		 if(smatchSub) {
		 sex="<li class=dropdown><a class=dropdown-toggle data-toggle=dropdown href=#><strong>Sex</strong><span style=margin-left:50px; color:goldenrod><em>"+sexCount+" found</em></span><i class=fa fa-caret-down></i></a><ul class=dropdown-menu dropdown-messages>"+sex+"</ul></li>";
                 
                }
		if(matchSub){
		out=name+age+sex;
		document.getElementById("DataLayout").innerHTML=out;
		}else{
		out="<i style=color:brown>Data not found</i>";
		document.getElementById("DataLayout").innerHTML=out;
		}
				
                
		});
		 
         


//end of outer loop
});