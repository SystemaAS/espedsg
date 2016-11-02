<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerMainMaintenance.jsp" />
<!-- =====================end header ==========================-->
	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/mainmaintenancecundf_vkund_edit.js?ver=${user.versionEspedsg}"></SCRIPT>	
	
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
					<c:when test="${not empty model.updateId}">
						<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
						<td width="15%" valign="bottom" class="tab" align="center">
							<font class="tabLink">&nbsp;Kunde</font>&nbsp;
							<font class="text11MediumBlue">(${model.kundnr})</font>
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
				<td width="100%">

					<table class="formFrameTitaniumGrayRoundBottom" width="95%" class="text11" cellspacing="0" border="0" cellpadding="0">
						<tr><td colspan="15" class="text12White" >&nbsp;Kunde informasjon</td></tr>
						<tr height="2"><td></td></tr>
							<tr height="25"> 
								<td width="8%" valign="bottom" class="tabDisabled" align="center" nowrap title="Mer kundedata...">
									<a id="alinkMainKundeGate" tabindex=-1 style="display:block;" href="mainmaintenancecundf_kunde_edit.do" >
									<font class="tabDisabledLink">&nbsp;Kunde</font>
									</a>
								</td>

								<td width="8%" valign="bottom" class="tabDisabled" align="center" title="Kontakt personer...">
									<a id="alinkMainMaintKontaktGate" onClick="setBlockUI(this);" href="mainmaintenancecundf_kontaktpersoner_edit.do">
										<font class="tabDisabledLink">&nbsp;Kontakter</font>&nbsp;						
									</a>
								</td>

								<td width="8%" valign="bottom" class="tabDisabled" align="center">
									<a id="alinkMainMaintFritextGate" onClick="setBlockUI(this);" href="mainmaintenancecundf_fritext_edit.do">
										<font class="tabDisabledLink">&nbsp;Fritext</font>&nbsp;						
									</a>
								</td>

								<td width="8%" valign="bottom" class="tabDisabled" align="center">
									<a id="alinkMainMaintParamsGate" onClick="setBlockUI(this);" href="mainmaintenancecundf_params_edit.do">
										<font class="tabDisabledLink">&nbsp;Parametrar</font>&nbsp;						
									</a>
								</td>

								<td width="8%" valign="bottom" class="tabDisabled" align="center">
									<a id="alinkMainMaintxxxGate" onClick="setBlockUI(this);" href="mainmaintenancecundf_xxx_edit.do">
										<font class="tabDisabledLink">&nbsp;Fane 5</font>&nbsp;						
									</a>
								</td>

								<td width="8%" valign="bottom" class="tabDisabled" align="center">
									<a id="alinkMainMaintxxxGate" onClick="setBlockUI(this);" href="mainmaintenancecundf_xxx_edit.do">
										<font class="tabDisabledLink">&nbsp;Fane 6</font>&nbsp;						
									</a>
								</td>

								<td width="8%" valign="bottom" class="tabDisabled" align="center">
									<a id="alinkMainMaintxxxGate" onClick="setBlockUI(this);" href="mainmaintenancecundf_xx_edit.do">
										<font class="tabDisabledLink">&nbsp;Fane 7</font>&nbsp;						
									</a>
								</td>

								<td width="8%" valign="bottom" class="tabDisabled" align="center">
									<a id="alinkMainMaintxxxGate" onClick="setBlockUI(this);" href="mainmaintenancecundf_xx_edit.do">
										<font class="tabDisabledLink">&nbsp;Fane 8</font>&nbsp;						
									</a>
								</td>
	
								<td width="8%" valign="bottom" class="tabDisabled" align="center">
									<a id="alinkMainMaintxxxGate" onClick="setBlockUI(this);" href="mainmaintenancecundf_xx_edit.do">
										<font class="tabDisabledLink">&nbsp;Fane 9</font>&nbsp;						
									</a>
								</td>

								<td width="8%" valign="bottom" class="tabDisabled" align="center">
									<a id="alinkMainMaintxxxGate" onClick="setBlockUI(this);" href="mainmaintenancecundf_xx_edit.do">
										<font class="tabDisabledLink">&nbsp;Fane 10</font>&nbsp;						
									</a>
								</td>

								<td width="8%" valign="bottom" class="tabDisabled" align="center">
									<a id="alinkMainMaintAvdGate" onClick="setBlockUI(this);" href="mainmaintenancecundf_xx_edit.do">
										<font class="tabDisabledLink">&nbsp;Fane 11</font>&nbsp;						
									</a>
								</td>

								<td width="8%" valign="bottom" class="tabDisabled" align="center">
									<a id="alinkMainMaintxxxGate" onClick="setBlockUI(this);" href="mainmaintenancecundf_xx_edit.do">
										<font class="tabDisabledLink">&nbsp;Fane 12</font>&nbsp;						
									</a>
								</td>

							</tr>
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

