<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header =====================================-->
<jsp:include page="/WEB-INF/views/headerEbookingChildWindows.jsp" />
<!-- =====================end header ====================================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
	specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/ebooking_childwindow.js?ver=${user.versionEspedsg}"></SCRIPT>
	
	<table width="90%" height="500px" class="tableBorderWithRoundCorners3D_RoundOnlyOnBottom" cellspacing="0" border="0" cellpadding="0">
		<tr height="5"><td colspan="2"></td></tr>
		<tr>
			<td colspan="3" class="text14Bold">&nbsp;&nbsp;&nbsp;
			<img title="search" valign="bottom" src="resources/images/search.gif" width="24px" height="24px" border="0" alt="search">
			<spring:message code="systema.ebooking.childwindow.customeraddresses.label.title"/>
			</td>
		</tr>
		<tr height="20"><td colspan="2"></td></tr>
		<tr>
		<td valign="top">
		<form action="ebooking_childwindow_customer_addresses.do?action=doFind" name="searchCustomerForm" id="searchCustomerForm" method="post">
			<input type="hidden" name="ctype" id="ctype" value="${model.container.ctype}">
			<%-- =====================================================  --%>
          	<%-- Here we have the search [Customer] popup window --%>
          	<%-- =====================================================  --%>
          		<%-- this container table is necessary in order to separate the datatables element and the frame above, otherwise
			 	the cosmetic frame will not follow the whole datatable grid including the search field... --%>
				<table id="containerdatatableTable" cellspacing="2" align="left" width="100%">
					
					<%-- NO FILTER since the data set won't be large (less than 50 in all customers)
					<tr height="5"><td></td></tr>
					<tr>
					<td>
						<table>
						<tr>
							<td class="text11">&nbsp;Customer No.</td>
							<td class="text11">&nbsp;<input type="text" class="inputText" name="sokknr" id="sokknr" size="8" maxlength="8" value="${model.container.sokknr}"></td>
						
							<td class="text11">&nbsp;&nbsp;&nbsp;Name</td>
							<td class="text11">&nbsp;<input type="text" class="inputText" name="soknvn" id="soknvn" size="15" maxlength="35" value="${model.container.soknvn}"></td>
						
							<td class="text11">&nbsp;&nbsp;&nbsp;Post.Code/City/Country</td>
							<td class="text11">&nbsp;<input type="text" class="inputText" name="kunpnsted" id="kunpnsted" size="15" maxlength="10" value="${model.container.kunpnsted}"></td>
						
							<td class="text11">&nbsp;</td>
	           				<td align="right">&nbsp;<input class="inputFormSubmit" type="submit" name="submit" value='<spring:message code="systema.transportdisp.search"/>'></td>
	           				<td width="15px" >&nbsp;</td>
	           				<td class="text11" style="color:#9F6000;"><label class="isa_warning" >&nbsp;&nbsp;Adressekunder&nbsp;&nbsp;</label></td>
	           				
		           		</tr>
		           		
		           		</table>
					</td>
					</tr>
					<%-- Validation errors 
					<spring:hasBindErrors name="record"> <%-- name must equal the command object name in the Controller
					<tr>
						<td colspan="20">
			            	<table align="left" border="0" cellspacing="0" cellpadding="0">
			            	<tr>
							<td class="textError">					
					            <ul>
					            <c:forEach var="error" items="${errors.allErrors}">
					                <li >
					                	<spring:message code="${error.code}" text="${error.defaultMessage}"/>
					                </li>
					            </c:forEach>
					            </ul>
							</td>
							</tr>
							</table>
						</td>
					</tr>
					</spring:hasBindErrors>
					--%>					
					<tr><td>&nbsp;&nbsp;<button name="deliveryAdrMaintButton" id="deliveryAdrMaintButton" class="inputFormSubmit" type="button" ><spring:message code="systema.ebooking.childwindow.customeraddresses.label.createNewButton"/></button></td></tr>
					<tr><td><hr size="1" width="100%"/></td></tr>								           		
	           		<tr height="15"><td></td></tr>
					
					<tr class="text12" >
					<td class="ownScrollableSubWindowDynamicWidthHeight" width="100%" style="height:50em;">
					<%-- this is the datatables grid (content)--%>
					<table id="customerAddressesList" class="display compact cell-border" width="100%">
						<thead>
						<tr style="background-color:#EEEEEE">
							<th class="text11">&nbsp;<spring:message code="systema.ebooking.childwindow.customeraddresses.label.customeradr.address.id"/></th>   
		                    <th class="text11">&nbsp;<spring:message code="systema.ebooking.childwindow.customeraddresses.label.customeradr.address.name"/></th>
		                    <th width="2%" class="text11">&nbsp;<spring:message code="systema.ebooking.childwindow.customeraddresses.label.customeradr.address.update"/></th>
		                    <th class="text11">&nbsp;<spring:message code="systema.ebooking.childwindow.customeraddresses.label.customeradr.address.address"/></th>
		                    <th class="text11">&nbsp;<spring:message code="systema.ebooking.childwindow.customeraddresses.label.customeradr.address.postnrStedLand"/></th>
		                    <th width="2%" class="text11">&nbsp;<spring:message code="systema.ebooking.childwindow.customeraddresses.label.customeradr.address.delete"/></th>
		                    
		                </tr> 
		                </thead>
		                
		                <tbody>
		                <c:forEach var="record" items="${model.customerAdressesList}" varStatus="counter">    
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
			               		<%--
			               		<a style="cursor:pointer;" id="@opd_${Xrecord.hereff}@alinkOpenOrdersListId_${counter.count}"
				           			onClick="setBlockUI(this);" href="ebooking_childwindow_customer_addresses_vedlikehold.do?action=doUpdate">
	    		    				<img title="Update" style="vertical-align:bottom;" src="resources/images/update.gif" border="0" alt="update">
    		    				</a>
    		    				--%>
    		    				<a href="javascript:void(0);" onClick="window.open('ebooking_childwindow_customer_addresses_vedlikehold.do?action=doUpdate','deliveryAdrMaintWin','top=300px,left=300px,height=400px,width=400px,scrollbars=yes,status=no,location=no')">
		 							<img id="imgDelAdrUpdate" style="vertical-align:bottom; cursor:pointer;" src="resources/images/update.gif" border="0" alt="update">
								</a>
			               </td>
			               <td class="text11" >&nbsp;${record.vadrn1}&nbsp;${record.vadrn2}</td>
			               <td class="text11" >&nbsp;${record.vadrn3}&nbsp;${record.valand}</td>
			               <td width="2%" align="center" class="text11" >&nbsp;
			               		<a style="cursor:pointer;" id="@opd_${Xrecord.hereff}@alinkOpenOrdersListId_${counter.count}"
				           			onClick="setBlockUI(this);" href="ebooking_childwindow_customer_addresses_vedlikehold.do?action=doUpdate&rm=1">
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
