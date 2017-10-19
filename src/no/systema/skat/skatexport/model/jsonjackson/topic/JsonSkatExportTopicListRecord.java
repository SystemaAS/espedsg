/**
 * 
 */
package no.systema.skat.skatexport.model.jsonjackson.topic;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author oscardelatorre
 * @date Feb 26, 2014
 *
 */
public class JsonSkatExportTopicListRecord extends JsonAbstractGrandFatherRecord {
	private String avd = null;
	public void setAvd(String value) {  this.avd = value; }
	public String getAvd() { return this.avd;}
	
	private String opd = null;
	public void setOpd(String value) {  this.opd = value; }
	public String getOpd() { return this.opd;}
	
	private String refnr = null;
	public void setRefnr(String value) {  this.refnr = value; }
	public String getRefnr() { return this.refnr;}
	
	private String dkeh_xref = null;
	public void setDkeh_xref(String value) {  this.dkeh_xref = value; }
	public String getDkeh_xref() {return this.dkeh_xref;}
	
	private String dkeh_mrn = null;
	public void setDkeh_mrn(String value) {  this.dkeh_mrn = value; }
	public String getDkeh_mrn() {return this.dkeh_mrn;}
	
	private String sign = null;
	public void setSign(String value) {  this.sign = value; }
	public String getSign() { return this.sign;}

	private String datum = null;
	public void setDatum(String value) {  this.datum = value; }
	public String getDatum() { return this.datum;}
	
	private String status = null;
	public void setStatus(String value) {  this.status = value; }
	public String getStatus() { return this.status;}

	private String avsNavn = null;
	public void setAvsNavn(String value) {  this.avsNavn = value; }
	public String getAvsNavn() { return this.avsNavn;}
	
	private String motNavn = null;
	public void setMotNavn(String value) {  this.motNavn = value; }
	public String getMotNavn() { return this.motNavn;}
	
	private String aart = null;
	public void setAart(String value) {  this.aart = value; }
	public String getAart() { return this.aart;}
	
	private String godsnr = null;
	public void setGodsnr(String value) {  this.godsnr = value; }
	public String getGodsnr() { return this.godsnr;}
	
	private String dkeh_221 = null;
	public void setDkeh_221(String value) {  this.dkeh_221 = value; }
	public String getDkeh_221() {return this.dkeh_221;}
	
	private String dkeh_222 = null;
	public void setDkeh_222(String value) {  this.dkeh_222 = value; }
	public String getDkeh_222() {return this.dkeh_222;}
	
	private String dkeh_181 = null;
	public void setDkeh_181(String value) {  this.dkeh_181 = value; }
	public String getDkeh_181() {return this.dkeh_181;}
	
	private String dkeh_prof = null;
	public void setDkeh_prof(String value) {  this.dkeh_prof = value; }
	public String getDkeh_prof() {return this.dkeh_prof;}
	
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
