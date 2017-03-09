/**
 * 
 */
package no.systema.skat.z.maintenance.main.service.html.dropdown;

import java.util.List;

import org.apache.log4j.Logger;

import no.systema.main.context.TdsServletContext;
import no.systema.main.util.io.TextFileReaderService;
import no.systema.skat.skatimport.mapper.url.request.UrlRequestParameterMapper;
import no.systema.skat.util.SkatConstants;


/**
 * This service fetches values into drop downs in HTML
 * Criteria is based upon whether the drop down list is static or dynamic.
 * 
 * This class is used ONLY for STATIC lists (e.g. currency codes, country codes, etc)
 * 
 * The servlet context is necessary in order to get a text-xml file within the webb-application path...
 * All static lists are retrieved from a file on disk.
 *  
 * @author oscardelatorre
 * @date Mar 09, 2017
 * 
 */
public class SkatMaintMainDropDownListPopulationService {
	private static final Logger logger = Logger.getLogger(SkatMaintMainDropDownListPopulationService.class.getName());
	
	private final String FILE_RESOURCE_PATH = SkatConstants.RESOURCE_FILES_PATH;
	private TextFileReaderService textFileReaderService = new TextFileReaderService();
	
	/**
	 * List of currencies
	 * @return
	 */
	public List<String> getToldsatstypeList(){
		String LIST_FILE = "skatMaintenanceToldsatstypeList.txt";
		List<String> list = textFileReaderService.getFileLines(TdsServletContext.getTdsServletContext().getResourceAsStream(this.FILE_RESOURCE_PATH + LIST_FILE));
		//Debug
		/*
		for(String record : list){
			logger.info(record);
		}*/
		return list;
	}
}
