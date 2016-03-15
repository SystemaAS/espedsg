/**
 * 
 */
package no.systema.asyjservices.model.jsonjackson;

import java.util.Collection;

/**
 * @author oscardelatorre
 * @date Nov 4, 2015
 *
 */
public class JsonSysJservicesMainListContainer {
	
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private Collection<JsonSysJservicesMainListRecord> list;
	public void setList(Collection<JsonSysJservicesMainListRecord> value){ this.list = value; }
	public Collection<JsonSysJservicesMainListRecord> getList(){ return list; }
	
	
}
