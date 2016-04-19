	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict();
  	//--------
  	//Koder
  	//--------
	jq(function() {
		jq('#generalCodeList').on('click', 'td', function(){
			  var id = this.id;
			  var record = id.split('@');
			  var kod = record[0].replace("kod", "");
			  var callerType = record[1].replace("ctype", "");
			  //alert(kod + " " + callerType);
			  
			  if(callerType == 'dkih_02f'){
				  opener.jq('#dkih_02f').val(kod);
				  opener.jq('#dkih_02f').focus();
				  
			  }else if(callerType == 'dkih_08f'){
				  opener.jq('#dkih_08f').val(kod);
				  opener.jq('#dkih_08f').focus();
				  
			  }else if(callerType == 'dkih_trlk'){
				  opener.jq('#dkih_trlk').val(kod);
				  opener.jq('#dkih_trlk').focus();
				  
			  }else if(callerType == 'dkih_nilk'){
				  opener.jq('#dkih_nilk').val(kod);
				  opener.jq('#dkih_nilk').focus();
				  
			  }else if(callerType == 'dkih_15'){
				  opener.jq('#dkih_15').val(kod);
				  opener.jq('#dkih_15').focus();
				  
			  }else if(callerType == 'dkih_211'){
				  opener.jq('#dkih_211').val(kod);
				  opener.jq('#dkih_211').focus();
				  
			  }else if(callerType == 'dkih_a'){
				  opener.jq('#dkih_a').val(kod);
				  opener.jq('#dkih_a').focus();
				  
			  }else if(callerType == 'dkih_221'){
				  opener.jq('#dkih_221').val(kod);
				  opener.jq('#dkih_221').focus();
				  
			  }else if(callerType == 'dkif_vakd'){
				  opener.jq('#dkif_vakd').val(kod);
				  opener.jq('#dkif_vakd').focus();
				  
			  }

			  //close child window
			  window.close();
		  });
	});
	
	
	//======================
    //Datatables jquery 
    //======================
    //private function [Filters]
    function filterGeneralCode () {
    		jq('#generalCodeList').DataTable().search(
      		jq('#generalCodeList_filter').val()
    		).draw();
    } 
	//Init datatables
    jq(document).ready(function() {
  	  //-----------------------
      //table [General Code List]
  	  //-----------------------
    	  jq('#generalCodeList').dataTable( {
    		  "dom": '<"top"fli>rt<"bottom"p><"clear">',
    		  "lengthMenu": [ 75, 100, 200, 500]
    	  });
      //event on input field for search
      jq('input.generalCodeList_filter').on( 'keyup click', function () {
      		filterGeneralCode();
      });
      
    });   
  	
  	
	