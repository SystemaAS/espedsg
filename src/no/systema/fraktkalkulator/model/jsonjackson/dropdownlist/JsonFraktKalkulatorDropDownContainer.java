/**
 * 
 */
package no.systema.fraktkalkulator.model.jsonjackson.dropdownlist;

import java.util.Collection;
/**
 * @author oscardelatorre
 * @date Mar 9, 2015
 */
public class JsonFraktKalkulatorDropDownContainer {
	
	private String wsuser = null;
	public void setWsuser(String value){ this.wsuser = value;}
	public String getWsuser(){ return this.wsuser; }
	
	private String wsavd = null;
	public void setWsavd(String value){ this.wsavd = value;}
	public String getWsavd(){ return this.wsavd; }
	
	private String wsot = null;
	public void setWsot(String value){ this.wsot = value;}
	public String getWsot(){ return this.wsot; }
	
	private String fra = null;
	public void setFra(String value){ this.fra = value;}
	public String getFra(){ return this.fra; }
	
	private String fralk = null;
	public void setFralk(String value){ this.fralk = value;}
	public String getFralk(){ return this.fralk; }
	
	private String til = null;
	public void setTil(String value){ this.til = value;}
	public String getTil(){ return this.til; }
	
	private String tillk = null;
	public void setTillk(String value){ this.tillk = value;}
	public String getTillk(){ return this.tillk; }
	
	private String vkt = null;
	public void setVkt(String value){ this.vkt = value;}
	public String getVkt(){ return this.vkt; }
	
	private String ant = null;
	public void setAnt(String value){ this.ant = value;}
	public String getAnt(){ return this.ant; }
	
	private String lm = null;
	public void setLm(String value){ this.lm = value;}
	public String getLm(){ return this.lm; }
	
	private String m3 = null;
	public void setM3(String value){ this.m3 = value;}
	public String getM3(){ return this.m3; }
	
	private String prod = null;
	public void setProd(String value){ this.prod = value;}
	public String getProd(){ return this.prod; }
	
	private String fran = null;
	public void setFran(String value){ this.fran = value;}
	public String getFran(){ return this.fran; }
	
	private String avvknr = null;
	public void setAvvknr(String value){ this.avvknr = value;}
	public String getAvvknr(){ return this.avvknr; }
	
	private String errMsg = null;
	public void setErrMsg(String value){ this.errMsg = value;}
	public String getErrMsg(){ return this.errMsg; }
	
	private Collection<JsonFraktKalkulatorProductRecord> pricecalclist_prod = null;
	public void setPricecalclist_prod(Collection<JsonFraktKalkulatorProductRecord> value){ this.pricecalclist_prod = value;}
	public Collection<JsonFraktKalkulatorProductRecord> getPricecalclist_prod(){ return this.pricecalclist_prod; }
	
	private Collection<JsonFraktKalkulatorOppdragTypeRecord> pricecalclist_ot = null;
	public void setPricecalclist_ot(Collection<JsonFraktKalkulatorOppdragTypeRecord> value){ this.pricecalclist_ot = value;}
	public Collection<JsonFraktKalkulatorOppdragTypeRecord> getPricecalclist_ot(){ return this.pricecalclist_ot; }
	
	private Collection<JsonFraktKalkulatorTjenesteTypeRecord> pricecalclist_tjen = null;
	public void setPricecalclist_tjen(Collection<JsonFraktKalkulatorTjenesteTypeRecord> value){ this.pricecalclist_tjen = value;}
	public Collection<JsonFraktKalkulatorTjenesteTypeRecord> getPricecalclist_tjen(){ return this.pricecalclist_tjen; }
	
	private Collection<JsonFraktKalkulatorIncotermsRecord> pricecalclist_levv = null;
	public void setPricecalclist_levv(Collection<JsonFraktKalkulatorIncotermsRecord> value){ this.pricecalclist_levv = value;}
	public Collection<JsonFraktKalkulatorIncotermsRecord> getPricecalclist_levv(){ return this.pricecalclist_levv; }
	
	
}
