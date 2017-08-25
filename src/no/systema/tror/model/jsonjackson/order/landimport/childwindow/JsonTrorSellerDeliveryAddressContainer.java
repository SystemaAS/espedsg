/**
 * 
 */
package no.systema.tror.model.jsonjackson.order.landimport.childwindow;

import java.util.Collection;
/**
 * @author oscardelatorre
 * @date Aug 24, 2017
 */
public class JsonTrorSellerDeliveryAddressContainer {
	
	private String user = null;
	public void setUser(String value){ this.user = value;}
	public String getUser(){ return this.user; }
	
	private String ctype = null;
	public void setCtype(String value){ this.ctype = value;}
	public String getCtype(){ return this.ctype; }
	
	private String kukun1 = null;
	public void setKukun1(String value){ this.kukun1 = value;}
	public String getKukun1(){ return this.kukun1; }
	
	private String errMsg = null;
	public void setErrMsg(String value){ this.errMsg = value;}
	public String getErrMsg(){ return this.errMsg; }
	
	private Collection<JsonTrorSellerDeliveryAddressRecord> dtoList = null;
	public void setDtoList(Collection<JsonTrorSellerDeliveryAddressRecord> value){ this.dtoList = value;}
	public Collection<JsonTrorSellerDeliveryAddressRecord> getDtoList(){ return this.dtoList; }
	
}
