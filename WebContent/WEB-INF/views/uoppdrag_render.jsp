<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerUoppdrag.jsp" />
<!-- =====================end header ==========================-->

<table width="100%" class="text11" cellspacing="0" border="0" cellpadding="0">
	<tr>
		<td>
		<%-- tab container component --%>
		<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
			<tr height="2"><td></td></tr>
			<tr height="25"> 
				<td width="20%" valign="bottom" class="tabDisabled" align="center">
					<a style="display:block;" href="uoppdraggate.do?action=doBack">
						<font class="tabDisabledLink">&nbsp;<spring:message code="systema.overview.uoppdrag.diagram.tab"/></font>
						<img valign="bottom" src="resources/images/barChart.png" width="16" hight="16" border="0" alt="bar graph">
					</a>
				</td>
				<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
				
				<td width="20%" valign="bottom" class="tabDisabled" align="center">
					<c:choose>
					<c:when test="${not empty model.listType}">
						<a style="display:block;" href="uoppdrag.do?uoType=${model.listType}&llim=${model.llim}&ulim=${model.ulim}">
							<font class="tabDisabledLink">&nbsp;<spring:message code="systema.overview.uoppdrag.intervalprefix.tab"/></font>&nbsp;&nbsp;<font class="text12Bold">[${model.listInterval}]</font>
							&nbsp;&nbsp;&nbsp;
							<input class="inputFormSubmitUoppNeutralR" type="submit" name="totalButton" value='${model.listSize}'/>
						</a>
					</c:when>
					<c:otherwise>
						<a style="display:block;" href="uoppdrag.do?">
							<font class="tabDisabledLink">&nbsp;<spring:message code="systema.overview.uoppdrag.intervalAll.tab"/>&nbsp;<b>${model.listSize}</b></font>
						</a>
					</c:otherwise>
					</c:choose>
				</td>
				<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
				<td width="20%" valign="bottom" class="tab" align="center" nowrap>
					<font class="tabLink">&nbsp;<spring:message code="systema.overview.uoppdrag.render.tab"/></font>
					&nbsp;<b>${model.container.avd}/${model.container.opd}</b>
					&nbsp;
					<img style="vertical-align: bottom" src="resources/images/app.png" width="14" hight="14" border="0" alt="show archive">
					
				</td>
				
				<td width="40%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			</tr>
			
		</table>
		</td>
	</tr>
	
	
	<tr>
	 	<td>
		<%-- --------------------------- --%>	
	 	<%-- tab area container PRIMARY  --%>
		<%-- --------------------------- --%>
		<table width="100%" class="tabThinBorderWhite" border="0" cellspacing="0" cellpadding="0">
	 		<tr height="25"><td colspan="2">&nbsp;</td></tr>
	 		
			<%-- Important header fields --%>		
			<tr valign="bottom">
				<td colspan="2" align="left" class="text12MediumBlue">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					Avd:&nbsp;<b>${model.container.avd}</b>&nbsp;&nbsp;Oppdrag:&nbsp;<b>${model.container.opd}</b>
					&nbsp;&nbsp;Sign:&nbsp;<b>${model.container.sign}</b>&nbsp;&nbsp;Pos:&nbsp;<b>${model.container.pos}</b>
					&nbsp;&nbsp;Turnr:&nbsp;<b>${model.container.tur}</b>
					&nbsp;&nbsp;Godsnr:&nbsp;<b>${model.container.godsNr}</b>
				</td>
			</tr>
			<tr height="5"><td colspan="2"></td></tr>
	 		
			<tr valign="top">
				<%--LEFT CELL --%>
				<td width="50%">
	 				<%-- SELLER --%>
	 				<table width="100%" align="left" border="0" cellspacing="0" cellpadding="0">
				 		<tr >
				 			<td >
				 			<table width="90%" align="center" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
				 				<tr height="15">
					 				<td class="text12White">&nbsp;Avsender</td>
				 				</tr>
				 			</table>	
			 				</td>
		 				</tr>
		 				<tr>
		 					<td>
		 					<table width="90%" align="center" class="formFrame" border="0" cellspacing="0" cellpadding="0">
		 						<tr>
					 				<td class="text12">&nbsp;Kundenr.</td>
					 				<td class="text12">&nbsp;Navn</td>
				 				</tr>
				 				<tr>
				 					<td class="text12"><input readonly type="text" class="inputText" name="avsKnr" id="avsKnr" size="20" value='${model.container.avsKnr}'></td>
			 						<td class="text12"><input readonly type="text" class="inputText" name="avsNavn" id="avsNavn" size="20" value='${model.container.avsNavn}'></td>
	 							</tr>
	 							<tr>
					 				<td class="text12">&nbsp;Adresse</td>
					 				<td class="text12">&nbsp;Adresse 2</td>
				 				</tr>
				 				<tr>
				 					<td class="text12"><input readonly type="text" class="inputText" name="avsAdr1" id="avsAdr1" size="20" value='${model.container.avsAdr1}'></td>
			 						<td class="text12"><input readonly type="text" class="inputText" name="avsAdr2" id="avsAdr2" size="20" value='${model.container.avsAdr2}'></td>
	 							</tr>
	 							<tr>
					 				<td class="text12">&nbsp;Adresse 3</td>
					 				<td class="text12">&nbsp;</td>
				 				</tr>
				 				<tr>
			 						<td class="text12"><input readonly type="text" class="inputText" name="avsAdr3" id="avsAdr3" size="20" value='${model.container.avsAdr3}'></td>
				 					<td class="text12">&nbsp;</td>
	 							</tr>
	 							<tr height="10"><td colspan="2"></td></tr>
		 					</table>
		 					</td>
		 				</tr>
		 				<tr height="10"><td colspan="2"></td></tr>
				 		<tr >
				 			<td >
				 			<table width="90%" align="center" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
				 				<tr height="15">
					 				<td class="text12White">&nbsp;Mottaker</td>
				 				</tr>
				 			</table>	
			 				</td>
		 				</tr>
		 				<tr>
		 					<td>
		 					<table width="90%" align="center" class="formFrame" border="0" cellspacing="0" cellpadding="0">
		 						<tr>
		 							<td class="text12">&nbsp;Kundenr.</td>
					 				<td class="text12">&nbsp;Navn</td>
				 				</tr>
				 				<tr>
			 						<td class="text12"><input readonly type="text" class="inputText" name="motKnr" id="motKnr" size="20" value='${model.container.motKnr}'></td>
			 						<td class="text12"><input readonly type="text" class="inputText" name="motNavn" id="motNavn" size="20" value='${model.container.motNavn}'></td>
	 							</tr>
	 							<tr>
					 				<td class="text12">&nbsp;Adresse</td>
					 				<td class="text12">&nbsp;Adresse 2</td>
				 				</tr>
				 				<tr>
				 					<td class="text12"><input readonly type="text" class="inputText" name="motAdr1" id="motAdr1" size="20" value='${model.container.motAdr1}'></td>
			 						<td class="text12"><input readonly type="text" class="inputText" name="motAdr2" id="motAdr2" size="20" value='${model.container.motAdr2}'></td>
	 							</tr>
	 							<tr>
					 				<td class="text12">&nbsp;Adresse 3</td>
					 				<td class="text12">&nbsp;</td>
					 				
				 				</tr>
				 				<tr>
				 					<td class="text12"><input readonly type="text" class="inputText" name="motAdr3" id="motAdr3" size="20" value='${model.container.motAdr3}'></td>
				 					<td class="text12">&nbsp;</td>
			 						
	 							</tr>
	 							<tr height="10"><td colspan="2"></td></tr>
				                
		 					</table>
		 					</td>
		 				</tr>
		 				<tr height="5"><td colspan="2"></td></tr>
						<tr> 
							<td>
								<table width="90%" align="left" border="0" cellspacing="0" cellpadding="0">
			 					<tr>
									<td class="text12">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						              	<a href="uoppdrag_logging.do?avd=${model.container.avd}&opd=${model.container.opd}&sign=${model.container.sign}&uoType=${model.listType}&llim=${model.llim}&ulim=${model.ulim}&antHfakt=${model.antHfakt}">
						              		<img valign="bottom" id="imgTrackTraceLog" width="18" hight="18" src="resources/images/log-icon.gif" border="0" alt="logging">
						              		&nbsp;<font style="text-decoration: underline;" >Til hend.logg</font>
						              	</a>
						              	<c:if test="${not empty model.antHfakt}">
							              	&nbsp;&nbsp;&nbsp;
							              	<a href="uoppdrag_archive.do?avd=${model.container.avd}&opd=${model.container.opd}&sign=${model.container.sign}&uoType=${model.listType}&llim=${model.llim}&ulim=${model.ulim}&antHfakt=${model.antHfakt}">
								              	<img valign="bottom" id="imgArchive" width="18" hight="18" src="resources/images/archive.png" border="0" alt="Handl.fakt.">
							              	<font style="text-decoration: underline;">
							              		<spring:message code="systema.overview.uoppdrag.archive.tab"/>&nbsp;
							              		(${model.antHfakt})
							              	</font>
							              	</a>
						              	</c:if>
						              	<c:if test="${not empty model.status}">
							              	&nbsp;&nbsp;&nbsp;
							              	<a href="uoppdrag_tvinnlog.do?avd=${model.container.avd}&opd=${model.container.opd}&sign=${model.container.sign}&uoType=${model.listType}&llim=${model.llim}&ulim=${model.ulim}&antHfakt=${model.antHfakt}&status=${model.status}">
								              	<img valign="bottom" id="imgTvinnLog" width="20" hight="20" src="resources/images/ediLog.png" border="0" alt="Tvinn-log">
							              	<font style="text-decoration: underline;">
							              		<spring:message code="systema.overview.uoppdrag.tvinnlog.tab"/>&nbsp;
							              		<%-- (${model.status})  --%>
							              	</font>
							              	</a>
						              	</c:if>
						              	
					                </td>
				                </tr>
				                </table>
			                </td>
		                </tr>
		 				
		 				
	 				</table>
				</td>
				<%--RIGHT CELL --%>
				<td width="50%">
	 				<table width="100%" align="left" border="0" cellspacing="1" cellpadding="0">
	 					<tr>
		 					<td >
							<table width="90%" cellspacing="0" border="0" cellpadding="0">
								<tr>
					 				<td class="text12">&nbsp;Aref:</td>
					 				<td class="text12">&nbsp;Godsmerke:</td>
				 				</tr>
								<tr>
					 				<td class="text12"><input readonly type="text" class="inputText" name="opdRef" id="opdRef" size="20" value='${model.container.opdRef}'></td>
					 				<td class="text12"><input readonly type="text" class="inputText" name="godsmerke" id="godsmerke" size="20" value='${model.container.godsmerke}'></td>
				 				</tr>
			 				</table>
			 				</td>
	 					</tr>
	 					<tr height="3"><td ></td></tr>
	 					<tr>
		 					<td >
							<table width="90%" cellspacing="0" border="0" cellpadding="0">
								<tr>
					 				<td class="text12">&nbsp;Merknad:</td>
				 				</tr>
								<tr>
					 				<td class="text12">
					 					<textarea rows="3" cols="52">${model.container.merknadAll}</textarea>
					 				</td>
				 				</tr>
			 				</table>
			 				</td>
	 					</tr>
	 					<tr height="5"><td colspan="2"></td></tr>
				 		<tr >
				 			<td >
							<table width="90%" cellspacing="0" border="0" cellpadding="0">
								<tr class="tableHeaderField" height="20" valign="left">
				                    <td class="tableHeaderFieldFirst" align="right">&nbsp;Antall&nbsp;</td>   
				                    <td class="tableHeaderField" nowrap>&nbsp;Vareslag</td>
				                    <td class="tableHeaderField" align="right">&nbsp;Vekt&nbsp;</td>
				                    <td class="tableHeaderField" align="right">&nbsp;M3&nbsp;</td>
				                    <td class="tableHeaderField" align="right">&nbsp;LM&nbsp;</td>
				                </tr>     
  				                    <tr class="tableRow" height="20" >
					               <td class="tableCellFirst" width="5%"  align="right">&nbsp;${model.container.antall}&nbsp;</td>
					               <td class="tableCell" >&nbsp;${model.container.vareslag}</td>
					               <td class="tableCell" align="right">&nbsp;${model.container.vekt}&nbsp;</td>
					               <td class="tableCell" align="right">&nbsp;${model.container.m3}&nbsp;</td>
					               <td class="tableCell" align="right">&nbsp;${model.container.lm}&nbsp;</td>
					            </tr> 
				            </table>
			 				</td>
		 				</tr>
	 				</table>
				</td>
			</tr>
			<tr height="20"><td colspan="2"></td></tr>


			<%-- put here some footer content of your preference... --%>


			<%-- Space on content area before bottom --%>
			<tr height="50"><td colspan="2"></td></tr>
			
		</table>
    </td>
    </tr>

</table>	
		
<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->

