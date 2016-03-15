/**
 * 
 */
package no.systema.skat.nctsimport.service;

import no.systema.skat.nctsimport.mapper.jsonjackson.SkatNctsImportSpecificTopicArchiveMapper;
import no.systema.skat.nctsimport.mapper.jsonjackson.SkatNctsImportSpecificTopicLoggingLargeTextMapper;
import no.systema.skat.nctsimport.mapper.jsonjackson.SkatNctsImportSpecificTopicLoggingMapper;
import no.systema.skat.nctsimport.mapper.jsonjackson.SkatNctsImportSpecificTopicMapper;
import no.systema.skat.nctsimport.model.jsonjackson.topic.archive.JsonSkatNctsImportSpecificTopicArchiveContainer;
import no.systema.skat.nctsimport.model.jsonjackson.topic.logging.JsonSkatNctsImportSpecificTopicLoggingContainer;
import no.systema.skat.nctsimport.model.jsonjackson.topic.logging.JsonSkatNctsImportSpecificTopicLoggingLargeTextContainer;
import no.systema.skat.nctsimport.model.jsonjackson.topic.JsonSkatNctsImportSpecificTopicContainer;

/**
 * @author oscardelatorre
 * @date Apr 24, 2014
 */
public class SkatNctsImportSpecificTopicServiceImpl implements SkatNctsImportSpecificTopicService{
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * 
	 * 
	 */
	public JsonSkatNctsImportSpecificTopicContainer getNctsImportSpecificTopicContainer(String utfPayload) {
		JsonSkatNctsImportSpecificTopicContainer container = null;
		try{
			SkatNctsImportSpecificTopicMapper mapper = new SkatNctsImportSpecificTopicMapper();
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
	public JsonSkatNctsImportSpecificTopicLoggingContainer getNctsImportSpecificTopicLoggingContainer(String utfPayload) {
		JsonSkatNctsImportSpecificTopicLoggingContainer container = null;
		try{
			SkatNctsImportSpecificTopicLoggingMapper mapper = new SkatNctsImportSpecificTopicLoggingMapper();
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
	public JsonSkatNctsImportSpecificTopicLoggingLargeTextContainer getNctsImportSpecificTopicLoggingLargeTextContainer(String utfPayload){
		JsonSkatNctsImportSpecificTopicLoggingLargeTextContainer container = null;
		try{
			SkatNctsImportSpecificTopicLoggingLargeTextMapper mapper = new SkatNctsImportSpecificTopicLoggingLargeTextMapper();
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
	public JsonSkatNctsImportSpecificTopicArchiveContainer getNctsImportSpecificTopicArchiveContainer(String utfPayload) {
		JsonSkatNctsImportSpecificTopicArchiveContainer container = null;
		try{
			SkatNctsImportSpecificTopicArchiveMapper mapper = new SkatNctsImportSpecificTopicArchiveMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}	

	
}
