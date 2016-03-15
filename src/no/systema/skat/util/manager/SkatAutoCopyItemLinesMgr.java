/**
 * 
 */
package no.systema.skat.util.manager;

import java.util.*;
import javax.servlet.http.HttpServletRequest;

import no.systema.main.model.SystemaWebUser;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.skat.skatimport.model.jsonjackson.topic.items.JsonSkatImportSpecificTopicItemRecord;
import no.systema.skat.skatimport.url.store.SkatImportUrlDataStore;
import no.systema.skat.skatimport.util.RpgReturnResponseHandler;
import no.systema.skat.util.SkatConstants;


import org.apache.log4j.Logger;

/**
 * @author oscardelatorre
 * @date Oct 11, 2014
 * 
 * This class is used to managed issues regarding auto-copy USE CASE on Gui elements
 * The requirement was initiated by Kingsrød DK in order to propagate values on some item lines to 
 * other item lines, thus copying values through all selected item lines in the database
 * 
 */
public class SkatAutoCopyItemLinesMgr {
	private static final Logger logger = Logger.getLogger(SkatAutoCopyItemLinesMgr.class.getName());
	private RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
	/**
	 * Gets the list of request elements' names that have been selected for auto-copy
	 * @param request
	 * @return
	 */
	public void setAutoCopyAttributes(HttpServletRequest request){
		String copyLineStartLineNr = request.getParameter("copyLineStartLineNr");
		String copyLineEndLineNr = request.getParameter("copyLineEndLineNr");
		String totalNumberOfItemLines = request.getParameter("totalNumberOfItemLinesJsp");
		logger.info("###copyLineStartLineNr: " + copyLineStartLineNr);
		logger.info("###copyLineEndLineNr: " + copyLineEndLineNr);
		logger.info("###totalNumberOfItemLines: " + totalNumberOfItemLines);
		//Inspect the http request elements
		ArrayList<String> parameterNames = new ArrayList<String>();
		Enumeration enumeration = request.getParameterNames();
	    while (enumeration.hasMoreElements()) {
	        String parameterName = (String) enumeration.nextElement();
	        if(parameterName!=null && parameterName.contains("_copy")){
	        		//DEBUG logger.info("AUTOCOPY field:" + parameterName);
	        		
	        		//Check if the parameterName is compound (not single). 
	        		//(Whenever the GUI has a pair-or-more values per checkbox)
	        		if(parameterName.contains("@")){
	        			ArrayList<String> splittedList = this.splitParameterName(parameterName);
	        			for(String singleRecord : splittedList){
	        				//DEBUG logger.info("AUTOCOPY field (from compound record):" + singleRecord);
	        				this.listOfParameterNamesToAutoCopy.add(singleRecord);
	        			}
	        		}else{
	        			//single record from GUI
	        			this.listOfParameterNamesToAutoCopy.add(parameterName);
	        		}
	        }
	    }
	    //Now set all attributes if applicable
	    if(this.getlistOfParameterNamesToAutoCopy()!=null && this.getlistOfParameterNamesToAutoCopy().size()>0){
			this.copyLineStartLineNr = copyLineStartLineNr;
			this.copyLineEndLineNr = copyLineEndLineNr;
			this.totalNumberOfItemLines = totalNumberOfItemLines;
			this.autoCopyActive = true;
		}
	}
	
	/**Splits a compound string value (Usually separated by a special character)
	 * 
	 * @param compoundRecord
	 * @return
	 */
	private ArrayList<String>splitParameterName(String compoundRecord){
		ArrayList<String> list = new ArrayList<String>();
		if(compoundRecord!=null && !"".equals(compoundRecord)){
			if(compoundRecord.contains("@")){
				String[] tmp = compoundRecord.split("@");
				List<String> record = Arrays.asList(tmp);
				for(String field: record){
					list.add(field);
				}
			}
		}
		
		return list;
	}
	/**
	 * Builds the url parameter list to be sent to the back-end
	 * @return
	 */
	public String buildAutoCopyUrlParametersSubstring(){
		StringBuffer retval = new StringBuffer();
		for(String field : this.getlistOfParameterNamesToAutoCopy()){
			retval.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST  + field + "=1");
		}
		return retval.toString();
	}
	
	/**
	 * Executes the AutoCopy on back-end
	 * @param jsonSkatImportSpecificTopicItemRecord
	 * @param urlCgiProxyService
	 * @return
	 */
	public boolean executeAutoCopySkatImport(String avd, String opd, String user, JsonSkatImportSpecificTopicItemRecord itemRecord, UrlCgiProxyService urlCgiProxyService){
		boolean rpgReturn = false;
		logger.info("[INFO] AutoCopy STEP after Update has been activated");
		String urlRequestParamsKeysAutoCopy = this.getRequestUrlKeyParametersAutoCopy(itemRecord, avd, opd, user);
		String urlRequestParamsTopicItemForAutoCopy = this.buildAutoCopyUrlParametersSubstring();
		//logger.info("[INFO] AutoCopy PARAMS: " + urlRequestParamsTopicItemForAutoCopy);
		//----------------------------------
		//add AutoCopy string if applicable
		//----------------------------------
		if(urlRequestParamsTopicItemForAutoCopy!=null){
			String BASE_URL_AUTO_COPY = SkatImportUrlDataStore.SKAT_IMPORT_BASE_AUTO_COPY_AFTER_UPDATE_SPECIFIC_TOPIC_ITEM_URL;
			logger.info("[INFO] Starting AUTO-COPY execute. LineNr [dkiv_syli]: " + itemRecord.getDkiv_syli());
			String urlRequestParamsAutoCopy = urlRequestParamsKeysAutoCopy + urlRequestParamsTopicItemForAutoCopy;
			logger.info("URL: " + BASE_URL_AUTO_COPY);
	    		logger.info("URL PARAMS: " + urlRequestParamsAutoCopy);
			//----------------------------------------------------------------------------
			//EXECUTE the UPDATE (RPG program) here (STEP [2] when creating a new record)
			//----------------------------------------------------------------------------
			String rpgReturnPayloadAutoCopy = urlCgiProxyService.getJsonContent(BASE_URL_AUTO_COPY, urlRequestParamsAutoCopy);	
			logger.info("[INFO] End AUTO-COPY execute.");
			//Debug --> 
	    		logger.info("Checking errMsg in rpgReturnPayloadAutoCopy" + rpgReturnPayloadAutoCopy);
		    	//we must evaluate a return RPG code in order to know if the Update was OK or not
		    	rpgReturnResponseHandler.evaluateRpgResponseOnTopicItemCreateOrUpdate(rpgReturnPayloadAutoCopy);
		    	if(rpgReturnResponseHandler.getErrorMessage()!=null && !"".equals(rpgReturnResponseHandler.getErrorMessage())){
		    		rpgReturnResponseHandler.setErrorMessage("[ERROR] FATAL on AUTO-COPY: " + rpgReturnResponseHandler.getErrorMessage());
		    		//TODO COVI -->this.setFatalError(model, rpgReturnResponseHandler, jsonSkatImportSpecificTopicItemRecord);
		    		
		    	}else{
		    		rpgReturn = true;
		    		//Update succefully done!
		    		logger.info("[INFO] Valid STEP AUTO-COPY -- Records successfully auto-copied, OK ");
			}
		}
		return rpgReturn;
	}
	
	/**
	 * 
	 * @param itemRecord
	 * @param avd
	 * @param opd
	 * @param user
	 * @return
	 */
	private String getRequestUrlKeyParametersAutoCopy(JsonSkatImportSpecificTopicItemRecord itemRecord, String avd, String opd, String user){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		
		urlRequestParamsKeys.append("user=" + user);
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd);
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + opd);
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "lin=" + itemRecord.getDkiv_syli());
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "linend=" + itemRecord.getCopyLineEndLineNr());
		
		return urlRequestParamsKeys.toString();
	}
	
	
	private boolean autoCopyActive = false;
	/**
	 * Indicates if the Manager should be used
	 * @return
	 */
	public boolean isAutoCopyActive (){return this.autoCopyActive;}
	
	
	private ArrayList<String> listOfParameterNamesToAutoCopy = new ArrayList<String>();
	/**
	 * This list contains the checked elements
	 * @return
	 */
	public ArrayList<String> getlistOfParameterNamesToAutoCopy(){return this.listOfParameterNamesToAutoCopy;}
	
	private String copyLineStartLineNr =null;
	public String getCopyLineStartLineNr(){return this.copyLineStartLineNr;}
	public Integer getCopyLineStartLineNrInt(){
		Integer retval = 0;
		if(this.copyLineStartLineNr!=null && !"".equals(this.copyLineStartLineNr)){
			try{
				retval = Integer.valueOf(this.copyLineStartLineNr);
			}catch(Exception e){
				//nada
			}
		}
		return retval;
	}
	
	
	private String copyLineEndLineNr =null;
	public String getCopyLineEndLineNr(){return this.copyLineEndLineNr;}
	
	private String totalNumberOfItemLines =null;
	public String getTotalNumberOfItemLines(){return this.totalNumberOfItemLines;}
	
}
