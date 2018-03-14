  //this variable is a global jQuery var instead of using "$" all the time. Very handy
  var jq = jQuery.noConflict();
  var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";

  function setBlockUI(element){
	  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
  }
  
  
  //Childwindow
  jq(function() {
		jq('#mainList').on('click', 'td', function(){
			var id = this.id;
			var record = id.split('@');
			var id = record[0].replace("id_","");
			var name = record[1].replace("name_","");
			var country = record[2].replace("country_","");
			
			var caller= jq("#ctype").val()
			 
			  //addressing a parent field from this child window
			  if( caller =='fr'){ 
				  opener.jq('#hesdf').val(id);
				  opener.jq('#helka').val(country);
			  }else{
				  opener.jq('#hesdt').val(id);
				  opener.jq('#helkk').val(country);
			  }	  
			  
			//close child window
			window.close();
		});
  });
  
  function filterMainList (){	
	  jq('#mainList').DataTable().search(
	  jq('#mainList_filter').val()
	  ).draw();
  }
  
  //-----------------
  //Init datatable
  //-----------------
  jq(document).ready(function() {
	  var lang = jq('#language').val(); 
	  
	  //-----------------------
	  //tables [fly-produkter]
	  //-----------------------
	  jq('#mainList').dataTable( {
		  "dom": '<"top"fli>rt<"bottom"p><"clear">',
		  "order": [[ 1, "asc" ]],
		  "lengthMenu": [ 15, 75, 100 ],
		  "language": { "url": getLanguage(lang) }
	  });
	  //event on input field for search
	  jq('input.mainList_filter').on( 'keyup click', function () {
		  filterMainList();
	  });
	  
  });
	  