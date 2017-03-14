<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerSkatMaintenance.jsp" />
<!-- =====================end header ==========================-->
	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/skatmaintenancefelles_dktard.js?ver=${user.versionEspedsg}"></SCRIPT>	
	
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
					<td width="20%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a id="alinkSadMaintFellesGate" tabindex=-1 style="display:block;" href="skatmaintenancefelles.do">
						<font class="tabDisabledLink">&nbsp;SKAT - Vedligehold</font>
						<img style="vertical-align: middle;"  src="resources/images/list.gif" border="0" alt="general list">
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="20%" valign="bottom" class="tab" align="center">
						<font class="tabLink">Toldtariffen</font>&nbsp;<font class="text12">DKTARD</font>&nbsp;
						<a id="alinkRecordId_${counter.count}" onClick="setBlockUI(this);" href="skatmaintenancefelles_dktard.do?id=${model.dbTable}">
							<img style="vertical-align: middle;"  src="resources/images/bulletGreen.png" border="0" width="8px" height="8px" alt="db table">
						</a>
					</td>
					<td width="70%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>	
				</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td>
		<%-- space separator --%>
	 		<table width="100%" class="tabThinBorderWhite" border="0" cellspacing="0" cellpadding="0">
	 	    <tr height="30"><td>&nbsp;</td></tr>
	 	    
	 	    <tr >
	 	    	<td width="5%">&nbsp;</td>
				<td width="100%" class="text12">
					<form action="skatmaintenancefelles_dktard.do?id=${model.dbTable}" name="formRecord" id="formRecord" method="POST" >
					Varekode&nbsp;
					<input type="text" class="inputTextMediumBlue" name="searchDktard01" id="searchDktard01" size="11" maxlength="10" value='${model.searchDktard01}'>
					&nbsp;&nbsp;Start dato&nbsp;
					<input type="text" class="inputTextMediumBlue" name="searchDktard02" id="searchDktard02" size="9" maxlength="8" value='${model.searchDktard02}'>
					&nbsp;&nbsp;<input onClick="setBlockUI(this);" class="inputFormSubmit" type="submit" name="submit" value='Søg'/>
					</form>
				</td>
			</tr>
			
			<%-- list component --%>
			<tr>
				<td width="5%">&nbsp;</td>
				<td width="100%">
				<table id="containerdatatableTable" width="98%" cellspacing="1" border="0" align="left">
			    	    <tr>
						<td class="text11">
						<table id="mainList" class="display compact cell-border" >
							<thead>
							<tr>
								<th align="center" width="2%" class="tableHeaderField" >&nbsp;Opdater&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;Varekode&nbsp;</th>
			                    <th class="tableHeaderField" >&nbsp;Start dato&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;Slut dato&nbsp;</th>
			                    <th class="tableHeaderField" >&nbsp;Toldsatstype&nbsp;</th>
			                    <th class="tableHeaderField" >&nbsp;Toldsats&nbsp;</th>
			                    <th class="tableHeaderField" >&nbsp;Supplerende mængde.&nbsp;</th>
			                    <th class="tableHeaderField" >&nbsp;Varekodetekst&nbsp;</th>
			                    <th align="center" class="tableHeaderField" >Fjern</th>
			                </tr>  
			                </thead> 
			                <tbody >  
				            <c:forEach var="record" items="${model.list}" varStatus="counter">   
				               <tr class="tableRow" height="20" >
				               <td id="recordUpdate_${record.dktard01}_${record.dktard02}_${record.dktard03}" onClick="getRecord(this);" align="center" width="2%" class="tableCellFirst" style="cursor:pointer; border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;">
		               				<img src="resources/images/update.gif" border="0" alt="edit">
				               </td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 1px;border-color:#FAEBD7;"><font class="text12">&nbsp;${record.dktard01}&nbsp;</font></td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.dktard02}&nbsp;</font></td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.dktard03}&nbsp;</font></td>
		                       <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.dktard04}&nbsp;</font></td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.dktard05}&nbsp;</font></td>
		                       <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.dktard47}&nbsp;</font></td>
		                       <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.dktard48}&nbsp;</font></td>
		                       <td align="center" width="2%" class="tableCell" style="cursor:pointer; border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;">
		               				<a onclick="javascript:return confirm('Er du sikker på at du vil slette denne?')" tabindex=-1 href="skatmaintenancefelles_dktard_edit.do?action=doDelete&id=${model.dbTable}&dktard01=${record.dktard01}&dktard02=${record.dktard02}&dktard03=${record.dktard03}">
					               		<img valign="bottom" src="resources/images/delete.gif" border="0" width="15px" height="15px" alt="remove">
					               	</a>
				               </td>
				            </tr> 
				            </c:forEach>
				            </tbody>
				            <tfoot>
							<tr>
							    <th align="center" width="2%" class="tableHeaderFieldWhiteBg11" >&nbsp;Opdater&nbsp;</th>
								<th align="center" class="tableHeaderFieldWhiteBg11" >&nbsp;DKTARD01</th>
			                    <th align="center" class="tableHeaderFieldWhiteBg11" >&nbsp;DKTARD02&nbsp;</th>
			                    <th align="center" class="tableHeaderFieldWhiteBg11" >&nbsp;DKTARD03&nbsp;</th>
								<th align="center" class="tableHeaderFieldWhiteBg11" >&nbsp;DKTARD04&nbsp;</th>
			                    <th align="center" class="tableHeaderFieldWhiteBg11" >&nbsp;DKTARD05&nbsp;</th>
			                    <th align="center" class="tableHeaderFieldWhiteBg11">&nbsp;DKTARD47&nbsp;</th>
			                    <th align="center" class="tableHeaderFieldWhiteBg11">&nbsp;DKTARD48&nbsp;</th>
			                    <th align="center" class="tableHeaderFieldWhiteBg11">Fjern</th>
			                </tr>  
			                </tfoot> 
			            </table>
					</td>	
					</tr>
				</table>
				</td>
			</tr>
		    
	 	    <tr height="25"><td>&nbsp;</td></tr>
	 	    
	 	    <%-- Validation errors --%>
			<spring:hasBindErrors name="record"> <%-- name must equal the command object name in the Controller --%>
			<tr>
				<td width="5%">&nbsp;</td>
				<td width="100%">
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
				<td width="5">&nbsp;</td>
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
			<tr height="2"><td>&nbsp;</td>
			</tr>
			<tr >
				<td width="5%">&nbsp;</td>
				<td><button name="newRecordButton" id="newRecordButton" class="inputFormSubmitStd" type="button" >Opret ny</button></td>
			</tr>
	 	    <tr >
	 	    	<td width="5%">&nbsp;</td>
				<td width="100%">
				<form action="skatmaintenancefelles_dktard_edit.do" name="formRecord" id="formRecord" method="POST" >
					<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
					<input type="hidden" name="updateId" id=updateId value="${model.updateId}"> 
					<input type="hidden" name="action" id=action value="doUpdate">
					
					<table width="80%" cellspacing="1" border="0" align="left">
			    	    <tr>
							<td class="text12" title="dktard01">&nbsp;<font class="text14RedBold" >*</font>Varekode</td>
							<td class="text12" title="dktard02">&nbsp;<font class="text14RedBold" >*</font>Start dato</td>
							<td class="text12" title="dktard03"><font class="text14RedBold" >*</font>&nbsp;Slut dato</td>
							<td class="text12" title="dktard04">&nbsp;Toldsatstype</td>
							<td class="text12" title="dktard05">&nbsp;Toldsats</td>
							<td class="text12" title="dktard47">&nbsp;Supplerende mængde</td>
							<td class="text12" title="dktard48"><font class="text14RedBold" >*</font>&nbsp;Varekodetext</td>
						</tr>
						<tr>
						<td ><input type="text" required oninvalid="this.setCustomValidity('Obligatoriskt')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="dktard01" id="dktard01" size="11" maxlength="10" value='${model.record.dktard01}'></td>
						<td ><input type="text" required oninvalid="this.setCustomValidity('Obligatoriskt')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="dktard02" id="dktard02" size="10" maxlength="8" value='${model.record.dktard02}'></td>
						<td ><input type="text" required oninvalid="this.setCustomValidity('Obligatoriskt')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="dktard03" id="dktard03" size="10" maxlength="8" value='${model.record.dktard03}'></td>
						<td >
							<select class="inputTextMediumBlue" name="dktard04" id="dktard04">
			            		<option value="">-vælg-</option>
			 				  	<c:forEach var="code" items="${model.codeListToldsatstype}" >
                              	 	<option value="${code}"<c:if test="${model.record.dktard04 == code}"> selected </c:if> >${code}</option>
								</c:forEach> 
							</select>
						</td>
						<td ><input type="text" class="inputTextMediumBlue" name="dktard05" id="dktard05" size="8" maxlength="7" value='${model.record.dktard05}'></td>
						<td ><input type="text" class="inputTextMediumBlue" name="dktard47" id="dktard47" size="4" maxlength="3" value='${model.record.dktard47}'></td>
						<td ><input type="text" required oninvalid="this.setCustomValidity('Obligatoriskt')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="dktard48" id="dktard48" size="20" maxlength="182" value='${model.record.dktard48}'></td>
						<td>
							<input class="inputFormSubmit" type="submit" name="submit" id="submit" value='Spare'/>
						</td>
						</tr>
						<tr height="3"><td></td>
					</table>
					
					<table class="tableHeaderField" width="80%" cellspacing="1" border="0" align="left">	
						<%--
						------------------------ 
						 FIRST  SECONDARY LINE
						------------------------ 
						--%>
						<tr>
							<td class="text12" title="dktard06">&nbsp;Enhedsgrundlag</td>
							<td class="text12" title="dktard07">&nbsp;Enhedstillæg</td>
							<td class="text12" title="dktard08">&nbsp;Komponent</td>
							<td class="text12" title="dktard09">&nbsp;EURO type</td>
						</tr>

						<tr>
						<td >
							<select class="inputTextMediumBlue" name="dktard06" id="dktard06">
			            		<option value="">-vælg-</option>
			 				  	<c:forEach var="record" items="${model.codeList022}" >
                              	 	<option value="${record.dkkd_kd}"<c:if test="${model.record.dktard06 == record.dkkd_kd}"> selected </c:if> >${record.dkkd_kd}</option>
								</c:forEach> 
							</select>
						</td>
						<td ><input type="text" class="inputTextMediumBlue" name="dktard07" id="dktard07" size="2" maxlength="1" value='${model.record.dktard07}'></td>
						<td ><input type="text" class="inputTextMediumBlue" name="dktard08" id="dktard08" size="6" maxlength="5" value='${model.record.dktard08}'></td>
						<td >
							<select name="dktard09" id="dktard09">
        		    			<option value="">-velg-</option>
							  	<option value="A"<c:if test="${ model.record.dktard09 == 'A'}"> selected </c:if> >A</option>
							  	<option value="R"<c:if test="${ model.record.dktard09 == 'R'}"> selected </c:if> >R</option>
							  	<option value="W"<c:if test="${ model.record.dktard09 == 'W'}"> selected </c:if> >W</option>
							</select>
						</td>
						</tr>
						<%--
						------------------------ 
						 SECOND  SECONDARY LINE
						------------------------ 
						--%>
						<tr height="10"><td></td></tr>
						<tr>
							<td class="text12" title="dktard10">&nbsp;Toldsatstype(2)</td>
							<td class="text12" title="dktard11">&nbsp;Toldsats(2)</td>
							<td class="text12" title="dktard12">&nbsp;Enhedsgrundlag(2)</td>
							<td class="text12" title="dktard13">&nbsp;Enhedstillæg(2)</td>
							<td class="text12" title="dktard14">&nbsp;Komponent(2)</td>
							<td class="text12" title="dktard15">&nbsp;EURO type(2)</td>
						</tr>

						<tr>
						<td >
							<select class="inputTextMediumBlue" name="dktard10" id="dktard10">
			            		<option value="">-vælg-</option>
			 				  	<c:forEach var="code" items="${model.codeListToldsatstype}" >
                              	 	<option value="${code}"<c:if test="${model.record.dktard10 == code}"> selected </c:if> >${code}</option>
								</c:forEach> 
							</select>
						</td>
						<td ><input type="text" class="inputTextMediumBlue" name="dktard11" id="dktard11" size="8" maxlength="7" value='${model.record.dktard11}'></td>
						<td >
							<select class="inputTextMediumBlue" name="dktard12" id="dktard12">
			            		<option value="">-vælg-</option>
			 				  	<c:forEach var="record" items="${model.codeList022}" >
                              	 	<option value="${record.dkkd_kd}"<c:if test="${model.record.dktard12 == record.dkkd_kd}"> selected </c:if> >${record.dkkd_kd}</option>
								</c:forEach> 
							</select>
						</td>
						<td ><input type="text" class="inputTextMediumBlue" name="dktard13" id="dktard13" size="2" maxlength="1" value='${model.record.dktard13}'></td>
						<td ><input type="text" class="inputTextMediumBlue" name="dktard14" id="dktard14" size="6" maxlength="5" value='${model.record.dktard14}'></td>
						<td >
							<select name="dktard15" id="dktard15">
        		    			<option value="">-velg-</option>
							  	<option value="A"<c:if test="${ model.record.dktard15 == 'A'}"> selected </c:if> >A</option>
							  	<option value="R"<c:if test="${ model.record.dktard15 == 'R'}"> selected </c:if> >R</option>
							  	<option value="W"<c:if test="${ model.record.dktard15 == 'W'}"> selected </c:if> >W</option>
							</select>
						</td>
						</tr>
						
						<%--
						------------------------ 
						 3:d  SECONDARY LINE
						------------------------ 
						--%>
						<tr>
							<td class="text12" title="dktard16">&nbsp;Toldsatstype(3)</td>
							<td class="text12" title="dktard17">&nbsp;Toldsats(3)</td>
							<td class="text12" title="dktard18">&nbsp;Enhedsgrundlag(3)</td>
							<td class="text12" title="dktard19">&nbsp;Enhedstillæg(3)</td>
							<td class="text12" title="dktard20">&nbsp;Komponent(3)</td>
							<td class="text12" title="dktard21">&nbsp;EURO type(3)</td>
						</tr>

						<tr>
						<td >
							<select class="inputTextMediumBlue" name="dktard16" id="dktard16">
			            		<option value="">-vælg-</option>
			 				  	<c:forEach var="code" items="${model.codeListToldsatstype}" >
                              	 	<option value="${code}"<c:if test="${model.record.dktard16 == code}"> selected </c:if> >${code}</option>
								</c:forEach> 
							</select>
						</td>
						<td ><input type="text" class="inputTextMediumBlue" name="dktard17" id="dktard17" size="8" maxlength="7" value='${model.record.dktard17}'></td>
						<td >
							<select class="inputTextMediumBlue" name="dktard18" id="dktard18">
			            		<option value="">-vælg-</option>
			 				  	<c:forEach var="record" items="${model.codeList022}" >
                              	 	<option value="${record.dkkd_kd}"<c:if test="${model.record.dktard18 == record.dkkd_kd}"> selected </c:if> >${record.dkkd_kd}</option>
								</c:forEach> 
							</select>
						</td>
						<td ><input type="text" class="inputTextMediumBlue" name="dktard19" id="dktard19" size="2" maxlength="1" value='${model.record.dktard19}'></td>
						<td ><input type="text" class="inputTextMediumBlue" name="dktard20" id="dktard20" size="6" maxlength="5" value='${model.record.dktard20}'></td>
						<td >
							<select name="dktard21" id="dktard21">
        		    			<option value="">-velg-</option>
							  	<option value="A"<c:if test="${ model.record.dktard21 == 'A'}"> selected </c:if> >A</option>
							  	<option value="R"<c:if test="${ model.record.dktard21 == 'R'}"> selected </c:if> >R</option>
							  	<option value="W"<c:if test="${ model.record.dktard21 == 'W'}"> selected </c:if> >W</option>
							</select>
						</td>
						</tr>
						<%--
						------------------------ 
						 4:th  SECONDARY LINE
						------------------------ 
						--%>
						<tr>
							<td class="text12" title="dktard22">&nbsp;Toldsatstype(4)</td>
							<td class="text12" title="dktard23">&nbsp;Toldsats(4)</td>
							<td class="text12" title="dktard24">&nbsp;Enhedsgrundlag(4)</td>
							<td class="text12" title="dktard25">&nbsp;Enhedstillæg(4)</td>
							<td class="text12" title="dktard26">&nbsp;Komponent(4)</td>
							<td class="text12" title="dktard27">&nbsp;EURO type(4)</td>
						</tr>

						<tr>
						<td >
							<select class="inputTextMediumBlue" name="dktard22" id="dktard22">
			            		<option value="">-vælg-</option>
			 				  	<c:forEach var="code" items="${model.codeListToldsatstype}" >
                              	 	<option value="${code}"<c:if test="${model.record.dktard22 == code}"> selected </c:if> >${code}</option>
								</c:forEach> 
							</select>
						</td>
						<td ><input type="text" class="inputTextMediumBlue" name="dktard23" id="dktard23" size="8" maxlength="7" value='${model.record.dktard23}'></td>
						<td >
							<select class="inputTextMediumBlue" name="dktard24" id="dktard24">
			            		<option value="">-vælg-</option>
			 				  	<c:forEach var="record" items="${model.codeList022}" >
                              	 	<option value="${record.dkkd_kd}"<c:if test="${model.record.dktard24 == record.dkkd_kd}"> selected </c:if> >${record.dkkd_kd}</option>
								</c:forEach> 
							</select>
						</td>
						<td ><input type="text" class="inputTextMediumBlue" name="dktard25" id="dktard25" size="2" maxlength="1" value='${model.record.dktard25}'></td>
						<td ><input type="text" class="inputTextMediumBlue" name="dktard26" id="dktard26" size="6" maxlength="5" value='${model.record.dktard26}'></td>
						<td >
							<select name="dktard27" id="dktard27">
        		    			<option value="">-velg-</option>
							  	<option value="A"<c:if test="${ model.record.dktard27 == 'A'}"> selected </c:if> >A</option>
							  	<option value="R"<c:if test="${ model.record.dktard27 == 'R'}"> selected </c:if> >R</option>
							  	<option value="W"<c:if test="${ model.record.dktard27 == 'W'}"> selected </c:if> >W</option>
							</select>
						</td>
						</tr>
						<%--
						------------------------ 
						 5:th  SECONDARY LINE
						------------------------ 
						--%>
						<tr>
							<td class="text12" title="dktard28">&nbsp;Toldsatstype(5)</td>
							<td class="text12" title="dktard29">&nbsp;Toldsats(5)</td>
							<td class="text12" title="dktard30">&nbsp;Enhedsgrundlag(5)</td>
							<td class="text12" title="dktard31">&nbsp;Enhedstillæg(5)</td>
							<td class="text12" title="dktard32">&nbsp;Komponent(5)</td>
							<td class="text12" title="dktard33">&nbsp;EURO type(5)</td>
						</tr>

						<tr>
						<td >
							<select class="inputTextMediumBlue" name="dktard28" id="dktard28">
			            		<option value="">-vælg-</option>
			 				  	<c:forEach var="code" items="${model.codeListToldsatstype}" >
                              	 	<option value="${code}"<c:if test="${model.record.dktard28 == code}"> selected </c:if> >${code}</option>
								</c:forEach> 
							</select>
						</td>
						<td ><input type="text" class="inputTextMediumBlue" name="dktard29" id="dktard29" size="8" maxlength="7" value='${model.record.dktard29}'></td>
						<td >
							<select class="inputTextMediumBlue" name="dktard30" id="dktard30">
			            		<option value="">-vælg-</option>
			 				  	<c:forEach var="record" items="${model.codeList022}" >
                              	 	<option value="${record.dkkd_kd}"<c:if test="${model.record.dktard30 == record.dkkd_kd}"> selected </c:if> >${record.dkkd_kd}</option>
								</c:forEach> 
							</select>
						</td>
						<td ><input type="text" class="inputTextMediumBlue" name="dktard31" id="dktard31" size="2" maxlength="1" value='${model.record.dktard31}'></td>
						<td ><input type="text" class="inputTextMediumBlue" name="dktard32" id="dktard32" size="6" maxlength="5" value='${model.record.dktard32}'></td>
						<td >
							<select name="dktard33" id="dktard33">
        		    			<option value="">-velg-</option>
							  	<option value="A"<c:if test="${ model.record.dktard33 == 'A'}"> selected </c:if> >A</option>
							  	<option value="R"<c:if test="${ model.record.dktard33 == 'R'}"> selected </c:if> >R</option>
							  	<option value="W"<c:if test="${ model.record.dktard33 == 'W'}"> selected </c:if> >W</option>
							</select>
						</td>
						</tr>
						
						<%--
						------------------------ 
						 6:th  SECONDARY LINE
						------------------------ 
						--%>
						<tr height="10"><td></td></tr>
						<tr>
							<td class="text12" >
							<img onMouseOver="showPop('bilag1_info');" onMouseOut="hidePop('bilag1_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info" >
				 			<span title="dktard34">Bilag-1</span>
								<div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="bilag1_info" class="popupWithInputText text11"  >
				           			<b>Bilag-1 - Antidumping</b>
				           			 <br>
									 1 = Der er mulighed for antidumping.
								</span>
								</div>
							</td>
							<td class="text12" >
							<img onMouseOver="showPop('bilag2_info');" onMouseOut="hidePop('bilag2_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info" >
				 			<span title="dktard35">Bilag-2</span>
				 				<div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="bilag2_info" class="popupWithInputText text11"  >
				           			<b>Bilag-2 - Toldsuspension</b>
				           			 <br>
									 2 = Der er mulighed for toldsuspension
								</span>
								</div>
				 			</td>
							<td class="text12" >
							<img onMouseOver="showPop('bilag3_info');" onMouseOut="hidePop('bilag3_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info" >
				 			<span title="dktard36">Bilag-3</span>
								<div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="bilag3_info" class="popupWithInputText text11"  >
				           			<b>Bilag-3 - Diverse</b>
				           			 <br>
									 3 = Der findes landbrugselementer, tillægstold for sukker/mel, udligningsafgift eller tillægsbeløb..
								</span>
								</div>
							</td>
							<td class="text12" >
							<img onMouseOver="showPop('bilag4_info');" onMouseOut="hidePop('bilag4_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info" >
				 			<span title="dktard37">Bilag-4</span>
				 				<div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="bilag4_info" class="popupWithInputText text11"  >
				           			<b>Bilag-4 - Præference</b>
				           			 <br>
									 4 = Der er mulighed for præferencetold.
								</span>
								</div>
							
							</td>
				
						</tr>

						<tr>
						<td ><input type="text" class="inputTextMediumBlue" name="dktard34" id="dktard34" size="2" maxlength="1" value='${model.record.dktard34}'></td>
						<td ><input type="text" class="inputTextMediumBlue" name="dktard35" id="dktard35" size="2" maxlength="1" value='${model.record.dktard35}'></td>
						<td ><input type="text" class="inputTextMediumBlue" name="dktard36" id="dktard36" size="2" maxlength="1" value='${model.record.dktard36}'></td>
						<td ><input type="text" class="inputTextMediumBlue" name="dktard37" id="dktard37" size="2" maxlength="1" value='${model.record.dktard37}'></td>
						
						</tr>
						
						<%--
						------------------------ 
						 7:th  SECONDARY LINE
						------------------------ 
						--%>
						<tr height="10"><td></td></tr>
						<tr>
							<td class="text12" title="dktard38">&nbsp;Varebest.(1)</td>
							<td class="text12" title="dktard39">&nbsp;Varebest.(2)</td>
							<td class="text12" title="dktard40">&nbsp;Varebest.(3)</td>
							<td class="text12" title="dktard41">&nbsp;Varebest.(4)</td>
							<td class="text12" title="dktard42">&nbsp;Varebest.(5)</td>
							<td class="text12" title="dktard43">&nbsp;Varebest.(6)</td>
							
						</tr>

						<tr>
							<td ><input type="text" class="inputTextMediumBlue" name="dktard38" id="dktard38" size="3" maxlength="2" value='${model.record.dktard38}'></td>
							<td ><input type="text" class="inputTextMediumBlue" name="dktard39" id="dktard39" size="3" maxlength="2" value='${model.record.dktard39}'></td>
							<td ><input type="text" class="inputTextMediumBlue" name="dktard40" id="dktard40" size="3" maxlength="2" value='${model.record.dktard40}'></td>
							<td ><input type="text" class="inputTextMediumBlue" name="dktard41" id="dktard41" size="3" maxlength="2" value='${model.record.dktard41}'></td>
							<td ><input type="text" class="inputTextMediumBlue" name="dktard42" id="dktard42" size="3" maxlength="2" value='${model.record.dktard42}'></td>
							<td ><input type="text" class="inputTextMediumBlue" name="dktard43" id="dktard43" size="3" maxlength="2" value='${model.record.dktard43}'></td>
							
						</tr>
						<tr>
							<td class="text12" title="dktard44">&nbsp;Varebest.(7)</td>
							<td class="text12" title="dktard45">&nbsp;Varebest.(8)</td>
							<td class="text12" title="dktard46">&nbsp;Varebest.(9)</td>
						</tr>

						<tr>
							<td ><input type="text" class="inputTextMediumBlue" name="dktard44" id="dktard44" size="3" maxlength="2" value='${model.record.dktard44}'></td>
							<td ><input type="text" class="inputTextMediumBlue" name="dktard45" id="dktard45" size="3" maxlength="2" value='${model.record.dktard45}'></td>
							<td ><input type="text" class="inputTextMediumBlue" name="dktard46" id="dktard46" size="3" maxlength="2" value='${model.record.dktard46}'></td>							
						</tr>
	 	    		</table> 		
	 	    	</form>
	 	    </tr>
	 	    <tr height="20"><td>&nbsp;</td></tr>
	 	     
	 		</table>
		</td>
	</tr>
</table>	

<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->

