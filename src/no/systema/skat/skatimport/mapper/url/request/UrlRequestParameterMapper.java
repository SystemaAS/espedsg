/**
 * 
 */
package no.systema.skat.skatimport.mapper.url.request;

import java.lang.reflect.Field;
import java.net.URLEncoder;

import org.apache.log4j.Logger;

import no.systema.skat.skatimport.mapper.jsonjackson.SkatImportTopicCopiedFromTransportUppdragMapper;
import no.systema.skat.skatimport.model.jsonjackson.topic.JsonSkatImportSpecificTopicRecord;
import no.systema.skat.skatimport.model.jsonjackson.topic.JsonSkatImportTopicInvoiceRecord;

import no.systema.skat.skatimport.model.jsonjackson.topic.items.JsonSkatImportSpecificTopicItemRecord;
import no.systema.skat.util.SkatConstants;


/**
 * @author oscardelatorre
 * @param Jan 27, 2014
 */
public class UrlRequestParameterMapper {
	private final String PREFIX = "no.systema.skat.skatimport.mapper.url.request.UrlRequestParameterMapper";
	private static final Logger logger = Logger.getLogger(UrlRequestParameterMapper.class.getName());
	
	/**
	 * Builds the final url parameter list (to send with a GET or POST form method)
	 * @param object
	 * @return
	 * 
	 */
	public String getUrlParameterValidString(JsonSkatImportSpecificTopicRecord object){
		StringBuffer sb = new StringBuffer();
		
		try{
			for(Field field: object.getFields()){
				try{
					field.setAccessible(true);//we must do this in order to access private fields
					String value = (String)field.get(object); 
					if(value==null){
						sb.append("");
					}else{
						//CRUCIAL! to encode the value in order to handle all special characters (%,&,",',()...) before JSON-call
						//& will be converted into "%26", %="%25", etc. 
						//Refer to URLEncode special characters for further info)
						value = URLEncoder.encode(value, "UTF-8");
						
						sb.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + field.getName() + "=");
						sb.append(value.trim());
					}
				}catch(Exception e){
					//Try Integer
					if(field.get(object) instanceof Integer){
						Integer value = (Integer)field.get(object); 
						sb.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + field.getName() + "=");
						sb.append(value);
					
					}else if(field.get(object) instanceof Double){
						Double value = (Double)field.get(object); 
						sb.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + field.getName() + "=");
						sb.append(value);
					}else{
						logger.info(this.PREFIX + " [INFO]data type not yet supported...");
					}
					//add more instances if you need...					
										
					
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	/**
	 * Builds the final url parameter list (to send with a GET or POST form method)
	 * @param object
	 * @return
	 */
	
	public String getUrlParameterValidString(JsonSkatImportSpecificTopicItemRecord object){
		StringBuffer sb = new StringBuffer();
		try{
			for(Field field: object.getFields()){
				try{
					field.setAccessible(true);//we must do this in order to access private fields
					String value = (String)field.get(object); 
					if(value==null){
						sb.append("");
					}else{
						//CRUCIAL! to encode the value in order to handle all special characters (%,&,",',()...) before JSON-call
						//& will be converted inte %26, %=%25, etc (refer to URLEncode special characters for further info)
						value = URLEncoder.encode(value, "UTF-8");
						
						sb.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + field.getName() + "=");
						sb.append(value.trim());
					}
				}catch(Exception e){
					//Try Integer
					if(field.get(object) instanceof Integer){
						Integer value = (Integer)field.get(object); 
						sb.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + field.getName() + "=");
						sb.append(value);
					
					}else if(field.get(object) instanceof Double){
						Double value = (Double)field.get(object); 
						sb.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + field.getName() + "=");
						sb.append(value);
					}else{
						logger.info(this.PREFIX + " [INFO]data type not yet supported...");
					}
					//add more instances if you need...	
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	/**
	 * 
	 * @param object
	 * @return
	 */
	public String getUrlParameterValidString(JsonSkatImportTopicInvoiceRecord object){
		StringBuffer sb = new StringBuffer();
		try{
			for(Field field: object.getFields()){
				try{
					field.setAccessible(true);//we must do this in order to access private fields
					String value = (String)field.get(object); 
					if(value==null){
						sb.append("");
					}else{
						//CRUCIAL! to encode the value in order to handle all special characters (%,&,",',()...) before JSON-call
						//& will be converted inte %26, %=%25, etc (refer to URLEncode special characters for further info)
						value = URLEncoder.encode(value, "UTF-8");
						
						sb.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + field.getName() + "=");
						sb.append(value.trim());
					}
				}catch(Exception e){
					//Try Integer
					if(field.get(object) instanceof Integer){
						Integer value = (Integer)field.get(object); 
						sb.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + field.getName() + "=");
						sb.append(value);
					
					}else if(field.get(object) instanceof Double){
						Double value = (Double)field.get(object); 
						sb.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + field.getName() + "=");
						sb.append(value);
					}else{
						logger.info(this.PREFIX + " [INFO]data type not yet supported...");
					}
					//add more instances if you need...	
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	
}
