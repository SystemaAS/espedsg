<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerMainMaintenance.jsp" />
<!-- =====================end header ==========================-->
	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/mainmaintenanceavd_syfa14r_edit.js?ver=${user.versionEspedsg}"></SCRIPT>	
	
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
							<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
								<a id="alinkMainMaintGate" tabindex=-1 style="display:block;" href="mainmaintenancegate.do">
								<font class="tabDisabledLink">&nbsp;Vedlikehold</font>
								<img style="vertical-align: middle;"  src="resources/images/list.gif" border="0" alt="general list">
								</a>
							</td>
							<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
							<td width="12%" valign="bottom" class="tabDisabled" align="center">
								<a id="alinkMainMaintAvdGate" onClick="setBlockUI(this);" href="mainmaintenanceavdgate.do?id=${model.dbTable}">
									<font class="tabDisabledLink">&nbsp;Avdelinger</font>&nbsp;						
									<img style="vertical-align: middle;"  src="resources/images/list.gif" border="0" alt="avd. gate list">
								</a>
							</td>
							<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
							<td width="12%" valign="bottom" class="tabDisabled" align="center">
								<a id="alinkMainMaintAvdSyfa14r" onClick="setBlockUI(this);" href="mainmaintenanceavd_syfa14r.do">
									<font class="tabDisabledLink">&nbsp;Gen. avd.</font>&nbsp;
									<img style="vertical-align: middle;"  src="resources/images/list.gif" border="0" alt="avd. general list">
								</a>
							</td>
							<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
							<td width="12%" valign="bottom" class="tab" align="center">
								<font class="tabLink">&nbsp;Avd.</font>&nbsp;
								<font class="text11MediumBlue">(${model.avd})</font>
							</td>
							
							<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
							<td width="12%" valign="bottom" class="tabDisabled" align="center">
								<a id="alinkMainMaintAvdFasteData" onClick="setBlockUI(this);" href="mainmaintenanceavd_syfa28r_edit.do?avd=${model.avd}&updateId=${model.avd}">
									<font class="tabDisabledLink">&nbsp;Faste data</font>&nbsp;
									<font class="text11MediumBlue">(${model.avd})</font>
								</a>
							</td>
							<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
							<td width="12%" valign="bottom" class="tabDisabled" align="center">
								<a id="alinkAvdHodeDok" onClick="setBlockUI(this);" href="mainmaintenanceavdhodedok.do?id=${model.dbTable}">
									<font class="tabDisabledLink">&nbsp;Hode på dok.</font>&nbsp;
									<font class="text11MediumBlue">(${model.avd})</font>
								</a>
							</td>
							<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
							<td width="12%" valign="bottom" class="tabDisabled" align="center">
								<a id="alinkAvdListHode" onClick="setBlockUI(this);" href="mainmaintenanceavdlisthode.do?id=${model.dbTable}">
									<font class="tabDisabledLink">&nbsp;Listehode</font>&nbsp;
									<font class="text11MediumBlue">(${model.avd})</font>
								</a>
							</td>
							<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
							<td width="12%" valign="bottom" class="tabDisabled" align="center">
								<a id="alinkAvdOppdTur" onClick="setBlockUI(this);" href="mainmaintenanceavdopptur.do?id=${model.dbTable}">
									<font class="tabDisabledLink">&nbsp;Oppnr og tur</font>&nbsp;
									<font class="text11MediumBlue">(${model.avd})</font>
								</a>
							</td>
							<td width="1%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
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
							<td width="13%" valign="bottom" class="tabDisabled" align="center">
								<a id="alinkMainMaintAvdSyfa14r" onClick="setBlockUI(this);" href="mainmaintenanceavd_syfa14r.do">
									<font class="tabDisabledLink">&nbsp;Gen. avd.</font>&nbsp;
									<img style="vertical-align: middle;"  src="resources/images/list.gif" border="0" alt="avd. general list">
								</a>
							</td>
							<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
							<td width="13%" valign="bottom" class="tab" align="center">
								<font class="tabLink">&nbsp;Lage ny avd.</font>&nbsp;
								<img style="vertical-align: middle;"  src="resources/images/add.png" width="12" height="12" border="0" alt="new">
							</td>
							<td width="47%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
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
						<input type="hidden" name="updateId" id=updateId value="${model.updateId}">
						<input type="hidden" name="action" id=action value="${model.action}">
						<table cellspacing="1" border="0" align="left">
				    	    <tr>
								<td class="text12" title="KOAAVD">&nbsp;<font class="text14RedBold" >*</font>Avd.</td>
								<td class="text12" title="KOANVN">&nbsp;<font class="text14RedBold" >*</font>Avd.navn</td>
								<td class="text12" title="KOAFIR">&nbsp;<font class="text14RedBold" >*</font>Firma</td>
								<td class="text12" title="KOAKNR">&nbsp;<font class="text14RedBold" >*</font>Kundenr.</td>
							</tr>
							<tr>
								
								<td >
									<c:choose>
										<c:when test="${not empty model.updateId}">
											<input readonly type="text" class="inputTextReadOnly" name="koaavd" id="koaavd" size="5" maxlength="4" value='${model.record.koaavd}'>
										</c:when>
										<c:otherwise>
											<input type="text" class="inputTextMediumBlueMandatoryField" name="koaavd" id="koaavd" size="5" maxlength="4" value='${model.record.koaavd}'>
										</c:otherwise>
									</c:choose>
								</td>
								<td ><input type="text" class="inputTextMediumBlueMandatoryField" name="koanvn" id="koanvn" size="35" maxlength="33" value='${model.record.koanvn}'></td>
								<td ><input readonly type="text" class="inputTextReadOnly" name="koafir" id="koafir" size="30" maxlength="59" value='${model.record.koafir}'></td>
								<td ><input type="text" class="inputTextMediumBlueMandatoryField" name="koaknr" id="koaknr" size="9" maxlength="8" value='${model.record.koaknr}'></td>
							</tr>
							<tr height="3"><td>&nbsp;</td></tr>
						</table>
						<table width="60%" 	cellspacing="1" border="0" align="left">
				    	    <tr>
								<td class="text12" title="KOABÆR">&nbsp;Bærer</td>
								<td class="text12" title="KOAKON">&nbsp;Sted</td>
								<td class="text12" title="KOAIAT">&nbsp;IATA kode</td>
								<td class="text12" title="KOAIA2">&nbsp;IATA kode 2</td>
								<td class="text12" title="KOAPOS">&nbsp;Pnr.fraktb.</td>
								<td class="text12" title="KOAIE">&nbsp;Imp/Eks.</td>
								<td class="text12" title="KOALK">&nbsp;Kun land</td>
								<td class="text12" title="NAVSG">&nbsp;Attesterer</td>

							</tr>
							<tr>
								<td ><input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="koabaer" id="koabaer" size="5" maxlength="4" value='${model.record.koabaer}'></td>
								<td ><input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="koakon" id="koakon" size="5" maxlength="4" value='${model.record.koakon}'></td>
								<td ><input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="koaiat" id="koaiat" size="8" maxlength="7" value='${model.record.koaiat}'></td>
								<td ><input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="koaia2" id="koaia2" size="5" maxlength="4" value='${model.record.koaia2}'></td>
								
								<td ><input type="text" class="inputTextMediumBlue" name="koapos" id="koapos" size="2" maxlength="1" value='${model.record.koapos}'></td>
								<td ><input type="text" class="inputTextMediumBlue" name="koaie" id="koaie" size="2" maxlength="1" value='${model.record.koaie}'></td>
								<td ><input type="text" class="inputTextMediumBlue" name="koalk" id="koalk" size="3" maxlength="2" value='${model.record.koalk}'></td>
								<td ><input type="text" class="inputTextMediumBlue" name="navsg" id="navsg" size="4" maxlength="3" value='${model.record.navsg}'></td>
								<td>
									<input class="inputFormSubmit" type="submit" name="submit" value='Lagre'/>
								</td>
							</tr>
							<tr height="5"><td >&nbsp;</td></tr>
						</table>
						<table class="formFrameHeader" width="60%" 	cellspacing="1" border="0" align="left">
							<tr><td colspan="15" class="text12White" >&nbsp;Brukere som skal motta melding om overførte "DUP"-oppdrag(tr.modul)</td></tr>
						</table>
						<table class="formFrame" width="60%" cellspacing="1" border="0" align="left">	
				    	    <tr>
								<td class="text12" title="KODUS1">&nbsp;Brukere1</td>
								<td class="text12" title="KODUS2">&nbsp;Brukere2</td>
								<td class="text12" title="KODUS3">&nbsp;Brukere3</td>
								<td class="text12" title="KODUS4">&nbsp;Brukere4</td>
								<td class="text12" title="KODUS5">&nbsp;Brukere5</td>
								<td class="text12" title="KODUS6">&nbsp;Brukere6</td>
							</tr>
							<tr>
								<td ><input type="text" class="inputTextMediumBlue" name="kodus1" id="kodus1" size="13" maxlength="10" value='${model.record.kodus1}'></td>
								<td ><input type="text" class="inputTextMediumBlue" name="kodus2" id="kodus2" size="13" maxlength="10" value='${model.record.kodus2}'></td>
								<td ><input type="text" class="inputTextMediumBlue" name="kodus3" id="kodus3" size="13" maxlength="10" value='${model.record.kodus3}'></td>
								<td ><input type="text" class="inputTextMediumBlue" name="kodus4" id="kodus4" size="13" maxlength="10" value='${model.record.kodus4}'></td>
								<td ><input type="text" class="inputTextMediumBlue" name="kodus5" id="kodus5" size="13" maxlength="10" value='${model.record.kodus5}'></td>
								<td ><input type="text" class="inputTextMediumBlue" name="kodus6" id="kodus6" size="13" maxlength="10" value='${model.record.kodus6}'></td>
							</tr>
							
						</table>
	 	    		</form>
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

