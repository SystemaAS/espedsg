<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header =====================================-->
<jsp:include page="/WEB-INF/views/headerTvinnSadChildWindows.jsp" />
<!-- =====================end header ====================================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
	specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tvinnsadimport_logging_ediftplog.js?ver=${user.versionEspedsg}"></SCRIPT>
	
	<table width="95%"  class="text11" cellspacing="0" border="0" cellpadding="0">
		<tr>
			<td colspan="3" class="text14Bold">&nbsp;&nbsp;&nbsp;
						<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
							<c:choose>
								<c:when test="${model.level == 'EDISS'}">
									<tr height="2"><td></td></tr>
									<tr height="25"> 
										<td width="18%" valign="bottom" class="tab" >
											<font class="tabLink">&nbsp;Level 1</font>
										</td>
										<td width="1px" class="tabFantomSpace" align="center"><font class="tabDisabledLink">&nbsp;</font></td>
										<td width="18%" valign="bottom" class="tabDisabled" align="center" nowrap>
											<a id="alinkHeader" style="display:block;" href="tvinnsadimport_logging_ediftplog2.do?sssn=${Xrecord.msn}&etc=todo">
												<font class="tabDisabledLink">&nbsp;Level 2</font>
											</a>
										</td>
										<td width="66%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
									</tr>
								</c:when>
								<c:otherwise>
									<tr height="2"><td></td></tr>
									<tr height="25"> 
										<td width="1px" class="tabFantomSpace" align="center"><font class="tabDisabledLink">&nbsp;</font></td>
										<td width="18%" valign="bottom" class="tabDisabled" align="center" nowrap>
											<a id="alinkHeader" style="display:block;" href="tvinnsadimport_logging_ediftplog.do?sssn=${Xrecord.msn}">
												<font class="tabDisabledLink">&nbsp;Level 1</font>
											</a>
										</td>
										<td width="18%" valign="bottom" class="tab" >
											<font class="tabLink">&nbsp;Level 2</font>
										</td>
										<td width="66%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
									</tr>
								</c:otherwise>
							</c:choose>
						</table>
			</td>
		</tr>
		<tr>
		<td valign="top">
		
		  		<%-- this container table is necessary in order to separate the datatables element and the frame above, otherwise
			 	the cosmetic frame will not follow the whole datatable grid including the search field... --%>
				<table class="tabThinBorderWhite" id="containerdatatableTable" cellspacing="2" align="left" width="100%" >
					<%--
					<tr>
					<td>
						<table>
						<form name="tvinnsadImportTolltariffForm" id="tvinnsadImportTolltariffForm" action="tvinnsadimport_edit_items_childwindow_tolltariff.do?action=doInit" method="post">
						<tr>
							<td class="text11">&nbsp;Varenr.</td>
							<td class="text11">&nbsp;<input type="text" class="inputText" name="vkod" id="vkod" size="10" maxlength="10" value="${model.vkod}"></td>
							<%-- 
							<td class="text11">&nbsp;Beskrivning</td>
							<td class="text11">&nbsp;<input type="text" class="inputText" name="tekst" id="tekst" size="30" maxlength="50" value="${model.tekst}"></td>
							
							<td class="text11">&nbsp;</td>
	           				<td align="right">&nbsp;<input class="inputFormSubmit" type="submit" name="submit" value='<spring:message code="search.label"/>'></td>
		           		</tr>
		           		</form>
		           		</table>
					</td>
					</tr>
					 --%> 
					<tr height="10"><td></td></tr>
					
					<tr class="text12" >
					<td class="ownScrollableSubWindowDynamicWidthHeight" width="100%" style="height:30em;">
					<%-- this is the datatables grid (content)--%>
					<table id="mainList" class="display compact cell-border" width="100%" >
						<thead>
						<tr style="background-color:#EEEEEE">
							<th class="text11" title="sssn">&nbsp;Interch.nr&nbsp;</th>
		               	   	<th class="text11" title="ssdt">&nbsp;Date&nbsp;</th>
		                    <th class="text11" title="sstm">&nbsp;Time&nbsp;</th>
		                    <th class="text11" title="ssst">&nbsp;Status&nbsp;</th>
		                    <th class="text11" title="sstext">&nbsp;Description&nbsp;</th>
		                </tr> 
		                </thead>
		                
		                <tbody>
		                <c:forEach var="record" items="${model.list}" varStatus="counter">    
			               <tr class="text11">
			               <td style="cursor:pointer;" class="text11MediumBlue" id="vkod${Xrecord.tatanr}@text${Xrecord.beskr1}" >
			               		<img title="select" valign="bottom" src="resources/images/bebullet.gif" border="0" alt="Interchange nr">&nbsp;${record.sssn}
			               </td>
		               	   <td class="text11">&nbsp;${record.ssdt}</td>
		               	   <td class="text11">&nbsp;${record.sstm}</td>
		               	   <td class="text11">&nbsp;${record.ssst}</td>
		               	   <td class="text11">&nbsp;${record.sstext}</td>
		               	   
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
