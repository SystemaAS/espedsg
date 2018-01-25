  //this variable is a global jQuery var instead of using "$" all the time. Very handy
  var jq = jQuery.noConflict();
  var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";

  function setBlockUI(element){
	  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
  }
  
//Global functions
  function g_getCurrentYearStr(){
	  return new Date().getFullYear().toString();
  }
  function g_getCurrentMonthStr(){
	  var currentMonth = new Date().getMonth() + 1;
	  var currentMonthStr = currentMonth.toString();
	  if (currentMonth < 10) { currentMonthStr = '0' + currentMonth; }
	  return currentMonthStr;
  }
  
  //TEXTAREA jQuery function to limit the number of characters per line
  //and per textarea
  //messageNoteConsignee
  jq(function() {
	    var limit = function (event) {
	        var linha = jq(this).attr("limit").split(",")[0];
	        var coluna = jq(this).attr("limit").split(",")[1];
	
	        var array = jq(this)
	            .val()
	            .split("\n");
	
	        jq.each(array, function (i, value) {
	            array[i] = value.slice(0, linha);
	        });
	
	        if (array.length >= coluna) {
	            array = array.slice(0, coluna);
	        }
	        jq(this).val(array.join("\n"))
	    }
	    //function call
	    jq("#messageNoteConsignee[limit]")
	        .keydown(limit)
	        .keyup(limit);
  });
  //messageNoteCarrier
  jq(function() {
	  var limit = function (event) {
	      var linha = jq(this).attr("limit").split(",")[0];
	      var coluna = jq(this).attr("limit").split(",")[1];
	
	      var array = jq(this)
	          .val()
	          .split("\n");
	
	      jq.each(array, function (i, value) {
	          array[i] = value.slice(0, linha);
	      });
	
	      if (array.length >= coluna) {
	          array = array.slice(0, coluna);
	      }
	      jq(this).val(array.join("\n"))
	    }
	    
	  	jq("#messageNoteCarrier[limit]")
	    .keydown(limit)
	    .keyup(limit);
	   
  });
  //END TEXTAREA
  
  
  jq(function() {
	  //custom validity
	  jq('#dfsg').focus(function() {
	    	if(jq('#dfsg').val()!=''){
	    		refreshCustomValidity(jq('#dfsg')[0]);
	  		}
	  });
	  jq('#dfbela').focus(function() {
	    	if(jq('#dfbela').val()!=''){
	    		refreshCustomValidity(jq('#dfbela')[0]);
	  		}
	  });
	  
	  //Carrier
	  jq('#trorCarrierIdLink').click(function() {
		  jq('#trorCarrierIdLink').attr('target','_blank');
		  window.open('tror_mainorder_childwindow_carrier.do?action=doFind&ctype=tror_car_fb&knr=' + jq('#dftran').val(),"carrierWin","top=300px,left=150px,height=800px,width=900px,scrollbars=no,status=no,location=no");
	  });
	  jq('#trorCarrierIdLink').keypress(function(e){ //extra feature for the end user
		  if(e.which == 13) {
			  jq('#trorCarrierIdLink').click();
		  }
	  });
	  //Buyer
	  jq('#trorBuyerIdLink').click(function() {
		  jq('#trorBuyerIdLink').attr('target','_blank');
		  window.open('mainmaintenance_childwindow_customer.do?action=doFind&ctype=tror_by_fb&knr=' + jq('#dfknsm').val(),"customerWin","top=300px,left=150px,height=800px,width=900px,scrollbars=no,status=no,location=no");
	  });
	  jq('#trorBuyerIdLink').keypress(function(e){ //extra feature for the end user
		  if(e.which == 13) {
			  jq('#trorBuyerIdLink').click();
		  }
	  });
	  //Buyer addresses
	  jq('#trorBuyerAddressesIdLink').click(function() {
		  jq('#trorBuyerAddressesIdLink').attr('target','_blank');
		  window.open('tror_mainorderlandimport_childwindow_buyer_addresses.do?action=doFind&ctype=tror_byadr_fb&kundnr=' + jq('#dfknsm').val(),"buyerAdrWin","top=300px,left=150px,height=600px,width=900px,scrollbars=no,status=no,location=no");
	  });
	  jq('#trorBuyerAddressesIdLink').keypress(function(e){ //extra feature for the end user
		  if(e.which == 13) {
			  jq('#trorBuyerAddressesIdLink').click();
		  }
	  });
	  //Seller
	  jq('#trorSellerIdLink').click(function() {
		  jq('#trorSellerIdLink').attr('target','_blank');
		  window.open('mainmaintenance_childwindow_customer.do?action=doFind&ctype=tror_se_fb&knr=' + jq('#dfknss').val(),"customerWin","top=300px,left=150px,height=800px,width=900px,scrollbars=no,status=no,location=no");
	  });
	  jq('#trorSellerIdLink').keypress(function(e){ //extra feature for the end user
		  if(e.which == 13) {
			  jq('#trorSellerIdLink').click();
		  }
	  });
	  //Seller addresses
	  jq('#trorSellerAddressesIdLink').click(function() {
		  jq('#trorSellerAddressesIdLink').attr('target','_blank');
		  //has to be buyer_addresses because of the dataset 
		  window.open('tror_mainorderlandimport_childwindow_buyer_addresses.do?action=doFind&ctype=tror_seadr_fb&kundnr=' + jq('#dfknss').val(),"buyerAdrWin","top=300px,left=150px,height=600px,width=900px,scrollbars=no,status=no,location=no");
	  });
	  jq('#trorSellerAddressesIdLink').keypress(function(e){ //extra feature for the end user
		  if(e.which == 13) {
			  jq('#trorSellerAddressesIdLink').click();
		  }
	  });
	  
	  //Annen fraktbetaler addresses
	  jq('#trorAnnenFraktbetalerIdLink').click(function() {
		  jq('#trorAnnenFraktbetalerIdLink').attr('target','_blank');
		  window.open('mainmaintenance_childwindow_customer.do?action=doFind&ctype=tror_annen_fb&knr=' + jq('#dfknsx').val(),"customerWin","top=300px,left=150px,height=800px,width=900px,scrollbars=no,status=no,location=no");
	  });
	  jq('#trorAnnenFraktbetalerIdLink').keypress(function(e){ //extra feature for the end user
		  if(e.which == 13) {
			  jq('#trorAnnenFraktbetalerIdLink').click();
		  }
	  });
	  
	  jq('#merkelappIdLink').click(function() {
	    	jq('#merkelappIdLink').attr('target','_blank');
	    	window.open('tror_mainorder_childwindow_generalcodes.do?action=doFind&ctype=tror_dfkdme_fb&kftype=MLAPKOD', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	  });
	  jq('#merkelappIdLink').keypress(function(e){ //extra feature for the end user
  		if(e.which == 13) {
  			jq('#merkelappIdLink').click();
  		}
	  });
	  
	  //TOLLSTED
	  jq('#dftollIdLink').click(function() {
		  jq('#dftollIdLink').attr('target','_blank');
      	  window.open('tror_mainorder_childwindow_tollsted.do?action=doFind&ctype=landimport_fb&direction=fra', "tollstedWin", "top=300px,left=450px,height=500px,width=700px,scrollbars=no,status=no,location=no");
	  });
      jq('#dftollIdLink').keypress(function(e){ //extra feature for the end user
    		if(e.which == 13) {
    			jq('#dftollIdLink').click();
    		}
      });
      
      //POSTAL CODE PONRN
	  jq('#trorPostalCodePonrnIdLink').click(function() {
		  jq('#trorPostalCodePonrnIdLink').attr('target','_blank');
      	  window.open('tror_mainorder_childwindow_postalcodes_ponrn.do?action=doFind&ctype=landimport_fb_dfpoul&ponnr=' + jq('#dfpoul').val() , "postalCodeWin", "top=300px,left=150px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	  });
      jq('#trorPostalCodePonrnIdLink').keypress(function(e){ //extra feature for the end user
    		if(e.which == 13) {
    			jq('#trorPostalCodePonrnIdLink').click();
    		}
      });
      
      //Button for DOKUFM --> Merke, snd.nivå
      jq('#dokufmButton').click(function() {
	    	window.open('tror_mainorder_childwindow_freightbill_merke_kolli.do?action=doFind&fmavd=' + jq('#dfavd').val() + '&fmopd=' + jq('#dfopd').val() + '&fmfbnr=' + jq('#dffbnr').val() , "dokufmWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	  });
	  
      
	 
	  //Adjust fields that are not String in target Dao object
	  jq("#formRecord").submit(function() {
		  if (jq('#dfm3').val() =='' ) {
			jq('#dfm3').val("0");
		  } else if (jq('#dfm3').val() !='') {  //BigDecimal
			jq("#dfm3").val(jq("#dfm3").val().replace(',', '.'));
		  }
		  if (jq('#dflm').val() =='' ) {
			jq('#dflm').val("0");
		  } else if (jq('#dflm').val() !='') {  //BigDecimal
			jq("#dflm").val(jq("#dflm").val().replace(',', '.'));
		  }
		  if (jq('#dffvbl').val() =='' ) {
			jq('#dffvbl').val("0");
		  } else if (jq('#dffvbl').val() !='') {  //BigDecimal
			jq("#dffvbl").val(jq("#dffvbl").val().replace(',', '.'));
		  }
		  if (jq('#dfbele').val() =='' ) {
			jq('#dfbele').val("0");
		  } else if (jq('#dfbele').val() !='') {  //BigDecimal
			jq("#dfbele").val(jq("#dfbele").val().replace(',', '.'));
		  }
		  //INTEGERs
		  if (jq('#dfdtu').val() =='' ) {
			jq('#dfdtu').val("0");
		  }
		  if (jq('#dftoll').val() =='' ) {
			jq('#dftoll').val("0");
		  }
		  if (jq('#dfknss').val() =='' ) {
			jq('#dfknss').val("0");
		  }
		  if (jq('#dfpnls').val() =='' ) {
			jq('#dfpnls').val("0");
		  }
		  if (jq('#dfpnat').val() =='' ) {
			jq('#dfpnat').val("0");
		  }
		  if (jq('#dfknsm').val() =='' ) {
			jq('#dfknsm').val("0");
		  }
		  if (jq('#dfpoul').val() =='' ) {
			jq('#dfpoul').val("0");
		  }
		  if (jq('#dfknsx').val() =='' ) {
			jq('#dfknsx').val("0");
		  }
		  if (jq('#dftran').val() =='' ) {
			jq('#dftran').val("0");
		  }
		  if (jq('#dfnteu').val() =='' ) {
			jq('#dfnteu').val("0");
		  }
		  if (jq('#dfntau').val() =='' ) {
			jq('#dfntau').val("0");
		  }
		  if (jq('#dfnt').val() =='' ) {
			jq('#dfnt').val("0");
		  }
		  
		  if (jq('#dfvktf').val() =='' ) {
			jq('#dfvktf').val("0");
		  }
		  if (jq('#dfntla').val() =='' ) {
			jq('#dfntla').val("0");
		  }
		  if (jq('#dffore').val() =='' ) {
			jq('#dffore').val("0");
		  }
		  if (jq('#dfrg').val() =='' ) {
			jq('#dfrg').val("0");
		  }
		  if (jq('#dfkta').val() =='' ) {
			jq('#dfkta').val("0");
		  }
		  if (jq('#dfktb').val() =='' ) {
			jq('#dfktb').val("0");
		  }
		  if (jq('#df6060').val() =='' ) {
			jq('#df6060').val("0");
		  }
		  if (jq('#df500a').val() =='' ) {
			jq('#df500a').val("0");
		  }
		  if (jq('#df500b').val() =='' ) {
			jq('#df500b').val("0");
		  }
		  
		  jq.blockUI({
				message : BLOCKUI_OVERLAY_MESSAGE_DEFAULT
		  });
	  });
		 
  });  
	  
	  