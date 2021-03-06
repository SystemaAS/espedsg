<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerUoppdrag.jsp" />
<!-- =====================end header ==========================-->

<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
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
							<font class="tabDisabledLink">&nbsp;<spring:message code="systema.overview.uoppdrag.intervalprefix.tab"/>&nbsp;<b>${model.listSize}</b></font>
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
				<td width="20%" valign="bottom" class="tabDisabled" align="center" nowrap>
					<a style="display:block;" href="uoppdrag_render.do?avd=${model.avd}&opd=${model.opd}&uoType=${model.listType}&llim=${model.llim}&ulim=${model.ulim}&lsize=${model.listSize}&antHfakt=${model.antHfakt}">						
						<font class="tabDisabledLink">&nbsp;<spring:message code="systema.overview.uoppdrag.render.tab"/></font>
						&nbsp;${model.avd}/${model.opd}
						&nbsp;
						<img style="vertical-align: bottom" src="resources/images/app.png" width="14" hight="14" border="0" alt="show archive">
						
					</a>
				</td>
				
				<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
				<td width="20%" valign="bottom" class="tab" align="center" nowrap>
					<font class="tabLink">&nbsp;<spring:message code="systema.overview.uoppdrag.archive.tab"/></font>
					<img style="vertical-align: bottom" src="resources/images/archive.png" width="16" hight="16" border="0" alt="show archive">
				</td>
				
				<td width="40%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
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
					<tr class="tableHeaderField" height="20" valign="left">
	                    <td class="tableHeaderField">&nbsp;<spring:message code="systema.overview.uoppdrag.archive.list.label.docType"/>&nbsp;</td> 
	                    <td class="tableHeaderField">&nbsp;<spring:message code="systema.overview.uoppdrag.archive.list.label.subject"/>&nbsp;</td> 
	                    <td class="tableHeaderField" align="right">&nbsp;<spring:message code="systema.overview.uoppdrag.archive.list.label.date"/>&nbsp;</td>
	                    <td class="tableHeaderField" align="right">&nbsp;<spring:message code="systema.overview.uoppdrag.archive.list.label.time"/>&nbsp;</td>
	                    <td class="tableHeaderField">&nbsp;<spring:message code="systema.overview.uoppdrag.archive.list.label.additionalInfo"/>&nbsp;</td>
	                    <%-- <td class="tableHeaderField">&nbsp;<spring:message code="systema.tds.export.archive.list.label.url"/>&nbsp;</td>  --%>
	                    <td class="tableHeaderField">&nbsp;<spring:message code="systema.overview.uoppdrag.archive.list.label.dokumentName"/></td> 
	               </tr>     
		           	<c:forEach items="${list}" var="record" varStatus="counter">    
		               <c:choose>           
		                   <c:when test="${counter.count%2==0}">
		                       <tr class="tableRow" height="20" >
		                   </c:when>
		                   <c:otherwise>   
		                       <tr class="tableOddRow" height="20" >
		                   </c:otherwise>
		               </c:choose>
		               <td class="tableCellFirst">&nbsp;${record.docType}</td>
		               <td class="tableCell" >&nbsp;${record.subject}</td>
		               <td class="tableCell" align="right" >&nbsp;${record.createDate}&nbsp;</td>
		               <td class="tableCell" align="right" >&nbsp;${record.createTime}&nbsp;</td>
		               <td class="tableCell" >&nbsp;${record.additionalInfo}</td>
		               <%-- <td class="tableCell" >&nbsp;${record.url}</td>  --%>
		               <td class="tableCell" >&nbsp;
		               		<a href="uoppdrag_renderArchive.do?fp=${record.url}" target="_new" >
			               		<img src="resources/images/pdf.png" border="0" width="16px" height="16px" alt="Visa arkivdokument" >
			               		${record.documentName}
		               		</a>
		               </td>
		            </tr> 
		            </c:forEach>
		            
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

