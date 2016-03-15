/**
 * 
 */
package no.systema.skat.skatimport.model.jsonjackson.topic.items;

import java.util.Collection;



/**
 * @author oscardelatorre
 * @date Oct 30, 2014
 * 
 */
public class JsonSkatImportSpecificTopicItemCertificateNrAndCodeR442Container {
	
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String dkih_mokn = null;
	public void setDkih_mokn(String value) {  this.dkih_mokn = value; }
	public String getDkih_mokn() { return this.dkih_mokn;}
	
	private String dkiv_331 = null;
	public void setDkiv_331(String value) {  this.dkiv_331 = value; }
	public String getDkiv_331() { return this.dkiv_331;}
	
	private String dkiv_34 = null;
	public void setDkiv_34(String value) {  this.dkiv_34 = value; }
	public String getDkiv_34() { return this.dkiv_34;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	private Collection<JsonSkatImportSpecificTopicItemCertificateNrAndCodeR442Record> certifikatList;
	public void setCertifikatList(Collection<JsonSkatImportSpecificTopicItemCertificateNrAndCodeR442Record> value){ this.certifikatList = value; }
	public Collection<JsonSkatImportSpecificTopicItemCertificateNrAndCodeR442Record> getCertifikatList(){ return certifikatList; }
	
	
	
}
