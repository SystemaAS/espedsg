/**
 * 
 */
package no.systema.tror.service;

import no.systema.tror.model.jsonjackson.order.childwindow.JsonTrorCarrierContainer;
import no.systema.tror.model.jsonjackson.order.childwindow.JsonTrorPostalCodeContainer;
import no.systema.tror.model.jsonjackson.order.childwindow.JsonTrorTollstedContainer;
import no.systema.tror.model.jsonjackson.order.childwindow.JsonTrorLosseLasteStedContainer;



/**
 * 
 * @author oscardelatorre
 * @date Aug 31, 2017
 * 
 *
 */
public interface TrorMainOrderHeaderChildwindowService {
	
	public JsonTrorCarrierContainer getCarrierListContainer(String utfPayload);
	public JsonTrorPostalCodeContainer getPostalCodeListContainer(String utfPayload);
	public JsonTrorTollstedContainer getTollstedListContainer(String utfPayload);
	public JsonTrorLosseLasteStedContainer getLosseLasteStedContainer(String utfPayload);
	
}
