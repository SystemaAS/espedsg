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
			  
			  if(callerType == 'dkiv_36'){
				  opener.jq('#dkiv_36').val(kod);
				  opener.jq('#dkiv_36').focus();
				  
			  }else if(callerType == 'dkiv_37'){
				  opener.jq('#dkiv_37').val(kod);
				  opener.jq('#dkiv_37').focus();
				  
			  }else if(callerType == 'dkiv_314a'){
				  opener.jq('#dkiv_314a').val(kod);
				  opener.jq('#dkiv_314a').focus();
				  
			  }else if(callerType == 'dkiv_411'){
				  opener.jq('#dkiv_411').val(kod);
				  opener.jq('#dkiv_411').focus();
				  
			  }else if(callerType == 'dkiv_34'){
				  opener.jq('#dkiv_34').val(kod);
				  opener.jq('#dkiv_34').focus();
				  
			  }else if(callerType == 'dkiv_402a'){
				  opener.jq('#dkiv_402a').val(kod);
				  opener.jq('#dkiv_402a').focus();
			  //Certifikat (begins here)	  
			  }else if(callerType == 'dkiv_4421'){
				  opener.jq('#dkiv_4421').val(kod);
				  opener.jq('#dkiv_4421').focus();
				  
			  }else if(callerType == 'dkiv_443'){
				  opener.jq('#dkiv_443').val(kod);
				  opener.jq('#dkiv_443').focus();
				  
			  }else if(callerType == 'dkiv_444b'){
				  opener.jq('#dkiv_444b').val(kod);
				  opener.jq('#dkiv_444b').focus();
				  
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
  	
  	
	