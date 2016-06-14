<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerSkatMaintenance.jsp" />
<!-- =====================end header ==========================-->
	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/skatmaintenanceimport_dkt057r.js?ver=${user.versionEspedsg}"></SCRIPT>	
	
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
						<a id="alinkSadMaintImportGate" tabindex=-1 style="display:block;" href="skatmaintenanceimport.do">
						<font class="tabDisabledLink">&nbsp;SKAT - Underhold</font>
						<img style="vertical-align: middle;"  src="resources/images/list.gif" border="0" alt="general list">
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="30%" valign="bottom" class="tab" align="center">
						<font class="tabLink">Valutakurser</font>&nbsp;<font class="text12">DKT057 / DKTVK</font>&nbsp;
						<a id="alinkRecordId_${counter.count}" onClick="setBlockUI(this);" href="skatmaintenanceimport_dkt057r.do?id=${model.dbTable}">
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
					<form action="skatmaintenanceimport_dkt057r.do?id=${model.dbTable}" name="formRecord" id="formRecord" method="POST" >
					Kode&nbsp;
					<input type="text" class="inputTextMediumBlue" name="searchKode" id="searchKode" size="5" maxlength="3" value='${model.searchKode}'>
					<%--
					&nbsp;Søkebegrep&nbsp;
					<input type="text" class="inputTextMediumBlue" name="searchTaalfa" id="searchTaalfa" size="15" maxlength="25" value='${model.taalfa}'>
					--%>
					&nbsp;&nbsp;<input onClick="setBlockUI(this);" class="inputFormSubmit" type="submit" name="submit" value='<spring:message code="systema.skat.search"/>'/>
					
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
								<th align="center" width="2%" class="tableHeaderField" >&nbsp;Ændre&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;Sort&nbsp;</th>
			                    <th class="tableHeaderField" >&nbsp;Kurs&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;Faktor&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;Fra&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;Til&nbsp;</th>
			                    <th align="center" class="tableHeaderField">Fjern</th>
			                </tr>  
			                </thead> 
			                <tbody >  
				            <c:forEach var="record" items="${model.list}" varStatus="counter">   
				               <tr class="tableRow" height="20" >
				               <td id="recordUpdate_${record.dkvk_kd}_${record.dkvk_dts}" onClick="getRecord(this);" align="center" width="2%" class="tableCellFirst" style="cursor:pointer; border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;">
		               				<img src="resources/images/update.gif" border="0" alt="edit">
				               </td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 1px;border-color:#FAEBD7;"><font class="text12">&nbsp;${record.dkvk_kd}&nbsp;</font></td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.dkvk_krs}&nbsp;</font></td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.dkvk_omr}&nbsp;</font></td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.dkvk_dts}&nbsp;</font></td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.dkvk_dte}&nbsp;</font></td>
				               
				               <td align="center" width="2%" class="tableCell" style="cursor:pointer; border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;">
		               				<a onclick="javascript:return confirm('Er du sikker på at du vil fjerne denne?')" tabindex=-1 href="skatmaintenanceimport_dkt057r_edit.do?action=doDelete&id=${model.dbTable}&dkvk_kd=${record.dkvk_kd}&dkvk_dts=${record.dkvk_dts}">
					               		<img valign="bottom" src="resources/images/delete.gif" border="0" width="15px" height="15px" alt="remove">
					               	</a>
				               </td>
				            </tr> 
				            </c:forEach>
				            </tbody>
				            <tfoot>
							<tr>
							    <th align="center" width="2%" class="tableHeaderFieldWhiteBg11" >&nbsp;Ændre&nbsp;</th>
								<th align="center" class="tableHeaderFieldWhiteBg11" >&nbsp;DKVK_KD</th>
			                    <th align="center" class="tableHeaderFieldWhiteBg11" >&nbsp;DKVK_KRS&nbsp;</th>
			                    <th align="center" class="tableHeaderFieldWhiteBg11" >&nbsp;DKVK_OMR&nbsp;</th>
			                    <th align="center" class="tableHeaderFieldWhiteBg11" >&nbsp;DKVK_DTS&nbsp;</th>
			                    <th align="center" class="tableHeaderFieldWhiteBg11" >&nbsp;DKVK_DTE&nbsp;</th>
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
				<td><button name="newRecordButton" id="newRecordButton" class="inputFormSubmitStd" type="button" >Opret	 ny</button></td>
			</tr>
	 	    <tr >
	 	    	<td width="5%">&nbsp;</td>
				<td width="100%">
				<form action="skatmaintenanceimport_dkt057r_edit.do" name="formRecord" id="formRecord" method="POST" >
					<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
					<input type="hidden" name="updateId" id=updateId value="${model.updateId}"> 
					<input type="hidden" name="action" id=action value="doUpdate">
					
					<table width="80%" cellspacing="1" border="0" align="left">
			    	    <tr>
							<td class="text12" title="DKVK_KD">&nbsp;<font class="text14RedBold" >*</font>Sort</td>
							<td class="text12" title="DKVK_KRS">&nbsp;<font class="text14RedBold" >*</font>Kurs</td>
							<td class="text12" title="DKVK_OMR">&nbsp;<font class="text14RedBold" >*</font>Faktor</td>
							<td class="text12" title="DKVK_DTS">&nbsp;<font class="text14RedBold" >*</font>Fra</td>
							<td class="text12" title="DKVK_DTE">&nbsp;Til</td>
						</tr>
						<tr>
						<td ><input type="text" class="inputTextMediumBlueMandatoryField" name="dkvk_kd" id="dkvk_kd" size="4" maxlength="3" value='${model.record.dkvk_kd}'></td>
						<td ><input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlueMandatoryField" name="dkvk_krs" id="dkvk_krs" size="10" maxlength="11" value='${model.record.dkvk_krs}'></td>
						<td ><input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlueMandatoryField" name="dkvk_omr" id="dkvk_omr" size="8" maxlength="7" value='${model.record.dkvk_omr}'></td>
						<td ><input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlueMandatoryField" name="dkvk_dts" id="dkvk_dts" size="9" maxlength="8" value='${model.record.dkvk_dts}'></td>
						<td ><input type="text" class="inputTextMediumBlue" name="dkvk_dte" id="dkvk_dte" size="9" maxlength="8" value='${model.record.dkvk_dte}'></td>
						<td>
							<input onClick="setBlockUI(this);" class="inputFormSubmit" type="submit" name="submit" value='Spare'/>
						</td>
						</tr>
						<tr height="3"><td></td>
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

