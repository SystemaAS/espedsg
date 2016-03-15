	//============================================================
	//General functions for TRANSPORT DISP. Child Search windows
	//============================================================
	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict();
  	
  	//Customer
	jq(function() {
		jq('#customerList').on('click', 'td', function(){
		  var id = this.id;
		  var record = id.split('@');
		  var kundNr = record[0].replace("kundnr_", "");
		  var customerName = record[1].replace("navn_", "");
		  //alert(kundNr + " type:" + jq('#ctype').val() + "-->customerName:" + customerName);
		  //addressing a parent field from this child window
		  opener.jq('#kundenr').val(kundNr);
		  opener.jq('#kundenr').focus();
		  //close child window
		  window.close();
	  });
	});
	
	
	//======================
    //Datatables jquery 
    //======================
    //private function [Filters]
    function filterCustomerList (){
        jq('#customerList').DataTable().search(
    		jq('#customerList_filter').val()
        ).draw();
    }
    
    //Init datatables
    jq(document).ready(function() {
  	  //-----------------------
	  //tables [Customer No.]
	  //-----------------------
	  jq('#customerList').dataTable( {
		  "dom": '<"top"fli>rt<"bottom"p><"clear">',
		  "lengthMenu": [ 50, 75, 100 ]
	  });
	  //event on input field for search
	  jq('input.customerList_filter').on( 'keyup click', function () {
		  filterCustomerList();
	  });
    });
	  
  	