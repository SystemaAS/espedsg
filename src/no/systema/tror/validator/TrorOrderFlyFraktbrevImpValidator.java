package no.systema.tror.validator;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;


import no.systema.main.util.StringManager;
import no.systema.tror.model.jsonjackson.JsonTrorOrderHeaderRecord;
import no.systema.jservices.common.dao.DokefimDao;
import no.systema.tror.url.store.TrorUrlDataStore;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainCundfContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainCundfRecord;
import no.systema.z.main.maintenance.url.store.MaintenanceMainUrlDataStore;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.service.UrlCgiProxyServiceImpl;
import no.systema.main.validator.EmailValidator;
import no.systema.z.main.maintenance.service.MaintMainCundfService;
import no.systema.z.main.maintenance.service.MaintMainCundfServiceImpl;


/**
 * 
 * @author oscardelatorre
 * @date Feb 21, 2018
 * 
 *
 */
public class TrorOrderFlyFraktbrevImpValidator implements Validator {
	private static final Logger logger = Logger.getLogger(TrorOrderFlyFraktbrevImpValidator.class.getName());
	//Init services here
	//private EbookingChildWindowService ebookingChildWindowService = new EbookingChildWindowServiceImpl();
	private UrlCgiProxyService urlCgiProxyService = new UrlCgiProxyServiceImpl();
	private MaintMainCundfService maintMainCundfService = new MaintMainCundfServiceImpl();
	
	//private EmailValidator emailValidator = new EmailValidator();
	private StringManager strMgr = new StringManager();
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return TrorOrderFlyFraktbrevImpValidator.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) {
		//Check for Mandatory fields
		DokefimDao record = (DokefimDao)obj;
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "henas", "systema.tror.orders.form.update.error.null.shipper.name.henas");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "heads1", "systema.tror.orders.form.update.error.null.shipper.name.heads1");
		//TODO ??
		
		
		//Check rules
		if(record!=null){
			/*
			//Oppkrav fält
			if(strMgr.isNotNull(record.getDfkde()) ){
				if(strMgr.isNull(record.getDflevi()) ){
					errors.rejectValue("dfkde", "systema.tror.fraktbrev.form.update.error.rule.telefon.mustExist");
				}
			}
			//Antall merkelapper
			if(record.getDfntla() != record.getDfnt() ){
				if(record.getDfntla() > record.getDfnt() ){
					errors.rejectValue("dfntla", "systema.tror.fraktbrev.form.update.error.rule.merkelapp.toBig");
				}
			}
			//Annen fraktbetaler
			if(record.getDfbela().equals("A") ){
				if( strMgr.isNotNull(record.getDfnavx()) && strMgr.isNotNull(record.getDfad1x()) ){
					//Ok
				}else{
					errors.rejectValue("dfnavx", "systema.tror.fraktbrev.form.update.error.rule.annenfraktbetaler.mustExist");
				}
			}
			*/
		}
		
	}	
	
	
}
