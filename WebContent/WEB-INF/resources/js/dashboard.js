	//===========================================
	//General functions for Dashboard - AJAX
	//===========================================
	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict();
    var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";
    
    function setBlockUI(element){
  	  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
    }
  	
  	
	//call this with onClick() from an html-element
	function doPostMultiUser(element){
		var id = element.id;
		//already in the jsp: --> jq('#formMU_'+ id).append('<input type="hidden" name="user" value="oscar">');
		jq('#formMU_'+ id).append('<input type="hidden" name="password" value="mltid">');
		//
		jq('#formMU_'+ id).submit();
		
	/*	
	 * //call this with onClick() in <a>link (in case a user pass. is sent to another WAR file)
	   jq.post("logonDashboard.do", { user: "jovo", password: "mltid" },
		   function(data) {
	         window.location = "logonDashboard.do";
	       });	   

	   
	   not working --> jq.redirect('logonDashboard.do', {'user': 'oscar', 'pwd': 'mltid'});
	   */
	}
  
	  jq(function() { 
		  jq("#dialogRunKundedatakontroll").dialog({
			  autoOpen: false,
			  maxWidth:400,
	          maxHeight: 220,
	          width: 400,
	          height: 220,
			  modal: true
		  });
	  });
	
	  jq(function() { 
		  jq("#dialogRunReportDashboard").dialog({
			  autoOpen: false,
			  maxWidth:400,
	          maxHeight: 220,
	          width: 400,
	          height: 220,
			  modal: true
		  });
	  });

	  jq(function() {
		  jq("#dialogRunKundedatakontrollLink").click(function() {
			  //setters (add more if needed)
			  jq('#dialogRunKundedatakontroll').dialog( "option", "title", "Kør Kundedatakontroll" );
			  //deal with buttons for this modal window
			  jq('#dialogRunKundedatakontroll').dialog({
				 buttons: [ 
		            {
					 id: "dialogSaveTU",	
					 text: "Fortsett",
					 click: function(){
						 		jq('#runKundedatakontrollForm').submit();
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
			  jq('#dialogRunKundedatakontroll').dialog('open');
		  });
	  });

	  jq(function() {
		  jq("#dialogRunReportDashboardLink").click(function() {
			  //setters (add more if needed)
			  jq('#dialogRunReportDashboard').dialog( "option", "title", "Vis Rapporter" );
			  //deal with buttons for this modal window
			  jq('#dialogRunReportDashboard').dialog({
				 buttons: [ 
		            {
					 id: "dialogSaveDB",	
					 text: "Fortsett",
					 click: function(){
						 		jq('#runReportDashboardForm').submit();
						 		jq( this ).dialog( "close" );
						 		jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
					 		}
				 	 },
		 	 		{
				 	 id: "dialogCancelDB",
				 	 text: "Avbryt", 
					 click: function(){
						 		jq( this ).dialog( "close" );
					 		} 
		 	 		 } ] 
			  });
			  jq('#dialogRunReportDashboard').dialog('open');
		  });
	  });
	  
	  