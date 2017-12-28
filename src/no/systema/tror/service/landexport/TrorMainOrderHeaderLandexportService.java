/**
 * 
 */
package no.systema.tror.service.landexport;

import no.systema.tror.model.jsonjackson.order.invoice.JsonTrorOrderLandExportInvoiceContainer;
//import no.systema.tror.model.jsonjackson.order.invoice.JsonTrorOrderInvoiceReadyMarkContainer;
import no.systema.tror.model.jsonjackson.JsonTrorOrderHeaderContainer;
import no.systema.tror.model.jsonjackson.JsonTrorOrderHeaderDummyContainer;


/**
 * 
 * @author oscardelatorre
 * @date Dec 28, 2017
 * 
 *
 */
public interface TrorMainOrderHeaderLandexportService {
	public JsonTrorOrderHeaderContainer getOrderHeaderContainer(String utfPayload);
	public JsonTrorOrderHeaderDummyContainer getOrderHeaderDummyContainer(String utfPayload);
	public JsonTrorOrderLandExportInvoiceContainer getOrderInvoiceContainer(String utfPayload);
	public JsonTrorOrderHeaderContainer getOrderHeaderContainerStatusUpdate(String utfPayload);
	//public JsonTransportDispWorkflowSpecificOrderInvoiceReadyMarkContainer getOrderInvoiceReadyMarkContainer(String utfPayload);
	

}
