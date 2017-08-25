/**
 * 
 */
package no.systema.tror.model.jsonjackson.order.landimport.childwindow;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;
/**
 * @author oscardelatorre
 * @date Aug 24, 2017
 * 
 */
public class JsonTrorSellerDeliveryAddressRecord extends JsonAbstractGrandFatherRecord  {
	
	
	private String kukun1 = null;
	public void setKukun1(String value){ this.kukun1 = value;}
	public String getKukun1(){ return this.kukun1; }
	
	private String kukun2 = null;
	public void setKukun2(String value){ this.kukun2 = value;}
	public String getKukun2(){ return this.kukun2; }
	
	private String kuintn = null;
	public void setKuintn(String value){ this.kuintn = value;}
	public String getKuintn(){ return this.kuintn; }
		
	private String kualfa = null;
	public void setKualfa(String value){ this.kualfa = value;}
	public String getKualfa(){ return this.kualfa; }
	
	private String kuvadr = null;
	public void setKuvadr(String value){ this.kuvadr = value;}
	public String getKuvadr(){ return this.kuvadr; }
	
	
	
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
