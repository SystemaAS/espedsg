<!DOCTYPE html>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %> <!-- generally you will include this in a header.jsp -->
 
<html>
	<head>
		<link href="resources/${user.cssEspedsg}?ver=${user.versionEspedsg}" rel="stylesheet" type="text/css"/>
		<link type="text/css" href="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/overcast/jquery-ui.css" rel="stylesheet">
		<c:choose>
			<%-- set up BEFORE login --%>
			<c:when test="${ fn:contains(user.cssEspedsg, 'Toten') }"> 
				<link rel="SHORTCUT ICON" type="image/ico" href="resources/images/toten_ico.ico"></link>
			</c:when>
			<c:otherwise>
				<link rel="SHORTCUT ICON" type="image/png" href="resources/images/systema_logo.png"></link>
			</c:otherwise>
		</c:choose>
		<%-- <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> --%>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE" />
		<title>${user.custName} - Ufortollede oppdrag</title>
	</head>
	<body>
	<%-- include som javascript functions --%>
	<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
	<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
	
	<script type="text/javascript" src="resources/js/systemaWebGlobal.js?ver=${user.versionEspedsg}"></script>
	
    <table class="noBg" width="1100" border="0" cellspacing="0" cellpadding="0">
		<%--Banner --%>
	 	<tr>
    		<td height="60" class="headerTdsBannerAreaBg" width="100%" align="left" colspan="3"> 
    			 <table width="1100" border="0" cellspacing="0" cellpadding="0">
    			 	<tr>
			        	<td>&nbsp;</td>
			        	<td>&nbsp;</td>
				 		<td>&nbsp;</td>
			        </tr>
				 	<tr>
				 		<c:choose>
						 		<c:when test="${not empty user.logo}">
					 				<c:choose>
						 				<c:when test="${fn:contains(user.logo, '/')}">
						 					<td class="text12" width="10%" align="center" valign="middle" >
												<img src="${user.logo}" border="0" width="30px" height="20px">
											</td>
										</c:when>
										<c:otherwise>
											<c:choose>
												<c:when test="${fn:contains(user.logo, 'systema')}">
												<td class="text12white" width="10%" align=left valign="bottom" >&nbsp;
													<img src="resources/images/${user.logo}" border="0" width=80px height=50px>
												</td>
												</c:when>
												<c:otherwise>
													<c:if test="${fn:contains(user.logo, 'logo')}">
														<td class="text12white" width="10%" align=left valign="bottom" >&nbsp;
															<img src="resources/images/${user.logo}" border="0" >
														</td>
													</c:if>
												</c:otherwise>
											</c:choose>	
										</c:otherwise>
									</c:choose>
	   			 				</c:when> 
	   			 				<c:otherwise>
							 		<td class="text12white" width="10%" align=left valign="bottom" >&nbsp;</td>
							 		<%-- <td class="text12white" width="10%" align=right valign="bottom" >&nbsp;</td>--%>
						 		</c:otherwise>
					 		</c:choose>
				 		<td class="text22Bold" width="40%" align="middle" valign="middle" style="color:#778899;" > 
				 			eSped<font style="color:#003300;">sg</font> - Ufortollede oppdrag
			 			</td>
		    			<td class="text12" width="30%" align="right" valign="middle" >
		    				<img src="resources/images/systema_logo.png" border="0" width=80px height=50px >&nbsp;
	    				</td>
			      		<%-- <td class="text12white" width="10%" align=right valign="bottom" >&nbsp;</td>--%>
			        </tr>
			        <tr>
			        	<td>&nbsp;</td>
			        	<td>&nbsp;</td>
				 		<td class="text14" width="10%" align=right valign="bottom" >&nbsp;</td>
			        </tr>
			        <tr class="text" height="1"><td></td></tr>
			     </table> 
			</td>
		</tr>
    		<tr>
			<td height="23" class="tabThinBorderLightSlateGray" width="100%" align="left" colspan="3"> 
    			 <table width="1100" border="0" cellspacing="1" cellpadding="1">
			 	<tr >
		    		<td class="text11" width="50%" align="left" >&nbsp;&nbsp;</td>
     				<td class="text11" width="50%" align="right">
     					<font class="headerMenuGreen">
		    				<img src="resources/images/appUser.gif" border="0" > 
						<font class="text11User" >${user.user}&nbsp;</font>${user.usrLang}
		    			</font>
		    			<font color="#FFFFFF"; style="font-weight: bold;">&nbsp;|&nbsp;</font>
		    			<a href="logoutUoppdrag.do">
		    				<font class="headerMenuGreen"><img src="resources/images/home.gif" border="0">&nbsp;
		    					<font class="text11User" ><spring:message code="dashboard.menu.button"/>&nbsp;</font>
		    				</font>
		    			</a>
		    			<font color="#FFFFFF"; style="font-weight: bold;">&nbsp;&nbsp;|&nbsp;</font>
		    			<font class="text12LightGreen" style="cursor:pointer;" onClick="showPop('versionInfo');">${user.versionSpring}&nbsp;</font>
    				    <div class="text11" style="position: relative;" align="left">
							<span style="position:absolute; left:5px; top:30px; width:250px" id="versionInfo" class="popupWithInputText"  >	
				           		&nbsp;<b>${user.versionEspedsg}</b>
			           			<br/><br/>
			           			&nbsp;<a href="renderLocalLog4j.do" target="_blank">log4j</a>
			           			<br/><br/><br/>
			           			<button name="versionInformationButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('versionInfo');">Close</button> 
			           		</span>
						</div> 
		    		</td>
      	        </tr>
      	     </table> 
			</td>
	    </tr>
		<tr class="text" height="20"><td></td></tr>
	    <%-- Validation Error section 
	    <c:if test="${errorMessage!=null}">
		<tr>
			<td colspan=3>
			<table>
					<tr>
					<td class="textError">					
			            <ul>
			                <li >
			                	${errorMessage}
			                </li>
			            
			            </ul>
					</td>
					</tr>
			</table>
			</td>
		</tr>
		</c:if>
		--%>
	    <tr class="text" height="2"><td></td></tr>
		
		<%-- ------------------------------------
		Content after banner och header menu
		------------------------------------- --%>
		<tr>
    		<td width="100%" align="left" colspan="3"> 
    		     
     