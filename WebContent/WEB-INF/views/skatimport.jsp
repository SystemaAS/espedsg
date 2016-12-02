<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerSkat.jsp" />
<!-- =====================end header ==========================-->
	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/skatglobal_edit.js?ver=${user.versionEspedsg}"></SCRIPT>	
	<SCRIPT type="text/javascript" src="resources/js/skatimport.js?ver=${user.versionEspedsg}"></SCRIPT>	
	
	<style type = "text/css">
	.ui-datepicker { font-size:9pt;}
	</style>


<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
<tr>
	<td>
	<%-- tab container component --%>
	<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
		<tr height="2"><td></td></tr>
		<tr height="25"> 
			<td width="20%" valign="bottom" class="tab" align="center" nowrap>
				<font class="tabLink">&nbsp;<spring:message code="systema.skat.import.list.tab"/></font>
				<img valign="bottom" src="resources/images/list.gif" border="0" alt="general list">
				
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="20%" valign="bottom" class="tabDisabled" align="center" nowrap>
					<a style="display:block;" id="copyFromTransportUppdragLink" runat="server" href="#">
						<font class="tabDisabledLink">&nbsp;<spring:message code="systema.skat.import.createnew.tab"/></font>
						<img valign="bottom" src="resources/images/add.png" width="12" hight="12" border="0" alt="create new">
					</a>
				
			</td>
			<td width="60%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>	
		</tr>
	</table>
	</td>
</tr>

<tr>
	<td>
		<%-- search filter component --%>
		<form action="skatimport.do?action=doFind" name="skatImportSearchForm" id="skatImportSearchForm" method="post" >
		<table width="100%" class="tabThinBorderWhite" border="0" cellspacing="0" cellpadding="0">
 	        <tr height="3"><td></td></tr>
 	        
 	        <tr>	
                <td class="text12" align="left" >&nbsp;&nbsp;&nbsp;<spring:message code="systema.skat.import.list.search.label.avd"/>
                		
                </td>
                <td class="text12" align="left" >&nbsp;&nbsp;<spring:message code="systema.skat.import.list.search.label.signatur"/></td>
                <td class="text12" align="left" >&nbsp;&nbsp;<spring:message code="systema.skat.import.list.search.label.arende"/></td>
                <td class="text12" align="left" >&nbsp;&nbsp;<spring:message code="systema.skat.import.list.search.label.refnr"/></td>
                <td class="text12" align="left" >&nbsp;&nbsp;<spring:message code="systema.skat.import.list.search.label.xrefnr"/></td>
                <td class="text12" align="left" >
	 				<img onMouseOver="showPop('meddTyp_info');" onMouseOut="hidePop('meddTyp_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
	 				<spring:message code="systema.skat.import.list.search.label.aart"/>
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
                
                <td class="text12" align="left" >
				<img onMouseOver="showPop('datum_info');" onMouseOut="hidePop('datum_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
                	<spring:message code="systema.skat.import.list.search.label.datum"/>
                <div class="text11" style="position: relative;" align="left">
				<span style="position:absolute;top:2px; width:250px;" id="datum_info" class="popupWithInputText text11"  >
	           		Standardsøg (blank dato) gælder <b> 15 dage bagud </b> på det tidspunkt. 
	           		<br/><br/>
					Hvis du ønsker at se længere tilbage i tiden, type, skal du angive fra dato. <br/>
					For eksempel 20131001 leder efter en 1-Okt-2013 til i dag.
				</span>	
				</div>
                </td>
                <td class="text12" align="left" >
                <img onMouseOver="showPop('status_info');" onMouseOut="hidePop('status_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
                <spring:message code="systema.skat.import.list.search.label.status"/>
                		<div class="text11" style="position: relative;" align="left">
						<span style="position:absolute;top:2px; width:500px;" id="status_info" class="popupWithInputText text11"  >
		           		 Kun status <b>M</b>,<b>10</b>,<b>20</b>,<b>40</b> eller <b>' '</b> kan redigeres.
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
                <td class="text12" align="left" >&nbsp;&nbsp;<spring:message code="systema.skat.import.list.search.label.avsandare"/></td>
                <td class="text12" align="left" >&nbsp;&nbsp;<spring:message code="systema.skat.import.list.search.label.mottagare"/></td>
                <td class="text12" align="left" >&nbsp;&nbsp;<spring:message code="systema.skat.import.list.search.label.internFakturanr"/></td>
                <td>&nbsp;</td>
			</tr>
 	        <tr>
				<td align="left" >&nbsp;
           			<select name="avd" id="avd">
	            		<option value="">-vælg-</option>
	 				  	<c:forEach var="record" items="${model.avdList}" >
                   	 		<option value="${record.avd}"<c:if test="${searchFilterSkatImport.avd == record.avd}"> selected </c:if> >${record.avd}<c:if test="${record.tst == '1'}">&nbsp;(test)</c:if></option>                       	 	
						</c:forEach> 
					</select>
				</td>
				<td align="left" >
           			<select name="sign" id="sign">
	            		<option value="">-vælg-</option>
	 				  	<c:forEach var="record" items="${model.signList}" >
                             	 	<option value="${record.sign}"
                             	 		<c:if test="${searchFilterSkatImport.sign == record.sign}"> selected </c:if> >
                             	 		${record.sign}</option>
						</c:forEach> 
					</select>
				</td>
				<td align="left" ><input type="text" class="inputText" name="opd" id="opd" size="8" maxlength="10" value='${searchFilterSkatImport.opd}'>&nbsp;</td>
				<td align="left" ><input type="text" class="inputText" name="refnr" id="refnr"size="8" maxlength="35" value='${searchFilterSkatImport.refnr}'>&nbsp;</td>
				<td align="left" ><input type="text" class="inputText" name="xrefnr" id="xrefnr"size="8" maxlength="35" value='${searchFilterSkatImport.xrefnr}'>&nbsp;</td>
				<td align="left" >
					<select name="aart" id="aart">
   	 				   <option value="">-vælg-</option>
	 				   <c:forEach var="record" items="${model.angivelsesArterCodeList}" >
 				  			<option value="${record.dkkd_kd}"<c:if test="${searchFilterSkatImport.aart == record.dkkd_kd}"> selected </c:if> >${record.dkkd_kd}&nbsp;${record.dkkd_kd2}</option>
					   </c:forEach>  
					</select>	
				</td>
				<td align="left" ><input onKeyPress="return numberKey(event)" type="text" class="inputText" name="datum" id="datum" size="9" maxlength="8" value='${searchFilterSkatImport.datum}'>&nbsp;</td>
				<td align="left" ><input type="text" class="inputText" name="status" id="status" size="2" maxlength="2" value='${searchFilterSkatImport.status}'>&nbsp;</td>
				<td align="left" ><input type="text" class="inputText" name="avsNavn" id="avsNavn" size="10" maxlength="50" value='${searchFilterSkatImport.avsNavn}'>&nbsp;</td>
				<td align="left" ><input type="text" class="inputText" name="motNavn" id="motNavn" size="10" maxlength="50" value='${searchFilterSkatImport.motNavn}'>&nbsp;</td>
				<td align="left" ><input type="text" class="inputText" name="internFakturanr" id="internFakturanr" size="8" maxlength="17" value='${searchFilterSkatImport.internFakturanr}'>&nbsp;</td>
				<td valign="top" align="left" >
                   &nbsp;<input class="inputFormSubmit" type="submit" name="submit" id="submit" value='<spring:message code="systema.skat.search"/>'>
                </td>
			</tr>
			
			<tr height="10"><td></td></tr>
		</table>
		</form>
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
	<c:if test="${model.errorMessage != null}">
		<tr>
		<td>
           	<table width="100%" align="left" border="0" cellspacing="0" cellpadding="0">
    		       	<tr>
				<td class="textError">
					${model.errorMessage}:&nbsp;${model.errorInfo}
				</td>
				</tr	>
			</table>
		</td>
		</tr>
	</c:if>	
	<%-- list component --%>
	<c:if test="${not empty list}">
	<tr>
		<td>		
		<table width="100%" cellspacing="0" border="0" cellpadding="0">
	    	<%-- separator --%>
	        <tr height="1"><td></td></tr> 
			<tr>
				<td>
				<table width="100%" cellspacing="0" border="0" cellpadding="0">
					<tr class="tableHeaderField" height="20" valign="left">
	                    <td class="tableHeaderFieldFirst">&nbsp;<spring:message code="systema.skat.import.list.search.label.avd"/>&nbsp;
	                    		
	                    </td>   
	                    <td class="tableHeaderField" nowrap>&nbsp;<spring:message code="systema.skat.import.list.search.label.signatur"/>&nbsp;</td>
	                    <td class="tableHeaderField" nowrap>
	                    		<img onMouseOver="showPop('update_info');" onMouseOut="hidePop('update_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
		 					<spring:message code="systema.skat.import.list.search.label.update"/>
	 			
		 				<div class="text11" style="position: relative;" align="left">
						<span style="position:absolute;top:2px; width:250px;" id="update_info" class="popupWithInputText text11"  >
			           			<img title="Uppdatera ärende" style="vertical-align:bottom;" src="resources/images/update.gif" border="0">
				           		<b>Uppdatera</b><br/>
				           		<ul>
				           			<li>&nbsp;Status=tomt, 10,20,40 eller M</li>
				           		</ul>
						</span>	
						</div>
						</td>
	                    
	                    <td class="tableHeaderField">&nbsp;<spring:message code="systema.skat.import.list.search.label.arende"/>&nbsp;</td>
	                    <td class="tableHeaderField" nowrap>&nbsp;<spring:message code="systema.skat.import.list.search.label.refnr"/>&nbsp;</td>
	                    <td class="tableHeaderField" nowrap>&nbsp;<spring:message code="systema.skat.import.list.search.label.xrefnr"/>&nbsp;</td>
	                    <td class="tableHeaderField" nowrap>&nbsp;<spring:message code="systema.skat.import.list.search.label.aart"/>&nbsp;</td>
	                    <td class="tableHeaderField" nowrap>&nbsp;<spring:message code="systema.skat.import.list.search.label.datum"/>&nbsp;</td>
	                    <td class="tableHeaderField">&nbsp;<spring:message code="systema.skat.import.list.search.label.status"/>&nbsp;</td>
	                    <td class="tableHeaderField">&nbsp;<spring:message code="systema.skat.import.list.search.label.avsandare"/>&nbsp;</td>
	                    <td class="tableHeaderField">&nbsp;<spring:message code="systema.skat.import.list.search.label.mottagare"/>&nbsp;</td>
            				<%--
	                    <td class="tableHeaderField">&nbsp;<spring:message code="systema.skat.import.list.search.label.begaranOmKlarering"/></td>
	                     --%>
	                    <td class="tableHeaderField" nowrap>&nbsp;<spring:message code="systema.skat.import.list.search.label.kopieraArende"/></td>
	                    <td class="tableHeaderField" nowrap>&nbsp;<spring:message code="systema.skat.import.list.search.label.deleteArende"/></td>
	                    
	                </tr>     
		            <c:forEach items="${list}" var="topic" varStatus="counter">    
		               <c:choose>           
		                   <c:when test="${counter.count%2==0}">
		                       <tr class="tableRow" height="20" >
		                   </c:when>
		                   <c:otherwise>   
		                       <tr class="tableOddRow" height="20" >
		                   </c:otherwise>
		               </c:choose>
		               <td class="tableCellFirst" width="5%">&nbsp;${topic.avd}</td>
		               <td class="tableCell" >&nbsp;${topic.sign}</td>
		               <td nowrap class="tableCell" align="center">
	               	   		<a id="alinkCurrentHeaderId_${counter.count}" onClick="setBlockUI(this);"  href="skatimport_edit.do?action=doFetch&avd=${topic.avd}&opd=${topic.opd}&sysg=${topic.sign}&refnr=${topic.refnr}&syst=${topic.status}&sydt=${topic.datum}">
	               				<img title="Uppdatera ärende" valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
            					</a>
               		   </td>
               		   <td class="tableCell" >&nbsp;
            		   		<a id="alinkCurrentHeaderOpdId_${counter.count}" onClick="setBlockUI(this);" href="skatimport_edit.do?action=doFetch&avd=${topic.avd}&opd=${topic.opd}&sysg=${topic.sign}&refnr=${topic.refnr}&syst=${topic.status}&sydt=${topic.datum}">
        		      				&nbsp;${topic.opd}
		               		</a>
		               </td>
		               <td class="tableCell" >&nbsp;${topic.refnr}</td>
		               <td class="tableCell" >&nbsp;${topic.dkih_xref}</td>
		               <td class="tableCell" >&nbsp;${topic.aart}</td>
		               <td class="tableCell" >&nbsp;${topic.datum}</td>
		               <td class="tableCell" >&nbsp;<b>${topic.status}</b></td>
		               <td class="tableCell" >&nbsp;${topic.avsNavn}</td>
		               <td class="tableCell" >&nbsp;${topic.motNavn}</td>
		               <%-- 
    		               <td class="tableCell" width="10%">&nbsp;</td>
    		               --%>
		               <td class="tableCell" width="10%">&nbsp;
		               		<%--<button class="buttonGray" type="button" name="copyButton${counter.count}" id="copyButton${counter.count}" >Kopiera</button> --%>
							<a class="copyLink" id="copyLink${counter.count}" runat="server" href="#">
								<img src="resources/images/copy.png" border="0" alt="copy">
								&nbsp;Kopiere
							</a>
							 
							<div style="display: none;" class="clazz_dialog" id="dialog${counter.count}" title="Dialog">
								<form  action="skatimport_copyTopic.do" name="copyForm${counter.count}" id="copyForm${counter.count}" method="post">
								 	<input type="hidden" name="action${counter.count}" id="action${counter.count}" value='doUpdate'/>
									<input type="hidden" name="originalAvd${counter.count}" id="originalAvd${counter.count}" value='${topic.avd}'/>
				 					<input type="hidden" name="originalOpd${counter.count}" id="originalOpd${counter.count}" value='${topic.opd}'/>
					 					
									<p class="text12" >Du skal vælga nye&nbsp;<code>Afdeling</code>&nbsp;og nye&nbsp;
										<code>Signatur</code> or at kunne kopiere en angivelse
									</p>
									<p class="text12" >Et nyt angivelsesnummer vil blive oprettet automatisk.
									</p>
									
									<table>
										<tr>
											<td class="text12" align="left" >&nbsp;Afdeling</td>
	                							<td class="text12" align="left" >&nbsp;Signatur</td>
	                						</tr>
	 									<tr>
											<td class="text12MediumBlue">
												<select class="newAvd" name="newAvd${counter.count}" id="newAvd${counter.count}">
								            		<option value="">-vælg-</option>
								 				  	<c:forEach var="record" items="${model.avdList}" >
							                             	 	<option value="${record.avd}">${record.avd}</option>
													</c:forEach> 
												</select>
											</td>
											<td class="text12MediumBlue">
												<select class="newSign" name="newSign${counter.count}" id="newSign${counter.count}">
								            		<option value="">-vælg-</option>
								 				  	<c:forEach var="record" items="${model.signList}" >
							                             	 	<option value="${record.sign}">${record.sign}</option>
													</c:forEach> 
												</select>
											</td>
										</tr>
									</table>
								</form>
							</div>
		               </td>
		               <c:choose>
			               <c:when test="${topic.status == 'M' || empty topic.status || topic.status == '20' }">	
				               <td class="tableCell" align="center" nowrap>&nbsp;
				               	<a onclick="javascript:return confirm('Er du sikker på at du vil slette denne?')"  tabindex=-1 href="skatimport.do?action=doDelete&avd=${topic.avd}&opd=${topic.opd}">
				               		<img valign="bottom" src="resources/images/delete.gif" border="0" alt="remove">
				               	</a>	
				               </td>
	    		               </c:when>
			               <c:otherwise>
			               		<td class="tableCell" align="center" nowrap>&nbsp;</td>
			               </c:otherwise>
		               </c:choose>

		            </tr> 
		            </c:forEach>
	            </table>
			</td>	
			</tr>
		</table>
		</td>
		</tr>
    </c:if> 

	<tr>
		<td>
			<div id="dialogCopyFromTransportUppdrag" title="Dialog">
				<form  action="skatimport_doFetchTopicFromTransportUppdrag.do" name="copyFromTransportUppdragForm" id="copyFromTransportUppdragForm" method="post">
				 	<input type="hidden" name="actionGS" id="actionGS" value='doUpdate'/>
						
					<p class="text12" >Du kan hente en ny sag fra den Norske Eksport eller fra en Transportopdrag.
					 	Du skal vælge&nbsp;<b>Afdeling</b>&nbsp;og&nbsp;<b>Opdragsnummer</b>.</p>
					<p class="text12">Orden til at hente er:</p>
					<ol class="text12" >
						<li class="text12" >
						    En ny opdragsnummer vil blive oprettet, hvis den fil, du input er tilgængelig i enten (a) <b>Norsk Eksport</b> eller (b) <b>Transportopdrag</b>
						</li>
						<br/>
						<li class="text12" >
							Hvis opdraget ikke er fundet hverken i den Norske Eksport eller i Transportopdrag skal du oprette en ny angivelse. 
							Du vil blive omdirigeret der automatisk.
						</li>
					</ol>
					
					<p class="text12" >Men hvis du ønsker at indtaste en ny angivelse, uden at køre denne rutine, forlader Titel og Opdragsnr. blank og klikke på <b>Fortsæt</b>.</p>
					
					<table>
						<tr>
							<td class="text12" align="left" >&nbsp;Afdeling</td>
   							<td class="text12" align="left" >&nbsp;Opdragsnr.</td>
   							<td class="text12" align="left" >&nbsp;Ext.ref.nr.</td>
   						</tr>
						<tr>
							<td class="text12MediumBlue">
								<select name="selectedAvd" id="selectedAvd">
				            		<option value="">-vælg-</option>
				 				  	<c:forEach var="record" items="${model.avdList}" >
			                             	 	<option value="${record.avd}">${record.avd}</option>
									</c:forEach> 
								</select>
							</td>
							<td class="text12MediumBlue">
								<input type="text" class="inputText" id="selectedOpd" name="selectedOpd" size="10" maxlength="35" value=''>&nbsp;
							</td>
							<td class="text12MediumBlue">
								<input type="text" class="inputText" id="selectedExtRefNr" name="selectedExtRefNr" size="25" maxlength="35" value=''>&nbsp;
								<a tabindex="-1" id="extRefIdLink">
									<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
								</a>
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

