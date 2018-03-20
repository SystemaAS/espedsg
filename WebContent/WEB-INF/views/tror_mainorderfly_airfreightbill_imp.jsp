<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>
<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTror.jsp" />
<!-- =====================end header ==========================-->
	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/trorglobal_edit.js?ver=${user.versionEspedsg}"></SCRIPT>	
	<SCRIPT type="text/javascript" src="resources/js/tror_mainorderfly_airfreightbill_imp.js?ver=${user.versionEspedsg}"></SCRIPT>
	<SCRIPT type="text/javascript" src="resources/js/trorFkeys_flyimport.js?ver=${user.versionEspedsg}"></SCRIPT>
	
	<%-- for dialog popup --%>
	<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
	
	<style type = "text/css">
	.ui-dialog{font-size:10pt;}
	.ui-datepicker { font-size:9pt;}
	</style>
	

<form action="tror_mainorderfly_airfreightbill_imp_edit.do" name="formRecord" id="formRecord" method="post">
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
							<img style="vertical-align:middle;" src="resources/images/airplaneYellow.png" width="18px" height="18px" border="0" alt="update">
						</c:when>
						<c:otherwise>
							<c:if test="${recordOrderTrorFly.heur == 'D'}">
								<img style="vertical-align:middle;" src="resources/images/airplaneBlue.png" width="18px" height="18px" border="0" alt="update">
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
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a class="text14" onClick="setBlockUI(this);" href="tror_mainorderfly_frisokvei.do?action=doFetch&avd=${recordOrderTrorFly.heavd}&sign=${recordOrderTrorFly.hesg}&opd=${recordOrderTrorFly.heopd}">
					<img style="vertical-align: bottom" src="resources/images/lightbulb.png" width="14" height="14" border="0" alt="show log">
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tror.order.frisokvei.tab"/></font>&nbsp;<font class="text10Orange">F7</font>
				</a>
			</td>			
			<c:if test="${recordOrderTrorFly.hepk5 == 'J' || recordOrderTrorFly.hepk5 == 'P'}">
				<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
				<td width="12%" valign="bottom" class="tab" align="center" nowrap>
					<img style="vertical-align: bottom" src="resources/images/pen.png" width="16" height="16" border="0" alt="Awb">
					<font class="tabLink">&nbsp;<spring:message code="systema.tror.order.flyfraktbrev.tab"/></font>&nbsp;<font class="text10Orange">F8</font>
				</td>
			</c:if>	
			<c:if test="${recordOrderTrorFly.hepk1 == 'J' || recordOrderTrorFly.hepk1 == 'P'}">		
				<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabLink">&nbsp;</font></td>
				<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
					<a class="text14" onClick="setBlockUI(this);" href="tror_mainorderfly_freightbill_gate.do?dfavd=${recordOrderTrorFly.heavd}&sign=${recordOrderTrorFly.hesg}&dfopd=${recordOrderTrorFly.heopd}">
						<img style="vertical-align:bottom;" src="resources/images/fraktbrev.png" width="14" height="14" border="0" alt="edit">
						<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tror.order.fraktbrev.tab"/></font>&nbsp;<font class="text10Orange">F3</font>
					</a>					
				</td>
			</c:if>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a class="text14" onClick="setBlockUI(this);" href="tror_mainorderfly_ttrace.do?subsys=${subSystem}&avd=${recordOrderTrorFly.heavd}&sign=${recordOrderTrorFly.hesg}&opd=${recordOrderTrorFly.heopd}">
					<img style="vertical-align: bottom" src="resources/images/sort_down.png" width="10" height="10" border="0" alt="show more">
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tror.order.more.tab"/></font>
				</a>
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
		<td colspan="10">
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
		<td colspan="10">
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
			<table width="100%" id="wrapperTable" class="tabThinBorderWhite" cellspacing="0">
				<input type="hidden" name="applicationUser" id="applicationUser" value='${user.user}'>
				<input type="hidden" name="action" id="action" value='${model.action}'>
				<input type="hidden" name="imavd" id="imavd" value='${recordOrderTrorFly.heavd}'>
				<input type="hidden" name="imopd" id="imopd" value='${recordOrderTrorFly.heopd}'>
				<input type="hidden" name="sign" id="sign" value='${recordOrderTrorFly.hesg}'>
					
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
				
			<tr height="10"><td>&nbsp;</td></tr>
			
			<tr>
           		<td>
        			<table class="dashboardFrameHeader" width="50%" align="left" border="0" cellspacing="0" cellpadding="0">
			 		<tr height="15">
			 			
			 			<td align="left" class="text14White">
							&nbsp;<img style="vertical-align:bottom;" src="resources/images/complete-icon.png" width="16" hight="16" border="0" alt="edit">	
							&nbsp;<spring:message code="systema.tror.flyfraktbrev.import.dokefim.form.update.label.title"/>&nbsp;&nbsp;
		 				</td>
			 				
	 				</tr>
 					</table>
           		</td>
            </tr>

            <tr >
           		<td>
            		<table width="50%" class="tableBorderWithRoundCorners3D_RoundOnlyOnBottom" align="left" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="5"><td ></td></tr>
				 		
						<tr>
				 			<td valign="top">
				 			 <table align="center" class="tableBorderWithRoundCornersLightYellow" cellspacing="1" cellpadding="1" border="0">
						 		<tr height="10"><td ></td></tr>
						 		<tr>
					 				<td class="text12">&nbsp;<span title="imlop"><spring:message code="systema.tror.flyfraktbrev.import.dokefim.form.update.label.lopenr"/></span></td>
					 				<td class="text12">&nbsp;<span title="trsta4"><spring:message code="systema.tror.flyfraktbrev.import.dokefim.form.update.label.productCode"/></span>
					 					<a tabindex="-1" id="prodKodeIdLink" >
											<img align="bottom" style="cursor:pointer;" src="resources/images/find.png" height="13px" width="13px" border="0" alt="search">
										</a>
					 				</td>
					 			</tr>
					 			<tr>	
				 					<td class="text12"><input readonly tabindex=-1 type="text" class="inputTextReadOnly" name="imlop" id="imlop" size="4" maxlength="3" value="${model.record.imlop}"></td>
				 					<td class="text12"><input type="text" class="inputTextMediumBlueMandatoryField" required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" name="trsta4" id="trsta4" size="2" maxlength="1" value="${model.record.trsta4}"></td>
				 				</tr>
					 			<tr>	
					 				<td class="text12">&nbsp;<span title="hegn5_7"><spring:message code="systema.tror.flyfraktbrev.import.dokefim.form.update.label.carrier"/></span>
					 					<a tabindex="-1" id="flyselskapIdLink" >
											<img align="bottom" style="cursor:pointer;" src="resources/images/find.png" height="13px" width="13px" border="0" alt="search">
										</a>	 	
					 				</td>
					 			</tr>
								<tr>	
				 					<td class="text12" >
				 						<c:choose>
					 						<c:when test="${ not empty model.record.hegn && fn:length(model.record.hegn)>8}">
					 							<input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="hegn5_7" id="hegn5_7" size="4" maxlength="3" value="${fn:substring(model.record.hegn, 4, 7)}">
					 						</c:when>
					 						<c:otherwise>
					 							<input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="hegn5_7" id="hegn5_7" size="4" maxlength="3" value="">
					 						</c:otherwise>
				 						</c:choose>
				 					</td>
				 				</tr>
				 				<tr>	
					 				<td class="text12">&nbsp;<span title="hesdf">&nbsp;<spring:message code="systema.tror.flyfraktbrev.import.dokefim.form.update.label.airportOfDeparture"/></span>
					 					<a tabindex="-1" id="flyplassFromIdLink" >
											<img align="bottom" style="cursor:pointer;" src="resources/images/find.png" height="13px" width="13px" border="0" alt="search">
										</a>	
					 				</td>
					 				<td class="text12">&nbsp;<span title="hesdt">&nbsp;<spring:message code="systema.tror.flyfraktbrev.import.dokefim.form.update.label.airportOfDestination"/></span>
					 					<a tabindex="-1" id="flyplassToIdLink" >
											<img align="bottom" style="cursor:pointer;" src="resources/images/find.png" height="13px" width="13px" border="0" alt="search">
										</a>	
					 				</td>
					 				
					 			</tr>
					 			<tr>	
				 					<td class="text11" >
				 						<input type="text" class="inputTextMediumBlueMandatoryField" required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" name="hesdf" id="hesdf" size="6" maxlength="5" value="${model.record.hesdf}">
								 		&nbsp;<input type="text" readonly class="inputTextReadOnly" name="helka" id="helka" size="3" maxlength="2" value="${model.record.helka}">
								 	</td>
								 	
								 	<td class="text11" >
				 						<input type="text" class="inputTextMediumBlueMandatoryField" required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')"  name="hesdt" id="hesdt" size="6" maxlength="5" value="${model.record.hesdt}">
				 						&nbsp;<input type="text" readonly class="inputTextReadOnly" name="helkk" id="helkk" size="3" maxlength="2" value="${model.record.helkk}">
								 	</td>
								</tr> 
								
								<tr height="25"><td ></td></tr>
				 				<tr>	
					 				<td class="text12">&nbsp;<span title="hegn"><spring:message code="systema.tror.flyfraktbrev.import.dokefim.form.update.label.awbnr.manualOrAuto"/></span></td>
					 				<td class="text12">&nbsp;<span title="hefr"><spring:message code="systema.tror.flyfraktbrev.import.dokefim.form.update.label.incoterms"/></span>
					 					<a tabindex="-1" id="incotermsIdLink" >
											<img align="bottom" style="cursor:pointer;" src="resources/images/find.png" height="13px" width="13px" border="0" alt="search">
										</a>	
					 				</td>
					 				<td class="text12">&nbsp;<span title="domoms"><spring:message code="systema.tror.flyfraktbrev.import.dokefim.form.update.label.vat"/></span></td>
					 				
		 						</tr>
		 						<tr>	
				 					<td class="text12" >
				 						<input type="text" class="inputTextMediumBlue" name="hegn" id="hegn" size="12" maxlength="11" value="${model.record.hegn}">
				 					</td>
				 					<td class="text12">
				 						<input type="text" class="inputTextMediumBlueMandatoryField" required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" name="hefr" id="hefr" size="4" maxlength="3" value="${model.record.hefr}">
				 					</td>
				 					<td class="text12">
				 						<select  class="inputTextMediumBlueMandatoryField" required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" name="domoms" id="domoms" >
					 						<option value="J"<c:if test="${model.record.domoms == 'J'}"> selected </c:if> >Ja</option>
						 				  	<option value="N"<c:if test="${model.record.domoms == 'N'}"> selected </c:if> >Nei</option>
						 				  	
										</select>
				 					</td>
		 						</tr>	
		 						<tr>	
					 				<td class="text12">&nbsp;<span title="hekns/ownHekns"><spring:message code="systema.tror.flyfraktbrev.import.dokefim.form.update.label.shipper"/></span>
					 					<a tabindex="-1" id="trorAvsenderImportIdLink" >
											<img align="bottom" style="cursor:pointer;" src="resources/images/find.png" height="13px" width="13px" border="0" alt="search">
										</a>
					 				</td>
		 						</tr>
		 						<tr>	
				 					<td class="text12" >
				 						<input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlueMandatoryField" required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" name="hekns" id="hekns" size="9" maxlength="8" value="${model.record.hekns}">
				 						
				 					</td>
				 					<td colspan="3" class="text12" >
				 						<input type="text" class="inputTextMediumBlue" name="ownHekns" id="ownHekns" size="25" maxlength="20" value="${model[model.record.hekns]}">
				 					</td>
		 						</tr>	
				 				<tr height="15"><td ></td></tr>
				 				<tr>
	 				 				<td colspan="5" align="right">
						 				<input class="inputFormSubmit" type="submit" name="submit" id="submit" value='<spring:message code="systema.tror.submit.save"/>'>
						 				&nbsp;<input class="inputFormSubmit" type="button" name="onwardButton" id="onwardButton" value='Videre'>
										&nbsp;&nbsp;<input class="inputFormSubmitGray" type="button" name="backToFlyfraktbrevGateButton" id="backToFlyfraktbrevGateButton" value='Tilbake til flyfraktb.lista'>
										
									</td>
								</tr>
							 </table>
						 	</td>
					 	</tr>
					 	<tr height="5"><td ></td></tr>
					 	
					 </table>
					 
				</td>
			</tr>
			
			<tr height="10"><td ></td></tr>
		</table>
		</td>
	</tr>
</table>
</form>
<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->
