<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerSkat.jsp" />
<!-- =====================end header ==========================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/skatglobal_edit.js?ver=${user.versionEspedsg}"></SCRIPT>	
	<SCRIPT type="text/javascript" src="resources/js/skatnctsimport_unloading_edit.js?ver=${user.versionEspedsg}"></SCRIPT>
 
 	<style type = "text/css">
	.ui-datepicker { font-size:9pt;}
	</style>
	
<form name="nctsImportSaveNewTopicForm" id="nctsImportSaveNewTopicForm" method="post">

<table width="100%" cellspacing="0" border="0" cellpadding="0">
	
 <tr>
 <td>	
	<%-- tab container component --%>
	<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
		<tr height="2"><td></td></tr>
		<tr height="25"> 
			<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a tabindex=-1 style="display:block;" href="skatnctsimport.do?action=doFind&sign=${recordTopicSkat.tisg}">
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.skat.ncts.import.list.tab"/></font>
					<img valign="bottom" src="resources/images/list.gif" border="0" alt="general list">
					
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a tabindex=-1 style="display:block;" href="skatnctsimport_edit.do?action=doFetch&avd=${recordTopicSkat.tiavd}&opd=${recordTopicSkat.titdn}
						&sysg=${recordTopicSkat.tisg}&syst=${recordTopicSkat.tist}&sydt=${recordTopicSkat.tidt}">
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.skat.ncts.import.created.mastertopic.tab"/></font>
					<font class="text12MediumBlue">[${recordTopicSkat.titdn}]</font>
					<c:if test="${ recordTopicSkat.tist == 'F' || recordTopicSkat.tist == 'M' || empty recordTopicSkat.tist}">
						<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
					</c:if>
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a tabindex=-1 style="display:block;" href="skatnctsimport_edit_items.do?action=doFetch&avd=${recordTopicSkat.tiavd}&sign=${recordTopicSkat.tisg}
											&opd=${recordTopicSkat.titdn}&mrnNr=${recordTopicSkat.titrnr}&godsNr=${recordTopicSkat.tign}
											&status=${recordTopicSkat.tist}&datum=${recordTopicSkat.tidt}">
					<font class="tabDisabledLink">
						&nbsp;<spring:message code="systema.skat.ncts.import.item.createnew.tab"/>
					</font>
					<c:if test="${ recordTopicSkat.tist == 'F' || recordTopicSkat.tist == 'M' || empty recordTopicSkat.tist}">
						<img valign="bottom" src="resources/images/add.png" width="12" hight="12" border="0" alt="create new">
					</c:if>
					
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="12%" valign="bottom" class="tab" align="center" nowrap>
				<font class="tabLink">
					&nbsp;<spring:message code="systema.skat.ncts.import.unloading.createnew.tab"/>
				</font>
				<img style="vertical-align: bottom" src="resources/images/unloading.png" width="16" hight="16" border="0" alt="show log">
			</td>
			
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a tabindex=-1 style="display:block;" href="skatnctsimport_unloading_edit_items.do?action=doFetch&avd=${recordTopicSkat.tiavd}&sign=${recordTopicSkat.tisg}	
											&opd=${recordTopicSkat.titdn}&mrnNr=${recordTopicSkat.titrnr}&godsNr=${recordTopicSkat.tign}
											&status=${recordTopicSkat.tist}&datum=${recordTopicSkat.tidt}">
					<font class="tabDisabledLink">
						&nbsp;<spring:message code="systema.skat.ncts.import.unloading.item.createnew.tab"/>
					</font>
					<img style="vertical-align: bottom" src="resources/images/add.png" width="12" hight="12" border="0" alt="item lines">
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a tabindex=-1 style="display:block;" href="skatnctsimport_logging.do?avd=${recordTopicSkat.tiavd}&sign=${recordTopicSkat.tisg}
									&opd=${recordTopicSkat.titdn}&mrnNr=${recordTopicSkat.titrnr}&godsNr=${recordTopicSkat.tign}
									&status=${recordTopicSkat.tist}&datum=${recordTopicSkat.tidt}">
					<font class="tabDisabledLink">
						&nbsp;<spring:message code="systema.skat.ncts.import.logging.tab"/>
					</font>
					<img style="vertical-align: bottom" src="resources/images/log-icon.png" width="16" hight="16" border="0" alt="show log">
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a tabindex=-1 style="display:block;" href="skatnctsimport_archive.do?avd=${recordTopicSkat.tiavd}&sign=${recordTopicSkat.tisg}
									&opd=${recordTopicSkat.titdn}&mrnNr=${recordTopicSkat.titrnr}&godsNr=${recordTopicSkat.tign}
									&status=${recordTopicSkat.tist}&datum=${recordTopicSkat.tidt}">
					<font class="tabDisabledLink">
						&nbsp;<spring:message code="systema.skat.ncts.import.archive.tab"/>
					</font>
					<img style="vertical-align: bottom" src="resources/images/archive.png" width="16" hight="16" border="0" alt="show archive">
				</a>
			</td>
			<td width="13%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
		</tr>
	</table>
	</td>
 </tr>
 
 <tr>
 	<td>
	<%-- --------------------------- --%>	
 	<%-- tab area container PRIMARY  --%>
	<%-- --------------------------- --%>
	<table width="100%" class="tabThinBorderWhite" border="0" cellspacing="0" cellpadding="0">
 		<tr height="3"><td colspan="2">&nbsp;</td></tr>
 		<%-- GENERAL HIDDEN --%> 
	    <input type="hidden" name="thmf" id="thmf" value="015">
			
		<%-- --- HIDDEN FORM FIELDS (not visible in form but important with an UPDATE ----- --%>			
		<%-- general (from user profile) --%>
		<input type="hidden" name="action" id="action" value='doUpdate'>
		<input type="hidden" name="applicationUser" id="applicationUser" value='${user.user}'>
		<input type="hidden" name="opd" id="opd" value='${recordTopicSkat.titdn}'>
		<%-- topic specific (syop and tuid) --%>
		<input type="hidden" name="tiavd" id="tiavd" value='${recordTopicSkat.tiavd}'>
		<input type="hidden" name="titdn" id="titdn" value='${recordTopicSkat.titdn}'>
		<input type="hidden" name="tisg" id="tisg" value='${recordTopicSkat.tisg}'>
		<input type="hidden" name="tist" id="tist" value='${recordTopicSkat.tist}'>
		<input type="hidden" name="tidt" id="tidt" value='${recordTopicSkat.tidt}'>
    		<input type="hidden" name="avd" id="avd" value='${recordTopicSkat.tiavd}'>
		<input type="hidden" name="sign" id="sign" value='${recordTopicSkat.tisg}'>
		<tr >
			<td colspan="3" align="left" class="text12MediumBlue">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Afd:&nbsp;<b>${recordTopicSkat.tiavd}</b>&nbsp;&nbsp;<span title="titdn">Angivelse:</span>&nbsp;<b>${recordTopicSkat.titdn}</b>
				&nbsp;&nbsp;&nbsp;<span title="titrnr">MRN-nr:</span>&nbsp;<b>${recordTopicSkat.titrnr}</b>&nbsp;&nbsp;Gods-nr:&nbsp;<b>${recordTopicSkat.tign}</b>
			</td>
		</tr>
		<tr >
			<td align="left" class="text11MediumBlue">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span title="tisg">Sign:</span>&nbsp;<b>${recordTopicSkat.tisg}</b>,&nbsp;&nbsp;<span title="tidt">Dato:</span>&nbsp;<b>${recordTopicSkat.tidt}</b>
				&nbsp;
				<img onMouseOver="showPop('status_info');" onMouseOut="hidePop('status_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
           		<span title="tist">Status:</span>&nbsp;<b>${recordTopicSkat.tist}</b>
				&nbsp;&nbsp;
				<span title="tienkl">Type procedure:</span>&nbsp;
				<c:if test="${recordTopicSkat.tienkl == 'J'}"><b>Forenklet</b></c:if>
				<c:if test="${recordTopicSkat.tienkl == 'N'}"><b>Normal</b></c:if>  
				
			</td>
				<span style="position:absolute; left:800px; top:150px; width:250px; height:520px;" id="status_info" class="popupWithInputText"  >
						<div class="text11" align="left">
		           		<br/>
		           		Kun <b>M</b>, F eller <b>' '</b> kan redigeres.
		           			<ul>
								<c:forEach var="record" items="${model.statusCodeList}" >
				           			<li><b>${record.tkkode}&nbsp;</b>&nbsp;${record.tktxtn}</li>
			           			</c:forEach>
		           			</ul>
						</div>
					</span>	                
		</tr>	

		<tr height="10"><td colspan="2">&nbsp;</td></tr>
		<%-- --------------- --%>
		<%-- CONTENT --%>
		<%-- --------------- --%>
		<tr>
		<td >
		<table align="center" width="98%" border="0" cellspacing="1" cellpadding="0">
		<%-- Other errors (none validation errors) --%>
		<c:if test="${not empty model.errorMessage}">
		<tr>
			<td width="5">&nbsp;</td>
			<td colspan="10">
            	<table align="left" border="0" cellspacing="0" cellpadding="0">
			 		<tr>
			 			<td class="textError">
			 				<ul>
                                   <li>
                                     	Fel vid uppdatering ${model.errorMessage}]  
                                   </li>
                                   <li>
                                     	[META-INFO: ${model.errorInfo}]  
                                   </li>
                                   
                               </ul>
			 			</td>
					</tr>
				</table>
			</td>
		</tr>
		</c:if>
		<tr>
			<td width="50%"class="text12" valign="top">
				<table width="90%" align="left" border="0" cellspacing="1" cellpadding="0">
				 	<tr >
					 	<td >
						<table width="100%" class="formFrameHeader" border="0" cellspacing="1" cellpadding="0">
					 		<tr height="15">
					 			<td class="text12White">
					 				&nbsp;Erhverv - Ansvarlig&nbsp;
				 				</td>
			 				</tr>
			            </table>
			            </td>
		            </tr>
		            <tr >
					 	<td>
						<table width="100%" class="formFrame" border="0" cellspacing="2" cellpadding="1">
					 		<tr >
					 			<td class="text12">&nbsp;<span title="tikn">Kundenr</span></td>
					 			<td class="text12">&nbsp;</td>
			 				</tr>
			 				<tr >
					 			<td class="text12"><input readonly type="text" class="inputTextMediumBlue" name="tikn" id="tikn" size="8" maxlength="8" value="${recordTopicSkat.tikn}"></td>
					 			<td class="text12">&nbsp;</td>
			 				</tr>
			 				<tr >
					 			<td class="text12">&nbsp;<span title="titin">TIN</span></td>
					 			<td class="text12">&nbsp;<span title="tina">Navn</span></td>
			 				</tr>
			 				<tr >
					 			<td class="text12"><input readonly type="text" class="inputTextMediumBlue" name="titin" id="titin" size="17" maxlength="17" value="${recordTopicSkat.titin}"></td>
					 			<td class="text12"><input readonly type="text" class="inputTextMediumBlue" name="tina" id="tina" size="30" maxlength="30" value="${recordTopicSkat.tina}"></td>
			 				</tr>
			 				<tr >
					 			<td class="text12">&nbsp;<span title="tiad1">Adresse</span></td>
					 			<td class="text12">&nbsp;<span title="tisk">Sprogkode</span></td>
			 				</tr>
			 				<tr >
					 			<td class="text12"><input readonly type="text" class="inputTextMediumBlue" name="tiad1" id="tiad1" size="30" maxlength="30" value="${recordTopicSkat.tiad1}"></td>
					 			<td ><input readonly type="text" class="inputTextMediumBlue" name="tisk" id="tisk" size="2" maxlength="2" value="${recordTopicSkat.tisk}"></td>
			 				</tr>
			 				<tr >
					 			<td class="text12">&nbsp;<span title="tips">By</span></td>
					 			<td class="text12">&nbsp;<span title="tipn">Postnr</span></td>
			 				</tr>
			 				<tr >
					 			<td class="text12"><input readonly type="text" class="inputTextMediumBlue" name="tips" id="tips" size="24" maxlength="24" value="${recordTopicSkat.tips}"></td>
					 			<td class="text12"><input readonly type="text" class="inputTextMediumBlue" name="tipn" id="tipn" size="9" maxlength="9" value="${recordTopicSkat.tipn}"></td>
			 				</tr>
			 				<tr >
					 			<td class="text12">&nbsp;<span title="tilk">Landkode</span></td>
					 			<td class="text12">&nbsp</td>
			 				</tr>
			 				<tr >
			 					<td ><input readonly type="text" class="inputTextMediumBlue" name="tilk" id="tilk" size="2" maxlength="2" value="${recordTopicSkat.tilk}"></td>
					 			<td class="text12">
					 				&nbsp;
					 			</td>
			 				</tr>
			            </table>
			            </td>
		            </tr>
	            
	            </table>
            </td>
            
            	<td width="50%"class="text12" valign="top">
				<table width="100%" align="center" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td valign="top">
						<table border="0" cellspacing="1" cellpadding="0">
						<tr>
				            <td >&nbsp;</td>
				            <td ><span title="tign">Godsnr</span></td>
				            <td colspan="3" ><input readonly type="text" class="inputText" name="tign" id="tign" size="36" maxlength="35" value="${recordTopicSkat.tign}"></td>
			            </tr>
			            <tr>
				            <td >&nbsp;</td>
				            <td ><span title="tignsk">Sprogkode</span>&nbsp;</td>
				            <td ><input readonly type="text" class="inputText" name="tignsk" id="tignsk" size="2" maxlength="2" value="${recordTopicSkat.tignsk}"></td>
			            </tr>
			            <tr>
				            <td >&nbsp;</td>
				            <td ><span title="titrnr">MRN-nr</span></td>
				            <td colspan="3"><input readonly type="text" class="inputText" name="titrnr" id="titrnr" size="36" maxlength="35" value="${recordTopicSkat.titrnr}"></td>
			            </tr>
			            <tr>
				            <td >&nbsp;</td>
				            <td ><span title="tialk">Afs.land</span>&nbsp;</td>
				            <td ><input readonly type="text" class="inputText" name="tialk" id="tialk" size="2" maxlength="2" value="${recordTopicSkat.tialk}"></td>
				            <td >&nbsp;</td>
			            </tr>
			            <tr height="10"><td>&nbsp;</td></tr>
			            <tr>
				            <td >&nbsp;</td>
				            <td ><span title="tialsk">Aft.lag.sted (kode)</span></td>
				            <td ><input readonly type="text" class="inputTextReadOnly" name="tialsk" id="tialsk" size="17" maxlength="17" value="${recordTopicSkat.tialsk}"></td>
				            <td ><span title="tialss">Sprogkode</span>&nbsp;</td>
				            <td ><input readonly type="text" class="inputTextReadOnly" name="tialss" id="tialss" size="2" maxlength="2" value="${recordTopicSkat.tialss}"></td>
			            </tr>
			            <tr>
				            <td >&nbsp;</td>
				            <td ><span title="tials">Aft.lag.sted</span></td>
				            <td ><input readonly type="text" class="inputTextReadOnly" name="tials" id="tials" size="20" maxlength="35" value="${recordTopicSkat.tials}"></td>
			            </tr>
			            <tr height="2"><td>&nbsp;</td></tr>
			            <tr>
				            <td >&nbsp;</td>
				            <td ><span title="tiglsk">Godk.lag.sted (kode)</span></td>
				            <td ><input readonly type="text" class="inputTextReadOnly" name="tiglsk" id="tiglsk" size="17" maxlength="17" value="${recordTopicSkat.tiglsk}"></td>
			            </tr>
			            <tr>
				            <td >&nbsp;</td>
				            <td ><span title="tiacts">Kontrol sted (kode)</span></td>
				            <td ><input readonly type="text" class="inputTextReadOnly" name="tiacts" id="tiacts" size="17" maxlength="17" value="${recordTopicSkat.tiacts}"></td>
			            </tr>
			            <tr height="2"><td>&nbsp;</td></tr>
			            <tr>
				            <td >&nbsp;</td>
				            <td ><span title="titsb">Best.toldsted</span></td>
				            <td ><input readonly type="text" class="inputTextReadOnly" name="titsb" id="titsb" size="10" maxlength="8" value="${recordTopicSkat.titsb}"></td>
				            <td ><span title="tiskb">Sprogkode&nbsp;</span></td>
				            <td ><input readonly type="text" class="inputTextReadOnly" name="tiskb" id="tiskb" size="2" maxlength="2" value="${recordTopicSkat.tiskb}"></td>
				            
			            </tr>
			            <tr>
				            <td >&nbsp;</td>
				            <td ><span title="tidtf">Frigivelsesdato</span></td>
				            <td ><input readonly type="text" class="inputTextReadOnly" name="tidtf" id="tidtf" size="10" maxlength="8" value="${recordTopicSkat.tidtf}"></td>
			            </tr>
			            
			            <tr height="2"><td>&nbsp;</td></tr>
			            </table>
				        </td>
			        </tr>
	            </table>
            </td>
		</tr>

		<tr height="15"><td colspan="2">&nbsp;</td></tr>
		
		
		<%-- Validation errors --%>
		<spring:hasBindErrors name="record"> <%-- name must equal the command object name in the Controller --%>
		<tr>
			<td colspan="10">
            	<table align="left" border="0" cellspacing="0" cellpadding="0">
            	<tr>
				<td class="textError">					
		            <ul>
		            <c:forEach var="error" items="${errors.allErrors}">
		                <li >
		                	<spring:message code="${error.code}" text="${error.defaultMessage}"/>
		                </li>
		            </c:forEach>
		            </ul>
				</td>
			</tr>
			</table>
			</td>
		</tr>
		</spring:hasBindErrors>
		
		<%-- ------------------------------------------ --%>
		<%-- SUB-SECTION that allows editable fields    --%>
		<%-- ------------------------------------------ --%>
	 	<tr >
		 	<td colspan="2">
			<table width="100%" class="formFrameHeader" border="0" cellspacing="1" cellpadding="0">
		 		<tr height="15">
		 			<td class="text12White">
		 				&nbsp;Losning bemærkninger&nbsp;<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
	 				</td>
 				</tr>
            </table>
            </td>
        </tr>
	 	<tr >
		 	<td width="50%">
				<table width="100%" class="formFrame" border="0" cellspacing="1" cellpadding="0">
			 		<tr height="15">
			 			<td class="text12">
			 				&nbsp;<span title="tivpos">Samlet antal varelinjer [NCTS]</span>
		 				</td>
			 			<td class="text12">
			 				&nbsp;<input readonly style="text-align: right" type="text" class="inputTextReadOnly" name="tivpos" id="tivpos" size="5" maxlength="5" value="${model.record.tivpos}">
		 				</td>
	 				</tr>
			 		<tr height="15">
			 			<td class="text12">
			 				&nbsp;<span title="tintk">Samlet kollital [NCTS]</span>
		 				</td>
			 			<td class="text12">
			 				&nbsp;<input readonly style="text-align: right" type="text" class="inputTextReadOnly" name="tintk" id="tintk" size="5" maxlength="4" value="${model.record.tintk}">
		 				</td>
	 				</tr>
			 		<tr height="15">
			 			<td class="text12">
			 				&nbsp;<span title="tivkb">Samlet bruttovægt [NCTS]</span>
		 				</td>
			 			<td class="text12">
			 				&nbsp;<input readonly style="text-align: right" type="text" class="inputTextReadOnly" name="tivkb" id="tivkb" size="11" maxlength="11" value="${model.record.tivkb}">
		 				</td>
	 				</tr>
	 				<tr height="5"><td></td></tr>
	            </table>
            </td>
		 	<td width="50%">
				<table width="100%" class="formFrame" border="0" cellspacing="1" cellpadding="0">
			 		<tr height="15">
			 			<td class="text12">
			 				&nbsp;<span title="nivpos">Samlet antal varelinjer [CTL]</span>
		 				</td>
			 			<td class="text12">
			 				&nbsp;<input readonly style="text-align: right" type="text" class="inputTextReadOnly" name="nivpos" id="nivpos" size="5" maxlength="5" value="${model.record.nivpos}">
		 				</td>
	 				</tr>
			 		<tr height="15">
			 			<td class="text12">
			 				&nbsp;<span title="nintk">Samlet kollital [CTL]</span>
		 				</td>
			 			<td class="text12">
			 				&nbsp;<input readonly style="text-align: right" type="text" class="inputTextReadOnly" name="nintk" id="nintk" size="5" maxlength="4" value="${model.record.nintk}">
		 				</td>
	 				</tr>
			 		<tr height="15">
			 			<td class="text12">
			 				&nbsp;<span title="nivkb">Samlet bruttovægt [CTL]</span>
		 				</td>
			 			<td class="text12">
			 				&nbsp;<input readonly style="text-align: right" type="text" class="inputTextReadOnly" name="nivkb" id="nivkb" size="11" maxlength="11" value="${model.record.nivkb}">
		 				</td>
	 				</tr>
	 				<tr height="5"><td></td></tr>
	            </table>
            </td>
        </tr>
        
        
		<tr>			
			<td colspan="2">
			<table class="formFrame" width="100%" align="left" border="0" cellspacing="1" cellpadding="0">
 				<tr height="5"><td></td></tr>
				<tr>
				<td width="50%" class="text12" valign="bottom">
					<table width="100%" align="left" border="0" cellspacing="1" cellpadding="0">
					 	<tr >
						 	<td>
							<table width="100%" border="0" cellspacing="1" cellpadding="0">
				 				<tr>		
									<td class="text12">&nbsp;
									<img onMouseOver="showPop('konform_info');" onMouseOut="hidePop('konform_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
									<font class="text12Red" >*</font><span title="nikonf">Konform</span>
						 				<span style="position:absolute; left:1020px; top:500px; width:250px; height:150px;" id="konform_info" class="popupWithInputText"  >
							           		<div class="text11" align="left">
							           			<br/>
							           			<b>Konform</b>
												 <ul>
												 	<li><b>0</b> = Losses med bemærkninger.</li>
												 	<li><b>1</b> = Losses uden bemærkninger.</li>
												 </ul>
						           			</div>
										</span>
									</td>			 			
						 			<td class="text12">
										<select name="nikonf" id="nikonf">
						            			<option value="0"<c:if test="${model.record.nikonf == '0' || empty model.record.nikonf}}"> selected </c:if> >0</option>
					 					  	<option value="1"<c:if test="${model.record.nikonf == '1'}"> selected </c:if> >1</option>
										</select>
									</td>
									<td class="text12">&nbsp;
									<img onMouseOver="showPop('afsluttet_info');" onMouseOut="hidePop('afsluttet_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
									<font class="text12Red" >*</font><span title="nifulf">Afsluttet</span>
										<span style="position:absolute; left:1020px; top:500px; width:250px; height:150px;" id="afsluttet_info" class="popupWithInputText"  >
							           		<div class="text11" align="left">
							           			<br/>
							           			<b>Afsluttet</b>
												 <ul>
												 	<li><b>Nej</b> = Losning er ikke afsluttet.</li>
												 	<li><b>Ja</b> = Losning er afsluttet.</li>
												 </ul>
						           			</div>
										</span>
									</td>
						 			<td class="text12">
										<select name="nifulf" id="nifulf">
						            			<option value=""<c:if test="${model.record.nifulf == '0' || empty model.record.nifulf}"> selected </c:if> >Nej</option>
						            			<option value="1"<c:if test="${model.record.nifulf == '1'}"> selected </c:if> >Ja</option>
										</select>
						 			</td>
						 			<td class="text12">&nbsp;<font class="text12Red" >*</font><span title="nidtl">Losning dato</span></td>
						 			<td class="text12">
						 				<input type="text" class="inputTextMediumBlue" name="nidtl" id="nidtl" size="9" maxlength="8" value="${model.record.nidtl}">
					 				</td>
						 		</tr>
						 	</table>
						 	</td>	
						 </tr>
						 <tr height="5"><td></td></tr>
						 <tr>
						 	<td>
						 	<table class="tableBorderWithRoundCornersGray" width="100%" border="0" cellspacing="1" cellpadding="0">		
				 				<tr >
						 			<td colspan="2" class="text12">&nbsp;<b>CTL resultat(OT/DI)</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<span title="nictsk">Sprogkode</span>&nbsp;
							            <select name="nictsk" id="nictsk">
							            		<option value="">-vælg-</option>
							 				  	<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
						                             <option value="${code.tkkode}"<c:if test="${model.record.nictsk == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
												</c:forEach> 
										</select>
										<a tabindex="-1" class="text12" target="_blank" href="${model.isoLanguageCodesURL.value}" onclick="${model.isoLanguageCodesURL.windowOpenDimensions}" >
						            			<img style="cursor:pointer;" src="resources/images/find.png" border="0" alt="search" >
						            		</a>
					            		</td>					 			
						 		</tr>
						 		<tr>	
						 			<td class="text12" >&nbsp;&nbsp;
						 			<img onMouseOver="showPop('control_OT_code_info');" onMouseOut="hidePop('control_OT_code_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
						 			<span title="nictb">OT:</span>&nbsp;
						 				<span style="position:absolute; left:1020px; top:500px; width:250px; height:150px;" id="control_OT_code_info" class="popupWithInputText"  >
							           		<div class="text11" align="left">
							           			<br/>
							           			<b>CTL resultat</b>
												<br/><br/>
												 (Hvis losningsresultat = 0)
												 <ul>
												 	<li><b>DI</b> = Afvigelse i værdi.</li>
												 	<li><b>OT</b> = Andre ting at rapportere</li>
												 </ul>
						           			</div>
										</span>
						 			
						 			</td>
						 			<td class="text12">
										<input type="text" class="inputTextMediumBlue" name="nictb" id="nictb" size="40" maxlength="70" value="${model.record.nictb}">
									</td>
								</tr>
								<tr>	
						 			<td class="text12" >&nbsp;&nbsp;
						 			<img onMouseOver="showPop('control_DI_code_info');" onMouseOut="hidePop('control_DI_code_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
						 			<span title="nictb2">DI:</span>
						 				<span style="position:absolute; left:1020px; top:500px; width:250px; height:150px;" id="control_DI_code_info" class="popupWithInputText"  >
							           		<div class="text11" align="left">
							           			<br/>
							           			<b>CTL resultat</b>
												<br/><br/>
												 (Hvis losningsresultat = 0)
												 <ul>
												 	<li><b>DI</b> = Afvigelse i værdi.</li>
												 	<li><b>OT</b> = Andre ting at rapportere</li>
												 </ul>
						           			</div>
										</span>						 			
						 			</td>
						 			<td class="text12">
										<input type="text" class="inputTextMediumBlue" name="nictb2" id="nictb2" size="40" maxlength="70" value="${model.record.nictb2}">
									</td>
								</tr>
								<tr>	
						 			<td class="text12" >&nbsp;&nbsp;
						 			<img onMouseOver="showPop('pointer_info');" onMouseOut="hidePop('pointer_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
						 			<span title="nictp">Peker:</span>
						 				<span style="position:absolute; left:1020px; top:500px; width:250px; height:150px;" id="pointer_info" class="popupWithInputText"  >
							           		<div class="text11" align="left">
							           			<br/>
							           			<b>Peker</b>
												<p>
													Indtast rubrikknr ledsagedokumentet med fejl. Hvis der er afvigelser i den sidste post, pakker eller vægt, automatisk sendes point og kode.
												</p>
						           			</div>
										</span>						 			
						 			</td>
						 			<td class="text12">
										<input type="text" class="inputTextMediumBlue" name="nictp" id="nictp" size="30" maxlength="35" value="${model.record.nictp}">
									</td>
								</tr>
								<tr>	
						 			<td class="text12" >&nbsp;&nbsp;
						 			<img onMouseOver="showPop('correctedValue_info');" onMouseOut="hidePop('correctedValue_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
						 			<span title="nictnv">Korrigeret værdi:</span>
						 				<span style="position:absolute; left:1020px; top:500px; width:250px; height:150px;" id="correctedValue_info" class="popupWithInputText"  >
							           		<div class="text11" align="left">
							           			<br/>
							           			<b>Korrigeret værdi</b>
												<p>
													Indtast afvigende værdien i feltet er angivet i <b>peker</b>.
												</p>
						           			</div>
										</span>						 			
						 			</td>
						 			<td class="text12">
										<input type="text" class="inputTextMediumBlue" name="nictnv" id="nictnv" size="20" maxlength="15" value="${model.record.nictnv}">
									</td>
								</tr>
								<%-- finns på grön skärm men används inte längre. Vi gömmer dessa här tills vidare besked
								<tr>	
									<td class="text12" >&nbsp;&nbsp;&nbsp;<span title="nictp">Pekare:</span>&nbsp;</td>
						 			<td class="text12">
										<input type="text" class="inputTextMediumBlue" name="nictp" id="nictp" size="6" maxlength="5" value="${model.record.nictp}">
										&nbsp;<span title="nictnv">Nytt värde:</span>&nbsp;
										<input type="text" class="inputTextMediumBlue" name="nictnv" id="nictnv" size="16" maxlength="15" value="${model.record.nictnv}">
									</td>
						 		</tr>
						 		 --%>
						 		<tr height="5"><td></td></tr>
				            </table>
				            </td>
			            </tr>
					 	<tr height="5"><td></td></tr>
						<tr>
						 	<td>
						 	<table class="tableBorderWithRoundCornersGray" width="100%" border="0" cellspacing="1" cellpadding="0">		
				 				<tr >
						 			<td colspan="2" class="text12">&nbsp;<span title""><b>Forsegling</b></span></td>					 			
						 		</tr>
						 		<tr>	
						 			<td class="text12" >&nbsp;&nbsp;&nbsp;<span title="nidfst">Status:</span>&nbsp;</td>
						 			<td class="text12">
					           			<select class="text11" name="nidfst" id="nidfst">
					           				<option value="1"<c:if test="${model.record.nidfst == '1'}"> selected </c:if> >Ok</option>
										  	<option value=""<c:if test="${model.record.nidfst == '0' || empty model.record.nidfst }"> selected </c:if> >Ikke Ok</option>
										</select>
									</td>
								</tr>
								<tr>	
						 			<td class="text12" >&nbsp;&nbsp;&nbsp;<span title="nidant">Antal:</span>&nbsp;</td>
						 			<td class="text12">
										<input onKeyPress="return numberKey(event)" style="text-align: right" type="text" class="inputTextMediumBlue" name="nidant" id="nidant" size="4" maxlength="4" value="${model.record.nidant}">
									</td>
								</tr>
								<tr>	
									<td class="text12" >&nbsp;&nbsp;&nbsp;<span title="nidfkd">Id:</span>&nbsp;</td>
						 			<td class="text12">
										<input type="text" class="inputTextMediumBlue" name="nidfkd" id="nidfkd" size="20" maxlength="20" value="${model.record.nidfkd}">
									</td>
						 		</tr>
						 		<tr>
						 			<td class="text12" >
						 				<span title="nidfsk">&nbsp;&nbsp;&nbsp;Sprogkode</span>&nbsp;
						 			</td>
						 			<td 	class="text12" >
							            <select name="nidfsk" id="nidfsk">
							            		<option value="">-vælg-</option>
							 				  	<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
						                             <option value="${code.tkkode}"<c:if test="${model.record.nidfsk == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
												</c:forEach> 
										</select>
										<a tabindex="-1" class="text12" target="_blank" href="${model.isoLanguageCodesURL.value}" onclick="${model.isoLanguageCodesURL.windowOpenDimensions}" >
						            			<img style="cursor:pointer;" src="resources/images/find.png" border="0" alt="search" >
						            		</a>
						 			</td>
						 		</tr>
						 		<tr height="5"><td></td></tr>
				            </table>
				            </td>
		 				    
			            </tr>
			            
		            </table>
	            	</td>
				<td width="50%" class="text12" valign="Top">
					<table width="100%" align="left" border="0" cellspacing="1" cellpadding="0">
						 <tr height="35"><td>&nbsp;</td></tr>
						 <tr>
						 	<td >
						 	<table class="tableBorderWithRoundCornersGray" width="85%" border="0" cellspacing="1" cellpadding="0">
								
								<tr>	
						 			<td class="text12">&nbsp;&nbsp;&nbsp;&nbsp;<b><span title="nimn1/nimn2">Bemærkninger</span></b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 			<span title="nimnsk">Sprogkode</span>&nbsp;
							            <select name="nimnsk" id="nimnsk">
							            		<option value="">-vælg-</option>
							 				  	<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
						                             <option value="${code.tkkode}"<c:if test="${model.record.nimnsk == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
												</c:forEach> 
										</select>
										<a tabindex="-1" class="text12" target="_blank" href="${model.isoLanguageCodesURL.value}" onclick="${model.isoLanguageCodesURL.windowOpenDimensions}" >
						            			<img style="cursor:pointer;" src="resources/images/find.png" border="0" alt="search" >
						            		</a>
					            		</td>		
								</tr>
								<tr>	
						 			<td class="text12"><span title="nimn1">&nbsp;&nbsp;</span>
										<input type="text" class="inputTextMediumBlue" name="nimn1" id="nimn1" size="70" maxlength="70" value="${model.record.nimn1}">
									</td>
								</tr>
								<tr>	
						 			<td class="text12"><span title="nimn2">&nbsp;&nbsp;</span>
										<input type="text" class="inputTextMediumBlue" name="nimn2" id="nimn2" size="70" maxlength="70" value="${model.record.nimn2}">
									</td>
						 		</tr>
						 		<tr height="5"><td></td></tr>
				            </table>
				            </td>
			            </tr>
			            <tr height="5"><td></td></tr>
			            <tr>
			            		<td align="center" >
						 	<table>
						 		<tr>
					            		<%-- only status = U,H are allowed  --%>
				 				    <c:choose>
					 				    <c:when test="${ recordTopicSkat.tist == 'U' || recordTopicSkat.tist == 'H' }">
						 				    <td class="text9BlueGreen" valign="bottom"  >
							 				    &nbsp;<input tabindex=-1 class="inputFormSubmit" type="submit" name="submit" onclick="javascript: form.action='skatnctsimport_unloading_edit.do';" value="<spring:message code="systema.skat.ncts.import.unloading.createnew.submit"/>"/>
							 				
							 					<%-- NOTE: we use the same routine as for the Topic ... --%>
						 				    		<input tabindex=-1 class="inputFormSubmit" type="submit" name="send" onclick="javascript: form.action='skatnctsimport_unloading_send.do';" value='<spring:message code="systema.skat.ncts.import.unloading.createnew.send"/>'/>
						 				    	</td>	
					 				    </c:when>
					 				    <c:otherwise>
						 				    <td  align="center" class="text9BlueGreen" valign="bottom"  >
						 				    		&nbsp;&nbsp;&nbsp;<input disabled class="inputFormSubmitGrayDisabled" type="submit" name="submit" value="<spring:message code="systema.skat.submit.not.editable"/>"/>
						 				    	</td>	
					 				    </c:otherwise>	
				 				    </c:choose>
		 				    		</tr>
		 				    </table>
				        </tr>    
		            </table>
	            </td>
	          </tr>
          	</table>
          	</td>
		</tr>
		
		<tr height="20"><td colspan="2">&nbsp;</td></tr>
		
	</table> 
	 
	</td>
 </tr>
</table>
</td>
</tr>
</table>
</form>	