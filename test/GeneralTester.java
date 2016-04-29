import java.text.SimpleDateFormat;
import java.util.*;
import java.sql.Timestamp;

import no.systema.main.util.NumberFormatterLocaleAware;

/**
 * @author oscardelatorre
 *
 */
public class GeneralTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try{
			String klbxxx = "M";
			if(klbxxx!=null && klbxxx.length()>=2){
				System.out.println(klbxxx.substring(1,2));
			}
			
			/*String value = "2910";
			System.out.println("day:" + value.substring(0,2));
			System.out.println("month:" + value.substring(2));
			*/
			
			/*
			String source = "12345678";
			Integer length = source.length();
			String tmp = source.substring(0,length-2);
			String target = tmp + "??";
			
			System.out.println(target);
			*/
			
			
			//Calendar. Date operations
			/*Calendar userCalendar = Calendar.getInstance();
			Calendar calendar = Calendar.getInstance();
			String mystring = "201303280723";
			Date userDate = new SimpleDateFormat("yyyyMMddHHmm").parse(mystring);
			userCalendar.setTime(userDate);
			Long diff = userCalendar.getTimeInMillis() - calendar.getTimeInMillis();
			System.out.println("Milliseconds diff: " + diff);
			*/
			/*
			String orderLineTotalsString = "@hent_2@hevkt_3"; 
			List<String> list = Arrays.asList(orderLineTotalsString.split("@"));
			for(String record: list){
				System.out.println(record);
			}*/
			
			/*
			double grossNetFactor = 0.9; //default;
			  String grossWeight = "0,291";
			  String netWeight = "";
			  try{
				  //Gross weight exists but not net weight
				  if(grossWeight!=null && !"".equals(grossWeight) && (netWeight==null || "".equals(netWeight))  ){
					  /*
					  if(headerRecord.getSefvk()!=null && !"".equals(headerRecord.getSefvk())){
						  String tmp = headerRecord.getSefvk().replace("," , ".");
						  grossNetFactor = Double.parseDouble(tmp);
					  }
					  //operation
					  grossWeight = grossWeight.replace("," , ".");
					  double grossWeightDbl = Double.parseDouble(grossWeight);
					  
					  //double netWeightDbl = Math.round(grossWeightDbl * grossNetFactor);
					  double netWeightDbl = grossWeightDbl * grossNetFactor;
					  NumberFormatterLocaleAware formatter = new NumberFormatterLocaleAware();
					  netWeight = String.valueOf(formatter.getDoubleEuropeanFormat(netWeightDbl, 3, false));
					  //final result
					  System.out.println(netWeight);
				  }
				  
			  }catch (Exception e){
				  System.out.println("ERROR");
			  }
			*/
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	
}
