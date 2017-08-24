<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header =====================================-->
<jsp:include page="/WEB-INF/views/headerTrorChildWindows.jsp" />
<!-- =====================end header ====================================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
	specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tror_childwindow.js?ver=${user.versionEspedsg}"></SCRIPT>
	
	<table width="90%" height="500px" class="tableBorderWithRoundCorners3D_RoundOnlyOnBottom" cellspacing="0" border="0" cellpadding="0">
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
		<form action="tror_childwindow_customer_addresses.do?action=doFind" name="searchCustomerForm" id="searchCustomerForm" method="post">
			<input type="hidden" name="ctype" id="ctype" value="${model.container.ctype}">
			<%-- =====================================================  --%>
          	<%-- Here we have the search [Customer] popup window --%>
          	<%-- =====================================================  --%>
          		<%-- this container table is necessary in order to separate the datatables element and the frame above, otherwise
			 	the cosmetic frame will not follow the whole datatable grid including the search field... --%>
				<table id="containerdatatableTable" cellspacing="2" align="left" width="100%">
					
					
					<tr><td>&nbsp;&nbsp;<button name="deliveryAdrMaintButton" id="deliveryAdrMaintButton" class="inputFormSubmit" type="button" ><spring:message code="systema.tror.childwindow.customeraddresses.label.createNewButton"/></button></td></tr>
					<tr><td><hr size="1" width="100%"/></td></tr>								           		
	           		<tr height="15"><td></td></tr>
					
					<tr class="text12" >
					<td class="ownScrollableSubWindowDynamicWidthHeight" width="100%" style="height:50em;">
					<%-- this is the datatables grid (content)--%>
					<table id="customerAddressesList" class="display compact cell-border" width="100%">
						<thead>
						<tr style="background-color:#EEEEEE">
							<th class="text11">&nbsp;<spring:message code="systema.tror.childwindow.customeraddresses.label.customeradr.address.id"/></th>   
		                    <th class="text11">&nbsp;<spring:message code="systema.tror.childwindow.customeraddresses.label.customeradr.address.name"/></th>
		                    <th width="2%" class="text11">&nbsp;<spring:message code="systema.tror.childwindow.customeraddresses.label.customeradr.address.update"/></th>
		                    <th class="text11">&nbsp;<spring:message code="systema.tror.childwindow.customeraddresses.label.customeradr.address.address"/></th>
		                    <th class="text11">&nbsp;<spring:message code="systema.tror.childwindow.customeraddresses.label.customeradr.address.postnrStedLand"/></th>
		                    <th width="2%" class="text11">&nbsp;<spring:message code="systema.tror.childwindow.customeraddresses.label.customeradr.address.delete"/></th>
		                    
		                </tr> 
		                </thead>
		                
		                <tbody>
		                <c:forEach var="record" items="${model.XcustomerAdressesList}" varStatus="counter">    
			               <c:choose>           
			                   <c:when test="${counter.count%2==0}">
			                       <tr class="text11" >
			                   </c:when>
			                   <c:otherwise>   
			                       <tr class="text11" >
			                   </c:otherwise>
			               </c:choose>
			               <td class="text11MediumBlue" style="cursor:pointer;" id="vadrnr_${record.vadrnr}@navn_${record.vadrna}@adr1_${record.vadrn1}@adr2_${record.vadrn2}@postnrsted_${record.vadrn3}@counter_${counter.count}">
			               	 <img style="vertical-align:middle;" src="resources/images/bebullet.gif" border="0" ><font class="text12SkyBlue" >&nbsp;${record.vadrnr}</font>
			               </td>
			               <td class="text11" >&nbsp;${record.vadrna}</td>
			               <td width="2%" align="center" class="text11" >&nbsp;
			               		<a href="javascript:void(0);" onClick="window.open('TODOtror_childwindow_customer_addresses_vedlikehold.do?action=doUpdate','deliveryAdrMaintWin','top=300px,left=300px,height=400px,width=400px,scrollbars=yes,status=no,location=no')">
		 							<img id="imgDelAdrUpdate" style="vertical-align:bottom; cursor:pointer;" src="resources/images/update.gif" border="0" alt="update">
								</a>
			               </td>
			               <td class="text11" >&nbsp;${record.vadrn1}&nbsp;${record.vadrn2}</td>
			               <td class="text11" >&nbsp;${record.vadrn3}&nbsp;${record.valand}</td>
			               <td width="2%" align="center" class="text11" >&nbsp;
			               		<a style="cursor:pointer;" id="@opd_${Xrecord.hereff}@alinkOpenOrdersListId_${counter.count}"
				           			onClick="setBlockUI(this);" href="tror_childwindow_customer_addresses_vedlikehold.do?action=doUpdate&rm=1">
	    		    				<img title="Update" style="vertical-align:bottom;" src="resources/images/delete.gif" border="0" alt="delete">
    		    				</a>
			               </td>
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
