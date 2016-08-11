import java.text.SimpleDateFormat;
import java.util.*;
import java.sql.Timestamp;

import no.systema.main.util.NumberFormatterLocaleAware;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainKodtvKodtwRecord;

/**
 * @author oscardelatorre
 *
 */
public class FixLimitStringTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		FixLimitStringTester tester = new FixLimitStringTester();
		JsonMaintMainKodtvKodtwRecord recordToValidate = new JsonMaintMainKodtvKodtwRecord();
		recordToValidate.setKovxxx0("A");
		recordToValidate.setKovxxx1("B");
		recordToValidate.setKovxxx3("N");
		recordToValidate.setKovxxx4("N");
		recordToValidate.setKovxxx5("CC");
		
		tester.populateKovxxxField(recordToValidate);
		System.out.println(recordToValidate.getKovxxx());
	}
	
	/**
	 * 
	 * @param recordToValidate
	 */
	private void populateKovxxxField(JsonMaintMainKodtvKodtwRecord recordToValidate){
		String SPACE = " ";
		//(0, 1-char)
		if(recordToValidate.getKovxxx0()!=null && !"".equals(recordToValidate.getKovxxx0())){
			String tmp = recordToValidate.getKovxxx0();
			recordToValidate.setKovxxx(tmp);
		}else{
			recordToValidate.setKovxxx(SPACE);
		}
		//(1, 2-chars)
		if(recordToValidate.getKovxxx1()!=null && !"".equals(recordToValidate.getKovxxx1())){
			String tmp = recordToValidate.getKovxxx() + recordToValidate.getKovxxx1();
			recordToValidate.setKovxxx(this.paddingString(tmp, 3));
		}else{
			recordToValidate.setKovxxx(recordToValidate.getKovxxx() + SPACE + SPACE);
		}
		//(3, 1-char)
		if(recordToValidate.getKovxxx3()!=null && !"".equals(recordToValidate.getKovxxx3())){
			String tmp = recordToValidate.getKovxxx() + recordToValidate.getKovxxx3();
			recordToValidate.setKovxxx(this.paddingString(tmp, 4));
		}else{
			recordToValidate.setKovxxx(recordToValidate.getKovxxx() + SPACE);
		}
		//(4, 1-char)
		if(recordToValidate.getKovxxx4()!=null && !"".equals(recordToValidate.getKovxxx4())){
			String tmp = recordToValidate.getKovxxx() + recordToValidate.getKovxxx4();
			recordToValidate.setKovxxx(this.paddingString(tmp, 5));
		}else{
			recordToValidate.setKovxxx(recordToValidate.getKovxxx() + SPACE);
		}
		//(5, 2-char)
		if(recordToValidate.getKovxxx5()!=null && !"".equals(recordToValidate.getKovxxx5())){
			String tmp = recordToValidate.getKovxxx() + recordToValidate.getKovxxx5();
			recordToValidate.setKovxxx(this.paddingString(tmp, 7));
		}else{
			recordToValidate.setKovxxx(recordToValidate.getKovxxx() + SPACE + SPACE);
		}
		
	}
	/**
	 * fills the string with spaces when applicables
     *	
	 * @param value
	 * @param limit
	 * @return
	 */
	private String paddingString(String value, int limit){
		String SPACE = " ";
		int FTX_LIMIT = limit;
		StringBuffer str = new StringBuffer(value);
		int len = str.length();
		for (int x=len+1;x<=FTX_LIMIT;x++){
			str.append(SPACE);
		}
		return str.toString();
	}
	
	
	
}
