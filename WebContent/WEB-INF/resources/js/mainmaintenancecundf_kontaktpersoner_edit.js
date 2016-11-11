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

jq(function() {
	//Clean values for createing new record
	jq('#newRecordButton').click(function() {
		jq('#tgkna').val("");
		jq('#cconta').val("")
		jq('#ctype').val("");
		jq('#cphone').val("");
		jq('#cmobil').val("");
		jq('#cfax').val("");
		jq('#cemail').val("");
		jq('#clive').val("");
		jq('#cprint').val("");
		jq('#sonavn').val("");
		jq('#cemne').val("");
		jq('#cavd').val("");
		jq('#cavdio').val("");
		jq('#copd').val("");
		jq('#copdio').val("");
		jq('#cmerge').val("");

		//for update
		jq('#updateId').val("");
		
	});
}); 


//-----------------------
//GET specific db-record
//-----------------------

function getRecord(record){
	var rawId = record.id;
	var applicationUserParam = jq('#applicationUser').val();
	rawId = rawId.replace("recordUpdate_", "");
	var record = rawId.split('_');
	var cfirma = record[0];
	var ccompn = record[1];
	var cconta = record[2];
	    	
	jq.ajax({
	  type: 'GET',
	  url: 'getSpecificRecord_cundc.do',
	  data: { applicationUser : jq('#applicationUser').val(), 
		  	  cfirma : cfirma,
		  	  ccompn : ccompn,
		  	  cconta : cconta },
	  dataType: 'json',
	  cache: false,
	  contentType: 'application/json',
	  success: function(data) {
	  	var len = data.length;
		for ( var i = 0; i < len; i++) {
			jq('#cconta').val("");jq('#cconta').val(data[i].cconta);
			jq('#ccontaorg').val("");jq('#ccontaorg').val(data[i].ccontaorg);
			jq('#ctype').val("");jq('#ctype').val(data[i].ctype);
			jq('#cphone').val("");jq('#cphone').val(data[i].cphone);
			jq('#cmobil').val("");jq('#cmobil').val(data[i].cmobil);
			jq('#cfax').val("");jq('#cfax').val(data[i].cfax);
			jq('#cemail').val("");jq('#cemail').val(data[i].cemail);
			jq('#clive').val("");jq('#clive').val(data[i].clive);
			jq('#cprint').val("");jq('#cprint').val(data[i].cprint);
			jq('#sonavn').val("");jq('#sonavn').val(data[i].sonavn);
			jq('#cemne').val("");jq('#cemne').val(data[i].cemne);
			jq('#cavd').val("");jq('#cavd').val(data[i].cavd);
			jq('#cavdio').val(""); jq('#cavdio').val(data[i].cavdio);
			jq('#copd').val("");jq('#copd').val(data[i].copd);
			jq('#copdio').val("");jq('#copdio').val(data[i].copdio);
			jq('#cmerge').val("");jq('#cmerge').val(data[i].cmerge);
			
			//for a future update
			jq('#updateId').val("");jq('#updateId').val(data[i].cconta);
			
		}
	  }, 
	  error: function() {
		  alert('Error loading ...');
	  }
	});
		
}


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
