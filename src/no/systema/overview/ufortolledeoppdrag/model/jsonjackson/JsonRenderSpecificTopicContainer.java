/**
 * 
 */
package no.systema.overview.ufortolledeoppdrag.model.jsonjackson;

import java.lang.reflect.Field;
import java.util.*;



/**
 * This class is the representation of an Uoppdrag (read only) usually coming from a list
 * This is the specific uoppdrag for the user to analyze (not editable in GUI)
 * 
 * @author oscardelatorre
 * @date Oct 15, 2013
 * 
 */
public class JsonRenderSpecificTopicContainer {
	
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}

	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg.trim();}

	private String avd = null;
	public void setAvd(String value) {  this.avd = value; }
	public String getAvd() { return this.avd;}
	
	private String opd = null;
	public void setOpd(String value) {  this.opd = value; }
	public String getOpd() { return this.opd.trim();}

	private String sign = null;
	public void setSign(String value) {  this.sign = value; }
	public String getSign() { return this.sign.trim();}

	private String tur = null;
	public void setTur(String value) {  this.tur = value; }
	public String getTur() { return this.tur;}
	
	private String godsNr = null;
	public void setGodsNr(String value) {  this.godsNr = value; }
	public String getGodsNr() { return this.godsNr;}
	
	
	private String pos = null;
	public void setPos(String value) {  this.pos = value; }
	public String getPos() { return this.pos;}
	
	private String uPos = null;
	public void setUPos(String value) {  this.uPos = value; }
	public String getUPos() { return this.uPos;}
	
	private String opdRef = null;
	public void setOpdRef(String value) {  this.opdRef = value; }
	public String getOpdRef() { return this.opdRef;}
	
	private String hawb = null;
	public void setHawb(String value) {  this.hawb = value; }
	public String getHawb() { return this.hawb.trim();}
	
	private String contNo = null;
	public void setContNo(String value) {  this.contNo = value; }
	public String getContNo() { return this.contNo;}
	
	private String avsKnr = null;
	public void setAvsKnr(String value) {  this.avsKnr = value; }
	public String getAvsKnr() { return this.avsKnr.trim();}
	
	private String avsNavn = null;
	public void setAvsNavn(String value) {  this.avsNavn = value; }
	public String getAvsNavn() { return this.avsNavn.trim();}
	
	private String avsAdr1 = null;
	public void setAvsAdr1(String value) {  this.avsAdr1 = value; }
	public String getAvsAdr1() { return this.avsAdr1.trim();}
	
	private String avsAdr2 = null;
	public void setAvsAdr2(String value) {  this.avsAdr2 = value; }
	public String getAvsAdr2() { return this.avsAdr2.trim();}
	
	private String avsAdr3 = null;
	public void setAvsAdr3(String value) {  this.avsAdr3 = value; }
	public String getAvsAdr3() { return this.avsAdr3.trim();}
	
	private String motKnr = null;
	public void setMotKnr(String value) {  this.motKnr = value; }
	public String getMotKnr() { return this.motKnr.trim();}
	
	private String motNavn = null;
	public void setMotNavn(String value) {  this.motNavn = value; }
	public String getMotNavn() { return this.motNavn.trim();}
	
	private String motAdr1 = null;
	public void setMotAdr1(String value) {  this.motAdr1 = value; }
	public String getMotAdr1() { return this.motAdr1.trim();}
	
	private String motAdr2 = null;
	public void setMotAdr2(String value) {  this.motAdr2 = value; }
	public String getMotAdr2() { return this.motAdr2.trim();}
	
	private String motAdr3 = null;
	public void setMotAdr3(String value) {  this.motAdr3 = value; }
	public String getMotAdr3() { return this.motAdr3.trim();}
	
	private String antall = null;
	public void setAntall(String value) {  this.antall = value; }
	public String getAntall() { return this.antall.trim();}

	private String vareslag = null;
	public void setVareslag(String value) {  this.vareslag = value; }
	public String getVareslag() { return this.vareslag.trim();}
	
	private String vekt = null;
	public void setVekt(String value) {  this.vekt = value; }
	public String getVekt() { return this.vekt.trim();}

	private String godsmerke = null;
	public void setGodsmerke(String value) {  this.godsmerke = value; }
	public String getGodsmerke() { return this.godsmerke.trim();}
	
	private String m3 = null;
	public void setM3(String value) {  this.m3 = value; }
	public String getM3() { return this.m3;}
	
	private String lm = null;
	public void setLm(String value) {  this.lm = value; }
	public String getLm() { return this.lm;}
	
	private String sadMerkn1 = "";
	public void setSadMerkn1(String value) {  this.sadMerkn1 = value; }
	public String getSadMerkn1() { return this.sadMerkn1;}
	
	private String sadMerkn2 = "";
	public void setSadMerkn2(String value) {  this.sadMerkn2 = value; }
	public String getSadMerkn2() { return this.sadMerkn2;}
	
	private String sadMerkn3 = "";
	public void setSadMerkn3(String value) {  this.sadMerkn3 = value; }
	public String getSadMerkn3() { return this.sadMerkn3;}
	
	private String sadMerkn4 = "";
	public void setSadMerkn4(String value) {  this.sadMerkn4 = value; }
	public String getSadMerkn4() { return this.sadMerkn4;}
	
	private String sadMerkn5 = "";
	public void setSadMerkn5(String value) {  this.sadMerkn5 = value; }
	public String getSadMerkn5() { return this.sadMerkn5;}
	
	private String sadMerkn6 = "";
	public void setSadMerkn6(String value) {  this.sadMerkn6 = value; }
	public String getSadMerkn6() { return this.sadMerkn6;}
	
	private String sadMerkn7 = "";
	public void setSadMerkn7(String value) {  this.sadMerkn7 = value; }
	public String getSadMerkn7() { return this.sadMerkn7;}
	
	private String sadMerkn8 = "";
	public void setSadMerkn8(String value) {  this.sadMerkn8 = value; }
	public String getSadMerkn8() { return this.sadMerkn8;}
	
	private String sadMerkn9 = "";
	public void setSadMerkn9(String value) {  this.sadMerkn9 = value; }
	public String getSadMerkn9() { return this.sadMerkn9;}
	
	private String sadMerkn10 = "";
	public void setSadMerkn10(String value) {  this.sadMerkn10 = value; }
	public String getSadMerkn10() { return this.sadMerkn10;}
	
	private String merknadAll = "";
	public String getMerknadAll() { 
		String space = " ";
		StringBuffer sb = new StringBuffer(0);
		sb.append(this.sadMerkn1.trim() + space);
		sb.append(this.sadMerkn2.trim() + space);
		sb.append(this.sadMerkn3.trim() + space);
		sb.append(this.sadMerkn4.trim() + space);
		sb.append(this.sadMerkn5.trim() + space);
		sb.append(this.sadMerkn6.trim() + space);
		sb.append(this.sadMerkn7.trim() + space);
		sb.append(this.sadMerkn8.trim() + space);
		sb.append(this.sadMerkn9.trim() + space);
		sb.append(this.sadMerkn10.trim());
		this.merknadAll = sb.toString();
		return this.merknadAll.trim();
	}
	
	
	
	/**
	 * Used for java reflection in other classes
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
