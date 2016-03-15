/**
 * 
 */
package jsonjackson.tdsexport;

import java.io.*;   
import java.util.Collection;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.DeserializationConfig;
//
import no.systema.main.service.UrlCgiProxyServiceImpl;
import no.systema.main.util.EncodingTransformer;
import no.systema.main.util.io.FileUtil;
import no.systema.tds.tdsexport.model.jsonjackson.*;
import no.systema.tds.tdsexport.model.jsonjackson.topic.logging.JsonTdsExportSpecificTopicLoggingLargeTextContainer;
import no.systema.tds.tdsexport.model.jsonjackson.topic.logging.JsonTdsExportSpecificTopicLoggingLargeTextRecord;
import no.systema.tds.tdsexport.service.TdsExportSpecificTopicService;
import no.systema.tds.tdsexport.service.TdsExportSpecificTopicServiceImpl;
import no.systema.tds.tdsexport.url.store.TdsExportUrlDataStore;
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
			TdsExportSpecificTopicService service = new TdsExportSpecificTopicServiceImpl();
			
			//read payload and transform to UTF-8 since all Json strings require UTF-8 encoding
			String urlStr = TdsExportUrlDataStore.TDS_EXPORT_BASE_LOG_LARGE_TEXT_FOR_SPECIFIC_TOPIC_URL;
			String urlParameters = "user=OSCAR&fmn=84278";
			UrlCgiProxyServiceImpl urlCgiProxyServiceImpl = new UrlCgiProxyServiceImpl();
			String payload = urlCgiProxyServiceImpl.getJsonContent(urlStr, urlParameters);
			System.out.println(payload);
//			EncodingTransformer transformer = new EncodingTransformer();
//			String jsonPayload = transformer.transformToJSONTargetEncoding(payload, "UTF-8");
			//At this point we now have an UTF-8 payload
			JsonTdsExportSpecificTopicLoggingLargeTextContainer container = service.getTdsExportSpecificTopicLoggingLargeTextContainer(payload);
			System.out.println(container.getUser());
			//list of objects
			Collection<JsonTdsExportSpecificTopicLoggingLargeTextRecord> list = container.getLoggtext();
			//Debug
			for(JsonTdsExportSpecificTopicLoggingLargeTextRecord record : list){
				System.out.println("text1: " + record.getF0078a());
				System.out.println("text2: " + record.getF0078b());
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
