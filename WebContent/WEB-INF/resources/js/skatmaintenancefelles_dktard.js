  //this variable is a global jQuery var instead of using "$" all the time. Very handy
  var jq = jQuery.noConflict();
  var counterIndex = 0;
  var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";
  
  function setBlockUI(element){
	  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
  }
  
  jq(function() {
  	  jq("#dktard02").datepicker({ 
  		  dateFormat: 'yyddmm'
  	  });
  	  jq("#dktard03").datepicker({ 
  		  dateFormat: 'yyddmm'
	  });
  	  jq("#searchDktard02").datepicker({ 
		  dateFormat: 'yyddmm'
	  });
  });

  jq(function() {
	
	//Clean values for createing new record
	jq('#newRecordButton').click(function() {
		jq('#dktard01').val("");
		jq("#dktard01").prop("readonly", false);
		jq("#dktard01").removeClass("inputTextReadOnly");
		jq("#dktard01").addClass("inputTextMediumBlueMandatoryField");
		
		//rest of the gang
		jq('#dktard02').val("");jq('#dktard03').val("");jq('#dktard04').val("");
		jq('#dktard05').val("");jq('#dktard06').val("");jq('#dktard07').val("");
		jq('#dktard08').val("");jq('#dktard09').val("");jq('#dktard10').val("");
		//
		jq('#dktard11').val("");jq('#dktard12').val("");jq('#dktard13').val("");
		jq('#dktard14').val("");jq('#dktard15').val("");jq('#dktard16').val("");
		jq('#dktard17').val("");jq('#dktard18').val("");jq('#dktard19').val("");
		jq('#dktard20').val("");
		//
		jq('#dktard21').val("");jq('#dktard22').val("");jq('#dktard23').val("");
		jq('#dktard24').val("");jq('#dktard25').val("");jq('#dktard26').val("");
		jq('#dktard27').val("");jq('#dktard28').val("");jq('#dktard29').val("");
		jq('#dktard30').val("");
		//
		jq('#dktard31').val("");jq('#dktard32').val("");jq('#dktard33').val("");
		jq('#dktard34').val("");jq('#dktard35').val("");jq('#dktard36').val("");
		jq('#dktard37').val("");jq('#dktard38').val("");jq('#dktard39').val("");
		jq('#dktard40').val("");
		//
		jq('#dktard41').val("");jq('#dktard42').val("");jq('#dktard43').val("");
		jq('#dktard44').val("");jq('#dktard45').val("");jq('#dktard46').val("");
		jq('#dktard47').val("");jq('#dktard48').val("");
		//for update
		jq('#updateId').val("");
	});
	
  }); 
	
  //Varekod
  //-----------------------
  //GET specific db-record
  //-----------------------
  
  function getRecord(record){
	var rawId = record.id;
  	var applicationUserParam = jq('#applicationUser').val();
  	rawId = rawId.replace("recordUpdate_", "");
  	var record = rawId.split('_');
	var id = record[0];
	var fromDate = record[1];
	var toDate = record[2];
	
	jq.ajax({
  	  type: 'GET',
  	  url: 'getSpecificRecord_dktard.do',
  	  data: { applicationUser : jq('#applicationUser').val(), 
  		  	  id : id,
  		  	  fromDate : fromDate,
  		  	  toDate : toDate },
  	  dataType: 'json',
  	  cache: false,
  	  contentType: 'application/json',
  	  success: function(data) {
	  	var len = data.length;
  		for ( var i = 0; i < len; i++) {
  			jq('#dktard01').val("");jq('#dktard01').val(data[i].dktard01);
  			jq("#dktard01").prop("readonly", true);
  			jq("#dktard01").removeClass("inputTextMediumBlueMandatoryField");
  			jq("#dktard01").addClass("inputTextReadOnly");
  			
  			//rest of the gang
  			jq('#dktard02').val("");jq('#dktard02').val(data[i].dktard02);
  			jq('#dktard03').val("");jq('#dktard03').val(data[i].dktard03);
  			jq('#dktard04').val("");jq('#dktard04').val(data[i].dktard04);
  			jq('#dktard05').val("");jq('#dktard05').val(data[i].dktard05);
  			jq('#dktard06').val("");jq('#dktard06').val(data[i].dktard06);
  			jq('#dktard07').val("");jq('#dktard07').val(data[i].dktard07);
  			jq('#dktard08').val("");jq('#dktard08').val(data[i].dktard08);
  			jq('#dktard09').val("");jq('#dktard09').val(data[i].dktard09);
  			jq('#dktard10').val("");jq('#dktard10').val(data[i].dktard10);
  			//
  			jq('#dktard11').val("");jq('#dktard11').val(data[i].dktard11);
  			jq('#dktard12').val("");jq('#dktard12').val(data[i].dktard12);
  			jq('#dktard13').val("");jq('#dktard13').val(data[i].dktard13);
  			jq('#dktard14').val("");jq('#dktard14').val(data[i].dktard14);
  			jq('#dktard15').val("");jq('#dktard15').val(data[i].dktard15);
  			jq('#dktard16').val("");jq('#dktard16').val(data[i].dktard16);
  			jq('#dktard17').val("");jq('#dktard17').val(data[i].dktard17);
  			jq('#dktard18').val("");jq('#dktard18').val(data[i].dktard18);
  			jq('#dktard19').val("");jq('#dktard19').val(data[i].dktard19);
  			jq('#dktard20').val("");jq('#dktard20').val(data[i].dktard20);
  			//
  			jq('#dktard21').val("");jq('#dktard21').val(data[i].dktard21);
  			jq('#dktard22').val("");jq('#dktard22').val(data[i].dktard22);
  			jq('#dktard23').val("");jq('#dktard23').val(data[i].dktard23);
  			jq('#dktard24').val("");jq('#dktard24').val(data[i].dktard24);
  			jq('#dktard25').val("");jq('#dktard25').val(data[i].dktard25);
  			jq('#dktard26').val("");jq('#dktard26').val(data[i].dktard26);
  			jq('#dktard27').val("");jq('#dktard27').val(data[i].dktard27);
  			jq('#dktard28').val("");jq('#dktard28').val(data[i].dktard28);
  			jq('#dktard29').val("");jq('#dktard29').val(data[i].dktard29);
  			jq('#dktard30').val("");jq('#dktard30').val(data[i].dktard30);
  			//
  			jq('#dktard31').val("");jq('#dktard31').val(data[i].dktard31);
  			jq('#dktard32').val("");jq('#dktard32').val(data[i].dktard32);
  			jq('#dktard33').val("");jq('#dktard33').val(data[i].dktard33);
  			jq('#dktard34').val("");jq('#dktard34').val(data[i].dktard34);
  			jq('#dktard35').val("");jq('#dktard35').val(data[i].dktard35);
  			jq('#dktard36').val("");jq('#dktard36').val(data[i].dktard36);
  			jq('#dktard37').val("");jq('#dktard37').val(data[i].dktard37);
  			jq('#dktard38').val("");jq('#dktard38').val(data[i].dktard38);
  			jq('#dktard39').val("");jq('#dktard39').val(data[i].dktard39);
  			jq('#dktard40').val("");jq('#dktard40').val(data[i].dktard40);
  			//
  			jq('#dktard41').val("");jq('#dktard41').val(data[i].dktard41);
  			jq('#dktard42').val("");jq('#dktard42').val(data[i].dktard42);
  			jq('#dktard43').val("");jq('#dktard43').val(data[i].dktard43);
  			jq('#dktard44').val("");jq('#dktard44').val(data[i].dktard44);
  			jq('#dktard45').val("");jq('#dktard45').val(data[i].dktard45);
  			jq('#dktard46').val("");jq('#dktard46').val(data[i].dktard46);
  			jq('#dktard47').val("");jq('#dktard47').val(data[i].dktard47);
  			jq('#dktard48').val("");jq('#dktard48').val(data[i].dktard48);
  			
  			//for a future update
  			jq('#updateId').val("");jq('#updateId').val(data[i].dktard01);
  			
  		}
  	  }, 
  	  error: function() {
  		  alert('Error loading ...');
  	  }
	});
		
  }
  		
  //-------------------
  //Datatables jquery
  //-------------------
  //private function
  function filterGlobal () {
    jq('#mainList').dataTable().search(
    	jq('#mainList_filter').val()
    ).draw();
  }
  
  jq(document).ready(function() {
      //init table (no ajax, no columns since the payload is already there by means of HTML produced on the back-end)
      jq('#mainList').dataTable( {
    	  "dom": '<"top">t<"bottom"flip><"clear">',
    	  "scrollY": "250px",
    	  "scrollCollapse":  false,
    	  "columnDefs": [{ "type": "num", "targets": 0 }],
    	  "lengthMenu": [ 75, 100]
  	  });
      
      //event on input field for search
      jq('input.mainList_filter').on( 'keyup click', function () {
      		filterGlobal();
      });
  	
  });
  