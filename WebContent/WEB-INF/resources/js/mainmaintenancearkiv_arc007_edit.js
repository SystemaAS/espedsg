  //this variable is a global jQuery var instead of using "$" all the time. Very handy
  var jq = jQuery.noConflict();
  var counterIndex = 0;
  var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";
  
  function setBlockUI(element){
	  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
  }
 
  jq(function() {

	    jq('#arklagIdLink').click(function() {
	    	alert("Hej arklag");
	    	jq('#arklagIdLink').attr('target','_blank');
	    	window.open('mainmaintenance_vkund_edit_childwindow_codes.do?caller=arklag', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	  
	  
  });
  
  