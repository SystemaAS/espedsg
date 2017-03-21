package no.systema.skat.z.maintenance.main.model.jsonjackson.dbtable;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;
/**
 * All variables must be initialized to empty strings and NOT to NULL values
 * This is because the db-inserts that will be done in all fields of the db-table
 * 
 * @author oscardelatorre
 * @date Mar 21, 2017
 * 
 */
public class JsonMaintDkthaRecord extends JsonAbstractGrandFatherRecord  {

	private String dkth_sysg = null;                                
	public void setDkth_sysg (String value){ this.dkth_sysg = value;   }   
	public String getDkth_sysg (){ return this.dkth_sysg;   }  
	
	private String dkth_namn = null; 
	public void setDkth_namn (String value){ this.dkth_namn = value;   }   
	public String getDkth_namn (){ return this.dkth_namn;   }              

	private String dkth_usid = null;
	public void setDkth_usid (String value){ this.dkth_usid = value;   }   
	public String getDkth_usid (){ return this.dkth_usid;   }              

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
