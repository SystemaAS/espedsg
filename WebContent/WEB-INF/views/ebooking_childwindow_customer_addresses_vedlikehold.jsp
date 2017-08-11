<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header =====================================-->
<jsp:include page="/WEB-INF/views/headerEbookingChildWindows.jsp" />
<!-- =====================end header ====================================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
	specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/ebooking_childwindow.js?ver=${user.versionEspedsg}"></SCRIPT>
	
	<table width="90%" height="300px" class="tableBorderWithRoundCorners3D_RoundOnlyOnBottom" cellspacing="0" border="0" cellpadding="0">
		<tr height="5"><td colspan="2"></td></tr>
		<tr>
			<td valign="top" colspan="3" class="text14Bold">&nbsp;&nbsp;&nbsp;
			<img title="search" valign="bottom" src="resources/images/vedlikehold.png" width="40px" height="40px" border="0" alt="search">
			<spring:message code="systema.ebooking.childwindow.customeraddresses.maint.label.title"/>
			</td>
		</tr>
		<tr height="20"><td colspan="2"></td></tr>
		<tr>
		<td valign="top">
			
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
					
													           		
	           		<tr height="10"><td></td></tr>
					
					<tr class="text12" >
					<td valign="top" >
					<%-- form --%>
					<form action="ebooking_childwindow_customer_addresses_vedlikehold.do?action=doUpdate" name="searchAdressMaintForm" id="searchAdressMaintForm" method="post">
					<input type="hidden" name="ctype" id="ctype" value="${model.container.ctype}">
					<table id="fieldTable" >
						<tr>
							<td class="text11">
								<span title="todo"><spring:message code="systema.ebooking.childwindow.customeraddresses.maint.label.customeradr.address.customernr"/></span>
							</td>
							<td class="text11">
								<input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" type="text" class="inputTextMediumBlueMandatoryField" name="todo" id="todo" size="35" maxlength="30" value="${Xmodel.record.todo}">
								<font class="text16RedBold" >*</font>
							</td>
						</tr>
						<tr>
							<td class="text11">
								<span title="todo"><spring:message code="systema.ebooking.childwindow.customeraddresses.maint.label.customeradr.address.name"/></span>
							</td>
							<td class="text11">
								<input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" type="text" class="inputTextMediumBlueMandatoryField" name="todo" id="todo" size="35" maxlength="30" value="${Xmodel.record.todo}">
								<font class="text16RedBold" >*</font>
							</td>
						</tr>
						<tr>
							<td class="text11">
								<span title="todo"><spring:message code="systema.ebooking.childwindow.customeraddresses.maint.label.customeradr.address.address1"/></span>
							</td>
							<td class="text11">
								<input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" type="text" class="inputTextMediumBlueMandatoryField" name="todo" id="todo" size="35" maxlength="30" value="${Xmodel.record.todo}">
								<font class="text16RedBold" >*</font>
							</td>
						</tr>
						<tr>
							<td class="text11">
								<span title="todo"><spring:message code="systema.ebooking.childwindow.customeraddresses.maint.label.customeradr.address.address2"/></span>
							</td>
							<td class="text11">
								<input type="text" class="inputTextMediumBlue" name="todo" id="todo" size="35" maxlength="30" value="${Xmodel.record.todo}">
							</td>
						</tr>
						<tr>
							<td class="text11">
								<span title="todo"><spring:message code="systema.ebooking.childwindow.customeraddresses.maint.label.customeradr.address.postnrSted"/></span>
								
							</td>
							<td class="text11">
								<input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" type="text" class="inputTextMediumBlueMandatoryField" name="todo" id="todo" size="35" maxlength="30" value="${Xmodel.record.todo}">
								<font class="text16RedBold" >*</font>
							</td>
						</tr>
						<tr>
							<td class="text11">
								<span title="todo"><spring:message code="systema.ebooking.childwindow.customeraddresses.maint.label.customeradr.address.countryCode"/></span>
							</td>
							<td class="text11">
								<input type="text" class="inputTextMediumBlue" name="todo" id="todo" size="3" maxlength="2" value="${Xmodel.record.todo}">
							</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td>
								<input tabindex=-1 class="inputFormSubmit" type="submit" name="submit" id="submit" value='<spring:message code="systema.ebooking.submit.save"/>'/>
							</td>
						</tr>	
		            </table>
		            </form>
		            </td>
	           		</tr>
        			</table>
		</td>
		</tr>
	</table> 
