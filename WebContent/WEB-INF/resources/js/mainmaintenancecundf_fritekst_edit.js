  //this variable is a global jQuery var instead of using "$" all the time. Very handy
  var jq = jQuery.noConflict();
  var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";
  
  function setBlockUI(element){
	  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
  } 
  
  jq(function() { 
    var lines = 80;
    var linesUsed = jq('#linesUsed');
    
    jq('#fxtxt').keydown(function(e) {
        newLines = jq(this).val().split("\n").length;
        linesUsed.text(newLines);
        
        if(e.keyCode == 13 && newLines >= lines) {
        	linesUsed.text("max "+newLines);
            linesUsed.css('color', 'red');
            return false;
        }
        else {
            linesUsed.css('color', '');
        }

    });
    
    
    jq('#alinkMainMaintKontaktGate').click(function() {
    	jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
    	autoSave('mainmaintenancecundf_fritekst_edit.do', "formRecord");
    });         
    jq('#alinkMainMaintKundeGate').click(function() {
    	jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
    	autoSave('mainmaintenancecundf_fritekst_edit.do', "formRecord");
    });         
    jq('#alinkMainMaintParamsGate').click(function() {
    	jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
    	autoSave('mainmaintenancecundf_fritekst_edit.do', "formRecord");
    });         
    jq('#alinkMainMaintVareRegGate').click(function() {
    	jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
    	autoSave('mainmaintenancecundf_fritekst_edit.do', "formRecord");
    });      
    
});