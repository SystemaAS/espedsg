package no.systema.tds.z.maintenance.main.model.jsonjackson.dbtable;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;

/**
 * 
 *@author oscardelatorre
 *@date Jun 3 2017
 */
public class JsonMaintSvtlvRecord extends JsonAbstractGrandFatherRecord  {

	private String svlv_kd = null;    //key                        
	public void setSvlv_kd (String value){ this.svlv_kd = value;   }   
	public String getSvlv_kd (){ return this.svlv_kd;   }  
	
	private String svlv_tr = null;                                
	public void setSvlv_tr (String value){ this.svlv_tr = value;   }   
	public String getSvlv_tr (){ return this.svlv_tr;   }  
	
	private String svlv_tr2 = null;                                
	public void setSvlv_tr2 (String value){ this.svlv_tr2 = value;   }   
	public String getSvlv_tr2 (){ return this.svlv_tr2;   }  
	
	private String svlv_fs = null;                                
	public void setSvlv_fs (String value){ this.svlv_fs = value;   }   
	public String getSvlv_fs (){ return this.svlv_fs;   }  
	
	private String svlv_fsp = null;                                
	public void setSvlv_fsp (String value){ this.svlv_fsp = value;   }   
	public String getSvlv_fsp (){ return this.svlv_fsp;   }  
	
	private String svlv_fs2 = null;                                
	public void setSvlv_fs2 (String value){ this.svlv_fs2 = value;   }   
	public String getSvlv_fs2 (){ return this.svlv_fs2;   }  
	
	private String svlv_fs2p = null;                                
	public void setSvlv_fs2p (String value){ this.svlv_fs2p = value;   }   
	public String getSvlv_fs2p (){ return this.svlv_fs2p;   }  
	
	private String svlv_ok = null;                                
	public void setSvlv_ok (String value){ this.svlv_ok = value;   }   
	public String getSvlv_ok (){ return this.svlv_ok;   }  
	
	private String svlv_kr = null;                                
	public void setSvlv_kr (String value){ this.svlv_kr = value;   }   
	public String getSvlv_kr (){ return this.svlv_kr;   }  
	
	private String svlv_ar = null;                                
	public void setSvlv_ar (String value){ this.svlv_ar = value;   }   
	public String getSvlv_ar (){ return this.svlv_ar;   }  
	
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
