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
 * @date Aug 14, 2017
 * 
 *
 */
public class TrorOrderHeaderValidator implements Validator {
	private static final Logger logger = Logger.getLogger(TrorOrderHeaderValidator.class.getName());
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
		return TrorOrderHeaderValidator.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		//Check for Mandatory fields
		JsonTrorOrderHeaderRecord record = (JsonTrorOrderHeaderRecord)obj;
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "hereff", "systema.ebooking.orders.form.update.error.null.from.hereff");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "henas", "systema.tror.orders.form.update.error.null.shipper.name.henas");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "heads1", "systema.tror.orders.form.update.error.null.shipper.name.heads1");
		
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "henak", "systema.tror.orders.form.update.error.null.consignee.name.henak");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "headk1", "systema.tror.orders.form.update.error.null.consignee.name.headk1");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "helka", "systema.tror.orders.form.update.error.null.from.helka");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "hesdf", "systema.tror.orders.form.update.error.null.from.hesdf");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "hetri", "systema.tror.orders.form.update.error.null.to.hetri");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "hesdt", "systema.tror.orders.form.update.error.null.to.hesdt");
		
		
		//Check rules
		if(record!=null){
			//Godsnr (the number can not have empty fields in the precedent field. If field 2 is filled up then field 2 MUST be there ...
			if(strMgr.isNull(record.getOwnHegn1()) && (strMgr.isNotNull(record.getOwnHegn2()) || strMgr.isNotNull(record.getOwnHegn3()) ) ){
				errors.rejectValue("hegn", "systema.tror.orders.form.update.error.rule.godsnr.invalid");
			}else{
				if( (strMgr.isNotNull(record.getOwnHegn1()) && strMgr.isNull(record.getOwnHegn2()) ) && strMgr.isNotNull(record.getOwnHegn3()) ){	
					errors.rejectValue("hegn", "systema.tror.orders.form.update.error.rule.godsnr.invalid");
				}
			}
			
			//Fakturapart
			if( (strMgr.isNotNull(record.getHeknsf())) && (strMgr.isNotNull(record.getHeknkf())) ){
				//Removed after Trond's mail. It should be possible to have both invoice parts ...
				//errors.rejectValue("heknsf", "systema.tror.orders.form.update.error.rule.both.invoicees.invalid");
			}else{
				if( (strMgr.isNotNull(record.getHeknsf())) || (strMgr.isNotNull(record.getHeknkf())) ){
					//OK (at least one)
				}else{
					if( !"X".equals(record.getHekdfs()) && !"X".equals(record.getHekdfk()) ){
						errors.rejectValue("heknsf", "systema.tror.orders.form.update.error.rule.sendersOrReceiversInvoicee.mustExist");
					}else{
						//OK ... according to CB/TH
					}
				}
			}
			
			//-----------------------------------------------------------------------------------------------------------
			//START Check References & Invoicees (one of them is always mandatory. In certain cases, both are mandatory)
			//These keys replaced hereff (ref.JOVO).
			//-----------------------------------------------------------------------------------------------------------
			/*
			if( (record.getHerfa()!=null && !"".equals(record.getHerfa())) || (record.getHerfk()!=null && !"".equals(record.getHerfk())) ){
				//OK. Go on with further validation
				//(2)
				if(this.isInvoiceeOnSeller(record)){
					if (record.getHerfa()==null || "".equals(record.getHerfa()) ){
						herfaTrigger = true;
						errors.rejectValue("herfa", "systema.tror.orders.form.update.error.rule.senderRef.mustExist");
					}
				}else{
					if(this.isInvoiceeOnReceiver(record)){
						if (record.getHerfk()==null || "".equals(record.getHerfk()) ){
							errors.rejectValue("herfa", "systema.tror.orders.form.update.error.rule.receiverRef.mustExist");
						}else{
							if (record.getHerfa()==null || "".equals(record.getHerfa()) ){
								errors.rejectValue("herfa", "systema.tror.orders.form.update.error.rule.both.senderAndReceiverRefs.mustExist");
							}
						}
					}
				}
			}else{
				//OBSOLETE?--> errors.rejectValue("herfa", "systema.ebooking.orders.form.update.error.rule.senderOrReceiverRef.mustExist");
			}
			
			//Specific Invoicee validation
			if("M".equals(record.getXfakBet()) ){
				if(record.getHerfk()==null || "".equals(record.getHerfk()) ){
					errors.rejectValue("herfa", "systema.tror.orders.form.update.error.rule.receiverRef.mustExist");
				}
				if(record.getHeknkf()==null || "".equals(record.getHeknkf()) ){
					errors.rejectValue("heknsf", "systema.tror.orders.form.update.error.rule.receiversInvoicee.mustExist");
				}
				
			}else if("S".equals(record.getXfakBet()) ){
				if(!herfaTrigger){
					if(record.getHerfa()==null || "".equals(record.getHerfa()) ){
						errors.rejectValue("herfa", "systema.tror.orders.form.update.error.rule.senderRef.mustExist");
					}
				}
				
				if(record.getHeknsf()==null || "".equals(record.getHeknsf()) ){
					errors.rejectValue("heknsf", "systema.tror.orders.form.update.error.rule.sendersInvoicee.mustExist");
				}
			}
			*/
			//-----------------------------------
			//END Check References and Invoicees
			//-----------------------------------
			
			//Check validity of part id - Sender
			if(strMgr.isNotNull(record.getHekns())){
				if(!this.isValidPartId(record, record.getHekns())){
					errors.rejectValue("hekns", "systema.tror.orders.form.update.error.rule.sender.isNotValid");
				}
			}
			//Check validity of part id - Receiver
			if(strMgr.isNotNull(record.getHeknk())){
				if(!this.isValidPartId(record, record.getHeknk())){
					errors.rejectValue("heknk", "systema.tror.orders.form.update.error.rule.receiver.isNotValid");
				}
			}
			//Check validity of part id - Seller's invoicee
			if(strMgr.isNotNull(record.getHeknsf())){
				if(!this.isValidPartId(record, record.getHeknsf())){
					errors.rejectValue("heknsf", "systema.tror.orders.form.update.error.rule.sendersInvoicee.isNotValid");
				}
			}
			//Check validity of part id - Buyer's invoicee
			if(strMgr.isNotNull(record.getHeknkf())){
				if(!this.isValidPartId(record, record.getHeknkf())){
					errors.rejectValue("heknkf", "systema.tror.orders.form.update.error.rule.receiversInvoicee.isNotValid");
				}
			}
			/*
			//Check that there is at least one item line
			if(this.itemLineRecordExist(record)){
				//OK = valid
			}else{
				//check fraktbrev lines (if applicable)
				if(this.fraktbrevRecordExists(record.getFraktbrevRecord())){
					//OK go on
				}else{
					errors.rejectValue("hereff", "systema.tror.orders.form.update.error.rule.itemLines.atleastOneLine.mustExist");
				}
			}
			
			//Check Dangerous goods
			if(record.getFraktbrevRecord()!=null){
				JsonMainOrderHeaderFraktbrevRecord fr = record.getFraktbrevRecord();
				if(this.isNotNull(fr.getFfunnr()) ){
					if( !this.isNotNull(fr.getFfantk())){
						errors.rejectValue("frbPhantom", "systema.tror.orders.form.update.error.rule.itemLines.mandatoryFields.dangerousGoods.antal.mustExist");
					}else if( !this.isNotNull(fr.getFfante())){
						errors.rejectValue("frbPhantom", "systema.tror.orders.form.update.error.rule.itemLines.mandatoryFields.dangerousGoods.mengde.mustExist");
					}else if( !this.isNotNull(fr.getFfenh())){
						errors.rejectValue("frbPhantom", "systema.tror.orders.form.update.error.rule.itemLines.mandatoryFields.dangerousGoods.enhet.mustExist");
					}
				}
			}
			
			//Check validity of email address
			if(record.getWsmail()!=null && !"".equals(record.getWsmail())){
				if( !this.emailValidator.validateEmail(record.getWsmail()) ){
					errors.rejectValue("wsmail", "systema.tror.orders.form.update.error.rule.email.isNotValid");
				}
			}
			//check fraktbrev lines (if applicable)
			if(this.fraktbrevRecordExists(record.getFraktbrevRecord())){
				if(!this.validMandatoryFieldsFraktbrev(record.getFraktbrevRecord())){
					errors.rejectValue("frbPhantom", "systema.tror.orders.form.update.error.rule.itemLines.mandatoryFields.mustExist");
				}
			}
			*/
		}
		
	}	
	/**
	 * 
	 * @param record
	 * @return
	 */
	/*
	private boolean isInvoiceeOnSeller(JsonMainOrderHeaderRecord record){
		boolean retval = false;
		if( (record.getTrknfa().equals(record.getHekns())) || (record.getTrknfa().equals(record.getHeknsf())) ){
			retval = true;
		}
		if( (record.getHekns()!=null && !"".equals(record.getHekns())) && (record.getHeknsf()!=null && !"".equals(record.getHeknsf())) ){
			retval = true;
		}
		return retval;
		
	}
	*/
	/**
	 * 
	 * @param record
	 * @return
	 */
	/*
	private boolean isInvoiceeOnReceiver(JsonMainOrderHeaderRecord record){
		boolean retval = false;
		if( (record.getTrknfa().equals(record.getHeknk())) || (record.getTrknfa().equals(record.getHeknkf())) ){
			retval = true;
		}
		if( (record.getHeknk()!=null && !"".equals(record.getHeknk())) && (record.getHeknkf()!=null && !"".equals(record.getHeknkf())) ){
			retval = true;
		}
		return retval;
		
	}
	*/
	/**
	 * At least one item line must exist
	 * 
	 * @param record
	 */
	/*
	private boolean itemLineRecordExist(JsonMainOrderHeaderRecord record){
		boolean retval = false;
		
		if(record!=null && record.getFraktbrevList() !=null){
		    for(JsonMainOrderHeaderFraktbrevRecord itemLine : record.getFraktbrevList()){
		    	if( (itemLine.getFvant()!=null && !"".equals(itemLine.getFvant())) && 
		    		(itemLine.getFvvt()!=null && !"".equals(itemLine.getFvvt())) &&
		    		(itemLine.getFvvkt()!=null && !"".equals(itemLine.getFvvkt())) ){
		    		retval = true;
		    	}
		    	break;
		    }
		}
		return retval;
	}
	*/
	
	/**
	 * 
	 * @param fraktbrevRecord
	 * @return
	 */
	/*
	private boolean fraktbrevRecordExists(JsonMainOrderHeaderFraktbrevRecord fraktbrevRecord){
		boolean retval = false;
		//Check if the end user has enter at least one field in the fraktbrev record. 
		//It is the only way to elucidate if the end user wants to input a fraktbrev record.
		if( this.isNotNull(fraktbrevRecord.getFvlinr()) || this.isNotNull(fraktbrevRecord.getFvant())  || this.isNotNull(fraktbrevRecord.getFvvkt()) || 
			this.isNotNull(fraktbrevRecord.getFvvt()) || this.isNotNull(fraktbrevRecord.getFmmrk1())  || this.isNotNull(fraktbrevRecord.getFvpakn()) || 
			this.isNotNull(fraktbrevRecord.getFvlen()) || this.isNotNull(fraktbrevRecord.getFvbrd())  || this.isNotNull(fraktbrevRecord.getFvhoy()) || 
			this.isNotNull(fraktbrevRecord.getFvvol()) || this.isNotNull(fraktbrevRecord.getFvlm()) ){
			retval = true;
		}
		return retval;
	}
	*/
	/**
	 * 
	 * @param fraktbrevRecord
	 * @return
	 */
	/*
	private boolean validMandatoryFieldsFraktbrev(JsonMainOrderHeaderFraktbrevRecord fraktbrevRecord){
		boolean retval = false;
		if( this.isNotNull(fraktbrevRecord.getFvant())  && this.isNotNull(fraktbrevRecord.getFvvt())  && this.isNotNull(fraktbrevRecord.getFvvkt()) ){
			retval = true;
		}
		return retval;
	}
	*/
	
	/**
	 * 
	 * @param record
	 * @param partId
	 * @return
	 */
	
	private boolean isValidPartId(JsonTrorOrderHeaderRecord record, String partId){
		boolean retval = false;
		
		//If the part is the same as the customer (login customer)
		if(partId!=null && partId.equals(record.getTrknfa())){
			retval = true;
		}else{
			//prepare the access CGI with RPG back-end
			String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SYCUNDFR_GET_LIST_URL;
			String urlRequestParamsKeys = "user=" + record.getApplicationUser() + "&kundnr=" + partId;
			logger.info("URL: " + BASE_URL);
			logger.info("PARAMS: " + urlRequestParamsKeys);
			logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
			String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
			//Debug -->
	    	//logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
			logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    
			if(jsonPayload!=null){
				JsonMaintMainCundfContainer container = this.maintMainCundfService.getList(jsonPayload);
	    		if(container!=null){
	    			for(JsonMaintMainCundfRecord  cusRecord : container.getList()){
	    				if(cusRecord.getKundnr().equals(partId)){
	    					retval = true;
	    				}
	    			}
	    		}
			}
		}
		return retval;
		
		
		
		
		
		
	}
	
}
