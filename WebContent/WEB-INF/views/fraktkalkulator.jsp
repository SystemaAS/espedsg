<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerFraktKalkulator.jsp" />
<!-- =====================end header ==========================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/fraktkalkulator.js?ver=${user.versionEspedsg}"></SCRIPT>
	
<table width="100%" cellspacing="0" border="0" cellpadding="0">

 <tr>
 <td>	
	<%-- tab container component --%>
	<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
		<tr height="2"><td></td></tr>
		<tr height="25"> 
			<td width="20%" valign="bottom" class="tab" align="center" nowrap>
				<font class="tabLink">&nbsp;<spring:message code="systema.fraktkalkulator.main.tab"/></font>
				<img valign="bottom" src="resources/images/calculator.png" height="14" width="14" border="0" alt="general list">
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="80%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>			
		</tr>
	</table>
	</td>
 </tr>
 <tr>
 	<td>
	<%-- --------------------------- --%>	
 	<%-- tab area container PRIMARY  --%>
	<%-- --------------------------- --%>
	<table width="100%" class="tabThinBorderWhite" border="0" cellspacing="0" cellpadding="0">

		<tr height="10"><td colspan="2">&nbsp;</td></tr>
		<%-- --------------- --%>
		<%-- CONTENT --%>
		<%-- --------------- --%>
		<tr>
		<td >
		<table align="center" width="98%" border="0" cellspacing="1" cellpadding="0">
		
		<%-- Left cell --%>
		<tr>
			<td width="50%"class="text12" valign="top">
				<form action="fraktkalkulator.do?action=doFind" name="searchForm" id="searchForm" method="post">
				<input type="hidden" name="applicationUser" id="applicationUser" value='${user.user}'>
				<input type="hidden" name="wsuser" id="wsuser" value='${model.user.wsuser}'>
				<input type="hidden" name="xkunfrakt" id="xkunfrakt" value='${model.user.xkunfrakt}'>
				<input type="hidden" name="overskr" id="overskr" value='${model.user.overskr}'>
				
				<table width="80%" align="left" border="0" cellspacing="0" cellpadding="0">
				 	<tr >
					 	<td >
						<table width="100%" class="dashboardFrameHeader" border="0" cellspacing="1" cellpadding="0">
					 		<tr height="15">
					 			<td class="text12White">
					 				&nbsp;&nbsp;<spring:message code="systema.fraktkalkulator.main.form.search.label.title"/>
									<img src="resources/images/find3.png" width="12px" height="12px" border="0" alt="find">
					 			</td>
					 			<td valign="bottom" align="center" class="text12White"><spring:message code="systema.fraktkalkulator.main.form.search.label.kundNr"/>&nbsp;[&nbsp;<b>${user.custNr}</b>&nbsp;]</td>
					 			<td valign="bottom" align="right" class="text12White">${model.user.overskr}&nbsp;&nbsp;</td>
			 				</tr>
			            </table>
			            </td>
		            </tr>
		            <tr >
					 	<td>
						<table width="100%" class="tableBorderWithRoundCorners3D_RoundOnlyOnBottom" border="0" cellspacing="2" cellpadding="1">
					 		<tr height="5px"><tb></tb></tr>
					 		<tr >
								<c:choose>
									<c:when test="${not empty user.intern && (user.intern=='J' || user.intern=='j')}">
							 			<td class="text12" colspan="2">&nbsp;<span title="avvknr"><spring:message code="systema.fraktkalkulator.main.form.search.label.avvknr"/></span>
							 				<a href="javascript:void(0);" target="_blank" onClick="window.open('fraktkalkulator_childwindow_customer.do?action=doInit','customerWin','top=150px,left=300px,height=600px,width=800px,scrollbars=no,status=no,location=no')">
							 					<img id="imgCustomerSearch" style="cursor:pointer;" src="resources/images/find.png" width="12px" height="12px" border="0" alt="search">
							 				</a>
							 			</td>
						 			</c:when>
						 			<c:otherwise>
										<td colspan="2">&nbsp;</td>					 			
						 			</c:otherwise>
					 			</c:choose>
					 			<td class="text12" >
					    				 <input style="cursor:pointer;" type="button" value="Postadr.bok" name="cnButton" onClick="window.open('http://epab.posten.no','')">
					    				 <img src="resources/images/find.png" width="12px" height="12px" border="0" alt="search">
					 			</td>
			 				</tr>
			 				<tr >
			 					<c:choose>
				 					<c:when test="${not empty user.intern && (user.intern=='J' || user.intern=='j')}">
						 				<td class="text12" colspan="2"><input type="text" class="inputTextMediumBlue" name="avvknr" id="avvknr" size="10" maxlength="8" value="${model.user.avvknr}"></td>
						 			</c:when>
						 			<c:otherwise>
						 				<td colspan="2">&nbsp;</td>
						 			</c:otherwise>
					 			</c:choose>
			 				</tr>
			 				<tr>
			 					<td class="text12">&nbsp;<span title="fralk/fra"><spring:message code="systema.fraktkalkulator.main.form.search.label.fra"/></span>
			 						<a href="javascript:void(0);" target="_blank" onClick="window.open('fraktkalkulator_childwindow_postalcodes.do?action=doInit&direction=fra&st2lk=${model.user.fralk}','postalWin','top=150px,left=320px,height=600px,width=800px,scrollbars=no,status=no,location=no')">
					 					&nbsp;<img align="bottom" src="resources/images/find.png" width="12px" height="12px" border="0" alt="search">
						            	</a>
			 					</td>
					 			<td class="text12">&nbsp;<span title="tillk/til"><spring:message code="systema.fraktkalkulator.main.form.search.label.til"/></</span>
			 						<a href="javascript:void(0);" target="_blank" onClick="window.open('fraktkalkulator_childwindow_postalcodes.do?action=doInit&direction=til&st2lk=${model.user.tillk}','postalWin','top=150px,left=340px,height=600px,width=800px,scrollbars=no,status=no,location=no')">
					 					&nbsp;<img align="bottom" src="resources/images/find.png" width="12px" height="12px" border="0" alt="search">
									</a>						            	
					 			</td>
					 			<td class="text12">&nbsp;<span title="vkt"><spring:message code="systema.fraktkalkulator.main.form.search.label.vkt"/></</span></td>
			 				</tr>
			 				<tr >
					 			<td class="text12">
					 				<input type="text" class="inputTextMediumBlue" name="fralk" id="fralk" size="2" maxlength="2" value="${model.user.fralk}">
					 				&nbsp;<input type="text" class="inputTextMediumBlue" name="fra" id="fra" size="5" maxlength="5" value="${model.user.fra}">
					 			</td>
					 			<td class="text12">
					 				<input type="text" class="inputTextMediumBlue" name="tillk" id="tillk" size="2" maxlength="2" value="${model.user.tillk}">
					 				&nbsp;<input type="text" class="inputTextMediumBlue" name="til" id="til" size="5" maxlength="5" value="${model.user.til}">
				 				</td>
				 				<td>
					 				<input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue" name="vkt" id="vkt" size="5" maxlength="5" value="${model.user.vkt}">
					 			</td>
			 				</tr>
			 				
							<%-- Validation errors --%>
							<spring:hasBindErrors name="record"> <%-- name must equal the command object name in the Controller --%>
							<tr>
								<td colspan="20">
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
			 				
			 				<tr height="10"><td></td></tr>
			 				<tr><td colspan="10"><hr size="1" width="100%" 	/></td></tr>
			 				<tr>
					 			<td class="text12Bold" colspan="2">&nbsp;<spring:message code="systema.fraktkalkulator.main.form.search.label.tillegsparam"/></</td>
			 				</tr>
			 				<tr height="3"><td></td></tr>
			 				<tr>
			 					<td class="text12">&nbsp;<span title="ant"><spring:message code="systema.fraktkalkulator.main.form.search.label.tillegsparam.ant"/></</span></td>
					 			<td class="text12">&nbsp;<span title="m3"><spring:message code="systema.fraktkalkulator.main.form.search.label.tillegsparam.m3"/></</span></td>
					 			<td class="text12">&nbsp;<span title="lm"><spring:message code="systema.fraktkalkulator.main.form.search.label.tillegsparam.lm"/></</span></td>
			 				</tr>
			 				<tr >
					 			<td class="text12">
					 				<input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue" name="ant" id="ant" size="5" maxlength="5" value="${model.user.ant}">
					 			</td>
					 			<td class="text12">
					 				<input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue" name="m3" id="m3" size="5" maxlength="5" value="${model.user.m3}">
				 				</td>
				 				<td class="text12">
					 				<input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue" name="lm" id="lm" size="5" maxlength="5" value="${model.user.lm}">
					 			</td>
			 				</tr>
			 				<%-- alternative parameters depending on xkunfrakt --%>
			 				<c:if test="${model.user.xkunfrakt=='N' || model.user.xkunfrakt=='n'}">
			 					<tr height="5"><td></td></tr>
				 				<tr >
						 			<td class="text12">
						 				<span title="farlig"><spring:message code="systema.fraktkalkulator.main.form.search.label.tillegsparam.farlig"/></</span>
						 				<input type="checkbox" name="farlig" id="farlig" value="1" <c:if test="${model.user.farlig == 'J'}"> checked </c:if> >
						 			</td>
						 			<td class="text12">
						 				<span title="varme"><spring:message code="systema.fraktkalkulator.main.form.search.label.tillegsparam.varme"/></</span>
						 				<input type="checkbox" name="varme" id="varme" value="1" <c:if test="${model.user.varme == 'J'}"> checked </c:if> >
					 				</td>
					 				<td class="text12">
					 					<span title="lengde"><spring:message code="systema.fraktkalkulator.main.form.search.label.tillegsparam.lengde"/></</span>
						 				<input type="checkbox" name="lengde" id="lengde" value="1" <c:if test="${model.user.lengde == 'J'}"> checked </c:if> >
						 			</td>
				 				</tr>
			 				</c:if>
			 				<tr height="5"><td></td></tr>
			 				<tr>
			 					<td class="text12">&nbsp;<span title="prod"><spring:message code="systema.fraktkalkulator.main.form.search.label.tillegsparam.prod"/></span></td>
					 		</tr>
			 				<tr>
			 					<td class="text12" colspan="2">
						    			<select name="prod" id="prod">
		            						<option value="">-velg-</option>
			 				  				<c:forEach var="record" items="${model.prodList}" >
                       	 						<option value="${record.prodkod}"<c:if test="${model.user.prod == record.prodkod}"> selected </c:if> >${record.prodtxt}</option>
											</c:forEach> 
									</select>
					    			</td>
				    			</tr>
			 				<%-- alternative parameters depending on xkunfrakt --%>
			 				<c:if test="${model.user.xkunfrakt=='N' || model.user.xkunfrakt=='n'}">
			 					<tr height="10"><td></td></tr>
			 					<tr><td colspan="5"><hr size="1" width="100%" 	/></td></tr>
			 					<tr>
			 						<td class="text12" colspan="3">&nbsp;<span title="wsavd"><spring:message code="systema.fraktkalkulator.main.form.search.label.tillegsparam.tjenType"/></span></td>
					 			</tr>
				 				<tr >
						 			<td class="text12" colspan="3">
						    			<select name="wsavd" id="wsavd">
			            					<option value="">-velg-</option>
			 				  			<c:forEach var="record" items="${model.tjenesteList}" >
                       	 					<option value="${record.wsavd1}"<c:if test="${model.user.wsavd == record.wsavd1}"> selected </c:if> >${record.wsavd2}</option>
										</c:forEach> 
									</select>
					    				</td>
				 				</tr>
				 				<tr height="5"><td></td></tr>
				 				<tr>
			 						<td class="text12" colspan="2">&nbsp;<span title="wsot"><spring:message code="systema.fraktkalkulator.main.form.search.label.tillegsparam.oppType"/></span></td>
			 						<td class="text12" >&nbsp;<span title="frankod"><spring:message code="systema.fraktkalkulator.main.form.search.label.tillegsparam.levvilkor"/></span></td>
			 						
					 			</tr>
				 				<tr >
						 			<td class="text12" colspan="2">
							    			<select name="wsot" id="wsot">
							    				<option value="">-velg-</option>
				            					<c:forEach var="record" items="${model.oppdragList}" >
	                       	 					<option value="${record.wsot1}"<c:if test="${model.user.wsot == record.wsot1}"> selected </c:if> >${record.wsot2}</option>
											</c:forEach>
										</select>
					    				</td>
					    				<td class="text12">
						    				<select name="frankod" id="frankod">
				            					<option value="">-velg-</option>
				 				  			<c:forEach var="record" items="${model.incotermsList}" >
	                       	 					<option value="${record.frankod}"<c:if test="${model.user.frankod == record.frankod}"> selected </c:if> >${record.frantxt}</option>
											</c:forEach>
										</select>
					    				</td>
				 				</tr>
				 				<tr height="10"><td></td></tr>
			 				</c:if>
			 				<%-- SUBMIT button --%>
			 				<tr>
			 					<td>&nbsp;</td>
			 					<td>&nbsp;</td>
			 					<td align="right" class="text12" valign="top">
			 						<input class="inputFormSubmit" type="submit" name="submit" value='<spring:message code="systema.fraktkalkulator.search"/>'>
			 						&nbsp;&nbsp;&nbsp;&nbsp;
			 					</td>
		 					</tr>
		 					<tr height="5"><td></td></tr>
			            </table>
			            </td>
		            </tr>
	            </table>
	            </form>
            </td>
            <td width="50%"class="text12" valign="top">
            		<form name="resultForm" id="resultForm" method="post">
				<table width="90%" align="left" border="0" cellspacing="0" cellpadding="0">
				 	<tr >
					 	<td >
						<table width="100%" class="dashboardFrameHeader" border="0" cellspacing="1" cellpadding="0">
					 		<tr height="15">
					 			<td class="text12White">
					 			&nbsp;&nbsp;<spring:message code="systema.fraktkalkulator.main.form.result.label.title"/>&nbsp;
					 			</td>
			 				</tr>
			            </table>
			            </td>
		            </tr>
		            
		            <tr >
					 	<td>
						<table width="100%" class="formFrame" border="0" cellspacing="2" cellpadding="1">
							<%-- Hide this section until further instructions
					 		<tr >
					 			<td class="text12" colspan="8">&nbsp;<span title="avvknr"><spring:message code="systema.fraktkalkulator.main.form.result.label.avvknr"/></span></td>
			 				</tr>
			 				<tr >
					 			<td class="text12" colspan="8"><input readonly type="text" class="inputTextMediumBlue" name="avvknr" id="avvknr" size="8" maxlength="8" value="${model.container.avvknr}"></td>
			 				</tr>
			 				<tr>
			 					<td class="text12">&nbsp;<span title="fralk/fra"><spring:message code="systema.fraktkalkulator.main.form.result.label.fra"/></span></td>
					 			<td class="text12">&nbsp;<span title="tillk/til"><spring:message code="systema.fraktkalkulator.main.form.result.label.til"/></span></td>
					 			<td class="text12">&nbsp;<span title="vkt"><spring:message code="systema.fraktkalkulator.main.form.result.label.vkt"/></span></td>
			 				</tr>
			 				<tr >
					 			<td class="text12">
					 				<input readonly type="text" class="inputTextMediumBlueGrayReadOnly" name="fralk" id="fralk" size="2" maxlength="2" value="${model.container.fralk}">
					 				&nbsp;<input readonly type="text" class="inputTextMediumBlueGrayReadOnly" name="fra" id="fra" size="5" maxlength="5" value="${model.container.fra}">
					 			</td>
					 			<td class="text12"><input readonly type="text" class="inputTextMediumBlueGrayReadOnly" name="tillk" id="tillk" size="2" maxlength="2" value="${model.container.tillk}">
					 				<input readonly type="text" class="inputTextMediumBlueGrayReadOnly" name="til" id="til" size="5" maxlength="5" value="${model.container.til}">
				 				</td>
				 				<td>
					 				<input readonly type="text" class="inputTextMediumBlueGrayReadOnly" name="vkt" id="vkt" size="5" maxlength="5" value="${model.container.vkt}">
					 			</td>
			 				</tr>
			 				 --%>
			 				<tr height="10"><td></td></tr>
			 				<tr>
			 					<td class="text11MediumBlue">&nbsp;<b><spring:message code="systema.fraktkalkulator.main.form.result.label.fraktvikt"/>:</b>&nbsp;${model.container.fbv}</td>
			 					<td colspan="2" class="text11MediumBlue">&nbsp;<b><spring:message code="systema.fraktkalkulator.main.form.result.label.avgangsdagar"/>:</b>&nbsp;${model.container.avdg}</td>
			 					<td colspan="2" class="text11MediumBlue">&nbsp;<b><spring:message code="systema.fraktkalkulator.main.form.result.label.framforingstid"/>:</b>&nbsp;${model.container.frdg}</td>
			 				</tr>
		 					
			 				<tr><td colspan="10"><hr size="1" width="100%"/></td></tr>
			 				
			 				<%--Hide this section until further instructions
			 				<tr>
					 			<td class="text12Bold" colspan="2">&nbsp;<spring:message code="systema.fraktkalkulator.main.form.result.label.tillegsparam"/></td>
			 				</tr>
			 				<tr height="3"><td></td></tr>
			 				<tr>
			 					<td class="text12">&nbsp;<span title="ant(readonly)"><spring:message code="systema.fraktkalkulator.main.form.result.label.tillegsparam.ant"/></span></td>
					 			<td class="text12">&nbsp;<span title="m3(readonly)"><spring:message code="systema.fraktkalkulator.main.form.result.label.tillegsparam.m3"/></span></td>
					 			<td class="text12">&nbsp;<span title="lm(readonly)"><spring:message code="systema.fraktkalkulator.main.form.result.label.tillegsparam.lm"/></span></td>
			 				</tr>
			 				<tr >
					 			<td class="text12">
					 				<input readonly type="text" class="inputTextMediumBlueGrayReadOnly" name="ant" id="ant" size="5" maxlength="5" value="${model.container.ant}">
					 			</td>
					 			<td class="text12">
					 				<input readonly type="text" class="inputTextMediumBlueGrayReadOnly" name="m3" id="m3" size="5" maxlength="5" value="${model.container.m3}">
				 				</td>
				 				<td class="text12">
					 				<input readonly type="text" class="inputTextMediumBlueGrayReadOnly" name="lm" id="lm" size="5" maxlength="5" value="${model.container.lm}">
					 			</td>
			 				</tr>
			 				<%-- alternative parameters depending on xkunfrakt
			 				<c:if test="${model.user.xkunfrakt=='N' || model.user.xkunfrakt=='n'}">
			 					<tr height="5"><td></td></tr>
				 				<tr >
						 			<td class="text12">
						 				<span title="farlig"><spring:message code="systema.fraktkalkulator.main.form.result.label.tillegsparam.farlig"/></</span>
						 				<input readonly type="checkbox" name="farlig" id="farlig" value="" <c:if test="${model.container.farlig == 'J'}"> checked </c:if> >
						 			</td>
						 			<td class="text12">
						 				<span title="varme"><spring:message code="systema.fraktkalkulator.main.form.result.label.tillegsparam.varme"/></</span>
						 				<input readonly type="checkbox" name="varme" id="varme" value="" <c:if test="${model.container.varme == 'J'}"> checked </c:if> >
					 				</td>
					 				<td class="text12">
					 					<span title="lengde"><spring:message code="systema.fraktkalkulator.main.form.result.label.tillegsparam.lengde"/></</span>
						 				<input readonly type="checkbox" name="lengde" id="lengde" value="" <c:if test="${model.container.lengde == 'J'}"> checked </c:if> >
						 			</td>
				 				</tr>
			 				</c:if>
			 				 
			 				<tr height="5"><td></td></tr>
			 				<tr>
			 					<td class="text12">&nbsp;<span title="prod"><spring:message code="systema.fraktkalkulator.main.form.result.label.tillegsparam.prod"/></span></td>
					 		</tr>
			 				<tr>
			 					<td class="text12" colspan="5">
						    			<input readonly type="text" class="inputTextMediumBlue" name="prod" id="prod" size="5" value="${model.container.prod}">
					    			</td>
				    			</tr>
				    			 --%>
				    		<tr height="2"><td></td></tr>
			 				<c:if test="${not empty model.container.oljvaltxt || not empty model.container.netto}">
				 				<tr>
				 					<td colspan="10">
				 					<table width="100%" >
				 						<c:if test="${not empty model.container.oljvaltxt}">
						 					<tr class="tableRow" >
							 					<td class="tableCellFirst"><spring:message code="systema.fraktkalkulator.main.form.result.label.xkunfrakt.column.petroleumCharge"/></td>
								    			<td class="tableCell">${model.container.oljvaltxt}</td>
							    			</tr>
						    			</c:if>
						    			<c:if test="${model.container.xkunfrakt == 'J' || model.container.xkunfrakt == 'j' }">
							    			<tr class="tableRow" >
						 						<td class="tableCellFirst"><spring:message code="systema.fraktkalkulator.main.form.result.label.xkunfrakt.column.prices"/></td>
								    			<td class="tableCell"><b><spring:message code="systema.fraktkalkulator.main.form.result.label.xkunfrakt.column.price"/></b>&nbsp;${model.container.netto}
								    				&nbsp;&nbsp;<b><spring:message code="systema.fraktkalkulator.main.form.result.label.xkunfrakt.column.grossPrice"/></b>:&nbsp;${model.container.brutto}
								    			</td>
							    			</tr>
							    			<tr class="tableRow" >
						 						<td class="tableCellFirst">&nbsp;</td>
								    			<td class="tableCell"><font class="text11MediumBlue">Kun frakt ekslusive evt. tillegg</font></td>
							    			</tr>
						    			</c:if>
					    			</table>
					    			</td>
				    			</tr>
				    			<tr height="2"><td></td></tr>
			    			</c:if>
				    			
			 				<c:if test="${not empty model.container.pricecalclist}">
			 					<tr>
					 				<td colspan="10">
									<table width="100%" cellspacing="0" border="0" cellpadding="0">
										 <tr class="tableHeaderField" height="20" valign="left">
										 	<td class="tableHeaderFieldFirst">&nbsp;<spring:message code="systema.fraktkalkulator.main.form.result.label.column.gebyrType"/>&nbsp;</td> 
						                    <td class="tableHeaderField">&nbsp;<spring:message code="systema.fraktkalkulator.main.form.result.label.column.text"/>&nbsp;</td> 
						                    <td class="tableHeaderField">&nbsp;<spring:message code="systema.fraktkalkulator.main.form.result.label.column.valuta"/>&nbsp;</td>
						                    <td align="right" class="tableHeaderField">&nbsp;<spring:message code="systema.fraktkalkulator.main.form.result.label.column.belop"/>&nbsp;</td>
						                    <td align="right" class="tableHeaderField">&nbsp;<spring:message code="systema.fraktkalkulator.main.form.result.label.column.belopNok"/>&nbsp;</td> 
							             </tr> 
						               	 <c:forEach var="record" items="${model.container.pricecalclist}" varStatus="counter">    
							               <c:choose>           
							                   <c:when test="${counter.count%2==0}">
							                       <tr class="tableRow" height="20" >
							                   </c:when>
							                   <c:otherwise>   
							                       <tr class="tableOddRow" height="20" >
							                   </c:otherwise>
							               </c:choose>
							               <td class="tableCellFirst">&nbsp;${record.wsgkd}</td>
							               <td class="tableCell">&nbsp;${record.wsgtxt}</td>
							               <td class="tableCell">&nbsp;${record.wsval}</td>
							               <td class="tableCell" align="right" >${record.wsbel}&nbsp;</td>
							               <td class="tableCell" align="right" >${record.wsbeln}&nbsp;</td>
							               </tr> 
							               <c:set var="myCounter" value="${counter.count}" scope="request" />
							               <c:set var="myTotal" value="${record.wstotn}" scope="request" />
							             </c:forEach>
							             <c:if test="${myCounter>=1}">
								            <tr class="tableRow" height="20" valign="left">
							                    <td class="tableCellFirst">&nbsp;<b><spring:message code="systema.fraktkalkulator.main.form.result.label.column.total"/></b>&nbsp;</td> 
							                    <td align="right" class="tableCell">&nbsp;</td>
							                    <td align="right" class="tableCell">&nbsp;</td>
							                    <td align="right" class="tableCell">&nbsp;</td>
							                    <td align="right" class="tableCell"><b>${myTotal}</b>&nbsp;</td> 
							               	</tr>
						               	</c:if>
					             	</table>
					 				</td>
				 				</tr>
			 				</c:if>
			            </table>
			            </td>
		            </tr>
	            </table>
	            </form>
            </td>
		</tr>
		<tr height="20"><td colspan="2">&nbsp;</td></tr>

	</table> 
	</td>
 </tr>
 </table>
 </td>
 </tr>
