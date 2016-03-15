/**
 * 
 */
package no.systema.overview.ufortolledeoppdrag.service;

import no.systema.overview.ufortolledeoppdrag.model.jsonjackson.JsonTopicContainer;
import no.systema.overview.ufortolledeoppdrag.model.jsonjackson.archive.JsonTopicArchiveContainer;
import no.systema.overview.ufortolledeoppdrag.model.jsonjackson.logging.JsonTopicLoggingContainer;
import no.systema.overview.ufortolledeoppdrag.model.jsonjackson.JsonRenderSpecificTopicContainer;
import no.systema.overview.ufortolledeoppdrag.model.jsonjackson.tvinnlog.JsonTopicTvinnLogContainer;
import no.systema.overview.ufortolledeoppdrag.model.jsonjackson.tvinnlog.JsonTopicTvinnLogLargeTextContainer;


/**
 * 
 * @author oscardelatorre
 * @date Sep 22, 2013
 * 
 */
public interface UoppdragService {
	public JsonTopicContainer getContainer (String utfPayload);
	public JsonTopicArchiveContainer getArchiveContainer (String utfPayload);
	public JsonTopicLoggingContainer getLoggingContainer (String utfPayload);
	public JsonRenderSpecificTopicContainer getRenderSpecificTopicContainer (String utfPayload);
	public JsonTopicTvinnLogContainer getTvinnLogContainer (String utfPayload);
	public JsonTopicTvinnLogLargeTextContainer getTvinnLogLargeTextContainer (String utfPayload);
	
	
}
