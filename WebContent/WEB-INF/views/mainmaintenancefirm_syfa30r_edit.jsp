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
									 			<table class="tableBorderGrayWithRoundCorners3D" width="100%" cellspacing="0" border="0" align="left">
									 			<tr>
									 				<td  valign="top" width="33%">
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
									 				<td valign="top" width="33%">
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
									 							<td class="text12"><img onMouseOver="showPop('fikdt_info');" onMouseOut="hidePop('fikdt_info');" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
																	<span title="fikdt">Benytter TVINN</span>
																	<div class="text11" style="position: relative;" align="left">
																	<span style="position:absolute;top:2px" id="fikdt_info" class="popupWithInputText text11"  >	
													           			todo
																	</span>
																	</div>
																</td>
																<td class="text12">
																	<select name="fikdt" id="fikdt" >
								 					  					<option value="">-velg-</option>
								 					  					<option value="1"<c:if test="${model.record.fikdt == '1'}"> selected </c:if> >Ja</option>
													  					<option value="0"<c:if test="${model.record.fikdt == '0'}"> selected </c:if> >Nei</option>
													  				</select>
																</td>
									 						</tr>
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
									 					</table>	
									 				</td>
									 				<td valign="top" width="34%">
									 					<table align="left" cellspacing="1" border="0" >
									 						<tr>
									 							<td class="text12"><img onMouseOver="showPop('fivalk_info');" onMouseOut="hidePop('fivalk_info');" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
																	<span title="fivalk">Valutakurser vedlikeholdes i</span>
																	<div class="text11" style="position: relative;" align="left">
																	<span style="position:absolute;top:2px" id="fivalk_info" class="popupWithInputText text11"  >	
													           			todo
																	</span>
																	</div>
																</td>
																<td class="text12">
																	<select name="fivalk" id="fivalk" >
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
																	<select name="filfb" id="filfb" >
								 					  					<option value="">-velg-</option>
								 					  					<option value="S"<c:if test="${model.record.filfb == 'S'}"> selected </c:if> >S</option>
													  					<option value="M"<c:if test="${model.record.filfb == 'M'}"> selected </c:if> >M</option>
													  				</select>	
																</td>
									 						</tr>
									 						<tr height="15"><td></td></tr>
									 						
									 						
									 					</table>		
									 				</td>
									 			</tr>
									 			<tr height="12"><td></td></tr>
									 			<tr>
						 							<td class="text11" colspan="4" >
						 								<table width="35%" class="formFrameHeader" cellspacing="0" border="0" align="left">
															<tr>
																<td colspan="4" class="text12White" >&nbsp;Sendingsnr.
																</td>
															</tr>							
														</table>
													</td>	
												</tr>
												<tr>	
													<td class="text11" colspan="4" >	
														<table width="35%" class="formFrame" cellspacing="0" border="0" align="left">
															<tr>
									 							<td colspan="3" class="text12"><img onMouseOver="showPop('todo_info');" onMouseOut="hidePop('todo_info');" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
																	<span title="todo">Tollpass - tildel send.nr</span>
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
									 							<td colspan="3" class="text11"><img onMouseOver="showPop('todo_info');" onMouseOut="hidePop('todo_info');" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
																	<span title="todo">Fr.brev - tildel send.nr</span>
																	<div class="text11" style="position: relative;" align="left">
																	<span style="position:absolute;top:2px" id="todo_info" class="popupWithInputText text11"  >	
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
									 			
									 			
									 			<tr>
													<td colspan="8">
														<table class="formFrameHeader" width="95%" 	cellspacing="0" border="0" align="left">
															<tr><td colspan="15" class="text12White" >&nbsp;Avkryss benyttede systemer</td></tr>
														</table>
														<table class="formFrame"  width="95%" cellspacing="1" border="0" align="left">
																<tr>
																	<td class="text11" align="left"><span title="todo">&nbsp;Land-import</span></td>
																	<td class="text11" align="left"><span title="todo">&nbsp;Land-eksport</span></td>
																	<td class="text11" align="left"><span title="todo">&nbsp;Fly-import</span></td>
																	<td class="text11" align="left"><span title="todo">&nbsp;Fly-eksport</span></td>
																	<td class="text11" align="left"><span title="todo">&nbsp;Fly-innland</span></td>
																	<td class="text11" align="left"><span title="todo">&nbsp;Tr.disp</span></td>
																	<td class="text11" align="left"><span title="todo">&nbsp;SAD-import</span></td>
																	<td class="text11" align="left"><span title="todo">&nbsp;SAD-eksport</span></td>
																	<td class="text11" align="left"><span title="todo">&nbsp;Båt-import</span></td>
																	<td class="text11" align="left"><span title="todo">&nbsp;Båt-eksport</span></td>
																	<td class="text11" align="left"><span title="todo">&nbsp;Track&/Tracing</span></td>
																	<td class="text11" align="left"><span title="todo">&nbsp;TdV IS</span></td>
																	
																</tr>		
										 	    				<tr>
																	<input type="hidden" name="kohavd" id="kohavd" value='${Xmodel.record.listeHodeRecord.kohavd}'>
																	<td class="text12" >
																		<select name="kohfak" id="kohfak" class="inputTextMediumBlueMandatoryField">
														  					<option value="">-velg-</option>
														  					<option value="J"<c:if test="${ Xmodel.record.listeHodeRecord.kohfak == 'J'}"> selected </c:if> >Ja</option>
														  					<option value="N"<c:if test="${ Xmodel.record.listeHodeRecord.kohfak == 'N'}"> selected </c:if> >Nei</option>
														  					
																	  	</select>
																	</td>
																	<td class="text12" >
																		<select name="kohlas" id="kohlas" class="inputTextMediumBlueMandatoryField">
														  					<option value="">-velg-</option>
														  					<option value="J"<c:if test="${ Xmodel.record.listeHodeRecord.kohlas == 'J'}"> selected </c:if> >Ja</option>
														  					<option value="N"<c:if test="${ Xmodel.record.listeHodeRecord.kohlas == 'N'}"> selected </c:if> >Nei</option>
														  					
																	  	</select>
																	</td>
																	<td class="text12" >
																		<select name="kohgod" id="kohgod" class="inputTextMediumBlueMandatoryField">
														  					<option value="">-velg-</option>
														  					<option value="J"<c:if test="${ Xmodel.record.listeHodeRecord.kohgod == 'J'}"> selected </c:if> >Ja</option>
														  					<option value="N"<c:if test="${ Xmodel.record.listeHodeRecord.kohgod == 'N'}"> selected </c:if> >Nei</option>
														  					
																	  	</select>
																	</td>
																	<td class="text12" >
																		<select name="kohbou" id="kohbou" class="inputTextMediumBlueMandatoryField">
														  					<option value="">-velg-</option>
														  					<option value="J"<c:if test="${ Xmodel.record.listeHodeRecord.kohbou == 'J'}"> selected </c:if> >Ja</option>
														  					<option value="N"<c:if test="${ Xmodel.record.listeHodeRecord.kohbou == 'N'}"> selected </c:if> >Nei</option>
														  					
																	  	</select>
																	</td>
																	<td class="text12" >
																		<select name="kohkk" id="kohkk" class="inputTextMediumBlueMandatoryField">
														  					<option value="">-velg-</option>
														  					<option value="J"<c:if test="${ Xmodel.record.listeHodeRecord.kohkk == 'J'}"> selected </c:if> >Ja</option>
														  					<option value="N"<c:if test="${ Xmodel.record.listeHodeRecord.kohkk == 'N'}"> selected </c:if> >Nei</option>
														  					
																	  	</select>
																	</td>
																	<td class="text12" >
																		<select name="kohlos" id="kohlos" class="inputTextMediumBlueMandatoryField">
														  					<option value="">-velg-</option>
														  					<option value="J"<c:if test="${ Xmodel.record.listeHodeRecord.kohlos == 'J'}"> selected </c:if> >Ja</option>
														  					<option value="N"<c:if test="${ Xmodel.record.listeHodeRecord.kohlos == 'N'}"> selected </c:if> >Nei</option>
														  					
																	  	</select>
																	</td>
																	<td class="text12" >
																		<select name="kohman" id="kohman" class="inputTextMediumBlueMandatoryField">
														  					<option value="">-velg-</option>
														  					<option value="J"<c:if test="${ Xmodel.record.listeHodeRecord.kohman == 'J'}"> selected </c:if> >Ja</option>
														  					<option value="N"<c:if test="${ Xmodel.record.listeHodeRecord.kohman == 'N'}"> selected </c:if> >Nei</option>
														  					
																	  	</select>
																	</td>
																	<td class="text12" >
																		<select name="kohls1" id="kohls1" class="inputTextMediumBlueMandatoryField">
														  					<option value="">-velg-</option>
														  					<option value="L"<c:if test="${ Xmodel.record.listeHodeRecord.kohls1 == 'L'}"> selected </c:if> >L</option>
														  					<option value="S"<c:if test="${ Xmodel.record.listeHodeRecord.kohls1 == 'S'}"> selected </c:if> >S</option>
														  					
																	  	</select>
																	</td>
																	<td class="text12" >
																		<select name="koh421" id="koh421" class="inputTextMediumBlueMandatoryField">
														  					<option value="">-velg-</option>
														  					<option value="J"<c:if test="${ Xmodel.record.listeHodeRecord.koh421 == 'J'}"> selected </c:if> >Ja</option>
														  					<option value="N"<c:if test="${ Xmodel.record.listeHodeRecord.koh421 == 'N'}"> selected </c:if> >Nei</option>
														  					
																	  	</select>
																	</td>
																	<td class="text12" >
																		<select name="kohls2" id="kohls2" class="inputTextMediumBlueMandatoryField">
														  					<option value="">-velg-</option>
														  					<option value="L"<c:if test="${ Xmodel.record.listeHodeRecord.kohls2 == 'L'}"> selected </c:if> >L</option>
														  					<option value="S"<c:if test="${ Xmodel.record.listeHodeRecord.kohls2 == 'S'}"> selected </c:if> >S</option>
														  					
																	  	</select>
																	</td>
																	<td class="text12" >
																		<select name="koh422" id="koh422" class="inputTextMediumBlueMandatoryField">
														  					<option value="">-velg-</option>
														  					<option value="J"<c:if test="${ Xmodel.record.listeHodeRecord.koh422 == 'J'}"> selected </c:if> >Ja</option>
														  					<option value="N"<c:if test="${ Xmodel.record.listeHodeRecord.koh422 == 'N'}"> selected </c:if> >Nei</option>
														  					
																	  	</select>
																	</td>
																	<td class="text12" >
																		<select name="kohls3" id="kohls3" class="inputTextMediumBlueMandatoryField">
														  					<option value="">-velg-</option>
														  					<option value="L"<c:if test="${ Xmodel.record.listeHodeRecord.kohls3 == 'L'}"> selected </c:if> >L</option>
														  					<option value="S"<c:if test="${ Xmodel.record.listeHodeRecord.kohls3 == 'S'}"> selected </c:if> >S</option>
														  					
																	  	</select>
																	</td>

												    	    	</tr>	
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

