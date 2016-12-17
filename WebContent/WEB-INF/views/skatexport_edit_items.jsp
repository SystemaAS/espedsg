<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerSkat.jsp" />
<!-- =====================end header ==========================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/jquery.calculator.js"></SCRIPT>
	<SCRIPT type="text/javascript" src="resources/js/jquery-ui-timepicker-addon.js"></SCRIPT>
	<SCRIPT type="text/javascript" src="resources/js/skatexport_edit_items.js?ver=${user.versionEspedsg}"></SCRIPT>
	
	<style type = "text/css">
	.ui-datepicker { font-size:9pt;}
		
	.ui-timepicker-div .ui-widget-header { margin-bottom: 8px; }
	.ui-timepicker-div dl { text-align: left; }
	.ui-timepicker-div dl dt { float: left; clear:left; padding: 0 0 0 5px; }
	.ui-timepicker-div dl dd { margin: 0 10px 10px 40%; }
	.ui-timepicker-div td { font-size: 90%; }
	.ui-tpicker-grid-label { background: none; border: none; margin: 0; padding: 0; }
	
	.ui-timepicker-rtl{ direction: rtl; }
	.ui-timepicker-rtl dl { text-align: right; padding: 0 5px 0 0; }
	.ui-timepicker-rtl dl dt{ float: right; clear: right; }
	.ui-timepicker-rtl dl dd { margin: 0 40% 10px 10px; }
	
	</style>
	
	<%-- tab container component --%>
	<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
		<tr height="2"><td></td></tr>
		<tr height="25"> 
			<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a id="alinkTopicList" tabindex=-1 style="display:block;" href="skatexport.do?action=doFind&sign=${model.sign}">
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.skat.export.list.tab"/></font>
					<img valign="bottom" src="resources/images/list.gif" border="0" alt="general list">
					
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a id="alinkHeader" tabindex=-1 style="display:block;" href="skatexport_edit.do?action=doFetch&avd=${model.avd}&opd=${model.opd}
						&sysg=${model.sign}&tuid=${model.refnr}&syst=${model.status}&sydt=${model.datum}">
					
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.skat.export.created.mastertopic.tab"/></font>
					<font class="text12MediumBlue">[${model.opd}]</font>
					<c:if test="${model.status == 'M' || empty model.status || model.status == '11' || model.status == '20' || model.status == '97'}">
						<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
					</c:if>
				</a>
			</td>
			
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
					<a id="alinkInvoices" tabindex=-1 style="display:block;" href="skatexport_edit_invoice.do?action=doFetch&avd=${model.avd}&sign=${model.sign}
												&opd=${model.opd}&refnr=${model.refnr}
												&status=${model.status}&datum=${model.datum}">
						<font class="tabDisabledLink">&nbsp;<spring:message code="systema.skat.export.invoice.tab"/></font>
					</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="15%" valign="bottom" class="tab" align="center" nowrap>
				<font class="tabLink">&nbsp;<spring:message code="systema.skat.export.item.createnew.tab"/></font>
				<c:if test="${model.status == 'M' || empty model.status || model.status == '11' || model.status == '20' || model.status == '97'}">
					<img valign="bottom" src="resources/images/add.png" width="12" hight="12" border="0" alt="create new">
				</c:if>
				
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a id="alinkLogging" tabindex=-1 style="display:block;" href="skatexport_logging.do?avd=${model.avd}&sign=${model.sign}&opd=${model.opd}&refnr=${model.refnr}
													&status=${model.status}&datum=${model.datum}">
					<font class="tabDisabledLink">
						&nbsp;<spring:message code="systema.skat.export.logging.tab"/>
					</font>
					<img style="vertical-align: bottom" src="resources/images/log-icon.png" width="16" hight="16" border="0" alt="show log">
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a id="alinkArchive" tabindex=-1 style="display:block;" href="skatexport_archive.do?avd=${model.avd}&sign=${model.sign}&opd=${model.opd}&refnr=${model.refnr}
													&status=${model.status}&datum=${model.datum}"">
					<font class="tabDisabledLink">
						&nbsp;<spring:message code="systema.skat.export.archive.tab"/>
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
				 				&nbsp;&nbsp;&nbsp;&nbsp;Ref.nr.:&nbsp;<b>${model.refnr}</b>
				 				&nbsp;&nbsp;
				 				<img onMouseOver="showPop('status_info');" onMouseOut="hidePop('status_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 				Status:&nbsp;<b>${model.status}</b>
				 				&nbsp;&nbsp;Angivelsesart:&nbsp;<b>${recordTopicSkat.dkeh_aart}</b>
				 				
				 				<div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="status_info" class="popupWithInputText text11"  >
					           		<br/>
					           		Kun status <b>M</b>,<b>11</b>,<b>20</b> eller <b>' '</b> kan redigeres. 
					           			<ul>
					           				<c:forEach var="record" items="${model.statusCodeList}" >
							           			<li><b>${record.dkkd_kd}&nbsp;-${record.dkkd_kd2}</b>&nbsp;${record.dkkf_txt}</li>
						           			</c:forEach>
					           				
					           			</ul>
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
							        		<td width="30%" class="text11" align="left">CVR/SE-nr&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordTopicSkat.dkeh_02a}</td>
							           	
							        </tr>
							        <tr>
							            <td width="30%" class="text11" align="left">Navn&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordTopicSkat.dkeh_02b}</td>
							        </tr>
									<tr>
							            <td width="30%" class="text11" align="left">Adresse&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordTopicSkat.dkeh_02c}</td>
							        </tr>
							        <tr>
							            <td width="30%" class="text11" align="left">Postnr.&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordTopicSkat.dkeh_02d}&nbsp;${recordTopicSkat.dkeh_02e}</td>
							        </tr>
							        
									<tr>
							            <td width="30%" class="text11" align="left">Landkode&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordTopicSkat.dkeh_02f}</td>
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
							            <td width="30%" class="text11" align="left">EORI&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordTopicSkat.dkeh_08a}</td>
							        </tr>
							        <tr>
							            <td width="30%" class="text11" align="left">Navn&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordTopicSkat.dkeh_08b}</td>
							        </tr>
									<tr>
							            <td width="30%" class="text11" align="left">Adresse&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordTopicSkat.dkeh_08c}</td>
							        </tr>
							        <tr>
							            <td width="30%" class="text11" align="left">Postnr.&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordTopicSkat.dkeh_08d}&nbsp;${recordTopicSkat.dkeh_08e}</td>
							        </tr>
							        
									<tr>
							            <td width="30%" class="text11" align="left">Landkode&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordTopicSkat.dkeh_08f}</td>
							        </tr>
							        <%--
									<tr>
							            <td width="30%" class="text11" align="left">Handläggare&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordTopicSkat.svih_moha}</td>
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
							<form id="createNewItemLine" method="post">
								<input type="hidden" name="action" id="action" value='doFetch'>
				 				<input type="hidden" name="avd" id="avd" value='${model.avd}'>
				 				<input type="hidden" name="renew" id="renew" value='1'>
				 				<input type="hidden" name="sign" id="sign" value='${model.sign}'>
								<input type="hidden" name="opd" id="opd" value='${model.opd}'>
				 				<input type="hidden" name="refnr" id="refnr" value='${model.refnr}'>
				 				<input type="hidden" name="status" id="status" value='${model.status}'>
				 				<input type="hidden" name="datum" id="datum" value='${model.datum}'>
				 				<input type="hidden" name="fabl" id="fabl" value='${recordTopicSkat.dkeh_222}'>
				 				<input type="hidden" name="totalGrossWeight" id="totalGrossWeight" value='${recordTopicSkat.dkeh_brut}'>
				 				
				 										
								<table width="100%" cellspacing="0" border="0" cellpadding="0">
									<tr>
										<td class="text12Bold">
											<c:if test="${model.status == 'M' || empty model.status || model.status == '11' || model.status == '20' || model.status == '97'}">
												<input tabindex=-1 class="inputFormSubmitStd" type="submit" name="submit" onclick="javascript: form.action='skatexport_edit_items.do';" value="<spring:message code="systema.skat.export.item.line.init.createnew.submit"/>">
											</c:if>
											<button name="allItemsButton" class="inputFormSubmitStd" type="button" onClick="showPop('allItems');" >Vis alle</button> 
										        <span style="background-color:#EEEEEE; position:absolute; left:50px; top:200px; width:1200px; height:1000px;" id="allItems" class="popupWithInputTextThickBorder"  >
									           		<table id="containerdatatableTable" width="96%" align="left" >
													<tr>
													<td class="text12">
												
														<table id="tblItemLinesAll" class="display compact cell-border">
										           			<thead>
												           	<tr style="background-color:#DDDDDD">	
																    <th class="text12">&nbsp;Linjenr.&nbsp;</th> 
												                    <th class="text12">&nbsp;<spring:message code="systema.skat.export.item.list.label.dkev_28b.purchaseSellerInvoice"/>&nbsp;</th>
												                    <th class="text12" >&nbsp;<spring:message code="systema.skat.export.item.list.label.dkev_32.varepostNr"/>&nbsp;</th>   
												                    <th class="text12" >&nbsp;<spring:message code="systema.skat.export.item.list.label.dkev_34a.oprLand"/>&nbsp;</th>
												                    <th class="text12" >&nbsp;<spring:message code="systema.skat.export.item.list.label.dkev_331.varekod"/>&nbsp;</th>
												                    <th class="text12" >&nbsp;<spring:message code="systema.skat.export.item.list.label.dkev_37.procedure"/>&nbsp;</th>
												                    <th class="text12">&nbsp;<spring:message code="systema.skat.export.item.list.label.dkev_35.bruttov"/>&nbsp;</th>
												                    <th class="text12">&nbsp;<spring:message code="systema.skat.export.item.list.label.dkev_38.nettov"/>&nbsp;</th>
												                    <th class="text12">&nbsp;<spring:message code="systema.skat.export.item.list.label.dkev_412.supplEnhVerdi"/>&nbsp;</th>
												                    <th class="text12">&nbsp;<spring:message code="systema.skat.export.item.list.label.dkev_313.kolliAntal"/>(&Sigma;)</th>
												                    <th class="text12">&nbsp;<spring:message code="systema.skat.export.item.list.label.dkev_315.vareDescription"/>&nbsp;</th>
												                    <th class="text12">&nbsp;<spring:message code="systema.skat.export.item.list.label.dkev_46.statValue"/>&nbsp;</th>
												                    <th class="text12" >&nbsp;<spring:message code="systema.skat.export.item.list.label.dkev_42.varansPris"/>&nbsp;</th>
												                    <th class="text12" >&nbsp;<spring:message code="systema.skat.export.item.list.label.dkerr.error"/>&nbsp;</th>
												                    <c:if test="${model.status == 'M' || empty model.status || model.status == '11' || model.status == '20' || model.status == '97'}">
													                    	<th align="center" class="text12" nowrap>Fjern</th>
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
														               <td width="2%" class="text11" align="center">&nbsp;${record.dkev_syli}
														               		<%--
														               		<a tabindex=-1 id="recordUpdate_${record.dkev_syli}_${record.dkev_32}" href="#" onClick="getItemData(this);">${record.dkev_syli}
														               			<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">&nbsp;
														               		</a>
														               		--%>
														               </td>
														               <td class="text11" >&nbsp;${record.dkev_28b}</td>
														               <td width="2%" class="text11" >&nbsp;${record.dkev_32}</td>
														               <td class="text11" >&nbsp;${record.dkev_34a}</td>
														               <td class="text11" >&nbsp;${record.dkev_331}</td>
														               <td class="text11" >&nbsp;${record.dkev_37}</td>
														               <td class="text11" >&nbsp;${record.dkev_35}</td>
														               <td class="text11" >&nbsp;${record.dkev_38}</td>
														               <td class="text11" >&nbsp;${record.dkev_412}</td>
														               <td class="text11" >&nbsp;${record.dkev_313}</td>
														               <td class="text11" ><div style="width:120px" >&nbsp;${record.dkev_315}</div></td>
				               							               <td class="text11">&nbsp;${record.dkev_46}</td>
														               <td class="text11">&nbsp;${record.dkev_42}</td>
														               <td align="center" class="text11">&nbsp;
														               		<c:if test="${not empty record.dkev_err}">
														               			<img src="resources/images/redFlag.png" width="18px" height="18px" border="0" alt="remove">
														               		</c:if>
														               </td>
														               <c:if test="${model.status == 'M' || empty model.status || model.status == '11' || model.status == '20' || model.status == '97'}">	
															               <td class="text11" align="center" >
															               	<a onclick="javascript:return confirm('Er du sikker på at du vil slette denne?')" tabindex=-1 href="skatexport_edit_items.do?action=doDelete&avd=${record.dkev_syav}&opd=${record.dkev_syop}&lin=${record.dkev_syli}&fabl=${recordTopicSkat.dkeh_222}">
															               		<img src="resources/images/delete.gif" border="0" alt="remove">
															               	</a>	
															               </td>
														               </c:if>
														            </tr>
														            <%-- this param is used ONLY in this JSP --%>
															        <c:set var="totalNumberOfItemLines" value="${counter.count}" scope="request" /> 
															        <%-- this param is used throughout the Controller --%>
															        <c:set var="numberOfItemLinesInTopic" value="${record.dkev_syli}" scope="request" /> 
															        
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
											 	        		<a href="skatExportListExcelView.do" target="_new">
										                 		<img valign="bottom" id="itemListExcel" src="resources/images/excel.png" border="0" alt="excel">&nbsp;Excel
											 	        		</a>
											 	        		&nbsp;
												 		</td>
													</tr>
													</table>
										   			</div>
								   				</span>
								   					
								   			<c:if test="${model.status == 'M' || empty model.status || model.status == '11' || model.status == '20' || model.status == '97'}">		
												&nbsp;<button title="Kontrollere vareposter" name="itemListControlButton" id="itemListControlButton" class="buttonGrayWithGreenFrame11" type="button" >Kontrollere vareposter</button>
											</c:if>		
								   				
										</td>
									</tr>
									<tr>
										<td class="text12Bold">&nbsp;Antal vareposter&nbsp;&nbsp;<font class="text12MediumBlue"><b>${totalNumberOfItemLines}</b></font>
										</td>
										<td width="10%" class="text12">&nbsp;</td>
										<td align="right" class="text11">Bruttovægt:&nbsp;
											<input tabindex=-1 align="right" type="text" readonly class="inputText11BlueBoldReadOnly" size="12" maxlength=11" value='${recordTopicSkat.dkeh_brut}'>
										</td>
										<td align="right" class="text11">Faktura beløb:&nbsp;
											<input tabindex=-1 align="right" type="text" readonly class="inputText11BlueBoldReadOnly" size="12" maxlength=20" value='${recordTopicSkat.dkeh_222}'>
											&nbsp;<font style="color:#000080; font-style: italic;"><b>${recordTopicSkat.dkeh_221}</b></font>
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
												size="16" maxlength=20" value='${model.recordItemContainerTopic.diffItemLinesTotalAmountWithInvoiceTotalAmount}'>
										</td>
									</tr>									
								</table>
							</form>
							</td>
						</tr> 
						
						<tr>
							<td >
								<form name="formItemList" id="formItemList" method="POST" >
			               		<input type="hidden" name="opdItemList" id="opdItemList" value="${model.opd}">
		 						<input type="hidden" name="avdItemList" id="avdItemList" value="${model.avd}"> 
		 						<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
							
								<table id="containerdatatableTable" cellspacing="2" align="left" >
								<tr>
								<td class="text11">
							
								<table id="tblItemLines" class="display compact cell-border">
									<thead>
										<tr style="background-color:#DDDDDD">
									    <th class="text11">&nbsp;Lin&nbsp;</th>
									    <th class="text11">&nbsp;Opd.&nbsp;</th>
									    <th class="text11">&nbsp;<spring:message code="systema.skat.export.item.list.label.dkev_28b.purchaseSellerInvoice"/>&nbsp;</th>
					                    <th class="text11" nowrap>&nbsp;<spring:message code="systema.skat.export.item.list.label.dkev_32.varepostNr"/>&nbsp;</th>   
					                    <th class="text11" nowrap>&nbsp;<spring:message code="systema.skat.export.item.list.label.dkev_34a.oprLand"/>&nbsp;</th>
					                    <th class="text11" nowrap>&nbsp;<spring:message code="systema.skat.export.item.list.label.dkev_331.varekod"/>&nbsp;</th>
					                    <th class="text11" nowrap>&nbsp;<spring:message code="systema.skat.export.item.list.label.dkev_37.procedure"/>&nbsp;</th>
					                    <th class="text11">&nbsp;<spring:message code="systema.skat.export.item.list.label.dkev_35.bruttov"/>&nbsp;</th>
					                    <th class="text11">&nbsp;<spring:message code="systema.skat.export.item.list.label.dkev_38.nettov"/>&nbsp;</th>
					                    <th class="text11">&nbsp;<spring:message code="systema.skat.export.item.list.label.dkev_412.supplEnhVerdi"/>&nbsp;</th>
					                    <th class="text11">&nbsp;<spring:message code="systema.skat.export.item.list.label.dkev_313.kolliAntal"/>(&Sigma;)</th>
					                    <th class="text11">&nbsp;<spring:message code="systema.skat.export.item.list.label.dkev_315.vareDescription"/>&nbsp;</th>
					                    <th class="text11">&nbsp;<spring:message code="systema.skat.export.item.list.label.dkev_46.statValue"/>&nbsp;</th>
					                    <th class="text11" nowrap>&nbsp;<spring:message code="systema.skat.export.item.list.label.dkev_42.varansPris"/>&nbsp;</th>
					                    <th class="text12" >&nbsp;<spring:message code="systema.skat.export.item.list.label.dkerr.error"/>&nbsp;</th>
										<c:if test="${model.status == 'M' || empty model.status || model.status == '11' || model.status == '20' || model.status == '97'}">
					                    	<th align="center" class="text11" nowrap>Fjern</th>
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
							               <td width="4%" class="text11" align="center">${record.dkev_syli}</td>
   							               <td width="4%" class="text11" align="center">&nbsp;
							               		<%--<a id="recordUpdate_${counter.count}_${record.sviv_vano}" href="#" onClick="getItemData(this);">${record.sviv_syli} --%>
							               		<a tabindex=-1 title="${counter.count}" id="recordUpdate_${record.dkev_syli}_${record.dkev_32}" href="#" onClick="getItemData(this);">
							               			<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">&nbsp;
							               		</a>
							               </td>
							               <td class="text11" >&nbsp;${record.dkev_28b}</td>
							               <td width="2%" class="text11" >&nbsp;${record.dkev_32}</td>
							               <td class="text11" >&nbsp;${record.dkev_34a}</td>
							               <td class="text11" >&nbsp;${record.dkev_331}</td>
							               <td class="text11" >&nbsp;${record.dkev_37}</td>
							               <td class="text11" >&nbsp;${record.dkev_35}</td>
							               <td class="text11" >&nbsp;${record.dkev_38}</td>
							               <td class="text11" >&nbsp;${record.dkev_412}</td>
							               <td class="text11" >&nbsp;${record.dkev_313}</td>
							               <td class="text11" ><div style="width:120px" >&nbsp;${record.dkev_315}</div></td>
							               <td class="text11">&nbsp;${record.dkev_46}</td>
   							               <td class="text11">&nbsp;${record.dkev_42}</td>
   							               <td align="center" class="text11">&nbsp;
							               		<c:if test="${not empty record.dkev_err}">
							               			<img src="resources/images/redFlag.png" width="18px" height="18px" border="0" alt="remove">
							               		</c:if>
							               </td>
   							               <c:if test="${model.status == 'M' || empty model.status || model.status == '11' || model.status == '20' || model.status == '97'}">	
								               <td class="text11" align="center" nowrap>&nbsp;
								               	<a onclick="javascript:return confirm('Er du sikker på at du vil slette denne?')" tabindex=-1 href="skatexport_edit_items.do?action=doDelete&avd=${record.dkev_syav}&opd=${record.dkev_syop}&lin=${record.dkev_syli}&fabl=${recordTopicSkat.dkeh_222}">
								               		<img valign="bottom" src="resources/images/delete.gif" border="0" alt="remove">
								               	</a>	
								               </td>
							               </c:if>
							            </tr>
								        <%-- <c:set var="numberOfItemLinesInTopic" value="${counter.count}" scope="request" />  --%>
								        <c:set var="numberOfItemLinesInTopic" value="${record.dkev_syli}" scope="request" /> 
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
	 			<form name="tdsExportEditTopicItemForm" id="tdsExportEditTopicItemForm" action="skatexport_edit_items.do" method="post">
				 	<%--Required key parameters from the Topic parent --%>
				 	<input type="hidden" name="action" id="action" value='doUpdate'/>
				 	<input type="hidden" name="opd" id="opd" value="${model.opd}"/>
				 	<input type="hidden" name="avd" id="avd" value="${model.avd}"/>
				 	<input type="hidden" name="sign" id="sign" value="${model.sign}"/>
				 	<input type="hidden" name="refnr" id="refnr" value="${model.refnr}"/>
				 	<input type="hidden" name="status" id="status" value="${model.status}"/>
				 	<input type="hidden" name="datum" id="datum" value="${model.datum}"/>
				 	<input type="hidden" name="fabl" id="fabl" value="${recordTopicSkat.dkeh_222}"/>
				 	
				 	<input type="hidden" name="dkev_syli" id="dkev_syli" value=''/>
				 	<input type="hidden" name="dkev_32" id="dkev_32" value='${model.record.dkev_32}'/>
				 	
				 	<input type="hidden" name="numberOfItemLinesInTopic" id="numberOfItemLinesInTopic" value="${numberOfItemLinesInTopic}" />
				 	<input type="hidden" name="lastSelectedItemLineNumber" id="lastSelectedItemLineNumber" value="${model.recordItemContainerTopic.lastSelectedItemLineNumber}" />
				 	
				 	<%-- Topic ITEM CREATE --%>
	 				<table width="100%" align="center" class="formFrameHeader" border="1" cellspacing="0" cellpadding="0">
				 		<tr height="15">
				 			<td class="text12White" align="left">
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
					           		<div class="text11" align="left" style="display:block;width:700px;word-break:break-all;">
					           			${activeUrlRPGUpdate_Skat}<br/><br/>
					           			<button name="updateInformationButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('updateInfo');">Close</button> 
					           		</div>
						        </span>  
				 				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				 				<c:choose>
					 				<c:when test="${not empty model.record.dkev_syli}">
				 						<input title="from model" tabindex=-1 align="center" class="text12BoldLightGreenForItemLinenr" readonly type="text" name="lineNr" id="lineNr" size="3" value='${model.record.dkev_syli}'>
									</c:when>
									<c:otherwise>
										<input title="from session" tabindex=-1 align="center" class="text12BoldLightGreenForItemLinenr" readonly type="text" name="lineNr" id="lineNr" size="3" value='${dkev_syli_SESSION}'>
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
							            <c:if test="${recordTopicSkat.dkeh_aart != '22' && recordTopicSkat.dkeh_aart != '23' && recordTopicSkat.dkeh_aart != '50'}">
							            		<font class="text16RedBold" >*</font>
							            </c:if>
							            <span title="dkev_331">Varekode</span>
						            	 
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
							            <img onMouseOver="showPop('37_1_info');" onMouseOut="hidePop('37_1_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 						<b>37.</b>
				 						<c:if test="${recordTopicSkat.dkeh_aart != '22' && recordTopicSkat.dkeh_aart != '23' && recordTopicSkat.dkeh_aart != '30' &&
				 									 recordTopicSkat.dkeh_aart != '50'}">
							            		<font class="text16RedBold" >*</font>
							            </c:if>
				 						<span title="dkev_37">Procedure</span>
				 						
							            <div class="text11" style="position: relative;" align="left">
										<span style="position:absolute;top:2px; width:250px;" id="37_1_info" class="popupWithInputText text11"  >	
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
						           			<br/><br/>
										</span>
										</div>
										</td>
							            
							            <td class="text12" align="left" nowrap><b>42.</b>
							            <%-- Varens pris IS NOT MAPPED to the EDIFACT but must be a part of the user input (required fields exactly the same as Stat.Værdi) --%>
				 						<c:if test="${recordTopicSkat.dkeh_aart != '22' && recordTopicSkat.dkeh_aart != '23' && recordTopicSkat.dkeh_aart != '30' &&
				 									 recordTopicSkat.dkeh_aart != '50'}">
							            		<font class="text16RedBold" >*</font>
							            </c:if>
							            	<span title="dkev_42">Varens pris:&nbsp;</span>
						            		</td>
						            		
							            <td class="text12" align="left">
							            <img onMouseOver="showPop('35_info');" onMouseOut="hidePop('35_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 						<b>35.</b>
				 						<c:if test="${recordTopicSkat.dkeh_aart != '50'}">
							            		<font class="text16RedBold" >*</font>
							            </c:if>
				 						<span title="dkev_35">Brut.(kg)</span>
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
										<c:if test="${recordTopicSkat.dkeh_aart != '22' && recordTopicSkat.dkeh_aart != '23' && recordTopicSkat.dkeh_aart != '30'&& recordTopicSkat.dkeh_aart != '50'}">
							            		<font class="text16RedBold" >*</font>
							            </c:if>
										<span title="dkev_38">Net.(kg)</span></td>
										
										
										<td class="text12" align="left" >
							            <img onMouseOver="showPop('31_gods_info');" onMouseOut="hidePop('31_gods_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 						<b>31.1</b>
				 						<c:if test="${recordTopicSkat.dkeh_aart != '22' && recordTopicSkat.dkeh_aart != '23' && recordTopicSkat.dkeh_aart != '50'}">
							            		<font class="text16RedBold" >*</font>
							            </c:if>
							            <span title="dkev_311">Kolli mærke&nbsp;</span>
							            
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
						            		<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlueMandatoryField" name="dkev_331" id="dkev_331" size="12" maxlength="8" value="${model.record.dkev_331}">
							            	<a tabindex="-1" id="dkev_331IdLink">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
											</a>
							            </td>
									
										<td align="left">
											<select class="inputTextMediumBlueMandatoryField" name="dkev_37" id="dkev_37">
						 						<option value="">-vælg-</option>
							 				  	<c:forEach var="code" items="${model.procedureKoderR37CodeList}" >
							 				  		<option value="${code.dkkd_kd}"<c:if test="${model.record.dkev_37 == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
												</c:forEach>  
											</select>	
											<a tabindex="-1" id="dkev_37IdLink" OnClick="triggerChildWindowGeneralCodes(this, '112')">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
											</a>	
						 				</td>
						 				<td class="text12" align="left" nowrap>
							            		<input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlueMandatoryField" name="dkev_42" id="dkev_42" size="14" maxlength="13" value="${model.record.dkev_42}">
							            	</td>
								        	<td class="text12" align="left"><input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlueMandatoryField" name="dkev_35" id="dkev_35" size="13" maxlength="12" value="${model.record.dkev_35}"></td>
										<td class="text12" align="left"><input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlueMandatoryField" name="dkev_38" id="dkev_38" size="13" maxlength="12" value="${model.record.dkev_38}"></td>
										<td class="text12" align="left"><input type="text" class="inputTextMediumBlueMandatoryField" name="dkev_311" id="dkev_311" size="17" maxlength="16" value="${model.record.dkev_311}"></td>
							        </tr>

							        <tr height="10"><td class="text" align="left" colspan="12"><hr></td></tr>
							        
									<tr>
										<td class="text12" >
										<img onMouseOver="showPop('31_kantal_info');" onMouseOut="hidePop('31_kantal_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
										<span id="kotaRubrik">
											<b>31.3</b>
										</span>
										<c:if test="${recordTopicSkat.dkeh_aart != '22' && recordTopicSkat.dkeh_aart != '23' && recordTopicSkat.dkeh_aart != '50'}">
							            		<font class="text16RedBold" >*</font>
							            </c:if>
							            <span title="dkev_313">Kolli antal</span>
				 						
										<div class="text11" style="position: relative;" align="left">
										<span style="position:absolute;top:2px; width:250px;" id="31_kantal_info" class="popupWithInputText text11"  >
							           		<b>31.3 Kolliantal</b>
						           			<br/><br/>
						           			Her anfører klarereren det samlede antal kolli, der er omfattet af vareposten.
										</span>
										</div>
										</td>
										
										<td class="text12" >
										<img onMouseOver="showPop('31_kslag_info');" onMouseOut="hidePop('31_kslag_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 						<b>31.4</b>
										<c:if test="${recordTopicSkat.dkeh_aart != '22' && recordTopicSkat.dkeh_aart != '23' && recordTopicSkat.dkeh_aart != '50'}">
							            		<font class="text16RedBold" >*</font>
							            </c:if>
							            <span title="dkev_314">Kolli art</span>
										
							           	<div class="text11" style="position: relative;" align="left">
										<span style="position:absolute;top:2px; width:250px;" id="31_kslag_info" class="popupWithInputText text11"  >	
						           			<b>31.4 Kolli art</b>
											<br/>
						           			Her beskriver klarereren, hvordan varerne er pakket. Emballagekoderne findes på SKATs hjemmeside.
										</span>
										</div>
										</td>
										
										<td class="text12" align="left" colspan="2">
										<img onMouseOver="showPop('31_varubesk_info');" onMouseOut="hidePop('31_varubesk_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 						<b>31.5</b>
										<c:if test="${recordTopicSkat.dkeh_aart != '22' && recordTopicSkat.dkeh_aart != '23' && recordTopicSkat.dkeh_aart != '50'}">
							            		<font class="text16RedBold" >*</font>
							            </c:if>
										<span title="dkev_315">Varebeskrivelse&nbsp;</span>
										
							           	<div class="text11" style="position: relative;" align="left">
										<span style="position:absolute;top:2px; width:250px;" id="31_varubesk_info" class="popupWithInputText text11"  >	
						           			<b>Varebeskrivelse</b>
											<br/><br/>
						           			En varebeskrivelse er en beskrivelse, der er tilstrækkelig entydig til at fastslå varekoden i Toldtariffen, og som muliggør en øjeblikkelig og sikker identifikation af varen.							           			
										</span>
										</div>
							            </td>
							            
							            <td class="text12" align="left" >
										<img onMouseOver="showPop('41_info');" onMouseOut="hidePop('41_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 						<b>41.</b><span title="dkev_412">Suppl.enh.verdi</span>/<span title="dkev_411">enh.</span>
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
										<td class="text12" align="left">&nbsp;<span title="dkev_28b">Køb/Salgsfakt.</span></td>
										
							        </tr>
									<tr>
					           			<td class="text11" valign="bottom">
											&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlueMandatoryField" name="dkev_313" id="dkev_313" size="7" maxlength="6" value="${model.record.dkev_313}">
										</td>
										<td align="left" nowrap>
					            			<select class="inputTextMediumBlueMandatoryField" name="dkev_314" id="dkev_314">
							            		<option value="">-vælg-</option>
							 				  	<c:forEach var="code" items="${model.emballageCodeList}" >
			                                	 	<option value="${code.dkkd_kd}"<c:if test="${model.record.dkev_314 == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
												</c:forEach> 
											</select>
											<a tabindex="-1" id="dkev_314IdLink" OnClick="triggerChildWindowGeneralCodes(this, '110')">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
											</a>	
										
				            				</td> 										
										<td class="text12" align="left" colspan="2"><input type="text" class="inputTextMediumBlueMandatoryField" name="dkev_315" id="dkev_315" size="40" maxlength="45" value="${model.record.dkev_315}"></td>
          										<td class="text12" align="left" nowrap>
											<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="dkev_412" id="dkev_412" size="10" maxlength="9" value="${model.record.dkev_412}">
											<select name="dkev_411" id="dkev_411">
						 						<option value="">-vælg-</option>
							 				  	<c:forEach var="code" items="${model.uomKoderR411CodeList}" >
							 				  		<option value="${code.dkkd_kd}"<c:if test="${model.record.dkev_411 == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
												</c:forEach>  
											</select>
											<a tabindex="-1" id="dkev_411IdLink" OnClick="triggerChildWindowGeneralCodes(this, '022')">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
											</a>
										</td>
										<td class="text12" >
											<input type="text" class="inputText" name="dkev_28b" id="dkev_28b" size="20" maxlength="17" value="${model.record.dkev_28b}">
										</td>
 							        </tr>
 							        <tr height="5"><td ></td></tr>
 							        <tr height="20"><td class="text" align="left" colspan="12"><hr></td></tr>
							        <%-- Session fields needed for the AJAX calculation av Stat.værdi --%>
							        <input type="hidden" name="session_dkeh_221" id="session_dkeh_221" value="${recordTopicSkat.dkeh_221}">
							        <input type="hidden" name="session_dkeh_221b" id="session_dkeh_221b" value="${recordTopicSkat.dkeh_221b}">
							        <input type="hidden" name="session_dkeh_222" id="session_dkeh_222" value="${recordTopicSkat.dkeh_222}">
							        <%-- Model fields needed for the AJAX calculation av Bilagda handlingar --%>
							        <input type="hidden" name="model_avd" id="model_avd" value="${model.avd}">
							        <input type="hidden" name="model_opd" id="model_opd" value="${model.opd}">
							        
							        <tr>
							            
							        		
							        		<td class="text12" align="left">
							 			<img onMouseOver="showPop('34a_info');" onMouseOut="hidePop('34a_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 						<b>34.a</b>
				 						
				 						<c:if test="${recordTopicSkat.dkeh_aart=='31' || recordTopicSkat.dkeh_aart=='32'}">
							            		<font class="text16RedBold" >*</font>
							            </c:if>
							            <span title="dkev_34a">Opr.land</span>
										
							           	<div class="text11" style="position: relative;" align="left">
										<span style="position:absolute;top:2px; width:250px;" id="34a_info" class="popupWithInputText text11"  >	
						           			<b>34.a Oprindelsesland</b>
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
				 						<c:if test="${recordTopicSkat.dkeh_aart != '22' && recordTopicSkat.dkeh_aart != '23' && recordTopicSkat.dkeh_aart != '30' &&
				 									 recordTopicSkat.dkeh_aart != '50'}">
							            		<font class="text16RedBold" >*</font>
							            </c:if>
							            <span title="dkev_46">Statistisk værdi:&nbsp;</span>
							            
							           	<div class="text11" style="position: relative;" align="left">
										<span style="position:absolute;top:2px; width:250px;" id="46_info" class="popupWithInputText text11"  >	
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
							           	<td colspan="3" class="text12" align="left" >
								            <img onMouseOver="showPop('40_info');" onMouseOut="hidePop('40_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					 						<b>40.</b>
					 						<c:if test="${recordTopicSkat.dkeh_aart != '50' }">
								            		<font class="text16RedBold" >*</font>
								            </c:if>
					 						<span title="dkev_401/dkev_402/dkev_403">Summarisk angivelse / Forudgående dokument:</span>
								            
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
											</span>
											</div>
						           		</td>
							        </tr>
							        <tr>
										<td align="left">
								            	<select name="dkev_34a" id="dkev_34a">
						 						<option value="">-vælg-</option>
							 				  	<c:forEach var="country" items="${model.countryCodeList}" >
							 				  		<option value="${country.dkkd_kd}"<c:if test="${model.record.dkev_34a == country.dkkd_kd}"> selected </c:if> >${country.dkkd_kd}</option>
												</c:forEach>  
											</select>
											<a tabindex="-1" id="dkev_34aIdLink" OnClick="triggerChildWindowGeneralCodes(this, '008')">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
											</a>																	 			
										</td>
										<td class="text12" align="left"><input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlueMandatoryField" name="dkev_46" id="dkev_46" size="16" maxlength="15" value="${model.record.dkev_46}"></td>
			           					
										<td colspan="3">
										<table>
										<tr>
					           			<td class="text12">&nbsp;&nbsp;&nbsp;&nbsp;<span title="dkev_401a-402a">40.2&nbsp;Kat./Type</span>
						           			<select class="inputTextMediumBlueMandatoryField" name="dkev_402a" id="dkev_402a">
							            		<option value="">-vælg-</option>
							 				  	<c:forEach var="code" items="${model.transportdocsSummariskadocsCodeList}" >
			                                	 	<option value="${code.dkkd_kd}"<c:if test="${model.record.dkev_402a == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
												</c:forEach> 
											</select>
											<a tabindex="-1" id="dkev_402aIdLink" OnClick="triggerChildWindowGeneralCodesR40(this, '017', ${recordTopicSkat.dkeh_26})">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
											</a>										           				
										</td>
										<td class="text12" ><span title="dkev_403a">&nbsp;&nbsp;&nbsp;40.3&nbsp;Id</span>
											<input type="text" class="inputTextMediumBlueMandatoryField" name="dkev_403a" id="dkev_403a" size="20" maxlength="35" value="${model.record.dkev_403a}">
											&nbsp;<button name="itemSummariskaDocsExtraFieldsButton" class="buttonGray" type="button" onClick="showPop('itemSummariskaDocsExtraFields');" >Mere...</button>
									        <span style="position:absolute; left:250px; top:400px; width:750px; height:550px;" id="itemSummariskaDocsExtraFields" class="popupWithInputText"  >
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
																<td class="text11" align="center">
																	Kat./Type
																</td>
											           			<td class="text11">
																	&nbsp;&nbsp;&nbsp;Id
																</td>
																
															</tr>
															<tr>
																<td 	align="right"><b>2.</b>
																	<select name="dkev_402b" id="dkev_402b">
													            		<option value="">-vælg-</option>
													 				  	<c:forEach var="code" items="${model.transportdocsSummariskadocsCodeList}" >
													 				  		<option value="${code.dkkd_kd}"<c:if test="${model.record.dkev_402b == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
																		</c:forEach> 
																	</select>
																</td>
											           			<td class="text11">
																	&nbsp;<input type="text" class="inputText" name="dkev_403b" id="dkev_403b" size="30" maxlength="35" value="${model.record.dkev_403b}">
																</td>
																<td align="right"><b>3.</b>
																	<select name="dkev_402c" id="dkev_402c">
													            		<option value="">-vælg-</option>
													 				  	<c:forEach var="code" items="${model.transportdocsSummariskadocsCodeList}" >
													 				  		<option value="${code.dkkd_kd}"<c:if test="${model.record.dkev_402c == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
																		</c:forEach> 
																	</select>
																</td>
											           			<td class="text11">
																	&nbsp;<input type="text" class="inputText" name="dkev_403c" id="dkev_403c" size="30" maxlength="35" value="${model.record.dkev_403c}">
																</td>
															</tr>
															
															<tr>
																<td align="right"><b>4.</b>
																	<select name="dkev_402d" id="dkev_402d">
													            		<option value="">-vælg-</option>
													 				  	<c:forEach var="code" items="${model.transportdocsSummariskadocsCodeList}" >
													 				  		<option value="${code.dkkd_kd}"<c:if test="${model.record.dkev_402d == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
																		</c:forEach> 
																	</select>
																</td>
											           			<td class="text11">
																	&nbsp;<input type="text" class="inputText" name="dkev_403d" id="dkev_403d" size="30" maxlength="35" value="${model.record.dkev_403d}">
																</td>
																<td align="right"><b>5.</b>
																	<select name="dkev_402e" id="dkev_402e">
													            		<option value="">-vælg-</option>
													 				  	<c:forEach var="code" items="${model.transportdocsSummariskadocsCodeList}" >
													 				  		<option value="${code.dkkd_kd}"<c:if test="${model.record.dkev_402e == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
																		</c:forEach> 
																	</select>
																</td>
											           			<td class="text11">
																	&nbsp;<input type="text" class="inputText" name="dkev_403e" id="dkev_403e" size="30" maxlength="35" value="${model.record.dkev_403e}">
																</td>
															</tr>
															
															<tr>
																<td align="right"><b>6.</b>
																	<select name="dkev_402f" id="dkev_402f">
													            		<option value="">-vælg-</option>
													 				  	<c:forEach var="code" items="${model.transportdocsSummariskadocsCodeList}" >
													 				  		<option value="${code.dkkd_kd}"<c:if test="${model.record.dkev_402f == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
																		</c:forEach> 
																	</select>
																</td>
											           			<td class="text11">
																	&nbsp;<input type="text" class="inputText" name="dkev_403f" id="dkev_403f" size="30" maxlength="35" value="${model.record.dkev_403f}">
																</td>
																<td align="right"><b>7.</b>
																	<select name="dkev_402g" id="dkev_402g">
													            		<option value="">-vælg-</option>
													 				  	<c:forEach var="code" items="${model.transportdocsSummariskadocsCodeList}" >
													 				  		<option value="${code.dkkd_kd}"<c:if test="${model.record.dkev_402g == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
																		</c:forEach> 
																	</select>
																</td>
											           			<td class="text11">
																	&nbsp;<input type="text" class="inputText" name="dkev_403g" id="dkev_403g" size="30" maxlength="35" value="${model.record.dkev_403g}">
																</td>
															</tr>
															
															<tr>
																<td align="right"><b>8.</b>
																	<select name="dkev_402h" id="dkev_402h">
													            		<option value="">-vælg-</option>
													 				  	<c:forEach var="code" items="${model.transportdocsSummariskadocsCodeList}" >
													 				  		<option value="${code.dkkd_kd}"<c:if test="${model.record.dkev_402h == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
																		</c:forEach> 
																	</select>
																</td>
											           			<td class="text11">
																	&nbsp;<input type="text" class="inputText" name="dkev_403h" id="dkev_403h" size="30" maxlength="35" value="${model.record.dkev_403h}">
																</td>
																<td align="right"><b>9.</b>
																	<select name="dkev_402i" id="dkev_402i">
													            		<option value="">-vælg-</option>
													 				  	<c:forEach var="code" items="${model.transportdocsSummariskadocsCodeList}" >
													 				  		<option value="${code.dkkd_kd}"<c:if test="${model.record.dkev_402i == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
																		</c:forEach> 
																	</select>
																</td>
											           			<td class="text11">
																	&nbsp;<input type="text" class="inputText" name="dkev_403i" id="dkev_403i" size="30" maxlength="35" value="${model.record.dkev_403i}">
																</td>
															</tr>
														
															<tr>
																<td align="right"><b>10.</b>
																	<select name="dkev_402j" id="dkev_402j">
													            		<option value="">-vælg-</option>
													 				  	<c:forEach var="code" items="${model.transportdocsSummariskadocsCodeList}" >
													 				  		<option value="${code.dkkd_kd}"<c:if test="${model.record.dkev_402j == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
																		</c:forEach> 
																	</select>
																</td>
											           			<td class="text11">
																	&nbsp;<input type="text" class="inputText" name="dkev_403j" id="dkev_403j" size="30" maxlength="35" value="${model.record.dkev_403j}">
																</td>
																<td align="right"><b>11.</b>
																	<select name="dkev_402k" id="dkev_402k">
													            		<option value="">-vælg-</option>
													 				  	<c:forEach var="code" items="${model.transportdocsSummariskadocsCodeList}" >
													 				  		<option value="${code.dkkd_kd}"<c:if test="${model.record.dkev_402k == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
																		</c:forEach> 
																	</select>
																</td>
											           			<td class="text11">
																	&nbsp;<input type="text" class="inputText" name="dkev_403k" id="dkev_403k" size="30" maxlength="35" value="${model.record.dkev_403k}">
																</td>
															</tr>
															
															<tr>
																<td align="right"><b>12.</b>
																	<select name="dkev_402l" id="dkev_402l">
													            		<option value="">-vælg-</option>
													 				  	<c:forEach var="code" items="${model.transportdocsSummariskadocsCodeList}" >
													 				  		<option value="${code.dkkd_kd}"<c:if test="${model.record.dkev_402l == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
																		</c:forEach> 
																	</select>
																</td>
											           			<td class="text11">
																	&nbsp;<input type="text" class="inputText" name="dkev_403l" id="dkev_403l" size="30" maxlength="35" value="${model.record.dkev_403l}">
																</td>
																<td align="right"><b>13.</b>
																	<select name="dkev_402m" id="dkev_402m">
													            		<option value="">-vælg-</option>
													 				  	<c:forEach var="code" items="${model.transportdocsSummariskadocsCodeList}" >
													 				  		<option value="${code.dkkd_kd}"<c:if test="${model.record.dkev_402m == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
																		</c:forEach> 
																	</select>
																</td>
											           			<td class="text11">
																	&nbsp;<input type="text" class="inputText" name="dkev_403m" id="dkev_403m" size="30" maxlength="35" value="${model.record.dkev_403m}">
																</td>
															</tr>
															
															<tr>
																<td align="right"><b>14.</b>
																	<select name="dkev_402n" id="dkev_402n">
													            		<option value="">-vælg-</option>
													 				  	<c:forEach var="code" items="${model.transportdocsSummariskadocsCodeList}" >
													 				  		<option value="${code.dkkd_kd}"<c:if test="${model.record.dkev_402n == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
																		</c:forEach> 
																	</select>
																</td>
											           			<td class="text11">
																	&nbsp;<input type="text" class="inputText" name="dkev_403n" id="dkev_403n" size="30" maxlength="35" value="${model.record.dkev_403n}">
																</td>
																<td align="right"><b>15.</b>
																	<select name="dkev_402o" id="dkev_402o">
													            		<option value="">-vælg-</option>
													 				  	<c:forEach var="code" items="${model.transportdocsSummariskadocsCodeList}" >
													 				  		<option value="${code.dkkd_kd}"<c:if test="${model.record.dkev_402o == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
																		</c:forEach> 
																	</select>
																</td>
											           			<td class="text11">
																	&nbsp;<input type="text" class="inputText" name="dkev_403o" id="dkev_403o" size="30" maxlength="35" value="${model.record.dkev_403o}">
																</td>
															</tr>
															
															<tr>
																<td align="right"><b>16.</b>
																	<select name="dkev_402p" id="dkev_402p">
													            		<option value="">-vælg-</option>
													 				  	<c:forEach var="code" items="${model.transportdocsSummariskadocsCodeList}" >
													 				  		<option value="${code.dkkd_kd}"<c:if test="${model.record.dkev_402p == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
																		</c:forEach> 
																	</select>
																</td>
											           			<td class="text11">
																	&nbsp;<input type="text" class="inputText" name="dkev_403p" id="dkev_403p" size="30" maxlength="35" value="${model.record.dkev_403p}">
																</td>
																<td align="right"><b>17.</b>
																	<select name="dkev_402q" id="dkev_402q">
													            		<option value="">-vælg-</option>
													 				  	<c:forEach var="code" items="${model.transportdocsSummariskadocsCodeList}" >
													 				  		<option value="${code.dkkd_kd}"<c:if test="${model.record.dkev_402q == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
																		</c:forEach> 
																	</select>
																</td>
											           			<td class="text11">
																	&nbsp;<input type="text" class="inputText" name="dkev_403q" id="dkev_403q" size="30" maxlength="35" value="${model.record.dkev_403q}">
																</td>
															</tr>
															<tr>
																<td align="right"><b>18.</b>
																	<select name="dkev_402r" id="dkev_402r">
													            		<option value="">-vælg-</option>
													 				  	<c:forEach var="code" items="${model.transportdocsSummariskadocsCodeList}" >
													 				  		<option value="${code.dkkd_kd}"<c:if test="${model.record.dkev_402r == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
																		</c:forEach> 
																	</select>
																</td>
											           			<td class="text11">
																	&nbsp;<input type="text" class="inputText" name="dkev_403r" id="dkev_403r" size="30" maxlength="35" value="${model.record.dkev_403r}">
																</td>
																<td align="right"><b>19.</b>
																	<select name="dkev_402s" id="dkev_402s">
													            		<option value="">-vælg-</option>
													 				  	<c:forEach var="code" items="${model.transportdocsSummariskadocsCodeList}" >
													 				  		<option value="${code.dkkd_kd}"<c:if test="${model.record.dkev_402s == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
																		</c:forEach> 
																	</select>
																</td>
											           			<td class="text11">
																	&nbsp;<input type="text" class="inputText" name="dkev_403s" id="dkev_403s" size="30" maxlength="35" value="${model.record.dkev_403s}">
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
												<c:choose>	
													<c:when test="${model.status == 'M' || empty model.status || model.status == '11' || model.status == '20' || model.status == '97'}">
														<input class="inputFormSubmit" type="submit" name="submit" id="submit" onclick="javascript: form.action='skatexport_edit_items.do';" value='<spring:message code="systema.skat.export.item.createnew.submit"/>'>
														&nbsp;&nbsp;
														<%-- SEND button: is causing some issues in the Stat.värde calculation
														 We shall have the send button ONLY at one place (so far) and that is at the header level
									 				    	<c:if test="${not empty totalNumberOfItemLines && '0' != totalNumberOfItemLines}">
									 				    		<input tabindex=-1 class="inputFormSubmit" type="submit" name="send" id="send" onclick="javascript: form.action='skatexport_send.do';" value='<spring:message code="systema.skat.import.createnew.send"/>'/>
									 				    	</c:if>
														--%>	
													</c:when>
													<c:otherwise>
							 				    		<input disabled class="inputFormSubmitGrayDisabled" type="submit" name="submit" value='<spring:message code="systema.skat.submit.not.editable"/>'/>
							 				    	</c:otherwise>	
						 				    	</c:choose>	
										</td>							        	
									</tr>
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
				 				<a tabindex=-1 href="renderLocalPdf.do?fn=SKAT_EDI_vejledning_CUSDEC_Eksport_rev4_9.pdf" target="_blank">
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
						            <span title="dkev_441">44.1&nbsp;Bevillingsnr:&nbsp;</span>
							            <div class="text11" style="position: relative;" align="left">
										<span style="position:absolute;top:2px; width:250px;" id="44_1_info" class="popupWithInputText text11"  >	
							           			<b>44.1 Bevillinr.[Sags-nr]</b>
												<br/><br/>
												Bevillingsnummeret [Sags-nr] er journalnummeret på bevillingen eller tilladelsen.
												<br/><br/>
												Skal angives, hvis der er krav om henvisning til bevilling.<br/>
												Der kan f.eks. være tale om bevilling til passiv forædling. Bevillingskrav kan være afhængig af procedurekoden.
										</span>
										</div>
									</td>
						            <td class="text12" align="left">
						            		<input type="text" class="inputText" name="dkev_441" id="dkev_441" size="15" maxlength="13" value="${model.record.dkev_441}">
						            </td>
						        </tr>
						        <tr>
						         
						            <td class="text12" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						            <img onMouseOver="showPop('44_2_info');" onMouseOut="hidePop('44_2_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
						            <span title="dkev_442a">44.2&nbsp;Certifikatnr:&nbsp;</span>
						            <div class="text11" style="position: relative;" align="left">
									<span style="position:absolute;top:2px; width:250px;" id="44_2_info" class="popupWithInputText text11"  >
						           			<b>44.2 Certifikatnr / Certifikatkode</b>
											<br/><br/>
											Dokument til opfyldelse af varebestemmelserne. Indhentes hos ressortmyndighederne.
											<br/>
											<b>Se rubrik 44.3</b>

									</span>
									</div>
									</td>
									
						            <td class="text12" align="left">
						            		<input type="text" class="inputText" name="dkev_442a" id="dkev_442a" size="25" maxlength="35" value="${model.record.dkev_442a}">
						            </td>
						        </tr>
						        <tr>
						            <td class="text12" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						            <img onMouseOver="showPop('44_2b_info');" onMouseOut="hidePop('44_2b_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
						            <span title="dkev_4421">44.2&nbsp;Certifikatkode:&nbsp;</span>
						            <div class="text11" style="position: relative;" align="left">
									<span style="position:absolute;top:2px; width:250px;" id="44_2b_info" class="popupWithInputText text11"  >
						           			<br/><br/>
											Dokument til opfyldelse af varebestemmelserne. Indhentes hos ressortmyndighederne.
											<br/>
											<b>Se rubrik 44.3</b>
									</span>
									</div>
									</td>
						            <td class="text12" >
				           				<select name="dkev_4421" id="dkev_4421">
					 						<option value="">-vælg-</option>
						 				  	<c:forEach var="code" items="${model.certifikatKoderR44_2CodeList}" >
						 				  		<option value="${code.dkkd_kd}"<c:if test="${model.record.dkev_4421 == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
											</c:forEach>  
										</select>
										<a tabindex="-1" id="dkev_4421IdLink" OnClick="triggerChildWindowGeneralCodes(this, '113')">
											<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
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
															&nbsp;<span title="dkev_442b">2. Cert.nr.</span><input type="text" class="inputText" name="dkev_442b" id="dkev_442b" size="36" maxlength="35" value="${model.record.dkev_442b}">
														</td>
														<td class="text11"><span title="dkev_4422">Cert.kode</span>
															<select name="dkev_4422" id="dkev_4422">
										 						<option value="">-vælg-</option>
											 				  	<c:forEach var="code" items="${model.certifikatKoderR44_2CodeList}" >
											 				  		<option value="${code.dkkd_kd}"<c:if test="${model.record.dkev_4422 == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
																</c:forEach>  
															</select>
															<%--
															<input class="clazz_copyElement" type="checkbox" name="dkev_442b_copy@dkev_4422_copy" id="dkev_442b_copy@dkev_4422_copy" value="1" ><font class="text11MediumBlue">Kopi</font>
															 --%>
														</td>
													</tr>
													<tr>
									           			<td class="text11">
															&nbsp;<span title="dkev_442c">3. Cert.nr.<input type="text" class="inputText" name="dkev_442c" id="dkev_442c" size="36" maxlength="35" value="${model.record.dkev_442c}">
														</td>
														<td class="text11"><span title="dkev_4423">Cert.kode</span>
															<select name="dkev_4423" id="dkev_4423">
										 						<option value="">-vælg-</option>
											 				  	<c:forEach var="code" items="${model.certifikatKoderR44_2CodeList}" >
											 				  		<option value="${code.dkkd_kd}"<c:if test="${model.record.dkev_4423 == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
																</c:forEach>  
															</select>
															<%--
															<input class="clazz_copyElement" type="checkbox" name="dkev_442c_copy@dkev_4423_copy" id="dkev_442c_copy@dkev_4423_copy" value="1" ><font class="text11MediumBlue">Kopi</font>
															 --%>
														</td>
													</tr>
													<tr>
									           			<td class="text11">
															&nbsp;4. Cert.nr.<input type="text" class="inputText" name="dkev_442d" id="dkev_442d" size="36" maxlength="35" value="${model.record.dkev_442d}">
														</td>
														<td class="text11">Cert.kode
															<select name="dkev_4424" id="dkev_4424">
										 						<option value="">-vælg-</option>
											 				  	<c:forEach var="code" items="${model.certifikatKoderR44_2CodeList}" >
											 				  		<option value="${code.dkkd_kd}"<c:if test="${model.record.dkev_4424 == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
																</c:forEach>  
															</select>
															<%-- 
															<input class="clazz_copyElement" type="checkbox" name="dkev_442d_copy@dkev_4424_copy" id="dkev_442d_copy@dkev_4424_copy" value="1" ><font class="text11MediumBlue">Kopi</font>
															--%>
														</td>
													</tr>
													<tr>
									           			<td class="text11">
															&nbsp;5. Cert.nr.<input type="text" class="inputText" name="dkev_442e" id="dkev_442e" size="36" maxlength="35" value="${model.record.dkev_442e}">
														</td>
														<td class="text11">Cert.kode
															<select name="dkev_4425" id="dkev_4425">
										 						<option value="">-vælg-</option>
											 				  	<c:forEach var="code" items="${model.certifikatKoderR44_2CodeList}" >
											 				  		<option value="${code.dkkd_kd}"<c:if test="${model.record.dkev_4425 == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
																</c:forEach>  
															</select>
															<%--
															<input class="clazz_copyElement" type="checkbox" name="dkev_442e_copy@dkev_4425_copy" id="dkev_442e_copy@dkev_4425_copy" value="1" ><font class="text11MediumBlue">Kopi</font>
															 --%>
														</td>
													</tr>
													<tr>
									           			<td class="text11">
															&nbsp;6. Cert.nr.<input type="text" class="inputText" name="dkev_442f" id="dkev_442f" size="36" maxlength="35" value="${model.record.dkev_442f}">
														</td>
														<td class="text11">Cert.kode
															<select name="dkev_4426" id="dkev_4426">
										 						<option value="">-vælg-</option>
											 				  	<c:forEach var="code" items="${model.certifikatKoderR44_2CodeList}" >
											 				  		<option value="${code.dkkd_kd}"<c:if test="${model.record.dkev_4426 == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
																</c:forEach>  
															</select>
															<%--
															<input class="clazz_copyElement" type="checkbox" name="dkev_442f_copy@dkev_4426_copy" id="dkev_442f_copy@dkev_4426_copy" value="1" ><font class="text11MediumBlue">Kopi</font>
															 --%>
														</td>
													</tr>
													<tr>
									           			<td class="text11">
															&nbsp;7. Cert.nr.<input type="text" class="inputText" name="dkev_442g" id="dkev_442g" size="36" maxlength="35" value="${model.record.dkev_442g}">
														</td>
														<td class="text11">Cert.kode
															<select name="dkev_4427" id="dkev_4427">
										 						<option value="">-vælg-</option>
											 				  	<c:forEach var="code" items="${model.certifikatKoderR44_2CodeList}" >
											 				  		<option value="${code.dkkd_kd}"<c:if test="${model.record.dkev_4427 == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
																</c:forEach>  
															</select>
															<%--
															<input class="clazz_copyElement" type="checkbox" name="dkev_442g_copy@dkev_4427_copy" id="dkev_442g_copy@dkev_4427_copy" value="1" ><font class="text11MediumBlue">Kopi</font>
															 --%>
														</td>
													</tr>
													<tr>
									           			<td class="text11">
															&nbsp;8. Cert.nr.<input type="text" class="inputText" name="dkev_442h" id="dkev_442h" size="36" maxlength="35" value="${model.record.dkev_442h}">
														</td>
														<td class="text11">Cert.kode
															<select name="dkev_4428" id="dkev_4428">
										 						<option value="">-vælg-</option>
											 				  	<c:forEach var="code" items="${model.certifikatKoderR44_2CodeList}" >
											 				  		<option value="${code.dkkd_kd}"<c:if test="${model.record.dkev_4428 == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
																</c:forEach>  
															</select>
															<%-- 
															<input class="clazz_copyElement" type="checkbox" name="dkev_442h_copy@dkev_4428_copy" id="dkev_442h_copy@dkev_4428_copy" value="1" ><font class="text11MediumBlue">Kopi</font>
															--%>
														</td>
													</tr>
													<tr>
									           			<td class="text11">
															&nbsp;<span title="dkev_442i">9. Cert.nr.</span><input type="text" class="inputText" name="dkev_442i" id="dkev_442i" size="36" maxlength="35" value="${model.record.dkev_442i}">
														</td>
														<td class="text11"><span title="dkev_4429">Cert.kode</span>
															<select name="dkev_4429" id="dkev_4429">
										 						<option value="">-vælg-</option>
											 				  	<c:forEach var="code" items="${model.certifikatKoderR44_2CodeList}" >
											 				  		<option value="${code.dkkd_kd}"<c:if test="${model.record.dkev_4429 == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
																</c:forEach>  
															</select>
															<%--
															<input class="clazz_copyElement" type="checkbox" name="dkev_442i_copy@dkev_4429_copy" id="dkev_442i_copy@dkev_4429_copy" value="1" ><font class="text11MediumBlue">Kopi</font>
															 --%>
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
						            <span title="dkev_443">44.3&nbsp;Varebestemmelse:&nbsp;</span>
						            <div class="text11" style="position: relative;" align="left">
									<span style="position:absolute;top:2px; width:250px;" id="44_3_info" class="popupWithInputText text11"  >
						           			<b>44.3 Varebestemmelser</b>
											<br/><br/>
											Når der foreligger særliga udførselsbetingelser for varekoden, skal varebestemmelseskoden angives,
											se afsnit [F.A.11] om varebestemmelser og se kodetabeller i e-Eksport under virksomheder Told - Erhverv 
											på http://www.skat.dk
									</span>
									</div>
									</td>
						            <td class="text12" >
				           				<select name="dkev_443" id="dkev_443">
					 						<option value="">-vælg-</option>
						 				  	<c:forEach var="code" items="${model.vabCertifikatKoderR44_3CodeList}" >
						 				  		<option value="${code.dkkd_kd}"<c:if test="${model.record.dkev_443 == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
											</c:forEach>  
										</select>
										<a tabindex="-1" id="dkev_443IdLink" OnClick="triggerChildWindowGeneralCodes(this, '114')">
											<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
										</a>
										
				           			</td>
						        </tr>
						        <tr>
						            <td class="text12" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							            <img onMouseOver="showPop('farligGods_info');" onMouseOut="hidePop('farligGods_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
						            		<span title="dkev_444">44.4&nbsp;FN Farlig gods:&nbsp;</span>
						            
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
						            		<select name="dkev_444" id="dkev_444">
					 						<option value="">-vælg-</option>
						 				  	<c:forEach var="code" items="${model.fnFarligGodsCodeList}" >
						 				  		<option value="${code.dkkd_kd}"<c:if test="${model.record.dkev_444 == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
											</c:forEach>  
										</select>
										<a tabindex="-1" id="dkev_444IdLink" OnClick="triggerChildWindowGeneralCodes(this, '115')">
											<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
										</a>						            
									</td>
						        </tr>
						        <tr>
						            <td class="text12" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							            <img onMouseOver="showPop('transdokType_info');" onMouseOut="hidePop('transdokType_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
						            		<span title="dkev_445a">44.5.a&nbsp;Transportdokumenttype&nbsp;</span>
						            
						            <div class="text11" style="position: relative;" align="left">
									<span style="position:absolute;top:2px; width:250px;" id="transdokType_info" class="popupWithInputText text11"  >	
					           				<b>44.5.a&nbsp;Transportdokumenttype</b>
											<br/><br/>
											Todo
											<br/><br/>
									</span>
									</div>
									</td>
						            <td class="text12" align="left">
						            		<select name="dkev_445a" id="dkev_445a">
					 						<option value="">-vælg-</option>
						 				  	<c:forEach var="code" items="${model.transportdocTypeCodeList}" >
						 				  		<option value="${code.dkkd_kd}"<c:if test="${model.record.dkev_445a == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
											</c:forEach>  
										</select>
										<a tabindex="-1" id="dkev_445aIdLink" OnClick="triggerChildWindowGeneralCodes(this, '116')">
											<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
										</a>										
						            </td>
						        </tr>
						        
						        <tr>
						            <td class="text12" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							            <img onMouseOver="showPop('transdokNr_info');" onMouseOut="hidePop('transdokNr_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
						            		<span title="dkev_445b">44.5.b&nbsp;Transportdokumentnummer&nbsp;</span>
						          
						            <div class="text11" style="position: relative;" align="left">
									<span style="position:absolute;top:2px; width:250px;" id="transdokNr_info" class="popupWithInputText text11"  >
					           				<b>44.5.2&nbsp;Transportdokumentnummer</b>
											<br/><br/>
											Todo
											<br/><br/>
									</span>
									</div>
									</td>
						            <td class="text12" align="left">
						            		<input type="text" class="inputText" name="dkev_445b" id="dkev_445b" size="20" maxlength="17" value="${model.record.dkev_445b}">
						            </td>
						        </tr>
						        <tr>
						            <td class="text12" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							            <img onMouseOver="showPop('delsend_info');" onMouseOut="hidePop('delsend_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
						            		<span title="dkev_447">44.7&nbsp;Delsendinger:&nbsp;</span>
						            <div class="text11" style="position: relative;" align="left">
									<span style="position:absolute;top:2px; width:250px;" id="delsend_info" class="popupWithInputText text11"  >
					           				<b>44.7&nbsp;Delsendinger</b>
											<br/><br/>
											Delsendinger består af en delsendingstype i første position og et firecifret løbenummer.
											<br/><br/>
											Delsendingstypen kan være en af følgende koder:
											<ul>
												<li><b>1</b> Første sending</li>
												<li><b>2</b> Efterfølgende sendinger</li>
												<li><b>9</b> Sidste sendning</li>
											</ul>
											Ved første delsending angives altid "0001" som løbe nummer.<br/>
											Ved Efterfølgende sendinger kan løbenummeret se ud som følger:
											<ul>
												<li>Sending 1: <b>10001</b></li>
												<li>Sending 2: <b>20002</b></li>
												<li>Sending 1: <b>20003</b></li>
												<li>Sidste sending 1: <b>90004</b></li>												
											</ul>
									</span>
									</div>
									</td>
						            <td class="text12" align="left">
						            		<input style="text-align:right;" onKeyPress="return amountKey(event)" type="text" class="inputText" name="dkev_447" id="dkev_447" size="6" maxlength="5" value="${model.record.dkev_447}">
						            </td>
						        </tr>
						        <tr>
						            <td class="text12" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							            <img onMouseOver="showPop('software_info');" onMouseOut="hidePop('software_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
						            		<span title="dkev_448">44.8&nbsp;Softwareværdi:&nbsp;</span>
						            <div class="text11" style="position: relative;" align="left">
									<span style="position:absolute;top:2px; width:250px;" id="software_info" class="popupWithInputText text11"  >
					           				<b>44.8&nbsp;Softwareværdi</b>
											<br/><br/>
											Todo
											<br/><br/>
									</span>
									</div>
									</td>
						            <td class="text12" align="left">
						            		<input style="text-align:right;" onKeyPress="return amountKey(event)" type="text" class="inputText" name="dkev_448" id="dkev_448" size="15" maxlength="15" value="${model.record.dkev_448}">
						            </td>
						        </tr>
						        <tr height="15"><td></td></tr>
						        
							
								<tr height="5"><td></td></tr>
			        	        </table>
					        </td>

					        <td width="50%" valign="top">
						 		<table border="0" cellspacing="1" cellpadding="0">
						 			<tr>
						            <td colspan="3" class="text12">
			 						<b>44.6&nbsp;Supplerende vareoplysninger</b>
						            
						           	<div class="text11" style="position: relative;" align="left">
									<span style="position:absolute;top:2px; width:250px;" id="44a_info" class="popupWithInputText text11"  >	
					           			<br/>
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
									<%--
									<tr>
					           			<td class="text11" >
					           				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span title="dkev_446a">Kode</span>
					           				&nbsp;
					           				<select name="dkev_446a" id="dkev_446a">
					           					<option value="">-vælg-</option>
						 						<c:forEach var="code" items="${model.supplerandeVareoplysningsKoderR44_6CodeList}" >
							 				  		<option value="${code.dkkd_kd}"<c:if test="${dkev_446a == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
												</c:forEach>  
											</select>
					           			</td>
					           			<td class="text11" >
					           				&nbsp;
					           			</td>
									</tr>
									--%>
									<tr>
										<td colspan="3" class="text11">
											&nbsp;&nbsp;&nbsp;&nbsp;<span title="dkev_446a">1.Tekst&nbsp;</span>
											<input type="text" class="inputText" name="dkev_446a" id="dkev_446a" size="35" maxlength="70" value="${model.record.dkev_446a}">
										</td>
									</tr>
									<tr>
										<td colspan="3" class="text11">
											&nbsp;&nbsp;&nbsp;&nbsp;<span title="dkev_446b">2.Tekst&nbsp;</span>
											<input type="text" class="inputText" name="dkev_446b" id="dkev_446b" size="35" maxlength="70" value="${model.record.dkev_446b}">
										</td>
									</tr>
									<tr>	
					           			<td colspan="3" class="text11">
											&nbsp;&nbsp;&nbsp;&nbsp;<span title="dkev_446c">3.Tekst&nbsp;</span>
											<input type="text" class="inputText" name="dkev_446c" id="dkev_446c" size="35" maxlength="70" value="${model.record.dkev_446c}">
										</td>
									</tr>
							        <tr height="15"><td></td></tr>
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
											<span title="dkev_332">Tillægskode&nbsp;</span>
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
											33.2&nbsp;<input type="text" class="inputText" name="dkev_332" id="dkev_332" size="4" maxlength="4" value="${model.record.dkev_332}">
										</td>
					            		</tr>
					            		<tr height="15"><td class="text" align="left">&nbsp;</td></tr>
									<tr>
							            <td class="text12" align="left">
							            <img onMouseOver="showPop('31_2_info');" onMouseOut="hidePop('31_2_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
							            <span title="dkev_312a,dkev_312b ..."><b>31.2</b>&nbsp;Containernr:&nbsp;</span>
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
								           	<input type="text" class="inputText" name="dkev_312a" id="dkev_312a" size="12" maxlength="11" value="${model.record.dkev_312a}">
								        </td>
								        <td>&nbsp;  	
							           		<button name="containerNrButton" class="buttonGray" type="button" onClick="showPop('containerNrInfo');" >Mere...</button> 
								           	<span style="position:absolute; left:480px; top:700px; width:580px; height:100px;" id="containerNrInfo" class="popupWithInputTextNoPointer"  >
								           		<div class="text10" align="left">
								           			<table>
								           			<tr>
									           			<td class="text11" colspan="5">
									           				<b>31.2 Containernr</b>
									           			</td>
									        			</tr>
								           			<tr>
									           			<td class="text11">
															&nbsp;2.<input type="text" class="inputText" name="dkev_312b" id="dkev_312b" size="12" maxlength="11" value="${model.record.dkev_312b}">
														</td>
														<td class="text11">
															&nbsp;3.<input type="text" class="inputText" name="dkev_312c" id="dkev_312c" size="12" maxlength="11" value="${model.record.dkev_312c}">
														</td>
														<td class="text11">
									           				&nbsp;4.<input type="text" class="inputText" name="dkev_312d" id="dkev_312d" size="12" maxlength="11" value="${model.record.dkev_312d}">
									           			</td>
									           			<td class="text11">
									           				&nbsp;5.<input type="text" class="inputText" name="dkev_312e" id="dkev_312e" size="12" maxlength="11" value="${model.record.dkev_312e}">
									           			</td>
													</tr>
													</table>
													<table width="100%" align="left" border="0">
														<tr align="left" >
															<td class="text12" ><button name="containerNrButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('containerNrInfo');">&nbsp;<spring:message code="systema.skat.export.ok"/></button> 
															</td>
														</tr>
													</table>
												</div>
								           	</span>
											
							           	</td>
							        </tr>

							        <tr height="15"><td></td></tr>
			        					<tr>
						            		<td class="text12" align="left" >
						            		<img onMouseOver="showPop('49_info');" onMouseOut="hidePop('49_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">	
							            <b>49.&nbsp;</b>
							            <span title="dkev_49">Ident. af oplag</span>
							            <div class="text11" style="position: relative;" align="left">
										<span style="position:absolute;top:2px; width:250px;" id="49_info" class="popupWithInputText text11"  >
							           			<b>49. Identificering af oplag</b>
							           			<br/><br/>
							           			Klarereren skal kun udfylde denne rubrik for varer, der oplægges på toldoplag. Man skal angive toldoplagets nummer, fx C123456DK.
							           			<br/><br/>
							           			Oplagsnummer skal angives, hvis angivelsen vedrører oplæggelse på toldoplag eller fraførsel fra toldoplag
										</span>	
										</div>
										</td>
										
							            <td ><input type="text" class="inputTextMediumBlue" name="dkev_49" id="dkev_49" size="18" maxlength="17" value="${model.record.dkev_49}"></td>
			        					</tr>
							        <tr height="15"><td></td></tr>
							        <tr>
							            <td class="text12" align="left">
							            <img onMouseOver="showPop('bem_info');" onMouseOut="hidePop('bem_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
							            <span title="dkev_bem1,dkev_bem2 ..."><b>Bemærkninger&nbsp;</b></span>

						           		<div class="text11" style="position: relative;" align="left">
										<span style="position:absolute;top:2px; width:250px;" id="bem_info" class="popupWithInputText text11"  >	
						           			<b>Bemærkninger&nbsp;</b>
											<br/><br/>
											Todo
										</span>
										</div>
							            </td>
							            
								        <td class="text12" >&nbsp;<button name="bemaerkningButton" class="buttonGray" type="button" onClick="showPop('bemaerkningInfo');" >Mere...</button> 
								           	<span style="position:absolute; left:480px; top:700px; width:580px; height:200px;" id="bemaerkningInfo" class="popupWithInputTextNoPointer"  >
								           		<div class="text10" align="left">
								           			<table>
								           			<tr>
									           			<td class="text11" colspan="5">
									           				<b>Bemærkninger&nbsp;</b>
									           			</td>
									        			</tr>
								           			<tr>
									           			<td class="text11">
															&nbsp;1.<input type="text" class="inputText" name="dkev_bem1" id="dkev_bem1" size="35" maxlength="70" value="${model.record.dkev_bem1}">
														</td>
														<td class="text11">
															&nbsp;2.<input type="text" class="inputText" name="dkev_bem2" id="dkev_bem2" size="35" maxlength="70" value="${model.record.dkev_bem2}">
														</td>
													</tr>
													<tr>
														<td class="text11">
									           				&nbsp;3.<input type="text" class="inputText" name="dkev_bem3" id="dkev_bem3" size="35" maxlength="70" value="${model.record.dkev_bem3}">
									           			</td>
									           			<td class="text11">
									           				&nbsp;4.<input type="text" class="inputText" name="dkev_bem4" id="dkev_bem4" size="35" maxlength="70" value="${model.record.dkev_bem4}">
									           			</td>
								           			</tr>
													
													</table>
													<table width="100%" align="left" border="0">
														<tr align="left" >
															<td class="text12" ><button name="bemaerkningButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('bemaerkningInfo');">&nbsp;<spring:message code="systema.skat.export.ok"/></button> 
															</td>
														</tr>
													</table>
												</div>
								           	</span>
											
							           	</td>
							        </tr>
							        
									<tr>
										<td class="text12">
										<img onMouseOver="showPop('ym_info');" onMouseOut="hidePop('ym_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
							            <span title="dkev_y71,dkev_y72 ..."><b>YM oplysninger&nbsp;</b></span>
							            <div class="text11" style="position: relative;" align="left">
										<span style="position:absolute;top:2px; width:250px;" id="ym_info" class="popupWithInputText text11"  >
						           			<b>YM oplysninger&nbsp;</b>
											<br/><br/>
											Todo
										</span>
										</div>
										</td>
										
										<td class="text12" >&nbsp;<button name="YMPosterButton" class="buttonGray" type="button" onClick="showPop('YMPoster');" >Mere...</button> 
								        <span style="background-color:#EEEEEE; position:absolute; left:50px; top:450px; width:1200px; height:500px;" id="YMPoster" class="popupWithInputTextThickBorder"  >
							           		<div class="ownScrollableSubWindow" style="width:1080px; height:450px; margin:10px;">
							           			<nav>
							           			<table class="formFrameTitaniumWhite" width="95%" border="0" align="left" cellspacing="2">
							           			<tr>
								           			<td colspan="3" class="text14"><b>YM oplysninger</b></td>
								           		</tr>
								           		
								           		<tr>
							           				<td width="50%" valign="top">
							           				<table >
								           				<tr>
								           					<td class="text12" colspan="2" >
																&nbsp;<b>YM Ekstra Varepost Oplysninger</b>
															</td>
														</tr>													 
														<tr>	
															<td class="text11">
																&nbsp;&nbsp;&nbsp;<span title="dkev_y71"><b>YM 7.1</b>&nbsp;YM Licensnummer</span>
															</td>
															<td class="text11">
																&nbsp;<input  type="text" class="inputText" name="dkev_y71" id="dkev_y71" size="20" maxlength="17" value="${model.record.dkev_y71}">
															</td>
														</tr>
														<tr>
															<td class="text11">
																&nbsp;&nbsp;&nbsp;<span title="dkev_y72"><b>YM 7.2</b>&nbsp;YM Licensart</span>
															</td>
															<td class="text11">
																&nbsp;<input  type="text" class="inputText" name="dkev_y72" id="dkev_y72" size="5" maxlength="4" value="${model.record.dkev_y72}">
															</td>
														</tr>
														<tr>
															<td class="text11">
																&nbsp;&nbsp;&nbsp;<span title="dkev_y73"><b>YM 7.3</b>&nbsp;Sats dato ved FF</span>
															</td>
															<td class="text11">
																&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="dkev_y73" id="dkev_y73" size="20" maxlength="12" value="${model.record.dkev_y73}">
															</td>
														</tr>
														<tr>
															<td class="text11">
																&nbsp;&nbsp;&nbsp;<span title="dkev_y74"><b>YM 7.4</b>&nbsp;Garanti-journal nr</span>
															</td>
															<td class="text11">
																&nbsp;<input  type="text" class="inputText" name="dkev_y74" id="dkev_y74" size="20" maxlength="17" value="${model.record.dkev_y74}">
															</td>
														</tr>
														<tr>
															<td class="text11">
																&nbsp;&nbsp;&nbsp;<span title="dkev_y75"><b>YM 7.5</b>&nbsp;Restitutionssats (i euro pr 100)</span>
															</td>
															<td class="text11">
																&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="dkev_y75" id="dkev_y75" size="20" maxlength="15" value="${model.record.dkev_y75}">
															</td>
														</tr>
														
														<tr>	
															<td class="text11">
																&nbsp;&nbsp;&nbsp;<span title="dkev_y63"><b>YM 6.3</b>&nbsp;Alt.mængde</span>
															</td>
															<td class="text11">
																&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="dkev_y63" id="dkev_y63" size="20" maxlength="9" value="${model.record.dkev_y63}">
															</td>
														</tr>
														<tr>	
															<td class="text11">
																&nbsp;&nbsp;&nbsp;<span title="dkev_y64"><b>YM 6.4</b>&nbsp;Alt.enhed</span>
															</td>
															<td class="text11">
																<select name="dkev_y64" id="dkev_y64" >
																<option value="">-vælg-</option>
								 				  					<c:forEach var="record" items="${model.suppUomKoderCodeList}" >
								 				  					<option value="${record.dkkd_kd}"<c:if test="${model.record.dkev_y64 == record.dkkd_kd}"> selected </c:if> >${record.dkkd_kd} - ${record.dkkf_txt}</option>
																	</c:forEach> 
																</select>
															</td>
														</tr>
														<tr>	
															<td class="text11">
																&nbsp;&nbsp;&nbsp;<span title="dkev_y76a"><b>YM 7.6.1</b>&nbsp;Erklæringer til varepost-1</span>
															</td>
															<td class="text11">
																&nbsp;<input  type="text" class="inputText" name="dkev_y76a" id="dkev_y76a" size="20" maxlength="30" value="${model.record.dkev_y76a}">
															</td>
														</tr>
														<tr>	
															<td class="text11">
																&nbsp;&nbsp;&nbsp;<span title="dkev_y76b"><b>YM 7.6.2</b>&nbsp;Erklæringer til varepost-2</span>
															</td>
															<td class="text11">
																&nbsp;<input  type="text" class="inputText" name="dkev_y76b" id="dkev_y76b" size="20" maxlength="30" value="${model.record.dkev_y76b}">
															</td>
														</tr>
														<tr>	
															<td class="text11">
																&nbsp;&nbsp;&nbsp;<span title="dkev_y76c"><b>YM 7.6.3</b>&nbsp;Erklæringer til varepost-3</span>
															</td>
															<td class="text11">
																&nbsp;<input  type="text" class="inputText" name="dkev_y76c" id="dkev_y76c" size="20" maxlength="30" value="${model.record.dkev_y76c}">
															</td>
														</tr>
														<tr>	
															<td class="text11">
																&nbsp;&nbsp;&nbsp;<span title="dkev_y76d"><b>YM 7.6.1</b>&nbsp;Erklæringer til varepost-4</span>
															</td>
															<td class="text11">
																&nbsp;<input  type="text" class="inputText" name="dkev_y76d" id="dkev_y76d" size="20" maxlength="30" value="${model.record.dkev_y76d}">
															</td>
														</tr>
														
														<tr height="20"><td></td></tr>
														<tr>	
															<td class="text12" >
																&nbsp;<b>Recept</b>
															</td>															
														</tr>
														<tr>
															<td class="text11">
																&nbsp;&nbsp;&nbsp;<span title="dkev_y81a"><b>YM 8.1</b>&nbsp;Recept nr (SE nr)</span>
															</td>
															<td class="text11">
																&nbsp;<input  type="text" class="inputText" name="dkev_y81a" id="dkev_y81a" size="20" maxlength="10" value="${model.record.dkev_y81a}">
															</td>
														</tr>
														<tr>
															<td class="text11">
																&nbsp;&nbsp;&nbsp;<span title="dkev_y81b"><b>YM 8.1</b>&nbsp;Recept nr (løb nr)</span>
															</td>
															<td class="text11">
																&nbsp;<input  type="text" class="inputText" name="dkev_y81b" id="dkev_y81b" size="5" maxlength="2" value="${model.record.dkev_y81b}">
															</td>
														</tr>
														<tr>
															<td class="text11">
																&nbsp;&nbsp;&nbsp;<span title="dkev_y81c"><b>YM 8.1</b>&nbsp;Recept mængde</span>
															</td>
															<td class="text11">
																&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="dkev_y81c" id="dkev_y81c" size="20" maxlength="15" value="${model.record.dkev_y81c}">
															</td>
														</tr>	
														<tr>
															<td class="text11">
																&nbsp;&nbsp;&nbsp;<span title="dkev_y81d"><b>YM 8.1</b>&nbsp;Recept enhed (Kilo/gram)</span>
															</td>
															<td class="text11">
																<select name="dkev_y81d" id="dkev_y81d" >
											 					  <option value="">-vælg-</option>
											 					  <option value="0"<c:if test="${ model.record.dkev_y81d == '0'}"> selected </c:if> >KG</option>
																  <option value="1"<c:if test="${ model.record.dkev_y81d == '1'}"> selected </c:if> >Gram</option>
																</select>
															</td>
														</tr>
														<tr>
															<td class="text11">
																&nbsp;&nbsp;&nbsp;<span title="dkev_y81e"><b>YM 8.1</b>&nbsp;Recept under/overvægt</span>
															</td>
															<td class="text11">
																<select name="dkev_y81e" id="dkev_y81e" >
											 					  <option value="">-vælg-</option>
											 					  <option value="U"<c:if test="${ model.record.dkev_y81e == 'U'}"> selected </c:if> >Undervægt</option>
																  <option value="O"<c:if test="${ model.record.dkev_y81e == 'O'}"> selected </c:if> >Overvægt</option>
																</select>
															</td>
														</tr>
													</table>
													</td>
													
													<td width="50%" valign="top" >		
													<table >
														<tr>	
															<td class="text12" >
																&nbsp;<b>Basisvarer</b>
															</td>															
														</tr>
														<tr>	
															<td class="text11">
																&nbsp;&nbsp;&nbsp;<span title="dkev_y82a"><b>YM 8.2</b>&nbsp;Basisvarekode - tillægskode</span>
															</td>
															<td class="text11">
																&nbsp;<input  type="text" class="inputText" name="dkev_y82a" id="dkev_y82a" size="15" maxlength="12" value="${model.record.dkev_y82a}">
															</td>
														</tr>	
														<tr>	
															<td class="text11">
																&nbsp;&nbsp;&nbsp;<span title="dkev_y82b"><b>YM 8.2</b>&nbsp;Nettovægt på basisvaren</span>
															</td>
															<td class="text11">
																&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="dkev_y82b" id="dkev_y82b" size="20" maxlength="15" value="${model.record.dkev_y82b}">
															</td>
														</tr>
														<tr>
															<td class="text11">
																&nbsp;&nbsp;&nbsp;<span title="dkev_y82c"><b>YM 8.2</b>&nbsp;Basisprocent</span>
															</td>
															<td class="text11">
																&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="dkev_y82c" id="dkev_y82c" size="20" maxlength="15" value="${model.record.dkev_y82c}">
															</td>
														</tr>
														<tr>	
															<td class="text11">
																&nbsp;&nbsp;&nbsp;<span title="dkev_y82d"><b>YM 8.2</b>&nbsp;Basismængde</span>
															</td>
															<td class="text11">
																&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="dkev_y82d" id="dkev_y82d" size="20" maxlength="15" value="${model.record.dkev_y82d}">
															</td>
														</tr>
														<tr>												
															<td class="text11">
																&nbsp;&nbsp;&nbsp;<span title="dkev_y82e"><b>YM 8.2</b>&nbsp;Licensnummer på basisvaren</span>
															</td>
															<td class="text11">
																&nbsp;<input  type="text" class="inputText" name="dkev_y82e" id="dkev_y82e" size="10" maxlength="8" value="${model.record.dkev_y82e}">
															</td>
														</tr>
														<tr>
															<td class="text11">
															&nbsp;&nbsp;&nbsp;<span title="dkev_y82f"><b>YM 8.2</b>&nbsp;Licensart på basisvaren</span>
															</td>
															<td class="text11">
																<select name="dkev_y82f" id="dkev_y82f" >
											 					  <option value="">-vælg-</option>
											 					  <option value="0"<c:if test="${ model.record.dkev_y82f == '0'}"> selected </c:if> >ikke deponeret</option>
																  <option value="1"<c:if test="${ model.record.dkev_y82f == '1'}"> selected </c:if> >deponeret</option>
																</select>																
															</td>
														</tr>
														<tr>
															<td class="text11">
																&nbsp;&nbsp;&nbsp;<span title="dkev_y82g"><b>YM 8.2</b>&nbsp;Basisv.dato ved FF</span>
															</td>
															<td class="text11">
																&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="dkev_y82g" id="dkev_y82g" size="15" maxlength="12" value="${model.record.dkev_y82g}">
															</td>
														</tr>
														<tr>
															<td class="text11">
																&nbsp;&nbsp;&nbsp;<span title="dkev_y82h"><b>YM 8.2</b>&nbsp;Erklæringskode</span>
															</td>
															<td class="text11">
																&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="dkev_y82h" id="dkev_y82h" size="15" maxlength="12" value="${model.record.dkev_y82h}">
															</td>
														</tr>
														<tr height="10"><td></td></tr>
														<tr align="left" >
															<td class="text11">&nbsp;</td>
															<td class="text11"><button name="YMPosterButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('YMPoster');">&nbsp;<spring:message code="systema.skat.export.ok"/></button> 
															</td>
														</tr>
															
													</table>
													</td>
												</tr>
												</table>
												</nav>
											</div>
										</span>
									</td>									
									</tr>								        
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

