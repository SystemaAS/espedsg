/**
 * 
 */
package no.systema.tror.service.landexport;

import no.systema.tror.mapper.jsonjackson.JsonTrorOrderHeaderMapperLandExport;
import no.systema.tror.mapper.jsonjackson.order.invoice.JsonTrorOrderLandExportInvoiceMapper;

import no.systema.tror.model.jsonjackson.order.invoice.JsonTrorOrderLandExportInvoiceContainer;
import no.systema.tror.model.jsonjackson.JsonTrorOrderHeaderContainer;
import no.systema.tror.model.jsonjackson.JsonTrorOrderHeaderDummyContainer;

/**
 * 
 * @author oscardelatorre
 * @date Dec 28, 2017
 * 
 * 
 */
public class TrorMainOrderHeaderLandexportServiceImpl implements TrorMainOrderHeaderLandexportService {

	/**
	 * Gets defualt values from HEDUMMY/MEMBER (TABLE)
	 */
	public JsonTrorOrderHeaderDummyContainer getOrderHeaderDummyContainer(String utfPayload){
		JsonTrorOrderHeaderDummyContainer container = null;
		try{
			JsonTrorOrderHeaderMapperLandExport mapper = new JsonTrorOrderHeaderMapperLandExport();
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
			JsonTrorOrderHeaderMapperLandExport mapper = new JsonTrorOrderHeaderMapperLandExport();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
	}
	/**
	 * 
	 */
	public JsonTrorOrderLandExportInvoiceContainer getOrderInvoiceContainer(String utfPayload) {
		JsonTrorOrderLandExportInvoiceContainer container = null;
		try{
			JsonTrorOrderLandExportInvoiceMapper mapper = new JsonTrorOrderLandExportInvoiceMapper();
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
			JsonTrorOrderHeaderMapperLandExport mapper = new JsonTrorOrderHeaderMapperLandExport();
			container = mapper.getContainerStatusUpdate(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
	}

}
