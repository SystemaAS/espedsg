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
							<font class="text11MediumBlue">[ ${kundeSessionParams.knavn} ]</font>
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
	 	    
			
			<%-- Other errors (none validation errors) --%>
			<c:if test="${not empty model.errorMessage}">
			<tr>
				<td width="5%">&nbsp;</td>
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
 	   	 	
 	   	 	<tr> <!-- Second tab row... -->
 	   	 		<td width="5%">&nbsp;</td>
 	   	 	    <td>
 					<table class="formFrameHeaderTransparent" width="1000" cellspacing="0" border="0" cellpadding="0">

						<tr height="20"> 

								<td width="80" valign="bottom" class="tab" align="center" nowrap>
									<font class="tabLinkMinor">&nbsp;Kunde</font>
								</td>


								<td width="80" valign="bottom" class="tabDisabled" align="center" title="Kontakt personer...">
									<a id="alinkMainMaintKontaktGate" onClick="setBlockUI(this);" href="mainmaintenancecundf_kontaktpersoner_list.do">
										<font class="tabDisabledLinkMinor">&nbsp;Kontakter</font>&nbsp;						
									</a>
								</td>

								<td width="80" valign="bottom" class="tabDisabled" align="center" title="Kontakt personer...">
									<a id="alinkMainMaintVareImpGate" onClick="setBlockUI(this);" href="mainmaintenancecundf_vareimp_edit.do">
										<font class="tabDisabledLinkMinor">&nbsp;Varer(import)</font>&nbsp;						
									</a>
								</td>

								<td width="80" valign="bottom" class="tabDisabled" align="center" title="Kontakt personer...">
									<a id="alinkMainMaintVareExpGate" onClick="setBlockUI(this);" href="mainmaintenancecundf_vareexp_edit.do">
										<font class="tabDisabledLinkMinor">&nbsp;Varer(export)</font>&nbsp;						
									</a>
								</td>



								<td width="80" valign="bottom" class="tabDisabled" align="center">
									<a id="alinkMainMaintFritextGate" onClick="setBlockUI(this);" href="mainmaintenancecundf_fritext_edit.do">
										<font class="tabDisabledLinkMinor">&nbsp;Fritext</font>&nbsp;						
									</a>
								</td>

								<td width="80" valign="bottom" class="tabDisabled" align="center">
									<a id="alinkMainMaintParamsGate" onClick="setBlockUI(this);" href="mainmaintenancecundf_params_edit.do">
										<font class="tabDisabledLinkMinor">&nbsp;Parametrar</font>&nbsp;						
									</a>
								</td>

								<td width="50" class="tabDisabledTrailingEnd"></td>

							 	<td width="475" class="tabFantomSpace" align="center" nowrap></td>


						</tr>
					</table>
				</td>
 	   	 	
 	   	 	
 	   	 	</tr> <!-- End second tab row -->
 
 
 	    	<tr>
 
				<td width="5%">&nbsp;</td>
				<td width="100%">
					<form action="mainmaintenancecundf_kunde_edit.do" name="formRecord" id="formRecord" method="POST" >
						<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
						<input type="hidden" name="action" id=action value="doUpdate">
						<input type="hidden" name="dirty" id=dirty value="">
						<table class="tabThinBorderWhite" width="95%" cellspacing="0" border="0" align="left">
							<tr height="3">
								<td>&nbsp;</td>
								<td width="3%">&nbsp;</td>
							</tr>
				    	    <tr>
				    	    	<td width="3%">&nbsp;</td>
								<td class="text12" title="kundnr">&nbsp;<font class="text14RedBold" >*</font>Kundenr</td>
								<td class="text12" title="knavn">&nbsp;<font class="text14RedBold" >*</font>Navn</td>
								<td class="text12" title="syrg">&nbsp;<font class="text14RedBold" >*</font>TIN / CVR/SE-nr</td>
								<td class="text12" title="adr1">&nbsp;<font class="text14RedBold" >*</font>Adresse</td>
								<td class="text12" title="adr1">&nbsp;Adresse2</td>
								
							</tr>
							<tr>
								<td width="3%">&nbsp;</td>
								<td ><input readonly type="text" class="inputTextReadOnly" name="kundnr" id="kundnr" size="10" maxlength="8" value='${model.record.kundnr}'></td>
								<td ><input type="text" class="inputTextMediumBlueMandatoryField" name="knavn" id="knavn" size="30" maxlength="30" value='${model.record.knavn}'></td>
								<td ><input type="text" class="inputTextMediumBlueMandatoryField" name="syrg" id="syrg" size="30" maxlength="14" value='${model.record.syrg}'></td>
								<td ><input type="text" class="inputTextMediumBlueMandatoryField" name="adr1" id="adr1" size="30" maxlength="30" value='${model.record.adr1}'></td>
								<td ><input type="text" class="inputTextMediumBlue" name="adr2" id="adr2" size="30" maxlength="30" value='${model.record.adr2}'></td>
								
							</tr>	
							<tr>
								<td width="3%">&nbsp;</td>
								<td class="text12" title="adr3">&nbsp;<font class="text14RedBold" >*</font>Postadresse</td>
								<td class="text12" title="postnr">&nbsp;<font class="text14RedBold" >*</font>Postnr(NO)</td>
								<td class="text12" title="syland">&nbsp;<font class="text14RedBold" >*</font>Land</td>
								<td class="text12" title="firma">&nbsp;<font class="text14RedBold" >*</font>Firma</td>
							</tr>	
							<tr>
								<td width="3%">&nbsp;</td>
								<td ><input type="text" class="inputTextMediumBlueMandatoryField" name="adr3" id="adr3" size="30" maxlength="30" value='${model.record.adr3}'></td>
								<td ><input type="text" class="inputTextMediumBlueMandatoryField" name="postnr" id="postnr" size="10" maxlength="4" value='${model.record.postnr}'></td>
								<td ><input type="text" class="inputTextMediumBlueMandatoryField" name="syland" id="syland" size="5" maxlength="2" value='${model.record.syland}'></td>
								<td ><input readonly type="text" class="inputTextReadOnly" name="firma" id="firma" size="5" maxlength="2" value='${model.record.firma}'></td>
							</tr>

							<tr>
								<td width="3%">&nbsp;</td>
								<td class="text12" title="adr3">&nbsp;Syland</td>
								<td class="text12" title="adr3">&nbsp;<font class="text14RedBold" >*</font>Sykont</td>
								<td class="text12" title="postnr">&nbsp;<font class="text14RedBold" >*</font>Syfr02</td>
							</tr>	

							<tr>
								<td width="3%">&nbsp;</td>
								<td ><input type="text" class="inputTextMediumBlue" name="adr3" id="adr3" size="30" maxlength="30" value='${model.record.syland}'></td>
								<td ><input type="text" class="inputTextMediumBlueMandatoryField" name="adr3" id="adr3" size="30" maxlength="30" value='${model.record.sykont}'></td>
								<td ><input type="text" class="inputTextMediumBlueMandatoryField" name="postnr" id="postnr" size="10" maxlength="4" value='${model.record.syfr02}'></td>

								<td>
									<input onClick="setBlockUI(this);" class="inputFormSubmit" type="submit" name="submit" id="submit" value='Lagre'/>
								</td>
		
							</tr>
							




							</tr>
							<tr height="3"><td>&nbsp;</td></tr>
						
							
						</table>
						
	 	    		</form>
				</td>
			</tr>
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
			
	 	    <tr height="20"><td>&nbsp;</td></tr>
	 	    
	 		</table>
		</td>
	</tr>
</table>	

<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->

