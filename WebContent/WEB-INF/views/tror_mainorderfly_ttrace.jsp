<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>
<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTror.jsp" />
<!-- =====================end header ==========================-->
	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/trorglobal_edit.js?ver=${user.versionEspedsg}"></SCRIPT>	
	<SCRIPT type="text/javascript" src="resources/js/tror_mainorderfly_ttrace.js?ver=${user.versionEspedsg}"></SCRIPT>
	<SCRIPT type="text/javascript" src="resources/js/trorFkeys_flyimport.js?ver=${user.versionEspedsg}"></SCRIPT>
	
	<%-- for dialog popup --%>
	<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
	
	<style type = "text/css">
	.ui-dialog{font-size:10pt;}
	.ui-datepicker { font-size:9pt;}
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
					<font class="tabDisabledLink"><spring:message code="systema.tror.order.faktura.tab"/></font><font class="text12">&nbsp;</font>&nbsp;<font class="text10Orange">F9</font>
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a class="text14" onClick="setBlockUI(this);" href="editNotisblock.do?action=doFetch&subsys=${subSystem}&avd=${recordOrderTrorFly.heavd}&opd=${recordOrderTrorFly.heopd}&sign=${recordOrderTrorFly.hesg}" > 	
					<img style="vertical-align: bottom" src="resources/images/veiledning.png" width="16" height="16" border="0" alt="show messages">
					<font class="tabDisabledLink"><spring:message code="systema.tror.order.notisblock.tab"/></font><font class="text12">&nbsp;</font>&nbsp;<font class="text10Orange">F10</font>
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
				<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
					<a class="text14" onClick="setBlockUI(this);" href="tror_mainorderfly_freightbill_gate.do?dfavd=${recordOrderTrorFly.heavd}&sign=${recordOrderTrorFly.hesg}&dfopd=${recordOrderTrorFly.heopd}">
						<img style="vertical-align: bottom" src="resources/images/fraktbrev.png" width="14" height="14" border="0" alt="show freight doc">
						<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tror.order.fraktbrev.tab"/></font>&nbsp;<font class="text10Orange">F3</font>
					</a>
				</td>
			</c:if>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="12%" valign="bottom" class="tab" align="center" nowrap>
				<img style="vertical-align: bottom" src="resources/images/sort_down.png" width="10" height="10" border="0" alt="show more">
				<font class="tabLink">&nbsp;<spring:message code="systema.tror.order.more.tab"/></font>
			</td>
				
			<td width="80%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>	
		</tr>
	</table>
	</td>
	</tr>
	
	<%-- --------------------------- --%>
	<%-- Validation errors FRONT END --%>
	<%-- --------------------------- --%>
	<spring:hasBindErrors name="record"> <%-- name must equal the command object name in the Controller --%>
	<tr height="5"><td></td></tr>
	<tr>
		<td>
           	<table class="tabThinBorderWhiteWithSideBorders" width="100%" align="left" border="0" cellspacing="0" cellpadding="0">
           	<tr>
			<td valign="bottom" class="textError">					
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
	<%-- Validation errors BACK END --%>
	<%-- -------------------------- --%>
	<c:if test="${not empty model.containerValidationBackend && not empty model.containerValidationBackend.errMsgListFromValidationBackend}">
		<tr>
		<td>
           	<table class="tabThinBorderWhiteWithSideBorders" width="100%" align="left" border="0" cellspacing="0" cellpadding="0">
           	<tr>
			<td valign="bottom" class="textError">					
	            <ul>
	            <c:forEach var="errMsg" items="${model.containerValidationBackend.errMsgListFromValidationBackend}">
	                <li >${errMsg}</li>
	            </c:forEach>
	            </ul>
			</td>
			</tr>
			</table>
		</td>
		</tr>		
	</c:if>
	<%-- -------------------------- --%>
	<%-- Validation errors on model --%>
	<%-- -------------------------- --%>
	<c:if test="${not empty model.errorMessage}">
		<tr>
		<td>
           	<table class="tabThinBorderWhiteWithSideBorders" width="100%" align="left" border="0" cellspacing="0" cellpadding="0">
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
	
	<tr>
		<td>
			<%-- this table wrapper is necessary to apply the css class with the thin border --%>
			<table style="width:100%" id="wrapperTable" class="tabThinBorderWhite" cellspacing="0" border="0" cellpadding="0">
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
			<tr height="20"><td>&nbsp;</td></tr>
	 	    
			<tr> <!-- Second tab row... -->
 	   	 		<td align="center" width="99%">
 					<table class="formFrameHeaderTransparent" width="99%" cellspacing="0" border="0" cellpadding="0">
						<tr height="20"> 
								
								<td width="12%" valign="bottom" class="tabSub" align="center" nowrap>
									<font class="tabLinkMinor">&nbsp;<spring:message code="systema.tror.order.more.tab.tracktrace.subtab"/></font>&nbsp;						
								</td>
								<td width="12%" valign="bottom" class="tabDisabledSub" align="center" nowrap>
									<a id="alinkMoreTrackTraceGeneral" onClick="setBlockUI(this);" href="tror_mainorderfly_ttrace_general.do?ttavd=${recordOrderTrorFly.heavd}&ttopd=${recordOrderTrorFly.heopd}">
										<font class="tabDisabledLinkMinor">&nbsp;<spring:message code="systema.tror.order.more.tab.tracktracegrl.subtab"/></font>&nbsp;					
									</a>
								</td>
								<td width="12%" valign="bottom" class="tabDisabledSub" align="center" nowrap>
									<a id="alinkEdiLogging" onClick="setBlockUI(this);" href="tror_mainorderfly_logging.do?avd=${recordOrderTrorFly.heavd}&sign=${recordOrderTrorFly.hesg}&opd=${recordOrderTrorFly.heopd}">
										<font class="tabDisabledLinkMinor">&nbsp;<spring:message code="systema.tror.order.more.tab.edilog.subtab"/></font>&nbsp;					
									</a>
								</td>
								<td width="12%" valign="bottom" class="tabDisabledSub" align="center" nowrap>
									<a id="alinkArchive" onClick="setBlockUI(this);" href="tror_mainorderfly_archive.do?avd=${recordOrderTrorFly.heavd}&sign=${recordOrderTrorFly.hesg}&opd=${recordOrderTrorFly.heopd}">
										<font class="tabDisabledLinkMinor">&nbsp;<spring:message code="systema.tror.order.more.tab.archive.subtab"/></font>&nbsp;					
									</a>
								</td>
								<td width="12%" valign="bottom" class="tabDisabledSub" align="center" nowrap>
									<a id="alinkBudget" onClick="setBlockUI(this);" href="tror_mainorderfly_budget.do?avd=${recordOrderTrorFly.heavd}&sign=${recordOrderTrorFly.hesg}&opd=${recordOrderTrorFly.heopd}">
										<font class="tabDisabledLinkMinor">&nbsp;<spring:message code="systema.tror.order.budget.tab"/></font>&nbsp;					
									</a>
								</td>
							 	<td width="80%" class="tabFantomSpace" align="center" nowrap></td>
						</tr>
					</table>
				</td>
 	   	 	</tr> <!-- End second tab row -->
 	   	 	
 	   	 	<tr>
				<td align="center" width="99%">
					<table class="tabThinBorderWhite" width="99%" cellspacing="0" border="0">
 	   	 				<tr height="20px"><td ></td></tr>
						<tr>
						<td width="40%" >	
							<%--
							<table align="left" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
						 		<tr height="15">
						 			<td colspan="10" class="text12White" align="left" >
						 				<b>&nbsp;&nbsp;Tidsloggning avgang/ankomst på oppdrag</b>
					 				</td>
				 				</tr>
			 				</table>
			 				 --%>
							<table width="50%" align="left" border="0" cellspacing="0" cellpadding="0">
						 		<tr height="5"><td class="text" align="left"></td></tr>
						 		<tr height="15">
						 			<td>
								 		<table class="formFrameHeader" width="100%" border="0" cellspacing="0" cellpadding="0">
								 		<tr>
								 			<td class="text12White" align="left" >
								 				<b>&nbsp;&nbsp;Tidsloggning avgang/ankomst på oppdrag</b>
							 				</td>
						 				</tr>
						 				</table>
					 				</td>
				 				</tr>
				 				<tr height="1px"><td ></td></tr>
						 		<tr>
							 		<td>
								 		<table class="formFrame" width="100%" border="0" cellspacing="0" cellpadding="0">
								 		<tr>
									 		<td>
									 		<table width="90%" >
									 			<tr height="2px"><td class="text" align="left"></td></tr>
										 		<tr>
										 			
										 			<td class="text12" align="left" title="todo"><b>&nbsp;<spring:message code="systema.tror.more.ttrace.label.avgang"/></b></td>
										 			<td class="text12" align="left" title="todo">&nbsp;<spring:message code="systema.tror.more.ttrace.label.etd"/></td>
										 			<td class="text12" align="left"><input type="text" class="inputTextMediumBlue"  name="todoEtd" id="todoEtd" size="10" maxlength="8" value=''></td>
										 			<td class="text12" align="left"><input type="text" class="inputTextMediumBlue"  name="todoEtdTid" id="todoEtdTid" size="5" maxlength="4" value=''></td>
										 			<td class="text12" align="left" title="todo">&nbsp;<spring:message code="systema.tror.more.ttrace.label.atd"/></td>
										 			<td class="text12" align="left"><input type="text" class="inputTextMediumBlue"  name="todoAtd" id="todoAtd" size="10" maxlength="8" value=''></td>
										 			<td class="text12" align="left"><input type="text" class="inputTextMediumBlue"  name="todoAtdTid" id="todoAtaTid" size="5" maxlength="4" value=''></td>
										 			
										        </tr>
										        <tr>
										        	<td class="text12" align="left" title="todo"><b>&nbsp;<spring:message code="systema.tror.more.ttrace.label.ankomst"/></b></td>
										 			<td class="text12" align="left" title="todo">&nbsp;<spring:message code="systema.tror.more.ttrace.label.eta"/></td>
										 			<td class="text12" align="left"><input type="text" class="inputTextMediumBlue"  name="todoEta" id="todoEta" size="10" maxlength="8" value=''></td>
										 			<td class="text12" align="left"><input type="text" class="inputTextMediumBlue"  name="todoEtaTid" id="todoEtaTid" size="5" maxlength="4" value=''></td>
										 			<td class="text12" align="left" title="todo">&nbsp;<spring:message code="systema.tror.more.ttrace.label.ata"/></td>
										 			<td class="text12" align="left"><input type="text" class="inputTextMediumBlue"  name="todoAta" id="todoAta" size="10" maxlength="8" value=''></td>
										 			<td class="text12" align="left"><input type="text" class="inputTextMediumBlue"  name="todoAtaTid" id="todoAtaTid" size="5" maxlength="4" value=''></td>
										 			
										        </tr>
										        <tr height="2px"><td class="text" align="left"></td></tr>
									        </table>
									        </td>
								        </tr>
								        </table>
								        
							        </td>
						        </tr>
						        <tr height="10px"><td class="text" align="left"></td></tr>
		        	        </table>
						</td>
						</tr>	
						<tr height="10px"><td class="text" align="left"></td></tr>
					</table>
				</td>
			</tr>											
 	   	 	<tr height="3"><td></td></tr>
 			</table>
		</td>
	</tr>
	
	<tr height="10"><td ></td></tr>
			
	</table>
		
<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->
