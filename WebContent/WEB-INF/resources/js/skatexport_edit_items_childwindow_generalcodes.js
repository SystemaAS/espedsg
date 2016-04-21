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
			  
			  if(callerType == 'dkev_37'){
				  opener.jq('#dkev_37').val(kod);
				  opener.jq('#dkev_37').focus();
				  
			  }else if(callerType == 'dkev_314'){
				  opener.jq('#dkev_314').val(kod);
				  opener.jq('#dkev_314').focus();
				  
			  }else if(callerType == 'dkev_411'){
				  opener.jq('#dkev_411').val(kod);
				  opener.jq('#dkev_411').focus();
				  
			  }else if(callerType == 'dkev_34a'){
				  opener.jq('#dkev_34a').val(kod);
				  opener.jq('#dkev_34a').focus();
				  
			  }else if(callerType == 'dkev_402a'){
				  opener.jq('#dkev_402a').val(kod);
				  opener.jq('#dkev_402a').focus();
				  
			  }else if(callerType == 'dkev_4421'){
				  opener.jq('#dkev_4421').val(kod);
				  opener.jq('#dkev_4421').focus();
			  //Certifikat (begins here)	  
			  }else if(callerType == 'dkev_443'){
				  opener.jq('#dkev_443').val(kod);
				  opener.jq('#dkev_443').focus();
				  
			  }else if(callerType == 'dkev_444'){
				  opener.jq('#dkev_444').val(kod);
				  opener.jq('#dkev_444').focus();
				  
			  }else if(callerType == 'dkev_445a'){
				  opener.jq('#dkev_445a').val(kod);
				  opener.jq('#dkev_445a').focus();
				  
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
  	
  	
	