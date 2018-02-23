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
	<SCRIPT type="text/javascript" src="resources/js/tror_mainorderfly_budget.js?ver=${user.versionEspedsg}"></SCRIPT>
	<SCRIPT type="text/javascript" src="resources/js/trorFkeys_flyimport.js?ver=${user.versionEspedsg}"></SCRIPT>
	
	<%-- for dialog popup --%>
	<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
	
	<style type = "text/css">
	.ui-dialog{font-size:10pt;}
	
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
	<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
	<tr>
	<td>	
	<%-- tab container component --%>
	<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
		<%-- This part right here allows for the dynamic allocation of a JSP depending on whether it is IMPORT or EXPORT --%>
		<c:choose>
			<c:when test="${recordOrderTrorFly.heur == 'C'}">
				<c:set var = "tabLinkJsp" scope = "request" value = "mainorderflyimport"/>	
			</c:when>
			<c:otherwise>
				<c:if test="${recordOrderTrorFly.heur == 'D'}">
					<c:set var = "tabLinkJsp" scope = "request" value = "mainorderflyexport"/>
				</c:if>
			</c:otherwise>
		</c:choose>
		
		<tr height="2"><td></td></tr>
		<tr height="25"> 
			<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a class="text14" onClick="setBlockUI(this);" href="tror_mainorderlist.do?action=doFind&sign=${recordOrderTrorFly.hesg}" > 	
					<img style="vertical-align:middle;" src="resources/images/bulletGreen.png" width="6px" height="6px" border="0" alt="open orders">
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tror.orderlist.tab"/></font>&nbsp;<font class="text10Orange">F2</font>
				</a>
			</td>
			
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a class="text14" onClick="setBlockUI(this);" href="tror_<c:out value="${tabLinkJsp}"/>.do?action=doFetch&heavd=${recordOrderTrorFly.heavd}&heopd=${recordOrderTrorFly.heopd}" > 	
					<c:choose>
						<c:when test="${recordOrderTrorFly.heur == 'C'}">
							<img style="vertical-align:middle;" src="resources/images/airplaneYellow.png" width="18px" height="18px" border="0" alt="create new">
						</c:when>
						<c:otherwise>
							<c:if test="${recordOrderTrorFly.heur == 'D'}">
								<img style="vertical-align:middle;" src="resources/images/airplaneBlue.png" width="18px" height="18px" border="0" alt="create new">
							</c:if>
						</c:otherwise>
					</c:choose>
				<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tror.order.tab"/>&nbsp;${recordOrderTrorFly.heavd}/${recordOrderTrorFly.heopd}</font>&nbsp;<font class="text10Orange">F4</font>
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a class="text14" onClick="setBlockUI(this);" href="tror_<c:out value="${tabLinkJsp}"/>_invoice.do?action=doFetch&heavd=${recordOrderTrorFly.heavd}&heopd=${recordOrderTrorFly.heopd}" > 	
					<img style="vertical-align: bottom" src="resources/images/invoice.png" width="16" height="16" border="0" alt="show invoice">
					<font class="tabDisabledLink"><spring:message code="systema.tror.order.faktura.tab"/></font>&nbsp;<font class="text10Orange">F9</font>
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a class="text14" onClick="setBlockUI(this);" href="editNotisblock.do?action=doFetch&subsys=${subSystem}&avd=${recordOrderTrorFly.heavd}&opd=${recordOrderTrorFly.heopd}&sign=${recordOrderTrorFly.hesg}" > 	
					<img style="vertical-align: bottom" src="resources/images/veiledning.png" width="16" height="16" border="0" alt="show messages">
					<font class="tabDisabledLink"><spring:message code="systema.tror.order.notisblock.tab"/></font>&nbsp;<font class="text10Orange">F10</font>
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a class="text14" onClick="setBlockUI(this);" href="tror_mainorderfly_frisokvei.do?action=doFetch&avd=${recordOrderTrorFly.heavd}&sign=${recordOrderTrorFly.hesg}&opd=${recordOrderTrorFly.heopd}">
					<img style="vertical-align: bottom" src="resources/images/lightbulb.png" width="14" height="14" border="0" alt="show log">
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tror.order.frisokvei.tab"/></font>&nbsp;<font class="text10Orange">F7</font>
				</a>
			</td>
			<c:if test="${recordOrderTrorFly.hepk5 == 'J' || recordOrderTrorFly.hepk5 == 'P'}">
				<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
				<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
					<a class="text14" onClick="setBlockUI(this);" href="tror_mainorderfly_airfreightbill_gate.do?avd=${recordOrderTrorFly.heavd}&sign=${recordOrderTrorFly.hesg}&opd=${recordOrderTrorFly.heopd}">
						<img style="vertical-align: bottom" src="resources/images/pen.png" width="16" height="16" border="0" alt="Awb">
						<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tror.order.flyfraktbrev.tab"/></font>&nbsp;<font class="text10Orange">F8</font>
					</a>
				</td>
			</c:if>
			
			<c:if test="${recordOrderTrorFly.hepk1 == 'J' || recordOrderTrorFly.hepk1 == 'P'}">
				<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
				<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
					<a class="text14" onClick="setBlockUI(this);" href="tror_mainorderfly_freightbill_gate.do?dfavd=${recordOrderTrorFly.heavd}&sign=${recordOrderTrorFly.hesg}&dfopd=${recordOrderTrorFly.heopd}">
						<img style="vertical-align: bottom" src="resources/images/fraktbrev.png" width="14" height="14" border="0" alt="show freight doc">
						<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tror.order.fraktbrev.tab"/></font>&nbsp;<font class="text10Orange">F3</font>
					</a>
				</td>
			</c:if>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a class="text14" onClick="setBlockUI(this);" href="tror_mainorderfly_ttrace.do?avd=${recordOrderTrorFly.heavd}&sign=${recordOrderTrorFly.hesg}&opd=${recordOrderTrorFly.heopd}">
					<img style="vertical-align: bottom" src="resources/images/sort_down.png" width="10" height="10" border="0" alt="show more">
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tror.order.more.tab"/></font>
				</a>
			</td>
				
			<td width="80%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>	
		</tr>
	</table>
	</td>
	</tr>
	
	
	
			
           	<%-- ---------------------- --%>
           	<%-- LIST of existent ITEMs --%>
           	<%-- ---------------------- --%>
           	<tr>
				<td>
					<table width="100%" id="wrapperTable" class="tabThinBorderWhite" cellspacing="0" border="0" cellpadding="0">
						<%--for F-Keys shortcuts. Used only in trorFkeys_...js --%>
						<input type="hidden" name="fkeysavd" id="fkeysavd" value='${recordOrderTrorFly.heavd}'>
						<input type="hidden" name="fkeysopd" id="fkeysopd" value='${recordOrderTrorFly.heopd}'>
						<input type="hidden" name="fkyessign" id="fkyessign" value='${recordOrderTrorFly.hesg}'>
						<c:choose>
							<c:when test="${recordOrderTrorFly.heur == 'C'}">
								<input type="hidden" name="fkyessubsys" id="fkyessubsys" value='mainorderflyimport'>
							</c:when>
							<c:otherwise>
								<c:if test="${recordOrderTrorFly.heur == 'D'}">
									<input type="hidden" name="fkyessubsys" id="fkyessubsys" value='mainorderflyexport'>
								</c:if>
							</c:otherwise>
						</c:choose>
		   				<%-- separator --%>
		       			<tr height="20"><td></td></tr> 
		       			
						<tr> <!-- Second tab row... -->
			 	   	 		<td align="center" width="99%">
			 					<table class="formFrameHeaderTransparent" width="99%" cellspacing="0" border="0" cellpadding="0">
									<tr height="20"> 
										<td width="12%" valign="bottom" class="tabDisabledSub" align="center" nowrap>
											<a id="alinkMoreTrackTrace" onClick="setBlockUI(this);" href="tror_mainorderfly_ttrace.do?avd=${recordOrderTrorFly.heavd}&sign=${recordOrderTrorFly.hesg}&opd=${recordOrderTrorFly.heopd}">
												<font class="tabDisabledLinkMinor">&nbsp;<spring:message code="systema.tror.order.more.tab.tracktrace.subtab"/></font>&nbsp;						
											</a>
										</td>
										<td width="12%" valign="bottom" class="tabDisabledSub" align="center" nowrap>
											<a id="alinkMoreTrackTraceGeneral" onClick="setBlockUI(this);" href="tror_mainorderfly_ttrace_general.do?ttavd=${recordOrderTrorFly.heavd}&ttopd=${recordOrderTrorFly.heopd}">
												<font class="tabDisabledLinkMinor">&nbsp;<spring:message code="systema.tror.order.more.tab.tracktracegrl.subtab"/></font>&nbsp;					
											</a>
										</td>
										<td width="12%" valign="bottom" class="tabDisabledSub" align="center" nowrap>
											<a id="alinkEdiLogging" onClick="setBlockUI(this);" href="tror_mainorderfly_logging.do?avd=${recordOrderTrorFly.heavd}&sign=${recordOrderTrorFly.hesg}&opd=${recordOrderTrorFly.heopd}">
												<font class="tabDisabledLinkMinor">&nbsp;<spring:message code="systema.tror.order.more.tab.edilog.subtab"/></font>&nbsp;					
											</a>
										</td>
										
										<td width="12%" valign="bottom" class="tabDisabledSub" align="center" nowrap>
											<a id="alinkArchive" onClick="setBlockUI(this);" href="tror_mainorderfly_archive.do?avd=${recordOrderTrorFly.heavd}&sign=${recordOrderTrorFly.hesg}&opd=${recordOrderTrorFly.heopd}">
												<font class="tabDisabledLinkMinor">&nbsp;<spring:message code="systema.tror.order.more.tab.archive.subtab"/></font>&nbsp;					
											</a>
										</td>
										<td width="12%" valign="bottom" class="tabSub" align="center" nowrap>
											<font class="tabLinkMinor">&nbsp;<spring:message code="systema.tror.order.budget.tab"/></font>&nbsp;					
										</td>
										
									 	<td width="80%" class="tabFantomSpace" align="center" nowrap></td>
									</tr>
									
								</table>
							</td>
			 	   	 	</tr> <!-- End second tab row -->
   	 		
						<tr>
							<td class="text11" >
							<%-- this table wrapper is needed for the datatables width --%>
							<table width="90%" cellspacing="0" border="0">
							
							<tr>
							<td class="text11">
								<table id="tblBudget" class="display compact cell-border" >
									<thead>
									<tr style="background-color:#DDDDDD">
										<th width="2%" align="center" class="text12" >&nbsp;<span title="bukdm">M&nbsp;</span></th>
									    <th width="2%" align="center" class="text12" >&nbsp;<span title="bust">S&nbsp;</span></th>
									    <th width="2%" align="center" class="text12" >&nbsp;Endre&nbsp;</th>
									    <th class="text12" >&nbsp;<span title="bubnr">Rekvnr&nbsp;</span></th>
					                    <th class="text12" >&nbsp;<span title="buvk">Geb&nbsp;</span></th>
					                    <th class="text12" >&nbsp;<span title="bubl">Beløp&nbsp;</span></th>
					                    <th class="text12" >&nbsp;<span title="buval">Val&nbsp;</span></th>
					                    <th class="text12" >&nbsp;<span title="bulnr">Leverandør&nbsp;</span></th>
					                    <th width="2%" class="text12" >&nbsp;<span title="butype">Ty&nbsp;</span></th>
					                    <th width="2%" class="text12" >&nbsp;<span title="bublst">Bls&nbsp;</span></th>
					                    <th class="text12" >&nbsp;<span title="busg">Sig&nbsp;</span></th>
					                    <th class="text12" >&nbsp;<span title="butunr">Turnr&nbsp;</span></th>
   					                    <th class="text12" >&nbsp;<span title="buoavd">Avd</span></th>
					                    <th class="text12" >&nbsp;<span title="buopd">Oppd&nbsp;</span></th>
					                    <th class="text12" >&nbsp;<span title="butxt">Fritekst&nbsp;</span></th>
					                    <th class="text12" >&nbsp;<span title="bubiln">Bilnr&nbsp;</span></th>
					                    <th class="text12" >&nbsp;<span title="bufedt">Dato&nbsp;</span></th>
					                    <th class="text12" >&nbsp;<span title="butnr">Transp.&nbsp;</span></th>
					                    <th class="text12" >&nbsp;<span title="bupMn/bupAr">Peri&nbsp;</span></th>
					                    <th class="text12" >&nbsp;<span title="todoDato">Plukk&nbsp;</span></th>
					                    <th class="text12" >&nbsp;Slett&nbsp;</th>
					               </tr> 
					               </thead>
					               <tbody>
				 					  <c:forEach items="${model.list}" var="record" varStatus="counter">    
							               <tr class="tableRow">
							                   
							               <td width="2%" align="center" class="text11" align="center">&nbsp;${record.bukdm}</td>
							               <td width="2%" align="center" class="text11" >&nbsp;<b class="text11Red">${record.bust}</b></td>
							               <td width="2%" align="center" class="text11" >&nbsp;
							               	   <c:if test="${record.bust!='S'}">
								            	   	<a id="recordUpdate_${record.bubnr}" href="#" onClick="getBudgetItemData(this);">
							               				<img title="Update" style="vertical-align:bottom;" src="resources/images/update.gif" border="0" alt="update">&nbsp;
							               			</a>
						               			</c:if>
					               			</td>
							               <td class="text11" >&nbsp;<font class="text11MediumBlue">${record.bubnr}</font></td>	
							               <td class="text11" >&nbsp;${record.buvk}</td>
							               <td class="text11" >&nbsp;${record.bubl}</td>
							               <td class="text11" >&nbsp;${record.buval}</td>
							               <td class="text11" >&nbsp;
							               	 <c:if test="${not empty record.levNavn}">
							               		<font class="text11MediumBlue">${record.levNavn}</font>&nbsp;[${record.bulnr}]
							               	</c:if>	
					               			</td>
							               <td width="2%" class="text11" >&nbsp;${record.butype}</td>
							               <td width="2%" class="text11" >&nbsp;${record.bublst}</td>
							               <td class="text11" >&nbsp;${record.busg}</td>
							               <td class="text11" >&nbsp;
							               		<c:if test="${not empty record.butunr && record.butunr!='0'}">	
							               			${record.butunr}
							               		</c:if>	
						               		</td>
							               <td class="text11" >&nbsp;
							               		<c:if test="${not empty record.buoavd && record.buoavd!='0'}">	
							               			${record.buoavd}
							               		</c:if>
							               	</td>
							               <td class="text11" >&nbsp;
							               		<c:if test="${not empty record.buopd && record.buopd!='0'}">
							               			${record.buopd}
							               		</c:if>	
							               	</td>
							               <td class="text11" >&nbsp;${record.butxt}</td>
							               <td class="text11" >&nbsp;${record.bubiln}</td>
							               <td class="text11" >&nbsp;
							               		<c:if test="${not empty record.bufedt && record.bufedt!='0'}">
							               			${record.bufedt}
							               		</c:if>
						               		</td>
							               <td class="text11" >&nbsp;
							               	  <c:if test="${not empty record.traNavn}">	
							               		<font class="text11MediumBlue">${record.traNavn}</font>&nbsp;[${record.butnr}]
							               	  </c:if>	
					               			</td>
							               <td class="text11" >&nbsp;${record.bupMn}/${record.bupCc}${record.bupAr}</td>
							               <td class="text11" >&nbsp;todo</td>
							               <%-- DELETE cell --%>							           
							               <td class="text11" align="center">
							               	   <c:if test="${not empty record.bubnr}">
							                   		<a style="cursor:pointer;" id="avd_${record.buoavd}@opd_${record.buopd}@tur_${record.butunr}@bubnr_${record.bubnr}" onClick="doPermanentlyDeleteInvoiceLine(this);" tabindex=-1 >
									               		<img valign="bottom" src="resources/images/delete.gif" border="0" alt="remove">
									               	</a>&nbsp;
									               	
								               	</c:if>
					               		  </td> 
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
				</td>
			</tr>
			<tr height="10"><td></td></tr>
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
		           	<table class="tabThinBorderWhiteWithSideBorders" width="100%" align="left" border="0" cellspacing="0" cellpadding="0">
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
	 				<form action="tror_mainorderfly_budget_edit.do" name="budgetUpdateItemForm" id="budgetUpdateItemForm" method="post">
				 	<%--Required key parameters from the Topic parent --%>
				 	<input type="hidden" name="applicationUser" id="applicationUser" value='${user.user}'>
				 	<input type="hidden" name="action" id="action" value='doUpdate'/>
					<input type="hidden" name="avd" id="avd" value='${model.container.avd}'>
					<input type="hidden" name="opd" id="opd" value='${model.container.opd}'>
					<input type="hidden" name="hepro" id="hepro" value='${Xmodel.container.tur}'>
					<input type="hidden" name="isModeUpdate" id="isModeUpdate" value="${model.record.isModeUpdate}">
					
				 	<%-- <input type="hidden" name="numberOfItemLinesInTopic" id="numberOfItemLinesInTopic" value="${numberOfItemLinesInTopic}" /> --%>
				 	
				 	<%-- Topic ITEM CREATE --%>
	 				<table width="90%" align="left" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
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
				 				<img onClick="showPop('updateInfo');" src="resources/images/update.gif" border="0" alt="edit">&nbsp;&nbsp;<font id="editLineNr"></font>
				 				<span style="position:absolute; left:150px; top:200px; width:800px; height:400px;" id="updateInfo" class="popupWithInputText"  >
		           		   			<div class="text12" align="left" style="display:block;width:700px;word-break:break-all;">
		           		   				${XactiveUrlRPGUpdate_TvinnSad}<br/><br/>
		           		   				<button name="updateInformationButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('updateInfo');">Close</button> 
		           		   			</div>
						        </span>  
			 				</td>
		 				</tr>
	 				</table>
					<table width="90%" align="left" class="formFrame" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="12"><td class="text" align="left"></td></tr>
				 		<tr>
					 		<td>
						 		<table  class="tableBorderWithRoundCornersGray" width="100%" border="0" cellspacing="0" cellpadding="0">
						 			<tr height="5"><td class="text" align="left"></td></tr>
						 			<tr >
						 				<td class="text12" align="left"><span title="bubnr">&nbsp;
							            	<img onMouseOver="showPop('rekvnr_info');" onMouseOut="hidePop('rekvnr_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
							 				Rekvnr.</span>
							 			</td>
							            <div class="text11" style="position: relative;" align="left">
											<span style="position:absolute; left:20px; top:20px; width:350px" id="rekvnr_info" class="popupWithInputText"  >
												<font class="text11">
							           			<b>Rekvnr.</b>
							           			<div>
							           				<p>Løpenummer tildeles automatisk fra telleverk (bilagsserie Å). <br/>
							           				Denne serien initieres automatisk og starter på 1000000.</p>
	    										</div>	 
						           			</font>
											</span>
											</div>
							            <td class="text12" align="left"><span title="bukdm">&nbsp;MVA&nbsp;</span></td>
						            	<td class="text12" align="left"><span title="buvk">&nbsp;Geb.</span>
							            	<a tabindex=-1 id="buvkIdLink">
	 											<img id="imgGebyrCodesSearch" align="bottom" style="cursor:pointer;" src="resources/images/find.png" height="13px" width="13px" border="0" alt="search">
	 										</a>
							            </td>
							            
							            <td class="text12" align="left"><span title="bubl">&nbsp;Beløp</span></td>
					            		<td class="text12" align="left"><span title="buval">&nbsp;Valuta</span></td>
					            		<td class="text12" align="left"><span title="bulnr">&nbsp;Leverandør</span>
						            		<a tabindex=-1 id="bulnrIdLink">
	 											<img id="imgSupplierSearch" align="bottom" style="cursor:pointer;" src="resources/images/find.png" height="13px" width="13px" border="0" alt="search">
	 										</a>
						            	</td>
						            	<td class="text12" align="left"><span title="levNavn">&nbsp;Lev.navn</span></td>
						            	<td class="text12" align="left"><span title="butype">&nbsp;
						            		<img onMouseOver="showPop('typekost_info');" onMouseOut="hidePop('typekost_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
						            		Type&nbsp;</span>
						            	</td>
						            		<div class="text11" style="position: relative;" align="left">
											<span style="position:absolute; left:920px; top:20px; width:350px" id="typekost_info" class="popupWithInputText"  >
												<font class="text11">
							           			<b>Type forventet kostnad</b>
							           			<div>
							           				<p>Rett kodeangivelse letter attestering.</p>
							           				<ul>
							           					<li><b>A</b> = Avtalt pris/kostnad</li>
							           					<li><b>E</b> = Estimert pris/kostnad</li>
							           					<li><b>P</b> = Provisjonskalkulert estimat. Denne koden kan ikke settes manuelt. <br/>
							           							Den betyr at program for "Avsetningsforslag ved periodeslutt er kjørt og at pris for turoppgjør ikke var avtalt/estimert. <br/>
							           							Systemet har kalkulert et provisjonsbasert estimat som er benyttet i forslaget.</li>
							           				</ul>
	    										</div>	 
						           			</font>
											</span>
											</div>
						            	<td class="text12" align="left"><span title="bublst">&nbsp;
						            		<img onMouseOver="showPop('bls_info');" onMouseOut="hidePop('bls_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
						            		Bls&nbsp;</span>
						            	</td>
						            		<div class="text11" style="position: relative;" align="left">
											<span style="position:absolute; left:1020px; top:20px; width:350px" id="bls_info" class="popupWithInputText"  >
												<font class="text11">
							           			<b>Bls. Belastes</b>
							           			<div>
							           				<p>Hvem forventes å bære kostnaden:</p>
							           				<ul>
							           					<li><b>E</b> = Egen (Det er vi selv som må bære kostnaden)</li>
							           					<li><b>T</b> = Transportør (Trekkes i transportøravregning)</li>
							           					<li><b>K</b> = Kunder (Den som mottar faktura på oppdraget skal ekstradebiteres denne kostnaden).<br/>
							           								<b>Teknisk:</b> Ved plukk til kostn.føring legges 100,00 i feltet for "prosent til inntekt". 
							           								<br/>Diff mellom kostnad og det som ER fakturert legges ut som åpen fakturalinje(v/overf. til øko).</li>
							           					<li><b>U</b> = Ubetinget mot KUNDE. (selv om inntekt finnes fra før, Teknisk 999,99 i felt for "prosent til inntekt").</li>
							           					<li><b>O</b> = Ferdig utført trekk i hovedavregning på angitt tur</li>
							           					<li><b>J</b> = Justering (benyttes NÅR TUR ALT ER AVREGNET)</li>
							           					<li><b>P</b> = Justering ER UTFØRT (hovedstatus blir samtidig A)</li>
							           					
							           				</ul>
	    										</div>	 
						           			</font>
											</span>
											</div>
						            	<td class="text12" align="left"><span title="busg">&nbsp;Sign&nbsp;</span></td>
						            	<td class="text12" align="left" ><span title="butunr">&nbsp;Turnr</span></td>
						            	<td class="text12" align="left"><span title="buoavd">&nbsp;&nbsp;&nbsp;Avd</span></td>
						            	<td class="text12" align="left" ><span title="buopd">&nbsp;Oppd.</span></td>
							        </tr>
							        <tr>
						        		<td class="text12" align="left">&nbsp;
						            		<input readonly type="text" class="inputTextReadOnly" name="bubnr" id="bubnr" size="8" maxlength="8" value="${model.record.bubnr}">
							            </td>
							            <td class="text12" align="left" valign="middle">
						            		<select class="inputTextMediumBlueField" id="bukdm" name="bukdm">
						            			<option value="">-blank-</option>
						            			<option value="J" <c:if test="${model.record.bukdm == 'J'}"> selected </c:if> >J</option>
						           				<option value="N" <c:if test="${model.record.bukdm == 'N'}"> selected </c:if> >N</option>
							           		</select>
						            	</td>
							            <td align="left">
						        			<select class="inputTextMediumBlueMandatoryField" name="buvk" id="buvk">
						 						<option value="">-select-</option>
							 				  	<c:forEach var="record" items="${model.gebyrCodesList}" >
							 				  		<option value="${record.kgekod}"<c:if test="${model.record.buvk == record.kgekod}"> selected </c:if> >${record.kgekod}</option>
												</c:forEach>
											</select>
										</td>
										<td class="text12" align="left">
						            		<input type="text" onKeyPress="return amountKey(event)" class="inputTextMediumBlueMandatoryField" name="bubl" id="bubl" size="8" maxlength="8" value="${model.record.bubl}">
							            </td>
										<td align="left" nowrap valign="middle">
							            	<select class="inputTextMediumBlueMandatoryField" name="buval" id="buval">
						 						<option value="">-valuta-</option>
							 				  	<c:forEach var="currency" items="${model.currencyCodeList}" >
							 				  		<option value="${currency}"<c:if test="${model.record.buval == currency || (empty model.record.buval && currency=='NOK')}"> selected </c:if> >${currency}</option>
												</c:forEach> 
											</select>
										</td>
						        		<td class="text12" align="left" ><input type="text" class="inputText" name="bulnr" id="bulnr" size="9" maxlength="8" value="${model.record.bulnr}"></td>
							            <td class="text12" align="left">&nbsp;
						            		<input readonly tabindex=-1 class="inputTextReadOnly" name="levNavn" id="levNavn" size="20" maxlength="35" value="${model.record.levNavn}">
							            </td>
							            <td class="text12" align="left" >
							            	<select class="inputTextMediumBlueMandatoryField" name="butype" id="butype">
						 						<option value="">-select-</option>
						 						<c:choose>
						 							<c:when test="${not empty model.record.butype}">
						 								<option value="A" <c:if test="${model.record.butype == 'A'}"> selected </c:if> >A</option>
					           							<option value="E" <c:if test="${model.record.butype == 'E'}"> selected </c:if> >E</option>
					           							<option value="P" <c:if test="${model.record.butype == 'P'}"> selected </c:if> >P</option>
					           						</c:when>
					           						<c:otherwise>
					           							<option value="A" >A</option>
					           							<option value="E" selected >E</option>
					           							<option value="P" >P</option>
					           						</c:otherwise>
				           						</c:choose>						 						
					           				</select>
						            	</td>
							            <td class="text12" align="left" >
							            	<select class="inputTextMediumBlueMandatoryField" name="bublst" id="bublst">
						 						<option value="">-select-</option>
						 						<c:choose>
						 							<c:when test="${not empty model.record.bublst}">
						 								<option value="E" <c:if test="${model.record.bublst == 'E'}"> selected </c:if> >E</option>
							           					<option value="T" <c:if test="${model.record.bublst == 'T'}"> selected </c:if> >T</option>
							           					<option value="K" <c:if test="${model.record.bublst == 'K'}"> selected </c:if> >K</option>
							           					<option value="U" <c:if test="${model.record.bublst == 'U'}"> selected </c:if> >U</option>
							           					<option value="O" <c:if test="${model.record.bublst == 'O'}"> selected </c:if> >O</option>
							           					<option value="J" <c:if test="${model.record.bublst == 'J'}"> selected </c:if> >J</option>
							           					<option value="P" <c:if test="${model.record.bublst == 'P'}"> selected </c:if> >P</option>
					           						</c:when>
					           						<c:otherwise>
					           							<c:choose>
						           							<c:when test="${not empty model.parentTrip}">
											            		<option value="E" >E</option>
									           					<option value="T" selected >T</option>
									           					<option value="K" >K</option>
									           					<option value="U" >U</option>
									           					<option value="O" >O</option>
									           					<option value="J" >J</option>
									           					<option value="P" >P</option>
								           					</c:when>
								           					<c:otherwise>
								           						<option value="E" selected >E</option>
									           					<option value="T" >T</option>
									           					<option value="K" >K</option>
									           					<option value="U" >U</option>
									           					<option value="O" >O</option>
									           					<option value="J" >J</option>
									           					<option value="P" >P</option>
								           					</c:otherwise>
							           					</c:choose>
					           						</c:otherwise>
				           						</c:choose>
					           				</select>
						            	</td>
							            <td class="text12" align="left" ><input type="text" class="inputText" name="busg" id="busg" size="3" maxlength="3" value="${user.signatur}"></td>
							            
						        		<td class="text12" align="left" >
						        			<input type="text" class="inputText" name="butunr" id="butunr" size="8" maxlength="10" value="${model.container.tur}">
						        		</td>
						        		<td class="text12">&nbsp;
						        			<c:choose>
												<c:when test="${not empty model.record.buoavd}">
							        				<input type="text" class="inputText" name="buoavd" id="buoavd" size="5" maxlength="4" value="${model.record.buoavd}">
							        			</c:when>
							        			<c:otherwise>
							        				<c:choose>
													<c:when test="${not empty model.parentTrip}">
							        					<input type="text" class="inputText" name="buoavd" id="buoavd" size="5" maxlength="4" value="">
							        				</c:when>
							        				<c:otherwise>
							        					<input type="text" class="inputText" name="buoavd" id="buoavd" size="5" maxlength="4" value="${model.container.avd}">
							        				</c:otherwise>
							        				</c:choose>
							        			</c:otherwise>
						        			</c:choose>
						        		</td>
						        		<td class="text12" align="left" >
						        			<c:choose>
												<c:when test="${not empty model.record.buopd}">
							        				<input type="text" class="inputText" name="buopd" id="buopd" size="8" maxlength="7" value="${model.record.buopd}">
							        			</c:when>
							        			<c:otherwise>
							        				<c:choose>
													<c:when test="${not empty model.parentTrip}">
														<input type="text" class="inputText" name="buopd" id="buopd" size="8" maxlength="7" value="">
													</c:when>
													<c:otherwise>
							        					<input type="text" class="inputText" name="buopd" id="buopd" size="8" maxlength="7" value="${model.container.opd}">
							        				</c:otherwise>
							        				</c:choose>
							        			</c:otherwise>
						        			</c:choose>
						        		</td>
						        		
							        </tr>
							        <tr height="12"><td class="text" align="left" colspan="20"><hr></td></tr>
							        <tr height="3"><td class="text" align="left"></td></tr>
							        <tr>
							 			<td colspan="2" class="text12" align="left" >&nbsp;&nbsp;<span title="butxt">Fritekst</span></td>
							            <td class="text12" align="left" >&nbsp;<span title="bubiln">Bilnr.</td>
							            <td class="text12" align="left" >&nbsp;<span title="bufedt">Dato</td>
							            <td class="text12" align="left" >&nbsp;<span title="todoKo">Ko</td>
							            <td class="text12" align="left" >&nbsp;<span title="butnr">Transp.
							            	<a tabindex=-1 id="butnrIdLink">
		 										<img id="imgTruckersNrSearch" style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="13px" height="13px" border="0" alt="search">
		 									</a>
							            </td>
							            <td class="text12" align="left"><span title="traNavn">&nbsp;Transp.navn</span></td>
							            <td class="text12" align="left" >&nbsp;<span title="bupMn">Periode</td>
							            <td class="text12" align="left" >&nbsp;<span title="bupAr">År</td>
					            		<td class="text12" align="left" >&nbsp;<span title="bubilk">Bilkode</td>	
							        </tr>
							        <tr>
							        	<td colspan="2" class="text12">&nbsp;	
						        			<input type="text" class="inputText" name="butxt" id="butxt" size="35" maxlength="35" value="${model.record.butxt}">
										</td>
										<td class="text12">
											<c:choose>
											<c:when test="${not empty model.record.bubiln}">	
						        				<input type="text" class="inputText" name="bubiln" id="bubiln" size="10" maxlength="8" value="${model.record.bubiln}">
						        			</c:when>
						        			<c:otherwise>
						        				<input type="text" class="inputText" name="bubiln" id="bubiln" size="10" maxlength="8" value="${model.recordSpecificTrip.tubiln}">
						        			</c:otherwise>
						        			</c:choose>
										</td>
										<td class="text12">	
											<c:choose>
											<c:when test="${not empty model.record.bufedt}">
						        				<input type="text" class="inputText" name="bufedt" id="bufedt" size="9" maxlength="8" value="${model.record.bufedt}">
						        			</c:when>
						        			<c:otherwise>
						        				<input type="text" class="inputText" name="bufedt" id="bufedt" size="9" maxlength="8" value="${model.recordSpecificTrip.tudt}">
						        			</c:otherwise>
						        			</c:choose>
										</td>
										<td class="text12">	
						        			<select class="inputTextMediumBlueField" name="todoKo" id="todoKo">
						 						<option value="">-select-</option>
							            		<option value="B" <c:if test="${Xmodel.record.todo == 'B'}"> selected </c:if> >?</option>
					           				</select>
										</td>
										<td class="text12">	
											<c:choose>
											<c:when test="${not empty model.record.butnr}">
						        				<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="butnr" id="butnr" size="9" maxlength="8" value="${model.record.butnr}">
						        			</c:when>
						        			<c:otherwise>
						        				<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="butnr" id="butnr" size="9" maxlength="8" value="${model.recordSpecificTrip.tuknt2}">
						        			</c:otherwise>
						        			</c:choose>
										</td>
										<td class="text12" align="left">&nbsp;
						            		<input tabindex=-1 readonly class="inputTextReadOnly" name="traNavn" id="traNavn" size="20" maxlength="35" value="${model.record.traNavn}">
							            </td>
										<td class="text12">	
											<c:choose>
											<c:when test="${not empty model.record.bupMn}">
						        				<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="bupMn" id="bupMn" size="2" maxlength="2" value="${model.record.bupMn}">
						        			</c:when>
						        			<c:otherwise>
						        				<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="bupMn" id="bupMn" size="2" maxlength="2" value="${model.recordSpecificTrip.turmnd}">
						        			</c:otherwise>
						        			</c:choose>
										</td>
										<td class="text12">	
											<c:choose>
											<c:when test="${not empty model.record.bupAr}">
						        				<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="bupAr" id="bupAr" size="4" maxlength="4" value="${model.record.bupCc}${model.record.bupAr}">
						        			</c:when>
						        			<c:otherwise>
						        				<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="bupAr" id="bupAr" size="4" maxlength="4" value="${model.recordSpecificTrip.centuryYearTurccTuraar}">
						        			</c:otherwise>
						        			</c:choose>
										</td>
										<td class="text12">&nbsp;
											<c:choose>
											<c:when test="${not empty model.record.bubilk}">	
						        				<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="bubilk" id="bubilk" size="3" maxlength="3" value="${model.record.bubilk}">
						        			</c:when>
						        			<c:otherwise>
						        				<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="bubilk" id="bubilk" size="3" maxlength="3" value="${model.recordSpecificTrip.tubilk}">
						        			</c:otherwise>
						        			</c:choose>
										</td>
							        </tr>
							        <tr height="8"><td class="text" align="left"></td></tr>
						        </table>
					        </td>
				        </tr>
					    <tr height="10"><td colspan="2" ></td></tr>
					    <tr>	
						    <td align="left" colspan="5">
								<input class="inputFormSubmit" type="submit" name="submit" id="submit" value='<spring:message code="systema.tror.submit.save"/>'>
								&nbsp;&nbsp;<input class="inputFormSubmitGray" type="button" name="updCancelButton" id="updCancelButton" value='<spring:message code="systema.tror.cancel"/>'>
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
	
	
		
<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->

