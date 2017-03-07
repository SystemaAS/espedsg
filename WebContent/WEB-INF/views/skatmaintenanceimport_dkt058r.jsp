<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerSkatMaintenance.jsp" />
<!-- =====================end header ==========================-->
	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/skatmaintenanceimport_dkt058r.js?ver=${user.versionEspedsg}"></SCRIPT>	
	
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
						<font class="tabDisabledLink">&nbsp;SKAT - Vedligehold</font>
						<img style="vertical-align: middle;"  src="resources/images/list.gif" border="0" alt="general list">
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="20%" valign="bottom" class="tab" align="center">
						<font class="tabLink">Certifikater</font>&nbsp;<font class="text12">DKT058 / DKTSE</font>&nbsp;
						<a id="alinkRecordId_${counter.count}" onClick="setBlockUI(this);" href="skatmaintenanceimport_dkt058r.do?id=${model.dbTable}">
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
					<form action="skatmaintenanceimport_dkt058r.do?id=${model.dbTable}" name="formRecordSearch" id="formRecordSearch" method="POST" >
					Kundenr&nbsp;
					<input type="text" class="inputTextMediumBlue" name="search_dkse_knr" id="search_dkse_knr" size="10" maxlength="8" value='${model.search_dkse_knr}'>
					&nbsp;&nbsp;Varekode&nbsp;
					<input type="text" class="inputTextMediumBlue" name="search_dkse_331" id="search_dkse_331" size="12" maxlength="10" value='${model.search_dkse_331}'>
					
					<%--
					&nbsp;Søkebegrep&nbsp;
					<input type="text" class="inputTextMediumBlue" name="searchTaalfa" id="searchTaalfa" size="15" maxlength="25" value='${model.taalfa}'>
					--%>
					&nbsp;&nbsp;<input onClick="setBlockUI(this);" class="inputFormSubmit" type="submit" name="submitSearch" id="submitSearch" value='<spring:message code="systema.skat.search"/>'/>
					
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
								<th class="tableHeaderField" >&nbsp;Kundenr&nbsp;</th>
			                    <th class="tableHeaderField" >&nbsp;Varekode&nbsp;</th>
			                    <th class="tableHeaderField" >&nbsp;Land&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;Certifikat&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;Nummer&nbsp;</th>
			                    <th align="center" class="tableHeaderField">Fjern</th>
			                </tr>  
			                </thead> 
			                <tbody >  
				            <c:forEach var="record" items="${model.list}" varStatus="counter">   
				               <tr class="tableRow" height="20" >
				               <td id="recordUpdate_${record.dkse_knr}_${record.dkse_331}" onClick="getRecord(this);" align="center" width="2%" class="tableCellFirst" style="cursor:pointer; border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;">
		               				<img src="resources/images/lock.gif" border="0" alt="locked">
				               </td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 1px;border-color:#FAEBD7;"><font class="text12">&nbsp;${record.dkse_knr}&nbsp;</font></td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.dkse_331}&nbsp;</font></td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.dkse_34}&nbsp;</font></td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.dkse_4421}&nbsp;</font></td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.dkse_442A}&nbsp;</font></td>
				               
				               <td align="center" width="2%" class="tableCell" style="cursor:pointer; border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;">
		               				<a onclick="javascript:return confirm('Er du sikker på at du vil fjerne denne?')" tabindex=-1 href="skatmaintenanceimport_dkt058r_edit.do?action=doDelete&id=${model.dbTable}&dkse_knr=${record.dkse_knr}&dkse_331=${record.dkse_331}&dkse_34=${record.dkse_34}&dkse_4421=${record.dkse_4421}">
					               		<img valign="bottom" src="resources/images/delete.gif" border="0" width="15px" height="15px" alt="remove">
					               	</a>
				               </td>
				            </tr> 
				            </c:forEach>
				            </tbody>
				            <tfoot>
							<tr>
							    <th align="center" width="2%" class="tableHeaderFieldWhiteBg11" >&nbsp;Ændre&nbsp;</th>
								<th align="center" class="tableHeaderFieldWhiteBg11" >&nbsp;DKSE_KNR</th>
			                    <th align="center" class="tableHeaderFieldWhiteBg11" >&nbsp;DKSE_331&nbsp;</th>
			                    <th align="center" class="tableHeaderFieldWhiteBg11" >&nbsp;DKSE_34&nbsp;</th>
			                    <th align="center" class="tableHeaderFieldWhiteBg11" >&nbsp;DKSE_4421&nbsp;</th>
			                    <th align="center" class="tableHeaderFieldWhiteBg11" >&nbsp;DKSE_442A&nbsp;</th>
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
				<form action="skatmaintenanceimport_dkt058r_edit.do" name="formRecord" id="formRecord" method="POST" >
					<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
					<input type="hidden" name="updateId" id=updateId value="${model.updateId}"> 
					<input type="hidden" name="action" id=action value="doUpdate">
					<input type="hidden" name="companyCode" id="companyCode" value="${user.companyCode}">
					
					
					<table width="80%" cellspacing="1" border="0" align="left">
			    	    <tr>
							<td class="text12" title="DKSE_KNR">&nbsp;<font class="text14RedBold" >*</font>Kundenr</td>
							<td class="text12" title="DKSE_331">&nbsp;<font class="text14RedBold" >*</font>Varekode</td>
							<td class="text12" title="DKSE_34">&nbsp;Land</td>
							<td class="text12" title="DKSE_4421">&nbsp;Certifikat</td>
							<td class="text12" title="DKSE_442A">&nbsp;Nummer</td>
						</tr>
						<tr>
						<td ><input onKeyPress="return numberKey(event)" type="text" required oninvalid="this.setCustomValidity('Obligatoriskt')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="dkse_knr" id="dkse_knr" size="10" maxlength="8" value='${model.record.dkse_knr}'>
							<a tabindex="-1" id="dkse_knrIdLink">
								<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
							</a>
						
						</td>
						<td ><input type="text" required oninvalid="this.setCustomValidity('Obligatoriskt')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="dkse_331" id="dkse_331" size="11" maxlength="10" value='${model.record.dkse_331}'></td>
						<td ><input type="text" class="inputTextMediumBlue" name="dkse_34" id="dkse_34" size="3" maxlength="2" value='${model.record.dkse_34}'></td>
						<td ><input type="text" class="inputTextMediumBlue" name="dkse_4421" id="dkse_4421" size="5" maxlength="4" value='${model.record.dkse_4421}'></td>
						<td ><input type="text" class="inputTextMediumBlue" name="dkse_442A" id="dkse_442A" size="30" maxlength="35" value='${model.record.dkse_442A}'></td>
						<td>
							<input class="inputFormSubmit" type="submit" name="submit" id="submit" value='Spare'/>
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

