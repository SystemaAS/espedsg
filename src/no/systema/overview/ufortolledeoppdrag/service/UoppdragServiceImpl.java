/**
 * 
 */
package no.systema.overview.ufortolledeoppdrag.service;

import no.systema.overview.ufortolledeoppdrag.model.jsonjackson.JsonTopicContainer;
import no.systema.overview.ufortolledeoppdrag.model.jsonjackson.archive.JsonTopicArchiveContainer;
import no.systema.overview.ufortolledeoppdrag.model.jsonjackson.logging.JsonTopicLoggingContainer;
import no.systema.overview.ufortolledeoppdrag.model.jsonjackson.tvinnlog.JsonTopicTvinnLogContainer;
import no.systema.overview.ufortolledeoppdrag.model.jsonjackson.tvinnlog.JsonTopicTvinnLogLargeTextContainer;
import no.systema.overview.ufortolledeoppdrag.model.jsonjackson.JsonRenderSpecificTopicContainer;
//mappers
import no.systema.overview.ufortolledeoppdrag.mapper.jsonjackson.JsonTopicMapper;
import no.systema.overview.ufortolledeoppdrag.mapper.jsonjackson.JsonTopicArchiveMapper;
import no.systema.overview.ufortolledeoppdrag.mapper.jsonjackson.JsonTopicLoggingMapper;
import no.systema.overview.ufortolledeoppdrag.mapper.jsonjackson.JsonTopicTvinnLogMapper;
import no.systema.overview.ufortolledeoppdrag.mapper.jsonjackson.JsonRenderSpecificTopicMapper;
import no.systema.overview.ufortolledeoppdrag.mapper.jsonjackson.JsonTopicTvinnLogLargeTextMapper;






/**
 * @author oscardelatorre
 * @date Aug 27, 2013
 * 
 */
public class UoppdragServiceImpl implements UoppdragService  {
	
	/**
	 * 
	 * @utfPayload
	 * @return
	 * 
	 */
	public JsonTopicContainer getContainer (String utfPayload){
		JsonTopicContainer container = null;
		try{
			JsonTopicMapper mapper = new JsonTopicMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
	}
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * 
	 */
	public JsonTopicArchiveContainer getArchiveContainer (String utfPayload){
		JsonTopicArchiveContainer container = null;
		try{
			JsonTopicArchiveMapper mapper = new JsonTopicArchiveMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
	}
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * 
	 */
	public JsonTopicLoggingContainer getLoggingContainer (String utfPayload){
		JsonTopicLoggingContainer container = null;
		try{
			JsonTopicLoggingMapper mapper = new JsonTopicLoggingMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}
	
	/**
	 * @param utfPayload
	 * @return
	 * 
	 */
	public JsonRenderSpecificTopicContainer getRenderSpecificTopicContainer (String utfPayload){
		JsonRenderSpecificTopicContainer container = null;
		try{
			JsonRenderSpecificTopicMapper mapper = new JsonRenderSpecificTopicMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}
	
	/**
	 * 
	 * @param utfPayload
	 * @return 
	 * 
	 */
	public JsonTopicTvinnLogContainer getTvinnLogContainer (String utfPayload){
		JsonTopicTvinnLogContainer container = null;
		try{
			JsonTopicTvinnLogMapper mapper = new JsonTopicTvinnLogMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}
	
	/**
	 * @param utfPayload
	 * @return
	 * 
	 */
	public JsonTopicTvinnLogLargeTextContainer getTvinnLogLargeTextContainer (String utfPayload){
		JsonTopicTvinnLogLargeTextContainer container = null;
		try{
			JsonTopicTvinnLogLargeTextMapper mapper = new JsonTopicTvinnLogLargeTextMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
		
	}
	
	
}
