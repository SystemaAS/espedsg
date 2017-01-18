package no.systema.skat.nctsexport.validator;

import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.service.UrlCgiProxyServiceImpl;
import no.systema.main.util.DateTimeManager;
import no.systema.main.validator.DateValidator;
import no.systema.skat.nctsexport.service.SkatNctsExportSpecificTopicService;
import no.systema.skat.nctsexport.service.SkatNctsExportSpecificTopicServiceImpl;

import no.systema.skat.nctsexport.model.jsonjackson.topic.JsonSkatNctsExportSpecificTopicRecord;
import no.systema.skat.nctsexport.model.jsonjackson.topic.validation.JsonSkatNctsExportSpecificTopicGuaranteeValidatorContainer;

import no.systema.skat.nctsexport.url.store.SkatNctsExportUrlDataStore;
import no.systema.skat.util.SkatConstants;

/**
 * 
 * @author oscardelatorre
 * @date Apr 15, 2014
 * 
 *
 */
public class SkatNctsExportHeaderValidator implements Validator {
	private static final Logger logger = Logger.getLogger(SkatNctsExportHeaderValidator.class.getName());
	//Intantiate services here since we are not capable to configure injection with Autowired. Check that further...
	private UrlCgiProxyService urlCgiProxyService = new UrlCgiProxyServiceImpl();
	private SkatNctsExportSpecificTopicService nctsExportSpecificTopicService = new SkatNctsExportSpecificTopicServiceImpl();
	   
	
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonSkatNctsExportSpecificTopicRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonSkatNctsExportSpecificTopicRecord record = (JsonSkatNctsExportSpecificTopicRecord)obj;
		logger.info("Inside SkatNctsExportHeaderValidator");
		
		//Check for Mandatory fields first
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thdk", "systema.skat.ncts.export.header.error.null.thdk"); 
		//Avsender
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thnas", "systema.skat.ncts.export.header.error.null.thnas"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thtins", "systema.skat.ncts.export.header.error.null.thtins"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thads1", "systema.skat.ncts.export.header.error.null.thads1"); 
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thsks", "systema.skat.ncts.export.header.error.null.thsks"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thpns", "systema.skat.ncts.export.header.error.null.thpns"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thpss", "systema.skat.ncts.export.header.error.null.thpss"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thlks", "systema.skat.ncts.export.header.error.null.thlks"); 
		//Modtager
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thnak", "systema.skat.ncts.export.header.error.null.thnak"); 
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thtins", "systema.skat.ncts.export.header.error.null.thtink"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thadk1", "systema.skat.ncts.export.header.error.null.thadk1"); 
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thskk", "systema.skat.ncts.export.header.error.null.thskk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thpnk", "systema.skat.ncts.export.header.error.null.thpnk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thpsk", "systema.skat.ncts.export.header.error.null.thpsk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thlkk", "systema.skat.ncts.export.header.error.null.thlkk");
		
		//ansvarig
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thnaa", "systema.skat.ncts.export.header.error.null.thnaa"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thtina", "systema.skat.ncts.export.header.error.null.thtina"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thlka", "systema.skat.ncts.export.header.error.null.thlka"); 
		
		//header
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thavd", "systema.skat.ncts.export.header.error.null.thavd"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thsg", "systema.skat.ncts.export.header.error.null.thsg");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thtrmi", "systema.skat.ncts.export.header.error.null.thtrmi");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thalk", "systema.skat.ncts.export.header.error.null.thalk");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thblk", "systema.skat.ncts.export.header.error.null.thblk");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thtaid", "systema.skat.ncts.export.header.error.null.thtaid");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thtalk", "systema.skat.ncts.export.header.error.null.thtalk");
		
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thtgid", "systema.skat.ncts.export.header.error.null.thtgid");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thtglk", "systema.skat.ncts.export.header.error.null.thtglk");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thtrm", "systema.skat.ncts.export.header.error.null.thtrm");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thcats", "systema.skat.ncts.export.header.error.null.thcats");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thskfd", "systema.skat.ncts.export.header.error.null.thskfd");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thdst", "systema.skat.ncts.export.header.error.null.thdst");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thdsk", "systema.skat.ncts.export.header.error.null.thdsk");
		
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thtsd1", "systema.skat.ncts.export.header.error.null.thtsd1");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thtsb", "systema.skat.ncts.export.header.error.null.thtsb");
		//Deadline mandatory with special requirement
		if( "J".equals(record.getThenkl()) ){
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thddt", "systema.skat.ncts.export.header.error.null.thddt");
		}
		
		//Logical controls if we passed the NOT NULL errors
		if(!errors.hasFieldErrors()){
			if(record!=null){
				//-----------------
				//Date validations
				//-----------------
				if(record.getThtrdt()!=null && !"".equals(record.getThtrdt())){
					if(!this.isValidDate(record.getThtrdt())){
						errors.rejectValue("thtrdt", "systema.skat.ncts.export.header.error.rule.thtrdt.invalid.date.format");
						logger.info("ERROR thtrdt");
					}
				}
				if(record.getThddtk()!=null && !"".equals(record.getThddtk())){
					if(!this.isValidDate(record.getThddtk())){
						errors.rejectValue("thddtk", "systema.skat.ncts.export.header.error.rule.thddtk.invalid.date.format");
						logger.info("ERROR thddtk");

					}
				}
				if(record.getThddt()!=null && !"".equals(record.getThddt())){
					if(!this.isValidDate(record.getThddt())){
						errors.rejectValue("thddt", "systema.skat.ncts.export.header.error.rule.thddt.invalid.date.format");
						logger.info("ERROR thddt");
					}
				}
				
				if(record.getThtins()!=null && !"".equals(record.getThtins())){
					if(!this.isValidSenderEori(record.getThtins())){
						errors.rejectValue("thddt", "systema.skat.ncts.export.header.error.rule.cvrNrPrefixSender");
						logger.info("ERROR thddt");
					}
				}
				//------------------------------------------------------------------
				//Förseglingsrelaterade fält (all 3 fields must be filled or empty)
				//------------------------------------------------------------------				
				if(record.getThdfkd()!=null && !"".equals(record.getThdfkd())){
					if(record.getThdfsk()==null || "".equals(record.getThdfsk())){
						errors.rejectValue("thdfsk", "systema.skat.ncts.export.header.error.rule.thdfkd.threerelatedfields");
						logger.info("ERROR thdfsk");
					}else if (record.getThdant()==null || "".equals(record.getThdant())){
						errors.rejectValue("thdant", "systema.skat.ncts.export.header.error.rule.thdfkd.threerelatedfields");
						logger.info("ERROR thdant");
					}
				}
				if(record.getThdfsk()!=null && !"".equals(record.getThdfsk())){
					if(record.getThdfkd()==null || "".equals(record.getThdfkd())){
						errors.rejectValue("thdfkd", "systema.skat.ncts.export.header.error.rule.thdfkd.threerelatedfields");
						logger.info("ERROR thdfkd");
					}else if (record.getThdant()==null || "".equals(record.getThdant())){
						errors.rejectValue("thdant", "systema.skat.ncts.export.header.error.rule.thdfkd.threerelatedfields");
						logger.info("ERROR thdant");
					}
				}
				if(record.getThdant()!=null && !"".equals(record.getThdant())){
					if(record.getThdfkd()==null || "".equals(record.getThdfkd())){
						errors.rejectValue("thdfkd", "systema.skat.ncts.export.header.error.rule.thdfkd.threerelatedfields");
						logger.info("ERROR thdfkd");
					}else if (record.getThdfsk()==null || "".equals(record.getThdfsk())){
						errors.rejectValue("thdfsk", "systema.skat.ncts.export.header.error.rule.thdfkd.threerelatedfields");
						logger.info("ERROR thdfsk");
					}
				}
				
				//-----------------
				//Kontrollresultat
				//-----------------
				if(record.getThenkl()!=null && "J".equals(record.getThenkl())){
					if(record.getThdkr()==null || "".equals (record.getThdkr())){
						errors.rejectValue("thdkr", "systema.skat.ncts.export.header.error.null.thdkr");
						logger.info("ERROR thdkr");
					}
				}
				
				/*
				//Transitkontor //under observation
				if(record.getThdkr()!=null && "A3".equals(record.getThdkr())){
					if( record.getThalk()!=null && record.getThblk()!=null){
						if(record.getThalk().equals(record.getThblk())){
							//nothing
						}else{
							if(record.getThtsd1()==null || "".equals (record.getThtsd1())){
								errors.rejectValue("thtsd1", "systema.skat.ncts.export.header.error.rule.thtsd1.atleastone");
								logger.info("ERROR thtsd1");
							}
						}
					}
				}*/
				
				//Transitkontor mandatory vid förenklad procedure
				if(record.getThenkl()!=null && "J".equals(record.getThenkl())){
					if(record.getThtsd1()==null || "".equals (record.getThtsd1())){
						//to be assessed
						//errors.rejectValue("thtsd1", "systema.skat.ncts.export.header.error.rule.thtsd1.atleastone");
					}else{
						if("SS".equals(record.getThdk())){
							if(!"1".equals(record.getThsik())){
								errors.rejectValue("thsik", "systema.skat.ncts.export.header.error.rule.thsik.sikkerhed.indicator.mandatory");
							}else{
								//relationship with Ank.dato og tid... only when indicator = 1
								if( (record.getThdta()!=null && !"".equals (record.getThdta())) && (record.getThtma()!=null && !"".equals (record.getThtma())) ){
									
									//check logical validity of Ank.dato
									DateTimeManager dateTimeMgr = new DateTimeManager();
									boolean isValidDate = dateTimeMgr.isValidCurrentAndForwardDate(record.getThdta(), "yyyyMMdd");
									boolean isValidTime = dateTimeMgr.isValidForwardTime(record.getThtma(), "HHmm");
									if(!isValidDate){
										errors.rejectValue("thdta", "systema.skat.ncts.export.header.error.rule.ankomstDatoThdtaNotValid"); 
									}
									if(!isValidTime){
										errors.rejectValue("thtma", "systema.skat.ncts.export.header.error.rule.ankomstTidThtmaNotValid"); 
									}
									
								}else{
									errors.rejectValue("thdta", "systema.skat.ncts.export.header.error.rule.thdta.dateAndTime.mandatory");
								}
							}
						}
					}
				}
				
				//--------
				//Garanti
				//--------
				//Garanti
				if(!"SS".equals(record.getThdk())){
					if(record.getThgft1()==null || "".equals(record.getThgft1())){
						if(record.getThgft2()==null || "".equals (record.getThgft2())){
							errors.rejectValue("thgft1", "systema.skat.ncts.export.header.error.rule.thgft1.atleastone");
							logger.info("ERROR thgft1");
						}
					}
				}
				
				//Garantikoder
				String errMsg = this.isValidGuarantee(record);
				if(errMsg!=null){
					errors.reject("thgft1", "Garantifel: " + errMsg);
				}
				
				//Begränsnings land
				if("1".equals(record.getThgbgi())){
					if(record.getThgbgu()==null || "".equals(record.getThgbgu())){
						errors.rejectValue("thgbgu", "systema.skat.ncts.export.header.error.rule.thgbgu.countrycode");
					}
				}
				
				//Godslokalkod
				if(!"".equals(record.getThlsd())){
					if(!"".equals(record.getThlasd())){
						//OK
					}else{
						errors.rejectValue("thlasd", "systema.skat.ncts.export.header.error.rule.thlasd.placeOfLoading.mandatory");
					}
				}
				
				//Sikkerhed = 1
				
				String SIKKERHED_ACTIVE = "1";
				if(SIKKERHED_ACTIVE.equals(record.getThsik())){
					//Rubrik 27
					if(!"".equals(record.getThlasd())){
						//OK
					}else{
						errors.rejectValue("thlasd", "systema.skat.ncts.export.header.error.rule.thlasd.placeOfLoading.mandatory");
					}
					//
					if("B".equals(record.getThspom()) || "E".equals(record.getThspom())){
						if(!"".equals(record.getThlosd())){
							//valid
						}else{
							errors.rejectValue("thlasd", "systema.skat.ncts.export.header.error.rule.thlosd.placeOfUnloading.mandatory");
						}
					}
					if(!"".equals(record.getThdta())){
						//OK
					}else{
						errors.rejectValue("thdta", "systema.skat.ncts.export.header.error.rule.thdta.dateAndTime.mandatory");
					}
					if(!"".equals(record.getThtma())){
						//OK
					}else{
						errors.rejectValue("thtma", "systema.skat.ncts.export.header.error.rule.thdta.dateAndTime.mandatory");
					}
					//Related field //Commercial Ref.
					/* To be assessed by Bo
					if(!"".equals(record.getThkref())){
						//OK
					}else{
						errors.rejectValue("thkref", "systema.skat.ncts.export.header.error.rule.thkref.commercialRef.mandatory");
					}
					//Realted field // Transport Ref.
					if(!"".equals(record.getThtref())){
						//OK
					}else{
						errors.rejectValue("thtref", "systema.skat.ncts.export.header.error.rule.thtref.transportRef.mandatory");
					}*/
				}
				
				//------------------------------------
				//Sikkerhed parter validations on TIN
				//------------------------------------
				//Security - Afsender
				if(this.securitySenderExists(record)){
					if(!this.isValidSecuritySender(record)){
						errors.rejectValue("thtinss", "systema.skat.ncts.export.header.error.rule.thtinss.sender.otherThanTinInfo.mandatory");
					}
				}
				//Security - Receiver
				if(this.securityReceiverExists(record)){
					if(!this.isValidSecurityReceiver(record)){
						errors.rejectValue("thtinks", "systema.skat.ncts.export.header.error.rule.thtinks.receiver.otherThanTinInfo.mandatory");
					}
				}
				//Security - Carrier
				if(this.securityCarrierExists(record)){
					if(!this.isValidSecurityCarrier(record)){
						errors.rejectValue("thtint", "systema.skat.ncts.export.header.error.rule.thtinks.carrier.otherThanTinInfo.mandatory");
					}
				}
			}
		}
	}
	/**
	 * 
	 * @param record
	 * @return
	 */
	private boolean securitySenderExists(JsonSkatNctsExportSpecificTopicRecord record){
		boolean retval = false;
		if(!"".equals(record.getThtinss()) || !"".equals(record.getThnass()) || !"".equals(record.getThadss1()) || !"".equals(record.getThpsss()) || 
				   !"".equals(record.getThpnss()) || !"".equals(record.getThlkss()) ) {
			retval = true;
		}
		
		return retval;
	}
	/**
	 * 
	 * @param record
	 * @return
	 */
	private boolean isValidSecuritySender(JsonSkatNctsExportSpecificTopicRecord record){
		boolean retval = false;
		if( record.getThtinss()!=null && !"".equals(record.getThtinss())){
			retval = true;
		}else{
			if(!"".equals(record.getThnass()) && !"".equals(record.getThadss1()) && !"".equals(record.getThpsss()) && 
			   !"".equals(record.getThpnss()) && !"".equals(record.getThlkss())){
				retval = true;
			}
		}
		return retval;
	}
	
	
	/**
	 * 
	 * @param record
	 * @return
	 */
	private boolean securityReceiverExists(JsonSkatNctsExportSpecificTopicRecord record){
		boolean retval = false;
		if(!"".equals(record.getThtinks()) || !"".equals(record.getThnaks()) || !"".equals(record.getThadks1()) || !"".equals(record.getThpsks()) || 
				   !"".equals(record.getThpnks()) || !"".equals(record.getThlkks()) ) {
			retval = true;
		}
		return retval;
	}
	/**
	 * 
	 * @param record
	 * @return
	 */
	private boolean isValidSecurityReceiver(JsonSkatNctsExportSpecificTopicRecord record){
		boolean retval = false;
		if( record.getThtinks()!=null && !"".equals(record.getThtinks())){
			retval = true;
		}else{
			if(!"".equals(record.getThnaks()) && !"".equals(record.getThadks1()) && !"".equals(record.getThpsks()) && 
			   !"".equals(record.getThpnks()) && !"".equals(record.getThlkks())){
				retval = true;
			}
		}
		return retval;
	}

	/**
	 * 
	 * @param record
	 * @return
	 */
	private boolean securityCarrierExists(JsonSkatNctsExportSpecificTopicRecord record){
		boolean retval = false;
		if(!"".equals(record.getThtint()) || !"".equals(record.getThnat()) || !"".equals(record.getThadt1()) || !"".equals(record.getThpst()) || 
				   !"".equals(record.getThpnt()) || !"".equals(record.getThlkt()) ) {
			retval = true;
		}
		return retval;
	}
	/**
	 * 
	 * @param record
	 * @return
	 */
	private boolean isValidSecurityCarrier(JsonSkatNctsExportSpecificTopicRecord record){
		boolean retval = false;
		if( record.getThtint()!=null && !"".equals(record.getThtint())){
			retval = true;
		}else{
			if(!"".equals(record.getThnat()) && !"".equals(record.getThadt1()) && !"".equals(record.getThpst()) && 
			   !"".equals(record.getThpnt()) && !"".equals(record.getThlkt())){
				retval = true;
			}
		}
		return retval;
	}

	/**
	 * 
	 * @param rawValue
	 * @return
	 */
	private boolean isValidDate(String rawValue){
		boolean retval = false;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		formatter.setLenient(false); //in order to put logical control for month
		try{
			Date tmp = formatter.parse(rawValue);
			retval = true;
		}catch(Exception e){
			//nothing
		}
		return retval;
	}
	
	/**
	 * Checks if the Guarantee number (thgft1) is ok together with its Guarantee code (thgadk)
	 * 
	 * @param record
	 * @return
	 */
	private String isValidGuarantee(JsonSkatNctsExportSpecificTopicRecord record){
		String method = "isValidGuarantee";
	 	logger.info("Inside " + method);
	 	String retval = null;
	 	
	 	logger.info("VALIDATION of Guarantee...");
		//---------------------------
		//get BASE URL = RPG-PROGRAM
		//---------------------------
		String BASE_URL = SkatNctsExportUrlDataStore.NCTS_EXPORT_BASE_VALIDATE_SPECIFIC_TOPIC_GUARRANTEE_URL;
		//url params
		String urlRequestParamsKeys = this.getRequestUrlKeyParametersForGuaranteeValidation(record.getApplicationUser(),record.getThgft1(),record.getThgadk() );
		//for debug purposes in GUI
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.info("URL: " + BASE_URL);
		logger.info("URL PARAMS: " + urlRequestParamsKeys);
		//--------------------------------------
		//EXECUTE the FETCH (RPG program) here
		//--------------------------------------
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		//Debug --> 
		logger.info(method + " --> jsonPayload:" + jsonPayload);
		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	
		if(jsonPayload!=null){
			JsonSkatNctsExportSpecificTopicGuaranteeValidatorContainer container = this.nctsExportSpecificTopicService.getNctsExportSpecificTopicGuaranteeValidatorContainer(jsonPayload);
			if(container!=null){
				if(container.getErrMsg()!=null && !"".equals(container.getErrMsg())){
					logger.info("######################################");
					logger.info("[ERROR] on Guarantee: " + container.getErrMsg());
					logger.info("######################################");
					retval = container.getErrMsg();
				}
			}
		}
		return retval;
	}
	/**
	   * Validate guarantee dependency fields
	   * 
	   * @param applicationUser
	   * @param guaranteeNumber
	   * @param guaranteeCode
	   * @return
	   */
	  private String getRequestUrlKeyParametersForGuaranteeValidation(String applicationUser,String guaranteeNumber,String guaranteeCode){
		  StringBuffer sb = new StringBuffer();
		  final String USER_TEST = "OSCAR"; //Special for test
		  if("Oscar".equalsIgnoreCase(applicationUser)){
			  sb.append("user=" + USER_TEST);
		  }else{
			  sb.append("user=" + applicationUser);
		  }
		  sb.append( SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "thgft1=" + guaranteeNumber );
		  sb.append( SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "thgadk=" + guaranteeCode );
		  return sb.toString();
	  }
	  
	  /**
	   * 
	   * @param record
	   * @return
	   */
	  private boolean isValidSenderEori(String value){
		  boolean retval = true;
		  //Removed for DACHSER test
		  /*
			boolean retval = false;
			if( value!=null && !"".equals(value) ){ 
				if(value.startsWith("DK")){
					retval = true;
				}
			}
		*/	
			return retval;
		}
	  
}
