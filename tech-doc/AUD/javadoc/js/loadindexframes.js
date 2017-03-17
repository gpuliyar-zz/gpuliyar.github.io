$(document).ready(function (){
var h=$(window).height();
var w=$(document).width();
var width = parseInt(w);
var height = parseInt(h);
var c1=0.2*height;
var c2=0.8*height;
//var r1=0.4*width;
//var r2=0.6*width;

var c = c1 + "px, " + c2 + "px";
//var r = r1 + "px, " + r2 + "px";
document.getElementById("mainframeset").cols = c;
//document.getElementById("subframeset").rows = r;
});

$( window ).resize(function() {
 var h=$(window).height();
var w=$(document).width();
var width = parseInt(w);
var height = parseInt(h);
var c1=0.2*height;
var c2=0.8*height;
//var r1=0.4*width;
//var r2=0.6*width;

var c = c1 + "px, " + c2 + "px";
//var r = r1 + "px, " + r2 + "px";
document.getElementById("mainframeset").cols = c;
//document.getElementById("subframeset").rows = r;
});
