package no.systema.main.controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.ui.Model;
import org.springframework.context.annotation.Scope;
import org.apache.log4j.Logger;




@Controller
/*@SessionAttributes(Constants.APP_USER_KEY)
@Scope("session")*/
public class LoginController {
	private static final Logger logger = Logger.getLogger(LoginController.class.getName());
	
	private ModelAndView loginView = new ModelAndView("login");

	//The [*.do] suffix is just an arbitrary suffix that could be something else. 
	//If you change it here then it MUST be the same that is used
	//in the JSP or other view (href or other redirect) that is calling this Controller
	@RequestMapping("login.do")
	public ModelAndView login(Model model){
		logger.info("Before login controller execution");
		
		String message = "Welcome till Systema eSped";
		model.addAttribute("messageTag", message);
		logger.info("After login controller execution");
		
		return this.loginView;
	}
    
}

