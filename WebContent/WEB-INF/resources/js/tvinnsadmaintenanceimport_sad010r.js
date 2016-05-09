  //this variable is a global jQuery var instead of using "$" all the time. Very handy
  var jq = jQuery.noConflict();
  var counterIndex = 0;
  var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";
  
  function setBlockUI(element){
	  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
  }
  
  jq(function() {
  	  jq("#tadato").datepicker({ 
  		  dateFormat: 'yymmdd'
  	  });
  	  jq("#tadts").datepicker({ 
		  dateFormat: 'yymmdd'
	  });
  });

  jq(function() {
	
	//Clean values for createing new record
	jq('#newRecordButton').click(function() {
		jq('#tatanr').val("");
			jq("#tatanr").prop("readonly", false);
			jq("#tatanr").removeClass("inputTextReadOnly");
			jq("#tatanr").addClass("inputTextMediumBlueMandatoryField");
			
			//rest of the gang
			jq('#taalfa').val("");
			jq('#taalfaOrig').val("");
			jq('#tadato').val("");
			jq('#tadtr').val("");
			jq('#tadts').val("");
			jq('#taordb').val("");
  			jq('#taordk').val("");
  			jq('#taeftb').val("");
  			jq('#taeftk').val("");
  			jq('#taefb').val("");
  			jq('#taefk').val("");
  			//
  			jq('#tastk').val("");
  			jq('#tatxt').val("");
  			jq('#taenhe').val("");
  			
			//for update
			jq('#updateId').val("");
	});
	
  }); 
	
  //Varekod
  //-----------------------
  //GET specific db-record
  //-----------------------
  
  function getRecord(record){
	var rawId = record.id;
  	var applicationUserParam = jq('#applicationUser').val();
  	rawId = rawId.replace("recordUpdate_", "");
  	var record = rawId.split('_');
	var tatanr = record[0];
	var taalfa = record[1];
	    	
	jq.ajax({
  	  type: 'GET',
  	  url: 'getSpecificRecord_sad010r.do',
  	  data: { applicationUser : jq('#applicationUser').val(), 
  		  	  id : tatanr,
  		  	  alfa : taalfa },
  	  dataType: 'json',
  	  cache: false,
  	  contentType: 'application/json',
  	  success: function(data) {
	  	var len = data.length;
  		for ( var i = 0; i < len; i++) {
  			jq('#tatanr').val("");jq('#tatanr').val(data[i].tatanr);
  			jq("#tatanr").prop("readonly", true);
  			jq("#tatanr").removeClass("inputTextMediumBlueMandatoryField");
  			jq("#tatanr").addClass("inputTextReadOnly");
  			
  			//rest of the gang
  			jq('#taalfa').val("");jq('#taalfa').val(data[i].taalfa);
  			jq('#taalfaOrig').val("");jq('#taalfaOrig').val(data[i].taalfa);
  			jq('#tadato').val("");jq('#tadato').val(data[i].tadato);
  			jq('#tadtr').val("");jq('#tadtr').val(data[i].tadtr);
  			jq('#tadts').val("");jq('#tadts').val(data[i].tadts);
  			
  			jq('#taordb').val("");jq('#taordb').val(data[i].taordb);
  			jq('#taordk').val("");jq('#taordk').val(data[i].taordk);
  			jq('#taeftb').val("");jq('#taeftb').val(data[i].taeftb);
  			jq('#taeftk').val("");jq('#taeftk').val(data[i].taeftk);
  			jq('#taefb').val("");jq('#taefb').val(data[i].taefb);
  			jq('#taefk').val("");jq('#taefk').val(data[i].taefk);
  			//
  			jq('#tastk').val("");jq('#tastk').val(data[i].tastk);
  			jq('#tatxt').val("");jq('#tatxt').val(data[i].tatxt);
  			jq('#taenhe').val("");jq('#taenhe').val(data[i].taenhe);
  			//countries
  			jq('#taeosb').val("");jq('#taeosb').val(data[i].taeosb);
  			jq('#taeosk').val("");jq('#taeosk').val(data[i].taeosk);
  			jq('#tatsjb').val("");jq('#tatsjb').val(data[i].tatsjb);
  			jq('#tatsjk').val("");jq('#tatsjk').val(data[i].tatsjk);
  			jq('#tatyrb').val("");jq('#tatyrb').val(data[i].tatyrb);
  			jq('#tatyrk').val("");jq('#tatyrk').val(data[i].tatyrk);
  			jq('#taisrb').val("");jq('#taisrb').val(data[i].taisrb);
  			jq('#taisrk').val("");jq('#taisrk').val(data[i].taisrk);
  			jq('#taellb').val("");jq('#taellb').val(data[i].taellb);
  			jq('#taellk').val("");jq('#taellk').val(data[i].taellk);
  			
  			
  			
  			//for a future update
  			jq('#updateId').val("");jq('#updateId').val(data[i].tatanr);
  			
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
    	  "columnDefs": [{ "type": "num", "targets": 0 }],
    	  "lengthMenu": [ 75, 100]
  	  });
      
      //event on input field for search
      jq('input.mainList_filter').on( 'keyup click', function () {
      		filterGlobal();
      });
  	
  });
  