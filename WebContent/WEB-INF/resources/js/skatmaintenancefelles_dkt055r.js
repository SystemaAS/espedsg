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
	  
	  //UNB Sender TEST
	  jq('#dktf_0004tIdLink').click(function() {
		  jq('#dktf_0004tIdLink').attr('target','_blank');
		  window.open('mainmaintenance_childwindow_edi.do?action=doFind&id=' + jq('#dktf_0004t').val() + '&ctype=dktf_0004t', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	  });
	  jq('#dktf_0004tIdLink').keypress(function(e){ //extra feature for the end user
  		  if(e.which == 13) {
  			  jq('#dktf_0004tIdLink').click();
  		  }
  	  });
	  //UNB Receiver TEST
	  jq('#dktf_0010tIdLink').click(function() {
		  jq('#dktf_0010tIdLink').attr('target','_blank');
		  window.open('mainmaintenance_childwindow_edi.do?action=doFind&id=' + jq('#dktf_0010t').val() + '&ctype=dktf_0010t', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	  });
	  jq('#dktf_0010tIdLink').keypress(function(e){ //extra feature for the end user
  		  if(e.which == 13) {
  			  jq('#dktf_0010tIdLink').click();
  		  }
  	  });
	  
	//UNB Sender PROD
	  jq('#dktf_0004pIdLink').click(function() {
		  jq('#dktf_0004pIdLink').attr('target','_blank');
		  window.open('mainmaintenance_childwindow_edi.do?action=doFind&id=' + jq('#dktf_0004p').val() + '&ctype=dktf_0004p', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	  });
	  jq('#dktf_0004pIdLink').keypress(function(e){ //extra feature for the end user
  		  if(e.which == 13) {
  			  jq('#dktf_0004pIdLink').click();
  		  }
  	  });
	  //UNB Receiver PROD
	  jq('#dktf_0010pIdLink').click(function() {
		  jq('#dktf_0010pIdLink').attr('target','_blank');
		  window.open('mainmaintenance_childwindow_edi.do?action=doFind&id=' + jq('#dktf_0010p').val() + '&ctype=dktf_0010p', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	  });
	  jq('#dktf_0010pIdLink').keypress(function(e){ //extra feature for the end user
  		  if(e.which == 13) {
  			  jq('#dktf_0010pIdLink').click();
  		  }
  	  });
	  
  });