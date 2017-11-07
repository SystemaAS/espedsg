/**
 * 
 */
package jsonjackson.nctsexport;

import java.io.*;   
import java.util.Collection;
//
import no.systema.main.service.UrlCgiProxyServiceImpl;
import no.systema.main.util.EncodingTransformer;
import no.systema.main.util.io.FileUtil;
import no.systema.tds.nctsexport.model.jsonjackson.*;
import no.systema.tds.nctsexport.model.jsonjackson.topic.logging.JsonNctsExportSpecificTopicLoggingLargeTextContainer;
import no.systema.tds.nctsexport.model.jsonjackson.topic.logging.JsonNctsExportSpecificTopicLoggingLargeTextRecord;
import no.systema.tds.nctsexport.service.NctsExportSpecificTopicService;
import no.systema.tds.nctsexport.service.NctsExportSpecificTopicServiceImpl;
import no.systema.tds.nctsexport.url.store.UrlDataStore;
import no.systema.tds.util.*;

/**
 * 
 * @author oscardelatorre
 *
 */
public class JsonTesterTopicLoggingLargeText {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JsonTesterTopicLoggingLargeText engine = new JsonTesterTopicLoggingLargeText();
		engine.run();
		
		
	}

	/**
	 * 
	 */
	private void run (){
		try{
			NctsExportSpecificTopicService service = new NctsExportSpecificTopicServiceImpl();
			
			//read payload and transform to UTF-8 since all Json strings require UTF-8 encoding
			String urlStr = UrlDataStore.NCTS_EXPORT_BASE_LOG_LARGE_TEXT_FOR_SPECIFIC_TOPIC_URL;
			String urlParameters = "user=OSCAR&fmn=85847";
			UrlCgiProxyServiceImpl urlCgiProxyServiceImpl = new UrlCgiProxyServiceImpl();
			String payload = urlCgiProxyServiceImpl.getJsonContent(urlStr, urlParameters);
			System.out.println(payload);
//			EncodingTransformer transformer = new EncodingTransformer();
//			String jsonPayload = transformer.transformToJSONTargetEncoding(payload, "UTF-8");
			//At this point we now have an UTF-8 payload
			JsonNctsExportSpecificTopicLoggingLargeTextContainer container = service.getNctsExportSpecificTopicLoggingLargeTextContainer(payload);
			System.out.println(container.getUser());
			//list of objects
			Collection<JsonNctsExportSpecificTopicLoggingLargeTextRecord> list = container.getLoggtext();
			//Debug
			for(JsonNctsExportSpecificTopicLoggingLargeTextRecord record : list){
				System.out.println("text1: " + record.getF0078a());
				System.out.println("text2: " + record.getF0078b());
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
