<%@ include file="/WEB-INF/views/include.jsp" %>
<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerReportDashboard.jsp" />
<!-- =====================end header ==========================-->

<style>


</style>
<script type="text/javascript">
"use strict";
var tolldataSize;
var ofs = 0, pag = 20;
var baseUrl = "/syjservicesbcore/syjsFORTOLLING_DB.do?user=${user.user}";

var jq = jQuery.noConflict();
var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Vennligst vent...";


function load_data() {
	
	var runningUrl = baseUrl;
	var selectedYear = jq('#selectYear').val();
	var selectedAvd = jq('#selectAvd').val();
	var selectedSign = jq('#selectSign').val();
	var selectedKundenr = jq('#selectKundenr').val();

	if (selectedYear != "" )	{
		runningUrl = runningUrl + "&registreringsdato="+selectedYear;
	}	
	if (selectedAvd != "" )	{
		runningUrl = runningUrl + "&avdeling="+selectedAvd;
	}
	if (selectedSign != "" )	{
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
		alert('Ingen data på urvalg.');  //TODO bättre UI
		throw error;
	}
	
	var tollData = data.dtoList;
    //console.log("tollData="+tollData);  //Tip: View i  Chrome devtool; NetWork-(mark xhr row)-Preview
    
    var fullDateFormat = d3.time.format('%Y%m%d');
    var yearFormat = d3.time.format('%Y');
    var monthFormat = d3.time.format('%m');
    var percentageFormat = d3.format('.2%');
    var numberFormat = d3.format(",.0f")
 
    // normalize/parse data
	 _.each(tollData, function(d) {
	  d.date = fullDateFormat.parse(d.registreringsdato);
	  d.year = yearFormat(d.date);
	  d.month = monthFormat(d.date);
	  d.avdeling = d.avdeling;
	  d.deklarasjonsnr= d.deklarasjonsnr;
	  d.reg_vareposter = +d.reg_vareposter;
	  d.off_vareposter = +d.off_vareposter;
	  d.registreringsdato = +d.registreringsdato; 
	  d.signatur =   d.signatur;
	  d.mottaker =   d.mottaker;
	});
 
	// set crossfilter. Crossfilter runs in the browser and the practical limit is somewhere around half a million to a million rows of data.
	var toll = crossfilter(tollData);	
	var  all = toll.groupAll();
	tolldataSize = toll.size();
	
	//Dimensions
	var  tollAllDim = toll.dimension(function(d) {return d;});	 //TODO översyn för exkl. kolumner i download
	var  dateDim  = toll.dimension(function(d) {return d.date;});
	var  yearDim  = toll.dimension(function(d) {return d.year;});
    var  monthDim = toll.dimension(function (d) {return d.month;});	
	var  avdDim  = toll.dimension(function(d) {return d.avdeling;});
	var  sisgDim  = toll.dimension(function(d) {return d.signatur;});
	var  typeDim  = toll.dimension(function(d) {return d.type;});
	//Charts 
	var  typeChart   = dc.pieChart("#chart-ring-type");
	var  yearChart   = dc.pieChart("#chart-ring-year");
	var  avdChart   = dc.pieChart('#chart-ring-avd');
	var  sisgChart   = dc.pieChart('#chart-ring-sisg');
	//var  yearlyBubbleChart = dc.bubbleChart('#yearly-bubble-chart');
	var  compositeChart = dc.compositeChart("#chart-composite");
	//var  compositeStackedChart = dc.compositeChart("#stacked-chart-composite");
	//var  stackedBarChart =  dc.barChart("#stacked-bar-chart"); //dc.compositeChart("#bar-chart-composite"); 
	//var  lineChartDate = dc.lineChart("#line-chart-date");
	var  dataCount = dc.dataCount('#data-count')	 
	var  antallDisplay = dc.numberDisplay("#antall");	
	var  antallreg_vareposterDisplay = dc.numberDisplay("#antallreg_vareposter");	
	var  antalloff_vareposterDisplay = dc.numberDisplay("#antalloff_vareposter");	
	var  totalTollDisplay = dc.numberDisplay("#totalToll");	
	var  totalAvgDisplay = dc.numberDisplay("#totalAvg");	
	var  dbDisplay = dc.numberDisplay("#db");	
	var  dataTable = dc.dataTable('#data-table');
	
	var mindate = dateDim.bottom(1)[0].date;
	var maxdate = dateDim.top(1)[0].date;
	
	var minmonth = dateDim.bottom(1)[0].month;
	var maxmonth = dateDim.top(1)[0].month;
	
	//Groups
	var  yearDimGroup = yearDim.group().reduceSum(function(d) {return d.reg_vareposter;});
	var  avdDimGroup = avdDim.group().reduceSum(function(d) {return d.reg_vareposter;});
	var  sisgDimGroup = sisgDim.group().reduceSum(function(d) {return d.reg_vareposter;});
	var  typeDimGroup = typeDim.group().reduceSum(function(d) {return d.reg_vareposter;});

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
	    .drawPaths(true)
	    .externalLabels(20)
	    .innerRadius(40)
	    
	yearChart
	    .width(300)
	    .height(300)
	    .dimension(yearDim)
	    .group(yearDimGroup)
	    .externalRadiusPadding(50)
	    .innerRadius(40)
   
	avdChart
	    .width(350)
	    .height(300)
	    .dimension(avdDim)
	    .group(avdDimGroup)
	    .slicesCap(25)
	    .innerRadius(40)
	    .externalRadiusPadding(50)
	    .legend(dc.legend().y(10).itemHeight(8).gap(3));
	
	sisgChart
	    .width(350)
	    .height(300)
	    .slicesCap(25)
	    .innerRadius(40)
	    .externalRadiusPadding(50)
	    .dimension(sisgDim)
	    .group(sisgDimGroup)
	    .legend(dc.legend().y(10).itemHeight(8).gap(3));
	
	
	antallDisplay
	     .group(omsetningsGroup)  
		 .valueAccessor(function (p) {
			 return p.value.count;
		  });
	
	antallreg_vareposterDisplay
		.group(omsetningsGroup)  
		.valueAccessor(function (p) {
				return p.value.sum_reg_vareposter;
		});
	
	antalloff_vareposterDisplay
		.group(omsetningsGroup)  
		.valueAccessor(function (p) {
				return p.value.sum_off_vareposter;
		});	
	

	totalTollDisplay
	     .group(omsetningsGroup)  
		 .valueAccessor(function (p) {
			 return p.value.totaltoll;
		  })
		 .formatNumber(function(d){ return d3.format(",.0f")(d)});
	    
	totalAvgDisplay
	     .group(omsetningsGroup)  
		 .valueAccessor(function (p) {
			 return p.value.totalavg;
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
		    .dimension(monthDim) 
		    .group(monthDimGroup)
		    .margins({top: 40, right: 10, bottom: 40, left: 80})
            .x(d3.scale.linear().domain([0,13])) //Funkar, bara enskilt!!
            
            //.x(d3.time.scale().domain([mindate, maxdate]))  //  .x(d3.time.scale().domain([mindate, maxdate])) Funkar  enskilt!!??
            //.xUnits(d3.time.month)
            
            .yAxisPadding('5%')
            .yAxisLabel("Antall vareposter")
        	.xAxisLabel("Måned")      
            .elasticY(true)
        	.legend( dc.legend().x(1000).y(20).itemHeight(5).gap(20).legendText(function(d, i) { 
        				if (i == 0) {
        					return "Antall registrerte vareposter";
        				}
        				if (i==1) {
        					return "Antall officielle vareposter";
        				}
        			}) 
        	)
		    .renderHorizontalGridLines(true)
		  	.compose([
		         dc.barChart(compositeChart)
		            //.dimension(monthDim) 
		            .colors('mediumslateblue')  //https://www.w3.org/TR/SVG/types.html#ColorKeywords
		            .centerBar(true)
		            .barPadding(1)
		            .renderLabel(true)
				    .legend(dc.legend().legendText(function(d) { return d.name + ': ' + d.data; }))
			        .valueAccessor(function (d) {
                		return d.value.sum_reg_vareposter; 
        			}),
   		         dc.barChart(compositeChart)
		           // .dimension(monthDim) 
		            .colors('mediumvioletred')  //https://www.w3.org/TR/SVG/types.html#ColorKeywords
		            .xAxisPadding(5000)
		            .barPadding(1)
		            .renderLabel(true)
		            .legend(dc.legend().legendText(function(d) { return d.name + ': ' + d.data; }))
			        .valueAccessor(function (d) {
             			return d.value.sum_off_vareposter; 
     			})       			
        			
		     ])  
//             .on('renderlet', function (_chart) {
//                 dc.events.trigger(function () {
//                	 _chart.selectAll("rect.bar").on("click", _chart.onClick);
//					dc.redrawAll();
//                 });
//             })	
             
		    .brushOn(false);
	        
       
	//        compositeChart.on('renderlet.barclicker', function(chart, filter) {
	//	  		  chart.selectAll('rect.bar').on('click.custom', function(d) {
	//	        	// use the data in d to take the right action
	//	        	//chart.filter(multikey(d.x, d.layer)); //TODO filter on måned
	//	        	dc.redrawAll();
	//	    		});
	//		 });        
        
	
/*	        
	        var compositeChartDate = dc.compositeChart("#composite-chart-date");
	        compositeChartDate.width(500)
	                .height(180)
	                .margins({top: 30, right: 50, bottom: 30, left: 30})
	                .dimension(dateDimension)
	                .group(dateIdSumGroup, "Id Sum")
	                .x(d3.time.scale().domain([new Date(2012, 4, 20), new Date(2012, 7, 15)]))
	                .xUnits(d3.time.days)
	                .shareColors(true)
	                .compose([
	                    dc.barChart(compositeChartDate).group(dateValueSumGroup, "Value Sum"),
	                    dc.lineChart(compositeChartDate),
	                    dc.lineChart(compositeChartDate).group(dateGroup, "Date Group")
	                ])
	                .on('renderlet', function (chart) {
	                    dc.events.trigger(function () {
	                        barChartDate.focus(chart.filter());
	                        lineChartDate.focus(chart.filter());
	                    });
	                })
	                .legend(dc.legend().x(400).y(10).itemHeight(13).gap(5))
	                .xAxis().ticks(5);	        
	        
*/	        
	        
/*	        
var negativeBarChart = dc.barChart("#negative-bar-chart");
negativeBarChart.width(1100)
        .height(200)
        .margins({top: 30, right: 50, bottom: 30, left: 30})
        .dimension(dateDimension)
        .group(dateNegativeValueSumGroup)
        .stack(dateNegativeValueSumGroup)
        .stack(dateNegativeValueSumGroup)
        .yAxisPadding(5)
        .elasticY(true)
        .x(d3.time.scale().domain([new Date(2012, 4, 20), new Date(2012, 7, 15)]))
        .renderHorizontalGridLines(true)
        .xUnits(d3.time.days);	        
	        
*/	        
	        
	        
 var minDate2 = dateDim.bottom(1)[0].month;
 var maxDate2  = dateDim.top(1)[0].month;
 

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

	d3.selectAll('a#type').on('click', function () {
		typeChart.filterAll();
		dc.redrawAll();
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
	d3.selectAll('a#sisg').on('click', function () {
		sisgChart.filterAll();
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
	      .dimension(toll)
	      .group(all)
		  .html({
            some: '<strong>%filter-count</strong> valgt ut av <strong>%total-count</strong> fortollinger' +
                ' | <a href=\'javascript:dc.filterAll(); dc.renderAll();\'>tilbakestill alt</a>',
            all: 'Alle fortollinger for utvalg. Vennligst klikk på grafen for å bruke filtre.'
          });      
	      

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
	
	jq(document).ready(function() {
		var lang = jq('#language').val();
		jq('#data-table').dataTable({
			"dom" : '<"top">t<"bottom"flip><"clear">',
			"scrollY" : "200px",
			"scrollCollapse" : true,
			"destroy": true,
			"language": {
				"url": getLanguage(lang)
	        }
		});
	});
	
/*
	jq('#data-table').on('click', '.data-table-col', function() {
		  var column = jq(this).attr("data-col");
		  var tollAllDim2 = toll.dimension(function(d) {return d[column];});
		  dataTable.dimension(tollAllDim2)
		  dataTable.sortBy(function(d) {
		    return d[column];
		  });
		  dataTable.redraw();
		});
*/	
	

	
	d3.select('#download').on('click', function() {
		var today = new Date();
        var data = tollAllDim.top(Infinity);
		var blob = new Blob([d3.csv.format(data)], {type: "text/csv;charset=utf-8"});
		
        saveAs(blob, 'fortolling_no-' + today + '.csv');
    });	
	
	tolldataSize = toll.size();
	//updateDataTable();
	  
	dc.renderAll(); 

	jq.unblockUI();
    
});

}
 
function display() {
    d3.select('#begin').text(ofs);
    d3.select('#end').text(ofs+pag-1);
    d3.select('#last').attr('disabled', ofs-pag<0 ? 'true' : null);
    d3.select('#next').attr('disabled', ofs+pag>=tolldataSize ? 'true' : null);
    d3.select('#size').text(tolldataSize);
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

</script>


<table width="100%"  cellspacing="0" border="0" cellpadding="0">
	<tr>
		<td>
		<%-- tab container component --%>
		<table style="border-collapse:initial;" width="100%"  cellspacing="0" border="0" cellpadding="0">
			<tr height="2"><td></td></tr>

				<tr height="25"> 

					<td width="20%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a class="text14" href="report_dashboard.do?report=report_trafikkregnskap" >
							<font class="tabDisabledLink">&nbsp;Trafikkregnskap</font>&nbsp;						
						</a>						
						<img style="vertical-align:middle;" src="resources/images/lorry_green.png"  width="18px" height="18px" border="0" alt="Trafikkregnskap rapport">
					</td>

					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>

					<td width="20%" valign="bottom" class="tab" align="center" nowrap>
						<font class="tabLink">&nbsp;Fortolling(NO)</font>
						<img  style="vertical-align:middle;" src="resources/images/list.gif" border="0" alt="general list">
					</td>

					<td width="60%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>	
	
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
	 	 <table width="100%" class="tabThinBorderWhiteWithSideBorders" border="0" cellspacing="0" cellpadding="0">
	 	    <tr height="20">
		 	    <td width="2%">&nbsp;</td>
		 	    <td>&nbsp;
				<div class="container-fluid">
				  <div class="row">
					<div class="col-md-8 text12">
							Fra år:
						<select name="selectYear" id="selectYear" >
	  						<option value="2017">2017</option>
		  					<option value="2016">2016</option>
	  					</select>
						<font class="text12">&nbsp;&nbsp;Avdeling:</font>
		        		<select class="inputTextMediumBlue" name="selectAvd" id="selectAvd">
	 						<option value="">-alle-</option>
		 				  	<c:forEach var="record" items="${model.avdList}" >
		 				  		<option value="${record.koakon}"<c:if test="${searchFilterTror.avd == record.koakon}"> selected </c:if> >${record.koakon}</option>
							</c:forEach>  
						</select>		  					
						<font class="text12">&nbsp;&nbsp;Signatur:</font>
		        		<select class="inputTextMediumBlue" name="selectSign" id="selectSign" autofocus>
			 						<option value="">-alle-</option>
			 						<c:forEach var="record" items="${model.signatureList}" >
				 				  		<option value="${record.kosfsi}"<c:if test="${searchFilterTror.sign == record.kosfsi}"> selected </c:if> >${record.kosfsi}</option>
									</c:forEach>   
						</select>	
  		    			<font class="text12">&nbsp;&nbsp;Mottaker:</font>
						<input type="text" class="inputText" name="selectKundenr" id="selectKundenr" size="9" maxlength="8" >  	

					</div>	

	  		    	<div class="col-md-4" align="right">
	   	              	<button class="inputFormSubmit" onclick="load_data()">Hent data</button> 
					</div>	
				  </div>
	
	  			  <div class="padded-row-small">&nbsp;</div>
	

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
				          <h3 class="text14">Antal officielle vareposter</h3>
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
						  	<h3 class="text11">Vareposter</h3>
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
						<div class="col-md-3" id="chart-ring-type">
						 	<h3 class="text11">Import / Eksport</h3>
						    <span class="reset" style="display: none;">filter: <span class="filter"></span></span>
						    <a class="reset" id="type" style="display: none;"> - <i>tilbakestill filter</i></a>
				        </div>
						<div class="col-md-3" id="chart-ring-year">
							<h3 class="text11">År</h3>
						    <span class="reset" style="display: none;">filter: <span class="filter"></span></span>
						    <a class="reset" id="year" style="display: none;"> - <i>tilbakestill filter</i></a>
				        </div>
				        <div class="col-md-3" id="chart-ring-avd">
				        	<h3 class="text11">Avdeling
				        	 <font class="text10">&nbsp;&nbsp;&nbsp;avd:&nbsp;<input id="avd-filter" type="text" size="5"/>  </font>
				        	 <a id="avdfilter">&nbsp;legg til filter</a>	
				        	</h3>
							 			
						    <span class="reset" style="display: none;">filter: <span class="filter"></span></span>
						    <a class="reset" id="avd" style="display: none;"> - <i>tilbakestill filter</i></a>
	
				        </div>
				        <div class="col-md-3" id="chart-ring-sisg">
				        	<h3 class="text11">Signatur</h3>
						    <span class="reset" style="display: none;">filter: <span class="filter"></span></span>
						    <a class="reset" id="sisg" style="display: none;"> - <i>tilbakestill filter</i></a>
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
				    <div class="col-md-12">
				       <h3 class="text11">Fortollinger, filtrert</h3>
				       <div class="padded-row"></div>
				       <table class="display compact cell-border" id="data-table">
				        <thead>
				          <tr>
				            <th class="tableHeaderField" >avdeling</th>
				            <th class="tableHeaderField" >deklarasjonsnr</th>
				            <th class="tableHeaderField" >reg. vareposter</th>
				            <th class="tableHeaderField" >off. vareposter</th>
				            <th class="tableHeaderField" >registreringsdato</th>
				            <th class="tableHeaderField" >signatur</th>
				            <th class="tableHeaderField" >mottaker</th>
				            <th class="tableHeaderField" >type</th>
				          </tr>
				        </thead>
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

         		</div> <!-- container -->
		 	    </td>
		 	    
	 		<td width="2%">&nbsp;</td>	 	    
	 	    </tr>
	 	 </table>
		</td>
	</tr>
</table>	
		
<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->

