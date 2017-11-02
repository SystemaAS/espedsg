/**
 * 
 */
package no.systema.tror.model.jsonjackson;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author oscardelatorre
 * @date Aug 11, 2017
 *
 */
public class JsonTrorOrderHeaderContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	                  
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	private Collection<JsonTrorOrderHeaderRecord> dtoList;
	public void setDtoList(Collection<JsonTrorOrderHeaderRecord> value){ this.dtoList = value; }
	public Collection<JsonTrorOrderHeaderRecord> getDtoList(){ return dtoList; }
	
	private Collection<JsonTrorOrderHeaderRecordStatus> list;
	public void setList(Collection<JsonTrorOrderHeaderRecordStatus> value){ this.list = value; }
	public Collection<JsonTrorOrderHeaderRecordStatus> getList(){ return list; }
	
	/**
	 * Used for java reflection in other classes
	 * @return
	 * @throws Exception
	 */
	
	public List<Field> getFields() throws Exception{
		Class cl = Class.forName(this.getClass().getCanonicalName());
		Field[] fields = cl.getDeclaredFields();
		List<Field> list = Arrays.asList(fields);
		
		return list;
	}
	
}
