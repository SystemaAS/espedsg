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
	  
	  //onwardButton
	  jq('#onwardButton').click(function() {
		  jq.blockUI({ message : BLOCKUI_OVERLAY_MESSAGE_DEFAULT });
		  window.location = "tror_mainorderfly_airfreightbill_edit.do?dflop=" + jq('#imlop').val() + "&dfavd=" + jq('#imavd').val() + "&sign=" + jq('#sign').val() + "&dfopd=" + jq('#imopd').val() ;
	  });
	  
	  //START - CHILDWINDOWS
	  //Produktkode
	  jq('#prodKodeIdLink').click(function() {
		  jq('#prodKodeIdLink').attr('target','_blank');
		  window.open('tror_mainorderflyimport_childwindow_airproducts.do?action=doFind',"produktKodeWin","top=300px,left=400px,height=600px,width=900px,scrollbars=no,status=no,location=no");
	  });
	  jq('#prodKodeIdLink').keypress(function(e){ //extra feature for the end user
		  if(e.which == 13) {
			  jq('#prodKodeIdLink').click();
		  }
	  });
	  //Flyselskap
	  jq('#flyselskapIdLink').click(function() {
		  jq('#flyselskapIdLink').attr('target','_blank');
		  window.open('tror_mainorderflyimport_childwindow_airlines.do?action=doFind',"airlineWin","top=300px,left=400px,height=700px,width=900px,scrollbars=no,status=no,location=no");
	  });
	  jq('#flyselskapIdLink').keypress(function(e){ //extra feature for the end user
		  if(e.which == 13) {
			  jq('#flyselskapIdLink').click();
		  }
	  });
	 //Flyplass
	  jq('#flyplassFromIdLink').click(function() {
		  jq('#flyplassFromIdLink').attr('target','_blank');
		  window.open('tror_mainorderflyimport_childwindow_cities.do?action=doFind&ctype=fr&st2kod=' + jq('#hesdf').val(), "cityWin","top=300px,left=400px,height=700px,width=900px,scrollbars=no,status=no,location=no");
	  });
	  jq('#flyplassFromIdLink').keypress(function(e){ //extra feature for the end user
		  if(e.which == 13) {
			  jq('#flyplassFromIdLink').click();
		  }
	  });
	  jq('#flyplassToIdLink').click(function() {
		  jq('#flyplassToIdLink').attr('target','_blank');
		  window.open('tror_mainorderflyimport_childwindow_cities.do?action=doFind&st2kod=' + jq('#hesdt').val(), "cityWin","top=300px,left=400px,height=700px,width=900px,scrollbars=no,status=no,location=no");
	  });
	  jq('#flyplassToIdLink').keypress(function(e){ //extra feature for the end user
		  if(e.which == 13) {
			  jq('#flyplassToIdLink').click();
		  }
	  });
	  //incoterms
	  
	  jq('#incotermsIdLink').click(function() {
		  jq('#incotermsIdLink').attr('target','_blank');
		  window.open('tror_mainorderflyimport_childwindow_incoterms.do?action=doFind', "incotermsWin","top=300px,left=400px,height=700px,width=900px,scrollbars=no,status=no,location=no");
	  });
	  jq('#incotermsIdLink').keypress(function(e){ //extra feature for the end user
		  if(e.which == 13) {
			  jq('#incotermsIdLink').click();
		  }
	  });
	  
	  //Avsender (customer search)
	  jq('#trorAvsenderImportIdLink').click(function() {
		  jq('#trorAvsenderImportIdLink').attr('target','_blank');
		  window.open('mainmaintenance_childwindow_customer.do?action=doFind&ctype=tror_flyiavs&knr=' + jq('#hekns').val(),"customerWin","top=300px,left=150px,height=800px,width=900px,scrollbars=no,status=no,location=no");
	  });
	  jq('#trorAvsenderImportIdLink').keypress(function(e){ //extra feature for the end user
		  if(e.which == 13) {
			  jq('#trorAvsenderImportIdLink').click();
		  }
	  });
	  //END - CHILDWINDOWS
	  
	  
  });
	  
	  