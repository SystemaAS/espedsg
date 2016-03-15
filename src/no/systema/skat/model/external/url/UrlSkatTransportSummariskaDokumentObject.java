/**
 * 
 */
package no.systema.skat.model.external.url;

/**
 * @author oscardelatorre
 * @date Jan 30, 2014
 * 
 */
public final class UrlSkatTransportSummariskaDokumentObject {
	public static final String value = "http://www.skat.dk/SKAT.aspx?oId=1658884";
	//public static final String windowOpenDimensions = "window.open(this.href,\'Taric\',\'width=750,height=500,left=5,top=20, scrollbars, resizable'); return false";
	public static final String windowOpenDimensions = "window.open(this.href,\'SKAT\'); return false";
	public String getValue(){return UrlSkatTransportSummariskaDokumentObject.value; }
	public String getWindowOpenDimensions(){return UrlSkatTransportSummariskaDokumentObject.windowOpenDimensions; }
	
}
