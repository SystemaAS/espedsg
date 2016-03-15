<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerUoppdrag.jsp" />
<!-- =====================end header ==========================-->
	<SCRIPT type="text/javascript" src="resources/js/uoppdraggate.js?ver=${user.versionEspedsg}"></SCRIPT>

<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
<tr>
	<td>
	<%-- tab container component --%>
	<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
		<tr height="2"><td></td></tr>
		<tr height="25"> 
			<td width="20%" valign="bottom" class="tab" align="center">
				<font class="tabLink">&nbsp;<spring:message code="systema.overview.uoppdrag.diagram.tab"/></font>
				<img valign="bottom" src="resources/images/barChart.png" width="20" hight="20" border="0" alt="Uoppdrag">
				
			</td>
			<td width="80%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>	
			
		</tr>
	</table>
	</td>
</tr>
<tr>
	<td>
	<%-- tab component --%>
 		<table height="650px" width="100%" class="tabThinBorderWhite" border="0" cellspacing="0" cellpadding="0">
 	        <tr height="20"><td></td></tr>
 	        <tr >
 	        		<td valign="top" >
 	        			<table align="left" border="0" >
 	        				<tr>
 	        					<td colspan="2" >&nbsp;&nbsp;&nbsp;
 	        						<img src="resources/tmpCharts/${jfreeChartFile}" border="0" alt="Graph not loaded. Check logs..."> 
 	        					</td>
 	        				</tr>
 	        				<tr >
			 	        		<td colspan="2">
			 	        			<table style="table-layout: fixed;" width="910px" align="center" border="0" >
			 	    					<tr>
				 	    					<td width="40px">&nbsp;</td>
				 	    					<c:forEach items="${model.chartCategoriesList}" var="record" varStatus="counter">
				 	    						<td width="10px"></td>
				 	    					 	<td align="center" class="tableCell" >
				 	    					 		<a style="display:block;" href="uoppdrag.do?uoType=partial&llim=${record.start}&ulim=${record.end}">
				 	    					 			<c:choose>
					 	    					 			<c:when test="${record.start == record.end}">
						 	    					 			<font class="text10">dg&nbsp;${record.start}<br></font>
					 	    					 			</c:when>
					 	    					 			<c:otherwise>
					 	    					 				<c:choose>
					 	    					 				<c:when test="${record.start == '-9999' || record.end == '9999' }">
					 	    					 					<c:if test="${record.start == '-9999'}">
						 	    					 					<font class="text10">&lt;${record.end}<br></font>
						 	    					 				</c:if>
						 	    					 				<c:if test="${record.end == '9999'}">
						 	    					 					<font class="text10">${record.start}&lt;<br></font>
						 	    					 				</c:if>
						 	    					 				
					 	    					 				</c:when>
					 	    					 				<c:otherwise>
					 	    					 					<font class="text10">dg&nbsp;${record.start}-${record.end}<br></font>
					 	    					 				</c:otherwise>
					 	    					 				</c:choose>
					 	    					 			</c:otherwise>
				 	    					 			</c:choose>
								             		<font class="text11Bold">${record.numberOfDager}</font>
									             	<font class="text11">&nbsp;stk</font>
				 	    							</a>
			 	    							</td>
				 	        				</c:forEach>	
			 	        				</tr>	  
			 	        			</table>
		 	        			</td>
	 	        			</tr>
						<tr height="25"><td></td></tr>

						<form name="formRefresh" id="formRefresh" method="POST" >
						<tr >
							<td>
			 	        			<table class="tableBorder" align="center" border="0" >
				 	        			<tr>
			 	        					<td class="text12">
			 	        					<table cellspacing="1" border="0">
			 	        						<tr>
			 	        						<td class="text12">
			 	        							<a href="uoppdrag.do?">
			 	        								&nbsp;
					 	        						<font class="text14Bold" style="font-style:italic;text-decoration:underline;"><b>${model.ufortListSize}</b></font>
					 	        						<font style="font-style:italic;text-decoration:underline;">ufortollede oppdrag. Se detaljer.</font>
					 	        						
						 	        				</a>
					 	        				</td>
					 	        				<td class="text12">
					 	        					<input style="visibility:hidden;" class="inputFormSubmitGrayOnGraph" type="submit" name="submit" id="submit" onclick="javascript: form.action='uoppdraggate.do';" value='Oppdater diagram'>
					 	        					<%--
					 	        					<img onMouseOver="showPop('clientChart_info');" onMouseOut="hidePop('clientChart_info');"style="vertical-align:top;" width="14px" height="14px" src="resources/images/info3.png" border="0" alt="info">
												<span id="clientChart_info" style="position:absolute; left:680px; top:640px; width:250px; height:100px;" class="popupWithInputText"  >
									           		<div class="text11" align="left">
									           		<b>Oppdater diagram</b>
									           		<br/><br/>
									           		Diagrammet er oppdatert på <b>klientsidan</b>.<br/>
									           		Bra når du skifter filter forhold og ønsker å se en rask diagram av utvalg.
													</div>
												</span>
												 --%>							
											</td>	
											</tr>
											</table>
			 	        					</td>
			 	        							
			 	        				</tr>
			 	        				<tr height="3"><td></td></tr>
			 	        				<tr>
			 	        					<td class="text12">
			 	        					<table cellspacing="1" border="0">
			 	        						<tr>
			 	        						<td class="text12">
					 	        					<select class="text11" id="tariffor" name="tariffor">
						 	        					<option style="font-style:italic;" value="">Tariffør - Alle</option>
													<c:forEach items="${model.hsTariffor}" var="record" varStatus="counter">
														<c:if test="${not empty record}">
					 	        								<option value='${record}'<c:if test="${model.searchFilterGateChar.tariffor == record}"> selected </c:if> >${record}</option>
					 	        							</c:if>
													</c:forEach>
												</select>
					 	        				</td>
					 	        				<td class="text12">
					 	        					<select class="text11" id="sign" name="sign">
						 	        					<option style="font-style:italic;" value="">Sign - Alle</option>
													<c:forEach items="${model.hsSign}" var="record" varStatus="counter">
														<c:if test="${not empty record}">
					 	        								<option value='${record}'<c:if test="${model.searchFilterGateChar.sign == record}"> selected </c:if> >${record}</option>
					 	        							</c:if>
													</c:forEach>
												</select>
					 	        				</td>
			 	        						<td class="text12">&nbsp;&nbsp;&nbsp;
			 	        							<img onClick="showPop('avd_info');"style="vertical-align:top;cursor:pointer;" width="14px" height="14px" src="resources/images/info3.png" border="0" alt="info">
												<span style="position:absolute; left:550px; top:600px; width:350px; height:200px;" id="avd_info" class="popupWithInputText"  >
									           		<div class="ownScrollableSubWindow" style="width:345px; height:150px;">
									           			<nav>
									           			<font class="text12"><b>Ytterligere informasjon</b> om avdelinger (<b>navn</b>)</font>
									           			<br/><br/>
									           			<select size="5" class="text11" id="avdExtraInfo" name="avdExtraInfo">
								 	        					<c:forEach items="${model.hsAvdAvdNavn}" var="record" varStatus="counter">
																<c:if test="${not empty record}">
							 	        								<option value="${record}">${record}</option>
							 	        							</c:if>
															</c:forEach>
														</select>
									           			<br/><br/>
									           			</nav>
									           		</div>
									           		<button name="avdInformationButtonClose" id="avdInformationButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('avd_info');">Ok</button> 
								           			<br/>	
										        </span>
										        <select class="text11" id="avd" name="avd">
						 	        					<option style="font-style:italic;" value="">Avd - Alle</option>
													<c:forEach items="${model.hsAvd}" var="record" varStatus="counter">
														<c:if test="${not empty record}">
					 	        								<option value='${record}' <c:if test="${model.searchFilterGateChar.avd == record}"> selected </c:if> >${record}</option>
					 	        							</c:if>
													</c:forEach>
												</select>
												 
					 	        				</td>
											<td class="text12" >
												<select class="text11" name="avdList" id="avdList">
									            		<option style="font-style:italic;" value="">Avd.gruppe - Alle</option>
								 				  	<c:forEach var="record" items="${model.avdGroupsList}" >
								 				  		<c:if test="${not empty record}">
					                             	 		<option value='${record.avdList}'<c:if test="${model.searchFilterGateChar.avdList == record.avdList}"> selected </c:if>  >${record.avdGrpNavn}</option>
					                             	 	</c:if>
													</c:forEach> 
												</select>
												
											</td>
											
											<c:choose>
												<c:when test="${model.hsTollagerkodSize>1}">
													<td class="text12" >
														<select class="text11" name="tollagerkod" id="tollagerkod">
											            		<option style="font-style:italic;" value="">Lagerkode - Alle</option>
										 				  	<c:forEach items="${model.hsTollagerkod}" var="record" varStatus="counter">
										 				  		<c:if test="${not empty record}">
							                             	 		<option value='${record}'<c:if test="${model.searchFilterGateChar.tollagerkod == record}"> selected </c:if>  >${record}</option>
							                             	 	</c:if>
															</c:forEach> 
														</select>
													</td>
													<c:if test="${model.hsTollagerdelkodSize>1}">
														<td class="text12" >
															<select class="text11" name="tollagerdelkod" id="tollagerdelkod">
												            		<option style="font-style:italic;" value="">Enh.kode - Alle</option>
											 				  	<c:forEach items="${model.hsTollagerdelkod}" var="record" varStatus="counter">
											 				  		<c:if test="${not empty record}">
								                             	 		<option value='${record}'<c:if test="${model.searchFilterGateChar.tollagerdelkod == record}"> selected </c:if>  >${record}</option>
								                             	 	</c:if>
																</c:forEach> 
															</select>
														</td>
													</c:if>
												</c:when>
												<c:otherwise>
													<%-- IF and ONLY IF delkod > 1 then show parent-child --%>
													<c:if test="${model.hsTollagerdelkodSize>1}"> 
														<td class="text12" >
															<select class="text11" name="tollagerkod" id="tollagerkod">
												            		<option style="font-style:italic;" value="">Lagerkode - Alle</option>
											 				  	<c:forEach items="${model.hsTollagerkod}" var="record" varStatus="counter">
											 				  		<c:if test="${not empty record}">
								                             	 		<option value='${record}'<c:if test="${model.searchFilterGateChar.tollagerkod == record}"> selected </c:if>  >${record}</option>
								                             	 	</c:if>
																</c:forEach> 
															</select>
														</td>
														<td class="text12" >
															<select class="text11" name="tollagerdelkod" id="tollagerdelkod">
												            		<option style="font-style:italic;" value="">Enh.kode - Alle</option>
											 				  	<c:forEach items="${model.hsTollagerdelkod}" var="record" varStatus="counter">
											 				  		<c:if test="${not empty record}">
								                             	 		<option value='${record}'<c:if test="${model.searchFilterGateChar.tollagerdelkod == record}"> selected </c:if>  >${record}</option>
								                             	 	</c:if>
																</c:forEach> 
															</select>
														</td>
													</c:if>
												</c:otherwise>
											</c:choose>

											
											<td class="text12" >&nbsp;&nbsp;
												<select class="text11" id="antHfaktFlag" name="antHfaktFlag">
							        					<option style="font-style:italic;" value="">HandelsFakt - Alle</option>
						        						<option value="Y" <c:if test="${model.searchFilterGateChar.antHfaktFlag == 'Y'}"> selected </c:if>  >Med skannet HF</option>
						        						<option value="N" <c:if test="${model.searchFilterGateChar.antHfaktFlag == 'N'}"> selected </c:if>  >Uten skannet HF</option>
												</select>
												<select class="text11" id="statusFlag" name="statusFlag">
							        					<option style="font-style:italic;" value="">Status - Alle</option>
						        						<option value="N" <c:if test="${model.searchFilterGateChar.statusFlag == 'N'}"> selected </c:if>  > Ikke påbegynt</option>
						        						<option value="Y" <c:if test="${model.searchFilterGateChar.statusFlag == 'Y'}"> selected </c:if>  > Påbegynt</option>
												</select>
											</td>
					 	        				</tr>	
				 	        				</table>
				 	        				</td>				 	        							
			 	        				</tr>
			 	        			</table>
			 	        		</td>
						</tr>
						<tr height="10"><td></td></tr>
						<tr>
							<td>
			 	        			<table align="center" border="0" >
				 	        			<tr>
				 	        				<td class="text12" >
											<img onMouseOver="showPop('serverChart_info');" onMouseOut="hidePop('serverChart_info');"style="vertical-align:top;" width="14px" height="14px" src="resources/images/info3.png" border="0" alt="info">
											<input class="inputFormSubmitGrayOnGraph" style="cursor:pointer;" type="submit" name="deepSubmit" id="deepSubmit" onclick="javascript: form.action='uoppdraggate.do';" value='Oppdater datagrunnlaget'>	
											<span id="serverChart_info" style="position:absolute; left:700px; top:600px; width:300px; height:100px;" class="popupWithInputText"  >
								           		<div class="text11" align="left">
								           		<b>Oppdater datagrunnlaget</b>
								           		<br/><br/>
								           		Trykk her når du ønsker å hente ferske data fra 
					        						server.<br/>Alle data om ufortollede blir da hentet på ny. 
	        										Diagrammet blir samtidig automatisk oppdatert 
	        										ut fra de filter du har satt. 
								           		</div>
											</span>		
				    						</td>
				 	        				<td class="text12" style="min-width:10px" >&nbsp;</td>
					 	        			<td class="text12" style="vertical-align:bottom;" >
											<img style="vertical-align:middle;" width="18px" height="18px" src="resources/images/clock.png" border="0" alt="info">
											<select class="text11" id="chartTickerInterval" name="chartTickerInterval">
						        					<option style="font-style:italic;" value='-99'>Auto oppdater - Stoppet</option>
					        						<option value='60000' <c:if test="${model.searchFilterGateChar.autoRefresh == '60000' || chartTickerInterval_SESSION == '60000'}"> selected </c:if>  >Hvert minutt</option>
					        						<option value='120000' <c:if test="${model.searchFilterGateChar.autoRefresh == '120000' || chartTickerInterval_SESSION == '120000'}"> selected </c:if>  >Hvert 2. minutt</option>
					        						<option value='300000' <c:if test="${model.searchFilterGateChar.autoRefresh == '300000' || chartTickerInterval_SESSION == '300000'}"> selected </c:if>  >Hvert 5. minutt</option>
					        						<option value='600000' <c:if test="${model.searchFilterGateChar.autoRefresh == '600000' || chartTickerInterval_SESSION == '600000'}"> selected </c:if>  >Hvert 10. minutt</option>
											</select>
										</td>
										
									</tr>
								</table>
							</td>
						</tr>		
						<tr height="5"><td></td></tr>					
   						</form>
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

