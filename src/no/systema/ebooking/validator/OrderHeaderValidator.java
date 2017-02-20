package no.systema.ebooking.validator;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.ebooking.model.jsonjackson.JsonMainOrderHeaderFraktbrevRecord;
import no.systema.ebooking.model.jsonjackson.JsonMainOrderHeaderRecord;

/**
 * 
 * @author oscardelatorre
 * @date Jan 07, 2017
 * 
 *
 */
public class OrderHeaderValidator implements Validator {
	private static final Logger logger = Logger.getLogger(OrderHeaderValidator.class.getName());
	
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return OrderHeaderValidator.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonMainOrderHeaderRecord record = (JsonMainOrderHeaderRecord)obj;
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "hereff", "systema.ebooking.orders.form.update.error.null.from.hereff");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "henas", "systema.ebooking.orders.form.update.error.null.shipper.name.henas");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "heads1", "systema.ebooking.orders.form.update.error.null.shipper.name.heads1");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "henak", "systema.ebooking.orders.form.update.error.null.consignee.name.henak");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "headk1", "systema.ebooking.orders.form.update.error.null.consignee.name.headk1");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "helka", "systema.ebooking.orders.form.update.error.null.from.helka");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "hesdf", "systema.ebooking.orders.form.update.error.null.from.hesdf");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "hetri", "systema.ebooking.orders.form.update.error.null.to.hetri");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "hesdt", "systema.ebooking.orders.form.update.error.null.to.hesdt");
		//Check for Mandatory fields
		
		if(record!=null){
			//Fakturapart
			if( (record.getHeknsf() !=null && !"".equals(record.getHeknsf())) && (record.getHeknkf()!=null && !"".equals(record.getHeknkf())) ){
				errors.rejectValue("heknsf", "systema.ebooking.orders.form.update.error.rule.both.invoicees.invalid");
			}
			//-----------------------------------------------------------------------------------------------------------
			//START Check References & Invoicees (one of them is always mandatory. In certain cases, both are mandatory)
			//These keys replaced hereff (ref.JOVO).
			//-----------------------------------------------------------------------------------------------------------
			if( (record.getHerfa()!=null && !"".equals(record.getHerfa())) || (record.getHerfk()!=null && !"".equals(record.getHerfk())) ){
				//OK. Go on with further validation
				//(2)
				if(this.isInvoiceeOnSeller(record)){
					if (record.getHerfa()==null || "".equals(record.getHerfa()) ){
						errors.rejectValue("herfa", "systema.ebooking.orders.form.update.error.rule.senderRef.mustExist");
					}
				}else{
					if(this.isInvoiceeOnReceiver(record)){
						if (record.getHerfk()==null || "".equals(record.getHerfk()) ){
							errors.rejectValue("herfa", "systema.ebooking.orders.form.update.error.rule.receiverRef.mustExist");
						}else{
							if (record.getHerfa()==null || "".equals(record.getHerfa()) ){
								errors.rejectValue("herfa", "systema.ebooking.orders.form.update.error.rule.both.senderAndReceiverRefs.mustExist");
							}
						}
					}
				}
			}else{
				errors.rejectValue("herfa", "systema.ebooking.orders.form.update.error.rule.senderOrReceiverRef.mustExist");
			}
			
			//Specific Invoicee validation
			if("M".equals(record.getXfakBet()) ){
				if(record.getHerfk()==null || "".equals(record.getHerfk()) ){
					errors.rejectValue("herfa", "systema.ebooking.orders.form.update.error.rule.receiverRef.mustExist");
				}
				if(record.getHeknkf()==null || "".equals(record.getHeknkf()) ){
					errors.rejectValue("heknsf", "systema.ebooking.orders.form.update.error.rule.receiversInvoicee.mustExist");
				}
				
			}else if("S".equals(record.getXfakBet()) ){
				if(record.getHerfa()==null || "".equals(record.getHerfa()) ){
					errors.rejectValue("herfa", "systema.ebooking.orders.form.update.error.rule.senderRef.mustExist");
				}
				if(record.getHeknsf()==null || "".equals(record.getHeknsf()) ){
					errors.rejectValue("heknsf", "systema.ebooking.orders.form.update.error.rule.sendersInvoicee.mustExist");
				}
			}
			//-----------------------------------
			//END Check References and Invoicees
			//-----------------------------------
			
			//Check that there is at least one item line
			if(this.itemLineRecordExist(record)){
				//OK = valid
			}else{
				errors.rejectValue("hereff", "systema.ebooking.orders.form.update.error.rule.itemLines.atleastOneLine.mustExist");
			}
		}
		
	}	
	/**
	 * 
	 * @param record
	 * @return
	 */
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
	/**
	 * 
	 * @param record
	 * @return
	 */
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
	
	/**
	 * At least one item line must exist
	 * 
	 * @param record
	 */
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
}
