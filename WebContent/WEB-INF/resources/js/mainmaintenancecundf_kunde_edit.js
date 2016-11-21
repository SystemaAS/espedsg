  //this variable is a global jQuery var instead of using "$" all the time. Very handy
  var jq = jQuery.noConflict();
  var counterIndex = 0;
  var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";
  
  function setBlockUI(element){
	  
	  //jq("#dialog").dialog("open");  
	  //Funkar inte att anropas rått. Behöver <div> alt .click
	  
//	  if(jq("#dirty").val() =="isDirty") {
//
//		  alert("Du har ändra, utan att spara. Vill du gå vidare.....");
//
//	  }

	  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
  }
  
jq(function() {
	jq("#formRecord").submit(function() {
		jq.blockUI({
			message : BLOCKUI_OVERLAY_MESSAGE_DEFAULT
		});
	});

	jq("input[type='text']").change(function() {
		jq('#dirty').val("isDirty");

	});	

    jq('#spraakIdLink').click(function() {
    	alert("Hej Språk");
    	jq('#spraakIdLink').attr('target','_blank');
    	window.open('kunde_edit_childwindow.do?action=doInit&type=2&ctype=silka', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });
    jq('#sylandIdLink').click(function() {
    	alert("Hej Land");
    	jq('#sylandIdLink').attr('target','_blank');
    	window.open('kunde_edit_childwindow.do?action=doInit&type=2&ctype=silka', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });
    jq('#valkodIdLink').click(function() {
    	alert("Hej Valkod");
    	jq('#valkodIdLink').attr('target','_blank');
    	window.open('kunde_edit_childwindow.do?action=doInit&type=2&ctype=silka', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });
    jq('#kundgrIdLink').click(function() {
    	alert("Hej Kundgrupp");
    	jq('#kundgrIdLink').attr('target','_blank');
    	window.open('kunde_edit_childwindow.do?action=doInit&type=2&ctype=silka', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });
    jq('#syopdtIdLink').click(function() {
    	alert("Hej Oppdragstype");
    	jq('#syopdtIdLink').attr('target','_blank');
    	window.open('kunde_edit_childwindow.do?action=doInit&type=2&ctype=silka', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });
    jq('#sylikvIdLink').click(function() {
    	alert("Hej Likviditetskode");
    	jq('#sylikvIdLink').attr('target','_blank');
    	window.open('kunde_edit_childwindow.do?action=doInit&type=2&ctype=silka', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });
    jq('#golkIdLink').click(function() {
    	alert("Hej Godslokalkode");
    	jq('#golkIdLink').attr('target','_blank');
    	window.open('kunde_edit_childwindow.do?action=doInit&type=2&ctype=silka', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });
    jq('#aktkodIdLink').click(function() {
    	alert("Hej Aktivitetskode");
    	jq('#aktkodIdLink').attr('target','_blank');
    	window.open('kunde_edit_childwindow.do?action=doInit&type=2&ctype=silka', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });
    jq('#syselgIdLink').click(function() {
    	alert("Hej Selgerkode");
    	jq('#syselgIdLink').attr('target','_blank');
    	window.open('kunde_edit_childwindow.do?action=doInit&type=2&ctype=silka', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    });
    
    
    

}); 
  