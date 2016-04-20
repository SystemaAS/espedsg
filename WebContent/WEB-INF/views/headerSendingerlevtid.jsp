<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %> <!-- generally you will include this in a header.jsp -->
 
<html>
	<head>
		<link href="resources/espedsg.css?ver=${user.versionEspedsg}" rel="stylesheet" type="text/css"/>
		<link type="text/css" href="${user.httpProtocol}://ajax.googleapis.com/ajax/libs/jqueryui/1.10.0/themes/overcast/jquery-ui.css" rel="stylesheet">
		<link rel="SHORTCUT ICON" type="image/png" href="resources/images/systema_logo.png"></link>
		<%-- <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> --%>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE" />
		<title>${user.custName} - Sendinger / Leveringstid</title>
	</head>
	<body>
	<%-- include som javascript functions --%>
	<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
	<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jqueryui/1.8.24/jquery-ui.min.js"></script>
	
	<script type="text/javascript" src="resources/js/systemaWebGlobal.js?ver=${user.versionEspedsg}"></script>
	
    <table class="noBg" width="1100" border="0" cellspacing="0" cellpadding="0">
		<%--Banner --%>
	 	<tr>
	 		 <%-- class="grayTitanBg" --%>
    		<td height="60" class="headerTdsBannerAreaBg" width="100%" align="left" colspan="3"> 
    			 <table width="1100" border="0" cellspacing="0" cellpadding="0">
    			 	<tr>
			        	<td>&nbsp;</td>
			        	<td>&nbsp;</td>
				 		<td>&nbsp;</td>
			        </tr>
				 	<tr>
				 		<td style="min-width: 300px; max-width: 300px;" class="text12white" align=left valign="middle" >
				 			<img align="middle" src="${user.logo}" >
				 		</td>
				 		<td class="text22Bold" width="40%" align="middle" valign="middle" > 
				 			Sendinger / Leveringstid
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
						<font style="color:#000000" >${user.user}&nbsp;</font>${user.usrLang}
		    			</font>
		    			<font color="#FFFFFF"; style="font-weight: bold;">&nbsp;|&nbsp;</font>
		    			<a href="logoutSendingerlevtid.do">
		    				<font class="headerMenuGreen"><img src="resources/images/home.gif" border="0">&nbsp;
		    					<font style="color:#000000;" ><spring:message code="dashboard.menu.button"/>&nbsp;</font>
		    				</font>
		    			</a>
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
    		     
     