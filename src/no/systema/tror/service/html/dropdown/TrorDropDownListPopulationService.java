/**
 * 
 */
package no.systema.tror.service.html.dropdown;

import java.util.List;

import org.apache.log4j.Logger;

import no.systema.main.context.TdsServletContext;
import no.systema.main.util.io.TextFileReaderService;
import no.systema.tror.util.TrorConstants;

import no.systema.tror.model.jsonjackson.codes.JsonTrorCodeContainer;
import no.systema.tror.model.jsonjackson.codes.JsonTrorCountryCodeContainer;
import no.systema.tror.model.jsonjackson.codes.JsonTrorCurrencyCodeContainer;
import no.systema.tror.model.jsonjackson.codes.JsonTrorOppdragsTypeCodeContainer;
import no.systema.tror.model.jsonjackson.codes.JsonTrorIncotermsCodeContainer;
import no.systema.tror.model.jsonjackson.codes.JsonTrorProductCodeContainer;
import no.systema.tror.model.jsonjackson.codes.JsonTrorEnhetCodeContainer;
import no.systema.tror.model.jsonjackson.codes.JsonTrorSignatureCodeContainer;


import no.systema.tror.mapper.jsonjackson.JsonTrorCodeMapper;





/**
 * This service fetches values into drop downs in HTML
 * Criteria is based upon whether the drop down list is static or dynamic.
 * 
 * This class is used ONLY for STATIC lists (e.g. currency codes, country codes, etc)
 * 
 * The servlet context is necessary in order to get a text-xml file within the web-application path...
 * All static lists are retrieved from a file on disk.
 *  
 * @author oscardelatorre
 * @date Jan 07, 2017
 * 
 */
public class TrorDropDownListPopulationService {
	private static final Logger logger = Logger.getLogger(TrorDropDownListPopulationService.class.getName());
	
	private final String FILE_RESOURCE_PATH = TrorConstants.RESOURCE_FILES_PATH;
	private TextFileReaderService textFileReaderService = new TextFileReaderService();
	private JsonTrorCodeMapper codeMapper = new JsonTrorCodeMapper(); 
	
	/**
	 * Years
	 * @return
	 */
	public List<String> getYearList(){
		String LIST_FILE = "yearList.txt";
		List<String> list = textFileReaderService.getFileLines(TdsServletContext.getTdsServletContext().getResourceAsStream(this.FILE_RESOURCE_PATH + LIST_FILE));
		//Debug
		/*
		for(String record : list){
			logger.info(record + "X");
		}*/
		return list;
	}
	
	/**
	 * Months
	 * @return
	 */
	public List<String> getMonthList(){
		String LIST_FILE = "monthList.txt";
		List<String> list = textFileReaderService.getFileLines(TdsServletContext.getTdsServletContext().getResourceAsStream(this.FILE_RESOURCE_PATH + LIST_FILE));
		//Debug
		/*
		for(String record : list){
			logger.info(record + "X");
		}*/
		return list;
	}
	/**
	 * 
	 * @return
	 */
	public List<String> getCurrencyList(){
		String LIST_FILE = "currencyList.txt";
		List<String> list = textFileReaderService.getFileLines(TdsServletContext.getTdsServletContext().getResourceAsStream(this.FILE_RESOURCE_PATH + LIST_FILE));
		//Debug
		/*
		for(String record : list){
			logger.info(record + "X");
		}*/
		return list;
	}
	
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	/*
	public JsonTransportDispSignatureContainer getSignContainer(String utfPayload) throws Exception{
		return this.signMapper.getContainer(utfPayload);
	}*/
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	
	public JsonTrorCodeContainer getContainer(String utfPayload) throws Exception{
		return this.codeMapper.getContainer(utfPayload);
	}
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTrorCountryCodeContainer getCountryContainer(String utfPayload) throws Exception{
		return this.codeMapper.getCountryCodeContainer(utfPayload);
	}
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTrorCurrencyCodeContainer getCurrencyContainer(String utfPayload) throws Exception{
		return this.codeMapper.getCurrencyCodeContainer(utfPayload);
	}
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTrorOppdragsTypeCodeContainer getOppdragsTypeContainer(String utfPayload) throws Exception{
		return this.codeMapper.getOppdragsTypeCodeContainer(utfPayload);
	}
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTrorIncotermsCodeContainer getIncotermsContainer(String utfPayload) throws Exception{
		return this.codeMapper.getIncotermsCodeContainer(utfPayload);
	}
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTrorProductCodeContainer getProductContainer(String utfPayload) throws Exception{
		return this.codeMapper.getProductCodeContainer(utfPayload);
	}
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTrorEnhetCodeContainer getEnhetContainer(String utfPayload) throws Exception{
		return this.codeMapper.getEnhetCodeContainer(utfPayload);
	}
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTrorSignatureCodeContainer getSignatureContainer(String utfPayload) throws Exception{
		return this.codeMapper.getSignatureContainer(utfPayload);
	}
}
