	//===========================================
	//General functions for Dashboard - AJAX
	//===========================================
	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict();
  	
  		/* call this with onClick() in <a>link (in case a user pass. is sent to another WAR file)
  		function postdata(){
  		   jq.post("transportdisp_mainorderlist.do", { id: "lang=${user.usrLang}", next_id: "myother_placeholder" },
  		       function(data) {
  		         alert("Data Loaded: " + data);
  		       });
  		}*/
  
  	