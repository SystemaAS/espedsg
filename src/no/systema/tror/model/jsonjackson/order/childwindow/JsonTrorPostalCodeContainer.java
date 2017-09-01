/**
 * 
 */
package no.systema.tror.model.jsonjackson.order.childwindow;

import java.util.Collection;
/**
 * @author oscardelatorre
 * @date Sep 1, 2017
 */
public class JsonTrorPostalCodeContainer {
	
	private String user = null;
	public void setUser(String value){ this.user = value;}
	public String getUser(){ return this.user; }
	
	private String ctype = null;
	public void setCtype(String value){ this.ctype = value;}
	public String getCtype(){ return this.ctype; }
	
	private String errMsg = null;
	public void setErrMsg(String value){ this.errMsg = value;}
	public String getErrMsg(){ return this.errMsg; }
	
	private Collection<JsonTrorPostalCodeRecord> dtoList = null;
	public void setDtoList(Collection<JsonTrorPostalCodeRecord> value){ this.dtoList = value;}
	public Collection<JsonTrorPostalCodeRecord> getDtoList(){ return this.dtoList; }
	
}
