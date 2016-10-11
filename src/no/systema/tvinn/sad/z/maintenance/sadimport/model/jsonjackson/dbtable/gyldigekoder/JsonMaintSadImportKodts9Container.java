/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.gyldigekoder;

import java.util.Collection;

/**
 * @author oscardelatorre
 * @date Okt 6, 2016
 *
 */
public class JsonMaintSadImportKodts9Container {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	
	private Collection<JsonMaintSadImportKodts9Record> list;
	public void setList(Collection<JsonMaintSadImportKodts9Record> value){ this.list = value; }
	public Collection<JsonMaintSadImportKodts9Record> getList(){ return list; }
	
}
