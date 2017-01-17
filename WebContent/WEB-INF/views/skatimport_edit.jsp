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
	<SCRIPT type="text/javascript" src="resources/js/skatimport_edit.js?ver=${user.versionEspedsg}"></SCRIPT>
	
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
						<c:when test="${empty model.record.dkih_sysg}">href="skatimport.do?action=doFind&sign=${user.skatSign}"</c:when>
						<c:otherwise>href="skatimport.do?action=doFind&sign=${model.record.dkih_sysg}"</c:otherwise>
					</c:choose> >
					
					<img valign="bottom" src="resources/images/list.gif" border="0" alt="general list">
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.skat.import.list.tab"/></font>
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<c:choose> 
			    <c:when test="${editActionOnTopic=='doUpdate' or editActionOnTopic=='doFetch'}">
					<td width="15%" valign="bottom" class="tab" align="center" nowrap>
						<font class="tabLink">
							&nbsp;<spring:message code="systema.skat.import.created.mastertopic.tab"/>
						</font>
						<font class="text12MediumBlue">[${model.record.dkih_syop}]</font>
						<c:if test="${ model.record.dkih_syst == 'M' || empty  model.record.dkih_syst || model.record.dkih_syst == '10' || model.record.dkih_syst == '20'|| model.record.dkih_syst == '40'}">
							<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
						</c:if>
					</td>
					
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a id="alinkInvoices" tabindex=-1 style="display:block;" href="skatimport_edit_invoice.do?action=doFetch&avd=${model.record.dkih_syav}&sign=${model.record.dkih_sysg}
															&opd=${model.record.dkih_syop}&refnr=${model.record.dkih_07}
															&status=${model.record.dkih_syst}&datum=${model.record.dkih_sydt}&fabl=${model.record.dkih_222}">
							<font class="tabDisabledLink">&nbsp;<spring:message code="systema.skat.import.invoice.tab"/></font>
						</a>
					</td>
					
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a id="alinkItemLines" tabindex=-1 style="display:block;" href="skatimport_edit_items.do?action=doFetch&avd=${ model.record.dkih_syav}&sign=${ model.record.dkih_sysg}
													&opd=${ model.record.dkih_syop}&refnr=${ model.record.dkih_07}
													&status=${ model.record.dkih_syst}&datum=${ model.record.dkih_sydt}&fabl=${ model.record.dkih_222}">
							<font class="tabDisabledLink">
								&nbsp;<spring:message code="systema.skat.import.item.createnew.tab"/>
							</font>
							<%--
							<c:if test="${ svih_syst == 'M' || empty  svih_syst}">
								<img valign="bottom" src="resources/images/add.png" width="12" hight="12" border="0" alt="create new">
							</c:if>
							 --%>
							 
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a id="alinkLogging" tabindex=-1 style="display:block;" href="skatimport_logging.do?avd=${ model.record.dkih_syav}&sign=${ model.record.dkih_sysg}
													&opd=${ model.record.dkih_syop}&refnr=${ model.record.dkih_07}
													&status=${ model.record.dkih_syst}&datum=${ model.record.dkih_sydt}">
							<font class="tabDisabledLink">
								&nbsp;<spring:message code="systema.skat.import.logging.tab"/>
							</font>
							<img style="vertical-align: bottom" src="resources/images/log-icon.png" width="16" hight="16" border="0" alt="show log">
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a id="alinkArchive" tabindex=-1 style="display:block;" href="skatimport_archive.do?avd=${model.record.dkih_syav}&sign=${model.record.dkih_sysg}
													&opd=${model.record.dkih_syop}&refnr=${model.record.dkih_07}
													&status=${model.record.dkih_syst}&datum=${model.record.dkih_sydt}">
							<font class="tabDisabledLink">
								&nbsp;<spring:message code="systema.skat.import.archive.tab"/>
							</font>
							<img style="vertical-align: bottom" src="resources/images/archive.png" width="16" hight="16" border="0" alt="show archive">
						</a>
					</td>
					<td width="10%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
				</c:when>
				<c:otherwise>
					<td width="15%" valign="bottom" class="tab" align="center" nowrap>
						<font class="tabLink">&nbsp;<spring:message code="systema.skat.import.createnew.tab"/></font>
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
	<form name="skatImportSaveNewTopicForm" id="skatImportSaveNewTopicForm" method="post">
	<table width="100%" class="tabThinBorderWhite" border="0" cellspacing="0" cellpadding="0">
			<%-- --- HIDDEN FORM FIELDS (not visible in form but important with an UPDATE ----- --%>			
			<%-- general (from user profile) --%>
			<input type="hidden" name="action" id="action" value='doUpdate'>
			<input type="hidden" name="applicationUser" id="applicationUser" value='${user.user}'>
			<input type="hidden" name="opd" id="opd" value='${model.record.dkih_syop}'>
			<%-- topic specific (syop and refnr) --%>
			<input type="hidden" name="dkih_syav" id="dkih_syav" value='${model.record.dkih_syav}'>
			<input type="hidden" name="dkih_syop" id="dkih_syop" value='${model.record.dkih_syop}'>
			<input type="hidden" name="dkih_sysg" id="dkih_sysg" value='${model.record.dkih_sysg}'>
			<input type="hidden" name="dkih_syst" id="dkih_syst" value='${model.record.dkih_syst}'>
			<input type="hidden" name="dkih_sydt" id="dkih_sydt" value='${model.record.dkih_sydt}'>
			<input type="hidden" name="dkih_07" id="dkih_07" value='${model.record.dkih_07}'>

		<tr height="4">
			<td colspan="2">&nbsp;
				<%-- test indicator /per avdelning --%> 
				<c:forEach var="record" items="${avdListSessionTestFlag}" >
					<c:if test="${record.avd == model.record.dkih_syav}">	
						<c:if test="${record.tst == '1'}">&nbsp;&nbsp;	
							<c:set var="isTestAvd" value="1" scope="request" />
						</c:if>
					</c:if>
				</c:forEach>		
			</td>
		</tr>
		
		<c:choose>
		<%-- UPDATE MODE --%> 
	    <c:when test="${editActionOnTopic=='doUpdate' or editActionOnTopic=='doFetch'}">
	    	<input type="hidden" name="avd" id="avd" value='${model.record.dkih_syav}'>
			<input type="hidden" name="sign" id="sign" value='${model.record.dkih_sysg}'>
			<tr>
				<td align="left" class="text12MediumBlue">
					&nbsp;&nbsp;<span title="dkih_syav">Afdeling:&nbsp;</span><b>${model.record.dkih_syav}</b>&nbsp;&nbsp;<span title="dkih_syop">Angivelse:&nbsp;</span><b>${model.record.dkih_syop}</b>
					&nbsp;&nbsp;<span title="dkih_07">Reference-nr:&nbsp;</span><b>${model.record.dkih_07}</b>
					&nbsp;&nbsp;<span title="dkih_sysg">Sign:&nbsp;</span><b>${model.record.dkih_sysg}</b>
				</td>
			</tr>
			<tr>	
				<td align="left" class="text12MediumBlue">				
					&nbsp;&nbsp;<span title="dkih_sydt">Dato:&nbsp;</span><b>${model.record.dkih_sydt}</b>
					&nbsp;&nbsp;<img onMouseOver="showPop('status_info');" onMouseOut="hidePop('status_info');" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					<span title="dkih_syst">Stat</span><a id="updateStatusLink" runat="server" href="#"><font class="text12MediumBlue">u</font></a>s:
					<b>
						<c:choose>
							<c:when test="${empty  model.record.dkih_syst}">
								&nbsp;
							</c:when>
							<c:otherwise>
								${model.record.dkih_syst}
							</c:otherwise>
						</c:choose>
					</b>
					<div class="text11" style="position: relative;" align="left">
					<span style="position:absolute;top:2px; width:500px;" id="status_info" class="popupWithInputText text11"  >    	
		           		 Kun status <b>M</b>,<b>10</b>,<b>20</b>eller <b>' '</b> kan redigeres.
		           		 <br/><br/> 
		           			<table width="90%" align="center" cellspacing="0" border="0" cellpadding="0">
		           				<c:forEach var="record" items="${model.statusCodeList}" varStatus="counter" >
		           				<c:choose>
		           					<c:when test="${counter.count%2 != 0}" >
			           					<tr>
			           					<td class="tableCellFirst" width="50%">
		           				 			<b>${record.dkkd_kd}</b>&nbsp;${record.dkkf_txt}
		           						</td>
		           					</c:when>
			           				<c:otherwise>
										<td class="tableCell" width="50%">
		           				 			<b>${record.dkkd_kd}</b>&nbsp;${record.dkkf_txt}
		           						</td>
		           						</tr>
			           				</c:otherwise>
		           				</c:choose>
		           				</c:forEach>
		           			</table>
					</span>	
					</div>						
				</td>

				<td align="right" valign="top" >
					<c:if test="${'1' != isTestAvd}">
						<%--This checkbox appears only in real production. Otherwise use the Testavdelning --%>
						<input type="checkbox" name="dkih_0035" id="dkih_0035" value="1" <c:if test="${model.record.dkih_0035 == '1'}"> checked </c:if> ><font class="text12MediumBlue"><b>TEST flag</b></font>&nbsp;&nbsp;&nbsp;						
					</c:if>
					<a tabindex=-1 href="skatimport_edit_printTopic.do?avd=${model.record.dkih_syav}&opd=${model.record.dkih_syop}">
					 	<img style="cursor:pointer;" src="resources/images/printer.png" width="30" hight="30" border="0" alt="Print">
						&nbsp;&nbsp;&nbsp;
					</a>
				</td>
			</tr>
			<%-- test indicator /per avdelning --%> 
			<c:if test="${'1' == isTestAvd}">
				<tr>
					<td align="left" class="text14Red" >
						&nbsp;&nbsp;<b>[TEST Afdeling]</b>
					</td>
				</tr>
			</c:if>
				
		</c:when>
		<%-- CREATE MODE --%> 
		<c:otherwise>
			<tr >
				<td align="left" class="text12MediumBlue">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Afdeling:&nbsp;
           			<select name="avd" id="avd" TABINDEX=1>
	            		<option value="">-vælg-</option>
	 				  	<c:forEach var="record" items="${model.avdList}" >
                             <option value="${record.avd}"<c:if test="${model.record.dkih_syav == record.avd}"> selected </c:if> >${record.avd}<c:if test="${record.tst == '1'}">&nbsp;(test)</c:if></option>
						</c:forEach> 
					</select>
					&nbsp;Sign:&nbsp;
           			<select name="sign" id="sign" TABINDEX=2>
	            		<option value="">-vælg-</option>
	 				  	<c:forEach var="record" items="${model.signList}" >
                             	 	<option value="${record.sign}"<c:if test="${model.record.dkih_sysg == record.sign}"> selected </c:if> >${record.sign}</option>
						</c:forEach> 
					</select>
				</td>
				<td>&nbsp;</td>
			</tr>	
		</c:otherwise>
		</c:choose>

		<tr height="10"><td colspan="2">&nbsp;</td></tr>
		<%-- --------------- --%>
		<%-- LEFT SIDE CELL --%>
		<%-- --------------- --%>
		<tr>
		<td width="55%" valign="top">
		<table width="96%" border="0" cellspacing="0" cellpadding="0">
			<tr>
	            <td width="5">&nbsp;</td>
	            <td >
	            	<table align="left" border="0" cellspacing="0" cellpadding="0">
	            		
				 		<tr>
				 			<td class="text12">
				 				<img onMouseOver="showPop('1_1_info');" onMouseOut="hidePop('1_1_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
			 					<b>1.1</b><font class="text16RedBold" >*</font><span title="dkih_r011">Ang.type&nbsp;</span>
			 					<div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="1_1_info" class="popupWithInputText text11"  >
					           		<ul>
				           				<li><b>EU</b><br>indførsel af varer fra EFTA-lande..
				           				</li>
				           				<li><b>CO</b><br>indførsel af T2-varer, der er underkastet særlige foranstaltninger i en overgangsperiode efter nye medlemsstaters tiltrædelse.
				           				
				           				</li>
				           				<li><b>IM</b><br>indførsel af varer med toldmæssig status T1 til EU's toldområde fra alle lande undtagen EFTA-lande: Island, Norge og Schweiz (herunder Liechtenstein).
				           				</li>
				           			
				           			</ul>
								</span>	
								</div>
								
				 			</td>
				 			<td>
				 				<select name="dkih_r011" id="dkih_r011" TABINDEX=3>
				 				  <option value="">-vælg-</option>
								  <option value="EU"<c:if test="${model.record.dkih_r011 == 'EU'}"> selected </c:if> >EU</option>
								  <option value="CO"<c:if test="${model.record.dkih_r011 == 'CO'}"> selected </c:if> >CO</option>
								  <option value="IM"<c:if test="${model.record.dkih_r011 == 'IM'}"> selected </c:if> >IM</option>
								</select>
			 				</td>
			 				<td class="text12">&nbsp;
			 				<img onMouseOver="showPop('meddTyp_info');" onMouseOut="hidePop('meddTyp_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
			 				<font class="text16RedBold" >*</font><span title="dkih_aart">Ang.art&nbsp;</span>
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
				 				<select name="dkih_aart" id="dkih_aart" TABINDEX=10>
				 				  <option value="">-vælg-</option>
					 				  	<c:forEach var="record" items="${model.angivelsesArterCodeList}" >
					 				  		<option value="${record.dkkd_kd}"<c:if test="${model.record.dkih_aart == record.dkkd_kd}"> selected </c:if> >${record.dkkd_kd}&nbsp;${record.dkkd_kd2}</option>
										</c:forEach>  
								</select>
				 			</td>
				 			
		 				</tr>
		 				<tr>
		 					<td class="text12">
		 						<img onMouseOver="showPop('1_2_info');" onMouseOut="hidePop('1_2_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 				<b>1.2</b>&nbsp;
				 				<span title="dkih_r012" id="v_dkih_r012" class="validation">EU ang.art&nbsp;</span>
				 				<div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="1_2_info" class="popupWithInputText text11"  >
					           		<ul>
					           			Anden underrubrik.
					           			<br/>
					           			<br/>
					           			<b>Angivelsesart (fält 1:2)</b>
					           			<br/>
					           			<li>
					           				<b>A</b> i forbindelse med en almindelig angivelse (almindelig fremgangsmåde, artikel 62 i kodeksen)
				           				</li>
				           				<li>
					           				<b>B</b> i forbindelse med en ufuldstændig angivelse (forenklet fremgangsmåde, artikel 76, stk. 1, litra a), i kodeksen
					           			</li>
					           			<li>
					           				<b>X</b> i forbindelse med en supplerende angivelse inden for rammerne af en forenklet fremgangsmåde, som beskrevet under kode
					           			</li>
					           			<li>
					           				<b>Z</b> i forbindelse med en supplerende angivelse inden for rammerne af en forenklet fremgangsmåde, som beskrevet i artikel 76, stk. 1, litra c
					           			</li>
					           		</ul>
								</span>
								</div>
				 				
				 			</td>
				 			<td>
				 				<select name="dkih_r012" id="dkih_r012" >
				 					<option value="">-vælg-</option>
								  <option value="A"<c:if test="${ model.record.dkih_r012 == 'A'}"> selected </c:if> >A</option>
								  <option value="B"<c:if test="${ model.record.dkih_r012 == 'B'}"> selected </c:if> >B</option>
								  <option value="X"<c:if test="${ model.record.dkih_r012 == 'X'}"> selected </c:if> >X</option>
								  <option value="Z"<c:if test="${ model.record.dkih_r012 == 'Z'}"> selected </c:if> >Z</option>
								</select>
			 				</td>
			 				<td class="text12">&nbsp;
			 					<img onMouseOver="showPop('ens_flag_info');" onMouseOut="hidePop('ens_flag_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 				<span title="dkih_ensf">ENS-flag&nbsp;</span>
				 				<div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="ens_flag_info" class="popupWithInputText text11"  >
						           		<b>ENS-flag</b>
						           		<ul>
						           			<li><b>1</b>
						           				&nbsp;ENS oplysninger medfølger til angivelsen (ellers udfyldes dette felt ikke).
						           			</li>
						           		</ul>
								</span>
								</div>
				 				
			 				</td>
			 				<td>
				 				<select name="dkih_ensf" id="dkih_ensf" >
				 				  <option selected value="">-vælg-</option>
								  <option value="1"<c:if test="${model.record.dkih_ensf == 1}"> selected </c:if> >1</option>
								</select>
			 				</td>
			 				
		 				</tr>
		 				<tr>
			 				<td class="text12" align="center"><b>A.1</b>
				 				<font class="text16RedBold" >*</font>
			 					<span title="dkih_a">Eksped.sted&nbsp;</span>
			 						
			 				</td>
			 				<td class="text12">
			            			<select name="dkih_a" id="dkih_a">
					            		<option value="">-vælg-</option>
					 				  	<c:forEach var="record" items="${model.toldstedCodeList}" >
					 				  		<option value="${record.dkkd_kd}"<c:if test="${model.record.dkih_a == record.dkkd_kd}"> selected </c:if> >${record.dkkd_kd}</option>
										</c:forEach>  
								</select>
								<a tabindex="-1" id="dkih_aIdLink">
									<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
								</a>	
			 				</td> 
			 				<td class="text12">
			 					&nbsp;<img onMouseOver="showPop('ajour_info');" onMouseOut="hidePop('ajour_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 				<span title="dkih_ajou">Ajour.type&nbsp;</span>
				 				<div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="ajour_info" class="popupWithInputText text11"  >
					           			<b>Ajourføringstype</b>
					           			<ul>
					           				<li>
					           					<b>1</b> Erstatter tidligere angivelse.
					           				</li>	
											<li>
												<b>8</b> Fastholder tidligere angivelse efter sandsynlig fejl.
											</li>
											<li>
												<b>9</b> Ajourfør tidligere angivelse med faktisk ankomsttidspunkt.
											</li>
										</ul>
								</span>
								</div>
								
				 			</td>
				 			<td>	
				 				<select name="dkih_ajou" id="dkih_ajou" >
			 					  <option value="">-vælg-</option>
								  <option value="1"<c:if test="${ model.record.dkih_ajou == '1'}"> selected </c:if> >1</option>
								  <option value="8"<c:if test="${ model.record.dkih_ajou == '8'}"> selected </c:if> >8</option>
								  <option value="9"<c:if test="${ model.record.dkih_ajou == '9'}"> selected </c:if> >9</option>
								  
						  		</select>
			 				</td>
		 				</tr>
						<tr>
		 					<td class="text12" >
		 						<img onMouseOver="showPop('dkih_dtm1_info');" onMouseOut="hidePop('dkih_dtm1_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 				<span title="dkih_dtm1" id="v_dkih_dtm1" class="validation">Forventet ank.dato&nbsp;</span>
				 				<div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="dkih_dtm1_info" class="popupWithInputText text11"  >
					           			<b>Forventet ankomstdato</b>
					           			<br/><br/>
					           			Hvis angivelsen angives, før varen ankommer til Danmark, skal der angives et forventet ankomsttidspunkt.
								</span>
				 				</div>
				 			</td>
			 				<td class="text12" align="left">
			 					<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="dkih_dtm1" id="dkih_dtm1" size="16" maxlength="12" value="${model.record.dkih_dtm1}">
		            			</td>
				 			
				 			<td class="text12">
		 						<img onMouseOver="showPop('dkih_dtm2_info');" onMouseOut="hidePop('dkih_dtm2_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 				<span title="dkih_dtm2">Faktisk ank.dato&nbsp;</span>
				 				<div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="dkih_dtm2_info" class="popupWithInputText text11"  >
					           			<b>Faktisk ankomstdato</b>
					           			<br/><br/>
					           			Hvis angivelsen angives i forbindelse med varens ankomst, eller hvis der tidligere er angivet et forventet ankomsttidspunkt i en angivelse, 
					           			kan der angives et faktisk ankomsttidspunkt.
								</span>
								</div>
			 				</td>
			 				<td class="text12" align="left">
			            			<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="dkih_dtm2" id="dkih_dtm2" size="16" maxlength="12" value="${model.record.dkih_dtm2}">
		            			</td>
		 				</tr>	
		 				<tr height="10"><td></td></tr>
		 				<tr>
		 					<td align="left" class="text12">&nbsp;
		 						<span title="dkih_syav+dkih_syop+dkih_sysg">Intern ref.&nbsp;</span>
				 			</td>
				 			<td colspan="4">
				 				<input readonly type="text" class="inputTextReadOnly" name="dkih_1004_phantom" id="dkih_1004_phantom" size="35" maxlength="35" value="${model.record.dkih_syav}/${model.record.dkih_syop}/${model.record.dkih_sysg}">
			 				</td>
			 			</tr>
			 			<tr>	
			 				<td align="left" class="text12">&nbsp;
		 						<span title="dkih_xref">Ekstern ref.&nbsp;</span>
				 			</td>
				 			<td colspan="4">
				 				<input type="text" class="inputText" name="dkih_xref" id="dkih_xref" size="35" maxlength="35" value="${model.record.dkih_xref}">
			 				</td>
				 		</tr>
				 		<tr>	
				 			<td align="left" class="text12">&nbsp;
			 					<img onMouseOver="showPop('beg_fasthold_info_MAIN');" onMouseOut="hidePop('beg_fasthold_info_MAIN');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
		 						<span title="dkih_bega" id="dkih_bega_label">Beg. for fastholdelse&nbsp;</span>
		 						<div class="text11" style="position: relative;" align="left">
		 						<span style="position:absolute; left:0px; top:0px;" id="beg_fasthold_info_MAIN" class="popupWithInputText"  >
		 							<font class="text11">
					           			<b>Begrundelse for fastholdelse</b>
					           			<div>
					           			<p>Angivelse af begrundelse for fastholdelse af tidligere angivelse efter sandsynlige fejl.</p>
					           			<p>Dette felt er obligatorisk for Ajour.type=<b>8</b>
					           			</div>
				           			</font>
								</span>
								</div>
		 						
				 			</td>
				 			<td colspan="4">
				 				<input <c:if test="${model.record.dkih_ajou != '8'}"> disabled </c:if>  type="text" class="inputText" name="dkih_bega" id="dkih_bega" size="35" maxlength="70" value="${model.record.dkih_bega}">
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
							        	<input type="hidden" name="orig_dkih_avkn" id="orig_dkih_avkn" value='${model.record.dkih_avkn}'>
							        	<input type="hidden" name="orig_dkih_02b" id="orig_dkih_02b" value='${model.record.dkih_02b}'>
							        	<input type="hidden" name="orig_dkih_02c" id="orig_dkih_02c" value='${model.record.dkih_02c}'>
							        	<input type="hidden" name="orig_dkih_02d" id="orig_dkih_02d" value='${model.record.dkih_02d}'>
							        	<input type="hidden" name="orig_dkih_02e" id="orig_dkih_02e" value='${model.record.dkih_02e}'>
							        	<input type="hidden" name="orig_dkih_02f" id="orig_dkih_02f" value='${model.record.dkih_02f}'>
							        	
							        	
							        	
							            <td class="text12" align="left" >&nbsp;&nbsp;
							            <span title="dkih_avkn">Kundenummer</span></td>
							            <td class="text12" align="left" >&nbsp;&nbsp;
							            <span title="dkih_02b" id="v_dkih_02b" class="validation">Navn&nbsp;</span>
							            	<a tabindex="-1" id="dkih_02bIdLink">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
											</a>	
							            </td>
							        </tr>
							        <tr>
							            <td class="text12" align="left"><input type="text" class="inputTextMediumBlue" name="dkih_avkn" id="dkih_avkn" size="8" maxlength="8" value="${model.record.dkih_avkn}"></td>
							            <td class="text12" align="left"><input type="text" class="inputTextMediumBlue" name="dkih_02b" id="dkih_02b" size="30" maxlength="35" value="${model.record.dkih_02b}"></td>
							            
							        </tr>
							        
							        <%--
							        <tr>
							            <td class="text12" align="left" >&nbsp;&nbsp;<font class="text16RedBold" >*</font>EORI</td>
							            <td class="text12" align="left" >&nbsp;&nbsp;</td>
							        </tr>
							        <tr>
							            <td align="left"><input type="text" class="inputTextMediumBlue" name="svih_aveo" id="svih_aveo" size="20" maxlength="17" value='${ svih_aveo}'></td>
							            <td align="left">&nbsp;</td>
							        </tr>
							        --%>
							         
							        <tr height="4"><td>&nbsp;</td></tr>
							        <tr>
							            <td class="text12" align="left" >&nbsp;&nbsp;
							            <span title="dkih_02c" id="v_dkih_02c" class="validation">Adresse</span></td>
							            <td class="text12" align="left" >&nbsp;&nbsp;
						            		<span title="dkih_02d" id="v_dkih_02d" class="validation">Postnummer</span></td>
								            		
							        </tr>
							        <tr>
							            <td align="left"><input type="text" class="inputTextMediumBlue" name="dkih_02c" id="dkih_02c" size="30" maxlength="35" value="${model.record.dkih_02c}"></td>
							            <td align="left"><input type="text" class="inputTextMediumBlue" name="dkih_02d" id="dkih_02d" size="10" maxlength="9" value="${model.record.dkih_02d}"></td> 
								            		
							        </tr>
							        <tr>
							        	<td>
							        		<table>
								        		<tr>
								            		<td class="text12" align="left" >&nbsp;&nbsp;
								            		<span title="dkih_02e" id="v_dkih_02e" class="validation">By</span></td>
								            		<td class="text12" >&nbsp;</td>
								            	</tr>
								        		<tr>
								            		<td align="left" colspan="2">
								            			<input type="text" class="inputTextMediumBlue" name="dkih_02e" id="dkih_02e" size="30" maxlength="35" value="${model.record.dkih_02e}">
								            		</td> 
								            		<td class="text12" >&nbsp;</td>
								        		</tr>    	
							            	</table>
						            </td>
						            <td>
							            	<table>
								        		<tr>
								            		<td class="text12" align="left" >&nbsp;&nbsp;
								            		<span title="dkih_02f" id="v_dkih_02f" class="validation">Land</span>
																													 			
								            		</td>
								            		
								            	</tr>
								        		<tr>
								        			<td align="left">
								            			<select name="dkih_02f" id="dkih_02f">
										            		<option value="">-vælg-</option>
										 				  	<c:forEach var="country" items="${model.countryCodeList}" >
										 				  		<option value="${country.dkkd_kd}"<c:if test="${model.record.dkih_02f == country.dkkd_kd}"> selected </c:if> >${country.dkkd_kd}</option>
															</c:forEach>  

														</select>
														<a tabindex="-1" id="dkih_02fIdLink" OnClick="triggerChildWindowCountryCodes(this)">
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
				 				<b>&nbsp;8.</b><font class="text16RedBold" >*</font>Modtager&nbsp;<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
				 				<div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="8_info" class="popupWithInputText text11"  >
						           		<b>Modtager</b>
						           		<ol>
						           			<li>Ved <b>elektronisk angivelse></b> anfører man kun virksomhedens CVR/SE-nummer med foranstillet ISO landekode DK.</li>
											<li>Hvis der er tale om en <b>privat varemodtager</b>, anfører man det fiktive SE-nummer DK09999981 samt navn og adresse.</li>
											<br/>
											<li>Ved <b>angivelse på enhedsdokumentet</b> anfører klarereren her importørens navn, adresse og CVR/SE-nummer med foranstillet ISO landekode DK.</li>
											
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
							        	<input type="hidden" name="orig_dkih_mokn" id="orig_dkih_mokn" value="${model.record.dkih_mokn}">
							        	<input type="hidden" name="orig_dkih_08a" id="orig_dkih_08a" value="${model.record.dkih_08a}">
							        	<input type="hidden" name="orig_dkih_08b" id="orig_dkih_08b" value="${model.record.dkih_08b}">
							        	<input type="hidden" name="orig_dkih_08c" id="orig_dkih_08c" value="${model.record.dkih_08c}">
							        	<input type="hidden" name="orig_dkih_08d" id="orig_dkih_08d" value="${model.record.dkih_08d}">
							        	<input type="hidden" name="orig_dkih_08e" id="orig_dkih_08e" value="${model.record.dkih_08e}">
							        	<input type="hidden" name="orig_dkih_08f" id="orig_dkih_08f" value="${model.record.dkih_08f}">
							        	
							            <td class="text12" align="left" >&nbsp;&nbsp;
							            <span title="dkih_mokn">Kundenummer</span></td>
							            <td class="text12" align="left" >&nbsp;&nbsp;<span title="dkih_08b">Navn</span>&nbsp;
							            	<a tabindex="-1" id="dkih_08bIdLink">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
											</a>	
										</td>
							        </tr>
							        <tr>
							            <td align="left"><input type="text" class="inputTextMediumBlue" name="dkih_mokn" id="dkih_mokn" size="8" maxlength="8" value="${model.record.dkih_mokn}"></td>
							            <td align="left"><input type="text" class="inputTextMediumBlue" name="dkih_08b" id="dkih_08b" size="30" maxlength="35" value="${model.record.dkih_08b}"></td>
							        </tr>
							        <tr>
							            <td class="text12" align="left" >&nbsp;&nbsp;<font class="text16RedBold" >*</font>
							            <span title="dkih_08a">CVR/SE-nr</span></td>
							            <td class="text12" align="left" >&nbsp;&nbsp;</td>
							        </tr>
							        <tr>
							            <td align="left"><input type="text" class="inputTextMediumBlue" name="dkih_08a" id="dkih_08a" size="20" maxlength="17" value="${model.record.dkih_08a}"></td>
							            <td align="left">&nbsp;</td>
							        </tr>
							        <tr height="4"><td>&nbsp;</td></tr>
							        <tr>
							            <td class="text12" align="left" >&nbsp;&nbsp;
							            <span title="dkih_08c">Adresse</span></td>
							            <td class="text12" align="left" >&nbsp;&nbsp;
						        			<span title="dkih_08d">Postnummer</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
								            		
							        </tr>
							        <tr>
							            <td align="left"><input type="text" class="inputTextMediumBlue" name="dkih_08c" id="dkih_08c" size="30" maxlength="35" value="${model.record.dkih_08c}"></td>
										<td align="left"><input type="text" class="inputTextMediumBlue" name="dkih_08d" id="dkih_08d" size="10" maxlength="9" value="${model.record.dkih_08d}"></td> 
							        </tr>
							        <tr>
							        	<td>
							        		<table>
								        		<tr>
								            		<td class="text12" align="left" >&nbsp;
								            		<span title="dkih_08e">By</span></td>
												<td align="left">&nbsp;</td>
								            	</tr>
								        		<tr>
								            		<td align="left" colspan="2"><input type="text" class="inputTextMediumBlue" name="dkih_08e" id="dkih_08e" size="30" maxlength="35" value="${model.record.dkih_08e}"></td> 
								            		
								        		</tr>    	
							            	</table>
						            </td>
						            <td>
							            	<table>
								        		<tr>
								        			<td class="text12" align="left" >&nbsp;&nbsp;
								            		<span title="dkih_08f">Land</span>
																												 			
												</td>
								            	</tr>
								        		<tr>
								        			<td align="left">
								            			<select name="dkih_08f" id="dkih_08f">
										            		<option value="">-vælg-</option>
										 				  	<c:forEach var="country" items="${model.countryCodeList}" >
										 				  		<option value="${country.dkkd_kd}"<c:if test="${model.record.dkih_08f == country.dkkd_kd}"> selected </c:if> >${country.dkkd_kd}</option>
															</c:forEach>  
													</select>
													<a tabindex="-1" id="dkih_08fIdLink" OnClick="triggerChildWindowCountryCodes(this)">
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
		 						<b>&nbsp;14.</b><font class="text16RedBold" >*</font>
				 				Klarereren / Repræsentant&nbsp;<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
				 				
				 				<div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="14_b_info" class="popupWithInputText text11"  >
				           			<b>14.&nbsp;Klarereren/repræsentanten</b> er den juridiske person, der afgiver fortoldningen.<br/><br/>
									I rubrikken anføres oplysning om klarererens/repræsentantens <b>Repr. CVR/SE-nr.</b>, der skal angives med foranstillet ISO landekode DK samt <b>Repræsentationsstatus</b>
									<br/><br/>
									Klareren angiver Repræsentationsstatus med kode 1, 2 eller 3:
									<ol>
					           			<li>Klarerer</li>
					           			<li>Repræsentant (direkte repræsentation), som beskrevet iTK artikel 5, stk. 2, første led.</li>
					           			<li>Repræsentant (indirekte repræsentation), som beskrevet iTK artikel 5, stk. 2, andet led.</li>
					           			
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
					 		<td>
						 		<table width="100%" border="0" cellspacing="0" cellpadding="0">
							 		<tr height="15">
							            <td class="text12" align="left">&nbsp;</td> 
							        </tr>
							        <tr>
							            <td class="text12" align="left" >&nbsp;&nbsp;<font class="text16RedBold" >*</font>
							            <span title="dkih_14a">Repr. CVR/SE-nr.</span></td>
							            <td class="text12" align="left" >&nbsp;&nbsp;<font class="text16RedBold" >*</font>
							            <span title="dkih_14b">Repræsentationsstatus</span></td>
							        </tr>
							        <tr>
							            <td align="left"><input type="text" class="inputTextMediumBlue" name="dkih_14a" id="dkih_14a" size="19" maxlength="18" value="${model.record.dkih_14a}"></td>
							            <td align="left">
							            	<select name="dkih_14b" id="dkih_14b">
			 									<option value="">-vælg-</option>
							  					<option value="1"
								  				<c:if test="${model.record.dkih_14b == '1'}"> selected </c:if> >1-Klarerer</option>
								  				<option value="2"
								  				<c:if test="${model.record.dkih_14b == '2'}"> selected </c:if> >2-Direkte repr.</option>
								  				<option value="3"
								  				<c:if test="${model.record.dkih_14b == '3'}"> selected </c:if> >3-Indirekte repr.</option>
											</select>
										</td>
							        </tr>
							        <%--
							        <tr>
							            <td align="left" class="text12" align="left" >&nbsp;&nbsp;<font class="text16RedBold" >*</font>
							            <span title="dkih_??">Handläggare</span></td>
							            <td align="left"class="text12" align="left" >&nbsp;&nbsp;<font class="text16RedBold" >*</font>
							            <span title="dkih_??">Telefon</span></td>
							            <td>&nbsp;</td>
							        </tr>
							        <tr>
							            <td align="left" ><input type="text" class="inputTextMediumBlue" name="svih_omha" id="svih_omha" size="25" maxlength="35" value="${ svih_omha}"></td>
							            <td align="left" ><input type="text" class="inputTextMediumBlue" name="svih_omtl" id="svih_omtl" size="20" maxlength="35" value="${ svih_omtl}"></td>
							            <td>&nbsp;</td>
							        </tr>
							         --%>
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
							            <span title="dkih_28b">Fakt.nr.&nbsp;</span></td>
							            <td ><input type="text" class="inputTextMediumBlue" name="dkih_28b" id="dkih_28b" size="18" maxlength="17" value='${ model.record.dkih_28b}'></td>
							            <td class="text12" align="left" >&nbsp;&nbsp;&nbsp;
							            <img onMouseOver="showPop('faktTyp_info');" onMouseOut="hidePop('faktTyp_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
						 				<span title="dkih_28a">Fakt.type&nbsp;</span>
							            <div class="text11" style="position:relative;" align="left">
							            <span  style="position:absolute; left:0px; top:0px;" id="faktTyp_info" class="popupWithInputText"  >
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
							 				<select name="dkih_28a" id="dkih_28a">
							 					<option value="">-vælg-</option><option value="N380"<c:if test="${ model.record.dkih_28a == 'N380'}"> selected </c:if> >N380</option>
											  	<option value="N325"<c:if test="${ model.record.dkih_28a == 'N325'}"> selected </c:if> >N325</option>
											</select>
			 							</td>
			 							<td class="text12">&nbsp;&nbsp;<span title="dkih_28c">Fakt.dato</span>
			 								<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="dkih_28c" id="dkih_28c" size="9" maxlength="8" value="${model.record.dkih_28c}">
			 							</td>
							        </tr>
							        <tr height="5">
							            <td class="text12Bold" align="left" >&nbsp;</td> 
							        </tr>
							        <tr>
							            <td colspan="5" class="text12" align="left" >&nbsp;
							            <span title="fakturaListTotSum/fakturaListTotValidCurrency"></span>Fakturasum. fra Fakturaer&nbsp;</span>
							            <input readonly type="text" class="inputTextReadOnly"  name="fakturaListTotSum" id="fakturaListTotSum" size="15" value='${ model.record.fakturaListTotSum}'>
							            &nbsp;&nbsp;
							            <input readonly type="text" class="inputTextReadOnly"  name="fakturaListTotValidCurrency" id="fakturaListTotValidCurrency" size="5" value='${ model.record.fakturaListTotValidCurrency}'>
							            &nbsp;<button title="Hente summen fra Fakturaer" name="getFakturaListSumButton" id="getFakturaListSumButton" class="buttonGrayWithGreenFrame" type="button" >Hente summen</button>
							            <input type="hidden" name="fakturaListTotKurs" id="fakturaListTotKurs" value='${ model.record.fakturaListTotKurs}'>
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
		  	<%-- INVOICE AMOUNT Fields --%>
			<tr height="10"><td></td></tr>
            <tr>
	            <td width="5">&nbsp;</td>
	            <td >
	                <table align="left" border="0" cellspacing="0" cellpadding="0">
				 		<tr>
				 			<td class="text12">
				 				<b>&nbsp;22.2</b>&nbsp;
				 				<span title="dkih_222" id="v_dkih_222" class="validation">Fakturabeløb&nbsp;</span>
				 			</td>
				 			<td align="left" >
				 				<input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue" name="dkih_222" id="dkih_222" size="20" maxlength="20" value="${model.record.dkih_222}">
				 			</td>
				 			<td class="text12">
				 				<b>&nbsp;22.1</b>&nbsp;
				 				<span title="dkih_221" id="v_dkih_221" class="validation">Fakuramøntsort</span>
				 				<%-- Note: onChange event in jQuery for this currency list --%>
				 				<select name="dkih_221" id="dkih_221" >
				 				  <option value="">-vælg-</option>	
				 				  <c:forEach var="currency" items="${model.currencyCodeList}" >
			 				  		<option value="${currency.dkkd_kd}"<c:if test="${ model.record.dkih_221 == currency.dkkd_kd}"> selected </c:if> >${currency.dkkd_kd}</option>
								  </c:forEach>  
								</select>
								<a tabindex="-1" id="dkih_221IdLink">
									<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
								</a>																		 			
								
			 				</td>
		 				</tr>
		 				<tr>
			 				<td class="text12" align="right">
				 				<span title="dkih_221b">Kurs&nbsp;</span>
				 			</td>
				 			<td class="text12" align="left" ><input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue" name="dkih_221b" id="dkih_221b" size="20" maxlength="20" value="${model.record.dkih_221b}"></td>
				 			<td class="text12" align="left" >&nbsp;
					 			<span title="dkih_221c">Faktor</span>
					 			<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="dkih_221c" id="dkih_221c" size="8" maxlength="7" value="${model.record.dkih_221c}">
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
			 				    <%--only status = M or emtpy status is allowed --%> 
			 				    <c:choose>
				 				    <c:when test="${ model.record.dkih_syst == 'M' || empty  model.record.dkih_syst || model.record.dkih_syst == '10' || model.record.dkih_syst == '20' || model.record.dkih_syst == '40'}">
					 				    	<input tabindex=-1 class="inputFormSubmit" type="submit" name="submit" id="submit" onclick="javascript: form.action='skatimport_edit.do';" value='<spring:message code="systema.skat.import.createnew.submit"/>'/>
					 				    	&nbsp;&nbsp;
					 				    	<c:if test="${not empty  model.record.dkih_syop && model.record.validUpdate}">
					 				    		<input tabindex=-2 class="inputFormSubmit" type="submit" name="send" id="send" onclick="javascript: form.action='skatimport_send.do';" value='<spring:message code="systema.skat.import.createnew.send"/>'/>
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
					            <img onMouseOver="showPop('15_a_info');" onMouseOut="hidePop('15_a_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
			 					<b>15.</b><font class="text16RedBold" >*</font><span title="dkih_15">Afsend.land</span>&nbsp;
			 					<div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="15_a_info" class="popupWithInputText text11"  >
				           			<b>15. Afsendelsesland</b>
				           			<br/><br/>
				           			Her anføres koden for det land, hvorfra varerne er afsendt med Danmark som bestemmelsesland. 
				           			Koderne, som skal benyttes, fremgår af SKATs hjemmeside.
				           			<br/><br/>
				           			Hvis varerne under transporten til Danmark har været genstand for opbevaring eller handelsmæssige transaktioner, 
				           			der ikke har noget at gøre med varernes transport (fx opmagasinering, overdragelse mv.), 
				           			skal man som afsendelsesland angive det sidste land, hvor opbevaring mv. har fundet sted.
								</span>	
								</div>	
					            </td>
					            
					            <td >
					            	<select name="dkih_15" id="dkih_15">
				 						<option value="">-vælg-</option>
					 				  	<c:forEach var="country" items="${model.countryCodeList}" >
					 				  		<option value="${country.dkkd_kd}"<c:if test="${model.record.dkih_15 == country.dkkd_kd}"> selected </c:if> >${country.dkkd_kd}</option>
										</c:forEach>  
									</select>
									<a tabindex="-1" id="dkih_15IdLink" OnClick="triggerChildWindowCountryCodes(this)">
										<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
									</a>																 			
								</td>
							</tr>
							<tr height="8"><td class="text"></td> </tr>
							
							<tr>
					            <td class="text12" align="left" >
					            <img onMouseOver="showPop('18_1_info');" onMouseOut="hidePop('18_1_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
			 					<b>18.1</b><span title="dkih_181" id="v_dkih_181" class="validation">Transp.ID</span>
			 					<div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="18_1_info" class="popupWithInputText text11"  >
					           			<b>18.1 Transportmidlets identitet ved ankomsten</b>
					           			<br/><br/>
					           			Her angiver klarereren identiteten for det transportmiddel, hvor varerne befinder sig - fx container, anhænger, bil eller skib. 
					           			Identiteten vil normalt være enten registreringsnummer eller navn. Tal og bogstaver skal skrives uden mellemrum.
					           			<br/><br/>
					           			Hvis der benyttes et trækkende køretøj og en påhængsvogn med forskellige registreringsnumre, 
					           			skal klarereren angive registreringsnummeret på både det trækkende køretøj og på påhængsvognen.
					           			<br/><br/>
					           			Hvis det drejer sig om postforsendelser eller transport i faste installationer, skal man ikke udfylde rubrikken.
								</span>	
								</div>	
					            </td>
				                 <td >
						            	<input type="text" class="inputTextMediumBlue" name="dkih_181" id="dkih_181" size="21" maxlength="20" value="${model.record.dkih_181}">
								</td>
							</tr>
							<tr>
					            <td class="text12" align="left" >
					            <img onMouseOver="showPop('18_a_info');" onMouseOut="hidePop('18_a_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
			 					<b>18.2</b><span title="dkih_18a">Transp.ref.</span>
			 					<div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="18_a_info" class="popupWithInputText text11"  >
					           			<b>18.2 TODO</b>
					           			<br/><br/>
					           			-
					           			<br/><br/>
					           			-
					           			<br/><br/>
					           			-
								</span>	
								</div>	
					            </td>
				                 <td >
						            	<input type="text" class="inputTextMediumBlue" name="dkih_18a" id="dkih_18a" size="21" maxlength="20" value="${model.record.dkih_18a}">
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
					            <b>21.</b>
					            <span title="dkih_211">Aktive transp. nationalitet ved grænsen&nbsp;</span>
					          
					            
					            <div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="21_info" class="popupWithInputText text11"  >
					           			<b>21. Det grænseoverskridende aktive transportmiddel</b>
					           			<br/><br/>
					           			Her skal man anføre nationaliteten på det aktive transportmiddel (landekoderne).
					           			<br/><br/>
					           			Når det drejer sig om kombineret transport, eller når der anvendes flere transportmidler, anføres som aktivt transportmiddel det, der tjener til fremdriften. Hvis transportformen for eksempel er lastbil på skib, vil det være skibet; 
					           			hvis der anvendes et trækkende køretøj og påhængsvogn, er det aktive transportmiddel det trækkende køretøj.
					           			<br/><br/>
					           			Man skal ikke udfylde rubrikken ved indførsel med post, jernbane eller i faste installationer.
								</span>	
								</div>
								</td>
								<td>
					            		<select name="dkih_211" id="dkih_211">
				 						<option value="">-vælg-</option>
					 				  	<c:forEach var="country" items="${model.countryCodeList}" >
					 				  		<option value="${country.dkkd_kd}"<c:if test="${model.record.dkih_211 == country.dkkd_kd}"> selected </c:if> >${country.dkkd_kd}</option>
										</c:forEach>  
									</select>
									<a tabindex="-1" id="dkih_211IdLink" OnClick="triggerChildWindowCountryCodes(this)">
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
					            <span title="dkih_25" id="v_dkih_25" class="validation">Transp. ved grænsen&nbsp;</span>
					            <div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="25_info" class="popupWithInputText text11"  >
					           			<b>25. Transportmåde ved grænsen</b>
					           			<br/><br/>
					           			Her angiver klarereren koden for den transportmåde, der anvendes ved transporten over EU's ydre grænse. Denne transportmåde skal være i overensstemmelse med det aktive transportmiddel, som er angivet i rubrik 21.<br/><br/>
					           			Når det drejer sig om kombineret transport, eller når der anvendes flere transportmidler, anføres som aktivt transportmiddel det, der tjener til fremdriften. Hvis transportformen for eksempel er lastbil på skib, vil det være skibet; 
					           			hvis der anvendes et trækkende køretøj og påhængsvogn, er det aktive transportmiddel det trækkende køretøj.
					           			<br/><br/>
					           			Koden findes på SKATs hjemmeside.
								</span>	
								</div>
								</td>	
					            <td class="text12" >
			           				<select name="dkih_25" id="dkih_25">
				 						<option value="">-vælg-</option>
					 				  	<c:forEach var="code" items="${model.transportR25R26CodeList}" >
					 				  		<option value="${code.dkkd_kd}"<c:if test="${model.record.dkih_25 == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}-${code.dkkf_txt}</option>
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
			 				<tr height="8"><td class="text"></td> </tr>
					 		<tr>
					            <td class="text12" align="left" >
					            <img onMouseOver="showPop('26_info');" onMouseOut="hidePop('26_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					            <b>26.</b>
					            <span title="dkih_26" id="v_dkih_26" class="validation">Indenlandsk transp.måde&nbsp;</span>
					            <div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="26_info" class="popupWithInputText text11"  >
					           			<b>26. Indenlandsk transportmåde</b>
					           			<br/><br/>
					           			Her angives koden for den transportmåde, der anvendes fra EU's ydre grænse til Danmark. Koden kan fx være 30 (vejtransport), 
					           			hvis der er tale om varer, der er ankommet med skib til Hamburg og videretransporteres til Danmark med lastbil.
					           			<br/><br/>
								</span>		
								</div>
								</td>
					            
					            <td class="text12" >
			           				<select name="dkih_26" id="dkih_26">
				 						<option value="">-vælg-</option>
					 				  	<c:forEach var="code" items="${model.transportR25R26CodeList}" >
					 				  		<option value="${code.dkkd_kd}"<c:if test="${model.record.dkih_26 == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}-${code.dkkf_txt}</option>
										</c:forEach>  
									</select>
			           			</td>
							</tr>
							
						</table>
					</td>
				</tr>

				<tr>
					<td width="2">&nbsp;</td>
					<td valign="top">
			 			<table border="0" cellspacing="0" cellpadding="0">
					 		<tr>
					            <td class="text12" align="left" >
					            <img onMouseOver="showPop('s29_info');" onMouseOut="hidePop('s29_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
			 					<span title="dkih_s29"><b>S29.</b>&nbsp;Bet.måde for transp.udgifter</span>&nbsp;
			 					<div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="s29_info" class="popupWithInputText text11"  >
					           			<b>S29. Betalingsmåde for transportudgifter (Transport charges method of payment)</b>
					           			<ul>
					           				<li><b>A</b>&nbsp;Kontant betaling</li>
					           				<li><b>B</b>&nbsp;Betaling med kreditkort</li>
					           				<li><b>C</b>&nbsp;Betaling med check</li>
					           				<li><b>D</b>&nbsp;Andet</li>
					           				<li><b>H</b>&nbsp;Elektronisk betaling</li>
					           				<li><b>Y</b>&nbsp;Kontohaver hos transportør</li>
					           				<li><b>Z</b>&nbsp;Ikke forudbetalt</li>					           									           									           									           									           									           				
					           			</ul>
					           			Her anføres koden for betalingsmåde, hvis der er oplysning om den.
										Anvendes ikke i den forenklede angivelse for AEO-certificerede virksomheder.
										<br/><br/>
										Feltet er obligatorisk at udfylde enten på hoveddelen eller på vareposten.
								</span>	
								</div>	
					            </td>
					            <td >
					            		<select name="dkih_s29" id="dkih_s29">
				 						<option value="">-vælg-</option>
					 				  	<c:forEach var="code" items="${model.betalningForTransportRS29CodeList}" >
					 				  		<option value="${code.dkkd_kd}"<c:if test="${model.record.dkih_s29 == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
										</c:forEach>  
									</select>					            		
								</td>
							</tr>
							<tr height="5"><td>&nbsp;</td> </tr>
							<tr>
				            <td class="text12" align="left" >
				            <img onMouseOver="showPop('dkih_201_info');" onMouseOut="hidePop('dkih_201_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
		 					<span title="dkih_201">&nbsp;Leveringsbetingelser [kode]</span>&nbsp;
				            <div class="text11" style="position: relative;" align="left">
							<span style="position:absolute;top:2px; width:250px;" id="dkih_201_info" class="popupWithInputText text11"  >
				           			<b>Leveringsbetingelser</b>
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
					            	<select name="dkih_201" id="dkih_201">
			 						<option value="">-vælg-</option>
					 				  	<c:forEach var="code" items="${model.incotermsCodeList}" >
					 				  		<option value="${code.dkkd_kd}"<c:if test="${model.record.dkih_201 == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
										</c:forEach>  
								</select>
							</td>
							</tr>
			 			
			 				<tr>
					            <td class="text12" align="left" >
					            <img onMouseOver="showPop('dkih_202_info');" onMouseOut="hidePop('dkih_202_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
			 					<span title="dkih_202">&nbsp;Sted</span>&nbsp;
					            <div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="dkih_202_info" class="popupWithInputText text11"  >
					           			<b>Sted</b>
					           			<br/><br/>
					           			Sted, hvor forsendelsesbetingelser og leveringsbetingelser opfyldes. 
					           			<br/><br/>
								</span>	
								</div>
								</td>
							</tr>
							<tr>	
					            <td colspan="2">
					            		&nbsp;&nbsp;&nbsp;<input type="text" class="inputTextMediumBlue" name="dkih_202" id="dkih_202" size="33" maxlength="70" value="${model.record.dkih_202}">
								</td>
							</tr>
							<tr height="2"><td>&nbsp;</td> </tr>
							
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
					            <span title="dkih_301">Oplagshav. ISO kode</span></td>
					            <td >
						            <select name="dkih_301" id="dkih_301">
					 				  <option value="">-vælg-</option>
									  <option value="DK"<c:if test="${model.record.dkih_301 == 'DK'}"> selected </c:if> >DK</option>
									</select>
					            </td>
	        					</tr>
	        					<tr>
				            		<td class="text12" align="left" >
					            &nbsp;&nbsp;&nbsp;30.2&nbsp;
					            <span title="dkih_302">Oplagshav. SE-nr</span></td>
					            <td ><input type="text" class="inputTextMediumBlue" name="dkih_302" id="dkih_302" size="17" maxlength="16" value="${model.record.dkih_302}"></td>
	        					</tr>
	        					<tr>
				            		<td class="text12" align="left" >
					            &nbsp;&nbsp;&nbsp;30.3&nbsp;
					            <span title="dkih_303">Bevillingstype</span></td>
					            <td ><input type="text" class="inputTextMediumBlue" name="dkih_303" id="dkih_303" size="4" maxlength="3" value="${model.record.dkih_303}"></td>
	        					</tr>
	        					<tr>
				            		<td class="text12" align="left" >
					            &nbsp;&nbsp;&nbsp;30.4&nbsp;
					            <span title="dkih_304">Adresseløbenr.</span></td>
					            <td ><input type="text" class="inputTextMediumBlue" name="dkih_304" id="dkih_304" size="4" maxlength="3" value="${model.record.dkih_304}"></td>
	        					</tr>
	        					<tr height="8"><td class="text"></td> </tr>
			 				
	        					<tr>
				            		<td class="text12" align="left" >
				            		<img onMouseOver="showPop('49_info');" onMouseOut="hidePop('49_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">	
					            <b>49.&nbsp;</b>
					            <span title="dkih_49">Ident. af oplag</span>
					            <div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="49_info" class="popupWithInputText text11"  >
					           			<b>49. Identificering af oplag</b>
					           			<br/><br/>
					           			Klarereren skal kun udfylde denne rubrik for varer, der oplægges på toldoplag. Man skal angive toldoplagets nummer, fx C123456DK.
					           			<br/><br/>
								</span>	
								</div>
								</td>
					            <td ><input type="text" class="inputTextMediumBlue" name="dkih_49" id="dkih_49" size="18" maxlength="17" value="${model.record.dkih_49}"></td>
	        					</tr>
	        					<tr height="1"><td colspan="2" style="border-bottom:1px solid;border-color:#DDDDDD;" class="text">&nbsp;</td> </tr>
				            
	        					<tr height="10"><td class="text">&nbsp;</td> </tr>
	        					<tr>
					            <td class="text12" align="left" >
					            <img onMouseOver="showPop('brut_info');" onMouseOut="hidePop('brut_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">	
					            <span title="dkih_brut">Bruttovægt</span>
					            <div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="brut_info" class="popupWithInputText text11"  >
					           			<b>Samlede bruttovægt</b>
								</span>	
								</div>
								</td>
					            <td >
					            		<input onKeyPress="return numberKey(event)" style="text-align: right" type="text" class="inputTextMediumBlue" name="dkih_brut" id="dkih_brut" size="12" maxlength="11" value="${model.record.dkih_brut}">
					            </td>
					        </tr>
				            <tr>
					            <td class="text12" align="left" >
					            <img onMouseOver="showPop('6_info');" onMouseOut="hidePop('6_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">	
					            <b>6.&nbsp;</b><span title="dkih_06">Kolli i alt</span>
					            <div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="6_info" class="popupWithInputText text11"  >
					           			<b>6. Kolli i alt</b>
					           			<br/><br/>
					           			Det samlede antal kolli, der er omfattet af fortoldningsangivelsen.
					           			<br/><br/>
								</span>	
								</div>
								</td>
					            <td >
					            		<input onKeyPress="return numberKey(event)" style="text-align: right" type="text" class="inputTextMediumBlue" name="dkih_06" id="dkih_06" size="8" maxlength="7" value="${model.record.dkih_06}">
					            </td>
					        </tr>
					        
					        <tr>
					        		<td class="text12Gray" align="left" >
					        			Antal kollin (i vareposterne)&nbsp;
					        		</td>
						        	<td >
					            		<input readonly style="text-align: right" type="text" class="inputTextReadOnly" name="sumOfAntalKolliInItemLines" id="sumOfAntalKolliInItemLines" size="8" maxlength="7" value="${ model.record.sumOfAntalKolliInItemLinesStr}">
					            		<c:if test="${not empty ( model.record.sumOfAntalKolliInItemLinesStr &&  model.record.dkih_06)}">
						            		<c:if test="${ model.record.dkih_06 !=  model.record.sumOfAntalKolliInItemLinesStr}">
								            <img onMouseOver="showPop('itemsSumKolli_info');" onMouseOut="hidePop('itemsSumKolli_info');" width="18px" height="20px" src="resources/images/redFlag.png" border="0" alt="kolliantal warning">	
								            <div class="text11" style="position: relative;" align="left">
											<span style="position:absolute;top:2px; width:250px;" id="itemsSumKolli_info" class="popupWithInputText text11"  >
											<font class="text11">
								           			<p>
								           			Summen af ​​antallet af pakker på varelinje niveau svarer ikke til det angivne antal pakker i posten.
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
					            		<input readonly style="text-align: right" type="text" class="inputTextReadOnly" name="sumOfAntalItemLines" id="sumOfAntalItemLines" size="8" value="${ model.record.sumOfAntalItemLinesStr}">
					            		<c:if test="${not empty ( model.record.sumOfAntalItemLinesStr)}">
						            		<c:if test="${ model.record.sumOfAntalItemLines <= 0 }">
								            <img onMouseOver="showPop('itemsSumAntalLines_info');" onMouseOut="hidePop('itemsSumAntalLines_info');" width="18px" height="20px" src="resources/images/redFlag.png" border="0" alt="varelinjerantal warning">	
								            <div class="text11" style="position: relative;" align="left">
											<span style="position:absolute;top:2px; width:250px;" id="itemsSumAntalLines_info" class="popupWithInputText text11"  >
													<font class="text11" >Summen af ​​antallet varer linjer må vare > 0</font>
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
					            		<input readonly style="text-align: right" type="text" class="inputTextReadOnly" name="sumTotalAmountItemLines" id="sumTotalAmountItemLines" size="8" value="${ model.record.sumTotalAmountItemLinesStr}">
						            	<c:if test="${model.record.sumTotalAmountItemLines != model.record.dkih_222Dbl}">
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
					        <tr>
			 					<td class="text12" colspan="2" >
			 					<img onMouseOver="showPop('dkih_12_info');" onMouseOut="hidePop('dkih_12_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 				&nbsp;<b>12.&nbsp;Værdioplysninger&nbsp;</b>
				 				<div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="dkih_12_info" class="popupWithInputText text11"  >
					           			<b>12. Værdioplysninger</b>&nbsp;[Interne EU.lev.kost - Samlet fragt]
					           			<br/><br/>
					           			Når fragt mv. efter toldværdireglerne opdeles i en del uden for EU og en del inden for EU, 
					           			skal klarereren angive den del af beløbet, der vedrører fragt mv. inden for EU, i hele kroner. 
					           			Derudover anvender man rubrikken til at angive øvrige værdier, der kun er momspligtige, fx værdien af softwaren. Se afsnitF.A.9 Fortoldning. Hvis der allerede er sket momsberegning, skal denne del af beløbet ikke angives.
					           			<br/><br/>
					           			Det beløb, som man angiver i denne rubrik, skal ikke medregnes i toldværdien i rubrik 46, 
					           			men skal medregnes i grundlaget for momsberegningen. 
					           			Der skal altså ikke afregnes told af beløbet, men udelukkende moms.
								</span>
								</div>
								</td>
							</tr> 
							<tr>
								<td class="text12" align="right" ><span title="dkih_12">Fragt inden for EU</span>&nbsp;&nbsp;&nbsp;</td>
			 					<td class="text12">
			 						<input onKeyPress="return amountKey(event)" style="text-align: right" type="text" class="inputTextMediumBlue" name="dkih_12" id="dkih_12" size="16" maxlength="15" value="${model.record.dkih_12}">
			 					</td>
							</tr>	
							<tr>
								<td class="text12" align="right" ><span title="dkih_12e">Fragt uden for EU</span>&nbsp;&nbsp;&nbsp;</td>
			 					<td class="text12">
			 						<input onKeyPress="return amountKey(event)" style="text-align: right" type="text" class="inputTextMediumBlue" name="dkih_12e" id="dkih_12e" size="16" maxlength="15" value="${model.record.dkih_12e}">
			 					</td>
							</tr>					        
							<tr height="10"><td class="text">&nbsp;</td> </tr>	
					        <tr>
					            <td class="text12" align="left" >
					            <img onMouseOver="showPop('s17_info');" onMouseOut="hidePop('s17_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">	
					            <span title="dkih_s17"><b>S17.</b>&nbsp;Lastningssted</span>
					            <div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="s17_info" class="popupWithInputText text11"  >
					           			<b>S17. Lastningssted (Place of loading)</b>
					           			<br/><br/>
					           			Her angives navnet på det sted, hvor varerne er lastet med størst mulig præcision med foranstillet landekode.
										<br/><br/>
					           			Anvendes ikke for ekspresforsendelser.
										<br/><br/>
					           			Feltet er obligatorisk at udfylde for øvrige forsendelser.
								</span>	
								</div>
					            </td>
					            <td ><input type="text" class="inputTextMediumBlue" name="dkih_s17" id="dkih_s17" size="20" maxlength="35" value="${model.record.dkih_s17}"></td>
					        </tr>
					        <tr>
					            <td class="text12" align="left" >
					            <img onMouseOver="showPop('s18_info');" onMouseOut="hidePop('s18_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">	
					            <span title="dkih_s18"><b>S18.</b>&nbsp;Losningssted</span>
					            <div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="s18_info" class="popupWithInputText text11"  >
					           			<b>S18. Losningssted (Place of unloading)</b>
					           			<br/><br/>
					           			Ved sø- og lufttransport angives koden på losningsområdet, hvis losningsstedet ligger i Danmark.
										<br/><br/>
										Ved vejtransport, eller hvis losningsstedet ligger i et andet land, angives navnet på det sted, hvor varerne skal losses med størst
										mulig præcision med foranstillet landekode.
										<br/><br/>
										Anvendes ikke i den forenklede angivelse for AEO-certificerede virksomheder.
										<br/><br/>
										Feltet er ikke obligatorisk at udfylde, men har stor betydning for vurderingen af, om en eventuel kontrol skal foretages ved første
										indgangstoldsted eller kan udskydes til losningsstedet.
								</span>	
								</div>
					            </td>
					            <td ><input type="text" class="inputTextMediumBlue" name="dkih_s18" id="dkih_s18" size="20" maxlength="35" value="${model.record.dkih_s18}"></td>
					        </tr>
					        <tr>
					            <td class="text12" align="left" >
					            <img onMouseOver="showPop('s28_info');" onMouseOut="hidePop('s28_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">	
					            <span title="dkih_s28"><b>S28.</b>&nbsp;Segl.ident</span>
					            <div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="s28_info" class="popupWithInputText text11"  >
					           			<b>S28. Seglnummer (Seal number)</b>
					           			<br/><br/>
					           			Identifikationsnumrene for de segl, der i givet fald er fastgjort til transportudstyret. Anvendes ikke i den forenklede angivelse for
										AEO-certificerede virksomheder. 
					           			<br/><br/>
					           			Feltet er ikke obligatorisk at udfylde.
								</span>	
								</div>
					            </td>
					            <td ><input type="text" class="inputTextMediumBlue" name="dkih_s28" id="dkih_s28" size="20" maxlength="35" value="${model.record.dkih_s28}"></td>
					        </tr>
					        <tr>
					            <td class="text12" align="left" >
					            <img onMouseOver="showPop('s32_info');" onMouseOut="hidePop('s32_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">	
					            <span title="dkih_s32"><b>S32.</b>&nbsp;Indikator a.s.o</span>
					            <div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="s32_info" class="popupWithInputText text11"  >
					           			<b>S32. Indikator for anden specifik omstændighed (Other specific circumstance indicator)</b>
					           			<br/><br/>
					           			Kode, der angiver den særlige omstændighed, hvis fordel virksomheden ønsker at benytte, fx ekspresforsendelser, skibs- og
										flyforsyninger, vejtransport og autoriserede økonomiske operatører.
					           			<br/><br/>
					           			Feltet er ikke obligatorisk at udfylde.
								</span>
								</div>
					            </td>
					            <td >
					            		<select name="dkih_s32" id="dkih_s32">
					 				  <option value="">-vælg-</option>
									  <option value="A"<c:if test="${model.record.dkih_s32 == 'A'}"> selected </c:if> >A-Expresforsendelser</option>
									  <option value="C"<c:if test="${model.record.dkih_s32 == 'C'}"> selected </c:if> >C-Vejtransport</option>
									  <option value="D"<c:if test="${model.record.dkih_s32 == 'D'}"> selected </c:if> >D-Jernbanetransport</option>
									  <option value="E"<c:if test="${model.record.dkih_s32 == 'E'}"> selected </c:if> >E-Autor. økonom. operatører</option>
									  
									</select>
					            </td>
					        </tr>
					        <tr height="15"><td class="text"></td></tr>
					        <tr>
				 				<td class="text12" align="left">&nbsp;</td>
					 			<td class="text12" align="left" >
				 					<img onMouseOver="showPop('recalculationFlag_info');" onMouseOut="hidePop('recalculationFlag_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">	
				 					<input type="checkbox" name="recalculationFlag" id="recalculationFlag" value="1" <c:if test="${model.record.dkih_genb == '1'}"> checked </c:if> ><font class="text12MediumBlue"><b>Genberegne vareposter</b></font>						
				 					<div class="text11" style="position: relative;" align="left">
									<span style="position:absolute;top:2px; width:250px;" id="recalculationFlag_info" class="popupWithInputText text11"  >
					           			<font class="text12">Genberegne den statistiske værdi af alle varer poster</font>
				           			</span>
									</div>
					 			</td>
		 					</tr>
					        
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
			 				<%-- moved on top (DACHSER DK)
				            <tr >	
			            		<td class="text">&nbsp;</td> 
			 				    <td class="text9BlueGreen" valign="bottom" align="right" >
	
			 				    only status = M or emtpy status is allowed 
			 				    <c:choose>
				 				    <c:when test="${ model.record.dkih_syst == 'M' || empty  model.record.dkih_syst || model.record.dkih_syst == '10' || model.record.dkih_syst == '20' || model.record.dkih_syst == '40'}">
					 				    	<input tabindex=-1 class="inputFormSubmit" type="submit" name="submit" id="submit" onclick="javascript: form.action='skatimport_edit.do';" value='<spring:message code="systema.skat.import.createnew.submit"/>'/>
					 				    	&nbsp;&nbsp;
					 				    	<c:if test="${not empty  model.record.dkih_syop && model.record.validUpdate}">
					 				    		<input tabindex=-2 class="inputFormSubmit" type="submit" name="send" id="send" onclick="javascript: form.action='skatimport_send.do';" value='<spring:message code="systema.skat.import.createnew.send"/>'/>
					 				    	</c:if>
				 				    </c:when>
				 				    <c:otherwise>
				 				    		<input disabled class="inputFormSubmitGrayDisabled" type="submit" name="submit" value='<spring:message code="systema.skat.submit.not.editable"/>'/>
				 				    </c:otherwise>	
			 				    </c:choose>
		 				    
                				</td>
					        </tr>
					        --%>
				            
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
		 				<a tabindex=-1 href="renderLocalPdf.do?fn=SKAT_EDI_vejledning_CUSDEC_vers_2_7.pdf" target="_blank">
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
			            <td >
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
									        	<%-- ================================================================================== --%>
									        	<%-- This hidden values are used when an AJAX event from within a dialog box is fired.  
									        		 These original values will be used when the user clicks "Cancel" buttons (puttting
									        		 back original value)																--%> 
									        	<%-- ================================================================================== --%>
									        	<input type="hidden" name="orig_dkih_trkn" id="orig_dkih_trkn" value='${model.record.dkih_trkn}'>
									        	<input type="hidden" name="orig_dkih_trna" id="orig_dkih_trna" value='${model.record.dkih_trna}'>
									        	<input type="hidden" name="orig_dkih_treo" id="orig_dkih_treo" value='${model.record.dkih_treo}'>
									        	<input type="hidden" name="orig_dkih_trga" id="orig_dkih_trga" value='${model.record.dkih_trga}'>
									        	<input type="hidden" name="orig_dkih_trpo" id="orig_dkih_trpo" value='${model.record.dkih_trpo}'>
									        	<input type="hidden" name="orig_dkih_trby" id="orig_dkih_trby" value='${model.record.dkih_trby}'>
									        	<input type="hidden" name="orig_dkih_trlk" id="orig_dkih_trlk" value='${model.record.dkih_trlk}'>
							        	
									            <td class="text12" align="left" >&nbsp;&nbsp;
									            <span title="dkih_trkn">Kundenummer</span></td>
									            <td class="text12" align="left" >&nbsp;&nbsp;<span title="dkih_trna">Navn</span>
									            	<a tabindex="-1" id="dkih_trnaIdLink">
													<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
													</a>
									            </td>
									        </tr>
									        <tr>
									            <td align="left"><input type="text" class="inputTextMediumBlue" name="dkih_trkn" id="dkih_trkn" size="8" maxlength="8" value="${model.record.dkih_trkn}"></td>
									            <td align="left"><input type="text" class="inputTextMediumBlue" name="dkih_trna" id="dkih_trna" size="30" maxlength="35" value="${model.record.dkih_trna}"></td>
									        </tr>
									        <tr>
									            <td class="text12" align="left" >&nbsp;&nbsp;
									            <span title="dkih_treo">CVR/SE-nr.</span></td>
									            <td class="text12" align="left" >&nbsp;&nbsp;</td>
									        </tr>
									        <tr>
									            <td align="left"><input type="text" class="inputTextMediumBlue" name="dkih_treo" id="dkih_treo" size="19" maxlength="18" value="${model.record.dkih_treo}"></td>
									            <td align="left">&nbsp;</td>
									        </tr>
									        <tr height="4"><td>&nbsp;</td></tr>
									        <tr>
									            <td class="text12" align="left" >&nbsp;&nbsp;
									            <span title="dkih_trga">Adresse</span></td>
									            <td class="text12" align="left" >&nbsp;&nbsp;
								        			<span title="dkih_trpo">Postnummer</span></td>
										            		
									        </tr>
									        <tr>
									            <td align="left"><input type="text" class="inputTextMediumBlue" name="dkih_trga" id="dkih_trga" size="30" maxlength="35" value="${model.record.dkih_trga}"></td>
									            <td align="left"><input type="text" class="inputTextMediumBlue" name="dkih_trpo" id="dkih_trpo" size="10" maxlength="35" value="${model.record.dkih_trpo}"></td> 
										            		
									        </tr>
									        <tr>
									        		<td>
										        		<table>
										        		<tr>
										            		<td class="text12" align="left" >&nbsp;
										            		<span title="dkih_trby">By</span></td>
										            		<td align="left">&nbsp;</td>
										            	</tr>
										        		<tr>
										            		<td align="left">
										       				<input type="text" class="inputTextMediumBlue" name="dkih_trby" id="dkih_trby" size="30" maxlength="35" value="${model.record.dkih_trby}">
									            			</td> 
										            		<td align="left">&nbsp;</td>
										        		</tr>    	
										            	</table>
									            </td>
									            <td >
										            	<table>
										        		<tr>
										        			<td class="text12" align="left" >&nbsp;&nbsp;
										            		<span title="dkih_trlk">Land</span>
																														 			
														</td>
										            	</tr>
										        		<tr >
										        			<td align="left">
										            			<select name="dkih_trlk" id="dkih_trlk">
												            		<option value="">-vælg-</option>
											 				  	<c:forEach var="country" items="${model.countryCodeList}" >
											 				  		<option value="${country.dkkd_kd}"<c:if test="${model.record.dkih_trlk == country.dkkd_kd}"> selected </c:if> >${country.dkkd_kd}</option>
																</c:forEach>  
															</select>
															<a tabindex="-1" id="dkih_trlkIdLink" OnClick="triggerChildWindowCountryCodes(this)">
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
			            <td >&nbsp;</td>
				 	</tr>
					<%-- ================== --%>
					<%-- UNDERETTES (party) --%>
					<%-- ================== --%>
					<tr>
			 			<td width="5">&nbsp;</td>
			            <td >		
			 				<table width="100%" align="left" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
						 		<tr height="18px">
						 			<td class="text12White">
						 				&nbsp;<img onMouseOver="showPop('underettes_info');" onMouseOut="hidePop('underettes_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 						Underettes&nbsp;<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
				 						<div class="text11" style="position: relative;" align="left">
										<span style="position:absolute;top:2px; width:250px;" id="underettes_info" class="popupWithInputText text11"  >
					           		   		<b>Underettes</b>
							           			<br/><br/>
							           			Todo
							           			<br/>
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
									        	<%-- ================================================================================== --%>
									        	<%-- This hidden values are used when an AJAX event from within a dialog box is fired.  
									        		 These original values will be used when the user clicks "Cancel" buttons (puttting
									        		 back original value)																--%> 
									        	<%-- ================================================================================== --%>
									        	<input type="hidden" name="orig_dkih_nikn" id="orig_dkih_nikn" value='${model.record.dkih_nikn}'>
									        	<input type="hidden" name="orig_dkih_nina" id="orig_dkih_nina" value='${model.record.dkih_nina}'>
									        	<input type="hidden" name="orig_dkih_nieo" id="orig_dkih_nieo" value='${model.record.dkih_nieo}'>
									        	<input type="hidden" name="orig_dkih_niga" id="orig_dkih_niga" value='${model.record.dkih_niga}'>
									        	<input type="hidden" name="orig_dkih_nipo" id="orig_dkih_nipo" value='${model.record.dkih_nipo}'>
									        	<input type="hidden" name="orig_dkih_niby" id="orig_dkih_niby" value='${model.record.dkih_niby}'>
									        	<input type="hidden" name="orig_dkih_nilk" id="orig_dkih_nilk" value='${model.record.dkih_nilk}'>
							        	
									            <td class="text12" align="left" >&nbsp;&nbsp;
									            <span title="dkih_nikn">Kundenummer</span></td>
									            <td class="text12" align="left" >&nbsp;&nbsp;
									            <span title="dkih_nina">Navn</span>
									            	<a tabindex="-1" id="dkih_ninaIdLink">
													<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
													</a>	
									            </td>
									        </tr>
									        <tr>
									            <td align="left"><input type="text" class="inputTextMediumBlue" name="dkih_nikn" id="dkih_nikn" size="8" maxlength="8" value="${model.record.dkih_nikn}"></td>
									            <td align="left"><input type="text" class="inputTextMediumBlue" name="dkih_nina" id="dkih_nina" size="30" maxlength="35" value="${model.record.dkih_nina}"></td>
									        </tr>
									        <tr>
									            <td class="text12" align="left" >&nbsp;&nbsp;
									            <span title="dkih_nieo">CVR/SE-nr.</span></td>
									            <td class="text12" align="left" >&nbsp;&nbsp;</td>
									        </tr>
									        <tr>
									            <td align="left"><input type="text" class="inputTextMediumBlue" name="dkih_nieo" id="dkih_nieo" size="19" maxlength="18" value="${model.record.dkih_nieo}"></td>
									            <td align="left">&nbsp;</td>
									        </tr>
									        <tr height="4"><td>&nbsp;</td></tr>
									        <tr>
									            <td class="text12" align="left" >&nbsp;&nbsp;
									            <span title="dkih_niga">Adresse</span></td>
									            <td class="text12" align="left" >&nbsp;&nbsp;
								        			<span title="dkih_nipo">Postnummer</span></td>
										            		
									        </tr>
									        <tr>
									            <td align="left"><input type="text" class="inputTextMediumBlue" name="dkih_niga" id="dkih_niga" size="30" maxlength="35" value="${model.record.dkih_niga}"></td>
									            <td align="left"><input type="text" class="inputTextMediumBlue" name="dkih_nipo" id="dkih_nipo" size="10" maxlength="35" value="${model.record.dkih_nipo}"></td> 
										            		
									        </tr>
									        <tr>
									        		<td>
										        		<table>
										        		<tr>
										            		<td class="text12" align="left" >&nbsp;
										            		<span title="dkih_niby">By</span></td>
										            		<td align="left">&nbsp;</td>
										            	</tr>
										        		<tr>
										            		<td align="left">
										       				<input type="text" class="inputTextMediumBlue" name="dkih_niby" id="dkih_niby" size="30" maxlength="35" value="${model.record.dkih_niby}">
									            			</td> 
										            		<td align="left">&nbsp;</td>
										        		</tr>    	
										            	</table>
									            </td>
									            <td >
										            	<table>
										        		<tr>
										        			<td class="text12" align="left" >&nbsp;&nbsp;
										            		<span title="dkih_nilk">Land</span>
																														 			
														</td>
										            	</tr>
										        		<tr >
										        			<td align="left">
										            			<select name="dkih_nilk" id="dkih_nilk">
												            		<option value="">-vælg-</option>
											 				  	<c:forEach var="country" items="${model.countryCodeList}" >
											 				  		<option value="${country.dkkd_kd}"<c:if test="${model.record.dkih_nilk == country.dkkd_kd}"> selected </c:if> >${country.dkkd_kd}</option>
																</c:forEach>  
															</select>
															<a tabindex="-1" id="dkih_nilkIdLink" OnClick="triggerChildWindowCountryCodes(this)">
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
							<td valign="top">
					 			<table border="0" cellspacing="0" cellpadding="0">
				                	<tr>
						 			<td class="text12" >
						 				<table align="left" border="0" cellspacing="0" cellpadding="0">
							 				<tr>
								 				<td class="text12">
								 					<img onMouseOver="showPop('beg_fasthold_info');" onMouseOut="hidePop('beg_fasthold_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
								 					<span title="dkih_begb...dkih_begd">Beg. for fastholdelse</span>&nbsp;
								 					<div class="text11" style="position: relative;" align="left">
													<span style="position:absolute;top:2px; width:250px;" id="beg_fasthold_info" class="popupWithInputText text11"  >
					           		   	           			<b>Begrundelse for fastholdelse</b>
										           			<br/><br/>
										           			Angivelse af begrundelse for fastholdelse af tidligere angivelse efter sandsynlige fejl.
													</span>
													</div>
								 				</td>
								 				<td class="text12" align="left"><button name="beg_fastholdFieldsButton" class="buttonGray" type="button" onClick="showPop('beg_fastholdFields');" >Mere...</button> 
											        <span style="position:absolute; left:600px; top:1100px; width:350px; height:300px;" id="beg_fastholdFields" class="popupWithInputText"  >
										           		<div class="text10" align="left">
										           			<table>
										           				<tr>
												           			<td class="text12">
					 												<b>Begrundelse for fastholdelse</b>
					 												</td>
																</tr>
																<tr>
												           			<td class="text11">
																		&nbsp;<span title="dkih_begb">2.Beg. for fastholdelse</span><input type="text" class="inputText" name="dkih_begb" id="dkih_begb" size="35" maxlength="70" value="${model.record.dkih_begb}">
																	</td>
																</tr>
																<tr>
												           			<td class="text11">
																		&nbsp;<span title="dkih_begc">3.Beg. for fastholdelse</span><input type="text" class="inputText" name="dkih_begc" id="dkih_begc" size="35" maxlength="70" value="${model.record.dkih_begc}">
																	</td>
																</tr>
																<tr>
												           			<td class="text11">
																		&nbsp;<span title="dkih_begd">4.Beg. for fastholdelse</span><input type="text" class="inputText" name="dkih_begd" id="dkih_begd" size="35" maxlength="45" value="${model.record.dkih_begd}">
																	</td>
																</tr>
																
										           			</table>
															<table width="100%" align="left" border="0">
																<tr align="left" >
																	<td class="text11"><button name="beg_fastholdFieldsButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('beg_fastholdFields');">&nbsp;Ok</button> 
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
						 		</table>
							</td>
						</tr>
						<tr height="10"><td></td><td></td></tr>
						<tr>
							<td width="2">&nbsp;</td>
				 			<td class="text12" >
				 				<table align="left" border="0" cellspacing="0" cellpadding="0">
								<tr>
						 			<td colspan="2" class="text12" align="left" >
						 			<img onMouseOver="showPop('7_info');" onMouseOut="hidePop('7_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
						 			<b>7.</b>&nbsp;
						 			<span title="dkih_07a...dkih_07d"><b>Referencenummer&nbsp;</b></span>
						 			<div class="text11" style="position: relative;" align="left">
									<span style="position:absolute;top:2px; width:250px;" id="7_info" class="popupWithInputText text11"  >
						           			<b>7. Referencenummer</b>
						           			<br/><br/>
						           			Det nummer, som toldsystemet automatisk tildeler angivelsen.
						           			<br/><br/>
						           			Referencenummeret, som er tildelt en forenklet ekspresangivelse, skal angives i rubrik 7, når de supplerende oplysninger angives.
						           			<br/><br/>
											Referencenummeret, som er tildelt en midlertidig oplæggelse, skal angives i rubrik 7, når standardfortoldning angives.						           			
						           			<br/><br/>
						           			<ul>
						           				<li><b>AEI</b> Registration number of previous Customs declaration (Opsplitning)</li>
												<li><b>AFM</b> Secondary Customs reference (Forudanmeldelse)</li>
												<li><b>ACW</b> Reference number to previous message (Tidligere angivelse)</li>
												<li><b>ABE</b> UCRN number</li>
						           			</ul>
									</span>
									</div>
									</td>
						 		</tr>
						 		<tr height="2"><td></td></tr>
						 		<tr>
				 					<td align="left" ><span title="dkih_07a">&nbsp;&nbsp;7.Ref.nr AEI&nbsp;</span></td>
						 			<td align="left" ><input type="text" class="inputTextMediumBlue" name="dkih_07a" id="dkih_07a" size="25" maxlength="13" value="${model.record.dkih_07a}"></td>				 			
				 				</tr>
				 				<tr>
				 					<td align="left" ><span title="dkih_07b">&nbsp;&nbsp;7.Ref.nr AFM&nbsp;</span></td>
						 			<td align="left" ><input type="text" class="inputTextMediumBlue" name="dkih_07b" id="dkih_07b" size="25" maxlength="13" value="${model.record.dkih_07b}"></td>				 			
				 				</tr>
				 				<tr>
				 					<td align="left" >
				 					<c:choose>
					 					<c:when test="${not empty model.record.dkih_07}">
											&nbsp; &nbsp;
					 					</c:when>
					 					<c:otherwise>
					 						<font class="text16RedBold" >*</font>
					 					</c:otherwise>
					 				</c:choose>
				 					<span title="dkih_07c">7.Ref.nr ACW&nbsp;</span></td>
						 			<td align="left" >
						 				<c:choose>
						 					<c:when test="${not empty model.record.dkih_07}">
												<input readonly type="text" class="inputTextReadOnly" name="dkih_07c" id="dkih_07c" size="25" maxlength="13" value="${model.record.dkih_07c}">
						 					</c:when>
						 					<c:otherwise>
												<input type="text" class="inputText" name="dkih_07c" id="dkih_07c" size="25" maxlength="13" value="${model.record.dkih_07c}">						 					
						 					</c:otherwise>
						 				</c:choose>
					 				</td>				 			
				 				</tr>
				 				<tr>
				 					<td align="left" ><span title="dkih_07d">&nbsp;&nbsp;7.Ref.nr ABE&nbsp;</span></td>
						 			<td align="left" ><input type="text" class="inputTextMediumBlue" name="dkih_07d" id="dkih_07d" size="25" maxlength="35" value="${model.record.dkih_07d}"></td>				 			
				 				</tr>
				 				</table>
				 			</td>
				 		</tr>
				 		
						<tr height="30"><td>&nbsp;</td><td>&nbsp;</td></tr> 
						<tr>
							<td width="2">&nbsp;</td>
				 			<td class="text12">
				 				<table align="left" border="0" cellspacing="0" cellpadding="0">
				 					<tr height="1"><td></td></tr>
					 				<tr>
					 					<td class="text12Bold" colspan="2">Systemdatoer&nbsp;</td>
									</tr>
								
					 				<tr height="1"><td></td></tr>
					 				<tr>
					 					<td class="text12" ><span title="dkih_godt">Godkendelsesdato:</span>&nbsp;</td>
					 					<td class="text12">
					 						<input readonly style="text-align: center" type="text" class="inputTextReadOnly" name="dkih_godt" id="dkih_godt" size="10" maxlength="8" value="${model.record.dkih_godt}">
					 					</td>
									</tr>
									<tr height="1"><td></td></tr>
					 				<tr>
					 					<td class="text12" ><span title="dkih_sadt">Satsdato:</span>&nbsp;</td>
					 					<td class="text12">
					 						<input readonly style="text-align: center" type="text" class="inputTextReadOnly" name="dkih_sadt" id="dkih_sadt" size="10" maxlength="8" value="${model.record.dkih_sadt}">
					 					</td>
									</tr>
									<tr height="1"><td></td></tr>
					 				<tr>
					 					<td class="text12" ><span title="dkih_vadt">Kontroldato:</span>&nbsp;</td>
					 					<td class="text12">
					 						<input readonly style="text-align: center" type="text" class="inputTextReadOnly" name="dkih_vadt" id="dkih_vadt" size="10" maxlength="8" value="${model.record.dkih_vadt}">
					 					</td>
									</tr>
									<tr height="1"><td></td></tr>
					 				<tr>
					 					<td class="text12" ><span title="dkih_andt">Faktisk ankomst:</span>&nbsp;</td>
					 					<td class="text12">
					 						<input readonly style="text-align: center" type="text" class="inputTextReadOnly" name="dkih_andt" id="dkih_andt" size="10" maxlength="8" value="${model.record.dkih_andt}">
					 					</td>
									</tr>
				 				</table>
				 			</td>
						</tr>
						<tr height="20"><td>&nbsp;</td><td>&nbsp;</td></tr> 
						
						<tr>
							<td >&nbsp;</td>
							<td colspan="3">
								<table align="left" border="0" cellspacing="0" cellpadding="0">
				 					<tr height="1"><td></td></tr>
					 				<tr>
					 					<td colspan="2" class="text12Bold" align="left" >
						 					<img onMouseOver="showPop('toldvardidekl_info');" onMouseOut="hidePop('toldvardidekl_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
						 					<a style="color:#000000;" target="_blank" href="http://www.skat.dk/SKAT.aspx?oID=2089688&chk=209500&layout=353121">
							 					TOLDVÆRDIDEKLARATION&nbsp;<b>D.V.1</b>
							 				</a>
						 				<div class="text11" style="position: relative;" align="left">
										<span style="position:absolute;top:2px; width:250px;" id="toldvardidekl_info" class="popupWithInputText text11"  >
						           			
							           			<b>TOLDVÆRDIDEKLARATION D.V.1</b>
							           			<br/><br/>
							           			Toldværdideklarationen er en erklæring, der skal afgives af importøren ved forsendelser indeholdende værditoldpligtige varer, hvis toldværdi overstiger 10.000 EUR.
							           			<br/><br/>
							           			Deklarationen indeholder oplysninger om handelsforholdet mellem køber og sælger, om købs- og leveringsbetingelser samt om de beløb, 
							           			der skal tages i betragtning ved ansættelsen af toldværdien.
										</span>
										</div>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td >&nbsp;</td>
							<td >
								<table align="left" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td colspan="3" class="text12">
										<button name="toldvardideklButton" class="buttonGray" type="button" onClick="showPop('toldvardidekl');" >Blankett</button> 
									        <span style="background-color:#EEEEEE; position:absolute; left:50px; top:1000px; width:1100px; height:600px;" id="toldvardidekl" class="popupWithInputTextThickBorder"  >
								           		<div class="ownScrollableSubWindow" style="width:1080px; height:480px; margin:10px;">
								           			<nav>
								           			<table width="95%" border="0" align="left" cellspacing="2">
								           			<tr>
									           			<td colspan="3" class="text14"><b>TOLDVÆRDIDEKLARATION D.V.1</b></td>
									           		</tr>
									           		<%-- FIRST SECTION --%>
									           		<tr>
								           				<td>
								           				<table class="formFrameTitaniumWhite" >
								           				
														<tr>
										           			<td class="text11">
																&nbsp;&nbsp;<span title="dkih_t04a"><b>4a.</b>Fakturaens nr.</span>
															</td>
															<td class="text11">
																&nbsp;&nbsp;<span title="dkih_t04b"><b>4b.</b>Fakturaens dato</span>
															</td>
															<td class="text11">
																&nbsp;&nbsp;<span title="dkih_t05a"><b>5a.</b>Kontraktens nr.</span>
															</td>
															<td class="text11">
																&nbsp;&nbsp;<span title="dkih_t05b"><b>5b.</b>Kontraktens dato</span>
															</td>
														</tr>
														<tr>
										           			<td class="text11">
										           				<%-- same value as master dkih_28b --%>
																&nbsp;<input type="text" class="inputTextReadOnly" readonly name="dkih_t04a" id="dkih_t04a" size="20" maxlength="35" value="${model.record.dkih_28b}">
															</td>
															<td class="text11">
										           				<%-- same value as master dkih_28c --%>															
																&nbsp;<input class="inputTextReadOnly" readonly type="text" name="dkih_t04b" id="dkih_t04b" size="9" maxlength="8" value="${model.record.dkih_28c}">
															</td>
															<td class="text11">
																&nbsp;<input type="text" class="inputText" name="dkih_t05a" id="dkih_t05a" size="20" maxlength="35" value="${model.record.dkih_t05a}">
															</td>
															<td class="text11">
																&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="dkih_t05b" id="dkih_t05b" size="9" maxlength="8" value="${model.record.dkih_t05b}">
															</td>
														</tr>
														</table>
														</td>
													</tr>
													<tr>
								           				<td>
								           				<table class="formFrameTitaniumWhite" >
								           				<tr>
										           			<td colspan="3" class="text11">
																&nbsp;&nbsp;<b>6</b>&nbsp;Nummer og dato på eventuel toldværdiafgørelse, der vedrører rubrik 7 - 9
															</td>
														</tr>
														<tr>
										           			<td class="text11">
																&nbsp;&nbsp;<span title="dkih_t06a"><b>6a.</b>&nbsp;Nummer</span>
															</td>
															<td class="text11">
																&nbsp;&nbsp;<span title="dkih_t06b"><b>6b.</b>&nbsp;Dato</span>
															</td>
														</tr>
														<tr>
										           			<td class="text11">
																&nbsp;<input type="text" class="inputText" name="dkih_t06a" id="dkih_t06a" size="20" maxlength="35" value="${model.record.dkih_t06a}">
															</td>
															<td class="text11">
																&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="dkih_t06b" id="dkih_t06b" size="9" maxlength="8" value="${model.record.dkih_t06b}">
															</td>
														</tr>
														</table>
														</td>
													</tr>
									           		<%-- SECORD SECTION (YES/NO section) --%>
								           			<tr>
								           				<td>
								           				<table width="95%" class="formFrameTitaniumWhite" >
														<tr>
										           			<td class="text11">
																&nbsp;<span title="dkih_t07a"><b>7a.</b>&nbsp;Er køber og sælger indbyrdes afhængige efter reglerne i artikel 143 *), i forordning nr. 2454 / 93?
																Hvis NEJ, gå til rubrik 8</span>
															</td>
															<td class="text11">
																<input type="radio" name="groupTold_7a" id="groupTold_7a" value="Y" <c:if test="${not empty model.record.dkih_t07a}"> checked</c:if> >Ja
																<input type="radio" name="groupTold_7a" id="groupTold_7a" value="N" <c:if test="${empty model.record.dkih_t07a}"> checked</c:if> >Nej 
															</td>
														</tr>
														<tr>
										           			<td class="text11">
																&nbsp;<span title="dkih_t07b"><b>7b.</b>&nbsp;Har det indbyrdes afhængighedsforhold påvirket prisen på de indførte varer?</span>
															</td>
															<td class="text11">
																<input type="radio" name="groupTold_7b" value="Y" <c:if test="${not empty model.record.dkih_t07b}"> checked</c:if> >Ja
																<input type="radio" name="groupTold_7b" value="N" <c:if test="${empty model.record.dkih_t07b}"> checked</c:if> >Nej 
															</td>
														</tr>
														<tr>
										           			<td class="text11">
																&nbsp;<span title="dkih_t07c"><b>7c.</b>&nbsp;(Besvarelse ikke obligatorisk). Ligger transaktionsværdien for de indførte varer meget nær 
																<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ved en af de værdier, der er omhandlet i artikel 29, stk. 2, litra b), i forordning nr. 2913 / 92?</span>
															</td>
															<td class="text11">
																<input type="radio" name="groupTold_7c" value="Y" <c:if test="${not empty model.record.dkih_t07c}"> checked</c:if> >Ja
																<input type="radio" name="groupTold_7c" value="N" <c:if test="${empty model.record.dkih_t07c}"> checked</c:if> >Nej 
															</td>
														</tr>
														
														<tr>
										           			<td class="text11">
																&nbsp;<span title="dkih_t07d">&nbsp;Hvis JA, anføres nærmere oplysninger:</span>
															</td>
														</tr>
														<tr>
															<td class="text11">
																<textarea rows="5" cols="50" class="inputText" name="dkih_t07d" id="dkih_t07d" maxlength="70">${model.record.dkih_t07d}</textarea>
															</td>	
														</tr>
														</table>
														</td>
													</tr>
													<tr>
								           				<td>
								           				<table width="95%" class="formFrameTitaniumWhite" >
								           				<tr>
										           			<td class="text11">
																&nbsp;<span title="dkih_t08a"><b>8a.</b>&nbsp;Er der fastsat begrænsninger i køberens rådighed over eller anvendelse af varerne ud over sådanne begrænsninger, som
																</span>
															</td>
														</tr>
														<tr>
										           			<td class="text11">
																&nbsp;<span title="dkih_t08a">&nbsp;- er pålagt eller krævet af lovgivningen eller af myndighederne i Fællesskabet,
																</span>
															</td>
														</tr>
														<tr>
										           			<td class="text11">
																&nbsp;<span title="dkih_t08a">&nbsp;- begrænser det geografiske område, hvor varerne kan videresælges, eller
																</span>
															</td>
														</tr>
														<tr>
										           			<td class="text11">
																&nbsp;<span title="dkih_t08a">&nbsp;- ikke i væsentlig grad påvirker varernes værdi?
																</span>
															</td>
															<td class="text11">
																<input type="radio" name="groupTold_8a" value="Y" <c:if test="${not empty model.record.dkih_t08a}"> checked</c:if> >Ja
																<input type="radio" name="groupTold_8a" value="N" <c:if test="${empty model.record.dkih_t08a}"> checked</c:if> >Nej 
															</td>
														</tr>
														<tr>
										           			<td class="text11">
																&nbsp;<span title="dkih_t08b"><b>8b.</b>&nbsp;Er salget eller prisen betinget af vilkår eller ydelser, hvis værdi ikke kan bestemmes for de varer, der skal værdiansættes?</span>
															</td>
															<td class="text11">
																<input type="radio" name="groupTold_8b" value="Y" <c:if test="${not empty model.record.dkih_t08b}"> checked</c:if> >Ja
																<input type="radio" name="groupTold_8b" value="N" <c:if test="${empty model.record.dkih_t08b}"> checked</c:if> >Nej 
															</td>
														</tr>
														<tr>
										           			<td class="text11">
																&nbsp;<span title="dkih_t08c">&nbsp;Anfør arten på eventuelle begrænsninger, vilkår eller ydelser:</span>
															</td>
														</tr>
														<tr>
															<td class="text11">
																<textarea rows="5" cols="50" class="inputText" name="dkih_t08c" id="dkih_t08c" maxlength="70">${model.record.dkih_t08c}</textarea>
															</td>	
														</tr>
														</table>
														</td>
													</tr>
													
													<tr>
								           				<td>
								           				<table width="95%" class="formFrameTitaniumWhite" >
								           				<tr>
										           			<td class="text11">
																&nbsp;<span title="dkih_t09a"><b>9a.</b>&nbsp;Skal royalty eller licensafgifter for de indførte varer ifølge salgsbetingelserne betales direkte eller indirekte af køberen?
																</span>
															</td>
															<td class="text11">
																<input type="radio" name="groupTold_9a" value="Y" <c:if test="${not empty model.record.dkih_t09a}"> checked</c:if> >Ja
																<input type="radio" name="groupTold_9a" value="N" <c:if test="${empty model.record.dkih_t09a}"> checked</c:if> >Nej 
															</td>
														</tr>
														<tr>
										           			<td class="text11">
																&nbsp;<span title="dkih_t09b"><b>9b.</b>&nbsp;Er salget betinget af, at en del af provenuet ved varernes videresalg, overdragelse eller anvendelse direkte eller indirekte
																tilfalder sælgeren?
																</span>
															</td>
															<td class="text11">
																<input type="radio" name="groupTold_9b" value="Y" <c:if test="${not empty model.record.dkih_t09b}"> checked</c:if>>Ja
																<input type="radio" name="groupTold_9b" value="N" <c:if test="${empty model.record.dkih_t09b}"> checked</c:if> >Nej 
															</td>
														</tr>
														<tr>
										           			<td class="text11">
																&nbsp;<span title="_??">&nbsp;Hvis JA til nogen af disse spørgsmål, anføres betingelserne, og om muligt anføres beløbene i rubrik 15 og 16.</span>
															</td>
														</tr>
														</table>
														</td>
													</tr>
													</table>
													</nav>
												</div>
												<div>
													<table >
														<%-- OK BUTTON --%>
								           				<tr align="left" >
															<td class="text11">&nbsp;&nbsp;&nbsp;<button name="toldvardideklButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('toldvardidekl');">&nbsp;<spring:message code="systema.skat.import.ok"/></button> 
															</td>
															<td >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																<a tabindex=-1 href="skatimport_edit_printToldvaerdiDeklaration.do?avd=${model.record.dkih_syav}&opd=${model.record.dkih_syop}">
																 	<img style="cursor:pointer;" src="resources/images/printer.png" width="30" hight="30" border="0" alt="Print Tolddekl." title="Print Tolddekl.">
																</a>
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
	
	<tr>
	<td>
		<%-- change status admin dialog --%>	
		<div id="dialogUpdateStatus" title="Dialog">
			<form action="skatimport_updateStatus.do" name="updateStatusForm" id="updateStatusForm" method="post">
			 	<input type="hidden" name="currentAvd" id="currentAvd" value="${model.record.dkih_syav}">
			 	<input type="hidden" name="currentOpd" id="currentOpd" value="${model.record.dkih_syop}">
			 		
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
 