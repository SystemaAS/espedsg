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
public class JsonFraktKalkulatorTjenesteTypeRecord extends JsonAbstractGrandFatherRecord {
	
	private String wsavd1 = null; //Koder
	public void setWsavd1(String value){ this.wsavd1 = value;}
	public String getWsavd1(){ return this.wsavd1; }
	
	private String wsavd2 = null; //Text
	public void setWsavd2(String value){ this.wsavd2 = value;}
	public String getWsavd2(){ return this.wsavd2; }
	
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
