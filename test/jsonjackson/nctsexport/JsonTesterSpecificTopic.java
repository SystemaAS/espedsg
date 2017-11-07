/**
 * 
 */
package jsonjackson.nctsexport;

import java.io.*;   
import java.util.Collection;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper; 

//
import no.systema.main.util.EncodingTransformer;
import no.systema.main.util.io.FileUtil;
import no.systema.tds.nctsexport.model.jsonjackson.topic.JsonNctsExportSpecificTopicContainer;
import no.systema.tds.nctsexport.model.jsonjackson.topic.JsonNctsExportSpecificTopicRecord;
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
			String testPayloadFile = "/Users/oscardelatorre/Documents/workspace/espedsg/test/SpecificNctsExportTopicJsonStringPayload.txt";
			//String testPayloadFile = "/Users/oscardelatorre/Documents/workspace/espedsg/test/testPayload.txt";
			String payload = fileUtil.readInputStreamAsString(new FileInputStream(testPayloadFile));
			EncodingTransformer transformer = new EncodingTransformer();
			String utfPayload = transformer.transformToJSONTargetEncoding(payload, "UTF-8");
			//At this point we now have an UTF-8 payload
			
			JsonNctsExportSpecificTopicContainer topicContainer = mapper.readValue(utfPayload.getBytes(), JsonNctsExportSpecificTopicContainer.class); 
			System.out.println(mapper.writeValueAsString(topicContainer));
			System.out.println("User: " + topicContainer.getUser());
			System.out.println("avd: " + topicContainer.getAvd());
			System.out.println("opd: " + topicContainer.getOpd());
			
			//list of objects (children of the Container)
			Collection<JsonNctsExportSpecificTopicRecord> fields = topicContainer.getOneorder();
			
			
			//Debug
			for(JsonNctsExportSpecificTopicRecord record : fields){
				System.out.println("THKNS: " + record.getThkns());
				System.out.println("THNAS: " + record.getThnas());
				System.out.println("THADS1: " + record.getThads1());
				System.out.println("THPNS: " + record.getThpns());
				System.out.println("THPSS: " + record.getThpss());
				System.out.println("THLKS: " + record.getThlks());
				System.out.println("THSKS: " + record.getThsks());
				System.out.println("THTINS: " + record.getThtins());
				//mottagare
				System.out.println("========== ");
				System.out.println("MOTTAGARE ");
				System.out.println("THKNK: " + record.getThknk());
				System.out.println("THNAK: " + record.getThnak());
				System.out.println("THADK1: " + record.getThadk1());
				System.out.println("THPNK: " + record.getThpnk());
				System.out.println("THPSK: " + record.getThpsk());
				System.out.println("THLKK: " + record.getThlkk());
				System.out.println("THSKK: " + record.getThskk());
				System.out.println("THTINK: " + record.getThtink());
				
				System.out.println("========== ");
				System.out.println("ANSVARIG ");
				System.out.println("THNAA: " + record.getThnaa());
				System.out.println("THADA1: " + record.getThada1());
				System.out.println("THPNA: " + record.getThpna());
				System.out.println("THPSA: " + record.getThpsa());
				System.out.println("THLKA: " + record.getThlka());
				System.out.println("THSKA: " + record.getThska());
				System.out.println("THTINA: " + record.getThtina());
				
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
