/**
 * 
 */
package no.systema.skat.skatimport.service;

import no.systema.skat.skatimport.model.jsonjackson.topic.JsonSkatImportTopicInvoiceContainer;
import no.systema.skat.skatimport.model.jsonjackson.topic.JsonSkatImportTopicInvoiceExternalContainer;
import no.systema.skat.skatimport.model.jsonjackson.topic.JsonSkatImportTopicInvoiceExternalForUpdateContainer;
import no.systema.skat.skatimport.model.jsonjackson.topic.archive.JsonSkatImportSpecificTopicArchiveContainer;

//
import no.systema.skat.skatimport.model.jsonjackson.topic.JsonSkatImportSpecificTopicContainer;
import no.systema.skat.skatimport.model.jsonjackson.topic.JsonSkatImportTopicCopiedFromTransportUppdragContainer;
import no.systema.skat.skatimport.model.jsonjackson.topic.logging.JsonSkatImportSpecificTopicLoggingContainer;
import no.systema.skat.skatimport.model.jsonjackson.topic.logging.JsonSkatImportSpecificTopicLoggingLargeTextContainer;
import no.systema.skat.skatimport.model.jsonjackson.topic.JsonSkatImportSpecificTopicOmbudContainer;
import no.systema.skat.skatimport.model.jsonjackson.topic.JsonSkatImportTopicCopiedContainer;
import no.systema.skat.skatimport.model.jsonjackson.topic.JsonSkatImportSpecificTopicRecalculationContainer;



/**
 * @author oscardelatorre
 * @date Jan 27, 2014
 *
 * 
 */

public interface SkatImportSpecificTopicService {
	public JsonSkatImportSpecificTopicContainer getSkatImportSpecificTopicContainer(String utfPayload);
	public JsonSkatImportSpecificTopicLoggingContainer getSkatImportSpecificTopicLoggingContainer(String utfPayload);
	public JsonSkatImportSpecificTopicLoggingLargeTextContainer getSkatImportSpecificTopicLoggingLargeTextContainer(String utfPayload);
	public JsonSkatImportTopicCopiedFromTransportUppdragContainer getSkatImportTopicCopiedFromTransportUppdragContainer(String utfPayload);
	public JsonSkatImportSpecificTopicOmbudContainer getSkatImportSpecificTopicOmbudContainer (String utfPayload);
	public JsonSkatImportTopicCopiedContainer getSkatImportTopicCopiedContainer(String utfPayload);
	public JsonSkatImportSpecificTopicArchiveContainer getSkatImportSpecificTopicArchiveContainer(String utfPayload);
	//
	public JsonSkatImportSpecificTopicRecalculationContainer getSkatImportSpecificTopicRecalculationContainer (String utfpayload);
	
	//Invoices
	public JsonSkatImportTopicInvoiceContainer getSkatImportTopicInvoiceContainerContainer (String utfPayload);
	public JsonSkatImportTopicInvoiceContainer getSkatImportTopicInvoiceContainerOneInvoice (String utfPayload);
	//External invoices
	public JsonSkatImportTopicInvoiceExternalContainer getSkatImportTopicInvoiceContainerContainerExternal (String utfPayload);
	public JsonSkatImportTopicInvoiceExternalContainer getSkatImportTopicInvoiceContainerOneInvoiceExternal (String utfPayload);
	public JsonSkatImportTopicInvoiceExternalForUpdateContainer getSkatImportTopicInvoiceContainerOneInvoiceExternalForUpdate (String utfPayload);
	
}
