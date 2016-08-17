/**
 * 
 */
package no.systema.z.main.maintenance.model.jsonjackson.dbtable;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author oscardelatorre
 * @date Aug 5, 2016
 *
 */
public class JsonMaintMainKodtaHodel1Container {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	
	private Collection<JsonMaintMainKodtaHodel1Record> list;
	public void setList(Collection<JsonMaintMainKodtaHodel1Record> value){ this.list = value; }
	public Collection<JsonMaintMainKodtaHodel1Record> getList(){ return list; }
	
	
}
