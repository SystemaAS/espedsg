import java.text.DecimalFormat;
import no.systema.main.util.NumberFormatterLocaleAware;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
		    //System.out.println(numberFormatter.getDouble("5,668", 3));
		    Double x = 5.62454645;
		    Double tmp = numberFormatter.getDouble(x, 3);
		    System.out.println("tmp:" + tmp);
		    
		    
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	
}
