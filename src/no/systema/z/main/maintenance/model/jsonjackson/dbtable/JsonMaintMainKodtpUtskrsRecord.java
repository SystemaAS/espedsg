package no.systema.z.main.maintenance.model.jsonjackson.dbtable;
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
 * @date Aug 9, 2016
 * 
 */
public class JsonMaintMainKodtpUtskrsRecord extends JsonAbstractGrandFatherRecord  {
	
	//KODTP table
	private String kopuni = "P"; //always as default                               
	public String getKopuniPropertyName (){ return "kopuni"; }
	public void setKopuni (String value){ this.kopuni = value;   }   
	public String getKopuni (){ return this.kopuni;   }  
	
	private String kopavd = "";                                
	public String getKopavdPropertyName (){ return "kopavd"; }
	public void setKopavd (String value){ this.kopavd = value;   }   
	public String getKopavd (){ return this.kopavd;   }  
	
	private String koplnr = "";                                
	public String getKoplnrPropertyName (){ return "koplnr"; }
	public void setKoplnr (String value){ this.koplnr = value;   }   
	public String getKoplnr (){ return this.koplnr;   }  
	
	private String kopnvn = "";                                
	public String getKopnvnPropertyName (){ return "kopnvn"; }
	public void setKopnvn (String value){ this.kopnvn = value;   }   
	public String getKopnvn (){ return this.kopnvn;   }  
	
	private String kopty = "";                                
	public String getKoptyPropertyName (){ return "kopty"; }
	public void setKopty (String value){ this.kopty = value;   }   
	public String getKopty (){ return this.kopty;   }  
	
	private String kophea = "";                                
	public String getKopheaPropertyName (){ return "kophea"; }
	public void setKophea (String value){ this.kophea = value;   }   
	public String getKophea (){ return this.kophea;   }  
	
	private String kopfm = "";                                
	public String getKopfmPropertyName (){ return "kopfm"; }
	public void setKopfm (String value){ this.kopfm = value;   }   
	public String getKopfm (){ return this.kopfm;   }  
	
	private String koplas = "";                                
	public String getKoplasPropertyName (){ return "koplas"; }
	public void setKoplas (String value){ this.koplas = value;   }   
	public String getKoplas (){ return this.koplas;   }  
	
	private String kopcpi = "";                                
	public String getKopcpiPropertyName (){ return "kopcpi"; }
	public void setKopcpi (String value){ this.kopcpi = value;   }   
	public String getKopcpi (){ return this.kopcpi;   }  
	
	private String koplpi = "";                                
	public String getKoplpiPropertyName (){ return "koplpi"; }
	public void setKoplpi (String value){ this.koplpi = value;   }   
	public String getKoplpi (){ return this.koplpi;   }  
	
	private String koplpp = "";                                
	public String getKoplppPropertyName (){ return "koplpp"; }
	public void setKoplpp (String value){ this.koplpp = value;   }   
	public String getKoplpp (){ return this.koplpp;   }  
	
	private String kopcpl = "";                                
	public String getKopcplPropertyName (){ return "kopcpl"; }
	public void setKopcpl (String value){ this.kopcpl = value;   }   
	public String getKopcpl (){ return this.kopcpl;   }  
	
	private String kopdraw = "";                                
	public String getKopdrawPropertyName (){ return "kopdraw"; }
	public void setKopdraw (String value){ this.kopdraw = value;   }   
	public String getKopdraw (){ return this.kopdraw;   }  
	
	private String kopoutb = "";                                
	public String getKopoutbPropertyName (){ return "kopoutb"; }
	public void setKopoutb (String value){ this.kopoutb = value;   }   
	public String getKopoutb (){ return this.kopoutb;   }  
	
	private String kopfor1 = "";                                
	public String getKopfor1PropertyName (){ return "kopfor1"; }
	public void setKopfor1 (String value){ this.kopfor1 = value;   }   
	public String getKopfor1 (){ return this.kopfor1;   }  
	
	private String kopfor2 = "";                                
	public String getKopfor2PropertyName (){ return "kopfor2"; }
	public void setKopfor2 (String value){ this.kopfor2 = value;   }   
	public String getKopfor2 (){ return this.kopfor2;   }  
	
	private String kopdupl = "";                                
	public String getKopduplPropertyName (){ return "kopdupl"; }
	public void setKopdupl (String value){ this.kopdupl = value;   }   
	public String getKopdupl (){ return this.kopdupl;   }  
	
	private String kopfov1 = "";                                
	public String getKopfov1PropertyName (){ return "kopfov1"; }
	public void setKopfov1 (String value){ this.kopfov1 = value;   }   
	public String getKopfov1 (){ return this.kopfov1;   }  
	
	private String kopfov2 = "";                                
	public String getKopfov2PropertyName (){ return "kopfov2"; }
	public void setKopfov2 (String value){ this.kopfov2 = value;   }   
	public String getKopfov2 (){ return this.kopfov2;   }  
	
	private String kopfov3 = "";                                
	public String getKopfov3PropertyName (){ return "kopfov3"; }
	public void setKopfov3 (String value){ this.kopfov3 = value;   }   
	public String getKopfov3 (){ return this.kopfov3;   }  
	
	private String kopfov4 = "";                                
	public String getKopfov4PropertyName (){ return "kopfov4"; }
	public void setKopfov4 (String value){ this.kopfov4 = value;   }   
	public String getKopfov4 (){ return this.kopfov4;   }  
	
	private String kopbov1 = "";                                
	public String getKopbov1PropertyName (){ return "kopbov1"; }
	public void setKopbov1 (String value){ this.kopbov1 = value;   }   
	public String getKopbov1 (){ return this.kopbov1;   }  
	
	private String kopbov2 = "";                        
	public String getKopbov2PropertyName (){ return "kopbov2"; }
	public void setKopbov2 (String value){ this.kopbov2 = value;   }   
	public String getKopbov2 (){ return this.kopbov2;   }  
	
	private String kopbov3 = "";                                
	public String getKopbov3PropertyName (){ return "kopbov3"; }
	public void setKopbov3 (String value){ this.kopbov3 = value;   }   
	public String getKopbov3 (){ return this.kopbov3;   }  
	
	private String kopbov4 = ""; 
	public String getKopbov4PropertyName (){ return "kopbov4"; }
	public void setKopbov4 (String value){ this.kopbov4 = value;   }   
	public String getKopbov4 (){ return this.kopbov4;   }              

	private String kopbov5 = ""; 
	public String getKopbov5PropertyName (){ return "kopbov5"; }
	public void setKopbov5 (String value){ this.kopbov5 = value;   }   
	public String getKopbov5 (){ return this.kopbov5;   }              

	private String kopcopi = ""; 
	public String getKopcopiPropertyName (){ return "kopcopi"; }
	public void setKopcopi (String value){ this.kopcopi = value;   }   
	public String getKopcopi (){ return this.kopcopi;   }              

	private String kophold = ""; 
	public String getKopholdPropertyName (){ return "kophold"; }
	public void setKophold (String value){ this.kophold = value;   }   
	public String getKophold (){ return this.kophold;   }              

	private String kopsave = ""; 
	public String getKopsavePropertyName (){ return "kopsave"; }
	public void setKopsave (String value){ this.kopsave = value;   }   
	public String getKopsave (){ return this.kopsave;   }              

	private String kopfrmp = ""; 
	public String getKopfrmpPropertyName (){ return "kopfrmp"; }
	public void setKopfrmp (String value){ this.kopfrmp = value;   }   
	public String getKopfrmp (){ return this.kopfrmp;   }              

	private String kopbamp = ""; 
	public String getKopbampPropertyName (){ return "kopbamp"; }
	public void setKopbamp (String value){ this.kopbamp = value;   }   
	public String getKopbamp (){ return this.kopbamp;   }              

	private String kopfrmf = ""; 
	public String getKopfrmfPropertyName (){ return "kopfrmf"; }
	public void setKopfrmf (String value){ this.kopfrmf = value;   }   
	public String getKopfrmf (){ return this.kopfrmf;   }              

	private String kopbamf = ""; 
	public String getKopbamfPropertyName (){ return "kopbamf"; }
	public void setKopbamf (String value){ this.kopbamf = value;   }   
	public String getKopbamf (){ return this.kopbamf;   }              

	//UTSKRS table
	private String utptxt = "";                           
	public String getUtptxtPropertyName (){ return "utptxt"; }
	public void setUtptxt (String value){ this.utptxt = value;   }   
	public String getUtptxt (){ return this.utptxt;   }  
	
	private String utpty = "";                                
	public String getUtptyPropertyName (){ return "utpty"; }
	public void setUtpty (String value){ this.utpty = value;   }   
	public String getUtpty (){ return this.utpty;   }  
	
	private String utpnr = "";                                
	public String getUtpnrPropertyName (){ return "utpnr"; }
	public void setUtpnr (String value){ this.utpnr = value;   }   
	public String getUtpnr (){ return this.utpnr;   }  
	
	private String utpl = "";                                
	public String getUtplPropertyName (){ return "utpl"; }
	public void setUtpl (String value){ this.utpl = value;   }   
	public String getUtpl (){ return this.utpl;   }  
	
	private String utpk = "";                                
	public String getUtpkPropertyName (){ return "utpk"; }
	public void setUtpk (String value){ this.utpk = value;   }   
	public String getUtpk (){ return this.utpk;   }  
	
	private String uthead = "";                                
	public String getUtheadPropertyName (){ return "uthead"; }
	public void setUthead (String value){ this.uthead = value;   }   
	public String getUthead (){ return this.uthead;   }  
	
	private String utpfm = "";                                
	public String getUtpfmPropertyName (){ return "utpfm"; }
	public void setUtpfm (String value){ this.utpfm = value;   }   
	public String getUtpfm (){ return this.utpfm;   }  
	
	private String utplas = "";                                
	public String getUtplasPropertyName (){ return "utplas"; }
	public void setUtplas (String value){ this.utplas = value;   }   
	public String getUtplas (){ return this.utplas;   }  
	
	private String utpcpi = "";                                
	public String getUtpcpiPropertyName (){ return "utpcpi"; }
	public void setUtpcpi (String value){ this.utpcpi = value;   }   
	public String getUtpcpi (){ return this.utpcpi;   }  
	
	private String utplpi = "";                                
	public String getUtplpiPropertyName (){ return "utplpi"; }
	public void setUtplpi (String value){ this.utplpi = value;   }   
	public String getUtplpi (){ return this.utplpi;   }  
	
	private String utplpp = "";                                
	public String getUtplppPropertyName (){ return "utplpp"; }
	public void setUtplpp (String value){ this.utplpp = value;   }   
	public String getUtplpp (){ return this.utplpp;   }  
	
	private String utpcpl = "";                                
	public String getUtpcplPropertyName (){ return "utpcpl"; }
	public void setUtpcpl (String value){ this.utpcpl = value;   }   
	public String getUtpcpl (){ return this.utpcpl;   }  
	
	private String utpdraw = "";                                
	public String getUtpdrawPropertyName (){ return "utpdraw"; }
	public void setUtpdraw (String value){ this.utpdraw = value;   }   
	public String getUtpdraw (){ return this.utpdraw;   }  
	
	private String utpoutb = "";                                
	public String getUtpoutbPropertyName (){ return "utpoutb"; }
	public void setUtpoutb (String value){ this.utpoutb = value;   }   
	public String getUtpoutb (){ return this.utpoutb;   }  
	
	private String utpfor1 = "";                                
	public String getUtpfor1PropertyName (){ return "utpfor1"; }
	public void setUtpfor1 (String value){ this.utpfor1 = value;   }   
	public String getUtpfor1 (){ return this.utpfor1;   }  
	
	private String utpfor2 = "";                                
	public String getUtpfor2PropertyName (){ return "utpfor2"; }
	public void setUtpfor2 (String value){ this.utpfor2 = value;   }   
	public String getUtpfor2 (){ return this.utpfor2;   }  
	
	private String utpdupl = "";                                
	public String getUtpduplPropertyName (){ return "utpdupl"; }
	public void setUtpdupl (String value){ this.utpdupl = value;   }   
	public String getUtpdupl (){ return this.utpdupl;   }  
	
	private String utpfov1 = "";                                
	public String getUtpfov1PropertyName (){ return "utpfov1"; }
	public void setUtpfov1 (String value){ this.utpfov1 = value;   }   
	public String getUtpfov1 (){ return this.utpfov1;   }  
	
	private String utpfov2 = "";                                
	public String getUtpfov2PropertyName (){ return "utpfov2"; }
	public void setUtpfov2 (String value){ this.utpfov2 = value;   }   
	public String getUtpfov2 (){ return this.utpfov2;   }  
	
	private String utpfov3 = "";                                
	public String getUtpfov3PropertyName (){ return "utpfov3"; }
	public void setUtpfov3 (String value){ this.utpfov3 = value;   }   
	public String getUtpfov3 (){ return this.utpfov3;   }  
	
	private String utpfov4 = "";                                
	public String getUtpfov4PropertyName (){ return "utpfov4"; }
	public void setUtpfov4 (String value){ this.utpfov4 = value;   }   
	public String getUtpfov4 (){ return this.utpfov4;   }  
	
	private String utpbov1 = "";                                
	public String getUtpbov1PropertyName (){ return "utpbov1"; }
	public void setUtpbov1 (String value){ this.utpbov1 = value;   }   
	public String getUtpbov1 (){ return this.utpbov1;   }  
	
	private String utpbov2 = "";                        
	public String getUtpbov2PropertyName (){ return "utpbov2"; }
	public void setUtpbov2 (String value){ this.utpbov2 = value;   }   
	public String getUtpbov2 (){ return this.utpbov2;   }  
	
	private String utpbov3 = "";                                
	public String getUtpbov3PropertyName (){ return "utpbov3"; }
	public void setUtpbov3 (String value){ this.utpbov3 = value;   }   
	public String getUtpbov3 (){ return this.utpbov3;   }  
	
	private String utpbov4 = ""; 
	public String getUtpbov4PropertyName (){ return "utpbov4"; }
	public void setUtpbov4 (String value){ this.utpbov4 = value;   }   
	public String getUtpbov4 (){ return this.utpbov4;   }              

	private String utpbov5 = ""; 
	public String getUtpbov5PropertyName (){ return "utpbov5"; }
	public void setUtpbov5 (String value){ this.utpbov5 = value;   }   
	public String getUtpbov5 (){ return this.utpbov5;   }              

	private String utpcopi = ""; 
	public String getUtpcopiPropertyName (){ return "utpcopi"; }
	public void setUtpcopi (String value){ this.utpcopi = value;   }   
	public String getUtpcopi (){ return this.utpcopi;   }              

	private String utphold = ""; 
	public String getUtpholdPropertyName (){ return "utphold"; }
	public void setUtphold (String value){ this.utphold = value;   }   
	public String getUtphold (){ return this.utphold;   }              

	private String utpsave = ""; 
	public String getUtpsavePropertyName (){ return "utpsave"; }
	public void setUtpsave (String value){ this.utpsave = value;   }   
	public String getUtpsave (){ return this.utpsave;   }              

	private String utpfrmp = ""; 
	public String getUtpfrmpPropertyName (){ return "utpfrmp"; }
	public void setUtpfrmp (String value){ this.utpfrmp = value;   }   
	public String getUtpfrmp (){ return this.utpfrmp;   }              

	private String utpbamp = ""; 
	public String getUtpbampPropertyName (){ return "utpbamp"; }
	public void setUtpbamp (String value){ this.utpbamp = value;   }   
	public String getUtpbamp (){ return this.utpbamp;   }              

	private String utpfrmf = ""; 
	public String getUtpfrmfPropertyName (){ return "utpfrmf"; }
	public void setUtpfrmf (String value){ this.utpfrmf = value;   }   
	public String getUtpfrmf (){ return this.utpfrmf;   }              

	private String utpbamf = ""; 
	public String getUtpbamfPropertyName (){ return "utpbamf"; }
	public void setUtpbamf (String value){ this.utpbamf = value;   }   
	public String getUtpbamf (){ return this.utpbamf;   }
	
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
