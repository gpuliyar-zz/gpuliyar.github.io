function displayData(tableName){

var out= dataTabletData[tableName];

document.getElementById('data-Modal').innerHTML=out;
$(".tableData").mCustomScrollbar({
                    theme:"minimal",
                    mouseWheel:{ scrollAmount: "300px" }
                });
  
};