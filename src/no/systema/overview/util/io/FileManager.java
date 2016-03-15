/**
 * 
 */
package no.systema.overview.util.io;

import java.io.File;

import org.apache.log4j.Logger;

/**
 * @author oscardelatorre
 * @date Aug 27, 2013
 * 
 */
public class FileManager {
	private static final Logger logger = Logger.getLogger(FileManager.class.getName());
	public static final String JFREE_CHARTS_ROOT_DIRECTORY = "/WEB-INF/resources/tmpCharts";
	
	/**
	 * 
	 * @param oldFile
	 */
	public void deleteOldChartFile(File oldFile){
        if(oldFile!=null && oldFile.exists()){
            oldFile.delete();
            logger.info("old file: " + oldFile.getAbsolutePath() + " has been deleted successfully.");
        }
        
    }
}
