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
	<SCRIPT type="text/javascript" src="resources/js/tror_mainorderlandexport_invoice.js?ver=${user.versionEspedsg}"></SCRIPT>
	<SCRIPT type="text/javascript" src="resources/js/trorFkeys_landimport.js?ver=${user.versionEspedsg}"></SCRIPT>
				
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
				<a class="text14" onClick="setBlockUI(this);" href="tror_mainorderlist.do?action=doFind&sign=${recordOrderTrorLand.hesg}" > 	
					<img style="vertical-align:middle;" src="resources/images/bulletGreen.png" width="6px" height="6px" border="0" alt="open orders">
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tror.orderlist.tab"/></font>&nbsp;<font class="text10Orange">F2</font>
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a class="text14" onClick="setBlockUI(this);" href="tror_mainorderlandexport.do?action=doFetch&heavd=${recordOrderTrorLand.heavd}&heopd=${recordOrderTrorLand.heopd}" > 	
					<img style="vertical-align:middle;" src="resources/images/lorry_blue.png" width="18px" height="18px" border="0" alt="update">
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tror.order.tab"/>&nbsp;${recordOrderTrorLand.heavd}/${recordOrderTrorLand.heopd}</font>&nbsp;<font class="text10Orange">F4</font>
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="12%" valign="bottom" class="tab" align="center" nowrap>
				<img style="vertical-align: bottom" src="resources/images/invoice.png" width="16" height="16" border="0" alt="show invoice">
				<font class="tabLink"><spring:message code="systema.tror.order.faktura.tab"/></font>&nbsp;<font class="text10Orange">F9</font>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a class="text14" onClick="setBlockUI(this);" href="editNotisblock.do?action=doFetch&subsys=${subSystem}&avd=${recordOrderTrorLand.heavd}&opd=${recordOrderTrorLand.heopd}&sign=${recordOrderTrorLand.hesg}" > 	
					<img style="vertical-align: bottom" src="resources/images/veiledning.png" width="16" height="16" border="0" alt="show messages">
					<font class="tabDisabledLink"><spring:message code="systema.tror.order.notisblock.tab"/></font>&nbsp;<font class="text10Orange">F10</font>
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a class="text14" onClick="setBlockUI(this);" href="tror_mainorderland_frisokvei.do?action=doFetch&avd=${model.record.heavd}&sign=${model.record.hesg}&opd=${model.record.heopd}">
					<img style="vertical-align: bottom" src="resources/images/lightbulb.png" width="14" height="14" border="0" alt="show log">
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tror.order.frisokvei.tab"/></font>&nbsp;<font class="text10Orange">F7</font>
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a class="text14" onClick="setBlockUI(this);" href="tror_mainorderland_budget.do?avd=${model.record.heavd}&sign=${model.record.hesg}&opd=${model.record.heopd}">
					<img style="vertical-align: bottom" src="resources/images/budget.png" width="16" height="16" border="0" alt="show budget">
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tror.order.budget.tab"/></font>&nbsp;<font class="text10Orange">F8</font>
				</a>
			</td>	
			<c:if test="${recordOrderTrorLand.hepk1 == 'J' || recordOrderTrorLand.hepk1 == 'P'}">
				<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
				<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
					<a class="text14" onClick="setBlockUI(this);" href="tror_mainorderland_freightbill_gate.do?dfavd=${recordOrderTrorLand.heavd}&sign=${recordOrderTrorLand.hesg}&dfopd=${recordOrderTrorLand.heopd}">
						<img style="vertical-align: bottom" src="resources/images/fraktbrev.png" width="16" height="16" border="0" alt="show freight doc">
						<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tror.order.fraktbrev.tab"/></font>&nbsp;<font class="text10Orange">F3</font>
					</a>
				</td>
			</c:if>	
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a class="text14" onClick="setBlockUI(this);" href="tror_mainorderland_ttrace.do?avd=${model.record.heavd}&sign=${model.record.hesg}&opd=${model.record.heopd}">
					<img style="vertical-align: bottom" src="resources/images/sort_down.png" width="10" height="10" border="0" alt="show more">
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tror.order.more.tab"/></font>
				</a>
			</td>
						
			<td width="80%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
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
	 				<table width="100%" align="left" class="formFrameHeaderTransparent" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="15">
				 			<td class="text12MediumBlue">
				 				&nbsp;<spring:message code="systema.tror.orders.invoice.update.label.avd"/>&nbsp;<b>${recordOrderTrorLand.heavd}</b>
				 				&nbsp;<spring:message code="systema.tror.orders.invoice.update.label.order"/>&nbsp;<b>${recordOrderTrorLand.heopd}</b>
				 				&nbsp;<spring:message code="systema.tror.orders.invoice.update.label.sign"/>&nbsp;<b>${recordOrderTrorLand.hesg}</b>
				 				&nbsp;&nbsp;
				 				<img onMouseOver="showPop('status_info');" onMouseOut="hidePop('status_info');" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 				<spring:message code="systema.tror.orders.invoice.update.label.status"/>&nbsp;<b>${recordOrderTrorLand.hest}</b>
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
				 				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="systema.tror.orders.invoice.update.label.turnr"/>&nbsp;<b>${recordOrderTrorLand.hepro}</b>
				 				&nbsp;<spring:message code="systema.tror.orders.invoice.update.label.godsnr"/>&nbsp;<b>${recordOrderTrorLand.hegn}</b>
				 				&nbsp;<spring:message code="systema.tror.orders.invoice.update.label.frankatur"/>&nbsp;<b>${recordOrderTrorLand.hefr}</b>
				 				&nbsp;&nbsp;&nbsp;<spring:message code="systema.tror.orders.invoice.update.label.fvekt"/>&nbsp;<b>${recordOrderTrorLand.hefbv}</b>
				 				
				 				
			 				</td>
		 				</tr>
	 				</table>
					<%-- MASTER Topic information [it is passed through a session object: XX] --%>
				 	<table height="40" width="100%" align="left" class="formFrameTitaniumWhite" border="0" cellspacing="0" cellpadding="0">
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
							           	<td class="text11MediumBlue" align="left">${recordOrderTrorLand.henas}</td>
							        </tr>
									<tr>
							            <td width="30%" class="text11" align="left"><spring:message code="systema.tror.orders.form.update.label.shipper.adr1"/>&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordOrderTrorLand.heads1}</td>
							        </tr>
							        <tr>
							            <td width="30%" class="text11" align="left"><spring:message code="systema.tror.orders.form.update.label.shipper.adr2"/>&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordOrderTrorLand.heads2}</td>
							        </tr>
							        
									<tr>
							            <td width="30%" class="text11" align="left"><spring:message code="systema.tror.orders.form.update.label.shipper.adr3"/>&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordOrderTrorLand.heads3}</td>
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
							           	<td class="text11MediumBlue" align="left">${recordOrderTrorLand.henak}</td>
							        </tr>
									<tr>
							            <td width="30%" class="text11" align="left"><spring:message code="systema.tror.orders.form.update.label.consignee.adr1"/>&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordOrderTrorLand.headk1}</td>
							        </tr>
							        <tr>
							            <td width="30%" class="text11" align="left"><spring:message code="systema.tror.orders.form.update.label.consignee.adr2"/>&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordOrderTrorLand.headk2}</td>
							        </tr>
									<tr>
							            <td width="30%" class="text11" align="left"><spring:message code="systema.tror.orders.form.update.label.consignee.adr3"/>&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordOrderTrorLand.headk3}</td>
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
	        			
	        			<tr>
	        				<td>	        				
				           		&nbsp;<button name="readyMarkButton" id="readyMarkButton" class="buttonGrayWithGreenFrame" type="button" >&nbsp;Ferdigmelde</button>
				           		<c:choose>
					           		<c:when test="${not empty model.container.readyMarkStatus}">
					           			<font id="readyMarkInvoice" class="text12MediumBlue">&nbsp;Status:&nbsp;${model.container.readyMarkStatus}</font>
					           		</c:when>
					           		<c:otherwise>
					           			<%-- this id must always be present since AJAX call must fill upp with text... --%>
					           			<font id="readyMarkInvoice" class="text12MediumBlue"></font>
					           		</c:otherwise>
				           		</c:choose>
				           		&nbsp;<button name="recalculateButton" id="recalculateButton" class="buttonGrayWithGreenFrame" type="button" >&nbsp;Ny beregning</button>
	        				</td>
	        			</tr>
	        			
						<tr>
							<td class="ownScrollableSubWindow" style="width:100%; height:25em;"> 
								<form name="formItemList" id="formItemList" method="POST" >
				               		<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
				 				<table id="container tableTable" width="100%" cellspacing="2" align="left" >
								<tr>
								<td class="text11">
								<table width="100%" cellspacing="0" border="0" cellpadding="0">		
								<%-- <table id="tblInvoices" class="display compact cell-border" >  --%>
									<thead>
									<tr style="background-color:#DDDDDD" class="tableHeaderField" height="23" valign="left">
										<th width="2%" align="center" class="tableHeaderFieldFirst" title="fali">&nbsp;<spring:message code="systema.tror.orders.invoice.update.label.lineNr"/></th>
										<c:if test="${empty recordOrderTrorLand.hest || not empty recordOrderTrorLand.hest }">
									    	<th width="2%" align="center" class="tableHeaderField">&nbsp;<spring:message code="systema.tror.orders.invoice.update.label.edit"/></th> 
									    </c:if>
									    <th align="center" class="tableHeaderField" title="fask">&nbsp;<spring:message code="systema.tror.orders.invoice.update.label.sk"/></th>
									    <th align="left" class="tableHeaderField" title="favk">&nbsp;<spring:message code="systema.tror.orders.invoice.update.label.gebyrCode"/></th>
									    <th align="left" class="tableHeaderField" title="stdVt/faVT">&nbsp;<spring:message code="systema.tror.orders.invoice.update.label.text"/></th> 
									    <th width="2%" align="center" class="tableHeaderField" title="faval">&nbsp;<spring:message code="systema.tror.orders.invoice.update.label.currency"/></th>
					                    <th align="right" class="tableHeaderField" title="fabelv">&nbsp;<spring:message code="systema.tror.orders.invoice.update.label.amount1"/>&nbsp;</th>
					                    <th align="right" class="tableHeaderField" title="fabeln">&nbsp;<spring:message code="systema.tror.orders.invoice.update.label.amount2"/>&nbsp;</th>
					                    <th width="2%" align="center" class="tableHeaderField" title="fakdm">&nbsp;<spring:message code="systema.tror.orders.invoice.update.label.mva"/></th>
					                    <th width="2%" align="center" class="tableHeaderField" title="fakda">&nbsp;<spring:message code="systema.tror.orders.invoice.update.label.opr"/></th>
					                    <th width="2%" align="center" class="tableHeaderField" title="fakda">&nbsp;<spring:message code="systema.tror.orders.invoice.update.label.st"/></th>
					                    <th class="tableHeaderField" title="fakunr/knavn">&nbsp;<spring:message code="systema.tror.orders.invoice.update.label.customer"/></th>
					                    <th align="right" class="tableHeaderField" title="fabelu"><spring:message code="systema.tror.orders.invoice.update.label.budget"/>&nbsp;</th>
					                    
					                    <c:if test="${empty recordOrderTrorLand.hest || not empty recordOrderTrorLand.hest}">
					                    	<th width="2%" align="center" class="tableHeaderField" nowrap>&nbsp;<spring:message code="systema.tror.orders.invoice.update.label.delete"/></th>
					                    </c:if> 
					               </tr> 
								   </thead>
								   <tbody >						               
				 					  <c:forEach items="${model.list}" var="record" varStatus="counter">  
				 					  	 
							              <tr class="tableRow" height="20" >
										   <td width="2%" align="center" class="tableCellFirst" >${record.fali}</td>

										   <c:if test="${empty recordOrderTrorLand.hest || not empty recordOrderTrorLand.hest }">
								               <td width="2%" class="tableCell" align="center">
								               		<c:if test="${not empty record.fali}"> 
								               			<c:if test="${ record.faopko != 'O' && record.faopko != 'F' && record.faopko != 'G'}">
									               			<a tabindex=-1 id="recordUpdate_${record.fali}" href="#" onClick="getInvoiceItemData(this);">
									               				<img title="Update" style="vertical-align:bottom;" src="resources/images/update.gif" border="0" alt="update">&nbsp;
									               			</a>
								               			</c:if>							       
							               			</c:if>        			
								               </td>
							               </c:if>

							               <td width="2%" align="center" class="tableCell" >&nbsp;${record.fask}</td>
							               <td width="2%" align="center" class="tableCell" >&nbsp;${record.favk}</td>
							               <td class="tableCell" <c:if test="${empty record.fali}"> style="font-weight: bold;" </c:if> align="left">
						               	   		<c:choose>
								               		<c:when test="${not empty record.faVT}">&nbsp;${record.faVT}</c:when>
								               		<c:otherwise>&nbsp;${record.stdVt}</c:otherwise>
							               		</c:choose>
							               </td>
							               <td width="2%" align="center" class="tableCell" >&nbsp;${record.faval}</td>
							               <td align="right" class="tableCell" <c:if test="${record.fakda == 'K'}"> style="color:#D8000C;" </c:if>>&nbsp;${record.fabelv}&nbsp;</td>
							               
							               <%--START fabeln --%>
							               
							               <c:choose>
								               <c:when test="${empty record.fali}">
								               		<c:choose>
									               		<c:when test="${fn:contains(record.fabeln, '-')}">
							               					<td class="tableCell" align="right" style="font-weight: bold; color:#D8000C;" >&nbsp;${record.fabeln}&nbsp;</td>
									               		</c:when>
									               		<c:otherwise>
									               			<td class="tableCell" align="right" style="font-weight: bold;" >&nbsp;${record.fabeln}&nbsp;</td>
									               		</c:otherwise>
								               		</c:choose>
								               </c:when>
							               	<c:otherwise>
								               		<td class="tableCell" align="right" <c:if test="${record.fakda == 'K'}"> style="color:#D8000C;" </c:if>>&nbsp;${record.fabeln}&nbsp;</td>
								               </c:otherwise>
							               </c:choose>
							               <%-- END fabeln --%>
							               
							               <td width="2%" align="center" class="tableCell" >&nbsp;${record.fakdm}</td>
							               <td width="2%" align="center" class="tableCell" >&nbsp;${record.fakda}</td>
							               <td width="2%" align="center" class="tableCell" >&nbsp;${record.faopko}</td>
							               <td align="left" class="tableCell" >&nbsp;<font class="text11MediumBlue"><b>${record.fakunr}</b></font>&nbsp;${record.knavn}</td>
							               <td align="right" class="tableCell" >${record.fabelu}</td>
							               <%-- DELETE cell --%>
							               <c:if test="${empty recordOrderTrorLand.hest || not empty recordOrderTrorLand.hest}">
							               		<td class="tableCell" align="center" nowrap>
											   		<c:if test="${not empty record.fali}">
											   			<c:if test="${ record.faopko != 'O' && record.faopko != 'F' && record.faopko != 'G'}">
									                   		<a style="cursor:pointer;" id="heavd_${recordOrderTrorLand.heavd}@heopd_${recordOrderTrorLand.heopd}@fali_${record.fali}" onClick="doPermanentlyDeleteInvoiceLine(this);" tabindex=-1 >
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
								        <%-- <c:set var="ownKundnrVar" value="${record.fakunr}" scope="request" /> --%>
								        
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
	 				<form action="tror_mainorderlandexport_invoice_edit.do" name="trorEditInvoiceItemForm" id="trorEditInvoiceItemForm" method="post">
				 	<%--Required key parameters from the Topic parent --%>
				 	<input type="hidden" name="action" id="action" value='doUpdate'/>
				 	<input type="hidden" name="fali" id="fali" value='${model.record.fali}'/>
				 	<input type="hidden" name="heavd" id="heavd" value='${recordOrderTrorLand.heavd}'>
	 				<input type="hidden" name="heopd" id="heopd" value='${recordOrderTrorLand.heopd}'>
					<input type="hidden" name="lineId" id="lineId" value="">
				 	<%-- <input type="hidden" name="numberOfItemLinesInTopic" id="numberOfItemLinesInTopic" value="${numberOfItemLinesInTopic}" /> --%>
				 	<%--for F-Keys shortcuts. Used only in trorFkeys_...js --%>
					<input type="hidden" name="fkeysavd" id="fkeysavd" value='${recordOrderTrorLand.heavd}'>
					<input type="hidden" name="fkeysopd" id="fkeysopd" value='${recordOrderTrorLand.heopd}'>
					<input type="hidden" name="fkyessign" id="fkyessign" value='${recordOrderTrorLand.hesg}'>
					<c:choose>
						<c:when test="${recordOrderTrorLand.heur == 'A'}">
							<input type="hidden" name="fkyessubsys" id="fkyessubsys" value='mainorderlandimport'>
						</c:when>
						<c:otherwise>
							<input type="hidden" name="fkyessubsys" id="fkyessubsys" value='mainorderlandexport'>
						</c:otherwise>
					</c:choose>	
				
				 	<%-- Topic ITEM CREATE --%>
	 				<table width="100%" align="left" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
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
					<table width="100%" align="left" class="formFrame" border="0" cellspacing="0" cellpadding="0">
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
							            <td class="text12" align="left"><span title="favk">&nbsp;<spring:message code="systema.tror.orders.invoice.update.label.gebyrCode"/></span>
							            	<a tabindex=-1 id="favkIdLink" >
	 											<img id="imgGebyrCodesSearch" align="bottom" style="cursor:pointer;" src="resources/images/find.png" height="13px" width="13px" border="0" alt="search">
	 										</a>
							            </td>
							            <td class="text12" align="left">
								            <img onMouseOver="showPop('tnr_info');" onMouseOut="hidePop('tnr_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
								            <span title="wtnr">&nbsp;<spring:message code="systema.tror.orders.invoice.update.label.tnr"/></span>
							            	<div class="text11" style="position: relative; display: inline;" align="left">
											<span style="position:absolute; width:200px;" id="tnr_info" class="popupWithInputText"  >
												<font class="text11">
							           			<b>Tabellnr</b>
							           			<div>
							           				<p>
							           				Man kan her, ved manuell oppretting av fakturalinje, angi hvilken bruttotabell gebyrprisen skal hentes fra.Det er ikke krav til feltet.
													</p>
	    										</div>	 
						           				</font>
											</span>
											</div>
							            </td>
							            
							            <td class="text12" align="left"><span title="fabelv">&nbsp;<spring:message code="systema.tror.orders.invoice.update.label.amount1"/></span></td>
					            		<td class="text12" align="left"><span title="faval">&nbsp;<spring:message code="systema.tror.orders.invoice.update.label.currency"/></span></td>
					            		<td class="text12" align="left"><span title="fakdm">&nbsp;<spring:message code="systema.tror.orders.invoice.update.label.mva"/></span></td>
					            		<td class="text12" align="left"><span title="faVT">&nbsp;<spring:message code="systema.tror.orders.invoice.update.label.freetext"/></span></td>
					            		<td class="text12" align="left"><span title="wantall">&nbsp;<spring:message code="systema.tror.orders.invoice.update.label.wantall"/></span></td>
					            		<td class="text12" align="left"><span title="fakunr">&nbsp;<spring:message code="systema.tror.orders.invoice.update.label.customer"/></span>
							            	<a tabindex=-1 href="javascript:void(0);" onClick="window.open('transportdisp_workflow_childwindow_customer.do?action=doInit&ctype=il','customerWin','top=300px,left=50px,height=800px,width=900px,scrollbars=no,status=no,location=no')">
	 											<img id="imgAgentSearch" align="bottom" style="cursor:pointer;" src="resources/images/find.png" height="13px" width="13px" border="0" alt="search">
	 										</a>
							            </td>
							            <td class="text12" align="left"><span title="wkurs">&nbsp;<spring:message code="systema.tror.orders.invoice.update.label.kurs"/></span></td>
					            		<td class="text12" align="left"><span title="faavdr">&nbsp;<spring:message code="systema.tror.orders.invoice.update.label.avdi"/></span></td>
					            		<td class="text12" align="left"><span title="fadocnB">&nbsp;<spring:message code="systema.tror.orders.invoice.update.label.opdi"/></span></td>
							        </tr>
							        <tr>
						        		<td align="left">
						        			
						        			<select required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="fask" id="fask">
						 						<option value="">-velg-</option>
							 				  	<option value="A"<c:if test="${model.record.fask == 'A'}"> selected </c:if> >A</option>
												<option value="F"<c:if test="${model.record.fask == 'F'}"> selected </c:if> >F</option>
												<option value="I"<c:if test="${model.record.fask == 'I'}"> selected </c:if> >I</option>
												<option value="K"<c:if test="${model.record.fask == 'K'}"> selected </c:if> >K</option>
												<option value="S"<c:if test="${model.record.fask == 'S' || empty model.record.fask}"> selected </c:if> >S</option>
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
							            <td class="text12" align="left">
						            		<input type="text" class="inputText" name="wtnr" id="wtnr" size="5" maxlength="5" value="${model.record.wtnr}">
							            </td>
										<td class="text12Grey" align="left" >
							 				<input type="text" onKeyPress="return numberKey(event)" required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="fabelv" id="fabelv" size="10" maxlength="8" value="${model.record.fabelv}">
							 			</td>
							 			<td class="text12" align="left">
						            		<select required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="faval" id="faval">
						 						<option value="">-valuta-</option>
							 				  	<c:forEach var="record" items="${model.currencyCodeList}" >
							 				  		<option value="${record.kvakod}"<c:if test="${model.record.faval == record.kvakod || (empty model.record.faval && record.kvakod=='NOK')}"> selected </c:if> >${record.kvakod}</option>
												</c:forEach>  
											</select>
							            </td>
							 			<td align="left">
						        			<select required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="fakdm" id="fakdm">
						 						<option value="">-velg-</option>
							 				  	<option value="J"<c:if test="${model.record.fakdm == 'J' || empty model.record.fakdm}"> selected </c:if> >J</option>
												<option value="N"<c:if test="${model.record.fakdm == 'N'}"> selected </c:if> >N</option>
											</select>
										</td>
										<td align="left" nowrap>
											<input type="text" class="inputTextMediumBlue" name="faVT" id="faVT" size="21" maxlength="20" value="${model.record.faVT}">
										</td>
										<td class="text12" align="left" ><input type="text" class="inputText" onKeyPress="return numberKey(event)" name="wantall" id="wantall" size="5" maxlength="5" value="${model.record.wantall}"></td>
							            
										<td class="text12" align="left">
						            		<input type="text" class="inputText" onKeyPress="return numberKey(event)" name="fakunr" id="fakunr" size="9" maxlength="8" value="${model.record.fakunr}">
							            </td>
							            <td class="text12" align="left" ><input type="text" class="inputText" onKeyPress="return amount(event)" name="wkurs" id="wkurs" size="6" maxlength="8" value="${model.record.wkurs}"></td>
							            <td class="text12">
						        			<input type="text" class="inputText" name="faavdr" id="faavdr" size="5" maxlength="4" value="${model.record.faavdr}">
						        		</td>
						        		<td class="text12" align="left" >
						        			<input type="text" class="inputText" name="fadocnB" id="fadocnB" size="8" maxlength="7" value="${model.record.fadocnB}">
						        		</td>
							        </tr>
							        <tr height="12"><td class="text" align="left" colspan="20"><hr></td></tr>
							        <tr height="3"><td class="text" align="left"></td></tr>
							        <tr>
							 			<td class="text12" align="left" >&nbsp;
							            	<img onMouseOver="showPop('utgift_info');" onMouseOut="hidePop('utgift_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
							 				<span title="fakduk/facu33/fabelu"><spring:message code="systema.tror.orders.invoice.update.label.utgift"/></span>
							 				<div class="text11" style="position: relative; display: inline;" align="left">
											<span style="position:absolute; width:400px;" id="utgift_info" class="popupWithInputText"  >
												<font class="text11">
							           			<b>Utgift</b>
							           			<div>
							           				<p>Felt 1: fast kod</p>
							           				<ul>
														<li><b>blank</b>=ikke forventet utgift/budsjett</li>
														<li><b>B</b>=Budsjettering obligatorisk</li>
														<li><b>N</b>=Nei / obligatorisk er overstyrt</li>
														<li><b>S</b>=?</li>
														<li><b>L</b>=?</li>
														<li><b>H</b>=?</li>
													</ul>
													<p>Felt 2: valuta kode</p>
													
													
													<p>Alle gebyr/varekoder som har felt "SPLITT" utfylt (vedlikehold gebyrkoder MENU MAINT2, punkt 3), krever at et netto kostnadsbeløp i feltet UTGIFT er utfyllt. Avhengig av "splittkoden" benyttes beløpet til ulike formål:</p>
													<ul>
														<li>Splitt = <b>B</b>-Budsjett. Netto kostnad flyttes automatisk til budsjett/rekvisisjonssystemet som en
														forventet inngående faktura. Det er også mulig å angi valutakode for utenlands valuta ved
														budsjettering av kostnad.
														</li>
														<li>Splitt = <b>L</b>-Leverandørføring. Netto kostnad føres automatisk i Leverandørreskontro. Felt LENR(L)
														må da være utfylt.</li>
														<li>Splitt = <b>J</b> Netto kostnad føres til reskontro for utleggskontroll.</li>
 													</ul>
													<p> 													
													Dersom det i gebyr/varekoderegisteret også er utfylt felt %SATS (% av bruttobeløp som trekkes ut som ren inntekt) vil feltet UTGIFT bli automatisk fylt ut med det resterende som er kostnad.
													Denne verdien kan overstyres.
													</p>
													<p>Ved å sette kode N i feltet kan man overstyre kravet til å taste utgift.</p>
	    										</div>	
	    										</font>
											</span>
											</div>
							            </td>
							            <td class="text12" align="left" >&nbsp;<spring:message code="systema.tror.orders.invoice.update.label.utgift.currency"/></td>
							            <td class="text12" align="left" >&nbsp;<spring:message code="systema.tror.orders.invoice.update.label.utgift.costbelopp"/></td>
							            <td class="text12" align="left"><span title="falevn">&nbsp;<spring:message code="systema.tror.orders.invoice.update.label.supplier.id"/></span>
						            		<a tabindex=-1 id="falevnIdLink" >
	 											<img id="imgSupplierSearch" align="bottom" style="cursor:pointer;" src="resources/images/find.png" height="13px" width="13px" border="0" alt="search">
	 										</a>
						            	</td>
						            	<td colspan="2" class="text12" align="left" ><span title="lnavn">&nbsp;<spring:message code="systema.tror.orders.invoice.update.label.supplier.name"/></span></td>
							            
					            		<td class="text12" align="left"><span title="wkomp">
						            		<img onMouseOver="showPop('komp_info');" onMouseOut="hidePop('komp_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
						            		<spring:message code="systema.tror.orders.invoice.update.label.komplett"/></span>
						            		<div class="text11" style="position: relative; display: inline;" align="left">
											<span style="position:absolute; width:350px" id="komp_info" class="popupWithInputText"  >
												<font class="text11">
							           			<b>Komplett</b>
							           			<div>
							           				<p>Man kan her, ved manuell oppretting av fakturalinje, angi hvilken bruttotabell gebyrprisen skal hentes fra.</p>
							           				Det er ikke krav til feltet.
							           				<p>Dette feltet er kun i bruk for de som benytter "Frakttabeller/Land og sjø-LCL (ikke postal)" (MENU MAINT3, punkt 3 og MENU MAINT4, punkt 4). Skal frakt beregnes etter komplett enhet ? </p>
							           				<p>Har kunden booket en komplett enhet kan fraktprisen hentes ved å taste 1,2 eller 3 her.</p>
							           				<ul>
														<li><b>1</b> = Hent pris for hele forvognen.</li> 
														<li><b>2</b> = Hent pris for hele hengeren.</li>
														<li><b>3</b> = Hent pris for hele vogntoget.</li>
													</ul>
													<p>
													OBS! Om en glemmer å taste noe her, så vil likevel aldri en vektberegnet pris bli større enn tallet gitt i "Komplett" i fraktavtalen.
							           				</p>
	    										</div>	 
						           				</font>
											</span>
											</div>

					            		</td>
					            		<td class="text12" align="left"><span title="facd11">
						            		<img onMouseOver="showPop('sam_info');" onMouseOut="hidePop('sam_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
						            		<spring:message code="systema.tror.orders.invoice.update.label.samm"/></span>
						            		<div class="text11" style="position: relative; display: inline;" align="left">
											<span style="position:absolute; width:350px" id="sam_info" class="popupWithInputText"  >
												<font class="text11">
							           			<b>Samm</b>
							           			<div>
							           				<p>Hvis gebyret i fakturautskriften skal slås sammen med en eller flere andre gebyrlinjer under en felles gebyrtekst,
							           				 skal det her ligge en kode som forteller hvilke linjer som skal slås sammen og med hvilken gebyrtekst.</p>
							           				<p>Denne sammenslåingen vil kun påvirke fakturautskriften.
							           				Systemet vil ta vare på og overføre til regnskap hver enkelt fakturalinje som vanlig</p>
													
	    										</div>	 
						           				</font>
											</span>
											</div>
						            		
						            	</td>
			            			</tr>
		            				
							        </tr>
							        <tr>
							        	<td class="text12">&nbsp;
						        			<select name="fakduk" id="fakduk">
						 						<option value=" ">-blank-</option>
							 				  	<option value="B" <c:if test="${model.record.fakduk == 'B'}"> selected </c:if> >B</option>
							 				  	<option value="N" <c:if test="${model.record.fakduk == 'N'}"> selected </c:if> >N</option>
							 				  	<option value="S" <c:if test="${model.record.fakduk == 'S'}"> selected </c:if> >S</option>
							 				  	<option value="L" <c:if test="${model.record.fakduk == 'L'}"> selected </c:if> >L</option>
							 				  	<option value="H" <c:if test="${model.record.fakduk == 'H'}"> selected </c:if> >H</option>
											</select>
										</td>
										<td class="text12">	
						        			<select name="facu33" id="facu33">
						 						<c:forEach var="record" items="${model.currencyCodeList}" >
							 				  		<option value="${record.kvakod}"<c:if test="${model.record.facu33 == record.kvakod || (empty model.record.facu33 && record.kvakod=='NOK')}"> selected </c:if> >${record.kvakod}</option>
												</c:forEach> 
											</select>
										</td>
										<td class="text12">	
						        			<input type="text" class="inputText" onKeyPress="return amountKey(event)" name="fabelu" id="fabelu" size="15" maxlength="14" value="${model.record.fabelu}">
										</td>
										<td class="text12" align="left" >
											<input type="text" class="inputText" name="falevn" id="falevn" size="9" maxlength="8" value="${model.record.falevn}">
										</td>
							            <td colspan="2" class="text12" align="left" >
											<input type="text" class="inputText" name="lnavn" id="lnavn" size="20" maxlength="35" value="${model.record.lnavn}">
										</td>
										<td class="text12" align="left" nowrap>
							            	<input type="text" class="inputText" name="wkomp" id="wkomp" size="5" maxlength="5" value="${model.record.wkomp}">
										</td>
						        		<td class="text12" align="left">
						            		<input type="text" class="inputText" name="facd11" id="facd11" size="5" maxlength="5" value="${model.record.facd11}">
						            	</td>
			            				
			            				<c:choose>	
			            					
											<%--<c:when test="${empty recordOrderTrorLand.hest || recordOrderTrorLand.hest == 'U' || recordOrderTrorLand.hest == 'O' || recordOrderTrorLand.hest == 'F' }"> --%>
											<c:when test="${empty recordOrderTrorLand.hest || not empty recordOrderTrorLand.hest}"> 
												<td align="center">
													<input class="inputFormSubmit" type="submit" name="submit" value='<spring:message code="systema.tror.submit.save"/>'>
												</td>
												<td align="left" colspan="3">	
													<input class="inputFormSubmitGray" type="button" name="updCancelButton" id="updCancelButton" value='<spring:message code="systema.tror.submit.clearValues"/>'>
												</td>
											</c:when>
											<c:otherwise>
												<td align="left" colspan="2">
					 				    			<input disabled class="inputFormSubmitGrayDisabled" type="submit" name="submit" value='<spring:message code="systema.tror.submit.not.editable"/>'/>
					 				    		</td>
					 				    	</c:otherwise>	
				 				    	</c:choose>	
										
							        </tr>
							        
							        <tr height="10"><td class="text" align="left"></td></tr>
						        </table>
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

