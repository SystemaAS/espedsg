<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTror.jsp" />
<!-- =====================end header ==========================-->
<SCRIPT type="text/javascript" src="resources/js/tror_mainorderlandimport_logging.js?ver=${user.versionEspedsg}"></SCRIPT>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">

<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
	<tr>
		<td>
		<%-- tab container component --%>
		<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
			<tr height="2"><td></td></tr>
			<tr height="25"> 
				<td width="13%" valign="bottom" class="tabDisabled" align="center" nowrap>
					<a class="text14" onClick="setBlockUI(this);" href="tror_mainorderlist.do?action=doFind" > 	
						<img style="vertical-align:middle;" src="resources/images/bulletGreen.png" width="6px" height="6px" border="0" alt="open orders">
						<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tror.orderlist.tab"/></font>
					</a>
				</td>
				<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
				<td width="13%" valign="bottom" class="tabDisabled" align="center" nowrap>
					<a class="text14" onClick="setBlockUI(this);" href="tror_mainorderlandimport.do?action=doFetch&heavd=${recordOrderTrorLandImport.heavd}&heopd=${recordOrderTrorLandImport.heopd}" > 	
						<img style="vertical-align:middle;" src="resources/images/lorry_green.png" width="18px" height="18px" border="0" alt="update">
						<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tror.order.tab"/>&nbsp;${recordOrderTrorLandImport.heavd}/${recordOrderTrorLandImport.heopd}</font>
					</a>
				</td>
				<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
				<td width="13%" valign="bottom" class="tabDisabled" align="center" nowrap>
					<a class="text14" onClick="setBlockUI(this);" href="tror_mainorderlandimport_invoice.do?action=doInit&heavd=${recordOrderTrorLandImport.heavd}&heopd=${recordOrderTrorLandImport.heopd}" >
						<img style="vertical-align: bottom" src="resources/images/invoice.png" width="16" hight="16" border="0" alt="show invoice">
						<font class="tabDisabledLink"><spring:message code="systema.tror.order.faktura.tab"/></font><font class="text12"></font>
					</a>
				</td>
				<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
				<td width="13%" valign="bottom" class="tabDisabled" align="center" nowrap>
					<a class="text14" onClick="setBlockUI(this);" href="editNotisblock.do?action=doFetch&subsys=tror_li&avd=${recordOrderTrorLandImport.heavd}&opd=${recordOrderTrorLandImport.heopd}&sign=${recordOrderTrorLandImport.hesg}" > 	
						<img style="vertical-align: bottom" src="resources/images/veiledning.png" width="16" hight="16" border="0" alt="show messages">
						<font class="tabDisabledLink"><spring:message code="systema.tror.order.notisblock.tab"/></font><font class="text12">&nbsp;</font>
					</a>
				</td>
				
				<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
				<td width="13%" valign="bottom" class="tab" align="center" nowrap>
					<img style="vertical-align: bottom" src="resources/images/log-icon.png" width="16" hight="16" border="0" alt="show log">
					<font class="tabLink"><spring:message code="systema.tror.order.logging.tab"/></font><font class="text12">&nbsp;</font>
					
				</td>
				
				<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
				<td width="13%" valign="bottom" class="tabDisabled" align="center" nowrap>
					<a class="text14" onClick="setBlockUI(this);" href="tror_mainorderlandimport_archive.do?avd=${recordOrderTrorLandImport.heavd}&sign=${recordOrderTrorLandImport.hesg}&opd=${recordOrderTrorLandImport.heopd}">
						<img style="vertical-align: bottom" src="resources/images/archive.png" width="16" hight="16" border="0" alt="show archive">
						<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tror.order.archive.tab"/></font>
						
					</a>
				</td>	
				<c:if test="${recordOrderTrorLandImport.hepk1 == 'J'}">
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="13%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a class="text14" onClick="setBlockUI(this);" href="tror_mainorderlandimport_freightbill.do?avd=${recordOrderTrorLandImport.heavd}&sign=${recordOrderTrorLandImport.hesg}&opd=${recordOrderTrorLandImport.heopd}">
							<img style="vertical-align: bottom" src="resources/images/fraktbrev.png" width="16" hight="16" border="0" alt="show freight doc">
							<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tror.order.fraktbrev.tab"/></font>
						</a>
					</td>
				</c:if>	
				<td width="50%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			</tr>
			<tr height="3"><td></td></tr>
		</table>
		</td>
	</tr>
	
	<%-- list component --%>
	<tr>
		<td>		
		<table width="100%" cellspacing="0" border="0" cellpadding="0">
	    	<%-- separator --%>
	        <tr height="2"><td></td></tr> 
			<tr>
				<td>
				<table width="100%" cellspacing="0" border="0" cellpadding="0">
					<thead>
					<tr class="tableHeaderField" height="20" >
						<th align="left" class="tableHeaderFieldFirst">&nbsp;<spring:message code="systema.tror.orders.logging.list.label.topicNr"/>&nbsp;</th>
						<th align="left" class="tableHeaderField">&nbsp;<spring:message code="systema.tror.orders.logging.list.label.interchangeId"/>&nbsp;</th>
						<th align="left" class="tableHeaderField">&nbsp;<spring:message code="systema.tror.orders.logging.list.label.messageNr"/>&nbsp;</th>
					    <th align="left" class="tableHeaderField">&nbsp;<spring:message code="systema.tror.orders.logging.list.label.sender"/>&nbsp;</th>
	                    <th align="left" class="tableHeaderField">&nbsp;<spring:message code="systema.tror.orders.logging.list.label.receiver"/>&nbsp;</th> 
	                    <th align="left" class="tableHeaderField">&nbsp;<spring:message code="systema.tror.orders.logging.list.label.t"/>&nbsp;</th> 
	                    <th align="left" class="tableHeaderField">&nbsp;<spring:message code="systema.tror.orders.logging.list.label.messageId"/>&nbsp;</th>
	                    <th align="left" class="tableHeaderField">&nbsp;<spring:message code="systema.tror.orders.logging.list.label.function"/>&nbsp;</th>
	                    <th align="left" class="tableHeaderField">&nbsp;<spring:message code="systema.tror.orders.logging.list.label.date"/>&nbsp;</th>
	                    <th align="left" class="tableHeaderField">&nbsp;<spring:message code="systema.tror.orders.logging.list.label.sequence"/>&nbsp;</th> 
	                    <th align="left" class="tableHeaderField">&nbsp;<spring:message code="systema.tror.orders.logging.list.label.ver"/>&nbsp;</th> 
	                    <th align="left" class="tableHeaderField">&nbsp;<spring:message code="systema.tror.orders.logging.list.label.eksp"/>&nbsp;</th>
	                    <th align="left" class="tableHeaderField">&nbsp;<spring:message code="systema.tror.orders.logging.list.label.s"/>&nbsp;</th>
	                    <th align="left" class="tableHeaderField">&nbsp;<spring:message code="systema.tror.orders.logging.list.label.sent"/>&nbsp;</th>
	                    <th align="left" class="tableHeaderField">&nbsp;<spring:message code="systema.tror.orders.logging.list.label.sentReceive"/>&nbsp;</th>
	                    <th align="left" class="tableHeaderField">&nbsp;<spring:message code="systema.tror.orders.logging.list.label.text"/>&nbsp;</th>

	               </tr>
	               </thead>
	               <tbody>     
		           	<c:forEach items="${list}" var="record" varStatus="counter">    
		               <c:choose>           
		                   <c:when test="${record.msr == 'R'}">
		                       <tr class="tableRow" style="background-color:#EEEEEE;" height="25" >
		                   </c:when>
		                   <c:otherwise>   
		                       <tr class="tableOddRow" height="25" >
		                   </c:otherwise>
		               </c:choose>
		               
		               <td class="tableCellFirst" <c:if test="${record.msr == 'R'}">style="color:#9F6000;"</c:if> >&nbsp;${record.mtdn}&nbsp;&nbsp;<font class="text8">[${model.sign}]</font></td>
		               <td class="tableCell">&nbsp;
		               		<a <c:if test="${record.msr == 'R'}">style="color:#9F6000;"</c:if> href="ediftplog.do?sssn=${record.msn}&ftplev=EDISS" target="_new" onClick="window.open(this.href,'targetWindow','top=200px,left=600px,height=800px,width=700px,scrollbars=no,status=no,location=no'); return false;">
		               			<img src="resources/images/bebullet.gif" border="0" alt="Vis Ftp log" >
		               			&nbsp;${record.msn}
		               		</a>
		               	</td>
		               <td class="tableCell">&nbsp;
		               		<a <c:if test="${record.msr == 'R'}">style="color:#9F6000;"</c:if>  href="tror_mainorderlandimport_renderEdifact.do?fp=${record.wurl}" target="_new" >
			               		<img src="resources/images/list.gif" border="0" width="12px" height="12px" alt="Vis Edifact" >
			               		&nbsp;${record.mmn}
	               		   	</a>
		               </td>
		               <td class="tableCell" <c:if test="${record.msr == 'R'}">style="color:#9F6000;"</c:if> >&nbsp;${record.m0004}</td>
		               <td class="tableCell" <c:if test="${record.msr == 'R'}">style="color:#9F6000;"</c:if> >&nbsp;${record.m0010}</td>
		               <td class="tableCell" <c:if test="${record.msr == 'R'}">style="color:#9F6000;"</c:if> >&nbsp;${record.m0035}</td>
		               <td class="tableCell" <c:if test="${record.msr == 'R'}">style="color:#9F6000;"</c:if> >&nbsp;${record.m0065}</td>
		               <td class="tableCell" <c:if test="${record.msr == 'R'}">style="color:#9F6000;"</c:if> >&nbsp;${record.m1n07}</td>
		               <td class="tableCell" <c:if test="${record.msr == 'R'}">style="color:#9F6000;"</c:if> >&nbsp;${record.m0068a}</td>
		               <td class="tableCell" <c:if test="${record.msr == 'R'}">style="color:#9F6000;"</c:if> >&nbsp;${record.m0068b}</td>
		               <td class="tableCell" <c:if test="${record.msr == 'R'}">style="color:#9F6000;"</c:if> >&nbsp;${record.m0068c}</td>
		               <td class="tableCell" <c:if test="${record.msr == 'R'}">style="color:#9F6000;"</c:if> >&nbsp;${record.m3039e}</td>
		               <td class="tableCell" <c:if test="${record.msr == 'R'}">style="color:#9F6000;"</c:if> >&nbsp;${record.mst}</td>
		               <td class="tableCell" <c:if test="${record.msr == 'R'}">style="color:#9F6000;"</c:if> >&nbsp;${record.mdt}-${record.mtm}</td>
		               <td class="tableCell" <c:if test="${record.msr == 'R'}">style="color:#9F6000;"</c:if> >&nbsp;${record.msr}</td>
		               <td class="tableCell" <c:if test="${record.msr == 'R'}">style="color:#9F6000;"</c:if> >&nbsp;${record.wtxt}
		               		<c:if test="${record.wmore == 'X'}">
		               			&nbsp;&nbsp;
		               			<a href="tror_mainorderlandimport_renderLargeText.do?fmn=${record.mmn}" target="_blank" onClick="window.open(this.href,'targetWindow','toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=700,height=500'); return false;">
		               				<img valign="bottom" src="resources/images/largeTextContent.png" width="16" hight="16" border="0" alt="large text content">
		               			</a>
							</c:if>
		               </td>
		            </tr> 
		            </c:forEach>
		            </tbody>
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

