<%@ include file="/WEB-INF/views/include.jsp" %>
<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerReportDashboard.jsp" />
<!-- =====================end header ==========================-->
<script type="text/javascript">
var  dataTable;
var faktSize;
var ofs = 0, pag = 20;
var url = "/syjservicesbcore/syjsFAKT_DB.do?user=${user.user}&year=2017";
//var url = "/syjservicesbcore/syjsFAKT_DB.do?user=NO_NAME&year=2017";  //TODO back to name

var jq = jQuery.noConflict();
var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";

function setBlockUI(element){
  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
}



d3.json(url, function(error, data) {
	//setBlockUI(this);  //TODO
	var faktData = data.dtoList;
   // console.log("faktData="+faktData);  //Tip: View i  Chrome devtool; NetWork-(mark xhr row)-Preview
    
    var fullDateFormat = d3.time.format('%Y%m%d');
    var yearFormat = d3.time.format('%Y');
    var monthFormat = d3.time.format('%m');
   
    // normalize/parse data
	 _.each(faktData, function(d) {
	  d.date = fullDateFormat.parse(d.hedtop);
	  d.year = yearFormat(d.date);
	  d.month = monthFormat(d.date);
	  d.tupro = d.tupro;
	  d.tubilk = d.tubilk;
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
	
	//Dimensions
	var  faktAllDim = fakt.dimension(function(d) {return d;});	 
	//var  kundeDim  = fakt.dimension(function(d) {return d.trknfa;});
	var  dateDim  = fakt.dimension(function(d) {return d.date;});
	var  yearDim  = fakt.dimension(function(d) {return d.year;});
    var  monthDim = fakt.dimension(function (d) {
    	return d.month;
    	});	
    //var  monthDim2 = fakt.dimension(d => d3.time.month(d.month));
	var  avdDim  = fakt.dimension(function(d) {return d.faavd;});
	var  tubilkDim  = fakt.dimension(function(d) {return d.tubilk;});
	//Groups
	//var  dateDimGroup = dateDim.group().reduceSum(function(d) {return +d.sumfabeln;});
	//var  monthDimGroup = monthDim.group().reduceSum(function(d) {return d.sumfabeln;});
	//var  monthDimGroup2 = monthDim2.group().reduceSum(d => d.sumfabeln);
	
	var  yearDimGroup = yearDim.group().reduceSum(function(d) {return d.sumfabeln;});
	var  avdDimGroup = avdDim.group().reduceSum(function(d) {return d.sumfabeln;});
	var  tubilkDimGroup = tubilkDim.group().reduceSum(function(d) {return d.sumfabeln;});
	//Charts 
	var  yearChart   = dc.pieChart("#chart-ring-year");
	var  avdChart   = dc.pieChart('#chart-ring-avd');
	var  tubilkChart   = dc.pieChart('#chart-ring-tubilk');
	//var  yearlyBubbleChart = dc.bubbleChart('#yearly-bubble-chart');
	var  compositeLineChart = dc.compositeChart("#line-chart-composite");
	//var  compositeStackedChart = dc.compositeChart("#stacked-chart-composite");
	var  stackedBarChart = dc.barChart("#stacked-bar-chart");
	//var  lineChartDate = dc.lineChart("#line-chart-date");
	var  dataCount = dc.dataCount('#data-count')	 
	var  omsetningsDisplay = dc.numberDisplay("#omsetning");	
	var  kostnadsDisplay = dc.numberDisplay("#kostnad");	
	var  resultatDisplay = dc.numberDisplay("#resultat");	
	var  dbDisplay = dc.numberDisplay("#db");	
	dataTable = dc.dataTable('#data-table');
	
	var mindate = dateDim.bottom(1)[0].date;
	var maxdate = dateDim.top(1)[0].date;
	
	//Group reduce
    var dateDimGroup =  dateDim.group().reduce(   //TESTAR: org=dateDimGroup
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
	
    var monthDimGroup =  monthDim.group().reduce(   
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
	
	
	
	
	
/*
	var  kundePerformanceGroup = kundeDim.group().reduce(
           
            function (p, v) {
            	++p.faavd;
            	++p.faopd;
                ++p.sumfabeln;
                ++p.count;
                p.sumAvd += p.faavd;
                p.sumTotal += p.sumfabeln;
                return p;    
            },
            
            function (p, v) {
            	--p.faavd;
            	--p.faopd;
                --p.sumfabeln;
                --p.count;
                p.sumAvd -= p.faavd;
                p.sumTotal -= p.sumfabeln;
                return p;
            },
           
            function () {
                return {faavd: 0,faopd: 0, sumfabeln: 0, sumTotal: 0, sumAvd: 0,  count: 0};
            }
        );	
 */   
    var omsetningsGroup =  faktAllDim.group().reduce(  
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
	    .width(350)
	    .height(300)
	    .dimension(yearDim)
	    .group(yearDimGroup)
	    //.externalLabels(20)
	    .externalRadiusPadding(50)
	    .innerRadius(30)
	    .legend(dc.legend().y(10).itemHeight(8).gap(3));
   
	avdChart
	    .width(350)
	    .height(300)
	    .slicesCap(25)
	    .innerRadius(40)
	    //.externalLabels(20)
	    .externalRadiusPadding(50)
	    .dimension(avdDim)
	    .group(avdDimGroup)
	    .legend(dc.legend().y(10).itemHeight(8).gap(3));
/*	
	avdChart.on('pretransition', function(chart) {
        chart.selectAll('.dc-legend-item text')
            .text('')
          .append('tspan')
            .text(function(d) { return d.name; })
          .append('tspan')
            .attr('x', 100)
            .attr('text-anchor', 'end')
            .text(function(d) { return d.data; });
    });	
*/	
	
	tubilkChart
	    .width(350)
	    .height(300)
	    .slicesCap(20)
	    .innerRadius(40)
	    //.externalLabels(20)
	    .externalRadiusPadding(50)
	    .dimension(tubilkDim)
	    .group(tubilkDimGroup)
	    .legend(dc.legend().y(10).itemHeight(8).gap(3));
	
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
//	      .html({
//	        one:"<span style=\"color:green; font-size: 26px;\">%number</span> Monkey",
//	        some:"<span style=\"color:red; font-size: 26px;\">%number</span> Total Monkeys",
//	        none:"<span style=\"color:steelblue; font-size: 26px;\">No</span> Monkeys"
//	      })		  
		  
		 .formatNumber(d3.format(".2%")); 
	  
/*
	yearlyBubbleChart 
	        .width(1000)
	        .height(320)
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
	            return p.value.sumTotal;
	        })
	        // `.valueAccessor` - the `Y` value will be passed to the `.y()` scale to determine pixel location
	        .valueAccessor(function (p) {
	            return p.value.sumfabeln;
	        })
	        // `.radiusValueAccessor` - the value will be passed to the `.r()` scale to determine radius size;
	        //   by default this maps linearly to [0,100]
	        .radiusValueAccessor(function (p) {
	            return p.value.sumfabeln;
	        })
	        .maxBubbleRelativeSize(0.3)
	        .x(d3.scale.linear().domain([-2500, 2500]))
	        .y(d3.scale.linear().domain([-100, 100]))
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

	        
	        
	lineChartDate
       		.width(1000)
            .height(325)
            .margins({top: 20, right: 10, bottom: 30, left: 80})
			.dimension(monthDim) 
			.group(dateDimGroup, "Kostnad")  
            .valueAccessor(function (d) {
                   return d.value.kostnad; 
             })
            .yAxisLabel("NOK")
        	.xAxisLabel("Måned")          
        	.stack(dateDimGroup, "Omsetning", function (d) {
                   return d.value.omsetning;
            })
            .brushOn(true)
            .title(function (d) {
                  return d.value;
            })
            .x(d3.scale.linear().domain([1,12]))
            .xUnits(d3.time.month)
            .elasticY(true)
            .renderHorizontalGridLines(true)
            .renderArea(true)
            //.x(d3.time.scale().domain([new Date(2017, 1, 1), new Date(2017, 12, 30)]))
	        //.xUnits(d3.time.days)
            .legend(dc.legend().x(900).y(20).itemHeight(5).gap(20));
*/


	compositeLineChart
		    .width(1200)
		    .height(400)
		    .margins({top: 20, right: 10, bottom: 30, left: 80})
			.x(d3.time.scale().domain([mindate, maxdate])) 	
			.xUnits(d3.time.months) 	
			//.x(d3.time.scale().domain([0,12]))
            //.xUnits(d3.time.month)
            .yAxisLabel("NOK")
            .elasticY(true)
        	.xAxisLabel("Måned")      
            .mouseZoomable(false)
        	.legend(dc.legend().x(1100).y(20).itemHeight(5).gap(20))
		    .renderHorizontalGridLines(true)
		    .compose([
		         dc.lineChart(compositeLineChart)
		            .dimension(dateDim) 
		            .colors('blue')
		            .group(dateDimGroup, "Omsetning") //dateDimGroup
			        .valueAccessor(function (d) {
                		return d.value.omsetning; 
        			}),            
		            //.dashStyle([5,5]),
		    	dc.lineChart(compositeLineChart)
		            .dimension(dateDim) 
		            .colors('red')
		            .group(dateDimGroup, "Kostnad")
		            .valueAccessor(function (d) {
                   		return d.value.kostnad; 
           			}),
		           // .dashStyle([2,2]),
			    dc.lineChart(compositeLineChart)
		            .dimension(dateDim) //dateDim
		            .colors('green')
		            .group(dateDimGroup, "Resultat")
					.valueAccessor(function (d) {
						var resultat = d.value.omsetning + d.value.kostnad;   // + = spooky algo
						return resultat;
					 })
		            .dashStyle([5,5])     
		        ])
		    .brushOn(false);
	   
/*
	compositeStackedChart
		.width(1200)
		.height(400)
		.margins({top: 20, right: 10, bottom: 30, left: 80})
		.legend(dc.legend().x(1100).y(20).itemHeight(5).gap(20))
		.x(d3.time.scale().domain([mindate, maxdate])) 	
		.xUnits(d3.time.months) 
		.yAxisLabel("NOK")
		.xAxisLabel("Måned")   
	    .elasticY(true)
		.renderHorizontalGridLines(true)
		.compose([
		     dc.barChart(compositeStackedChart)
		        .dimension(dateDim)
		        .group(dateDimGroup, "Kostnad")      
		        .valueAccessor(function (d) {
		       		return d.value.kostnad; 
				})	        
		])
		.brushOn(true);	
 */  

 
 var minDate2 = monthDim.bottom(1)[0].date;
 var maxDate2  = monthDim.top(1)[0].date;
 //minDate2=d3.time.day.offset(minDate2, -40);
// console.log("minDate2="+minDate2,"maxDate2="+maxDate2);  
// console.log("monthDim="+monthDim);
// console.log("monthDim.top(Infinity)="+monthDim.top(Infinity));

 stackedBarChart
		.width(1200)
		.height(400)
		.margins({top: 20, right: 10, bottom: 30, left: 80})
		//.brushOn(false)
		//.mouseZoomable(false)
		//.centerBar(false)
		//.gap(10)
		//.elasticY(true)
		//.renderHorizontalGridLines(true)
		//.x(d3.time.scale().domain([mindate2, maxdate2])) 	
		//.xUnits(d3.time.months)
		//.round(d3.time.month.round)
		//.alwaysUseRounding(true)
        //.dimension(monthDim) 
 	    //.group(monthDimGroup);

		// .width(400)
		// .height(260)
		 //.x(d3.time.scale().domain([minDate2, maxDate2]))
		 //.xUnits(d3.time.months)
		 
         //.x(d3.scale.ordinal())  //funkar!!, time scale funkar inte!!
         .x(d3.scale.linear().domain([0,13])) //Funkar, enskilt!!
         //.xUnits(d3.time.months) //funkar men med tunna streck, ihop med linear
 		 //.x(d3.time.scale().domain([0,12]))  //funkar, men med tunna streck
 		 //.x(d3.time.scale().domain([minDate2, maxDate2]))  //Funkar inte
 		 //.x(d3.scale.linear().domain([minDate2, maxDate2]))  //Funkar inte
         //.xUnits(d3.time.month)        
         
         //.x(d3.scale.ordinal().domain([1,12]))  //Funkar inte, smetar ut fel..
         
         //.xUnits(dc.units.ordinal)
         //.xUnits(d3.time.months)
         .yAxisLabel("NOK")
         .xAxisLabel("Måned")               
         .ordering(function(d) { return d.key; })	
		 .dimension(monthDim)

		 .group(monthDimGroup, "Kostnad", function (d) {
        	 //console.log("d.value.kostnad="+d.value.kostnad);
             return Math.abs(d.value.kostnad);
         })
         .stack(monthDimGroup, "Omsetning", function (d) {
             var omsetningStacked = d.value.omsetning + Math.abs(d.value.kostnad);
             //console.log("omsetningStacked="+omsetningStacked);
             return omsetningStacked;
         })
         
         .renderHorizontalGridLines(true)
         .renderVerticalGridLines(true)
         //.renderTitle(true)
         //.title(function(d) {
        //	 	//console.log("title="+d.key + '[' + this.layer + ']: ' + d.value[this.layer]);
         //     return d.key + '[' + this.layer + ']: ' + d.value[this.layer];
         //})        
   		 .renderLabel(true)  
		 .elasticY(true)
		 .gap(1)
		 .centerBar(true)
		 //.xAxisPadding(15)
		 //.xAxisPaddingUnit('month')
//		 .xAxis().tickFormat(function(d){
//        	  console.log("d.key="+d.key);
//        	  console.log("d.data.key="+d.data.key);
//        	  console.log("d.value="+d.value);
//        	  return d3.time.months;
//          })
			
		 .legend(dc.legend().x(1100).y(20).itemHeight(5).gap(20))
		 
		 
		 
		 
		 
		 .brushOn(true);
		// .clipPadding(20);

	/*	inspiration
         var barChartDateB = dc.barChart("#bar-chart-dateB", "groupB");
         barChartDateB.width(500)
                 .height(150)
                 .dimension(dateDimension)
                 .group(dateIdSumGroup)
                 .stack(dateValueSumGroup)
                 .stack(dateValueSumGroup, function (d) {
                     return 10;
                 })
                 .brushOn(false)
                 .title(function (d) {
                     return d.value;
                 })
                 .x(d3.time.scale().domain([new Date(2012, 4, 20), new Date(2012, 07, 15)]))
                 .xUnits(d3.time.days)
                 .renderHorizontalGridLines(true)
                 .renderVerticalGridLines(true)
                 .gap(0)
                 .xAxis().tickValues([new Date(2012, 3, 1), new Date(2012, 6, 1), new Date(2012, 9, 1)]);
         barChartDateB.yAxis().tickValues([0, 75, 140]);
	*/	
		
		
		
		
        // stackedBarChart.xAxis().tickFormat(d3.time.format('%B'));
		 
 
//        .group(dateDimGroup, "Kostnad")
//    	.valueAccessor(function (d) {
//		       return d.value.kostnad; 
//		});
  //Diverse grejer till datum   
//https://jsfiddle.net/pramod24/q4aquukz/4/



   	d3.selectAll('a#all').on('click', function () {
     	dc.filterAll();
     	dc.renderAll();
   	});

	d3.selectAll('a#year').on('click', function () {
		yearChart.filterAll();
		dc.redrawAll();
	});
	d3.selectAll('a#intekkt').on('click', function () {
		alert("WTF");
		compositeLineChart.filterAll();
		dc.redrawAll();
	});
	d3.selectAll('a#avd').on('click', function () {
		avdChart.filterAll();
		dc.redrawAll();
	});	 
	d3.selectAll('a#tubilk').on('click', function () {
		tubilkChart.filterAll();
		dc.redrawAll();
	});	
	d3.selectAll('a#stacked-bar').on('click', function () {
		stackedBarChart.filterAll();
		dc.redrawAll();
	});		

	d3.selectAll('a#avdfilter').on('click', function () {
		avdChart.filter(jq('#avd-filter').val());
		dc.redrawAll();
		jq('#avd-filter').val("");
	});		

	dataCount
	      .dimension(fakt)
	      .group(all);  

	dataTable
	    .dimension(faktAllDim)
	    .group(function (d) { return 'dc.js insists on putting a row here so I remove it using JS'; })
	    .size(Infinity) 
	    .columns([
	      function (d) { return d.tupro; },
	      function (d) { return d.tubilk; },
	      function (d) { return d.faavd; },
	      function (d) { return d.faopd; },
	      function (d) { return d.sumfabeln; },
	      function (d) { return d.hedtop; },
	      function (d) { return d.fakda; },
	      function (d) { return d.faopko; },
	      function (d) { return d.trknfa; }
	    ])
	    .order(d3.descending)
	    .on('renderlet', function (table) {
	      	// each time table is rendered remove nasty extra row dc.js insists on adding
	     	table.select('tr.dc-table-group').remove();
	 });
	   
	faktSize = fakt.size();
	updateDataTable();
	  
	dc.renderAll(); 
    
});
 
function display() {
    d3.select('#begin').text(ofs);
    d3.select('#end').text(ofs+pag-1);
    d3.select('#last').attr('disabled', ofs-pag<0 ? 'true' : null);
    d3.select('#next').attr('disabled', ofs+pag>=faktSize ? 'true' : null);
    d3.select('#size').text(faktSize);
}
function updateDataTable() {
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
						<font class="tabLink">&nbsp;Trafikkregnskap</font>
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
	
	
				  
				  </div>
				  <div class="row">
					<div class="col-md-12">
					  <div class="row ">
		  				<div class="col-md-3 show-grid-center-large">
  						       Omsetning
  						 </div>
			  			 <div class="col-md-3 show-grid-center-large">
  						        Kostnad
  						 </div> 
			  			 <div class="col-md-3 show-grid-center-large">
  						        Resultat
  						 </div> 
 			  		     <div class="col-md-3 show-grid-center-large">
  						        Dekningsbidrag
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
				    <div class="col-md-12">
				      <div class="row">
   						 <div class="col-md-12 show-grid-left">
   						    Omsetning og kostnad 
   						 </div>
				      </div>
				      <div class="row border">
						 <div class="col-md-12 dc-chart" id="line-chart-composite" align="center"> 
						 	<!--  <div><span class="filter"></span></div>-->
						 </div>  
				      </div>
				    </div>
				  </div>
				  
				  
				  <div class="row">
					<div class="col-md-12">
					  <div class="row ">
		  				<div class="col-md-4 show-grid-left">
  						       År
  						</div>
		  				<div class="col-md-2 show-grid-left">
  						       Avdeling
  						</div>
    				    <div class="col-md-2 show-grid-right">
						  avd:<input id="avd-filter" type="text" size="5"/>
						  <a id="avdfilter">legg til filter</a>			    
  				    	</div>						
  						
		  				<div class="col-md-4 show-grid-left">
  						       Bilkode
  						</div>
 
					  </div> 
					  <div class="row border">
						<div class="col-md-4 border" id="chart-ring-year" align="center">
						    <span class="reset" style="display: none;">filter: <span class="filter"></span></span>
						    <a class="reset" id="year" style="display: none;"> - <i>tilbakestill filter</i></a>
				        </div>
				        <div class="col-md-4 border" id="chart-ring-avd" align="center">
						    <span class="reset" style="display: none;">filter: <span class="filter"></span></span>
						    <a class="reset" id="avd" style="display: none;"> - <i>tilbakestill filter</i></a>
				        </div>
				        <div class="col-md-4 border" id="chart-ring-tubilk" align="center">
						    <span class="reset" style="display: none;">filter: <span class="filter"></span></span>
						    <a class="reset" id="tubilk" style="display: none;"> - <i>tilbakestill filter</i></a>
				        </div>  
					  </div> 					  
					</div>		
				  </div>	
				  
				  <div class="row">
				    <div class="col-md-12">
				      <div class="row">
   						 <div class="col-md-12 show-grid-left">
   						    Omsetning og kostnad /måned 
   						 </div>
				      </div>

				      <div class="row">
						 <div class="col-md-12" id="stacked-bar-chart"  align="center"> 
						    <span class="reset" style="display: none;">filter: <span class="filter"></span></span>
						    <a class="reset" id="stacked-bar" style="display: none;"> - <i>tilbakestill filter</i></a>
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
				            <th>tupro</th>
				            <th>tubilk</th>
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

