<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerSkat.jsp" />
<!-- =====================end header ==========================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/skatglobal_edit.js?ver=${user.versionEspedsg}"></SCRIPT>	
	<SCRIPT type="text/javascript" src="resources/js/skatnctsimport_edit.js?ver=${user.versionEspedsg}"></SCRIPT>
	
<table width="100%" cellspacing="0" border="0" cellpadding="0">

 <tr>
 <td>	
	<%-- tab container component --%>
	<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
		<tr height="2"><td></td></tr>
		<tr height="25"> 
			<c:choose> 
			    <c:when test="${editActionOnTopic=='doUpdate' or editActionOnTopic=='doFetch'}">
					<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a tabindex=-1 style="display:block;" href="skatnctsimport.do?action=doFind&sign=${model.record.tisg}">
							<font class="tabDisabledLink">&nbsp;<spring:message code="systema.skat.ncts.import.list.tab"/></font>
							<img valign="bottom" src="resources/images/list.gif" border="0" alt="general list">
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			
					<td width="15%" valign="bottom" class="tab" align="center" nowrap>
						<font class="tabLink">
							&nbsp;<spring:message code="systema.skat.ncts.import.created.mastertopic.tab"/>
						</font>
						<font class="text12MediumBlue">[${model.record.titdn}]</font>
						<c:if test="${model.record.tist == 'F' || model.record.tist == 'M' || empty model.record.tist}">
							<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
						</c:if>
						
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a tabindex=-1 style="display:block;" href="skatnctsimport_edit_items.do?action=doFetch&avd=${model.record.tiavd}&sign=${model.record.tisg}
													&opd=${model.record.titdn}&mrnNr=${model.record.titrnr}&godsNr=${model.record.tign}
													&status=${model.record.tist}&datum=${model.record.tidt}">
							<font class="tabDisabledLink">
								&nbsp;<spring:message code="systema.skat.ncts.import.item.createnew.tab"/>
							</font>
							<c:if test="${ model.record.tist == 'F' || model.record.tist == 'M' || empty model.record.tist}">
								<img valign="bottom" src="resources/images/add.png" width="12" hight="12" border="0" alt="create new">
							</c:if>
							
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a tabindex=-1 style="display:block;" href="skatnctsimport_unloading_edit.do?avd=${model.record.tiavd}&sign=${model.record.tisg}
													&opd=${model.record.titdn}&mrnNr=${model.record.titrnr}&godsNr=${model.record.tign}
													&status=${model.record.tist}&datum=${model.record.tidt}">
							<font class="tabDisabledLink">
								&nbsp;<spring:message code="systema.skat.ncts.import.unloading.createnew.tab"/>
							</font>
							<img style="vertical-align: bottom" src="resources/images/unloading.png" width="16" hight="16" border="0" alt="show log">
						</a>
					</td>
					
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a tabindex=-1 style="display:block;" href="skatnctsimport_unloading_edit_items.do?action=doFetch&avd=${model.record.tiavd}&sign=${model.record.tisg}
											&opd=${model.record.titdn}&mrnNr=${model.record.titrnr}&godsNr=${model.record.tign}
											&status=${model.record.tist}&datum=${model.record.tidt}">
							<font class="tabDisabledLink">
								&nbsp;<spring:message code="systema.skat.ncts.import.unloading.item.createnew.tab"/>
							</font>
							<img style="vertical-align: bottom" src="resources/images/add.png" width="12" hight="12" border="0" alt="item lines">
						</a>
					</td>
					
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a tabindex=-1 style="display:block;" href="skatnctsimport_logging.do?avd=${model.record.tiavd}&sign=${model.record.tisg}
											&opd=${model.record.titdn}&mrnNr=${model.record.titrnr}&godsNr=${model.record.tign}
											&status=${model.record.tist}&datum=${model.record.tidt}">
							<font class="tabDisabledLink">
								&nbsp;<spring:message code="systema.skat.ncts.import.logging.tab"/>
							</font>
							<img style="vertical-align: bottom" src="resources/images/log-icon.png" width="16" hight="16" border="0" alt="show log">
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a tabindex=-1 style="display:block;" href="skatnctsimport_archive.do?avd=${model.record.tiavd}&sign=${model.record.tisg}
											&opd=${model.record.titdn}&mrnNr=${model.record.titrnr}&godsNr=${model.record.tign}
											&status=${model.record.tist}&datum=${model.record.tidt}">
							<font class="tabDisabledLink">
								&nbsp;<spring:message code="systema.skat.ncts.import.archive.tab"/>
							</font>
							<img style="vertical-align: bottom" src="resources/images/archive.png" width="16" hight="16" border="0" alt="show archive">
						</a>
					</td>
					<td width="13%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
				</c:when>
				<c:otherwise>
					<td width="20%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a tabindex=-1 style="display:block;" href="skatnctsimport.do?action=doFind&sign=${model.record.tisg}">
							<font class="tabDisabledLink">&nbsp;<spring:message code="systema.skat.ncts.import.list.tab"/></font>
							<img valign="bottom" src="resources/images/list.gif" border="0" alt="general list">
						</a>
					</td>

					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="20%" valign="bottom" class="tab" align="center" nowrap>
						<font class="tabLink">&nbsp;<spring:message code="systema.skat.ncts.import.createnew.tab"/></font>
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
	<form name="nctsImportSaveNewTopicForm" id="nctsImportSaveNewTopicForm" method="post">
	<table width="100%" class="tabThinBorderWhite" border="0" cellspacing="0" cellpadding="0">
 		<tr height="3">
 			<td colspan="2">&nbsp;
		 		<%-- test indicator /per avdelning --%> 
				<c:forEach var="record" items="${avdListSessionTestFlag}" >
					<c:if test="${record.avd == model.record.tiavd}">	
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
			<input type="hidden" name="opd" id="opd" value='${model.record.titdn}'>
			<%-- topic specific --%>
			<input type="hidden" name="tiavd" id="tiavd" value='${model.record.tiavd}'>
			<input type="hidden" name="titdn" id="titdn" value='${model.record.titdn}'>
			<input type="hidden" name="tisg" id="tisg" value='${model.record.tisg}'>
			<input type="hidden" name="tist" id="tist" value='${model.record.tist}'>
			<input type="hidden" name="tidt" id="tidt" value='${model.record.tidt}'>
	    		<input type="hidden" name="avd" id="avd" value='${model.record.tiavd}'>
			<input type="hidden" name="sign" id="sign" value='${model.record.tisg}'>
			
			<tr >
				<td colspan="3" align="left" class="text12MediumBlue">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Afd:&nbsp;${model.record.tiavd}&nbsp;&nbsp;<span title="titdn">Angivelse:</span>&nbsp;<b>${model.record.titdn}</b>
					&nbsp;&nbsp;&nbsp;Gods-nr:&nbsp;<b>${model.record.tign}</b>
				</td>
				<c:if test="${'1' != isTestAvd}">		
					<td align="right" >
						<%--This checkbox appears only in real production. Otherwise use the Testavdelning --%>
						<input type="checkbox" name="dknh_0035" id="dknh_0035" value="1" <c:if test="${model.record.dknh_0035 == '1'}"> checked </c:if>  ><font class="text12MediumBlue"><b>TEST flag</b></font>&nbsp;&nbsp;&nbsp;
					</td>
				</c:if>
			</tr>
			<tr >
				<td align="left" class="text12MediumBlue">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span title="titrnr">MRN-nr:</span>&nbsp;<b>${model.record.titrnr}</b>
				</td>
			</tr>
			<tr height="5"><td></td></tr>
			<tr >
				<td align="left" class="text12MediumBlue">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span title="tisg">Sign:</span>&nbsp;<b>${model.record.tisg}</b>,&nbsp;&nbsp;<span title="tidt">Dato:</span>&nbsp;<b>${model.record.tidt}</b>,
					&nbsp;
					<img onMouseOver="showPop('status_info');" onMouseOut="hidePop('status_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
           			<span title="tist">Stat<a id="updateStatusLink" runat="server" href="#"><font class="text11MediumBlue">u</font></a>s:</span>&nbsp;<b>${model.record.tist}</b>
					&nbsp;&nbsp;
					<font class="text16RedBold" >*</font><span title="tienkl">Type procedure</span>&nbsp;
					<%-- Must be model attribute in order to validate towards the filter (thsg) --%>
           			<select class="text11" name="tienkl" id="tienkl">
	            			<option value="J"<c:if test="${model.record.tienkl == 'J'}"> selected </c:if> >Forenklet</option>
					  	<option value="N"<c:if test="${model.record.tienkl == 'N'}"> selected </c:if> >Normal</option>
					</select>
				
					<div class="text11" style="position: relative;" align="left">
					<span style="position:absolute; left:150; top:2px; width:250px;" id="status_info" class="popupWithInputText text11"  >
			           		<br/>
			           		Kun <b>M</b>, F eller <b>' '</b> kan redigeres.
			           			<ul>
									<c:forEach var="record" items="${model.statusCodeList}" >
					           			<li><b>${record.tkkode}&nbsp;</b>&nbsp;${record.tktxtn}</li>
				           			</c:forEach>
			           			</ul>
						</span>	
					</div>
				</td>                
			</tr>	
			<%-- test indicator /per avdelning --%> 
			<c:if test="${'1' == isTestAvd}">
				<tr>
					<td align="left" class="text14Red" >
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>[ TEST Afdeling ]</b>
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
			<input type="hidden" name="opd" id="opd" value='${model.record.titdn}'>
			<%-- topic specific (syop and tuid) --%>
			<input type="hidden" name="titdn" id="titdn" value='${model.record.titdn}'>
			<input type="hidden" name="tist" id="tist" value='${model.record.tist}'>
			<input type="hidden" name="tidt" id="tidt" value='${model.record.tidt}'>
			
			<tr >
				<td align="left" class="text12MediumBlue">
					&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;<font class="text16RedBold" >*</font><span title="avd(tiavd)">Afd:</span>&nbsp;
					<%-- Must be model attribute in order to validate towards the filter (thavd) --%>
           			<select name="avd" id="avd">
	            		<option value="">-vælg-</option>
	 				  	<c:forEach var="record" items="${model.avdList}" >
                       	 	<option value="${record.avd}"<c:if test="${model.record.tiavd == record.avd}"> selected </c:if> >${record.avd}<c:if test="${record.tst == '1'}">&nbsp;(test)</c:if></option>
						</c:forEach> 
					</select>
					&nbsp;
					<font class="text16RedBold" >*</font><span title="sign(tisg)">Sign:</span>&nbsp;
					<%-- Must be model attribute in order to validate towards the filter (thsg) --%>
           			<select name="sign" id="sign">
	            		<option value="">-vælg-</option>
	 				  	<c:forEach var="record" items="${model.signList}" >
	 				  		<c:choose>
		 				  		<c:when test="${empty model.record.tisg}">
		                        	 	<option value="${record.sign}"<c:if test="${user.skatSign == record.sign}"> selected </c:if> >${record.sign}</option>
		 				  		</c:when>
		 				  		<c:otherwise>
		                        	 	<option value="${record.sign}"<c:if test="${model.record.tisg == record.sign}"> selected </c:if> >${record.sign}</option>
	                        	 	</c:otherwise>
                        	 	</c:choose>
						</c:forEach> 
					</select>
					<font class="text16RedBold" >*</font><span title="tienkl">Type procedure</span>&nbsp;
					<%-- Must be model attribute in order to validate towards the filter (thsg) --%>
           			<select name="tienkl" id="tienkl">
	            			<option value="J"<c:if test="${model.record.tienkl == 'J'}"> selected </c:if> >Forenklet</option>
					  	<option value="N"<c:if test="${model.record.tienkl == 'N'}"> selected </c:if> >Normal</option>
					</select>
				</td>
				<td>&nbsp;</td>
			</tr>	
		</c:otherwise>
		</c:choose>

		<tr height="10"><td colspan="2">&nbsp;</td></tr>
		<%-- --------------- --%>
		<%-- CONTENT --%>
		<%-- --------------- --%>
		<tr>
		<td >
		<table align="center" width="98%" border="0" cellspacing="1" cellpadding="0">
			
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
			
			<%-- Other errors (none validation errors) --%>
			<c:if test="${not empty model.errorMessage}">
			<tr>
				<td colspan="10">
	            	<table align="left" border="0" cellspacing="0" cellpadding="0">
				 		<tr>
				 			<td class="textError">
				 				<ul>
                                    <li>
                                      	Fel vid uppdatering:${model.errorMessage}]  
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
					 			<td class="text12White">&nbsp;&nbsp;Erhverv - Ansvarlig&nbsp;</td>
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
					 			<td class="text12"><input type="text" class="inputTextMediumBlue" name="tikn" id="tikn" size="8" maxlength="8" value="${model.record.tikn}"></td>
					 			<td class="text12">&nbsp;</td>
			 				</tr>
			 				<tr >
								<%-- ================================================================================== --%>
						        	<%-- This hidden values are used when an AJAX event from within a dialog box is fired.  
						        		 These original values will be used when the user clicks "Cancel" buttons (puttting
						        		 back original value)																--%> 
						        	<%-- ================================================================================== --%>
						        	<input type="hidden" name="orig_tikn" id="orig_tikn" value='${model.record.tikn}'>
						        	<input type="hidden" name="orig_tina" id="orig_tina" value='${model.record.tina}'>
						        	<input type="hidden" name="orig_titin" id="orig_titin" value='${model.record.titin}'>
						        	<input type="hidden" name="orig_tiad1" id="orig_tiad1" value='${model.record.tiad1}'>
						        	<input type="hidden" name="orig_tipn" id="orig_tipn" value='${model.record.tipn}'>
						        	<input type="hidden" name="orig_tips" id="orig_tips" value='${model.record.tips}'>
						        	<input type="hidden" name="orig_tilk" id="orig_tilk" value='${model.record.tilk}'>
						        	<input type="hidden" name="orig_tisk" id="orig_tisk" value='${model.record.tisk}'>
			 				
					 			<td class="text12">&nbsp;<font class="text16RedBold" >*</font><span title="titin">TIN</span></td>
					 			<td class="text12">&nbsp;<font class="text16RedBold" >*</font><span title="tina">Navn</span>
						            <img id="imgAnsvarigSearch" style="cursor:pointer;" src="resources/images/find.png" border="0" alt="search" onClick="showPop('searchCustomerDialog10');">
						            	<%-- ======================================================== --%>
						            	<%-- Here we have the search Ansvarig [Customer] popup window --%>
						            	<%-- ======================================================== --%>
						            	<span style="position:absolute; left:500px; top:250px; width:300px; height:250px;" id="searchCustomerDialog10" class="popupWithInputText"  >
						           		<div class="text10" align="left">
						           			<table>
						           				<tr>
													<td class="text11">&nbsp;Kundenummer</td>
													<td class="text11">&nbsp;<input type="text" class="inputText" name="search_sveh_dkkn" id="search_sveh_dkkn" size="18" maxlength="8" value=""></td>
												</tr>
							           			<tr>
													<td class="text11">&nbsp;Navn</td>
													<td class="text11">&nbsp;<input type="text" class="inputText" name="search_sveh_dkna" id="search_sveh_dkna" size="18" maxlength="35" value=""></td>
												</tr>
							           			<tr>
							           				<td class="text11">&nbsp;</td>
								           			<td align="right">&nbsp;<button name="searchCustomer20" class="buttonGray" type="button" onClick="searchAnsvarigOwnWindow()"><spring:message code="systema.skat.search"/></button></td>
								           		</tr>
								           		<tr height="4"><td ></td></tr>
								           		<tr>
							           				<td class="text11">&nbsp;Udvalg</td>
								           			<td>&nbsp;</td>
								           		</tr>
								           		<tr>
													<td colspan="2">&nbsp;
														<select class="text11" id="ansvarigList" name="ansvarigList" size="3" onDblClick="hidePop('searchCustomerDialog10');">
		 													<option selected value="">-vælg-</option>
		 							 					</select>
													</td>
												</tr>
						           			</table>
											<table width="30%" align="left" border="0">
												<tr align="left" >
													<td >&nbsp;<button name="searchCustomer10CloseOk" id="searchCustomer10CloseOk" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('searchCustomerDialog10');"><spring:message code="systema.skat.ok"/></button></td>
													<td >&nbsp;<button name="searchCustomer10CloseCancel" id="searchCustomer10CloseCancel" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('searchCustomerDialog10');"><spring:message code="systema.skat.cancel"/></button></td>
												</tr>
											</table>
										</div>
									</span>	
					 			</td>
			 				</tr>
			 				<tr >
					 			<td class="text12"><input type="text" class="inputTextMediumBlueMandatoryField" name="titin" id="titin" size="17" maxlength="17" value="${model.record.titin}"></td>
					 			<td class="text12"><input type="text" class="inputTextMediumBlueMandatoryField" name="tina" id="tina" size="30" maxlength="30" value="${model.record.tina}"></td>
			 				</tr>
			 				<tr >
					 			<td class="text12">&nbsp;<span title="tiad1">Adresse</span></td>
					 			<td class="text12">&nbsp;<span title="tisk">Sprogkode</span>
					 				<a tabindex="-1" class="text12" target="_blank" href="${model.isoLanguageCodesURL.value}" onclick="${model.isoLanguageCodesURL.windowOpenDimensions}" >
					            			<img style="cursor:pointer;" src="resources/images/find.png" border="0" alt="search" >
					            		</a>
					 			</td>
			 				</tr>
			 				<tr >
					 			<td class="text12"><input type="text" class="inputTextMediumBlue" name="tiad1" id="tiad1" size="30" maxlength="30" value="${model.record.tiad1}"></td>
					 			<td class="text12">
					 				<select name="tisk" id="tisk">
						            		<option value="">-vælg-</option>
						 				  	<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
					                             <option value="${code.tkkode}"<c:if test="${model.record.tisk == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
											</c:forEach> 
									</select>
					 			</td>
			 				</tr>
			 				<tr >
					 			<td class="text12">&nbsp;<span title="tips">By</span></td>
					 			<td class="text12">&nbsp;<span title="tipn">Postnr</span></td>
			 				</tr>
			 				<tr >
					 			<td class="text12"><input type="text" class="inputTextMediumBlue" name="tips" id="tips" size="24" maxlength="24" value="${model.record.tips}"></td>
					 			<td class="text12"><input type="text" class="inputTextMediumBlue" name="tipn" id="tipn" size="9" maxlength="9" value="${model.record.tipn}"></td>
			 				</tr>
			 				<tr >
					 			<td class="text12">&nbsp;<span title="tilk">Landkode</span>
						 			<a tabindex="-1" class="text14" target="_blank" href="${model.skatLandCodesURL.value}" onclick="${model.skatLandCodesURL.windowOpenDimensions}" >
					            			<img style="cursor:pointer;" src="resources/images/find.png" border="0" alt="search" >
					            		</a>
					 			</td>
					 			<td class="text12">&nbsp</td>
			 				</tr>
			 				<tr >
					 			<td>
					 				<select name="tilk" id="tilk">
						            		<option value="">-vælg-</option>
					 				  	<c:forEach var="country" items="${model.countryCodeList}" >
	                                	 	<option value="${country.dkkd_kd}"<c:if test="${model.record.tilk == country.dkkd_kd}"> selected </c:if> >${country.dkkd_kd}</option>
										</c:forEach> 
									</select>
				 				</td>
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
				            <td ><font class="text16RedBold" >*</font><span title="tign">Godsnr</span></td>
				            <td colspan="3" ><input type="text" class="inputTextMediumBlueMandatoryField" name="tign" id="tign" size="36" maxlength="35" value="${model.record.tign}"></td>
			            </tr>
						<tr>
				            <td >&nbsp;</td>
				            <td ><font class="text16RedBold" >*</font><span title="tignsk">Sprogkode</span>&nbsp;</td>
				            <td >
					            <select class="inputTextMediumBlueMandatoryField" name="tignsk" id="tignsk">
					            		<option value="">-vælg-</option>
					 				  	<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
				                             <option value="${code.tkkode}"<c:if test="${model.record.tignsk == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
										</c:forEach> 
								</select>
				 				<a tabindex="-1" class="text12" target="_blank" href="${model.isoLanguageCodesURL.value}" onclick="${model.isoLanguageCodesURL.windowOpenDimensions}" >
				            			<img style="cursor:pointer;" src="resources/images/find.png" border="0" alt="search" >
				            		</a>
				            </td>
			            </tr>
			            <tr>
				            <td >&nbsp;</td>
				            <td ><font class="text16RedBold" >*</font><span title="titrnr">MRN-nr</span></td>
				            <td colspan="3" ><input type="text" class="inputTextMediumBlueMandatoryField" name="titrnr" id="titrnr" size="36" maxlength="18" value="${model.record.titrnr}"></td>
			            </tr>
			            <tr>
				            <td >&nbsp;</td>
				            <td ><font class="text16RedBold" >*</font><span title="tialk">Afs.land</span>&nbsp;</td>
				            <td >
					            <select class="inputTextMediumBlueMandatoryField" name="tialk" id="tialk">
					            		<option value="">-vælg-</option>
				 				  	<c:forEach var="country" items="${model.countryCodeList}" >
		                              	 	<option value="${country.dkkd_kd}"<c:if test="${model.record.tialk == country.dkkd_kd}"> selected </c:if> >${country.dkkd_kd}</option>
									</c:forEach> 
								</select>
					 			<a tabindex="-1" class="text14" target="_blank" href="${model.skatLandCodesURL.value}" onclick="${model.skatLandCodesURL.windowOpenDimensions}" >
				            			<img style="cursor:pointer;" src="resources/images/find.png" border="0" alt="search" >
				            		</a>							
				            </td>
			            </tr>
			            <tr height="10"><td>&nbsp;</td></tr>
			            <tr>
				            <td >&nbsp;</td>
				            <td ><span title="tialsk">Aft.lag.sted (kode)</span></td>
				            <td ><input type="text" class="inputText" name="tialsk" id="tialsk" size="17" maxlength="17" value="${model.record.tialsk}"></td>
				            <td ><span title="tialss">Sprogkode</span>&nbsp;</td>
				            <td >
					            <select name="tialss" id="tialss">
					            		<option value="">-vælg-</option>
					 				  	<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
				                             <option value="${code.tkkode}"<c:if test="${model.record.tialss == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
										</c:forEach> 
								</select>
								<a tabindex="-1" class="text12" target="_blank" href="${model.isoLanguageCodesURL.value}" onclick="${model.isoLanguageCodesURL.windowOpenDimensions}" >
				            			<img style="cursor:pointer;" src="resources/images/find.png" border="0" alt="search" >
				            		</a>
				            </td>
			            </tr>
			            <tr>
				            <td >&nbsp;</td>
				            <td ><span title="tials">Aft.lag.sted</span></td>
				            <td ><input type="text" class="inputText" name="tials" id="tials" size="20" maxlength="35" value="${model.record.tials}"></td>
			            </tr>
			            <tr height="2"><td>&nbsp;</td></tr>
			            <tr>
				            <td >&nbsp;</td>
				            <td ><span title="tiglsk">Godk.lag.sted (kode)</span></td>
				            <td ><input type="text" class="inputText" name="tiglsk" id="tiglsk" size="17" maxlength="17" value="${model.record.tiglsk}"></td>
			            </tr>
			            <tr>
				            <td >&nbsp;</td>
				            <td ><span title="tiacts">Kontrol sted (kode)</span></td>
				            <td ><input type="text" class="inputText" name="tiacts" id="tiacts" size="17" maxlength="17" value="${model.record.tiacts}"></td>
			            </tr>
			            <tr height="2"><td>&nbsp;</td></tr>
			            <tr>
				            <td >&nbsp;</td>
				            <td ><font class="text16RedBold" >*</font><span title="titsb">Best.toldsted</span></td>
				            <td ><input type="text" class="inputTextMediumBlueMandatoryField" name="titsb" id="titsb" size="9" maxlength="8" value="${model.record.titsb}">
					            	<img id="imgTullkontor" style="cursor:pointer;" src="resources/images/find.png" border="0" alt="search" onClick="showPop('searchTullkontor_Dialog');">
		            				<%-- ============================================================= --%>
					            	<%-- Here we have the search Transittullkontor 01 popup window   --%>
					            	<%-- ============================================================= --%>
					            	<span style="position:absolute; left:800px; top:250px; width:300px; height:250px;" id="searchTullkontor_Dialog" class="popupWithInputText"  >
					           		<div class="text10" align="left">
					           			<table>
					           				<tr>
												<td class="text11">&nbsp;Tolsted</td>
												<td class="text11">&nbsp;<input type="text" class="inputText" name="search_sveh_utfa" id="search_sveh_utfa" size="18" maxlength="8" value=""></td>
											</tr>
						           			<tr>
												<td class="text11">&nbsp;Kode</td>
												<td class="text11">&nbsp;<input type="text" class="inputText" name="search_sveh_utfa_Code" id="search_sveh_utfa_Code" size="18" maxlength="35" value=""></td>
											</tr>
						           			<tr>
						           				<td class="text11">&nbsp;</td>
							           			<td align="right">&nbsp;<button name="searchTullkontor" class="buttonGray" type="button" onClick="searchAvgangTullkontorOwnWindow();"><spring:message code="systema.skat.search"/></button></td>
							           		</tr>
							           		<tr height="4"><td ></td></tr>
							           		<tr>
						           				<td class="text11">&nbsp;Udvalg</td>
							           			<td>&nbsp;</td>
							           		</tr>
							           		<tr>
												<td colspan="2">&nbsp;
													<select class="text11" id="tullkontorList" name="tullkontorList" size="3" onDblClick="hidePop('searchTullkontor_Dialog');">
	 													<option selected value="">-vælg-</option>
	 							 					</select>
												</td>
											</tr>
					           			</table>
										<table width="100%" align="left" border="0">
											<tr align="left" >
												<td >&nbsp;<button name="searchTullkontor_Close" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('searchTullkontor_Dialog');"><spring:message code="systema.skat.ok"/></button></td>
											</tr>
										</table>
									</div>
								</span>
				            </td>
				            <td ><span title="tiskb">Sprogkode</span>&nbsp;</td>
				            <td >
					            <select name="tiskb" id="tiskb">
					            		<option value="">-vælg-</option>
					 				  	<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
				                             <option value="${code.tkkode}"<c:if test="${model.record.tiskb == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
										</c:forEach> 
								</select>
				 				<a tabindex="-1" class="text12" target="_blank" href="${model.isoLanguageCodesURL.value}" onclick="${model.isoLanguageCodesURL.windowOpenDimensions}" >
				            			<img style="cursor:pointer;" src="resources/images/find.png" border="0" alt="search" >
				            		</a>
				            </td>
			            </tr>
			            <tr>
				            <td >&nbsp;</td>
				            <td ><span title="tidtf">Frigivelsesdato</span></td>
				            <td ><input readonly type="text" class="inputTextReadOnly" name="tidtf" id="tidtf" size="8" maxlength="8" value="${model.record.tidtf}"></td>
			            </tr>
			            
			            <tr height="2"><td>&nbsp;</td></tr>
			            <tr >	
			            		<td class="text">&nbsp;</td>
			            		<td class="text">&nbsp;</td>
			            		 
		 				    	<%-- only status = M or emtpy status is allowed --%>
		 				    <c:choose>
			 				    <c:when test="${ model.record.tist == 'F' || model.record.tist == 'M' || empty model.record.tist}">
			 				    <td class="text9BlueGreen" valign="bottom"  >
				 				    	<input tabindex=-1 class="inputFormSubmit" type="submit" name="submit" onclick="javascript: form.action='skatnctsimport_edit.do';" value='<spring:message code="systema.skat.ncts.import.createnew.submit"/>'/>
				 				</td>    	
				 				    	&nbsp;&nbsp;
				 				    	<c:if test="${not empty model.record.titdn}">
					 				    	<td colspan="2" class="text9BlueGreen" valign="bottom"  >
					 				    		<input tabindex=-2 class="inputFormSubmit" type="submit" name="send" onclick="javascript: form.action='skatnctsimport_send.do';" value='<spring:message code="systema.skat.ncts.import.createnew.send"/>'/>
					 				    	</td>	
				 				    	</c:if>
			 				    </c:when>
			 				    <c:otherwise>
				 				    <td colspan="2" class="text9BlueGreen" valign="bottom"  >
				 				    		<input disabled class="inputFormSubmitGrayDisabled" type="submit" name="submit" value="<spring:message code="systema.skat.submit.not.editable"/>"/>
				 				    	</td>	
			 				    </c:otherwise>	
		 				    </c:choose>
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
 </form>
 </td>
 </tr>

 	<tr>
	<td>
		<%-- change status admin dialog --%>	
		<div id="dialogUpdateStatus" title="Dialog">
			<form action="skatnctsimport_updateStatus.do" name="updateStatusForm" id="updateStatusForm" method="post">
			 	<input type="hidden" name="currentAvd" id="currentAvd" value="${model.record.tiavd}">
			 	<input type="hidden" name="currentOpd" id="currentOpd" value="${model.record.titdn}">
			 		
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
 
 
	