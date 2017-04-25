  //this variable is a global jQuery var instead of using "$" all the time. Very handy
  var jq = jQuery.noConflict();
  var counterIndex = 0;
  var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";
  
  function setBlockUI(element){
	  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
  }
 
  jq(function() {
	  jq("#thdta").datepicker({ 
		  dateFormat: 'yymmdd' 	  
	  });
	  jq("#thddt").datepicker({ 
		  dateFormat: 'yymmdd' 	  
	  });
	  
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
		  window.open('mainmaintenanceavd_childwindow_syfa14r.do?action=doFind&snealist=1' + '&ctype=thavd', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	  });
	  jq('#thavdIdLink').keypress(function(e){ //extra feature for the end user
  		  if(e.which == 13) {
  			  jq('#thavdIdLink').click();
  		  }
  	  });
	  
	  //----------------
	  //CUSTOMER search
	  //----------------
	  //Ansvarlig
	  jq('#tinaIdLink').click(function() {
		  jq('#thnasIdLink').attr('target','_blank');
		  window.open('mainmaintenance_childwindow_customer.do?action=doFind&sonavn=' + jq('#tina').val() + "&knr=" + jq('#tikn').val() + '&ctype=tina', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	  });
	  jq('#tinaIdLink').keypress(function(e){ //extra feature for the end user
  		  if(e.which == 13) {
  			  jq('#tinaIdLink').click();
  		  }
  	  });
	  
	  
	  
	  //Tullkontor (code id = 106)
	  jq('#thcatsIdLink').click(function() {
		  jq('#thcatsIdLink').attr('target','_blank');
		  window.open('mainmaintenanceavdskatncts_childwindow_generalcodes.do?action=doFind&tkunik=106' + "&tkkode=" + jq('#thcats').val() + '&ctype=thcats', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	  });
	  jq('#thcatsIdLink').keypress(function(e){ //extra feature for the end user
  		  if(e.which == 13) {
  			  jq('#thcatsIdLink').click();
  		  }
  	  });
	  jq('#thtsbIdLink').click(function() {
		  jq('#thtsbIdLink').attr('target','_blank');
		  window.open('mainmaintenanceavdskatncts_childwindow_generalcodes.do?action=doFind&tkunik=106' + "&tkkode=" + jq('#thtsb').val() + '&ctype=thtsb', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	  });
	  jq('#thtsbIdLink').keypress(function(e){ //extra feature for the end user
  		  if(e.which == 13) {
  			  jq('#thtsbIdLink').click();
  		  }
  	  });
	  //(1)
	  jq('#thtsd1IdLink').click(function() {
		  jq('#thtsd1IdLink').attr('target','_blank');
		  window.open('mainmaintenanceavdskatncts_childwindow_generalcodes.do?action=doFind&tkunik=106' + "&tkkode=" + jq('#thtsd1').val() + '&ctype=thtsd1', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	  });
	  jq('#thtsd1IdLink').keypress(function(e){ //extra feature for the end user
  		  if(e.which == 13) {
  			  jq('#thtsd1IdLink').click();
  		  }
  	  });
	  //(2)
	  jq('#thtsd2IdLink').click(function() {
		  jq('#thtsd2IdLink').attr('target','_blank');
		  window.open('mainmaintenanceavdskatncts_childwindow_generalcodes.do?action=doFind&tkunik=106' + "&tkkode=" + jq('#thtsd2').val() + '&ctype=thtsd2', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	  });
	  jq('#thtsd2IdLink').keypress(function(e){ //extra feature for the end user
  		  if(e.which == 13) {
  			  jq('#thtsd2IdLink').click();
  		  }
  	  });
	  //(3)
	  jq('#thtsd3IdLink').click(function() {
		  jq('#thtsd3IdLink').attr('target','_blank');
		  window.open('mainmaintenanceavdskatncts_childwindow_generalcodes.do?action=doFind&tkunik=106' + "&tkkode=" + jq('#thtsd3').val() + '&ctype=thtsd3', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	  });
	  jq('#thtsd3IdLink').keypress(function(e){ //extra feature for the end user
  		  if(e.which == 13) {
  			  jq('#thtsd3IdLink').click();
  		  }
  	  });
	  //(4)
	  jq('#thtsd4IdLink').click(function() {
		  jq('#thtsd4IdLink').attr('target','_blank');
		  window.open('mainmaintenanceavdskatncts_childwindow_generalcodes.do?action=doFind&tkunik=106' + "&tkkode=" + jq('#thtsd4').val() + '&ctype=thtsd4', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	  });
	  jq('#thtsd4IdLink').keypress(function(e){ //extra feature for the end user
  		  if(e.which == 13) {
  			  jq('#thtsd4IdLink').click();
  		  }
  	  });
	  //(5)
	  jq('#thtsd5IdLink').click(function() {
		  jq('#thtsd5IdLink').attr('target','_blank');
		  window.open('mainmaintenanceavdskatncts_childwindow_generalcodes.do?action=doFind&tkunik=106' + "&tkkode=" + jq('#thtsd5').val() + '&ctype=thtsd5', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	  });
	  jq('#thtsd5IdLink').keypress(function(e){ //extra feature for the end user
  		  if(e.which == 13) {
  			  jq('#thtsd5IdLink').click();
  		  }
  	  });
	  //(6)
	  jq('#thtsd6IdLink').click(function() {
		  jq('#thtsd6IdLink').attr('target','_blank');
		  window.open('mainmaintenanceavdskatncts_childwindow_generalcodes.do?action=doFind&tkunik=106' + "&tkkode=" + jq('#thtsd6').val() + '&ctype=thtsd6', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	  });
	  jq('#thtsd6IdLink').keypress(function(e){ //extra feature for the end user
  		  if(e.which == 13) {
  			  jq('#thtsd6IdLink').click();
  		  }
  	  });
	  //(7)
	  jq('#thtsd7IdLink').click(function() {
		  jq('#thtsd7IdLink').attr('target','_blank');
		  window.open('mainmaintenanceavdskatncts_childwindow_generalcodes.do?action=doFind&tkunik=106' + "&tkkode=" + jq('#thtsd7').val() + '&ctype=thtsd7', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	  });
	  jq('#thtsd7IdLink').keypress(function(e){ //extra feature for the end user
  		  if(e.which == 13) {
  			  jq('#thtsd7IdLink').click();
  		  }
  	  });
	  //(8)
	  jq('#thtsd8IdLink').click(function() {
		  jq('#thtsd8IdLink').attr('target','_blank');
		  window.open('mainmaintenanceavdskatncts_childwindow_generalcodes.do?action=doFind&tkunik=106' + "&tkkode=" + jq('#thtsd8').val() + '&ctype=thtsd8', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	  });
	  jq('#thtsd8IdLink').keypress(function(e){ //extra feature for the end user
  		  if(e.which == 13) {
  			  jq('#thtsd8IdLink').click();
  		  }
  	  });
	  
  	  
  });
  
  