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
  	  
  	  //custom validity
	  jq('#dktard02').blur(function() {
	  		if(jq('#dktard02').val()!=''){
	    		refreshCustomValidity(jq('#dktard02')[0]);
	  		}
	  });
  });