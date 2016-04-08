<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>


<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerSkat.jsp" />
<!-- =====================end header ==========================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/skatimport_edit_items.js?ver=${user.versionEspedsg}"></SCRIPT>
	<SCRIPT type="text/javascript" src="resources/js/skatimport_edit_items_tullvaerdideklaration.js?ver=${user.versionEspedsg}"></SCRIPT>
	<SCRIPT type="text/javascript" src="resources/js/jquery.calculator.js"></SCRIPT>
	<%-- bug in jquery 1.10.0 when adding a form to a dialog box as in:
		jq('div#myDiv').parent().appendTo('form#myForm');	
		 need to put z-index --%>
	<style type = "text/css">
	.ui-dialog { z-index: 101;} 
	</style>
	
	<%-- tab container component --%>
	<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
		<tr height="2"><td></td></tr>
		<tr height="25"> 
			<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a id="alinkTopicList" tabindex=-1 style="display:block;" href="skatimport.do?action=doFind&sign=${model.sign}">
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.skat.import.list.tab"/></font>
					<img valign="bottom" src="resources/images/list.gif" border="0" alt="general list">
					
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a id="alinkHeader" tabindex=-1 style="display:block;" href="skatimport_edit.do?action=doFetch&avd=${model.avd}&opd=${model.opd}
						&sysg=${model.sign}&refnr=${dkih_07}&syst=${model.status}&sydt=${model.datum}">
					
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.skat.import.created.mastertopic.tab"/></font>
					<font class="text12MediumBlue">[${model.opd}]</font>
					<c:if test="${model.status == 'M' || empty model.status || model.status == '10' || model.status == '20'|| model.status == '40'}">
						<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
					</c:if>
				</a>
			</td>
			
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a id="alinkInvoices" tabindex=-1 style="display:block;" href="skatimport_edit_invoice.do?action=doFetch&avd=${model.avd}&sign=${model.sign}
													&opd=${model.opd}&refnr=${dkih_07}
													&status=${model.status}&datum=${model.datum}">
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.skat.import.invoice.tab"/></font>
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="15%" valign="bottom" class="tab" align="center" nowrap>
				<font class="tabLink">&nbsp;<spring:message code="systema.skat.import.item.createnew.tab"/></font>
				<c:if test="${model.status == 'M' || empty model.status || model.status == '10' || model.status == '20'|| model.status == '40'}">
					<img valign="bottom" src="resources/images/add.png" width="12" hight="12" border="0" alt="create new">
				</c:if>
				
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
				<a id="alinkArchive" tabindex=-1 style="display:block;" href="skatimport_archive.do?avd=${model.avd}&sign=${model.sign}&opd=${model.opd}&refnr=${dkih_07}
													&status=${model.status}&datum=${model.datum}"">
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
		<table border="0" width="95%" align="center" border="0">
			<tr>
	 			<td >		
	 				<%-- MASTER Topic header --%>
	 				<table width="100%" align="center" class="formFrameHeaderTransparent" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="15">
				 			<td class="text12MediumBlue">
				 				&nbsp;Avd&nbsp;<b>${model.avd}</b>
				 				&nbsp;Angivelse&nbsp;<b>${model.opd}</b>
				 				&nbsp;Sign&nbsp;<b>${model.sign}</b>
				 				&nbsp;&nbsp;&nbsp;&nbsp;Ref.nr.:&nbsp;<b>${recordTopicSkat.dkih_07}</b>
				 				&nbsp;&nbsp;
				 				<img onMouseOver="showPop('status_info');" onMouseOut="hidePop('status_info');" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 				Status:&nbsp;<b>${model.status}</b>
				 				&nbsp;&nbsp;Angivelsesart:&nbsp;<b>${recordTopicSkat.dkih_aart}</b>
				 				
				 				<div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:500px;" id="status_info" class="popupWithInputText text11"  >
					           		 Kun status <b>M</b>,<b>10</b>,<b>20</b>eller <b>' '</b> kan redigeres.
					           		 <br/><br/> 
					           			<table width="90%" align="center" cellspacing="0" border="0" cellpadding="0">
					           				<c:forEach var="record" items="${model.statusCodeList}" varStatus="counter" >
					           				<c:choose>
					           					<c:when test="${counter.count%2 != 0}" >
						           					<tr>
						           					<td class="tableCellFirst" width="50%">
					           				 			<b>${record.dkkd_kd}</b>&nbsp;${record.dkkf_txt}
					           						</td>
					           					</c:when>
						           				<c:otherwise>
													<td class="tableCell" width="50%">
					           				 			<b>${record.dkkd_kd}</b>&nbsp;${record.dkkf_txt}
					           						</td>
					           						</tr>
						           				</c:otherwise>
					           				</c:choose>
					           				</c:forEach>
					           			</table>
								</span>	
								</div>
			 				</td>
		 				</tr>
	 				</table>
					<%-- MASTER Topic information [it is passed through a session object: XX] --%>
				 	<table height="40" width="100%" align="center" class="formFrameTitaniumWhite" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="2"><td class="text" align="left" colspan="2"></td></tr>
				 		<tr>
					 		<td width="50%">
						 		<table width="80%" border="0" cellspacing="1" cellpadding="0">
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
							           	<td class="text11MediumBlue" align="left">${recordTopicSkat.dkih_02d}&nbsp;${recordTopicSkat.dkih_02e}</td>
							        </tr>
							        
									<tr>
							            <td width="30%" class="text11" align="left">Landkode&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordTopicSkat.dkih_02f}</td>
							        </tr>						        
							        
			        	        </table>
					        </td>
					        <td width="50%">
						 		<table width="80%" border="0" cellspacing="1" cellpadding="0">
							 		<tr>
							            <td width="30%" class="text11Bold" align="left" >Modtager</td>
							            <td class="text11" align="left" >&nbsp;&nbsp;</td>
							        </tr>
							        <tr>
							            <td width="30%" class="text11" align="left">CVR/SE-nr&nbsp;</td>
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
							           	<td class="text11MediumBlue" align="left">${recordTopicSkat.dkih_08d}&nbsp;${recordTopicSkat.dkih_08e}</td>
							        </tr>
							        
									<tr>
							            <td width="30%" class="text11" align="left">Landkode&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordTopicSkat.dkih_08f}</td>
							        </tr>
							        <%--
									<tr>
							            <td width="30%" class="text11" align="left">Handläggare&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${XX.svih_moha}</td>
							        </tr>
							         --%>
							        
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
							<form name="createNewItemLine" id="createNewItemLine" method="post" action="skatimport_edit_items.do">
								<input type="hidden" name="action" id="action" value='doFetch'>
				 				<input type="hidden" name="avd" id="avd" value='${model.avd}'>
				 				<input type="hidden" name="sign" id="sign" value='${model.sign}'>
								<input type="hidden" name="opd" id="opd" value='${model.opd}'>
				 				<input type="hidden" name="refnr" id="refnr" value='${dkih_07}'>
				 				<input type="hidden" name="status" id="status" value='${model.status}'>
				 				<input type="hidden" name="datum" id="datum" value='${model.datum}'>
				 				<input type="hidden" name="fabl" id="fabl" value='${recordTopicSkat.dkih_222}'>
				 				<input type="hidden" name="dkih_mokn" id="dkih_mokn" value='${recordTopicSkat.dkih_mokn}'>
				 				<input type="hidden" name="totalGrossWeight" id="totalGrossWeight" value='${recordTopicSkat.dkih_brut}'>
				 				
				 				
				 										
								<table width="100%" cellspacing="0" border="0" cellpadding="0">
									<tr>
										<td class="text12Bold">
											<c:if test="${model.status == 'M' || empty model.status || model.status == '10' || model.status == '20'|| model.status == '40'}">
												<input tabindex=-1 class="inputFormSubmitStd" type="submit" name="submitNew" onclick="javascript: form.action='skatimport_edit_items.do';" value="<spring:message code="systema.skat.import.item.line.init.createnew.submit"/>">
											</c:if>
											<button name="allItemsButton" class="inputFormSubmitStd" type="button" onClick="showPop('allItems');" >Vis alle</button> 
										       <span style="background-color:#EEEEEE; position:absolute; left:50px; top:200px; width:1100px; height:1000px;" id="allItems" class="popupWithInputTextThickBorder"  >
									           		<table id="containerdatatableTable" width="98%" align="left" >
													<tr>
													<td class="text12">
												
														<table id="tblItemLinesAll" class="display compact cell-border">
										           			<thead>
												           	<tr style="background-color:#DDDDDD">	
																    <th class="">&nbsp;Linjenr.&nbsp;</th>   
																    <th class="text11"><spring:message code="systema.skat.import.item.list.label.dkiv_28b.purchaseSellerInvoice"/>&nbsp;</th>
												                    <th class="text11" ><spring:message code="systema.skat.import.item.list.label.dkiv_32.varepostNr"/>&nbsp;</th>   
												                    <th class="text11" >&nbsp;<spring:message code="systema.skat.import.item.list.label.dkiv_34.oprLand"/>&nbsp;</th>
												                    <th class="text11" >&nbsp;<spring:message code="systema.skat.import.item.list.label.dkiv_331.varekod"/>&nbsp;</th>
												                    <th class="text11" >&nbsp;<spring:message code="systema.skat.import.item.list.label.dkiv_36.preference"/>&nbsp;</th>
												                    <th class="text11" >&nbsp;<spring:message code="systema.skat.import.item.list.label.dkiv_37.procedure"/>&nbsp;</th>
												                    <th class="text11">&nbsp;<spring:message code="systema.skat.import.item.list.label.dkiv_35.bruttov"/>&nbsp;</th>
												                    <th class="text11">&nbsp;<spring:message code="systema.skat.import.item.list.label.dkiv_38.nettov"/>&nbsp;</th>
												                    <th class="text11">&nbsp;<spring:message code="systema.skat.import.item.list.label.dkiv_412.supplEnhVerdi"/>&nbsp;</th>
												                    <th class="text11">&nbsp;<spring:message code="systema.skat.import.item.list.label.dkiv_313a.kolliAntal"/></th>
												                    <th class="text11">&nbsp;<spring:message code="systema.skat.import.item.list.label.dkiv_315a.vareDescription"/>&nbsp;</th>
												                    <th class="text11">&nbsp;Avg / <spring:message code="systema.skat.import.item.list.label.dkiv_46.statValue"/></th>
												                    <th class="text11" ><spring:message code="systema.skat.import.item.list.label.dkiv_42.varansPris"/>&nbsp;</th>
												                    <th class="text12" >&nbsp;<spring:message code="systema.skat.import.item.list.label.dkerr.error"/>&nbsp;</th>
												                    <c:if test="${model.status == 'M' || empty model.status || model.status == '10' || model.status == '20'|| model.status == '40'}">
												                    	<th align="center" class="text11" nowrap>Fjern</th>
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
														               <td width="2%" class="text11" align="center">&nbsp;${record.dkiv_syli}
														               		<%--
														               		<a tabindex=-1 id="recordUpdate_${record.dkiv_syli}_${record.dkiv_32}" href="#" onClick="getItemData(this);">${record.dkiv_syli}
														               			<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">&nbsp;
														               		</a>
														               		--%>
														               </td>
														               
														               <td class="text11" >&nbsp;${record.dkiv_28b}</td>
														               <td width="2%" class="text11" >&nbsp;${record.dkiv_32}</td>
														               <td class="text11" >&nbsp;${record.dkiv_34}</td>
														               <td class="text11" >&nbsp;${record.dkiv_331}</td>
														               <td class="text11" >&nbsp;${record.dkiv_36}</td>
														               <td class="text11" >&nbsp;${record.dkiv_37}</td>
														               <td class="text11" >&nbsp;${record.dkiv_35}</td>
														               <td class="text11" >&nbsp;${record.dkiv_38}</td>
														               <td class="text11" >&nbsp;${record.dkiv_412}</td>
														               <td class="text11" >&nbsp;${record.dkiv_313a}</td>
														               <td class="text11" ><div style="width:120px">&nbsp;${record.dkiv_315a}</div></td>
														               <td class="text11" align="left">&nbsp;&nbsp;${record.dkiv_46}
														               
														               <%--
														               		<font class="text12OrangeBold" onMouseOver="showPop('avgifterReadOnly_info_${record.dkiv_syli}');" onMouseOut="hidePop('avgifterReadOnly_info_${record.dkiv_syli}');" alt="info">
														               		 <img valign="bottom" src="resources/images/infoOrange.png" width="12px" height="12px" border="0" alt="avg.info">&nbsp;
														               		</font>
														               <span class="popupWithInputTextGrayBg" style="position:absolute; left:620px; top:300px;" id="avgifterReadOnly_info_${record.dkiv_syli}" >
														           		<div class="text10" align="left" >
													           				<table cellspacing="0" border="0" cellpadding="0">
													           					<tr>
													           						<td colspan="5" class="text12OrangeBold">Afgifter [Sker på SKAT]</td>
													           					</tr>
													           					<tr height="10"><td></td></tr>
													           					<tr>
													           						<td colspan="2" class="text14">Linjenr&nbsp;<b>${record.dkiv_syli}</b></td>
													           						<td colspan="3" class="text14">Varukod&nbsp;<b>${record.dkiv_331}</b></td>
													           					</tr>
													           					<tr height="10"><td></td></tr>
													           					<tr class="tableHeaderField" height="20" valign="left">
																    					<td class="tableHeaderFieldFirst">&nbsp;Art&nbsp;</td>   
																    					<td class="tableHeaderField">&nbsp;Grundlag&nbsp;</td>   
																    					<td class="tableHeaderField">&nbsp;Sats&nbsp;</td>   
																    					<td class="tableHeaderField">&nbsp;Beløb&nbsp;</td>   
																    					<td class="tableHeaderField">&nbsp;Ber.art&nbsp;</td>   
																    					
																    				</tr>	
																    				<tr class="tableRow" >
													           						<td class="tableCellFirst" >&nbsp;${record.dkiva_abk1}</td>
													           						<td class="tableCell" >&nbsp;${record.dkiva_abg1}</td>
													           						<td class="tableCell" >&nbsp;${record.dkiva_abs1}</td>
													           						<td class="tableCell" >&nbsp;${record.dkiva_abb1}</td>
													           						<td class="tableCell" >&nbsp;${record.dkiva_aba1}</td>
													           						
													           					</tr>
													           					
																				<tr class="tableRow" >
													           						<td class="tableCellFirst" >&nbsp;${record.dkiva_abk2}</td>
													           						<td class="tableCell" >&nbsp;${record.dkiva_abg2}</td>
													           						<td class="tableCell" >&nbsp;${record.dkiva_abs2}</td>
													           						<td class="tableCell" >&nbsp;${record.dkiva_abb2}</td>
													           						<td class="tableCell" >&nbsp;${record.dkiva_aba2}</td>
													           						
													           					</tr>
																				<tr class="tableRow" >
													           						<td class="tableCellFirst" >&nbsp;${record.dkiva_abk3}</td>
													           						<td class="tableCell" >&nbsp;${record.dkiva_abg3}</td>
													           						<td class="tableCell" >&nbsp;${record.dkiva_abs3}</td>
													           						<td class="tableCell" >&nbsp;${record.dkiva_abb3}</td>
													           						<td class="tableCell" >&nbsp;${record.dkiva_aba3}</td>
													           						
													           					</tr>
																				<tr class="tableRow" >
													           						<td class="tableCellFirst" >&nbsp;${record.dkiva_abk4}</td>
													           						<td class="tableCell" >&nbsp;${record.dkiva_abg4}</td>
													           						<td class="tableCell" >&nbsp;${record.dkiva_abs4}</td>
													           						<td class="tableCell" >&nbsp;${record.dkiva_abb4}</td>
													           						<td class="tableCell" >&nbsp;${record.dkiva_aba4}</td>
													           						
													           					</tr>
																				<tr class="tableRow" >
													           						<td class="tableCellFirst" >&nbsp;${record.dkiva_abk5}</td>
													           						<td class="tableCell" >&nbsp;${record.dkiva_abg5}</td>
													           						<td class="tableCell" >&nbsp;${record.dkiva_abs5}</td>
													           						<td class="tableCell" >&nbsp;${record.dkiva_abb5}</td>
													           						<td class="tableCell" >&nbsp;${record.dkiva_aba5}</td>
													           						
													           					</tr>
													           					<tr height="10"><td></td></tr>
													           					
													           				</table>		 
													           			</div>
													           			</span>	
														               	 --%>	
														               	</td>
														               <td class="text11">&nbsp;${record.dkiv_42}</td>
														               <td align="center" class="text11">&nbsp;
														               		<c:if test="${not empty record.dkiv_err}">
														               			<img src="resources/images/redFlag.png" width="18px" height="18px" border="0" alt="remove">
														               		</c:if>
														               </td>
														               <c:if test="${model.status == 'M' || empty model.status || model.status == '10' || model.status == '20'|| model.status == '40'}">	
															               <td class="text11" align="center" nowrap>&nbsp;
															               	<a onclick="javascript:return confirm('Er du sikker på at du vil slette denne?')" tabindex=-1 href="skatimport_edit_items.do?action=doDelete&avd=${record.dkiv_syav}&opd=${record.dkiv_syop}&lin=${record.dkiv_syli}&fabl=${XX.svih_fabl}">
															               		<img valign="bottom" src="resources/images/delete.gif" border="0" alt="remove">
															               	</a>	
															               </td>
														               </c:if>
														               
														            </tr>
														            <%-- this param is used ONLY in this JSP --%>
															        <c:set var="totalNumberOfItemLines" value="${counter.count}" scope="request" /> 
															        <%-- this param is used throughout the Controller --%>
															        <c:set var="numberOfItemLinesInTopic" value="${record.dkiv_syli}" scope="request" /> 
															        </c:forEach>
															     </tbody>   
													        </table>
															</td>											           		
												         </tr>
												         </table>
												         
												         <div>
												         	<table >
															<%-- OK BUTTON --%>
									           				<tr align="left" >
																<td class="text11"><button name="allItemsButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('allItems');">&nbsp;Ok</button></td>
																<td class="text12">&nbsp;&nbsp;&nbsp;
													 	        		<a href="skatImportListExcelView.do" target="_new">
												                 		<img valign="bottom" id="itemListExcel" src="resources/images/excel.png" border="0" alt="excel">&nbsp;Excel
													 	        		</a>
													 	        		&nbsp;
														 		</td>
															</tr>
															</table>
														</div>	
								   				</span>	
								   				<c:if test="${model.status == 'M' || empty model.status || model.status == '10' || model.status == '20'|| model.status == '40'}">		
													&nbsp;<button title="Kontrollere vareposter" name="itemListControlButton" id="itemListControlButton" class="buttonGrayWithGreenFrame11" type="button" >Kontrollere vareposter</button>
												</c:if>	
										</td>
									</tr>
									<tr>
										<td class="text12Bold">&nbsp;Antal vareposter&nbsp;&nbsp;<font class="text12MediumBlue"><b>${totalNumberOfItemLines}</b></font>
						            		
										</td>
										<td width="10%" class="text12">&nbsp;</td>
										
										<td align="right" class="text11">Bruttovægt:&nbsp;
											<input tabindex=-1 align="right" type="text" readonly class="inputText11BlueBoldReadOnly" size="12" maxlength=11" value='${recordTopicSkat.dkih_brut}'>
										</td>
										<td align="right" class="text11">Faktura beløb:&nbsp;
											<input tabindex=-1 align="right" type="text" readonly class="inputText11BlueBoldReadOnly" size="12" maxlength=20" value='${recordTopicSkat.dkih_222}'>
											&nbsp;<font style="color:#000080; font-style: italic;"><b>${recordTopicSkat.dkih_221}</b></font>
										</td>
										<td align="right" class="text11">Varens pris&nbsp;(&Sigma;):&nbsp;
											<input tabindex=-1 align="right" type="text" readonly class="inputText11BlueBoldReadOnly" size="12" maxlength=20" value='${model.recordItemContainerTopic.calculatedItemLinesTotalAmount}'>
										</td>
										<td align="right" class="text11">Forskel:&nbsp;
											<input tabindex=-1 align="right" type="text" readonly
												<c:choose>
												<c:when test="${fn:contains(model.recordItemContainerTopic.diffItemLinesTotalAmountWithInvoiceTotalAmount,'-')}">
													class="inputText11RedBoldReadOnly" 
												</c:when>
												<c:otherwise>
													class="inputText11BlueBoldReadOnly"
												</c:otherwise>
												</c:choose>
												size="12" maxlength=20" value='${model.recordItemContainerTopic.diffItemLinesTotalAmountWithInvoiceTotalAmount}'>
										</td>
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
								
								<table id="containerdatatableTable" width="95%" cellspacing="2" align="left" >
								<tr>
								<td class="text11">
							
								<table id="tblItemLines" width="95%" class="display compact cell-border">
									<thead>
									<tr style="background-color:#DDDDDD">
									    <th class="text11">&nbsp;Lin.nr.&nbsp;</th>
									    <th class="text11">&nbsp;Opd.&nbsp;</th>   
									    <th class="text11"><spring:message code="systema.skat.import.item.list.label.dkiv_28b.purchaseSellerInvoice"/>&nbsp;</th>
					                    <th class="text11" nowrap><spring:message code="systema.skat.import.item.list.label.dkiv_32.varepostNr"/>&nbsp;</th>   
					                    <th class="text11" nowrap>&nbsp;<spring:message code="systema.skat.import.item.list.label.dkiv_34.oprLand"/>&nbsp;</th>
					                    <th class="text11" nowrap>&nbsp;<spring:message code="systema.skat.import.item.list.label.dkiv_331.varekod"/>&nbsp;</th>
					                    <th class="text11" nowrap>&nbsp;<spring:message code="systema.skat.import.item.list.label.dkiv_36.preference"/>&nbsp;</th>
					                    <th class="text11" nowrap>&nbsp;<spring:message code="systema.skat.import.item.list.label.dkiv_37.procedure"/>&nbsp;</th>
					                    <th class="text11">&nbsp;<spring:message code="systema.skat.import.item.list.label.dkiv_35.bruttov"/>&nbsp;</th>
					                    <th class="text11">&nbsp;<spring:message code="systema.skat.import.item.list.label.dkiv_38.nettov"/>&nbsp;</th>
					                    <th class="text11">&nbsp;<spring:message code="systema.skat.import.item.list.label.dkiv_412.supplEnhVerdi"/>&nbsp;</th>
					                    <th class="text11">&nbsp;<spring:message code="systema.skat.import.item.list.label.dkiv_313a.kolliAntal"/></th>
					                    <th class="text11">&nbsp;<spring:message code="systema.skat.import.item.list.label.dkiv_315a.vareDescription"/>&nbsp;</th>
					                    <th class="text11">&nbsp;Avg / <spring:message code="systema.skat.import.item.list.label.dkiv_46.statValue"/></th>
					                    <th class="text11" nowrap><spring:message code="systema.skat.import.item.list.label.dkiv_42.varansPris"/>&nbsp;</th>
					                    <th class="text12" >&nbsp;<spring:message code="systema.skat.import.item.list.label.dkerr.error"/>&nbsp;</th>
					                    <c:if test="${model.status == 'M' || empty model.status || model.status == '10' || model.status == '20'|| model.status == '40'}">
				                    		<th align="center" class="text11" nowrap>Fjern</td>
					                    </c:if>
					               </tr> 
					               </thead>
					               <tbody>
				 					  <c:forEach items="${model.list}" var="record" varStatus="counter">    
							               <c:choose>           
							                   <c:when test="${counter.count%2==0}">
							                       <tr id="parentItemListTableRowNr_${counter.count}" class="tableRow" height="20" >
							                   </c:when>
							                   <c:otherwise> 
							                       <tr id="parentItemListTableRowNr_${counter.count}" class="tableOddRow" height="20" >
							                   </c:otherwise>
							               </c:choose>
							               
							               <td width="4%" class="text11" align="center">${record.dkiv_syli}</td>
							               <td width="4%" class="text11" align="center">&nbsp;
							               		<%--<a id="recordUpdate_${counter.count}_${record.sviv_vano}" href="#" onClick="getItemData(this);">${record.sviv_syli} --%>
							               		<a tabindex=-1 title="${counter.count}" id="recordUpdate_${record.dkiv_syli}_${record.dkiv_32}" href="#" onClick="getItemData(this);">
							               			<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">&nbsp;
							               		</a>
							               </td>
							               <td class="text11" >&nbsp;${record.dkiv_28b}</td>
							               <td width="2%" class="text11" >&nbsp;${record.dkiv_32}</td>
							               <td class="text11" >&nbsp;${record.dkiv_34}</td>
							               <td class="text11" >&nbsp;${record.dkiv_331}</td>
							               <td class="text11" >&nbsp;${record.dkiv_36}</td>
							               <td class="text11" >&nbsp;${record.dkiv_37}</td>
							               <td class="text11" >&nbsp;${record.dkiv_35}</td>
							               <td class="text11" >&nbsp;${record.dkiv_38}</td>
							               <td class="text11" >&nbsp;${record.dkiv_412}</td>
							               <td class="text11" >&nbsp;${record.dkiv_313a}</td>
							               <td class="text11"><div style="width:120px">&nbsp;${record.dkiv_315a}</div></td>
							               <td class="text11" align="left">&nbsp;${record.dkiv_46}
							               		<%--
							               		<font class="text12OrangeBold" onMouseOver="showPop('avgifterReadOnly_info_${record.dkiv_syli}');" onMouseOut="hidePop('avgifterReadOnly_info_${record.dkiv_syli}');" alt="info">
							               		 <img valign="bottom" src="resources/images/infoOrange.png" width="12px" height="12px" border="0" alt="avg.info">&nbsp;
							               		</font>/
							               	   		
								               <span class="popupWithInputTextGrayBg" style="position:absolute; left:620px; top:300px;" id="avgifterReadOnly_info_${record.dkiv_syli}" >
								           		<div class="text10" align="left" >
								           	 		<table cellspacing="0" border="0" cellpadding="0">
							           					<tr>
							           						<td colspan="5" class="text12OrangeBold">Afgifter [Sker på SKAT]</td>
							           					</tr>
							           					<tr height="10"><td></td></tr>
							           					<tr>
							           						<td colspan="2" class="text14">Linjenr&nbsp;<b>${record.dkiv_syli}</b></td>
							           						<td colspan="3" class="text14">Varukod&nbsp;<b>${record.dkiv_331}</b></td>
							           					</tr>
							           					<tr height="10"><td></td></tr>
							           					<tr class="tableHeaderField" height="20" valign="left">
										    					<td class="tableHeaderFieldFirst">&nbsp;Art&nbsp;</td>   
										    					<td class="tableHeaderField">&nbsp;Grundlag&nbsp;</td>   
										    					<td class="tableHeaderField">&nbsp;Sats&nbsp;</td>   
										    					<td class="tableHeaderField">&nbsp;Beløb&nbsp;</td>   
										    					<td class="tableHeaderField">&nbsp;Ber.art&nbsp;</td>   
										    					
										    				</tr>	
										    				<tr class="tableRow" >
							           						<td class="tableCellFirst" >&nbsp;${record.dkiva_abk1}</td>
							           						<td class="tableCell" >&nbsp;${record.dkiva_abg1}</td>
							           						<td class="tableCell" >&nbsp;${record.dkiva_abs1}</td>
							           						<td class="tableCell" >&nbsp;${record.dkiva_abb1}</td>
							           						<td class="tableCell" >&nbsp;${record.dkiva_aba1}</td>
							           						
							           					</tr>
							           					
														<tr class="tableRow" >
							           						<td class="tableCellFirst" >&nbsp;${record.dkiva_abk2}</td>
							           						<td class="tableCell" >&nbsp;${record.dkiva_abg2}</td>
							           						<td class="tableCell" >&nbsp;${record.dkiva_abs2}</td>
							           						<td class="tableCell" >&nbsp;${record.dkiva_abb2}</td>
							           						<td class="tableCell" >&nbsp;${record.dkiva_aba2}</td>
							           						
							           					</tr>
														<tr class="tableRow" >
							           						<td class="tableCellFirst" >&nbsp;${record.dkiva_abk3}</td>
							           						<td class="tableCell" >&nbsp;${record.dkiva_abg3}</td>
							           						<td class="tableCell" >&nbsp;${record.dkiva_abs3}</td>
							           						<td class="tableCell" >&nbsp;${record.dkiva_abb3}</td>
							           						<td class="tableCell" >&nbsp;${record.dkiva_aba3}</td>
							           						
							           					</tr>
														<tr class="tableRow" >
							           						<td class="tableCellFirst" >&nbsp;${record.dkiva_abk4}</td>
							           						<td class="tableCell" >&nbsp;${record.dkiva_abg4}</td>
							           						<td class="tableCell" >&nbsp;${record.dkiva_abs4}</td>
							           						<td class="tableCell" >&nbsp;${record.dkiva_abb4}</td>
							           						<td class="tableCell" >&nbsp;${record.dkiva_aba4}</td>
							           						
							           					</tr>
														<tr class="tableRow" >
							           						<td class="tableCellFirst" >&nbsp;${record.dkiva_abk5}</td>
							           						<td class="tableCell" >&nbsp;${record.dkiva_abg5}</td>
							           						<td class="tableCell" >&nbsp;${record.dkiva_abs5}</td>
							           						<td class="tableCell" >&nbsp;${record.dkiva_abb5}</td>
							           						<td class="tableCell" >&nbsp;${record.dkiva_aba5}</td>
							           						
							           					</tr>
							           					<tr height="10"><td></td></tr>
							           					
							           				</table>	
							           			</div>	
							           			</span>		 
							           			--%>
							               	</td>
							               <td class="text11">&nbsp;${record.dkiv_42}</td>
							               <td align="center" class="text11">&nbsp;
							               		<c:if test="${not empty record.dkiv_err}">
							               			<img src="resources/images/redFlag.png" width="18px" height="18px" border="0" alt="remove">
							               		</c:if>
							               </td>
							               <c:if test="${model.status == 'M' || empty model.status || model.status == '10' || model.status == '20'|| model.status == '40'}">	
								               <td class="text11" align="center" nowrap>&nbsp;
								               	<a onclick="javascript:return confirm('Er du sikker på at du vil slette denne?')" tabindex=-1 href="skatimport_edit_items.do?action=doDelete&avd=${record.dkiv_syav}&opd=${record.dkiv_syop}&lin=${record.dkiv_syli}&fabl=${XX.svih_fabl}">
								               		<img valign="bottom" src="resources/images/delete.gif" border="0" alt="remove">
								               	</a>	
								               </td>
							               </c:if>
							               
							            </tr>
								        <%-- <c:set var="numberOfItemLinesInTopic" value="${counter.count}" scope="request" />  --%>
								        <c:set var="numberOfItemLinesInTopic" value="${record.dkiv_syli}" scope="request" /> 
								        </c:forEach>
								    </tbody>    
						        </table>
								</td>
								</tr>
								</table>
						</form>
				</td>
			</tr>
			
			<tr height="3"><td></td></tr>
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
			<%-- ------------------------------------------------- --%>
           	<%-- DETAIL Section - Create Item line PRIMARY SECTION --%>
           	<%-- ------------------------------------------------- --%>
           	<tr>
	 			<td >
	 			<form name="tdsImportEditTopicItemForm" id="tdsImportEditTopicItemForm" method="post">
				 	<%--Required key parameters from the Topic parent --%>
				 	<input type="hidden" name="action" id="action" value='doUpdate'/>
				 	<input type="hidden" name="opd" id="opd" value="${model.opd}"/>
				 	<input type="hidden" name="avd" id="avd" value="${model.avd}"/>
				 	<input type="hidden" name="sign" id="sign" value="${model.sign}"/>
				 	<input type="hidden" name="refnr" id="refnr" value="${dkih_07}"/>
				 	<input type="hidden" name="status" id="status" value="${model.status}"/>
				 	<input type="hidden" name="datum" id="datum" value="${model.datum}"/>
				 	<input type="hidden" name="fabl" id="fabl" value="${recordTopicSkat.dkih_222}"/>
				 	
				 	<input type="hidden" name="dkiv_syli" id="dkiv_syli" value=''/>
				 	<input type="hidden" name="dkiv_32" id="dkiv_32" value="${model.record.dkiv_32}"/>
				 	
				 	<input type="hidden" name="numberOfItemLinesInTopic" id="numberOfItemLinesInTopic" value="${numberOfItemLinesInTopic}" />
				 	<input type="hidden" name="lastSelectedItemLineNumber" id="lastSelectedItemLineNumber" value="${model.recordItemContainerTopic.lastSelectedItemLineNumber}" />
				 	
				 	<%-- Topic ITEM CREATE --%>
	 				<table width="100%" align="center" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="15">
				 			<td class="text12White" align="left" >
				 				<b>&nbsp;&nbsp;V<label onClick="showPop('debugPrintlnAjaxItemFetchAdmin');" >a</label>repost&nbsp;</b>
				 				
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
				 				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				 				<c:choose>
					 				<c:when test="${not empty model.record.dkiv_syli}">
						 				<input title="from model" tabindex=-1 align="center" class="text12BoldLightGreenForItemLinenr" readonly type="text" name="lineNr" id="lineNr" size="3" value="${model.record.dkiv_syli}">
									</c:when>
									<c:otherwise>
				 						<input title="from session" tabindex=-1 align="center" class="text12BoldLightGreenForItemLinenr" readonly type="text" name="lineNr" id="lineNr" size="3" value="${dkiv_syli_SESSION}">
									</c:otherwise>
								</c:choose>
			 				</td>
		 				</tr>
	 				</table>
					<table width="100%" align="center" class="formFrame" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="15"><td class="text" align="left"></td></tr>
				 		<tr>
					 		<td>
						 		<table width="100%" border="0" cellspacing="0" cellpadding="0">
							 		<tr>
							 			<td class="text12" align="left">
							            <img onMouseOver="showPop('33_info');" onMouseOut="hidePop('33_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
							            <b>33.</b>
							            <c:if test="${recordTopicSkat.dkih_aart=='02' || recordTopicSkat.dkih_aart=='04' || recordTopicSkat.dkih_aart=='07'}">
							            		<font class="text16RedBold" >*</font>
							            </c:if>
							            <span title="dkiv_331">Varekode</span>
							            
							            <div class="text11" style="position: relative;" align="left">
										<span style="position:absolute;top:2px; width:250px;" id="33_info" class="popupWithInputText text11"  >
						           			<b>33. Varekoder</b>
						           			<br/><br/>
											Her angiver klarereren den 10-cifrede varekode i henhold tilToldtariffen. I nogle tilfælde skal der yderligere angives op til to 4-cifrede tillægskoder, fx når der er tale om antidumpingtold, landbrugselement mv. 
											Se Toldtariffens vejledning om anvendelse af varekoder og tillægskoder og afsnittet om MIO.						           			<br/><br/>
						           			I vissa fall ska du ange en eller flera tilläggskoder, exempelvis för varor som är föremål för antidumpning.
						           			<br/><br/>
						           			I visse tilfælde kan eller skal der anvendes en særlig varekode. Disse særlige varekoder finder man i den elektroniske udgave af tariffen på Toldtariffen. 
						           			Alle særlige varekoder starter med 99.
										</span>
										</div>
				 						
							            </td>
							 			
							            	<td class="text12" align="left">
							            <img onMouseOver="showPop('36_info');" onMouseOut="hidePop('36_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info" >
				 						<b>36.</b>
				 						<c:if test="${recordTopicSkat.dkih_aart=='03' || recordTopicSkat.dkih_aart=='04' || recordTopicSkat.dkih_aart=='07'}">
							            		<font class="text16RedBold" >*</font>
							            </c:if>
				 						<span title="dkiv_36">Præference</span>
				 						<div class="text11" style="position: relative;" align="left">
										<span style="position:absolute;top:2px; width:250px;" id="36_info" class="popupWithInputText text11"  >
						           			<b>36. Præference</b>
						           			 <br><br>
											 Her skal klarereren angive med en trecifret kode, om man anmoder om præferencetoldbehandling, og i givet fald hvilken type præference, 
						           			 der er tale om. Se koderne på SKATshjemmeside. 
										</span>
										</div>
				 						</td>
							            
							            <td class="text12" align="left">
							            <img onMouseOver="showPop('37_1_info');" onMouseOut="hidePop('37_1_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 						<b>37.</b>
				 						<c:if test="${recordTopicSkat.dkih_aart=='03' || recordTopicSkat.dkih_aart=='04' || recordTopicSkat.dkih_aart=='07'}">
							            		<font class="text16RedBold" >*</font>
							            </c:if>
				 						<span title="dkiv_37">Procedure</span>
							            <div class="text11" style="position: relative;" align="left">
										<span style="position:absolute;top:2px; width:250px;" id="37_1_info" class="popupWithInputText text11"  >
						           			<br>
						           			<b>37. Procedure</b>
						           			<br/><br/>
						           			Her skal klarereren angive den 7-cifrede kode for den procedure, som varerne ønskes toldbehandlet efter.
						           			<br/><br/>
						           			De to første cifre er koden for den ønskede procedure, fx fortoldning til fri omsætning og frit forbrug (40) eller aktiv forædling (41 eller 51).
						           			<br/><br/>
											De to næste cifre er koden for en eventuel forudgående procedure. En forudgående procedure er en procedure, som varerne har været underkastet tidligere, 
											og som har relevans for indførslen, fx udført til passiv forædling (21) eller oplagt på toldoplag (71). 
											Hvis varerne ikke har været underkastet nogen relevant procedure, er cifrene 00.
						           			<br/><br/>
						           			De tre sidste cifre er:
						           			<ul>
						           				<li> 
						           					En nærmere defineret EU-procedure, angivet ved en kode bestående af et bogstav efterfulgt af et løbenummer. Som et eksempel kan nævnes, at procedurekoden for varer, 
						           					der returneres til Danmark fra et land uden for EU, og som angives som returvarer, vil være 40.10.F01, eller
					           					</li>
						           				<li>
						           					En national kode bestående af tre tal.
						           				</li>
						           			</ul>
						           			<br/>
										</span>
										</div>
										</td>
							            
							            <td class="text12" align="left"><b>42.</b>
				 						<c:if test="${recordTopicSkat.dkih_aart=='03' || recordTopicSkat.dkih_aart=='04' || recordTopicSkat.dkih_aart=='07'}">
							            		<font class="text16RedBold" >*</font>
							            </c:if>
							            	<span title="dkiv_42">Varens pris:&nbsp;</span>
						            		</td>
						            		
							            <td class="text12" align="left">
							            <img onMouseOver="showPop('35_info');" onMouseOut="hidePop('35_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 						<b>35.</b>
				 						<c:if test="${recordTopicSkat.dkih_aart=='01' || recordTopicSkat.dkih_aart=='02' || recordTopicSkat.dkih_aart=='04' || 
				 									  recordTopicSkat.dkih_aart=='05' || recordTopicSkat.dkih_aart=='07'}">
							            		<font class="text16RedBold" >*</font>
							            </c:if>
				 						<span title="dkiv_35">Brut.(kg)</span>
							            <div class="text11" style="position: relative;" align="left">
										<span style="position:absolute;top:2px; width:250px;" id="35_info" class="popupWithInputText text11"  >
							           			<br/>
							           			<b>35. Bruttomasse (kg)</b>
							           			<br/><br/>
												Klarereren skal anføre bruttovægten for den enkelte varepost i kg og g efter følgende regler:											
												<br/>
												<ul>
								           			<li>Hvis bruttovægten er under 1 kg, skal man angive vægten i g (fx 0,867).</li>
								           			<li>Hvis bruttovægten er over 1 kg, kan man enten angive vægten i hele kg eller i kg og g med tre decimaler (fx 22 eller 22,487).</li>
								           		</ul>
							           			<br/>
							           			<b>Bruttomassen</b> betyder den samlede vægt af varerne inklusive al emballage, dog ikke transportmateriel, fx containere.
										</span>
										</div>
										</td>
										<td class="text12" align="left">
										<b>&nbsp;38.</b>
										<c:if test="${recordTopicSkat.dkih_aart=='03' || recordTopicSkat.dkih_aart=='04' || recordTopicSkat.dkih_aart=='05' || 
				 									  recordTopicSkat.dkih_aart=='07' || recordTopicSkat.dkih_aart=='08'}">
							            		<font class="text16RedBold" >*</font>
							            </c:if>
										<span title="dkiv_38">Net.(kg)</span>
										</td>
										<td class="text12" align="left" >
							            <img onMouseOver="showPop('31_gods_info');" onMouseOut="hidePop('31_gods_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 						<b>31.1</b>
				 						<c:if test="${recordTopicSkat.dkih_aart=='02' || recordTopicSkat.dkih_aart=='04' || 
				 									  recordTopicSkat.dkih_aart=='05' || recordTopicSkat.dkih_aart=='07'}">
							            		<font class="text16RedBold" >*</font>
							            </c:if>
							            <span title="dkiv_311a">Kolli mærke&nbsp;</span>
							            <div class="text11" style="position: relative;" align="left">
										<span style="position:absolute;top:2px; width:250px;" id="31_gods_info" class="popupWithInputText text11"  >
						           			<br/>
						           			<b>Kolli Mærke</b>
											<br/><br/>
						           			Her oplyses emballagernes mærke og numre. For uemballerede varer skal klarereren anføre "in bulk".
										</span>
										</div>
										</td>
							        </tr>
							        <tr>
							        		
							        		<td class="text12" align="left">
							            		<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="dkiv_331" id="dkiv_331" size="14" maxlength="10" value="${model.record.dkiv_331}">
							            		<img id="imgTaricVarukodSearch" style="cursor:pointer;" src="resources/images/find.png" border="0" alt="search" onClick="showPop('searchTaricCodesDialog');">
								            <%-- ======================================================== --%>
							            		<%-- Here we have the search Taric codes popup window --%>
							            		<%-- ======================================================== --%>
							            		<span style="position:absolute; left:300px; top:450px; width:500px; height:210px;" id="searchTaricCodesDialog" class="popupWithInputText"  >
								           		<div class="text10" align="left">
								           			<table>
									           			<tr>
									           			<td>
										           			<table>
										           				<tr>
																	<td class="text11">&nbsp;Varukod</td>
																	<td class="text11">&nbsp;<input type="text" class="inputText" name="search_dkiv_331" id="search_dkiv_331" size="10" maxlength="10" value=""></td>
																</tr>
											           			<tr>
											           				<td align="right">&nbsp;<button name="searchTaricCode" id="searchTaricCode" class="buttonGray" type="button" onClick="searchTaricVarukod();"><spring:message code="systema.skat.import.search"/></button></td>
											           				<td class="text11">&nbsp;</td>
												           			
												           		</tr>
												           		<tr height="4"><td ></td></tr>
											           		</table>
										           		</td>
										           		</tr>
														
														<tr>
									           			<td>
										           			<table>							           		
												           		<tr>
											           				<td class="text11">&nbsp;Lista</td>
												           			<td>&nbsp;</td>
												           		</tr>
												           		<tr>
																	<td colspan="2">&nbsp;
																		<select class="text11" id="taricVarukodList" name="taricVarukodList" size="5" onDblClick="hidePop('searchTaricCodesDialog');">
						 													<option selected value="">-vælg-</option>
						 							 					</select>
																	</td>
																</tr>
										           			</table>
									           			</td>
									           			</tr>
															
														<tr>
									           			<td>								           			
															<table width="100%" align="left" border="0">
																<tr align="left" >
																	<td >&nbsp;<button name="searchTaricCodesClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('searchTaricCodesDialog');"><spring:message code="systema.skat.import.ok"/></button></td>
																</tr>
															</table>
														</td>
														</tr>
													</table>
												</div>
											</span>	
							            </td>
										
										
										<td class="text12" align="left" nowrap>
											<select name="dkiv_36" id="dkiv_36">
						 						<option value="">-vælg-</option>
							 				  	<c:forEach var="code" items="${model.preferenceCodeList}" >
							 				  		<option value="${code.dkkd_kd}"<c:if test="${model.record.dkiv_36 == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
												</c:forEach>  
											</select>
											<a tabindex="-1" class="text14" target="_blank" href="${model.skatPreference36CodesURL.value}" onclick="${model.skatPreference36CodesURL.windowOpenDimensions}" >
						            			<img style="cursor:pointer;" src="resources/images/find.png" border="0" alt="search" >
						            			</a>																	 			
										</td>
										
										<td align="left" nowrap>
											<select name="dkiv_37" id="dkiv_37">
						 						<option value="">-vælg-</option>
							 				  	<c:forEach var="code" items="${model.procedureKoderR37CodeList}" >
							 				  		<option value="${code.dkkd_kd}"<c:if test="${model.record.dkiv_37 == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
												</c:forEach>  
											</select>	
											<a tabindex="-1" class="text14" target="_blank" href="${model.skatProcedure37CodesURL.value}" onclick="${model.skatProcedure37CodesURL.windowOpenDimensions}" >
						            			<img style="cursor:pointer;" src="resources/images/find.png" border="0" alt="search" >
						            			</a>	
						 				</td>
						 				
						 				<td class="text12" align="left" nowrap>
							            		<input onKeyPress="return amountKey(event)" type="text" class="inputText" name="dkiv_42" id="dkiv_42" size="14" maxlength="13" value="${model.record.dkiv_42}">
							            	</td>
						 				<td class="text12" align="left"><input onKeyPress="return amountKey(event)" type="text" class="inputText" name="dkiv_35" id="dkiv_35" size="10" maxlength="12" value="${model.record.dkiv_35}"></td>
										<td class="text12" align="left"><input onKeyPress="return amountKey(event)" type="text" class="inputText" name="dkiv_38" id="dkiv_38" size="10" maxlength="9" value="${model.record.dkiv_38}"></td>
										<td class="text12" align="left" ><input type="text" class="inputText" name="dkiv_311a" id="dkiv_311a" size="16" maxlength="16" value="${model.record.dkiv_311a}"></td>
										
							        </tr>

							        <tr height="10"><td class="text" align="left" colspan="12"><hr></td></tr>
							        
									<tr>
										
										<td class="text12">
										<img onMouseOver="showPop('31_kantal_info');" onMouseOut="hidePop('31_kantal_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
										<span id="kotaRubrik">
											<b>31.3</b>
										</span>
										<c:if test="${recordTopicSkat.dkih_aart=='02' || recordTopicSkat.dkih_aart=='04' || recordTopicSkat.dkih_aart=='07'}">
							            		<font class="text16RedBold" >*</font>
							            </c:if>
				 						<span title="dkiv_313a">Kolli antal</span>
										<div class="text11" style="position: relative;" align="left">
										<span style="position:absolute;top:2px; width:250px;" id="31_kantal_info" class="popupWithInputText text11"  >
							           		<b>31.3 Kolliantal</b>
						           			<br/><br/>
						           			Her anfører klarereren det samlede antal kolli, der er omfattet af vareposten.
										</span>
										</div>
							            </td>
							            
										<td class="text12">
										<img onMouseOver="showPop('31_kslag_info');" onMouseOut="hidePop('31_kslag_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 						<b>31.4</b>
										<c:if test="${recordTopicSkat.dkih_aart=='02' || recordTopicSkat.dkih_aart=='04' || recordTopicSkat.dkih_aart=='07'}">
							            		<font class="text16RedBold" >*</font>
							            </c:if>
										<span title="dkiv_314a">Kolli art</span>
										
										<div class="text11" style="position: relative;" align="left">
										<span style="position:absolute;top:2px; width:250px;" id="31_kslag_info" class="popupWithInputText text11"  >
						           			<br/>
						           			<b>31.4 Kolli art</b>
											<br/>
						           			Her beskriver klarereren, hvordan varerne er pakket. Emballagekoderne findes på SKATs hjemmeside.
										</span>
										</div>
										</td>
							            
										<td class="text12" align="left" colspan="2">
										<img onMouseOver="showPop('31_varubesk_info');" onMouseOut="hidePop('31_varubesk_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 						<b>31.5</b>
										<c:if test="${recordTopicSkat.dkih_aart=='01' || recordTopicSkat.dkih_aart=='02' || recordTopicSkat.dkih_aart=='04' || recordTopicSkat.dkih_aart=='07'}">
							            		<font class="text16RedBold" >*</font>
							            </c:if>
										<span title="dkiv_315a">Varebeskrivelse&nbsp;</span>
										<div class="text11" style="position: relative;" align="left">
										<span style="position:absolute;top:2px; width:250px;" id="31_varubesk_info" class="popupWithInputText text11"  >
						           			<br/>
						           			<b>Varebeskrivelse</b>
											<br/><br/>
						           			En varebeskrivelse er en beskrivelse, der er tilstrækkelig entydig til at fastslå varekoden i Toldtariffen, og som muliggør en øjeblikkelig og sikker identifikation af varen.							           			
										</span>
										</div>
										</td>
										<td class="text12" align="left" colspan="2">
										<img onMouseOver="showPop('41_info');" onMouseOut="hidePop('41_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 						<b>41.</b><span title="dkiv_412">Suppl.enh.verdi</span>/<span title="dkiv_411">enh.</span>
				 							<a tabindex="-1" class="text14" target="_blank" href="${model.skatUnitOfMeasureCodesURL.value}" onclick="${model.skatUnitOfMeasureCodesURL.windowOpenDimensions}" >
						            				<img style="cursor:pointer;" src="resources/images/find.png" border="0" alt="search" >
						            			</a>	
											<div class="text11" style="position: relative;" align="left">
											<span style="position:absolute;top:2px; width:250px;" id="41_info" class="popupWithInputText text11"  >
						           		
													<b>41. Supplerende enheder</b>
													<br/><br/>
													Klarereren skal anføre supplerende mængde i den enhed, som Toldtariffen kræver for den pågældende varekode, fx stk., par, liter, 
													MWh eller m2. Man anfører mængderne uden decimaler.
													<br/><br/>
													Ved indførsel af maskiner, apparater mv. samt visse arter transportmateriel, henhørende under Toldtariffens kap. 84-89 (afsnit XVI og XVII), 
													der leveres i flere komponenter på forskellige tidspunkter, 
													skal klarereren udfylde rubrikken efter reglerne for særlige varebevægelser, se afsnit F.A.9 Fortoldning.
													<br/><br/>
													Klarereren skal kun udfylde rubrikken, hvis der er krav om supplerende enhed ifølge Toldtariffen, 
													eller når der er tale om indførsel efter reglerne for særlige varebevægelser.
											</span>
											</div>
										</td>
										<td class="text12" align="left">&nbsp;&nbsp;<span title="dkiv_28b">Køb/Salgsfakt.</span></td>
							        </tr>
									<tr>
					           			<td class="text11" valign="bottom">
											<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="dkiv_313a" id="dkiv_313a" size="7" maxlength="6" value="${model.record.dkiv_313a}">
										</td>
										<td align="left" nowrap>
					            			<select name="dkiv_314a" id="dkiv_314a">
							            		<option value="">-vælg-</option>
							 				  	<c:forEach var="code" items="${model.emballageCodeList}" >
			                                	 	<option value="${code.dkkd_kd}"<c:if test="${model.record.dkiv_314a == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
												</c:forEach> 
											</select>
											<a tabindex="-1" class="text14" target="_blank" href="${model.skatKolliart31CodesURL.value}" onclick="${model.skatKolliart31CodesURL.windowOpenDimensions}" >
						            			<img style="cursor:pointer;" src="resources/images/find.png" border="0" alt="search" >
						            			</a>											
				            				</td> 										
										<td class="text12" align="left" colspan="2"><input type="text" class="inputText" name="dkiv_315a" id="dkiv_315a" size="40" maxlength="45" value="${model.record.dkiv_315a}">
							            &nbsp;&nbsp;<button name="itemDescriptionExtraInformationButton" class="buttonGray" type="button" onClick="showPop('itemDescriptionExtraInformation');" >Mere...</button>
								        <span style="position:absolute; left:280px; top:500px; width:350px; height:300px;" id="itemDescriptionExtraInformation" class="popupWithInputText"  >
								           		<div class="text10" align="left">
								           			<table>
								           				<tr>
										           			<td class="text12">
																<b>Flere varebeskrivelser</b>
															</td>
														</tr>
														
									           			<tr>
										           			<td class="text11">
																&nbsp;31.2 Varebeskrivelse<input type="text" class="inputText" name="dkiv_315b" id="dkiv_315b" size="35" maxlength="45" value="${model.record.dkiv_315b}">
															</td>
														</tr>
														<tr>
										           			<td class="text11">
																&nbsp;31.3 Varebeskrivelse<input type="text" class="inputText" name="dkiv_315c" id="dkiv_315c" size="35" maxlength="45" value="${model.record.dkiv_315c}">
															</td>
														</tr>
														<tr>
										           			<td class="text11">
																&nbsp;31.4 Varebeskrivelse<input type="text" class="inputText" name="dkiv_315d" id="dkiv_315d" size="35" maxlength="45" value="${model.record.dkiv_315d}">
															</td>
														</tr>
														<tr>
										           			<td class="text11">
																&nbsp;31.5 Varebeskrivelse<input type="text" class="inputText" name="dkiv_315e" id="dkiv_315e" size="35" maxlength="45" value="${model.record.dkiv_315e}">
															</td>
														</tr>
														
								           			</table>
													<table width="100%" align="left" border="0">
														<tr align="left" >
															<td class="text11"><button name="itemDescriptionExtraInformationButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('itemDescriptionExtraInformation');">&nbsp;Ok</button> 
															</td>
														</tr>
													</table>
												</div>
										</span>
										</td>
										<td class="text12" align="left" colspan="2">
											<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="dkiv_412" id="dkiv_412" size="12" maxlength="9" value="${model.record.dkiv_412}">
											&nbsp;
											<select name="dkiv_411" id="dkiv_411">
						 						<option value="">-</option>
							 				  	<c:forEach var="code" items="${model.uomKoderR411CodeList}" >
							 				  		<option value="${code.dkkd_kd}"<c:if test="${model.record.dkiv_411 == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
												</c:forEach>  
											</select>
										</td>
										<td class="text12" align="left" ><input type="text" class="inputText" name="dkiv_28b" id="dkiv_28b" size="17" maxlength="17" value="${model.record.dkiv_28b}"></td>
 							        </tr>

 							        <tr height="5"><td ></td></tr>
 							        <tr height="20"><td class="text" align="left" colspan="12"><hr></td></tr>
							        <%-- Session fields needed for the AJAX calculation av Avgifter --%>
							        <input type="hidden" name="session_dkih_12" id="session_dkih_12" value="${recordTopicSkat.dkih_12}">
							        <input type="hidden" name="session_dkih_12e" id="session_dkih_12e" value="${recordTopicSkat.dkih_12e}">
							        <input type="hidden" name="session_dkih_221" id="session_dkih_221" value="${recordTopicSkat.dkih_221}">
							        <input type="hidden" name="session_dkih_221b" id="session_dkih_221b" value="${recordTopicSkat.dkih_221b}">
							        <input type="hidden" name="session_dkih_221c" id="session_dkih_221c" value="${recordTopicSkat.dkih_221c}">
							        <input type="hidden" name="session_dkih_222" id="session_dkih_222" value="${recordTopicSkat.dkih_222}">
							        <input type="hidden" name="session_dkih_201" id="session_dkih_201" value="${recordTopicSkat.dkih_201}">
							        
							        <%-- Model fields needed for the AJAX calculation av Bilagda handlingar --%>
							        <input type="hidden" name="model_avd" id="model_avd" value="${model.avd}">
							        <input type="hidden" name="model_opd" id="model_opd" value="${model.opd}">
							        
							        <tr>
							        		<td class="text12" align="left">
							 			<img onMouseOver="showPop('34a_info');" onMouseOut="hidePop('34a_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 						<b>34.</b>
				 						<c:if test="${recordTopicSkat.dkih_aart=='02' || recordTopicSkat.dkih_aart=='04' || recordTopicSkat.dkih_aart=='07'}">
							            		<font class="text16RedBold" >*</font>
							            </c:if>
				 						<span title="dkiv_34">Opr.land</span>
										
							            <div class="text11" style="position: relative;" align="left">
										<span style="position:absolute;top:2px; width:250px;" id="34a_info" class="popupWithInputText text11"  >
						           			<b>34. Oprindelsesland</b>
						           			<br/><br/>
											Klarereren skal angive oprindelsesland med koden for det land, hvor varerne er fremstillet. Hvis fremstillingen er foregået i to eller flere lande, skal klarereren anføre det land, hvor den sidste væsentlige forarbejdning har fundet sted.						           			<br/><br/>
											Observera att koden EU endast får användas vid återimport och när du samtidigt yrkar på preferenstull, det vill säga då du i fält 37
											anger en förfarandekod som börjar på 6 eller koderna 4010 och 4071 samt en förmånskod i fält 36 som börjar på 2 eller 3.
						           			<br/><br/>
						           			Ompakning, sortering eller blanding betragtes ikke som forarbejdning, medmindre varen efter behandlingen ikke længere kan henføres til noget andet oprindelsesland end det, 
						           			hvor denne behandling har fundet sted.
						           			<br/><br/>
						           			Er der særlige regler til fastlæggelse af varers oprindelse i EU-forordninger eller internationale aftaler, fx vedrørende toldpræference, 
						           			skal klarereren anføre oprindelseslandet i overensstemmelse med disse regler. 
						           			Oprindelseslandet vil i disse tilfælde normalt fremgå af de dokumenter, som kræves fremlagt i bestemmelseslandet.
											<br/><br/>
						           			For skibe og fly, der allerede er i drift, angiver man landekoden for det land, hvor skibet eller flyet var registreret før erhvervelsen, uanset hvor det er bygget.
						           			<br/><br/>
						           			For varer, der hører under Toldtariffens kapitel 97, angiver man koden for afsendelseslandet.
										</span>
										</div>
										</td>
						        		<td class="text12" align="left">
							            <img onMouseOver="showPop('46_info');" onMouseOut="hidePop('46_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 						<b>46.</b>
				 						<c:if test="${recordTopicSkat.dkih_aart=='02' || recordTopicSkat.dkih_aart=='03' || recordTopicSkat.dkih_aart=='04' ||
				 									 recordTopicSkat.dkih_aart=='05' || recordTopicSkat.dkih_aart=='07' || recordTopicSkat.dkih_aart=='08' }">
							            		<font class="text16RedBold" >*</font>
							            </c:if>
							            <span title="dkiv_46">Statistisk værdi:&nbsp;</span>
							           
							            <div class="text11" style="position: relative;" align="left">
										<span style="position:absolute;top:2px; width:250px;" id="46_info" class="popupWithInputText text11"  >
							           			<br/>
							           			<b>46. Statistisk værdi</b>
												<br/><br/>
							           			Den statistiske værdi for den enkelte varepost svarer til varernes toldværdi. For toldfrie varer anvendes en tilsvarende værdi.
							           			<br/><br/>
												Klarereren skal angive værdien i hele danske kroner. Værdien skal være mindst 1 kr.
												<br/><br/>
												Skal der betales værditold af en vare, og har forsendelsen en samlet værdi på over 80.000 kr., skal klarereren afgive en toldværdideklaration. 
												Hvis angivelsen omfatter flere vareposter, skal man altid - uanset værdi - afgive en toldværdispecifikation. Se reglerne i afsnit F.A.8.
										</span>
										</div>
										</td>
							           	
							           	<td colspan="4" class="text12" align="left" >
								            <img onMouseOver="showPop('40_info');" onMouseOut="hidePop('40_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					 						<b>40.</b>
					 						<c:if test="${recordTopicSkat.dkih_aart=='02' || recordTopicSkat.dkih_aart=='04' ||
					 									 recordTopicSkat.dkih_aart=='05' || recordTopicSkat.dkih_aart=='07' }">
								            		<font class="text16RedBold" >*</font>
								            </c:if>
					 						<span title="dkiv_401/dkiv_402/dkiv_403">Summarisk angivelse / Forudgående dokument:</span>
								            <div class="text11" style="position: relative;" align="left">
											<span style="position:absolute;top:2px; width:250px;" id="40_info" class="popupWithInputText text11"  >
							           			<b>40. Summarisk angivelse / Forudgående dokument</b>
												<br/><br/>
												Rubrikken skal udfyldes med henvisning til eventuelt tidligere udfærdigede dokumenter, der er relevante for indførslen (fx fragtbrev, TIR forsendelse, EU forsendelse mv.).
												<br/><br/>
												Her er et eksempel på udfyldning af rubrikken:
												<br/>
												Det foregående dokument er et T1-forsendelsesdokument, og bestemmelsestoldstedet har anvendt 
												nummeret "238544". Koden vil i dette tilfælde være "Z 821-238544". 
												("Z for det foregående dokument, "821" for forsendelsesproceduren og "238544" for dokumentets registreringsnummer.)
												<br/><br/>
												Koderne findes på SKATs hjemmeside.
												<br/><br/>
												Kategorier
												<ul>
													<li><b>X</b> Summarisk deklaration</li>
													<li><b>Y</b> Ursprunglig deklaration</li>
													<li><b>Z</b> Foregående dokument</li>
													
												</ul>
											</span>
											</div>
										</td>
							        </tr>
							        <tr>
						        			<td align="left" nowrap>
								            	<select name="dkiv_34" id="dkiv_34">
						 						<option value="">-vælg-</option>
							 				  	<c:forEach var="country" items="${model.countryCodeList}" >
							 				  		<option value="${country.dkkd_kd}"<c:if test="${model.record.dkiv_34 == country.dkkd_kd}"> selected </c:if> >${country.dkkd_kd}</option>
												</c:forEach>  
											</select>
											<a tabindex="-1" class="text14" target="_blank" href="${model.skatLandCodesURL.value}" onclick="${model.skatLandCodesURL.windowOpenDimensions}" >
						            			<img style="cursor:pointer;" src="resources/images/find.png" border="0" alt="search" >
						            			</a>																	 			
										</td>
								        	<td class="text12" align="left"><input onKeyPress="return numberKey(event)" type="text" class="inputText" name="dkiv_46" id="dkiv_46" size="16" maxlength="15" value="${model.record.dkiv_46}"></td>
										<td colspan="4" class="text12" align="left" nowrap>
							           			<table>
							           				<tr>
									           			<td class="text12">&nbsp;&nbsp;&nbsp;&nbsp;<span title="dkiv_401-2">40.2&nbsp;Kat./Type</span>
										           			<select name="dkiv_402a" id="dkiv_402a">
											            		<option value="">-vælg-</option>
											 				  	<c:forEach var="code" items="${model.transportdocsSummariskadocsCodeList}" >
											 				  		<option value="${code.dkkd_kd}"<c:if test="${model.record.dkiv_402a == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
																</c:forEach> 
															</select>
															<a tabindex="-1" class="text14" target="_blank" href="${model.skatTransportSummariskaDokument40CodesURL.value}" onclick="${model.skatTransportSummariskaDokument40CodesURL.windowOpenDimensions}" >
	         													<img style="cursor:pointer;" src="resources/images/find.png" border="0" alt="search" >
	         												</a>										           				
														</td>
														<td class="text12" ><span title="dkiv_403a">&nbsp;&nbsp;&nbsp;40.3&nbsp;Id</span>
															<input type="text" class="inputText" name="dkiv_403a" id="dkiv_403a" size="25" maxlength="35" value="${model.record.dkiv_403a}">
															<input class="clazz_copyElement" type="checkbox" name="dkiv_401a_copy@dkiv_402a_copy@dkiv_403a_copy" id="dkiv_401a_copy@dkiv_402a_copy@dkiv_403a_copy" value="1" >
															<font class="text11MediumBlue" >
																<label onMouseOver="showPop('dkiv_403a_copy_info');" onMouseOut="hidePop('dkiv_403a_copy_info');">Kopi</label>
															</font>
															<div class="text11" style="position: relative;" align="left" >
									 						<span style="position:absolute; left:0px; top:0px;" id="dkiv_403a_copy_info" class="popupWithInputText"  >
									 							<font class="text11">
												           			Kopiere værdien til alle efterfølgende vareposter
											           			</font>
															</span>
															</div>
														</td>
														
														<td class="text12" >
															&nbsp;&nbsp;
															<button name="itemSummariskaDocsExtraFieldsButton" class="buttonGray" type="button" onClick="showPop('itemSummariskaDocsExtraFields');" >Mere...</button>
													        <span style="position:absolute; left:250px; top:400px; width:800px; height:550px;" id="itemSummariskaDocsExtraFields" class="popupWithInputText"  >
													           		<div class="text10" align="left">
													           			<table>
													           				<tr>
															           			<td colspan="2" class="text12">
																					<b>40.</b>Sum.ang./Forudg.dok.
																				</td>
																			</tr>
																			<tr height="8"><td></td></tr>
																			<tr>
																				<td class="text11" align="center">
																					Kat./Type
																				</td>
															           			<td class="text11">
																					&nbsp;&nbsp;&nbsp;Id
																				</td>
																				<td class="text11" width="20px">
																					&nbsp;
																				</td>
																				<td class="text11" align="center">
																					Kat./Type
																				</td>
															           			<td class="text11">
																					&nbsp;&nbsp;&nbsp;Id
																				</td>
																				
																			</tr>
																			<tr>
																				<td 	align="right"><b>2.</b>
																					<select name="dkiv_402b" id="dkiv_402b">
																	            		<option value="">-vælg-</option>
																	 				  	<c:forEach var="code" items="${model.transportdocsSummariskadocsCodeList}" >
																	 				  		<option value="${code.dkkd_kd}"<c:if test="${model.record.dkiv_402b == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
																						</c:forEach> 
																					</select>
																				</td>
															           			<td class="text11">
																					&nbsp;<input type="text" class="inputText" name="dkiv_403b" id="dkiv_403b" size="30" maxlength="35" value="${model.record.dkiv_403b}">
																					<input class="clazz_copyElement" type="checkbox" name="dkiv_401b_copy@dkiv_402b_copy@dkiv_403b_copy" id="dkiv_401b_copy@dkiv_402b_copy@dkiv_403b_copy" value="1" ><font class="text11MediumBlue">Kopi</font>
																				</td>
																				<td class="text11" width="20px">
																					&nbsp;
																				</td>
																				
																				<td align="right"><b>3.</b>
																					<select name="dkiv_402c" id="dkiv_402c">
																	            		<option value="">-vælg-</option>
																	 				  	<c:forEach var="code" items="${model.transportdocsSummariskadocsCodeList}" >
																	 				  		<option value="${code.dkkd_kd}"<c:if test="${model.record.dkiv_402c == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
																						</c:forEach> 
																					</select>
																				</td>
															           			<td class="text11">
																					&nbsp;<input type="text" class="inputText" name="dkiv_403c" id="dkiv_403c" size="30" maxlength="35" value="${model.record.dkiv_403c}">
																					<input class="clazz_copyElement" type="checkbox" name="dkiv_401c_copy@dkiv_402c_copy@dkiv_403c_copy" id="dkiv_401c_copy@dkiv_402c_copy@dkiv_403c_copy" value="1" ><font class="text11MediumBlue">Kopi</font>
																				</td>
																			</tr>
																			
																			<tr>
																				<td align="right"><b>4.</b>
																					<select name="dkiv_402d" id="dkiv_402d">
																	            		<option value="">-vælg-</option>
																	 				  	<c:forEach var="code" items="${model.transportdocsSummariskadocsCodeList}" >
																	 				  		<option value="${code.dkkd_kd}"<c:if test="${model.record.dkiv_402d == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
																						</c:forEach> 
																					</select>
																				</td>
															           			<td class="text11">
																					&nbsp;<input type="text" class="inputText" name="dkiv_403d" id="dkiv_403d" size="30" maxlength="35" value="${model.record.dkiv_403d}">
																					<input class="clazz_copyElement" type="checkbox" name="dkiv_401d_copy@dkiv_402d_copy@dkiv_403d_copy" id="dkiv_401d_copy@dkiv_402d_copy@dkiv_403d_copy" value="1" ><font class="text11MediumBlue">Kopi</font>
																				</td>
																				<td class="text11" width="20px">
																					&nbsp;
																				</td>
																				<td align="right"><b>5.</b>
																					<select name="dkiv_402e" id="dkiv_402e">
																	            		<option value="">-vælg-</option>
																	 				  	<c:forEach var="code" items="${model.transportdocsSummariskadocsCodeList}" >
																	 				  		<option value="${code.dkkd_kd}"<c:if test="${model.record.dkiv_402e == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
																						</c:forEach> 
																					</select>
																				</td>
															           			<td class="text11">
																					&nbsp;<input type="text" class="inputText" name="dkiv_403e" id="dkiv_403e" size="30" maxlength="35" value="${model.record.dkiv_403e}">
																					<input class="clazz_copyElement" type="checkbox" name="dkiv_401e_copy@dkiv_402e_copy@dkiv_403e_copy" id="dkiv_401e_copy@dkiv_402e_copy@dkiv_403e_copy" value="1" ><font class="text11MediumBlue">Kopi</font>
																				</td>
																			</tr>
																			
																			<tr>
																				<td align="right"><b>6.</b>
																					<select name="dkiv_402f" id="dkiv_402f">
																	            		<option value="">-vælg-</option>
																	 				  	<c:forEach var="code" items="${model.transportdocsSummariskadocsCodeList}" >
																	 				  		<option value="${code.dkkd_kd}"<c:if test="${model.record.dkiv_402f == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
																						</c:forEach> 
																					</select>
																				</td>
															           			<td class="text11">
																					&nbsp;<input type="text" class="inputText" name="dkiv_403f" id="dkiv_403f" size="30" maxlength="35" value="${model.record.dkiv_403f}">
																					<input class="clazz_copyElement" type="checkbox" name="dkiv_401f_copy@dkiv_402f_copy@dkiv_403f_copy" id="dkiv_401f_copy@dkiv_402f_copy@dkiv_403f_copy" value="1" ><font class="text11MediumBlue">Kopi</font>
																				</td>
																				<td class="text11" width="20px">
																					&nbsp;
																				</td>
																				<td align="right"><b>7.</b>
																					<select name="dkiv_402g" id="dkiv_402g">
																	            		<option value="">-vælg-</option>
																	 				  	<c:forEach var="code" items="${model.transportdocsSummariskadocsCodeList}" >
																	 				  		<option value="${code.dkkd_kd}"<c:if test="${model.record.dkiv_402g == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
																						</c:forEach> 
																					</select>
																				</td>
															           			<td class="text11">
																					&nbsp;<input type="text" class="inputText" name="dkiv_403g" id="dkiv_403g" size="30" maxlength="35" value="${model.record.dkiv_403g}">
																					<input class="clazz_copyElement" type="checkbox" name="dkiv_401g_copy@dkiv_402g_copy@dkiv_403g_copy" id="dkiv_401g_copy@dkiv_402g_copy@dkiv_403g_copy" value="1" ><font class="text11MediumBlue">Kopi</font>
																				</td>
																			</tr>
																			
																			<tr>
																				<td align="right"><b>8.</b>
																					<select name="dkiv_402h" id="dkiv_402h">
																	            		<option value="">-vælg-</option>
																	 				  	<c:forEach var="code" items="${model.transportdocsSummariskadocsCodeList}" >
																	 				  		<option value="${code.dkkd_kd}"<c:if test="${model.record.dkiv_402h == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
																						</c:forEach> 
																					</select>
																				</td>
															           			<td class="text11">
																					&nbsp;<input type="text" class="inputText" name="dkiv_403h" id="dkiv_403h" size="30" maxlength="35" value="${model.record.dkiv_403h}">
																					<input class="clazz_copyElement" type="checkbox" name="dkiv_401h_copy@dkiv_402h_copy@dkiv_403h_copy" id="dkiv_401h_copy@dkiv_402h_copy@dkiv_403h_copy" value="1" ><font class="text11MediumBlue">Kopi</font>
																				</td>
																				<td class="text11" width="20px">
																					&nbsp;
																				</td>
																				<td align="right"><b>9.</b>
																					<select name="dkiv_402i" id="dkiv_402i">
																	            		<option value="">-vælg-</option>
																	 				  	<c:forEach var="code" items="${model.transportdocsSummariskadocsCodeList}" >
																	 				  		<option value="${code.dkkd_kd}"<c:if test="${model.record.dkiv_402i == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
																						</c:forEach> 
																					</select>
																				</td>
															           			<td class="text11">
																					&nbsp;<input type="text" class="inputText" name="dkiv_403i" id="dkiv_403i" size="30" maxlength="35" value="${model.record.dkiv_403i}">
																					<input class="clazz_copyElement" type="checkbox" name="dkiv_401i_copy@dkiv_402i_copy@dkiv_403i_copy" id="dkiv_401i_copy@dkiv_402i_copy@dkiv_403i_copy" value="1" ><font class="text11MediumBlue">Kopi</font>
																				</td>
																			</tr>
																		
																			<tr>
																				<td align="right"><b>10.</b>
																					<select name="dkiv_402j" id="dkiv_402j">
																	            		<option value="">-vælg-</option>
																	 				  	<c:forEach var="code" items="${model.transportdocsSummariskadocsCodeList}" >
																	 				  		<option value="${code.dkkd_kd}"<c:if test="${model.record.dkiv_402j == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
																						</c:forEach> 
																					</select>
																				</td>
															           			<td class="text11">
																					&nbsp;<input type="text" class="inputText" name="dkiv_403j" id="dkiv_403j" size="30" maxlength="35" value="${model.record.dkiv_403j}">
																					<input class="clazz_copyElement" type="checkbox" name="dkiv_401j_copy@dkiv_402j_copy@dkiv_403j_copy" id="dkiv_401j_copy@dkiv_402j_copy@dkiv_403j_copy" value="1" ><font class="text11MediumBlue">Kopi</font>
																				</td>
																				<td class="text11" width="20px">
																					&nbsp;
																				</td>
																				<td align="right"><b>11.</b>
																					<select name="dkiv_402k" id="dkiv_402k">
																	            		<option value="">-vælg-</option>
																	 				  	<c:forEach var="code" items="${model.transportdocsSummariskadocsCodeList}" >
																	 				  		<option value="${code.dkkd_kd}"<c:if test="${model.record.dkiv_402k == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
																						</c:forEach> 
																					</select>
																				</td>
															           			<td class="text11">
																					&nbsp;<input type="text" class="inputText" name="dkiv_403k" id="dkiv_403k" size="30" maxlength="35" value="${model.record.dkiv_403k}">
																					<input class="clazz_copyElement" type="checkbox" name="dkiv_401k_copy@dkiv_402k_copy@dkiv_403k_copy" id="dkiv_401k_copy@dkiv_402k_copy@dkiv_403k_copy" value="1" ><font class="text11MediumBlue">Kopi</font>
																				</td>
																			</tr>
																			
																			<tr>
																				<td align="right"><b>12.</b>
																					<select name="dkiv_402l" id="dkiv_402l">
																	            		<option value="">-vælg-</option>
																	 				  	<c:forEach var="code" items="${model.transportdocsSummariskadocsCodeList}" >
																	 				  		<option value="${code.dkkd_kd}"<c:if test="${model.record.dkiv_402l == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
																						</c:forEach> 
																					</select>
																				</td>
															           			<td class="text11">
																					&nbsp;<input type="text" class="inputText" name="dkiv_403l" id="dkiv_403l" size="30" maxlength="35" value="${model.record.dkiv_403l}">
																					<input class="clazz_copyElement" type="checkbox" name="dkiv_401l_copy@dkiv_402l_copy@dkiv_403l_copy" id="dkiv_401l_copy@dkiv_402l_copy@dkiv_403l_copy" value="1" ><font class="text11MediumBlue">Kopi</font>
																				</td>
																				<td class="text11" width="20px">
																					&nbsp;
																				</td>
																				<td align="right"><b>13.</b>
																					<select name="dkiv_402m" id="dkiv_402m">
																	            		<option value="">-vælg-</option>
																	 				  	<c:forEach var="code" items="${model.transportdocsSummariskadocsCodeList}" >
																	 				  		<option value="${code.dkkd_kd}"<c:if test="${model.record.dkiv_402m == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
																						</c:forEach> 
																					</select>
																				</td>
															           			<td class="text11">
																					&nbsp;<input type="text" class="inputText" name="dkiv_403m" id="dkiv_403m" size="30" maxlength="35" value="${model.record.dkiv_403m}">
																					<input class="clazz_copyElement" type="checkbox" name="dkiv_401m_copy@dkiv_402m_copy@dkiv_403m_copy" id="dkiv_401m_copy@dkiv_402m_copy@dkiv_403m_copy" value="1" ><font class="text11MediumBlue">Kopi</font>
																				</td>
																			</tr>
																			
																			<tr>
																				<td align="right"><b>14.</b>
																					<select name="dkiv_402n" id="dkiv_402n">
																	            		<option value="">-vælg-</option>
																	 				  	<c:forEach var="code" items="${model.transportdocsSummariskadocsCodeList}" >
																	 				  		<option value="${code.dkkd_kd}"<c:if test="${model.record.dkiv_402n == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
																						</c:forEach> 
																					</select>
																				</td>
															           			<td class="text11">
																					&nbsp;<input type="text" class="inputText" name="dkiv_403n" id="dkiv_403n" size="30" maxlength="35" value="${model.record.dkiv_403n}">
																					<input class="clazz_copyElement" type="checkbox" name="dkiv_401n_copy@dkiv_402n_copy@dkiv_403n_copy" id="dkiv_401n_copy@dkiv_402n_copy@dkiv_403n_copy" value="1" ><font class="text11MediumBlue">Kopi</font>
																				</td>
																				<td class="text11" width="20px">
																					&nbsp;
																				</td>
																				<td align="right"><b>15.</b>
																					<select name="dkiv_402o" id="dkiv_402o">
																	            		<option value="">-vælg-</option>
																	 				  	<c:forEach var="code" items="${model.transportdocsSummariskadocsCodeList}" >
																	 				  		<option value="${code.dkkd_kd}"<c:if test="${model.record.dkiv_402o == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
																						</c:forEach> 
																					</select>
																				</td>
															           			<td class="text11">
																					&nbsp;<input type="text" class="inputText" name="dkiv_403o" id="dkiv_403o" size="30" maxlength="35" value="${model.record.dkiv_403o}">
																					<input class="clazz_copyElement" type="checkbox" name="dkiv_401o_copy@dkiv_402o_copy@dkiv_403o_copy" id="dkiv_401o_copy@dkiv_402o_copy@dkiv_403o_copy" value="1" ><font class="text11MediumBlue">Kopi</font>
																				</td>
																			</tr>
																			
																			<tr>
																				<td align="right"><b>16.</b>
																					<select name="dkiv_402p" id="dkiv_402p">
																	            		<option value="">-vælg-</option>
																	 				  	<c:forEach var="code" items="${model.transportdocsSummariskadocsCodeList}" >
																	 				  		<option value="${code.dkkd_kd}"<c:if test="${model.record.dkiv_402p == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
																						</c:forEach> 
																					</select>
																				</td>
															           			<td class="text11">
																					&nbsp;<input type="text" class="inputText" name="dkiv_403p" id="dkiv_403p" size="30" maxlength="35" value="${model.record.dkiv_403p}">
																					<input class="clazz_copyElement" type="checkbox" name="dkiv_401p_copy@dkiv_402p_copy@dkiv_403p_copy" id="dkiv_401p_copy@dkiv_402p_copy@dkiv_403p_copy" value="1" ><font class="text11MediumBlue">Kopi</font>
																				</td>
																				<td class="text11" width="20px">
																					&nbsp;
																				</td>
																				<td align="right"><b>17.</b>
																					<select name="dkiv_402q" id="dkiv_402q">
																	            		<option value="">-vælg-</option>
																	 				  	<c:forEach var="code" items="${model.transportdocsSummariskadocsCodeList}" >
																	 				  		<option value="${code.dkkd_kd}"<c:if test="${model.record.dkiv_402q == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
																						</c:forEach> 
																					</select>
																				</td>
															           			<td class="text11">
																					&nbsp;<input type="text" class="inputText" name="dkiv_403q" id="dkiv_403q" size="30" maxlength="35" value="${model.record.dkiv_403q}">
																					<input class="clazz_copyElement" type="checkbox" name="dkiv_401q_copy@dkiv_402q_copy@dkiv_403q_copy" id="dkiv_401q_copy@dkiv_402q_copy@dkiv_403q_copy" value="1" ><font class="text11MediumBlue">Kopi</font>
																				</td>
																			</tr>
																			<tr>
																				<td align="right"><b>18.</b>
																					<select name="dkiv_402r" id="dkiv_402r">
																	            		<option value="">-vælg-</option>
																	 				  	<c:forEach var="code" items="${model.transportdocsSummariskadocsCodeList}" >
																	 				  		<option value="${code.dkkd_kd}"<c:if test="${model.record.dkiv_402r == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
																						</c:forEach> 
																					</select>
																				</td>
															           			<td class="text11">
																					&nbsp;<input type="text" class="inputText" name="dkiv_403r" id="dkiv_403r" size="30" maxlength="35" value="${model.record.dkiv_403r}">
																					<input class="clazz_copyElement" type="checkbox" name="dkiv_401r_copy@dkiv_402r_copy@dkiv_403r_copy" id="dkiv_401r_copy@dkiv_402r_copy@dkiv_403r_copy" value="1" ><font class="text11MediumBlue">Kopi</font>
																				</td>
																				<td class="text11" width="20px">
																					&nbsp;
																				</td>
																				<td align="right"><b>19.</b>
																					<select name="dkiv_402s" id="dkiv_402s">
																	            		<option value="">-vælg-</option>
																	 				  	<c:forEach var="code" items="${model.transportdocsSummariskadocsCodeList}" >
																	 				  		<option value="${code.dkkd_kd}"<c:if test="${model.record.dkiv_402s == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
																						</c:forEach> 
																					</select>
																				</td>
															           			<td class="text11">
																					&nbsp;<input type="text" class="inputText" name="dkiv_403s" id="dkiv_403s" size="30" maxlength="35" value="${model.record.dkiv_403s}">
																					<input class="clazz_copyElement" type="checkbox" name="dkiv_401s_copy@dkiv_402s_copy@dkiv_403s_copy" id="dkiv_401s_copy@dkiv_402s_copy@dkiv_403s_copy" value="1" ><font class="text11MediumBlue">Kopi</font>
																				</td>
																			</tr>
													           			</table>
																		<table width="100%" align="left" border="0">
																			<tr align="left" >
																				<td class="text11"><button name="itemSummariskaDocsExtraFieldsButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('itemSummariskaDocsExtraFields');">&nbsp;Ok</button> 
																				</td>
																			</tr>
																		</table>
																	</div>
															</span>														
														 </td>	
													</tr>
							           			</table>
									    </td>
									    <td align="left" colspan="5">
											<div style="display: none;" class="clazz_dialog" id="dialogCopy" title="Dialog">
												<p class="text12" >
													Mindst et <b>kopi-element</b> er blevet valgt.
												</p>
												<p class="text12" >
													Du skal angive <b>til</b> hvilken linjenr. du vil kopiere dine værdier.
												</p>
												<table>
													<tr>
														<td class="text12" align="left" >&nbsp;Fra linjenr.</td>
				                							<td class="text12" align="left" >&nbsp;Til linjenr.</td>
				                						</tr>
				 									<tr>
														<td class="text12MediumBlue">
															<%-- this hidden field is required to interact with jQuery Dialog box and the AjaxController --%>
															<input type="hidden" name="totalNumberOfItemLinesJsp" id="totalNumberOfItemLinesJsp" value="<c:out value="${totalNumberOfItemLines}"/>">
															<input type="text" class="inputText" name="copyLineStartLineNr" id="copyLineStartLineNr" size="2" maxlength="2" value="">
														</td>
														<td class="text12MediumBlue">
															<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="copyLineEndLineNr" id="copyLineEndLineNr" size="2" maxlength="2" value="">
														</td>
														
													</tr>
												</table>
											</div>									    
											<c:choose>	
													<c:when test="${model.status == 'M' || empty model.status || model.status == '10' || model.status == '20'|| model.status == '40'}">
														<%--<input class="inputFormSubmit" type="submit" name="submit" onclick="javascript: form.action='skatimport_edit_items.do';" value='<spring:message code="systema.skat.import.item.createnew.submit"/>'> --%>
														<input class="inputFormSubmit" type="submit" name="submitUpdate" id="submitUpdate" onclick="javascript: form.action='skatimport_edit_items.do';" value='<spring:message code="systema.skat.import.item.createnew.submit"/>'>
														&nbsp;&nbsp;
														<%-- SEND button: is causing some issues in the Stat.värde calculation
															 We shall have the send button ONLY at one place (so far) and that is at the header level
									 				    	<c:if test="${not empty totalNumberOfItemLines && '0' != totalNumberOfItemLines}">
									 				    		<input tabindex=-1 class="inputFormSubmit" type="submit" name="send" id="send" onclick="javascript: form.action='skatimport_send.do';" value='<spring:message code="systema.skat.import.createnew.send"/>'/>
									 				    	</c:if>
									 				  	 --%>
													</c:when>
													<c:otherwise>
							 				    		<input disabled class="inputFormSubmitGrayDisabled" type="submitUpdateDisabled" name="submitUpdateDisabled" value='<spring:message code="systema.skat.submit.not.editable"/>'/>
							 				    	</c:otherwise>	
						 				    	</c:choose>
										</td>	
								    </tr>
								    <tr height="10"><td colspan="2" ></td></tr>
			        	        </table>
					        </td>
					    </tr>
				        
				        <tr height="20"><td colspan="2" ></td></tr>
				        
           	<%-- --------------------------------------------------- --%>
           	<%-- DETAIL Section - Create Item line SECONDARY SECTION --%>
           	<%-- --------------------------------------------------- --%>
           	<tr height="10"><td></td></tr>
            <tr>
	 			<td >		
	 				<%-- Item header --%>
	 				<table width="100%" align="center" class="secondarySectionHeader" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="15">
				 			<td class="text12WhiteBold">
				 				<b>&nbsp;&nbsp;Varepost&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Supplerende oplysninger&nbsp;</b>
				 				<img src="resources/images/update.gif" border="0" alt="edit">
				 			</td>
							<td class="text11White" align="right">
				 				<b>CUSDEC&nbsp;</b>
				 				<a tabindex=-1 href="renderLocalPdf.do?fn=SKAT_EDI_vejledning_CUSDEC_vers_2_7.pdf" target="_blank">
				 					<img valign="bottom" width="14px" height="14px" src="resources/images/pdf.png" border="0" alt="pdf">
				 				</a>
				 				&nbsp;&nbsp;&nbsp;&nbsp;
							</td>
		 				</tr>
	 				</table>
					<%-- Item input --%>
				 	<table width="100%" align="center" class="secondarySectionFrame" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="10"><td class="text" align="left"></td></tr>
				 		<tr>
					 		<td width="50%" valign="top">
				 			<table border="0" cellspacing="1" cellpadding="0">
				            		<tr>
						            <td nowrap colspan="2" class="text12" align="left">
						            &nbsp;
			 						<b>44.&nbsp;Certifikat og bevilling</b></td>
						           	<td class="text12" align="left">&nbsp;</td>
						        </tr>
						        <tr height="5"><td class="text" align="left"></td></tr>
								
								<tr>
						            <td class="text12" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						            <img onMouseOver="showPop('44_1_info');" onMouseOut="hidePop('44_1_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
						            <span title="dkiv_441">44.1&nbsp;Bevillingsnr:&nbsp;</span>
							            <div class="text11" style="position: relative;" align="left">
										<span style="position:absolute;top:2px; width:250px;" id="44_1_info" class="popupWithInputText text11"  >
							           			<b>44.1 Bevillinr.[Sags-nr]</b>
												<br/><br/>
												Bevillingsnummeret [Sags-nr] er journalnummeret på bevillingen eller tilladelsen.
										</span>
										</div>
									</td>	
						            <td class="text12" align="left">
						            		<input type="text" class="inputText" name="dkiv_441" id="dkiv_441" size="15" maxlength="13" value="${model.record.dkiv_441}">
						            </td>
						        </tr>
						        <tr>
						         
						            <td class="text12" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						            <img onMouseOver="showPop('44_2_info');" onMouseOut="hidePop('44_2_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
						            <span title="dkiv_442a">44.2&nbsp;Certifikatnr:&nbsp;</span>
						            <div class="text11" style="position: relative;" align="left">
									<span style="position:absolute;top:2px; width:250px;" id="44_2_info" class="popupWithInputText text11"  >
						           			<b>44.2 Certifikatnr / Certifikatkode</b>
											<br/><br/>
											Ved indførsel af varer omfattet af varebestemmelser eller med adgang til en nedsat told ud over de almindelige præferenceordninger er der ofte krav, 
											om at varerne skal ledsages af et certifikat, en tilladelse eller lignende.
											<br/><br/>
											Hvis varerne er omfattet af kontrolforanstaltninger, som gør, at de ikke kan frigives, før der er foretaget kontrol, 
											skal klarereren medbringe certifikatet eller tilladelsen til toldmyndighederne til brug for kontrollen.
											<br/><br/>
											Hvis varerne er omfattet af kontrolforanstaltninger, hvor der er krav om våbentilladelse el. lign., 
											når varerne overgår til fri omsætning, skal klarereren indgive tilladelsen til SKAT.
											<br/><br/>
											Henvisning til AEO certifikat angives i denne rubrik.
											<br/><br/>
											Man angiver certifikatet ved hjælp af en kode på fire karakterer samt certifikatets nummer.
									</span>
									</div>
									</td>
						            <td class="text12" align="left">
						            		<input type="text" class="inputText" name="dkiv_442a" id="dkiv_442a" size="25" maxlength="35" value="${model.record.dkiv_442a}">
						            		<input class="clazz_copyElement" type="checkbox" name="dkiv_442a_copy@dkiv_4421_copy" id="dkiv_442a_copy@dkiv_4421_copy" value="1" >
						            		<font class="text11MediumBlue">
						            			<label onMouseOver="showPop('dkiv_442a_copy_info');" onMouseOut="hidePop('dkiv_442a_copy_info');">Kopi</label>
						            		</font>						
						            		<div class="text11" style="position: relative;" align="left" >
				 						<span style="position:absolute; left:0px; top:0px;" id="dkiv_442a_copy_info" class="popupWithInputText"  >
				 							<font class="text11" >
							           			Kopiere værdien til alle efterfølgende vareposter
						           			</font>
										</span>
										</div>
						            </td>
						        </tr>
						        <tr>
						            <td class="text12" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						            <img onMouseOver="showPop('44_2b_info');" onMouseOut="hidePop('44_2b_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
						            <span title="dkiv_4421">44.2&nbsp;Certifikatkode:&nbsp;</span>
						            <div class="text11" style="position: relative;" align="left">
									<span style="position:absolute;top:2px; width:250px;" id="44_2b_info" class="popupWithInputText text11"  >
						           			<b>44.2 Certifikatnr / Certifikatkode</b>
											<br/><br/>
											Ved indførsel af varer omfattet af varebestemmelser eller med adgang til en nedsat told ud over de almindelige præferenceordninger er der ofte krav, 
											om at varerne skal ledsages af et certifikat, en tilladelse eller lignende.
											<br/><br/>
											Hvis varerne er omfattet af kontrolforanstaltninger, som gør, at de ikke kan frigives, før der er foretaget kontrol, 
											skal klarereren medbringe certifikatet eller tilladelsen til toldmyndighederne til brug for kontrollen.
											<br/><br/>
											Hvis varerne er omfattet af kontrolforanstaltninger, hvor der er krav om våbentilladelse el. lign., 
											når varerne overgår til fri omsætning, skal klarereren indgive tilladelsen til SKAT.
											<br/><br/>
											Henvisning til AEO certifikat angives i denne rubrik.
											<br/><br/>
											Man angiver certifikatet ved hjælp af en kode på fire karakterer samt certifikatets nummer.
									</span>
									</div>
									</td>
						            <td class="text12" >
				           				<select name="dkiv_4421" id="dkiv_4421">
					 						<option value="">-vælg-</option>
						 				  	<c:forEach var="code" items="${model.certifikatKoderR44_2CodeList}" >
						 				  		<option value="${code.dkkd_kd}"<c:if test="${model.record.dkiv_4421 == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
											</c:forEach>  
										</select>
										<a tabindex="-1" class="text14" target="_blank" href="${model.skatCertifikatk44_2CodesURL.value}" onclick="${model.skatCertifikatk44_2CodesURL.windowOpenDimensions}" >
           										<img style="cursor:pointer;" src="resources/images/find.png" border="0" alt="search" >
           								</a>
										<button name="containerNrButton" class="buttonGray" type="button" onClick="showPop('certifikatKode_Info');" >Mere...</button> 
								           	<span style="position:absolute; left:480px; top:750px; width:550px; height:400px;" id="certifikatKode_Info" class="popupWithInputText"  >
								           		<div class="text10" align="left">
								           			<table>
								           			<tr>
									           			<td class="text11" colspan="3">
									           				<b>44.2 Certifikatnr. / Certifikatkode</b>
									           			</td>
									        			</tr>
								           			<tr>
									           			<td class="text11">
															&nbsp;<span title="dkiv_442b">2. Cert.nr.</span><input type="text" class="inputText" name="dkiv_442b" id="dkiv_442b" size="36" maxlength="35" value="${model.record.dkiv_442b}">
														</td>
														<td class="text11"><span title="dkiv_4422">Cert.kode</span>
															<select name="dkiv_4422" id="dkiv_4422">
										 						<option value="">-vælg-</option>
											 				  	<c:forEach var="code" items="${model.certifikatKoderR44_2CodeList}" >
											 				  		<option value="${code.dkkd_kd}"<c:if test="${model.record.dkiv_4422 == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
																</c:forEach>  
															</select>
															<input class="clazz_copyElement" type="checkbox" name="dkiv_442b_copy@dkiv_4422_copy" id="dkiv_442b_copy@dkiv_4422_copy" value="1" ><font class="text11MediumBlue">Kopi</font>
														</td>
													</tr>
													<tr>
									           			<td class="text11">
															&nbsp;<span title="dkiv_442c">3. Cert.nr.<input type="text" class="inputText" name="dkiv_442c" id="dkiv_442c" size="36" maxlength="35" value="${model.record.dkiv_442c}">
														</td>
														<td class="text11"><span title="dkiv_4423">Cert.kode</span>
															<select name="dkiv_4423" id="dkiv_4423">
										 						<option value="">-vælg-</option>
											 				  	<c:forEach var="code" items="${model.certifikatKoderR44_2CodeList}" >
											 				  		<option value="${code.dkkd_kd}"<c:if test="${model.record.dkiv_4423 == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
																</c:forEach>  
															</select>
															<input class="clazz_copyElement" type="checkbox" name="dkiv_442c_copy@dkiv_4423_copy" id="dkiv_442c_copy@dkiv_4423_copy" value="1" ><font class="text11MediumBlue">Kopi</font>
														</td>
													</tr>
													<tr>
									           			<td class="text11">
															&nbsp;4. Cert.nr.<input type="text" class="inputText" name="dkiv_442d" id="dkiv_442d" size="36" maxlength="35" value="${model.record.dkiv_442d}">
														</td>
														<td class="text11">Cert.kode
															<select name="dkiv_4424" id="dkiv_4424">
										 						<option value="">-vælg-</option>
											 				  	<c:forEach var="code" items="${model.certifikatKoderR44_2CodeList}" >
											 				  		<option value="${code.dkkd_kd}"<c:if test="${model.record.dkiv_4424 == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
																</c:forEach>  
															</select>
															<input class="clazz_copyElement" type="checkbox" name="dkiv_442d_copy@dkiv_4424_copy" id="dkiv_442d_copy@dkiv_4424_copy" value="1" ><font class="text11MediumBlue">Kopi</font>
														</td>
													</tr>
													<tr>
									           			<td class="text11">
															&nbsp;5. Cert.nr.<input type="text" class="inputText" name="dkiv_442e" id="dkiv_442e" size="36" maxlength="35" value="${model.record.dkiv_442e}">
														</td>
														<td class="text11">Cert.kode
															<select name="dkiv_4425" id="dkiv_4425">
										 						<option value="">-vælg-</option>
											 				  	<c:forEach var="code" items="${model.certifikatKoderR44_2CodeList}" >
											 				  		<option value="${code.dkkd_kd}"<c:if test="${model.record.dkiv_4425 == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
																</c:forEach>  
															</select>
															<input class="clazz_copyElement" type="checkbox" name="dkiv_442e_copy@dkiv_4425_copy" id="dkiv_442e_copy@dkiv_4425_copy" value="1" ><font class="text11MediumBlue">Kopi</font>
														</td>
													</tr>
													<tr>
									           			<td class="text11">
															&nbsp;6. Cert.nr.<input type="text" class="inputText" name="dkiv_442f" id="dkiv_442f" size="36" maxlength="35" value="${model.record.dkiv_442f}">
														</td>
														<td class="text11">Cert.kode
															<select name="dkiv_4426" id="dkiv_4426">
										 						<option value="">-vælg-</option>
											 				  	<c:forEach var="code" items="${model.certifikatKoderR44_2CodeList}" >
											 				  		<option value="${code.dkkd_kd}"<c:if test="${model.record.dkiv_4426 == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
																</c:forEach>  
															</select>
															<input class="clazz_copyElement" type="checkbox" name="dkiv_442f_copy@dkiv_4426_copy" id="dkiv_442f_copy@dkiv_4426_copy" value="1" ><font class="text11MediumBlue">Kopi</font>
														</td>
													</tr>
													<tr>
									           			<td class="text11">
															&nbsp;7. Cert.nr.<input type="text" class="inputText" name="dkiv_442g" id="dkiv_442g" size="36" maxlength="35" value="${model.record.dkiv_442g}">
														</td>
														<td class="text11">Cert.kode
															<select name="dkiv_4427" id="dkiv_4427">
										 						<option value="">-vælg-</option>
											 				  	<c:forEach var="code" items="${model.certifikatKoderR44_2CodeList}" >
											 				  		<option value="${code.dkkd_kd}"<c:if test="${model.record.dkiv_4427 == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
																</c:forEach>  
															</select>
															<input class="clazz_copyElement" type="checkbox" name="dkiv_442g_copy@dkiv_4427_copy" id="dkiv_442g_copy@dkiv_4427_copy" value="1" ><font class="text11MediumBlue">Kopi</font>
														</td>
													</tr>
													<tr>
									           			<td class="text11">
															&nbsp;8. Cert.nr.<input type="text" class="inputText" name="dkiv_442h" id="dkiv_442h" size="36" maxlength="35" value="${model.record.dkiv_442h}">
														</td>
														<td class="text11">Cert.kode
															<select name="dkiv_4428" id="dkiv_4428">
										 						<option value="">-vælg-</option>
											 				  	<c:forEach var="code" items="${model.certifikatKoderR44_2CodeList}" >
											 				  		<option value="${code.dkkd_kd}"<c:if test="${model.record.dkiv_4428 == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
																</c:forEach>  
															</select>
															<input class="clazz_copyElement" type="checkbox" name="dkiv_442h_copy@dkiv_4428_copy" id="dkiv_442h_copy@dkiv_4428_copy" value="1" ><font class="text11MediumBlue">Kopi</font>
														</td>
													</tr>
													<tr>
									           			<td class="text11">
															&nbsp;<span title="dkiv_442i">9. Cert.nr.</span><input type="text" class="inputText" name="dkiv_442i" id="dkiv_442i" size="36" maxlength="35" value="${model.record.dkiv_442i}">
														</td>
														<td class="text11"><span title="dkiv_4429">Cert.kode</span>
															<select name="dkiv_4429" id="dkiv_4429">
										 						<option value="">-vælg-</option>
											 				  	<c:forEach var="code" items="${model.certifikatKoderR44_2CodeList}" >
											 				  		<option value="${code.dkkd_kd}"<c:if test="${model.record.dkiv_4429 == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
																</c:forEach>  
															</select>
															<input class="clazz_copyElement" type="checkbox" name="dkiv_442i_copy@dkiv_4429_copy" id="dkiv_442i_copy@dkiv_4429_copy" value="1" ><font class="text11MediumBlue">Kopi</font>
														</td>
													</tr>
													</table>
													<table width="100%" align="left" border="0">
														<tr align="left" >
															<td class="text12" ><button name="certifikatButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('certifikatKode_Info');">&nbsp;Ok</button> 
															</td>
														</tr>
													</table>
												</div>
								           	</span>           								
           								
           								
				           			</td>
						        </tr>
						        <tr>
						            <td class="text12" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						            <img onMouseOver="showPop('44_3_info');" onMouseOut="hidePop('44_3_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
						            <span title="dkiv_443">44.3&nbsp;Varebestemmelse:&nbsp;</span>
						            <div class="text11" style="position: relative;" align="left">
									<span style="position:absolute;top:2px; width:250px;" id="44_3_info" class="popupWithInputText text11"  >
						           			<b>44.3 Varebestemmelser</b>
											<br/><br/>
											Hvis varerne er omfattet af varebestemmelser, jf. afsnit F.A.11, er det her, 
											man skal angive bogstavkoden for den pågældende varebestemmelse. 
											<br/><br/>
											Varebestemmelsen angiver, hvilke betingelser, der er knyttet til importen, fx at der skal fremlægges licens eller certifikat mv.
											<br/><br/>
											Hvis varen er omfattet af en varebestemmelse, skal klarereren desuden angive enten certifikatkode- og nummer i rubrik 44.2., 
											eller oplysninger i rubrik 44.6., afhængigt af den pågældende varebestemmelse.												<br/><br/>
											Henvisning til AEO certifikat angives i denne rubrik.
											<br/><br/>
											Det fremgår af Toldtariffen, om den enkelte varekode er omfattet af varebestemmelser.
									</span>
									</div>
									</td>
						            <td class="text12" >
				           				<select name="dkiv_443" id="dkiv_443">
					 						<option value="">-vælg-</option>
						 				  	<c:forEach var="code" items="${model.vabCertifikatKoderR44_3CodeList}" >
						 				  		<option value="${code.dkkd_kd}"<c:if test="${model.record.dkiv_443 == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
											</c:forEach>  
										</select>
										<a tabindex="-1" class="text14" target="_blank" href="${model.skatVabCertifikatk44_3CodesURL.value}" onclick="${model.skatVabCertifikatk44_3CodesURL.windowOpenDimensions}" >
           										<img style="cursor:pointer;" src="resources/images/find.png" border="0" alt="search" >
           									</a>
										
				           			</td>
						        </tr>
						        <tr>
						            <td class="text12" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						            <img onMouseOver="showPop('44_4_info');" onMouseOut="hidePop('44_4_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
						            <span title="dkiv_444b">44.4&nbsp;Præferencedok.kode:&nbsp;</span>
						            <div class="text11" style="position: relative;" align="left">
									<span style="position:absolute;top:2px; width:250px;" id="44_4_info" class="popupWithInputText text11"  >
						           			<b>44.4 Præferencedok.kode</b>
											<br/><br/>
											Hvis man anmoder om præferencetoldbehandling, er det her, 
											klarereren skal angive koden for den type præferencedokumentation, der skal anvendes. 
											Koderne findes på SKATs hjemmeside.
									</span>
									</div>
									</td>
						            <td class="text12" >
				           				<select name="dkiv_444b" id="dkiv_444b">
					 						<option value="">-vælg-</option>
						 				  	<c:forEach var="code" items="${model.pdokumentationsKoderR44_4CodeList}" >
						 				  		<option value="${code.dkkd_kd}"<c:if test="${model.record.dkiv_444b == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
											</c:forEach>  
										</select>
										<a tabindex="-1" class="text14" target="_blank" href="${model.skatPreferenceDokumentation44_4CodesURL.value}" onclick="${model.skatPreferenceDokumentation44_4CodesURL.windowOpenDimensions}" >
           										<img style="cursor:pointer;" src="resources/images/find.png" border="0" alt="search" >
           									</a>
										
				           			</td>
						        </tr>
						        <tr>
						            <td class="text12" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						            <img onMouseOver="showPop('44_4a_info');" onMouseOut="hidePop('44_4a_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
						            <span title="dkiv_444a">44.4a&nbsp;Præferencedok.nr:&nbsp;</span>
						            <div class="text11" style="position: relative;" align="left">
									<span style="position:absolute;top:2px; width:250px;" id="44_4a_info" class="popupWithInputText text11"  >
						           			<b>44.4a Præferencedok.nr</b>
											<br/><br/>
											Her angiver klarereren nummeret på det præferencedokument, der er angivet i rubrik 44.4. 
											Hvis der er tale om en fakturaerklæring, angiver klarereren fakturanummeret.
									</span>
									</div>
									</td>
						            <td class="text12" align="left">
					            		<input type="text" class="inputText" name="dkiv_444a" id="dkiv_444a" size="25" maxlength="20" value="${model.record.dkiv_444a}">
						            </td>
						        </tr>
						        
								<tr height="15"><td></td></tr>
						        <tr>
						            <td colspan="5" class="text12">
						            &nbsp;
			 						<b>44.9&nbsp;Oplysningstype</b>
						            
					           		<div class="text11" style="position: relative;" align="left">
									<span style="position:absolute;top:2px; width:250px;" id="44a_info" class="popupWithInputText text11"  >
					           			<b>Särskilda upplysningar</b>
										<br/>
					           			Fält 44 består av två delfält, ett fritextfält och ett kodfält. Vissa uppgifter ska du alltid fylla i då du deklarerar enligt godkänd exportör, medan ytterligare upplysningar lämnar du vid behov. Det kan exempelvis vara ett tillståndsnummer eller ett licensnummer för exportreglerade varor.
										<br/><br/>
										Särskilda upplysningar (Kod)<br/>
										I vissa fall ska du ange särskilda upplysningar. 
										<br/><br/>
										<b>Bilagda handlingar/Certifikat och tillstånd </b>
										<br/>
										Här ska du ange en eller flera koder följda av ett identitetsnummer för de handlingar, certifikat eller tillstånd som ligger till grund för deklarationen. Koden består av fyra tecken och kan bestå av både bokstäver och siffror. Kod för faktura och identitetsnummer är obligatoriska uppgifter.    
										<br/><br/>
										Du ska även ange ett transportdokumentnummer. I de fall du inte skrivit något i fältet "Kommersiellt referensnummer", är transportdokumentnumret en obligatorisk uppgift. Transportdokumentnummer finns förklarat under rubriken " Transportdokumentnummer".

									</span>
									</div>
									</td>
						        </tr>
								<tr height="5"><td></td></tr>
			           			<tr>
				           			<td class="text11">
				           				<span title="dkiv_449a">&nbsp;&nbsp;&nbsp;&nbsp;Kode</span>
				           				<select name="dkiv_449a" id="dkiv_449a">
					 						<option value="">-vælg-</option>
						 				  	<c:forEach var="code" items="${model.oplysningsTypeCodeList}" >
						 				  		<c:choose>
													<c:when test="${not empty model.record.dkiv_449a}">
							 				  			<option value="${code.dkkd_kd}"<c:if test="${model.record.dkiv_449a == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
							 				  		</c:when>
							 				  		<c:otherwise>
							 				  			<option value="${code.dkkd_kd}"<c:if test="${XX.svih_faty == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
							 				  		</c:otherwise>
							 				  	</c:choose>	
											</c:forEach>  
										</select>
				           			</td>
				           			<td class="text11">
										<span title="dkiv_449b">&nbsp;Værdi</span>
											<c:choose>
												<c:when test="${not empty dkiv_449b}">
													<input type="text" class="inputText" name="dkiv_449b" id="dkiv_449b" size="17" maxlength="16" value="${model.record.dkiv_449b}">
												</c:when>
												<c:otherwise>
													<input type="text" class="inputText" name="dkiv_449b" id="dkiv_449b" size="17" maxlength="16" value="${XX.svih_fatx}"> <%--TODO ?? --%>
												</c:otherwise>
											</c:choose>
									</td>
								</tr>
								<tr height="15"><td></td></tr>
						        <tr>
						            <td colspan="2" class="text12">
						            &nbsp;
			 						<b>44.6&nbsp;Supplerende vareoplysninger</b></td>
						        </tr>
								<tr height="5"><td></td></tr>
								<tr>
				           			<td class="text11" >
				           				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span title="dkiv_446ka">Kode</span>
				           				&nbsp;
				           				<select name="dkiv_446ka" id="dkiv_446ka">
				           					<option value="">-vælg-</option>
					 						<c:forEach var="code" items="${model.supplerandeVareoplysningsKoderR44_6CodeList}" >
						 				  		<option value="${code.dkkd_kd}"<c:if test="${model.record.dkiv_446ka == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
											</c:forEach>  
										</select>
										&nbsp;<button name="itemSupplerendeVareOpExtraFieldsButton" class="buttonGray" type="button" onClick="showPop('itemSupplerendeVareOpExtraFields');" >Mere...</button>
									        <span style="position:absolute; left:250px; top:800px; width:750px; height:500px;" id="itemSupplerendeVareOpExtraFields" class="popupWithInputText"  >
									           		<div class="text10" align="left">
									           			<table>
									           				<tr>
											           			<td colspan="2" class="text12">
																	<b>44.6&nbsp;Supplerende vareoplysninger</b></td>
																</td>
															</tr>
															<tr height="8"><td></td></tr>
															<tr>
																<td class="text11" align="center">
																	<span title="dkiv_446kb,_446kf,_446kj,_446kn,_446kr">Kode</span>
																</td>
											           			<td class="text11">
																	&nbsp;&nbsp;<span title="dkiv_446tb - _446ts">Tekst</span>
																</td>
																<td class="text11" align="center">&nbsp;</td>
											           			<td class="text11">
																	&nbsp;&nbsp;Tekst
																</td>
																
															</tr>
															<tr>
																<td 	align="right"><b>2.</b>
																	<select name="dkiv_446kb" id="dkiv_446kb">
													            		<option value="">-vælg-</option>
													 				  	<c:forEach var="code" items="${model.supplerandeVareoplysningsKoderR44_6CodeList}" >
													 				  		<option value="${code.dkkd_kd}"<c:if test="${model.record.dkiv_446kb == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
																		</c:forEach> 
																	</select>
																</td>
											           			<td class="text10">b
																	&nbsp;<input type="text" class="inputText" name="dkiv_446tb" id="dkiv_446tb" size="30" maxlength="35" value="${model.record.dkiv_446tb}">
																</td>
																<td align="right">&nbsp;</td>
											           			<td class="text10">c
																	&nbsp;<input type="text" class="inputText" name="dkiv_446tc" id="dkiv_446tc" size="30" maxlength="35" value="${model.record.dkiv_446tc}">
																</td>
															</tr>
															<tr>
																<td align="right">&nbsp;</td>
											           			<td class="text10">d
																	&nbsp;<input type="text" class="inputText" name="dkiv_446td" id="dkiv_446td" size="30" maxlength="35" value="${model.record.dkiv_446td}">
																</td>
																<td align="right">&nbsp;</td>
											           			<td class="text10">e
																	&nbsp;<input type="text" class="inputText" name="dkiv_446te" id="dkiv_446te" size="30" maxlength="35" value="${model.record.dkiv_446te}">
																</td>
															</tr>
															<tr height="5"><td></td></tr>
															<tr>
																<td align="right"><b>3.</b>
																	<select name="dkiv_446kf" id="dkiv_446kf">
													            		<option value="">-vælg-</option>
													 				  	<c:forEach var="code" items="${model.supplerandeVareoplysningsKoderR44_6CodeList}" >
													 				  		<option value="${code.dkkd_kd}"<c:if test="${model.record.dkiv_446kf == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
																		</c:forEach> 
																	</select>
																</td>
											           			<td class="text10">f
																	&nbsp;<input type="text" class="inputText" name="dkiv_446tf" id="dkiv_446tf" size="30" maxlength="35" value="${model.record.dkiv_446tf}">
																</td>
																<td align="right">&nbsp;</td>
											           			<td class="text10">g
																	&nbsp;<input type="text" class="inputText" name="dkiv_446tg" id="dkiv_446tg" size="30" maxlength="35" value="${model.record.dkiv_446tg}">
																</td>
															</tr>
															<tr>
																<td align="right">&nbsp;</td>
											           			<td class="text10">h
																	&nbsp;<input type="text" class="inputText" name="dkiv_446th" id="dkiv_446th" size="30" maxlength="35" value="${model.record.dkiv_446th}">
																</td>
																<td align="right">&nbsp;</td>
											           			<td class="text10">i
																	&nbsp;<input type="text" class="inputText" name="dkiv_446ti" id="dkiv_446ti" size="30" maxlength="35" value="${model.record.dkiv_446ti}">
																</td>
															</tr>
															<tr height="5"><td></td></tr>
															<tr>
																<td align="right"><b>4.</b>
																	<select name="dkiv_446kj" id="dkiv_446kj">
													            		<option value="">-vælg-</option>
													 				  	<c:forEach var="code" items="${model.supplerandeVareoplysningsKoderR44_6CodeList}" >
													 				  		<option value="${code.dkkd_kd}"<c:if test="${model.record.dkiv_446kj == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
																		</c:forEach> 
																	</select>
																</td>
											           			<td class="text10">j
																	&nbsp;<input type="text" class="inputText" name="dkiv_446tj" id="dkiv_446tj" size="30" maxlength="35" value="${model.record.dkiv_446tj}">
																</td>
																<td align="right">&nbsp;</td>
											           			<td class="text10">k
																	&nbsp;<input type="text" class="inputText" name="dkiv_446tk" id="dkiv_446tk" size="30" maxlength="35" value="${model.record.dkiv_446tk}">
																</td>
															</tr>
															<tr>
																<td align="right">&nbsp;</td>
											           			<td class="text10">l
																	&nbsp;<input type="text" class="inputText" name="dkiv_446tl" id="dkiv_446tl" size="30" maxlength="35" value="${model.record.dkiv_446tl}">
																</td>
																<td align="right">&nbsp;</td>
											           			<td class="text10">m
																	&nbsp;<input type="text" class="inputText" name="dkiv_446tm" id="dkiv_446tm" size="30" maxlength="35" value="${model.record.dkiv_446tm}">
																</td>
															</tr>
															<tr height="5"><td></td></tr>
															<tr>
																<td align="right"><b>5.</b>
																	<select name="dkiv_446kn" id="dkiv_446kn">
													            		<option value="">-vælg-</option>
													 				  	<c:forEach var="code" items="${model.supplerandeVareoplysningsKoderR44_6CodeList}" >
													 				  		<option value="${code.dkkd_kd}"<c:if test="${model.record.dkiv_446kn == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
																		</c:forEach> 
																	</select>
																</td>
											           			<td class="text10">n
																	&nbsp;<input type="text" class="inputText" name="dkiv_446tn" id="dkiv_446tn" size="30" maxlength="35" value="${model.record.dkiv_446tn}">
																</td>
																<td align="right">&nbsp;</td>
											           			<td class="text10">o
																	&nbsp;<input type="text" class="inputText" name="dkiv_446to" id="dkiv_446to" size="30" maxlength="35" value="${model.record.dkiv_446to}">
																</td>
															</tr>
															<tr>
																<td align="right">&nbsp;</td>
											           			<td class="text10">p
																	&nbsp;<input type="text" class="inputText" name="dkiv_446tp" id="dkiv_446tp" size="30" maxlength="35" value="${model.record.dkiv_446tp}">																
																</td>
																<td align="right">&nbsp;</td>
											           			<td class="text10">q
																	&nbsp;<input type="text" class="inputText" name="dkiv_446tq" id="dkiv_446tq" size="30" maxlength="35" value="${model.record.dkiv_446tq}">																
																</td>
															</tr>
															<tr height="5"><td></td></tr>
															<tr>
																<td align="right"><b>6.</b>
																	<select name="dkiv_446kr" id="dkiv_446kr">
													            		<option value="">-vælg-</option>
													 				  	<c:forEach var="code" items="${model.supplerandeVareoplysningsKoderR44_6CodeList}" >
													 				  		<option value="${code.dkkd_kd}"<c:if test="${model.record.dkiv_446kr == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
																		</c:forEach> 
																	</select>
																</td>
											           			<td class="text10">r
																	&nbsp;<input type="text" class="inputText" name="dkiv_446tr" id="dkiv_446tr" size="30" maxlength="35" value="${model.record.dkiv_446tr}">																
																</td>
																<td align="right">&nbsp;</td>
											           			<td class="text10">s
																	&nbsp;<input type="text" class="inputText" name="dkiv_446ts" id="dkiv_446ts" size="30" maxlength="35" value="${model.record.dkiv_446ts}">																
																</td>
															</tr>
									           			</table>
														<table width="100%" align="left" border="0">
															<tr align="left" >
																<td class="text11"><button name="itemSupplerendeVareOpExtraFieldsButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('itemSupplerendeVareOpExtraFields');">&nbsp;Ok</button> 
																</td>
															</tr>
														</table>
													</div>
											</span>											
				           			</td>
				           			<td class="text11" >
				           				&nbsp;
				           			</td>
								</tr>
								<tr>
									<td colspan="2" class="text11">&nbsp;&nbsp;&nbsp;&nbsp;
										<span title="dkiv_446ta">Tekst&nbsp;</span>
									</td>
								</tr>
								<tr>
									<td colspan="2" class="text11">&nbsp;&nbsp;&nbsp;
										<textarea onKeyPress="return lockCarriageReturnKey(event)" rows="5" cols="40" class="inputText" name="dkiv_446ta" id="dkiv_446ta" maxlength="254">${model.record.dkiv_446ta}</textarea>
									</td>
								</tr>
								<tr height="5"><td></td></tr>
			        	        </table>
					        </td>
					        <td width="50%" valign="top">
						 		<table border="0" cellspacing="1" cellpadding="0">
									<tr>
							            <td nowrap colspan="2" class="text12" align="left">
							            		<b>33.&nbsp;Varekode [Tillægskoder]</b>
				 						</td>
							           	<td class="text12" align="left">&nbsp;</td>
							        </tr>
							        <tr height="5"><td class="text" align="left"></td></tr>
							        <tr>   	
						            		<td class="text12" align="left">&nbsp;&nbsp;
											<img onMouseOver="showPop('332_info');" onMouseOut="hidePop('332_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info" >
											<span title="dkiv_332/dkiv_333">Tillægskoder&nbsp;</span>
											<div class="text11" style="position: relative;" align="left">
											<span style="position:absolute;top:2px; width:250px;" id="332_info" class="popupWithInputText text11"  >
								           			<b>33.&nbsp;Varekode [Tillægskoder]</b>
													<p>
													I nogle tilfælde skal der yderligere angives op til to 4-cifrede tillægskoder, fx når der er tale om antidumpingtold,
													landbrugselement mv. 
													</p>
													<p>
													Se Toldtariffens vejledning om anvendelse af varekoder og tillægskoder og afsnittet om MIO.
													</p>
											</span>
											</div>
										</td>
										<td class="text12" align="left" colspan="2">
											33.2&nbsp;<input type="text" class="inputText" name="dkiv_332" id="dkiv_332" size="4" maxlength="4" value="${model.record.dkiv_332}">
											&nbsp;33.3&nbsp;<input type="text" class="inputText" name="dkiv_333" id="dkiv_333" size="4" maxlength="4" value="${model.record.dkiv_333}">
										</td>
					            		</tr>
					            		<tr height="15"><td class="text" align="left">&nbsp;</td></tr>
						 			<tr>
							            <td class="text12" nowrap colspan="2" align="left"><b>31.</b>&nbsp;
							            		<span title="dkiv_311/dkiv_313/dkiv_314">Kolli mærke, kolli antal og kolli art</span>
						            		</td>
							           	<td class="text12" align="left">&nbsp;
							           	<button name="goodsMarkButton" class="buttonGray" type="button" onClick="showPop('goodsMark');" >Mere...</button> 
   									        <span style="position:absolute; left:480px; top:800px; width:350px; height:320px;" id="goodsMark" class="popupWithInputText"  >
								           		<div class="text10" align="left" valign="top">
								           			<table>
								           				<tr>
										           			<td class="text11">
										           				&nbsp;Kolli mærke [max 16 tecken]:
										           			</td>
										           			<td class="text11">
																&nbsp;Kolli antal:
															</td>
															<td class="text11">
																&nbsp;Kolli art [kod]
															</td>
														</tr>
									           			<tr>
										           			<td valign="top" class="text11">
										           				&nbsp;2.<textarea rows="2" cols="15" class="inputText" name="dkiv_311b" id="dkiv_311b" maxlength="16">${model.record.dkiv_311b}</textarea>
										           			</td>
										           			<td valign="bottom" class="text11">
																&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="dkiv_313b" id="dkiv_313b" size="6" maxlength="6" value="${model.record.dkiv_313b}">
															</td>
															<td align="left" valign="bottom">
										            			<select name="dkiv_314b" id="dkiv_314b">
												            		<option value="">-vælg-</option>
												 				  	<c:forEach var="code" items="${model.emballageCodeList}" >
								                                	 	<option value="${code.dkkd_kd}"<c:if test="${model.record.dkiv_314b == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
																	</c:forEach> 
																</select>
									            				</td> 
										           		</tr>
														<tr>
										           			<td valign="top" class="text11">
										           				&nbsp;3.<textarea rows="2" cols="15" class="inputText" name="dkiv_311c" id="dkiv_311c" maxlength="16">${model.record.dkiv_311c}</textarea>
										           			</td>
										           			<td valign="bottom" class="text11">
																&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="dkiv_313c" id="dkiv_313c" size="6" maxlength="6" value="${model.record.dkiv_313c}">
															</td>
															<td align="left" valign="bottom">
										            			<select name="dkiv_314c" id="dkiv_314c">
												            		<option value="">-vælg-</option>
												 				  	<c:forEach var="code" items="${model.emballageCodeList}" >
								                                	 	<option value="${code.dkkd_kd}"<c:if test="${model.record.dkiv_314c == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
																	</c:forEach> 
																</select>
									            				</td> 
										           		</tr>
														
									           			<tr>
										           			<td valign="top" class="text11">
										           				&nbsp;4.<textarea rows="2" cols="15" class="inputText" name="dkiv_311d" id="dkiv_311d" maxlength="16">${model.record.dkiv_311d}</textarea>
										           			</td>
										           			<td valign="bottom" class="text11">
																&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="dkiv_313d" id="dkiv_313d" size="6" maxlength="6" value="${model.record.dkiv_313d}">
															</td>
															<td align="left" valign="bottom">
										            			<select name="dkiv_314d" id="dkiv_314d">
												            		<option value="">-vælg-</option>
												 				  	<c:forEach var="code" items="${model.emballageCodeList}" >
								                                	 	<option value="${code.dkkd_kd}"<c:if test="${model.record.dkiv_314d == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
																	</c:forEach> 
																</select>
									            				</td> 
										           		</tr>
														<tr>
										           			<td valign="top" class="text11">
										           				&nbsp;5.<textarea rows="2" cols="15" class="inputText" name="dkiv_311e" id="dkiv_311e" maxlength="16">${model.record.dkiv_311e}</textarea>
										           			</td>
										           			<td valign="bottom" class="text11">
																&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="dkiv_313e" id="dkiv_313e" size="6" maxlength="6" value="${model.record.dkiv_313e}">
															</td>
															<td align="left" valign="bottom">
										            			<select name="dkiv_314e" id="dkiv_314e">
												            		<option value="">-vælg-</option>
												 				  	<c:forEach var="code" items="${model.emballageCodeList}" >
								                                	 	<option value="${code.dkkd_kd}"<c:if test="${model.record.dkiv_314e == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
																	</c:forEach> 
																</select>
									            				</td> 
										           		</tr>
														
									           			
								           			</table>
													<table width="100%" align="left" border="0">
														<tr align="left" >
															<td  class="text12"><button name="goodsMarkButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('goodsMark');">&nbsp;Ok</button> 
															</td>
														</tr>
													</table>
												</div>
										</span>
							           	</td>
							        </tr>
									<tr>
							            <td class="text12" align="left">&nbsp;&nbsp;
							            <img onMouseOver="showPop('31_2_info');" onMouseOut="hidePop('31_2_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
							            <span title="dkiv_312a,dkiv_312b ...">31.2 Containernr:&nbsp;</span>
							            <div class="text11" style="position: relative;" align="left">
										<span style="position:absolute;top:2px; width:250px;" id="31_2_info" class="popupWithInputText text11"  >
						           			<b>31.2 Containernr</b>
											<br/><br/>
											Hvis varerne transporteres i container, angiver klarereren identifikationsnummeret på containeren. 
											Både præfiks og nummer skal anføres.
										</span>
										</div>
										</td>
							            <td class="text12" align="left">
								           	<input type="text" class="inputText" name="dkiv_312a" id="dkiv_312a" size="12" maxlength="11" value="${model.record.dkiv_312a}">
								        </td>
								        <td>&nbsp;  	
							           		<button name="containerNrButton" class="buttonGray" type="button" onClick="showPop('containerNrInfo');" >Mere...</button> 
								           	<span style="position:absolute; left:480px; top:850px; width:580px; height:100px;" id="containerNrInfo" class="popupWithInputText"  >
								           		<div class="text10" align="left">
								           			<table>
								           			<tr>
									           			<td class="text11" colspan="5">
									           				<b>31.2 Containernr</b>
									           			</td>
									        			</tr>
								           			<tr>
									           			<td class="text11">
															&nbsp;2.<input type="text" class="inputText" name="dkiv_312b" id="dkiv_312b" size="12" maxlength="11" value="${model.record.dkiv_312b}">
														</td>
														<td class="text11">
															&nbsp;3.<input type="text" class="inputText" name="dkiv_312c" id="dkiv_312c" size="12" maxlength="11" value="${model.record.dkiv_312c}">
														</td>
														<td class="text11">
									           				&nbsp;4.<input type="text" class="inputText" name="dkiv_312d" id="dkiv_312d" size="12" maxlength="11" value="${model.record.dkiv_312d}">
									           			</td>
									           			<td class="text11">
									           				&nbsp;5.<input type="text" class="inputText" name="dkiv_312e" id="dkiv_312e" size="12" maxlength="11" value="${model.record.dkiv_312e}">
									           			</td>
													</tr>
													</table>
													<table width="100%" align="left" border="0">
														<tr align="left" >
															<td class="text12" ><button name="containerNrButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('containerNrInfo');">&nbsp;Ok</button> 
															</td>
														</tr>
													</table>
												</div>
								           	</span>
											
							           	</td>
							        </tr>
							        <tr height="15"><td></td></tr>

							        <tr>
							            <td class="text12" align="left">
							            		<img onMouseOver="showPop('39_info');" onMouseOut="hidePop('39_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
						            			<span title="dkiv_39"><b>39.</b>&nbsp;Kontingent nr:</span>
							            <div class="text11" style="position: relative;" align="left">
										<span style="position:absolute;top:2px; width:250px;" id="39_info" class="popupWithInputText text11"  >
						           			<b>39 Kontingenter</b>
											<br/><br/>
											Kontingentløbenummer
											<br/><br/>
											Se Toldtariffen
										</span>
										</div>
										</td>
							            <td class="text12" align="left">
							            		<input type="text" onKeyPress="return numberKey(event)" class="inputText" name="dkiv_39" id="dkiv_39" size="7" maxlength="6" value="${model.record.dkiv_39}">
							            		<a tabindex="-1" class="text14" target="_blank" href="${model.skatToldKontingenterCodesURL.value}" onclick="${model.skatToldKontingenterCodesURL.windowOpenDimensions}" >
        											<img style="cursor:pointer;" src="resources/images/find.png" border="0" alt="search" >
           									</a>
							            </td>
							        </tr>
							        <tr height="20"><td></td></tr>
							        
							        <tr>
							            <td class="text12" align="left">
							            <img onMouseOver="showPop('sikkerheds_info');" onMouseOut="hidePop('sikkerheds_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					            		<span title="dkiv_sikk">Sikkerhedsstillelse:&nbsp;</span>
							           
							            <div class="text11" style="position: relative;" align="left">
										<span style="position:absolute;top:2px; width:250px;" id="sikkerheds_info" class="popupWithInputText text11"  >
						           			<b>Sikkerhedsstillelse</b>
											<br/><br/>
											Oplysning om særlig sikkerhedsstillelse i fb. m. antidumping/udligningtold
											<br/><br/>
											Se Toldvejledningen (Indikator af, hvilken slags angivelse, der her er tale om, altså sikkerhedsstillelse i forbindelse med antidumpingtold (ma) eller udligningstold (mu))
										</span>
										</div>
										</td>
							            <td class="text12" align="left">
							            		<input type="text" class="inputText" name="dkiv_sikk" id="dkiv_sikk" size="3" maxlength="2" value="${model.record.dkiv_sikk}">
							            </td>
							        </tr>
							        <tr>
							            <td class="text12" align="left">
								            <img onMouseOver="showPop('farligGods_info');" onMouseOut="hidePop('farligGods_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
							            		<span title="dkiv_s27"><b>S27.</b>&nbsp;FN Farlig gods:&nbsp;</span>
							            
							            <div class="text11" style="position: relative;" align="left">
										<span style="position:absolute;top:2px; width:250px;" id="farligGods_info" class="popupWithInputText text11"  >
						           			<b>FN-kode for farlige varer (UN Dangerous goods code)</b>
											<br/><br/>
											Her anføres De Forenede Nationers kode for farligt gods (UNDG), hvis det er relevant.
											<br/><br/>
											Koden er det unikke firecifrede løbenummer, der af FN tildeles stoffer og artikler, der er opført på en liste over farligt gods, som
											almindeligvis forsendes.
											<br/><br/>
											Anvendes ikke i den forenklede angivelse for AEO-certificerede virksomheder.
											<br/><br/>
											Feltet er ikke obligatorisk at udfylde.
										</span>
										</div>
										</td>
							            <td class="text12" align="left">
							            		<input type="text" class="inputText" name="dkiv_s27" id="dkiv_s27" size="7" maxlength="6" value="${model.record.dkiv_s27}">
							            </td>
							        </tr>
							        <tr height="30"><td>&nbsp;</td></tr>
						        </table>
							        
						        <table>
							        <tr >        
 										<td valign="top" colspan="4" >
											<table class="tableBorderWithRoundCornersGray">
										        <tr >
										        <td colspan="2" class="text12Bold">
										        		<img onMouseOver="showPop('47_info');" onMouseOut="hidePop('47_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
										        		47.&nbsp;<label onClick="showPop('debugPrintlnAjaxAdmin');" >A</label>fgifter [Sker på SKAT]
													<span style="position:absolute; left:200px; top:600px; width:800px; height:300px;" id="debugPrintlnAjaxAdmin" class="popupWithInputText"  >
										           		<div class="text11" align="left">
										           			<label id="debugPrintlnAjaxInfo"></label>
										           			&nbsp;&nbsp;&nbsp;&nbsp;<button name="specialInformationButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('debugPrintlnAjaxAdmin');">Close</button> 
										           		</div>
										        		</span>										        		

										        <div class="text11" style="position: relative;" align="left">
												<span style="position:absolute;top:2px; width:250px;" id="47_info" class="popupWithInputText text11"  >
								           			<br/>
								           			<b>Afgifter [Sker på SKAT]</b>
													<br/><br/>
													Todo
													<br/>
													--
												</span>
										        </div>
										        </td>
										        </tr>
												<tr height="5"><td></td></tr>
												<tr>
													<td class="text12" align="left" >&nbsp;<span title="dkiva_abk1">Art</span></td>
			                							<td class="text12" align="left" >&nbsp;<span title="dkiva_abg1">Grundlag</span></td>
			                							<td class="text12" align="left" >&nbsp;<span title="dkiva_abs1">Sats</span></td>
			                							<td class="text12" align="left" >&nbsp;<span title="dkiva_abb1">Beløb</span></td>
			                							<td class="text12" align="left" >&nbsp;<span title="dkiva_aba1">Ber.art</span></td>
			                							
			                						</tr>
			 									<tr>
													<td class="text12MediumBlue">
														<input readonly tabindex=-1 type="text" class="inputTextReadOnly" name="dkiva_abk1" id="dkiva_abk1" size="8" value="${model.record.dkiva_abk1}">
													</td>
													<td class="text12MediumBlue">
														<input readonly tabindex=-1 type="text" class="inputTextReadOnly" name="dkiva_abg1" id="dkiva_abg1" size="8" value="${model.record.dkiva_abg1}">
													</td>
													<td class="text12MediumBlue">
														<input readonly tabindex=-1 type="text" class="inputTextReadOnly" name="dkiva_abs1" id="dkiva_abs1" size="8" value="${model.record.dkiva_abs1}">
													</td>
													<td class="text12MediumBlue">
														<input readonly tabindex=-1 type="text" class="inputTextReadOnly" name="dkiva_abb1" id="dkiva_abb1" size="8" value="${model.record.dkiva_abb1}">
													</td>
													<td class="text12MediumBlue">
														<input readonly tabindex=-1 type="text" class="inputTextReadOnly" name="dkiva_aba1" id="dkiva_aba1" size="8" value="${model.record.dkiva_aba1}">
													</td>
													
												</tr>
			 									<tr>
													<td class="text12MediumBlue">
														<input readonly tabindex=-1 type="text" class="inputTextReadOnly" name="dkiva_abk2" id="dkiva_abk2" size="8" value="${model.record.dkiva_abk2}">
													</td>
													<td class="text12MediumBlue">
														<input readonly tabindex=-1 type="text" class="inputTextReadOnly" name="dkiva_abg2" id="dkiva_abg2" size="8" value="${model.record.dkiva_abg2}">
													</td>
													<td class="text12MediumBlue">
														<input readonly tabindex=-1 type="text" class="inputTextReadOnly" name="dkiva_abs2" id="dkiva_abs2" size="8" value="${model.record.dkiva_abs2}">
													</td>
													<td class="text12MediumBlue">
														<input readonly tabindex=-1 type="text" class="inputTextReadOnly" name="dkiva_abb2" id="dkiva_abb2" size="8" value="${model.record.dkiva_abb2}">
													</td>
													<td class="text12MediumBlue">
														<input readonly tabindex=-1 type="text" class="inputTextReadOnly" name="dkiva_aba2" id="dkiva_aba2" size="8" value="${model.record.dkiva_aba2}">
													</td>
													
												</tr>
			 									<tr>
													<td class="text12MediumBlue">
														<input readonly tabindex=-1 type="text" class="inputTextReadOnly" name="dkiva_abk3" id="dkiva_abk3" size="8" value="${model.record.dkiva_abk3}">
													</td>
													<td class="text12MediumBlue">
														<input readonly tabindex=-1 type="text" class="inputTextReadOnly" name="dkiva_abg3" id="dkiva_abg3" size="8" value="${model.record.dkiva_abg3}">
													</td>
													<td class="text12MediumBlue">
														<input readonly tabindex=-1 type="text" class="inputTextReadOnly" name="dkiva_abs3" id="dkiva_abs3" size="8" value="${model.record.dkiva_abs3}">
													</td>
													<td class="text12MediumBlue">
														<input readonly tabindex=-1 type="text" class="inputTextReadOnly" name="dkiva_abb3" id="dkiva_abb3" size="8" value="${model.record.dkiva_abb3}">
													</td>
													<td class="text12MediumBlue">
														<input readonly tabindex=-1 type="text" class="inputTextReadOnly" name="dkiva_aba3" id="dkiva_aba3" size="8" value="${model.record.dkiva_aba3}">
													</td>
													
												</tr>
			 									<tr>
													<td class="text12MediumBlue">
														<input readonly tabindex=-1 type="text" class="inputTextReadOnly" name="dkiva_abk4" id="dkiva_abk4" size="8" value="${model.record.dkiva_abk4}">
													</td>
													<td class="text12MediumBlue">
														<input readonly tabindex=-1 type="text" class="inputTextReadOnly" name="dkiva_abg4" id="dkiva_abg4" size="8" value="${model.record.dkiva_abg4}">
													</td>
													<td class="text12MediumBlue">
														<input readonly tabindex=-1 type="text" class="inputTextReadOnly" name="dkiva_abs4" id="dkiva_abs4" size="8" value="${model.record.dkiva_abs4}">
													</td>
													<td class="text12MediumBlue">
														<input readonly tabindex=-1 type="text" class="inputTextReadOnly" name="dkiva_abb4" id="dkiva_abb4" size="8" value="${model.record.dkiva_abb4}">
													</td>
													<td class="text12MediumBlue">
														<input readonly tabindex=-1 type="text" class="inputTextReadOnly" name="dkiva_aba4" id="dkiva_aba4" size="8" value="${model.record.dkiva_aba4}">
													</td>
													
												</tr>
			 									<tr>
													<td class="text12MediumBlue">
														<input readonly tabindex=-1 type="text" class="inputTextReadOnly" name="dkiva_abk5" id="dkiva_abk5" size="8" value="${model.record.dkiva_abk5}">
													</td>
													<td class="text12MediumBlue">
														<input readonly tabindex=-1 type="text" class="inputTextReadOnly" name="dkiva_abg5" id="dkiva_abg5" size="8" value="${model.record.dkiva_abg5}">
													</td>
													<td class="text12MediumBlue">
														<input readonly tabindex=-1 type="text" class="inputTextReadOnly" name="dkiva_abs5" id="dkiva_abs5" size="8" value="${model.record.dkiva_abs5}">
													</td>
													<td class="text12MediumBlue">
														<input readonly tabindex=-1 type="text" class="inputTextReadOnly" name="dkiva_abb5" id="dkiva_abb5" size="8" value="${model.record.dkiva_abb5}">
													</td>
													<td class="text12MediumBlue">
														<input readonly tabindex=-1 type="text" class="inputTextReadOnly" name="dkiva_aba5" id="dkiva_aba5" size="8" value="${model.record.dkiva_aba5}">
													</td>
													
												</tr>
			 									<tr>
													<td class="text12MediumBlue">
														&nbsp;
													</td>
													<td class="text12MediumBlue">
														&nbsp;
													</td>
													<td class="text12MediumBlue">
														&nbsp;
													</td>
													<td class="text12MediumBlue">
														&nbsp;
													</td>
													<td class="text12MediumBlue">
														&nbsp;
													</td>
												</tr>
											</table>
 							    			</td>
 									</tr>
 									<tr height="15"><td></td></tr>
 									<tr>
										<td >&nbsp;</td>
										<td colspan="3">
											<table align="left" border="0" cellspacing="0" cellpadding="0">
							 					<tr height="1"><td></td></tr>
								 				<tr>
								 					<td colspan="2" class="text12Bold" align="left" >
									 					<img onMouseOver="showPop('toldvardidekl_info');" onMouseOut="hidePop('toldvardidekl_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
									 					<a style="color:#000000;" target="_blank" href="http://www.skat.dk/SKAT.aspx?oID=2089688&chk=209500&layout=353121">
										 					TOLDVÆRDIDEKLARATION&nbsp;<b>D.V.1</b>
										 				</a>
									 			
									 				<div class="text11" style="position: relative;" align="left">
													<span style="position:absolute;top:2px; width:250px;" id="toldvardidekl_info" class="popupWithInputText text11"  >
										           			<b>TOLDVÆRDIDEKLARATION D.V.1</b>
										           			<br/><br/>
										           			Toldværdideklarationen er en erklæring, der skal afgives af importøren ved forsendelser indeholdende værditoldpligtige varer, hvis toldværdi overstiger 10.000 EUR.
										           			<br/><br/>
										           			Deklarationen indeholder oplysninger om handelsforholdet mellem køber og sælger, om købs- og leveringsbetingelser samt om de beløb, 
										           			der skal tages i betragtning ved ansættelsen af toldværdien.
													</span>
													</div>
													</td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
									<td >&nbsp;</td>
									<td >
										<table align="left" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td colspan="3" class="text12">
												<button name="toldvardideklButton" class="buttonGray" type="button" onClick="showPop('toldvardidekl');" >Blankett</button> 
											        <span style="background-color:#EEEEEE; position:absolute; left:50px; top:500px; width:1100px; height:600px;" id="toldvardidekl" class="popupWithInputTextThickBorder"  >
										           		<div class="ownScrollableSubWindow" style="width:1080px; height:480px; margin:10px;">
										           			<nav>
										           			<table width="95%" border="0" align="left" cellspacing="2" cellpadding="0">
										           			<tr>
											           			<td colspan="3" class="text14"><b>TOLDVÆRDIDEKLARATION D.V.1</b></td>
											           		</tr>
											           		<%-- FIRST SECTION --%>
											           		<tr>
										           				<td>
										           				<table width="95%" border="0" class="formFrameTitaniumWhite" >
										           				<tr>
										           					<td class="text12" colspan="2">
																		&nbsp;<b>A. Beregningsgrundlag</b>
																	</td>															
																</tr>
										           				<tr>
										           					<td class="text11">
																		&nbsp;&nbsp;&nbsp;<span title="dkiv_t11a"><b>11a.</b>&nbsp;Nettopris i faktureret valuta. (Faktisk betalte pris eller pris, som
																		skulle betales ved afregning på fortoldningstidspunktet)</span>
																	</td>
																	<td class="text11">
																		&nbsp;<input readonly type="text" class="inputTextRight" name="dkiv_t11a" id="dkiv_t11a" size="20" maxlength="15" value="${model.record.dkiv_t11a}">
																	</td>
																</tr>
																<tr>	
																	<td class="text11">
																		&nbsp;&nbsp;&nbsp;<span title="dkiv_t11b"><b>11b.</b>&nbsp;Indirekte betalinger - se rubrik 8 b.</span>
																		&nbsp;(Omregningskurs: <font class="text11Gray"><b>${recordTopicSkat.dkih_221b}</b></font> )
																	</td>
																	<td class="text11">
																		&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputTextRight" name="dkiv_t11b" id="dkiv_t11b" size="20" maxlength="15" value="${model.record.dkiv_t11b}">
																	</td>
																</tr>
																<tr>	
																	<td class="text11">
																		&nbsp;&nbsp;&nbsp;<span title="dkiv_t12"><b>12</b>&nbsp;I alt (A) i national valuta (danske kroner)</span>
																	</td>
																	<td class="text11">
																		&nbsp;<input readonly type="text" class="inputTextRightBold" name="dkiv_t12" id="dkiv_t12" size="20" maxlength="15" value="${model.record.dkiv_t12}">
																	</td>
																</tr>
																
																</table>
																</td>
															</tr>
											           		<tr>
										           				<td>
										           				<table width="95%" border="0" class="formFrameTitaniumWhite" >
										           				<tr>
										           					<td class="text12" colspan="2">
																		&nbsp;<b>B. Tillæg:&nbsp;Omkostninger i national valuta, der ikke er medregnet under A ovenfor *)
																		Anfør eventuel relevant toldværdiafgørelse</b>
																	</td>															
																</tr>
																<%-- RUBRIK 13 --%>
																<tr>
										           					<td class="text12">
																		&nbsp;<b>13</b>&nbsp;Omkostninger, afholdt af køberen:
																	</td>
																</tr>
										           				<tr>
										           					<td class="text11">
																		&nbsp;&nbsp;&nbsp;<span title="dkiv_t13a"><b>a.</b>&nbsp;provision, undtagen indkøbsprovision</span>
																	</td>
																	<td class="text11">
																		&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputTextRight" name="dkiv_t13a" id="dkiv_t13a" size="20" maxlength="15" value="${model.record.dkiv_t13a}">
																	</td>
																</tr>
																<tr>	
																	<td class="text11">
																		&nbsp;&nbsp;&nbsp;<span title="dkiv_t13b"><b>b.</b>&nbsp;mæglerhonorar</span>
																	</td>
																	<td class="text11">
																		&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputTextRight" name="dkiv_t13b" id="dkiv_t13b" size="20" maxlength="15" value="${model.record.dkiv_t13b}">
																	</td>
																</tr>
																<tr>	
																	<td class="text11">
																		&nbsp;&nbsp;&nbsp;<span title="dkiv_t13c"><b>c</b>&nbsp;emballage og emballering .</span>
																	</td>
																	<td class="text11">
																		&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputTextRight" name="dkiv_t13c" id="dkiv_t13c" size="20" maxlength="15" value="${model.record.dkiv_t13c}">
																	</td>
																</tr>
																<tr height="10"><td></td></tr>
																<%-- RUBRIK 14 --%>
																<tr>
										           					<td class="text12" colspan="2">
																		&nbsp;<b>14</b>&nbsp;Varer og tjenesteydelser, leveret af køberen uden beregning eller til nedsat pris til brug ved fremstilling og salg til eksport af de indførte varer. 
																		<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Der anføres om nødvendigt forholdsmæssige værdier
																	</td>
																</tr>
																<tr>
										           					<td class="text11">
																		&nbsp;&nbsp;&nbsp;<span title="dkiv_t14a"><b>a.</b>&nbsp;materialer, komponenter, dele og lign. indgået i de indførte varer</span>
																	</td>
																	<td class="text11">
																		&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputTextRight" name="dkiv_t14a" id="dkiv_t14a" size="20" maxlength="15" value="${model.record.dkiv_t14a}">
																	</td>
																</tr>
																<tr>	
																	<td class="text11">
																		&nbsp;&nbsp;&nbsp;<span title="dkiv_t14b"><b>b.</b>&nbsp;værktøj, matricer, forme og lign., der er anvendt ved fremstillingen af
																		de indførte varer</span>
																	</td>
																	<td class="text11">
																		&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputTextRight" name="dkiv_t14b" id="dkiv_t14b" size="20" maxlength="15" value="${model.record.dkiv_t14b}">
																	</td>
																</tr>
																<tr>	
																	<td class="text11">
																		&nbsp;&nbsp;&nbsp;<span title="dkiv_t14c"><b>c</b>&nbsp;materialer, der er forbrugt ved fremstillingen af de indførte varer</span>
																	</td>
																	<td class="text11">
																		&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputTextRight" name="dkiv_t14c" id="dkiv_t14c" size="20" maxlength="15" value="${model.record.dkiv_t14c}">
																	</td>
																</tr>
																<tr>	
																	<td class="text11">
																		&nbsp;&nbsp;&nbsp;<span title="dkiv_t14d"><b>d</b>&nbsp;ingeniørarbejde, udviklingsarbejde, kunst- og designarbejde, tegninger og skitser, 
																		som er udført andetsteds end i Fællesskabet, <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;og som er nødvendige for fremstillingen af de indførte varer</span>
																	</td>
																	<td class="text11">
																		&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputTextRight" name="dkiv_t14d" id="dkiv_t14d" size="20" maxlength="15" value="${model.record.dkiv_t14d}">
																	</td>
																</tr>
																
																<tr height="10"><td></td></tr>
																<%-- RUBRIK 15 --%>
																<tr>
										           					<td class="text11">
																		&nbsp;<span title="dkiv_t15"><b><font class="text12">15</font></b>&nbsp;Royalty og licensafgifter - se rubrik 9 a</span>
																	</td>
																	<td class="text11">
																		&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputTextRight" name="dkiv_t15" id="dkiv_t15" size="20" maxlength="15" value="${model.record.dkiv_t15}">
																	</td>
																</tr>
																<tr height="10"><td></td></tr>
																<%-- RUBRIK 16 --%>
																<tr>
										           					<td class="text11">
																		&nbsp;<span title="dkiv_t16"><b><font class="text12">16</font></b>&nbsp;Værdien af enhver del af provenuet ved ethvert videresalg, <br/>
																		&nbsp;&nbsp;&nbsp;&nbsp;overdragelse eller anvendelse, som tilfalder sælgeren - se rubrik 9 b</span>
																	</td>
																	<td class="text11">
																		&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputTextRight" name="dkiv_t16" id="dkiv_t16" size="20" maxlength="15" value="${model.record.dkiv_t16}">
																	</td>
																</tr>
																<tr height="10"><td></td></tr>
																<%-- RUBRIK 17 --%>
																<tr>
										           					<td class="text12">
																		&nbsp;<b>17</b>&nbsp;Leveringsomkostninger til (indførselsstedet)
																	</td>
																	<td class="text11">
																		<c:forEach var="record" items="${model.toldstedCodeList}" >
																			<c:if test="${recordTopicSkat.dkih_a == record.dkkd_kd}"> 
																				&nbsp;<input style="text-align:right;" readonly type="text" class="inputTextReadOnly" name="dkiv_t17_readOnly" id="dkiv_t17_readOnly" size="20" maxlength="35" value="${record.dkkf_txt}">
																			</c:if> 
																		</c:forEach>
																	</td>
																</tr>
										           				<tr>
										           					<td class="text11">
																		&nbsp;&nbsp;&nbsp;<span title="dkiv_t17a"><b>a.</b>&nbsp;transport</span>
																	</td>
																	<td class="text11">
																		&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputTextRight" name="dkiv_t17a" id="dkiv_t17a" size="20" maxlength="15" value="${model.record.dkiv_t17a}">
																	</td>
																</tr>
																<tr>	
																	<td class="text11">
																		&nbsp;&nbsp;&nbsp;<span title="dkiv_t17b"><b>b.</b>&nbsp;lastning og håndtering</span>
																	</td>
																	<td class="text11">
																		&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputTextRight" name="dkiv_t17b" id="dkiv_t17b" size="20" maxlength="15" value="${model.record.dkiv_t17b}">
																	</td>
																</tr>
																<tr>	
																	<td class="text11">
																		&nbsp;&nbsp;&nbsp;<span title="dkiv_t17c"><b>c</b>&nbsp;forsikring</span>
																	</td>
																	<td class="text11">
																		&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputTextRight" name="dkiv_t17c" id="dkiv_t17c" size="20" maxlength="15" value="${model.record.dkiv_t17c}">
																	</td>
																</tr>
																<tr height="10"><td></td></tr>
																<%-- RUBRIK 18 --%>
																<tr>
										           					<td class="text12">
																		&nbsp;<b>18</b>&nbsp;I alt (B)
																	</td>
																	<td class="text11">
																		&nbsp;<input readonly type="text" class="inputTextRightBold" name="dkiv_t18" id="dkiv_t18" size="20" value="${model.record.dkiv_t18}">
																	</td>
																</tr>
																</table>
																</td>
															</tr>
															
															
															<tr>
										           				<td>
										           				<table width="95%" border="0" class="formFrameTitaniumWhite" >
										           				<tr>
										           					<td class="text12" colspan="2">
																		&nbsp;<b>C. Fradrag: Omkostninger i national valuta, der er medregnet under A ovenfor *)</b>
																	</td>															
																</tr>
										           				<tr>
										           					<td class="text11">
																		&nbsp;<span title="dkiv_t19"><b><font class="text12">19</font></b>&nbsp;Transportomkostninger efter ankomsten til indførselsstedet</span>
																	</td>
																	<td class="text11">
																		&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputTextRight" name="dkiv_t19" id="dkiv_t19" size="20" maxlength="15" value="${model.record.dkiv_t19}">
																	</td>
																</tr>
																<tr>
										           					<td class="text11">
																		&nbsp;<span title="dkiv_t20"><b><font class="text12">20</font></b>&nbsp;Omkostninger til bygnings-, installations- eller monteringsarbejder, 
																		ved ligeholdelse eller teknisk bistand, foretaget efter indførselen</span>
																	</td>
																	<td class="text11">
																		&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputTextRight" name="dkiv_t20" id="dkiv_t20" size="20" maxlength="15" value="${model.record.dkiv_t20}">
																	</td>
																</tr>
																<tr>
										           					<td class="text11">
																		&nbsp;<span title="dkiv_t21a/b"><b><font class="text12">21</font></b>&nbsp;Andre omkostninger (angiv arten):</span>
																		<input type="text" class="inputText" name="dkiv_t21a" id="dkiv_t21a" size="20" maxlength="15" value="${model.record.dkiv_t21a}">
																	</td>
																	<td class="text11">
																		&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputTextRight" name="dkiv_t21b" id="dkiv_t21b" size="20" maxlength="15" value="${model.record.dkiv_t21b}">
																	</td>
																</tr>
																
																<tr>
										           					<td class="text11">
																		&nbsp;<span title="dkiv_t22"><b><font class="text12">22</font></b>&nbsp;Told og afgifter, som skal betales i Fællesskabet i forbindelse med import
																		eller salg af varerne</span>
																	</td>
																	<td class="text11">
																		&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputTextRight" name="dkiv_t22" id="dkiv_t22" size="20" maxlength="15" value="${model.record.dkiv_t22}">
																	</td>
																</tr>
																
																<tr>
										           					<td class="text11">
																		&nbsp;<span title="dkiv_t23"><b><font class="text12">23</font></b>&nbsp;I alt (C)</span>
																	</td>
																	<td class="text11">
																		&nbsp;<input readonly type="text" class="inputTextRightBold" name="dkiv_t23" id="dkiv_t23" size="20" maxlength="15" value="${model.record.dkiv_t23}">
																	</td>
																</tr>
																
																</table>
																</td>
															</tr>
															<tr height="20"><td></td></tr>
															<tr>
										           				<td>
										           				<table width="95%" border="0" class="formFrameTitaniumWhite" >
										           				<tr>
										           					<td class="text12">
																		&nbsp;<span title="dkiv_t24"><b>24. Angivet værdi (A + B ÷ C)</b></span>&nbsp;&nbsp;<font class="text12Gray" style="font-style:italic;">[Statistisk værdi]</font>
																	</td>
																	<td class="text11" align="right">
																		&nbsp;<input readonly type="text" class="inputTextRightBold" name="dkiv_t24" id="dkiv_t24" size="20" maxlength="15" value="${model.record.dkiv_t24}">
																		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																	</td>															
																</tr>
										           				
																</table>
																</td>
															</tr>
															<tr height="30"><td></td></tr>
															<tr>
										           				<td>
										           				<table width="95%" class="formFrameTitaniumWhite" >
										           				<tr>
										           					<td class="text12" colspan="5">
																		&nbsp;Hvis beløbene skal betales i udenlandsk valuta, skal der nedenfor - med reference til rubrik og varepost nr. - anføres beløb i udenlandsk møntenhed og omregningskurs
																	</td>
																</tr>
																<tr>	
																	<td class="text11"><span title="dkiv_t25a">Reference</span></td>
																	<td class="text11"><span title="dkiv_t25b">Beløb</span></td>
																	<td class="text11"><span title="dkiv_t25c">Møntsort</span></td>
																	<td class="text11"><span title="dkiv_t25d">Omregningskurs</span></td>
																</tr>
																<tr>	
																	<td class="text11">
																		<input type="text" class="inputText" name="dkiv_t25a" id="dkiv_t25a" size="20" maxlength="35" value="">
																	</td>
																	<td class="text11">
																		<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="dkiv_t25b" id="dkiv_t25b" size="20" maxlength="15" value="${model.record.dkiv_t25b}">
																	</td>
																	<td class="text11">
																		<%-- Note: onChange event in jQuery for this currency list --%>
														 				<select name="dkiv_t25c" id="dkiv_t25c" >
														 				  <option value="">-vælg-</option>	
														 				  <c:forEach var="currency" items="${model.currencyCodeList}" >
													 				  		<option value="${currency.dkkd_kd}">${currency.dkkd_kd}</option>
																		  </c:forEach>  
																		</select>
																		<a tabindex="-1" class="text14" target="_blank" href="${model.skatCurrencyCodesURL.value}" onclick="${model.skatCurrencyCodesURL.windowOpenDimensions}" >
														            			<img style="cursor:pointer;" src="resources/images/find.png" border="0" alt="search" >
														            		</a>		
																	</td>
																	<td class="text11">
																		<input onKeyPress="return amountKey(event)" type="text" class="inputText" name="dkiv_t25d" id="dkiv_t25d" size="20" maxlength="7" value="${model.record.dkiv_t25d}">
																	</td>
																																
																</tr>
										           				
																</table>
																</td>
															</tr>
															
															</table>
															</nav>
														</div>
														<div>
															<table >
																<%-- OK BUTTON --%>
										           				<tr align="left" >
																	<td class="text11">&nbsp;&nbsp;&nbsp;<button name="toldvardideklButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('toldvardidekl');">&nbsp;<spring:message code="systema.skat.import.ok"/></button></td>
																	
																</tr>
															</table>
														</div>
													</span>
												</td>									
											</tr>
			        	        			</table>
					        			</td>
				        			</tr>
								<tr height="15"><td></td></tr>
				</table>
            	</td>
           	</tr>
            <tr height="30"><td></td></tr>
		</table>
		</td>
		</tr>
	</table>    
	
	</form>
		
<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->

