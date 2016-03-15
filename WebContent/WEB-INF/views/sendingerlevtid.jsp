<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerSendingerlevtid.jsp" />
<!-- =====================end header ==========================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/sendingerlevtid.js?ver=${user.versionEspedsg}"></SCRIPT>
	
<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
<tr>
	<td>
	<%-- tab container component --%>
	<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
		<tr height="2"><td></td></tr>
		<tr height="25">
			<td width="20%" valign="bottom" class="tabDisabled" align="center">
				<a style="display:block;" href="sendingerlevtidgatefilter.do?action=doBack">
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.overview.sendingerlevtid.filter.tab"/></font>
					&nbsp;<img valign="bottom" src="resources/images/find.png" border="0" alt="filter">
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					
			<td width="20%" valign="bottom" class="tabDisabled" align="center">
				<a style="display:block;" href="sendingerlevtidgate.do?action=doBack">
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.overview.sendingerlevtid.diagram.tab"/></font>
					<img valign="bottom" src="resources/images/pieChart.png" width="20" hight="20" border="0" alt="Sendinger">
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			
			<td width="25%" valign="bottom" class="tab" align="center">
				<c:choose>
				<c:when test="${not empty model.listType}">
					<font class="text12">${model.listType}</font></font>&nbsp;&nbsp;<font class="text12Bold">[${model.offset}]</font>
					&nbsp;&nbsp;&nbsp;				
					<input class="inputFormSubmitUoppNeutralR" type="submit" name="totalButton" value='${model.listSize}'/>
				</c:when>
				<c:otherwise>
					<font class="tabLinkSign">&nbsp;Alle&nbsp;<b>${model.listSize}</b></font>
				</c:otherwise>
				</c:choose>
				
     		</td>
			<td width="55%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>	
			
		</tr>
	</table>
	</td>
</tr>
<tr>
	<td>
	<%-- tab component --%>
 		<table width="100%" class="tabThinBorderWhite" border="0" cellspacing="0" cellpadding="0">
 			<tr height="10"><td></td></tr>
 				<%--
 			<tr>
 	        <td>
 	        		<table  border="0" cellspacing="0" cellpadding="0">
		 	        	<form name="searchForm" id="searchForm" action="todoFindByField.do" method="post" >
		 	        	<input type="hidden" name="uoType" id="uoType" value='${model.listType}'>
		 	        	<input type="hidden" name="llim" id="llim" value='${model.llim}'>
		 	        	<input type="hidden" name="ulim" id="ulim" value='${model.ulim}'>
		 	        
		 	        	<tr>	
			 	        <td class="text12" align="left" >&nbsp;&nbsp;&nbsp;Dato</td>
		                <td class="text12" align="left" >&nbsp;&nbsp;&nbsp;Dager</td>
		                <td class="text12" align="left" >&nbsp;&nbsp;&nbsp;Tar</td>
		                <td class="text12" align="left" >&nbsp;&nbsp;Oppdrag</td>
		                <td class="text12" align="left" >&nbsp;&nbsp;Sign</td>
		                <td class="text12" align="left" >&nbsp;&nbsp;Avd</td>
		                <td class="text12" align="left" >&nbsp;&nbsp;Ext.Ref</td>
		                <td class="text12" align="left" >&nbsp;&nbsp;Handl.faktura</td>
		                <td class="text12" align="left" >&nbsp;&nbsp;Mottaker</td>
		                <td class="text12" align="left" >&nbsp;&nbsp;Kll</td>
		            </tr>
		            <tr>
			            <td align="left" >&nbsp;&nbsp;<input onKeyPress="return dateKey(event)" type="text" class="inputText" name="dato" id="dato" size="10" maxlength="8" value='${model.searchFilter.dato}'>&nbsp;</td>
						<td align="left" >&nbsp;<input onKeyPress="return amountKey(event)" type="text" class="inputText" name="dager" id="dager" size="10" maxlength="5" value='${model.searchFilter.dager}'>&nbsp;</td>
						<td align="left" >&nbsp;<input type="text" class="inputText" name="tariffor" id="tariffor" size="5" maxlength="10" value='${model.searchFilter.tariffor}'>&nbsp;</td>
						<td align="left" >&nbsp;<input type="text" class="inputText" name="opd" id="opd" size="10" maxlength="30" value='${model.searchFilter.opd}'>&nbsp;</td>
						<td align="left" >
							<c:choose>
								<c:when test="${not empty model.searchFilterGateChar.sign}"> 
									&nbsp;<input readonly type="text" class="inputTextReadOnly" name="sign" id="sign" size="5" maxlength="10" value='${model.searchFilterGateChar.sign}'>&nbsp;
								</c:when>
								<c:otherwise> 
									&nbsp;<input type="text" class="inputText" name="sign" id="sign" size="5" maxlength="10" value='${model.searchFilter.sign}'>&nbsp;
								</c:otherwise>
							</c:choose>
						</td>
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
						<td align="left" >&nbsp;<input type="text" class="inputText" name="extRef" id="extRef" size="10" maxlength="20" value='${model.searchFilter.extRef}'>&nbsp;</td>
						<td align="left" >&nbsp;<input type="text" class="inputText" name="antHfakt" id="antHfakt" size="10" maxlength="20" value='${model.searchFilter.antHfakt}'>&nbsp;</td>
						<td align="left" >&nbsp;<input type="text" class="inputText" name="mottaker" id="mottaker" size="10" maxlength="50" value='${model.searchFilter.mottaker}'>&nbsp;</td>
						<td align="left" >&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="kll" id="kll" size="5" maxlength="20" value='${model.searchFilter.kll}'>&nbsp;</td>
						<td valign="top" align="left" >
		                   &nbsp;<input class="inputFormSubmit" type="submit" name="submit" value='<spring:message code="search.label"/>'>
		                   <img src="resources/images/find.png" border="0" alt="">
		                </td>
		                
					</tr>
	            	    <tr>
		                <td class="text12" align="left" >&nbsp;&nbsp;&nbsp;Vekt</td>
		                <td class="text12" align="left" >&nbsp;&nbsp;Godsnr</td>
		                <td class="text12" align="left" >&nbsp;&nbsp;Status</td>
		                <td class="text12" align="left" >&nbsp;&nbsp;Merknad</td>
		            </tr>
					<tr>	
						<td align="left" >&nbsp;<input type="text" class="inputText" name="vekt" id="vekt" size="5" maxlength="20" value='${model.searchFilter.vekt}'>&nbsp;</td>
						<td align="left" >&nbsp;<input onKeyPress="return amountKey(event)" type="text" class="inputText" name="godsnr" id="godsnr" size="10" maxlength="20" value='${model.searchFilter.godsnr}'>&nbsp;</td>
						<td align="left" >&nbsp;<input type="text" class="inputText" name="status" id="status" size="5" maxlength="10" value='${model.searchFilter.status}'>&nbsp;</td>
						<td align="left" >&nbsp;<input type="text" class="inputText" name="merknad" id="merknad" size="10" maxlength="20" value='${model.searchFilter.merknad}'>&nbsp;</td>
		            </tr>
		            <tr height="10"><td></td></tr>
		           
			 	    </form>
 	        		</table>
 	        </td>
 	        </tr> 
 	          --%>	        
 	     </table>
 	     
 	     <%-- Excel
     	 <table width="100%" cellspacing="0" border="0" cellpadding="0">
     	 	<tr height="1"><td colspan="5"></td></tr>
 	     	<tr>
	 	        <td align="right" >
	 	        		<a href="todoListExcelView.do" target="_new">
                 		<img valign="bottom" id="sendingerExcel" src="resources/images/excel.png" border="0" alt="excel">&nbsp;Excel
	 	        		</a>
	 	        		&nbsp;
	 	        		
			 	</td>
		 	</tr>
	 	 </table>
	 	  --%>
	 	  
 	     <table width="100%" cellspacing="0" border="0" cellpadding="0">   
			 <tr height="1"><td colspan="5"></td></tr>
      			
    			  <tr class="tableHeaderField" height="20" valign="left">
                 <td class="tableHeaderFieldFirst">&nbsp;Send.nr&nbsp;</td>
                 <td class="tableHeaderField">&nbsp;Avd</td>   
                 <td class="tableHeaderField">&nbsp;Turnr.</td>
                 <td class="tableHeaderField">&nbsp;Agent</td>
                 <td class="tableHeaderField">&nbsp;Knr.</td>
                 <td class="tableHeaderField">&nbsp;Mottaker</td>
                 <td class="tableHeaderField">&nbsp;Pnr.</td>
                 <td class="tableHeaderField">&nbsp;Sted</td>
                 <td class="tableHeaderField">&nbsp;A.dg</td>
                 <td class="tableHeaderField">&nbsp;Dg</td>
                 <td class="tableHeaderField">&nbsp;Ant Kll</td>
                 <td class="tableHeaderField">&nbsp;Vekt</td>
                 <td class="tableHeaderField">&nbsp;014</td>
                 <td nowrap class="tableHeaderField">&nbsp;InnDato</td>
                 <td nowrap class="tableHeaderField">&nbsp;Utdato</td>
                 <td nowrap class="tableHeaderField">&nbsp;PODdato</td>
                 <td class="tableHeaderField">&nbsp;Dgr</td>
                 <td class="tableHeaderField">&nbsp;Res</td>
                	 <td nowrap class="tableHeaderField">&nbsp;PODarc Dt</td>
                 <td class="tableHeaderField">&nbsp;PAres</td>
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
	              <td class="tableCellFirst" >&nbsp;${record.sendNr}</td>
	              <td class="tableCell" width="5%">&nbsp;${record.avd}</td>
   	              <td class="tableCell" >&nbsp;${record.turNr}</td>
	              <td class="tableCell" >&nbsp;${record.agentNr}</td>
	              <td class="tableCell" >&nbsp;${record.oppdragsGiver}</td>
	              <td class="tableCell" >&nbsp;${record.mottaker}</td>
	              <td class="tableCell" >&nbsp;${record.postNr}</td>
	              <td class="tableCell" >&nbsp;${record.sted}</td>
	              <td class="tableCell" >&nbsp;${record.avgDager}</td>
	              <td class="tableCell" >&nbsp;${record.dagerFramfTid}</td>
	              <td class="tableCell" >&nbsp;${record.antall}</td>
	              <td class="tableCell" >&nbsp;${record.vekt}</td>
	              <td class="tableCell" >&nbsp;${record.stat014_16}</td>
	              <td nowrap class="tableCell" >&nbsp;${record.innDato}</td>
	              <td nowrap class="tableCell" >&nbsp;${record.utDato}</td>
	              <td nowrap class="tableCell" >&nbsp;${record.podDato}</td>
	              <td class="tableCell" >&nbsp;${record.dgrPod}</td>
	              <td class="tableCell" >&nbsp;${record.resultat}</td>
	              <td nowrap class="tableCell" >&nbsp;${record.podADato}</td>
	              <td class="tableCell" >&nbsp;${record.podARes}</td>
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

