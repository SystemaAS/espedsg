/**
 * 
 */
package no.systema.skat.skatimport.model.jsonjackson.topic;
import java.util.Collection;
/**
 * 
 * @author oscardelatorre
 * @date Feb 20, 2014
 * 
 */
public class JsonSkatImportSpecificTopicOmbudContainer {
	
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() {return this.user;}
	
	private String avd = null;
	public void setAvd(String value) {  this.avd = value; }
	public String getAvd() {return this.avd;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() {return this.errMsg;}
	
	private Collection<JsonSkatImportSpecificTopicOmbudRecord> getGetdepinf = null;
	public void setGetdepinf(Collection<JsonSkatImportSpecificTopicOmbudRecord> value) {  this.getGetdepinf = value; }
	public Collection<JsonSkatImportSpecificTopicOmbudRecord> getGetdepinf() {return this.getGetdepinf;}
	
	
}
