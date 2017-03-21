<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerSkat.jsp" />
<!-- =====================end header ==========================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/skatglobal_edit.js?ver=${user.versionEspedsg}"></SCRIPT>	
	<SCRIPT type="text/javascript" src="resources/js/skatnctsexport_edit.js?ver=${user.versionEspedsg}"></SCRIPT>
 
 	<style type = "text/css">
	.ui-datepicker { font-size:9pt;}
	</style>
	
	

<table width="100%" cellspacing="0" border="0" cellpadding="0">
	
 <tr>
 <td>	
	<%-- tab container component --%>
	<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
		<tr height="2"><td></td></tr>
		<tr height="25"> 
			<td width="20%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a id="alinkTopicList" tabindex=-1 style="display:block;" 
					<c:choose>
						<c:when test="${empty model.record.thsg}">href="skatnctsexport.do?action=doFind&sign=${user.skatSign}"</c:when>
						<c:otherwise>href="skatnctsexport.do?action=doFind&sign=${model.record.thsg}""</c:otherwise>
					</c:choose> >
					
					<img valign="bottom" src="resources/images/list.gif" border="0" alt="general list">
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.skat.ncts.export.list.tab"/></font>
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<c:choose> 
			    <c:when test="${editActionOnTopic=='doUpdate' or editActionOnTopic=='doFetch'}">
					<td width="15%" valign="bottom" class="tab" align="center" nowrap>
						<font class="tabLink">
							&nbsp;<spring:message code="systema.skat.ncts.export.created.mastertopic.tab"/>
						</font>
						<font class="text12MediumBlue">[${model.record.thtdn}]</font>
						<c:if test="${ model.record.thst == 'G' ||  model.status=='F' || model.record.thst == 'M' || empty model.record.thst}">
							<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
						</c:if>
						
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a id="alinkItemLines" tabindex=-1 style="display:block;" href="skatnctsexport_edit_items.do?action=doFetch&avd=${model.record.thavd}&sign=${model.record.thsg}
													&opd=${model.record.thtdn}&tullId=${model.record.thtuid}&mrnNr=${model.record.thtrnr}
													&status=${model.record.thst}&datum=${model.record.thdt}">
							<font class="tabDisabledLink">
								&nbsp;<spring:message code="systema.skat.ncts.export.item.createnew.tab"/>
							</font>
							<c:if test="${ model.record.thst == 'G' ||  model.status=='F' || model.record.thst == 'M' || empty model.record.thst}">
								<img valign="bottom" src="resources/images/add.png" width="12" hight="12" border="0" alt="create new">
							</c:if>
							
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a id="alinkLogging" tabindex=-1 style="display:block;" href="skatnctsexport_logging.do?avd=${model.record.thavd}&sign=${model.record.thsg}
													&opd=${model.record.thtdn}&tullId=${model.record.thtuid}&mrnNr=${model.record.thtrnr}
													&status=${model.record.thst}&datum=${model.record.thdt}">
							<font class="tabDisabledLink">
								&nbsp;<spring:message code="systema.skat.ncts.export.logging.tab"/>
							</font>
							<img style="vertical-align: bottom" src="resources/images/log-icon.png" width="16" hight="16" border="0" alt="show log">
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a id="alinkArchive" tabindex=-1 style="display:block;" href="skatnctsexport_archive.do?avd=${model.record.thavd}&sign=${model.record.thsg}
													&opd=${model.record.thtdn}&tullId=${model.record.thtuid}&mrnNr=${model.record.thtrnr}
													&status=${model.record.thst}&datum=${model.record.thdt}">
							<font class="tabDisabledLink">
								&nbsp;<spring:message code="systema.skat.ncts.export.archive.tab"/>
							</font>
							<img style="vertical-align: bottom" src="resources/images/archive.png" width="16" hight="16" border="0" alt="show archive">
						</a>
					</td>
					<td width="20%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
				</c:when>
				<c:otherwise>
					<td width="20%" valign="bottom" class="tab" align="center" nowrap>
						<font class="tabLink">&nbsp;<spring:message code="systema.skat.ncts.export.createnew.tab"/></font>
						<img valign="bottom" src="resources/images/add.png" width="12" hight="12" border="0" alt="create new">
						
					</td>
					<td width="60%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
				</c:otherwise>
			</c:choose>
		</tr>
	</table>
	</td>
 </tr>
 
 <tr>
 	<td>
	<%-- --------------------------- --%>	
 	<%-- tab area container PRIMARY  --%>
	<%-- --------------------------- --%>
	<form name="nctsExportSaveNewTopicForm" id="nctsExportSaveNewTopicForm" method="post">
	<table width="100%" class="tabThinBorderWhite" border="0" cellspacing="0" cellpadding="0">
 		<tr height="3">
 			<td colspan="2">&nbsp;
				<%-- test indicator /per avdelning --%> 
				<c:forEach var="record" items="${avdListSessionTestFlag}" >
					<c:if test="${record.avd == model.record.thavd}">	
						<c:if test="${record.tst == '1'}">
							<c:set var="isTestAvd" value="1" scope="request" />
						</c:if>
					</c:if>
				</c:forEach>
 			</td>
		</tr>
 		<%-- GENERAL HIDDEN --%> 
	    <input type="hidden" name="thmf" id="thmf" value="015">
			
		<c:choose>
		<%-- UPDATE MODE --%> 
	    <c:when test="${editActionOnTopic=='doUpdate' or editActionOnTopic=='doFetch'}">
	    	<%-- --- HIDDEN FORM FIELDS (not visible in form but important with an UPDATE ----- --%>			
			<%-- general (from user profile) --%>
			<input type="hidden" name="action" id="action" value='doUpdate'>
			<input type="hidden" name="applicationUser" id="applicationUser" value='${user.user}'>
			<input type="hidden" name="opd" id="opd" value='${model.record.thtdn}'>
			<%-- topic specific (syop and tuid) --%>
			<input type="hidden" name="thavd" id="thavd" value='${model.record.thavd}'>
			<input type="hidden" name="thtdn" id="thtdn" value='${model.record.thtdn}'>
			<input type="hidden" name="thsg" id="thsg" value='${model.record.thsg}'>
			<input type="hidden" name="thst" id="thst" value='${model.record.thst}'>
			<input type="hidden" name="thdt" id="thdt" value='${model.record.thdt}'>
			<input type="hidden" name="thtuid" id="thtuid" value='${model.record.thtuid}'>
			<input type="hidden" name="lrnNr" id="lrnNr" value='${model.record.thtuid}'>
	    		<input type="hidden" name="avd" id="avd" value='${model.record.thavd}'>
			<input type="hidden" name="sign" id="sign" value='${model.record.thsg}'>
			
			<tr >
				<td align="left" class="text12MediumBlue">
					&nbsp;&nbsp;&nbsp;&nbsp;Afdeling:&nbsp;${model.record.thavd}&nbsp;&nbsp;<span title="thtdn">Angivelse:</span>&nbsp;<b>${model.record.thtdn}</b>
					
				</td>
				<td>
					<table width="100%">
						<tr>
						<td class="text12MediumBlue" align="right">
							<table>
							<tr>
								<td class="text12MediumBlue" valign="bottom">
									<c:if test="${'1' != isTestAvd}">
										<%--This checkbox appears only in real production. Otherwise use the Testavdelning --%>
										<input type="checkbox" name="dkxh_0035" id="dkxh_0035" value="1" <c:if test="${model.record.dkxh_0035 == '1'}"> checked </c:if>  ><b>TEST flag</b></font>&nbsp;&nbsp;&nbsp;
									</c:if>
								</td>
								<td class="text12MediumBlue">
									<a href="skatncts_export_edit_printTopic.do?avd=${model.record.thavd}&opd=${model.record.thtdn}">
									 	<img style="cursor:pointer;" src="resources/images/printer.png" width="30" hight="30" border="0" alt="Print">
										&nbsp;&nbsp;&nbsp;
									</a>
								</td>
							</tr>
							</table>
						</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr >
				<td colspan="2" class="text12MediumBlue" align="left">
					&nbsp;&nbsp;&nbsp;&nbsp;<span title="thtuid">LRN-nr:</span>&nbsp;<b>${model.record.thtuid}</b>
					&nbsp;&nbsp;<span title="thtrnr">MRN-nr:</span>&nbsp;<b>${model.record.thtrnr}</b>
				</td>
			</tr>
			<tr height="3"><td></td></tr>
			<tr >
				<td colspan="2" align="left" class="text12MediumBlue">
					&nbsp;&nbsp;&nbsp;&nbsp;<span title="thsg">Sign:</span>&nbsp;<b>${model.record.thsg}</b>,&nbsp;&nbsp;<span title="thdt">Dato:</span>&nbsp;<b>${model.record.thdt}</b>,
					<img onMouseOver="showPop('status_info');" onMouseOut="hidePop('status_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					<span title="thst">Stat<a id="updateStatusLink" runat="server" href="#"><font class="text11MediumBlue">u</font></a>s:</span>&nbsp;<b>${model.record.thst}</b>
					&nbsp;&nbsp;
					<font class="text16RedBold" >*</font><span title="thenkl">Type af procedure</span>&nbsp;
					<%-- Must be model attribute in order to validate towards the filter (thsg) --%>
           			<select class="inputTextMediumBlueMandatoryField" class="text11" name="thenkl" id="thenkl">
	            		<option value="J"<c:if test="${model.record.thenkl == 'J'}"> selected </c:if> >Forenklet</option>
					  	<option value="N"<c:if test="${model.record.thenkl == 'N'}"> selected </c:if> >Normal</option>
					</select>
					<div class="text11" style="position: relative;" align="left">
					<span style="position:absolute;top:2px; width:250px;" id="status_info" class="popupWithInputText text11"  >		           	
		           		Kun status <b>M</b>, <b>F</b>, <b>G</b> eller <b>' '</b> kan redigeres.
		           			<ul>
								<c:forEach var="record" items="${model.statusCodeList}" >
				           			<li><b>${record.tkkode}&nbsp;</b>&nbsp;${record.tktxtn}</li>
			           			</c:forEach>
		           			</ul>
					</span>
					</div>
				</td>
				
			</tr>
			<c:if test="${'1' == isTestAvd}">
				<tr>
					<td colspan="2" align="left" class="text14Red">
						&nbsp;&nbsp;&nbsp;<b>[ TEST Afdeling ]</b>
						<input type="hidden" name="testAvdFlag" id="testAvdFlag" value='${isTestAvd}'>
					</td>
				</tr>	
			</c:if> 
		</c:when>
		<%-- CREATE MODE --%> 
		<c:otherwise>
			<%-- --- HIDDEN FORM FIELDS (not visible in form but important with an UPDATE ----- --%>			
			<%-- general (from user profile) --%>
			<input type="hidden" name="action" id="action" value='doUpdate'>
			<input type="hidden" name="applicationUser" id="applicationUser" value='${user.user}'>
			<input type="hidden" name="opd" id="opd" value='${model.record.thtdn}'>
			<%-- topic specific (syop and tuid) --%>
			<input type="hidden" name="thtdn" id="thtdn" value='${model.record.thtdn}'>
			<input type="hidden" name="thst" id="thst" value='${model.record.thst}'>
			<input type="hidden" name="thdt" id="thdt" value='${model.record.thdt}'>
			<input type="hidden" name="thtuid" id="thtuid" value='${model.record.thtuid}'>
			<input type="hidden" name="lrnNr" id="lrnNr" value='${model.record.thtuid}'>
			
			<tr >
				<td align="left" class="text12MediumBlue">
					&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;<font class="text16RedBold" >*</font>Afdelning:&nbsp;
					<%-- Must be model attribute in order to validate towards the filter (thavd) --%>
           			<select name="avd" id="avd">
	            		<option value="">-vælg-</option>
	 				  	<c:forEach var="record" items="${model.avdList}" >
                            <option value="${record.avd}"<c:if test="${model.record.thavd == record.avd}"> selected </c:if> >${record.avd}<c:if test="${record.tst == '1'}">&nbsp;(test)</c:if></option>
						</c:forEach> 
					</select>
					&nbsp;
					<font class="text16RedBold" >*</font><span title="thsg">Sign:</span>&nbsp;
					<%-- Must be model attribute in order to validate towards the filter (thsg) --%>
           			<select name="sign" id="sign">
	            		<option value="">-vælg-</option>
	 				  	<c:forEach var="record" items="${model.signList}" >
                             	 	<option value="${record.sign}"<c:if test="${model.record.thsg == record.sign}"> selected </c:if> >${record.sign}</option>
						</c:forEach> 
					</select>
					<font class="text16RedBold" >*</font><span title="thenkl">Type af procedure</span>&nbsp;
					<%-- Must be model attribute in order to validate towards the filter (thsg) --%>
           			<select name="thenkl" id="thenkl">
	            		<option value="J"<c:if test="${model.record.thenkl == 'J'}"> selected </c:if> >Forenklet</option>
					  	<option value="N"<c:if test="${model.record.thenkl == 'N'}"> selected </c:if> >Normal</option>
								   
					</select>
				</td>
				<td>&nbsp;</td>
			</tr>	
		</c:otherwise>
		</c:choose>

		<tr height="10"><td colspan="2">&nbsp;</td></tr>
		<%-- --------------- --%>
		<%-- LEFT SIDE CELL --%>
		<%-- --------------- --%>
		<tr>
		<td width="55%">
		<table border="0" cellspacing="1" cellpadding="0">
			<tr>
	            <td width="5">&nbsp;</td>
	            <td >
	            	<table align="left" border="0" cellspacing="0" cellpadding="0">
	            		
				 		<tr>
				 			<td class="text12">
				 				<img onMouseOver="showPop('deklTyp_info');" onMouseOut="hidePop('deklTyp_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
			 					<b>1</b><font class="text16RedBold" >&nbsp;*</font><span title="thdk">Angivelse&nbsp;</span>
			 				
			 					<div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="deklTyp_info" class="popupWithInputText text11"  >
					           			<ul>
					           				<li>
					           				 	<b>T-</b>&nbsp;for the transportation of mixed consignments of T1 and T2 goods. In this case, the
													goods will be specified in separate SAD-BIS forms or loading lists for each
													status category of goods. The blank space after the symbol T should be struck
													through to prevent the insertion of any additional digits or numbers.
					           				</li>	
											<li>
					           				 	<b>T1</b>&nbsp;to cover the movement of non-Community goods within the Community and
													to/from the Community and the EFTA countries, as well as between the EFTA
													countries themselves. T1 is also used to cover the movement of Community goods in
													certain circumstances..
					           				</li>
					           				<li>
					           				 	<b>T2</b>&nbsp;for the transportation of Community goods where required.
					           				</li>
					           				<li>
					           				 	<b>T2F</b>&nbsp;
					           				 	for Community goods consigned to/from/between the non-fiscal territories of the Community
					           				 	E.g. Åland och Kanarieöarna
					           				</li>
					           				
					           				<li>
					           				 	<b>T2SM</b>&nbsp;SE T2 + SAN MARINO (Gäller inte NCTS Sverige [ref. NCTS Manual sida 174].)
					           				</li>
					           				<li>
					           				 	<b>TIR</b>Transport Internationaux Routiers (Gäller inte NCTS Sverige [ref. NCTS Manual sida 174].)
					           				</li>
					           				
					           				<li>
					           				 	<b>SS</b>Förhandsanmälan
					           				</li>
					           				<li>
					           				 	<b>T2L</b>TEST
					           				</li>
					           				
					           			</ul>
					           			Note: where the symbols T1, T2, T2F, T1bis, T2bis, T2Fbis, as appropriate, have been
										omitted the goods shall be deemed to have been placed under the T1 procedure.
					           			<br/>
					           			Procedurernas syfte är att visa vad ditt gods har för tullstatus det vill säga om det är en gemenskapsvara eller icke-gemenskapsvara.
										<br/>Transiterar du en icke-gemenskapsvara ska du alltid använda T1 proceduren. Om du istället transiterar en gemenskapsvara kan du oftast använda T2 proceduren.						           			
								</span>	
								</div>
							</td>	
				 			
				 			<td>
				 				<select class="inputTextMediumBlueMandatoryField" name="thdk" id="thdk" TABINDEX=1>
				 				  <option value="">-vælg-</option>
				 				  	<c:forEach var="code" items="${model.ncts031_DeklType_CodeList}" >
                                	 	<option value="${code.tkkode}"<c:if test="${model.record.thdk == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
									</c:forEach> 
								</select>
			 				</td>
			 				<td class="text12">
			 					&nbsp;<img onMouseOver="showPop('4_info');" onMouseOut="hidePop('4_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 				<b>4.</b><span title="thlstl">Ladelister&nbsp;</span>
			 					<div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="4_info" class="popupWithInputText text11"  >	
           							<ul>
				           				<li>
				           				Udfyldes ikke, fordi papirladelister blev afskaffet med virkning fra den 1. januar 2005.		
										</li>	
									</ul>									
								</span>
								</div>								
				 			</td>
				 			<td>	
				 				<input readonly type="textReadOnly" class="inputTextMediumBlue"  name="thlstl" id="thlstl" size="8" maxlength="4" value='${model.record.thlstl}'>
			 				</td>
			 				
			 				<td class="text12">&nbsp;&nbsp;
			 					&nbsp;<img onMouseOver="showPop('5_info');" onMouseOut="hidePop('5_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 				<b>5.</b><span title="thvpos">V.poster&nbsp;</span>
								<div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="5_info" class="popupWithInputText text11"  >	
				           				<ul>
					           				<li>
					           				 The total number of items declared on the SAD will be entered here. The number of
											 items corresponds to the number of boxes 31 to be completed. 
					           				</li>	
										</ul>
										Fältet är spärrat. Beräknas automatiskt från varulinjerna.
								</span>
								</div>								
				 			</td>
				 			<td>	
				 				<input type="text" class="inputTextReadOnly" readonly name="thvpos" id="thvpos" size="8" maxlength="8" value='${model.record.thvpos}'>
			 				</td>
			 			</tr>
		 					
		 				<tr height="10"><td></td></tr>
	 				</table>
 				</td>
			</tr>

			<%-- Validation errors --%>
			<spring:hasBindErrors name="record"> <%-- name must equal the command object name in the Controller --%>
			<tr>
				<td width="5">&nbsp;</td>
				<td>
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
			
			<%-- Other errors (none validation errors) --%>
			<c:if test="${not empty model.errorMessage}">
			<tr>
				<td width="5">&nbsp;</td>
				<td >
	            	<table align="left" border="0" cellspacing="0" cellpadding="0">
				 		<tr>
				 			<td class="textError">
				 				<ul>
                                    <li>
                                      	Fel vid uppdatering. [ERROR:${model.errorMessage}]  
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
	 			<td width="5">&nbsp;</td>
	            <td >		
	 				<%-- SENDER --%>
	 				<table width="100%" align="left" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="15">
				 			<td class="text12White">
								&nbsp;<img onMouseOver="showPop('2_info');" onMouseOut="hidePop('2_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					 			<b>&nbsp;2.</b><font class="text16RedBold" >*</font>Afsender&nbsp;<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
								<div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="2_info" class="popupWithInputText text11"  >	
	           					           			Completion of this box is optional for the Contracting Parties.
									The full name and the address of the consignor/exporter concerned shall be entered.
									<br/>The Contracting Parties may add to the explanatory note the requirement to include a
									reference to the identification number allocated by the competent authorities for tax,
									statistical or other purposes.
									<br/>Where consignments are grouped, the word “Various” in the appropriate language, may
									be entered in this box and the list of consignors may be attached to the declaration.<ul>
								</span>
								</div>
				 				
			 				</td>
		 				</tr>
	 				</table>
	 			</td>
	 		</tr>
	 		<tr>	
	 			<td width="5">&nbsp;</td>
	            <td >	
					<%-- create record --%>
				 	<table width="100%" align="left" class="formFrame" border="0" cellspacing="0" cellpadding="0">
				 		<tr>
					 		<td width="100%">
						 		<table width="100%" border="0" cellspacing="0" cellpadding="0">
							 		<tr height="10"><td ></td></tr>
							        
							        <tr>
							        	<%-- ================================================================================== --%>
							        	<%-- This hidden values are used when an AJAX event from within a dialog box is fired.  
							        		 These original values will be used when the user clicks "Cancel" buttons (puttting
							        		 back original value)																--%> 
							        	<%-- ================================================================================== --%>
							        	<input type="hidden" name="orig_thkns" id="orig_thkns" value='${model.record.thkns}'>
							        	<input type="hidden" name="orig_thnas" id="orig_thnas" value='${model.record.thnas}'>
							        	<input type="hidden" name="orig_thtins" id="orig_thtins" value='${model.record.thtins}'>
							        	<input type="hidden" name="orig_thads1" id="orig_thads1" value='${model.record.thads1}'>
							        	<input type="hidden" name="orig_thpns" id="orig_thpns" value='${model.record.thpns}'>
							        	<input type="hidden" name="orig_thpss" id="orig_thpss" value='${model.record.thpss}'>
							        	<input type="hidden" name="orig_thlks" id="orig_thlks" value='${model.record.thlks}'>
							        	<input type="hidden" name="orig_thsks" id="orig_thsks" value='${model.record.thsks}'>
							        	
							        	
							            <td class="text12" align="left" >&nbsp;&nbsp;<span title="thkns">Kundenummer</span></td>
							            <td class="text12" align="left" >&nbsp;<font class="text16RedBold" >*</font><span title="thnas">Navn&nbsp;</span>
							            	<a tabindex="-1" id="thnasIdLink">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
											</a>
							            			
							            </td>
							        </tr>
							        <tr>
							            <td class="text12" align="left"><input type="text" class="inputTextMediumBlue" name="thkns" id="thkns" size="8" maxlength="8" value="${model.record.thkns}"></td>
							            <td class="text12" align="left"><input type="text" class="inputTextMediumBlueMandatoryField" name="thnas" id="thnas" size="30" maxlength="35" value="${model.record.thnas}"></td>
							            
							        </tr>
							        
							        <tr>
							            <td class="text12" align="left" >&nbsp;<font class="text16RedBold" >*</font><span title="thtins">CVR/SE-nr</span></td>
							            <td class="text12" align="left" >&nbsp;&nbsp;</td>
							        </tr>
							        <tr>
							            <td align="left"><input type="text" class="inputTextMediumBlueMandatoryField" name="thtins" id="thtins" size="20" maxlength="17" value="${model.record.thtins}"></td>
							            <td align="left">&nbsp;</td>
							        </tr>
							        <tr height="4"><td>&nbsp;</td></tr>
							        <tr>
							            <td class="text12" align="left" >&nbsp;<font class="text16RedBold" >*</font><span title="thads1">Adresse</span></td>
							            <td class="text12" align="left" >&nbsp;<span title="thsks">Sprogkode</span>
						            		
							            </td>
							        </tr>
							        <tr>
							            <td align="left"><input type="text" class="inputTextMediumBlueMandatoryField"  name="thads1" id="thads1" size="30" maxlength="35" value="${model.record.thads1}"></td>
							            <td class="text12" align="left" >
						            		&nbsp;<select class="inputTextMediumBlue" name="thsks" id="thsks">
							            		<option value="">-vælg-</option>
							 				  	<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
			                                	 	<option value="${code.tkkode}"<c:if test="${model.record.thsks == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
												</c:forEach> 
											</select>
											<a tabindex="-1" id="thsksIdLink" OnClick="triggerChildWindowLanguageCodes(this)">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
											</a>
										</td>
							        </tr>
							        <tr>
							        		<td>
								        		<table>
								        		<tr>
								            		<td class="text12" align="left" >&nbsp;<font class="text16RedBold" >*</font><span title="thpss">By</span></td>
								            		<td align="left">&nbsp;</td>
								            	</tr>
								        		<tr>
								            		<td align="left">
								       				<input type="text" class="inputTextMediumBlueMandatoryField" name="thpss" id="thpss" size="30" maxlength="35" value="${model.record.thpss}">
							            			</td> 
								            		<td align="left">&nbsp;</td>
								        		</tr>    	
								            	</table>
							            </td>
							            <td >
								            	<table>
								        		<tr>
								        			<td class="text12" align="left" >&nbsp;<font class="text16RedBold" >*</font><span title="thpns">Postnummer</span></td>
								            		<td class="text12" align="left" >&nbsp;<font class="text16RedBold" >*</font><span title="thlks">Land</span>
								            		
								            		</td>
								            	</tr>
								        		<tr >
								        			<td align="left"><input type="text" class="inputTextMediumBlueMandatoryField" name="thpns" id="thpns" size="10" maxlength="9" value="${model.record.thpns}"></td> 
								            		<td align="left">
								            			<select class="inputTextMediumBlueMandatoryField" name="thlks" id="thlks">
										            		<option value="">-vælg-</option>
									 				  	<c:forEach var="country" items="${model.countryCodeList}" >
					                                	 	<option value="${country.dkkd_kd}"<c:if test="${model.record.thlks == country.dkkd_kd}"> selected </c:if> >${country.dkkd_kd}</option>
														</c:forEach> 
													</select>
													<a id="thlksIdLink" OnClick="triggerChildWindowCountryCodes(this)">
								            			<img style="cursor:pointer;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
								            		</a>
								            		</td> 
								        		</tr>  
								            	</table>
							            </td>
						            	</tr>
							        <tr height="15">
							            <td class="text12Bold" align="left" >&nbsp;</td> 
							        </tr>
						        </table>
					        </td>
				        </tr>
					</table>          
            	</td>
           	</tr> 
           	<tr height="10"><td></td></tr>
           	
           	<%-- RECEIVER --%>
	 		<tr>
	 			<td width="5">&nbsp;</td>
	            <td >		
	 				<table width="100%" align="left" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="15">
				 			<td class="text12White">
				 				&nbsp;<img onMouseOver="showPop('8_info');" onMouseOut="hidePop('8_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 				<b>&nbsp;8.</b>Modtager&nbsp;<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
				 				<div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="8_info" class="popupWithInputText text11"  >	
				           			<ul>
					           			<li>The full name and address of the person(s) or company(s) to whom the goods are to be
											delivered (consignee) shall be entered here. Where consignments are grouped, theword
											‘various’, in the appropriate language, may be entered in this box and the list of
											consignees may be attached to the SAD.
					           			</li>
					           		</ul>
								</span>
								</div>
				 				
			 				</td>
		 				</tr>
	 				</table>
	 			</td>
	 		</tr>
	 		<tr>	
	 			<td width="5">&nbsp;</td>
	            <td >	
					<%-- create record --%>
				 	<table width="100%" align="left" class="formFrame" border="0" cellspacing="0" cellpadding="0">
				 		<tr>
					 		<td width="100%">
						 		<table width="100%" border="0" cellspacing="0" cellpadding="0">
							 		<tr height="10"><td ></td></tr>
							        
							        <tr>
							        	<%-- ================================================================================== --%>
							        	<%-- This hidden values are used when an AJAX event from within a dialog box is fired.  
							        		 These original values will be used when the user clicks "Cancel" buttons (puttting
							        		 back original value)																--%> 
							        	<%-- ================================================================================== --%>
							        	<input type="hidden" name="orig_thknk" id="orig_thknk" value='${model.record.thknk}'>
							        	<input type="hidden" name="orig_thnak" id="orig_thnak" value='${model.record.thnak}'>
							        	<input type="hidden" name="orig_thtink" id="orig_thtink" value='${model.record.thtink}'>
							        	<input type="hidden" name="orig_thadk1" id="orig_thadk1" value='${model.record.thadk1}'>
							        	<input type="hidden" name="orig_thpnk" id="orig_thpnk" value='${model.record.thpnk}'>
							        	<input type="hidden" name="orig_thpsk" id="orig_thpsk" value='${model.record.thpsk}'>
							        	<input type="hidden" name="orig_thlkk" id="orig_thlkk" value='${model.record.thlkk}'>
							        	<input type="hidden" name="orig_thskk" id="orig_thskk" value='${model.record.thskk}'>
							        	
							            <td class="text12" align="left" >&nbsp;&nbsp;<span title="thknk">Kundenummer</span></td>
							            <td class="text12" align="left" >&nbsp;<font class="text16RedBold" >*</font><span title="thnak">Navn&nbsp;</span>
							            	<a tabindex="-1" id="thnakIdLink">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
											</a>	
										</td>
							        </tr>
							        <tr>
							            <td align="left"><input type="text" class="inputTextMediumBlue" name="thknk" id="thknk" size="8" maxlength="8" value="${model.record.thknk}"></td>
							            <td align="left"><input type="text" class="inputTextMediumBlueMandatoryField" name="thnak" id="thnak" size="30" maxlength="35" value="${model.record.thnak}"></td>
							        </tr>

							        <tr>
							            <td class="text12" align="left" >&nbsp;&nbsp;<span title="thtink">EORI</span></td>
							            <td class="text12" align="left" >&nbsp;&nbsp;</td>
							        </tr>
							        <tr>
							            <td align="left"><input type="text" class="inputTextMediumBlue" name="thtink" id="thtink" size="20" maxlength="17" value="${model.record.thtink}"></td>
							            <td align="left">&nbsp;</td>
							        </tr>
							        <tr height="4"><td>&nbsp;</td></tr>
							        <tr>
							            <td class="text12" align="left" >&nbsp;<font class="text16RedBold" >*</font><span title="thadk1">Adresse</span></td>
							            <td class="text12" align="left" >&nbsp;<span title="thskk">Sprogkode</span>
						            		
							            </td>
							        </tr>
							        <tr>
							            <td align="left"><input type="text" class="inputTextMediumBlueMandatoryField" name="thadk1" id="thadk1" size="30" maxlength="35" value="${model.record.thadk1}"></td>
							            <td class="text12" align="left" >
							            		&nbsp;<select class="inputTextMediumBlue" name="thskk" id="thskk">
								            		<option value="">-vælg-</option>
								 				  	<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
				                                	 	<option value="${code.tkkode}"<c:if test="${model.record.thskk == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
													</c:forEach> 
											</select>
											<a tabindex="-1" id="thskkIdLink" OnClick="triggerChildWindowLanguageCodes(this)">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
											</a>
										</td>
							        </tr>
							        <tr>
							        		<td>
								        		<table>
								        		<tr>
								            		<td class="text12" align="left" >&nbsp;<font class="text16RedBold" >*</font><span title="thpsk">By</span></td>
								            		<td align="left">&nbsp;</td>
								            	</tr>
								        		<tr>
								            		<td align="left">
								       				<input type="text" class="inputTextMediumBlueMandatoryField"  name="thpsk" id="thpsk" size="30" maxlength="35" value="${model.record.thpsk}">
							            			</td> 
								            		<td align="left">&nbsp;</td>
								        		</tr>    	
								            	</table>
							            </td>
							            <td >
								            	<table>
								        		<tr>
								        			<td class="text12" align="left" >&nbsp;<font class="text16RedBold" >*</font><span title="thpnk">Postnummer</span></td>
								            		<td class="text12" align="left" >&nbsp;<font class="text16RedBold" >*</font><span title="thlkk">Land</span>
								            		
								            		</td>
								            	</tr>
								        		<tr >
								        			<td align="left"><input type="text" class="inputTextMediumBlueMandatoryField" name="thpnk" id="thpnk" size="10" maxlength="9" value="${model.record.thpnk}"></td> 
								            		<td align="left">
								            			<select class="inputTextMediumBlueMandatoryField" name="thlkk" id="thlkk">
										            		<option value="">-vælg-</option>
									 				  	<c:forEach var="country" items="${model.countryCodeList}" >
					                                	 	<option value="${country.dkkd_kd}"<c:if test="${model.record.thlkk == country.dkkd_kd}"> selected </c:if> >${country.dkkd_kd}</option>
														</c:forEach> 
													</select>
													<a id="thlkkIdLink" OnClick="triggerChildWindowCountryCodes(this)">
									            			<img style="cursor:pointer;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
								            		</a>
								            		
								            		</td> 
								        		</tr>  
								            	</table>
							            </td>
						            	</tr>
							        
							        <tr height="15">
							            <td class="text12Bold" align="left" >&nbsp;</td> 
							        </tr>
						        </table>
					        </td>
				        </tr>
					</table>          
            	</td>
           	</tr> 
           	<tr height="10"><td></td></tr>
           	
            <%-- INVOICE AMOUNT Fields 
            <tr>
	            <td width="5">&nbsp;</td>
	            <td >
	                <table align="left" border="0" cellspacing="0" cellpadding="0">
				 		<tr>
				 			<td class="text12"><font class="text16RedBold" >*</font><b><span title="thgbl">Garantibeløb&nbsp;</span></b></td>
				 			<td align="left" ><input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue" name="thgbl" id="thgbl" size="20" maxlength="20" value="${model.record.thgbl}"></td>
				 			<td class="text12">&nbsp;<font class="text16RedBold" >*</font><span title="thgvk">Møntsort</span>
				 				<%-- Note: onChange event in jQuery for this currency list 
				 				<select name="thgvk" id="thgvk" >
				 				  <option value="">-vælg-</option>	
				 				  	<c:forEach var="code" items="${model.currencyCodeList}" >
                                	 	<option value="${code.dkkd_kd}"<c:if test="${model.record.thgvk == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
									</c:forEach> 
								</select>
								<a tabindex="-1" target="_blank" href="${model.skatCurrencyCodesURL.value}" onclick="${model.skatCurrencyCodesURL.windowOpenDimensions}" >
				            			<img style="cursor:pointer;" src="resources/images/find.png" border="0" alt="search" >
				            		</a>								
			 				</td>
		 				</tr>
		 				<%--
		 				<tr>
			 				<td class="text12">&nbsp;Kurs&nbsp;</td>
				 			<td class="text12" align="left" ><input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue" name="sveh_vaku" id="sveh_vaku" size="20" maxlength="20" value='${vaku}'></td>
				 			
		 				</tr>
		 				 
		 				 
		 				<tr height="15"><td></td></tr>
					</table>
					</td>
			</tr>
			--%>
			<tr>
				<td width="5">&nbsp;</td>
	            <td >
					<%-- Special section --%>
					<table align="left" class="formFrameHeader" width="100%" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="15">
				 			<td class="text12White">
				 				&nbsp;Specifikke oplysninger om rejseruten og garanti&nbsp;<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
			 				</td>
		 				</tr>
	 				</table>
	 			</td>
 			</tr>
 			<tr>
	 			<td width="5">&nbsp;</td>
	            <td >
	 				<%-- create record --%>
				 	<table align="left" class="formFrame" width="100%" border="0" cellspacing="0" cellpadding="0">
				 		<tr>
					 		<td>
						 		<table align="left" border="0" cellspacing="0" cellpadding="0">
							 		<tr height="15">
							            <td class="text12Bold" align="left" >&nbsp;</td> 
							            <td class="text12Bold" align="left" >&nbsp;</td> 
							            
							        </tr>
							        <tr>
							        	<td class="text12" align="left" >
							        	&nbsp;<img onMouseOver="showPop('51_info');" onMouseOut="hidePop('51_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
							        
							        	<b>51.</b><span title="thtsd1/thtsd2...">Planlagte grænseovergangssteder og lande&nbsp;</span>
							        	<div class="text11" style="position: relative;" align="left">
										<span style="position:absolute;top:2px; width:250px;" id="51_info" class="popupWithInputText text11"  >	
	           	
						           			Skriv det planlagte grænseovergangssted, når forsendelsen kører ind i et EFTA-land (fx Norge eller Schweiz).	
						           			<p>
											Hvis forsendelsen skal køre gennem et EFTA-land for derefter igen at køre ind i EU, 
											skal både det planlagte grænseovergangssted ind i EFTA-landet og det planlagte grænseovergangssted ind i EU anføres, 
											dvs. hvis en forsendelse fx skal køre fra Danmark til Italien via Schweiz, så skal både indgangstoldstedet til Schweiz og indgangstoldstedet til Italien skrives på forsendelsesangivelsen.
						           			
											</p>
										</span>
										</div>
										</td>		
							        	<td class="text12" align="left" >&nbsp;</td>
							        </tr>
							        
							        <tr>
							        	<td>&nbsp;&nbsp;
							            	<table align="left" border="0" cellspacing="0" cellpadding="0">
							            		<tr>
							            			
							            			<td class="text12" >
							            			1.<input type="text" class="inputTextMediumBlue" name="thtsd1" id="thtsd1" size="10" maxlength="8" value="${model.record.thtsd1}">
									            		<a id="thtsd1IdLink" OnClick="triggerChildWindowTullkontorCodes(this)">
									            			<img style="cursor:pointer;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
									            		</a>
								            		</td>

							            			<td class="text12" >
							            			&nbsp;
							            			&nbsp;
							            			2.<input type="text" class="inputTextMediumBlue" name="thtsd2" id="thtsd2" size="10" maxlength="8" value='${model.record.thtsd2}'>
									            		<a id="thtsd2IdLink" OnClick="triggerChildWindowTullkontorCodes(this)">
									            			<img style="cursor:pointer;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
									            		</a>
							            			</td>
							            			
							            			<td class="text12" >
							            			&nbsp;
							            			&nbsp;
							            			3.<input type="text" class="inputTextMediumBlue" name="thtsd3" id="thtsd3" size="10" maxlength="8" value='${model.record.thtsd3}'>
							            			<a id="thtsd3IdLink" OnClick="triggerChildWindowTullkontorCodes(this)">
								            			<img style="cursor:pointer;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
								            		</a>
							            			</td>
							            		</tr>
							            		
							            		<tr>
							            			<td class="text12" >
							            			4.<input type="text" class="inputTextMediumBlue" name="thtsd4" id="thtsd4" size="10" maxlength="8" value='${model.record.thtsd4}'>
							            			<a id="thtsd4IdLink" OnClick="triggerChildWindowTullkontorCodes(this)">
								            			<img style="cursor:pointer;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
								            		</a>
							            			</td>
							            			
							            			<td class="text12" >
							            			&nbsp;
							            			&nbsp;
							            			5.<input type="text" class="inputTextMediumBlue" name="thtsd5" id="thtsd5" size="10" maxlength="8" value='${model.record.thtsd5}'>
							            			<a id="thtsd5IdLink" OnClick="triggerChildWindowTullkontorCodes(this)">
								            			<img style="cursor:pointer;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
								            		</a>
							            			</td>
							            			
							            			<td class="text12" >
							            			&nbsp;
							            			&nbsp;
							            			6.<input type="text" class="inputTextMediumBlue" name="thtsd6" id="thtsd6" size="10" maxlength="8" value='${model.record.thtsd6}'>
							            			<a id="thtsd6IdLink" OnClick="triggerChildWindowTullkontorCodes(this)">
								            			<img style="cursor:pointer;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
								            		</a>
							            			</td>					            			
							            		</tr>
							            		<tr>
							            			<td class="text12" >
							            			7.<input type="text" class="inputTextMediumBlue" name="thtsd7" id="thtsd7" size="10" maxlength="8" value='${model.record.thtsd7}'>
							            			<a id="thtsd7IdLink" OnClick="triggerChildWindowTullkontorCodes(this)">
								            			<img style="cursor:pointer;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
								            		</a>
							            			</td>
							            			
							            			<td class="text12" >
							            			&nbsp;
							            			&nbsp;
							            			8.<input type="text" class="inputTextMediumBlue" name="thtsd8" id="thtsd8" size="10" maxlength="8" value='${model.record.thtsd8}'>
							            			<a id="thtsd8IdLink" OnClick="triggerChildWindowTullkontorCodes(this)">
								            			<img style="cursor:pointer;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
								            		</a>
							            			</td>
							            		</tr>
							            	</table>
							            </td>     
							        </tr>
							        
							        <tr height="10"><td>&nbsp;</td></tr>

							        <tr>
							        	<td>&nbsp;&nbsp;
							        	<table align="left" border="0" cellspacing="0" cellpadding="0">
								        	<tr>
								        	<td class="text12" align="left" >
								        	
								        	&nbsp;<img onMouseOver="showPop('53_info');" onMouseOut="hidePop('53_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
								        	<b>53.</b><font class="text16RedBold" >*</font><span title="thtsb">Bestemmelsestoldsted</span>&nbsp;
								        	<div class="text11" style="position: relative;" align="left">
											<span style="position:absolute;top:2px; width:250px;" id="53_info" class="popupWithInputText text11"  >	
							           			The name of the customs office at which the goods shall be presented in order to end
												the transit operation shall be entered (office of destination). 
												<br/><br/>
							           			The offices of destination
												are shown in the 'list of customs offices competent to deal with transit operations'. The
												website address is:
												http://europa.eu.int/comm/taxation_customs/dds/en/csrdhome.htm.
											</span>
											</div>
											</td>
								        	
						            		<td ><input type="text" class="inputTextMediumBlueMandatoryField" name="thtsb" id="thtsb" size="10" maxlength="8" value="${model.record.thtsb}">
						            		<a id="thtsbIdLink" OnClick="triggerChildWindowTullkontorCodes(this)">
			            						<img style="cursor:pointer;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
			        			    		</a>
					            			</td>
								            
								            </tr>
							            </table>
							            </td>
							            <td>&nbsp;</td>
							        </tr>
							        	<tr height="18"><td>&nbsp;</td></tr>
							        <tr >
							        	<td >&nbsp;&nbsp;
							            	<table class="tableBorderWithRoundCornersGray" align="left" border="0" cellspacing="2" cellpadding="0">
							            		<tr height="2"><td ></td></tr>
									        	<tr >
										        	<td  colspan="3" class="text12" align="left" >
										        	&nbsp;<img onMouseOver="showPop('52_info');" onMouseOut="hidePop('52_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
										        	<b>52.</b><b>Garantikoder</b>&nbsp;
													<div class="text11" style="position: relative;" align="left">
													<span style="position:absolute;top:2px; width:250px;" id="52_info" class="popupWithInputText text11"  >	
											           		Enter the appropriate code for the type of guarantee being used in the sub division
															marked “CODE” as specified in the table below.
															<br/><br/>
															Enter the appropriate information as specified in the table below under ‘other entries’,
															where required.
															<br/><br/>
															If the comprehensive guarantee, a guarantee waiver or individual guarantee is not valid
															for all the Contracting Parties, add 'not valid for' followed by the code of the relevant
															Contracting Party or Parties
										           			<br/><br/>
										           			Garantikoder
										           			<ul>
											           			<li>
											           				<b>0</b>&nbsp;Fritagelse for sikkerhedsstillelse
											           			</li>
											           			<li>
											           				<b>1</b>&nbsp;Samlet kaution / Universal garanti
											           			</li>
											           			<li>
											           				<b>2</b>&nbsp;Enkelt kaution ved kautionist / Enkelt garanti/Garantist
											           			</li>
											           			<li>
											           				<b>3</b>&nbsp;Kontant depositum / Enkelt garanti/Kontant
											           			</li>
											           			<li>
											           				<b>4</b>&nbsp;Enkelt kaution ved sikkerhedsdokumenter (7.000 EUR)
											           			</li>
											           			<li>
											           				<b>5</b>&nbsp;Fritagelse for sikkerhedsstillelse, når det beløb, der skal stilles sikkerhed for, ikke overstiger 500 EUR
											           			</li>
											           			<li>
											           				<b>6</b>&nbsp;Fritagelse for sikkerhedsstillelse, jf. artikel 95 i TK artikel 95 / Undantaget garanti enligt transiteringskonventionen.
											           			</li>
											           			<li>
											           				<b>8</b>&nbsp;Fritagelse for sikkerhedsstillelse for visse offentlige organer.
											           			</li>
											           			<li>
											           				<b>9</b>&nbsp;Enkelt kaution efter bilag 47a i GB Bilag 47a / For individual guarantee of the type under point 3 of Annex IV to Appendix I (NCTS manual);
											           			</li>
											           		</ul>
													</span>	
													</div>
													</td>	
									        	</tr>
									        	<tr height="5"><td></td></tr>
							            		<tr>
							            			<td class="text12">
							            			<img onMouseOver="showPop('52_kode_info');" onMouseOut="hidePop('52_kode_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
							            			<span title="thgkd">Kode</span>
							            			
							            			<div class="text11" style="position: relative;" align="left">
													<span style="position:absolute;top:2px; width:250px;" id="52_kode_info" class="popupWithInputText text11"  >	
										           			Garantikoder
															<p>
																I forbindelse med anvendelse af godkendte ordninger (godkendt afsender) skal kode 0 eller kode 1 altid bruges.
															</p>
										           			<ul>
											           			<li>
											           				<b>0</b>&nbsp;Fritagelse for sikkerhedsstillelse
											           			</li>
											           			<li>
											           				<b>1</b>&nbsp;Samlet kaution / Universal garanti
											           			</li>
											           			<li>
											           				<b>2</b>&nbsp;Enkelt kaution ved kautionist / Enkelt garanti/Garantist
											           			</li>
											           			<li>
											           				<b>3</b>&nbsp;Kontant depositum / Enkelt garanti/Kontant
											           			</li>
											           			<li>
											           				<b>4</b>&nbsp;Enkelt kaution ved sikkerhedsdokumenter (7.000 EUR)
											           			</li>
											           			<li>
											           				<b>5</b>&nbsp;Fritagelse for sikkerhedsstillelse, når det beløb, der skal stilles sikkerhed for, ikke overstiger 500 EUR
											           			</li>
											           			<li>
											           				<b>6</b>&nbsp;Fritagelse for sikkerhedsstillelse, jf. artikel 95 i TK artikel 95 / Undantaget garanti enligt transiteringskonventionen.
											           			</li>
											           			<li>
											           				<b>8</b>&nbsp;Fritagelse for sikkerhedsstillelse for visse offentlige organer.
											           			</li>
											           			<li>
											           				<b>9</b>&nbsp;Enkelt kaution efter bilag 47a i GB Bilag 47a / For individual guarantee of the type under point 3 of Annex IV to Appendix I (NCTS manual);
											           			</li>
											           		</ul>
													</span>
													</div>		
							            			
							            			</td>
							            			<td class="text12">&nbsp;<span title="thgft1">Garantinummer</span></td>
							            			<td class="text12">&nbsp;<span title="thgft2">Annan garanti</span></td>
							            		</tr>
							            		<tr>
							            			<td>
							            				<select name="thgkd" id="thgkd" >
										 				  <option value="">-vælg-</option>
														  <option value="0"<c:if test="${model.record.thgkd == '0'}"> selected </c:if> >0</option>
														  <option value="1"<c:if test="${model.record.thgkd == '1'}"> selected </c:if> >1</option>
														  <option value="2"<c:if test="${model.record.thgkd == '2'}"> selected </c:if> >2</option>
														  <option value="3"<c:if test="${model.record.thgkd == '3'}"> selected </c:if> >3</option>
														  <option value="4"<c:if test="${model.record.thgkd == '4'}"> selected </c:if> >4</option>
														  <option value="5"<c:if test="${model.record.thgkd == '5'}"> selected </c:if> >5</option>
														  <option value="6"<c:if test="${model.record.thgkd == '6'}"> selected </c:if> >6</option>
														  <option value="8"<c:if test="${model.record.thgkd == '8'}"> selected </c:if> >8</option>
														  <option value="9"<c:if test="${model.record.thgkd == '9'}"> selected </c:if> >9</option>
														</select>
							            			</td>
							            			<td><input type="text" class="inputTextMediumBlue" name="thgft1" id="thgft1" size="24" maxlength="24" value="${model.record.thgft1}"></td>
							            			<td><input type="text" class="inputTextMediumBlue" name="thgft2" id="thgft2" size="30" maxlength="35" value="${model.record.thgft2}"></td>
							            		</tr>
							            		<tr>
							            			<td class="text12">&nbsp;<span title="thgadk">Adgangskode</span></td>
							            			<td class="text12">&nbsp;<span title="thgbgi">Begrænsning i EU</span></td>
							            			<td class="text12">
							            			<img onMouseOver="showPop('beg_udenfor_info');" onMouseOut="hidePop('beg_udenfor_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
							            			<span title="thgbgu">Begrænsning udenfor EU [landkode]</span>
							            			<div class="text11" style="position: relative;" align="left">
													<span style="position:absolute;top:2px; width:250px;" id="beg_udenfor_info" class="popupWithInputText text11"  >		           	
									           			<p>
									           			Skriv landekoden på det/de lande, hvor den anvendte sikkerhedsstillelse ikke er gyldig:
									           			</p>
									           			<ul>
									           				<li><b>CH</b>Schweiz</li>
									           				<li><b>NO</b>Norge</li>
									           				<li><b>IS</b>Island</li>
									           				<li><b>AN</b>Andorra</li>
									           				<li><b>SM</b>San Marino</li>
									           				<li><b>HR</b>Kroatien</li>
									           			</ul>													
													</span>	
													</div>									            			
							            			</td>
							            		</tr>
							            		<tr>
							            			<td><input type="text" class="inputTextMediumBlue" name="thgadk" id="thgadk" size="5" maxlength="4" value="${model.record.thgadk}"></td>
							            			<td>
								            			<select name="thgbgi" id="thgbgi" >
									 				  <option value="0"<c:if test="${model.record.thgbgi == '0'}"> selected </c:if> >0</option>
													  <option value="1"<c:if test="${model.record.thgbgi == '1'}"> selected </c:if> >1</option>
													</select>
							            			</td>
							            			<td align="left">
								            			<select name="thgbgu" id="thgbgu">
										            		<option value="">-vælg-</option>
									 				  	<c:forEach var="country" items="${model.countryCodeList}" >
					                                	 	<option value="${country.dkkd_kd}"<c:if test="${model.record.thgbgu == country.dkkd_kd}"> selected </c:if> >${country.dkkd_kd}</option>
														</c:forEach> 
													</select>
									            	<a id="thgbguIdLink" OnClick="triggerChildWindowCountryCodes(this)">
								            			<img style="cursor:pointer;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
								            		</a>
							            			</td> 
							            		</tr>
							            		<tr height="5"><td></td></tr>
							            		
							            		<%-- These fields are not to be presented in the GUI --%>
							            		<tr>
									 			<td class="text12" colspan="2" ><font class="text16RedBold" >*</font><span title="thgbl">Garantibeløb&nbsp;</span></td>
									 			<td class="text12">&nbsp;<span title="thgvk">Møntsort</span></td>
								 			</tr>
									 		<tr>	
									 			<td colspan="2" align="left" >
									 			<input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlueMandatoryField" name="thgbl" id="thgbl" size="25" maxlength="13" value="${model.record.thgbl}">
									 			</td>
									 			<td>
									 				<select class="inputTextMediumBlueMandatoryField" name="thgvk" id="thgvk" >
									 				  <option value="">-vælg-</option>	
									 				  	<c:forEach var="code" items="${model.currencyCodeList}" >
					                                	 	<option value="${code.dkkd_kd}"<c:if test="${model.record.thgvk == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
														</c:forEach> 
													</select>
													<a tabindex="-1" id="thgvkIdLink" >
														<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
													</a>																	 						
								 				</td>
							 				</tr>
							            		<tr height="5"><td></td></tr>
							            	</table>
							            </td>  
							            <td>&nbsp;</td>
							        </tr>
							        <tr height="15">
							            <td class="text12Bold" align="left" >&nbsp;</td> 
							            <td class="text12Bold" align="left" >&nbsp;</td> 
							        </tr>
								</table>
							</td>
						</tr>
						
					</table>
					  
				</td>
		  	</tr>
           	</table>
		</td>
		<%-- --------------- --%>
		<%-- RIGHT SIDE CELL --%>
		<%-- --------------- --%>
		<td width="45%" align="center" valign="top">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="2">&nbsp;</td>
					<td valign="top">
			 			<table border="0" cellspacing="0" cellpadding="0">
			 			
			 				<tr >	
				            	<td colspan="4" class="text9BlueGreen" valign="bottom" align="left" >
			 				    	<%-- only status = M or emtpy status is allowed --%>
				 				    <c:choose>
					 				    <c:when test="${ model.record.thst == 'G' ||  model.status=='F' || model.record.thst == 'M' || empty model.record.thst}">
						 				    	<input class="inputFormSubmit" type="submit" name="submit" id="submit" onclick="javascript: form.action='skatnctsexport_edit.do';" value='<spring:message code="systema.skat.ncts.export.createnew.submit"/>'/>
						 				    	&nbsp;&nbsp;
						 				    	<c:if test="${not empty model.record.thtdn}">
						 				    		<input tabindex=-2 class="inputFormSubmit" type="submit" name="send" onclick="javascript: form.action='skatnctsexport_send.do';" value='<spring:message code="systema.skat.ncts.export.createnew.send"/>'/>
						 				    	</c:if>
					 				    </c:when>
					 				    <c:otherwise>
					 				    		<input disabled class="inputFormSubmitGrayDisabled" type="submit" name="submit" value='<spring:message code="systema.skat.submit.not.editable"/>'/>
					 				    </c:otherwise>	
				 				    </c:choose>
	                				</td>
					        </tr>
			 				<tr height="12"><td class="text"></td> </tr>
			 				
					 		<tr>
					            <td class="text12" align="left" >
					            <img onMouseOver="showPop('15_info');" onMouseOut="hidePop('15_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
			 					<b>15.</b><font class="text16RedBold" >*</font><span title="thalk">Afsendelsesland&nbsp;</span>
			 					<div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="15_info" class="popupWithInputText text11"  >	
	           	
					           			<ul>
					           				<li>The name of the country from which goods are to be dispatched/exported shall be
												entered.
											</li>
					           			</ul>
								</span>	
								</div>
								</td>	
					            <td >
					            	<select class="inputTextMediumBlueMandatoryField" name="thalk" id="thalk">
				 						<option value="">-vælg-</option>
					 				  	<c:forEach var="country" items="${model.countryCodeList}" >
	                                	 	<option value="${country.dkkd_kd}"<c:if test="${model.record.thalk == country.dkkd_kd}"> selected </c:if> >${country.dkkd_kd}</option>
										</c:forEach> 
									</select>
					            	<a tabindex="-1" id="thalkIdLink" OnClick="triggerChildWindowCountryCodes(this)">
										<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
									</a>
									
								</td>
							</tr>
							<tr>
					            <td class="text12" align="left" >
					            <img onMouseOver="showPop('17_info');" onMouseOut="hidePop('17_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					            <b>17.</b><font class="text16RedBold" >*</font><span title="thblk">Bestemmelsesland&nbsp;</span>
					            <div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="17_info" class="popupWithInputText text11"  >
					           			<ul>
					           				<li>The name of the country of destination shall be entered.<br/><br/></li>
					           				<li>Det sidst kendte bestemmelsesland. En omladning i et andet land, ændrer ikke landet. Destination land kan aldrig være Europa.</li>
					           			</ul>
								</span>	
					            </div>
					            </td>
					            
					            <td >
					            	<select class="inputTextMediumBlueMandatoryField" name="thblk" id="thblk">
				 						<option value="">-vælg-</option>
					 				  	<c:forEach var="country" items="${model.countryCodeList}" >
	                                	 	<option value="${country.dkkd_kd}"<c:if test="${model.record.thblk == country.dkkd_kd}"> selected </c:if> >${country.dkkd_kd}</option>
										</c:forEach> 
									</select>
					            	<a tabindex="-1" id="thblkIdLink" OnClick="triggerChildWindowCountryCodes(this)">
										<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
									</a>
									
								</td>
					        </tr>
				            
						</table>
					</td>
				</tr>
				
				<tr height="20"><td colspan="2">&nbsp;</td></tr>
		
				<tr>
					<td width="2">&nbsp;</td>
			 		<td>
			 			<table border="0" cellspacing="2" cellpadding="0">
			 				<tr>
					            <td class="text12" align="left" >
					            <img onMouseOver="showPop('18_info');" onMouseOut="hidePop('18_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					            <b>18.</b>
					            <font class="text16RedBold" >*</font><span title="thtaid">Transportmidlets identitet og nationalitet ved afsendelsen </span>
					            <div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="18_info" class="popupWithInputText text11"  >
					           			Transportmidlets identitet og nationalitet ved afsendelsen
					           			<ul>
						           			<li><b>First subdivision</b><br>
						           			The means of identification, for example the registration
											number(s) or name of the means of transport (lorry, ship, railway wagon, aircraft)
											on which the goods are directly loaded on presentation at the office of departure.
						           			</li>
						           			<br/>
						           			<li><b>Second subdivision</b><br>
												The nationality of the means of transport, using the codes
												laid down for that purpose.						           			
											</li>
					           			</ul>
					           			If the means of transport is made up of several means of transport, the nationality of
										the means of transport, which provides propulsion, shall be entered.
										<br/><br/>
					           			For example, where a tractor and a trailer with different registration numbers are used,
										enter the registration numbers of both tractor and trailer, and the nationality of the tractor.
										<br/>
										<b>LORRIES</b><br/>
										the vehicle’s registration number shall be entered
										<br/><br/><b>CONTAINERS</b><br/>
										the container’s number shall not be entered in this box, but in box 31. Details of the
										vessel or vehicle which is transporting the container shall be entered in box18.
										<br/><br/><b>SHIP</b><br/>
										the name of the ship shall be entered
										<br/><br/><b>AIRCRAFT</b><br/>
										the aircraft’s registration letters shall be entered
										<br/><br/><b>RAIL</b><br/>
										The railway carriage’s number shall be entered. Details of the nationality shall not be
										entered.
										For demountable bodies on railway carriages the demountable body’s number shall be
										entered in box 31, as a demountable body is considered to be a container.
										<br/><br/><b>FIXED TRANSPORT INSTALLATIONS</b><br/>
										Details of the registration number or nationality shall not be entered.
										In other cases, declaration of the nationality is optional for Contracting Parties.
								</span>
								</div>
								</td>
								<td class="text">&nbsp;</td>	
					        </tr>
					        <tr>
				            	<td >
				            		<input type="text" class="inputTextMediumBlueMandatoryField" name="thtaid" id="thtaid" size="25" maxlength="35" value="${model.record.thtaid}">
				            	</td>
				            	<td class="text">&nbsp;</td>
	        				</tr>
	        				<tr>
				            	<td class="text12">
				            		<font class="text16RedBold" >*</font><span title="thtalk">Nationalitet</span>
				            		
				            		<select class="inputTextMediumBlueMandatoryField" name="thtalk" id="thtalk">
				 						<option value="">-vælg-</option>
					 				  	<c:forEach var="country" items="${model.countryCodeList}" >
	                                	 	<option value="${country.dkkd_kd}"<c:if test="${model.record.thtalk == country.dkkd_kd}"> selected </c:if> >${country.dkkd_kd}</option>
										</c:forEach> 
									</select>
									<a tabindex="-1" id="thtalkIdLink" OnClick="triggerChildWindowCountryCodes(this)">
										<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
									</a>
				            		
									&nbsp;<span title="thtask">Sprog</span>
						            		
									<select name="thtask" id="thtask">
			            				<option value="">-vælg-</option>
			 				  			<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
                               	 			<option value="${code.tkkode}"<c:if test="${model.record.thtask == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
										</c:forEach> 
									</select>
									<a tabindex="-1" id="thtaskIdLink" OnClick="triggerChildWindowLanguageCodes(this)">
										<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
									</a>
				            	</td>
				            	<td class="text">&nbsp;</td>
	        				</tr>
	        				<tr>
				            	<td class="text12">
				            		<img onMouseOver="showPop('thtrmi_info');" onMouseOut="hidePop('thtrmi_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				            		<b>26.</b><span title="thtrmi">Transp.måde ved afsendelsen&nbsp;</span>
				 				  	<select class="inputTextMediumBlue" name="thtrmi" id="thtrmi">
				 						<option value="">-vælg-</option>
					 				  	<c:forEach var="code" items="${model.transportmadeCodeList}" >
					 				  		<option value="${code.tkkode}"<c:if test="${model.record.thtrmi == code.tkkode}"> selected </c:if> >${code.tkkode}-${code.tktxtn}</option>
										</c:forEach>  
									</select>
				            	<div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="thtrmi_info" class="popupWithInputText text11"  >
					           			This box is optional for EU Member States in respect of Community transit.
										Using the appropriate Community codes, enter the mode of
										transport upon departure.
					           			<ul>
					           				<c:forEach var="code" items="${model.transportmadeCodeList}" >
						 				  		<li><b>${code.tkkode}</b>&nbsp;${code.tktxtn}</li>
					 				  		</c:forEach>	
					           			</ul>
								</span>
								</div>
								</td>
								<td class="text">&nbsp;</td>
	        				</tr>
						</table>
					</td>
				</tr>
				
				<tr height="10"><td class="text">&nbsp;</td> </tr>
				<tr>
					<td width="2">&nbsp;</td>
			 		<td>
						<table border="0" cellspacing="2" cellpadding="0">	
					 		<tr>
					            <td class="text12" align="left">
					            <img onMouseOver="showPop('21_info');" onMouseOut="hidePop('21_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					            <b>21.</b>
					            <span title="thtgid">Det grænseoverskridende aktive transportmiddels identitet og nationalitet</span>
					            <div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="21_info" class="popupWithInputText text11"  >
					           			Enter the appropriate code for the type (lorry, ship, railway wagon, aircraft, etc.) and
										the means of identification (e.g. registration number or name) of the active means of
										transport (i.e. the means of transport providing propulsion) which it is presumed will
										propel the consignment across the external border of the Contracting Party where the
										office of departure is located. 
										<br/><br/>Then enter the code for the nationality of the means of
										transport, as known at the time the goods were placed under the transit procedure.
										<br/><br/><br/><br/>
										Where combined transport or several means of transport are used, the active means of
										transport is the unit which provides propulsion for the whole combination. For example
										in the case of a lorry on a sea-going vessel the active means of transport is the ship;
										and where a combination of a tractor and a trailer is used, the active means of
										transport is the tractor.
								</span>	
								</div>
								</td>
					            <td class="text">&nbsp;</td>
					        </tr>
				            <tr>
				            	<td >
				            		<input type="text" class="inputTextMediumBlue" name="thtgid" id="thtgid" size="25" maxlength="35" value="${model.record.thtgid}">
				            	</td>
				            	<td class="text">&nbsp;</td>
	        				</tr>
	        				<tr>
				            	<td class="text12">
				            		<span title="thtglk">Nationalitet</span>
				            		
				            		<select class="inputTextMediumBlue" name="thtglk" id="thtglk">
				 						<option value="">-vælg-</option>
					 				  	<c:forEach var="country" items="${model.countryCodeList}" >
	                                	 	<option value="${country.dkkd_kd}"<c:if test="${model.record.thtglk == country.dkkd_kd}"> selected </c:if> >${country.dkkd_kd}</option>
										</c:forEach> 
									</select>
									<a id="thtglkIdLink" OnClick="triggerChildWindowCountryCodes(this)">
					            			<img style="cursor:pointer;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
				            		</a>
				            		
									&nbsp;<span title="thtgsk">Sprog</span>
						            		
									<select name="thtgsk" id="thtgsk">
			            				<option value="">-vælg-</option>
			 				  			<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
                               	 			<option value="${code.tkkode}"<c:if test="${model.record.thtgsk == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
										</c:forEach> 
									</select>
									<a tabindex="-1" id="thtgskIdLink" OnClick="triggerChildWindowLanguageCodes(this)">
										<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
									</a>
									
				            	</td>
				            	<td class="text">&nbsp;</td>
	        				</tr>
 		       				<tr>
				            	<td class="text12">
				            		<img onMouseOver="showPop('thtrm_info');" onMouseOut="hidePop('thtrm_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
		            				<b>25.</b><span title="thtrm">Transp.måde ved grænsen&nbsp;</span>
				            		<select class="inputTextMediumBlue" name="thtrm" id="thtrm">
			 						<option value="">-vælg-</option>
				 				  	<c:forEach var="code" items="${model.transportmadeCodeList}" >
				 				  		<option value="${code.tkkode}"<c:if test="${model.record.thtrm == code.tkkode}"> selected </c:if> >${code.tkkode}-${code.tktxtn}</option>
									</c:forEach> 
								</select>
				            	<div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="thtrm_info" class="popupWithInputText text11"  >
				           			Using the following codes enter the mode of the active means of transport on which the
									goods will leave the territory of the Contracting Party in which the office of departure
									is located.
				           			<ul>
				           				<c:forEach var="code" items="${model.transportmadeCodeList}" >
					 				  		<li><b>${code.tkkode}</b>&nbsp;${code.tktxtn}</li>
				 				  		</c:forEach>	
				           			</ul>
								</span>
								</div>
								</td>
							<td class="text">&nbsp;</td>
	        				</tr>
	        					
						</table>
					</td>
				</tr>
				<tr height="15"><td class="text">&nbsp;</td> </tr>
				
				<tr>
					<td width="2">&nbsp;</td>
		 			<td class="text12">
		 				<table align="left" border="0" cellspacing="0" cellpadding="0">
			 				<tr>
				 				<td class="text12">
				 				<img onMouseOver="showPop('19_info');" onMouseOut="hidePop('19_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 				<b>19.</b>&nbsp;<font class="text16RedBold" >*</font><span title="thkdc">Container&nbsp;&nbsp;</span>
				 				<div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="19_info" class="popupWithInputText text11"  >
					           		Enter one of the following codes to give the presumed situation at the border of the
									Contracting Party in whose territory the office of departure is located, as known at the
									time the goods are placed under the transit procedure.
				           			<ul>
					           			<li><b>0</b>&nbsp;-&nbsp;Where the goods are not carried in a container</li>
					           			<li><b>1</b>&nbsp;-&nbsp;Where the goods are carried in a container</li>
					           		</ul>
								</span>
								</div>
								</td>
				 				<td class="text12" >
		 							<select class="inputTextMediumBlueMandatoryField" name="thkdc" id="thkdc">
				 						<option value="0"<c:if test="${model.record.thkdc == 0}"> selected </c:if> >0</option>
								  		<option value="1"<c:if test="${model.record.thkdc == 1}"> selected </c:if> >1</option>
								  	</select>
		 						</td>
			 				</tr>
		 				</table>
		 			</td>				 			
		 		</tr>	
				<tr>
					<td width="2">&nbsp;</td>
			 		<td>
			 			<table border="0" cellspacing="1" cellpadding="0">
						<tr>
				            <td class="text12" align="left" >
				            <img onMouseOver="showPop('lastplats_info');" onMouseOut="hidePop('lastplats_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">	
				            <b>27.</b>&nbsp;<span title="thlasd">Indladningssted</span>
				            
				            <div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="lastplats_info" class="popupWithInputText text11"  >
				           			<b>Place of loading</b>
				           			<br/><br/>
				           			<ul>
				           				<li>Enter, using the appropriate code or text where available, the place where the goods are to be
											loaded onto the active means of transport on which they are to cross the border of the
											Contracting Party in whose territory the office of departure is located, as known at the
											time the goods are placed under the transit procedure.
										</li>
				           			</ul>
							</span>
							</div>
							</td>
							<td class="text12" >
						    	<input type="text" class="inputTextMediumBlue" name="thlasd" id="thlasd" size="17" maxlength="17" value="${model.record.thlasd}">
						    </td>
						</tr>
	        				<tr height="10"><td class="text">&nbsp;</td> </tr>
			 				<tr>
					            <td class="text12" align="left" >
					            <img onMouseOver="showPop('autolagringsplats_info');" onMouseOut="hidePop('autolagringsplats_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">	
					            <b>30.</b>&nbsp;<span title="thlsd">Aftalt opbevaringskode</span>
					            <div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="autolagringsplats_info" class="popupWithInputText text11"  >
					           			<ul>
					           				<li>Authorised location of goods, code</li>
					           			</ul>
								</span>	
								</div>
								</td>
							    <td class="text" >
							    	<input type="text" class="inputTextMediumBlue" name="thlsd" id="thlsd" size="17" maxlength="17" value="${model.record.thlsd}">
							    </td>
	        				</tr>
						<tr>	
						    <td class="text12" align="left" >
				            <img onMouseOver="showPop('aftaltKontrolsted_info');" onMouseOut="hidePop('aftaltKontrolsted_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">	
				            <b>30.</b>&nbsp;<span title="thlas2">Aftalt kontrolsted</span>
				           
				            <div class="text11" style="position: relative;" align="left">
							<span style="position:absolute;top:2px; width:250px;" id="aftaltKontrolsted_info" class="popupWithInputText text11"  >
				           			<ul>
			           					<li>Customs sub place</li>
				           			</ul>
							</span>
							</div>
							</td>
							<td class="text12" >
						    		<input type="text" class="inputTextMediumBlue" name="thlas2" id="thlas2" size="17" maxlength="17" value="${model.record.thlas2}">
						    </td>							    
	        				</tr>	
			 				<tr>
					            <td class="text12" align="left">
					            <img onMouseOver="showPop('tullkontor_info');" onMouseOut="hidePop('tullkontor_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">	
					            <b>C.</b>&nbsp;<font class="text16RedBold" >*</font><span title="thcats">Afgangstoldsted&nbsp;</span>
					            <div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="tullkontor_info" class="popupWithInputText text11"  >
					           			<ul>
					           				<li>Avgångstullkontor är det tullkontor där godset lämnar gemenskapens tullområde<br/><br/></li>
					           				<li>Du anger avgångstullkontoret med en kod. Koden består av åtta tecken. De två första anger landet med hjälp av landkod. De följande sex tecknen anger det berörda kontoret i detta land.<br/><br/>
					           					Exempel: Det svenska tullkontoret Malmö har koden SE000050 som är en NCTS-kod.
					           				</li>
					           				
					           			</ul>
								</span>	
								</div>
								</td>
					            
							    <td class="text">
							    <input type="text" class="inputTextMediumBlueMandatoryField"  name="thcats" id="thcats" size="10" maxlength="8" value='${model.record.thcats}'>
			            		<a id="thcatsIdLink" OnClick="triggerChildWindowTullkontorCodes(this)">
			            			<img style="cursor:pointer;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
			            		</a>
								</td>
					        </tr>
					        <tr height="2"><td>&nbsp;</td></tr>
					        <tr>
					            <td class="text12" align="left">
					            <img onMouseOver="showPop('sprakkod_foljedok_info');" onMouseOut="hidePop('sprakkod_foljedok_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">	
					            <font class="text16RedBold" >*</font><span title="thskfd">Sprogkode dok.&nbsp;</span>
					            <div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="sprakkod_foljedok_info" class="popupWithInputText text11"  >
					           			<ul>
					           				<li>Ledsagedokument (f.eks. varelista)
					           				</li>
					           			</ul>
								</span>	
								</div>
								</td>
					            <td align="left">
	            					<select class="inputTextMediumBlueMandatoryField" name="thskfd" id="thskfd">
			            				<option value="">-vælg-</option>
			 				  			<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
                               	 			<option value="${code.tkkode}"<c:if test="${model.record.thskfd == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
										</c:forEach> 
								</select>
				            		<a tabindex="-1" id="thskfdIdLink" OnClick="triggerChildWindowLanguageCodes(this)">
										<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
									</a>
								</td> 
							</tr>
							<tr height="2"><td>&nbsp;</td></tr>
							<tr>
					            <td class="text12" align="left" >
					            <img onMouseOver="showPop('transnr_dt_info');" onMouseOut="hidePop('transnr_dt_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">	
					            <span title="thtrdt">Transitdato</span>
					            <div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="transnr_dt_info" class="popupWithInputText text11"  >
				           			<ul>
				           				<li>Transiteringsdato til godkendelse</li>
				           			</ul>	
								</span>	
								</div>
								</td>
					            <td class="text12" >
					            	<input readonly onKeyPress="return numberKey(event)" type="text" class="inputTextReadOnly" name="thtrdt" id="thtrdt" size="8" value="${model.record.thtrdt}">
					            	&nbsp;
					            </td>
					        </tr>
					        
					        <tr>
					            <td class="text12" align="left" >
					            <img onMouseOver="showPop('deklarantplats_info');" onMouseOut="hidePop('deklarantplats_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">	
					            <font class="text16RedBold" >*</font><span title="thdst">Dekl.sted</span>
					            <div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="deklarantplats_info" class="popupWithInputText text11"  >
					           			<ul>
					           				<li>Plats (inklusive landkod)</li>
					           			</ul>	
								</span>	
								</div>
								</td>
					            
					            <td class="text12" align="left" >
					            	<input type="text" class="inputTextMediumBlueMandatoryField" name="thdst" id="thdst" size="15" maxlength="15" value="${model.record.thdst}">
					            	&nbsp;<font class="text16RedBold" >*</font><span title="thdsk">Dekl.sprog</span>
					            	<select class="inputTextMediumBlueMandatoryField" name="thdsk" id="thdsk">
			            				<option value="">-vælg-</option>
			 				  			<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
                               	 			<option value="${code.tkkode}"<c:if test="${model.record.thdsk == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
										</c:forEach> 
									</select>
				            		<a tabindex="-1" id="thdskIdLink" OnClick="triggerChildWindowLanguageCodes(this)">
										<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
									</a>
					            </td>
					        </tr>
					        <tr>
					            <td class="text12" align="left" >
					            <img onMouseOver="showPop('deklarant_info');" onMouseOut="hidePop('deklarant_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">	
					            <span title="thtarf">Deklarant</span>
					            <div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="deklarant_info" class="popupWithInputText text11"  >
					           			Feltet er blokeret. Hentes automatisk.
								</span>
								</div>
								</td>
					            <td colspan="3" class="text12" align="left" >
					            		<input readonly type="text" class="inputTextReadOnly" name="thtarf" id="thtarf" size="25" value="${model.record.thtarf}">
					            </td>
					        </tr>
					        <tr height="15"><td>&nbsp;</td></tr>	 
							
					        <tr>
					            <td class="text12" align="left" >
					            <img onMouseOver="showPop('6_info');" onMouseOut="hidePop('6_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">	
					            <b>6.&nbsp;</b><span title="thntk">Kollital</span>
					            <div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="6_info" class="popupWithInputText text11"  >
				           			<ul>
				           				<li>The total number of packages (pallets, cartons, coils, etc.) making up the consignments
											in question shall be entered here
				           				</li>
				           			</ul>
				           			Beregnes automatisk fra vareposter men kan øverstyres.
								</span>	
								</div>
								</td>
					            <td ><input onKeyPress="return numberKey(event)" style="text-align: right" type="text" class="inputTextMediumBlue" name="thntk" id="thntk" size="8" maxlength="7" value="${model.record.thntk}"></td>
					        </tr>
					        <tr>
					            <td class="text12" align="left" >
					            <img onMouseOver="showPop('bruttovikt_info');" onMouseOut="hidePop('bruttovikt_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">	
					            <span title="thvkb">Bruttovægt</span>
					            <div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="bruttovikt_info" class="popupWithInputText text11"  >
				           			<ul>
				           				<li>Sending samlede bruttovægt.</li>
				           			</ul>
				           			Beregnes automatisk fra vareposter men kan øverstyres.
								</span>	
								</div>
								</td>
					            
					            <td ><input onKeyPress="return amountKey(event)" style="text-align: right" type="text" class="inputTextMediumBlue" name="thvkb" id="thvkb" size="10" maxlength="9" value="${model.record.thvkb}"></td>
					            
					        </tr>	    
				            <tr height="2"><td>&nbsp;</td></tr>
				            
					     	<%-- This section is not needed in NCTS since the backend sums all kollis at an item level --%>
					     	<tr>
					        		<td class="text12Gray" align="left" >
					        			Antal kollin (i vareposterne)&nbsp;
					        		</td>
						        	<td >
					            		<input readonly style="text-align: right" type="text" class="inputTextReadOnly" name="sumOfAntalKolliInItemLines" id="sumOfAntalKolliInItemLines" size="8" maxlength="7" value="${ model.record.sumOfAntalKolliInItemLinesStr}">
					            		<c:if test="${not empty ( model.record.sumOfAntalKolliInItemLinesStr &&  model.record.thntk)}">
						            		<c:if test="${ model.record.thntk !=  model.record.sumOfAntalKolliInItemLinesStr}">
								            <img onMouseOver="showPop('itemsSumKolli_info');" onMouseOut="hidePop('itemsSumKolli_info');" width="18px" height="20px" src="resources/images/redFlag.png" border="0" alt="kolliantal warning">	
								            <div class="text11" style="position: relative;" align="left">
									            <span style="position:absolute; left:10px; top:0px;" id="itemsSumKolli_info" class="popupWithInputText"  >
								           			<font class="text11">
								           			<p>
								           			Summen af ​​antallet af pakker på varelinje niveau svarer ikke til det angivne antal pakker i posten.
													Vi anbefaler at gennemgå, hvad kunne muligvis være forkert ved at kontrollere varer linjer.
								           			</p>
								           			<p>
								           			Hvis nummeret til venstre er = <b>-1</b> betyder, at der er mere end 0-varelinjer, og summen af varelinjer kolliantal er = 0 (hvilket ikke er korrekt).
								           			</p>
								           			</font>
												</span>	
											</div>
						            		</c:if>
					            		</c:if>
					            </td>
					        </tr>
					         
					        <tr>
					        		<td class="text12Gray" align="left" >Antal vareposter &nbsp;</td>
						        	<td >
					            		<input readonly style="text-align: right" type="text" class="inputTextReadOnly" name="sumOfAntalItemLines" id="sumOfAntalItemLines" size="8" value="${ model.record.sumOfAntalItemLinesStr}">
					            		<c:if test="${not empty ( model.record.sumOfAntalItemLinesStr)}">
						            		<c:if test="${ model.record.sumOfAntalItemLines <= 0 }">
								            <img onMouseOver="showPop('itemsSumAntalLines_info');" onMouseOut="hidePop('itemsSumAntalLines_info');" width="18px" height="20px" src="resources/images/redFlag.png" border="0" alt="varelinjerantal warning">	
								            <div class="text11" style="position: relative;" align="left">
									            <span style="position:absolute; left:10px; top:0px;" id="itemsSumAntalLines_info" class="popupWithInputText"  >
								           			<font class="text11" >Summen af ​​antallet varer linjer må vare > 0</font>
												</span>
											</div>	
						            		</c:if>
					            		</c:if>
					            </td>
					        </tr>
					     	
					     	<tr height="10"><td>&nbsp;</td></tr>
					     	<tr>
					            <td class="text12" align="left">
					            <img onMouseOver="showPop('kontrollresultat_info');" onMouseOut="hidePop('kontrollresultat_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
 								<b>D.</b>&nbsp;<span title="thdkr">Kontrollresultat.&nbsp;</span>
 								<div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="kontrollresultat_info" class="popupWithInputText text11"  >
				           				Forenklet procedure
				           			<br/>
				           			<ul>
					           			<li><b>A3</b>&nbsp; Forenklet procedure</li>
					           		</ul>
					           		<br>
					           		For normal procedure = tomt.
								</span>
								</div>
								</td>
					           	<td class="text12" align="left">
					           		<select name="thdkr" id="thdkr" >
					 				  <option value="">-vælg-</option>
									  <option value="A3"<c:if test="${model.record.thdkr == 'A3'}"> selected </c:if> >A3</option>													  
									</select>
					           		
					           	</td>
				           	</tr>
				           	<tr>
					            <td class="text12" align="left">
					            <img onMouseOver="showPop('frist_info');" onMouseOut="hidePop('frist_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
 								<b>D.</b>&nbsp;<span title="thddt">Deadline&nbsp;</span>
 								<div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="frist_info" class="popupWithInputText text11"  >
				           				Deadline	
				           			<br/>
				           			<ul>
					           			<li>...</li>
					           		</ul>
								</span>
								</div>
								</td>
					           	<td class="text12" align="left">
					           		<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="thddt" id="thddt" size="10" maxlength="8" value="${model.record.thddt}">
					           		&nbsp;
					           	</td>
				           	</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td width="2">&nbsp;</td>
			 		<td>
			 			<table width="80%" align="left" border="0" cellspacing="0" cellpadding="0">
			 				<tr height="5">
			 					<td class="text">&nbsp;</td> 
			 					<td class="text">&nbsp;</td> 
			 				</tr>
			 				<tr >
				            	<td class="text">&nbsp;</td> 
		 						<td class="text">&nbsp;</td> 
			 				</tr>
			 				
							<%-- cloned on top --- DACHSER DK --%>
				            <tr >	
			 				    <td class="text9BlueGreen" valign="bottom" align="left" >
			 				    	<%-- only status = M or emtpy status is allowed --%>
				 				    <c:choose>
					 				    <c:when test="${ model.record.thst == 'G' ||  model.status=='F' || model.record.thst == 'M' || empty model.record.thst}">
						 				    	<input class="inputFormSubmit" type="submit" name="submit2" id="submit2" onclick="javascript: form.action='skatnctsexport_edit.do';" value='<spring:message code="systema.skat.ncts.export.createnew.submit"/>'/>
						 				    	&nbsp;&nbsp;
						 				    	<c:if test="${not empty model.record.thtdn}">
						 				    		<input tabindex=-2 class="inputFormSubmit" type="submit" name="send" id="send2" onclick="javascript: form.action='skatnctsexport_send.do';" value='<spring:message code="systema.skat.ncts.export.createnew.send"/>'/>
						 				    	</c:if>
					 				    </c:when>
					 				    <c:otherwise>
					 				    		<input disabled class="inputFormSubmitGrayDisabled" type="submit" name="submit2" id="submit2" value='<spring:message code="systema.skat.submit.not.editable"/>'/>
					 				    </c:otherwise>	
				 				    </c:choose>
	                				</td>
					        </tr>
					        
					        <tr >	
			            		<td class="text9BlueGreen" valign="bottom" align="left" >
		 					    	<%-- only status = P or U are allowed --%>
			 				        <c:if test="${ model.record.thst == 'P' ||  model.record.thst=='U' }">
			 				        	<c:if test="${not empty model.record.thtrnr}">
				 				    		<button name="cancellationButton" id="cancellationButton" class="buttonGrayWithGreenFrame" type="button" ><spring:message code="systema.skat.cancellation.skat"/></button>
				 				    	</c:if> 
				 				    </c:if>
				 				</td>
					        </tr>
					        
					        <tr height="25"><td colspan="2">&nbsp;</td></tr>
					        
					        <c:if test="${model.record.warrantyAlarm}">
					        <tr>
						        <td colspan="2" >&nbsp;&nbsp;
						            	<table style="border-color:red;border-width: 2px 2px 2px 2px;" class="tableBorderWithRoundCorners" align="left" border="0" cellspacing="2" cellpadding="1">
								    		<tr>
									    		<td class="text14" colspan="2">
									            <img width="18px" height="20px" src="resources/images/redFlag.png" border="0" alt="emergency">	
										    		<b>Brugte garantibeløb</b>&nbsp;<font class="text14RedBold">overskrider</font>&nbsp;<b>alarmgrænsen på</b>&nbsp; 
										    		<b>${model.record.tgprm}&nbsp;%</b>
										    		<ul class="text14">
										    			<li>
										    				Totalt garantibeløb er: <font class="text14GreenBold">${model.record.tggbl}</font>
										    			</li>
										    			<li>
										    				Totalt brugte garantibeløb (herunder dette angivelse) er: <font class="text14RedBold">${model.record.tggblb}</font>
										    			</li>
										    		</ul>
								    			</td>	
								    		</tr>
								    		 	   
						        		</table>
						        </td>
					        </tr>
					        </c:if>
					        
						</table>
					</td>
				</tr>
				
			</table>
		</td>
		</tr>

		<tr height="20"><td colspan="2">&nbsp;</td></tr>
		
		<tr>
		    <td colspan="2">		
			<%-- ------------------------------- --%>
			<%-- tab area container DIVISON AREA --%>
			<%-- ------------------------------- --%>
			<table border="0" cellspacing="0" cellpadding="0">
				<tr >
					<td height="10" class="tabNoBorderSeparatorWhite" align="left" > 
			   			 <table border="0" cellspacing="0" cellpadding="0">
						 	<tr >
						 		<%--
						 		<td class="divisionLine">
					    			&nbsp;
					    		</td>
					    		 --%>
					        </tr>
					     </table> 
					</td>
				</tr>
			</table>
			</td>
	 	</tr>
		<tr height="20"><td colspan="2">&nbsp;</td></tr>
		<tr>
		    <td colspan="2" >
			<%-- ---------------------------- --%>
			<%-- tab area container SECONDARY --%>
			<%-- ---------------------------- --%>
			<table width="100%" class="secondarySectionHeader" border="0" cellspacing="0" cellpadding="0">
		 		<tr height="18">
					<td class="text14WhiteBold">
		 				<b>&nbsp;&nbsp;&nbsp;&nbsp;Supplerende oplysninger&nbsp;<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
					</td>
				</tr>
			</table>
			</td>
		</tr>
		
		<%--EXTRAORDINÄRA --%>
		<tr>
			<td colspan="2" >
			<table width="100%" class="secondarySectionFrame" border="0" cellspacing="0" cellpadding="0">
				<tr height="10"><td colspan="2"></td></tr>
				<tr>
				<td width="50%" valign="top">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
			 			<td width="5">&nbsp;</td>
			            <td >		
			 				<%-- ANSVARIG --%>
			 				<table width="100%" align="left" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
						 		<tr height="18px">
						 			<td class="text12White">
						 				&nbsp;
						 				<img onMouseOver="showPop('14_info');" onMouseOut="hidePop('14_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 						
				 						<b>&nbsp;50.</b><font class="text16RedBold" >*</font>Hovedforpligtede&nbsp;<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
				 						
						 				<div class="text11" style="position: relative;" align="left">
										<span style="position:absolute;top:2px; width:250px;" id="14_info" class="popupWithInputText text11"  >
						           			Hovedforpligtede. 
						           			<ul>
							           			<li>The principal’s name (full name of the person or company) and full address shall be
													entered as well as the identification number, if any, allocated by the competent
													authorities. 
													<br/><br/>If appropriate, the full name (person or company) of the authorised
													representative who signs on behalf of the principal shall be entered.
													Subject to any specific provisions on the use of computerised systems, the original of
													the handwritten signature of the person concerned must appear on the SAD copy no.1,
													which is to be kept at the office of departure. If this is a legal person, the signatory
													shall add after his signature his full name and the capacity in which he is signing.
							           			</li>
							           		</ul>
										</span>
										</div>
					 				</td>
				 				</tr>
			 				</table>
			 			</td>
			 		</tr>
			 		<tr>	
			 			<td width="5">&nbsp;</td>
			            <td >	
							<%-- create record --%>
						 	<table width="100%" align="left" class="formFrame" border="0" cellspacing="0" cellpadding="0">
						 		<tr>
							 		<td>
								 		<table width="100%" border="0" cellspacing="0" cellpadding="0">
									 		<tr height="15">
									            <td class="text12" align="left">&nbsp;</td> 
									        </tr>
									        <tr>
									        	<%-- ================================================================================== --%>
									        	<%-- This hidden values are used when an AJAX event from within a dialog box is fired.  
									        		 These original values will be used when the user clicks "Cancel" buttons (puttting
									        		 back original value)																--%> 
									        	<%-- ================================================================================== --%>
									        	<input type="hidden" name="orig_sveh_dkkn" id="orig_sveh_dkkn" value='${dkkn}'>
									        	<input type="hidden" name="orig_thnaa" id="orig_thnaa" value='${model.record.thnaa}'>
									        	<input type="hidden" name="orig_thtina" id="orig_thtina" value='${model.record.thtina}'>
									        	<input type="hidden" name="orig_thada1" id="orig_thada1" value='${model.record.thada1}'>
									        	<input type="hidden" name="orig_thpna" id="orig_thpna" value='${model.record.thpna}'>
									        	<input type="hidden" name="orig_thpsa" id="orig_thpsa" value='${model.record.thpsa}'>
									        	<input type="hidden" name="orig_thlka" id="orig_thlka" value='${model.record.thlka}'>
									        	<input type="hidden" name="orig_thska" id="orig_thska" value='${model.record.thska}'>
							        				<td class="text12" align="left" >&nbsp;&nbsp;<font class="text16RedBold" >*</font><span title="thtina">CVR/SE-nr</span></td>
									            <td class="text12" align="left" >&nbsp;&nbsp;<font class="text16RedBold" >*</font><span title="thnaa">Navn</span>
									            	<a tabindex="-1" id="thnaaIdLink">
													<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
													</a>
									            </td>
									            
									        </tr>
									        <tr>
									        		<td align="left"><input type="text" class="inputTextMediumBlueMandatoryField" name="thtina" id="thtina" size="20" maxlength="17" value="${model.record.thtina}"></td>
									            <td align="left"><input type="text" class="inputTextMediumBlueMandatoryField" name="thnaa" id="thnaa" size="30" maxlength="35" value="${model.record.thnaa}"></td>
									            
									        </tr>

									        <tr height="4"><td>&nbsp;</td></tr>
									        <tr>
									            <td class="text12" align="left" >&nbsp;&nbsp;<span title="thada1">Adresse</span></td>
									            <td class="text12" align="left" >&nbsp;&nbsp;<span title="thska">Sprogkode</span>
								            		
									            </td>
									        </tr>
									        <tr>
									            <td align="left"><input type="text" class="inputTextMediumBlue" name="thada1" id="thada1" size="30" maxlength="35" value="${model.record.thada1}"></td>
									            <td class="text12" align="left" >
									            		&nbsp;<select name="thska" id="thska">
										            		<option value="">-vælg-</option>
										 				  	<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
						                                	 	<option value="${code.tkkode}"<c:if test="${model.record.thska == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
															</c:forEach> 
													</select>
													<a tabindex="-1" id="thskaIdLink"  OnClick="triggerChildWindowLanguageCodes(this)">
														<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
													</a>
									            
												</td>
									        </tr>
									        <tr>
									        		<td>
										        		<table>
										        		<tr>
										            		<td class="text12" align="left" >&nbsp;&nbsp;<span title="thpsa">By</span></td>
										            		<td align="left">&nbsp;</td>
										            	</tr>
										        		<tr>
										            		<td align="left">
										       				<input type="text" class="inputTextMediumBlue" name="thpsa" id="thpsa" size="30" maxlength="35" value="${model.record.thpsa}">
									            			</td> 
										            		<td align="left">&nbsp;</td>
										        		</tr>    	
										            	</table>
									            </td>
									            <td >
										            	<table>
										        		<tr>
										        			<td class="text12" align="left" >&nbsp;&nbsp;<span title="thpna">Postnummer</span></td>
										            		<td class="text12" align="left" >&nbsp;<font class="text16RedBold" >*</font><span title="thlka">Land</span>
										            		
										            		</td>
										            	</tr>
										        		<tr >
										        			<td align="left"><input type="text" class="inputTextMediumBlue" name="thpna" id="thpna" size="10" maxlength="9" value="${model.record.thpna}"></td> 
										            		<td align="left">
										            			<select class="inputTextMediumBlueMandatoryField" name="thlka" id="thlka">
												            		<option value="">-vælg-</option>
											 				  	<c:forEach var="country" items="${model.countryCodeList}" >
							                                	 	<option value="${country.dkkd_kd}"<c:if test="${model.record.thlka == country.dkkd_kd}"> selected </c:if> >${country.dkkd_kd}</option>
																</c:forEach> 
															</select>
															<a tabindex="-1" id="thlkaIdLink"  OnClick="triggerChildWindowCountryCodes(this)">
																<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
															</a>
										            		</td> 
										        		</tr>  
										            	</table>
									            </td>
								            	</tr>
									        
									        <tr height="15">
							            		<td class="text12Bold" align="left" >&nbsp;</td>
							            		<td class="text12Bold" align="left" >&nbsp;</td> 
									        </tr>  
									        
								        </table>
							        </td>
						        </tr>
							</table>          
		            		</td>
		           	</tr> 
		           	<tr height="20"><td></td></tr>
		           	</table>
				</td>
				<%-- --------------- --%>
				<%-- RIGHT SIDE CELL --%>
				<%-- --------------- --%>
				<td width="50%" align="center" valign="top">
					<table border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="2">&nbsp;</td>
							<td valign="top">
					 			<table border="0" cellspacing="0" cellpadding="0">
			                		<tr>
						 			<td class="text12" >
						 				<table align="left" border="0" cellspacing="2" cellpadding="0">
						 					<tr>
									            <td class="text12" align="left">
									            <img onMouseOver="showPop('forsegling_info');" onMouseOut="hidePop('forsegling_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 								&nbsp;<span title="thdfkd">Forsegling Id&nbsp;</span>
												<div class="text11" style="position: relative;" align="left">
												<span style="position:absolute;top:2px; width:250px;" id="forsegling_info" class="popupWithInputText text11"  >								           			<br>
								           				Skal indtaste alle værdier (Antal/kode/sprog) eller ingen.
								           			<br/>
												</span>
												</div>
												</td>
									           	<td class="text12" align="left">
									           		<input type="text" class="inputTextMediumBlue" name="thdfkd" id="thdfkd" size="20" maxlength="20" value="${model.record.thdfkd}">
									           	</td>
								           	</tr>
								           	<tr>
									            <td class="text12" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									            		<span title="thdant">Forseglingsantal&nbsp;</span>
									            </td>
				 								<td class="text12" align="left">
									           		<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="thdant" id="thdant" size="4" maxlength="4" value="${model.record.thdant}">
									           	</td>
								           	</tr>
								           	<tr>
									            <td class="text12" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									            		<span title="thdfsk">Forseglingssprogkode</span>
								            		</td>
				 								<td class="text12" align="left">
									           		<select name="thdfsk" id="thdfsk">
							            				<option value="">-vælg-</option>
							 				  			<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
				                               	 			<option value="${code.tkkode}"<c:if test="${model.record.thdfsk == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
														</c:forEach> 
													</select>
									           		&nbsp;
									           	</td>
								           	</tr>
											<tr height="20"><td></td></tr>						 				
							 				<tr>
									            <td class="text12" align="left">
									            <img onMouseOver="showPop('kontrolltullplats_info');" onMouseOut="hidePop('kontrolltullplats_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 								&nbsp;<span title="thdats/thddtk">Kontroll toldsted og dato&nbsp;</span>
				 								<div class="text11" style="position: relative;" align="left">
												<span style="position:absolute;top:2px; width:250px;" id="kontrolltullplats_info" class="popupWithInputText text11"  >

								           			<ul>
									           			<li>?</li>
									           		</ul>
												</span>
												</div>
												</td>
									           	<td class="text12" align="left">
									           		<input readonly type="text" class="inputTextReadOnly" name="thdats" id="thdats" size="20" maxlength="8" value="${model.record.thdats}">
									           	</td>
									           	<td class="text12" align="left">	
									           		<input readonly type="text" class="inputTextReadOnly" name="thddtk" id="thddtk" size="20" maxlength="8" value="${model.record.thddtk}">
									           		&nbsp;
									           	</td>
								           	</tr>
							 				<tr>
								 				<td nowrap class="text12">
								 				<img onMouseOver="showPop('returadress_info');" onMouseOut="hidePop('returadress_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
									 			&nbsp;<span title="thtsn1/thtsn2/thtslk">Returadresse&nbsp;</span>
								 				<div class="text11" style="position: relative;" align="left">
												<span style="position:absolute;top:2px; width:250px;" id="returadress_info" class="popupWithInputText text11"  >
									           			-pending...
												</span>
												</div>
												</td>
						 			
								 				<td class="text12">
								 					<input readonly type="text" class="inputTextReadOnly" name="thtsn1" id="thtsn1" size="20" maxlength="35" value="${model.record.thtsn1}">
								 				</td>
								 				<td class="text12">
								 					<input readonly type="text" class="inputTextReadOnly" name="thtsn2" id="thtsn2" size="20" maxlength="35" value="${model.record.thtsn2}">
								 				</td>
							 				</tr>
							 				<tr>
								 				<td nowrap class="text12">&nbsp;</td>
					 							<td class="text12">
								 					<input readonly type="text" class="inputTextMediumBlue" name="thtslk" id="thtslk" size="2" maxlength="2" value="${model.record.thtslk}">
								 				</td>
							 				</tr>
						 				</table>
						 			</td>
						 		</tr>
						 		</table>
							</td>
						</tr>
					</table>
				 </td>
			</tr>
			<tr>
				<td colspan="2">&nbsp;&nbsp;
		            	<table width="99%" align="center" class="tableBorderWithRoundCornersDarkRed" border="0" cellspacing="2" cellpadding="0">
		            		<tr>
							<td width="50%" align="left" valign="top" class="text12" align="left" >
								<table width="99%" align="left" border="0" cellspacing="0" cellpadding="0">
							 		<tr height="18px">
							 			<td class="text14">
							 				&nbsp;<img onMouseOver="showPop('sikkerhed_info');" onMouseOut="hidePop('sikkerhed_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
									        	<b>Sikkerhed</b>&nbsp;
									        <div class="text11" style="position: relative;" align="left">
												<span style="position:absolute;top:2px; width:250px;" id="sikkerhed_info" class="popupWithInputText text11"  >
													TODO
												</span>
											</div>
						 				</td>
					 				</tr>
					 				<tr>
					 					
					            			<td class="text12">&nbsp;<span title="thsik">
					            				<img onMouseOver="showPop('sikkerhedIndicator_info');" onMouseOut="hidePop('sikkerhedIndicator_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					            				Sikkerhed - Indikator</span>&nbsp;
					            				
											
					            				<select name="thsik" id="thsik" >
							 				  <option value=""<c:if test="${model.record.thsik == '0' || empty model.record.thsik }"> selected </c:if> >0</option>
											  <option value="1"<c:if test="${model.record.thsik == '1'}"> selected </c:if> >1</option>
											</select>
											&nbsp;&nbsp;<span title="thdta">Ank.dato</span>&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="thdta" id="thdta" size="9" maxlength="8" value="${model.record.thdta}">
											&nbsp;<span title="thtma">Tid</span>&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="thtma" id="thtma" size="5" maxlength="4" value="${model.record.thtma}">
											
											<div class="text11" style="position: relative;" align="left">
											<span style="position:absolute;top:2px; width:250px;" id="sikkerhedIndicator_info" class="popupWithInputText text11"  >
												Indikator for specifik omstændighed (0 eller 1)	
											</span>
											</div>	
										</td>
					            		</tr>
					            		<tr>
					            			<td class="text12">&nbsp;<span title="thkref">Kommersielt ref.nr</span></td>
					            		</tr>
					            		<tr>
					            			<td><input type="text" class="inputTextMediumBlue" name="thkref" id="thkref" size="70" maxlength="70" value="${model.record.thkref}"></td>
					            		</tr>
					            		<tr>
					            			<td class="text12">&nbsp;<span title="thtref">Transportør ref.nr</span></td>
					            		</tr>
					            		<tr>
										<td><input type="text" class="inputTextMediumBlue" name="thtref" id="thtref" size="70" maxlength="35" value="${model.record.thtref}"></td>
					            		</tr>
					        		</table>
					        </td>
				            <td width="50%" align="left" valign="top" class="text12" align="left" >   
				            		<table width="99%" align="left" border="0" cellspacing="0" cellpadding="0">
				            			<tr height="18px">
					            			<td class="text12">&nbsp;</td>
					            		</tr>
		            					<tr>
					            			<td class="text12">
					            				<img onMouseOver="showPop('spesOmstand_info');" onMouseOut="hidePop('spesOmstand_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					            				<span title="thspom">Spes.omstændighed</span>&nbsp;
					            				<select name="thspom" id="thspom">
					            				<option value="">-vælg-</option>
					 				  			<c:forEach var="code" items="${model.ncts096_SpecOmst_CodeList}" >
	                             	 				<option value="${code.tkkode}"<c:if test="${model.record.thspom == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
												</c:forEach> 
											</select>
											<div class="text11" style="position: relative;" align="left">
											<span style="position:absolute;top:2px; width:250px;" id="spesOmstand_info" class="popupWithInputText text11"  >
								           			<ul>
								           				<c:forEach var="code" items="${model.ncts096_SpecOmst_CodeList}" >
									 				  		<li><b>${code.tkkode}</b>&nbsp;${code.tktxtn}</li>
								 				  		</c:forEach>	
								           			</ul>
											</span>
											</div>
				            				</td>
					            			<td class="text12">
					            				<img onMouseOver="showPop('betalmade_info');" onMouseOut="hidePop('betalmade_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					            				<span title="thtkbm">Transp.kost/Betal.måde</span>&nbsp;
					            				<select name="thtkbm" id="thtkbm">
					            				<option value="">-vælg-</option>
					 				  			<c:forEach var="code" items="${model.ncts116_BetalningTransport_CodeList}" >
	                             	 				<option value="${code.tkkode}"<c:if test="${model.record.thtkbm == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
												</c:forEach> 
											</select>
											<div class="text11" style="position: relative;" align="left">
											<span style="position:absolute;top:2px; width:250px;" id="betalmade_info" class="popupWithInputText text11"  >
								           			<ul>
								           				<c:forEach var="code" items="${model.ncts116_BetalningTransport_CodeList}" >
									 				  		<li><b>${code.tkkode}</b>&nbsp;${code.tktxtn}</li>
								 				  		</c:forEach>	
								           			</ul>
											</span>
											</div>
					            			</td>
					            		</tr>
					            		<tr>
					            			<td class="text12">&nbsp;&nbsp;<span title="thlosd">Lossested</span>
					            				<input type="text" class="inputTextMediumBlue" name="thlosd" id="thlosd" size="30" maxlength="35" value="${model.record.thlosd}">
					            			</td>
					            			<td class="text12">&nbsp;&nbsp;<span title="thlosdsk">Lossested sprogkode</span>
					            				<select name="thlosdsk" id="thlosdsk">
					            				<option value="">-vælg-</option>
					 				  			<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
				                             	 			<option value="${code.tkkode}"<c:if test="${model.record.thlosdsk == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
												</c:forEach> 
											</select>
											<a tabindex="-1" id="thlosdskIdLink"  OnClick="triggerChildWindowLanguageCodes(this)">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
											</a>
					            			</td>
					            		</tr>
					            		<tr>
					            			<td class="text12">&nbsp;
					            				<table>
					            				<tr>
					            					<td colspan="4" class="text12">&nbsp;
					            					<span title="thlkr1-8">Landkode for rejseplan</span>
					            					<a tabindex="-1" id="thlkr1-8"  OnClick="triggerChildWindowCountryCodes(this)">
														<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
													</a>
					            					</td>
					            				</tr>
					            				<tr>
						            				<td>
						            				<select name="thlkr1" id="thlkr1">
									            		<option value="">-vælg-</option>
								 				  	<c:forEach var="country" items="${model.countryCodeList}" >
				                                	 	<option value="${country.dkkd_kd}"<c:if test="${model.record.thlkr1 == country.dkkd_kd}"> selected </c:if> >${country.dkkd_kd}</option>
													</c:forEach> 
												</select>
												</td>
												<td>
						            				<select name="thlkr2" id="thlkr2">
									            		<option value="">-vælg-</option>
								 				  	<c:forEach var="country" items="${model.countryCodeList}" >
				                                	 	<option value="${country.dkkd_kd}"<c:if test="${model.record.thlkr2 == country.dkkd_kd}"> selected </c:if> >${country.dkkd_kd}</option>
													</c:forEach> 
												</select>
												</td>
												<td>
						            				<select name="thlkr3" id="thlkr3">
									            		<option value="">-vælg-</option>
								 				  	<c:forEach var="country" items="${model.countryCodeList}" >
				                                	 	<option value="${country.dkkd_kd}"<c:if test="${model.record.thlkr3 == country.dkkd_kd}"> selected </c:if> >${country.dkkd_kd}</option>
													</c:forEach> 
												</select>
												</td>
												<td>
												<select name="thlkr4" id="thlkr4">
									            		<option value="">-vælg-</option>
								 				  	<c:forEach var="country" items="${model.countryCodeList}" >
				                                	 	<option value="${country.dkkd_kd}"<c:if test="${model.record.thlkr4 == country.dkkd_kd}"> selected </c:if> >${country.dkkd_kd}</option>
													</c:forEach> 
												</select>
						            				</td>
					            				</tr>
					            				<tr>	
						            				<td>
						            				<select name="thlkr5" id="thlkr5">
									            		<option value="">-vælg-</option>
								 				  	<c:forEach var="country" items="${model.countryCodeList}" >
				                                	 	<option value="${country.dkkd_kd}"<c:if test="${model.record.thlkr5 == country.dkkd_kd}"> selected </c:if> >${country.dkkd_kd}</option>
													</c:forEach> 
												</select>
						            				</td>
						            				<td>
						            				<select name="thlkr6" id="thlkr6">
									            		<option value="">-vælg-</option>
								 				  	<c:forEach var="country" items="${model.countryCodeList}" >
				                                	 	<option value="${country.dkkd_kd}"<c:if test="${model.record.thlkr6 == country.dkkd_kd}"> selected </c:if> >${country.dkkd_kd}</option>
													</c:forEach> 
												</select>
						            				</td>
						            				<td>
						            				<select name="thlkr7" id="thlkr7">
									            		<option value="">-vælg-</option>
								 				  	<c:forEach var="country" items="${model.countryCodeList}" >
				                                	 	<option value="${country.dkkd_kd}"<c:if test="${model.record.thlkr7 == country.dkkd_kd}"> selected </c:if> >${country.dkkd_kd}</option>
													</c:forEach> 
												</select>
												</td>
												<td>
						            				<select name="thlkr8" id="thlkr8">
									            		<option value="">-vælg-</option>
								 				  	<c:forEach var="country" items="${model.countryCodeList}" >
				                                	 	<option value="${country.dkkd_kd}"<c:if test="${model.record.thlkr8 == country.dkkd_kd}"> selected </c:if> >${country.dkkd_kd}</option>
													</c:forEach> 
												</select>
												</td>
											</tr>
											</table>
					            			</td>
					            		</tr>
			 					</table>
							</td>
						</tr>
		            		<tr height="3"><td>&nbsp;</td></tr>
				        	<tr>
				        		<td width="50%" align="center" valign="top">
								<table width="99%" align="center" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
							 		<tr height="18px">
							 			<td class="text12White">
							 				&nbsp;
					 						&nbsp;Afsender - Sikkerhed&nbsp;<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
						 				</td>
					 				</tr>
			 					</table>
			 					<table width="99%" align="center" class="formFrame" border="0" cellspacing="0" cellpadding="0">
							 		<tr>	
							 			<td width="5">&nbsp;</td>
							            <td >	
											<%-- create record --%>
										 	<table width="100%" align="left" class="formFrame" border="0" cellspacing="0" cellpadding="0">
										 		<tr>
											 		<td>
												 		<table width="100%" border="0" cellspacing="0" cellpadding="0">
											 				<%-- ================================================================================== --%>
													        	<%-- This hidden values are used when an AJAX event from within a dialog box is fired.  
													        		 These original values will be used when the user clicks "Cancel" buttons (puttting
													        		 back original value)																--%> 
													        	<%-- ================================================================================== --%>
													        	<input type="hidden" name="orig_thknss" id="orig_thknss" value='${model.record.thknss}'>
													        	<input type="hidden" name="orig_thnass" id="orig_thnass" value='${model.record.thnass}'>
													        	<input type="hidden" name="orig_thtinss" id="orig_thtinss" value='${model.record.thtinss}'>
													        	<input type="hidden" name="orig_thadss1" id="orig_thadss1" value='${model.record.thadss1}'>
													        	<input type="hidden" name="orig_thpnss" id="orig_thpnss" value='${model.record.thpnss}'>
													        	<input type="hidden" name="orig_thpsss" id="orig_thpsss" value='${model.record.thpsss}'>
													        	<input type="hidden" name="orig_thlkss" id="orig_thlkss" value='${model.record.thlkss}'>
													        	<input type="hidden" name="orig_thskss" id="orig_thskss" value='${model.record.thskss}'>
													        	
													        	<tr height="10">
													            <td class="text12" align="left">&nbsp;</td> 
													        </tr>
													 		<tr height="15">
													            <td class="text12" align="left" >&nbsp;&nbsp;<span title="thknss">Kundenummer</span></td>
													            <td class="text12" align="left" >&nbsp;&nbsp;<span title="thnass">Navn</span>
													            	<a tabindex="-1" id="thnassIdLink">
																	<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
																	</a>
											            		</td>
													        </tr>
													        <tr>
													        		<td align="left"><input type="text" class="inputTextMediumBlue" name="thknss" id="thknss" size="10" maxlength="8" value="${model.record.thknss}"></td>
													            <td align="left"><input type="text" class="inputTextMediumBlue" name="thnass" id="thnass" size="30" maxlength="35" value="${model.record.thnass}"></td>
													        </tr>
													        <tr>
											        				<td class="text12" align="left" >&nbsp;&nbsp;<span title="thtinss">CVR/SE-nr</span></td>
													        </tr>
													        <tr>
													        		<td align="left"><input type="text" class="inputTextMediumBlue" name="thtinss" id="thtinss" size="20" maxlength="17" value="${model.record.thtinss}"></td>
													        </tr>
				
													        <tr height="4"><td>&nbsp;</td></tr>
													        <tr>
													            <td class="text12" align="left" >&nbsp;&nbsp;<span title="thadss1">Adresse</span></td>
													            <td class="text12" align="left" >&nbsp;&nbsp;<span title="thskss">Sprogkode</span>
												            		
													            </td>
													        </tr>
													        <tr>
													            <td align="left"><input type="text" class="inputTextMediumBlue" name="thadss1" id="thadss1" size="30" maxlength="30" value="${model.record.thadss1}"></td>
													            <td class="text12" align="left" >
													            		&nbsp;<select name="thskss" id="thskss">
														            		<option value="">-vælg-</option>
														 				  	<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
										                                	 	<option value="${code.tkkode}"<c:if test="${model.record.thskss == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
																			</c:forEach> 
																	</select>
																	<a tabindex="-1" id="thskssIdLink" OnClick="triggerChildWindowLanguageCodes(this)">
																		<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
																	</a>
													            
																</td>
													        </tr>
													        <tr>
													        		<td>
														        		<table>
														        		<tr>
														            		<td class="text12" align="left" >&nbsp;&nbsp;<span title="thpsss">By</span></td>
														            		<td align="left">&nbsp;</td>
														            	</tr>
														        		<tr>
														            		<td align="left">
														       				<input type="text" class="inputTextMediumBlue" name="thpsss" id="thpsss" size="30" maxlength="24" value="${model.record.thpsss}">
													            			</td> 
														            		<td align="left">&nbsp;</td>
														        		</tr>    	
														            	</table>
													            </td>
													            <td >
														            	<table>
														        		<tr>
														        			<td class="text12" align="left" >&nbsp;&nbsp;<span title="thpnss">Postnummer</span></td>
														            		<td class="text12" align="left" >&nbsp;<span title="thlkss">Land</span>
														            		
														            		</td>
														            	</tr>
														        		<tr >
														        			<td align="left"><input type="text" class="inputTextMediumBlue" name="thpnss" id="thpnss" size="10" maxlength="8" value="${model.record.thpnss}"></td> 
														            		<td align="left">
														            			<select name="thlkss" id="thlkss">
																            		<option value="">-vælg-</option>
															 				  	<c:forEach var="country" items="${model.countryCodeList}" >
											                                	 	<option value="${country.dkkd_kd}"<c:if test="${model.record.thlkss == country.dkkd_kd}"> selected </c:if> >${country.dkkd_kd}</option>
																				</c:forEach> 
																			</select>
																			<a id="thlkssIdLink" OnClick="triggerChildWindowCountryCodes(this)">
														            			<img style="cursor:pointer;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
														            		</a>
														            		</td> 
														        		</tr>  
														            	</table>
													            </td>
												            	</tr>
													        
													        <tr height="15">
											            		<td class="text12Bold" align="left" >&nbsp;</td>
											            		<td class="text12Bold" align="left" >&nbsp;</td> 
													        </tr>  
													        
												        </table>
											        </td>
										        </tr>
											</table>          
						            		</td>
						           	</tr> 
		 						</table>
			 					<table width="99%" align="center" border="0" cellspacing="0" cellpadding="0">
							 		<tr height="5"><td>&nbsp;</td></tr>
			 					</table>	
								<table width="99%" align="center" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
							 		<tr height="18px">
							 			<td class="text12White">
							 				&nbsp;
					 						&nbsp;Transportør - Sikkerhed&nbsp;<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
						 				</td>
					 				</tr>
			 					</table>
			 					<table width="99%" align="center" class="formFrame" border="0" cellspacing="0" cellpadding="0">
							 		<tr>	
							 			<td width="5">&nbsp;</td>
							            <td >	
											<%-- create record --%>
										 	<table width="100%" align="left" class="formFrame" border="0" cellspacing="0" cellpadding="0">
										 		<tr>
											 		<td>
												 		<table width="100%" border="0" cellspacing="0" cellpadding="0">
											 				<%-- ================================================================================== --%>
													        	<%-- This hidden values are used when an AJAX event from within a dialog box is fired.  
													        		 These original values will be used when the user clicks "Cancel" buttons (puttting
													        		 back original value)																--%> 
													        	<%-- ================================================================================== --%>
													        	<input type="hidden" name="orig_thknt" id="orig_thknt" value='${model.record.thknt}'>
													        	<input type="hidden" name="orig_thnat" id="orig_thnat" value='${model.record.thnat}'>
													        	<input type="hidden" name="orig_thtint" id="orig_thtint" value='${model.record.thtint}'>
													        	<input type="hidden" name="orig_thadt1" id="orig_thadt1" value='${model.record.thadt1}'>
													        	<input type="hidden" name="orig_thpnt" id="orig_thpnt" value='${model.record.thpnt}'>
													        	<input type="hidden" name="orig_thpst" id="orig_thpst" value='${model.record.thpst}'>
													        	<input type="hidden" name="orig_thlkt" id="orig_thlkt" value='${model.record.thlkt}'>
													        	<input type="hidden" name="orig_thskt" id="orig_thskt" value='${model.record.thskt}'>
													        	
													        	<tr height="10">
													            <td class="text12" align="left">&nbsp;</td> 
													        </tr>
													 		<tr height="15">
													            <td class="text12" align="left" >&nbsp;&nbsp;<span title="thknt">Kundenummer</span></td>
													            <td class="text12" align="left" >&nbsp;&nbsp;<span title="thnat">Navn</span>
													            	<a tabindex="-1" id="thnatIdLink">
																	<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
																	</a>
											            		</td>
													        </tr>
													        <tr>
													        		<td align="left"><input type="text" class="inputTextMediumBlue" name="thknt" id="thknt" size="10" maxlength="8" value="${model.record.thknt}"></td>
													            <td align="left"><input type="text" class="inputTextMediumBlue" name="thnat" id="thnat" size="30" maxlength="35" value="${model.record.thnat}"></td>
													        </tr>
													        <tr>
											        				<td class="text12" align="left" >&nbsp;&nbsp;<span title="thtint">CVR/SE-nr</span></td>
													        </tr>
													        <tr>
													        		<td align="left"><input type="text" class="inputTextMediumBlue" name="thtint" id="thtint" size="20" maxlength="17" value="${model.record.thtint}"></td>
													        </tr>
				
													        <tr height="4"><td>&nbsp;</td></tr>
													        <tr>
													            <td class="text12" align="left" >&nbsp;&nbsp;<span title="thadt1">Adresse</span></td>
													            <td class="text12" align="left" >&nbsp;&nbsp;<span title="thskt">Sprogkode</span>
												            		
													            
													            </td>
													        </tr>
													        <tr>
													            <td align="left"><input type="text" class="inputTextMediumBlue" name="thadt1" id="thadt1" size="30" maxlength="30" value="${model.record.thadt1}"></td>
													            <td class="text12" align="left" >
													            		&nbsp;<select name="thskt" id="thskt">
														            		<option value="">-vælg-</option>
														 				  	<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
										                                	 	<option value="${code.tkkode}"<c:if test="${model.record.thskt == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
																			</c:forEach> 
																	</select>
																	<a tabindex="-1" id="thsktIdLink"  OnClick="triggerChildWindowLanguageCodes(this)">
																		<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
																	</a>
																</td>
													        </tr>
													        <tr>
													        		<td>
														        		<table>
														        		<tr>
														            		<td class="text12" align="left" >&nbsp;&nbsp;<span title="thpst">By</span></td>
														            		<td align="left">&nbsp;</td>
														            	</tr>
														        		<tr>
														            		<td align="left">
														       				<input type="text" class="inputTextMediumBlue" name="thpst" id="thpst" size="30" maxlength="24" value="${model.record.thpst}">
													            			</td> 
														            		<td align="left">&nbsp;</td>
														        		</tr>    	
														            	</table>
													            </td>
													            <td >
														            	<table>
														        		<tr>
														        			<td class="text12" align="left" >&nbsp;&nbsp;<span title="thpnt">Postnummer</span></td>
														            		<td class="text12" align="left" >&nbsp;<span title="thlkt">Land</span>
														            		
														            		</td>
														            	</tr>
														        		<tr >
														        			<td align="left"><input type="text" class="inputTextMediumBlue" name="thpnt" id="thpnt" size="10" maxlength="8" value="${model.record.thpnt}"></td> 
														            		<td align="left">
														            			<select name="thlkt" id="thlkt">
																            		<option value="">-vælg-</option>
															 				  	<c:forEach var="country" items="${model.countryCodeList}" >
											                                	 	<option value="${country.dkkd_kd}"<c:if test="${model.record.thlkt == country.dkkd_kd}"> selected </c:if> >${country.dkkd_kd}</option>
																				</c:forEach> 
																			</select>
																			<a id="thlktIdLink" OnClick="triggerChildWindowCountryCodes(this)">
															            			<img style="cursor:pointer;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
														            		</a>
														            		</td> 
														        		</tr>  
														            	</table>
													            </td>
												            	</tr>
													        
													        <tr height="15">
											            		<td class="text12Bold" align="left" >&nbsp;</td>
											            		<td class="text12Bold" align="left" >&nbsp;</td> 
													        </tr>  
													        
												        </table>
											        </td>
										        </tr>
											</table>          
						            		</td>
						           	</tr> 
		 						</table>
			        			</td>
			        			<td width="50%" align="center" valign="top">
								<table width="99%" align="center" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
							 		<tr height="18px">
							 			<td class="text12White">
							 				&nbsp;
					 						&nbsp;Modtager - Sikkerhed&nbsp;<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
						 				</td>
					 				</tr>
			 					</table>
			 					<table width="99%" align="center" class="formFrame" border="0" cellspacing="0" cellpadding="0">
							 		<tr>	
							 			<td width="5">&nbsp;</td>
							            <td >	
											<%-- create record --%>
										 	<table width="100%" align="left" class="formFrame" border="0" cellspacing="0" cellpadding="0">
										 		<tr>
											 		<td>
												 		<table width="100%" border="0" cellspacing="0" cellpadding="0">
													 		<%-- ================================================================================== --%>
													        	<%-- This hidden values are used when an AJAX event from within a dialog box is fired.  
													        		 These original values will be used when the user clicks "Cancel" buttons (puttting
													        		 back original value)																--%> 
													        	<%-- ================================================================================== --%>
													        	<input type="hidden" name="orig_thknks" id="orig_thknks" value='${model.record.thknks}'>
													        	<input type="hidden" name="orig_thnaks" id="orig_thnaks" value='${model.record.thnaks}'>
													        	<input type="hidden" name="orig_thtinks" id="orig_thtinks" value='${model.record.thtinks}'>
													        	<input type="hidden" name="orig_thadks1" id="orig_thadks1" value='${model.record.thadks1}'>
													        	<input type="hidden" name="orig_thpnks" id="orig_thpnks" value='${model.record.thpnks}'>
													        	<input type="hidden" name="orig_thpsks" id="orig_thpsks" value='${model.record.thpsks}'>
													        	<input type="hidden" name="orig_thlkks" id="orig_thlkks" value='${model.record.thlkks}'>
													        	<input type="hidden" name="orig_thskks" id="orig_thskks" value='${model.record.thskks}'>
													 		<tr height="10">
													            <td class="text12" align="left">&nbsp;</td> 
													        </tr>
													        <tr height="15">
													            <td class="text12" align="left" >&nbsp;&nbsp;<span title="thknks">Kundenummer</span></td>
													            <td class="text12" align="left" >&nbsp;&nbsp;<span title="thnaks">Navn</span>
													            	<a tabindex="-1" id="thnaksIdLink">
																	<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
																	</a>	
													            </td>
													        </tr>
													        <tr>
													        		<td align="left"><input type="text" class="inputTextMediumBlue" name="thknks" id="thknks" size="10" maxlength="8" value="${model.record.thknks}"></td>
													            <td align="left"><input type="text" class="inputTextMediumBlue" name="thnaks" id="thnaks" size="30" maxlength="35" value="${model.record.thnaks}"></td>
													        </tr>
													        <tr>
											        				<td class="text12" align="left" >&nbsp;&nbsp;<span title="thtinks">EORI</span></td>
													        </tr>
													        <tr>
													        		<td align="left"><input type="text" class="inputTextMediumBlue" name="thtinks" id="thtinks" size="20" maxlength="17" value="${model.record.thtinks}"></td>
													        </tr>
				
													        <tr height="4"><td>&nbsp;</td></tr>
													        <tr>
													            <td class="text12" align="left" >&nbsp;&nbsp;<span title="thadks1">Adresse</span></td>
													            <td class="text12" align="left" >&nbsp;&nbsp;<span title="thskks">Sprogkode</span>
												            		
													            </td>
													        </tr>
													        <tr>
													            <td align="left"><input type="text" class="inputTextMediumBlue" name="thadks1" id="thadks1" size="30" maxlength="30" value="${model.record.thadks1}"></td>
													            <td class="text12" align="left" >
													            		&nbsp;<select name="thskks" id="thskks">
														            		<option value="">-vælg-</option>
														 				  	<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
										                                	 	<option value="${code.tkkode}"<c:if test="${model.record.thskks == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
																			</c:forEach> 
																	</select>
																	<a tabindex="-1" id="thskksIdLink" OnClick="triggerChildWindowLanguageCodes(this)">
																		<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
																	</a>
																</td>
													        </tr>
													        <tr>
													        		<td>
														        		<table>
														        		<tr>
														            		<td class="text12" align="left" >&nbsp;&nbsp;<span title="thpsks">By</span></td>
														            		<td align="left">&nbsp;</td>
														            	</tr>
														        		<tr>
														            		<td align="left">
														       				<input type="text" class="inputTextMediumBlue" name="thpsks" id="thpsks" size="30" maxlength="24" value="${model.record.thpsks}">
													            			</td> 
														            		<td align="left">&nbsp;</td>
														        		</tr>    	
														            	</table>
													            </td>
													            <td >
														            	<table>
														        		<tr>
														        			<td class="text12" align="left" >&nbsp;&nbsp;<span title="thpnks">Postnummer</span></td>
														            		<td class="text12" align="left" >&nbsp;<span title="thlkks">Land</span>
														            		
														            		</td>
														            	</tr>
														        		<tr >
														        			<td align="left"><input type="text" class="inputTextMediumBlue" name="thpnks" id="thpnks" size="10" maxlength="8" value="${model.record.thpnks}"></td> 
														            		<td align="left">
														            			<select name="thlkks" id="thlkks">
																            		<option value="">-vælg-</option>
															 				  	<c:forEach var="country" items="${model.countryCodeList}" >
											                                	 	<option value="${country.dkkd_kd}"<c:if test="${model.record.thlkks == country.dkkd_kd}"> selected </c:if> >${country.dkkd_kd}</option>
																				</c:forEach> 
																			</select>
																			<a id="thlkksIdLink" OnClick="triggerChildWindowCountryCodes(this)">
															            			<img style="cursor:pointer;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
														            		</a>
														            		</td> 
														        		</tr>  
														            	</table>
													            </td>
												            	</tr>
													        
													        <tr height="15">
											            		<td class="text12Bold" align="left" >&nbsp;</td>
											            		<td class="text12Bold" align="left" >&nbsp;</td> 
													        </tr>  
												        </table>
											        </td>
										        </tr>
											</table>          
						            		</td>
						           	</tr> 
		 						</table>
			        			</td>
				        	</tr>
				        	<tr height="10"><td>&nbsp;</td></tr>
		            	</table>
		            </td>  
		            <td>&nbsp;</td>
		        </tr>
		        	<tr height="20"><td>&nbsp;</td></tr>
			</table> <%-- END to the wrapper table for EXTRAORDINARY data --%>	
			</td>			
		</tr>		         
	</table> 
	</form> 
	</td>
 </tr>
 
 
 <tr>
	<td>
		<%-- change status admin dialog --%>	
		<div id="dialogUpdateStatus" title="Dialog">
			<form action="skatnctsexport_updateStatus.do" name="updateStatusForm" id="updateStatusForm" method="post">
			 	<input type="hidden" name="currentAvd" id="currentAvd" value="${model.record.thavd}">
			 	<input type="hidden" name="currentOpd" id="currentOpd" value="${model.record.thtdn}">
			 		
				<p class="text12" >Change status as needed.</p>
				<table>
					<tr>
						<td class="text12" align="left" >&nbsp;Status</td>
						<td class="text12MediumBlue">
							<select name="selectedStatus" id="selectedStatus">
			            			<option value="">-vælg-</option>
			 				  	<c:forEach var="record" items="${model.statusCodeList}" >
		                            <option value="${record.tkkode}">${record.tkkode}</option>
								</c:forEach> 
							</select>
							
						</td>
					</tr>
				</table>
			</form>
		</div>
	</td>
</tr> 

<tr>
		<td>
			<div id="dialogCancellation" title="Dialog">
				<form  action="skatnctsexport_cancellationSkat.do" name="cancellationForm" id="cancellationForm" method="post">
				 	<input type="hidden" name="tkavd" id="tkavd" value='${model.record.thavd}'/>
				 	<input type="hidden" name="tktdn" id="tktdn" value='${model.record.thtdn}'/>
				 		
					<p class="text12" >Annullerings anmodning</p>
					
					<table>
						<tr>
							<td class="text12" align="left" >Årsag</td>
   							<td class="text12" align="left" >Sprogkode</td>
   						</tr>
						<tr>
							<td class="text12MediumBlue">
								<input type="text" class="inputText" id="tkft1" name="tkft1" size="65" maxlength="70" value=''>
							</td>
							<td class="text12MediumBlue">
								<select name="tksk" id="tksk">
				            		<option value="">-vælg-</option>
				 				  	<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
                               	 		<option value="${code.tkkode}"<c:if test="${model.record.thskfd == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
									</c:forEach> 
								</select>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</td>
	</tr>

	