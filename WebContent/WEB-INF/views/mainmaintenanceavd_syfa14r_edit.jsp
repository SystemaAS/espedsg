<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerMainMaintenance.jsp" />
<!-- =====================end header ==========================-->
	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/mainmaintenanceavd_syfa14r_edit.js?ver=${user.versionEspedsg}"></SCRIPT>	
	
	<style type = "text/css">
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
					<td width="14%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a id="alinkMainMaintGate" tabindex=-1 style="display:block;" href="mainmaintenancegate.do">
						<font class="tabDisabledLink">&nbsp;Vedlikehold</font>
						<img style="vertical-align: middle;"  src="resources/images/list.gif" border="0" alt="general list">
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="14%" valign="bottom" class="tabDisabled" align="center">
						<a id="alinkMainMaintAvdGate" onClick="setBlockUI(this);" href="mainmaintenanceavdgate.do?id=${model.dbTable}">
							<font class="tabDisabledLink">&nbsp;Avdelinger</font>&nbsp;						
							<img style="vertical-align: middle;"  src="resources/images/list.gif" border="0" alt="avd. gate list">
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="14%" valign="bottom" class="tab" align="center">
						<font class="tabLink">&nbsp;Gen.Avd</font>&nbsp;
						<font class="text11MediumBlue">(${model.avd})</font>
						<a id="alinkMainMaintAvdSyfa14r" onClick="setBlockUI(this);" href="mainmaintenanceavd_syfa14r.do">
							<img style="vertical-align: middle;"  src="resources/images/list.gif" border="0" alt="avd. general list">
						</a>
					</td>
					
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="14%" valign="bottom" class="tabDisabled" align="center">
						<a id="alinkMainMaintAvdFasteData" onClick="setBlockUI(this);" href="mainmaintenanceavdfastedata.do?id=${model.dbTable}">
							<font class="tabDisabledLink">&nbsp;Faste data</font>&nbsp;
							<font class="text11MediumBlue">(${model.avd})</font>
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="14%" valign="bottom" class="tabDisabled" align="center">
						<a id="alinkAvdHodeDok" onClick="setBlockUI(this);" href="mainmaintenanceavdhodedok.do?id=${model.dbTable}">
							<font class="tabDisabledLink">&nbsp;Hode på dok.</font>&nbsp;
							<font class="text11MediumBlue">(${model.avd})</font>
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="14%" valign="bottom" class="tabDisabled" align="center">
						<a id="alinkAvdListHode" onClick="setBlockUI(this);" href="mainmaintenanceavdlisthode.do?id=${model.dbTable}">
							<font class="tabDisabledLink">&nbsp;Listehode</font>&nbsp;
							<font class="text11MediumBlue">(${model.avd})</font>
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="14%" valign="bottom" class="tabDisabled" align="center">
						<a id="alinkAvdOppdTur" onClick="setBlockUI(this);" href="mainmaintenanceavdopptur.do?id=${model.dbTable}">
							<font class="tabDisabledLink">&nbsp;Oppd.nr og tur</font>&nbsp;
							<font class="text11MediumBlue">(${model.avd})</font>
						</a>
					</td>
					<td width="2%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
				</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td>
		<%-- space separator --%>
	 		<table width="100%" class="tabThinBorderWhite" border="0" cellspacing="0" cellpadding="0">
	 	    <tr height="20"><td>&nbsp;</td></tr>
	 	    <tr>
				<td width="5%">&nbsp;</td>
				<td >UPDATE fields: TODO right here!</td>
			</tr>	
	 	    
			<%-- list component 
			<tr>
				<td width="5%">&nbsp;</td>
				<td width="100%">
				<table id="containerdatatableTable" width="90%" cellspacing="1" border="0" align="left">
			    	    <tr>
						<td class="text11">
						<table width="100%" id="mainList" class="display compact cell-border" >
							<thead>
							<tr>
							                                                                
								<th width="2%" class="tableHeaderFieldFirst" align="center" >&nbsp;Avd&nbsp;</th>
								<th width="15%" class="tableHeaderField" align="center" >&nbsp;Avd.navn</th>
			                    <th class="tableHeaderField" align="left" >&nbsp;Firma&nbsp;</th>
			                    <th class="tableHeaderField" align="left" >&nbsp;Kundenr.&nbsp;</th>
			                    <th class="tableHeaderField" align="center" >&nbsp;Bære&nbsp;</th>
			                    <th class="tableHeaderField" align="center" >&nbsp;Sted&nbsp;</th>
			                    <th class="tableHeaderField" align="center" >&nbsp;IATA Kode&nbsp;</th>
			                </tr>  
			                </thead> 
			                <tbody >  
				            <c:forEach var="record" items="${Xmodel.list}" varStatus="counter">   
				               <tr class="tableRow" height="20" >
				              
				               <td width="2%" class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" align="center">
					               	<a id="alinkRecordId_${counter.count}" onClick="setBlockUI(this);" href="TODOtvinnsadmaintenanceimport_${record.pgm}.do?id=${record.dbTable}">
	               						<img src="resources/images/update.gif" border="0" alt="edit">
				               		</a>
				               </td>
				               <td width="15%" class="tableCellFirst" style="border-style: solid;border-width: 0px 1px 1px 1px;border-color:#FAEBD7;" align="center" ><font class="text12">&nbsp;${record.id}&nbsp;</font></td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.todo}&nbsp;</font></td>
		                       <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.todo}&nbsp;</font></td>
		                       <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.todo}&nbsp;</font></td>
		                       <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.todo}&nbsp;</font></td>
		                       <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.todo}&nbsp;</font></td>
		                       
				            </tr> 
				            </c:forEach>
				            </tbody>
			            </table>
					</td>	
					</tr>
				</table>
				</td>
			</tr>	
			--%>	    
	 	    <tr height="20"><td>&nbsp;</td></tr>
	 		</table>
		</td>
	</tr>
</table>	

<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->

