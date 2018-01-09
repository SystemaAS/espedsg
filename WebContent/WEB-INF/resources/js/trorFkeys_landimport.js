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
  		 //
  		 var f13 = 124;
  		 var f24 = 135;
	  
  		 //check shiftKey (Apple machines do use this ...	
  		 if(event.shiftKey){
  			var charCode = (evt.which) ? evt.which : event.keyCode; 
  			//alert(charCode);
  			/* Edge has F24 as OS-command on browser
  			if (charCode == f12){ //shift + F12 = F24 --> in apple
  	  			 //More 
  	   			 setBlockUIKeys();
  	   			 window.location.href = "tror_mainorderlandimport_more.do?avd=" + jq('#fkeysavd').val() + "&sign=" + jq('#fkyessign').val() + "&opd=" + jq('#fkyesopd').val();	 
  			}*/
  			
  		 }else{
	  		 var charCode = (evt.which) ? evt.which : event.keyCode;
	  		 //debug ->
	  		 //alert(charCode);
	  		 if (charCode == f9){
	  			 //Faktura link
	  			setBlockUIKeys();
	  			window.location.href = "tror_" + jq('#fkyessubsys').val() + "_invoice.do?action=doFetch&heavd=" + jq('#fkeysavd').val() + "&heopd=" + jq('#fkeysopd').val(); 
	  		 
	  		 }else if (charCode == f10){
	  			 //Notisblock link 
	  			setBlockUIKeys();
	  			var subsys = "tror_li";
	  			if(jq('#fkyessubsys').val() == 'mainorderlandexport'){
	  				subsys = "tror_le";
	  			}
	  			window.location.href = "editNotisblock.do?action=doFetch&subsys=" + subsys + "&avd=" + jq('#fkeysavd').val() + "&opd=" + jq('#fkeysopd').val() + "&sign=" + jq('#fkeyssign').val(); 
	  		 
	  		 }else if (charCode == f7){
	  			 //EDI-logg link 
	  			 setBlockUIKeys();
	  			 window.location.href = "tror_mainorderland_frisokvei.do?action=doFetch&avd=" + jq('#fkeysavd').val() + + "&sign=" + jq('#fkeysign').val() + "&opd=" + jq('#fkeysopd').val() ; 
		  		 
		  	}else if (charCode == f8){
	  			 //Archive link 
	  			 setBlockUIKeys();
	  			window.location.href = "tror_mainorderland_budget.do?avd=" + jq('#fkeysavd').val() + + "&sign=" + jq('#fkeyssign').val() + "&opd=" + jq('#fkeysopd').val() ; 
		  		 
		  	}else if (charCode == f2){
	  			 //Orderlist link 
	  			setBlockUIKeys();
	  			window.location.href = "tror_mainorderlist.do?action=doFind"; 
	  		 
	  		 }else if (charCode == f4){
	  			 //Order link 
	  			setBlockUIKeys();
	  			window.location.href = "tror_" + jq('#fkyessubsys').val() + ".do?action=doFetch&heavd=" + jq('#fkeysavd').val() + "&heopd=" + jq('#fkeysopd').val();
	  		 
	  		 }else if (charCode == f3){
	  			 //Fraktbrev
	   			setBlockUIKeys();
	   			window.location.href = "tror_mainorderland_freightbill_gate.do?dfavd=" + jq('#fkeysavd').val() + "&sign=" + jq('#fkeyssign').val() + "&dfopd=" + jq('#fkeysopd').val();
	   		 
	   		
	  		 }/*else if (charCode == f24){ //Edge har detta som OS-command
  	  			 //More 
  	   			 setBlockUIKeys();
  	   			 window.location.href = "tror_mainorderlandimport_more.do?avd=" + jq('#fkeysavd').val() + "&sign=" + jq('#fkeyssign').val() + "&opd=" + jq('#fkeysopd').val();	 
  			}*/
  		 }	 
  	 });
  	