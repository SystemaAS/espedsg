package no.systema.z.main.maintenance.util;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import no.systema.main.context.TdsAppContext;

/**
 * Help class accessing messages in message_<locale>.properties
 * 
 * @author Fredrik Möller
 * @date Apr 20, 2017
 *
 */
public class MessageSourceHelper {
	private static final Logger logger = Logger.getLogger(MessageSourceHelper.class.getName());

	private ApplicationContext context = null;
	private Locale locale = null;

	public MessageSourceHelper(HttpServletRequest request) {
		context = TdsAppContext.getApplicationContext();
		CookieLocaleResolver localeResolver = (CookieLocaleResolver) context.getBean("localeResolver");
		locale = localeResolver.resolveLocale(request);
	}
	
	
	/**
	 * Decode key/id into messages from message.properties.
	 * Using locale from CookieLocaleResolver
	 * 
	 * @param id, key in message.properties
	 * @param params
	 * @return decoded message
	 */
	public String getMessage(String id, Object[] params) {
		return context.getMessage(id, params, locale);
	}
	
}
