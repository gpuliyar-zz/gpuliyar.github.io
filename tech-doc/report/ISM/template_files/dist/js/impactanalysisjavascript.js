var stringImpact;
var prevParentKey="";
var prevChildValue="";
var levelStar;
var levelPoint;
var loopCheck;
var currentLevel;
var currentRoot;
var prevLevelStar=1;
var prevLevelChildType="";
var flagStatus=false;
var tempHtml="";
function printDiv(di) {
	var oldstr = document.body.innerHTML;
	$("#impactFooter").hide();
	$("th").css("width", "5px !important");
	$("td").css("width", "5px !important");
	$("a").css("font-size", "10px");
	$("a").attr("href", "");
	$("td").css("font-size", "10px");
	$(".panel-body").css("margin-left", "-80px");
	var newStr = $("#modalIN").html();
	document.body.innerHTML = newStr;
	window.print();
	document.body.innerHTML = oldstr;
	$('.modal').modal('hide');
	$('.modal-backdrop.in').hide();
	//$('#myModal').modal();
	$('#myModal').modal('show');

	$("body").mCustomScrollbar("destroy");
	$("body").mCustomScrollbar({
		theme : "minimal",
		mouseWheel : {
			scrollAmount : "230px"
		}
	});
	$("#inputId").keyup(function (e) {
		keyUpFunc();
	});
	var oldIE;
	if ($('html').is('.ie6, .ie7, .ie8')) {
		oldIE = true;
	}

	if (oldIE) {
		location.reload();
	} else {}
	$('#myModal').on('shown.bs.modal', function () {
		$("body").mCustomScrollbar("disable");
	});
	$('#myModal').on('hidden.bs.modal', function () {
		$("body").mCustomScrollbar("update");
	});
	$('.tooltip-demo').tooltip({
		selector : "[data-toggle=tooltip]",
		container : "body"
	});
}

function checkHref(str) {
	var x;
	if (str.indexOf('|') === -1) {
		x = "0";
	} else {
		x = str;
	}
	return x;
}
function getDFDFCollspan(obj) {
	if (!obj[0]) {
		var objArr = $.makeArray(Object.keys(obj));
		var collspan = [];
		for (i = 0; i < objArr.length; i++) {
			var obj1 = obj[objArr[i]];
			var objtemp = $.makeArray(Object.keys(obj1));
			var tempCount = 0;
			for (j = 0; j < objtemp.length; j++) {
				var obj2 = obj1[objtemp[j]];
				var objtemp1 = $.makeArray(Object.keys(obj2));
				tempCount = tempCount + objtemp1.length;
			}
			collspan[i] = tempCount;
		}
	}
	return collspan;
}
function getDOLength(obj) {
	if (!obj[0]) {
		var objArr = $.makeArray(Object.keys(obj));
		var dolength = 0;
		for (i = 0; i < objArr.length; i++) {
			var obj1 = obj[objArr[i]];
			var objtemp = $.makeArray(Object.keys(obj1));
			for (j = 0; j < objtemp.length; j++) {
				++dolength;
			}
		}
	}
	return dolength;
}
function getDOStack(obj) {
	if (!obj[0]) {
		var objArr = $.makeArray(Object.keys(obj));
		var dostack = [];
		var count = 0;
		for (i = 0; i < objArr.length; i++) {
			var obj1 = obj[objArr[i]];
			var objtemp = $.makeArray(Object.keys(obj1));
			for (j = 0; j < objtemp.length; j++) {
				dostack[count++] = objtemp[j];
			}
		}
	}
	return dostack;
}

var jsonObject;
function countofRegionFunc(arr) {
	var count = 0;
	for (i = 0; i < arr.length; i++) {
		count = count + parseInt(arr[i]);
	}
	return count;
}
function makeRegionArray(obj) {
	if (!obj[0]) {
		var objArr1 = $.makeArray(Object.keys(obj));
		var dataobject = objArr1.length;
		var region = [];
		var count = 0;
		for (i = 0; i < dataobject; i++) {
			var obj1 = obj[objArr1[i]];
			var objtemp = $.makeArray(Object.keys(obj1));
			for (j = 0; j < objtemp.length; j++) {
				var tempobj1 = obj1[objtemp[j]];
				var tempobjArr1 = $.makeArray(Object.keys(tempobj1));
				for (k = 0; k < tempobjArr1.length; k++) {
					region[count++] = tempobjArr1[k];
				}
			}

		}

	}
	return region;
}

function makeTypeArray(obj) {
	if (!obj[0]) {
		var objArr1 = $.makeArray(Object.keys(obj));
		var dataobject = objArr1.length;
		var typeFilter = [];
		var count = 0;
		var tempm = "";
		for (i = 0; i < dataobject; i++) {
			var obj1 = obj[objArr1[i]];
			var objtemp = $.makeArray(Object.keys(obj1));
			for (j = 0; j < objtemp.length; j++) {
				var tempobj1 = obj1[objtemp[j]];
				var tempobjArr1 = $.makeArray(Object.keys(tempobj1));
				for (k = 0; k < tempobjArr1.length; k++) {
					var temptype = tempobj1[tempobjArr1[k]];
					tempm = "";
					for (m = 0; m < temptype.length; m++) {

						tempm = tempm + temptype[m] + ":";
					}
					typeFilter[count++] = tempm;
				}
			}

		}

	}
	return typeFilter;
}
function getDataFormDeatils(obj) {
	var x;
	if (!obj[0]) {
		var objArr1 = $.makeArray(Object.keys(obj));
		var dataobject = objArr1.length;
	}
	return dataobject;
}

function getDFColumSpanDeatils(obj, name) {
	var x;
	if (!obj[0]) {
		obj = obj[name];
		var objArr1 = $.makeArray(Object.keys(obj));
		var dataobject = objArr1.length;
		return dataobject;
	}
}
function getDODeatils(obj) {
	var x;
	if (!obj[0]) {
		var objArr1 = $.makeArray(Object.keys(obj));
		var dataobject = objArr1.length;
		var region = $.makeArray(Object.keys(obj));

		for (i = 0; i < dataobject; i++) {
			var obj1 = obj[objArr1[i]];
			var objtemp = $.makeArray(Object.keys(obj1));
			count = 0;
			for (j = 0; j < objtemp.length; j++) {
				++count;
			}
			region[i] = count;

		}
		x = region;
	}
	return x;

}
function getDOCollspan(obj) {
	if (!obj[0]) {
		var objArr1 = $.makeArray(Object.keys(obj));
		var dataobject = [];
		var count = 0;
		for (i = 0; i < objArr1.length; i++) {
			var obj1 = obj[objArr1[i]];
			var objtemp = $.makeArray(Object.keys(obj1));
			for (j = 0; j < objtemp.length; j++) {
				var obj2 = obj1[objtemp[j]];
				var objtemp1 = $.makeArray(Object.keys(obj2));
				dataobject[count++] = objtemp1.length;
			}
		}
	}
	return dataobject;
}

function getDataObject(obj, name, l) {
	obj = obj[name];
	var objArr1 = $.makeArray(Object.keys(obj));
	return objArr1[l];
}
function getDataForm(obj, l) {
	var objArr1 = $.makeArray(Object.keys(obj));
	return objArr1[l];
}
function collaspeTable(x){
	debugger;
	if(x.className==="root fa-root-plus-square"){
	constructChunk(x.innerText.trim(),$("#childNameID").text().trim(),x);
	}else if(x.className=="root fa-root-minus-square"){
	$("#modalBodyID").children().remove();
	$("#modalBodyID").html(tempHtml);
	}else{
	collaspeTableShow(x);
	}
}
function constructChunk(childType,childName,x) {
	tableStruct="";
	var jsonObject = constructImpactObject1(childType, childName);
	var objArr = $.makeArray(Object.keys(jsonObject));
	for (i = 0; i < objArr.length; i++) {
		var indentSpace = objArr[i].split('.');
		tableStruct = tableStruct + '<div style="margin-left:' + ((indentSpace[0] + 1) * 6) + 'px;border-bottom:1px solid gainsboro;">';
		var subjsonObject = jsonObject[objArr[i]];
		var subobjArr = $.makeArray(Object.keys(subjsonObject));
		if(i==0){
		tableStruct = tableStruct + '<span onclick="collaspeTable(this)" class="root fa-root-minus-square" style="color: #898989;font-size: medium;">' + ' ' + subobjArr[0] + '</span>';
		}else{
		tableStruct = tableStruct + '<span onclick="collaspeTable(this)" class="fa fa-minus-square" style="color: #898989;font-size: medium;">' + ' ' + subobjArr[0] + '</span>';
		}
		var jsonAttributes = subjsonObject[subobjArr[0]];
		var jsonAttributesArr = Object.keys(jsonAttributes[0]);
		tableStruct = tableStruct + '<div class="table-responsive">' +
			'<table class=" table-hover" style="margin-bottom:2px!important;width:100%;">' +
			'<thead>' +
			'<tr>';
		for (j = 0; j < jsonAttributesArr.length - 1; j++) {
			tableStruct = tableStruct + '<th>' + jsonAttributesArr[j] + '</th>';
		}
		tableStruct = tableStruct + '</tr></thead><tbody>';
		for (l = 0; l < jsonAttributes.length; l++) {
			tableStruct = tableStruct + '<tr>';
			jsonAttributesArr = Object.keys(jsonAttributes[l]);
			for (k = 0; k < jsonAttributesArr.length - 1; k++) {
				if (jsonAttributesArr[k] == 'Title') {
					var tempTitle=jsonAttributes[l][jsonAttributesArr[k]].split(".");
					if(tempTitle.length!=2){
					var titleTag=jsonAttributes[l][jsonAttributesArr[k]];
					}else{
						var titleTag="<b>"+tempTitle[0]+"</b>"+"."+tempTitle[1];
					}
					if (jsonAttributes[l]["href"] == "#") {
						tableStruct = tableStruct + '<td>' + titleTag + '</td>';

					} else {
						tableStruct = tableStruct + '<td><a target="newtab" href=' + jsonAttributes[l][jsonAttributesArr[jsonAttributesArr.length - 1]] + '>' + titleTag + '</td>';
					}
				} else {
					tableStruct = tableStruct + '<td>' + jsonAttributes[l][jsonAttributesArr[k]] + '</td>';
				}
			}
			tableStruct = tableStruct + '</tr>';
		}
		tableStruct = tableStruct + '</tbody></table>';
		tableStruct = tableStruct + '</div></div>';
	}
	
	debugger;
	$("#modalBodyID").children().remove();
	$("#modalBodyID").html(tempHtml);
	var m=$("span").filter(":contains("+x.innerText+")")[0];
	m.parentElement.outerHTML=tableStruct;
	
	$('.tooltip-demo').tooltip({
		selector : "[data-toggle=tooltip]",
		container : "body"
	});
//collapseall();
}
function collaspeTableShow(x) {

	var status;
	var d = x.nextSibling;
	if (x.className == "fa fa-minus-square") {
		$(d).slideUp('0.5');
		x.className = "fa fa-plus-square";
		status = "hide";
	} else {
		$(d).slideDown('0.5');
		status = "show";
		x.className = "fa fa-minus-square";

	}

	var spanParent = x.parentNode;
	var marginLeftValue = spanParent.style.marginLeft;
	var mValue = parseInt(marginLeftValue.slice(0, (marginLeftValue.length - 3)));
	flag = 1;
	var nextDiv = spanParent;
	try {
		while (flag == 1) {
			nextDiv = nextDiv.nextSibling;
			var nextmarginLeftValue = nextDiv.style.marginLeft;
			var nValue = parseInt(nextmarginLeftValue.slice(0, (nextmarginLeftValue.length - 3)));
			if (nValue == mValue || nValue < mValue) {
				flag == false;
				return false;
			} else {
				if (status == "hide") {
					$(nextDiv).slideUp('0.5');

				} else {
					$(nextDiv).slideDown('0.5');

				}

			}
		}
	} catch (err) {}

}
function collapseall() {
	$(".fa-minus-square,.fa-plus-square").parent().each(function () {
		if ($(this).css("margin-left") == "66px") {
			$(this).children('span').removeClass("fa fa-minus-square").addClass("fa-plus-square");
			//.addClass( "fa-plus-square" );
			$(this).children('div').slideUp('0.5');

		} else {
			$(this).children('span').removeClass("fa fa-minus-square").addClass("fa fa-plus-square");
			$(this).children('div').slideUp('0.5');
			$(this).slideUp('0.5');
		}

	});
	$("#colID").hide();
	$("#exID").show();
}
function expandall() {
	$(".fa-minus-square,.fa-plus-square").parent().each(function () {
		//if($(this).css("margin-left")=="126px"||$(this).css("margin-left")=="186px"||$(this).css("margin-left")=="66px"){
		$(this).children('span').removeClass("fa fa-plus-square").addClass("fa fa-minus-square");
		$(this).children('div').slideDown('0.5');
		$(this).slideDown('0.1');

		//}

	});
	$("#exID").hide();
	$("#colID").show();
}
function dsmodal(x) {
	var dsStr = x.title.split(",");

	var infoletName = dsStr[1];
	if (dsStr[1].indexOf("|") != -1) {
		var searchtemp1 = dsStr[1].split("|");
		dsStr[1] = searchtemp1[0];
	}

	//var infoletName="abc";
	var tableStruct = '<div class="modal-header">' +
		'<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>' +
		'<h4 class="modal-title" id="myModalLabel"> IMPACT OF ' + dsStr[0] + '<span style="color:rgb(211, 92, 92);"> ' + searchtemp1[1] + '</span>'+'<span style="display:none" id="childNameID">'+searchtemp1[0]+'</span>'+
		'<div class="modalHead collaspeDiv fa tooltip-demo" style="display: none">' +
		'<img  id="colID" width="32px" onclick="collapseall()" class="collapseClass" src="../template_files/dist/icons/collapse.png" data-toggle="tooltip" data-placement="top" title="Collapse All"></span>' +
		'<img id="exID" width="32px" onclick="expandall()" style="display:none" class="expandClass" src="../template_files/dist/icons/expand1.png" data-toggle="tooltip" data-placement="top" title="Expand All"></span></div>' +
		' </div>' +
		'</h4>' +

		'<div class="modal-body">' +
		'<div class="panel panel-default">' +

		'<div id="modalBodyID" class="panel-body">';

	//var jsonObject = data[dsStr[0]][searchtemp1[0]];
	var jsonObject = constructImpactObject(dsStr[0], searchtemp1[0]);
	var objArr = $.makeArray(Object.keys(jsonObject));
	for (i = 0; i < objArr.length; i++) {
		//var indentSpace=objArr[i].slice(6,objArr[i].length);
		var indentSpace = objArr[i].split('.');
		tableStruct = tableStruct + '<div style="margin-left:' + ((indentSpace[0] + 1) * 6) + 'px;border-bottom:1px solid gainsboro;">';
		var subjsonObject = jsonObject[objArr[i]];
		var subobjArr = $.makeArray(Object.keys(subjsonObject));
		tableStruct = tableStruct + '<span onclick="collaspeTable(this)" class="root fa-root-plus-square" style="color: #898989;font-size: medium;">' + ' ' + subobjArr[0] + '</span>';
		var jsonAttributes = subjsonObject[subobjArr[0]];
		var jsonAttributesArr = Object.keys(jsonAttributes[0]);
		tableStruct = tableStruct + '<div class="table-responsive">' +
			'<table class=" table-hover" style="margin-bottom:2px!important;width:100%;">' +
			'<thead>' +
			'<tr>';
		for (j = 0; j < jsonAttributesArr.length - 1; j++) {
			tableStruct = tableStruct + '<th>' + jsonAttributesArr[j] + '</th>';
		}
		tableStruct = tableStruct + '</tr></thead><tbody>';
		for (l = 0; l < jsonAttributes.length; l++) {
			tableStruct = tableStruct + '<tr>';
			jsonAttributesArr = Object.keys(jsonAttributes[l]);
			for (k = 0; k < jsonAttributesArr.length - 1; k++) {
				if (jsonAttributesArr[k] == 'Title') {
					if (jsonAttributes[l]["href"] == "#") {
						tableStruct = tableStruct + '<td>' + jsonAttributes[l][jsonAttributesArr[k]] + '</td>';

					} else {
						tableStruct = tableStruct + '<td><a target="newtab" href=' + jsonAttributes[l][jsonAttributesArr[jsonAttributesArr.length - 1]] + '>' + jsonAttributes[l][jsonAttributesArr[k]] + '</td>';
					}
				} else {
					tableStruct = tableStruct + '<td>' + jsonAttributes[l][jsonAttributesArr[k]] + '</td>';
				}
			}
			tableStruct = tableStruct + '</tr>';
		}
		tableStruct = tableStruct + '</tbody></table>';
		tableStruct = tableStruct + '</div></div>';
	}

	tableStruct = tableStruct + '</div>' +
		'</div>' +
		'</div>' +
		'<div id="impactFooter" class="modal-footer">' +
		'<button type="button" class="btn btn-primary" onclick="printDiv(this)">Print</button>' +
		'</div>';
	$('#modalIN').html(tableStruct);
	$('#myModal').modal('show');
	$('.tooltip-demo').tooltip({
		selector : "[data-toggle=tooltip]",
		container : "body"
	});
//collapseall();
tempHtml=$("#modalBodyID").html();
}

function constructImpactObject1(parentkey, childvalue) {
levelStar = 1;
levelPoint = 1;
loopCheck=[];
stringImpact=inFlowerBraces("");
prevLevelChildType="";
constructImpactObjectRecursive1(currentRoot, childvalue,true,parentkey);
stringImpact=outFlowerBraces(stringImpact);

return JSON.parse(stringImpact);
}
function constructImpactObject(parentkey, childvalue) {
currentRoot=parentkey;
levelStar = 1;
levelPoint = 1;
loopCheck=[];
stringImpact=inFlowerBraces("");
cloudClick(parentkey, childvalue);
stringImpact=outFlowerBraces(stringImpact);

return JSON.parse(stringImpact);
}
function constructImpactObjectRecursive12(objectType, objectName,statusFlow,assignMe) {



	//debugger;
	try {
		var parentTypeData = data[objectType];
		if(parentTypeData !=null){
			
			var parentNameData = parentTypeData[objectName];
			/*if(statusFlow){
			parentNameData=[assignMe];
			}*/
				
				
			if(parentNameData!=null){
			
		for(var childType in parentNameData){
				if(statusFlow===true&&childType==assignMe||statusFlow===false){
				var childTypeData = parentNameData[childType];
				if(childTypeData!=null){
				for(var childName in childTypeData){
					if(continueLoop(childType, childName)){
				//	if(Object.keys(childTypeData)[0] !=childName){
				//	stringImpact = joinObject(stringImpact);
				//	}else
					
					if(stringImpact.slice(-1)!=","&&stringImpact!=="{"){
					stringImpact = joinObject(stringImpact);
					}
					
					/*if(levelStar==1&&Object.keys(childTypeData).length==1){
					stringImpact = joinObject(stringImpact);	
					}*/
					var details = childTypeData[childName];
					var objectImpChildVal = $.makeArray(Object.keys(details[0]));
					if(levelStar==1){
						currentLevel=levelPoint;
					}
					if(levelStar==prevLevelStar&&prevLevelChildType===childType){
						
					}else{
					stringImpact = stringImpact+includeAssign((appendQuotes(concatLevel(levelStar,levelPoint++))), inFlowerBraces(""));
					stringImpact = stringImpact + includeAssign(appendQuotes(childType), "");
					stringImpact = stringImpact + inSquareBraces("");
					}
					constructAssignment(details,objectImpChildVal,childType,childName);
					
					
					prevLevelChildType=childType;
						prevLevelStar=levelStar;
					if((getRefrenceStatus(childType,childName))){
					flagStatus=true;
					stringImpact = outSquareBraces(stringImpact);
					stringImpact = outFlowerBraces(stringImpact);
					//stringImpact = joinObject(stringImpact);
					prevLevelChildType=childType;
						prevLevelStar=levelStar;
						levelStar++;
						constructImpactObjectRecursive1(childType, childName,false,"");
						//stringImpact = stringImpact + constructImpactObjectLoop(parentNameData[i], [childType[j]]);
						//stringImpact = outSquareBraces(stringImpact);
					}else{
						if(Object.keys(childTypeData)[Object.keys(childTypeData).length-1] ===childName)
						{
							stringImpact = outSquareBraces(stringImpact);
							stringImpact = outFlowerBraces(stringImpact);
						}else if(prevLevelStar!==levelStar&&prevLevelChildType!==childType){
					stringImpact = outSquareBraces(stringImpact);
					stringImpact = outFlowerBraces(stringImpact);
					}
						
					}
				//	if (j + 1 != childType.length) {
					

				//} 
				
				}
					if(stringImpact.slice(-2)!=="]}"&&Object.keys(childTypeData)[Object.keys(childTypeData).length-1] ===childName){
					stringImpact = outSquareBraces(stringImpact);
					stringImpact = outFlowerBraces(stringImpact);
					}
				}
				
					
				//		if (i + 1 != parentNameData.length) {
					
					

				//}else{
					//stringImpact = outFlowerBraces(stringImpact);
			//	}
		}	
				}
			}
		}
	}
	} catch (err) {
		
	}
	levelStar = 1;
	return stringImpact;
	//return JSON.parse(stringImpact);
}
function constructImpactObjectRecursive1(objectType, objectName,statusFlow,assignMe) {
var temp="";
var lStatus;


	//debugger;
	try {
		var parentTypeData = data[objectType];
		if(parentTypeData !=null){
			
			var parentNameData = parentTypeData[objectName];
			/*if(statusFlow){
			parentNameData=[assignMe];
			}*/
				
				
			if(parentNameData!=null){
			
		for(var childType in parentNameData){
				if(statusFlow===true&&childType==assignMe||statusFlow===false){
				var childTypeData = parentNameData[childType];
				if(childTypeData!=null){
				for(var childName in childTypeData){
					
					if(stringImpact.slice(-1)!=","&&stringImpact!=="{"){
					stringImpact = joinObject(stringImpact);
					}
					if(temp.slice(-1)!=","&&temp!==""){
					temp = joinObject(temp);
					}
					var details = childTypeData[childName];
					var objectImpChildVal = $.makeArray(Object.keys(details[0]));
					if(levelStar==1){
						currentLevel=levelPoint;
					}
					//if(levelStar==prevLevelStar&&prevLevelChildType===childType){
						
					//}else{
					
					//}
					temp=temp+constructAssignment(details,objectImpChildVal,objectType,objectName,childType,childName);
					lStatus=levelStar;
					if((getRefrenceStatus(childType, childName))&&continueLoop(childType, childName)){
					
					stringImpact = stringImpact+includeAssign((appendQuotes(concatLevel(levelStar++,levelPoint++))), inFlowerBraces(""));
					stringImpact = stringImpact + includeAssign(appendQuotes(childType), "");
					stringImpact = stringImpact + inSquareBraces("");
					stringImpact = stringImpact + temp;
					stringImpact = outSquareBraces(stringImpact);
					stringImpact = outFlowerBraces(stringImpact);
					temp="";
					constructImpactObjectRecursive1(childType, childName,false,"");
					}
					levelStar=lStatus;
				
				
					if(stringImpact.slice(-2)!=="]}"&&Object.keys(childTypeData)[Object.keys(childTypeData).length-1] ===childName){
					stringImpact = stringImpact+includeAssign((appendQuotes(concatLevel(levelStar,levelPoint++))), inFlowerBraces(""));
					stringImpact = stringImpact + includeAssign(appendQuotes(childType), "");
					stringImpact = stringImpact + inSquareBraces("");
					stringImpact = stringImpact + temp;
					stringImpact = outSquareBraces(stringImpact);
					stringImpact = outFlowerBraces(stringImpact);
					temp="";
					
					}
				}
				
					
				
		}	
				}
			}
		}
	}
	} catch (err) {
		
	}
	levelStar = lStatus;
	return stringImpact;
	//return JSON.parse(stringImpact);
}
function constructImpactObjectRecursive(objectType, objectName) {



	//debugger;
	try {
		var parentTypeData = data[objectType];
		if(parentTypeData !=null){
			
			var parentNameData = parentTypeData[objectName];
			if(parentNameData!=null){
			
		for(var childType in parentNameData){
				
				var childTypeData = parentNameData[childType];
				if(childTypeData!=null){
				for(var childName in childTypeData){
					if(continueLoop(childType, childName)){
				//	if(Object.keys(childTypeData)[0] !=childName){
				//	stringImpact = joinObject(stringImpact);
				//	}else
					
					if(stringImpact.slice(-1)!=","&&stringImpact!=="{"){
					stringImpact = joinObject(stringImpact);
					}
					
					/*if(levelStar==1&&Object.keys(childTypeData).length==1){
					stringImpact = joinObject(stringImpact);	
					}*/
					var details = childTypeData[childName];
					var objectImpChildVal = $.makeArray(Object.keys(details[0]));
					
					stringImpact = stringImpact+includeAssign((appendQuotes(concatLevel(levelStar,levelPoint++))), inFlowerBraces(""));
					stringImpact = stringImpact + includeAssign(appendQuotes(childType), "");
					stringImpact = stringImpact + inSquareBraces("");
					constructAssignment(details,objectImpChildVal,childType,childName);
					
					
					
					if((getRefrenceStatus(childType,childName))){
					stringImpact = outSquareBraces(stringImpact);
					stringImpact = outFlowerBraces(stringImpact);
					//stringImpact = joinObject(stringImpact);
						levelStar++;
						constructImpactObjectRecursive(childType, childName);
						//stringImpact = stringImpact + constructImpactObjectLoop(parentNameData[i], [childType[j]]);
						//stringImpact = outSquareBraces(stringImpact);
					}else{
						stringImpact = outSquareBraces(stringImpact);
		
				stringImpact = outFlowerBraces(stringImpact);	
					}
				//	if (j + 1 != childType.length) {
					

				//} 
				
				}
				
				}
				
					
				//		if (i + 1 != parentNameData.length) {
					
					

				//}else{
					//stringImpact = outFlowerBraces(stringImpact);
			//	}
		}	
			}
		}
	}
	} catch (err) {
		
	}
	levelStar = 1;
	return stringImpact;
	//return JSON.parse(stringImpact);
}
function cloudClick(objectType, objectName) {



	//debugger;
	try {
		var parentTypeData = data[objectType];
		if(parentTypeData !=null){
			
			var parentNameData = parentTypeData[objectName];
			if(parentNameData!=null){
		
		for(var childType in parentNameData){
		if(stringImpact.slice(-1)!=","&&stringImpact.slice(-1)==="}"){
					stringImpact = joinObject(stringImpact);
				}
				
			stringImpact = stringImpact+includeAssign((appendQuotes(concatLevel(levelStar,levelPoint++))), inFlowerBraces(""));	
			if(stringImpact.slice(-1)!=","&&stringImpact.slice(-1)!="{"){
					stringImpact = joinObject(stringImpact);
				}
				stringImpact = stringImpact + includeAssign(appendQuotes(childType), "");
				stringImpact = stringImpact + inSquareBraces("");
				stringImpact = stringImpact+inFlowerBraces("");
				stringImpact = stringImpact +"\"Null\":\"Null\"";
				stringImpact = outFlowerBraces(stringImpact);
				
				stringImpact = outSquareBraces(stringImpact);
				stringImpact = outFlowerBraces(stringImpact);
				
				levelStar = 1;
				}
				
				
			}
		}
	}
	 catch (err) {
		
	}
	
	return stringImpact;
}

function inFlowerBraces(istr) {
	return "{" + istr;
}
function outFlowerBraces(istr) {
	return istr + "}";
}
function joinObject(s) {
	return s + ",";
}
function inSquareBraces(istr) {
	return "[" + istr;
}
function outSquareBraces(istr) {
	return istr + "]";
}
function includeAssign(s1, s2) {
	return s1 + ":" + s2;
}
function concatLevel(star, point) {
	return star+"."+point;
}
function appendQuotes(s) {
	return  "\""+s+"\"";
}
function replaceAll(find, replace, str) {
  return str.replace(new RegExp(find, 'g'), replace);
}

function replaceQuotes(s){
    return replaceAll("\"", " ",s);
}
function getRefrenceStatus(p,c){
	
	var x=false;
	try{
	if ((data[p][c])) {
		x=true;
	}
	}catch (err){
		x=false;
	}
	return x;
}
function continueLoop(parentkey, childvalue) {
	
	cvCount=0;
	var y=true;
	var d=data[parentkey][childvalue]
	try{
	
	for(var cv in d){
	//	++cvCount;
	//	count=0;
		var pd = d[cv];
for(i=0;i<loopCheck.length;i++){
	var temp=loopCheck[i].toString().split(":");
//if(temp[0]==childvalue&&temp[1]==cv&&temp[2]==Object.keys(pd)[0]&&temp[3]==currentLevel){
//	++count;
	if(temp[0]==parentkey&&temp[1]==childvalue&&temp[2]==cv&&temp[3]==currentLevel){
		
		y=false;

}
}
}


	}catch(err){
		alert(err);
	}
	//if(cvCount>1){
	//y=false;	
	//}
	return y;
}
function getRootLevelStatus(childType) {
	var y=true;
	try{
	
for(i=0;i<loopCheck.length;i++){
	var temp=loopCheck[i].toString().split(":");
if(temp[0]==childType&&temp[2]==1){
y=false;
break;
}

}

	}catch(err){
		alert(err);
	}
	return y;
}


function constructAssignment(dataParent,details,objType,objName,childType,childName){
	var stringImpact1="";
		childLen = dataParent.length;
		loopCheck.push([objType+":"+objName+":"+childType+":"+currentLevel]);			
	for (m = 0; m < childLen; m++) {
						stringImpact1 = stringImpact1 + inFlowerBraces("");
						for (k = 0; k < details.length; k++) {
							var temp=replaceQuotes(dataParent[m][details[k]]);
							stringImpact1 = stringImpact1 + includeAssign(appendQuotes(details[k]), appendQuotes(temp));
							if (k + 1 != details.length) {
								stringImpact1 = joinObject(stringImpact1);
							} else {
								stringImpact1 = outFlowerBraces(stringImpact1);
							}
						}
						if (m + 1 != childLen) {
							stringImpact1 = joinObject(stringImpact1);
						}
					}	
return stringImpact1;
}
function dsmodal1(x) {
	var dsStr = x.title.split(",");
	var jsonObject = data[dsStr[0]][dsStr[1]];

	var objArr = $.makeArray(Object.keys(jsonObject));

	alert(objArr);
	if (dsStr[1].indexOf("|") != -1) {
		var searchtemp1 = dsStr[1].split("|");
		dsStr[1] = searchtemp1[0];
	}
	var tableStruct = '<div class="modal-header">' +
		'<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>' +
		'<h4 class="modal-title" id="myModalLabel">' + dsStr[1] + '</h4>' +
		' </div>' +
		'<div class="modal-body">' +
		'<div class="panel panel-default">';

	for (i = 0; i < objArr.length; i++) {
		tableStruct = tableStruct + '<div class="panel-heading">' +
			objArr[i] +
			'</div>' +
			'<div class="panel-body">' +
			'<div class="table-responsive">' +
			'<table class="table  table-bordered table-hover">' +
			'<thead>' +
			'<tr>' +
			'<th>Data Form</th>' +
			'<th>Data Object</th>' +
			'<th>Region</th>' +
			'<th>Field</th>' +
			'</tr>' +
			'</thead>' +
			'<tbody>';
		if (jsonObject[0]) {
			var objArr1 = jsonObject;
			var arrObj1 = String(objArr);
		} else {
			jsonObject = jsonObject[objArr[i]];
			if (jsonObject[0]) {
				var objArr1 = jsonObject;
				var arrObj1 = String(objArr);
			} else {
				var objArr1 = $.makeArray(Object.keys(jsonObject));
				var arrObj1 = String(objArr);
			}
			if (objArr1[i].indexOf("|") != -1) {
				var searchtemp = objArr1[i].split("|");
				objArr1[i] = searchtemp[0];
			}
			traverseObject = impact_exta_inf_df[objArr1[i]];
			if (traverseObject[0]) {
				var objArr2 = traverseObject;

			} else {
				var objArr2 = $.makeArray(Object.keys(traverseObject));

			}
			var DFLength = objArr2.length;
			var DFStack = objArr2;
			var DFCollspan = getDFDFCollspan(traverseObject);

			var DOLength = getDOLength(traverseObject);
			var DOStack = getDOStack(traverseObject);
			var DOCollspan = getDOCollspan(traverseObject);

			var RegionStack = makeRegionArray(traverseObject);
			var TypeStack = makeTypeArray(traverseObject);

			var DFPrint = 0;
			var DOPrint = 0;
			var currDF = 0;
			var currDO = 0;
			//var dataObjectDeatils=getDataFormDeatils(traverseObject);
			//var regionDeatils=getColumSpanDeatils(traverseObject);
			//var DODeatils=getDODeatils(traverseObject);
			//countofRegion=countofRegionFunc(regionDeatils);
			//countofDataObject=countofDataObjectFunc(regionDeatils);

			for (l = 0; l < RegionStack.length; l++) {

				/*if(l==0){
				tableStruct=tableStruct+'<tr><td rowspan='+regionDeatils.length+'>'+objArr1[l]+'</td>';
				}*/
				tableStruct = tableStruct + '<tr>';
				if (l >= currDF) {
					if (DFPrint < DFLength) {
						//var tmpDF=getDataForm(traverseObject,l);
						//var dataFormDeatils=getDFColumSpanDeatils(traverseObject,tmpDF);
						tableStruct = tableStruct + '<td rowspan=' + DFCollspan[DFPrint] + '>' + DFStack[DFPrint++] + '</td>';
						currDF = DFCollspan[DFPrint - 1] + l;
					}

				}
				if (l >= currDO) {
					if (DOPrint < DOLength) {
						//var tmpDO=getDataObject(traverseObject,currDF,l);
						tableStruct = tableStruct + '<td rowspan=' + DOCollspan[DOPrint] + '>' + DOStack[DOPrint++] + '</td>';
						currDO = DOCollspan[DOPrint - 1] + l;
					}
				}

				//	var tmpRegion=getDataObject(traverseObject,l);
				tableStruct = tableStruct + '<td>' + RegionStack[l] + '</td>';

				tableStruct = tableStruct + '<td>' + TypeStack[l] + '</td>';

				tableStruct = tableStruct + '</tr>';
			}
			tableStruct = tableStruct + '</tbody>' +
				'</table>' +
				'</div>' +
				'</div>' +
				'</div>' +
				'</div>';
			$('#modalIN').html(tableStruct);
			$('#myModal').modal('show');

			tableStruct1 = '<div class="modal-header"><button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>' +
				'<h4 class="modal-title" id="myModalLabel">MS_ISM_ISSUE_ACTION_PROD|pldoc/index.html#MS_ISM_ISSUE_ACTION_PROD</h4></div>' +
				'<div class="modal-body">' +
				'<div class="panel panel-default">' +
				'<div class="panel-heading">PROCEDURE</div>' +
				'<div class="panel-body">' +
				'<div class="table-responsive"><table class="table  table-bordered table-hover">' +
				'<thead>' +
				'<tr><th>Data Form</th>' +
				'<th>Data Object</th>' +
				'<th>Region</th>' +
				'<th>Field</th></tr>' +
				'</thead>' +
				'<tbody>' +
				'<tr><td>MS_ISM_DU_CLOSE_ACT_HOOK</td>' +
				'<td>MS_ISM_DU_CLOSE_ACT_HOOK</td>' +
				'<td>MS_ISM_DU_CLOSE_ACT_HOOK</td>' +
				'<td>MS_ISM_DU_CLOSE_ACT_HOOK</td>' +
				'</tr>' +
				'</tbody></table>' +
				'</div>' +
				'</div>' +
				'</div>' +
				'</div>';

		}
	}
}
function openClick(e, level, flag) {
	if(level==="PACKAGE"){
		$(e).children("ul").empty();
		treeMenuHeadPB(e);
	}
	var sF = document.getElementById("inputId").value;

	out = "";
	var endoftree = 0;
	var searchText = level.split(",");
	levelLength = searchText.length;
	jsonObject = data[searchText[0]];

	for (i = 1; i < levelLength; i++) {
		jsonObject = jsonObject[searchText[i]];
	}
	if (flag == 1) {
		try {
			var firstIndex = searchText[levelLength - 2];
			jsonObject = data[firstIndex][searchText[levelLength - 1]];
			level = firstIndex + "," + searchText[levelLength - 1];
		} catch (err) {
			endoftree = 1;
		}
	}

	if (e.className == "close" && endoftree == 0 && e.childNodes.length == 1) {
		try {
			var endofloop = 0;
			if (jsonObject[0]) {
				var objArr = jsonObject;
				var arrObj = String(objArr);
				endofloop = 1;
			} else {
				var objArr = $.makeArray(Object.keys(jsonObject));
				var arrObj = String(objArr);
			}
		} catch (err) {}
		var iul = document.createElement('ul');
		iul.className = 'hide';
		try {
			for (i = 0; i < objArr.length; i++) {
				var levelJson = level + "," + objArr[i];
				var ili = document.createElement('li');
				//setting end of the loop
				endofloop = 1;
				ili.className = 'empty';
				ili.setAttribute("onclick", "openClick(this,\"" + levelJson + "\"," + endofloop + ")");
				var secretString = levelJson + "," + endofloop;
				var ia = document.createElement('a');
				var hrefcheck = checkHref(objArr[i]);
				if (hrefcheck != "0") {
					var strsplit = hrefcheck.split("|");
					//var parentKey=searchText[levelLength-2]
					if (typeof(searchText[levelLength - 2]) == "undefined") {
						ia.href = strsplit[2];
					} else {

						var parentKeyInd = searchText[levelLength - 2].split("|");
						//if(searchText[levelLength-1]=="INFOLET"){
						traverseObject = traverse[parentKeyInd[0]][strsplit[0]];

						var traverseArray = $.makeArray(Object.keys(traverseObject));
						var traverseString = "";
						for (j = 0; j < traverseArray.length; j++) {
							if (j > 0) {
								var traverseString = traverseString + "^"
							}
							traverseString = traverseString + traverseArray[j];
							traverseSubObject = traverse[parentKeyInd[0]][strsplit[0]][traverseArray[j]];
							for (l = 0; l < traverseSubObject.length; l++) {
								var tstr = "#" + traverseSubObject[l];
								traverseString = traverseString + tstr;
							}
						}

						//}
						ia.href = strsplit[2] + "#" + searchText[levelLength - 1] + "#" + parentKeyInd[0] + "+" + traverseString;
					}

					ia.setAttribute('target', 'newTab');
					var it = document.createTextNode(strsplit[1]);
					var a = document.createElement('span');
					a.innerHTML = "<a title='" + secretString + "' class='icn icn-requirement' onclick='dsmodal(this)' style='color:#898989;vertical-align: sub; margin-left: 5px;'></a>";
					ia.appendChild(it);
					ia.appendChild(a);
				} else {
					var it = document.createTextNode(objArr[i]);
					ia.appendChild(it);
				}

				ili.appendChild(ia);
				iul.appendChild(ili);
			}

			e.appendChild(iul);

		} catch (err) {
			alert(err);
			endoftree = 1;
		}
	}
	try {
		if (e.className == "close" && endoftree == 0) {
			e.lastChild.className = "";
			e.className = "open";
		} else if (e.className == "open" && endoftree == 0) {
			e.lastChild.className = "hide";
			e.className = "close";
		} else if (endoftree = 1) {
			e.className = "empty";
		}
	} catch (err) {}
	var revHtml = document.getElementById('l1').innerHTML;
	document.getElementById('l1').innerHTML = revHtml;

	if (event.stopPropagation) {
		event.stopPropagation();
	} else {
		event.returnValue = false;
	}

}
function toObject(arr) {
	var rv = {};
	for (var i = 0; i < arr.length; ++i)
		rv[i] = arr[i];
	return rv;
}
if (!Object.keys) {
	Object.keys = function (obj) {
		var keys = [];
		for (var i in obj) {
			if (obj.hasOwnProperty(i)) {
				keys.push(i);
			}
		}
		return keys;
	};
}
if(typeof String.prototype.trim !== 'function') {
  String.prototype.trim = function() {
    return this.replace(/^\s+|\s+$/g, ''); 
  }
}
function treeMenuHeadOrginal() {
	debugger;
	var out = "";
	try {
		var objArr = $.makeArray(Object.keys(data));
		var arrObj = String(objArr);
	} catch (err) {}
	for (i = 0; i < objArr.length; i++) {
		var ili = document.createElement('li');
		ili.className = 'close';
		ili.setAttribute("onclick", "openClick(this,\"" + objArr[i] + "\",0)");
		var ia = document.createElement('a');
		var it = document.createTextNode(objArr[i]);
		ia.appendChild(it);
		ili.appendChild(ia);
		document.getElementById('l1').appendChild(ili);
	}
	var revHtml = document.getElementById('l1').innerHTML;
	document.getElementById('l1').innerHTML = "";
	document.getElementById('l1').innerHTML = revHtml;
}

function treeMenuHead() {
	debugger;
	var out = "";
	try {
		var objArrnew = $.makeArray(Object.keys(parents));
		var arrObj = String(objArrnew);
	} catch (err) {}
	for (i = 0; i < objArrnew.length; i++) {
		var ilinew = document.createElement('li');
		ilinew.className = 'close';
		ilinew.setAttribute("onclick", "openClick(this,\"" + objArrnew[i] + "\",0)");
		level = objArrnew[i];
		var ianew = document.createElement('a');
		var itnew = document.createTextNode(objArrnew[i]);
		ianew.appendChild(itnew);
		ilinew.appendChild(ianew);
		flag = 1;
		out = "";
		var endoftree = 0;
		var searchText = objArrnew[i].split(",");
		levelLength = searchText.length;
		jsonObject = parents[searchText[0]];

		for (j = 1; j < levelLength; j++) {
			jsonObject = jsonObject[searchText[j]];
		}
		if (flag == 1) {
			try {
				var firstIndex = searchText[levelLength - 2];
				jsonObject = parents[firstIndex][searchText[levelLength - 1]];
				level = firstIndex + "," + searchText[levelLength - 1];
			} catch (err) {
				endoftree = 1;
			}
		}

		try {
			var endofloop = 0;
			if (jsonObject[0]) {
				var objArr = jsonObject;
				var arrObj = String(objArr);
				endofloop = 1;
			} else {
				var objArr = $.makeArray(Object.keys(jsonObject));
				var arrObj = String(objArr);
				arrObj=arrObj.sort();
			}
		} catch (err) {}
		var iul = document.createElement('ul');
		iul.className = 'hide';
		try {
			for (k = 0; k < objArr.length; k++) {
				var tempPar = objArr[k].split("|");
				var titleArray = tempPar[0];
				var linkArray = links[level][tempPar[1]];
				var levelJson = level + "," + tempPar[1] + "|" + titleArray + "|" + linkArray;

				var ili = document.createElement('li');
				//setting end of the loop
				endofloop = 1;
				ili.className = 'empty';
				ili.setAttribute("onclick", "openClick(this,\"" + levelJson + "\"," + endofloop + ")");
				var secretString = levelJson + "," + endofloop;
				var ia = document.createElement('a');
				ia.href = linkArray;
				//	var hrefcheck = checkHref(objArr[k]);
				//			if(hrefcheck!="0"){
				//var strsplit=hrefcheck.split("|");
				//var parentKey=searchText[levelLength-2]
				/*	if (typeof(searchText[levelLength-2]) == "undefined"){
				ia.href = strsplit[2];
				}else{

				var parentKeyInd=searchText[levelLength-2].split("|");
				//if(searchText[levelLength-1]=="INFOLET"){
				traverseObject = traverse[parentKeyInd[0]][strsplit[0]];


				var traverseArray = $.makeArray(Object.keys(traverseObject));
				var traverseString="";
				for (j = 0; j < traverseArray.length; j++) {
				if(j>0){
				var traverseString=traverseString+"^"
				}
				traverseString=traverseString+traverseArray[j];
				traverseSubObject = traverse[parentKeyInd[0]][strsplit[0]][traverseArray[j]];
				for(l=0;l<traverseSubObject.length;l++){
				var tstr="#"+traverseSubObject[l];
				traverseString=traverseString+tstr;
				}
				}

				//}
				ia.href = strsplit[2]+"#"+searchText[levelLength-1]+"#"+parentKeyInd[0]+"+"+traverseString;
				}*/

				ia.setAttribute('target', 'newTab');
				var it = document.createTextNode(titleArray);
				var a = document.createElement('span');
				a.innerHTML = "<a title='" + secretString + "' class='icn icn-requirement' onclick='dsmodal(this)' style='color:#898989;vertical-align: sub; margin-left: 5px;'></a>";
				ia.appendChild(it);
				ia.appendChild(a);
				/*	} else {
				var it = document.createTextNode(objArr[k]);
				ia.appendChild(it);
				}*/

				ili.appendChild(ia);
				iul.appendChild(ili);
			}
			ilinew.appendChild(iul);
			document.getElementById('l1').appendChild(ilinew);

		} catch (err) {
			alert(err);
			endoftree = 1;
		}
	}

	var revHtml = document.getElementById('l1').innerHTML;
	document.getElementById('l1').innerHTML = revHtml;

	if (event.stopPropagation) {
		event.stopPropagation();
	} else {
		event.returnValue = false;
	}
}
function treeMenuHeadPB(e) {
	debugger;
	var out = "";
	try {
		var objArrnew = $.makeArray(Object.keys(pkgMethods));
		var arrObj = String(objArrnew);
	} catch (err) {}
	for (i = 0; i < objArrnew.length; i++) {
		var ilinew = document.createElement('li');
		ilinew.className = 'close';
		var displayText=objArrnew[i].split("|");
		ilinew.setAttribute("onclick", "openClick(this,\"" + displayText[0] + "\",0)");
		level = objArrnew[i];
		var ianew = document.createElement('a');
		
		var itnew = document.createTextNode(displayText[0]);
		ianew.appendChild(itnew);
		ilinew.appendChild(ianew);
		flag = 1;
		out = "";
		var endoftree = 0;
		var searchText = objArrnew[i].split(",");
		levelLength = searchText.length;
		jsonObject = pkgMethods[searchText[0]];

		for (j = 1; j < levelLength; j++) {
			jsonObject = jsonObject[searchText[j]];
		}
		if (flag == 1) {
			try {
				var firstIndex = searchText[levelLength - 2];
				jsonObject = pkgMethods[firstIndex][searchText[levelLength - 1]];
				level = firstIndex + "," + searchText[levelLength - 1];
			} catch (err) {
				endoftree = 1;
			}
		}

		try {
			var endofloop = 0;
			if (jsonObject[0]) {
				var objArr = jsonObject;
				var arrObj = String(objArr);
				endofloop = 1;
			} else {
				var objArr = $.makeArray(Object.keys(jsonObject));
				var arrObj = String(objArr);
				arrObj=arrObj.sort();
			}
		} catch (err) {}
		var iul = document.createElement('ul');
		iul.className = 'hide';
		try {
			for (k = 0; k < objArr.length; k++) {
				var tempPar = objArr[k].split("|");
			
				var titleArray = tempPar[0];
				if(links["PACKAGE"]===undefined){
				var linkArray="#";	
				}else{
				var linkArray = links["PACKAGE"][displayText[0]+"."+tempPar[0]];
				}
				var levelJson = "PACKAGE"+"," + displayText[0]+"."+tempPar[0] + "|" + displayText[0]+"."+titleArray + "|" + linkArray;

				var ili = document.createElement('li');
				//setting end of the loop
				endofloop = 1;
				ili.className = 'empty';
				ili.setAttribute("onclick", "openClick(this,\"" + levelJson + "\"," + endofloop + ")");
				var secretString = levelJson + "," + endofloop;
				var ia = document.createElement('a');
				ia.href = linkArray;

				ia.setAttribute('target', 'newTab');
				var it = document.createTextNode(titleArray);
				var a = document.createElement('span');
				a.innerHTML = "<a title='" + secretString + "' class='icn icn-requirement' onclick='dsmodal(this)' style='color:#898989; vertical-align: sub; margin-left: 5px;'></a>";
				ia.appendChild(it);
				ia.appendChild(a);
				ili.appendChild(ia);
				iul.appendChild(ili);
			}
			ilinew.appendChild(iul);
			$(e).children("ul").append(ilinew);

		} catch (err) {
			alert(err);
			endoftree = 1;
		}
	}

	var revHtml = $(e).children("ul").innerHTML;
	$(e).children("ul").innerHTML = revHtml;

	if (event.stopPropagation) {
		event.stopPropagation();
	} else {
		event.returnValue = false;
	}
}
function clearValue() {
	$("#inputId").val("");
	keyUpFunc();
}
function keyUpFunc() {
	var sF = document.getElementById("inputId").value;
	if (sF != "") {}
	$("li").removeClass('open');
	$("li").addClass('close');
	$("ul").addClass('hide');
	$("#l1").removeClass('hide');
	$("span").removeClass('highLight');
	if (sF == "") {
		$("li").show();
		$("li").removeClass('open');
		$("li").addClass('close');
		$("ul").addClass('hide');
		$("#l1").removeClass('hide');
	} else {
		$("#l1 li").hide();
		var searchValue = $("#inputId").val().toUpperCase(),
		matchSub = false;
		$('li').each(function () {
			var itemValue;
			var Sub = $(this).children('a');
			itemValue = $.trim(Sub.eq(0).text()).toUpperCase();
			if (itemValue.search(searchValue) >= 0) {
				var n = itemValue.search(searchValue);
				var till = searchValue.length + n;
				if (n != 0) {
					var prev = itemValue.substring(0, n);
				}
				if ((itemValue.length - 1) != ((searchValue.length - 1) + n)) {
					var next = itemValue.substring((n + searchValue.length), itemValue.length);
				}
				if ((n != 0) && ((itemValue.length - 1) != ((searchValue.length - 1) + n))) {
					var tot = prev + "<span class='highLight'>" + searchValue + "</span>" + next;
				} else if ((n == 0) && ((itemValue.length - 1) != ((searchValue.length - 1) + n))) {
					var tot = "<span class='highLight'>" + searchValue + "</span>" + next;
				} else if ((n != 0) && ((itemValue.length - 1) == ((searchValue.length - 1) + n))) {
					var tot = prev + "<span class='highLight'>" + searchValue + "</span>";
				} else {
					var tot = "<span class='highLight'>" + searchValue + "</span>";
				}
				Sub.eq(0).html(tot);
				Sub.eq(0).parentsUntil($("#l1"), "li").show();
				Sub.eq(0).parent("li").parentsUntil($("#l1"), "li").removeClass('close');
				Sub.eq(0).parent("li").parentsUntil($("#l1"), "li").addClass('open');
				Sub.eq(0).parentsUntil($("#l1"), "ul").removeClass('hide');
			}
		});
	}
}
$(document).ready(function () {
	$("#colID").click(function () {
		$("#colID").hide();
		$("#exID").show();
	});
	$("#exID").click(function () {
		$("#exID").hide();
		$("#colID").show();
	});
	treeMenuHead();
	$("#inputId").keyup(function (e) {
		keyUpFunc();
	});
});
