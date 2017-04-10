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
				<td width="95%">
					<form action="mainmaintenancearkiv_arc007_edit.do" name="formRecord" id="formRecord" method="POST" >
						<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
						<input type="hidden" name="updateId" id=updateId value="${model.updateId}">
						<input type="hidden" name="action" id=action value="${model.action}">
						<table  width="95%" cellspacing="1" border="0" align="left">
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
											<td ><input type="text" required oninvalid="this.setCustomValidity('Obligatoriskt')" onchange="setCustomValidity('')" class="inputTextMediumBlueMandatoryField"  name="artxt" id="artxt" size="20" maxlength="15" value='${model.record.artxt}'></td>
											<td class="text12" title="ARKJN">&nbsp;Arkiver:</td>
											<td class="text12" >
												<select name="arkjn" id="arkjn" class="inputTextMediumBlue">
								  					<option value="">-<spring:message code="systema.choose"/>-</option>
								  					<option value="J"<c:if test="${ model.record.arkjn == 'J'}"> selected </c:if> ><spring:message code="systema.yes"/></option>
								  					<option value="N"<c:if test="${ model.record.arkjn == 'N'}"> selected </c:if> ><spring:message code="systema.no"/></option>
								  					
											  	</select>
											</td>
											<td class="text12" title="ARKSND">&nbsp;Ep:</td>
											<td class="text12" >
												<select name="arksnd" id="arksnd" class="inputTextMediumBlue">
								  					<option value="">-<spring:message code="systema.choose"/>-</option>
								  					<option value="J"<c:if test="${ model.record.arksnd == 'J'}"> selected </c:if> ><spring:message code="systema.yes"/></option>
								  					<option value="N"<c:if test="${ model.record.arksnd == 'N'}"> selected </c:if> ><spring:message code="systema.no"/></option>
								  					
											  	</select>
											</td>
											<td class="text12" title="ARKLAG">&nbsp;Mappe:</td>
											<td><input type="text" class="inputTextMediumBlue"  name="arklag" id="arklag" size="2" maxlength="2" value='${model.record.arklag}'>
												<a tabindex="-1" id="arklagIdLink">
													<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="Søk" >
												</a>
											</td>
											<td><input readonly type="text" class="inputTextReadOnly" name="arcane" id="arcane" size="10" maxlength="10" value='${model.record.artype}'></td>
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
											<td class="text12Bold">&nbsp;
												Vedlegg
											</td>
											<td align="right">&nbsp;Ref.
												<a tabindex="-1" id="arkvedRefLink">
													<img style="cursor:pointer;vertical-align:middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="Søk" >
												</a>
												&nbsp;&nbsp;
											</td>	
											
										</tr>
									</table>
									<table class="formFramePeachGrayRoundBottom"  width="100%" cellspacing="0" border="0" align="center">
										<tr> 
											<td>
												<table>
													<tr>
														<td class="text12" title="arkved">&nbsp;
															Vedlegg:
														</td>
														<td><input type="text" class="inputTextMediumBlue" name="avkved1" id="avkved1" size="2" maxlength="2" value='${model.record.arkved}'></td>
														<td><input type="text" class="inputTextMediumBlue" name="avkved2" id="avkved2" size="2" maxlength="2" value='${model.record.arkved}'></td>
														<td><input type="text" class="inputTextMediumBlue" name="avkved3" id="avkved3" size="2" maxlength="2" value='${model.record.arkved}'></td>
														<td><input type="text" class="inputTextMediumBlue" name="avkved4" id="avkved4" size="2" maxlength="2" value='${model.record.arkved}'></td>
														<td><input type="text" class="inputTextMediumBlue" name="avkved5" id="avkved5" size="2" maxlength="2" value='${model.record.arkved}'></td>
														<td><input type="text" class="inputTextMediumBlue" name="avkved6" id="avkved6" size="2" maxlength="2" value='${model.record.arkved}'></td>
														<td><input type="text" class="inputTextMediumBlue" name="avkved7" id="avkved7" size="2" maxlength="2" value='${model.record.arkved}'></td>
														<td><input type="text" class="inputTextMediumBlue" name="avkved8" id="avkved8" size="2" maxlength="2" value='${model.record.arkved}'></td>
														<td><input type="text" class="inputTextMediumBlue" name="avkved9" id="avkved9" size="2" maxlength="2" value='${model.record.arkved}'></td>
														<td><input type="text" class="inputTextMediumBlue" name="avkved10" id="avkved10" size="2" maxlength="2" value='${model.record.arkved}'></td>
														<td><input type="text" class="inputTextMediumBlue" name="avkved11" id="avkved11" size="2" maxlength="2" value='${model.record.arkved}'></td>
														<td><input type="text" class="inputTextMediumBlue" name="avkved12" id="avkved12" size="2" maxlength="2" value='${model.record.arkved}'></td>
														<td><input type="text" class="inputTextMediumBlue" name="avkved13" id="avkved13" size="2" maxlength="2" value='${model.record.arkved}'></td>
														<td><input type="text" class="inputTextMediumBlue" name="avkved14" id="avkved14" size="2" maxlength="2" value='${model.record.arkved}'></td>
														<td><input type="text" class="inputTextMediumBlue" name="avkved15" id="avkved15" size="2" maxlength="2" value='${model.record.arkved}'></td>
													</tr>
													<tr>
														<td class="text12" title="arkved">&nbsp;
															Vedlegg:
														</td>
														<td><input type="text" class="inputTextMediumBlue" name="avkved16" id="avkved16" size="2" maxlength="2" value='${model.record.arkved}'></td>
														<td><input type="text" class="inputTextMediumBlue" name="avkved17" id="avkved17" size="2" maxlength="2" value='${model.record.arkved}'></td>
														<td><input type="text" class="inputTextMediumBlue" name="avkved18" id="avkved18" size="2" maxlength="2" value='${model.record.arkved}'></td>
														<td><input type="text" class="inputTextMediumBlue" name="avkved19" id="avkved19" size="2" maxlength="2" value='${model.record.arkved}'></td>
														<td><input type="text" class="inputTextMediumBlue" name="avkved20" id="avkved20" size="2" maxlength="2" value='${model.record.arkved}'></td>
														<td><input type="text" class="inputTextMediumBlue" name="avkved21" id="avkved21" size="2" maxlength="2" value='${model.record.arkved}'></td>
														<td><input type="text" class="inputTextMediumBlue" name="avkved22" id="avkved22" size="2" maxlength="2" value='${model.record.arkved}'></td>
														<td><input type="text" class="inputTextMediumBlue" name="avkved23" id="avkved23" size="2" maxlength="2" value='${model.record.arkved}'></td>
														<td><input type="text" class="inputTextMediumBlue" name="avkved24" id="avkved24" size="2" maxlength="2" value='${model.record.arkved}'></td>
														<td><input type="text" class="inputTextMediumBlue" name="avkved25" id="avkved25" size="2" maxlength="2" value='${model.record.arkved}'></td>
														<td><input type="text" class="inputTextMediumBlue" name="avkved26" id="avkved26" size="2" maxlength="2" value='${model.record.arkved}'></td>
														<td><input type="text" class="inputTextMediumBlue" name="avkved27" id="avkved27" size="2" maxlength="2" value='${model.record.arkved}'></td>
														<td><input type="text" class="inputTextMediumBlue" name="avkved28" id="avkved28" size="2" maxlength="2" value='${model.record.arkved}'></td>
														<td><input type="text" class="inputTextMediumBlue" name="avkved29" id="avkved29" size="2" maxlength="2" value='${model.record.arkved}'></td>
														<td><input type="text" class="inputTextMediumBlue" name="avkved30" id="avkved30" size="2" maxlength="2" value='${model.record.arkved}'></td>
													</tr>
													<tr>
														<td class="text12" title="arvedl">&nbsp;
															Vedleg til Epost:
														</td>													
														<td colspan="8"><input type="text" class="inputTextMediumBlue" name="arvedl" id="arvedl" size="45" maxlength="40" value='${model.record.arvedl}'></td>
														<td colspan="3" class="text12" title="armrg">&nbsp;
															Slå sammen PDF:
														</td>
														<td colspan="2">
															<select name="armrg" id="armrg" >
						 					  					<option value="">-<spring:message code="systema.choose"/>-</option>
						 					  					<option value="J"<c:if test="${model.record.armrg == 'J'}"> selected </c:if>><spring:message code="systema.yes"/></option>
											  					<option value="N"<c:if test="${model.record.armrg == 'N'}"> selected </c:if>><spring:message code="systema.no"/></option>
											  				</select>
														</td>
													</tr>
													
												</table>
											</td>
										</tr>
									</table>
								</td> <!-- End second row -->
							</tr>	
							
							<tr>
								<td>&nbsp; <!-- Third row -->						
									<table class="formFrameHeaderPeachWithBorder" width="100%" 	cellspacing="0" border="0" align="center">
										<tr>
											<td class="text12Bold">&nbsp;
												Skanning
											</td>
										</tr>
									</table>
										<table class="formFramePeachGrayRoundBottom"  width="100%" cellspacing="0" border="0" align="center">
										<tr> 
											<td>
												<table>
													<tr>
														<td class="text12" title="arsban">&nbsp;
															Opplastingsbane:
														</td>													
														<td colspan="8"><input type="text" class="inputTextMediumBlue" name="arsban" id="arsban" size="55" maxlength="50" value='${model.record.arsban}'></td>
														<td  class="text12" title="arsfsk">&nbsp;
															Fjern skilleark:
														</td>
														<td>
															<select name="arsfsk" id="arsfsk" >
						 					  					<option value="">-<spring:message code="systema.choose"/>-</option>
						 					  					<option value="J"<c:if test="${model.record.arsfsk == 'J'}"> selected </c:if>><spring:message code="systema.yes"/></option>
											  					<option value="N"<c:if test="${model.record.arsfsk == 'N'}"> selected </c:if>><spring:message code="systema.no"/></option>
											  				</select>
														</td>
														<td  class="text12" title="arscts">&nbsp;
															Tidsstempel:
														</td>
														<td>
															<select name="arscts" id="arscts" >
						 					  					<option value="">-<spring:message code="systema.choose"/>-</option>
						 					  					<option value="J"<c:if test="${model.record.arscts == 'J'}"> selected </c:if>><spring:message code="systema.yes"/></option>
											  					<option value="N"<c:if test="${model.record.arscts == 'N'}"> selected </c:if>><spring:message code="systema.no"/></option>
											  				</select>
														</td>
														<td  class="text12" title="arslab">&nbsp;
															Merke skilleark:
														</td>
														<td>
															<select name="arslab" id="arslab" >
						 					  					<option value="">-<spring:message code="systema.choose"/>-</option>
						 					  					<option value="J"<c:if test="${model.record.arslab == 'J'}"> selected </c:if>><spring:message code="systema.yes"/></option>
											  					<option value="N"<c:if test="${model.record.arslab == 'N'}"> selected </c:if>><spring:message code="systema.no"/></option>
											  				</select>
														</td>
													</tr>
													<tr>
														<td  class="text12Gray">&nbsp;
															Spesial regler:
														</td>												
													</tr>
													<tr>
														<td colspan="3" class="text12" title="arsrle">&nbsp;
															Lengde:
														</td>													
														<td><input type="text" class="inputTextMediumBlue" name="arsrle" id="arsrle" size="5" maxlength="2" value='${model.record.arsrle}'></td>
														<td class="text12" title="arsrpa">&nbsp;&nbsp;
															Mønster:
														</td>													
														<td><input type="text" class="inputTextMediumBlue" name="arsrpa" id="arsrpa" size="7" maxlength="2" value='${model.record.arsrpa}'></td>
														<td class="text12" title="arsrst">&nbsp;&nbsp;
															I position:
														</td>													
														<td><input type="text" class="inputTextMediumBlue" name="arsrst" id="arsrst" size="5" maxlength="2" value='${model.record.arsrst}'></td>
														<td  class="text12" title="arsrno">&nbsp;
															Numerisk:
														</td>
														<td>
															<select name="arsrno" id="arsrno" >
						 					  					<option value="">-<spring:message code="systema.choose"/>-</option>
						 					  					<option value="J"<c:if test="${model.record.arsrno == 'J'}"> selected </c:if>><spring:message code="systema.yes"/></option>
											  					<option value="N"<c:if test="${model.record.arsrno == 'N'}"> selected </c:if>><spring:message code="systema.no"/></option>
											  				</select>
														</td>
													</tr>													
												</table>
											</td>
										</tr>
									</table>
								</td> <!-- End third row -->
							</tr>							
							
							<tr height="3"><td>&nbsp;</td></tr>
							
							<tr> 
								<td align="right">
									<input class="inputFormSubmit" type="submit" name="submit" id="submit" value='<spring:message code="systema.save"/>'/>
								</td>
							</tr>

							<tr height="3"><td>&nbsp;</td></tr>
							
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

