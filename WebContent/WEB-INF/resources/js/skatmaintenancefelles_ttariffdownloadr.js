		//===========================================
	//General functions for Dashboard - AJAX
	//===========================================
	//this variable is a global jQuery var instead of using "$" all the time. Very handy
	var jq = jQuery.noConflict();
	var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";
	
	function setBlockUI(element){
	  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
	}

  //----------------------------------------
  //START Model dialog "Create new order"
  //--------------------------------------
  //Initialize <div> here
  jq(function() { 
	  jq("#dialogDownloadTariff").dialog({
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
	  jq("#downloadButton").click(function() {
		  //setters (add more if needed)
		  jq('#dialogDownloadTariff').dialog( "option", "title", "Download i fast filformat" );
		  //deal with buttons for this modal window
		  jq('#dialogDownloadTariff').dialog({
			 buttons: [ 
	            {
				 id: "dialogSaveTU",	
				 text: "Fortsett",
				 click: function(){
					 		jq('#downloadForm').submit();
					 		jq( this ).dialog( "close" );
					 		jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
				 		}
			 	 },
	 	 		{
			 	 id: "dialogCancelTU",
			 	 text: "Avbryt", 
				 click: function(){
					 		jq( this ).dialog( "close" );
				 		} 
	 	 		 } ] 
		  });
		  jq('#dialogDownloadTariff').dialog('open');
	  });
  });
  //-----------------------------
  //END Create new order - Dialog
  //----------------------------- 	