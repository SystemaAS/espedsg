/**
 * 
 */
package no.systema.main.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Utility class for number formats (including decimal formats)
 * 
 * @author oscardelatorre
 * @date Sep 17, 2013
 * 
 */
public class NumberFormatterLocaleAware {
	 
	 /**
	  * Returns a number as a String, formatted with European notation
	  * 
	  * @param sourceNumber
	  * @param numberOfDecimals
	  * @return
	  * 
	  */
	 public String getDoubleEuropeanFormat (Double sourceNumber, int numberOfDecimals){
		 String retval = "0";
		 try{
			 NumberFormat nf = NumberFormat.getInstance(Locale.GERMAN);
			 nf.setMaximumFractionDigits(numberOfDecimals);
			 nf.setMinimumFractionDigits(numberOfDecimals);
			 nf.setGroupingUsed(true); //takes the thousand separator into account. As in: 3.000.080,08
			 String strValue = nf.format(sourceNumber); 
			 
			 retval = strValue;
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 return retval;
		 
	 }
	 /**
	  * 
	  * @param sourceNumber
	  * @param numberOfDecimals
	  * @param thousandGrouping
	  * @return
	  */
	 public String getDoubleEuropeanFormat (Double sourceNumber, int numberOfDecimals, boolean thousandGrouping){
		 String retval = "0";
		 try{
			 NumberFormat nf = NumberFormat.getInstance(Locale.GERMAN);
			 nf.setMaximumFractionDigits(numberOfDecimals);
			 nf.setMinimumFractionDigits(numberOfDecimals);
			 nf.setGroupingUsed(thousandGrouping); //takes the thousand separator into account. As in: 3.000.080,08
			 String strValue = nf.format(sourceNumber); 
			 
			 retval = strValue;
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 return retval;
		 
	 }
	 /**
	  * 
	  * @param sourceNumber
	  * @param numberOfDecimals
	  * @param thousandGrouping
	  * @param locale
	  * @return
	  */
	 public String getString (Double sourceNumber, int numberOfDecimals, boolean thousandGrouping, String localeStr){
		 String retval = "0";
		 try{
			 Locale locale = new Locale(localeStr);
			 NumberFormat nf = NumberFormat.getInstance(locale);
			 nf.setMaximumFractionDigits(numberOfDecimals);
			 nf.setMinimumFractionDigits(numberOfDecimals);
			 nf.setGroupingUsed(thousandGrouping);
			 String strValue = nf.format(sourceNumber); 
			 
			 retval = strValue;
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 return retval;
		 
	 }
	 /**
	  * 
	  * @param sourceNumber
	  * @return
	  */
	 public Double getDouble(String sourceNumber){
		 Double retval = 0.00D;
		 try{
			 if(sourceNumber!=null){
				 retval = Double.parseDouble(sourceNumber.replace(",", "."));
			 }
		 }catch(Exception e){
			 //e.printStackTrace();
		 }
		 return retval;
	 }
	 
	 /**
	  * 
	  * @param sourceNumber
	  * @return
	  */
	 public Integer getInteger(String sourceNumber){
		 Integer retval = 0;
		 try{
			 if(sourceNumber!=null){
				 retval = Integer.parseInt(sourceNumber);
			 }
		 }catch(Exception e){
			 //nothing
		 }
		 return retval;
	 }
	 
	 
	 /**
	  * 
	  * @param sourceNumber
	  * @param numberOfDecimals
	  * @return
	  */
	 public Double getDouble(String sourceNumber, int numberOfDecimals){
		 Double retval = 0.00D;
		 Double tmp = 0.00D;
		 try{
			 if(sourceNumber!=null){
				 tmp = Double.parseDouble(sourceNumber.replace(",", "."));
				 String decimalMask = new String();
				 if(numberOfDecimals<=1){
					 decimalMask = "0";
				 }else{
					 for(int x=1;x<=numberOfDecimals;x++){
						 decimalMask += "0";
					 }
				 }
				 
				 DecimalFormat decim = new DecimalFormat("#." + decimalMask);
				 retval = Double.parseDouble(decim.format(tmp));
				 	
			 }
		 }catch(Exception e){
			 //nothing
		 }
		 return retval;
	 }
	 
}
