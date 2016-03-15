/**
 * 
 */
package jsonjackson.nctsexport;

import java.io.*;   
import java.util.Collection;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.DeserializationConfig;
//
import no.systema.main.service.UrlCgiProxyServiceImpl;
import no.systema.main.util.EncodingTransformer;
import no.systema.main.util.io.FileUtil;
import no.systema.tds.nctsexport.model.jsonjackson.*;
import no.systema.tds.nctsexport.model.jsonjackson.topic.archive.JsonNctsExportSpecificTopicArchiveContainer;
import no.systema.tds.nctsexport.model.jsonjackson.topic.archive.JsonNctsExportSpecificTopicArchiveRecord;
import no.systema.tds.nctsexport.service.NctsExportSpecificTopicService;
import no.systema.tds.nctsexport.service.NctsExportSpecificTopicServiceImpl;
import no.systema.tds.nctsexport.url.store.UrlDataStore;
import no.systema.tds.util.*;

/**
 * 
 * @author oscardelatorre
 *
 */
public class JsonTesterTopicArchive {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JsonTesterTopicArchive engine = new JsonTesterTopicArchive();
		engine.run();
		
		
	}

	/**
	 * 
	 */
	private void run (){
		try{
			NctsExportSpecificTopicService service = new NctsExportSpecificTopicServiceImpl();
			
			//read payload and transform to UTF-8 since all Json strings require UTF-8 encoding
			String urlStr = UrlDataStore.NCTS_EXPORT_BASE_ARCHIVE_FOR_SPECIFIC_TOPIC_URL;
			String urlParameters = "user=JOVO&avd=1&opd=52919";
			UrlCgiProxyServiceImpl urlCgiProxyServiceImpl = new UrlCgiProxyServiceImpl();
			String payload = urlCgiProxyServiceImpl.getJsonContent(urlStr, urlParameters);
			System.out.println(payload);

			//At this point we now have an UTF-8 payload
			JsonNctsExportSpecificTopicArchiveContainer container = service.getNctsExportSpecificTopicArchiveContainer(payload);
			System.out.println(container.getUser());
			//list of objects
			Collection<JsonNctsExportSpecificTopicArchiveRecord> list = container.getArchiveElements();
			//Debug
			for(JsonNctsExportSpecificTopicArchiveRecord record : list){
				System.out.println("Archive url: " + record.getUrl());
				System.out.println("Archive subject: " + record.getSubject());
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
