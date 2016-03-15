/**
 * 
 */
package jsonjackson.nctsexport;

import java.io.*;   
import java.util.Collection;

import no.systema.skat.nctsimport.service.SkatNctsImportTextFileJsonService;
import no.systema.skat.service.html.dropdown.SkatDropDownListPopulationService;

import no.systema.skat.model.jsonjackson.codes.JsonSkatNctsCodeContainer;
import no.systema.skat.model.jsonjackson.codes.JsonSkatNctsCodeRecord;

/**
 * 
 * @author oscardelatorre
 *
 */
public class JsonTesterSkatStatus{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JsonTesterSkatStatus engine = new JsonTesterSkatStatus();
		//engine.run();
		
		
	}

	/**
	 * 
	 */
	/*
	private void run (){
		try{
			SkatDropDownListPopulationService skatDropDownListPopulationService = new SkatDropDownListPopulationService();
			SkatNctsImportTextFileJsonService jsonService = new SkatNctsImportTextFileJsonService();
			Collection<JsonSkatNctsCodeRecord> list = jsonService.getStatusImplementationFromTextFile(skatDropDownListPopulationService);
			for(JsonSkatNctsCodeRecord record : list){
				System.out.println(record.getTkkode());
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	*/
}
