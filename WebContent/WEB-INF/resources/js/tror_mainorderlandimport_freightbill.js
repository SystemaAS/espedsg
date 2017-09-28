  //this variable is a global jQuery var instead of using "$" all the time. Very handy
  var jq = jQuery.noConflict();
  var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";

  
  //FULL TODO
  
  function setBlockUI(element){
	  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
  }
  
//Global functions
  function g_getCurrentYearStr(){
	  return new Date().getFullYear().toString();
  }
  function g_getCurrentMonthStr(){
	  var currentMonth = new Date().getMonth() + 1;
	  var currentMonthStr = currentMonth.toString();
	  if (currentMonth < 10) { currentMonthStr = '0' + currentMonth; }
	  return currentMonthStr;
  }
  
  jq(function() {
	  //custom validity
	  jq('#dfsg').focus(function() {
	    	if(jq('#dfsg').val()!=''){
	    		refreshCustomValidity(jq('#dfsg')[0]);
	  		}
	  });
	  jq('#dfbela').focus(function() {
	    	if(jq('#dfbela').val()!=''){
	    		refreshCustomValidity(jq('#dfbela')[0]);
	  		}
	  });
  });  