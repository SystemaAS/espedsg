/**
 * 
 */
package no.systema.skat.z.maintenance.skatimport.model.jsonjackson.dbtable;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author oscardelatorre
 * @date Mar 06, 2017
 *
 */
public class JsonMaintDktseContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	
	private Collection<JsonMaintDktseRecord> dtoList;
	public void setDtoList(Collection<JsonMaintDktseRecord> value){ this.dtoList = value; }
	public Collection<JsonMaintDktseRecord> getDtoList(){ return dtoList; }
	
	private Collection<JsonMaintDktseRecord> list;
	public void setList(Collection<JsonMaintDktseRecord> value){ this.list = value; }
	public Collection<JsonMaintDktseRecord> getList(){ return list; }
	
	
	public List<Field> getFields() throws Exception{
		Class cl = Class.forName(this.getClass().getCanonicalName());
		Field[] fields = cl.getDeclaredFields();
		List<Field> list = Arrays.asList(fields);
		
		return list;
	}
}
