<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===============================-->
<jsp:include page="/WEB-INF/views/headerTror.jsp" />
<!-- =====================end header ==============================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/jquery.calculator.js"></SCRIPT>
	<SCRIPT type="text/javascript" src="resources/js/jquery-ui-timepicker-addon.js"></SCRIPT>
	<SCRIPT type="text/javascript" src="resources/js/transportdispglobal_edit.js?ver=${user.versionEspedsg}"></SCRIPT>			
	<SCRIPT type="text/javascript" src="resources/js/tror_mainorderfly_freightbill_list.js?ver=${user.versionEspedsg}"></SCRIPT>
	<SCRIPT type="text/javascript" src="resources/js/trorFkeys_flyimport.js?ver=${user.versionEspedsg}"></SCRIPT>
	
	<%-- for dialog popup --%>
	<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
	
	<style type = "text/css">
	.ui-dialog{font-size:10pt;}
	.ui-datepicker { font-size:9pt;}
	.ui-timepicker-div .ui-widget-header { margin-bottom: 8px; }
	.ui-timepicker-div dl { text-align: left; }
	.ui-timepicker-div dl dt { float: left; clear:left; padding: 0 0 0 5px; }
	.ui-timepicker-div dl dd { margin: 0 10px 10px 40%; }
	.ui-timepicker-div td { font-size: 90%; }
	.ui-tpicker-grid-label { background: none; border: none; margin: 0; padding: 0; }
	
	.ui-timepicker-rtl{ direction: rtl; }
	.ui-timepicker-rtl dl { text-align: right; padding: 0 5px 0 0; }
	.ui-timepicker-rtl dl dt{ float: right; clear: right; }
	.ui-timepicker-rtl dl dd { margin: 0 40% 10px 10px; }
	
	</style>
	
<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
	<tr>
	<td>	
	<%-- tab container component --%>
	<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
		<%-- This part right here allows for the dynamic allocation of a JSP depending on whether it is IMPORT or EXPORT --%>
		<c:choose>
			<c:when test="${recordOrderTrorFly.heur == 'C'}">
				<c:set var = "tabLinkJsp" scope = "request" value = "mainorderflyimport"/>	
			</c:when>
			<c:otherwise>
				<c:if test="${recordOrderTrorFly.heur == 'D'}">
					<c:set var = "tabLinkJsp" scope = "request" value = "mainorderflyexport"/>
				</c:if>
			</c:otherwise>
		</c:choose>
		
		<tr height="2"><td></td></tr>
		<tr height="25"> 
			<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a class="text14" onClick="setBlockUI(this);" href="tror_mainorderlist.do?action=doFind&sign=${recordOrderTrorFly.hesg}" > 	
					<img style="vertical-align:middle;" src="resources/images/bulletGreen.png" width="6px" height="6px" border="0" alt="open orders">
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tror.orderlist.tab"/></font>&nbsp;<font class="text10Orange">F2</font>
				</a>
			</td>
			
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a class="text14" onClick="setBlockUI(this);" href="tror_<c:out value="${tabLinkJsp}"/>.do?action=doFetch&heavd=${recordOrderTrorFly.heavd}&heopd=${recordOrderTrorFly.heopd}" > 	
					<c:choose>
						<c:when test="${recordOrderTrorFly.heur == 'C'}">
							<img style="vertical-align:middle;" src="resources/images/airplaneYellow.png" width="18px" height="18px" border="0" alt="create new">
						</c:when>
						<c:otherwise>
							<c:if test="${recordOrderTrorFly.heur == 'D'}">
								<img style="vertical-align:middle;" src="resources/images/airplaneBlue.png" width="18px" height="18px" border="0" alt="create new">
							</c:if>
						</c:otherwise>
					</c:choose>
				<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tror.order.tab"/>&nbsp;${recordOrderTrorFly.heavd}/${recordOrderTrorFly.heopd}</font>&nbsp;<font class="text10Orange">F4</font>
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a class="text14" onClick="setBlockUI(this);" href="tror_<c:out value="${tabLinkJsp}"/>_invoice.do?action=doFetch&heavd=${recordOrderTrorFly.heavd}&heopd=${recordOrderTrorFly.heopd}" > 	
					<img style="vertical-align: bottom" src="resources/images/invoice.png" width="16" height="16" border="0" alt="show invoice">
					<font class="tabDisabledLink"><spring:message code="systema.tror.order.faktura.tab"/></font>&nbsp;<font class="text10Orange">F9</font>
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a class="text14" onClick="setBlockUI(this);" href="editNotisblock.do?action=doFetch&subsys=${subSystem}&avd=${recordOrderTrorFly.heavd}&opd=${recordOrderTrorFly.heopd}&sign=${recordOrderTrorFly.hesg}" > 	
					<img style="vertical-align: bottom" src="resources/images/veiledning.png" width="16" height="16" border="0" alt="show messages">
					<font class="tabDisabledLink"><spring:message code="systema.tror.order.notisblock.tab"/></font>&nbsp;<font class="text10Orange">F10</font>
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a class="text14" onClick="setBlockUI(this);" href="tror_mainorderfly_frisokvei.do?action=doFetch&avd=${recordOrderTrorFly.heavd}&sign=${recordOrderTrorFly.hesg}&opd=${recordOrderTrorFly.heopd}">
					<img style="vertical-align: bottom" src="resources/images/lightbulb.png" width="14" height="14" border="0" alt="show log">
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tror.order.frisokvei.tab"/></font>&nbsp;<font class="text10Orange">F7</font>
				</a>
			</td>

			<c:if test="${recordOrderTrorFly.hepk5 == 'J' || recordOrderTrorFly.hepk5 == 'P'}">
				<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
				<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
					<a class="text14" onClick="setBlockUI(this);" href="tror_mainorderfly_airfreightbill_gate.do?avd=${recordOrderTrorFly.heavd}&sign=${recordOrderTrorFly.hesg}&opd=${recordOrderTrorFly.heopd}">
						<img style="vertical-align: bottom" src="resources/images/pen.png" width="16" height="16" border="0" alt="Awb">
						<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tror.order.flyfraktbrev.tab"/></font>&nbsp;<font class="text10Orange">F8</font>
					</a>
				</td>
			</c:if>
			<c:if test="${recordOrderTrorFly.hepk1 == 'J' || recordOrderTrorFly.hepk1 == 'P'}">
				<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
				<td width="12%" valign="bottom" class="tab" align="center" nowrap>
					<img style="vertical-align: bottom" src="resources/images/fraktbrev.png" width="14" height="14" border="0" alt="show freight doc">
					<font class="tabLink">&nbsp;<spring:message code="systema.tror.order.fraktbrev.tab"/></font>&nbsp;<font class="text10Orange">F3</font>
				</td>
			</c:if>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a class="text14" onClick="setBlockUI(this);" href="tror_mainorderfly_ttrace.do?avd=${recordOrderTrorFly.heavd}&sign=${recordOrderTrorFly.hesg}&opd=${recordOrderTrorFly.heopd}">
					<img style="vertical-align: bottom" src="resources/images/sort_down.png" width="10" height="10" border="0" alt="show more">
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tror.order.more.tab"/></font>
				</a>
			</td>
				
			<td width="80%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>	
		</tr>
	</table>
	</td>
	</tr>
		
   	<%-- ---------------------- --%>
   	<%-- LIST of existent ITEMs --%>
   	<%-- ---------------------- --%>
   	<tr>
		<td>
			<table width="100%" id="wrapperTable" class="tabThinBorderWhite" cellspacing="0" border="0" cellpadding="0">
				<%--for F-Keys shortcuts. Used only in trorFkeys_...js --%>
				<input type="hidden" name="fkeysavd" id="fkeysavd" value='${recordOrderTrorFly.heavd}'>
				<input type="hidden" name="fkeysopd" id="fkeysopd" value='${recordOrderTrorFly.heopd}'>
				<input type="hidden" name="fkyessign" id="fkyessign" value='${recordOrderTrorFly.hesg}'>
				<c:choose>
					<c:when test="${recordOrderTrorFly.heur == 'C'}">
						<input type="hidden" name="fkyessubsys" id="fkyessubsys" value='mainorderflyimport'>
					</c:when>
					<c:otherwise>
						<c:if test="${recordOrderTrorFly.heur == 'D'}">
							<input type="hidden" name="fkyessubsys" id="fkyessubsys" value='mainorderflyexport'>
						</c:if>
					</c:otherwise>
				</c:choose>
				
				
   				<%-- separator --%>
       			<tr height="10"><td></td></tr> 
				
				<tr>
					<td class="text11" >
					<%-- this table wrapper is needed for the datatables width --%>
					<table width="90%" cellspacing="0" border="0">
						<tr>
							<td class="text11" >
								<table id="tblMain" class="display compact cell-border" >
									<thead>
									<tr style="background-color:#DDDDDD">
										<th align="center" width="2%" class="text12" >&nbsp;<span title="todo">Endre&nbsp;</span></th>
										<th align="center" class="text12" width="2%" >&nbsp;<span title="todo">Lnr.</span></th>  
										<th align="center" width="5%" class="text12" >&nbsp;<span title="todo">Fraktbrevnr.&nbsp;</span></th>
										<th class="text12" >&nbsp;<span title="dfnavm">Mottaker</span></th>  
										<th class="text12" >&nbsp;<span title="dfad3m">Poststed</span></th> 
										<th align="center" class="text12" width="2%" >&nbsp;<span title="dfst">Status</span></th> 
										<th class="text12" width="2%" >&nbsp;<span title="dfnt">Antall</span></th>
										<th class="text12" width="5%" >&nbsp;<span title="dfvs">Vareslag</span></th>
										<th class="text12" width="2%" >&nbsp;<span title="dfvkt">Vekt</span></th>
										<th class="text12" >&nbsp;<span title="dfnavs">Transportør</span></th>
					        			<th align="center" width="2%" class="text12" >&nbsp;Slett&nbsp;</th>
					               </tr> 
					               </thead>
					               <tbody>
				 					  <c:forEach items="${model.list}" var="record" varStatus="counter">    
							               <tr class="tableRow" height="20" >
							                
							               <td align="center" width="2%" class="text11" >
							     				<a id="recordUpdate_${record.dffbnr}" onClick="setBlockUI(this);" href="tror_mainorderfly_freightbill_edit.do?dfavd=${record.dfavd}&sign=${record.dfsg}&dfopd=${record.dfopd}&dffbnr=${record.dffbnr}">
							     					<img title="Update" style="vertical-align:bottom;" src="resources/images/update.gif" border="0" alt="update">&nbsp;						               				
					               				</a>
						               	   </td>
						               	   <td align="center" class="text11" >${record.dffbnr}</td>
						               	   <td align="center" width="4%" class="text11" align="left">${record.df1004}</td>
							               <td class="text11" >${record.dfnavm}</td>
						               	   <td class="text11" >${record.dfad3m}</td>
						               	   <td align="center" class="text11" width="2%" >${record.dfst}</td>
						               	   <td class="text11" width="2%" >${record.dfnt}</td>
						               	   <td class="text11" width="5%" >${record.dfvs}</td>
						               	   <td class="text11" width="2%" >${record.dfvkt}</td>
						               	   <td class="text11" >${record.dfnavs}</td>
							               
							               <%-- DELETE cell --%>							           
							               <td width="2%" class="text11" align="center">
							               	   <c:if test="${not empty record.dffbnr && record.dfst != 'P'}"> <%-- status P = not removable --%>
							                   		<a style="cursor:pointer;" id="avd_${record.dfavd}@opd_${record.dfopd}@id_${record.dffbnr}@sign_${record.dfsg}" onClick="doDeleteItemLine(this);" tabindex=-1 >
									               		<img valign="bottom" src="resources/images/delete.gif" border="0" alt="remove">
									               	</a>&nbsp;
									               	
								               	</c:if>
					               		  </td> 
							            </tr>
								        
								        </c:forEach>
						           </tbody>
						        </table>
							</td>
						</tr>
					</table>
				</td>
				</tr>
				
				<tr height="5"><td></td></tr>
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
				
				<%-- -------------------------- --%>
				<%-- Validation errors on model --%>
				<%-- -------------------------- --%>
				<c:if test="${not empty model.errorMessage}">
					<tr>
					<td>
			           	<table class="tabThinBorderWhiteWithSideBorders" width="90%" align="left" border="0" cellspacing="0" cellpadding="0">
			           	<tr>
						<td valign="bottom" class="textError">					
				            <ul>
				            	<li >${model.errorMessage}</li>
				            </ul>
						</td>
						</tr>
						</table>
					</td>
					</tr>		
				</c:if>
				
		
				<tr height="30"><td></td></tr>

			</table>		
		</td>
	</tr>
	
</table>
	
	
		
<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->

