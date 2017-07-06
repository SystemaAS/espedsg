  //this variable is a global jQuery var instead of using "$" all the time. Very handy
  var jq = jQuery.noConflict();
  var counterIndex = 0;
  var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";
  
  function setBlockUI(element){
	  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
  }
  
  jq(function() {
	  jq("#opd").focus();
	  
	  //Overlay
	  jq( "#submit" ).click(function( event ) {
  		  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT });
  	  });
	  
	  //External references in model Dialog
	  	jq('#extRefIdLink').click(function() {
	    	jq('#extRefIdLink').attr('target','_blank');
	    	window.open('skatexport_childwindow_external_references.do?', "codeWin", "top=300px,left=750px,height=500px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#extRefIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#extRefIdLink').click();
			}
	    });
  });
  
  jq(function() {
	  jq("#datum").datepicker({ 
		  dateFormat: 'yymmdd'	  
	  });
	  jq("#datumt").datepicker({ 
		  dateFormat: 'yymmdd'	  
	  });
	  
	  jq("#sendAllDtm2").datetimepicker({ 
  		  dateFormat: 'yymmdd',
		  controlType: 'select',
		  timeFormat: 'HHmm' 
	  });
	  jq('#sendAllDtm2').change(function() {
	  		var val = jq("#sendAllDtm2").val();
	  		jq("#sendAllDtm2").val(val.replace(' ', ''));
	  	  });
	  
  });
  
  
  //-----------------------------------
  //START Model dialog "Kopiera Ärende
  //-----------------------------------
  //Initialize <div> here
  jq(function() { 
	  jq( ".clazz_dialog" ).each(function(){
		jq(this).dialog({
			autoOpen: false,
			modal: true
		});
	  });
  });
  //Present dialog box onClick (href in parent JSP)
  jq(function() {
	  jq(".copyLink").click(function() {
		  var id = this.id;
		  counterIndex = id.replace("copyLink","");
		  //setters (add more if needed)
		  jq('#dialog'+counterIndex).dialog( "option", "title", "Kopi Angivelse " + jq('#originalOpd'+counterIndex).val() );
		  
		  //deal with buttons for this modal window
		  jq('#dialog'+counterIndex).dialog({
			 buttons: [ 
	            {
				 id: "dialogSave"+counterIndex,	
				 text: "Fortsæt",
				 click: function(){
					 		jq('#copyForm'+counterIndex).submit();
				 		}
			 	 },
	 	 		{
			 	 id: "dialogCancel"+counterIndex,
			 	 text: "Annullér", 
				 click: function(){
					 		//back to initial state of form elements on modal dialog
					 		jq("#dialogSave"+counterIndex).button("option", "disabled", true);
					 		jq("#newAvd"+counterIndex).val("");
					 		jq("#newSign"+counterIndex).val("");
							jq( this ).dialog( "close" ); 
					 		  
				 		} 
	 	 		 } ] 
			  
		  });
		  //init values
		  jq("#dialogSave"+counterIndex).button("option", "disabled", true);
		  
		  //open now
		  jq('#dialog'+counterIndex).dialog('open');
		 
	  });
  });
  //Events for the drop downs (some kind of "implicit validation" since all drop downs are mandatory)
  jq(function() {
	  jq(".newAvd").change(function() {
		  if(jq("#dialog"+counterIndex).find('.newAvd').val()!='' && jq("#dialog"+counterIndex).find('.newSign').val()!=''){
			  jq("#dialogSave"+counterIndex).button("option", "disabled", false);
			  
		  }else{
			  jq("#dialogSave"+counterIndex).button("option", "disabled", true);
		  }
	  });
	  jq(".newSign").change(function() {
		  if(jq("#dialog"+counterIndex).find('.newAvd').val()!='' && jq("#dialog"+counterIndex).find('.newSign').val()!=''){
			  jq("#dialogSave"+counterIndex).button("option", "disabled", false);
			  
		  }else{
			  jq("#dialogSave"+counterIndex).button("option", "disabled", true);
		  }
	  });
  });
  //---------------------------------
  //END Model dialog "Kopiera Ärende
  //---------------------------------
	
  
//-----------------------------------------------------------------------------
  //START Model dialog "Kopiera Ärende from template (norsk import/transport order)
  //---------------------------------------------------------------------------
  //Initialize <div> here
  jq(function() { 
	  jq("#dialogCopyFromTransportUppdrag").dialog({
		  autoOpen: false,
		  maxWidth:500,
          maxHeight: 400,
          width: 500,
          height: 400,
		  modal: true
	  });
  });
  //Present dialog box onClick (href in parent JSP)
  jq(function() {
	  jq("#copyFromTransportUppdragLink").click(function() {
		  //setters (add more if needed)
		  jq('#dialogCopyFromTransportUppdrag').dialog( "option", "title", "Hent opdrag fra SYSPED" );
		  
		  //deal with buttons for this modal window
		  jq('#dialogCopyFromTransportUppdrag').dialog({
			 buttons: [ 
	            {
				 id: "dialogSaveTU",	
				 text: "Fortsæt",
				 click: function(){
					 		jq('#copyFromTransportUppdragForm').submit();
				 		}
			 	 },
	 	 		{
			 	 id: "dialogCancelTU",
			 	 text: "Annullér", 
				 click: function(){
					 		//back to initial state of form elements on modal dialog
					 		jq("#dialogSaveSU").button("option", "disabled", true);
					 		jq("#selectedAvd").val("");
					 		jq("#selectedOpd").val("");
					 		jq("#selectedExtRefNr").val("");
							jq( this ).dialog( "close" ); 
				 		} 
	 	 		 } ] 
		  });
		  //init values
		  jq("#dialogSaveSU").button("option", "disabled", false);
		  //open now
		  jq('#dialogCopyFromTransportUppdrag').dialog('open');
	  });
  });
//Events for the drop downs (some kind of "implicit validation" since all drop downs are mandatory)
  jq(function() {
	  /*deprecated
	  jq("#selectedAvd").change(function() {
		  if(jq('#selectedAvd').val()!=''){
			  if(jq('#selectedOpd').val()!='' || jq('#selectedExtRefNr').val()!=''){
				  jq("#dialogSaveTU").button("option", "disabled", false);
			  }else{
				  jq("#dialogSaveTU").button("option", "disabled", true);
			  }
		  }else{
			  applyRuleOnDialogTranspUppdragNullAvd();
		  }
	  });
	  jq("#selectedOpd").blur(function() {
		  if(jq('#selectedAvd').val()!=''){
			  if(jq("#selectedOpd").val()!=''){
				  jq('#selectedExtRefNr').val("");
				  jq("#dialogSaveTU").button("option", "disabled", false);
			  }else{
				  if(jq("#selectedExtRefNr").val()==''){
					  jq("#dialogSaveTU").button("option", "disabled", true);
				  }
			  }
		  }else{
			  applyRuleOnDialogTranspUppdragNullAvd();
		  }
	  });
	  jq("#selectedExtRefNr").blur(function() {
		  if(jq('#selectedAvd').val()!=''){
			  if(jq("#selectedExtRefNr").val()!=''){
				  jq('#selectedOpd').val("");
				  jq("#dialogSaveTU").button("option", "disabled", false);
			  }else{
				  if(jq("#selectedOpd").val()==''){
					  jq("#dialogSaveTU").button("option", "disabled", true);
				  }
			  }
		  }else{
			  applyRuleOnDialogTranspUppdragNullAvd();
		  }
	  });
	  */
  });
  function applyRuleOnDialogTranspUppdragNullAvd(){
	  if(jq('#selectedOpd').val()=='' && jq('#selectedExtRefNr').val()==''){
		  jq("#dialogSaveTU").button("option", "disabled", false);
	  }else{
		  jq("#dialogSaveTU").button("option", "disabled", true);
	  }
  }
  function applyRuleOnDialogTranspUppdragExtRef(){
	  if(jq('#selectedExtRefNr').val()!=''){
		  jq("#dialogSaveTU").button("option", "disabled", false);
	  }else{
		  jq("#dialogSaveTU").button("option", "disabled", true);
	  }
  }
  
  //-----------------------------------------------
  //END Model dialog "Kopiera Ärende from template
  //-----------------------------------------------

//Initialize <div> here
  jq(function() { 
	  jq("#dialogSendAll").dialog({
		  autoOpen: false,
		  maxWidth:300,
          maxHeight: 350,
          width: 300,
          height: 350,
		  modal: true
	  });
  });
  
//Present dialog box onClick (href in parent JSP)
  jq(function() {
	  jq("#buttonSendAll").click(function() {
		  //setters (add more if needed)
		  jq('#dialogSendAll').dialog( "option", "title", "Sæt Faktisk ekspeditionstid" );
		  
		  //deal with buttons for this modal window
		  jq('#dialogSendAll').dialog({
			 buttons: [ 
	            {
				 id: "buttonSaveOk",	
				 text: "Fortstæt",
				 click: function(){
	            			sendAllWithStatus11();
				 		}
			 	 },
	 	 		{
			 	 id: "buttonCancel",
			 	 text: "Annullér", 
				 click: function(){
					 		//back to initial state of form elements on modal dialog
					 		//jq("#buttonSaveOk").button("option", "disabled", true);
					 		//jq("#selectedAvd").val("");
					 		//jq("#selectedOpd").val("");
					 		//jq("#selectedExtRefNr").val("");
							jq( this ).dialog( "close" ); 
				 		} 
	 	 		 } ] 
		  });
		  //init values
		  jq("#buttonSaveOk").button("option", "disabled", false);
		  //open now
		  jq('#dialogSendAll').dialog('open');
	  });
  });
  
  //Send multiple selections to the controller here
  function sendAllWithStatus11() {
	  //init place holder
		var requestParams = "";
		var RECORD_SEPARATOR = ';';
		jq( ".clazzSendAware" ).each(function( i ) {
			  var id = this.id;
			  var record = id.split('_');
			  var syav = record[0].replace("syav", "");
			  var syop = record[1].replace("syop", "");
			  var counter = i + 1;
			  //
			  if(jq('#syav' + syav + '_' + 'syop' + syop ).prop('checked')){
				  var str = "&avd=" + syav + "&opd=" + syop + "&dtm2=" + jq('#sendAllDtm2').val();
				//start
				  requestParams += str + RECORD_SEPARATOR;
			  }  
		});	
		//DEBUG --> alert(requestParams);
		if(requestParams != '' && jq('#sendAllDtm2').val() != ''){
			//DEBUG-->alert("A");
			jq.ajax({
				type: 'GET',
				url: 'sendAllSkatExportStatus11_SkatExport.do',
				data: { applicationUser : jq('#applicationUser').val(),
						requestParams : requestParams },
				dataType: 'json',
				cache: false,
				contentType: 'application/json',
	  	  	  	success: function(data) {
					//Update has been done successfully
					jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT });
		  			window.location.reload();
		  			window.close();		
		  	  		/*
		  			var len = data.length;
		  	  		for ( var i = 0; i < len; i++) {
		  	  			//Update has been done successfully
		  	  			
		  	  		}*/
				},
		  	  	error: function() {
	  	  	    	alert('Error loading ...');
	  	  		}
			});
			//reload
			//jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT });
  			//window.location.reload();
  			//window.close();
		}
		
  	}

  
  
  
  