  //this variable is a global jQuery var instead of using "$" all the time. Very handy
  var jq = jQuery.noConflict();
  
  //fire Click event on hidden Submit button 
  jq(function() {
	  //init avdList
	  if(jq("#avd").val()!=""){
		  jq("#avdList").prop("disabled",true);
	  }else{
		  jq("#avdList").prop("disabled",false);
	  }
	  
	  //change events
	  //--------------------
	  //Automate the Submit
	  //--------------------
	  jq("#avd").change(function() {
		  if(jq("#avd").val()!=""){
			  jq("#avdList").prop("disabled",true);
		  }else{
			  jq("#avdList").prop("disabled",false);
		  }
		  jq("#submit").trigger("click");
	  });
	  
	  //======================================
	  //Extra info bubble with related events
	  jq("#avdExtraInfo").change(function() {
		  if(jq("#avdExtraInfo").val()!="" && jq("#avdExtraInfo").val()!=null){
			  var key = jq('#avdExtraInfo').val();
			  var separator = key.indexOf(" ");
			  if(separator>0){
				  //alert(key.substring(0,separator));
				  jq('#avd').val(key.substring(0,separator));
			  }
		  }
	  });
	  jq("#avdInformationButtonClose").click(function() {
		  if(jq("#avdExtraInfo").val()!="" && jq("#avdExtraInfo").val()!=null){
			  jq('#avd').change(); //trigger it as if the end-user had done it...
		  }
	  });
	  //End for extra infor bubble
	  //========================================

	  
	  jq("#tariffor").change(function() {
		  jq("#submit").trigger("click");
	  });
	  
	  jq("#sign").change(function() {
		  jq("#submit").trigger("click");
	  });

	  jq("#antHfaktFlag").change(function() {
		  jq("#submit").trigger("click");
	  });

	  jq("#statusFlag").change(function() {
		  jq("#submit").trigger("click");
	  });
	  
	  jq("#avdList").change(function() {
		  jq("#submit").trigger("click");
	  });
	  
	  jq("#tollagerkod").change(function() {
		  jq("#submit").trigger("click");
	  });
	  
	  jq("#tollagerdelkod").change(function() {
		  jq("#submit").trigger("click");
	  });
	  //END automate submit

  });
  
  //----------------------------------------
  //CHART TICKER or REFRESH implementation
  //----------------------------------------
  jq(function() {
	  //init when document is ready (implicit load)
	  var intervalHandle = null;
	  var DEBUGG_PREFIX = "[INFO]";
	    
	  //console.log("BB");
	  //--------------------------------------------------------------------------------------------------------
	  //Refresh (ticker) mechanism automation (upon page reload AFTER user interaction (selection on drop-down)
	  //--------------------------------------------------------------------------------------------------------
	  //This if-sats is executed each time the page reloads (after a refresh)
	  //The step is required in order to catch a global event, decoupled from the drop-down "change" event, which is also
	  //required for the ON/OFF switch for the whole refresh mechanism.
	  var initValue = jq("#chartTickerInterval").val();
	  if(initValue!='-99'){
		  var message = " firing on <load> "  + DEBUGG_PREFIX +  new Date();
		  console.log(initValue + message);//for debug on Firefox console
		  //only valid values
		  var intervalInMilliSeconds = parseInt(initValue);
		  setIntervalOnRefresh(intervalInMilliSeconds);
		  
	  }else{
		  console.log(" stop <load> "  + DEBUGG_PREFIX +  new Date());
		  clearInterval(intervalHandle);
	  }
	  
	  //--------------------------------------------------------------
	  //Refresh (ticker) mechanism start-point (upon user interaction)
	  //--------------------------------------------------------------
	  jq("#chartTickerInterval").change(function() {
		  var message = " firing on <change> "   + DEBUGG_PREFIX +  new Date();
		  var value = jq("#chartTickerInterval").val();
		  console.log(value + message);//for debug on console
		  
		  //only valid values
		  if(value!='-99'){
			  var intervalInMilliSeconds = parseInt(value);
			  setIntervalOnRefresh(intervalInMilliSeconds);
			  
		  }else{
			  console.log("stop "   + DEBUGG_PREFIX +  new Date());
			  clearInterval(intervalHandle);
			  jq("#deepSubmit").trigger("click");
		  }
	  	  
	  });
	  
	  //CORE functionality
	  //user defined function
	  
	  function setIntervalOnRefresh(intervalInMilliSeconds) {
		  intervalHandle = setInterval(function() {
			  console.log("Firing user defined: setIntervalOnRefresh...");
			  jq("#deepSubmit").trigger("click");
	  	  }, intervalInMilliSeconds); //Refreshes every x-milliseconds
	  	  //Turn off caching
	  	  jq.ajaxSetup({ cache: false });
	  };
	  
			
  });
  //------------------
  //END CHART TICKER
  //------------------
  
  