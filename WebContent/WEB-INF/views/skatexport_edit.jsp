<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerSkat.jsp" />
<!-- =====================end header ==========================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/jquery.calculator.js"></SCRIPT>
	<SCRIPT type="text/javascript" src="resources/js/jquery-ui-timepicker-addon.js"></SCRIPT>
	<SCRIPT type="text/javascript" src="resources/js/skatglobal_edit.js?ver=${user.versionEspedsg}"></SCRIPT>			
	<SCRIPT type="text/javascript" src="resources/js/skatexport_edit.js?ver=${user.versionEspedsg}"></SCRIPT>
	
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
	

<table width="100%" cellspacing="0" border="0" cellpadding="0">
	
 <tr>
 <td>	
	<%-- tab container component --%>
	<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
		<tr height="2"><td></td></tr>
		<tr height="25"> 
			<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a id="alinkTopicList" tabindex=-1 style="display:block;" 
					<c:choose>
						<c:when test="${empty model.record.dkeh_sysg}">href="skatexport.do?action=doFind&sign=${user.skatSign}"</c:when>
						<c:otherwise>href="skatexport.do?action=doFind&sign=${model.record.dkeh_sysg}""</c:otherwise>
					</c:choose> >
				
					<img valign="bottom" src="resources/images/list.gif" border="0" alt="general list">
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.skat.export.list.tab"/></font>
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<c:choose> 
			    <c:when test="${editActionOnTopic=='doUpdate' or editActionOnTopic=='doFetch'}">
					<td width="15%" valign="bottom" class="tab" align="center" nowrap>
						<font class="tabLink">
							&nbsp;<spring:message code="systema.skat.export.created.mastertopic.tab"/>
						</font>
						<font class="text12MediumBlue">[${model.record.dkeh_syop}]</font>
						<c:if test="${ model.record.dkeh_syst == 'M' || empty  model.record.dkeh_syst || model.record.dkeh_syst == '11' || model.record.dkeh_syst == '20' || model.record.dkeh_syst == '97' || model.record.dkeh_syst == '40'}">
							<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
						</c:if>
					</td>
					
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a id="alinkInvoices" tabindex=-1 style="display:block;" href="skatexport_edit_invoice.do?action=doFetch&avd=${model.record.dkeh_syav}&sign=${model.record.dkeh_sysg}
															&opd=${model.record.dkeh_syop}&refnr=${model.record.dkeh_07}
															&status=${model.record.dkeh_syst}&datum=${model.record.dkeh_sydt}&fabl=${model.record.dkeh_222}">
							<font class="tabDisabledLink">&nbsp;<spring:message code="systema.skat.export.invoice.tab"/></font>
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a id="alinkItemLines" tabindex=-1 style="display:block;" href="skatexport_edit_items.do?action=doFetch&avd=${ model.record.dkeh_syav}&sign=${ model.record.dkeh_sysg}
													&opd=${ model.record.dkeh_syop}&refnr=${ model.record.dkeh_07}
													&status=${ model.record.dkeh_syst}&datum=${ model.record.dkeh_sydt}&fabl=${ model.record.dkeh_222}">
							<font class="tabDisabledLink">
								&nbsp;<spring:message code="systema.skat.export.item.createnew.tab"/>
							</font>
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a id="alinkLogging" tabindex=-1 style="display:block;" href="skatexport_logging.do?avd=${ model.record.dkeh_syav}&sign=${ model.record.dkeh_sysg}
													&opd=${ model.record.dkeh_syop}&refnr=${ model.record.dkeh_07}
													&status=${ model.record.dkeh_syst}&datum=${ model.record.dkeh_sydt}">
							<font class="tabDisabledLink">
								&nbsp;<spring:message code="systema.skat.export.logging.tab"/>
							</font>
							<img style="vertical-align: bottom" src="resources/images/log-icon.png" width="16" hight="16" border="0" alt="show log">
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a id="alinkArchive" tabindex=-1 style="display:block;" href="skatexport_archive.do?avd=${model.record.dkeh_syav}&sign=${model.record.dkeh_sysg}
													&opd=${model.record.dkeh_syop}&refnr=${model.record.dkeh_07}
													&status=${model.record.dkeh_syst}&datum=${model.record.dkeh_sydt}">
							<font class="tabDisabledLink">
								&nbsp;<spring:message code="systema.skat.export.archive.tab"/>
							</font>
							<img style="vertical-align: bottom" src="resources/images/archive.png" width="16" hight="16" border="0" alt="show archive">
						</a>
					</td>
					<td width="10%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
				</c:when>
				<c:otherwise>
					<td width="15%" valign="bottom" class="tab" align="center" nowrap>
						<font class="tabLink">&nbsp;<spring:message code="systema.skat.export.createnew.tab"/></font>
						<img valign="bottom" src="resources/images/add.png" width="12" hight="12" border="0" alt="create new">
						
					</td>
					<td width="70%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
				</c:otherwise>
			</c:choose>
			
			
		</tr>
	</table>
	</td>
 </tr>
 
 <tr>
 	<td>
	<%-- --------------------------- --%>	
 	<%-- tab area container PRIMARY  --%>
	<%-- --------------------------- --%>
	<form name="skatExportSaveNewTopicForm" id="skatExportSaveNewTopicForm" method="post">
	<table width="100%" class="tabThinBorderWhite" border="0" cellspacing="0" cellpadding="0">
			<%-- --- HIDDEN FORM FIELDS (not visible in form but important with an UPDATE ----- --%>			
			<%-- general (from user profile) --%>
			<input type="hidden" name="action" id="action" value='doUpdate'>
			<input type="hidden" name="applicationUser" id="applicationUser" value='${user.user}'>
			<input type="hidden" name="opd" id="opd" value='${model.record.dkeh_syop}'>
			<%-- topic specific (syop and refnr) --%>
			<input type="hidden" name="dkeh_syav" id="dkeh_syav" value='${model.record.dkeh_syav}'>
			<input type="hidden" name="dkeh_syop" id="dkeh_syop" value='${model.record.dkeh_syop}'>
			<input type="hidden" name="dkeh_sysg" id="dkeh_sysg" value='${model.record.dkeh_sysg}'>
			<input type="hidden" name="dkeh_syst" id="dkeh_syst" value='${model.record.dkeh_syst}'>
			<input type="hidden" name="dkeh_sydt" id="dkeh_sydt" value='${model.record.dkeh_sydt}'>
			<input type="hidden" name="dkeh_07" id="dkeh_07" value='${model.record.dkeh_07}'>
		<tr height="4">
			<td colspan="2">&nbsp;
				<%-- test indicator /per avdelning --%> 
				<c:forEach var="record" items="${avdListSessionTestFlag}" >
					<c:if test="${record.avd == model.record.dkeh_syav}">	
						<c:if test="${record.tst == '1'}">
							<c:set var="isTestAvd" value="1" scope="request" />
						</c:if>
					</c:if>
				</c:forEach>	
			</td>
		</tr>	
		
		<c:choose>
		<%-- UPDATE MODE --%> 
	    <c:when test="${editActionOnTopic=='doUpdate' or editActionOnTopic=='doFetch'}">
	    	<input type="hidden" name="avd" id="avd" value='${model.record.dkeh_syav}'>
			<input type="hidden" name="sign" id="sign" value='${model.record.dkeh_sysg}'>
			<%-- proforma angiv. fallback in case user väljer bort proforma. Så att man inte tappar original MRN --%>
			<input type="hidden" name="proforma_mrn" id="proforma_mrn" value='${model.record.dkeh_mrn}'>
			
			<tr >
				<td align="left" class="text12MediumBlue" >
					&nbsp;&nbsp;&nbsp;&nbsp;<span title="dkeh_syav">Afdeling:&nbsp;</span><b>${model.record.dkeh_syav}</b>&nbsp;&nbsp;<span title="dkeh_syop">Angivelse:&nbsp;</span><b>${model.record.dkeh_syop}</b>
					&nbsp;&nbsp;<span title="dkeh_07">Reference-nr:&nbsp;</span><b>${model.record.dkeh_07}</b>
					&nbsp;&nbsp;<span title="dkeh_sysg">Sign:&nbsp;</span><b>${model.record.dkeh_sysg}</b>
				</td>
				<td align="right" >
					
					<c:if test="${'1' != isTestAvd}">
						<%--This checkbox appears only in real production. Otherwise use the Testavdelning --%>
						<input type="checkbox" name="dkeh_0035" id="dkeh_0035" value="1" <c:if test="${model.record.dkeh_0035 == '1'}"> checked </c:if>  ><font class="text14MediumBlue"><b>TEST flag</b></font>&nbsp;&nbsp;&nbsp;
					</c:if>
					<a tabindex=-1 href="skatexport_edit_printTopic.do?avd=${model.record.dkeh_syav}&opd=${model.record.dkeh_syop}">
					 	<img style="vertical-align: bottom;cursor: pointer;"  src="resources/images/printer.png" width="30" hight="30" border="0" alt="Print">
					</a>
					&nbsp;&nbsp;<img title="Print delere" style="vertical-align: bottom;cursor: pointer;" id="printSkilleArkImg" width="30px" height="30px" src="resources/images/printer2.png" border="0" alt="Print delere">
					&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;<img title="Upload dokument" style="vertical-align: bottom;cursor: pointer;" id="uploadFileImg" width="25px" height="25px" src="resources/images/upload.png" border="0" alt="Upload dokument">
					&nbsp;&nbsp;&nbsp;
				</td>
			</tr>
			<tr>
				<td align="left" class="text12MediumBlue" >
					&nbsp;&nbsp;&nbsp;&nbsp;<span title="dkeh_mrn">MRN-nr:&nbsp;</span><b>${model.record.dkeh_mrn}</b>
					&nbsp;&nbsp;&nbsp;&nbsp;<span title="dkeh_sydt">Dato:&nbsp;</span><b>${model.record.dkeh_sydt}</b>
					&nbsp;&nbsp;<img onMouseOver="showPop('status_info');" onMouseOut="hidePop('status_info');" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					<span title="dkeh_syst">Stat</span><a tabindex=-1 id="updateStatusLink" runat="server" href="#"><font class="text12MediumBlue">u</font></a>s:
					<b>
						<c:choose>
							<c:when test="${empty  model.record.dkeh_syst}">
								-
							</c:when>
							<c:otherwise>
								${model.record.dkeh_syst}
							</c:otherwise>
						</c:choose>
					</b>
					<%-- test indicator /per avdelning --%> 
					<c:if test="${'1' == isTestAvd}">
						<font class="text14Red" >	
							&nbsp;&nbsp;<b>[TEST Afdeling]</b>
							<input type="hidden" name="testAvdFlag" id="testAvdFlag" value='${isTestAvd}'>
						</font>
					</c:if>
					&nbsp;&nbsp;&nbsp;<input id="updateProformaCheckbox" type="checkbox" name="dkeh_prof" id="dkeh_prof" value="1" <c:if test="${not empty model.record.dkeh_prof}"> checked </c:if> ><span title="dkeh_prof"><font class="text12MediumBlue">Proformaangivelse</font></span>
					<div id=updateProformaIcon style="display:inline;">
						<a tabindex=-1 id="updateProformaLink" runat="server" href="#">
							<img src="resources/images/update.gif" border="0" alt="edit">
						</a>
					</div>
					 
					<div class="text11" style="position: relative;" align="left">
					<span style="position:absolute;top:2px; width:250px;" id="status_info" class="popupWithInputText text11"  >	
		           		<br/>
		           		Kun status <b>M</b>,<b>11</b>,<b>20</b>eller <b>' '</b> kan redigeres. 
		           			<ul>
								<c:forEach var="record" items="${model.statusCodeList}" >
				           			<li><b>${record.dkkd_kd}&nbsp;-${record.dkkd_kd2}</b>&nbsp;${record.dkkf_txt}</li>
			           			</c:forEach>
		           			</ul>
					</span>
					</div>
					
				</td>
			</tr>
			
		</c:when>
		<%-- CREATE MODE --%> 
		<c:otherwise>
			<tr >
				<td align="left" class="text12MediumBlue">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Afdeling:&nbsp;
           			<select name="avd" id="avd" TABINDEX=1>
	            		<option value="">-vælg-</option>
	 				  	<c:forEach var="record" items="${model.avdList}" >
                       	 	<option value="${record.avd}"<c:if test="${model.record.dkeh_syav == record.avd}"> selected </c:if> >${record.avd}<c:if test="${record.tst == '1'}">&nbsp;(test)</c:if></option>
						</c:forEach> 
					</select>
					
					&nbsp;Sign:&nbsp;
           			<select name="sign" id="sign" TABINDEX=2>
	            		<option value="">-vælg-</option>
	 				  	<c:forEach var="record" items="${model.signList}" >
                             	 	<option value="${record.sign}"<c:if test="${model.record.dkeh_sysg == record.sign}"> selected </c:if> >${record.sign}</option>
						</c:forEach> 
					</select>
				</td>
				<td>&nbsp;</td>
			</tr>	
		</c:otherwise>
		</c:choose>		
		<tr height="5"><td colspan="2">&nbsp;</td></tr>
		<%-- --------------- --%>
		<%-- LEFT SIDE CELL --%>
		<%-- --------------- --%>
		<tr>
		<td width="55%">
		<table width="96%" border="0" cellspacing="0" cellpadding="0">
			<tr>
	            <td width="5">&nbsp;</td>
	            <td >
	            	<table align="left" border="0" cellspacing="0" cellpadding="0">
	            			<tr>
				 			<td class="text12">
				 				<img onMouseOver="showPop('1_1_info');" onMouseOut="hidePop('1_1_info');" style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
			 					<b>1.1</b><span title="dkeh_r011" id="v_dkeh_r011" class="validation">Ang.type&nbsp;</span>
			 					
					           	<div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="1_1_info" class="popupWithInputText text11"  >		
					           			<ul>
					           				<c:forEach var="record" items="${model.angivelsesTypeCodeList}" >
					           				<li><b>${record.dkkd_kd}</b>&nbsp;${record.dkkf_txt}</li>
				           					</c:forEach>
					           			</ul>
								</span>		
			 					</div>
				 			</td>
				 			<td>
				 				<select name="dkeh_r011" id="dkeh_r011" TABINDEX=3>
				 				  <option value="">-vælg-</option>
					 				  	<c:forEach var="record" items="${model.angivelsesTypeCodeList}" >
					 				  		<option value="${record.dkkd_kd}"<c:if test="${model.record.dkeh_r011 == record.dkkd_kd}"> selected </c:if> >${record.dkkd_kd}</option>
										</c:forEach>  
								</select>
								
			 				</td>
			 				<td class="text12" align="left">
			 				<img onMouseOver="showPop('meddTyp_info');" onMouseOut="hidePop('meddTyp_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
			 				<span title="dkeh_aart" id="v_dkeh_aart" class="validation">Ang.art&nbsp;</span>
				 			
				           	<div class="text11" style="position: relative;" align="left">
							<span style="position:absolute;top:2px; width:250px;" id="meddTyp_info" class="popupWithInputText text11"  >	
					           		<ul>
					           			<c:forEach var="record" items="${model.angivelsesArterCodeList}" >
					           			<li><b>${record.dkkd_kd}&nbsp;${record.dkkd_kd2}</b>&nbsp;${record.dkkf_txt}</li>
					           			</c:forEach>
					           		</ul>
							</span>
							</div>
							</td>
			 				<td>
				 				<select name="dkeh_aart" id="dkeh_aart" TABINDEX=10>
				 				  <option value="">-vælg-</option>
					 				  	<c:forEach var="record" items="${model.angivelsesArterCodeList}" >
					 				  		<option value="${record.dkkd_kd}"<c:if test="${model.record.dkeh_aart == record.dkkd_kd}"> selected </c:if> >${record.dkkd_kd}&nbsp;${record.dkkd_kd2}</option>
										</c:forEach>  
								</select>
				 			</td>
				 			
		 				</tr>
		 				<tr>
		 					<td class="text12" >
		 						<img onMouseOver="showPop('1_2_info');" onMouseOut="hidePop('1_2_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 				<b>1.2</b>&nbsp;
				 				<span title="dkeh_r012" id="v_dkeh_r012" class="validation">EU ang.art&nbsp;</span>
				 					
					           	<div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="1_2_info" class="popupWithInputText text11"  >	
					           		Anden underrubrik.
				           			<br/><br/>
				           			<b>EU-Angivelsesart (fält 1:2)</b>
					           		<ul>
					           			<c:forEach var="record" items="${model.euAngivelsesArterCodeList}" >
					           			<li><b>${record.dkkd_kd}</b>&nbsp;${record.dkkf_txt}</li>
					           			</c:forEach>
					           			
					           		</ul>
								</span>
								</div>
				 			</td>
				 			<td>
				 				<select name="dkeh_r012" id="dkeh_r012" TABINDEX=10>
				 				  <option value="">-vælg-</option>
				 				  	<c:forEach var="record" items="${model.euAngivelsesArterCodeList}" >
				 				  		<option value="${record.dkkd_kd}"<c:if test="${model.record.dkeh_r012 == record.dkkd_kd}"> selected </c:if> >${record.dkkd_kd}</option>
									</c:forEach>  
								</select>
								
			 				</td>
			 				<td class="text12" align="left">
			 					<img onMouseOver="showPop('ajour_info');" onMouseOut="hidePop('ajour_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 				<span title="dkeh_ajou" id="v_dkeh_ajou" class="validation">Ajour.type&nbsp;</span>
				 				<div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="ajour_info" class="popupWithInputText text11"  >	
					           			<b>Ajourføringstype</b>
					           			<ul>
					           				<li>
					           					<b>1</b> Rette tidligere angivelse.
					           				</li>
					           				<li>
					           					<b>2</b> Erstatter tidligere angivelse.
					           				</li>
					           				<li>
					           					<b>6</b> Faktisk ankomst til udpassagested.
					           				</li>
					           				<li>
					           					<b>7</b> Fakturaoplysning.
					           				</li>	
											<li>
												<b>8</b> Fastholder tidligere angivelse efter sandsynlig fejl.
											</li>
											<li>
												<b>9</b> Ajourfør tidligere angivelse med faktisk ekspeditionstidspunkt.
											</li>
										</ul>
								</span>
								</div>
				 			</td>
				 			<td>	
				 				<select name="dkeh_ajou" id="dkeh_ajou" >
			 					  <option value="" <c:if test="${ empty model.record.dkeh_ajou }"> selected </c:if> >-vælg-</option>
			 					  <option value="1"<c:if test="${ model.record.dkeh_ajou == '1'}"> selected </c:if> >1</option>
								  <option value="2"<c:if test="${ model.record.dkeh_ajou == '2'}"> selected </c:if> >2</option>
								  <option value="6"<c:if test="${ model.record.dkeh_ajou == '6'}"> selected </c:if> >6</option>
								  <option value="7"<c:if test="${ model.record.dkeh_ajou == '7'}"> selected </c:if> >7</option>
								  <option value="8"<c:if test="${ model.record.dkeh_ajou == '8'}"> selected </c:if> >8</option>
								  <option value="9"<c:if test="${ model.record.dkeh_ajou == '9'}"> selected </c:if> >9</option>
						  		</select>
			 				</td>		 				
		 				</tr>
		 				<tr>
							<td class="text12" align="left">
								<img onMouseOver="showPop('ekspedsted_info');" onMouseOut="hidePop('ekspedsted_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
								<b>A.1</b><span title="dkeh_a1" id="v_dkeh_a1" class="validation">Eksped.sted&nbsp;</span>
					           	<div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="ekspedsted_info" class="popupWithInputText text11"  >	
					           			<b>Eksped.sted</b>
					           			<br/><br/>
					           			Ekspeditionssted, hvor varerne skal frigives til eksport.
					           			<br/><br/>
					           			Toldekspeditionssted eller godkendt eksportør.
										<br/><br/>
										Toldekspeditionssted for den godkendte eksportør er oplyst i bevillingen.
								</span>
								</div>
			 				</td>
			 				<td class="text12">
			            			<select name="dkeh_a1" id="dkeh_a1">
					            		<option value="">-vælg-</option>
					 				  	<c:forEach var="record" items="${model.toldstedCodeList}" >
					 				  		<option value="${record.dkkd_kd}"<c:if test="${model.record.dkeh_a1 == record.dkkd_kd}"> selected </c:if> >${record.dkkd_kd}</option>
										</c:forEach>  
								</select>
								<a tabindex="-1" id="dkeh_a1IdLink" >
									<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
								</a>
			 				</td>
			 				<td align="left" class="text12">
			 					<img onMouseOver="showPop('ekspednr_info');" onMouseOut="hidePop('ekspednr_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
								<b>A.2</b>&nbsp;<span title="dkeh_a2" id="v_dkeh_a2" class="validation">Eksped.nr.&nbsp;</span>
								<div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="ekspednr_info" class="popupWithInputText text11"  >	
					           			<b>Eksped.nummer</b>
					           			<br/><br/>
					           			Ekspeditionsnummeret er et unikt løbenummer med tretten cifre, som indeholder 
					           			<ul>
					           				<li>Oplysninger om år</li>
					           				<li>Angivelsestype (Export = <b>2</b>)</li>
					           				<li>Et ottecifret løbenummer</li>
					           			</ul>
					           			<br/><br/>
					           			Eksempel:
					           			<br/><br/>
					           			2014<b>2</b>12345678
								</span>
								</div>
				 			</td>
				 			<td align="left" >
				 				<input type="text" class="inputTextMediumBlue" name="dkeh_a2" id="dkeh_a2" size="16" maxlength="13" value="${model.record.dkeh_a2}">
			 				</td>
		 				</tr>
		 				<tr>
		 					<td class="text12" >
		 						<img onMouseOver="showPop('dkeh_dtm1_info');" onMouseOut="hidePop('dkeh_dtm1_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 				<span title="dkeh_dtm1" id="v_dkeh_dtm1" class="validation">Forventet eksp.tid&nbsp;</span>
				 				<div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="dkeh_dtm1_info" class="popupWithInputText text11"  >
					           			<b>Forventet ekspeditionstidspunkt</b>
					           			<br/><br/>
					           			Forventet ankomst til toldekspeditionssted eller forventet indlæsningstid hos den godkendte eksportør.
										Dato (år måned dato) og klokkeslæt (timer/minutter).
								</span>
								</div>
				 			</td>
							<td class="text12" align="left">
			 					<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="dkeh_dtm1" id="dkeh_dtm1" size="16" maxlength="12" value="${model.record.dkeh_dtm1}">
		            			</td>
		            			

				 			<td class="text12" >
		 						<img onMouseOver="showPop('dkeh_dtm2_info');" onMouseOut="hidePop('dkeh_dtm2_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 				<span title="dkeh_dtm2" id="v_dkeh_dtm2" class="validation">Faktisk eksp.tid&nbsp;</span>
				 				<div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="dkeh_dtm2_info" class="popupWithInputText text11"  >
					           			<b>Faktisk ekspeditionstidspunkt</b>
					           			<br/><br/>
					           			Faktisk ekspeditionstid på et toldekspeditionssted eller tidspunktet, hvor den godkendte eksportør har overholdt sin forudanmeldelsestid.
										Dato (år måned dato) og klokkeslæt (timer/minutter).
								</span>
								</div>
			 				</td>
			 				<td class="text12" align="left">
			            			<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="dkeh_dtm2" id="dkeh_dtm2" size="16" maxlength="12" value="${model.record.dkeh_dtm2}">
		            			</td>
		 				</tr>	
		 				<tr height="2"><td></td></tr>
			 			<tr>
							<td align="left" class="text12Gray">&nbsp;
		 						<span title="dkeh_fast_dummy">&nbsp;&nbsp;&nbsp;Faktisk.utp.sted&nbsp;</span>
				 			</td>
				 			<td colspan="2">
				 				<input readonly type="text" class="inputTextReadOnly" name="dkeh_fast_dummy" id="dkeh_fast_dummy" size="30" maxlenght="25" value="${model.record.dkeh_fast}">
			 				</td>			
			 				<td class="text12" align="left" >
					            <img onMouseOver="showPop('29_info');" onMouseOut="hidePop('29_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">	
					            <b>29.</b>&nbsp;<span title="dkeh_29" id="v_dkeh_29" class="validation">Udg.toldsted</span>
					            <div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="29_info" class="popupWithInputText text11"  >
					           			<b>29. Udgangstoldsted</b>
					           			<br/><br/>
					           			Koden for det toldsted, hvorfra varerne forventes at udpassere af EU's toldområde eller hvor forsendelsesordning begyndes.
										<br/><br/>
										Udpasserer varerne som postforsendelse eller med jernbane, anføres henholdsvis kode DK000010 og DK000020.<br/><br/>
					           			<br/><br/>
								</span>	
								</div>
							</td>	 		
			 			</tr>
			 			<tr height="2"><td></td></tr>
			 			<tr>
							<td align="left" class="text12Gray">&nbsp;
		 						<span title="dkeh_xref">&nbsp;&nbsp;&nbsp;Ekstern ref.&nbsp;</span>
				 			</td>
				 			<td colspan="2">
				 				<input type="text" class="inputText" name="dkeh_xref" id="dkeh_xref" size="30" maxlenght="35" value="${model.record.dkeh_xref}">
			 				</td>
			 				
				            <td >
				            		<select id="dkeh_29" name="dkeh_29" >
				            		<option value="">-vælg-</option>
			 				  	<c:forEach var="record" items="${model.udgangstoldstedCodeList}" >
			 				  		<option value="${record.dkkd_kd}"<c:if test="${model.record.dkeh_29 == record.dkkd_kd}"> selected </c:if> >${record.dkkd_kd}</option>
								</c:forEach>
								</select>
								<a tabindex="-1" id="dkeh_29IdLink" >
									<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
								</a>									
				            </td>			 		
			 			</tr> 
	 				</table>
 				</td>
	 		</tr>
	 		<tr height="5"><td></td></tr>
	 		

			<%-- Validation errors --%>
			<spring:hasBindErrors name="record"> <%-- name must equal the command object name in the Controller --%>
			<tr>
				<td width="5">&nbsp;</td>
				<td>
	            	<table align="left" border="0" cellspacing="0" cellpadding="0">
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
			
			<%-- Other errors (none validation errors) --%>
			<c:if test="${not empty model.errorMessage}">
			<tr>
				<td width="5">&nbsp;</td>
				<td >
	            	<table align="left" border="0" cellspacing="0" cellpadding="0">
				 		<tr>
				 			<td class="textError">
				 				<ul>
                                    <li>
                                    		Fel vid uppdatering/kopiering. [ERROR/WARN] ${model.errorMessage} 
                                    </li>
                                    <li>
                                      	[META-INFO: ${model.errorInfo}]  
                                    </li>
                                </ul>
				 			</td>
						</tr>
					</table>
				</td>
			</tr>
			</c:if>
			
			<%-- INVOICE AMOUNT Fields --%>
			<tr height="5"><td></td></tr>
			<tr>
				<td width="5">&nbsp;</td>
	            <td >
					<%-- Special section --%>
					<table align="left" class="formFrameHeader" width="100%" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="15">
				 			<td class="text12White">
				 				<b>&nbsp;22.</b>&nbsp;&nbsp;Faktura&nbsp;<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
			 				</td>
		 				</tr>
	 				</table>
	 			</td>
 			</tr>
            <tr>
	            <td width="5">&nbsp;</td>
	            <td >
	                <table class="formFrame" width="100%" align="left" border="0" cellspacing="1" cellpadding="1">
				 		<tr>
				 			<td class="text12">
				 				<b>&nbsp;22.2</b>&nbsp;
				 				<span title="dkeh_222" id="v_dkeh_222" class="validation">Fakturabeløb&nbsp;</span>
				 			</td>
				 			<td align="left" >
				 				<input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue" name="dkeh_222" id="dkeh_222" size="20" maxlength="19" value="${model.record.dkeh_222}">
				 			</td>
				 			<td class="text12">
				 				<b>&nbsp;22.1</b>&nbsp;
				 				<span title="dkeh_221" id="v_dkeh_221" class="validation">Fakuramøntsort</span>
				 				<%-- Note: onChange event in jQuery for this currency list --%>
				 				<select name="dkeh_221" id="dkeh_221" >
				 				  <option value="">-vælg-</option>	
				 				  <c:forEach var="currency" items="${model.currencyCodeList}" >
			 				  		<option value="${currency.dkkd_kd}"<c:if test="${ model.record.dkeh_221 == currency.dkkd_kd}"> selected </c:if> >${currency.dkkd_kd}</option>
								  </c:forEach>  
								</select>
								<a tabindex="-1" id="dkeh_221IdLink" >
									<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
								</a>																	 			
								
			 				</td>
		 				</tr>
		 				<tr>
			 				<td class="text12" align="left">
				 				<span title="dkeh_221b" id="v_dkeh_221b" class="validation">&nbsp;Kurs&nbsp;</span>
				 			</td>
				 			<td class="text12" align="left" ><input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue" name="dkeh_221b" id="dkeh_221b" size="20" maxlength="20" value="${model.record.dkeh_221b}"></td>
				 			<td class="text12" align="left" >&nbsp;
					 			<span title="dkeh_221c">Faktor</span>
					 			<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="dkeh_221c" id="dkeh_221c" size="8" maxlength="7" value="${model.record.dkeh_221c}">
					 			&nbsp;<button title="Hente summen fra Fakturaer" name="getFakturaListSum2Button" id="getFakturaListSum2Button" class="buttonGrayWithGreenFrame" type="button" >Hente summen</button>
				 			</td>
		 				</tr>
		 				<tr height="2"><td></td></tr>
					</table>
					</td>
			</tr>
			<tr height="10"><td></td></tr>
 			<tr>
	 			<td width="5">&nbsp;</td>
	            <td >		
	 				<%-- SENDER --%>
	 				<table width="100%" align="left" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="15">
				 			<td class="text12White">
								&nbsp;<img onMouseOver="showPop('2_info');" onMouseOut="hidePop('2_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					 			<b>&nbsp;2.</b>Afsender/Eksportør&nbsp;<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
				 				<div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="2_info" class="popupWithInputText text11"  >
					           		<b>Afsender / Eksportør</b>
					           		<ol>
					           			<li><b>Afsenders navn</b>
										Det fuldstændige navn på den afsender/eksportør, der har udstedt fakturaen.
										</li>
										<li><b>Afsenders adresse</b>
										Den fuldstændige adresse på den afsender/eksportør, der har udstedt fakturaen.
										</li>
									</ol>	
								</span>
								</div>
			 				</td>
		 				</tr>
	 				</table>
	 			</td>
	 		</tr>
	 		<tr>	
	 			<td width="5">&nbsp;</td>
	            <td >	
					<%-- create record --%>
				 	<table width="100%" align="left" class="formFrame" border="0" cellspacing="0" cellpadding="0">
				 		<tr>
					 		<td width="100%">
						 		<table width="100%" border="0" cellspacing="0" cellpadding="0">
							 		<tr height="10"><td ></td></tr>
							        
							        <tr>
							        	<%-- ================================================================================== --%>
							        	<%-- This hidden values are used when an AJAX event from within a dialog box is fired.  
							        		 These original values will be used when the user clicks "Cancel" buttons (puttting
							        		 back original value)																--%> 
							        	<%-- ================================================================================== --%>
							        	<input type="hidden" name="orig_dkeh_avkn" id="orig_dkeh_avkn" value='${model.record.dkeh_avkn}'>
							        	<input type="hidden" name="orig_dkeh_02a" id="orig_dkeh_02a" value='${model.record.dkeh_02a}'>
							        	<input type="hidden" name="orig_dkeh_02b" id="orig_dkeh_02b" value='${model.record.dkeh_02b}'>
							        	<input type="hidden" name="orig_dkeh_02c" id="orig_dkeh_02c" value='${model.record.dkeh_02c}'>
							        	<input type="hidden" name="orig_dkeh_02d" id="orig_dkeh_02d" value='${model.record.dkeh_02d}'>
							        	<input type="hidden" name="orig_dkeh_02e" id="orig_dkeh_02e" value='${model.record.dkeh_02e}'>
							        	<input type="hidden" name="orig_dkeh_02f" id="orig_dkeh_02f" value='${model.record.dkeh_02f}'>
							        	
							        	
							        	
							            <td class="text12" align="left" >&nbsp;&nbsp;
							            <span title="dkeh_avkn">Kundenummer</span></td>
							            <td class="text12" align="left" >&nbsp;&nbsp;<span title="dkeh_02b">Navn&nbsp;</span>
							            	<a tabindex="-1" id="dkeh_02bIdLink">
											<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
											</a>
							            </td>
							        </tr>
							        <tr>
							            <td class="text12" align="left"><input type="text" class="inputTextMediumBlue" name="dkeh_avkn" id="dkeh_avkn" size="8" maxlength="8" value="${model.record.dkeh_avkn}"></td>
							            <td class="text12" align="left"><input type="text" class="inputTextMediumBlue" name="dkeh_02b" id="dkeh_02b" size="30" maxlength="35" value="${model.record.dkeh_02b}"></td>
							            
							        </tr>
							        
							        <tr>
							            <td class="text12" align="left" >&nbsp;&nbsp;<span title="dkeh_02a" id="v_dkeh_02a" class="validation">CVR/SE-nr</span></td>
							            <td class="text12" align="left" >&nbsp;&nbsp;</td>
							        </tr>
							        <tr>
							            <td align="left"><input type="text" class="inputTextMediumBlue" name="dkeh_02a" id="dkeh_02a" size="20" maxlength="10" value='${model.record.dkeh_02a}'></td>
							            <td align="left">&nbsp;</td>
							        </tr>
							         
							        <tr height="4"><td>&nbsp;</td></tr>
							        <tr>
							            <td class="text12" align="left" >&nbsp;&nbsp;
							            <span title="dkeh_02c">Adresse</span></td>
							            <td class="text12" align="left" >&nbsp;&nbsp;
						            		<span title="dkeh_02d">Postnummer</span></td>
							        </tr>
							        <tr>
							            <td align="left"><input type="text" class="inputTextMediumBlue" name="dkeh_02c" id="dkeh_02c" size="30" maxlength="35" value="${model.record.dkeh_02c}"></td>
							            <td align="left"><input type="text" class="inputTextMediumBlue" name="dkeh_02d" id="dkeh_02d" size="10" maxlength="9" value="${model.record.dkeh_02d}"></td> 
									            		
							        </tr>
							        <tr>
								        	<td>
								        		<table>
									        		<tr>
									            		<td class="text12" align="left" >&nbsp;&nbsp;
									            		<span title="dkeh_02e">By</span></td>
									            		<td class="text12" >&nbsp;</td>
									            	</tr>
									        		<tr>
									            		<td align="left" colspan="2">
									            			<input type="text" class="inputTextMediumBlue" name="dkeh_02e" id="dkeh_02e" size="30" maxlength="35" value="${model.record.dkeh_02e}">
									            		</td> 
									            		<td class="text12" >&nbsp;</td>
									        		</tr>    	
								            	</table>
							            </td>
							            <td>
								            	<table>
									        		<tr>
									            		
									            		<td class="text12" align="left" >&nbsp;&nbsp;
									            		<span title="dkeh_02f">Land</span>
																														 			
									            		</td>
									            		
									            	</tr>
									        		<tr>
									        			<td align="left">
									            			<select name="dkeh_02f" id="dkeh_02f">
											            		<option value="">-vælg-</option>
											 				  	<c:forEach var="country" items="${model.countryCodeList}" >
											 				  		<option value="${country.dkkd_kd}"<c:if test="${model.record.dkeh_02f == country.dkkd_kd}"> selected </c:if> >${country.dkkd_kd}</option>
																</c:forEach>  
	
															</select>
															<a tabindex="-1" id="dkeh_02fIdLink"  OnClick="triggerChildWindowCountryCodes(this)">
																<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
															</a>
														</td>
									            		<td align="left">&nbsp;</td> 
									        		</tr>    	
								            	</table>
							            </td>
							        </tr>
							        
							        <tr height="15">
							            <td class="text12Bold" align="left" >&nbsp;</td> 
							        </tr>
						        </table>
					        </td>
				        </tr>
					</table>          
            	</td>
           	</tr> 
           	<tr height="10"><td></td></tr>
           	
           	<%-- RECEIVER --%>
	 		<tr>
	 			<td width="5">&nbsp;</td>
	            <td >		
	 				<table width="100%" align="left" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="15">
				 			<td class="text12White">
				 				&nbsp;<img onMouseOver="showPop('8_info');" onMouseOut="hidePop('8_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 				<b>&nbsp;8.</b>Modtager&nbsp;<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
				 				<div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="8_info" class="popupWithInputText text11"  >
						           		<b>Modtager</b>
						           		<ol>
						           			<li>Rubrikken skal indeholde oplysning om modtagerens navn og adresse.</li>
											
										</ol>
								</span>
								</div>
			 				</td>
		 				</tr>
	 				</table>
	 			</td>
	 		</tr>
	 		<tr>	
	 			<td width="5">&nbsp;</td>
	            <td >	
					<%-- create record --%>
				 	<table width="100%" align="left" class="formFrame" border="0" cellspacing="0" cellpadding="0">
				 		<tr>
					 		<td width="100%">
						 		<table width="100%" border="0" cellspacing="0" cellpadding="0">
							 		<tr height="10"><td ></td></tr>
							        
							        <tr>
							        	<%-- ================================================================================== --%>
							        	<%-- This hidden values are used when an AJAX event from within a dialog box is fired.  
							        		 These original values will be used when the user clicks "Cancel" buttons (puttting
							        		 back original value)																--%> 
							        	<%-- ================================================================================== --%>
							        	<input type="hidden" name="orig_dkeh_mokn" id="orig_dkeh_avkn" value="${model.record.dkeh_mokn}">
							        	<input type="hidden" name="orig_dkeh_08a" id="orig_dkeh_08a" value="${model.record.dkeh_08a}">
							        	<input type="hidden" name="orig_dkeh_08b" id="orig_dkeh_08b" value="${model.record.dkeh_08b}">
							        	<input type="hidden" name="orig_dkeh_08c" id="orig_dkeh_08c" value="${model.record.dkeh_08c}">
							        	<input type="hidden" name="orig_dkeh_08d" id="orig_dkeh_08d" value="${model.record.dkeh_08d}">
							        	<input type="hidden" name="orig_dkeh_08e" id="orig_dkeh_08e" value="${model.record.dkeh_08e}">
							        	<input type="hidden" name="orig_dkeh_08f" id="orig_dkeh_08f" value="${model.record.dkeh_08f}">
							        	
							            <td class="text12" align="left" >&nbsp;&nbsp;
							            <span title="dkeh_mokn">Kundenummer</span></td>
							            <td class="text12" align="left" >&nbsp;&nbsp;<span title="dkeh_08b" id="v_dkeh_08b" class="validation">Navn</span>&nbsp;
							            	<a tabindex="-1" id="dkeh_08bIdLink">
											<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
											</a>
										</td>
							        </tr>
							        <tr>
							            <td align="left"><input type="text" class="inputTextMediumBlue" name="dkeh_mokn" id="dkeh_mokn" size="8" maxlength="8" value="${model.record.dkeh_mokn}"></td>
							            <td align="left"><input type="text" class="inputTextMediumBlue" name="dkeh_08b" id="dkeh_08b" size="30" maxlength="35" value="${model.record.dkeh_08b}"></td>
							        </tr>
							        <tr>
							            <td class="text12" align="left" >&nbsp;&nbsp;
							            <span title="dkeh_08a">EORI</span></td>
							            <td class="text12" align="left" >&nbsp;&nbsp;</td>
							        </tr>
							        <tr>
							            <td align="left"><input type="text" class="inputTextMediumBlue" name="dkeh_08a" id="dkeh_08a" size="20" maxlength="17" value="${model.record.dkeh_08a}"></td>
							            <td align="left">&nbsp;</td>
							        </tr>
							        <tr height="4"><td>&nbsp;</td></tr>
							        <tr>
							            <td class="text12" align="left" >&nbsp;&nbsp;
							            <span title="dkeh_08c" id="v_dkeh_08c" class="validation">Adresse</span></td>
							            <td class="text12" align="left" >&nbsp;&nbsp;
						        			<span title="dkeh_08d" id="v_dkeh_08d" class="validation">Postnummer</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
								            		
    							        </tr>
							        <tr>
							            <td align="left"><input type="text" class="inputTextMediumBlue" name="dkeh_08c" id="dkeh_08c" size="30" maxlength="35" value="${model.record.dkeh_08c}"></td>
							            <td align="left"><input type="text" class="inputTextMediumBlue" name="dkeh_08d" id="dkeh_08d" size="10" maxlength="9" value="${model.record.dkeh_08d}"></td> 
								            		
							        </tr>
							        <tr>
							        	<td>
							        		<table>
								        		<tr>
								            		<td class="text12" align="left" >&nbsp;
								            		<span title="dkeh_08e" id="v_dkeh_08e" class="validation">By</span></td>
												<td align="left">&nbsp;</td>
								            	</tr>
								        		<tr>
								            		<td align="left" colspan="2"><input type="text" class="inputTextMediumBlue" name="dkeh_08e" id="dkeh_08e" size="30" maxlength="35" value="${model.record.dkeh_08e}"></td> 
								            		
								        		</tr>    	
							            	</table>
						            </td>
						            <td>
							            	<table>
								        		<tr>
								        			<td class="text12" align="left" >&nbsp;&nbsp;
								            		<span title="dkeh_08f" id="v_dkeh_08f" class="validation">Land</span>
																													 			
												</td>
								            	</tr>
								        		<tr>
								        			<td align="left">
								            			<select name="dkeh_08f" id="dkeh_08f">
										            		<option value="">-vælg-</option>
										 				  	<c:forEach var="country" items="${model.countryCodeList}" >
										 				  		<option value="${country.dkkd_kd}"<c:if test="${model.record.dkeh_08f == country.dkkd_kd}"> selected </c:if> >${country.dkkd_kd}</option>
															</c:forEach>  
													</select>
													<a tabindex="-1" id="dkeh_08fIdLink"  OnClick="triggerChildWindowCountryCodes(this)">
														<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
													</a>
								            		</td> 
								        		</tr>
							            	</table>
						            </td>
							        </tr>
							        <tr height="15">
							            <td class="text12Bold" align="left" >&nbsp;</td> 
							        </tr>
							        
						        </table>
					        </td>
				        </tr>
					</table>          
            	</td>
           	</tr> 
           	<tr height="10"><td></td></tr>
           	<tr>
	 			<td width="5">&nbsp;</td>
	            <td >		
	 				<%-- OMBUD --%>
	 				<table width="100%" align="left" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="18px">
				 			<td class="text12White">
				 				&nbsp;<img onMouseOver="showPop('14_b_info');" onMouseOut="hidePop('14_b_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
		 						<b>&nbsp;14.</b>Klarereren / Repræsentant&nbsp;<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
				 				<div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="14_b_info" class="popupWithInputText text11"  >
				           			<b>14.&nbsp;Klarereren/repræsentanten</b> er den juridiske person, der afgiver fortoldningen.<br/><br/>
									I rubrikken anføres oplysning om klarererens/repræsentantens <b>Repr. CVR/SE-nr.</b>, der skal angives med foranstillet ISO landekode DK
									
								</span>
								</div>
			 				</td>
		 				</tr>
	 				</table>
	 			</td>
	 		</tr>
	 		<tr>
	 			<td width="5">&nbsp;</td>
	            <td >	
					<%-- create record --%>
				 	<table width="100%" align="left" class="formFrame" border="0" cellspacing="0" cellpadding="0">
				 		<tr>
					 		<td>
						 		<table width="100%" border="0" cellspacing="0" cellpadding="0">
							 		<tr height="15">
							            <td class="text12" align="left">&nbsp;</td> 
							        </tr>
							        <tr>
							            <td class="text12" align="left" >&nbsp;&nbsp;
							            <span title="dkeh_14a">Repr. CVR/SE-nr.</span></td>
							            
							            <td class="text12" align="left" >&nbsp;&nbsp;
							            <span title="dkeh_14b">Repræsentationsstatus</span></td>
							             
							        </tr>
							        <tr>
							            <td align="left"><input type="text" class="inputTextMediumBlue" name="dkeh_14a" id="dkeh_14a" size="19" maxlength="10" value="${model.record.dkeh_14a}"></td>
							            
							            <td align="left">
							            	<select name="dkeh_14b" id="dkeh_14b">
			 									<option value="">-vælg-</option>
							  					<option value="1"
								  				<c:if test="${model.record.dkeh_14b == '1'}"> selected </c:if> >1-Klarerer</option>
								  				<option value="2"
								  				<c:if test="${model.record.dkeh_14b == '2'}"> selected </c:if> >2-Direkte repr.</option>
								  				<option value="3"
								  				<c:if test="${model.record.dkeh_14b == '3'}"> selected </c:if> >3-Indirekte repr.</option>
											</select>
										</td>
										
							        </tr>
							       
						        </table>
					      	</td>
						 </tr>
						 <tr height="20"><td></td></tr>
				 	</table>
            		</td>
           	</tr>
           	<tr height="20"><td></td></tr>
           	<tr>
				<td width="5">&nbsp;</td>
	            <td >
					<%-- Special section --%>
					<table align="left" class="formFrameHeader" width="100%" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="15">
				 			<td class="text12White">
				 				<b>&nbsp;44.</b>&nbsp;Øvrige supplerende oplysninger&nbsp;/&nbsp;Certifikat&nbsp;<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
			 				</td>
		 				</tr>
	 				</table>
	 			</td>
 			</tr>
 			<tr>
	 			<td width="5">&nbsp;</td>
	            <td >
	 				<%-- create record --%>
				 	<table align="left" class="formFrame" width="100%" border="0" cellspacing="0" cellpadding="0">
				 		<tr>
					 		<td>
						 		<table align="left" border="0" cellspacing="0" cellpadding="0">
							 		<tr height="15">
							            <td class="text12Bold" align="left" >&nbsp;</td> 
							        </tr>
							        <tr>
							            <td class="text12" align="left" >&nbsp;
							            <span title="dkeh_28b">Fakt.nr.&nbsp;</span></td>
							            <td ><input type="text" class="inputTextMediumBlue" name="dkeh_28b" id="dkeh_28b" size="18" maxlength="17" value='${ model.record.dkeh_28b}'></td>
							            <td class="text12" align="left" >&nbsp;&nbsp;&nbsp;
							            <img onMouseOver="showPop('faktTyp_info');" onMouseOut="hidePop('faktTyp_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
						 				<span title="dkeh_28a">Fakt.type&nbsp;</span>
							            <div class="text11" style="position: relative;" align="left">
										<span style="position:absolute;top:2px; width:250px;" id="faktTyp_info" class="popupWithInputText text11"  >
						           			<font class="text12">
						           			<ul>
							           			<li><b>N380</b> handelsfaktura</li>
							           			<li><b>N325</b> proformafaktura</li>
							           		</ul>
							           		</font>
										</span>
										</div>
										</td>
							            <td>
							 				<select name="dkeh_28a" id="dkeh_28a">
							 					<option value="">-vælg-</option><option value="N380"<c:if test="${ model.record.dkeh_28a == 'N380'}"> selected </c:if> >N380</option>
											  	<option value="N325"<c:if test="${ model.record.dkeh_28a == 'N325'}"> selected </c:if> >N325</option>
											</select>
			 							</td>
			 							<td class="text12">&nbsp;&nbsp;<span title="dkeh_28c">Fakt.dato</span>
			 								<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="dkeh_28c" id="dkeh_28c" size="9" maxlength="8" value="${model.record.dkeh_28c}">
			 							</td>
							        </tr>
							        <tr height="5">
							            <td class="text12Bold" align="left" >&nbsp;</td> 
							        </tr>
							        <tr>
							            <td colspan="5" class="text12" align="left" >&nbsp;
							            <span title="fakturaListTotSum/fakturaListTotValidCurrency">Fakturasum. fra Fakturaer&nbsp;</span>
							            <input readonly type="text" class="inputTextReadOnly"  name="fakturaListTotSum" id="fakturaListTotSum" size="15" value='${ model.record.fakturaListTotSum}'>
							            &nbsp;&nbsp;
							            <input readonly type="text" class="inputTextReadOnly"  name="fakturaListTotValidCurrency" id="fakturaListTotValidCurrency" size="5" value='${ model.record.fakturaListTotValidCurrency}'>
							            &nbsp;<button title="Hente summen fra Fakturaer" name="getFakturaListSumButton" id="getFakturaListSumButton" class="buttonGrayWithGreenFrame" type="button" >Hente summen</button>
							            <input type="hidden" name="fakturaListTotKurs" id="fakturaListTotKurs" value='${ model.record.fakturaListTotKurs}'>
							            <input type="hidden" name="fakturaListTotFaktnr" id="fakturaListTotFaktnr" value='${ model.record.fakturaListTotFaktnr}'>
							            </td>
							        </tr>
							         
							        <tr height="5">
							            <td class="text12Bold" align="left" >&nbsp;</td> 
							        </tr>
								</table>
							</td>
						</tr>
					</table>
					  
				</td>
		  	</tr>
		  	
		  	
           	</table>
		</td>
		<%-- --------------- --%>
		<%-- RIGHT SIDE CELL --%>
		<%-- --------------- --%>
		<td width="50%" align="center" valign="top">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="2">&nbsp;</td>
					<td valign="top">
			 			<table border="0" cellspacing="0" cellpadding="0">
			 				
			 				<tr >	
			            		<td colspan="4" class="text9BlueGreen" valign="bottom" align="left" >
								<%-- only status = M or emtpy status is allowed --%>
			 				    <c:choose>
				 				    <c:when test="${ model.record.dkeh_syst == 'M' || empty  model.record.dkeh_syst || model.record.dkeh_syst == '11' || model.record.dkeh_syst == '20' || model.record.dkeh_syst == '97' || model.record.dkeh_syst == '40'}">
					 				    	<input class="inputFormSubmit" type="submit" name="submit" id="submit" onclick="javascript: form.action='skatexport_edit.do';" value='<spring:message code="systema.skat.export.createnew.submit"/>'/>
					 				    	&nbsp;&nbsp;
					 				    	<c:if test="${not empty  model.record.dkeh_syop && model.record.validUpdate}"> 
					 				    		<input tabindex=-2 class="inputFormSubmit" type="submit" name="send" id="send" onclick="javascript: form.action='skatexport_send.do';" value='<spring:message code="systema.skat.export.createnew.send"/>'/>
					 				    	</c:if>
				 				    </c:when>
				 				    <c:otherwise>
				 				    		<input disabled class="inputFormSubmitGrayDisabled" type="submit" name="submit" value='<spring:message code="systema.skat.submit.not.editable"/>'/>
				 				    </c:otherwise>	
			 				    </c:choose>
		 				    
                				</td>
					        </tr>
				            
			 				<tr height="12"><td class="text"></td> </tr>
					 		<tr>
					            <td class="text12" align="left" >
					            <img onMouseOver="showPop('17_a_info');" onMouseOut="hidePop('17_a_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
			 					<b>17a.</b><span title="dkeh_17a" id="v_dkeh_17a" class="validation">Bestem.land</span>&nbsp;
					            <div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="17_a_info" class="popupWithInputText text11"  >
					           			<b>17a. Bestemmelsesland</b>
					           			<br/><br/>
					           			Koden for bestemmelseslandet. Koderne fremgår af landefortegnelsen i bilag 37. Eller se kodetabeller i e-Export under virksomheder Told - Erhverv på http://www.skat.dk/
					           			<br/><br/>
								</span>	
								</div>
								</td>	
					            <td >
					            	<select name="dkeh_17a" id="dkeh_17a">
				 						<option value="">-vælg-</option>
					 				  	<c:forEach var="country" items="${model.countryCodeList}" >
					 				  		<option value="${country.dkkd_kd}"<c:if test="${model.record.dkeh_17a == country.dkkd_kd}"> selected </c:if> >${country.dkkd_kd}</option>
										</c:forEach>  
									</select>
									<a tabindex="-1" id="dkeh_17aIdLink"  OnClick="triggerChildWindowCountryCodes(this)">
										<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
									</a>
									<%-- info span 
									<img onClick="showPop('bestemmelseslandInfo');" tabindex=-1 style="cursor:pointer;" src="resources/images/find.png" border="0" alt="search" >
									<span style="position:absolute; left:800px; top:150px; width:350px; height:150px;" id="bestemmelseslandInfo" class="popupWithInputText"  >
						           		<div class="text10" align="left">
					           				<select class="text11" id="bestemmelsesland" name="bestemmelsesland" size="5" onDblClick="hidePop('bestemmelseslandInfo');">
						           				<c:forEach var="country" items="${model.countryCodeList}" >
						 				  			<option value="${country.dkkd_kd}">${country.dkkd_kd}&nbsp;${country.dkkf_txt}</option>
												</c:forEach>
						           			</select>
						           			
											<table width="100%" align="left" border="0">
												<tr height="10">&nbsp;<td class="text11">&nbsp;</td></tr>
												<tr align="left" >
													<td class="text11">&nbsp;<button name="bestemmelseslandButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('bestemmelseslandInfo');">&nbsp;<spring:message code="systema.skat.export.ok"/></button> 
													</td>
												</tr>
											</table>
										</div>
									</span>
									--%>
								</td>
							</tr>
							
							<tr height="4"><td class="text"></td> </tr>
							<tr>
					            <td class="text12" align="left" >
					            <img onMouseOver="showPop('18_1_info');" onMouseOut="hidePop('18_1_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
			 					<b>18.1</b>&nbsp;<span title="dkeh_181" id="v_dkeh_181" class="validation">Transp.ID</span>
			 					<div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="18_1_info" class="popupWithInputText text11"  >
					           			<b>18.1 Transportmidlets identitet ved afgang</b>
					           			<br/><br/>
					           			Rubrikken udfyldes ikke ved udførsel med post, jernbane eller i faste installationer
					           			<br/><br/>
					           			Her angives identiteten for det transportmiddel, fx anhænger, bil eller skib, som varerne indlades på. 
					           			Identiteten er normalt enten registreringsnummer eller navn.
					           			<br/><br/>
								</span>	
								</div>	
					            </td>
				                 <td >
						            	<input type="text" class="inputTextMediumBlue" name="dkeh_181" id="dkeh_181" size="21" maxlength="20" value="${model.record.dkeh_181}">
								</td>
							</tr>
							
						</table>
					</td>
				</tr>
				<tr>
					<td width="2">&nbsp;</td>
			 		<td>
						<table border="0" cellspacing="1" cellpadding="0">	
					 		<tr>
					            <td class="text12" align="left">
					            <img onMouseOver="showPop('21_info');" onMouseOut="hidePop('21_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					            <b>21</b><span title="dkeh_21">&nbsp;Transportmidlets nationalitet ved udførsel&nbsp;</span>
					            
					            <div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="21_info" class="popupWithInputText text11"  >
					           			<b>21 Transportmidlets nationalitet ved udførsel</b>&nbsp;
					           			(Grænseoverskridende aktive transportmiddels nationalitet)
					           			<br/><br/>
					           			Rubrikken udfyldes ikke ved udførsel med post, jernbane eller i faste installationer.
					           			<br/><br/>
					           			Når det drejer sig om kombineret transport, eller når der anvendes flere transportmidler, anføres som aktivt transportmiddel det, der tjener til fremdriften, 
					           			fx skibet, hvis transportformen er lastbil på skib. 
					           			Anvendes fx et trækkende køretøj og påhængsvogn, er det aktive transportmiddel det trækkende køretøj.
					           			<br/><br/>
					           			Nationalitetskoden for det angivne transportmiddel jf. landefortegnelsen.
								</span>	
								</div>
								</td>
								<td>
					            		<select name="dkeh_21" id="dkeh_21">
				 						<option value="">-vælg-</option>
					 				  	<c:forEach var="country" items="${model.countryCodeList}" >
					 				  		<option value="${country.dkkd_kd}"<c:if test="${model.record.dkeh_21 == country.dkkd_kd}"> selected </c:if> >${country.dkkd_kd}</option>
										</c:forEach>  
									</select>
									<a tabindex="-1" id="dkeh_21IdLink"   OnClick="triggerChildWindowCountryCodes(this)">
										<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
									</a>																	 			
					            	</td>
					        </tr>
						</table>
					</td>
				</tr>
				
				<tr>
					<td width="2">&nbsp;</td>
			 		<td>
			 			<table border="0" cellspacing="1" cellpadding="0">
			 				<tr>
					            <td class="text12" align="left" >
					            <img onMouseOver="showPop('25_info');" onMouseOut="hidePop('25_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					            <b>25.</b>
					            <span title="dkeh_25" id="v_dkeh_25" class="validation" >Transp.måde ved grænsen&nbsp;</span>
					            <div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="25_info" class="popupWithInputText text11"  >
					           			<b>25. Transportmåde ved grænsen</b>
					           			<br/><br/>
					           			Koden for den transportmåde, der anvendes ved transporten over EU's ydre grænse. 
					           			Denne transportmåde skal være i overensstemmelse med det aktive transportmiddel angivet i rubrik 21. 
					           			Der anvendes koderne i bilag 145.
								</span>	
								</div>
								</td>
					            <td class="text12" >
			           				<select name="dkeh_25" id="dkeh_25">
				 						<option value="">-vælg-</option>
					 				  	<c:forEach var="code" items="${model.transportmadeCodeList}" >
					 				  		<option value="${code.dkkd_kd}"<c:if test="${model.record.dkeh_25 == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}-${code.dkkf_txt}</option>
										</c:forEach>  
									</select>
			           			</td>
							</tr>
			 				<tr height="2"><td class="text"></td> </tr>
			 				<tr>
					            <td class="text12" align="left" >
					            <img onMouseOver="showPop('26_info');" onMouseOut="hidePop('26_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					            <b>26.</b>
					            <span title="dkeh_26" id="v_dkeh_26" class="validation">Indenlandsk transp.måde&nbsp;</span>
					            <div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="26_info" class="popupWithInputText text11"  >
					           			<b>26. Indenlandsk transportmåde</b>
					           			<br/><br/>
					           			Koden for den transportmåde, der anvendes ved varernes frigivelse til udførsel af EU's toldområde. 
					           			Udfyldelsen er obligatorisk, og der anvendes de koder, der er anført i bilag 145.
								</span>	
								</div>
								</td>	
					            
					            <td class="text12" >
			           				<select name="dkeh_26" id="dkeh_26">
				 						<option value="">-vælg-</option>
					 				  	<c:forEach var="code" items="${model.transportmadeCodeList}" >
					 				  		<option value="${code.dkkd_kd}"<c:if test="${model.record.dkeh_26 == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}-${code.dkkf_txt}</option>
										</c:forEach>  
									</select>
			           			</td>
							</tr>
							<%--
							<tr height="2"><td class="text"></td> </tr>
					 		<tr>
					            <td class="text12" align="left" >
					            <img onMouseOver="showPop('taf_info');" onMouseOut="hidePop('taf_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					            <span title="dkeh_taf">Transp.middel ved afgang&nbsp;</span></td>
					            <span style="position:absolute; left:800px; top:150px; width:250px; height:240px;" id="taf_info" class="popupWithInputText"  >
					           		<div class="text11" align="left">
					           			<b>Transp.middel ved afgang</b>
					           			<br/><br/>
					           			Todo...
									</div>
								</span>		
					            
					            <td class="text12" >
					            		&nbsp;<input type="text" class="inputTextMediumBlue" name="dkeh_taf" id="dkeh_taf" size="21" maxlength="20" value="${model.record.dkeh_taf}">
					            </td>
							</tr>
							 --%>
							 
						</table>
					</td>
				</tr>
				
				<tr height="10"><td>&nbsp;</td> </tr>
				<tr>
					<td width="2">&nbsp;</td>
					<td valign="top">
			 			<table border="0" cellspacing="0" cellpadding="0">
				 			<tr>
				            <td class="text12" align="left" >
				            <img onMouseOver="showPop('s13_info');" onMouseOut="hidePop('s13_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
		 					<b>S13.1</b><span title="dkeh_s131" id="v_dkeh_s131" class="validation">&nbsp;Transitlandskode</span>&nbsp;
				            <div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="s13_info" class="popupWithInputText text11"  >
				           			<b>S13. Transitlandskode</b>
				           			<br/><br/>
				           			Landekode/koder indsættes. 
				           			<br/><br/>
							</span>	
							</div>
							</td>	
				            <td >
				            		<select name="dkeh_s131" id="dkeh_s131">
			 						<option value="">-vælg-</option>
				 				  	<c:forEach var="country" items="${model.countryCodeList}" >
				 				  		<option value="${country.dkkd_kd}"<c:if test="${model.record.dkeh_s131 == country.dkkd_kd}"> selected </c:if> >${country.dkkd_kd}</option>
									</c:forEach>  
								</select>
								<a tabindex="-1" id="dkeh_s131IdLink"  OnClick="triggerChildWindowCountryCodes(this)">
									<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
								</a>
								
							</td>
							</tr>
							
							<tr>
				            <td class="text12" align="left" >
				            <img onMouseOver="showPop('s132_info');" onMouseOut="hidePop('s132_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
		 					<b>S13.2</b><span title="dkeh_s132">&nbsp;Transitlandskode-2</span>&nbsp;
				            <div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="s132_info" class="popupWithInputText text11"  >
				           			<b>S13.2 Transitlandskode-2</b>
				           			<br/><br/>
				           			Landekode/koder indsættes. 
				           			<br/><br/>
							</span>
							</div>
							</td>		
				            <td >
				            		<select name="dkeh_s132" id="dkeh_s132">
			 						<option value="">-vælg-</option>
				 				  	<c:forEach var="country" items="${model.countryCodeList}" >
				 				  		<option value="${country.dkkd_kd}"<c:if test="${model.record.dkeh_s132 == country.dkkd_kd}"> selected </c:if> >${country.dkkd_kd}</option>
									</c:forEach>  
								</select>
								<a tabindex="-1" id="dkeh_s132IdLink"  OnClick="triggerChildWindowCountryCodes(this)">
									<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
								</a>
								&nbsp;
								<button name="transitCountriesButton" class="buttonGray" type="button" onClick="showPop('transitCountries');" >Mere transit</button> 
							        <span style="position:absolute; left:950px; top:300px; width:250px; height:200px;" id="transitCountries" class="popupWithInputText"  >
						           		<div class="text10" align="left" valign="top">
						           			<table>
						           				<tr>
								           			<td class="text11">
														<b>S13.3</b><span title="dkeh_s133">&nbsp;Transitland 3</span>
													</td>
													<td>
									           			<select name="dkeh_s133" id="dkeh_s133">
									 						<option value="">-vælg-</option>
										 				  	<c:forEach var="country" items="${model.countryCodeList}" >
										 				  		<option value="${country.dkkd_kd}"<c:if test="${model.record.dkeh_s133 == country.dkkd_kd}"> selected </c:if> >${country.dkkd_kd}</option>
															</c:forEach>  
														</select> 
													</td>
												</tr>
								           		<tr>
								           			<td class="text11">
														<b>S13.4</b><span title="dkeh_s134">&nbsp;Transitland 4</span>
													</td>
													<td>
									           			<select name="dkeh_s134" id="dkeh_s134">
									 						<option value="">-vælg-</option>
										 				  	<c:forEach var="country" items="${model.countryCodeList}" >
										 				  		<option value="${country.dkkd_kd}"<c:if test="${model.record.dkeh_s134 == country.dkkd_kd}"> selected </c:if> >${country.dkkd_kd}</option>
															</c:forEach>  
														</select> 
													</td>
												</tr>
												<tr>
								           			<td class="text11">
														<b>S13.5</b><span title="dkeh_s135">&nbsp;Transitland 5</span>
													</td>
													<td>
									           			<select name="dkeh_s135" id="dkeh_s135">
									 						<option value="">-vælg-</option>
										 				  	<c:forEach var="country" items="${model.countryCodeList}" >
										 				  		<option value="${country.dkkd_kd}"<c:if test="${model.record.dkeh_s135 == country.dkkd_kd}"> selected </c:if> >${country.dkkd_kd}</option>
															</c:forEach>  
														</select> 
													</td>
												</tr>
							           			
						           			</table>
											<table width="100%" align="left" border="0">
												<tr align="left" >
													<td  class="text12"><button name="transitCountriesButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('transitCountries');">&nbsp;<spring:message code="systema.skat.export.ok"/></button> 
													</td>
												</tr>
											</table>
										</div>
								</span>								
								
							</td>
							</tr>
			 			</table>
		 			</td>
		 			</tr>
		 			<tr>	
		 				<td width="2">&nbsp;</td>
						<td valign="top">
			 			<table border="0" cellspacing="0" cellpadding="0">
			 				<tr height="3"><td>&nbsp;</td> </tr>
			 				<tr height="1"><td colspan="2" style="border-bottom:1px solid;border-color:#DDDDDD;" class="text">&nbsp;</td> </tr>
			 				<tr height="5"><td>&nbsp;</td> </tr>
							<tr>
				            <td class="text12" align="left" >
				            <img onMouseOver="showPop('dkeh_201_info');" onMouseOut="hidePop('dkeh_201_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
		 					<b>20.1</b><span title="dkeh_201">&nbsp;Leveringsbetingelser [kode]</span>&nbsp;
				            <div class="text11" style="position: relative;" align="left">
							<span style="position:absolute;top:2px; width:250px;" id="dkeh_201_info" class="popupWithInputText text11"  >
				           			<b>20.1 Leveringsbetingelser</b>
				           			<br/><br/>
				           			Internationale koder for forsendelsesbetingelser og leveringsbetingelser. 
				           			<br/><br/>
				           			<ul>
				           				<c:forEach var="record" items="${model.incotermsCodeList}" >
				           				<li><b>${record.dkkd_kd}</b>&nbsp;${record.dkkf_txt}</li>
				           				</c:forEach>				           									           									           									           									           									           				
				           			</ul>
							</span>	
							</div>
							</td>	
				            <td >
					            	<select name="dkeh_201" id="dkeh_201">
			 						<option value="">-vælg-</option>
					 				  	<c:forEach var="code" items="${model.incotermsCodeList}" >
					 				  		<option value="${code.dkkd_kd}"<c:if test="${model.record.dkeh_201 == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
										</c:forEach>  
								</select>
							</td>
							</tr>
			 			
			 				<tr>
					            <td class="text12" align="left" >
					            <img onMouseOver="showPop('dkeh_202_info');" onMouseOut="hidePop('dkeh_202_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
			 					<b>20.2</b><span title="dkeh_202">&nbsp;Sted</span>&nbsp;
					            <div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="dkeh_202_info" class="popupWithInputText text11"  >
					           			<b>20.2 Sted</b>
					           			<br/><br/>
					           			Sted, hvor forsendelsesbetingelser og leveringsbetingelser opfyldes. 
					           			<br/><br/>
								</span>	
								</div>
								</td>
							</tr>
							<tr>	
					            <td colspan="2">
					            		&nbsp;&nbsp;&nbsp;<input type="text" class="inputTextMediumBlue" name="dkeh_202" id="dkeh_202" size="33" maxlength="70" value="${model.record.dkeh_202}">
								</td>
							</tr>
							<tr height="5"><td>&nbsp;</td> </tr>
				
					 		<tr>
					            <td class="text12" align="left" >
					            <img onMouseOver="showPop('s29_info');" onMouseOut="hidePop('s29_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
			 					<b>S29.</b>&nbsp;<span title="dkeh_s29" id="v_dkeh_s29" class="validation">Bet.måde for transp.udgifter</span>&nbsp;
					            <div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="s29_info" class="popupWithInputText text11"  >
				           			<b>S29. Betalingsmåde for transportudgifter (Transport charges method of payment)</b>
				           			<ul>
				           				<c:forEach var="record" items="${model.betalningsmadeCodeList}" >
				           				<li><b>${record.dkkd_kd}</b>&nbsp;${record.dkkf_txt}</li>
				           				</c:forEach>				           									           									           									           									           									           				
				           			</ul>
				           			Her anføres koden for betalingsmåde, hvis der er oplysning om den.
								</span>
								</div>
								</td>
								<td >
					            		<select name="dkeh_s29" id="dkeh_s29">
				 						<option value="">-vælg-</option>
					 				  	<c:forEach var="code" items="${model.betalningsmadeCodeList}" >
					 				  		<option value="${code.dkkd_kd}"<c:if test="${model.record.dkeh_s29 == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
										</c:forEach>  
									</select>					            		
								</td>
							</tr>
						</table>
					</td>
				</tr>
				
				<tr>
					<td width="2">&nbsp;</td>
			 		<td>
			 			<table border="0" cellspacing="1" cellpadding="0">
			 				<tr height="1"><td colspan="2" style="border-bottom:1px solid;border-color:#DDDDDD;" class="text">&nbsp;</td> </tr>
			 				<tr height="10"><td class="text">&nbsp;</td> </tr>
			 				<tr>
					            <td class="text12" align="left" >
					            <img onMouseOver="showPop('30_info');" onMouseOut="hidePop('30_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">	
					            <b>30.&nbsp;Varernes placering</b>
					            <div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="30_info" class="popupWithInputText text11"  >
					           			<b>30. Varernes placering</b>
					           			<br/><br/>
					           			Her angiver klarereren, hvor varerne kan kontrolleres. 
					           			Hvis feltet ikke er udfyldt, vil varens placering være toldekspeditionsstedet, der er angivet i rubrik A. Rubrikken udfyldes med ISO landekode DK, SE-nummer, bevillingstype og løbenummeret på den adresse i bevillingen, som der er tale om.
					           			<br/><br/>
								</span>	
								</div>
								</td>	
					        </tr>
				            <tr>
				            		<td class="text12" align="left" >
					            &nbsp;&nbsp;&nbsp;30.1&nbsp;
					            <span title="dkeh_301" id="v_dkeh_301" class="validation">Oplagshav. ISO kode</span></td>
					            <td >
						            <select name="dkeh_301" id="dkeh_301">
					 				  <option value="">-vælg-</option>
									  <option value="DK"<c:if test="${model.record.dkeh_301 == 'DK'}"> selected </c:if> >DK</option>
									</select>
					            </td>
	        					</tr>
	        					<tr>
				            		<td class="text12" align="left" >
					            &nbsp;&nbsp;&nbsp;30.2&nbsp;
					            <span title="dkeh_302" id="v_dkeh_302" class="validation">Oplagshav. SE-nr</span></td>
					            <td ><input type="text" class="inputTextMediumBlue" name="dkeh_302" id="dkeh_302" size="9" maxlength="8" value="${model.record.dkeh_302}"></td>
	        					</tr>
	        					<tr>
				            		<td class="text12" align="left" >
					            &nbsp;&nbsp;&nbsp;30.3&nbsp;
					            <span title="dkeh_303" id="v_dkeh_303" class="validation">Bevillingstype</span></td>
					            <td ><input type="text" class="inputTextMediumBlue" name="dkeh_303" id="dkeh_303" size="4" maxlength="3" value="${model.record.dkeh_303}"></td>
	        					</tr>
	        					<tr>
				            		<td class="text12" align="left" >
					            &nbsp;&nbsp;&nbsp;30.4&nbsp;
					            <span title="dkeh_304" id="v_dkeh_304" class="validation">Adresseløbenr.</span></td>
					            <td ><input type="text" class="inputTextMediumBlue" name="dkeh_304" id="dkeh_304" size="4" maxlength="3" value="${model.record.dkeh_304}"></td>
	        					</tr>
	        					<tr height="8"><td class="text"></td> </tr>
			 				
							<tr>
					            <td class="text12" align="left" >&nbsp;&nbsp;&nbsp;
					            <img onMouseOver="showPop('uvp_info');" onMouseOut="hidePop('uvp_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					            30.d&nbsp;
			 					<span title="dkeh_uvp" id="v_dkeh_uvp" class="validation">Var.plac.udpassage</span>
			 					<div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="uvp_info" class="popupWithInputText text11"  >
					           			<b>Varernes placering udpassage</b>
					           			<br/><br/>
					           			Kan kun anvendes i forbindelse med ankomstregistreringer, dvs ajourføringstype = 6
					           			<br/><br/>
					           			[I BGM segmentet for angivelsesart: AIU FoU, FuaA, FuaF, Pro.]
					           			
								</span>	
								</div>	
					            </td>
				                 <td >
						            	<input type="text" class="inputTextMediumBlue" name="dkeh_uvp" id="dkeh_uvp" size="21" maxlength="35" value="${model.record.dkeh_uvp}">
								</td>
							</tr>
			 				
	        					<tr height="1"><td colspan="2" style="border-bottom:1px solid;border-color:#DDDDDD;" class="text">&nbsp;</td> </tr>
				            
	        					<tr height="10"><td class="text">&nbsp;</td> </tr>
	        					<tr>
					            <td class="text12" align="left" >
					            <img onMouseOver="showPop('brut_info');" onMouseOut="hidePop('brut_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">	
					            <span title="dkeh_brut">Bruttovægt</span>
					            <div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="brut_info" class="popupWithInputText text11"  >
					           			<b>Samlede bruttovægt</b>
								</span>	
								</div>
								</td>
					            <td >
				            		<input onKeyPress="return numberKey(event)" style="text-align: right" type="text" class="inputTextMediumBlue" name="dkeh_brut" id="dkeh_brut" size="12" maxlength="11" value="${model.record.dkeh_brut}">
					            </td>
					        </tr>
				            <tr>
					            <td class="text12" align="left" >
					            <img onMouseOver="showPop('6_info');" onMouseOut="hidePop('6_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">	
					            <b>6.&nbsp;</b>
					            <span title="dkeh_06" id="v_dkeh_06" class="validation">Kolli i alt</span>
					            <div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="6_info" class="popupWithInputText text11"  >
				           			<b>6. Kolli i alt</b>
				           			<br/><br/>
				           			Det samlede antal kolli.
				           			<br/><br/>
				           			Beregnes automatisk af oplysninger om kolli antal i vareposterne.
								</span>	
								</div>
								</td>
					            <td >
				            		<input onKeyPress="return numberKey(event)" style="text-align: right" type="text" class="inputTextMediumBlue" name="dkeh_06" id="dkeh_06" size="8" maxlength="7" value="${model.record.dkeh_06}">
					            </td>
					        </tr>
					        
					        <tr>
					        		<td class="text12Gray" align="left" >
					        			Antal kollin (i vareposterne)&nbsp;
					        		</td>
						        	<td >
					            		<input readonly style="text-align: right" type="text" class="inputTextReadOnly" name="sumOfAntalKolliInItemLines" id="sumOfAntalKolliInItemLines" size="20" value="${ model.record.sumOfAntalKolliInItemLinesStr}">
					            		<c:if test="${not empty ( model.record.sumOfAntalKolliInItemLinesStr &&  model.record.dkeh_06)}">
						            		<c:if test="${ model.record.dkeh_06 !=  model.record.sumOfAntalKolliInItemLinesStr}">
								            <img onMouseOver="showPop('itemsKolliSum_info');" onMouseOut="hidePop('itemsKolliSum_info');" width="18px" height="20px" src="resources/images/redFlag.png" border="0" alt="kolliantal warning">	
								            <div class="text11" style="position: relative;" align="left">
											<span style="position:absolute;top:2px; width:250px;" id="itemsKolliSum_info" class="popupWithInputText text11"  >
								           			<font class="text11">
								           			<p>
								           			Summen af ​​antallet af pakker på produktlinje niveau svarer ikke til det angivne antal pakker i posten.
													Vi anbefaler at gennemgå, hvad kunne muligvis være forkert ved at kontrollere varer linjer.
													</p>
								           			<p>
								           			Hvis nummeret til venstre er = <b>-1</b> betyder, at der er mere end 0-varelinjer, og summen af varelinjer kolliantal er = 0 (hvilket ikke er korrekt).
								           			</p>
								           			</font>
											</span>	
											</div>
						            		</c:if>
					            		</c:if>
					            </td>
					        </tr>
					        <tr>
					        		<td class="text12Gray" align="left" >
					        			Antal vareposter &nbsp;
					        		</td>
						        	<td >
					            		<input readonly style="text-align: right" type="text" class="inputTextReadOnly" name="sumOfAntalItemLines" id="sumOfAntalItemLines" size="20" value="${ model.record.sumOfAntalItemLinesStr}">
					            		<c:if test="${not empty ( model.record.sumOfAntalItemLinesStr)}">
						            		<c:if test="${ model.record.sumOfAntalItemLines <= 0 && model.record.dkeh_aart != '50'}">
								            <img onMouseOver="showPop('itemsSum_info');" onMouseOut="hidePop('itemsSum_info');" width="18px" height="20px" src="resources/images/redFlag.png" border="0" alt="varelinjerantal warning">	
								            <div class="text11" style="position: relative;" align="left">
									            <span style="position:absolute; left:10px; top:0px;" id="itemsSum_info" class="popupWithInputText"  >
								           			<font class="text11" >
								           			Summen af ​​antallet varer linjer må vare > 0
								           			<br/><br/>
								           			Kun Angivelsestype: <b>IE507-Ankomstmeddelelse</b> kan ha antall <b>varer linjer=0</b>
								           			</font>
												</span>
											</div>	
						            		</c:if>
					            		</c:if>
					            		 
					            </td>
					        </tr>
   					        <tr>
					        		<td class="text12Gray" align="left" >
					        			Sum av varelinjebeløp&nbsp;
					        		</td>
						        	<td >
					            		<input readonly style="text-align: right" type="text" class="inputTextReadOnly" name="sumTotalAmountItemLinesStr---Dummy" id="sumTotalAmountItemLinesStr---Dummy" size="20" maxlength="20" value="${ model.record.sumTotalAmountItemLinesStr}">
						            	<c:if test="${model.record.sumTotalAmountItemLines != model.record.dkeh_222Dbl}">
						            		<img onMouseOver="showPop('itemsAmountSum_info');" onMouseOut="hidePop('itemsAmountSum_info');" width="18px" height="20px" src="resources/images/redFlag.png" border="0" alt="vare-sum warning">	
							            <div class="text11" style="position: relative;" align="left">
							            <span style="position:absolute; left:10px; top:0px;" id="itemsAmountSum_info" class="popupWithInputText"  >
						           			<font class="text11" >Summen af ​​Varens pris(Σ) <b>matcher ikke</b> 22.2 Fakturabeløb</font>
										</span>	
										</div>
					            		</c:if>
					            </td>
					        </tr>
					        <tr height="10"><td class="text">&nbsp;</td> </tr>
					        
						</table>
					</td>
				</tr>
				<tr height="20"><td class="text"></td></tr>
				<tr>
					<td width="2">&nbsp;</td>
			 		<td>
			 			<table width="80%" align="left" border="0" cellspacing="0" cellpadding="0">
			 				<tr height="5">
			 					<td class="text">&nbsp;</td> 
			 					<td class="text">&nbsp;</td> 
			 				</tr>
			 				<tr >
				            	<td class="text">&nbsp;</td> 
		 						<td class="text">&nbsp;</td> 
			 				</tr>
			 				
			 				
				            <tr >	
			            		<td class="text9BlueGreen" valign="bottom" align="left" >
			 				    <c:choose>
				 				    <c:when test="${ model.record.dkeh_syst == 'M' || empty  model.record.dkeh_syst || model.record.dkeh_syst == '11' || model.record.dkeh_syst == '20' || model.record.dkeh_syst == '97' || model.record.dkeh_syst == '40'}">
					 				    	<input class="inputFormSubmit" type="submit" name="submit2" id="submit2" onclick="javascript: form.action='skatexport_edit.do';" value='<spring:message code="systema.skat.export.createnew.submit"/>'/>
					 				    	&nbsp;&nbsp;
					 				    	<c:if test="${not empty  model.record.dkeh_syop && model.record.validUpdate}">
					 				    		<input tabindex=-2 class="inputFormSubmit" type="submit" name="send2" id="send2" onclick="javascript: form.action='skatexport_send.do';" value='<spring:message code="systema.skat.export.createnew.send"/>'/>
					 				    	</c:if>
				 				    </c:when>
				 				    <c:otherwise>
				 				    		<input disabled class="inputFormSubmitGrayDisabled" type="submit" name="submit2" value='<spring:message code="systema.skat.submit.not.editable"/>'/>
				 				    </c:otherwise>	
			 				    </c:choose>
		 				    
                				</td>
					        </tr>
				            -
						</table>
					</td>
				</tr>
				
			</table>
		</td>
		</tr>
		<tr height="20"><td colspan="2">&nbsp;</td></tr>
		
		<tr height="20"><td colspan="2">&nbsp;</td></tr>
		<tr>
		    <td colspan="2" >
			<%-- ---------------------------- --%>
			<%-- tab area container SECONDARY --%>
			<%-- ---------------------------- --%>
			<table width="100%" class="secondarySectionHeader" border="0" cellspacing="0" cellpadding="0">
		 		<tr height="18">
					<td class="text14WhiteBold">
		 				<b>&nbsp;&nbsp;&nbsp;&nbsp;Supplerende oplysninger&nbsp;</b>
		 				<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
					</td>
					<td class="text11White" align="right">
		 				<b>CUSDEC</b>&nbsp;
		 				<a tabindex=-1 href="renderLocalPdf.do?fn=SKAT_EDI_vejledning_CUSDEC_Eksport_rev4_9.pdf" target="_blank">
		 					<img valign="bottom" width="14px" height="14px" src="resources/images/pdf.png" border="0" alt="pdf">
		 				</a>
		 				&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
			</table>
			</td>
		</tr>
		
		<%--EXTRAORDINÄRA --%>
		<tr>
			<td colspan="2" >
			<table width="100%" class="secondarySectionFrame" border="0" cellspacing="0" cellpadding="0">
				<tr height="10"><td colspan="2"></td></tr>
				<tr>
				<td width="50%" valign="top">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
			 			<td width="5">&nbsp;</td>
			            <td colspan="2">
	            				<%-- =========== --%>
			 				<%-- TRANSPORTØR --%>
			 				<%-- =========== --%>	
			 				<table width="100%" align="left" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
						 		<tr height="18px">
						 			<td class="text12White">
						 				&nbsp;<img onMouseOver="showPop('transportor_info');" onMouseOut="hidePop('transportor_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 						Transportør&nbsp;<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
				 						<div class="text11" style="position: relative;" align="left">
										<span style="position:absolute;top:2px; width:250px;" id="transportor_info" class="popupWithInputText text11"  >
							           			<b>Transportør</b>
							           			<br/><br/>
							           			Todo
							           			<br/><br/>
										</span>
										</div>
					 				</td>
				 				</tr>
			 				</table>
			 			</td>
			 		</tr>
			 		<tr>	
			 			<td width="5">&nbsp;</td>
			            <td colspan="2">	
							<%-- create record --%>
						 	<table width="100%" align="left" class="formFrame" border="0" cellspacing="0" cellpadding="0">
						 		<tr>
							 		<td>
								 		<table width="100%" border="0" cellspacing="0" cellpadding="0">
									 		<tr height="15">
									            <td class="text12" align="left">&nbsp;</td> 
									        </tr>
									        <tr>
									        	<%-- ================================================================================== --%>
									        	<%-- This hidden values are used when an AJAX event from within a dialog box is fired.  
									        		 These original values will be used when the user clicks "Cancel" buttons (puttting
									        		 back original value)																--%> 
									        	<%-- ================================================================================== --%>
									        	<input type="hidden" name="orig_dkeh_trkn" id="orig_dkeh_trkn" value='${model.record.dkeh_trkn}'>
									        	<input type="hidden" name="orig_dkeh_treo" id="orig_dkeh_treo" value='${model.record.dkeh_treo}'>
									        	
							        	
									            <td class="text12" align="left" >&nbsp;&nbsp;
									            <span title="dkeh_trkn">Kundenummer</span></td>
									            <td class="text12" align="center" >&nbsp;&nbsp;<span title="dkeh_treo" id="v_dkeh_treo" class="validation">EORI-nr.</span>
									            	<a tabindex="-1" id="dkeh_treoIdLink">
													<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
													</a>
									            </td>
									        </tr>
									        <tr>
									            <td align="left"><input type="text" class="inputTextMediumBlue" name="dkeh_trkn" id="dkeh_trkn" size="8" maxlength="8" value="${model.record.dkeh_trkn}"></td>
									            <td align="center"><input type="text" class="inputTextMediumBlue" name="dkeh_treo" id="dkeh_treo" size="19" maxlength="18" value="${model.record.dkeh_treo}"></td>
									        </tr>
									        <tr height="5"><td></td></tr>
									        
									        <tr>
									            <td class="text12">
													<span title="dkeh_zkp" id="v_dkeh_zkp" class="validation">&nbsp;Transportmidlets fører</span>
												</td>
												<td align="center"></td>
									        </tr>
									        <tr>
									            <td align="left"><input type="text" class="inputText" name="dkeh_zkp" id="dkeh_zkp" size="20" maxlength="35" value="${model.record.dkeh_zkp}">
									            <td align="center"></td>
									        </tr>
									        <tr height="10"><td></td></tr>
								        </table>
							        </td>
						        </tr>
							</table>          
			            	</td>
		           	</tr> 
		           	<tr height="10"><td></td></tr>
		           	<tr>
			 			<td width="5">&nbsp;</td>
			            <td colspan="2">
	            				<%-- =========== --%>
			 				<%-- REPRESENTAT --%>
			 				<%-- =========== --%>	
			 				<table width="100%" align="left" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
						 		<tr height="18px">
						 			<td class="text12White">
						 				&nbsp;<img onMouseOver="showPop('representant_info');" onMouseOut="hidePop('representant_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 						Repræsentant ved udpassage&nbsp;<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
				 						<div class="text11" style="position: relative;" align="left">
										<span style="position:absolute;top:2px; width:250px;" id="representant_info" class="popupWithInputText text11"  >
							           			<b>Repræsentant ved udpassage</b>
							           			<br/><br/>
							           			Todo
							           			<br/><br/>
										</span>
										</div>
					 				</td>
				 				</tr>
			 				</table>
			 			</td>
			 		</tr>
			 		<tr>	
			 			<td width="5">&nbsp;</td>
			            <td colspan="2">	
							<%-- create record --%>
						 	<table width="100%" align="left" class="formFrame" border="0" cellspacing="0" cellpadding="0">
						 		<tr>
							 		<td>
								 		<table width="100%" border="0" cellspacing="0" cellpadding="0">
									 		<tr height="15">
									            <td class="text12" align="left">&nbsp;</td> 
									        </tr>
									        <tr>
									        	<%-- ================================================================================== --%>
									        	<%-- This hidden values are used when an AJAX event from within a dialog box is fired.  
									        		 These original values will be used when the user clicks "Cancel" buttons (puttting
									        		 back original value)																--%> 
									        	<%-- ================================================================================== --%>
									        	<input type="hidden" name="orig_dkeh_rekn" id="orig_dkeh_rekn" value='${model.record.dkeh_rekn}'>
									        	<input type="hidden" name="orig_dkeh_rena" id="orig_dkeh_rena" value='${model.record.dkeh_rena}'>
									        	<input type="hidden" name="orig_dkeh_reeo" id="orig_dkeh_reeo" value='${model.record.dkeh_reeo}'>
									        	<input type="hidden" name="orig_dkeh_rega" id="orig_dkeh_rega" value='${model.record.dkeh_rega}'>
									        	<input type="hidden" name="orig_dkeh_repo" id="orig_dkeh_repo" value='${model.record.dkeh_repo}'>
									        	<input type="hidden" name="orig_dkeh_reby" id="orig_dkeh_reby" value='${model.record.dkeh_reby}'>
									        	<input type="hidden" name="orig_dkeh_relk" id="orig_dkeh_relk" value='${model.record.dkeh_relk}'>
									        	
							        	
									            <td class="text12" align="left" >&nbsp;&nbsp;
									            <span title="dkeh_rekn">Kundenummer</span></td>
									            <td class="text12" align="left" >&nbsp;&nbsp;<span title="dkeh_rena">Navn</span>
									           		<a tabindex="-1" id="dkeh_renaIdLink">
													<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
													</a>
									            </td>
									        </tr>
									        <tr>
									            <td align="left"><input type="text" class="inputTextMediumBlue" name="dkeh_rekn" id="dkeh_rekn" size="8" maxlength="8" value="${model.record.dkeh_rekn}"></td>
									            <td align="left"><input type="text" class="inputTextMediumBlue" name="dkeh_rena" id="dkeh_rena" size="30" maxlength="35" value="${model.record.dkeh_rena}"></td>
									        </tr>
									        <tr>
									            <td class="text12" align="left" >&nbsp;&nbsp;
									            <span title="dkeh_reeo" id="v_dkeh_reeo" class="validation">EORI-nr.</span></td>
									            <td class="text12" align="left" >&nbsp;&nbsp;</td>
									        </tr>
									        <tr>
									            <td align="left"><input type="text" class="inputTextMediumBlue" name="dkeh_reeo" id="dkeh_reeo" size="19" maxlength="18" value="${model.record.dkeh_reeo}"></td>
									            <td align="left">&nbsp;</td>
									        </tr>
									        <tr height="4"><td>&nbsp;</td></tr>
									        <tr>
									            <td class="text12" align="left" >&nbsp;&nbsp;
									            <span title="dkeh_rega">Adresse</span></td>
									            <td class="text12" align="left" >&nbsp;&nbsp;
								        			<span title="dkeh_repo">Postnummer</span></td>
										            		
									        </tr>
									        <tr>
									            <td align="left"><input type="text" class="inputTextMediumBlue" name="dkeh_rega" id="dkeh_rega" size="30" maxlength="35" value="${model.record.dkeh_rega}"></td>
									            <td align="left"><input type="text" class="inputTextMediumBlue" name="dkeh_repo" id="dkeh_repo" size="10" maxlength="35" value="${model.record.dkeh_repo}"></td> 
										            		
									        </tr>
									        <tr>
									        		<td>
										        		<table>
										        		<tr>
										            		<td class="text12" align="left" >&nbsp;
										            		<span title="dkeh_reby">By</span></td>
										            		<td align="left">&nbsp;</td>
										            	</tr>
										        		<tr>
										            		<td align="left">
										       				<input type="text" class="inputTextMediumBlue" name="dkeh_reby" id="dkeh_reby" size="30" maxlength="35" value="${model.record.dkeh_reby}">
									            			</td> 
										            		<td align="left">&nbsp;</td>
										        		</tr>    	
										            	</table>
									            </td>
									            <td >
										            	<table>
										        		<tr>
										        			<td class="text12" align="left" >&nbsp;&nbsp;
										            		<span title="dkeh_relk">Land</span>
																																 			
														</td>
										            	</tr>
										        		<tr >
										        			<td align="left">
										            			<select name="dkeh_relk" id="dkeh_relk">
												            		<option value="">-vælg-</option>
											 				  	<c:forEach var="country" items="${model.countryCodeList}" >
											 				  		<option value="${country.dkkd_kd}"<c:if test="${model.record.dkeh_relk == country.dkkd_kd}"> selected </c:if> >${country.dkkd_kd}</option>
																</c:forEach>  
															</select>
															<a tabindex="-1" id="dkeh_relkIdLink"  OnClick="triggerChildWindowCountryCodes(this)">
																<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
															</a>
										            		</td> 
										        		</tr>  
										            	</table>
									            </td>
									        </tr>
									        <tr height="15">
								            		<td class="text12Bold" align="left" >&nbsp;</td>
								            		<td class="text12Bold" align="left" >&nbsp;</td> 
									        </tr>
								        </table>
							        </td>
						        </tr>
							</table>          
			            	</td>
		           	</tr> 
		           	<tr height="10"><td></td></tr>
		           	<tr>
			 			<td >&nbsp;</td>
			            <td class="text12" valign="top">
			 				<table align="left" border="0" cellspacing="0" cellpadding="0">
			 					<tr height="1"><td></td></tr>
				 				<tr>
				 					<td class="text12Bold" colspan="2">Systemdatoer&nbsp;</td>
								</tr>
							
				 				<tr height="1"><td></td></tr>
				 				<tr>
				 					<td class="text12" ><span title="dkeh_godt_dummy">Godkendelsesdato:</span>&nbsp;</td>
				 					<td class="text12">
				 						<input readonly style="text-align: right" type="text" class="inputTextReadOnly" name="dkeh_godt_dummy" id="dkeh_godt_dummy" size="13" maxlength="12" value="${model.record.dkeh_godt}">
				 					</td>
								</tr>
								<tr height="1"><td></td></tr>
				 				<tr>
				 					<td class="text12" ><span title="dkeh_frdt_dummy">Frigivelsetidspunkt:</span>&nbsp;</td>
				 					<td class="text12">
				 						<input readonly style="text-align: right" type="text" class="inputTextReadOnly" name="dkeh_frdt_dummy" id="dkeh_frdt_dummy" size="13" maxlength="12" value="${model.record.dkeh_frdt}">
				 					</td>
								</tr>
								<tr height="1"><td></td></tr>
				 				<tr>
				 					<td class="text12" ><span title="dkeh_vadt_dummy">Valideringstidspunkt:</span>&nbsp;</td>
				 					<td class="text12">
				 						<input readonly style="text-align: right" type="text" class="inputTextReadOnly" name="dkeh_vadt_dummy" id="dkeh_vadt_dummy" size="13" maxlength="12" value="${model.record.dkeh_vadt}">
				 					</td>
								</tr>
								<tr height="1"><td></td></tr>
				 				<tr>
				 					<td class="text12" ><span title="dkeh_fedt_dummy">Fakt.eksp.tidspunkt:</span>&nbsp;</td>
				 					<td class="text12">
				 						<input readonly style="text-align: right" type="text" class="inputTextReadOnly" name="dkeh_fedt_dummy" id="dkeh_fedt_dummy" size="13" maxlength="12" value="${model.record.dkeh_fedt}">
				 					</td>
								</tr>
								<tr height="1"><td></td></tr>
				 				<tr>
				 					<td class="text12" ><span title="dkeh_fudt_dummy">Fakt.udpassagetid:</span>&nbsp;</td>
				 					<td class="text12">
				 						<input readonly style="text-align: right" type="text" class="inputTextReadOnly" name="dkeh_fudt_dummy" id="dkeh_fudt_dummy" size="13" maxlength="8" value="${model.record.dkeh_fudt}">
				 					</td>
								</tr>
								
			 				</table>
			 			</td>
			 			<td class="text12" valign="top">
			 				<table align="left" border="0" cellspacing="0" cellpadding="0">
			 					<tr height="1"><td></td></tr>
				 				<tr>
				 					<td class="text12Bold" >&nbsp;</td>
								</tr>
								<tr height="1"><td></td></tr>
			 					<tr>
				 					<td class="text12" ><span title="dkeh_fvdt_dummy">Forv.udpassagetid:</span>&nbsp;</td>
				 					<td class="text12">
				 						<input readonly style="text-align: right" type="text" class="inputTextReadOnly" name="dkeh_fvdt_dummy" id="dkeh_fvdt_dummy" size="10" maxlength="8" value="${model.record.dkeh_fvdt}">
				 					</td>
								</tr>
								<tr height="1"><td></td></tr>
				 				<tr>
				 					<td class="text12" ><span title="dkeh_ctdt_dummy">Udt.udpassagectl:</span>&nbsp;</td>
				 					<td class="text12">
				 						<input readonly style="text-align: right" type="text" class="inputTextReadOnly" name="dkeh_ctdt_dummy" id="dkeh_ctdt_dummy" size="10" maxlength="8" value="${model.record.dkeh_ctdt}">
				 					</td>
								</tr>
								<tr height="1"><td></td></tr>
				 				<tr>
				 					<td class="text12" ><span title="dkeh_cfdt_dummy">Frig.udpassagectl:</span>&nbsp;</td>
				 					<td class="text12">
				 						<input readonly style="text-align: right" type="text" class="inputTextReadOnly" name="dkeh_cfdt_dummy" id="dkeh_cfdt_dummy" size="10" maxlength="8" value="${model.record.dkeh_cfdt}">
				 					</td>
								</tr>
								<tr height="1"><td></td></tr>
				 				<tr>
				 					<td class="text12" ><span title="dkeh_fadt_dummy">Fakt.ank.utp.sted:</span>&nbsp;</td>
				 					<td class="text12">
				 						<input readonly style="text-align: right" type="text" class="inputTextReadOnly" name="dkeh_fadt_dummy" id="dkeh_fadt_dummy" size="10" maxlength="8" value="${model.record.dkeh_fadt}">
				 					</td>
								</tr>
			 				</table>
			 			</td>
				 	</tr>
				 	
			 		<tr height="10"><td></td></tr>
					</table>
				</td>
				<td width="2">&nbsp;</td>
				<%-- --------------- --%>
				<%-- RIGHT SIDE CELL --%>
				<%-- --------------- --%>
				<td width="45%" align="center" valign="top">
					<table border="0" cellspacing="0" cellpadding="0">
						<tr>
						<td width="2">&nbsp;</td>
			 			<td class="text12">
		 				<table align="left" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td class="text12" nowrap>
				 					<img onMouseOver="showPop('fraktdok_info');" onMouseOut="hidePop('fraktdok_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					 				<span title="dkeh_gis1">Fragtdok.&nbsp;</span>
					 				<div class="text11" style="position: relative;" align="left">
									<span style="position:absolute;top:2px; width:250px;" id="fraktdok_info" class="popupWithInputText text11"  >
							           		<b>Fragtdokument</b>
							           		<ul>
							           			<li><b>1</b> Hvis gennemgående fragtdokument</li>
												<li><b>0</b> Hvis direkte udførsel, [1131] = 117 </li>
												
							           		</ul>
									</span>
					 				</div>
				 				</td>
				 				
				 				<td class="text12" nowrap>
				 					<img onMouseOver="showPop('storageFlag_info');" onMouseOut="hidePop('storageFlag_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					 				<span title="dkeh_gis2">Storage flag&nbsp;</span>
					 				<div class="text11" style="position: relative;" align="left">
									<span style="position:absolute;top:2px; width:250px;" id="storageFlag_info" class="popupWithInputText text11"  >
							           		<b>Storage flag</b>
							           		<ul>
							           			<li>Storage flag = <b>0</b>, [1131] = 109 </li>
							           		</ul>
									</span>
					 				</div>
				 				</td>
				 				<td class="text12">
				 					<img onMouseOver="showPop('forsegl_info');" onMouseOut="hidePop('forsegl_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					 				<span title="dkeh_gis3">Disp. for Forsegling&nbsp;</span>
					 				<div class="text11" style="position: relative;" align="left">
									<span style="position:absolute;top:2px; width:250px;" id="forsegl_info" class="popupWithInputText text11"  >
							           		<b>Disp. for Forsegling</b>
							           		<ul>
							           			<li>Disp. For Forsegling = <b>0</b> eller <b>1</b>, [1131] = XXX</li>
							           		</ul>
									</span>
									</div>
				 				</td>
				 			</tr>
				 			
				 			<tr>	
				 				<td>
					 				<select name="dkeh_gis1" id="dkeh_gis1" >
					 				  <option selected value="">-vælg-</option>
									  <option value="0"<c:if test="${model.record.dkeh_gis1 == '0'}"> selected </c:if> >0</option>
									  <option value="1"<c:if test="${model.record.dkeh_gis1 == '1'}"> selected </c:if> >1</option>
									</select>
				 				</td>
				 				<td>
					 				<select name="dkeh_gis2" id="dkeh_gis2" >
					 				  <option selected value="">-vælg-</option>
									  <option value="0"<c:if test="${model.record.dkeh_gis2 == '0'}"> selected </c:if> >0</option>
									</select>
				 				</td>
				 				<td>
					 				<select name="dkeh_gis3" id="dkeh_gis3" >
					 				  <option selected value="">-vælg-</option>
									  <option value="0"<c:if test="${model.record.dkeh_gis3 == '0'}"> selected </c:if> >0</option>
									  <option value="1"<c:if test="${model.record.dkeh_gis3 == '1'}"> selected </c:if> >1</option>
									</select>
				 				</td>
			 				</tr>		 				
			 				<tr height="10"><td></td></tr>
			 				<tr>
				 				<td class="text12" colspan="3">
				 					<img onMouseOver="showPop('restitution_info');" onMouseOut="hidePop('restitution_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					 				<span title="dkeh_gis4">Restitution mindre end 1000 EURO&nbsp;</span>
					 				<div class="text11" style="position: relative;" align="left">
									<span style="position:absolute;top:2px; width:250px;" id="restitution_info" class="popupWithInputText text11"  >
							           		<b>Restitution mindre end 1000 EURO</b>
							           		<ul>
							           			<li>Restitution mindre end 1000 EURO = <b>0</b> eller <b>1</b>, [1131] = BBB</li>
							           		</ul>
									</span>
									</div>
				 				</td>
				 			</tr>
				 			<tr>	
				 				<td col="3">
					 				<select name="dkeh_gis4" id="dkeh_gis4" >
					 				  <option selected value="">-vælg-</option>
									  <option value="0"<c:if test="${model.record.dkeh_gis4 =='0'}"> selected </c:if> >0</option>
									  <option value="1"<c:if test="${model.record.dkeh_gis4 =='1'}"> selected </c:if> >1</option>								</select>
				 				</td>
			 				</tr>	
							
							
							</table>
							</td>
						</tr>
						<tr height="20"><td></td></tr>
						<tr>
							<td width="2">&nbsp;</td>
				 			<td class="text12">
				 				<table align="left" border="0" cellspacing="0" cellpadding="0">
					 				<tr height="5"><td>&nbsp;</td></tr>
					 				<tr>
					 					<td class="text12" >
					 					<img onMouseOver="showPop('plombering_info');" onMouseOut="hidePop('plombering_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
						 				&nbsp;<b>&nbsp;Plombering&nbsp;</b></td>
					 					<div class="text11" style="position: relative;" align="left">
										<span style="position:absolute;top:2px; width:250px;" id="plombering_info" class="popupWithInputText text11"  >
							           			<b>Plombering</b>
							           			<br/>
							           			<ul>
								           			<li><b>Id-1...Id-5</b> Plomb.Id (hovedsageligt til interne plomber).</li>
							           			</ul>	
										</span>
										</div>
									</tr>
				 				</table>
				 			</td>
						</tr> 				 		
						<tr>
							<td width="2">&nbsp;</td>
				 			<td class="text12">
				 				<table align="left" border="0" cellspacing="0" cellpadding="0">
					 				<tr height="2"><td></td></tr>
					 				<tr>
					 					<td class="text12" >&nbsp;&nbsp;&nbsp;<span title="dkeh_d011">Plombering Id-1</span>&nbsp; </td>
					 					<td class="text12">
					 						<input  type="text" class="inputTextMediumBlue" name="dkeh_d011" id="dkeh_d011" size="21" maxlength="35" value="${model.record.dkeh_d011}">
					 					</td>
									</tr>
									<tr>
					 					<td class="text12" >&nbsp;&nbsp;&nbsp;<span title="dkeh_d012">Plombering Id-2</span>&nbsp; </td>
					 					<td class="text12">
					 						<input  type="text" class="inputTextMediumBlue" name="dkeh_d012" id="dkeh_d012" size="21" maxlength="35" value="${model.record.dkeh_d012}">
					 					</td>
									</tr>
									<tr>
					 					<td class="text12" >&nbsp;&nbsp;&nbsp;<span title="dkeh_d013">Plombering Id-3</span>&nbsp; </td>
					 					<td class="text12">
					 						<input  type="text" class="inputTextMediumBlue" name="dkeh_d013" id="dkeh_d013" size="21" maxlength="35" value="${model.record.dkeh_d013}">
					 					</td>
									</tr>
									<tr>
					 					<td class="text12" >&nbsp;&nbsp;&nbsp;<span title="dkeh_d014">Plombering Id-4</span>&nbsp; </td>
					 					<td class="text12">
					 						<input  type="text" class="inputTextMediumBlue" name="dkeh_d014" id="dkeh_d014" size="21" maxlength="35" value="${model.record.dkeh_d014}">
					 					</td>
									</tr>
									<tr>
					 					<td class="text12" >&nbsp;&nbsp;&nbsp;<span title="dkeh_d015">Plombering Id-5</span>&nbsp; </td>
					 					<td class="text12">
					 						<input  type="text" class="inputTextMediumBlue" name="dkeh_d015" id="dkeh_d015" size="21" maxlength="35" value="${model.record.dkeh_d015}">
					 					</td>
									</tr>
									<tr height="15"><td></td></tr>
									<%--
									<tr>
							 			<td colspan="2" class="text12" align="left" >
							 			<img onMouseOver="showPop('7_info');" onMouseOut="hidePop('7_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
							 			<b>7.</b>&nbsp;
							 			<span title="dkeh_07a...dkeh_07d"><b>Referencenummer&nbsp;</b></span></td>
							 			<span style="position:absolute; left:800px; top:1000px; width:250px; height:320px;" id="7_info" class="popupWithInputText"  >
							           		<div class="text11" align="left">
							           			<b>7. Referencenummer</b>
							           			<br/><br/>
							           			Rubrikken skal udfyldes, men er til virksomhedens eget brug. Internt nummer ved filoverførsel.
							           			<br/><br/>
							           			Referencenummeret, som er tildelt en midlertidig oplæggelse, skal angives i rubrik 7, når standardfortoldning angives.						           			
							           			<br/><br/>
							           			<ul>
							           				<li><b>AEI</b> Registration number of previous Customs declaration (Opsplitning)</li>
													<li><b>AFM</b> Secondary Customs reference (Forudanmeldelse)</li>
													<li><b>ACW</b> Reference number to previous message (Tidligere angivelse)</li>
													<li><b>ABE</b> UCRN number</li>
							           			</ul>
						           			</div>
										</span>
							 		</tr>
							 		<tr height="2"><td></td></tr>
							 		<tr>
					 					<td align="left" ><span title="dkeh_07a">&nbsp;&nbsp;7.Ref.nr AEI&nbsp;</span></td>
							 			<td align="left" ><input type="text" class="inputTextMediumBlue" name="dkeh_07a" id="dkeh_07a" size="21" maxlength="13" value="${model.record.dkeh_07a}"></td>				 			
					 				</tr>
					 				<tr>
					 					<td align="left" ><span title="dkeh_07b">&nbsp;&nbsp;7.Ref.nr AFM&nbsp;</span></td>
							 			<td align="left" ><input type="text" class="inputTextMediumBlue" name="dkeh_07b" id="dkeh_07b" size="21" maxlength="13" value="${model.record.dkeh_07b}"></td>				 			
					 				</tr>
					 				<tr>
					 					<td align="left" ><span title="dkeh_07c">&nbsp;&nbsp;7.Ref.nr ACW&nbsp;</span></td>
							 			<td align="left" ><input type="text" class="inputTextMediumBlue" name="dkeh_07c" id="dkeh_07c" size="21" maxlength="13" value="${model.record.dkeh_07c}"></td>				 			
					 				</tr>
					 				<tr>
					 					<td align="left" ><span title="dkeh_07d">&nbsp;&nbsp;7.Ref.nr ABE&nbsp;</span></td>
							 			<td align="left" ><input type="text" class="inputTextMediumBlue" name="dkeh_07d" id="dkeh_07d" size="21" maxlength="35" value="${model.record.dkeh_07d}"></td>				 			
					 				</tr>
					 				 --%>
				 				</table>
				 			</td>
						</tr>
						<tr height="10"><td></td></tr>
						
						<tr>
							<td width="2">&nbsp;</td>
				 			<td class="text12">
				 				<table align="left" border="0" cellspacing="0" cellpadding="0">
					 				<tr height="5"><td>&nbsp;</td></tr>
					 				<tr>
					 					<td class="text12" >
					 					<img onMouseOver="showPop('bemaerkning_info');" onMouseOut="hidePop('bemaerkning_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
						 				&nbsp;<b>&nbsp;Bemærkning&nbsp;</b>
					 					<div class="text11" style="position: relative;" align="left">
										<span style="position:absolute;top:2px; width:250px;" id="bemaerkning_info" class="popupWithInputText text11"  >
							           			<b>Bemærkning</b>
							           			<br/>
							           			<ul>
								           			<li>Skal angives ved ajourføring type 1, 2 og 8</li>
							           			</ul>	
										</span>
										</div>
										</td>
									</tr>
				 				</table>
				 			</td>
						</tr> 				 		
						<tr>
							<td width="2">&nbsp;</td>
				 			<td class="text12">
				 				<table align="left" border="0" cellspacing="0" cellpadding="0">
					 				<tr height="2"><td></td></tr>
					 				<tr>
					 					<td class="text12" >&nbsp;&nbsp;&nbsp;<span title="dkeh_ftx6">Bemærkning-1</span>&nbsp; </td>
					 					<td class="text12">
					 						<input  type="text" class="inputTextMediumBlue" name="dkeh_ftx6" id="dkeh_ftx6" size="30" maxlength="70" value="${model.record.dkeh_ftx6}">
					 					</td>
									</tr>
									<tr>
					 					<td class="text12" >&nbsp;&nbsp;&nbsp;<span title="dkeh_ftx7">Bemærkning-2</span>&nbsp; </td>
					 					<td class="text12">
					 						<input  type="text" class="inputTextMediumBlue" name="dkeh_ftx7" id="dkeh_ftx7" size="30" maxlength="70" value="${model.record.dkeh_ftx7}">
					 					</td>
									</tr>
									<tr>
					 					<td class="text12" >&nbsp;&nbsp;&nbsp;<span title="dkeh_ftx8">Bemærkning-3</span>&nbsp; </td>
					 					<td class="text12">
					 						<input  type="text" class="inputTextMediumBlue" name="dkeh_ftx8" id="dkeh_ftx8" size="30" maxlength="70" value="${model.record.dkeh_ftx8}">
					 					</td>
									</tr>
									<tr>
					 					<td class="text12" >&nbsp;&nbsp;&nbsp;<span title="dkeh_ftx9">Bemærkning-4</span>&nbsp; </td>
					 					<td class="text12">
					 						<input  type="text" class="inputTextMediumBlue" name="dkeh_ftx9" id="dkeh_ftx9" size="30" maxlength="70" value="${model.record.dkeh_ftx9}">
					 					</td>
									</tr>
									
				 				</table>
				 			</td>
						</tr>
						<tr height="15"><td></td></tr>
				        <tr>
				        		<td width="2">&nbsp;</td>
				            <td class="text12" align="left" >
					            <img onMouseOver="showPop('indssted_info');" onMouseOut="hidePop('indssted_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">	
					            <span title="dkeh_ind" id="v_dkeh_ind" class="validation"><b></b>&nbsp;Indladningssted</span>
					            &nbsp;&nbsp;
				            		<select id="dkeh_ind" name="dkeh_ind" >
				            		<option value="">-vælg-</option>
			 				  	<c:forEach var="record" items="${model.udgangstoldstedCodeList}" >
			 				  		<option value="${record.dkkd_kd}"<c:if test="${model.record.dkeh_ind == record.dkkd_kd}"> selected </c:if> >${record.dkkd_kd}</option>
								</c:forEach>
								</select>
								<a tabindex="-1" id="dkeh_indIdLink" >
									<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
								</a>
								
								<div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="indssted_info" class="popupWithInputText text11"  >
					           			<b>Indladningssted</b>
					           			<br/><br/>
					           			Kun på Angivelsesart:
										<ul>
											<li>Pro</li>
											<li>ProM</li>
										</ul>
								</span>	
								</div>									
				            </td>
				        </tr>
						<tr height="30"><td></td></tr>
						<tr>
							<td colspan="2" class="text12">
							<span title="section_YM" id="v_section_YM" class="validation"><b>&nbsp;&nbsp;YM oplysninger</b></span>
								<button name="YMPosterButton" class="buttonGray" type="button" onClick="showPop('YMPoster');" >Mere</button> 
						        <span style="background-color:#EEEEEE; position:absolute; left:50px; top:1000px; width:900px; height:600px;" id="YMPoster" class="popupWithInputTextThickBorder"  >
					           		<div id="ymInformationDialog" class="ownScrollableSubWindow" style="width:850px; height:580px; margin:10px;">
					           			<nav>
					           			<table class="formFrameTitaniumWhite" width="95%" border="0" align="left" cellspacing="2">
					           			<tr>
					           				<td colspan="3" class="text14"><b>YM oplysninger</b></td>
						           		</tr>
						           		
						           		<tr>
					           				<td width="90%" valign="top">
					           				<table >
						           				<tr>
						           					<td class="text12" colspan="2" >
														&nbsp;<b>YM Ekstra Hoveddels Oplysninger</b>
													</td>
												</tr>													 
						           				<tr>
						           					<td class="text11">
														&nbsp;&nbsp;&nbsp;<span title="dkeh_ymd1" id="v_dkeh_ymd1" class="validation"><b>YM 1.1</b>&nbsp;Inladning start</span>
													</td>
													<td class="text11">
														&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="dkeh_ymd1" id="dkeh_ymd1" size="16" maxlength="12" value="${model.record.dkeh_ymd1}">
													</td>
												</tr>
												<tr>
													<td class="text11">
														&nbsp;&nbsp;&nbsp;<span title="dkeh_ymd2" id="v_dkeh_ymd2" class="validation"><b>YM 1.2</b>&nbsp;Inladning slut</span>
													</td>
													<td class="text11">
														&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="dkeh_ymd2" id="dkeh_ymd2" size="16" maxlength="12" value="${model.record.dkeh_ymd2}">
													</td>
												</tr>
												<tr>	
													<td class="text11">
														&nbsp;&nbsp;&nbsp;<span title="dkeh_ym21" id="v_dkeh_ym21" class="validation"><b>YM 2.1</b>&nbsp;Eksport art</span>
													</td>
													<td class="text11">&nbsp;
														<select name="dkeh_ym21" id="dkeh_ym21">
									 						<option value="">-vælg-</option>
										 				  	<c:forEach var="code" items="${model.exportArterCodeList}" >
										 				  		<option value="${code.dkkd_kd}"<c:if test="${model.record.dkeh_ym21 == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
															</c:forEach>  
														</select>
														<a tabindex="-1" class="text14" target="_blank" href="${model.exportArterCodesURL.value}" onclick="${model.exportArterCodesURL.windowOpenDimensions}" >
										            			<img style="cursor:pointer;" src="resources/images/find.png" border="0" alt="search" >
										            		</a>	
														
													</td>
												</tr>
												<tr>	
													<td class="text11">
														&nbsp;&nbsp;&nbsp;<span title="dkeh_ym23" id="v_dkeh_ym23" class="validation"><b>YM 2.3</b>&nbsp;T-Status</span>
													</td>
													<td class="text11">&nbsp;
														<select name="dkeh_ym23" id="dkeh_ym23">
									 						<option value="">-vælg-</option>
										 				  	<c:forEach var="code" items="${model.tStatusCodeList}" >
										 				  		<option value="${code.dkkd_kd}"<c:if test="${model.record.dkeh_ym23 == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}-${code.dkkf_txt}</option>
															</c:forEach>  
														</select>
													</td>
												</tr>
												<tr>
													<td class="text11">
														&nbsp;&nbsp;&nbsp;<span title="dkeh_ymvp" id="v_dkeh_ymvp" class="validation"><b>YM 1.3</b>&nbsp;Varernes placering – Indladningssted (ikke godkendt ordning)</span>
													</td>
													<td class="text11">
														&nbsp;<input  type="text" class="inputText" name="dkeh_ymvp" id="dkeh_ymvp" size="20" maxlength="35" value="${model.record.dkeh_ymvp}">
													</td>
												</tr>
												<tr>
													<td class="text11">
														&nbsp;&nbsp;&nbsp;<span title="dkeh_ymd3"><b>YM 2.2</b>&nbsp;Fraførselsdato</span>
													</td>
													<td class="text11">
														&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="dkeh_ymd3" id="dkeh_ymd3" size="15" maxlength="8" value="${model.record.dkeh_ymd3}">
													</td>
												</tr>
												<tr>
													<td class="text11">
														&nbsp;&nbsp;&nbsp;<span title="dkeh_yme1"><b>YM 2.4</b>&nbsp;Erklæringer til hoveddel-1</span>
													</td>
													<td class="text11">&nbsp;
														<select name="dkeh_yme1" id="dkeh_yme1">
									 						<option value="">-vælg-</option>
										 				  	<c:forEach var="code" items="${model.erklaeringerCodeList}" >
										 				  		<option value="${code.dkkd_kd}"<c:if test="${model.record.dkeh_yme1 == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}&nbsp;${fn:substring(code.dkkf_txt, 0, 50)}</option>
															</c:forEach>  
														</select>
													</td>
												</tr>
												<tr>
													<td class="text11">
														&nbsp;&nbsp;&nbsp;<span title="dkeh_yme2"><b>YM 2.4</b>&nbsp;Erklæringer til hoveddel-2</span>
													</td>
													<td class="text11">&nbsp;
														<select name="dkeh_yme2" id="dkeh_yme2" >
									 						<option value="">-vælg-</option>
										 				  	<c:forEach var="code" items="${model.erklaeringerCodeList}" >
										 				  		<option value="${code.dkkd_kd}"<c:if test="${model.record.dkeh_yme2 == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}&nbsp;${fn:substring(code.dkkf_txt, 0, 50)}</option>
															</c:forEach>  
														</select>
													</td>
												</tr>
												<tr>
													<td class="text11">
														&nbsp;&nbsp;&nbsp;<span title="dkeh_yme3"><b>YM 2.4</b>&nbsp;Erklæringer til hoveddel-3</span>
													</td>
													<td class="text11">&nbsp;
														<select name="dkeh_yme3" id="dkeh_yme3" >
									 						<option value="">-vælg-</option>
										 				  	<c:forEach var="code" items="${model.erklaeringerCodeList}" >
										 				  		<option value="${code.dkkd_kd}"<c:if test="${model.record.dkeh_yme3 == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}&nbsp;${fn:substring(code.dkkf_txt, 0, 50)}</option>
															</c:forEach>  
														</select>
													</td>
												</tr>
												<tr>
													<td class="text11">
														&nbsp;&nbsp;&nbsp;<span title="dkeh_ymb1"><b>YM 2.5</b>&nbsp;Bemærk.felt til opl. til hoveddel-1</span>
													</td>
													<td class="text11">
														&nbsp;<input  type="text" class="inputText" name="dkeh_ymb1" id="dkeh_ymb1" size="20" maxlength="70" value="${model.record.dkeh_ymb1}">
													</td>
												</tr>
												<tr>
													<td class="text11">
														&nbsp;&nbsp;&nbsp;<span title="dkeh_ymb2"><b>YM 2.5</b>&nbsp;Bemærk.felt til opl. til hoveddel-2</span>
													</td>
													<td class="text11">
														&nbsp;<input  type="text" class="inputText" name="dkeh_ymb2" id="dkeh_ymb2" size="20" maxlength="70" value="${model.record.dkeh_ymb2}">
													</td>
												</tr>
												<tr>
													<td class="text11">
														&nbsp;&nbsp;&nbsp;<span title="dkeh_ymb3"><b>YM 2.5</b>&nbsp;Bemærk.felt til opl. til hoveddel-3</span>
													</td>
													<td class="text11">
														&nbsp;<input  type="text" class="inputText" name="dkeh_ymb3" id="dkeh_ymb3" size="20" maxlength="70" value="${model.record.dkeh_ymb3}">
													</td>
												</tr>
												<tr>
													<td class="text11">
														&nbsp;&nbsp;&nbsp;<span title="dkeh_ymb4"><b>YM 2.5</b>&nbsp;Bemærk.felt til opl. til hoveddel-4</span>
													</td>
													<td class="text11">
														&nbsp;<input  type="text" class="inputText" name="dkeh_ymb4" id="dkeh_ymb4" size="20" maxlength="70" value="${model.record.dkeh_ymb4}">
													</td>
												</tr>
												
											</table>
											</td>
											
											</tr>
											<tr height="10"><td></td></tr>
											<tr align="right" >
												<td class="text11">&nbsp;</td>
												<td class="text11"><button name="YMPosterButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('YMPoster');">&nbsp;<spring:message code="systema.skat.export.ok"/></button> 
												</td>
											</tr>
										</table>
										</nav>
									</div>
								</span>
							</td>									
						</tr>
						
						<tr height="20"><td></td></tr>
						<tr>
							<td colspan="2" class="text12">
							<span title="section_Proviant" id="v_section_Proviant" class="validation"><b>&nbsp;&nbsp;Proviant</b>
								<button name="proviantButton" class="buttonGray" type="button" onClick="showPop('proviant');" >Mere</button> 
						        <span style="background-color:#EEEEEE; position:absolute; left:50px; top:1000px; width:900px; height:600px;" id="proviant" class="popupWithInputTextThickBorder"  >
					           		<div id="proviantDialog" class="ownScrollableSubWindow" style="width:850px; height:580px; margin:10px;">
					           			<nav>
					           			<table class="formFrameTitaniumWhite" width="95%" border="0" align="left" cellspacing="2">
					           			<tr>
						           			<td colspan="3" class="text14">&nbsp;</td>
						           		</tr>
						           		
						           		<tr>
					           				<td valign="top">
					           				<table >
						           				<tr>
						           					<td class="text12" colspan="2" >
														&nbsp;<b>Proviant</b>
													</td>
												</tr>													 
						           				<tr>
						           					<td class="text11">
														&nbsp;&nbsp;&nbsp;<span title="dkeh_ftx1" id="v_dkeh_ftx1" class="validation">&nbsp;Rejsens mål</span>
													</td>
													<td class="text11">
														&nbsp;<input type="text" class="inputText" name="dkeh_ftx1" id="dkeh_ftx1" size="20" maxlength="70" value="${model.record.dkeh_ftx1}">
													</td>
													<td class="text11">
														&nbsp;&nbsp;&nbsp;<span title="dkeh_ftx2" id="v_dkeh_ftx2" class="validation">&nbsp;Rejsens varighed</span>
													</td>
													<td class="text11">
														&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="dkeh_ftx2" id="dkeh_ftx2" size="5" maxlength="4" value="${model.record.dkeh_ftx2}">
													</td>
												</tr>
												<tr>
						           					<td class="text11">
														&nbsp;&nbsp;&nbsp;<span title="dkeh_ftx3" id="v_dkeh_ftx3" class="validation">&nbsp;Antal besætningsmedlemmer</span>
													</td>
													<td class="text11">
														&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="dkeh_ftx3" id="dkeh_ftx3" size="5" maxlength="4" value="${model.record.dkeh_ftx3}">
													</td>
													
												</tr>
												<tr>
						           					<td class="text11">
														&nbsp;&nbsp;&nbsp;<span title="dkeh_ftx4" id="v_dkeh_ftx4" class="validation">&nbsp;Transportmidlets art</span>
													</td>
													<td class="text11">
														&nbsp;<input type="text" class="inputText" name="dkeh_ftx4" id="dkeh_ftx4" size="20" maxlength="17" value="${model.record.dkeh_ftx4}">
													</td>
													<td class="text11">
														&nbsp;&nbsp;&nbsp;<span title="dkeh_ftx5" id="v_dkeh_ftx5" class="validation">&nbsp;Transportmidlets hjemsted</span>
													</td>
													<td class="text11">
														&nbsp;<input type="text" class="inputText" name="dkeh_ftx5" id="dkeh_ftx5" size="20" maxlength="35" value="${model.record.dkeh_ftx5}">
													</td>
												</tr>
												<tr>
						           					<td class="text11">
														&nbsp;&nbsp;&nbsp;<span title="dkeh_ftxa">&nbsp;Spiritusbeholdning stk</span>
													</td>
													<td class="text11">
														&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="dkeh_ftxa" id="dkeh_ftxa" size="10" maxlength="9" value="${model.record.dkeh_ftxa}">
													</td>
													<td class="text11">
														&nbsp;&nbsp;&nbsp;<span title="dkeh_ftxb">&nbsp;Spiritusbeholdning liter</span>
													</td>
													<td class="text11">
														&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="dkeh_ftxb" id="dkeh_ftxb" size="10" maxlength="9" value="${model.record.dkeh_ftxb}">
													</td>
												</tr>
												
												<tr>
						           					<td class="text11">
														&nbsp;&nbsp;&nbsp;<span title="dkeh_ftxc">&nbsp;Vinbeholdning stk</span>
													</td>
													<td class="text11">
														&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="dkeh_ftxc" id="dkeh_ftxc" size="10" maxlength="9" value="${model.record.dkeh_ftxc}">
													</td>
													<td class="text11">
														&nbsp;&nbsp;&nbsp;<span title="dkeh_ftxd">&nbsp;Vinbeholdning liter</span>
													</td>
													<td class="text11">
														&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="dkeh_ftxd" id="dkeh_ftxd" size="10" maxlength="9" value="${model.record.dkeh_ftxd}">
													</td>
												</tr>
												<tr>
						           					<td class="text11">
														&nbsp;&nbsp;&nbsp;<span title="dkeh_ftxe">&nbsp;Cigaretbeholdning stk</span>
													</td>
													<td class="text11">
														&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="dkeh_ftxe" id="dkeh_ftxe" size="10" maxlength="9" value="${model.record.dkeh_ftxe}">
													</td>
													<td class="text11">
														&nbsp;&nbsp;&nbsp;<span title="dkeh_ftxf">&nbsp;Cigar/cerutbeholdning stk</span>
													</td>
													<td class="text11">
														&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="dkeh_ftxf" id="dkeh_ftxf" size="10" maxlength="9" value="${model.record.dkeh_ftxf}">
													</td>
												</tr>
												<tr>
						           					<td class="text11">
														&nbsp;&nbsp;&nbsp;<span title="dkeh_ftxg">&nbsp;Tobakbeholdning kg</span>
													</td>
													<td class="text11">
														&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="dkeh_ftxg" id="dkeh_ftxg" size="10" maxlength="9" value="${model.record.dkeh_ftxg}">
													</td>
													<td class="text11">
														&nbsp;&nbsp;&nbsp;<span title="dkeh_ftxh">&nbsp;Ølbeholdning fl, ds</span>
													</td>
													<td class="text11">
														&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="dkeh_ftxh" id="dkeh_ftxh" size="10" maxlength="9" value="${model.record.dkeh_ftxh}">
													</td>
												</tr>	
												<tr>
						           					<td class="text11">
														&nbsp;&nbsp;&nbsp;<span title="dkeh_ftxi">&nbsp;Beholdning, andet</span>
													</td>
													<td class="text11">
														&nbsp;<input type="text" class="inputText" name="dkeh_ftxi" id="dkeh_ftxi" size="10" maxlength="9" value="${model.record.dkeh_ftxi}">
													</td>
													<td class="text11">
														&nbsp;&nbsp;&nbsp;<span title="dkeh_ftxj" id="v_dkeh_ftxj" class="validation">&nbsp;Beskrivning-1</span>
													</td>
													<td class="text11">
														&nbsp;<input type="text" class="inputText" name="dkeh_ftxj" id="dkeh_ftxj" size="20" maxlength="70" value="${model.record.dkeh_ftxj}">
													</td>
												</tr>
												<tr>
						           					<td class="text11">
														&nbsp;&nbsp;&nbsp;<span title="dkeh_ftxk">&nbsp;Beskrivning-2</span>
													</td>
													<td class="text11">
														&nbsp;<input type="text" class="inputText" name="dkeh_ftxk" id="dkeh_ftxk" size="20" maxlength="70" value="${model.record.dkeh_ftxk}">
													</td>
													<td class="text11">
														&nbsp;&nbsp;&nbsp;<span title="dkeh_ftxl">&nbsp;Beskrivning-3</span>
													</td>
													<td class="text11">
														&nbsp;<input type="text" class="inputText" name="dkeh_ftxl" id="dkeh_ftxl" size="20" maxlength="70" value="${model.record.dkeh_ftxl}">
													</td>
												</tr>
												<tr>
						           					<td class="text11">
														&nbsp;&nbsp;&nbsp;<span title="dkeh_ftxm">&nbsp;Beskrivning-4</span>
													</td>
													<td class="text11">
														&nbsp;<input type="text" class="inputText" name="dkeh_ftxm" id="dkeh_ftxm" size="20" maxlength="70" value="${model.record.dkeh_ftxm}">
													</td>
													
												</tr>	
											</table>
											</td>
											
											</tr>
											<tr height="10"><td></td></tr>
											<tr align="right" >
												<td class="text11">&nbsp;</td>
												<td class="text11"><button name="proviantButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('proviant');">&nbsp;<spring:message code="systema.skat.export.ok"/></button> 
												</td>
											</tr>
										</table>
										</nav>
									</div>
								</span>
							</td>									
						</tr>
						
						<tr height="30"><td>&nbsp;</td><td>&nbsp;</td></tr> 
					</table>
					</td>
				</tr>
			</table> <%-- END to the wrapper table for EXTRAORDINARY data --%>	
			</td>			
		</tr>		         
	</table>
	</form>  
	</td>
 </tr>
 
 <%-- change status admin dialog --%>	
 <tr>
	<td>
		<div id="dialogUpdateStatus" title="Dialog">
			 	<form action="skatexport_updateStatus.do" name="updateStatusForm" id="updateStatusForm" >
			 	<input type="hidden" name="currentAvd" id="currentAvd" value='${model.record.dkeh_syav}'>
			 	<input type="hidden" name="currentOpd" id="currentOpd" value='${model.record.dkeh_syop}'>
				<p class="text12" >Change status as needed.</p>
				<table>
					<tr>
						<td class="text12" align="left" >&nbsp;Status</td>
						<td class="text12MediumBlue">
							<select name="selectedStatus" id="selectedStatus">
			            			<option value="">-vælg-</option>
			 				  	<c:forEach var="record" items="${model.statusCodeList}" >
		                            <option value="${record.dkkd_kd}">${record.dkkd_kd}</option>
								</c:forEach> 
							</select>	
						</td>
					</tr>
				</table>
				</form>
		</div>
	</td>
</tr>

 <%-- proforma dialog --%>	
 <tr>
	<td >
		<div id="dialogUpdateProforma" title="Dialog">
			 	<form action="skatexport_updateProforma.do" name="updateProformaForm" id="updateProformaForm" >
			 	<input type="hidden" name="currentAvd" id="currentAvd" value='${model.record.dkeh_syav}'>
			 	<input type="hidden" name="currentOpd" id="currentOpd" value='${model.record.dkeh_syop}'>
			 	<input type="hidden" name="currentOpd" id="currentSign" value='${model.record.dkeh_sysg}'>
			 	<input type="hidden" name="currentCheckboxProforma" id="currentCheckboxProforma" value=''>
			 	
				<p class="text12" >Proforma angivelses felter</p>
				<table >
					<tr>
					<td >
						<table border="0">
						<tr>
							<td class="text12" align="left" title="dkeh_mrn" ><b>MRN</b></td>
							<td class="text12MediumBlue">
								<input type="text" class="inputText" name="dkeh_mrn" id="dkeh_mrn" size="20" maxlength="20" value="${model.record.dkeh_mrn}">
							</td>
						</tr>
						<tr>	
							<td class="text12" align="left" title="dkeh_godt" ><b>Godkendelsesdato</b></td>
							<td class="text12MediumBlue">
								<input type="text" class="inputText" name="dkeh_godt" id="dkeh_godt" size="8" maxlength="8" value="${model.record.dkeh_godt}">
							</td>
						</tr>
						</table>
					</td>
					</tr>

					<tr height="10"><td></td></tr> 
					<tr>	
						<td colspan="5">
						<table width="100%">
						<tr>
							<td valign="top" width="50%">
								<table >
								<tr>
									<td class="text12" align="left"  title="dkeh_frdt" >Frigivelsetidspunkt</td>
									<td class="text12MediumBlue">
										<input type="text" class="inputText" name="dkeh_frdt" id="dkeh_frdt" size="8" maxlength="8" value="${model.record.dkeh_frdt}">
									</td>
								</tr>
								<tr>
									<td class="text12" align="left"  title="dkeh_vadt" >Valideringstidspunkt</td>
									<td class="text12MediumBlue">
										<input type="text" class="inputText" name="dkeh_vadt" id="dkeh_vadt" size="8" maxlength="8" value="${model.record.dkeh_vadt}">
									</td>
								</tr>
								<tr>	
									<td class="text12" align="left"  title="dkeh_fedt" >Fakt.eksp.tidspunkt</td>
									<td class="text12MediumBlue">
										<input type="text" class="inputText" name="dkeh_fedt" id="dkeh_fedt" size="8" maxlength="8" value="${model.record.dkeh_frdt}">
									</td>
								</tr>
								<tr>
									<td class="text12" align="left"  title="dkeh_fudt" >Fakt.udpassagetid</td>
									<td class="text12MediumBlue">
										<input type="text" class="inputText" name="dkeh_fudt" id="dkeh_fudt" size="8" maxlength="8" value="${model.record.dkeh_fudt}">
									</td>
								</tr>
								<tr>
									<td class="text12" align="left"  title="dkeh_fvdt" >Forv.udpassagetid</td>
									<td class="text12MediumBlue">
										<input type="text" class="inputText" name="dkeh_fvdt" id="dkeh_fvdt" size="8" maxlength="8" value="${model.record.dkeh_fvdt}">
									</td>
								</tr>
								</table>
							</td>
							
							<td valign="top" width="50%">
								<table >
								<tr>
								
								<td class="text12" align="left"  title="dkeh_ctdt" >Udt.udpassagectl</td>
								<td class="text12MediumBlue">
									<input type="text" class="inputText" name="dkeh_ctdt" id="dkeh_ctdt" size="8" maxlength="8" value="${model.record.dkeh_ctdt}">
								</td>
								</tr>
								<tr>	
									<td class="text12" align="left"  title="dkeh_cfdt" >Frig.udpassagectl</td>
									<td class="text12MediumBlue">
										<input type="text" class="inputText" name="dkeh_cfdt" id="dkeh_cfdt" size="8" maxlength="8" value="${model.record.dkeh_cfdt}">
									</td>
								</tr>
								<tr>	
									<td class="text12" align="left"  title="dkeh_fadt" >Fakt.ank.utp.sted</td>
									<td class="text12MediumBlue">
										<input type="text" class="inputText" name="dkeh_fadt" id="dkeh_fadt" size="8" maxlength="8" value="${model.record.dkeh_fadt}">
									</td>
								</tr>
								<tr>	
									<td class="text12" align="left"  title="dkeh_fast" >Faktisk.utp.sted</td>
									<td class="text12MediumBlue">
										<input type="text" class="inputText" name="dkeh_fast" id="dkeh_fast" size="25" maxlength="25" value="${model.record.dkeh_fast}">
									</td>
								</tr>
								</table>
							</td>			
						</tr>				
						</table>
						</td>			
					</tr>
					</table>
				</form>				
		</div>
	</td>
</tr>
 
 <%-- -------------------------- --%>	
 <%-- print skilleark dialog    --%>	
 <%-- -------------------------- --%>	
 <tr>
	<td>
		<div id="dialogPrintSkilleArk" title="Dialog">
			<form action="skatexport_edit_printSkilleArkTopic.do" name="skilleArkForm" id="skilleArkForm" method="post">
			 	<input type="hidden" name="currentAvd" id="currentAvd" value="${model.record.dkeh_syav}">
			 	<input type="hidden" name="currentOpd" id="currentOpd" value="${model.record.dkeh_syop}">
				<table>
					<tr>
						<td class="text12" align="left" >&nbsp;Type</td>
						<td class="text12MediumBlue">
							<select name="selectedType" id="selectedType">
			            		<option value="">-velg-</option>
			            		<c:forEach var="record" items="${model.typeArchiveCodeList}" >
			 				  		<option value="${record.artype}">${record.artype}&nbsp;${record.artxt}</option>
								</c:forEach>  
							</select>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</td>
</tr> 

 <%-- -------------------------- --%>	
 <%-- upload file dialog         --%>	
 <%-- -------------------------- --%>	
	<tr>
		<td valign="bottom" >
			<div id="dialogUploadArchiveDocument" title="Dialog">
				<table align="left" class="popupFloatingWithRoundCorners3D">
				    <tr height="2"><td></td></tr>
			    	<tr>
					<td valign="top">
					<form name="uploadFileForm" id="uploadFileForm" method="post" enctype="multipart/form-data">
						<input type="hidden" name="applicationUserUpload" id="applicationUserUpload" value='${user.user}'>
						<input type="hidden" name="wsavd" id="wsavd" value='${model.record.dkeh_syav}'>
						<input type="hidden" name="wsopd" id="wsopd" value='${model.record.dkeh_syop}'>
						<input type="hidden" name="userDate" id="userDate" value=''>
						<input type="hidden" name="userTime" id="userTime" value=''>
						
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
									<tr height="5"><td></td></tr>
									<tr>	
										<td class="text11">&nbsp;Fil:</td>
										<td class="text11">
			           						&nbsp;<input type="file" name="fileUpload" id="fileUpload" />
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
		</td>
	</tr>
