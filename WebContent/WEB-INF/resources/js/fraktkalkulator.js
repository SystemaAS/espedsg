	//=============================================
	//General functions for FRAKTKALKULATOR - AJAX
	//=============================================
	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict();
  	
	
	
    //On-Change oppdragType values after user selection on tjeneste drop-down
	jq(function() { 
	    jq('#wsavd').change(function() {
	    	//default
	    	var knr = jq('#userCustnr').val();
	    	var knrAlt = jq('#avvknr').val();
	    	//override when applicable
	    	if(knrAlt != null && knrAlt != ''){ 
	    		knr = knrAlt; 
	    	}
	    	console.log("###########knr:" + knr);
	    	updateOppdragTypeDropDown(knr);
    		updateIncotermsDropDown(knr);
	    		
		});
	    //when new kundnr is registered
	    jq('#avvknr').blur(function() {
	    	//default
	    	var knr = jq('#userCustnr').val();
	    	//override when applicable
	    	if(jq('#avvknr').val()!=''){ 
	    		knr = jq('#avvknr').val(); 
	    	}	    	
	    	updateProdListDropDown(knr);
	    	updateOppdragTypeDropDown(knr);
    		updateIncotermsDropDown(knr);
		});
	});
	//Private JS function
	function updateProdListDropDown(knr) {
		jq.getJSON('updateProductList_Fraktkalkulator.do', {
			applicationUser : jq('#applicationUser').val(),
			wsavd : jq('#wsavd').val(),
			avvknr : knr,
			ajax : 'true'
		}, function(data) {
			//alert("Hello");
			var html = '<option selected value="">-velg-</option>';
			var len = data.length;
			for ( var i = 0; i < len; i++) {
				html += '<option value="' + data[i].prodkod + '">' + data[i].prodtxt + '</option>';
			}
			//now that we have our options, give them to our select
			jq('#prod').html(html);
		});
	}
	//Private JS function
	function updateOppdragTypeDropDown(knr) {
		jq.getJSON('updateOppdragType_Fraktkalkulator.do', {
			applicationUser : jq('#applicationUser').val(),
			wsavd : jq('#wsavd').val(),
			avvknr : knr,
			ajax : 'true'
		}, function(data) {
			//alert("Hello");
			var html = '<option selected value="">-velg-</option>';
			var len = data.length;
			for ( var i = 0; i < len; i++) {
				html += '<option value="' + data[i].wsot1 + '">' + data[i].wsot2 + '</option>';
			}
			//now that we have our options, give them to our select
			jq('#wsot').html(html);
		});
	}
	//Private JS function
	function updateIncotermsDropDown(knr) {
		jq.getJSON('updateIncoterms_Fraktkalkulator.do', {
			applicationUser : jq('#applicationUser').val(),
			wsavd : jq('#wsavd').val(),
			avvknr : knr,
			ajax : 'true'
		}, function(data) {
			//alert("Hello");
			var html = '<option selected value="">-velg-</option>';
			var len = data.length;
			for ( var i = 0; i < len; i++) {
				html += '<option value="' + data[i].frankod + '">' + data[i].frantxt + '</option>';
			}
			//now that we have our options, give them to our select
			jq('#frankod').html(html);
		});
	}