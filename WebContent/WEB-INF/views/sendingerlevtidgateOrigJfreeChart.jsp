<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerSendingerlevtid.jsp" />
<!-- =====================end header ==========================-->
<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
<tr>
	<td>
	<%-- tab container component --%>
	<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
		<tr height="2"><td></td></tr>
		<tr height="25"> 
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
 		<table height="500px" width="100%" class="tabThinBorderWhite" border="0" cellspacing="0" cellpadding="0">
 	        <tr height="100"><td></td></tr>
 	        <tr >
 	        		<td valign="top" >
 	        			<table align="left" border="0" >
 	        				<tr>
 	        					<td >&nbsp;&nbsp;
 	        						<img src="resources/tmpCharts/${jfreeChartFileLevQA}" border="0" alt="Graph not loaded. Check logs..."> 
 	        					</td>
 	        					<td >
 	        						<img src="resources/tmpCharts/${jfreeChartFileArchiveQA}" border="0" alt="Graph not loaded. Check logs..."> 
 	        					</td>
 	        				</tr>
 	        				<%-- 
 	        				<tr>
        						<td align="center" class="text12">&nbsp;&nbsp;POD-kvalitet</td>
 	        					<td align="center" class="text12">&nbsp;&nbsp;POD Archived-kvalitet</td>
        					</tr>
        					--%>
        					<tr >
			 	        		<td align="center" >
			 	        			<form name="formKvalitet" id="formKvalitet" action='sendingerlevtid.do' method="POST" >
					 	        			
			 	        			<table width=250px" align="center" border="0" >
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
			 	        		<td align="center" >
			 	        			
			 	        			<table width=250px" align="center" border="0" >
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
		        					</form>
    							</td>
    							
        					</tr>
						<tr height="50"><td></td></tr>

 	        				<tr >
			 	        		<td colspan="2">
			 	        			<table width=500px" class="tableBorder" align="center" border="0" >
				 	    				<tr>
			 	        					<td class="text12">&nbsp;
						 	        			Totalt <font class="text14Bold">${model.sendingerListSize}</font>&nbsp;sendinger&nbsp;
						 	        			<a class="text12" href="sendingerlevtid.do" >
						 	        				<img src="resources/images/app.png" border="0" width="16" height="16">&nbsp;Detaljert lista
											</a>
					 	        			</td>
			 	        				</tr>
			 	        				
			 	        				<tr height="3"><td></td></tr>
			 	        				<tr>
			 	        					<td class="text12">
			 	        					<table cellspacing="2">
			 	        					<form name="formRefresh" id="formRefresh" action="sendingerlevtidgate.do" method="POST" >
					 	        				<td class="text12">
					 	        					Avd
					 	        					<select class="text11" id="searchFilterGateCharAvd" name="searchFilterGateCharAvd">
					 	        					<option value="">-Alle-</option>
												<c:forEach items="${model.hsAvd}" var="record" varStatus="counter">
				 	        							<option value='${record}'<c:if test="${model.searchFilterGateChar.avd == record}"> selected </c:if>>${record}</option>
												</c:forEach>
												</select>
												
					 	        				</td>	
					 	        				<%--
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
        			<td>		
				
 	        </tr>
		</table>
	</td>
</tr>
</table>

<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->

