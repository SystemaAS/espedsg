/**
 * 
 */
package no.systema.skat.nctsexport.service;

import no.systema.skat.nctsexport.model.jsonjackson.topic.JsonSkatNctsExportSpecificTopicContainer;
import no.systema.skat.nctsexport.model.jsonjackson.topic.archive.JsonSkatNctsExportSpecificTopicArchiveContainer;
import no.systema.skat.nctsexport.model.jsonjackson.topic.logging.JsonSkatNctsExportSpecificTopicLoggingContainer;
import no.systema.skat.nctsexport.model.jsonjackson.topic.logging.JsonSkatNctsExportSpecificTopicLoggingLargeTextContainer;
import no.systema.skat.nctsexport.model.jsonjackson.topic.validation.JsonSkatNctsExportSpecificTopicGuaranteeValidatorContainer;
import no.systema.skat.nctsexport.model.jsonjackson.topic.JsonSkatNctsExportTopicCopiedContainer;

/**
 * 
 * @author oscardelatorre
 * @date Apr 14, 2014
 * 
 *
 */
public interface SkatNctsExportSpecificTopicService {
	public JsonSkatNctsExportSpecificTopicContainer getNctsExportSpecificTopicContainer(String utfPayload);
	public JsonSkatNctsExportSpecificTopicArchiveContainer getNctsExportSpecificTopicArchiveContainer(String utfPayload);
	public JsonSkatNctsExportSpecificTopicLoggingContainer getNctsExportSpecificTopicLoggingContainer(String utfPayload);
	public JsonSkatNctsExportSpecificTopicLoggingLargeTextContainer getNctsExportSpecificTopicLoggingLargeTextContainer(String utfPayload);
	public JsonSkatNctsExportSpecificTopicGuaranteeValidatorContainer getNctsExportSpecificTopicGuaranteeValidatorContainer(String utfPayload);
	public JsonSkatNctsExportTopicCopiedContainer getNctsExportTopicCopiedContainer(String utfPayload);
}
