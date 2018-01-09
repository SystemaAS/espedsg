<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTror.jsp" />
<!-- =====================end header ==========================-->
<SCRIPT type="text/javascript" src="resources/js/tror_mainorderland_archive.js?ver=${user.versionEspedsg}"></SCRIPT>
<SCRIPT type="text/javascript" src="resources/js/trorFkeys_landimport.js?ver=${user.versionEspedsg}"></SCRIPT>

<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">

<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
	<tr>
		<td>
		<%-- tab container component --%>
		<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
			<%-- This part right here allows for the dynamic allocation of a JSP depending on whether it is IMPORT or EXPORT --%>
			<c:choose>
				<c:when test="${recordOrderTrorLand.heur == 'A'}">
					<c:set var = "tabLinkJsp" scope = "request" value = "mainorderlandimport"/>	
				</c:when>
				<c:otherwise>
					<c:set var = "tabLinkJsp" scope = "request" value = "mainorderlandexport"/>
				</c:otherwise>
			</c:choose>
		
			<tr height="2"><td></td></tr>
			<tr height="25"> 
				<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
					<a class="text14" onClick="setBlockUI(this);" href="tror_mainorderlist.do?action=doFind&sign=${recordOrderTrorLand.hesg}" > 	
						<img style="vertical-align:middle;" src="resources/images/bulletGreen.png" width="6px" height="6px" border="0" alt="open orders">
						<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tror.orderlist.tab"/></font>&nbsp;<font class="text10Orange">F2</font>
					</a>
				</td>
				<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
				<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
					<a class="text14" onClick="setBlockUI(this);" href="tror_<c:out value="${tabLinkJsp}"/>.do?action=doFetch&heavd=${recordOrderTrorLand.heavd}&heopd=${recordOrderTrorLand.heopd}" > 	
						<c:choose>
							<c:when test="${recordOrderTrorLand.heur == 'A'}">
								<img style="vertical-align:middle;" src="resources/images/lorry_green.png" width="18px" height="18px" border="0" alt="create new">
							</c:when>
							<c:otherwise>
								<img style="vertical-align:middle;" src="resources/images/lorry_blue.png" width="18px" height="18px" border="0" alt="create new">
							</c:otherwise>
						</c:choose>
						<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tror.order.tab"/>&nbsp;${recordOrderTrorLand.heavd}/${recordOrderTrorLand.heopd}</font>&nbsp;<font class="text10Orange">F4</font>
					</a>
				</td>
				<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
				<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
					<a class="text14" onClick="setBlockUI(this);" href="tror_<c:out value="${tabLinkJsp}"/>_invoice.do?action=doInit&heavd=${recordOrderTrorLand.heavd}&heopd=${recordOrderTrorLand.heopd}" >
						<img style="vertical-align: bottom" src="resources/images/invoice.png" width="16" height="16" border="0" alt="show invoice">
						<font class="tabDisabledLink"><spring:message code="systema.tror.order.faktura.tab"/></font><font class="text12"></font>&nbsp;<font class="text10Orange">F9</font>
					</a>
				</td>
				<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
				<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
					<a class="text14" onClick="setBlockUI(this);" href="editNotisblock.do?action=doFetch&subsys=${subSystem}&avd=${recordOrderTrorLand.heavd}&opd=${recordOrderTrorLand.heopd}&sign=${recordOrderTrorLand.hesg}" > 	
						<img style="vertical-align: bottom" src="resources/images/veiledning.png" width="16" height="16" border="0" alt="show messages">
						<font class="tabDisabledLink"><spring:message code="systema.tror.order.notisblock.tab"/></font><font class="text12">&nbsp;</font><font class="text10Orange">F10</font>
					</a>
				</td>
				
				<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
				<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
					<a class="text14" onClick="setBlockUI(this);" href="tror_mainorderland_frisokvei.do?action=doFetch&avd=${recordOrderTrorLand.heavd}&sign=${recordOrderTrorLand.hesg}&opd=${recordOrderTrorLand.heopd}">
						<img style="vertical-align: bottom" src="resources/images/lightbulb.png" width="14" height="14" border="0" alt="show log">
						<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tror.order.frisokvei.tab"/></font>&nbsp;<font class="text10Orange">F7</font>
					</a>
				</td>
				
				<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
				<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
					<a class="text14" onClick="setBlockUI(this);" href="tror_mainorderland_budget.do?avd=${recordOrderTrorLand.heavd}&sign=${recordOrderTrorLand.hesg}&opd=${recordOrderTrorLand.heopd}">
						<img style="vertical-align: bottom" src="resources/images/budget.png" width="16" height="16" border="0" alt="show budget">
						<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tror.order.budget.tab"/></font>&nbsp;<font class="text10Orange">F8</font>
					</a>
				</td>
				<c:if test="${recordOrderTrorLand.hepk1 == 'J' || recordOrderTrorLand.hepk1 == 'P'}">
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a class="text14" onClick="setBlockUI(this);" href="tror_mainorderland_freightbill_gate.do?dfavd=${recordOrderTrorLand.heavd}&sign=${recordOrderTrorLand.hesg}&dfopd=${recordOrderTrorLand.heopd}">
							<img style="vertical-align: bottom" src="resources/images/fraktbrev.png" width="16" height="16" border="0" alt="show freight doc">
							<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tror.order.fraktbrev.tab"/></font>&nbsp;<font class="text10Orange">F3</font>
						</a>
					</td>
				</c:if>	
				<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
				<td width="12%" valign="bottom" class="tab" align="center" nowrap>
					<img style="vertical-align: bottom" src="resources/images/sort_down.png" width="10" height="10" border="0" alt="show more">
					<font class="tabLink">&nbsp;<spring:message code="systema.tror.order.more.tab"/></font>						
				</td>
				<td width="50%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			</tr>
		</table>
		</td>
	</tr>
	
	<%-- list component --%>
	<tr>
		<td>		
		<table id="wrapperTable" class="tabThinBorderWhite" width="100%" cellspacing="0" border="0" cellpadding="0">
			<%--for F-Keys shortcuts. Used only in trorFkeys_...js --%>
			<input type="hidden" name="fkeysavd" id="fkeysavd" value='${recordOrderTrorLand.heavd}'>
			<input type="hidden" name="fkeysopd" id="fkeysopd" value='${recordOrderTrorLand.heopd}'>
			<input type="hidden" name="fkyessign" id="fkyessign" value='${recordOrderTrorLand.hesg}'>
			<c:choose>
				<c:when test="${recordOrderTrorLand.heur == 'A'}">
					<input type="hidden" name="fkyessubsys" id="fkyessubsys" value='mainorderlandimport'>
				</c:when>
				<c:otherwise>
					<input type="hidden" name="fkyessubsys" id="fkyessubsys" value='mainorderlandexport'>
				</c:otherwise>
			</c:choose>		
	    	<%-- separator --%>
	        <tr height="20"><td></td></tr> 
	        
	        <tr> <!-- Second tab row... -->
 	   	 		<td align="center" width="99%">
 					<table class="formFrameHeaderTransparent" width="99%" cellspacing="0" border="0" cellpadding="0">
						<tr height="20"> 
							<td width="12%" valign="bottom" class="tabDisabledSub" align="center" nowrap>
								<a id="alinkMoreTrackTrace" onClick="setBlockUI(this);" href="tror_mainorderland_ttrace.do?avd=${recordOrderTrorLand.heavd}&sign=${recordOrderTrorLand.hesg}&opd=${recordOrderTrorLand.heopd}">
									<font class="tabDisabledLinkMinor">&nbsp;<spring:message code="systema.tror.order.more.tab.tracktrace.subtab"/></font>&nbsp;						
								</a>
							</td>
							<td width="12%" valign="bottom" class="tabDisabledSub" align="center" nowrap>
								<a id="alinkMoreTrackTraceGeneral" onClick="setBlockUI(this);" href="tror_mainorderland_ttrace_general.do?ttavd=${recordOrderTrorLand.heavd}&ttopd=${recordOrderTrorLand.heopd}">
									<font class="tabDisabledLinkMinor">&nbsp;<spring:message code="systema.tror.order.more.tab.tracktracegrl.subtab"/></font>&nbsp;					
								</a>
							</td>
							<td width="12%" valign="bottom" class="tabDisabledSub" align="center" nowrap>
								<a id="alinkEdiLogging" onClick="setBlockUI(this);" href="tror_mainorderland_logging.do?avd=${recordOrderTrorLand.heavd}&sign=${recordOrderTrorLand.hesg}&opd=${recordOrderTrorLand.heopd}">
									<font class="tabDisabledLinkMinor">&nbsp;<spring:message code="systema.tror.order.more.tab.edilog.subtab"/></font>&nbsp;					
								</a>
							</td>
							<td width="12%" valign="bottom" class="tabSub" align="center" nowrap>
								<font class="tabLinkMinor">&nbsp;<spring:message code="systema.tror.order.more.tab.archive.subtab"/></font>&nbsp;					
							</td>
							
						 	<td width="80%" class="tabFantomSpace" align="center" nowrap></td>
						</tr>
						
					</table>
				</td>
 	   	 	</tr> <!-- End second tab row -->
   	 		
   	 		<tr>
			<td align="center" width="99%">
				<table class="tabThinBorderWhite" width="99%" cellspacing="0" border="0">
				<tr height="20px"><td ></td></tr>
				<tr>
					<td align="center" width="99%">
					<table width="99%" cellspacing="0" border="0" cellpadding="0">
						
						<thead>
						<tr class="tableHeaderField" height="20" >
							<th align="left" class="tableHeaderFieldFirst">&nbsp;<spring:message code="systema.tror.orders.archive.list.label.topicNr"/>&nbsp;</th>
		                    <th align="left" class="tableHeaderField">&nbsp;<spring:message code="systema.tror.orders.archive.list.label.docType"/>&nbsp;</th> 
		                    <th align="left" class="tableHeaderField">&nbsp;<spring:message code="systema.tror.orders.archive.list.label.subject"/>&nbsp;</th> 
		                    <th align="left" class="tableHeaderField">&nbsp;<spring:message code="systema.tror.orders.archive.list.label.date"/>&nbsp;</th>
		                    <th align="left" class="tableHeaderField">&nbsp;<spring:message code="systema.tror.orders.archive.list.label.time"/>&nbsp;</th>
		                    <th align="left" class="tableHeaderField">&nbsp;<spring:message code="systema.tror.orders.archive.list.label.additionalInfo"/>&nbsp;</th>
		                    <th align="left" class="tableHeaderField">&nbsp;<spring:message code="systema.tror.orders.archive.list.label.document"/>&nbsp;</th> 
		               </tr>
		               </thead>
		               <tbody>     
			           		<c:if test="${not empty list}">    
					           	<c:forEach items="${list}" var="record" varStatus="counter">    
					               <c:choose>           
					                   <c:when test="${counter.count%2==0}">
					                       <tr class="tableRow" height="25" >
					                   </c:when>
					                   <c:otherwise>   
					                       <tr class="tableOddRow" height="25" >
					                   </c:otherwise>
					               </c:choose>
					               <td class="tableCellFirst" >&nbsp;${model.opd}&nbsp;&nbsp;<font class="text8">[${model.sign}]</font></td>
					               <td class="tableCell">&nbsp;${record.docType}</td>
					               <td class="tableCell" >&nbsp;${record.subject}</td>
					               <td class="tableCell" >&nbsp;${record.createDate}</td>
					               <td class="tableCell" >&nbsp;${record.createTime}</td>
					               <td class="tableCell" >&nbsp;${record.additionalInfo}</td>
					               <%-- <td class="tableCell" >&nbsp;${record.url}</td> --%> 
					               <td class="tableCell" >&nbsp;
					               		<a href="tror_mainorderland_renderArchive.do?fp=${record.url}" target="_new" >
						               		<img src="resources/images/pdf.png" border="0" width="16px" height="16px" alt="Visa arkivdokument" >
						               		${record.documentName}
					               		</a>
					               </td>
					            </tr> 
					            </c:forEach>
				            </c:if>
			            </tbody>
			            
		            </table>
				</td>	
				</tr>
				
			</table>
		</td>
		</tr>
		<tr height="10px"><td ></td></tr>	
		</table>
		</td>
	</tr>
	
</table>	
		
<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->

