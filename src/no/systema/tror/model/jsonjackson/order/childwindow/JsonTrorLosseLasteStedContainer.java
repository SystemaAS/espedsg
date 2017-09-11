/**
 * 
 */
package no.systema.tror.model.jsonjackson.order.childwindow;

import java.util.Collection;


/**
 * General Code Container for Tror losse/laste sted codes
 * 
 * 
 *
 * @author oscardelatorre
 * @date Sep 11, 2017
 *
 */
public class JsonTrorLosseLasteStedContainer {
	private String user = null;
	public void setUser(String value){ this.user = value;}
	public String getUser(){ return this.user; }
	
	private String errMsg = null;
	public void setErrMsg(String value){ this.errMsg = value;}
	public String getErrMsg(){ return this.errMsg; }
	
	private Collection<JsonTrorLosseLasteStedRecord> dtoList = null;
	public void setDtoList(Collection<JsonTrorLosseLasteStedRecord> value){ this.dtoList = value;}
	public Collection<JsonTrorLosseLasteStedRecord> getDtoList(){ return this.dtoList; }
	
}
