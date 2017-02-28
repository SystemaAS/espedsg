/**
 * 
 */
package no.systema.skat.z.maintenance.skatexport.model.jsonjackson.dbtable;

import java.util.Collection;

/**
 * @author oscardelatorre
 * @date Feb 28, 2017
 *
 */
public class JsonMaintDktkdContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	
	private Collection<JsonMaintDktkdRecord> list;
	public void setList(Collection<JsonMaintDktkdRecord> value){ this.list = value; }
	public Collection<JsonMaintDktkdRecord> getList(){ return list; }
	
}
