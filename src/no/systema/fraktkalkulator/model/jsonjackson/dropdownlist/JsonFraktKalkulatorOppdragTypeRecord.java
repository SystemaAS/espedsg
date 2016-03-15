/**
 * 
 */
package no.systema.fraktkalkulator.model.jsonjackson.dropdownlist;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;
/**
 * @author oscardelatorre
 * @date Mar 9, 2015
 * 
 */
public class JsonFraktKalkulatorOppdragTypeRecord extends JsonAbstractGrandFatherRecord {
	
	private String wsot1 = null;//kod
	public void setWsot1(String value){ this.wsot1 = value;}
	public String getWsot1(){ return this.wsot1; }
	
	private String wsot2 = null;//text
	public void setWsot2(String value){ this.wsot2 = value;}
	public String getWsot2(){ return this.wsot2; }
	
	/**
	 * User for java reflection in other classes
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
