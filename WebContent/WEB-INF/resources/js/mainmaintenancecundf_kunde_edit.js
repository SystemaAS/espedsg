  //this variable is a global jQuery var instead of using "$" all the time. Very handy
  var jq = jQuery.noConflict();
  var counterIndex = 0;
  var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";
  
  function setBlockUI(element){
	  
	  //jq("#dialog").dialog("open");  
	  //Funkar inte att anropas rått. Behöver <div> alt .click
	  
	  if(jq("#dirty").val() =="isDirty") {

		  alert("Du har ändra, utan att spara. Vill du gå vidare.....");

	  }

	  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
  }
  
  
	jq(function() { 
	
		jq("#dialog").dialog({
			modal: true,
			resizable: false,
			buttons: {
				"Yeah!": function() {
					$(this).dialog("close");
				},
				"Sure, Why Not": function() {
					$(this).dialog("close");
				}
			}
		});	
		
		
		jq("input[type='text']").change(function() {
	    	jq('#dirty').val("isDirty"); 
	    	
	    });	
		
		
		jq('#knavn').change(function() {
	    	jq('#dirty').val("isDirty"); 
	    	
	    });
		
		
	});