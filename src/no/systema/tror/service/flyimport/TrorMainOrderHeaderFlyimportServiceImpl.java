/**
 * 
 */
package no.systema.tror.service.flyimport;

import no.systema.tror.mapper.jsonjackson.JsonTrorOrderHeaderMapperFlyimport;
import no.systema.tror.mapper.jsonjackson.order.invoice.JsonTrorOrderFlyImportInvoiceMapper;

import no.systema.tror.model.jsonjackson.order.invoice.JsonTrorOrderFlyImportInvoiceContainer;
import no.systema.tror.service.flyimport.TrorMainOrderHeaderFlyimportService;
import no.systema.tror.model.jsonjackson.JsonTrorOrderHeaderContainer;
import no.systema.tror.model.jsonjackson.JsonTrorOrderHeaderDummyContainer;

/**
 * 
 * @author oscardelatorre
 * @date Feb 13, 2018
 * 
 * 
 */
public class TrorMainOrderHeaderFlyimportServiceImpl implements TrorMainOrderHeaderFlyimportService {

	/**
	 * Gets defualt values from HEDUMMY/MEMBER (TABLE)
	 */
	public JsonTrorOrderHeaderDummyContainer getOrderHeaderDummyContainer(String utfPayload){
		JsonTrorOrderHeaderDummyContainer container = null;
		try{
			JsonTrorOrderHeaderMapperFlyimport mapper = new JsonTrorOrderHeaderMapperFlyimport();
			container = mapper.getDummyContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
	}
	/**
	 * 
	 */
	public JsonTrorOrderHeaderContainer getOrderHeaderContainer(String utfPayload) {
		JsonTrorOrderHeaderContainer container = null;
		try{
			JsonTrorOrderHeaderMapperFlyimport mapper = new JsonTrorOrderHeaderMapperFlyimport();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
	}
	/**
	 * 
	 */
	public JsonTrorOrderFlyImportInvoiceContainer getOrderInvoiceContainer(String utfPayload) {
		JsonTrorOrderFlyImportInvoiceContainer container = null;
		try{
			JsonTrorOrderFlyImportInvoiceMapper mapper = new JsonTrorOrderFlyImportInvoiceMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
	}
	/**
	 * 
	 */
	public JsonTrorOrderHeaderContainer getOrderHeaderContainerStatusUpdate(String utfPayload) {
		JsonTrorOrderHeaderContainer container = null;
		try{
			JsonTrorOrderHeaderMapperFlyimport mapper = new JsonTrorOrderHeaderMapperFlyimport();
			container = mapper.getContainerStatusUpdate(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
	}

}
