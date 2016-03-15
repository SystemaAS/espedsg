/**
 * 
 */
package no.systema.efaktura.model.jsonjackson;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;
import java.util.*;
import java.lang.reflect.Field;
/**
 * @author oscardelatorre
 * @date Jun 22, 2015
 * 
 *
 */
public class JsonEfakturaMainListRecord extends JsonAbstractGrandFatherRecord{
	
	private String avd = null;
	public void setAvd(String value) {  this.avd = value; }
	public String getAvd() {return this.avd;}
	
	private String opd = null;
	public void setOpd(String value) {  this.opd = value; }
	public String getOpd() {return this.opd;}
	
	//Fakturanr
	private String xffn = null;
	public void setXffn(String value) {  this.xffn = value; }
	public String getXffn() {return this.xffn;}
	
	private String herfa = null;
	public void setHerfa(String value) {  this.herfa = value; }
	public String getHerfa() {return this.herfa;}
	
	private String hedtop = null;
	public void setHedtop(String value) {  this.hedtop = value; }
	public String getHedtop() {return this.hedtop;}
	
	private String fadato = null;
	public void setFadato(String value) {  this.fadato = value; }
	public String getFadato() {return this.fadato;}
	
	private String hesdf = null;
	public void setHesdf(String value) {  this.hesdf = value; }
	public String getHesdf() {return this.hesdf;}
	
	private String hesdt = null;
	public void setHesdt(String value) {  this.hesdt = value; }
	public String getHesdt() {return this.hesdt;}
	
	private String henas = null;
	public void setHenas(String value) {  this.henas = value; }
	public String getHenas() {return this.henas;}
	
	private String henak = null;
	public void setHenak(String value) {  this.henak = value; }
	public String getHenak() {return this.henak;}
	
	private String hekdpl = null;
	public void setHekdpl(String value) {  this.hekdpl = value; }
	public String getHekdpl() {return this.hekdpl;}
	
	private String hent = null;
	public void setHent(String value) {  this.hent = value; }
	public String getHent() {return this.hent;}
	
	private String hevkt = null;
	public void setHevkt(String value) {  this.hevkt = value; }
	public String getHevkt() {return this.hevkt;}
	
	private String hem3 = null;
	public void setHem3(String value) {  this.hem3 = value; }
	public String getHem3() {return this.hem3;}
	
	private String helm = null;
	public void setHelm(String value) {  this.helm = value; }
	public String getHelm() {return this.helm;}
	
	private String xfst = null;
	public void setXfst(String value) {  this.xfst = value; }
	public String getXfst() {return this.xfst;}
	
	private String xfdts = null;
	public void setXfdts(String value) {  this.xfdts = value; }
	public String getXfdts() {return this.xfdts;}
	
	//kundnr
	private String xfkn = null;
	public void setXfkn(String value) {  this.xfkn = value; }
	public String getXfkn() {return this.xfkn;}
	
	private String xpdf = null;
	public void setXpdf(String value) {  this.xpdf = value; }
	public String getXpdf() {return this.xpdf;}
	
	public String documentName = null;
	public String getDocumentName() {
		if(this.xpdf!=null){
			int x = this.xpdf.lastIndexOf("/");
			this.documentName = xpdf.substring(x+1);
		}
		return this.documentName;
	}
	//utgående xml-faktura
	private String xsifs = null;
	public void setXsifs(String value) {  this.xsifs = value; }
	public String getXsifs() {return this.xsifs;}
	
	//inkommande xml-kvittering
	private String xrifs = null;
	public void setXrifs(String value) {  this.xrifs = value; }
	public String getXrifs() {return this.xrifs;}
	
	
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
