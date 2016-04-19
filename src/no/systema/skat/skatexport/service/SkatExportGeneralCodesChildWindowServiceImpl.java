/**
 * 
 */
package no.systema.skat.skatexport.service;

import no.systema.skat.mapper.jsonjackson.SkatCodeMapper;
import no.systema.skat.model.jsonjackson.codes.JsonSkatCodeContainer;
import no.systema.skat.model.jsonjackson.codes.JsonSkatNctsCodeContainer;




/**
 * Accesses the mapper (for codes) general routine from AS400
 * 
 * @author oscardelatorre
 * @date Jan 19, 2016
 * 
 */
public class SkatExportGeneralCodesChildWindowServiceImpl implements SkatExportGeneralCodesChildWindowService {
	private SkatCodeMapper codeMapper = new SkatCodeMapper();
	
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonSkatCodeContainer getCodeContainer(String utfPayload) throws Exception{
		return this.codeMapper.getContainer(utfPayload);
	}
	
	
}
