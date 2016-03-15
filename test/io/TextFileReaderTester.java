/**
 * 
 */
package io;

import java.util.*;

import no.systema.espedsgadmin.db.FileDatabaseManager;
import no.systema.espedsgadmin.model.CustomerApplicationObject;

/**
 * @author oscardelatorre
 *
 */
public class TextFileReaderTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*String sourceFile = "/ownfiles/customers.txt";
		TextFileReaderService readerService = new TextFileReaderService();
		List<String> payload = readerService.getFileLines(sourceFile);
		for(String record : payload){
			System.out.println (record);
		}*/
		
		String customerFile = "/ownfiles/customersDb/customers.db";
		String appFile = "/ownfiles/customersDb/applicationModules.db";
		String custAppFile = "/ownfiles/customersDb/custApps.db";
		
		FileDatabaseManager dbMgr = new FileDatabaseManager(customerFile, appFile, custAppFile);
		List<CustomerApplicationObject> list = dbMgr.getCustAppMap();
		for(CustomerApplicationObject obj : list){
			System.out.print("Customer: " + obj.getName() + ":");
			List<String>appsList = obj.getApplicationList();
			for(String app : appsList){
				System.out.print(" -->APP:" + app);
			}
			System.out.println();
		}
		/*
		final String UTF_8 = "UTF-8";
		final String ROOT_PATH = "/ownfiles/customersDb/";
		//------------------
		//(1) CUSTOMERS DB
		//------------------
		String sourceFileCustomers =  ROOT_PATH + "customers.db";
		TextFileReaderService readerServiceCustomers = new TextFileReaderService();
		List<String> payloadCustomers = readerServiceCustomers.getFileLines(sourceFileCustomers, UTF_8);
		Map<String, String> customerMap = new HashMap<String, String>();
		for(String record : payloadCustomers){
			if(!record.contains("META")){
				String[] fields = record.split(";");
				//System.out.println ("CUST: " + record);
				if(fields!=null && fields.length>1){
					customerMap.put(fields[0], fields[1]);
				}
			}	
		}
		//--------
		//APP.DB
		//--------
		String sourceFileApps = ROOT_PATH + "applicationModules.db";
		TextFileReaderService readerServiceApps = new TextFileReaderService();
		List<String> payloadApps = readerServiceApps.getFileLines(sourceFileApps, UTF_8);
		Map<String, String> appMap = new HashMap<String, String>();
		for(String record : payloadApps){
			//System.out.println ("APPS: " + record);
			if(!record.contains("META")){
				String[] fields = record.split(";");
				//System.out.println ("CUST: " + record);
				if(fields!=null && fields.length>1){
					appMap.put(fields[0], fields[1]);
				}
			}	
		}
		//------------
		//CUST-APP DB
		//------------
		String sourceFileCustApps = ROOT_PATH + "custApps.db";
		TextFileReaderService readerServiceCustApps = new TextFileReaderService();
		List<String> payloadCustApps = readerServiceCustApps.getFileLines(sourceFileCustApps, UTF_8);
		Map<String, List> custAppMap = new HashMap<String, List>();
		for(String record : payloadCustApps){
			if(!record.contains("META")){
				String[] id = record.split("@");
				//System.out.println ("CUST: " + id[0]);
				String[] fields = { };
				if(id!=null && id.length>1){
					fields = id[1].split(";");
				}	
				
				List<String> list = null;
				if(fields!=null && fields.length>0){
					list = Arrays.asList(fields);
				}else{
					list = new ArrayList();
				}
				custAppMap.put(id[0], list );
				
				String customerName = (String)customerMap.get(id[0]);
				System.out.println ("CUSTOMER:" + customerName );
				for (String app : list){
					String appName = (String)appMap.get(app);
					System.out.println (" FIELD:" + appName);
				}
				System.out.println (" ");
			}
		}
		*/
	}
	
}
