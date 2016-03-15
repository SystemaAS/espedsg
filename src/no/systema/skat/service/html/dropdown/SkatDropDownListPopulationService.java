/**
 * 
 */
package no.systema.skat.service.html.dropdown;

import no.systema.skat.mapper.jsonjackson.SkatCodeMapper;
import no.systema.skat.mapper.jsonjackson.avdsignature.SkatAvdelningMapper;
import no.systema.skat.mapper.jsonjackson.avdsignature.SkatSignatureMapper;


import no.systema.skat.model.jsonjackson.codes.JsonSkatCodeContainer;
import no.systema.skat.model.jsonjackson.codes.JsonSkatNctsCodeContainer;
import no.systema.skat.model.jsonjackson.avdsignature.JsonSkatAvdelningContainer;
import no.systema.skat.model.jsonjackson.avdsignature.JsonSkatSignatureContainer;



/**
 * Accesses the mapper (for codes) general routine from AS400
 * 
 * @author oscardelatorre
 * @date Jan 27, 2014
 * 
 */
public class SkatDropDownListPopulationService {
	private SkatCodeMapper codeMapper = new SkatCodeMapper();
	private SkatAvdelningMapper avdMapper = new SkatAvdelningMapper();
	private SkatSignatureMapper signMapper = new SkatSignatureMapper();
	
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonSkatCodeContainer getCodeContainer(String utfPayload) throws Exception{
		return this.codeMapper.getContainer(utfPayload);
	}
	
	
	public JsonSkatNctsCodeContainer getNctsCodeContainer(String utfPayload) throws Exception{
		return this.codeMapper.getNctsContainer(utfPayload);
	}
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonSkatAvdelningContainer getAvdelningContainer(String utfPayload) throws Exception{
		return this.avdMapper.getContainer(utfPayload);
	}
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonSkatSignatureContainer getSignatureContainer(String utfPayload) throws Exception{
		return this.signMapper.getContainer(utfPayload);
	}
}
