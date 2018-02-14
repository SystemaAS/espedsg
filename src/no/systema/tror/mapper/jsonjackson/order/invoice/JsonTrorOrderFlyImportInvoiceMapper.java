/**
 * 
 */
package no.systema.tror.mapper.jsonjackson.order.invoice;

//jackson library
import org.apache.log4j.Logger;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper; 
//application library
import no.systema.tror.model.jsonjackson.order.invoice.JsonTrorOrderFlyImportInvoiceContainer;
import no.systema.tror.model.jsonjackson.order.invoice.JsonTrorOrderFlyImportInvoiceRecord;
//import no.systema.tror.model.jsonjackson.order.invoice.JsonTrorOrderLandImportInvoiceReadyMarkContainer;

/**
 * @author oscardelatorre
 * @date Feb 14, 2018
 * 
 */
public class JsonTrorOrderFlyImportInvoiceMapper {
	private static final Logger logger = Logger.getLogger(JsonTrorOrderFlyImportInvoiceMapper.class.getName());
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTrorOrderFlyImportInvoiceContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		//At this point we now have an UTF-8 payload
		JsonTrorOrderFlyImportInvoiceContainer container = mapper.readValue(utfPayload.getBytes(), JsonTrorOrderFlyImportInvoiceContainer.class); 
		//logger.info("[JSON-String payload status=OK]  " + container.getUser());
		if(container!=null && container.getInvoiceLineUpdate()!=null){
			for (JsonTrorOrderFlyImportInvoiceRecord record : container.getInvoiceLineUpdate()){
				//DEBUG
			}
		}else if(container!=null && container.getInvoiceLines()!=null){
			for (JsonTrorOrderFlyImportInvoiceRecord record : container.getInvoiceLines()){
				//DEBUG
			}
		}
		return container;
	}
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	/*
	public JsonTransportDispWorkflowSpecificOrderInvoiceReadyMarkContainer getReadyMarkContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		//At this point we now have an UTF-8 payload
		JsonTransportDispWorkflowSpecificOrderInvoiceReadyMarkContainer container = mapper.readValue(utfPayload.getBytes(), JsonTransportDispWorkflowSpecificOrderInvoiceReadyMarkContainer.class); 
		//logger.info("[JSON-String payload status=OK]  " + container.getUser());
		
		return container;
	}
	*/
}
