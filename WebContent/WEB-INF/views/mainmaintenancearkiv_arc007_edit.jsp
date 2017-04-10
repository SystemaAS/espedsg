<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerMainMaintenance.jsp" />
<!-- =====================end header ==========================-->
	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/mainmaintenancearkiv_arc007_edit.js?ver=${user.versionEspedsg}"></SCRIPT>	
	
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
							<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
								<a id="alinkMainMaintGate" tabindex=-1 style="display:block;" href="mainmaintenancegate.do">
								<font class="tabDisabledLink">&nbsp;Vedlikehold</font>
								<img style="vertical-align: middle;"  src="resources/images/list.gif" border="0" alt="general list">
								</a>
							</td>
							<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
							<td width="15%" valign="bottom" class="tabDisabled" align="center">
								<a id="alinkMainMaintArkivGate" onClick="setBlockUI(this);" href="mainmaintenancearkivgate.do?id=${model.dbTable}">
									<font class="tabDisabledLink">&nbsp;Arkiv</font>&nbsp;						
									<img style="vertical-align: middle;"  src="resources/images/list.gif" border="0" alt="arkiv gate list">
								</a>
							</td>
							<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
							<td width="15%" valign="bottom" class="tabDisabled" align="center">
								<a id="alinkMainMaintArkivArc007" onClick="setBlockUI(this);" href="mainmaintenancearkiv_arc007.do">
									<font class="tabDisabledLink">&nbsp;Dokumenter</font>&nbsp;
									<img style="vertical-align: middle;"  src="resources/images/list.gif" border="0" alt="arkiv general list">
								</a>
							</td>
							<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
							<td width="15%" valign="bottom" class="tab" align="center">
								<font class="tabLink">&nbsp;Dokument</font>&nbsp;
								<font class="text11MediumBlue">(${model.record.artype})</font>
							</td>
							
							<td width="40%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
							
						</c:when>
						<c:otherwise>
							<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
								<a id="alinkMainMaintGate" tabindex=-1 style="display:block;" href="mainmaintenancegate.do">
								<font class="tabDisabledLink">&nbsp;Vedlikehold</font>
								<img style="vertical-align: middle;"  src="resources/images/list.gif" border="0" alt="general list">
								</a>
							</td>
							<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
							<td width="15%" valign="bottom" class="tabDisabled" align="center">
								<a id="alinkMainMaintArkivGate" onClick="setBlockUI(this);" href="mainmaintenancearkivgate.do?id=${model.dbTable}">
									<font class="tabDisabledLink">&nbsp;Arkiv</font>&nbsp;						
									<img style="vertical-align: middle;"  src="resources/images/list.gif" border="0" alt="avd. gate list">
								</a>
							</td>
							<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
							<td width="15%" valign="bottom" class="tabDisabled" align="center">
								<a id="alinkMainMaintArkivArc007" onClick="setBlockUI(this);" href="mainmaintenancearkiv_arc007.do">
									<font class="tabDisabledLink">&nbsp;Dokumenter</font>&nbsp;
									<img style="vertical-align: middle;"  src="resources/images/list.gif" border="0" alt="avd. general list">
								</a>
							</td>
							<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
							<td width="15%" valign="bottom" class="tab" align="center">
								<font class="tabLink">&nbsp;Lage ny</font>&nbsp;
								<img style="vertical-align: middle;"  src="resources/images/add.png" width="12" height="12" border="0" alt="new">
							</td>
							
							<td width="40%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
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
					<form action="mainmaintenancearkiv_arc007_edit.do" name="formRecord" id="formRecord" method="POST" >
						<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
						<input type="hidden" name="updateId" id=updateId value="${model.updateId}">
						<input type="hidden" name="action" id=action value="${model.action}">
						<table  width="100%" cellspacing="1" border="1" align="left">
							<tr>
								<td> <!-- First row -->
									<table>
										<tr>
											<td class="text12" title="ARTYPE">&nbsp;<font class="text14RedBold" >*</font>Kode:</td>
											<td >
												<c:choose>
													<c:when test="${not empty model.updateId}">
														<input readonly type="text" class="inputTextReadOnly" name="artype" id="artype" size="3" maxlength="2" value='${model.record.artype}'>
													</c:when>
													<c:otherwise>
														<input type="text" required oninvalid="this.setCustomValidity('Obligatoriskt')" onchange="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="artype" id="artype" size="3" maxlength="2" value='${model.record.artype}'>
													</c:otherwise>
												</c:choose>
											</td>
											<td class="text12" title="ARTXT">&nbsp;<font class="text14RedBold" >*</font>Tekst:</td>
											<td ><input type="text" required oninvalid="this.setCustomValidity('Obligatoriskt')" onchange="setCustomValidity('')" class="inputTextMediumBlueMandatoryField"  name="artxt" id="artxt" size="15" maxlength="15" value='${model.record.artxt}'></td>
											<td class="text12" title="ARKJN">&nbsp;Arkiver:</td>
											<td class="text12" >
												<select name="arkjn" id="arkjn" class="inputTextMediumBlue">
								  					<option value="">-velg-</option>
								  					<option value="J"<c:if test="${ model.record.arkjn == 'J'}"> selected </c:if> >Ja</option>
								  					<option value="N"<c:if test="${ model.record.arkjn == 'N'}"> selected </c:if> >Nei</option>
								  					
											  	</select>
											</td>
											<td class="text12" title="ARKSND">&nbsp;Ep:</td>
											<td><input type="text" class="inputTextMediumBlue"  name="arksnd" id="arksnd" size="1" maxlength="1" value='${model.record.arksnd}'></td>
											<td class="text12" title="ARKLAG">&nbsp;Mappe:</td>
											<td><input type="text" class="inputTextMediumBlue"  name="arklag" id="arklag" size="2" maxlength="2" value='${model.record.arklag}'>
												<a tabindex="-1" id="arklagIdLink">
													<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="Søk" >
												</a>
											</td>
											<td class="text12" title="ARKDAG">&nbsp;</font>Lagres(dager)</td>
											<td><input type="text" class="inputTextMediumBlue"  name="arkdag" id="arkdag" size="5" maxlength="5" value='${model.record.arkdag}'></td>
										</tr>		
									</table>
								</td> <!-- End first row -->
							</tr>
							
							<tr>
								<td>&nbsp; <!-- Second row -->
									<table class="formFrameHeaderPeachWithBorder" width="100%" 	cellspacing="0" border="0" align="center">
										<tr>
											<td class="text12Bold">
												Vedlegg
											</td>
										</tr>
									</table>
									<table class="formFramePeachGrayRoundBottom"  width="100%" cellspacing="0" border="0" align="center">
										<tr> 
											<td>
												<table border="0">
													<tr>
														<td class="text12" title="bankg">&nbsp;
															label 1
														</td>
														<td><input type="text" class="inputTextMediumBlue" name="bankg" id="bankg" size="20" maxlength="15" value='${model.record.arkdag}'></td>
														<td class="text12" title="betbet">&nbsp;
															label 2
														</td>
														<td><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="betbet" id="betbet" size="5" maxlength="2" value='${model.record.arkdag}'></td>
													</tr>
													<tr>
														<td class="text12" title="fmot">&nbsp;
															label 3
															&nbsp;&nbsp;&nbsp;&nbsp;
														</td>
														<td><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="fmot" id="fmot" size="10" maxlength="8" value='${model.record.arkdag}'></td>
														<td class="text12" title="sfakt">&nbsp;
															label 4
														</td>
														<td><input type="text" class="inputTextMediumBlue" name="sfakt" id="sfakt" size="1" maxlength="1" value='${model.record.arkdag}'></td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</td>
							</tr>		
							<tr> 
								<td align="right">
									<input class="inputFormSubmit" type="submit" name="submit" id="submit" value='<spring:message code="systema.save"/>'/>
								</td>
							</tr>

							<tr height="3">
								<td>&nbsp;</td>
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

