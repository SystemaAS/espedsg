/**
 * 
 */
package no.systema.skat.admin.service;

import no.systema.skat.admin.model.jsonjackson.topic.JsonSkatAdminNorskExportListContainer;
import no.systema.skat.admin.model.jsonjackson.topic.JsonSkatAdminNorskImportListContainer;
import no.systema.skat.admin.model.jsonjackson.topic.JsonSkatAdminTransportListContainer;

import no.systema.skat.admin.mapper.jsonjackson.SkatAdminTransportListMapper;
import no.systema.skat.admin.mapper.jsonjackson.SkatAdminNorskImportListMapper;
import no.systema.skat.admin.mapper.jsonjackson.SkatAdminNorskExportListMapper;

/**
 * @author oscardelatorre
 * @date Feb 20, 2014
 * 
 */
public class SkatAdminTransportServiceImpl implements SkatAdminTransportService {
	
	/**
	 * Transportuppdrag
	 * 
	 * @param utfPayload
	 * @return
	 */
	public JsonSkatAdminTransportListContainer getSkatAdminTransportListContainer(String utfPayload){
		JsonSkatAdminTransportListContainer container = null;
		try{
			SkatAdminTransportListMapper mapper = new SkatAdminTransportListMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
	}
	
	/**
	 * Norsk Import
	 * 
	 * @param utfPayload
	 * @return
	 */
	public JsonSkatAdminNorskImportListContainer getSkatAdminNorskImportListContainer(String utfPayload){
		JsonSkatAdminNorskImportListContainer container = null;
		try{
			SkatAdminNorskImportListMapper mapper = new SkatAdminNorskImportListMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
	}
	
	/**
	 * Norsk Export
	 * 
	 * @param utfPayload
	 * @return
	 */
	public JsonSkatAdminNorskExportListContainer getSkatAdminNorskExportListContainer(String utfPayload){
		JsonSkatAdminNorskExportListContainer container = null;
		try{
			SkatAdminNorskExportListMapper mapper = new SkatAdminNorskExportListMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
	}
}
