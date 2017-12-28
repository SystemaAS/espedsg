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
	<SCRIPT type="text/javascript" src="resources/js/tror_mainorderland_ttrace_general.js?ver=${user.versionEspedsg}"></SCRIPT>
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
				&nbsp;&nbsp;&nbsp;<font class="text16MediumBlue">Avd/Opd&nbsp;&nbsp;<b>${model.record.ttavd}</b>/<b>${model.record.ttopd}</b></font>	
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
							
								<table width="80%" cellspacing="0" border="0" cellpadding="0">
									<tr>
										<td class="text12">
											<b>&nbsp;Track/Trace, Hendelseslogg &nbsp;&nbsp;</b>
					            		</td>
									</tr>
									<tr height="2"><td></td></tr>
								</table>
						
							</td>
						</tr> 
						<tr>
							<td class="text11" >
								<table id="tblMain" class="display compact cell-border" >
									<thead>
									<tr style="background-color:#DDDDDD">
										<th align="center" width="2%" class="text12">&nbsp;Endre&nbsp;</th>
					        			<th align="center" width="2%" class="text12">&nbsp;<spring:message code="systema.tror.orders.tt.logging.list.label.avd"/>&nbsp;</th>
					        			<th align="center" width="2%" class="text12">&nbsp;<spring:message code="systema.tror.orders.tt.logging.list.label.opd"/>&nbsp;</th>
					        			<th align="center" width="2%" class="text12">&nbsp;<spring:message code="systema.tror.orders.tt.logging.list.label.fbrev"/>&nbsp;</th>
					        			<th class="text12">&nbsp;<spring:message code="systema.tror.orders.tt.logging.list.label.date"/>&nbsp;</th>
					                    <th align="center" width="5%" class="text12">&nbsp;<spring:message code="systema.tror.orders.tt.logging.list.label.time"/>&nbsp;</th>   
					                    <th align="center" width="5%" class="text12">&nbsp;<spring:message code="systema.tror.orders.tt.logging.list.label.kode"/>&nbsp;</th>
					                    <th class="text12">&nbsp;<spring:message code="systema.tror.orders.tt.logging.list.label.text"/>&nbsp;</th>
					                    <th class="text12">&nbsp;<spring:message code="systema.tror.orders.tt.logging.list.label.status"/>&nbsp;</th>
					                    <th align="center" width="5%" class="text12">&nbsp;<spring:message code="systema.tror.orders.tt.logging.list.label.user"/>&nbsp;</th>
					        			<th align="center" width="5%" class="text12">&nbsp;<spring:message code="systema.tror.orders.tt.logging.list.label.delete"/>&nbsp;</th>
					        			
					               </tr> 
					               </thead>
					               <tbody>
					               	 <c:forEach items="${model.list}" var="record" varStatus="counter">    
						               <tr class="tableRow" height="20" >
						                <td align="center" class="text11" >
						               		<c:if test="${not empty record.ttdate && not empty record.tttime}">
						               	   		<c:if test="${record.ttmanu == 'J'}">
								               	<a style="cursor:pointer;" id="recordUpdate_avd_${record.ttavd}@opd_${record.ttopd}@date_${record.ttdate}@time_${record.tttime}" onClick="getItemData(this);">
						               				<img title="Update" style="vertical-align:bottom;" src="resources/images/update.gif" border="0" alt="update">&nbsp;
						               			</a>
						               			</c:if>
					               			</c:if>
							            </td>   	
						                <td class="text11">
						               		<c:if test="${record.ttavd > 0}">
						               		${record.ttavd}
						               		</c:if>
						               	</td>
						               	<td class="text11">
						               		<c:if test="${record.ttopd > 0}">
						               		${record.ttopd}
						               		</c:if>
						               	</td>   
						               	<td class="text11">
						               		<c:if test="${record.ttfbnr > 0}">
						               		${record.ttfbnr}
						               		</c:if>
						               	</td>
						               			
						               	<td class="text11" >${record.ttdate}</td>
						               	<td align="right" class="text11" >${record.tttime}</td>
						               	<td class="text11" >${record.ttacti}</td>
						               	<td class="text11" >${record.tttexl}</td>
						               	<td align="center" class="text11" >${record.ttmanu}</td>
						               	<td class="text11" >${record.ttuser}</td>
						               	<%-- DELETE cell --%>							           
						               	<td width="2%" class="text11" align="center">
						               	   <c:if test="${not empty record.ttdate && not empty record.tttime}">
						               	   		<c:if test="${record.ttmanu == 'J'}">
						                   		<a style="cursor:pointer;" id="avd_${record.ttavd}@opd_${record.ttopd}@date_${record.ttdate}@time_${record.tttime}" onClick="doDeleteItemLine(this);" tabindex=-1 >
								               		<img valign="bottom" src="resources/images/delete.gif" border="0" alt="remove">
								               	</a>&nbsp;
								               	</c:if>
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
					<form name="createNewLineForm" id="createNewLineForm" method="post" action="tror_mainorderland_ttrace_general.do">
						<input type="hidden" name="ttavd" id="ttavd" value='${model.record.ttavd}'>
						<input type="hidden" name="ttopd" id="ttopd" value='${model.record.ttopd}'>
						<input tabindex=-1 class="inputFormSubmitStd" type="submit" name="submit" id="submit" value='Lage ny'>
					</form>
				</td>
			</tr>
			<tr height="5"><td class="text12" align="left" ></td></tr>
           	<tr>
	 			<td >
	 				<form action="tror_mainorderland_ttrace_general_edit.do" name="trorUpdateTracktForm" id="trorUpdateTracktForm" method="post">
				 	<%--Required key parameters from the Topic parent --%>
				 	<input type="hidden" name="applicationUser" id="applicationUser" value='${user.user}'>
				 	<input type="hidden" name="action" id="action" value='doUpdate'/>
					<input type="hidden" name="ttavd" id="ttavd" value='${model.record.ttavd}'>
					<input type="hidden" name="ttopd" id="ttopd" value='${model.record.ttopd}'>
					<input type="hidden" name="updateId" id="updateId" value="${model.updateId}">
					
				 	<%-- <input type="hidden" name="numberOfItemLinesInTopic" id="numberOfItemLinesInTopic" value="${numberOfItemLinesInTopic}" /> --%>
				 	
				 	<%-- Topic ITEM CREATE --%>
	 				<table width="100%" align="left" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
	 					
				 		<tr height="15">
				 			<td class="text12White" align="left" >
				 				<b>&nbsp;&nbsp;Varelinje&nbsp;</b>
 								<img onClick="showPop('updateInfo');" src="resources/images/update.gif" border="0" alt="edit">&nbsp;&nbsp;<font id="editLineNr"></font>
			 				</td>
		 				</tr>
	 				</table>
					<table width="100%" align="left" class="formFrame" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="12"><td class="text" align="left"></td></tr>
				 		<tr>
					 		<td>
						 		<table  class="tableBorderWithRoundCornersGray" width="95%" border="0" cellspacing="0" cellpadding="0">
						 			<tr height="5"><td class="text" align="left"></td></tr>
						 			<tr >
						 				
						            	<td class="text12" align="left"><span title="ttfbnr">&nbsp;<font class="text14RedBold" >*</font>Fraktbrevnr.</span></td>
							            <td class="text12" align="left">
							            	<img onMouseOver="showPop('ttacti_info');" onMouseOut="hidePop('ttacti_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					            			<span title="ttacti"><font class="text14RedBold" >*</font>Kode</span>
											<div class="text11" style="position: relative;" align="left">
												<span style="position:absolute; left:25px; top:-400px; width:250px" id="ttacti_info" class="popupWithInputText"  >
													<font class="text11">
								           			<b>Kode</b>
								           			<ul>
								           				<c:forEach var="record" items="${model.genericList}" varStatus="counter">
								           					<li><font class="text10"><b>${record.kfkod}</b>&nbsp;${record.kftxt}</font></li>
								           				</c:forEach>
								           			</ul>
							           			</font>
												</span>
											</div>	
							            </td>
					            		<td width="5%" class="text12" align="left"><span title="ttdate/tttime"><font class="text14RedBold" >*</font>Hendelsestidspunkt</span></td>
					            		<td class="text12" align="left"><span title="ttmanu">&nbsp;&nbsp;Status</span></td>
					            		<td class="text12" align="left">
					            			<img onMouseOver="showPop('ttedev_info');" onMouseOut="hidePop('ttedev_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					            			<span title="ttedev">Event code</span>
											<div class="text11" style="position: relative;" align="left">
												<span style="position:absolute; left:25px; top:-400px; width:250px" id="ttedev_info" class="popupWithInputText"  >
													<font class="text11">
								           			<b>Event code</b>
								           			<ul>
								           				<c:forEach var="record" items="${model.genericList}" varStatus="counter">
								           					<li><font class="text10"><b>${record.kfkod}</b>&nbsp;${record.kftxt}</font></li>
								           				</c:forEach>
								           			</ul>
							           			</font>
												</span>
											</div>	
				            			</td>
					            		<td class="text12" align="left">
					            			<img onMouseOver="showPop('ttedre_info');" onMouseOut="hidePop('ttedre_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					            			<span title="ttedre">Reason code</span>
											<div class="text11" style="position: relative;" align="left">
												<span style="position:absolute; left:25px; top:-400px; width:250px" id="ttedre_info" class="popupWithInputText"  >
													<font class="text11">
								           			<b>Reason code</b>
								           			<ul>
								           				<c:forEach var="record" items="${model.genericList}" varStatus="counter">
								           					<li><font class="text10"><b>${record.kfkod}</b>&nbsp;${record.kftxt}</font></li>
								           				</c:forEach>
								           			</ul>
							           			</font>
												</span>
					            		</td>
							        </tr>
							        <tr>
						        		<td class="text12" align="left" >&nbsp;<input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlueMandatoryField" name="ttfbnr" id="ttfbnr" size="4" maxlength="3" value="<c:if test="${model.record.ttfbnr > 0}">${model.record.ttfbnr}</c:if>"/></td>
							            <td class="text12" align="left" >
							            	<select required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="ttacti" id="ttacti" >
							 				  <option value="">-velg-</option>
											  <c:forEach var="record" items="${model.genericList}" >
							 				  		<option value="${record.kfkod}"<c:if test="${model.record.ttacti == record.kfkod}"> selected </c:if> >${record.kfkod}</option>
											  </c:forEach> 
											</select>							            	
							            </td>
							            <td nowrap width="5%" class="text12" align="left"><input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlueMandatoryField"  name="ttdate" id="ttdate" size="10" maxlength="8" value="<c:if test="${model.record.ttdate > 0}">${model.record.ttdate}</c:if>"/>
							 			&nbsp;Kl:<input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlueMandatoryField"  name="tttime" id="tttime" size="7" maxlength="6" value="<c:if test="${model.record.tttime > 0}">${model.record.tttime}</c:if>"/></td>	
							            	
							            <td class="text12" align="left" >
							            	<select class="inputTextMediumBlue" name="ttmanu" id="ttmanu" >
							 				  <%--
											  <option value="S"<c:if test="${model.record.ttmanu == 'S'}"> selected </c:if> >Send</option>
											  <option value="F"<c:if test="${model.record.ttmanu == 'F'}"> selected </c:if> >Ferdig</option>
											   --%>
											  <option value="J">Manuelt</option>
											</select>
							            </td>	
							            <td class="text12" align="left" >
							            	<select class="inputTextMediumBlue" name="ttedev" id="ttedev" >
							 				  <option value="">-velg-</option>
											  <c:forEach var="record" items="${model.genericList}" >
							 				  		<option value="${record.kfkod}"<c:if test="${model.record.ttedev == record.kfkod}"> selected </c:if> >${record.kfkod}</option>
											  </c:forEach> 
											</select>
							            </td>	
							            <td class="text12" align="left" >
							            	<select class="inputTextMediumBlue" name="ttedre" id="ttedre" >
							 				  <option value="">-velg-</option>
											  <c:forEach var="record" items="${model.genericList}" >
							 				  		<option value="${record.kfkod}"<c:if test="${model.record.ttedre == record.kfkod}"> selected </c:if> >${record.kfkod}</option>
											  </c:forEach>
											</select>
							            </td>							            
							        </tr>
							        <tr height="5"><td class="text" align="left"></td></tr>
							        <tr >
						 				<td class="text12" align="left"><span title="tttexl">&nbsp;NO tekst</span></td>
						 				<td colspan="10" class="text12" align="left"><input type="text" class="inputTextMediumBlue"  name="tttexl" id="tttexl" size="75" maxlength="71" value='${model.record.tttexl}'></td>
							        </tr>
							        <tr >
						 				<td class="text12" align="left"><span title="tttext">&nbsp;EN tekst</span></td>
						 				<td colspan="10" class="text12" align="left"><input type="text" class="inputTextMediumBlue"  name="tttext" id="tttext" size="75" maxlength="71" value='${model.record.tttext}'></td>
							        </tr>
							        <tr height="5"><td class="text" align="left"></td></tr>
							        <tr >
						 				<td class="text12" align="left"><span title="ttdepo">&nbsp;Depot/term</span></td>
						 				<td colspan="10" class="text12" align="left"><input type="text" class="inputTextMediumBlue"  name="ttdepo" id="ttdepo" size="12" maxlength="10" value='${model.record.ttdepo}'></td>
							        </tr>
							        <tr >
						 				<td class="text12" align="left"><span title="ttname">&nbsp;Name</span></td>
						 				<td colspan="10" class="text12" align="left"><input type="text" class="inputTextMediumBlue"  name="ttname" id="ttname" size="12" maxlength="10" value='${model.record.ttname}'></td>
							        </tr>
							        <tr height="10"><td class="text" align="left"></td></tr>
							              
							         <tr >
						 				<td class="text12" align="left"><span title="ttdatl/tttiml">&nbsp;Loggføringstid</span></td>
						 				<td colspan="2" class="text12" align="left"><input readonly type="text" class="inputTextReadOnly"  name="ttdatl" id="ttdatl" size="10" maxlength="8" value='${model.record.ttdatl}'>
							 				&nbsp;Kl:<input readonly type="text" class="inputTextReadOnly"  name="tttiml" id="tttiml" size="7" maxlength="6" value='${model.record.tttiml}'>
							 			</td>
							 			<td class="text12" align="left"><span title="ttuser">&nbsp;Av bruker ID</span></td>
						 				<td class="text12" align="left">
						 					<c:choose>
						 					<c:when test="${not empty model.record.ttuser}">
						 						<input readonly type="text" class="inputTextReadOnly"  name="ttuser" id="ttuser" size="8" maxlength="10" value='${model.record.ttuser}'>
						 					</c:when>
						 					<c:otherwise>
						 						<input readonly type="text" class="inputTextReadOnly"  name="ttuser" id="ttuser" size="8" maxlength="10" value='${user.user}'>
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

