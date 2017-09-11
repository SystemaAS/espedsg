/**
 * 
 */
package no.systema.tror.service;

import no.systema.tror.mapper.jsonjackson.JsonTrorOrderHeaderMapperChildWindow;
import no.systema.tror.mapper.jsonjackson.JsonTrorOrderHeaderMapperLandImportChildWindow;

import no.systema.tror.model.jsonjackson.order.childwindow.JsonTrorCarrierContainer;
import no.systema.tror.model.jsonjackson.order.childwindow.JsonTrorLosseLasteStedContainer;
import no.systema.tror.model.jsonjackson.order.childwindow.JsonTrorPostalCodeContainer;
import no.systema.tror.model.jsonjackson.order.childwindow.JsonTrorTollstedContainer;



/**
 * 
 * @author oscardelatorre
 * @date Aug 31, 2017
 * 
 * 
 */
public class TrorMainOrderHeaderChildwindowServiceImpl implements TrorMainOrderHeaderChildwindowService {

	/**
	 * 
	 */
	public JsonTrorCarrierContainer getCarrierListContainer(String utfPayload){
		JsonTrorCarrierContainer container = null;
		try{
			JsonTrorOrderHeaderMapperChildWindow mapper = new JsonTrorOrderHeaderMapperChildWindow();
			container = mapper.getCarrierContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
	}
	
	/**
	 * 
	 */
	public JsonTrorPostalCodeContainer getPostalCodeListContainer(String utfPayload){
		JsonTrorPostalCodeContainer container = null;
		try{
			JsonTrorOrderHeaderMapperChildWindow mapper = new JsonTrorOrderHeaderMapperChildWindow();
			container = mapper.getPostnrContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
		
		
	}
	/**
	 * 
	 */
	public JsonTrorTollstedContainer getTollstedListContainer(String utfPayload){
		JsonTrorTollstedContainer container = null;
		try{
			JsonTrorOrderHeaderMapperChildWindow mapper = new JsonTrorOrderHeaderMapperChildWindow();
			container = mapper.getTollstedContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
		
		
	}
	/**
	 * 
	 */
	public JsonTrorLosseLasteStedContainer getLosseLasteStedContainer(String utfPayload){
		JsonTrorLosseLasteStedContainer container = null;
		try{
			JsonTrorOrderHeaderMapperLandImportChildWindow mapper = new JsonTrorOrderHeaderMapperLandImportChildWindow();
			container = mapper.getLosseLasteContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
	}
	

}
