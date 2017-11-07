/**
 * 
 */
package jsonjackson.tdsexport;

import java.io.*;   
import java.util.Collection;
//
import no.systema.main.service.UrlCgiProxyServiceImpl;
import no.systema.main.util.EncodingTransformer;
import no.systema.main.util.io.FileUtil;
import no.systema.tds.tdsexport.model.jsonjackson.*;
import no.systema.tds.tdsexport.model.jsonjackson.topic.JsonTdsExportTopicListContainer;
import no.systema.tds.tdsexport.model.jsonjackson.topic.JsonTdsExportTopicListRecord;
import no.systema.tds.tdsexport.service.TdsExportTopicListService;
import no.systema.tds.tdsexport.service.TdsExportTopicListServiceImpl;
import no.systema.tds.tdsexport.url.store.TdsExportUrlDataStore;
import no.systema.tds.util.*;

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
			TdsExportTopicListService tdsExportTopicListService = new TdsExportTopicListServiceImpl();
			
			//read payload and transform to UTF-8 since all Json strings require UTF-8 encoding
			String urlStr = TdsExportUrlDataStore.TDS_EXPORT_BASE_TOPICLIST_URL;
			String urlParameters = "user=OSCAR&avd=1&sign=CB";
			UrlCgiProxyServiceImpl urlCgiProxyServiceImpl = new UrlCgiProxyServiceImpl();
			String payload = urlCgiProxyServiceImpl.getJsonContent(urlStr, urlParameters);
			System.out.println(payload);
			EncodingTransformer transformer = new EncodingTransformer();
			String jsonPayload = transformer.transformToJSONTargetEncoding(payload, "UTF-8");
			//At this point we now have an UTF-8 payload
			JsonTdsExportTopicListContainer topicListContainer = tdsExportTopicListService.getTdsExportTopicListContainer(jsonPayload);
			System.out.println("User: " + topicListContainer.getUser());
			
			//list of objects
			Collection<JsonTdsExportTopicListRecord> list = topicListContainer.getOrderList();
			//Debug
			for(JsonTdsExportTopicListRecord topic : list){
				System.out.println("avsNamn: " + topic.getAvsNavn());
				System.out.println("motNamn: " + topic.getMotNavn());
				System.out.println("opdNamn: " + topic.getOpd());
				
				
				System.out.println("TullId: " + topic.getTullId());
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
