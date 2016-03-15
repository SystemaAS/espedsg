<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerSysJservices.jsp" />
<!-- =====================end header ==========================-->
	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/asyjservices_mainlist.js?ver=${user.versionEspedsg}"></SCRIPT>
	
	<style type = "text/css">
	.ui-datepicker { font-size:9pt;}
	</style>

<table width="800px"  class="text11" cellspacing="0" border="0" cellpadding="0">
	<tr>
	<td>
	<%-- tab container component --%>
	<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
		<tr height="2"><td></td></tr>
		<tr height="25"> 
			<td width="20%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a style="display:block;" href="asyjservices_mainlist.do?action=Find">
					<img style="vertical-align:middle;" src="resources/images/bulletGreen.png" width="6px" height="6px" border="0" alt="efaktura log">
					<font class="tabDisabledLink">jServices</font>
				</a>
			</td>
			<%--
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="20%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a style="display:block;" href="transportdisp_workflow_getTrip.do?user=${user.user}&tuavd=${searchFilter.avd}&tupro=${searchFilter.tur}">
					<img style="vertical-align:bottom;" src="resources/images/list.gif" border="0" alt="general list">
					<font class="tabDisabledLink"><spring:message code="systema.transportdisp.workflow.trip.tab"/></font>
				</a>
			</td>
			 --%>
			<td width="80%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
		</tr>
	</table>
	</td>
	</tr>
	<input type="hidden" name="applicationUser" id="applicationUser" value='${user.user}'>
	
	<%-- Validation errors --%>
	<spring:hasBindErrors name="record"> <%-- name must equal the command object name in the Controller --%>
	<tr>
		<td>
           	<table width="100%" align="left" border="0" cellspacing="0" cellpadding="0">
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
	
		<tr>
		<td>
			<%-- this table wrapper is necessary to apply the css class with the thin border --%>
			<table id="wrapperTable" class="tabThinBorderWhite" width="100%" cellspacing="1" border="0">
			<tr height="5"><td></td></tr> 
						
			<%-- Datatables component --%>
			<%-- Log records --%>
			
			<tr>
				<td>
				<table id="containerdatatableTable" width="100%" cellspacing="2" align="left">	
				<tr>
					<td class="ownScrollableSubWindowNoBackgroundColor" style="background-color:#F7F7F6; width:100%; height:50em;">
					<table id="mainList" class="display compact cell-border" width="100%" cellspacing="0">
						<thead>
						<tr style="background-color:#BCC6CC">
							<th style="text-align:left" class="text12">Knavn</th>   
		                    <th align="left" class="text12">Adr1</th>
		                </tr> 
		                </thead>
		                
		                <tbody>
			            <c:forEach items="${list}" var="record" varStatus="counter">    
			      			<tr class="text11" >
			             		<td class="text11MediumBlue" >&nbsp;${record.knavn}</td>
			               		<td class="text11MediumBlue" >&nbsp;${record.adr1}</td>
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
		</td>
		</tr>
		<tr height="10"><td></td></tr>
	</table>
		
<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->

