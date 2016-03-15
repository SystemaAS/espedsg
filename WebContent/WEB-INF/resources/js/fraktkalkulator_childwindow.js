	//============================================================
	//General functions for FRAKTKALKULATOR Child Search windows
	//============================================================
	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict();
  	
  	//Select Kundnr
	jq(function() {
	  jq(".clazzDtRowKund").dblclick(function() {
		  var id = this.id;
		  var record = id.split('@');
		  var kundNr = record[0];
		  //DEBUG --> alert(kundNr);
		  //addressing a parent field from this child window
		  opener.jq('#avvknr').val(kundNr);
		  //close child window
		  window.close();
	  });
	});
	//Select Postal Code Fra
	jq(function() {
	  jq(".clazzDtRowPostalCodeFra").dblclick(function() {
		  var id = this.id;
		  var record = id.split('@');
		  var postalCodeRaw = record[0];
		  var postalCode = postalCodeRaw.split('_');
		  //addressing a parent field from this child window
		  opener.jq('#fra').val(postalCode[0]);
		  opener.jq('#fralk').val(postalCode[1]);
		  //close child window
		  window.close();
	  });
	});
	//Select Postal Code Til
	jq(function() {
	  jq(".clazzDtRowPostalCodeTil").dblclick(function() {
		  var id = this.id;
		  var record = id.split('@');
		  var postalCodeRaw = record[0];
		  var postalCode = postalCodeRaw.split('_');
		  //addressing a parent field from this child window
		  opener.jq('#til').val(postalCode[0]);
		  opener.jq('#tillk').val(postalCode[1]);
		  //close child window
		  window.close();
	  });
	});
	
	//====================
    //Datatables jquery
    //====================
    //private function [Filters]
    function filterCustomer () {
    		jq('#customerList').DataTable().search(
      		jq('#customerList_filter').val()
    		).draw();
    }
    
    function filterPostNrFrom () {
        jq('#postNrFromList').DataTable().search(
        		jq('#postNrFromList_filter').val()
        ).draw();
    }
    
    function filterPostNrTo () {
        jq('#postNrToList').DataTable().search(
        		jq('#postNrToList_filter').val()
        ).draw();
    }
    
    
    //Init datatables
    jq(document).ready(function() {
  	  //-----------------------
      //table [Customer]
  	  //-----------------------
    	  jq('#customerList').dataTable( {
    		  "dom": '<"top"fli>rt<"bottom"p><"clear">',
    		  "lengthMenu": [ 50, 75, 100]
    	  });
      //event on input field for search
      jq('input.customerList_filter').on( 'keyup click', function () {
      		filterCustomer();
      });
      
      //--------------------------
      //table [PostNr From]
	  //--------------------------
	  jq('#postNrFromList').dataTable( {
		  "dom": '<"top"fli>rt<"bottom"p><"clear">',
		  "lengthMenu": [ 50, 75, 100 ]
	  });
	  //event on input field for search
	  jq('input.postNrFromList_filter').on( 'keyup click', function () {
		  filterPostNrFrom();
	  });
	  
	  //-----------------------
	  //tables [PostNr To]
	  //-----------------------
	  jq('#postNrToList').dataTable( {
		  "dom": '<"top"fli>rt<"bottom"p><"clear">',
		  "lengthMenu": [ 50, 75, 100 ]
	  });
	  //event on input field for search
	  jq('input.postNrToList_filter').on( 'keyup click', function () {
		  filterPostNrFrom();
	  });
    	
    });
  	