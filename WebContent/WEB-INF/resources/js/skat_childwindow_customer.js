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
			  //addressing a parent field from this child window
			  
			  //=========================
			  //SKAT Export Module 
			  //=========================
			  //AVS
			  if(callerType == 'dkeh_02b'){
				  opener.jq('#dkeh_avkn').val(knr);
				  opener.jq('#dkeh_02b').val(knavn);
				  opener.jq('#dkeh_02a').val(eori);
				  opener.jq('#dkeh_02c').val(adr1);
				  opener.jq('#dkeh_02e').val(adr3);
				  opener.jq('#dkeh_02d').val(postnr);
				  opener.jq('#dkeh_02f').val(land);
				  opener.jq('#dkeh_02b').focus();
			  //MOTTAGARE		 
			  }else if(callerType == 'dkeh_08b'){
				  opener.jq('#dkeh_mokn').val(knr);
				  opener.jq('#dkeh_08b').val(knavn);
				  opener.jq('#dkeh_08a').val(eori);
				  opener.jq('#dkeh_08c').val(adr1);
				  opener.jq('#dkeh_08e').val(adr3);
				  opener.jq('#dkeh_08d').val(postnr);
				  opener.jq('#dkeh_08f').val(land);
				  opener.jq('#dkeh_08b').focus();
			  //CARRIER	  
			  }else if(callerType == 'dkeh_treo'){
				  opener.jq('#dkeh_trkn').val(knr);
				  opener.jq('#dkeh_treo').val(eori);
				  opener.jq('#dkeh_trkn').focus();
			  //Representant ved udpassage
			  }else if(callerType == 'dkeh_rena'){
				  opener.jq('#dkeh_rekn').val(knr);
				  opener.jq('#dkeh_rena').val(knavn);
				  opener.jq('#dkeh_reeo').val(eori);
				  opener.jq('#dkeh_rega').val(adr1);
				  opener.jq('#dkeh_reby').val(adr3);
				  opener.jq('#dkeh_repo').val(postnr);
				  opener.jq('#dkeh_relk').val(land);
				  opener.jq('#dkeh_rena').focus();

			  //=========================
			  //SKAT Import Module 
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
			  //=========================
			  //SKAT NCTS Eksport Module 
			  //=========================  
			  }else if(callerType == 'thnas'){
				  opener.jq('#thkns').val(knr);
				  opener.jq('#thnas').val(knavn);
				  opener.jq('#thtins').val(eori);
				  opener.jq('#thads1').val(adr1);
				  opener.jq('#thpss').val(adr3);
				  opener.jq('#thpns').val(postnr);
				  opener.jq('#thlks').val(land);
				  opener.jq('#thsks').val("");
				  opener.jq('#thnas').focus();
			  //Modtager	  
			  }else if(callerType == 'thnak'){
				  opener.jq('#thknk').val(knr);
				  opener.jq('#thtink').val(eori);
				  opener.jq('#thnak').val(knavn);
				  opener.jq('#thadk1').val(adr1);
				  opener.jq('#thpnk').val(postnr);
				  opener.jq('#thpsk').val(adr3);
				  opener.jq('#thlkk').val(land);
				  opener.jq('#thskk').val("");	
				  opener.jq('#thnak').focus();
			  //Ansvarige	  
			  }else if(callerType == 'thnaa'){
				  opener.jq('#thtina').val(eori);
				  opener.jq('#thnaa').val(knavn);
				  opener.jq('#thada1').val(adr1);
				  opener.jq('#thpna').val(postnr);
				  opener.jq('#thpsa').val(adr3);
				  opener.jq('#thlka').val(land);
				  opener.jq('#thskk').val("");	
				  opener.jq('#thnaa').focus();
				  
			  //Avsender - Sikkerhed	  
			  }else if(callerType == 'thnass'){
				  opener.jq('#thknss').val(knr);
				  opener.jq('#thnass').val(knavn);
				  opener.jq('#thtinss').val(eori);
				  opener.jq('#thadss1').val(adr1);
				  opener.jq('#thpsss').val(adr3);
				  opener.jq('#thpnss').val(postnr);
				  opener.jq('#thlkss').val(land);
				  opener.jq('#thskss').val("");
				  opener.jq('#thnass').focus();
			  //Modtager - Sikkerhed	  
			  }else if(callerType == 'thnaks'){
				  opener.jq('#thknks').val(knr);
				  opener.jq('#thtinks').val(eori);
				  opener.jq('#thnaks').val(knavn);
				  opener.jq('#thadks1').val(adr1);
				  opener.jq('#thpnks').val(postnr);
				  opener.jq('#thpsks').val(adr3);
				  opener.jq('#thlkks').val(land);
				  opener.jq('#thskks').val("");	
				  opener.jq('#thnaks').focus();
			  //Carrier - Sikkerhed	  
			  }else if(callerType == 'thnat'){
				  opener.jq('#thknt').val(knr);
				  opener.jq('#thtint').val(eori);
				  opener.jq('#thnat').val(knavn);
				  opener.jq('#thadt1').val(adr1);
				  opener.jq('#thpnt').val(postnr);
				  opener.jq('#thpst').val(adr3);
				  opener.jq('#thlkt').val(land);
				  opener.jq('#thskt').val("");	
				  opener.jq('#thnat').focus();
			  //=========================
			  //SKAT NCTS Import Module 
			  //=========================  
			  }else if(callerType == 'tina'){
				  opener.jq('#tikn').val(knr);
				  opener.jq('#tina').val(knavn);
				  opener.jq('#titin').val(eori);
				  opener.jq('#tiad1').val(adr1);
				  opener.jq('#tips').val(adr3);
				  opener.jq('#tipn').val(postnr);
				  opener.jq('#tilk').val(land);
				  opener.jq('#tisk').val("");
				  opener.jq('#tina').focus();
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
  	
  	
	