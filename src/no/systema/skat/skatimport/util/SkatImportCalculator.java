/**
 * 
 */
package no.systema.skat.skatimport.util;

import no.systema.skat.skatimport.model.jsonjackson.topic.items.JsonSkatImportSpecificTopicItemContainer;
import no.systema.skat.skatimport.model.jsonjackson.topic.items.JsonSkatImportSpecificTopicItemRecord;

import java.text.NumberFormat;
import java.math.BigDecimal;
import java.util.Locale;

import org.apache.log4j.Logger;
/**
 * @author oscardelatorre
 * @date Jan 29, 2014
 * 
 * 
 */
public class SkatImportCalculator {
	private static final Logger logger = Logger.getLogger(SkatImportCalculator.class.getName());
	
	/**
	 * 
	 * @param jsonSkatImportSpecificTopicItemContainer
	 * @return
	 */
	public Double getItemLinesTotalAmount(JsonSkatImportSpecificTopicItemContainer jsonSkatImportSpecificTopicItemContainer){
		Double retval = 0.00D;
		if(jsonSkatImportSpecificTopicItemContainer!=null){
			for (JsonSkatImportSpecificTopicItemRecord record : jsonSkatImportSpecificTopicItemContainer.getOrderList()){
				try{
					String rawValue =  record.getDkiv_42();
					if(rawValue==null || "".equals(rawValue)){
						rawValue = "0.00";
					}else{
						NumberFormat nf = NumberFormat.getInstance(Locale.GERMAN);
					    try {
					        Number parsed = nf.parse(rawValue);
					        BigDecimal bd = new BigDecimal(parsed.toString());
					        //logger.info(bd.toString());
					        retval += bd.doubleValue();
					        
					    } catch (Exception e) {
					        e.printStackTrace();
					    }
					}
					//logger.info("################### FINAL CONVERSION SKAT-IMPORT FABL(sum of dkiv_42) calculated: " + retval);
					
				}catch (Exception e){
					e.printStackTrace();
				}
			}
		}
		
		return retval;
		
	}
	/**
	 * 
	 * @param invoiceAmountParam
	 * @param calculatedItemLinesTotalAmount
	 * @return
	 */
	public Double getDiffBetweenCalculatedTotalAmountAndTotalAmount(String invoiceAmountParam, Double calculatedItemLinesTotalAmount){
		Double retval = 0.000D;
		Double tmp = 0.000D;
		try{
			String rawValue =  invoiceAmountParam;
			if(rawValue==null || "".equals(rawValue)){
				rawValue = "0.00";
			}else{
				//rawValue = rawValue.replace(",", ".");
				NumberFormat nf = NumberFormat.getInstance(Locale.GERMAN);
			    try {
			        Number parsed = nf.parse(rawValue);
			        BigDecimal bd = new BigDecimal(parsed.toString());
			        logger.info(bd.toString());
			        tmp = bd.doubleValue();

			    } catch (Exception e) {
			        e.printStackTrace();
			    }
			}
			
			Double invoiceAmount = tmp;
			retval = invoiceAmount - calculatedItemLinesTotalAmount;
			//logger.info("################### invoiceAmountParam: " + invoiceAmountParam);
			//logger.info("################### calculatedItemLinesTotalAmount: " + calculatedItemLinesTotalAmount);
			//logger.info("################### FINAL CONVERSION IMPORT FABL (diff): " + retval);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return retval;
	}
	
	/**
	 * 
	 * @param svih_vufr - frakt
	 * @param svih_vufo - försäkring
	 * @param svih_ouko - övriga kostnader
	 * @param svih_kara - kassarabatt
	 * @param svih_anra - annan rabatt
	 * @return
	 * 
	 */
	public boolean isValidVardeUppgifter(String svih_vufr, String svih_vufo, String svih_ouko, String svih_kara, String svih_anra ){
		boolean retval = false;
		
		Double frakt = 0.00D;
		Double forsakring = 0.00D;
		Double ovrKostnader = 0.00D;
		Double kassaRabatt = 0.00D;
		Double annanRabatt = 0.00D;
		
		NumberFormat nf = NumberFormat.getInstance(Locale.GERMAN);
		try {
			//Frakt
	        Number parsed = nf.parse("".equals(svih_vufr)?"0":svih_vufr);
	        BigDecimal bd = new BigDecimal(parsed.toString());
	        frakt = bd.doubleValue();
	        //Forsakring
	        parsed = nf.parse("".equals(svih_vufo)?"0":svih_vufo);
	        bd = new BigDecimal(parsed.toString());
	        forsakring = bd.doubleValue();
	        //Ovriga kostnader
	        parsed = nf.parse("".equals(svih_ouko)?"0":svih_ouko);
	        bd = new BigDecimal(parsed.toString());
	        ovrKostnader = bd.doubleValue();
	        //kassarabatt
	        parsed = nf.parse("".equals(svih_kara)?"0":svih_kara);
	        bd = new BigDecimal(parsed.toString());
	        kassaRabatt = bd.doubleValue();
	        //annanrabatt
	        parsed = nf.parse("".equals(svih_anra)?"0":svih_anra);
	        bd = new BigDecimal(parsed.toString());
	        annanRabatt = bd.doubleValue();
	        //(Frakt + Forsakring + Ovriga kostnader)-(kassarabatt + annanrabatt) must be >= 0
	        Double result = (frakt + forsakring + ovrKostnader) - (kassaRabatt + annanRabatt);
	        if(result>=0){
	        		retval = true;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		
		
		return retval;
	}
	
}
