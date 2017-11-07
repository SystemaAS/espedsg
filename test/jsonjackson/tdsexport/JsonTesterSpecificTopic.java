/**
 * 
 */
package jsonjackson.tdsexport;

import java.io.*;   
import java.util.Collection;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper; 

//
import no.systema.main.util.EncodingTransformer;
import no.systema.main.util.io.FileUtil;
import no.systema.tds.tdsexport.model.jsonjackson.topic.JsonTdsExportSpecificTopicContainer;
import no.systema.tds.tdsexport.model.jsonjackson.topic.JsonTdsExportSpecificTopicRecord;
import no.systema.tds.util.*;

/**
 * 
 * @author oscardelatorre
 *
 */
public class JsonTesterSpecificTopic{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JsonTesterSpecificTopic engine = new JsonTesterSpecificTopic();
		engine.run();
		
		
	}

	/**
	 * 
	 */
	private void run (){
		try{
			ObjectMapper mapper = new ObjectMapper();  
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
			//read payload and transform to UTF-8 since all Json strings require UTF-8 encoding
			FileUtil fileUtil = new FileUtil();
			String testPayloadFile = "/Users/oscardelatorre/Documents/workspace/espedsg/test/SpecificTdsExportTopicJsonStringPayload.txt";
			//String testPayloadFile = "/Users/oscardelatorre/Documents/workspace/espedsg/test/testPayload.txt";
			String payload = fileUtil.readInputStreamAsString(new FileInputStream(testPayloadFile));
			EncodingTransformer transformer = new EncodingTransformer();
			String utfPayload = transformer.transformToJSONTargetEncoding(payload, "UTF-8");
			//At this point we now have an UTF-8 payload
			
			JsonTdsExportSpecificTopicContainer topicContainer = mapper.readValue(utfPayload.getBytes(), JsonTdsExportSpecificTopicContainer.class); 
			System.out.println(mapper.writeValueAsString(topicContainer));
			System.out.println("User: " + topicContainer.getUser());
			System.out.println("avd: " + topicContainer.getAvd());
			System.out.println("opd: " + topicContainer.getOpd());
			
			//list of objects (children of the Container)
			Collection<JsonTdsExportSpecificTopicRecord> fields = topicContainer.getOneorder();
			
			
			//Debug
			for(JsonTdsExportSpecificTopicRecord record : fields){
				System.out.println("SVEH_SYAV: " + record.getSveh_syav());
				System.out.println("SVEH_SYOP: " + record.getSveh_syop());
				System.out.println("SVEH_AVNA: " + record.getSveh_avna());
				System.out.println("SVEH_MONA: " + record.getSveh_mona());
				System.out.println("SVEH_ABUB: " + record.getSveh_abub());
				System.out.println("SVEH_GODM: " + record.getSveh_godm());
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
