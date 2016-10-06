package no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.gyldigekoder;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;
 

/**
 * All variables must be initialized to empty strings and NOT to NULL values
 * This is because the db-inserts that will be done in all fields of the db-table
 * 
 * @author oscardelatorre
 * @date Oct 6, 2016
 * 
 */
public class JsonMaintSadImportKodts9Record extends JsonAbstractGrandFatherRecord {

	private String ks9sta = null; 
	public void setKs9sta (String value){ this.ks9sta = value;   }   
	public String getKs9sta (){ return this.ks9sta;   }              

	private String ks9uni = null; 
	public void setKs9uni (String value){ this.ks9uni = value;   }   
	public String getKs9uni (){ return this.ks9uni;   }              

	private String ks9typ = null; 
	public void setKs9typ (String value){ this.ks9typ = value;   }   
	public String getKs9typ(){ return this.ks9typ;   }              

	private String ks9ftx =  null;
	public void setKs9ftx (String value){ this.ks9ftx = value;   }   
	public String getKs9ftx (){ return this.ks9ftx;   }              

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
