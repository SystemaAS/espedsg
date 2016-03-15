  //this variable is a global jQuery var instead of using "$" all the time. Very handy
  var jq = jQuery.noConflict();
  var counterIndex = 0;
  var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";

  
  function setBlockUI(element){
	  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
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
          width: 400,
          height: 220,
		  modal: true
	  });
  });
  //Present dialog box onClick (href in parent JSP)
  jq(function() {
	  jq("#createNewOrderTabIdLink").click(function() {
		  //setters (add more if needed)
		  jq('#dialogCreateNewOrder').dialog( "option", "title", "Lag ny Ordre" );
		  
		  //deal with buttons for this modal window
		  jq('#dialogCreateNewOrder').dialog({
			 buttons: [ 
	            {
				 id: "dialogSaveTU",	
				 text: "Fortsett",
				 click: function(){
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
  //END Create new order - Dialog
  //-----------------------------
  
  
  
  
  //-------------------
  //Datatables jquery
  //-------------------
  jq(document).ready(function() {
    //init table (no ajax, no columns since the payload is already there by means of HTML produced on the back-end)
	jq('#openOrders').dataTable( {
	  "jQueryUI": false,
	  "dom": '<"top"fli>rt<"bottom"p><"clear">',
	  "scrollY":        	"700px",
	  "scrollCollapse":  	true,
	  //"autoWidth": false, //for optimization purposes when initializing the table
	  "lengthMenu": [ 50, 75, 100]
	  
	} );
	
    //event on input field for search
    jq('input.openOrders_filter').on( 'keyup click', function () {
    		filtersInit();
    });
    
  });
  
  
  

  
  