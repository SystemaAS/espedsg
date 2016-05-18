	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict();
  	var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";
    
  	
  	//======================
    //Datatables jquery 
    //======================
    //private function [Filters]
    function filterGeneral () {
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
      		filterGeneral();
      });
      
    });   
  	
  	
  	