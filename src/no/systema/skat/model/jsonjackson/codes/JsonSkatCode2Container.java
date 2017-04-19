/**
 * 
 */
package no.systema.skat.model.jsonjackson.codes;

import java.util.Collection;

import no.systema.tds.model.jsonjackson.codes.JsonTdsCodeRecord;

/**
 * General Code Container for Skat general codes
 * 
 * 
 *
 * @author oscardelatorre
 * @date Apr 19, 2017
 *
 */
public class JsonSkatCode2Container {
	private String user = null;
	public void setUser(String value){ this.user = value;}
	public String getUser(){ return this.user; }
	
	private String type = null;
	public void setType(String value){ this.type = value;}
	public String getType(){ return this.type; }
	
	private String errMsg = null;
	public void setErrMsg(String value){ this.errMsg = value;}
	public String getErrMsg(){ return this.errMsg; }
	
	private Collection<JsonSkatCode2Record> arkivkodelist = null;
	public void setArkivkodelist(Collection<JsonSkatCode2Record> value){ this.arkivkodelist = value;}
	public Collection<JsonSkatCode2Record> getArkivkodelist(){ return this.arkivkodelist; }
	
}
