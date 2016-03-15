/**
 * 
 */
package no.systema.fraktkalkulator.model.jsonjackson;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;
/**
 * @author oscardelatorre
 * @date Mar 04, 2015
 * 
 */
public class JsonFraktKalkulatorResultRecord extends JsonAbstractGrandFatherRecord {
	
	private String wsgkd = null;
	public void setWsgkd(String value){ this.wsgkd = value;}
	public String getWsgkd(){ return this.wsgkd; }
	
	private String wsgtxt = null;
	public void setWsgtxt(String value){ this.wsgtxt = value;}
	public String getWsgtxt(){ return this.wsgtxt; }
	
	private String wsval = null;
	public void setWsval(String value){ this.wsval = value;}
	public String getWsval(){ return this.wsval; }
	
	private String wsbel = null;
	public void setWsbel(String value){ this.wsbel = value;}
	public String getWsbel(){ return this.wsbel; }
	
	private String wsbeln = null;
	public void setWsbeln(String value){ this.wsbeln = value;}
	public String getWsbeln(){ return this.wsbeln; }
	
	private String wsmva = null;
	public void setWsmva(String value){ this.wsmva = value;}
	public String getWsmva(){ return this.wsmva; }
	
	private String wstotn = null;
	public void setWstotn(String value){ this.wstotn = value;}
	public String getWstotn(){ return this.wstotn; }
	
	
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
