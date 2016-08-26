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
			<form action="mainmaintenanceavd_syfa14r_edit.do" name="formRecord" id="formRecord" method="POST" >
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
					
						<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
						<input type="hidden" name="updateId" id=updateId value="${Xmodel.updateId}">
						<input type="hidden" name="action" id=action value="${Xmodel.action}">
						<table cellspacing="1" border="0" align="left">
				    	    <tr>
								<td class="text12" title="sidl"><font class="text12RedBold" >*</font>Bruke dataliste
									<select name="sidl" id="sidl" class="inputTextMediumBlueMandatoryField">
					  					<option value="">-velg-</option>
					  					<option value="J"<c:if test="${ model.record.sidl == 'J'}"> selected </c:if> >Ja</option>
					  					<option value="N"<c:if test="${ model.record.sidl == 'N'}"> selected </c:if> >Nej</option>
					  					<option value="1"<c:if test="${ model.record.sidl == '1'}"> selected </c:if> >1</option>
								  	</select>
								</td>
								<td class="text12" title="sia4">&nbsp;&nbsp;Format A4
									<select name="sia4" id="sia4" class="inputTextMediumBlue">
					  					<option value="">-velg-</option>
					  					<option value="J"<c:if test="${ model.record.sia4 == 'J'}"> selected </c:if> >Ja</option>
					  					<option value="N"<c:if test="${ model.record.sia4 == 'N'}"> selected </c:if> >Nej</option>
								  	</select>
								</td>	
								<td class="text12" title="sitdn">&nbsp;&nbsp;<font class="text12RedBold" >*</font>Nrt.teller intern ref.
									<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlueMandatoryField" name="sitdn" id="sitdn" size="10" maxlength="7" value='${model.record.sitdn}'>
								</td>
								<td class="text12" title="sitolk">&nbsp;&nbsp;<font class="text12RedBold" >*</font>Bruk kommunikasjon
									<select name="sitolk" id="sitolk" class="inputTextMediumBlueMandatoryField">
					  					<option value="">-velg-</option>
					  					<option value="J"<c:if test="${ model.record.sitolk == 'J'}"> selected </c:if> >Ja</option>
					  					<option value="N"<c:if test="${ model.record.sitolk == 'N'}"> selected </c:if> >Nej</option>
								  	</select>
								</td>
								<td class="text12" title="siekst">&nbsp;&nbsp;Importere eksterne data
									<select name="siekst" id="siekst" class="inputTextMediumBlue">
					  					<option value="">-velg-</option>
					  					<option value="J"<c:if test="${ model.record.siekst == 'J'}"> selected </c:if> >Ja</option>
					  					<option value="N"<c:if test="${ model.record.siekst == 'N'}"> selected </c:if> >Nej</option>
					  					<option value="R"<c:if test="${ model.record.siekst == 'R'}"> selected </c:if> >R</option>
								  	</select>
								</td>
							</tr>
							
							<tr>
				 			<td class="text12" align="left">
				 				<img onMouseOver="showPop('1_1_info');" onMouseOut="hidePop('1_1_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
			 					<b>1.1</b><span title="sidty">Dekl.type&nbsp;</span>
			 					<input type="text" class="inputTextMediumBlue" name="sidty" id="sidty" size="4" maxlength="2" value="${model.record.sidty}">
			 					
			 					<div class="text11" style="position: relative;" align="left">
			 					<span style="position:absolute; top:2px; width:250px;" id="1_1_info" class="popupWithInputText text11"  >
					           			<b>1.1 Dekl.type</b>
					           			<ul>
					           				<li><b>EU</b> innførsel fra et EØS,EFTA eller EU-land</li>
					           				<li><b>IM</b> innførsel fra land som ikke er tilknyttet EØS, EFTA eller EU (tredjeland).
					           				</li>
					           			</ul>
					           			<p>
					           			KODEN KAN LEGGES INN SOM STANDARDVERDI PR.AVDELING. 
					           			KAN OVERSTYRES UNDER DEKLARERINGEN.
					           			</p>
								</span>	
								</div>	
				 				
			 				</td>
			 				
			 				<td class="text12" colspan="2">
				 				<img onMouseOver="showPop('prosedyr_info');" onMouseOut="hidePop('prosedyr_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 				<b>1.2</b><span title="sidp">&nbsp;Eksped.type</span>
				 				<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="sidp" id="sidp" size="4" maxlength="2" value="${model.record.sidp}">
					 			
					 			<div class="text11" style="position: relative;display:inline;" align="left">
				 				<span style="position:absolute; top:5px; left:-130px; width:250px;" id="prosedyr_info" class="popupWithInputText text11">
					           		<ul>
					           			<li>todo</li>
					           		</ul>
								</span>
								</div>
			 				</td>
			 			</tr>
			 			<tr>
			 				<td class="text12" align="center">
					 				<span title="siski" id="v_siski" class="validation">Toll/Mva</span>
					 				<select name="siski" id="siski" >
				 						<option value="">-velg-</option>
								  		<option value="S"<c:if test="${ model.record.siski == 'S'}"> selected </c:if> >S</option>
								  		<option value="K"<c:if test="${ model.record.siski == 'K'}"> selected </c:if> >K</option>
								  		<option value="I"<c:if test="${ model.record.siski == 'I'}"> selected </c:if> >I</option>
									</select>
					 				<div class="text11" style="position: relative;" align="left">
					 				<span style="position:absolute; top:2px; width:250px;" id="tollMva_info" class="popupWithInputText text11" >
					           			<b>Toll/Mva</b>
					           			<br/><br/>
					           			Dersom systemet ikke finner tollkreditt på det tastede kundenummeret, så må man angi hvem som skal betale TOLL/MVA.
						           		<ul>
						           			<li><b>S</b> = på faktura mot selger/avsender siden (flyttes autom. over på faktura)</li>
					           				<li><b>K</b> = på faktura mot kjøper</li>
						           			<li><b>I</b> = Ingen (Kunden kommer selv med sjekk på utlegget</li>
						           		</ul>
						           		<p>
						           		KRAV TIL FELTET dersom kunde ikke har tollkreditt.
						           		</p>
									</span>
									</div>
								</td>
			 				
				 				<td class="text12" colspan="2">
				 					<img onMouseOver="showPop('ens_flag_info');" onMouseOut="hidePop('ens_flag_info'); "style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 					<span title="sikddk">&nbsp;Dagsopp./Kontant</span>
					 				<select name="sikddk" id="sikddk" >
					 				  <option selected value="">-velg-</option>
									  <option value="D"<c:if test="${model.record.sikddk == 'D'}"> selected </c:if> >D</option>
									  <option value="K"<c:if test="${model.record.sikddk == 'K'}"> selected </c:if> >K</option>
									</select>
					 				
					 				<div class="text11" style="position: relative;" align="left">
					 				<span style="position:absolute; top:2px; width:250px;" id="ens_flag_info" class="popupWithInputText text11"  >
							           		<b>Dagsoppgjør/Kontant</b>
							           		<p>
							           		Kodevalg for hvilke tekst man ønsker på deklarasj. dersom S/K/I er brukt.
							           		</p>
							           		<ul>
							           			<li><b>D</b> = Dagsoppgjør</li>
							           			<li><b>K</b> = Kontant</li>						           			
							           		</ul>
									</span>
									</div>
				 				</td>
			 			
			 			
			 			</tr>
							
						<tr height="3"><td>&nbsp;</td></tr>
							
						</table>
				</td>
			</tr>
			
			<tr>
			<td width="5%">&nbsp;</td>
			<td>
				<table width="98%" cellspacing="1" border="0" align="left">
					<tr>
						<%-- LEFT SIDE  --%>
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
							 	<table width="95%" align="left" class="formFrame" border="0" cellspacing="0" cellpadding="0">
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
										            <span title="sinas" id="v_sinas" class="validation">Navn&nbsp;</span>
										            	<a tabindex="-1" id="sinasIdLink">
															<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
														</a>				
										            </td>
										        </tr>
										        <tr>
										            <td class="text12" align="left"><input type="text" class="inputTextMediumBlue" name="sikns" id="sikns" size="9" maxlength="8" value="${model.record.sikns}"></td>
										            <td class="text12" align="left"><input type="text" class="inputTextMediumBlue"  name="sinas" id="sinas" size="31" maxlength="30" value="${model.record.sinas}"></td>
										        </tr>
										        <tr height="4"><td>&nbsp;</td></tr>
										        <tr>
										            <td class="text12" align="left" >&nbsp;&nbsp;
										            <span title="siads1" id="v_siads1" class="validation">Adresse-1</span></td>
										            <td>&nbsp;</td>
										        </tr>
										        <tr>
										            <td colspan="2" align="left"><input type="text" class="inputTextMediumBlue"  name="siads1" id="siads1" size="40" maxlength="30" value="${model.record.siads1}"></td>
			    							            
										        </tr>
										        <tr>
										            <td class="text12" align="left" >&nbsp;&nbsp;
										            <span title="siads2" id="v_siads2" class="validation">Adresse-2</span></td>
			    							            <td>&nbsp;</td>
										        </tr>
										        <tr>
										            <td colspan="2" align="left"><input type="text" class="inputTextMediumBlue" name="siads2" id="siads2" size="40" maxlength="30" value="${model.record.siads2}"></td>
			   							            
										        </tr>
										        <tr>
										            <td class="text12" align="left" >&nbsp;&nbsp;
										            <span title="siads3" id="v_siads3" class="validation">Adresse-3</span></td>
			   							            <td>&nbsp;</td>							            
										        </tr>
										        <tr>
										            <td colspan="2" align="left"><input type="text" class="inputTextMediumBlue" name="siads3" id="siads3" size="40" maxlength="30" value="${model.record.siads3}"></td>
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
				 		<tr>
				 			<td >		
				 				<table width="95%" align="left" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
							 		<tr height="15">
							 			<td class="text12White">
							 				&nbsp;<img onMouseOver="showPop('8_info');" onMouseOut="hidePop('8_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
							 				<b>&nbsp;8.</b>&nbsp;Mottaker&nbsp;<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
							 				<div class="text11" style="position: relative;" align="left">
							 				<span style="position:absolute; top:2px; width:250px;" id="8_info" class="popupWithInputText text11"  >
									           		<b>Mottaker</b>
									           		<p>
									           		Oppgi mottakers/vareeiers navn og adresse.
									           		</p>
									           		<p> 
									           		I henhold til tollovens paragraf 1, pkt 3, er vareeier den som overfor tollvesenet er legitimert til å råde over varene. 
									           		Her oppgis også vareeierens 8-sifrede organisasjonsnr.
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
							 	<table width="95%" align="left" class="formFrame" border="0" cellspacing="0" cellpadding="0">
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
										        	<input type="hidden" name="orig_siknk" id="orig_siknk" value='${Xmodel.record.siknk}'>
										        	<input type="hidden" name="orig_sinak" id="orig_sinak" value='${Xmodel.record.sinak}'>
										        	<input type="hidden" name="orig_sirg" id="orig_sirg" value='${Xmodel.record.sirg}'>
										        	<input type="hidden" name="orig_siadk1" id="orig_siadk1" value='${Xmodel.record.siadk1}'>
										        	<input type="hidden" name="orig_siadk2" id="orig_siadk2" value='${Xmodel.record.siadk2}'>
										        	<input type="hidden" name="orig_siadk3" id="orig_siadk3" value='${Xmodel.record.siadk3}'>
										        	
										            <td class="text12" align="left" >&nbsp;&nbsp;
										            <span title="siknk">Kundenummer</span>
										            		<img id="receiverFreeTextImg" onMouseOver="showPop('receiverFtxtinfo');" onMouseOut="hidePop('receiverFtxtinfo');" onClick="showPop('receiverInfoFreeTextDialog');" style="vertical-align:top;" width="18px" height="18px" src="resources/images/largeTextContent.png" border="0" alt="info" title="Spørring på fritekster for kunder" >
											 			<div class="text11" style="position: relative;" align="left">
														<span style="position:absolute; top:2px; width:250px;" id="receiverFtxtinfo" class="popupWithInputText"  >
											           		<font class="text11" >Spørring på fritekster for kunder</font>
														</span>
														</div>
														
														<span style="position:absolute; left:300px; top:450px; width:400px; height:500px;" id="receiverInfoFreeTextDialog" class="popupWithInputText"  >
											           		<div class="text11" align="left">
											           		Spørring på fritekster for kunder
											           		<p>
											           			<textarea rows="20" cols="40" class="inputText" name="receiverInfoTextArea" id="receiverInfoTextArea" maxlength="1000"></textarea>
											           		</p>
											           		<p>
											           			&nbsp;&nbsp;<button name="receiverInfoFreeTextDialogCloseOk" id="receiverInfoFreeTextDialogCloseOk" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('receiverInfoFreeTextDialog');"><spring:message code="systema.tvinn.sad.import.ok"/></button>
											           		</p>
											           		</div>
														</span>
														
														
										            </td>
										            <td class="text12" align="left" >&nbsp;&nbsp;
										            <span title="sinak" id="v_sinak" class="validation">Navn&nbsp;</span>
										            	<a tabindex="-1" id="sinakIdLink">
															<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
														</a>			
										            </td>
										        </tr>
										        <tr>
										            <td class="text12" align="left"><input type="text" class="inputTextMediumBlue" name="siknk" id="siknk" size="9" maxlength="8" value="${model.record.siknk}"></td>
										            <td class="text12" align="left"><input type="text" class="inputTextMediumBlue"  name="sinak" id="sinak" size="31" maxlength="30" value="${model.record.sinak}"></td>
										        </tr>
										        <tr height="10"><td></td></tr>
										        
										        <tr>
										            <td class="text12" align="left" >&nbsp;<span title="sirg" id="v_sirg" class="validation">Regnr</span></td>
										            <td class="text12" >
									 					<img onMouseOver="showPop('48_info');" onMouseOut="hidePop('48_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
										 				&nbsp;<b>48.&nbsp;</b><span title="siktc/sikta/siktb">Kontonr.Tollkredit&nbsp;</span>
										 				<div class="text11" style="position: relative;" align="left">
										 				<span style="position:absolute; top:2px; width:250px;" id="48_info" class="popupWithInputText text11"  >
										           			<b>48. Kontonr.Tollkredit</b>&nbsp;
															<p>
										           			Hentes fra kunderegister. Dersom det er en kunde som tidligere har vært uten kred, så kan man taste inn krednr.
										           			</p>
														</span>
														</div>
													</td>
										        </tr>
										        <tr>
										            <td align="left"><input type="text" class="inputTextMediumBlue"  name="sirg" id="sirg" size="20" maxlength="11" value="${model.record.sirg}"></td>
										            <td align="left">
										            		<input onKeyPress="return numberKey(event)" style="text-align: right" type="text" class="inputTextMediumBlue" name="siktc" id="siktc" size="1" maxlength="1" value="${model.record.siktc}">
														<input onKeyPress="return numberKey(event)" style="text-align: right" type="text" class="inputTextMediumBlue" name="sikta" id="sikta" size="5" maxlength="5" value="${model.record.sikta}">
														<input onKeyPress="return numberKey(event)" style="text-align: right" type="text" class="inputTextMediumBlue" name="siktb" id="siktb" size="2" maxlength="2" value="${model.record.siktb}">
													</td>	
										        </tr>
										         
										        <tr height="4"><td>&nbsp;</td></tr>
										        <tr>
										            <td class="text12" align="left" >&nbsp;&nbsp;
										            <span title="siadk1" id="v_siadk1" class="validation">Adresse-1</span></td>
										            <td>&nbsp;</td>
										        </tr>
										        <tr>
										            <td colspan="2" align="left"><input type="text" class="inputTextMediumBlue" name="siadk1" id="siadk1" size="40" maxlength="30" value="${model.record.siadk1}"></td>
			    							            
										        </tr>
										        <tr>
										            <td class="text12" align="left" >&nbsp;&nbsp;
										            <span title="siadk2" id="v_siadk2" class="validation">Adresse-2</span></td>
			    							            <td>&nbsp;</td>
										        </tr>
										        <tr>
										            <td colspan="2" align="left"><input type="text" class="inputTextMediumBlue" name="siadk2" id="siadk2" size="40" maxlength="30" value="${model.record.siadk2}"></td>
			   							            
										        </tr>
										        <tr>
										            <td class="text12" align="left" >&nbsp;&nbsp;
										            <span title="siadk3" id="v_siadk3" class="validation">Adresse-3</span></td>
			   							            <td>&nbsp;</td>							            
										        </tr>
										        <tr>
										            <td colspan="2" align="left"><input type="text" class="inputTextMediumBlue" name="siadk3" id="siadk3" size="40" maxlength="30" value="${model.record.siadk3}"></td>
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
			           			 		
					 	<%-- DEKLARANT --%>
					 	<tr>
			            <td >		
			 				<table width="95%" align="left" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
						 		<tr height="18px">
						 			<td class="text12White">
						 				&nbsp;<img onMouseOver="showPop('14_b_info');" onMouseOut="hidePop('14_b_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 						<b>&nbsp;14.</b>&nbsp;Deklarant&nbsp;<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
						 				<div class="text11" style="position: relative;" align="left">
						 				<span style="position:absolute; top:2px; width:250px;" id="14_b_info" class="popupWithInputText text11"  >
							           		<b>14.Deklarant</b> 
						           			<p>
						           				Hentes automatisk fra standardverdiene for AVDELINGEN
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
						 	<table width="95%" align="left" class="formFrame" border="0" cellspacing="0" cellpadding="0">
						 		<tr>
							 		<td>
								 		<table width="100%" border="0" cellspacing="0" cellpadding="0">
									 		<tr height="5"><td></td></tr>
									        <tr>
									            <td class="text12" align="left" >&nbsp;&nbsp;
									            <span title="sinad">Navn</span></td>
									            <td class="text12" align="left" >&nbsp;&nbsp;
									            <span title="sitlf">Telefon</span></td>
									        </tr>
									        <tr>
									            <td align="left"><input  type="text" class="inputTextMediumBlue" name="sinad" id="sinad" size="35" maxlength="30" value="${model.record.sinad}"></td>
									            <td align="left"><input  type="text" class="inputTextMediumBlue" name="sitlf" id="sitlf" size="15" maxlength="12" value="${model.record.sitlf}"></td>
									        </tr>
									        
								        </table>
							      	</td>
								 </tr>
								 <tr height="15"><td></td></tr>
						 	</table>
	            		</td>
		           	</tr>
		           	<tr height="20"><td></td></tr>
					 	
					 	
					 	
					 	
					 	
					 		
						</table>
						</td>
						
						<%-- RIGH SIDE  --%>
						<td width="50%" valign="top">
						<table width="100%" cellspacing="1" border="1" align="left">
							<tr>
					            <td class="text12" align="left" >
					            <img onMouseOver="showPop('15_info');" onMouseOut="hidePop('15_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
			 					<b>15.</b>&nbsp;<span title="silka">Avs/utf.land</span>&nbsp;
					            <div class="text11" style="position: relative;" align="left">
			 					<span style="position:absolute; top:2px; width:250px;" id="15_info" class="popupWithInputText text11"  >
					           			<b>15. Avs/utf.land</b>
					           			<br/><br/>
					           			Med avsenderland forstås det land hvorfra varen er sendt til Norge uten mellomliggende handelstransaksjon (omlasting undderveis endrer ikke forholdet)
										
								</span>	
								</div>
									
					             </td>
					            <td >
				            		<select class="inputTextMediumBlue" name="silka" id="silka">
				 						<option value="">-velg-</option>
					 				  	<c:forEach var="country" items="${model.countryCodeList}" >
					 				  		<option value="${country.zkod}"<c:if test="${model.record.silka == country.zkod}"> selected </c:if> >${country.zkod}</option>
										</c:forEach>  
									</select>
									<a tabindex="-1" id="silkaIdLink">
									<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
									</a>
																																	 			
								</td>
							</tr>
							<tr height="8"><td class="text"></td> </tr>
							
							<tr>
					            <td class="text12" align="left" >
					            <img onMouseOver="showPop('19_info');" onMouseOut="hidePop('19_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					            <b>19.</b><span title="sikdc" id="v_sikdc" class="validation">Container&nbsp;</span>
					            
					            <div class="text11" style="position: relative;" align="left">
					            <span style="position:absolute; top:2px; width:250px;" id="19_info" class="popupWithInputText text11">
					           			<b>19. Container</b>
					           			<br/><br/>
					           			Oppgi,med fastsatte koder, om varene blir transportert i containere ved innpassering til Norge.
										<br>
										<ul>
										<li><b>0</b> = Varer som ikke transporteres i containere.</li>
										<li><b>1</b> = Varer som transporteres i containere.</li>
										</ul>
										Kan defineres som standardverdier pr. avdeling. 
										
								</span>	
								</div>
								</td>
									
					            <td class="text12" >
			           				<select name="sikdc" id="sikdc">
				 						<option value="0" <c:if test="${model.record.sikdc == '0'}"> selected </c:if> >0</option>
				 						<option value="1" <c:if test="${model.record.sikdc == '1'}"> selected </c:if> >1</option>								 				  	  
									</select>
			           			</td>
							</tr>
							<tr height="10"><td class="text"></td> </tr>
							
							<tr>
					            <td class="text12" align="left" >
					            <img onMouseOver="showPop('21_1_info');" onMouseOut="hidePop('21_1_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
			 					<b>21.1</b><span title="sitrid" id="v_sitrid" class="validation">Transp.ID</span>
			 					<div class="text11" style="position: relative;" align="left">
			 					<span style="position:absolute; top:2px; width:250px;" id="21_1_info" class="popupWithInputText text11"  >
					           			<b>21.1 Det aktive Transportmidlets identitet</b>
					           			<p>
					           			Oppgi det aktive transportmidlets identitet ved innpassering til Norge. 
					           			</p>
										<p>
										KRAV ved de fleste transportmåter.
										</p>
								</span>		
					            </div>
					            </td>
					            
				                 <td >
						            	<input type="text" class="inputTextMediumBlue" name="sitrid" id="sitrid" size="21" maxlength="20" value="${model.record.sitrid}">
								</td>
							</tr>
							
							
							<tr>
					            <td class="text12" align="left">
					            <img onMouseOver="showPop('21_2_info');" onMouseOut="hidePop('21_2_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					            <b>21.2</b>
					            <span title="silkt">Aktive transp. nasjonalitet&nbsp;</span>
					          	<div class="text11" style="position: relative;" align="left">
					            <span style="position:absolute; top:2px; width:250px;" id="21_2_info" class="popupWithInputText text11"  >
					           			<b>21.2 Aktive transp. nasjonalitet ved grense</b>
					           			<p>
					           			Oppgi det aktive transportmidlets nasjonalitet ved innpassering til Norge. 
					           			Feks. Norge = NO Tyskland = DE.
					           			</p>
					           			
								</span>
								</div>
								</td>
									
								<td>
				            		<select name="silkt" id="silkt">
			 						<option value="">-velg-</option>
				 				  	<c:forEach var="country" items="${model.countryCodeList}" >
				 				  		<option value="${country.zkod}"<c:if test="${model.record.silkt == country.zkod}"> selected </c:if> >${country.zkod}</option>
									</c:forEach>  
									</select>
									<a tabindex="-1" id="silktIdLink">
									<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
									</a>
									</td>
					        </tr>
							
							
							<tr>
					            <td class="text12" align="left" >
					            <img onMouseOver="showPop('25_info');" onMouseOut="hidePop('25_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					            <b>25.</b>
					            <span title="sitrm" id="v_sitrm" class="validation">Transportmåte ved grensen</span>
					            <div class="text11" style="position: relative;" align="left">
					            <span style="position:absolute; top:2px; width:250px;" id="25_info" class="popupWithInputText text11"  >
					           			<b>25. Transportmåte ved grensen</b>
					           			<p>
					           			Oppgi med kode transportmåten ved innpassering til Norge.
										Koden kan legges inn som standardverdi for avdelingen
										</p>
										
								</span>	
								</div>
								
								</td>	
					            <td class="text12" >
			           				<select name="sitrm" id="sitrm">
				 						<option value="">-velg-</option>
					 				  	<c:forEach var="record" items="${model.transportmaterCodeList}" >
					 				  		<option value="${record.zkod}"<c:if test="${model.record.sitrm == record.zkod}"> selected </c:if> >${record.zkod}</option>
										</c:forEach>  
									</select>
			           			</td>
							</tr>
							
							
							<tr>
								<td class="text12" align="left" >
					            <img onMouseOver="showPop('49_info');" onMouseOut="hidePop('49_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">	
					            <b>49.&nbsp;</b><span title="sign">Godsnr</span>
					            &nbsp;<input type="text" class="inputTextMediumBlue"  name="sign" id="sign" size="20" maxlength="15" value="${model.record.sign}">
					            <div class="text11" style="position: relative;" align="left">
					            <span style="position:absolute; top:2px; width:250px;" id="49_info" class="popupWithInputText text11"  >
					           			<b>49. Godsnr</b><br/>
					           			Godsnr og posisjonsnr/konnossementnr overføres fra oppdraget.
										<br/><br/>
										Oppbygging av godsnr.:
										<ul>
											<li><b>1.- 4.</b> karakter: Årstall for registrering</li>
											<li><b>5.- 6.</b> karakter: Tolldistriktskode</li>
											<li><b>7.- 9.</b> karakter: Tollagerkode</li>
											<li><b>10.-12.</b> karakter: Lossedag, kalenderens dagnr.<li>
											<li><b>13.-15.</b> karakter: lossenr, fortløpende listenr, pr. tollager og dag</li>
										</ul>
										NB: Disse godsnummerkodene er definert i systemet med følgende funksjon:
										<ul>
											<li><b>P</b> = Postsending. Hvis denne koden benyttes vil rubrikk 25 Transportmåte automatisk bli    oppdatert med kode 50. Dette gjelder selv om annen kode allerede er tastet.
											Hvis Transportmåte = 50 er tastet og annen godsnummerkode enn P
											er benyttet, vil systemet gi feilmelding om dette.
											Ved godsnummerkode P er det ikke tillatt å taste noe i felt for posisjonsnr/konnossementnr.</li>
											<li><b>R</b> = Reisegods. Ved godsnummerkode R er det ikke tillatt å taste noe i felt for posisjonsnr/konnossementnr.
											<li><b>D</b> = Grensefortollet. Ved godsnummerkode D er det ikke tillatt å taste noe i felt for posisjonsnr/konnossementnr.
											<br/><br/>
											Andre gyldige koder:
											<li><b>J</b> = Jernbane.</li>
											<li><b>E</b> = Jernbane Express.</li>
											<li><b>F</b> = Fortolling fra frilager.</li>
											<li><b>T</b> = Fortolling fra transittlager.</li>
										</ul>
										Ved disse kodene er det valgfritt for bruker å taste noe i felt for posisjonsnr/konnossementnr.
								</span>	
								</div>
								
								</td>
								<td class="text12" align="left" ><span title="sipos">Posisjon</span>
					            		&nbsp;<input type="text" class="inputTextMediumBlue" name="sipos" id="sipos" size="15" maxlength="9" value="${model.record.sipos}">
					            </td>
					        </tr>
							
							<tr height="5"><td class="text"></td></tr>
							
							
						</table>
						</td>
					</tr>
				</table>
			</td>
			</tr>			
				
	 	    <tr height="20"><td>&nbsp;</td></tr>
	 	    
			</table>
	 		</form>
	 		
		</td>
	</tr>
</table>	

<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->

