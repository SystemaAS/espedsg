<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>
<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTror.jsp" />
<!-- =====================end header ==========================-->
	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/trorglobal_edit.js?ver=${user.versionEspedsg}"></SCRIPT>	
	<SCRIPT type="text/javascript" src="resources/js/tror_landimportorder.js?ver=${user.versionEspedsg}"></SCRIPT>
	<%-- for dialog popup --%>
	<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
	
	<style type = "text/css">
	.ui-dialog{font-size:10pt;}
	.ui-datepicker { font-size:9pt;}
	</style>
	

<form action="tror_landimportorder.do"  name="trorOrderForm" id="trorOrderForm" method="post">
<table width="90%"  class="text11" cellspacing="0" border="0" cellpadding="0">
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
			
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="20%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a class="text14" href="tror_mainorder.do?action=doFetch&heavd=${model.record.heavd}&heopd=${model.record.heopd}" >
					<img style="vertical-align:middle;" src="resources/images/update.gif" width="12px" height="12px" border="0" alt="update order">
					<font class="tabDisabledLink"><spring:message code="systema.tror.order.tab"/></font><font class="text12">&nbsp;${model.record.heavd}/${model.record.heopd}</font>
				</a>
			</td>
			
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="20%" valign="bottom" class="tab" align="center" nowrap>
				<img style="vertical-align:middle;" src="resources/images/lorry_green.png" width="14px" height="14px" border="0" alt="update sub-order">
				<font class="tabLink"><spring:message code="systema.tror.order.suborder.landimport.tab"/></font>									
			</td>
				
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
			
            <%-- FORM DETAIL --%>
            <tr ondrop="drop(event)" ondragover="allowDrop(event)" >
            		<td>
            		<input type="hidden" name="applicationUser" id="applicationUser" value='${user.user}'>
					<input type="hidden" name="action" id="action" value='doUpdate'>
					<input type="hidden" name="selectedType" id="selectedType" value='${model.selectedType}'>
					
					<c:if test="${not empty XXmodel.record.heopd}">
						<input type="hidden" name="heopd" id="heopd" value='${XXmodel.record.heopd}'>
						<input type="hidden" name="heavd" id="heavd" value='${XXmodel.record.heavd}'>
						<input type="hidden" name="hesg" id="hesg" value='${XXmodel.record.hesg}'> <%--sign --%>
						<input type="hidden" name="hedtop" id="hedtop" value='${XXmodel.record.hedtop}'> <%--datum --%>
						<input type="hidden" name="hepro" id="hepro" value='${XXmodel.record.hepro}'> <%--turnr. --%>
						<input type="hidden" name="hest" id="hest" value='${XXmodel.record.hest}'> <%--status --%>
						
					</c:if>
					
					<table style="width:85%;" align="center" border="0" cellspacing="0" cellpadding="0">
			 		<tr height="10"><td ></td></tr>
			 		<tr>
				 		<td width="99%" valign="top">
							<table class="dashboardFrameHeader" align="center" style="width:85%" cellspacing="1" cellpadding="0">
					 			<tr height="20">
						 			<td align="left" class="text12White">
										&nbsp;<img style="vertical-align:bottom;" src="resources/images/update.gif" border="0" alt="edit">	
										&nbsp;<spring:message code="systema.tror.orders.form.update.label.header.edit"/>
										&nbsp;&nbsp;<b>${model.record.heavd} / ${model.record.heopd}</b>
					 				</td>
						 				
					 				<td align="right" class="text12White" width="50%">
					 					STATUS&nbsp;&nbsp;<font style="color: yellow"><b>${model.record.hest}</b></font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="systema.tror.orders.form.update.label.header.customerIdAndName"/>	
										&nbsp;&nbsp;&nbsp;&nbsp;<b>${XXXXmodel.record.trknfaNavn}&nbsp;&nbsp;</b>${XXXXmodel.record.trknfa}&nbsp;&nbsp;
										
					 				</td>
					    		</tr>
					 		</table>
						</td>
					</tr>
					<tr>
						<td width="99%" valign="top">
						<table align="center" style="width:85%" class="tableBorderWithRoundCorners3D_RoundOnlyOnBottom" cellspacing="1" cellpadding="0">
						<tr>
							<td>
							<table align="center" style="width:98%" cellspacing="1" cellpadding="0"> 
					 			<tr height="10"><td >&nbsp;</td></tr>
				 				<tr>
					 				<td colspan="10">
					 				<table class="tableBorderWithRoundCorners">
					 				<tr>
								    		<td class="text14MediumBlue" title="henas"><spring:message code="systema.tror.orders.form.update.label.shipper"/></td>
								    		<td class="text12">
								    			<div>
								    				<label>
								    					<b>${model.record.henas}</b>&nbsp;
								    				</label>
								    			</div>
								    		</td>
								    		<td class="text12">
								    			<div>
								    				<label>
								    					&nbsp;<img style="vertical-align: bottom" src="resources/images/addressIcon.png" width="12" hight="12" border="0" alt="address">
						 							<font class="text12" style="color:#666666;">[${model.record.heads1}&nbsp;&nbsp;${model.record.heads2}&nbsp;&nbsp;${model.record.heads3}]</font>
								    				</label>
								    			</div>
								    		</td>
								    	</tr>
							    		<tr>
								    		<td class="text14MediumBlue" title="henak"><spring:message code="systema.tror.orders.form.update.label.consignee"/></td>
								    		<td class="text12">
								    			<div>
								    				<label>
								    					<b>${model.record.henak}</b>&nbsp;
								    				</label>
								    			</div>
						    				</td>
								    		<td class="text12">
								    			<div>
								    				<label>
								    					&nbsp;<img style="vertical-align: bottom" src="resources/images/addressIcon.png" width="12" hight="12" border="0" alt="address">
						 							<font class="text12" style="color:#666666;">[${model.record.headk1}&nbsp;&nbsp;${model.record.headk2}&nbsp;&nbsp;${model.record.headk3}]</font>
								    				</label>
								    			</div>
						    				</td>
								    	</tr>
								    	</table>
							    	</td>
								</tr>							    	
							    <tr height="5"><td >&nbsp;</td></tr>
					 			
					 			<tr>
					 			<td colspan="10">
					 				<table width="40%">
						    		<tr>
						    			<td class="text12" title="wsetd">&nbsp;Transportmåte</td>
							    		<td class="text12">
							    			<select class="inputTextMediumBlue" name="todo" id="todo" >
							 				  <option value="">-velg-</option>
											  <%-- TODO --%>
											</select>
						    			</td>
						    		</tr>
						    		<tr>
						    			<td class="text12" title="wsetd">&nbsp;Transportid</td>
							    		<td class="text12">
							    			<input type="text" class="inputTextMediumBlue11" name="todo" id="todo" size="11" maxlength="10" value="${XXXmodel.record.todo}">
						    			</td>
						    		</tr>
								    <tr>
						    			<td class="text12" title="wsetd">&nbsp;Container</td>
							    		<td class="text12">
							    			<input type="text" class="inputTextMediumBlue11" name="todo" id="todo" size="11" maxlength="10" value="${XXXmodel.record.todo}">
						    			</td>
						    		</tr>
						    		<tr>
						    			<td class="text12" title="wsetd">&nbsp;Containernr.</td>
							    		<td class="text12">
							    			<input type="text" class="inputTextMediumBlue11" name="todo" id="todo" size="11" maxlength="10" value="${XXXmodel.record.todo}">
						    			</td>
						    		</tr>
						    		</table>
						    		</td>
					    		</tr>
						    	<tr height="10"><td >&nbsp;</td></tr>
						    	<tr>
					 				<td colspan="10">
						 				<table class="tableBorderWithRoundCornersGray">
						 				<tr>
						 				
							 			<td class="text12" title="todo">&nbsp;Fraktbrev</td>
							 			
							    		<td class="text12">
							    			<select class="inputTextMediumBlue" name="todo" id="todo" >
							 				  <option value="">-velg-</option>
											  <option value="J"<c:if test="${Xmodel.record.todo == 'J'}"> selected </c:if> >Ja</option>
											  <option value="N"<c:if test="${Xmodel.record.todo == 'N'}"> selected </c:if> >Nei</option>
											</select>
						    			</td>
							 			<td class="text12" title="todo">&nbsp;Forpassing SAD</td>
							    		<td class="text12">
							    			<select class="inputTextMediumBlue" name="todo" id="todo" >
							 				  <option value="">-velg-</option>
											  <option value="J"<c:if test="${Xmodel.record.todo == 'J'}"> selected </c:if> >Ja</option>
											  <option value="N"<c:if test="${Xmodel.record.todo == 'N'}"> selected </c:if> >Nei</option>
											</select>
						    			</td>
							    		<td class="text12" title="todo">&nbsp;Kjørekvittering</td>
							    		<td class="text12">
							    			<select class="inputTextMediumBlue" name="todo" id="todo" >
							 				  <option value="">-velg-</option>
											  <option value="J"<c:if test="${Xmodel.record.todo == 'J'}"> selected </c:if> >Ja</option>
											  <option value="N"<c:if test="${Xmodel.record.todo == 'N'}"> selected </c:if> >Nei</option>
											</select>
						    			</td>
						    			<td class="text12" title="todo">&nbsp;Tolldekl.</td>
							    		<td class="text12">
							    			<select class="inputTextMediumBlue" name="todo" id="todo" >
							 				  <option value="">-velg-</option>
											  <option value="J"<c:if test="${Xmodel.record.todo == 'J'}"> selected </c:if> >Ja</option>
											  <option value="N"<c:if test="${Xmodel.record.todo == 'N'}"> selected </c:if> >Nei</option>
											</select>
						    			</td>
						    			</tr>
						    			</table>
					    			</td>
					    			
					    			<td align="right"><input tabindex=-1 class="inputFormSubmit submitSaveClazz" type="submit" name="submit" id="submit" value='<spring:message code="systema.tror.submit.save"/>'/></td>
					    			
					    			
					    		</tr>
						    	<tr height="10"><td >&nbsp;</td></tr>
					    		</table>
					    		</td>
					    		</tr>
				 		</table>
						</td>
					</tr>	
				
			<tr height="15"><td ></td></tr>
	</table>
</form>
		
<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->
