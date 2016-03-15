/**
 * 
 */
package no.systema.overview.ufortolledeoppdrag.controller.ajax;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;
import java.util.*;

import javax.servlet.http.HttpSession;


/**
 * @author oscardelatorre
 *
 */
@Controller
public class UoppdragAjaxHandlerController {
	private static final Logger logger = Logger.getLogger(UoppdragAjaxHandlerController.class.getName());
	
	@RequestMapping(value = "helloUoppdragAjaxHandler.do", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody String getMessage(@RequestParam String id, HttpSession session) {
	  logger.info("Inside getMessage on UoppdragAjaxHandlerController!");
	  String result = "Hello " + id + " is " + new Date().toString() + " SessionId: " + session.getId();
	  logger.info(result);
	  
	  return result;
	}

	  
}
