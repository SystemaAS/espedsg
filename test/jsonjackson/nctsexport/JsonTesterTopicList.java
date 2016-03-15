/**
 * 
 */
package jsonjackson.nctsexport;

import java.io.*;   
import java.util.Collection;
//
import no.systema.main.service.UrlCgiProxyServiceImpl;
import no.systema.main.util.EncodingTransformer;
import no.systema.tds.nctsexport.model.jsonjackson.topic.JsonNctsExportTopicListContainer;
import no.systema.tds.nctsexport.model.jsonjackson.topic.JsonNctsExportTopicListRecord;
import no.systema.tds.nctsexport.service.NctsExportTopicListService;
import no.systema.tds.nctsexport.service.NctsExportTopicListServiceImpl;
import no.systema.tds.nctsexport.url.store.UrlDataStore;

/**
 * 
 * @author oscardelatorre
 *
 */
public class JsonTesterTopicList {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JsonTesterTopicList engine = new JsonTesterTopicList();
		engine.run();
		
		
	}

	/**
	 * 
	 */
	private void run (){
		
		
		try{
			
			NctsExportTopicListService tdsExportTopicListService = new NctsExportTopicListServiceImpl();
			
			//read payload and transform to UTF-8 since all Json strings require UTF-8 encoding
			String urlStr = UrlDataStore.NCTS_EXPORT_BASE_TOPICLIST_URL;
			String urlParameters = "user=OSCAR&avd=1&sign=CB";
			UrlCgiProxyServiceImpl urlCgiProxyServiceImpl = new UrlCgiProxyServiceImpl();
			String payload = urlCgiProxyServiceImpl.getJsonContent(urlStr, urlParameters);
			System.out.println(payload);
			EncodingTransformer transformer = new EncodingTransformer();
			String jsonPayload = transformer.transformToJSONTargetEncoding(payload, "UTF-8");
			//At this point we now have an UTF-8 payload
			JsonNctsExportTopicListContainer topicListContainer = tdsExportTopicListService.getNctsExportTopicListContainer(jsonPayload);
			System.out.println(topicListContainer.getUser());
			//list of objects
			Collection<JsonNctsExportTopicListRecord> list = topicListContainer.getOrderList();
			//Debug
			for(JsonNctsExportTopicListRecord topic : list){
				System.out.println("001: " + topic.getSign());
				System.out.println("003: " + topic.getOpd());
				System.out.println("002: " + topic.getMotNavn());
				System.out.println("mrnNr: " + topic.getMrnNr());
				System.out.println("lrnNr: " + topic.getLrnNr());
				System.out.println("bruttoVikt: " + topic.getBruttoVikt());
				System.out.println("forenklad: " + topic.getForenklad());
				System.out.println("elList: " + topic.getElList());
				System.out.println("kolli: " + topic.getKolli());
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
			
	}
	
			
}
