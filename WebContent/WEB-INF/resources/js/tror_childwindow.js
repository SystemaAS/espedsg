	//============================================================
	//General functions for EBOOKING Child Search windows
	//============================================================
	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict();
  	var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";
  	
  	function setBlockUI(element){
  	  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
    }
  	
  	jq(function() {
  		//Triggers on drag-and-drop to upload
		jq('#file').change(function(){
			jq('#uploadFileForm').submit();
			
		});
  	});
  	
  	//Vedlikehold adresser
  	jq(function() {
  		/*
	  	jq('#deliveryAdrMaintButton').click(function() {
	  		window.open('ebooking_childwindow_customer_addresses_vedlikehold.do?action=doInit', "deliveryAdrMaintWin", "top=300px,left=300px,height=400px,width=400px,scrollbars=yes,status=no,location=no");
	    });
	  	
	    jq('#deliveryAdrMaintButton').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#deliveryAdrMaintButton').click();
			}
	    });
	    */
	    
  	});
  	
  	//avd-childwindow
	jq(function() {
		jq('#avdList').on('click', 'td', function(){
			var id = this.id;
			  var record = id.split('@');
			  var id = record[0].replace("id_","");
			  var name = record[1].replace("name_","");
			  var caller= jq("#ctype").val()
			  /*if(record.length>3){
				 caller = record[3].replace("caller_","");
			  }*/
			  
			  //addressing a parent field from this child window
			  if( caller =='tror_landimport'){ 
				  opener.jq('#heavd').val(id);
				  jq('#heavd').focus();
			  }else if(caller =='todo'){ 
				  //opener.jq('#heavd').val(id);
			  }
			  
			  //close child window
			  window.close();
	  });
	});
  	
  	
	//Customer
	jq(function() {
		jq('#customerList').on('click', 'td', function(){
		  var id = this.id;
		  var record = id.split('@');
		  var kundNr = record[0].replace("kundnr_", "");
		  var navn = record[1].replace("navn_", "");
		  var adr1 = record[2].replace("adr1_", "");
		  var adr2 = record[3].replace("adr2_", "");
		  var postnrsted = record[4].replace("postnrsted_", "");
		  var kundName = record[5].replace("kundname_", "");
		  var kundAddress = record[6].replace("kundaddress_", "");
		  
		  
		  //alert(kundNr + " type:" + jq('#ctype').val() + "-->customerName:" + customerName);
		  //addressing a parent field from this child window
		  if(jq('#ctype').val()=='s'){
			  //shipper/consignor 	
			  opener.jq('#hekns').val(kundNr);
			  
			  if(opener.jq('#xfakBet').val() != 'M'){
				  opener.jq('#heknsf').val(kundNr);
		  	  }
			  opener.jq('#whenas').val(kundName + " - " + kundAddress);
			  
			  opener.jq('#henas').val(navn);
			  opener.jq('#henas').removeClass( "isa_error" );
			  
			  opener.jq('#heads1').val(adr1);
			  opener.jq('#heads2').val(adr2);
			  opener.jq('#heads3').val(postnrsted);
			  opener.jq('#hekns').focus();
			  
		  }else if(jq('#ctype').val()=='a'){
			  //agent  
			  opener.jq('#trknfa').val(kundNr);
			  opener.jq('#trknfa').focus();
		  
		  }else if(jq('#ctype').val()=='c'){
			  //consignee
			  opener.jq('#heknk').val(kundNr);
			  if(opener.jq('#xfakBet').val() == 'M'){
				  opener.jq('#heknkf').val(kundNr);
		  	  }
			  opener.jq('#whenak').val(kundName + " - " + kundAddress);
			  
			  opener.jq('#henak').val(navn);
			  opener.jq('#henak').removeClass( "isa_error" );
			  
			  opener.jq('#headk1').val(adr1);
			  opener.jq('#headk2').val(adr2);
			  opener.jq('#headk3').val(postnrsted);
			  opener.jq('#heknk').focus();
			  
		  }else if(jq('#ctype').val()=='il'){
			  opener.jq('#fakunr').val(kundNr);
			  
		  }else if(jq('#ctype').val()=='sf'){
			  //selgers fakturapart  
			  opener.jq('#heknsf').val(kundNr);
			  opener.jq('#whenasf').val(kundName + " - " + kundAddress);
			  opener.jq('#heknsf').focus();
			  
		  }else if(jq('#ctype').val()=='kf'){
			  //kjøpers fakturapart 
			  opener.jq('#heknkf').val(kundNr);
			  opener.jq('#whenakf').val(kundName + " - " + kundAddress);
			  opener.jq('#heknkf').focus();
		  
		  }
		  
		  //close child window
		  window.close();
	  });
	});
	
	jq(function() {
		jq('#customerAddressesList').on('click', 'td', function(){
		  var id = this.id;
		  var record = id.split('@');
		  var kundNr = record[0].replace("vadrnr_", "");
		  var navn = record[1].replace("navn_", "");
		  var adr1 = record[2].replace("adr1_", "");
		  var adr2 = record[3].replace("adr2_", "");
		  var postnrsted = record[4].replace("postnrsted_", "");
		  //In the case of fraktbrev we must use this 2 fields in the seller address side
		  var postnr = record[5].replace("postnr_", "");
		  var sted = record[6].replace("sted_", "");
		  
		  //alert(kundNr + " type:" + jq('#ctype').val() + "-->customerName:" + customerName);
		  //addressing a parent field from this child window
		  if(jq('#ctype').val()=='s'){
			  //shipper/consignor 	
			  //opener.jq('#hekns').val(kundNr); //this should not override the customer number
			  opener.jq('#henas').val(navn);
			  opener.jq('#heads1').val(adr1);
			  opener.jq('#heads2').val(adr2);
			  opener.jq('#heads3').val(postnrsted);
			  opener.jq('#henas').focus();
			  
		  }else if(jq('#ctype').val()=='c'){
			  //consignee
			  //opener.jq('#heknk').val(kundNr); //this should not override the customer number
			  opener.jq('#henak').val(navn);
			  opener.jq('#headk1').val(adr1);
			  opener.jq('#headk2').val(adr2);
			  opener.jq('#headk3').val(postnrsted);
			  opener.jq('#henak').focus();
		  
		  }else if(jq('#ctype').val()=='tror_seadr'){
			  //consignor
			  //opener.jq('#heknk').val(kundNr); //this should not override the customer number
			  opener.jq('#henas').val(navn);
			  opener.jq('#heads1').val(adr1);
			  opener.jq('#heads2').val(adr2);
			  opener.jq('#heads3').val(postnrsted);
			  opener.jq('#henas').focus();
		  
		  }else if(jq('#ctype').val()=='tror_byadr'){
			  //consignee
			  //opener.jq('#heknk').val(kundNr); //this should not override the customer number
			  opener.jq('#henak').val(navn);
			  opener.jq('#headk1').val(adr1);
			  opener.jq('#headk2').val(adr2);
			  opener.jq('#headk3').val(postnrsted);
			  opener.jq('#henak').focus();
		  
		  }else if(jq('#ctype').val()=='tror_byadr_fb'){
			  //fraktbrevs consignee
			  opener.jq('#dfnavm').val(navn);
			  opener.jq('#dfad1m').val(adr1);
			  opener.jq('#dfad2m').val(adr2);
			  opener.jq('#dfad3m').val(postnrsted);
			  opener.jq('#dfnavm').focus();
			  
		  }else if(jq('#ctype').val()=='tror_seadr_fb'){
			  //fraktbrevs consignor uses the same buyer_addresses childwindow. IMPORT NOTE!: It is not like the sellers_addresses in main order
			  opener.jq('#dfnavs').val(navn);
			  opener.jq('#dfad1s').val(adr1);
			  opener.jq('#dfad2s').val(adr2);
			  opener.jq('#dfad3s').val(sted);
			  opener.jq('#dfpnls').val(postnr);
			  //focus
			  opener.jq('#dfnavs').focus();
		  }
		  
		  
		  //close child window
		  window.close();
	  });
	});
    //Transporttypes
	jq(function() {
		jq('#transporttypesList').on('click', 'td', function(){
			var id = this.id;
			  var record = id.split('@');
			  var id = record[0].replace("id_","");
			  var name = record[1].replace("name_","");
			  var caller= jq("#ctype").val()
			  /*if(record.length>3){
				 caller = record[3].replace("caller_","");
			  }*/
			  
			  //addressing a parent field from this child window
			  if( caller =='tror_landimport'){ 
				  opener.jq('#hekdtm').val(id);
				  jq('#hekdtm').focus();
			  }else if(caller =='todo'){ 
				  //opener.jq('#hetri').val(countryCode);
				  //opener.jq('#hesdt').val(postalCode);
				  //opener.jq('#hesdt').removeClass("text11RedBold");
				  //opener.jq('#OWNwppns2').val(city);
			  }
			  
			  //close child window
			  window.close();
	  });
	});
	//Carrier
	jq(function() {
		jq('#carrierList').on('click', 'td', function(){
		  var id = this.id;
		  //id_${record.vmtran}@vmtrku_${record.vmtrku}@vmnavn_${record.vmnavn}@vmtrle_${record.vmtrle}@vmincr_${record.vmincr}@counter_${counter.count}
		  var record = id.split('@');
		  var id = record[0].replace("id_", "");
		  var vmtrku = record[1].replace("vmtrku_", "");
		  var vmnavn = record[2].replace("vmnavn_", "");
		  var vmtrle = record[3].replace("vmtrle_", "");
		  var todo = record[4].replace("todo_", "");
		  var caller= jq("#ctype").val()
		 
		  //carrier 
		  if( caller =='tror_car'){ 
			  opener.jq('#heknt').val(id);
			  opener.jq('#ownheknt').val(vmnavn);
			  opener.jq('#heknt').focus();
			  
		  }else if( caller =='tror_car_fb'){ 
			  opener.jq('#dftran').val(id);
			  opener.jq('#dfnat').val(vmnavn);
			  opener.jq('#dfknm').val(vmtrku);
			  opener.jq('#dftran').focus();
		  }
		  
		  //close child window
		  window.close();
		});
	});
	
  	
	//Select Postal Code From and To ?
	jq(function() {
		jq('#postalCodeList').on('click', 'td', function(){
			var id = this.id;
			  var record = id.split('@');
			  var postalCode = record[0].replace("postalcode_","");
			  var countryCode = record[1].replace("country_","");
			  var city = record[2].replace("city_","");
			  var caller= jq("#ctype").val()
			  /*if(record.length>3){
				 caller = record[3].replace("caller_","");
			  }*/
			  
			  //addressing a parent field from this child window
			  if(opener.jq('#hesdf').length && caller =='hesdf'){ //only way to check if field exists. (Order)
				  opener.jq('#helka').val(countryCode);
				  opener.jq('#hesdf').val(postalCode);
				  opener.jq('#hesdf').removeClass("text11RedBold");
				  opener.jq('#OWNwppns1').val(city);
			  }else if(opener.jq('#hesdt').length && caller =='hesdt'){ //since there are several postnr callers in the same JSP
				  opener.jq('#hetri').val(countryCode);
				  opener.jq('#hesdt').val(postalCode);
				  opener.jq('#hesdt').removeClass("text11RedBold");
				  opener.jq('#OWNwppns2').val(city);
			  }
			  
			  //close child window
			  window.close();
		});
		
		jq('#postalCodePonrnList').on('click', 'td', function(){
			var id = this.id;
			  var record = id.split('@');
			  var id = record[0].replace("id_","");
			  var name = record[1].replace("name_","");
			  var caller= jq("#ctype").val()
			  
			  if(caller =='landimport_fb_dfpoul'){ 
				  opener.jq('#dfpoul').val(id);
				  opener.jq('#dfadul').val(name);
				  opener.jq('#dfpoul').focus();
			  }
			  
			  //close child window
			  window.close();
	  });
		
	});
	
	jq(function() {
		jq('#tollstedList').on('click', 'td', function(){
			var id = this.id;
			  var record = id.split('@');
			  var id = record[0].replace("id_","");
			  var name = record[1].replace("name_","");
			  var caller= jq("#ctype").val()
			  /*if(record.length>3){
				 caller = record[3].replace("caller_","");
			  }*/
			  
			  //addressing a parent field from this child window
			  if( caller =='landimport' || caller =='landimport_fb'){ 
				  opener.jq('#dftoll').val(id);
				  opener.jq('#dftoll').focus();
				  
			  }
			  
			  //close child window
			  window.close();
	  });
	});
	
	jq(function() {
		//incoterms
		jq('#incotermsList').on('click', 'td', function(){
			var id = this.id;
			  var record = id.split('@');
			  var id = record[0].replace("id_","");
			  var name = record[1].replace("name_","");
			  var caller= jq("#ctype").val();
			  /*if(record.length>3){
				 caller = record[3].replace("caller_","");
			  }*/
			  
			  //addressing a parent field from this child window
			  if( caller =='landimport'){ 
				  opener.jq('#hefr').val(id);
				  //opener.jq('#TODOname').val(name);
				  opener.jq('#hefr').focus();
				  
			  }else if(caller =='todo'){ 
				  //opener.jq('#hetri').val(countryCode);
				  //opener.jq('#hesdt').val(postalCode);
				  //opener.jq('#hesdt').removeClass("text11RedBold");
				  //opener.jq('#OWNwppns2').val(city);
			  }
			  
			  //close child window
			  window.close();
		});
		//Oppdragstype 
		jq('#oppdragstypeList').on('click', 'td', function(){
			var id = this.id;
			  var record = id.split('@');
			  var id = record[0].replace("id_","");
			  var name = record[1].replace("name_","");
			  var caller= jq("#ctype").val()
			  /*if(record.length>3){
				 caller = record[3].replace("caller_","");
			  }*/
			  
			  //addressing a parent field from this child window
			  if( caller =='landimport'){ 
				  opener.jq('#heot').val(id);
				  //opener.jq('#TODOname').val(name);
				  opener.jq('#heot').focus();
				  
			  }else if(caller =='todo'){ 
				  //opener.jq('#hetri').val(countryCode);
				  //opener.jq('#hesdt').val(postalCode);
				  //opener.jq('#hesdt').removeClass("text11RedBold");
				  //opener.jq('#OWNwppns2').val(city);
			  }
			  //close child window
			  window.close();
		});
		//product codes 
		jq('#produktList').on('click', 'td', function(){
			var id = this.id;
			  var record = id.split('@');
			  var id = record[0].replace("id_","");
			  var name = record[1].replace("name_","");
			  var caller= jq("#ctype").val()
			  /*if(record.length>3){
				 caller = record[3].replace("caller_","");
			  }*/
			  
			  //addressing a parent field from this child window
			  if( caller =='landimport'){ 
				  opener.jq('#hekdpl').val(id);
				  opener.jq('#hekdpl').focus();
				  
			  }else if(caller =='landimport_fb'){ 
				  opener.jq('#dfprok').val(id);
				  opener.jq('#dfprok').focus();
				  
			  }
			  //close child window
			  window.close();
		});
		
	});
	
	
	//Select Load/Unload place
	jq(function() {
		jq('#loadUnloadPlacesList').on('click', 'td', function(){
		  var id = this.id;
		  var record = id.split('@');
		  var loadPlaceCode = record[0].replace("code_", "");
		  var loadPlaceName = record[1].replace("loadplacename_", "");
		  var caller = record[2].replace("caller_", "");
		  if(opener.jq('#hesdl').length && caller=='hesdl'){ //only way to check if field exists.(Order)
			  opener.jq('#hesdl').val(loadPlaceName);
		  }else if(opener.jq('#hesdla').length && caller=='hesdla'){ //only way to check if field exists.(Order)
			  opener.jq('#hesdla').val(loadPlaceName);
		  }
		  //close child window
		  window.close();
	  });
	});
	
	//Select unit-of-measure codes
	jq(function() {
		jq('#uomList').on('click', 'td', function(){
		  var id = this.id;
		  var record = id.split('@');
		  var id = record[0].replace("id_", "");
		  var name = record[1].replace("name_", "");
		  var caller= jq("#ctype").val();
		  
		  if ( caller =='tror_landimport_e1'){ 
			  opener.jq('#ownEnhet1').val(id);
			  opener.jq('#ownEnhet1').focus();
			  
		  }else if(caller =='tror_landimport_e2'){
			  opener.jq('#ownEnhet2').val(id);
			  opener.jq('#ownEnhet2').focus();
			  
		  } 
		  
		 
		  //close child window
		  window.close();
		  
	  });
	});
	
	//Select loading places
	jq(function() {
		jq('#loadunloadList').on('click', 'td', function(){
		  var id = this.id;
		  var record = id.split('@');
		  var id = record[0].replace("id_", "");
		  var name = record[1].replace("name_", "");
		  var caller= jq("#ctype").val();
		  
		  if ( caller =='tror_landimport' || caller =='tror_landexport'){ 
			  opener.jq('#hesdl').val(name);
			  opener.jq('#hesdl').focus();
			  
		  }else if(caller =='todo'){
			 //TODO
		  } 
		  //close child window
		  window.close();
		  
	  });
	});
	
	
	//Select merkelapp
	jq(function() {
		jq('#merkelappList').on('click', 'td', function(){
		  var id = this.id;
		  var record = id.split('@');
		  var id = record[0].replace("id_", "");
		  var name = record[1].replace("name_", "");
		  var caller= jq("#ctype").val();
		  
		  if ( caller =='tror_dfkdme_fb'){ 
			  opener.jq('#dfkdme').val(id);
			  opener.jq('#dfkdme').focus();
			  
		  }else if(caller =='todo'){
			 //TODO
		  } 
		  //close child window
		  window.close();
		  
	  });
	});
	

	
	//======================
    //Datatables jquery 
    //======================
    //private function [Filters]
	
    function filterPostNr () {
        jq('#postalCodeList').DataTable().search(
    		jq('#postalCodeList_filter').val()
        ).draw();
    }
    function filterPostNrPonrn () {
        jq('#postalCodePonrnList').DataTable().search(
    		jq('#postalCodePonrnList_filter').val()
        ).draw();
    }
    
    /*
    function filterPostNrTo () {
        jq('#postNrToList').DataTable().search(
    		jq('#postNrToList_filter').val()
        ).draw();
    }*/
	

    function filterCustomerList (){
        jq('#customerList').DataTable().search(
    		jq('#customerList_filter').val()
        ).draw();
    }
    
    function filterCustomerAddressesList (){
        jq('#customerAddressesList').DataTable().search(
    		jq('#customerAddressesList_filter').val()
        ).draw();
    }
    function filterCarrierList (){
        jq('#carrierList').DataTable().search(
    		jq('#carrierList_filter').val()
        ).draw();
    }
    function filterLoadUnloadPlacesList (){
        jq('#loadUnloadPlacesList').DataTable().search(
    		jq('#loadUnloadPlacesList_filter').val()
        ).draw();
    }
    
    function filterTollstedList (){
        jq('#tollstedList').DataTable().search(
    		jq('#tollstedList_filter').val()
        ).draw();
    }
    function filterIncotermsList (){
        jq('#incotermsList').DataTable().search(
    		jq('#incotermsList_filter').val()
        ).draw();
    }
    function filterOppdragstypeList (){
        jq('#oppdragstypeList').DataTable().search(
    		jq('#oppdragstypeList_filter').val()
        ).draw();
    }
    function filterProductcodeList (){
        jq('#produktList').DataTable().search(
    		jq('#produktList_filter').val()
        ).draw();
    }
    
    function filterAvdList (){
        jq('#avdList').DataTable().search(
    		jq('#avdList_filter').val()
        ).draw();
    }
    function filterTransporttypesList (){
        jq('#transporttypesList').DataTable().search(
    		jq('#transporttypesList_filter').val()
        ).draw();
    }
    
    function filterUomList (){
        jq('#uomList').DataTable().search(
    		jq('#uomList_filter').val()
        ).draw();
    }
    
    function LoadunloadList (){
        jq('#loadunloadList').DataTable().search(
    		jq('#loadunloadList_filter').val()
        ).draw();
    }
    
    
    /*
    function filterPackingCodesList (){
        jq('#packingCodesList').DataTable().search(
    		jq('#packingCodesList_filter').val()
        ).draw();
    }
    function filterDangerousGoodsList (){
        jq('#dangerousGoodsList').DataTable().search(
    		jq('#dangerousGoodsList_filter').val()
        ).draw();
    }
    */
    //Init datatables
    jq(document).ready(function() {
      var lang = jq('#language').val(); 	
      //--------------------------
      //table [PostNr]
	  //--------------------------
	  jq('#postalCodeList').dataTable( {
		  "dom": '<"top"fli>rt<"bottom"p><"clear">',
		  "lengthMenu": [ 50, 75, 100 ],
		  "language": { "url": getLanguage(lang) }
	  });
	  //event on input field for search
	  jq('input.postalCodeList_filter').on( 'keyup click', function () {
		  filterPostNr();
	  });
	  
	  //--------------------------
      //table [PostNr PONRN]
	  //--------------------------
	  jq('#postalCodePonrnList').dataTable( {
		  "dom": '<"top"fli>rt<"bottom"p><"clear">',
		  "lengthMenu": [ 50, 75, 100 ],
		  "language": { "url": getLanguage(lang) }
	  });
	  //event on input field for search
	  jq('input.postalCodePonrnList_filter').on( 'keyup click', function () {
		  filterPostNrPonrn();
	  });
	  
	  //-----------------------
	  //tables [PostNr To]
	  //-----------------------
	  /*
	  jq('#postNrToList').dataTable( {
		  "dom": '<"top"fli>rt<"bottom"p><"clear">',
		  "lengthMenu": [ 50, 75, 100 ],
		  "language": { "url": getLanguage(lang) }
	  });
	  //event on input field for search
	  jq('input.postNrToList_filter').on( 'keyup click', function () {
		  filterPostNrFrom();
	  });
	  */
	  //-----------------------
	  //tables [Customer No.]
	  //-----------------------
	  jq('#customerList').dataTable( {
		  "dom": '<"top"fli>rt<"bottom"p><"clear">',
		  "lengthMenu": [ 50, 75, 100 ],
		  "language": { "url": getLanguage(lang) }
	  });
	  //event on input field for search
	  jq('input.customerList_filter').on( 'keyup click', function () {
		  filterCustomerList();
	  });
	  //related table Customer Addresses
	  jq('#customerAddressesList').dataTable( {
		  "dom": '<"top"fli>rt<"bottom"p><"clear">',
		  "lengthMenu": [ 50, 75, 100 ],
		  "order": [[ 1, "asc" ]],
		  "language": { "url": getLanguage(lang) }
	  });
	  //event on input field for search
	  jq('input.customerAddressesList_filter').on( 'keyup click', function () {
		  filterCustomerAddressesList();
	  });

	  //-----------------------
	  //tables [Carrier No.]
	  //-----------------------
	  jq('#carrierList').dataTable( {
		  "dom": '<"top"fli>rt<"bottom"p><"clear">',
		  "lengthMenu": [ 20, 50, 75, 100 ],
		  "language": { "url": getLanguage(lang) }
	  });
	  //event on input field for search
	  jq('input.carrierList_filter').on( 'keyup click', function () {
		  filterCarrierList();
	  });
	  
	  //-----------------------
	  //tables [Tollsted]
	  //-----------------------
	  jq('#tollstedList').dataTable( {
		  "dom": '<"top"fli>rt<"bottom"p><"clear">',
		  "lengthMenu": [ 10, 20 ],
		  "order": [[ 1, "asc" ]],
		  "language": { "url": getLanguage(lang) }
	  });
	  //event on input field for search
	  jq('input.tollstedList_filter').on( 'keyup click', function () {
		  filterTollstedList();
	  });
	  
	  
	  
	  //-----------------------
	  //tables [avd]
	  //-----------------------
	  jq('#avdList').dataTable( {
		  "dom": '<"top"fli>rt<"bottom"p><"clear">',
		  "lengthMenu": [ 15, 75, 100 ],
		  "language": { "url": getLanguage(lang) }
	  });
	  //event on input field for search
	  jq('input.avdList_filter').on( 'keyup click', function () {
		  filterAvdList();
	  });
	  
	  
	  //-----------------------
	  //tables [Tranporttyp]
	  //-----------------------
	  jq('#transporttypesList').dataTable( {
		  "dom": '<"top"fli>rt<"bottom"p><"clear">',
		  "lengthMenu": [ 10, 20, 75, 100 ],
		  "language": { "url": getLanguage(lang) }
	  });
	  //event on input field for search
	  jq('input.transporttypesList_filter').on( 'keyup click', function () {
		  filterTransporttypesList();
	  });
	  
	  
	  //-----------------------
	  //tables [Incoterms]
	  //-----------------------
	  jq('#incotermsList').dataTable( {
		  "dom": '<"top"fli>rt<"bottom"p><"clear">',
		  "lengthMenu": [ 10, 20, 75, 100 ],
		  "language": { "url": getLanguage(lang) }
	  });
	  //event on input field for search
	  jq('input.incotermsList_filter').on( 'keyup click', function () {
		  filterIncotermsList();
	  });
	  
	  //-----------------------
	  //tables [Oppdragstype]
	  //-----------------------
	  jq('#oppdragstypeList').dataTable( {
		  "dom": '<"top"fli>rt<"bottom"p><"clear">',
		  "lengthMenu": [ 10, 20, 75, 100 ],
		  "language": { "url": getLanguage(lang) }
	  });
	  //event on input field for search
	  jq('input.oppdragstypeList_filter').on( 'keyup click', function () {
		  filterOppdragstypeList();
	  });
	  
	  //-----------------------
	  //tables [Product codes]
	  //-----------------------
	  jq('#produktList').dataTable( {
		  "dom": '<"top"fli>rt<"bottom"p><"clear">',
		  "lengthMenu": [ 10, 20, 75, 100 ],
		  "language": { "url": getLanguage(lang) }
	  });
	  //event on input field for search
	  jq('input.produktList_filter').on( 'keyup click', function () {
		  filterProduktList();
	  });

	  //------------------------------
	  //tables [Load/Unload places]
	  //----------------------------
	  jq('#loadUnloadPlacesList').dataTable( {
		  "dom": '<"top"fli>rt<"bottom"p><"clear">',
		  "lengthMenu": [ 50, 75, 100 ],
		  "language": { "url": getLanguage(lang) }
	  });
	  //event on input field for search
	  jq('input.loadUnloadPlacesList_filter').on( 'keyup click', function () {
		  filterLoadUnloadPlacesList();
	  });
	  
	  //------------------------------
	  //tables [uom codes]
	  //----------------------------
	  jq('#uomList').dataTable( {
		  "dom": '<"top"fli>rt<"bottom"p><"clear">',
		  "lengthMenu": [ 10, 20, 50 ],
		  "language": { "url": getLanguage(lang) }
	  });
	  //event on input field for search
	  jq('input.uomList_filter').on( 'keyup click', function () {
		  filterUomList();
	  });
	  
	  //------------------------------
	  //tables [uom codes]
	  //----------------------------
	  jq('#loadunloadList').dataTable( {
		  "dom": '<"top"fli>rt<"bottom"p><"clear">',
		  "lengthMenu": [ 10, 20, 50 ],
		  "language": { "url": getLanguage(lang) }
	  });
	  //event on input field for search
	  jq('input.loadunloadList_filter').on( 'keyup click', function () {
		  filterLoadunloadList();
	  });
	  
	  //------------------------------
	  //tables merkelappList
	  //----------------------------
	  jq('#merkelappList').dataTable( {
		  "dom": '<"top"fli>rt<"bottom"p><"clear">',
		  "lengthMenu": [ 10, 20, 50 ],
		  "language": { "url": getLanguage(lang) }
	  });
	  
	  /*
	  //------------------------------
	  //tables [packing codes]
	  //----------------------------
	  jq('#packingCodesList').dataTable( {
		  "dom": '<"top"fli>rt<"bottom"p><"clear">',
		  "lengthMenu": [ 50, 75, 100 ],
		  "language": { "url": getLanguage(lang) }
	  });
	  //event on input field for search
	  jq('input.packingCodesList_filter').on( 'keyup click', function () {
		  filterPackingCodesList();
	  });
	  
	//------------------------------
	  //tables [dangerous goods]
	  //----------------------------
	  jq('#dangerousGoodsList').dataTable( {
		  "dom": '<"top"fli>rt<"bottom"p><"clear">',
		  "lengthMenu": [ 50, 75, 100 ],
		  "language": { "url": getLanguage(lang) }
	  });
	  //event on input field for search
	  jq('input.dangerousGoodsList_filter').on( 'keyup click', function () {
		  filterDangerousGoodsList();
	  });
	  */
      
    });
  	