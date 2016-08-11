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
					<form action="mainmaintenanceavd_syfa28r_edit.do" name="formRecord" id="formRecord" method="POST" >
						<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
						<input type="hidden" name="updateId" id=updateId value="${model.updateId}">
						<input type="hidden" name="action" id=action value="${model.action}">
						<input type="hidden" name="avd" id="avd" value="${model.avd}">
						<input type="hidden" name="kovavd" id="kovavd" value="${model.record.kovavd}">
						
						<table width="99%" 	cellspacing="1" border="0" align="left">
							<tr>
								<td valign="top" width="90%">
									<table	cellspacing="1" border="0" align="left">
									 	<tr>
											<td class="text12" title="kovpro">Prosjektregnskap</td>
											<td class="text12" >
												<select name="kovpro" id="kovpro" >
			 					  					<option value="">-velg-</option>
			 					  					<option value="J"<c:if test="${ model.record.kovpro == 'J'}"> selected </c:if> >Ja</option>
								  					<option value="N"<c:if test="${ model.record.kovpro == 'N'}"> selected </c:if> >Nej</option>
								  				</select>	
											</td>
											<td width="100px" class="text12" >&nbsp;</td>
											<td class="text12" title="kowxxx0">Fet skr. på fly.fb</td>
											<td class="text12" >
												<select name="kowxxx0" id="kowxxx0" >
			 					  					<option value="">-velg-</option>
			 					  					<option value="J"<c:if test="${ fn:substring(model.record.kowxxx, 0, 1) == 'J'}"> selected </c:if> >Ja</option>
								  					<option value="N"<c:if test="${ fn:substring(model.record.kowxxx, 0, 1) == 'N'}"> selected </c:if> >Nej</option>
								  				</select>	
											</td>
										</tr>	
										<tr>
											<td class="text12" title="kovxxx0">Fr.brev/TP, R28.Belast</td>
											<td class="text12">
												<select name="kovxxx0" id="kovxxx0" >
			 					  					<option value="">-velg-</option>
			 					  					<option value="S"<c:if test="${ fn:substring(model.record.kovxxx, 0, 1) == 'S'}"> selected </c:if> >S</option>
								  					<option value="M"<c:if test="${ fn:substring(model.record.kovxxx, 0, 1) == 'M'}"> selected </c:if> >M</option>
								  				</select>	
											</td>
											<td width="100px" class="text12" >&nbsp;</td>
											<td class="text12" title="kovxxx5/kovxxx1">Fritekstkode R38 FB/TP</td>
											<td class="text12" >
												<input type="text" class="inputTextMediumBlue" name="kovxxx5" id="kovxxx5" size="3" maxlength="2" value="${fn:substring(model.record.kovxxx, 5, 7)}">
												/
												<input type="text" class="inputTextMediumBlue" name="kovxxx1" id="kovxxx1" size="3" maxlength="2" value="${fn:substring(model.record.kovxxx, 1, 3)}">	
											</td>

										</tr>	
										<tr>
											<td class="text12" title="kovxxx3">Skriv fr.brev ved EDI</td>
											<td class="text12"> 
												<select name="kovxxx3" id="kovxxx3" >
			 					  					<option value="">-velg-</option>
			 					  					<option value="J"<c:if test="${ fn:substring(model.record.kovxxx, 3, 4) == 'J'}"> selected </c:if> >Ja</option>
								  					<option value="N"<c:if test="${ fn:substring(model.record.kovxxx, 3, 4) == 'N'}"> selected </c:if> >Nej</option>
								  				</select>	
											</td>
											<td width="100px" class="text12" >&nbsp;</td>
											<td class="text12" title="kovxxx4">Skriv Tollpass ved EDI</td>
											<td class="text12" >
												<select name="kovxxx4" id="kovxxx4" >
			 					  					<option value="">-velg-</option>
			 					  					<option value="J"<c:if test="${ fn:substring(model.record.kovxxx, 4, 5) == 'J'}"> selected </c:if> >Ja</option>
								  					<option value="N"<c:if test="${ fn:substring(model.record.kovxxx, 4, 5) == 'N'}"> selected </c:if> >Nej</option>
								  				</select>	
											</td>
										</tr>	
										<tr>
											<td class="text12" title="kowxxx1">Godslinjer på faktura</td>
											<td class="text12" >
												<input type="text" class="inputTextMediumBlue" name="kowxxx1" id="kowxxx1" size="3" maxlength="1" value='${ fn:substring(model.record.kowxxx, 1, 2) }' />
											<td width="100px" class="text12" >&nbsp;</td>
											<td class="text12" title="kowxxx2">Std OpdType (TR.modul)</td>
											<td class="text12" >
												<input type="text" class="inputTextMediumBlue" name="kowxxx2" id="kowxxx2" size="3" maxlength="2" value='${ fn:substring(model.record.kowxxx, 2, 4) }' />	
											</td>
											
										</tr>
										<tr>
											<td class="text12" title="kovlkg">Omr.faktor 1 Lastemeter</td>
											<td class="text12" >
												<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="kovlkg" id="kovlkg" size="6" maxlength="5" value='${model.record.kovlkg}'>&nbsp;Kg.	
											</td>
											<td width="100px" class="text12" >&nbsp;</td>
											<td class="text12" title="kovkkg">Omr.faktor 1 M3</td>
											<td class="text12" >
												<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="kovkkg" id="kovkkg" size="6" maxlength="5" value='${model.record.kovkkg}'>&nbsp;Kg.
											</td>
										</tr>
										
										<tr>
											<td class="text12" title="kovavr">Overstyre avrund.</td>
											<td class="text12" >
												<input type="text" class="inputTextMediumBlue" name="kovavr" id="kovavr" size="2" maxlength="1" value="${model.record.kovavr}">
											</td>
											
											<%-- TEST on JSTL --%>
											<td class="text12" title="kovxxxT">
												<input type="text" class="inputTextMediumBlue" name="kovxxx" id="kovxxx" size="8" maxlength="5" value="${model.record.kovxxx}">
											</td>
											<td class="text12" title="kowxxxT">
												<input type="text" class="inputTextMediumBlue" name="kowxxx" id="kowxxx" size="8" maxlength="5" value="${model.record.kowxxx}">
											</td>
											
										</tr>		
									</table>
								</td>
							</tr>
							<tr height="5"><td >&nbsp;</td></tr>
						</table>
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
								<td ><input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="kovk1" id="kovk1" size="6" maxlength="5" value='${model.record.kovk1}'></td>
								<td ><input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="kovk2" id="kovk2" size="6" maxlength="5" value='${model.record.kovk2}'></td>
								<td ><input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="kovk3" id="kovk3" size="6" maxlength="5" value='${model.record.kovk3}'></td>
								<td ><input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="kovk4" id="kovk4" size="6" maxlength="5" value='${model.record.kovk4}'></td>
								<td ><input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="kovk5" id="kovk5" size="6" maxlength="5" value='${model.record.kovk5}'></td>
								<td ><input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="kovk6" id="kovk6" size="6" maxlength="5" value='${model.record.kovk6}'></td>
								<td ><input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="kovk7" id="kovk7" size="6" maxlength="5" value='${model.record.kovk7}'></td>
								<td ><input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="kovk8" id="kovk8" size="6" maxlength="5" value='${model.record.kovk8}'></td>
								<td ><input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="kovk9" id="kovk9" size="6" maxlength="5" value='${model.record.kovk9}'></td>
								<td ><input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="kovk10" id="kovk10" size="6" maxlength="5" value='${model.record.kovk10}'></td>
								<td ><input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="kovk11" id="kovk11" size="6" maxlength="5" value='${model.record.kovk11}'></td>
								<td ><input readonly onKeyPress="return numberKey(event)" type="text" class="inputTextReadOnly" name="todo" id="todo" size="6" maxlength="5" value='99999'></td>
							</tr>
							<tr>
								<td ><input type="checkbox" name="kowf1" id="kowf1" value="F" <c:if test="${model.record.kowf1 == 'F'}"> checked </c:if>  ><font class="text11">fast pris</font></td>
								<td ><input type="checkbox" name="kowf2" id="kowf2" value="F" <c:if test="${model.record.kowf2 == 'F'}"> checked </c:if>  ><font class="text11">fast pris</font></td>
								<td ><input type="checkbox" name="kowf3" id="kowf3" value="F" <c:if test="${model.record.kowf3 == 'F'}"> checked </c:if>  ><font class="text11">fast pris</font></td>
								<td ><input type="checkbox" name="kowf4" id="kowf4" value="F" <c:if test="${model.record.kowf4 == 'F'}"> checked </c:if>  ><font class="text11">fast pris</font></td>
								<td ><input type="checkbox" name="kowf5" id="kowf5" value="F" <c:if test="${model.record.kowf5 == 'F'}"> checked </c:if>  ><font class="text11">fast pris</font></td>
								<td ><input type="checkbox" name="kowf6" id="kowf6" value="F" <c:if test="${model.record.kowf6 == 'F'}"> checked </c:if>  ><font class="text11">fast pris</font></td>
								<td ><input type="checkbox" name="kowf7" id="kowf7" value="F" <c:if test="${model.record.kowf7 == 'F'}"> checked </c:if>  ><font class="text11">fast pris</font></td>
								<td ><input type="checkbox" name="kowf8" id="kowf8" value="F" <c:if test="${model.record.kowf8 == 'F'}"> checked </c:if>  ><font class="text11">fast pris</font></td>
								<td ><input type="checkbox" name="kowf9" id="kowf9" value="F" <c:if test="${model.record.kowf9 == 'F'}"> checked </c:if>  ><font class="text11">fast pris</font></td>
								<td ><input type="checkbox" name="kowf10" id="kowf10" value="F" <c:if test="${model.record.kowf10 == 'F'}"> checked </c:if>  ><font class="text11">fast pris</font></td>
								<td ><input type="checkbox" name="kowf11" id="kowf11" value="F" <c:if test="${model.record.kowf11 == 'F'}"> checked </c:if>  ><font class="text11">fast pris</font></td>
								<td ><input type="checkbox" name="kowf12" id="kowf12" value="F" <c:if test="${model.record.kowf12 == 'F'}"> checked </c:if>  ><font class="text11">fast pris</font></td>
								
							</tr>
							
							<tr height="3"><td >&nbsp;</td></tr>
							<tr>
								<td colspan="12" > 
									<table cellspacing="1" border="0" align="left">
									<tr>
										<td class="text12" ><img onMouseOver="showPop('kovomr_info');" onMouseOut="hidePop('kovomr_info');" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
											<span title="kovomr">Pr.per ? kg&nbsp;</span>
											<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="kovomr" id="kovomr" size="4" maxlength="3" value='${model.record.kovomr}'>
											
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
		 					  					<option value="0"<c:if test="${ model.record.kowmm == '0'}"> selected </c:if> >0</option>
							  					<option value="1"<c:if test="${ model.record.kowmm == '1'}"> selected </c:if> >1</option>
							  					<option value="2"<c:if test="${ model.record.kowmm == '2'}"> selected </c:if> >2</option>
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
						<table width="99%" 	cellspacing	="1" border="0" align="left">
							<tr height="3"><td >&nbsp;</td></tr>
							<tr>
								<td valign="top" width="90%">
									<table	cellspacing="1" border="0" align="left">
										<tr>
											<td class="text12" title="kowkom" >&nbsp;Kombinert giro</td>
											<td >
												<select name="kowkom" id="kowkom" >
			 					  					<option value="">-velg-</option>
			 					  					<option value="J"<c:if test="${ model.record.kowkom == 'J'}"> selected </c:if> >Ja</option>
								  					<option value="N"<c:if test="${ model.record.kowkom == 'N'}"> selected </c:if> >Nej</option>
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
			 					  					<option value="A"<c:if test="${ model.record.kowbbs == 'A'}"> selected </c:if> >A</option>
								  					<option value="B"<c:if test="${ model.record.kowbbs == 'B'}"> selected </c:if> >B</option>
								  					<option value="C"<c:if test="${ model.record.kowbbs == 'C'}"> selected </c:if> >C</option>
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
			 					  					<option value="J"<c:if test="${ model.record.kowawb == 'J'}"> selected </c:if> >J</option>
								  					<option value="N"<c:if test="${ model.record.kowawb == 'N'}"> selected </c:if> >N</option>
								  					<option value="S"<c:if test="${ model.record.kowawb == 'S'}"> selected </c:if> >S</option>
								  					<option value="K"<c:if test="${ model.record.kowawb == 'K'}"> selected </c:if> >K</option>
								  				</select>
											</td>
										</tr>
										<tr>
											<td class="text12" title="kowhod" >&nbsp;Hode på fakt.</td>
											<td ><input type="text" class="inputTextMediumBlue" name="kowhod" id="kowhod" size="2" maxlength="1" value='${model.record.kowhod}'></td>
											<td class="text12" title="kowlas" >&nbsp;&nbsp;&nbsp;&nbsp;H/AWB-tab</td>
											<td ><input type="text" class="inputTextMediumBlue" name="kowlas" id="todo" size="2" maxlength="1" value='${model.record.kowlas}'></td>
											<td class="text12" title="avutpr" >&nbsp;&nbsp;&nbsp;&nbsp;Utl.pro.</td>
											<td ><input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue" name="avutpr" id="avutpr" size="6" maxlength="5" value='${model.record.avutpr}'></td>
											<td class="text12" title="avutmi" >&nbsp;Utl.minb.</td>
											<td ><input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="avutmi" id="avutmi" size="6" maxlength="4" value='${model.record.avutmi}'></td>
											<td width="50px">&nbsp;</td>
											<td >
												<input class="inputFormSubmit" type="submit" name="submit" value='Lagre'/>
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
	 	    
	 	    <%-- list component --%>
			<tr>
				<td width="5%">&nbsp;</td>
				<td width="100%">
				<table id="containerdatatableTable" width="95%" cellspacing="1" border="0" align="left">
			    	    <tr>
						<td class="text11">
						<table id="childList" class="display compact cell-border" >
							<thead>
							<tr>
							    <th width="2%" class="tableHeaderFieldFirst" align="center" >Endre</th>                                                            
								<th width="2%" class="tableHeaderFieldFirst" align="center" >Lnr</th>                                                            
								<th width="25%" class="tableHeaderField" align="left" >Beskrivelse</th>
								<th class="tableHeaderField" align="left" >Papirtype</th>
			                    <th class="tableHeaderField" align="center" >&nbsp;Anl.</th>
			                    <th class="tableHeaderField" align="center" >&nbsp;Ank.</th>
			                    <th class="tableHeaderField" align="center" >Printer</th>
			                    <th class="tableHeaderField" align="center" >H</th>
			                    <th class="tableHeaderField" align="center" >L</th>
			                    <th class="tableHeaderField" align="center" >8</th>
			                    <th class="tableHeaderField" align="center" >Stringk</th>
			                    <th class="tableHeaderField" align="center" >Sk.</th>
			                    <th class="tableHeaderField" align="center" >Kop.</th>
			                    <th class="tableHeaderField" align="center" >Slett</th>
			                </tr>  
			                </thead> 
			                <tbody >  
				            <c:forEach var="childRecord" items="${model.record.childList}" varStatus="counter">   
				               <tr class="tableRow" height="20" >
				               <td width="2%" class="tableCellFirst" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" align="center">
					               	<a id="alinkRecordId_${counter.count}" target="_blank" href="mainmaintenanceavd_syfa28r_edit_childwindow.do?kopavd=${childRecord.kopavd}&koplnr=${childRecord.koplnr}&updateId=${childRecord.kopavd}_${childRecord.koplnr}" onClick="window.open(this.href,'targetWindowSyfa28','top=400px,left=400px,height=300px,width=700px,scrollbars=no,status=no,location=no'); return false;">
	               						<img src="resources/images/update.gif" border="0" alt="edit">
				               		</a>
				               </td>
				               <td width="2%" class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" align="center">${childRecord.koplnr}</td>
				               <td width="25%" class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 1px;border-color:#FAEBD7;">&nbsp;${childRecord.utptxt}&nbsp;</td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" >&nbsp;${childRecord.kopty}&nbsp;</td>
		                       <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" >&nbsp;${childRecord.utpl}&nbsp;</td>
		                       <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" >&nbsp;${childRecord.utpk}&nbsp;</td>
		                       <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" >&nbsp;${childRecord.kopnvn}&nbsp;</td>
		                       <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" >&nbsp;${childRecord.kophea}</td>
		                       <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" align="center">&nbsp;${childRecord.koplas}&nbsp;</td>
		                       <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" align="center">&nbsp;${childRecord.koplpi}&nbsp;</td>
		                       <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" align="center">&nbsp;${childRecord.kopfm}&nbsp;</td>
		                       <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" align="center">&nbsp;${childRecord.kopdraw}&nbsp;</td>
		                       <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" align="center">&nbsp;${childRecord.kopcopi}&nbsp;</td>
		                       <td align="center" width="2%" class="tableCell" style="cursor:pointer; border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;">
		               				<a onclick="javascript:return confirm('Er du sikker på at du vil slette denne?')" tabindex=-1 href="TODOmainmaintenanceavd_syfa14r_edit.do?action=doDelete&koaavd=${childRecord.kopavd}">
					               		<img valign="bottom" src="resources/images/delete.gif" border="0" width="15px" height="15px" alt="remove">
					               	</a>
				               </td>
				            </tr> 
				            </c:forEach>
				            </tbody>
			            </table>
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

