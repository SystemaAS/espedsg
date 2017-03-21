  //this variable is a global jQuery var instead of using "$" all the time. Very handy
  var jq = jQuery.noConflict();
  var counterIndex = 0;
  var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";
  
  function setBlockUI(element){
	  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
  }
  
//called from form-submit, otherwise the select-disabled won't send the value
  function enableDisabledFields() {
	    document.getElementById('dkth_sysg').disabled= "";
  }
  
  
  jq(function() { 
	  //Childwindow on parent signatures
	  jq('#dkth_sysgIdLink').click(function() {
		  jq('#dkth_sysgIdLink').attr('target','_blank');
		  window.open('mainmaintenance_childwindow_signatures.do?action=doFind&id=' + jq('#dkth_sysg').val() + '&ctype=dkth_sysg', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	  });
	  jq('#dkth_sysgIdLink').keypress(function(e){ //extra feature for the end user
		  if(e.which == 13) {
			  jq('#dkth_sysgIdLink').click();
		  }
	  });
  });
  
  jq(function() {
		//Clean values for createing new record
		jq('#newRecordButton').click(function() {
			jq('#dkth_sysg').val("");
			jq('#dkth_sysg').attr('disabled', false);
			jq('#dkth_sysg').attr('readOnly', false);
			jq("#dkth_sysg").removeClass("inputTextReadOnly");
			jq("#dkth_sysg").addClass("inputTextMediumBlueMandatoryField");
			//
			jq('#dkth_namn').val("");
			jq('#dkth_usid').val("");
			
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
  	//var record = rawId.split('_');
  	var dkth_sysg = rawId;
	
	jq.ajax({
  	  type: 'GET',
  	  url: 'getSpecificRecord_dkt056r.do',
  	  data: { applicationUser : applicationUserParam, 
  		  	  id : dkth_sysg },
  	  dataType: 'json',
  	  cache: false,
  	  contentType: 'application/json',
  	  success: function(data) {
	  	var len = data.length;
  		for ( var i = 0; i < len; i++) {

  			if(data[i].dkth_sysg!=null && data[i].dkth_sysg != ''){
  				jq('#dkth_sysg').val("");jq('#dkth_sysg').val(data[i].dkth_sysg);
  				//editable fields
  				//Special treatment for honet when empty since we must map to space-Html representation: meaning = '+'
  				
	  			jq('#dkth_sysg').val("");jq('#dkth_sysg').val(data[i].dkth_sysg);
	  			jq('#dkth_sysg').attr('disabled', true);
	  			jq('#dkth_sysg').attr('readOnly', true);
				jq("#dkth_sysg").removeClass("inputTextMediumBlueMandatoryField");
				jq("#dkth_sysg").addClass("inputTextReadOnly");
				//rest of the fields
				jq('#dkth_namn').val("");jq('#dkth_namn').val(data[i].dkth_namn);
  				jq('#dkth_usid').val("");jq('#dkth_usid').val(data[i].dkth_usid);
  				
	  			//for a future update
	  			jq('#updateId').val("");jq('#updateId').val(data[i].dkth_sysg);

  			}else{
  				//DEBUG->
  				alert("dkth_sysg = NULL ?");
  			}
  			
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
    	  "scrollY": "350px",
    	  "scrollCollapse":  true,
    	  //"columnDefs": [{ "type": "num", "targets": 1 }],
    	  "order": [[ 1, "asc" ]],
    	  "lengthMenu": [ 75, 100]
  	  });
      
      //event on input field for search
      jq('input.mainList_filter').on( 'keyup click', function () {
      		filterGlobal();
      });
  	
  });
  