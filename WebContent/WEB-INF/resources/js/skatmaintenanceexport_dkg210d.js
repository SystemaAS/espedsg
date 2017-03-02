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
  });
  
  
  jq(function() {
		//Clean values for createing new record
		jq('#newRecordButton').click(function() {
			jq('#dkkd_kd').val("");
			jq("#dkkd_kd").prop("readonly", false);
			jq("#dkkd_kd").removeClass("inputTextReadOnly");
			jq("#dkkd_kd").addClass("inputTextMediumBlueMandatoryField");
			
			//rest of the gang
			jq('#dkkd_kd2').val("");
			jq('#dkkd_kd3').val("");
			jq('#dkkd_txt').val("");
			
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
	var dkkd_typ = record[0];
	var dkkd_kd = record[1];
	
	jq.ajax({
  	  type: 'GET',
  	  url: 'getSpecificRecord_dkg210d.do',
  	  data: { applicationUser : jq('#applicationUser').val(), 
  		  dkkd_typ : dkkd_typ,
  		  dkkd_kd : dkkd_kd},
  	  dataType: 'json',
  	  cache: false,
  	  contentType: 'application/json',
  	  success: function(data) {
	  	var len = data.length;
  		for ( var i = 0; i < len; i++) {
  			jq('#dkkd_kd').val("");jq('#dkkd_kd').val(data[i].dkkd_kd);
  			jq("#dkkd_kd").prop("readonly", true);
  			jq("#dkkd_kd").removeClass("inputTextMediumBlueMandatoryField");
  			jq("#dkkd_kd").addClass("inputTextReadOnly");
  			
  			//rest of the gang
  			jq('#dkkd_kd2').val("");jq('#dkkd_kd2').val(data[i].dkkd_kd2);
  			jq('#dkkd_kd3').val("");jq('#dkkd_kd3').val(data[i].dkkd_kd3);
  			jq('#dkkd_txt').val("");jq('#dkkd_txt').val(data[i].dkkd_txt);
  			
  			//for a future update
  			jq('#updateId').val("");jq('#updateId').val(data[i].dkkd_kd);
  			
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
    	  "columnDefs": [{ "type": "num", "targets": 1 }],
    	  "order": [[ 1, "asc" ]],
    	  "lengthMenu": [ 75, 100]
  	  });
      
      //event on input field for search
      jq('input.mainList_filter').on( 'keyup click', function () {
      		filterGlobal();
      });
  	
  });
  