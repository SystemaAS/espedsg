/**
 * 
 */
package no.systema.overview.sendingerlevtid.filter;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author oscardelatorre
 * @date Oct 4, 2013
 * 
 */
public class SearchFilterGateChart {
	SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyyMMdd");
	
	
	private String avd = null;
	public void setAvd(String value) {  this.avd = value; }
	public String getAvd() { return this.avd;}
	
	private String sign = null;
	public void setSign(String value) {  this.sign = value; }
	public String getSign() { return this.sign;}
	
	private String maxSn = "99999";
	public void setMaxSn(String value) {  this.maxSn = value; }
	public String getMaxSn() { return this.maxSn;}
	
	private String maxRc = "99999";
	public void setMaxRc(String value) {  this.maxRc = value; }
	public String getMaxRc() { return this.maxRc;}
	
	
	private String dato = null;
	public void setDato(String value) {  this.dato = value; }
	public String getDato() {
		if(this.dato==null){
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DATE, -7);
			this.dato = dateFormatter.format(calendar.getTime());
		}
		return this.dato;
	}
	
	private String datot = null;
	public void setDatot(String value) {  this.datot = value; }
	public String getDatot() {
		if(this.datot==null){
			Date date = Calendar.getInstance().getTime();
			this.datot = dateFormatter.format(date);
		}
		return this.datot;
	}
	
	private String agent = null;
	public void setAgent(String value) {  this.agent = value; }
	public String getAgent() { return this.agent;}
	
	private String agentg = null;
	public void setAgentg(String value) {  this.agentg = value; }
	public String getAgentg() { return this.agentg;}
	
	private String tur = null;
	public void setTur(String value) {  this.tur = value; }
	public String getTur() { return this.tur;}
	
	private String iols = null;
	public void setIols(String value) {  this.iols = value; }
	public String getIols() { return this.iols;}
	
	private String ls1 = null;
	public void setLs1(String value) {  this.ls1 = value; }
	public String getLs1() { return this.ls1;}
	
	private String ls2 = null;
	public void setLs2(String value) {  this.ls2 = value; }
	public String getLs2() { return this.ls2;}
	
	private String levyn = null;
	public void setLevyn(String value) {  this.levyn = value; }
	public String getLevyn() { return this.levyn;}
	
	private String poayn = null;
	public void setPoayn(String value) {  this.poayn = value; }
	public String getPoayn() { return this.poayn;}
	
	private String trans = null;
	public void setTrans(String value) {  this.trans = value; }
	public String getTrans() { return this.trans;}
	
	private String mottpost = null;
	public void setMottpost(String value) {  this.mottpost = value; }
	public String getMottpost() { return this.mottpost;}

	private String viskl = null;
	public void setViskl(String value) {  this.viskl = value; }
	public String getViskl() { return this.viskl;}

	private String ied = null;
	public void setIed(String value) {  this.ied = value; }
	public String getIed() { return this.ied;}
	
	private String iott = null;
	public void setIott(String value) {  this.iott = value; }
	public String getIott() { return this.iott;}
	
	private String tt1 = null;
	public void setTt1(String value) {  this.tt1 = value; }
	public String getTt1() { return this.tt1;}
	
	private String tt2 = null;
	public void setTt2(String value) {  this.tt2 = value; }
	public String getTt2() { return this.tt2;}
	
	private String tt3 = null;
	public void settTt3(String value) {  this.tt3 = value; }
	public String getTt3() { return this.tt3;}
	
	private String tt4 = null;
	public void setTt4(String value) {  this.tt4 = value; }
	public String getTt4() { return this.tt4;}
	
	private String tt5 = null;
	public void setTt5(String value) {  this.tt5 = value; }
	public String getTt5() { return this.tt5;}
	
	private String iottb = null;
	public void setIottb(String value) {  this.iottb = value; }
	public String getIottb() { return this.iottb;}
	
	private String tt1b = null;
	public void setTt1b(String value) {  this.tt1b = value; }
	public String getTt1b() { return this.tt1b;}
	
	private String tt2b = null;
	public void setTt2b(String value) {  this.tt2b = value; }
	public String getTt2b() { return this.tt2b;}
	
	private String tt3b = null;
	public void setTt3b(String value) {  this.tt3b = value; }
	public String getTt3b() { return this.tt3b;}
	
	private String tt4b = null;
	public void setTt4b(String value) {  this.tt4b = value; }
	public String getTt4b() { return this.tt4b;}
	
	private String tt5b = null;
	public void setTt5b(String value) {  this.tt5b = value; }
	public String getTt5b() { return this.tt5b;}
	
	
	
}
