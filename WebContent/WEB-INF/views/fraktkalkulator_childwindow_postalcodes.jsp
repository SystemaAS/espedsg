<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header =====================================-->
<jsp:include page="/WEB-INF/views/headerFraktKalkulatorChildWindows.jsp" />
<!-- =====================end header ====================================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
	specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/fraktkalkulator_childwindow.js?ver=${user.versionEspedsg}"></SCRIPT>
	
	<table width="90%" height="500px" class="tableBorderWithRoundCorners3D_RoundOnlyOnBottom" cellspacing="0" border="0" cellpadding="0">
		<tr height="5"><td colspan="2"></td></tr>
		<tr>
			<td colspan="3" class="text14Bold">&nbsp;
				<c:if test="${not empty model.direction}" >
					<c:choose>
						<c:when test="${model.direction == 'fra'}">
							Fra postnr.
						</c:when>
						<c:otherwise>
							Til postnr.
						</c:otherwise>
					</c:choose>
				</c:if>
			</td>
		</tr>
		<tr height="20"><td colspan="2"></td></tr>
		<tr>
		<td valign="top">
		<form action="fraktkalkulator_childwindow_postalcodes?action=doFind" name="searchPostalCodesForm" id="searchPostalCodesForm" method="post">
			<input type="hidden" name="direction" id="direction" value="${model.direction}">
			<%-- =====================================================  --%>
          	<%-- Here we have the search [Customer] popup window --%>
          	<%-- =====================================================  --%>
          		<%-- this container table is necessary in order to separate the datatables element and the frame above, otherwise
			 	the cosmetic frame will not follow the whole datatable grid including the search field... --%>
				<table id="containerdatatableTable" cellspacing="2" align="left">
					<tr height="5"><td></td></tr>
					
					<tr>
					<td>
						<table>
						<tr>
							<td class="text11">&nbsp;Navn</td>
							<td class="text11">&nbsp;<input type="text" class="inputText" name="st2nvn" id="st2nvn" size="20" maxlength="35" value="${model.record.st2nvn}"></td>
						
							<td class="text11">&nbsp;&nbsp;&nbsp;Kun land</td>
							<td class="text11">&nbsp;<input type="text" class="inputText" name="st2lk" id="st2lk" size="2" maxlength="2" value="${model.record.st2lk}"></td>
							
							<td class="text11">&nbsp;</td>
							<td class="text11">Vis kun</td>
							<td class="text11">
								<select name="wskunpa" id="wskunpa">
		            					<option value="">-velg-</option>
		            					<option value="A" <c:if test="${model.record.wskunpa == 'A'}"> selected </c:if> >Alfakoder</option>
		            					<option value="P" <c:if test="${model.record.wskunpa == 'P'}"> selected </c:if> >PostNr.</option>
								</select>
							</td>
							<td class="text11">&nbsp;</td>
	           				<td align="right">&nbsp;<input class="inputFormSubmit" type="submit" name="submit" value='<spring:message code="systema.fraktkalkulator.search"/>'>
		           		</tr>
		           		</table>
					</td>
					</tr>
					
					<tr><td><hr size="1" width="100%"/></td></tr>								           		
	           		<tr height="15"><td></td></tr>
					
					<tr class="text12" >
					<td class="ownScrollableSubWindowDynamicWidthHeight" style="width:600px; height:30em;">
						<%-- this is the datatables grid (content) --%>
						<c:choose>
							<c:when test="${model.direction == 'fra'}">
								<table id="postNrFromList" class="display compact cell-border">
							</c:when>
							<c:otherwise>
								<table id="postNrToList" class="display compact cell-border">
							</c:otherwise>
						</c:choose>
					
						<thead>
						<tr style="background-color:#EEEEEE">
						    <th class="text11">&nbsp;Kode/pnr&nbsp;</th>   
		                    <th class="text11">&nbsp;Navn&nbsp;</th>
		                    <th class="text11">&nbsp;Land&nbsp;</th>
		                </tr> 
		                </thead>
		                
		                <tbody>
		                <c:forEach var="record" items="${model.postalCodesList}" varStatus="counter">    
			               <c:choose>           
			                   <c:when test="${counter.count%2==0}">
			                   		<c:choose>
										<c:when test="${model.direction == 'fra'}">
											<tr class="text11 clazzDtRowPostalCodeFra" id="${record.st2kod}_${record.st2lk}@dt_postalcode_${counter.count}" >
										</c:when>
										<c:otherwise>
											<tr class="text11 clazzDtRowPostalCodeTil" id="${record.st2kod}_${record.st2lk}@dt_postalcode_${counter.count}" >
										</c:otherwise>
									</c:choose>
			                   </c:when>
			                   <c:otherwise>
			                   		<c:choose>
										<c:when test="${model.direction == 'fra'}">
					                       	<tr class="text11 clazzDtRowPostalCodeFra" id="${record.st2kod}_${record.st2lk}@dt_postalcode_${counter.count}" >
										</c:when>
										<c:otherwise>
											<tr class="text11 clazzDtRowPostalCodeTil" id="${record.st2kod}_${record.st2lk}@dt_postalcode_${counter.count}" >
										</c:otherwise>
									</c:choose>
			                   </c:otherwise>
			               </c:choose>
			               <td class="text11">&nbsp;${record.st2kod}</td>
			               <td class="text11">&nbsp;${record.st2nvn}</td>
			               <td class="text11">&nbsp;${record.st2lk}</td>
			            </tr> 
			            </c:forEach>
			            </tbody>
		            </table>
		            </td>
	           		</tr>
        			</table>
				
		</form>	
		</td>
		</tr>
	</table> 
