/**
 * 
 */
package no.systema.tror.util;

import java.util.*;


import org.apache.log4j.Logger;

/**
 * The class evaluates return codes from RPG operations.
 * 
 * @author oscardelatorre
 * @date Jul 04, 2017
 * 
 */
public class RpgReturnResponseHandler {
	private static final Logger logger = Logger.getLogger(RpgReturnResponseHandler.class.getName());
	
	private String errorMessage = null;
	public void setErrorMessage(String value){ this.errorMessage=value;  }
	public String getErrorMessage(){ return this.errorMessage;  }
	
	private String user = null;
	public void setUser(String value){ this.user=value;  }
	public String getUser(){ return this.user;  }
	
	private String hereff = null;
	public void setHereff(String value){ this.hereff=value;  }
	public String getHereff(){ return this.hereff;  }
	
	private String heunik = null;
	public void setHeunik(String value){ this.heunik=value;  }
	public String getHeunik(){ return this.heunik;  }
	
	
	/**
	 * Sets the error message code after an RPG-call been made by an HTML-POST request on a trip update
	 * 
	 * @param rpgRawReturnPayload
	 * @return
	 */
	public void evaluateRpgResponseOnUpdate(String rpgRawResponsePayload){
		
		if(rpgRawResponsePayload!=null){
			String tmp = rpgRawResponsePayload.replaceAll("\"", "");
			String tmp2 = tmp.replace("{", "");
			String cleanRawPayload = tmp2.replace("}", "");
			
			String[] record = cleanRawPayload.split(",");
			List <String>list = Arrays.asList(record);
			for(String field: list){
				//logger.info(field);
				String[] keyValuePair = field.split(":");
				if(keyValuePair[0]!=null){
					if(keyValuePair[0].trim().equalsIgnoreCase("errMsg")){
						if(keyValuePair.length>1){
							String errorCode = keyValuePair[1];
							if(errorCode!=null && !"".equals(errorCode.trim())){
								this.errorMessage = errorCode ;
								logger.info(this.errorMessage);
							}
						}
					}else if(keyValuePair[0].trim().equalsIgnoreCase("user")){
						this.user = keyValuePair[0] + ":" + keyValuePair[1] + ",";
					}	
					/*}else if(keyValuePair[0].trim().equalsIgnoreCase("hereff")){
						//this.tupro = keyValuePair[0] + ":" + keyValuePair[1]+ ",";
						this.hereff = keyValuePair[1].trim();
					}else if(keyValuePair[0].trim().equalsIgnoreCase("heunik")){
						//this.tuavd = keyValuePair[0] + ":" + keyValuePair[1]+ ",";
						this.heunik = keyValuePair[1].trim();
					}*/
						
				}
			}
		}
		
		
	}
	
	/**
	 * 
	 * @param rpgRawResponsePayload
	 */
	
	public void evaluateRpgResponseOnEditSpecificOrder(String rpgRawResponsePayload){
		if(rpgRawResponsePayload!=null){
			String tmp = rpgRawResponsePayload.replaceAll("\"", "");
			String tmp2 = tmp.replace("{", "");
			String cleanRawPayload = tmp2.replace("}", "");
			
			String[] record = cleanRawPayload.split(",");
			List <String>list = Arrays.asList(record);
			for(String field: list){
				//logger.info(field);
				String[] keyValuePair = field.split(":");
				if(keyValuePair[0]!=null){
					if(keyValuePair[0].trim().equalsIgnoreCase("errMsg")){
						if(keyValuePair.length>1){
							String errorCode = keyValuePair[1];
							if(errorCode!=null && !"".equals(errorCode.trim())){
								this.errorMessage = errorCode ;
								logger.info(this.errorMessage);
							}
						}
					}else if(keyValuePair[0].trim().equalsIgnoreCase("user")){
						this.user = keyValuePair[0] + ":" + keyValuePair[1] + ",";
					}else if(keyValuePair[0].trim().equalsIgnoreCase("hereff")){
						//this.tupro = keyValuePair[0] + ":" + keyValuePair[1]+ ",";
						this.hereff = keyValuePair[1].trim();
					}else if(keyValuePair[0].trim().equalsIgnoreCase("heunik")){
						//this.tuavd = keyValuePair[0] + ":" + keyValuePair[1]+ ",";
						this.heunik = keyValuePair[1].trim();
					}
				}
			}
		}
		
		
	}
	
	/**
	 * 
	 * @param rpgRawResponsePayload
	 */
	
	public void evaluateRpgResponseOnValidateSpecificOrderLine(String rpgRawResponsePayload){
		if(rpgRawResponsePayload!=null){
			String tmp = rpgRawResponsePayload.replaceAll("\"", "");
			String tmp2 = tmp.replace("{", "");
			String cleanRawPayload = tmp2.replace("}", "");
			
			String[] record = cleanRawPayload.split(",");
			List <String>list = Arrays.asList(record);
			for(String field: list){
				//logger.info(field);
				String[] keyValuePair = field.split(":");
				if(keyValuePair[0]!=null){
					if(keyValuePair[0].trim().equalsIgnoreCase("errMsg")){
						if(keyValuePair.length>1){
							String errorCode = keyValuePair[1];
							if(errorCode!=null && !"".equals(errorCode.trim())){
								this.errorMessage = errorCode ;
								logger.info(this.errorMessage);
							}
						}
					}else if(keyValuePair[0].trim().equalsIgnoreCase("user")){
						this.user = keyValuePair[0] + ":" + keyValuePair[1] + ",";
					}else if(keyValuePair[0].trim().equalsIgnoreCase("hereff")){
						//this.tupro = keyValuePair[0] + ":" + keyValuePair[1]+ ",";
						this.hereff = keyValuePair[1].trim();
					}else if(keyValuePair[0].trim().equalsIgnoreCase("heunik")){
						//this.tuavd = keyValuePair[0] + ":" + keyValuePair[1]+ ",";
						this.heunik = keyValuePair[1].trim();
					}
				}
			}
		}
		
		
	}
	
	
	
	
}
