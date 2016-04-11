/**
 * 
 */
package no.systema.skat.skatimport.model.jsonjackson.topic;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;
import no.systema.tvinn.sad.util.TvinnSadDateFormatter;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author oscardelatorre
 * @date Apr 6, 2016
 *
 */
public class JsonSkatImportTopicInvoiceExternalRecord extends JsonAbstractGrandFatherRecord {
	private TvinnSadDateFormatter dateFormatter = new TvinnSadDateFormatter();
	
	private String debugPrintlnAjax = null;
	public void setDebugPrintlnAjax(String value) {  this.debugPrintlnAjax = value; }
	public String getDebugPrintlnAjax() {return this.debugPrintlnAjax;}
	
	private String dkif_syav = null;
	public void setDkif_syav(String value) {  this.dkif_syav = value; }
	public String getDkif_syav() { return this.dkif_syav;}
	
	private String dkif_syop = null;
	public void setDkif_syop(String value) {  this.dkif_syop = value; }
	public String getDkif_syop() { return this.dkif_syop;}
	
	private String dkif_faty = null;
	public void setDkif_faty(String value) {  this.dkif_faty = value; }
	public String getDkif_faty() { return this.dkif_faty;}
	
	private String dkif_fatx = null;
	public void setDkif_fatx(String value) {  this.dkif_fatx = value; }
	public String getDkif_fatx() { return this.dkif_fatx;}
	
	private String dkif_vakd = null;
	public void setDkif_vakd(String value) { this.dkif_vakd = value; }
	public String getDkif_vakd() { return this.dkif_vakd; }
	
	private String dkif_vaku = null;
	public void setDkif_vaku(String value) {  this.dkif_vaku = value; }
	public String getDkif_vaku() { return this.dkif_vaku;}

	private String dkif_fabl = null;
	public void setDkif_fabl(String value) {  this.dkif_fabl = value; }
	public String getDkif_fabl() { return this.dkif_fabl;}
	
	private Double dkif_fablDbl = 0.00D;
	public Double getDkif_fablDbl() { 
		if(this.dkif_fabl!=null){
			try{
				this.dkif_fablDbl = Double.parseDouble(this.dkif_fabl.replace(",", "."));
			}catch(Exception e){
				//nothing
			}
		}
		return this.dkif_fablDbl;
	}
	
	
	private String dkif_unik = null;
	public void setDkif_unik(String value) {  this.dkif_unik = value; }
	public String getDkif_unik() { return this.dkif_unik;}
	
	private String dkif_reff = null;
	public void setDkif_reff(String value) {  this.dkif_reff = value; }
	public String getDkif_reff() { return this.dkif_reff;}
	
	
	
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
