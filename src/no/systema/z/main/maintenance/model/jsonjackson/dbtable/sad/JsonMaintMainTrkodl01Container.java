/**
 * 
 */
package no.systema.z.main.maintenance.model.jsonjackson.dbtable.sad;

import java.util.Collection;

/**
 * @author oscardelatorre
 * @date Sep 21, 2016
 *
 */
public class JsonMaintMainTrkodl01Container {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	
	private Collection<JsonMaintMainTrkodl01Record> list;
	public void setList(Collection<JsonMaintMainTrkodl01Record> value){ this.list = value; }
	public Collection<JsonMaintMainTrkodl01Record> getList(){ return list; }
	
}
