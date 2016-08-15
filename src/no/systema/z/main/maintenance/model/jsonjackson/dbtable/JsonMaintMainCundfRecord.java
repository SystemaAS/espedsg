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
