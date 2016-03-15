	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict();
  	
  	//----------------------------------------------
  	//Trigger points for toldvaerdi sums calculation
  	//----------------------------------------------
  	jq(function() {
  		jq('#dkiv_t11b').blur(function() { calculateToldvaerdiSums(); });
  		jq('#dkiv_t13a').blur(function() { calculateToldvaerdiSums(); });
  		jq('#dkiv_t13b').blur(function() { calculateToldvaerdiSums(); });
  		jq('#dkiv_t13c').blur(function() { calculateToldvaerdiSums(); });
  		jq('#dkiv_t14a').blur(function() { calculateToldvaerdiSums(); });
  		jq('#dkiv_t14b').blur(function() { calculateToldvaerdiSums(); });
  		jq('#dkiv_t14c').blur(function() { calculateToldvaerdiSums(); });
  		jq('#dkiv_t14d').blur(function() { calculateToldvaerdiSums(); });
  		jq('#dkiv_t15').blur(function() { calculateToldvaerdiSums(); });
  		jq('#dkiv_t16').blur(function() { calculateToldvaerdiSums(); });
  		jq('#dkiv_t17a').blur(function() { calculateToldvaerdiSums(); });
  		jq('#dkiv_t17b').blur(function() { calculateToldvaerdiSums(); });
  		jq('#dkiv_t17c').blur(function() { calculateToldvaerdiSums(); });
  		jq('#dkiv_t19').blur(function() { calculateToldvaerdiSums(); });
  		jq('#dkiv_t20').blur(function() { calculateToldvaerdiSums(); });
  		jq('#dkiv_t21b').blur(function() { calculateToldvaerdiSums(); });
  		jq('#dkiv_t22').blur(function() { calculateToldvaerdiSums(); });

  	});
  	
  	//CALCULATE Tollværdi-delsummor via AJAX Controller here
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
					jq('#dkiv_t12').val(data[i].dkiv_t12);
					jq('#dkiv_t18').val(data[i].dkiv_t18);
					jq('#dkiv_t23').val(data[i].dkiv_t23);
					jq('#dkiv_t24').val(data[i].dkiv_t24);
					jq('#dkiv_46').val(data[i].dkiv_t24); //dkiv_t24 = dkiv_46 ALWAYS !!
					
				}
			}
		});
  	}
  	

	