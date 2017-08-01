<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="utf-8">
  <link rel="stylesheet" type="text/css" href="https://dc-js.github.io/dc.js/css/dc.css" />
  <script src="https://dc-js.github.io/dc.js/js/d3.js"></script>
  <script src="https://dc-js.github.io/dc.js/js/crossfilter.js"></script>
  <script src="https://dc-js.github.io/dc.js/js/dc.js"></script>
  <script src="https://rawgit.com/crossfilter/reductio/master/reductio.js"></script>
  <script src="https://npmcdn.com/universe@latest/universe.js"></script>
  <script src="http://colorbrewer2.org/export/colorbrewer.js"></script>
</head>

<body>


<h2>The Systema report module!</h2>
 
<div class="row">
    <div id="chart_SYS" >
        <strong>Yearly Performance</strong> (gain/loss)
        <a class="reset" href="javascript:chart_SYS.filterAll();dc.redrawAll();"
           style="display: none;">reset</a>
        <div class="clearfix"></div>
    </div>
</div>
 
<div class="row">
    <div id="yearly-bubble-chart" class="dc-chart">
        <strong>Yearly Performance</strong> (gain/loss)
        <a class="reset" href="javascript:yearlyBubbleChart.filterAll();dc.redrawAll();"
           style="display: none;">reset</a>
        <div class="clearfix"></div>
    </div>
</div>

<div class="row">
    <div id="yearly-bubble-chart_SYS" class="dc-chart">
        <strong>Yearly Performance</strong> (gain/loss) SYS
        <a class="reset" href="javascript:yearlyBubbleChart_SYS.filterAll();dc.redrawAll();"
           style="display: none;">reset</a>
        <div class="clearfix"></div>
    </div>
</div>


<script>
	//crossfiler och dimension, se  https://github.com/crossfilter/crossfilter/wiki/API-Reference#crossfilter_add

	
	//var faktCsv = "resources/files/fakt.csv";   
	//var faktCsv = "resources/files/fakt_no_nulls.csv";   
	var faktCsv = "resources/files/fakt_no_nulls_minor.csv";   
	var chart = dc.barChart("#chart_SYS");
	
	d3.csv(faktCsv, function(error, data) {
	  var dateFormat = d3.time.format('%Y%m%d');
		
	  data.forEach(function(d) {
	  	d.dd = dateFormat.parse(d.fadato);
	    d.fabeln = +d.fabeln;
	  });
	
	  var fakt                 = crossfilter(data);
	  var fadatoDimensionDD      = fakt.dimension(function(d) {return +d.dd;});
	  var fadatoDimension      = fakt.dimension(function(d) {return +d.fadato;});
	  
	 // alert("fadatoDimension, inifitty="+fadatoDimension.top(Infinity).length);
	 // alert("fadatoDimension all="+fadatoDimension);  
	  
	  
	 // alert("fadatoDimensionDD infinoty="+fadatoDimensionDD.top(Infinity).length);

	 // alert("fadatoDimensionDD all="+fadatoDimensionDD);
	  
	  
	  var groupByFadato = fadatoDimension.group().reduceSum(function(d) { return d.fabeln; });
	
	  
	  
	  
	  alert("groupByFadato="+groupByFadato.top(Infinity).length);
	  
	  var top1 = groupByFadato.top(1);
	 // var top5 = groupByFadato.top(15);
	  alert("dato top 0="+top1[0].key+" value="+top1[0].value);
	 // alert("dato top 1="+top1[1].key+" value="+top1[1].value);
	  
	  
	  
	  
	    var yearlyDimension = fakt.dimension(function (d) {
	    	//alert("d3.time.year(d.dd)="+d3.time.year(d.dd));
	        return d3.time.year(d.dd).getFullYear();
	    });
	  
	   alert("yearlyDimension="+yearlyDimension.top(Infinity).length);
	   alert("yearlyDimension top(1)="+yearlyDimension.top(1));  
	    
		var groupByYearly = yearlyDimension.group().reduceSum(function(d) { return d.fabeln; });
	    
		var top11 = groupByYearly.top(1);
		
		 alert("top11="+top11[0].key+" value="+top1[0].value);
		
		
		
	//	alert("sumfabelnByYearly="+sumfabelnByYearly.top(Infinity).length);  
	//	alert("sumfabelnByYearly="+sumfabelnByYearly.top(1));    
	  
	  chart   
	    .width(768)
	    .height(480)
	    .x(d3.scale.linear().domain([6,50]))
	    .brushOn(false)
	    .yAxisLabel("This is the Y Axis!")
	    .dimension(yearlyDimension) //FUNKAR INTE??
	    .group(groupByYearly) //FUNKAR INTE??
	    .on('renderlet', function(chart) {
	        chart.selectAll('rect').on("click", function(d) {
	            console.log("click!", d);
	        });
	    });
	    
	  
	  	chart.render();
	  	
	    //dc.renderAll(); 
	  	
	  	
	});


	var ndxCsv = "resources/files/ndx.csv";   
	var yearlyBubbleChart = dc.bubbleChart('#yearly-bubble-chart');	

	d3.csv(ndxCsv, function (data) {
		    // Since its a csv file we need to format the data a bit.
		    var dateFormat = d3.time.format('%m/%d/%Y');
		    var numberFormat = d3.format('.2f');

		    data.forEach(function (d) {
		        d.dd = dateFormat.parse(d.date);
		        d.month = d3.time.month(d.dd); // pre-calculate month for better performance
		        d.close = +d.close; // coerce to number
		        d.open = +d.open;
		    });
	
		    var ndx = crossfilter(data);
		    var all = ndx.groupAll();
		    
		    // Dimension by year
		    var yearlyDimension = ndx.dimension(function (d) {
		        return d3.time.year(d.dd).getFullYear();
		    });
		    
		    
		   // alert("yearlyDimension="+yearlyDimension.top(Infinity).length);		    
		    
		    
		    // Maintain running tallies by year as filters are applied or removed
		    var yearlyPerformanceGroup = yearlyDimension.group().reduce(
		        /* callback for when data is added to the current filter results */
		        function (p, v) {
		            ++p.count;
		            p.absGain += v.close - v.open;
		            p.fluctuation += Math.abs(v.close - v.open);
		            p.sumIndex += (v.open + v.close) / 2;
		            p.avgIndex = p.sumIndex / p.count;
		            p.percentageGain = p.avgIndex ? (p.absGain / p.avgIndex) * 100 : 0;
		            p.fluctuationPercentage = p.avgIndex ? (p.fluctuation / p.avgIndex) * 100 : 0;
		            return p;
		        },
		        /* callback for when data is removed from the current filter results */
		        function (p, v) {
		            --p.count;
		            p.absGain -= v.close - v.open;
		            p.fluctuation -= Math.abs(v.close - v.open);
		            p.sumIndex -= (v.open + v.close) / 2;
		            p.avgIndex = p.count ? p.sumIndex / p.count : 0;
		            p.percentageGain = p.avgIndex ? (p.absGain / p.avgIndex) * 100 : 0;
		            p.fluctuationPercentage = p.avgIndex ? (p.fluctuation / p.avgIndex) * 100 : 0;
		            return p;
		        },
		        /* initialize p */
		        function () {
		            return {
		                count: 0,
		                absGain: 0,
		                fluctuation: 0,
		                fluctuationPercentage: 0,
		                sumIndex: 0,
		                avgIndex: 0,
		                percentageGain: 0
		            };
		        }
		    );
	    
		    yearlyBubbleChart /* dc.bubbleChart('#yearly-bubble-chart', 'chartGroup') */
	        // (_optional_) define chart width, `default = 200`
	        .width(990)
	        // (_optional_) define chart height, `default = 200`
	        .height(250)
	        // (_optional_) define chart transition duration, `default = 750`
	        .transitionDuration(1500)
	        .margins({top: 10, right: 50, bottom: 30, left: 40})
	        .dimension(yearlyDimension)
	        //The bubble chart expects the groups are reduced to multiple values which are used
	        //to generate x, y, and radius for each key (bubble) in the group
	        .group(yearlyPerformanceGroup)
	        // (_optional_) define color function or array for bubbles: [ColorBrewer](http://colorbrewer2.org/)
	        .colors(colorbrewer.RdYlGn[9])
	        //(optional) define color domain to match your data domain if you want to bind data or color
	        .colorDomain([-500, 500])
	    	//##### Accessors

	        //Accessor functions are applied to each value returned by the grouping

	        // `.colorAccessor` - the returned value will be passed to the `.colors()` scale to determine a fill color
	        .colorAccessor(function (d) {
	            return d.value.absGain;
	        })
	        // `.keyAccessor` - the `X` value will be passed to the `.x()` scale to determine pixel location
	        .keyAccessor(function (p) {
	            return p.value.absGain;
	        })
	        // `.valueAccessor` - the `Y` value will be passed to the `.y()` scale to determine pixel location
	        .valueAccessor(function (p) {
	            return p.value.percentageGain;
	        })
	        // `.radiusValueAccessor` - the value will be passed to the `.r()` scale to determine radius size;
	        //   by default this maps linearly to [0,100]
	        .radiusValueAccessor(function (p) {
	            return p.value.fluctuationPercentage;
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
	        .xAxisLabel('Index Gain')
	        // (_optional_) render a vertical axis lable left of the y axis
	        .yAxisLabel('Index Gain %')
	        //##### Labels and  Titles

	        //Labels are displayed on the chart for each bubble. Titles displayed on mouseover.
	        // (_optional_) whether chart should render labels, `default = true`
	        .renderLabel(true)
	        .label(function (p) {
	            return p.key;
	        })
	        // (_optional_) whether chart should render titles, `default = false`
	        .renderTitle(true)
	        .title(function (p) {
	            return [
	                p.key,
	                'Index Gain: ' + numberFormat(p.value.absGain),
	                'Index Gain in Percentage: ' + numberFormat(p.value.percentageGain) + '%',
	                'Fluctuation / Index Ratio: ' + numberFormat(p.value.fluctuationPercentage) + '%'
	            ].join('\n');
	        })
	        //#### Customize Axes

	        // Set a custom tick format. Both `.yAxis()` and `.xAxis()` return an axis object,
	        // so any additional method chaining applies to the axis, not the chart.
	        .yAxis().tickFormat(function (v) {
	            return v + '%';
	        });
	    
		    
		    dc.renderAll(); 
		    
		    
	});

	
	//var faktCsv = "resources/files/fakt.csv";   
	var faktCsv2 = "resources/files/fakt_no_nulls.csv";   
	var yearlyBubbleChart_SYS = dc.bubbleChart('#yearly-bubble-chart_SYS');	
	
	d3.csv(faktCsv2, function (data) {
	    // Since its a csv file we need to format the data a bit.
	    //var dateFormat = d3.time.format('%m/%d/%Y');
	    
	    var dateFormat = d3.time.format('%Y%m%d');  //faktcsv
	    var numberFormat = d3.format('.2f');

	    data.forEach(function (d) {
	    	//alert("d.fadato"+d.fadato);
	        d.dd = dateFormat.parse(d.fadato);
	        //d.month = d3.time.month(d.dd); // pre-calculate month for better performance, PARSAR inter så bra.....
	        d.fabeln = +d.fabeln; // coerce to number
	       // d.open = +d.open;
	    });

	    var ndx = crossfilter(data);
	    var all = ndx.groupAll();
	    
	    // Dimension by year
	    var yearlyDimension = ndx.dimension(function (d) {
	    	//alert("d3.time.year(d.dd)="+d3.time.year(d.dd));
	        return d3.time.year(d.dd).getFullYear();
	    });
	    
	    
	//   alert("yearlyDimension="+yearlyDimension.top(Infinity).length);		    
	    
	    
	    // Maintain running tallies by year as filters are applied or removed
	    var yearlyPerformanceGroup = yearlyDimension.group().reduce(
	        function (p, v) {
	        	//alert("v.fadato="+v.fadato+", v.fabeln="+v.fabeln);
	            ++p.count;
	            //p.absGain += v.close - v.open;
	            p.absGain += v.fabeln;
	            //p.fluctuation += Math.abs(v.close - v.open);
	            p.fluctuation += Math.abs(v.fabeln);
	            //p.sumIndex += (v.open + v.close) / 2;
	            p.sumIndex +=  Math.abs(v.fabeln);
	            p.avgIndex = p.sumIndex / p.count;
	            p.percentageGain = p.avgIndex ? (p.absGain / p.avgIndex) * 100 : 0;
	            p.fluctuationPercentage = p.avgIndex ? (p.fluctuation / p.avgIndex) * 100 : 0;
	            return p;
	        },
	        function (p, v) {
	            --p.count;
	            //p.absGain -= v.close - v.open;
	            p.absGain -= v.fabeln;
	            //p.fluctuation -= Math.abs(v.close - v.open);
	            p.fluctuation -= Math.abs(v.fabeln);
	           //p.sumIndex -= (v.open + v.close) / 2;
	            p.sumIndex -=  Math.abs(v.fabeln);
	            p.avgIndex = p.count ? p.sumIndex / p.count : 0;
	            p.percentageGain = p.avgIndex ? (p.absGain / p.avgIndex) * 100 : 0;
	            p.fluctuationPercentage = p.avgIndex ? (p.fluctuation / p.avgIndex) * 100 : 0;
	            return p;
	        },
	        function () {
	            return {
	                count: 0,
	                absGain: 0,
	                fluctuation: 0,
	                fluctuationPercentage: 0,
	                sumIndex: 0,
	                avgIndex: 0,
	                percentageGain: 0
	            };
	        }
	    );
    
	    yearlyBubbleChart_SYS 
        // (_optional_) define chart width, `default = 200`
        .width(990)
        // (_optional_) define chart height, `default = 200`
        .height(250)
        // (_optional_) define chart transition duration, `default = 750`
        .transitionDuration(1500)
        .margins({top: 10, right: 50, bottom: 30, left: 40})
        .dimension(yearlyDimension)
        //The bubble chart expects the groups are reduced to multiple values which are used
        //to generate x, y, and radius for each key (bubble) in the group
        .group(yearlyPerformanceGroup)
        // (_optional_) define color function or array for bubbles: [ColorBrewer](http://colorbrewer2.org/)
        .colors(colorbrewer.RdYlGn[9])
        //(optional) define color domain to match your data domain if you want to bind data or color
        .colorDomain([-500, 500])
    	//##### Accessors

        //Accessor functions are applied to each value returned by the grouping

        // `.colorAccessor` - the returned value will be passed to the `.colors()` scale to determine a fill color
        .colorAccessor(function (d) {
            return d.value.absGain;
        })
        // `.keyAccessor` - the `X` value will be passed to the `.x()` scale to determine pixel location
        .keyAccessor(function (p) {
            return p.value.absGain;
        })
        // `.valueAccessor` - the `Y` value will be passed to the `.y()` scale to determine pixel location
        .valueAccessor(function (p) {
            return p.value.percentageGain;
        })
        // `.radiusValueAccessor` - the value will be passed to the `.r()` scale to determine radius size;
        //   by default this maps linearly to [0,100]
        .radiusValueAccessor(function (p) {
            return p.value.fluctuationPercentage;
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
        .xAxisLabel('Index Gain')
        // (_optional_) render a vertical axis lable left of the y axis
        .yAxisLabel('Index Gain %')
        //##### Labels and  Titles

        //Labels are displayed on the chart for each bubble. Titles displayed on mouseover.
        // (_optional_) whether chart should render labels, `default = true`
        .renderLabel(true)
        .label(function (p) {
            return p.key;
        })
        // (_optional_) whether chart should render titles, `default = false`
        .renderTitle(true)
        .title(function (p) {
            return [
                p.key,
                'Index Gain: ' + numberFormat(p.value.absGain),
                'Index Gain in Percentage: ' + numberFormat(p.value.percentageGain) + '%',
                'Fluctuation / Index Ratio: ' + numberFormat(p.value.fluctuationPercentage) + '%'
            ].join('\n');
        })
        //#### Customize Axes

        // Set a custom tick format. Both `.yAxis()` and `.xAxis()` return an axis object,
        // so any additional method chaining applies to the axis, not the chart.
        .yAxis().tickFormat(function (v) {
            return v + '%';
        });
    
	    
	    dc.renderAll(); 
	    
	    
});

	
	
	
	
	
	
</script>
</body>
</html>