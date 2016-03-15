<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerUoppdrag.jsp" />
<!-- =====================end header ==========================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/uoppdrag.js?ver=${user.versionEspedsg}"></SCRIPT>
	
<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
<tr>
	<td>
	<%-- tab container component --%>
	<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
		<tr height="2"><td></td></tr>
		<tr height="25">
			<td width="20%" valign="bottom" class="tabDisabled" align="center">
				<a style="display:block;" href="uoppdraggate.do?action=doBack">
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.overview.uoppdrag.diagram.tab"/></font>
					<img valign="bottom" src="resources/images/barChart.png" width="20" hight="20" border="0" alt="bar graph">
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			
			<td width="20%" valign="bottom" class="tab" align="center">
				<c:choose>
				<c:when test="${not empty model.listType}">
					<font class="text12"><spring:message code="systema.overview.uoppdrag.intervalprefix.tab"/></font>&nbsp;&nbsp;<font class="text12Bold">[${model.listInterval}]</font>
					&nbsp;&nbsp;&nbsp;				
					<input class="inputFormSubmitUoppNeutralR" type="submit" name="totalButton" value='${model.listSize}'/>
				</c:when>
				<c:otherwise>
					<font class="tabLinkSign">&nbsp;<spring:message code="systema.overview.uoppdrag.intervalAll.tab"/>&nbsp;<b>${model.listSize}</b></font>
				</c:otherwise>
				</c:choose>
     		</td>
			<td width="80%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>	
			
		</tr>
	</table>
	</td>
</tr>
<tr>
	<td>
	<%-- tab component --%>
 		<table width="100%" class="tabThinBorderWhite" border="0" cellspacing="0" cellpadding="0">
 			<tr height="10"><td></td></tr>
 			<tr>
 	        <td>
 	        		<table  border="0" cellspacing="0" cellpadding="0">
		 	        	<form name="searchForm" id="searchForm" action="uoppdragFindByField.do" method="post" >
		 	        	<input type="hidden" name="uoType" id="uoType" value='${model.listType}'>
		 	        	<input type="hidden" name="llim" id="llim" value='${model.llim}'>
		 	        	<input type="hidden" name="ulim" id="ulim" value='${model.ulim}'>
		 	        	<tr>	
			 	        <td class="text12" align="left" >&nbsp;&nbsp;&nbsp;<spring:message code="systema.overview.uoppdrag.main.list.label.date"/></td>
		                <td class="text12" align="left" >&nbsp;&nbsp;&nbsp;<spring:message code="systema.overview.uoppdrag.main.list.label.tar"/></td>
		                <td class="text12" align="left" >&nbsp;&nbsp;<spring:message code="systema.overview.uoppdrag.main.list.label.avd"/></td>
		                <td class="text12" align="left" >&nbsp;&nbsp;<spring:message code="systema.overview.uoppdrag.main.list.label.topicNr"/></td>
		                <td class="text12" align="left" >&nbsp;&nbsp;<spring:message code="systema.overview.uoppdrag.main.list.label.sign"/></td>
		                <td class="text12" align="left" >&nbsp;&nbsp;<spring:message code="systema.overview.uoppdrag.main.list.label.extref"/></td>
		                <td class="text12" align="left" >&nbsp;&nbsp;<spring:message code="systema.overview.uoppdrag.main.list.label.handelsFakt"/></td>
		                <td class="text12" align="left" >&nbsp;&nbsp;<spring:message code="systema.overview.uoppdrag.main.list.label.godsNr"/></td>
		                <td class="text12" align="left" >&nbsp;&nbsp;<spring:message code="systema.overview.uoppdrag.main.list.label.status"/></td>
		                <td class="text12" align="left" >&nbsp;&nbsp;<spring:message code="systema.overview.uoppdrag.main.list.label.mark"/></td>
		                
		            </tr>
		            <tr>
			            <td align="left" >&nbsp;&nbsp;<input onKeyPress="return dateKey(event)" type="text" class="inputText" name="dato" id="dato" size="10" maxlength="8" value='${model.searchFilter.dato}'>&nbsp;</td>
						<td align="left" >&nbsp;<input type="text" class="inputText" name="tariffor" id="tariffor" size="4" maxlength="3" value='${model.searchFilter.tariffor}'>&nbsp;</td>
						<td align="left" >
							<c:choose>
								<c:when test="${not empty model.searchFilterGateChar.avd}"> 
									&nbsp;<input readonly type="text" class="inputTextReadOnly" name="avd" id="avd" size="5" maxlength="10" value='${model.searchFilterGateChar.avd}'>&nbsp;
								</c:when>
								<c:otherwise> 
									&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="avd" id="avd" size="5" maxlength="10" value='${model.searchFilter.avd}'>&nbsp;
								</c:otherwise>
							</c:choose>
						</td>
						<td align="left" >&nbsp;<input type="text" class="inputText" name="opd" id="opd" size="7" maxlength="7" value='${model.searchFilter.opd}'>&nbsp;</td>
						<td align="left" >
							<c:choose>
								<c:when test="${not empty model.searchFilterGateChar.sign}"> 
									&nbsp;<input readonly type="text" class="inputTextReadOnly" name="sign" id="sign" size="4" maxlength="3" value='${model.searchFilterGateChar.sign}'>&nbsp;
								</c:when>
								<c:otherwise> 
									&nbsp;<input type="text" class="inputText" name="sign" id="sign" size="4" maxlength="3" value='${model.searchFilter.sign}'>&nbsp;
								</c:otherwise>
							</c:choose>
						</td>
						<td align="left" >&nbsp;<input type="text" class="inputText" name="extRef" id="extRef" size="10" maxlength="20" value='${model.searchFilter.extRef}'>&nbsp;</td>
						<td align="left" >&nbsp;<input type="text" class="inputText" name="mottaker" id="mottaker" size="10" maxlength="50" value='${model.searchFilter.mottaker}'>&nbsp;</td>
						<td align="left" >&nbsp;<input onKeyPress="return amountKey(event)" type="text" class="inputText" name="godsnr" id="godsnr" size="15" maxlength="15" value='${model.searchFilter.godsnr}'>&nbsp;</td>
						<td align="left" >&nbsp;<input type="text" class="inputText" name="status" id="status" size="2" maxlength="1" value='${model.searchFilter.status}'>&nbsp;</td>
						<td align="left" >&nbsp;<input type="text" class="inputText" name="merknad" id="merknad" size="15" maxlength="70" value='${model.searchFilter.merknad}'>&nbsp;</td>
						
						<td valign="top" align="left" >
		                   &nbsp;<input class="inputFormSubmit" type="submit" name="submit" id="submit" value='<spring:message code="search.label"/>'>
		                   <img src="resources/images/find.png" border="0" alt="">
		                </td>
		                
					</tr>
	            	    <tr height="5"><td></td></tr>
					<tr>	
						<td class="text12" colspan="4">&nbsp;&nbsp;
						<%-- <c:if test="${not empty model.searchFilterGateChar.antHfaktFlag}"> disabled </c:if>  --%>
							<select <c:if test="${not empty model.searchFilterGateChar.antHfaktFlag}"> disabled </c:if> class="text11" id="antHfaktFlag" name="antHfaktFlag">
		        					<option  style="font-style:italic;" value="">HandelsFakt - Alle</option>
	        						<option value="Y" <c:if test="${model.searchFilter.antHfaktFlag == 'Y' || model.searchFilterGateChar.antHfaktFlag =='Y'}"> selected </c:if>  >Med skannet HF</option>
	        						<option value="N" <c:if test="${model.searchFilter.antHfaktFlag == 'N' || model.searchFilterGateChar.antHfaktFlag =='N'}"> selected </c:if>  >Uten skannet HF</option>
							</select>&nbsp;
							<select <c:if test="${not empty model.searchFilterGateChar.statusFlag}"> disabled </c:if>  class="text11" id="statusFlag" name="statusFlag">
		        					<option  style="font-style:italic;" value="">Status - Alle</option>
	        						<option value="N" <c:if test="${model.searchFilter.statusFlag == 'N' || model.searchFilterGateChar.statusFlag =='N'}"> selected </c:if>  > Ikke påbegynt</option>
	        						<option value="Y" <c:if test="${model.searchFilter.statusFlag == 'Y' || model.searchFilterGateChar.statusFlag =='Y'}"> selected </c:if>  > Påbegynt</option>
							</select>
							
						</td>
						
		            </tr>
		            <tr height="5"><td></td></tr>
			 	    </form>
 	        		</table>
 	        </td>
 	        </tr> 	        
 	     </table>
     	 <table width="100%" cellspacing="0" border="0" cellpadding="0">
     	 	<tr height="1"><td colspan="5"></td></tr>
 	     	<tr>
	 	        <td align="right" >
	 	        		<a href="uoppListExcelView.do" target="_new">
                 		<img valign="bottom" id="uoppExcel" src="resources/images/excel.png" border="0" alt="excel">&nbsp;Excel
	 	        		</a>
	 	        		&nbsp;
	 	        		
			 	</td>
		 	</tr>
	 	 </table>
 	     <table width="100%" cellspacing="0" border="0" cellpadding="0">   
			 <tr height="1"><td colspan="5"></td></tr>
      			
    			  <tr class="tableHeaderField" height="20" valign="left">
                 <td class="tableHeaderFieldFirst" align="right">&nbsp;<spring:message code="systema.overview.uoppdrag.main.list.label.date"/>&nbsp;</td>
                 <td nowrap class="tableHeaderField" align="right">&nbsp;<spring:message code="systema.overview.uoppdrag.main.list.label.dager"/>&nbsp;
                 	<c:choose>
                 	<c:when test="${empty model.sortCol || model.sortCol == 'dager'}">
	                 	<a href="uoppdragSortByColumn.do?uoType=${model.listType}&col=dager&sort=${model.imgSortPng}&llim=${model.llim}&ulim=${model.ulim}">
	                 		<img valign="bottom" id="imgSortDager" width="12" hight="12" src="resources/images/${model.imgSortPng}" border="0" alt="">
	                		</a>
                		</c:when>
                		<c:otherwise>
	                 	<a href="uoppdragSortByColumn.do?uoType=${model.listType}&col=dager&sort=${model.imgSortPngNeutral}&llim=${model.llim}&ulim=${model.ulim}">
	                 		<img valign="bottom" id="imgSortDager" width="12" hight="12" src="resources/images/${model.imgSortPngNeutral}" border="0" alt="">
	                		</a>                		
                		</c:otherwise>
					</c:choose>
                 </td>   
                 <td class="tableHeaderField">&nbsp;<spring:message code="systema.overview.uoppdrag.main.list.label.tar"/>
                 	<c:choose>
                 	<c:when test="${model.sortCol == 'tariffor'}">
	                 	<a href="uoppdragSortByColumn.do?uoType=${model.listType}&col=tariffor&sort=${model.imgSortPng}&llim=${model.llim}&ulim=${model.ulim}">
	                 		<img valign="bottom" id="imgSortTariffor" width="12" hight="12" src="resources/images/${model.imgSortPng}" border="0" alt="">
	                		</a>
                		</c:when>
                		<c:otherwise>
	                 	<a href="uoppdragSortByColumn.do?uoType=${model.listType}&col=tariffor&sort=${model.imgSortPngNeutral}&llim=${model.llim}&ulim=${model.ulim}">
	                 		<img valign="bottom" id="imgSortTariffor" width="12" hight="12" src="resources/images/${model.imgSortPngNeutral}" border="0" alt="">
	                		</a>
                		</c:otherwise>
					</c:choose>
                 </td>
                 <td class="tableHeaderField" align="right">&nbsp;<spring:message code="systema.overview.uoppdrag.main.list.label.avd"/>&nbsp;
                 	<c:choose>
                 	<c:when test="${model.sortCol == 'avd'}">
	                 	<a href="uoppdragSortByColumn.do?uoType=${model.listType}&col=avd&sort=${model.imgSortPng}&llim=${model.llim}&ulim=${model.ulim}">
	                 		<img valign="bottom" id="imgSortAvd" width="12" hight="12" src="resources/images/${model.imgSortPng}" border="0" alt="">
	                		</a>
                		</c:when>
                		<c:otherwise>
	                 	<a href="uoppdragSortByColumn.do?uoType=${model.listType}&col=avd&sort=${model.imgSortPngNeutral}&llim=${model.llim}&ulim=${model.ulim}">
	                 		<img valign="bottom" id="imgSortAvd" width="12" hight="12" src="resources/images/${model.imgSortPngNeutral}" border="0" alt="">
	                		</a>
                		</c:otherwise>
					</c:choose>
                 </td>
                 <td class="tableHeaderField" align="right">&nbsp;<spring:message code="systema.overview.uoppdrag.main.list.label.topicNr"/>&nbsp;</td>
                 <td nowrap class="tableHeaderField">&nbsp;<spring:message code="systema.overview.uoppdrag.main.list.label.sign"/>
                 	<c:choose>
                 	<c:when test="${model.sortCol == 'sign'}">
	                 	<a href="uoppdragSortByColumn.do?uoType=${model.listType}&col=sign&sort=${model.imgSortPng}&llim=${model.llim}&ulim=${model.ulim}">
	                 		<img valign="bottom" id="imgSortSign" width="12" hight="12" src="resources/images/${model.imgSortPng}" border="0" alt="">
	                		</a>
                		</c:when>
                		<c:otherwise>
	                 	<a href="uoppdragSortByColumn.do?uoType=${model.listType}&col=sign&sort=${model.imgSortPngNeutral}&llim=${model.llim}&ulim=${model.ulim}">
	                 		<img valign="bottom" id="imgSortSign" width="12" hight="12" src="resources/images/${model.imgSortPngNeutral}" border="0" alt="">
	                		</a>
                		</c:otherwise>
					</c:choose>
                 </td>
                 <td class="tableHeaderField">&nbsp;<spring:message code="systema.overview.uoppdrag.main.list.label.extref"/>
					<c:choose>
                 	<c:when test="${model.sortCol == 'extRef'}">
	                 	<a href="uoppdragSortByColumn.do?uoType=${model.listType}&col=extRef&sort=${model.imgSortPng}&llim=${model.llim}&ulim=${model.ulim}">
	                 		<img valign="bottom" id="imgSortExtRef" width="12" hight="12" src="resources/images/${model.imgSortPng}" border="0" alt="">
	                		</a>
                		</c:when>
                		<c:otherwise>
	                 	<a href="uoppdragSortByColumn.do?uoType=${model.listType}&col=extRef&sort=${model.imgSortPngNeutral}&llim=${model.llim}&ulim=${model.ulim}">
	                 		<img valign="bottom" id="imgSortExtRef" width="12" hight="12" src="resources/images/${model.imgSortPngNeutral}" border="0" alt="">
	                		</a>
                		</c:otherwise>
					</c:choose>                 
                 
                 </td>
                 <td class="tableHeaderField">&nbsp;<spring:message code="systema.overview.uoppdrag.main.list.label.handelsFakt"/></td>
                 <td class="tableHeaderField">&nbsp;<spring:message code="systema.overview.uoppdrag.main.list.label.receiver"/></td>
                 <td class="tableHeaderField" align="right">&nbsp;<spring:message code="systema.overview.uoppdrag.main.list.label.kll"/></td>
                 <td class="tableHeaderField" align="right">&nbsp;<spring:message code="systema.overview.uoppdrag.main.list.label.weight"/></td>
                 <td class="tableHeaderField">&nbsp;<spring:message code="systema.overview.uoppdrag.main.list.label.godsNr"/>
					<c:choose>
                 	<c:when test="${model.sortCol == 'godsnr'}">
	                 	<a href="uoppdragSortByColumn.do?uoType=${model.listType}&col=godsnr&sort=${model.imgSortPng}&llim=${model.llim}&ulim=${model.ulim}">
	                 		<img valign="bottom" id="imgSortGodsnr" width="12" hight="12" src="resources/images/${model.imgSortPng}" border="0" alt="">
	                		</a>
                		</c:when>
                		<c:otherwise>
	                 	<a href="uoppdragSortByColumn.do?uoType=${model.listType}&col=godsnr&sort=${model.imgSortPngNeutral}&llim=${model.llim}&ulim=${model.ulim}">
	                 		<img valign="bottom" id="imgSortGodsnr" width="12" hight="12" src="resources/images/${model.imgSortPngNeutral}" border="0" alt="">
	                		</a>
                		</c:otherwise>
					</c:choose>                 
                 </td>
                 <td class="tableHeaderField">&nbsp;<spring:message code="systema.overview.uoppdrag.main.list.label.status"/></td>
                 <td class="tableHeaderField">&nbsp;<spring:message code="systema.overview.uoppdrag.main.list.label.mark"/></td>
              </tr>
              
              <c:forEach items="${model.list}" var="record" varStatus="counter">    
              <c:choose>           
                  <c:when test="${counter.count%2==0}">
                      <tr class="tableRow" height="20" >
                  </c:when>
                  <c:otherwise>   
                      <tr class="tableOddRow" height="20" >
                  </c:otherwise>
              </c:choose>
	              <td class="tableCellFirst" align="right" >&nbsp;${record.dato}&nbsp;</td>
	              <td class="tableCell" width="5%" align="right">&nbsp;${record.dager}&nbsp;</td>
   	              <td class="tableCell" >&nbsp;${record.tariffor}</td>
	              <td class="tableCell" align="right">&nbsp;${record.avd}&nbsp;</td>
	              <td class="tableCell" align="right">&nbsp;
	              	<a href="uoppdrag_render.do?avd=${record.avd}&opd=${record.opd}&uoType=${model.listType}&llim=${model.llim}&ulim=${model.ulim}&lsize=${model.listSize}&antHfakt=${record.antHfakt}&status=${record.status}">
	              		${record.opd}&nbsp;
	              	</a>
	              	
	              </td>
	              <td class="tableCell" >&nbsp;${record.sign}</td>
	              <td class="tableCell" >&nbsp;${record.extRef}</td>
	              <td align="center" class="tableCell" >&nbsp;
	              	<a href="uoppdrag_archive.do?avd=${record.avd}&opd=${record.opd}&sign=${record.sign}&uoType=${model.listType}&llim=${model.llim}&ulim=${model.ulim}&antHfakt=${record.antHfakt}">
	              		<b>${record.antHfakt}</b>
	              		<c:if test="${not empty record.antHfakt}">
	              			<img style="vertical-align:bottom;" id="imgArchive" width="14" hight="14" src="resources/images/archive.png" border="0" alt="Handl.fakt.">
	              		</c:if>
	              	</a>
	              </td>
	              <td class="tableCell" >&nbsp;${record.mottaker}</td>
	              <td class="tableCell" align="right">&nbsp;${record.kll}&nbsp;</td>
	              <td class="tableCell" align="right" >&nbsp;${record.vekt}&nbsp;</td>
	              <td class="tableCell" >&nbsp;${record.godsnr}</td>
	              <td class="tableCell" >&nbsp;${record.status}</td>
	              <td class="tableCell" >&nbsp;${record.merknad}</td>
	              <%--
	              <td class="tableCell" >&nbsp;
	              	<a href="uoppdrag_logging.do?avd=${record.avd}&opd=${record.opd}&sign=${record.sign}&uoType=${model.listType}&llim=${model.llim}&ulim=${model.ulim}">
	              		<img valign="bottom" id="imgTrackTraceLog" width="14" hight="14" src="resources/images/log-icon.gif" border="0" alt="logging">
	              		&nbsp;Til hend.logg
	              	</a>
	              </td>
	               --%>
              </tr>
              <c:set var="numberOfRecords" value="${counter.count}" scope="request" />   
			            
           </c:forEach>
           <tr height="20"><td></td></tr>    
		</tr>         	
       </table>
	</td>
</tr>
</table>

<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->

