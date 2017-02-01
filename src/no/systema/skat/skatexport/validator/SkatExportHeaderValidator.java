package no.systema.skat.skatexport.validator;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.*;

import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.main.util.DateTimeManager;
import no.systema.main.validator.DateValidator;
import no.systema.skat.skatexport.model.jsonjackson.topic.JsonSkatExportSpecificTopicRecord;
import no.systema.skat.skatexport.model.jsonjackson.topic.items.JsonSkatExportSpecificTopicItemContainer;
import no.systema.skat.skatexport.model.jsonjackson.topic.items.JsonSkatExportSpecificTopicItemRecord;




/**
 * 
 * @author oscardelatorre
 * @date Jan 27, 2014
 *
 */
public class SkatExportHeaderValidator implements Validator {
	
	final String ANG_ART_20_ALU = "20";
	final String ANG_ART_21_FOU = "21";
	final String ANG_ART_22_SUP = "22";
	final String ANG_ART_23_SUPH = "23";
	final String ANG_ART_24_FUAA = "24";
	final String ANG_ART_25_FUAF = "25";
	final String ANG_ART_26_PRO = "26";
	final String ANG_ART_27_PROM = "27";
	final String ANG_ART_28_EFU = "28";
	final String ANG_ART_30_EXS = "30";
	final String ANG_ART_31_YM_FUAGo = "31";
	final String ANG_ART_32_YM_FUAAo = "32";
	final String ANG_ART_50_IE507 = "50";
	
	
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonSkatExportSpecificTopicRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonSkatExportSpecificTopicRecord record = (JsonSkatExportSpecificTopicRecord)obj;
		
		//Check for Mandatory fields first
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_syav", "systema.skat.export.header.error.null.dkeh_syav");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_sysg", "systema.skat.export.header.error.null.dkeh_sysg");
		//Special case to ensure that dkeh_aart and dkeh_r011 always exist unless IE507
		if(!this.ANG_ART_50_IE507.equals(record.getDkeh_aart()) ){
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_r011", "systema.skat.export.header.error.null.dkeh_r011");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_aart", "systema.skat.export.header.error.null.dkeh_aart");
		}		
		
		/*
		//Check for Mandatory fields first
		*/
		
		if( this.ANG_ART_20_ALU.equals(record.getDkeh_aart()) ){
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_r011", "systema.skat.export.header.error.null.dkeh_r011");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_r012", "systema.skat.export.header.error.null.dkeh_r012");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_a1", "systema.skat.export.header.error.null.dkeh_a1");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_17a", "systema.skat.export.header.error.null.dkeh_17a");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_29", "systema.skat.export.header.error.null.dkeh_29");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_dtm1", "systema.skat.export.header.error.null.dkeh_dtm1");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_25", "systema.skat.export.header.error.null.dkeh_25");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_26", "systema.skat.export.header.error.null.dkeh_26");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_08b", "systema.skat.export.header.error.null.dkeh_08b");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_08c", "systema.skat.export.header.error.null.dkeh_08c");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_08d", "systema.skat.export.header.error.null.dkeh_08d");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_08e", "systema.skat.export.header.error.null.dkeh_08e");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_08f", "systema.skat.export.header.error.null.dkeh_08f");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_s29", "systema.skat.export.header.error.null.dkeh_s29");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_06", "systema.skat.export.header.error.null.dkeh_06");
			//Fakt.belopp and related fields
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_221", "systema.skat.export.header.error.null.dkeh_221");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_221b", "systema.skat.export.header.error.null.dkeh_221b");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_222", "systema.skat.export.header.error.null.dkeh_222");
			
			
			
		}else if( this.ANG_ART_21_FOU.equals(record.getDkeh_aart()) ){
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_r011", "systema.skat.export.header.error.null.dkeh_r011");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_r012", "systema.skat.export.header.error.null.dkeh_r012");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_a1", "systema.skat.export.header.error.null.dkeh_a1");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_17a", "systema.skat.export.header.error.null.dkeh_17a");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_29", "systema.skat.export.header.error.null.dkeh_29");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_dtm1", "systema.skat.export.header.error.null.dkeh_dtm1");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_25", "systema.skat.export.header.error.null.dkeh_25");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_26", "systema.skat.export.header.error.null.dkeh_26");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_02a", "systema.skat.export.header.error.null.dkeh_02a");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_08b", "systema.skat.export.header.error.null.dkeh_08b");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_08c", "systema.skat.export.header.error.null.dkeh_08c");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_08d", "systema.skat.export.header.error.null.dkeh_08d");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_08e", "systema.skat.export.header.error.null.dkeh_08e");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_08f", "systema.skat.export.header.error.null.dkeh_08f");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_s29", "systema.skat.export.header.error.null.dkeh_s29");
			//Fakt.belopp and related fields
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_221", "systema.skat.export.header.error.null.dkeh_221");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_221b", "systema.skat.export.header.error.null.dkeh_221b");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_222", "systema.skat.export.header.error.null.dkeh_222");
			
			
		}else if( this.ANG_ART_22_SUP.equals(record.getDkeh_aart()) ){
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_r011", "systema.skat.export.header.error.null.dkeh_r011");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_r012", "systema.skat.export.header.error.null.dkeh_r012");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_a2", "systema.skat.export.header.error.null.dkeh_a2");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_26", "systema.skat.export.header.error.null.dkeh_26");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_06", "systema.skat.export.header.error.null.dkeh_06");
			//Fakt.belopp and related fields
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_221", "systema.skat.export.header.error.null.dkeh_221");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_221b", "systema.skat.export.header.error.null.dkeh_221b");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_222", "systema.skat.export.header.error.null.dkeh_222");
			
		}else if( this.ANG_ART_23_SUPH.equals(record.getDkeh_aart()) ){
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_r011", "systema.skat.export.header.error.null.dkeh_r011");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_r012", "systema.skat.export.header.error.null.dkeh_r012");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_a2", "systema.skat.export.header.error.null.dkeh_a2");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_26", "systema.skat.export.header.error.null.dkeh_26");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_06", "systema.skat.export.header.error.null.dkeh_06");
			
		}else if(this.ANG_ART_24_FUAA.equals(record.getDkeh_aart())){
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_r011", "systema.skat.export.header.error.null.dkeh_r011");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_r012", "systema.skat.export.header.error.null.dkeh_r012");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_a1", "systema.skat.export.header.error.null.dkeh_a1");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_17a", "systema.skat.export.header.error.null.dkeh_17a");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_29", "systema.skat.export.header.error.null.dkeh_29");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_dtm1", "systema.skat.export.header.error.null.dkeh_dtm1");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_25", "systema.skat.export.header.error.null.dkeh_25");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_26", "systema.skat.export.header.error.null.dkeh_26");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_08b", "systema.skat.export.header.error.null.dkeh_08b");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_08c", "systema.skat.export.header.error.null.dkeh_08c");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_08d", "systema.skat.export.header.error.null.dkeh_08d");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_08e", "systema.skat.export.header.error.null.dkeh_08e");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_08f", "systema.skat.export.header.error.null.dkeh_08f");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_s29", "systema.skat.export.header.error.null.dkeh_s29");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_06", "systema.skat.export.header.error.null.dkeh_06");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_21", "systema.skat.export.header.error.null.dkeh_21");
			
			//Fakt.belopp and related fields
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_221", "systema.skat.export.header.error.null.dkeh_221");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_221b", "systema.skat.export.header.error.null.dkeh_221b");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_222", "systema.skat.export.header.error.null.dkeh_222");
			
		}else if(this.ANG_ART_25_FUAF.equals(record.getDkeh_aart())){
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_r011", "systema.skat.export.header.error.null.dkeh_r011");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_r012", "systema.skat.export.header.error.null.dkeh_r012");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_a1", "systema.skat.export.header.error.null.dkeh_a1");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_17a", "systema.skat.export.header.error.null.dkeh_17a");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_29", "systema.skat.export.header.error.null.dkeh_29");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_dtm1", "systema.skat.export.header.error.null.dkeh_dtm1");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_25", "systema.skat.export.header.error.null.dkeh_25");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_26", "systema.skat.export.header.error.null.dkeh_26");
			//Motdager
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_08b", "systema.skat.export.header.error.null.dkeh_08b");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_08c", "systema.skat.export.header.error.null.dkeh_08c");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_08d", "systema.skat.export.header.error.null.dkeh_08d");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_08e", "systema.skat.export.header.error.null.dkeh_08e");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_08f", "systema.skat.export.header.error.null.dkeh_08f");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_s29", "systema.skat.export.header.error.null.dkeh_s29");
			//Fakt.belopp and related fields
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_221", "systema.skat.export.header.error.null.dkeh_221");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_221b", "systema.skat.export.header.error.null.dkeh_221b");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_222", "systema.skat.export.header.error.null.dkeh_222");
			
		}else if(this.ANG_ART_28_EFU.equals(record.getDkeh_aart())){
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_r011", "systema.skat.export.header.error.null.dkeh_r011");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_r012", "systema.skat.export.header.error.null.dkeh_r012");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_a1", "systema.skat.export.header.error.null.dkeh_a1");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_17a", "systema.skat.export.header.error.null.dkeh_17a");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_29", "systema.skat.export.header.error.null.dkeh_29");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_dtm2", "systema.skat.export.header.error.null.dkeh_dtm2");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_25", "systema.skat.export.header.error.null.dkeh_25");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_26", "systema.skat.export.header.error.null.dkeh_26");
			//Motdager
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_08b", "systema.skat.export.header.error.null.dkeh_08b");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_08c", "systema.skat.export.header.error.null.dkeh_08c");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_08d", "systema.skat.export.header.error.null.dkeh_08d");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_08e", "systema.skat.export.header.error.null.dkeh_08e");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_08f", "systema.skat.export.header.error.null.dkeh_08f");
			//
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_06", "systema.skat.export.header.error.null.dkeh_06");
			//Fakt.belopp and related fields
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_221", "systema.skat.export.header.error.null.dkeh_221");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_221b", "systema.skat.export.header.error.null.dkeh_221b");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_222", "systema.skat.export.header.error.null.dkeh_222");
			
		}else if(this.ANG_ART_26_PRO.equals(record.getDkeh_aart())){
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_r011", "systema.skat.export.header.error.null.dkeh_r011");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_r012", "systema.skat.export.header.error.null.dkeh_r012");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_a1", "systema.skat.export.header.error.null.dkeh_a1");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_dtm1", "systema.skat.export.header.error.null.dkeh_dtm1");
			//Afsender
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_02a", "systema.skat.export.header.error.null.dkeh_02a");
			//Motdager
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_08b", "systema.skat.export.header.error.null.dkeh_08b");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_08c", "systema.skat.export.header.error.null.dkeh_08c");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_08d", "systema.skat.export.header.error.null.dkeh_08d");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_08e", "systema.skat.export.header.error.null.dkeh_08e");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_08f", "systema.skat.export.header.error.null.dkeh_08f");
			
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_17a", "systema.skat.export.header.error.null.dkeh_17a");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_ind", "systema.skat.export.header.error.null.dkeh_ind");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_s29", "systema.skat.export.header.error.null.dkeh_s29");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_29", "systema.skat.export.header.error.null.dkeh_29");
			//Proviant
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_ftx1", "systema.skat.export.header.error.null.dkeh_ftx1");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_ftx2", "systema.skat.export.header.error.null.dkeh_ftx2");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_ftx3", "systema.skat.export.header.error.null.dkeh_ftx3");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_ftx4", "systema.skat.export.header.error.null.dkeh_ftx4");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_ftx5", "systema.skat.export.header.error.null.dkeh_ftx5");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_ftxj", "systema.skat.export.header.error.null.dkeh_ftxj");
			//Fakt.belopp and related fields
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_221", "systema.skat.export.header.error.null.dkeh_221");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_221b", "systema.skat.export.header.error.null.dkeh_221b");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_222", "systema.skat.export.header.error.null.dkeh_222");
			
			//Transport
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_181", "systema.skat.export.header.error.null.dkeh_181");
			//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_25", "systema.skat.export.header.error.null.dkeh_25");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_zkp", "systema.skat.export.header.error.null.dkeh_zkp");
			//
			
			
		}else if(this.ANG_ART_27_PROM.equals(record.getDkeh_aart())){
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_r011", "systema.skat.export.header.error.null.dkeh_r011");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_r012", "systema.skat.export.header.error.null.dkeh_r012");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_a1", "systema.skat.export.header.error.null.dkeh_a1");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_dtm2", "systema.skat.export.header.error.null.dkeh_dtm2");
			//Afsender
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_02a", "systema.skat.export.header.error.null.dkeh_02a");
			//Motdager
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_08b", "systema.skat.export.header.error.null.dkeh_08b");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_08c", "systema.skat.export.header.error.null.dkeh_08c");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_08d", "systema.skat.export.header.error.null.dkeh_08d");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_08e", "systema.skat.export.header.error.null.dkeh_08e");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_08f", "systema.skat.export.header.error.null.dkeh_08f");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_17a", "systema.skat.export.header.error.null.dkeh_17a");
			//Transport
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_181", "systema.skat.export.header.error.null.dkeh_181");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_s29", "systema.skat.export.header.error.null.dkeh_s29");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_ind", "systema.skat.export.header.error.null.dkeh_ind");
			//Proviant
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_ftx1", "systema.skat.export.header.error.null.dkeh_ftx1");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_ftx2", "systema.skat.export.header.error.null.dkeh_ftx2");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_ftx3", "systema.skat.export.header.error.null.dkeh_ftx3");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_ftx4", "systema.skat.export.header.error.null.dkeh_ftx4");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_ftx5", "systema.skat.export.header.error.null.dkeh_ftx5");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_ftxj", "systema.skat.export.header.error.null.dkeh_ftxj");
			//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_25", "systema.skat.export.header.error.null.dkeh_25");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_zkp", "systema.skat.export.header.error.null.dkeh_zkp");
			//Fakt.belopp and related fields
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_221", "systema.skat.export.header.error.null.dkeh_221");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_221b", "systema.skat.export.header.error.null.dkeh_221b");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_222", "systema.skat.export.header.error.null.dkeh_222");
			
		}else if(this.ANG_ART_30_EXS.equals(record.getDkeh_aart())){
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_a1", "systema.skat.export.header.error.null.dkeh_a1");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_r011", "systema.skat.export.header.error.null.dkeh_r011");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_r012", "systema.skat.export.header.error.null.dkeh_r012");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_dtm1", "systema.skat.export.header.error.null.dkeh_dtm1");
			/*
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_gis1", "systema.skat.export.header.error.null.dkeh_gis1");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_gis2", "systema.skat.export.header.error.null.dkeh_gis2");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_gis3", "systema.skat.export.header.error.null.dkeh_gis3");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_gis4", "systema.skat.export.header.error.null.dkeh_gis4");
			*/
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_uvp", "systema.skat.export.header.error.null.dkeh_uvp");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_s131", "systema.skat.export.header.error.null.dkeh_s131");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_s29", "systema.skat.export.header.error.null.dkeh_s29");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_06", "systema.skat.export.header.error.null.dkeh_06");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_29", "systema.skat.export.header.error.null.dkeh_29");
			//Motdager
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_08b", "systema.skat.export.header.error.null.dkeh_08b");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_08c", "systema.skat.export.header.error.null.dkeh_08c");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_08d", "systema.skat.export.header.error.null.dkeh_08d");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_08e", "systema.skat.export.header.error.null.dkeh_08e");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_08f", "systema.skat.export.header.error.null.dkeh_08f");
			//Representant
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_reeo", "systema.skat.export.header.error.null.dkeh_reeo");
			//Fakt.belopp and related fields
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_221", "systema.skat.export.header.error.null.dkeh_221");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_221b", "systema.skat.export.header.error.null.dkeh_221b");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_222", "systema.skat.export.header.error.null.dkeh_222");
			
			
		}else if(this.ANG_ART_50_IE507.equals(record.getDkeh_aart())){
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_ajou", "systema.skat.export.header.error.null.dkeh_ajou");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_29", "systema.skat.export.header.error.null.dkeh_29");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_uvp", "systema.skat.export.header.error.null.dkeh_uvp");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_treo", "systema.skat.export.header.error.null.dkeh_treo");
			/*
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_gis1", "systema.skat.export.header.error.null.dkeh_gis1");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_gis2", "systema.skat.export.header.error.null.dkeh_gis2");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_gis3", "systema.skat.export.header.error.null.dkeh_gis3");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_gis4", "systema.skat.export.header.error.null.dkeh_gis4");
			*/
			
			
		}else if(this.ANG_ART_31_YM_FUAGo.equals(record.getDkeh_aart())){
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_a1", "systema.skat.export.header.error.null.dkeh_a1");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_r011", "systema.skat.export.header.error.null.dkeh_r011");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_r012", "systema.skat.export.header.error.null.dkeh_r012");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_02a", "systema.skat.export.header.error.null.dkeh_02a");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_17a", "systema.skat.export.header.error.null.dkeh_17a");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_ymd1", "systema.skat.export.header.error.null.dkeh_ymd1");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_ymd2", "systema.skat.export.header.error.null.dkeh_ymd2");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_ym21", "systema.skat.export.header.error.null.dkeh_ym21");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_ym23", "systema.skat.export.header.error.null.dkeh_ym23");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_25", "systema.skat.export.header.error.null.dkeh_25");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_26", "systema.skat.export.header.error.null.dkeh_26");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_s29", "systema.skat.export.header.error.null.dkeh_s29");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_06", "systema.skat.export.header.error.null.dkeh_06");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_29", "systema.skat.export.header.error.null.dkeh_29");
			//Fakt.belopp and related fields
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_221", "systema.skat.export.header.error.null.dkeh_221");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_221b", "systema.skat.export.header.error.null.dkeh_221b");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_222", "systema.skat.export.header.error.null.dkeh_222");
			//Var.placering R.30
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_301", "systema.skat.export.header.error.null.dkeh_301");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_302", "systema.skat.export.header.error.null.dkeh_302");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_303", "systema.skat.export.header.error.null.dkeh_303");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_304", "systema.skat.export.header.error.null.dkeh_304");
			
			
		}else if(this.ANG_ART_32_YM_FUAAo.equals(record.getDkeh_aart())){
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_a1", "systema.skat.export.header.error.null.dkeh_a1");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_r011", "systema.skat.export.header.error.null.dkeh_r011");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_r012", "systema.skat.export.header.error.null.dkeh_r012");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_02a", "systema.skat.export.header.error.null.dkeh_02a");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_17a", "systema.skat.export.header.error.null.dkeh_17a");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_ymvp", "systema.skat.export.header.error.null.dkeh_ymvp");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_ymd1", "systema.skat.export.header.error.null.dkeh_ymd1");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_ymd2", "systema.skat.export.header.error.null.dkeh_ymd2");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_ym21", "systema.skat.export.header.error.null.dkeh_ym21");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_ym23", "systema.skat.export.header.error.null.dkeh_ym23");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_25", "systema.skat.export.header.error.null.dkeh_25");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_26", "systema.skat.export.header.error.null.dkeh_26");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_s29", "systema.skat.export.header.error.null.dkeh_s29");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_06", "systema.skat.export.header.error.null.dkeh_06");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_29", "systema.skat.export.header.error.null.dkeh_29");
			//Fakt.belopp and related fields
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_221", "systema.skat.export.header.error.null.dkeh_221");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_221b", "systema.skat.export.header.error.null.dkeh_221b");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_222", "systema.skat.export.header.error.null.dkeh_222");
			/*
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_gis1", "systema.skat.export.header.error.null.dkeh_gis1");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_gis2", "systema.skat.export.header.error.null.dkeh_gis2");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_gis3", "systema.skat.export.header.error.null.dkeh_gis3");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkeh_gis4", "systema.skat.export.header.error.null.dkeh_gis4");
			*/
		}
		
		//Logical (RULES) controls if we passed the NOT NULL errors
		if(!errors.hasFieldErrors()){
			if(record!=null){
				//------
				//dates 
				//------
				//Check for valid dates (when applicable)
				if(record.getDkeh_dtm1()!=null && !"".equals(record.getDkeh_dtm1())){
					//This can not be implemented in all status since the dtm1 could be back in time at the time the user sends a final Angivelse with
					//the final dtm2. Currently this dtm1 IS ACCEPTED several days back in time...
					//The only exception being status= empty or status="M"
					if(record.getDkeh_syst()==null || "".equals(record.getDkeh_syst()) || "M".equals(record.getDkeh_syst()) ){
						String shortDkih_dtm1 = this.getShortDate(record.getDkeh_dtm1());
						boolean isValidDate = new DateTimeManager().isValidCurrentAndForwardDate(shortDkih_dtm1, "yyyyMMdd");
						if(!isValidDate){
							errors.rejectValue("dkeh_dtm1", "systema.skat.export.header.error.rule.ankomstTidDtm1NotValid"); 
						}
					}

					boolean isValidISOFormat = new DateValidator().validateDateIso203_YYYYMMDDhhmm(record.getDkeh_dtm1());
					if(!isValidISOFormat){
						errors.rejectValue("dkeh_dtm1", "systema.skat.export.header.error.rule.ankomstTidDtm1NotValidMask"); 
					}
					//lenght validation
					if(record.getDkeh_dtm1().length()<12){
						errors.rejectValue("dkeh_dtm1", "systema.skat.export.header.error.rule.ankomstTidDtm1NotValidMask"); 
					}
				}				
				if (record.getDkeh_dtm2()!=null && !"".equals(record.getDkeh_dtm2())){
					boolean isValidISOFormat = new DateValidator().validateDateIso203_YYYYMMDDhhmm(record.getDkeh_dtm2());
					if(!isValidISOFormat){
						errors.rejectValue("dkeh_dtm2", "systema.skat.export.header.error.rule.ankomstTidDtm2NotValidMask"); 
					}
					//lenght validation
					if(record.getDkeh_dtm2().length()<12){
						errors.rejectValue("dkeh_dtm2", "systema.skat.export.header.error.rule.ankomstTidDtm2NotValidMask"); 
					}
					//There is a requirement regarding ajour=9 together with fakt.eksp.tid
					//Either both or nothing
					if("9".equals(record.getDkeh_ajou()) ){
						//OK
					}else{
						errors.rejectValue("dkeh_dtm2", "systema.skat.export.header.error.rule.ankomstTidDtm2NotValid");
					}
				}
				//There is a requirement regarding ajour=9 together with fakt.eksp.tid
				//Either both or nothing
				if ("9".equals(record.getDkeh_ajou()) ){
					if(record.getDkeh_dtm2()!=null && !"".equals(record.getDkeh_dtm2())){
						 //OK
					}else{
						errors.rejectValue("dkeh_dtm2", "systema.skat.export.header.error.rule.ankomstTidDtm2NotValid");
					}
					
				}
				//--------------
				//Angivelsestyp
				//--------------
				if( record.getDkeh_17a()!=null && !"".equals(record.getDkeh_17a()) ){ 
					if(!this.isValidAngivelseTypeForEftaCountry(record)){
						errors.rejectValue("dkeh_17a", "systema.skat.export.header.error.rule.eftaCountriesAngType"); 
					}
					if(!this.isValidAngivelseTypeForNoneEftaCountry(record)){
						errors.rejectValue("dkeh_17a", "systema.skat.export.header.error.rule.noneEftaCountriesAngType"); 
					}
				}
				//-------------------
				//FTX - Bemærkning
				//-------------------
				if("1".equals(record.getDkeh_ajou()) || "2".equals(record.getDkeh_ajou()) || "8".equals(record.getDkeh_ajou()) ){
					if(record.getDkeh_ftx6()==null || "".equals(record.getDkeh_ftx6())){
						errors.rejectValue("dkeh_ftx6", "systema.skat.export.header.error.rule.bemaerkning1NotNull");
					}
				}
				//-------------------
				//Ekspeditionsnr
				//-------------------
				final String CENTURY_CODE = "20";
				final String EXPORT_CODE = "2";
	
				if(record.getDkeh_a2()!=null && !"".equals(record.getDkeh_a2()) ){
					if(record.getDkeh_a2().length()<13){
						errors.rejectValue("dkeh_a2", "systema.skat.export.header.error.rule.expeditionsNrNotValid");
					}else{
						String prefix = record.getDkeh_a2().substring(0,5);
						if(!prefix.startsWith(CENTURY_CODE)){
							errors.rejectValue("dkeh_a2", "systema.skat.export.header.error.rule.expeditionsNrNotValid");
						}
						if(!prefix.endsWith(EXPORT_CODE)){
							errors.rejectValue("dkeh_a2", "systema.skat.export.header.error.rule.expeditionsNrNotValid");
						}
					}
					
				}
				//----------------------
				//Proviant restrictions
				//----------------------
				if(this.ANG_ART_26_PRO.equals(record.getDkeh_aart()) || this.ANG_ART_27_PROM.equals(record.getDkeh_aart()) ){
					if(record.getDkeh_25()!=null && !"".equals(record.getDkeh_25())){
						errors.rejectValue("dkeh_a2", "systema.skat.export.header.error.rule.transport25_NotValid");
					}
					//Check for a minimum valid Proviant Section
					if(!this.isValidProviantSection(record)){
						errors.rejectValue("dkeh_a2", "systema.skat.export.header.error.rule.proviantSectionNotValid");
					}
					
				}
				if(this.ANG_ART_30_EXS.equals(record.getDkeh_aart())){
					//----------------------------------------------
					//Transitland - at least 2 where DK = first one
					//----------------------------------------------
					if(!"".equals(record.getDkeh_s131())){
						if(!"DK".equals(record.getDkeh_s131())){
							errors.rejectValue("dkeh_s131", "systema.skat.export.header.error.null.dkeh_s131");	
						}else{
							if(record.getDkeh_s132()==null || "".equals(record.getDkeh_s132()) ){	
								errors.rejectValue("dkeh_s132", "systema.skat.export.header.error.null.dkeh_s131");
							}
						}
					}
				}
				
				//----------------------
				//Afsender - Sender
				//----------------------
				if(this.ANG_ART_22_SUP.equals(record.getDkeh_aart()) || this.ANG_ART_23_SUPH.equals(record.getDkeh_aart()) || 
					this.ANG_ART_50_IE507.equals(record.getDkeh_aart())){
					//Nothing
				}else{
					//Validate Sender when EORI is null
					if(record.getDkeh_02a()==null || "".equals(record.getDkeh_02a())){
						if(!this.isValidSender(record)){
							errors.rejectValue("dkeh_02a", "systema.skat.export.header.error.rule.senderSectionNotValid");
						}
					}else{
						if(!this.isValidSenderEori(record)){
							errors.rejectValue("dkeh_02a", "systema.skat.export.header.error.rule.cvrNrPrefixSender");
						}
						if(record.getDkeh_02a().length()!=10 ){
							errors.rejectValue("dkeh_02a", "systema.skat.export.header.error.rule.cvrNrValidLengthSender");
						}
						
					}
				}
				//----------------------
				//Modtager - Receiver
				//----------------------
				if(this.ANG_ART_31_YM_FUAGo.equals(record.getDkeh_aart()) || this.ANG_ART_32_YM_FUAAo.equals(record.getDkeh_aart()) ){
					//Validate Receiver when EORI is null
					if(record.getDkeh_08a()==null || "".equals(record.getDkeh_08a())){
						if(!this.isValidReceiver(record)){
							errors.rejectValue("dkeh_08a", "systema.skat.export.header.error.rule.receiverSectionNotValid");
						}
						
					}
				}
				//-----------------------
				//Klarerer - Representant
				//-----------------------
				if(this.ANG_ART_50_IE507.equals(record.getDkeh_aart())){
					//Nothing
				}else{
					if(record.getDkeh_14a()!=null && !"".equals(record.getDkeh_14a())){
						if(!this.isValidKlarererEori(record)){
							errors.rejectValue("dkeh_14a", "systema.skat.export.header.error.rule.cvrNrPrefixKlarereren");
						}
						if(record.getDkeh_14a().length()!=10 ){
							errors.rejectValue("dkeh_14a", "systema.skat.export.header.error.rule.cvrNrValidLengthKlarereren");
						}
					}
				}
				
				//-----------------------
				//Varernas placering
				//-----------------------
				if(record.getDkeh_304()!=null && !"".equals(record.getDkeh_304())){
					int MIN_LENGTH_DKEH_304 = 3;
					if(record.getDkeh_304().length() < MIN_LENGTH_DKEH_304){
						errors.rejectValue("dkeh_304", "systema.skat.export.header.error.rule.varplacering.adresseLopenr");
					}
					
				}
				
				
				//---------------------------------------------
				//We need to validate all item lines
				//(a) Varekode: 		8-characters (dkev_331)
				//(b) put more rules here...
				//---------------------------------------------
				String itemCode = null;
				String prefix = null;
				String errorMsgVarekode = " -->33.Varekode: skal være 8-cifret";
				for(JsonSkatExportSpecificTopicItemRecord itemRecord : record.getJsonSkatExportSpecificTopicItemContainer().getOrderList()){
					itemCode = itemRecord.getDkev_331();
					prefix = "Vareposter:Linje[" + itemRecord.getDkev_syli() + "] ";
					if(itemCode!=null && !"".equals(itemCode)){
						if(itemCode.length()!=8){
							errors.rejectValue("dkeh_syav", "dkeh_syav", prefix + errorMsgVarekode);
							break;
						}
					}else{
						errors.rejectValue("dkeh_syav", "dkeh_syav", prefix + errorMsgVarekode);
						break;
					}
				}
				
			}
			
		}
		
		
		
	}
	/**
	 * 
	 * @param value
	 * @return
	 */
	private String getShortDate(String value){
		String retval = null;
		if(value!=null && !"".equals(value)){
			if(value.length()>8){
				try{
					retval = value.substring(0,8);
				}catch (Exception e){
					//nothing
				}
			}
		}
		
		return retval;
	}
	/**
	 * 
	 * @param record
	 * @return
	 */
	private boolean isValidProviantSection(JsonSkatExportSpecificTopicRecord record){
		boolean retval = false;
		if(!"".equals(record.getDkeh_ftxa())){ retval = true; }
		if(!"".equals(record.getDkeh_ftxb())){ retval = true; }
		if(!"".equals(record.getDkeh_ftxc())){ retval = true; }
		if(!"".equals(record.getDkeh_ftxd())){ retval = true; }
		if(!"".equals(record.getDkeh_ftxe())){ retval = true; }
		if(!"".equals(record.getDkeh_ftxf())){ retval = true; }
		if(!"".equals(record.getDkeh_ftxg())){ retval = true; }
		if(!"".equals(record.getDkeh_ftxh())){ retval = true; }
		if(!"".equals(record.getDkeh_ftxi())){ retval = true; }
		
		
		return retval;
	}
	
	/**
	 * 
	 * @param record
	 * @return
	 */
	private boolean isValidSender(JsonSkatExportSpecificTopicRecord record){
		boolean retval = false;
		if(!"".equals(record.getDkeh_02b()) && !"".equals(record.getDkeh_02c()) && !"".equals(record.getDkeh_02d()) &&
		   !"".equals(record.getDkeh_02e()) && !"".equals(record.getDkeh_02f())){ 
			
			retval = true; 
		}
		
		return retval;
	}
	/**
	 * 
	 * @param record
	 * @return
	 */
	private boolean isValidSenderEori(JsonSkatExportSpecificTopicRecord record){
		boolean retval = false;
		if( record.getDkeh_02a()!=null && !"".equals(record.getDkeh_02a()) ){ 
			if(record.getDkeh_02a().startsWith("DK")){
				retval = true;
			}
		}
		
		return retval;
	}
	/**
	 * 
	 * @param record
	 * @return
	 */
	private boolean isValidKlarererEori(JsonSkatExportSpecificTopicRecord record){
		boolean retval = false;
		if( record.getDkeh_14a()!=null && !"".equals(record.getDkeh_14a()) ){ 
			if(record.getDkeh_14a().startsWith("DK")){
				retval = true;
			}
		}
		
		return retval;
	}
	
	/**
	 * 
	 * @param record
	 * @return
	 */
	private boolean isValidReceiver(JsonSkatExportSpecificTopicRecord record){
		boolean retval = false;
		if(!"".equals(record.getDkeh_08b()) && !"".equals(record.getDkeh_08c()) && !"".equals(record.getDkeh_08d()) &&
		   !"".equals(record.getDkeh_08e()) && !"".equals(record.getDkeh_08f())){ 
			
			retval = true; 
		}
		
		return retval;
	}
	
	
	/**
	 * 
	 * @param value
	 * @return
	 */
	private boolean isValidAngivelseTypeForEftaCountry(JsonSkatExportSpecificTopicRecord record){
		String[] eftaCountry = {"NO","IS","CH"};
		boolean retval = true;
		
		if(record!=null){
			String receiverLand = record.getDkeh_17a();
			
			List<String> list = Arrays.asList(eftaCountry);
			for(String field: list){
				if(!"EU".equals(record.getDkeh_r011())){
					if(field.equals(receiverLand)){
						retval = false;
						break;
					}
				}
			}
		}
		return retval;
	}
	
	/**
	 * 
	 * @param value
	 * @return
	 */
	private boolean isValidAngivelseTypeForNoneEftaCountry(JsonSkatExportSpecificTopicRecord record){
		String[] eftaCountry = {"NO","IS","CH"};
		boolean retval = true;
		boolean eftaMatch = false;
		
		if(record!=null){
			String receiverLand = record.getDkeh_17a();
			//Catch the possible Efta country (if any)
			List<String> list = Arrays.asList(eftaCountry);
			for(String field: list){
				if(field.equals(receiverLand)){
					eftaMatch = true;
					break;
				}
			}
		}
		//Now check if valid
		if("EU".equals(record.getDkeh_r011()) && !eftaMatch){
			retval = false;
		}
		
		return retval;
	}
	
		
}
