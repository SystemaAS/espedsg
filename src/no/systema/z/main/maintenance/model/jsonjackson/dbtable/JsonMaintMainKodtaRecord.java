package no.systema.z.main.maintenance.model.jsonjackson.dbtable;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;

/**
 * 
 * @author oscardelatorre
 * @date Aug 1, 2016
 * 
 */
public class JsonMaintMainKodtaRecord extends JsonAbstractGrandFatherRecord {
	
	private String koauni = null;                             
	public void setKoauni (String value){ this.koauni = value;   }   
	public String getKoauni (){ return this.koauni;   }  
	
	private String koaavd = null;                                 
	public void setKoaavd (String value){ this.koaavd = value;   }   
	public String getKoaavd (){ return this.koaavd;   }  
	
	private String koaknr = null;  
	public void setKoaknr (String value){ this.koaknr = value;   }   
	public String getKoaknr (){ return this.koaknr;   }              

	private String koabaer = null;  
	public void setKoabaer (String value){ this.koabaer = value;   }   
	public String getKoabaer (){ return this.koabaer;   }              

	private String koakon = null;  
	public void setKoakon (String value){ this.koakon = value;   }   
	public String getKoakon (){ return this.koakon;   }              

	private String koafir = null;  
	public void setKoafir (String value){ this.koafir = value;   }   
	public String getKoafir (){ return this.koafir;   }              

	private String koanvn = null;  
	public void setKoanvn (String value){ this.koanvn = value;   }   
	public String getKoanvn (){ return this.koanvn;   }              

	private String koaiat = null;  
	public void setKoaiat (String value){ this.koaiat = value;   }   
	public String getKoaiat (){ return this.koaiat;   }              

	private String koaie = null;  
	public void setKoaie (String value){ this.koaie = value;   }   
	public String getKoaie (){ return this.koaie;   }              

	private String koapos = null;  
	public void setKoapos (String value){ this.koapos = value;   }   
	public String getKoapos (){ return this.koapos;   }              

	private String koalk = null;  
	public void setKoalk (String value){ this.koalk = value;   }   
	public String getKoalk (){ return this.koalk;   }              

	
	//external fields from joins
	private String navsg = null;  
	public void setNavsg (String value){ this.navsg = value;   }   
	public String getNavsg (){ return this.navsg;   }              

	private String ksidnr = null;  
	public void setKsidnr (String value){ this.ksidnr = value;   }   
	public String getKsidnr (){ return this.ksidnr;   }              

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
