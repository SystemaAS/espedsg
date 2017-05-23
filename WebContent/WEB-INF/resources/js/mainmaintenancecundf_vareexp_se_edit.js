//this variable is a global jQuery var instead of using "$" all the time. Very handy
var jq = jQuery.noConflict();
var counterIndex = 0;
var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";

function setBlockUI(element) {

	jq.blockUI({
		message : BLOCKUI_OVERLAY_MESSAGE_DEFAULT
	});
}

jq(function() {
	jq('#newRecordButton').click(function() {
		jq("input[type='text']").val("");
		jq("#svew_knso").prop("readonly", false);
		jq("#svew_knso").removeClass("inputTextReadOnly");
		jq("#svew_knso").addClass("inputTextMediumBlueMandatoryField");
		
		//for update
		jq('#updateId').val("");
		
	});

	jq("#formRecord").submit(function() {
		jq.blockUI({
			message : BLOCKUI_OVERLAY_MESSAGE_DEFAULT
		});
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
	var svew_knnr = record[0];
	var svew_knso = record[1];
	
	jq("#svew_knso").prop("readonly", true);
	jq("#svew_knso").removeClass("inputTextMediumBlueMandatoryField");
	jq("#svew_knso").addClass("inputTextReadOnly");
	
	
	jq.ajax({
	  type: 'GET',
	  url: 'getSpecificRecord_svew.do',
	  data: { applicationUser : jq('#applicationUser').val(), 
		  svew_knnr : svew_knnr,
		  svew_knso  : svew_knso },
	  dataType: 'json',
	  cache: false,
	  contentType: 'application/json',
	  success: function(data) {
	  	var len = data.length;
		for ( var i = 0; i < len; i++) {
			jq('#svew_knso').val("");jq('#svew_knso').val(data[i].svew_knso);
			jq('#svew_vasl').val("");jq('#svew_vasl').val(data[i].svew_vasl);
			jq('#svew_vasl2').val("");jq('#svew_vasl2').val(data[i].svew_vasl2);
			jq('#svew_vasl3').val("");jq('#svew_vasl3').val(data[i].svew_vasl3);
			jq('#svew_vasl4').val("");jq('#svew_vasl4').val(data[i].svew_vasl4);
			jq('#svew_vasl5').val("");jq('#svew_vasl5').val(data[i].svew_vasl5);
			jq('#svew_vano').val("");jq('#svew_vano').val(data[i].svew_vano);
			jq('#svew_vata').val("");jq('#svew_vata').val(data[i].svew_vata);
			jq('#svew_lagi').val("");jq('#svew_lagi').val(data[i].svew_lagi);
			jq('#svew_ulkd').val("");jq('#svew_ulkd').val(data[i].svew_ulkd);
			jq('#svew_vati').val("");jq('#svew_vati').val(data[i].svew_vati);
			jq('#svew_vat4').val("");jq('#svew_vat4').val(data[i].svew_vat4);
			jq('#svew_vat5').val("");jq('#svew_vat5').val(data[i].svew_vat5);
			jq('#svew_komr').val("");jq('#svew_komr').val(data[i].svew_komr);
			jq('#svew_fnkd').val("");jq('#svew_fnkd').val(data[i].svew_fnkd);
			jq('#svew_brut').val("");jq('#svew_brut').val(data[i].svew_brut);
			jq('#svew_eup1').val("");jq('#svew_eup1').val(data[i].svew_eup1);
			jq('#svew_neto').val("");jq('#svew_neto').val(data[i].svew_neto);
			jq('#svew_eup2').val("");jq('#svew_eup2').val(data[i].svew_eup2);
			jq('#svew_betk').val("");jq('#svew_betk').val(data[i].svew_betk);
			jq('#svew_atin').val("");jq('#svew_atin').val(data[i].svew_atin);
			jq('#svew_kono').val("");jq('#svew_kono').val(data[i].svew_kono);
			jq('#svew_ankv').val("");jq('#svew_ankv').val(data[i].svew_ankv);
			jq('#svew_stva').val("");jq('#svew_stva').val(data[i].svew_stva);
			jq('#svew_stva2').val("");jq('#svew_stva2').val(data[i].svew_stva2);
			jq('#svew_fabl').val("");jq('#svew_fabl').val(data[i].svew_fabl);
			
			
			//for a future update
			jq('#updateId').val("");jq('#updateId').val(data[i].svew_knso);
			
		}
	  }, 

	  error: function (jqXHR, exception) {
		    alert('Error loading ...look in console log.');
		    console.log(jqXHR);
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
	var lang = jq('#language').val();
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
			"url": getLanguage(lang)
        }
	});

});
