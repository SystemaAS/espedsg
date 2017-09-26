<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>
<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTror.jsp" />
<!-- =====================end header ==========================-->
	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/trorglobal_edit.js?ver=${user.versionEspedsg}"></SCRIPT>	
	<SCRIPT type="text/javascript" src="resources/js/tror_mainorderlandimport_freightbill.js?ver=${user.versionEspedsg}"></SCRIPT>
	<%-- for dialog popup --%>
	<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
	
	<style type = "text/css">
	.ui-dialog{font-size:10pt;}
	.ui-datepicker { font-size:9pt;}
	</style>
	

<form action="tror_mainorderlandimport.do" name="trorOrderForm" id="trorOrderForm" method="post">
<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
	<tr>
	<td>
	<%-- tab container component --%>
	<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
		<tr height="2"><td></td></tr>
		<tr height="25"> 
			<td width="13%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a class="text14" onClick="setBlockUI(this);" href="tror_mainorderlist.do?action=doFind" > 	
					<img style="vertical-align:middle;" src="resources/images/bulletGreen.png" width="6px" height="6px" border="0" alt="open orders">
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tror.orderlist.tab"/></font>
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="13%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a class="text14" onClick="setBlockUI(this);" href="tror_mainorderlandimport.do?action=doFetch&heavd=${recordOrderTrorLandImport.heavd}&heopd=${recordOrderTrorLandImport.heopd}" > 	
					<img style="vertical-align:middle;" src="resources/images/lorry_green.png" width="18px" height="18px" border="0" alt="update">
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tror.order.tab"/>&nbsp;${recordOrderTrorLandImport.heavd}/${recordOrderTrorLandImport.heopd}</font>
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="13%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a class="text14" onClick="setBlockUI(this);" href="tror_mainorderlandimport_invoice.do?action=doFetch&heavd=${recordOrderTrorLandImport.heavd}&heopd=${recordOrderTrorLandImport.heopd}" > 	
					<img style="vertical-align: bottom" src="resources/images/invoice.png" width="16" hight="16" border="0" alt="show invoice">
					<font class="tabDisabledLink"><spring:message code="systema.tror.order.faktura.tab"/></font><font class="text12">&nbsp;</font>
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="13%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a class="text14" onClick="setBlockUI(this);" href="editNotisblock.do?action=doFetch&subsys=tror_li&avd=${recordOrderTrorLandImport.heavd}&opd=${XXmodel.record.heopd}&sign=${recordOrderTrorLandImport.hesg}" > 	
					<img style="vertical-align: bottom" src="resources/images/veiledning.png" width="16" hight="16" border="0" alt="show messages">
					<font class="tabDisabledLink"><spring:message code="systema.tror.order.notisblock.tab"/></font><font class="text12">&nbsp;</font>
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="13%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a class="text14" onClick="setBlockUI(this);" href="tror_mainorderlandimport_logging.do?avd=${recordOrderTrorLandImport.heavd}&sign=${recordOrderTrorLandImport.hesg}&opd=${recordOrderTrorLandImport.heopd}">
					<img style="vertical-align: bottom" src="resources/images/log-icon.png" width="16" hight="16" border="0" alt="show log">
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tror.order.logging.tab"/></font>
				</a>
			</td>
			
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="13%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a class="text14" onClick="setBlockUI(this);" href="tror_mainorderlandimport_archive.do?avd=${recordOrderTrorLandImport.heavd}&sign=${recordOrderTrorLandImport.hesg}&opd=${recordOrderTrorLandImport.heopd}">
					<img style="vertical-align: bottom" src="resources/images/archive.png" width="16" hight="16" border="0" alt="show archive">
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tror.order.archive.tab"/></font>
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabLink">&nbsp;</font></td>
			<td width="13%" valign="bottom" class="tab" align="center" nowrap>
				<img style="vertical-align: bottom" src="resources/images/fraktbrev.png" width="16" hight="16" border="0" alt="show freight doc">
				<font class="tabLink">&nbsp;<spring:message code="systema.tror.order.fraktbrev.tab"/></font>
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
			<table style="width:100%" id="wrapperTable" class="tabThinBorderWhite" cellspacing="0">
			<tr height="10"><td>&nbsp;</td></tr> 
			<%-- FORM HEADER --%>
	 		<tr>
           		<td>
        			<table class="dashboardFrameHeader" width="98%" align="left" border="0" cellspacing="0" cellpadding="0">
			 		<tr height="15">
			 			<td align="left" class="text14White">
							&nbsp;<img style="vertical-align:bottom;" src="resources/images/complete-icon.png" width="16" hight="16" border="0" alt="edit">	
							&nbsp;FRAKTBREV&nbsp;&nbsp;<font style="color: yellow"><b>${fn:substring(model.record.df1004, 0, 2)}&nbsp;${fn:substring(model.record.df1004, 2, 7)}&nbsp;${fn:substring(model.record.df1004, 7, 17)}</b></font>
							&nbsp;&nbsp;&nbsp;&nbsp;<font style="color: black"><b>${model.record.dfavd} / ${model.record.dfopd} / ${model.record.dffbnr} / ${model.record.dfsg}</b></font>
		 				</td>
		 				<td align="right" class="text12White" width="50%">
		 					&nbsp;&nbsp;
		 				</td>
	 				</tr>
 					</table>
           		</td>
            </tr>
            <%-- FORM DETAIL --%>
            <tr ondrop="drop(event)" ondragover="allowDrop(event)" >
            		<td>
            		<input type="hidden" name="applicationUser" id="applicationUser" value='${user.user}'>
					<input type="hidden" name="action" id="action" value='doUpdate'>
					<input type="hidden" name="selectedType" id="selectedType" value='${model.selectedType}'>
					<input type="hidden" name="heur" id="heur" value='${Xmodel.record.heur}'> <%--delsystem --%>
					
					<c:if test="${not empty Xmodel.record.heopd}">
						<input type="hidden" name="heopd" id="heopd" value='${Xmodel.record.heopd}'>
						<input type="hidden" name="heavd" id="heavd" value='${Xmodel.record.heavd}'>
						<input type="hidden" name="hesg" id="hesg" value='${Xmodel.record.hesg}'> <%--sign --%>
						<input type="hidden" name="hest" id="hest" value='${Xmodel.record.hest}'> <%--status --%>
					</c:if>
					<%--
					<input type="hidden" name="messageNoteConsigneeOriginal" id="messageNoteConsigneeOriginal" value='${XXmodel.record.messageNoteConsigneeOriginal}'>
					<input type="hidden" name="messageNoteCarrierOriginal" id="messageNoteCarrierOriginal" value='${XXmodel.record.messageNoteCarrierOriginal}'>
					<input type="hidden" name="messageNoteInternalOriginal" id="messageNoteInternalOriginal" value='${XXmodel.record.messageNoteInternalOriginal}'>
					 --%>
					<table class="tableBorderWithRoundCorners3D_RoundOnlyOnBottom" width="98%" align="left" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="5"><td ></td></tr>
				 		<tr>
							<td colspan="8">
								
								<table class="text12" border="0">
					 			<tr>
					 				<td width="5px" class="text14" >&nbsp;</td>
					 				<td class="text14">Status</td>
					 				<td class="text14" ><input type="text" class="inputTextMediumBlue" name="dfst" id="dfst" size="2" maxlength="1" value="${model.record.dfst}"></td>
					 				<td width="5px" class="text14" >&nbsp;</td>
					 				<td class="text14" >Merkelapp/ant</td>
					 				<td class="text14" >
					 					<select name="dfkdme" id="dfkdme" class="inputTextMediumBlue" >
					 						<option value="">-velg-</option>
						 				  	<c:forEach var="record" items="${model.merkelappList}" >
						 				  		<option value="${Xrecord.koakon}"<c:if test="${Xmodel.record.heavd == Xrecord.koakon}"> selected </c:if> >${Xrecord.koakon}</option>
											</c:forEach>  
										</select>
					 					<input type="text" class="inputTextMediumBlue" name="dfntla" id="dfntla" size="5" maxlength="4" value="${model.record.dfntla}">
					 				</td>
					 				<td width="20px" class="text14" >&nbsp;</td>
					 				<td class="text14">Oppkrav</td>
					 				<td class="text14" >
					 					<select  name="dfkde" id="dfkde" class="inputTextMediumBlue" >
					 						<option value="">-velg-</option>
						 				  	<option value="X"<c:if test="${model.record.dfkde == 'X'}"> selected </c:if> >X</option>
										</select>
									</td>
									<td width="5px" class="text14" >&nbsp;</td>	
									<td class="text14">Sign</td>
					 				<td class="text14" ><input type="text" class="inputTextMediumBlue" name="dfsg" id="dfsg" size="4" maxlength="3" value="${model.record.dfsg}"></td>
					 				<td width="5px" class="text14" >&nbsp;</td>	
					 				<td class="text14">T.std</td>
					 				<td class="text14" ><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="dftoll" id="dftoll" size="5" maxlength="4" value="${model.record.dftoll}"></td>
					 				<td width="20px" class="text14" >&nbsp;</td>
					 				<td class="text14">EDIFACT</td>
					 				<td class="text14" >
					 					<select  name="dfcmn" id="dfcmn" class="inputTextMediumBlue" >
					 						<option value="">-velg-</option>
						 				  	<option value="N"<c:if test="${model.record.dfcmn == 'N'}"> selected </c:if> >Nei</option>
						 				  	<option value="Y"<c:if test="${model.record.dfcmn == 'Y'}"> selected </c:if> >Ja</option>
										</select>
									</td>
									<td width="5px" class="text14" >&nbsp;</td>		
					 				<td class="text14">Ventekode</td>
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
							<td class="text14Bold">&nbsp;<font class="text16RedBold" >*</font><spring:message code="systema.tror.orders.form.update.label.shipper"/></td>
							<td class="text14Bold">&nbsp;<font class="text16RedBold" >*</font><spring:message code="systema.tror.orders.form.update.label.consignee"/></td>
						</tr>
						<tr height="5"><td ></td></tr>
						
						<tr>
				 			<td valign="top" width="50%" >
				 			 <table style="width:98%" class="tableBorderWithRoundCornersLightGray" cellspacing="1" cellpadding="1" border="0">
						 		<tr height="10"><td ></td></tr>
						 		<tr>
					 				<td class="text12">
					 					&nbsp;<span title="hekns"><spring:message code="systema.tror.orders.form.update.label.shipper.id"/>&nbsp;</span>
					 					<a tabindex="-1" id="trorSellerIdLink" >
 											<img align="bottom" style="cursor:pointer;" src="resources/images/find.png" height="13px" width="13px" border="0" alt="search">
 										</a>
					 				</td>
					 				<td class="text12">
					 					&nbsp;<span title="whenas"><spring:message code="systema.tror.orders.form.update.label.shipper.seller"/>&nbsp;</span>
					 	
					 				</td>
					 			</tr>
					 			<tr>	
				 					<td class="text12" ><input type="text" class="inputTextMediumBlue" name="dfknss" id="dfknss" size="10" maxlength="8" value="${model.record.dfknss}"></td>
								 	<td class="text12" ><input readonly tabindex=-1 type="text" class="inputTextReadOnly" name="whenas" id="whenas" size="50" value="${XXXmodel.record.heknsNavn}&nbsp;-&nbsp;${XXXmodel.record.heknsPnSt}"></td>
				 				</tr>
								<tr height="5"><td ></td></tr>
						 		<tr>
					 				<td class="text12">&nbsp;<font class="text16RedBold" >*</font><span title="dfnavs"><spring:message code="systema.tror.orders.form.update.label.shipper.name"/></span>
					 					<%-- <c:if test="${XXmodel.record.fakBetExists}">
						 					<a href="javascript:void(0);" onClick="window.open('tror_childwindow_customer_addresses.do?action=doFind&ctype=s&wkundnr=${user.custNr}','customerWin','top=300px,left=150px,height=800px,width=900px,scrollbars=no,status=no,location=no')">
		 										<img id="imgShipperSearch" align="bottom" style="cursor:pointer;" src="resources/images/find.png" height="13px" width="13px" border="0" alt="search">
		 									</a>
		 								</c:if> --%>
	 									<a tabindex="-1" id="trorSellerAddressesIdLink" >
											<img align="bottom" style="cursor:pointer;" src="resources/images/find.png" height="13px" width="13px" border="0" alt="search">
										</a>	 									
					 				</td>
					 				<td class="text12">&nbsp;<font class="text16RedBold" >*</font><span title="dfad1s"><spring:message code="systema.tror.orders.form.update.label.shipper.adr1"/></span></td>
					 			</tr>
					 			<tr>	
				 					<td class="text12"><input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" type="text" class="inputTextMediumBlueMandatoryField" name="dfnavs" id="dfnavs" size="25" maxlength="30" value="${model.record.dfnavs}"></td>
				 					<td class="text12"><input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" type="text" class="inputTextMediumBlueMandatoryField" name="dfad1s" id="dfad1s" size="25" maxlength="30" value="${model.record.dfad1s}"></td>
				 				</tr>
					 			<tr>	
					 				<td class="text12">&nbsp;<span title="dfad2s"><spring:message code="systema.tror.orders.form.update.label.shipper.adr2"/></span></td>
					 				<td class="text12">&nbsp;<span title="dfpnls/dfad3s"><spring:message code="systema.tror.orders.form.update.label.shipper.adr3"/></span></td>
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
					 				<td class="text12">&nbsp;<span title="dfsref"><spring:message code="systema.tror.fraktbrev.form.update.label.avsRef"/></span></td>
					 				<td class="text12">&nbsp;<span title="dfbref"><spring:message code="systema.tror.fraktbrev.form.update.label.bokRef"/></span></td>
					 				
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
				 					<td class="text12Bold">&nbsp;
				 						<img style="vertical-align: bottom;" width="24px" height="24px" src="resources/images/invoice.png" border="0" alt="invoice">
				 						Oppr. avsender/Fakturasender
				 					</td>
								</tr>
				 				<tr>
				 					<td colspan="2">
				 					<table class="tableBorderWithRoundCornersLightGray" width="80%">
					 					<tr>
							 				<td class="text12">&nbsp;<span title="dffase">Navn&nbsp;</span></td>
						 				</tr>
						 				<tr>	
						 					<td class="text12" ><input  type="text12" class="inputText" name="dffase" id="dffase" size="30" maxlength="25" value="${model.record.dffase}"></td>
					 					</tr>
									</table>
									</td>				 				
					 			</tr>
				 				<tr height="10"><td ></td></tr>
							 </table>
						 	</td>
						 	<td valign="top" width="50%">
				 			 <table style="width:98%" class="tableBorderWithRoundCornersLightGray" cellspacing="1" cellpadding="1">
					 			<tr height="10"><td ></td></tr>
						 		<tr>
					 				<td class="text12">
					 					&nbsp;<span title="dfknsm"><spring:message code="systema.tror.orders.form.update.label.consignee.id"/>&nbsp;</span>
					 					<a tabindex="-1" id="trorBuyerIdLink" >
 											<img align="bottom" style="cursor:pointer;" src="resources/images/find.png" height="13px" width="13px" border="0" alt="search">
 										</a>
					 				</td>
					 				<td class="text12">
					 					&nbsp;<span title="whenak"><spring:message code="systema.tror.orders.form.update.label.consignee.buyer"/>&nbsp;</span>
					 				</td>	
					 			</tr>
					 			<tr>	
				 					<td class="text12"><input type="text" class="inputTextMediumBlue" name="dfknsm" id="dfknsm" size="10" maxlength="8" value="${model.record.dfknsm}"></td>
				 					<td class="text12" ><input readonly tabindex=-1 type="text" class="inputTextReadOnly" name="whenak" id="whenak" size="50" value="${XXmodel.record.heknkNavn}&nbsp;-&nbsp;${XXmodel.record.heknkPnSt}"></td>
				 				</tr>
				 				<tr height="5"><td ></td></tr>
						 		<tr>
					 				<td class="text12">&nbsp;<font class="text16RedBold" >*</font><span title="dfnavm"><spring:message code="systema.tror.orders.form.update.label.consignee.name"/></span>
					 					<%-- <c:if test="${XXmodel.record.fakBetExists}">
						 					<a href="javascript:void(0);" onClick="window.open('tror_childwindow_customer_addresses.do?action=doFind&ctype=c&wkundnr=${user.custNr}','customerWin','top=300px,left=150px,height=800px,width=900px,scrollbars=no,status=no,location=no')">
		 										<img id="imgConsigneeSearch" align="bottom" style="cursor:pointer;" src="resources/images/find.png" height="13px" width="13px" border="0" alt="search">
		 									</a>
	 									</c:if> --%>
	 									<a tabindex="-1" id="trorBuyerAddressesIdLink" >
											<img align="bottom" style="cursor:pointer;" src="resources/images/find.png" height="13px" width="13px" border="0" alt="search">
										</a>
					 				</td>
					 				<td class="text12">&nbsp;<font class="text16RedBold" >*</font><span title="dfad1m"><spring:message code="systema.tror.orders.form.update.label.consignee.adr1"/></span></td>
					 			</tr>
					 			<tr>	
				 					<td class="text12"><input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" type="text" class="inputTextMediumBlueMandatoryField" name="dfnavm" id="dfnavm" size="25" maxlength="30" value="${model.record.dfnavm}"></td>
				 					<td class="text12"><input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" type="text" class="inputTextMediumBlueMandatoryField" name="dfad1m" id="dfad1m" size="25" maxlength="30" value="${model.record.dfad1m}"></td>
				 				</tr>
					 			<tr>	
					 				<td class="text12">&nbsp;<span title="dfad2m"><spring:message code="systema.tror.orders.form.update.label.consignee.adr2"/></span></td>
					 				<td class="text12">&nbsp;<span title="dfad3m"><spring:message code="systema.tror.orders.form.update.label.consignee.adr3"/></span></td>
					 			</tr>
								<tr>	
				 					<td class="text12"><input type="text" class="inputTextMediumBlue" name="dfad2m" id="dfad2m" size="25" maxlength="30" value="${model.record.dfad2m}"></td>
				 					<td class="text12"><input type="text" class="inputTextMediumBlue" name="dfad3m" id="dfad3m" size="25" maxlength="30" value="${model.record.dfad3m}"></td>
				 				</tr>
				 				<tr>	
					 				<td class="text12">&nbsp;<span title="dfmref"><spring:message code="systema.tror.fraktbrev.form.update.label.motRef"/></span></td>
					 				
					 			</tr>
					 			<tr>	
				 					<td class="text11" >
				 						<input type="text" class="inputTextMediumBlue" name="dfmref" id="dfmref" size="20" maxlength="17" value="${model.record.dfmref}">
								 	</td>
								 </tr>	
				 				<tr height="90"><td ></td></tr>
				 				
 
				 				<tr height="10"><td ></td></tr>
			 				</table>
						 	</td>
					 	</tr>
					 	<tr height="10"><td ></td></tr>
					 </table>
				</td>
			</tr>
			<tr>
            		<td>
	        			<table style="width:98%;" align="left" class="tableBorderWithRoundCornersLightGray" cellspacing="0" cellpadding="0">
				 		<tr height="5"><td colspan="2" ></td></tr>
				 		<tr>
							<td valign="top" style="width:50%;border-right:1px solid;border-color:#FFFFFF;""  >
								<table>
						 		<tr height="2"><td ></td></tr>
							 	<tr>	
						 			<td class="text12">
						 				<img onMouseOver="showPop('helka_info');" onMouseOut="hidePop('helka_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
						 				<font class="text12RedBold" >*</font><span title="helka/hesdf"><spring:message code="systema.tror.orders.form.update.label.from"/>&nbsp;</span>
						 				<div class="text11" style="position: relative;" align="left">
											<span style="position:absolute;top:0px; width:250px" id="helka_info" class="popupWithInputText"  >
												<font class="text11">
							           			<b>Fra sted</b>
							           			<div>
							           			<p>Landkode + postnr / kode for "kundefraktens" frasted. Ved IKKE postnr.basert er det kun ett kodefelt (5 langt)
												</p>
							           			</div>
						           			</font>
										</span>
										</div>
				 					</td>
					 				<td class="text12">
					 					<select required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="helka" id="helka">
					 						<option value="">-landkode-</option>
					 						<c:forEach var="record" items="${model.countryCodeList}" >
						 				  		<option value="${record.klklk}"<c:if test="${Xmodel.record.helka == record.klklk}"> selected </c:if> >${record.klklk}</option>
											</c:forEach>  
											
										</select>
										
					 				</td>
						 			<td class="text12" nowrap>
						 				<input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" type="text" class="inputTextMediumBlueMandatoryField" name="hesdf" id="hesdf" size="6" maxlength="5" value="${Xmodel.record.hesdf}">
						 				<a tabindex=0 id="hesdfIdLink">
	 										<img id="imgFromSearch" align="bottom" style="cursor:pointer;" src="resources/images/find.png" width="13px" height="13px" border="0" alt="search">
	 									</a>
					 				</td>
					 				<td class="text11" colspan="2">
						 				<input readonly tabindex=-1 type="text" class="inputTextReadOnly" name="OWNwppns1" id="OWNwppns1" size="20" maxlength="35" value="${XXXmodel.record.wppns1}">
					 				</td>
					 				<td class="text11">&nbsp;&nbsp;</td>
					 			</tr>
					 				 			
							 	</table>
							</td>
							<td align="left">
							<table >
								<tr>
									<td colspan="4">
									<table>
							 			<tr>	
								 			<td class="text11">
								 				<img onMouseOver="showPop('hetri_info');" onMouseOut="hidePop('hetri_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
								 				<font class="text12RedBold" >*</font><span title="hetri/hesdt"><spring:message code="systema.tror.orders.form.update.label.to"/></span>
								 				<div class="text11" style="position: relative;" align="left">
													<span style="position:absolute;top:0px; width:250px" id="hetri_info" class="popupWithInputText"  >
														<font class="text11">
									           			<b>Til sted</b>
									           			<div>
									           			<p>Landkode + postnr / kode for "kundefraktens" tilsted. Ved IKKE postnr.basert er det kun ett kodefelt (5 langt).
														</p>
									           			</div>
								           			</font>
												</span>
												</div>
							 				</td>
							 				<td class="text12">
							 					<select required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="hetri" id="hetri">
							 						<option value="">-landkode-</option>
								 				  	<c:forEach var="record" items="${model.countryCodeList}" >
						 				  				<option value="${record.klklk}"<c:if test="${Xmodel.record.hetri == record.klklk}"> selected </c:if> >${record.klklk}</option>
													</c:forEach>  
												</select>
							 				</td>
								 			<td class="text12" nowrap>
								 				<input type="text" required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="hesdt" id="hesdt" size="6" maxlength="5" value="${Xmodel.record.hesdt}">
								 				<a tabindex=0 id="hesdtIdLink" >
			 										<img id="imgToSearch" align="bottom" style="cursor:pointer;" src="resources/images/find.png" width="13px" height="13px" border="0" alt="search">
			 									</a>
							 				</td>
											<td class="text12" colspan="2">
								 				<input readonly tabindex=-1 type="text" class="inputTextReadOnly" name="OWNwppns2" id="OWNwppns2" size="20" maxlength="35" value="${XXXmodel.record.wppns2}">
							 				</td>
							 				<td class="text12">&nbsp;&nbsp;</td>
							 			</tr>			
							 		</table>
							 		</td>
							 	</tr>
							</table>
							</td>
						</tr>
						
						
						
						<tr height="6"><td colspan="2" ></td></tr>
						<tr height="1"><td colspan="2" style="border-bottom:1px solid;border-color:#FFFFFF;" class="text"></td></tr>
						<tr height="6"><td colspan="2" ></td></tr>
						<tr>
							<td colspan="10">
							<table width="100%" border="0">	
								<tr>
									<td align="left" width="100%" valign="top">
									<table class="tableBorderWithRoundCorners3D" border="0">
									<tr>
						 				<td class="text12"><span title="heot"><spring:message code="systema.tror.orders.form.update.label.oppdragstype"/></span>
						 					<a href="javascript:void(0);" onClick="window.open('tror_mainorder_childwindow_oppdragstype.do?action=doFind&ctype=landimport','opptypeWin','top=300px,left=150px,height=600px,width=800px,scrollbars=no,status=no,location=no')">
	 											<img id="imgOpptype" align="bottom" style="cursor:pointer;" src="resources/images/find.png" height="13px" width="13px" border="0" alt="search">
	 										</a>
						 				</td>
							 			<td class="text12">
							 				<select name="heot" id="heot">
							 					<option value="" >-velg-</option>
							 					<c:forEach var="record" items="${model.oppdragstypeList}" varStatus="counter">
							 						<option value='${record.ko1kod}' <c:if test="${record.ko1kod == Xmodel.record.heot}"> selected </c:if> >${record.ko1kod}</option>
							 						<c:set var="listSizeHeot" value="${counter.count}" scope="request" /> 
							 					</c:forEach>
											</select>
							 			</td>
										<td class="text12">&nbsp;<span title="hefr"><spring:message code="systema.tror.orders.form.update.label.incoterms"/></span>
											<a href="javascript:void(0);" onClick="window.open('tror_mainorder_childwindow_incoterms.do?action=doFind&ctype=landimport','incotermsWin','top=300px,left=150px,height=600px,width=800px,scrollbars=no,status=no,location=no')">
	 											<img id="imgIncoterms" align="bottom" style="cursor:pointer;" src="resources/images/find.png" height="13px" width="13px" border="0" alt="search">
	 										</a>
										</td>
							 			<td class="text12">
							 				<select name="hefr" id="hefr">
							 					<option value="" >-velg-</option>
							 					<c:forEach var="record" items="${model.incotermsList}" varStatus="counter">
							 						<option value='${record.kfrkod}' <c:if test="${record.kfrkod == Xmodel.record.hefr}"> selected </c:if> >${record.kfrkod}</option>
							 						<c:set var="listSizeHefr" value="${counter.count}" scope="request" />
							 					</c:forEach>
							 					
											</select>
							 			</td>
										<td class="text12">&nbsp;<span title="hekdpl"><spring:message code="systema.tror.orders.form.update.label.productcode"/></span>
											<a href="javascript:void(0);" onClick="window.open('tror_mainorder_childwindow_productcodes.do?action=doFind&ctype=landimport','customerWin','top=300px,left=150px,height=600px,width=800px,scrollbars=no,status=no,location=no')">
	 											<img id="imgProductcodes" align="bottom" style="cursor:pointer;" src="resources/images/find.png" height="13px" width="13px" border="0" alt="search">
	 										</a>
										</td>
							 			<td class="text12">
							 				<select name="hekdpl" id="hekdpl">
							 					<option value="" >-velg-</option>
							 					<c:forEach var="record" items="${model.produktList}" varStatus="counter">
							 						<option value='${record.kfkod}' <c:if test="${record.kfkod == Xmodel.record.hekdpl}"> selected </c:if> >${record.kfkod}</option>
							 					</c:forEach>
											</select>
							 			</td>
							 			
										
							    		<td class="text12" title="todo">&nbsp;<spring:message code="systema.tror.orders.form.update.label.kkvittering"/></td>
							    		<td class="text12">
							    			<select class="inputTextMediumBlue" name="hepk3" id="hepk3" >
							 				  <option value="">-velg-</option>
											  <option value="J"<c:if test="${Xmodel.record.hepk3 == 'J'}"> selected </c:if> >Ja</option>
											  <option value="N"<c:if test="${Xmodel.record.hepk3 == 'N'}"> selected </c:if> >Nei</option>
											</select>
						    			</td>
						    			<td class="text12" title="todo">&nbsp;<spring:message code="systema.tror.orders.form.update.label.tolldekl"/></td>
							    		<td class="text12">
							    			<select class="inputTextMediumBlue" name="hepk4" id="hepk4" >
							 				  <option value="">-velg-</option>
											  <option value="J"<c:if test="${Xmodel.record.hepk4 == 'J'}"> selected </c:if> >Ja</option>
											  <option value="N"<c:if test="${Xmodel.record.hepk4 == 'N'}"> selected </c:if> >Nei</option>
											  <option value="P"<c:if test="${Xmodel.record.hepk4 == 'P'}"> selected </c:if> >P</option>
											</select>
						    			</td>
						 			
							 			<td class="text12" width="50px">&nbsp;</td>	
							 			<%-------------------------- --%>
							 			<%-- START Fraktbrev section --%>
							 			<%-------------------------- --%>
							 			<td class="text12">
							 				<img onMouseOver="showPop('fraktbrev_info');" onMouseOut="hidePop('fraktbrev_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
							 				<span title="hepk1"><b><spring:message code="systema.tror.orders.form.update.label.fraktbrev"/></b></span>
							 				<div class="text12" style="position: relative;" align="left">
												<span style="position:absolute; left:0px; top:0px; width:450px" id="fraktbrev_info" class="popupWithInputText"  >
													<font class="text11">
								           			<b>Fraktbrev/tollsted</b>
								           			<div>
								           				todo
								           			</div>
							           			</font>
											</span>
											</div>
							 			</td>
							 			<td class="text12">
							 				<a tabindex=-1 id="fraktbrevRenderPdfLink" name="fraktbrevRenderPdfLink" target="_new" href="TODOtransportdisp_mainorderlist_renderFraktbrev.do?user=${user.user}&wsavd=${Xmodel.record.heavd}&wsopd=${Xmodel.record.heopd}&wstoll=${XXmodel.record.dftoll}">
		    									<img id="imgFraktbrevPdf" title="Fraktbr.PDF" style="vertical-align:middle;" src="resources/images/pdf.png" width="16" height="16" border="0" alt="Fraktbr. PDF">
											</a>
										</td>
							 			<td nowrap class="text12">
							 				<input type="checkbox" id="hepk1" name="hepk1" value="J" <c:if test="${Xmodel.record.hepk1 == 'J'}"> checked </c:if>>
							 				<input readonly type="text" class="inputText11ReadOnly" size="1" maxlength="1" name="hepk1RO" id="hepk1RO" value="${Xmodel.record.hepk1}">
							 			</td>
							 			
							 			<%------------------------ --%>
							 			<%-- END Fraktbrev section --%>
							 			<%------------------------ --%>
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
			            		<td>
				        			<table width="98%" id="containerdatatableTable" cellspacing="0" align="left" >
			
									<%-- <c:if test="${not empty XXmodel.record.heunik}"> --%>
										<tr height="5"><td align="left" ></td></tr>
										
								 		<tr >
											<td align="bottom" colspan="3" class="text11">&nbsp;&nbsp;<img style="vertical-align:bottom;" src="resources/images/update.gif" width="10px" height="10px" border="0" alt="update item line">
											&nbsp;<spring:message code="systema.tror.fraktbrev.form.detail.update.label.itemLine"/>
											</td>
										</tr>
								 		<tr>
											<td colspan="2" style="padding: 0px;">
												<table align="left" border="0" style="width:100%;" >
												<tr class="tableHeaderField10" >
													
										 			<td align="left" valign="bottom" class="tableHeaderFieldFirst11"><span title="dfgm/dfgm2">&nbsp;<spring:message code="systema.tror.fraktbrev.form.detail.update.label.marks"/></span></td>
										 			<td align="right" valign="bottom" class="tableHeaderField11"><span title="dfnt">&nbsp;<font class="text12RedBold" >*</font><spring:message code="systema.tror.fraktbrev.form.detail.update.label.antal"/>&nbsp;</span></td>
										 			<td align="center" valign="bottom" class="tableHeaderField11"><span title="ownEnhet1/ownEnhet2">&nbsp;<spring:message code="systema.tror.fraktbrev.form.detail.update.label.forpak"/></span></td>
										 			<td align="left" valign="bottom" class="tableHeaderField11"><span title="dfvs/dfvs2">&nbsp;<spring:message code="systema.tror.fraktbrev.form.detail.update.label.goodsDesc"/></span></td>
										 			<td align="right" valign="bottom" class="tableHeaderField11"><span title="dfvkt/dfvktf">&nbsp;<spring:message code="systema.tror.fraktbrev.form.detail.update.label.weight"/>&nbsp;</span></td>
										 			<td align="right" valign="bottom" class="tableHeaderField11"><span title="dfm3">&nbsp;<spring:message code="systema.tror.fraktbrev.form.detail.update.label.m3"/>&nbsp;</span></td>
										 			<td align="right" valign="bottom" class="tableHeaderField11"><span title="dflm">&nbsp;<spring:message code="systema.tror.fraktbrev.form.detail.update.label.lm.fa"/>&nbsp;</span></td>
										 		</tr>
										 		<tr class="tableRow">
										 			<td align="left" class="tableCell" nowrap>
									 					<input type="text" class="inputTextMediumBlue11" name="dfgm" id="dfgm" size="16" maxlength="15" value="${model.record.dfgm}">
									 				</td>
									 				<td align="right" class="tableCell" nowrap>
										 				<input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue11MandatoryField" style="text-align:right;" name="dfnt" id="dfnt" size="8" maxlength="7" value="${model.record.dfnt}">
									 				</td>
									 				<td align="center" class="tableCell" nowrap>
										 				<select name="ownEnhet1" id="ownEnhet1">
										 					<option value="" >-velg-</option>
										 					<c:forEach var="record" items="${model.enhetList}" varStatus="counter">
										 						<c:choose>
											 						<c:when test="${not empty model.record.dfvs1 && fn:length(model.record.dfvs1) > 2}" >
											 							<option value='${record.tkkode}' <c:if test="${ fn:substring(model.record.dfvs1, 0, 2) == record.tkkode }"> selected </c:if> >${record.tkkode}</option>
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
									 					<c:choose>
										 					<c:when test="${not empty model.record.dfvs && fn:length(model.record.dfvs) > 3}" >
										 						<input type="text" class="inputTextMediumBlue11" name="dfvs" id="dfvs" size="30" maxlength="25" value="${ fn:substring(model.record.dfvs, 3, fn:length(model.record.dfvs)) }">		
										 					</c:when>
										 					<c:otherwise>
										 						<input type="text" class="inputTextMediumBlue11" name="dfvs" id="dfvs" size="30" maxlength="25" value="${ model.record.dfvs }">
										 					</c:otherwise>
									 					</c:choose>
									 				</td>
									 				<td align="right" class="tableCell" nowrap>
										 				<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue11" style="text-align:right;" name="dfvkt" id="dfvkt" size="10" maxlength="9" value="${model.record.dfvkt}">
									 				</td>
									 				
									 				<td align="right" class="tableCell" nowrap>
										 				<input onFocus="calculateVolume(this);" onBlur="checkVolumeNewLine(this);" onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue11" style="text-align:right;" name="dfm3" id="dfm3" size="12" maxlength="11" value="${model.record.dfm3}">
									 				</td>
									 				<td align="right" class="tableCell" nowrap>
										 				<input onBlur="checkLmNewLine(this);validateNewItemLine();" onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue11" style="text-align:right;" name="dflm" id="dflm" size="8" maxlength="7" value="${model.record.dflm}">
									 				</td>
									 			</tr>
									 			<tr class="tableRow">
										 			<td align="left" class="tableCell" nowrap>
									 					<input type="text" class="inputTextMediumBlue11" name="dfgm2" id="dfgm2" size="16" maxlength="15" value="${model.record.dfgm2}">
									 				</td>
									 				<td align="right" class="tableCell" nowrap>&nbsp;</td>
									 				<td align="center" class="tableCell" nowrap>
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
										 				
									 				</td>
									 				<td align="left" class="tableCell" nowrap>
									 					<c:choose>
										 					<c:when test="${not empty model.record.dfvs2 && fn:length(model.record.dfvs2) > 3}" >
										 						<input type="text" class="inputTextMediumBlue11" name="dfvs2" id="dfvs2" size="30" maxlength="25" value="${ fn:substring(model.record.dfvs2, 3, fn:length(model.record.dfvs2)) }">		
										 					</c:when>
										 					<c:otherwise>
										 						<input type="text" class="inputTextMediumBlue11" name="dfvs2" id="dfvs2" size="30" maxlength="25" value="${ model.record.dfvs2 }">
										 					</c:otherwise>
									 					</c:choose>
									 					
									 				</td>
									 				<td align="right" class="tableCell">
									 					<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue11" style="text-align:right;" name="dfvktf" id="dfvktf" size="10" maxlength="9" value="${model.record.dfvktf}">
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
										<c:choose>
						 				    <c:when test="${ not empty Xmodel.record.heopd }">
						 				    	<c:if test="${empty recordOrderTrorLandImport.hest || recordOrderTrorLandImport.hest == 'U' || recordOrderTrorLandImport.hest == 'O' || recordOrderTrorLandImport.hest == 'F' }">
						 				    		<input tabindex=-1 class="inputFormSubmit submitSaveClazz" type="submit" name="submit" id="submit" value='<spring:message code="systema.tror.submit.save"/>'/>
						 				    	</c:if>
						 				    </c:when>
						 				    <c:otherwise>
					 				    		<input tabindex=-1 class="inputFormSubmit submitSaveClazz" type="submit" name="submitnew" id="submitnew" value='<spring:message code="systema.tror.submit.createnew.order"/>'/>
						 				    </c:otherwise>	
					 				    </c:choose>
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
