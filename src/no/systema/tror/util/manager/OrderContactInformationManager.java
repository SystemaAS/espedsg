/**
 * 
 */
package no.systema.tror.util.manager;

import java.util.Arrays;
import java.util.Calendar;
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
import no.systema.jservices.common.dao.DokufeDao;
import no.systema.jservices.common.json.JsonDtoContainer;
import no.systema.jservices.common.json.JsonReader;
import no.systema.jservices.common.util.StringUtils;
import no.systema.main.model.SystemaWebUser;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.util.AppConstants;
import no.systema.main.util.JsonDebugger;
import no.systema.main.util.MessageNoteManager;
import no.systema.main.util.StringManager;
import no.systema.transportdisp.model.workflow.order.OrderContactInformationObject;
import no.systema.tror.mapper.url.request.UrlRequestParameterMapper;
import no.systema.tror.url.store.TrorUrlDataStore;
import no.systema.tror.util.TrorConstants;

/**
 * Utility class to manage message notes issues
 * 
 * @author oscardelatorre
 * @date Jan 26, 2018
 * 
 */
public class OrderContactInformationManager {
	private static final JsonDebugger jsonDebugger = new JsonDebugger(1500);
	private static Logger logger = Logger.getLogger(OrderContactInformationManager.class.getName());
	private StringManager strMgr = new StringManager();
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	private UrlCgiProxyService urlCgiProxyService;
	//
	private final String PARTY_CONSIGNOR_CN = "CN";
	private final String PARTY_CONSIGNEE_CZ = "CZ";
	//Constructor
	public OrderContactInformationManager(UrlCgiProxyService service){
		this.urlCgiProxyService = service;
	}
	
	/**
	 * 
	 * @param appUser
	 * @param daoParam
	 * @param partyType
	 * @param readOnly
	 * @param contactInfoObject
	 * @return
	 */
	public DokufeDao getContactInformation(SystemaWebUser appUser, DokufeDao daoParam, String partyType, boolean readOnly, OrderContactInformationObject contactInfoObject){
		 DokufeDao dao = null;
		 //http://localhost:8080/syjservicestror/syjsDOKUFE.do?user=OSCAR&fe_dfavd=1&fe_dfopd=52919&fe_dffbnr=1&fe_n3035
		 //===========
		 //FETCH LIST
		 //===========
		 logger.info("Inside: getContactInformation");
		 JsonReader<JsonDtoContainer<DokufeDao>> jsonReader = new JsonReader<JsonDtoContainer<DokufeDao>>();
		 jsonReader.set(new JsonDtoContainer<DokufeDao>());
			
		 //prepare the access CGI with RPG back-end
		 String BASE_URL = TrorUrlDataStore.TROR_BASE_FETCH_SPECIFIC_CONTACT_INFORMATION_URL;
		 StringBuffer urlRequestParamsKeys = new StringBuffer();
		 urlRequestParamsKeys.append("user=" + appUser.getUser() + "&fe_dfavd=" + daoParam.getFe_dfavd() + "&fe_dfopd=" + daoParam.getFe_dfopd());
		 urlRequestParamsKeys.append("&fe_dffbnr=" + daoParam.getFe_dffbnr()); //always fraktbrev nr 1 
		 urlRequestParamsKeys.append("&fe_n3035=" + partyType);
		 
		 logger.info("URL: " + BASE_URL);
		 logger.info("PARAMS: " + urlRequestParamsKeys);
		 logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		 String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys.toString());
		 logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		 logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
		 
		 JsonDtoContainer<DokufeDao> container = (JsonDtoContainer<DokufeDao>) jsonReader.get(jsonPayload);
		 if (container != null) {
			if(container.getDtoList()!=null){
				for(DokufeDao record : container.getDtoList()){
					if(PARTY_CONSIGNOR_CN.equals(record.getFe_n3035())) {
						if(readOnly){
							contactInfoObject.setOwnSenderContactName(record.getFe_c3412());
							contactInfoObject.setOwnSenderMobile(record.getFe_c3148B());
							contactInfoObject.setOwnSenderEmail(record.getFe_c3148E());
						}
						dao = record;						
					}else if(PARTY_CONSIGNEE_CZ.equals(record.getFe_n3035())) {
						if(readOnly){
							contactInfoObject.setOwnReceiverContactName(record.getFe_c3412());
							contactInfoObject.setOwnReceiverMobile(record.getFe_c3148B());
							contactInfoObject.setOwnReceiverEmail(record.getFe_c3148E());
						}
						dao = record;
					}
				}
			}
		 }
		 return dao;
	}
	
	/**
	 * 
	 * @param appUser
	 * @param orderContactInfoObj
	 * @param dokufeDao
	 */
	public void updateContactInformation(SystemaWebUser appUser, OrderContactInformationObject orderContactInfoObj, DokufeDao dokufeDao){
		boolean recordToValidateReadOnly = false;
		DokufeDao dao = null;
		//-------------------------
		//Sender - Consignor - CN
		//-------------------------
		dao = this.getContactInformation(appUser, dokufeDao, this.PARTY_CONSIGNOR_CN, recordToValidateReadOnly, orderContactInfoObj);
		if(dao != null){
			//update
			logger.info("update CN...");
			this.updateDokufe(appUser, TrorConstants.MODE_UPDATE, dao, orderContactInfoObj, this.PARTY_CONSIGNOR_CN);
		}else{
			//create new
			logger.info("create CN...");
			dao = new DokufeDao();
			dao.setFe_dfavd(Integer.parseInt(orderContactInfoObj.getAvd()));
			dao.setFe_dfopd(Integer.parseInt(orderContactInfoObj.getOpd()));
			this.updateDokufe(appUser, TrorConstants.MODE_ADD, dao, orderContactInfoObj, this.PARTY_CONSIGNOR_CN);
		}
		//---------------------------
		//Receiver - Consignee - CZ
		//---------------------------
		dao = this.getContactInformation(appUser, dokufeDao, this.PARTY_CONSIGNEE_CZ, recordToValidateReadOnly, orderContactInfoObj);
		if(dao != null){
			//update
			logger.info("update CZ...");
			this.updateDokufe(appUser, TrorConstants.MODE_UPDATE, dao, orderContactInfoObj, this.PARTY_CONSIGNEE_CZ);
		}else{
			//create new
			logger.info("create CZ...");
			dao = new DokufeDao();
			dao.setFe_dfavd(Integer.parseInt(orderContactInfoObj.getAvd()));
			dao.setFe_dfopd(Integer.parseInt(orderContactInfoObj.getOpd()));
			this.updateDokufe(appUser, TrorConstants.MODE_ADD, dao, orderContactInfoObj, PARTY_CONSIGNEE_CZ);
		}
		
		
	}
	

	/**
	 * http://localhost:8080/syjservicestror/syjsDOKUFE_U.do?user=OSCAR&mode=U/A&fe_dfavd=1&fe_dfopd=52919&fe_dffbnr=1&fe_n3035...etc
	 * 
	 * @param appUser
	 * @param mode
	 * @param dao
	 * @param orderContactInfoObj
	 * @param partyType
	 * @return
	 */
	private int updateDokufe(SystemaWebUser appUser, String mode, DokufeDao dao, OrderContactInformationObject orderContactInfoObj, String partyType){
		 int retval = 0;
		 int FRAKTBREV_1 = 1;
		 //===========
		 //UPDATE 
		 //===========
		 //Update dao here
		 dao.setFe_dffbnr(FRAKTBREV_1);
		 if(this.PARTY_CONSIGNEE_CZ.equals(partyType)){
			 dao.setFe_c3412(orderContactInfoObj.getOwnReceiverContactName());
			 dao.setFe_c3148B(orderContactInfoObj.getOwnReceiverMobile());
			 dao.setFe_c3148E(orderContactInfoObj.getOwnReceiverEmail());
			 dao.setFe_n3035(orderContactInfoObj.getOwnReceiverPartId());
		 }else{
			 dao.setFe_c3412(orderContactInfoObj.getOwnSenderContactName());
			 dao.setFe_c3148B(orderContactInfoObj.getOwnSenderMobile());
			 dao.setFe_c3148E(orderContactInfoObj.getOwnSenderEmail());
			 dao.setFe_n3035(orderContactInfoObj.getOwnSenderPartId());
		 }
		 
		 logger.info("Inside: updateContactInformation");
		 JsonReader<JsonDtoContainer<DokufeDao>> jsonReader = new JsonReader<JsonDtoContainer<DokufeDao>>();
		 jsonReader.set(new JsonDtoContainer<DokufeDao>());
			
		 //prepare the access CGI with RPG back-end
		 String BASE_URL = TrorUrlDataStore.TROR_BASE_DOKUFE_DML_UPDATE_URL;
		 String urlRequestParamsKeys = "user=" + appUser.getUser() + "&mode=" + mode;
		 String urlRequestParams = urlRequestParamsKeys + this.urlRequestParameterMapper.getUrlParameterValidString(dao);
		 
		 
		 logger.info("URL: " + BASE_URL);
		 logger.info("PARAMS: " + urlRequestParams);
		 logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		 String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
		 logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		 logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
		 
		 JsonDtoContainer<DokufeDao> container = (JsonDtoContainer<DokufeDao>) jsonReader.get(jsonPayload);
		 if (container != null) {
			if(container.getDtoList()!=null){
				if(StringUtils.hasValue(container.getErrMsg()) ){
					retval = -1;
					logger.info("Error during UPDATE - DOKUFE ...");
				}
			}
		 }
		 
		 return retval;
		 
	}
	
}
