	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict();
  	//--------
  	//Koder
  	//--------
	jq(function() {
		jq('#customerList').on('click', 'td', function(){
			  var id = this.id;
			  var record = id.split('@');
			 
			  var knr = record[0].replace("knr", "");
			  var knavn = record[1].replace("knavn", "");
			  var adr1 = record[2].replace("kadr1", "");
			  var adr3 = record[3].replace("kadr3", "");
			  var postnr = record[4].replace("kpostnr", "");
			  var land = record[5].replace("kland", "");
			  var eori = record[6].replace("keori", "");
			  var callerType = record[7].replace("ctype", "");
			  var adr2 = record[8].replace("kadr2", "");
			  var tlf = record[9].replace("tlf", "");
			  
			  //addressing a parent field from this child window
			  
			  //=========================
			  //TDS Export Module 
			  //=========================
			  //AVS
			  if(callerType == 'sveh_avna'){
				  opener.jq('#sveh_avkn').val(knr);
				  opener.jq('#sveh_avna').val(knavn);
				  opener.jq('#sveh_aveo').val(eori);
				  opener.jq('#sveh_ava1').val(adr1);
				  opener.jq('#sveh_ava2').val(adr2);
				  opener.jq('#sveh_avpa').val(adr3);
				  opener.jq('#sveh_avpn').val(postnr);
				  opener.jq('#sveh_avlk').val(land);
				  opener.jq('#sveh_avha').val("");
				  opener.jq('#sveh_avtl').val(tlf);
				  //focus
				  opener.jq('#sveh_avna').focus();
			  //MOTTAGARE		 
			  }else if(callerType == 'sveh_mona'){
				  opener.jq('#sveh_mokn').val(knr);
				  opener.jq('#sveh_mona').val(knavn);
				  opener.jq('#sveh_moeo').val(eori);
				  opener.jq('#sveh_moa1').val(adr1);
				  opener.jq('#sveh_moa2').val(adr2);
				  opener.jq('#sveh_mopa').val(adr3);
				  opener.jq('#sveh_mopn').val(postnr);
				  opener.jq('#sveh_molk').val(land);
				  //focus
				  opener.jq('#sveh_mona').focus();
				  
			  //Deklarant
			  }else if(callerType == 'sveh_dkna'){
				  opener.jq('#sveh_dkkn').val(knr);
				  opener.jq('#sveh_dkna').val(knavn);
				  opener.jq('#sveh_dkeo').val(eori);
				  opener.jq('#sveh_dka1').val(adr1);
				  opener.jq('#sveh_dka2').val(adr2);
				  opener.jq('#sveh_dkpa').val(adr3);
				  opener.jq('#sveh_dkpn').val(postnr);
				  opener.jq('#sveh_dklk').val(land);
				  //focus
				  opener.jq('#sveh_dkna').focus();

			  //=========================
			  //TDS Import Module 
			  //=========================
		      //AVS		  
			  }else if(callerType == 'dkih_02b'){
				  opener.jq('#dkih_avkn').val(knr);
				  opener.jq('#dkih_02b').val(knavn);
				  opener.jq('#dkih_02a').val(eori);
				  opener.jq('#dkih_02c').val(adr1);
				  opener.jq('#dkih_02e').val(adr3);
				  opener.jq('#dkih_02d').val(postnr);
				  opener.jq('#dkih_02f').val(land);
				  opener.jq('#dkih_02b').focus();
			  //MOTTAGARE		 
			  }else if(callerType == 'dkih_08b'){
				  opener.jq('#dkih_mokn').val(knr);
				  opener.jq('#dkih_08b').val(knavn);
				  opener.jq('#dkih_08a').val(eori);
				  opener.jq('#dkih_08c').val(adr1);
				  opener.jq('#dkih_08e').val(adr3);
				  opener.jq('#dkih_08d').val(postnr);
				  opener.jq('#dkih_08f').val(land);
				  opener.jq('#dkih_08b').focus();

			  //CARRIER
			  }else if(callerType == 'dkih_trna'){
				  opener.jq('#dkih_trkn').val(knr);
				  opener.jq('#dkih_trna').val(knavn);
				  opener.jq('#dkih_treo').val(eori);
				  opener.jq('#dkih_trga').val(adr1);
				  opener.jq('#dkih_trby').val(adr3);
				  opener.jq('#dkih_trpo').val(postnr);
				  opener.jq('#dkih_trlk').val(land);
				  opener.jq('#dkih_trna').focus();
			  
			  //UNDERETTES
			  }else if(callerType == 'dkih_nina'){
				  opener.jq('#dkih_nikn').val(knr);
				  opener.jq('#dkih_nina').val(knavn);
				  opener.jq('#dkih_nieo').val(eori);
				  opener.jq('#dkih_niga').val(adr1);
				  opener.jq('#dkih_niby').val(adr3);
				  opener.jq('#dkih_nipo').val(postnr);
				  opener.jq('#dkih_nilk').val(land);
				  opener.jq('#dkih_nina').focus();		  
			  
			  }
			  //close child window
			  window.close();
		  });
	});
	
	
	//======================
    //Datatables jquery 
    //======================
    //private function [Filters]
    function filterGeneralCode () {
    		jq('#customerList').DataTable().search(
      		jq('#customerList_filter').val()
    		).draw();
    } 
	//Init datatables
    jq(document).ready(function() {
  	  //-----------------------
      //table [General Code List]
  	  //-----------------------
    	  jq('#customerList').dataTable( {
    		  "dom": '<"top"fli>rt<"bottom"p><"clear">',
    		  "lengthMenu": [ 75, 100, 200, 500]
    	  });
      //event on input field for search
      jq('input.customerList_filter').on( 'keyup click', function () {
      		filterGeneralCode();
      });
      
    });   
  	
  	
	