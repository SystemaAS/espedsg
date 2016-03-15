/**
 * 
 */
package no.systema.overview.ufortolledeoppdrag.filter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import no.systema.tds.nctsimport.controller.NctsImportUnloadingItemsController;

import org.apache.log4j.Logger;

/**
 * @author oscardelatorre
 * @date Aug 30, 2013
 * 
 */
public class SearchFilterUoppdrag{
	//private static final Logger logger = Logger.getLogger(SearchFilterUoppdrag.class.getName());
	
	private String dato = null;
	public void setDato(String value) {  this.dato = value; }
	public String getDato() { return this.dato;}
	
	private String dager = null;
	public void setDager(String value) {  this.dager = value; }
	public String getDager() { return this.dager;}
	
	private String tariffor = null;
	public void setTariffor(String value) {  this.tariffor = value; }
	public String getTariffor() { return this.tariffor;}
	
	private String avd = null;
	public void setAvd(String value) {  this.avd = value; }
	public String getAvd() { return this.avd;}
	
	private String opd = null;
	public void setOpd(String value) {  this.opd = value; }
	public String getOpd() { return this.opd;}
	
	private String oppdrag = null;
	public void setOppdrag(String value) {  this.oppdrag = value; }
	public String getOppdrag() { return this.oppdrag;}
	
	private String sign = null;
	public void setSign(String value) {  this.sign = value; }
	public String getSign() { return this.sign;}
	
	private String extRef = null;
	public void setExtRef(String value) {  this.extRef = value; }
	public String getExtRef() { return this.extRef;}
	
	private String antHfakt = null;
	public void setAntHfakt(String value) {  this.antHfakt = value; }
	public String getAntHfakt() { return this.antHfakt;}
	
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
	public String getVekt() { return this.vekt;}
	
	private String godsnr = null;
	public void setGodsnr(String value) {  this.godsnr = value; }
	public String getGodsnr() { return this.godsnr;}
	
	private String status = null;
	public void setStatus(String value) {  this.status = value; }
	public String getStatus() { return this.status;}
	
	private String statusFlag = null;
	public void setStatusFlag(String value) {  this.statusFlag = value; }
	public String getStatusFlag() { return this.statusFlag;}
	
	private String merknad = null;
	public void setMerknad(String value) {  this.merknad = value; }
	public String getMerknad() { return this.merknad;}
	
	private String avdList = null;
	public void setAvdList(String value) {  this.avdList = value; }
	public String getAvdList() { return this.avdList;}
	
	//This method is necessary in order to match the avdList with avd (found in ufortList) 
	//since the avdList has string values such as: 001, 002, 003...and the avd values are integer kinds: 1,2,3...
	public List<Integer>getAvdListMembersAsIntegers(){
		List<Integer> finalList = new ArrayList<Integer>();

		if(this.avdList!=null && !"".equals(this.avdList)){
			String[] arrayAvdList = this.avdList.split(",");
			List<String> list = Arrays.asList(arrayAvdList);
			for(String record : list){
				try{
					int value = Integer.parseInt(record);
					finalList.add(value);
				}catch(NumberFormatException e){
					//continue
				}
			}
		}
		
		return finalList;
		
	}
	
	private	String  tollagerkod = null;
	public void setTollagerkod(String value) {  this.tollagerkod=value; }
	public String getTollagerkod() { return this.tollagerkod;}
	//delkod
	private	String  tollagerdelkod = null;
	public void setTollagerdelkod(String value) {  this.tollagerdelkod=value; }
	public String getTollagerdelkod() { return this.tollagerdelkod;}
	
	
	
	/**
	 * Gets the populated values by reflection
	 * @param searchFilter
	 * @return
	 * @throws Exception
	 */
	public Map<String,String> getPopulatedFields() throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		
		Class cl = Class.forName(this.getClass().getCanonicalName());
		Field[] fields = cl.getDeclaredFields();
		List<Field> list = Arrays.asList(fields);
		for(Field field : list){
			field.setAccessible(true);
			//logger.info("FIELD NAME: " + field.getName() + "VALUE:" + (String)field.get(this));
			String value = (String)field.get(this);
			if(value!=null && !"".equals(value)){
				//logger.info(field.getName() + " Value:" + value);
				map.put(field.getName(), value);
			}
		}
		
		return map;
	}
	
}
