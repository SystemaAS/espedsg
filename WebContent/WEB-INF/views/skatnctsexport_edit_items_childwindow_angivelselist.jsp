<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header =====================================-->
<jsp:include page="/WEB-INF/views/headerSkatChildWindows.jsp" />
<!-- =====================end header ====================================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
	specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/skatnctsexport_edit_items_childwindow_angivelselist.js?ver=${user.versionEspedsg}"></SCRIPT>
	
	<table width="90%" height="500px" class="tableBorderWithRoundCorners3D_RoundOnlyOnBottom" cellspacing="0" border="0" cellpadding="0">
		<tr>
			<td colspan="3" class="text14Bold">&nbsp;Søk Angivelse</td>
		</tr>
		<tr>
		<td valign="top">
		
		  		<%-- this container table is necessary in order to separate the datatables element and the frame above, otherwise
			 	the cosmetic frame will not follow the whole datatable grid including the search field... --%>
				<table id="containerdatatableTable" cellspacing="2" align="left" width="100%" >
					<tr>
					<td>
						<table>
						<form name="skatNctsExportAngivelseForm" id="skatNctsExportAngivelseForm" action="skatnctsexport_edit_items_childwindow_angivelselist.do?action=doInit" method="post">
						<tr>
							<td class="text11">&nbsp;Angivelse</td>
							<td class="text11">&nbsp;<input type="text" class="inputText" name="vkod" id="vkod" size="10" maxlength="10" value="${Xmodel.vkod}"></td>
							<%-- 
							<td class="text11">&nbsp;Beskrivning</td>
							<td class="text11">&nbsp;<input type="text" class="inputText" name="tekst" id="tekst" size="30" maxlength="50" value="${model.tekst}"></td>
							--%>
							<td class="text11">&nbsp;</td>
	           				<td align="right">&nbsp;<input class="inputFormSubmit" type="submit" name="submit" value='<spring:message code="search.label"/>'>
		           		</tr>
		           		
		           		</table>
					</td>
					</tr>
					 
													           		
	           		<tr height="10"><td></td></tr>
					
					<tr class="text12" >
					<td class="ownScrollableSubWindowDynamicWidthHeight" width="100%" style="height:30em;">
					<%-- this is the datatables grid (content)--%>
					<table id="angivelseList" class="display compact cell-border" width="100%" >
						<thead>
						<tr style="background-color:#EEEEEE">
							<th class="text11" title="avd">&nbsp;<spring:message code="systema.skat.export.list.search.label.avd"/>&nbsp;</th>
		                    <th class="text11" title="sign">&nbsp;<spring:message code="systema.skat.export.list.search.label.signatur"/>&nbsp;</th>
		                    <th class="text11" title="opd">&nbsp;<spring:message code="systema.skat.export.list.search.label.arende"/>&nbsp;</th>
		                    <th class="text11" title="refnr">&nbsp;<spring:message code="systema.skat.export.list.search.label.refnr"/>&nbsp;</th>
		                    <th class="text11" title="dkeh_xref">&nbsp;<spring:message code="systema.skat.export.list.search.label.xrefnr"/>&nbsp;</th>
		                    <th class="text11" title="aart">&nbsp;<spring:message code="systema.skat.export.list.search.label.aart"/>&nbsp;</th>
		                    <th class="text11" title="datum">&nbsp;<spring:message code="systema.skat.export.list.search.label.datum"/>&nbsp;</th>
		                    <th class="text11" title="status">&nbsp;<spring:message code="systema.skat.export.list.search.label.status"/>&nbsp;</th>
		                    <th class="text11" title="avsNavn">&nbsp;<spring:message code="systema.skat.export.list.search.label.avsandare"/>&nbsp;</th>
		                    <th class="text11" title="motNavn">&nbsp;<spring:message code="systema.skat.export.list.search.label.mottagare"/>&nbsp;</th>
		                </tr> 
		                </thead>
		                
		                <tbody>
		                <c:forEach var="record" items="${Xmodel.angivelseList}" varStatus="counter">    
			               <c:choose>           
			                   <c:when test="${counter.count%2==0}">
			                       <tr class="text11">
			                   </c:when>
			                   <c:otherwise>   
			                       <tr class="text11">
			                   </c:otherwise>
			               </c:choose>
			               <td class="text11">&nbsp;${record.avd}</td>
			               <td class="text11">&nbsp;${record.sign}</td>
			               <td nowrap style="cursor:pointer;" class="text11MediumBlue" id="vkod${Xrecord.tatanr}@text${Xrecord.beskr1}" >
			               		<img title="select" valign="bottom" src="resources/images/update.gif" border="0" alt="edit">&nbsp;${record.opd}
			               	</td>
		               	   <td class="text11">&nbsp;${record.refnr}</td>
		               	   <td class="text11">&nbsp;${topic.dkeh_xref}</td>
		               	   <td class="text11">&nbsp;${topic.aart}</td>
		               	   <td class="text11">&nbsp;${topic.datum}</td>
		               	   <td class="text11">&nbsp;${topic.status}</td>
		               	   <td class="text11">&nbsp;${topic.avsNavn}</td>
		               	   <td class="text11">&nbsp;${topic.motNavn}</td>
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
