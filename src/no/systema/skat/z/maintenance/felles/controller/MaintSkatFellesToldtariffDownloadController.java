package no.systema.skat.z.maintenance.felles.controller;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;

//application imports
import no.systema.main.context.TdsAppContext;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.validator.LoginValidator;
import no.systema.main.util.AppConstants;
import no.systema.main.util.JsonDebugger;
import no.systema.main.util.StringManager;
import no.systema.main.model.SystemaWebUser;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainEdiiContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainEdiiRecord;
import no.systema.z.main.maintenance.service.MaintMainEdiiService;
import no.systema.z.main.maintenance.url.store.MaintenanceMainUrlDataStore;
import no.systema.skat.z.maintenance.main.mapper.url.request.UrlRequestParameterMapper;
import no.systema.skat.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintDktfiContainer;
import no.systema.skat.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintDktfiRecord;
import no.systema.skat.z.maintenance.main.service.MaintDktfiService;
import no.systema.skat.z.maintenance.main.service.html.dropdown.SkatMaintMainDropDownListPopulationService;
import no.systema.skat.z.maintenance.main.url.store.MaintenanceUrlDataStore;
import no.systema.skat.z.maintenance.main.util.SkatMaintenanceConstants;
import no.systema.skat.z.maintenance.felles.validator.MaintSkatFellesDkt055rValidator;


/**
 *  SKAT Maintenance Felles Told tariff download Controller 
 * 
 * @author oscardelatorre
 * @date Sep 12, 2017
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class MaintSkatFellesToldtariffDownloadController {
	
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private static final Logger logger = Logger.getLogger(MaintSkatFellesToldtariffDownloadController.class.getName());
	private ModelAndView loginView = new ModelAndView("login");
	private ApplicationContext context;
	private LoginValidator loginValidator = new LoginValidator();
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	private StringManager strMgr = new StringManager();
	private String [] sourceURLArray = { AppConstants.HTTPS_SKAT_DOWNLOAD_TOLDTARIF_FLATFILE_1, AppConstants.HTTPS_SKAT_DOWNLOAD_TOLDTARIF_FLATFILE_2, AppConstants.HTTPS_SKAT_DOWNLOAD_TOLDTARIF_FLATFILE_3, AppConstants.HTTPS_SKAT_DOWNLOAD_TOLDTARIF_FLATFILE_4 };
	private String targetDirectory = AppConstants.HTTPS_SKAT_DOWNLOAD_TOLDTARIF_TARGETDIR;
	/**
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 */
	@RequestMapping(value="skatmaintenancefelles_ttariffdownloadr.do", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView doDownloadSkatFiles(HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("skatmaintenancefelles_ttariffdownloadr");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		
		String doIt	= request.getParameter("doIt"); //only when the AS400 service will be executed
		
		Map model = new HashMap();
		if(appUser==null){
			return this.loginView;
		}else{
			List<String> fileList = Arrays.asList(this.sourceURLArray);
			if (this.strMgr.isNotNull(doIt)){
				try{
					for(String sourceURL : fileList){
						URL url = new URL(sourceURL);
						String fileName = sourceURL.substring(sourceURL.lastIndexOf('/') + 1, sourceURL.length());
						Path targetPath = new File(this.targetDirectory + File.separator + fileName).toPath();
						Files.copy(url.openStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
						logger.info(sourceURL + " successfully downloaded");
					}
					//At this point we have the files in the target directory
			    	//now run the AS400 service pgm
			    	StringBuffer errMsg = new StringBuffer();
		    		this.doIt(appUser.getUser(), errMsg);
		    		
		    		if(errMsg!=null && errMsg.length()>0){
		    			model.put(SkatMaintenanceConstants.ASPECT_ERROR_MESSAGE, errMsg.toString());
		    		}else{
		    			logger.info("Update on DK-toldtarif back-end successfully ended ...");
		    		}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			
	    	model.put(SkatMaintenanceConstants.DOMAIN_LIST, fileList);
	    	
	    	successView.addObject(SkatMaintenanceConstants.DOMAIN_MODEL , model);
	    	return successView;
		}
	}
	/**
	 * 
	 * @param applicationUser
	 * @param errMsg
	 */
	private void doIt(String applicationUser, StringBuffer errMsg){
			
			String BASE_URL = MaintenanceUrlDataStore.MAINTENANCE_BASE_DKG210R_RUN_DOWNLOAD_TOLTARIF_URL;
			StringBuffer urlRequestParams = new StringBuffer();
			urlRequestParams.append("user="+ applicationUser);

			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
	    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
	    	logger.info("URL PARAMS: " + urlRequestParams);
	    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
	    	//extract
	    	if(jsonPayload!=null){
				//lists this container is only as dummy in order to access the errMsg
	    		JsonMaintDktfiContainer container = this.maintDktfiService.getList(jsonPayload);
		        if(container!=null){
		        	if (this.strMgr.isNotNull(container.getErrMsg()) ){
		        		errMsg.append(container.getErrMsg());
		        	}
		        }
	    	}
	    	//TEST
	    	/*JsonMaintDktfiContainer container = new JsonMaintDktfiContainer();
	    	container.setErrMsg("ERROR COVI!!!");
	    	errMsg.append(container.getErrMsg());
			*/

	}
	
	//SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	
	@Qualifier ("maintDktfiService")
	private MaintDktfiService maintDktfiService;
	@Autowired
	@Required
	public void setMaintDktfiService (MaintDktfiService value){ this.maintDktfiService = value; }
	public MaintDktfiService getMaintDktfiService(){ return this.maintDktfiService; }
	
	
	@Qualifier ("skatMaintMainDropDownListPopulationService")
	private SkatMaintMainDropDownListPopulationService skatMaintMainDropDownListPopulationService;
	@Autowired
	@Required
	public void setSkatMaintMainDropDownListPopulationService (SkatMaintMainDropDownListPopulationService value){ this.skatMaintMainDropDownListPopulationService = value; }
	public SkatMaintMainDropDownListPopulationService getSkatMaintMainDropDownListPopulationService(){ return this.skatMaintMainDropDownListPopulationService; }
	
	
	@Qualifier 
	private MaintMainEdiiService maintMainEdiiService;
	@Autowired
	@Required	
	public void setMaintMainEdiiService(MaintMainEdiiService value){this.maintMainEdiiService = value;}
	public MaintMainEdiiService getMaintMainEdiiService(){ return this.maintMainEdiiService; }
	
	
	
	
}

