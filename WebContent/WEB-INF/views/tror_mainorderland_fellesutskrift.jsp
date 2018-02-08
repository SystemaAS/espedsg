<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>
<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTror.jsp" />
<!-- =====================end header ==========================-->
	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/trorglobal_edit.js?ver=${user.versionEspedsg}"></SCRIPT>	
	<SCRIPT type="text/javascript" src="resources/js/tror_mainorderland_fellesutskrift.js?ver=${user.versionEspedsg}"></SCRIPT>
	<SCRIPT type="text/javascript" src="resources/js/trorFkeys_landimport.js?ver=${user.versionEspedsg}"></SCRIPT>
	
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
		<tr height="2"><td></td></tr>
		<tr height="25"> 
			<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a class="text14" onClick="setBlockUI(this);" href="tror_mainorderlist.do?action=doFind&sign=${recordOrderTrorLand.hesg}" > 	
					<img style="vertical-align:middle;" src="resources/images/bulletGreen.png" width="6px" height="6px" border="0" alt="open orders">
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tror.orderlist.tab"/></font>&nbsp;<font class="text10Orange">F2</font>
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a tabindex=-1 id="createNewOrderTabIdLink" style="display:block;" runat="server" href="#">
					<img style="vertical-align:middle;" src="resources/images/add.png" width="12px" height="12px" border="0" alt="create new">
					<font class="tabDisabledLink"><spring:message code="systema.tror.createnew.order.tab"/></font>
				</a>
				
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="15%" valign="bottom" class="tab" align="center" nowrap>
				<img style="vertical-align:middle;" src="resources/images/printer2.png" width="12px" height="12px" border="0" alt="fellesutskrift">
				<font class="tabLink"><spring:message code="systema.tror.fellesprint.tab"/></font>
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
			<input type="hidden" name="fkeysavd" id="fkeysavd" value='${recordOrderTrorLandImport.heavd}'>
			<input type="hidden" name="fkeysopd" id="fkeysopd" value='${recordOrderTrorLandImport.heopd}'>
			<input type="hidden" name="fkyessign" id="fkyessign" value='${recordOrderTrorLandImport.hesg}'>
				
			<tr height="20"><td>&nbsp;</td></tr>

 	   	 	<tr>
				<td align="center" width="99%">
					<table width="99%" cellspacing="0" border="0">
 	   	 				<tr height="30px"><td ></td></tr>
 	   	 				<tr>
							<td  width="50%" >
							<table width="100%" border="0">
								<tr>
									<td class="text12" title="todo">&nbsp;<spring:message code="systema.tror.more.fellesutskrift.label.avd"/></td>
									<td class="text12" title="todo">&nbsp;<spring:message code="systema.tror.more.fellesutskrift.label.sign"/></td>
									<td class="text12" title="todo">&nbsp;<spring:message code="systema.tror.more.fellesutskrift.label.turnr"/></td>
									<td class="text12" title="todo">&nbsp;<spring:message code="systema.tror.more.fellesutskrift.label.oppdragnr"/></td>
									<td class="text12" title="todo">&nbsp;<spring:message code="systema.tror.more.fellesutskrift.label.godsnr"/></td>
									<td class="text12" title="todo">&nbsp;<spring:message code="systema.tror.more.fellesutskrift.label.godsnr"/> fra dato</td>
									
								</tr>
								<tr>
									<td class="text12" title="todo"><input type="text" class="inputTextMediumBlue"  name="todo" id="todo" size="5" maxlength="4" value=''></td>
									<td class="text12" title="todo"><input type="text" class="inputTextMediumBlue"  name="todo" id="todo" size="5" maxlength="3" value=''></td>
									<td class="text12" title="todo"><input type="text" class="inputTextMediumBlue"  name="todo" id="todo" size="10" maxlength="8" value=''></td>
									<td class="text12" title="todo"><input type="text" class="inputTextMediumBlue"  name="todo" id="todo" size="10" maxlength="7" value=''></td>
									<td class="text12" title="todo"><input type="text" class="inputTextMediumBlue"  name="todo" id="todo" size="17" maxlength="15" value=''></td>
									<td class="text12" title="todo"><input type="text" class="inputTextMediumBlue"  name="todo" id="todo" size="10" maxlength="8" value=''></td>
								</tr>	
							</table>
							</td>
						</tr>
						<tr height="10px"><td ></td></tr>

						<tr>
						<td>	
							<table width="80%" align="left" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
						 		<tr height="15">
						 			<td class="text12White" align="left" >
						 				<b>&nbsp;&nbsp;<spring:message code="systema.tror.more.fellesutskrift.label.parameter"/></b>
					 				</td>
				 				</tr>
			 				</table>
							<table width="80%" align="left" class="formFrame" border="0" cellspacing="0" cellpadding="0">
						 		<tr height="15"><td class="text" align="left"></td></tr>
						 		<tr>
							 		<td>
								 		<table width="100%" border="0" cellspacing="0" cellpadding="0">
									 		<tr>
									 			<td class="text12" align="left" title="todo">&nbsp;<spring:message code="systema.tror.more.fellesutskrift.label.oppdformular"/>&nbsp;og fra dato</td>
									            <td class="text12" align="left" title="todo">&nbsp;<spring:message code="systema.tror.more.fellesutskrift.label.styckkfraktbrev"/></td>
									            <td class="text12" align="left" title="todo">&nbsp;<spring:message code="systema.tror.more.fellesutskrift.label.tpassfraktbrev"/></td>
									            <td class="text12" align="left" title="todo">&nbsp;<spring:message code="systema.tror.more.fellesutskrift.label.fsad"/></td>
									            <td class="text12" align="left" title="todo">&nbsp;<spring:message code="systema.tror.more.fellesutskrift.label.inlandflyfraktbrev"/></td>
									            <td class="text12" align="left" title="todo">&nbsp;<spring:message code="systema.tror.more.fellesutskrift.label.losselista"/></td>
									            <td class="text12" align="left" title="todo">&nbsp;<spring:message code="systema.tror.more.fellesutskrift.label.ffaktura"/></td>
									       		<td class="text12" align="left" title="todo">&nbsp;<spring:message code="systema.tror.more.fellesutskrift.label.cmr"/></td>
									        </tr>
									        <tr>
									 			<td align="left">
								        			<select class="inputTextMediumBlue" name="todo" id="todo">
								 						<option value="">-velg-</option>
									 				  	<option value="J"<c:if test="${Xmodel.record.fakdm == 'J' || empty Xmodel.record.fakdm}"> selected </c:if> >Ja</option>
														<option value="N"<c:if test="${Xmodel.record.fakdm == 'N'}"> selected </c:if> >Nei</option>
													</select>
													<input type="text" class="inputTextMediumBlue"  name="todo" id="todo" size="10" maxlength="8" value=''>
												</td>
												<td align="left">
								        			<select class="inputTextMediumBlue" name="todo" id="todo">
								 						<option value="">-velg-</option>
									 				  	<option value="J"<c:if test="${Xmodel.record.fakdm == 'J' || empty Xmodel.record.fakdm}"> selected </c:if> >Ja</option>
														<option value="N"<c:if test="${Xmodel.record.fakdm == 'N'}"> selected </c:if> >Nei</option>
													</select>
												</td>
												<td align="left">
								        			<select class="inputTextMediumBlue" name="todo" id="todo">
								 						<option value="">-velg-</option>
									 				  	<option value="J"<c:if test="${Xmodel.record.fakdm == 'J' || empty Xmodel.record.fakdm}"> selected </c:if> >Ja</option>
														<option value="N"<c:if test="${Xmodel.record.fakdm == 'N'}"> selected </c:if> >Nei</option>
													</select>
												</td>
												<td align="left">
								        			<select class="inputTextMediumBlue" name="todo" id="todo">
								 						<option value="">-velg-</option>
									 				  	<option value="J"<c:if test="${Xmodel.record.fakdm == 'J' || empty Xmodel.record.fakdm}"> selected </c:if> >Ja</option>
														<option value="N"<c:if test="${Xmodel.record.fakdm == 'N'}"> selected </c:if> >Nei</option>
													</select>
												</td>
												<td align="left">
								        			<select class="inputTextMediumBlue" name="todo" id="todo">
								 						<option value="">-velg-</option>
									 				  	<option value="J"<c:if test="${Xmodel.record.fakdm == 'J' || empty Xmodel.record.fakdm}"> selected </c:if> >Ja</option>
														<option value="N"<c:if test="${Xmodel.record.fakdm == 'N'}"> selected </c:if> >Nei</option>
													</select>
												</td>
												<td align="left">
								        			<select class="inputTextMediumBlue" name="todo" id="todo">
								 						<option value="">-velg-</option>
									 				  	<option value="J"<c:if test="${Xmodel.record.fakdm == 'J' || empty Xmodel.record.fakdm}"> selected </c:if> >Ja</option>
														<option value="N"<c:if test="${Xmodel.record.fakdm == 'N'}"> selected </c:if> >Nei</option>
													</select>
												</td>
												<td align="left">
								        			<select class="inputTextMediumBlue" name="todo" id="todo">
								 						<option value="">-velg-</option>
									 				  	<option value="J"<c:if test="${Xmodel.record.fakdm == 'J' || empty Xmodel.record.fakdm}"> selected </c:if> >Ja</option>
														<option value="N"<c:if test="${Xmodel.record.fakdm == 'N'}"> selected </c:if> >Nei</option>
													</select>
												</td>
												<td align="left">
								        			<select class="inputTextMediumBlue" name="todo" id="todo">
								 						<option value="">-velg-</option>
									 				  	<option value="J"<c:if test="${Xmodel.record.fakdm == 'J' || empty Xmodel.record.fakdm}"> selected </c:if> >Ja</option>
														<option value="N"<c:if test="${Xmodel.record.fakdm == 'N'}"> selected </c:if> >Nei</option>
													</select>
												</td>								
									        </tr>
									        <tr height="10px"><td class="text" align="left"></td></tr>
									        <tr>
									 			<td valign="top" colspan="3">
									 				<table >
									 					<tr>
									 						<td colspan="3" class="text12" align="left" title="todo">&nbsp;<spring:message code="systema.tror.more.fellesutskrift.label.faktoppdtyper"/></td>
									 					</tr>
									 					<tr>
												        	<td align="left">
											        			<input type="text" class="inputTextMediumBlue"  name="todo" id="todo" size="4" maxlength="2" value=''>
											        			<input type="text" class="inputTextMediumBlue"  name="todo" id="todo" size="4" maxlength="2" value=''>
											        			<input type="text" class="inputTextMediumBlue"  name="todo" id="todo" size="4" maxlength="2" value=''>
											        			<input type="text" class="inputTextMediumBlue"  name="todo" id="todo" size="4" maxlength="2" value=''>
											        			<input type="text" class="inputTextMediumBlue"  name="todo" id="todo" size="4" maxlength="2" value=''>
											        			<input type="text" class="inputTextMediumBlue"  name="todo" id="todo" size="4" maxlength="2" value=''>
											        		</td>		
												        </tr>
									 				</table>
									 			</td>
									 			<td valign="top" colspan="6">
									 				<table class="tableBorderWithRoundCornersLightGray"	>
									 					<tr>
									 						<td class="text12MediumBlue" align="left" title="todo">&nbsp;<spring:message code="systema.tror.more.fellesutskrift.label.jobbko"/></td>
									            			<td class="text12MediumBlue" align="left" title="todo">&nbsp;<spring:message code="systema.tror.more.fellesutskrift.label.avskriver"/></td>
									            			<td class="text12MediumBlue" align="left" title="todo">&nbsp;<spring:message code="systema.tror.more.fellesutskrift.label.avskriver.mlapp"/></td>
									            		</tr>
									            		<tr>
									            			<td align="left">
											        			<select class="inputTextMediumBlue" name="todo" id="todo">
											 						<option value="">-velg-</option>
												 				  	<option value="J"<c:if test="${Xmodel.record.fakdm == 'J' || empty Xmodel.record.fakdm}"> selected </c:if> >Ja</option>
																	<option value="N"<c:if test="${Xmodel.record.fakdm == 'N'}"> selected </c:if> >Nei</option>
																</select>
															</td>
															<td align="left">
											        			<input type="text" class="inputTextMediumBlue"  name="todo" id="todo" size="12" maxlength="10" value=''>
											        		</td>
											        		<td align="left">
											        			<input type="text" class="inputTextMediumBlue"  name="todo" id="todo" size="12" maxlength="10" value=''>
											        		</td>		
									            		</tr>
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
		
		
	<%-- Pop-up window --%>
		<tr>
		<td>
			<div id="dialogCreateNewOrder" title="Dialog">
				<form  action="tror_mainordergate.do" name="createNewOrderForm" id="createNewOrderForm" method="post">
				 	<input type="hidden" name="actionGS" id="actionGS" value='doUpdate'/>
					<input type="hidden" name="applicationUser" id="applicationUser" value='${user.user}'>
					
					<p class="text12" >&nbsp;<spring:message code="systema.tror.order.suborder.title.types"/></p>
					 				
					<table>
						<tr>
							<td class="text12MediumBlue">Type&nbsp;
								<select required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" class="inputTextMediumBlue11MandatoryField" name="selectedType" id="selectedType">
									<option value="A"><spring:message code="systema.tror.order.suborder.landimport"/></option>
									<option value="B"><spring:message code="systema.tror.order.suborder.landexport"/></option>
									<option value="C"><spring:message code="systema.tror.order.suborder.airimport"/></option>
									<option value="D"><spring:message code="systema.tror.order.suborder.airexport"/></option>
								</select>
								&nbsp;&nbsp;<div style="display:inline;" id="imagePreview"></div>
							</td>
						</tr>
						<tr>
							<td class="text12MediumBlue">Avd&nbsp;&nbsp;
								<select required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="heavd" id="heavd" >
			 						<c:forEach var="record" items="${model.avdList}" >
				 				  		<option value="${record.koakon}"<c:if test="${model.record.heavd == record.koakon}"> selected </c:if> >${record.koakon}</option>
									</c:forEach>  
								</select>
							</td>
						</tr>
					</table>
						
				</form>
			</div>
		</td>
		</tr>	
		
			
	</table>
		
<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->
