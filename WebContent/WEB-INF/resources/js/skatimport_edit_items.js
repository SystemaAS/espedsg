	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict();
  	var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";
  	
  	//Overlay on tab (to mark visually a delay...)
    jq(function() {
      jq('#alinkTopicList').click(function() { 
  		  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
  	  });	
  	  jq('#alinkHeader').click(function() { 
  		  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
  	  });
  	  jq('#alinkLogging').click(function() { 
		  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
	  });
  	  jq('#alinkArchive').click(function() { 
		  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
	  });
  	  
  	  jq( "#submitUpdate" ).click(function( event ) {
  		  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT });
  	  });
    });  
  	//-----------------------------------------------------------------------------
  	//jQuery CALCULATOR (related to jquery.calculator.js and jquery.calculator.css
  	//-----------------------------------------------------------------------------
  	jq(function() {
  		jq('#dkiv_42').calculator({ showOn: 'button',  
  			buttonImageOnly: true, buttonImage: 'resources/images/calculator.png', decimalChar: ','});
  		
  		//TEST jq("#parentItemLineListTable tr:last-child").focus();
  		
  	});
  	
  	
  	
  	//-----------------------------------------------
  	//Trigger points for "calculateStatistisktVarde"
  	//-----------------------------------------------
  	jq(function() {
  		jq('#dkiv_42').blur(function() {
  			//(1) Calc. aprox Gross weight (proposal to the user)
  			var unitPrice = jq('#dkiv_42').val().replace(".",""); //must clean all US notation
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
  					jq('#dkiv_35').val(proposedGross.replace(".",",")); //final EU-format (for decimals)
  				}
  			}
  			//(a) Calculate Freight/Transport costs ONLY if this is a new line (CREATE NEW mode)
  			//(b) Get pairs of Certifcate Nr. and codes if applicable. ONLY with (CREATE NEW mode)
  			var lineNr = jq('#dkiv_syli').val(); //determines if this is a new line
  			if("" == lineNr){
  				//Now get pairs of certificates if applicable
  				getCertificateNrAndCodeR442();
  				//This function includes calculateStatistisktVarde() in order to force a synchronous call to Ajax functions
  				calculateTransportCostsOnItem();
  				
  			}else{
  				//(2) Stat.value
  				calculateToldvaerdiSums();
  			}
  		});
  		
  	});
  	
  	//--------------------------------------------------
  	//GET Certificate Numbers and Codes (if applicable)
  	//--------------------------------------------------
  	function getCertificateNrAndCodeR442() {
  		jq.ajax({
			type: 'GET',
			url: 'getCertificateNrAndCodeR442_SkatImport.do',
			data: { 	applicationUser : jq('#applicationUser').val(),
					customerNumber : jq('#dkih_mokn').val(),
					sku : jq('#dkiv_331').val(), 
					countryCode : jq('#dkiv_34').val() },
			dataType: 'json',
			cache: false,
			contentType: 'application/json',
			success: function(data) {
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					//Check if there are values otherwise leave the original "EJ OMFATTET" if it exists.
					//This check is necessary only in this first value pair
					if(""!=data[i].dkse_4421 && ""!=data[i].dkse_442a){
						jq('#dkiv_4421').val(data[i].dkse_4421);
						jq('#dkiv_442a').val(data[i].dkse_442a);
					}
					jq('#dkiv_4422').val(data[i].dkse_4422);
					jq('#dkiv_442b').val(data[i].dkse_442b);
					jq('#dkiv_4423').val(data[i].dkse_4423);
					jq('#dkiv_442c').val(data[i].dkse_442c);
					jq('#dkiv_4424').val(data[i].dkse_4424);
					jq('#dkiv_442d').val(data[i].dkse_442d);
					jq('#dkiv_4425').val(data[i].dkse_4425);
					jq('#dkiv_442e').val(data[i].dkse_442e);
					jq('#dkiv_4426').val(data[i].dkse_4426);
					jq('#dkiv_442f').val(data[i].dkse_442f);
					jq('#dkiv_4427').val(data[i].dkse_4427);
					jq('#dkiv_442g').val(data[i].dkse_442g);
					jq('#dkiv_4428').val(data[i].dkse_4428);
					jq('#dkiv_442h').val(data[i].dkse_442h);
					jq('#dkiv_4429').val(data[i].dkse_4429);
					jq('#dkiv_442i').val(data[i].dkse_442i);
				}
			}
		});
  	}
  	
  	
  	//CALCULATE Freight/Transport costs via AJAX Controller here ONLY when this is a new line (create new)
  	function calculateTransportCostsOnItem() {
  		var dkStrParam = "dkiv_42=" + jq('#dkiv_42').val() + 
		   "&dkih_12=" + jq('#session_dkih_12').val() + "&dkih_12e=" + jq('#session_dkih_12e').val() + 
		   "&dkih_222=" + jq('#session_dkih_222').val() + "&dkih_201=" + jq('#session_dkih_201').val();

		jq.ajax({
			type: 'GET',
			url: 'calculateTransportCostsOnItem_SkatImport.do',
			data: { 	applicationUser : jq('#applicationUser').val(),
				dkStr : dkStrParam },
			dataType: 'json',
			cache: false,
			contentType: 'application/json',
			success: function(data) {
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					//debug info
					jq('#debugPrintlnAjaxInfo').text(data[i].debugPrintlnAjax);
					//alert("_t17a" + data[i].dkiv_t17a);
					jq('#dkiv_t17a').val(data[i].dkiv_t17a);
					jq('#dkiv_t19').val(data[i].dkiv_t19);
				}
				//CALCULATE Stat.værdi here when we must call it after the calculateTransportCostsOnItem_SkatImport.do Ajax
				calculateToldvaerdiSums();
			}
		});
  	}
  	//CALCULATE Stat.værdi (ej Avgifter) via AJAX Controller here
  	//This function is exactly the same as in tullvaerdideklaration
  	function calculateToldvaerdiSums() {
  		var dkStrParam = "dkiv_42=" + jq('#dkiv_42').val() + "&dkiv_t11b=" + jq('#dkiv_t11b').val() +
		   "&dkih_221b=" + jq('#session_dkih_221b').val() + "&dkih_221c=" + jq('#session_dkih_221c').val() + 
		   "&dkiv_t13a=" + jq('#dkiv_t13a').val() + 
		   "&dkiv_t13b=" + jq('#dkiv_t13b').val() + "&dkiv_t13c=" + jq('#dkiv_t13c').val() +
		   "&dkiv_t14a=" + jq('#dkiv_t14a').val() + "&dkiv_t14b=" + jq('#dkiv_t14b').val() +
		   "&dkiv_t14c=" + jq('#dkiv_t14c').val() + "&dkiv_t14d=" + jq('#dkiv_t14d').val() +
		   "&dkiv_t15=" + jq('#dkiv_t15').val() + "&dkiv_t16=" + jq('#dkiv_t16').val() +
		   "&dkiv_t17a=" + jq('#dkiv_t17a').val() + "&dkiv_t17b=" + jq('#dkiv_t17b').val() +
		   "&dkiv_t17c=" + jq('#dkiv_t17c').val() + "&dkiv_t19=" + jq('#dkiv_t19').val() +
		   "&dkiv_t20=" + jq('#dkiv_t20').val() + "&dkiv_t21b=" + jq('#dkiv_t21b').val() +
		   "&dkiv_t22=" + jq('#dkiv_t22').val();

		jq.ajax({
			type: 'GET',
			url: 'calculateToldvaerdiSumsOnItem_SkatImport.do',
			data: { 	applicationUser : jq('#applicationUser').val(),
				dkStr : dkStrParam },
			dataType: 'json',
			cache: false,
			contentType: 'application/json',
			success: function(data) {
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					//debug info
					jq('#debugPrintlnAjaxInfo').text(data[i].debugPrintlnAjax);
					//alert("_24" + data[i].dkiv_t24);
					jq('#dkiv_t11a').val(jq('#dkiv_42').val());
					jq('#dkiv_t12').val(data[i].dkiv_t12);
					jq('#dkiv_t18').val(data[i].dkiv_t18);
					jq('#dkiv_t23').val(data[i].dkiv_t23);
					jq('#dkiv_t24').val(data[i].dkiv_t24);
					jq('#dkiv_46').val(data[i].dkiv_t24); //dkiv_t24 = dkiv_46 ALWAYS !!
				}
			}
		});  		
  		
  	}
  	
  	
  	
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
	  	  url: 'getSpecificTopicItemChosenFromGuiElement_SkatImport.do',
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
				jq('#lineNr').val(""); jq('#lineNr').val(data[i].dkiv_syli);
				jq('#dkiv_syli').val(""); jq('#dkiv_syli').val(data[i].dkiv_syli);
				//this is for a dialog box when saving (copy elements when applicable)
				var copyLineStartLineNr = data[i].dkiv_syli;
				jq('#copyLineStartLineNr').val(""); jq('#copyLineStartLineNr').val((copyLineStartLineNr*1) + 1);
				
				jq('#dkiv_311a').val(""); jq('#dkiv_311a').val(data[i].dkiv_311a);
				jq('#dkiv_311b').val(""); jq('#dkiv_311b').val(data[i].dkiv_311b);
				jq('#dkiv_311c').val(""); jq('#dkiv_311c').val(data[i].dkiv_311c);
				jq('#dkiv_311d').val(""); jq('#dkiv_311d').val(data[i].dkiv_311d);
				jq('#dkiv_311e').val(""); jq('#dkiv_311e').val(data[i].dkiv_311e);
				//
				jq('#dkiv_312a').val(""); jq('#dkiv_312a').val(data[i].dkiv_312a);
				jq('#dkiv_312b').val(""); jq('#dkiv_312b').val(data[i].dkiv_312b);
				jq('#dkiv_312c').val(""); jq('#dkiv_312c').val(data[i].dkiv_312c);
				jq('#dkiv_312d').val(""); jq('#dkiv_312d').val(data[i].dkiv_312d);
				jq('#dkiv_312e').val(""); jq('#dkiv_312e').val(data[i].dkiv_312e);
				//
				jq('#dkiv_313a').val(""); jq('#dkiv_313a').val(data[i].dkiv_313a);
				jq('#dkiv_313b').val(""); jq('#dkiv_313b').val(data[i].dkiv_313b);
				jq('#dkiv_313c').val(""); jq('#dkiv_313c').val(data[i].dkiv_313c);
				jq('#dkiv_313d').val(""); jq('#dkiv_313d').val(data[i].dkiv_313d);
				jq('#dkiv_313e').val(""); jq('#dkiv_313e').val(data[i].dkiv_313e);
				//
				jq('#dkiv_314a').val(""); jq('#dkiv_314a').val(data[i].dkiv_314a);
				jq('#dkiv_314b').val(""); jq('#dkiv_314b').val(data[i].dkiv_314b);
				jq('#dkiv_314c').val(""); jq('#dkiv_314c').val(data[i].dkiv_314c);
				jq('#dkiv_314d').val(""); jq('#dkiv_314d').val(data[i].dkiv_314d);
				jq('#dkiv_314e').val(""); jq('#dkiv_314e').val(data[i].dkiv_314e);
				//
				jq('#dkiv_315a').val(""); jq('#dkiv_315a').val(data[i].dkiv_315a);
				jq('#dkiv_315b').val(""); jq('#dkiv_315b').val(data[i].dkiv_315b);
				jq('#dkiv_315c').val(""); jq('#dkiv_315c').val(data[i].dkiv_315c);
				jq('#dkiv_315d').val(""); jq('#dkiv_315d').val(data[i].dkiv_315d);
				jq('#dkiv_315e').val(""); jq('#dkiv_315e').val(data[i].dkiv_315e);
				//
				jq('#dkiv_28b').val(""); jq('#dkiv_28b').val(data[i].dkiv_28b);
				jq('#dkiv_32').val(""); jq('#dkiv_32').val(data[i].dkiv_32);
				jq('#dkiv_331').val(""); jq('#dkiv_331').val(data[i].dkiv_331);
				jq('#dkiv_332').val(""); jq('#dkiv_332').val(data[i].dkiv_332);
				jq('#dkiv_333').val(""); jq('#dkiv_333').val(data[i].dkiv_333);
				jq('#dkiv_sikk').val(""); jq('#dkiv_sikk').val(data[i].dkiv_sikk);
				jq('#dkiv_34').val(""); jq('#dkiv_34').val(data[i].dkiv_34);
				jq('#dkiv_35').val(""); jq('#dkiv_35').val(data[i].dkiv_35);
				jq('#dkiv_36').val(""); jq('#dkiv_36').val(data[i].dkiv_36);
				jq('#dkiv_37').val(""); jq('#dkiv_37').val(data[i].dkiv_37);
				jq('#dkiv_38').val(""); jq('#dkiv_38').val(data[i].dkiv_38);
				jq('#dkiv_39').val(""); jq('#dkiv_39').val(data[i].dkiv_39);
				jq('#dkiv_s27').val(""); jq('#dkiv_s27').val(data[i].dkiv_s27);
				jq('#dkiv_402a').val(""); jq('#dkiv_402a').val(data[i].dkiv_402a);
				jq('#dkiv_403a').val(""); jq('#dkiv_403a').val(data[i].dkiv_403a);
				jq('#dkiv_402b').val(""); jq('#dkiv_402b').val(data[i].dkiv_402b);
				jq('#dkiv_403b').val(""); jq('#dkiv_403b').val(data[i].dkiv_403b);
				jq('#dkiv_402c').val(""); jq('#dkiv_402c').val(data[i].dkiv_402c);
				jq('#dkiv_403c').val(""); jq('#dkiv_403c').val(data[i].dkiv_403c);
				jq('#dkiv_402d').val(""); jq('#dkiv_402d').val(data[i].dkiv_402d);
				jq('#dkiv_403d').val(""); jq('#dkiv_403d').val(data[i].dkiv_403d);
				jq('#dkiv_402e').val(""); jq('#dkiv_402e').val(data[i].dkiv_402e);
				jq('#dkiv_403e').val(""); jq('#dkiv_403e').val(data[i].dkiv_403e);
				jq('#dkiv_402f').val(""); jq('#dkiv_402f').val(data[i].dkiv_402f);
				jq('#dkiv_403f').val(""); jq('#dkiv_403f').val(data[i].dkiv_403f);
				jq('#dkiv_402g').val(""); jq('#dkiv_402g').val(data[i].dkiv_402g);
				jq('#dkiv_403g').val(""); jq('#dkiv_403g').val(data[i].dkiv_403g);
				jq('#dkiv_402h').val(""); jq('#dkiv_402h').val(data[i].dkiv_402h);
				jq('#dkiv_403h').val(""); jq('#dkiv_403h').val(data[i].dkiv_403h);
				jq('#dkiv_402i').val(""); jq('#dkiv_402i').val(data[i].dkiv_402i);
				jq('#dkiv_403i').val(""); jq('#dkiv_403i').val(data[i].dkiv_403i);
				jq('#dkiv_402j').val(""); jq('#dkiv_402j').val(data[i].dkiv_402j);
				jq('#dkiv_403j').val(""); jq('#dkiv_403j').val(data[i].dkiv_403j);
				jq('#dkiv_402k').val(""); jq('#dkiv_402k').val(data[i].dkiv_402k);
				jq('#dkiv_403k').val(""); jq('#dkiv_403k').val(data[i].dkiv_403k);
				jq('#dkiv_402l').val(""); jq('#dkiv_402l').val(data[i].dkiv_402l);
				jq('#dkiv_403l').val(""); jq('#dkiv_403l').val(data[i].dkiv_403l);
				jq('#dkiv_402m').val(""); jq('#dkiv_402m').val(data[i].dkiv_402m);
				jq('#dkiv_403m').val(""); jq('#dkiv_403m').val(data[i].dkiv_403m);
				jq('#dkiv_402n').val(""); jq('#dkiv_402n').val(data[i].dkiv_402n);
				jq('#dkiv_403n').val(""); jq('#dkiv_403n').val(data[i].dkiv_403n);
				jq('#dkiv_402o').val(""); jq('#dkiv_402o').val(data[i].dkiv_402o);
				jq('#dkiv_403o').val(""); jq('#dkiv_403o').val(data[i].dkiv_403o);
				jq('#dkiv_402p').val(""); jq('#dkiv_402p').val(data[i].dkiv_402p);
				jq('#dkiv_403p').val(""); jq('#dkiv_403p').val(data[i].dkiv_403p);
				jq('#dkiv_402q').val(""); jq('#dkiv_402q').val(data[i].dkiv_402q);
				jq('#dkiv_403q').val(""); jq('#dkiv_403q').val(data[i].dkiv_403q);
				jq('#dkiv_402r').val(""); jq('#dkiv_402r').val(data[i].dkiv_402r);
				jq('#dkiv_403r').val(""); jq('#dkiv_403r').val(data[i].dkiv_403r);
				jq('#dkiv_402s').val(""); jq('#dkiv_402s').val(data[i].dkiv_402s);
				jq('#dkiv_403s').val(""); jq('#dkiv_403s').val(data[i].dkiv_403s);
				
				jq('#dkiv_411').val(""); jq('#dkiv_411').val(data[i].dkiv_411);
				jq('#dkiv_412').val(""); jq('#dkiv_412').val(data[i].dkiv_412);
				jq('#dkiv_42').val(""); jq('#dkiv_42').val(data[i].dkiv_42);
				jq('#dkiv_441').val(""); jq('#dkiv_441').val(data[i].dkiv_441);
				//Certifikatkode
				jq('#dkiv_4421').val(""); jq('#dkiv_4421').val(data[i].dkiv_4421);
				jq('#dkiv_4422').val(""); jq('#dkiv_4422').val(data[i].dkiv_4422);
				jq('#dkiv_4423').val(""); jq('#dkiv_4423').val(data[i].dkiv_4423);
				jq('#dkiv_4424').val(""); jq('#dkiv_4424').val(data[i].dkiv_4424);
				jq('#dkiv_4425').val(""); jq('#dkiv_4425').val(data[i].dkiv_4425);
				jq('#dkiv_4426').val(""); jq('#dkiv_4426').val(data[i].dkiv_4426);
				jq('#dkiv_4427').val(""); jq('#dkiv_4427').val(data[i].dkiv_4427);
				jq('#dkiv_4428').val(""); jq('#dkiv_4428').val(data[i].dkiv_4428);
				jq('#dkiv_4429').val(""); jq('#dkiv_4429').val(data[i].dkiv_4429);
				//Certifikatnr.				
				jq('#dkiv_442a').val(""); jq('#dkiv_442a').val(data[i].dkiv_442a);
				jq('#dkiv_442b').val(""); jq('#dkiv_442b').val(data[i].dkiv_442b);
				jq('#dkiv_442c').val(""); jq('#dkiv_442c').val(data[i].dkiv_442c);
				jq('#dkiv_442d').val(""); jq('#dkiv_442d').val(data[i].dkiv_442d);
				jq('#dkiv_442e').val(""); jq('#dkiv_442e').val(data[i].dkiv_442e);
				jq('#dkiv_442f').val(""); jq('#dkiv_442f').val(data[i].dkiv_442f);
				jq('#dkiv_442g').val(""); jq('#dkiv_442g').val(data[i].dkiv_442g);
				jq('#dkiv_442h').val(""); jq('#dkiv_442h').val(data[i].dkiv_442h);
				jq('#dkiv_442i').val(""); jq('#dkiv_442i').val(data[i].dkiv_442i);
				
				jq('#dkiv_443').val(""); jq('#dkiv_443').val(data[i].dkiv_443);
				jq('#dkiv_444a').val(""); jq('#dkiv_444a').val(data[i].dkiv_444a);
				jq('#dkiv_444b').val(""); jq('#dkiv_444b').val(data[i].dkiv_444b);
				jq('#dkiv_46').val(""); jq('#dkiv_46').val(data[i].dkiv_46);
				
				//Rubrik 44.6 (Code)
				jq('#dkiv_446ka').val(""); jq('#dkiv_446ka').val(data[i].dkiv_446ka);
				jq('#dkiv_446kb').val(""); jq('#dkiv_446kb').val(data[i].dkiv_446kb);
				jq('#dkiv_446kf').val(""); jq('#dkiv_446kf').val(data[i].dkiv_446kf);
				jq('#dkiv_446kj').val(""); jq('#dkiv_446kj').val(data[i].dkiv_446kj);
				jq('#dkiv_446kn').val(""); jq('#dkiv_446kn').val(data[i].dkiv_446kn);
				jq('#dkiv_446kr').val(""); jq('#dkiv_446kr').val(data[i].dkiv_446kr);
				//Rubrik 44.6 (Text)
				jq('#dkiv_446ta').val(""); jq('#dkiv_446ta').val(data[i].dkiv_446ta);
				// Belong to dkiv_446kb
				jq('#dkiv_446tb').val(""); jq('#dkiv_446tb').val(data[i].dkiv_446tb);
				jq('#dkiv_446tc').val(""); jq('#dkiv_446tc').val(data[i].dkiv_446tc);
				jq('#dkiv_446td').val(""); jq('#dkiv_446td').val(data[i].dkiv_446td);
				jq('#dkiv_446te').val(""); jq('#dkiv_446te').val(data[i].dkiv_446te);
				// Belong to dkiv_446kf				
				jq('#dkiv_446tf').val(""); jq('#dkiv_446tf').val(data[i].dkiv_446tf);
				jq('#dkiv_446tg').val(""); jq('#dkiv_446tg').val(data[i].dkiv_446tg);
				jq('#dkiv_446th').val(""); jq('#dkiv_446th').val(data[i].dkiv_446th);
				jq('#dkiv_446ti').val(""); jq('#dkiv_446ti').val(data[i].dkiv_446ti);
				// Belong to dkiv_446kj
				jq('#dkiv_446tj').val(""); jq('#dkiv_446tj').val(data[i].dkiv_446tj);
				jq('#dkiv_446tk').val(""); jq('#dkiv_446tk').val(data[i].dkiv_446tk);
				jq('#dkiv_446tl').val(""); jq('#dkiv_446tl').val(data[i].dkiv_446tl);
				jq('#dkiv_446tm').val(""); jq('#dkiv_446tm').val(data[i].dkiv_446tm);
				// Belong to dkiv_446kn
				jq('#dkiv_446tn').val(""); jq('#dkiv_446tn').val(data[i].dkiv_446tn);
				jq('#dkiv_446to').val(""); jq('#dkiv_446to').val(data[i].dkiv_446to);
				jq('#dkiv_446tp').val(""); jq('#dkiv_446tp').val(data[i].dkiv_446tp);
				jq('#dkiv_446tq').val(""); jq('#dkiv_446tq').val(data[i].dkiv_446tq);
				// Belong to dkiv_446kr
				jq('#dkiv_446tr').val(""); jq('#dkiv_446tr').val(data[i].dkiv_446tr);
				jq('#dkiv_446ts').val(""); jq('#dkiv_446ts').val(data[i].dkiv_446ts);
				
				
				//Rubrik 44.9
				jq('#dkiv_449a').val(""); jq('#dkiv_449a').val(data[i].dkiv_449a);
				jq('#dkiv_449b').val(""); jq('#dkiv_449b').val(data[i].dkiv_449b);
				//Afgifter
				jq('#dkiva_aba1').val(""); jq('#dkiva_aba1').val(data[i].dkiva_aba1);
				jq('#dkiva_abk1').val(""); jq('#dkiva_abk1').val(data[i].dkiva_abk1);
				jq('#dkiva_abg1').val(""); jq('#dkiva_abg1').val(data[i].dkiva_abg1);
				jq('#dkiva_abs1').val(""); jq('#dkiva_abs1').val(data[i].dkiva_abs1);
				jq('#dkiva_abx1').val(""); jq('#dkiva_abx1').val(data[i].dkiva_abx1);
				jq('#dkiva_abb1').val(""); jq('#dkiva_abb1').val(data[i].dkiva_abb1);
				//
				jq('#dkiva_aba2').val(""); jq('#dkiva_aba2').val(data[i].dkiva_aba2);
				jq('#dkiva_abk2').val(""); jq('#dkiva_abk2').val(data[i].dkiva_abk2);
				jq('#dkiva_abg2').val(""); jq('#dkiva_abg2').val(data[i].dkiva_abg2);
				jq('#dkiva_abs2').val(""); jq('#dkiva_abs2').val(data[i].dkiva_abs2);
				jq('#dkiva_abx2').val(""); jq('#dkiva_abx2').val(data[i].dkiva_abx2);
				jq('#dkiva_abb2').val(""); jq('#dkiva_abb2').val(data[i].dkiva_abb2);
				//
				jq('#dkiva_aba3').val(""); jq('#dkiva_aba3').val(data[i].dkiva_aba3);
				jq('#dkiva_abk3').val(""); jq('#dkiva_abk3').val(data[i].dkiva_abk3);
				jq('#dkiva_abg3').val(""); jq('#dkiva_abg3').val(data[i].dkiva_abg3);
				jq('#dkiva_abs3').val(""); jq('#dkiva_abs3').val(data[i].dkiva_abs3);
				jq('#dkiva_abx3').val(""); jq('#dkiva_abx3').val(data[i].dkiva_abx3);
				jq('#dkiva_abb3').val(""); jq('#dkiva_abb3').val(data[i].dkiva_abb3);
				//
				jq('#dkiva_aba4').val(""); jq('#dkiva_aba4').val(data[i].dkiva_aba4);
				jq('#dkiva_abk4').val(""); jq('#dkiva_abk4').val(data[i].dkiva_abk4);
				jq('#dkiva_abg4').val(""); jq('#dkiva_abg4').val(data[i].dkiva_abg4);
				jq('#dkiva_abs4').val(""); jq('#dkiva_abs4').val(data[i].dkiva_abs4);
				jq('#dkiva_abx4').val(""); jq('#dkiva_abx4').val(data[i].dkiva_abx4);
				jq('#dkiva_abb4').val(""); jq('#dkiva_abb4').val(data[i].dkiva_abb4);
				//
				jq('#dkiva_aba5').val(""); jq('#dkiva_aba5').val(data[i].dkiva_aba5);
				jq('#dkiva_abk5').val(""); jq('#dkiva_abk5').val(data[i].dkiva_abk5);
				jq('#dkiva_abg5').val(""); jq('#dkiva_abg5').val(data[i].dkiva_abg5);
				jq('#dkiva_abs5').val(""); jq('#dkiva_abs5').val(data[i].dkiva_abs5);
				jq('#dkiva_abx5').val(""); jq('#dkiva_abx5').val(data[i].dkiva_abx5);
				jq('#dkiva_abb5').val(""); jq('#dkiva_abb5').val(data[i].dkiva_abb5);
				//Tollværdi
				jq('#dkiv_t11a').val(""); jq('#dkiv_t11a').val(data[i].dkiv_42);
				jq('#dkiv_t11b').val(""); jq('#dkiv_t11b').val(data[i].dkiv_t11b);
				jq('#dkiv_t12').val(""); jq('#dkiv_t12').val(data[i].dkiv_t12);
				jq('#dkiv_t13a').val(""); jq('#dkiv_t13a').val(data[i].dkiv_t13a);
				jq('#dkiv_t13b').val(""); jq('#dkiv_t13b').val(data[i].dkiv_t13b);
				jq('#dkiv_t13c').val(""); jq('#dkiv_t13c').val(data[i].dkiv_t13c);
				jq('#dkiv_t14a').val(""); jq('#dkiv_t14a').val(data[i].dkiv_t14a);
				jq('#dkiv_t14b').val(""); jq('#dkiv_t14b').val(data[i].dkiv_t14b);
				jq('#dkiv_t14c').val(""); jq('#dkiv_t14c').val(data[i].dkiv_t14c);
				jq('#dkiv_t14d').val(""); jq('#dkiv_t14d').val(data[i].dkiv_t14d);
				jq('#dkiv_t15').val(""); jq('#dkiv_t15').val(data[i].dkiv_t15);
				jq('#dkiv_t16').val(""); jq('#dkiv_t16').val(data[i].dkiv_t16);
				jq('#dkiv_t17a').val(""); jq('#dkiv_t17a').val(data[i].dkiv_t17a);
				jq('#dkiv_t17b').val(""); jq('#dkiv_t17b').val(data[i].dkiv_t17b);
				jq('#dkiv_t17c').val(""); jq('#dkiv_t17c').val(data[i].dkiv_t17c);
				jq('#dkiv_t18').val(""); jq('#dkiv_t18').val(data[i].dkiv_t18);
				jq('#dkiv_t19').val(""); jq('#dkiv_t19').val(data[i].dkiv_t19);
				jq('#dkiv_t20').val(""); jq('#dkiv_t20').val(data[i].dkiv_t20);
				jq('#dkiv_t21a').val(""); jq('#dkiv_t21a').val(data[i].dkiv_t21a);
				jq('#dkiv_t21b').val(""); jq('#dkiv_t21b').val(data[i].dkiv_t21b);
				jq('#dkiv_t22').val(""); jq('#dkiv_t22').val(data[i].dkiv_t22);
				jq('#dkiv_t23').val(""); jq('#dkiv_t23').val(data[i].dkiv_t23);
				jq('#dkiv_t24').val(""); jq('#dkiv_t24').val(data[i].dkiv_t24); 
				jq('#dkiv_t25a').val(""); jq('#dkiv_t25a').val(data[i].dkiv_t25a);
				jq('#dkiv_t25b').val(""); jq('#dkiv_t25b').val(data[i].dkiv_t25b);
				jq('#dkiv_t25c').val(""); jq('#dkiv_t25c').val(data[i].dkiv_t25c);
				jq('#dkiv_t25d').val(""); jq('#dkiv_t25d').val(data[i].dkiv_t25d);
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
			jq.getJSON('searchTaricVarukod_SkatImport.do', {
				applicationUser : jq('#applicationUser').val(),
				taricVarukod : jq('#search_dkiv_331').val(),
				ajax : 'true'
			}, function(data) {
				var html = '<option selected value="">-Select-</option>';
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					var optionValue = data[i].dktara02;
					if(data[i].dktara63.length > 0){
						//this is done in order to take care of the supp.enhet (dkiv_411) through a string split (when taricVarukodList.change)
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
		  jq('#dkiv_331').val("");
		  //and populate (if applicable)
		  var key = jq('#taricVarukodList').val();
		  var separator = key.indexOf("@");
		  //alert("will alert here...");
		  if(separator>0){
			  jq('#dkiv_331').val(key.substring(0,separator));
			  //alert("combo:" + key.substring(separator+1));
			  jq('#dkiv_411').val(key.substring(separator+1));
		  }else{
			  //alert("combo: separator == 0");
			  jq('#dkiv_331').val(key);
			  jq('#dkiv_411').val("");
		  }
		  			  
		});
	});
  	//----------------------------------
	//Events Varukod (SEARCH window)
	//----------------------------------
	//img click
	jq(function() {	    
		jq('#imgTaricVarukodSearch').click(function(){
    			jq("#search_dkiv_331").focus();
    		});
	});
	
	jq(function() {	    
		jq('#search_dkiv_331').keypress(function(e){
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
			    	jq('#dkiv_331').val(""); 
				//now populate (if applicable)
			    	var key = jq('#taricVarukodList').val();
			    	jq('#dkiv_331').val(key); 
		    	}
	    });
	    
	});
	//This blur function is used when the end-user does not uses the taricVarukodlist.change (jQuery) due to his/her knowledge of the varukod
	//In this case the Ajax function must be called when the user leaves the varukod field...
	jq(function() {	    
		jq('#dkiv_331').blur(function(e){
			jq.getJSON('searchTaricVarukod_SkatImport.do', {
				applicationUser : jq('#applicationUser').val(),
				taricVarukod : jq('#dkiv_331').val(),
				ajax : 'true'
			}, function(data) {
				//var html = '<option selected value="">-Select-</option>';
				var len = data.length;
				var dktara63 = "";
				for ( var i = 0; i < len; i++) {
					if(data[i].dktara63.length > 0){
						//this is done in order to take care of the supp.enhet (dkiv_411) through a string split (when taricVarukodList.change)
						dktara63 = data[i].dktara63;
					}
				}
				//Use the value or change a previous value into blank
				if(dktara63 != ""){
					jq('#dkiv_411').val(dktara63);
				}else{
					jq('#dkiv_411').val("");
				}
				
			});		
    		});
	});
  	
	
  	
 	
  	//calculate a net weight from the gross weight
  	jq(function() { 
	    jq('#dkiv_35').blur(function() {
		  //init field(s)
	    	  var grossWeight = jq('#dkiv_35').val();
	    	  grossWeight = grossWeight.replace(",",".");
	    	  var netWeight = jq('#dkiv_38').val("");
		  if(netWeight!=null){
			  var netWeightRaw = Math.round(grossWeight * 0.8);
			  jq('#dkiv_38').val(netWeightRaw);
		  }
		  
		});
	});
  	
	//Currency AJAX fetch
	jq(function() { 
	    jq('#dkiv_t25c').change(function() {
	    	//alert('Hej');
	    	//this parameters must match the AJAX controller parameter names in Spring exactly...
			jq.getJSON('getCurrencyRate_Skat.do', {
				applicationUser : jq('#applicationUser').val(),
				currencyCode : jq('#dkiv_t25c').val(),
				ajax : 'true'
			}, function(data) {
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					jq('#dkiv_t25d').val(data[i].dkvk_krs);
					//jq('#dkih_vaom').val(data[i].dkvs_omr);
				}
				
			});
	    });
	});
	
	//Initialize <div> here
	  jq(function() { 
		  jq( ".clazz_dialog" ).each(function(){
			jq(this).dialog({
				autoOpen: false,
				modal: true
				//maxWidth:400,
		        //maxHeight: 300,
		        //width: 400,
		        //height: 300,
			});
		  });
	  });
	  //Present dialog box onClick (href in parent JSP)
	  jq(function() {
		  jq("#submitUpdate").click(function() {
			  var autoCopyExists = false;
			  var totalNumberOfItemLinesJsp = jq("#totalNumberOfItemLinesJsp").val();
			  var lineNr = jq("#lineNr").val();
			  jq(".clazz_copyElement").each(function(){
				  if(jq(this).is(':checked')){
					  if(lineNr*1 < totalNumberOfItemLinesJsp*1 ){
						  autoCopyExists = true;
					  }else{
						  //this values will flag the controller to indicate to copy to the next new line (without saving to the database)
						  //only to put in the model/session in order to autofill the element(s) for the next new record.
						  jq("#copyLineStartLineNr").val("-1");
						  jq("#copyLineEndLineNr").val("-1");
					  }
				  }else{
					  //to clear all old values in case no checkbox has been selected
					  jq("#copyLineStartLineNr").val("");
					  jq("#copyLineEndLineNr").val("");
				  }
			  });
			  if(autoCopyExists){
			  	  //alert("B");
				  jq("#copyLineStartLineNr").val(lineNr*1 + 1);
				  jq("#copyLineEndLineNr").val("99");
				  jq("#copyLineStartLineNr").attr("readonly", true); 
				  //jq("#copyLineEndLineNr").attr("readonly", true);
				  
				  //setters (add more if needed)
				  jq('#dialogCopy').dialog( "option", "title", "Kopi elementer " );
				  //deal with buttons for this modal window
				  jq('#dialogCopy').dialog({
					 buttons: [ 
			            {
						 id: "dialogSave",	
						 text: "Fortsæt",
						 click: function(){
							 		//alert("START-LINE:" + jq('#copyLineStartLineNr').val());
							 		//we must add the jQuery box since it is not included in the form by default.
							 		jq('div#dialogCopy').parent().appendTo('form#tdsImportEditTopicItemForm');
							 		//here is the form submit at last!
							 		jq('#tdsImportEditTopicItemForm').submit();
				 				}
					 	 },
			 	 		{
					 	 id: "dialogCancel",
					 	 text: "Annullér", 
						 click: function(){
							 		//back to initial state of form elements on modal dialog
							 		jq("#dialogSave").button("option", "disabled", true);
							 		jq("#copyLineStartLineNr").val("");
							 		jq("#copyLineEndLineNr").val("");
									jq( this ).dialog( "close" ); 
						 		} 
			 	 		 } ] 
					  
				  });
				  //init values
				  //jq("#dialogSave").button("option", "disabled", true);
				  
				  //open now
				  jq('#dialogCopy').dialog('open');
				  return false;
			  }else{
				  //to clear all old values in case no checkbox has been selected
				  jq("#copyLineStartLineNr").val("");
				  jq("#copyLineEndLineNr").val("");
				  //submit the form without prompting the user with the dialog box
				  jq('div#dialogCopy').parent().appendTo('form#tdsImportEditTopicItemForm');
				  jq('#tdsImportEditTopicItemForm').submit();
			  }
			  
		  });
	  });
  	
	//Grid aspects on behavior usually required when updating more than 10-rows. 
	//All this helps to high-light the next-row to update...after a newly row update has taken place.
	jq(document).ready(function(){
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
			//focus on dkiv_331
			jq('#dkiv_331').focus();
		}
	});
	  	
	  
	  
  	

	