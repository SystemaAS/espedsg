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
public class JsonFraktKalkulatorIncotermsRecord extends JsonAbstractGrandFatherRecord {
	
	private String frankod = null;
	public void setFrankod(String value){ this.frankod = value;}
	public String getFrankod(){ return this.frankod; }
	
	private String frantxt = null;
	public void setFrantxt(String value){ this.frantxt = value;}
	public String getFrantxt(){ return this.frantxt; }
	
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
