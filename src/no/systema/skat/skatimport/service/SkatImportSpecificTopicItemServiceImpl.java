/**
 * 
 */
package no.systema.skat.skatimport.service;

import no.systema.skat.skatimport.mapper.jsonjackson.SkatImportSpecificTopicItemMapper;
//import no.systema.tds.mapper.jsonjackson.validation.TdsMangdEnhetMapper;

import no.systema.skat.skatimport.model.jsonjackson.topic.items.JsonSkatImportSpecificTopicItemCertificateNrAndCodeR442Container;
import no.systema.skat.skatimport.model.jsonjackson.topic.items.JsonSkatImportSpecificTopicItemContainer;
import no.systema.skat.skatimport.model.jsonjackson.topic.items.JsonSkatImportSpecificTopicItemAvgifterContainer;
import no.systema.skat.skatimport.model.jsonjackson.topic.items.JsonSkatImportSpecificTopicItemBilagdaHandlingarContainer;
import no.systema.skat.skatimport.model.jsonjackson.topic.items.JsonSkatImportSpecificTopicItemToldvaerdiContainer;
import no.systema.skat.skatimport.model.jsonjackson.topic.items.JsonSkatImportSpecificTopicItemToldvaerdiTransportContainer;

//import no.systema.tds.model.jsonjackson.validation.JsonTdsMangdEnhetContainer;

/**
 * @author oscardelatorre
 * @date Sep 2, 2013
 * 
 *
 */
public class SkatImportSpecificTopicItemServiceImpl implements SkatImportSpecificTopicItemService{
	/**
	 * @param utfPayload
	 * @return
	 * 
	 */
	public JsonSkatImportSpecificTopicItemContainer getSkatImportSpecificTopicItemContainer(String utfPayload) {
		JsonSkatImportSpecificTopicItemContainer container = null;
		try{
			SkatImportSpecificTopicItemMapper mapper = new SkatImportSpecificTopicItemMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}
	
	/**
	 * Maps the avgifter
	 * 
	 * @param utfPayload
	 * @return
	 * 
	 */
	public JsonSkatImportSpecificTopicItemAvgifterContainer getSkatImportSpecificTopicItemAvgifterContainer(String utfPayload) {
		JsonSkatImportSpecificTopicItemAvgifterContainer container = null;
		try{
			SkatImportSpecificTopicItemMapper tdsExportSpecificTopicItemMapper = new SkatImportSpecificTopicItemMapper();
			container = tdsExportSpecificTopicItemMapper.getAvgifterContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}
	
	/**
	 * Maps the mangdenhet (Yes or No)
	 * 
	 * @param utfPayload
	 * @return
	 * 
	 */
	/*
	public JsonTdsMangdEnhetContainer getTdsMangdEnhetContainer (String utfPayload){
		JsonTdsMangdEnhetContainer container = null;
		try{
			TdsMangdEnhetMapper mapper = new TdsMangdEnhetMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
	}
	*/
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * 
	 * 
	 */
	public JsonSkatImportSpecificTopicItemBilagdaHandlingarContainer getSkatBilagdaHandlingarContainer (String utfPayload){
		JsonSkatImportSpecificTopicItemBilagdaHandlingarContainer container= null;
		try{
			SkatImportSpecificTopicItemMapper mapper = new SkatImportSpecificTopicItemMapper();
			container = mapper.getBilagdaHandlingarContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}
	
	/**
	 * Tullvaerdideklaration calculator
	 * 
	 * @param utfPayload
	 * @return
	 */
	public JsonSkatImportSpecificTopicItemToldvaerdiContainer getSkatTullvaerdiContainer (String utfPayload){
		JsonSkatImportSpecificTopicItemToldvaerdiContainer container= null;
		try{
			SkatImportSpecificTopicItemMapper mapper = new SkatImportSpecificTopicItemMapper();
			container = mapper.getTullvaerdiContainer(utfPayload);
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
	public JsonSkatImportSpecificTopicItemToldvaerdiTransportContainer getSkatTullvaerdiTransportContainer (String utfPayload){
		JsonSkatImportSpecificTopicItemToldvaerdiTransportContainer container= null;
		try{
			SkatImportSpecificTopicItemMapper mapper = new SkatImportSpecificTopicItemMapper();
			container = mapper.getTullvaerdiTransportContainer(utfPayload);
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
	public JsonSkatImportSpecificTopicItemCertificateNrAndCodeR442Container getSkatCertificateNrAndCodeR442Container (String utfPayload){
		JsonSkatImportSpecificTopicItemCertificateNrAndCodeR442Container container= null;
		try{
			SkatImportSpecificTopicItemMapper mapper = new SkatImportSpecificTopicItemMapper();
			container = mapper.getCertificateNrAndCodeR442Container(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
	}
	
}
