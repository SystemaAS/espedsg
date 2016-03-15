/**
 * 
 */
package no.systema.overview.ufortolledeoppdrag.util.manager;

import java.lang.reflect.Field;
import java.util.*;

import org.apache.log4j.Logger;

import no.systema.overview.ufortolledeoppdrag.controller.UoppdragController;
import no.systema.overview.ufortolledeoppdrag.filter.SearchFilterUoppdrag;
import no.systema.overview.ufortolledeoppdrag.filter.SearchFilterGateChart;
import no.systema.overview.ufortolledeoppdrag.model.jsonjackson.JsonTopicRecord;

/**
 * This class is the manager class in order to implement a search mechanism.
 * The search mechanism uses java reflection since we must match a given search filter with a record
 * 
 * With a database the search filter manager would not be necessary and instead the SQL-clause would be used to limit the dataset 
 * after a filter search condition
 * 
 * 
 * @author oscardelatorre
 * @date Aug 30, 2013
 * 
 */
public class SearchFilterMgr {
	private static final Logger logger = Logger.getLogger(SearchFilterMgr.class.getName());
	
	/**
	 * Returns a filtered list (if any)
	 * 
	 * @param searchFilter
	 * @param topicList
	 * @return
	 * 
	 */
	public Collection<JsonTopicRecord> filterUoppragDataset(SearchFilterUoppdrag searchFilterObj, Collection<JsonTopicRecord> topicList){
		SearchFilterUoppdrag searchFilter = null;
		//check which instance is calling the method...
		if(searchFilterObj instanceof SearchFilterUoppdrag){
			searchFilter = (SearchFilterUoppdrag)searchFilterObj;
		}else if (searchFilterObj instanceof SearchFilterGateChart){
			searchFilter = (SearchFilterGateChart)searchFilterObj;
		}
		
		Collection<JsonTopicRecord> outputList = new ArrayList<JsonTopicRecord>();
		try{
			Collection <JsonTopicRecord> filteredList = new ArrayList<JsonTopicRecord>();
			Set<Map.Entry<String, String>> setOfEntries = searchFilter.getPopulatedFields().entrySet();
			if(setOfEntries.size()>0){
				logger.info("BEFORE loop for filter!!!");
				
				jsonRecordMainLoop:
				for(JsonTopicRecord record : topicList){
					//logger.info("###Topic Values " + record.getValues());
					//check now all search fields towards total content in each record 
					boolean completeMatch = false;
					boolean antHfaktIsNull = false;
					boolean statusIsNull = false;
					//--------------------------------------------------------
					//special hack for flags=N and other special filter cases
					//--------------------------------------------------------
					if(record.getAntHfakt()==null || "".equals(record.getAntHfakt())){
						antHfaktIsNull = true;
					}
					if(record.getStatus()==null || "".equals(record.getStatus())){
						statusIsNull = true;
					}
					//fetch the list of populated searchFilter fields by java reflection
					searchFilterLoop:
					for(Map.Entry<String, String> entry : setOfEntries) {
							String searchFilterField = entry.getKey();
							String searchFilterValue = entry.getValue();
							if(searchFilterValue!=null && !"".equals(searchFilterValue)){
								//iterate through the json-record fields in order to match it with the searchFilter populated fields
								jsonRecordFieldLoop: 
								for(Field jsonRecordField : record.getFields()){
									jsonRecordField.setAccessible(true);
									//logger.info("jsonField:" + jsonRecordField.getName());
									//logger.info("jsonValue:" + (String)jsonRecordField.get(record));
									
									if(searchFilterField.equals(jsonRecordField.getName())){
										String jsonRecordFieldValue = (String)jsonRecordField.get(record);
										//logger.info("PP" + jsonRecordField.getName());
										
										if(jsonRecordFieldValue!=null && !"".equals(jsonRecordFieldValue)){
											//logger.info("FilterAY:" + searchFilterField + "XX" + searchFilterValue);
											//logger.info("Record:" + jsonRecordField.getName() + "XX" + jsonRecordFieldValue);
											if(jsonRecordFieldValue.toLowerCase().contains(searchFilterValue.toLowerCase())){
												completeMatch = true;
												//Debug
												logger.info("[DEBUG] MATCH <filter field>:" + searchFilterField + " with <record field>:" + jsonRecordField.getName());
												logger.info("[DEBUG] MATCH <filter value>:" + searchFilterValue + " with <record value>:" + jsonRecordFieldValue);
												break jsonRecordFieldLoop;
											}else{
												//it takes just one tiny "no match" to put the complete match as false. All values must match or no complete match will be assessed
												completeMatch = false;
												break searchFilterLoop;
											}
										}else{
											if(searchFilterField.equals("antHfaktFlag") && searchFilterValue.equals("N")){
												//logger.info("A");
												if(antHfaktIsNull){
													logger.info("[DEBUG] MATCH <antHfaktFlag = N>");
													completeMatch = true;
													antHfaktIsNull = false;
													break jsonRecordFieldLoop;
												}
											}else if(searchFilterField.equals("statusFlag") && searchFilterValue.equals("N")){
												if(statusIsNull){
													logger.info("[DEBUG] MATCH <statusFlag = N>");
													completeMatch = true;
													statusIsNull = false;
													break jsonRecordFieldLoop;
												}
											}else{
												completeMatch = false;
												break searchFilterLoop;
											}
										}
									}else{
										if(searchFilterField.equals("avdList") && !"".equals(searchFilterValue)){
											if("avd".equals(jsonRecordField.getName())){
												logger.info("[DEBUG] Comparing avdList...");
												String avd = (String)jsonRecordField.get(record);
												if(avd!=null && !"".equals(avd)){
													if (this.avdExistsInAvdList(searchFilter,avd)){
														logger.info("[DEBUG] MATCH <avdList = " + searchFilterValue + " avd:"+ avd + ">");
														completeMatch = true;
														break jsonRecordFieldLoop;
													}else{
														completeMatch = false;
														break searchFilterLoop;
													}
												}else{
													completeMatch = false;
													break searchFilterLoop;
												}
											}
										}
									}
									
								}
							}
					}
					//valid object (only with complete Match!)
					if(completeMatch){
						//Debug
						//logger.info("[INFO] TOTAL MATCH! with filter values");
						filteredList.add(record);
					}
				}
				//filtered list
				outputList = filteredList;
				
			}else{
				//in case of no filtered used
				outputList = topicList;
			}
		}catch (Exception e){
			e.printStackTrace();
			outputList = topicList;
			return outputList;
		}
		

		return outputList;
		
	}
		
	/**
	 * Compares avdList with avd to discover a MATCH 
	 * 	
	 * @param searchFilter
	 * @param avd
	 * @return
	 */
	private boolean avdExistsInAvdList(SearchFilterUoppdrag searchFilter, String avd){
		boolean retval = false;
		
		List<Integer> listOfAvdAsIntegers = searchFilter.getAvdListMembersAsIntegers();
		parentLoop:
		for(Integer record: listOfAvdAsIntegers){
			if(avd!=null && !"".equals(avd)){
				try{
					int avdInt = Integer.parseInt(avd);
					if(record==avdInt){
						logger.info("[DEBUG] MATCH! on avd vs avdList: [" + record + "]");
						retval = true;
						break parentLoop;
					}
				}catch(NumberFormatException e){
					//continue
				}
			}
		}
		return retval;
	}
	
	/**
	 * Filter implementation for the UoopdragGateController (parent chart filter)
	 * Returns a filtered list (if any)
	 * 
	 * @param searchFilter
	 * @param topicList
	 * @return
	 * 
	 * @deprecated This method has been replaced by  filterUoppragDataset since we use a inherited class in order to 
	 * use the same method for both kinds of SearchFilter instances
	 * Refer to SearchFilterGateChar...
	 * 
	 */
	
	public Collection<JsonTopicRecord> filterUoppragGateDataset(SearchFilterGateChart searchFilter, Collection<JsonTopicRecord> topicList){
		Collection<JsonTopicRecord> outputList = new ArrayList<JsonTopicRecord>();
		try{
			Collection <JsonTopicRecord> filteredList = new ArrayList<JsonTopicRecord>();
			Set<Map.Entry<String, String>> setOfEntries = searchFilter.getPopulatedFields().entrySet();
			if(setOfEntries.size()>0){
				logger.info("BERFORE loop for filter!!!");
				
				jsonRecordMainLoop:
				for(JsonTopicRecord record : topicList){
					//logger.info("###Topic Values " + record.getValues());
					//check now all search fields towards total content in each record 
					boolean completeMatch = false;
					boolean antHfaktIsNull = false;
					boolean statusIsNull = false;
					
					//---------------------------------
					//special hack for antHfaktFlag=N
					//---------------------------------
					if(record.getAntHfakt()==null || "".equals(record.getAntHfakt())){
						antHfaktIsNull = true;
					}
					if(record.getStatus()==null || "".equals(record.getStatus())){
						statusIsNull = true;
					}
					//fetch the list of populated searchFilter fields by java reflection
					searchFilterLoop:
					for(Map.Entry<String, String> entry : setOfEntries) {
							String searchFilterField = entry.getKey();
							String searchFilterValue = entry.getValue();
							if(searchFilterValue!=null && !"".equals(searchFilterValue)){
								//iterate through the json-record fields in order to match it with the searchFilter populated fields
								jsonRecordFieldLoop: 
								for(Field jsonRecordField : record.getFields()){
									jsonRecordField.setAccessible(true);
									//logger.info("jsonField:" + jsonRecordField.getName());
									//logger.info("jsonValue:" + (String)jsonRecordField.get(record));
									
									if(searchFilterField.equals(jsonRecordField.getName())){
										String jsonRecordFieldValue = (String)jsonRecordField.get(record);
										//logger.info("PP" + jsonRecordField.getName());
										
										if(jsonRecordFieldValue!=null && !"".equals(jsonRecordFieldValue)){
											//logger.info("FilterAY:" + searchFilterField + "XX" + searchFilterValue);
											//logger.info("Record:" + jsonRecordField.getName() + "XX" + jsonRecordFieldValue);
											if(jsonRecordFieldValue.toLowerCase().contains(searchFilterValue.toLowerCase())){
												completeMatch = true;
												//Debug
												logger.info("[DEBUG] MATCH <filter field>:" + searchFilterField + " with <record field>:" + jsonRecordField.getName());
												logger.info("[DEBUG] MATCH <filter value>:" + searchFilterValue + " with <record value>:" + jsonRecordFieldValue);
												break jsonRecordFieldLoop;
											}else{
												//it takes just one tiny "no match" to put the complete match as false. All values must match or no complete match will be assessed
												completeMatch = false;
												break searchFilterLoop;
											}
										}else{
											if(searchFilterField.equals("antHfaktFlag") && searchFilterValue.equals("N")){
												//logger.info("A");
												if(antHfaktIsNull){
													logger.info("[DEBUG] MATCH <antHfaktFlag = N>");
													completeMatch = true;
													antHfaktIsNull = false;
													break jsonRecordFieldLoop;
												}
											}else if(searchFilterField.equals("statusFlag") && searchFilterValue.equals("N")){
												if(statusIsNull){
													logger.info("[DEBUG] MATCH <statusFlag = N>");
													completeMatch = true;
													statusIsNull = false;
													break jsonRecordFieldLoop;
												}
											}else{
												completeMatch = false;
												break searchFilterLoop;
											}
										}
									}
									
								}
							}
					}
					//valid object (only with complete Match!)
					if(completeMatch){
						//Debug
						//logger.info("[INFO] TOTAL MATCH! with filter values");
						filteredList.add(record);
					}
				}
				//filtered list
				outputList = filteredList;
				
			}else{
				//in case of no filtered used
				outputList = topicList;
			}
		}catch (Exception e){
			e.printStackTrace();
			outputList = topicList;
			return outputList;
		}
		

		return outputList;
		
	}
	
}
