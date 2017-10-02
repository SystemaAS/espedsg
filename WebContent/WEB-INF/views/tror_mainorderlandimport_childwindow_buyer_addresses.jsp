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
				<spring:message code="systema.tror.childwindow.customeraddresses.label.title.alt2"/>
			</td>
		</tr>
		<tr height="20"><td colspan="2"></td></tr>
		<tr>
		<td valign="top">
		<form action="tror_childwindow_buyer_addresses.do?action=doFind" name="searchCustomerForm" id="searchCustomerForm" method="post">
			<input type="hidden" name="ctype" id="ctype" value="${model.container.ctype}">
			<%-- =====================================================  --%>
          	<%-- Here we have the search [Customer] popup window --%>
          	<%-- =====================================================  --%>
          		<%-- this container table is necessary in order to separate the datatables element and the frame above, otherwise
			 	the cosmetic frame will not follow the whole datatable grid including the search field... --%>
				<table id="containerdatatableTable" cellspacing="2" align="left" width="100%">
					
					<tr><td><hr size="1" width="100%"/></td></tr>								           		
	           		<tr height="15"><td></td></tr>
					
					<tr class="text12" >
					<td class="ownScrollableSubWindowDynamicWidthHeight" width="100%" style="height:30em;">
					<%-- this is the datatables grid (content)--%>
					<table id="customerAddressesList" class="display compact cell-border" width="100%">
						<thead>
						<tr style="background-color:#EEEEEE">
							<th class="text11">&nbsp;<spring:message code="systema.tror.childwindow.customeraddresses.label.customeradr.address.addressNr"/></th>   
		                    <th class="text11">&nbsp;<spring:message code="systema.tror.childwindow.customeraddresses.label.customeradr.address.name"/></th>
		                    <th class="text11">&nbsp;<spring:message code="systema.tror.childwindow.customeraddresses.label.customeradr.address.address"/></th>
		                    <th class="text11">&nbsp;<spring:message code="systema.tror.childwindow.customeraddresses.label.customeradr.address.address2"/></th>
		                    <th class="text11">&nbsp;<spring:message code="systema.tror.childwindow.customeraddresses.label.customeradr.address.postnrStedLand"/></th>
		                </tr> 
		                </thead>
		                
		                <tbody>
		                <c:forEach var="record" items="${model.buyerAddressesList}" varStatus="counter">    
			               <tr class="text11" >
			               
			               <td class="text11MediumBlue" style="cursor:pointer;" id="vadrnr_${record.vadrnr}@navn_${record.vadrna}@adr1_${record.vadrn1}@adr2_${record.vadrn2}@postnrsted_${record.vadrn3}@postnr_${record.postNr}@sted_${record.city}@counter_${counter.count}">
			               	 <img style="vertical-align:middle;" src="resources/images/bebullet.gif" border="0" ><font class="text12SkyBlue" >&nbsp;${record.vadrnr}</font>
			               </td>
			               <td class="text11" >&nbsp;${record.vadrna}</td>
			               <td class="text11" >&nbsp;${record.vadrn1}</td>
			               <td class="text11" >&nbsp;${record.vadrn2}</td>
			               <td class="text11" >&nbsp;${record.vadrn3}</td>
			               
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
