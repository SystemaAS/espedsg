<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerEbooking.jsp" />
<!-- =====================end header ==========================-->
	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/ebookingglobal_edit.js?ver=${user.versionEspedsg}"></SCRIPT>	
	<SCRIPT type="text/javascript" src="resources/js/ebooking_mainorderlist.js?ver=${user.versionEspedsg}"></SCRIPT>
	<%-- for dialog popup --%>
	<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
	
	<style type = "text/css">
	.ui-dialog{font-size:10pt;}
	.ui-datepicker { font-size:9pt;}
	</style>
	

<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
	<tr>
	<td>
	<%-- tab container component --%>
	<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
		<tr height="2"><td></td></tr>
		<tr height="25"> 
			<td width="20%" valign="bottom" class="tab" align="center" nowrap>
				<img style="vertical-align:middle;" src="resources/images/bulletGreen.png" width="6px" height="6px" border="0" alt="open orders">
				<font class="tabLink">&nbsp;<spring:message code="systema.ebooking.orderlist.tab"/></font>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="20%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a id="createNewOrderTabIdLink" style="display:block;" runat="server" href="#">
					<img style="vertical-align:middle;" src="resources/images/add.png" width="12px" height="12px" border="0" alt="create new">
					<font class="tabDisabledLink"><spring:message code="systema.ebooking.createnew.order.tab"/></font>
				</a>
			</td>
			<td width="60%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>	
		</tr>
	</table>
	</td>
	</tr>
	
	
	
	<tr>
	<td>
		<table width="100%" class="tabThinBorderWhiteWithSideBorders" border="0" cellspacing="0" cellpadding="0">
			<tr height="10"><td></td></tr>
			<%-- Should be set-on for the whole solution. This here was just a prototype
 	        <tr>
 	        <td height="2px" valign="top" align="right"><font class="text11MediumBlue">Stretch workspace</font><input tabindex="-1" type="checkbox" id="checkBoxVisibility">&nbsp;&nbsp;</td>
 	        </tr>
 	        --%>
		</table>		
	</td>
	</tr>
	
	
	<%-- Validation errors --%>
	<spring:hasBindErrors name="record"> <%-- name must equal the command object name in the Controller --%>
	<tr>
		<td>
           	<table width="100%" align="left" border="0" cellspacing="0" cellpadding="0">
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
	
	<%-- -------------------------- --%>
	<%-- Validation errors on model --%>
	<%-- -------------------------- --%>
	<c:if test="${not empty model.errorMessage}">
		<tr>
		<td>
           	<table class="tabThinBorderWhiteWithSideBorders" width="100%" align="left" border="0" cellspacing="0" cellpadding="0">
           	<tr>
			<td valign="bottom" class="textError">					
	            <ul>
	            	<li >${model.errorMessage}</li>
	            </ul>
			</td>
			</tr>
			</table>
		</td>
		</tr>		
	</c:if>
	
	
		<tr>
		<td>
			<%-- this table wrapper is necessary to apply the css class with the thin border --%>
			<table id="wrapperTable" class="tabThinBorderWhite" width="100%" cellspacing="1">
			<%-- OPEN ORDERS --%>
			<%-- search filter component --%>
			<tr>
				<td>
					
				<%-- this container table is necessary in order to separate the datatables element and the frame above, otherwise
					 the cosmetic frame will not follow the whole datatable grid including the search field... --%>
				<table id="containerdatatableTable" width="100%" cellspacing="2" align="left" >
				<tr>
   				    <form name="searchForm" id="searchForm" action="ebooking_mainorderlist.do?action=doFind" method="post" >
					<input type="hidden" name="userAvd" id="userAvd" value=''>
			    	<td> 
			    	<table width="100%">
			    		<tr> 
			    		<td>	
			        		&nbsp;<font title="orderNr" class="text12"><spring:message code="systema.ebooking.orders.open.list.search.label.ordernr"/></font>
			        		&nbsp;<input type="text" class="inputText" name="opd" id="opd" size="10" maxlength="15" value='${searchFilter.orderNr}'>
				        </td>
				        <td>	
			        		&nbsp;<font title="date" class="text12"><spring:message code="systema.ebooking.orders.open.list.search.label.date"/></font>
				        	&nbsp;<input type="text" class="inputText" name="date" id="date" size="10" maxlength="15" value='${searchFilter.date}'>
				        </td>
				        <td>	
			        		&nbsp;<font title="fromDate/fromDate" class="text12"></font>
				        	&nbsp;<input type="text" class="inputText" name="fromDate" id="fromDate" size="9" maxlength="8" value='${searchFilter.fromDate}'>
				        	-<input type="text" class="inputText" name="toDate" id="toDate" size="9" maxlength="8" value='${searchFilter.toDate}'>
				        </td>
				        <td>	
			        		&nbsp;<font title="sender" class="text12"><spring:message code="systema.ebooking.orders.open.list.search.label.sender"/></font>
				        	&nbsp;<input type="text" class="inputText" name="todo" id="todo" size="10" maxlength="15" value='${searchFilter.sender}'>
				        </td>
			        	<td>	
			        		&nbsp;<font title="receiver" class="text12"><spring:message code="systema.ebooking.orders.open.list.search.label.receiver"/></font>
				        	&nbsp;<input type="text" class="inputText" name="todo" id="todo" size="10" maxlength="15" value='${searchFilter.receiver}'>
				        </td>
			        	<td>	
			        		&nbsp;<font title="from" class="text12"><spring:message code="systema.ebooking.orders.open.list.search.label.from"/></font>
				        	&nbsp;<input type="text" class="inputText" name="from" id="from" size="9" maxlength="8" value='${searchFilter.from}'>
				        </td>
				        <td>	
				        	&nbsp;<font title="to" class="text12"><spring:message code="systema.ebooking.orders.open.list.search.label.to"/></font>
				        	&nbsp;<input type="text" class="inputText" name="to" id="to" size="9" maxlength="8" value='${searchFilter.to}'>&nbsp;&nbsp;&nbsp;
				        </td>
				        <td>	
				        	<input class="inputFormSubmit" type="submit" name="submit" id="submit" value='<spring:message code="systema.ebooking.search"/>'>
				        </td>   
				        </tr>
				    </table>    
					</td>
					</form>
				</tr>
				<%--
				<c:if test="${not empty model.containerOpenOrders.maxWarning}">
					<tr>	
						<td class="listMaxLimitWarning">
						<img style="vertical-align:bottom;" src="resources/images/redFlag.png" width="16" height="16" border="0" alt="Warning">
						${model.containerOpenOrders.maxWarning}</td>
					</tr>
				</c:if>
				--%>
				 
				<tr height="5"><td></td></tr>
				<tr>
				<td >
				<table style="width:100%;" id="openOrders" class="display compact cell-border" cellspacing="0" >
					<thead >
					<tr style="background-color:#BCC6CC">
						<th class="text12"><spring:message code="systema.ebooking.orders.open.list.search.label.ordernr"/></th>   
	                    <th class="text12"><spring:message code="systema.ebooking.orders.open.list.search.label.date"/></th>
	                    <th class="text12"><spring:message code="systema.ebooking.orders.open.list.search.label.sender"/></th>
	                    <th class="text12"><spring:message code="systema.ebooking.orders.open.list.search.label.receiver"/></th>
	                    <th class="text12"><spring:message code="systema.ebooking.orders.open.list.search.label.antall"/></th>
	                    <th class="text12"><spring:message code="systema.ebooking.orders.open.list.search.label.weight"/></th>
	                    <th class="text12"><spring:message code="systema.ebooking.orders.open.list.search.label.m3"/></th>
	                    <th class="text12"><spring:message code="systema.ebooking.orders.open.list.search.label.from"/></th>
	                    <th class="text12"><spring:message code="systema.ebooking.orders.open.list.search.label.to"/></th>
	                    <th class="text12"><spring:message code="systema.ebooking.orders.open.list.search.label.printdoc"/></th>
	                    <th class="text12"><spring:message code="systema.ebooking.orders.open.list.search.label.getlist"/></th>
	                    <th class="text12"><spring:message code="systema.ebooking.orders.open.list.search.label.transmit"/></th>
	                    <th class="text12"><spring:message code="systema.ebooking.orders.open.list.search.label.delete"/></th>
	                    
	                </tr> 
	                </thead>
	                
	                
	                <tbody >
		            <c:forEach items="${listOpenOrders}" var="record" varStatus="counter">  
		            <tr class="tex11" >
		               <td class="text11MediumBlue" id="opd_${record.hereff}@${counter.count}" >
			           		<div id="opd${record.hereff}_linkcontainer${counter.count}" >
			           		<a style="cursor:pointer;" id="@opd_${record.hereff}@alinkOpenOrdersListId_${counter.count}"
			           			onClick="setBlockUI(this);" href="ebooking_mainorder.do?action=doFetch&heunik=${record.unik}&hereff=${record.hereff}&sysg=&sitll=&syst=&sydt=">
    		    				<img title="Update" style="vertical-align:bottom;" src="resources/images/update.gif" border="0" alt="update">
    		    				<font class="text11MediumBlue">${record.hereff}</font>
    		    			</a>
    		    			</div>
			           </td>
			           <td align="center" class="text11MediumBlue">&nbsp;${record.hedtop}</td>
		               <td align="left" class="text11MediumBlue">&nbsp;${record.henas}</td>
		               <td align="left" class="text11MediumBlue">&nbsp;${record.henak}</td>
		               <td align="center" class="text11MediumBlue">&nbsp;${record.hent}</td>
		               <td align="center" class="text11MediumBlue">&nbsp;${record.hevkt}</td>
		               <td align="center" class="text11MediumBlue">&nbsp;${record.hem3}</td>
		               <td align="center" class="text11MediumBlue">&nbsp;${record.xfralk}${record.hesdf}</td>
		               <td align="center" class="text11MediumBlue">&nbsp;${record.xtillk}${record.hesdt}</td>
		               <td align="center" class="text11MediumBlue">&nbsp;${Xrecord.todo}</td>
		               <td align="center" class="text11MediumBlue">&nbsp;${Xrecord.todo}</td>
		               <td align="center" class="text11MediumBlue">&nbsp;${Xrecord.todo}</td>
		               
		               <td align="center" class="text11MediumBlue">
            		  	 	<a sytle="cursor:pointer;" id="hereff_${record.hereff}@heunik_${record.unik}" title="delete" onClick="doPermanentlyDeleteOrder(this)" tabindex=-1>
			               		<img src="resources/images/delete.gif" border="0" alt="remove">
			               	</a>&nbsp;
					   </td>
		            </tr> 
		            </c:forEach>
		            </tbody>
		            
	            </table>
				</td>	
				</tr>
				
				<tr>
            		<td align="right" class="text12">
            		<table >
					<tr>
						<td>	
							<a href="EbookingOpenOrdersListExcelView.do" target="_new">
			                		<img valign="bottom" id="openOrdersListExcel" src="resources/images/excel.gif" width="14" height="14" border="0" alt="excel">
			                		<font class="text12MediumBlue">&nbsp;Excel</font>
			 	        		</a>
			 	        		&nbsp;
		 	        		</td>
		 	        		<td class="text12" width="15px">&nbsp;</td>
	 	        		</tr>
	 	        		</table>
			 		</td>
	         	</tr>
				</table>
				</td>
			</tr>
			
			<tr height="5"><td></td></tr>
			
			</table>
		</td>
		</tr>
		<%-- Pop-up window --%>
		<tr>
		<td>
			<div id="dialogCreateNewOrder" title="Dialog">
				<form  action="ebooking_mainorder.do" name="createNewOrderForm" id="createNewOrderForm" method="post">
				 	<input type="hidden" name="actionGS" id="actionGS" value='doUpdate'/>
					<input type="hidden" name="applicationUser" id="applicationUser" value='${user.user}'>
					
					<p class="text12" >&nbsp;Velg type av Transportoppdrag.</p>
										
					<table>
						<tr>
							<td class="text12MediumBlue">Type&nbsp;
								<select name="selectedType" id="selectedType">
									<c:forEach var="record" items="${model.containerOpenOrders.orderTypesNew}" >
	                             	 	<option value="${record.newAvd}@${record.newModul}@${record.newModul2}@${record.newLandKode}@${record.newSideSK}@${record.newText}" >${record.newText}</option>
									</c:forEach>
									
									<%--
									<option value="0000HD  STransportoppdrag Innland/Utgående" >Transportoppdrag Innland/Utgående</option>
									<option value="0000HEDKSTransportoppdrag Eksport,Danmark Test" >Transportoppdrag Eksport-Danmakr Test</option>
									<option value="0000HI  KTransportoppdrag Import" >Transportoppdrag Import</option>
									 --%>									  
								</select>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</td>
		</tr>
		
</table>	
		
<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->

