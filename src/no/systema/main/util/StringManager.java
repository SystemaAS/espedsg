/**
 * 
 */
package no.systema.main.util;

import java.util.*;
import org.apache.log4j.Logger;

/**
 * Utility class to manage message notes issues
 * 
 * @author oscardelatorre
 * @date Aug 11, 2016
 * 
 */
public class StringManager {
	private static Logger logger = Logger.getLogger(StringManager.class.getName());
	
	/**
	 * Get lines separated with CR
	 * 
	 * @param message
	 * @return
	 */
	public String paddingString(String value, int limit){
		String SPACE = " ";
		int FTX_LIMIT = limit;
		StringBuffer str = new StringBuffer(value);
		int len = str.length();
		for (int x=len+1;x<=FTX_LIMIT;x++){
			str.append(SPACE);
		}
		return str.toString();
	}
	

}
