<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header =====================================-->
<jsp:include page="/WEB-INF/views/headerTrorChildWindows.jsp" />
<!-- =====================end header ====================================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
	specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tror_childwindow.js?ver=${user.versionEspedsg}"></SCRIPT>
	
	<table width="90%" class="tableBorderWithRoundCorners3D_RoundOnlyOnBottom" cellspacing="0" border="0" cellpadding="0">
		<tr height="5"><td colspan="2"></td></tr>
		<tr>
			<td colspan="3" class="text14Bold">&nbsp;&nbsp;&nbsp;
			<img title="search" valign="bottom" src="resources/images/search.gif" width="24px" height="24px" border="0" alt="search">
			<spring:message code="systema.tror.childwindow.customeraddresses.label.title"/>
			</td>
		</tr>
		<tr height="20"><td colspan="2"></td></tr>
		<tr>
		<td valign="top">
		<form action="tror_childwindow_seller_addresses.do?action=doFind" name="searchCustomerForm" id="searchCustomerForm" method="post">
			<input type="hidden" name="ctype" id="ctype" value="${model.container.ctype}">
			<%-- =====================================================  --%>
          	<%-- Here we have the search [Customer] popup window --%>
          	<%-- =====================================================  --%>
          		<%-- this container table is necessary in order to separate the datatables element and the frame above, otherwise
			 	the cosmetic frame will not follow the whole datatable grid including the search field... --%>
				<table id="containerdatatableTable" cellspacing="2" align="left" width="100%">
					<%--
					<tr><td>&nbsp;&nbsp;<button name="deliveryAdrMaintButton" id="deliveryAdrMaintButton" class="inputFormSubmit" type="button" ><spring:message code="systema.tror.childwindow.customeraddresses.label.createNewButton"/></button></td></tr>
					<tr><td><hr size="1" width="100%"/></td></tr>								           		
	           		<tr height="15"><td></td></tr>
					 --%>
					<tr height="10"><td></td></tr>
					 
					<tr class="text12" >
					<td class="ownScrollableSubWindowDynamicWidthHeight" width="100%" style="height:30em;">
					<%-- this is the datatables grid (content)--%>
					<table id="customerAddressesList" class="display compact cell-border" width="100%">
						<thead>
						<tr style="background-color:#EEEEEE">
							<th class="text11">&nbsp;</th>
							<th class="text11">&nbsp;Lev.kundenr</th>   
		                    <th class="text11">&nbsp;Mott./ vadrn</th>
		                    <th class="text11">&nbsp;Alfa</th>
		                    <%-- <th width="2%" class="text11">&nbsp;<spring:message code="systema.tror.childwindow.customeraddresses.label.customeradr.address.update"/></th> --%>
		                    <th class="text11">&nbsp;Navn</th>
		                    <th class="text11">&nbsp;Adr1</th>
		                    <th class="text11">&nbsp;Adr2</th>
		                    <th class="text11">&nbsp;Adr3</th>
		                    <%-- <th width="2%" class="text11">&nbsp;<spring:message code="systema.tror.childwindow.customeraddresses.label.customeradr.address.delete"/></th>  --%>
		                </tr> 
		                </thead>
		                
		                <tbody>
		                <c:forEach var="record" items="${model.sellerAddressesList}" varStatus="counter">    
			               <tr class="text11" >
			               <td width="2%" class="text11MediumBlue" >
			               		<img style="vertical-align:middle;" src="resources/images/bebullet.gif" border="0" >
			               </td>
			               <td class="text11MediumBlue" style="cursor:pointer;" id="vadrnr_${record.kukun2}@navn_${record.navn}@adr1_${record.adr1}@adr2_${record.adr2}@postnrsted_${record.adr3}@postnr_dummy@sted_dummy@counter_${counter.count}">
			               	 <font class="text12SkyBlue" >&nbsp;${record.kuintn}</font>
			               </td>
			               <td class="text11" >&nbsp;${record.kukun2}</td>
			               <td class="text11" >&nbsp;${record.kualfa}</td>
			               <td class="text11" >&nbsp;${record.navn}</td>
			               <td class="text11" >&nbsp;${record.adr1}</td>
			               <td class="text11" >&nbsp;${record.adr2}</td>
			               <td class="text11" >&nbsp;${record.adr3}</td>
			               
			            </tr> 
			            </c:forEach>
			            </tbody>
		            </table>
		            </td>
	           		</tr>
        			</table>
				
		</form>	
		</td>
		</tr>
	</table> 
