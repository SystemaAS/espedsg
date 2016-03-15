/**
 * 
 */
package no.systema.asyjservices.model.jsonjackson;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;
import java.util.*;
import java.lang.reflect.Field;
/**
 * @author oscardelatorre
 * @date Nov 4, 2015
 * 
 *
 */
public class JsonSysJservicesMainListRecord extends JsonAbstractGrandFatherRecord{
	
	private String knavn = null;
	public void setKnavn(String value) {  this.knavn = value; }
	public String getKnavn() {return this.knavn;}
	
	private String adr1 = null;
	public void setAdr1(String value) {  this.adr1 = value; }
	public String getAdr1() {return this.adr1;}
	
	
	
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
