/**
 * 
 */
package no.systema.tror.service.landimport;

import no.systema.tror.model.jsonjackson.order.invoice.JsonTrorOrderLandImportInvoiceContainer;
//import no.systema.tror.model.jsonjackson.order.invoice.JsonTrorOrderInvoiceReadyMarkContainer;
import no.systema.tror.model.jsonjackson.JsonTrorOrderHeaderContainer;


/**
 * 
 * @author oscardelatorre
 * @date Aug 11, 2017
 * 
 *
 */
public interface TrorMainOrderHeaderLandimportService {
	public JsonTrorOrderHeaderContainer getOrderHeaderContainer(String utfPayload);
	public JsonTrorOrderLandImportInvoiceContainer getOrderInvoiceContainer(String utfPayload);
	//public JsonTransportDispWorkflowSpecificOrderInvoiceReadyMarkContainer getOrderInvoiceReadyMarkContainer(String utfPayload);
	

}
