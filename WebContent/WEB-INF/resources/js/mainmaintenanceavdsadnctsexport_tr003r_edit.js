  //this variable is a global jQuery var instead of using "$" all the time. Very handy
  var jq = jQuery.noConflict();
  var counterIndex = 0;
  var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";
  
  function setBlockUI(element){
	  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
  }
 
  jq(function() {
	  
	  //Sender EDI
	  jq('#s0004IdLink').click(function() {
		  jq('#s0004IdLink').attr('target','_blank');
		  window.open('mainmaintenance_childwindow_edi.do?action=doFind&inid=' + jq('#s0004').val() + '&ctype=s0004', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	  });
	  jq('#s0004IdLink').keypress(function(e){ //extra feature for the end user
  		  if(e.which == 13) {
  			  jq('#s0004IdLink').click();
  		  }
  	  });
	  
	  //Receiver EDI
	  jq('#s0010IdLink').click(function() {
		  jq('#s0010IdLink').attr('target','_blank');
		  window.open('mainmaintenance_childwindow_edi.do?action=doFind&inid=' + jq('#s0010').val() + '&ctype=s0010', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	  });
	  jq('#s0010IdLink').keypress(function(e){ //extra feature for the end user
  		  if(e.which == 13) {
  			  jq('#s0010IdLink').click();
  		  }
  	  });
	  
	  //Avd.
	  jq('#thavdIdLink').click(function() {
		  jq('#thavdIdLink').attr('target','_blank');
		  window.open('mainmaintenanceavd_childwindow_syfa14r.do?action=doFind&nealist=1' + '&ctype=thavd', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	  });
	  jq('#thavdIdLink').keypress(function(e){ //extra feature for the end user
  		  if(e.which == 13) {
  			  jq('#thavdIdLink').click();
  		  }
  	  });
	  
	  //----------------
	  //CUSTOMER search
	  //----------------
	  //Avs
	  jq('#thnasIdLink').click(function() {
		  jq('#thnasIdLink').attr('target','_blank');
		  window.open('mainmaintenance_childwindow_customer.do?action=doFind&sonavn=' + jq('#thnas').val() + "&knr=" + jq('#thkns').val() + '&ctype=thnas', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	  });
	  jq('#thnasIdLink').keypress(function(e){ //extra feature for the end user
  		  if(e.which == 13) {
  			  jq('#thnasIdLink').click();
  		  }
  	  });
	  //Mottaker
	  jq('#thnakIdLink').click(function() {
		  jq('#thnakIdLink').attr('target','_blank');
		  window.open('mainmaintenance_childwindow_customer.do?action=doFind&sonavn=' + jq('#thnak').val() + "&knr=" + jq('#thknk').val() + '&ctype=thnak', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	  });
	  jq('#thnakIdLink').keypress(function(e){ //extra feature for the end user
  		  if(e.which == 13) {
  			  jq('#thnakIdLink').click();
  		  }
  	  });
	  //Ansvarlig
	  jq('#thnaaIdLink').click(function() {
		  jq('#thnaaIdLink').attr('target','_blank');
		  window.open('mainmaintenance_childwindow_customer.do?action=doFind&sonavn=' + jq('#thnaa').val() + '&ctype=thnaa', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	  });
	  jq('#thnaaIdLink').keypress(function(e){ //extra feature for the end user
  		  if(e.which == 13) {
  			  jq('#thnaaIdLink').click();
  		  }
  	  });
	  //Avs - Sikkerhed
	  jq('#thnassIdLink').click(function() {
		  jq('#thnassIdLink').attr('target','_blank');
		  window.open('mainmaintenance_childwindow_customer.do?action=doFind&sonavn=' + jq('#thnass').val() + "&knr=" + jq('#thknss').val() + '&ctype=thnass', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	  });
	  jq('#thnassIdLink').keypress(function(e){ //extra feature for the end user
  		  if(e.which == 13) {
  			  jq('#thnassIdLink').click();
  		  }
  	  });
	  //Mottak. - Sikkerhed
	  jq('#thnaksIdLink').click(function() {
		  jq('#thnaksIdLink').attr('target','_blank');
		  window.open('mainmaintenance_childwindow_customer.do?action=doFind&sonavn=' + jq('#thnaks').val() + "&knr=" + jq('#thknks').val() + '&ctype=thnaks', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	  });
	  jq('#thnaksIdLink').keypress(function(e){ //extra feature for the end user
  		  if(e.which == 13) {
  			  jq('#thnaksIdLink').click();
  		  }
  	  });
	  //Carrier - Sikkerhed
	  jq('#thnatIdLink').click(function() {
		  jq('#thnatIdLink').attr('target','_blank');
		  window.open('mainmaintenance_childwindow_customer.do?action=doFind&sonavn=' + jq('#thnat').val() + "&knr=" + jq('#thknt').val() + '&ctype=thnat', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	  });
	  jq('#thnatIdLink').keypress(function(e){ //extra feature for the end user
  		  if(e.which == 13) {
  			  jq('#thnatIdLink').click();
  		  }
  	  });
	  
	  /*
	  //Tullkontor (code id = 106)
	  jq('#titsbIdLink').click(function() {
		  jq('#titsbIdLink').attr('target','_blank');
		  window.open('mainmaintenanceavdsad_childwindow_generalcodes.do?action=doFind&tkunik=106' + "&tkkode=" + jq('#titsb').val() + '&ctype=titsb', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	  });
	  jq('#titsbIdLink').keypress(function(e){ //extra feature for the end user
  		  if(e.which == 13) {
  			  jq('#tinaIdLink').click();
  		  }
  	  });
	  */
  	  
  });
  
  