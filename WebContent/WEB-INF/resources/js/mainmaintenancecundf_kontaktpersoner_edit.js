//this variable is a global jQuery var instead of using "$" all the time. Very handy
var jq = jQuery.noConflict();
var counterIndex = 0;
var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";

function setBlockUI(element) {

	//jq("#dialog").dialog("open");  
	//Funkar inte att anropas rått. Behöver <div> alt .click

	if (jq("#dirty").val() == "isDirty") {

		alert("Du har ändrat, utan att spara. Vill du gå vidare.....");

	}

	jq.blockUI({
		message : BLOCKUI_OVERLAY_MESSAGE_DEFAULT
	});
}

jq(function() {

	jq("#dialog").dialog({
		modal : true,
		resizable : false,
		buttons : {
			"Yeah!" : function() {
				$(this).dialog("close");
			},
			"Sure, Why Not" : function() {
				$(this).dialog("close");
			}
		}
	});

	jq("input[type='text']").change(function() {
		jq('#dirty').val("isDirty");

	});

});

//-------------------
//Datatables jquery
//-------------------
//private function
function filterGlobal() {
	jq('#mainList').dataTable().search(jq('#mainList_filter').val()).draw();
}

jq(document).ready(function() {
	//init table (no ajax, no columns since the payload is already there by means of HTML produced on the back-end)
	jq('#mainList').dataTable({
		"dom" : '<"top">t<"bottom"flip><"clear">',
		"scrollY" : "250px",
		"scrollCollapse" : false,
		"columnDefs" : [ {
			"type" : "num",
			"targets" : 0
		} ],
		"lengthMenu" : [ 75, 100 ]
	});

});
