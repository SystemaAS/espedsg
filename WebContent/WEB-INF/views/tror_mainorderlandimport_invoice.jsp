<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTror.jsp" />
<!-- =====================end header ==========================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/jquery.calculator.js"></SCRIPT>
	<SCRIPT type="text/javascript" src="resources/js/jquery-ui-timepicker-addon.js"></SCRIPT>
	<SCRIPT type="text/javascript" src="resources/js/trorglobal_edit.js?ver=${user.versionEspedsg}"></SCRIPT>
	<SCRIPT type="text/javascript" src="resources/js/tror_mainorderlandimport_invoice.js?ver=${user.versionEspedsg}"></SCRIPT>			
	<%-- for dialog popup --%>
	<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
	
	<style type = "text/css">
	.ui-dialog{font-size: 90.5%;}
	
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
		<tr height="2">
			<td>
				<input type="hidden" name="modelStatus" id="modelStatus" value='${model.status}'>
			</td>
		</tr>
		<tr height="25"> 
			<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a class="text14" onClick="setBlockUI(this);" href="tror_mainorderlist.do?action=doFind" > 	
					<img style="vertical-align:middle;" src="resources/images/bulletGreen.png" width="6px" height="6px" border="0" alt="open orders">
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tror.orderlist.tab"/></font>
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a class="text14" onClick="setBlockUI(this);" href="tror_mainorderlandimport.do?action=doFetch&heavd=${recordOrderTrorLandImport.heavd}&heopd=${recordOrderTrorLandImport.heopd}" > 	
					<img style="vertical-align:middle;" src="resources/images/lorry_green.png" width="18px" height="18px" border="0" alt="update">
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tror.order.tab"/>&nbsp;${recordOrderTrorLandImport.heavd}/${recordOrderTrorLandImport.heopd}</font>
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="15%" valign="bottom" class="tab" align="center" nowrap>
				<font class="tabLink"><spring:message code="systema.tror.order.faktura.tab"/></font><font class="text12"></font>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a class="text14" onClick="setBlockUI(this);" href="tror_mainorderlandimport_notisblock.do?action=doInit&heavd=${recordOrderTrorLandImport.heavd}&heopd=${recordOrderTrorLandImport.heopd}" > 	
					<font class="tabDisabledLink"><spring:message code="systema.tror.order.notisblock.tab"/></font><font class="text12">&nbsp;</font>
				</a>
			</td>
		
			<td width="50%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
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
				 				&nbsp;Avd&nbsp;<b>${recordOrderTrorLandImport.heavd}</b>
				 				&nbsp;Ordre&nbsp;<b>${recordOrderTrorLandImport.heopd}</b>
				 				&nbsp;Sign&nbsp;<b>${recordOrderTrorLandImport.hesg}</b>
				 				&nbsp;&nbsp;
				 				<img onMouseOver="showPop('status_info');" onMouseOut="hidePop('status_info');" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 				Status:&nbsp;<b>${recordOrderTrorLandImport.hest}</b>
				 				<div class="text11" style="position: relative; display: inline;" align="left" >
				 				<span style="position:absolute;top:2px; width:400px;" id="status_info" class="popupWithInputText text11"  >
					           		<p>Status på oppdraget. Denne koden forteller hvor langt et oppdrag har kommet i
										"syklusen" fra det første gang registreres til det er ferdig fakturert og avsluttet.</p> 
				           			<ul>
				           				<li><b>' '</b>&nbsp;(blank) "Åpent". Oppdraget er ikke fakturert og det er åpent for alle typer endringer.
				           				<li><b>U</b>&nbsp;Booking / B/L er laget, men oppdrag er ikke bearbeidet. Hvis et oppdrag i sjø-modulen er påbegynt via Booking eller B/L, vill oppdraget inntil man går inn og jobber med det, ha denne statusen.</li>
				           				<li><b>K</b>&nbsp;"Ferdigmeldt". Oppdraget ligger i kø for ferdigmeldte oppdrag. Man har fortsatt mulighet for å endre på fakturaen, eller omgjøre klarmeldingen.</li>
				           				<li><b>C</b>&nbsp;"Klar for samlefaktura". Oppdraget ligger i kø for samlefaktura. Man har fremdeles mulighet for å endre på fakturaen eller fjerne fra samlefakturakø.</li>	
				           				
				           				<li><b>F</b>&nbsp;"Fakturert". Oppdraget er fakturert, men ennå ikke overført til økonomisystemet. Oppdraget kan hverken krediteres eller slettes i denne status. De enkelte fakturaer kan derimot slettes (MENU INV, punkt 6).Fakturanummerene merkes da i fakturajournalen med "**** SLETTET ***".</li>	
				           				<li><b>G</b>&nbsp;"Merket for overføring". Oppdragene har denne status i tiden mellom merking for overføring til regnskap og selve overføringen.Ved denne status kan fakturaer slettes. Fakturanummerene merkes da i fakturajournalen med "**** SLETTET ****".Ordinær kreditering - se under.</li>	
				           				<li><b>T</b>&nbsp;Overført men ikke oppdatert i statistikk. Et oppdrag med denne statusen er ferdig overført til regnskap, men ennå ikke oppdatert i statistikk.</li>	
				           				<li><b>O</b>&nbsp;"Overført". Ferdig overført til regnskap. I denne status kan en faktura i sin helhet krediteres, men ikke slettes.</li>	
				           				<li><b>S</b>&nbsp;"Slettet". Oppdraget er slettet via funksjon for sletting av oppdrag.</li>	
				           				<li><b>X</b>&nbsp;"Under oppdatering ". Noen arbeider med oppdraget, og oppholder seg på oppdragsbildet. Hvis man, mens man er inne på et oppdrag, "mister" kontakten med systemet, eller det oppstår en feilsituasjon, vil oppdraget kunne bli "hengende" i status X.</li>	
				           				<li><b>M</b>&nbsp;"Under oppdatering ". Oppdraget er låst fordi en overføring av import-MVA fra fortollingsprogrammet foregår akkurat nå. OBS! Som man forstår skal et oppdrag ha status 'X' eller 'M' kun en begrenset tidsperiode. Dersom oppdrag har denne status permanent skyldes dette unormalt jobbavbrudd - f.eks strømbrudd.
				           						Ta i så fall kontakt med dataansvarlig slik at hun eller han kan rette statusen på oppdraget til ' '.</li>	
				           							
				           			</ul>
								</span>	
								</div>
				 				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Turnr&nbsp;<b>${recordOrderTrorLandImport.hepro}</b>
				 				&nbsp;Godsnr.&nbsp;<b>${recordOrderTrorLandImport.hegn}</b>
				 				&nbsp;Frankatur&nbsp;<b>${recordOrderTrorLandImport.hefr}</b>
				 				
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
							            <td width="30%" class="text11Bold" align="left" ><spring:message code="systema.tror.orders.form.update.label.shipper"/></td>
							            <td class="text11" align="left" >&nbsp;&nbsp;</td>
							        </tr>
							        
							        <tr>
							            <td width="30%" class="text11" align="left"><spring:message code="systema.tror.orders.form.update.label.shipper.name"/>&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordOrderTrorLandImport.henas}</td>
							        </tr>
									<tr>
							            <td width="30%" class="text11" align="left"><spring:message code="systema.tror.orders.form.update.label.shipper.adr1"/>&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordOrderTrorLandImport.heads1}</td>
							        </tr>
							        <tr>
							            <td width="30%" class="text11" align="left"><spring:message code="systema.tror.orders.form.update.label.shipper.adr2"/>&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordOrderTrorLandImport.heads2}</td>
							        </tr>
							        
									<tr>
							            <td width="30%" class="text11" align="left"><spring:message code="systema.tror.orders.form.update.label.shipper.adr3"/>&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordOrderTrorLandImport.heads3}</td>
							        </tr>
							        						        
			        	        </table>
					        </td>
					        <td width="50%">
						 		<table width="100%" border="0" cellspacing="1" cellpadding="0">
							 		<tr>
							            <td width="30%" class="text11Bold" align="left" ><spring:message code="systema.tror.orders.form.update.label.consignee"/></td>
							            <td class="text11" align="left" >&nbsp;&nbsp;</td>
							        </tr>
							        
							        <tr>
							            <td width="30%" class="text11" align="left"><spring:message code="systema.tror.orders.form.update.label.consignee.name"/>&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordOrderTrorLandImport.henak}</td>
							        </tr>
									<tr>
							            <td width="30%" class="text11" align="left"><spring:message code="systema.tror.orders.form.update.label.consignee.adr1"/>&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordOrderTrorLandImport.headk1}</td>
							        </tr>
							        <tr>
							            <td width="30%" class="text11" align="left"><spring:message code="systema.tror.orders.form.update.label.consignee.adr2"/>&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordOrderTrorLandImport.headk2}</td>
							        </tr>
									<tr>
							            <td width="30%" class="text11" align="left"><spring:message code="systema.tror.orders.form.update.label.consignee.adr3"/>&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordOrderTrorLandImport.headk3}</td>
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
	        			<%--
						<tr >
							<td>
							<form name="createNewItemLine" id="createNewItemLine" method="post" action="TODOtvinnsadexport_edit_finansopplysninger.do">
								<input type="hidden" name="action" id="action" value='doFetch'>
				 				<input type="hidden" name="totalGrossWeight" id="totalGrossWeight" value='${recordTopicTvinnSad.sevkb}'>
				 				
				 				
								<table width="80%" cellspacing="0" border="0" cellpadding="0">
									<tr>
										<td class="text12Bold">&nbsp;Antall varelinjer&nbsp;&nbsp;<font class="text12MediumBlue"><b>${Xmodel.recordItemContainerFinansOpplysningerTopic.totalNumberOfItemLines}</b></font>
						            		</td>
										<td align="right" class="text11">Fsum:&nbsp;
											<input tabindex=-1 align="right" type="text" readonly class="inputText11BlueBoldReadOnly" size="12" maxlength=20" value="${recordTopicTvinnSad.sebel1}">
											<font class="inputText11BlueBoldReadOnly">${XrecordTopicTvinnSad.seval1}</font>
										</td>
										<td align="right" class="text11">Vsum&nbsp;(&Sigma;):&nbsp;
											<input tabindex=-1 align="right" type="text" readonly class="inputText11BlueBoldReadOnly" size="12" maxlength=20" value="${Xmodel.recordItemContainerFinansOpplysningerTopic.calculatedItemLinesTotalAmount}">
											<font class="inputText11BlueBoldReadOnly">${Xmodel.recordItemContainerFinansOpplysningerTopic.calculatedValidCurrency}</font>											
										</td>
										<%--
										<td align="right" class="text11">Diff:&nbsp;
											<input tabindex=-1 align="right" type="text" readonly
												<c:choose>
												<c:when test="${fn:contains(Xmodel.recordItemContainerFinansOpplysningerTopic.diffItemLinesTotalAmountWithInvoiceTotalAmount,'-')}">
													class="inputText11RedBoldReadOnly" 
												</c:when>
												<c:otherwise>
													class="inputText11BlueBoldReadOnly"
												</c:otherwise>
												</c:choose>
												size="12" maxlength=20" value='${Xmodel.recordItemContainerFinansOpplysningerTopic.diffItemLinesTotalAmountWithInvoiceTotalAmount}'>
										</td>
										-- here END tag
									</tr>
									<tr height="2"><td></td></tr>
								</table>
							</form>
							
							</td>
						</tr> 
						--%>
						<tr>
							<td >
								<form name="formItemList" id="formItemList" method="POST" >
				               		<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
				 				<table id="container tableTable" width="80%" cellspacing="2" align="left" >
								<tr>
								<td class="text11">
										
								<table id="tblInvoices" class="display compact cell-border" >
									<thead>
									<tr style="background-color:#DDDDDD">
										<th width="2%" align="center" class="text12"><spring:message code="systema.tror.orders.invoice.update.label.lineNr"/></th>
										<c:if test="${empty recordOrderTrorLandImport.hest || recordOrderTrorLandImport.hest == 'U' || recordOrderTrorLandImport.hest == 'O' || recordOrderTrorLandImport.hest == 'F' }">
									    	<th width="2%" align="center" class="text12"><spring:message code="systema.tror.orders.invoice.update.label.edit"/></th> 
									    </c:if>
									    <th align="center" class="text12"><spring:message code="systema.tror.orders.invoice.update.label.sk"/></th>
									    <th align="left" class="text12"><spring:message code="systema.tror.orders.invoice.update.label.gebyrCode"/></th>
									    <th align="left" class="text12"><spring:message code="systema.tror.orders.invoice.update.label.text"/></th> 
									    <th width="2%" align="center" class="text12"><spring:message code="systema.tror.orders.invoice.update.label.currency"/></th>
					                    <th align="right" class="text12" nowrap>&nbsp;<spring:message code="systema.tror.orders.invoice.update.label.amount1"/>&nbsp;</th>
					                    <th align="right" class="text12" nowrap>&nbsp;<spring:message code="systema.tror.orders.invoice.update.label.amount2"/>&nbsp;</th>
					                    <th width="2%" align="center" class="text12" nowrap><spring:message code="systema.tror.orders.invoice.update.label.mva"/></th>
					                    <th width="2%" align="center" class="text12" nowrap><spring:message code="systema.tror.orders.invoice.update.label.opr"/></th>
					                    
					                    <c:if test="${empty recordOrderTrorLandImport.hest || recordOrderTrorLandImport.hest == 'U' || recordOrderTrorLandImport.hest == 'O' || recordOrderTrorLandImport.hest == 'F' }">
					                    	<th width="2%" align="center" class="text12" nowrap><spring:message code="systema.tror.orders.invoice.update.label.delete"/>Slett</th>
					                    </c:if> 
					               </tr> 
								   </thead>
								   <tbody>						               
				 					  <c:forEach items="${model.list}" var="record" varStatus="counter">  
				 					  	<c:if test="${not empty record.fali}">  
							              <tr class="tableRow">
										   <td width="2%" align="center" class="text11" >${record.fali}</td>

										   <c:if test="${empty recordOrderTrorLandImport.hest || recordOrderTrorLandImport.hest == 'U' || recordOrderTrorLandImport.hest == 'O' || recordOrderTrorLandImport.hest == 'F' }">
								               <td width="2%" class="text11" align="center">
							               			<a tabindex=-1 id="recordUpdate_${record.fali}" href="#" onClick="getInvoiceItemData(this);">
							               				<img title="Update" style="vertical-align:bottom;" src="resources/images/update.gif" border="0" alt="update">&nbsp;
							               			</a>							               			
								               </td>
							               </c:if>

							               <td width="2%" align="center" class="text11" >${record.fask}</td>
							               <td width="2%" align="center" class="text11" >${record.favk}</td>
							               <td class="text11" align="left">${record.stdVt}
						               	   		<c:if test="${not empty record.faVT}">&nbsp;/&nbsp;${record.faVT}</c:if>
							               </td>
							               <td width="2%" align="center" class="text11" >${record.faval}</td>
							               <td align="right" class="text11" >&nbsp;${record.fabelv}</td>
							               <td align="right" class="text11" >&nbsp;${record.fabeln}</td>
							               <td width="2%" align="center" class="text11" >${record.fakdm}</td>
							               <td width="2%" align="center" class="text11" >${record.fakda}</td>
							               <c:if test="${empty recordOrderTrorLandImport.hest || recordOrderTrorLandImport.hest == 'U' || recordOrderTrorLandImport.hest == 'O' || recordOrderTrorLandImport.hest == 'F' }">
							               		<td class="text11" align="center" nowrap>
											   		<c:if test="${record.faopko==' ' || record.faopko=='A' || record.faopko=='B' || record.faopko=='C'}">
								                   		<c:if test="${not empty record.fali}">
									                   		<a style="cursor:pointer;" id="heavd_${recordOrderTrorLandImport.heavd}@heopd_${recordOrderTrorLandImport.heopd}@fali_${record.fali}" onClick="doPermanentlyDeleteInvoiceLine(this);" tabindex=-1 >
											               		<img valign="bottom" src="resources/images/delete.gif" border="0" alt="remove">
											               	</a>&nbsp;
										               	</c:if>
									               	</c:if>								               	
							               		</td>
							               </c:if>
							            </tr>
								        <%-- this param is used ONLY in this JSP 
								        <c:set var="totalNumberOfItemLines" value="${counter.count}" scope="request" />
								        --%> 
								        <%-- this param is used throughout the Controller --%>
								        <c:set var="numberOfItemLinesInTopic" value="${Xrecord.svln}" scope="request" /> 
								        <c:set var="ownKundnrVar" value="${record.fakunr}" scope="request" /> 
								        </c:if>
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
			<%-- -------------------------- --%>
			<%-- Validation errors on model --%>
			<%-- -------------------------- --%>
			<c:if test="${not empty model.errorMessage}">
				<tr>
				<td>
		           	<table width="100%" align="left" border="0" cellspacing="0" cellpadding="0">
		           	<tr>
					<td valign="bottom" class="textError">					
			            <ul>
			            	<li >${model.errorMessage}</li>
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
	 			<td >
	 				<form action="tror_mainorderlandimport_invoice_edit.do" name="trorEditInvoiceItemForm" id="trorEditInvoiceItemForm" method="post">
				 	<%--Required key parameters from the Topic parent --%>
				 	<input type="hidden" name="action" id="action" value='doUpdate'/>
				 	<input type="hidden" name="fali" id="fali" value='${model.record.fali}'/>
				 	<input type="hidden" name="fakunr" id="fakunr" value='${ownKundnrVar}'/>
				 	<input type="hidden" name="heavd" id="heavd" value='${recordOrderTrorLandImport.heavd}'>
	 				<input type="hidden" name="heopd" id="heopd" value='${recordOrderTrorLandImport.heopd}'>
								
				 	<input type="hidden" name="lineId" id="lineId" value="">
				 	<%-- <input type="hidden" name="numberOfItemLinesInTopic" id="numberOfItemLinesInTopic" value="${numberOfItemLinesInTopic}" /> --%>
				 	
				 	<%-- Topic ITEM CREATE --%>
	 				<table width="80%" align="left" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="15">
				 			<td class="text12White" align="left" >
				 				<b>&nbsp;&nbsp;V<label onClick="showPop('debugPrintlnAjaxItemFetchAdmin');" >a</label>relinje&nbsp;</b>
				 				
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
				 				<img onClick="showPop('updateInfo');" src="resources/images/update.gif" border="0" alt="edit" >&nbsp;&nbsp;<div name="editLineNr" id="editLineNr" style="display: inline;" >${model.record.fali}</div>
				 				<span style="position:absolute; left:150px; top:200px; width:800px; height:400px;" id="updateInfo" class="popupWithInputText"  >
		           		   			<div class="text12" align="left" style="display:block;width:700px;word-break:break-all;">
		           		   				${todo_activeUrlRPGUpdate_TvinnSad}<br/><br/>
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
							 			<td class="text12" align="left">
							 				<img onMouseOver="showPop('sk_info');" onMouseOut="hidePop('sk_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
							 				<span title="fask"><spring:message code="systema.tror.orders.invoice.update.label.sk"/></span>
							 				<div class="text11" style="position: relative; display: inline;" align="left">
											<span style="position:absolute; width:200px;" id="sk_info" class="popupWithInputText"  >
												<font class="text11">
							           			<b>SK</b>
							           			<div>
							           				<p>
							           				Selger(S) /Kjøper(K) .Tast <b>S</b> eller <b>K</b> for å styre denne linjen mot selgers eller kjøpers faktura.
													</p>
													<p>
													<ul>
														<li><b>A</b>=Belastning mot agent. (Gjelder kun for Landmodulen). <br/>
															Fanges opp av ametasystemet og erstattes av linje med X (se under) ved den endelige belastning.
														</li>
														<li><b>X</b>=Fakturalinje mot "fri part". Ikke S eller K. Kundenummer tastes i felt KUNR (X).
														</li>
														<li><b>I</b>=Intern avdelingsbelastning. (Gjelder kun for Transport -modulen).
														</li>
														<li><b>F</b>=Flyfraktbrev. Fakturalinjene legges mot mottagersiden på flyfraktbrevet.
														</li>
													</ul>
													</p>
													<p>NB: Ved samlast export vil fakturalinjene ha status "K".</p>
													
	    										</div>	 
						           				</font>
											</span>
											</div>
							 			
							 			</td>
							            <td class="text12" align="left"><span title="favk">&nbsp;<spring:message code="systema.tror.orders.invoice.update.label.gebyrCode"/></span></td>
							            <td class="text12" align="left"><span title="faVT">&nbsp;<spring:message code="systema.tror.orders.invoice.update.label.text"/></span></td>
					            		<td class="text12" align="left"><span title="faval">&nbsp;<spring:message code="systema.tror.orders.invoice.update.label.currency"/></span></td>
					            		<td class="text12" align="left"><span title="fabelv">&nbsp;<spring:message code="systema.tror.orders.invoice.update.label.amount1"/></span></td>
					            		<td class="text12" align="left"><span title="fakdm">&nbsp;<spring:message code="systema.tror.orders.invoice.update.label.mva"/></span></td>
							        </tr>
							        <tr>
						        		<td align="left">
						        			
						        			<select required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="fask" id="fask">
						 						<option value="">-velg-</option>
							 				  	<option value="A"<c:if test="${model.record.fask == 'A'}"> selected </c:if> >A</option>
												<option value="F"<c:if test="${model.record.fask == 'F'}"> selected </c:if> >F</option>
												<option value="I"<c:if test="${model.record.fask == 'I'}"> selected </c:if> >I</option>
												<option value="K"<c:if test="${model.record.fask == 'K'}"> selected </c:if> >K</option>
												<option value="S"<c:if test="${model.record.fask == 'S'}"> selected </c:if> >S</option>
												<option value="X"<c:if test="${model.record.fask == 'X'}"> selected </c:if> >X</option>  
											</select>
										</td>
										<td class="text12" align="left">
						            		<select required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="favk" id="favk">
						 						<option value="">-select-</option>
							 				  	<c:forEach var="record" items="${model.gebyrCodesList}" >
							 				  		<option value="${record.kgekod}"<c:if test="${model.record.favk == record.kgekod}"> selected </c:if> >${record.kgekod}</option>
												</c:forEach> 
											</select>
							            </td>
										<td align="left" nowrap>
											<input type="text" class="inputTextMediumBlue" name="faVT" id="faVT" size="21" maxlength="20" value="${model.record.faVT}">
										</td>
						        		<td class="text12" align="left">
						            		<select required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="faval" id="faval">
						 						<option value="">-valuta-</option>
							 				  	<c:forEach var="record" items="${model.currencyCodeList}" >
							 				  		<option value="${record.kvakod}"<c:if test="${model.record.faval == record.kvakod || (empty model.record.faval && record.kvakod=='NOK')}"> selected </c:if> >${record.kvakod}</option>
												</c:forEach>  
											</select>
							            </td>
							            <td class="text12Grey" align="left" >
							 				<input type="text" onKeyPress="return numberKey(event)" required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="fabelv" id="fabelv" size="10" maxlength="8" value="${model.record.fabelv}">
							 			</td>
							 			<td align="left">
						        			<select required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="fakdm" id="fakdm">
						 						<option value="">-velg-</option>
							 				  	<option value="J"<c:if test="${model.record.fakdm == 'J'}"> selected </c:if> >J</option>
												<option value="N"<c:if test="${model.record.fakdm == 'N'}"> selected </c:if> >N</option>
											</select>
										</td>
							        </tr>
							        <tr height="10"><td class="text" align="left"></td></tr>
						        </table>
					        </td>
				        </tr>
					    <tr height="10"><td colspan="2" ></td></tr>
					    <tr>	
						    <td align="left" colspan="5">
								<c:choose>	
									<c:when test="${empty recordOrderTrorLandImport.hest || recordOrderTrorLandImport.hest == 'U' || recordOrderTrorLandImport.hest == 'O' || recordOrderTrorLandImport.hest == 'F' }">
										<input class="inputFormSubmit" type="submit" name="submit" value='<spring:message code="systema.tror.submit.save"/>'>
										&nbsp;&nbsp;<input class="inputFormSubmitGray" type="button" name="updCancelButton" id="updCancelButton" value='<spring:message code="systema.tror.submit.clearValues"/>'>
									</c:when>
									<c:otherwise>
			 				    		<input disabled class="inputFormSubmitGrayDisabled" type="submit" name="submit" value='<spring:message code="systema.tror.submit.not.editable"/>'/>
			 				    	</c:otherwise>	
		 				    	</c:choose>	
							</td>							        	
				        </tr>
        	        </table>
        	        </form>
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

