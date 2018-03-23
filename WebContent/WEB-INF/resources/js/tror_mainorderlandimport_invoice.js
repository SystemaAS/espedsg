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
  	
    //Links on child windows
	jq(function() {
		  //supplier child window search
		  jq('#falevnIdLink').click(function() {
			jq('#falevnIdLink').attr('target','_blank');  
			window.open('transportdisp_workflow_childwindow_supplier.do?action=doInit&kode=' + jq('#falevn').val(),"supplierWin","top=300px,left=50px,height=800px,width=900px,scrollbars=no,status=no,location=no");
		  });
		  
		  //gebyr koder child window search
		  jq('#favkIdLink').click(function() {
			jq('#favkIdLink').attr('target','_blank');  
			window.open('transportdisp_workflow_childwindow_gebyrcode.do?action=doInit&kode=' + jq('#favk').val(),"gebyrCodesWin","top=300px,left=50px,height=800px,width=900px,scrollbars=no,status=no,location=no");
		  });
	});
	
	
	
	//Ferdigmelde (readyMark). 
	jq(function() {
	  	jq('#readyMarkButton').click(function() {
	  		jq.ajax({
			  	  type: 'GET',
			  	  url: 'updateReadyMarkInvoice.do',
			  	  data: { applicationUser : jq('#applicationUser').val(), 
			  		  	  avd : jq('#heavd').val(),
			  		  	  opd : jq('#heopd').val() }, 
			  	  dataType: 'json',
			  	  cache: false,
			  	  contentType: 'application/json',
			  	  success: function(data) {
			  		var len = data.length;
		  			for ( var i = 0; i < len; i++) { 
		  				jq('#readyMarkInvoice').text("");
		  				if(data[i].status!=''){
		  					jq('#readyMarkInvoice').removeClass( "isa_error" );
		  					jq('#readyMarkInvoice').text("[Status: " + data[i].status + "]");
		  				}else{ 
		  					if(data[i].errMsg!=''){
		  						jq('#readyMarkInvoice').addClass( "isa_error" );
		  						jq('#readyMarkInvoice').text("[ERROR: " + data[i].errMsg + "]");
	  						}
		  				}
					}
			  	  },
			  	  error: function() {
			  	    alert('Error loading ...');
			  	  }
			  	}); 
	  		});
	  	
	  	  //by beregning button
	  	  jq('#recalculateButton').click(function() {
	  		  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
	  		  window.location = "tror_mainorderlandimport_invoice_recalculate.do?action=doInit&heavd=" + jq('#heavd').val() + "&heopd=" + jq('#heopd').val();
	  		  
		  });
	  	
	});
	
	
    /*
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
	*/
  	
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
  			jq('#fakunr').val("");
  			jq('#fadocnB').val("");
			jq('#faavdr').val("");
			//
			jq('#falevn').val("");
			jq('#fadocnB').val("");
			jq('#faavdr').val("");
			jq('#fakduk').val("");
			jq('#facu33').val("");
			jq('#fabelu').val("");
			//
			jq('#fakunr').val("");
			jq('#facd11').val("");
  			
  			//aspects
  			jq('#editLineNr').text("");
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
				//the rest
				jq('#fask').val("");jq('#fask').val(data[i].fask);
				jq('#favk').val("");jq('#favk').val(data[i].favk);
				jq('#fabelv').val("");jq('#fabelv').val(data[i].fabelv);
				jq('#faval').val("");jq('#faval').val(data[i].faval);
				jq('#fakdm').val("");jq('#fakdm').val(data[i].fakdm);
				jq('#fakunr').val("");jq('#fakunr').val(data[i].fakunr);
				jq('#fadocnB').val("");jq('#fadocnB').val(data[i].fadocnB);
				jq('#faavdr').val("");jq('#faavdr').val(data[i].faavdr);
				jq('#falevn').val("");jq('#falevn').val(data[i].falevn);
				jq('#fakduk').val("");jq('#fakduk').val(data[i].fakduk);
				jq('#facu33').val("");jq('#facu33').val(data[i].facu33);
				jq('#fabelu').val("");jq('#fabelu').val(data[i].fabelu);
				jq('#facd11').val("");jq('#facd11').val(data[i].facd11);
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
				*/
				jq('#fask').focus();
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

	
	jq(document).ready(function() {
	     //In order for F-Keys to work in tabs ... 
		
		 jq('#fask').focus();
	});
	//-------------------
    //Datatables jquery
    //-------------------
    //private function
	/* DATATABLE not good in this UC since we have SUBTOTALS that ARE fucked up in the order ...
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
  		  "tabIndex":   -1,
  		  "order": [[ 2, "asc" ], [ 0, "asc" ]],
  		  "scrollCollapse":  true,
  		  "lengthMenu": [ 25, 50]
  	  });
      //event on input field for search
      jq('input.tblInvoices_filter').on( 'keyup click', function () {
      		filterGlobal();
      });
  	
    });
	*/
	