<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header =================================== -->
<jsp:include page="/WEB-INF/views/headerTvinnSadAvggrunnlagExternal.jsp" />
<!-- =====================end header ================================== -->
	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tvinnsadglobal_edit.js?ver=${user.versionEspedsg}"></SCRIPT>	
	<SCRIPT type="text/javascript" src="resources/js/tvinnsadadmin_avggrunnlag.js?ver=${user.versionEspedsg}"></SCRIPT>	
			
	<style type = "text/css">
	.ui-datepicker { font-size:9pt;}
	</style>


<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
<tr>
	<td>
	<%-- tab container component --%>
	<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
		<tr height="2"><td></td></tr>
		<tr height="25">
			<td width="15%" valign="bottom" class="tab" align="center" nowrap>
				<font class="tabLink">&nbsp;<spring:message code="systema.tvinn.sad.admin.avggrunnlag.list.tab"/></font>
				<img valign="bottom" src="resources/images/list.gif" border="0" alt="avg.grunnlag">
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="85%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>	
		</tr>
	</table>
	</td>
</tr>

<tr>
	<td>
	<%-- search filter component --%>
		
 		<table width="100%" class="tabThinBorderWhite" border="0" cellspacing="0" cellpadding="0">
 			<tr height="20"><td></td></tr>
 			
 	        <tr>
 	        	<td>
 	        		<table style="width:80%;" align="left" class="XformFrame" border="0" cellspacing="0" cellpadding="0">
 	        			<tr height="15"><td >&nbsp;</td></tr>
 	        			<tr>
						<td width="99%" valign="top">
							<table class="dashboardFrameHeader" align="center" style="width:80%" cellspacing="1" cellpadding="0">
					 			<tr height="18">
						 			<td class="text14">
							 			<img style="vertical-align: middle" src="resources/images/bulletGreen.png" width="8" hight="8" border="0" alt="alt">
							 			Beregningskriteriene
						 			</td>
						    		</tr>
					 		</table>
						</td>
						</tr>	
 	        			
 	        			<tr>
						<td width="99%" valign="top">
						<table align="center" style="width:80%" class="tableBorderWithRoundCorners3D_RoundOnlyOnBottom" cellspacing="1" cellpadding="0">
						<tr>
							<td>
							<form name="sadAdminAvggrunnlagForm" id="sadAdminAvggrunnlagForm" action="tvinnsadadmin_avggrunnlag_external.do?action=doCalculate" method="post" >
							<table align="left" style="width:90%" cellspacing="1" cellpadding="0"> 
					 			<tr height="8"><td >&nbsp;</td></tr>
 	        					<tr>
					 	        	<td>&nbsp;</td>	
					 	        	<td class="text12" align="left" >&nbsp;Fra dato</td>
					                <td class="text12" align="left" >&nbsp;Til dato</td>
					                <td class="text12" align="left" >&nbsp;Kunde
					                	<a tabindex="-1" id="avggCustomerIdLink">
											<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
										</a>
					                </td>
					                
					                <td>&nbsp;</td>
								</tr>
					 	        <tr>
					 	        	<td>&nbsp;</td>	
									<td align="left" ><input onKeyPress="return numberKey(event)" type="text" class="inputText" name="fromDate" id="fromDate" size="10" maxlength="6" value='${searchFilter.fromDate}'>&nbsp;</td>
									<td align="left" ><input onKeyPress="return numberKey(event)" type="text" class="inputText" name="toDate" id="toDate" size="10" maxlength="6" value='${searchFilter.toDate}'>&nbsp;</td>
									<td align="left" colspan="2">
										<input type="text" class="inputText" name="avggCustomerId" id="avggCustomerId" size="10" maxlength="8" value='${searchFilter.avggCustomerId}'>&nbsp;
										<input readonly type="text" class="inputTextReadOnly" name="avggCustomerName" id="avggCustomerName" size="35" maxlength="35" value=''>
									</td>
									<td valign="top" align="left" >
					                   &nbsp;<input class="inputFormSubmit" type="submit" name="submit" id="submit" value='Lage'>
					                </td>
								</tr>
								<tr height="8"><td >&nbsp;</td></tr>
 	        				</table>
 	        				</form>
 	        				</td>
 	        			</tr>
 	        			<tr height="10"><td >&nbsp;</td></tr>
 	        			
 	        			
 	        			</table>	
 	        			</td>
 	        			</tr>
 	        			<tr height="15"><td >&nbsp;</td></tr>
 	        			
 	        			<tr>
							<td >
								<table align="center" style="width:80%" cellspacing="0" border="0" cellpadding="0">
									<tr>
									<td class="text12">
						 				<img style="vertical-align: bottom" src="resources/images/excel.png" width="18" hight="18" border="0" alt="show docs">
						 				&nbsp;<b>Resultat</b>
						 			</td>
						    		</tr>
				    				<tr height="2"><td></td></tr>
									<tr class="tableHeaderField" height="20" valign="left">
					                    <td class="tableHeaderFieldFirst">&nbsp;Dato&nbsp;</td> 
					                    <td class="tableHeaderField">&nbsp;Dokument&nbsp;</td>
					                    
					               </tr> 
					              
					               	<c:forEach var="record" items="${model.documentList}" varStatus="counter">    
						               <tr class="tableRow" height="20" >
						               <td class="tableCell">&nbsp;${Xrecord.date}&nbsp;</td>
						               <td class="tableCell">&nbsp;${Xrecord.link}&nbsp;</td>
						               	
						              
						               <%--	
						               <td class="tableCellFirst">&nbsp;
						               		<c:if test="${not empty record.wttd}">
							                  	<fmt:parseDate value="${record.wttd}" var="dateHendelseslogDate" pattern="yyyyMMdd" />
					    				       	  	<fmt:formatDate pattern="yyyy.MM.dd" value="${dateHendelseslogDate}" />
				    				       	  	</c:if>
						               </td>
						               <td class="tableCell">&nbsp;
						               		<c:if test="${not empty record.wttt}">
							                  	<fmt:parseDate type="time" value="${record.wttt}" var="timeHendelseslogDate" pattern="HHmmss" />
							               		<fmt:formatDate pattern="HH:mm:ss" value="${timeHendelseslogDate}" />
						               		</c:if>
						               </td>
						               <td class="tableCell">&nbsp;${record.wttx}&nbsp;</td>
						               <td class="tableCell" >&nbsp;
						               		<c:if test="${not empty record.wttg}">
						               			<img style="vertical-align: bottom" src="${record.wttg}" border="0" alt="show sign">
						               		</c:if>
						               </td>
						               <td align="center" class="tableCell" >&nbsp;
						               		<c:if test="${not empty record.wttg2}">
						               			<a href="${record.wttg2}" target="_blank">
						               				<img style="vertical-align: bottom" src="${record.wttg2}" Hspace="5" Vspace="5" width="128px" height="80px" border="0" alt="show image">
						               			</a>
						               		</c:if>
						               </td>
						                --%>
						            </tr> 
						            </c:forEach>
						             
					            </table>
							</td>
						</tr>
 	        			<tr height="20"><td >&nbsp;</td></tr>
 	        			
 	        		</table>	
 	        	</td>
 	        </tr>
 	        <tr height="10"><td></td></tr>
		</table>
	</td>
	</tr>
	<tr height="3"><td></td></tr>
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
	<%-- list component --%>
	<c:if test="${not empty list}">
	<tr>
		<td>		
		<table width="100%" cellspacing="0" border="0" cellpadding="0">
	    	<%-- separator 
	        <tr height="1"><td></td></tr> 
			<tr>
				<td>
				<table width="100%" cellspacing="0" border="0" cellpadding="0">
					<tr class="tableHeaderField" height="20" valign="left">
	                    <td class="tableHeaderFieldFirst" align="left" >&nbsp;<spring:message code="systema.tvinn.sad.admin.transport.list.search.label.avd"/></td>
                			<td class="tableHeaderField" align="left" >&nbsp;<spring:message code="systema.tvinn.sad.admin.transport.list.search.label.sign"/></td>
		                <td class="tableHeaderField" align="left" >&nbsp;<spring:message code="systema.tvinn.sad.admin.transport.list.search.label.transportuppdrag"/></td>
		                <td class="tableHeaderField" align="left" >&nbsp;<spring:message code="systema.tvinn.sad.admin.transport.list.search.label.sender"/></td>
		                <td class="tableHeaderField" align="left" >&nbsp;<spring:message code="systema.tvinn.sad.admin.transport.list.search.label.receiver"/></td>
		                <td class="tableHeaderField" align="left" >&nbsp;<spring:message code="systema.tvinn.sad.admin.transport.list.search.label.datum"/></td>
	                </tr>     
		            <c:forEach items="${list}" var="topic" varStatus="counter">    
		               <c:choose>           
		                   <c:when test="${counter.count%2==0}">
		                       <tr class="tableRow" height="20" >
		                   </c:when>
		                   <c:otherwise>   
		                       <tr class="tableOddRow" height="20" >
		                   </c:otherwise>
		               </c:choose>
		               <td class="tableCellFirst" width="5%">&nbsp;${topic.avd}</td>
		               <td class="tableCell" >&nbsp;${topic.sign}</td>
		               <td class="tableCell" >&nbsp;${topic.opd}</td>
		               <td class="tableCell" >&nbsp;${topic.avsNavn}</td>
		               <td class="tableCell" >&nbsp;${topic.motNavn}</td>
		               <td class="tableCell" >&nbsp;${topic.datum}</td>
		            </tr> 
		            </c:forEach>
	            </table>
			</td>	
			</tr>
			--%>
		</table>
		</td>
	</tr>
    </c:if> 
    
</table>	
		
<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->

