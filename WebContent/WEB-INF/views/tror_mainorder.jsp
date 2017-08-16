<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>
<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTror.jsp" />
<!-- =====================end header ==========================-->
	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/trorglobal_edit.js?ver=${user.versionEspedsg}"></SCRIPT>	
	<SCRIPT type="text/javascript" src="resources/js/tror_mainorder.js?ver=${user.versionEspedsg}"></SCRIPT>
	<%-- for dialog popup --%>
	<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
	
	<style type = "text/css">
	.ui-dialog{font-size:10pt;}
	.ui-datepicker { font-size:9pt;}
	</style>
	

<form action="tror_mainorder.do"  name="trorOrderForm" id="trorOrderForm" method="post">
<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
	<tr>
	<td>
	<%-- tab container component --%>
	<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
		<tr height="2"><td></td></tr>
		<tr height="25"> 
			<td width="20%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a class="text14" href="tror_mainorderlist.do?action=doFind" > 	
					<img style="vertical-align:middle;" src="resources/images/bulletGreen.png" width="6px" height="6px" border="0" alt="open orders">
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tror.orderlist.tab"/></font>
				</a>
			</td>
			<c:choose>
				<c:when test="${empty model.record.heopd}">
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="20%" valign="bottom" class="tab" align="center" nowrap>
						<img style="vertical-align:middle;" src="resources/images/add.png" width="12px" height="12px" border="0" alt="create new">
						<font class="tabLink"><spring:message code="systema.tror.createnew.order.tab"/></font>
					</td>
				</c:when>
				<c:otherwise>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="20%" valign="bottom" class="tab" align="center" nowrap>
						<img style="vertical-align:middle;" src="resources/images/update.gif" width="12px" height="12px" border="0" alt="update order">
						<font class="tabLink"><spring:message code="systema.tror.order.tab"/></font><font class="text12">&nbsp;${model.record.heavd}/${model.record.heopd}</font>
					</td>
					<c:if test="${ not empty model.record.heur }">
						<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
						<td width="20%" valign="bottom" class="tabDisabled" align="center" nowrap>
							<c:choose>
								<c:when test="${ model.record.heur == 'A' || model.record.heur == 'B' || model.record.heur == 'C' || model.record.heur == 'D' }">
									<c:if test="${ model.record.heur == 'A' }">
										<a class="text14" href="tror_landimportorder.do?action=doFind&heavd=${model.record.heavd}&heopd=${model.record.heopd}" >
											<img style="vertical-align:middle;" src="resources/images/lorry_green.png" width="14px" height="14px" border="0" alt="update sub-order">
											<font class="tabDisabledLink"><spring:message code="systema.tror.order.suborder.landimport.tab"/></font>
										</a>
									</c:if>
									<c:if test="${ model.record.heur == 'B' }">
										<a class="text14" href="tror_landimportorder.do?action=doFind&heavd=${model.record.heavd}&heopd=${model.record.heopd}" >
											<img style="vertical-align:middle;" src="resources/images/lorry_green.png" width="14px" height="14px" border="0" alt="update sub-order">
											<font class="tabDisabledLink"><spring:message code="systema.tror.order.suborder.landexport.tab"/></font>
										</a>
									</c:if>
									<c:if test="${ model.record.heur == 'C' }">
										<a class="text14" href="tror_landimportorder.do?action=doFind&heavd=${model.record.heavd}&heopd=${model.record.heopd}" >
											<img style="vertical-align:middle;" src="resources/images/airplaneYellow.gif" width="18px" height="18px" border="0" alt="update sub-order">
											<font class="tabDisabledLink"><spring:message code="systema.tror.order.suborder.airimport.tab"/></font>
										</a>
									</c:if>
									<c:if test="${ model.record.heur == 'D' }">
										<a class="text14" href="tror_landimportorder.do?action=doFind&heavd=${model.record.heavd}&heopd=${model.record.heopd}" >
											<img style="vertical-align:middle;" src="resources/images/airplaneYellow.gif" width="18px" height="18px" border="0" alt="update sub-order">
											<font class="tabDisabledLink"><spring:message code="systema.tror.order.suborder.airexport.tab"/></font>
										</a>
									</c:if>
								</c:when>
								<c:otherwise>
									<font class="tabDisabledLink">Not yet implemented</font>
								</c:otherwise>
							</c:choose>
						</td>
					</c:if>
				</c:otherwise>
			</c:choose>
			<td width="60%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>	
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
	        			<table style="width:98%;" align="left" class="formFrameHeader" border="1" cellspacing="0" cellpadding="0">
				 		<tr height="15">
				 			<c:choose>
					 			<c:when test="${not empty model.record.heopd}">
						 			<td align="left" class="text12White">
										&nbsp;<img style="vertical-align:bottom;" src="resources/images/update.gif" border="0" alt="edit">	
										&nbsp;<spring:message code="systema.tror.orders.form.update.label.header.edit"/>
										&nbsp;&nbsp;<b>${model.record.heavd} / ${model.record.heopd}</b>
					 				</td>
				 				</c:when>
				 				<c:otherwise>
									<td align="left" class="text12White">
										&nbsp;<spring:message code="systema.tror.orders.form.update.label.header.add"/>
					 				</td>	
				 				</c:otherwise>
			 				</c:choose>
			 				<td align="right" class="text12White" width="50%">
			 					STATUS&nbsp;&nbsp;<font style="color: yellow"><b>${model.record.hest}</b></font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="systema.tror.orders.form.update.label.header.customerIdAndName"/>	
								&nbsp;&nbsp;&nbsp;&nbsp;<b>${XXmodel.record.trknfaNavn}&nbsp;&nbsp;</b>${XXmodel.record.trknfa}&nbsp;&nbsp;
								
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
					
					<c:if test="${not empty model.record.heopd}">
						<input type="hidden" name="heopd" id="heopd" value='${model.record.heopd}'>
						<input type="hidden" name="heavd" id="heavd" value='${model.record.heavd}'>
						<input type="hidden" name="hesg" id="hesg" value='${model.record.hesg}'> <%--sign --%>
						<input type="hidden" name="hedtop" id="hedtop" value='${model.record.hedtop}'> <%--datum --%>
						<input type="hidden" name="hepro" id="hepro" value='${model.record.hepro}'> <%--turnr. --%>
						<input type="hidden" name="hest" id="hest" value='${model.record.hest}'> <%--status --%>
						
					</c:if>
					<%--
					<input type="hidden" name="messageNoteConsigneeOriginal" id="messageNoteConsigneeOriginal" value='${Xmodel.record.messageNoteConsigneeOriginal}'>
					<input type="hidden" name="messageNoteCarrierOriginal" id="messageNoteCarrierOriginal" value='${Xmodel.record.messageNoteCarrierOriginal}'>
					<input type="hidden" name="messageNoteInternalOriginal" id="messageNoteInternalOriginal" value='${Xmodel.record.messageNoteInternalOriginal}'>
					 --%>
					<table style="width:98%;" align="left" class="formFrame" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="10"><td ></td></tr>
				 		<tr>
							<td colspan="4">
							<table border="0">
								<c:if test="${empty model.record.heopd}">
									<tr>
										<td align="left" class="text14Bold" >
						 					&nbsp;<font class="text16RedBold" >*</font><span title="heavd"><spring:message code="systema.tror.orders.form.update.label.avdelning"/></span>
						 				</td>
						 				<td class="text14Bold">
						 					<input type="text" required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" class="inputTextMediumBlue11MandatoryField" name="heavd" id="heavd" size="5" maxlength="4" value="" >
											&nbsp;&nbsp;<font class="text16RedBold" >*</font><span title="hesg"><spring:message code="systema.tror.orders.form.update.label.signature"/></span>
						 					<input type="text" required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" class="inputTextMediumBlue11MandatoryField" name="hesg" id="hesg" size="3" maxlength="3" value="" >
						 				</td>
						 			</tr>
					 			</c:if>
							 	<tr>
							 		<td align="left" class="text14Bold" >
					 					&nbsp;<font class="text16RedBold" >*</font><span title="heur"><spring:message code="systema.tror.orders.form.update.label.delsystem"/></span>
					 				</td>
					 				<td class="text11">
					 					<select required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" class="inputTextMediumBlue11MandatoryField" name="heur" id="heur">
					 						<option value="">-velg-</option>
						 				  	<c:forEach var="record" items="${model.delsystemList}" >
						 				  		<option value="${record.kfkod}"<c:if test="${model.record.heur == record.kfkod }"> selected </c:if> >${record.kftxt}</option>
											</c:forEach>  
										</select>
					 				</td>
					 				<td class="text12">&nbsp;&nbsp;<b>Datum</b>&nbsp;${model.record.hedtop}</td>
					 				<td class="text12">&nbsp;&nbsp;<b>Turnr.</b>&nbsp;${model.record.hepro}</td>
					 				
					 			</tr>
							</table>
							</td>
						</tr>
						
						<tr height="5"><td ></td></tr>
						<tr height="3"><td colspan="2" style="border-bottom:1px solid;border-color:#DDDDDD;" class="text"></td></tr>
						<tr height="5"><td ></td></tr>
						<tr>
							<td class="text14Bold">&nbsp;<font class="text16RedBold" >*</font><spring:message code="systema.tror.orders.form.update.label.shipper"/></td>
							<td class="text14Bold">&nbsp;<font class="text16RedBold" >*</font><spring:message code="systema.tror.orders.form.update.label.consignee"/></td>
						</tr>
						<tr height="5"><td ></td></tr>
						
						<tr>
							<td >
							<table class="tableBorderWithRoundCornersLightGray">
								<tr>
									<td class="text12Bold"><spring:message code="systema.tror.orders.form.update.label.shippingDates"/></td>
									<td class="text11">&nbsp;<span title="wsetdd/wsetdk"><spring:message code="systema.tror.orders.form.update.label.shippingDates.etd"/></span></td>
									<td class="text11">
										<input type="text" class="inputTextMediumBlue11" name="wsetdd" id="wsetdd" size="9" maxlength="8" value="${XXmodel.record.wsetdd}">
									</td>
									<td class="text11"><input type="text" class="inputTextMediumBlue11" name="wsetdk" id="wsetdk" size="4" maxlength="4" value="${XXmodel.record.wsetdk}"></td>
									
						
								</tr>
							</table>
							</td>
							<td >
							<table class="tableBorderWithRoundCornersLightGray">
								<tr>
									<td class="text12Bold"><spring:message code="systema.tror.orders.form.update.label.arrivalDates"/></td>
									<td class="text11">&nbsp;<span title="wsetad/wsetak"><spring:message code="systema.tror.orders.form.update.label.arrivalDates.eta"/></span></td>
									<td class="text11">
										<input type="text" class="inputTextMediumBlue11" name="wsetad" id="wsetad" size="9" maxlength="8" value="${XXmodel.record.wsetad}">
									</td>
									<td class="text11"><input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue11" name="wsetak" id="wsetak" size="4" maxlength="4" value="${XXmodel.record.wsetak}"></td>
									
								</tr>
							</table>
							</td>		
						</tr>
						<tr height="5"><td ></td></tr>
						
						
						<tr>
				 			<td valign="top" width="50%" >
				 			 <table style="width:98%" class="tableBorderWithRoundCornersGray" cellspacing="1" cellpadding="0" border="0">
						 		<tr height="10"><td ></td></tr>
						 		<tr>
					 				<td class="text11">
					 					&nbsp;<span title="hekns"><spring:message code="systema.tror.orders.form.update.label.shipper.id"/>&nbsp;</span>
					 					<%-- <c:if test="${Xmodel.record.fakBetExists}">  --%>
						 					<a href="javascript:void(0);" onClick="window.open('tror_childwindow_customer.do?action=doFind&ctype=s','customerWin','top=300px,left=150px,height=800px,width=900px,scrollbars=no,status=no,location=no')">
		 										<img id="imgShipperSearch" align="bottom" style="cursor:pointer;" src="resources/images/find.png" height="13px" width="13px" border="0" alt="search">
		 									</a>
	 									<%-- </c:if> --%>
					 				</td>
					 				<td class="text11">
					 					&nbsp;<span title="whenas"><spring:message code="systema.tror.orders.form.update.label.shipper.seller"/>&nbsp;</span>
					 	
					 				</td>
					 			</tr>
					 			<tr>	
				 					<td class="text11" ><input type="text" class="inputTextMediumBlueUPPERCASE" name="hekns" id="hekns" size="10" maxlength="8" value="${model.record.hekns}"></td>
								 	<td class="text11" ><input readonly tabindex=-1 type="text" class="inputTextReadOnly" name="whenas" id="whenas" size="50" value="${XXmodel.record.heknsNavn}&nbsp;-&nbsp;${XXmodel.record.heknsPnSt}"></td>
				 				</tr>
								<tr height="5"><td ></td></tr>
						 		<tr>
					 				<td class="text11">&nbsp;<font class="text16RedBold" >*</font><span title="henas"><spring:message code="systema.tror.orders.form.update.label.shipper.name"/></span>
					 					<%-- <c:if test="${Xmodel.record.fakBetExists}"> --%>
						 					<a href="javascript:void(0);" onClick="window.open('tror_childwindow_customer_addresses.do?action=doFind&ctype=s&wkundnr=${user.custNr}','customerWin','top=300px,left=150px,height=800px,width=900px,scrollbars=no,status=no,location=no')">
		 										<img id="imgShipperSearch" align="bottom" style="cursor:pointer;" src="resources/images/find.png" height="13px" width="13px" border="0" alt="search">
		 									</a>
	 									<%-- </c:if> --%>
					 				</td>
					 				<td class="text11">&nbsp;<font class="text16RedBold" >*</font><span title="heads1"><spring:message code="systema.tror.orders.form.update.label.shipper.adr1"/></span></td>
					 			</tr>
					 			<tr>	
				 					<td class="text11"><input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" type="text" class="inputTextMediumBlueMandatoryField" name="henas" id="henas" size="25" maxlength="30" value="${model.record.henas}"></td>
				 					<td class="text11"><input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" type="text" class="inputTextMediumBlueMandatoryField" name="heads1" id="heads1" size="25" maxlength="30" value="${model.record.heads1}"></td>
				 				</tr>
					 			<tr>	
					 				<td class="text11">&nbsp;<span title="heads2"><spring:message code="systema.tror.orders.form.update.label.shipper.adr2"/></span></td>
					 				<td class="text11">&nbsp;<span title="heads3"><spring:message code="systema.tror.orders.form.update.label.shipper.adr3"/></span></td>
					 			</tr>
								<tr>	
				 					<td class="text11" >
				 					<input type="text" class="inputTextMediumBlueUPPERCASE" name="heads2" id="heads2" size="25" maxlength="30" value="${model.record.heads2}">
				 					</td>
				 					<td class="text11"><input type="text" class="inputTextMediumBlue" name="heads3" id="heads3" size="25" maxlength="30" value="${model.record.heads3}"></td>
				 				</tr>
				 				
				 				<tr height="15"><td ></td></tr>	
				 				<tr>	
				 					<td class="text11">
										<img onMouseOver="showPop('herfa_info');" onMouseOut="hidePop('herfa_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					 					<span title="herfa"><spring:message code="systema.tror.orders.form.update.label.avsRef"/></span>						 				
										<div class="text11" style="position: relative;" align="left">
										<span style="position:absolute; left:0px; top:0px; width:250px" id="herfa_info" class="popupWithInputText"  >
											<font class="text11">
							           			<b>Søk Avs.</b>
							           			<div>
							           			<p>Avsenders søkereferanse <br>
							           				Begrep for senere søk/gjenfinning.</p>
							           			</div>
						           			</font>
										</span>
										</div>
									</td>
				 					<td class="text11">
				 						<span title="hesdla">
				 							<img style="vertical-align:middle;" src="resources/images/loading.png" width="15px" height="15px" border="0" alt="load/unload">
				 							<spring:message code="systema.tror.orders.form.update.label.load"/>
				 							<a href="javascript:void(0);" onClick="window.open('tror_childwindow_loadunloadplaces.do?action=doInit&&caller=hesdla','postalcodeWin','top=300px,left=50px,height=600px,width=800px,scrollbars=no,status=no,location=no')">						 				
						 						<img id="imgToSearch" align="bottom" style="cursor:pointer;" src="resources/images/find.png" height="13px" width="13px" border="0" alt="search">
						 					</a>
				 						</span>
				 					</td>
				 				</tr>
				 				<tr>	
				 					<td class="text11" >
				 						<input type="text" class="inputTextMediumBlue" name="herfa" id="herfa" size="20" maxlength="35" value="${model.record.herfa}">
								 	</td>
				 					<td class="text11" >
						 				<input type="text" class="inputTextMediumBlue11" name="hesdla" id="hesdla" size="21" maxlength="20" value="${model.record.hesdla}">
										
						 			</td>
				 				</tr>
				 				<tr height="5"><td ></td></tr>
								<tr>
					 				<td class="text11">&nbsp;<span title="wsscont"><spring:message code="systema.tror.orders.form.update.label.shipper.contactName"/></span></td>
					 				<td class="text11">&nbsp;<span title="wsstlf"><spring:message code="systema.tror.orders.form.update.label.shipper.telephone"/></span></td>
					 			</tr>
					 			<tr>	
				 					<td class="text11"><input type="text" class="inputTextMediumBlueUPPERCASE" name="wsscont" id="wsscont" size="25" maxlength="30" value="${Xmodel.record.wsscont}"></td>
				 					<td class="text11"><input type="text" class="inputTextMediumBlueUPPERCASE" name="wsstlf" id="wsstlf" size="25" maxlength="30" value="${Xmodel.record.wsstlf}"></td>
				 				</tr>
					 			<tr>	
					 				<td class="text11" colspan="2">&nbsp;<span title="wssmail"><spring:message code="systema.tror.orders.form.update.label.shipper.email"/></span></td>
					 			</tr>
					 			<tr>	
				 					<td class="text11" colspan="2"><input type="text" class="inputTextMediumBlue" name="wssmail" id="wssmail" size="50" maxlength="70" value="${Xmodel.record.wssmail}"></td>
				 				</tr>
				 				<tr height="8"><td ></td></tr>
				 				
				 				<c:choose>
				 				<c:when test="${Xmodel.record.fakBetExists}">													 				
									<tr>
					 					<td class="text12Bold">&nbsp;
					 						<img style="vertical-align: bottom;" width="24px" height="24px" src="resources/images/invoice.png" border="0" alt="invoice">
					 						<spring:message code="systema.tror.orders.form.update.label.shipper.invoicee"/>
				 						</td>
									</tr>
					 				<tr>
					 					<td colspan="2">
					 					<table class="tableBorderWithRoundCornersLightGray">
						 					<tr>
								 				<td class="text11">
								 					&nbsp;<span title="heknsf"><spring:message code="systema.tror.orders.form.update.label.shipper.invoicee.id"/>&nbsp;</span>
								 					<a href="javascript:void(0);" onClick="window.open('tror_childwindow_customer.do?action=doFind&ctype=sf','customerWin','top=300px,left=150px,height=800px,width=900px,scrollbars=no,status=no,location=no')">
					 										<img id="imgConsigneeSearch" align="bottom" style="cursor:pointer;" src="resources/images/find.png" height="13px" width="13px" border="0" alt="search">
				 									</a>
								 				</td>
								 				<td class="text11">
								 					&nbsp;<span title="whenasf"><spring:message code="systema.tror.orders.form.update.label.shipper.invoicee.name"/>&nbsp;</span>
								 				</td>
								 				<td class="text11">
								 					<img onMouseOver="showPop('shipperCurr_info');" onMouseOut="hidePop('shipperCurr_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
									 				<span title="hevals"><spring:message code="systema.tror.orders.form.update.label.shipper.invoicee.currencyCode"/>&nbsp;</span>
									 				<div class="text11" style="position: relative;" align="left">
														<span style="position:absolute; left:0px; top:0px; width:250px" id="shipperCurr_info" class="popupWithInputText"  >
															<font class="text11">
										           			<b>Valuta</b>
										           			<div>
										           			<p>Valuta for fakturautstedelse - hentes fra kunderegister, kan overstyres. 
										           				Ved ulik NOK går fremmedvaluta inn i reskontro.
										           			</p>
										           			</div>
									           			</font>
													</span>
													</div>
								 				</td>
								 				
							 				</tr>
							 				<tr>	
							 					<td class="text11" ><input type="text" class="inputTextMediumBlueUPPERCASE" name="heknsf" id="heknsf" size="10" maxlength="8" value="${Xmodel.record.heknsf}"></td>
											 	<td class="text11" ><input readonly tabindex=-1 type="text" class="inputTextReadOnly" name="whenasf" id="whenasf" size="50" value="${Xmodel.record.heknsfNavn} - ${Xmodel.record.heknsfPnSt}"></td>
							 					<td class="text11" >
							 						<select name="hevals" id="hevals">
								 						<option value="">-valuta-</option>
									 				  	<c:forEach var="currency" items="${model.currencyCodeList}" >
									 				  		<option value="${currency}"<c:if test="${Xmodel.record.hevals == currency || (empty Xmodel.record.hevals && currency=='NOK')}"> selected </c:if> >${currency}</option>
														</c:forEach>  
													</select>
							 					</td>
							 					
						 					</tr>
										</table>
										</td>				 				
						 			</tr>
					 			</c:when>
					 			<c:otherwise>
					 				<input type="hidden" name="heknsf" id="heknsf" value="${Xmodel.record.heknsf}" >   
					 			</c:otherwise>	
					 			</c:choose>			
					 			 	
				 				<tr height="10"><td ></td></tr>
							 </table>
						 	</td>
						 	<td valign="top" width="50%">
				 			 <table style="width:98%" class="tableBorderWithRoundCornersGray" cellspacing="1" cellpadding="0">
					 			<tr height="10"><td ></td></tr>
						 		<tr>
					 				<td class="text11">
					 					&nbsp;<span title="heknk"><spring:message code="systema.tror.orders.form.update.label.consignee.id"/>&nbsp;</span>
					 					<%-- <c:if test="${Xmodel.record.fakBetExists}"> --%>
						 					<a href="javascript:void(0);" onClick="window.open('tror_childwindow_customer.do?action=doFind&ctype=c','customerWin','top=300px,left=150px,height=800px,width=900px,scrollbars=no,status=no,location=no')">
		 										<img id="imgConsigneeSearch" align="bottom" style="cursor:pointer;" src="resources/images/find.png" height="13px" width="13px" border="0" alt="search">
		 									</a>	
	 									<%-- </c:if> --%>
					 				</td>
					 				<td class="text11">
					 					&nbsp;<span title="whenak"><spring:message code="systema.tror.orders.form.update.label.consignee.buyer"/>&nbsp;</span>
					 				</td>	
					 			</tr>
					 			<tr>	
				 					<td class="text11"><input type="text" class="inputTextMediumBlue" name="heknk" id="heknk" size="10" maxlength="8" value="${model.record.heknk}"></td>
				 					<td class="text11" ><input readonly tabindex=-1 type="text" class="inputTextReadOnly" name="whenak" id="whenak" size="50" value="${Xmodel.record.heknkNavn}&nbsp;-&nbsp;${Xmodel.record.heknkPnSt}"></td>
				 				</tr>
				 				<tr height="5"><td ></td></tr>
						 		<tr>
					 				<td class="text11">&nbsp;<font class="text16RedBold" >*</font><span title="henak"><spring:message code="systema.tror.orders.form.update.label.consignee.name"/></span>
					 					<%-- <c:if test="${Xmodel.record.fakBetExists}"> --%>
						 					<a href="javascript:void(0);" onClick="window.open('tror_childwindow_customer_addresses.do?action=doFind&ctype=c&wkundnr=${user.custNr}','customerWin','top=300px,left=150px,height=800px,width=900px,scrollbars=no,status=no,location=no')">
		 										<img id="imgConsigneeSearch" align="bottom" style="cursor:pointer;" src="resources/images/find.png" height="13px" width="13px" border="0" alt="search">
		 									</a>
	 									<%-- </c:if> --%>
					 				</td>
					 				<td class="text11">&nbsp;<font class="text16RedBold" >*</font><span title="headk1"><spring:message code="systema.tror.orders.form.update.label.consignee.adr1"/></span></td>
					 			</tr>
					 			<tr>	
				 					<td class="text11"><input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" type="text" class="inputTextMediumBlueMandatoryField" name="henak" id="henak" size="25" maxlength="30" value="${model.record.henak}"></td>
				 					<td class="text11"><input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" type="text" class="inputTextMediumBlueMandatoryField" name="headk1" id="headk1" size="25" maxlength="30" value="${model.record.headk1}"></td>
				 				</tr>
					 			<tr>	
					 				<td class="text11">&nbsp;<span title="headk2"><spring:message code="systema.tror.orders.form.update.label.consignee.adr2"/></span></td>
					 				<td class="text11">&nbsp;<span title="headk3"><spring:message code="systema.tror.orders.form.update.label.consignee.adr3"/></span></td>
					 			</tr>
								<tr>	
				 					<td class="text11"><input type="text" class="inputTextMediumBlue" name="headk2" id="headk2" size="25" maxlength="30" value="${model.record.headk2}"></td>
				 					<td class="text11"><input type="text" class="inputTextMediumBlue" name="headk3" id="headk3" size="25" maxlength="30" value="${model.record.headk3}"></td>
				 				</tr>
				 				
				 				<tr height="15"><td ></td></tr>
				 				<tr>	
				 					<td class="text11"><img onMouseOver="showPop('herfk_info');" onMouseOut="hidePop('herfk_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					 					&nbsp;&nbsp;<span title="herfk"><spring:message code="systema.tror.orders.form.update.label.consignee.ref"/></span>
						 				<div class="text11" style="position: relative;" align="left">
										<span style="position:absolute; left:0px; top:0px; width:250px" id="herfk_info" class="popupWithInputText"  >
											<font class="text11">
							           			<b>Søk Mott.</b>
							           			<div>
							           			<p>Mottakers søkereferanse Fritt felt for utfylling. Begrep for senere søk/gjenfinning.</p>
							           			</div>
						           			</font>
										</span>
										</div>
				 					</td>
				 					<td class="text11">
				 						<img style="vertical-align:middle;" src="resources/images/loading.png" width="15px" height="15px" border="0" alt="load/unload">
				 						<span title="hesdl"><spring:message code="systema.tror.orders.form.update.label.unload"/></span>
				 						<a href="javascript:void(0);" onClick="window.open('tror_childwindow_loadunloadplaces.do?action=doInit&caller=hesdl','postalcodeWin','top=300px,left=50px,height=600px,width=800px,scrollbars=no,status=no,location=no')">						 				
						 					<img id="imgToSearch" align="bottom" style="cursor:pointer;" src="resources/images/find.png" height="13px" width="13px" border="0" alt="search">
						 				</a>
				 					</td>
				 				</tr>
				 				<tr>	
				 					<td class="text11" >
				 						<input type="text" class="inputTextMediumBlue" name="herfk" id="herfk" size="20" maxlength="35" value="${model.record.herfk}">
				 					</td>
				 					<td class="text11" >
						 				<input type="text" class="inputTextMediumBlue11" name="hesdl" id="hesdl" size="21" maxlength="20" value="${model.record.hesdl}">
										
						 			</td>
				 				</tr>
				 				<tr height="5"><td ></td></tr>
				 				<tr>
					 				<td class="text11">&nbsp;<span title="wskcont"><spring:message code="systema.tror.orders.form.update.label.consignee.contactName"/></span></td>
					 				<td class="text11">&nbsp;<span title="wsktlf"><spring:message code="systema.tror.orders.form.update.label.consignee.telephone"/></span></td>
					 			</tr>
					 			<tr>	
				 					<td class="text11"><input type="text" class="inputTextMediumBlueUPPERCASE" name="wskcont" id="wskcont" size="25" maxlength="30" value="${Xmodel.record.wskcont}"></td>
				 					<td class="text11"><input type="text" class="inputTextMediumBlueUPPERCASE" name="wsktlf" id="wsktlf" size="25" maxlength="30" value="${Xmodel.record.wsktlf}"></td>
				 				</tr>
					 			<tr>	
					 				<td class="text11" colspan="2">&nbsp;<span title="wskmail"><spring:message code="systema.tror.orders.form.update.label.consignee.email"/></span></td>
					 			</tr>
					 			<tr>	
				 					<td class="text11" colspan="2"><input type="text" class="inputTextMediumBlue" name="wskmail" id="wskmail" size="50" maxlength="70" value="${Xmodel.record.wskmail}"></td>
				 				</tr>
				 				<tr height="8"><td ></td></tr>
				 				
				 				<c:choose>
				 				<c:when test="${Xmodel.record.fakBetExists}">
									<tr>
					 					<td class="text12Bold">&nbsp;
					 						<img style="vertical-align: bottom;" width="24px" height="24px" src="resources/images/invoice.png" border="0" alt="invoice">
					 						<spring:message code="systema.tror.orders.form.update.label.consignee.invoicee"/>
					 					</td>
									</tr>
					 				<tr>
					 					<td colspan="2">
					 					<table class="tableBorderWithRoundCornersLightGray">
						 					<tr>
								 				<td class="text11">
								 					&nbsp;<span title="heknkf"><spring:message code="systema.tror.orders.form.update.label.consignee.invoicee.id"/>&nbsp;</span>
							 						<a href="javascript:void(0);" onClick="window.open('tror_childwindow_customer.do?action=doFind&ctype=kf','customerWin','top=300px,left=150px,height=800px,width=900px,scrollbars=no,status=no,location=no')">
				 										<img id="imgShipperSearch" align="bottom" style="cursor:pointer;" src="resources/images/find.png" height="13px" width="13px" border="0" alt="search">
				 									</a>
								 				</td>
								 				<td class="text11">
								 					&nbsp;<span title="whenakf"><spring:message code="systema.tror.orders.form.update.label.consignee.invoicee.name"/>&nbsp;</span>
								 				</td>
								 				
							 				</tr>
							 				<tr>	
							 					<td class="text11" ><input type="text" class="inputTextMediumBlue" name="heknkf" id="heknkf" size="10" maxlength="8" value="${model.record.heknkf}"></td>
											 	<td class="text11" ><input readonly tabindex=-1 type="text" class="inputTextReadOnly" name="whenakf" id="whenakf" size="50" value="${Xmodel.record.heknkfNavn} - ${Xmodel.record.heknkfPnSt}"></td>
							 					<td class="text11">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
						 					</tr>
										</table>
										</td>				 				
						 			</tr>
					 			</c:when>	
					 			<c:otherwise>
					 				<input type="hidden" name="heknkf" id="heknkf" value="${model.record.heknkf}" >   
					 			</c:otherwise>	
					 			</c:choose>						 
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
	        			<table style="width:98%;" align="left" class="tableBorderWithRoundCornersGray" cellspacing="0" cellpadding="0">
				 		<tr height="5"><td colspan="2" ></td></tr>
				 		<tr>
							<td valign="top" style="width:50%;border-right:1px solid;border-color:#FFFFFF;""  >
								<table>
						 		<tr height="2"><td ></td></tr>
							 	<tr>	
						 			<td class="text11">
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
					 				<td class="text11">
					 					<select required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" class="inputTextMediumBlue11MandatoryField" name="helka" id="helka">
					 						<option value="">-landkode-</option>
					 						<c:forEach var="record" items="${model.countryCodeList}" >
						 				  		<option value="${record.klklk}"<c:if test="${model.record.helka == record.klklk}"> selected </c:if> >${record.klklk}</option>
											</c:forEach>  
											
										</select>
										
					 				</td>
						 			<td class="text11" nowrap>
						 				<input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" type="text" class="inputTextMediumBlue11MandatoryField" name="hesdf" id="hesdf" size="6" maxlength="5" value="${model.record.hesdf}">
						 				<a tabindex=0 id="hesdfIdLink">
	 										<img id="imgFromSearch" align="bottom" style="cursor:pointer;" src="resources/images/find.png" width="13px" height="13px" border="0" alt="search">
	 									</a>
					 				</td>
					 				<td class="text11" colspan="2">
						 				<input readonly tabindex=-1 type="text" class="inputTextReadOnly" name="OWNwppns1" id="OWNwppns1" size="20" maxlength="14" value="${XXmodel.record.wppns1}">
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
							 				<td class="text11">
							 					<select required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" class="inputTextMediumBlue11MandatoryField" name="hetri" id="hetri">
							 						<option value="">-landkode-</option>
								 				  	<c:forEach var="record" items="${model.countryCodeList}" >
						 				  				<option value="${record.klklk}"<c:if test="${model.record.hetri == record.klklk}"> selected </c:if> >${record.klklk}</option>
													</c:forEach>  
												</select>
							 				</td>
								 			<td class="text11" nowrap>
								 				<input type="text" required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" class="inputTextMediumBlue11MandatoryField" name="hesdt" id="hesdt" size="6" maxlength="5" value="${model.record.hesdt}">
								 				<a tabindex=0 id="hesdtIdLink" >
			 										<img id="imgToSearch" align="bottom" style="cursor:pointer;" src="resources/images/find.png" width="13px" height="13px" border="0" alt="search">
			 									</a>
							 				</td>
											<td class="text11" colspan="2">
								 				<input readonly tabindex=-1 type="text" class="inputTextReadOnly" name="OWNwppns2" id="OWNwppns2" size="20" maxlength="14" value="${XXmodel.record.wppns2}">
							 				</td>
							 				<td class="text11">&nbsp;&nbsp;</td>
							 			</tr>			
							 		</table>
							 		</td>
							 	</tr>
							</table>
							</td>
						</tr>
						
						
						
						<tr height="8"><td colspan="2" ></td></tr>
						<tr height="1"><td colspan="2" style="border-bottom:1px solid;border-color:#FFFFFF;" class="text"></td></tr>
						<tr height="8"><td colspan="2" ></td></tr>
						<tr>
							<td colspan="4">
							<table>	
								<tr>
									<td>
									<table>
						 			<tr>		
										<td class="text11">&nbsp;
											<span title="heot"><spring:message code="systema.tror.orders.form.update.label.oppdragstype"/></span>
							 			</td>
							 			<td class="text11">
							 				<select name="heot" id="heot">
							 					<option value="" >-velg-</option>
							 					<c:forEach var="record" items="${model.oppdragstypeList}" varStatus="counter">
							 						<option value='${record.ko1kod}' <c:if test="${record.ko1kod == model.record.heot}"> selected </c:if> >${record.ko1kod}</option>
							 						<c:set var="listSizeHeot" value="${counter.count}" scope="request" /> 
							 					</c:forEach>
							 					<c:if test="${listSizeHeot > 1}">
							 						
							 					</c:if>
											</select>
							 			</td>
										<td width="30px">&nbsp;</td>
										<td class="text11">&nbsp;
											<span title="hefr"><spring:message code="systema.tror.orders.form.update.label.incoterms"/></span>
							 			</td>
							 			<td class="text11">
							 				<select name="hefr" id="hefr">
							 					<option value="" >-velg-</option>
							 					<c:forEach var="record" items="${model.incotermsList}" varStatus="counter">
							 						<option value='${record.kfrkod}' <c:if test="${record.kfrkod == model.record.hefr}"> selected </c:if> >${record.kfrkod}</option>
							 						<c:set var="listSizeHefr" value="${counter.count}" scope="request" />
							 					</c:forEach>
							 					
											</select>
							 			</td>
										<td width="30px">&nbsp;</td>
										<td class="text11">&nbsp;
											<span title="hekdpl"><spring:message code="systema.tror.orders.form.update.label.productcode"/></span>
							 			</td>
							 			<td class="text11">
							 				<select name="hekdpl" id="hekdpl">
							 					<option value="" >-velg-</option>
							 					<c:forEach var="record" items="${model.produktList}" varStatus="counter">
							 						<option value='${record.kfkod}' <c:if test="${record.kfkod == model.record.hekdpl}"> selected </c:if> >${record.kfkod}</option>
							 					</c:forEach>
											</select>
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
            <tr height="2"><td></td></tr>
            
            
            <tr>
            		<td>
	        			<table width="98%" id="containerdatatableTable" cellspacing="2" align="left" >

						<%-- <c:if test="${not empty Xmodel.record.heunik}"> --%>
							<tr height="10"><td align="left" ></td></tr>
							
					 		<tr >
								<td align="bottom" colspan="3" class="text11">&nbsp;&nbsp;<img style="vertical-align:bottom;" src="resources/images/update.gif" width="10px" height="10px" border="0" alt="update item line">
								&nbsp;<spring:message code="systema.tror.orders.form.detail.update.label.itemLine"/>
								</td>
							</tr>
					 		<tr>
								<td colspan="2" style="padding: 3px;">
									<table align="left" border="0" style="width:100%;" >
									<tr class="tableHeaderField10" >
										
							 			<td align="left" valign="bottom" class="tableHeaderField11"><span title="hegm1/hegm2">&nbsp;<spring:message code="systema.tror.orders.form.detail.update.label.marks"/></span></td>
							 			<td align="right" valign="bottom" class="tableHeaderField11"><span title="hent">&nbsp;<font class="text12RedBold" >*</font><spring:message code="systema.tror.orders.form.detail.update.label.antal"/>&nbsp;</span></td>
							 			<td align="center" valign="bottom" class="tableHeaderField11"><span title="intern">&nbsp;<spring:message code="systema.tror.orders.form.detail.update.label.forpak"/></span></td>
							 			<td align="left" valign="bottom" class="tableHeaderField11"><span title="hevs1/hevs2">&nbsp;<font class="text12RedBold" >*</font><spring:message code="systema.tror.orders.form.detail.update.label.goodsDesc"/></span></td>
							 			<td align="right" valign="bottom" class="tableHeaderField11"><span title="hevkt">&nbsp;<font class="text12RedBold" >*</font><spring:message code="systema.tror.orders.form.detail.update.label.weight"/>&nbsp;</span></td>
							 			<td align="right" valign="bottom" class="tableHeaderField11"><span title="hem3">&nbsp;<spring:message code="systema.tror.orders.form.detail.update.label.m3"/>&nbsp;</span></td>
							 			<td align="right" valign="bottom" class="tableHeaderField11"><span title="helm">&nbsp;<spring:message code="systema.tror.orders.form.detail.update.label.lm.fa"/>&nbsp;</span></td>
							 		</tr>
							 		<tr class="tableRow">
							 			<td align="left" class="tableCell" nowrap>
						 					<input type="text" class="inputTextMediumBlue11" name="hegm1" id="hegm1" size="16" maxlength="15" value="${model.record.hegm1}">
						 				</td>
						 				<td align="right" class="tableCell" nowrap>
							 				<input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue11MandatoryField" style="text-align:right;" name="hent" id="hent" size="8" maxlength="7" value="${model.record.hent}">
						 				</td>
						 				<td align="center" class="tableCell" nowrap>
							 				<select name="ownEnhet1" id="ownEnhet1">
							 					<option value="" >-velg-</option>
							 					<c:forEach var="record" items="${model.enhetList}" varStatus="counter">
							 						<c:choose>
								 						<c:when test="${not empty model.record.hevs1 && fn:length(model.record.hevs1) > 2}" >
								 							<option value='${record.tkkode}' <c:if test="${ fn:substring(model.record.hevs1, 0, 2) == record.tkkode }"> selected </c:if> >${record.tkkode}</option>
								 						</c:when>
								 						<c:otherwise>
								 							<option value='${record.tkkode}'>${record.tkkode}</option>
								 						</c:otherwise>
							 						</c:choose>
							 					</c:forEach>
											</select>
							 				<a tabindex=0 id="fvpaknIdLinkNewLine" onClick="searchPackingCodesNewLine(this);">
	 											<img id="imgfvpaknSearch" align="bottom" style="cursor:pointer;" src="resources/images/find2.png" height="11px" width="11px" border="0" alt="search">
	 										</a>
						 				</td>
						 				<td align="left" class="tableCell" nowrap>
						 					<c:choose>
							 					<c:when test="${not empty model.record.hevs1 && fn:length(model.record.hevs1) > 3}" >
							 						<input type="text" class="inputTextMediumBlue11MandatoryField" name="hevs1" id="hevs1" size="30" maxlength="25" value="${ fn:substring(model.record.hevs1, 3, fn:length(model.record.hevs1)) }">		
							 					</c:when>
							 					<c:otherwise>
							 						<input type="text" class="inputTextMediumBlue11MandatoryField" name="hevs1" id="hevs1" size="30" maxlength="25" value="${ model.record.hevs1 }">
							 					</c:otherwise>
						 					</c:choose>
						 				</td>
						 				<td align="right" class="tableCell" nowrap>
							 				<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue11MandatoryField" style="text-align:right;" name="hevkt" id="hevkt" size="10" maxlength="9" value="${model.record.hevkt}">
						 				</td>
						 				
						 				<td align="right" class="tableCell" nowrap>
							 				<input onFocus="calculateVolume(this);" onBlur="checkVolumeNewLine(this);" onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue11" style="text-align:right;" name="hem3" id="hem3" size="12" maxlength="11" value="${model.record.hem3}">
						 				</td>
						 				<td align="right" class="tableCell" nowrap>
							 				<input onBlur="checkLmNewLine(this);validateNewItemLine();" onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue11" style="text-align:right;" name="helm" id="helm" size="8" maxlength="7" value="${model.record.helm}">
						 				</td>
						 			</tr>
						 			<tr class="tableRow">
							 			<td align="left" class="tableCell" nowrap>
						 					<input type="text" class="inputTextMediumBlue11" name="hegm2" id="hegm2" size="16" maxlength="15" value="${model.record.hegm2}">
						 				</td>
						 				<td align="right" class="tableCell" nowrap>&nbsp;</td>
						 				<td align="center" class="tableCell" nowrap>
							 				<select name="ownEnhet2" id="ownEnhet2">
							 					<option value="" >-velg-</option>
							 					<c:forEach var="record" items="${model.enhetList}" varStatus="counter">
								 					<c:choose>
								 						<c:when test="${not empty model.record.hevs2 && fn:length(model.record.hevs2) > 2}" >
								 							<option value='${record.tkkode}' <c:if test="${ fn:substring(model.record.hevs2, 0, 2) == record.tkkode }"> selected </c:if> >${record.tkkode}</option>
								 						</c:when>
								 						<c:otherwise>
								 							<option value='${record.tkkode}' >${record.tkkode}</option>
								 						</c:otherwise>
							 						</c:choose>
						 						</c:forEach>
											</select>
							 				<a tabindex=0 id="fvpaknIdLinkNewLine" onClick="searchPackingCodesNewLine(this);">
	 											<img id="imgfvpaknSearch" align="bottom" style="cursor:pointer;" src="resources/images/find2.png" height="11px" width="11px" border="0" alt="search">
	 										</a>
						 				</td>
						 				<td align="left" class="tableCell" nowrap>
						 					<c:choose>
							 					<c:when test="${not empty model.record.hevs2 && fn:length(model.record.hevs2) > 3}" >
							 						<input type="text" class="inputTextMediumBlue11" name="hevs2" id="hevs2" size="30" maxlength="25" value="${ fn:substring(model.record.hevs2, 3, fn:length(model.record.hevs2)) }">		
							 					</c:when>
							 					<c:otherwise>
							 						<input type="text" class="inputTextMediumBlue11" name="hevs2" id="hevs2" size="30" maxlength="25" value="${ model.record.hevs2 }">
							 					</c:otherwise>
						 					</c:choose>
						 					
						 				</td>
						 				<td align="right" class="tableCell" nowrap>&nbsp;</td>
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
            <tr height="2"><td></td></tr>
             
            <tr>
				<td colspan="2">
					<table style="width:98%;">
					<tr>
						<td align="right">
		 				    <label class="text11Red" id="orderLineErrMsgPlaceHolder"></label>
	 				    </td>
						<td align="right">
							<c:choose>
			 				    <c:when test="${ not empty model.record.heopd }">
			 				    	<input tabindex=-1 class="inputFormSubmit submitSaveClazz" type="submit" name="submit" id="submit" value='<spring:message code="systema.tror.submit.save"/>'/>
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
			
			
			
				
            <%-- HEADER 
	 		<tr>
            		<td>
	        			<table style="width:98%;" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="15">
				 			<td class="text12White" colspan="5">
							<img onMouseOver="showPop('messageNote_info');" onMouseOut="hidePop('messageNote_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
			 				<span title="messageNoteConsignee/Carrier/Internal"><spring:message code="systema.tror.orders.form.update.label.messageNotes"/></span>
			 				<div class="text11" style="position: relative;" align="left">
								<span style="position:absolute; left:0px; top:0px;" id="messageNote_info" class="popupWithInputText"  >
									<font class="text11">
				           			<b>Message/Notes</b>
				           			<div>
				           			<p>The message will be printed as shown in screen.</p>
				           			<ul>
				           				<li>Max.character per line: 70-characters</li>
				           			    <li>Max.number of lines per message area: 2</li>
				           			</ul>	
				           			</div>
			           			</font>
							</span>
							</div>	
			 				</td>
		 				</tr>
	 				</table>
            		</td>
            </tr>
            
            <tr>
				<td valign="top" colspan="10">
					<table style="width:98%" class="formFrame" > 		
			 		<tr>
			 			<td valign="top" align="left" style="width:70%" >
				 			<table>
				 				
								<tr>
						 			<td class="text12"><spring:message code="systema.tror.orders.form.update.label.messageNotes.receiver"/></td>
						 			<td class="text11">
						 				<%-- this is used ONLY for the delete line operation (mandatory date/linenr) 
						 				<c:forEach items="${Xmodel.record.messageNoteConsigneeRaw}" var="freeTextRecord" varStatus="counter">
						 					<c:if test="${not empty freeTextRecord.frtli}">
						 						<input type="hidden" id="ownMessageNoteReceiverLineNr_${freeTextRecord.frtli}" name="ownMessageNoteReceiverLineNr_${freeTextRecord.frtli}" value="${freeTextRecord.frtli}@${freeTextRecord.frtdt}">
						 					</c:if>
						 				</c:forEach>
						 				<%-- this is ONLY for presentation issues and the INSERT DML  
						 				<textarea class="text11UPPERCASE" style="resize: none;overflow-y: scroll;" id="messageNoteConsignee" name="messageNoteConsignee" limit='70,2' cols="75" rows="5">${Xmodel.record.messageNoteConsignee}</textarea>
					 				</td>
				 				</tr>
								<tr>
						 			<td class="text12"><spring:message code="systema.tror.orders.form.update.label.messageNotes.carrier"/></td>
						 			<td class="text11">
						 				<%-- this is used ONLY for the delete line operation (mandatory date/linenr) 
						 				<c:forEach items="${Xmodel.record.messageNoteCarrierRaw}" var="freeTextRecord" varStatus="counter">
						 					<c:if test="${not empty freeTextRecord.frtli}">
						 						<input type="hidden" id="ownMessageNoteCarrierLineNr_${freeTextRecord.frtli}" name="ownMessageNoteCarrierLineNr_${freeTextRecord.frtli}" value="${freeTextRecord.frtli}@${freeTextRecord.frtdt}">
						 					 </c:if>
						 				</c:forEach>
						 				<%-- this is ONLY for presentation issues and the INSERT DML  
						 				<textarea class="text11UPPERCASE" style="resize: none;overflow-y: scroll;" id="messageNoteCarrier" name="messageNoteCarrier" limit='70,2' cols="75" rows="5">${Xmodel.record.messageNoteCarrier}</textarea>
					 				</td>
				 				</tr>
				 				<tr>
						 			<td class="text12"><spring:message code="systema.tror.orders.form.update.label.messageNotes.sender"/></td>
						 			<td class="text11">
						 				<%-- this is used ONLY for the delete line operation (mandatory date/linenr) 
						 				<c:forEach items="${Xmodel.record.messageNoteInternalRaw}" var="freeTextRecord" varStatus="counter">
						 					<c:if test="${not empty freeTextRecord.frtli}">
						 						<input type="hidden" id="ownMessageNoteInternalLineNr_${freeTextRecord.frtli}" name="ownMessageNoteInternalLineNr_${freeTextRecord.frtli}" value="${freeTextRecord.frtli}@${freeTextRecord.frtdt}">
						 					</c:if> 
						 				</c:forEach>
						 				<%-- this is ONLY for presentation issues and the INSERT DML  
						 				<textarea class="text11UPPERCASE" style="resize: none;overflow-y: scroll;" id="messageNoteInternal" name="messageNoteInternal" limit='70,2' cols="75" rows="5">${Xmodel.record.messageNoteInternal}</textarea>
					 				</td>
				 				</tr>
				 				<tr height="5"><td></td></tr>
				 				<tr>
						 			<td class="text12"><spring:message code="systema.tror.orders.form.update.label.email"/></td>
						 			<td class="text11">
						 				<input type="text" class="inputTextMediumBlue11" size="55" maxlength="70" name="wsmail" id="wsmail" value="${Xmodel.record.wsmail}">
						 				&nbsp;(ekstra booking bekreftelse)
					 				</td>									
				 				</tr>
			 				</table>
		 				</td>
		 				<td valign="top" align="left" style="width:30%" >
							<table> 		
					 		<tr>
					 			<td align="left">
					 			<table class="tableBorderWithRoundCorners" width="480px">
									<tr>
							 			<td valign="top" class="text12">Arkiv docs. &nbsp;
						 					<ul>
						 					<c:forEach items="${XXmodel.record.archivedDocsRecord}" var="record" varStatus="counter">
						 						<li>
						 						<a target="_blank" href="TODOtransportdisp_workflow_renderArchivedDocs.do?doclnk=${record.doclnk}">
		    		    							<img title="Archive" style="vertical-align:middle;" src="resources/images/pdf.png" width="14" height="14" border="0" alt="PDF arch.">
		    		    							${record.doctxt}
				   								</a>&nbsp;&nbsp;&nbsp;
				   								</li>
						 					</c:forEach>
						 					</ul>
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
			 --%>
			<tr height="10"><td ></td></tr>
	</table>
</form>
		
<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->
