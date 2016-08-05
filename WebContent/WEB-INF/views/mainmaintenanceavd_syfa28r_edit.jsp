<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerMainMaintenance.jsp" />
<!-- =====================end header ==========================-->
	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/mainmaintenanceavd_syfa28r_edit.js?ver=${user.versionEspedsg}"></SCRIPT>	
	
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
					<td width="12%" valign="bottom" class="tabDisabled" align="center">
						<a id="alinkMainMaintAvdFasteData" onClick="setBlockUI(this);" href="mainmaintenanceavd_syfa14r_edit.do?avd=${model.avd}&updateId=${model.avd}">
							<font class="tabDisabledLink">&nbsp;Avd.</font>&nbsp;
							<font class="text11MediumBlue">(${model.avd})</font>
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="12%" valign="bottom" class="tab" align="center">
						<font class="tabLink">&nbsp;Faste data</font>&nbsp;
						<font class="text11MediumBlue">(${model.avd})</font>
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
						
						<table width="99%" 	cellspacing="1" border="0" align="left">
							<tr>
								<td valign="top" width="50%">
									<table	cellspacing="1" border="0" align="left">
									 	<tr>
											<td class="text12" >Prosjektregnskap</td>
											<td class="text12" title="todo">
												<select name="kovpro" id="kovpro" >
			 					  					<option value="">-velg-</option>
			 					  					<option value="J"<c:if test="${ Xmodel.record.kovpro == 'J'}"> selected </c:if> >J</option>
								  					<option value="N"<c:if test="${ Xmodel.record.kovpro == 'N'}"> selected </c:if> >N</option>
								  				</select>	
											</td>
										</tr>	
										<tr>
											<td class="text12" >Fr.brev/TP, R28.Belast</td>
											<td class="text12" title="todo">
												<select name="kovxxxFrBrev" id="kovxxxFrBrev" >
			 					  					<option value="">-velg-</option>
			 					  					<option value="S"<c:if test="${ Xmodel.record.kovxxxFrBrev == 'S'}"> selected </c:if> >S</option>
								  					<option value="M"<c:if test="${ Xmodel.record.kovxxxFrBrev == 'M'}"> selected </c:if> >M</option>
								  				</select>	
											</td>
										</tr>	
										<tr>
											<td class="text12" >Skriv fr.brev ved EDI</td>
											<td class="text12" title="todo">
												<select name="kovtodo" id="kovtodo" >
			 					  					<option value="">-velg-</option>
			 					  					<option value="J"<c:if test="${ Xmodel.record.kovtodo == 'J'}"> selected </c:if> >J</option>
								  					<option value="N"<c:if test="${ Xmodel.record.kovtodo == 'N'}"> selected </c:if> >N</option>
								  				</select>	
											</td>
										</tr>	
										<tr>
											<td class="text12" >Godslinjer på faktura</td>
											<td class="text12" title="todo">
												<select name="kovtodo" id="kovtodo" >
			 					  					<option value="">-velg-</option>
			 					  					<option value="J"<c:if test="${ Xmodel.record.kovtodo == 'J'}"> selected </c:if> >J</option>
								  					<option value="N"<c:if test="${ Xmodel.record.kovtodo == 'N'}"> selected </c:if> >N</option>
								  				</select>	
											</td>
										</tr>		
									</table>
								
								</td>
								<td valign="top" width="50%">
									<table cellspacing="1" border="0" align="left">
									 	<tr>
											<td class="text12" >Fet skr. på fly.fb</td>
											<td class="text12" title="todo">
												<select name="todo" id="todo" >
			 					  					<option value="">-velg-</option>
			 					  					<option value="J"<c:if test="${ Xmodel.record.todo == 'J'}"> selected </c:if> >J</option>
								  					<option value="N"<c:if test="${ Xmodel.record.todo == 'N'}"> selected </c:if> >N</option>
								  				</select>	
											</td>
										</tr>	
										<tr>
											<td class="text12" >Fritekstkode R38 FB/TP</td>
											<td class="text12" title="todo">
												<input type="text" class="inputTextMediumBlue" name="todo" id="todo" size="3" maxlength="2" value='${Xmodel.record.todo}'>
												/
												<input type="text" class="inputTextMediumBlue" name="todo" id="todo" size="3" maxlength="2" value='${Xmodel.record.todo}'>	
											</td>
										</tr>	
										<tr>
											<td class="text12" >Skriv Tollpass ved EDI</td>
											<td class="text12" title="todo">
												<select name="kovtodo" id="kovtodo" >
			 					  					<option value="">-velg-</option>
			 					  					<option value="J"<c:if test="${ Xmodel.record.kovtodo == 'J'}"> selected </c:if> >J</option>
								  					<option value="N"<c:if test="${ Xmodel.record.kovtodo == 'N'}"> selected </c:if> >N</option>
								  				</select>	
											</td>
										</tr>	
										<tr>
											<td class="text12" >Std OpdType (TR.modul)</td>
											<td class="text12" title="todo">
												<input type="text" class="inputTextMediumBlue" name="todo" id="todo" size="3" maxlength="2" value='${Xmodel.record.todo}'>	
											</td>
										</tr>		
									</table>
								</td>
							</tr>
							<tr height="5"><td >&nbsp;TODO more here!</td></tr>
						</table>
						<table class="formFrameHeader" width="90%" 	cellspacing="1" border="0" align="left">
							<tr>
								<td colspan="2" class="text12White" >&nbsp;Forteller til og med vekter i de enkelte frakt grupper - intervaller.&nbsp;&nbsp;En F under kiloene betyr at fast pris gjelder i intervallet
								</td>
							</tr>							
						</table>
						<table class="formFrame" width="90%" cellspacing="1" border="0" align="left">	
				    	    <tr>
								<td class="text12" title="KOVK1">&nbsp;1</td>
								<td class="text12" title="KOVK2">&nbsp;2</td>
								<td class="text12" title="KOVK3">&nbsp;3</td>
								<td class="text12" title="KOVK4">&nbsp;4</td>
								<td class="text12" title="KOVK5">&nbsp;5</td>
								<td class="text12" title="KOVK6">&nbsp;6</td>
								<td class="text12" title="KOVK7">&nbsp;7</td>
								<td class="text12" title="KOVK8">&nbsp;8</td>
								<td class="text12" title="KOVK9">&nbsp;9</td>
								<td class="text12" title="KOVK10">&nbsp;10</td>
								<td class="text12" title="KOVK11">&nbsp;11</td>
								<td class="text12" title="TODO?">&nbsp;12</td>
							</tr>
							<tr>
								<td ><input type="text" class="inputTextMediumBlue" name="kovk1" id="kovk1" size="6" maxlength="5" value='${Xmodel.record.kovk1}'></td>
								<td ><input type="text" class="inputTextMediumBlue" name="kovk2" id="kovk2" size="6" maxlength="5" value='${Xmodel.record.kovk2}'></td>
								<td ><input type="text" class="inputTextMediumBlue" name="kovk3" id="kovk3" size="6" maxlength="5" value='${Xmodel.record.kovk3}'></td>
								<td ><input type="text" class="inputTextMediumBlue" name="kovk4" id="kovk4" size="6" maxlength="5" value='${Xmodel.record.kovk4}'></td>
								<td ><input type="text" class="inputTextMediumBlue" name="kovk5" id="kovk5" size="6" maxlength="5" value='${Xmodel.record.kovk5}'></td>
								<td ><input type="text" class="inputTextMediumBlue" name="kovk6" id="kovk6" size="6" maxlength="5" value='${Xmodel.record.kovk6}'></td>
								<td ><input type="text" class="inputTextMediumBlue" name="kovk7" id="kovk7" size="6" maxlength="5" value='${Xmodel.record.kovk7}'></td>
								<td ><input type="text" class="inputTextMediumBlue" name="kovk8" id="kovk8" size="6" maxlength="5" value='${Xmodel.record.kovk8}'></td>
								<td ><input type="text" class="inputTextMediumBlue" name="kovk9" id="kovk9" size="6" maxlength="5" value='${Xmodel.record.kovk9}'></td>
								<td ><input type="text" class="inputTextMediumBlue" name="kovk10" id="kovk10" size="6" maxlength="5" value='${Xmodel.record.kovk10}'></td>
								<td ><input type="text" class="inputTextMediumBlue" name="kovk11" id="kovk11" size="6" maxlength="5" value='${Xmodel.record.kovk11}'></td>
								<td ><input type="text" class="inputTextMediumBlue" name="todo" id="todo" size="6" maxlength="5" value='${Xmodel.record.todo}'></td>
								
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

