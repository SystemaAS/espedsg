  //this variable is a global jQuery var instead of using "$" all the time. Very handy
  var jq = jQuery.noConflict();
  var counterIndex = 0;
  var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";
  
  function setBlockUI(element){
	  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
  }
  
  jq(function() {
	  jq("#trorUpdateTracktForm").submit(function() {
		  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT}); 
	  });
	  jq("#createNewLineForm").submit(function() {
		  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT}); 
	  });
  });
  
  jq(document).ready(function() {
		//jq('#updCancelButton').hide();
  });
  
  //Global functions
  function g_getCurrentYearStr(){
	  return new Date().getFullYear().toString();
  }
  function g_getCurrentMonthStr(){
	  var currentMonth = new Date().getMonth() + 1;
	  var currentMonthStr = currentMonth.toString();
	  if (currentMonth < 10) { currentMonthStr = '0' + currentMonth; }
	  return currentMonthStr;
  }

  jq(function() {
		jq("#ttdate").datepicker({ 
			dateFormat: 'yymmdd'  
		});
  });
  
  
//custom validity
  jq('#ttfbnr').focus(function() {
	  if(jq('#ttfbnr').val()!=''){
		  refreshCustomValidity(jq('#ttfbnr')[0]);
	  }
  });
  jq('#ttacti').focus(function() {
		if(jq('#ttacti').val()!=''){
			refreshCustomValidity(jq('#ttacti')[0]);
		}
	  });
  jq('#ttdate').focus(function() {
		if(jq('#ttdate').val()!=''){
			refreshCustomValidity(jq('#ttdate')[0]);
		}
  });
  jq('#tttime').focus(function() {
		if(jq('#tttime').val()!=''){
			refreshCustomValidity(jq('#tttime')[0]);
		}
  });
  
 /*
  jq(function() {
	  jq('#newRecordButton').click(function() {
		  jq('#fskode').val("");
		  jq('#fskode').prop("readonly", false);
		  jq('#fskode').removeClass("inputTextReadOnly");
		  jq('#fskode').addClass("inputTextMediumBlueMandatoryField");
		  
		  //rest of the gang
		  jq('#fssok').val("");
		  jq('#fsdokk').val("");
		  //
		  jq('#isModeUpdate').val("");
		  //
		  jq('#fskode').focus();
	  });
  });
    */
  
  //-------------------
  //Fetch specific line
  //-------------------
  function getItemData(record) {
		var FIELD_SEPARATOR = "@";
	  	var htmlValue = record.id;
	  	var applicationUser = jq('#applicationUser').val();
	  	//alert(htmlValue);
	  	htmlValue = htmlValue.replace("recordUpdate_","");
	  	var fields = htmlValue.split(FIELD_SEPARATOR);
	  	var avd = fields[0].replace("avd_","");
		var opd = fields[1].replace("opd_","");
		var date = fields[2].replace("date_","");
		var time = fields[3].replace("time_","");
		
	  	//id="recordUpdate_avd_${record.ttavd}@opd_${record.ttopd}@date_${record.ttdate}@time_${record.tttime}
	  	var requestString = "user=" + jq('#applicationUser').val() + "&ttavd=" + avd + "&ttopd=" + opd + "&ttdate=" + date + "&tttime=" + time;
	  	//DEBUG--> alert(requestString);
	  	
	  	jq.ajax({
	  	  type: 'GET',
	  	  url: 'getTrackAndTraceGeneralDetailLine.do',
	  	  data: { applicationUser : applicationUser, 
	  		  	  requestString : requestString }, 
	  	  dataType: 'json',
	  	  cache: false,
	  	  contentType: 'application/json',
	  	  success: function(data) {
	  		var len = data.length;
			for ( var i = 0; i < len; i++) {
				//alert(data[i].fask);
				
				jq('#updateId').val("");jq('#updateId').val("true");
				jq('#ttfbnr').val("");jq('#ttfbnr').val(data[i].ttfbnr); 
				jq('#ttacti').val("");jq('#ttacti').val(data[i].ttacti); 
				//keys (plus ttavd and ttopd of course
				jq('#ttdate').val("");jq('#ttdate').val(data[i].ttdate); 
				jq('#tttime').val("");jq('#tttime').val(data[i].tttime);
				//
				jq('#ttmanu').val("");jq('#ttmanu').val(data[i].ttmanu); 
				jq('#ttedev').val("");jq('#ttedev').val(data[i].ttedev); 
				jq('#ttedre').val("");jq('#ttedre').val(data[i].ttedre); 
				//texts
				jq('#tttexl').val("");jq('#tttexl').val(data[i].tttexl); 
				jq('#tttext').val("");jq('#tttext').val(data[i].tttext); 
				//other
				jq('#ttdepo').val("");jq('#ttdepo').val(data[i].ttdepo); 
				jq('#ttname').val("");jq('#ttname').val(data[i].ttname); 
				//logg and user data (read only)
				jq('#ttdatl').val("");jq('#ttdatl').val(data[i].ttdatl); 
				jq('#tttiml').val("");jq('#tttiml').val(data[i].tttiml); 
				jq('#ttuser').val("");jq('#ttuser').val(data[i].ttuser); 
				//read only field(s) on GUI
				jq('#ttdate').prop("readonly", true);
				jq('#ttdate').removeClass("inputTextMediumBlueMandatoryField");
				jq('#ttdate').addClass("inputTextReadOnly");
				//remove datepicker
				jq( '#ttdate' ).datepicker( "destroy" );
				jq( '#ttdate' ).removeClass("hasDatepicker").removeAttr('id');
				//end datepicker
				jq('#tttime').prop("readonly", true);
				jq('#tttime').removeClass("inputTextMediumBlueMandatoryField");
				jq('#tttime').addClass("inputTextReadOnly");
				
			}
	  	  },
	  	  error: function() {
	  	    alert('Error loading ...');
	  	  }
	  	  
	  	});
	  	
	  	
	}
	
  	//---------------------------------------
	//DELETE Invoice line
	//This is done in order to present a jquery
	//Alert modal pop-up
	//----------------------------------------
	function doDeleteItemLine(element){
	  //start
		//avd_${model.avd}@opd_${model.opd}@date_${record.date}@time_${record.time}
		var record = element.id.split('@');
		var avd = record[0].replace("avd_","");
		var opd = record[1].replace("opd_","");
		var date = record[2].replace("date_","");
		var time = record[3].replace("time_","");
		
	  //Start dialog
	  jq('<div></div>').dialog({
      modal: true,
      title: "Dialog - Slett date: " + date + " " + time,
      buttons: {
	        Fortsett: function() {
      		jq( this ).dialog( "close" );
	            //do delete
	            jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
	            window.location = "tror_mainorderland_ttrace_general_edit.do?action=doDelete" + "&ttavd=" + avd + "&ttopd=" + opd + "&ttdate=" + date + "&tttime=" + time;
	        },
	        Avbryt: function() {
	            jq( this ).dialog( "close" );
	        }
      },
      open: function() {
	  		  var markup = "Er du sikker på at du vil slette denne?";
	          jq(this).html(markup);
	          //make Cancel the default button
	          jq(this).siblings('.ui-dialog-buttonpane').find('button:eq(1)').focus();
	     }
	  });  //end dialog
	}
  
//-------------------
  //Datatables jquery
  //-------------------
  //private function
  function filterGlobal () {
    jq('#tblMain').dataTable().search(
    	jq('#tblMain_filter').val()
    ).draw();
  }

  jq(document).ready(function() {
	  var lang = jq('#language').val(); 
  	  
    //init table (no ajax, no columns since the payload is already there by means of HTML produced on the back-end)
    jq('#tblMain').dataTable( {
  	  	"dom": '<"top">rt<"bottom"f><"clear">',
		  "scrollY":  "200px",
		  "order": [ [ 4, "asc" ], [ 5, "asc" ] ],
		  "scrollCollapse":  true,
		  "lengthMenu": [ 25, 50],
		  "language": { "url": getLanguage(lang) }
	  });
    //event on input field for search
    jq('input.tblMain_filter').on( 'keyup click', function () {
    		filterGlobal();
    });
	
  });
	 
  