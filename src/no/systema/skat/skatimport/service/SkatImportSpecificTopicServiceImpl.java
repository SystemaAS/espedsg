/**
 * 
 */
package no.systema.skat.skatimport.service;



//SKAT
import no.systema.skat.skatimport.model.jsonjackson.topic.JsonSkatImportSpecificTopicContainer;
import no.systema.skat.skatimport.model.jsonjackson.topic.JsonSkatImportSpecificTopicRecalculationContainer;
import no.systema.skat.skatimport.model.jsonjackson.topic.JsonSkatImportTopicCopiedFromTransportUppdragContainer;
import no.systema.skat.skatimport.model.jsonjackson.topic.logging.JsonSkatImportSpecificTopicLoggingContainer;
import no.systema.skat.skatimport.model.jsonjackson.topic.logging.JsonSkatImportSpecificTopicLoggingLargeTextContainer;
import no.systema.skat.skatimport.model.jsonjackson.topic.JsonSkatImportSpecificTopicOmbudContainer;
import no.systema.skat.skatimport.model.jsonjackson.topic.JsonSkatImportTopicCopiedContainer;
import no.systema.skat.skatimport.mapper.jsonjackson.SkatImportTopicCopiedMapper;
import no.systema.skat.skatimport.mapper.jsonjackson.SkatImportSpecificTopicMapper;
import no.systema.skat.skatimport.mapper.jsonjackson.SkatImportTopicCopiedFromTransportUppdragMapper;
import no.systema.skat.skatimport.mapper.jsonjackson.SkatImportSpecificTopicLoggingMapper;
import no.systema.skat.skatimport.mapper.jsonjackson.SkatImportSpecificTopicLoggingLargeTextMapper;
import no.systema.skat.skatimport.mapper.jsonjackson.SkatImportSpecificTopicArchiveMapper;
import no.systema.skat.skatimport.model.jsonjackson.topic.archive.JsonSkatImportSpecificTopicArchiveContainer;

/**
 * @author oscardelatorre
 *
 */
public class SkatImportSpecificTopicServiceImpl implements SkatImportSpecificTopicService{
	/**
	 * 
	 * @param utfPayload
	 * @return
	 *  
	 */
	public JsonSkatImportSpecificTopicContainer getSkatImportSpecificTopicContainer(String utfPayload) {
		JsonSkatImportSpecificTopicContainer container = null;
		try{
			SkatImportSpecificTopicMapper mapper = new SkatImportSpecificTopicMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * 
	 */
	public JsonSkatImportSpecificTopicOmbudContainer getSkatImportSpecificTopicOmbudContainer(String utfPayload) {
		JsonSkatImportSpecificTopicOmbudContainer container = null;
		try{
			SkatImportSpecificTopicMapper mapper = new SkatImportSpecificTopicMapper();
			container = mapper.getOmbudContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}
	
	/**
	 * Archive
	 * @param utfPayload
	 * @return
	 */
	public JsonSkatImportSpecificTopicArchiveContainer getSkatImportSpecificTopicArchiveContainer(String utfPayload) {
		JsonSkatImportSpecificTopicArchiveContainer container = null;
		try{
			SkatImportSpecificTopicArchiveMapper mapper = new SkatImportSpecificTopicArchiveMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}

	/**
	 * Logging
	 * @param utfPayload
	 * @return
	 */
	public JsonSkatImportSpecificTopicLoggingContainer getSkatImportSpecificTopicLoggingContainer(String utfPayload) {
		JsonSkatImportSpecificTopicLoggingContainer container = null;
		try{
			SkatImportSpecificTopicLoggingMapper mapper = new SkatImportSpecificTopicLoggingMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}
	
	/**
	 * Show large text
	 * @param utfPayload
	 * @return
	 * 
	 */
	public JsonSkatImportSpecificTopicLoggingLargeTextContainer getSkatImportSpecificTopicLoggingLargeTextContainer(String utfPayload){
		JsonSkatImportSpecificTopicLoggingLargeTextContainer container = null;
		try{
			SkatImportSpecificTopicLoggingLargeTextMapper mapper = new SkatImportSpecificTopicLoggingLargeTextMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
	}
	
	/**
	 * Cloned record
	 * 
	 * @param utfPayload
	 * @return
	 * 
	 */
	public JsonSkatImportTopicCopiedContainer getSkatImportTopicCopiedContainer(String utfPayload){
		JsonSkatImportTopicCopiedContainer container = null;
		try{
			SkatImportTopicCopiedMapper mapper = new SkatImportTopicCopiedMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
	}
	
	/**
	 * @param utfPayload
	 * @return
	 * 
	 */
	public JsonSkatImportTopicCopiedFromTransportUppdragContainer getSkatImportTopicCopiedFromTransportUppdragContainer(String utfPayload){
		JsonSkatImportTopicCopiedFromTransportUppdragContainer container = null;
		try{
			SkatImportTopicCopiedFromTransportUppdragMapper mapper = new SkatImportTopicCopiedFromTransportUppdragMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;	
	}
	
	/**
	 * 
	 */
	public JsonSkatImportSpecificTopicRecalculationContainer getSkatImportSpecificTopicRecalculationContainer (String utfPayload){
		JsonSkatImportSpecificTopicRecalculationContainer container = null;
		try{
			SkatImportSpecificTopicMapper mapper = new SkatImportSpecificTopicMapper();
			container = mapper.getRecalculationContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
		
		
	}
	
}
