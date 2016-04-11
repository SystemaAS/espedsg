/**
 * 
 */
package no.systema.skat.skatexport.model.jsonjackson.topic;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;
import no.systema.tvinn.sad.util.TvinnSadDateFormatter;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author oscardelatorre
 * @date Apr 6, 2016
 *
 */
public class JsonSkatExportTopicInvoiceExternalRecord extends JsonAbstractGrandFatherRecord {
	private TvinnSadDateFormatter dateFormatter = new TvinnSadDateFormatter();
	
	private String debugPrintlnAjax = null;
	public void setDebugPrintlnAjax(String value) {  this.debugPrintlnAjax = value; }
	public String getDebugPrintlnAjax() {return this.debugPrintlnAjax;}
	
	private String dkef_syav = null;
	public void setDkef_syav(String value) {  this.dkef_syav = value; }
	public String getDkef_syav() { return this.dkef_syav;}
	
	private String dkef_syop = null;
	public void setDkef_syop(String value) {  this.dkef_syop = value; }
	public String getDkef_syop() { return this.dkef_syop;}
	
	private String dkef_faty = null;
	public void setDkef_faty(String value) {  this.dkef_faty = value; }
	public String getDkef_faty() { return this.dkef_faty;}
	
	private String dkef_fatx = null;
	public void setDkef_fatx(String value) {  this.dkef_fatx = value; }
	public String getDkef_fatx() { return this.dkef_fatx;}
	
	private String dkef_vakd = null;
	public void setDkef_vakd(String value) { this.dkef_vakd = value; }
	public String getDkef_vakd() { return this.dkef_vakd; }
	
	private String dkef_vaku = null;
	public void setDkef_vaku(String value) {  this.dkef_vaku = value; }
	public String getDkef_vaku() { return this.dkef_vaku;}

	private String dkef_fabl = null;
	public void setDkef_fabl(String value) {  this.dkef_fabl = value; }
	public String getDkef_fabl() { return this.dkef_fabl;}
	
	private Double dkef_fablDbl = 0.00D;
	public Double getDkef_fablDbl() { 
		if(this.dkef_fabl!=null){
			try{
				this.dkef_fablDbl = Double.parseDouble(this.dkef_fabl.replace(",", "."));
			}catch(Exception e){
				//nothing
			}
		}
		return this.dkef_fablDbl;
	}
	
	
	private String dkef_unik = null;
	public void setDkef_unik(String value) {  this.dkef_unik = value; }
	public String getDkef_unik() { return this.dkef_unik;}
	
	private String dkef_reff = null;
	public void setDkef_reff(String value) {  this.dkef_reff = value; }
	public String getDkef_reff() { return this.dkef_reff;}
	
	
	
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
