/**
 * 
 */
package no.systema.overview.ufortolledeoppdrag.model;

import java.util.*;

import no.systema.overview.ufortolledeoppdrag.model.jsonjackson.JsonTopicRecordDiagramChart;

/**
 * This class is populated at runtime as the chartList is parsed
 * 
 * Each user profile could have different color chart preferences but ALL color charts may have 4 categories
 * (1=way ahead, 2=ok, 3=watch list and 4=emergency)
 * 
 * Each can be represented with different colors. Usually with 1=grey, 2=green, 3=yellow, 4=red which we set as default BUT could be overrided 
 * 
 * 
 * @author oscardelatorre
 * @date Sep 17, 2013
 * 
 */

public class DiagramColorChartMetaData {
	/* TO IMPLEMENT!!!  we need some kind of priority (0,1,2,3) per category in the JSON string. E.g 0=yellow, 1= yellow, 2= yellow, 3=red
	 * in order to be able to handle all combinations of colors and priorities
	 * 
	 * If the implementation is done we must CHANGE the green.yellow.red combination that we use in the jsp:s (c:when / c:otherwise)
	
	
	private final String DEFAULT_COLOR_WAY_AHEAD = "grey";
	private final String DEFAULT_COLOR_GREEN = "green";
	private final String DEFAULT_COLOR_YELLOW = "yellow";
	private final String DEFAULT_COLOR_RED = "red";
	
	
	private String colorWayAhead = DEFAULT_COLOR_WAY_AHEAD;
	public void setColorWayAhead(String value) {  this.colorWayAhead = value; }
	public String getColorWayAhead() { return this.colorWayAhead.trim().toLowerCase();}
	
	private String colorOk = DEFAULT_COLOR_GREEN;
	public void setColorOk(String value) {  this.colorOk = value; }
	public String getColorOk() { return this.colorOk.trim().toLowerCase();}
	
	private String colorWatch = DEFAULT_COLOR_YELLOW;
	public void setColorWatch(String value) {  this.colorWatch = value; }
	public String getColorWatch() { return this.colorWatch.trim().toLowerCase();}
	
	private String colorEmergency = DEFAULT_COLOR_RED;
	public void setColorEmergency(String value) {  this.colorEmergency = value; }
	public String getColorEmergency() { return this.colorEmergency.trim().toLowerCase();}
	*/
	
	/*
	inputFormSubmitUoppOk
	inputFormSubmitUoppWatch
	inputFormSubmitUoppEmergency
	inputFormSubmitUoppWayAhead
	*/
	
	
	
}
