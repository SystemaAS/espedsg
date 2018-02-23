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
	  jq('#trsta4').focus(function() {
	    	if(jq('#trsta4').val()!=''){
	    		refreshCustomValidity(jq('#trsta4')[0]);
	  		}
	  });
	  
	  jq('#hekns').focus(function() {
	    	if(jq('#hekns').val()!=''){
	    		refreshCustomValidity(jq('#hekns')[0]);
	  		}
	  });
	  
	  jq('#hesdf').focus(function() {
	    	if(jq('#hesdf').val()!=''){
	    		refreshCustomValidity(jq('#hesdf')[0]);
	  		}
	  });
	  jq('#hesdt').focus(function() {
	    	if(jq('#hesdt').val()!=''){
	    		refreshCustomValidity(jq('#hesdt')[0]);
	  		}
	  });
	  jq('#hefr').focus(function() {
	    	if(jq('#hefr').val()!=''){
	    		refreshCustomValidity(jq('#hefr')[0]);
	  		}
	  });
	  jq('#domoms').focus(function() {
	    	if(jq('#domoms').val()!=''){
	    		refreshCustomValidity(jq('#domoms')[0]);
	  		}
	  });
	 
	  //backButton
	  jq('#backToFlyfraktbrevGateButton').click(function() {
		  jq.blockUI({ message : BLOCKUI_OVERLAY_MESSAGE_DEFAULT });
		  window.location = "tror_mainorderfly_airfreightbill_imp_gate.do?imavd=" + jq('#imavd').val() + "&sign=" + jq('#sign').val() + "&imopd=" + jq('#imopd').val() ;
	  });
	  
	  
  });
	  
	  