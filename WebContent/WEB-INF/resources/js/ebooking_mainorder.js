  //this variable is a global jQuery var instead of using "$" all the time. Very handy
  var jq = jQuery.noConflict();
  var counterIndex = 0;
  var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";

  
  function setBlockUI(element){
	  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
  }
  
  jq(function() {
	  jq("#dateTODO").datepicker({ 
		  dateFormat: 'yymmdd'
		  //,defaultDate: "-1m"	  
	  });
	  
	  
  });
  
//==============================================================================
  //START - Postal codes On-Blur (required to be an exact number and nothing else)
  //==============================================================================
  jq(function() {
	  	/*
	  	jq('#hesdf').focus(function() {
	  	  if(jq('#hesdf').val()=='' && jq('#heads3').val()!=''){
	  		  var sellersPostalCodeRaw = jq('#heads3').val();
	  		  var postalCode = sellersPostalCodeRaw.substr(0,sellersPostalCodeRaw.indexOf(' '));
	  		  jq('#hesdf').val(postalCode);
	  	  }
	  	});
	    jq('#hesdf').blur(function() {
	    	var id = jq('#hesdf').val();
	    	if(id!=null && id!=""){
	    		var countryCode = jq('#helka').val();
	    		getCity(CITY_OWNwppns1,id,countryCode);
	    	}else{
	    		jq('#OWNwppns1').val("");
	    	}
		});
		*/
	    jq('#hesdfIdLink').click(function() {
	    	jq('#hesdfIdLink').attr('target','_blank');
	    	window.open('ebooking_childwindow_postalcodes.do?action=doInit&direction=fra&st2lk=' + jq('#helka').val() + '&st2kod=' + jq('#hesdf').val() + '&caller=hesdf', "postalcodeWin", "top=300px,left=450px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#hesdfIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#hesdfIdLink').click();
			}
	    });
	    //hesdt
	    jq('#hesdtIdLink').click(function() {
	    	jq('#hesdtIdLink').attr('target','_blank');
	    	window.open('ebooking_childwindow_postalcodes.do?action=doInit&direction=til&st2lk=' + jq('#hetri').val() + '&st2kod=' + jq('#hesdt').val() + '&caller=hesdt', "postalcodeWin", "top=300px,left=450px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#hesdtIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#hesdtIdLink').click();
			}
	    });
	    
  });
  
  
  
  

  
  