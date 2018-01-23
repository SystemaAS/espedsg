<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header =====================================-->
<jsp:include page="/WEB-INF/views/headerTrorChildWindows.jsp" />
<!-- =====================end header ====================================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
	specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tror_childwindow_freightbill_merke_kolli.js?ver=${user.versionEspedsg}"></SCRIPT>
	
	<table width="90%" class="tableBorderWithRoundCorners3D_RoundOnlyOnBottom" cellspacing="0" border="0" cellpadding="0">
		<tr height="5"><td colspan="2"></td></tr>
		<tr>
			<td colspan="3" class="text14Bold">&nbsp;&nbsp;&nbsp;
				<img title="search" valign="bottom" src="resources/images/search.gif" width="24px" height="24px" border="0" alt="search">
				Merke vedlikehold
			</td>
		</tr>
		<tr height="20"><td colspan="2"></td></tr>
		<tr>
		<td valign="top">
			
				<form name="trorMerkeKolliForm" id="trorMerkeKolliForm" action="TODOtror_mainorder_childwindow_freightbill_merke_kolli.do?action=doFind" method="post">
				<input type="hidden" name="ctype" id="ctype" value="${model.ctype}">
				<input type="hidden" name="kftype" id="kftype" value="${model.kftype}">
					
				<%-- ============================================  --%>
	          	<%-- Here we have the search  popup window --%>
	          	<%-- ============================================  --%>
          		<%-- this container table is necessary in order to separate the datatables element and the frame above, otherwise
			 	the cosmetic frame will not follow the whole datatable grid including the search field... --%>
				<table id="containerdatatableTable" cellspacing="2" align="left" width="100%">
				
					
					<tr class="text12" >
					<td class="ownScrollableSubWindowDynamicWidthHeight" width="90%" style="height:30em;">
					<%-- this is the datatables grid (content)--%>
					<table id="list" class="display compact cell-border" width="100%">
						<thead>
						<tr style="background-color:#EEEEEE">
							<th width="5%" class="text11">&nbsp;Kollinr</th>   
		                    <th class="text11">&nbsp;Merke/KolliID</th>
		                    <th class="text11">&nbsp;Inn</th>
		                    <th class="text11">&nbsp;Ut</th>
		                    <th class="text11">&nbsp;Levert</th>
		                    <th align="right" class="text11">&nbsp;Vekt</th>
		                    <th align="right" class="text11">&nbsp;Lengde</th>
		                    <th class="text11">&nbsp;Beskrivelse</th>
		                   
		                </tr> 
		                </thead>
		                
		                <tbody>
		                <c:forEach var="record" items="${model.list}" varStatus="counter">    
			               <tr class="text11" >
			               <td width="2%" class="text11MediumBlue" style="cursor:pointer;" id="avd_${record.fmavd}@opd_${record.fmopd}@fbnr_${record.fmfbnr}@mknr_${record.fmmknr}@counter_${counter.count}">
			               	 	${record.fmmknr}
			               </td>
			               <td class="text11" >&nbsp;${record.fmmrk1}</td>
			               <td class="text11" >&nbsp;${record.fmiter}</td> 
			               <td class="text11" >&nbsp;${record.fmuter}</td>
			               <td class="text11" >&nbsp;${record.fmleve}</td> 
			               <td align="right" class="text11" >&nbsp;${record.fmvkt}</td>
			               <td align="right" class="text11" >&nbsp;${record.fmlen}</td> 
			               <td class="text11" >&nbsp;</td>
			                      
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
