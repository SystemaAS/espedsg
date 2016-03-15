  //this variable is a global jQuery var instead of using "$" all the time. Very handy
  var jq = jQuery.noConflict();
  var counterIndex = 0;
  
  
  jq(function() {
	  jq("#from").datepicker({ 
		  dateFormat: 'yymmdd'
	  });
	  jq("#to").datepicker({ 
		  dateFormat: 'yymmdd'
	  });

  });
  
  
  
  //-------------------
  //Datatables jquery
  //-------------------
  //private function
  function filtersInit () {
    jq('#logRecords').DataTable().search(
    		jq('#openOrders_filter').val()
    ).draw();
  }

  jq(document).ready(function() {
    //init table (no ajax, no columns since the payload is already there by means of HTML produced on the back-end)
	jq('#logRecords').dataTable( {
	  "jQueryUI": false,
	  "dom": '<"top"fli>rt<"bottom"p><"clear">',
	  "order": [[ 4, "desc" ]],
	  "lengthMenu": [ 50, 75, 100]
	} );
    //event on input field for search
    jq('input.logRecords_filter').on( 'keyup click', function () {
    		filtersInit();
    } );
  } );
  
  
	 


  
  