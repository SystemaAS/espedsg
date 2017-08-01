<!DOCTYPE html>
<html lang="en">
<head>
    <title>The Systema report module!</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="../css/dc.css"/>
    <style>
      #table td {
        padding-left: 10px;
      }
    </style>
</head>
<body>

<div class="container">
<script type="text/javascript" src="header.js"></script>
 
 <h2>The Systema report module!</h2>
 <div class="row">
  <div id="chart-ring-year" style="width:300px; height:330px">
    <div class="reset" style="visibility: hidden;">selected: <span class="filter"></span>
      <a href="javascript:yearRingChart.filterAll();dc.redrawAll();">reset</a>
    </div>
  </div>
</div>

  <div id="chart-bar-revenue" style="width:300px; height:330px">
    <div class="reset" style="visibility: hidden;">range: <span class="filter"></span>
      <a href="javascript:revenueBarChart.filterAll();dc.redrawAll();">reset</a>
    </div>
  </div>





















  <!-- not sure why all these styles necessary, not the point of this -->
  <div style="clear: both; margin: 30px; float: left">
    <div id="table"></div>
    <div id="download-type" style="clear: both; float: left">
      <div><label><input type=radio name="operation" value="raw" checked="true">&nbsp;all data</label></div>
      <div><label><input type=radio name="operation" value="table">&nbsp;table data</label></div>
    </div>
    <div style="float: left">
      <button class="btn" id="download">download</button>
    </div>
  </div>

  <link rel="stylesheet" type="text/css" href="https://dc-js.github.io/dc.js/css/dc.css" />
  <link rel="stylesheet" type="text/css" href="https://dc-js.github.io/dc.js/css/bootstrap.min.css">
  <script src="https://dc-js.github.io/dc.js/js/d3.js"></script>
  <script src="https://dc-js.github.io/dc.js/js/crossfilter.js"></script>
  <script src="https://dc-js.github.io/dc.js/js/dc.js"></script>
  <script src="https://rawgit.com/crossfilter/reductio/master/reductio.js"></script>
  <script src="https://npmcdn.com/universe@latest/universe.js"></script>
  <script src="http://colorbrewer2.org/export/colorbrewer.js"></script>
  <script type="text/javascript" src="https://dc-js.github.io/dc.js/js/FileSaver.js"></script>
<script type="text/javascript">

var yearRingChart   = dc.pieChart("#chart-ring-year"),
    revenueBarChart  = dc.barChart("#chart-bar-revenue");

var table = dc.dataTable('#table');

//Datum=fadato, ex. 20060912
//Intäkt=fabeln, ex.200 
//Kundnr=falevn ex. 600012

/*
var spendData = [
    {falevn: '600012', fabeln: '56', fadato: 20060912},
    {falevn: '600012', fabeln: '11', fadato: 20060912},
    {falevn: '600012', fabeln: '42', fadato: 20060912},
    {falevn: '5000', fabeln: '73', fadato: 20070920},
    {falevn: '5000', fabeln: '20', fadato: 20070920},
    {falevn: '5000', fabeln: '59', fadato: 20070920},
    {falevn: '600012', fabeln: '31', fadato: 20060920}
];
*/

var faktCsv = "resources/files/fakt_no_nulls.csv";   

//jQuery.getJson('data.json', function(data){...}); eller d3.json('data.json', function(data) {...});


d3.csv(faktCsv, function(error, spendData) {

// normalize/parse data
spendData.forEach(function(d) {
	  d.Spent = d.Spent;
});

// set crossfilter
var ndx = crossfilter(spendData),
    yearDim  = ndx.dimension(function(d) {return +d.fadato;}),
    nameDim  = ndx.dimension(function(d) {return d.falevn;}),
    yearDimGroup = yearDim.group().reduceSum(function(d) {return +d.fabeln;}),
    nameDimGroup = nameDim.group().reduceSum(function(d) {return +d.fabeln/1000;}),
    allDollars = ndx.groupAll().reduceSum(function(d) { return +d.fabeln; });

  yearRingChart
    .width(300)
    .height(300)
    .dimension(yearDim)
    .group(yearDimGroup)
    .innerRadius(50)
    .controlsUseVisibility(true);



  revenueBarChart
	.width(300)
	.height(200)
	.outerPadding(0)
	//.gap(1)
	//.margins({ top: 0, right: 0, bottom: 95, left: 30 })
	 .margins({top: 10, right: 50, bottom: 30, left: 40})
	.group(nameDimGroup)
	.dimension(nameDim)
	.elasticY(true)
	.xUnits(dc.units.ordinal)
	.brushOn(true)
	.x(d3.scale.ordinal())
	.controlsUseVisibility(true)
	//.ordinalColors(['red','green','blue'])
	.colors(colorbrewer.RdYlGn[2])
	 .xAxisLabel('Kundenr')
	 .yAxisLabel('Intekt')
	.renderHorizontalGridLines(true);


  table
    .dimension(nameDim)
    .group(function(d) {
        return d.value;
    })
    .sortBy(function(d) { return +d.fabeln; })
    .showGroups(false)
    .columns(['falevn',
              {
                  label: 'Fabeln',
                  format: function(d) {
                      return d.fabeln;
                  }
              },
              'fadato',
              {
                  label: 'Percent of Total',
                  format: function(d) {
                      return Math.floor((d.fabeln / allDollars.value()) * 100) + '%';
                  }
              }]);

d3.select('#download')
    .on('click', function() {
        var data = nameDim.top(Infinity);
        if(d3.select('#download-type input:checked').node().value==='table') {
            data = data.sort(function(a, b) {
                return table.order()(table.sortBy()(a), table.sortBy()(b));
            });
            data = data.map(function(d) {
                var row = {};
                table.columns().forEach(function(c) {
                    row[table._doColumnHeaderFormat(c)] = table._doColumnValueFormat(c, d);
                });
                return row;
            });
        }
        var blob = new Blob([d3.csv.format(data)], {type: "text/csv;charset=utf-8"});
        saveAs(blob, 'data.csv');
    });

dc.renderAll(); 

});

</script>

</div>
</body>
</html>
