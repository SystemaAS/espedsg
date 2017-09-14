<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerSkatMaintenance.jsp" />
<!-- =====================end header ==========================-->
	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/skatmaintenancefelles_ttariffdownloadr.js?ver=${user.versionEspedsg}"></SCRIPT>
	<%-- for dialog popup --%>
	<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">	
	
	<style type = "text/css">
	.ui-dialog{font-size: 90.5%;}
	.ui-datepicker { font-size:9pt;}
	</style>


<table width="100%" class="text11" cellspacing="0" border="0" cellpadding="0">
	<tr height="15"><td>&nbsp;</td></tr>
	<tr>
		<td>
		<%-- tab container component --%>
		<table width="100%" class="text11" cellspacing="0" border="0" cellpadding="0">
			<tr height="2"><td></td></tr>
				<tr height="25"> 
					<td width="20%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a id="alinkSadMaintFellesGate" tabindex=-1 style="display:block;" href="skatmaintenancefelles.do">
						<font class="tabDisabledLink">&nbsp;SKAT - Vedligehold</font>
						<img style="vertical-align: middle;"  src="resources/images/list.gif" border="0" alt="general list">
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="40%" valign="bottom" class="tab" align="center">
						<font class="tabLink">Download af den elektroniske Toldtarif</font>&nbsp;<font class="text12">SKAT</font>&nbsp;
					</td>
					<td width="70%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>	
				</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td>
			<table align="center" width="100%" class="tabThinBorderWhite" border="0" cellspacing="0" cellpadding="0">
	 	    <tr height="30"><td>&nbsp;</td></tr>
	 	    <tr >
	 	    	<td width="5%">&nbsp;</td>
				<td align="left">
					<table class="formFrameHeaderPeachWithBorder" width="50%" cellspacing="0" border="0" align="left">
						<tr>
							<td width="5%">&nbsp;</td>
							<td class="text14" >&nbsp;&nbsp;Download i fast filformat&nbsp;&nbsp;<img style="vertical-align: middle;"  src="resources/images/bulletGreen.png" border="0" width="10px" height="10px" alt="prod"></td>
						</tr>
					</table>
			</tr>
			<tr>	
				<td width="5%">&nbsp;</td>
				<td align="left">	
					<table class="formFramePeachGrayRoundBottom" width="50%" cellspacing="1" border="0" align="left">
						<tr>	
							<td width="100%" class="text14">
								<ul>
									<c:forEach var="record" items="${model.list}" >
										<li><font class="text14MediumBlue">${record}</font></li>
									</c:forEach>
								</ul>
							</td>
						</tr>
						<tr>	
							<td width="100%" class="text14">
								&nbsp;&nbsp;&nbsp;&nbsp;<input class="inputFormSubmit" type="button" name="downloadButton" id="downloadButton" value='Download'/>
							</td>
						</tr>
		 	    	</table>
		 	    </td>	
			</tr>
			
			
	 	    <%-- Validation errors --%>
			<spring:hasBindErrors name="record"> <%-- name must equal the command object name in the Controller --%>
			<tr>
				<td width="5%">&nbsp;</td>
				<td width="100%">
	            	<table align="left" border="0" cellspacing="0" cellpadding="0">
	            	<tr >
					<td >					
			            <ul class="isa_error text12" >
			            <c:forEach var="error" items="${errors.allErrors}">
			                <li >
			                	<spring:message code="${error.code}" text="${error.defaultMessage}"/>&nbsp;&nbsp;
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
				 			<td >
				 				<ul class="isa_error text12" >
                                    <li>[ERROR on Update] - Server return code: ${model.errorMessage}</li>                                    
                                </ul>
				 			</td>
						</tr>
					</table>
				</td>
			</tr>
			</c:if>
			<tr height="2"><td>&nbsp;</td>

	 	    <tr height="20"><td>&nbsp;</td></tr>
	 	     
	 		</table>
		</td>
	</tr>
	
	<%-- Pop-up window --%>
	<tr>
	<td>
		<div id="dialogDownloadTariff" title="Dialog">
			<form  action="skatmaintenancefelles_ttariffdownloadr.do" name="downloadForm" id="downloadForm" method="post">
			<input type="hidden" name="doIt" id="doIt" value="1">
				<p>Download af den elektroniske Toldtarif </p>
				<p>ca ... <b>5 min</b> </p>
			</form>
		</div>
	</td>
	</tr>
		
</table>	

<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->

