/**
 * 
 */
package no.systema.overview.ufortolledeoppdrag.model.jsonjackson;

import java.lang.reflect.Field;
import java.util.*;



/**
 * @author oscardelatorre
 * @date Aug 27, 2013
 * 
 */
public class JsonTopicRecord implements Comparable<JsonTopicRecord> {
	
	/**
	 * this will sort by std default = "dager" (primary key)
	 */
	@Override
	public int compareTo(JsonTopicRecord record) {
		//must do an explicit convert in order to sort by "dager" numerical
		int d1 = Integer.parseInt(this.dager);
		int d2 = 0;
		if(record.dager!=null && !record.dager.equals("")){
			d2 = Integer.parseInt(record.dager);
		}
		//this will give the desired effect with natural order (asc =1) and reverseOrder (desc = -1)
		return d1 > d2 ? 1 : (d1 < d2 ? -1 : 0);
	}
	
	/**
	 * 
	 * Comparator implementation to Sort JsonTopicRecord object based on other fields than "dager" (which is the default)
	 * We must encapsulate this inner classes in order to accomplish this behavior within its outer class
	 */
	public static class OrderByOppdrag implements Comparator<JsonTopicRecord> {
        @Override
        public int compare(JsonTopicRecord o1, JsonTopicRecord o2) {
            return o1.oppdrag.compareTo(o2.oppdrag);
        }
    }
	public static class OrderByDato implements Comparator<JsonTopicRecord> {
        @Override
        public int compare(JsonTopicRecord o1, JsonTopicRecord o2) {
            return o1.dato.compareTo(o2.dato);
        }
    }
	public static class OrderBySign implements Comparator<JsonTopicRecord> {
        @Override
        public int compare(JsonTopicRecord o1, JsonTopicRecord o2) {
            return o1.sign.compareTo(o2.sign);
        }
    }
	public static class OrderByTariffor implements Comparator<JsonTopicRecord> {
        @Override
        public int compare(JsonTopicRecord o1, JsonTopicRecord o2) {
            return o1.tariffor.compareTo(o2.tariffor);
        }
    }
	public static class OrderByAvd implements Comparator<JsonTopicRecord> {
        @Override
        public int compare(JsonTopicRecord o1, JsonTopicRecord o2) {
        		//we must convert in order to sort as integer and not as String
	        	int d1 = Integer.parseInt(o1.avd);
	    		int d2 = Integer.parseInt(o2.avd);
	    		//this will give the desired effect with natural order (asc =1) and reverseOrder (desc = -1)
	    		return d1 > d2 ? 1 : (d1 < d2 ? -1 : 0);
            //return o1.avd.compareTo(o2.avd);
        }
    }
	public static class OrderByGodsnr implements Comparator<JsonTopicRecord> {
        @Override
        public int compare(JsonTopicRecord o1, JsonTopicRecord o2) {
            return o1.godsnr.compareTo(o2.godsnr);
        }
    }
	public static class OrderByExtRef implements Comparator<JsonTopicRecord> {
        @Override
        public int compare(JsonTopicRecord o1, JsonTopicRecord o2) {
            return o1.extRef.compareTo(o2.extRef);
        }
    }
	
	public static class OrderByTollagerkod implements Comparator<JsonTopicRecord> {
        @Override
        public int compare(JsonTopicRecord o1, JsonTopicRecord o2) {
            return o1.tollagerkod.compareTo(o2.tollagerkod);
        }
    }

	
	private String dato = null;
	public void setDato(String value) {  this.dato = value; }
	public String getDato() { return this.dato;}
	
	private String dager = null;
	public void setDager(String value) {  this.dager = (value !=null) ? value : "0";  }
	public String getDager() { return this.dager;}
	
	private String tariffor = null;
	public void setTariffor(String value) {  this.tariffor = value; }
	public String getTariffor() { return this.tariffor;}
	
	private String avd = null;
	public void setAvd(String value) {  this.avd = value; }
	public String getAvd() { return this.avd;}
	
	private String avdNavn = null;
	public void setAvdNavn(String value) {  this.avdNavn = value; }
	public String getAvdNavn() { return this.avdNavn;}
	
	private String opd = null;
	public void setOpd(String value) {  this.opd = value; }
	public String getOpd() { return this.opd.trim();}
	
	private String oppdrag = null;
	public void setOppdrag(String value) {  this.oppdrag = value; }
	public String getOppdrag() { return this.oppdrag.trim();}
	
	private String sign = null;
	public void setSign(String value) {  this.sign = value; }
	public String getSign() { return this.sign.trim();}
	
	private String extRef = null;
	public void setExtRef(String value) {  this.extRef = value; }
	public String getExtRef() { return this.extRef;}
	
	private String antHfakt = null;
	public void setAntHfakt(String value) {
		this.antHfakt = value;
		//must set antHfaktFlag in order to allow for Reflection comparison with SearchFilter...
		if(this.antHfakt!=null && !"".equals(this.antHfakt.trim())){
			this.antHfaktFlag = "Y";
		}
		
	}
	public String getAntHfakt() { return this.antHfakt.trim();}
	
	
	private String antHfaktFlag = null;
	public void setAntHfaktFlag(String value) {  this.antHfaktFlag = value; }
	public String getAntHfaktFlag() { return this.antHfaktFlag;}
	
	private String mottaker = null;
	public void setMottaker(String value) {  this.mottaker = value; }
	public String getMottaker() { return this.mottaker;}
	
	private String kll = null;
	public void setKll(String value) {  this.kll = value; }
	public String getKll() { return this.kll;}
	
	private String vekt = null;
	public void setVekt(String value) {  this.vekt = value; }
	public String getVekt() { return this.vekt.trim();}
	
	private String godsnr = null;
	public void setGodsnr(String value) { 
		int TOLLAGERKOD_START = 4;
		int TOLLAGERKOD_END = 9;
		//
		int TOLLAGERDELKOD_START = 12;
		int TOLLAGERDELKOD_END = 13;
		
		
		this.godsnr = value; 
		//set the tollagerkoden here
		if(this.godsnr!=null && !"".equals(this.godsnr.trim())){
			this.godsnr = this.godsnr.trim();
			if(this.godsnr.length()>9){
				this.setTollagerkod(this.godsnr.substring(TOLLAGERKOD_START,TOLLAGERKOD_END));
			}
			if(this.godsnr.length()>13){
				this.setTollagerdelkod(this.godsnr.substring(TOLLAGERDELKOD_START,TOLLAGERDELKOD_END));
			}
		}
	}
	public String getGodsnr() { return this.godsnr;}
	
	
	/**
	 * 201101050293008  01050 = tollagerkoden , 293 = julianskalender dag, 008= Ankomstnummer ('enhet') denne dag (en ankomst er ofte EN bil / EN container,  som så kan inneholde mange 'posisjoner')   
     *  Obs - første-siffer av disse 3 KAN ofte benyttes 'kreativt' - se under PS.. 
	 * 
	 * Spec
	 * 2011        = Årstall 
	  01050        = tollagerkode(bevillingskode) 
		293        = dagnummer (juliansk kalender - dag siden nyttår..) 
		008        = Ankomstnummer ('enhet') denne dag (en ankomst er ofte EN bil / EN container,  som så kan inneholde mange 'posisjoner')   
	 */
	private	String  tollagerkod = null;
	private void setTollagerkod(String value) {  this.tollagerkod=value; }
	public String getTollagerkod() { return this.tollagerkod;}
	//delkod
	private	String  tollagerdelkod = null;
	private void setTollagerdelkod(String value) {  this.tollagerdelkod=value; }
	public String getTollagerdelkod() { return this.tollagerdelkod;}
	
	
	private String status = null;
	public void setStatus(String value) {  
		this.status = value; 
		
		//must set antHfaktFlag in order to allow for Reflection comparison with SearchFilter...
		if(this.status!=null && !"".equals(this.status.trim())){
			this.statusFlag = "Y";
		}
	}
	public String getStatus() { return this.status.trim();}
	
	private String statusFlag = null;
	public void setStatusFlag(String value) {  this.statusFlag = value; }
	public String getStatusFlag() { return this.statusFlag;}
	
	private String merknad = null;
	public void setMerknad(String value) {  this.merknad = value; }
	public String getMerknad() { return this.merknad.trim();}
	
	/**
	 * Used for java reflection in other classes
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
