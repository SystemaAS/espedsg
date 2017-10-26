<%@ include file="/WEB-INF/views/include.jsp" %>
<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerReportDashboard.jsp" />
<!-- =====================end header ==========================-->

<script type="text/javascript">
"use strict";
var tolldataSize;
var ofs = 0, pag = 20;
var baseUrl = "/syjservicesbcore/syjsFORTOLLING_DB.do?user=${user.user}";
var merknaderDescUrl = "/syjservicestn/syjsTVI99D.do?user=${user.user}";

var jq = jQuery.noConflict();
var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Vennligst vent...";

var merknader;

d3.json(merknaderDescUrl, function(error, data) {
	if (error) {
		jq.unblockUI();
		//throw error;
	}

	if (data.list == '') {
		jq.unblockUI();
		alert('Ingen data for merknader.');  
		return "no data found";
	} else {
		merknader = data.list;
	}
	
	//console.log("Desc 954="+_.findWhere(merknader,{e9705:'954'}).e4440);
	
	
});

function getMerknadDesc(id) {
	var desc =  _.findWhere(merknader,{e9705:id});
	if (desc != null && desc != "") {
		return desc.e4440;
	} else {
		return "["+id+" ikke funnet som funksjonfeil i vedlikehold.]";		
	}

}

function load_data() {
	
	var runningUrl = baseUrl;
	var selectedYear = jq('#selectYear').val();
	var selectedAvd = jq('#selectAvd').val();
	var selectedSign = jq('#selectSign').val();
	var selectedKundenr = jq('#selectKundenr').val();

	if (selectedYear != "" )	{
		runningUrl = runningUrl + "&registreringsdato="+selectedYear;
	}	
	if (selectedAvd != null && selectedAvd != "")	{
		runningUrl = runningUrl + "&avdelings="+selectedAvd; 
	}
	if (selectedSign != null && selectedSign != "")	{
		runningUrl = runningUrl + "&signatur="+selectedSign;
	}	
	if (selectedKundenr != "" )	{
		runningUrl = runningUrl + "&mottaker="+selectedKundenr;
	}
	console.log("runningUrl="+runningUrl); 		
	
    jq.blockUI({message : BLOCKUI_OVERLAY_MESSAGE_DEFAULT});

	d3.json(runningUrl, function(error, data) {
		if (error) {
			jq.unblockUI();
			throw error;
		}
			
		if (data.dtoList == '') {
			jq.unblockUI();
			alert('Ingen data på urvalg.'); 
			return "no data found";
		}
		
		
		var tollData = data.dtoList;
	    //console.log("tollData="+tollData);  //Tip: View i  Chrome devtool; NetWork-(mark xhr row)-Preview
	    
	    var fullDateFormat = d3.time.format('%Y%m%d');
	    var yearFormat = d3.time.format('%Y');
	    var monthFormat = d3.time.format('%m');
	    var monthNameFormat = d3.time.format('%m.%b');
	    var percentageFormat = d3.format('.2%');
	    var numberFormat = d3.format(",.0f")
	 
	    // normalize/parse data
		 _.each(tollData, function( d) {
		  d.date = fullDateFormat.parse(d.registreringsdato);
		  d.year = yearFormat(d.date);
		  d.month = monthNameFormat(d.date);
		  d.avdeling = d.avdeling;
		  d.deklarasjonsnr= d.deklarasjonsnr;
		  d.reg_vareposter = +d.reg_vareposter;
		  d.off_vareposter = +d.off_vareposter;
		  d.registreringsdato = +d.registreringsdato; 
		  d.signatur =   d.signatur;
		  d.mottaker =   d.mottaker;
		  d.edim =   d.edim;
		});
	 
		// set crossfilter. Crossfilter runs in the browser and the practical limit is somewhere around half a million to a million rows of data.
		var toll = crossfilter(tollData);	
		var  all = toll.groupAll();
		tolldataSize = toll.size();
		
		//Dimensions
		var  tollAllDim = toll.dimension(function(d) {return d;});	
		var  dateDim  = toll.dimension(function(d) {return d.date;});
		var  yearDim  = toll.dimension(function(d) {return d.year;});
	    var  monthDim = toll.dimension(function (d) {return d.month;});	
		var  avdDim  = toll.dimension(function(d) {return d.avdeling;});
		var  sisgDim  = toll.dimension(function(d) {return d.signatur;});
		var  typeDim  = toll.dimension(function(d) {return d.type;});
		var  edimDim  = toll.dimension(function(d) {return d.edim;});
		//Charts 
		var  typeChart   = dc.pieChart("#chart-ring-type");
		var  yearChart   = dc.pieChart("#chart-ring-year");
		var  avdChart   = dc.pieChart('#chart-ring-avd');
		var  sisgChart   = dc.pieChart('#chart-ring-sisg');
		var  edimChart   = dc.pieChart('#chart-ring-edim');
		var  compositeChart = dc.compositeChart("#chart-composite");
		var  dataCount = dc.dataCount('#data-count')	 
		var  antallDisplay = dc.numberDisplay("#antall");	
		var  antallreg_vareposterDisplay = dc.numberDisplay("#antallreg_vareposter");	
		var  antalloff_vareposterDisplay = dc.numberDisplay("#antalloff_vareposter");	
		
		var mindate = dateDim.bottom(1)[0].date;
		var maxdate = dateDim.top(1)[0].date;
		
		var minmonth = dateDim.bottom(1)[0].month;
		var maxmonth = dateDim.top(1)[0].month;
		
		//Groups
		var  yearDimGroup = yearDim.group().reduceSum(function(d) {return d.reg_vareposter;});
		var  avdDimGroup = avdDim.group().reduceSum(function(d) {return d.reg_vareposter;});
		var  sisgDimGroup = sisgDim.group().reduceSum(function(d) {return d.reg_vareposter;});
		var  typeDimGroup = typeDim.group().reduceSum(function(d) {return d.reg_vareposter;});
		var  edimDimGroup = edimDim.group().reduceSum(function(d) {return d.reg_vareposter;});
		
		//Group reduce
	    var dateDimGroup =  dateDim.group().reduce(   
	            /* callback for when data is added to the current filter results */
	            function (p, v) {
	                ++p.count;
	                p.sum_reg_vareposter += v.reg_vareposter;   
	                p.sum_off_vareposter  += v.off_vareposter;    
	                return p;
	            },
	            /* callback for when data is removed from the current filter results */
	            function (p, v) {
	                --p.count;
	                p.sum_reg_vareposter -= v.reg_vareposter;   
	                p.sum_off_vareposter -= v.off_vareposter;   
	                return p;
	            },
	            /* initialize p */
	            function () {
	                return {
	                    count: 0,
	                    sum_reg_vareposter: 0,
	                    sum_off_vareposter: 0
	                };
	            }
	    );  
		
	    var monthDimGroup =  monthDim.group().reduce(   
	            function (p, v) {
	                ++p.count;
	                p.sum_reg_vareposter += v.reg_vareposter;   
	                p.sum_off_vareposter  += v.off_vareposter;     
	                return p;
	            },
	            function (p, v) {
	                --p.count;
	                p.sum_reg_vareposter -= v.reg_vareposter;   
	                p.sum_off_vareposter  -= v.off_vareposter;     
	                return p;
	            },
	            function () {
	                return {
	                    count: 0,
	                    sum_reg_vareposter: 0,
	                    sum_off_vareposter: 0
	                };
	            }
	    );  	
		
		
	    var omsetningsGroup =  tollAllDim.group().reduce(  
	            /* callback for when data is added to the current filter results */
	            function (p, v) {
	                ++p.count;
	                p.sum_reg_vareposter += v.reg_vareposter;   
	                p.sum_off_vareposter  += v.off_vareposter;
	                return p;
	            },
	            /* callback for when data is removed from the current filter results */
	            function (p, v) {
	                --p.count;
	                p.sum_reg_vareposter -= v.reg_vareposter; 
	                p.sum_off_vareposter -= v.off_vareposter;   
	                return p;
	            },
	            /* initialize p */
	            function () {
	                return {
	                    count: 0,
	                    sum_reg_vareposter: 0,
	                    sum_off_vareposter: 0
	                };
	            }
	    );  
	  
		typeChart
		    .width(300)
		    .height(300)
		    .dimension(typeDim)
		    .group(typeDimGroup)
		    .externalRadiusPadding(50)
		    .innerRadius(30)
		    .on("filtered", getFiltersValues);
		    
		yearChart
		    .width(300)
		    .height(300)
		    .dimension(yearDim)
		    .group(yearDimGroup)
		     .externalRadiusPadding(50)
		    .innerRadius(30)
		    .on("filtered", getFiltersValues);
	   
		avdChart
		    .width(300)
		    .height(300)
		    .dimension(avdDim)
		    .group(avdDimGroup)
		    .slicesCap(25)
		    .innerRadius(30)
		    .externalRadiusPadding(50)
		    .legend(dc.legend().y(10).itemHeight(8).gap(3))
		    .on("filtered", getFiltersValues);
		
		sisgChart
		    .width(300)
		    .height(300)
		    .slicesCap(25)
		    .innerRadius(30)
		    .externalRadiusPadding(50)
		    .dimension(sisgDim)
		    .group(sisgDimGroup)
		    .on("filtered", getFiltersValues);
	
		edimChart
		    .width(300)
		    .height(300)
		    .slicesCap(25)
		    .innerRadius(30)
		    .externalRadiusPadding(50)
		    .legend(dc.legend().y(10).itemHeight(8).gap(3))
		    .dimension(edimDim)
		    .group(edimDimGroup)
			.renderTitle(true)
			.title(function (d) {
				var key, desc;
				if (d.key == 'OK') {
					key = 'OK';
					desc = '';
				} else {
					var trailingThree = d.key.slice(-3);
					if (isNaN(trailingThree)) {  //key is a shapeshifter
						 key = d.key.slice(0,2);
						 desc = d.key.slice(2,d.key.lenght);
			        } else {
			        	 key = d.key;
						 desc =  getMerknadDesc(trailingThree);	        	 
			        }
				}
	            return [
	                key + ':',
	                desc
	            ].join('\n');			
			});	    
		
		antallDisplay
		     .group(omsetningsGroup)  
		     .formatNumber(d3.format(".g"))
			 .valueAccessor(function (p) {
				 return p.value.count;
			  });
		
		antallreg_vareposterDisplay
			.group(omsetningsGroup)  
			.formatNumber(d3.format(".g"))
			.valueAccessor(function (p) {
					return p.value.sum_reg_vareposter;
			});
		
		antalloff_vareposterDisplay
			.group(omsetningsGroup)  
			.formatNumber(d3.format(".g"))
			.valueAccessor(function (p) {
					return p.value.sum_off_vareposter;
			});	
		
	
		compositeChart
			    .width(1200)
			    .height(500)
			    .dimension(monthDim)   
			    .group(monthDimGroup) 
			    .margins({top: 40, right: 10, bottom: 40, left: 80})
	     		.x(d3.scale.ordinal())  
	            .xUnits(dc.units.ordinal)   
	            .yAxisPadding('10%')
	            .yAxisLabel("Antall vareposter")
	        	.xAxisLabel("Måned")      
	            .elasticY(true)
	            .elasticX(true)
	            .mouseZoomable(false)  //true npot working in this context
	        	.legend( dc.legend().x(1020).y(0).itemHeight(10).gap(10).legendText(function(d, i) { 
	        				if (i == 2) {
	        					return "Antall registrerte vareposter";
	        				}
	        				if (i==1) {
	        					return "Antall offisielle vareposter";
	        				}
	        				if (i==0) {
	        					return "Antall fortollinger";
	        				}
	        			}) 
	        	)
			    .renderHorizontalGridLines(true)
			    .renderTitle(true)
			    .title(function (d) {
			    			var diffRegAndOff = d.value.sum_reg_vareposter - d.value.sum_off_vareposter;
	                    	 return [
	         	                d.key,
	         	                'Reg. varuposter: ' + numberFormat(d.value.sum_reg_vareposter) ,
	         	                'Off. varuposter: ' + numberFormat(d.value.sum_off_vareposter),
	         	                'Fortollinger: ' + numberFormat(d.value.count),
	         	               ' Differanse reg./off.: ' + numberFormat(diffRegAndOff)
	         	            ].join('\n');
	        	})	
			  	.compose([
	     			dc.barChart(compositeChart)
			    		.group(monthDimGroup) 
			            .barPadding(1)
				        /*Antall fortollinger*/
			           .valueAccessor(function (d) {
	           				return d.value.count; 
	   					}) 
	   					/*Antall off. varuposter*/
	   					.stack(monthDimGroup,function (d) {
	                     	return d.value.sum_off_vareposter;  //100
	                    })
	                    /*Antall off. varuposter*/
	                    .stack(monthDimGroup,function (d) {
	                    	var diffRegAndOff =  d.value.sum_reg_vareposter - d.value.sum_off_vareposter;   //100-80=20
	                    	return diffRegAndOff;
	                    })
			     ])
	            .xAxis().tickFormat(function(d) { 
	            	return d.substr(3); 
	            });
	
		//Diverse grejer till datum   
		//https://jsfiddle.net/pramod24/q4aquukz/4/
	
	
	   	d3.selectAll('a#all').on('click', function () {
	     	dc.filterAll();
	     	dc.renderAll();
	   	});
	
		d3.selectAll('a#type').on('click', function () {
			typeChart.filterAll();
			dc.redrawAll();
		});
	
		d3.selectAll('a#year').on('click', function () {
			yearChart.filterAll();
			dc.redrawAll();
		});
	
		d3.selectAll('a#avd').on('click', function () {
			avdChart.filterAll();
			dc.redrawAll();
		});	 
		d3.selectAll('a#sisg').on('click', function () {
			sisgChart.filterAll();
			dc.redrawAll();
		});	
		d3.selectAll('a#edim').on('click', function () {
			edimChart.filterAll();
			dc.redrawAll();
		});	
		d3.selectAll('a#composite').on('click', function () {
			compositeChart.filterAll();
			compositeChart.redraw();
			dc.redrawAll();
		});	
		
		d3.selectAll('a#avdfilter').on('click', function () {
			avdChart.filter(jq('#avd-filter').val());
			dc.redrawAll();
			jq('#avd-filter').val("");
		});		
	
		d3.selectAll('a#merknadfilter').on('click', function () {
			edimChart.filter(jq('#merknad-filter').val());
			dc.redrawAll();
			jq('#merknad-filter').val("");
		});		
		
		dataCount
		      .dimension(toll)
		      .group(all)
			  .html({
	            some: '<strong>%filter-count</strong> valgt ut av <strong>%total-count</strong> fortollinger' +
	                ' | <a href=\'javascript:dc.filterAll(); dc.renderAll();\'>tilbakestill alt</a>',
	            all: 'Alle <strong>%total-count</strong> fortollinger for utvalg. Vennligst klikk på grafen for å bruke filtre.'
	          });      
		      
		d3.select('#download').on('click', function() {
			var today = new Date();
	        var data = tollAllDim.top(Infinity);
	        var saveData = data.map(function(obj) {
	            return {avdeling: obj.avdeling, deklarasjonsnr: obj.deklarasjonsnr, reg_vareposter: obj.reg_vareposter, 
	            		off_vareposter: obj.off_vareposter, registreringsdato: obj.registreringsdato,
	            		signatur: obj.signatur, mottaker: obj.mottaker};
	        });
	       
			var blob = new Blob([d3.tsv.format(saveData)], {type: "application/vnd.ms-excel;charset=utf-8"});  // text/csv
			
	        saveAs(blob, 'fortolling_no-' + today + '.xls');
	    });	
	
		var displayed = false;
		jq('#showTable' ).click(function() {
		   if (displayed) {
			   jq( '#detailsTable' ).toggle();
			   displayed = false;
		   } else {
		   jq( '#detailsTable' ).toggle( "slow", function() {
		    console.log("start filling dataTable...")
	
		    var dataTable = dc.dataTable('#data-table');
		    
			dataTable
		    .dimension(tollAllDim) 
		    .group(function (d) { return 'dc.js insists on putting a row here so I remove it using JS'; })
		    .size(Infinity) 
		    .columns([
		      function (d) { return d.avdeling; },
		      function (d) { return d.deklarasjonsnr; },
		      function (d) { return d.reg_vareposter; },
		      function (d) { return d.off_vareposter; },
		      function (d) { return d.registreringsdato; },
		      function (d) { return d.signatur ; },
		      function (d) { return d.mottaker ; },
		      function (d) { return d.type ; }
		    ])
		    .on('renderlet', function (table) {
		      	// each time table is rendered remove nasty extra row dc.js insists on adding
		     	table.select('tr.dc-table-group').remove();
		 	});	    
		    
		    dataTable.render();	    //måste vara med
		    
		    var lang = jq('#language').val();
			jq('#data-table').DataTable({
				"dom" : '<"top">t<"bottom"f><"clear">',
				"scrollY" : "200px",
				"scrollCollapse" : false,
				destroy : true,
				"columnDefs" : [ {
					"type" : "num",
					"targets" : 0
				} ],
				"lengthMenu" : [ 75, 100 ],
				"language": {
					"url": getLanguage(lang)
		        }
			}); 
			
			console.log("ready filling dataTable.");
			displayed = true;
		    
		   });
		  }
		}); 
	
		function getFiltersValues() {
		    var filters = [
		        { name: 'type', value: typeChart.filters()},
		        { name: 'year', value: yearChart.filters()},
		        { name: 'avd',  value: avdChart.filters()},
		        { name: 'sisg', value: sisgChart.filters()},
		        { name: 'edim', value:  edimChart.filters()},
		        { name: 'composite', value: compositeChart.filters()}];
		    var recursiveEncoded = jq.param( filters );
		    location.hash = recursiveEncoded;
		}
		
		
		// Init chart filters
		function initFilters() {
			console.log("initFilter");
			// Get hash values
		    var parseHash = /^#type=([A-Za-z0-9,_\-\/\s]*)&year=([A-Za-z0-9,_\-\/\s]*)&avd=([A-Za-z0-9,_\-\/\s]*)&sisg=([A-Za-z0-9,_\-\/\s]*)&edim=([A-Za-z0-9,_\-\/\s]*)$/;
		    var parsed = parseHash.exec(decodeURIComponent(location.hash));
			console.log("parsed="+parsed);
		    function filter(chart, rank) {  // for instance chart = typeChart and rank in URL hash = 1
		  
		    	//chart
		        if (parsed[rank] == "") {
		            chart.filter(null);
		        }
		        else {
		            var filterValues = parsed[rank].split(",");
		            for (var i = 0; i < filterValues.length; i++ ) {
		                chart.filter(filterValues[i]);
		            }
		        }
		    }
		    if (parsed) {
		        filter(typeChart, 1);
		        filter(yearChart, 2);
		        filter(avdChart, 3);
		        filter(sisgChart, 4);
		        filter(edimChart, 5);
		    }
		}
	
		tolldataSize = toll.size();
		  
		dc.renderAll(); 
		
		initFilters();
		
		jq(document).ready(function() {
			jq('#toggleArea').toggle(true); //default hide
		});
		
		jq.unblockUI();
	    
	});

}
 

jq(document).ready(function() {
	jq('select#selectVarekode').selectList();
	jq('select#selectSign').selectList();
	jq('select#selectAvd').selectList();
	jq('#detailsTable').toggle(false); //default hide
	jq('#toggleArea').toggle(false); //default hide
});	


window.addEventListener('error', function (e) {
	  var error = e.error;
	  jq.unblockUI();
	  console.log("Event e.error",e.error);

	  if (e instanceof TypeError) {
			//what todo  
	  } else {
		  alert('Uforutsett fel har intreffet. Vennligst gör forfrisk på fane Fortolling(NO).');
	  }
	  
});

</script>


<table id="fullTable" width="100%"  cellspacing="0" border="0" cellpadding="0">
	<tr>
		<td>
		<%-- tab container component --%>
		<table style="border-collapse:initial;" width="100%"  cellspacing="0" border="0" cellpadding="0">
			<tr height="2"><td></td></tr>
				<tr height="25"> 
					<td width="20%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a class="text14" href="report_dashboard.do?report=report_trafikkregnskap_overview">
							<font class="tabDisabledLink">&nbsp;Trafikkregnskap - oversikt</font>&nbsp;						
						</a>
						<img  style="vertical-align:middle;" src="resources/images/lorry_green.png" width="18px" height="18px" border="0">
			  		</td>		
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>

					<td width="20%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a class="text14" href="report_dashboard.do?report=report_trafikkregnskap" >
							<font class="tabDisabledLink">&nbsp;Trafikkregnskap - detaljer</font>&nbsp;						
						</a>						
						<img style="vertical-align:middle;" src="resources/images/lorry_green.png"  width="18px" height="18px" border="0" alt="Trafikkregnskap rapport">
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>

					<td width="20%" valign="bottom" class="tab" align="center" nowrap>
						<font class="tabLink">&nbsp;Fortolling(NO)</font>
						<img  style="vertical-align:middle;" src="resources/images/list.gif" border="0" alt="general list">
					</td>

					<td width="40%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>	
	
				</tr>
		</table>
		</td>
	</tr>

	<tr>
		<td>
			<!--  
			The Bootstrap 3 grid system has four tiers of classes: xs (phones), sm (tablets), md (desktops), and lg (larger desktops). 
			You can use nearly any combination of these classes to create more dynamic and flexible layouts.
			Each tier of classes scales up, meaning if you plan on setting the same widths for xs and sm, you only need to specify xs.
			-->
	 	 <table width="100%" class="tabThinBorderWhite" border="0" cellspacing="0" cellpadding="0">
	 	    <tr height="20">
		 	    <td width="2%">&nbsp;</td>
		 	    <td>&nbsp;
				<div class="container-fluid">
				  <div class="row">
					<div class="col-md-1 text12">
						<font class="text12">Fra år:</font><br>
						<select name="selectYear" id="selectYear" >
	  						<option value="2017">2017</option>
		  					<option value="2016">2016</option>
	  					</select>
	  				</div>
	  				
					<div class="col-md-1 text12">
						<font class="text12">Avdeling:</font><br>
		        		<select class="inputTextMediumBlue" name="selectAvd" id="selectAvd" multiple="multiple" title="-velg-">
		 				  	<c:forEach var="record" items="${model.avdList}" >
		 				  		<option value="${record.koakon}">${record.koakon}</option>
							</c:forEach>  
						</select>						
					</div>	
						
					<div class="col-md-1 text12">
						<font class="text12">Signatur:</font><br>
		        		<select class="inputTextMediumBlue" name="selectSign" id="selectSign" multiple="multiple" title="-velg-">
	 						<c:forEach var="record" items="${model.signatureList}" >
		 				  		<option value="${record.ksisig}">${record.ksisig}</option>
							</c:forEach>   
						</select>					
					</div>
					
					<div class="col-md-2 text12">
  		    			<font class="text12">&nbsp;&nbsp;Mottaker:</font><br>
						<input type="text" class="inputText" name="selectKundenr" id="selectKundenr" size="9" maxlength="8" >  	
						<a tabindex="-1" id="kundenrLink">
							<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0">
						</a>&nbsp;	
					</div> 	

	  		    	<div class="col-md-7" align="right">
	   	              	<button class="inputFormSubmit" onclick="load_data()" autofocus>Hent data</button> 
					</div>	
	
				  </div>

	  			  <div class="padded-row-small">&nbsp;</div>

<div id="toggleArea">

				  <div class="row">
					<div class="col-md-12">
					  <div class="row">
						<div class="col-md-4 padded" id="antall" align="center">
						    <h3 class="text14">Antall fortollinger</h3>
						</div>
				        <div class="col-md-4 padded" id="antallreg_vareposter" align="center">
				           <h3 class="text14">Antall registrerte vareposter</h3>
				        </div>  
				        <div class="col-md-4 padded" id="antalloff_vareposter" align="center">
				          <h3 class="text14">Antall offisielle vareposter</h3>
				        </div>  
					  </div> 	
					</div>		
				  </div>
	
				  <div class="row">
					   <div class="col-md-12">
					      <h3 class="text14" style="border-bottom-style: solid; border-width: 1px;">&nbsp;</h3>
					   </div>
				  </div>	
	

				  <div class="row">
				    <div class="col-md-12">
				      <div class="row">
   						 <div class="col-md-12">
   						   <!--   <h3 class="text11" style="border-bottom-style: solid; border-width: 1px;">Vareposter</h3> -->
   						 </div>
				      </div>
				      <div class="row">
						 <div class="col-md-12 dc-chart" id="chart-composite"> 
						  	<h3 class="text12">Vareposter</h3>
						    <span class="reset" style="display: none;">filter: <span class="filter"></span></span>
						    <a class="reset" id="composite" style="display: none;"> - <i>tilbakestill filter</i></a>
						 </div>  
				      </div>
				    </div>
				  </div>
				  
				  <div class="row">
				    <div class="col-md-12"></div>
				  </div>
				  
				  <div class="row">
					<div class="col-md-12">
					  <div class="row">
						   <div class="col-md-12">
						      <h3 class="text14" style="border-bottom-style: solid; border-width: 1px;">&nbsp;</h3>
						   </div>
					  </div>				  
					  

					  <div class="row">
					     <div class="col-md-2" id="chart-ring-avd">
				        	<h3 class="text12" align="justify">Avdeling
					        	 <font class="text11">&nbsp;&nbsp;&nbsp;avd:&nbsp;<input id="avd-filter" type="text" size="5"/>  </font>
					        	 <a id="avdfilter">legg til</a>	
				        	</h3>
						    <span class="reset" style="display: none;">filter: <span class="filter"></span></span>
						    <a class="reset" id="avd" style="display: none;"> - <i>tilbakestill filter</i></a>
				        </div>
						<div class="col-md-2" id="chart-ring-type">
						 	<h3 class="text12" align="center">Import / Eksport</h3>
						    <span class="reset" style="display: none;">filter: <span class="filter"></span></span>
						    <a class="reset" id="type" style="display: none;"> - <i>tilbakestill filter</i></a>
				        </div>
						<div class="col-md-2" id="chart-ring-year">
							<h3 class="text12" align="center">År</h3>
						    <span class="reset" style="display: none;">filter: <span class="filter"></span></span>
						    <a class="reset" id="year" style="display: none;"> - <i>tilbakestill filter</i></a>
				        </div>

				        <div class="col-md-3" id="chart-ring-sisg">
				        	<h3 class="text12" align="center">Signatur</h3>
						    <span class="reset" style="display: none;">filter: <span class="filter"></span></span>
						    <a class="reset" id="sisg" style="display: none;"> - <i>tilbakestill filter</i></a>
				        </div>  
				        <div class="col-md-3" id="chart-ring-edim">
				        	<h3 class="text12">Merknader
					        	<font class="text11">&nbsp;&nbsp;&nbsp;merknad:&nbsp;<input id="merknad-filter" type="text" size="5"/>  </font>
					        	 <a id="merknadfilter">legg til</a>	
				        	 </h3>
						    <span class="reset" style="display: none;">filter: <span class="filter"></span></span>
						    <a class="reset" id="edim" style="display: none;"> - <i>tilbakestill filter</i></a>
				        </div> 
					  </div> 	
					</div>		
				  </div>	
	
				  <div class="row">
				    <div class="col-md-12" id="data-count"></div>
				  </div>
				 
				  <div class="row">
					<div class="col-md-12">
						<h3 class="text14" style="border-bottom-style: solid; border-width: 1px;">&nbsp;</h3>
					</div>
				  </div>	
   
				  <div class="row">
					<div class="col-md-12" id="showTable">
  						<h3><a id="showTable"><font class="text12">Vis Fortollinger, filtrert</font>
  						&nbsp;<img onMouseOver="showPop('vis_fortoll_info');" onMouseOut="hidePop('vis_fortoll_info');" width="12px" height="12px" src="resources/images/info3.png">
		 				</a></h3>
		 				<div class="text11" style="position: relative;" align="left">
		 				<span style="position:absolute; top:2px; width:250px;" id="vis_fortoll_info" class="popupWithInputText text11"  >
				           		<b>
				           			Vis Fortollinger, filtrert
				 	          	</b><br><br>
				           		Bruk detaljer dersom det finnes intresse att se spesifike fortollinger.
				           		Hvis stort antall fortollinger er utvalgt, ytelse kan oppleves som mindre bra.
								<br><br>
						</span>
						</div>
					</div>
				  </div>	
				   
				  <div class="padded-row"></div>
	
				  <div class="row" id="detailsTable">
				    <div class="col-md-12">
				       <table class="display" id="data-table" cellspacing="0" width="100%">
				        <thead>
				          <tr>
				            <th>avdeling</th>
				            <th>deklarasjonsnr</th>
				            <th>reg. vareposter</th>
				            <th>off. vareposter</th>
				            <th>registreringsdato</th>
				            <th>signatur</th>
				            <th>mottaker</th>
				            <th>type</th>
				          </tr>
				        </thead>
				        <tfoot>
				          <tr>
				            <th>avdeling</th>
				            <th>deklarasjonsnr</th>
				            <th>reg. vareposter</th>
				            <th>off. vareposter</th>
				            <th>registreringsdato</th>
				            <th>signatur</th>
				            <th>mottaker</th>
				            <th>type</th>
				          </tr>
				        </tfoot>				        
				       </table>

				      <div>
						<a href="#" id="download">
	                		<img valign="bottom" id="mainListExcel" src="resources/images/excel.gif" width="14" height="14" border="0" alt="excel">
	                		<font class="text12MediumBlue">&nbsp;Excel</font>
	 	        		</a>
  					  </div>
				    </div>
				  
				  </div>
	
				  
				<div class="padded-row">&nbsp;</div>

</div>



         		</div> <!-- container -->
		 	    </td>
	 	    </tr>
	 	 </table>
		</td>
	</tr>
</table>	
		
<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->

