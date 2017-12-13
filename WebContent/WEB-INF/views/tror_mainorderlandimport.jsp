<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>
<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTror.jsp" />
<!-- =====================end header ==========================-->
	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/trorglobal_edit.js?ver=${user.versionEspedsg}"></SCRIPT>	
	<SCRIPT type="text/javascript" src="resources/js/tror_mainorderlandimport.js?ver=${user.versionEspedsg}"></SCRIPT>
	<SCRIPT type="text/javascript" src="resources/js/trorFkeys_landimport.js?ver=${user.versionEspedsg}"></SCRIPT>
	
	<%-- for dialog popup --%>
	<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
	
	<style type = "text/css">
	.ui-dialog{font-size:10pt;}
	.ui-datepicker { font-size:9pt;}
	</style>
	


<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
	<tr>
	<td>
	<%-- tab container component --%>
	<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
		<tr height="2"><td></td></tr>
		<tr height="25"> 
			<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a class="text14" onClick="setBlockUI(this);" href="tror_mainorderlist.do?action=doFind" > 	
					<img style="vertical-align:middle;" src="resources/images/bulletGreen.png" width="6px" height="6px" border="0" alt="open orders">
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tror.orderlist.tab"/></font>&nbsp;<font class="text10Orange">F2</font>
				</a>
			</td>
			<c:choose>
				<c:when test="${empty model.record.heopd}">
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="12%" valign="bottom" class="tab" align="center" nowrap>
						<img style="vertical-align:middle;" src="resources/images/lorry_green.png" width="18px" height="18px" border="0" alt="create new">
						<font class="tabLink"><spring:message code="systema.tror.createnew.order.tab"/></font>
					</td>
				</c:when>
				<c:otherwise>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="12%" valign="bottom" class="tab" align="center" nowrap>
						<img style="vertical-align:middle;" src="resources/images/lorry_green.png" width="18px" height="18px" border="0" alt="update">
						<font class="tabLink"><spring:message code="systema.tror.order.tab"/></font><font class="text12">&nbsp;${model.record.heavd}/${model.record.heopd}</font>&nbsp;<font class="text10Orange">F4</font>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a class="text14" onClick="setBlockUI(this);" href="tror_mainorderlandimport_invoice.do?action=doFetch&heavd=${model.record.heavd}&heopd=${model.record.heopd}" > 	
							<img style="vertical-align: bottom" src="resources/images/invoice.png" width="16" hight="16" border="0" alt="show invoice">
							<font class="tabDisabledLink"><spring:message code="systema.tror.order.faktura.tab"/></font>&nbsp;<font class="text10Orange">F10</font>
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a class="text14" onClick="setBlockUI(this);" href="editNotisblock.do?action=doFetch&subsys=tror_li&avd=${model.record.heavd}&opd=${model.record.heopd}&sign=${model.record.hesg}" > 	
							<img style="vertical-align: bottom" src="resources/images/veiledning.png" width="16" hight="16" border="0" alt="show messages">
							<font class="tabDisabledLink"><spring:message code="systema.tror.order.notisblock.tab"/></font>&nbsp;<font class="text10Orange">F9</font>
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a class="text14" onClick="setBlockUI(this);" href="tror_mainorderlandimport_logging.do?avd=${model.record.heavd}&sign=${model.record.hesg}&opd=${model.record.heopd}">
							<img style="vertical-align: bottom" src="resources/images/log-icon.png" width="16" hight="16" border="0" alt="show log">
							<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tror.order.logging.tab"/></font>
						</a>
					</td>
					
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a class="text14" onClick="setBlockUI(this);" href="tror_mainorderlandimport_archive.do?avd=${model.record.heavd}&sign=${model.record.hesg}&opd=${model.record.heopd}">
							<img style="vertical-align: bottom" src="resources/images/archive.png" width="16" hight="16" border="0" alt="show archive">
							<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tror.order.archive.tab"/></font>
						</a>
					</td>
					<c:if test="${model.record.hepk1 == 'J' || model.record.hepk1 == 'P'}">
						<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
						<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
							<a class="text14" onClick="setBlockUI(this);" href="tror_mainorderlandimport_freightbill_edit.do?dfavd=${model.record.heavd}&sign=${model.record.hesg}&dfopd=${model.record.heopd}">
								<img style="vertical-align: bottom" src="resources/images/fraktbrev.png" width="16" hight="16" border="0" alt="show freight doc">
								<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tror.order.fraktbrev.tab"/></font>&nbsp;<font class="text10Orange">F3</font>
							</a>
						</td>
					</c:if>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a class="text14" onClick="setBlockUI(this);" href="tror_mainorderlandimport_more.do?avd=${model.record.heavd}&sign=${model.record.hesg}&opd=${model.record.heopd}">
							<img style="vertical-align: bottom" src="resources/images/sort_down.png" width="10" hight="10" border="0" alt="show more">
							<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tror.order.more.tab"/></font>&nbsp;<font class="text10Orange">F24</font>
						</a>
					</td>
					
				</c:otherwise>
			</c:choose>
			<td width="80%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>	
		</tr>
	</table>
	</td>
	</tr>
	
	<%-- --------------------------- --%>
	<%-- Validation errors FRONT END --%>
	<%-- --------------------------- --%>
	<spring:hasBindErrors name="record"> <%-- name must equal the command object name in the Controller --%>
	<tr height="5"><td></td></tr>
	<tr>
		<td>
           	<table class="tabThinBorderWhiteWithSideBorders" width="100%" align="left" border="0" cellspacing="0" cellpadding="0">
           	<tr>
			<td valign="bottom" class="textError">					
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
	<%-- Validation errors BACK END --%>
	<%-- -------------------------- --%>
	<c:if test="${not empty model.containerValidationBackend && not empty model.containerValidationBackend.errMsgListFromValidationBackend}">
		<tr>
		<td>
           	<table class="tabThinBorderWhiteWithSideBorders" width="100%" align="left" border="0" cellspacing="0" cellpadding="0">
           	<tr>
			<td valign="bottom" class="textError">					
	            <ul>
	            <c:forEach var="errMsg" items="${model.containerValidationBackend.errMsgListFromValidationBackend}">
	                <li >${errMsg}</li>
	            </c:forEach>
	            </ul>
			</td>
			</tr>
			</table>
		</td>
		</tr>		
	</c:if>
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
	
	
	<%-- ------------------------------- --%>
	<%-- Floating window for file upload --%>
	<%-- ------------------------------- --%>
		<c:if test="${not empty model.record.heopd}">
			<tr>
				<td valign="bottom" >
					<span style="position:absolute; left:1360px; top:160px; width:550px; height:800px;" id="economyMatrixInfo" class="popupFloating"  >
		           		<div class="text10" align="left">
		           		<table align="left" class="popupFloatingWithRoundCorners3D">
						    <tr height="2"><td></td></tr>
					    	<tr>
							<td valign="top">
							<form name="uploadFileForm" id="uploadFileForm" method="post" enctype="multipart/form-data">
								<input type="hidden" name="applicationUserUpload" id="applicationUserUpload" value='${user.user}'>
								<input type="hidden" name="wsavd" id="wsavd" value='${model.record.heavd}'>
								<input type="hidden" name="wsopd" id="wsopd" value='${model.record.heopd}'>
								<input type="hidden" name="userDate" id="userDate" value=''>
								<input type="hidden" name="userTime" id="userTime" value=''>
								<input type="hidden" name="callerModule" id="callerModule" value='trorLandimport'>
								
									<table id="containerdatatableTable" cellspacing="2" align="left">
										<tr>
											<td colspan="3" class="text12Bold">&nbsp;
												<img style="vertical-align:bottom;" src="resources/images/upload.png" border="0" width="20" height="20" alt="upload">
												&nbsp;File Upload&nbsp;							
											</td>
										</tr>
										<tr>
										<tr height="5"><td></td></tr>
										<tr>
										<td>
											<table>
											<%--
											<tr>
												<td class="text11">&nbsp;Nytt filnavn:</td>
												<td class="text11">&nbsp;<input tabindex=-1 type="text" class="inputText" name="fileNameNew" id="fileNameNew" size="20" maxlength="20" value=""></td>
											</tr>
											 --%>
											<tr>
												<td class="text11">&nbsp;Arkiv typen:</td>
												<td class="text11">&nbsp;
													<select tabindex=-1 name="wstype" id="wstype">
														<c:forEach var="record" items="${user.arkivKodOpdList}" >
								                       	 	<option value="${record.arkKod}">${record.arkKod}-${record.arkTxt}</option>
														</c:forEach> 
													</select>	
												</td>
											</tr>
											<tr>	
												<td class="text11">&nbsp;Fil:</td>
												<td class="text11">
					           						&nbsp;<input ondragenter="myFileUploadDragEnter(event)" ondragleave="myFileUploadDragLeave(event)" tabindex=-1 class="tableBorderWithRoundCornersLightYellow3D noFileChosenTransparent" style="width:300px;height:200px;display:block;" type="file" name="file" id="file" />
					       						</td>
							           		</tr>
							           		</table>
										</td>
										</tr>
										<tr height="5"><td></td></tr>
					       			</table>
							</form>	
							</td>
							</tr>
						</table>
					  </div>
					</span>  		
				</td>
			</tr>
		</c:if>
		
	

		<tr>
		<td>
			<form action="tror_mainorderlandimport.do" name="trorOrderForm" id="trorOrderForm" method="post">
			<%-- this table wrapper is necessary to apply the css class with the thin border --%>
			<table style="width:100%" id="wrapperTable" class="tabThinBorderWhite" cellspacing="0">
			<tr height="10"><td>&nbsp;</td></tr> 
			<%-- FORM HEADER --%>
	 		<tr>
           		<td>
           			
        			<table class="formFrameHeader" width="98%" align="left" border="0" cellspacing="0" cellpadding="0">
			 		<tr height="15">
			 			<c:choose>
				 			<c:when test="${not empty model.record.heopd}">
					 			<td align="left" class="text14White">
									&nbsp;<img style="vertical-align:bottom;" src="resources/images/update.gif" border="0" alt="edit">	
									&nbsp;<spring:message code="systema.tror.orders.form.update.label.header.edit"/>
									&nbsp;&nbsp;<font style="color: yellow"><b>${model.record.heavd} / ${model.record.heopd} / ${model.record.hesg}</b></font>
				 				</td>
			 				</c:when>
			 				<c:otherwise>
								<td align="left" class="text14White">
									&nbsp;<spring:message code="systema.tror.orders.form.update.label.header.add"/>
				 				</td>	
			 				</c:otherwise>
		 				</c:choose>
		 				<td align="right" class="text12White" width="50%">
		 					<img style="vertical-align: middle;" onMouseOver="showPop('status_info');" onMouseOut="hidePop('status_info');" width="9px" height="9px" src="resources/images/info3.png" border="0" alt="info">
							STATUS
							<img id="updateStatusImg" style="vertical-align: middle; cursor:pointer;" src="resources/images/changeStatus.png" width="11px" height="11px" border="0" alt="change status">
		 					&nbsp;&nbsp;<font style="color: orangered"><b>${model.record.hest}</b></font>&nbsp;&nbsp;
		 					<div class="text11" style="position: relative; display: inline;" align="left" >
				 				<span style="position:absolute;left:-40px; top:5px; width:400px;" id="status_info" class="popupWithInputText text11"  >
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
		 					
		 					
		 				</td>
	 				</tr>
 					</table>
           		</td>
            </tr>
            <%-- FORM DETAIL --%>
            <tr ondrop="drop(event)" ondragover="allowDrop(event)" >
            		<td>
            		<input type="hidden" name="applicationUser" id="applicationUser" value='${user.user}'>
					<input type="hidden" name="action" id="action" value='doUpdate'>
					<input type="hidden" name="selectedType" id="selectedType" value='${model.selectedType}'>
					<input type="hidden" name="heur" id="heur" value='${model.record.heur}'> <%--delsystem --%>
					
					<c:if test="${not empty model.record.heopd}">
						<input type="hidden" name="heopd" id="heopd" value='${model.record.heopd}'>
						<input type="hidden" name="heavd" id="heavd" value='${model.record.heavd}'>
						<input type="hidden" name="hesg" id="hesg" value='${model.record.hesg}'> <%--sign --%>
						<input type="hidden" name="hest" id="hest" value='${model.record.hest}'> <%--status --%>
					</c:if>
					<%-- ORIGINAL
					<input type="hidden" name="messageNoteConsigneeOriginal" id="messageNoteConsigneeOriginal" value='${Xmodel.record.messageNoteConsigneeOriginal}'>
					<input type="hidden" name="messageNoteCarrierOriginal" id="messageNoteCarrierOriginal" value='${Xmodel.record.messageNoteCarrierOriginal}'>
					<input type="hidden" name="messageNoteInternalOriginal" id="messageNoteInternalOriginal" value='${Xmodel.record.messageNoteInternalOriginal}'>
					 --%>
					<table class="formFrame" width="98%" align="left" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="5"><td ></td></tr>
				 		<tr>
							<td colspan="8">
								<c:choose>
									<c:when test="${empty model.record.heopd}">
										<table class="tableBorderWithRoundCornersLightGray" border="0">
							 			<tr>
							 				<td width="5px" class="text14" >&nbsp;</td>
							 				<td class="text14"><spring:message code="systema.tror.orders.form.update.label.delsystem"/></td>
							 				<td class="text14" ><b><spring:message code="systema.tror.order.suborder.landimport"/></b></td>
							 				<td class="text14" width="15px" >&nbsp;</td>
							 				<td class="text14" >
							 					&nbsp;<font class="text16RedBold" >*</font><span title="heavd"><spring:message code="systema.tror.orders.form.update.label.avdelning"/></span>
							 				</td>
							 				<td class="text14">
												<select autofocus name="heavd" id="heavd" required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" >
							 						<option value="">-velg-</option>
								 				  	<c:forEach var="record" items="${model.avdList}" >
								 				  		<option value="${record.koakon}"<c:if test="${model.record.heavd == record.koakon}"> selected </c:if> >${record.koakon}</option>
													</c:forEach>  
												</select>

												&nbsp;&nbsp;<font class="text16RedBold" >*</font><span title="hesg"><spring:message code="systema.tror.orders.form.update.label.signature"/></span>
							 					<select name="hesg" id="hesg" required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" >
							 						<option value="">-velg-</option>
								 				  	<c:forEach var="record" items="${model.signatureList}" >
								 				  		<option value="${record.kosfsi}"<c:if test="${model.record.hesg == record.kosfsi}"> selected </c:if> >${record.kosfsi}</option>
													</c:forEach>  
												</select>							 					
							 				</td>
							 				<td width="5px" class="text14" >&nbsp;</td>
							 			</tr>
							 			</table>
						 			</c:when>
						 			<c:otherwise>
						 				<table width="100%" border="0">
									 	<tr>
									 		<td valign="top" align="left" width="50%">
									 		<table border="0">
										 		<tr>
										 			<td>
										 			<table class="tableBorderWithRoundCornersLightGray" border="0">
										 			<tr>
											 		<td width="5px" class="text14" >&nbsp;</td>
											 		<td align="left" class="text14" style="vertical-align:bottom;" ><span title="heur"><spring:message code="systema.tror.orders.form.update.label.delsystem"/></span></td>
									 				<td class="text14" ><b><spring:message code="systema.tror.order.suborder.landimport"/></b></td>
									 				
									 				
									 				<td width="5px" class="text14" >&nbsp;</td>
									 				</tr>
									 				</table>
									 				</td>
									 				<td class="text14"><button name="frisokveiButton" id="frisokveiButton" class="buttonGrayWithGreenFrame" type="button" >Frie søkeveier</button></td>
								 				</tr>
							 				</table>
							 				</td>
							 				
							 				<td valign="top" align="left" width="50%">
							 				<table width="100%" border="0">
										 		<tr>
											 		<td valign="bottom"  width="5px" class="text14" >
											 			<button name="trackAndTraceButton" id="trackAndTraceButton" class="buttonGrayWithGreenFrame" type="button" ><spring:message code="systema.tror.orders.tt.logging.button.trackAndTrace"/></button> 
												    </td>
											 		<td>
											 			<button name="budgetButton" id="budgetButton" class="buttonGrayWithGreenFrame" type="button" >Budsjett/rekv.</button>
													</td>
												</tr>
											</table>	
							 				</td>
							 				
							 			</tr>
							 			
							 			</table>
						 			</c:otherwise>
					 			</c:choose>
						 			
					 			<table width="100%" border="0">
					 			<tr>
					 				<td valign="top" align="left" width="50%">
					 					<table width="100%" border="0">
					 					<tr>
					 						<td class="text12" title="hedtop"><font class="text16RedBold" >*</font><spring:message code="systema.tror.orders.form.update.label.date"/></td>
							 				<td class="text12" >	
							 					<input type="text" required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="hedtop" id="hedtop" size="9" maxlength="8" value="${model.record.hedtop}">
							 				</td>
							 				<td class="text12" title="hepro">
							 					<img onMouseOver="showPop('turnr_info');" onMouseOut="hidePop('turnr_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
							 					<spring:message code="systema.tror.orders.form.update.label.turnr"/>
							 					<div class="text11" style="position: relative;" align="left" >
									 				<span style="position:absolute; top:2px; width:200px;" id="turnr_info" class="popupWithInputText text11"  >
										           		<p><b>Turnr</b></p> 
									           			Turnr hentes automatisk fra teller ved tasting av F-9. Feltet er tilgjengelig slik at en allerede
														eksisterende tur kan tastes inn
													</span>	
												</div>
							 				
							 				</td>
							 				<td class="text12">
							 					<input <c:if test="${ not empty model.record.heopd }"> autofocus </c:if>  type="text" class="inputTextMediumBlue" name="hepro" id="hepro" size="9" maxlength="8" value="${model.record.hepro}">
							 				</td>
							 				<td class="text12" title="hepos1">
							 					<img onMouseOver="showPop('hepos_info');" onMouseOut="hidePop('hepos_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
							 					<spring:message code="systema.tror.orders.form.update.label.position"/>
							 					<input type="text" class="inputTextMediumBlue" name="hepos1" id="hepos1" size="8" maxlength="7" value="${model.record.hepos1}">
							 					&nbsp;<input type="text" class="inputTextMediumBlue" name="hepos2" id="hepos2" size="5" maxlength="4" value="${model.record.hepos2}">
							 					
							 					<div class="text11" style="position: relative;" align="left" >
									 				<span style="position:absolute; top:2px; width:200px;" id="hepos_info" class="popupWithInputText text11"  >
										           		<p><b><spring:message code="systema.tror.orders.form.update.label.position"/></b></p> 
									           			Hovedposisjon er 4 lang. Underposisjon er 4 lang. Overføres til rubrikk 49 på SAD-dokumentet
														( konnossement nr. ).
													</span>	
												</div>
							 					
							 				</td>
							 				
							 			</tr>
							 			<tr>	
							 				<td class="text12" title="hegn:${model.record.hegn}">
							 					<img onMouseOver="showPop('hegn_info');" onMouseOut="hidePop('hegn_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
							 					<spring:message code="systema.tror.orders.form.update.label.godsnr"/>
							 					<div class="text11" style="position: relative;" align="left" >
									 				<span style="position:absolute; top:2px; width:200px;" id="hegn_info" class="popupWithInputText text11"  >
										           		<p><b><spring:message code="systema.tror.orders.form.update.label.godsnr"/></b></p> 
									           			Her tastes forsendelsens godsnummer.
													</span>	
												</div>
							 				</td>
							 				
							 				<td colspan="3" class="text12">
							 					<input type="text" class="inputTextMediumBlue" name="ownHegn1" id="ownHegn1" size="5" maxlength="4" value="${model.record.ownHegn1}">
							 					<input type="text" class="inputTextMediumBlue" name="ownHegn2" id="ownHegn2" size="6" maxlength="5" value="${model.record.ownHegn2}">
							 					<input type="text" class="inputTextMediumBlue" name="ownHegn3" id="ownHegn3" size="7" maxlength="6" value="${model.record.ownHegn3}">
							 				</td>
							 				<td class="text12">
							 					<img onMouseOver="showPop('hesdl_info');" onMouseOut="hidePop('hesdl_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
							 					<img style="vertical-align:middle;" src="resources/images/loading.png" width="15px" height="15px" border="0" alt="load/unload">
				 								<span title="hesdl"><spring:message code="systema.tror.orders.form.update.label.unload"/></span>
				 								<a href="javascript:void(0);" onClick="window.open('tror_mainorder_childwindow_loadunloadplaces.do?action=doFind&ctype=tror_landimport','loadunloadWin','top=300px,left=50px,height=600px,width=800px,scrollbars=no,status=no,location=no')">						 				
						 							<img id="imgToSearch" align="bottom" style="cursor:pointer;" src="resources/images/find.png" height="13px" width="13px" border="0" alt="search">
						 						</a>
				 								<input type="text" class="inputTextMediumBlue" name="hesdl" id="hesdl" size="25" maxlength="20" value="${model.record.hesdl}">
				 								
				 								<div class="text11" style="position: relative;" align="left" >
									 				<span style="position:absolute; top:2px; width:200px;" id="hesdl_info" class="popupWithInputText text11"  >
										           		<p><b><spring:message code="systema.tror.orders.form.update.label.unload"/></b></p> 
									           			Lossested. Det som står i dette feltet skrives på ankomstmelding og losseliste.
														Det er ikke krav til feltet.
													</span>	
												</div>
											</td>
						 				</tr>
						 				
						 				</table>
					 				</td>
					 				
					 				<td valign="top" align="left" width="50%">
						 				<table width="100%" border="0">
						 				<tr>
						 					<td class="text12" title="hekna">
						 						<img onMouseOver="showPop('hekna_info');" onMouseOut="hidePop('hekna_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
						 						<spring:message code="systema.tror.orders.form.update.label.agent"/>
						 						<a tabindex="-1" id="trorAgentIdLink" >
		 											<img align="bottom" style="cursor:pointer;" src="resources/images/find.png" height="13px" width="13px" border="0" alt="search">
		 										</a>
		 										<div class="text11" style="position: relative;" align="left" >
									 				<span style="position:absolute; top:2px; width:200px;" id="hekna_info" class="popupWithInputText text11"  >
										           		<p><b><spring:message code="systema.tror.orders.form.update.label.agent"/></b></p> 
									           			Tast agents kundenr eller søk i kunderegister.
													</span>	
												</div>
		 										
		 									</td>
		 									<td class="text12" >	
						 						<input type="text" class="inputTextMediumBlue" name="hekna" id="hekna" size="9" maxlength="8" value="${model.record.hekna}">
						 					</td>
						 					<td class="text12" title="henaa">	
						 						<input type="text" class="inputTextReadOnly" name="henaa" id="henaa" size="20" maxlength="30" value="${model.record.henaa}">
							 				</td>
									 		<td class="text12" title="herfa">
									 			<img onMouseOver="showPop('herfa_info');" onMouseOut="hidePop('herfa_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
									 			<spring:message code="systema.tror.orders.form.update.label.agentRef"/>&nbsp;
									 			<input type="text" class="inputTextMediumBlue" name="herfa" id="herfa" size="15" maxlength="14" value="${model.record.herfa}">
									 			<div class="text11" style="position: relative;" align="left" >
									 				<span style="position:absolute; top:2px; width:200px;" id="herfa_info" class="popupWithInputText text11"  >
										           		<p><b><spring:message code="systema.tror.orders.form.update.label.agentRef"/></b></p> 
									           			Agents referanse kan tastes her. Feltet kan ellers benyttes til hva man måtte ønske.
													</span>	
												</div>
									 		</td>
							 				
							 			</tr>
							 			<tr>
							 				<td class="text12" title="heknt">
							 					<img onMouseOver="showPop('heknt_info');" onMouseOut="hidePop('heknt_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
							 					<spring:message code="systema.tror.orders.form.update.label.transport"/>
							 					<a tabindex="-1" id="trorCarrierIdLink" >
		 											<img align="bottom" style="cursor:pointer;" src="resources/images/find.png" height="13px" width="13px" border="0" alt="search">
		 										</a>
		 										<div class="text11" style="position: relative;"" align="left" >
									 				<span style="position:absolute; top:2px; width:200px;" id="heknt_info" class="popupWithInputText text11"  >
										           		<p><b><spring:message code="systema.tror.orders.form.update.label.transport"/></b></p> 
									           			Tast transportørs kundenr eller søk i kunderegister.
													</span>	
												</div>
		 									</td>
		 									<td class="text12" >	
							 					<input type="text" class="inputTextMediumBlue" name="heknt" id="heknt" size="9" maxlength="8" value="${model.record.heknt}">
							 				</td>
							 				<td class="text12" title="ownheknt">	
							 					<input type="text" class="inputTextReadOnly" name="ownheknt" id="ownheknt" size="30" maxlength="30" value="${Xmodel.record.ownTODO}">
							 				</td>
							 				<td>&nbsp;&nbsp;</td>
							 			</tr>
							 			
							 			</table>
						 			</td>
					 			</tr>
					 			
					 			<tr>
					 				<td align="left" width="50%">&nbsp;</td>
								 	
								 	<td align="left" width="50%">
						 				<table width="98%" class="tableBorderWithRoundCornersLightGray" border="0">
						 				<tr>		
								 			<td class="text12">
								 				<img onMouseOver="showPop('hekdtm_info');" onMouseOut="hidePop('hekdtm_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
							 					<span title="hekdtm"><spring:message code="systema.tror.orders.form.update.label.transporttype"/></span>
								 				<a href="javascript:void(0);" onClick="window.open('tror_mainorder_childwindow_transporttypes.do?action=doFind&ctype=tror_landimport','customerWin','top=300px,left=150px,height=600px,width=800px,scrollbars=no,status=no,location=no')">
		 											<img id="imgTransporttype" align="bottom" style="cursor:pointer;" src="resources/images/find.png" height="13px" width="13px" border="0" alt="search">
		 										</a>
		 										<div class="text11" style="position: relative;" align="left" >
									 				<span style="position:absolute; top:2px; width:200px;" id="hekdtm_info" class="popupWithInputText text11"  >
										           		<p><b><spring:message code="systema.tror.orders.form.update.label.transporttype"/></b></p> 
									           			Tast gyldig kode for transportmåte ved grense. Overføres SAD:
									           			<ul>
									           				<li><b>10</b>&nbsp;Fartøy, norske utenlandske.
									           				<li><b>12</b>&nbsp;Jernbanevogn på fartøy.</li>
									           				<li><b>16</b>&nbsp;Bil på fartøy.</li>
									           				<li><b>17</b>&nbsp;Tilhenger på fartøy.</li>	
									           				<li><b>20</b>&nbsp;Jernbane.</li>	
									           				<li><b>23</b>&nbsp;Bil/tilhenger på jernbanevogn.</li>	
									           				<li><b>30</b>&nbsp;Bil (vegtransport).</li>	
									           				<li><b>40</b>&nbsp;Luftfartøy.</li>	
									           				<li><b>50</b>&nbsp;Post.</li>	
									           				<li><b>70</b>&nbsp;Faste installasjoner (rør, kabler etc..).</li>	
									           				<li><b>80</b>&nbsp;Transport på innenlandske vannveier.</li>
									           				<li><b>90</b>&nbsp;Egen framdrift.</li>
									           						
									           			</ul>
													</span>	
												</div>
								 			</td>
								 			<td class="text12">
								 				<select name="hekdtm" id="hekdtm">
								 					<option value="" >-velg-</option>
								 					<c:forEach var="record" items="${model.transporttypeList}" varStatus="counter">
								 						<option value='${record.ks4trm}' <c:if test="${ model.record.hekdtm == record.ks4trm }"> selected </c:if> >${record.ks4trm}</option>
								 					</c:forEach>
												</select>
												
								 			</td>
								 			<td class="text12">&nbsp;
												<img onMouseOver="showPop('hetrm_info');" onMouseOut="hidePop('hetrm_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
												<span title="hetrm"><spring:message code="systema.tror.orders.form.update.label.transportland"/></span>
												<div class="text11" style="position: relative;" align="left" >
									 				<span style="position:absolute; top:2px; width:200px;" id="hetrm_info" class="popupWithInputText text11"  >
										           		<p><b><spring:message code="systema.tror.orders.form.update.label.transportland"/></b></p> 
									           			Tast landkode (tobokstavs) for transportmidlets hjemland.
									           			
													</span>	
												</div>
								 			</td>
								 			<td class="text12">
								 				<select name="hetrm" id="hetrm">
								 					<option value="" >-landkode-</option>
								 					<c:forEach var="record" items="${model.countryCodeList}" varStatus="counter">
								 						<option value='${record.klklk}' <c:if test="${ model.record.hetrm == record.klklk}"> selected </c:if> >${record.klklk}</option>
								 					</c:forEach>
												</select>
												
								 			</td>
								 			
								 			<td class="text12">&nbsp;
								 				<img onMouseOver="showPop('hetrc_info');" onMouseOut="hidePop('hetrc_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
												<span title="hetrc"><spring:message code="systema.tror.orders.form.update.label.container"/></span>
												<div class="text11" style="position: relative;" align="left" >
									 				<span style="position:absolute; top:2px; width:200px;" id="hetrc_info" class="popupWithInputText text11"  >
										           		<p><b><spring:message code="systema.tror.orders.form.update.label.container"/></b></p> 
									           			Tast gyldig kode for å angi om varen ble fraktet i container ved grensepassering.
									           			<ul>
									           				<li><b>0</b>&nbsp;ikke i container.
									           				<li><b>1</b>&nbsp;i container.</li>
									           			</ul>	
													</span>	
												</div>
								 			</td>
								 			<td class="text12">
								 				<select class="inputTextMediumBlue" name="hetrc" id="hetrc" >
								 				  <option value="1"<c:if test="${ model.record.hetrc == '1' }"> selected </c:if> >1</option>
												  <option value="0"<c:if test="${ model.record.hetrc == '0' || empty model.record.hetrc }"> selected </c:if> >0</option>
												</select>
								 			</td>
								 		</tr>
								 		<tr>	
								 			<td class="text12">
								 			<img onMouseOver="showPop('hetrcn_info');" onMouseOut="hidePop('hetrcn_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
								 			<span title="hetrcn"><spring:message code="systema.tror.orders.form.update.label.containernr"/></span>
								 			<div class="text11" style="position: relative;" align="left" >
									 				<span style="position:absolute; top:2px; width:200px;" id="hetrcn_info" class="popupWithInputText text11"  >
										           		<p><b><spring:message code="systema.tror.orders.form.update.label.containernr"/></b></p> 
									           			Tast containernr dersom 1 er svart i foregående felt.
									           			KRAV til feltet dersom CONT = 1.
													</span>	
											</div>
								 			</td>
								 			<td colspan="2" class="text12">
								 				<input type="text" class="inputTextMediumBlue" name="hetrcn" id="hetrcn" size="18" maxlength="17" value="${model.record.hetrcn}">
								 			</td>
							 			</tr>
							 			</table>
						 			</td>
					 			</tr>
					 			
					 			</table>
						 			
							</td>
						</tr>
						
						<tr height="2"><td ></td></tr>
						<tr height="2"><td colspan="2" style="border-bottom:1px solid;border-color:#DDDDDD;" class="text"></td></tr>
						<tr height="2"><td ></td></tr>
						<tr>
							<td class="text14Bold">&nbsp;<font class="text16RedBold" >*</font><spring:message code="systema.tror.orders.form.update.label.shipper"/></td>
							<td class="text14Bold">&nbsp;<font class="text16RedBold" >*</font><spring:message code="systema.tror.orders.form.update.label.consignee"/></td>
						</tr>
						<tr height="5"><td ></td></tr>
						<%--
						
						<tr>
							<td >
							<table class="tableBorderWithRoundCornersLightGray">
								<tr>
									<td class="text12Bold"><spring:message code="systema.tror.orders.form.update.label.shippingDates"/></td>
									<td class="text11">&nbsp;<span title="wsetdd/wsetdk"><spring:message code="systema.tror.orders.form.update.label.shippingDates.etd"/></span></td>
									<td class="text11">
										<input type="text" class="inputTextMediumBlue11" name="wsetdd" id="wsetdd" size="9" maxlength="8" value="${XXmodel.record.wsetdd}">
									</td>
									<td class="text11"><input type="text" class="inputTextMediumBlue11" name="wsetdk" id="wsetdk" size="4" maxlength="4" value="${XXmodel.record.wsetdk}"></td>
									
						
								</tr>
							</table>

							</td>
							<td >

							<table class="tableBorderWithRoundCornersLightGray">
								<tr>
									<td class="text12Bold"><spring:message code="systema.tror.orders.form.update.label.arrivalDates"/></td>
									<td class="text11">&nbsp;<span title="wsetad/wsetak"><spring:message code="systema.tror.orders.form.update.label.arrivalDates.eta"/></span></td>
									<td class="text11">
										<input type="text" class="inputTextMediumBlue11" name="wsetad" id="wsetad" size="9" maxlength="8" value="${XXmodel.record.wsetad}">
									</td>
									<td class="text11"><input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue11" name="wsetak" id="wsetak" size="4" maxlength="4" value="${XXmodel.record.wsetak}"></td>
									
								</tr>
							</table>
							
							</td>		
						</tr>
						<tr height="5"><td ></td></tr>
						 --%>
						
						<tr>
				 			<td valign="top" width="50%" >
				 			 <table style="width:98%" class="tableBorderWithRoundCornersGray" cellspacing="1" cellpadding="0" border="0">
						 		<tr height="10"><td ></td></tr>
						 		<tr>
					 				<td class="text12">
					 					&nbsp;<span title="hekns"><spring:message code="systema.tror.orders.form.update.label.shipper.id"/>&nbsp;</span>
					 					<a tabindex="-1" id="trorSellerIdLink" >
 											<img align="bottom" style="cursor:pointer;" src="resources/images/find.png" height="13px" width="13px" border="0" alt="search">
 										</a>
					 				</td>
					 				<td class="text12">
					 					&nbsp;<span title="whenas"><spring:message code="systema.tror.orders.form.update.label.shipper.seller"/>&nbsp;</span>
					 	
					 				</td>
					 			</tr>
					 			<tr>	
				 					<td class="text12" ><input type="text" class="inputTextMediumBlue" name="hekns" id="hekns" size="10" maxlength="8" value="${model.record.hekns}"></td>
								 	<td class="text12" ><input readonly tabindex=-1 type="text" class="inputTextReadOnly" name="whenas" id="whenas" size="50" value="${XXmodel.record.heknsNavn}&nbsp;-&nbsp;${XXmodel.record.heknsPnSt}"></td>
				 				</tr>
								<tr height="5"><td ></td></tr>
						 		<tr>
					 				<td class="text12">&nbsp;<font class="text16RedBold" >*</font><span title="henas"><spring:message code="systema.tror.orders.form.update.label.shipper.name"/></span>
					 					<%-- <c:if test="${Xmodel.record.fakBetExists}">
						 					<a href="javascript:void(0);" onClick="window.open('tror_childwindow_customer_addresses.do?action=doFind&ctype=s&wkundnr=${user.custNr}','customerWin','top=300px,left=150px,height=800px,width=900px,scrollbars=no,status=no,location=no')">
		 										<img id="imgShipperSearch" align="bottom" style="cursor:pointer;" src="resources/images/find.png" height="13px" width="13px" border="0" alt="search">
		 									</a>
		 								</c:if> --%>
	 									<a tabindex="-1" id="trorSellerAddressesIdLink" >
											<img align="bottom" style="cursor:pointer;" src="resources/images/find.png" height="13px" width="13px" border="0" alt="search">
										</a>	 									
					 				</td>
					 				<td class="text12">&nbsp;<font class="text16RedBold" >*</font><span title="heads1"><spring:message code="systema.tror.orders.form.update.label.shipper.adr1"/></span></td>
					 			</tr>
					 			<tr>	
				 					<td class="text12"><input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" type="text" class="inputTextMediumBlueMandatoryField" name="henas" id="henas" size="25" maxlength="30" value="${model.record.henas}"></td>
				 					<td class="text12"><input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" type="text" class="inputTextMediumBlueMandatoryField" name="heads1" id="heads1" size="25" maxlength="30" value="${model.record.heads1}"></td>
				 				</tr>
					 			<tr>	
					 				<td class="text12">&nbsp;<span title="heads2"><spring:message code="systema.tror.orders.form.update.label.shipper.adr2"/></span></td>
					 				<td class="text12">&nbsp;<span title="heads3"><spring:message code="systema.tror.orders.form.update.label.shipper.adr3"/></span></td>
					 			</tr>
								<tr>	
				 					<td class="text12" >
				 					<input type="text" class="inputTextMediumBlueUPPERCASE" name="heads2" id="heads2" size="25" maxlength="30" value="${model.record.heads2}">
				 					</td>
				 					<td class="text12"><input type="text" class="inputTextMediumBlue" name="heads3" id="heads3" size="25" maxlength="30" value="${model.record.heads3}"></td>
				 				</tr>
				 				
				 				<tr height="15"><td ></td></tr>	
				 				<%--
				 				<tr>	
				 					<td class="text12">
										<img onMouseOver="showPop('herfa_info');" onMouseOut="hidePop('herfa_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					 					<span title="herfa"><spring:message code="systema.tror.orders.form.update.label.avsRef"/></span>						 				
										<div class="text11" style="position: relative;" align="left">
										<span style="position:absolute; left:0px; top:0px; width:250px" id="herfa_info" class="popupWithInputText"  >
											<font class="text11">
							           			<b>Søk Avs.</b>
							           			<div>
							           			<p>Avsenders søkereferanse <br>
							           				Begrep for senere søk/gjenfinning.</p>
							           			</div>
						           			</font>
										</span>
										</div>
									</td>
				 					<td class="text12">
				 						<span title="hesdla">
				 							<img style="vertical-align:middle;" src="resources/images/loading.png" width="15px" height="15px" border="0" alt="load/unload">
				 							<spring:message code="systema.tror.orders.form.update.label.load"/>
				 							<a href="javascript:void(0);" onClick="window.open('tror_childwindow_loadunloadplaces.do?action=doInit&&caller=hesdla','postalcodeWin','top=300px,left=50px,height=600px,width=800px,scrollbars=no,status=no,location=no')">						 				
						 						<img id="imgToSearch" align="bottom" style="cursor:pointer;" src="resources/images/find.png" height="13px" width="13px" border="0" alt="search">
						 					</a>
				 						</span>
				 					</td>
				 				</tr>
				 				<tr>	
				 					<td class="text12" >
				 						<input type="text" class="inputTextMediumBlue" name="herfa" id="herfa" size="20" maxlength="35" value="${model.record.herfa}">
								 	</td>
				 					<td class="text12" >
						 				<input type="text" class="inputTextMediumBlue" name="hesdla" id="hesdla" size="21" maxlength="20" value="${model.record.hesdla}">
										
						 			</td>
				 				</tr>
				 				<tr height="8"><td ></td></tr>
				 				--%>
				 																 				
								<tr>
				 					<td class="text12Bold">&nbsp;
				 						<img style="vertical-align: bottom;" width="24px" height="24px" src="resources/images/invoice.png" border="0" alt="invoice">
				 						<spring:message code="systema.tror.orders.form.update.label.shipper.invoicee"/>
			 						</td>
								</tr>
				 				<tr>
				 					<td colspan="2">
				 					<table class="tableBorderWithRoundCornersLightGray">
					 					<tr>
							 				<td class="text12">
							 					&nbsp;<span title="heknsf"><spring:message code="systema.tror.orders.form.update.label.shipper.invoicee.id"/>&nbsp;</span>
							 					<a tabindex="-1" id="trorSellerFmIdLink" >
		 											<img align="bottom" style="cursor:pointer;" src="resources/images/find.png" height="13px" width="13px" border="0" alt="search">
		 										</a>
							 				</td>
							 				<td class="text12">
							 					&nbsp;<span title="whenasf"><spring:message code="systema.tror.orders.form.update.label.shipper.invoicee.name"/>&nbsp;</span>
							 				</td>
							 				<td class="text12">
							 					<img onMouseOver="showPop('shipperCurr_info');" onMouseOut="hidePop('shipperCurr_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
								 				<span title="hevals"><spring:message code="systema.tror.orders.form.update.label.shipper.invoicee.currencyCode"/>&nbsp;</span>
								 				<div class="text11" style="position: relative;" align="left">
													<span style="position:absolute; left:0px; top:0px; width:250px" id="shipperCurr_info" class="popupWithInputText"  >
														<font class="text11">
									           			<b>Valuta</b>
									           			<div>
									           			<p>Valuta for fakturautstedelse - hentes fra kunderegister, kan overstyres. 
									           				Ved ulik NOK går fremmedvaluta inn i reskontro.
									           			</p>
									           			</div>
								           			</font>
												</span>
												</div>
							 				</td>
							 				
						 				</tr>
						 				<tr>	
						 					<td class="text12" >
						 						<c:choose>
						 						<c:when test="${'0' != model.record.heknsf}">
						 							<input type="text" class="inputTextMediumBlue" name="heknsf" id="heknsf" size="10" maxlength="8" value="${model.record.heknsf}">
						 						</c:when>
						 						<c:otherwise>
						 							<input type="text" class="inputTextMediumBlue" name="heknsf" id="heknsf" size="10" maxlength="8" value="">
						 						</c:otherwise>
						 						</c:choose>
						 					</td>
										 	<td class="text12" ><input readonly tabindex=-1 type="text" class="inputTextReadOnly" name="whenasf" id="whenasf" size="50" value="${Xmodel.record.heknsfNavn} - ${Xmodel.record.heknsfPnSt}"></td>
						 					<td class="text12" >
						 						<select name="hevals" id="hevals">
							 						<option value="">-valuta-</option>
								 				  	<c:forEach var="record" items="${model.currencyCodeList}" >
								 				  		<option value="${record.kvakod}"<c:if test="${model.record.hevals == record.kvakod || (empty model.record.hevals && record.kvakod=='NOK')}"> selected </c:if> >${record.kvakod}</option>
													</c:forEach>  
												</select>
						 					</td>
						 					
					 					</tr>
									</table>
									</td>				 				
					 			</tr>
				 				<tr height="10"><td ></td></tr>
							 </table>
						 	</td>
						 	<td valign="top" width="50%">
				 			 <table style="width:98%" class="tableBorderWithRoundCornersGray" cellspacing="1" cellpadding="0">
					 			<tr height="10"><td ></td></tr>
						 		<tr>
					 				<td class="text12">
					 					&nbsp;<span title="heknk"><spring:message code="systema.tror.orders.form.update.label.consignee.id"/>&nbsp;</span>
					 					<a tabindex="-1" id="trorBuyerIdLink" >
 											<img align="bottom" style="cursor:pointer;" src="resources/images/find.png" height="13px" width="13px" border="0" alt="search">
 										</a>
					 				</td>
					 				<td class="text12">
					 					&nbsp;<span title="whenak"><spring:message code="systema.tror.orders.form.update.label.consignee.buyer"/>&nbsp;</span>
					 				</td>	
					 			</tr>
					 			<tr>	
				 					<td class="text12"><input type="text" class="inputTextMediumBlue" name="heknk" id="heknk" size="10" maxlength="8" value="${model.record.heknk}"></td>
				 					<td class="text12" ><input readonly tabindex=-1 type="text" class="inputTextReadOnly" name="whenak" id="whenak" size="50" value="${Xmodel.record.heknkNavn}&nbsp;-&nbsp;${Xmodel.record.heknkPnSt}"></td>
				 				</tr>
				 				<tr height="5"><td ></td></tr>
						 		<tr>
					 				<td class="text12">&nbsp;<font class="text16RedBold" >*</font><span title="henak"><spring:message code="systema.tror.orders.form.update.label.consignee.name"/></span>
					 					<%-- <c:if test="${Xmodel.record.fakBetExists}">
						 					<a href="javascript:void(0);" onClick="window.open('tror_childwindow_customer_addresses.do?action=doFind&ctype=c&wkundnr=${user.custNr}','customerWin','top=300px,left=150px,height=800px,width=900px,scrollbars=no,status=no,location=no')">
		 										<img id="imgConsigneeSearch" align="bottom" style="cursor:pointer;" src="resources/images/find.png" height="13px" width="13px" border="0" alt="search">
		 									</a>
	 									</c:if> --%>
	 									<a tabindex="-1" id="trorBuyerAddressesIdLink" >
											<img align="bottom" style="cursor:pointer;" src="resources/images/find.png" height="13px" width="13px" border="0" alt="search">
										</a>
					 				</td>
					 				<td class="text12">&nbsp;<font class="text16RedBold" >*</font><span title="headk1"><spring:message code="systema.tror.orders.form.update.label.consignee.adr1"/></span></td>
					 			</tr>
					 			<tr>	
				 					<td class="text12"><input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" type="text" class="inputTextMediumBlueMandatoryField" name="henak" id="henak" size="25" maxlength="30" value="${model.record.henak}"></td>
				 					<td class="text12"><input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" type="text" class="inputTextMediumBlueMandatoryField" name="headk1" id="headk1" size="25" maxlength="30" value="${model.record.headk1}"></td>
				 				</tr>
					 			<tr>	
					 				<td class="text12">&nbsp;<span title="headk2"><spring:message code="systema.tror.orders.form.update.label.consignee.adr2"/></span></td>
					 				<td class="text12">&nbsp;<span title="headk3"><spring:message code="systema.tror.orders.form.update.label.consignee.adr3"/></span></td>
					 			</tr>
								<tr>	
				 					<td class="text12"><input type="text" class="inputTextMediumBlue" name="headk2" id="headk2" size="25" maxlength="30" value="${model.record.headk2}"></td>
				 					<td class="text12"><input type="text" class="inputTextMediumBlue" name="headk3" id="headk3" size="25" maxlength="30" value="${model.record.headk3}"></td>
				 				</tr>
				 				
				 				<tr height="15"><td ></td></tr>
				 				<%--
				 				<tr>	
				 					<td class="text12"><img onMouseOver="showPop('herfk_info');" onMouseOut="hidePop('herfk_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					 					&nbsp;&nbsp;<span title="herfk"><spring:message code="systema.tror.orders.form.update.label.consignee.ref"/></span>
						 				<div class="text11" style="position: relative;" align="left">
										<span style="position:absolute; left:0px; top:0px; width:250px" id="herfk_info" class="popupWithInputText"  >
											<font class="text11">
							           			<b>Søk Mott.</b>
							           			<div>
							           			<p>Mottakers søkereferanse Fritt felt for utfylling. Begrep for senere søk/gjenfinning.</p>
							           			</div>
						           			</font>
										</span>
										</div>
				 					</td>
				 					<td class="text12">
				 						<img style="vertical-align:middle;" src="resources/images/loading.png" width="15px" height="15px" border="0" alt="load/unload">
				 						<span title="hesdl"><spring:message code="systema.tror.orders.form.update.label.unload"/></span>
				 						<a href="javascript:void(0);" onClick="window.open('tror_childwindow_loadunloadplaces.do?action=doInit&caller=hesdl','postalcodeWin','top=300px,left=50px,height=600px,width=800px,scrollbars=no,status=no,location=no')">						 				
						 					<img id="imgToSearch" align="bottom" style="cursor:pointer;" src="resources/images/find.png" height="13px" width="13px" border="0" alt="search">
						 				</a>
				 					</td>
				 				</tr>
				 				<tr>	
				 					<td class="text12" >
				 						<input type="text" class="inputTextMediumBlue" name="herfk" id="herfk" size="20" maxlength="35" value="${model.record.herfk}">
				 					</td>
				 					<td class="text12" >
						 				<input type="text" class="inputTextMediumBlue" name="hesdl" id="hesdl" size="21" maxlength="20" value="${model.record.hesdl}">
										
						 			</td>
				 				</tr>
				 				<tr height="8"><td ></td></tr>
				 				--%>
				 				
								<tr>
				 					<td class="text12Bold">&nbsp;
				 						<img style="vertical-align: bottom;" width="24px" height="24px" src="resources/images/invoice.png" border="0" alt="invoice">
				 						<spring:message code="systema.tror.orders.form.update.label.consignee.invoicee"/>
				 					</td>
								</tr>
				 				<tr>
				 					<td colspan="2">
				 					<table class="tableBorderWithRoundCornersLightGray">
					 					<tr>
							 				<td class="text12">
							 					&nbsp;<span title="heknkf"><spring:message code="systema.tror.orders.form.update.label.consignee.invoicee.id"/>&nbsp;</span>
						 						<a tabindex="-1" id="trorBuyerFmIdLink" >
		 											<img align="bottom" style="cursor:pointer;" src="resources/images/find.png" height="13px" width="13px" border="0" alt="search">
		 										</a>
							 				</td>
							 				<td class="text12">
							 					&nbsp;<span title="whenakf"><spring:message code="systema.tror.orders.form.update.label.consignee.invoicee.name"/>&nbsp;</span>
							 				</td>
							 				<td class="text12">
							 					<img onMouseOver="showPop('consigneeCurr_info');" onMouseOut="hidePop('consigneeCurr_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
								 				<span title="hevalk"><spring:message code="systema.tror.orders.form.update.label.consignee.invoicee.currencyCode"/>&nbsp;</span>
								 				<div class="text11" style="position: relative;" align="left">
													<span style="position:absolute; left:0px; top:0px; width:250px" id="consigneeCurr_info" class="popupWithInputText"  >
														<font class="text11">
									           			<b>Valuta</b>
									           			<div>
									           			<p>Valuta for fakturautstedelse - hentes fra kunderegister, kan overstyres. 
									           				Ved ulik NOK går fremmedvaluta inn i reskontro.
									           			</p>
									           			</div>
								           			</font>
												</span>
												</div>
							 				</td>
						 				</tr>
						 				<tr>	
						 					<td class="text12" >
						 						<c:choose>
						 						<c:when test="${'0' != model.record.heknkf}">
						 							<input type="text" class="inputTextMediumBlue" name="heknkf" id="heknkf" size="10" maxlength="8" value="${model.record.heknkf}">
						 						</c:when>
						 						<c:otherwise>
						 							<input type="text" class="inputTextMediumBlue" name="heknkf" id="heknkf" size="10" maxlength="8" value="">
						 						</c:otherwise>
						 						</c:choose>
						 					</td>
										 	<td class="text12" ><input readonly tabindex=-1 type="text" class="inputTextReadOnly" name="whenakf" id="whenakf" size="50" value="${Xmodel.record.heknkfNavn} - ${Xmodel.record.heknkfPnSt}"></td>
						 					<td class="text12" >
						 						<select name="hevalk" id="hevalk">
							 						<option value="">-valuta-</option>
								 				  	<c:forEach var="record" items="${model.currencyCodeList}" >
								 				  		<option value="${record.kvakod}"<c:if test="${model.record.hevalk == record.kvakod || (empty model.record.hevalk && record.kvakod=='NOK')}"> selected </c:if> >${record.kvakod}</option>
													</c:forEach>  
												</select>
						 					</td>
					 					</tr>
									</table>
									</td>				 				
					 			</tr>
					 						 
				 				<tr height="10"><td ></td></tr>
			 				</table>
						 	</td>
					 	</tr>
					 	<tr height="10"><td ></td></tr>
					 </table>
				</td>
			</tr>
			<tr>
            		<td>
	        			<table style="width:98%;" align="left" class="tableBorderWithRoundCornersGray" cellspacing="0" cellpadding="0">
				 		<tr height="5"><td colspan="2" ></td></tr>
				 		<tr>
							<td valign="top" style="width:50%;border-right:1px solid;border-color:#FFFFFF;""  >
								<table>
						 		<tr height="2"><td ></td></tr>
							 	<tr>	
						 			<td class="text12">
						 				<img onMouseOver="showPop('helka_info');" onMouseOut="hidePop('helka_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
						 				<font class="text12RedBold" >*</font><span title="helka/hesdf"><spring:message code="systema.tror.orders.form.update.label.from"/>&nbsp;</span>
						 				<div class="text11" style="position: relative;" align="left">
											<span style="position:absolute;top:0px; width:250px" id="helka_info" class="popupWithInputText"  >
												<font class="text11">
							           			<b>Fra sted</b>
							           			<div>
							           			<p>Landkode + postnr / kode for "kundefraktens" frasted. Ved IKKE postnr.basert er det kun ett kodefelt (5 langt)
												</p>
							           			</div>
						           			</font>
										</span>
										</div>
				 					</td>
					 				<td class="text12">
					 					<select required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="helka" id="helka">
					 						<option value="">-landkode-</option>
					 						<c:forEach var="record" items="${model.countryCodeList}" >
						 				  		<option value="${record.klklk}"<c:if test="${model.record.helka == record.klklk}"> selected </c:if> >${record.klklk}</option>
											</c:forEach>  
											
										</select>
										
					 				</td>
						 			<td class="text12" nowrap>
						 				<input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" type="text" class="inputTextMediumBlueMandatoryField" name="hesdf" id="hesdf" size="6" maxlength="5" value="${model.record.hesdf}">
						 				<a tabindex=0 id="hesdfIdLink">
	 										<img id="imgFromSearch" align="bottom" style="cursor:pointer;" src="resources/images/find.png" width="13px" height="13px" border="0" alt="search">
	 									</a>
					 				</td>
					 				<td class="text11" colspan="2">
						 				<input readonly tabindex=-1 type="text" class="inputTextReadOnly" name="OWNwppns1" id="OWNwppns1" size="20" maxlength="35" value="${XXmodel.record.wppns1}">
					 				</td>
					 				<td class="text11">&nbsp;&nbsp;</td>
					 			</tr>
					 				 			
							 	</table>
							</td>
							<td align="left">
							<table >
								<tr>
									<td colspan="4">
									<table>
							 			<tr>	
								 			<td class="text11">
								 				<img onMouseOver="showPop('hetri_info');" onMouseOut="hidePop('hetri_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
								 				<font class="text12RedBold" >*</font><span title="hetri/hesdt"><spring:message code="systema.tror.orders.form.update.label.to"/></span>
								 				<div class="text11" style="position: relative;" align="left">
													<span style="position:absolute;top:0px; width:250px" id="hetri_info" class="popupWithInputText"  >
														<font class="text11">
									           			<b>Til sted</b>
									           			<div>
									           			<p>Landkode + postnr / kode for "kundefraktens" tilsted. Ved IKKE postnr.basert er det kun ett kodefelt (5 langt).
														</p>
									           			</div>
								           			</font>
												</span>
												</div>
							 				</td>
							 				<td class="text12">
							 					<select required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="hetri" id="hetri">
							 						<option value="">-landkode-</option>
								 				  	<c:forEach var="record" items="${model.countryCodeList}" >
						 				  				<option value="${record.klklk}"<c:if test="${model.record.hetri == record.klklk}"> selected </c:if> >${record.klklk}</option>
													</c:forEach>  
												</select>
							 				</td>
								 			<td class="text12" nowrap>
								 				<input type="text" required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="hesdt" id="hesdt" size="6" maxlength="5" value="${model.record.hesdt}">
								 				<a tabindex=0 id="hesdtIdLink" >
			 										<img id="imgToSearch" align="bottom" style="cursor:pointer;" src="resources/images/find.png" width="13px" height="13px" border="0" alt="search">
			 									</a>
							 				</td>
											<td class="text12" colspan="2">
								 				<input readonly tabindex=-1 type="text" class="inputTextReadOnly" name="OWNwppns2" id="OWNwppns2" size="20" maxlength="35" value="${XXmodel.record.wppns2}">
							 				</td>
							 				<td class="text12">&nbsp;&nbsp;</td>
							 			</tr>			
							 		</table>
							 		</td>
							 	</tr>
							</table>
							</td>
						</tr>

						<tr height="6"><td colspan="2" ></td></tr>
						<tr height="1"><td colspan="2" style="border-bottom:1px solid;border-color:#FFFFFF;" class="text"></td></tr>
						<tr height="6"><td colspan="2" ></td></tr>
						<tr>
							<td colspan="10">
							<table width="100%" border="0">	
								<tr>
									<td align="left" width="100%" valign="top">
									<table class="tableBorderWithRoundCornersLightGray" border="0">
									<tr>
						 				<td class="text12">
						 					<img onMouseOver="showPop('heot_info');" onMouseOut="hidePop('heot_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
						 					<span title="heot"><spring:message code="systema.tror.orders.form.update.label.oppdragstype"/></span>
						 					<a href="javascript:void(0);" onClick="window.open('tror_mainorder_childwindow_oppdragstype.do?action=doFind&ctype=landimport','opptypeWin','top=300px,left=150px,height=600px,width=800px,scrollbars=no,status=no,location=no')">
	 											<img id="imgOpptype" align="bottom" style="cursor:pointer;" src="resources/images/find.png" height="13px" width="13px" border="0" alt="search">
	 										</a>
	 										<div class="text11" style="position: relative;" align="left">
												<span style="position:absolute;top:2px; width:250px" id="heot_info" class="popupWithInputText"  >
													<font class="text11">
								           			<b><spring:message code="systema.tror.orders.form.update.label.oppdragstype"/></b>
								           			<ul>
								           				<c:forEach var="record" items="${model.oppdragstypeList}" varStatus="counter">
								           					<li><font class="text10"><b>${record.ko1kod}</b>&nbsp;${record.ko1ntx}</font></li>
								           				</c:forEach>
								           			</ul>
							           			</font>
												</span>
											</div>
						 				</td>
							 			<td class="text12">
							 				<select name="heot" id="heot">
							 					<option value="" >-velg-</option>
							 					<c:forEach var="record" items="${model.oppdragstypeList}" varStatus="counter">
							 						<option value='${record.ko1kod}' <c:if test="${record.ko1kod == model.record.heot}"> selected </c:if> >${record.ko1kod}</option>
							 						<c:set var="listSizeHeot" value="${counter.count}" scope="request" /> 
							 					</c:forEach>
											</select>
											
							 			</td>
										<td class="text12">
											<img onMouseOver="showPop('hefr_info');" onMouseOut="hidePop('hefr_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
						 					<span title="hefr"><spring:message code="systema.tror.orders.form.update.label.incoterms"/></span>
											<a href="javascript:void(0);" onClick="window.open('tror_mainorder_childwindow_incoterms.do?action=doFind&ctype=landimport','incotermsWin','top=300px,left=150px,height=600px,width=800px,scrollbars=no,status=no,location=no')">
	 											<img id="imgIncoterms" align="bottom" style="cursor:pointer;" src="resources/images/find.png" height="13px" width="13px" border="0" alt="search">
	 										</a>
	 										<div class="text11" style="position: relative;" align="left">
												<span style="position:absolute;top:2px; width:200px" id="hefr_info" class="popupWithInputText"  >
													<font class="text11">
								           			<b><spring:message code="systema.tror.orders.form.update.label.incoterms"/></b>
								           			<ul>
								           				<c:forEach var="record" items="${model.incotermsList}" varStatus="counter">
								           					<li><font class="text10"><b>${record.kfrkod}</b>&nbsp;${record.kfrntx}</font></li>
								           				</c:forEach>
								           			</ul>
							           			</font>
												</span>
											</div>
										</td>
							 			<td class="text12">
							 				<select name="hefr" id="hefr">
							 					<option value="" >-velg-</option>
							 					<c:forEach var="record" items="${model.incotermsList}" varStatus="counter">
							 						<option value='${record.kfrkod}' <c:if test="${record.kfrkod == model.record.hefr}"> selected </c:if> >${record.kfrkod}</option>
							 						<c:set var="listSizeHefr" value="${counter.count}" scope="request" />
							 					</c:forEach>
							 					
											</select>
							 			</td>
										<td class="text12">
											<img onMouseOver="showPop('hekdpl_info');" onMouseOut="hidePop('hekdpl_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
											<span title="hekdpl"><spring:message code="systema.tror.orders.form.update.label.productcode"/></span>
											<a href="javascript:void(0);" onClick="window.open('tror_mainorder_childwindow_productcodes.do?action=doFind&ctype=landimport','customerWin','top=300px,left=150px,height=600px,width=800px,scrollbars=no,status=no,location=no')">
	 											<img id="imgProductcodes" align="bottom" style="cursor:pointer;" src="resources/images/find.png" height="13px" width="13px" border="0" alt="search">
	 										</a>
	 										<div class="text11" style="position: relative;" align="left">
												<span style="position:absolute;top:2px; width:200px" id="hekdpl_info" class="popupWithInputText"  >
													<font class="text11">
								           			<b><spring:message code="systema.tror.orders.form.update.label.productcode"/></b>
								           			<ul>
								           				<c:forEach var="record" items="${model.produktList}" varStatus="counter">
								           					<li><font class="text10"><b>${record.kfkod}</b>&nbsp;${record.kftxt}</font></li>
								           				</c:forEach>
								           			</ul>
							           			</font>
												</span>
											</div>
	 										
										</td>
							 			<td class="text12">
							 				<select name="hekdpl" id="hekdpl">
							 					<option value="" >-velg-</option>
							 					<c:forEach var="record" items="${model.produktList}" varStatus="counter">
							 						<option value='${record.kfkod}' <c:if test="${record.kfkod == model.record.hekdpl}"> selected </c:if> >${record.kfkod}</option>
							 					</c:forEach>
											</select>
							 			</td>
							 			
										<%--	
							 			<td class="text12" title="todo"><spring:message code="systema.tror.orders.form.update.label.forpassing"/></td>
							    		<td class="text12">
							    			<select class="inputTextMediumBlue" name="hepk2" id="hepk2" >
							 				  <option value="">-velg-</option>
											  <option value="J"<c:if test="${model.record.hepk2 == 'J'}"> selected </c:if> >Ja</option>
											  <option value="N"<c:if test="${model.record.hepk2 == 'N'}"> selected </c:if> >Nei</option>
											</select>
						    			</td>
						    			--%>
							    		<td class="text12" title="hepk3">&nbsp;<spring:message code="systema.tror.orders.form.update.label.kkvittering"/></td>
							    		<td class="text12">
							    			<select class="inputTextMediumBlue" name="hepk3" id="hepk3" >
							 				  <option value="">-velg-</option>
											  <option value="J"<c:if test="${model.record.hepk3 == 'J'}"> selected </c:if> >Ja</option>
											  <option value="N"<c:if test="${model.record.hepk3 == 'N'}"> selected </c:if> >Nei</option>
											</select>
						    			</td>
						    			<td class="text12" title="hepk4">&nbsp;<spring:message code="systema.tror.orders.form.update.label.tolldekl"/></td>
							    		<td class="text12">
							    			<select class="inputTextMediumBlue" name="hepk4" id="hepk4" >
							 				  <option value="">-velg-</option>
											  <option value="J"<c:if test="${model.record.hepk4 == 'J'}"> selected </c:if> >Ja</option>
											  <option value="N"<c:if test="${model.record.hepk4 == 'N'}"> selected </c:if> >Nei</option>
											  <option value="P"<c:if test="${model.record.hepk4 == 'P'}"> selected </c:if> >P</option>
											</select>
						    			</td>
						 			
							 			<td class="text12" width="50px">&nbsp;</td>	
							 			<%-------------------------- --%>
							 			<%-- START Fraktbrev section --%>
							 			<%-------------------------- --%>
							 			<td class="text12">
							 				<img onMouseOver="showPop('fraktbrev_info');" onMouseOut="hidePop('fraktbrev_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
							 				<span title="hepk1"><b><spring:message code="systema.tror.orders.form.update.label.fraktbrev"/></b></span>
							 				<div class="text12" style="position: relative;" align="left">
												<span style="position:absolute; left:0px; top:0px; width:450px" id="fraktbrev_info" class="popupWithInputText"  >
													<font class="text11">
								           			<b>Fraktbrev</b>
								           			<div>
								           				todo
								           			</div>
							           			</font>
											</span>
											</div>
							 			</td>
							 			<td class="text12">
							 				<a tabindex=-1 id="fraktbrevRenderPdfLink" name="fraktbrevRenderPdfLink" target="_new" href="transportdisp_mainorderlist_renderFraktbrev.do?user=${user.user}&wsavd=${model.record.heavd}&wsopd=${model.record.heopd}&wstoll=${Xmodel.record.dftoll}">
		    									<img id="imgFraktbrevPdf" title="Fraktbr.PDF" style="vertical-align:middle;" src="resources/images/pdf.png" width="16" height="16" border="0" alt="Fraktbr. PDF">
											</a>
										</td>
							 			<td nowrap class="text12">
							 				<input type="checkbox" id="hepk1" name="hepk1" value="J" <c:if test="${model.record.hepk1 == 'J'}"> checked </c:if>>
							 				<input readonly type="text" class="inputText11ReadOnly" size="1" maxlength="1" name="hepk1RO" id="hepk1RO" value="${model.record.hepk1}">
							 			</td>
							 			<%--
							 			<td nowrap class="text12">&nbsp;&nbsp;<span title="dftoll"><spring:message code="systema.tror.orders.form.update.label.tollsted"/></span>
							 				<a tabindex=0 id="dftollIdLink" >
	 											<img id="imgToSearch" align="bottom" style="cursor:pointer;" src="resources/images/find.png" width="13px" height="13px" border="0" alt="search">
	 										</a>
							 			</td>
							 			<td class="text12">
							 				<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" size="5" maxlength="4" name="dftoll" id="dftoll" value="${Xmodel.record.dftoll}">
							 			</td>
							 			
							 			<td class="text12">&nbsp;&nbsp;<span title="todo"><spring:message code="systema.tror.orders.form.update.label.merkelapp"/></span></td>
							 			<td nowrap class="text12">
							 				<input type="checkbox" id="todo" name="todo" value="S" <c:if test="${Xmodel.record.dfkdme == 'S'}"> checked </c:if>>
							 				
						 				</td>
							 			<td class="text12">&nbsp;<span title="todo"><spring:message code="systema.tror.orders.form.update.label.etterkrav"/></span></td>
							 			<td nowrap class="text12">
							 				<input type="checkbox" id="todo" name="todo" value="S" <c:if test="${Xmodel.record.dfkdme == 'S'}"> checked </c:if>>
						 				</td>
						 				<td class="text12">&nbsp;<span title="hentvs"><spring:message code="systema.tror.orders.form.update.label.antalVarelinjer"/></span></td>
						 				<td class="text12">
							 				<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" size="5" maxlength="3" name="hentvs" id="hentvs" value="${model.record.hentvs}">
							 			</td>
							 			<td>&nbsp;</td>
							 			 --%>
							 			<%------------------------ --%>
							 			<%-- END Fraktbrev section --%>
							 			<%------------------------ --%>
							 		</tr>

							 		</table>
							 		</td>

						 			</tr>
						 			</table>
						 			</td>

						 		</tr>
					 		</table>
					 		</td>
				 		</tr>
						
						<tr>
			            		<td>
				        			<table width="98%" id="containerdatatableTable" cellspacing="0" align="left" >
			
									<%-- <c:if test="${not empty Xmodel.record.heunik}"> --%>
										<tr height="5"><td align="left" ></td></tr>
										
								 		<tr >
											<td align="bottom" colspan="3" class="text11">&nbsp;&nbsp;<img style="vertical-align:bottom;" src="resources/images/update.gif" width="10px" height="10px" border="0" alt="update item line">
											&nbsp;<spring:message code="systema.tror.orders.form.detail.update.label.itemLine"/>
											</td>
										</tr>
								 		<tr>
											<td colspan="2" style="padding: 0px;">
												<table align="left" border="0" style="width:100%;" >
												<tr class="tableHeaderField10" >
													
										 			<td align="left" valign="bottom" class="tableHeaderFieldFirst11"><span title="hegm1/hegm2">&nbsp;<spring:message code="systema.tror.orders.form.detail.update.label.marks"/></span></td>
										 			<td align="right" valign="bottom" class="tableHeaderField11"><span title="hent">&nbsp;<font class="text12RedBold" >*</font><spring:message code="systema.tror.orders.form.detail.update.label.antal"/>&nbsp;</span></td>
										 			<td align="center" valign="bottom" class="tableHeaderField11"><span title="ownEnhet1/ownEnhet2">&nbsp;<spring:message code="systema.tror.orders.form.detail.update.label.forpak"/></span></td>
										 			<td align="left" valign="bottom" class="tableHeaderField11"><span title="hevs1/hevs2">&nbsp;<font class="text12RedBold" >*</font><spring:message code="systema.tror.orders.form.detail.update.label.goodsDesc"/></span></td>
										 			<td align="right" valign="bottom" class="tableHeaderField11"><span title="hevkt">&nbsp;<font class="text12RedBold" >*</font><spring:message code="systema.tror.orders.form.detail.update.label.weight"/>&nbsp;</span></td>
										 			<td align="right" valign="bottom" class="tableHeaderField11"><span title="hem3">&nbsp;<spring:message code="systema.tror.orders.form.detail.update.label.m3"/>&nbsp;</span></td>
										 			<td align="right" valign="bottom" class="tableHeaderField11"><span title="helm">&nbsp;<spring:message code="systema.tror.orders.form.detail.update.label.lm.fa"/>&nbsp;</span></td>
										 		</tr>
										 		<tr class="tableRow">
										 			<td align="left" class="tableCell" nowrap>
									 					<input type="text" class="inputTextMediumBlue11" name="hegm1" id="hegm1" size="16" maxlength="15" value="${model.record.hegm1}">
									 				</td>
									 				<td align="right" class="tableCell" nowrap>
										 				<input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue11MandatoryField" style="text-align:right;" name="hent" id="hent" size="8" maxlength="7" value="${model.record.hent}">
									 				</td>
									 				<td align="center" class="tableCell" nowrap>
										 				<select name="ownEnhet1" id="ownEnhet1">
										 					<option value="" >-velg-</option>
										 					<c:forEach var="record" items="${model.enhetList}" varStatus="counter">
										 						<c:choose>
											 						<c:when test="${not empty model.uom1}" >
											 							<option value='${record.tkkode}' <c:if test="${ model.uom1 == record.tkkode }"> selected </c:if> >${record.tkkode}</option>
											 						</c:when>
											 						<c:otherwise>
											 							<option value='${record.tkkode}'>${record.tkkode}</option>
											 						</c:otherwise>
										 						</c:choose>
										 					</c:forEach>
														</select>
														
														<a href="javascript:void(0);" onClick="window.open('tror_mainorder_childwindow_unitcodes.do?action=doFind&ctype=tror_landimport_e1','unitWin','top=300px,left=150px,height=600px,width=800px,scrollbars=no,status=no,location=no')">
				 											<img id="imgTransporttype" align="bottom" style="cursor:pointer;" src="resources/images/find.png" height="12px" width="12px" border="0" alt="search">
				 										</a>
														
									 				</td>
									 				<td align="left" class="tableCell" nowrap>
									 					<fmt:parseNumber scope="request" var="uom1Length" type="number" value="${model.uom1Length}" />
									 					<c:choose>
										 					<c:when test="${not empty model.uom1 && not empty model.record.hevs1 && fn:length(model.record.hevs1) > uom1Length}" >
										 						<input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" type="text" class="inputTextMediumBlue11MandatoryField" name="hevs1" id="hevs1" size="30" maxlength="25" value="${ fn:substring(model.record.hevs1, uom1Length, fn:length(model.record.hevs1)) }">		
										 					</c:when>
										 					<c:otherwise>
										 						<input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" type="text" class="inputTextMediumBlue11MandatoryField" name="hevs1" id="hevs1" size="30" maxlength="25" value="${ model.record.hevs1 }">
										 					</c:otherwise>
									 					</c:choose>
									 				</td>
									 				<td align="right" class="tableCell" nowrap>
										 				<input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue11MandatoryField" style="text-align:right;" name="hevkt" id="hevkt" size="10" maxlength="9" value="${model.record.hevkt}">
									 				</td>
									 				
									 				<td align="right" class="tableCell" nowrap>
										 				<input onFocus="calculateVolume(this);" onBlur="checkVolumeNewLine(this);" onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue11" style="text-align:right;" name="hem3" id="hem3" size="12" maxlength="11" value="${ fn:replace(model.record.hem3,'.',',') }">
									 				</td>
									 				<td align="right" class="tableCell" nowrap>
										 				<input onBlur="checkLmNewLine(this);validateNewItemLine();" onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue11" style="text-align:right;" name="helm" id="helm" size="8" maxlength="7" value="${ fn:replace(model.record.helm,'.',',') }"> 
									 				</td>
									 			</tr>
									 			<tr class="tableRow">
										 			<td align="left" class="tableCell" nowrap>
									 					<input type="text" class="inputTextMediumBlue11" name="hegm2" id="hegm2" size="16" maxlength="15" value="${model.record.hegm2}">
									 				</td>
									 				<td align="right" class="tableCell" nowrap>&nbsp;</td>
									 				<td align="center" class="tableCell" >&nbsp;
									 					<%-- OBSOLETE ?
										 				<select name="ownEnhet2" id="ownEnhet2">
										 					<option value="" >-velg-</option>
										 					<c:forEach var="record" items="${model.enhetList}" varStatus="counter">
											 					<c:choose>
											 						<c:when test="${not empty model.record.hevs2 && fn:length(model.record.hevs2) > 2}" >
											 							<option value='${record.tkkode}' <c:if test="${ fn:substring(model.record.hevs2, 0, 2) == record.tkkode }"> selected </c:if> >${record.tkkode}</option>
											 						</c:when>
											 						<c:otherwise>
											 							<option value='${record.tkkode}' >${record.tkkode}</option>
											 						</c:otherwise>
										 						</c:choose>
									 						</c:forEach>
														</select>
														<a href="javascript:void(0);" onClick="window.open('tror_mainorder_childwindow_unitcodes.do?action=doFind&ctype=tror_landimport_e2','unitWin','top=300px,left=150px,height=600px,width=800px,scrollbars=no,status=no,location=no')">
				 											<img id="imgTransporttype" align="bottom" style="cursor:pointer;" src="resources/images/find.png" height="12px" width="12px" border="0" alt="search">
				 										</a>
										 				 --%>
									 				</td>
									 				<td align="left" class="tableCell" nowrap>
									 					<c:choose>
										 					<c:when test="${not empty model.record.hevs2 && fn:length(model.record.hevs2) > 3}" >
										 						<input type="text" class="inputTextMediumBlue11" name="hevs2" id="hevs2" size="30" maxlength="25" value="${ fn:substring(model.record.hevs2, 3, fn:length(model.record.hevs2)) }">		
										 					</c:when>
										 					<c:otherwise>
										 						<input type="text" class="inputTextMediumBlue11" name="hevs2" id="hevs2" size="30" maxlength="25" value="${ model.record.hevs2 }">
										 					</c:otherwise>
									 					</c:choose>
									 					
									 				</td>
									 				<td align="right" class="tableCell" nowrap>&nbsp;</td>
									 				<td align="right" class="tableCell" nowrap>&nbsp;</td>
									 				<td align="right" class="tableCell" nowrap>&nbsp;</td>
									 			</tr>
									 			
									 			</table>
								 			</td>
							 			</tr>
							 			<%-- </c:if>  --%>
									<tr height="5"><td ></td></tr>
				 				</table>
			           		</td>
			            </tr>
						
						<tr>
							<td colspan="2">
								<table style="width:98%;">
								<tr>
									<td align="right">
					 				    <label class="text11Red" id="orderLineErrMsgPlaceHolder"></label>
				 				    </td>
									<td align="right">
										<c:choose>
						 				    <c:when test="${ not empty model.record.heopd }">
						 				    	<c:if test="${empty recordOrderTrorLandImport.hest || recordOrderTrorLandImport.hest == 'U' || recordOrderTrorLandImport.hest == 'O' || recordOrderTrorLandImport.hest == 'F' }">
						 				    		<input class="inputFormSubmit submitSaveClazz" type="submit" name="submit" id="submit" value='<spring:message code="systema.tror.submit.save"/>'/>
						 				    	</c:if>
						 				    </c:when>
						 				    <c:otherwise>
					 				    		<input tabindex=-1 class="inputFormSubmit submitSaveClazz" type="submit" name="submitnew" id="submitnew" value='<spring:message code="systema.tror.submit.createnew.order"/>'/>
						 				    </c:otherwise>	
					 				    </c:choose>
				 				    </td>
							    </tr>
							    </table>
						    </td>
						</tr>
						
	 				</table>
	 				</form>
            		</td>
            </tr>
            <tr height="2"><td></td></tr>
            
			<tr height="10"><td ></td></tr>
			
			<%-- change status dialog --%>	
			 <tr>
				<td>
					<div id="dialogUpdateStatus" title="Dialog">
						 	<form action="tror_mainorderlandimport_updateStatus.do" name="updateStatusForm" id="updateStatusForm" >
						 	<input type="hidden" name="currentAvd" id="currentAvd" value='${model.record.heavd}'>
						 	<input type="hidden" name="currentOpd" id="currentOpd" value='${model.record.heopd}'>
							<p class="text12" >Change status as needed.</p>
							<table>
								<tr>
									<td class="text12" align="left" >
										Status
									</td>
									<td class="text12MediumBlue">
										<select name="selectedStatus" id="selectedStatus">
					            			<option value="">-vælg-</option>
					            			<option value=" ">Åpent</option>
					            			<option value="U">U</option>
											<option value="K">K</option>
											<option value="C">C</option>
											<option value="F">F</option>
											<option value="G">G</option>
											<option value="T">T</option>
											<option value="O">O</option>
											<option value="S">S</option>
											<option value="X">X</option>
											<option value="M">M</option>
										</select>	
									</td>
								</tr>
							</table>
							</form>
					</div>
				</td>
			</tr>

			
	</table>

		
<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->
