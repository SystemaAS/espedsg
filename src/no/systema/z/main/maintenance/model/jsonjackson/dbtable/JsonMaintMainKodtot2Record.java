package no.systema.z.main.maintenance.model.jsonjackson.dbtable;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;
import no.systema.main.util.*;

/**
 * All variables must be initialized to empty strings and NOT to NULL values
 * This is because the db-inserts that will be done in all fields of the db-table
 * 
 * @author oscardelatorre
 * @date Sep 22, 2016
 * 
 */
public class JsonMaintMainKodtot2Record extends JsonAbstractGrandFatherRecord  {
	
	private String ko2sta = "";                             
	public String getKo2staPropertyName (){ return "ko2sta"; }
	public void setKo2sta (String value){ this.ko2sta = value;   }   
	public String getKo2sta (){ return this.ko2sta;   }  
	
	private String ko2uni = "";                                
	public String getKo2uniPropertyName (){ return "ko2uni"; }
	public void setKo2uni (String value){ this.ko2uni = value;   }   
	public String getKo2uni (){ return this.ko2uni;   }  
	
	private String ko2kod = "";                                
	public String getKo2kodPropertyName (){ return "ko2kod"; }
	public void setKo2kod (String value){ this.ko2kod = value;   }   
	public String getKo2kod (){ return this.ko2kod;   }  
	
	private String ko2ntx = "";                                
	public String getKo2ntxPropertyName (){ return "ko2ntx"; }
	public void setKo2ntx (String value){ this.ko2ntx = value;   }   
	public String getKo2ntx (){ return this.ko2ntx;   }  
	
	private String ko2etx = "";                                
	public String getKo2etxPropertyName (){ return "ko2etx"; }
	public void setKo2etx (String value){ this.ko2etx = value;   }   
	public String getKo2etx (){ return this.ko2etx;   }  
	
	private String ko2fb = "";                                
	public String getKo2fbPropertyName (){ return "ko2fb"; }
	public void setKo2fb (String value){ this.ko2fb = value;   }   
	public String getKo2fb (){ return this.ko2fb;   }  
	
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
