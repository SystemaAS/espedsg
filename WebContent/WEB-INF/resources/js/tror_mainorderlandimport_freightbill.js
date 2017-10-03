  //this variable is a global jQuery var instead of using "$" all the time. Very handy
  var jq = jQuery.noConflict();
  var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";

  
  //FULL TODO
  
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
	  
	  jq('#merkelappIdLink').click(function() {
	    	jq('#merkelappIdLink').attr('target','_blank');
	    	window.open('tror_mainorder_childwindow_generalcodes.do?action=doFind&ctype=tror_dfkdme_fb&kftype=MLAPKOD', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	  });
  });  