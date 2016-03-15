import java.util.*;

import no.systema.tds.tdsexport.util.RpgReturnResponseHandler;

/**
 * @author oscardelatorre
 *
 */
public class ReturnCodeTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ReturnCodeTester tester = new ReturnCodeTester();
		tester.run();

	}
	/**
	 * 
	 */
	private void run(){
		String rpgRawReturnPayload = "{\"user\" : , avd : , \"opd\" : , errMsg : , oneorder : [ ] } ";
		RpgReturnResponseHandler errorHandler = new RpgReturnResponseHandler();
		errorHandler.evaluateRpgResponseOnTopicUpdate(rpgRawReturnPayload);
		System.out.println(errorHandler.getErrorMessage());
	}
}
