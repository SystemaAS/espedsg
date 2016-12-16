//this variable is a global jQuery var instead of using "$" all the time. Very handy
var jq = jQuery.noConflict();
//--------
//Koder
//--------
jq(function() {
	jq('#codeList').on('click', 'td', function() {
		var id = this.id;
		var record = id.split('@');
		var code = record[0].replace("code", "");
		var caller = record[1].replace("caller", "");

		//addressing a parent field from this child window
		if (caller == 'ctype') {
			opener.jq('#ctype').val(code);
			opener.jq('#ctype').change();
			opener.jq('#ctype').focus();
		} 

		//close child window
		window.close();
	});
});

//======================
//Datatables jquery 
//======================
//private function [Filters]
function filterCode() {
	jq('#codeList').DataTable().search(jq('#codeList_filter').val()).draw();
}
//Init datatables
jq(document).ready(function() {
	jq('#codeList').dataTable({
		"dom" : '<"top"fli>rt<"bottom"p><"clear">',
		"lengthMenu" : [ 75, 100, 200, 500 ]
	});
	//event on input field for search
	jq('input.codeList_filter').on('keyup click', function() {
		filterCode();
	});

});
