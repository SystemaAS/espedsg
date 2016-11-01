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
						
						<table width="99%" 	cellspacing="1" border="0" align="left">
							<tr>
								<td valign="top">
									<table width="100%" cellspacing="1" border="0" align="left">
										<tr >
											<td colspan="8" class="text14MediumBlue" title=".">
											Firmakode&nbsp;&nbsp;&nbsp;<input readonly class="inputTextReadOnly" type="text" size="10" value="${model.record.fifirm}"/>
											&nbsp;&nbsp;&nbsp;
											Firmanavn&nbsp;&nbsp;&nbsp;<input readonly class="inputTextReadOnly" type="text" size="40" value="${model.record.fift}"/>
											</td>
										</tr>
										<tr height="5"><td >&nbsp;</td></tr>
											
									 	<tr>
									 		<td>
									 			<table class="tableBorderGrayWithRoundCorners3D" width="100%" cellspacing="1" border="0" align="left">
									 			<tr>
									 				<td  valign="top" width="32%">
									 					<table align="left" cellspacing="1" border="0" >
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
																	<span style="position:absolute;top:2px" id="kfilibo_info" class="popupWithInputText text11"  >	
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
																	<select name="fikdul" id="fikdul" >
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
									 							<td class="text12">&nbsp;&nbsp;<img onMouseOver="showPop('kfistft_info');" onMouseOut="hidePop('fistft_info');" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
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
																	<input type="text" class="inputTextMediumBlue" name="fiups" id="fiups" size="6" maxlength="5" value="${model.record.fiups}">
																</td>
									 						</tr>
									 						<tr>
									 							<td class="text12"><img onMouseOver="showPop('kovpro_info');" onMouseOut="hidePop('kovpro_info');" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
																	<span title="kovpro">Fakturanr.</span>
																	<div class="text11" style="position: relative;" align="left">
																	<span style="position:absolute;top:2px" id="kovpro_info" class="popupWithInputText text11"  >	
													           			todo
																	</span>
																	</div>
																</td>
																<td class="text12">
																	<input type="text" class="inputTextMediumBlue" name="todo" id="todo" size="8" maxlength="7" value="${Xmodel.record.todo}">
																</td>
									 						</tr>
									 						<tr>
									 							<td class="text12"><img onMouseOver="showPop('kovpro_info');" onMouseOut="hidePop('kovpro_info');" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
																	<span title="kovpro">KID=8</span>
																	<div class="text11" style="position: relative;" align="left">
																	<span style="position:absolute;top:2px" id="kovpro_info" class="popupWithInputText text11"  >	
													           			todo
																	</span>
																	</div>
																</td>
																<td class="text12">
																	<input type="text" class="inputTextMediumBlue" name="todo" id="todo" size="1" maxlength="1" value="${Xmodel.record.todo}">
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
									 					</table>
									 					
									 				</td>
									 				<td valign="top" width="32%">
									 					<table align="left" cellspacing="1" border="0" >
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
																	<input type="text" class="inputTextMediumBlue" name="fitax" id="fitax" size="6" maxlength="5" value="${model.record.fitax}">
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
																	<input type="text" class="inputTextMediumBlue" name="fitaxd" id="fitaxd" size="7" maxlength="6" value="${model.record.fitaxd}">
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
																	<input type="text" class="inputTextMediumBlue" name="fitax2" id="fitax2" size="6" maxlength="5" value="${model.record.fitax2}">
																</td>
									 						</tr>
									 						<tr>
									 							<td class="text12"><img onMouseOver="showPop('kovpro_info');" onMouseOut="hidePop('kovpro_info');" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
																	<span title="kovpro">Benytter TVINN</span>
																	<div class="text11" style="position: relative;" align="left">
																	<span style="position:absolute;top:2px" id="kovpro_info" class="popupWithInputText text11"  >	
													           			todo
																	</span>
																	</div>
																</td>
																<td class="text12">
																	<select name="todo" id="todo" >
								 					  					<option value="">-velg-</option>
								 					  					<option value="1"<c:if test="${Xmodel.record.todo == 'J'}"> selected </c:if> >Ja</option>
													  					<option value="0"<c:if test="${Xmodel.record.todo == 'N'}"> selected </c:if> >Nei</option>
													  				</select>
																</td>
									 						</tr>
									 						<tr>
									 							<td class="text12"><img onMouseOver="showPop('kovpro_info');" onMouseOut="hidePop('kovpro_info');" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
																	<span title="kovpro">Økonomisystem</span>
																	<div class="text11" style="position: relative;" align="left">
																	<span style="position:absolute;top:2px" id="kovpro_info" class="popupWithInputText text11"  >	
													           			todo
																	</span>
																	</div>
																</td>
																<td class="text12">
																	<input type="text" class="inputTextMediumBlue" name="todo" id="todo" size="2" maxlength="1" value="${Xmodel.record.todo}">
																</td>
									 						</tr>
									 						<tr>
									 							<td class="text12"><img onMouseOver="showPop('kovpro_info');" onMouseOut="hidePop('kovpro_info');" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
																	<span title="kovpro">Avtaleeier</span>
																	<div class="text11" style="position: relative;" align="left">
																	<span style="position:absolute;top:2px" id="kovpro_info" class="popupWithInputText text11"  >	
													           			todo
																	</span>
																	</div>
																</td>
																<td class="text12">
																	<input type="text" class="inputTextMediumBlue" name="todo" id="todo" size="2" maxlength="1" value="${Xmodel.record.todo}">
																</td>
									 						</tr>
									 					</table>	
									 				</td>
									 				<td valign="top" width="36%">
									 					<table align="left" cellspacing="1" border="0" >
									 						<tr>
									 							<td class="text12"><img onMouseOver="showPop('kovpro_info');" onMouseOut="hidePop('kovpro_info');" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
																	<span title="kovpro">Valutakurser vedlikeholdes i</span>
																	<div class="text11" style="position: relative;" align="left">
																	<span style="position:absolute;top:2px" id="kovpro_info" class="popupWithInputText text11"  >	
													           			todo
																	</span>
																	</div>
																</td>
																<td class="text12">
																	<select name="todo" id="todo" >
								 					  					<option value="">-velg-</option>
								 					  					<option value="S"<c:if test="${Xmodel.record.todo == 'S'}"> selected </c:if> >Spedisjon</option>
													  					<option value="0"<c:if test="${Xmodel.record.todo == '0'}"> selected </c:if> >Økonomi</option>
													  				</select>	
																</td>
									 						</tr>
									 						<tr>
									 							<td class="text12"><img onMouseOver="showPop('kovpro_info');" onMouseOut="hidePop('kovpro_info');" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
																	<span title="kovpro">Fr.brev/TP, R28.Belast</span>
																	<div class="text11" style="position: relative;" align="left">
																	<span style="position:absolute;top:2px" id="kovpro_info" class="popupWithInputText text11"  >	
													           			todo
																	</span>
																	</div>
																</td>
																<td class="text12">
																	<select name="todo" id="todo" >
								 					  					<option value="">-velg-</option>
								 					  					<option value="S"<c:if test="${Xmodel.record.todo == 'S'}"> selected </c:if> >S</option>
													  					<option value="M"<c:if test="${Xmodel.record.todo == 'M'}"> selected </c:if> >M</option>
													  					
													  				</select>	
																</td>
									 						</tr>
									 						<tr height="15"><td></td></tr>
									 						
									 						<tr>
									 							<td colspan="4" class="text11">
									 								<table class="formFrameHeaderPeachWithBorder" width="100%" cellspacing="1" border="0" align="left">
																		<tr>
																			<td colspan="4" class="text12" >&nbsp;Sendingsnr.
																			</td>
																		</tr>							
																	</table>
																	<table class="formFramePeachGrayRoundBottom" width="100%" cellspacing="1" border="0" align="left">
																		<tr>
												 							<td colspan="3" class="text11"><img onMouseOver="showPop('kovpro_info');" onMouseOut="hidePop('kovpro_info');" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
																				<span title="kovpro">Tollpass - tildel send.nr</span>
																				<div class="text11" style="position: relative;" align="left">
																				<span style="position:absolute;top:2px" id="kovpro_info" class="popupWithInputText text11"  >	
																           			todo
																				</span>
																				</div>
																			</td>
																			<td colspan="2" class="text11">
																				<select name="todo" id="todo" >
											 					  					<option value="">-velg-</option>
											 					  					<option value="J"<c:if test="${Xmodel.record.todo == 'J'}"> selected </c:if> >J</option>
																  					<option value="N"<c:if test="${Xmodel.record.todo == 'N'}"> selected </c:if> >N</option>
																  					<option value="U"<c:if test="${Xmodel.record.todo == 'U'}"> selected </c:if> >U</option>
																  				</select>	
																			</td>
												 						</tr>
												 						<tr>
												 							<td colspan="3" class="text11"><img onMouseOver="showPop('kovpro_info');" onMouseOut="hidePop('kovpro_info');" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
																				<span title="kovpro">Fr.brev - tildel send.nr</span>
																				<div class="text11" style="position: relative;" align="left">
																				<span style="position:absolute;top:2px" id="kovpro_info" class="popupWithInputText text11"  >	
																           			todo
																				</span>
																				</div>
																			</td>
																			<td colspan="2" class="text11">
																				<select name="todo" id="todo" >
											 					  					<option value="">-velg-</option>
											 					  					<option value="J"<c:if test="${Xmodel.record.todo == 'J'}"> selected </c:if> >J</option>
																  					<option value="N"<c:if test="${Xmodel.record.todo == 'N'}"> selected </c:if> >N</option>
																  					<option value="U"<c:if test="${Xmodel.record.todo == 'U'}"> selected </c:if> >U</option>
																  				</select>	
																			</td>
												 						</tr>
																		<tr height="10"><td></td></tr>
																		<tr>
																			<td class="text11">&nbsp;</td>
																			<td class="text11">La</td>
																			<td class="text11">Lever</td>
																			<td class="text11">Sist brukt</td>
																			<td class="text11">Max nr.</td>
																		</tr>
																		<tr>
																			<td class="text11">Sendingsnr.</td>
																			<td class="text11"><input type="text" class="inputTextMediumBlue" name="todo" id="todo" size="3" maxlength="2" value="${Xmodel.record.todo}"/></td>
																			<td class="text11"><input type="text" class="inputTextMediumBlue" name="todo" id="todo" size="6" maxlength="5" value="${Xmodel.record.todo}"/></td>
																			<td class="text11"><input type="text" class="inputTextMediumBlue" name="todo" id="todo" size="10" maxlength="9" value="${Xmodel.record.todo}"/></td>
																			<td class="text11"><input type="text" class="inputTextMediumBlue" name="todo" id="todo" size="10" maxlength="9" value="${Xmodel.record.todo}"/></td>
																		</tr>
																		<tr>
																			<td class="text11">Kolli-ID&nbsp;${Xmodel.record.todo}</td>
																			<td class="text11"><input type="text" class="inputTextMediumBlue" name="todo" id="todo" size="3" maxlength="2" value="${Xmodel.record.todo}"/></td>
																			<td class="text11"><input type="text" class="inputTextMediumBlue" name="todo" id="todo" size="6" maxlength="5" value="${Xmodel.record.todo}"/></td>
																			<td class="text11"><input type="text" class="inputTextMediumBlue" name="todo" id="todo" size="10" maxlength="9" value="${Xmodel.record.todo}"/></td>
																			<td class="text11"><input type="text" class="inputTextMediumBlue" name="todo" id="todo" size="10" maxlength="9" value="${Xmodel.record.todo}"/></td>
																		</tr>
																	</table>
																</td>
									 						</tr>
									 						<tr height="10"><td></td></tr>
									 						
									 						
									 						
									 					</table>		
									 				</td>
									 				
									 			</tr>
									 			</table>
									 		</td>
									 	

										</tr>
										
										
										<%--	
										<tr>
											<td class="text12" ><img onMouseOver="showPop('kovxxx0_info');" onMouseOut="hidePop('kovxxx0_info');" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">										
												<span title="kovxxx0">Fraktbrev/TP, R28.Belast</span>
												<div class="text11" style="position: relative;" align="left">
												<span style="position:absolute;top:2px" id="kovxxx0_info" class="popupWithInputText text11"  >	
								           			<p>Tast S eller M hvis du vil at det på fraktbrev og tollpass skal være ferdigutfylt (som forslag) i</p>
													<p>R.28 at Selger evt Mottaker skal belastes frakt og omkostninger. Ved BLANK kommer inget forslag og saksbehandler MÅ velge manuelt. A=Andre er ikke tillatt som standardverdi.</p>
												</span>
												</div>
											</td>
											<td class="text12">
												<select name="kovxxx0" id="kovxxx0" >
			 					  					<option value="">-velg-</option>
			 					  					<option value="S"<c:if test="${ fn:substring(Xmodel.record.kovxxx, 0, 1) == 'S'}"> selected </c:if> >S</option>
								  					<option value="M"<c:if test="${ fn:substring(Xmodel.record.kovxxx, 0, 1) == 'M'}"> selected </c:if> >M</option>
								  				</select>	
											</td>
											<td width="100px" class="text12" >&nbsp;</td>
											<td class="text12" ><img onMouseOver="showPop('kovxxx5_info');" onMouseOut="hidePop('kovxxx5_info');" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
												<span title="kovxxx5/kovxxx1">Fritekstkode R38 FB/TP</span>
												<div class="text11" style="position: relative;" align="left">
												<span style="position:absolute;top:2px" id="kovxxx5_info" class="popupWithInputText text11"  >	
								           			<p>Her kan tastes en kode for fritekst som automatisk skal hentes og skrives i rub.38 hvis tollpass.
														Det vanlige vil være f.eks en ekstra påminning om at godset ikke må tas i bruk og gjerne også
														navn/adresse som ruckattest skal sendes til.</p>
													<p>Første felt gjelder fraktbrev, andre felt gjelder tollpass</p>
												</span>
												</div>
											
											</td>
											<td class="text12" >
												<input type="text" class="inputTextMediumBlue" name="kovxxx5" id="kovxxx5" size="3" maxlength="2" value="${fn:substring(Xmodel.record.kovxxx, 5, 7)}">
												/
												<input type="text" class="inputTextMediumBlue" name="kovxxx1" id="kovxxx1" size="3" maxlength="2" value="${fn:substring(Xmodel.record.kovxxx, 1, 3)}">	
											</td>

										</tr>	
										<tr>
											<td class="text12" ><img onMouseOver="showPop('kovxxx3_info');" onMouseOut="hidePop('kovxxx3_info');" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
												<span title="kovxxx3">Skriv fraktbrev ved EDI</span>
												<div class="text11" style="position: relative;" align="left">
												<span style="position:absolute;top:2px" id="kovxxx3_info" class="popupWithInputText text11"  >	
								           			<p>Står det blank her (eller N) gjelder programpakkens standard:
														VED EDI SKRIVES DET IKKE UT NOE PÅ PRINTER (men merkelapper  kommer selvsagt hvis en har bedt om det). </p>                  
										            <p>"J" vil gi utskrift som når en ikke sender EDI.              
														"1" er en spesialverdi som kan være aktuell (når en benytter laser) SKRIV ut, men kun 1 eksemplar.</p>
												</span>
												</div>
											</td>
											<td class="text12"> 
												<select name="kovxxx3" id="kovxxx3" >
			 					  					<option value="">-velg-</option>
			 					  					<option value="J"<c:if test="${ fn:substring(Xmodel.record.kovxxx, 3, 4) == 'J'}"> selected </c:if> >Ja</option>
								  					<option value="N"<c:if test="${ fn:substring(Xmodel.record.kovxxx, 3, 4) == 'N'}"> selected </c:if> >Nei</option>
								  				</select>	
											</td>
											<td width="100px" class="text12" >&nbsp;</td>
											<td class="text12" ><img onMouseOver="showPop('kovxxx4_info');" onMouseOut="hidePop('kovxxx4_info');" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
												<span title="kovxxx4">Skriv Tollpass ved EDI</span>
												<div class="text11" style="position: relative;" align="left">
												<span style="position:absolute;top:2px" id="kovxxx4_info" class="popupWithInputText text11"  >	
								           			<p>...</p>                  										        
												</span>
												</div>													
											</td>
											<td class="text12" >
												<select name="kovxxx4" id="kovxxx4" >
			 					  					<option value="">-velg-</option>
			 					  					<option value="J"<c:if test="${ fn:substring(Xmodel.record.kovxxx, 4, 5) == 'J'}"> selected </c:if> >Ja</option>
								  					<option value="N"<c:if test="${ fn:substring(Xmodel.record.kovxxx, 4, 5) == 'N'}"> selected </c:if> >Nei</option>
								  				</select>	
											</td>
										</tr>	
										<tr>
											<td class="text12" title="kowxxx1">Godslinjer på faktura</td>
											<td class="text12" >
												<input type="text" class="inputTextMediumBlue" name="kowxxx1" id="kowxxx1" size="3" maxlength="1" value='${ fn:substring(Xmodel.record.kowxxx, 1, 2) }' />
											</td>	
											<td width="100px" class="text12" >&nbsp;</td>
											<td class="text12" title="kowxxx2">Std Oppd.type (TR.modul)</td>
											<td class="text12" >
												<select name="kowxxx2" id="kowxxx2" class="inputTextMediumBlue">
							  					<option value="">-velg-</option>
							  					<c:forEach var="record" items="${model.oppTypeList}" >
							 				  		<option value="${record.ko2kod}"<c:if test="${ fn:substring(Xmodel.record.kowxxx, 2, 4) == record.ko2kod}"> selected </c:if> >${record.ko2kod}</option>
												  </c:forEach>  
												</select>
												<a tabindex="-1" id="kowxxx2IdLink">
													<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
												</a>
											</td>
										</tr>
										<tr>
											<td class="text12" title="kovlkg">Omr.faktor 1 Lastemeter</td>
											<td class="text12" >
												<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="kovlkg" id="kovlkg" size="6" maxlength="5" value='${Xmodel.record.kovlkg}'>&nbsp;Kg.	
											</td>
											<td width="100px" class="text12" >&nbsp;</td>
											<td class="text12" title="kovkkg">Omr.faktor 1 M3</td>
											<td class="text12" >
												<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="kovkkg" id="kovkkg" size="6" maxlength="5" value='${Xmodel.record.kovkkg}'>&nbsp;Kg.
											</td>
										</tr>
										
										<tr>
											<td class="text12" title="kovavr"><img onMouseOver="showPop('kovavr_info');" onMouseOut="hidePop('kovavr_info');" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
												<span title="kovavr">Overstyre avrund.</span>
												<div class="text11" style="position: relative;" align="left">
												<span style="position:absolute;top:2px" id="kovavr_info" class="popupWithInputText text11"  >	
								           			<p>
								           			Tast <b>J</b> om det ikke ønskes opprunding av vekt/fakt.vekt
										            (Alt annet enn 'J' blir tatt for 'N'-> vanlig avrunding).      
													Vanlig avrunding=                                          
													Opp til nærmeste 100  når fakt.vekt er over 1000kg og pris er gitt pr 10kg eller mer.
													Opp til nærmeste 10  når fakt.vekt er er under 1000kg og når fakt.vekt er over 1000 men
													pris er gitt pr 2-9 kg.</p>
													<p>   
													INGEN AVRUNDING når pris er gitt PR.1 kg. 
								           			</p>                  										        
												</span>
												</div>		
											</td>
											<td class="text12" >
												<input type="text" class="inputTextMediumBlue" name="kovavr" id="kovavr" size="2" maxlength="1" value="${Xmodel.record.kovavr}">
											</td>
											
											<%-- TEST on JSTL
											<td class="text12" title="kovxxxT">
												<input type="text" class="inputTextMediumBlue" name="kovxxx" id="kovxxx" size="8" maxlength="5" value="${Xmodel.record.kovxxx}">
											</td>
											<td class="text12" title="kowxxxT">
												<input type="text" class="inputTextMediumBlue" name="kowxxx" id="kowxxx" size="8" maxlength="5" value="${Xmodel.record.kowxxx}">
											</td>
											 
											
										</tr>
										--%>		
									</table>
								</td>
							</tr>
							<tr height="5"><td >&nbsp;</td></tr>
						</table>
						
						
						
						<%--
						<table class="formFrameHeader" width="95%" 	cellspacing="1" border="0" align="left">
							<tr>
								<td colspan="2" class="text12White" >&nbsp;Forteller til og med vekter i de enkelte frakt grupper - intervaller.
								</td>
							</tr>							
						</table>
						<table class="formFrame" width="95%" cellspacing="1" border="0" align="left">	
				    	    <tr>
								<td class="text12" title="kovk1/kowf1" align="center" ><b>1</b></td>
								<td class="text12" title="kovk2/kowf2" align="center" ><b>2</b></td>
								<td class="text12" title="kovk3/kowf3" align="center" ><b>3</b></td>
								<td class="text12" title="kovk4/kowf4" align="center" ><b>4</b></td>
								<td class="text12" title="kovk5/kowf5" align="center" ><b>5</b></td>
								<td class="text12" title="kovk6/kowf6" align="center" ><b>6</b></td>
								<td class="text12" title="kovk7/kowf7" align="center" ><b>7</b></td>
								<td class="text12" title="kovk8/kowf8" align="center" ><b>8</b></td>
								<td class="text12" title="kovk9/kowf9" align="center" ><b>9</b></td>
								<td class="text12" title="kovk10/kowf10" align="center" ><b>10</b></td>
								<td class="text12" title="kovk11/kowf11" align="center" ><b>11</b></td>
								<td class="text12" title="todo?/kowf12" align="center" ><b>12</b></td>
							</tr>
							<tr>
								<td ><input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="kovk1" id="kovk1" size="6" maxlength="5" value='${Xmodel.record.kovk1}'></td>
								<td ><input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="kovk2" id="kovk2" size="6" maxlength="5" value='${Xmodel.record.kovk2}'></td>
								<td ><input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="kovk3" id="kovk3" size="6" maxlength="5" value='${Xmodel.record.kovk3}'></td>
								<td ><input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="kovk4" id="kovk4" size="6" maxlength="5" value='${Xmodel.record.kovk4}'></td>
								<td ><input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="kovk5" id="kovk5" size="6" maxlength="5" value='${Xmodel.record.kovk5}'></td>
								<td ><input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="kovk6" id="kovk6" size="6" maxlength="5" value='${Xmodel.record.kovk6}'></td>
								<td ><input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="kovk7" id="kovk7" size="6" maxlength="5" value='${Xmodel.record.kovk7}'></td>
								<td ><input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="kovk8" id="kovk8" size="6" maxlength="5" value='${Xmodel.record.kovk8}'></td>
								<td ><input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="kovk9" id="kovk9" size="6" maxlength="5" value='${Xmodel.record.kovk9}'></td>
								<td ><input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="kovk10" id="kovk10" size="6" maxlength="5" value='${Xmodel.record.kovk10}'></td>
								<td ><input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="kovk11" id="kovk11" size="6" maxlength="5" value='${Xmodel.record.kovk11}'></td>
								<td ><input readonly onKeyPress="return numberKey(event)" type="text" class="inputTextReadOnly" name="todo" id="todo" size="6" maxlength="5" value='99999'></td>
							</tr>
							<tr>
								<td ><input type="checkbox" name="kowf1" id="kowf1" value="F" <c:if test="${Xmodel.record.kowf1 == 'F'}"> checked </c:if>  ><font class="text11">fast pris</font></td>
								<td ><input type="checkbox" name="kowf2" id="kowf2" value="F" <c:if test="${Xmodel.record.kowf2 == 'F'}"> checked </c:if>  ><font class="text11">fast pris</font></td>
								<td ><input type="checkbox" name="kowf3" id="kowf3" value="F" <c:if test="${Xmodel.record.kowf3 == 'F'}"> checked </c:if>  ><font class="text11">fast pris</font></td>
								<td ><input type="checkbox" name="kowf4" id="kowf4" value="F" <c:if test="${Xmodel.record.kowf4 == 'F'}"> checked </c:if>  ><font class="text11">fast pris</font></td>
								<td ><input type="checkbox" name="kowf5" id="kowf5" value="F" <c:if test="${Xmodel.record.kowf5 == 'F'}"> checked </c:if>  ><font class="text11">fast pris</font></td>
								<td ><input type="checkbox" name="kowf6" id="kowf6" value="F" <c:if test="${Xmodel.record.kowf6 == 'F'}"> checked </c:if>  ><font class="text11">fast pris</font></td>
								<td ><input type="checkbox" name="kowf7" id="kowf7" value="F" <c:if test="${Xmodel.record.kowf7 == 'F'}"> checked </c:if>  ><font class="text11">fast pris</font></td>
								<td ><input type="checkbox" name="kowf8" id="kowf8" value="F" <c:if test="${Xmodel.record.kowf8 == 'F'}"> checked </c:if>  ><font class="text11">fast pris</font></td>
								<td ><input type="checkbox" name="kowf9" id="kowf9" value="F" <c:if test="${Xmodel.record.kowf9 == 'F'}"> checked </c:if>  ><font class="text11">fast pris</font></td>
								<td ><input type="checkbox" name="kowf10" id="kowf10" value="F" <c:if test="${Xmodel.record.kowf10 == 'F'}"> checked </c:if>  ><font class="text11">fast pris</font></td>
								<td ><input type="checkbox" name="kowf11" id="kowf11" value="F" <c:if test="${Xmodel.record.kowf11 == 'F'}"> checked </c:if>  ><font class="text11">fast pris</font></td>
								<td ><input type="checkbox" name="kowf12" id="kowf12" value="F" <c:if test="${Xmodel.record.kowf12 == 'F'}"> checked </c:if>  ><font class="text11">fast pris</font></td>
								
							</tr>
							
							<tr height="3"><td >&nbsp;</td></tr>
							<tr>
								<td colspan="12" > 
									<table cellspacing="1" border="0" align="left">
									<tr>
										<td class="text12" ><img onMouseOver="showPop('kovomr_info');" onMouseOut="hidePop('kovomr_info');" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
											<span title="kovomr">Pris / kg&nbsp;</span>
											<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="kovomr" id="kovomr" size="4" maxlength="3" value='${Xmodel.record.kovomr}'>
											
											<div class="text11" style="position: relative;" align="left">
											<span style="position:absolute;top:2px" id="kovomr_info" class="popupWithInputText text11"  >	
							           			<b>i</b> intervaller uten F skal pris være pr ?? kg
											</span>
											</div>
										</td>
										<td class="text12" width="20px" ></td>
										<td class="text12"><img onMouseOver="showPop('kowmm_info');" onMouseOut="hidePop('kowmm_info');" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
											<span title="kowmm">Intervall-type&nbsp;</span>
											<select name="kowmm" id="kowmm" >
		 					  					<option value="">-velg-</option>
		 					  					<option value="0"<c:if test="${ Xmodel.record.kowmm == '0'}"> selected </c:if> >0</option>
							  					<option value="1"<c:if test="${ Xmodel.record.kowmm == '1'}"> selected </c:if> >1</option>
							  					<option value="2"<c:if test="${ Xmodel.record.kowmm == '2'}"> selected </c:if> >2</option>
										  	</select>
										  	
											<div class="text11" style="position: relative;" align="left">
											<span style="position:absolute;top:2px;width:300px;" id="kowmm_info" class="popupWithInputText text11"  >	
							           			<ul>
								           			<li><b>0</b>&nbsp;=&nbsp;Ren intevall-pris</li>
							           				<li><b>1</b>&nbsp;=&nbsp;...dog ikke under maks i foregående interv.</li>
							           				<li><b>2</b>&nbsp;=&nbsp;...dog ikke over minimum i neste intervall</li>
							           			</ul>
											</span>
											</div>
										
										</td>
									</tr>
									</table>
								</td>
							</tr>	
						</table>
						 --%>
						
						
						<%--
						<table width="99%" 	cellspacing	="1" border="0" align="left">
							<tr height="3"><td >&nbsp;</td></tr>
							<tr>
								<td valign="top" width="90%">
									<table	cellspacing="1" border="0" align="left">
										<tr>
											<td class="text12" ><img onMouseOver="showPop('kowkom_info');" onMouseOut="hidePop('kowkom_info');" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
												<span title="kowkom" >Kombinert giro</span>
												<div class="text11" style="position: relative;" align="left">
												<span style="position:absolute;top:2px" id="kowkom_info" class="popupWithInputText text11"  >	
								           			<p>Skriv Ja hvis dere benytter den gule giroen som både gjelder bank og postverket.</p> 
												</span>
												</div>
											</td>
											<td >
												<select name="kowkom" id="kowkom" >
			 					  					<option value="">-velg-</option>
			 					  					<option value="J"<c:if test="${ Xmodel.record.kowkom == 'J'}"> selected </c:if> >Ja</option>
								  					<option value="N"<c:if test="${ Xmodel.record.kowkom == 'N'}"> selected </c:if> >Nei</option>
								  				</select>
											</td>
											<td class="text12" title="kowbbs" >&nbsp;
												<img onMouseOver="showPop('kowbbs_info');" onMouseOut="hidePop('kowbbs_info');" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
												Printer typer BBS
												<div class="text11" style="position: relative;" align="left">
												<span style="position:absolute;top:2px" id="kowbbs_info" class="popupWithInputText text11"  >	
								           			<ul>
									           			<li><b>A</b>&nbsp;=&nbsp;4214/5224/5225</li>
								           				<li><b>B</b>&nbsp;=&nbsp;4224</li>
								           				<li><b>C</b>&nbsp;=&nbsp;OCR-belte</li>
								           			</ul>
												</span>
												</div>
											</td>
											<td >
												<select name="kowbbs" id="kowbbs" >
			 					  					<option value="">-velg-</option>
			 					  					<option value="A"<c:if test="${ Xmodel.record.kowbbs == 'A'}"> selected </c:if> >A</option>
								  					<option value="B"<c:if test="${ Xmodel.record.kowbbs == 'B'}"> selected </c:if> >B</option>
								  					<option value="C"<c:if test="${ Xmodel.record.kowbbs == 'C'}"> selected </c:if> >C</option>
								  				</select>
											</td>
											<td class="text12" title="kowawb" >&nbsp;
												<img onMouseOver="showPop('kowawb_info');" onMouseOut="hidePop('kowawb_info');" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
												Delt faktura/giro
												<div class="text11" style="position: relative;" align="left">
												<span style="position:absolute;top:2px;width:200px" id="kowawb_info" class="popupWithInputText text11"  >	
								           			<ul>
									           			<li><b>J</b>&nbsp;=&nbsp;72 delt</li>
								           				<li><b>N</b>&nbsp;=&nbsp;72 m/giro</li>
								           				<li><b>S</b>&nbsp;=&nbsp;96 m/giro</li>
								           				<li><b>K</b>&nbsp;=&nbsp;72M/Kid</li>
								           			</ul>
												</span>
												</div>
											</td>
											<td >
												<select name="kowawb" id="kowawb" >
			 					  					<option value="">-velg-</option>
			 					  					<option value="J"<c:if test="${ Xmodel.record.kowawb == 'J'}"> selected </c:if> >J</option>
								  					<option value="N"<c:if test="${ Xmodel.record.kowawb == 'N'}"> selected </c:if> >N</option>
								  					<option value="S"<c:if test="${ Xmodel.record.kowawb == 'S'}"> selected </c:if> >S</option>
								  					<option value="K"<c:if test="${ Xmodel.record.kowawb == 'K'}"> selected </c:if> >K</option>
								  				</select>
											</td>
										</tr>
										<tr>
											<td class="text12"  ><img onMouseOver="showPop('kowhod_info');" onMouseOut="hidePop('kowhod_info');" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
												<span title="kowhod">Hode på fakt.</span>
												<div class="text11" style="position: relative;" align="left">
												<span style="position:absolute;top:2px;width:200px" id="kowhod_info" class="popupWithInputText text11"  >	
								           			<p>Tast  J  dersom du ønsker at systemet skal skrive ut fakturahode og bunntekst som definert for
													avdelingen i fakturahoderegisteret (MENU VEDL3, valg3).</p>                   
													<p>Kode  T  eller  B  gir kun  Topp  respektive  Bunn-tekst.</p>
													<p>    
													Denne styringen gjelder kun faktura. Styring av øvrige dokumenthoder finnes under MENU VEDL3, valg5. (Kun faktura får bunnteksten.)                                            
													Dersom en benytter kode N i dette feltet, så skrives faktura på 72 linjer ( Benytter IKKE bankgiro) 
													</p>          
												</span>
												</div>
											
											</td>
											<td ><input type="text" class="inputTextMediumBlue" name="kowhod" id="kowhod" size="2" maxlength="1" value='${Xmodel.record.kowhod}'></td>
											<td class="text12" title="kowlas" >&nbsp;
												<img onMouseOver="showPop('kowlas_info');" onMouseOut="hidePop('kowlas_info');" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
												<span title="kowlas">H/AWB-tab</span>
												<div class="text11" style="position: relative;" align="left">
												<span style="position:absolute;top:2px;width:200px" id="kowlas_info" class="popupWithInputText text11"  >	
								           			<p>Hvis man har egne telleverk på awb/hawb-nummer for denne avdeling tastes koden for telleverk.
													Denne kode kan man legge inn eget telleverk på for det enkelte flyselskap.</p>                   
													          
												</span>
												</div>
											</td>
											<td ><input type="text" class="inputTextMediumBlue" name="kowlas" id="todo" size="2" maxlength="1" value='${Xmodel.record.kowlas}'></td>
											<td class="text12" title="avutpr" >&nbsp;
												<img onMouseOver="showPop('avutpr_info');" onMouseOut="hidePop('avutpr_info');" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
												<span title="avutpr">Utl.pro.</span>
												<div class="text11" style="position: relative;" align="left">
												<span style="position:absolute;top:2px;width:200px" id="avutpr_info" class="popupWithInputText text11"  >
													<b>Utleggsprovisjon</b>	
								           			<p>Har avdelingen avvik fra satsene i firmaopplysningen legges dette inn her. Det legges inn sats og
														minimumsbeløp. Obs: 99,99 i sats betyr at avdelingen ikke har utleggsprovisjon.          
													</p>
												</span>
												</div>
											</td>
											<td ><input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue" name="avutpr" id="avutpr" size="6" maxlength="5" value='${Xmodel.record.avutpr}'></td>
											<td class="text12" title="avutmi" >&nbsp;Utl.minb.</td>
											<td ><input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="avutmi" id="avutmi" size="6" maxlength="4" value='${Xmodel.record.avutmi}'></td>
											<td width="50px">&nbsp;</td>
											<td >
												<c:choose>
													<c:when test="${not empty Xmodel.record.kovavd}">
														<input onClick="setBlockUI(this);" class="inputFormSubmit" type="submit" name="submit" value='Lagre'/>
													</c:when>
													<c:otherwise>
														<input onClick="setBlockUI(this);" class="inputFormSubmit" type="submit" name="submit" value='Lage ny'/>
													</c:otherwise>
												</c:choose>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>		
	 	    		</form>
				</td>
			</tr>	 				    
	 	    <tr height="20"><td>&nbsp;</td></tr>
	 	    
	 	    --%>    
	 	   
	 	    <tr height="20"><td>&nbsp;</td></tr>

	 		</table>
		</td>
	</tr>
</table>	

<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->

