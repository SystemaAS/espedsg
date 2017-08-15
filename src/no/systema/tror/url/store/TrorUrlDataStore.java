package no.systema.tror.url.store;

import no.systema.main.util.AppConstants;

/**
 * 
 * Static URLs
 * @author oscardelatorre
 * @date Jul 04, 2017
 * 
 * 
 */
public class TrorUrlDataStore {
	
	//----------------------------
	//[1] FETCH MAIN ORDER LIST
	//----------------------------
	//http://localhost:8080/syjservicestror/syjsHEADF_LITE.do?user=OSCAR&limit=100 (dftdg)
	static public String TROR_BASE_MAIN_ORDER_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestror/syjsHEADF_LITE.do";
	
	//http://gw.systema.no/sycgip/syjsHEADF_LITE.do?user=OSCAR&heavd=1&heopd=11111 (mandatory fields)
	static public String TROR_BASE_FETCH_SPECIFIC_ORDER_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestror/syjsHEADF.do";
	
	/**
	 * @Example SELECT http://gw.systema.no:8080/syjservicestror/syjsKODTVA.do?user=OSCAR
	 */
	static public String TROR_BASE_KODTVA_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestror/syjsKODTVA.do";
	
	/**
	 * @Example SELECT specific: http://gw.systema.no:8080/syjservicestror/syjsKUFAST.do?user=OSCAR&kftyp=PRODTYPE&kfkod=L
	 * @Example SELECT list: http://gw.systema.no:8080/syjservicestror/syjsKUFAST.do?user=OSCAR&kftyp=PRODTYPE
	 */
	static public String TROR_BASE_KUFAST_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestror/syjsKUFAST.do";

	/**
	//--------------------
	//[3] EDIT Order
	//mode=A  = Add
	//mode=U  = Update
	//mode=D  = Delete
	//OG et tillegg ...:
	//&mode=G  = Get    
	//(og G=Get med &HEUNIK / &HEREFF=blank   betyr å hente defaulter   MEN  &HEUNIK / &HEREFF ulik blank = hente en konkret sak (for visning/edit mm..)
	//----------------------
	//http://gw.systema.no/sycgip/TBOK002R.pgm?user=OSCAR&mode=G&HEUNIK=&HEREFF=&newavd=80&newmodul=H&newmodul=E&newlandkode=DK&newsidesk=S&newtext=
	static public String EBOOKING_BASE_UPDATE_SPECIFIC_ORDER_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TBOK002R.pgm";
	
	//[2.1] Message Note management (Consignee, Carrier, Internal)
	static public String EBOOKING_BASE_WORKFLOW_FETCH_MAIN_ORDER_MESSAGE_NOTE_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE08R.pgm";
	//http://gw.systema.no/sycgip/TJGE08R.pgm?user=OSCAR&unik=75&reff=11&part=R (R=Receiver, G=Carrier, Blank=internal melding)
	//[2.2] Fraktbrev section (order lines)
	static public String EBOOKING_BASE_WORKFLOW_FETCH_LIST_MAIN_ORDER_FRAKTBREV_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE21R.pgm"; 	
	//http://gw.systema.no/sycgip/TJGE21R.pgm?user=OSCAR&unik=10001206&reff=TARZAN%20W&fbn=1
	static public String EBOOKING_BASE_WORKFLOW_FETCH_LINE_MAIN_ORDER_FRAKTBREV_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE22R.pgm"; 	
	//http://gw.systema.no/sycgip/TJGE22R.pgm?user=OSCAR&unik=10001201&reff=TARZAN%20X&fbn=1&lin=1
	static public String EBOOKING_BASE_WORKFLOW_UPDATE_LINE_MAIN_ORDER_FRAKTBREV_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE23R.pgm";
	//http://gw.systema.no/sycgip/TJGE23R.pgm?user=OSCAR&unik=10001206&reff=TARZAN W&fbn=1&lin=3&mode=A&...
	static public String EBOOKING_BASE_WORKFLOW_VALIDATE_LINE_MAIN_ORDER_FRAKTBREV_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE23RV.pgm";
	//http://gw.systema.no/sycgip/TJGE23RV.pgm?user=JOVO&avd=75&opd=19&fmmrk1=&fvant=1&fvpakn=&fvvt=TEST&fvvkt=&fvvol=&fvlm=&fvlm2=&fvlen=&fvbrd=&fvhoy=&ffunnr=1234&ffemb=&ffantk=1&ffante=1&ffenh=KGM
	static public String EBOOKING_BASE_WORKFLOW_VALIDATE_LINE_MAIN_ORDER_FRAKTBREV_2_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE23RV2.pgm";
	//http://gw.systema.no/sycgip/TJGE23RV2.pgm?user=JOVO&avd=75&opdtyp=OX&fmmrk1=&fvant=2&fvpakn=&fvvt=TEST&fvvkt=1000&fvlen=220&fvbrd=220&fvhoy=120&fvvol=&fvlm=&fvlm2=&ffunr=1234&ffemb=&ffantk=1&ffante=1&ffenh=KGM
	
	//------------
	//SEND ORDER
	//------------
	//http://gw.systema.no/sycgip/TBOK009R.pgm?user=OSCAR&HEUNIK=10001176
	static public String EBOOKING_BASE_SEND_SPECIFIC_ORDER_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TBOK009R.pgm";
	**/

	//----------------
	//Child window
	//----------------
	/**
	//(FRA)-->http://gw.systema.no/sycgip/TJINQSTED.pgm?user=JOVO&varlk=FRALK&VARKOD=FRA&SOKLK=NO&WSKUNPA=A (A, P eller blank) 
	//(TIL)-->http://gw.systema.no/sycgip/TJINQSTED.pgm?user=JOVO&varlk=TILLK&VARKOD=TIL&SOKLK=NO&
	static public String EBOOKING_BASE_CHILDWINDOW_POSTAL_CODES_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJINQSTED.pgm";
	
	
	//Fakturakunde - replaces the good old fashioned customer search(not applicable in eBooking)
	static public String EBOOKING_BASE_CHILDWINDOW_CUSTOMER_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJINQFKU.pgm";
	//Fakturakunde adresser
	static public String EBOOKING_BASE_CHILDWINDOW_CUSTOMER_DELIVERY_ADDRESS_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJINQVADR.pgm";
	//http://gw.systema.no/sycgip/TJINQVADR.pgm?user=JOVO&wkundnr=7031&wvadrnr=1 
	//http://gw.systema.no/sycgip/TJINQVADR.pgm?user=JOVO&wkundnr=7031&wvadrna=A (Alfa search exact)
	**/
	
	//Customer (N/A)
	/**
	static public String EBOOKING_BASE_CHILDWINDOW_CUSTOMER_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJINQKUND.pgm";
	//http://gw.systema.no/sycgip/TJINQKUND.pgm?user=JOVO&sokknr=1 
	//flera parametrar är: soknvn, kunpnsted, wsvarnv, maxv
	static public String EBOOKING_BASE_CHILDWINDOW_CUSTOMER_DELIVERY_ADDRESS_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJINQVADR.pgm";
	//http://gw.systema.no/sycgip/TJINQVADR.pgm?user=JOVO&wkundnr=7031&wvadrnr=1 
	//http://gw.systema.no/sycgip/TJINQVADR.pgm?user=JOVO&wkundnr=7031&wvadrna=A (all addresses)
	//...if not empty this will override the customer address (fetched with TJINQKUND.pgm...)
	
	
	static public String EBOOKING_BASE_CHILDWINDOW_LOAD_UNLOAD_PLACES_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJINQSDL.pgm";
	//http://gw.systema.no/sycgip/TJINQSDL.pgm?user=JOVO(return all)
	//http://gw.systema.no/sycgip/TJINQSDL.pgm?user=JOVO&soknvn=T... etc
	static public String EBOOKING_BASE_CHILDWINDOW_PACKING_CODES_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJINQFORP.pgm";
	//http://gw.systema.no/sycgip/TJINQFORP.pgm?user=JOVO&kode=ABCD&Getval=J&fullInfo=J
	//http://gw.systema.no/sycgip/TJINQFORP.pgm?user=JOVO&kode=A (sök alla fom A)
	
	static public String EBOOKING_BASE_CHILDWINDOW_DANGEROUS_GOODS_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJINQUNNR.pgm";
	//Note: gir alle poster (max 100)  fra og med unnr 1950 ved &fullinfo  ulik J returneres kun unnr /emb.gruppe/ index og kort tekst (max 50 lang) 
	//[1]http://gw.systema.no/sycgip/TJINQUNNR.pgm?user=JOVO&unnr=1950=&embg=&indx=&getval=&fullinfo=J
	//Note: Ved &getval=J sender en gjerne inn også embg + index (om dette er ulikt blank).. 
	//[2]http://gw.systema.no/sycgip/TJINQUNNR.pgm?user=JOVO&unnr=1950=&embg=&indx=G&getval=J&fullinfo=J
	//Note: Exakt match
	//[3]http://gw.systema.no/sycgip/TJINQUNNR.pgm?user=JOVO&unnr=1950=&embg=&indx=G&getval=J&fullinfo=J
	//[3.1]
	//J=Krever FULL match (båd unnr / embg / indx) :
	//http://gw.systema.no/sycgip/TJINQUNNR.pgm?user=JOVO&unnr=1202=&embg=III&indx=&matchOnly=J
	//[3.2]
	//E=Krever match på unnr / embg:
	//http://gw.systema.no/sycgip/TJINQUNNR.pgm?user=JOVO&unnr=1202=&embg=III&indx=&matchOnly=E
	//[3.3]
	//U=Krever kun match på  unnr :
	//http://gw.systema.no/sycgip/TJINQUNNR.pgm?user=JOVO&unnr=1202=&embg=&indx=&matchOnly=U
	
	//---------------------------------------------------
	//[2] GENERAL CODES - for country (AS400 from TVINN) 
	//---------------------------------------------------
	static public String EBOOKING_CODES_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNOG005R.pgm"; 
	//http://gw.systema.no/sycgip/TNOG005R.pgm?user=OSCAR&typ=2 //country list
	**/
	
	//---------------------------------------------------
	//[1.1] GENERAL FUNCTIONS eg.(signature, other...) 
	//---------------------------------------------------
	//http://localhost:8080/syjservicesbcore/syjsKOFAST.do?user=OSCAR&kftyp=DELSYS
	static public String TROR_GENERAL_CODES_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsKOFAST.do";
	//http://gw.systema.no:8080/syjservicesbcore/syjsKODTLK.do?user=OSCAR
	static public String TROR_COUNTRY_CODES_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsKODTLK.do";
	//http://gw.systema.no:8080/syjservicestror/syjsKODTVA.do?user=OSCAR
	static public String TROR_CURRENCY_CODES_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestror/syjsKODTVA.do";
	//http://gw.systema.no:8080/syjservicesbcore/syjsKODTOTY.do?user=OSCAR
	static public String TROR_OPPDRAGSTYPE_CODES_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsKODTOTY.do";
	//http://gw.systema.no:8080/syjservicestror/syjsKODTFR.do?user=OSCAR
	static public String TROR_INCOTERMS_CODES_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestror/syjsKODTFR.do";
	//http://gw.systema.no:8080/syjservicestror/syjsKUFAST.do?user=OSCAR
	static public String TROR_PRODUCT_CODES_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestror/syjsKUFAST.do";
	//http://gw.systema.no:8080/syjservicesbcore/syjsTRKODFR.do?user=OSCAR&tkunik=017
	static public String TROR_ENHET_CODES_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsTRKODFR.do";
			
	
	
	
}
