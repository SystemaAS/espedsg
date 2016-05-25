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
			String SPACE = "Z";
			int FTX_LIMIT = 50;
			StringBuffer str = new StringBuffer("TARZAN in the jungle");
			int len = str.length();
			for (int x=len+1;x<=FTX_LIMIT;x++){
				str.append(SPACE);
			}
			System.out.println(str);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	
}
