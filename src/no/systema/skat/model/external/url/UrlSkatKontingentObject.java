/**
 * 
 */
package no.systema.skat.model.external.url;

/**
 * @author oscardelatorre
 * @date Feb 13, 2014
 * 
 */
public final class UrlSkatKontingentObject {
	public static final String value = "http://tarif.skat.dk/tariff/uc/qqry/tqa/search.jsf?conversationId=164415";
	//public static final String windowOpenDimensions = "window.open(this.href,\'Taric\',\'width=750,height=500,left=5,top=20, scrollbars, resizable'); return false";
	public static final String windowOpenDimensions = "window.open(this.href,\'SKAT\'); return false";
	public String getValue(){return UrlSkatKontingentObject.value; }
	public String getWindowOpenDimensions(){return UrlSkatKontingentObject.windowOpenDimensions; }
	
}
