<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerSkat.jsp" />
<!-- =====================end header ==========================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/jquery.calculator.js"></SCRIPT>
	<SCRIPT type="text/javascript" src="resources/js/jquery-ui-timepicker-addon.js"></SCRIPT>
	<SCRIPT type="text/javascript" src="resources/js/skatimport_edit_invoice.js?ver=${user.versionEspedsg}"></SCRIPT>
	
	<%-- for dialog popup --%>
	<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/blitzer/jquery-ui.css">
	<style type = "text/css">
		.ui-dialog{font-size:10pt;}
	</style>
	
	<%-- tab container component --%>
	<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
		<tr height="2"><td></td></tr>
		<tr height="25"> 
			<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a id="alinkTopicList" tabindex=-1 style="display:block;" href="skatimport.do?action=doFind&sign=${model.sign}">
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.skat.import.list.tab"/></font>
					<img src="resources/images/list.gif" border="0" alt="general list">
					
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a id="alinkHeader" tabindex=-1 style="display:block;" href="skatimport_edit.do?action=doFetch&avd=${model.avd}&opd=${model.opd}
						&sysg=${model.sign}&refnr=${dkih_07}&syst=${model.status}&sydt=${model.datum}">
					
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.skat.import.created.mastertopic.tab"/></font>
					<font class="text12MediumBlue">[${model.opd}]</font>
					<c:if test="${model.status == 'M' || empty model.status || model.status == '10' || model.status == '20'|| model.status == '40'}">
						<img src="resources/images/update.gif" border="0" alt="edit">
					</c:if>
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="15%" valign="bottom" class="tab" align="center" nowrap>
				<font class="tabLink">&nbsp;<spring:message code="systema.skat.import.invoice.tab"/></font>
			</td>
			
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a id="alinkItemLines" tabindex=-1 style="display:block;" href="skatimport_edit_items.do?action=doFetch&avd=${model.avd}&sign=${model.sign}
											&opd=${model.opd}&refnr=${dkih_07}
											&status=${model.status}&datum=${model.datum}&fabl=${recordTopicSkat.dkih_222}">
					<font class="tabDisabledLink">
						&nbsp;<spring:message code="systema.skat.import.item.createnew.tab"/>
					</font>
					<c:if test="${model.status == 'M' || empty model.status || model.status == '1'}">
						<img src="resources/images/add.png" width="12" hight="12" border="0" alt="create new">
					</c:if>
					
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a id="alinkLogging" tabindex=-1 style="display:block;" href="skatimport_logging.do?avd=${model.avd}&sign=${model.sign}&opd=${model.opd}&refnr=${dkih_07}
													&status=${model.status}&datum=${model.datum}">
					<font class="tabDisabledLink">
						&nbsp;<spring:message code="systema.skat.import.logging.tab"/>
					</font>
					<img style="vertical-align: bottom" src="resources/images/log-icon.png" width="16" hight="16" border="0" alt="show log">
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a id="alinkArchive" tabindex=-1 style="display:block;" href="skatimport_archive.do?avd=${model.avd}&sign=${model.sign}
											&opd=${model.opd}&refnr=${dkih_07}
											&status=${model.status}&datum=${model.datum}">
					<font class="tabDisabledLink">
						&nbsp;<spring:message code="systema.skat.import.archive.tab"/>
					</font>
					<img style="vertical-align: bottom" src="resources/images/archive.png" width="16" hight="16" border="0" alt="show archive">
				</a>
			</td>
			<td width="10%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
		</tr>
	</table>
	<%-- -------------------------------- --%>	
 	<%-- tab area container MASTER TOPIC  --%>
	<%-- -------------------------------- --%>
 	<table width="100%" class="tabThinBorderWhite" border="0" cellspacing="0" cellpadding="0">
		<tr height="15"><td colspan="2">&nbsp;</td></tr>	
		
		<tr>
		<td >
		<table border="0" width="95%" align="center">
			<tr>
	 			<td >		
	 				<%-- MASTER Topic header --%>
	 				<table width="80%" align="left" class="formFrameHeaderTransparent" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="15">
				 			<td class="text12MediumBlue">
				 				&nbsp;Afd&nbsp;<b>${model.avd}</b>
				 				&nbsp;Angiv.&nbsp;<b>${model.opd}</b>
				 				&nbsp;Sign&nbsp;<b>${model.sign}</b>
				 				&nbsp;&nbsp;Status:&nbsp;<b>${model.status}</b>				 				
			 				</td>
		 				</tr>
	 				</table>
					<%-- MASTER Topic information [it is passed through a session object: XX] --%>
				 	<table height="40" width="80%" align="left" class="formFrameTitaniumWhite" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="2"><td class="text" align="left" colspan="2"></td></tr>
				 		<tr>
					 		<td width="50%">
						 		<table width="100%" border="0" cellspacing="1" cellpadding="0">
							 		<tr>
							            <td width="30%" class="text11Bold" align="left" >Eksportør</td>
							            <td class="text11" align="left" >&nbsp;&nbsp;</td>
							        </tr>
							        <tr>
							            <td width="30%" class="text11" align="left">EORI&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordTopicSkat.dkih_02a}</td>
							        </tr>
							        <tr>
							            <td width="30%" class="text11" align="left">Navn&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordTopicSkat.dkih_02b}</td>
							        </tr>
									<tr>
							            <td width="30%" class="text11" align="left">Adresse&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordTopicSkat.dkih_02c}</td>
							        </tr>
							        <tr>
							            <td width="30%" class="text11" align="left">Postnr.&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordTopicSkat.dkih_02d}</td>
							        </tr>
							        
									<tr>
							            <td width="30%" class="text11" align="left">By&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordTopicSkat.dkih_02e}&nbsp;${recordTopicSkat.dkih_02f}</td>
							        </tr>
							        <tr>
							        		<td width="30%" class="text11" align="left">&nbsp;</td>
							        </tr>						        
			        	        </table>
					        </td>
					        <td width="50%">
						 		<table width="100%" border="0" cellspacing="1" cellpadding="0">
							 		<tr>
							            <td width="30%" class="text11Bold" align="left" >Modtager</td>
							            <td class="text11" align="left" >&nbsp;&nbsp;</td>
							        </tr>
							        <tr>
							            <td width="30%" class="text11" align="left">CVR/SE-nr.&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordTopicSkat.dkih_08a}</td>
							        </tr>
							        <tr>
							            <td width="30%" class="text11" align="left">Navn&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordTopicSkat.dkih_08b}</td>
							        </tr>
									<tr>
							            <td width="30%" class="text11" align="left">Adresse&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordTopicSkat.dkih_08c}</td>
							        </tr>
							        <tr>
							            <td width="30%" class="text11" align="left">Postnr.&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordTopicSkat.dkih_08d}</td>
							        </tr>
									<tr>
							            <td width="30%" class="text11" align="left">By&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordTopicSkat.dkih_08e}&nbsp;${recordTopicSkat.dkih_08f}</td>
							        </tr>
							        <tr>
							            <td width="30%" class="text11" align="left">Klarereren&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordTopicSkat.dkih_14a}&nbsp;&nbsp;${recordTopicSkat.dkih_14b}</td>
							        </tr>
							        
			        	        </table>
					        </td>
				        </tr>
					</table>          
            	</td>
           	</tr> 
           	<%-- ---------------------- --%>
           	<%-- LIST of existent ITEMs --%>
           	<%-- ---------------------- --%>
           	<tr>
				<td>
					<table width="100%" cellspacing="0" border="0" cellpadding="0">
	    				<%-- separator --%>
	        			<tr height="10"><td></td></tr> 
						<tr >
							<td>
								<table width="80%" cellspacing="0" border="0" cellpadding="0">
									<tr>
										<td class="text12Bold">&nbsp;Antall fakturaer&nbsp;&nbsp;<font class="text12MediumBlue"><b>${model.recordItemContainerInvoiceTopic.totalNumberOfItemLines}</b></font>
						            	</td>
						            	
										<td align="right" class="text11">Fsum:&nbsp;
											<input tabindex=-1 align="right" type="text" readonly class="inputText11BlueBoldReadOnly" size="12" value="${recordTopicSkat.dkih_222}">
											<font class="inputText11BlueBoldReadOnly">${recordTopicSkat.dkih_221}</font>
										</td>
										
										<td align="right" class="text11">Vsum&nbsp;(&Sigma;):&nbsp;
											<input tabindex=-1 align="right" type="text" readonly class="inputText11BlueBoldReadOnly" size="12" value="${model.recordItemContainerInvoiceTopic.calculatedItemLinesTotalAmount}">
											<font class="inputText11BlueBoldReadOnly">${model.recordItemContainerInvoiceTopic.calculatedValidCurrency}</font>											
										</td>
										 
										<%-- Bug somewhere !!!
										<td align="right" class="text11">Diff:&nbsp;
											<input tabindex=-1 align="right" type="text" readonly
												<c:choose>
												<c:when test="${fn:contains(Xmodel.recordItemContainerInvoiceTopic.diffItemLinesTotalAmountWithInvoiceTotalAmount,'-')}">
													class="inputText11RedBoldReadOnly" 
												</c:when>
												<c:otherwise>
													class="inputText11BlueBoldReadOnly"
												</c:otherwise>
												</c:choose>
												size="12" maxlength=20" value='${Xmodel.recordItemContainerInvoiceTopic.diffItemLinesTotalAmountWithInvoiceTotalAmount}'>
										</td>
										 --%>
									</tr>
									<tr height="2"><td></td></tr>
								</table>
							
							</td>
						</tr> 
						
						<tr>
							<td >
							<form name="formItemList" id="formItemList" method="POST" >
					               		<input type="hidden" name="opdItemList" id="opdItemList" value="${model.opd}">
				 						<input type="hidden" name="avdItemList" id="avdItemList" value="${model.avd}">
				 						<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
							
								<table id="containerdatatableTable" width="80%" cellspacing="2" align="left" >
								<tr>
								<td>
								<%-- this is the datatables grid (content) --%>
								<table id="tblInvoices" class="display compact cell-border" >
									<thead>
									<tr style="background-color:#DDDDDD">
									    <th class="text12"><span title="dkif_fatx">&nbsp;Fakturanr.&nbsp;</span></th>   
					                    <%--
					                    <th class="text12" ><span title="dkif_faty">&nbsp;Typ&nbsp;</span></th>
					                     --%>
					                    <th align="right" class="text12" ><span title="dkif_fabl">&nbsp;Beløb&nbsp;</span></th>
					                    <th class="text12" ><span title="dkif_vakd">&nbsp;Møntsort&nbsp;</span></th>
					                    <th align="right" class="text12" ><span title="dkif_vaku">&nbsp;Kurs&nbsp;</span></th>
					                    <th class="text12" align="left"><span title="dkif_omr">Faktor&nbsp;</span></th> 
					                    <c:if test="${ model.status == 'M' || empty  model.status || model.status == '10' || model.status == '20' || model.status == '40'}">
					                    	<th align="center" class="text12" >Radera</th>
					                    </c:if>
					                    
					               </tr> 
					               </thead>
					               <tbody>
				 					  <c:forEach items="${model.list}" var="record" varStatus="counter">    
							               <c:choose>           
							                   <c:when test="${counter.count%2==0}">
							                       <tr class="tableRow" height="20" >
							                   </c:when>
							                   <c:otherwise> 
							                       <tr class="tableOddRow" height="20" >
							                   </c:otherwise>
							               </c:choose>
							               <td width="20%" class="text11" >
							               		<a tabindex=-1 id="recordUpdate_${record.dkif_fatx}" href="#" onClick="getItemData(this);">
							               			&nbsp;<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">&nbsp;${record.dkif_fatx}
							               		</a>
							               </td>
							               <%--
							               <td class="text11" >&nbsp;${record.dkif_faty}</td>
							                --%>
							               <td align="right" class="text11" >&nbsp;${record.dkif_fabl}&nbsp;</td>
							               <td class="text11" >&nbsp;${record.dkif_vakd}</td>
							               <td align="right" class="text11" >&nbsp;${record.dkif_vaku}&nbsp;</td>
							               <td class="text11" >&nbsp;${record.dkif_omr}</td>
							               <c:if test="${ model.status == 'M' || empty  model.status || model.status == '10' || model.status == '20' || model.status == '40'}">
								               <td width="4%" class="text11" align="center" nowrap>
								               	<a onclick="javascript:return confirm('Er du sikker på at du vil slette denne?')" tabindex=-1 href="skatimport_edit_invoice.do?action=doDelete&sign=${model.sign}&avd=${model.avd}&opd=${model.opd}&status=${model.status}&fak=${record.dkif_fatx}">
								               		<img valign="bottom" src="resources/images/delete.gif" border="0" alt="remove">
								               	</a>	&nbsp;
								               </td>
							               </c:if>
							            </tr>
								        <%-- this param is used ONLY in this JSP 
								        <c:set var="totalNumberOfItemLines" value="${counter.count}" scope="request" />
								        --%> 
								        <%-- this param is used throughout the Controller --%>
								        <c:set var="numberOfItemLinesInTopic" value="${Xrecord.svln}" scope="request" /> 
								        </c:forEach>
								       </tbody> 
						            </table>
						        </td>
						        </tr>
						        </table>
						       </form> 
							</td>
							
						</tr>
					</table>
				</td>
			</tr>
			<tr height="1"><td></td></tr>
			<%-- Validation errors --%>
			<spring:hasBindErrors name="record"> <%-- name must equal the command object name in the Controller --%>
			<tr>
				<td>
	            	<table width="100%" align="left" border="0" cellspacing="0" cellpadding="0">
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
			<c:if test="${not empty Xmodel.errorMessage}">
			<tr>
				<td colspan="3">
	            	<table align="left" border="0" cellspacing="0" cellpadding="0">
				 		<tr>
				 			<td class="textError">
				 				<ul>
                                    <li>
                                      	Fel vid uppdatering. [ERROR:${Xmodel.errorMessage}]  
                                    </li>
                                    <li>
                                      	[META-INFO: ${Xmodel.errorInfo}]  
                                    </li>
                                    
                                </ul>
				 			</td>
						</tr>
					</table>
				</td>
			</tr>
			</c:if>
			<%-- ------------------------------------------------- --%>
           	<%-- DETAIL Section - Create Item line PRIMARY SECTION --%>
           	<%-- ------------------------------------------------- --%>
           	<tr>
	           	<td>
	           	<form name="createNewItemLine" id="createNewItemLine" method="post" >
					<input type="hidden" name="action" id="action" value='doFetch'>
	 				<input type="hidden" name="opd" id="opd" value="${model.opd}"/>
	 				<input type="hidden" name="avd" id="avd" value="${model.avd}"/>
	 				<input type="hidden" name="sign" id="sign" value="${model.sign}"/>
	 				<input type="hidden" name="status" id="status" value="${model.status}"/>
	 				<input type="hidden" name="datum" id="datum" value='${model.datum}'>
	 				<input type="hidden" name="fabl" id="fabl" value='${XrecordTopicSkat.todo}'>
	 				<input type="hidden" name="totalGrossWeight" id="totalGrossWeight" value='${XrecordTopicSkat.todo}'>
	 				<table width="80%" cellspacing="0" border="0" cellpadding="0">
						<tr>
							<td class="text12Bold">
								<c:if test="${ model.status == 'M' || empty  model.status || model.status == '10' || model.status == '20' || model.status == '40'}">
									<input tabindex=-1 class="inputFormSubmitStd" type="submit" name="submit" onclick="javascript: form.action='skatimport_edit_invoice.do';" value="Skabe ny">
								</c:if>
								&nbsp;<button title="Import av externa fakturor" name="importInvoicesButton" id="importInvoicesButton" class="buttonGrayWithGreenFrame" type="button" >Import eksterne fakturaer</button>
							</td>
						</tr>
				   </table>
			   	</form>
           		</td>
           	</tr>
           	<%-- ------------------------------------------------- --%>
           	<%-- Init form in case we want to reload               --%>
           	<%-- ------------------------------------------------- --%>
           	<tr>
	 			<td >
	 				<form name="skatImportEditTopicInvoiceItemForm" id="skatImportEditTopicInvoiceItemForm" method="post">
				 	<%--Required key parameters from the Topic parent --%>
				 	<input type="hidden" name="action" id="action" value='doUpdate'/>
				 	<input type="hidden" name="opd" id="opd" value="${model.opd}"/>
				 	<input type="hidden" name="avd" id="avd" value="${model.avd}"/>
				 	<input type="hidden" name="sign" id="sign" value="${model.sign}"/>
				 	<input type="hidden" name="isModeUpdate" id="isModeUpdate" value="${model.record.isModeUpdate}"/>
				 	<input type="hidden" name="status" id="status" value="${model.status}"/>
				 	<input type="hidden" name="datum" id="datum" value="${model.datum}"/>
				 	<input type="hidden" name="fabl" id="fabl" value="${XrecordTopicSkat.svih_fabl}"/>
				 	<input type="hidden" name="lineId" id="lineId" value="">
				 	<%-- <input type="hidden" name="numberOfItemLinesInTopic" id="numberOfItemLinesInTopic" value="${numberOfItemLinesInTopic}" /> --%>
				 	
				 	<%-- Topic ITEM CREATE --%>
	 				<table width="80%" align="left" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="15">
				 			<td class="text12White" align="left" >
				 				<b>&nbsp;&nbsp;F<label onClick="showPop('debugPrintlnAjaxItemFetchAdmin');" >a</label>ktura&nbsp;</b>
				 				
		 									<span style="position:absolute; left:150px; top:200px; width:800px; height:400px;" id="debugPrintlnAjaxItemFetchAdmin" class="popupWithInputText"  >
								           		<div class="text11" align="left">
								           			<label id="debugPrintlnAjaxItemFetchInfo"></label>
								           			<br/>
								           			&nbsp;&nbsp;
								           			<button name="specialInformationButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('debugPrintlnAjaxItemFetchAdmin');">
								           			Close
								           			</button> 
								           		</div>
								        		</span>
		 				
				 				
				 				<img onClick="showPop('updateInfo');" src="resources/images/update.gif" border="0" alt="edit">
				 				<span style="position:absolute; left:150px; top:200px; width:800px; height:400px;" id="updateInfo" class="popupWithInputText"  >
		           		   			<div class="text12" align="left" style="display:block;width:700px;word-break:break-all;">
		           		   				${activeUrlRPGUpdate_Skat}<br/><br/>
		           		   				<button name="updateInformationButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('updateInfo');">Close</button> 
		           		   			</div>
						        </span>  
			 				</td>
		 				</tr>
	 				</table>
	 				
					<table width="80%" align="left" class="formFrame" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="15"><td class="text" align="left"></td></tr>
				 		<tr>
					 		<td>
						 		<table width="100%" border="0" cellspacing="0" cellpadding="0">
							 		<tr>
							 			<td class="text12" align="left"><span title="dkif_fatx"><font class="text16RedBold" >*</font>Fakturanr.</span></td>
							            <%--
							            <td class="text12" align="left"><font class="text16RedBold" >*</font><span title="svif_faty">&nbsp;Typ</span></td>
							             --%>
							            <td class="text12" align="left"><font class="text16RedBold" >*</font><span title="dkif_fabl">&nbsp;Beløb</span></td>
							            <td class="text12" align="left"><font class="text16RedBold" >*</font><span title="dkif_vakd">&nbsp;Møntsort</span></td>
					            		<td class="text12" align="left"><font class="text16RedBold" >*</font><span title="dkif_vaku">&nbsp;Kurs</span></td>
					            		<td class="text12" align="left"><span title="factor">Faktor&nbsp;</span></td>
					            		
							        </tr>
							        <tr>
						        		<td align="left">
						        			<input type="text" class="inputTextMediumBlueMandatoryField" name="dkif_fatx" id="dkif_fatx" size="20" maxlength="17" value="${model.record.dkif_fatx}">
										</td>
										<%--
										<td>
											<select class="inputTextMediumBlueMandatoryField" name="dkif_faty" id="dkif_faty">
						 						<option value="">-Välj-</option>
						 						
							 				  	<c:forEach var="code" items="${Xmodel.mcfCodeList}" >
							 				  		<c:choose>
														<c:when test="${not empty Xmodel.record.dkif_faty}">
								 				  			<option value="${code.dkkd_kd}"<c:if test="${Xmodel.record.dkif_faty == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
								 				  		</c:when>
								 				  		<c:otherwise>
								 				  			<option value="${code.dkkd_kd}"<c:if test="${Xmodel.record.dkif_faty == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
								 				  		</c:otherwise>
								 				  	</c:choose>
												</c:forEach>
												  
											</select>
											<a tabindex="-1" id="bilagdaHandIdLink">
           										<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
           									</a>
			 							</td>
			 							 --%>
										<td class="text12" align="left">
							            		<input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlueMandatoryField" name="dkif_fabl" id="dkif_fabl" size="13" maxlength="12" value="${model.record.dkif_fabl}">
							            </td>
										<td align="left" nowrap>
							            	<select class="inputTextMediumBlueMandatoryField" name="dkif_vakd" id="dkif_vakd">
						 						<option value="">-vælg-</option>	
								 				  <c:forEach var="currency" items="${model.currencyCodeList}" >
							 				  		<option value="${currency.dkkd_kd}"<c:if test="${ model.record.dkif_vakd == currency.dkkd_kd}"> selected </c:if> >${currency.dkkd_kd}</option>
												  </c:forEach>  
												</select> 
												 
											</select>
											<a tabindex="-1" id="dkif_vakdIdLink">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
											</a>
										</td>
						        		<td class="text12" align="left">
						            		<input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlueMandatoryField" name="dkif_vaku" id="dkif_vaku" size="8" maxlength="8" value="${model.record.dkif_vaku}">
							            </td>
							            <%-- this field is only used via Ajax since there is no database field. It is used to disclosed a factor when changing the currency --%>
							 			<td class="text12Grey" align="left" ><input readonly type="text" class="inputTextReadOnly" name="factor" id="factor" size="6" value=""></td>
							        </tr>
							        <tr height="10"><td class="text" align="left"></td></tr>
						        </table>
					        </td>
				        </tr>
					    <tr height="10"><td colspan="2" ></td></tr>
					    <tr>	
						    <td align="left" colspan="5">
									<c:choose>	
										<c:when test="${ model.status == 'M' || empty  model.status || model.status == '10' || model.status == '20' || model.status == '40'}">
											<input class="inputFormSubmit" type="submit" name="submit" onclick="javascript: form.action='skatimport_edit_invoice.do';" value='Spare faktura'>
											&nbsp;&nbsp;
										</c:when>
										<c:otherwise>
				 				    		<input disabled class="inputFormSubmitGrayDisabled" type="submit" name="submit" value='Ikke opdaterbar'/>
				 				    	</c:otherwise>	
			 				    	</c:choose>	
							</td>							        	
				        </tr>
        	        </table>
        	         
		        </td>
		    </tr>
			<tr height="20"><td colspan="2" ></td></tr>
			<tr height="30"><td></td></tr>
			
		</table>
		</td>
		</tr>
	</table>    
	
	</form>
		
<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->

