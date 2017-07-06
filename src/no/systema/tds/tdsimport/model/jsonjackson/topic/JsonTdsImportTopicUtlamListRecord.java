/**
 * 
 */
package no.systema.tds.tdsimport.model.jsonjackson.topic;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author oscardelatorre
 * @date Jul 06, 2017
 *
 */
public class JsonTdsImportTopicUtlamListRecord extends JsonAbstractGrandFatherRecord {
	
	private String doc_1004 = null; //tullid
	public void setDoc_1004(String value) {  this.doc_1004 = value; }
	public String getDoc_1004() { return this.doc_1004;}
	
	private String dtm_2380B = null; //utlämningsdatum
	public void setDtm_2380B(String value) {  this.dtm_2380B = value; }
	public String getDtm_2380B() { return this.dtm_2380B;}
	
	private String loc_1131 = null; //typ
	public void setLoc_1131(String value) {  this.loc_1131 = value; }
	public String getLoc_1131() { return this.loc_1131;}
	
	private String nad_3036D = null; //avsändare
	public void setNad_3036D(String value) {  this.nad_3036D = value; }
	public String getNad_3036D() { return this.nad_3036D;}
	
	private String nad_3036E = null; //mottagare
	public void setNad_3036E(String value) {  this.nad_3036E = value; }
	public String getNad_3036E() { return this.nad_3036E;}
	
	
	
	/**
	 * 
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
