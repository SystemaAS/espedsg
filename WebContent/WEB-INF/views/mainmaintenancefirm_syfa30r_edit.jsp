<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerMainMaintenance.jsp" />
<!-- =====================end header ==========================-->
	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/mainmaintenancefirm_syfa30r_edit.js?ver=${user.versionEspedsg}"></SCRIPT>	
	
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
					<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a id="alinkMainMaintGate" tabindex=-1 style="display:block;" href="mainmaintenancegate.do">
						<font class="tabDisabledLink">&nbsp;Vedlikehold</font>
						<img style="vertical-align: middle;"  src="resources/images/list.gif" border="0" alt="general list">
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="15%" valign="bottom" class="tab" align="center">
						<font class="tabLink">&nbsp;Firma opplysninger</font>&nbsp;
						<font class="text11MediumBlue">${model.avdTODO}</font>
					</td>
					<td width="76%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
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
					<form action="mainmaintenanceavd_syfa28r_edit.do" name="formRecord" id="formRecord" method="POST" >
						<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
						<c:if test="${not empty Xmodel.record.kovavd}">
							<input type="hidden" name="updateId" id=updateId value="${model.updateId}">
						</c:if>
						<input type="hidden" name="action" id=action value="${model.action}">
						<input type="hidden" name="avd" id="avd" value="${model.avd}">
						<input type="hidden" name="kovavd" id="kovavd" value="${Xmodel.record.kovavd}">
						<input type="hidden" name="avdnavn" id=avdnavn value="${model.avdnavn}">
						
						<table width="99%" 	cellspacing="0" border="0" align="left">
							<tr>
								<td valign="top">
									<table width="100%" cellspacing="0" border="0" align="left">
										<tr >
											<td colspan="8" class="text14MediumBlue" >
											<span title="fifirm">Firmakode&nbsp;</span>&nbsp;&nbsp;<input tabindex=-1 readonly class="inputTextReadOnly" type="text" size="10" value="${model.record.fifirm}"/>
											&nbsp;&nbsp;&nbsp;
											<span  title="fift">Firmanavn&nbsp;</span>&nbsp;&nbsp;<input tabindex=-1 readonly class="inputTextReadOnly" type="text" size="40" value="${model.record.fift}"/>
											&nbsp;&nbsp;&nbsp;
											<span  title="fikrtn">Kortnavn&nbsp;</span>&nbsp;&nbsp;<input tabindex=-1 readonly class="inputTextReadOnly" type="text" size="30" value="${model.record.fikrtn}"/>
											
											</td>
										</tr>
										<tr height="3"><td >&nbsp;</td></tr>
											
									 	<tr>
									 		<td>
									 			<table class="tableBorderGrayWithRoundCorners3D" width="100%" cellspacing="0" border="0" align="left">
									 			<tr height="3"><td >&nbsp;</td></tr>
									 			<tr>
									 				<td  valign="top" width="25%">
									 					<table align="left" cellspacing="2" border="0" >
									 						<tr>
									 							<td class="text12"><img onMouseOver="showPop('filibf_info');" onMouseOut="hidePop('filibf_info');" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
																	<span title="filibf">Filbibliotek Produksjon</span>
																	<div class="text11" style="position: relative;" align="left">
																	<span style="position:absolute;top:2px" id="filibf_info" class="popupWithInputText text11"  >	
													           			todo
																	</span>
																	</div>
																</td>
																<td class="text12">
																	<input type="text" class="inputTextMediumBlue" name="filibf" id="filibf" size="11" maxlength="10" value="${model.record.filibf}">
																</td>
									 						</tr>
									 						<tr>
									 							<td class="text12"><img onMouseOver="showPop('filibo_info');" onMouseOut="hidePop('filibo_info');" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
																	<span title="filibo">Filbibliotek Historikk</span>
																	<div class="text11" style="position: relative;" align="left">
																	<span style="position:absolute;top:2px" id="filibo_info" class="popupWithInputText text11"  >	
													           			todo
																	</span>
																	</div>
																</td>
																<td class="text12">
																	<input type="text" class="inputTextMediumBlue" name="filibo" id="filibo" size="11" maxlength="10" value="${model.record.filibo}">
																</td>
									 						</tr>
									 						<tr>
									 							<td class="text12"><img onMouseOver="showPop('fikdul_info');" onMouseOut="hidePop('fikdul_info');" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
																	<span title="fikdul">Utleggskontr</span>
																	<div class="text11" style="position: relative;" align="left">
																	<span style="position:absolute;top:2px" id="fikdul_info" class="popupWithInputText text11"  >	
													           			(Ja/Nej) - Alle gebyrkoder med kundenr reskontroføres
																	</span>
																	</div>
																</td>
																<td class="text12">
																	<select name="fikdul" id="fikdul" class="inputTextMediumBlue">
								 					  					<option value="">-velg-</option>
								 					  					<option value="J"<c:if test="${model.record.fikdul == 'J'}"> selected </c:if> >Ja</option>
													  					<option value="N"<c:if test="${model.record.fikdul == 'N'}"> selected </c:if> >Nei</option>
													  				</select>	
																</td>
									 						</tr>
									 						<tr>
									 							<td class="text12"><img onMouseOver="showPop('fistfn_info');" onMouseOut="hidePop('fistfn_info');" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
																	<span title="fistfn">Fritxtkode faktura</span>
																	<div class="text11" style="position: relative;" align="left">
																	<span style="position:absolute;top:2px" id="fistfn_info" class="popupWithInputText text11"  >	
													           			todo
																	</span>
																	</div>
																</td>
																<td class="text12">
																	<input type="text" class="inputTextMediumBlue" name="fistfn" id="fistfn" size="3" maxlength="2" value="${model.record.fistfn}">
																</td>
									 						
									 						</tr>
									 						<tr>
									 							<td class="text12">&nbsp;&nbsp;<img onMouseOver="showPop('fistfe_info');" onMouseOut="hidePop('fistfe_info');" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
																	<span title="fistfe">Engelsk</span>
																	<div class="text11" style="position: relative;" align="left">
																	<span style="position:absolute;top:2px" id="fistfe_info" class="popupWithInputText text11"  >	
													           			todo
																	</span>
																	</div>
																</td>
																<td class="text12">
																	<input type="text" class="inputTextMediumBlue" name="fistfe" id="fistfe" size="3" maxlength="2" value="${model.record.fistfe}">
																</td>
									 						
									 						</tr>
									 						<tr>
									 							<td class="text12">&nbsp;&nbsp;<img onMouseOver="showPop('fistft_info');" onMouseOut="hidePop('fistft_info');" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
																	<span title="fistft">Tysk</span>
																	<div class="text11" style="position: relative;" align="left">
																	<span style="position:absolute;top:2px" id="fistft_info" class="popupWithInputText text11"  >	
													           			todo
																	</span>
																	</div>
																</td>
																<td class="text12">
																	<input type="text" class="inputTextMediumBlue" name="fistft" id="fistft" size="3" maxlength="2" value="${model.record.fistft}">
																</td>
									 						
									 						</tr>
									 						
									 					</table>
									 					
									 				</td>
									 				<td valign="top" width="25%">
									 					<table align="left" cellspacing="2" border="0" >
									 						<tr>
									 							<td class="text12"><img onMouseOver="showPop('fiups_info');" onMouseOut="hidePop('fiups_info');" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
																	<span title="fiups">Utl.prov standard</span>
																	<div class="text11" style="position: relative;" align="left">
																	<span style="position:absolute;top:2px" id="fiups_info" class="popupWithInputText text11"  >	
													           			todo
																	</span>
																	</div>
																</td>
																<td class="text12">
																	<input type="text" class="inputTextMediumBlue" name="fiups" id="fiups" size="6" maxlength="5" value="${model.record.fiupsNO}">
																</td>
									 						</tr>
									 						
									 						<tr>
									 							<td class="text12"><img onMouseOver="showPop('todo_info');" onMouseOut="hidePop('todo_info');" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
																	<span title="todo">Fakturanr.</span>
																	<div class="text11" style="position: relative;" align="left">
																	<span style="position:absolute;top:2px" id="todo_info" class="popupWithInputText text11"  >	
													           			todo
																	</span>
																	</div>
																</td>
																<td class="text12">
																	<input type="text" class="inputTextMediumBlue" name="todo" id="todo" size="8" maxlength="7" value="${Xmodel.record.todo}TODO">
																</td>
									 						</tr>
									 						<tr>
									 							<td class="text12"><img onMouseOver="showPop('todo_info');" onMouseOut="hidePop('todo_info');" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
																	<span title="todo">KID=8</span>
																	<div class="text11" style="position: relative;" align="left">
																	<span style="position:absolute;top:2px" id="todo_info" class="popupWithInputText text11"  >	
													           			todo
																	</span>
																	</div>
																</td>
																<td class="text12">
																	<input type="text" class="inputTextMediumBlue" name="todo" id="todo" size="1" maxlength="1" value="${Xmodel.record.todo}TODO">
																</td>
									 						</tr>
									 						<tr>
									 							<td class="text12"><img onMouseOver="showPop('filand_info');" onMouseOut="hidePop('filand_info');" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
																	<span title="filand">Landkode</span>
																	<div class="text11" style="position: relative;" align="left">
																	<span style="position:absolute;top:2px" id="filand_info" class="popupWithInputText text11"  >	
													           			todo
																	</span>
																	</div>
																</td>
																<td class="text12">
																	<input type="text" class="inputTextMediumBlue" name="filand" id="filand" size="3" maxlength="2" value="${model.record.filand}">
																</td>
									 						</tr>
									 						<tr>
									 							<td class="text12"><img onMouseOver="showPop('fiupm_info');" onMouseOut="hidePop('fiupm_info');" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
																	<span title="fiupm">Min.bel.utl.pro</span>
																	<div class="text11" style="position: relative;" align="left">
																	<span style="position:absolute;top:2px" id="fiupm_info" class="popupWithInputText text11"  >	
													           			todo
																	</span>
																	</div>
																</td>
																<td class="text12">
																	<input type="text" class="inputTextMediumBlue" name="fiupm" id="fiupm" size="3" maxlength="2" value="${model.record.fiupm}">
																</td>
									 						</tr>
									 						<tr>
									 							<td class="text12"><img onMouseOver="showPop('ficurr_info');" onMouseOut="hidePop('ficurr_info');" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
																	<span title="ficurr">Valutakode</span>
																	<div class="text11" style="position: relative;" align="left">
																	<span style="position:absolute;top:2px" id="ficurr_info" class="popupWithInputText text11"  >	
													           			todo
																	</span>
																	</div>
																</td>
																<td class="text12">
																	<input type="text" class="inputTextMediumBlue" name="ficurr" id="ficurr" size="4" maxlength="3" value="${model.record.ficurr}">
																</td>
									 						</tr>
									 						
									 					</table>	
									 				</td>
									 				<td valign="top" width="25%">
									 					<table align="left" cellspacing="2" border="0" >
									 						<tr>
									 							<td class="text12"><img onMouseOver="showPop('fideci_info');" onMouseOut="hidePop('fideci_info');" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
																	<span title="fideci">Desimaler</span>
																	<div class="text11" style="position: relative;" align="left">
																	<span style="position:absolute;top:2px" id="fideci_info" class="popupWithInputText text11"  >	
													           			todo
																	</span>
																	</div>
																</td>
																<td class="text12">
																	<input type="text" class="inputTextMediumBlue" name="fideci" id="fideci" size="2" maxlength="1" value="${model.record.fideci}">
																</td>
									 						</tr>
									 						<tr>
									 							<td class="text12"><img onMouseOver="showPop('fidtfm_info');" onMouseOut="hidePop('fidtfm_info');" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
																	<span title="fidtfm">Datoformat</span>
																	<div class="text11" style="position: relative;" align="left">
																	<span style="position:absolute;top:2px" id="fidtfm_info" class="popupWithInputText text11"  >	
													           			todo
																	</span>
																	</div>
																</td>
																<td class="text12">
																	<input type="text" class="inputTextMediumBlue" name="fidtfm" id="fidtfm" size="2" maxlength="1" value="${model.record.fidtfm}">
																</td>
									 						</tr>
									 						
									 						<tr>
									 							<td class="text12"><img onMouseOver="showPop('fitax_info');" onMouseOut="hidePop('fitax_info');" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
																	<span title="fitax">Momssats i %</span>
																	<div class="text11" style="position: relative;" align="left">
																	<span style="position:absolute;top:2px" id="fitax_info" class="popupWithInputText text11"  >	
													           			todo
																	</span>
																	</div>
																</td>
																<td class="text12">
																	<input type="text" class="inputTextMediumBlue" name="fitax" id="fitax" size="6" maxlength="5" value="${model.record.fitaxNO}">
																</td>
									 						</tr>
									 						<tr>
									 							<td class="text12"><img onMouseOver="showPop('fitaxd_info');" onMouseOut="hidePop('fitaxd_info');" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
																	<span title="fitaxd">F.o.m. dato</span>
																	<div class="text11" style="position: relative;" align="left">
																	<span style="position:absolute;top:2px" id="fitaxd_info" class="popupWithInputText text11"  >	
													           			todo
																	</span>
																	</div>
																</td>
																<td class="text12">
																	<input type="text" class="inputTextMediumBlue" name="fitaxd" id="fitaxd" size="7" maxlength="6" value="${model.record.fitaxdNO}">
																</td>
									 						</tr>
									 						<tr>
									 							<td class="text12"><img onMouseOver="showPop('fitax2_info');" onMouseOut="hidePop('fitax2_info');" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
																	<span title="fitax2">Tidligere momssats i %</span>
																	<div class="text11" style="position: relative;" align="left">
																	<span style="position:absolute;top:2px" id="fitax2_info" class="popupWithInputText text11"  >	
													           			todo
																	</span>
																	</div>
																</td>
																<td class="text12">
																	<input type="text" class="inputTextMediumBlue" name="fitax2" id="fitax2" size="6" maxlength="5" value="${model.record.fitax2NO}">
																</td>
									 						</tr>
									 						<tr>
									 							<td class="text12"><img onMouseOver="showPop('fikdt_info');" onMouseOut="hidePop('fikdt_info');" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
																	<span title="fikdt">Benytter TVINN</span>
																	<div class="text11" style="position: relative;" align="left">
																	<span style="position:absolute;top:2px" id="fikdt_info" class="popupWithInputText text11"  >	
													           			todo
																	</span>
																	</div>
																</td>
																<td class="text12">
																	<select name="fikdt" id="fikdt" class="inputTextMediumBlue">
								 					  					<option value="">-velg-</option>
								 					  					<option value="1"<c:if test="${model.record.fikdt == '1'}"> selected </c:if> >Ja</option>
													  					<option value="0"<c:if test="${model.record.fikdt == '0'}"> selected </c:if> >Nei</option>
													  				</select>
																</td>
									 						</tr>
									 						</table>
														</td>
														
								 						<td valign="top" width="25%">
								 							<table align="left" cellspacing="2" border="0" >
								 							<tr>
									 							<td class="text12"><img onMouseOver="showPop('fiecon_info');" onMouseOut="hidePop('fiecon_info');" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
																	<span title="fiecon">Økonomisystem</span>
																	<div class="text11" style="position: relative;" align="left">
																	<span style="position:absolute;top:2px" id="fiecon_info" class="popupWithInputText text11"  >	
													           			todo
																	</span>
																	</div>
																</td>
																<td class="text12">
																	<input type="text" class="inputTextMediumBlue" name="fiecon" id="fiecon" size="2" maxlength="1" value="${model.record.fiecon}">
																</td>
									 						</tr>
									 						<tr>
									 							<td class="text12"><img onMouseOver="showPop('fiavte_info');" onMouseOut="hidePop('fiavte_info');" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
																	<span title="fiavte">Avtaleeier</span>
																	<div class="text11" style="position: relative;" align="left">
																	<span style="position:absolute;top:2px" id="fiavte_info" class="popupWithInputText text11"  >	
													           			todo
																	</span>
																	</div>
																</td>
																<td class="text12">
																	<input type="text" class="inputTextMediumBlue" name="fiavte" id="fiavte" size="2" maxlength="1" value="${model.record.fiavte}">
																</td>
									 						</tr>
									 						<tr>
									 							<td class="text12"><img onMouseOver="showPop('fivalk_info');" onMouseOut="hidePop('fivalk_info');" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
																	<span title="fivalk">Valutakurser vedlikehol.i</span>
																	<div class="text11" style="position: relative;" align="left">
																	<span style="position:absolute;top:2px" id="fivalk_info" class="popupWithInputText text11"  >	
													           			todo
																	</span>
																	</div>
																</td>
																<td class="text12">
																	<select name="fivalk" id="fivalk" class="inputTextMediumBlue">
								 					  					<option value="">-velg-</option>
								 					  					<option value="S"<c:if test="${model.record.fivalk == 'S'}"> selected </c:if> >Spedisjon</option>
													  					<option value="Ø"<c:if test="${model.record.fivalk == 'Ø'}"> selected </c:if> >Økonomi</option>
													  				</select>	
																</td>
									 						</tr>
									 						<tr>
									 							<td class="text12"><img onMouseOver="showPop('filfb_info');" onMouseOut="hidePop('filfb_info');" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
																	<span title="filfb">Fr.brev/TP, R28.Belast</span>
																	<div class="text11" style="position: relative;" align="left">
																	<span style="position:absolute;top:2px" id="filfb_info" class="popupWithInputText text11"  >	
													           			todo
																	</span>
																	</div>
																</td>
																<td class="text12">
																	<select name="filfb" id="filfb" class="inputTextMediumBlue">
								 					  					<option value="">-velg-</option>
								 					  					<option value="S"<c:if test="${model.record.filfb == 'S'}"> selected </c:if> >S</option>
													  					<option value="M"<c:if test="${model.record.filfb == 'M'}"> selected </c:if> >M</option>
													  				</select>	
																</td>
									 						</tr>
									 					</table>		
									 				</td>
									 			</tr>
									 			<tr height="12"><td></td></tr>
									 			<tr>
						 							<td class="text11" width="40%" colspan="2">
						 								<table width="90%" class="formFrameHeader" cellspacing="0" border="0" align="center">
															<tr>
																<td colspan="4" class="text12White" >&nbsp;Sendingsnr</td>
															</tr>							
														</table>
													</td>	
													
													<td class="text11" width="40%" colspan="2" >
						 								<table width="90%" class="formFrameHeader" cellspacing="0" border="0" align="left">
															<tr>
																<td colspan="4" class="text12White" >&nbsp;Suppl.info</td>
															</tr>							
														</table>
													</td>	
												</tr>
												<tr>	
													<td valign="top" class="text11" width="40%" colspan="2" >	
														<table width="90%" class="formFrame" cellspacing="0" border="0" align="center">
															<tr>
																<td>
																<table width="55%" cellspacing="0" border="0" >
																	<tr>
											 							<td class="text12"><img onMouseOver="showPop('fitpnr_info');" onMouseOut="hidePop('fitpnr_info');" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
																			<span title="fitpnr">Tollpass - tildel send.nr</span>
																			<div class="text11" style="position: relative;" align="left">
																			<span style="position:absolute;top:2px" id="fitpnr_info" class="popupWithInputText text11"  >	
															           			todo
																			</span>
																			</div>
																		</td>
																		<td class="text11">
																			<select name="fitpnr" id="fitpnr" class="inputTextMediumBlue">
										 					  					<option value="">-velg-</option>
										 					  					<option value="J"<c:if test="${model.record.fitpnr == 'J'}"> selected </c:if> >J</option>
															  					<option value="N"<c:if test="${model.record.fitpnr == 'N'}"> selected </c:if> >N</option>
															  					<option value="U"<c:if test="${model.record.fitpnr == 'U'}"> selected </c:if> >U</option>
															  				</select>	
																		</td>
											 						</tr>
											 						<tr>
											 							<td class="text12"><img onMouseOver="showPop('fifbnr_info');" onMouseOut="hidePop('fifbnr_info');" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
																			<span title="fifbnr">Fr.brev - tildel send.nr</span>
																			<div class="text11" style="position: relative;" align="left">
																			<span style="position:absolute;top:2px" id="fifbnr_info" class="popupWithInputText text11"  >	
															           			todo
																			</span>
																			</div>
																		</td>
																		<td class="text11">
																			<select name="fifbnr" id="fifbnr" class="inputTextMediumBlue">
										 					  					<option value="">-velg-</option>
										 					  					<option value="J"<c:if test="${model.record.fifbnr == 'J'}"> selected </c:if> >J</option>
															  					<option value="N"<c:if test="${model.record.fifbnr == 'N'}"> selected </c:if> >N</option>
															  					<option value="U"<c:if test="${model.record.fifbnr == 'U'}"> selected </c:if> >U</option>
															  				</select>	
																		</td>
											 						</tr>
																</table>
																</td>
															</tr>
															<tr height="10"><td></td></tr>
															
															<tr>
															 <td>
															 	<table align="left" cellspacing="1" border="0" >
															 		<tr>
																		<td class="text12">&nbsp;</td>
																		<td class="text12" title="fisnla/fiidla">La</td>
																		<td class="text12" title="fisnle/fiidle">Lever</td>
																		<td class="text12" title="firecn/fiidnr">Sist brukt</td>
																		<td class="text12" title="firecm/fiidmx">Max nr.</td>
																	</tr>
																	<tr>
																		<td class="text12">&nbsp;&nbsp;&nbsp;Sendingsnr.</td>
																		<td class="text12"><input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="fisnla" id="fisnla" size="3" maxlength="2" value="${model.record.fisnla}"/></td>
																		<td class="text12"><input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="fisnle" id="fisnle" size="6" maxlength="5" value="${model.record.fisnle}"/></td>
																		<td class="text12"><input onKeyPress="return numberKey(event)" align="right" type="text" class="inputTextMediumBlue" name="firecn" id="firecn" size="10" maxlength="9" value="${model.record.firecn}"/></td>
																		<td class="text12"><input onKeyPress="return numberKey(event)" align="right" type="text" class="inputTextMediumBlue" name="firecm" id="firecm" size="10" maxlength="9" value="${model.record.firecm}"/></td>
																	</tr>
																	<tr>
																		<td class="text12">&nbsp;&nbsp;&nbsp;Kolli-ID&nbsp;&nbsp;<b>3</b></td>
																		<td class="text12"><input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="fiidla" id="fiidla" size="3" maxlength="2" value="${model.record.fiidla}"/></td>
																		<td class="text12"><input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="fiidle" id="fiidle" size="6" maxlength="5" value="${model.record.fiidle}"/></td>
																		<td class="text12"><input onKeyPress="return numberKey(event)" align="right" type="text" class="inputTextMediumBlue" name="fiidnr" id="fiidnr" size="10" maxlength="9" value="${model.record.fiidnr}"/></td>
																		<td class="text12"><input onKeyPress="return numberKey(event)" align="right" type="text" class="inputTextMediumBlue" name="fiidmx" id="fiidmx" size="10" maxlength="9" value="${model.record.fiidmx}"/></td>
																	</tr>
															 	</table>
															 </td>
															</tr>
															<tr height="13"><td></td></tr>
														</table>
													</td>
													
													<td valign="top" class="text11" width="40%" colspan="2" >	
														<table width="90%" class="formFrame" cellspacing="0" border="0" align="left">
															<tr>
									 							<td class="text12"><img onMouseOver="showPop('fisadk_info');" onMouseOut="hidePop('fisadk_info');" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
																	<span title="fisadk">Sumposter (Fakturajour.)til hovedbok</span>
																	<div class="text11" style="position: relative;" align="left">
																	<span style="position:absolute;top:2px" id="fisadk_info" class="popupWithInputText text11"  >	
													           			<p>
													           			<ul>
													           				<li>Nei: Faktnr -> Bilagsnr</li>
													           				<li>Ja: Systemgen.Bil.nr</li>
													           			</ul>
													           			</p>
																	</span>
																	</div>
																</td>
																<td class="text12">
																	<select name="fisadk" id="fisadk" class="inputTextMediumBlue">
								 					  					<option value="">-velg-</option>
								 					  					<option value="J"<c:if test="${model.record.fisadk == 'J'}"> selected </c:if> >Ja</option>
													  					<option value="N"<c:if test="${model.record.fisadk == 'N'}"> selected </c:if> >Nei</option>
													  					
													  				</select>	
																</td>
									 						</tr>
									 						<tr>
									 							<td class="text12"><img onMouseOver="showPop('file41_info');" onMouseOut="hidePop('file41_info');" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
																	<span title="file41">Switche MVA-kode etter Kto-type.....</span>
																	<div class="text11" style="position: relative;" align="left">
																	<span style="position:absolute;top:2px" id="file41_info" class="popupWithInputText text11"  >	
													           			todo
																	</span>
																	</div>
																</td>
																<td class="text11">
																	<select name="file41" id="file41" class="inputTextMediumBlue">
								 					  					<option value="">-velg-</option>
								 					  					<option value="J"<c:if test="${model.record.file41 == 'J'}"> selected </c:if> >Ja</option>
													  					<option value="N"<c:if test="${model.record.file41 == 'N'}"> selected </c:if> >Nei</option>
													  					
													  				</select>	
																</td>
									 						</tr>
									 						<tr height="8"><td></td></tr>
															<tr>
									 							<td class="text11">&nbsp;</td>
									 							<td class="text12"><span title="file11">&nbsp;Fakt.</span></td>
																<td class="text12"><span title="file21">&nbsp;Kost.bilag</span></td>
									 						</tr>
									 						<tr>
									 							<td class="text12"><img onMouseOver="showPop('file11_info');" onMouseOut="hidePop('file11_info');" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
																	<span title="file11/file21">Automatisk overføring i nattrutine av:</span>
																	<div class="text11" style="position: relative;" align="left">
																	<span style="position:absolute;top:2px" id="file11_info" class="popupWithInputText text11"  >	
													           			todo
																	</span>
																	</div>
																</td>
																<td class="text11">
																	<select name="file11" id="file11" class="inputTextMediumBlue" >
								 					  					<option value="">-velg-</option>
								 					  					<option value="J"<c:if test="${model.record.file11 == 'J'}"> selected </c:if> >Ja</option>
													  					<option value="N"<c:if test="${model.record.file11 == 'N'}"> selected </c:if> >Nei</option>
													  					
													  				</select>	
																</td>
																<td class="text11">
																	<select name="file21" id="file21" class="inputTextMediumBlue">
								 					  					<option value="">-velg-</option>
								 					  					<option value="J"<c:if test="${model.record.file21 == 'J'}"> selected </c:if> >Ja</option>
													  					<option value="N"<c:if test="${model.record.file21 == 'N'}"> selected </c:if> >Nei</option>
													  					
													  				</select>	
																</td>
									 						</tr>
															<tr height="8"><td></td></tr>
															<tr>
									 							<td class="text12"><img onMouseOver="showPop('tillat_info');" onMouseOut="hidePop('tillat_info');" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
																	<span title="tillat">Tillate periodsplitt kostnadsbilag</span>
																	<div class="text11" style="position: relative;" align="left">
																	<span style="position:absolute;top:2px" id="tillat_info" class="popupWithInputText text11"  >	
													           			todo
																	</span>
																	</div>
																</td>
																<td class="text11">
																	<select name="tillat" id="tillat" class="inputTextMediumBlue">
								 					  					<option value="">-velg-</option>
								 					  					<option value="J"<c:if test="${model.record.tillat == 'J'}"> selected </c:if> >Ja</option>
													  					<option value="N"<c:if test="${model.record.tillat == 'N'}"> selected </c:if> >Nei</option>
													  					
													  				</select>	
																</td>
									 						</tr>
									 						<tr>
									 							<td class="text12">&nbsp;&nbsp;&nbsp;<img onMouseOver="showPop('interr_info');" onMouseOut="hidePop('interr_info');" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
																	<span title="interr">Interimskonto</span>
																	<div class="text11" style="position: relative;" align="left">
																	<span style="position:absolute;top:2px" id="interr_info" class="popupWithInputText text11"  >	
													           			todo
																	</span>
																	</div>
																</td>
																<td class="text11">
																	<select name="interr" id="interr" class="inputTextMediumBlue">
								 					  					<option value="">-velg-</option>
								 					  					<option value="J"<c:if test="${model.record.interr == 'J'}"> selected </c:if> >Ja</option>
													  					<option value="N"<c:if test="${model.record.interr == 'N'}"> selected </c:if> >Nei</option>
													  					
													  				</select>	
																</td>
									 						</tr>
															<tr height="5"><td></td></tr>
														</table>
													</td>
						 						</tr>
						 						
						 			
						 						<%-- Supplerende Del 2 --%>
						 						<tr height="8"><td></td></tr>
						 						<tr>
						 						<td class="text11" width="40%" colspan="2" >
						 								<table width="90%" class="formFrameHeader" cellspacing="0" border="0" align="center">
															<tr>
																<td colspan="4" class="text12White" >&nbsp;Suppl.info - 2</td>
															</tr>							
														</table>
													</td>	
												</tr>
						 						<tr>
						 						<td valign="top" class="text11" width="40%" colspan="2" >	
														<table width="90%" class="formFrame" cellspacing="0" border="0" align="center">
															<tr>
									 							<td class="text12"><img onMouseOver="showPop('innutl_info');" onMouseOut="hidePop('innutl_info');" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
																	<span title="innutl">Innlandsfraktbrev på utlandsfraktbrev</span>
																	<div class="text11" style="position: relative;" align="left">
																	<span style="position:absolute;top:2px" id="innutl_info" class="popupWithInputText text11"  >	
													           			todo
																	</span>
																	</div>
																</td>
																<td class="text11">
																	<select name="innutl" id="innutl" class="inputTextMediumBlue">
								 					  					<option value="">-velg-</option>
								 					  					<option value="J"<c:if test="${model.record.innutl == 'J'}"> selected </c:if> >Ja</option>
													  					<option value="N"<c:if test="${model.record.innutl == 'N'}"> selected </c:if> >Nei</option>
													  					
													  				</select>	
																</td>
									 						</tr>
															<tr>
									 							<td class="text12"><img onMouseOver="showPop('zipcod_info');" onMouseOut="hidePop('zipcod_info');" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
																	<span title="zipcod">Benyttes ZIP-kode i stedsregister</span>
																	<div class="text11" style="position: relative;" align="left">
																	<span style="position:absolute;top:2px" id="zipcod_info" class="popupWithInputText text11"  >	
													           			
																	</span>
																	</div>
																</td>
																<td class="text11">
																	<select name="zipcod" id="zipcod" class="inputTextMediumBlue">
								 					  					<option value="">-velg-</option>
								 					  					<option value="J"<c:if test="${model.record.zipcod == 'J'}"> selected </c:if> >Ja</option>
													  					<option value="N"<c:if test="${model.record.zipcod == 'N'}"> selected </c:if> >Nei</option>
													  					
													  				</select>	
																</td>
									 						</tr>
									 						<tr height="8"><td></td></tr>
									 						<tr>
									 							<td class="text12">&nbsp;</td>
									 							<td class="text12"><span title="fikufr">&nbsp;Fra</span></td>
																<td class="text12"><span title="fikuti">&nbsp;Till</span></td>
									 						</tr>
									 						<tr>
									 							<td class="text12"><img onMouseOver="showPop('fikufr_info');" onMouseOut="hidePop('fikufr_info');" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
																	<span title="fikufr/fikuti">Adressekunder kundenummer</span>
																	<div class="text11" style="position: relative;" align="left">
																	<span style="position:absolute;top:2px" id="fikufr_info" class="popupWithInputText text11"  >	
													           			todo
																	</span>
																	</div>
																</td>
																<td class="text12">
																	<input onKeyPress="return numberKey(event)" align="right" type="text" class="inputTextMediumBlue" name="fikufr" id="fikufr" size="10" maxlength="8" value="${model.record.fikufr}"/>
																</td>
																<td class="text12">
																	<input onKeyPress="return numberKey(event)" align="right" type="text" class="inputTextMediumBlue" name="fikuti" id="fikuti" size="10" maxlength="8" value="${model.record.fikuti}"/>
																</td>
									 						</tr>
									 						<tr>
									 							<td class="text12"><img onMouseOver="showPop('fikune_info');" onMouseOut="hidePop('fikune_info');" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
																	<span title="fikune">Adressekunder neste nummer for tildeling</span>
																	<div class="text11" style="position: relative;" align="left">
																	<span style="position:absolute;top:2px" id="fikune_info" class="popupWithInputText text11"  >	
													           			todo
																	</span>
																	</div>
																</td>
																<td class="text12">
																	<input onKeyPress="return numberKey(event)" align="right" type="text" class="inputTextMediumBlue" name="fikune" id="fikune" size="10" maxlength="8" value="${model.record.fikune}"/>	
																</td>
													
									 						</tr>
														
															<tr height="5"><td></td></tr>
														</table>
													</td>
						 						</tr>
						 						
						 						
						 						
						 						<tr height="10"><td></td></tr>
									 			<tr>
													<td colspan="8">
														<table class="formFrameHeader" width="95%" 	cellspacing="0" border="0" align="center">
															<tr><td colspan="15" class="text12White" >&nbsp;Avkryss benyttede systemer</td></tr>
														</table>
														<table class="formFrame"  width="95%" cellspacing="1" border="0" align="center">
																<tr>
																	<td class="text11" align="left"><span title="fiuli">&nbsp;Land-import</span></td>
																	<td class="text11" align="left"><span title="fiule">&nbsp;Land-eksport</span></td>
																	<td class="text11" align="left"><span title="fiurfi">&nbsp;Fly-import</span></td>
																	<td class="text11" align="left"><span title="fiurfe">&nbsp;Fly-eksport</span></td>
																	<td class="text11" align="left"><span title="firufl">&nbsp;Fly-innland</span></td>
																	<td class="text11" align="left"><span title="fitran">&nbsp;Tr.disp</span></td>
																	<td class="text11" align="left"><span title="fiursi">&nbsp;SAD-import</span></td>
																	<td class="text11" align="left"><span title="fiurse">&nbsp;SAD-eksport</span></td>
																	<td class="text11" align="left"><span title="fiurbi">&nbsp;Båt-import</span></td>
																	<td class="text11" align="left"><span title="fiurbe">&nbsp;Båt-eksport</span></td>
																	<td class="text11" align="left"><span title="fiurbl">&nbsp;Track&/Tracing</span></td>
																	<td class="text11" align="left"><span title="fitdvi">&nbsp;TdV IS</span></td>
																</tr>		
										 	    				<tr>
																	<td class="text12" >
																		<select name="fiurli" id="fiurli" class="inputTextMediumBlue">
														  					<option value="">-velg-</option>
														  					<option value="J"<c:if test="${ model.record.fiurli == 'J'}"> selected </c:if> >Ja</option>
														  					<option value="N"<c:if test="${ model.record.fiurli == 'N'}"> selected </c:if> >Nei</option>
														  					
																	  	</select>
																	</td>
																	<td class="text12" >
																		<select name="fiurle" id="fiurle" class="inputTextMediumBlue">
														  					<option value="">-velg-</option>
														  					<option value="J"<c:if test="${ model.record.fiurle == 'J'}"> selected </c:if> >Ja</option>
														  					<option value="N"<c:if test="${ model.record.fiurle == 'N'}"> selected </c:if> >Nei</option>
														  					
																	  	</select>
																	</td>
																	<td class="text12" >
																		<select name="fiurfi" id="fiurfi" class="inputTextMediumBlue">
														  					<option value="">-velg-</option>
														  					<option value="J"<c:if test="${ model.record.fiurfi == 'J'}"> selected </c:if> >Ja</option>
														  					<option value="N"<c:if test="${ model.record.fiurfi == 'N'}"> selected </c:if> >Nei</option>
														  					
																	  	</select>
																	</td>
																	<td class="text12" >
																		<select name="fiurfe" id="fiurfe" class="inputTextMediumBlue">
														  					<option value="">-velg-</option>
														  					<option value="J"<c:if test="${ model.record.fiurfe == 'J'}"> selected </c:if> >Ja</option>
														  					<option value="N"<c:if test="${ model.record.fiurfe == 'N'}"> selected </c:if> >Nei</option>
														  					
																	  	</select>
																	</td>
																	<td class="text12" >
																		<select name="fiurfl" id="fiurfl" class="inputTextMediumBlue">
														  					<option value="">-velg-</option>
														  					<option value="J"<c:if test="${ model.record.fiurfl == 'J'}"> selected </c:if> >Ja</option>
														  					<option value="N"<c:if test="${ model.record.fiurfl == 'N'}"> selected </c:if> >Nei</option>
														  					
																	  	</select>
																	</td>
																	<td class="text12" >
																		<select name="fitran" id="fitran" class="inputTextMediumBlue">
														  					<option value="">-velg-</option>
														  					<option value="J"<c:if test="${ model.record.fitran == 'J'}"> selected </c:if> >Ja</option>
														  					<option value="N"<c:if test="${ model.record.fitran == 'N'}"> selected </c:if> >Nei</option>
														  					
																	  	</select>
																	</td>
																	<td class="text12" >
																		<select name="fiursi" id="fiursi" class="inputTextMediumBlue">
														  					<option value="">-velg-</option>
														  					<option value="J"<c:if test="${ model.record.fiursi == 'J'}"> selected </c:if> >Ja</option>
														  					<option value="N"<c:if test="${ model.record.fiursi == 'N'}"> selected </c:if> >Nei</option>
														  					
																	  	</select>
																	</td>
																	<td class="text12" >
																		<select name="fiurse" id="fiurse" class="inputTextMediumBlue">
														  					<option value="">-velg-</option>
														  					<option value="J"<c:if test="${ model.record.fiurse == 'J'}"> selected </c:if> >Ja</option>
														  					<option value="N"<c:if test="${ model.record.fiurse == 'N'}"> selected </c:if> >Nei</option>
														  					
																	  	</select>
																	</td>
																	<td class="text12" >
																		<select name="fiurbi" id="fiurbi" class="inputTextMediumBlue">
														  					<option value="">-velg-</option>
														  					<option value="J"<c:if test="${ model.record.fiurbi == 'J'}"> selected </c:if> >Ja</option>
														  					<option value="N"<c:if test="${ model.record.fiurbi == 'N'}"> selected </c:if> >Nei</option>
														  					
																	  	</select>
																	</td>
																	<td class="text12" >
																		<select name="fiurbe" id="fiurbe" class="inputTextMediumBlue">
														  					<option value="">-velg-</option>
														  					<option value="J"<c:if test="${ model.record.fiurbe == 'J'}"> selected </c:if> >Ja</option>
														  					<option value="N"<c:if test="${ model.record.fiurbe == 'N'}"> selected </c:if> >Nei</option>
																	  	</select>
																	</td>
																	<td class="text12" >
																		<select name="fiurbl" id="fiurbl" class="inputTextMediumBlue">
														  					<option value="">-velg-</option>
														  					<option value="J"<c:if test="${ model.record.fiurbl == 'J'}"> selected </c:if> >Ja</option>
														  					<option value="N"<c:if test="${ model.record.fiurbl == 'N'}"> selected </c:if> >Nei</option>
																	  	</select>
																	</td>
																	<td class="text12" >
																		<select name="fitdvi" id="fitdvi" class="inputTextMediumBlue">
														  					<option value="">-velg-</option>
														  					<option value="J"<c:if test="${ model.record.fitdvi == 'J'}"> selected </c:if> >Ja</option>
														  					<option value="N"<c:if test="${ model.record.fitdvi == 'N'}"> selected </c:if> >Nei</option>
																	  	</select>
																	</td>
												    	    	</tr>	
												    	    	<tr height="10"><td></td></tr>
												    	    	<tr>
																	<td colspan="12" class="text12" align="left">
																	<table>
																		<tr>
																			<td class="text12" align="left">
																			<span title="f">&nbsp;Skriv ut melding om manuell behandling på TVINN</span>
																			</td>
																			<td class="text12" >
																					<select name="file31" id="file31" class="inputTextMediumBlue">
																  					<option value="">-velg-</option>
																  					<option value="J"<c:if test="${ model.record.file31 == 'J'}"> selected </c:if> >Ja</option>
																  					<option value="N"<c:if test="${ model.record.file31 == 'N'}"> selected </c:if> >Nei</option>
																			  	</select>
																			</td>
																	</table>
																	</td>
																</tr>
																<tr height="5"><td></td></tr>
												    	    	
															</table>
														</td>
													</tr>
													
												<%-- bottom space towards frame bottom --%>	
												<tr height="10"><td></td></tr>
									 			</table>
									 		</td>
										</tr>
										
							<tr height="5"><td >&nbsp;</td></tr>
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

