<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ================== special login header ==================-->
<jsp:include page="/WEB-INF/views/headerDashboard.jsp" />
<!-- =====================end header ==========================-->
<SCRIPT type="text/javascript" src="resources/js/dashboard.js?ver=${user.versionEspedsg}"></SCRIPT>

		<%-- Applications' menu --%>
		<tr height="400" >
			<td height="300" width="500" align="center" valign="top" > 
    			 <table width="96%" class="dashboardFrameMain" border="0" cellspacing="0" cellpadding="0">
    			 		<tr class="text" height="1"><td></td></tr>
    			 		<tr>
			 			<td class="text12" align="center" >		
			 				<table width="96%" align="center" class="dashboardFrameHeader" border="0" cellspacing="0" cellpadding="0">
						 		<tr height="20">
						 			<td class="text14White">
						 				<b>&nbsp;Modul<a href="asyjservices_mainlist.do" ><font class="text14White">e</font></a>r - espedSg&nbsp;</b>
					 				</td>
				 				</tr>
			 				</table>
			 			</td>
			 		</tr>
			 		<tr >
			    		<td class="text12" align="center" >
			    			
			    			<table width="96%" align="center" class="dashboardFrame" border="0" cellspacing="0" cellpadding="0">
						 		<tr >
						 			<%--<td style="border-left: 2px solid #3F9E4D; padding: 5px;" align="left" height="60px" class="text14"> --%>
						 			<td align="left" height="60px" class="text14">
						 			<ol>
						 			<c:if test="${user.user == 'OSCAR'}">
						 				<li style="line-height:20px;">
						 				<font class="text14">
							 				<a class="text14" href="asyjservices_mainlist.do" > 	
			 									<img src="resources/images/bulletGreen.png" width="10px" height="10px" border="0">&nbsp;
			 									<font class="text14NavyBlue">JavaServices</font>
	 										</a>
										</font>
										</li>
										<li style="line-height:20px;">
						 				<font class="text14">
							 				<a class="text14" href="aespedsg_roadmap.do" > 	
			 									<img src="resources/images/bulletGreen.png" width="10px" height="10px" border="0">&nbsp;
			 									<font class="text14NavyBlue">eSpedsg Roadmap</font>
	 										</a>
										</font>
										</li>
										<li style="line-height:20px;">
						 				<font class="text14">
							 				<a class="text14" href="ebooking_mainorderlist.do?lang=${user.usrLang}&action=doFind" > 	
			 									<img src="resources/images/bulletGreen.png" width="10px" height="10px" border="0">&nbsp;
			 									<font class="text14NavyBlue">ebooking eSpedsg</font>
	 										</a>
										</font>
										</li>
										
						 			</c:if>
			 						<c:forEach items="${list}" var="record" varStatus="counter"> 
						 				<c:if test="${ fn:contains(record.prog, 'TOMCAT') }">
						 					<c:set var="imgSrcTomcat" scope="session" value="resources/images/bulletGreen.png"/>
											<li style="line-height:20px;">
					 						<font class="text14">
					 						<c:if test="${fn:contains(record.prog,'-WRKTRIPS') }">
							 					<font class="text14">
							 						<%-- uncomment this line IF more menu choices appear...
									 					<a class="text14" href="transportdispgate.do" >
									 				 --%>
									 				<a class="text14" href="transportdisp_mainorderlist.do?lang=${user.usrLang}&action=doFind" > 	
					 									<img src="${imgSrcTomcat}" width="10px" height="10px" border="0">&nbsp;
					 									<font class="text14NavyBlue">${record.prTxt}</font>
			 										</a>
												</font>
											</c:if>
											<c:if test="${fn:contains(record.prog,'-eFaktura') }">
							 					<font class="text14">
									 				<a class="text14" href="efaktura_mainlist.do?action=doFind" > 	
					 									<img src="${imgSrcTomcat}" width="10px" height="10px" border="0">&nbsp;
					 									<font class="text14NavyBlue">${record.prTxt}</font>
			 										</a>
												</font>
											</c:if>
						 					<c:if test="${fn:contains(record.prog,'-SPORROPP') }">
							 					<font class="text14">
									 				<a class="text14" href="sporringoppdraggate.do?lang=${user.usrLang}" >
					 									<img src="${imgSrcTomcat}" width="10px" height="10px" border="0">&nbsp;
					 									<font class="text14NavyBlue">${record.prTxt}</font>
			 										</a>
												</font>
											</c:if>
						 					<c:if test="${fn:contains(record.prog,'-PRISKALK') }">
						 						<font class="text14">
									 				<a class="text14" href="fraktkalkulatorgate.do?lang=${user.usrLang}" >
					 									<img src="${imgSrcTomcat}" width="10px" height="10px" border="0">&nbsp;
														<font class="text14NavyBlue">${record.prTxt}</font>
			 										</a>
												</font>
						 					</c:if>
						 					<c:if test="${fn:contains(record.prog,'-TVINN') }">
				 								<a class="text14" href="tvinnsadgate.do" onMouseOver="showPop('infoTVINN');" onMouseOut="hidePop('infoTVINN');" >
				 									<img src="${imgSrcTomcat}" width="10px" height="10px" border="0">&nbsp;
	 												<font class="text14NavyBlue">${record.prTxt}</font>
		 										</a>
				 								<%-- ===========================================  --%>
									            	<%-- Here we have the info popup window TVINN --%>
									            	<%-- ===========================================  --%>
									            	<span style="position:absolute; left:720px; top:180px; width:390px; height:300px;" id="infoSKAT" class="popupPlain"  >
									           		<div align="center">
									           			<table>
									           				<tr>
																<td align="left" class="text12" ><b>TVINN</b> 
																</td>
															</tr>
															<tr class="text" height="10"><td></td></tr>
															<tr>
																<td align="center" >
																	<img src="resources/images/miniSKAT.png" border="0" width="350px"; height="210px">
																</td>
															</tr>
										           		</table>
													</div>
												</span>
											</c:if>
						 					<c:if test="${fn:contains(record.prog,'-SKAT') }">
				 								<a class="text14" href="skatgate.do" onMouseOver="showPop('infoSKAT');" onMouseOut="hidePop('infoSKAT');" >
				 									<img src="${imgSrcTomcat}" width="10px" height="10px" border="0">&nbsp;
	 												<font class="text14NavyBlue">${record.prTxt}</font>
		 										</a>
				 								<%-- ===========================================  --%>
									            	<%-- Here we have the info popup window SKAT --%>
									            	<%-- ===========================================  --%>
									            	<span style="position:absolute; left:720px; top:180px; width:390px; height:300px;" id="infoSKAT" class="popupPlain"  >
									           		<div align="center">
									           			<table>
									           				<tr>
																<td align="left" class="text12" ><b>SKAT</b>, Toldsystemet. 
																</td>
															</tr>
															<tr class="text" height="10"><td></td></tr>
															<tr>
																<td align="center" >
																	<img src="resources/images/miniSKAT.png" border="0" width="350px"; height="210px">
																</td>
															</tr>
										           		</table>
													</div>
												</span>
											</c:if>
				 							<c:if test="${fn:contains(record.prog,'-TDS') }">
				 								<a class="text14" href="tdsgate.do" onMouseOver="showPop('infoTDS');" onMouseOut="hidePop('infoTDS');" >
				 									<img src="${imgSrcTomcat}" width="10px" height="10px" border="0">&nbsp;
	 												<font class="text14NavyBlue">${record.prTxt}</font>
		 										</a>
				 								
				 								<%-- ===========================================  --%>
									            	<%-- Here we have the info popup window TDS --%>
									            	<%-- ===========================================  --%>
									            	<span style="position:absolute; left:720px; top:180px; width:390px; height:300px;" id="infoTDS" class="popupPlain"  >
									           		<div align="center">
									           			<table>
									           				<tr>
																<td align="left" class="text12" ><b>TDS</b>, Tulldatasystemet, är det datasystem som används i Sverige för klarering av import-och exportärenden och för att debitera tull och moms.
																TDS inkluderar också Transitering som kallas för NCTS.<br/><br/><b>NCTS</b> är en förkortning av New Computerised Transit System och är en datorisering av gemensam och gemenskapstransitering.
																</td>
															</tr>
															<tr class="text" height="10"><td></td></tr>
															<tr>
																<td align="center" >
																	<img src="resources/images/miniTDS.png" border="0" width="350px"; height="210px">
																</td>
															</tr>
										           		</table>
													</div>
												</span>
											</c:if>
											<c:if test="${fn:contains(record.prog,'-UFORTOPPD') }">
				 								<a class="text14" href="uoppdraggate.do?deepSubmit=do" onMouseOver="showPop('infoUOpp');" onMouseOut="hidePop('infoUOpp');" >
	 												<img src="${imgSrcTomcat}" width="10px" height="10px" border="0">&nbsp;
													<font class="text14NavyBlue">${record.prTxt}</font>
													</a>
				 								<%-- ======================================================  --%>
									            	<%-- Here we have the info popup window Ufortollede Oppdrag  --%>
									            	<%-- ======================================================  --%>
									            		<span style="position:absolute; left:720px; top:180px; width:390px; height:300px;" id="infoUOpp" class="popupPlain"  >
										           		<div align="center">
										           			<table>
										           				<tr>
																	<td align="left" class="text12" ><b>Ufortollede Oppdrag</b>, info here<br/><br/>
																	</td>
																</tr>
																<tr class="text" height="10"><td></td></tr>
																<tr>
																	<td align="center" >
																		<img src="resources/images/miniUoppdChart.jpg" border="0" width="350px"; height="210px">
																	</td>
																</tr>
											           		</table>
														</div>
													</span>
											</c:if>
											<c:if test="${fn:contains(record.prog,'-KVALITET') }">
				 								<a class="text14" href="sendingerlevtidgatefilter.do" >
	 												<img src="${imgSrcTomcat}" width="10px" height="10px" border="0">&nbsp;
													<font class="text14NavyBlue">${record.prTxt}</font>
												</a>
											</c:if>
											<c:if test="${fn:contains(record.prog,'-CUST_APP') }">
				 								<a class="text14" href="espedsgadmin.do" >
	 												<img src="${imgSrcTomcat}" width="10px" height="10px" border="0">&nbsp;
													<font class="text14NavyBlue">${record.prTxt}</font>
													</a>
											</c:if>
						 					</font>
					 						</li>
										</c:if>
						 			</c:forEach>	
						 			</ol>
						 			</td>
				 				</tr>
			 				</table>
	      				</td>
			        </tr>
			        <tr class="text" height="50"><td></td></tr>
			        <tr>
			 			<td class="text12" align="center" >		
			 				<table width="96%" align="center" class="dashboardFrameHeader" border="0" cellspacing="0" cellpadding="0">
						 		<tr height="20">
						 			<td class="text14White">
						 				<b>&nbsp;Andre moduler&nbsp;</b>
					 				</td>
				 				</tr>
			 				</table>
			 			</td>
			 		</tr>
			 		<tr >
			    		<td class="text12" align="center" >
			    			<table width="96%" align="center" class="dashboardFrame" border="0" cellspacing="0" cellpadding="0">
						 		<tr >
						 			<td align="left" height="60px" class="text14">
						 			<ul>
					 				<c:forEach items="${list}" var="record" varStatus="counter"> 
						 				<c:if test="${ !fn:contains(record.prog, 'TOMCAT') }">
						 					<c:set var="imgSrcNoneTomcat" scope="session" value="resources/images/bulletGrey.png"/>	
						 					<li style="line-height:20px; list-style-type: none;">
						 					<font class="text14">
											<c:choose>
					 							<c:when test="${not empty record.prog && fn:contains(record.prog,'UsrSpcName') }">
						 							<a class="text14" target="_blank" href="${record.progChunksUrl}" onclick="window.open(${record.progChunks}); return false" >
														<img src="${imgSrcNoneTomcat}"  width="10px" height="10px" border="0">&nbsp;
		 												<font class="text14SlateGray">${record.prTxt}</font>
		 											</a>	
					 							</c:when>
					 							<c:otherwise>
						 							<img src="${imgSrcNoneTomcat}" width="10px" height="10px" border="0">&nbsp;
		 											<font class="text14GrayInactiveLinkOnDashbord">${record.prTxt}</font>
					 							</c:otherwise>
				 							</c:choose>
				 							</font>
				 							</li>
			 							</c:if> 
		 							</c:forEach>	 
						 			</ul>	
					 				</td>
				 				</tr>
			 				</table>
	      				</td>
			        </tr>
			        <tr class="text" height="100"><td></td></tr>
			     </table> 
			</td>
			<td height="500" width="500" align="center" valign="top" > 
    			 <table width="96%" class="dashboardFrameMain" border="0" cellspacing="0" cellpadding="0">
    			 	<tr class="text" height="1"><td></td></tr>
    			 	<tr>
			 			<td class="text12" align="center" >		
			 				<table width="96%" align="center" class="dashboardFrameHeader" border="0" cellspacing="0" cellpadding="0">
						 		<tr height="20">
						 			<td class="text14White">
						 				<b>&nbsp;Info&nbsp;&nbsp;</b>
							    			<font class="text12LightGreen">${user.versionSpring}</font>
						    				<img style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" onClick="showPop('versionInfo');" > 
									        <span style="position:absolute; left:700px; top:105px; width:150px; height:50px;" id="versionInfo" class="popupWithInputText"  >
									           		<div class="text11" align="left">
									           			${user.versionEspedsg}</br></br>
									           			<button name="versionInformationButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('versionInfo');">Close</button> 
									           		</div>
									        </span>  
						 				 
					 				</td>
				 				</tr>
			 				</table>
			 			</td>
			 		</tr>
			 		
				 	<tr >
			    		<td class="text12" align="center" valign="top" >
			    			<table width="96%" align="center" class="dashboardFrame" border="0" cellspacing="0" cellpadding="0">
						 		<tr >
						 			<td height="300px" class="text14" valign="top" >
						 				&nbsp;&nbsp;
					 				</td>
				 				</tr>
				 				<tr class="text" height="50"><td></td></tr>
			 				</table>
	      				</td>
			        </tr>
   				 	<tr class="text" height="100"><td></td></tr>
			        
			     </table> 
			</td>
		</tr>
		<tr class="text" height="30"><td></td></tr>	
	
	