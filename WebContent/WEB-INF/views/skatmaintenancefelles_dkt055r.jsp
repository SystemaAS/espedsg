<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerSkatMaintenance.jsp" />
<!-- =====================end header ==========================-->
	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/skatmaintenancefelles_dkt055r.js?ver=${user.versionEspedsg}"></SCRIPT>	
	
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
						<font class="tabLink">Firmaoplys.</font>&nbsp;<font class="text12">DKT055 / DKTFI</font>&nbsp;
						<a id="alinkRecordId_${counter.count}" onClick="setBlockUI(this);" href="skatmaintenancefelles_dkt055r.do?id=${model.dbTable}">
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
			<form action="skatmaintenancefelles_dkt055r_edit.do" name="formRecord" id="formRecord" method="POST" >
			<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
			<input type="hidden" name="updateId" id=updateId value="${model.updateId}"> 
			<input type="hidden" name="action" id=action value="doUpdate">
					
	 		<table width="100%" class="tabThinBorderWhite" border="0" cellspacing="0" cellpadding="0">
	 	    <tr height="30"><td>&nbsp;</td></tr>
	 	    
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
			<%--
			<tr >
				<td width="5%">&nbsp;</td>
				<td><button name="newRecordButton" id="newRecordButton" class="inputFormSubmitStd" type="button" >Opret ny</button></td>
			</tr>
			 --%>
	 	    <tr >
	 	    	<td width="5%">&nbsp;</td>
				<td width="100%">
					<table class=formFrameHeaderBlueWithBorder width="80%" cellspacing="0" border="0" align="left">
						<tr><td class="text12White" >&nbsp;TEST&nbsp;&nbsp;<img style="vertical-align: middle;"  src="resources/images/bulletGrey.png" border="0" width="10px" height="10px" alt="test"></td>
						</tr>
					</table>
					<table class="formFrame" width="80%" cellspacing="1" border="0" align="left">	
						<%--
						------------------------ 
						 TEST
						------------------------ 
						--%>
						<tr height="8"><td></td>
						<tr>
							<td class="text14" title=""><b>&nbsp;UNB</b>&nbsp;
								<img style="vertical-align: top;"  src="resources/images/checkmarkOK.png" border="0" width="12px" height="12px" alt="FTP">
							</td>	
						</tr>
						<tr>
							<td class="text12" title="dktf_0004t">&nbsp;Afsender id
								<a tabindex="-1" id="dktf_0004tIdLink">
									<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
								</a>
							</td>
							<td class="text12" title="dktf_0010t">&nbsp;Modtager id
								<a tabindex="-1" id="dktf_0010tIdLink">
									<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
								</a>
							</td>
							<td class="text12" title="dktf_0022t">&nbsp;Kodeord</td>
						</tr>
						<tr>
							<td ><input type="text" class="inputTextMediumBlue" name="dktf_0004t" id="dktf_0004t" size="30" maxlength="35" value='${model.record.dktf_0004t}'></td>
							<td ><input type="text" class="inputTextMediumBlue" name="dktf_0010t" id="dktf_0010t" size="30" maxlength="35" value='${model.record.dktf_0010t}'></td>
							<td ><input type="text" class="inputTextMediumBlue" name="dktf_0022t" id="dktf_0022t" size="15" maxlength="14" value='${model.record.dktf_0022t}'></td>
						</tr>
						<tr height="5"><td></td>
						<tr>
							<td class="text14" title=""><b>&nbsp;FTP</b>
								<img style="vertical-align: bottom;" src="resources/images/move.png" border="0" width="22px" height="22px" alt="FTP">
							</td>
						</tr>
						<tr>
							<td class="text12" title="dktf_ftust">&nbsp;Bruger id</td>
							<td class="text12" title="dktf_ftpwt">&nbsp;Kodeord&nbsp;
								<img style="vertical-align: bottom;" src="resources/images/lockOrig.png" border="0" alt="pass">
							</td>
							<td class="text12" title="dktf_ftipt">&nbsp;Adresse</td>
						</tr>
						<tr>
							<td ><input type="text" class="inputTextMediumBlue" name="dktf_ftust" id="dktf_ftust" size="30" maxlength="35" value='${model.record.dktf_ftust}'></td>
							<td ><input type="text" class="inputTextMediumBlue" name="dktf_ftpwt" id="dktf_ftpwt" size="21" maxlength="20" value='${model.record.dktf_ftpwt}'></td>
							<td ><input type="text" class="inputTextMediumBlue" name="dktf_ftipt" id="dktf_ftipt" size="40" maxlength="64" value='${model.record.dktf_ftipt}'></td>
						</tr>
						<tr height="3"><td></td>
						<tr>
							<td class="text12" title="dktf_0031t">&nbsp;Returner bekræftelse</td>
						</tr>
						<tr>
							<td >
								<select name="dktf_0031t" id="dktf_0031t">
	        		    			<option value="">-velg-</option>
								  	<option value="1"<c:if test="${ model.record.dktf_0031t == '1'}"> selected </c:if> >Ja</option>
								  	<option value=""<c:if test="${ model.record.dktf_0031t == ''}"> selected </c:if> >Nei</option>
								</select>
							</td>
						</tr>
						<tr height="8"><td></td>
	 	    		</table> 
	 	    		<%-- SEPARATOR --%>
	 	    		<table width="80%" cellspacing="0" border="0" align="left">
						<tr height="25" ><td class="text12" >&nbsp;</td></tr>
					</table>
					
	 	    		<table class="formFrameHeaderPeachWithBorder" width="80%" cellspacing="0" border="0" align="left">
						<tr><td class="text12" >&nbsp;PRODUKSJON&nbsp;&nbsp;<img style="vertical-align: middle;"  src="resources/images/bulletGreen.png" border="0" width="10px" height="10px" alt="prod"></td></tr>
					</table>
					<table class="formFramePeachGrayRoundBottom" width="80%" cellspacing="1" border="0" align="left">	
						<%--
						------------------------ 
						 PROD
						------------------------ 
						--%>
						<tr height="8"><td></td>
						<tr>
							<td class="text14" title=""><b>&nbsp;UNB</b>&nbsp;
								<img style="vertical-align: top;"  src="resources/images/checkmarkOK.png" border="0" width="12px" height="12px" alt="FTP">
							</td>
						</tr>
						<tr>
							<td class="text12" title="dktf_0004p">&nbsp;Afsender id
								<a tabindex="-1" id="dktf_0004pIdLink">
									<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
								</a>
							</td>
							<td class="text12" title="dktf_0010p">&nbsp;Modtager id
								<a tabindex="-1" id="dktf_0010pIdLink">
									<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
								</a>
							</td>
							<td class="text12" title="dktf_0022p">&nbsp;Kodeord</td>
						</tr>
						<tr>
							<td ><input type="text" class="inputTextMediumBlue" name="dktf_0004p" id="dktf_0004p" size="30" maxlength="35" value='${model.record.dktf_0004p}'></td>
							<td ><input type="text" class="inputTextMediumBlue" name="dktf_0010p" id="dktf_0010p" size="30" maxlength="35" value='${model.record.dktf_0010p}'></td>
							<td ><input type="text" class="inputTextMediumBlue" name="dktf_0022p" id="dktf_0022p" size="15" maxlength="14" value='${model.record.dktf_0022p}'></td>
						</tr>
						<tr height="5"><td></td>
						<tr>
							<td class="text14" title=""><b>&nbsp;FTP</b>
								<img style="vertical-align: bottom;"  src="resources/images/move.png" border="0" width="22px" height="22px" alt="FTP">
							</td>
						</tr>
						<tr>
							<td class="text12" title="dktf_ftusp">&nbsp;Bruger id</td>
							<td class="text12" title="dktf_ftpwp">&nbsp;Kodeord&nbsp;
								<img style="vertical-align: bottom;" src="resources/images/lockOrig.png" border="0" alt="pass">
							</td>
							<td class="text12" title="dktf_ftipp">&nbsp;Adresse</td>
						</tr>
						<tr>
							<td ><input type="text" class="inputTextMediumBlue" name="dktf_ftusp" id="dktf_ftusp" size="30" maxlength="35" value='${model.record.dktf_ftusp}'></td>
							<td ><input type="text" class="inputTextMediumBlue" name="dktf_ftpwp" id="dktf_ftpwp" size="21" maxlength="20" value='${model.record.dktf_ftpwp}'></td>
							<td ><input type="text" class="inputTextMediumBlue" name="dktf_ftipp" id="dktf_ftipp" size="40" maxlength="64" value='${model.record.dktf_ftipp}'></td>
						</tr>
						<tr height="3"><td></td>
						<tr>
							<td class="text12" title="dktf_0031p">&nbsp;Returner bekræftelse</td>
						</tr>
						<tr>
							<td >
								<select name="dktf_0031p" id="dktf_0031p">
	        		    			<option value="">-velg-</option>
								  	<option value="1"<c:if test="${ model.record.dktf_0031p == '1'}"> selected </c:if> >Ja</option>
								  	<option value=""<c:if test="${ model.record.dktf_0031p == ''}"> selected </c:if> >Nei</option>
								</select>
							</td>
						</tr>
						<tr height="8"><td></td>
	 	    		</table>
	 	    		
	 	    		<table width="80%" cellspacing="1" border="0" align="left">
	 	    			<tr height="10"><td>&nbsp;</td>
			    	    <tr>
				    	    <td colspan="2" class="text12" title="dktard01">&nbsp;Bibliotek for LSOUTPUT&nbsp;&nbsp;&nbsp;
				    	    <input type="text" class="inputTextMediumBlue" name="dktf_lsli" id="dktf_lsli" size="11" maxlength="10" value='${model.record.dktf_lsli}'>
				    	    </td>
				    	    <td class="text12" align="right">
								<input class="inputFormSubmit" type="submit" name="submit" id="submit" value='Spare'/>
							</td>
						</tr>
						<tr height="3"><td></td>
					</table>		
	 	    </tr>
	 	    <tr height="20"><td>&nbsp;</td></tr>
	 	   </table>
	 		</form>
		</td>
	</tr>
</table>	

<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->

