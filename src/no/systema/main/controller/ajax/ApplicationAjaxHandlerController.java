/**
 * 
 */
package no.systema.main.controller.ajax;

import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

//
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.service.general.notisblock.NotisblockService;
import no.systema.main.model.jsonjackson.general.notisblock.JsonNotisblockContainer;
import no.systema.main.model.jsonjackson.general.notisblock.JsonNotisblockRecord;
import no.systema.main.url.store.MainUrlDataStore;
import no.systema.main.util.AppConstants;
/**
 * This Ajax handler is the class handling ajax request general in the whole Application. 
 * It will usually be called from within a jQuery function or other javascript alike... 
 * 
 * @author oscardelatorre
 * @date Jan 20, 2015
 * 
 */
@Controller
public class ApplicationAjaxHandlerController {
	private static final Logger logger = Logger.getLogger(ApplicationAjaxHandlerController.class.getName());
	
	
	/**
	 * 
	 * @param applicationUser
	 * @param elementValue
	 * @param avd
	 * @param opd
	 * @return
	 */
	@RequestMapping(value = "getSpecificNotisblockItemChosenFromGuiElement.do", method = RequestMethod.GET)
	public @ResponseBody Set<JsonNotisblockRecord> getSpecificNotisblockItemChosenFromGuiElement
	  						(@RequestParam String applicationUser, @RequestParam String elementValue, 
	  						 @RequestParam String avd, @RequestParam String opd ) {
		 
		 final String METHOD = "[DEBUG] getSpecificNotisblockItemChosenFromGuiElement ";
		 logger.info(METHOD + "Inside");
		 Set<JsonNotisblockRecord> result = new HashSet<JsonNotisblockRecord>();
		 //prepare the access CGI with RPG back-end
		 String BASE_URL = MainUrlDataStore.SYSTEMA_NOTIS_BLOCK_FETCH_ITEMLINE_URL;
		 String[] fields = elementValue.split("_");
		 //String lineNr = null;
		 String frtli = null;
		 String frtdt = null;
		 if(fields!=null && fields.length==3){
			 //logger.info(METHOD + "FIELDs: " + fields[0] + " " + fields[1] + " " + fields[2]);
			 frtli = fields[1];
			 frtdt = fields[2];
			 //logger.info(applicationUser + "-" + elementValue + " frtli:" + frtli + " frtdt:" + frtdt);
			 String urlRequestParamsKeys = this.getRequestUrlKeyParametersForNotisblockItem(applicationUser, avd, opd, frtli, frtdt);
			 logger.info(METHOD + "URL: " + BASE_URL);
			 logger.info(METHOD + "PARAMS: " + urlRequestParamsKeys);
			 logger.info(METHOD + Calendar.getInstance().getTime() +  " CGI-start timestamp");
			 String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
			 logger.info(METHOD + Calendar.getInstance().getTime() +  " CGI-end timestamp");		
			 if(jsonPayload!=null){
				 //we must replace wrong name in order to use the same JSON item record. The RPG name "oneline" should be replaced (at the back end)
				 //in the future by orderList... We do that here and now
				 logger.info(METHOD + jsonPayload);
				 JsonNotisblockContainer container = this.notisblockService.getNotisblockListContainer(jsonPayload);
				 if(container!=null){
					 for(JsonNotisblockRecord  record : container.getFreetxtGet()){
						 //TODO record.setDebugPrintlnAjax(BASE_URL + "?" + urlRequestParamsKeys + " <JSON> " + jsonPayload + "</JSON>");
				         logger.info("=====>debugFetch: OK output on GUI");
				         //add to list
				         result.add(record);
					 }
				 }
			 }
		 }else{
			 logger.error(METHOD + "[ERROR] on fields[]...null or incorrect length???...");
		 }
		 return result;
	 }	 

	  /*
	  @RequestMapping(value = "updateNotisblockItemLine_SadImport.do", method ={RequestMethod.GET, RequestMethod.POST} )
	  public @ResponseBody Set updateNotisblockItemLine
	  						(@RequestParam String applicationUser, @RequestParam String frtavd, @RequestParam String frtopd, 
							@RequestParam String frtdt, @RequestParam String frtli, @RequestParam String frttxt, @RequestParam String frtkod, 
							@RequestParam String mode	) {
		 final String METHOD = "[DEBUG] method:updateNotisblockItemLine ";
		 logger.info(METHOD + " Inside...");
		 Set result = new HashSet();
		 final String DELETE_LINE_MODE = "D";
		 //prepare the access CGI with RPG back-end
		 String BASE_URL = TvinnSadUrlDataStore.TVINN_SAD_NOTIS_BLOCK_UPDATE_ITEMLINE_URL;
		 StringBuffer urlRequestParamsKeysBuffer = new StringBuffer();
		 urlRequestParamsKeysBuffer.append("user=" + applicationUser);
		 urlRequestParamsKeysBuffer.append("&frtavd=" + frtavd);
		 urlRequestParamsKeysBuffer.append("&frtopd=" + frtopd);
		 urlRequestParamsKeysBuffer.append("&frtdt=" + frtdt);
		 urlRequestParamsKeysBuffer.append("&frtli=" + frtli);
		 urlRequestParamsKeysBuffer.append("&mode=" + mode);
		 //now put the values to update (edit, create new)
		 if(!DELETE_LINE_MODE.equals(mode)){
			 urlRequestParamsKeysBuffer.append("&frttxt=" + frttxt);
			 urlRequestParamsKeysBuffer.append("&frtkod=" + frtkod);		 
		 }
		 String urlRequestParamsKeys = urlRequestParamsKeysBuffer.toString();
		 logger.info(METHOD + "URL: " + BASE_URL);
		 logger.info(METHOD + "PARAMS: " + urlRequestParamsKeys);
		 logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		 String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		 logger.info(METHOD + jsonPayload);
		 logger.info(METHOD + Calendar.getInstance().getTime() +  " CGI-end timestamp");
		 
		 JsonNotisblockContainer jsonNotisblockContainer = this.notisblockService.getNotisblockListContainer(jsonPayload);
		 logger.info("JsonNotisblockContainer:" + jsonNotisblockContainer);
		 if(jsonNotisblockContainer!=null){
			 //logger.info("A:" + jsonNotisblockContainer.getErrMsg());
			 if( !"".equals(jsonNotisblockContainer.getErrMsg()) ){
				 //Debug
				 logger.info(METHOD + "[ERROR]:" + jsonNotisblockContainer.getErrMsg());
			 }
			 result.add(jsonNotisblockContainer);
		 }
		 return result;
	  }
	  */
	  
	  /**
	   * 
	   * @param applicationUser
	   * @param avd
	   * @param opd
	   * @param frtli
	   * @param frtdt
	   * @return
	   */
	  private String getRequestUrlKeyParametersForNotisblockItem(String applicationUser, String avd, String opd,  String frtli, String frtdt){
		  StringBuffer sb = new StringBuffer();
		  sb.append("user=" + applicationUser);
		  if(avd!=null && !"".equals(avd) && opd!=null && !"".equals(opd) && frtli!=null && !"".equals(frtli) && frtdt!=null && !"".equals(frtdt)){
			  sb.append( AppConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd );
			  sb.append( AppConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + opd );
			  sb.append( AppConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "dat=" + frtdt );
			  sb.append( AppConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "lin=" + frtli );
		  }
		  return sb.toString();
	  }

	  //SERVICES
	  @Qualifier ("urlCgiProxyService")
	  private UrlCgiProxyService urlCgiProxyService;
	  @Autowired
	  @Required
	  public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	  public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	  
	  @Qualifier ("notisblockService")
	  private NotisblockService notisblockService;
	  @Autowired
	  public void setNotisblockService (NotisblockService value){ this.notisblockService=value; }
	  public NotisblockService getNotisblockService(){return this.notisblockService;}
		
		
}
