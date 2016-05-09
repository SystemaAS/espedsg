/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;
import no.systema.tvinn.sad.util.TvinnSadDateFormatter;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author oscardelatorre
 * @date May 6, 2016
 *
 */
public class JsonMaintSadImportTariRecord extends JsonAbstractGrandFatherRecord {
	private TvinnSadDateFormatter dateFormatter = new TvinnSadDateFormatter();
	
    	
	private String tatanr = null;                                
	public void setTatanr (String value){ this.tatanr = value;   }   
	public String getTatanr (){ return this.tatanr;   }  
	
	private String tatar = null; 
	public void setTatar (String value){ this.tatar = value;   }   
	public String getTatar (){ return this.tatar;   }              

	private String tadato = null;
	public void setTadato (String value){ this.tadato = value;   }   
	public String getTadato (){ return this.tadato;   }              

	private String tadts = null;
	public void setTadts (String value){ this.tadts = value;   }   
	public String getTadts (){ return this.tadts;   }              

	private String tadtr = null; 
	public void setTadtr (String value){ this.tadtr = value;   }   
	public String getTadtr (){ return this.tadtr;   }              

	private String taordk = null; 
	public void setTaordk (String value){ this.taordk = value;   }   
	public String getTaordk (){ return this.taordk;   }              

	private String taordb = null; 
	public void setTaordb (String value){ this.taordb = value;   }   
	public String getTaordb (){ return this.taordb;   }              

	
	private String taeftk = null; 
	public void setTaeftk (String value){ this.taeftk = value;   }   
	public String getTaeftk (){ return this.taeftk;   }              

	private String taeftb = null; 
	public String getTaeftbPropertyName (){ return "taeftb"; }
	public void setTaeftb (String value){ this.taeftb = value;   }   
	public String getTaeftb (){ return this.taeftb;   }              
	
	private String taefk = null; 
	public void setTaefk (String value){ this.taefk = value;   }   
	public String getTaefk (){ return this.taefk;   }              

	private String taefb = null; 
	public void setTaefb (String value){ this.taefb = value;   }   
	public String getTaefb (){ return this.taefb;   }              
	
	private String tatxt = null; 
	public void setTatxt (String value){ this.tatxt = value;   }   
	public String getTatxt (){ return this.tatxt;   }              

	private String takapn = null; 
	public void setTakapn (String value){ this.takapn = value;   }   
	public String getTakapn (){ return this.takapn;   }              
	
	private String taalfa = null; 
	public void setTaalfa (String value){ this.taalfa = value;   }   
	public String getTaalfa (){ return this.taalfa;   }              
	
	private String taalfaOrig = ""; 
	public String getTaalfaOrigPropertyName (){ return "taalfaOrig"; }
	public void setTaalfaOrig (String value){ this.taalfaOrig = value;   }   
	public String getTaalfaOrig (){ return this.taalfaOrig;   }              
	
	
	private String tastk = null; 
	public void setTastk (String value){ this.tastk = value;   }   
	public String getTastk (){ return this.tastk;   }              
	
	private String takap = null; 
	public void setTakap (String value){ this.takap = value;   }   
	public String getTakap (){ return this.takap;   }              
	
	private String takapa = null; 
	public void setTakapa (String value){ this.takapa = value;   }   
	public String getTakapa (){ return this.takapa;   }              
	
	private String taenhe = null; 
	public void setTaenhe (String value){ this.taenhe = value;   }   
	public String getTaenhe (){ return this.taenhe;   }              
	
	private String tarest = null; 
	public void setTarest (String value){ this.tarest = value;   }   
	public String getTarest (){ return this.tarest;   }              
	
	private String takdae = null; 
	public void setTakdae (String value){ this.takdae = value;   }   
	public String getTakdae (){ return this.takdae;   }              
	
	private String takdse = null; 
	public void setTakdse (String value){ this.takdse = value;   }   
	public String getTakdse (){ return this.takdse;   }              
	
	private String taeosb = null; 
	public void setTaeosb (String value){ this.taeosb = value;   }   
	public String getTaeosb (){ return this.taeosb;   }              
	
	private String taeosk = null; 
	public void setTaeosk (String value){ this.taeosk = value;   }   
	public String getTaeosk (){ return this.taeosk;   }   
	
	private String tatsjb = null; 
	public void setTatsjb (String value){ this.tatsjb = value;   }   
	public String getTatsjb (){ return this.tatsjb;   }              
	
	private String tatsjk = null; 
	public void setTatsjk (String value){ this.tatsjk = value;   }   
	public String getTatsjk (){ return this.tatsjk;   }              
	
	private String tatyrb = null; 
	public void setTatyrb (String value){ this.tatyrb = value;   }   
	public String getTatyrb (){ return this.tatyrb;   }              
	
	private String tatyrk = null; 
	public void setTatyrk (String value){ this.tatyrk = value;   }   
	public String getTatyrk (){ return this.tatyrk;   }              
	
	private String taisrb = null; 
	public void setTaisrb (String value){ this.taisrb = value;   }   
	public String getTaisrb (){ return this.taisrb;   }              
	
	private String taisrk = null; 
	public void setTaisrk (String value){ this.taisrk = value;   }   
	public String getTaisrk (){ return this.taisrk;   }              
	
	private String taellb = null; 
	public void setTaellb (String value){ this.taellb = value;   }   
	public String getTaellb (){ return this.taellb;   }              
	
	private String taellk = null; 
	public void setTaellk (String value){ this.taellk = value;   }   
	public String getTaellk (){ return this.taellk;   }              
	
	
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
