/**
 * 
 */
package no.systema.fraktkalkulator.model.jsonjackson;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
/**
 * @author oscardelatorre
 * @date Feb 26, 2015
 */
public class JsonFraktKalkulatorUserContainer {
	
	private String wsuser = null;
	public void setWsuser(String value){ this.wsuser = value;}
	public String getWsuser(){ return this.wsuser; }
	
	private String xkunfrakt = null;
	public void setXkunfrakt(String value){ this.xkunfrakt = value;}
	public String getXkunfrakt(){ return this.xkunfrakt; }
	
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
	
	private String kunde = null;
	public void setKunde(String value){ this.kunde = value;}
	public String getKunde(){ return this.kunde; }
	
	private String avvknr = null;
	public void setAvvknr(String value){ this.avvknr = value;}
	public String getAvvknr(){ return this.avvknr; }
	
	private String sdato = null;
	public void setSdato(String value){ this.sdato = value;}
	public String getSdato(){ return this.sdato; }
	
	private String stid = null;
	public void setStid(String value){ this.stid = value;}
	public String getStid(){ return this.stid; }
	
	private String overskr = null;
	public void setOverskr(String value){ this.overskr = value;}
	public String getOverskr(){ return this.overskr; }
	
	private String frankod = null;
	public void setFrankod(String value){ this.frankod = value;}
	public String getFrankod(){ return this.frankod; }
	
	private String frantxt = null;
	public void setFrantxt(String value){ this.frantxt = value;}
	public String getFrantxt(){ return this.frantxt; }
	
	private String prodkod = null;
	public void setProdkod(String value){ this.prodkod = value;}
	public String getProdkod(){ return this.prodkod; }
	
	private String wsavd1 = null;
	public void setWsavd1(String value){ this.wsavd1 = value;}
	public String getWsavd1(){ return this.wsavd1; }
	
	private String wsavd2 = null;
	public void setWsavd2(String value){ this.wsavd2 = value;}
	public String getWsavd2(){ return this.wsavd2; }
	
	private String wsot1 = null;
	public void setWsot1(String value){ this.wsot1 = value;}
	public String getWsot1(){ return this.wsot1; }
	
	private String wsot2 = null;
	public void setWsot2(String value){ this.wsot2 = value;}
	public String getWsot2(){ return this.wsot2; }
	
	private String farlig = null;
	public void setFarlig(String value){ this.farlig = value;}
	public String getFarlig(){ return this.farlig; }
	
	private String varme = null;
	public void setVarme(String value){ this.varme = value;}
	public String getVarme(){ return this.varme; }
	
	private String lengde = null;
	public void setLengde(String value){ this.lengde = value;}
	public String getLengde(){ return this.lengde; }
	
	
	private String errMsg = null;
	public void setErrMsg(String value){ this.errMsg = value;}
	public String getErrMsg(){ return this.errMsg; }
	
	
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
