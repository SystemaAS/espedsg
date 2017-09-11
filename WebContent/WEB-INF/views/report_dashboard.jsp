<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerReportDashboard.jsp" />
<!-- =====================end header ==========================-->
  <script src="https://dc-js.github.io/dc.js/js/d3.js"></script>
  <script src="https://dc-js.github.io/dc.js/js/crossfilter.js"></script>
  <script src="https://dc-js.github.io/dc.js/js/dc.js"></script>
  <script src="http://colorbrewer2.org/export/colorbrewer.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.js"></script>

  <link rel="stylesheet" type="text/css" href="https://dc-js.github.io/dc.js/css/dc.css">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn" crossorigin="anonymous"></script>

  <script src="https://unpkg.com/leaflet@1.1.0/dist/leaflet.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/underscore.js/1.8.3/underscore-min.js"></script>

  <script src="https://rawgit.com/crossfilter/reductio/master/reductio.js"></script>
  <script src="https://npmcdn.com/universe@latest/universe.js"></script>



<style>

.dc-data-count small {
  font-size: 16px;
}

.container-fluid {
  padding-left: 10px;
  padding-right: 20px;
}

.pie-chart {
  height: 150px;
}

.number-display {
  font-size: 30px;
  padding-top: 10px;
  padding-bottom: 10px;
}

.padded {
  padding-top: 10px;
  padding-bottom: 10px;
}

.dc-chart .axis text{
  font-size: 12px;
}

text {
  font-size: 16px;
   fill: black;
}

.filterBold {
  font-size: 16px;
  fill: black;
}

#map {
  height: 150px;
}

#control-row {
  padding-bottom: 50px;
}

.padded-row {
  padding-bottom: 50px;
}


a { cursor: pointer }

.show-grid [class^="col-"] {
    padding-top: 10px;
    padding-bottom: 10px;
    border: 1px solid #ddd;
    background-color: #eee !important;
}

.show-grid-small {
    border: 1px solid #ddd;
    background-color: #eee !important;
}

.show-grid-left {
    border: 1px solid #ddd;
    background-color: #eee !important;
    border-right-style: none;
}

.show-grid-right {
    border: 1px solid #ddd;
    background-color: #eee !important;
    border-left-style: none;
    text-align: right;
    font-size: xx-small;
    
}

.show-grid-center {
    padding-top: 10px;
    padding-bottom: 10px;
    border: 1px solid #ddd;
    background-color: #eee !important;
    border-right-style: none;
    font-weight: bold;
    font-size: 18px;
    text-align: center;
}


.border {
    border: 1px solid #ddd;
}

.dc-chart g.row text {
    fill: #403131;
    font-size: 12px;
    cursor: pointer;
}

</style>


<script type="text/javascript">

	var  dataTable;
	var faktSize;

d3.json("/syjservicesbcore/syjsFAKT_DB.do?user=OSCAR", function(error, data) {
	var faktData = data.dtoList;
   // console.log("faktData="+faktData);  //Tip: View i  Chrome devtool; NetWork-(mark xhr row)-Preview
    
    var dateFormat = d3.time.format('%Y%m%d');   
    var fullDateFormat = d3.time.format('%Y%m%d');
    var yearFormat = d3.time.format('%Y');
    var monthFormat = d3.time.format('%m');
   
    // normalize/parse data
	 _.each(faktData, function(d) {
	  d.dd = fullDateFormat.parse(d.hedtop);
	  d.year = yearFormat(d.dd);
	  d.month = monthFormat(d.dd);
	  d.faavd = d.faavd;
	  d.faopd = d.faopd;
	  d.sumfabeln = +d.sumfabeln;
	  d.hedtop = d.hedtop; 
	  d.fakda = d.fakda;
	  d.faopko = d.faopko;
	  d.trknfa = d.trknfa;
	});
 
	// set crossfilter. Crossfilter runs in the browser and the practical limit is somewhere around half a million to a million rows of data.
	var fakt = crossfilter(faktData);	
	var  all = fakt.groupAll();
	faktSize = fakt.size();
	
	var  kundeDim  = fakt.dimension(function(d) {return d.trknfa;});
	var  faktByYearDim  = fakt.dimension(function(d) {return d.year;});
	var  faktByMonthDim  = fakt.dimension(function(d) {return +d.month;});
	var  faktByAvdDim  = fakt.dimension(function(d) {return d.faavd;});
	var  faktByFakdaDim  = fakt.dimension(function(d) {return d.fakda;});
	var  faktByFaopkoDim  = fakt.dimension(function(d) {return d.faopko;});
	var  faktByYearDimGroup = faktByYearDim.group().reduceSum(function(d) {return +d.sumfabeln;});
	var  faktByAvdDimGroup = faktByAvdDim.group().reduceSum(function(d) {return +d.sumfabeln;});
	var  faktAllDim = fakt.dimension(function(d) {return d;});	 
	
	var  countFaktByMonth = faktByMonthDim.group().reduceCount();
	var  countFaktByFakda = faktByFakdaDim.group().reduceCount();
	var  countFaktByFaopko = faktByFaopkoDim.group().reduceCount();
	 
	var  yearChart   = dc.pieChart("#chart-ring-year");
	var  monthChart   = dc.pieChart('#chart-ring-month');
	var  avdChart   = dc.pieChart('#chart-ring-avd');
	var  fakdaChart   = dc.pieChart('#chart-ring-fakda');
	var  faopkoChart   = dc.pieChart('#chart-ring-faopko');
	dataTable = dc.dataTable('#data-table');
	var  yearlyBubbleChart = dc.bubbleChart('#yearly-bubble-chart');
	var  lineChartDate = dc.lineChart("#line-chart-date");
	var  moveChart = dc.lineChart('#monthly-move-chart');
	var  dataCount = dc.dataCount('#data-count')	 

	var  omsetningsDisplay = dc.numberDisplay("#omsetning");	
	var  kostnadsDisplay = dc.numberDisplay("#kostnad");	
	var  resultatDisplay = dc.numberDisplay("#resultat");	
	var  dbDisplay = dc.numberDisplay("#db");	
	
    // Dimension by month
    var moveMonths = fakt.dimension(function (d) {
        return d.month;
    });	
    // Group by total sumfabeln within month
    var monthlyMoveGroup = moveMonths.group().reduceSum(function (d) {
        return d.sumfabeln;
    });	

  
    var monthlyGroup =  moveMonths.group().reduce(   
            /* callback for when data is added to the current filter results */
            function (p, v) {
                ++p.count;
                if (v.fakda != 'K') {
                	p.omsetning += v.sumfabeln;   
                } else {
                	p.kostnad += v.sumfabeln; 
                }
                return p;
            },
            /* callback for when data is removed from the current filter results */
            function (p, v) {
                --p.count;
                if (v.fakda != 'K') {
                	p.omsetning -= v.sumfabeln;   
                } else {
                	p.kostnad -= v.sumfabeln; 
                }
                return p;
            },
            /* initialize p */
            function () {
                return {
                    count: 0,
                    omsetning: 0,
                    kostnad: 0,
                };
            }
    );  
    
    
    var  kundePerformanceGroup = kundeDim.group().reduce(
            /* callback for when data is added to the current filter results */
            function (p, v) {
            	++p.faavd;
            	++p.faopd;
                ++p.sumfabeln;
                ++p.count;
                p.sumAvd += p.faavd;
                p.sumTotal += p.sumfabeln;
                return p;    
            },
            /* callback for when data is removed from the current filter results */
            function (p, v) {
            	--p.faavd;
            	--p.faopd;
                --p.sumfabeln;
                --p.count;
                p.sumAvd -= p.faavd;
                p.sumTotal -= p.sumfabeln;
                return p;
            },
            /* initialize p */
            function () {
                return {faavd: 0,faopd: 0, sumfabeln: 0, sumTotal: 0, sumAvd: 0,  count: 0};
            }
        );	
    
    
    
    
    //TODO remove
    var  yearPerformanceGroup = faktByYearDim.group().reduce(
        /* callback for when data is added to the current filter results */
        function (p, v) {
        	++p.faavd;
        	++p.faopd;
            ++p.sumfabeln;
            ++p.count;
            p.sumAvd += p.faavd;
            p.sumTotal += p.sumfabeln;
            p.avgIndex = p.sumTotal / p.count;
            p.percentageGain = p.avgIndex ? (p.sumfabeln / p.avgIndex) * 100 : 0;
            return p;    
        },
        /* callback for when data is removed from the current filter results */
        function (p, v) {
        	--p.faavd;
        	--p.faopd;
            --p.sumfabeln;
            --p.count;
            p.sumAvd -= p.faavd;
            p.sumTotal -= p.sumfabeln;
            p.avgIndex = p.count ? p.sumTotal / p.count : 0;
            p.percentageGain = p.avgIndex ? (p.sumfabel / p.avgIndex) * 100 : 0;
            return p;
        },
        /* initialize p */
        function () {
            return {faavd: 0,faopd: 0, sumfabeln: 0, sumTotal: 0, sumAvd: 0, avgIndex: 0, percentageGain: 0, count: 0};
        }
    );	
	 
    var omsetningsGroup =  faktAllDim.group().reduce(   //all.reduce(   //  faktByYearDim
            /* callback for when data is added to the current filter results */
            function (p, v) {
                ++p.count;
                if (v.fakda != 'K') {
                	p.omsetning += v.sumfabeln;   
                } else {
                	p.kostnad += v.sumfabeln; 
                }
                return p;
            },
            /* callback for when data is removed from the current filter results */
            function (p, v) {
                --p.count;
                if (v.fakda != 'K') {
                	p.omsetning -= v.sumfabeln;   
                } else {
                	p.kostnad -= v.sumfabeln; 
                }
                return p;
            },
            /* initialize p */
            function () {
                return {
                    count: 0,
                    omsetning: 0,
                    kostnad: 0,
                };
            }
    );  
  
  
  
	  yearChart
	    .width(150)
	    .height(150)
	    .dimension(faktByYearDim)
	    .group(faktByYearDimGroup)
	    .innerRadius(30)
	    .controlsUseVisibility(true);
   
	  //TODO remmove
	  monthChart
        .width(150)
        .height(150)
        .dimension(faktByMonthDim)
        .group(countFaktByMonth)
        .innerRadius(30)
        .ordering(function (d) {
          var order = {
            'Jan': 1, 'Feb': 2, 'Mar': 3, 'Apr': 4,
            'May': 5, 'Jun': 6, 'Jul': 7, 'Aug': 8,
            'Sep': 9, 'Oct': 10, 'Nov': 11, 'Dec': 12
          };
          return order[d.key];
          });	  
	  

	  avdChart
	    .width(150)
	    .height(150)
	    .dimension(faktByAvdDim)
	    .group(faktByAvdDimGroup)
	    .innerRadius(30)
	    .controlsUseVisibility(true);
	  
	  //TODO remove
	  fakdaChart
	    .width(150)
	    .height(150)
	    .dimension(faktByFakdaDim)
	    .group(countFaktByFakda)
	    .innerRadius(30)
	    .controlsUseVisibility(true);
	  
	  faopkoChart
	    .width(150)
	    .height(150)
	    .dimension(faktByFaopkoDim)
	    .group(countFaktByFaopko)
	    .innerRadius(30)
	    .controlsUseVisibility(true);
	  
	  omsetningsDisplay
	     .group(omsetningsGroup)  
		 .valueAccessor(function (p) {
			// console.log("p.value.omsetning="+p.value.omsetning);
			 return p.value.omsetning;
		  })
		  .formatNumber(function(d){ return d3.format(",.0f")(d)});
	    
	  kostnadsDisplay
	     .group(omsetningsGroup)  
		 .valueAccessor(function (p) {
			// console.log("p.value.kostnad="+p.value.kostnad);
			 return p.value.kostnad;
		  })
		 .formatNumber(function(d){ return d3.format(",.0f")(d)});
	    
	  resultatDisplay
	     .group(omsetningsGroup)  
		 .valueAccessor(function (p) {
			var resultat = p.value.omsetning + p.value.kostnad;   // + = spooky algo
			return resultat;
		  })
		 .formatNumber(function(d){ return d3.format(",.0f")(d)});

	  dbDisplay
	     .group(omsetningsGroup)  
		 .valueAccessor(function (p) {
			var resultat = p.value.omsetning + p.value.kostnad;   // + = spooky algo
			var db = resultat / p.value.omsetning;
			return db;
		  })
		 .formatNumber(d3.format(".2%")); 
	  
	    //#### Bubble Chart
	    //Create a bubble chart and use the given css selector as anchor. You can also specify
	    //an optional chart group for this chart to be scoped within. When a chart belongs
	    //to a specific group then any interaction with the chart will only trigger redraws
	    //on charts within the same chart group.
	    // <br>API: [Bubble Chart](https://github.com/dc-js/dc.js/blob/master/web/docs/api-latest.md#bubble-chart)
	    yearlyBubbleChart /* dc.bubbleChart('#yearly-bubble-chart', 'chartGroup') */
	        // (_optional_) define chart width, `default = 200`
	        .width(1000)
	        // (_optional_) define chart height, `default = 200`
	        .height(320)
	        // (_optional_) define chart transition duration, `default = 750`
	        .transitionDuration(1500)
		    .margins({top: 20, right: 10, bottom: 30, left: 90})
		    .dimension(kundeDim)
	        //The bubble chart expects the groups are reduced to multiple values which are used
	        //to generate x, y, and radius for each key (bubble) in the group
	        .group(kundePerformanceGroup)
	        // (_optional_) define color function or array for bubbles: [ColorBrewer](http://colorbrewer2.org/)
	        .colors(colorbrewer.RdYlGn[9])
	        //(optional) define color domain to match your data domain if you want to bind data or color
	        .colorDomain([-500, 500])
	   		 //##### Accessors

	        //Accessor functions are applied to each value returned by the grouping
	        // `.colorAccessor` - the returned value will be passed to the `.colors()` scale to determine a fill color
	        .colorAccessor(function (d) {
	            return d.value.sumfabeln;
	        })
	        // `.keyAccessor` - the `X` value will be passed to the `.x()` scale to determine pixel location
	        .keyAccessor(function (p) {
	           // return p.value.sumfabeln;
	            return p.value.sumTotal;
	        })
	        // `.valueAccessor` - the `Y` value will be passed to the `.y()` scale to determine pixel location
	        .valueAccessor(function (p) {
	            return p.value.sumfabeln;
	            //return p.value.sumTotal;
	        })
	        // `.radiusValueAccessor` - the value will be passed to the `.r()` scale to determine radius size;
	        //   by default this maps linearly to [0,100]
	        .radiusValueAccessor(function (p) {
	            return p.value.sumfabeln;
	        })
	        .maxBubbleRelativeSize(0.3)
	        .x(d3.scale.linear().domain([-2500, 2500]))
	        .y(d3.scale.linear().domain([-100, 100]))  //-100, 100
	        .r(d3.scale.linear().domain([0, 4000]))
	        //##### Elastic Scaling
	        //`.elasticY` and `.elasticX` determine whether the chart should rescale each axis to fit the data.
	        .elasticY(true)
	        .elasticX(true)
	        //`.yAxisPadding` and `.xAxisPadding` add padding to data above and below their max values in the same unit
	        //domains as the Accessors.
	        .yAxisPadding(100)
	        .xAxisPadding(500)
	        // (_optional_) render horizontal grid lines, `default=false`
	        .renderHorizontalGridLines(true)
	        // (_optional_) render vertical grid lines, `default=false`
	        .renderVerticalGridLines(true)
	        // (_optional_) render an axis label below the x axis
	        .xAxisLabel('Omsetning')
	        // (_optional_) render a vertical axis lable left of the y axis
	        .yAxisLabel('Antall oppdrag')
	        //##### Labels and  Titles

	        //Labels are displayed on the chart for each bubble. Titles displayed on mouseover.
	        // (_optional_) whether chart should render labels, `default = true`
	        .renderLabel(true)
	        .label(function (p) {
	            return p.key;
	        })

	        .renderTitle(true)
	        .title(function (p) {
	            return [
	                p.key,
	                'NOK: ' + p.value.sumTotal,
	                'Antall oppdrag: ' + p.value.sumfabeln
	            ].join('\n');
	        })	        
	        
	        
	        //#### Customize Axes
	        // Set a custom tick format. Both `.yAxis()` and `.xAxis()` return an axis object,
	        // so any additional method chaining applies to the axis, not the chart.
		    //    .yAxis().tickFormat(function (v) {
		    //        return v + '%';
		    //    });
 	 
		    //https://github.com/dc-js/dc.js/blob/develop/web/play-ground.html        
	        lineChartDate
	        		.width(1000)
	                .height(325)
	                .margins({top: 20, right: 10, bottom: 30, left: 80})
	                //.dimension(faktByYearDim)
	                //.group(faktByYearDimGroup, "Id Sum")
					.dimension(moveMonths) // moveMonths  
					.group(monthlyGroup, "Kostnad")  
                    .valueAccessor(function (d) {
                        return d.value.kostnad /1000; 
                    })
                    .yAxisLabel("NOK")
		        	.xAxisLabel("Måned")          
		        	.stack(monthlyGroup, "Omsetning", function (d) {
	                    return d.value.omsetning /1000;
	               })
	                .brushOn(true)
	               .title(function (d) {
	                    return d.value;
	                })
	               // .x(d3.time.scale().domain([mindate, maxdate]))
	                .x(d3.scale.linear().domain([1,12]))
	                .xUnits(d3.time.month)
	                .elasticY(true)
	                .renderHorizontalGridLines(true)
	                .renderArea(true)
	                .legend(dc.legend().x(900).y(20).itemHeight(5).gap(20));
	               // .xAxis().ticks(5);	 
	               //.title(function (d) {
                   //    var value = d.value ? d.value : d.value;
                   //    if (isNaN(value)) {
                   //        value = 0;
                   //    }
                   //    return dateFormat(d.key) + '\n' + numberFormat(value);
                   //});
	
               
	        	//Specify an area chart by using a line chart with `.renderArea(true)`.
               // <br>API: [Stack Mixin](https://github.com/dc-js/dc.js/blob/master/web/docs/api-latest.md#stack-mixin),
               // [Line Chart](https://github.com/dc-js/dc.js/blob/master/web/docs/api-latest.md#line-chart)
               //TODO remove
               moveChart /* dc.lineChart('#monthly-move-chart', 'chartGroup') */
                   .renderArea(true)
                   .width(990)
                   .height(200)
                   .transitionDuration(1000)
                   .margins({top: 30, right: 50, bottom: 25, left: 40})
                   .dimension(moveMonths)
                   .mouseZoomable(true)
               // Specify a "range chart" to link its brush extent with the zoom of the current "focus chart".
                  // .rangeChart(volumeChart)
                   .x(d3.time.scale().domain([1,12])) // .x(d3.time.scale().domain([new Date(2000, 0, 1), new Date(2015, 11, 31)])) 
                   .round(d3.time.month.round)
                   .xUnits(d3.time.months)
                   .elasticY(true)
                   .renderHorizontalGridLines(true)
               //##### Legend

                   // Position the legend relative to the chart origin and specify items' height and separation.
                   .legend(dc.legend().x(800).y(10).itemHeight(13).gap(5))
                   .brushOn(false)
                   // Add the base layer of the stack with group. The second parameter specifies a series name for use in the
                   // legend.
                   // The `.valueAccessor` will be used for the base layer
                   .group(monthlyMoveGroup, 'Monthly Index Average')  //indexAvgByMonthGroup
                   .valueAccessor(function (d) {
                       return d.value /1000; //d.value.avg
                   })
                   // Stack additional layers with `.stack`. The first paramenter is a new group.
                   // The second parameter is the series name. The third is a value accessor.
                   .stack(monthlyMoveGroup, 'Monthly Index Move', function (d) {
                       return d.value / 1000;
                   })
                   // Title can be called by any stack layer.
                  // .title(function (d) {
                  //     var value = d.value ? d.value : d.value;
                  //     if (isNaN(value)) {
                  //         value = 0;
                  //     }
                  //     return dateFormat(d.key) + '\n' + numberFormat(value);
                   //});
       
	   // register handlers
	   d3.selectAll('a#all').on('click', function () {
	     dc.filterAll();
	     dc.renderAll();
	   });

	   d3.selectAll('a#year').on('click', function () {
	     yearChart.filterAll();
	     dc.redrawAll();
	   });

	   d3.selectAll('a#month').on('click', function () {
	     monthChart.filterAll();
	     dc.redrawAll();
	   });
	   d3.selectAll('a#kunde').on('click', function () {
		  yearlyBubbleChart.filterAll();
		  dc.redrawAll();
		});	   
	   d3.selectAll('a#intekkt').on('click', function () {
			  lineChartDate.filterAll();
			  dc.redrawAll();
	   });	
	   
	   d3.selectAll('a#avd').on('click', function () {
		 avdChart.filterAll();
		 dc.redrawAll();
	   });	   
	   d3.selectAll('a#fakda').on('click', function () {
			 fakdaChart.filterAll();
			 dc.redrawAll();
	   });	
	   d3.selectAll('a#faopko').on('click', function () {
			 faopkoChart.filterAll();
			 dc.redrawAll();
	   });		   
	   
	   

	   dataCount
	      .dimension(fakt)
	      .group(all);  

	   dataTable
	    .dimension(faktAllDim)
	    .group(function (d) { return 'dc.js insists on putting a row here so I remove it using JS'; })
	    .size(Infinity) 
	    .columns([
	      function (d) { return d.faavd; },
	      function (d) { return d.faopd; },
	      function (d) { return d.sumfabeln; },
	      function (d) { return d.hedtop; },
	      function (d) { return d.fakda; },
	      function (d) { return d.faopko; },
	      function (d) { return d.trknfa; }
	    ])
	   // .sortBy(dc.pluck('rating_score'))
	    .order(d3.descending)
	    .on('renderlet', function (table) {
	      	// each time table is rendered remove nasty extra row dc.js insists on adding
	     	table.select('tr.dc-table-group').remove();
	  	});
	   
	    faktSize = fakt.size();
	    updateDataTable();
	  
	dc.renderAll(); 
    
 });
 
	var ofs = 0, pag = 20;
	function display() {
	    d3.select('#begin').text(ofs);
	    d3.select('#end').text(ofs+pag-1);
	    d3.select('#last').attr('disabled', ofs-pag<0 ? 'true' : null);
	    d3.select('#next').attr('disabled', ofs+pag>=faktSize ? 'true' : null);
	    d3.select('#size').text(faktSize);
	}
	function updateDataTable() {
	//	alert("faktSize="+faktSize);
		dataTable.beginSlice(ofs);
		dataTable.endSlice(ofs+pag);
	    display();
	}
	function next() {
	    ofs += pag;
	    updateDataTable();
	    dataTable.redraw();
	}
	function last() {
	    ofs -= pag;
	    updateDataTable();
	    dataTable.redraw();
	}

</script>

<table width="100%" class="text11" cellspacing="0" border="0" cellpadding="0">
	<tr>
		<td>
		<%-- tab container component --%>
		<table width="100%" class="text11" cellspacing="0" border="0" cellpadding="0">
			<tr height="2"><td></td></tr>
				<tr height="25"> 
					<td width="20%" valign="bottom" class="tab" align="center" nowrap>
						<font class="tabLink">&nbsp;Lønnsomhetsanalyse</font>
						<img valign="bottom" src="resources/images/list.gif" border="0" alt="general list">
					</td>
					<td width="80%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>	
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
					<div class="col-md-12">
					  <div class="row ">
		  				<div class="col-md-3 show-grid-center">
  						       Omsetning
  						 </div>
			  			 <div class="col-md-3 show-grid-center">
  						        Kostnad
  						 </div> 
			  			 <div class="col-md-3 show-grid-center">
  						        Resultat
  						 </div> 
 			  		     <div class="col-md-3 show-grid-center">
  						        DB
  						 </div>    						       						     						    
					  </div> 
					  <div class="row border">
						<div class="col-md-3 padded" id="omsetning" align="center"></div>
				        <div class="col-md-3 padded" id="kostnad" align="center"></div>  
				        <div class="col-md-3 padded" id="resultat" align="center"></div>  
				        <div class="col-md-3 padded" id="db" align="center"></div>  
					  </div> 					  
					</div>		
				  </div>
				  <div class="row">
				    <div class="col-md-2">
				      <div class="row">
   						 <div class="col-md-6 show-grid-left">
   						    År 
   						 </div>
  	  				    <div class="col-md-6 show-grid-right">
							 <a id="year">tilbakestill</a>			    
	  				    </div> 						 
   					  </div>
   					  <div class="row border">
				        <div class="col-md-12 dc-chart" id="chart-ring-year" align="center">
				   			<div><span class="filter"></span></div>
				        </div> 						 
				      </div>
				      <div class="row">
	  					<div class="col-md-6 show-grid-left">
	  						Avdeling 
	  					</div>	
	  				    <div class="col-md-6 show-grid-right">
							 <a id="avd">tilbakestill</a>			    
	  				    </div>
	  				  </div>
	  				  <div class="row border">
					      <div class="col-md-12 dc-chart" id="chart-ring-avd" align="center">
							  <div><span class="filter"></span></div>
					      </div>
				      </div>
				    </div>
				    <div class="col-md-10">
				      <div class="row">
   						 <div class="col-md-6 show-grid-left">
   						    Omsetning og kostnad 
   						 </div>
   	  				    <div class="col-md-6 show-grid-right">
   	  				     <a id="intekkt">tilbakestill</a>	
	  				    </div>						 
				      </div>
				      <div class="row border">
						 <div class=" col-md-12 dc-chart" id="line-chart-date" align="center">
						 	<div><span class="filter"></span></div>
						 </div>  <!--    , monthly-move-chart-->  
				      </div>
				    </div>
				  </div>
				  
				  <div class="row">
				    <div class="col-md-2">
				      <div class="row">
	  					<div class="col-md-6 show-grid-left">
	  						Faopko
	  				    </div>
						<div class="col-md-6 show-grid-right">
							<a id="faopko">tilbakestill</a>
						</div>
	  				  </div>
	  				  <div class="row border">
					      <div class="col-md-12 dc-chart" id="chart-ring-faopko" align="center">
						    <div><span class="filter"></span></div>
					      </div>
				      </div>

				      <div class="row">
	  					<div class="col-md-6 show-grid-left">
	  						Fakda
	  				    </div>
						<div class="col-md-6 show-grid-right">
							<a id="fakda">tilbakestill</a>
						</div>
	  				  </div>
	  				  <div class="row border">
					      <div class="col-md-12 dc-chart" id="chart-ring-fakda" align="center">
						    <div><span class="filter"></span></div>
					      </div>
				      </div>				      
	
					</div>
 
				    <div class="col-md-10">
				      <div class="row">
   						 <div class="col-md-6 show-grid-left">
   						   Kunde
   						 </div>
 						<div class="col-md-6 show-grid-right">
							<a id="kunde">tilbakestill</a>
						</div>  						 
				      </div>
				      <div class="row border">
						 <div class="col-md-12" id="yearly-bubble-chart">
						 	<div class="filterBold"><span class="filter"></span></div>
						 </div>
				      </div>
					</div>

				  </div>	
	
				  <div class="row">
				    <div class="col-md-6 show-grid-left" id="data-count"> 
				        <span class="filter-count"></span> faktura poster valgt ut av <span class="total-count"></span> poster.
				    </div>
				    <div class="col-md-6 show-grid-right">
						<a id="all">tilbakestill alt</a>
					</div> 
				  </div>
	
				  <div class="padded-row">&nbsp;</div>
	
				  <div class="row">
				    <div class="col-md-12 show-grid-small">
				      <table class="table table-bordered table-striped" id="data-table">
				        <thead>
				          <tr class="header">
				            <th>faavd</th>
				            <th>faopd</th>
				            <th>sumfabeln</th>
				            <th>hedtop</th>
				            <th>fakda</th>
					        <th>faopko</th>		
					        <th>trknfa</th>		
				          </tr>
				        </thead>
				      </table>
				      <div id="paging">
   						<!--  Viser <span id="begin"></span>-<span id="end"></span> av <span id="size"></span>.-->
    					<input id="last" type="Button" value="forrige" onclick="javascript:last()" />
    					<input id="next" type="button" value="neste" onclick="javascript:next()"/>
  					  </div>
				    </div>
				  </div>
				</div>
		 	   </td>
	 	    </tr>
	 	    
	 		</table>
		</td>
	</tr>
 			
	 
    
</table>	
		
<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->

