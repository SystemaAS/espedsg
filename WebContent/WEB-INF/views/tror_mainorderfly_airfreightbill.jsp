<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>
<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTror.jsp" />
<!-- =====================end header ==========================-->
	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/trorglobal_edit.js?ver=${user.versionEspedsg}"></SCRIPT>	
	<SCRIPT type="text/javascript" src="resources/js/tror_mainorderfly_airfreightbill.js?ver=${user.versionEspedsg}"></SCRIPT>
	<SCRIPT type="text/javascript" src="resources/js/trorFkeys_flyimport.js?ver=${user.versionEspedsg}"></SCRIPT>
	
	<%-- for dialog popup --%>
	<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
	
	<style type = "text/css">
	.ui-dialog{font-size:10pt;}
	.ui-datepicker { font-size:9pt;}
	</style>
	

<form action="tror_mainorderfly_airfreightbill_edit.do" name="formRecord" id="formRecord" method="post">
	
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
			<table style="width:100%" id="wrapperTable" class="tabThinBorderWhite" cellspacing="0">
			<tr height="10"><td>&nbsp;</td></tr> 
			<%-- FORM HEADER --%>
	 		<tr>
           		<td>
        			<table class="dashboardFrameHeader" width="98%" align="left" border="0" cellspacing="0" cellpadding="0">
			 		<tr height="15">
			 			
			 			<c:choose>
				 			<c:when test="${not empty model.record.dflop}">
					 			<td align="left" class="text14White">
									&nbsp;<img style="vertical-align:bottom;" src="resources/images/complete-icon.png" width="16" hight="16" border="0" alt="edit">	
									&nbsp;<spring:message code="systema.tror.flyfraktbrev.form.update.label.title"/>
									&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="systema.tror.flyfraktbrev.form.update.label.mawb"/>:&nbsp;<font style="color: yellow"><b>${recordFlyfraktbrevImportHeaderTrorFly.hegn}</b></font>
									&nbsp;&nbsp;&nbsp;&nbsp;<font style="color: black"><b>${model.record.dfavd} / ${model.record.dfopd} / ${model.record.dflop} / ${model.record.dfsg}</b></font>
									&nbsp;&nbsp;
									
				 				</td>
			 				</c:when>
			 				<c:otherwise>
			 					<td align="left" class="text14White">
									&nbsp;<img style="vertical-align:bottom;" src="resources/images/bulletRed.png" width="16" hight="16" border="0" alt="edit">	
									&nbsp;<spring:message code="systema.tror.flyfraktbrev.form.update.label.title"/>&nbsp;&nbsp;&nbsp;&nbsp;<font style="color: yellow"><b><spring:message code="systema.tror.fraktbrev.form.update.label.title.notExist"/></b></font>
									&nbsp;&nbsp;
				 				</td>
			 				</c:otherwise>
		 				</c:choose>
	 				</tr>
 					</table>
           		</td>
            </tr>
            <%-- FORM DETAIL --%>
            <tr ondrop="drop(event)" ondragover="allowDrop(event)" >
            		<td>
            		<input type="hidden" name="applicationUser" id="applicationUser" value='${user.user}'>
					<input type="hidden" name="action" id="action" value='${model.action}'>
					<input type="hidden" name="dfavd" id="dfavd" value='${recordOrderTrorFly.heavd}'>
					<input type="hidden" name="dfopd" id="dfopd" value='${recordOrderTrorFly.heopd}'>
					<input type="hidden" name="dflop" id="dflop" value='${recordFlyfraktbrevImportHeaderTrorFly.imlop}'>
					<input type="hidden" name="sign" id="sign" value='${recordOrderTrorFly.hesg}'>
						
					<%-- dfri = F as offsett. Always. Old rule in order to acquire status "active" ... --%>
					<input type="hidden" name="dfri" id="dfri" value='F'> 
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
					
					<table class="tableBorderWithRoundCorners3D_RoundOnlyOnBottom" width="98%" align="left" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="5"><td ></td></tr>
				 		<tr>
							<td colspan="8">
								
								<table class="text12" border="0">
								
					 			<tr>
					 				<td class="text14">
					 					<img style="vertical-align: middle;" onMouseOver="showPop('fwb_info');" onMouseOut="hidePop('fwb_info');" width="9px" height="9px" src="resources/images/info3.png" border="0" alt="info">
					 					<span title="dfcmn"><spring:message code="systema.tror.flyfraktbrev.form.update.label.fwb"/></span>
					 					<div class="text11" style="position: relative; display: inline;" align="left" >
							 				<span style="position:absolute;left:20px; width:250px;" id="fwb_info" class="popupWithInputText text11"  >
							           		<p>
							           		Dette feltet benyttes kun ved overføring av FWB   (FWB = Elektronisk fraktbrev) til flyselskap handling agent. Brukes kun hvis Tradevision er installert.
											Gyldige koder:
											<ul>
											<li><b>J</b> AWB skal sendes elektronisk.</li>
											<li><b>N</b> AWB skal ikke sendes. Kode J vil bli beholdt selv om FWB allerede er sendt. Hvis AWB blir endret skal også korrigert FWB automatisk bli avsendt.</li>
											</ul>
											Hvis man registrerer HAWB vil dette feltet automatisk bli oppdatert med kode N.
											</p> 
						           			
											</span>	
										</div>
					 				</td>
					 				<td class="text14" >
					 					<select  name="dfcmn" id="dfcmn" class="inputTextMediumBlue" >
					 						<option value="">-velg-</option>
						 				  	<option value="N"<c:if test="${model.record.dfcmn == 'N'}"> selected </c:if> >Nei</option>
						 				  	<option value="J"<c:if test="${model.record.dfcmn == 'J'}"> selected </c:if> >Ja</option>
										</select>
									</td>
									
									<td width="5px" class="text14" >&nbsp;</td>		
					 				<td class="text14">
					 					<img style="vertical-align: middle;" onMouseOver="showPop('merkelapp_info');" onMouseOut="hidePop('merkelapp_info');" width="9px" height="9px" src="resources/images/info3.png" border="0" alt="info">
										<span title="dfkdme"><spring:message code="systema.tror.flyfraktbrev.form.update.label.merkelapp"/></span>
	 					 				<div class="text11" style="position: relative; display: inline;" align="left" >
							 				<span style="position:absolute;left:20px; width:250px;" id="merkelapp_info" class="popupWithInputText text11"  >
							           		<p>
							           		Tast <b>J</b>,<b>N</b> eller <b>S</b> for merkelapp. Ved J så skrives automatisk merkelapper iht antall kolli på awb.
											Kode B gir adresselapper i tillegg til ordinære. Ved kode S så skrives Bar Code Merkelapp hvis TradeVision og Thermoprinter er installert.
											</p> 
											</span>	
										</div>
					 				</td>
					 				<td class="text14" >
					 					<select  name="dfkdme" id="dfkdme" class="inputTextMediumBlue" >
					 						<option value="">-velg-</option>
						 				  	<option value="J"<c:if test="${model.record.dfkdme == 'J'}"> selected </c:if> >Ja</option>
						 				  	<option value="N"<c:if test="${model.record.dfkdme == 'N'}"> selected </c:if> >Nei</option>
						 				  	<option value="S"<c:if test="${model.record.dfkdme == 'S'}"> selected </c:if> >S</option>
						 				  	
										</select>	
					 				</td>
					 				<td width="5px" class="text14" >&nbsp;</td>	
					 				<td class="text14" >
					 					<input class="inputFormSubmitGray" type="button" name="backToFlyfraktbrevGateButton" id="backToFlyfraktbrevGateButton" value='Tilbake til flyfraktb.lista'>
					 				</td> 
					 				 
					 			</tr>
					 			</table>
							</td>
						</tr>
						
						<tr height="10"><td ></td></tr>
						<tr>
							<td class="text14Bold">&nbsp;<spring:message code="systema.tror.flyfraktbrev.form.update.label.shipper"/></td>
							<td class="text14Bold">&nbsp;<spring:message code="systema.tror.flyfraktbrev.form.update.label.consignee"/></td>
						</tr>
						<tr height="5"><td ></td></tr>
						
						<tr>
				 			<td valign="top" width="50%" >
				 			 <table style="width:98%" class="tableBorderWithRoundCornersLightYellow" cellspacing="1" cellpadding="1" border="0">
						 		<tr height="10"><td ></td></tr>
						 		<tr>
						 			<td>
						 			<table style="width:100%" >
					 		 		<tr>
						 				<td class="text12">&nbsp;<span title="todo"><spring:message code="systema.tror.flyfraktbrev.form.update.label.name"/></span></td>
						 				<td class="text12">&nbsp;<span title="dftlfk"><spring:message code="systema.tror.flyfraktbrev.form.update.label.from"/></span></td>
						 				<td class="text12">&nbsp;<span title="dftlfk"><spring:message code="systema.tror.flyfraktbrev.form.update.label.to"/></span></td>
						 				<td class="text12">&nbsp;<span title="dftlfk"><spring:message code="systema.tror.flyfraktbrev.form.update.label.incoterms"/></span></td>
						 				<td class="text12">&nbsp;<span title="dftlfs"><spring:message code="systema.tror.flyfraktbrev.form.update.label.phone"/></span></td>
						 				
						 			</tr>
						 			<tr>	
						 				<td class="text12"><input readonly type="text" class="inputTextReadOnly" name="ownSenderName" id="ownSenderName" size="30" maxlength="50" value="${ownSenderName}"></td>
					 					<td class="text12"><input readonly type="text" class="inputTextReadOnly" name="header_imp.hesdf" id="header_imp.hesdf" size="4" maxlength="3" value="${recordFlyfraktbrevImportHeaderTrorFly.hesdf}"></td>
					 					<td class="text12"><input readonly type="text" class="inputTextReadOnly" name="header_imp.hesdt" id="header_imp.hesdt" size="4" maxlength="3" value="${recordFlyfraktbrevImportHeaderTrorFly.hesdt}"></td>
					 					<td class="text12"><input readonly type="text" class="inputTextReadOnly" name="header_imp.hefr" id="header_imp.hefr" size="4" maxlength="3" value="${recordFlyfraktbrevImportHeaderTrorFly.hefr}"></td>
					 					<td class="text12"><input type="text" class="inputTextMediumBlue" name="dftlfs" id="dftlfs" size="16" maxlength="15" value="${model.record.dftlfs}"></td>
					 				</tr>
					 				</table>
					 				</td>
					 			</tr>	
					 			<tr height="10"><td ></td></tr>
				 				
							 </table>
						 	</td>
						 	<td valign="top" width="50%">
				 			 <table style="width:98%" class="tableBorderWithRoundCornersLightYellow" cellspacing="1" cellpadding="1">
					 			<tr height="10"><td ></td></tr>
					 			<tr>
						 			<td>
						 			<table style="width:60%" >
							 		<tr>
						 				<td class="text12">&nbsp;<span title="todo"><spring:message code="systema.tror.flyfraktbrev.form.update.label.name"/></span></td>
						 				<td class="text12">&nbsp;<span title="todo"><spring:message code="systema.tror.flyfraktbrev.form.update.label.phone"/></span></td>
						 				
						 			</tr>
						 			<tr>	<%--${model[model.record.hekns]} --%>
					 					<td class="text12"><input readonly type="text" class="inputTextReadOnly" name="todo" id="todo" size="30" maxlength="50" value="${recordOrderTrorFly.henak}"></td>
					 					<td class="text12"><input type="text" class="inputTextMediumBlue" name="dftlfk" id="dftlfk" size="16" maxlength="15" value="${model.record.dftlfk}"></td>
					 				</tr>
					 				</table>
					 				</td>
				 				</tr>
				 				<tr height="10"><td ></td></tr>
			 				</table>
						 	</td>
					 	</tr>
					 	<tr height="10"><td ></td></tr>
					 	
					 	<tr>
				 			<td colspan="2" valign="top">
				 			<table class="tableBorderWithRoundCorners" width="99%">
				 				<%--
							 	<tr>
				 					<td class="text14Bold">&nbsp;
				 						<img style="vertical-align: bottom;" width="24px" height="24px" src="resources/images/lorry.png" border="0" alt="lorry">
				 						10.&nbsp;<spring:message code="systema.tror.fraktbrev.form.update.label.carrier"/>
				 					</td>
								</tr>
								 --%>
				 				<tr>
				 					<td >
				 					<table border="0">
				 						<tr>
					 						<td valign="top" >
				 							<table class="tableBorderWithRoundCornersLightGray">
					 						<tr>
					 					
								 				<td class="text12">&nbsp;<span title="dfroa1"><b><spring:message code="systema.tror.flyfraktbrev.form.update.label.routing"/></b></span></td>
								 				<td class="text12" >
								 					<input type="text" class="inputTextMediumBlue" name="dfroa1" id="dfroa1" size="4" maxlength="3" value="${model.record.dfroa1}">
								 				</td>
							 					<td class="text12" >
								 					<input type="text" class="inputTextMediumBlue" name="dfrob1" id="dfrob1" size="8" maxlength="7" value="${model.record.dfrob1}">
							 					</td>
							 					<td class="text12" >
								 					<input type="text" class="inputTextMediumBlue" name="dfroc1" id="dfroc1" size="3" maxlength="2" value="${model.record.dfroc1}">
							 					</td>
							 					<td width="5">&nbsp;</td>
							 					<td class="text12SkyBlue">&nbsp;<span title="dfetd1"><spring:message code="systema.tror.flyfraktbrev.form.update.label.etd"/></span></td>
								 				<td class="text12" >
								 					<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="dfetd1" id="dfetd1" size="5" maxlength="4" value="${model.record.dfetd1}">
								 				</td>
								 				<td class="text12SkyBlue" >	
							 						<span title="dfeta1"><spring:message code="systema.tror.flyfraktbrev.form.update.label.eta"/></span>
							 					</td>
							 					<td class="text12" >	
								 					<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="dfeta1" id="dfeta1" size="5" maxlength="4" value="${model.record.dfeta1}">
								 				</td>
								 				<td class="text12SkyBlue" >	
							 						<span title="dfeda1"><spring:message code="systema.tror.flyfraktbrev.form.update.label.estday"/></span>
							 					</td>
							 					<td class="text12" >	
								 					<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="dfeda1" id="dfeda1" size="3" maxlength="2" value="${model.record.dfeda1}">							 					
							 					</td>
							 					
							 					<td width="10">
								 				<td class="text12">&nbsp;<span title="dfroa2"><b><spring:message code="systema.tror.flyfraktbrev.form.update.label.routing"/>-2</b></span></td>
								 				<td class="text12" >
								 					<input type="text" class="inputTextMediumBlue" name="dfroa2" id="dfroa2" size="4" maxlength="3" value="${model.record.dfroa2}">
								 				</td>
								 				<td class="text12" >	
								 					<input type="text" class="inputTextMediumBlue" name="dfrob2" id="dfrob2" size="8" maxlength="7" value="${model.record.dfrob2}">
								 				</td>
								 				<td class="text12" >	
								 					<input type="text" class="inputTextMediumBlue" name="dfroc2" id="dfroc2" size="3" maxlength="2" value="${model.record.dfroc2}">
							 					</td>
							 					<td width="5">&nbsp;</td>
							 					<td class="text12SkyBlue">&nbsp;<span title="dfetd1"><spring:message code="systema.tror.flyfraktbrev.form.update.label.etd"/></span></td>
								 				<td class="text12" >
								 					<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="dfetd2" id="dfetd2" size="5" maxlength="4" value="${model.record.dfetd2}">
								 				</td>	
								 				<td class="text12SkyBlue" >	
							 						<span title="dfeta1"><spring:message code="systema.tror.flyfraktbrev.form.update.label.eta"/></span>
							 					</td>
							 					<td class="text12" >	
								 					<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="dfeta2" id="dfeta2" size="5" maxlength="4" value="${model.record.dfeta2}">
								 				</td>
								 				<td class="text12SkyBlue" >	
							 						<span title="dfeda1"><spring:message code="systema.tror.flyfraktbrev.form.update.label.estday"/></span>
							 					</td>
							 					<td class="text12" >	
								 					<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="dfeda2" id="dfeda2" size="3" maxlength="2" value="${model.record.dfeda2}">							 					
							 					</td>
						 					</tr>
						 					
					 						<tr>
							 					<td class="text12">&nbsp;<span title="dfroa3"><b><spring:message code="systema.tror.flyfraktbrev.form.update.label.routing"/>-3</b></span></td>
								 				<td class="text12" >
								 					<input type="text" class="inputTextMediumBlue" name="dfroa3" id="dfroa3" size="4" maxlength="3" value="${model.record.dfroa3}">
								 				</td>	
								 				<td class="text12" >	
								 					<input type="text" class="inputTextMediumBlue" name="dfrob3" id="dfrob3" size="8" maxlength="7" value="${model.record.dfrob3}">
								 				</td>
								 				<td class="text12" >	
								 					<input type="text" class="inputTextMediumBlue" name="dfroc3" id="dfroc3" size="3" maxlength="2" value="${model.record.dfroc3}">
							 					</td>
							 					<td width="5">&nbsp;</td>
							 					<td class="text12SkyBlue">&nbsp;<span title="dfetd1"><spring:message code="systema.tror.flyfraktbrev.form.update.label.etd"/></span></td>
								 				<td class="text12" >
								 					<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="dfetd3" id="dfetd3" size="5" maxlength="4" value="${model.record.dfetd3}">
								 				</td>
								 				<td class="text12SkyBlue" >	
							 						<span title="dfeta1"><spring:message code="systema.tror.flyfraktbrev.form.update.label.eta"/></span>
							 					</td>
							 					<td class="text12" >	
								 					<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="dfeta3" id="dfeta3" size="5" maxlength="4" value="${model.record.dfeta3}">
								 				</td>
								 				<td class="text12SkyBlue" >	
							 						<span title="dfeda1"><spring:message code="systema.tror.flyfraktbrev.form.update.label.estday"/></span>
							 					</td>
							 					<td class="text12" >	
								 					<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="dfeda3" id="dfeda3" size="3" maxlength="2" value="${model.record.dfeda3}">							 					
							 					</td>
						 					</tr>
						 					</table>
						 					</td>
						 				</tr>
						 				
						 				
									</table>
									</td>				 				
					 			</tr>
						 	</table>
						 	</td>
					 	</tr>
					 	
					 	<tr>
				 			<td colspan="2" valign="top" width="100%" >
				 			<table width="99%">
				 				<tr height="4"><td ></td></tr>
				 				<tr>
				 					<td >
				 					<table class="tableBorderWithRoundCorners" width="100%" border="0">
				 						<tr>
				 						<td>
				 							<table width="80%" >
				 								<tr>	
										 			<td class="text12">
										 				<span title="dfinfa">&nbsp;<spring:message code="systema.tror.flyfraktbrev.form.update.label.info"/></span>
								 					</td>
									 				<td colspan="10" class="text12">
									 					<input type="text" class="inputTextMediumBlue" name="dfinf1" id="dfinf1" size="36" maxlength="35" value="${model.record.dfinf1}">
									 					&nbsp;<input type="text" class="inputTextMediumBlue" name="dfinf2" id="dfinf2" size="36" maxlength="35" value="${model.record.dfinf2}">
									 				</td>
									 			</tr>	
				 								<tr>	
										 			<td class="text12">
										 				<span title="dfval">&nbsp;<spring:message code="systema.tror.flyfraktbrev.form.update.label.currency"/></span>
								 					</td>
									 				<td class="text12">
									 					<select name="dfval" id="dfval">
									 						<option value="">-valuta-</option>
										 				  	<c:forEach var="record" items="${model.currencyCodeList}" >
										 				  		<option value="${record.kvakod}"<c:if test="${model.record.dfval == record.kvakod || (empty model.record.dfval && record.kvakod=='NOK')}"> selected </c:if> >${record.kvakod}</option>
															</c:forEach>  
														</select>
									 				</td>
										 			<td class="text12">
										 				<span title="dffvcu"><spring:message code="systema.tror.flyfraktbrev.form.update.label.oclca"/></span>
								 					</td>
									 				<td class="text12">
									 					<input type="text" class="inputTextMediumBlue" name="dfdvca" id="dfdvca" size="8" maxlength="7" value="${model.record.dfdvca}">
									 				</td>
										 			<td class="text12">
										 				<span title="dfpoli">&nbsp;<spring:message code="systema.tror.flyfraktbrev.form.update.label.oslcu"/></span>
								 					</td>
									 				<td class="text12">
									 					<input type="text" class="inputTextMediumBlue" name="dfdvcu" id="dfdvcu" size="8" maxlength="7" value="${model.record.dfdvcu}">
									 				</td>
									 				<td class="text12">
										 				<span title="dfbelf">&nbsp;<spring:message code="systema.tror.flyfraktbrev.form.update.label.forsikring"/></span>
								 					</td>
									 				<td class="text12">
									 					<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="dfbelf" id="dfbelf" size="8" maxlength="7" value="${model.record.dfbelf}">
									 				</td>
										 			<td class="text12">
										 				<span title="dftols">&nbsp;<spring:message code="systema.tror.flyfraktbrev.form.update.label.tollstatus"/></span>
								 					</td>
									 				<td class="text12">
									 					<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="dftols" id="dftols" size="3" maxlength="2" value="${model.record.dftols}">
									 				</td>
									 			</tr>
									 			<tr height="5"><td colspan="2" ></td></tr>
									 		</table>
									 	</td>
									 	</tr>
									 	<tr>
				 							<td colspan="10">
				 							<table width="85%" >
				 								<tr height="5"><td ></td></tr>	
							 					<tr>	
										 			<td class="text12">
										 				<span title="dfinfa">&nbsp;<spring:message code="systema.tror.flyfraktbrev.form.update.label.hinfo"/></span>
								 					</td>
									 				<td class="text12">
									 					<input type="text" class="inputTextMediumBlue" name="dfinfa" id="dfinfa" size="75" maxlength="71" value="${model.record.dfinfa}">
									 				</td>
									 				<td class="text12">
									 					<input type="text" class="inputTextMediumBlue" name="dfinfb" id="dfinfb" size="75" maxlength="71" value="${model.record.dfinfb}">
									 				</td>
									 				
									 			</tr>
									 			<tr>	
										 			<td class="text12">
										 				<span title="dfinfc">&nbsp;<spring:message code="systema.tror.flyfraktbrev.form.update.label.rdest"/></span>
								 					</td>
									 				<td class="text12">
									 					<input type="text" class="inputTextMediumBlue" name="dfinfc" id="dfinfc" size="75" maxlength="71" value="${model.record.dfinfc}">
									 				</td>
									 			</tr>
							 				</table>
						 				</td>
						 				</tr>
									</table>
									</td>				 				 				
					 			</tr>
 							</table>
 							</td>
					 	</tr>
					 	
					 	<tr height="5"><td ></td></tr>
					 </table>
				</td>
			</tr>
			<tr height="5"><td ></td></tr>
			<%-- X --%>
			<tr>
            		<td>
	        			<table style="width:98%;" align="left" class="tableBorderWithRoundCornersLightYellow" cellspacing="0" cellpadding="0">
				 		
						<tr height="5"><td colspan="2" ></td></tr>
						<tr height="1"><td colspan="2" style="border-bottom:1px solid;border-color:#FFFFFF;" class="text"></td></tr>
						<tr height="3"><td colspan="2" ></td></tr>
						
						<tr>
			            		<td>
				        			<table width="98%" id="containerdatatableTable" cellspacing="0" align="left" >
									<%-- <c:if test="${not empty XXXmodel.record.heunik}"> --%>
										<tr height="5"><td align="left" ></td></tr>
										
								 		<tr >
											<td align="bottom" colspan="3" class="text11">&nbsp;&nbsp;<img style="vertical-align:middle;" src="resources/images/update.gif" width="10px" height="10px" border="0" alt="update item line">
											&nbsp;<spring:message code="systema.tror.fraktbrev.form.detail.update.label.itemLine"/>
											<c:if test="${not empty Xmodel.record.df1004}">
												&nbsp;&nbsp;<button name="dokufmButton" id="dokufmButton" class="buttonGrayWithGreenFrame" type="button" >Merke, snd.nivå</button>
											</c:if>
											</td>
										</tr>
								 		<tr>
											<td colspan="2" style="padding: 0px;">
												<table align="left" border="0" style="width:100%;" >
												<tr class="tableHeaderField10" >
													
										 			<td width="10%" align="right" valign="bottom" class="tableHeaderFieldFirst11"><span title="dfnt1">&nbsp;<spring:message code="systema.tror.flyfraktbrev.form.update.itemlines.label.antall"/></span></td>
										 			<td align="right" valign="bottom" class="tableHeaderField11"><span title="dfvkt1">&nbsp;<spring:message code="systema.tror.flyfraktbrev.form.update.itemlines.label.vekt"/>&nbsp;</span></td>
										 			<td align="right" valign="bottom" class="tableHeaderField11">
										 				<img style="vertical-align: middle;" onMouseOver="showPop('rc_info');" onMouseOut="hidePop('rc_info');" width="9px" height="9px" src="resources/images/info3.png" border="0" alt="info">
										 				<span title="dfrc1"><spring:message code="systema.tror.flyfraktbrev.form.update.itemlines.label.rateClass"/></span>
										 				<div class="text11" style="position: relative; display: inline;" align="left" >
											 				<span style="position:absolute;top:-55px; width:400px;" id="rc_info" class="popupWithInputText text11"  >
												           		<p>Feltet må fylles ut med en av følgende gyldige koder:</p> 
											           			<ul>
											           				<li><b>B</b>&nbsp;BASIC CHARGE
											           				<li><b>C</b>&nbsp;SPECIFIC COMMODITY RATE</li>
											           				<li><b>E</b>&nbsp;ULD ADDITIONAL RATE</li>
											           				<li><b>K</b>&nbsp;RATE PER KILOGRAMME</li>	
											           				
											           				<li><b>M</b>&nbsp;MINIMUM CHARGE</li>	
											           				<li><b>N</b>&nbsp;NORMAL RATE</li>	
											           				<li><b>Q</b>&nbsp;QUANTITY RATE</li>	
											           				<li><b>R</b>&nbsp;CLASS RATE REDUCTION</li>	
											           				<li><b>S</b>&nbsp;CLASS RATE SURCHARGE</li>	
											           				<li><b>U</b>&nbsp;ULD BASIC CHARGE OR RATE</li>	
											           				<li><b>X</b>&nbsp;ULD ADDITIONAL INFORMATION</li>	
											           				<li><b>Y</b>&nbsp;ULD DISCOUNT</li>	
							
										           			</ul>
										           			<p>
										           			Ved å benytte F-4 kan man søke i kode/tekstregister for gyldige rate class koder (MENU MAINT2, punkt 13).Ved M/N/Q i RC tastes normalt kun en ratelinje.
															Skal Base + kg rate benyttes er det krav til at det tastes to ratelinjer.På første ratelinje tastes kode B i RC og baseprisen i ratefeltet.
															</p>
															<p>
															På annen ratelinje tastes kode K i RC og pr.kg. raten i ratefeltet.
															(Feltet Fvekt må fylles ut på begge linjene.)
															Eks:
															Ant.      Vekt.    RC.      I.no       Fvekt    P Rate.....         Total     Varebetegn....
															0002     27,0      B                     27,0      300,00              300,00  BROCHURES
															K                     27,0      14,20                383,00
															</p>
															<p>	
															Hvis SAS Priority Cargo skal benyttes tastes ratelinjene som vist ovenfor, men man taster i tillegg kode P eller D i feltet P.
															Hvis systemet finner brutto eller kundeavtalte rater vil disse automatisk oppdatere ratelinjene når man benytter enter-tasten.
															For å sjekke om det ligger rater på aktuell strekning /flyselskap / kunde kan man derfor taste enter før man begynner registrering.
															</p>
															<p>		
															NB: gjelder kun for HAWB.
															Hvis man ønsker å få teksten "As agreed" skrevet ut på fraktbrevet istedenfor rate og totalbeløp er dette mulig ved å taste kode A i feltet P (se under). Vær oppmerksom på at man, avhengig av hvilken RC kode man benytter, allikevel må taste informasjon i de feltene det er krav til.
															Hvis man benytter RC kodene M/N/Q/B/K/C/E/U/X/Y er det også krav til at man taster rate.
											           		</p>
															</span>	
														</div>
										 			</td>
										 			<td align="right" valign="bottom" class="tableHeaderField11"><span title="dfcom1">&nbsp;<spring:message code="systema.tror.flyfraktbrev.form.update.itemlines.label.ino"/></span></td>
										 			<td align="right" valign="bottom" class="tableHeaderField11"><span title="dffbv1">&nbsp;<spring:message code="systema.tror.flyfraktbrev.form.update.itemlines.label.fvekt"/>&nbsp;</span></td>
										 			<td align="right" valign="bottom" class="tableHeaderField11"><span title="dfrk1">&nbsp;<spring:message code="systema.tror.flyfraktbrev.form.update.itemlines.label.p"/>&nbsp;</span></td>
										 			<td align="right" valign="bottom" class="tableHeaderField11">
										 				<img style="vertical-align: middle;" onMouseOver="showPop('rate_info');" onMouseOut="hidePop('rate_info');" width="9px" height="9px" src="resources/images/info3.png" border="0" alt="info">
										 				<span title="dfrpr1"><spring:message code="systema.tror.flyfraktbrev.form.update.itemlines.label.rate"/>&nbsp;</span>
										 				<div class="text11" style="position: relative; display: inline;" align="left" >
											 				<span style="position:absolute;top:-55px; width:250px;" id="rate_info" class="popupWithInputText text11"  >
											           		<p>
											           		<b>RATE</b>
											           		I ratefeltet tastes raten som skal benyttes på flyfraktbrevet.
															Hvis det er tastet kode B eller M i RC feltet skal Baseprisen / Minimumsprisen tastes i dette feltet.
															Feltet for total blir da oppdatert med samme beløp.
															Hvis det er tastet kode N / Q / K i RC feltet skal selve raten pr. kg. tastes.
															Feltet for total blir da oppdatert med Fvekt x raten.
															</p> 
										           			
										           			<p>
										           			<b>RATE CONSTRUCTION</b>
										           			Ved konstruksjon av rater benyttes flg. teknikk: Hvis man f. eks. har en forsendelse til Las Vegas
															via Los Angeles og skal legge til konstruksjonsrate for strekningen LAX - LAS tastes denne separat i egen linje på skjermbildet.
															Man har 10 kll / 450 kg og tilleggsraten er NOK 1,60.
															Raten OSL - LAX er NOK 25,60.
															Ant       Vekt     RC       Fvekt    Rate     Total
															10        450,0    Q         450,0    25,60    11520,00
															LAS                 Q         450,0      1,60        720,00
															</p>
															
															</span>	
														</div>
										 				
										 			</td>
										 			<td align="right" valign="bottom" class="tableHeaderField11"><span title="dfblt1">&nbsp;<spring:message code="systema.tror.flyfraktbrev.form.update.itemlines.label.total"/>&nbsp;</span></td>
										 			<td align="left" valign="bottom" class="tableHeaderField11"><span title="dfvs1">&nbsp;<spring:message code="systema.tror.flyfraktbrev.form.update.itemlines.label.itemdesc"/>&nbsp;</span></td>
										 		</tr>
										 		<tr class="tableRow">
										 			<td width="10%" align="right" class="tableCellFirst" >
									 					<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue11" style="text-align:right;" name="dfnt1" id="dfnt1" size="5" maxlength="4" value="${model.record.dfnt1}">
									 				</td>
									 				<td align="right" class="tableCell" >
									 					<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue11" style="text-align:right;" name="dfvkt1" id="dfvkt1" size="9" maxlength="8" value="${fn:replace(model.record.dfvkt1,'.',',')}">
									 				</td>
									 				<td align="right" class="tableCell" >
									 					<select name="dfrc1" id="dfrc1">
									 					<option value="">-velg-</option>
										 				  	<c:forEach var="record" items="${model.rateClassCodeList}" >
										 				  		<option value="${record}"<c:if test="${model.record.dfrc1 == record }"> selected </c:if> >${record}</option>
															</c:forEach>
									 					</select>
									 				</td>
									 				<td align="right" class="tableCell" >
									 					<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue11" style="text-align:right;" name="dfcom1" id="dfcom1" size="5" maxlength="4" value="${model.record.dfcom1}">
									 				</td>
									 				<td align="right" class="tableCell" >
									 					<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue11" style="text-align:right;" name="dffbv1" id="dffbv1" size="9" maxlength="8" value="${fn:replace(model.record.dffbv1,'.',',')}">
									 				</td>
									 				<td align="right" class="tableCell" >
									 					<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue11" style="text-align:right;" name="dfrk1" id="dfrk1" size="2" maxlength="1" value="${model.record.dfrk1}">
									 				</td>
									 				<td align="right" class="tableCell" >
									 					<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue11" style="text-align:right;" name="dfrpr1" id="dfrpr1" size="10" maxlength="9" value="${fn:replace(model.record.dfrpr1,'.',',')}">
									 				</td>
									 				<td align="right" class="tableCell" >
									 					<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue11" style="text-align:right;" name="dfblt1" id="dfblt1" size="14" maxlength="13" value="${fn:replace(model.record.dfblt1,'.',',')}">
									 				</td>
									 				<td align="left" class="tableCell" >
									 					<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue11" name="dfvs1" id="dfvs1" size="26" maxlength="25" value="${model.record.dfvs1}">
									 				</td>
									 				
									 			</tr>
									 			<tr class="tableRow">
										 			<td width="10%" align="right" class="tableCellFirst" >
									 					<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue11" style="text-align:right;" name="dfnt2" id="dfnt2" size="5" maxlength="4" value="${model.record.dfnt2}">
									 				</td>
									 				<td align="right" class="tableCell" >
									 					<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue11" style="text-align:right;" name="dfvkt2" id="dfvkt2" size="9" maxlength="8" value="${fn:replace(model.record.dfvkt2,'.',',')}">
									 				</td>
									 				<td align="right" class="tableCell" >
									 					<select name="dfrc2" id="dfrc2">
									 					<option value="">-velg-</option>
										 				  	<c:forEach var="record" items="${model.rateClassCodeList}" >
										 				  		<option value="${record}"<c:if test="${model.record.dfrc2 == record }"> selected </c:if> >${record}</option>
															</c:forEach>
									 					</select>
									 				</td>
									 				<td align="right" class="tableCell" >
									 					<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue11" style="text-align:right;" name="dfcom2" id="dfcom2" size="5" maxlength="4" value="${model.record.dfcom2}">
									 				</td>
									 				<td align="right" class="tableCell" >
									 					<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue11" style="text-align:right;" name="dffbv2" id="dffbv2" size="9" maxlength="8" value="${fn:replace(model.record.dffbv2,'.',',')}">
									 				</td>
									 				<td align="right" class="tableCell" >
									 					<%--
									 					<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue11" style="text-align:right;" name="dfrk2" id="dfrk2" size="2" maxlength="1" value="${model.record.dfrk2}">
									 					 --%>
									 				</td>
									 				<td align="right" class="tableCell" >
									 					<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue11" style="text-align:right;" name="dfrpr2" id="dfrpr2" size="10" maxlength="9" value="${fn:replace(model.record.dfrpr2,'.',',')}">
									 				</td>
									 				<td align="right" class="tableCell" >
									 					<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue11" style="text-align:right;" name="dfblt2" id="dfblt2" size="14" maxlength="13" value="${fn:replace(model.record.dfblt2,'.',',')}">
									 				</td>
									 				<td align="left" class="tableCell" >
									 					<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue11" name="dfvs2" id="dfvs2" size="26" maxlength="25" value="${model.record.dfvs2}">
									 				</td>
									 				
									 			</tr>
									 			<tr class="tableRow">
										 			<td width="10%" align="right" class="tableCellFirst" >
									 					<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue11" style="text-align:right;" name="dfnt3" id="dfnt3" size="5" maxlength="4" value="${model.record.dfnt3}">
									 				</td>
									 				<td align="right" class="tableCell" >
									 					<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue11" style="text-align:right;" name="dfvkt3" id="dfvkt3" size="9" maxlength="8" value="${fn:replace(model.record.dfvkt3,'.',',')}">
									 				</td>
									 				<td align="right" class="tableCell" >
									 					<select name="dfrc3" id="dfrc3">
									 					<option value="">-velg-</option>
										 				  	<c:forEach var="record" items="${model.rateClassCodeList}" >
										 				  		<option value="${record}"<c:if test="${model.record.dfrc3 == record }"> selected </c:if> >${record}</option>
															</c:forEach>
									 					</select>
									 				</td>
									 				<td align="right" class="tableCell" >
									 					<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue11" style="text-align:right;" name="dfcom3" id="dfcom3" size="5" maxlength="4" value="${model.record.dfcom3}">
									 				</td>
									 				<td align="right" class="tableCell" >
									 					<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue11" style="text-align:right;" name="dffbv3" id="dffbv3" size="9" maxlength="8" value="${fn:replace(model.record.dffbv3,'.',',')}">
									 				</td>
									 				<td align="right" class="tableCell" >
									 					<%--
									 					<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue11" style="text-align:right;" name="dfrk3" id="dfrk3" size="2" maxlength="1" value="${model.record.dfrk3}">
									 					 --%>
									 				</td>
									 				<td align="right" class="tableCell" >
									 					<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue11" style="text-align:right;" name="dfrpr3" id="dfrpr3" size="10" maxlength="9" value="${fn:replace(model.record.dfrpr3,'.',',')}">
									 				</td>
									 				<td align="right" class="tableCell" >
									 					<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue11" style="text-align:right;" name="dfblt3" id="dfblt3" size="14" maxlength="13" value="${fn:replace(model.record.dfblt3,'.',',')}">
									 				</td>
									 				<td align="left" class="tableCell" >
									 					<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue11" name="dfvs3" id="dfvs3" size="26" maxlength="25" value="${model.record.dfvs3}">
									 				</td>
									 				
									 			</tr>
									 			
									 			<tr class="tableRow">
										 			<td width="10%" align="right" class="tableCellFirst" >
									 					<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue11" style="text-align:right;" name="dfnt4" id="dfnt4" size="5" maxlength="4" value="${model.record.dfnt4}">
									 				</td>
									 				<td align="right" class="tableCell" >
									 					<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue11" style="text-align:right;" name="dfvkt4" id="dfvkt4" size="9" maxlength="8" value="${fn:replace(model.record.dfvkt4,'.',',')}">
									 				</td>
									 				<td align="right" class="tableCell" >
									 					<select name="dfrc4" id="dfrc4">
									 					<option value="">-velg-</option>
										 				  	<c:forEach var="record" items="${model.rateClassCodeList}" >
										 				  		<option value="${record}"<c:if test="${model.record.dfrc4 == record }"> selected </c:if> >${record}</option>
															</c:forEach>
									 					</select>
									 				</td>
									 				<td align="right" class="tableCell" >
									 					<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue11" style="text-align:right;" name="dfcom4" id="dfcom4" size="5" maxlength="4" value="${model.record.dfcom4}">
									 				</td>
									 				<td align="right" class="tableCell" >
									 					<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue11" style="text-align:right;" name="dffbv4" id="dffbv4" size="9" maxlength="8" value="${fn:replace(model.record.dffbv4,'.',',')}">
									 				</td>
									 				<td align="right" class="tableCell" >
									 					 <%--
									 					<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue11" style="text-align:right;" name="dfrk4" id="dfrk4" size="2" maxlength="1" value="${model.record.dfrk4}">
									 					 --%>
									 				</td>
									 				<td align="right" class="tableCell" >
									 					<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue11" style="text-align:right;" name="dfrpr4" id="dfrpr4" size="10" maxlength="9" value="${fn:replace(model.record.dfrpr4,'.',',')}">
									 				</td>
									 				<td align="right" class="tableCell" >
									 					<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue11" style="text-align:right;" name="dfblt4" id="dfblt4" size="14" maxlength="13" value="${fn:replace(model.record.dfblt4,'.',',')}">
									 				</td>
									 				<td align="left" class="tableCell" >
									 					<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue11" name="dfvs4" id="dfvs4" size="26" maxlength="25" value="${model.record.dfvs4}">
									 				</td>
									 				
									 			</tr>
									 			<tr class="tableRow">
										 			<td width="10%" align="right" class="tableCellFirst" >
									 					<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue11" style="text-align:right;" name="dfnt5" id="dfnt5" size="5" maxlength="4" value="${model.record.dfnt5}">
									 				</td>
									 				<td align="right" class="tableCell" >
									 					<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue11" style="text-align:right;" name="dfvkt5" id="dfvkt5" size="9" maxlength="8" value="${fn:replace(model.record.dfvkt5,'.',',')}">
									 				</td>
									 				<td align="right" class="tableCell" >
									 					<select name="dfrc5" id="dfrc5">
									 					<option value="">-velg-</option>
										 				  	<c:forEach var="record" items="${model.rateClassCodeList}" >
										 				  		<option value="${record}"<c:if test="${model.record.dfrc5 == record }"> selected </c:if> >${record}</option>
															</c:forEach>
									 					</select>
									 				</td>
									 				<td align="right" class="tableCell" >
									 					<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue11" style="text-align:right;" name="dfcom5" id="dfcom5" size="5" maxlength="4" value="${model.record.dfcom5}">
									 				</td>
									 				<td align="right" class="tableCell" >
									 					<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue11" style="text-align:right;" name="dffbv5" id="dffbv5" size="9" maxlength="8" value="${fn:replace(model.record.dffbv5,'.',',')}">
									 				</td>
									 				<td align="right" class="tableCell" >
									 					<%--
									 					<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue11" style="text-align:right;" name="dfrk5" id="dfrk5" size="2" maxlength="1" value="${model.record.dfrk5}">
									 					 --%>
									 				</td>
									 				<td align="right" class="tableCell" >
									 					<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue11" style="text-align:right;" name="dfrpr5" id="dfrpr5" size="10" maxlength="9" value="${fn:replace(model.record.dfrpr5,'.',',')}">
									 				</td>
									 				<td align="right" class="tableCell" >
									 					<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue11" style="text-align:right;" name="dfblt5" id="dfblt5" size="14" maxlength="13" value="${fn:replace(model.record.dfblt5,'.',',')}">
									 				</td>
									 				<td align="left" class="tableCell" >
									 					<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue11" name="dfvs5" id="dfvs5" size="26" maxlength="25" value="${model.record.dfvs5}">
									 				</td>
									 				
									 			</tr>
									 			<tr class="tableRow">
										 			<td width="10%" align="right" class="tableCellFirst" >
									 					<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue11" style="text-align:right;" name="dfnt6" id="dfnt6" size="5" maxlength="4" value="${model.record.dfnt6}">
									 				</td>
									 				<td align="right" class="tableCell" >
									 					<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue11" style="text-align:right;" name="dfvkt6" id="dfvkt6" size="9" maxlength="8" value="${fn:replace(model.record.dfvkt6,'.',',')}">
									 				</td>
									 				<td align="right" class="tableCell" >
									 					<select name="dfrc6" id="dfrc6">
									 					<option value="">-velg-</option>
										 				  	<c:forEach var="record" items="${model.rateClassCodeList}" >
										 				  		<option value="${record}"<c:if test="${model.record.dfrc6 == record }"> selected </c:if> >${record}</option>
															</c:forEach>
									 					</select>
									 				</td>
									 				<td align="right" class="tableCell" >
									 					<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue11" style="text-align:right;" name="dfcom6" id="dfcom6" size="5" maxlength="4" value="${model.record.dfcom6}">
									 				</td>
									 				<td align="right" class="tableCell" >
									 					<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue11" style="text-align:right;" name="dffbv6" id="dffbv6" size="9" maxlength="8" value="${fn:replace(model.record.dffbv6,'.',',')}">
									 				</td>
									 				<td align="right" class="tableCell" >
									 					<%--
									 					<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue11" style="text-align:right;" name="dfrk6" id="dfrk6" size="2" maxlength="1" value="${model.record.dfrk6}">
									 					 --%>
									 				</td>
									 				<td align="right" class="tableCell" >
									 					<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue11" style="text-align:right;" name="dfrpr6" id="dfrpr6" size="10" maxlength="9" value="${fn:replace(model.record.dfrpr6,'.',',')}">
									 				</td>
									 				<td align="right" class="tableCell" >
									 					<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue11" style="text-align:right;" name="dfblt6" id="dfblt6" size="14" maxlength="13" value="${fn:replace(model.record.dfblt6,'.',',')}">
									 				</td>
									 				<td align="left" class="tableCell" >
									 					<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue11" name="dfvs6" id="dfvs6" size="26" maxlength="25" value="${model.record.dfvs6}">
									 				</td>
									 			</tr>
									 			<tr class="tableRow">
									 				<td nowrap width="10%" align="right" class="tableCellFirst" >&nbsp;
									 					<b><spring:message code="systema.tror.flyfraktbrev.form.update.itemlines.label.totalSum"/>&nbsp;</span></b>
										 				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										 				<input readonly type="text" class="inputTextReadOnly" style="font-weight: bold; text-align: right;" name="dfntt" id="dfntt" size="8" maxlength="10" value="${fn:replace(model.record.dfntt,'.',',')}">
										 			</td>	
									 				<td align="right" class="tableCell" >&nbsp;<input readonly type="text" class="inputTextReadOnly" style="font-weight: bold;text-align: right;" name="dfvktt" id="dfvktt" size="10" maxlength="12" value="${fn:replace(model.record.dfvktt,'.',',')}"></td>
									 				<td align="right" class="tableCell" >&nbsp;</td>
									 				<td align="right" class="tableCell" >&nbsp;</td>
									 				<td align="right" class="tableCell" >&nbsp;<input readonly type="text" class="inputTextReadOnly" style="font-weight: bold;text-align: right;" name="nothing" id="nothing" size="10" maxlength="12" value="${fn:replace(model.fvektTotal,'.',',')}"></td>
									 				<td align="right" class="tableCell" >&nbsp;</td>
									 				<td align="right" class="tableCell" >&nbsp;</td>
									 				<td align="right" class="tableCell" >&nbsp;<input readonly type="text" class="inputTextReadOnly" style="font-weight: bold;text-align: right;" name="dfblt" id="dfblt" size="10" maxlength="12" value="${fn:replace(model.record.dfblt,'.',',')}"></td>
									 				
										 			<td align="left" class="tableCell" >
									 					<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue11" name="dfvs7" id="dfvs7" size="26" maxlength="25" value="${model.record.dfvs7}">
									 				</td>
									 				
									 			</tr>
									 			<tr class="tableRow">
									 					
										 			<td width="10%" align="right" class="tableCellFirst" >&nbsp;</td>
									 				<td align="right" class="tableCell" >&nbsp;</td>
									 				<td align="right" class="tableCell" >&nbsp;</td>
									 				<td align="right" class="tableCell" >&nbsp;</td>
									 				<td align="right" class="tableCell" >&nbsp;</td>
									 				<td align="right" class="tableCell" >&nbsp;</td>
									 				<td align="right" class="tableCell" >&nbsp;</td>
									 				<td align="right" class="tableCell" >&nbsp;</td>
									 				
									 				<td align="left" class="tableCell" >
									 					<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue11" name="dfvs8" id="dfvs8" size="26" maxlength="25" value="${model.record.dfvs8}">
									 				</td>
									 				
									 			</tr>
									 			<tr class="tableRow">
										 			<td width="5%" align="right" class="tableCellFirst" >&nbsp;</td>
									 				<td align="right" class="tableCell" >&nbsp;</td>
									 				<td align="right" class="tableCell" >&nbsp;</td>
									 				<td align="right" class="tableCell" >&nbsp;</td>
									 				<td align="right" class="tableCell" >&nbsp;</td>
									 				<td align="right" class="tableCell" >&nbsp;</td>
									 				<td align="right" class="tableCell" >&nbsp;</td>
									 				<td align="right" class="tableCell" >&nbsp;</td>
									 				<td align="left" class="tableCell" >
									 					<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue11" name="dfvs9" id="dfvs9" size="26" maxlength="25" value="${model.record.dfvs9}">
									 				</td>
									 				
									 			</tr>
									 			<tr class="tableRow">
									 				<td width="5%" align="right" class="tableCellFirst" >&nbsp;</td>
									 				<td align="right" class="tableCell" >&nbsp;</td>
									 				<td align="right" class="tableCell" >&nbsp;</td>
									 				<td align="right" class="tableCell" >&nbsp;</td>
									 				<td align="right" class="tableCell" >&nbsp;</td>
									 				<td align="right" class="tableCell" >&nbsp;</td>
									 				<td align="right" class="tableCell" >&nbsp;</td>
									 				<td align="right" class="tableCell" >&nbsp;</td>
									 				<td align="left" class="tableCell" >
									 					<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue11" name="dfvs10" id="dfvs10" size="26" maxlength="25" value="${model.record.dfvs10}">
									 				</td>
									 				
									 			</tr>
									 			<tr height="5"><td align="left" ></td></tr>
									 			<tr>
									 			<td valign="top" colspan="8" >
	        										<table width="100%" align="left" class="tableBorderWithRoundCornersLightYellow" cellspacing="1" cellpadding="0">
	        											<tr height="3"><td align="left" ></td></tr>
		        										<tr>
		        											<td class="text12"><span title="dfkddc/dfbldc">&nbsp;<spring:message code="systema.tror.flyfraktbrev.form.update.label.charge.dueCarrier"/></span></td>
		        											<td>	
		        												<select name="dfkddc" id="dfkddc">
											 						<option value="">-velg-</option>
												 				  	<option value="P"<c:if test="${model.record.dfkddc == 'P' }"> selected </c:if> >Prepaid</option>
																	<option value="C"<c:if test="${model.record.dfkddc == 'C' }"> selected </c:if> >Collect</option>
																</select>
		        											</td>
		        											<td>	
		        												<input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue11" name="dfbldc" id="dfbldc" size="12" maxlength="10" value="${fn:replace(model.record.dfbldc,'.',',')}">
		        											</td>
		        											<td class="text12"><span title="dfbla"><spring:message code="systema.tror.flyfraktbrev.form.update.label.charge.valuation"/></span></td>
		        											<td>	
		        												<input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue11" name="dfbla" id="dfbla" size="12" maxlength="10" value="${fn:replace(model.record.dfbla,'.',',')}">
		        											</td>
		        											<td class="text12"><span title="dfnett"><spring:message code="systema.tror.flyfraktbrev.form.update.label.charge.weight"/></span></td>
		        											<td>	
		        												<input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue11" name="dfnett" id="dfnett" size="12" maxlength="10" value="${fn:replace(model.record.dfnett,'.',',')}">
		        											</td>
		        										</tr>
		        										<tr>
		        											<td class="text12"><span title="todo">&nbsp;<spring:message code="systema.tror.flyfraktbrev.form.update.label.charge.dueAgent"/></span></td>
		        											<td>&nbsp;</td>
		        											<td>	
		        												<input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue11" name="todo" id="todo" size="12" maxlength="10" value="${Xmodel.record.todo}">
		        											</td>
		        											<td class="text12"><span title="dfblav"><spring:message code="systema.tror.flyfraktbrev.form.update.label.charge.tax"/></span></td>
		        											<td>	
		        												<input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue11" name="dfblav" id="dfblav" size="12" maxlength="10" value="${fn:replace(model.record.dfblav,'.',',')}">
		        											</td>
		        											<td class="text12"><span title="todo">R</span></td>
		        											<td>	
		        												<input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue11" name="todo" id="todo" size="12" maxlength="10" value="${Xmodel.record.todo}">
		        											</td>
		        										</tr>
		        										<tr>
		        											<td class="text12"><span title="dfft">&nbsp;<spring:message code="systema.tror.flyfraktbrev.form.update.label.charge.text"/></span></td>
		        											<td colspan="10">	
		        												<input type="text" class="inputTextMediumBlue11" name="dfft" id="dfft" size="45" maxlength="41" value="${model.record.dfft}">
		        											</td>
		        											
		        										</tr>
		        										<tr height="3"><td align="left" ></td></tr>
		        										</table>
	        										</td>
									 				<td valign="bottom" align="right">
														<input tabindex=-1 class="inputFormSubmit submitSaveClazz" type="submit" name="submit" id="submit" value='<spring:message code="systema.tror.submit.save"/>'/>
								 				    </td>
									 			</tr>

									 			</table>
								 			</td>
							 			</tr>
									<tr height="5"><td ></td></tr>
				 				</table>
			           		</td>
			            </tr>
						
						<%--
						<tr>
							<td colspan="2">
								<table style="width:98%;">
								<tr>
									<td align="right">
					 				    <label class="text11Red" id="orderLineErrMsgPlaceHolder"></label>
				 				    </td>
									<td align="right">
										<input tabindex=-1 class="inputFormSubmit submitSaveClazz" type="submit" name="submit" id="submit" value='<spring:message code="systema.tror.submit.save"/>'/>
					 				
				 				    </td>
							    </tr>
							    </table>
						    </td>
						</tr>
						 --%>
						
	 				</table>
            		</td>
            </tr>
            <tr height="2"><td></td></tr>
            
			<tr height="10"><td ></td></tr>
	</table>
</form>
		
<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->
