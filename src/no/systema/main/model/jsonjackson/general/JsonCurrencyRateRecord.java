/**
 * 
 */
package no.systema.main.model.jsonjackson.general;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

/**
 * @author oscardelatorre
 * @date Mar 01, 2013
 */
public class JsonCurrencyRateRecord {
	//TDS
	private String svvs_omr = null;
	public void setSvvs_omr(String value){ this.svvs_omr = value;}
	public String getSvvs_omr(){ return this.svvs_omr; }
	
	private String svvk_krs = null;
	public void setSvvk_krs(String value){ this.svvk_krs = value;}
	public String getSvvk_krs(){ return this.svvk_krs; }

	//SKAT
	private String dkvs_omr = null;
	public void setDkvs_omr(String value){ this.dkvs_omr = value;}
	public String getDkvs_omr(){ return this.dkvs_omr; }
	
	private String dkvk_krs = null;
	public void setDkvk_krs(String value){ this.dkvk_krs = value;}
	public String getDkvk_krs(){ return this.dkvk_krs; }
	
	//TVINN_SAD
	private String kvaomr = null;
	public void setKvaomr(String value){ this.kvaomr = value;}
	public String getKvaomr(){ return this.kvaomr; }
	
	private String kvakrs = null;
	public void setKvakrs(String value){ this.kvakrs = value;}
	public String getKvakrs(){ return this.kvakrs; }
	
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
