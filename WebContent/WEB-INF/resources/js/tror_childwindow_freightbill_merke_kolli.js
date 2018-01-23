	//============================================================
	//General functions for EBOOKING Child Search windows
	//============================================================
	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict();
  	var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";
  	
  	function setBlockUI(element){
  	  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
    }
  	
  	
  	function filter (){
        jq('#list').DataTable().search(
    		jq('#list_filter').val()
        ).draw();
    }
  	
  	//----------------
  	//Init datatables
  	//-----------------
    jq(document).ready(function() {
      
      var lang = jq('#language').val(); 	
      //--------
      //table 
	  //--------
	  jq('#list').dataTable( {
		  "dom": '<"top"fli>rt<"bottom"p><"clear">',
		  "lengthMenu": [ 50, 75, 100 ],
		  "language": { "url": getLanguage(lang) }
	  });
	  //event on input field for search
	  jq('input.list_filter').on( 'keyup click', function () {
		  filter();
	  });
	  
    });
  	
  	
  	
	

  	