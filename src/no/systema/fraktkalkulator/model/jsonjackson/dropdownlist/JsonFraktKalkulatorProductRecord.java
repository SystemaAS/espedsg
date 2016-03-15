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
public class JsonFraktKalkulatorProductRecord extends JsonAbstractGrandFatherRecord {
	
	private String prodkod = null;
	public void setProdkod(String value){ this.prodkod = value;}
	public String getProdkod(){ return this.prodkod; }
	
	private String prodtxt = null;
	public void setProdtxt(String value){ this.prodtxt = value;}
	public String getProdtxt(){ return this.prodtxt; }
	
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
