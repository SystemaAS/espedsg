	//===========================================
	//General functions for tror landimport- AJAX
	//===========================================
	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	 var jq = jQuery.noConflict();
  	 var counterIndex = 0;
  	 var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";
  	 //rest
  
  	 function setBlockUIKeys(){
  		 jq.blockUI({
  			css: { 
  	            fontSize:'16px'
  	        },
  			 message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT}
  		 );
  	 }
  	
  	 //KEY's shortcuts
  	 jq(document).keydown(function(evt) {
  		 var f1 = 112; 
  		 var f2 = 113; 
  		 var f3 = 114; 
  		 var f4 = 115; 
  		 var f5 = 116;
  		 var f6 = 117; 
  		 var f7 = 118; 
  		 var f8 = 119; 
  		 var f9 = 120; 
  		 var f10 = 121;
  		 var f11 = 122; 
  		 var f12 = 123;
  		 
  		 var f18 = 80;
	  
  		 var charCode = (evt.which) ? evt.which : event.keyCode;
  		 //debug ->
  		 //alert(charCode);
  		 if (charCode == f10){
  			 //Faktura link
  			setBlockUIKeys();
  			window.location.href = "tror_mainorderlandimport_invoice.do?action=doFetch&heavd=" + jq('#heavd').val() + "&heopd=" + jq('#heopd').val(); 
  		 
  		 }else if (charCode == f9){
  			 //Notisblock link 
  			setBlockUIKeys();
  			window.location.href = "editNotisblock.do?action=doFetch&subsys=tror_li&avd=" + jq('#heavd').val() + "&opd=" + jq('#heopd').val() + "&sign=" + jq('#hesg').val(); 
  		 
  		 }else if (charCode == f2){
  			 //Orderlist link 
  			setBlockUIKeys();
  			window.location.href = "tror_mainorderlist.do?action=doFind"; 
  		 
  		 }else if (charCode == f4){
  			 //Order link 
  			setBlockUIKeys();
  			window.location.href = "tror_mainorderlandimport.do?action=doFetch&heavd=" + jq('#heavd').val() + "&heopd=" + jq('#heopd').val();
  		 
  		 }
  	 });
  	