<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header =====================================-->
<jsp:include page="/WEB-INF/views/headerMainMaintenanceChildWindows.jsp" />
<!-- =====================end header ====================================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
	specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/mainmaintenanceavd_syfa28r_edit_childwindow.js?ver=${user.versionEspedsg}"></SCRIPT>
	
	<table width="90%" height="180px" class="tableBorderWithRoundCorners3D_RoundOnlyOnBottom" cellspacing="1" border="0" cellpadding="0">
		<tr>
			<td colspan="10" class="text14Bold">&nbsp;&nbsp;&nbsp;
				<img title="search" valign="bottom" src="resources/images/update.gif" width="16px" height="16px" border="0" alt="search">
				Vedlikehold av avdelings faste data (Del -2)
			</td>
		</tr>
		<tr>
		<td valign="top">
		
  		<%-- this container table is necessary in order to separate the datatables element and the frame above, otherwise
	 	the cosmetic frame will not follow the whole datatable grid including the search field... --%>
		<table id="containerdatatableTable" cellspacing="1" align="left" width="100%" >
			<tr>
			<td>
				<table>
					<form name="syfa28ChildForm" id="syfa28ChildForm" action="TODOtvinnsad_childwindow_customer.do?action=doFind" method="post">
					<input type="hidden" name="ctype" id="ctype" value="${Xmodel.ctype}">
					<tr>
						<td class="text12" title="koplnr">&nbsp;Utskriftnr.</td>
						<td class="text12" title="utptxt">&nbsp;Beskrivelse</td>
						<td class="text12" title="kopty">&nbsp;Papirtype</td>
						<td class="text12" title="kopnvn">&nbsp;Printernavn</td>
						<td class="text12" title="kophea">&nbsp;H</td>
						<td class="text12" title="koplas">&nbsp;L</td>
						<td class="text12" title="koplpi">&nbsp;8</td>
	           		</tr>
	           		<tr>
						<td ><input readonly type="text" class="inputTextReadOnly" name="koplnr" id="koplnr" size="5" value='${model.record.koplnr}'></td>
						<td ><input readonly type="text" class="inputTextReadOnly" name="utptxt" id="utptxt" size="30" value='${model.record.utptxt}'></td>
						<td ><input type="text" class="inputTextMediumBlue" name="kopty" id="kopty" size="11" maxlength="10" value='${model.record.kopty}'></td>
						<td ><input type="text" class="inputTextMediumBlue" name="kopnvn" id="kopnvn" size="11" maxlength="10" value='${model.record.kopnvn}'></td>
						<td ><input type="text" class="inputTextMediumBlue" name="kophea" id="kophea" size="2" maxlength="1" value='${model.record.kophea}'></td>
						<td ><input type="text" class="inputTextMediumBlue" name="koplas" id="koplas" size="2" maxlength="1" value='${model.record.koplas}'></td>
						<td ><input type="text" class="inputTextMediumBlue" name="koplpi" id="koplpi" size="2" maxlength="1" value='${model.record.koplpi}'></td>
	           		</tr>
	           		
	           		<tr>
						<td class="text12" title="kopfm">&nbsp;Stringk.</td>
						<td class="text12" title="kopdraw/kopoutb">&nbsp;Skuff I/U</td>
						<td class="text12" title="kopcopi">&nbsp;Kopier</td>
						<td class="text12" title="kopfov1">&nbsp;Ovl.</td>
						<td colspan="2" class="text12">&nbsp;</td>
	           		</tr>
	           		<tr>
						<td ><input type="text" class="inputTextMediumBlue" name="kopfm" id="kopfm" size="6" maxlength="5" value='${model.record.kopfm}'></td>
						<td >
							<input type="text" class="inputTextMediumBlue" name="kopdraw" id="kopdraw" size="4" maxlength="3" value='${model.record.kopdraw}'>
							<input type="text" class="inputTextMediumBlue" name="kopoutb" id="kopoutb" size="4" maxlength="3" value='${model.record.kopoutb}'>
						</td>
						<td ><input type="text" class="inputTextMediumBlue" name="kopcopi" id="kopcopi" size="3" maxlength="2" value='${model.record.kopcopi}'></td>
						<td ><input type="text" class="inputTextMediumBlue" name="kopfov1" id="kopfov1" size="10" maxlength="9" value='${model.record.kopfov1}'></td>
						
						<td colspan="2" ><input class="inputFormSubmit" type="submit" name="submit" id="submit"value='Lagre'/></td>
	           		</tr>
	           		
	           		</form>
           		</table>
			</td>
			</tr>           		
       		<tr height="10"><td></td></tr>
   		</table>

		</td>
		</tr>
</table> 
