package no.systema.overview.ufortolledeoppdrag.controller.dlink;


import java.util.*;

import org.apache.log4j.Logger;
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
import org.springframework.context.annotation.Scope;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.springframework.web.bind.WebDataBinder;
//import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

//import no.systema.tds.service.MainHdTopicService;
import no.systema.main.validator.UserValidator;

//application imports
import no.systema.main.model.SystemaWebUser;
import no.systema.main.model.jsonjackson.JsonSystemaUserContainer;
import no.systema.main.model.jsonjackson.JsonSystemaUserRecord;

import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.service.login.SystemaWebLoginService;

import no.systema.main.url.store.MainUrlDataStore;
import no.systema.main.util.AppConstants;



/**
 * Direct link controller for Uoppdrag-Systema Web esped's module
 * 
 * This controller is used when a user wants to directly bypass the login-dashboard mechanism into a specific web page
 * Usually used when a non-user system makes the call to the web-site thus being required to bypass the login mechanism in a 
 * user-human friendly manner.
 * 
 * @author oscardelatorre
 * @date Aug 28, 2014
 * 
 *
 */

@Controller
@Scope("session")
public class UoppdragDirectLinkController {
	private static final Logger logger = Logger.getLogger(UoppdragDirectLinkController.class.getName());
	
	private ModelAndView loginView = new ModelAndView("login");
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new UserValidator());
    }
	
	/**
	 * Gate point for the direct link (Dlink) of this module. (Requirement initiated by PostNord-NO)
	 * The system/user should use the following Dlink: 
	 * 
	 * http://localhost:8080/espedsg/uoppdraggateDlink.do?ru=youruser&dp=yourpassword
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="uoppdraggateDlink.do", method=RequestMethod.GET)
	public ModelAndView goToDlink(HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("redirect:uoppdraggate.do?deepSubmit=do");
		Map model = new HashMap();
		
		SystemaWebUser appUser = new SystemaWebUser();
		String user = request.getParameter("ru");
		String password = request.getParameter("dp");
		
		if( (user!=null && !"".equals(user)) && (password!=null && !"".equals(password)) ){
			appUser.setUser(user.toUpperCase());
			appUser.setPassword(password);
		}else{
	    		String msg = "NO USER/PASSWORD on Dlink-string";
			logger.info("[ERROR Fatal] " + msg);
			this.setFatalAS400LoginError(model, msg);
				this.loginView.addObject("model", model);
			
			return loginView;
		}
		
		//---------------------------
		//get BASE URL = RPG-PROGRAM
        //---------------------------
		String BASE_URL = MainUrlDataStore.SYSTEMA_WEB_LOGIN_URL;
		
		//url params
		String urlRequestParamsKeys = this.getRequestUrlKeyParameters(appUser);
		//don't show the pwd
	    	int pwd = urlRequestParamsKeys.indexOf("&pwd");
	    	String credentailsPwd = urlRequestParamsKeys.substring(pwd + 5);
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
	    	logger.info("URL: " + BASE_URL);
	    	logger.info("URL PARAMS: " + urlRequestParamsKeys.substring(0,pwd)+"&md5");
	    	
	    	//--------------------------------------
	    	//EXECUTE the FETCH (RPG program) here
	    	//--------------------------------------
	    	try{
		    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
			//Debug --> 
		    	//System.out.println(jsonPayload);
		    	logger.info(jsonPayload);
		    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp "+ new StringBuilder(credentailsPwd).reverse().toString() + "carebum");
		    	if(jsonPayload!=null){ 
		    		JsonSystemaUserContainer jsonSystemaUserContainer = this.systemaWebLoginService.getSystemaUserContainer(jsonPayload);
		    		//check for errors
		    		if(jsonSystemaUserContainer!=null){
		    			if(jsonSystemaUserContainer.getErrMsg()!=null && !"".equals(jsonSystemaUserContainer.getErrMsg())){
		    				logger.info("[ERROR Fatal] User not valid (user/password) Check your credentials...");
		    				this.setFatalAS400LoginError(model, jsonSystemaUserContainer.getErrMsg());
		    				this.loginView.addObject("model", model);
		    				return this.loginView;
		    			}else{
		    				this.setDashboardMenuObjectsInSession(session, jsonSystemaUserContainer);
		    				//hand-over to appUser from JsonUser
		    				this.doHandOverToSystemaWebUser(request, appUser, jsonSystemaUserContainer);
		    			}
		    		}
		    	}else{
		    		String msg = "NO CONTENT on jsonPayload";
				logger.info("[ERROR Fatal] " + msg);
				this.setFatalAS400LoginError(model, msg);
					this.loginView.addObject("model", model);
				
				return loginView;
			}
		    	session.setAttribute(AppConstants.SYSTEMA_WEB_USER_KEY, appUser);
	    	}catch(Exception e){
	    		String msg = "NO CONNECTION";
			logger.info("[ERROR Fatal] NO CONNECTION ");
			this.setFatalAS400LoginError(model, msg);
				this.loginView.addObject("model", model);
			
			return loginView;
	    		
	    	}
	    	return successView;
		
		
	}
	
	/**
	 * This is the only place in which the jsonUserContainer lends over its values to the global SystemWebUser object
	 * @param request
	 * @param appUser
	 * @param jsonSystemaUserContainer
	 * 
	 */
	private void doHandOverToSystemaWebUser(HttpServletRequest request, SystemaWebUser appUser, JsonSystemaUserContainer jsonSystemaUserContainer){
		
		//user values
		appUser.setUser(jsonSystemaUserContainer.getUser().toUpperCase());
		appUser.setUserName(jsonSystemaUserContainer.getUserName());
		appUser.setUsrLang(jsonSystemaUserContainer.getUsrLang());
		appUser.setUserAS400(jsonSystemaUserContainer.getUsrAS400());
		//customer values
		appUser.setCustNr(jsonSystemaUserContainer.getCustNr());
		appUser.setCustName(jsonSystemaUserContainer.getCustName());
		
		//logo and banner
		appUser.setLogo(jsonSystemaUserContainer.getLogo());
		appUser.setBanner(jsonSystemaUserContainer.getBanner());
		appUser.setSystemaLogo(jsonSystemaUserContainer.getSystemaLogo());
		//logger.info("[INFO] user logo:" + appUser.getLogo() );
		//logger.info("[INFO] user banner:" + appUser.getBanner() );
		//logger.info("[INFO] user sign:" + appUser.getTdsSign() );
		//logger.info("[INFO] user Systema logo:" + appUser.getSystemaLogo() );
		
		//end user - login url
		//logger.info("[INFO] servlet host (on Login):" + appUser.getServletHost() );
		
		//This host parameter below is used for reaching external resources since images or other static resources
		//might be available either for internal ip-addresses or external but not both.
		//If the user reaches the login-page then he/she will reach static resources on this ip-address
		String host = this.getServletHostWithNoPort(request.getHeader("Host"));
		appUser.setServletHostWithoutHttpPrefix(host);
		appUser.setServletHost("http://" + host);
				
		
	}
	
	/**
	 * 
	 * @param rawValue
	 * @return
	 */
	private String getServletHostWithNoPort(String rawValue){
		String retval = rawValue;
		if(rawValue!=null){
			if(rawValue.contains(":")){
				int end = rawValue.indexOf(":");
				retval = rawValue.substring(0,end);
			}
		}
		
		return retval;
	}
	
	/**
	 * 
	 * @param appUser
	 * @return
	 */
	private String getRequestUrlKeyParameters(SystemaWebUser appUser){
		StringBuffer urlRequestParamsKeys = null;
		
		if(appUser!=null){
			if( (appUser.getUser()!=null && !"".equals(appUser.getUser())) && (appUser.getPassword()!=null && !"".equals(appUser.getPassword()))){
				urlRequestParamsKeys = new StringBuffer();
				urlRequestParamsKeys.append("user=" + appUser.getUser());
				urlRequestParamsKeys.append(AppConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "pwd=" + appUser.getPassword());
				
			}
		}
		return urlRequestParamsKeys.toString();
	}
	
	/**
	 * Sets the domain objects (menu of program lists) in session. 
	 * Must be in session since different applications must get back to the dashboard.
	 *  
	 * @param session
	 * @param model
	 * @param jsonSystemaUserContainer
	 */
	private void setDashboardMenuObjectsInSession(HttpSession session, JsonSystemaUserContainer jsonSystemaUserContainer){
		//SET HEADER RECORDS  (from RPG)
		List list = new ArrayList();
		for (JsonSystemaUserRecord record : jsonSystemaUserContainer.getMenuList()){
			list.add(record);
			//logger.info("PrTxt:" + record.getPrTxt());
			//logger.info("Prog:" + record.getProg());
		}
		//model.put(Constants.DOMAIN_LIST, list);
		session.setAttribute(AppConstants.DOMAIN_LIST, list);

	}
	
	
	
	/**
	 * 
	 * @param model
	 * @param errorMessage
	 */
	private void setFatalAS400LoginError(Map model, String errorMessage){
		String errorAS400Suffix = " [AS400 error]";
		model.put(AppConstants.ASPECT_ERROR_MESSAGE, errorMessage + errorAS400Suffix);
	}
	
	//SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	
	@Qualifier ("systemaWebLoginService")
	private SystemaWebLoginService systemaWebLoginService;
	@Autowired
	@Required
	public void setSystemaWebLoginService (SystemaWebLoginService value){ this.systemaWebLoginService = value; }
	public SystemaWebLoginService getSystemaWebLoginService(){ return this.systemaWebLoginService; }
		
		
}

	