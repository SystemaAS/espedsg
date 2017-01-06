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
    
    jq('#syrg').blur(function() {
		//var customerNr = jq('#tgkna').val();
		var orgNr =  jq('#syrg').val();
		//var name = jq('#tgnaa').val();
	    //var address = jq('#tgada1').val();
	    alert("orgNr="+orgNr);
		//if(customerNr!="" && (orgNr=='' && name=='' && address=='')){
		if(orgNr!=""){
    		jq.getJSON('getSpecificRecord_enhet_brreg.do', {
			applicationUser : jq('#applicationUser').val(),
			orgnr : orgNr,
			ajax : 'true'
		}, function(data) {
			var len = data.length;
			for ( var i = 0; i < len; i++) {
				customer = new Object();
				customer.knavn = data[i].navn;
				//customer.eori = data[i].eori;
				//customer.adr1 = data[i].adr1;
				//customer.adr2 = data[i].adr2;
				//customer.adr3 = data[i].adr3;
				//customer.postnr = data[i].postnr;
				//customer.syrg = data[i].syrg;
				//customer.tlf = data[i].tlf;
				//customer.syland = data[i].syland;
			  	//put the object in map now with customerNumber as key
				//map[customer.kundnr] = customer;
			}
			if(len > 0){
			//	jq('#tgtina').val(customer.syrg);
				jq("#knavn").val(customer.knavn);jq("#knavn").change();
			//	jq('#tgada1').val(customer.adr1);
			//	jq('#tgpna').val(customer.postnr);
			//	jq('#tgpsa').val(customer.adr3);
			//	jq('#tglka').val(customer.syland);
			}else{
				//init fields
			//	jq('#tgtina').val("");
			//	jq('#tgnaa').val("");
			//	jq('#tgada1').val("");
			//	jq('#tgpna').val("");
			//	jq('#tgpsa').val("");
			}
		});
		}
});

    
    

}); 
  