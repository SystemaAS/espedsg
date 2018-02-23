<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTror.jsp" />
<!-- =====================end header ==========================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/jquery.calculator.js"></SCRIPT>
	<SCRIPT type="text/javascript" src="resources/js/jquery-ui-timepicker-addon.js"></SCRIPT>
	<%--<SCRIPT type="text/javascript" src="resources/js/tvinnsadglobal_edit.js?ver=${user.versionEspedsg}"></SCRIPT>--%>			
	<SCRIPT type="text/javascript" src="resources/js/tror_mainorderflyimport_notisblock.js?ver=${user.versionEspedsg}"></SCRIPT>
	<SCRIPT type="text/javascript" src="resources/js/trorFkeys_flyimport.js?ver=${user.versionEspedsg}"></SCRIPT>
	
	<%-- for dialog popup --%>
	<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
	
	
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
	
	<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
	<tr>
	<td>
	<%-- tab container component --%>
	<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
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
				<a class="text14" onClick="setBlockUI(this);" href="tror_mainorderflyimport.do?action=doFetch&heavd=${recordOrderTrorFly.heavd}&heopd=${recordOrderTrorFly.heopd}" > 	
					<img style="vertical-align:middle;" src="resources/images/airplaneYellow.png" width="18px" height="18px" border="0" alt="update">
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tror.order.tab"/>&nbsp;${recordOrderTrorFly.heavd}/${recordOrderTrorFly.heopd}</font>&nbsp;<font class="text10Orange">F4</font>
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a class="text14" onClick="setBlockUI(this);" href="tror_mainorderflyimport_invoice.do?action=doInit&heavd=${recordOrderTrorFly.heavd}&heopd=${recordOrderTrorFly.heopd}" >
					<img style="vertical-align: bottom" src="resources/images/invoice.png" width="16" height="16" border="0" alt="show invoice">
					<font class="tabDisabledLink"><spring:message code="systema.tror.order.faktura.tab"/></font>&nbsp;<font class="text10Orange">F9</font>
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="12%" valign="bottom" class="tab" align="center" nowrap>
				<img style="vertical-align: bottom" src="resources/images/veiledning.png" width="16" height="16" border="0" alt="show messages">
				<font class="tabLink"><spring:message code="systema.tror.order.notisblock.tab"/></font>&nbsp;<font class="text10Orange">F10</font>
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
			<td width="50%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
		</tr>
	</table>
	</td>
	</tr>
	
	<%-- ---------------------- --%>
  	<%-- LIST of existent ITEMs --%>
  	<%-- ---------------------- --%>
	<tr>
	<td>
 	<table width="100%" class="tabThinBorderWhite" border="0" cellspacing="0" cellpadding="0">
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
  			<tr height="10"><td></td></tr>
           	<tr>
				<td>
					<table width="100%" cellspacing="0" border="0" cellpadding="0">
	    					
						<tr>
							<td class="text11" >
								<form name="formItemList" id="formItemList" method="POST" >
			               		<input type="hidden" name="frtavd" id="frtavd" value="${model.avd}">
		 						<input type="hidden" name="frtopd" id="frtopd" value="${model.opd}">
		 						<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
		 						
								<table id="containerdatatableTable" width="80%" cellspacing="2" align="left" >
								<tr>
								<td class="text11">
								<table id="tblNotes" class="display compact cell-border" >
									<thead>
									<tr style="background-color:#DDDDDD">
									    <%-- <th width="2%" align="center" class="text12">&nbsp;Lnr.&nbsp;</th>  --%>   
									    <th width="2%" align="center" class="text12">&nbsp;Endre&nbsp;</th>   
									    <th width="2%" class="text12" nowrap>&nbsp;Dato&nbsp;</th>
					                    <th class="text12" nowrap>&nbsp;Part&nbsp;</th>
					                    <th class="text12" nowrap>&nbsp;Fritekst&nbsp;</th>
				                    	<th width="2%" align="center" class="text12" nowrap>Slett</th>
					               </tr> 
					               </thead>
					               <tbody>
				 					  <c:forEach items="${model.list}" var="record" varStatus="counter">    
						               <tr class="tableRow" height="20" >
							               <%-- <td width="2%" align="center" class="text11" >&nbsp;${record.frtli}</td>  --%>
							               <td width="2%" align="center" class="text11" >
							               		<a tabindex=-1 id="recordUpdate_${record.frtli}_${record.frtdt}" href="#" onClick="getNotisblockItemData(this);">
							               			<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
							               		</a>
							               </td>
							               
							               <td width="2%" class="text11" >&nbsp;${record.frtdt}</td>
							               <td width="2%" class="text11" >&nbsp;${record.frtkod}</td>
							               <td class="text11" >&nbsp;${record.frttxt}</td>
									       <td width="2%" class="text11" align="center" nowrap>
							               	<a onclick="javascript:return confirm('Er du sikker på at du vil slette denne?')" tabindex=-1 href="editNotisblock.do?action=doDelete&subsys=tror_li&orig=${model.orig}&sign=${model.sign}&frtli=${record.frtli}&frtdt=${record.frtdt}&opd=${record.frtopd}&avd=${record.frtavd}">
							               		<img valign="bottom" src="resources/images/delete.gif" border="0" alt="remove">
							               	</a>	
							               </td>
							            </tr>
								        <%-- this param is used ONLY in this JSP 
								        <c:set var="totalNumberOfItemLines" value="${counter.count}" scope="request" />
								        --%> 
								        <%-- this param is used throughout the Controller --%>
								        <c:set var="numberOfItemLinesInTopic" value="${counter.count}" scope="request" /> 
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
			<tr height="2"><td></td></tr>
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
			
			
			<tr >
				<td>
				<form name="createNewItemLine" id="createNewItemLine" method="post" action="editNotisblock.do">
					<%--Required key parameters --%>
				 	<input type="hidden" name="action" id="action" value='doFetch'/>
				 	<input type="hidden" name="subsys" id="subsys" value='tror_li'/>
				 	<input type="hidden" name="orig" id="orig" value="${model.orig}"/>
				 	<input type="hidden" name="opd" id="opd" value="${model.opd}"/>
				 	<input type="hidden" name="avd" id="avd" value="${model.avd}"/>
				 	<input type="hidden" name="sign" id="sign" value="${model.sign}"/>
				 	
					<table width="80%" cellspacing="0" border="0" cellpadding="0">
						<tr>
							<td class="text12Bold">
								<input tabindex=-1 class="inputFormSubmitStd" type="submit" name="submit" onclick="javascript: form.action='editNotisblock.do';" value="<spring:message code="systema.tvinn.sad.export.item.line.init.createnew.submit"/>">
							</td>
						</tr>
						
					</table>
				</form>
				</td>
			</tr> 
			<%-- ------------------------------------------------- --%>
           	<%-- DETAIL Section - Create Item line PRIMARY SECTION --%>
           	<%-- ------------------------------------------------- --%>
           	<tr>
	 			<td >
	 				<form name="trorFlyImportNotisblockItemForm" id="trorFlyImportNotisblockItemForm" method="post">
				 	<%--Required key parameters --%>
				 	<input type="hidden" name="action" id="action" value='doUpdate'/>
				 	<input type="hidden" name="subsys" id="subsys" value='tror_fi'/>
				 	<input type="hidden" name="orig" id="orig" value="${model.orig}"/>
				 	
				 	<input type="hidden" name="frtopd" id="frtopd" value="${model.opd}"/>
				 	<input type="hidden" name="frtavd" id="frtavd" value="${model.avd}"/>
				 	<input type="hidden" name="opd" id="opd" value="${model.opd}"/>
				 	<input type="hidden" name="avd" id="avd" value="${model.avd}"/>
				 	<input type="hidden" name="sign" id="sign" value="${model.sign}"/>
				 	<input type="hidden" name="ceilingLineNumber" id="ceilingLineNumber" value="${model.containerParent.ceilingLineNumber}"/>
				 	
				 	
				 	<%-- <input type="hidden" name="numberOfItemLinesInTopic" id="numberOfItemLinesInTopic" value="${numberOfItemLinesInTopic}" /> --%>
				 	
				 	<%-- Topic ITEM CREATE --%>
	 				<table width="80%" align="left" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="15">
				 			<td class="text12White" align="left" >
				 				<b>&nbsp;&nbsp;N<label onClick="showPop('debugPrintlnAjaxItemFetchAdmin');" >o</label>tat&nbsp;</b>
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
		           		   				${TODOactiveUrlRPGUpdate_TvinnSad}<br/><br/>
		           		   				<button name="updateInformationButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('updateInfo');">Close</button> 
		           		   			</div>
						        </span>  
			 				</td>
		 				</tr>
	 				</table>
					<table width="80%" align="left" class="formFrame" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="15"><td class="text" align="left"></td></tr>
				 		<input type="hidden" name="frtli" id="frtli" value="${model.record.frtli}"/>
				 		
				 		<tr>
					 		<td>
						 		<table width="100%" border="0" cellspacing="0" cellpadding="0">
							 		<tr>
							            <td class="text12" align="left"><span title="frtdt">&nbsp;Dato</span></td>
							            <td class="text12" align="left"><span title="frtkod">&nbsp;Part</span></td>
							            <td class="text12" align="left"><span title="frttxt">&nbsp;Tekst</span></td>
							        </tr>
							        <tr>
						        		<td align="left" valign="top">
						        			<input autofocus type="text" class="inputTextMediumBlue" name="frtdt" id="frtdt" size="9" maxlength="8" value="${model.record.frtdt}">
										</td>
										<td align="left" valign="top">
						        			<input type="text" class="inputTextMediumBlueMandatoryField" name="frtkod" id="frtkod" size="2" maxlength="1" value="${model.record.frtkod}">
										</td>
										<td class="text12" align="left">
						            		<textarea rows="1" cols="79" class="inputTextMediumBlueMandatoryField" name="frttxt" id="frttxt" maxlength="79">${model.record.frttxt}</textarea>
							            </td>
							        </tr>
							        <tr height="10"><td class="text" align="left"></td></tr>
						        </table>
					        </td>
				        </tr>
					    <tr height="10"><td colspan="2" ></td></tr>
					    <tr>	
						    <td align="left" colspan="5">
								<input class="inputFormSubmit" type="submit" name="submit" onclick="javascript: form.action='editNotisblock.do';" value="Lagre notat">
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

