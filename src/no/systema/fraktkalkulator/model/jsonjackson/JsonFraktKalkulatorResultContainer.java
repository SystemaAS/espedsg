/**
 * 
 */
package no.systema.fraktkalkulator.model.jsonjackson;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author oscardelatorre
 * @date Mar 4, 2015
 */
public class JsonFraktKalkulatorResultContainer {
	
	private String user = null;
	public void setUser(String value){ this.user = value;}
	public String getUser(){ return this.user; }
	
	private String xkunfrakt = null;
	public void setXkunfrakt(String value){ this.xkunfrakt = value;}
	public String getXkunfrakt(){ return this.xkunfrakt; }
	
	
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
	
	private String tilpn = null;
	public void setTilpn(String value){ this.tilpn = value;}
	public String getTilpn(){ return this.tilpn; }
	
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
	
	private String varme = null;
	public void setVarme(String value){ this.varme = value;}
	public String getVarme(){ return this.varme; }
	
	private String farlig = null;
	public void setFarlig(String value){ this.farlig = value;}
	public String getFarlig(){ return this.farlig; }
	
	private String lengde = null;
	public void setLengde(String value){ this.lengde = value;}
	public String getLengde(){ return this.lengde; }
	
	private String netto = null;
	public void setNetto(String value){ this.netto = value;}
	public String getNetto(){ return this.netto; }
	
	private String brutto = null;
	public void setBrutto(String value){ this.brutto = value;}
	public String getBrutto(){ return this.brutto; }
	
	private String oljvaltxt = null;
	public void setOljvaltxt(String value){ this.oljvaltxt = value;}
	public String getOljvaltxt(){ return this.oljvaltxt; }
	
	private String avdg = null;
	public void setAvdg(String value){ this.avdg = value;}
	public String getAvdg(){ return this.avdg; }
	
	private String frdg = null;
	public void setFrdg(String value){ this.frdg = value;}
	public String getFrdg(){ return this.frdg; }
	
	private String minfr = null;
	public void setMinfr(String value){ this.minfr = value;}
	public String getMinfr(){ return this.minfr; }
	
	private String wsavd = null;
	public void setWsavd(String value){ this.wsavd = value;}
	public String getWsavd(){ return this.wsavd; }
	
	private String kunde = null;
	public void setKunde(String value){ this.kunde = value;}
	public String getKunde(){ return this.kunde; }
	
	private String avvknr = null;
	public void setAvvknr(String value){ this.avvknr = value;}
	public String getAvvknr(){ return this.avvknr; }
	
	private String avvkntxt = null;
	public void setAvvkntxt(String value){ this.avvkntxt = value;}
	public String getAvvkntxt(){ return this.avvkntxt; }
	
	private String fbv = null;
	public void setFbv(String value){ this.fbv = value;}
	public String getFbv(){ return this.fbv; }
	
	private String overskr = null;
	public void setOverskr(String value){ this.overskr = value;}
	public String getOverskr(){ return this.overskr; }
	
	private String tjen = null;
	public void setTjen(String value){ this.tjen = value;}
	public String getTjen(){ return this.tjen; }
	
	private String heot = null;
	public void setHeot(String value){ this.heot = value;}
	public String getHeot(){ return this.heot; }
	
	private String hefr = null;
	public void setHefr(String value){ this.hefr = value;}
	public String getHefr(){ return this.hefr; }
	
	private String errMsg = null;
	public void setErrMsg(String value){ this.errMsg = value;}
	public String getErrMsg(){ return this.errMsg; }
	
	private Collection<JsonFraktKalkulatorResultRecord> pricecalclist = null;
	public void setPricecalclist(Collection<JsonFraktKalkulatorResultRecord> value){ this.pricecalclist = value;}
	public Collection<JsonFraktKalkulatorResultRecord> getPricecalclist(){ return this.pricecalclist; }
	
}
