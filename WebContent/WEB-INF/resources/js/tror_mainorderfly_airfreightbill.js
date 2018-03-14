  //this variable is a global jQuery var instead of using "$" all the time. Very handy
  var jq = jQuery.noConflict();
  var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";

  function setBlockUI(element){
	  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
  }
  
  
  jq(function() {
	  //custom validity
	  /*
	  jq('#trsta4').focus(function() {
	    	if(jq('#trsta4').val()!=''){
	    		refreshCustomValidity(jq('#trsta4')[0]);
	  		}
	  });
	  
	  jq('#hekns').focus(function() {
	    	if(jq('#hekns').val()!=''){
	    		refreshCustomValidity(jq('#hekns')[0]);
	  		}
	  });
	  
	  jq('#hesdf').focus(function() {
	    	if(jq('#hesdf').val()!=''){
	    		refreshCustomValidity(jq('#hesdf')[0]);
	  		}
	  });
	  jq('#hesdt').focus(function() {
	    	if(jq('#hesdt').val()!=''){
	    		refreshCustomValidity(jq('#hesdt')[0]);
	  		}
	  });
	  jq('#hefr').focus(function() {
	    	if(jq('#hefr').val()!=''){
	    		refreshCustomValidity(jq('#hefr')[0]);
	  		}
	  });
	  jq('#domoms').focus(function() {
	    	if(jq('#domoms').val()!=''){
	    		refreshCustomValidity(jq('#domoms')[0]);
	  		}
	  });
	  */
	  
	  //backButton
	  jq('#backToFlyfraktbrevGateButton').click(function() {
		  jq.blockUI({ message : BLOCKUI_OVERLAY_MESSAGE_DEFAULT });
		  window.location = "tror_mainorderfly_airfreightbill_imp_gate.do?imavd=" + jq('#dfavd').val() + "&sign=" + jq('#sign').val() + "&imopd=" + jq('#dfopd').val() ;
	  });
	  
	  
	  /*
	  //START - CHILDWINDOWS
	  //Produktkode
	  jq('#prodKodeIdLink').click(function() {
		  jq('#prodKodeIdLink').attr('target','_blank');
		  window.open('tror_mainorderflyimport_childwindow_airproducts.do?action=doFind',"produktKodeWin","top=300px,left=400px,height=600px,width=900px,scrollbars=no,status=no,location=no");
	  });
	  jq('#prodKodeIdLink').keypress(function(e){ //extra feature for the end user
		  if(e.which == 13) {
			  jq('#prodKodeIdLink').click();
		  }
	  });
	  //Flyselskap
	  jq('#flyselskapIdLink').click(function() {
		  jq('#flyselskapIdLink').attr('target','_blank');
		  window.open('tror_mainorderflyimport_childwindow_airlines.do?action=doFind',"airlineWin","top=300px,left=400px,height=700px,width=900px,scrollbars=no,status=no,location=no");
	  });
	  jq('#flyselskapIdLink').keypress(function(e){ //extra feature for the end user
		  if(e.which == 13) {
			  jq('#flyselskapIdLink').click();
		  }
	  });
	  //END - CHILDWINDOWS
	  */
	  
  });
	  
	  