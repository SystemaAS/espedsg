package no.systema.z.main.maintenance.model.jsonjackson.dbtable;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;

public class JsonMaintMainCundfRecord extends JsonAbstractGrandFatherRecord {

	private String kundnr = null;                                
	public void setKundnr (String value){ this.kundnr = value;   }   
	public String getKundnr (){ return this.kundnr;   }              

	private String firma = null;                                
	public void setFirma (String value){ this.firma = value;   }   
	public String getFirma (){ return this.firma;   }              

	private String knavn = null;                                
	public void setKnavn (String value){ this.knavn = value;   }   
	public String getKnavn (){ return this.knavn;   }              

	private String adr1 = null;                                
	public void setAdr1 (String value){ this.adr1 = value;   }   
	public String getAdr1 (){ return this.adr1;   }              

	private String adr2 = null;                                
	public void setAdr2 (String value){ this.adr2 = value;   }   
	public String getAdr2 (){ return this.adr2;   }              

	private String adr3 = null;                                
	public void setAdr3 (String value){ this.adr3 = value;   }   
	public String getAdr3 (){ return this.adr3;   }              

	private String postnr = null;                                
	public void setPostnr (String value){ this.postnr = value;   }   
	public String getPostnr (){ return this.postnr;   }              

	private String syrg = null;                                
	public void setSyrg (String value){ this.syrg = value;   }   
	public String getSyrg (){ return this.syrg;   }              

	private String syland = null;                                
	public void setSyland (String value){ this.syland = value;   }   
	public String getSyland (){ return this.syland;   }              

	private String sykont = null;                                
	public void setSykont (String value){ this.sykont = value;   }   
	public String getSykont (){ return this.sykont;   }              

	private String syfr02 = null;                                
	public void setSyfr02 (String value){ this.syfr02 = value;   }   
	public String getSyfr02 (){ return this.syfr02;   }              

	private String sonavn = null;                                
	public void setSonavn (String value){ this.sonavn = value;   }   
	public String getSonavn (){ return this.sonavn;   }              

	private String sypoge = null;                                
	public void setSypoge (String value){ this.sypoge = value;   }   
	public String getSypoge (){ return this.sypoge;   }              

	private String spraak = null;                                
	public void setSpraak (String value){ this.spraak = value;   }   
	public String getSpraak (){ return this.spraak;   }              

	private String eori = null;                                
	public void setEori (String value){ this.eori = value;   }   
	public String getEori (){ return this.eori;   }    
	
	private String pnpbku = null;                                
	public void setPnpbku (String value){ this.pnpbku = value;   }   
	public String getPnpbku(){ return this.pnpbku;   }    

	private String kpers = null;                                
	public void setKpers (String value){ this.kpers = value;   }   
	public String getKpers(){ return this.kpers;   }    	
	
	private String tlf = null;                                
	public void setTlf (String value){ this.tlf = value;   }   
	public String getTlf(){ return this.tlf;   }    	

	private String syepos = null;                                
	public void setSyepos (String value){ this.syepos = value;   }   
	public String getSyepos(){ return this.syepos;   }    	

	private String systat = null;                                
	public void setSystat (String value){ this.systat = value;   }   
	public String getSystat(){ return this.systat;   }    	

	private String valkod = null;                                
	public void setValkod (String value){ this.valkod = value;   }   
	public String getValkod(){ return this.valkod;   }    	

	private String kundgr = null;                                
	public void setKundgr (String value){ this.kundgr = value;   }   
	public String getKundgr(){ return this.kundgr;   }    	

	private String adr21 = null;                                
	public void setAdr21 (String value){ this.adr21 = value;   }   
	public String getAdr21(){ return this.adr21;   }    	
	
	private String fmot = null;                                
	public void setFmot (String value){ this.fmot = value;   }   
	public String getFmot(){ return this.fmot;   }    	

	
	/**
	 * 
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
