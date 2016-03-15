  //this variable is a global jQuery var instead of using "$" all the time. Very handy
  var jq = jQuery.noConflict();
  var counterIndex = 0;
  
  
  jq(function() {
	  jq("#TODO").click(function() {
		  var value = jq("#TODO").attr('src');
		  
		  if(value=='resources/images/sort_down.png'){
			  jq("#TODO").attr('src',"resources/images/sort_up.png");
		  }else{
			  jq("#TODO").attr('src',"resources/images/sort_down.png");
		  }
	  });
  });
  //Testing in order to implement sort in each column...
  /*
  jq(function() {
	  jq("#imgSortDager").click(function() {
		  var value = jq("#imgSortDager").attr('src');
		  
		  if(value=='resources/images/sort_down.png'){
			  jq("#imgSortDager").attr('src',"resources/images/sort_up.png");
		  }else{
			  jq("#imgSortDager").attr('src',"resources/images/sort_down.png");
		  }
		  console.log('###########');
		  //call function
		  jq.ajax({
		  	  type: 'GET',
		  	  url: 'helloUoppdragAjaxHandler.do',
		  	  data: { id : 'Oscar'
		  		  	  },
		  	  dataType: 'text',
		  	  success: function(data) {
		  		var len = data.length;
		  		console.log('Inside with legend: ' + data);
		  		for ( var i = 0; i < len; i++) {
		  			
		  			
		  		}
		  	},
		  	  error: function() {
		  		console.log('Error loading');
		  	  }
		  	});
		  
	  });
  });
  */

