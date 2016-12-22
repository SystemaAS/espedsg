<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerMainMaintenance.jsp" />
<!-- =====================end header ==========================-->
	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/mainmaintenancecundf_kunde_edit.js?ver=${user.versionEspedsg}"></SCRIPT>	
	
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
	 		<table width="100%" class="tabThinBorderWhite" border="0" cellspacing="0" cellpadding="0">
	 	    <tr height="20"><td>&nbsp;</td></tr>
	 	    
			
<!-- 	 	    
	 	    <tr>
	 	   		<td width="5%">&nbsp;</td>
	 	   		<td>
					<table class="formFrameHeaderTransparent" width="1000" cellspacing="0" border="0" cellpadding="0">

						<tr height="20"> 
						
								<td width="20">&nbsp;</td>

								<td width="80" valign="bottom" class="tabDisabled" align="center" title="Sköna  personer...">
									<a id="alinkMainMaintMavgGate" onClick="setBlockUI(this);" href="mainmaintenancecundf_xxx_edit.do">
										<font class="tabDisabledLinkMinor">&nbsp;Miljöavgift</font>&nbsp;						
									</a>
								</td>

								<td width="80" valign="bottom" class="tabDisabled" align="center" title="Kontakt personer...">
									<a id="alinkMainMaintxxxGate" onClick="setBlockUI(this);" href="mainmaintenancecundf_xxx_edit.do">
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
 	   	 	<tr> <!-- Second tab row... -->
 	   	 		<td>&nbsp;</td>
 	   	 	    <td>
 					<table class="formFrameHeaderTransparent" style="width:1000px" cellspacing="0" border="0" cellpadding="0">
						<tr height="20"> 
								<td width="80" valign="bottom" class="tabSub" align="center" nowrap>
									<font class="tabLinkMinor">&nbsp;Kunde</font>
								</td>

								<td width="80" valign="bottom" class="tabDisabledSub" align="center" nowrap>
									<a id="alinkMainMaintKontaktGate" onClick="setBlockUI(this);" href="mainmaintenancecundf_kontaktpersoner_list.do">
										<font class="tabDisabledLinkMinor">&nbsp;Kontakter</font>&nbsp;						
									</a>
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

								<td width="80" valign="bottom" class="tabDisabledSub" align="center" nowrap>
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
								<td width="40" class="tabDisabledTrailingEnd"  align="center" nowrap></td>
-->
							 	<td width="520" class="tabFantomSpace" align="center" nowrap></td>


						</tr>
					</table>
				</td>
 	   	 	
 	   	 	
 	   	 	</tr> <!-- End second tab row -->
 
 	    	<tr>
				<td>&nbsp;</td>
				<td width="100%">
					<form action="mainmaintenancecundf_kunde_edit.do" name="formRecord" id="formRecord" method="POST" >
						<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
						<input type="hidden" name="action" id=action value="${model.action}">
						<input type="hidden" name="dirty" id=dirty value="">
						<table class="tabThinBorderWhite" width="100%" cellspacing="0" border="0" align="left">
							<tr>
								<td width="50%" >&nbsp;
									<table border="0">
										<tr>
											<td class="text12" title="kundnr">&nbsp;<font class="text14RedBold" >*</font>Kundenr:</td>
											<td><input type="text" required oninvalid="this.setCustomValidity('Obligatoriskt')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField"  name="kundnr" id="kundnr" size="10" maxlength="8" value='${model.record.kundnr}'></td>
<!-- 
											<td><input readonly type="text" class="inputTextReadOnly" name="kundnr" id="kundnr" size="10" maxlength="8" value='${model.record.kundnr}'></td>
 -->
											<td class="text12" title="syrg">&nbsp;Org.nr:</td>
											<td><input type="text" class="inputTextMediumBlue" name="syrg" id="syrg" size="15" maxlength="14" value='${model.record.syrg}'></td>
										</tr>
										<tr>
											<td class="text12" title="knavn">&nbsp;<font class="text14RedBold" >*</font>Navn:</td>
											<td><input type="text" required oninvalid="this.setCustomValidity('Obligatoriskt')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="knavn" id="knavn" size="30" maxlength="30" value='${model.record.knavn}'></td>
											<td class="text12" title="eori">&nbsp;Eorinr:</td>
											<td><input type="text" class="inputTextMediumBlue" name="eori" id="eori" size="15" maxlength="17" value='${model.record.eori}'></td>
										</tr>
										<tr>
											<td class="text12" title="adr1">&nbsp;Adresse:</td>
											<td><input type="text" class="inputTextMediumBlue" name="adr1" id="adr1" size="30" maxlength="30" value='${model.record.adr1}'></td>
											<td class="text12" title="spraak">&nbsp;Språk:</td>
											<td><input type="text" class="inputTextMediumBlue" name="spraak" id="spraak" size="1" maxlength="1" value='${model.record.spraak}'>
												<a tabindex="-1" id="spraakIdLink">
													<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="Søk" >
												</a>
											</td>
										</tr>
										<tr>
											<td class="text12" title="postnr">&nbsp;<font class="text14RedBold" >*</font>Postnr(norsk):</td>
											<td><input type="text" onKeyPress="return numberKey(event)" required oninvalid="this.setCustomValidity('Obligatoriskt')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="postnr" id="postnr" size="5" maxlength="4" value='${model.record.postnr}'></td>
											<td class="text12" title="adr2">&nbsp;Postboks:</td>
											<td><input type="text" class="inputTextMediumBlue" name="adr2" id="adr2" size="20" maxlength="30" value='${model.record.adr2}'></td>
										</tr>
										<tr>
											<td class="text12" title="sypoge">&nbsp;Postnr(utlendsk):</td>
											<td><input type="text" class="inputTextMediumBlue" name="sypoge" id="sypoge" size="10" maxlength="9" value='${model.record.sypoge}'></td>
											<td class="text12" title="syland">&nbsp;Land:</td>
											<td><input type="text" class="inputTextMediumBlue" name="syland" id="syland" size="2" maxlength="2" value='${model.record.syland}'>
												<a tabindex="-1" id="sylandIdLink">
													<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="Søk" >
												</a>
											</td>
										</tr>
										<tr>
											<td class="text12" title="adr3">&nbsp;<font class="text14RedBold" >*</font>Postadresse:</td>
											<td><input type="text" required oninvalid="this.setCustomValidity('Obligatoriskt')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="adr3" id="adr3" size="25" maxlength="24" value='${model.record.adr3}'></td>
											<td class="text12" title="pnpbku">&nbsp;Postboksnr:</td>
											<td><input type="text" class="inputTextMediumBlue" name="pnpbku" id="pnpbku" size="10" maxlength="10" value='${model.record.pnpbku}'></td>
										</tr>
									</table>
								</td>
								<td width="50%" valign="top">&nbsp;
									<table border="0">
										<tr>
											<td class="text12" title="adr21">&nbsp;Adresse 3:</td>
											<td><input type="text" class="inputTextMediumBlue" name="adr21" id="adr21" size="25" maxlength="30" value='${model.record.adr21}'></td>
											<td class="text12" title="systat">&nbsp;State:</td>
											<td><input type="text" class="inputTextMediumBlue" name="systat" id="systat" size="3" maxlength="3" value='${model.record.systat}'></td>

										</tr>
										<tr>
											<td class="text12" title="kpers">&nbsp;Kontaktperson:</td>
											<td><input type="text" class="inputTextMediumBlue" name="kpers" id="kpers" size="25" maxlength="30" value='${model.record.kpers}'></td>
											<td class="text12" title="tlf">&nbsp;Telefon:</td>
											<td><input type="text" class="inputTextMediumBlue" name="tlf" id="tlf" size="10" maxlength="8" value='${model.record.tlf}'></td>
										</tr>
										<tr>
											<td class="text12" title="syepos">&nbsp;E-mail:</td>
											<td colspan="4"><input type="text" class="inputTextMediumBlue" name="syepos" id="syepos" size="60" maxlength="70" value='${model.record.syepos}'></td>

										</tr>
										<tr>
											<td class="text12" title="valkod">&nbsp;Valutakod:</td>
											<td><input type="text" class="inputTextMediumBlue" name="valkod" id="valkod" size="3" maxlength="3" value='${model.record.valkod}'>
												<a tabindex="-1" id="valkodIdLink">
													<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="Søk" >
												</a>
											</td>
											<td class="text12" title="kundgr">&nbsp;Kundegruppe:</td>
											<td><input type="text" class="inputTextMediumBlue" name="kundgr" id="kundgr" size="2" maxlength="2" value='${model.record.kundgr}'>
												<a tabindex="-1" id="kundgrIdLink">
													<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="Søk" >
												</a>
											</td>
										</tr>
										<tr>
											<td colspan="4">&nbsp;</td>
										</tr>
									</table>
								</td>
							</tr>

							<tr> 
								<td colspan="2" >&nbsp;
									<table class="formFrameHeaderPeachWithBorder" width="100%" 	cellspacing="0" border="0" align="center">
										<tr>
											<td class="text12Bold">
												&nbsp;Faktura 
											</td>
										</tr>
									</table>
									<table class="formFramePeachGrayRoundBottom"  width="100%" cellspacing="0" border="0" align="center">
										<tr> 
											<td width="50%" >
												<table border="0">
													<tr>
														<td class="text12" title="bankg">&nbsp;Bankgiro:</td>
														<td><input type="text" class="inputTextMediumBlue" name="bankg" id="bankg" size="20" maxlength="15" value='${model.record.bankg}'></td>
														<td class="text12" title="betbet">&nbsp;<font class="text14RedBold">*</font>Betalingsbetingelse:</td>
														<td><input type="text" onKeyPress="return numberKey(event)" required oninvalid="this.setCustomValidity('Obligatoriskt')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="betbet" id="betbet" size="5" maxlength="2" value='${model.record.betbet}'></td>
													</tr>
													<tr>
														<td class="text12" title="postg">&nbsp;Postgiro:</td>
														<td><input type="text" class="inputTextMediumBlue" name="postg" id="postg" size="20" maxlength="20" value='${model.record.postg}'></td>
														<td class="text12" title="betmat">&nbsp;Betaling:</td>
														<td><input type="text" class="inputTextMediumBlue" name="betmat" id="betmat" size="15" maxlength="17" value='${model.record.betmat}'></td>
			
													</tr>
													<tr>
														<td class="text12" title="sykont">&nbsp;Kontonummer:</td>
														<td><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="sykont" id="sykont" size="10" maxlength="7" value='${model.record.sykont}'></td>
														<td class="text12" title="kgrens">&nbsp;Kredittgrense:</td>
														<td><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="kgrens" id="kgrens" size="10" maxlength="7" value='${model.record.kgrens}'></td>
			
													</tr>
													<tr>
														<td class="text12" title="fmot">&nbsp;Fakturamottager:&nbsp;&nbsp;&nbsp;&nbsp;</td>
														<td><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="fmot" id="fmot" size="10" maxlength="8" value='${model.record.fmot}'></td>
														<td class="text12" title="sfakt">&nbsp;Samlefakturagrunnlag:</td>
														<td><input type="text" class="inputTextMediumBlue" name="sfakt" id="sfakt" size="1" maxlength="1" value='${model.record.sfakt}'></td>
													</tr>
												</table>
											</td>
											<td width="50%" valign="top">
												<table border="0">
													<tr>
														<td class="text12" title="sysalu">&nbsp;Saldo, ufaktureret:</td>
														<td><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="sysalu" id="sysalu" size="15" maxlength="13" value='${model.record.sysalu}'></td>
														<td class="text12" title="syfr03">&nbsp;Faktura, fritekstkode:</td>
														<td><input type="text" class="inputTextMediumBlue" name="syfr03" id="syfr03" size="5" maxlength="2" value='${model.record.syfr03}'></td>
													</tr>
													<tr>
														<td class="text12" title="xxinm3">&nbsp;Faktura, innland M3:</td>
														<td><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="xxinm3" id="xxinm3" size="5" maxlength="3" value='${model.record.xxinm3}'></td>
														<td class="text12" title="xxinlm">&nbsp;Faktura, innland LM:</td>
														<td><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="xxinlm" id="xxinlm" size="5" maxlength="4" value='${model.record.xxinlm}'></td>
													</tr>
													<tr>
														<td class="text12" title="rnraku">&nbsp;Mva nummer:</td>
														<td colspan="3"><input type="text" class="inputTextMediumBlue" name="rnraku" id="rnraku" size="20" maxlength="20" value='${model.record.rnraku}'></td>
													</tr>
													<tr>
														<td class="text12" title="symvjn">&nbsp;Momsregistrert:</td>
														<td><input type="text" class="inputTextMediumBlue" name="symvjn" id="symvjn" size="1" maxlength="1" value='${model.record.symvjn}'></td>
														<td class="text12" title="symvsp">&nbsp;Momsspecifikasjon:</td>
														<td><input type="text" class="inputTextMediumBlue" name="symvsp" id="symvsp" size="1" maxlength="1" value='${model.record.symvsp}'></td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</td>
							</tr>	

							<tr>
								<td colspan="2" >&nbsp;
									<table class="formFrameHeaderPeachWithBorder" width="100%" 	cellspacing="0" border="0" align="center">
										<tr>
											<td class="text12Bold">
												&nbsp;Vilkår 
											</td>
										</tr>
									</table>
									<table class="formFramePeachGrayRoundBottom"  width="100%" cellspacing="0" border="0" align="center">
										<tr> 
											<td width="50%" >
												<table border="0">
													<tr>
														<td class="text12" title="syutlp">&nbsp;Utlendsk provision:</td>
														<td><input type="text" onKeyPress="return amountKey(event)" class="inputTextMediumBlue" name="syutlp" id="syutlp" size="5" maxlength="4" value='${model.record.syutlp}'>%</td>
														<td class="text12" title="syminu">	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																								Utlendsk provision, min.:</td>
														<td><input type="text" onKeyPress="return amountKey(event)" class="inputTextMediumBlue" name="syminu" id="syminu" size="5" maxlength="4" value='${model.record.syminu}'>%</td>
													</tr>
													<tr>
														<td class="text12" title="syopdt">&nbsp;Oppdragstype:</td>
														<td><input type="text" class="inputTextMediumBlue" name="syopdt" id="syopdt" size="5" maxlength="2" value='${model.record.syopdt}'>
															<a tabindex="-1" id="syopdtIdLink">
																<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="Søk" >
															</a>
														</td>
														<td class="text12" title="sylikv">	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																								Likviditetskode:</td>
														<td><input type="text" class="inputTextMediumBlue" name="sylikv" id="sylikv" size="1" maxlength="1" value='${model.record.sylikv}'>
															<a tabindex="-1" id="sylikvIdLink">
																<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="Søk" >
															</a>
														</td>
													</tr>
												</table>
											</td>
											<td width="50%" valign="top">
												<table border="0">
													<tr>
														<td class="text12" title="golk">&nbsp;Godslokalkode:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
														</td>
														<td><input type="text" class="inputTextMediumBlue" name="golk" id="golk" size="5" maxlength="4" value='${model.record.golk}'>
															<a tabindex="-1" id="golkIdLink">
																<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="Søk" >
															</a>
														</td>
														<td class="text12" title="aktkod">	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																							Aktivitetskode:</td>
														<td><input type="text" class="inputTextMediumBlue" name="aktkod" id="aktkod" size="5" maxlength="2" value='${model.record.aktkod}'>
															<a tabindex="-1" id="aktkodIdLink">
																<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="Søk" >
															</a>
														</td>
													</tr>
													<tr>
														<td class="text12" title="dkund">&nbsp;Diversekunde:</td>
														<td><input type="text" class="inputTextMediumBlue" name="dkund" id="dkund" size="1" maxlength="1" value='${model.record.dkund}'></td>
														<td class="text12" title="syfr02">	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																							Tollkreditt:</td>
														<td><input type="text" class="inputTextMediumBlue" name="syfr02" id="syfr02" size="5" maxlength="4" value='${model.record.syfr02}'></td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</td>
							</tr>	

							<tr> 
								<td colspan="2" >&nbsp;
									<table class="formFrameHeaderPeachWithBorder" width="100%" 	cellspacing="0" border="0" align="center">
										<tr>
											<td class="text12Bold">
												&nbsp;Øvrig
											</td>
										</tr>
									</table>
									<table class="formFramePeachGrayRoundBottom"  width="100%" cellspacing="0" border="0" align="center">
										<tr> 
											<td width="50%" >
												<table border="0">
													<tr>
														<td class="text12" title="vatkku">&nbsp;VAT nummer:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
														</td>
														<td><input type="text" class="inputTextMediumBlue" name="vatkku" id="vatkku" size="20" maxlength="14" value='${model.record.vatkku}'></td>
														<td class="text12" title="syselg">&nbsp;Selgerkode:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
														</td>
														<td><input type="text" class="inputTextMediumBlue" name="syselg" id="syselg" size="5" maxlength="3" value='${model.record.syselg}'>
															<a tabindex="-1" id="syselgIdLink">
																<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="Søk" >
															</a>
														</td>
													</tr>
													<tr>
														<td class="text12" title="aknrku">&nbsp;Avtalekundenr:</td>
														<td><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="aknrku" id="aknrku" size="10" maxlength="8" value='${model.record.aknrku}'></td>
														<td class="text12" title="syfr04">&nbsp;Secured:</td>
														<td><input type="text" class="inputTextMediumBlue" name="syfr04" id="syfr04" size="5" maxlength="3" value='${model.record.syfr04}'></td>
													</tr>
													<tr>
														<td class="text12" title="syregn">&nbsp;Fylkeskattesjef:</td>
														<td><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="syregn" id="syregn" size="10" maxlength="8" value='${model.record.syregn}'></td>
														<td class="text12" title="syfr05">&nbsp;Samtak:</td>
														<td><input type="text" class="inputTextMediumBlue" name="syfr05" id="syfr05" size="5" maxlength="3" value='${model.record.syfr05}'></td>
													</tr>
												</table>
											</td>
											<td width="50%" valign="top">
												<table border="0">
													<tr>
														<td class="text12" title="syfr06">&nbsp;Free 06:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
														</td>
														<td><input type="text" onKeyPress="return amountKey(event)" class="inputTextMediumBlue" name="syfr06" id="syfr06" size="5" maxlength="4" value='${model.record.syfr06}'></td>
														<td class="text12" title="xxbre">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																								Breddegrad:</td>
														<td><input type="text" onKeyPress="return amountKey(event)" class="inputTextMediumBlue" name="xxbre" id="xxbre" size="10" maxlength="8" value='${model.record.xxbre}'></td>
													</tr>
													<tr>
														<td class="text12" title="syiat1">&nbsp;Iatakode 1:</td>
														<td><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="syiat1" id="syiat1" size="10" maxlength="7" value='${model.record.syiat1}'></td>
														<td class="text12" title="xxlen">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																								Lengdegrad:</td>
														<td><input type="text" class="inputTextMediumBlue" name="xxlen" id="xxlen" size="10" maxlength="9" value='${model.record.xxlen}'></td>
													</tr>
													<tr>
														<td class="text12" title="syiat2">&nbsp;Iatakode 2:</td>
														<td><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="syiat2" id="syiat2" size="5" maxlength="4" value='${model.record.syiat2}'></td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr><td colspan="2">&nbsp;</td></tr>
							<%-- Validation errors --%>
							<spring:hasBindErrors name="record"> <%-- name must equal the command object name in the Controller --%>
							<tr>
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
								<td >&nbsp;</td>
								<td align="right">
									<input class="inputFormSubmit" type="submit" name="submit" id="submit" value='Lagre'/>
								</td>
							</tr>

							<tr height="3">
								<td>&nbsp;</td>
							</tr>
						</table>
						
	 	    		</form>
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

