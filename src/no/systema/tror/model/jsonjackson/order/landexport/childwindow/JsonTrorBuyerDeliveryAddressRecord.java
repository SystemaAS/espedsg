package no.systema.tror.model.jsonjackson.order.landexport.childwindow;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import no.systema.main.util.StringManager;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;

public class JsonTrorBuyerDeliveryAddressRecord extends JsonAbstractGrandFatherRecord  {
	private StringManager strMgr = new StringManager();
	
	private String aktkod;
	private int journr;
	private String firma; // key
	private int kundnr; // key
	private int vadrnr; // key
	private String vadrna;
	private String vadrn1;
	private String vadrn2;
	private String vadrn3;
	private String sonavn;
	private int distr;
	private int rutenr;
	private int sekvnr;
	private int varegn;
	private int vakon1;
	private int vakon2;
	private String varg;
	private String vastat;
	private String valand;
	private String vakure;
	private String vatlf;
	private String vafax;
	private String vamail;
	private BigDecimal vabre = new BigDecimal(0);
	private BigDecimal valen = new BigDecimal(0);
	private Map<String, Object> keys = new HashMap<String, Object>();

	public String getAktkod() {
		return aktkod;
	}

	public void setAktkod(String aktkod) {
		this.aktkod = aktkod;
	}

	public int getJournr() {
		return journr;
	}

	public void setJournr(int journr) {
		this.journr = journr;
	}

	public String getFirma() {
		return firma;
	}

	public void setFirma(String firma) {
		this.firma = firma;
	}

	public int getKundnr() {
		return kundnr;
	}

	public void setKundnr(int kundnr) {
		this.kundnr = kundnr;
	}

	public int getVadrnr() {
		return vadrnr;
	}

	public void setVadrnr(int vadrnr) {
		this.vadrnr = vadrnr;
	}

	public String getVadrna() {
		return vadrna;
	}

	public void setVadrna(String vadrna) {
		this.vadrna = vadrna;
	}

	public String getVadrn1() {
		return vadrn1;
	}

	public void setVadrn1(String vadrn1) {
		this.vadrn1 = vadrn1;
	}

	public String getVadrn2() {
		return vadrn2;
	}

	public void setVadrn2(String vadrn2) {
		this.vadrn2 = vadrn2;
	}

	public String getVadrn3() {
		return vadrn3;
	}

	public void setVadrn3(String vadrn3) {
		this.vadrn3 = vadrn3;
	}

	public String getSonavn() {
		return sonavn;
	}

	public void setSonavn(String sonavn) {
		this.sonavn = sonavn;
	}

	public int getDistr() {
		return distr;
	}

	public void setDistr(int distr) {
		this.distr = distr;
	}

	public int getRutenr() {
		return rutenr;
	}

	public void setRutenr(int rutenr) {
		this.rutenr = rutenr;
	}

	public int getSekvnr() {
		return sekvnr;
	}

	public void setSekvnr(int sekvnr) {
		this.sekvnr = sekvnr;
	}

	public int getVaregn() {
		return varegn;
	}

	public void setVaregn(int varegn) {
		this.varegn = varegn;
	}

	public int getVakon1() {
		return vakon1;
	}

	public void setVakon1(int vakon1) {
		this.vakon1 = vakon1;
	}

	public int getVakon2() {
		return vakon2;
	}

	public void setVakon2(int vakon2) {
		this.vakon2 = vakon2;
	}

	public String getVarg() {
		return varg;
	}

	public void setVarg(String varg) {
		this.varg = varg;
	}

	public String getVastat() {
		return vastat;
	}

	public void setVastat(String vastat) {
		this.vastat = vastat;
	}

	public String getValand() {
		return valand;
	}

	public void setValand(String valand) {
		this.valand = valand;
	}

	public String getVakure() {
		return vakure;
	}

	public void setVakure(String vakure) {
		this.vakure = vakure;
	}

	public String getVatlf() {
		return vatlf;
	}

	public void setVatlf(String vatlf) {
		this.vatlf = vatlf;
	}

	public String getVafax() {
		return vafax;
	}

	public void setVafax(String vafax) {
		this.vafax = vafax;
	}

	public String getVamail() {
		return vamail;
	}

	public void setVamail(String vamail) {
		this.vamail = vamail;
	}

	public BigDecimal getVabre() {
		return vabre;
	}

	public void setVabre(BigDecimal vabre) {
		this.vabre = vabre;
	}

	public BigDecimal getValen() {
		return valen;
	}

	public void setValen(BigDecimal valen) {
		this.valen = valen;
	}
	
	private String postNr = null;
	public void setPostNr(String value){ this.postNr = value;}
	public String getPostNr(){ 
		if(strMgr.isNotNull (this.getVadrn3()) ){
			int index = this.getVadrn3().indexOf(" ");
			if(index >= 0 && this.getVadrn3().length()>5){
				String tmpPostnr = this.getVadrn3().substring(0, 4);
				String tmpCity = this.getVadrn3().substring(5);
				this.postNr = tmpPostnr;
			}
		}
		return this.postNr;
	}
	
	
	private String city = null;
	public void setCity(String value){ this.city = value;}
	public String getCity(){ 
		if(strMgr.isNotNull (this.getVadrn3()) ){
			int index = this.getVadrn3().indexOf(" ");
			if(index >= 0 && this.getVadrn3().length()>5){
				String tmpPostnr = this.getVadrn3().substring(0, 4);
				String tmpCity = this.getVadrn3().substring(5);
				this.city = tmpCity;
			}
		}
		return this.city;
	}

	/**
	 * User for java reflection in other classes
	 * @return
	 * @throws Exception
	 */
	public List<Field> getFields() throws Exception{
		Class cl = Class.forName(this.getClass().getCanonicalName());
		Field[] fields = cl.getDeclaredFields();
		List<Field> list = Arrays.asList(fields);
		
		return list;
	}

}
