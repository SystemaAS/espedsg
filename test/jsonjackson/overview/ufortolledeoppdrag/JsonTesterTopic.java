/**
 * 
 */
package jsonjackson.overview.ufortolledeoppdrag;

import java.io.*;   
import java.util.*;

//
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.service.UrlCgiProxyServiceImpl;
import no.systema.main.util.io.TextFileReaderService;

import no.systema.overview.ufortolledeoppdrag.model.jsonjackson.JsonTopicContainer;
import no.systema.overview.ufortolledeoppdrag.model.jsonjackson.JsonTopicRecord;
import no.systema.overview.ufortolledeoppdrag.model.jsonjackson.JsonTopicRecordDiagramChart;

import no.systema.overview.ufortolledeoppdrag.service.UoppdragService;
import no.systema.overview.ufortolledeoppdrag.service.UoppdragServiceImpl;

import no.systema.overview.ufortolledeoppdrag.util.jfreechart.GeneralDistributionChart;
import no.systema.overview.ufortolledeoppdrag.util.jfreechart.manager.*;


import no.systema.overview.ufortolledeoppdrag.url.store.UrlDataStore;

/**
 * 
 * @author oscardelatorre
 *
 */
public class JsonTesterTopic{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JsonTesterTopic engine = new JsonTesterTopic();
		engine.run();
		
		
	}

	/**
	 * 
	 */
	private void run (){
		try{
			UoppdragService uoppdragService = new UoppdragServiceImpl();
			/*
			String urlStr = UrlDataStore.OVERVIEW_UFORTOLLEDE_OPPDRAG_MAINLIST_URL;
			String urlParams = "user=OSCAR";
			UrlCgiProxyService urlCgiProxyService = new UrlCgiProxyServiceImpl();
			String jsonPayload = urlCgiProxyService.getJsonContent(urlStr, urlParams);
			*/
			//String sourceFile = "/ownfiles/uopptests/tollpostJSON.txt";
			String sourceFile = "/ownfiles/uopptests/oscarJSON.txt";
			//String sourceFile = "/ownfiles/uopptests/systemaJSON.txt";
			
			
			TextFileReaderService readerService = new TextFileReaderService();
			List<String> payload = readerService.getFileLines(sourceFile);
			String jsonPayload = null;
			int counter = 1;
			for(String record : payload){
				if(counter==1){
					jsonPayload = record;
				}else{
					jsonPayload += record;
				}
			}
			JsonTopicContainer container = uoppdragService.getContainer(jsonPayload);
			
			//list of objects (children of the Container)
			Collection<JsonTopicRecord> ufortList = container.getUfortList();
			Collection<JsonTopicRecordDiagramChart> chartListRaw = container.getChartList();
			//fill chart with categories (since JSON does not have this required field)
			int categoryId = 0;
			Collection<JsonTopicRecordDiagramChart> chartList = new ArrayList<JsonTopicRecordDiagramChart>();
			for (JsonTopicRecordDiagramChart record : chartListRaw){
				record.setCategoryId(categoryId);
				chartList.add(record);
				categoryId++;
			}
			//test for std implementation
			//chartList = this.getStdImplementation(uoppdragService);
			//container.setChartList(chartList);
			
			//init chart
			GeneralDistributionChart jfreeChart = new GeneralDistributionChart();
			jfreeChart.setGraphFileName("/ownfiles");
			IJFreeChartDimension jfreeChartDimensionMgr = new JFreeChartDynamicBarDimensionMgr();
			//IJFreeChartDimension jfreeChartDimensionMgr = new JFreeChartStandardBarDimensionMgr();
			
			if (jfreeChartDimensionMgr instanceof JFreeChartDynamicBarDimensionMgr){
				jfreeChartDimensionMgr.buildChart(ufortList, chartList);
				jfreeChart.produceBarChartJPEG_DynamicDays(jfreeChartDimensionMgr);
			}
			
			//test sorting of list - with BeanComparator
			/*
			List<JsonTopicRecord> newList = (List)list;
			Collections.sort(newList, new BeanComparator("dager"));
			Collections.reverseOrder()(newList, new BeanComparator("dager"));
			for(JsonTopicRecord record : newList){
				System.out.println("Dager: " + record.getDager());
			}*/
			
			//test sorting of list - with Comparable
			/*List<JsonTopicRecord> newList = (List)list;
			Collections.sort(newList);
			System.out.println("Dager ASC");
			for (JsonTopicRecord record : newList){
				System.out.print(record.getDager() + " ");
			}
			System.out.println("");
			System.out.println("Dager DESC");
			Collections.sort(newList, Collections.reverseOrder());
			for (JsonTopicRecord record : newList){
				System.out.print(record.getDager() + " ");
			}
			//Oppdrag
			System.out.println("");
			System.out.println("Oppdrag ASC");
			Collections.sort(newList, new JsonTopicRecord.OrderByOppdrag() );
			for (JsonTopicRecord record : newList){
				System.out.print(record.getOppdrag() + " ");
			}
			System.out.println("");
			System.out.println("Oppdrag DESC");
			Collections.sort(newList, Collections.reverseOrder(new JsonTopicRecord.OrderByOppdrag()) );
			for (JsonTopicRecord record : newList){
				System.out.print(record.getOppdrag() + " ");
			}
			//Sign
			System.out.println("");
			System.out.println("Sign ASC");
			Collections.sort(newList, new JsonTopicRecord.OrderBySign() );
			for (JsonTopicRecord record : newList){
				System.out.print(record.getSign() + " ");
			}
			System.out.println("");
			System.out.println("Sign DESC");
			Collections.sort(newList, Collections.reverseOrder(new JsonTopicRecord.OrderBySign()) );
			for (JsonTopicRecord record : newList){
				System.out.print(record.getSign() + " ");
			}
			*/
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param uoppdragService
	 * @return
	 */
	private Collection<JsonTopicRecordDiagramChart> getStdImplementation(UoppdragService uoppdragService){
		String jsonPayload  = null;
		TextFileReaderService fileService = new TextFileReaderService();
		List<String> list = fileService.getFileLines("/ownfiles/jsonStdUoppdragImplementationString.txt", "UTF-8");
		for(String record : list){
			System.out.println(record);
			jsonPayload = record;
			
		}
		JsonTopicContainer container = uoppdragService.getContainer(jsonPayload);
		
		//list of objects (children of the Container)
		Collection<JsonTopicRecord> ufortList = container.getUfortList();
		Collection<JsonTopicRecordDiagramChart> chartList = container.getChartList();
		
		for(JsonTopicRecordDiagramChart record : chartList){
			//System.out.println(record.isSingleValue());
		}
		
		
		return chartList;
	}
	
	 

}
