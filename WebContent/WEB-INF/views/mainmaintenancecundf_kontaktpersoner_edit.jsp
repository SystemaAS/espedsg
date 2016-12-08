<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerMainMaintenance.jsp" />
<!-- =====================end header ==========================-->
	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/mainmaintenancecundf_kontaktpersoner_edit.js?ver=${user.versionEspedsg}"></SCRIPT>	
	
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
						<font class="tabDisabledLink">&nbsp;Vedlikehold</font>
						<img style="vertical-align: middle;"  src="resources/images/list.gif" border="0" alt="general list">
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="15%" valign="bottom" class="tabDisabled" align="center">
						<a id="alinkMainCundfGate" tabindex=-1 style="display:block;" href="mainmaintenancecundf_vkund.do">
	
						<font class="tabDisabledLink">&nbsp;Kunderegister</font>&nbsp;
						<img style="vertical-align: middle;"  src="resources/images/list.gif" border="0" alt="avd. general list">
						</a>
					</td>
					<c:choose>
						
						<c:when test="${not empty kundeSessionParams.kundnr}">
							<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
							<td width="15%" valign="bottom" class="tab" align="center">
								<font class="tabLink">&nbsp;Kunde</font>&nbsp;
								<font class="text11MediumBlue">[${tab_knavn_display}]</font>
							</td>
							<td width="55%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
						</c:when>
						<c:otherwise>
							<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
							<td width="15%" valign="bottom" class="tab" align="center">
									<font class="tabLink">&nbsp;Lage ny kunde</font>&nbsp;						
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
							<td width="80" valign="bottom" class="tabDisabledSub" align="center" nowrap>
								<a id="alinkMainMaintKontaktGate" onClick="setBlockUI(this);" href="mainmaintenancecundf_kunde_edit.do">
									<font class="tabDisabledLinkMinor">&nbsp;Kunde</font>&nbsp;						
								</a>
							</td>

							<td width="80" valign="bottom" class="tabSub" align="center" nowrap>
									<font class="tabLinkMinor">&nbsp;Kontakter</font>&nbsp;						
							</td>

							<td width="80" valign="bottom" class="tabDisabledSub" align="center" nowrap>
								<a id="alinkMainMaintVareImpGate" onClick="setBlockUI(this);" href="mainmaintenancecundf_vareimp_edit.do">
									<font class="tabDisabledLinkMinor">&nbsp;Varer(import)</font>&nbsp;						
								</a>
							</td>

							<td width="80" valign="bottom" class="tabDisabledSub" align="center" nowrap>
								<a id="alinkMainMaintVareExpGate" onClick="setBlockUI(this);" href="mainmaintenancecundf_vareexp_edit.do">
									<font class="tabDisabledLinkMinor">&nbsp;Varer(export)</font>&nbsp;						
								</a>
							</td>

							<td width="80" valign="bottom" class="tabDisabledSub" align="center">
								<a id="alinkMainMaintFritextGate" onClick="setBlockUI(this);" href="mainmaintenancecundf_fritext_edit.do">
									<font class="tabDisabledLinkMinor">&nbsp;Fritekst</font>&nbsp;						
								</a>
							</td>

							<td width="80" valign="bottom" class="tabDisabledSub" align="center" nowrap>
								<a id="alinkMainMaintParamsGate" onClick="setBlockUI(this);" href="mainmaintenancecundf_params_edit.do">
									<font class="tabDisabledLinkMinor">&nbsp;Parametere</font>&nbsp;						
								</a>
							</td>
							
							<td width="80" valign="bottom" class="tabDisabledSub" align="center" nowrap>
								<a id="alinkMainMaintMavgGate" onClick="setBlockUI(this);" href="mainmaintenancecundf_xxx_edit.do">
									<font class="tabDisabledLinkMinor">&nbsp;Miljøavgift</font>&nbsp;						
								</a>
							</td>							
							
<!-- 
							<td width="50" class="tabDisabledTrailingEnd"></td>
 -->
						 	<td width="540" class="tabFantomSpace" align="center" nowrap></td>
						</tr>
					</table>
				</td>
 	   	 	
 	   	 	
 	   	 	</tr> <!-- End second tab row -->
 	   	 	
 	   	 	<tr height="30">
 	   	 		<td>&nbsp;</td>
 	   	 		<td width="100%">
 	   	 		 <table id="mainArea" class="tabThinBorderWhite" width="100%" cellspacing="0" border="0" align="left">
 	   	 		 	<tr id="list">
 	   	 		 		<td>&nbsp;
							<table id="mainList" class="display compact cell-border" >
							<thead>
								<tr>
									<th align="center" class="tableHeaderField" >&nbsp;Endre&nbsp;</th>
									<th class="tableHeaderField" >&nbsp;Kontaktperson&nbsp;</th>
									<th class="tableHeaderField" >&nbsp;Type&nbsp;</th>
				                    <th class="tableHeaderField" >&nbsp;Telefon&nbsp;</th>
									<th class="tableHeaderField" >&nbsp;Mobiltelefon&nbsp;</th>
									<th class="tableHeaderField" >&nbsp;E-mail&nbsp;</th>
									<th class="tableHeaderField" >&nbsp;Slett&nbsp;</th>
				                </tr>  
				             </thead> 
				             <tbody >  
					            <c:forEach var="record" items="${model.list}" varStatus="counter">   
					               <tr class="tableRow" height="20" >
					               <td id="recordUpdate_${record.cfirma}_${record.ccompn}_${record.cconta}" onClick="getRecord(this);" align="center" width="5%" class="tableCellFirst" style="cursor:pointer; border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;">
			               				<img src="resources/images/update.gif" border="0" alt="edit">
					               </td>
					               <td width="15%" class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 1px;border-color:#FAEBD7;"><font class="text12">&nbsp;${record.cconta}&nbsp;</font></td>
					               <td width="15%" class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 1px;border-color:#FAEBD7;"><font class="text12">&nbsp;${record.ctype}&nbsp;</font></td>
					               <td width="10%" class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.cphone}&nbsp;</font></td>
					               <td width="10%" class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.cmobil}&nbsp;</font></td>
					               <td width="40%" class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.cemail}&nbsp;</font></td>
			                       <td align="center" width="5%" class="tableCell" style="cursor:pointer; border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;">
			               				<a onclick="javascript:return confirm('Er du sikker på at du vil slette denne?')" tabindex=-1 href="mainmaintenancecundf_kontaktpersoner_edit.do?action=doDelete&cfirma=${record.cfirma}&ccompn=${record.ccompn}&cconta=${record.cconta}&ctype=${record.ctype}">
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
							<form action="mainmaintenancecundf_kontaktpersoner_edit.do" name="formRecord" id="formRecord" method="POST" >
								<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
								<input type="hidden" name="updateId" id="updateId" value="${model.updateId}"> 
								<input type="hidden" name="ccontaorg" id="ccontaorg" value="${model.record.ccontaorg}"> 
								<input type="hidden" name="action" id="action" value="doUpdate">
								<table id="kontakpersonerDetails" width="100%" cellspacing="0" border="0" align="left">
									<%-- Validation errors --%>
									<spring:hasBindErrors name="record"> <%-- name must equal the command object name in the Controller --%>
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
									
									<%-- Other errors (none validation errors) --%>
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
								
									<tr >
										<td><button name="newRecordButton" id="newRecordButton" class="inputFormSubmitStd" type="button" >Lage ny</button></td>
									</tr>
									<tr height="2">
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td>
											<table class="formFrameHeaderPeachWithBorder" width="100%" 	cellspacing="0" border="0" align="left">
												<tr>
													<td class="text12Bold">
													&nbsp;Kontaktinfo 
													</td>
												</tr>
											</table>			
											<table class="formFramePeachGrayRoundBottom"  width="100%" cellspacing="0" border="0" align="center">
												<tr> 
													<td width="50%" >
														<table>
															<tr>
																<td class="text12" title="cconta">&nbsp;<font class="text14RedBold" >*</font>Kontaktperson:</td>
																<td><input type="text" required oninvalid="this.setCustomValidity('Obligatoriskt')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="cconta" id="cconta" size="35" maxlength="30" value='${model.record.cconta}'></td>
																<td class="text12" title="cphone">&nbsp;Telefon:</td>
																<td><input type="text" class="inputTextMediumBlue" name="cphone" id="cphone" size="15" maxlength="15" value='${model.record.cphone}'></td>
															</tr>
															<tr>
																<td class="text12" title="ctype">&nbsp;Funksjon:</td>
																<td><input type="text" class="inputTextMediumBlue" name="ctype" id="ctype" size="35" maxlength="30" value='${model.record.ctype}'>
																	<a tabindex="-1" id="ctypeIdLink">
																		<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="Søk" >
																	</a>
																</td>
																<td class="text12" title="cmobil">&nbsp;Mobil:</td>
																<td><input type="text" class="inputTextMediumBlue" name="cmobil" id="cmobil" size="15" maxlength="15" value='${model.record.cmobil}'></td>
															</tr>
															<tr>
																<td class="text12" title="cmerge">&nbsp;Slå sammen PDF:</td>
																<td>
																	<input type="text" class="inputTextMediumBlue" name="cmerge" id="cmerge" size="1" maxlength="1" value='${model.record.cmerge}'>
																 	<font class="text12" title="cprint">&nbsp;Print også ?:</font> 
																 	<input type="text" class="inputTextMediumBlue" name="cprint" id="cprint" size="1" maxlength="1" value='${model.record.cprint}'>
																 	<font class="text12" title="clive">&nbsp;Vedlegg (V):</font> 
																 	<input type="text" class="inputTextMediumBlue" name="clive" id="clive" size="1" maxlength="1" value='${model.record.clive}'>
																</td>
															</tr>
														</table>
													</td>
													<td width="50%" valign="top">
														<table>
															<tr>
																<td colspan="2" class="text12" title="cemail">&nbsp;E-mail:</td>
																<td><input type="text" class="inputTextMediumBlue" name="cemail" id="cemail" size="70" maxlength="70" value='${model.record.cemail}'></td>
															<tr>
																<td colspan="2" class="text12" title="cemne">&nbsp;E-mail, emne:</td>
																<td><input type="text" class="inputTextMediumBlue" name="cemne" id="cemne" size="70" maxlength="80" value='${model.record.cemne}'></td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</td>
									</tr>

									<tr>
										<td>&nbsp;
											<table class="formFrameHeaderPeachWithBorder" width="100%" 	cellspacing="0" border="0" align="left">
												<tr>
													<td class="text12Bold">
													&nbsp;Avvikende vedleggsliste på kundenivå
													</td>
												</tr>
											</table>			
											<table class="formFramePeachGrayRoundBottom"  width="100%" cellspacing="0" border="0" align="center">
												<tr> 
													<td width="50%" >
														<table>
															<tr>
																<td class="text12">&nbsp;Faste koder:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
																<td><input type="text" class="inputTextMediumBlue" name="arkvedk1" id="arkvedk1" size="2" maxlength="2" value='${model.record.arkvedk1}'></td>
																<td><input type="text" class="inputTextMediumBlue" name="arkvedk1" id="arkvedk1" size="2" maxlength="2" value='${model.record.arkvedk1}'></td>
																<td><input type="text" class="inputTextMediumBlue" name="arkvedk1" id="arkvedk1" size="2" maxlength="2" value='${model.record.arkvedk1}'></td>
																<td><input type="text" class="inputTextMediumBlue" name="arkvedk1" id="arkvedk1" size="2" maxlength="2" value='${model.record.arkvedk1}'></td>
																<td><input type="text" class="inputTextMediumBlue" name="arkvedk1" id="arkvedk1" size="2" maxlength="2" value='${model.record.arkvedk1}'></td>
																<td><input type="text" class="inputTextMediumBlue" name="arkvedk1" id="arkvedk1" size="2" maxlength="2" value='${model.record.arkvedk1}'></td>
																<td><input type="text" class="inputTextMediumBlue" name="arkvedk1" id="arkvedk1" size="2" maxlength="2" value='${model.record.arkvedk1}'></td>
																<td><input type="text" class="inputTextMediumBlue" name="arkvedk1" id="arkvedk1" size="2" maxlength="2" value='${model.record.arkvedk1}'></td>
																<td><input type="text" class="inputTextMediumBlue" name="arkvedk1" id="arkvedk1" size="2" maxlength="2" value='${model.record.arkvedk1}'></td>
																<td><input type="text" class="inputTextMediumBlue" name="arkvedk1" id="arkvedk1" size="2" maxlength="2" value='${model.record.arkvedk1}'></td>
																<td><input type="text" class="inputTextMediumBlue" name="arkvedk1" id="arkvedk1" size="2" maxlength="2" value='${model.record.arkvedk1}'></td>
																<td><input type="text" class="inputTextMediumBlue" name="arkvedk1" id="arkvedk1" size="2" maxlength="2" value='${model.record.arkvedk1}'></td>
																<td><input type="text" class="inputTextMediumBlue" name="arkvedk1" id="arkvedk1" size="2" maxlength="2" value='${model.record.arkvedk1}'></td>
																<td><input type="text" class="inputTextMediumBlue" name="arkvedk1" id="arkvedk1" size="2" maxlength="2" value='${model.record.arkvedk1}'></td>
																<td><input type="text" class="inputTextMediumBlue" name="arkvedk1" id="arkvedk1" size="2" maxlength="2" value='${model.record.arkvedk1}'></td>
															</tr>
															<tr>
																<td class="text12">&nbsp;Faste koder:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
																<td><input type="text" class="inputTextMediumBlue" name="arkvedk1" id="arkvedk1" size="2" maxlength="2" value='${model.record.arkvedk1}'></td>
																<td><input type="text" class="inputTextMediumBlue" name="arkvedk1" id="arkvedk1" size="2" maxlength="2" value='${model.record.arkvedk1}'></td>
																<td><input type="text" class="inputTextMediumBlue" name="arkvedk1" id="arkvedk1" size="2" maxlength="2" value='${model.record.arkvedk1}'></td>
																<td><input type="text" class="inputTextMediumBlue" name="arkvedk1" id="arkvedk1" size="2" maxlength="2" value='${model.record.arkvedk1}'></td>
																<td><input type="text" class="inputTextMediumBlue" name="arkvedk1" id="arkvedk1" size="2" maxlength="2" value='${model.record.arkvedk1}'></td>
																<td><input type="text" class="inputTextMediumBlue" name="arkvedk1" id="arkvedk1" size="2" maxlength="2" value='${model.record.arkvedk1}'></td>
																<td><input type="text" class="inputTextMediumBlue" name="arkvedk1" id="arkvedk1" size="2" maxlength="2" value='${model.record.arkvedk1}'></td>
																<td><input type="text" class="inputTextMediumBlue" name="arkvedk1" id="arkvedk1" size="2" maxlength="2" value='${model.record.arkvedk1}'></td>
																<td><input type="text" class="inputTextMediumBlue" name="arkvedk1" id="arkvedk1" size="2" maxlength="2" value='${model.record.arkvedk1}'></td>
																<td><input type="text" class="inputTextMediumBlue" name="arkvedk1" id="arkvedk1" size="2" maxlength="2" value='${model.record.arkvedk1}'></td>
																<td><input type="text" class="inputTextMediumBlue" name="arkvedk1" id="arkvedk1" size="2" maxlength="2" value='${model.record.arkvedk1}'></td>
																<td><input type="text" class="inputTextMediumBlue" name="arkvedk1" id="arkvedk1" size="2" maxlength="2" value='${model.record.arkvedk1}'></td>
																<td><input type="text" class="inputTextMediumBlue" name="arkvedk1" id="arkvedk1" size="2" maxlength="2" value='${model.record.arkvedk1}'></td>
																<td><input type="text" class="inputTextMediumBlue" name="arkvedk1" id="arkvedk1" size="2" maxlength="2" value='${model.record.arkvedk1}'></td>
																<td><input type="text" class="inputTextMediumBlue" name="arkvedk1" id="arkvedk1" size="2" maxlength="2" value='${model.record.arkvedk1}'></td>

															</tr>
														</table>
													</td>
												</tr>
											</table>
										</td>
									</tr>

									<tr>
										<td>&nbsp;
											<table class="formFrameHeaderPeachWithBorder" width="100%" 	cellspacing="0" border="0" align="left">
												<tr>
													<td class="text12Bold">
													&nbsp;Inc/Omit
													</td>
												</tr>
											</table>			
											<table class="formFramePeachGrayRoundBottom"  width="100%" cellspacing="0" border="0" align="center">
												<tr> 
													<td width="20%" >
														<table>
															<tr>
																<td class="text12" title="cavdio">&nbsp;Inc/Omit:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
																<td><input type="text" class="inputTextMediumBlue" name="cavdio" id="cavdio" size="1" maxlength="1" value='${model.record.cavdio}'></td>
																<td class="text12" title="cavd">&nbsp;Avdeling:</td>
															</tr>
															<tr>
																<td colspan="3" class="text12" title="cavd" height="25">&nbsp;</td>
															</tr>
															<tr>
																<td class="text12" title="copdio">&nbsp;Inc/Omit:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
																<td><input type="text" class="inputTextMediumBlue" name="copdio" id="copdio" size="1" maxlength="1" value='${model.record.copdio}'></td>
																<td class="text12" title="copd">&nbsp;Ordertype:</td>
															</tr>
														</table>
													</td>
													<td width="80%" valign="top">
														<table>
															<tr>
																<td><input type="text" class="inputTextMediumBlue" name="cavd1" id="cavd1" size="4" maxlength="4" value='${model.record.cavd1}'></td>
																<td><input type="text" class="inputTextMediumBlue" name="cavd1" id="cavd1" size="4" maxlength="4" value='${model.record.cavd1}'></td>
																<td><input type="text" class="inputTextMediumBlue" name="cavd1" id="cavd1" size="4" maxlength="4" value='${model.record.cavd1}'></td>
																<td><input type="text" class="inputTextMediumBlue" name="cavd1" id="cavd1" size="4" maxlength="4" value='${model.record.cavd1}'></td>
																<td><input type="text" class="inputTextMediumBlue" name="cavd1" id="cavd1" size="4" maxlength="4" value='${model.record.cavd1}'></td>
																<td><input type="text" class="inputTextMediumBlue" name="cavd1" id="cavd1" size="4" maxlength="4" value='${model.record.cavd1}'></td>
																<td><input type="text" class="inputTextMediumBlue" name="cavd1" id="cavd1" size="4" maxlength="4" value='${model.record.cavd1}'></td>
																<td><input type="text" class="inputTextMediumBlue" name="cavd1" id="cavd1" size="4" maxlength="4" value='${model.record.cavd1}'></td>
																<td><input type="text" class="inputTextMediumBlue" name="cavd1" id="cavd1" size="4" maxlength="4" value='${model.record.cavd1}'></td>
																<td><input type="text" class="inputTextMediumBlue" name="cavd1" id="cavd1" size="4" maxlength="4" value='${model.record.cavd1}'></td>
															</tr>
															<tr>
																<td><input type="text" class="inputTextMediumBlue" name="cavd1" id="cavd1" size="4" maxlength="4" value='${model.record.cavd1}'></td>
																<td><input type="text" class="inputTextMediumBlue" name="cavd1" id="cavd1" size="4" maxlength="4" value='${model.record.cavd1}'></td>
																<td><input type="text" class="inputTextMediumBlue" name="cavd1" id="cavd1" size="4" maxlength="4" value='${model.record.cavd1}'></td>
																<td><input type="text" class="inputTextMediumBlue" name="cavd1" id="cavd1" size="4" maxlength="4" value='${model.record.cavd1}'></td>
																<td><input type="text" class="inputTextMediumBlue" name="cavd1" id="cavd1" size="4" maxlength="4" value='${model.record.cavd1}'></td>
																<td><input type="text" class="inputTextMediumBlue" name="cavd1" id="cavd1" size="4" maxlength="4" value='${model.record.cavd1}'></td>
																<td><input type="text" class="inputTextMediumBlue" name="cavd1" id="cavd1" size="4" maxlength="4" value='${model.record.cavd1}'></td>
																<td><input type="text" class="inputTextMediumBlue" name="cavd1" id="cavd1" size="4" maxlength="4" value='${model.record.cavd1}'></td>
																<td><input type="text" class="inputTextMediumBlue" name="cavd1" id="cavd1" size="4" maxlength="4" value='${model.record.cavd1}'></td>
																<td><input type="text" class="inputTextMediumBlue" name="cavd1" id="cavd1" size="4" maxlength="4" value='${model.record.cavd1}'></td>
															</tr>
															<tr>
																<td><input type="text" class="inputTextMediumBlue" name="copd1" id="copd1" size="2" maxlength="2" value='${model.record.copd1}'></td>
																<td><input type="text" class="inputTextMediumBlue" name="copd1" id="copd1" size="2" maxlength="2" value='${model.record.copd1}'></td>
																<td><input type="text" class="inputTextMediumBlue" name="copd1" id="copd1" size="2" maxlength="2" value='${model.record.copd1}'></td>
																<td><input type="text" class="inputTextMediumBlue" name="copd1" id="copd1" size="2" maxlength="2" value='${model.record.copd1}'></td>
																<td><input type="text" class="inputTextMediumBlue" name="copd1" id="copd1" size="2" maxlength="2" value='${model.record.copd1}'></td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</td>
									</tr>

									<tr><td>&nbsp;</td></tr>
									<tr> 
										<td align="right">
											<input class="inputFormSubmit" type="submit" name="submit" id="submit" value='Lagre'/>
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

