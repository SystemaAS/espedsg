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
	//Clean values for createing new record
	jq('#newRecordButton').click(function() {
		jq('#varenr').val("")
		jq('#varebe').val("");
		jq('#levenr').val("");
		jq('#w2vf').val("");
		jq('#w2lk').val("")
		jq('#w2vnti').val("");
		jq('#w2tn').val("");
		jq('#w2pre').val("");		
		jq('#w2pva').val("")
		jq('#w2as').val("");
		jq('#w2mfr').val("");
		jq('#w2belt').val("");		
		jq('#w2vktb').val("");		
		jq('#w2ntm').val("")
		jq('#w2beln').val("");
		jq('#w2pros').val("");
		jq('#w2val').val("");				

		//for update
		jq('#updateId').val("");
		
	});

	jq("#formRecord").submit(function() {
		jq.blockUI({
			message : BLOCKUI_OVERLAY_MESSAGE_DEFAULT
		});
	});

	jq("input[type='text']").change(function() {
		jq('#dirty').val("isDirty");

	});
	
    jq('#sypaidIdLink').click(function() {
    	jq('#sypaidIdLink').attr('target','_blank');
    	window.open('mainmaintenance_vkund_edit_childwindow_codes.do?caller=sypaid', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });	

	
}); 


//-----------------------
//GET specific db-record   //TODO FIX
//-----------------------

function getRecord(record){
	var rawId = record.id;
	var applicationUserParam = jq('#applicationUser').val();
	rawId = rawId.replace("recordUpdate_", "");
	var record = rawId.split('_');
	var levenr = record[0];
	var varenr = record[1];
	    	
	jq.ajax({
	  type: 'GET',
	  url: 'getSpecificRecord_sadvare.do',
	  data: { applicationUser : jq('#applicationUser').val(), 
		  levenr : levenr,
		  varenr  : varenr },
	  dataType: 'json',
	  cache: false,
	  contentType: 'application/json',
	  success: function(data) {
	  	var len = data.length;
		for ( var i = 0; i < len; i++) {
			jq('#varenr').val("");jq('#varenr').val(data[i].varenr);
			jq('#varebe').val("");jq('#varebe').val(data[i].varebe);
			jq('#levenr').val("");jq('#levenr').val(data[i].levenr);
			jq('#w2vf').val("");jq('#w2vf').val(data[i].w2vf);
			jq('#w2lk').val("");jq('#w2lk').val(data[i].w2lk);
			jq('#w2vnti').val("");jq('#w2vnti').val(data[i].w2vnti);
			jq('#w2tn').val("");jq('#w2tn').val(data[i].w2tn);
			jq('#w2pre').val("");jq('#w2pre').val(data[i].w2pre);
			jq('#w2pva').val("");jq('#w2pva').val(data[i].w2pva);
			jq('#w2as').val("");jq('#w2as').val(data[i].w2as);
			jq('#w2mfr').val("");jq('#w2mfr').val(data[i].w2mfr);
			jq('#w2belt').val("");jq('#w2belt').val(data[i].w2belt);
			jq('#w2vktb').val("");jq('#w2vktb').val(data[i].w2vktb);
			jq('#w2ntm').val("");jq('#w2ntm').val(data[i].w2ntm);
			jq('#w2beln').val("");jq('#w2beln').val(data[i].w2beln);
			jq('#w2pros').val("");jq('#w2pros').val(data[i].w2pros);
			jq('#w2val').val("");jq('#w2val').val(data[i].w2val);
	
			//for a future update
			jq('#updateId').val("");jq('#updateId').val(data[i].sypaid);
			
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
		"scrollY" : "200px",
		"scrollCollapse" : false,
		"columnDefs" : [ {
			"type" : "num",
			"targets" : 0
		} ],
		"lengthMenu" : [ 75, 100 ],
		"language": {
			"url": "//cdn.datatables.net/plug-ins/1.10.13/i18n/Norwegian-Bokmal.json"   //TODO Extraxt into own server
        }
	});

});
