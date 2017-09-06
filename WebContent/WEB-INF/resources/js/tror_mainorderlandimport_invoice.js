	//this variable is a global jQuery var instead of using "$" all the time. Very handy
	var jq = jQuery.noConflict();
	var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";
    
	function setBlockUI(element){
		  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
	  }
  	
	//Overlay on tab (to mark visually a delay...)
    jq(function() {
	  jq('#alinkTopicList').click(function() { 
  		  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
  	  });	
  	  jq('#alinkHeader').click(function() { 
		  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
	  });
      jq('#alinkOmberegning').click(function() { 
  		  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
  	  });  	  
  	  jq('#alinkItemLines').click(function() { 
  		  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
  	  });
  	  jq('#alinkLogging').click(function() { 
		  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
	  });
  	  jq('#alinkArchive').click(function() { 
		  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
	  });
    });
  	
	jq(function() {
		jq("#sfdt").datepicker({ 
			dateFormat: 'ddmmy'  
		});
    });
	
	jq(function() {
  	  	jq('#importInvoicesButton').click(function() {
  	  		window.open('tvinnsadexport_edit_childwindow_external_invoices.do?avd=' + jq("#avd").val() + "&opd=" + jq("#opd").val(), 'importInvoicesWin','top=120px,left=100px,height=600px,width=800px,scrollbars=no,status=no,location=no');
  	  	});
  	  	
  	  	//=====================================
	  	//START Child window for general codes
	  	//=====================================
  		//Valutakod
	    jq('#sfvk28IdLink').click(function() {
	    	jq('#sfvk28IdLink').attr('target','_blank');
	    	window.open('tvinnsadexport_edit_childwindow_generalcodes.do?action=doInit&type=V&ctype=sfvk28', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#sfvk28IdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#sfvk28IdLink').click();
			}
	    });
	    //=====================================
	  	//END Child window for general codes
	  	//=====================================
  	});
	
	//-----------------------------------------------------------------------------
  	//jQuery CALCULATOR (related to jquery.calculator.js and jquery.calculator.css
  	//-----------------------------------------------------------------------------
  	jq(function() {
  		jq('#sfbl28').calculator({ showOn: 'button',  
  			buttonImageOnly: true, buttonImage: 'resources/images/calculator.png', decimalChar: ','});
  	});
  	
	//----------------
	//onChange events
	//----------------
	jq(function() { 
		jq('#currencySearch').change(function() {
			jq('#sfvk28').val(jq('#currencySearch').val());	
		});
	});
	  
	jq(function() {
		//CustomValidity refresh
		jq('#fask').focus(function() {
	    	if(jq('#fask').val()!=''){
	    		refreshCustomValidity(jq('#fask')[0]);
	  		}
	  	});
		jq('#favk').focus(function() {
	    	if(jq('#favk').val()!=''){
	    		refreshCustomValidity(jq('#favk')[0]);
	  		}
	  	});
		jq('#faval').focus(function() {
	    	if(jq('#faval').val()!=''){
	    		refreshCustomValidity(jq('#faval')[0]);
	  		}
	  	});
		jq('#fabelv').focus(function() {
	    	if(jq('#fabelv').val()!=''){
	    		refreshCustomValidity(jq('#fabelv')[0]);
	  		}
	  	});
		jq('#fakdm').focus(function() {
	    	if(jq('#fakdm').val()!=''){
	    		refreshCustomValidity(jq('#fakdm')[0]);
	  		}
	  	});
		
		
		//Clean values for createing new record
		jq('#updCancelButton').click(function() {
			//key
			jq('#fali').val("");
			//rest
			jq('#fask').val("");
  			jq('#favk').val("");
  			jq('#faVT').val("");
  			jq('#faval').val("");
  			jq('#fabelv').val("");
  			jq('#fakdm').val("");
		});
	}); 
  	/**
  	 * gets a specific item line
  	 * 
  	 * @param record
  	 */
  	
	function getInvoiceItemData(record) {
		var FIELD_SEPARATOR = "_";
	  	var htmlValue = record.id;
	  	var applicationUser = jq('#applicationUser').val();
	  	//alert(htmlValue);
	  	var field = htmlValue.split(FIELD_SEPARATOR);
	  	var lineId = field[1];
	  	var requestString = "user=" + jq('#applicationUser').val() + "&avd=" + jq('#heavd').val() + "&opd=" + jq('#heopd').val() + "&lin=" + lineId + "&type=A";
	  	//alert(requestString);
	  	//http://gw.systema.no/sycgip/TJGE25R.pgm?user=JOVO&avd=80&opd=201523&lin=&type=A
	  	
	  	jq.ajax({
	  	  type: 'GET',
	  	  url: 'getOrderInvoiceDetailLine_Landimport.do', //we are borrowing services from TranspDisp in the Controller until Tror module is able to migrate this ...
	  	  data: { applicationUser : applicationUser, 
	  		  	  requestString : requestString }, 
	  	  dataType: 'json',
	  	  cache: false,
	  	  contentType: 'application/json',
	  	  success: function(data) {
	  		var len = data.length;
			for ( var i = 0; i < len; i++) {
				//alert(data[i].fask);
				//jq('#editLineNr').text("");jq('#editLineNr').text(data[i].fali);
				jq('#updCancelButton').show(); //in order to be able to cancel (implicit reload)
				
				jq('#editLineNr').text("");jq('#editLineNr').text(data[i].fali); //for GUI purposes
				//keys
				jq('#fali').val("");jq('#fali').val(data[i].fali);
				jq('#fakunr').val("");jq('#fakunr').val(data[i].fakunr);
				//the rest
				jq('#fask').val("");jq('#fask').val(data[i].fask);
				jq('#favk').val("");jq('#favk').val(data[i].favk);
				jq('#fabelv').val("");jq('#fabelv').val(data[i].fabelv);
				jq('#faval').val("");jq('#faval').val(data[i].faval);
				jq('#fakdm').val("");jq('#fakdm').val(data[i].fakdm);
				//Fritext
				jq('#faVT').val("");jq('#faVT').val(data[i].faVT);
				//jq('#stdVt').val("");jq('#stdVt').val(data[i].stdVt);
				/* tentative 
				var freeText = data[i].faVT;
				if(freeText!=""){
					jq('#freeText').val("");jq('#freeText').val(data[i].faVT); //only for presentation purposes
				}else{
					jq('#freeText').val("");jq('#freeText').val(data[i].stdVt); //only for presentation purposes
				}
				
				jq('#falevn').val("");jq('#falevn').val(data[i].falevn);
				jq('#fadocnB').val("");jq('#fadocnB').val(data[i].fadocnB);
				jq('#faavdr').val("");jq('#faavdr').val(data[i].faavdr);
				jq('#fakduk').val("");jq('#fakduk').val(data[i].fakduk);
				jq('#facu33').val("");jq('#facu33').val(data[i].facu33);
				jq('#fabelu').val("");jq('#fabelu').val(data[i].fabelu);
				
				jq('#fakunr').val("");jq('#fakunr').val(data[i].fakunr);
				jq('#facd11').val("");jq('#facd11').val(data[i].facd11);
				*/
				
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
	function doPermanentlyDeleteInvoiceLine(element){
	  //start
	  var record = element.id.split('@');
	  var heavd = record[0];
	  var heopd = record[1];
	  var fali = record[2];
	  heavd= heavd.replace("heavd_","");
	  heopd= heopd.replace("heopd_","");
	  fali= fali.replace("fali_","");
	  
	  //Start dialog
	  jq('<div></div>').dialog({
        modal: true,
        title: "Dialog - Slett linje: " + fali,
        buttons: {
	        Fortsett: function() {
        		jq( this ).dialog( "close" );
	            //do delete
	            jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
	            window.location = "tror_mainorderlandimport_invoice_edit.do?action=doDelete" + "&heavd=" + heavd + "&heopd=" + heopd + "&fali=" + fali;
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
	
  	//============================
	//START - Currency AJAX fetch
	//============================
	jq(function() { 
	    jq('#sfvk28').change(function() {
	    		//In Norway we must use the current day (today) as currency date, 
    			//therefore we send = null. The AjaxController will take care of the rest
    			var currencyDate = null; 
	    		getCurrencyData(currencyDate);
	    });
	});
	//fetch currency rate in date event (if applicable)
	jq(function() { 
	    jq('#sfdt').blur(function() {
	    		var currencyRate = jq('#sfkr28').val();
	    		if(currencyRate==null || currencyRate==""){
	    			//In Norway we must use the current day (today) as currency date, 
	    			//therefore we send = null. The AjaxController will take care of the rest
	    			var currencyDate = null;
	    			getCurrencyData(currencyDate);
	    		}
	    });
	});
	//private function to AJAX-Controller
	function getCurrencyData(currencyDate) {
		jq.ajax({
			type: 'GET',
			url: 'getCurrencyRate_SadExport.do',
			data: { 	applicationUser : jq('#applicationUser').val(),
					currencyCode : jq('#sfvk28').val(),
					isoDate : currencyDate} ,
			dataType: 'json',
			success: function(data) {
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					jq('#sfkr28').val(data[i].kvakrs);
					jq('#factor').val(data[i].kvaomr);
				}
			}
		});
	}
	//============================
	//END - Currency AJAX fetch
	//============================

	
	//-------------------
    //Datatables jquery
    //-------------------
    //private function
    function filterGlobal () {
      jq('#tblInvoices').dataTable().search(
      	jq('#tblInvoices_filter').val()
      ).draw();
    }

    jq(document).ready(function() {
      //Aspects in general 
      jq('#updCancelButton').hide();
    	
      //init table (no ajax, no columns since the payload is already there by means of HTML produced on the back-end)
      jq('#tblInvoices').dataTable( {
    	  //"dom": '<"top">t<"bottom"f><"clear">',
    	  "dom": '<"top"i>rt<"bottom"f><"clear">',
  		  "scrollY":    "200px",
  		  "order": [[ 2, "asc" ], [ 0, "asc" ]],
  		  "scrollCollapse":  true,
  		  "lengthMenu": [ 25, 50]
  	  });
      //event on input field for search
      jq('input.tblInvoices_filter').on( 'keyup click', function () {
      		filterGlobal();
      });
  	
    });

	