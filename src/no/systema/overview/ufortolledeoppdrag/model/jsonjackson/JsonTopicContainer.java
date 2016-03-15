/**
 * 
 */
package no.systema.overview.ufortolledeoppdrag.model.jsonjackson;
import java.util.*;
/**
 * @author oscardelatorre
 * @date Aug 27, 2013
 * 
 */
public class JsonTopicContainer {
	
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private Collection<JsonTopicRecordAvdGroup> avdGroups = null;
	public void setAvdGroups(Collection<JsonTopicRecordAvdGroup> value) {  this.avdGroups = value; }
	public Collection<JsonTopicRecordAvdGroup> getAvdGroups() { return this.avdGroups;}
	
	private Collection<JsonTopicRecordDiagramChart> chartList = null;
	public void setChartList(Collection<JsonTopicRecordDiagramChart> value) {  this.chartList = value; }
	public Collection<JsonTopicRecordDiagramChart> getChartList() { return this.chartList;}
	
	private Collection<JsonTopicRecord> ufortList = null;
	public void setUfortList(Collection<JsonTopicRecord> value) {  this.ufortList = value; }
	public Collection<JsonTopicRecord> getUfortList() { return this.ufortList;}
	
	private String jsonPayload = null;
	public void setJsonPayload(String value) {  this.jsonPayload = value; }
	public String getJsonPayload() { return this.jsonPayload;}
	
	
	
	private Set<String> htmlDropDownTariffor = new HashSet<String>();
	public void setHtmlDropDownTariffor(Set<String> value) {  this.htmlDropDownTariffor = value; }
	public Set<String> getHtmlDropDownTariffor() { return this.htmlDropDownTariffor;}
	
	private Set<String> htmlDropDownSign = new HashSet<String>();
	public void setHtmlDropDownSign(Set<String> value) {  this.htmlDropDownSign = value; }
	public Set<String> getHtmlDropDownSign() { return this.htmlDropDownSign;}
	
	private Set<String> htmlDropDownAvd = new HashSet<String>();
	public void setHtmlDropDownAvd(Set<String> value) {  this.htmlDropDownAvd = value; }
	public Set<String> getHtmlDropDownAvd() { return this.htmlDropDownAvd;}
	
	private Set<String> htmlDropDownAvdAvdNavn = new HashSet<String>();
	public void setHtmlDropDownAvdAvdNavn(Set<String> value) {  this.htmlDropDownAvdAvdNavn = value; }
	public Set<String> getHtmlDropDownAvdAvdNavn() { return this.htmlDropDownAvdAvdNavn;}
	
	private Set<String> htmlDropDownAvdGroup = new HashSet<String>();
	public void setHtmlDropDownAvdGroup(Set<String> value) {  this.htmlDropDownAvdGroup = value; }
	public Set<String> getHtmlDropDownAvdGroup() { return this.htmlDropDownAvdGroup;}
	
	private Set<String> htmlDropDownTollagerkod = new HashSet<String>();
	public void setHtmlDropDownTollagerkod(Set<String> value) {  this.htmlDropDownTollagerkod = value; }
	public Set<String> getHtmlDropDownTollagerkod() { return this.htmlDropDownTollagerkod;}
	
	private Set<String> htmlDropDownTollagerdelkod = new HashSet<String>();
	public void setHtmlDropDownTollagerdelkod(Set<String> value) {  this.htmlDropDownTollagerdelkod = value; }
	public Set<String> getHtmlDropDownTollagerdelkod() { return this.htmlDropDownTollagerdelkod;}
	
	

}
