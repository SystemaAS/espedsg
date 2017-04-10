/**
 * 
 */
package no.systema.skat.z.maintenance.skatncts.model.jsonjackson.dbtable;

import java.util.Collection;

/**
 * @author oscardelatorre
 * @date Apr 10, 2017
 *
 */
public class JsonMaintDkxkodfContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	
	private Collection<JsonMaintDkxkodfRecord> list;
	public void setList(Collection<JsonMaintDkxkodfRecord> value){ this.list = value; }
	public Collection<JsonMaintDkxkodfRecord> getList(){ return list; }
	
}
