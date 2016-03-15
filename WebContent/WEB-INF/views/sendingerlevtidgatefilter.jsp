<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerSendingerlevtid.jsp" />
<!-- =====================end header ==========================-->
<SCRIPT type="text/javascript" src="resources/js/sendingerlevtidgatefilter.js?ver=${user.versionEspedsg}"></SCRIPT>	

<script type="text/javascript">
//<![CDATA[
//Send form if they hit enter.
/*
document.onkeypress = enter;
function enter(e){
if (e.which == 13){sendform()}
}

//Form to send 
function sendform(){
document.forms[0].submit();
}
*/
//]]>
</script>

<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
<tr>
	<td>
	<%-- tab container component --%>
	<table tabindex='-1' width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
		<tr height="2"><td></td></tr>
		<tr height="25"> 
			<td width="20%" valign="bottom" class="tab" align="center" >
				<font class="tabLink">&nbsp;<spring:message code="systema.overview.sendingerlevtid.filter.tab"/></font>
				&nbsp;<img valign="bottom" src="resources/images/find.png"  border="0" alt="Sendinger">
				
			</td>
			<td width="80%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>	
			
		</tr>
	</table>
	</td>
</tr>
<tr>
	<td>
	<%-- tab component --%>
 		<table  tabindex='-1' height="750px" width="100%" class="tabThinBorderWhite" border="0" cellspacing="0" cellpadding="0">
 	        <tr height="60"><td>&nbsp;</td></tr>
 	        <tr style="height:15px;">
	 			<td valign="bottom" >		
	 				<%-- form header --%>
	 				<table width="70%" align="center" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
				 		<tr>
				 			<td class="text12White">&nbsp;
					 			<b>Søkekriteriene</b>&nbsp;Tilleggs filter
			 				</td>
		 				</tr>
	 				</table>
	 			</td>
	 		</tr>
 	        <tr>
 	        		<td valign="top">
 	        			<form name="formFilter" id="formFilter" method="POST" >
 	        			<input type="hidden" name="applicationUser" id="applicationUser" value='${user.user}'>
 	        			<table class="formFrameTitaniumGrayRoundBottom" width="70%" align="center" border="0" >
 	        				<tr>
 	        					<td class="text12">
								<font class="text14Red" >*</font>Sendt i tidsrom(yyyymmdd):
 	        					</td>
 	        					<td >
 	        						<table >
 	        						<tr>
	 	        						<td>
	 	        							<input onKeyPress="return numberKey(event)" class="inputText" type="text" name="dato" id="dato" size="9" maxlength="8" value='${model.searchFilterGateChar_SENDLEV.dato}'>
	 	        						</td>
	 	        						
	 	        						<td>
	 	        							<input onKeyPress="return numberKey(event)" class="inputText" type="text" name="datot" id="datot" size="9" maxlength="8" value='${model.searchFilterGateChar_SENDLEV.datot}'>
	 	        						</td>
 	        						</tr>
 	        						</table>
 	        					</td>
 	        				</tr>
 	        				
 	        				<tr height="20px"><td >&nbsp;</td></tr>
 	        				
 	        				<tr>
 	        					<td class="text12">
								Agentnr:
 	        					</td>

 	        					<td >
 	        						<table >
 	        						<tr>
	 	        						<td>
	 	        							<input class="inputText" type="text" name="agent" id="agent" size="8" maxlength="8" value='${model.searchFilterGateChar_SENDLEV.agent}'>
	 	        							
	 	        							<img id="imgAgentSearch" style="cursor:pointer;" src="resources/images/find.png" border="0" alt="search" onClick="showPop('searchAgentDialog');">
							            	<%-- =====================================================  --%>
							            	<%-- Here we have the search Sender [Customer] popup window --%>
							            	<%-- =====================================================  --%>
							            	<span style="position:absolute; left:600px; top:200px; width:300px; height:212px;" id="searchAgentDialog" class="popupWithInputText"  >
								           		<div class="text10" align="left">
								           			<table>
								           				<tr>
															<td class="text11">&nbsp;Agent nr.</td>
															<td class="text11">&nbsp;<input type="text" class="inputText" name="search_sveh_avkn" id="search_sveh_avkn" size="18" maxlength="8" value=''></td>
														</tr>
									           			<tr>
															<td class="text11">&nbsp;Navn</td>
															<td class="text11">&nbsp;<input type="text" class="inputText" name="search_sveh_avna" id="search_sveh_avna" size="18" maxlength="35" value=''></td>
														</tr>
									           			<tr>
									           				<td class="text11">&nbsp;</td>
										           			<td align="right">&nbsp;<button name="searchCustomer" id="searchCustomer" class="buttonGray" type="button" onClick="searchAgentOwnWindow()">Sök</button></td>
										           		</tr>
										           		<tr height="4"><td ></td></tr>
										           		<tr>
									           				<td class="text11">&nbsp;Utvalg</td>
										           			<td>&nbsp;</td>
										           		</tr>
										           		<tr>
															<td colspan="2">&nbsp;
																<%-- check jQuery events (onChange) for this list --%>
																<select class="text11" id="agentList" name="agentList" size="3" onDblClick="hidePop('searchAgentDialog');">
				 													<option value="">-Velg-</option>
				 							 					</select>
															</td>
															
															<%-- <input type="hidden" name="hidden_sveh_avkn" id="hidden_sveh_avkn" value=''> --%>
														</tr>
								           			</table>
													<table width="30%" align="left" border="0">
														<tr align="left" >
															<td >&nbsp;<button name="searchAgentCloseOk" id="searchAgentCloseOk" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('searchAgentDialog');">Ok</button></td>
															<td >&nbsp;<button name="searchAgentCloseCancel" id="searchAgentCloseCancel" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('searchAgentDialog');">Avbryt</button></td>
														</tr>
													</table>
												</div>
											</span>				
						            		
	 	        						</td>
	 	        						
	 	        						<td>
	 	        							&nbsp;
	 	        						</td>
 	        						</tr>
 	        						</table>
 	        					</td>
 	        				</tr>
 	        				<tr>
 	        					<td class="text12">
								AgentGruppe:
 	        					</td>
 	        					<td >
 	        						<table >
 	        						<tr>
	 	        						<td>
	 	        							<input class="inputText" type="text" name="agentg" id="agentg" size="8" maxlength="8" value='${model.searchFilterGateChar_SENDLEV.agentg}'>
	 	        						</td>
	 	        						
	 	        						<td class="text12">
	 	        							(SAE=95700, CL=95500)
	 	        						</td>
 	        						</tr>
 	        						</table>
 	        					</td>
 	        				</tr>
 	        				<tr>
 	        					<td class="text12">
								Kun Turnummer:
 	        					</td>
 	        					<td >
 	        						<table >
 	        						<tr>
	 	        						<td>
	 	        							<input class="inputText" type="text" name="tur" id="tur" size="8" maxlength="8" value='${model.searchFilterGateChar_SENDLEV.tur}'>
	 	        						</td>
	 	        						
	 	        						<td class="text12">
	 	        							
	 	        						</td>
 	        						</tr>
 	        						</table>
 	        					</td>
 	        				</tr>
 	        				<tr>
 	        					<td class="text12">
								Kun Import/Eksport/Domestic:
 	        					</td>
 	        					<td >
 	        						<table >
 	        						<tr>
	 	        						<td>
						 				<select name="ied" id="ied">
						 					<option value="">-Alle-</option>
						 					<option value="I" <c:if test="${model.searchFilterGateChar_SENDLEV.ied == 'I' || empty model.searchFilterGateChar_SENDLEV.ied }"> selected </c:if> >Import</option>
						 					<option value="E" <c:if test="${model.searchFilterGateChar_SENDLEV.ied == 'E'}"> selected </c:if> >Eksport</option>
						 					<option value="D" <c:if test="${model.searchFilterGateChar_SENDLEV.ied == 'D'}"> selected </c:if> >Domestic</option>
										</select>
	 	        						</td>
	 	        						
	 	        						<td class="text12">
										&nbsp;
	 	        						</td>
 	        						</tr>
 	        						</table>
 	        					</td>
 	        				</tr>
 	        				
 	        				<tr>
 	        					<td class="text12">
								Inkluder/Overse(I/O) oppdrag m/Lossested:
 	        					</td>
 	        					<td >
 	        						<table >
 	        						<tr>
	 	        						<td>
	 	        							<input class="inputText" type="text" name="iols" id="iols" size="8" maxlength="8" value='${model.searchFilterGateChar_SENDLEV.tur}'>
	 	        						</td>
             							
	 	        						<td class="text12">
		 	        						<input class="inputText" type=text name="ls1" id="ls1" value='${model.searchFilterGateChar_SENDLEV.ls1}' size=20 maxlength=20>
	 	        						</td>
 	 	        						<td class="text12">
		 	        						<input class="inputText" type=text name="ls2" id="ls2" value='${model.searchFilterGateChar_SENDLEV.ls2}' size=20 maxlength=20>
	 	        						</td>
 	        						</tr>
 	        						</table>
 	        					</td>
 	        				</tr>

 	        				<tr>
 	        					<td class="text12">
								Inkluder/Overse oppdrag m/T&T-kode
 	        					</td>
 	        					<td >
 	        						<table>
 	        						<tr>
	 	        						<td>
						 				<select name="iott" id="iott">
						 					<option value="">-Alle-</option>
						 					<option value="I" <c:if test="${model.searchFilterGateChar_SENDLEV.iott == 'I'}"> selected </c:if> >Inkluder</option>
						 					<option value="O" <c:if test="${model.searchFilterGateChar_SENDLEV.iott == 'O'}"> selected </c:if> >Overse</option>
										</select>
	 	        						</td>
	 	        						
	 	        						<td class="text12">
										<input <c:if test="${empty model.searchFilterGateChar_SENDLEV.iott}"> disabled </c:if>  class="inputText" type="text" name="tt1" id="tt1" size="3" maxlength="3" value='${model.searchFilterGateChar_SENDLEV.tt1}'>
	 	        						</td>
	 	        						<td class="text12">
										<input <c:if test="${empty model.searchFilterGateChar_SENDLEV.iott}"> disabled </c:if>  class="inputText" type="text" name="tt2" id="tt2" size="3" maxlength="3" value='${model.searchFilterGateChar_SENDLEV.tt2}'>
	 	        						</td>
	 	        						<td class="text12">
										<input <c:if test="${empty model.searchFilterGateChar_SENDLEV.iott}"> disabled </c:if>  class="inputText" type="text" name="tt3" id="tt3" size="3" maxlength="3" value='${model.searchFilterGateChar_SENDLEV.tt3}'>
	 	        						</td>
	 	        						<td class="text12">
										<input <c:if test="${empty model.searchFilterGateChar_SENDLEV.iott}"> disabled </c:if>  class="inputText" type="text" name="tt4" id="tt4" size="3" maxlength="3" value='${model.searchFilterGateChar_SENDLEV.tt4}'>
	 	        						</td>
	 	        						<td class="text12">
										<input <c:if test="${empty model.searchFilterGateChar_SENDLEV.iott}"> disabled </c:if>  class="inputText" type="text" name="tt5" id="tt5" size="3" maxlength="3" value='${model.searchFilterGateChar_SENDLEV.tt5}'>
	 	        						</td>
	 	        						
 	        						</tr>
 	        						</table>
 	        					</td>
 	        				</tr>
 	        				
 	        				<tr>
 	        					<td class="text12">
								Inkluder/Overse oppdrag m/T&T-kode
 	        					</td>
 	        					<td >
 	        						<table>
 	        						<tr>
	 	        						<td>
						 				<select name="iottb" id="iottb">
						 					<option value="">-Alle-</option>
						 					<option value="I" <c:if test="${model.searchFilterGateChar_SENDLEV.iottb == 'I'}"> selected </c:if> >Inkluder</option>
						 					<option value="O" <c:if test="${model.searchFilterGateChar_SENDLEV.iottb == 'O'}"> selected </c:if> >Overse</option>
										</select>
	 	        						</td>
	 	        						
	 	        						<td class="text12">
										<input <c:if test="${empty model.searchFilterGateChar_SENDLEV.iottb}"> disabled </c:if> class="inputText" type="text" name="tt1b" id="tt1b" size="3" maxlength="3" value='${model.searchFilterGateChar_SENDLEV.tt1b}'>
	 	        						</td>
	 	        						<td class="text12">
										<input <c:if test="${empty model.searchFilterGateChar_SENDLEV.iottb}"> disabled </c:if> class="inputText" type="text" name="tt2b" id="tt2b" size="3" maxlength="3" value='${model.searchFilterGateChar_SENDLEV.tt2b}'>
	 	        						</td>
	 	        						<td class="text12">
										<input <c:if test="${empty model.searchFilterGateChar_SENDLEV.iottb}"> disabled </c:if> class="inputText" type="text" name="tt3b" id="tt3b" size="3" maxlength="3" value='${model.searchFilterGateChar_SENDLEV.tt3b}'>
	 	        						</td>
	 	        						<td class="text12">
										<input <c:if test="${empty model.searchFilterGateChar_SENDLEV.iottb}"> disabled </c:if> class="inputText" type="text" name="tt4b" id="tt4b" size="3" maxlength="3" value='${model.searchFilterGateChar_SENDLEV.tt4b}'>
	 	        						</td>
	 	        						<td class="text12">
										<input <c:if test="${empty model.searchFilterGateChar_SENDLEV.iottb}"> disabled </c:if> class="inputText" type="text" name="tt5b" id="tt5b" size="3" maxlength="3" value='${model.searchFilterGateChar_SENDLEV.tt5b}'>
	 	        						</td>
	 	        						
 	        						</tr>
 	        						</table>
 	        					</td>
 	        				</tr>
 	        				<tr>
 	        					<td colspan="10" class="text12">
								(Ved T&T-filter er det nok at EN av kodene slår til. Dvs: I:Flere koder gir flere treff. O:Flere kode FÆRRE treff/flere "Overses")
 	        					</td>
 	        					<td>
 	        					</td>
 	        				</tr>
 	        				
 	        				<tr height="30px"><td>&nbsp;</td></tr>
 	        				
 	        				<tr>
 	        					<td class="text12">
								Kun de med Lev.tids-kode Y/N:
 	        					</td>
 	        					<td >
 	        						<table >
 	        						<tr>
	 	        						<td>
						 				<select name="levyn" id="levyn">
						 					<option value="">-Alle-</option>
						 					<option value="Y" <c:if test="${model.searchFilterGateChar_SENDLEV.levyn == 'Y'}"> selected </c:if> >Y</option>
						 					<option value="N" <c:if test="${model.searchFilterGateChar_SENDLEV.levyn == 'N'}"> selected </c:if> >N</option>
										</select>
	 	        						</td>
	 	        						<td class="text12">(Y=Levert i tide, N=Levert for sent)</td>
 	        						</tr>
 	        						</table>
 	        					</td>
 	        				</tr>
 	        				
 	        				<tr>
 	        					<td class="text12">
								Kun de med POD archived tids-kode Y/N:
 	        					</td>
 	        					<td >
 	        						<table >
 	        						<tr>
	 	        						<td>
						 				<select name="poayn" id="poayn">
						 					<option value="">-Alle-</option>
						 					<option value="Y" <c:if test="${model.searchFilterGateChar_SENDLEV.poayn == 'Y'}"> selected </c:if> >Y</option>
						 					<option value="N" <c:if test="${model.searchFilterGateChar_SENDLEV.poayn == 'N'}"> selected </c:if> >N</option>
										</select>
	 	        						</td>
	 	        						<td class="text12">(Y=POD Archived innen 48 timer, N=POD Archived for sent)</td>
 	        						</tr>
 	        						</table>
 	        					</td>
 	        				</tr>
 	        				
 	        				<tr>
 	        					<td class="text12">
								Avdelning/trafikk:
 	        					</td>
 	        					<td >
 	        						<table >
 	        						<tr>
	 	        						<td>
	 	        							<input class="inputText" type="text" name="avd" id="avd" size="4" maxlength="4" value='${model.searchFilterGateChar_SENDLEV.avd}'>
	 	        						</td>
	 	        						
	 	        						<td class="text12">
	 	        							
	 	        						</td>
 	        						</tr>
 	        						</table>
 	        					</td>
 	        				</tr>
 	        				<tr>
 	        					<td class="text12">
								Transportør:
 	        					</td>
 	        					<td >
 	        						<table >
 	        						<tr>
	 	        						<td>
	 	        							<input class="inputText" type="text" name="trans" id="trans" size="8" maxlength="8" value='${model.searchFilterGateChar_SENDLEV.trans}'>
	 	        							
	 	        							<img id="imgCarrierSearch" style="cursor:pointer;" src="resources/images/find.png" border="0" alt="search" onClick="showPop('searchCarrierDialog');">
	 	        							<%-- =====================================================  --%>
							            	<%-- Here we have the search Sender [Customer] popup window --%>
							            	<%-- =====================================================  --%>
							            	<span style="position:absolute; left:600px; top:600px; width:300px; height:212px;" id="searchCarrierDialog" class="popupWithInputText"  >
								           		<div class="text10" align="left">
								           			<table>
								           				<tr>
															<td class="text11">&nbsp;Trans.nr.</td>
															<td class="text11">&nbsp;<input type="text" class="inputText" name="search_tnr" id="search_tnr" size="18" maxlength="8" value=''></td>
														</tr>
									           			<tr>
															<td class="text11">&nbsp;Navn</td>
															<td class="text11">&nbsp;<input type="text" class="inputText" name="search_sonavn" id="search_sonavn" size="18" maxlength="35" value=''></td>
														</tr>
									           			<tr>
									           				<td class="text11">&nbsp;</td>
										           			<td align="right">&nbsp;<button name="searchCarrier" id="searchCarrier" class="buttonGray" type="button" onClick="searchCarrierOwnWindow()">Sök</button></td>
										           		</tr>
										           		<tr height="4"><td ></td></tr>
										           		<tr>
									           				<td class="text11">&nbsp;Utvalg</td>
										           			<td>&nbsp;</td>
										           		</tr>
										           		<tr>
															<td colspan="2">&nbsp;
																<%-- check jQuery events (onChange) for this list --%>
																<select class="text11" id="carrierList" name="carrierList" size="3" onDblClick="hidePop('searchCarrierDialog');">
				 													<option value="">-Velg-</option>
				 							 					</select>
															</td>
															
															<%-- <input type="hidden" name="hidden_sveh_avkn" id="hidden_sveh_avkn" value=''> --%>
														</tr>
								           			</table>
													<table width="30%" align="left" border="0">
														<tr align="left" >
															<td >&nbsp;<button name="searchCustomerCloseOk" id="searchCustomerCloseOk" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('searchCarrierDialog');">Ok</button></td>
															<td >&nbsp;<button name="searchCarrierCloseCancel" id="searchCarrierCloseCancel" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('searchCarrierDialog');">Avbryt</button></td>
														</tr>
													</table>
												</div>
										</span>				
						            		
	 	        						</td>
	 	        						
	 	        						<td class="text12">
	 	        							
	 	        						</td>
 	        						</tr>
 	        						</table>
 	        					</td>
 	        				</tr>
 	        				<tr>
 	        					<td class="text12">
								Mottakers postnr og/eller sted:
 	        					</td>
 	        					<td >
 	        						<table >
 	        						<tr>
	 	        						<td>
	 	        							<input class="inputText" type="text" name="mottpost" id="mottpost" size="20" maxlength="20" value='${model.searchFilterGateChar_SENDLEV.mottpost}'>
	 	        						</td>
	 	        						
	 	        						<td class="text12">
	 	        							
	 	        						</td>
 	        						</tr>
 	        						</table>
 	        					</td>
 	        				</tr>
 	        				<tr>
 	        					<td class="text12">
								Vis også klokkeslett:
 	        					</td>
 	        					<td >
 	        						<table >
 	        						<tr>
	 	        						<td>
	 	        							<input type="checkbox" name="viskl" id="viskl" value="Y" <c:if test="${model.searchFilterGateChar_SENDLEV.viskl == 'Y'}"> checked </c:if> >
	 	        						</td>
	 	        						
	 	        						<td class="text12">
	 	        							
	 	        						</td>
 	        						</tr>
 	        						</table>
 	        					</td>
 	        				</tr>
 	        				
 	        				
 	        				<tr>
 	        					<td class="text12">&nbsp;</td>
	        					<td >
 	        						<table align="right">
 	        						<tr>
	 	        						<td class="text12">
	 		        						<input class="inputFormSubmit" type="submit" name="submit" onclick="javascript: form.action='sendingerlevtidgate.do';" value="OK"/>
	 	        						</td>
		        						<td class="text12">
	 		        						<input class="inputFormSubmit" type="submit" name="cancel" onclick="javascript: form.action='logoutSendingerlevtid.do';" value="Avslutt"/>
	 	        						</td>
	 	        						<td class="text12">
	 		        					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	 		        					</td>
	 	        						
 	        						</tr>
 	        						</table>
 	        					</td>
 	        				</tr>
        				</table>  
        				</form>      				
        			</td>	
 	        </tr>
		</table>
	</td>
</tr>
</table>

<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->

