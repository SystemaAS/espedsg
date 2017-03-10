import java.text.SimpleDateFormat;
import java.util.*;
import java.sql.Timestamp;

import no.systema.main.util.NumberFormatterLocaleAware;
import no.systema.main.util.StringManager;

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
			StringManager mgr = new StringManager();
			String str = "888,89";
			String s = mgr.trailingStringWithNumericFiller(mgr.removeChar(str, ","), 7, "0");
			System.out.println(mgr.removeChar(s, ","));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	
}
