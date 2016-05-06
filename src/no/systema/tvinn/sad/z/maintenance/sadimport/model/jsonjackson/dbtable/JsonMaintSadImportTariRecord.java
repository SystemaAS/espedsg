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
	
	private String takdsae = null; 
	public void setTakdsae (String value){ this.takdsae = value;   }   
	public String getTakdsae (){ return this.takdsae;   }              
	
	private String taeoesb = null; 
	public void setTaeoesb (String value){ this.taeoesb = value;   }   
	public String getTaeoesb (){ return this.taeoesb;   }              
	
	private String taeoesk = null; 
	public void setTaeoesk (String value){ this.taeoesk = value;   }   
	public String getTaeoesk (){ return this.taeoesk;   }              
	
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
