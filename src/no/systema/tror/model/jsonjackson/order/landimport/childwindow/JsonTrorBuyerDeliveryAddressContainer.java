/**
 * 
 */
package no.systema.tror.model.jsonjackson.order.landimport.childwindow;

import java.util.Collection;
/**
 * @author oscardelatorre
 * @date Aug 25, 2017
 */
public class JsonTrorBuyerDeliveryAddressContainer {
	
	private String user = null;
	public void setUser(String value){ this.user = value;}
	public String getUser(){ return this.user; }
	
	private String ctype = null;
	public void setCtype(String value){ this.ctype = value;}
	public String getCtype(){ return this.ctype; }
	
	private String kundnr = null;
	public void setKundnr(String value){ this.kundnr = value;}
	public String getKundnr(){ return this.kundnr; }
	
	private String errMsg = null;
	public void setErrMsg(String value){ this.errMsg = value;}
	public String getErrMsg(){ return this.errMsg; }
	
	private Collection<JsonTrorBuyerDeliveryAddressRecord> dtoList = null;
	public void setDtoList(Collection<JsonTrorBuyerDeliveryAddressRecord> value){ this.dtoList = value;}
	public Collection<JsonTrorBuyerDeliveryAddressRecord> getDtoList(){ return this.dtoList; }
	
}
