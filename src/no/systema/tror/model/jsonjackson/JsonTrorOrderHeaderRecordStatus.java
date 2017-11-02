/**
 * 
 */
package no.systema.tror.model.jsonjackson;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;

/**
 * @author oscardelatorre
 * @date Nov 2, 2017
 *
 */
public class JsonTrorOrderHeaderRecordStatus extends JsonAbstractGrandFatherRecord  {
	
	private String status = null;
	public void setStatus(String value) {  this.status = value; }
	public String getStatus() { return this.status;}
	                  
	
	/**
	 * Used for java reflection in other classes
	 * @return
	 * @throws Exception
	 */
	
	public List<Field> getFields() throws Exception{
		Class cl = Class.forName(this.getClass().getCanonicalName());
		Field[] fields = cl.getDeclaredFields();
		List<Field> list = Arrays.asList(fields);
		
		return list;
	}
	
}
