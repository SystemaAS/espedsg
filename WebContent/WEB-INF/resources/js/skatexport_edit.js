	//===========================================
	//General functions for this JSP side - AJAX
	//===========================================
	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict();
  	var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";
  	
  	//Overlay on tab (to mark visually a delay...)
    jq(function() {
      jq('#alinkTopicList').click(function() { 
  		  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
  	  });
      jq('#alinkInvoices').click(function() { 
  		  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
  	  });
      
  	  jq('#alinkItemLines').click(function() { 
  		  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
  	  });
  	  jq('#alinkLogging').click(function() { 
		  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
	  });
  	  jq('#alinkArchive').click(function() { 
		  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
	  });
  	  
  	  jq( "#submit" ).click(function( event ) {
  		  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT });
  	  });
  	  jq( "#submit2" ).click(function( event ) {
		  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT });
	  });
  	  
  	  jq('#getFakturaListSumButton').click(function() { 
  		if(jq('#fakturaListTotSum').val()!='' && jq('#fakturaListTotValidCurrency').val()!='' ){  
	  		jq('#dkeh_222').val(jq('#fakturaListTotSum').val());
	  		jq('#dkeh_221').val(jq('#fakturaListTotValidCurrency').val());	
	  		//jq('#KURS???').val(jq('#finansOpplysningarTotKurs').val());
	  		
	  		//for backwards compatibility meaning: ref till fakturalista
  			jq('#dkeh_28b').val("FLERE FAKTURAER");
  			
  		}
	  });
  	  
  	  
  	  	//----------------
	  	//CUSTOMER search
	  	//----------------
	    //SENDER
	    jq('#dkeh_02bIdLink').click(function() {
	    	jq('#dkeh_02bIdLink').attr('target','_blank');
	    	window.open('skat_childwindow_customer.do?action=doFind&sonavn=' + jq('#dkeh_02b').val() + '&ctype=dkeh_02b', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#dkeh_02bIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#dkeh_02bIdLink').click();
			}
	    });
	    //MODTAGER
	    jq('#dkeh_08bIdLink').click(function() {
	    	jq('#dkeh_08bIdLink').attr('target','_blank');
	    	window.open('skat_childwindow_customer.do?action=doFind&sonavn=' + jq('#dkeh_08b').val() + '&ctype=dkeh_08b', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#dkeh_08bIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#dkeh_08bIdLink').click();
			}
	    });
	    //CARRIER
	    jq('#dkeh_treoIdLink').click(function() {
	    	jq('#dkeh_treoIdLink').attr('target','_blank');
	    	window.open('skat_childwindow_customer.do?action=doFind&sonavn=' + jq('#dkeh_treo').val() + '&ctype=dkeh_treo', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#dkeh_treoIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#dkeh_treoIdLink').click();
			}
	    });
	    //REPRESENTAT ved udpassage
	    jq('#dkeh_renaIdLink').click(function() {
	    	jq('#dkeh_renaIdLink').attr('target','_blank');
	    	window.open('skat_childwindow_customer.do?action=doFind&sonavn=' + jq('#dkeh_rena').val() + '&ctype=dkeh_rena', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#dkeh_renaIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#dkeh_renaIdLink').click();
			}
	    });
	    
	    //Eksp.sted
	  	  jq('#dkeh_a1IdLink').click(function() {
	    	jq('#dkeh_a1IdLink').attr('target','_blank');
	    	window.open('skatexport_edit_childwindow_generalcodes.do?action=doInit&type=103&ctype=dkeh_a1', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	  	  });
	  	  jq('#dkeh_a1IdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#dkeh_a1IdLink').click();
			}
	  	  });
	  	  
	      //Valuta
	  	  jq('#dkeh_221IdLink').click(function() {
	    	jq('#dkeh_221IdLink').attr('target','_blank');
	    	window.open('skatexport_edit_childwindow_generalcodes.do?action=doInit&type=107&ctype=dkeh_221', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	  	  });
	  	  jq('#dkeh_221IdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#dkeh_221IdLink').click();
			}
	  	  });
	  	  
	  	  //Udgangstoldsted
	  	  jq('#dkeh_29IdLink').click(function() {
	    	jq('#dkeh_29IdLink').attr('target','_blank');
	    	window.open('skatexport_edit_childwindow_tullkontor.do?action=doInit&type=117&ctype=dkeh_29&tkkode=' + jq('#dkeh_29').val(), "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	  	  });
	  	  jq('#dkeh_29IdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#dkeh_29IdLink').click();
			}
	  	  });
	  	  //Udgangstoldsted
	  	  jq('#dkeh_indIdLink').click(function() {
	    	jq('#dkeh_indIdLink').attr('target','_blank');
	    	window.open('skatexport_edit_childwindow_tullkontor.do?action=doInit&type=117&ctype=dkeh_ind&tkkode=' + jq('#dkeh_ind').val(), "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	  	  });
	  	  jq('#dkeh_indIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#dkeh_indIdLink').click();
			}
	  	  });
  	  
    }); 
    
    //ChildWindow Country Codes
    function triggerChildWindowCountryCodes(record){
    	var idLink = record.id;
    	var id = idLink.replace("IdLink", "");
    	jq(idLink).attr('target','_blank');
    	window.open('skatexport_edit_childwindow_generalcodes.do?action=doInit&type=008&ctype=' + id , "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    }
  	
  	jq(document).ready(function(){
  	    jq(this).scrollTop(0); //needed for Chrome (bug)
  	});
  	
  	jq(function() {
  		jq('#dkeh_222').calculator({ showOn: 'button',  
  			buttonImageOnly: true, buttonImage: 'resources/images/calculator.png', decimalChar: ','});
  	});
  	jq(function() {
  	  //----------------------------------------------------------------------
    	  //including timepicker (jQuery addon to datepicker). Ref to parent JSP.
    	  //----------------------------------------------------------------------
    	  jq("#dkeh_dtm1").datetimepicker({ 
    		  dateFormat: 'yymmdd',
  		  controlType: 'select',
  		  timeFormat: 'HHmm'  
  	  });
    	  //no space characters allowed...
  	  jq('#dkeh_dtm1').change(function() {
  		var val = jq("#dkeh_dtm1").val();
  		jq("#dkeh_dtm1").val(val.replace(' ', ''));
  	  });
  	  jq("#dkeh_dtm2").datetimepicker({ 
  		  dateFormat: 'yymmdd',
		  controlType: 'select',
		  timeFormat: 'HHmm' 
	  });
  	  //no space characters allowed...
  	  jq('#dkeh_dtm2').change(function() {
  		var val = jq("#dkeh_dtm2").val();
  		jq("#dkeh_dtm2").val(val.replace(' ', ''));
  	  });
  	  
  	  jq('#dkeh_dtm2').blur(function() {
  		  if(jq('#dkeh_syst').val() == '11'){
  			jq('#dkeh_ajou').val("9");
  		  }
  	  });
  	  
  	  jq("#dkeh_28c").datepicker({ 
		  dateFormat: 'yymmdd'  
	  });
	  
  	  jq("#dkeh_ymd1").datetimepicker({ 
  		  dateFormat: 'yymmdd',
  		  controlType: 'select',
  		  timeFormat: 'HHmm'
	  });
  	  //no space characters allowed...
  	  jq('#dkeh_ymd1').change(function() {
  		var val = jq("#dkeh_ymd1").val();
  		jq("#dkeh_ymd1").val(val.replace(' ', ''));
  	  });
  	  
  	  jq("#dkeh_ymd2").datetimepicker({ 
		  dateFormat: 'yymmdd',
		  controlType: 'select',
		  timeFormat: 'HHmm'
	  });
  	  //no space characters allowed...
  	  jq('#dkeh_ymd2').change(function() {
  		var val = jq("#dkeh_ymd2").val();
  		jq("#dkeh_ymd2").val(val.replace(' ', ''));
  	  });
  	  
  	  jq("#dkeh_ymd3").datepicker({ 
		  dateFormat: 'yymmdd'
	  });
  	  
    });
  	
  	//----------------
  	//onChange events
  	//----------------
  	jq(function() { 
	    jq('#avd').change(function() {
	    		
			jq.getJSON('getSpecificTopicOmbud_SkatExport.do', {
				applicationUser : jq('#applicationUser').val(),
				avd : jq('#avd').val(),
				ajax : 'true'
			}, function(data) {
				//alert("Hello");
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					jq('#dkeh_14a').val(data[i].dkea_14a);
					jq('#dkeh_14b').val(data[i].dkea_14b);
				}
			});
	    });
	});
  	
  	
	
	//onChange A.1 Eksped.sted list
	jq(function() { 
	    jq('#toldstedList').change(function() {
	    		jq('#dkeh_a1').val(jq('#toldstedList').val());	
	    });
	});
	//onChange 17a. Bestemmelsesland list
	jq(function() { 
	    jq('#bestemmelsesland').change(function() {
	    		jq('#dkeh_17a').val(jq('#bestemmelsesland').val());	
	    });
	});
	//onChange S13. Transitland list
	jq(function() { 
	    jq('#transitland').change(function() {
	    		jq('#dkeh_s131').val(jq('#transitland').val());	
	    });
	});
	jq(function() { 
	    jq('#transitland2').change(function() {
	    		jq('#dkeh_s132').val(jq('#transitland2').val());	
	    });
	});
	//onChange 29. Udagangstoldsted list
	jq(function() { 
	    jq('#udgangstoldsted').change(function() {
	    		jq('#dkeh_29').val(jq('#udgangstoldsted').val());	
	    });
	});
	//onChange Indladningssted list
	jq(function() { 
	    jq('#indladningssted').change(function() {
	    		jq('#dkeh_ind').val(jq('#indladningssted').val());	
	    });
	});
  	  
	/*
  	//onChange sign list
	jq(function() { 
	    jq('#sign').change(function() {
	    		
	    	jq.getJSON('getSignatureName_TdsImport.do', {
				applicationUser : jq('#applicationUser').val(),
				avd : jq('#avd').val(),
				sign : jq('#sign').val(),
				ajax : 'true'
			}, function(data) {
				//alert("Hello");
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					jq('#svih_omha').val(data[i].svth_namn);
				}
			});
	    });
	});
  	*/
	
	
	//-----------------------
  	//INIT CUSTOMER Object
  	//-----------------------
	var map = {};
  	//init the customer object in javascript (will be put into a map)
  	var customer = new Object();
  	//fields
  	customer.kundnr = "";customer.knavn = "";customer.eori = "";customer.adr1 = "";
  	customer.adr2 = "";customer.adr3 = "";customer.postnr = "";customer.syland = "";
  	customer.kpers = "";customer.tlf = "";
  	//---------------------------------------------------------
  	//FETCH CUSTOMER from SENDER  html area
  	//---------------------------------------------------------
	function searchSenderOwnWindow() {
		jq(function() {
			jq.getJSON('searchCustomer_Skat.do', {
				applicationUser : jq('#applicationUser').val(),
				customerName : jq('#search_dkeh_02b').val(),
				customerNumber : jq('#search_dkeh_avkn').val(),
				ajax : 'true'
			}, function(data) {
				//alert("Hello");
				var html = '<option selected value="">-Select-</option>';
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					html += '<option value="' + data[i].kundnr + '">' + data[i].knavn + '</option>';
					customer = new Object();
					customer.kundnr = data[i].kundnr;
					customer.knavn = data[i].knavn;
					customer.eori = data[i].eori;
					customer.adr1 = data[i].adr1;
					customer.adr2 = data[i].adr2;
					customer.adr3 = data[i].adr3;
					customer.postnr = data[i].postnr;
					customer.kpers = data[i].kpers;
					customer.tlf = data[i].tlf;
					customer.syland = data[i].syland;
				  	
					//put the object in map now with customerNumber as key
					map[customer.kundnr] = customer;
				}
				//now that we have our options, give them to our select
				jq('#senderList').html(html);
			});
		});
	}
	//onChange sender list
	jq(function() { 
	    jq('#senderList').change(function() {
	      //init fields
		  jq('#dkeh_02b').val("");
		  jq('#dkeh_02c').val("");
		  jq('#dkeh_02d').val("");
		  jq('#dkeh_02e').val("");
		  jq('#dkeh_02f').val("");
		  //now populate (if applicable)
		  var key = jq('#senderList').val();
		  jq('#dkeh_avkn').val(key);
		  customer = map[key];
		  jq('#dkeh_02b').val(customer.knavn);
		  jq('#dkeh_02c').val(customer.adr1);
		  jq('#dkeh_02d').val(customer.postnr);
		  jq('#dkeh_02e').val(customer.adr3);
		  jq('#dkeh_02f').val(customer.syland);
	    });
	});
	
	//onClick for Sender dialog
	jq(function() { 
	    jq('#searchCustomerCloseCancel').click(function() {
	      //rescue the original fields
	      jq('#dkeh_avkn').val(jq("#orig_dkeh_avkn").val());	
		  jq('#dkeh_02b').val(jq("#orig_dkeh_02b").val());
		  jq('#dkeh_02c').val(jq("#orig_dkeh_02c").val());
		  jq('#dkeh_02d').val(jq("#orig_dkeh_02d").val());
		  jq('#dkeh_02e').val(jq("#orig_dkeh_02e").val());
		  jq('#dkeh_02f').val(jq("#orig_dkeh_02f").val());
	    });
	});
	//----------------------------------
	//Events Sender (SEARCH window)
	//----------------------------------
	//img click
	jq(function() {	    
		jq('#imgCustomerSearch').click(function(){
    			jq("#search_dkeh_avkn").focus();
    		});
	});
	
	jq(function() {	    
		jq('#search_dkeh_avkn').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchSenderOwnWindow);
			}			
    		});
		jq('#search_dkeh_02b').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchSenderOwnWindow);
			}			
    		});
	});
	//--------------------------------------------------------------------------------------
	//Extra behavior for Customer number ( without using (choose from list) extra roundtrip)
	//--------------------------------------------------------------------------------------
	jq(function() { 
	    jq('#dkeh_avkn').blur(function() {
		    	var avknValue = jq('#dkeh_avkn').val();
	    		if(avknValue!=null && avknValue!=""){
		    		jq.getJSON('searchCustomer_Skat.do', {
					applicationUser : jq('#applicationUser').val(),
					customerName : "",
					customerNumber : jq('#dkeh_avkn').val(),
					ajax : 'true'
				}, function(data) {
					//alert("Hello");
					var len = data.length;
					for ( var i = 0; i < len; i++) {
						//html += '<option value="' + data[i].kundnr + '">' + data[i].knavn + '</option>';
						customer = new Object();
						customer.kundnr = data[i].kundnr;
						customer.knavn = data[i].knavn;
						customer.eori = data[i].eori;
						customer.adr1 = data[i].adr1;
						customer.adr2 = data[i].adr2;
						customer.adr3 = data[i].adr3;
						customer.postnr = data[i].sypoge;//data[i].postnr; DK=sypoge
						customer.kpers = data[i].kpers;
						customer.tlf = data[i].tlf;
						customer.syland = data[i].syland;
					  	//put the object in map now with customerNumber as key
						map[customer.kundnr] = customer;
					}
					if(len > 0){
						jq('#dkeh_avkn').val(customer.kundnr);
						jq('#dkeh_02a').val(customer.eori);
						jq('#dkeh_02b').val(customer.knavn);
						jq('#dkeh_02c').val(customer.adr1);
						jq('#dkeh_02d').val(customer.postnr);
						jq('#dkeh_02e').val(customer.adr3);
						jq('#dkeh_02f').val(customer.syland);
					}else{
						//init fields
						jq('#dkeh_02a').val("");
						jq('#dkeh_02b').val("");
						jq('#dkeh_02c').val("");
						jq('#dkeh_02d').val("");
						jq('#dkeh_02e').val("");
						jq('#dkeh_02f').val("");
					}
				});
    			}
		});
	});
	

  	//---------------------------------------------------------
	//FETCH CUSTOMER from RECEIVER [MOTTAGARE] html area
  	//---------------------------------------------------------
	function searchReceiverOwnWindow() {
		jq(function() {
			jq.getJSON('searchCustomer_Skat.do', {
				applicationUser : jq('#applicationUser').val(),
				customerName : jq('#search_dkeh_08b').val(),
				customerNumber : jq('#search_dkeh_mokn').val(),
				ajax : 'true'
			}, function(data) {
				var html = '<option selected value="">-Select-</option>';
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					html += '<option value="' + data[i].kundnr + '">' + data[i].knavn + '</option>';
					customer = new Object();
					customer.kundnr = data[i].kundnr;
					customer.knavn = data[i].knavn;
					customer.eori = data[i].eori;
					customer.adr1 = data[i].adr1;
					customer.adr2 = data[i].adr2;
					customer.adr3 = data[i].adr3;
					customer.postnr = data[i].postnr;
					customer.kpers = data[i].kpers;
					customer.tlf = data[i].tlf;
					customer.syland = data[i].syland;
				  	
					//put the object in map now with customerNumber as key
					map[customer.kundnr] = customer;
				}
				//now that we have our options, give them to our select
				jq('#receiverList').html(html);
			});
		});
	}
	//Sets receiver values after user selection
	jq(function() { 
	    jq('#receiverList').change(function() {
		  //init all fields
		  jq('#dkeh_08a').val("");
		  jq('#dkeh_08b').val("");
		  jq('#dkeh_08c').val("");
		  jq('#dkeh_08d').val("");
		  jq('#dkeh_08e').val("");
		  jq('#dkeh_08f').val("");
		  //now populate (if applicable)
		  var key = jq('#receiverList').val();
		  jq('#dkeh_mokn').val(key);
		  customer = map[key];
		  jq('#dkeh_08a').val(customer.eori);
		  jq('#dkeh_08b').val(customer.knavn);
		  jq('#dkeh_08c').val(customer.adr1);
		  jq('#dkeh_08d').val(customer.postnr);
		  jq('#dkeh_08e').val(customer.adr3);
		  jq('#dkeh_08f').val(customer.syland);
		});
	});
	//onClick for Receiver(Mottagare) dialog
	jq(function() { 
	    jq('#searchCustomer10CloseCancel').click(function() {
	      //rescue the original fields
		  jq('#dkeh_avkn').val(jq("#orig_dkeh_avkn").val());	
		  jq('#dkeh_08a').val(jq("#orig_dkeh_08a").val());
		  jq('#dkeh_08b').val(jq("#orig_dkeh_08b").val());
		  jq('#dkeh_08c').val(jq("#orig_dkeh_08c").val());
		  jq('#dkeh_08d').val(jq("#orig_dkeh_08d").val());
		  jq('#dkeh_08e').val(jq("#orig_dkeh_08e").val());
		  jq('#dkeh_08f').val(jq("#orig_dkeh_08f").val());		  
	    });
	});
	//----------------------------------
	//Events Receiver (SEARCH window)
	//----------------------------------
	//img click
	jq(function() {	    
		jq('#imgReceiverSearch').click(function(){
    			jq("#search_dkeh_mokn").focus();
    		});
	});
	
	jq(function() {	    
		jq('#search_dkeh_mokn').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchReceiverOwnWindow);
			}			
    		});
		jq('#search_dkeh_mona').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchReceiverOwnWindow);
			}			
    		});
	});
	//--------------------------------------------------------------------------------------
	//Extra behavior for Customer number ( without using (choose from list) extra roundtrip)
	//--------------------------------------------------------------------------------------
	jq(function() { 
	    jq('#dkeh_mokn').blur(function() {
		    	var moknValue = jq('#dkeh_mokn').val();
	    		if(moknValue!=null && moknValue!=""){
		    		jq.getJSON('searchCustomer_Skat.do', {
					applicationUser : jq('#applicationUser').val(),
					customerName : "",
					customerNumber : jq('#dkeh_mokn').val(),
					ajax : 'true'
				}, function(data) {
					//alert("Hello");
					var len = data.length;
					for ( var i = 0; i < len; i++) {
						//html += '<option value="' + data[i].kundnr + '">' + data[i].knavn + '</option>';
						customer = new Object();
						customer.kundnr = data[i].kundnr;
						customer.knavn = data[i].knavn;
						customer.eori = data[i].eori;
						customer.adr1 = data[i].adr1;
						customer.adr2 = data[i].adr2;
						customer.adr3 = data[i].adr3;
						customer.postnr = data[i].sypoge;//data[i].postnr; DK=sypoge
						customer.kpers = data[i].kpers;
						customer.tlf = data[i].tlf;
						customer.syland = data[i].syland;
					  	//put the object in map now with customerNumber as key
						map[customer.kundnr] = customer;
					}
					if(len > 0){
						jq('#dkeh_mokn').val(customer.kundnr);
						jq('#dkeh_08a').val(customer.eori);
						jq('#dkeh_08b').val(customer.knavn);
						jq('#dkeh_08c').val(customer.adr1);
						jq('#dkeh_08d').val(customer.postnr);
						jq('#dkeh_08e').val(customer.adr3);
						jq('#dkeh_08f').val(customer.syland);
					}else{
						//init fields
						jq('#dkeh_08a').val("");
						jq('#dkeh_08b').val("");
						jq('#dkeh_08c').val("");
						jq('#dkeh_08d').val("");
						jq('#dkeh_08e').val("");
						jq('#dkeh_08f').val("");
					}
				});
    			}
		});
	});

	
	
	
	
	
  	//-----------------------------------------
	//FETCH CUSTOMER from TRANSPORTOR html area
  	//-----------------------------------------
	function searchTransportorOwnWindow() {
		jq(function() {
			jq.getJSON('searchCustomer_Skat.do', {
				applicationUser : jq('#applicationUser').val(),
				customerName : jq('#search_dkeh_trna').val(),
				customerNumber : jq('#search_dkeh_trkn').val(),
				ajax : 'true'
			}, function(data) {
				var html = '<option selected value="">-Select-</option>';
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					html += '<option value="' + data[i].kundnr + '">' + data[i].knavn + '</option>';
					customer = new Object();
					customer.kundnr = data[i].kundnr;
					customer.knavn = data[i].knavn;
					customer.adr1 = data[i].adr1;
					customer.adr2 = data[i].adr2;
					customer.adr3 = data[i].adr3;
					customer.postnr = data[i].postnr;
					customer.kpers = data[i].kpers;
					
					//put the object in map now with customerNumber as key
					map[customer.kundnr] = customer;
				}
				//now that we have our options, give them to our select
				jq('#transportorList').html(html);
			});
		});
	}
	//Sets deklarant values after user selection
	jq(function() { 
	    jq('#transportorList').change(function() {
	      //init fields
		  jq('#dkeh_treo').val("");
		  
		  //now populate (if applicable)
		  var key = jq('#transportorList').val();
		  jq('#dkeh_trkn').val(key);
		  customer = map[key];
		  jq('#dkeh_treo').val(customer.eori);
		  
		});
	});
	//onClick for Transport dialog
	jq(function() { 
	    jq('#searchCustomer20CloseCancel').click(function() {
	      //rescue the original fields
	      jq('#dkeh_trkn').val(jq("#orig_dkeh_trkn").val());	
		  jq('#dkeh_treo').val(jq("#orig_dkeh_treo").val());
		  
	    });
	});
	//----------------------------------
	//Events Transportor (SEARCH window)
	//----------------------------------
	//img click
	jq(function() {	    
		jq('#imgTranportorSearch').click(function(){
    			jq("#search_dkeh_trkn").focus();
    		});
	});
	
	jq(function() {	    
		jq('#search_dkeh_trkn').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchTransportorOwnWindow);
			}			
    		});
		jq('#search_dkeh_trna').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchTransportorOwnWindow);
			}			
    		});
	});
	//--------------------------------------------------------------------------------------
	//Extra behavior for Customer number ( without using (choose from list) extra roundtrip)
	//--------------------------------------------------------------------------------------
	jq(function() { 
	    jq('#dkeh_trkn').blur(function() {
		    	var trknValue = jq('#dkeh_trkn').val();
	    		if(trknValue!=null && trknValue!=""){
		    		jq.getJSON('searchCustomer_Skat.do', {
					applicationUser : jq('#applicationUser').val(),
					customerName : "",
					customerNumber : jq('#dkeh_trkn').val(),
					ajax : 'true'
				}, function(data) {
					//alert("Hello");
					var len = data.length;
					for ( var i = 0; i < len; i++) {
						//html += '<option value="' + data[i].kundnr + '">' + data[i].knavn + '</option>';
						customer = new Object();
						customer.kundnr = data[i].kundnr;
						customer.knavn = data[i].knavn;
						customer.eori = data[i].eori;
						customer.adr1 = data[i].adr1;
						customer.adr2 = data[i].adr2;
						customer.adr3 = data[i].adr3;
						customer.postnr = data[i].sypoge;//data[i].postnr; DK=sypoge
						customer.kpers = data[i].kpers;
						customer.tlf = data[i].tlf;
						customer.syland = data[i].syland;
					  	//put the object in map now with customerNumber as key
						map[customer.kundnr] = customer;
					}
					if(len > 0){
						jq('#dkeh_trkn').val(customer.kundnr);
						jq('#dkeh_treo').val(customer.eori);
					}else{
						//init fields
						jq('#dkeh_treo').val("");
					}
				});
	    		}
		});
	});

	
	
	
	
  	//---------------------------------------------------------
	//FETCH CUSTOMER from REPRESENTANT html area
  	//---------------------------------------------------------
	function searchRepresentantOwnWindow() {
		jq(function() {
			jq.getJSON('searchCustomer_Skat.do', {
				applicationUser : jq('#applicationUser').val(),
				customerName : jq('#search_dkeh_rena').val(),
				customerNumber : jq('#search_dkeh_rekn').val(),
				ajax : 'true'
			}, function(data) {
				var html = '<option selected value="">-Select-</option>';
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					html += '<option value="' + data[i].kundnr + '">' + data[i].knavn + '</option>';
					customer = new Object();
					customer.kundnr = data[i].kundnr;
					customer.knavn = data[i].knavn;
					customer.eori = data[i].eori;
					customer.adr1 = data[i].adr1;
					customer.adr2 = data[i].adr2;
					customer.adr3 = data[i].adr3;
					customer.postnr = data[i].postnr;
					customer.kpers = data[i].kpers;
					customer.tlf = data[i].tlf;
					customer.syland = data[i].syland;
				  	
					//put the object in map now with customerNumber as key
					map[customer.kundnr] = customer;
				}
				//now that we have our options, give them to our select
				jq('#representantList').html(html);
			});
		});
	}
	//Sets representant values after user selection
	jq(function() { 
	    jq('#representantList').change(function() {
		  //init all fields
		  jq('#dkeh_rekn').val("");
		  jq('#dkeh_rena').val("");
		  jq('#dkeh_reeo').val("");
		  jq('#dkeh_rega').val("");
		  jq('#dkeh_reby').val("");
		  jq('#dkeh_repo').val("");
		  jq('#dkeh_relk').val("");
		  
		  //now populate (if applicable)
		  var key = jq('#representantList').val();
		  jq('#dkeh_rekn').val(key);
		  customer = map[key];
		  jq('#dkeh_reeo').val(customer.eori);
		  jq('#dkeh_rena').val(customer.knavn);
		  jq('#dkeh_rega').val(customer.adr1);
		  jq('#dkeh_repo').val(customer.postnr);
		  jq('#dkeh_reby').val(customer.adr3);
		  jq('#dkeh_relk').val(customer.syland);
		});
	});
	//onClick for Representant dialog
	jq(function() { 
	    jq('#searchCustomer30CloseCancel').click(function() {
	      //rescue the original fields
		  jq('#dkeh_rekn').val(jq("#orig_dkeh_rekn").val());	
		  jq('#dkeh_reeo').val(jq("#orig_dkeh_reeo").val());
		  jq('#dkeh_rena').val(jq("#orig_dkeh_rena").val());
		  jq('#dkeh_rega').val(jq("#orig_dkeh_rega").val());
		  jq('#dkeh_repo').val(jq("#orig_dkeh_repo").val());
		  jq('#dkeh_reby').val(jq("#orig_dkeh_reby").val());
		  jq('#dkeh_relk').val(jq("#orig_dkeh_relk").val());		  
	    });
	});
	//----------------------------------
	//Events Representant (SEARCH window)
	//----------------------------------
	//img click
	jq(function() {	    
		jq('#imgRepresentantSearch').click(function(){
    			jq("#search_dkeh_rekn").focus();
    		});
	});
	
	jq(function() {	    
		jq('#search_dkeh_rekn').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchRepresentantOwnWindow);
			}			
    		});
		jq('#search_dkeh_rena').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchRepresentantOwnWindow);
			}			
    		});
	});
	//--------------------------------------------------------------------------------------
	//Extra behavior for Customer number ( without using (choose from list) extra roundtrip)
	//--------------------------------------------------------------------------------------
	jq(function() { 
	    jq('#dkeh_rekn').blur(function() {
		    	var reknValue = jq('#dkeh_rekn').val();
	    		if(reknValue!=null && reknValue!=""){
		    		jq.getJSON('searchCustomer_Skat.do', {
					applicationUser : jq('#applicationUser').val(),
					customerName : "",
					customerNumber : jq('#dkeh_rekn').val(),
					ajax : 'true'
				}, function(data) {
					//alert("Hello");
					var len = data.length;
					for ( var i = 0; i < len; i++) {
						//html += '<option value="' + data[i].kundnr + '">' + data[i].knavn + '</option>';
						customer = new Object();
						customer.kundnr = data[i].kundnr;
						customer.knavn = data[i].knavn;
						customer.eori = data[i].eori;
						customer.adr1 = data[i].adr1;
						customer.adr2 = data[i].adr2;
						customer.adr3 = data[i].adr3;
						customer.postnr = data[i].sypoge;//data[i].postnr; DK=sypoge
						customer.kpers = data[i].kpers;
						customer.tlf = data[i].tlf;
						customer.syland = data[i].syland;
					  	//put the object in map now with customerNumber as key
						map[customer.kundnr] = customer;
					}
					if(len > 0){
						jq('#dkeh_rekn').val(customer.kundnr);
						jq('#dkeh_reeo').val(customer.eori);
						jq('#dkeh_rena').val(customer.knavn);
						jq('#dkeh_rega').val(customer.adr1);
						jq('#dkeh_repo').val(customer.postnr);
						jq('#dkeh_reby').val(customer.adr3);
						jq('#dkeh_relk').val(customer.syland);
					}else{
						//init fields
						jq('#dkeh_reeo').val("");
						jq('#dkeh_rena').val("");
						jq('#dkeh_rega').val("");
						jq('#dkeh_repo').val("");
						jq('#dkeh_reby').val("");
						jq('#dkeh_relk').val("");
					}
				});
	    		}
		});
	});
	
	
	//============================
	//START - Currency AJAX fetch
	//============================
	jq(function() { 
	    jq('#dkeh_221').change(function() {
	    	//alert('Hej');
	    	//this parameters must match the AJAX controller parameter names in Spring exactly...
	    		var isoDate = "";
	    		var faktisktAnkDato = jq('#dkeh_dtm2').val();
	    		var forventatAnkDato = jq('#dkeh_dtm1').val();
	    		if(faktisktAnkDato!=""){
	    			isoDate =faktisktAnkDato; 
	    		}else{
	    			isoDate =forventatAnkDato;
	    		}
	    		getCurrencyData(isoDate);
	    });
	});
	jq(function() { 
	    jq('#dkeh_221b').blur(function() {
	    		//alert('Hej');
	    		//this parameters must match the AJAX controller parameter names in Spring exactly...
	    		var dkeh_221b = jq('#dkeh_221b').val();
	    		var isoDate = "";
	    		var faktisktAnkDato = jq('#dkeh_dtm2').val();
	    		var forventatAnkDato = jq('#dkeh_dtm1').val();
	    		if(faktisktAnkDato!=""){
	    			isoDate =faktisktAnkDato; 
	    		}else{
	    			isoDate =forventatAnkDato;
	    		}
	    		if(dkeh_221b==null || dkeh_221b==""){
	    			getCurrencyData(isoDate);
	    		}	
	    });
	});
	jq(function() { 
	    jq('#dkeh_221c').blur(function() {
	    		//alert('Hej');
	    		//this parameters must match the AJAX controller parameter names in Spring exactly...
	    		var dkeh_221c = jq('#dkeh_221c').val();
	    		var isoDate = "";
	    		var faktisktAnkDato = jq('#dkeh_dtm2').val();
	    		var forventatAnkDato = jq('#dkeh_dtm1').val();
	    		if(faktisktAnkDato!=""){
	    			isoDate =faktisktAnkDato; 
	    		}else{
	    			isoDate =forventatAnkDato;
	    		}
	    		if(dkeh_221c==null || dkeh_221c==""){
	    			getCurrencyData(isoDate);
	    		}	
	    });
	});
	//private function
	function getCurrencyData(isoDate) {
		jq.ajax({
			type: 'GET',
			url: 'getCurrencyRate_SkatExport.do',
			data: { 	applicationUser : jq('#applicationUser').val(),
					currencyCode : jq('#dkeh_221').val(),
					isoDate : isoDate} ,
			dataType: 'json',
			success: function(data) {
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					jq('#dkeh_221b').val(data[i].dkvk_krs);
					jq('#dkeh_221c').val(data[i].dkvs_omr);
				}
			}
		});
	}
	//============================
	//END - Currency AJAX fetch
	//============================
	
	
	
	//--------------------------------------------------------------------------
  	//onChange-VALIDATION on-demand event
	//This section should always be exactly the same as in the Spring Validator
	//(mandatory fields section of the Validator)
  	//--------------------------------------------------------------------------
	jq(function() { 
	    jq('#dkeh_aart').change(function() {
	    		var aart = jq('#dkeh_aart').val();
	    		
	    		if("" == aart){ 
	    			//init all first
	    			initValidationClass(jq('.validation'));
	    			
	    		}else if("20" == aart){
	    			//init all first
	    			initValidationClass(jq('.validation'));
	    			//change
	    			highlightElement(jq('#v_dkeh_r011'));highlightElement(jq('#v_dkeh_r012'));
	    			highlightElement(jq('#v_dkeh_a1'));highlightElement(jq('#v_dkeh_17a'));
	    			highlightElement(jq('#v_dkeh_29'));highlightElement(jq('#v_dkeh_dtm1'));
	    			highlightElement(jq('#v_dkeh_25'));highlightElement(jq('#v_dkeh_26'));
	    			highlightElement(jq('#v_dkeh_08b'));highlightElement(jq('#v_dkeh_08c'));
	    			highlightElement(jq('#v_dkeh_08d'));highlightElement(jq('#v_dkeh_08e'));
	    			highlightElement(jq('#v_dkeh_08f'));highlightElement(jq('#v_dkeh_s29'));
	    			highlightElement(jq('#v_dkeh_06'));highlightElement(jq('#v_dkeh_221'));
	    			highlightElement(jq('#v_dkeh_221b'));highlightElement(jq('#v_dkeh_222'));
	    			
	    		}else if("21" == aart){
	    			//init all first
	    			initValidationClass(jq('.validation'));
	    			//change
	    			highlightElement(jq('#v_dkeh_r011'));highlightElement(jq('#v_dkeh_r012'));
	    			highlightElement(jq('#v_dkeh_a1'));highlightElement(jq('#v_dkeh_17a'));
	    			highlightElement(jq('#v_dkeh_29'));highlightElement(jq('#v_dkeh_dtm1'));
	    			highlightElement(jq('#v_dkeh_25'));highlightElement(jq('#v_dkeh_26'));
	    			highlightElement(jq('#v_dkeh_02a'));
	    			highlightElement(jq('#v_dkeh_08b'));highlightElement(jq('#v_dkeh_08c'));
	    			highlightElement(jq('#v_dkeh_08d'));highlightElement(jq('#v_dkeh_08e'));
	    			highlightElement(jq('#v_dkeh_08f'));highlightElement(jq('#v_dkeh_s29'));
	    			highlightElement(jq('#v_dkeh_06'));highlightElement(jq('#v_dkeh_221'));
	    			highlightElement(jq('#v_dkeh_221b'));highlightElement(jq('#v_dkeh_222'));
	    			
	    			
	    		}else if("22" == aart){
	    			//init all first
	    			initValidationClass(jq('.validation'));
	    			//change
	    			highlightElement(jq('#v_dkeh_r011'));highlightElement(jq('#v_dkeh_r012'));
	    			highlightElement(jq('#v_dkeh_a2'));highlightElement(jq('#v_dkeh_26'));
	    			highlightElement(jq('#v_dkeh_06'));highlightElement(jq('#v_dkeh_221'));
	    			highlightElement(jq('#v_dkeh_221b'));highlightElement(jq('#v_dkeh_222'));
	    			
	    		}else if("23" == aart){
	    			//init all first
	    			initValidationClass(jq('.validation'));
	    			//change
	    			highlightElement(jq('#v_dkeh_r011'));highlightElement(jq('#v_dkeh_r012'));
	    			highlightElement(jq('#v_dkeh_a2'));highlightElement(jq('#v_dkeh_26'));
	    			highlightElement(jq('#v_dkeh_06'));
	    			
	    		}else if("24" == aart){
	    			//init all first
	    			initValidationClass(jq('.validation'));
	    			//change
	    			highlightElement(jq('#v_dkeh_r011'));highlightElement(jq('#v_dkeh_r012'));
	    			highlightElement(jq('#v_dkeh_a1'));highlightElement(jq('#v_dkeh_17a'));
	    			highlightElement(jq('#v_dkeh_29'));highlightElement(jq('#v_dkeh_dtm1'));
	    			highlightElement(jq('#v_dkeh_25'));highlightElement(jq('#v_dkeh_26'));
	    			highlightElement(jq('#v_dkeh_08b'));highlightElement(jq('#v_dkeh_08c'));
	    			highlightElement(jq('#v_dkeh_08d'));highlightElement(jq('#v_dkeh_08e'));
	    			highlightElement(jq('#v_dkeh_08f'));highlightElement(jq('#v_dkeh_s29'));
	    			highlightElement(jq('#v_dkeh_06'));highlightElement(jq('#v_dkeh_221'));
	    			highlightElement(jq('#v_dkeh_221b'));highlightElement(jq('#v_dkeh_222'));
	    			
	    		}else if("25" == aart){
	    			//init all first
	    			initValidationClass(jq('.validation'));
	    			//change
	    			highlightElement(jq('#v_dkeh_r011'));highlightElement(jq('#v_dkeh_r012'));
	    			highlightElement(jq('#v_dkeh_a1'));highlightElement(jq('#v_dkeh_17a'));
	    			highlightElement(jq('#v_dkeh_29'));highlightElement(jq('#v_dkeh_dtm1'));
	    			highlightElement(jq('#v_dkeh_25'));highlightElement(jq('#v_dkeh_26'));
	    			highlightElement(jq('#v_dkeh_08b'));highlightElement(jq('#v_dkeh_08c'));
	    			highlightElement(jq('#v_dkeh_08d'));highlightElement(jq('#v_dkeh_08e'));
	    			highlightElement(jq('#v_dkeh_08f'));highlightElement(jq('#v_dkeh_s29'));
	    			highlightElement(jq('#v_dkeh_221'));
	    			highlightElement(jq('#v_dkeh_221b'));highlightElement(jq('#v_dkeh_222'));
	    			
	    		}else if("26" == aart){ 
	    			//Pro
	    			//init all first
	    			initValidationClass(jq('.validation'));
	    			//change
	    			highlightElement(jq('#v_section_Proviant'));
	    			highlightElement(jq('#v_dkeh_r011'));highlightElement(jq('#v_dkeh_r012'));
	    			highlightElement(jq('#v_dkeh_a1'));highlightElement(jq('#v_dkeh_dtm1'));
	    			highlightElement(jq('#v_dkeh_02a'));
	    			highlightElement(jq('#v_dkeh_08b'));highlightElement(jq('#v_dkeh_08c'));
	    			highlightElement(jq('#v_dkeh_08d'));highlightElement(jq('#v_dkeh_08e'));
	    			highlightElement(jq('#v_dkeh_08f'));highlightElement(jq('#v_dkeh_17a'));
	    			highlightElement(jq('#v_dkeh_ind'));highlightElement(jq('#v_dkeh_s29'));
	    			highlightElement(jq('#v_dkeh_29'));highlightElement(jq('#v_dkeh_ftx1'));
	    			highlightElement(jq('#v_dkeh_ftx2'));highlightElement(jq('#v_dkeh_ftx3'));
	    			highlightElement(jq('#v_dkeh_ftx4'));highlightElement(jq('#v_dkeh_ftx5'));
	    			highlightElement(jq('#v_dkeh_ftxj'));highlightElement(jq('#v_dkeh_181'));
	    			highlightElement(jq('#v_dkeh_zkp'));highlightElement(jq('#v_dkeh_221'));
	    			highlightElement(jq('#v_dkeh_221b'));highlightElement(jq('#v_dkeh_222'));
	    			
	    		}else if("27" == aart){ 
	    			//ProM
	    			//init all first
	    			initValidationClass(jq('.validation'));
	    			//change
	    			highlightElement(jq('#v_section_Proviant'));
	    			highlightElement(jq('#v_dkeh_r011'));highlightElement(jq('#v_dkeh_r012'));
	    			highlightElement(jq('#v_dkeh_a1'));highlightElement(jq('#v_dkeh_dtm2'));
	    			highlightElement(jq('#v_dkeh_02a'));
	    			highlightElement(jq('#v_dkeh_08b'));highlightElement(jq('#v_dkeh_08c'));
	    			highlightElement(jq('#v_dkeh_08d'));highlightElement(jq('#v_dkeh_08e'));
	    			highlightElement(jq('#v_dkeh_08f'));highlightElement(jq('#v_dkeh_17a'));
	    			highlightElement(jq('#v_dkeh_ind'));highlightElement(jq('#v_dkeh_s29'));
	    			highlightElement(jq('#v_dkeh_29'));highlightElement(jq('#v_dkeh_ftx1'));
	    			highlightElement(jq('#v_dkeh_ftx2'));highlightElement(jq('#v_dkeh_ftx3'));
	    			highlightElement(jq('#v_dkeh_ftx4'));highlightElement(jq('#v_dkeh_ftx5'));
	    			highlightElement(jq('#v_dkeh_ftxj'));highlightElement(jq('#v_dkeh_181'));
	    			highlightElement(jq('#v_dkeh_zkp'));highlightElement(jq('#v_dkeh_221'));
	    			highlightElement(jq('#v_dkeh_221b'));highlightElement(jq('#v_dkeh_222'));
	    			
	    		}else if("28" == aart){
	    			//init all first
	    			initValidationClass(jq('.validation'));
	    			//change
	    			highlightElement(jq('#v_dkeh_r011'));highlightElement(jq('#v_dkeh_r012'));
	    			highlightElement(jq('#v_dkeh_a1'));highlightElement(jq('#v_dkeh_dtm2'));
	    			highlightElement(jq('#v_dkeh_17a'));highlightElement(jq('#v_dkeh_29'));
	    			highlightElement(jq('#v_dkeh_25'));highlightElement(jq('#v_dkeh_26'));
	    			highlightElement(jq('#v_dkeh_06'));
	    			highlightElement(jq('#v_dkeh_08b'));highlightElement(jq('#v_dkeh_08c'));
	    			highlightElement(jq('#v_dkeh_08d'));highlightElement(jq('#v_dkeh_08e'));
	    			highlightElement(jq('#v_dkeh_08f'));highlightElement(jq('#v_dkeh_221'));
	    			highlightElement(jq('#v_dkeh_221b'));highlightElement(jq('#v_dkeh_222'));
	    			
	    			
	    		}else if("30" == aart){
	    			//init all first
	    			initValidationClass(jq('.validation'));
	    			//change
	    			highlightElement(jq('#v_dkeh_r011'));highlightElement(jq('#v_dkeh_r012'));
	    			highlightElement(jq('#v_dkeh_a1'));highlightElement(jq('#v_dkeh_dtm1'));
	    			highlightElement(jq('#v_dkeh_uvp'));highlightElement(jq('#v_dkeh_s131'));
	    			highlightElement(jq('#v_dkeh_s29'));highlightElement(jq('#v_dkeh_29'));
	    			highlightElement(jq('#v_dkeh_06'));highlightElement(jq('#v_dkeh_reeo'));
	    			highlightElement(jq('#v_dkeh_08b'));highlightElement(jq('#v_dkeh_08c'));
	    			highlightElement(jq('#v_dkeh_08d'));highlightElement(jq('#v_dkeh_08e'));
	    			highlightElement(jq('#v_dkeh_08f'));highlightElement(jq('#v_dkeh_221'));
	    			highlightElement(jq('#v_dkeh_221b'));highlightElement(jq('#v_dkeh_222'));
	    			
	    		}else if("31" == aart){
	    			//YM
	    			//init all first
	    			initValidationClass(jq('.validation'));
	    			//change
	    			highlightElement(jq('#v_section_YM'));
	    			highlightElement(jq('#v_dkeh_r011'));highlightElement(jq('#v_dkeh_r012'));
	    			highlightElement(jq('#v_dkeh_a1'));highlightElement(jq('#v_dkeh_02a'));
	    			highlightElement(jq('#v_dkeh_17a'));highlightElement(jq('#v_dkeh_25'));
	    			highlightElement(jq('#v_dkeh_26'));highlightElement(jq('#v_dkeh_s29'));
	    			highlightElement(jq('#v_dkeh_06'));highlightElement(jq('#v_dkeh_29'));
	    			highlightElement(jq('#v_dkeh_ymd1'));highlightElement(jq('#v_dkeh_ymd2'));
	    			highlightElement(jq('#v_dkeh_ym21'));highlightElement(jq('#v_dkeh_ym23'));
	    			highlightElement(jq('#v_dkeh_221'));
	    			highlightElement(jq('#v_dkeh_221b'));highlightElement(jq('#v_dkeh_222'));
	    			highlightElement(jq('#v_dkeh_301'));highlightElement(jq('#v_dkeh_302'));
	    			highlightElement(jq('#v_dkeh_303'));highlightElement(jq('#v_dkeh_304'));
	    			
	    		}else if("32" == aart){
	    			//YM
	    			//init all first
	    			initValidationClass(jq('.validation'));
	    			//change
	    			highlightElement(jq('#v_section_YM'));
	    			highlightElement(jq('#v_dkeh_r011'));highlightElement(jq('#v_dkeh_r012'));
	    			highlightElement(jq('#v_dkeh_a1'));highlightElement(jq('#v_dkeh_02a'));
	    			highlightElement(jq('#v_dkeh_17a'));highlightElement(jq('#v_dkeh_25'));
	    			highlightElement(jq('#v_dkeh_26'));highlightElement(jq('#v_dkeh_s29'));
	    			highlightElement(jq('#v_dkeh_06'));highlightElement(jq('#v_dkeh_29'));
	    			highlightElement(jq('#v_dkeh_ymvp'));highlightElement(jq('#v_dkeh_ymd1'));
	    			highlightElement(jq('#v_dkeh_ymd2'));highlightElement(jq('#v_dkeh_ym21'));
	    			highlightElement(jq('#v_dkeh_ym23'));highlightElement(jq('#v_dkeh_221'));
	    			highlightElement(jq('#v_dkeh_221b'));highlightElement(jq('#v_dkeh_222'));
	    				
	    		}else if("50" == aart){
	    			//IE507 (Ankomstmeddelande)
	    			//init all first
	    			initValidationClass(jq('.validation'));
	    			//change
	    			highlightElement(jq('#v_dkeh_ajou'));highlightElement(jq('#v_dkeh_29'));
	    			highlightElement(jq('#v_dkeh_uvp'));highlightElement(jq('#v_dkeh_treo'));

	    		}
 
	    });
	    //private
		function highlightElement(element){
			element.css({ 'color': 'red', 'font-size': '112%' });
		}
		function initValidationClass(element){
			element.css({ 'color': 'black', 'font-size': '100%' });
		}
	});
	

	  //-------------------------------------------
	  //START Model dialog ADMIN: "Update status"
	  //-------------------------------------------
	  //Initialize <div> here
	  jq(function() { 
		  jq("#dialogUpdateStatus").dialog({
			  autoOpen: false,
			  maxWidth:500,
	          maxHeight: 400,
	          width: 280,
	          height: 220,
			  modal: true
		  });
	  });
	  //Present dialog box onClick (href in parent JSP)
	  jq(function() {
		  jq("#updateStatusLink").click(function() {
			  //setters (add more if needed)
			  jq('#dialogUpdateStatus').dialog( "option", "title", "Update Status" );
			  
			  //deal with buttons for this modal window
			  jq('#dialogUpdateStatus').dialog({
				 buttons: [ 
		            {
					 id: "dialogSaveTU",	
					 text: "Ok",
					 click: function(){
						 		jq('#updateStatusForm').submit();
					 		}
				 	 },
		 	 		{
				 	 id: "dialogCancelTU",
				 	 text: "Cancel", 
					 click: function(){
						 		//back to initial state of form elements on modal dialog
						 		jq("#dialogSaveSU").button("option", "disabled", true);
						 		jq("#selectedStatus").val("");
						 		jq( this ).dialog( "close" ); 
					 		} 
		 	 		 } ] 
			  });
			  //init values
			  jq("#dialogSaveSU").button("option", "disabled", true);
			  //open now
			  jq('#dialogUpdateStatus').dialog('open');
		  });
	  });

	