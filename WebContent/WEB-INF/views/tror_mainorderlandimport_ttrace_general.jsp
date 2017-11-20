<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===============================-->
<jsp:include page="/WEB-INF/views/headerTrorTTraceGeneral.jsp" />
<!-- =====================end header ==============================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/jquery.calculator.js"></SCRIPT>
	<SCRIPT type="text/javascript" src="resources/js/jquery-ui-timepicker-addon.js"></SCRIPT>
	<SCRIPT type="text/javascript" src="resources/js/transportdispglobal_edit.js?ver=${user.versionEspedsg}"></SCRIPT>			
	<SCRIPT type="text/javascript" src="resources/js/tror_mainorderlandimport_ttrace_general.js?ver=${user.versionEspedsg}"></SCRIPT>
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
	
	
	<%-- -------------------------------- --%>	
 	<%-- tab area container MASTER TOPIC  --%>
	<%-- -------------------------------- --%>
 	<table width="100%" class="tableBorderWithRoundCorners3D_RoundOnlyOnBottom" border="0" cellspacing="0" cellpadding="0">
		<tr height="3"><td colspan="2">&nbsp;</td></tr>
		<tr>
			<td colspan="3" class="text16Bold">&nbsp;&nbsp;&nbsp;
				<img width="20px" height="20px" src="resources/images/find.png" border="0" alt="frisok">
				<spring:message code="systema.tror.title"/> - <spring:message code="systema.tror.order.tracktracel.general.label"/>
				&nbsp;&nbsp;&nbsp;<font class="text16MediumBlue">Avd/Opd&nbsp;&nbsp;<b>${model.avd}</b>/<b>${model.opd}</b></font>	
			</td>
		</tr>
		<tr height="5"><td colspan="2">&nbsp;</td></tr>
			
			
		<tr>
		<td >
		<table border="0" width="99%" align="center">
			
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
							<form name="createNewItemLine" id="createNewItemLine" method="post" action="TODO.do">
								<table width="80%" cellspacing="0" border="0" cellpadding="0">
									<tr>
										<td class="text12">
											<b>&nbsp;Track/Trace, Hendelseslogg &nbsp;&nbsp;</b>
					            		</td>
									</tr>
									<tr height="2"><td></td></tr>
								</table>
							</form>
							</td>
						</tr> 
						<tr>
							<td class="text11" >
								<table id="tblMain" class="display compact cell-border" >
									<thead>
									<tr style="background-color:#DDDDDD">
										
					        			<th align="center" width="2%" class="text12">&nbsp;<spring:message code="systema.tror.orders.tt.logging.list.label.fbrev"/>&nbsp;</th>
					        			<th align="center" width="2%" class="text12">&nbsp;Endre&nbsp;</th>
					        			<th class="text12">&nbsp;<spring:message code="systema.tror.orders.tt.logging.list.label.date"/>&nbsp;</th>
					                    <th align="center" width="5%" class="text12">&nbsp;<spring:message code="systema.tror.orders.tt.logging.list.label.time"/>&nbsp;</th>   
					                    <th align="center" width="5%" class="text12">&nbsp;<spring:message code="systema.tror.orders.tt.logging.list.label.event"/>&nbsp;</th>
					                    <th class="text12">&nbsp;<spring:message code="systema.tror.orders.tt.logging.list.label.text"/>&nbsp;</th>
					                    <th align="center" width="5%" class="text12">&nbsp;<spring:message code="systema.tror.orders.tt.logging.list.label.user"/>&nbsp;</th>
					        			<th align="center" width="5%" class="text12">&nbsp;<spring:message code="systema.tror.orders.tt.logging.list.label.delete"/>&nbsp;</th>
					        			
					               </tr> 
					               </thead>
					               <tbody>
					               	 <c:forEach items="${model.trackAndTraceloggingList}" var="record" varStatus="counter">    
						               <tr class="tableRow" height="20" >
						                   
						               	<td class="text11">&nbsp;${record.frBrev}</td>
						               	<td align="center" class="text11" >
							               	<a id="recordUpdate_${record.date}@time_${record.time}" href="#" onClick="getBudgetItemData(this);">
					               				<img title="Update" style="vertical-align:bottom;" src="resources/images/update.gif" border="0" alt="update">&nbsp;
					               			</a>
							            </td>   			
						               	<td class="text11" >&nbsp;${record.date}</td>
						               	<td align="right" class="text11" >&nbsp;${record.time}</td>
						               	<td class="text11" >&nbsp;${record.event}</td>
						               	<td class="text11" >&nbsp;${record.textLoc}</td>
						               	<td class="text11" >&nbsp;${record.user}</td>
						               	<%-- DELETE cell --%>							           
						               	<td width="2%" class="text11" align="center">
						               	   <c:if test="${not empty record.date && not empty record.time}">
						                   		<a style="cursor:pointer;" id="avd_${model.avd}@opd_${model.opd}@date_${record.date}@time_${record.time}" onClick="doDeleteItemLine(this);" tabindex=-1 >
								               		<img valign="bottom" src="resources/images/delete.gif" border="0" alt="remove">
								               	</a>&nbsp;
								               	
							               	</c:if>
				               		  	</td> 
						            	</tr>
						            </c:forEach>
					               	</tbody>	

						        </table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr height="5"><td></td></tr>
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
		           	<table class="tabThinBorderWhiteWithSideBorders" width="90%" align="left" border="0" cellspacing="0" cellpadding="0">
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
	 			<td class="text12" align="left" >
					<input tabindex=-1  class="inputFormSubmitStd" type="button" name="newRecordButton" id="newRecordButton" value='Lage ny'>
				</td>
			</tr>
			<tr height="5"><td class="text12" align="left" ></td></tr>
           	<tr>
	 			<td >
	 				<form action="tror_mainorderlandimport_frisokvei_edit.do" name="trorUpdateItemForm" id="trorUpdateItemForm" method="post">
				 	<%--Required key parameters from the Topic parent --%>
				 	<input type="hidden" name="applicationUser" id="applicationUser" value='${user.user}'>
				 	<input type="hidden" name="action" id="action" value='doUpdate'/>
					<input type="hidden" name="avd" id="avd" value='${model.container.avd}'>
					<input type="hidden" name="opd" id="opd" value='${model.container.opd}'>
					<input type="hidden" name="fskodeKey" id="fskodeKey" value='${model.record.fskodeKey}'>
					<input type="hidden" name="fssokKey" id="fssokKey" value='${model.record.fssokKey}'>
					<input type="hidden" name="isModeUpdate" id="isModeUpdate" value="${model.record.isModeUpdate}">
					
				 	<%-- <input type="hidden" name="numberOfItemLinesInTopic" id="numberOfItemLinesInTopic" value="${numberOfItemLinesInTopic}" /> --%>
				 	
				 	<%-- Topic ITEM CREATE --%>
	 				<table width="90%" align="left" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
	 					
				 		<tr height="15">
				 			<td class="text12White" align="left" >
				 				<b>&nbsp;&nbsp;Varelinje&nbsp;</b>
 								<img onClick="showPop('updateInfo');" src="resources/images/update.gif" border="0" alt="edit">&nbsp;&nbsp;<font id="editLineNr"></font>
			 				</td>
		 				</tr>
	 				</table>
					<table width="90%" align="left" class="formFrame" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="12"><td class="text" align="left"></td></tr>
				 		<tr>
					 		<td>
						 		<table  class="tableBorderWithRoundCornersGray" width="95%" border="0" cellspacing="0" cellpadding="0">
						 			<tr height="5"><td class="text" align="left"></td></tr>
						 			<tr >
						 				
						            	<td class="text12" align="left">&nbsp;<span title="todo">&nbsp;Fraktbrevnr.</span></td>
							            <td class="text12" align="left">&nbsp;<span title="todo">&nbsp;Kode</span></td>
					            		<td width="5%" class="text12" align="left">&nbsp;<span title="todo">Hendelsestidspunkt</span></td>
					            		<td class="text12" align="left">&nbsp;<span title="todo">&nbsp;&nbsp;Status</span></td>
					            		<td class="text12" align="left">&nbsp;<span title="todo">&nbsp;&nbsp;Event code</span></td>
					            		<td class="text12" align="left">&nbsp;<span title="todo">&nbsp;&nbsp;Reason code</span></td>
							        </tr>
							        <tr>
						        		<td class="text12" align="left" >&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="todo" id="todo" size="4" maxlength="3" value="${Xmodel.record.fsdokk}"></td>
							            <td class="text12" align="left" >&nbsp;<input type="text" class="inputTextMediumBlue" name="todo" id="todo" size="4" maxlength="3" value="${Xmodel.record.fsdokk}"></td>
							            <td width="5%" class="text12" align="left"><input type="text" class="inputTextMediumBlue"  name="todoDate" id="todoDate" size="10" maxlength="8" value=''>
							 			&nbsp;Kl:<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue"  name="todoDateTime" id="todoDateTime" size="5" maxlength="4" value=''></td>	
							            	
							            <td class="text12" align="left" >&nbsp;
							            	<select class="inputTextMediumBlue" name="todo" id="todo" >
							 				  <option value="">-velg-</option>
											  <option value="S"<c:if test="${Xmodel.record.hepk3 == 'S'}"> selected </c:if> >Send</option>
											  <option value="F"<c:if test="${Xmodel.record.hepk3 == 'F'}"> selected </c:if> >Ferdig</option>
											  <option value="J"<c:if test="${Xmodel.record.hepk3 == 'J'}"> selected </c:if> >Man</option>
											</select>
							            </td>	
							            <td class="text12" align="left" >&nbsp;
							            	<select class="inputTextMediumBlue" name="todo" id="todo" >
							 				  <option value="">-velg-</option>
											  <%-- TODO underregister --%> 
											</select>
							            </td>	
							            <td class="text12" align="left" >&nbsp;
							            	<select class="inputTextMediumBlue" name="todo" id="todo" >
							 				  <option value="">-velg-</option>
											  <%-- TODO underregister --%>
											</select>
							            </td>							            
							        </tr>
							        <tr height="5"><td class="text" align="left"></td></tr>
							        <tr >
						 				<td class="text12" align="left"><span title="todo">&nbsp;NO tekst</span></td>
						 				<td colspan="10" class="text12" align="left"><input type="text" class="inputTextMediumBlue"  name="todo" id="todo" size="75" maxlength="71" value=''></td>
							        </tr>
							        <tr >
						 				<td class="text12" align="left"><span title="todo">&nbsp;EN tekst</span></td>
						 				<td colspan="10" class="text12" align="left"><input type="text" class="inputTextMediumBlue"  name="todo" id="todo" size="75" maxlength="71" value=''></td>
							        </tr>
							        <tr height="5"><td class="text" align="left"></td></tr>
							        <tr >
						 				<td class="text12" align="left"><span title="todo">&nbsp;Depot/term</span></td>
						 				<td colspan="10" class="text12" align="left"><input type="text" class="inputTextMediumBlue"  name="todo" id="todo" size="12" maxlength="10" value=''></td>
							        </tr>
							        <tr >
						 				<td class="text12" align="left"><span title="todo">&nbsp;Name</span></td>
						 				<td colspan="10" class="text12" align="left"><input type="text" class="inputTextMediumBlue"  name="todo" id="todo" size="12" maxlength="10" value=''></td>
							        </tr>
							        <tr height="10"><td class="text" align="left"></td></tr>
							              
							         <tr >
						 				<td class="text12" align="left"><span title="todo">&nbsp;Loggføringstid</span></td>
						 				<td colspan="2" class="text12" align="left"><input readonly type="text" class="inputTextReadOnly"  name="todoDateNow" id="todoDateNow" size="10" maxlength="8" value=''>
							 				&nbsp;Kl:<input readonly type="text" class="inputTextReadOnly"  name="todoDateTimeNow" id="todoDateTimeNow" size="5" maxlength="4" value=''>
							 			</td>
							 			<td class="text12" align="left"><span title="todo">&nbsp;Av bruker ID</span></td>
						 				<td class="text12" align="left"><input readonly type="text" class="inputTextReadOnly"  name="todo" id="todo" size="8" maxlength="10" value=''></td>
							        </tr>
							        
							        
							        <tr height="8"><td class="text" align="left"></td></tr>
							        
						        </table>
					        </td>
				        </tr>
					    <tr height="10"><td colspan="2" ></td></tr>
					    <tr>	
						    <td align="left" colspan="5">
						    	<%--TODO 
								<input class="inputFormSubmit" type="submit" name="submit" id="submit" value='<spring:message code="systema.tror.submit.save"/>'>
								--%>
								<%-- 
								&nbsp;&nbsp;<input class="inputFormSubmitGray" type="button" name="updCancelButton" id="updCancelButton" value='<spring:message code="systema.transportdisp.cancel"/>'>
								--%>
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

