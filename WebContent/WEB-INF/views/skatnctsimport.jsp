<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerSkat.jsp" />
<!-- =====================end header ==========================-->
<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/skatglobal_edit.js?ver=${user.versionEspedsg}"></SCRIPT>	
	<SCRIPT type="text/javascript" src="resources/js/skatnctsimport.js?ver=${user.versionEspedsg}"></SCRIPT>	
	
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
				<font class="tabLink">&nbsp;<spring:message code="systema.skat.ncts.import.list.tab"/></font>
				<img valign="bottom" src="resources/images/list.gif" border="0" alt="general list">
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="20%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a style="display:block;" href="skatnctsimport_edit.do?action=doPrepareCreate&user=${user.user}">
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.skat.ncts.import.createnew.tab"/></font>
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
		
 		<table width="100%" class="tabThinBorderWhite" border="0" cellspacing="0" cellpadding="0">
 	        <tr height="3"><td></td></tr>
 	        <form name="nctsImportSearchForm" id="searchForm" action="skatnctsimport?action=doFind" method="post" >
 	        <tr>	
                <td class="text12" align="left" >&nbsp;&nbsp;&nbsp;<spring:message code="systema.skat.ncts.import.list.search.label.avd"/></td>
                <td class="text12" align="left" >&nbsp;&nbsp;<spring:message code="systema.skat.ncts.import.list.search.label.signatur"/></td>
                <td class="text12" align="left" >&nbsp;&nbsp;<spring:message code="systema.skat.ncts.import.list.search.label.arende"/></td>
                <td class="text12" align="left" >&nbsp;&nbsp;<spring:message code="systema.skat.ncts.import.list.search.label.mrnNr"/></td>
                <td class="text12" align="left" >
				<img onMouseOver="showPop('datum_info');" onMouseOut="hidePop('datum_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
                <spring:message code="systema.skat.ncts.import.list.search.label.datum"/>
                <span style="position:absolute; left:900px; top:150px; width:250px; height:180px;" id="datum_info" class="popupWithInputText"  >
	           		<div class="text11" align="left">
	           		Standardsøg (blank dato) gælder <b> 15 dage bagud </b> på det tidspunkt. 
	           		<br/><br/>
					Hvis du ønsker at se længere tilbage i tiden, type, skal du angive fra dato. <br/>
					For eksempel 20131001 leder efter en 1-Okt-2013 til i dag.
	           		</div>
				</span>	
                </td>
                <td class="text12" align="left" >
				<img onMouseOver="showPop('status_info');" onMouseOut="hidePop('status_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
                <spring:message code="systema.skat.ncts.import.list.search.label.status"/></td>
                <span style="position:absolute; left:900px; top:150px; width:250px; height:450px;" id="status_info" class="popupWithInputText"  >
		           		<div class="text11" align="left">
		           		<br/>
		           		Endast <b>M</b>, F eller <b>' '</b> kan redigeres.
		           			<ul>
								<c:forEach var="record" items="${model.statusCodeList}" >
				           			<li><b>${record.tkkode}&nbsp;</b>&nbsp;${record.tktxtn}</li>
			           			</c:forEach>
		           			</ul>
						</div>
					</span>	                
                <td class="text12" align="left" >&nbsp;&nbsp;<spring:message code="systema.skat.ncts.import.list.search.label.type"/></td>
                <td class="text12" align="left" >&nbsp;&nbsp;<spring:message code="systema.skat.ncts.import.list.search.label.ansvarig"/></td>
                <td class="text12" align="left" >&nbsp;&nbsp;<spring:message code="systema.skat.ncts.import.list.search.label.godsnr"/></td>
                <td class="text12" align="left" >&nbsp;&nbsp;<spring:message code="systema.skat.ncts.import.list.search.label.frigivningsDatum"/></td>
                
                <td>&nbsp;</td>
			</tr>
 	        <tr>
				<td align="left" >&nbsp;
           			<select name="avd" id="avd">
	            		<option value="">-vælg-</option>
	 				  	<c:forEach var="record" items="${model.avdList}" >
                        	 	<option value="${record.avd}"<c:if test="${searchFilter.avd == record.avd}"> selected </c:if> >${record.avd}<c:if test="${record.tst == '1'}">&nbsp;(test)</c:if></option>                       	 	
						</c:forEach> 
					</select>
				</td>
				<td align="left" >
           			<select name="sign" id="sign">
	            		<option value="">-vælg-</option>
	 				  	<c:forEach var="record" items="${model.signList}" >
                             	 	<option value="${record.sign}"<c:if test="${searchFilter.sign == record.sign}"> selected </c:if> >${record.sign}</option>
						</c:forEach> 
					</select>
				</td>
				<td align="left" ><input type="text" class="inputText" name="opd" id="opd" size="10" maxlength="10" value="${searchFilter.opd}">&nbsp;</td>
				<td align="left" ><input type="text" class="inputText" name="mrnNr" id="mrnNr" size="15" maxlength="18" value="${searchFilter.mrnNr}">&nbsp;</td>
				<td align="left" ><input onKeyPress="return numberKey(event)" type="text" class="inputText" name="datum" id="datum" size="9" maxlength="8" value="${searchFilter.datum}">&nbsp;</td>
				<td align="left" ><input type="text" class="inputText" name="status" id="status" size="2" maxlength="1" value="${searchFilter.status}">&nbsp;</td>
				<td align="left" >
					<select name="forenklad" id="forenklad">
		            		<option value="">-vælg-</option>
		            		<option value="J" <c:if test="${searchFilter.forenklad == 'J'}"> selected </c:if> >Forenklet</option>
		            		<option value="N" <c:if test="${searchFilter.forenklad == 'N'}"> selected </c:if> >Normal</option>
					</select>
				</td>
				<td align="left" ><input type="text" class="inputText" name="ansNavn" id="ansNavn" size="12" maxlength="50" value="${searchFilter.ansNavn}">&nbsp;</td>
				<td align="left" ><input type="text" class="inputText" name="godsNr" id="godsNr" size="12" maxlength="35" value="${searchFilter.godsNr}">&nbsp;</td>
				<td align="left" ><input onKeyPress="return numberKey(event)" type="text" class="inputText" name="datumFr" id="datumFr" size="9" maxlength="8" value="${searchFilter.datumFr}">&nbsp;</td>

				
				<td valign="top" align="left" >
                   &nbsp;<input class="inputFormSubmit" type="submit" name="submit" value='<spring:message code="systema.skat.search"/>'>
                   <img src="resources/images/find.png" border="0" alt="">
                </td>
			</tr>
			</form>
			<tr height="10"><td></td></tr>
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
                    <td class="tableHeaderFieldFirst" align="left" >&nbsp;&nbsp;&nbsp;<spring:message code="systema.skat.ncts.import.list.search.label.avd"/></td>
                		<td class="tableHeaderField" align="left" >&nbsp;&nbsp;<spring:message code="systema.skat.ncts.import.list.search.label.signatur"/></td>
                		<td class="tableHeaderField" align="center" >&nbsp;<spring:message code="systema.skat.ncts.import.list.search.label.update"/></td>
                		<td class="tableHeaderField" align="left" >&nbsp;&nbsp;<spring:message code="systema.skat.ncts.import.list.search.label.arende"/></td>
                		<td class="tableHeaderField" align="left" >&nbsp;&nbsp;<spring:message code="systema.skat.ncts.import.list.search.label.mrnNr"/></td>
                		<td class="tableHeaderField" align="left" >&nbsp;&nbsp;<spring:message code="systema.skat.ncts.import.list.search.label.datum"/></td>
                		<td class="tableHeaderField" align="left" >&nbsp;&nbsp;<spring:message code="systema.skat.ncts.import.list.search.label.status"/></td>
                		<td class="tableHeaderField" align="left" >&nbsp;&nbsp;<spring:message code="systema.skat.ncts.import.list.search.label.type"/></td>
                		<td class="tableHeaderField" align="left" >&nbsp;&nbsp;<spring:message code="systema.skat.ncts.import.list.search.label.ansvarig"/></td>
                		<td class="tableHeaderField" align="left" >&nbsp;&nbsp;<spring:message code="systema.skat.ncts.import.list.search.label.godsnr"/></td>
                		<td class="tableHeaderField" align="left" >&nbsp;&nbsp;<spring:message code="systema.skat.ncts.import.list.search.label.frigivningsDatum"/></td>
                		<%--
                		<td class="tableHeaderField">&nbsp;Kopiera Ärende&nbsp;</td>
	                 --%>    
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
		               <td class="tableCell" align="center" >
		               		<c:choose>
		               		<c:when test="${empty topic.status || topic.status == 'M' || topic.status == 'F'}">
	              	   	   		<span title="Angivelse er opdaterbar"></span>
	            	   	   			<a href="skatnctsimport_edit.do?action=doFetch&avd=${topic.avd}&opd=${topic.opd}&status=${topic.status}">
	               					<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
	               				</a>
               				</c:when>
               				<c:otherwise>
               					<c:choose>
	               					<c:when test="${topic.status == 'U' || topic.status == 'H'}">
		              	   	   			<span title="Losning er opdaterbar">
		              	   	   			<a href="skatnctsimport_unloading_edit.do?action=doFetch&origo=list&avd=${topic.avd}&sign=${topic.sign}&opd=${topic.opd}&status=${topic.status}&datum=${topic.datum}">
											<img valign="bottom" src="resources/images/unloading.png" width="16" hight="16" border="0" alt="lossning - editable">					
										</a>
		              	   	   			</span>
		               				</c:when>
		               				<c:otherwise>
												               				
		               				</c:otherwise>
	               				</c:choose>									
               				</c:otherwise>
	               			</c:choose>
	               	   </td>
               		   <td class="tableCell" width="10%" >&nbsp;
               		   		<c:choose>
		               		<c:when test="${empty topic.status || topic.status == 'M' ||  topic.status == 'F'}">
	              	   	   		<span title="Angivelse er opdaterbar"></span>
	            	   	   			<a href="skatnctsimport_edit.do?action=doFetch&avd=${topic.avd}&opd=${topic.opd}&status=${topic.status}">
	               					&nbsp;&nbsp;${topic.opd}
	               				</a>
               				</c:when>
               				<c:otherwise>
               					<c:choose>
	               					<c:when test="${topic.status == 'U' || topic.status == 'H'}">
		              	   	   			<span title="Lossning är uppdaterbar">
		              	   	   			<a href="skatnctsimport_unloading_edit.do?action=doFetch&origo=list&avd=${topic.avd}&sign=${topic.sign}&opd=${topic.opd}&status=${topic.status}&datum=${topic.datum}">
											&nbsp;&nbsp;${topic.opd}
										</a>
		              	   	   			</span>
		               				</c:when>
		               				<c:otherwise>
										<a href="skatnctsimport_edit.do?action=doFetch&avd=${topic.avd}&opd=${topic.opd}&status=${topic.status}">
											&nbsp;&nbsp;${topic.opd}
										</a>		               				
		               				</c:otherwise>
	               				</c:choose>									
               				</c:otherwise>
	               			</c:choose>
               		   </td>
		               <td class="tableCell" >&nbsp;${topic.mrnNr}</td>
		               <td class="tableCell" >&nbsp;${topic.datum}</td>
		               <td class="tableCell" >&nbsp;<b>${topic.status}</b></td>
		               <td class="tableCell" >&nbsp;
               				<c:if test="${topic.forenklad == 'J'}">Forenklet</c:if>
		               		<c:if test="${topic.forenklad == 'N'}">Normal</c:if>
		               </td>
		               <td class="tableCell" >&nbsp;${topic.ansNavn}</td>
		               <td class="tableCell" >&nbsp;${topic.godsNr}</td>
		               <td class="tableCell" >&nbsp;${topic.datumFr}</td>
		            </tr> 
		            </c:forEach>
	            </table>
			</td>	
			</tr>
		</table>
		</td>
		</tr>
    </c:if> 
</table>	
		
<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->

