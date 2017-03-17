function displayRef(key){

var out= references[key];
    document.getElementById('data-Modal').innerHTML=out;

$(".tableData").mCustomScrollbar({
                    theme:"minimal",
                    mouseWheel:{ scrollAmount: "300px" }
                });
  
};