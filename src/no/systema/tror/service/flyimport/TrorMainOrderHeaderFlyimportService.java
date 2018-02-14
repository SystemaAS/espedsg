/**
 * 
 */
package no.systema.tror.service.flyimport;

import no.systema.tror.model.jsonjackson.order.invoice.JsonTrorOrderFlyImportInvoiceContainer;
//import no.systema.tror.model.jsonjackson.order.invoice.JsonTrorOrderInvoiceReadyMarkContainer;
import no.systema.tror.model.jsonjackson.JsonTrorOrderHeaderContainer;
import no.systema.tror.model.jsonjackson.JsonTrorOrderHeaderDummyContainer;


/**
 * 
 * @author oscardelatorre
 * @date Feb 13, 2018
 * 
 *
 */
public interface TrorMainOrderHeaderFlyimportService {
	public JsonTrorOrderHeaderContainer getOrderHeaderContainer(String utfPayload);
	public JsonTrorOrderHeaderDummyContainer getOrderHeaderDummyContainer(String utfPayload);
	public JsonTrorOrderFlyImportInvoiceContainer getOrderInvoiceContainer(String utfPayload);
	public JsonTrorOrderHeaderContainer getOrderHeaderContainerStatusUpdate(String utfPayload);
	//public JsonTransportDispWorkflowSpecificOrderInvoiceReadyMarkContainer getOrderInvoiceReadyMarkContainer(String utfPayload);
	

}
