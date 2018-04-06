<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>
<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTror.jsp" />
<!-- =====================end header ==========================-->
	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/trorglobal_edit.js?ver=${user.versionEspedsg}"></SCRIPT>	
	<SCRIPT type="text/javascript" src="resources/js/tror_mainorderland_freightbill.js?ver=${user.versionEspedsg}"></SCRIPT>
	<SCRIPT type="text/javascript" src="resources/js/trorFkeys_landimport.js?ver=${user.versionEspedsg}"></SCRIPT>
	
	<%-- for dialog popup --%>
	<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
	
	<style type = "text/css">
	.ui-dialog{font-size:10pt;}
	.ui-datepicker { font-size:9pt;}
	</style>
	

<form action="tror_mainorderland_freightbill_edit.do" name="formRecord" id="formRecord" method="post">
<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
	<tr>
	<td>
	
	<%-- tab container component --%>
	<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
		<%-- This part right here allows for the dynamic allocation of a JSP depending on whether it is IMPORT or EXPORT --%>
		<c:choose>
			<c:when test="${recordOrderTrorLand.heur == 'A'}">
				<c:set var = "tabLinkJsp" scope = "request" value = "mainorderlandimport"/>	
			</c:when>
			<c:otherwise>
				<c:set var = "tabLinkJsp" scope = "request" value = "mainorderlandexport"/>
			</c:otherwise>
		</c:choose>
	
		<tr height="2"><td></td></tr>
		<tr height="25"> 
			<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a class="text14" onClick="setBlockUI(this);" href="tror_mainorderlist.do?action=doFind&sign=${recordOrderTrorLand.hesg}" > 	
					<img style="vertical-align:middle;" src="resources/images/bulletGreen.png" width="6px" height="6px" border="0" alt="open orders">
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tror.orderlist.tab"/></font>&nbsp;<font class="text10Orange">F2</font>
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a class="text14" onClick="setBlockUI(this);" href="tror_<c:out value="${tabLinkJsp}"/>.do?action=doFetch&heavd=${recordOrderTrorLand.heavd}&heopd=${recordOrderTrorLand.heopd}" > 	
					<c:choose>
						<c:when test="${recordOrderTrorLand.heur == 'A'}">
							<img style="vertical-align:middle;" src="resources/images/lorry_green.png" width="18px" height="18px" border="0" alt="update">
						</c:when>
						<c:otherwise>
							<img style="vertical-align:middle;" src="resources/images/lorry_blue.png" width="18px" height="18px" border="0" alt="update">
						</c:otherwise>
					</c:choose>
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tror.order.tab"/>&nbsp;${recordOrderTrorLand.heavd}/${recordOrderTrorLand.heopd}</font>&nbsp;<font class="text10Orange">F4</font>
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a class="text14" onClick="setBlockUI(this);" href="tror_<c:out value="${tabLinkJsp}"/>_invoice.do?action=doFetch&heavd=${recordOrderTrorLand.heavd}&heopd=${recordOrderTrorLand.heopd}" > 	
					<img style="vertical-align: bottom" src="resources/images/invoice.png" width="16" height="16" border="0" alt="show invoice">
					<font class="tabDisabledLink"><spring:message code="systema.tror.order.faktura.tab"/></font>&nbsp;<font class="text10Orange">F9</font>
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a class="text14" onClick="setBlockUI(this);" href="editNotisblock.do?action=doFetch&subsys=${subSystem}&avd=${recordOrderTrorLand.heavd}&opd=${recordOrderTrorLand.heopd}&sign=${recordOrderTrorLand.hesg}" > 	
					<img style="vertical-align: bottom" src="resources/images/veiledning.png" width="16" height="16" border="0" alt="show messages">
					<font class="tabDisabledLink"><spring:message code="systema.tror.order.notisblock.tab"/></font>&nbsp;<font class="text10Orange">F10</font>
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a class="text14" onClick="setBlockUI(this);" href="tror_mainorderland_frisokvei.do?action=doFetch&avd=${recordOrderTrorLand.heavd}&sign=${recordOrderTrorLand.hesg}&opd=${recordOrderTrorLand.heopd}">
					<img style="vertical-align: bottom" src="resources/images/lightbulb.png" width="14" height="14" border="0" alt="show log">
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tror.order.frisokvei.tab"/></font>&nbsp;<font class="text10Orange">F7</font>
				</a>
			</td>
			
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a class="text14" onClick="setBlockUI(this);" href="tror_mainorderland_budget.do?avd=${recordOrderTrorLand.heavd}&sign=${recordOrderTrorLand.hesg}&opd=${recordOrderTrorLand.heopd}">
					<img style="vertical-align: bottom" src="resources/images/budget.png" width="16" height="16" border="0" alt="show budget">
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tror.order.budget.tab"/></font>&nbsp;<font class="text10Orange">F8</font>
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabLink">&nbsp;</font></td>
			<td width="12%" valign="bottom" class="tab" align="center" nowrap>
				<a class="text14" onClick="setBlockUI(this);" href="tror_mainorderland_freightbill_gate.do?dfavd=${recordOrderTrorLand.heavd}&sign=${recordOrderTrorLand.hesg}&dfopd=${recordOrderTrorLand.heopd}">
					<img style="vertical-align:bottom;" src="resources/images/fraktbrev.png" width="16" hight="16" border="0" alt="edit">
					<font class="tabLink">&nbsp;<spring:message code="systema.tror.order.fraktbrev.tab"/></font>&nbsp;<font class="text10Orange">F3</font>
				</a>					
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a class="text14" onClick="setBlockUI(this);" href="tror_mainorderland_ttrace.do?subsys=tror_li&avd=${recordOrderTrorLand.heavd}&sign=${recordOrderTrorLand.hesg}&opd=${recordOrderTrorLand.heopd}">
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
				 			<c:when test="${not empty model.record.df1004}">
					 			<td align="left" class="text14White">
									&nbsp;<img style="vertical-align:bottom;" src="resources/images/complete-icon.png" width="16" hight="16" border="0" alt="edit">	
									&nbsp;<spring:message code="systema.tror.fraktbrev.form.update.label.title"/>&nbsp;&nbsp;<font style="color: yellow"><b>${fn:substring(model.record.df1004, 0, 2)}&nbsp;${fn:substring(model.record.df1004, 2, 7)}&nbsp;${fn:substring(model.record.df1004, 7, 17)}</b></font>
									&nbsp;&nbsp;&nbsp;&nbsp;<font style="color: black"><b>${model.record.dfavd} / ${model.record.dfopd} / ${model.record.dffbnr} / ${model.record.dfsg}</b></font>
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
					<input type="hidden" name="dfavd" id="dfavd" value='${recordOrderTrorLand.heavd}'>
					<input type="hidden" name="dfopd" id="dfopd" value='${recordOrderTrorLand.heopd}'>
					<input type="hidden" name="sign" id="sign" value='${recordOrderTrorLand.hesg}'>
						
					<%-- dfri = F as offsett. Always. Old rule in order to acquire status "active" ... --%>
					<input type="hidden" name="dfri" id="dfri" value='F'> 
					<%--for F-Keys shortcuts. Used only in trorFkeys_...js --%>
					<input type="hidden" name="fkeysavd" id="fkeysavd" value='${recordOrderTrorLand.heavd}'>
					<input type="hidden" name="fkeysopd" id="fkeysopd" value='${recordOrderTrorLand.heopd}'>
					<input type="hidden" name="fkyessign" id="fkyessign" value='${recordOrderTrorLand.hesg}'>
					<c:choose>
						<c:when test="${recordOrderTrorLand.heur == 'A'}">
							<input type="hidden" name="fkyessubsys" id="fkyessubsys" value='mainorderlandimport'>
						</c:when>
						<c:otherwise>
							<input type="hidden" name="fkyessubsys" id="fkyessubsys" value='mainorderlandexport'>
						</c:otherwise>
					</c:choose>
					
					<c:if test="${not empty model.record.df1004}">
						<input type="hidden" name="dffbnr" id="dffbnr" value='${model.record.dffbnr}'>
						<input type="hidden" name="df1004" id="df1004" value='${model.record.df1004}'>
						
						<%-- <input type="hidden" name="dfsg" id="dfsg" value='${model.record.dfsg}'> sign --%>
						<%-- <input type="hidden" name="dfst" id="dfst" value='${model.record.dfst}'> status --%>
						
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
						 					<option value='S' <c:if test="${model.record.dfbela == 'S'}"> selected </c:if> ><spring:message code="systema.tror.fraktbrev.form.update.label.fraktbetaler.seller"/></option>
				 							<option value='M' <c:if test="${model.record.dfbela == 'M'}"> selected </c:if> ><spring:message code="systema.tror.fraktbrev.form.update.label.fraktbetaler.receiver"/></option>
				 							<option value='A' <c:if test="${model.record.dfbela == 'A'}"> selected </c:if> ><spring:message code="systema.tror.fraktbrev.form.update.label.fraktbetaler.annen"/></option>
										</select>
					 				</td>
					 				<td width="5px" class="text14" >&nbsp;</td>	
									<td class="text14"><span title="dfsg"><spring:message code="systema.tror.fraktbrev.form.update.label.sign"/></span></td>
					 				<td class="text14" ><input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" type="text" class="inputTextMediumBlueMandatoryField" name="dfsg" id="dfsg" size="4" maxlength="3" value="${model.record.dfsg}"></td>
					 				
					 				<td width="20px" class="text14" >&nbsp;</td>
					 				<td class="text14"><span title="dfst"><spring:message code="systema.tror.fraktbrev.form.update.label.status"/></span></td>
					 				<td class="text14" ><input type="text" class="inputTextMediumBlue" name="dfst" id="dfst" size="2" maxlength="1" value="${model.record.dfst}"></td>
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
						 				  		<option value="${record.kfkod}"<c:if test="${model.record.dfkdme == record.kfkod}"> selected </c:if> >${record.kfkod}</option>
											</c:forEach>  
										</select>
					 					<input type="text" class="inputTextMediumBlue" name="dfntla" id="dfntla" size="5" maxlength="4" value="${model.record.dfntla}">
					 				</td>
					 				<td width="20px" class="text14" >&nbsp;</td>
					 				<td class="text14"><span title="dfkde"><spring:message code="systema.tror.fraktbrev.form.update.label.oppkrav"/></span></td>
					 				<td class="text14" >
					 					<select  name="dfkde" id="dfkde" class="inputTextMediumBlue" >
					 						<option value="">-velg-</option>
						 				  	<option value="X"<c:if test="${model.record.dfkde == 'X'}"> selected </c:if> >X</option>
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
						 				<c:when test="${'0' != model.record.dftoll}">
				 							<input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="dftoll" id="dftoll" size="5" maxlength="4" value="${model.record.dftoll}">
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
						 				  	<option value="N"<c:if test="${model.record.dfcmn == 'N'}"> selected </c:if> >Nei</option>
						 				  	<option value="Y"<c:if test="${model.record.dfcmn == 'Y'}"> selected </c:if> >Ja</option>
										</select>
									</td>
									<td width="5px" class="text14" >&nbsp;</td>		
					 				<td class="text14"><span title="dfven"><spring:message code="systema.tror.fraktbrev.form.update.label.ventekode"/></span></td>
					 				<td class="text14" ><input type="text" class="inputTextMediumBlue" name="dfven" id="dfven" size="2" maxlength="1" value="${model.record.dfven}"></td>
					 				 
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
							 					<input type="text" required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="hedtop" id="hedtop" size="9" maxlength="8" value="${Xmodel.record.hedtop}">
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
				 					<td class="text12" ><input type="text" class="inputTextMediumBlue" name="dfknss" id="dfknss" size="10" maxlength="8" value="${model.record.dfknss}"></td>
								 	<td class="text12" ><input readonly tabindex=-1 type="text" class="inputTextReadOnly" name="whenas" id="whenas" size="50" value="${XXXmodel.record.heknsNavn}&nbsp;-&nbsp;${XXXmodel.record.heknsPnSt}"></td>
				 				</tr>
								<tr height="5"><td ></td></tr>
						 		<tr>
					 				<td class="text12">&nbsp;<span title="dfnavs"><spring:message code="systema.tror.fraktbrev.form.update.label.shipper.name"/></span>
					 					<%-- <c:if test="${XXmodel.record.fakBetExists}">
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
				 					<td class="text12"><input type="text" class="inputTextMediumBlue" name="dfnavs" id="dfnavs" size="25" maxlength="30" value="${model.record.dfnavs}"></td>
				 					<td class="text12"><input type="text" class="inputTextMediumBlue" name="dfad1s" id="dfad1s" size="25" maxlength="30" value="${model.record.dfad1s}"></td>
				 				</tr>
					 			<tr>	
					 				<td class="text12">&nbsp;<span title="dfad2s"><spring:message code="systema.tror.fraktbrev.form.update.label.shipper.adr2"/></span></td>
					 				<td class="text12">&nbsp;<span title="dfpnls/dfad3s"><spring:message code="systema.tror.fraktbrev.form.update.label.shipper.adr3"/></span></td>
					 			</tr>
								<tr>	
				 					<td class="text12" >
				 					<input type="text" class="inputTextMediumBlueUPPERCASE" name="dfad2s" id="dfad2s" size="25" maxlength="30" value="${model.record.dfad2s}">
				 					</td>
				 					<td class="text12">
				 						<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="dfpnls" id="dfpnls" size="5" maxlength="4" value="${model.record.dfpnls}">
				 						<input type="text" class="inputTextMediumBlue" name="dfad3s" id="dfad3s" size="25" maxlength="30" value="${model.record.dfad3s}">
				 					</td>
				 				</tr>
				 				<tr>	
					 				<td class="text12">&nbsp;<span title="dfsref"><b>16.</b>&nbsp;<spring:message code="systema.tror.fraktbrev.form.update.label.avsRef"/></span></td>
					 				<td class="text12">&nbsp;<span title="dfbref"><b>18.</b>&nbsp;<spring:message code="systema.tror.fraktbrev.form.update.label.bokRef"/></span></td>
					 				
					 			</tr>
					 			<tr>	
				 					<td class="text11" >
				 						<input type="text" class="inputTextMediumBlue" name="dfsref" id="dfsref" size="20" maxlength="17" value="${model.record.dfsref}">
								 	</td>
								 	<td class="text11" >
				 						<input type="text" class="inputTextMediumBlue" name="dfbref" id="dfbref" size="20" maxlength="17" value="${model.record.dfbref}">
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
						 					<td class="text12" ><input  type="text" class="inputTextMediumBlue" name="dffase" id="dffase" size="30" maxlength="25" value="${model.record.dffase}"></td>
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
						 				<c:when test="${'0' != model.record.dfknsm}">
				 							<input type="text" class="inputTextMediumBlue" name="dfknsm" id="dfknsm" size="10" maxlength="8" value="${model.record.dfknsm}">
				 						</c:when>
				 						<c:otherwise>
				 							<input type="text" class="inputTextMediumBlue" name="dfknsm" id="dfknsm" size="10" maxlength="8" value="">
				 						</c:otherwise>
				 						</c:choose>
				 					</td>
				 					<td class="text12" ><input readonly tabindex=-1 type="text" class="inputTextReadOnly" name="whenak" id="whenak" size="50" value="${XXmodel.record.heknkNavn}&nbsp;-&nbsp;${XXmodel.record.heknkPnSt}"></td>
				 				</tr>
				 				<tr height="5"><td ></td></tr>
						 		<tr>
					 				<td class="text12">&nbsp;<span title="dfnavm"><spring:message code="systema.tror.fraktbrev.form.update.label.consignee.name"/></span>
					 					<%-- <c:if test="${XXmodel.record.fakBetExists}">
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
				 					<td class="text12"><input type="text" class="inputTextMediumBlue" name="dfnavm" id="dfnavm" size="25" maxlength="30" value="${model.record.dfnavm}"></td>
				 					<td class="text12"><input type="text" class="inputTextMediumBlue" name="dfad1m" id="dfad1m" size="25" maxlength="30" value="${model.record.dfad1m}"></td>
				 				</tr>
					 			<tr>	
					 				<td class="text12">&nbsp;<span title="dfad2m"><spring:message code="systema.tror.fraktbrev.form.update.label.consignee.adr2"/></span></td>
					 				<td class="text12">&nbsp;<span title="dfad3m"><spring:message code="systema.tror.fraktbrev.form.update.label.consignee.adr3"/></span></td>
					 			</tr>
								<tr>	
				 					<td class="text12"><input type="text" class="inputTextMediumBlue" name="dfad2m" id="dfad2m" size="25" maxlength="30" value="${model.record.dfad2m}"></td>
				 					<td class="text12"><input type="text" class="inputTextMediumBlue" name="dfad3m" id="dfad3m" size="25" maxlength="30" value="${model.record.dfad3m}"></td>
				 				</tr>
				 				<tr>	
					 				<td class="text12">&nbsp;<span title="dfmref"><b>25.</b>&nbsp;<spring:message code="systema.tror.fraktbrev.form.update.label.motRef"/></span></td>
					 				
					 			</tr>
					 			<tr>	
				 					<td class="text11" >
				 						<input type="text" class="inputTextMediumBlue" name="dfmref" id="dfmref" size="20" maxlength="17" value="${model.record.dfmref}">
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
				 			<td valign="top" width="50%" >
				 			<table width="98%">
							 	<tr>
				 					<td class="text14Bold">&nbsp;
				 						<img style="vertical-align: bottom;" width="24px" height="24px" src="resources/images/lorry.png" border="0" alt="lorry">
				 						10.&nbsp;<spring:message code="systema.tror.fraktbrev.form.update.label.carrier"/>
				 					</td>
								</tr>
				 				<tr>
				 					<td colspan="8">
				 					<table class="tableBorderWithRoundCorners" width="100%">
					 					<tr>
					 					
							 				<td class="text12">&nbsp;<span title="dftran"><spring:message code="systema.tror.fraktbrev.form.update.label.carrier.id"/>&nbsp;</span>
							 					<a tabindex="-1" id="trorCarrierIdLink" >
		 											<img align="bottom" style="cursor:pointer;" src="resources/images/find.png" height="13px" width="13px" border="0" alt="search">
		 										</a>
							 				</td>
							 				<td class="text12" >
							 					<c:choose>
								 				<c:when test="${'0' != model.record.dftran}">
						 							<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="dftran" id="dftran" size="9" maxlength="8" value="${model.record.dftran}">
						 						</c:when>
						 						<c:otherwise>
						 							<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="dftran" id="dftran" size="9" maxlength="8" value="">
						 						</c:otherwise>
						 						</c:choose>
							 				</td>
							 				
						 					<td class="text12">&nbsp;<span title="dfnat"><spring:message code="systema.tror.fraktbrev.form.update.label.carrier.name"/>&nbsp;</span></td>
							 				<td class="text12" ><input  type="text" class="inputTextMediumBlue" name="dfnat" id="dfnat" size="21" maxlength="20" value="${model.record.dfnat}"></td>
							 				<td class="text12">&nbsp;<span title="dfknm"><b>23.</b>&nbsp;<spring:message code="systema.tror.fraktbrev.form.update.label.carrier.kundnr.transport"/>&nbsp;</span></td>
							 				<td class="text12" ><input  type="text" class="inputTextMediumBlue" name="dfknm" id="dfknm" size="15" maxlength="12" value="${model.record.dfknm}"></td>
						 				</tr>
						 				<tr>
							 				<td class="text12">&nbsp;<span title="dfprok"><b>14.</b>&nbsp;<spring:message code="systema.tror.fraktbrev.form.update.label.carrier.productCode"/>&nbsp;</span></td>
							 				<td colspan="2" class="text12" >
							 					<select name="dfprok" id="dfprok" class="inputTextMediumBlue" >
							 						<option value="">-velg-</option>
								 				  	<c:forEach var="record" items="${model.produktList}" >
								 				  		<option value='${record.kfkod}' <c:if test="${record.kfkod == model.record.dfprok}"> selected </c:if> >${record.kfkod}</option>
													</c:forEach>  
												</select>
												<a href="javascript:void(0);" onClick="window.open('tror_mainorder_childwindow_productcodes_landimport_fraktbrev.do?action=doFind&ctype=landimport_fb','customerWin','top=300px,left=150px,height=600px,width=800px,scrollbars=no,status=no,location=no')">
	 												<img id="imgProductcodes" align="bottom" style="cursor:pointer;" src="resources/images/find.png" height="13px" width="13px" border="0" alt="search">
	 											</a>
							 				</td>
							 			</tr>
							 			<tr>	
						 					<td class="text12">&nbsp;<span title="dfpro1"><spring:message code="systema.tror.fraktbrev.form.update.label.carrier.productCode.txt1"/>&nbsp;</span></td>
							 				<td colspan="4" class="text12" ><input  type="text" class="inputTextMediumBlue" name="dfpro1" id="dfpro1" size="35" maxlength="30" value="${model.record.dfpro1}"></td>
							 			</tr>
							 			<tr height="29"><td ></td></tr>
									</table>
									</td>				 				
					 			</tr>
						 	</table>
						 	</td>
						 	<td valign="top" width="50%" >
				 			<table width="98%">
						 		<tr>
				 					<td class="text14Bold">&nbsp;
				 						<img style="vertical-align: bottom;" width="20px" height="24px" src="resources/images/addressIcon.png" border="0" alt="del.address">
				 						07. / 09.&nbsp;<spring:message code="systema.tror.fraktbrev.form.update.label.deliveryaddress"/>
				 					</td>
								</tr>
				 				<tr>
				 					<td colspan="2">
				 					<table class="tableBorderWithRoundCorners" width="100%">
					 					<tr>
							 				<td class="text12">&nbsp;<span title="dfnavl"><spring:message code="systema.tror.fraktbrev.form.update.label.deliveryaddress.name"/>&nbsp;</span></td>
							 				<td class="text12">&nbsp;<span title="dfad1l"><spring:message code="systema.tror.fraktbrev.form.update.label.deliveryaddress.adr1"/>&nbsp;</span></td>
							 				<td class="text12">&nbsp;<span title="dfad1l"><spring:message code="systema.tror.fraktbrev.form.update.label.deliveryaddress.adr2"/>&nbsp;</span></td>
						 				</tr>
						 				<tr>	
						 					<td class="text12" ><input  type="text" class="inputTextMediumBlue" name="dfnavl" id="dfnavl" size="30" maxlength="30" value="${model.record.dfnavl}"></td>
						 					<td class="text12" ><input  type="text" class="inputTextMediumBlue" name="dfad1l" id="dfad1l" size="25" maxlength="30" value="${model.record.dfad1l}"></td>
						 					<td class="text12" ><input  type="text" class="inputTextMediumBlue" name="dfad2l" id="dfad2l" size="25" maxlength="30" value="${model.record.dfad2l}"></td>
					 					</tr>
					 					<tr>
							 				<td class="text12">&nbsp;<span title="dfpoul"><spring:message code="systema.tror.fraktbrev.form.update.label.deliveryaddress.postcode"/></span>
							 				<a tabindex="-1" id="trorPostalCodePonrnIdLink" >
	 											<img align="bottom" style="cursor:pointer;" src="resources/images/find.png" height="13px" width="13px" border="0" alt="search">
	 										</a>
							 				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span title="dfadul"><spring:message code="systema.tror.fraktbrev.form.update.label.deliveryaddress.adr3"/></span>&nbsp;</td>
						 				</tr>
						 				<tr>	
						 					<td colspan="2" class="text12" >
						 						<c:choose>
								 				<c:when test="${'0' != model.record.dfpoul}">
						 							<input  onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="dfpoul" id="dfpoul" size="5" maxlength="4" value="${model.record.dfpoul}">
						 						</c:when>
						 						<c:otherwise>
						 							<input  onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="dfpoul" id="dfpoul" size="5" maxlength="4" value="">
						 						</c:otherwise>
						 						</c:choose>
						 						
						 						<input  type="text" class="inputTextMediumBlue" name="dfadul" id="dfadul" size="25" maxlength="25" value="${model.record.dfadul}">
						 					</td>
					 					</tr>
					 					<tr height="12"><td ></td></tr>
									</table>
									</td>				 				
					 			</tr>
 							</table>
 							</td>
					 	</tr>
					 	
					 	<tr height="1"><td ></td></tr>
					 	<tr>
				 			<td colspan="2" valign="top" width="100%" >
				 			<table width="99%">
							 	<tr>
				 					<td class="text14Bold">&nbsp;
				 						<img style="vertical-align: bottom;" width="22px" height="22px" src="resources/images/containerYellow.png" border="0" alt="freight">
				 						19 / 20&nbsp;<spring:message code="systema.tror.fraktbrev.form.update.label.annenfraktbetaleraddress"/>
				 					</td>
								</tr>
				 				<tr>
				 					<td colspan="8">
				 					<table class="tableBorderWithRoundCorners" width="100%">
					 					<tr>
					 						<td class="text12">&nbsp;<span title="dfknsx"><spring:message code="systema.tror.fraktbrev.form.update.label.annenfraktbetaleraddress.knr"/>&nbsp;</span>
					 							<a tabindex="-1" id="trorAnnenFraktbetalerIdLink" >
													<img align="bottom" style="cursor:pointer;" src="resources/images/find.png" height="13px" width="13px" border="0" alt="search">
												</a>	
					 						</td>
							 				<td class="text12">&nbsp;<span title="dfknx"><spring:message code="systema.tror.fraktbrev.form.update.label.annenfraktbetaleraddress.knr.carrier"/>&nbsp;</span></td>
							 				<td class="text12">&nbsp;<span title="dfnavx"><spring:message code="systema.tror.fraktbrev.form.update.label.annenfraktbetaleraddress.name"/>&nbsp;</span></td>
							 				<td class="text12">&nbsp;<span title="dfad1x"><spring:message code="systema.tror.fraktbrev.form.update.label.annenfraktbetaleraddress.adr1"/>&nbsp;</span></td>
							 				<td class="text12">&nbsp;<span title="dfad2x"><spring:message code="systema.tror.fraktbrev.form.update.label.annenfraktbetaleraddress.adr2"/>&nbsp;</span></td>
							 				<td class="text12">&nbsp;<span title="dfad3x"><spring:message code="systema.tror.fraktbrev.form.update.label.annenfraktbetaleraddress.adr3"/></span>&nbsp;</td>
							 				
						 				</tr>
						 				<tr>
					 						<td class="text12" >
							 					<c:choose>
									 				<c:when test="${'0' != model.record.dfknsx}">
							 							<input  onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="dfknsx" id="dfknsx" size="8" maxlength="8" value="${model.record.dfknsx}">
							 						</c:when>
							 						<c:otherwise>
							 							<input  onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="dfknsx" id="dfknsx" size="8" maxlength="8" value="">
							 						</c:otherwise>
						 						</c:choose>
						 					</td>
						 					<td class="text12" ><input  type="text" class="inputTextMediumBlue" name="dfknx" id="dfknx" size="10" maxlength="12" value="${model.record.dfknx}"></td>
						 					<td class="text12" ><input  type="text" class="inputTextMediumBlue" name="dfnavx" id="dfnavx" size="30" maxlength="30" value="${model.record.dfnavx}"></td>
						 					<td class="text12" ><input  type="text" class="inputTextMediumBlue" name="dfad1x" id="dfad1x" size="25" maxlength="30" value="${model.record.dfad1x}"></td>
						 					<td class="text12" ><input  type="text" class="inputTextMediumBlue" name="dfad2x" id="dfad2x" size="25" maxlength="30" value="${model.record.dfad2x}"></td>
						 					<td class="text12" ><input  type="text" class="inputTextMediumBlue" name="dfad3x" id="dfad3x" size="25" maxlength="30" value="${model.record.dfad3x}"></td>
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
				 		<tr>
							<td valign="top" style="width:100%;border-right:1px solid;border-color:#FFFFFF;"  >
								<table border="0">
						 		<tr height="2"><td ></td></tr>
							 	<tr>	
						 			<td class="text12">
						 				<span title="dffvcu/dffvbl"><b>24.</b>&nbsp;<spring:message code="systema.tror.fraktbrev.form.update.label.vareforsverdi"/></span>
				 					</td>
					 				<td class="text12">
					 					<input type="text" class="inputTextMediumBlue" name="dffvcu" id="dffvcu" size="4" maxlength="3" value="${model.record.dffvcu}">
					 				</td>
						 			<td class="text12">
						 				<c:choose>
						 				<c:when test="${'0' != model.record.dffvbl}">
				 							<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="dffvbl" id="dffvbl" size="12" maxlength="11" value="${fn:replace(model.record.dffvbl,'.',',')}">
				 						</c:when>
				 						<c:otherwise>
				 							<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="dffvbl" id="dffvbl" size="12" maxlength="11" value="">
				 						</c:otherwise>
				 						</c:choose>
				 						
					 				</td>
					 				<td class="text12">
						 				<span title="dffvcu"><spring:message code="systema.tror.fraktbrev.form.update.label.category"/></span>
				 					</td>
					 				<td class="text12">
					 					<input type="text" class="inputTextMediumBlue" name="dffkat" id="dffkat" size="5" maxlength="4" value="${model.record.dffkat}">
					 				</td>
						 			<td class="text12">
						 				<span title="dfpoli"><b>26.</b>&nbsp;<spring:message code="systema.tror.fraktbrev.form.update.label.polis"/></span>
				 					</td>
					 				<td class="text12">
					 					<input type="text" class="inputTextMediumBlue" name="dfpoli" id="dfpoli" size="15" maxlength="14" value="${model.record.dfpoli}">
					 				</td>
						 			<td class="text12">
					 					<input type="text" class="inputTextMediumBlue" name="dfskfo" id="dfskfo" size="2" maxlength="1" value="${model.record.dfskfo}">
					 				</td>
					 				<td class="text12">
						 				<span title="dflevb"><b>27.</b>&nbsp;<spring:message code="systema.tror.fraktbrev.form.update.label.levbetingelser"/></span>
				 					</td>
					 				<td class="text12">
					 					<input type="text" class="inputTextMediumBlue" name="dflevb" id="dflevb" size="4" maxlength="3" value="${model.record.dflevb}">
					 				</td>
						 			<td class="text12">
					 					<input type="text" class="inputTextMediumBlue" name="dflevt" id="dflevt" size="21" maxlength="20" value="${model.record.dflevt}">
					 				</td>
					 				<td class="text12">
						 				<span title="dflevi"><b>30.</b>&nbsp;<spring:message code="systema.tror.fraktbrev.form.update.label.levintr"/></span>
				 					</td>
					 				<td class="text12">
					 					<input type="text" class="inputTextMediumBlue" name="dflevi" id="dflevi" size="30" maxlength="30" value="${model.record.dflevi}">
					 				</td>
						 			
					 			</tr>
					 			<tr>
					 				
					 				<td class="text12">
						 				<span title="dfnteu"><b>12.</b>&nbsp;<spring:message code="systema.tror.fraktbrev.form.update.label.utv.pll"/></span>
				 					</td>
					 				<td class="text12">
					 					<c:choose>
						 				<c:when test="${'0' != model.record.dfnteu}">
				 							<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="dfnteu" id="dfnteu" size="4	" maxlength="2" value="${model.record.dfnteu}">
				 						</c:when>
				 						<c:otherwise>
				 							<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="dfnteu" id="dfnteu" size="4	" maxlength="2" value="">
				 						</c:otherwise>
				 						</c:choose>
					 					
					 				</td>
						 			<td class="text12">&nbsp;</td>
						 			<td class="text12">
						 				<span title="dfntau"><b>13.</b>&nbsp;<spring:message code="systema.tror.fraktbrev.form.update.label.andre.pll"/></span>
				 					</td>
					 				<td class="text12">
					 					<c:choose>
						 				<c:when test="${'0' != model.record.dfntau}">
				 							<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="dfntau" id="dfntau" size="5" maxlength="2" value="${model.record.dfntau}">
				 						</c:when>
				 						<c:otherwise>
				 							<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="dfntau" id="dfntau" size="5" maxlength="2" value="">
				 						</c:otherwise>
				 						</c:choose>
					 				</td>
					 				<td class="text12">
						 				<span title="dfbele">Oppkravs Beløp</span>&nbsp;
						 				
				 					</td>
					 				<td class="text12">
						 				<input readonly type="text" class="inputTextReadOnly" name="dfbele" id="dfbele" size="15" maxlength="20" value="${ fn:replace(model.record.dfbele,'.',',') }">
				 					</td>
				 					<td class="text12">&nbsp;</td>
				 					<td class="text12">&nbsp;<span title="dfpro2"><spring:message code="systema.tror.fraktbrev.form.update.label.carrier.productCode.txt2"/>&nbsp;</span></td>
					 				<td colspan="4" class="text12" ><input  type="text" class="inputTextMediumBlue" name="dfpro2" id="dfpro2" size="30" maxlength="30" value="${model.record.dfpro2}"></td>
						 		</tr>
					 			</table>
							</td>

						</tr>
						
						<tr height="5"><td colspan="2" ></td></tr>
						<tr height="1"><td colspan="2" style="border-bottom:1px solid;border-color:#FFFFFF;" class="text"></td></tr>
						<tr height="3"><td colspan="2" ></td></tr>
						
						
						<tr>
			            		<td>
				        			<table width="98%" id="containerdatatableTable" cellspacing="0" align="left" >
			
									<%-- <c:if test="${not empty XXmodel.record.heunik}"> --%>
										<tr height="5"><td align="left" ></td></tr>
										
								 		<tr >
											<td align="bottom" colspan="3" class="text11">&nbsp;&nbsp;<img style="vertical-align:middle;" src="resources/images/update.gif" width="10px" height="10px" border="0" alt="update item line">
											&nbsp;<spring:message code="systema.tror.fraktbrev.form.detail.update.label.itemLine"/>
											<c:if test="${not empty model.record.df1004}">
												&nbsp;&nbsp;<button name="dokufmButton" id="dokufmButton" class="buttonGrayWithGreenFrame" type="button" >Merke, snd.nivå</button>
											</c:if>
											</td>
										</tr>
								 		<tr>
											<td colspan="2" style="padding: 0px;">
												<table align="left" border="0" style="width:100%;" >
												<tr class="tableHeaderField10" >
													
										 			<td align="left" valign="bottom" class="tableHeaderFieldFirst11"><span title="dfgm/dfgm2">&nbsp;<b>33.</b><spring:message code="systema.tror.fraktbrev.form.detail.update.label.marks"/></span></td>
										 			<td align="right" valign="bottom" class="tableHeaderField11"><span title="dfnt">&nbsp;<font class="text12RedBold" >*</font><b>34.</b><spring:message code="systema.tror.fraktbrev.form.detail.update.label.antal"/>&nbsp;</span></td>
										 			<td align="center" valign="bottom" class="tableHeaderField11"><span title="ownEnhet1/ownEnhet2">&nbsp;<b>35.</b><spring:message code="systema.tror.fraktbrev.form.detail.update.label.forpak"/></span></td>
										 			<td align="left" valign="bottom" class="tableHeaderField11"><span title="dfvs/dfvs2">&nbsp;<b>35.</b><spring:message code="systema.tror.fraktbrev.form.detail.update.label.goodsDesc"/></span></td>
										 			<td align="right" valign="bottom" class="tableHeaderField11"><span title="dfvkt/dfvktf">&nbsp;<b>36.</b><spring:message code="systema.tror.fraktbrev.form.detail.update.label.weight"/>&nbsp;</span></td>
										 			<td align="right" valign="bottom" class="tableHeaderField11"><span title="dfm3">&nbsp;<b>37.</b><spring:message code="systema.tror.fraktbrev.form.detail.update.label.m3"/>&nbsp;</span></td>
										 			<td align="right" valign="bottom" class="tableHeaderField11"><span title="dflm">&nbsp;<b>37.</b><spring:message code="systema.tror.fraktbrev.form.detail.update.label.lm.fa"/>&nbsp;</span></td>
										 		</tr>
										 		<tr class="tableRow">
										 			<td align="left" class="tableCell" nowrap>
									 					<input type="text" class="inputTextMediumBlue11" name="dfgm" id="dfgm" size="15" maxlength="12" value="${model.record.dfgm}">
									 				</td>
									 				<td align="right" class="tableCell" nowrap>
										 				<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue11" style="text-align:right;" name="dfnt" id="dfnt" size="8" maxlength="7" value="${model.record.dfnt}">
									 				</td>
									 				<td align="center" class="tableCell" nowrap>
										 				<select name="ownEnhet1" id="ownEnhet1">
										 					<option value="" >-velg-</option>
										 					<c:forEach var="record" items="${model.enhetList}" varStatus="counter">
										 						<c:choose>
										 							<c:when test="${not empty model.uom1 }" >
											 							<option value='${record.tkkode}' <c:if test="${ model.uom1 == record.tkkode }"> selected </c:if> >${record.tkkode}</option>
											 						</c:when>
											 						<c:otherwise>
											 							<option value='${record.tkkode}'>${record.tkkode}</option>
											 						</c:otherwise>
										 						</c:choose>
										 					</c:forEach>
														</select>
														
														<a href="javascript:void(0);" onClick="window.open('tror_mainorder_childwindow_unitcodes.do?action=doFind&ctype=tror_landimport_e1','unitWin','top=300px,left=150px,height=600px,width=800px,scrollbars=no,status=no,location=no')">
				 											<img id="imgTransporttype" align="bottom" style="cursor:pointer;" src="resources/images/find.png" height="12px" width="12px" border="0" alt="search">
				 										</a>
														
									 				</td>
									 				<td align="left" class="tableCell" nowrap>
									 					<fmt:parseNumber scope="request" var="uom1Length" type="number" value="${model.uom1Length}" />
									 					<c:choose>
									 						<c:when test="${not empty model.uom1 && not empty model.record.dfvs && fn:length(model.record.dfvs) > uom1Length}" >
										 						<input type="text" class="inputTextMediumBlue11" name="dfvs" id="dfvs" size="30" maxlength="25" value="${ fn:substring(model.record.dfvs, uom1Length, fn:length(model.record.dfvs)) }">		
										 					</c:when>
										 					<c:otherwise>
										 						<input title="plaitxt" type="text" class="inputTextMediumBlue11" name="dfvs" id="dfvs" size="30" maxlength="25" value="${ model.record.dfvs }">
										 					</c:otherwise>
									 					</c:choose>
									 				</td>
									 				<td align="right" class="tableCell" nowrap>
										 				<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue11" style="text-align:right;" name="dfvkt" id="dfvkt" size="10" maxlength="9" value="${model.record.dfvkt}">
									 				</td>
									 				
									 				<td align="right" class="tableCell" nowrap>
										 				<input onFocus="calculateVolume(this);" onBlur="checkVolumeNewLine(this);" onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue11" style="text-align:right;" name="dfm3" id="dfm3" size="12" maxlength="11" value="${fn:replace(model.record.dfm3,'.',',')}">
									 				</td>
									 				<td align="right" class="tableCell" nowrap>
										 				<input onBlur="checkLmNewLine(this);validateNewItemLine();" onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue11" style="text-align:right;" name="dflm" id="dflm" size="8" maxlength="7" value="${fn:replace(model.record.dflm,'.',',')}">
									 				</td>
									 			</tr>
									 			<tr class="tableRow">
										 			<td align="left" class="tableCell" nowrap>
									 					<input type="text" class="inputTextMediumBlue11" name="dfgm2" id="dfgm2" size="15" maxlength="12" value="${model.record.dfgm2}">
									 				</td>
									 				<td align="right" class="tableCell" nowrap>&nbsp;</td>
									 				<td align="center" class="tableCell" >
									 					<%-- OBSOLETE ?
										 				<select name="ownEnhet2" id="ownEnhet2">
										 					<option value="" >-velg-</option>
										 					<c:forEach var="record" items="${model.enhetList}" varStatus="counter">
											 					<c:choose>
											 						<c:when test="${not empty model.record.dfvs2 && fn:length(model.record.dfvs2) > 2}" >
											 							<option value='${record.tkkode}' <c:if test="${ fn:substring(model.record.dfvs2, 0, 2) == record.tkkode }"> selected </c:if> >${record.tkkode}</option>
											 						</c:when>
											 						<c:otherwise>
											 							<option value='${record.tkkode}' >${record.tkkode}</option>
											 						</c:otherwise>
										 						</c:choose>
									 						</c:forEach>
														</select>
														<a href="javascript:void(0);" onClick="window.open('tror_mainorder_childwindow_unitcodes.do?action=doFind&ctype=tror_landimport_e2','unitWin','top=300px,left=150px,height=600px,width=800px,scrollbars=no,status=no,location=no')">
				 											<img id="imgTransporttype" align="bottom" style="cursor:pointer;" src="resources/images/find.png" height="12px" width="12px" border="0" alt="search">
				 										</a>
				 										 --%>
										 				
									 				</td>
									 				<td align="left" class="tableCell" nowrap>
									 					<input type="text" class="inputTextMediumBlue11" name="dfvs2" id="dfvs2" size="30" maxlength="25" value="${ model.record.dfvs2 }">
									 				</td>
									 				<td align="right" class="tableCell">
									 					<c:choose>
										 				<c:when test="${'0' != model.record.dfvktf}">
								 							<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue11" style="text-align:right;" name="dfvktf" id="dfvktf" size="10" maxlength="9" value="${model.record.dfvktf}">
								 						</c:when>
								 						<c:otherwise>
								 							<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue11" style="text-align:right;" name="dfvktf" id="dfvktf" size="10" maxlength="9" value="">
								 						</c:otherwise>
								 						</c:choose>
									 				</td>
									 				<td align="right" class="tableCell" nowrap>&nbsp;</td>
									 				<td align="right" class="tableCell" nowrap>&nbsp;</td>
									 			</tr>
									 			
									 			</table>
								 			</td>
							 			</tr>
							 			<%-- </c:if>  --%>
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
										<c:if test="${not empty model.record.dfopd}">
					 				    	<input tabindex=-1 class="inputFormSubmit submitSaveClazz" type="submit" name="submit" id="submit" value='<spring:message code="systema.tror.submit.save"/>'/>
					 				    </c:if>
				 				    </td>
							    </tr>
							    </table>
						    </td>
						</tr>
						
						<tr>
							<td colspan="2">
							<table style="width:50%;">
							<tr>
	 				
					 			<td valign="top" class="text12">Melding til Mottaker</td>
					 			<td valign="top" class="text12">
					 				<textarea class="text12" style="resize: none;overflow-y: scroll;" id="messageNoteConsignee" name="messageNoteConsignee" limit='65,10' cols="80" rows="6" >${model.messageNoteConsignee}</textarea>
				 				</td>
			 				</tr>
			 				<tr>
					 			<td valign="top" class="text12">Melding til Transportør</td>
					 			<td valign="top" class="text12">
					 				<textarea class="text12" style="resize: none;overflow-y: scroll;" id="messageNoteCarrier" name="messageNoteCarrier" limit='65,10' cols="80" rows="6">${model.messageNoteCarrier}</textarea>
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
