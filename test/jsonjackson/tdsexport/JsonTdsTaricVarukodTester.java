/**
 * 
 */
package jsonjackson.tdsexport;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.service.UrlCgiProxyServiceImpl;

import no.systema.tds.service.TdsTaricVarukodServiceImpl;
import no.systema.tds.url.store.TdsUrlDataStore;
import no.systema.tds.model.jsonjackson.codes.JsonTdsTaricVarukodContainer;
import no.systema.tds.model.jsonjackson.codes.JsonTdsTaricVarukodRecord;



/**
 * @author oscardelatorre
 *
 */
public class JsonTdsTaricVarukodTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JsonTdsTaricVarukodTester tester = new JsonTdsTaricVarukodTester();
		try{
			tester.run();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private void run() throws Exception{
		TdsTaricVarukodServiceImpl taricService = new TdsTaricVarukodServiceImpl();
		
		String BASE_URL = TdsUrlDataStore.TDS_FETCH_TARIC_VARUKODER_ITEMS_URL;
		String urlRequestParamsKeys = "user=OSCAR&ie=E&kod=8501";
		UrlCgiProxyService urlCgiProxyService = new UrlCgiProxyServiceImpl();
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		JsonTdsTaricVarukodContainer container = taricService.getContainer(jsonPayload);
		for(JsonTdsTaricVarukodRecord record : container.getTullTaxalist()){
			System.out.print(record.getSvvs_vatak() + " " + record.getSvvs_vata() + " ");
			System.out.println(record.getSvvs_txtk());
			
			/*BASE_URL = TdsUrlDataStore.TDS_FETCH_TARIC_VARUKODER_ITEMS_URL;
			urlRequestParamsKeys = "user=OSCAR&ie=E&kod=" + record.getSvvs_vata();
			jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
			*/
		}
	}
	
}
