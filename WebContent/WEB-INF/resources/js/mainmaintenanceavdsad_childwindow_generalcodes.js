	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict();
  	//----------
  	//functions
  	//----------
  	
	jq(function() {
		jq('#mainList').on('click', 'td', function(){
			  
			  var id = this.id;
			  var record = id.split('@');
			 
			  var tkkode = record[0].replace("tkkode", "");
			  var callerType = record[1].replace("ctype", "");
			  
			  if(callerType == 'titsb'){
				  opener.jq('#titsb').val(tkkode);
				  opener.jq('#titsb').focus();
				  
			  }else if(callerType == 'TODO'){
				  //opener.jq('#todo').val(tkkode);
				  //opener.jq('#todo').focus();
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
    		jq('#mainList').DataTable().search(
      		jq('#mainList_filter').val()
    		).draw();
    } 
	//Init datatables
    jq(document).ready(function() {
  	  //-----------------------
      //table [General Code List]
  	  //-----------------------
    	  jq('#mainList').dataTable( {
    		  "dom": '<"top"fli>rt<"bottom"p><"clear">',
    		  "lengthMenu": [ 75, 100, 200, 500]
    	  });
      //event on input field for search
      jq('input.mainList_filter').on( 'keyup click', function () {
      		filterGeneralCode();
      });
      
    });   
  	
  	
	