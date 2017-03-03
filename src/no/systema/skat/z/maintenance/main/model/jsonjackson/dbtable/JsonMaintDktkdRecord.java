package no.systema.skat.z.maintenance.main.model.jsonjackson.dbtable;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;

/**
 * All variables must be initialized to empty strings and NOT to NULL values
 * This is because the db-inserts that will be done in all fields of the db-table
 * 
 * @author oscardelatorre
 * @date Feb 28, 2017
 * 
 */
public class JsonMaintDktkdRecord extends JsonAbstractGrandFatherRecord{

	private String dkkd_typ = null;                                
	public void setDkkd_typ (String value){ this.dkkd_typ = value;   }   
	public String getDkkd_typ (){ return this.dkkd_typ;   }  
	
	private String dkkd_kd = null; 
	public void setDkkd_kd (String value){ this.dkkd_kd = value;   }   
	public String getDkkd_kd (){ return this.dkkd_kd;   }              

	private String dkkd_kd2 = null;
	public void setDkkd_kd2 (String value){ this.dkkd_kd2 = value;   }   
	public String getDkkd_kd2 (){ return this.dkkd_kd2;   }              

	private String dkkd_kd3 = null;
	public void setDkkd_kd3 (String value){ this.dkkd_kd3 = value;   }   
	public String getDkkd_kd3 (){ return this.dkkd_kd3;   }              

	private String dkkd_txt = null; 
	public void setDkkd_txt (String value){ this.dkkd_txt = value;   }   
	public String getDkkd_txt (){ return this.dkkd_txt;   }              

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
