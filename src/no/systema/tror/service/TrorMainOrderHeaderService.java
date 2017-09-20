/**
 * 
 */
package no.systema.tror.service;

import no.systema.tror.model.jsonjackson.frisokvei.JsonTrorOrderHeaderFrisokveiContainer;
import no.systema.tror.model.jsonjackson.archive.JsonTrorOrderHeaderArchiveContainer;
import no.systema.tror.model.jsonjackson.logging.JsonTrorOrderHeaderLoggingContainer;
import no.systema.tror.model.jsonjackson.logging.JsonTrorOrderHeaderLoggingLargeTextContainer;
import no.systema.tror.model.jsonjackson.logging.JsonTrorOrderHeaderTrackAndTraceLoggingContainer;
import no.systema.tror.model.jsonjackson.budget.JsonTrorOrderHeaderBudgetContainer;



/**
 * 
 * @author oscardelatorre
 * @date Sep 15, 2017
 * 
 *
 */
public interface TrorMainOrderHeaderService {
	public JsonTrorOrderHeaderArchiveContainer getArchiveContainer(String utfPayload);
	public JsonTrorOrderHeaderLoggingContainer getLoggingContainer(String utfPayload);
	public JsonTrorOrderHeaderLoggingLargeTextContainer getLoggingLargeTextContainer(String utfPayload);
	public JsonTrorOrderHeaderTrackAndTraceLoggingContainer getTrackAndTraceLoggingContainer(String utfPayload);
	public JsonTrorOrderHeaderBudgetContainer getBudgetContainer(String utfPayload);
	public JsonTrorOrderHeaderFrisokveiContainer getOrderFrisokveiContainer(String utfPayload);
}
