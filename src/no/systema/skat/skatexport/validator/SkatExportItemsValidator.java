	package no.systema.skat.skatexport.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.skat.skatexport.model.jsonjackson.topic.items.JsonSkatExportSpecificTopicItemRecord;

/**
 * 
 * @author oscardelatorre
 * Mar 19, 2014
 * 
 */
public class SkatExportItemsValidator implements Validator {
	
	final String ANG_ART_20_ALU = "20";
	final String ANG_ART_21_FOU = "21";
	final String ANG_ART_22_SUP = "22";
	final String ANG_ART_23_SUPH = "23";
	final String ANG_ART_24_FUAA = "24";
	final String ANG_ART_25_FUAF = "25";
	final String ANG_ART_26_PRO = "26";
	final String ANG_ART_27_PROM = "27";
	final String ANG_ART_28_EFU = "28";
	final String ANG_ART_30_EXS = "30";
	final String ANG_ART_31_YM_FUAGo = "31";
	final String ANG_ART_32_YM_FUAAo = "32";
	final String ANG_ART_50_IE507 = "50";
	
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonSkatExportSpecificTopicItemRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * 
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		
		JsonSkatExportSpecificTopicItemRecord record = (JsonSkatExportSpecificTopicItemRecord)obj;
			
		if( this.ANG_ART_20_ALU.equals(record.getHeader_dkeh_aart()) ){
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_311", "systema.skat.export.header.error.null.item.dkev_311");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_313", "systema.skat.export.header.error.null.item.dkev_313");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_314", "systema.skat.export.header.error.null.item.dkev_314");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_315", "systema.skat.export.header.error.null.item.dkev_315");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_331", "systema.skat.export.header.error.null.item.dkev_331");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_35", "systema.skat.export.header.error.null.item.dkev_35");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_37", "systema.skat.export.header.error.null.item.dkev_37");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_38", "systema.skat.export.header.error.null.item.dkev_38");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_402a", "systema.skat.export.header.error.null.item.dkev_402");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_403a", "systema.skat.export.header.error.null.item.dkev_403");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_46", "systema.skat.export.header.error.null.item.dkev_46");
			
			
		}else if( this.ANG_ART_21_FOU.equals(record.getHeader_dkeh_aart()) ){
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_311", "systema.skat.export.header.error.null.item.dkev_311");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_313", "systema.skat.export.header.error.null.item.dkev_313");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_314", "systema.skat.export.header.error.null.item.dkev_314");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_315", "systema.skat.export.header.error.null.item.dkev_315");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_331", "systema.skat.export.header.error.null.item.dkev_331");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_35", "systema.skat.export.header.error.null.item.dkev_35");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_37", "systema.skat.export.header.error.null.item.dkev_37");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_38", "systema.skat.export.header.error.null.item.dkev_38");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_402a", "systema.skat.export.header.error.null.item.dkev_402");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_403a", "systema.skat.export.header.error.null.item.dkev_403");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_46", "systema.skat.export.header.error.null.item.dkev_46");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_42", "systema.skat.export.header.error.null.item.dkev_42");
			
			
		}else if( this.ANG_ART_22_SUP.equals(record.getHeader_dkeh_aart()) ){
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_35", "systema.skat.export.header.error.null.item.dkev_35");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_402a", "systema.skat.export.header.error.null.item.dkev_402");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_403a", "systema.skat.export.header.error.null.item.dkev_403");
			
		}else if( this.ANG_ART_23_SUPH.equals(record.getHeader_dkeh_aart()) ){
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_35", "systema.skat.export.header.error.null.item.dkev_35");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_402a", "systema.skat.export.header.error.null.item.dkev_402");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_403a", "systema.skat.export.header.error.null.item.dkev_403");
			
		}else if(this.ANG_ART_24_FUAA.equals(record.getHeader_dkeh_aart())){
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_311", "systema.skat.export.header.error.null.item.dkev_311");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_313", "systema.skat.export.header.error.null.item.dkev_313");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_314", "systema.skat.export.header.error.null.item.dkev_314");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_315", "systema.skat.export.header.error.null.item.dkev_315");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_331", "systema.skat.export.header.error.null.item.dkev_331");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_35", "systema.skat.export.header.error.null.item.dkev_35");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_37", "systema.skat.export.header.error.null.item.dkev_37");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_38", "systema.skat.export.header.error.null.item.dkev_38");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_402a", "systema.skat.export.header.error.null.item.dkev_402");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_403a", "systema.skat.export.header.error.null.item.dkev_403");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_46", "systema.skat.export.header.error.null.item.dkev_46");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_42", "systema.skat.export.header.error.null.item.dkev_42");
			
		}else if(this.ANG_ART_25_FUAF.equals(record.getHeader_dkeh_aart())){
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_311", "systema.skat.export.header.error.null.item.dkev_311");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_313", "systema.skat.export.header.error.null.item.dkev_313");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_314", "systema.skat.export.header.error.null.item.dkev_314");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_315", "systema.skat.export.header.error.null.item.dkev_315");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_331", "systema.skat.export.header.error.null.item.dkev_331");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_35", "systema.skat.export.header.error.null.item.dkev_35");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_37", "systema.skat.export.header.error.null.item.dkev_37");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_38", "systema.skat.export.header.error.null.item.dkev_38");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_402a", "systema.skat.export.header.error.null.item.dkev_402");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_403a", "systema.skat.export.header.error.null.item.dkev_403");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_46", "systema.skat.export.header.error.null.item.dkev_46");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_42", "systema.skat.export.header.error.null.item.dkev_42");
			
		}else if(this.ANG_ART_28_EFU.equals(record.getHeader_dkeh_aart())){
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_311", "systema.skat.export.header.error.null.item.dkev_311");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_313", "systema.skat.export.header.error.null.item.dkev_313");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_314", "systema.skat.export.header.error.null.item.dkev_314");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_315", "systema.skat.export.header.error.null.item.dkev_315");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_331", "systema.skat.export.header.error.null.item.dkev_331");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_35", "systema.skat.export.header.error.null.item.dkev_35");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_37", "systema.skat.export.header.error.null.item.dkev_37");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_38", "systema.skat.export.header.error.null.item.dkev_38");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_402a", "systema.skat.export.header.error.null.item.dkev_402");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_403a", "systema.skat.export.header.error.null.item.dkev_403");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_46", "systema.skat.export.header.error.null.item.dkev_46");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_42", "systema.skat.export.header.error.null.item.dkev_42");
			
		}else if(this.ANG_ART_26_PRO.equals(record.getHeader_dkeh_aart())){
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_311", "systema.skat.export.header.error.null.item.dkev_311");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_313", "systema.skat.export.header.error.null.item.dkev_313");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_314", "systema.skat.export.header.error.null.item.dkev_314");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_315", "systema.skat.export.header.error.null.item.dkev_315");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_331", "systema.skat.export.header.error.null.item.dkev_331");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_35", "systema.skat.export.header.error.null.item.dkev_35");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_37", "systema.skat.export.header.error.null.item.dkev_37");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_38", "systema.skat.export.header.error.null.item.dkev_38");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_402a", "systema.skat.export.header.error.null.item.dkev_402");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_403a", "systema.skat.export.header.error.null.item.dkev_403");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_46", "systema.skat.export.header.error.null.item.dkev_46");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_42", "systema.skat.export.header.error.null.item.dkev_42");
			
		}else if(this.ANG_ART_27_PROM.equals(record.getHeader_dkeh_aart())){
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_311", "systema.skat.export.header.error.null.item.dkev_311");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_313", "systema.skat.export.header.error.null.item.dkev_313");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_314", "systema.skat.export.header.error.null.item.dkev_314");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_315", "systema.skat.export.header.error.null.item.dkev_315");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_331", "systema.skat.export.header.error.null.item.dkev_331");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_35", "systema.skat.export.header.error.null.item.dkev_35");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_37", "systema.skat.export.header.error.null.item.dkev_37");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_38", "systema.skat.export.header.error.null.item.dkev_38");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_402a", "systema.skat.export.header.error.null.item.dkev_402");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_403a", "systema.skat.export.header.error.null.item.dkev_403");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_46", "systema.skat.export.header.error.null.item.dkev_46");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_42", "systema.skat.export.header.error.null.item.dkev_42");
			
		}else if(this.ANG_ART_30_EXS.equals(record.getHeader_dkeh_aart())){
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_311", "systema.skat.export.header.error.null.item.dkev_311");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_313", "systema.skat.export.header.error.null.item.dkev_313");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_314", "systema.skat.export.header.error.null.item.dkev_314");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_315", "systema.skat.export.header.error.null.item.dkev_315");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_331", "systema.skat.export.header.error.null.item.dkev_331");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_35", "systema.skat.export.header.error.null.item.dkev_35");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_402a", "systema.skat.export.header.error.null.item.dkev_402");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_403a", "systema.skat.export.header.error.null.item.dkev_403");
			
		}else if(this.ANG_ART_50_IE507.equals(record.getHeader_dkeh_aart())){
			
			
		}else if(this.ANG_ART_31_YM_FUAGo.equals(record.getHeader_dkeh_aart())){
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_311", "systema.skat.export.header.error.null.item.dkev_311");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_313", "systema.skat.export.header.error.null.item.dkev_313");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_314", "systema.skat.export.header.error.null.item.dkev_314");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_315", "systema.skat.export.header.error.null.item.dkev_315");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_331", "systema.skat.export.header.error.null.item.dkev_331");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_34a", "systema.skat.export.header.error.null.item.dkev_34a");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_35", "systema.skat.export.header.error.null.item.dkev_35");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_37", "systema.skat.export.header.error.null.item.dkev_37");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_38", "systema.skat.export.header.error.null.item.dkev_38");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_402a", "systema.skat.export.header.error.null.item.dkev_402");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_403a", "systema.skat.export.header.error.null.item.dkev_403");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_46", "systema.skat.export.header.error.null.item.dkev_46");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_42", "systema.skat.export.header.error.null.item.dkev_42");
			
		}else if(this.ANG_ART_32_YM_FUAAo.equals(record.getHeader_dkeh_aart())){
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_311", "systema.skat.export.header.error.null.item.dkev_311");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_313", "systema.skat.export.header.error.null.item.dkev_313");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_314", "systema.skat.export.header.error.null.item.dkev_314");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_315", "systema.skat.export.header.error.null.item.dkev_315");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_331", "systema.skat.export.header.error.null.item.dkev_331");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_34a", "systema.skat.export.header.error.null.item.dkev_34a");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_35", "systema.skat.export.header.error.null.item.dkev_35");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_37", "systema.skat.export.header.error.null.item.dkev_37");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_38", "systema.skat.export.header.error.null.item.dkev_38");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_402a", "systema.skat.export.header.error.null.item.dkev_402");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_403a", "systema.skat.export.header.error.null.item.dkev_403");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_46", "systema.skat.export.header.error.null.item.dkev_46");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkev_42", "systema.skat.export.header.error.null.item.dkev_42");
			
		}
		
		//Logical (RULES) controls if we passed the NOT NULL errors
		if(!errors.hasFieldErrors()){
			if(record!=null){
				//----------------------
				//Proviant restrictions
				//----------------------
				if(this.ANG_ART_26_PRO.equals(record.getHeader_dkeh_aart()) || this.ANG_ART_27_PROM.equals(record.getHeader_dkeh_aart())){
					boolean match = false;
					String [] validKodes = {"99302400","99302700","99309900"};
					for(String code: validKodes){
						if(code.equals(record.getDkev_331())){
							match = true;
							break;
						}
					}
					if(!match){
						errors.rejectValue("dkev_331", "systema.skat.export.header.error.rule.item.vareKodNotValid"); 
					}
				}
					
				//----------------------
				//49. Ident. of oplag
				//----------------------
				if(!"".equals(record.getDkev_37())){
					if(record.getDkev_37().startsWith("3171")){
						if("".equals(record.getDkev_49())){
							errors.rejectValue("dkev_49", "systema.skat.export.header.error.rule.item.idOfOplagNotValid"); 
						}else{
							//check if RegEx on 49. Ident. af oplag is correct
							if(!this.isValidRegExDkev_49(record.getDkev_49())){
								errors.rejectValue("dkev_49", "systema.skat.export.header.error.rule.item.idOfOplagNotValidRegEx"); 
							}
								
						}
					}
				}
				
				//41 either all or none
				if(record.getDkev_412()!=null && !"".equals(record.getDkev_412())){
					if(record.getDkev_411()!=null && !"".equals(record.getDkev_411())){
						//OK
					}else{
						errors.rejectValue("dkev_412", "systema.skat.export.header.error.null.item.dkev_41_2");
					}
				}
				if(record.getDkev_411()!=null && !"".equals(record.getDkev_411())){
					if(record.getDkev_412()!=null && !"".equals(record.getDkev_412())){
						//OK
					}else{
						errors.rejectValue("dkev_412", "systema.skat.export.header.error.null.item.dkev_41_2");
					}
				}
				
				//---------------------
				//Gross and Net weight
				//---------------------
				if(record.getDkev_35()!=null && !"".equals(record.getDkev_35())){
					if(record.getDkev_38()!=null && !"".equals(record.getDkev_38())){
						try{
							String grossFormatTmp = record.getDkev_35().replace(".", "");
							double grossWeight = Double.parseDouble(grossFormatTmp.replace(",", "."));
							String netFormatTmp = record.getDkev_38().replace(".", "");
							double netWeight = Double.parseDouble(netFormatTmp.replace(",", "."));
							
							//Net can not be > than Gross
							if (netWeight>grossWeight){
								errors.rejectValue("dkev_38", "systema.skat.export.header.error.rule.item.netWeightTooBig");
							}
						}catch(Exception e){
							//just take a fantom hit here 
						}
					}
				}
				//-----------------------------------------
				//No decimals are allowed with weights > 1
				//-----------------------------------------
				//Gross
				if(record.getDkev_35()!=null && !"".equals(record.getDkev_35())){
					String grossFormatTmp = record.getDkev_35().replace(".", "");
					double grossWeight = Double.parseDouble(grossFormatTmp.replace(",", "."));
					if(grossWeight>1){
						if(grossWeight%1==0){
							//nothing since there are no decimals (mathematically)
						}else{
							errors.rejectValue("dkev_35", "systema.skat.export.header.error.rule.item.gross.invalidDecimals");
						}
					}
				}
				//Net
				if(record.getDkev_38()!=null && !"".equals(record.getDkev_38())){
					String netFormatTmp = record.getDkev_38().replace(".", "");
					double netWeight = Double.parseDouble(netFormatTmp.replace(",", "."));
					if(netWeight>1){
						if(netWeight%1==0){
							//nothing since there are no decimals (mathematically)
						}else{
							errors.rejectValue("dkev_38", "systema.skat.export.header.error.rule.item.net.invalidDecimals");
						}
					}
				}
			}
		}
		
		
	}
	
	/**
	 * 
	 * @param value
	 * @return
	 */
	private boolean isValidRegExDkev_49(String value) {
        boolean retVal = false;
        try {
            if (value != null) {
                //String nameRegex = "^(\\d{4})$";
                //String nameRegex = "^\\w{2}\\d{9}\\w{3}$";
            	    String nameRegex = "[a-zA-Z]{1}[0-9]{6}DK";
                Pattern namePattern = Pattern.compile(nameRegex);
                Matcher nameMatcher = namePattern.matcher(value);
                boolean matchFound = nameMatcher.find();
                if (matchFound) {
                    retVal = true;
                } 
            }
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        return retVal;
    }
	
}
