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
import no.systema.tds.tdsexport.model.jsonjackson.topic.archive.JsonTdsExportSpecificTopicArchiveContainer;
import no.systema.tds.tdsexport.model.jsonjackson.topic.archive.JsonTdsExportSpecificTopicArchiveRecord;
import no.systema.tds.tdsexport.service.TdsExportSpecificTopicService;
import no.systema.tds.tdsexport.service.TdsExportSpecificTopicServiceImpl;
import no.systema.tds.tdsexport.url.store.TdsExportUrlDataStore;
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
			TdsExportSpecificTopicService service = new TdsExportSpecificTopicServiceImpl();
			
			//read payload and transform to UTF-8 since all Json strings require UTF-8 encoding
			String urlStr = TdsExportUrlDataStore.TDS_EXPORT_BASE_ARCHIVE_FOR_SPECIFIC_TOPIC_URL;
			String urlParameters = "user=JOVO&avd=1&opd=52919";
			UrlCgiProxyServiceImpl urlCgiProxyServiceImpl = new UrlCgiProxyServiceImpl();
			String payload = urlCgiProxyServiceImpl.getJsonContent(urlStr, urlParameters);
			System.out.println(payload);

			//At this point we now have an UTF-8 payload
			JsonTdsExportSpecificTopicArchiveContainer container = service.getTdsExportSpecificTopicArchiveContainer(payload);
			System.out.println(container.getUser());
			//list of objects
			Collection<JsonTdsExportSpecificTopicArchiveRecord> list = container.getArchiveElements();
			//Debug
			for(JsonTdsExportSpecificTopicArchiveRecord record : list){
				System.out.println("Archive url: " + record.getUrl());
				System.out.println("Archive subject: " + record.getSubject());
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
