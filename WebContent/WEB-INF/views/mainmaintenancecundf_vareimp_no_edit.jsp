<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerMainMaintenance.jsp" />
<!-- =====================end header ==========================-->
	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/mainmaintenancecundf_vareimp_no_edit.js?ver=${user.versionEspedsg}"></SCRIPT>	
	
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
						 	<td width="500" class="tabFantomSpace" align="center" nowrap></td>
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
							<td width="100" valign="bottom" class="tabDisabledSub" align="center" nowrap>
								<a id="alinkMainMaintVareExpNoGate" onClick="setBlockUI(this);" href="mainmaintenancecundf_vareexp_no.do">
										<font class="tabDisabledLinkMinor">&nbsp;
											  <spring:message code="systema.main.maintenance.customer.vareregister.exp.no"/>
										</font>&nbsp;						
								</a>
							</td>
							<td width="100" valign="bottom" class="tabSub" align="center" nowrap>
									<font class="tabLinkMinor">&nbsp;
									   <spring:message code="systema.main.maintenance.customer.vareregister.imp.no"/>
									</font>
							</td>
							<td width="100" valign="bottom" class="tabDisabledSub" align="center" nowrap>
							<!--  	<a id="alinkMainMaintFritextGate" onClick="setBlockUI(this);" href="mainmaintenancecundf_vareimp_dk_edit.do"> -->
									<font class="tabDisabledLinkMinor">&nbsp;
										 <spring:message code="systema.main.maintenance.customer.vareregister.imp.dk"/>
									</font>&nbsp;						
							<!--  	</a> -->
							</td>
							<td width="100" valign="bottom" class="tabDisabledSub" align="center" nowrap>
							<!-- 	<a id="alinkMainMaintParamsGate" onClick="setBlockUI(this);" href="mainmaintenancecundf_vareexp_dk_edit.do">  -->
									<font class="tabDisabledLinkMinor">&nbsp;
										 <spring:message code="systema.main.maintenance.customer.vareregister.exp.dk"/>
									</font>&nbsp;						
							<!--  	</a> -->
							</td>							
							<td width="100" valign="bottom" class="tabDisabledSub" align="center" nowrap>
							<!-- 	<a id="alinkMainMaintParamsGate" onClick="setBlockUI(this);" href="mainmaintenancecundf_vareimp_se_edit.do">  -->
									<font class="tabDisabledLinkMinor">&nbsp;
										 <spring:message code="systema.main.maintenance.customer.vareregister.imp.se"/>
									</font>&nbsp;						
							<!--  	</a> -->
							</td>
							<td width="100" valign="bottom" class="tabDisabledSub" align="center" nowrap>
							<!--  	<a id="alinkMainMaintParamsGate" onClick="setBlockUI(this);" href="mainmaintenancecundf_vareexp_se_edit.do"> -->
									<font class="tabDisabledLinkMinor">&nbsp;
										 <spring:message code="systema.main.maintenance.customer.vareregister.exp.se"/>
									</font>&nbsp;						
							<!-- 	</a>  -->
							</td>		
							
						 	<td width="450" class="tabFantomSpace" align="center" nowrap></td>

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
								<th align="center" width="2%" class="tableHeaderField" >&nbsp;Endre&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;Varenr.&nbsp;</th>
			                    <th class="tableHeaderField" >&nbsp;Beskrivelse&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;VF&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;LK&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;Tariffnr.&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;Tn&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;Pref.&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;PVA&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;Tollsats&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;MF&nbsp;</th>
			                    <th align="center" class="tableHeaderField">Slett</th>
			                </tr>  
				             </thead> 
				             <tbody >  
					            <c:forEach var="record" items="${model.list}" varStatus="counter">   
					               <tr class="tableRow" height="20" >
						               <td id="recordUpdate_${record.levenr}_${record.varenr}" onClick="getRecord(this);" align="center" width="2%" class="tableCellFirst" style="cursor:pointer; border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;">
				               				<img src="resources/images/update.gif" border="0" alt="edit">
						               </td>
						               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 1px;border-color:#FAEBD7;"><font class="text12">&nbsp;${record.varenr}&nbsp;</font></td>
						               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.varebe}&nbsp;</font></td>
						               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.w2vf}&nbsp;</font></td>
						               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.w2lk}&nbsp;</font></td>
						               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.w2vnti}&nbsp;</font></td>
						               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.w2tn}&nbsp;</font></td>
						               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.w2pre}&nbsp;</font></td>
						               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.w2pva}&nbsp;</font></td>
						               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.w2as}&nbsp;</font></td>
						               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.w2mfr}&nbsp;</font></td>
						               
						               <td align="center" width="2%" class="tableCell" style="cursor:pointer; border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;">
				               				<a onclick="javascript:return confirm('Er du sikker på at du vil slette denne?')" tabindex=-1 href="tvinnsadmaintenanceimport_sad001ar_edit.do?action=doDelete&id=${model.dbTable}&varenr=${record.varenr}&levenr=${record.levenr}">
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
							<form action="mainmaintenancecundf_vareimp_no_edit.do" name="formRecord" id="formRecord" method="POST" >
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
													<td width="50%">
														<table>
															<tr>
																<td class="text12" title="varenr">&nbsp;
																	Artikelnr
																	<c:choose>
																		<c:when test="${model.action == 'doCreate'}">
																			<td><input type="text" class="inputTextMediumBlue"  name="varenr" id="varenr" size="30" maxlength="28" value='${model.record.varenr}'></td>
																		</c:when>
																		<c:otherwise>
																			<td><input readonly type="text" class="inputTextReadOnly"  name="varenr" id="varenr" size="30" maxlength="28" value='${model.record.varenr}'></td>
																		</c:otherwise>
																	</c:choose>
																
																
																<td class="text12" title="w2f2">&nbsp;
																	Verdi fast
																</td>
																<td><input type="text"  class="inputTextMediumBlue" name="w2f2" id="w2f2" size="1" maxlength="1" value='${model.record.w2f2}'></td>
																
																
																
															</tr>
															
															<tr>
																<td class="text12" title="syrg">&nbsp;
																	Beskrivelse
																</td>
																<td><input type="text" class="inputTextMediumBlue" name="varebe" id="varebe" size="55" maxlength="50" value='${model.record.varebe}'></td>
															
																<td class="text12" title="w2lk">&nbsp;
																	Land
																</td>
																<td><input type="text" class="inputTextMediumBlue" name="w2lk" id="w2lk" size="2" maxlength="2" value='${model.record.w2lk}'></td>
															</tr>
															<tr>
																<td class="text12" title="w2vnti">&nbsp;
																	Varenr
																</td>
																<td><input type="text" class="inputTextMediumBlue" name="w2vnti" id="w2vnti" size="30" maxlength="30" value='${model.record.w2vnti}'></td>
																<td class="text12" title="spraak">&nbsp;
																	Preferanse
																</td>
																<td><input type="text" class="inputTextMediumBlue" name="w2pre" id="w2pre" size="1" maxlength="1" value='${model.record.w2pre}'></td>
															</tr>
															<tr>
																<td class="text12" title="w2belt">&nbsp;
																	Tolverdi
																</td>
																<td><input type="text" class="inputTextMediumBlue" name="w2belt" id="w2belt" size="15" maxlength="15" value='${model.record.w2belt}'></td>
																<td class="text12" title="w2vktb">&nbsp;
																	Bruttovekt
																</td>
																<td><input type="text" class="inputTextMediumBlue" name="w2vktb" id="w2vktb" size="15" maxlength="15" value='${model.record.w2vktb}'></td>
															</tr>
															<tr>
																<td class="text12" title="w2vktn">&nbsp;
																	Nettovikt
																</td>
																<td><input type="text" class="inputTextMediumBlue" name="w2vktn" id="w2vktn" size="15" maxlength="15" value='${model.record.w2vktn}'></td>
																<td class="text12" title="w2ntm">&nbsp;
																	Mengde
																</td>
																<td><input type="text" class="inputTextMediumBlue" name="w2ntm" id="w2ntm" size="10" maxlength="9" value='${model.record.w2ntm}'></td>
															</tr>
														</table>
													</td>
													
													<td width="50%" valign="top">
														<table>
															<tr>
																<td class="text12" title="w2mfr">&nbsp;
																	Momsfri
																</td>
																<td><input type="text" class="inputTextMediumBlue" name="w2mfr" id="w2mfr" size="1" maxlength="1" value='${model.record.w2mfr}'></td>
																<td class="text12" title="w2beln">&nbsp;
																	Bel NOK
																</td>
																<td><input type="text" class="inputTextMediumBlue" name="w2beln" id="w2beln" size="15" maxlength="15" value='${model.record.w2beln}'></td>
															</tr>
															<tr>
																<td class="text12" title="w2bel">&nbsp;
																	Avg. just
																</td>
																<td><input type="text" class="inputTextMediumBlue" name="w2bel" id="w2bel" size="15" maxlength="13" value='${model.record.w2bel}'></td>
																<td class="text12" title="w2pros">&nbsp;
																	Varev.(proc)
																</td>
																<td><input type="text" class="inputTextMediumBlue" name="w2pros" id="w2pros" size="6" maxlength="6" value='${model.record.w2pros}'></td>
															</tr>
															<tr>
																<td class="text12" title="w2val">&nbsp;
																	Valutakod
																</td>
																<td colspan="4"><input type="text" class="inputTextMediumBlue" name="w2val" id="w2val" size="3" maxlength="3" value='${model.record.w2val}'></td>
															</tr>
															
															<tr>
																<td class="text12" title="w2pva">&nbsp;
																	Pvaf
																</td>
																<td><input type="text"  class="inputTextMediumBlue" name="w2pva" id="w2pva" size="1" maxlength="1" value='${model.record.w2pva}'></td>
																<td class="text12" title="w2as">&nbsp;
																	Avsats
																</td>
																<td><input type="text" class="inputTextMediumBlue" name="w2as" id="w2as" size="10" maxlength="9" value='${model.record.w2as}'></td>
															</tr>
															
															<tr>
																<td colspan="4">&nbsp;</td>
															</tr>
															<tr>
																<td colspan="4">&nbsp;</td>
															</tr>	
															<tr>
																<td colspan="3">&nbsp;</td>
																<td>&nbsp;
																</td>	
															</tr>										
														</table>
													</td>
												</tr>
											</table>
										</td>
									</tr>  <!-- End Artikelinfo -->
									
									
									<tr> <!-- Artikelbesk o avgift -->
										<td width="50%">
											<table class="formFrameHeaderPeachWithBorder" width="100%" 	cellspacing="0" border="0" align="left">
												<tr>
													<td class="text12Bold">&nbsp;
														Artikelbeskrivelse
													</td>
												</tr>
											</table>			

											<table class="formFramePeachGrayRoundBottom"  width="100%" cellspacing="0" border="0" align="center">
												<tr> 
													<td class="text12" title="w2mfr">&nbsp;
														Beskrivelse
													</td>
													<td class="text12" title="w2bel">&nbsp;
														Merke og nr
													</td>
													<td class="text12" title="w2pros">&nbsp;
														Antall kolli
													</td>
													<td class="text12" title="w2beln">&nbsp;
														Enhet
													</td>
												</tr>
												<tr>
													<td><input type="text" class="inputTextMediumBlue" name="w2vt01" id="w2vt01" size="30" maxlength="30" value='${model.record.w2vt01}'></td>
													<td><input type="text" class="inputTextMediumBlue" name="w2ft01" id="w2ft01" size="30" maxlength="28" value='${model.record.w2ft01}'></td>
													<td><input type="text" class="inputTextMediumBlue" name="w2nt01" id="w2nt01" size="10" maxlength="6" value='${model.record.w2nt01}'></td>
													<td><input type="text" class="inputTextMediumBlue" name="w2eh01" id="w2eh01" size="5" maxlength="4" value='${model.record.w2eh01}'></td>
												</tr>
												<tr>
													<td><input type="text" class="inputTextMediumBlue" name="w2vt02" id="w2vt02" size="30" maxlength="30" value='${model.record.w2vt02}'></td>
													<td><input type="text" class="inputTextMediumBlue" name="w2ft02" id="w2ft02" size="30" maxlength="28" value='${model.record.w2ft02}'></td>
													<td><input type="text" class="inputTextMediumBlue" name="w2nt02" id="w2nt02" size="10" maxlength="6" value='${model.record.w2nt02}'></td>
													<td><input type="text" class="inputTextMediumBlue" name="w2eh02" id="w2eh02" size="5" maxlength="4" value='${model.record.w2eh02}'></td>
												</tr>
												<tr>
													<td><input type="text" class="inputTextMediumBlue" name="w2vt03" id="w2vt03" size="30" maxlength="30" value='${model.record.w2vt03}'></td>
													<td><input type="text" class="inputTextMediumBlue" name="w2ft03" id="w2ft03" size="30" maxlength="28" value='${model.record.w2ft03}'></td>
													<td><input type="text" class="inputTextMediumBlue" name="w2nt03" id="w2nt03" size="10" maxlength="6" value='${model.record.w2nt03}'></td>
													<td><input type="text" class="inputTextMediumBlue" name="w2eh03" id="w2eh03" size="5" maxlength="4" value='${model.record.w2eh03}'></td>
												</tr>
											</table>
										</td>
										
										<td width="50%">
											<table class="formFrameHeaderPeachWithBorder" width="100%" 	cellspacing="0" border="0" align="left">
												<tr>
													<td class="text12Bold">&nbsp;
														Avgifter
													</td>
												</tr>
											</table>			
									
											<table class="formFramePeachGrayRoundBottom"  width="100%" cellspacing="0" border="0" align="center">
												<tr>
													<td class="text12" title="w2mfr">&nbsp;
														Kode
													</td>
													<td class="text12" title="w2bel">&nbsp;
														Sekvens
													</td>
													<td class="text12" title="w2pros">&nbsp;
														Sats
													</td>
													<td class="text12" title="w2beln">&nbsp;
														Grunnlag
													</td>
													<td class="text12" title="w2beln">&nbsp;
														Belop
													</td>
												</tr>
												<tr>
													<td><input type="text" class="inputTextMediumBlue" name="w2akd1" id="w2akd1" size="2" maxlength="2" value='${model.record.w2akd1}'></td>
													<td><input type="text" class="inputTextMediumBlue" name="w2asv1" id="w2asv1" size="3" maxlength="3" value='${model.record.w2asv1}'></td>
													<td><input type="text" class="inputTextMediumBlue" name="w2asa1" id="w2asa1" size="15" maxlength="13" value='${model.record.w2asa1}'></td>
													<td><input type="text" class="inputTextMediumBlue" name="w2agr1" id="w2agr1" size="15" maxlength="13" value='${model.record.w2agr1}'></td>
													<td><input type="text" class="inputTextMediumBlue" name="w2abl1" id="w2abl1" size="6" maxlength="6" value='${model.record.w2abl1}'></td>
												</tr>
												<tr>
													<td><input type="text" class="inputTextMediumBlue" name="w2akd2" id="w2akd2" size="2" maxlength="2" value='${model.record.w2akd2}'></td>
													<td><input type="text" class="inputTextMediumBlue" name="w2asv2" id="w2asv2" size="3" maxlength="3" value='${model.record.w2asv2}'></td>
													<td><input type="text" class="inputTextMediumBlue" name="w2asa2" id="w2asa2" size="15" maxlength="13" value='${model.record.w2asa2}'></td>
													<td><input type="text" class="inputTextMediumBlue" name="w2agr2" id="w2agr2" size="15" maxlength="13" value='${model.record.w2agr2}'></td>
													<td><input type="text" class="inputTextMediumBlue" name="w2abl2" id="w2abl2" size="6" maxlength="6" value='${model.record.w2abl2}'></td>
												</tr>
												<tr>
													<td><input type="text" class="inputTextMediumBlue" name="w2akd3" id="w2akd3" size="2" maxlength="2" value='${model.record.w2akd3}'></td>
													<td><input type="text" class="inputTextMediumBlue" name="w2asv3" id="w2asv3" size="3" maxlength="3" value='${model.record.w2asv3}'></td>
													<td><input type="text" class="inputTextMediumBlue" name="w2asa3" id="w2asa3" size="15" maxlength="13" value='${model.record.w2asa3}'></td>
													<td><input type="text" class="inputTextMediumBlue" name="w2agr3" id="w2agr3" size="15" maxlength="13" value='${model.record.w2agr3}'></td>
													<td><input type="text" class="inputTextMediumBlue" name="w2abl3" id="w2abl3" size="6" maxlength="6" value='${model.record.w2abl3}'></td>
												</tr>
											</table>
										
										</td>
										
									</tr>  <!-- End Artikelbesk / Avgift -->
		

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

