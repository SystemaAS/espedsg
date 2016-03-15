/**
 * 
 */
package no.systema.skat.nctsimport.service;

import no.systema.skat.nctsimport.model.jsonjackson.topic.archive.JsonSkatNctsImportSpecificTopicArchiveContainer;
import no.systema.skat.nctsimport.model.jsonjackson.topic.logging.JsonSkatNctsImportSpecificTopicLoggingContainer;
import no.systema.skat.nctsimport.model.jsonjackson.topic.logging.JsonSkatNctsImportSpecificTopicLoggingLargeTextContainer;
import no.systema.skat.nctsimport.model.jsonjackson.topic.JsonSkatNctsImportSpecificTopicContainer;


/**
 * 
 * @author oscardelatorre
 * @date Apr 24, 2014
 * 
 */
public interface SkatNctsImportSpecificTopicService {
	public JsonSkatNctsImportSpecificTopicContainer getNctsImportSpecificTopicContainer(String utfPayload);
	public JsonSkatNctsImportSpecificTopicArchiveContainer getNctsImportSpecificTopicArchiveContainer(String utfPayload);
	public JsonSkatNctsImportSpecificTopicLoggingContainer getNctsImportSpecificTopicLoggingContainer(String utfPayload);
	public JsonSkatNctsImportSpecificTopicLoggingLargeTextContainer getNctsImportSpecificTopicLoggingLargeTextContainer(String utfPayload);
	
}
