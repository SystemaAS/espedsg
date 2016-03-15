/**
 * 
 */
package no.systema.overview.ufortolledeoppdrag.service;

import java.util.Collection;
import java.util.List;
import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.web.context.ServletContextAware;

import no.systema.main.context.TdsServletContext;
import no.systema.main.util.io.TextFileReaderService;
import no.systema.overview.ufortolledeoppdrag.model.jsonjackson.JsonTopicContainer;
import no.systema.overview.ufortolledeoppdrag.model.jsonjackson.JsonTopicRecordDiagramChart;
import no.systema.tds.nctsimport.controller.NctsImportUnloadingItemsController;
import no.systema.tds.util.TdsConstants;


/**
 * The class gets a std Json chart definition.
 * It will be used when a user profile lacks any chartList configuration (colors and START-END values)
 *  
 * @author oscardelatorre
 * @date Sep 17, 2013
 */
public class StandardFallbackChartListFor3DBarService {
	private static final Logger logger = Logger.getLogger(StandardFallbackChartListFor3DBarService.class.getName());
	
	private final String FILE_RESOURCE_PATH = TdsConstants.RESOURCE_FILES_PATH;
	private TextFileReaderService textFileReaderService = new TextFileReaderService();
	
	/**
	 * 
	 * @param uoppdragService
	 * @return
	 */
	public Collection<JsonTopicRecordDiagramChart> getStdImplementationFromTextFile(UoppdragService uoppdragService){
		String jsonPayload  = null;
		TextFileReaderService fileService = new TextFileReaderService();
		String JSON_CHART_FILE = "jsonStdUoppdragImplementationString.txt";
		List<String> list = textFileReaderService.getFileLines(TdsServletContext.getTdsServletContext().getResourceAsStream(this.FILE_RESOURCE_PATH + JSON_CHART_FILE));
		
		for(String record : list){
			//logger.info(record);
			jsonPayload = record;
			
		}
		JsonTopicContainer container = uoppdragService.getContainer(jsonPayload);
		Collection<JsonTopicRecordDiagramChart> chartList = container.getChartList();
		
		for(JsonTopicRecordDiagramChart record : chartList){
			//logger.info(record.isSingleValue());
		}
		return chartList;
	}
}
