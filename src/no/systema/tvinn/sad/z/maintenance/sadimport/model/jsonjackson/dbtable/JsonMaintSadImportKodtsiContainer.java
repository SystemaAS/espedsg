/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable;

import java.util.Collection;

/**
 * @author oscardelatorre
 * @date Apr 9, 2016
 *
 */
public class JsonMaintSadImportKodtsiContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	
	private Collection<JsonMaintSadImportKodtsiRecord> list;
	public void setList(Collection<JsonMaintSadImportKodtsiRecord> value){ this.list = value; }
	public Collection<JsonMaintSadImportKodtsiRecord> getList(){ return list; }
	
}
