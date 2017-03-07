package no.systema.skat.z.maintenance.skatimport.model.jsonjackson.dbtable;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;

/**
 * All variables must be initialized to empty strings and NOT to NULL values
 * This is because the db-inserts that will be done in all fields of the db-table
 * 
 * @author oscardelatorre
 * @date Mar 07, 2017
 * 
 */
public class JsonMaintDktseRecord extends JsonAbstractGrandFatherRecord{

	private String dkse_knr = null;                                
	public void setDkse_knr (String value){ this.dkse_knr = value;   }   
	public String getDkse_knr (){ return this.dkse_knr;   }  
	
	private String dkse_331 = null; 
	public void setDkse_331 (String value){ this.dkse_331 = value;   }   
	public String getDkse_331 (){ return this.dkse_331;   }              

	private String dkse_34 = null;
	public void setDkse_34 (String value){ this.dkse_34 = value;   }   
	public String getDkse_34 (){ return this.dkse_34;   }              

	private String dkse_4421 = null;
	public void setDkse_4421 (String value){ this.dkse_4421 = value;   }   
	public String getDkse_4421 (){ return this.dkse_4421;   }              

	private String dkse_442A = null; 
	public void setDkse_442A (String value){ this.dkse_442A = value;   }   
	public String getDkse_442A (){ return this.dkse_442A;   }              
    
	private boolean validCustomerNumber = true;
	public void setValidCustomerNumber (boolean value){ this.validCustomerNumber = value;   }   
	public boolean isValidCustomerNumber (){ return this.validCustomerNumber;   }              
    
	private boolean validVarekode = true;
	public void setValidVarekode (boolean value){ this.validVarekode = value;   }   
	public boolean isValidVarekode (){ return this.validVarekode;   }              
    

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
