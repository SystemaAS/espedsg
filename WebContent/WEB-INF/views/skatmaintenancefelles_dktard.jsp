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
						<font class="tabLink">Toldtariffen - Taric</font>&nbsp;<font class="text12">DKTARD</font>&nbsp;
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
					<input type="text" class="inputTextMediumBlue" name="searchTatanr" id="searchTatanr" size="9" maxlength="8" value='${model.tatanr}'>
					&nbsp;Søkebegrep&nbsp;
					<input type="text" class="inputTextMediumBlue" name="searchTaalfa" id="searchTaalfa" size="15" maxlength="25" value='${model.taalfa}'>
					&nbsp;&nbsp;<input onClick="setBlockUI(this);" class="inputFormSubmit" type="submit" name="submit" value='Søk'/>
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
			                    <th class="tableHeaderField">&nbsp;Toldsats&nbsp;</th>
			                    <th class="tableHeaderField">&nbsp;Supplerende mængde.&nbsp;</th>
			                    <th class="tableHeaderField">&nbsp;Varekodetekst&nbsp;</th>
			                    <th align="center" class="tableHeaderField">Slett</th>
			                </tr>  
			                </thead> 
			                <tbody >  
				            <c:forEach var="record" items="${Xmodel.list}" varStatus="counter">   
				               <tr class="tableRow" height="20" >
				               <td id="recordUpdate_${record.tatanr}_${record.taalfa}" onClick="getRecord(this);" align="center" width="2%" class="tableCellFirst" style="cursor:pointer; border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;">
		               				<img src="resources/images/update.gif" border="0" alt="edit">
				               </td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 1px;border-color:#FAEBD7;"><font class="text12">&nbsp;${record.tatanr}&nbsp;</font></td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.taordb}&nbsp;</font></td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.taordk}&nbsp;</font></td>
		                       <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.taeftb}&nbsp;</font></td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.taeftk}&nbsp;</font></td>
		                       <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.taefb}&nbsp;</font></td>
		                       <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.taefb}&nbsp;</font></td>
		                       <td align="center" width="2%" class="tableCell" style="cursor:pointer; border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;">
		               				<a onclick="javascript:return confirm('Er du sikker på at du vil slette denne?')" tabindex=-1 href="tvinnsadmaintenancefelles_sad010r_edit.do?action=doDelete&id=${model.dbTable}&tatanr=${record.tatanr}&taalfa=${record.taalfa}">
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
			                    <th align="center" class="tableHeaderFieldWhiteBg11">Slett</th>
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
					<input type="hidden" name="taalfaOrig" id=taalfaOrig value="${XXmodel.record.taalfaOrig}">
					
					<table width="98%" cellspacing="1" border="0" align="left">
			    	    <tr>
							<td class="text12" title="DKTARD01">&nbsp;<font class="text14RedBold" >*</font>Varekode</td>
							<td class="text12" title="DKTARD02">&nbsp;<font class="text14RedBold" >*</font>Start dato</td>
							<td class="text12" title="DKTARD03">&nbsp;Slut dato</td>
							<td class="text12" title="DKTARD04">&nbsp;Toldsatstype</td>
							<td class="text12" title="DKTARD05">&nbsp;Toldsats</td>
							<td class="text12" title="DKTARD47">&nbsp;Supplerende mængde</td>
							<td class="text12" title="DKTARD48">&nbsp;Varekodetext</td>
							
						</tr>
						<tr>
						<td ><input type="text" class="inputTextMediumBlueMandatoryField" name="dktard01" id="dktard01" size="11" maxlength="10" value='${XXmodel.record.tatanr}'></td>
						<td ><input type="text" class="inputTextMediumBlueMandatoryField" name="dktard02" id="dktard02" size="10" maxlength="8" value='${XXmodel.record.taalfa}'></td>
						<td ><input type="text" class="inputTextMediumBlue" name="dktard03" id="dktard03" size="10" maxlength="8" value='${XXmodel.record.tadatoNO}'></td>
						<td ><input type="text" class="inputTextMediumBlue" name="dktard04" id="dktard04" size="3" maxlength="2" value='${XXmodel.record.tadtsNO}'></td>
						<td ><input type="text" class="inputTextMediumBlue" name="dktard05" id="dktard05" size="8" maxlength="7" value='${XXmodel.record.tadtsNO}'></td>
						<td ><input type="text" class="inputTextMediumBlue" name="dktard47" id="dktard47" size="4" maxlength="3" value='${XXmodel.record.tadtsNO}'></td>
						<td ><input type="text" class="inputTextMediumBlue" name="dktard48" id="dktard48" size="20" maxlength="182" value='${XXmodel.record.tadtsNO}'></td>
						<%--
						<td >
							<select name="tastk" id="tastk">
        		    			<option value="">-velg-</option>
							  	<option value="J"<c:if test="${ XXmodel.record.tastk == 'J'}"> selected </c:if> >Ja</option>
							</select>
						</td>
						 --%>
						
						<td>
							<input onClick="setBlockUI(this);" class="inputFormSubmit" type="submit" name="submit" value='Lagre'/>
						</td>
						</tr>
						<tr height="3"><td></td>
					</table>
					
					
					<table class="tableHeaderField" width="98%" cellspacing="1" border="0" align="left">	
						<%--
						------------------------ 
						 FIRST  SECONDARY LINE
						------------------------ 
						--%>
						<tr>
							<td class="text12" title="TAORDB">&nbsp;Ordinær</td>
							<td class="text12" title="TAORDK">&nbsp;PVA</td>
							<td class="text12" title="TAEFTB">&nbsp;EFTA</td>
							<td class="text12" title="TAEFTK">&nbsp;PVA</td>
							<td class="text12" title="TAEFB">&nbsp;EF</td>
							<td class="text12" title="TAEFK">&nbsp;PVA</td>
							
						</tr>

						<tr>
						<td ><input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue" name="taordb" id="taordb" size="10" maxlength="10" value='${Xmodel.record.taordb}'></td>
						<td >
							<select name="taordk" id="taordk">
        		    			<option value="">-velg-</option>
							  	<option value="A"<c:if test="${ Xmodel.record.taordk == 'A'}"> selected </c:if> >A</option>
							  	<option value="F"<c:if test="${ Xmodel.record.taordk == 'F'}"> selected </c:if> >F</option>
							  	<option value="P"<c:if test="${ Xmodel.record.taordk == 'P'}"> selected </c:if> >P</option>		
							  	<option value="V"<c:if test="${ Xmodel.record.taordk == 'V'}"> selected </c:if> >V</option>
							  	<option value="W"<c:if test="${ Xmodel.record.taordk == 'W'}"> selected </c:if> >W</option>
							</select>
						</td>
						<td ><input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue" name="taeftb" id="taeftb" size="10" maxlength="10" value='${Xmodel.record.taeftb}'></td>
						<td >
							<select name="taeftk" id="taeftk">
        		    			<option value="">-velg-</option>
							  	<option value="A"<c:if test="${ Xmodel.record.taeftk == 'A'}"> selected </c:if> >A</option>
							  	<option value="F"<c:if test="${ Xmodel.record.taeftk == 'F'}"> selected </c:if> >F</option>
							  	<option value="P"<c:if test="${ Xmodel.record.taeftk == 'P'}"> selected </c:if> >P</option>		
							  	<option value="V"<c:if test="${ Xmodel.record.taeftk == 'V'}"> selected </c:if> >V</option>
							  	<option value="W"<c:if test="${ Xmodel.record.taeftk == 'W'}"> selected </c:if> >W</option>
							</select>
						</td>
						<td ><input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue" name="taefb" id="taefb" size="10" maxlength="10" value='${Xmodel.record.taefb}'></td>
						<td >
							<select name="taefk" id="taefk">
        		    			<option value="">-velg-</option>
							  	<option value="A"<c:if test="${ Xmodel.record.taefk == 'A'}"> selected </c:if> >A</option>
							  	<option value="F"<c:if test="${ Xmodel.record.taefk == 'F'}"> selected </c:if> >F</option>
							  	<option value="P"<c:if test="${ Xmodel.record.taefk == 'P'}"> selected </c:if> >P</option>		
							  	<option value="V"<c:if test="${ Xmodel.record.taefk == 'V'}"> selected </c:if> >V</option>
							  	<option value="W"<c:if test="${ Xmodel.record.taefk == 'W'}"> selected </c:if> >W</option>
							</select>
						</td>
						
						</tr>
						<%--
						------------------------ 
						 SECOND  SECONDARY LINE
						------------------------ 
						--%>
						<tr height="10"><td></td></tr>
						<tr >
							<td class="text12" title="TAEOSB">&nbsp;EØS</td>
							<td class="text12" title="TAEOSK">&nbsp;PVA</td>
							<td class="text12" title="TATSJB">&nbsp;Swaziland</td>
							<td class="text12" title="TATSJK">&nbsp;PVA</td>
							<td class="text12" title="TATYRB">&nbsp;Tyrkia</td>
							<td class="text12" title="TATYRK">&nbsp;PVA</td>
							<td class="text12" title="TAISRB">&nbsp;Israel</td>
							<td class="text12" title="TAISRK">&nbsp;PVA</td>
							<td class="text12" title="TAELLB">&nbsp;Singapore</td>
							<td class="text12" title="TAELLK">&nbsp;PVA</td>
							<td class="text12" title="TABULB">&nbsp;Chile</td>
							<td class="text12" title="TABULK">&nbsp;PVA</td>
							
						</tr>
						<tr >
							<td ><input type="text" class="inputTextMediumBlue" name="taeosb" id="taeosb" size="10" maxlength="10" value='${Xmodel.record.taeosb}'></td>
							<td>
								<select name="taeosk" id="taeosk">
        		    			<option value="">-velg-</option>
							  	<option value="A"<c:if test="${ Xmodel.record.taeosk == 'A'}"> selected </c:if> >A</option>
							  	<option value="F"<c:if test="${ Xmodel.record.taeosk == 'F'}"> selected </c:if> >F</option>
							  	<option value="P"<c:if test="${ Xmodel.record.taeosk == 'P'}"> selected </c:if> >P</option>		
							  	<option value="V"<c:if test="${ Xmodel.record.taeosk == 'V'}"> selected </c:if> >V</option>
							  	<option value="W"<c:if test="${ Xmodel.record.taeosk == 'W'}"> selected </c:if> >W</option>
								</select>
							</td>
							<td ><input type="text" class="inputTextMediumBlue" name="tatsjb" id="tatsjb" size="10" maxlength="10" value='${Xmodel.record.tatsjb}'></td>
							<td>
								<select name="tatsjk" id="tatsjk">
        		    			<option value="">-velg-</option>
							  	<option value="A"<c:if test="${ Xmodel.record.tatsjk == 'A'}"> selected </c:if> >A</option>
							  	<option value="F"<c:if test="${ Xmodel.record.tatsjk == 'F'}"> selected </c:if> >F</option>
							  	<option value="P"<c:if test="${ Xmodel.record.tatsjk == 'P'}"> selected </c:if> >P</option>		
							  	<option value="V"<c:if test="${ Xmodel.record.tatsjk == 'V'}"> selected </c:if> >V</option>
							  	<option value="W"<c:if test="${ Xmodel.record.tatsjk == 'W'}"> selected </c:if> >W</option>
								</select>
							</td>
							<td ><input type="text" class="inputTextMediumBlue" name="tatyrb" id="tatyrb" size="10" maxlength="10" value='${Xmodel.record.tatyrb}'></td>
							<td>
								<select name="tatyrk" id="tatyrk">
        		    			<option value="">-velg-</option>
							  	<option value="A"<c:if test="${ Xmodel.record.tatyrk == 'A'}"> selected </c:if> >A</option>
							  	<option value="F"<c:if test="${ Xmodel.record.tatyrk == 'F'}"> selected </c:if> >F</option>
							  	<option value="P"<c:if test="${ Xmodel.record.tatyrk == 'P'}"> selected </c:if> >P</option>		
							  	<option value="V"<c:if test="${ Xmodel.record.tatyrk == 'V'}"> selected </c:if> >V</option>
							  	<option value="W"<c:if test="${ Xmodel.record.tatyrk == 'W'}"> selected </c:if> >W</option>
								</select>
							</td>
							<td ><input type="text" class="inputTextMediumBlue" name="taisrb" id="taisrb" size="10" maxlength="10" value='${Xmodel.record.taisrb}'></td>
							<td>
								<select name="taisrk" id="taisrk">
        		    			<option value="">-velg-</option>
							  	<option value="A"<c:if test="${ Xmodel.record.taisrk == 'A'}"> selected </c:if> >A</option>
							  	<option value="F"<c:if test="${ Xmodel.record.taisrk == 'F'}"> selected </c:if> >F</option>
							  	<option value="P"<c:if test="${ Xmodel.record.taisrk == 'P'}"> selected </c:if> >P</option>		
							  	<option value="V"<c:if test="${ Xmodel.record.taisrk == 'V'}"> selected </c:if> >V</option>
							  	<option value="W"<c:if test="${ Xmodel.record.taisrk == 'W'}"> selected </c:if> >W</option>
								</select>
							</td>
							<td ><input type="text" class="inputTextMediumBlue" name="taellb" id="taellb" size="10" maxlength="10" value='${Xmodel.record.taellb}'></td>
							<td>
								<select name="taellk" id="taellk">
        		    			<option value="">-velg-</option>
							  	<option value="A"<c:if test="${ Xmodel.record.taellk == 'A'}"> selected </c:if> >A</option>
							  	<option value="F"<c:if test="${ Xmodel.record.taellk == 'F'}"> selected </c:if> >F</option>
							  	<option value="P"<c:if test="${ Xmodel.record.taellk == 'P'}"> selected </c:if> >P</option>		
							  	<option value="V"<c:if test="${ Xmodel.record.taellk == 'V'}"> selected </c:if> >V</option>
							  	<option value="W"<c:if test="${ Xmodel.record.taellk == 'W'}"> selected </c:if> >W</option>
								</select>
							</td>
							<td ><input type="text" class="inputTextMediumBlue" name="tabulb" id="tabulb" size="10" maxlength="10" value='${Xmodel.record.tabulb}'></td>
							<td>
								<select name="tabulk" id="tabulk">
        		    			<option value="">-velg-</option>
							  	<option value="A"<c:if test="${ Xmodel.record.tabulk == 'A'}"> selected </c:if> >A</option>
							  	<option value="F"<c:if test="${ Xmodel.record.tabulk == 'F'}"> selected </c:if> >F</option>
							  	<option value="P"<c:if test="${ Xmodel.record.tabulk == 'P'}"> selected </c:if> >P</option>		
							  	<option value="V"<c:if test="${ Xmodel.record.tabulk == 'V'}"> selected </c:if> >V</option>
							  	<option value="W"<c:if test="${ Xmodel.record.tabulk == 'W'}"> selected </c:if> >W</option>
								</select>
							</td>
							
						</tr>
						<%--
						------------------------ 
						 3:d  SECONDARY LINE
						------------------------ 
						--%>
						<tr>
							<td class="text12" title="TAPOLB">&nbsp;Makedonia</td>
							<td class="text12" title="TAPOLK">&nbsp;PVA</td>
							<td class="text12" title="TAROMB">&nbsp;Sør-Korea</td>
							<td class="text12" title="TAROMK">&nbsp;PVA</td>
							<td class="text12" title="TAN05B">&nbsp;Serbia</td>
							<td class="text12" title="TAN05K">&nbsp;PVA</td>
							<td class="text12" title="TAN06B">&nbsp;Albania</td>
							<td class="text12" title="TAN06K">&nbsp;PVA</td>
							<td class="text12" title="TAN07B">&nbsp;Ukraina</td>
							<td class="text12" title="TAN07K">&nbsp;PVA</td>
							<td class="text12" title="TAUNGB">&nbsp;Jordan</td>
							<td class="text12" title="TAUNGK">&nbsp;PVA</td>
						</tr>
						<tr>
							<td ><input type="text" class="inputTextMediumBlue" name="tapolb" id="tapolb" size="10" maxlength="10" value='${Xmodel.record.tapolb}'></td>
							<td>
								<select name="tapolk" id="tapolk">
        		    			<option value="">-velg-</option>
							  	<option value="A"<c:if test="${ Xmodel.record.tapolk == 'A'}"> selected </c:if> >A</option>
							  	<option value="F"<c:if test="${ Xmodel.record.tapolk == 'F'}"> selected </c:if> >F</option>
							  	<option value="P"<c:if test="${ Xmodel.record.tapolk == 'P'}"> selected </c:if> >P</option>		
							  	<option value="V"<c:if test="${ Xmodel.record.tapolk == 'V'}"> selected </c:if> >V</option>
							  	<option value="W"<c:if test="${ Xmodel.record.tapolk == 'W'}"> selected </c:if> >W</option>
								</select>
							</td>
							<td ><input type="text" class="inputTextMediumBlue" name="taromb" id="taromb" size="10" maxlength="10" value='${Xmodel.record.taromb}'></td>
							<td>
								<select name="taromk" id="taromk">
        		    			<option value="">-velg-</option>
							  	<option value="A"<c:if test="${ Xmodel.record.taromk == 'A'}"> selected </c:if> >A</option>
							  	<option value="F"<c:if test="${ Xmodel.record.taromk == 'F'}"> selected </c:if> >F</option>
							  	<option value="P"<c:if test="${ Xmodel.record.taromk == 'P'}"> selected </c:if> >P</option>		
							  	<option value="V"<c:if test="${ Xmodel.record.taromk == 'V'}"> selected </c:if> >V</option>
							  	<option value="W"<c:if test="${ Xmodel.record.taromk == 'W'}"> selected </c:if> >W</option>
								</select>
							</td>
							<td ><input type="text" class="inputTextMediumBlue" name="tan05b" id="tan05b" size="10" maxlength="10" value='${Xmodel.record.tan05b}'></td>
							<td>
								<select name="tan05k" id="tan05k">
        		    			<option value="">-velg-</option>
							  	<option value="A"<c:if test="${ Xmodel.record.tan05k == 'A'}"> selected </c:if> >A</option>
							  	<option value="F"<c:if test="${ Xmodel.record.tan05k == 'F'}"> selected </c:if> >F</option>
							  	<option value="P"<c:if test="${ Xmodel.record.tan05k == 'P'}"> selected </c:if> >P</option>		
							  	<option value="V"<c:if test="${ Xmodel.record.tan05k == 'V'}"> selected </c:if> >V</option>
							  	<option value="W"<c:if test="${ Xmodel.record.tan05k == 'W'}"> selected </c:if> >W</option>
								</select>
							</td>
							<td ><input type="text" class="inputTextMediumBlue" name="tan06b" id="tan06b" size="10" maxlength="10" value='${Xmodel.record.tan06b}'></td>
							<td>
								<select name="tan06k" id="tan06k">
        		    			<option value="">-velg-</option>
							  	<option value="A"<c:if test="${ Xmodel.record.tan06k == 'A'}"> selected </c:if> >A</option>
							  	<option value="F"<c:if test="${ Xmodel.record.tan06k == 'F'}"> selected </c:if> >F</option>
							  	<option value="P"<c:if test="${ Xmodel.record.tan06k == 'P'}"> selected </c:if> >P</option>		
							  	<option value="V"<c:if test="${ Xmodel.record.tan06k == 'V'}"> selected </c:if> >V</option>
							  	<option value="W"<c:if test="${ Xmodel.record.tan06k == 'W'}"> selected </c:if> >W</option>
								</select>
							</td>
							<td ><input type="text" class="inputTextMediumBlue" name="tan07b" id="tan07b" size="10" maxlength="10" value='${Xmodel.record.tan07b}'></td>
							<td>
								<select name="tan07k" id="tan07k">
        		    			<option value="">-velg-</option>
							  	<option value="A"<c:if test="${ Xmodel.record.tan07k == 'A'}"> selected </c:if> >A</option>
							  	<option value="F"<c:if test="${ Xmodel.record.tan07k == 'F'}"> selected </c:if> >F</option>
							  	<option value="P"<c:if test="${ Xmodel.record.tan07k == 'P'}"> selected </c:if> >P</option>		
							  	<option value="V"<c:if test="${ Xmodel.record.tan07k == 'V'}"> selected </c:if> >V</option>
							  	<option value="W"<c:if test="${ Xmodel.record.tan07k == 'W'}"> selected </c:if> >W</option>
								</select>
							</td>
							<td ><input type="text" class="inputTextMediumBlue" name="taungb" id="taungb" size="10" maxlength="10" value='${Xmodel.record.taungb}'></td>
							<td>
								<select name="taungk" id="taungk">
        		    			<option value="">-velg-</option>
							  	<option value="A"<c:if test="${ Xmodel.record.taungk == 'A'}"> selected </c:if> >A</option>
							  	<option value="F"<c:if test="${ Xmodel.record.taungk == 'F'}"> selected </c:if> >F</option>
							  	<option value="P"<c:if test="${ Xmodel.record.taungk == 'P'}"> selected </c:if> >P</option>		
							  	<option value="V"<c:if test="${ Xmodel.record.taungk == 'V'}"> selected </c:if> >V</option>
							  	<option value="W"<c:if test="${ Xmodel.record.taungk == 'W'}"> selected </c:if> >W</option>
								</select>
							</td>
						</tr>
						
						<%--
						------------------------ 
						 4:th  SECONDARY LINE
						------------------------ 
						--%>
						<tr>
							<td class="text12" title="TASLOB">&nbsp;Tunisia</td>
							<td class="text12" title="TASLOK">&nbsp;PVA</td>
							<td class="text12" title="TAMULB">&nbsp;Min.uland</td>
							<td class="text12" title="TAMULK">&nbsp;PVA</td>
							<td class="text12" title="TAOULB">&nbsp;Ord.uland</td>
							<td class="text12" title="TAOULK">&nbsp;PVA</td>
							<td class="text12" title="TAGRLB">&nbsp;Grønnland</td>
							<td class="text12" title="TAGRLK">&nbsp;PVA</td>
							<td class="text12" title="TAFERB">&nbsp;Færøyen</td>
							<td class="text12" title="TAFERK">&nbsp;PVA</td>
							<td class="text12" title="TAISTB">&nbsp;IndiaSrTh</td>
							<td class="text12" title="TAISTK">&nbsp;PVA</td>
						</tr>
						<tr>
							<td ><input type="text" class="inputTextMediumBlue" name="taslob" id="taslob" size="10" maxlength="10" value='${Xmodel.record.taslob}'></td>
							<td>
								<select name="taslok" id="taslok">
        		    			<option value="">-velg-</option>
							  	<option value="A"<c:if test="${ Xmodel.record.taslok == 'A'}"> selected </c:if> >A</option>
							  	<option value="F"<c:if test="${ Xmodel.record.taslok == 'F'}"> selected </c:if> >F</option>
							  	<option value="P"<c:if test="${ Xmodel.record.taslok == 'P'}"> selected </c:if> >P</option>		
							  	<option value="V"<c:if test="${ Xmodel.record.taslok == 'V'}"> selected </c:if> >V</option>
							  	<option value="W"<c:if test="${ Xmodel.record.taslok == 'W'}"> selected </c:if> >W</option>
								</select>
							</td>
							<td ><input type="text" class="inputTextMediumBlue" name="tamulb" id="tamulb" size="10" maxlength="10" value='${Xmodel.record.tamulb}'></td>
							<td>
								<select name="tamulk" id="tamulk">
        		    			<option value="">-velg-</option>
							  	<option value="A"<c:if test="${ Xmodel.record.tamulk == 'A'}"> selected </c:if> >A</option>
							  	<option value="F"<c:if test="${ Xmodel.record.tamulk == 'F'}"> selected </c:if> >F</option>
							  	<option value="P"<c:if test="${ Xmodel.record.tamulk == 'P'}"> selected </c:if> >P</option>		
							  	<option value="V"<c:if test="${ Xmodel.record.tamulk == 'V'}"> selected </c:if> >V</option>
							  	<option value="W"<c:if test="${ Xmodel.record.tamulk == 'W'}"> selected </c:if> >W</option>
								</select>
							</td>
							<td ><input type="text" class="inputTextMediumBlue" name="taoulb" id="taoulb" size="10" maxlength="10" value='${Xmodel.record.taoulb}'></td>
							<td>
								<select name="taoulk" id="taoulk">
        		    			<option value="">-velg-</option>
							  	<option value="A"<c:if test="${ Xmodel.record.taoulk == 'A'}"> selected </c:if> >A</option>
							  	<option value="F"<c:if test="${ Xmodel.record.taoulk == 'F'}"> selected </c:if> >F</option>
							  	<option value="P"<c:if test="${ Xmodel.record.taoulk == 'P'}"> selected </c:if> >P</option>		
							  	<option value="V"<c:if test="${ Xmodel.record.taoulk == 'V'}"> selected </c:if> >V</option>
							  	<option value="W"<c:if test="${ Xmodel.record.taoulk == 'W'}"> selected </c:if> >W</option>
								</select>
							</td>
							<td ><input type="text" class="inputTextMediumBlue" name="tagrlb" id="tagrlb" size="10" maxlength="10" value='${Xmodel.record.tagrlb}'></td>
							<td>
								<select name="tagrlk" id="tagrlk">
        		    			<option value="">-velg-</option>
							  	<option value="A"<c:if test="${ Xmodel.record.tagrlk == 'A'}"> selected </c:if> >A</option>
							  	<option value="F"<c:if test="${ Xmodel.record.tagrlk == 'F'}"> selected </c:if> >F</option>
							  	<option value="P"<c:if test="${ Xmodel.record.tagrlk == 'P'}"> selected </c:if> >P</option>		
							  	<option value="V"<c:if test="${ Xmodel.record.tagrlk == 'V'}"> selected </c:if> >V</option>
							  	<option value="W"<c:if test="${ Xmodel.record.tagrlk == 'W'}"> selected </c:if> >W</option>
								</select>
							</td>
							<td ><input type="text" class="inputTextMediumBlue" name="taferb" id="taferb" size="10" maxlength="10" value='${Xmodel.record.taferb}'></td>
							<td>
								<select name="taferk" id="taferk">
        		    			<option value="">-velg-</option>
							  	<option value="A"<c:if test="${ Xmodel.record.taferk == 'A'}"> selected </c:if> >A</option>
							  	<option value="F"<c:if test="${ Xmodel.record.taferk == 'F'}"> selected </c:if> >F</option>
							  	<option value="P"<c:if test="${ Xmodel.record.taferk == 'P'}"> selected </c:if> >P</option>		
							  	<option value="V"<c:if test="${ Xmodel.record.taferk == 'V'}"> selected </c:if> >V</option>
							  	<option value="W"<c:if test="${ Xmodel.record.taferk == 'W'}"> selected </c:if> >W</option>
								</select>
							</td>
							<td ><input type="text" class="inputTextMediumBlue" name="taistb" id="taistb" size="10" maxlength="10" value='${Xmodel.record.taistb}'></td>
							<td>
								<select name="taistk" id="taistk">
        		    			<option value="">-velg-</option>
							  	<option value="A"<c:if test="${ Xmodel.record.taistk == 'A'}"> selected </c:if> >A</option>
							  	<option value="F"<c:if test="${ Xmodel.record.taistk == 'F'}"> selected </c:if> >F</option>
							  	<option value="P"<c:if test="${ Xmodel.record.taistk == 'P'}"> selected </c:if> >P</option>		
							  	<option value="V"<c:if test="${ Xmodel.record.taistk == 'V'}"> selected </c:if> >V</option>
							  	<option value="W"<c:if test="${ Xmodel.record.taistk == 'W'}"> selected </c:if> >W</option>
								</select>
							</td>
						</tr>
						
						<%--
						------------------------ 
						 5:th  SECONDARY LINE
						------------------------ 
						--%>
						<tr>
							<td class="text12" title="TAMARB">&nbsp;Marokka</td>
							<td class="text12" title="TAMARK">&nbsp;PVA</td>
							<td class="text12" title="TAN08B">&nbsp;Peru</td>
							<td class="text12" title="TAN08K">&nbsp;PVA</td>
							<td class="text12" title="TAN09B">&nbsp;Montenegro</td>
							<td class="text12" title="TAN09K">&nbsp;PVA</td>
							<td class="text12" title="TAN10B">&nbsp;Hong Kong</td>
							<td class="text12" title="TAN10K">&nbsp;PVA</td>
							<td class="text12" title="TAMEXB">&nbsp;México</td>
							<td class="text12" title="TAMEXK">&nbsp;PVA</td>
							<td class="text12" title="TAVGAB">&nbsp;VB Gaza</td>
							<td class="text12" title="TAVGAK">&nbsp;PVA</td>
						</tr>
						<tr>
							<td ><input type="text" class="inputTextMediumBlue" name="tamarb" id="tamarb" size="10" maxlength="10" value='${Xmodel.record.tamarb}'></td>
							<td>
								<select name="tamark" id="tamark">
        		    			<option value="">-velg-</option>
							  	<option value="A"<c:if test="${ Xmodel.record.tamark == 'A'}"> selected </c:if> >A</option>
							  	<option value="F"<c:if test="${ Xmodel.record.tamark == 'F'}"> selected </c:if> >F</option>
							  	<option value="P"<c:if test="${ Xmodel.record.tamark == 'P'}"> selected </c:if> >P</option>		
							  	<option value="V"<c:if test="${ Xmodel.record.tamark == 'V'}"> selected </c:if> >V</option>
							  	<option value="W"<c:if test="${ Xmodel.record.tamark == 'W'}"> selected </c:if> >W</option>
								</select>
							</td>
							<td ><input type="text" class="inputTextMediumBlue" name="tan08b" id="tan08b" size="10" maxlength="10" value='${Xmodel.record.tan08b}'></td>
							<td>
								<select name="tan08k" id="tan08k">
        		    			<option value="">-velg-</option>
							  	<option value="A"<c:if test="${ Xmodel.record.tan08k == 'A'}"> selected </c:if> >A</option>
							  	<option value="F"<c:if test="${ Xmodel.record.tan08k == 'F'}"> selected </c:if> >F</option>
							  	<option value="P"<c:if test="${ Xmodel.record.tan08k == 'P'}"> selected </c:if> >P</option>		
							  	<option value="V"<c:if test="${ Xmodel.record.tan08k == 'V'}"> selected </c:if> >V</option>
							  	<option value="W"<c:if test="${ Xmodel.record.tan08k == 'W'}"> selected </c:if> >W</option>
								</select>
							</td>
							<td ><input type="text" class="inputTextMediumBlue" name="tan09b" id="tan09b" size="10" maxlength="10" value='${Xmodel.record.tan09b}'></td>
							<td>
								<select name="tan09k" id="tan09k">
        		    			<option value="">-velg-</option>
							  	<option value="A"<c:if test="${ Xmodel.record.tan09k == 'A'}"> selected </c:if> >A</option>
							  	<option value="F"<c:if test="${ Xmodel.record.tan09k == 'F'}"> selected </c:if> >F</option>
							  	<option value="P"<c:if test="${ Xmodel.record.tan09k == 'P'}"> selected </c:if> >P</option>		
							  	<option value="V"<c:if test="${ Xmodel.record.tan09k == 'V'}"> selected </c:if> >V</option>
							  	<option value="W"<c:if test="${ Xmodel.record.tan09k == 'W'}"> selected </c:if> >W</option>
								</select>
							</td>
							<td ><input type="text" class="inputTextMediumBlue" name="tan10b" id="tan10b" size="10" maxlength="10" value='${Xmodel.record.tan10b}'></td>
							<td>
								<select name="tan10k" id="tan10k">
        		    			<option value="">-velg-</option>
							  	<option value="A"<c:if test="${ Xmodel.record.tan10k == 'A'}"> selected </c:if> >A</option>
							  	<option value="F"<c:if test="${ Xmodel.record.tan10k == 'F'}"> selected </c:if> >F</option>
							  	<option value="P"<c:if test="${ Xmodel.record.tan10k == 'P'}"> selected </c:if> >P</option>		
							  	<option value="V"<c:if test="${ Xmodel.record.tan10k == 'V'}"> selected </c:if> >V</option>
							  	<option value="W"<c:if test="${ Xmodel.record.tan10k == 'W'}"> selected </c:if> >W</option>
								</select>
							</td>
							<td ><input type="text" class="inputTextMediumBlue" name="tamexb" id="tamexb" size="10" maxlength="10" value='${Xmodel.record.tamexb}'></td>
							<td>
								<select name="tamexk" id="tamexk">
        		    			<option value="">-velg-</option>
							  	<option value="A"<c:if test="${ Xmodel.record.tamexk == 'A'}"> selected </c:if> >A</option>
							  	<option value="F"<c:if test="${ Xmodel.record.tamexk == 'F'}"> selected </c:if> >F</option>
							  	<option value="P"<c:if test="${ Xmodel.record.tamexk == 'P'}"> selected </c:if> >P</option>		
							  	<option value="V"<c:if test="${ Xmodel.record.tamexk == 'V'}"> selected </c:if> >V</option>
							  	<option value="W"<c:if test="${ Xmodel.record.tamexk == 'W'}"> selected </c:if> >W</option>
								</select>
							</td>
							<td ><input type="text" class="inputTextMediumBlue" name="tavgab" id="tavgab" size="10" maxlength="10" value='${Xmodel.record.tavgab}'></td>
							<td>
								<select name="tavgak" id="tavgak">
        		    			<option value="">-velg-</option>
							  	<option value="A"<c:if test="${ Xmodel.record.tavgak == 'A'}"> selected </c:if> >A</option>
							  	<option value="F"<c:if test="${ Xmodel.record.tavgak == 'F'}"> selected </c:if> >F</option>
							  	<option value="P"<c:if test="${ Xmodel.record.tavgak == 'P'}"> selected </c:if> >P</option>		
							  	<option value="V"<c:if test="${ Xmodel.record.tavgak == 'V'}"> selected </c:if> >V</option>
							  	<option value="W"<c:if test="${ Xmodel.record.tavgak == 'W'}"> selected </c:if> >W</option>
								</select>
							</td>
						</tr>
						
						<%--
						------------------------ 
						 6:th  SECONDARY LINE
						------------------------ 
						--%>
						<tr>
							<td class="text12" title="TAN01B">&nbsp;Libanon</td>
							<td class="text12" title="TAN01K">&nbsp;PVA</td>
							<td class="text12" title="TAN02B">&nbsp;SACU</td>
							<td class="text12" title="TAN02K">&nbsp;PVA</td>
							<td class="text12" title="TAN03B">&nbsp;Egypt</td>
							<td class="text12" title="TAN03K">&nbsp;PVA</td>
							<td class="text12" title="TAN04B">&nbsp;Canada</td>
							<td class="text12" title="TAN04K">&nbsp;PVA</td>
							<td class="text12" title="TAN11B">&nbsp;BW,NA,SZ</td>
							<td class="text12" title="TAN11K">&nbsp;PVA</td>
							<td class="text12" title="TAN12B">&nbsp;Lavere MIL</td>
							<td class="text12" title="TAN12K">&nbsp;PVA</td>
						</tr>
						<tr>
							<td ><input type="text" class="inputTextMediumBlue" name="tan01b" id="tan01b" size="10" maxlength="10" value='${Xmodel.record.tan01b}'></td>
							<td>
								<select name="tan01k" id="tan01k">
        		    			<option value="">-velg-</option>
							  	<option value="A"<c:if test="${ Xmodel.record.tan01k == 'A'}"> selected </c:if> >A</option>
							  	<option value="F"<c:if test="${ Xmodel.record.tan01k == 'F'}"> selected </c:if> >F</option>
							  	<option value="P"<c:if test="${ Xmodel.record.tan01k == 'P'}"> selected </c:if> >P</option>		
							  	<option value="V"<c:if test="${ Xmodel.record.tan01k == 'V'}"> selected </c:if> >V</option>
							  	<option value="W"<c:if test="${ Xmodel.record.tan01k == 'W'}"> selected </c:if> >W</option>
								</select>
							</td>
							<td ><input type="text" class="inputTextMediumBlue" name="tan02b" id="tan02b" size="10" maxlength="10" value='${Xmodel.record.tan02b}'></td>
							<td>
								<select name="tan02k" id="tan02k">
        		    			<option value="">-velg-</option>
							  	<option value="A"<c:if test="${ Xmodel.record.tan02k == 'A'}"> selected </c:if> >A</option>
							  	<option value="F"<c:if test="${ Xmodel.record.tan02k == 'F'}"> selected </c:if> >F</option>
							  	<option value="P"<c:if test="${ Xmodel.record.tan02k == 'P'}"> selected </c:if> >P</option>		
							  	<option value="V"<c:if test="${ Xmodel.record.tan02k == 'V'}"> selected </c:if> >V</option>
							  	<option value="W"<c:if test="${ Xmodel.record.tan02k == 'W'}"> selected </c:if> >W</option>
								</select>
							</td>
							<td ><input type="text" class="inputTextMediumBlue" name="tan03b" id="tan03b" size="10" maxlength="10" value='${Xmodel.record.tan03b}'></td>
							<td>
								<select name="tan03k" id="tan03k">
        		    			<option value="">-velg-</option>
							  	<option value="A"<c:if test="${ Xmodel.record.tan03k == 'A'}"> selected </c:if> >A</option>
							  	<option value="F"<c:if test="${ Xmodel.record.tan03k == 'F'}"> selected </c:if> >F</option>
							  	<option value="P"<c:if test="${ Xmodel.record.tan03k == 'P'}"> selected </c:if> >P</option>		
							  	<option value="V"<c:if test="${ Xmodel.record.tan03k == 'V'}"> selected </c:if> >V</option>
							  	<option value="W"<c:if test="${ Xmodel.record.tan03k == 'W'}"> selected </c:if> >W</option>
								</select>
							</td>
							<td ><input type="text" class="inputTextMediumBlue" name="tan04b" id="tan04b" size="10" maxlength="10" value='${Xmodel.record.tan04b}'></td>
							<td>
								<select name="tan04k" id="tan04k">
        		    			<option value="">-velg-</option>
							  	<option value="A"<c:if test="${ Xmodel.record.tan04k == 'A'}"> selected </c:if> >A</option>
							  	<option value="F"<c:if test="${ Xmodel.record.tan04k == 'F'}"> selected </c:if> >F</option>
							  	<option value="P"<c:if test="${ Xmodel.record.tan04k == 'P'}"> selected </c:if> >P</option>		
							  	<option value="V"<c:if test="${ Xmodel.record.tan04k == 'V'}"> selected </c:if> >V</option>
							  	<option value="W"<c:if test="${ Xmodel.record.tan04k == 'W'}"> selected </c:if> >W</option>
								</select>
							</td>
							<td ><input type="text" class="inputTextMediumBlue" name="tan11b" id="tan11b" size="10" maxlength="10" value='${Xmodel.record.tan11b}'></td>
							<td>
								<select name="tan11k" id="tan11k">
        		    			<option value="">-velg-</option>
							  	<option value="A"<c:if test="${ Xmodel.record.tan11k == 'A'}"> selected </c:if> >A</option>
							  	<option value="F"<c:if test="${ Xmodel.record.tan11k == 'F'}"> selected </c:if> >F</option>
							  	<option value="P"<c:if test="${ Xmodel.record.tan11k == 'P'}"> selected </c:if> >P</option>		
							  	<option value="V"<c:if test="${ Xmodel.record.tan11k == 'V'}"> selected </c:if> >V</option>
							  	<option value="W"<c:if test="${ Xmodel.record.tan11k == 'W'}"> selected </c:if> >W</option>
								</select>
							</td>
							<td ><input type="text" class="inputTextMediumBlue" name="tan12b" id="tan12b" size="10" maxlength="10" value='${Xmodel.record.tan12b}'></td>
							<td>
								<select name="tan12k" id="tan12k">
        		    			<option value="">-velg-</option>
							  	<option value="A"<c:if test="${ Xmodel.record.tan12k == 'A'}"> selected </c:if> >A</option>
							  	<option value="F"<c:if test="${ Xmodel.record.tan12k == 'F'}"> selected </c:if> >F</option>
							  	<option value="P"<c:if test="${ Xmodel.record.tan12k == 'P'}"> selected </c:if> >P</option>		
							  	<option value="V"<c:if test="${ Xmodel.record.tan12k == 'V'}"> selected </c:if> >V</option>
							  	<option value="W"<c:if test="${ Xmodel.record.tan12k == 'W'}"> selected </c:if> >W</option>
								</select>
							</td>
						</tr>
						
						<%--
						------------------------ 
						 7:th  SECONDARY LINE
						------------------------ 
						--%>
						<tr>
							<td class="text12" title="TAN13B">&nbsp;Reserver</td>
							<td class="text12" title="TAN13K">&nbsp;PVA</td>
							<td class="text12" title="TAN14B">&nbsp;Reserver</td>
							<td class="text12" title="TAN14K">&nbsp;PVA</td>
							<td class="text12" title="TAN15B">&nbsp;Reserver</td>
							<td class="text12" title="TAN15K">&nbsp;PVA</td>
						</tr>
						<tr>
							<td ><input type="text" class="inputTextMediumBlue" name="tan13b" id="tan13b" size="10" maxlength="10" value='${Xmodel.record.tan13b}'></td>
							<td>
								<select name="tan13k" id="tan13k">
        		    			<option value="">-velg-</option>
							  	<option value="A"<c:if test="${ Xmodel.record.tan13k == 'A'}"> selected </c:if> >A</option>
							  	<option value="F"<c:if test="${ Xmodel.record.tan13k == 'F'}"> selected </c:if> >F</option>
							  	<option value="P"<c:if test="${ Xmodel.record.tan13k == 'P'}"> selected </c:if> >P</option>		
							  	<option value="V"<c:if test="${ Xmodel.record.tan13k == 'V'}"> selected </c:if> >V</option>
							  	<option value="W"<c:if test="${ Xmodel.record.tan13k == 'W'}"> selected </c:if> >W</option>
								</select>
							</td>
							<td ><input type="text" class="inputTextMediumBlue" name="tan14b" id="tan14b" size="10" maxlength="10" value='${Xmodel.record.tan14b}'></td>
							<td>
								<select name="tan14k" id="tan14k">
        		    			<option value="">-velg-</option>
							  	<option value="A"<c:if test="${ Xmodel.record.tan14k == 'A'}"> selected </c:if> >A</option>
							  	<option value="F"<c:if test="${ Xmodel.record.tan14k == 'F'}"> selected </c:if> >F</option>
							  	<option value="P"<c:if test="${ Xmodel.record.tan14k == 'P'}"> selected </c:if> >P</option>		
							  	<option value="V"<c:if test="${ Xmodel.record.tan14k == 'V'}"> selected </c:if> >V</option>
							  	<option value="W"<c:if test="${ Xmodel.record.tan14k == 'W'}"> selected </c:if> >W</option>
								</select>
							</td>
							<td ><input type="text" class="inputTextMediumBlue" name="tan15b" id="tan15b" size="10" maxlength="10" value='${Xmodel.record.tan15b}'></td>
							<td>
								<select name="tan15k" id="tan15k">
        		    			<option value="">-velg-</option>
							  	<option value="A"<c:if test="${ Xmodel.record.tan15k == 'A'}"> selected </c:if> >A</option>
							  	<option value="F"<c:if test="${ Xmodel.record.tan15k == 'F'}"> selected </c:if> >F</option>
							  	<option value="P"<c:if test="${ Xmodel.record.tan15k == 'P'}"> selected </c:if> >P</option>		
							  	<option value="V"<c:if test="${ Xmodel.record.tan15k == 'V'}"> selected </c:if> >V</option>
							  	<option value="W"<c:if test="${ Xmodel.record.tan15k == 'W'}"> selected </c:if> >W</option>
								</select>
							</td>							
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

