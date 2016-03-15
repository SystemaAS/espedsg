<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerSendingerlevtid.jsp" />
<!-- =====================end header ==========================-->
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript">
      google.load("visualization", "1", {packages:["corechart"]});
      google.setOnLoadCallback(drawChart);
      //------------------------------------- 
      //Main function for drawing both Pies 
      //------------------------------------- 
      function drawChart() {
    		  //--------------------------
          //(1) Pie Chart- POD [main] 
          //--------------------------
          var data = new google.visualization.DataTable();
          var leveringsKvalDbl = Number('${model.container.leveringsKvalDbl}');
          //alert(leveringsKvalDbl);
          data.addColumn('string', 'Leveringskvalitet');
          data.addColumn('number', 'Prosent');
          data.addRows([
            ['Ok', leveringsKvalDbl],
            ['Ikke Ok', 100-leveringsKvalDbl]
          ]);

          // Set chart options 
          var options = {title:'Leveringskvalitet',
		        		     titleTextStyle	:{fontSize: 16},
        		 			 is3D: true,
        		 			backgroundColor:{stroke: '#DDDDDD', strokeWidth: 2},
        		  			 width:500,
                         height:300,
                         chartArea: {left:100, 'height': '80%'},
                         slices:{0: {color: '4AC948'}, 1: {color: 'red'}}
                         };

          // Instantiate and draw our chart, passing in some options.
          var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
          chart.draw(data, options);
        	  //send the chart and the selectHandler
	      google.visualization.events.addListener(chart, 'select', selectHandlerLevQA);
          
        	  
        	  
          //----------------------------
          //(2) Pie Chart- POD Archived 
          //----------------------------
          var dataArk = new google.visualization.DataTable();
          var archievedKvalDbl = Number('${model.container.archievedKvalDbl}');
        	  //alert(archievedKvalDbl);
          dataArk.addColumn('string', 'POD Archived-kvalitet');
          dataArk.addColumn('number', 'Prosent');
          dataArk.addRows([
            ['Ok', archievedKvalDbl],
            ['Ikke Ok', 100-archievedKvalDbl]
          ]);

          // Set chart options 
          var optionsArk = {title:'POD Archived-kvalitet',
			        		    titleTextStyle	:{fontSize: 16},
      		  				is3D: true,
      		  				backgroundColor:{stroke: '#DDDDDD', strokeWidth: 2},
      		  				width:500,
                  			height:300,
                  			chartArea: {left:100, 'height': '80%'},
                  			slices:{0: {color: '4AC948'}, 1: {color: 'red'}}
                  			};

          // Instantiate and draw our chart, passing in some options.
          var chartArk = new google.visualization.PieChart(document.getElementById('chart_divArk'));
          chartArk.draw(dataArk, optionsArk);
          //send the chart and the selectHandler
	      google.visualization.events.addListener(chartArk, 'select', selectHandlerArkQA);
          
        	  //==================================== 
        	  //Inner functions = handlers 
        	  //One handler per chart
        	  //... in order to enable href clicks !
        	  //==================================== 
          function selectHandlerLevQA() {
        		  var row = chart.getSelection()[0].row;
	          var element = data.getValue(row, 0);
	          //alert('You just selected: ' + element);
	          if(element=='Ok'){
          		window.location.href = 'sendingerlevtid.do?submitOK_LevQA=Y';
          	  }else{
          		window.location.href = 'sendingerlevtid.do?submitNOK_LevQA=N';
          	  }
	          
	        }
          function selectHandlerArkQA() {
	    		  var row = chartArk.getSelection()[0].row;
	          var element = data.getValue(row, 0);
	          //alert('You just selected: ' + element);
	          if(element=='Ok'){
	      		window.location.href = 'sendingerlevtid.do?submitOK_ArkQA=Y';
	      	  }else{
	      		window.location.href = 'sendingerlevtid.do?submitNOK_ArkQA=N';
	      	  }
          
	        }
	      
       }
      
    </script>


<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
<tr>
	<td>
	<%-- tab container component --%>
	<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
		<tr height="2"><td></td></tr>
		<tr height="25"> 
			<td width="20%" valign="bottom" class="tabDisabled" align="center">
				<a style="display:block;" href="sendingerlevtidgatefilter.do?action=doBack">
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.overview.sendingerlevtid.filter.tab"/></font>
					&nbsp;<img valign="bottom" src="resources/images/find.png" border="0" alt="filter">
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			
		
			<td width="20%" valign="bottom" class="tab" align="center">
				<font class="tabLink">&nbsp;<spring:message code="systema.overview.sendingerlevtid.diagram.tab"/></font>
				<img valign="bottom" src="resources/images/pieChart.png" width="20" hight="20" border="0" alt="Sendinger">
				
			</td>
			<td width="80%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>	
			
		</tr>
	</table>
	</td>
</tr>

<tr>
	<td>
	<%-- tab component --%>
 		<table height="600px" width="100%" class="tabThinBorderWhite" border="0" cellspacing="0" cellpadding="0">
 	        <tr height="10"><td></td></tr>
 	        
 	        <tr >
 	        		<td align="left" >
 	                	<table align="center" border="0" >
 	                		<tr>
 	                			<td >
		 	                		<div id="chart_div" ></div>
	 	                		</td>
	 	                		<td >
		 	                		<div id="chart_divArk" ></div>
	 	                		</td>
						</tr>
						<form name="formKvalitet" id="formKvalitet" action='sendingerlevtid.do' method="POST" >
						<tr>
							<td>
								<table align="center" >
								<tr>
		       						<td align="right" class="text12">
		       							<input class="inputFormSubmitGrayOnGraphBoldOK" type="submit" name="submitOK_LevQA" value='${model.container.leveringsKval}%'>
		  							</td>
		  							<td align="left" class="text12">
		       							&nbsp;OK
		  							</td>
	  							</tr>
	  							<tr>
			       					<td align="right" class="text12">
		       							<input class="inputFormSubmitGrayOnGraphBoldNOK" type="submit" name="submitNOK_LevQA" value='${model.container.leveringsKvalNotOk}%'>
		  							</td>
		  							<td align="left" class="text12">
		       							&nbsp;Ikke OK
		  							</td>
	  							</tr>
	  							</table>
  							</td>
  							<td>
								<table align="center" >
								<tr>
		       						<td align="right" class="text12">
		       							<input class="inputFormSubmitGrayOnGraphBoldOK" type="submit" name="submitOK_ArkQA" value='${model.container.archievedKval}%'>
		  							</td>
		  							<td align="left" class="text12">
		       							&nbsp;OK
		  							</td>
	  							</tr>
	  							<tr>
			       					<td align="right" class="text12">
		       							<input class="inputFormSubmitGrayOnGraphBoldNOK" type="submit" name="submitNOK_ArkQA" value='${model.container.archievedKvalNotOk}%'>
		  							</td>
		  							<td align="left" class="text12">
		       							&nbsp;Ikke OK
		  							</td>
	  							</tr>
	  							</table>
  							</td>
  						</tr>
  						</form>
     				</table>
       			</td>
       		</tr>
       		<tr height="10"><td></td></tr>
			<tr >
 	        		<td>
 	        			<table width=500px" class="tableBorder" align="center" border="0" >
	 	    				<tr>
 	        					<td class="text12">&nbsp;
			 	        			Sendtinef / Leveringskvalitet&nbsp;<font class="text14Bold">${model.sendingerListSize}</font>&nbsp;sendinger&nbsp;
			 	        			<a class="text12" href="sendingerlevtid.do" >
			 	        				<img src="resources/images/app.png" border="0" width="16" height="16">&nbsp;Detaljert lista
								</a>
		 	        			</td>
 	        				</tr>
 	        				
 	        				<tr height="3"><td></td></tr>
 	        				<tr>
 	        					<td class="text12">
 	        					<table align="center" cellspacing="2">
 	        					<form name="formRefresh" id="formRefresh" action="sendingerlevtidgate.do?action=doBack" method="POST" >
 	        						<%--
		 	        				<td class="text12">
		 	        					Avd
		 	        					<select class="text11" id="searchFilterGateCharAvd" name="searchFilterGateCharAvd">
		 	        					<option value="">-Alle-</option>
									<c:forEach items="${model.hsAvd}" var="record" varStatus="counter">
	 	        							<option value='${record}'<c:if test="${model.searchFilterGateChar.avd == record}"> selected </c:if>>${record}</option>
									</c:forEach>
									</select>
									
		 	        				</td>	
		 	        				
		 	        				<td class="text12">
		 	        					Sign
		 	        					<select class="text11" id="searchFilterGateCharSign" name="searchFilterGateCharSign">
		 	        					<option value="">-Alle-</option>
									<c:forEach items="${model.hsSign}" var="record" varStatus="counter">
	 	        							<option value='${record}'<c:if test="${model.searchFilterGateChar.sign == record}"> selected </c:if> >${record}</option>
									</c:forEach>
									</select>
									
		 	        				</td>
		 	        				 --%>
		 	        				<td class="text12">
		 	        					&nbsp;&nbsp;&nbsp;
		 	        					<input class="inputFormSubmitGrayOnGraph" type="submit" name="submit" value='Oppdater diagram'>
		 	        				</td>
		 	        					
	 	        				</form>
	 	        				</table>
	 	        				</td>			
 	        				</tr>
 	        			</table>
 	        		</td>
			</tr>
		</table>
	</td>
</tr>
</table>

<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->

