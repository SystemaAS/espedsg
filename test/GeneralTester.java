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
			/*StringManager mgr = new StringManager();
			String str = "888,89";
			String s = mgr.trailingStringWithNumericFiller(mgr.removeChar(str, ","), 7, "0");
			System.out.println(mgr.removeChar(s, ","));*/
			
			String tmp = "http://localhost:8080";
			int m = tmp.lastIndexOf("/");
			System.out.println(tmp.substring(m + 1));
			
			String x = tmp.replace("http://", "");
			int i = x.indexOf(":");
			x = x.substring(0, i);
			
			System.out.println(x);
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	
}
