/**
 * 
 */
package no.systema.skat.skatexport.service;


//SKAT
import no.systema.skat.skatexport.model.jsonjackson.topic.archive.JsonSkatExportSpecificTopicArchiveContainer;
import no.systema.skat.skatexport.model.jsonjackson.topic.logging.JsonSkatExportSpecificTopicLoggingContainer;
import no.systema.skat.skatexport.model.jsonjackson.topic.logging.JsonSkatExportSpecificTopicLoggingLargeTextContainer;
import no.systema.skat.skatexport.model.jsonjackson.topic.JsonSkatExportSpecificTopicContainer;
import no.systema.skat.skatexport.model.jsonjackson.topic.JsonSkatExportTopicCopiedContainer;
import no.systema.skat.skatexport.model.jsonjackson.topic.JsonSkatExportTopicCopiedFromTransportUppdragContainer;
import no.systema.skat.skatexport.mapper.jsonjackson.SkatExportTopicCopiedMapper;
import no.systema.skat.skatexport.mapper.jsonjackson.SkatExportSpecificTopicMapper;
import no.systema.skat.skatexport.mapper.jsonjackson.SkatExportSpecificTopicArchiveMapper;
import no.systema.skat.skatexport.mapper.jsonjackson.SkatExportSpecificTopicLoggingMapper;
import no.systema.skat.skatexport.mapper.jsonjackson.SkatExportSpecificTopicLoggingLargeTextMapper;
import no.systema.skat.skatexport.mapper.jsonjackson.SkatExportTopicCopiedFromTransportUppdragMapper;
import no.systema.skat.skatexport.model.jsonjackson.topic.JsonSkatExportSpecificTopicOmbudContainer;
import no.systema.skat.skatexport.mapper.jsonjackson.SkatExportTopicInvoiceMapper;
import no.systema.skat.skatexport.model.jsonjackson.topic.JsonSkatExportTopicInvoiceContainer;
import no.systema.skat.skatexport.model.jsonjackson.topic.JsonSkatExportTopicInvoiceExternalContainer;
import no.systema.skat.skatexport.model.jsonjackson.topic.JsonSkatExportTopicInvoiceExternalForUpdateContainer;
import no.systema.skat.skatexport.mapper.jsonjackson.SkatExportSpecificTopicMapper;
import no.systema.skat.skatexport.model.jsonjackson.topic.JsonSkatExportSpecificTopicFaktTotalContainer;


/**
 * 
 * @author oscardelatorre
 *
 */
public class SkatExportSpecificTopicServiceImpl implements SkatExportSpecificTopicService{
	/**
	 * 
	 * @param utfPayload
	 * @return
	 *  
	 */
	public JsonSkatExportSpecificTopicContainer getSkatExportSpecificTopicContainer(String utfPayload) {
		JsonSkatExportSpecificTopicContainer container = null;
		try{
			SkatExportSpecificTopicMapper mapper = new SkatExportSpecificTopicMapper();
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
	 */
	public JsonSkatExportSpecificTopicFaktTotalContainer getSkatExportSpecificTopicFaktTotalContainer (String utfPayload){
		JsonSkatExportSpecificTopicFaktTotalContainer container = null;
		try{
			SkatExportSpecificTopicMapper mapper = new SkatExportSpecificTopicMapper();
			container = mapper.getFaktTotalContainer(utfPayload);
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
	public JsonSkatExportSpecificTopicOmbudContainer getSkatExportSpecificTopicOmbudContainer(String utfPayload) {
		JsonSkatExportSpecificTopicOmbudContainer container = null;
		try{
			SkatExportSpecificTopicMapper mapper = new SkatExportSpecificTopicMapper();
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
	public JsonSkatExportSpecificTopicArchiveContainer getSkatExportSpecificTopicArchiveContainer(String utfPayload) {
		JsonSkatExportSpecificTopicArchiveContainer container = null;
		try{
			SkatExportSpecificTopicArchiveMapper mapper = new SkatExportSpecificTopicArchiveMapper();
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
	public JsonSkatExportSpecificTopicLoggingContainer getSkatExportSpecificTopicLoggingContainer(String utfPayload) {
		JsonSkatExportSpecificTopicLoggingContainer container = null;
		try{
			SkatExportSpecificTopicLoggingMapper mapper = new SkatExportSpecificTopicLoggingMapper();
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
	public JsonSkatExportSpecificTopicLoggingLargeTextContainer getSkatExportSpecificTopicLoggingLargeTextContainer(String utfPayload){
		JsonSkatExportSpecificTopicLoggingLargeTextContainer container = null;
		try{
			SkatExportSpecificTopicLoggingLargeTextMapper mapper = new SkatExportSpecificTopicLoggingLargeTextMapper();
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
	public JsonSkatExportTopicCopiedContainer getSkatExportTopicCopiedContainer(String utfPayload){
		JsonSkatExportTopicCopiedContainer container = null;
		try{
			SkatExportTopicCopiedMapper mapper = new SkatExportTopicCopiedMapper();
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
	public JsonSkatExportTopicCopiedFromTransportUppdragContainer getSkatExportTopicCopiedFromTransportUppdragContainer(String utfPayload){
		JsonSkatExportTopicCopiedFromTransportUppdragContainer container = null;
		try{
			SkatExportTopicCopiedFromTransportUppdragMapper mapper = new SkatExportTopicCopiedFromTransportUppdragMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;	
	}
	
	
	/**
	 * 
	 */
	public JsonSkatExportTopicInvoiceContainer getSkatExportTopicInvoiceContainerContainer (String utfPayload){
		JsonSkatExportTopicInvoiceContainer container = null;
		try{
			SkatExportTopicInvoiceMapper mapper = new SkatExportTopicInvoiceMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
	}
	/**
	 * 
	 */
	public JsonSkatExportTopicInvoiceContainer getSkatExportTopicInvoiceContainerOneInvoice (String utfPayload){
		JsonSkatExportTopicInvoiceContainer container = null;
		try{
			SkatExportTopicInvoiceMapper mapper = new SkatExportTopicInvoiceMapper();
			container = mapper.getContainerOneInvoice(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
	}
	
	/**
	 * External invoices
	 */
	public JsonSkatExportTopicInvoiceExternalContainer getSkatExportTopicInvoiceContainerContainerExternal (String utfPayload){
		JsonSkatExportTopicInvoiceExternalContainer container = null;
		try{
			SkatExportTopicInvoiceMapper mapper = new SkatExportTopicInvoiceMapper();
			container = mapper.getContainerInvoiceExternal(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
	}
	/**
	 * External invoices
	 * 
	 */
	public JsonSkatExportTopicInvoiceExternalContainer getSkatExportTopicInvoiceContainerOneInvoiceExternal (String utfPayload){
		JsonSkatExportTopicInvoiceExternalContainer container = null;
		try{
			SkatExportTopicInvoiceMapper mapper = new SkatExportTopicInvoiceMapper();
			container = mapper.getContainerOneInvoiceInvoiceExternal(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
		
	}
	
	/**
	 * Update of external invoice (import it to normal invoice list)
	 * @param utfPayload
	 * 
	 */
	public JsonSkatExportTopicInvoiceExternalForUpdateContainer getSkatExportTopicInvoiceContainerOneInvoiceExternalForUpdate (String utfPayload){
		JsonSkatExportTopicInvoiceExternalForUpdateContainer container = null;
		try{
			SkatExportTopicInvoiceMapper mapper = new SkatExportTopicInvoiceMapper();
			container = mapper.getContainerOneInvoiceInvoiceExternalForUpdate(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
	}
	
	
}
