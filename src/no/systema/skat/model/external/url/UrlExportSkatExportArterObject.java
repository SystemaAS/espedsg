/**
 * 
 */
package no.systema.skat.model.external.url;

/**
 * @author oscardelatorre
 * @date Mar 20, 2014
 * 
 */
public final class UrlExportSkatExportArterObject {
	public static final String value = "http://skat.dk/SKAT.aspx?oId=169104&chk=201378";
	//public static final String windowOpenDimensions = "window.open(this.href,\'Taric\',\'width=750,height=500,left=5,top=20, scrollbars, resizable'); return false";
	public static final String windowOpenDimensions = "window.open(this.href,\'SKAT\'); return false";
	public String getValue(){return UrlExportSkatExportArterObject.value; }
	public String getWindowOpenDimensions(){return UrlExportSkatExportArterObject.windowOpenDimensions; }
	
}
