<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerMainMaintenance.jsp" />
<!-- =====================end header ==========================-->
	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<script type="text/javascript" src="resources/js/mainmaintenancecundf_vareexp_se_edit.js?ver=${user.versionEspedsg}"></script>	
	
	<style type = "text/css">
	.ui-datepicker { font-size:9pt;}
	</style>

<table id="parentTab" width="100%" class="text11" cellspacing="0" border="0" cellpadding="0">
	<tr height="15"><td>&nbsp;</td></tr>
	<tr>
		<td>
			<%-- tab container component --%>
			<table id="tabContainer"  width="100%" class="text11" cellspacing="0" border="0" cellpadding="0">
				<tr height="2"><td></td></tr>
				<tr height="25"> 
					<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a id="alinkMainMaintGate" tabindex=-1 style="display:block;" href="mainmaintenancegate.do">
						<font class="tabDisabledLink">&nbsp;
							<spring:message code="systema.main.maintenance.label"/>
						</font>
						<img style="vertical-align: middle;"  src="resources/images/list.gif" border="0" alt="general list">
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="15%" valign="bottom" class="tabDisabled" align="center">
						<a id="alinkMainCundfGate" tabindex=-1 style="display:block;" href="mainmaintenancecundf_vkund.do">
						<font class="tabDisabledLink">&nbsp;
							<spring:message code="systema.main.maintenance.customerreg"/>
						</font>&nbsp;
						<img style="vertical-align: middle;"  src="resources/images/list.gif" border="0" alt="avd. general list">
						</a>
					</td>
					<c:choose>
						
						<c:when test="${not empty kundeSessionParams.kundnr}">
							<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
							<td width="15%" valign="bottom" class="tab" align="center">
								<font class="tabLink">&nbsp;
									<spring:message code="systema.main.maintenance.customer"/>
								</font>&nbsp;
								<font class="text11MediumBlue">[${tab_knavn_display}]</font>
							</td>
							<td width="55%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
						</c:when>
						<c:otherwise>
							<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
							<td width="15%" valign="bottom" class="tab" align="center">
								<font class="tabLink">&nbsp;
									<spring:message code="systema.main.maintenance.customer.new"/>
								</font>&nbsp;						
								<img style="vertical-align: middle;"  src="resources/images/add.png" width="12" height="12" border="0" alt="new">
							</td>
							<td width="55%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
						</c:otherwise>
					</c:choose>
				</tr>
			</table>
		</td>
	</tr>
	
	<tr>
		<td>
			<%-- space separator --%>
	 		<table id="tabRows" width="100%" class="tabThinBorderWhite" border="0" cellspacing="0" cellpadding="0">
	 	    <tr height="20"><td>&nbsp;</td></tr>
			
<!-- 	 	    
	 	    <tr>
	 	   		<td width="25%">&nbsp;</td> 
	 	   		<td>
					<table id="firstTabRow" class="formFrameHeaderTransparent" width="1000" cellspacing="0" border="0" cellpadding="0">
						<tr height="20"> 
							<td width="20">&nbsp;</td>
							<td width="80" valign="bottom" class="tabDisabled" align="center" title="Sköna  personer...">
								<a id="alinkMainMaintMavgGate" onClick="setBlockUI(this);" href="mainmaintenancecundf_xxx_edit.do">
									<font class="tabDisabledLinkMinor">&nbsp;Miljöavgift</font>&nbsp;						
								</a>
							</td>

							<td width="80" valign="bottom" class="tabDisabled" align="center" title="xXx...">
								<a id="alinkMainMaintMavgGate" onClick="setBlockUI(this);" href="mainmaintenancecundf_xxx_edit.do">
									<font class="tabDisabledLinkMinor">&nbsp;Fane xxx</font>&nbsp;						
								</a>
							</td>

							<td width="80" valign="bottom" class="tabDisabled" align="center">
								<a id="alinkMainMaintxxxGate" onClick="setBlockUI(this);" href="mainmaintenancecundf_xxx_edit.do">
									<font class="tabDisabledLinkMinor">&nbsp;Fane xyz</font>&nbsp;						
								</a>
							</td>

							<td width="80" valign="bottom" class="tabDisabled" align="center">
								<a id="alinkMainMaintxxxGate" onClick="setBlockUI(this);" href="mainmaintenancecundf_xxx_edit.do">
									<font class="tabDisabledLinkMinor">&nbsp;Fane xyz</font>&nbsp;						
								</a>
							</td>

							<td width="80" valign="bottom" class="tabDisabled" align="center">
								<a id="alinkMainMaintxxGate" onClick="setBlockUI(this);" href="mainmaintenancecundf_xxx_edit.do">
									<font class="tabDisabledLinkMinor">&nbsp;Fane xyz</font>&nbsp;						
								</a>
							</td>

							<td width="80" valign="bottom" class="tabDisabledTrailingAbove" align="center">
								<a id="alinkMainMaintxxxGate" onClick="setBlockUI(this);" href="mainmaintenancecundf_xxx_edit.do">
									<font class="tabDisabledLinkMinor">&nbsp;Fane xyz</font>&nbsp;						
								</a>
							</td>

						   <td width="500"></td>
						</tr>
					</table>
				</td>
 	   	 	</tr>
 	
  -->   	 	
 	   	 	<tr> 
 	   	 		<td>&nbsp;</td>
 	   	 	    <td>
 					<table id= "secondTabRow" class="formFrameHeaderTransparent" style="width:1000px" cellspacing="0" border="0" cellpadding="0">
						<tr height="20"> 
							<td width="110" valign="bottom" class="tabDisabledSub" align="center" nowrap>
								<a id="alinkMainMaintKontaktGate" onClick="setBlockUI(this);" href="mainmaintenancecundf_kunde_edit.do">
									<font class="tabDisabledLinkMinor">&nbsp;
										<spring:message code="systema.main.maintenance.customer"/>
									</font>
								</a>
							</td>
							<td width="110" valign="bottom" class="tabDisabledSub" align="center" nowrap>
								<a id="alinkMainMaintKontaktGate" onClick="setBlockUI(this);" href="mainmaintenancecundf_kontaktpersoner_list.do">
									<font class="tabDisabledLinkMinor">&nbsp;
										<spring:message code="systema.main.maintenance.customer.contacts"/>
									</font>&nbsp;						
								</a>
							</td>
							
							<td width="110" valign="bottom" class="tabDisabledSub" align="center" nowrap>
								<a id="alinkMainMaintFritextGate" onClick="setBlockUI(this);" href="mainmaintenancecundf_fritekst_edit.do">
									<font class="tabDisabledLinkMinor">&nbsp;
										<spring:message code="systema.main.maintenance.customer.text"/>
									</font>&nbsp;						
								</a>
							</td>
							<td width="110" valign="bottom" class="tabDisabledSub" align="center" nowrap>
								<a id="alinkMainMaintParamsGate" onClick="setBlockUI(this);" href="mainmaintenancecundf_params_list.do">
									<font class="tabDisabledLinkMinor">&nbsp;
										<spring:message code="systema.main.maintenance.customer.params"/>
									</font>&nbsp;						
								</a>
							</td>							
							<td width="110" valign="bottom" class="tabSub" align="center" nowrap>
									<font class="tabLinkMinor">&nbsp;
										<spring:message code="systema.main.maintenance.customer.vareregister"/>
									</font>&nbsp;						
							</td>
							
<!--  

							<td width="110" valign="bottom" class="tabDisabledSub" align="center" nowrap>
								<a id="alinkMainMaintMavgGate" onClick="setBlockUI(this);" href="mainmaintenancecundf_xxx_edit.do">
									<font class="tabDisabledLinkMinor">&nbsp;
										<spring:message code="systema.main.maintenance.customer.envfee"/>
									</font>&nbsp;						
								</a>
							</td>							
-->							
<!-- 
							<td width="50" class="tabDisabledTrailingEnd"></td>
 -->
						 	<td width="540" class="tabFantomSpace" align="center" nowrap></td>
						</tr>
					</table>
				</td>
 	   	 	</tr> <!-- End second tab row  -->  

	   	 	<tr> 
 	   	 		<td>&nbsp;</td>
 	   	 	    <td>
 					<table id= "thirdTabRow" class=formFrameHeaderTransparentThirdTabRow style="width:1000px" cellspacing="0" border="0" cellpadding="0">
 					   <tr height="20"><td>&nbsp;</td>
 					   </tr>
					   <tr height="20"> 
							<c:if test="${kundeSessionParams.exportNo == true}">
								<td width="100" valign="bottom" class="tabDisabledSub" align="center" nowrap>
									<a id="alinkMainMaintVareExpNoGate" onClick="setBlockUI(this);" href="mainmaintenancecundf_vareexp_no.do">
											<font class="tabDisabledLinkMinor">&nbsp;
												  <spring:message code="systema.main.maintenance.customer.vareregister.exp.no"/>
											</font>&nbsp;						
									</a>
								</td>
							</c:if>
							<c:if test="${kundeSessionParams.importNo == true}">
								<td width="100" valign="bottom" class="tabDisabledSub" align="center" nowrap>
									<a id="alinkMainMaintVareImpNoGate" onClick="setBlockUI(this);" href="mainmaintenancecundf_vareimp_no.do">
											<font class="tabDisabledLinkMinor">&nbsp;
												  <spring:message code="systema.main.maintenance.customer.vareregister.imp.no"/>
											</font>&nbsp;						
									</a>
								</td>
							</c:if>
							<c:if test="${kundeSessionParams.exportDk == true}">
								<td width="100" valign="bottom" class="tabDisabledSub" align="center" nowrap>
								<!-- 	<a id="alinkMainMaintParamsGate" onClick="setBlockUI(this);" href="mainmaintenancecundf_vareexp_dk_edit.do">  -->
										<font class="tabDisabledLinkMinor">&nbsp;
											 <spring:message code="systema.main.maintenance.customer.vareregister.exp.dk"/>
										</font>&nbsp;						
								<!--  	</a> -->
								</td>							
							</c:if>
							<c:if test="${kundeSessionParams.importDk == true}">
								<td width="100" valign="bottom" class="tabDisabledSub" align="center" nowrap>
								<!--  	<a id="alinkMainMaintFritextGate" onClick="setBlockUI(this);" href="mainmaintenancecundf_vareimp_dk_edit.do"> -->
										<font class="tabDisabledLinkMinor">&nbsp;
											 <spring:message code="systema.main.maintenance.customer.vareregister.imp.dk"/>
										</font>&nbsp;						
								<!--  	</a> -->
								</td>
							</c:if>
							<td width="100" valign="bottom" class="tabSub" align="center" nowrap>
									<font class="tabLinkMinor">&nbsp;
										 <spring:message code="systema.main.maintenance.customer.vareregister.exp.se"/>
									</font>&nbsp;						
							</td>		
							<c:if test="${kundeSessionParams.importSe == true}">
								<td width="100" valign="bottom" class="tabDisabledSub" align="center" nowrap>
								<!-- 	<a id="alinkMainMaintParamsGate" onClick="setBlockUI(this);" href="mainmaintenancecundf_vareimp_se_edit.do">  -->
										<font class="tabDisabledLinkMinor">&nbsp;
											 <spring:message code="systema.main.maintenance.customer.vareregister.imp.se"/>
										</font>&nbsp;						
								<!--  	</a> -->
								</td>
							</c:if>
							
						 	<td width="${kundeSessionParams.fantomSpaceWidth}" class="tabFantomSpace" align="center" nowrap></td>

						</tr>
					</table>
				</td>
 	   	 	</tr> <!-- End third tab row -->

 	   	 	<tr height="30">
 	   	 		<td>&nbsp;</td>
 	   	 		<td width="100%">
 	   	 		 <table id="mainArea" class="tabThinBorderWhite" width="100%" cellspacing="0" border="0" align="left">
 	   	 		 	<tr id="list">
 	   	 		 		<td>&nbsp;
							<table id="mainList" class="display compact cell-border" >
							<thead>
							<tr>
								<th align="center" width="2%" class="tableHeaderField" >&nbsp;<spring:message code="systema.edit"/>&nbsp;</th>
								<th class="tableHeaderField" title="svew_knso">&nbsp;Sökbegrepp&nbsp;</th>
			                    <th class="tableHeaderField" title="svew_vasl">&nbsp;Varubeskrivning&nbsp;</th>
								<th class="tableHeaderField" title="svew_vano">&nbsp;Varupost nr.&nbsp;</th>
								<th class="tableHeaderField" title="svew_vata">&nbsp;Varukod Taric nr&nbsp;</th>
								<th class="tableHeaderField" title="svew_ulkd">&nbsp;Land&nbsp;</th>
								<th class="tableHeaderField" title="svew_brut">&nbsp;Bruttovikt&nbsp;</th>
								<th class="tableHeaderField" title="svew_neto">&nbsp;Nettovikt&nbsp;</th>
								<th class="tableHeaderField" title="svew_eup1">&nbsp;Förfarande 37:1&nbsp;</th>
								<th class="tableHeaderField" title="svew_eup2">&nbsp;Förfarande 37:2&nbsp;</th>
			                    <th align="center" class="tableHeaderField"><spring:message code="systema.delete"/></th>
			                </tr>  
				             </thead> 
				             <tbody >  
					            <c:forEach var="record" items="${model.list}" varStatus="counter">   
					               <tr class="tableRow" height="20" >
						               <td id="recordUpdate_${record.svew_knnr}_${record.svew_knso}" onClick="getRecord(this);" align="center" width="2%" class="tableCellFirst" style="cursor:pointer; border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;">
				               				<img src="resources/images/update.gif" border="0" alt="edit">
						               </td>
						               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 1px;border-color:#FAEBD7;"><font class="text12">&nbsp;${record.svew_knso}&nbsp;</font></td>
						               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.svew_vasl}&nbsp;</font></td>
						               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.svew_vano}&nbsp;</font></td>
						               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.svew_vata}&nbsp;</font></td>
						               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.svew_ulkd}&nbsp;</font></td>
						               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.svew_brut}&nbsp;</font></td>
						               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.svew_neto}&nbsp;</font></td>
						               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.svew_eup1}&nbsp;</font></td>
						               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.svew_eup2}&nbsp;</font></td>
						               
						               <td align="center" width="2%" class="tableCell" style="cursor:pointer; border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;">
				               				<a onclick="javascript:return confirm('<spring:message code="systema.delete.confirm"/>')" tabindex=-1 href="mainmaintenancecundf_vareexp_se_edit.do?action=doDelete&svew_knnr=${record.svew_knnr}&svew_knso=${record.svew_knso}">
							               		<img valign="bottom" src="resources/images/delete.gif" border="0" width="15px" height="15px" alt="remove">
							               	</a>
						               </td>
					               </tr> 
					            </c:forEach>
					          </tbody>
				            </table>
 	   	 		 		</td>
 	   	 		 	</tr>

					<tr height="25">
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>

 	   	 		 	<tr id="details">
 	   	 		 		<td>
							<form action="mainmaintenancecundf_vareexp_se_edit.do" name="formRecord" id="formRecord" method="POST" >
								<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
								<input type="hidden" name="updateId" id="updateId" value="${model.updateId}"> 
								<input type="hidden" name="action" id=action value="doUpdate">
								<table id="paramsDetails" width="100%" cellspacing="0" border="0" align="left">
									<spring:hasBindErrors name="record"> 
										<tr>
											<td>
												<table align="left" border="0" cellspacing="0" cellpadding="0">
													<tr>
														<td>
															<ul class="isa_error text12">
																<c:forEach var="error" items="${errors.allErrors}">
																	<li><spring:message code="${error.code}"
																			text="${error.defaultMessage}" />&nbsp;&nbsp;</li>
																</c:forEach>
															</ul>
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</spring:hasBindErrors>
									<c:if test="${not empty model.errorMessage}">
									<tr>
										<td >
								           	<table align="left" border="0" cellspacing="0" cellpadding="0">
										 		<tr>
										 			<td >
										 				<ul class="isa_error text12" >
								                                  <li>${model.errorMessage}</li>                                    
								                              </ul>
										 			</td>
												</tr>
											</table>
										</td>
									</tr>
									</c:if>
								
									<tr>
										<td>
											<button name="newRecordButton" id="newRecordButton" class="inputFormSubmitStd" type="button">
												<spring:message code="systema.new"/>
											</button>&nbsp;&nbsp;
									</tr>
									<tr height="2">
										<td>&nbsp;</td>
									</tr>
									<tr> <!-- Artikelinfo -->
										<td colspan="2">
											<table class="formFrameHeaderPeachWithBorder" width="100%" 	cellspacing="0" border="0" align="left">
												<tr>
													<td class="text12Bold">&nbsp;
														Artikelinfo
													</td>
												</tr>
											</table>			

											<table class="formFramePeachGrayRoundBottom"  width="100%" cellspacing="0" border="0" align="center">
												<tr> 
													<td width="50%" valign="top">
														<table border="0">
															<tr>
																<td class="text12" title="svew_knso">&nbsp;<font class="text14RedBold" >*</font>Sökbegrepp:</td>
																<td class="text12">
																	<input type="text"  required oninvalid="this.setCustomValidity('Obligatoriskt')" onchange="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="svew_knso" id="svew_knso" size="35" maxlength="35" value='${model.record.svew_knnr}'>
																	&nbsp;Varukod Taric nr:
																	<input type="text" class="inputTextMediumBlue" name="svew_vata" id="svew_vata" size="10" maxlength="8" value='${model.record.svew_vata}'>
																</td>
																<td class="text12" title="svew_lagi">&nbsp;Id:</td>
																<td>
																	<input type="text" class="inputTextMediumBlue" name="svew_lagi" id="svew_lagi" size="15" maxlength="14" value='${model.record.svew_lagi}'>
																</td>
															</tr>
															
															<tr>
																<td class="text12" title="svew_vasl">&nbsp;Varubeskrivning:</td>
																<td><input type="text" class="inputTextMediumBlue" name="svew_vasl" id="svew_vasl" size="70" maxlength="70" value='${model.record.svew_vasl}'></td>
																<td class="text12" title="svew_ulkd">&nbsp;Land:</td>
																<td><input type="text" class="inputTextMediumBlue" name="svew_ulkd" id="svew_ulkd" size="2" maxlength="2" value='${model.record.svew_ulkd}'>
																</td>
															</tr>
															
															<tr>
																<td class="text12" title="svew_vasl2">&nbsp;Varubeskrivning:</td>
																<td><input type="text" class="inputTextMediumBlue" name="svew_vasl" id="svew_vasl" size="70" maxlength="70" value='${model.record.svew_vasl2}'></td>
																<td class="text12" title="svew_vati">&nbsp;Varukod (33:3):</td>
																<td><input type="text" class="inputTextMediumBlue" name="svew_vati" id="svew_vati" size="5" maxlength="4" value='${model.record.svew_vati}'>
																</td>
															</tr>
															
															<tr>
																<td class="text12" title="svew_vasl3">&nbsp;Varubeskrivning:</td>
																<td><input type="text" class="inputTextMediumBlue" name="svew_vasl3" id="svew_vasl3" size="70" maxlength="70" value='${model.record.svew_vasl3}'></td>
																<td class="text12" title="svew_vat4">&nbsp;Varukod (33:4):</td>
																<td><input type="text" class="inputTextMediumBlue" name="svew_vat4" id="svew_vat4" size="5" maxlength="4" value='${model.record.svew_vat4}'></td>
															</tr>
															
															<tr>
																<td class="text12" title="svew_vasl4">&nbsp;Varubeskrivning:</td>
																<td><input type="text" class="inputTextMediumBlue" name="svew_vasl4" id="svew_vasl4" size="70" maxlength="70" value='${model.record.svew_vasl4}'></td>
																<td class="text12" title="svew_vat5">&nbsp;Varukod (33:5):</td>
																<td><input type="text" class="inputTextMediumBlue" name="svew_vat5" id="svew_vat5" size="5" maxlength="4" value='${model.record.svew_vat5}'></td>
															</tr>
															<tr>
																<td class="text12" title="svew_vasl5">&nbsp;Varubeskrivning:</td>
																<td><input type="text" class="inputTextMediumBlue" name="svew_vasl5" id="svew_vasl5" size="70" maxlength="70" value='${model.record.svew_vasl5}'></td>
																<td class="text12" title="svew_vano">&nbsp;Varupost nr::</td>
																<td>
																	<input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="svew_vano" id="svew_vano" size="5" maxlength="5" value='${model.record.svew_vano}'>
																</td>
															</tr>
															<tr>
																<td class="text12" title="svew_komr">&nbsp;Komm. ref.nr:</td>
																<td><input type="text" class="inputTextMediumBlue" name="svew_komr" id="svew_komr" size="70" maxlength="70" value='${model.record.svew_komr}'></td>
																<td class="text12" title="svew_fnkd">&nbsp;FN-kod för farligt gods:</td>
																<td><input type="text" class="inputTextMediumBlue" name="svew_fnkd" id="svew_fnkd" size="5" maxlength="4" value='${model.record.svew_knso}'></td>
															</tr>

														</table>
													</td>
													
													<td width="50%" valign="top">
														<table border="0">
															<tr>
																<td class="text12" title="svew_brut">Bruttovikt:</td>
																<td><input type="text" onKeyPress="return amountKey(event)" class="inputTextMediumBlue" name="svew_brut" id="svew_brut" size="15" maxlength="18" value='${model.record.svew_knso}'></td>
																<td class="text12" title="svew_eup1">Förf.(37:1):</td>
																<td><input type="text" class="inputTextMediumBlue" name="svew_eup1" id="svew_eup1" size="5" maxlength="4" value='${model.record.svew_eup1}'></td>
															</tr>
															<tr>
																<td class="text12" title="svew_neto">Nettovikt:</td>
																<td><input type="text" onKeyPress="return amountKey(event)" class="inputTextMediumBlue" name="svew_neto" id="svew_neto" size="15" maxlength="18" value='${model.record.svew_neto}'></td>
																<td class="text12" title="svew_eup2">Förf.(37:2):</td>
																<td><input type="text" class="inputTextMediumBlue" name="svew_eup2" id="svew_eup2" size="5" maxlength="4" value='${model.record.svew_eup2}'></td>
															</tr>
															<tr>
																<td class="text12" title="svew_betk">Betalkod:</td>
																<td><input type="text" class="inputTextMediumBlue" name="svew_betk" id="svew_betk" size="2" maxlength="1" value='${model.record.svew_betk}'></td>
																<td class="text12" title="svew_ankv">Kvantitet:</td>
																<td><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="svew_ankv" id="svew_ankv" size="10" maxlength="10" value='${model.record.svew_ankv}'></td>
															</tr>
															<tr>
																<td class="text12" title="svew_kono">Kontigentnr.:</td>
																<td><input type="text" class="inputTextMediumBlue" name="svew_kono" id="svew_kono" size="5" maxlength="3" value='${model.record.svew_kono}'></td>
																<td class="text12" title="svew_stva">Statistiskt värde:</td>
																<td><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="svew_stva" id="svew_stva" size="12" maxlength="11" value='${model.record.svew_stva}'></td>
															</tr>
															
															<tr>
																<td class="text12" title="svew_atin">Indikator:</td>
																<td><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="svew_atin" id="svew_atin" size="5" maxlength="3" value='${model.record.svew_atin}'></td>
																<td class="text12" title="svew_stva2">Tullvärde:</td>
																<td><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="svew_stva2" id="svew_stva2" size="12" maxlength="11" value='${model.record.svew_stva2}'></td>
															</tr>

															<tr>
																<td class="text12" title="svew_fabl">Fakturabelopp:</td>
																<td><input type="text" onKeyPress="return amountKey(event)" class="inputTextMediumBlue" name="svew_fabl" id="svew_fabl" size="15" maxlength="14" value='${model.record.svew_fabl}'></td>
																<td colspan="2">
																	&nbsp;
																</td>
															</tr>

														</table>
													</td>
												</tr>
											</table>
										</td>
									</tr>  <!-- End Artikelinfo -->
									
									
									<tr> <!-- Artikelbesk o avgift -->
										<td width="50%" valign="top">
											<table class="formFrameHeaderPeachWithBorder" width="100%" 	cellspacing="0" border="0" align="left">
												<tr>
													<td class="text12Bold">&nbsp;<spring:message code="systema.main.maintenance.mainmaintenancesadvare.header.productdesc"/></td>
												</tr>
											</table>			

											<table class="formFramePeachGrayRoundBottom"  width="100%" cellspacing="0" border="0" align="center">
												<tr>
													<td  class="text12" colspan="4" align="right">
														<a tabindex="-1" id="enhetIdLink">Ref.
															<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="Søk" >
														</a>&nbsp;&nbsp;&nbsp;
													</td>
												</tr>
												<tr> 
													<td class="text12" title="w2vt01"><spring:message code="systema.main.maintenance.mainmaintenancesadvare.w2vt01"/></td>
													<td class="text12" title="w2ft01"><spring:message code="systema.main.maintenance.mainmaintenancesadvare.w2ft01"/></td>
													<td class="text12" title="w2nt01"><spring:message code="systema.main.maintenance.mainmaintenancesadvare.w2nt01"/></td>
													<td class="text12" title="w2eh01"><spring:message code="systema.main.maintenance.mainmaintenancesadvare.w2eh01"/></td>
												</tr>
												<tr>
													<td><input type="text" class="inputTextMediumBlue" name="w2vt01" id="w2vt01" size="31" maxlength="30" value='${model.record.svew_knso}'></td>
													<td><input type="text" class="inputTextMediumBlue" name="w2ft01" id="w2ft01" size="29" maxlength="28" value='${model.record.svew_knso}'></td>
													<td><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="w2nt01" id="w2nt01" size="10" maxlength="6" value='${model.record.svew_knso}'></td>
													<td><input type="text" class="inputTextMediumBlue" name="w2eh01" id="w2eh01" size="5" maxlength="4" value='${model.record.svew_knso}'></td>
												</tr>
												<tr>
													<td><input type="text" class="inputTextMediumBlue" name="w2vt02" id="w2vt02" size="31" maxlength="30" value='${model.record.svew_knso}'></td>
													<td><input type="text" class="inputTextMediumBlue" name="w2ft02" id="w2ft02" size="29" maxlength="28" value='${model.record.svew_knso}'></td>
													<td><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="w2nt02" id="w2nt02" size="10" maxlength="6" value='${model.record.svew_knso}'></td>
													<td><input type="text" class="inputTextMediumBlue" name="w2eh02" id="w2eh02" size="5" maxlength="4" value='${model.record.svew_knso}'></td>
												</tr>
												<tr>
													<td><input type="text" class="inputTextMediumBlue" name="w2vt03" id="w2vt03" size="31" maxlength="30" value='${model.record.svew_knso}'></td>
													<td><input type="text" class="inputTextMediumBlue" name="w2ft03" id="w2ft03" size="29" maxlength="28" value='${model.record.svew_knso}'></td>
													<td><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="w2nt03" id="w2nt03" size="10" maxlength="6" value='${model.record.svew_knso}'></td>
													<td><input type="text" class="inputTextMediumBlue" name="w2eh03" id="w2eh03" size="5" maxlength="4" value='${model.record.svew_knso}'></td>
												</tr>
												<tr>
													<td><input type="text" class="inputTextMediumBlue" name="w2vt04" id="w2vt04" size="31" maxlength="30" value='${model.record.svew_knso}'></td>
													<td><input type="text" class="inputTextMediumBlue" name="w2ft04" id="w2ft04" size="29" maxlength="28" value='${model.record.svew_knso}'></td>
													<td><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="w2nt04" id="w2nt04" size="10" maxlength="6" value='${model.record.svew_knso}'></td>
													<td><input type="text" class="inputTextMediumBlue" name="w2eh04" id="w2eh04" size="5" maxlength="4" value='${model.record.svew_knso}'></td>
												</tr>
												<tr>
													<td><input type="text" class="inputTextMediumBlue" name="w2vt05" id="w2vt05" size="31" maxlength="30" value='${model.record.svew_knso}'></td>
													<td><input type="text" class="inputTextMediumBlue" name="w2ft05" id="w2ft05" size="29" maxlength="28" value='${model.record.svew_knso}'></td>
													<td><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="w2nt05" id="w2nt05" size="10" maxlength="6" value='${model.record.svew_knso}'></td>
													<td><input type="text" class="inputTextMediumBlue" name="w2eh05" id="w2eh05" size="5" maxlength="4" value='${model.record.svew_knso}'></td>
												</tr>
												<tr>
													<td>&nbsp;</td>
													<td><input type="text" class="inputTextMediumBlue" name="w2ft06" id="w2ft06" size="29" maxlength="28" value='${model.record.svew_knso}'></td>
													<td><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="w2nt06" id="w2nt06" size="10" maxlength="6" value='${model.record.svew_knso}'></td>
													<td><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="w2eh06" id="w2eh06" size="5" maxlength="4" value='${model.record.svew_knso}'></td>
												</tr>
												<tr>
													<td>&nbsp;</td>
													<td><input type="text" class="inputTextMediumBlue" name="w2ft07" id="w2ft07" size="29" maxlength="28" value='${model.record.w2fsvew_knso07}'></td>
													<td><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="w2nt07" id="w2nt07" size="10" maxlength="6" value='${model.record.svew_knso}'></td>
													<td><input type="text" class="inputTextMediumBlue" name="w2eh07" id="w2eh07" size="5" maxlength="4" value='${model.record.svew_knso}'></td>
												</tr>												
												<tr>
													<td colspan="4" class="text12" height="25">&nbsp;</td>
												</tr>
											</table>
										</td>
										
										<td width="50%" valign="top">
											<table class="formFrameHeaderPeachWithBorder" width="100%" 	cellspacing="0" border="0" align="left">
												<tr>
													<td class="text12Bold">&nbsp;<spring:message code="systema.main.maintenance.mainmaintenancesadvare.header.fee"/></td>
												</tr>
											</table>			
									
											<table class="formFramePeachGrayRoundBottom"  width="100%" cellspacing="0" border="0" align="left">
												<tr>
													<td  class="text12" colspan="4" align="left">
														<a tabindex="-1" id="avgkodeIdLink">Ref.
															<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="Søk" >
														</a>
													</td>
												</tr>
												<tr>
													<td class="text12" title="w2akd"><spring:message code="systema.main.maintenance.mainmaintenancesadvare.w2akd"/></td>
													<td class="text12" title="w2asv"><spring:message code="systema.main.maintenance.mainmaintenancesadvare.w2asv"/></td>
													<td class="text12" title="w2asa"><spring:message code="systema.main.maintenance.mainmaintenancesadvare.w2asa"/></td>
												</tr>
												<tr>
													<td><input type="text" class="inputTextMediumBlue" name="w2akd1" id="w2akd1" size="2" maxlength="2" value='${model.record.svew_knso}'></td>
													<td><input type="text" class="inputTextMediumBlue" name="w2asv1" id="w2asv1" size="3" maxlength="3" value='${model.record.svew_knso}'></td>
													<td><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="w2asa1" id="w2asa1" size="15" maxlength="13" value='${model.record.svew_knso}'></td>
												</tr>
												<tr>
													<td><input type="text" class="inputTextMediumBlue" name="w2akd2" id="w2akd2" size="2" maxlength="2" value='${model.record.svew_knso}'></td>
													<td><input type="text" class="inputTextMediumBlue" name="w2asv2" id="w2asv2" size="3" maxlength="3" value='${model.record.svew_knso}'></td>
													<td><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="w2asa2" id="w2asa2" size="15" maxlength="13" value='${model.record.svew_knso}'></td>
												</tr>
												<tr>
													<td><input type="text" class="inputTextMediumBlue" name="w2akd3" id="w2akd3" size="2" maxlength="2" value='${model.record.svew_knso}'></td>
													<td><input type="text" class="inputTextMediumBlue" name="w2asv3" id="w2asv3" size="3" maxlength="3" value='${model.record.svew_knso}'></td>
													<td><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="w2asa3" id="w2asa3" size="15" maxlength="13" value='${model.record.svew_knso}'></td>
												</tr>
												<tr>
													<td><input type="text" class="inputTextMediumBlue" name="w2akd4" id="w2akd4" size="2" maxlength="2" value='${model.record.svew_knso}'></td>
													<td><input type="text" class="inputTextMediumBlue" name="w2asv4" id="w2asv4" size="3" maxlength="3" value='${model.record.svew_knso}'></td>
													<td><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="w2asa4" id="w2asa4" size="15" maxlength="13" value='${model.record.svew_knso}'></td>
												</tr>
												<tr>
													<td><input type="text" class="inputTextMediumBlue" name="w2akd5" id="w2akd5" size="2" maxlength="2" value='${model.record.svew_knso}'></td>
													<td><input type="text" class="inputTextMediumBlue" name="w2asv5" id="w2asv5" size="3" maxlength="3" value='${model.record.svew_knso}'></td>
													<td><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="w2asa5" id="w2asa5" size="15" maxlength="13" value='${model.record.svew_knso}'></td>
												</tr>
												<tr>
													<td><input type="text" class="inputTextMediumBlue" name="w2akd6" id="w2akd6" size="2" maxlength="2" value='${model.record.svew_knso}'></td>
													<td><input type="text" class="inputTextMediumBlue" name="w2asv6" id="w2asv6" size="3" maxlength="3" value='${model.record.svew_knso}'></td>
													<td><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="w2asa6" id="w2asa6" size="15" maxlength="13" value='${model.record.svew_knso}'></td>
												</tr>
												<tr>
													<td><input type="text" class="inputTextMediumBlue" name="w2akd7" id="w2akd7" size="2" maxlength="2" value='${model.record.svew_knso}'></td>
													<td><input type="text" class="inputTextMediumBlue" name="w2asv7" id="w2asv7" size="3" maxlength="3" value='${model.record.svew_knso}'></td>
													<td><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="w2asa7" id="w2asa7" size="15" maxlength="13" value='${model.record.svew_knso}'></td>
												</tr>
												<tr>
													<td><input type="text" class="inputTextMediumBlue" name="w2akd8" id="w2akd8" size="2" maxlength="2" value='${model.record.svew_knso}'></td>
													<td><input type="text" class="inputTextMediumBlue" name="w2asv8" id="w2asv8" size="3" maxlength="3" value='${model.record.svew_knso}'></td>
													<td><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="w2asa8" id="w2asa8" size="15" maxlength="13" value='${model.record.svew_knso}'></td>
												</tr>
											</table>
										
										</td>
										
									</tr>  <!-- End Artikelbesk / Avgift -->
		
									<tr> <!-- Tilleggsopplysningar -->
										<td width="50%"  valign="top">
											<table class="formFrameHeaderPeachWithBorder" width="100%" 	cellspacing="0" border="0" align="left">
												<tr>
													<td class="text12Bold">&nbsp;<spring:message code="systema.main.maintenance.mainmaintenancesadvare.header.supplementsa"/></td>
												</tr>
											</table>			

											<table class="formFramePeachGrayRoundBottom"  width="100%" cellspacing="0" border="0" align="center">
												<tr>
													<td>&nbsp;</td>
													<td  class="text12" align="left">
														<a tabindex="-1" id="kommref1IdLink">Ref.
															<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="Søk" >
														</a>
													</td>
												</tr>
												<tr> 
													<td class="text12" title="w2top"><spring:message code="systema.main.maintenance.mainmaintenancesadvare.w2top"/></td>
													<td class="text12" title="w2cre"><spring:message code="systema.main.maintenance.mainmaintenancesadvare.w2cre"/></td>
												</tr>
												<tr>
													<td><input type="text" class="inputTextMediumBlue" name="w2top1" id="w2top1" size="20" maxlength="17" value='${model.record.svew_knso}'></td>
													<td><input type="text" class="inputTextMediumBlue" name="w2cre1" id="w2cre1" size="3" maxlength="3" value='${model.record.svew_knso}'></td>
												</tr>
												<tr>
													<td><input type="text" class="inputTextMediumBlue" name="w2top2" id="w2top2" size="20" maxlength="17" value='${model.record.svew_knso}'></td>
													<td><input type="text" class="inputTextMediumBlue" name="w2cre2" id="w2cre2" size="3" maxlength="3" value='${model.record.svew_knso}'></td>
												</tr>
												<tr>
													<td><input type="text" class="inputTextMediumBlue" name="w2top3" id="w2top3" size="20" maxlength="17" value='${model.record.svew_knso}'></td>
													<td><input type="text" class="inputTextMediumBlue" name="w2cre3" id="w2cre3" size="3" maxlength="3" value='${model.record.svew_knso}'></td>
												</tr>
												<tr>
													<td><input type="text" class="inputTextMediumBlue" name="w2top4" id="w2top4" size="20" maxlength="17" value='${model.record.svew_knso}'></td>
													<td><input type="text" class="inputTextMediumBlue" name="w2cre4" id="w2cre4" size="3" maxlength="3" value='${model.record.svew_knso}'></td>
												</tr>
												<tr>
													<td><input type="text" class="inputTextMediumBlue" name="w2top5" id="w2top5" size="20" maxlength="17" value='${model.record.svew_knso}'></td>
													<td><input type="text" class="inputTextMediumBlue" name="w2cre5" id="w2cre5" size="3" maxlength="3" value='${model.record.svew_knso}'></td>
												</tr>
											</table>
										</td>
										
										<td width="50%" valign="top">
											<table class="formFrameHeaderPeachWithBorder" width="100%" 	cellspacing="0" border="0" align="left">
												<tr>
													<td class="text12Bold">&nbsp;<spring:message code="systema.main.maintenance.mainmaintenancesadvare.header.supplementsb"/></td>
												</tr>
											</table>			

											<table class="formFramePeachGrayRoundBottom"  width="100%" cellspacing="0" border="0" align="center">
												<tr>
													<td>&nbsp;</td>
													<td  class="text12" align="left">
														<a tabindex="-1" id="kommref2IdLink">Ref.
															<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="Søk" >
														</a>
													</td>
												</tr>
												<tr> 
													<td class="text12" title="w2top"><spring:message code="systema.main.maintenance.mainmaintenancesadvare.w2top"/></td>
													<td class="text12" title="w2cre"><spring:message code="systema.main.maintenance.mainmaintenancesadvare.w2cre"/></td>
												</tr>
												<tr>
													<td><input type="text" class="inputTextMediumBlue" name="w2top6" id="w2top6" size="20" maxlength="17" value='${model.record.svew_knso}'></td>
													<td><input type="text" class="inputTextMediumBlue" name="w2cre6" id="w2cre6" size="3" maxlength="3" value='${model.record.svew_knso}'></td>
												</tr>
												<tr>
													<td><input type="text" class="inputTextMediumBlue" name="w2top7" id="w2top7" size="20" maxlength="17" value='${model.record.svew_knso}'></td>
													<td><input type="text" class="inputTextMediumBlue" name="w2cre7" id="w2cre7" size="3" maxlength="3" value='${model.record.svew_knso}'></td>
												</tr>
												<tr>
													<td><input type="text" class="inputTextMediumBlue" name="w2top8" id="w2top8" size="20" maxlength="17" value='${model.record.svew_knso}'></td>
													<td><input type="text" class="inputTextMediumBlue" name="w2cre8" id="w2cre8" size="3" maxlength="3" value='${model.record.svew_knso}'></td>
												</tr>
												<tr>
													<td><input type="text" class="inputTextMediumBlue" name="w2top9" id="w2top9" size="20" maxlength="17" value='${model.record.svew_knso}'></td>
													<td><input type="text" class="inputTextMediumBlue" name="w2cre9" id="w2cre9" size="3" maxlength="3" value='${model.record.svew_knso}'></td>
												</tr>
												<tr>
													<td><input type="text" class="inputTextMediumBlue" name="w2top10" id="w2top10" size="20" maxlength="17" value='${model.record.svew_knso}'></td>
													<td><input type="text" class="inputTextMediumBlue" name="w2cre10" id="w2cre10" size="3" maxlength="3" value='${model.record.svew_knso}'></td>
												</tr>
											</table>
										
										</td>
										
									</tr>  <!-- End Tilleggsopplysningar -->


									<tr><td>&nbsp;</td></tr>
									<tr> 
										<td colspan="2" align="right">
											<input class="inputFormSubmit" type="submit" name="submit" id="submit" value='<spring:message code="systema.save"/>'/>
										</td>
									</tr>
								</table>
			 	    		</form>
 	   	 		 		</td>
 	   	 		 	</tr>
 
 	   	 		 </table>
 	   	 		
 	   	 		</td>
 
 	   	 		<td width="30">
 	   	 			&nbsp;
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

