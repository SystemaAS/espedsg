import java.text.SimpleDateFormat;
import java.util.Date;

import no.systema.main.util.DateTimeManager;
import no.systema.main.validator.DateValidator;

import no.systema.tvinn.sad.util.TvinnSadDateFormatter;

public class DateTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		String dateValue = "201412121001";
		boolean isValid = new DateValidator().validateDateIso203_YYYYMMDDhhmm(dateValue);
		if(isValid){ System.out.println ("Valid");}
		*/
		
		/*TvinnSadDateFormatter formatter = new TvinnSadDateFormatter();
		System.out.println(formatter.convertToDate_ISO("010114"));
		System.out.println(formatter.convertToDate_NO("20141231"));
		*/
		
		/*
		DateTimeManager mgr = new DateTimeManager();
		String tmp = mgr.getDateFormatted_ISO("20148222", "yyyyMMdd");
		System.out.println(tmp);
		*/
		
		
		DateTimeManager mgr = new DateTimeManager();
		String userDate = "20151128";
		boolean isValid = mgr.currentDayBeforeUserDate(userDate, "yyyyMMdd");
		if(isValid){
			System.out.println("Valid old file");
		}else{
			System.out.println("Valid new file");
		}
		/*
		try{
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
			Date userDate = formatter.parse(userValue);
			Date today = formatter.parse(mgr.getCurrentDate_ISO());
			if(today.before(userDate)){
				System.out.println("Valid old file");
			}else{
				System.out.println("Valid new file");
			}
		}catch(Exception e){
			e.toString();
		}*/
		
		
	}
	
	
}
