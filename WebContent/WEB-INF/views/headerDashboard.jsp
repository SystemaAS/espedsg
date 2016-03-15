<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %> <!-- generally you will include this in a header.jsp -->
<html>
	<head>
		<link href="resources/espedsg.css?ver=${user.versionEspedsg}" rel="stylesheet" type="text/css"/>
		
		<link rel="SHORTCUT ICON" type="image/png" href="resources/images/systema_logo.png"></link>
		<%-- <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> --%>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE" />
		<title>${user.custName} eSpedsg</title>
	</head>

	<body>
	<%-- include som javascript functions --%>
	<script type="text/javascript" src="${user.httpProtocol}://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<script type="text/javascript" src="${user.httpProtocol}://ajax.googleapis.com/ajax/libs/jqueryui/1.10.4/jquery-ui.min.js""></script>
	<script type="text/javascript" src="resources/js/jquery.blockUI.js"></script>
	
	<%-- include som javascript functions --%>
 	<script type="text/javascript" src="resources/js/systemaWebGlobal.js?ver=${user.versionEspedsg}"></script>
 
	<table class="dashboardFrameMain" width="1100" border="0" cellspacing="0" cellpadding="0">
		<%--Banner --%>
		<tr class="text" height="10"><td></td></tr>
		<tr >
    		<td height="60" align="center" colspan="2"> 
			 <table width="1050" height="100" class="dashboardBanner" border="0" cellspacing="0" cellpadding="0" align="center" 
    			 		<c:if test="${ not empty user.banner }">
    			 			style="background-image:url('${user.banner}');background-repeat:no-repeat;" 
    			 		</c:if>  >
    			 
    			 		<tr height="5"><td></td></tr>
				 	<tr>
				 		<td style="min-width: 300px; max-width: 300px;" class="text22Bold" align=left valign="middle" >
				 			<c:if test="${not empty user.logo}">
								<img src="${user.logo}" border="0" >
    			 				</c:if>
						</td>
						
						
						<td class="text32Bold" width="40%" align="center" valign="middle" 
								<c:choose>
			 					<c:when test="${ not empty user.banner }">
				 					style="font-style:italic; color:#555555;" <%-- gray --%>
				 				</c:when>
				 				<c:otherwise>
				 					style="font-style:italic; color:#778899;" <%-- special metal gray --%>
				 				</c:otherwise>
				 				</c:choose>
						>
				 			eSped<font style="color:#003300;">sg</font>
				 		</td>
			    			<td width="30%" align="right" valign="middle">
							<c:if test="${not empty user.systemaLogo && (user.systemaLogo=='Y')}">
				    				<img src="resources/images/systema_logo.png" border="0" width=85px height=55px>
								&nbsp;&nbsp; 
							</c:if>		
		    				</td>
			      		
			        </tr>
    			 		<tr height="5"><td></td></tr>
		     </table> 
 		</td>

		</tr>
		<tr height="2"><td></td></tr>
		<%-- Dashboard menu --%>
		<tr >
			<td height="23" align="center" colspan="2"> 
    			 <table class="dashboardFrameMain" width="1050" border="0" cellspacing="0" cellpadding="0" align="center" >
				 	<tr >
			    		<td class="text11" width="50%" align="left" >&nbsp;&nbsp;
			    			
	      				</td>
	      				<td class="text11" width="50%" align="right">
	      					<font class="headerMenuGreenNoPointer">
	      						<img title="${user.logo}" src="resources/images/appUser.gif" border="0" onClick="showPop('specialInformationAdmin');">&nbsp;
	      							<font style="color:#000000">${user.user}&nbsp;</font>${user.usrLang}&nbsp;
			    				        <span style="position:absolute; left:750px; top:180px; width:320px; height:20px;" id="specialInformationAdmin" class="popupWithInputText"  >
						           		<div class="text11" align="left">
						           			${activeUrlRPG}
						           			&nbsp;&nbsp;&nbsp;&nbsp;<button name="specialInformationButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('specialInformationAdmin');">Close</button> 
						           		</div>
							        </span>   		
			    				
				    			</font>
				    			<font color="#FCFFF0"; style="font-weight: bold;" onClick="showPop('userInfo');">&nbsp;|&nbsp;</font>
				    			<span style="position:absolute; left:750px; top:180px; width:150px; height:20px;" id="userInfo" class="popupWithInputText"  >
						           		<div class="text11" align="left">
						           			<%-- in order to see TDS Behörighet pw --%>
						           			${user.user}@${user.pwAS400}
						           			&nbsp;&nbsp;&nbsp;&nbsp;<button name="userInfoButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('userInfo');">Close</button> 
						           		</div>
							        </span>
				    			<a href="logout.do">
				    				<font class="headerMenuGreen">
				    					<img src="resources/images/lock.gif" border="0">&nbsp;
				    					<font style="color:#000000" ><spring:message code="logout.logout"/>&nbsp;</font>
				    				</font>
			    				</a>
			    			</td>
			        </tr>
			     </table> 
			</td>
	    </tr>
	 	
		
	    
		