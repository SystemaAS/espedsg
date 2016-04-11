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
 * @date 06  Apr, 2016
 *
 */
public class JsonSkatExportTopicInvoiceRecord extends JsonAbstractGrandFatherRecord {
	private TvinnSadDateFormatter dateFormatter = new TvinnSadDateFormatter();
	
	//This is to know if the invoice line is new or existent (update). We don't have any other line nr. or id to know whether it is an
	//update or a create new
	private String isModeUpdate = null;
	public void setIsModeUpdate(String value) {  this.isModeUpdate = value; }
	public String getIsModeUpdate() {return this.isModeUpdate;}
	
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

	private Double dkef_vakuDbl = 0.00D;
	public Double getDkef_vakuDbl() { 
		if(this.dkef_vaku!=null){
			try{
				this.dkef_vakuDbl = Double.parseDouble(this.dkef_vaku.replace(",", "."));
			}catch(Exception e){
				//nothing
			}
		}
		return this.dkef_vakuDbl;
	}
	
	private String dkef_fabl = null;
	public void setDkef_fabl(String value) {  this.dkef_fabl = value; }
	public String getDkef_fabl() { return this.dkef_fabl;}
	
	private Double dkef_fablDbl = 0.00D;
	public Double getSfkr28Dbl() { 
		if(this.dkef_fabl!=null){
			try{
				this.dkef_fablDbl = Double.parseDouble(this.dkef_fabl.replace(",", "."));
			}catch(Exception e){
				//nothing
			}
		}
		return this.dkef_fablDbl;
	}
	
	//None database field (internal)
	private String dkef_omr = null;
	public void setDkef_omr(String value) {  this.dkef_omr = value; }
	public String getDkef_omr() { return this.dkef_omr;}
	
	private Integer dkef_omrInt = 1;
	public Integer getDkef_omrInt() { 
		if(this.dkef_omr!= null){
			try{
				dkef_omrInt = Integer.parseInt(this.dkef_omr);
			}catch(Exception e){
				//nothing
			}
		}
		return this.dkef_omrInt;
	}
	
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
