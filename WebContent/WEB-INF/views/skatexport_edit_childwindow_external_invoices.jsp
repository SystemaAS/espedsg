<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header =====================================-->
<jsp:include page="/WEB-INF/views/headerSkatChildWindows.jsp" />
<!-- =====================end header ====================================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
	specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/skatexport_edit_childwindow_external_invoices.js?ver=${user.versionEspedsg}"></SCRIPT>
	
	<table width="99%" height="100px" class="tableBorderWithRoundCorners3D_RoundOnlyOnBottom" cellspacing="0" border="0" cellpadding="0">
	
		<tr><td valign="top" colspan="3" class="text14Bold">&nbsp;&nbsp;&nbsp;
		<img title="select" valign="bottom" src="resources/images/search.gif" width="24px" height="24px" border="0" alt="search">
		Eksterne Fakturaer
		</td>
		</tr>
		
		<tr height="8"><td></td></tr>
		<tr>
		<td valign="top">
		<form action="TODO_childwindow_external_invoices.do?action=doFind" name="searchForm" id="searchForm" method="post">
				<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
				<input type="hidden" name="avd" id="avd" value="${model.avd}">
				<input type="hidden" name="opd" id="opd" value="${model.opd}">
				
				<%-- this container table is necessary in order to separate the datatables element and the frame above, otherwise
			 	the cosmetic frame will not follow the whole datatable grid including the search field... --%>
				<table id="containerdatatableTable" width="98%" align="left">
					<%-- Fakturalista (external invoices) --%>
					<tr height="15"><td></td></tr>
					<tr><td valign="top" colspan="3" class="text14Bold">&nbsp;Fakturaer</td></tr>
					<tr>
						<td colspan="3" >
							<table id="tblInvoices" class="display compact cell-border" width="100%">
								<thead>
								<tr style="background-color:#DDDDDD">
									<th class="text12">&nbsp;Vælg&nbsp;</th>
								    <th class="text12">&nbsp;Fakturanr.&nbsp;</th>   
				                    <%--
				                    <th class="text12">&nbsp;Type&nbsp;</th>
				                     --%> 
				                    <th align="right" class="text12">&nbsp;Beløb&nbsp;</th> 
				                    <th class="text12">&nbsp;Møntsort&nbsp;</th> 
				                    
			                    </tr>
			                    </thead>
			                    <tbody>
			                    <c:forEach items="${Xmodel.list}" var="record" varStatus="counter">    
					               <c:choose>           
					                   <c:when test="${counter.count%2==0}">
					                       <tr class="tableRow" height="15" >
					                   </c:when>
					                   <c:otherwise>   
					                       <tr class="tableOddRow" height="15" >
					                   </c:otherwise>
					               </c:choose>
					               <td class="text11" align="center">
					               		<input class="clazzInvoiceAware" type="checkbox" value="J" id="id${record.dkef_reff}_unik${record.dkef_unik}" name="id${record.dkef_reff}_unik${record.dkef_unik}" >
					               </td>
					               <td width="10%" class="text11" align="center">&nbsp;<span title="reff/unik:${record.dkef_reff}/${record.dkef_unik}">${record.dkef_fatx}</span></td>
					               <%--
					               <td class="text11" >&nbsp;${record.dkef_faty}</td>
					                --%>
					               <td align="right" class="text11" >&nbsp;${record.dkef_fabl}&nbsp;</td>
					               <td class="text11" >&nbsp;${record.dkef_vakd}</td>
					               
				               </tr>
				               </c:forEach>
				               </tbody>
		                    </table>  
						</td>
					</tr>
					
					
	               <tr>
		               <td align="left">
		               		&nbsp;<input class="inputFormSubmit" type="button" name="buttonCloseOk" id="buttonCloseOk" value='OK'>
		               		&nbsp;<input class="inputFormSubmit" type="button" name="cancel" id="cancel" value='Annullér'>
		               </td>
	               </tr>	
			</table>
				
		</form>	
		</td>
		</tr>
	</table> 
