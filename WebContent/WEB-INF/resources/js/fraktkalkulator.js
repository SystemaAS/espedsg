	//=============================================
	//General functions for FRAKTKALKULATOR - AJAX
	//=============================================
	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict();
  	
	
	
    //On-Change oppdragType values after user selection on tjeneste drop-down
	jq(function() { 
	    jq('#wsavd').change(function() {
	    		updateOppdragTypeDropDown();
	    		updateIncotermsDropDown();
	    		
		});
	});
	//Private JS function
	function updateOppdragTypeDropDown() {
		jq.getJSON('updateOppdragType_Fraktkalkulator.do', {
			applicationUser : jq('#applicationUser').val(),
			wsavd : jq('#wsavd').val(),
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
	function updateIncotermsDropDown() {
		jq.getJSON('updateIncoterms_Fraktkalkulator.do', {
			applicationUser : jq('#applicationUser').val(),
			wsavd : jq('#wsavd').val(),
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