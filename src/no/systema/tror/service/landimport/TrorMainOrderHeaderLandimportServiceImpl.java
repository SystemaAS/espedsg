/**
 * 
 */
package no.systema.tror.service.landimport;

import no.systema.tror.mapper.jsonjackson.JsonTrorOrderHeaderMapperLandimport;
import no.systema.tror.mapper.jsonjackson.order.invoice.JsonTrorOrderLandImportInvoiceMapper;

import no.systema.tror.model.jsonjackson.order.invoice.JsonTrorOrderLandImportInvoiceContainer;
import no.systema.tror.model.jsonjackson.JsonTrorOrderHeaderContainer;
import no.systema.tror.model.jsonjackson.JsonTrorOrderHeaderDummyContainer;

/**
 * 
 * @author oscardelatorre
 * @date Aug 11, 2017
 * 
 * 
 */
public class TrorMainOrderHeaderLandimportServiceImpl implements TrorMainOrderHeaderLandimportService {

	/**
	 * Gets defualt values from HEDUMMY/MEMBER (TABLE)
	 */
	public JsonTrorOrderHeaderDummyContainer getOrderHeaderDummyContainer(String utfPayload){
		JsonTrorOrderHeaderDummyContainer container = null;
		try{
			JsonTrorOrderHeaderMapperLandimport mapper = new JsonTrorOrderHeaderMapperLandimport();
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
			JsonTrorOrderHeaderMapperLandimport mapper = new JsonTrorOrderHeaderMapperLandimport();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
	}
	/**
	 * 
	 */
	public JsonTrorOrderLandImportInvoiceContainer getOrderInvoiceContainer(String utfPayload) {
		JsonTrorOrderLandImportInvoiceContainer container = null;
		try{
			JsonTrorOrderLandImportInvoiceMapper mapper = new JsonTrorOrderLandImportInvoiceMapper();
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
			JsonTrorOrderHeaderMapperLandimport mapper = new JsonTrorOrderHeaderMapperLandimport();
			container = mapper.getContainerStatusUpdate(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
	}

}
