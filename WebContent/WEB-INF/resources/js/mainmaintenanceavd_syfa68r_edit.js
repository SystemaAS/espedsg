
//this variable is a global jQuery var instead of using "$" all the time. Very handy
  var jq = jQuery.noConflict();
  var counterIndex = 0;
  var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";
  
  function setBlockUI(element){
	  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
  }
 
  //-----------------------
  //GET specific db-record
  //-----------------------
  function getRecord(record){
	var rawId = record.id;
  	var applicationUserParam = jq('#applicationUser').val();
  	rawId = rawId.replace("recordUpdate_", "");
	
	jq.ajax({
  	  type: 'GET',
  	  url: 'getSpecificRecord_syfa68r.do',
  	  data: { applicationUser : jq('#applicationUser').val(), 
  		  	  kohavd : rawId },
  	  dataType: 'json',
  	  cache: false,
  	  contentType: 'application/json',
  	  success: function(data) {
	  	var len = data.length;
  		for ( var i = 0; i < len; i++) {
			
  			jq('#kohfak').val("");jq('#kohfak').val(data[i].kohfak);
  			jq('#kohlas').val("");jq('#kohlas').val(data[i].kohlas);
  			
  			jq('#kohgod').val("");jq('#kohgod').val(data[i].kohgod);
  			jq('#kohbou').val("");jq('#kohbou').val(data[i].kohbou);
  			jq('#kohkk').val("");jq('#kohkk').val(data[i].kohkk);
  			jq('#kohlos').val("");jq('#kohlos').val(data[i].kohlos);
  			jq('#kohman').val("");jq('#kohman').val(data[i].kohman);
  			jq('#kohls1').val("");jq('#kohls1').val(data[i].kohls1);
  			jq('#koh421').val("");jq('#koh421').val(data[i].koh421);
  			jq('#kohls2').val("");jq('#kohls2').val(data[i].kohls2);
  			jq('#koh422').val("");jq('#koh422').val(data[i].koh422);
  			jq('#kohls3').val("");jq('#kohls3').val(data[i].kohls3);
  			jq('#koh423').val("");jq('#koh423').val(data[i].koh423);
  			//for a future update
  			jq('#updateId').val("");jq('#updateId').val(data[i].kohavd);
	  			
  			
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
    	  "scrollY": "100px",
    	  "scrollCollapse":  false,
    	  //"columnDefs": [{ "type": "num", "targets": 1 }],
    	  //"order": [[ 1, "asc" ]],
    	  "lengthMenu": [ 75, 100]
  	  });
      
      //event on input field for search
      jq('input.mainList_filter').on( 'keyup click', function () {
      		filterGlobal();
      });
  	
  });
 
  