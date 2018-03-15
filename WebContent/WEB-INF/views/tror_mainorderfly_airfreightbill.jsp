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
				 			<c:when test="${not empty Xmodel.record.df1004}">
					 			<td align="left" class="text14White">
									&nbsp;<img style="vertical-align:bottom;" src="resources/images/complete-icon.png" width="16" hight="16" border="0" alt="edit">	
									&nbsp;<spring:message code="systema.tror.fraktbrev.form.update.label.title"/>&nbsp;&nbsp;<font style="color: yellow"><b>${fn:substring(Xmodel.record.df1004, 0, 2)}&nbsp;${fn:substring(Xmodel.record.df1004, 2, 7)}&nbsp;${fn:substring(Xmodel.record.df1004, 7, 17)}</b></font>
									&nbsp;&nbsp;&nbsp;&nbsp;<font style="color: black"><b>${Xmodel.record.dfavd} / ${Xmodel.record.dfopd} / ${Xmodel.record.dffbnr} / ${Xmodel.record.dfsg}</b></font>
									&nbsp;&nbsp;
									
				 				</td>
			 				</c:when>
			 				<c:otherwise>
			 					<td align="left" class="text14White">
									&nbsp;<img style="vertical-align:bottom;" src="resources/images/bulletRed.png" width="16" hight="16" border="0" alt="edit">	
									&nbsp;<spring:message code="systema.tror.fraktbrev.form.update.label.title"/>&nbsp;&nbsp;&nbsp;&nbsp;<font style="color: yellow"><b><spring:message code="systema.tror.fraktbrev.form.update.label.title.notExist"/></b></font>
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
					
					<c:if test="${not empty Xmodel.record.df1004}">
						<input type="hidden" name="dffbnr" id="dffbnr" value='${Xmodel.record.dffbnr}'>
						<input type="hidden" name="df1004" id="df1004" value='${Xmodel.record.df1004}'>
						
						<%-- <input type="hidden" name="dfsg" id="dfsg" value='${Xmodel.record.dfsg}'> sign --%>
						<%-- <input type="hidden" name="dfst" id="dfst" value='${Xmodel.record.dfst}'> status --%>
						
					</c:if>
					
					<table class="tableBorderWithRoundCorners3D_RoundOnlyOnBottom" width="98%" align="left" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="5"><td ></td></tr>
				 		<tr>
							<td colspan="8">
								
								<table class="text12" border="0">
								
					 			<tr>
					 				<td align="left" class="text14" ><b>28.</b>&nbsp;<span title="dfbela"><spring:message code="systema.tror.fraktbrev.form.update.label.fraktbetaler"/></span></td>
					 				<td class="text11">
										<select class="inputTextMediumBlueMandatoryField" required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" name="dfbela" id="dfbela">
											<option value=''>-velg-</option>
						 					<option value='S' <c:if test="${Xmodel.record.dfbela == 'S'}"> selected </c:if> ><spring:message code="systema.tror.fraktbrev.form.update.label.fraktbetaler.seller"/></option>
				 							<option value='M' <c:if test="${Xmodel.record.dfbela == 'M'}"> selected </c:if> ><spring:message code="systema.tror.fraktbrev.form.update.label.fraktbetaler.receiver"/></option>
				 							<option value='A' <c:if test="${Xmodel.record.dfbela == 'A'}"> selected </c:if> ><spring:message code="systema.tror.fraktbrev.form.update.label.fraktbetaler.annen"/></option>
										</select>
					 				</td>
					 				<td width="5px" class="text14" >&nbsp;</td>	
									<td class="text14"><span title="dfsg"><spring:message code="systema.tror.fraktbrev.form.update.label.sign"/></span></td>
					 				<td class="text14" ><input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" type="text" class="inputTextMediumBlueMandatoryField" name="dfsg" id="dfsg" size="4" maxlength="3" value="${Xmodel.record.dfsg}"></td>
					 				
					 				<td width="20px" class="text14" >&nbsp;</td>
					 				<td class="text14"><span title="dfst"><spring:message code="systema.tror.fraktbrev.form.update.label.status"/></span></td>
					 				<td class="text14" ><input type="text" class="inputTextMediumBlue" name="dfst" id="dfst" size="2" maxlength="1" value="${Xmodel.record.dfst}"></td>
					 				<td width="5px" class="text14" >&nbsp;</td>
					 				<td class="text14" ><span title="dfkdme/dfntla"><spring:message code="systema.tror.fraktbrev.form.update.label.marknote"/>
					 					<a tabindex="-1" id="merkelappIdLink" >
 											<img align="bottom" style="cursor:pointer;" src="resources/images/find.png" height="13px" width="13px" border="0" alt="search">
 										</a> / <spring:message code="systema.tror.fraktbrev.form.update.label.marknote.qty"/></span>
 									</td>
					 				<td class="text14" >
					 					<select name="dfkdme" id="dfkdme" class="inputTextMediumBlue" >
					 						<option value="">-velg-</option>
						 				  	<c:forEach var="record" items="${model.merkelappList}" >
						 				  		<option value="${record.kfkod}"<c:if test="${Xmodel.record.dfkdme == record.kfkod}"> selected </c:if> >${record.kfkod}</option>
											</c:forEach>  
										</select>
					 					<input type="text" class="inputTextMediumBlue" name="dfntla" id="dfntla" size="5" maxlength="4" value="${Xmodel.record.dfntla}">
					 				</td>
					 				<td width="20px" class="text14" >&nbsp;</td>
					 				<td class="text14"><span title="dfkde"><spring:message code="systema.tror.fraktbrev.form.update.label.oppkrav"/></span></td>
					 				<td class="text14" >
					 					<select  name="dfkde" id="dfkde" class="inputTextMediumBlue" >
					 						<option value="">-velg-</option>
						 				  	<option value="X"<c:if test="${Xmodel.record.dfkde == 'X'}"> selected </c:if> >X</option>
										</select>
									</td>
									<td width="5px" class="text14" >&nbsp;</td>	
					 				<td class="text14"><span title="dftoll"><spring:message code="systema.tror.fraktbrev.form.update.label.tstd"/></span>
					 					<a tabindex="-1" id="dftollIdLink" >
 											<img align="bottom" style="cursor:pointer;" src="resources/images/find.png" height="13px" width="13px" border="0" alt="search">
 										</a>
					 				</td>
					 				<td class="text14" >
					 					<c:choose>
						 				<c:when test="${'0' != Xmodel.record.dftoll}">
				 							<input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="dftoll" id="dftoll" size="5" maxlength="4" value="${Xmodel.record.dftoll}">
				 						</c:when>
				 						<c:otherwise>
				 							<input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="dftoll" id="dftoll" size="5" maxlength="4" value="">
				 						</c:otherwise>
				 						</c:choose>
					 				</td>
					 				<td width="20px" class="text14" >&nbsp;</td>
					 				<td class="text14"><span title="dfcmn"><spring:message code="systema.tror.fraktbrev.form.update.label.edifact"/></span></td>
					 				<td class="text14" >
					 					<select  name="dfcmn" id="dfcmn" class="inputTextMediumBlue" >
					 						<option value="">-velg-</option>
						 				  	<option value="N"<c:if test="${Xmodel.record.dfcmn == 'N'}"> selected </c:if> >Nei</option>
						 				  	<option value="Y"<c:if test="${Xmodel.record.dfcmn == 'Y'}"> selected </c:if> >Ja</option>
										</select>
									</td>
									<td width="5px" class="text14" >&nbsp;</td>		
					 				<td class="text14"><span title="dfven"><spring:message code="systema.tror.fraktbrev.form.update.label.ventekode"/></span></td>
					 				<td class="text14" ><input type="text" class="inputTextMediumBlue" name="dfven" id="dfven" size="2" maxlength="1" value="${Xmodel.record.dfven}"></td>
					 				
					 				<td class="text14" >
					 					<input class="inputFormSubmitGray" type="button" name="backToFlyfraktbrevGateButton" id="backToFlyfraktbrevGateButton" value='Tilbake til flyfraktb.lista'>
					 				</td> 
					 				 
					 			</tr>
					 			</table>
						 			
						 		<%--	
					 			<table width="100%" border="0">
					 			<tr>
					 				<td valign="top" align="left" width="50%">
					 					<table width="100%" border="0">
					 					<tr>
					 						<td class="text12" title="hedtop"><font class="text16RedBold" >*</font><spring:message code="systema.tror.orders.form.update.label.date"/></td>
							 				<td class="text12" >	
							 					<input type="text" required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="hedtop" id="hedtop" size="9" maxlength="8" value="${XXmodel.record.hedtop}">
							 				</td>
							 				
						 				</tr>
						 				
						 				</table>
					 				</td>
					 				
					 				<td valign="top" align="left" width="50%">
						 				<table width="100%" border="0">
						 				<tr>
						 					<td class="text12" title="hekna"><spring:message code="systema.tror.orders.form.update.label.agent"/>
						 						<a tabindex="-1" id="trorAgentIdLink" >
		 											<img align="bottom" style="cursor:pointer;" src="resources/images/find.png" height="13px" width="13px" border="0" alt="search">
		 										</a>
		 									</td>
							 			</tr>
							 			
							 			</table>
						 			</td>
					 			</tr>
					 			</table>
					 			 --%>	
							</td>
						</tr>
						
						<tr height="10"><td ></td></tr>
						<tr>
							<td class="text14Bold">&nbsp;01.&nbsp;<spring:message code="systema.tror.fraktbrev.form.update.label.shipper"/></td>
							<td class="text14Bold">&nbsp;04.&nbsp;<spring:message code="systema.tror.fraktbrev.form.update.label.consignee"/></td>
						</tr>
						<tr height="5"><td ></td></tr>
						
						<tr>
				 			<td valign="top" width="50%" >
				 			 <table style="width:98%" class="tableBorderWithRoundCornersLightYellow" cellspacing="1" cellpadding="1" border="0">
						 		<tr height="10"><td ></td></tr>
						 		<tr>
					 				<td class="text12">
					 					&nbsp;<span title="dfknss"><spring:message code="systema.tror.fraktbrev.form.update.label.shipper.id"/>&nbsp;</span>
					 					<a tabindex="-1" id="trorSellerIdLink" >
 											<img align="bottom" style="cursor:pointer;" src="resources/images/find.png" height="13px" width="13px" border="0" alt="search">
 										</a>
					 				</td>
					 				<td class="text12">
					 					&nbsp;<span title="whenas"><spring:message code="systema.tror.fraktbrev.form.update.label.shipper.seller"/>&nbsp;</span>
					 	
					 				</td>
					 			</tr>
					 			<tr>	
				 					<td class="text12" ><input type="text" class="inputTextMediumBlue" name="dfknss" id="dfknss" size="10" maxlength="8" value="${Xmodel.record.dfknss}"></td>
								 	<td class="text12" ><input readonly tabindex=-1 type="text" class="inputTextReadOnly" name="whenas" id="whenas" size="50" value="${XXXXmodel.record.heknsNavn}&nbsp;-&nbsp;${XXXXmodel.record.heknsPnSt}"></td>
				 				</tr>
								<tr height="5"><td ></td></tr>
						 		<tr>
					 				<td class="text12">&nbsp;<span title="dfnavs"><spring:message code="systema.tror.fraktbrev.form.update.label.shipper.name"/></span>
					 					<%-- <c:if test="${XXXmodel.record.fakBetExists}">
						 					<a href="javascript:void(0);" onClick="window.open('tror_childwindow_customer_addresses.do?action=doFind&ctype=s&wkundnr=${user.custNr}','customerWin','top=300px,left=150px,height=800px,width=900px,scrollbars=no,status=no,location=no')">
		 										<img id="imgShipperSearch" align="bottom" style="cursor:pointer;" src="resources/images/find.png" height="13px" width="13px" border="0" alt="search">
		 									</a>
		 								</c:if> --%>
	 									<a tabindex="-1" id="trorSellerAddressesIdLink" >
											<img align="bottom" style="cursor:pointer;" src="resources/images/find.png" height="13px" width="13px" border="0" alt="search">
										</a>	 									
					 				</td>
					 				<td class="text12">&nbsp;<span title="dfad1s"><spring:message code="systema.tror.fraktbrev.form.update.label.shipper.adr1"/></span></td>
					 			</tr>
					 			<tr>	
				 					<td class="text12"><input type="text" class="inputTextMediumBlue" name="dfnavs" id="dfnavs" size="25" maxlength="30" value="${Xmodel.record.dfnavs}"></td>
				 					<td class="text12"><input type="text" class="inputTextMediumBlue" name="dfad1s" id="dfad1s" size="25" maxlength="30" value="${Xmodel.record.dfad1s}"></td>
				 				</tr>
					 			<tr>	
					 				<td class="text12">&nbsp;<span title="dfad2s"><spring:message code="systema.tror.fraktbrev.form.update.label.shipper.adr2"/></span></td>
					 				<td class="text12">&nbsp;<span title="dfpnls/dfad3s"><spring:message code="systema.tror.fraktbrev.form.update.label.shipper.adr3"/></span></td>
					 			</tr>
								<tr>	
				 					<td class="text12" >
				 					<input type="text" class="inputTextMediumBlueUPPERCASE" name="dfad2s" id="dfad2s" size="25" maxlength="30" value="${Xmodel.record.dfad2s}">
				 					</td>
				 					<td class="text12">
				 						<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="dfpnls" id="dfpnls" size="5" maxlength="4" value="${Xmodel.record.dfpnls}">
				 						<input type="text" class="inputTextMediumBlue" name="dfad3s" id="dfad3s" size="25" maxlength="30" value="${Xmodel.record.dfad3s}">
				 					</td>
				 				</tr>
				 				<tr>	
					 				<td class="text12">&nbsp;<span title="dfsref"><b>16.</b>&nbsp;<spring:message code="systema.tror.fraktbrev.form.update.label.avsRef"/></span></td>
					 				<td class="text12">&nbsp;<span title="dfbref"><b>18.</b>&nbsp;<spring:message code="systema.tror.fraktbrev.form.update.label.bokRef"/></span></td>
					 				
					 			</tr>
					 			<tr>	
				 					<td class="text11" >
				 						<input type="text" class="inputTextMediumBlue" name="dfsref" id="dfsref" size="20" maxlength="17" value="${Xmodel.record.dfsref}">
								 	</td>
								 	<td class="text11" >
				 						<input type="text" class="inputTextMediumBlue" name="dfbref" id="dfbref" size="20" maxlength="17" value="${Xmodel.record.dfbref}">
								 	</td>
								</tr> 
								
								<tr height="15"><td ></td></tr>
				 				<tr>	
					 				<td class="text12">&nbsp;<span title="ownSenderContactName"><spring:message code="systema.tror.orders.form.update.label.shipper.contactName"/></span></td>
					 				<td class="text12">&nbsp;<span title="ownSenderMobile"><spring:message code="systema.tror.orders.form.update.label.shipper.mobile"/></span></td>
		 						</tr>
		 						<tr>	
				 					<td class="text12" >
				 						<input type="hidden" name="ownSenderPartId" id="ownSenderPartId" value='CN'>
				 						<input type="text" class="inputTextMediumBlue" name="ownSenderContactName" id="ownSenderContactName" size="25" maxlength="60" value="${model.ownSenderContactName}">
				 					</td>
				 					<td class="text12">
				 						<input type="text" class="inputTextMediumBlue" name="ownSenderMobile" id="ownSenderMobile" size="25" maxlength="60" value="${model.ownSenderMobile}">
				 					</td>
		 						</tr>	
		 						<tr>	
					 				<td class="text12">&nbsp;<span title="ownSenderEmail"><spring:message code="systema.tror.orders.form.update.label.shipper.email"/></span></td>
		 						</tr>
		 						<tr>	
				 					<td class="text12" >
				 						<input type="text" class="inputTextMediumBlue" name="ownSenderEmail" id="ownSenderEmail" size="25" maxlength="60" value="${model.ownSenderEmail}">
				 					</td>
		 						</tr>	
				 				
				 				<tr height="15"><td ></td></tr>	
				 												 				
								<tr>
				 					<td class="text12Bold">&nbsp;
				 						<img style="vertical-align: bottom;" width="24px" height="24px" src="resources/images/invoice.png" border="0" alt="invoice">
				 						Oppr. avsender/Fakturasender
				 					</td>
								</tr>
				 				<tr>
				 					<td colspan="2">
				 					<table class="tableBorderWithRoundCorners" width="80%">
					 					<tr>
							 				<td class="text12">&nbsp;<span title="dffase">Navn&nbsp;</span></td>
						 				</tr>
						 				<tr>	
						 					<td class="text12" ><input  type="text" class="inputTextMediumBlue" name="dffase" id="dffase" size="30" maxlength="25" value="${Xmodel.record.dffase}"></td>
					 					</tr>
									</table>
									</td>				 				
					 			</tr>
				 				<tr height="5"><td ></td></tr>
							 </table>
						 	</td>
						 	<td valign="top" width="50%">
				 			 <table style="width:98%" class="tableBorderWithRoundCornersLightYellow" cellspacing="1" cellpadding="1">
					 			<tr height="10"><td ></td></tr>
						 		<tr>
					 				<td class="text12">
					 					&nbsp;<span title="dfknsm"><spring:message code="systema.tror.fraktbrev.form.update.label.consignee.id"/>&nbsp;</span>
					 					<a tabindex="-1" id="trorBuyerIdLink" >
 											<img align="bottom" style="cursor:pointer;" src="resources/images/find.png" height="13px" width="13px" border="0" alt="search">
 										</a>
					 				</td>
					 				<td class="text12">
					 					&nbsp;<span title="whenak"><spring:message code="systema.tror.fraktbrev.form.update.label.consignee.buyer"/>&nbsp;</span>
					 				</td>	
					 			</tr>
					 			<tr>	
				 					<td class="text12">
				 						<c:choose>
						 				<c:when test="${'0' != Xmodel.record.dfknsm}">
				 							<input type="text" class="inputTextMediumBlue" name="dfknsm" id="dfknsm" size="10" maxlength="8" value="${Xmodel.record.dfknsm}">
				 						</c:when>
				 						<c:otherwise>
				 							<input type="text" class="inputTextMediumBlue" name="dfknsm" id="dfknsm" size="10" maxlength="8" value="">
				 						</c:otherwise>
				 						</c:choose>
				 					</td>
				 					<td class="text12" ><input readonly tabindex=-1 type="text" class="inputTextReadOnly" name="whenak" id="whenak" size="50" value="${XXXmodel.record.heknkNavn}&nbsp;-&nbsp;${XXXmodel.record.heknkPnSt}"></td>
				 				</tr>
				 				<tr height="5"><td ></td></tr>
						 		<tr>
					 				<td class="text12">&nbsp;<span title="dfnavm"><spring:message code="systema.tror.fraktbrev.form.update.label.consignee.name"/></span>
					 					<%-- <c:if test="${XXXmodel.record.fakBetExists}">
						 					<a href="javascript:void(0);" onClick="window.open('tror_childwindow_customer_addresses.do?action=doFind&ctype=c&wkundnr=${user.custNr}','customerWin','top=300px,left=150px,height=800px,width=900px,scrollbars=no,status=no,location=no')">
		 										<img id="imgConsigneeSearch" align="bottom" style="cursor:pointer;" src="resources/images/find.png" height="13px" width="13px" border="0" alt="search">
		 									</a>
	 									</c:if> --%>
	 									<a tabindex="-1" id="trorBuyerAddressesIdLink" >
											<img align="bottom" style="cursor:pointer;" src="resources/images/find.png" height="13px" width="13px" border="0" alt="search">
										</a>
					 				</td>
					 				<td class="text12">&nbsp;<span title="dfad1m"><spring:message code="systema.tror.fraktbrev.form.update.label.consignee.adr1"/></span></td>
					 			</tr>
					 			<tr>	
				 					<td class="text12"><input type="text" class="inputTextMediumBlue" name="dfnavm" id="dfnavm" size="25" maxlength="30" value="${Xmodel.record.dfnavm}"></td>
				 					<td class="text12"><input type="text" class="inputTextMediumBlue" name="dfad1m" id="dfad1m" size="25" maxlength="30" value="${Xmodel.record.dfad1m}"></td>
				 				</tr>
					 			<tr>	
					 				<td class="text12">&nbsp;<span title="dfad2m"><spring:message code="systema.tror.fraktbrev.form.update.label.consignee.adr2"/></span></td>
					 				<td class="text12">&nbsp;<span title="dfad3m"><spring:message code="systema.tror.fraktbrev.form.update.label.consignee.adr3"/></span></td>
					 			</tr>
								<tr>	
				 					<td class="text12"><input type="text" class="inputTextMediumBlue" name="dfad2m" id="dfad2m" size="25" maxlength="30" value="${Xmodel.record.dfad2m}"></td>
				 					<td class="text12"><input type="text" class="inputTextMediumBlue" name="dfad3m" id="dfad3m" size="25" maxlength="30" value="${Xmodel.record.dfad3m}"></td>
				 				</tr>
				 				<tr>	
					 				<td class="text12">&nbsp;<span title="dfmref"><b>25.</b>&nbsp;<spring:message code="systema.tror.fraktbrev.form.update.label.motRef"/></span></td>
					 				
					 			</tr>
					 			<tr>	
				 					<td class="text11" >
				 						<input type="text" class="inputTextMediumBlue" name="dfmref" id="dfmref" size="20" maxlength="17" value="${Xmodel.record.dfmref}">
								 	</td>
								 </tr>
								 <tr height="15"><td ></td></tr>
				 				<tr>	
					 				<td class="text12">&nbsp;<span title="ownReceiverContactName"><spring:message code="systema.tror.orders.form.update.label.consignee.contactName"/></span></td>
					 				<td class="text12">&nbsp;<span title="ownReceiverMobile"><spring:message code="systema.tror.orders.form.update.label.consignee.mobile"/></span></td>
		 						</tr>
		 						<tr>	
				 					<td class="text12" >
				 						<input type="hidden" name="ownReceiverPartId" id="ownReceiverPartId" value='CZ'>
				 						<input type="text" class="inputTextMediumBlue" name="ownReceiverContactName" id="ownReceiverContactName" size="25" maxlength="60" value="${model.ownReceiverContactName}">
				 					</td>
				 					<td class="text12">
				 						<input type="text" class="inputTextMediumBlue" name="ownReceiverMobile" id="ownReceiverMobile" size="25" maxlength="60" value="${model.ownReceiverMobile}">
				 					</td>
		 						</tr>	
		 						<tr>	
					 				<td class="text12">&nbsp;<span title="ownReceiverEmail"><spring:message code="systema.tror.orders.form.update.label.consignee.email"/></span></td>
		 						</tr>
		 						<tr>	
				 					<td class="text12" >
				 						<input type="text" class="inputTextMediumBlue" name="ownReceiverEmail" id="ownReceiverEmail" size="25" maxlength="60" value="${model.ownReceiverEmail}">
				 					</td>
		 						</tr>	
				 				<tr height="100"><td ></td></tr>
				 				
			 				</table>
						 	</td>
					 	</tr>
					 	<tr height="5"><td ></td></tr>
					 	
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
				 							<table width="50%" >
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
				 							<table width="78%" >
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
													
										 			<td align="right" valign="bottom" class="tableHeaderFieldFirst11"><span title="dfnt1">&nbsp;<spring:message code="systema.tror.flyfraktbrev.form.update.itemlines.label.antall"/></span></td>
										 			<td align="right" valign="bottom" class="tableHeaderField11"><span title="dfvkt1">&nbsp;<spring:message code="systema.tror.flyfraktbrev.form.update.itemlines.label.vekt"/>&nbsp;</span></td>
										 			<td align="right" valign="bottom" class="tableHeaderField11"><span title="dfrc1">&nbsp;<spring:message code="systema.tror.flyfraktbrev.form.update.itemlines.label.rateClass"/></span></td>
										 			<td align="right" valign="bottom" class="tableHeaderField11"><span title="dfcom1">&nbsp;<spring:message code="systema.tror.flyfraktbrev.form.update.itemlines.label.ino"/></span></td>
										 			<td align="right" valign="bottom" class="tableHeaderField11"><span title="dffbv1">&nbsp;<spring:message code="systema.tror.flyfraktbrev.form.update.itemlines.label.fvekt"/>&nbsp;</span></td>
										 			<td align="right" valign="bottom" class="tableHeaderField11"><span title="dfrk1">&nbsp;<spring:message code="systema.tror.flyfraktbrev.form.update.itemlines.label.p"/>&nbsp;</span></td>
										 			<td align="right" valign="bottom" class="tableHeaderField11"><span title="dfrpr1">&nbsp;<spring:message code="systema.tror.flyfraktbrev.form.update.itemlines.label.rate"/>&nbsp;</span></td>
										 			<td align="right" valign="bottom" class="tableHeaderField11"><span title="dfblt1">&nbsp;<spring:message code="systema.tror.flyfraktbrev.form.update.itemlines.label.total"/>&nbsp;</span></td>
										 			<td align="left" valign="bottom" class="tableHeaderField11"><span title="dfvs1">&nbsp;<spring:message code="systema.tror.flyfraktbrev.form.update.itemlines.label.itemdesc"/>&nbsp;</span></td>
										 		</tr>
										 		<tr class="tableRow">
										 			<td align="right" class="tableCellFirst" >
									 					<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue11" style="text-align:right;" name="dfnt1" id="dfnt1" size="5" maxlength="4" value="${model.record.dfnt1}">
									 				</td>
									 				<td align="right" class="tableCell" >
									 					<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue11" style="text-align:right;" name="dfvkt1" id="dfvkt1" size="9" maxlength="8" value="${fn:replace(model.record.dfvkt1,'.',',')}">
									 				</td>
									 				<td align="right" class="tableCell" >
									 					<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue11" name="dfrc1" id="dfrc1" size="2" maxlength="1" value="${model.record.dfrc1}">
									 				</td>
									 				<td align="right" class="tableCell" >
									 					<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue11" name="dfcom1" id="dfcom1" size="5" maxlength="4" value="${model.record.dfcom1}">
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
										 			<td align="right" class="tableCellFirst" >
									 					<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue11" style="text-align:right;" name="dfnt2" id="dfnt2" size="5" maxlength="4" value="${model.record.dfnt2}">
									 				</td>
									 				<td align="right" class="tableCell" >
									 					<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue11" style="text-align:right;" name="dfvkt2" id="dfvkt2" size="9" maxlength="8" value="${fn:replace(model.record.dfvkt2,'.',',')}">
									 				</td>
									 				<td align="right" class="tableCell" >
									 					<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue11" style="text-align:right;" name="dfrc2" id="dfrc2" size="2" maxlength="1" value="${model.record.dfrc2}">
									 				</td>
									 				<td align="right" class="tableCell" >
									 					<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue11" style="text-align:right;" name="dfcom2" id="dfcom2" size="5" maxlength="4" value="${model.record.dfcom2}">
									 				</td>
									 				<td align="right" class="tableCell" >
									 					<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue11" style="text-align:right;" name="dffbv2" id="dffbv2" size="9" maxlength="8" value="${fn:replace(model.record.dffbv2,'.',',')}">
									 				</td>
									 				<td align="right" class="tableCell" >
									 					<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue11" style="text-align:right;" name="dfrk2" id="dfrk2" size="2" maxlength="1" value="${model.record.dfrk2}">
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
									 			
									 			
									 			</table>
								 			</td>
							 			</tr>
									<tr height="5"><td ></td></tr>
				 				</table>
			           		</td>
			            </tr>
						
						<tr>
							<td colspan="2">
								<table style="width:98%;">
								<tr>
									<td align="right">
					 				    <label class="text11Red" id="orderLineErrMsgPlaceHolder"></label>
				 				    </td>
									<td align="right">
										<c:if test="${not empty Xmodel.record.dfopd}">
					 				    	<input tabindex=-1 class="inputFormSubmit submitSaveClazz" type="submit" name="submit" id="submit" value='<spring:message code="systema.tror.submit.save"/>'/>
					 				    </c:if>
				 				    </td>
							    </tr>
							    </table>
						    </td>
						</tr>
						
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
