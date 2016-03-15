/**
 * 
 */
package no.systema.transportdisp.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.transportdisp.model.jsonjackson.workflow.order.invoice.JsonTransportDispWorkflowSpecificOrderInvoiceContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.invoice.JsonTransportDispWorkflowSpecificOrderInvoiceRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.order.invoice.JsonTransportDispWorkflowSpecificOrderInvoiceReadyMarkContainer;

/**
 * @author oscardelatorre
 * @date Aug 14, 2015
 * 
 */
public class JsonTransportDispWorkflowSpecificOrderInvoiceMapper {
	private static final Logger logger = Logger.getLogger(JsonTransportDispWorkflowSpecificOrderInvoiceMapper.class.getName());
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTransportDispWorkflowSpecificOrderInvoiceContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		//At this point we now have an UTF-8 payload
		JsonTransportDispWorkflowSpecificOrderInvoiceContainer container = mapper.readValue(utfPayload.getBytes(), JsonTransportDispWorkflowSpecificOrderInvoiceContainer.class); 
		//logger.info("[JSON-String payload status=OK]  " + container.getUser());
		if(container!=null && container.getInvoiceLineUpdate()!=null){
			for (JsonTransportDispWorkflowSpecificOrderInvoiceRecord record : container.getInvoiceLineUpdate()){
				//DEBUG
			}
		}else if(container!=null && container.getInvoiceLines()!=null){
			for (JsonTransportDispWorkflowSpecificOrderInvoiceRecord record : container.getInvoiceLines()){
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
	public JsonTransportDispWorkflowSpecificOrderInvoiceReadyMarkContainer getReadyMarkContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		//At this point we now have an UTF-8 payload
		JsonTransportDispWorkflowSpecificOrderInvoiceReadyMarkContainer container = mapper.readValue(utfPayload.getBytes(), JsonTransportDispWorkflowSpecificOrderInvoiceReadyMarkContainer.class); 
		//logger.info("[JSON-String payload status=OK]  " + container.getUser());
		
		return container;
	}
	
}
