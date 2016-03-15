import java.text.DecimalFormat;
import no.systema.main.util.NumberFormatterLocaleAware;
/**
 * @author oscardelatorre
 *
 */
public class DecimalNumberTester {
	
	public static void main(String[] args) {
		try{
			/*
			Double price = 0.3888;
		    DecimalFormat decim = new DecimalFormat("#.00");
		    Double price2 = Double.parseDouble(decim.format(price));
		    System.out.println(price2);
			*/
		    NumberFormatterLocaleAware numberFormatter = new NumberFormatterLocaleAware();
		    System.out.println(numberFormatter.getDouble("5,668", 3));

			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	
}
