<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerReportDashboard.jsp" />
<!-- =====================end header ==========================-->
  <script src="https://dc-js.github.io/dc.js/js/d3.js"></script>
  <script src="https://dc-js.github.io/dc.js/js/crossfilter.js"></script>
  <script src="https://dc-js.github.io/dc.js/js/dc.js"></script>

  <link rel="stylesheet" type="text/css" href="https://dc-js.github.io/dc.js/css/dc.css">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn" crossorigin="anonymous"></script>

  <script src="https://unpkg.com/leaflet@1.1.0/dist/leaflet.js""></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/underscore.js/1.8.3/underscore-min.js"></script>

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
}

#map {
  height: 150px;
}

#control-row {
  padding-bottom: 20px;
}

a { cursor: pointer }

.show-grid [class^="col-"] {
    padding-top: 10px;
    padding-bottom: 10px;
    border: 1px solid #ddd;
    background-color: #eee !important;
}

</style>


<script type="text/javascript">

var untappedJson = "resources/files/untappd.json";   

/* Parse JSON file, create charts, draw markers on map */
d3.json(untappedJson, function (error, data) {
  var beerData = data.response.beers.items;

  var fullDateFormat = d3.time.format('%a, %d %b %Y %X %Z');
  var yearFormat = d3.time.format('%Y');
  var monthFormat = d3.time.format('%b');
  var dayOfWeekFormat = d3.time.format('%a');

  // normalize/parse data so dc can correctly sort & bin them
  // I like to think of each "d" as a row in a spreadsheet
  _.each(beerData, function(d) {
    d.count = +d.count;
    // round to nearest 0.25
    d.rating_score = Math.round(+d.rating_score * 4) / 4;
    d.beer.rating_score = Math.round(+d.beer.rating_score *4) / 4;
    // round to nearest 0.5
    d.beer.beer_abv = Math.round(+d.beer.beer_abv * 2) / 2;
    // round to nearest 10
    d.beer.beer_ibu = Math.floor(+d.beer.beer_ibu / 10) * 10;

    d.first_had_dt = fullDateFormat.parse(d.first_had);
    d.first_had_year = +yearFormat(d.first_had_dt);
    d.first_had_month = monthFormat(d.first_had_dt);
    d.first_had_day = dayOfWeekFormat(d.first_had_dt);
  });

  // set crossfilter
  var ndx = crossfilter(beerData);

  // create dimensions (x-axis values)
  var yearDim  = ndx.dimension(function(d) {return d.first_had_year;}),
      // dc.pluck: short-hand for same kind of anon. function we used for yearDim
      monthDim  = ndx.dimension(dc.pluck('first_had_month')),
      dayOfWeekDim = ndx.dimension(dc.pluck('first_had_day')),
      ratingDim = ndx.dimension(dc.pluck('rating_score')),
      commRatingDim = ndx.dimension(function(d) {return d.beer.rating_score;}),
      abvDim = ndx.dimension(function(d) {return d.beer.beer_abv;}),
      ibuDim = ndx.dimension(function(d) {return d.beer.beer_ibu;}),
      allDim = ndx.dimension(function(d) {return d;});

  // create groups (y-axis values)
  var all = ndx.groupAll();
  var countPerYear = yearDim.group().reduceCount(),
      countPerMonth = monthDim.group().reduceCount(),
      countPerDay = dayOfWeekDim.group().reduceCount(),
      countPerRating = ratingDim.group().reduceCount(),
      countPerCommRating = commRatingDim.group().reduceCount(),
      countPerABV = abvDim.group().reduceCount(),
      countPerIBU = ibuDim.group().reduceCount();

  // specify charts
  var yearChart   = dc.pieChart('#chart-ring-year'),
      monthChart   = dc.pieChart('#chart-ring-month'),
      dayChart   = dc.pieChart('#chart-ring-day'),
      ratingCountChart  = dc.barChart('#chart-rating-count'),
      commRatingCountChart  = dc.barChart('#chart-community-rating-count'),
      abvCountChart  = dc.barChart('#chart-abv-count'),
  //    ibuCountChart  = dc.barChart('#chart-ibu-count'),
      dataCount = dc.dataCount('#data-count')
      dataTable = dc.dataTable('#data-table');

  yearChart
      .width(150)
      .height(150)
      .dimension(yearDim)
      .group(countPerYear)
      .innerRadius(20);

  monthChart
      .width(150)
      .height(150)
      .dimension(monthDim)
      .group(countPerMonth)
      .innerRadius(20)
      .ordering(function (d) {
        var order = {
          'Jan': 1, 'Feb': 2, 'Mar': 3, 'Apr': 4,
          'May': 5, 'Jun': 6, 'Jul': 7, 'Aug': 8,
          'Sep': 9, 'Oct': 10, 'Nov': 11, 'Dec': 12
        };
        return order[d.key];
      });

  dayChart
      .width(150)
      .height(150)
      .dimension(dayOfWeekDim)
      .group(countPerDay)
      .innerRadius(20)
      .ordering(function (d) {
        var order = {
          'Mon': 0, 'Tue': 1, 'Wed': 2, 'Thu': 3,
          'Fri': 4, 'Sat': 5, 'Sun': 6
        }
        return order[d.key];
      }
     );

  ratingCountChart
      .width(300)
      .height(180)
      .dimension(ratingDim)
      .group(countPerRating)
      .x(d3.scale.linear().domain([0,5.2]))
      .elasticY(true)
      .centerBar(true)
      .barPadding(5)
      .xAxisLabel('My rating')
      .yAxisLabel('Count')
      .margins({top: 10, right: 20, bottom: 50, left: 50});
  ratingCountChart.xAxis().tickValues([0, 1, 2, 3, 4, 5]);

  commRatingCountChart
      .width(300)
      .height(180)
      .dimension(commRatingDim)
      .group(countPerCommRating)
      .x(d3.scale.linear().domain([0,5.2]))
      .elasticY(true)
      .centerBar(true)
      .barPadding(5)
      .xAxisLabel('Community rating')
      .yAxisLabel('Count')
      .margins({top: 10, right: 20, bottom: 50, left: 50});
  commRatingCountChart.xAxis().tickValues([0, 1, 2, 3, 4, 5]);

  abvCountChart
      .width(300)
      .height(180)
      .dimension(abvDim)
      .group(countPerABV)
      .x(d3.scale.linear().domain([-0.2, d3.max(beerData, function (d) { return d.beer.beer_abv; }) + 0.2]))
      .elasticY(true)
      .centerBar(true)
      .barPadding(2)
      .xAxisLabel('Alcohol By Volume (%)')
      .yAxisLabel('Count')
      .margins({top: 10, right: 20, bottom: 50, left: 50});

  dataCount
      .dimension(ndx)
      .group(all);

   dataTable
    .dimension(allDim)
    .group(function (d) { return 'dc.js insists on putting a row here so I remove it using JS'; })
    .size(100)
    .columns([
      function (d) { return d.brewery.brewery_name; },
      function (d) { return d.beer.beer_name; },
      function (d) { return d.beer.beer_style; },
      function (d) { return d.rating_score; },
      function (d) { return d.beer.rating_score; },
      function (d) { return d.beer.beer_abv; },
      function (d) { return d.beer.beer_ibu; }
    ])
    .sortBy(dc.pluck('rating_score'))
    .order(d3.descending)
    .on('renderlet', function (table) {
      // each time table is rendered remove nasty extra row dc.js insists on adding
      table.select('tr.dc-table-group').remove();

      
  });

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

   d3.selectAll('a#day').on('click', function () {
     dayChart.filterAll();
     dc.redrawAll();
   });

   // showtime!
   dc.renderAll();

 });
 
//var yearRingChart2   = dc.pieChart("#chart-ring-year2");
 
d3.json("/syjservicesbcore/syjsFAKT_DB.do?user=OSCAR&year=2017", function(error, data) {
	var faktData = data.dtoList;
    console.log("faktData="+faktData);  //Tip: View i  Chrome devtool; NetWork-(mark xhr row)-Preview

    // normalize/parse data
	 _.each(faktData, function(d) {
	  d.faavd = d.faavd;
	  d.faopd = d.faopd;
	  d.sumfabeln = +d.sumfabeln;
	  d.fadato = +d.fadato;
	  d.fakda = d.fakda;
	});
 
	// set crossfilter
	 var ndx = crossfilter(faktData);
	
	 var datoDim  = ndx.dimension(function(d) {return d.fadato;}),
	     avdDim  = ndx.dimension(function(d) {return d.faavd;}),
	     pengarDimGroup = datoDim.group().reduceSum(function(d) {return d.sumfabeln;});
  
	 
	 //TODO fix dimension
/*
	 yearRingChart2
	     .width(300)
	     .height(300)
	     .dimension(datoDim)
	     .group(pengarDimGroup)
	     .innerRadius(50)
	     .controlsUseVisibility(true);  
*/    
    
    
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
		<%-- space separator --%>
	 		<table width="100%" class="tabThinBorderWhite" border="0" cellspacing="0" cellpadding="0">
	 	    <tr height="20">
		 	    <td width="2%">&nbsp;</td>
		 	    <td>&nbsp;
				<div class="container-fluid">
				  <div class="row show-grid">
				    <div class="col-xs-12 dc-data-count dc-chart text12" id="data-count">
				       Faktura poster
				        <small>
				          <span class="filter-count"></span> valgt ut av <span class="total-count"></span> poster |
				           <a id="all" href="#">Tilbakestill alt</a>
				        </small>
				    </div>
				  </div>
				  <div class="row show-grid" id="control-row">
				    <div class="col-xs-2 pie-chart">
				      <h4>År <small><a id="year">tilbakestill</a></small></h4>
				      <div class="dc-chart" id="chart-ring-year"></div>
				    </div>
				    <div class="col-xs-2 pie-chart">
				      <h4>Måned <small><a id="month" href="#">tilbakestill</a></small></h4>
				      <div class="dc-chart" id="chart-ring-month"></div>
				    </div>
				    <div class="col-xs-2 pie-chart">
				      <h4>Dag <small><a id="day">tilbakestill</a></small></h4>
				      <div id="chart-ring-day" class="dc-chart"></div>
				    </div>
<!--  
				    <div class="col-xs-6">
				      <h4>Breweries</h4>
				      <div id="map"></div>
				    </div>
-->		
				  </div>
				  <div class="row show-grid">
				    <div class="col-xs-6 col-md-3">
				      <div class="dc-chart" id="chart-rating-count"></div>
				    </div>
				    <div class="col-xs-6 col-md-3">
				      <div class="dc-chart" id="chart-community-rating-count"></div>
				    </div>
				    <div class="col-xs-6 col-md-3">
				      <div class="dc-chart" id="chart-abv-count"></div>
				    </div>
<!-- 
				    <div class="col-xs-6 col-md-3">
				      <div class="dc-chart" id="chart-ibu-count"></div>
				    </div>
 -->
				  </div>
				  <div class="row show-grid">
				    <div class="col-xs-12">
				      <table class="table table-bordered table-striped" id="data-table">
				        <thead>
				          <tr class="header">
				            <th>Brewery</th>
				            <th>Beer</th>
				            <th>Style</th>
				            <th>My Rating</th>
				            <th>Community Rating</th>
				            <th>ABV %</th>
				            <th>IBU</th>
				          </tr>
				        </thead>
				      </table>
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

