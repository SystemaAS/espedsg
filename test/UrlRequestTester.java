import java.net.URL;
import java.net.URLEncoder;

import no.systema.main.service.UrlCgiProxyServiceImpl;
import no.systema.tds.tdsimport.url.store.TdsImportUrlDataStore;
import no.systema.tds.url.store.TdsUrlDataStore;

/**
 * @author oscardelatorre
 * 
 */
public class UrlRequestTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		UrlRequestTester urlRquestTester = new UrlRequestTester();
		try{
			System.out.println(URLEncoder.encode("Oscar&COVI%D", "UTF-8"));
			String tmp = new String("Oscar&");
			tmp.replace("&","%26");
		}catch(Exception e){
			e.printStackTrace();
		}
		//urlRquestTester.runPOST();
		//urlRquestTester.runGET();

	}
	/**
	 * HTML POST
	 */
	public void runPOST(){
		//String urlStr = UrlDataStore.TDS_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_URL;
		//String urlParameters = "user=OSCAR&avd=1&opd=219&mode=U&sveh_syav=1&sveh_syop=219&sveh_tart=1&sveh_dek1=EX&sveh_dek2=Z&sveh_kenh=ABCDEFGHIJKLMNOPQ&sveh_tuid=SYS0000849&sveh_mtyp=UGE&sveh_sydt=20130321&sveh_syst=E&sveh_sysg=CB&sveh_avkn=&sveh_aveo=SE010101010600&sveh_avna=ANDöÖøØ ANDERSSONS EXPORTINDUSTRI AB&sveh_ava1=L22NXXX  YTTERSTA GR?ND 1&sveh_ava2=LILLA GALLERIPASSAGEN EFTER POSTEN&sveh_avpn=190 45&sveh_avpa=STOCKHOLM ARLANDA&sveh_avlk=SE&sveh_avha=ANNA ANDERSSON ALEXANDERSSON LUNDBY&sveh_avtl=49 123 444 5555 666 2345&sveh_mokn=&sveh_moeo=SE010101010600&sveh_mona=THE SMITHS at ?M?L und G?teborg???&sveh_moa1=Whatever&sveh_moa2=2 ND EAST STREET BEHIND THE BAKERY&sveh_mopn=US1111119&sveh_mopa=VERY LONG ISLAND AND NEW YORK STATE&sveh_molk=US&sveh_kota=101&sveh_rfab=&sveh_rfac=ABC12345678912345678912345678912345&sveh_rfac2=&sveh_rfac3=&sveh_kvsa=J&sveh_dkkn=&sveh_dkeo=SE010101010600&sveh_dklk=SE&sveh_dkna=ANDERS ANDERSSONS EXPORTINDUSTRI AB&sveh_dka1=L?NGA MELLANGATANS YTTERSTA GR?ND 1&sveh_dka2=LILLA GALLERIPASSAGEN EFTER POSTEN&sveh_dkpa=STOCKHOLM ARLANDA&sveh_dkpn=190 45&sveh_omeo=SE444197610900&sveh_omty=2&sveh_omha=CHRISTER BECH&sveh_omtl=004722660660&sveh_avut=SE&sveh_aukd=&sveh_aube=US&sveh_trid=&sveh_trlk=&sveh_cont=1&sveh_conn=&sveh_trai=FARTYGET ELISABETH VICTORIA REGINA&sveh_tral=US&sveh_faty=N380&sveh_fatx=1234&sveh_vakd=&sveh_vaku=&sveh_vaom=&sveh_fabl=&sveh_trgr=3&sveh_trin=1&sveh_grkd=&sveh_godm=&sveh_golk=AZZ&sveh_kosl=&sveh_vasl=&sveh_brut=123456789&sveh_suti=&sveh_abub=&sveh_eup1=&sveh_eup2=&sveh_call=&sveh_upps=T&sveh_tid1=&sveh_tid2=&sveh_tid3=&sveh_tid21=&sveh_tid22=&sveh_tid23=&sveh_kleo=SE603303&sveh_trnr=&sveh_lagi=&sveh_lagt=&sveh_lagl=&sveh_utfa=SE603303&sveh_last=&sveh_taxd=&sveh_beat=201304011200&sveh_betk=&sveh_komr=&sveh_res1=&sveh_res2=&sveh_res3=&sveh_res4=&sveh_res5=&sveh_res6=&sveh_res7=&sveh_res8=&sveh_sel1=PLOMB12345&sveh_sel2=&sveh_saom=B&sveh_sgdk=&sveh_sgme=&sveh_sgut=&sveh_sgid=&sveh_sgdt=&sveh_byte=&sveh_kval=";
		
		//String urlStr = UrlDataStore.TDS_EXPORT_BASE_TOPICLIST_URL;
		
		String urlStr = TdsImportUrlDataStore.TDS_IMPORT_BASE_UPDATE_SPECIFIC_TOPIC_ITEM_URL;
		String urlParameters = "user=OSCAR&avd=1&opd=157&lin=1&mode=U&sum_of_sviv_kotas=0&sviv_syav=1&sviv_syop=157&sviv_syli=1&sviv_vano=1&sviv_ulkd=CA&sviv_vata=&sviv_vati=&sviv_eup1=4071&sviv_eup2=&sviv_brut=1550&sviv_fokd=&sviv_neto=1240&sviv_ankv=&sviv_vasl=berg€&sviv_vas2=&sviv_vas3=&sviv_vas4=&sviv_vas5=&sviv_kota=123&sviv_kot2=&sviv_kot3=&sviv_kot4=&sviv_kot5=&sviv_kosl=EF&sviv_kos2=&sviv_kos3=&sviv_kos4=&sviv_kos5=&sviv_godm=DEF456&sviv_god2=&sviv_god3=&sviv_god4=&sviv_god5=&sviv_vat4=&sviv_suko=&sviv_suk6=&sviv_sukb=&sviv_sutx=&sviv_sut2=&sviv_sut3=&sviv_sut4=&sviv_sut5=&sviv_sut6=&sviv_sut7=&sviv_sut8=&sviv_sut9=&sviv_suta=&sviv_sutb=&sviv_sutc=&sviv_sutd=&sviv_sute=&sviv_sutf=&sviv_stva=&sviv_stva2=&sviv_fabl=&sviv_bit1=&sviv_bit2=&sviv_bit3=&sviv_bit4=&sviv_bit5=&sviv_bit6=&sviv_bit7=&sviv_bit8=&sviv_bit9=&sviv_bii1=&sviv_bii2=&sviv_bii3=&sviv_bii4=&sviv_bii5=&sviv_bii6=&sviv_bii7=&sviv_bii8=&sviv_bii9=&sviv_co01=AB123456789&sviv_co02=&sviv_co03=&sviv_co04=&sviv_co05=&sviv_co06=&sviv_co07=&sviv_co08=&sviv_co09=&sviv_co10=&sviv_co11=&sviv_co12=&sviv_co13=&sviv_co14=&sviv_co15=&sviv_co16=&sviv_co17=&sviv_co18=&sviv_co19=&sviv_co20=&sviv_tik1=&sviv_tik2=&sviv_tik3=&sviv_tik4=&sviv_tik5=&sviv_tik6=&sviv_tit1=&sviv_tit2=&sviv_tit3=&sviv_tit4=&sviv_tit5=&sviv_tit6=&sviv_tix1=&sviv_tix2=&sviv_tix3=&sviv_tix4=&sviv_tix5=&sviv_tix6=&sviv_lagi=AAA12345&sviv_lagt=C&sviv_lagl=SE&sviv_call=00&sviva_abk1=&sviva_abg1=&sviva_abs1=&sviva_abx1=&sviva_abb1=&sviva_abk2=&sviva_abg2=&sviva_abs2=&sviva_abx2=&sviva_abb2=&sviva_abk3=&sviva_abg3=&sviva_abs3=&sviva_abx3=&sviva_abb3=&sviva_abk4=&sviva_abg4=&sviva_abs4=&sviva_abx4=&sviva_abb4=&sviva_abk5=&sviva_abg5=&sviva_abs5=&sviva_abx5=&sviva_abb5=";

		
		try{
		UrlCgiProxyServiceImpl urlCgiProxyServiceImpl = new UrlCgiProxyServiceImpl();
		String urlParametersInBytes = new String(urlParameters.getBytes());
		String response = urlCgiProxyServiceImpl.getJsonContent(urlStr, urlParametersInBytes);
		System.out.println(response);
			//String iso = new String(response.getBytes(), "ISO-8859-1");
			//System.out.println(iso);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
	/**
	 * HMTL GET
	 */
	public void runGET(){
		String urlStr = "http://gw.systema.no/sycgip/TSVE002R.pgm?user=OSCAR&avd=1&opd=139&mode=U1&sveh_syav=1";
		
		UrlCgiProxyServiceImpl urlCgiProxyServiceImpl = new UrlCgiProxyServiceImpl();
		String response = urlCgiProxyServiceImpl.getJsonContent(urlStr);
		System.out.println(response);
	}
	
}
