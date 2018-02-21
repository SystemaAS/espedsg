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
	  
	  //backButton
	  jq('#backToFlyfraktbrevGateButton').click(function() {
		  jq.blockUI({ message : BLOCKUI_OVERLAY_MESSAGE_DEFAULT });
		  window.location = "tror_mainorderfly_airfreightbill_gate.do?imavd=" + jq('#imavd').val() + "&sign=" + jq('#sign').val() + "&imopd=" + jq('#imopd').val() ;
	  });
	  
	  //Buyer
	  /*
	  jq('#trorBuyerIdLink').click(function() {
		  jq('#trorBuyerIdLink').attr('target','_blank');
		  window.open('mainmaintenance_childwindow_customer.do?action=doFind&ctype=tror_by_fb&knr=' + jq('#dfknsm').val(),"customerWin","top=300px,left=150px,height=800px,width=900px,scrollbars=no,status=no,location=no");
	  });
	  jq('#trorBuyerIdLink').keypress(function(e){ //extra feature for the end user
		  if(e.which == 13) {
			  jq('#trorBuyerIdLink').click();
		  }
	  });
	  */
  });
	  
	  