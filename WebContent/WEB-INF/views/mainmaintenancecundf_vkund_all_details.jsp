<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<link href="resources/espedsgmaintenance.css?ver=${user.versionEspedsg}" rel="stylesheet" type="text/css"/>
<link href="resources/jquery.calculator.css" rel="stylesheet" type="text/css"/>
<link type="text/css" href="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/overcast/jquery-ui.css" rel="stylesheet">
<link type="text/css" href="//cdn.datatables.net/1.10.11/css/jquery.dataTables.css" rel="stylesheet">

<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
<script type="text/javascript" src="resources/js/jquery.blockUI.js"></script>
<script type="text/javascript" src="resources/js/systemaWebGlobal.js?ver=${user.versionEspedsg}"></script>
<script type="text/javascript" src="//cdn.datatables.net/1.10.11/js/jquery.dataTables.min.js"></script>


<SCRIPT type="text/javascript" src="resources/js/mainmaintenancecundf_vkund_all_details.js?ver=${user.versionEspedsg}"></SCRIPT>	


<style type = "text/css">
.ui-datepicker { font-size:9pt;}
</style>

<table class="formFrameHeader" width="95%" class="text11" cellspacing="0" border="0" cellpadding="0">
	<tr><td colspan="15" class="text12White" >&nbsp;Kunde informasjon</td></tr>
	<tr height="2"><td></td></tr>
		<tr height="25"> 
			<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a id="alinkMainMaintGate" tabindex=-1 style="display:block;" href="mainmaintenancegate.do">
				<font class="tabDisabledLink">&nbsp;Kunde</font>
				<img style="vertical-align: middle;"  src="resources/images/list.gif" border="0" alt="general list">
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="15%" valign="bottom" class="tabDisabled" align="center">
				<a id="alinkMainMaintAvdGate" onClick="setBlockUI(this);" href="mainmaintenanceavdgate.do?id=${model.dbTable}">
					<font class="tabDisabledLink">&nbsp;Kontaktpersoner</font>&nbsp;						
					<img style="vertical-align: middle;"  src="resources/images/list.gif" border="0" alt="avd. gate list">
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="15%" valign="bottom" class="tabDisabled" align="center">
				<a id="alinkMainMaintAvdGate" onClick="setBlockUI(this);" href="mainmaintenanceavd_syfa14r_edit.do">
					<font class="tabDisabledLink">&nbsp;Fritext</font>&nbsp;						
					<img style="vertical-align: middle;"  src="resources/images/list.gif" width="12" height="12" border="0" alt="new">
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="15%" valign="bottom" class="tabDisabled" align="center">
				<a id="alinkMainMaintAvdGate" onClick="setBlockUI(this);" href="mainmaintenanceavd_syfa14r_edit.do">
					<font class="tabDisabledLink">&nbsp;Parametrar</font>&nbsp;						
					<img style="vertical-align: middle;"  src="resources/images/list.gif" width="12" height="12" border="0" alt="new">
				</a>
			</td>
			<td width="40%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
		</tr>
</table>


