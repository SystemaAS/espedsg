  //this variable is a global jQuery var instead of using "$" all the time. Very handy
  var jq = jQuery.noConflict();
  var counterIndex = 0;
  var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";

  
  function setBlockUI(element){
	  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
  }
  
  jq(function() {
	  jq("#selectedType").change(function() {
		  showDelsystemIcon	(jq("#selectedType").val());		 
	  });
	  jq("#selectedType").focus(function() {
		  showDelsystemIcon	(jq("#selectedType").val());		 
	  });
  });
  function showDelsystemIcon(value){
	  
	  if ( value == "A" ){
			 jq("#imagePreview").empty();
	    	 jq("#imagePreview").append("<img style=\"vertical-align:middle;\" src=\"resources/images/lorry_green.png\" width=\"24px\" height=\"24px\" " + "\" />");
	     }else if ( value == "B" ){
			 jq("#imagePreview").empty();
	    	 jq("#imagePreview").append("<img style=\"vertical-align:middle;\" src=\"resources/images/lorry_blue.png\" width=\"24px\" height=\"24px\" " + "\" />");
	     }else if ( value == "C" ){
			 jq("#imagePreview").empty();
	    	 jq("#imagePreview").append("<img style=\"vertical-align:middle;\" src=\"resources/images/airplaneYellow.png\" width=\"24px\" height=\"24px\" " + "\" />");
	     }else if ( value == "D" ){
			 jq("#imagePreview").empty();
	    	 jq("#imagePreview").append("<img style=\"vertical-align:middle;\" src=\"resources/images/airplaneBlue.png\" width=\"24px\" height=\"24px\" " + "\" />");
	     
	     }else{
	    	 jq("#imagePreview").empty();
	    	 jq("#imagePreview").append("displays missing image here...");
	     }
	  
  }
  
  function printDocument(element){
	  var id = element.id;
	  var record = id.split('_');
	  var prefix = record[0];
	  var unikId = record[1]; 
	  //alert(unikId);
	  
	  //jq(id).attr('target','_blank');  //not needed in order to avoid strange behavior in non-Chrome browsers... (example: Firefox pop-up blank tab in addition of the PDF tab ??)
	  var userIP = jq("#userHttpJQueryDocRoot").val().replace("http://", "");
	  
	  if(prefix.indexOf("fraktbrev")>=0){
		  var link = jq("#userHttpJQueryDocRoot").val() + '/sycgip/esop11fb.pgm?user=' + jq("#applicationUser").val() + '&curtur=' + unikId + '&UserIP=' + userIP;
		  //alert(link);
		  window.open(link, "printDocWinFb", "top=300px,left=50px,height=800px,width=900px,scrollbars=no,status=no,location=no");
	  
	  }else if(prefix.indexOf("cmr")>=0){
		  var link = jq("#userHttpJQueryDocRoot").val() + '/sycgip/esop11cm.pgm?user=' + jq("#applicationUser").val() + '&curtur=' + unikId + '&UserIP=' + userIP;
		  window.open(link, "printDocWinCm", "top=300px,left=50px,height=800px,width=900px,scrollbars=no,status=no,location=no");
	  
	  }else if(prefix.indexOf("merkPdf")>=0){
		  var link = jq("#userHttpJQueryDocRoot").val() + '/sycgip/ss115.pgm?user=' + jq("#applicationUser").val() + '&curtur=' + unikId + '&UserIP=' + userIP;
		  window.open(link + '&lay=HZ&copyprt=J&labeltyp=L', "printDocWinPf", "top=300px,left=50px,height=800px,width=900px,scrollbars=no,status=no,location=no");
	  
	  }else if(prefix.indexOf("merkZpl")>=0){
		  var link = jq("#userHttpJQueryDocRoot").val() + '/sycgip/ss115.pgm?user=' + jq("#applicationUser").val() + '&curtur=' + unikId + '&UserIP=' + userIP;
		  window.open(link + '&lay=HZ&copyprt=J&labeltyp=Z', "printDocWinMz", "top=300px,left=50px,height=800px,width=900px,scrollbars=no,status=no,location=no");
	  }
	  
	  //refresh parent window
	  setTimeout(refreshOrderListWindow, 4000);
  }
  
  function refreshOrderListWindow(){
	  window.location.reload();
  }
  
  jq(function() {
	  jq("#date").datepicker({ 
		  dateFormat: 'yymmdd'
		  //,defaultDate: "-1m"	  
	  });
	  jq("#fromDateF").datepicker({ 
		  dateFormat: 'yymmdd'  
	  });
	  jq("#fromDateT").datepicker({ 
		  dateFormat: 'yymmdd'  
	  });
	  //
	  jq("#opd").focus();
  });
  
  //----------------------------------------
  //START Model dialog "Create new order"
  //--------------------------------------
  //Initialize <div> here
  jq(function() { 
	  jq("#dialogCreateNewOrder").dialog({
		  autoOpen: false,
		  maxWidth:400,
          maxHeight: 220,
          width: 300,
          height: 250,
		  modal: true
	  });
  });
  
  //Present dialog box onClick (href in parent JSP)
  jq(function() {
	  jq("#createNewOrderTabIdLink").click(function() {
		  
		  //setters (add more if needed)
		  jq('#dialogCreateNewOrder').dialog( "option", "title", "Lag ny Oppdrag" );
		  
		  //deal with buttons for this modal window
		  jq('#dialogCreateNewOrder').dialog({
			 buttons: [ 
	            {
				 id: "dialogSaveTU",	
				 text: "Fortsett",
				 click: function(){
					 		jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
			 				jq('#createNewOrderForm').submit();
				 		}
			 	 },
	 	 		{
			 	 id: "dialogCancelTU",
			 	 text: "Avbryt", 
				 click: function(){
					 		//back to initial state of form elements on modal dialog
					 		//jq("#dialogSaveSU").button("option", "disabled", true);
					 		
					 		jq( this ).dialog( "close" );
					 		jq("#opd").focus();
				 		} 
	 	 		 } ] 
		  });
		  //init values
		  //jq("#dialogSaveTU").button("option", "disabled", false);
		  //open now
		  jq('#dialogCreateNewOrder').dialog('open');
	  });
  });
  //-----------------------------
  //END Create new order - Dialog
  //-----------------------------

  //---------------------------------------
  //DELETE Order
  //This is done in order to present a jquery
  //Alert modal pop-up
  //----------------------------------------
  function doPermanentlyDeleteOrder(element){
	  //start
	  var record = element.id.split('@');
	  var avd = record[0];
	  var opd = record[1];
	  avd= avd.replace("avd_","");
	  opd= opd.replace("opd_","");
	  //alert(avd + " " + opd);
	  	//Start dialog
	  	jq('<div></div>').dialog({
	        modal: true,
	        title: "Dialog - Slett Oppdrag " + opd,
	        buttons: {
		        Fortsett: function() {
	        		jq( this ).dialog( "close" );
		            //do delete
		            jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
		            window.location = "tror_mainorderlist_permanently_delete_order.do?action=doDelete" + "&avd=" + avd + "&orderNr=" + opd;
		        },
		        Avbryt: function() {
		            jq( this ).dialog( "close" );
		        }
	        },
	        open: function() {
		  		  var markup = "Er du sikker på at du vil slette denne?";
		          jq(this).html(markup);
		          //make Cancel the default button
		          jq(this).siblings('.ui-dialog-buttonpane').find('button:eq(1)').focus();
		     }
		});  //end dialog
  }
  
  //------------------------------------
  //START Model dialog "Update status"
  //------------------------------------
  //Initialize <div> here
  jq(function() { 
	  jq( ".clazz_dialog" ).each(function(){
		jq(this).dialog({
			autoOpen: false,
			maxWidth:500,
	        maxHeight: 400,
	        width: 280,
	        height: 220,
			modal: true
		});
	  });
  });
  //Present dialog box onClick (href in parent JSP)
  jq(function() {
	  jq(".updateStatus").click(function() {
		  var id = this.id;
		  counterIndex = id.replace("updateStatus","");
		  //setters (add more if needed)
		  jq('#dialogUpdateStatus'+counterIndex).dialog( "option", "title", "Oppdatere Status "  + jq('#currentOpd'+counterIndex).val() );
		  
		  //deal with buttons for this modal window
		  jq('#dialogUpdateStatus'+counterIndex).dialog({
			 buttons: [ 
	            {
				 id: "dialogSave"+counterIndex,	
				 text: "Ok",
				 click: function(){
					 		jq('#updateStatusForm'+counterIndex).submit();
				 		}
			 	 },
	 	 		{
			 	 id: "dialogCancel"+counterIndex,
			 	 text: "Avbryt", 
				 click: function(){
					 		//back to initial state of form elements on modal dialog
					 		//jq("#dialogSave"+counterIndex).button("option", "disabled", true);
					 		jq("#selectedStatus"+counterIndex).val("");
					 		jq( this ).dialog( "close" ); 
					 		  
				 		} 
	 	 		 } ] 
			  
		  });
		  //init values
		  //jq("#dialogSave"+counterIndex).button("option", "disabled", true);
		  
		  //open now
		  jq('#dialogUpdateStatus'+counterIndex).dialog('open');
		 
	  });
  });
  //-------------------
  //END Update status
  //-------------------
  
  
  
  //-------------------
  //Datatables jquery
  //-------------------
  jq(document).ready(function() {
	 var lang = jq('#language').val(); 
    //init table (no ajax, no columns since the payload is already there by means of HTML produced on the back-end)
	jq('#openOrders').dataTable( {
	  "jQueryUI": false,
	  "dom": '<"top"fli>rt<"bottom"p><"clear">',
	  "scrollY":        	"700px",
	  "scrollCollapse":  	true,
	  //"columnDefs": [{ className: "dt-head-left", "targets": [ 2 ] }],
	  "order": [[ 5, "desc" ]], //date
	  //"autoWidth": false, //for optimization purposes when initializing the table
	  "lengthMenu": [ 50, 75, 100],
	  "language": { "url": getLanguage(lang) }
	} );
	
    //event on input field for search
    jq('input.openOrders_filter').on( 'keyup click', function () {
    		filtersInit();
    });
    
  });
  
  
  

  
  