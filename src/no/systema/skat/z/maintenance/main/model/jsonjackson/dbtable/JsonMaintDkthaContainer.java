/**
 * 
 */
package no.systema.skat.z.maintenance.main.model.jsonjackson.dbtable;

import java.util.Collection;

/**
 * @author oscardelatorre
 * @date Mar 21, 2017
 *
 */
public class JsonMaintDkthaContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	private Collection<JsonMaintDkthaRecord> dtoList;
	public void setDtoList(Collection<JsonMaintDkthaRecord> value){ this.dtoList = value; }
	public Collection<JsonMaintDkthaRecord> getDtoList(){ return dtoList; }
	
}
