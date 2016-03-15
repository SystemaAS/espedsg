/**
 * 
 */
package no.systema.skat.model.jsonjackson.codes;

import java.util.Comparator;
/**
 * @author oscardelatorre
 * @date Jan 24, 2014
 * 
 */
public class JsonSkatCodeRecord implements Comparable<JsonSkatCodeRecord> {
	
	@Override
	/**
	 * must be implemented. In this case we just put a default
	 */
	public int compareTo(JsonSkatCodeRecord record) {
		return 1;
	}
	
	/**
	 * 
	 * Comparator implementation to Sort JsonSkatTopicRecord object based on other fields than default compareTo
	 * We must encapsulate this inner classes in order to accomplish this behavior within its outer class
	 */
	public static class OrderByDkkd_kd implements Comparator<JsonSkatCodeRecord> {
        @Override
        public int compare(JsonSkatCodeRecord o1, JsonSkatCodeRecord o2) {
            return o1.dkkd_kd.compareTo(o2.dkkd_kd);
        }
    }
	private String dkkd_kd = null;
	public void setDkkd_kd(String value){ this.dkkd_kd = value;}
	public String getDkkd_kd(){ return this.dkkd_kd; }
	
	private String dkkd_kd2 = null;
	public void setDkkd_kd2(String value){ this.dkkd_kd2 = value;}
	public String getDkkd_kd2(){ return this.dkkd_kd2; }
	
	private String dkkd_kd3 = null;
	public void setDkkd_kd3(String value){ this.dkkd_kd3 = value;}
	public String getDkkd_kd3(){ return this.dkkd_kd3; }
	
	private String dkkf_txt = null;
	public void setDkkf_txt(String value){ this.dkkf_txt = value;}
	public String getDkkf_txt(){ return this.dkkf_txt; }

	
}
