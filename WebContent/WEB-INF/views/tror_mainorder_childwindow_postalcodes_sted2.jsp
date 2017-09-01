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
				<spring:message code="systema.tror.childwindow.postalcodes.label.title"/>
			</td>
		</tr>
		<tr height="20"><td colspan="2"></td></tr>
		<tr>
		<td valign="top">
			<%-- ============================================  --%>
          	<%-- Here we have the search  popup window --%>
          	<%-- ============================================  --%>
          		<%-- this container table is necessary in order to separate the datatables element and the frame above, otherwise
			 	the cosmetic frame will not follow the whole datatable grid including the search field... --%>
				<table id="containerdatatableTable" cellspacing="2" align="left" width="100%">
					<tr>
					<td>
						<table>
						<form name="trorPostalCodeForm" id="trorPostalCodeForm" action="tror_mainorder_childwindow_postalcodes_sted2.do?action=doFind" method="post">
						<input type="hidden" name="ctype" id="ctype" value="${model.ctype}">
						<tr>
							<td class="text12">&nbsp;<spring:message code="systema.tror.childwindow.postalcodes.label.postnr"/></td>
							<td class="text12">&nbsp;<input type="text" class="inputText" name="st2kod" id="st2kod" size="10" maxlength="10" value="${model.record.st2kod}"></td>
							<td class="text11">&nbsp;</td>
							<td class="text12">&nbsp;<spring:message code="systema.tror.childwindow.postalcodes.label.countryCode"/></td>
							<td class="text12">&nbsp;<input type="text" class="inputText" name="st2lk" id="st2lk" size="4" maxlength="2" value="${model.record.st2lk}"></td>
							
							<td class="text11">&nbsp;</td>
	           				<td align="right">&nbsp;<input class="inputFormSubmit" type="submit" name="submit" value='<spring:message code="search.label"/>'></td>
		           		</tr>
		           		</form>
		           		</table>
					</td>
					</tr>								           		
	           		<tr height="10"><td></td></tr>
					<tr class="text12" >
					<td class="ownScrollableSubWindowDynamicWidthHeight" width="100%" style="height:50em;">
					<%-- this is the datatables grid (content)--%>
					<table id="postalCodeList" class="display compact cell-border" width="100%">
						<thead>
						<tr style="background-color:#EEEEEE">
							<th width="5%" class="text11">&nbsp;<spring:message code="systema.tror.childwindow.postalcodes.label.postnr"/></th>   
		                    <th class="text11">&nbsp;<spring:message code="systema.tror.childwindow.postalcodes.label.name"/></th>
		                    <th width="2%" align="center" class="text11">&nbsp;<spring:message code="systema.tror.childwindow.postalcodes.label.countryCode"/></th>
		                    <th width="2%" align="center" class="text11">&nbsp;<spring:message code="systema.tror.childwindow.postalcodes.label.stkode"/></th>
		                    <th width="2%" align="center" class="text11">&nbsp;<spring:message code="systema.tror.childwindow.postalcodes.label.norsksonefly"/></th>
		                </tr> 
		                </thead>
		                
		                <tbody>
		                <c:forEach var="record" items="${model.postalCodeList}" varStatus="counter">    
			               <tr class="text11" >
			               <%-- 
			               <td class="text11MediumBlue" style="cursor:pointer;" id="vadrnr_${record.vadrnr}@navn_${record.vadrna}@adr1_${record.vadrn1}@adr2_${record.vadrn2}@postnrsted_${record.vadrn3}@counter_${counter.count}img">
			               	 <img style="vertical-align:middle;" src="resources/images/bebullet.gif" border="0" >
			               </td>
			               --%>
			               <td class="text11MediumBlue" style="cursor:pointer;" id="postalcode_${record.st2kod}@country_${record.st2lk}@city_${record.st2nvn}@st2ko2_${record.st2ko2}@st2son_${record.st2son}@counter_${counter.count}">
			               	 	${record.st2kod}
			               </td>
			               <td class="text11" >&nbsp;${record.st2nvn}</td> 
			               <td width="2%" align="center" class="text11" >&nbsp;${record.st2lk}</td>
			               <td width="2%" align="center" class="text11" >&nbsp;${record.st2ko2}</td>
			               <td width="2%" align="center" class="text11" >&nbsp;${record.st2son}</td>
			               
			               
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
