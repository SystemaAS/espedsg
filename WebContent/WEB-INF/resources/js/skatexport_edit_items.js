	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict();
  	var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";
    
    function setBlockUI(element){
  	  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
    }
    
    jq(function() {
    	jq('#alinkTopicList').click(function() { 
		  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
    	});	
    	jq('#alinkHeader').click(function() { 
    		  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
    	});
    	jq('#alinkInvoices').click(function() { 
    		  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
    	});
        jq('#alinkLogging').click(function() { 
    		jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
    	});
    	jq('#alinkArchive').click(function() { 
    		jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
  	  	});	
  	  //Overlay
  	  jq( "#submit" ).click(function( event ) {
		  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT });
	  });
  	  
    });
  	//-----------------------------------------------------------------------------
  	//jQuery CALCULATOR (related to jquery.calculator.js and jquery.calculator.css
  	//-----------------------------------------------------------------------------
  	jq(function() {
  		jq('#dkev_42').calculator({ showOn: 'button',  
  			buttonImageOnly: true, buttonImage: 'resources/images/calculator.png', decimalChar: ','});
  	});
  	//-----------------------------------------------
  	//Trigger points for "calculateStatistisktVarde"
  	//-----------------------------------------------
  	jq(function() {
  		jq('#dkev_42').blur(function() {
  			//(1) Calc. aprox Gross weight (proposal to the user)
  			var unitPrice = jq('#dkev_42').val().replace(".",""); //must clean all US notation
  			unitPrice = unitPrice.replace(",","."); //now we replace EU notation with US in order to use Math
  			var invoiceAmount = jq('#fabl').val().replace(".","");
  			invoiceAmount = invoiceAmount.replace(",",".");
  			if(unitPrice != "" && invoiceAmount != ""){
  				if(invoiceAmount>0){
  					var totalGross = jq('#totalGrossWeight').val().replace(".","");
  					totalGross = totalGross.replace(",",".");
  					var proposedGross = (unitPrice/invoiceAmount) * totalGross;
  					//final value proposed
  					proposedGross = proposedGross.toFixed(3);
  					jq('#dkev_35').val(proposedGross.replace(".",",")); //final EU-format (for decimals)
  				}
  			}
  			//(2) Stat.value
  			calculateStatistisktVarde();
  			
  		});

  	});
  	//init the dkev_46 value each time we change a "character" the price (dkev_42)
  	jq(function() {
  		jq('#dkev_42').on('input', function() {
  			jq('#dkev_46').val("");
  		});

  	});
  	
  	
  	
  	//----------------------------------------------------------------------
	//including timepicker (jQuery addon to datepicker). Ref to parent JSP.
	//----------------------------------------------------------------------
  	jq(function() {
  		jq("#dkev_y73").datepicker({ 
    		  dateFormat: 'yymmdd'  
    	  	});
  		
	  	jq("#dkev_y82g").datetimepicker({ 
	  		dateFormat: 'yymmdd',
	  		controlType: 'select',
	  		timeFormat: 'HHmm'
	  	});
	  	//no space characters allowed...
	  	jq('#dkev_y82g').change(function() {
	  		var val = jq("#dkev_y82g").val();
			jq("#dkev_y82g").val(val.replace(' ', ''));
	  	});
  	});
  	
	 
  	
  	/**
  	 * gets a specific item line
  	 * 
  	 * @param record
  	 */
  	function getItemData(record) {
	  	var htmlValue = record.id;
	  	var rowCounter = jq("#"+record.id).attr("title");
	  	//alert(rowCounter);
	  	
	  	var applicationUserParam = jq('#applicationUser').val();
	  	var avdParam = jq('#avdItemList').val();
	  	var opdParam = jq('#opdItemList').val();
	  	//alert(htmlValue);
	  	
	  	jq.ajax({
	  	  type: 'GET',
	  	  url: 'getSpecificTopicItemChosenFromGuiElement_SkatExport.do',
	  	  data: { applicationUser : applicationUserParam, 
	  		  	  elementValue : htmlValue, 
	  		  	  avd : avdParam, 
	  		  	  opd : opdParam },
	  	  dataType: 'json',
	  	  cache: false,
	  	  contentType: 'application/json',
	  	  success: function(data) {
	  		var len = data.length;
			for ( var i = 0; i < len; i++) {
				//This line counter (lastSelectedItemLineNumber) is used in order to have a serial counter for the row lines. It is the only serial counter...
				//It is used ONLY for aspect/behavior purposes on GUI (scroll, bgColor, etc) in the specific row.
				jq('#lastSelectedItemLineNumber').val(""); jq('#lastSelectedItemLineNumber').val(rowCounter);
				//back-end data
				jq('#lineNr').val(""); jq('#lineNr').val(data[i].dkev_syli);
				jq('#dkev_syli').val(""); jq('#dkev_syli').val(data[i].dkev_syli);
				
				jq('#dkev_311').val(""); jq('#dkev_311').val(data[i].dkev_311);
				//
				jq('#dkev_312a').val(""); jq('#dkev_312a').val(data[i].dkev_312a);
				jq('#dkev_312b').val(""); jq('#dkev_312b').val(data[i].dkev_312b);
				jq('#dkev_312c').val(""); jq('#dkev_312c').val(data[i].dkev_312c);
				jq('#dkev_312d').val(""); jq('#dkev_312d').val(data[i].dkev_312d);
				jq('#dkev_312e').val(""); jq('#dkev_312e').val(data[i].dkev_312e);
				//
				jq('#dkev_313').val(""); jq('#dkev_313').val(data[i].dkev_313);
				jq('#dkev_314').val(""); jq('#dkev_314').val(data[i].dkev_314);
				jq('#dkev_315').val(""); jq('#dkev_315').val(data[i].dkev_315);
				//
				jq('#dkev_28b').val(""); jq('#dkev_28b').val(data[i].dkev_28b);
				jq('#dkev_32').val(""); jq('#dkev_32').val(data[i].dkev_32);
				jq('#dkev_331').val(""); jq('#dkev_331').val(data[i].dkev_331);
				jq('#dkev_332').val(""); jq('#dkev_332').val(data[i].dkev_332);
				jq('#dkev_sikk').val(""); jq('#dkev_sikk').val(data[i].dkev_sikk);
				jq('#dkev_34a').val(""); jq('#dkev_34a').val(data[i].dkev_34a);
				jq('#dkev_35').val(""); jq('#dkev_35').val(data[i].dkev_35);
				jq('#dkev_37').val(""); jq('#dkev_37').val(data[i].dkev_37);
				jq('#dkev_38').val(""); jq('#dkev_38').val(data[i].dkev_38);
				
				jq('#dkev_402a').val(""); jq('#dkev_402a').val(data[i].dkev_402a);
				jq('#dkev_403a').val(""); jq('#dkev_403a').val(data[i].dkev_403a);
				jq('#dkev_402b').val(""); jq('#dkev_402b').val(data[i].dkev_402b);
				jq('#dkev_403b').val(""); jq('#dkev_403b').val(data[i].dkev_403b);
				jq('#dkev_402c').val(""); jq('#dkev_402c').val(data[i].dkev_402c);
				jq('#dkev_403c').val(""); jq('#dkev_403c').val(data[i].dkev_403c);
				jq('#dkev_402d').val(""); jq('#dkev_402d').val(data[i].dkev_402d);
				jq('#dkev_403d').val(""); jq('#dkev_403d').val(data[i].dkev_403d);
				jq('#dkev_402e').val(""); jq('#dkev_402e').val(data[i].dkev_402e);
				jq('#dkev_403e').val(""); jq('#dkev_403e').val(data[i].dkev_403e);
				jq('#dkev_402f').val(""); jq('#dkev_402f').val(data[i].dkev_402f);
				jq('#dkev_403f').val(""); jq('#dkev_403f').val(data[i].dkev_403f);
				jq('#dkev_402g').val(""); jq('#dkev_402g').val(data[i].dkev_402g);
				jq('#dkev_403g').val(""); jq('#dkev_403g').val(data[i].dkev_403g);
				jq('#dkev_402h').val(""); jq('#dkev_402h').val(data[i].dkev_402h);
				jq('#dkev_403h').val(""); jq('#dkev_403h').val(data[i].dkev_403h);
				jq('#dkev_402i').val(""); jq('#dkev_402i').val(data[i].dkev_402i);
				jq('#dkev_403i').val(""); jq('#dkev_403i').val(data[i].dkev_403i);
				jq('#dkev_402j').val(""); jq('#dkev_402j').val(data[i].dkev_402j);
				jq('#dkev_403j').val(""); jq('#dkev_403j').val(data[i].dkev_403j);
				jq('#dkev_402k').val(""); jq('#dkev_402k').val(data[i].dkev_402k);
				jq('#dkev_403k').val(""); jq('#dkev_403k').val(data[i].dkev_403k);
				jq('#dkev_402l').val(""); jq('#dkev_402l').val(data[i].dkev_402l);
				jq('#dkev_403l').val(""); jq('#dkev_403l').val(data[i].dkev_403l);
				jq('#dkev_402m').val(""); jq('#dkev_402m').val(data[i].dkev_402m);
				jq('#dkev_403m').val(""); jq('#dkev_403m').val(data[i].dkev_403m);
				jq('#dkev_402n').val(""); jq('#dkev_402n').val(data[i].dkev_402n);
				jq('#dkev_403n').val(""); jq('#dkev_403n').val(data[i].dkev_403n);
				jq('#dkev_402o').val(""); jq('#dkev_402o').val(data[i].dkev_402o);
				jq('#dkev_403o').val(""); jq('#dkev_403o').val(data[i].dkev_403o);
				jq('#dkev_402p').val(""); jq('#dkev_402p').val(data[i].dkev_402p);
				jq('#dkev_403p').val(""); jq('#dkev_403p').val(data[i].dkev_403p);
				jq('#dkev_402q').val(""); jq('#dkev_402q').val(data[i].dkev_402q);
				jq('#dkev_403q').val(""); jq('#dkev_403q').val(data[i].dkev_403q);
				jq('#dkev_402r').val(""); jq('#dkev_402r').val(data[i].dkev_402r);
				jq('#dkev_403r').val(""); jq('#dkev_403r').val(data[i].dkev_403r);
				jq('#dkev_402s').val(""); jq('#dkev_402s').val(data[i].dkev_402s);
				jq('#dkev_403s').val(""); jq('#dkev_403s').val(data[i].dkev_403s);
				
				jq('#dkev_411').val(""); jq('#dkev_411').val(data[i].dkev_411);
				jq('#dkev_412').val(""); jq('#dkev_412').val(data[i].dkev_412);
				jq('#dkev_42').val(""); jq('#dkev_42').val(data[i].dkev_42);
				jq('#dkev_441').val(""); jq('#dkev_441').val(data[i].dkev_441);
				
				//Certifikatkode
				jq('#dkev_4421').val(""); jq('#dkev_4421').val(data[i].dkev_4421);
				jq('#dkev_4422').val(""); jq('#dkev_4422').val(data[i].dkev_4422);
				jq('#dkev_4423').val(""); jq('#dkev_4423').val(data[i].dkev_4423);
				jq('#dkev_4424').val(""); jq('#dkev_4424').val(data[i].dkev_4424);
				jq('#dkev_4425').val(""); jq('#dkev_4425').val(data[i].dkev_4425);
				jq('#dkev_4426').val(""); jq('#dkev_4426').val(data[i].dkev_4426);
				jq('#dkev_4427').val(""); jq('#dkev_4427').val(data[i].dkev_4427);
				jq('#dkev_4428').val(""); jq('#dkev_4428').val(data[i].dkev_4428);
				jq('#dkev_4429').val(""); jq('#dkev_4429').val(data[i].dkev_4429);
				//Certifikatnr.				
				jq('#dkev_442a').val(""); jq('#dkev_442a').val(data[i].dkev_442a);
				jq('#dkev_442b').val(""); jq('#dkev_442b').val(data[i].dkev_442b);
				jq('#dkev_442c').val(""); jq('#dkev_442c').val(data[i].dkev_442c);
				jq('#dkev_442d').val(""); jq('#dkev_442d').val(data[i].dkev_442d);
				jq('#dkev_442e').val(""); jq('#dkev_442e').val(data[i].dkev_442e);
				jq('#dkev_442f').val(""); jq('#dkev_442f').val(data[i].dkev_442f);
				jq('#dkev_442g').val(""); jq('#dkev_442g').val(data[i].dkev_442g);
				jq('#dkev_442h').val(""); jq('#dkev_442h').val(data[i].dkev_442h);
				jq('#dkev_442i').val(""); jq('#dkev_442i').val(data[i].dkev_442i);
				
				
				jq('#dkev_443').val(""); jq('#dkev_443').val(data[i].dkev_443);
				jq('#dkev_444').val(""); jq('#dkev_444').val(data[i].dkev_444);
				jq('#dkev_445a').val(""); jq('#dkev_445a').val(data[i].dkev_445a);
				jq('#dkev_445b').val(""); jq('#dkev_445b').val(data[i].dkev_445b);
				jq('#dkev_446a').val(""); jq('#dkev_446a').val(data[i].dkev_446a);
				jq('#dkev_446b').val(""); jq('#dkev_446b').val(data[i].dkev_446b);
				jq('#dkev_446c').val(""); jq('#dkev_446c').val(data[i].dkev_446c);
				jq('#dkev_447').val(""); jq('#dkev_447').val(data[i].dkev_447);
				jq('#dkev_448').val(""); jq('#dkev_448').val(data[i].dkev_448);
				jq('#dkev_449a').val(""); jq('#dkev_449a').val(data[i].dkev_449a);
				jq('#dkev_449b').val(""); jq('#dkev_449b').val(data[i].dkev_449b);
				jq('#dkev_46').val(""); jq('#dkev_46').val(data[i].dkev_46);
				jq('#dkev_49').val(""); jq('#dkev_49').val(data[i].dkev_49);
				//Bemærkningar
				jq('#dkev_bem1').val(""); jq('#dkev_bem1').val(data[i].dkev_bem1);
				jq('#dkev_bem2').val(""); jq('#dkev_bem2').val(data[i].dkev_bem2);
				jq('#dkev_bem3').val(""); jq('#dkev_bem3').val(data[i].dkev_bem3);
				jq('#dkev_bem4').val(""); jq('#dkev_bem4').val(data[i].dkev_bem4);
				//YM
				jq('#dkev_y63').val(""); jq('#dkev_y63').val(data[i].dkev_y63);
				jq('#dkev_y64').val(""); jq('#dkev_y64').val(data[i].dkev_y64);
				jq('#dkev_y71').val(""); jq('#dkev_y71').val(data[i].dkev_y71);
				jq('#dkev_y72').val(""); jq('#dkev_y72').val(data[i].dkev_y72);
				jq('#dkev_y73').val(""); jq('#dkev_y73').val(data[i].dkev_y73);
				jq('#dkev_y74').val(""); jq('#dkev_y74').val(data[i].dkev_y74);
				jq('#dkev_y75').val(""); jq('#dkev_y75').val(data[i].dkev_y75);
				jq('#dkev_y76a').val(""); jq('#dkev_y76a').val(data[i].dkev_y76a);
				jq('#dkev_y76b').val(""); jq('#dkev_y76b').val(data[i].dkev_y76b);
				jq('#dkev_y76c').val(""); jq('#dkev_y76c').val(data[i].dkev_y76c);
				jq('#dkev_y76d').val(""); jq('#dkev_y76d').val(data[i].dkev_y76d);
				jq('#dkev_y81a').val(""); jq('#dkev_y81a').val(data[i].dkev_y81a);
				jq('#dkev_y81b').val(""); jq('#dkev_y81b').val(data[i].dkev_y81b);
				jq('#dkev_y81c').val(""); jq('#dkev_y81c').val(data[i].dkev_y81c);
				jq('#dkev_y81d').val(""); jq('#dkev_y81d').val(data[i].dkev_y81d);
				jq('#dkev_y81e').val(""); jq('#dkev_y81e').val(data[i].dkev_y81e);
				jq('#dkev_y82a').val(""); jq('#dkev_y82a').val(data[i].dkev_y82a);
				jq('#dkev_y82b').val(""); jq('#dkev_y82b').val(data[i].dkev_y82b);
				jq('#dkev_y82c').val(""); jq('#dkev_y82c').val(data[i].dkev_y82c);
				jq('#dkev_y82d').val(""); jq('#dkev_y82d').val(data[i].dkev_y82d);
				jq('#dkev_y82e').val(""); jq('#dkev_y82e').val(data[i].dkev_y82e);
				jq('#dkev_y82f').val(""); jq('#dkev_y82f').val(data[i].dkev_y82f);
				jq('#dkev_y82g').val(""); jq('#dkev_y82g').val(data[i].dkev_y82g);
				jq('#dkev_y82h').val(""); jq('#dkev_y82h').val(data[i].dkev_y82h);
				
				//debug information on Fetch item
				jq('#debugPrintlnAjaxItemFetchInfo').text(data[i].debugPrintlnAjax);
			}
	  	  },
	  	  error: function() {
	  	    alert('Error loading ...');
	  	  }
	  	});
  	}
  	
  	//taric varukod search
  	function searchTaricVarukod() {
		jq(function() {
			jq.getJSON('searchTaricVarukod_SkatExport.do', {
				applicationUser : jq('#applicationUser').val(),
				taricVarukod : jq('#search_dkev_331').val(),
				ajax : 'true'
			}, function(data) {
				var html = '<option selected value="">-Select-</option>';
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					var optionValue = data[i].dktara02;
					if(data[i].dktara63.length > 0){
						//this is done in order to take care of the supp.enhet (dkev_411) through a string split (when taricVarukodList.change)
						optionValue = optionValue + "@" + data[i].dktara63;
					}
					html += '<option value="' + optionValue + '">' + data[i].dktara02 + ' ' + data[i].dktara64 +  '</option>';
					//html += '<option value="' + data[i].dktara02 + '">' + data[i].dktara02 + '</option>';
				}
				//now that we have our options, give them to our select
				jq('#taricVarukodList').html(html);
				
			});
		});
	}
  	//set the taric varukod in target input text field
  	jq(function() { 
	    jq('#taricVarukodList').change(function() {
		  //init field(s)
		  jq('#dkev_331').val("");
		  //and populate (if applicable)
		  var key = jq('#taricVarukodList').val();
		  var separator = key.indexOf("@");
		  if(separator>0){
			  jq('#dkev_331').val(key.substring(0,separator));
			  //alert("combo:" + key.substring(separator+1));
			  jq('#dkev_411').val(key.substring(separator+1));
		  }else{
			  //alert("combo: separator == 0");
			  jq('#dkev_331').val(key);
			  jq('#dkev_411').val("");
		  }
		  			  
		});
	});
  	//----------------------------------
	//Events Varukod (SEARCH window)
	//----------------------------------
	//img click
	jq(function() {	    
		jq('#imgTaricVarukodSearch').click(function(){
    			jq("#search_dkev_331").focus();
    		});
	});
	
	jq(function() {	    
		jq('#search_dkev_331').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchTaricVarukod);
			}			
    		});
	});

	//On Keypress (13)
	jq(function() { 
	    jq('#taricVarukodList').keypress(function() {
		    	if(e.which == 13) {
				//alert("hej till publiken");
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
			    	jq('#dkev_331').val(""); 
				//now populate (if applicable)
			    	var key = jq('#taricVarukodList').val();
			    	jq('#dkev_331').val(key.substring(0,8));
			    	
		    	}
	    });
	    
	});
	//This blur function is used when the end-user does not uses the taricVarukodlist.change (jQuery) due to his/her knowledge of the varukod
	//In this case the Ajax function must be called when the user leaves the varukod field...
	jq(function() {	    
		jq('#dkev_331').blur(function(e){
			jq.getJSON('searchTaricVarukod_SkatExport.do', {
				applicationUser : jq('#applicationUser').val(),
				taricVarukod : jq('#dkev_331').val(),
				ajax : 'true'
			}, function(data) {
				//var html = '<option selected value="">-Select-</option>';
				var len = data.length;
				var dktara63 = "";
				for ( var i = 0; i < len; i++) {
					if(data[i].dktara63.length > 0){
						//this is done in order to take care of the supp.enhet (dkev_411) through a string split (when taricVarukodList.change)
						dktara63 = data[i].dktara63;
					}
				}
				//Use the value or change a previous value into blank
				if(dktara63 != ""){
					jq('#dkev_411').val(dktara63);
				}else{
					jq('#dkev_411').val("");
				}
				
			});		
    		});
	});
	
  	
  	//CALCULATE Stat.værdi (ej Avgifter (since there are none)) via AJAX Controller here
  	function calculateStatistisktVarde() {
  		var dkehStrParam = "dkeh_221=" + jq('#session_dkeh_221').val() +  "&dkeh_221b=" + jq('#session_dkeh_221b').val() + 
  							"&dkeh_222=" + jq('#session_dkeh_222').val(); 

		var dkevStrParam = "&dkev_46=" + jq('#dkev_46').val() + "&dkev_42=" + jq('#dkev_42').val();	
		
		jq.ajax({
			type: 'GET',
			url: 'calculateStatistisktVarde_SkatExport.do',
			data: { 	applicationUser : jq('#applicationUser').val(),
					dkehStr : dkehStrParam, 
				  	dkevStr : dkevStrParam },
			dataType: 'json',
			cache: false,
			contentType: 'application/json',
			success: function(data) {
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					
					//alert(data[i].debugPrintlnAjax);
					//debug info
					jq('#debugPrintlnAjaxInfo').text(data[i].debugPrintlnAjax);
					
					//statistiskt værdi (ONLY this value will be calculated.
					//all other values are calculated by SKAT and are not subjected to AS400 calculation (as in SKAT)
					//alert(callerType);
					jq('#dkev_46').val(data[i].dkev_46);
					
				}
			}
		});
  	}
  	
  	//calculate a net weight from the gross weight
  	jq(function() { 
	    jq('#dkev_35').blur(function() {
		  //init field(s)
	    	  var grossWeight = jq('#dkev_35').val();
	    	  grossWeight = grossWeight.replace(",",".");
	    	  var netWeight = jq('#dkev_38').val("");
		  if(netWeight!=null){
			  var netWeightRaw = Math.round(grossWeight * 0.8);
			  jq('#dkev_38').val(netWeightRaw);
		  }
		  
		});
	});
  	
	
  	
  	jq(document).ready(function(){
		 //init table (no ajax, no columns since the payload is already there by means of HTML produced on the back-end)
	      jq('#tblItemLinesAll').dataTable( {
	    	  "dom": '<"top">t<"bottom"flip><"clear">',
	    	  "scrollY":    "800px",
	  		  "scrollCollapse":  true,
	  		  "columnDefs": [{ "type": "num", "targets": 0 }],
	  		  "lengthMenu": [ 75, 100, 300, 400, 900]
	  	  });
	      //init table (no ajax, no columns since the payload is already there by means of HTML produced on the back-end)
	      jq('#tblItemLines').dataTable( {
	    	  "dom": '<"top">t<"bottom"flip><"clear">',
	    	  "scrollY":    "180px",
	  		  "scrollCollapse":  true,
	  		  "columnDefs": [{ "type": "num", "targets": 0 }],
	  		  "lengthMenu": [ 75, 100, 300, 400, 900]
	  	  });
	      
	      //event on input field for search
	      jq('input.tblItemLines_filter').on( 'keyup click', function () {
	      		filterGlobal();
	      });
	      //event on input field for search
	      jq('input.tblItemLinesAll_filter').on( 'keyup click', function () {
	      		filterGlobal();
	      });
		
		
  	});
  	
  //Grid aspects on behavior usually required when updating more than 10-rows. 
	//All this helps to high-light the next-row to update...after a newly row update has taken place.
	/* OBSOLETE:    jq(document).ready(function(){
		var indx = 1;
		try{
			indx = parseInt(jq('#lastSelectedItemLineNumber').val());
			indx++;
		}catch(err){ 
			//alert("err:" + err.message)
		}
		var row = document.getElementById("parentItemLineListTable").rows;
		//do the rest ONLY if lineNr is empty (since there could be validadtion errors and in this case the code should not execute further)
		var lineNr = jq('#lineNr').val();
		if (indx > 1 && ""==lineNr){
			//alert(indx);
			row[indx].scrollIntoView(false);
			var id = "#"+row[indx].id;
			//jq(id).css("background-color","#F0F0F0");
			jq(id).css("background-color","#A3D098");
			row[indx].focus();
			
		}else{
			//focus on dkev_331
			jq('#dkev_331').focus();
		}
	});
	*/

	