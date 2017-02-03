	//============================================================
	//General functions for EBOOKING Child Search windows
	//============================================================
	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict();
  	
  	
  	jq(function() {
  		//Triggers on drag-and-drop to upload
		jq('#file').change(function(){
			jq('#uploadFileForm').submit();
			
		});
  	});
  	
  //Customer
	jq(function() {
		jq('#customerList').on('click', 'td', function(){
		  var id = this.id;
		  var record = id.split('@');
		  var kundNr = record[0].replace("kundnr_", "");
		  var customerName = record[1].replace("navn_", "");
		  var aktkod = record[2].replace("aktkod_", "");
		  
		  //alert(kundNr + " type:" + jq('#ctype').val() + "-->customerName:" + customerName);
		  //addressing a parent field from this child window
		  if(jq('#ctype').val()=='s'){
			  //shipper/consignor 	
			  opener.jq('#hekns').val(kundNr);
			  opener.jq('#hekns').focus();
		  }else if(jq('#ctype').val()=='a'){
			  //agent  
			  opener.jq('#trknfa').val(kundNr);
			  opener.jq('#trknfa').focus();
		  }else if(jq('#ctype').val()=='c'){
			  //consignee
			  opener.jq('#heknk').val(kundNr);
			  opener.jq('#heknk').focus();
		  }else if(jq('#ctype').val()=='il'){
			  //invoice line (on invoice jsp)
			  if(aktkod == 'I' && opener.jq('#fask').val() == 'X'){
				  //Not valid. Do nothing!
			  }else{
				  opener.jq('#fakunr').val(kundNr);
			  }
		  }
		  
		  //close child window
		  window.close();
	  });
	});
  	
	//Select Postal Code From
	jq(function() {
		jq('#postNrFromList').on('click', 'td', function(){
			var id = this.id;
			  var record = id.split('@');
			  var postalCode = record[0].replace("postalcode_","");
			  var countryCode = record[1].replace("country_","");
			  var city = record[2].replace("city_","");
			  var caller="init";
			  if(record.length>3){
				 caller = record[3].replace("caller_","");
			  }
			  
			  //addressing a parent field from this child window
			  if(opener.jq('#hesdf').length && caller=='hesdf'){ //only way to check if field exists. (Order)
				  opener.jq('#helka').val(countryCode);
				  opener.jq('#hesdf').val(postalCode);
				  opener.jq('#hesdf').removeClass("text11RedBold");
				  opener.jq('#OWNwppns1').val(city);
			  }else if(opener.jq('#hesdt').length && caller=='hesdt'){ //since there are several postnr callers in the same JSP
				  opener.jq('#hetri').val(countryCode);
				  opener.jq('#hesdt').val(postalCode);
				  opener.jq('#hesdt').removeClass("text11RedBold");
				  opener.jq('#OWNwppns2').val(city);
			  }
			  
			  //close child window
			  window.close();
	  });
	});
	
	//Select Postal Code To
	jq(function() {
		jq('#postNrToList').on('click', 'td', function(){
			var id = this.id;
			  var record = id.split('@');
			  var postalCode = record[0].replace("postalcode_","");
			  var countryCode = record[1].replace("country_","");
			  var city = record[2].replace("city_","");
			  var caller="init";
			  if(record.length>3){
				 caller = record[3].replace("caller_","");
			  }
			  //addressing a parent field from this child window
			  if(opener.jq('#tustet').length){ //only way to check if field exists. (Trip)
				  opener.jq('#tusont').val(countryCode);
				  opener.jq('#tustet').val(postalCode);
				  opener.jq('#tusdt').val(city);
			  }
			  if(opener.jq('#hesdt').length && caller=='hesdt'){ //only way to check if field exists.(Order)
				  opener.jq('#hetri').val(countryCode);
				  opener.jq('#hesdt').val(postalCode);
				  opener.jq('#hesdt').removeClass("text11RedBold");
				  opener.jq('#OWNwppns2').val(city);
			  }else if(opener.jq('#hesdvt').length && caller=='hesdvt'){ //only way to check if field exists.(Order)
				  opener.jq('#helkk').val(countryCode);
				  opener.jq('#hesdvt').val(postalCode);
				  opener.jq('#hesdvt').removeClass("text11RedBold");
				  opener.jq('#OWNwppns4').val(city);
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
	
	//Select Dangerous code.
	jq(function() {
		jq('#dangerousGoodsList').on('click', 'td', function(){
		  var id = this.id;
		  var record = id.split('@');
		  if(jq('#callerLineCounter').val()!=''){
			  var unnr = record[0].replace("unnr_", "");
			  var embg = record[1].replace("embg_", "");
			  var indx = record[2].replace("indx_", "");
			  var fakt = record[3].replace("fakt_", "");
			  var callerLineCounterStr = jq('#callerLineCounter').val();
			  var callerLineCounter = 0;
			  if(callerLineCounterStr!=""){ callerLineCounter = parseInt(callerLineCounterStr);}
			  //alert(callerLineCounter);
			  //addressing a parent field from this child window
			  opener.jq('#ffunnr_' + callerLineCounter).val(unnr);opener.jq('#ffembg_' + callerLineCounter).val(embg);
			  opener.jq('#ffindx_' + callerLineCounter).val(indx);
			  //ADR calculation
			  fakt = parseInt(fakt);
			  var units = 0;
			  if(opener.jq('#ffante_' + callerLineCounter).val()!=''){ units = parseInt(opener.jq('#ffante_' + callerLineCounter).val()); }
			  var adr = fakt * units;
			  //alert(adr);
			  if(adr>0){ opener.jq('#ffpoen_' + callerLineCounter).val(adr); }
			  
			  //cosmetics
			  opener.jq('#ffunnr_' + callerLineCounter).removeClass("isa_warning");opener.jq('#ffembg_' + callerLineCounter).removeClass("isa_warning");
			  opener.jq('#ffindx_' + callerLineCounter).removeClass("isa_warning");
			  opener.jq('#ffunnr_' + callerLineCounter).removeClass("isa_error");opener.jq('#ffembg_' + callerLineCounter).removeClass("isa_error");
			  opener.jq('#ffindx_' + callerLineCounter).removeClass("isa_error");
		  }else{
			  var unnr = record[0].replace("unnr", "");
			  var embg = record[1].replace("embg", "");
			  var indx = record[2].replace("indx", "");
			  var fakt = record[3].replace("fakt", "");
			  //addressing a parent field from this child window
			  opener.jq('#ffunnr').val(unnr);opener.jq('#ffembg').val(embg);
			  opener.jq('#ffindx').val(indx);opener.jq('#ownAdrFaktNewLine').val(fakt);
			  
			  //cosmetics
			  opener.jq('#ffunnr' + callerLineCounter).removeClass("isa_warning");opener.jq('#ffembg' + callerLineCounter).removeClass("isa_warning");
			  opener.jq('#ffindx' + callerLineCounter).removeClass("isa_warning");
			  opener.jq('#ffunnr' + callerLineCounter).removeClass("isa_error");opener.jq('#ffembg' + callerLineCounter).removeClass("isa_error");
			  opener.jq('#ffindx' + callerLineCounter).removeClass("isa_error");
		  }
		  //close child window
		  window.close();
		  
	  });
	});
	
	//Select packing codes
	jq(function() {
		jq('#packingCodesList').on('click', 'td', function(){
		  var id = this.id;
		  var record = id.split('@');
		  if(jq('#callerLineCounter').val()!=''){
			  var fvpakn = record[0].replace("kode_", "");
			  var text = record[1].replace("text_", "");
			  var fvlen = record[2].replace("len_", "");
			  var fvbrd = record[3].replace("brd_", "");
			  var fvhoy = record[4].replace("hoy_", "");
			  var fvlm = record[5].replace("lm_", "");
			  var fvlm2 = record[6].replace("lm2_", "");
			  
			  var callerLineCounterStr = jq('#callerLineCounter').val();
			  var callerLineCounter = 0;
			  if(callerLineCounterStr!=""){ callerLineCounter = parseInt(callerLineCounterStr);}
			  //alert(callerLineCounter);
			  //addressing a parent field from this child window
			  opener.jq('#fvpakn_' + callerLineCounter).val(fvpakn);
			  if(opener.jq('#fvvt_' + callerLineCounter).val()==''){ opener.jq('#fvvt_' + callerLineCounter).val(text); }
			  if(opener.jq('#fvlen_' + callerLineCounter).val()==''){ opener.jq('#fvlen_' + callerLineCounter).val(fvlen); }
			  if(opener.jq('#fvbrd_' + callerLineCounter).val()==''){ opener.jq('#fvbrd_' + callerLineCounter).val(fvbrd); }
			  if(opener.jq('#fvhoy_' + callerLineCounter).val()==''){ opener.jq('#fvhoy_' + callerLineCounter).val(fvhoy); }
			  if(opener.jq('#fvlm_' + callerLineCounter).val()==''){ opener.jq('#fvlm_' + callerLineCounter).val(fvlm); }
			  if(opener.jq('#fvlm2_' + callerLineCounter).val()==''){ opener.jq('#fvlm2_' + callerLineCounter).val(fvlm2); }
			  //cosmetics
			  //opener.jq('#ffunnr_' + callerLineCounter).removeClass("isa_warning");opener.jq('#ffembg_' + callerLineCounter).removeClass("isa_warning");
			  //opener.jq('#ffindx_' + callerLineCounter).removeClass("isa_warning");
			  //opener.jq('#ffunnr_' + callerLineCounter).removeClass("isa_error");opener.jq('#ffembg_' + callerLineCounter).removeClass("isa_error");
			  //opener.jq('#ffindx_' + callerLineCounter).removeClass("isa_error");
		  }else{
			  var fvpakn = record[0].replace("kode", "");
			  var text = record[1].replace("text", "");
			  var fvlen = record[2].replace("len", "");
			  var fvbrd = record[3].replace("brd", "");
			  var fvhoy = record[4].replace("hoy", "");
			  var fvlm = record[5].replace("lm", "");
			  var fvlm2 = record[6].replace("lm2", "");
			  //addressing a parent field from this child window
			  opener.jq('#fvpakn').val(fvpakn);
			  if(opener.jq('#fvvt').val()==''){ opener.jq('#fvvt').val(text); }
			  if(opener.jq('#fvlen').val()==''){ opener.jq('#fvlen').val(fvlen); }
			  if(opener.jq('#fvbrd').val()==''){ opener.jq('#fvbrd').val(fvbrd); }
			  if(opener.jq('#fvhoy').val()==''){ opener.jq('#fvhoy').val(fvhoy); }
			  if(opener.jq('#fvlm').val()==''){ opener.jq('#fvlm').val(fvlm); }
			  if(opener.jq('#fvlm2').val()==''){ opener.jq('#fvlm2').val(fvlm2); }
			  //cosmetics
			  //opener.jq('#ffunnr' + callerLineCounter).removeClass("isa_warning");opener.jq('#ffembg' + callerLineCounter).removeClass("isa_warning");
			  //opener.jq('#ffindx' + callerLineCounter).removeClass("isa_warning");
			  //opener.jq('#ffunnr' + callerLineCounter).removeClass("isa_error");opener.jq('#ffembg' + callerLineCounter).removeClass("isa_error");
			  //opener.jq('#ffindx' + callerLineCounter).removeClass("isa_error");
		  }
		  //close child window
		  window.close();
		  
	  });
	});
	
	//======================
    //Datatables jquery 
    //======================
    //private function [Filters]
    
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
    
    function filterCustomerList (){
        jq('#customerList').DataTable().search(
    		jq('#customerList_filter').val()
        ).draw();
    }
    function filterLoadUnloadPlacesList (){
        jq('#loadUnloadPlacesList').DataTable().search(
    		jq('#loadUnloadPlacesList_filter').val()
        ).draw();
    }
    function filterPackingCodesList (){
        jq('#packingCodesList').DataTable().search(
    		jq('#packingCodesList_filter').val()
        ).draw();
    }
    
    //Init datatables
    jq(document).ready(function() {
  	
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
	  
	  //------------------------------
	  //tables [Load/Unload places]
	  //----------------------------
	  jq('#loadUnloadPlacesList').dataTable( {
		  "dom": '<"top"fli>rt<"bottom"p><"clear">',
		  "lengthMenu": [ 50, 75, 100 ]
	  });
	  //event on input field for search
	  jq('input.loadUnloadPlacesList_filter').on( 'keyup click', function () {
		  filterLoadUnloadPlacesList();
	  });
	  
	  //------------------------------
	  //tables [packing codes]
	  //----------------------------
	  jq('#packingCodesList').dataTable( {
		  "dom": '<"top"fli>rt<"bottom"p><"clear">',
		  "lengthMenu": [ 50, 75, 100 ]
	  });
	  //event on input field for search
	  jq('input.packingCodesList_filter').on( 'keyup click', function () {
		  filterPackingCodesList();
	  });
      
    });
  	