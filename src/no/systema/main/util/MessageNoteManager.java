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
 * @date Maj 13, 2015
 * 
 */
public class MessageNoteManager {
	
	private static Logger logger = Logger.getLogger(MessageNoteManager.class.getName());
	/**
	 * Get lines separated with CR
	 * 
	 * @param message
	 * @return
	 */
	public String[] getChunksOfMessageNote(String message){
		String[] records = new String[30];
		if(message!=null){
			String messageRaw = message;
			if(messageRaw!=null){
				records = messageRaw.split("\\n");
				for (int i = 0; i < records.length; i++){
					records[i] = records[i].replace("\\n", "");
					logger.debug("##:" + records[i]);
				}
			}
		}
		return records;
	}
	

}
