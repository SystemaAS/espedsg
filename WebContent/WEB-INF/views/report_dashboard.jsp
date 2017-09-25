<%@ include file="/WEB-INF/views/include.jsp" %>
<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerReportDashboard.jsp" />
<!-- =====================end header ==========================-->

<style>

/*
#accordion .ui-accordion-header{
	display: block;
	cursor: pointer;
	position: relative;
	margin: 2px 0 0 0;
	padding: .0em .0em .0em .0em;
	font-size: 100%;	
	width: 96%;
}
*/

</style>
<script type="text/javascript">
var  dataTable;
var faktSize;
var ofs = 0, pag = 20;
var url = "/syjservicesbcore/syjsFAKT_DB.do?user=${user.user}&year=2016";

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
    var percentageFormat = d3.format('.2%');
    var numberFormat = d3.format(",.0f")
   
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
	  d.kalle = 10;
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
	var  compositeChart = dc.compositeChart("#chart-composite");
	//var  compositeStackedChart = dc.compositeChart("#stacked-chart-composite");
	//var  stackedBarChart =  dc.barChart("#stacked-bar-chart"); //dc.compositeChart("#bar-chart-composite"); 
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


*/


	compositeChart
		    .width(1200)
		    .height(500)
		    .margins({top: 40, right: 10, bottom: 30, left: 80})
			//.x(d3.time.scale().domain([mindate, maxdate])) 	
			//.xUnits(d3.time.months) 	
			//.x(d3.scale.linear().domain([1,12]))
            //.xUnits(d3.time.month)
            
            //.x(d3.scale.ordinal())  //funkar!!, time scale funkar inte!!
            .x(d3.scale.linear().domain([0,13])) //Funkar, bara enskilt!!
            //.xUnits(dc.units.ordinal)

            .yAxisPadding('5%')
            .yAxisLabel("NOK")
			//.yAxis().tickFormat(d3.format("d"))

            
        	.xAxisLabel("Måned")      
            .elasticY(true)
            .renderTitle(true)
	    	.title(function (d) {
            	var resultat = d.value.omsetning + d.value.kostnad;  
            	var db = resultat / d.value.omsetning;
            	 return [
 	                d.key,
 	                'Resultat: ' + numberFormat(resultat),
 	                'Dekningsbidrag: ' + numberFormat(db)
 	            ].join('\n');
			 })	
        	.mouseZoomable(false)
        	.legend(dc.legend().x(1100).y(20).itemHeight(5).gap(20))
		    .renderHorizontalGridLines(true)
		  	.compose([
		         dc.barChart(compositeChart)
		            .dimension(monthDim) 
		            .colors('mediumslateblue')  //https://www.w3.org/TR/SVG/types.html#ColorKeywords
		            .centerBar(true)
		            .renderLabel(true)
			        .label(function (d) {
			        	var resultat = d.data.value.omsetning + d.data.value.kostnad;  
		            	var db = resultat / d.data.value.omsetning;
		            	//console.log("d.data.value.omsetning="+d.data.value.omsetning);
			            return "Db:"+percentageFormat(db);
			        })    
		            .group(monthDimGroup, "Omsetning") //dateDimGroup
			        .valueAccessor(function (d) {
                		return d.value.omsetning; 
        			}),            
		    	dc.barChart(compositeChart)
		            .dimension(monthDim) 
		            .colors('mediumvioletred')
		            .centerBar(true)
		            .group(monthDimGroup, "Kostnad")
		            .valueAccessor(function (d) {
                   		return d.value.kostnad; 
           			}),
			    dc.lineChart(compositeChart)
		            .dimension(monthDim) //dateDim
		            .colors('green')
		            .group(monthDimGroup, "Resultat")
					.valueAccessor(function (d) {
						var resultat = d.value.omsetning + d.value.kostnad;   // + = spooky algo
						return resultat;
					 })
		            .dashStyle([5,3])     
		     ])         
		    .brushOn(false);
	        
	        
	    //compositeChart.xAxis().tickFormat(d3.time.format('%B'));	        
	        
	        
	        
	   
 var minDate2 = dateDim.bottom(1)[0].month;
 var maxDate2  = dateDim.top(1)[0].month;
 
 //maxDate2.setMonth(maxDate2.getMonth() + 1 );
 
 //var minDate = new Date(dateDim.bottom(1)[0]["timestamp"]);
 //var maxDate = new Date(dateDim.top(1)[0]["timestamp"]);
 
 //minDate2 = d3.time.month.offset(minDate2, -1);
 //maxDate2 = d3.time.month.offset(maxDate2, 1);

/* 
stackedBarChart
		.width(1200)
		.height(420)
		.margins({top: 20, right: 10, bottom: 30, left: 80})
		//.x(d3.time.scale().domain([minDate2, maxDate2])) 	//scale.linear()
		.x(d3.scale.linear().domain([minDate2, maxDate2])) 	//
		//.x(d3.time.scale().domain([d3.time.day.offset(minDate2, -15), d3.time.day.offset(maxDate2, 15)]))
		//.xUnits(d3.time.month)
		//.round(d3.time.month.round)
		//.alwaysUseRounding(true)
        //.dimension(monthDim) 
 	    //.group(monthDimGroup);

		.xUnits(function(){return 100;})
		
         //.x(d3.scale.ordinal())  //funkar!!, time scale funkar inte!!
         //.x(d3.scale.linear().domain([0,13])) //Funkar, bara enskilt!!
         //.xUnits(d3.time.months) //funkar inte men 
 		 //.x(d3.time.scale().domain([1,12]))  //funkar, inte
 		// .x(d3.time.scale().domain([minDate2, maxDate2]))  //Funkar inte
 		 //.x(d3.scale.linear().domain([minDate2, maxDate2]))  //Funkar inte
         //.xUnits(d3.time.month)        
         
         //.x(d3.scale.ordinal().domain([1,12]))  //Funkar inte, smetar ut fel..
         
         //.xUnits(dc.units.ordinal)
         //.xUnits(d3.time.months)
         .yAxisLabel("NOK")
         .xAxisLabel("Måned")   
         //.ordering(function(d) { 
        //	return d.key; 
         // })	
		 .dimension(monthDim)
		 .group(monthDimGroup, "Kostnad", function (d) {
        	 //console.log("d.value.kostnad="+d.value.kostnad);
             return d.value.kostnad;
         })
         .stack(monthDimGroup, "Resultat", function (d) {
             var resultat = d.value.omsetning + d.value.kostnad;
             //console.log("resultat="+resultat);
             return resultat;
         })
         .stack(monthDimGroup, "Omsetning", function (d) {
             //console.log("omsetningStacked="+omsetningStacked);
             return d.value.omsetning;
         })         
         
         .renderHorizontalGridLines(true)
         .renderVerticalGridLines(true)
         //.renderTitle(true)
         //.title(function(d) {
        //	 	//console.log("title="+d.key + '[' + this.layer + ']: ' + d.value[this.layer]);
         //     return d.key + '[' + this.layer + ']: ' + d.value[this.layer];
         //})        
   		 .renderLabel(true)  
   		 .renderTitle(true)
		 .elasticY(true)
		 .gap(1)
		 .centerBar(true)
		 .xAxisPadding(5000)
		 .xAxisPaddingUnit('month')
//		 .xAxis().tickFormat(function(d){
//        	  console.log("d.key="+d.key);
//        	  console.log("d.data.key="+d.data.key);
//        	  console.log("d.value="+d.value);
//        	  return d3.time.months;
//          })
			
		 .legend(dc.legend().x(1100).y(20).itemHeight(5).gap(20))
		 
		 .brushOn(true);
		// .clipPadding(20);
*/
		
		
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
		compositeChart.filterAll();
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
	d3.selectAll('a#composite').on('click', function () {
		compositeChart.filterAll();
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
	      //function (d) { return d.kalle; }
	    ])
	    .order(d3.descending)
	    .on('renderlet', function (table) {
	      	// each time table is rendered remove nasty extra row dc.js insists on adding
	     	table.select('tr.dc-table-group').remove();
	 });
	

	jq('#data-table').on('click', '.data-table-col', function() {
		  var column = jq(this).attr("data-col");
		  var faktAllDim2 = fakt.dimension(function(d) {return d[column];});
		  dataTable.dimension(faktAllDim2)
		  dataTable.sortBy(function(d) {
		    return d[column];
		  });
		  dataTable.redraw();
		});
	
	
	
	d3.select('#download').on('click', function() {
        var data = faktAllDim.top(Infinity);
		var blob = new Blob([d3.csv.format(data)], {type: "text/csv;charset=utf-8"});
        saveAs(blob, 'trafikregnskap.csv');
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


///
var saveAs = saveAs || (function(view) {
	"use strict";
	// IE <10 is explicitly unsupported
	if (typeof view === "undefined" || typeof navigator !== "undefined" && /MSIE [1-9]\./.test(navigator.userAgent)) {
		return;
	}
	var
		  doc = view.document
		  // only get URL when necessary in case Blob.js hasn't overridden it yet
		, get_URL = function() {
			return view.URL || view.webkitURL || view;
		}
		, save_link = doc.createElementNS("http://www.w3.org/1999/xhtml", "a")
		, can_use_save_link = "download" in save_link
		, click = function(node) {
			var event = new MouseEvent("click");
			node.dispatchEvent(event);
		}
		, is_safari = /constructor/i.test(view.HTMLElement) || view.safari
		, is_chrome_ios =/CriOS\/[\d]+/.test(navigator.userAgent)
		, throw_outside = function(ex) {
			(view.setImmediate || view.setTimeout)(function() {
				throw ex;
			}, 0);
		}
		, force_saveable_type = "application/octet-stream"
		// the Blob API is fundamentally broken as there is no "downloadfinished" event to subscribe to
		, arbitrary_revoke_timeout = 1000 * 40 // in ms
		, revoke = function(file) {
			var revoker = function() {
				if (typeof file === "string") { // file is an object URL
					get_URL().revokeObjectURL(file);
				} else { // file is a File
					file.remove();
				}
			};
			setTimeout(revoker, arbitrary_revoke_timeout);
		}
		, dispatch = function(filesaver, event_types, event) {
			event_types = [].concat(event_types);
			var i = event_types.length;
			while (i--) {
				var listener = filesaver["on" + event_types[i]];
				if (typeof listener === "function") {
					try {
						listener.call(filesaver, event || filesaver);
					} catch (ex) {
						throw_outside(ex);
					}
				}
			}
		}
		, auto_bom = function(blob) {
			// prepend BOM for UTF-8 XML and text/* types (including HTML)
			// note: your browser will automatically convert UTF-16 U+FEFF to EF BB BF
			if (/^\s*(?:text\/\S*|application\/xml|\S*\/\S*\+xml)\s*;.*charset\s*=\s*utf-8/i.test(blob.type)) {
				return new Blob([String.fromCharCode(0xFEFF), blob], {type: blob.type});
			}
			return blob;
		}
		, FileSaver = function(blob, name, no_auto_bom) {
			if (!no_auto_bom) {
				blob = auto_bom(blob);
			}
			// First try a.download, then web filesystem, then object URLs
			var
				  filesaver = this
				, type = blob.type
				, force = type === force_saveable_type
				, object_url
				, dispatch_all = function() {
					dispatch(filesaver, "writestart progress write writeend".split(" "));
				}
				// on any filesys errors revert to saving with object URLs
				, fs_error = function() {
					if ((is_chrome_ios || (force && is_safari)) && view.FileReader) {
						// Safari doesn't allow downloading of blob urls
						var reader = new FileReader();
						reader.onloadend = function() {
							var url = is_chrome_ios ? reader.result : reader.result.replace(/^data:[^;]*;/, 'data:attachment/file;');
							var popup = view.open(url, '_blank');
							if(!popup) view.location.href = url;
							url=undefined; // release reference before dispatching
							filesaver.readyState = filesaver.DONE;
							dispatch_all();
						};
						reader.readAsDataURL(blob);
						filesaver.readyState = filesaver.INIT;
						return;
					}
					// don't create more object URLs than needed
					if (!object_url) {
						object_url = get_URL().createObjectURL(blob);
					}
					if (force) {
						view.location.href = object_url;
					} else {
						var opened = view.open(object_url, "_blank");
						if (!opened) {
							// Apple does not allow window.open, see https://developer.apple.com/library/safari/documentation/Tools/Conceptual/SafariExtensionGuide/WorkingwithWindowsandTabs/WorkingwithWindowsandTabs.html
							view.location.href = object_url;
						}
					}
					filesaver.readyState = filesaver.DONE;
					dispatch_all();
					revoke(object_url);
				}
			;
			filesaver.readyState = filesaver.INIT;

			if (can_use_save_link) {
				object_url = get_URL().createObjectURL(blob);
				setTimeout(function() {
					save_link.href = object_url;
					save_link.download = name;
					click(save_link);
					dispatch_all();
					revoke(object_url);
					filesaver.readyState = filesaver.DONE;
				});
				return;
			}

			fs_error();
		}
		, FS_proto = FileSaver.prototype
		, saveAs = function(blob, name, no_auto_bom) {
			return new FileSaver(blob, name || blob.name || "download", no_auto_bom);
		}
	;
	// IE 10+ (native saveAs)
	if (typeof navigator !== "undefined" && navigator.msSaveOrOpenBlob) {
		return function(blob, name, no_auto_bom) {
			name = name || blob.name || "download";

			if (!no_auto_bom) {
				blob = auto_bom(blob);
			}
			return navigator.msSaveOrOpenBlob(blob, name);
		};
	}

	FS_proto.abort = function(){};
	FS_proto.readyState = FS_proto.INIT = 0;
	FS_proto.WRITING = 1;
	FS_proto.DONE = 2;

	FS_proto.error =
	FS_proto.onwritestart =
	FS_proto.onprogress =
	FS_proto.onwrite =
	FS_proto.onabort =
	FS_proto.onerror =
	FS_proto.onwriteend =
		null;

	return saveAs;
}(
	   typeof self !== "undefined" && self
	|| typeof window !== "undefined" && window
	|| this.content
));
// `self` is undefined in Firefox for Android content script context
// while `this` is nsIContentFrameMessageManager
// with an attribute `content` that corresponds to the window

if (typeof module !== "undefined" && module.exports) {
  module.exports.saveAs = saveAs;
} else if ((typeof define !== "undefined" && define !== null) && (define.amd !== null)) {
  define("FileSaver.js", function() {
    return saveAs;
  });
}

jq( function() {
	jq( "#accordion" ).accordion({
    	collapsible: true,
    	active: false,
    	heightStyle: "content"
   });
} );


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
						 <div class="col-md-12 dc-chart" id="chart-composite" align="center"> 
						    <span class="reset" style="display: none;">filter: <span class="filter"></span></span>
						    <a class="reset" id="composite" style="display: none;"> - <i>tilbakestill filter</i></a>
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
	
<!--   				  
				  <div class="row">
				    <div class="col-md-12">
				      <div class="row">
   						 <div class="col-md-12 show-grid-left">
   						    Omsetning og kostnad /måned 
   						 </div>
				      </div>

				      <div class="row border">
						 <div class="col-md-12" id="stacked-bar-chart"  align="center"> 
						    <span class="reset" style="display: none;">filter: <span class="filter"></span></span>
						    <a class="reset" id="stacked-bar" style="display: none;"> - <i>tilbakestill filter</i></a>
						  </div>   
				      </div>

				    </div>
				  </div>				  
				  
--> 	
				  <div class="row">
				    <div class="col-md-6 show-grid-left" id="data-count"> 
				        <span class="filter-count"></span> faktura poster valgt ut av <span class="total-count"></span> poster.
				    </div>
				    <div class="col-md-6 show-grid-right">
						<a id="all">tilbakestill alt</a>
					</div> 
				  </div>
				 
				  
				  <div class="padded-row">&nbsp;</div>
	
   
				  <div id="accordion" class="row">
				    <h3>Fakturaposter, utvalg</h3>
				    <div class="col-md-12 show-grid-small">
				      <table class="table table-bordered table-striped" id="data-table">
				        <thead>
				          <tr class="header">
				            <th class="data-table-col" data-col="tupro">Tupro</th>
				            <th class="data-table-col" data-col="tubilk">Tubilk</th>
				            <th class="data-table-col" data-col="faavd">Faavd</th>
				            <th class="data-table-col" data-col="faopd">Faopd</th>
				            <th class="data-table-col" data-col="sumfabeln">Sumfabeln</th>
				            <th class="data-table-col" data-col="hedtop">Hedtop</th>
				            <th class="data-table-col" data-col="fakda">Fakda</th>
					        <th class="data-table-col" data-col="faopko">Faopko</th>
					        <th class="data-table-col" data-col="trknfa">Trknfa</th>
				          </tr>
				        </thead>
				      </table>
				      <div id="paging">
   						<!--  Viser <span id="begin"></span>-<span id="end"></span> av <span id="size"></span>.-->
    					<input id="last" type="Button" value="forrige" onclick="javascript:last()" />
    					<input id="next" type="button" value="neste" onclick="javascript:next()"/>
    					<button class="btn" id="download">download</button>
  					  </div>
				    </div>
				  </div>
				</div>

				<div class="padded-row">&nbsp;</div>

		 	   </td>
	 	    </tr>
	 	 </table>
		</td>
	</tr>
</table>	
		
<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->

