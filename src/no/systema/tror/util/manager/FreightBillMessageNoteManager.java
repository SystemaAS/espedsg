/**
 * 
 */
package no.systema.tror.util.manager;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.SessionAttributes;

import no.systema.jservices.common.dao.Dok29Dao;
import no.systema.jservices.common.dao.Dok36Dao;
import no.systema.jservices.common.dao.DokufDao;
import no.systema.jservices.common.json.JsonDtoContainer;
import no.systema.jservices.common.json.JsonReader;
import no.systema.main.model.SystemaWebUser;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.util.AppConstants;
import no.systema.main.util.MessageNoteManager;
import no.systema.main.util.StringManager;
import no.systema.tror.url.store.TrorUrlDataStore;
import no.systema.tror.util.TrorConstants;

/**
 * Utility class to manage message notes issues
 * 
 * @author oscardelatorre
 * @date Jan 26, 2018
 * 
 */
public class FreightBillMessageNoteManager {
	
	private static Logger logger = Logger.getLogger(FreightBillMessageNoteManager.class.getName());
	private MessageNoteManager messageNoteMgr = new MessageNoteManager();
	private StringManager strMgr = new StringManager();
	
	private final String CARRIAGE_RETURN_PLAIN = "\n";
	private final String MESSAGE_NOTE_PARTY_TYPE_CONSIGNEE = "consignee";
	private final String MESSAGE_NOTE_PARTY_TYPE_CARRIER = "carrier";
	private UrlCgiProxyService urlCgiProxyService;
	//Constructor
	public FreightBillMessageNoteManager(UrlCgiProxyService service){
		this.urlCgiProxyService = service;
	}
	
	/**
	 * 
	 * @param model
	 * @param appUser
	 * @param dokufDao
	 */
	public void fetchMessageNoteConsignee(Map model, SystemaWebUser appUser, DokufDao dokufDao){
		StringBuffer guiMessageNotePayload = new StringBuffer();
		
		JsonReader<JsonDtoContainer<Dok29Dao>> jsonReader = new JsonReader<JsonDtoContainer<Dok29Dao>>();
		jsonReader.set(new JsonDtoContainer<Dok29Dao>());
		final String BASE_URL = TrorUrlDataStore.TROR_BASE_FETCH_DOK29_URL;
		StringBuilder urlRequestParams = new StringBuilder();
		urlRequestParams.append("user=" + appUser.getUser());
		urlRequestParams.append("&d29avd=" + dokufDao.getDfavd());
		urlRequestParams.append("&d29opd=" + dokufDao.getDfopd());
		urlRequestParams.append("&d29fnr=" + dokufDao.getDffbnr());
		
		
		logger.info("URL: " + BASE_URL);
		logger.info("PARAMS: " + urlRequestParams.toString());
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		logger.info("jsonPayload=" + jsonPayload);
		DokufDao record = null;
		JsonDtoContainer<Dok29Dao> container = (JsonDtoContainer<Dok29Dao>) jsonReader.get(jsonPayload);
		if (container != null) {
			for (Dok29Dao dao : container.getDtoList()) {
				guiMessageNotePayload.append(dao.getD29txt() + this.CARRIAGE_RETURN_PLAIN);
			}
		}
		model.put("messageNoteConsignee", guiMessageNotePayload.toString());
		
	}
	/**
	 * 
	 * @param model
	 * @param appUser
	 * @param dokufDao
	 */
	public void fetchMessageNoteCarrier(Map model, SystemaWebUser appUser, DokufDao dokufDao){
		StringBuffer guiMessageNotePayload = new StringBuffer();
		
		JsonReader<JsonDtoContainer<Dok36Dao>> jsonReader = new JsonReader<JsonDtoContainer<Dok36Dao>>();
		jsonReader.set(new JsonDtoContainer<Dok36Dao>());
		final String BASE_URL = TrorUrlDataStore.TROR_BASE_FETCH_DOK36_URL;
		StringBuilder urlRequestParams = new StringBuilder();
		urlRequestParams.append("user=" + appUser.getUser());
		urlRequestParams.append("&d36avd=" + dokufDao.getDfavd());
		urlRequestParams.append("&d36opd=" + dokufDao.getDfopd());
		urlRequestParams.append("&d36fnr=" + dokufDao.getDffbnr());
		
		
		logger.info("URL: " + BASE_URL);
		logger.info("PARAMS: " + urlRequestParams.toString());
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		logger.info("jsonPayload=" + jsonPayload);
		DokufDao record = null;
		JsonDtoContainer<Dok36Dao> container = (JsonDtoContainer<Dok36Dao>) jsonReader.get(jsonPayload);
		if (container != null) {
			for (Dok36Dao dao : container.getDtoList()) {
				guiMessageNotePayload.append(dao.getD36txt() + this.CARRIAGE_RETURN_PLAIN);
			}
		}
		model.put("messageNoteCarrier", guiMessageNotePayload.toString());
		
	}
	/**
	 * 
	 * @param model
	 * @param appUser
	 * @param request
	 * @param recordToValidate
	 */
	public void processMessageNotes(Map model, SystemaWebUser appUser, HttpServletRequest request, DokufDao recordToValidate){
		String messageNoteConsignee = request.getParameter("messageNoteConsignee");
		String messageNoteCarrier = request.getParameter("messageNoteCarrier");
		
		List<String> messageNoteConsigneeList = this.messageNoteMgr.getChunksOfMessageNoteAsList(messageNoteConsignee);
		List<String> messageNoteCarrierList = this.messageNoteMgr.getChunksOfMessageNoteAsList(messageNoteCarrier);
		this.updateMessageNote(model, messageNoteConsigneeList, appUser, MESSAGE_NOTE_PARTY_TYPE_CONSIGNEE, recordToValidate);
		this.updateMessageNote(model, messageNoteCarrierList, appUser, MESSAGE_NOTE_PARTY_TYPE_CARRIER, recordToValidate);
	}
	/**
	 * 
	 * @param model
	 * @param messageNote
	 * @param appUser
	 */
	public void updateMessageNote(Map model, List<String> messageNotePayload, SystemaWebUser appUser, String partyType, DokufDao recordToValidate){
		String CARRIAGE_RETURN = "[\n\r]";
		String NULL_PAYLOAD = null;
		//logger.info("A" + messageNotePayload); 
		if(messageNotePayload!=null && !messageNotePayload.isEmpty()){
			if(this.MESSAGE_NOTE_PARTY_TYPE_CONSIGNEE.equals(partyType)){
				//delete all
				this.updateMessageNoteConsignee(NULL_PAYLOAD, model, appUser, recordToValidate, TrorConstants.MODE_DELETE);
				for(String linePayload: messageNotePayload){
					linePayload = linePayload.replaceAll(CARRIAGE_RETURN, "");
					//logger.info("CONSIGNEE MESSAGE NOTE #########:" + linePayload);
					//insert this line
					this.updateMessageNoteConsignee(linePayload, model, appUser, recordToValidate, TrorConstants.MODE_ADD);
					
				}
			}else if (this.MESSAGE_NOTE_PARTY_TYPE_CARRIER.equals(partyType)){
				//delete all
				this.updateMessageNoteCarrier(NULL_PAYLOAD, model, appUser, recordToValidate, TrorConstants.MODE_DELETE);
				for(String linePayload: messageNotePayload){
					linePayload = linePayload.replaceAll(CARRIAGE_RETURN, "");
					//logger.info("CARRIER MESSAGE NOTE #########:" + linePayload);
					//insert this line
					this.updateMessageNoteCarrier(linePayload, model, appUser, recordToValidate, TrorConstants.MODE_ADD);
				}
			}
			
		}
	}
	/**
	 * 
	 * @param payload
	 * @param model
	 * @param appUser
	 * @param recordToValidate
	 * @param MODE
	 */
	public void updateMessageNoteConsignee(String payload, Map model, SystemaWebUser appUser, DokufDao recordToValidate, String MODE){
		
		JsonReader<JsonDtoContainer<Dok29Dao>> jsonReader = new JsonReader<JsonDtoContainer<Dok29Dao>>();
		jsonReader.set(new JsonDtoContainer<Dok29Dao>());
		final String BASE_URL = TrorUrlDataStore.TROR_BASE_DOK29_DML_UPDATE_URL;
		StringBuilder urlRequestParams = new StringBuilder();
		urlRequestParams.append("user=" + appUser.getUser());
		urlRequestParams.append("&mode=" + MODE);
		urlRequestParams.append("&d29avd=" + recordToValidate.getDfavd());
		urlRequestParams.append("&d29opd=" + recordToValidate.getDfopd());
		urlRequestParams.append("&d29fnr=" + recordToValidate.getDffbnr());
		if(!MODE.equals("D")){
			urlRequestParams.append("&d29txt=" + payload);
		}
		
		logger.info("URL: " + BASE_URL);
		logger.info("PARAMS: " + urlRequestParams.toString());
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		logger.info("jsonPayload=" + jsonPayload);
		DokufDao record = null;
		JsonDtoContainer<Dok29Dao> container = (JsonDtoContainer<Dok29Dao>) jsonReader.get(jsonPayload);
		if (container != null) {
			if(strMgr.isNotNull(container.getErrMsg())){
				logger.info("ERROR on update from DOK29 .... check this out!" + " MODE:" + MODE);
			}
		}
	}
	/**
	 * 
	 * @param payload
	 * @param model
	 * @param appUser
	 * @param recordToValidate
	 * @param MODE
	 */
	public void updateMessageNoteCarrier(String payload, Map model, SystemaWebUser appUser, DokufDao recordToValidate, String MODE){
		
		JsonReader<JsonDtoContainer<Dok36Dao>> jsonReader = new JsonReader<JsonDtoContainer<Dok36Dao>>();
		jsonReader.set(new JsonDtoContainer<Dok36Dao>());
		final String BASE_URL = TrorUrlDataStore.TROR_BASE_DOK36_DML_UPDATE_URL;
		StringBuilder urlRequestParams = new StringBuilder();
		urlRequestParams.append("user=" + appUser.getUser());
		urlRequestParams.append("&mode=" + MODE);
		urlRequestParams.append("&d36avd=" + recordToValidate.getDfavd());
		urlRequestParams.append("&d36opd=" + recordToValidate.getDfopd());
		urlRequestParams.append("&d36fnr=" + recordToValidate.getDffbnr());
		if(!MODE.equals("D")){
			urlRequestParams.append("&d36txt=" + payload);
		}
		logger.info("URL: " + BASE_URL);
		logger.info("PARAMS: " + urlRequestParams.toString());
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		logger.info("jsonPayload=" + jsonPayload);
		DokufDao record = null;
		JsonDtoContainer<Dok36Dao> container = (JsonDtoContainer<Dok36Dao>) jsonReader.get(jsonPayload);
		if (container != null) {
			if(strMgr.isNotNull(container.getErrMsg())){
				logger.info("ERROR on update from DOK36 .... check this out!" + " MODE:" + MODE);
			}
		}
	}
	
	

}
