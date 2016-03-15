<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %> <!-- generally you will include this in a header.jsp -->

<html>
	<head>
		<link href="resources/espedsg.css?ver=${user.versionEspedsg}" rel="stylesheet" type="text/css"/>
		<link href="resources/jquery.calculator.css" rel="stylesheet" type="text/css"/>
		<link type="text/css" href="${user.httpProtocol}://ajax.googleapis.com/ajax/libs/jqueryui/1.10.0/themes/overcast/jquery-ui.css" rel="stylesheet">
		<%--<link type="text/css" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.0/themes/smoothness/jquery-ui.css" rel="stylesheet"> --%>
		
		<link rel="SHORTCUT ICON" type="image/png" href="resources/images/systema_logo.png"></link>
		<%-- <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> --%>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE" />
		<title>Systema - SKAT Toldsystem</title>
	</head>
	<body>
	<%-- include som javascript functions --%>
	<script type="text/javascript" src="${user.httpProtocol}://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<script type="text/javascript" src="${user.httpProtocol}://ajax.googleapis.com/ajax/libs/jqueryui/1.10.4/jquery-ui.min.js""></script>
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
				 		<td class="text12white" width="10%" align=left valign="bottom" >&nbsp;</td>
				 		<td class="text22Bold" width="80%" align="middle" valign="middle" style="color:#778899;" >
				 			eSped<font style="color:#003300;">sg</font> - SKAT
				 			
				 		</td>
				 		 
			    		<td class="text12" width="10%" align="center" valign="middle" ><img src="resources/images/systema_logo.png" border="0" width=80px height=50px ></td>
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
		<%-- Header menu --%>
		<c:choose>
		<c:when test="${user.authorizedSkatUserAS400 == 'Y'}">
		<tr >
			<td height="23" class="tabThinBorderLightSlateGray" width="100%" align="left" colspan="3"> 
    			 <table width="1100" border="0" cellspacing="0" cellpadding="0">
				 	<tr >
			    		<td class="text11" width="70%" align="left" >&nbsp;&nbsp;
			    			<%-- --------------- --%>
			    			<%-- SKAT EXPORT MENU --%>
			    			<%-- --------------- --%>
			    			<a tabindex=-1 href="skatexport.do?action=doFind&sign=${user.skatSign}">
			    				&nbsp;<font 
			    				<c:choose>           
		                   			<c:when test="${user.activeMenu=='SKAT_EXPORT'}">
		                       			class="headerMenuMediumGreen"
		                   			</c:when>
		                   			<c:otherwise>   
		                       			class="headerMenuLightGreen"
		                   			</c:otherwise>
		               			</c:choose>
			    				
			    				>&nbsp;<spring:message code="systema.skat.export.label"/>&nbsp;</font>
			    			</a>
			    			<%-- 
			    			&nbsp;<font class="headerMenuLightGreen"><spring:message code="systema.skat.export.label"/>&nbsp;</font>
			    			<--%>
			    			&nbsp;<font color="#FFFFFF" style="font-weight: bold;">|</font>
			    			
			    			<%-- ---------------  --%>
			    			<%-- SKAT IMPORT MENU --%>
			    			<%-- ---------------  --%>
			    			<a tabindex=-1 href="skatimport.do?action=doFind&sign=${user.skatSign}" >
			    				&nbsp;<font 
			    				<c:choose>           
		                   			<c:when test="${user.activeMenu=='SKAT_IMPORT'}">
		                       			class="headerMenuMediumGreen"
		                   			</c:when>
		                   			<c:otherwise>   
		                       			class="headerMenuLightGreen"
		                   			</c:otherwise>
		               			</c:choose>
			    				
			    				>&nbsp;<spring:message code="systema.skat.import.label"/>&nbsp;</font>
			    			</a>
			    			&nbsp;<font color="#FFFFFF" style="font-weight: bold;">|</font>
			    			<%-- --------------------- --%>
			    			<%-- SKAT NCTS EXPORT MENU --%>
			    			<%-- --------------------- --%>
			    			<a tabindex=-1 href="skatnctsexport.do?action=doFind&sign=${user.skatSign}">
			    				&nbsp;<font
			    				<c:choose>           
		                   			<c:when test="${user.activeMenu=='SKAT_NCTS_EXPORT'}">
		                       			class="headerMenuMediumGreen"
		                   			</c:when>
		                   			<c:otherwise>   
		                       			class="headerMenuLightGreen"
		                   			</c:otherwise>
		               			</c:choose>
			    				>&nbsp;<spring:message code="systema.skat.ncts.export.label"/>&nbsp;</font>
			    			</a>
			    			&nbsp;<font color="#FFFFFF" style="font-weight: bold;">|</font>
			    			
			    			<%-- --------------------- --%>
			    			<%-- SKAT NCTS IMPORT MENU --%>
			    			<%-- --------------------- --%>
			    			<a tabindex=-1 href="skatnctsimport.do?action=doFind&sign=${user.skatSign}">
			    				&nbsp;<font
			    				<c:choose>           
		                   			<c:when test="${user.activeMenu=='SKAT_NCTS_IMPORT'}">
		                       			class="headerMenuMediumGreen"
		                   			</c:when>
		                   			<c:otherwise>   
		                       			class="headerMenuLightGreen"
		                   			</c:otherwise>
		               			</c:choose>
			    				>&nbsp;<spring:message code="systema.skat.ncts.import.label"/>&nbsp;</font>
			    			</a>
			    			<%--
			    			<font class="headerMenuLightGreen">&nbsp;<spring:message code="systema.ncts.import.label"/>&nbsp;</font>
			    			--%>	
			    			&nbsp;<font color="#FFFFFF" style="font-weight: bold;">&nbsp;|&nbsp;</font>
			    			
			    			<%-- -------------- --%>
			    			<%-- ADMIN  MENU    --%>
			    			<%-- -------------- --%>
			    			<a tabindex=-1 href="skatadmin_transport.do">
			    				&nbsp;<font
			    				<c:choose>           
		                   			<c:when test="${user.activeMenu=='SKAT_ADMIN'}">
		                       			class="headerMenuAdminOn"
		                   			</c:when>
		                   			<c:otherwise>   
		                       			class="headerMenuAdmin"
		                   			</c:otherwise>
	               			</c:choose>
	               			>&nbsp;&nbsp;<spring:message code="systema.skat.admin.label"/>&nbsp;&nbsp;</font>
			    			</a>
			    			<%--
			    			<font class="headerMenuAdmin">&nbsp;<spring:message code="systema.skat.admin.label"/>&nbsp;&nbsp;</font>
			    			 --%>
			    			 
	      				</td>		      				
	      				<td class="text11" width="50%" align="right">
	      					<img valign="bottom" src="resources/images/countryFlags/Flag_DK.gif" height="12" border="0" alt="country">
		      				&nbsp;
		      				<font class="headerMenuGreen">
			    				<img src="resources/images/appUser.gif" border="0" onClick="showPop('specialInformationAdmin');" > 
						        <span style="position:absolute; left:100px; top:150px; width:1000px; height:400px;" id="specialInformationAdmin" class="popupWithInputText"  >
						           		<div class="text11" align="left">
						           			${activeUrlRPG_Skat}
						           			<br/><br/>
						           			<button name="specialInformationButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('specialInformationAdmin');">Close</button> 
						           		</div>
						        </span>   		
			    				<font style="color:#000000" >${user.user}&nbsp;</font>${user.usrLang}</font>
			    				<font color="#FFFFFF"; style="font-weight: bold;">&nbsp;|&nbsp;&nbsp;</font>
				    			<a tabindex=-1 href="logoutSkat.do">
				    				<font class="headerMenuGreen"><img src="resources/images/home.gif" border="0">&nbsp;
				    					<font style="color:#000000;" ><spring:message code="dashboard.menu.button"/>&nbsp;</font>
				    				</font>
				    			</a>
				    			<font color="#FFFFFF"; style="font-weight: bold;">&nbsp;&nbsp;|&nbsp;</font>
				    			<font class="text12LightGreen" style="cursor:pointer;" onClick="showPop('versionInfo');">${user.versionSpring}&nbsp;</font>
			    				    <span style="position:absolute; left:800px; top:105px; width:150px; height:100px;" id="versionInfo" class="popupWithInputText"  >
						           		<div class="text11" align="left">
						           			&nbsp;<b>${user.versionEspedsg}</b>
						           			<br/><br/>
						           			&nbsp;<a href="renderLocalLog4j.do" target="_blank">log4j</a>
						           			<br/><br/><br/>
						           			<button name="versionInformationButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('versionInfo');">Close</button> 
						           		</div>
						        </span>  
				    		</td>
	      				
			        </tr>
			     </table> 
			</td>
	    </tr>
	    </c:when>
	    
	    <c:otherwise>
	    	    		<tr>
				<td height="23" class="tabThinBorderLightSlateGray" width="100%" align="left" colspan="3"> 
	    			 <table width="1100" border="0" cellspacing="1" cellpadding="1">
					 	<tr >
				    		<td class="text11" width="50%" align="left" >&nbsp;&nbsp;</td>
	      				<td class="text11" width="50%" align="right">
	      					<img valign="bottom" src="resources/images/countryFlags/Flag_DK.gif" height="12" border="0" alt="country">
		      				&nbsp;
	      					<font class="headerMenuGreen">
				    				<img src="resources/images/appUser.gif" border="0" > 
								<font style="color:#000000" >${user.user}&nbsp;</font>${user.usrLang}
				    			</font>
				    			<font color="#FFFFFF"; style="font-weight: bold;">&nbsp;|&nbsp;</font>
				    			<a tabindex=-1 href="logoutSkat.do">
				    				<font class="headerMenuGreen"><img src="resources/images/home.gif" border="0">&nbsp;
				    					<font style="color:#000000;" ><spring:message code="dashboard.menu.button"/>&nbsp;</font>
				    				</font>
				    			</a>
				    			<font color="#FFFFFF"; style="font-weight: bold;">&nbsp;&nbsp;|&nbsp;</font>
				    			<font class="text12LightGreen" style="cursor:pointer;" onClick="showPop('versionInfo');">${user.versionSpring}&nbsp;</font>
						        <span style="position:absolute; left:800px; top:105px; width:150px; height:50px;" id="versionInfo" class="popupWithInputText"  >
						           		<div class="text11" align="left">
						           			<b>${user.versionEspedsg}</b></br></br>
						           			<button name="versionInformationButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('versionInfo');">Close</button> 
						           		</div>
						        </span>  
				    		</td>
		      	        </tr>
		      	     </table> 
				</td>
		    </tr>
			<tr class="text" height="20"><td></td></tr>
		    <tr>
				<td width="100%" align="left" >
					<form action="skatgate.do" name="loginSkatForm" id="loginSkatForm" method="POST" > 
    			 		<table width="250" border="0" cellspacing="1" cellpadding="0">
    			 		<tr >
				    		<td colspan="2" class="text12" >&nbsp;
				    			<img onMouseOver="showPop('skatBehorighet_info');" onMouseOut="hidePop('skatBehorighet_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				    			<b>SKAT Brugertilladelse</b>
								<span style="position:absolute; left:300px; top:120px; width:250px; height:200px;" id="skatBehorighet_info" class="popupWithInputText"  >
					           		<div class="text11" align="left">
					           			Denne brugerkontrol er nødvendig for at bruge både
					           			<ol>
					           				<li><b>SKAT</b>&nbsp;Eksort/Import</li>
					           				<li><b>NCTS</b>&nbsp;Export/Import</li>	
										</ol>
										<br/>Kontakta din systemadministratör vid brugerproblem.
									</div>
								</span>				    		
				    		</td>
	      	        </tr>
	      	        <tr class="text" height="5"><td></td></tr>
				 	<tr >
				    		<td class="text12" >&nbsp;&nbsp;BrugerId</td>
		      				<td class="text12" >
		      					<input readonly type="text" class="inputTextReadOnly" name=userAS400 id="userAS400" size="10" maxlength="10" value='${user.userAS400}'>	
		      					<input type="hidden" name=formSubmit id="formSubmit" value="Y">	
				    		</td>
	      	        </tr>
	      	        <tr>
						<td>&nbsp;</td>
						<td align="left"><input class="inputFormLoginSubmitGreen" type="submit" value="<spring:message code="login.skat.user.submit"/>" /></td>
					</tr>
	      	     	</table>
		      	    </form>  
				</td>
		    </tr>
		    <%-- Validation Error section --%>
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
		</c:otherwise>
	    </c:choose>
	    
	    
	    <tr class="text" height="2"><td></td></tr>
		
		
		<%-- ------------------------------------
		Content after banner och header menu
		------------------------------------- --%>
		<tr>
    		<td width="100%" align="left" colspan="3"> 
    		     
     