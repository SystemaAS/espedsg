/**
 * 
 */
package no.systema.skat.z.maintenance.main.model.jsonjackson.dbtable;

import java.util.Collection;

/**
 * @author oscardelatorre
 * @date Mar 09, 2017
 *
 */
public class JsonMaintDktardContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	
	private Collection<JsonMaintDktardRecord> dtoList;
	public void setDtoList(Collection<JsonMaintDktardRecord> value){ this.dtoList = value; }
	public Collection<JsonMaintDktardRecord> getDtoList(){ return dtoList; }
	
}
