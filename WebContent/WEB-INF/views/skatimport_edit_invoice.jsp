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
				<font class="tabLink">&nbsp;<spring:message code="systema.tds.import.invoice.tab"/></font>
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
			<td width="15%" valign="bottom" class="tab" align="center" nowrap>
				<font class="tabLink">&nbsp;<spring:message code="systema.skat.import.logging.tab"/></font>
				<img style="vertical-align: bottom" src="resources/images/log-icon.png" width="16" hight="16" border="0" alt="show log">
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
				 				&nbsp;Avd&nbsp;<b>${Xmodel.avd}</b>
				 				&nbsp;Tolldeknr.&nbsp;<b>${Xmodel.opd}</b>
				 				&nbsp;Sign&nbsp;<b>${Xmodel.sign}</b>
				 				&nbsp;&nbsp;Status:&nbsp;<b>${Xmodel.status}</b>
				 				&nbsp;&nbsp;Dekl.:&nbsp;<b>${recordTopic.svih_dek1}</b>
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
							            <td width="30%" class="text11Bold" align="left" >Exportör</td>
							            <td class="text11" align="left" >&nbsp;&nbsp;</td>
							        </tr>
							        <tr>
							            <td width="30%" class="text11" align="left">EORI&nbsp;</td>
							           	<td class="text11MediumBlue" align="left"></td>
							        </tr>
							        <tr>
							            <td width="30%" class="text11" align="left">Namn&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordTopic.svih_avna}</td>
							        </tr>
									<tr>
							            <td width="30%" class="text11" align="left">Adress 1&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordTopic.svih_ava1}</td>
							        </tr>
							        <tr>
							            <td width="30%" class="text11" align="left">Adress 2&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordTopic.svih_ava2}</td>
							        </tr>
							        
									<tr>
							            <td width="30%" class="text11" align="left">Postadress&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordTopic.svih_avpn}&nbsp;${recordTopic.svih_avpa}&nbsp;${recordTopic.svih_avlk}</td>
							        </tr>
							        <tr>
							        		<td width="30%" class="text11" align="left">&nbsp;</td>
							        </tr>						        
			        	        </table>
					        </td>
					        <td width="50%">
						 		<table width="100%" border="0" cellspacing="1" cellpadding="0">
							 		<tr>
							            <td width="30%" class="text11Bold" align="left" >Mottagare</td>
							            <td class="text11" align="left" >&nbsp;&nbsp;</td>
							        </tr>
							        <tr>
							            <td width="30%" class="text11" align="left">EORI.&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordTopic.svih_moeo}</td>
							        </tr>
							        <tr>
							            <td width="30%" class="text11" align="left">Namn&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordTopic.svih_mona}</td>
							        </tr>
									<tr>
							            <td width="30%" class="text11" align="left">Adress 1&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordTopic.svih_moa1}</td>
							        </tr>
							        <tr>
							            <td width="30%" class="text11" align="left">Adress 2&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordTopic.svih_moa2}</td>
							        </tr>
									<tr>
							            <td width="30%" class="text11" align="left">Postadress&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordTopic.svih_mopn}&nbsp;${recordTopic.svih_mopa}&nbsp;${recordTopic.svih_molk}</td>
							        </tr>
							        <tr>
							            <td width="30%" class="text11" align="left">Handläggare&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordTopic.svih_moha}&nbsp;tel:${recordTopic.svih_motl}</td>
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
										<td class="text12Bold">&nbsp;Antal fakturor&nbsp;&nbsp;<font class="text12MediumBlue"><b>${Xmodel.recordItemContainerInvoiceTopic.totalNumberOfItemLines}</b></font>
						            	</td>
						            	
										<td align="right" class="text11">Fsum:&nbsp;
											<input tabindex=-1 align="right" type="text" readonly class="inputText11BlueBoldReadOnly" size="12" value="${recordTopic.svih_fabl}">
											<font class="inputText11BlueBoldReadOnly">${recordTopic.svih_vakd}</font>
										</td>
										
										<td align="right" class="text11">Vsum&nbsp;(&Sigma;):&nbsp;
											<input tabindex=-1 align="right" type="text" readonly class="inputText11BlueBoldReadOnly" size="12" value="${Xmodel.recordItemContainerInvoiceTopic.calculatedItemLinesTotalAmount}">
											<font class="inputText11BlueBoldReadOnly">${Xmodel.recordItemContainerInvoiceTopic.calculatedValidCurrency}</font>											
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
					               		<input type="hidden" name="opdItemList" id="opdItemList" value="${Xmodel.opd}">
				 						<input type="hidden" name="avdItemList" id="avdItemList" value="${Xmodel.avd}">
				 						<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
							
								<table id="containerdatatableTable" width="80%" cellspacing="2" align="left" >
								<tr>
								<td>
								<%-- this is the datatables grid (content) --%>
								<table id="tblInvoices" class="display compact cell-border" >
									<thead>
									<tr style="background-color:#DDDDDD">
									    <th class="text12"><span title="svif_fatx">&nbsp;Fakturanr.&nbsp;</span></th>   
					                    <th class="text12" ><span title="svif_faty">&nbsp;Typ&nbsp;</span></th>
					                    <th align="right" class="text12" ><span title="svif_fabl">&nbsp;Belopp&nbsp;</span></th>
					                    <th class="text12" ><span title="svif_vakd">&nbsp;Valuta&nbsp;</span></th>
					                    <th align="right" class="text12" ><span title="svif_vaku">&nbsp;Kurs&nbsp;</span></th>
					                    <th class="text12" align="left"><span title="svif_omr">Faktor&nbsp;</span></th> 
					                    <c:if test="${Xmodel.status == 'M' || empty Xmodel.status}">
					                    	<th align="center" class="text12" >Radera</th>
					                    </c:if>
					                    
					               </tr> 
					               </thead>
					               <tbody>
				 					  <c:forEach items="${Xmodel.list}" var="record" varStatus="counter">    
							               <c:choose>           
							                   <c:when test="${counter.count%2==0}">
							                       <tr class="tableRow" height="20" >
							                   </c:when>
							                   <c:otherwise> 
							                       <tr class="tableOddRow" height="20" >
							                   </c:otherwise>
							               </c:choose>
							               <td width="20%" class="text11" >
							               		<a tabindex=-1 id="recordUpdate_${record.svif_fatx}" href="#" onClick="getItemData(this);">
							               			&nbsp;<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">&nbsp;${record.svif_fatx}
							               		</a>
							               </td>
							               <td class="text11" >&nbsp;${record.svif_faty}</td>
							               <td align="right" class="text11" >&nbsp;${record.svif_fabl}&nbsp;</td>
							               <td class="text11" >&nbsp;${record.svif_vakd}</td>
							               <td align="right" class="text11" >&nbsp;${record.svif_vaku}&nbsp;</td>
							               <td class="text11" >&nbsp;${record.svif_omr}</td>
							               <c:if test="${Xmodel.status == 'M' || empty Xmodel.status}">	
								               <td width="4%" class="text11" align="center" nowrap>
								               	<a onclick="javascript:return confirm('Är du säker att du vill radera raden?')" tabindex=-1 href="tdsimport_edit_invoice.do?action=doDelete&sign=${Xmodel.sign}&avd=${Xmodel.avd}&opd=${Xmodel.opd}&status=${Xmodel.status}&fak=${record.svif_fatx}">
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
	 				<input type="hidden" name="opd" id="opd" value="${Xmodel.opd}"/>
	 				<input type="hidden" name="avd" id="avd" value="${Xmodel.avd}"/>
	 				<input type="hidden" name="sign" id="sign" value="${Xmodel.sign}"/>
	 				<input type="hidden" name="status" id="status" value="${XXmodel.status}"/>
	 				<input type="hidden" name="datum" id="datum" value='${Xmodel.datum}'>
	 				<input type="hidden" name="fabl" id="fabl" value='${recordTopic.svih_fabl}'>
	 				<input type="hidden" name="totalGrossWeight" id="totalGrossWeight" value='${XrecordTopicTvinnSad.sevkb}'>
	 				<table width="80%" cellspacing="0" border="0" cellpadding="0">
						<tr>
							<td class="text12Bold">
								<c:if test="${XXmodel.status == 'M' || empty XXmodel.status}">
									<input tabindex=-1 class="inputFormSubmitStd" type="submit" name="submit" onclick="javascript: form.action='tdsimport_edit_invoice.do';" value="Skapa ny">
								</c:if>
								&nbsp;<button title="Import av externa fakturor" name="importInvoicesButton" id="importInvoicesButton" class="buttonGrayWithGreenFrame" type="button" >Importera externa fakturor</button>
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
	 				<form name="tdsImportEditTopicInvoiceItemForm" id="tdsImportEditTopicInvoiceItemForm" method="post">
				 	<%--Required key parameters from the Topic parent --%>
				 	<input type="hidden" name="action" id="action" value='doUpdate'/>
				 	<input type="hidden" name="opd" id="opd" value="${Xmodel.opd}"/>
				 	<input type="hidden" name="avd" id="avd" value="${Xmodel.avd}"/>
				 	<input type="hidden" name="sign" id="sign" value="${Xmodel.sign}"/>
				 	<input type="hidden" name="isModeUpdate" id="isModeUpdate" value="${Xmodel.record.isModeUpdate}"/>
				 	<input type="hidden" name="status" id="status" value="${XXmodel.status}"/>
				 	<input type="hidden" name="datum" id="datum" value="${XXmodel.datum}"/>
				 	<input type="hidden" name="fabl" id="fabl" value="${recordTopic.svih_fabl}"/>
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
		           		   				${activeUrlRPGUpdate}<br/><br/>
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
							 			<td class="text12" align="left"><span title="svif_fatx"><font class="text16RedBold" >*</font>Fakturanr.</span></td>
							            <td class="text12" align="left"><font class="text16RedBold" >*</font><span title="svif_faty">&nbsp;Typ</span></td>
							            <td class="text12" align="left"><font class="text16RedBold" >*</font><span title="svif_fabl">&nbsp;Belopp</span></td>
							            <td class="text12" align="left"><font class="text16RedBold" >*</font><span title="svif_vakd">&nbsp;Valuta</span></td>
					            		<td class="text12" align="left"><font class="text16RedBold" >*</font><span title="svif_vaku">&nbsp;Kurs</span></td>
					            		<td class="text12" align="left"><span title="factor">Faktor&nbsp;</span></td>
					            		
							        </tr>
							        <tr>
						        		<td align="left">
						        			<input type="text" class="inputTextMediumBlueMandatoryField" name="svif_fatx" id="svif_fatx" size="20" maxlength="17" value="${Xmodel.record.svif_fatx}">
										</td>
										<td>
											<select class="inputTextMediumBlueMandatoryField" name="svif_faty" id="svif_faty">
						 						<option value="">-Välj-</option>
						 						
							 				  	<c:forEach var="code" items="${Xmodel.mcfCodeList}" >
							 				  		<c:choose>
														<c:when test="${not empty Xmodel.record.svif_faty}">
								 				  			<option value="${code.svkd_kd}"<c:if test="${Xmodel.record.svif_faty == code.svkd_kd}"> selected </c:if> >${code.svkd_kd}</option>
								 				  		</c:when>
								 				  		<c:otherwise>
								 				  			<option value="${code.svkd_kd}"<c:if test="${Xmodel.record.svif_faty == code.svkd_kd}"> selected </c:if> >${code.svkd_kd}</option>
								 				  		</c:otherwise>
								 				  	</c:choose>
												</c:forEach>
												  
											</select>
											<a tabindex="-1" id="bilagdaHandIdLink">
           										<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
           									</a>
			 							</td>
										<td class="text12" align="left">
							            		<input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlueMandatoryField" name="svif_fabl" id="svif_fabl" size="13" maxlength="12" value="${Xmodel.record.svif_fabl}">
							            </td>
										<td align="left" nowrap>
							            	<select class="inputTextMediumBlueMandatoryField" name="svif_vakd" id="svif_vakd">
						 						<option value="">-välj-</option>
						 						 
							 				  	<c:forEach var="currency" items="${Xmodel.mdxCodeList}" >
							 				  		<option value="${currency.svkd_kd}"<c:if test="${Xmodel.record.svif_vakd == currency.svkd_kd}"> selected </c:if> >${currency.svkd_kd}</option>
												</c:forEach> 
												 
											</select>
											<a tabindex="-1" id="valutaIdLink">
           										<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
           									</a>
										</td>
						        		<td class="text12" align="left">
						            		<input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlueMandatoryField" name="svif_vaku" id="svif_vaku" size="8" maxlength="8" value="${Xmodel.record.svif_vaku}">
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
										<c:when test="${Xmodel.status == 'M' || empty Xmodel.status || Xmodel.status == '1'}">
											<input class="inputFormSubmit" type="submit" name="submit" onclick="javascript: form.action='tdsimport_edit_invoice.do';" value='Spara faktura'>
											&nbsp;&nbsp;
										</c:when>
										<c:otherwise>
				 				    		<input disabled class="inputFormSubmitGrayDisabled" type="submit" name="submit" value='Ej uppdaterbart'/>
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

