<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerReportDashboard.jsp" />
<!-- =====================end header ==========================-->
  <script src="https://dc-js.github.io/dc.js/js/d3.js"></script>
  <script src="https://dc-js.github.io/dc.js/js/crossfilter.js"></script>
  <script src="https://dc-js.github.io/dc.js/js/dc.js"></script>
  <script src="http://colorbrewer2.org/export/colorbrewer.js"></script>

  <link rel="stylesheet" type="text/css" href="https://dc-js.github.io/dc.js/css/dc.css">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn" crossorigin="anonymous"></script>

  <script src="https://unpkg.com/leaflet@1.1.0/dist/leaflet.js""></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/underscore.js/1.8.3/underscore-min.js"></script>

  <script src="https://rawgit.com/crossfilter/reductio/master/reductio.js"></script>
  <script src="https://npmcdn.com/universe@latest/universe.js"></script>


<style>

.dc-data-count small {
  font-size: 16px;
}

.container-fluid {
  padding-left: 40px;
  padding-right: 40px;
}

.pie-chart {
  height: 150px;
}

.dc-chart .axis text{
  font-size: 12px;
}

text {
  font-size: 16px;
   fill: black;
}

#map {
  height: 150px;
}

#control-row {
  padding-bottom: 50px;
}

a { cursor: pointer }

.show-grid [class^="col-"] {
    padding-top: 10px;
    padding-bottom: 10px;
    border: 1px solid #ddd;
    background-color: #eee !important;
}


.dc-chart g.row text {
    fill: #403131;
    font-size: 12px;
    cursor: pointer;
}

</style>


<script type="text/javascript">

// http://localhost:8080/syjservicesbcore/syjsFAKT_DB.do?user=OSCAR&year=2017
d3.json("/syjservicesbcore/syjsFAKT_DB.do?user=OSCAR&year=2017", function(error, data) {
	var faktData = data.dtoList;
   // console.log("faktData="+faktData);  //Tip: View i  Chrome devtool; NetWork-(mark xhr row)-Preview
    
    var dateFormat = d3.time.format('%Y%m%d');   
    var fullDateFormat = d3.time.format('%Y%m%d');
    var yearFormat = d3.time.format('%Y');
    var monthFormat = d3.time.format('%m');
   
    // normalize/parse data
	 _.each(faktData, function(d) {
	  d.dd = fullDateFormat.parse(d.fadato);
	  d.year = yearFormat(d.dd);
	  d.month = monthFormat(d.dd);
	  d.faavd = d.faavd;
	  d.faopd = d.faopd;
	  d.sumfabeln = +d.sumfabeln;
	  d.fadato = d.fadato;
	  d.fakda = d.fakda;
	});
 
	// set crossfilter
	var ndx = crossfilter(faktData);	
	var all = ndx.groupAll();
	
	var  yearDim  = ndx.dimension(function(d) {return d.year;});
	var  avdDim  = ndx.dimension(function(d) {return d.faavd;});
	var  yearDimGroup = yearDim.group().reduceSum(function(d) {return +d.sumfabeln/1000;});
	var  avdDimGroup = avdDim.group().reduceSum(function(d) {return +d.sumfabeln/1000;});
	
	 var monthDim  = ndx.dimension(function(d) {return +d.month;});
	 var countPerMonth = monthDim.group().reduceCount();

	 var countAvdDim = avdDim.group().reduceCount();
	 
	 
	 
	 var  yearChart   = dc.pieChart("#chart-ring-year");
	 var  monthChart   = dc.pieChart('#chart-ring-month');
	 var  avdChart   = dc.pieChart('#chart-ring-avd');
	 var  dataTable = dc.dataTable('#data-table');
	 var  yearlyBubbleChart = dc.bubbleChart('#yearly-bubble-chart');

	 var avdFabelnRowChart = dc.rowChart("#chart-row-avdfabeln");	 
	 
	 
	 var allDim = ndx.dimension(function(d) {return d;});	 
	 var dataCount = dc.dataCount('#data-count')	 

    var yearPerformanceGroup = yearDim.group().reduce(
        /* callback for when data is added to the current filter results */
        function (p, v) {
        	++p.faavd;
        	++p.faopd;
            ++p.sumfabeln;
            ++p.count;
            p.sumAvd += p.faavd;
            p.sumIndex += p.sumfabeln;
            p.avgIndex = p.sumIndex / p.count;
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
            p.sumIndex -= p.sumfabeln;
            p.avgIndex = p.count ? p.sumIndex / p.count : 0;
            p.percentageGain = p.avgIndex ? (p.sumfabel / p.avgIndex) * 100 : 0;
            return p;
        },
        /* initialize p */
        function () {
            return {faavd: 0,faopd: 0, sumfabeln: 0, sumIndex: 0,sumAvd: 0, avgIndex: 0, percentageGain: 0, count: 0};
        }
    );	
	 

	  yearChart
	    .width(150)
	    .height(150)
	    .dimension(yearDim)
	    .group(yearDimGroup)
	    .innerRadius(30)
	    .controlsUseVisibility(true);
   
	  monthChart
      .width(150)
      .height(150)
      .dimension(monthDim)
      .group(countPerMonth)
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
	    .dimension(avdDim)
	    .group(avdDimGroup)
	    .innerRadius(30)
	    .controlsUseVisibility(true);
	  
	  
	  var fabelnPerAvd = avdDim.group().reduceSum(function (d) {
          return +d.sumfabeln /1000 ; 
      });

	  avdFabelnRowChart
	  	.width(900)
	  	.height(250)
       .dimension(avdDim)
       .group(fabelnPerAvd)
       .colors(d3.scale.ordinal().range(['red','green','blue']))
       .elasticX(true);
	  
	  
	    //#### Bubble Chart

	    //Create a bubble chart and use the given css selector as anchor. You can also specify
	    //an optional chart group for this chart to be scoped within. When a chart belongs
	    //to a specific group then any interaction with the chart will only trigger redraws
	    //on charts within the same chart group.
	    // <br>API: [Bubble Chart](https://github.com/dc-js/dc.js/blob/master/web/docs/api-latest.md#bubble-chart)

	    yearlyBubbleChart /* dc.bubbleChart('#yearly-bubble-chart', 'chartGroup') */
	        // (_optional_) define chart width, `default = 200`
	        .width(900)
	        // (_optional_) define chart height, `default = 200`
	        .height(250)
	        // (_optional_) define chart transition duration, `default = 750`
	        .transitionDuration(1500)
	        .margins({top: 10, right: 50, bottom: 30, left: 40})
	        .dimension(yearDim)
	        //The bubble chart expects the groups are reduced to multiple values which are used
	        //to generate x, y, and radius for each key (bubble) in the group
	        .group(yearPerformanceGroup)
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
	            return p.value.sumIndex;
	        })
	        // `.valueAccessor` - the `Y` value will be passed to the `.y()` scale to determine pixel location
	        .valueAccessor(function (p) {
	            return p.value.sumfabeln;
	            //return p.value.sumIndex;
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
	        .xAxisLabel('Summa fabeln per oppdrag')
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
	                'NOK: ' + p.value.sumIndex,
	                'Antall oppdrag: ' + p.value.sumfabeln
	            ].join('\n');
	        })	        
	        
	        
	        
	        //#### Customize Axes

	        // Set a custom tick format. Both `.yAxis()` and `.xAxis()` return an axis object,
	        // so any additional method chaining applies to the axis, not the chart.
	    //    .yAxis().tickFormat(function (v) {
	    //        return v + '%';
	    //    });
 	 
 	 
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
	   
	   d3.selectAll('a#avd').on('click', function () {
		 avdChart.filterAll();
		 dc.redrawAll();
	   });	   
	   

	   dataCount
	      .dimension(ndx)
	      .group(all);  

	   
	   dataTable
	    .dimension(allDim)
	    .group(function (d) { return 'dc.js insists on putting a row here so I remove it using JS'; })
	    .size(1000)
	    .columns([
	      function (d) { return d.faavd; },
	      function (d) { return d.faopd; },
	      function (d) { return d.sumfabeln; },
	      function (d) { return d.fadato; },
	      function (d) { return d.fakda; }
	    ])
	   // .sortBy(dc.pluck('rating_score'))
	    .order(d3.descending)
	    .on('renderlet', function (table) {
	      // each time table is rendered remove nasty extra row dc.js insists on adding
	      table.select('tr.dc-table-group').remove();
	      
	  });
	  
	
	  dc.renderAll(); 
    
 });
 

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
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="20%" valign="bottom" class="tabDisabled" align="center" nowrap>
		               		<a style="display:block;" id="norskLink" href="report_dashboard.do?lib=untappd">
								<font class="tabDisabledLink">&nbsp;Trafikkanalyse (Untappd)</font>
								<img valign="bottom" src="resources/images/list.gif" border="0" alt="general list">
							</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="20%" valign="bottom" class="tabDisabled" align="center" nowrap>
		               		<a style="display:block;" id="norsk2Link" href="report_dashboard.do?lib=D3">
									<font class="tabDisabledLink">&nbsp;Terminalanalyse (D3)</font>
								<img valign="bottom" src="resources/images/list.gif" border="0" alt="general list">
							</a>
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
				  <div class="row"> <!-- show-grid -->
				    <div class="col-xs-9 dc-data-count dc-chart text12" id="data-count"> 
				       <h3>Faktura poster
				        <small>
				          <span class="filter-count"></span> valgt ut av <span class="total-count"></span> poster |
				           <a id="all" href="#">Tilbakestill alt</a>
				        </small>
				        </h3>
				    </div>
				  </div>
				  
				  <div class="row" id="control-row">  <!-- show-grid -->
				    <div class="col-xs-4 col-md-3 pie-chart">
				      <h4>År <small><a id="year">tilbakestill&nbsp;&nbsp;</a></small></h4>
				      <div class="dc-chart" id="chart-ring-year"></div>
				    </div>
				    <div class="col-xs-4 col-md-3 pie-chart">
				      <h4>Måned <small><a id="month">tilbakestill&nbsp;&nbsp;</a></small></h4>
				      <div class="dc-chart" id="chart-ring-month"></div>
				    </div>

				    <div class="col-xs-4 col-md-3 pie-chart">
				      <h4>Avdeling <small><a id="avd">tilbakestill</a></small></h4>
				      <div class="dc-chart" id="chart-ring-avd"></div>
				    </div>
  
				  </div>
				 
				  <div class="row">
				    <div class="col-xs-6 col-md-9">
					  <strong>Sum fabeln / 1000, per avdeling</strong>
				      <div class="dc-chart" id="chart-row-avdfabeln"></div>
				    </div>

				  <div class="row"> 
				    <div class="col-xs-6 col-md-9">
	      				<div class="dc-chart" id="yearly-bubble-chart"></div>
				    </div>
				  </div>
	
				  <div class="row"> <!-- show-grid -->
				    <div class="col-xs-9 col-md-9">
				      <table class="table table-bordered table-striped" id="data-table">
				        <thead>
				          <tr class="header">
				            <th>faavd</th>
				            <th>faopd</th>
				            <th>sumfabeln</th>
				            <th>fadato</th>
				            <th>fakda</th>
				          </tr>
				        </thead>
				      </table>
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

