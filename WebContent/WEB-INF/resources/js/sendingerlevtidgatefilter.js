	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict();
 	var map = {};
 	  
  	//focus 
  	jq(function() {
  		jq("#dato").focus();
  	});
  
  	
  	//--------------------------------------
  	//FETCH CUSTOMER(Agent) from AGENT html area
  	//--------------------------------------
  	//init the customer object in javascript (will be put into a map)
  	var customer = new Object();
  	//fields
  	customer.kundnr = "";customer.knavn = "";customer.eori = "";customer.adr1 = "";
  	customer.adr2 = "";customer.adr3 = "";customer.postnr = "";customer.syland = "";
  	customer.kpers = "";customer.tlf = "";
  	//AJAX - to Controller
  	function searchAgentOwnWindow() {
		jq(function() {
			jq.getJSON('searchCustomer_SendingerLevtid.do', {
				applicationUser : jq('#applicationUser').val(),
				customerName : jq('#search_sveh_avna').val(),
				customerNumber : jq('#search_sveh_avkn').val(),
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
				jq('#agentList').html(html);
			});
		});
	}
	//-----------------------------
	//Agent list (SEARCH window)
	//-----------------------------
	//onChange sender list
	jq(function() { 
	    jq('#agentList').change(function() {
	      //init fields
		  jq('#agent').val("");
		  //now populate (if applicable)
		  var key = jq('#agentList').val();
		  jq('#agent').val(key);
		  customer = map[key];
	    });
	});
	
	//onClick for Agent dialog
	jq(function() { 
	    jq('#searchAgentCloseCancel').click(function() {
	      //blank the field
	      //jq('#trans').val("");	
	    });
	});
	
	//On Keypress (13)
	jq(function() { 
	    jq('#agentList').keypress(function() {
		    	if(e.which == 13) {
				//alert("hej till publiken");
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
			    	jq('#agent').val(""); 
				//now populate (if applicable)
			    	var key = jq('#agentList').val();
			    	jq('#agent').val(key); 
		    	}
	    });
	    
	});
	
	//----------------------------------
	//Events Agent (SEARCH window)
	//----------------------------------
	//img click
	jq(function() {	    
		jq('#imgAgentSearch').click(function(){
    			jq("#search_sveh_avkn").focus();
    		});
	});
	
	jq(function() {	    
		jq('#search_sveh_avkn').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchAgentOwnWindow);
			}			
    		});
		jq('#search_sveh_avna').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchAgentOwnWindow);
			}			
    		});
	});

  	
  	
  	
  	
  	
  	
  	//-----------------------------
	//FETCH CARRIER (SEARCH window) 
	//-----------------------------
	//init the carrier object in javascript (will be put into a map)
  	var carrier = new Object();
	//fields
	carrier.tnavn = "";
	//AJAX - in Controller
	function searchCarrierOwnWindow() {
		jq(function() {
			jq.getJSON('searchCarrier_SendingerLevtid.do', {
				applicationUser : jq('#applicationUser').val(),
				carrierName : jq('#search_sonavn').val(),
				carrierNumber : jq('#search_tnr').val(),
				ajax : 'true'
			}, function(data) {
				//alert("Hello");
				var html = '<option selected value="">-Select-</option>';
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					html += '<option value="' + data[i].tranNr + '">' + data[i].tnavn + '</option>';
					carrier = new Object();
					carrier.tranNr = data[i].tranNr;
					
					//put the object in map now with customerNumber as key
					map[carrier.tranNr] = carrier;
				}
				//now that we have our options, give them to our select
				jq('#carrierList').html(html);
			});
		});
	}
	
	//onClick for Carrier dialog
	jq(function() { 
	    jq('#searchCarrierCloseCancel').click(function() {
	      //blank the field
	      //jq('#trans').val("");	
	    });
	});
	
	//-----------------------------
	//Carrier list (SEARCH window)
	//-----------------------------
	//onChange
	jq(function() { 
	    jq('#carrierList').change(function() {
		    	jq('#trans').val(""); 
		    	//now populate (if applicable)
		    	var key = jq('#carrierList').val();
		    	jq('#trans').val(key); 
	    });
	});
	//On Keypress (13)
	jq(function() { 
	    jq('#carrierList').keypress(function() {
		    	if(e.which == 13) {
				//alert("hej till publiken");
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
			    	jq('#trans').val(""); //tds export
				//now populate (if applicable)
			    	var key = jq('#carrierList').val();
			    	jq('#trans').val(key); //tds export
		    	}
	    });
	    
	});
	
	//----------------------------------
	//Events Carrier (SEARCH window)
	//----------------------------------
	//img click
	jq(function() {	    
		jq('#imgCarrierSearch').click(function(){
    			jq("#search_tnr").focus();
    		});
	});
	
	jq(function() {	    
		jq('#search_tnr').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchCarrierOwnWindow);
			}			
    		});
		jq('#search_sonavn').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchCarrierOwnWindow);
			}			
    		});
	});


	//------------------
	//GUI events/actions
	//------------------
	
	jq(function() { 
	    jq('#iott').change(function() {
	    		if(jq('#iott').val()!=""){
	    			jq('#tt1').attr("disabled", false); 
	    			jq('#tt2').attr("disabled", false); 
	    			jq('#tt3').attr("disabled", false); 
	    			jq('#tt4').attr("disabled", false); 
	    			jq('#tt5').attr("disabled", false); 
	    		}else{
	    			//init
	    			jq('#tt1').val("");jq('#tt2').val("");jq('#tt3').val("");
	    			jq('#tt4').val("");jq('#tt5').val("");
	    			//disable all
	    			jq('#tt1').attr("disabled", true); 
	    			jq('#tt2').attr("disabled", true); 
	    			jq('#tt3').attr("disabled", true); 
	    			jq('#tt4').attr("disabled", true); 
	    			jq('#tt5').attr("disabled", true); 
	    		}
	    });
	    
	    jq('#iottb').change(function() {
	    		if(jq('#iottb').val()!=""){
	    			jq('#tt1b').attr("disabled", false); 
	    			jq('#tt2b').attr("disabled", false); 
	    			jq('#tt3b').attr("disabled", false); 
	    			jq('#tt4b').attr("disabled", false); 
	    			jq('#tt5b').attr("disabled", false); 
	    		}else{
	    			//init
	    			jq('#tt1b').val("");jq('#tt2b').val("");jq('#tt3b').val("");
	    			jq('#tt4b').val("");jq('#tt5b').val("");
	    			//disable all
	    			jq('#tt1b').attr("disabled", true); 
	    			jq('#tt2b').attr("disabled", true); 
	    			jq('#tt3b').attr("disabled", true); 
	    			jq('#tt4b').attr("disabled", true); 
	    			jq('#tt5b').attr("disabled", true); 
	    		}
	    });

	});
	

  
	
  
