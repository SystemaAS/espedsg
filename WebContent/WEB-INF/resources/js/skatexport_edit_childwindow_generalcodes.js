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
			  
			  if(callerType == 'dkeh_02f'){
				  opener.jq('#dkeh_02f').val(kod);
				  opener.jq('#dkeh_02f').focus();
				  
			  }else if(callerType == 'dkeh_08f'){
				  opener.jq('#dkeh_08f').val(kod);
				  opener.jq('#dkeh_08f').focus();
				  
			  }else if(callerType == 'dkeh_relk'){
				  opener.jq('#dkeh_relk').val(kod);
				  opener.jq('#dkeh_relk').focus();
				  
			  }else if(callerType == 'dkeh_17a'){
				  opener.jq('#dkeh_17a').val(kod);
				  opener.jq('#dkeh_17a').focus();
				  
			  }else if(callerType == 'dkeh_21'){
				  opener.jq('#dkeh_21').val(kod);
				  opener.jq('#dkeh_21').focus();
				  
			  }else if(callerType == 'dkeh_s131'){
				  opener.jq('#dkeh_s131').val(kod);
				  opener.jq('#dkeh_s131').focus();
				  
			  }else if(callerType == 'dkeh_s132'){
				  opener.jq('#dkeh_s132').val(kod);
				  opener.jq('#dkeh_s132').focus();
				  
			  }else if(callerType == 'dkeh_221'){
				  opener.jq('#dkeh_221').val(kod);
				  opener.jq('#dkeh_221').focus();
				  
			  }else if(callerType == 'dkeh_a1'){
				  opener.jq('#dkeh_a1').val(kod);
				  opener.jq('#dkeh_a1').focus();
				  
			  }else if(callerType == 'dkeh_29'){
				  opener.jq('#dkeh_29').val(kod);
				  opener.jq('#dkeh_29').focus();
				  
			  }else if(callerType == 'dkef_vakd'){
				  opener.jq('#dkef_vakd').val(kod);
				  opener.jq('#dkef_vakd').focus();
				  
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
  	
  	
	