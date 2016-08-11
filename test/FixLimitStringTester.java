import java.text.SimpleDateFormat;
import java.util.*;
import java.sql.Timestamp;

import no.systema.z.main.maintenance.controller.avd.MainMaintenanceAvdFastDataSyfa28Controller;
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
		recordToValidate.setKovxxx1("BT");
		recordToValidate.setKovxxx3("");
		recordToValidate.setKovxxx4("");
		recordToValidate.setKovxxx5("CC");
		MainMaintenanceAvdFastDataSyfa28Controller controller = new MainMaintenanceAvdFastDataSyfa28Controller();
		controller.populateKovxxxField(recordToValidate);
		System.out.println(recordToValidate.getKovxxx());
	}
	
	
	
}
