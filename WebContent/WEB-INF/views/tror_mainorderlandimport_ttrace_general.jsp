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
							<td class="ownScrollableSubWindow" style="width:90%; height:15em;">
								<table width="100%" cellspacing="0" border="0" cellpadding="0">
									<tr class="tableHeaderField" height="20" valign="left">
										
					        			<td align="center" width="2%" class="tableHeaderFieldFirst">&nbsp;<spring:message code="systema.tror.orders.tt.logging.list.label.fbrev"/>&nbsp;</td>   
									    <td class="tableHeaderField">&nbsp;<spring:message code="systema.tror.orders.tt.logging.list.label.date"/>&nbsp;</td>
					                    <td align="center" width="5%" class="tableHeaderField">&nbsp;<spring:message code="systema.tror.orders.tt.logging.list.label.time"/>&nbsp;</td>   
					                    <td align="center" width="5%" class="tableHeaderField">&nbsp;<spring:message code="systema.tror.orders.tt.logging.list.label.event"/>&nbsp;</td>
					                    <td class="tableHeaderField">&nbsp;<spring:message code="systema.tror.orders.tt.logging.list.label.text"/>&nbsp;</td>
					                    <td align="center" width="5%" class="tableHeaderField">&nbsp;<spring:message code="systema.tror.orders.tt.logging.list.label.user"/>&nbsp;</td>
					        			
					               </tr> 
					               	 <c:forEach items="${model.trackAndTraceloggingList}" var="record" varStatus="counter">    
						               <c:choose>           
						                   <c:when test="${counter.count%2==0}">
						                       <tr class="tableRow" height="20" >
						                   </c:when>
						                   <c:otherwise> 
						                       <tr class="tableOddRow" height="20" >
						                   </c:otherwise>
						               </c:choose>
						               	<td class="tableCellFirst">&nbsp;${record.frBrev}</td>
						               	<td class="tableCell" >&nbsp;${record.date}</td>
						               	<td class="tableCell" >&nbsp;${record.time}</td>
						               	<td class="tableCell" >&nbsp;${record.event}</td>
						               	<td class="tableCell" >&nbsp;${record.textLoc}</td>
						               	<td class="tableCell" >&nbsp;${record.user}</td>
						               	<%-- DELETE cell --%>							           
						               	<td width="2%" class="tableCell" align="center">
						               	   <c:if test="${not empty record.date && not empty record.event}">
						                   		<a style="cursor:pointer;" id="avd_${model.avd}@opd_${model.opd}@date_${record.date}@time_${record.time}" onClick="doDeleteItemLine(this);" tabindex=-1 >
								               		<img valign="bottom" src="resources/images/delete.gif" border="0" alt="remove">
								               	</a>&nbsp;
								               	
							               	</c:if>
				               		  	</td> 
						            	</tr>
						            </c:forEach>
					               		
					               
									  <%--						               
				 					  <c:forEach items="${Xmodel.list}" var="record" varStatus="counter">    
							               <c:choose>           
							                   <c:when test="${counter.count%2==0}">
							                   	   <%--highlight cost lines 	
							                       <tr class="tableRow" height="20" >
							                   </c:when>
							                   <c:otherwise> 
							                       <tr class="tableOddRow" height="20" >
							                   </c:otherwise>
							               </c:choose>
							               <td align="center" width="4%" class="tableCellFirst" align="center">&nbsp;${record.fskode}</td>
							               <td align="center" width="2%" class="tableCell" >
							     				<a id="recordUpdate_${record.fskode}_${record.fssok}" href="#" onClick="getItemData(this);">
							     					<c:choose>
								     					<c:when test="${not empty record.fskode && not empty record.fssok}">
						               						<img title="Update" style="vertical-align:bottom;" src="resources/images/update.gif" border="0" alt="update">&nbsp;
						               					</c:when>
						               					<c:otherwise>
						               						<img title="Update" style="vertical-align:bottom;" src="resources/images/redFlag.png" width="15" height="17" border="0" alt="update">&nbsp;
						               					</c:otherwise>
					               					</c:choose>
					               				</a>
						               	   </td>
							               <td class="tableCell" >&nbsp;${record.fssok}</td>
							               <td width="4%" class="tableCell" >&nbsp;${record.krav}</td>
							               <td class="tableCell" >&nbsp;${record.fsdokk}</td>
							               <%-- DELETE cell 							           
							               <td width="2%" class="tableCell" align="center">
							               	   <c:if test="${not empty record.fskode && not empty record.fssok}">
							                   		<a style="cursor:pointer;" id="avd_${record.fsavd}@opd_${record.fsopd}@kode_${record.fskode}@sok_${record.fssok}" onClick="doDeleteItemLine(this);" tabindex=-1 >
									               		<img valign="bottom" src="resources/images/delete.gif" border="0" alt="remove">
									               	</a>&nbsp;
									               	
								               	</c:if>
					               		  </td> 
							            </tr>
								        <%-- this param is used ONLY in this JSP 
								        <c:set var="totalNumberOfItemLines" value="${counter.count}" scope="request" />
								         
								        <%-- this param is used throughout the Controller 
								        <c:set var="numberOfItemLinesInTopic" value="${Xrecord.svln}" scope="request" /> 
								        </c:forEach>
						            --%>
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
						 		<table  class="tableBorderWithRoundCornersGray" width="90%" border="0" cellspacing="0" cellpadding="0">
						 			<tr height="5"><td class="text" align="left"></td></tr>
						 			<tr >
						 				
						            	<td class="text12" align="left">&nbsp;<font class="text14RedBold" >*</font><span title="fskode">&nbsp;Kode</span>
							            	<a tabindex=-1 id="fskodeIdLink">
	 											<img id="imgFrisokveiCodesSearch" align="bottom" style="cursor:pointer;" src="resources/images/find.png" height="13px" width="13px" border="0" alt="search">
	 										</a>
							            </td>
							            <td class="text12" align="left">&nbsp;<font class="text14RedBold" >*</font><span title="fssok">&nbsp;Søketekst</span></td>
					            		<td class="text12" align="left">&nbsp;<span title="fsdokk">&nbsp;Dok.kode</span>
					            			<a tabindex=-1 id="fsdokkIdLink">
	 											<img id="imgFrisokveiDocCodesSearch" align="bottom" style="cursor:pointer;" src="resources/images/find.png" height="13px" width="13px" border="0" alt="search">
	 										</a>
					            		</td>
					            		
							        </tr>
							        <tr>
						        		<td class="text12" align="left" >&nbsp;<input type="text" class="inputTextMediumBlueMandatoryField" name="fskode" id="fskode" size="4" maxlength="3" value="${model.record.fskode}"></td>
							            <td class="text12" align="left" >
						        			&nbsp;<input type="text" class="inputTextMediumBlueMandatoryField" name="fssok" id="fssok" size="36" maxlength="35" value="${model.record.fssok}">
						        		</td>
						        		<td class="text12" align="left" >&nbsp;<input type="text" class="inputTextMediumBlue" name="fsdokk" id="fsdokk" size="11" maxlength="10" value="${model.record.fsdokk}"></td>
							            
							        </tr>
							        
							        <tr height="8"><td class="text" align="left"></td></tr>
						        </table>
					        </td>
				        </tr>
					    <tr height="10"><td colspan="2" ></td></tr>
					    <tr>	
						    <td align="left" colspan="5">
								<input class="inputFormSubmit" type="submit" name="submit" id="submit" value='<spring:message code="systema.tror.submit.save"/>'>
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

