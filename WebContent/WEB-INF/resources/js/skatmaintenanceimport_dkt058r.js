  //this variable is a global jQuery var instead of using "$" all the time. Very handy
  var jq = jQuery.noConflict();
  var counterIndex = 0;
  var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";
  
  function setBlockUI(element){
	  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
  }
  
  jq(function() {
	  jq("#formRecord").submit(function() {
		  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT}); 
	  });
	  
	  //----------------
	  //CUSTOMER search
	  //----------------
	  //Kundnr
	  jq('#dkse_knrIdLink').click(function() {
		  jq('#dkse_knrIdLink').attr('target','_blank');
		  window.open('mainmaintenance_childwindow_customer.do?action=doFind&firma=' + jq('#companyCode').val() + "&knr=" + jq('#dkse_knr').val() + '&ctype=dkse_knr', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	  });
	  jq('#dkse_knrIdLink').keypress(function(e){ //extra feature for the end user
  		  if(e.which == 13) {
  			  jq('#dkse_knrIdLink').click();
  		  }
  	  });
  });
  
  
  jq(function() {
		//Clean values for createing new record
		jq('#newRecordButton').click(function() {
			jq('#dkse_knr').val("");
			jq("#dkse_knr").prop("readonly", false);
			jq("#dkse_knr").removeClass("inputTextReadOnly");
			jq("#dkse_knr").addClass("inputTextMediumBlueMandatoryField");
			//
			jq('#dkse_331').val("");
			jq("#dkse_331").prop("readonly", false);
			jq("#dkse_331").removeClass("inputTextReadOnly");
			jq("#dkse_331").addClass("inputTextMediumBlueMandatoryField");
			
			//rest of the gang
			jq('#dkse_34').val("");
			jq('#dkse_4421').val("");
			jq('#dkse_442A').val("");
			
			//for update
			jq('#updateId').val("");
		});
  }); 
  
  //-----------------------
  //GET specific db-record
  //-----------------------
  function getRecord(record){
	/* N/A  
	var rawId = record.id;
  	var applicationUserParam = jq('#applicationUser').val();
  	
  	rawId = rawId.replace("recordUpdate_", "");
  	var record = rawId.split('_');
	var kvakod = record[0];
	var kvadt = record[1];
	
	jq.ajax({
  	  type: 'GET',
  	  url: 'getSpecificRecord_dkt058r.do',
  	  data: { applicationUser : jq('#applicationUser').val(), 
  		  	  id : kvakod,
  		  	  date : kvadt},
  	  dataType: 'json',
  	  cache: false,
  	  contentType: 'application/json',
  	  success: function(data) {
	  	var len = data.length;
  		for ( var i = 0; i < len; i++) {
  			jq('#dkvk_kd').val("");jq('#dkvk_kd').val(data[i].dkvk_kd);
  			jq("#dkvk_kd").prop("readonly", true);
  			jq("#dkvk_kd").removeClass("inputTextMediumBlueMandatoryField");
  			jq("#dkvk_kd").addClass("inputTextReadOnly");
  			
  			jq('#dkvk_dts').val("");jq('#dkvk_dts').val(data[i].dkvk_dts);
  			jq("#dkvk_dts").prop("readonly", true);
  			jq("#dkvk_dts").removeClass("inputTextMediumBlueMandatoryField");
  			jq("#dkvk_dts").addClass("inputTextReadOnly");
  			
  			//rest of the gang
  			jq('#dkvk_krs').val("");jq('#dkvk_krs').val(data[i].dkvk_krs);
  			jq('#dkvk_omr').val("");jq('#dkvk_omr').val(data[i].dkvk_omr);
  			jq('#dkvk_dte').val("");jq('#dkvk_dte').val(data[i].dkvk_dte);
  			
  			//for a future update
  			jq('#updateId').val("");jq('#updateId').val(data[i].dkvk_kd);
  			
  		}
  	  }, 
  	  error: function() {
  		  alert('Error loading ...');
  	  }
	});
	*/
  }
  
			
  //-------------------
  //Datatables jquery
  //-------------------
  //private function
  function filterGlobal () {
    jq('#mainList').dataTable().search(
    	jq('#mainList_filter').val()
    ).draw();
  }
  
  jq(document).ready(function() {
      //init table (no ajax, no columns since the payload is already there by means of HTML produced on the back-end)
      jq('#mainList').dataTable( {
    	  "dom": '<"top">t<"bottom"flip><"clear">',
    	  "scrollY": "250px",
    	  "scrollCollapse":  false,
    	  "lengthMenu": [ 75, 100]
  	  });
      
      //event on input field for search
      jq('input.mainList_filter').on( 'keyup click', function () {
      		filterGlobal();
      });
  	
  });
  