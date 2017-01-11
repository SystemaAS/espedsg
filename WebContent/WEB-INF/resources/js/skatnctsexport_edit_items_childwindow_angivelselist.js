	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict();
  	
  	//--------
  	//Koder
  	//--------
	jq(function() {
		jq("#datum").datepicker({ 
			  dateFormat: 'yymmdd' 
			  //defaultDate: "-6m"	  
		});
		
		//put values from childwindow into opener fields
		jq('#angivelseList').on('click', 'td', function(){
			  var id = this.id;
			  var record = id.split('@');
			  var avd = record[0].replace("avd", "");
			  var opd = record[1].replace("opd", "");
			  var xref = record[2].replace("xref", "");
			  var refnr = record[3].replace("refnr", "");
			  var mrn = record[4].replace("mrn", "");
			  var faktValuta = record[5].replace("valuta", "");
			  var faktBelopp = record[6].replace("blp", "");
			  
			  //alert(vkod + " " + text);
			  opener.jq('#tvavd2').val(avd);
			  opener.jq('#tvtdn2').val(opd);
			  opener.jq('#xref').val(xref);
			  opener.jq('#dkxv_221').val(faktValuta);
			  opener.jq('#dkxv_222').val(faktBelopp);
			  
			  if(opener.jq('#tvdref').val()==''){
				  if(mrn!=''){
					  opener.jq('#tvdref').val(mrn);
				  }else{	  
					  opener.jq('#tvdref').val(refnr);
				  }
			  }
			  
			  opener.jq('#tvtdn2').focus();
			  //close child window
			  window.close();
			  
	    });
	});
	
	jq(function() {
		jq('#vkod').blur(function(){
			if(jq('#vkod').val() != ""){
				jq('#tekst').val("");
			}
		});
		jq('#tekst').blur(function(){
			if(jq('#tekst').val() != ""){
				jq('#vkod').val("");
			}
		});
	});
	
	//======================
    //Datatables jquery 
    //======================
    //private function [Filters]
    function filterGeneralCode () {
    		jq('#angivelseList').DataTable().search(
      		jq('#angivelseList_filter').val()
    		).draw();
    } 
	//Init datatables
    jq(document).ready(function() {
  	  //-----------------------
      //table [General Code List]
  	  //-----------------------
    	  jq('#angivelseList').dataTable( {
    		  "dom": '<"top"fli>rt<"bottom"p><"clear">',
    		  "lengthMenu": [ 75, 100, 200, 500]
    	  });
      //event on input field for search
      jq('input.angivelseList_filter').on( 'keyup click', function () {
      		filterGeneralCode();
      });
      
    });   
  	
  	
	