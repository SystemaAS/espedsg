<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerMainMaintenance.jsp" />
<!-- =====================end header ==========================-->
	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/mainmaintenanceavdsadimport_syftaaar_edit.js?ver=${user.versionEspedsg}"></SCRIPT>	
	
	<style type = "text/css">
	.ui-datepicker { font-size:9pt;}
	</style>


<table width="100%" class="text11" cellspacing="0" border="0" cellpadding="0">
	<tr height="15"><td>&nbsp;</td></tr>
	<tr>
		<td>
		<%-- tab container component --%>
		<table width="100%" class="text11" cellspacing="0" border="0" cellpadding="0">
			<tr height="2"><td></td></tr>
				<tr height="25"> 
					<c:choose>
						<c:when test="${not empty model.updateId}">
							<td width="13%" valign="bottom" class="tabDisabled" align="center" nowrap>
								<a id="alinkMainMaintGate" tabindex=-1 style="display:block;" href="mainmaintenancegate.do">
								<font class="tabDisabledLink">&nbsp;Vedlikehold</font>
								<img style="vertical-align: middle;"  src="resources/images/list.gif" border="0" alt="general list">
								</a>
							</td>
							<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
							<td width="13%" valign="bottom" class="tabDisabled" align="center">
								<a id="alinkMainMaintAvdGate" onClick="setBlockUI(this);" href="mainmaintenanceavdgate.do?id=${model.dbTable}">
									<font class="tabDisabledLink">&nbsp;Avdelinger</font>&nbsp;						
									<img style="vertical-align: middle;"  src="resources/images/list.gif" border="0" alt="avd. gate list">
								</a>
							</td>
							<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
							<td width="16%" valign="bottom" class="tabDisabled" align="center">
								<a id="alinkMainMaintAvdSadiSyftaaar" onClick="setBlockUI(this);" href="mainmaintenanceavdsadimport_syftaaar.do">
									<font class="tabDisabledLink">&nbsp;TVINN SAD Import.</font>&nbsp;
									<img style="vertical-align: middle;"  src="resources/images/list.gif" border="0" alt="avd. general list">
								</a>
							</td>
							<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
							<td width="16%" valign="bottom" class="tab" align="center">
								<font class="tabLink">&nbsp;Avd.</font>&nbsp;
								<font class="text11MediumBlue">(${model.avd})</font>
							</td>
							
							<td width="45%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
						</c:when>
						<c:otherwise>
							<td width="13%" valign="bottom" class="tabDisabled" align="center" nowrap>
								<a id="alinkMainMaintGate" tabindex=-1 style="display:block;" href="mainmaintenancegate.do">
								<font class="tabDisabledLink">&nbsp;Vedlikehold</font>
								<img style="vertical-align: middle;"  src="resources/images/list.gif" border="0" alt="general list">
								</a>
							</td>
							<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
							<td width="13%" valign="bottom" class="tabDisabled" align="center">
								<a id="alinkMainMaintAvdGate" onClick="setBlockUI(this);" href="mainmaintenanceavdgate.do?id=${model.dbTable}">
									<font class="tabDisabledLink">&nbsp;Avdelinger</font>&nbsp;						
									<img style="vertical-align: middle;"  src="resources/images/list.gif" border="0" alt="avd. gate list">
								</a>
							</td>
							<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
							<td width="16%" valign="bottom" class="tabDisabled" align="center">
								<a id="alinkMainMaintAvdSadiSyftaaar" onClick="setBlockUI(this);" href="mainmaintenanceavdsadimport_syftaaar.do">
									<font class="tabDisabledLink">&nbsp;TVINN SAD Import.</font>&nbsp;
									<img style="vertical-align: middle;"  src="resources/images/list.gif" border="0" alt="avd. general list">
								</a>
							</td>
							<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
							<td width="16%" valign="bottom" class="tab" align="center">
								<font class="tabLink">&nbsp;Lage ny avd.</font>&nbsp;
								<img style="vertical-align: middle;"  src="resources/images/add.png" width="12" height="12" border="0" alt="new">
							</td>
							<td width="45%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
						</c:otherwise>
					</c:choose>
				</tr>
		</table>
		</td>
	</tr>
	
	
	
	
	<tr>
		<td>
		<%-- space separator --%>
	 		<table width="100%" class="tabThinBorderWhite" border="0" cellspacing="0" cellpadding="0">
	 	    <tr height="20"><td>&nbsp;</td></tr>
	 	    
	 	    <%-- Validation errors --%>
			<spring:hasBindErrors name="record"> <%-- name must equal the command object name in the Controller --%>
			<tr>
				<td width="5%">&nbsp;</td>
				<td >
		           	<table align="left" border="0" cellspacing="0" cellpadding="0">
		           	<tr >
		           	<td >					
			            <ul class="isa_error text12" >
			            <c:forEach var="error" items="${errors.allErrors}">
			                <li >
			                	<spring:message code="${error.code}" text="${error.defaultMessage}"/>&nbsp;&nbsp;
			                </li>
			            </c:forEach>
			            </ul>
					</td>
					</tr>
					</table>
				</td>
			</tr>
			</spring:hasBindErrors>
			
			<%-- Other errors (none validation errors) --%>
			<c:if test="${not empty model.errorMessage}">
			<tr>
				<td width="5%">&nbsp;</td>
				<td >
		           	<table align="left" border="0" cellspacing="0" cellpadding="0">
				 		<tr>
				 			<td >
				 				<ul class="isa_error text12" >
		                                  <li>[ERROR on Update] - Server return code: ${model.errorMessage}</li>                                    
		                              </ul>
				 			</td>
						</tr>
					</table>
				</td>
			</tr>
			</c:if>
	 	    
	 	   
	 	    
	 	    <tr>
				<td width="5%">&nbsp;</td>
				<td width="100%">
					<form action="mainmaintenanceavd_syfa14r_edit.do" name="formRecord" id="formRecord" method="POST" >
						<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
						<input type="hidden" name="updateId" id=updateId value="${Xmodel.updateId}">
						<input type="hidden" name="action" id=action value="${Xmodel.action}">
						<table cellspacing="1" border="0" align="left">
				    	    <tr>
								<td class="text12" title="sidl">Bruke dataliste
									<select name="sidl" id="sidl" class="inputTextMediumBlueMandatoryField">
					  					<option value="">-velg-</option>
					  					<option value="J"<c:if test="${ model.record.sidl == 'J'}"> selected </c:if> >Ja</option>
					  					<option value="N"<c:if test="${ model.record.sidl == 'N'}"> selected </c:if> >Nej</option>
					  					<option value="1"<c:if test="${ model.record.sidl == '1'}"> selected </c:if> >1</option>
								  	</select>
								</td>
								<td class="text12" title="sia4">&nbsp;&nbsp;Format A4
									<select name="sia4" id="sia4" class="inputTextMediumBlueMandatoryField">
					  					<option value="">-velg-</option>
					  					<option value="J"<c:if test="${ model.record.sia4 == 'J'}"> selected </c:if> >Ja</option>
					  					<option value="N"<c:if test="${ model.record.sia4 == 'N'}"> selected </c:if> >Nej</option>
								  	</select>
								</td>	
								<td class="text12" title="sitdn">&nbsp;&nbsp;Nrt.teller intern ref.
									<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlueMandatoryField" name="sitdn" id="sitdn" size="10" maxlength="7" value='${model.record.sitdn}'>
								</td>
								<td class="text12" title="sitolk">&nbsp;&nbsp;Bruk kommunikasjon
									<select name="sitolk" id="sitolk" class="inputTextMediumBlueMandatoryField">
					  					<option value="">-velg-</option>
					  					<option value="J"<c:if test="${ model.record.sitolk == 'J'}"> selected </c:if> >Ja</option>
					  					<option value="N"<c:if test="${ model.record.sitolk == 'N'}"> selected </c:if> >Nej</option>
								  	</select>
								</td>
								<td class="text12" title="siekst">&nbsp;&nbsp;Importere eksterne data
									<select name="siekst" id="siekst" class="inputTextMediumBlueMandatoryField">
					  					<option value="">-velg-</option>
					  					<option value="J"<c:if test="${ model.record.siekst == 'J'}"> selected </c:if> >Ja</option>
					  					<option value="N"<c:if test="${ model.record.siekst == 'N'}"> selected </c:if> >Nej</option>
					  					<option value="R"<c:if test="${ model.record.siekst == 'R'}"> selected </c:if> >R</option>
								  	</select>
								</td>
							</tr>
							
							<tr height="3"><td>&nbsp;</td></tr>
						</table>
						
						
	 	    		</form>
				</td>
			</tr>
			
			<tr>
			<td width="5%">&nbsp;</td>
			<td>
				<table width="98%" cellspacing="1" border="0" align="left">
					<tr>
						<td width="50%" >
						<table width="100%" cellspacing="1" border="1" align="left">
						<tr>
				 			<td >		
				 				<%-- SENDER --%>
				 				<table width="95%" align="left" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
							 		<tr height="15">
							 			<td class="text12White">
											&nbsp;<img onMouseOver="showPop('2_info');" onMouseOut="hidePop('2_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
								 			<b>&nbsp;2.</b>&nbsp;Avsender / Eksportør&nbsp;<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
							 				<div class="text11" style="position: relative;" align="left">
							 				<span style="position:absolute; top:2px; width:250px;" id="2_info" class="popupWithInputText text11"  >
								           		<b>Avsender / Eksportør</b>
								           		<p>
								           		Oppgi selgerens navn og adresse. 
								           		Dersom ekspedisjonen omfatter fakturaer fra flere selgere enn det er plass til i rubrikken, skrives "se vedlagte fakturaer". 
								           		</p>
								           		<p>
								           		KRAV TIL FELTET
								           		</p>
											</span>
											</div>
											
						 				</td>
					 				</tr>
				 				</table>
				 			</td>
				 		</tr>
					 		
				 		<tr>	
				 			<td >	
								<%-- create record --%>
							 	<table width="90%" align="left" class="formFrame" border="0" cellspacing="0" cellpadding="0">
							 		<tr>
								 		<td width="100%">
									 		<table width="100%" border="0" cellspacing="0" cellpadding="0">
										 		<tr height="10"><td ></td></tr>
										        
										        <tr>
										        	<%-- ================================================================================== --%>
										        	<%-- This hidden values are used when an AJAX event from within a dialog box is fired.  
										        		 These original values will be used when the user clicks "Cancel" buttons (puttting
										        		 back original value)																--%> 
										        	<%-- ================================================================================== --%>
										        	<input type="hidden" name="orig_sikns" id="orig_sikns" value='${Xmodel.record.sikns}'>
										        	<input type="hidden" name="orig_sinas" id="orig_sinas" value='${Xmodel.record.sinas}'>
										        	<input type="hidden" name="orig_siads1" id="orig_siads1" value='${Xmodel.record.siads1}'>
										        	<input type="hidden" name="orig_siads2" id="orig_siads2" value='${Xmodel.record.siads2}'>
										        	<input type="hidden" name="orig_siads3" id="orig_siads3" value='${Xmodel.record.siads3}'>
										        	
										            <td class="text12" align="left" >&nbsp;&nbsp;
											            <span title="sikns">Kundenummer</span>
											            <img title="Spørring på fritekster for kunder" id="senderFreeTextImg" onMouseOver="showPop('senderFtxtinfo');" onMouseOut="hidePop('senderFtxtinfo');" onClick="showPop('senderInfoFreeTextDialog');" style="vertical-align:top;" width="18px" height="18px" src="resources/images/largeTextContent.png" border="0" alt="info">
											 			<div class="text11" style="position: relative;" align="left">
														<span style="position:absolute; top:2px; width:250px;" id="senderFtxtinfo" class="popupWithInputText"  >
											           		<font class="text11" >Spørring på fritekster for kunder</font>
														</span>
														</div>
														
											 			<span style="position:absolute; left:300px; top:300px; width:400px; height:500px;" id="senderInfoFreeTextDialog" class="popupWithInputText"  >
											           		<div class="text11" align="left">
											           		Spørring på fritekster for kunder
											           		<p>
											           			<textarea rows="20" cols="40" class="inputText" name="senderInfoTextArea" id="senderInfoTextArea" maxlength="1000"></textarea>
											           		</p>
											           		<p>
											           			&nbsp;&nbsp;<button name="senderInfoFreeTextDialogCloseOk" id="senderInfoFreeTextDialogCloseOk" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('senderInfoFreeTextDialog');"><spring:message code="systema.tvinn.sad.import.ok"/></button>
											           		</p>
											           		</div>
														</span>
														
														
														
										            </td>
										            <td class="text12" align="left" >&nbsp;&nbsp;
										            <span title="sinas" id="v_sinas" class="validation"><font class="text16RedBold" >*</font>Navn&nbsp;</span>
										            	<a tabindex="-1" id="sinasIdLink">
															<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
														</a>				
										            </td>
										        </tr>
										        <tr>
										            <td class="text12" align="left"><input type="text" class="inputTextMediumBlue" name="sikns" id="sikns" size="9" maxlength="8" value="${Xmodel.record.sikns}"></td>
										            <td class="text12" align="left"><input type="text" class="inputTextMediumBlueMandatoryField"  name="sinas" id="sinas" size="31" maxlength="30" value="${Xmodel.record.sinas}"></td>
										        </tr>
										        <tr height="4"><td>&nbsp;</td></tr>
										        <tr>
										            <td class="text12" align="left" >&nbsp;&nbsp;
										            <span title="siads1" id="v_siads1" class="validation"><font class="text16RedBold" >*</font>Adresse-1</span></td>
										            <td>&nbsp;</td>
										        </tr>
										        <tr>
										            <td colspan="2" align="left"><input type="text" class="inputTextMediumBlueMandatoryField"  name="siads1" id="siads1" size="40" maxlength="30" value="${Xmodel.record.siads1}"></td>
			    							            
										        </tr>
										        <tr>
										            <td class="text12" align="left" >&nbsp;&nbsp;
										            <span title="siads2" id="v_siads2" class="validation">Adresse-2</span></td>
			    							            <td>&nbsp;</td>
										        </tr>
										        <tr>
										            <td colspan="2" align="left"><input type="text" class="inputTextMediumBlue" name="siads2" id="siads2" size="40" maxlength="30" value="${Xmodel.record.siads2}"></td>
			   							            
										        </tr>
										        <tr>
										            <td class="text12" align="left" >&nbsp;&nbsp;
										            <span title="siads3" id="v_siads3" class="validation">Adresse-3</span></td>
			   							            <td>&nbsp;</td>							            
										        </tr>
										        <tr>
										            <td colspan="2" align="left"><input type="text" class="inputTextMediumBlue" name="siads3" id="siads3" size="40" maxlength="30" value="${Xmodel.record.siads3}"></td>
			   							        </tr>
										        <tr height="15">
										            <td class="text12Bold" align="left" >&nbsp;</td> 
			   							            <td>&nbsp;</td>							            
										        </tr>
									        </table>
								        </td>
							        </tr>
								</table>          
			            	</td>
			           	</tr> 
			           	<tr height="10"><td></td></tr>
			           	
			           	<%-- RECEIVER --%>
								 		
					 		
						</table>
						</td>
						<td width="50%" >
						<table width="100%" cellspacing="1" border="1" align="left">
							<tr>
								<td>
								
								
								</td>
							</tr>
						</table>
						</td>
					</tr>
				</table>
			</td>
			</tr>			
				
	 	    <tr height="20"><td>&nbsp;</td></tr>
	 	    

	 		</table>
		</td>
	</tr>
</table>	

<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->

