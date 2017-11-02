/**
 * 
 */
package no.systema.skat.skatexport.model.jsonjackson.topic;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;
import no.systema.skat.skatexport.model.jsonjackson.topic.items.JsonSkatExportSpecificTopicItemContainer;
import java.util.*;
import java.lang.reflect.Field;
/**
 * @author oscardelatorre
 * @date Mar 14, 2014
 * AS400 File: DKEH ; Format: DKEHR
 *
 */
public class JsonSkatExportSpecificTopicRecord extends JsonAbstractGrandFatherRecord{
	
	//Needed list for validation purposes (iterating through all lines in order to validate som key required values inside the order validator)
	private JsonSkatExportSpecificTopicItemContainer jsonSkatExportSpecificTopicItemContainer = null;
	public void setJsonSkatExportSpecificTopicItemContainer(JsonSkatExportSpecificTopicItemContainer value) {  this.jsonSkatExportSpecificTopicItemContainer = value; }
	public JsonSkatExportSpecificTopicItemContainer getJsonSkatExportSpecificTopicItemContainer() {return this.jsonSkatExportSpecificTopicItemContainer;}
	
	private Integer sumOfAntalKolliInItemLines = 0;
	public void setSumOfAntalKolliInItemLines(Integer value) {  this.sumOfAntalKolliInItemLines = value; }
	public Integer getSumOfAntalKolliInItemLines() {return this.sumOfAntalKolliInItemLines;}
	
	private String sumOfAntalKolliInItemLinesStr = null;
	public String getSumOfAntalKolliInItemLinesStr() {
		this.sumOfAntalKolliInItemLinesStr = String.valueOf(this.sumOfAntalKolliInItemLines);
		return this.sumOfAntalKolliInItemLinesStr;
	}
	
	private Integer sumOfAntalItemLines = 0;
	public void setSumOfAntalItemLines(Integer value) {  this.sumOfAntalItemLines = value; }
	public Integer getSumOfAntalItemLines() {return this.sumOfAntalItemLines;}
	
	private String sumOfAntalItemLinesStr = null;
	public String getSumOfAntalItemLinesStr() {
		this.sumOfAntalItemLinesStr = String.valueOf(this.sumOfAntalItemLines);
		return this.sumOfAntalItemLinesStr;
	}
	
	private Double sumTotalAmountItemLines = 0.00D;
	public void setSumTotalAmountItemLines(Double value) {  this.sumTotalAmountItemLines = value; }
	public Double getSumTotalAmountItemLines() {return this.sumTotalAmountItemLines;}
	
	private String sumTotalAmountItemLinesStr = null;
	public String getSumTotalAmountItemLinesStr() {
		String tmpStr = this.numberFormatter.getDoubleToPlainString(sumTotalAmountItemLines, 3);
		this.sumTotalAmountItemLinesStr = tmpStr;
		return this.sumTotalAmountItemLinesStr;
	}
	
	private boolean fakturaListExist = false;
	public void setFakturaListExist(boolean value) { this.fakturaListExist = value; }
	public boolean getFakturaListExist (){ return this.fakturaListExist; }

	//Used when different currencies exist. The main currency must be = DKK
	private String fakturaListTotValidCurrency = null;
	public void setFakturaListTotValidCurrency(String value) {  this.fakturaListTotValidCurrency = value; }
	public String getFakturaListTotValidCurrency() { return this.fakturaListTotValidCurrency; }
	
	private String fakturaListTotSum = null;
	public void setFakturaListTotSum(String value) {  this.fakturaListTotSum = value; }
	public String getFakturaListTotSum() { return this.fakturaListTotSum; }
	
	private String fakturaListTotKurs = null;
	public void setFakturaListTotKurs(String value) {  this.fakturaListTotKurs = value; }
	public String getFakturaListTotKurs() { return this.fakturaListTotKurs; }
	
	private String fakturaListMrn = null;
	public void setFakturaListMrn(String value) {  this.fakturaListMrn = value; }
	public String getFakturaListMrn() { return this.fakturaListMrn; }
	
	
	
	//in order to validate before a "send topic"
	private boolean validUpdate = false;
	public void setValidUpdate(boolean value) {  this.validUpdate = value; }
	public boolean isValidUpdate() {return this.validUpdate;}
	
	//test avd flag
	private String testAvdFlag = null;
	public void setTestAvdFlag(String value) {  this.testAvdFlag = value; }
	public String getTestAvdFlag() {return this.testAvdFlag;}
	
	//test indicator
	private String dkeh_0035 = null;
	public void setDkeh_0035(String value) {  this.dkeh_0035 = value; }
	public String getDkeh_0035() {return this.dkeh_0035;}
		
	private String dkeh_syav = null;
	public void setDkeh_syav(String value) {  this.dkeh_syav = value; }
	public String getDkeh_syav() {return this.dkeh_syav;}
	
	private String dkeh_syop = null;
	public void setDkeh_syop(String value) {  this.dkeh_syop = value; }
	public String getDkeh_syop() {return this.dkeh_syop;}
	
	private String dkeh_sysg = null;
	public void setDkeh_sysg(String value) {  this.dkeh_sysg = value; }
	public String getDkeh_sysg() {return this.dkeh_sysg;}
	
	private String dkeh_syst = null;
	public void setDkeh_syst(String value) {  this.dkeh_syst = value; }
	public String getDkeh_syst() {return this.dkeh_syst;}
	
	private String dkeh_sydt = null;
	public void setDkeh_sydt(String value) {  this.dkeh_sydt = value; }
	public String getDkeh_sydt() {return this.dkeh_sydt;}
	
	private String dkeh_godt = null;
	public void setDkeh_godt(String value) {  this.dkeh_godt = value; }
	public String getDkeh_godt() {return this.dkeh_godt;}

	private String dkeh_frdt = null;
	public void setDkeh_frdt(String value) {  this.dkeh_frdt = value; }
	public String getDkeh_frdt() {return this.dkeh_frdt;}
	
	private String dkeh_vadt = null;
	public void setDkeh_vadt(String value) {  this.dkeh_vadt = value; }
	public String getDkeh_vadt() {return this.dkeh_vadt;}
	
	private String dkeh_fedt = null;
	public void setDkeh_fedt(String value) {  this.dkeh_fedt = value; }
	public String getDkeh_fedt() {return this.dkeh_fedt;}
	
	private String dkeh_fudt = null;
	public void setDkeh_fudt(String value) {  this.dkeh_fudt = value; }
	public String getDkeh_fudt() {return this.dkeh_fudt;}
	
	private String dkeh_fvdt = null;
	public void setDkeh_fvdt(String value) {  this.dkeh_fvdt = value; }
	public String getDkeh_fvdt() {return this.dkeh_fvdt;}
	
	private String dkeh_ctdt = null;
	public void setDkeh_ctdt(String value) {  this.dkeh_ctdt = value; }
	public String getDkeh_ctdt() {return this.dkeh_ctdt;}
	
	private String dkeh_cfdt = null;
	public void setDkeh_cfdt(String value) {  this.dkeh_cfdt = value; }
	public String getDkeh_cfdt() {return this.dkeh_cfdt;}
	
	private String dkeh_fadt = null;
	public void setDkeh_fadt(String value) {  this.dkeh_fadt = value; }
	public String getDkeh_fadt() {return this.dkeh_fadt;}
	
	private String dkeh_fast = null;
	public void setDkeh_fast(String value) {  this.dkeh_fast = value; }
	public String getDkeh_fast() {return this.dkeh_fast;}
	
	private String dkeh_mrn = null;
	public void setDkeh_mrn(String value) {  this.dkeh_mrn = value; }
	public String getDkeh_mrn() {return this.dkeh_mrn;}
	
	private String dkeh_prof = null;
	public void setDkeh_prof(String value) {  this.dkeh_prof = value; }
	public String getDkeh_prof() {return this.dkeh_prof;}
	
	private String dkeh_1004 = null;
	public void setDkeh_1004(String value) {  this.dkeh_1004 = value; }
	public String getDkeh_1004() {return this.dkeh_1004;}
	
	private String dkeh_xref = null;
	public void setDkeh_xref(String value) {  this.dkeh_xref = value; }
	public String getDkeh_xref() {return this.dkeh_xref;}
	
	private String dkeh_a1 = null;
	public void setDkeh_a1(String value) {  this.dkeh_a1 = value; }
	public String getDkeh_a1() {return this.dkeh_a1;}
	
	private String dkeh_a2 = null;
	public void setDkeh_a2(String value) {  this.dkeh_a2 = value; }
	public String getDkeh_a2() {return this.dkeh_a2;}
	
	private String dkeh_dtm1 = null;
	public void setDkeh_dtm1(String value) {  this.dkeh_dtm1 = value; }
	public String getDkeh_dtm1() {return this.dkeh_dtm1;}
	
	
	private String dkeh_dtm2 = null;
	public void setDkeh_dtm2(String value) {  this.dkeh_dtm2 = value; }
	public String getDkeh_dtm2() {return this.dkeh_dtm2;}
	
	
	private String dkeh_ajou = null;
	public void setDkeh_ajou(String value) {  this.dkeh_ajou = value; }
	public String getDkeh_ajou() {return this.dkeh_ajou;}
	
	private String dkeh_ensf = null;
	public void setDkeh_ensf(String value) {  this.dkeh_ensf = value; }
	public String getDkeh_ensf() {return this.dkeh_ensf;}
	
	private String dkeh_r011 = null;
	public void setDkeh_r011(String value) {  this.dkeh_r011 = value; }
	public String getDkeh_r011() {return this.dkeh_r011;}

	private String dkeh_r012 = null;
	public void setDkeh_r012(String value) {  this.dkeh_r012 = value; }
	public String getDkeh_r012() {return this.dkeh_r012;}

	private String dkeh_aart = null;
	public void setDkeh_aart(String value) {  this.dkeh_aart = value; }
	public String getDkeh_aart() {return this.dkeh_aart;}

	private String dkeh_gis1 = null;
	public void setDkeh_gis1(String value) {  this.dkeh_gis1 = value; }
	public String getDkeh_gis1() {return this.dkeh_gis1;}
	
	private String dkeh_gis2 = null;
	public void setDkeh_gis2(String value) {  this.dkeh_gis2 = value; }
	public String getDkeh_gis2() {return this.dkeh_gis2;}

	private String dkeh_gis3 = null;
	public void setDkeh_gis3(String value) {  this.dkeh_gis3 = value; }
	public String getDkeh_gis3() {return this.dkeh_gis3;}
	
	private String dkeh_gis4 = null;
	public void setDkeh_gis4(String value) {  this.dkeh_gis4 = value; }
	public String getDkeh_gis4() {return this.dkeh_gis4;}

	
	//----Afsender
	private String dkeh_avkn = null;
	public void setDkeh_avkn(String value) {  this.dkeh_avkn = value; }
	public String getDkeh_avkn() {return this.dkeh_avkn;}
	
	private String dkeh_02a = null;
	public void setDkeh_02a(String value) {  this.dkeh_02a = value; }
	public String getDkeh_02a() {return this.dkeh_02a;}
	
	private String dkeh_02b = null;
	public void setDkeh_02b(String value) {  this.dkeh_02b = value; }
	public String getDkeh_02b() {return this.dkeh_02b;}
	
	private String dkeh_02c = null;
	public void setDkeh_02c(String value) {  this.dkeh_02c = value; }
	public String getDkeh_02c() {return this.dkeh_02c;}
	
	private String dkeh_02d = null;
	public void setDkeh_02d(String value) {  this.dkeh_02d = value; }
	public String getDkeh_02d() {return this.dkeh_02d;}
	
	private String dkeh_02e = null;
	public void setDkeh_02e(String value) {  this.dkeh_02e = value; }
	public String getDkeh_02e() {return this.dkeh_02e;}
	
	private String dkeh_02f = null;
	public void setDkeh_02f(String value) {  this.dkeh_02f = value; }
	public String getDkeh_02f() {return this.dkeh_02f;}
	
	private String dkeh_brut = null;
	public void setDkeh_brut(String value) {  this.dkeh_brut = value; }
	public String getDkeh_brut() {return this.dkeh_brut;}
	
	private String dkeh_06 = null;
	public void setDkeh_06(String value) {  this.dkeh_06 = value; }
	public String getDkeh_06() {return this.dkeh_06;}
	
	private String dkeh_07 = null;
	public void setDkeh_07(String value) {  this.dkeh_07 = value; }
	public String getDkeh_07() {return this.dkeh_07;}
	
	private String dkeh_07a = null;
	public void setDkeh_07a(String value) {  this.dkeh_07a = value; }
	public String getDkeh_07a() {return this.dkeh_07a;}
	
	private String dkeh_07b = null;
	public void setDkeh_07b(String value) {  this.dkeh_07b = value; }
	public String getDkeh_07b() {return this.dkeh_07b;}
	
	private String dkeh_07c = null;
	public void setDkeh_07c(String value) {  this.dkeh_07c = value; }
	public String getDkeh_07c() {return this.dkeh_07c;}
	
	private String dkeh_07d = null;
	public void setDkeh_07d(String value) {  this.dkeh_07d = value; }
	public String getDkeh_07d() {return this.dkeh_07d;}
	
	
	//-----Modtager
	private String dkeh_mokn = null;
	public void setDkeh_mokn(String value) {  this.dkeh_mokn = value; }
	public String getDkeh_mokn() {return this.dkeh_mokn;}
	
	private String dkeh_08a = null;
	public void setDkeh_08a(String value) {  this.dkeh_08a = value; }
	public String getDkeh_08a() {return this.dkeh_08a;}
	
	private String dkeh_08b = null;
	public void setDkeh_08b(String value) {  this.dkeh_08b = value; }
	public String getDkeh_08b() {return this.dkeh_08b;}
	
	private String dkeh_08c = null;
	public void setDkeh_08c(String value) {  this.dkeh_08c = value; }
	public String getDkeh_08c() {return this.dkeh_08c;}
	
	private String dkeh_08d = null;
	public void setDkeh_08d(String value) {  this.dkeh_08d = value; }
	public String getDkeh_08d() {return this.dkeh_08d;}
	
	private String dkeh_08e = null;
	public void setDkeh_08e(String value) {  this.dkeh_08e = value; }
	public String getDkeh_08e() {return this.dkeh_08e;}
	
	private String dkeh_08f = null;
	public void setDkeh_08f(String value) {  this.dkeh_08f = value; }
	public String getDkeh_08f() {return this.dkeh_08f;}
	
	//------Representat
	private String dkeh_rekn = null;
	public void setDkeh_rekn(String value) {  this.dkeh_rekn = value; }
	public String getDkeh_rekn() {return this.dkeh_rekn;}
	
	private String dkeh_reeo = null;
	public void setDkeh_reeo(String value) {  this.dkeh_reeo = value; }
	public String getDkeh_reeo() {return this.dkeh_reeo;}
	
	private String dkeh_rena = null;
	public void setDkeh_rena(String value) {  this.dkeh_rena = value; }
	public String getDkeh_rena() {return this.dkeh_rena;}

	private String dkeh_rega = null;
	public void setDkeh_rega(String value) {  this.dkeh_rega = value; }
	public String getDkeh_rega() {return this.dkeh_rega;}

	private String dkeh_repo = null;
	public void setDkeh_repo(String value) {  this.dkeh_repo = value; }
	public String getDkeh_repo() {return this.dkeh_repo;}
	
	private String dkeh_reby = null;
	public void setDkeh_reby(String value) {  this.dkeh_reby = value; }
	public String getDkeh_reby() {return this.dkeh_reby;}
	
	private String dkeh_relk = null;
	public void setDkeh_relk(String value) {  this.dkeh_relk = value; }
	public String getDkeh_relk() {return this.dkeh_relk;}
	
	//------Transportør
	private String dkeh_trkn = null;
	public void setDkeh_trkn(String value) {  this.dkeh_trkn = value; }
	public String getDkeh_trkn() {return this.dkeh_trkn;}
	
	private String dkeh_treo = null;
	public void setDkeh_treo(String value) {  this.dkeh_treo = value; }
	public String getDkeh_treo() {return this.dkeh_treo;}

	private String dkeh_zkp = null;
	public void setDkeh_zkp(String value) {  this.dkeh_zkp = value; }
	public String getDkeh_zkp() {return this.dkeh_zkp;}
	
		
	//rest of information	
	private String dkeh_12 = null;
	public void setDkeh_12(String value) {  this.dkeh_12 = value; }
	public String getDkeh_12() { return this.dkeh_12;}
	
	private String dkeh_14a = null;
	public void setDkeh_14a(String value) {  this.dkeh_14a = value; }
	public String getDkeh_14a() { return this.dkeh_14a;}
	
	private String dkeh_14b = null;
	public void setDkeh_14b(String value) {  this.dkeh_14b = value; }
	public String getDkeh_14b() { return this.dkeh_14b;}
	
	private String dkeh_17a = null;
	public void setDkeh_17a(String value) {  this.dkeh_17a = value; }
	public String getDkeh_17a() { return this.dkeh_17a;}
	
	private String dkeh_uvp = null;
	public void setDkeh_uvp(String value) {  this.dkeh_uvp = value; }
	public String getDkeh_uvp() { return this.dkeh_uvp;}
	
	
	private String dkeh_181 = null;
	public void setDkeh_181(String value) {  this.dkeh_181 = value; }
	public String getDkeh_181() { return this.dkeh_181;}

	private String dkeh_201 = null;
	public void setDkeh_201(String value) {  this.dkeh_201 = value; }
	public String getDkeh_201() { return this.dkeh_201;}
	
	private String dkeh_s29 = null;
	public void setDkeh_s29(String value) {  this.dkeh_s29 = value; }
	public String getDkeh_s29() { return this.dkeh_s29;}
	
	private String dkeh_202 = null;
	public void setDkeh_202(String value) {  this.dkeh_202 = value; }
	public String getDkeh_202() { return this.dkeh_202;}
	
	private String dkeh_21 = null;
	public void setDkeh_21(String value) {  this.dkeh_21 = value; }
	public String getDkeh_21() { return this.dkeh_21;}
	
	private String dkeh_221 = null;
	public void setDkeh_221(String value) {  this.dkeh_221 = value; }
	public String getDkeh_221() { return this.dkeh_221;}

	private String dkeh_221b = null;
	public void setDkeh_221b(String value) {  this.dkeh_221b = value; }
	public String getDkeh_221b() { return this.dkeh_221b;}
	
	private String dkeh_221c = null;
	public void setDkeh_221c(String value) {  this.dkeh_221c = value; }
	public String getDkeh_221c() { return this.dkeh_221c;}
	
	private String dkeh_222 = null;
	public void setDkeh_222(String value) {  this.dkeh_222 = value; }
	public String getDkeh_222() { return this.dkeh_222;}
	
	private Double dkeh_222Dbl = 0.00D;
	public Double getDkeh_222Dbl() {
		Double retval = dkeh_222Dbl;
		try{
			if(this.dkeh_222!=null){
				retval = Double.parseDouble(this.dkeh_222.replace(",", "."));
			}
		}catch(Exception e){
			//Let it go
		}
		return retval;
	}
	
	private String dkeh_25 = null;
	public void setDkeh_25(String value) {  this.dkeh_25 = value; }
	public String getDkeh_25() { return this.dkeh_25;}
	
	private String dkeh_26 = null;
	public void setDkeh_26(String value) {  this.dkeh_26 = value; }
	public String getDkeh_26() { return this.dkeh_26;}
	
	private String dkeh_taf = null;
	public void setDkeh_taf(String value) {  this.dkeh_taf = value; }
	public String getDkeh_taf() { return this.dkeh_taf;}
	
	private String dkeh_ind = null;
	public void setDkeh_ind(String value) {  this.dkeh_ind = value; }
	public String getDkeh_ind() { return this.dkeh_ind;}
	
	private String dkeh_29 = null;
	public void setDkeh_29(String value) {  this.dkeh_29 = value; }
	public String getDkeh_29() { return this.dkeh_29;}
	
	
	private String dkeh_301 = null;
	public void setDkeh_301(String value) {  this.dkeh_301 = value; }
	public String getDkeh_301() { return this.dkeh_301;}
	
	private String dkeh_302 = null;
	public void setDkeh_302(String value) {  this.dkeh_302 = value; }
	public String getDkeh_302() { return this.dkeh_302;}
	
	private String dkeh_303 = null;
	public void setDkeh_303(String value) {  this.dkeh_303 = value; }
	public String getDkeh_303() { return this.dkeh_303;}

	private String dkeh_304 = null;
	public void setDkeh_304(String value) {  this.dkeh_304 = value; }
	public String getDkeh_304() { return this.dkeh_304;}

	private String dkeh_49 = null;
	public void setDkeh_49(String value) {  this.dkeh_49 = value; }
	public String getDkeh_49() { return this.dkeh_49;}
	
	//Invoice records only internally and not for SKAT (AS400 db and not EDIFACT outbound)
	private String dkeh_28a = null;
	public void setDkeh_28a(String value) {  this.dkeh_28a = value; }
	public String getDkeh_28a() { return this.dkeh_28a;}

	private String dkeh_28b = null;
	public void setDkeh_28b(String value) {  this.dkeh_28b = value; }
	public String getDkeh_28b() { return this.dkeh_28b;}

	private String dkeh_28c = null;
	public void setDkeh_28c(String value) {  this.dkeh_28c = value; }
	public String getDkeh_28c() { return this.dkeh_28c;}

	
	private String dkeh_s131 = null;
	public void setDkeh_s131(String value) {  this.dkeh_s131 = value; }
	public String getDkeh_s131() { return this.dkeh_s131;}
	
	private String dkeh_s132 = null;
	public void setDkeh_s132(String value) {  this.dkeh_s132 = value; }
	public String getDkeh_s132() { return this.dkeh_s132;}
	
	private String dkeh_s133 = null;
	public void setDkeh_s133(String value) {  this.dkeh_s133 = value; }
	public String getDkeh_s133() { return this.dkeh_s133;}
	
	private String dkeh_s134 = null;
	public void setDkeh_s134(String value) {  this.dkeh_s134 = value; }
	public String getDkeh_s134() { return this.dkeh_s134;}
	
	private String dkeh_s135 = null;
	public void setDkeh_s135(String value) {  this.dkeh_s135 = value; }
	public String getDkeh_s135() { return this.dkeh_s135;}
	
	private String dkeh_d011 = null;
	public void setDkeh_d011(String value) {  this.dkeh_d011 = value; }
	public String getDkeh_d011() { return this.dkeh_d011;}
	
	private String dkeh_d012 = null;
	public void setDkeh_d012(String value) {  this.dkeh_d012 = value; }
	public String getDkeh_d012() { return this.dkeh_d012;}
	
	private String dkeh_d013 = null;
	public void setDkeh_d013(String value) {  this.dkeh_d013 = value; }
	public String getDkeh_d013() { return this.dkeh_d013;}
	
	private String dkeh_d014 = null;
	public void setDkeh_d014(String value) {  this.dkeh_d014 = value; }
	public String getDkeh_d014() { return this.dkeh_d014;}
	
	private String dkeh_d015 = null;
	public void setDkeh_d015(String value) {  this.dkeh_d015 = value; }
	public String getDkeh_d015() { return this.dkeh_d015;}
	
	private String dkeh_ftx1 = null;
	public void setDkeh_ftx1(String value) {  this.dkeh_ftx1 = value; }
	public String getDkeh_ftx1() { return this.dkeh_ftx1;}
	
	private String dkeh_ftx2 = null;
	public void setDkeh_ftx2(String value) {  this.dkeh_ftx2 = value; }
	public String getDkeh_ftx2() { return this.dkeh_ftx2;}
	
	private String dkeh_ftx3 = null;
	public void setDkeh_ftx3(String value) {  this.dkeh_ftx3 = value; }
	public String getDkeh_ftx3() { return this.dkeh_ftx3;}

	private String dkeh_ftx4 = null;
	public void setDkeh_ftx4(String value) {  this.dkeh_ftx4 = value; }
	public String getDkeh_ftx4() { return this.dkeh_ftx4;}

	private String dkeh_ftx5 = null;
	public void setDkeh_ftx5(String value) {  this.dkeh_ftx5 = value; }
	public String getDkeh_ftx5() { return this.dkeh_ftx5;}

	private String dkeh_ftx6 = null;
	public void setDkeh_ftx6(String value) {  this.dkeh_ftx6 = value; }
	public String getDkeh_ftx6() { return this.dkeh_ftx6;}
	
	private String dkeh_ftx7 = null;
	public void setDkeh_ftx7(String value) {  this.dkeh_ftx7 = value; }
	public String getDkeh_ftx7() { return this.dkeh_ftx7;}

	private String dkeh_ftx8 = null;
	public void setDkeh_ftx8(String value) {  this.dkeh_ftx8 = value; }
	public String getDkeh_ftx8() { return this.dkeh_ftx8;}

	private String dkeh_ftx9 = null;
	public void setDkeh_ftx9(String value) {  this.dkeh_ftx9 = value; }
	public String getDkeh_ftx9() { return this.dkeh_ftx9;}

	private String dkeh_ftxa = null;
	public void setDkeh_ftxa(String value) {  this.dkeh_ftxa = value; }
	public String getDkeh_ftxa() { return this.dkeh_ftxa;}

	private String dkeh_ftxb = null;
	public void setDkeh_ftxb(String value) {  this.dkeh_ftxb = value; }
	public String getDkeh_ftxb() { return this.dkeh_ftxb;}

	private String dkeh_ftxc = null;
	public void setDkeh_ftxc(String value) {  this.dkeh_ftxc = value; }
	public String getDkeh_ftxc() { return this.dkeh_ftxc;}

	private String dkeh_ftxd = null;
	public void setDkeh_ftxd(String value) {  this.dkeh_ftxd = value; }
	public String getDkeh_ftxd() { return this.dkeh_ftxd;}

	private String dkeh_ftxe = null;
	public void setDkeh_ftxe(String value) {  this.dkeh_ftxe = value; }
	public String getDkeh_ftxe() { return this.dkeh_ftxe;}

	private String dkeh_ftxf = null;
	public void setDkeh_ftxf(String value) {  this.dkeh_ftxf = value; }
	public String getDkeh_ftxf() { return this.dkeh_ftxf;}

	private String dkeh_ftxg = null;
	public void setDkeh_ftxg(String value) {  this.dkeh_ftxg = value; }
	public String getDkeh_ftxg() { return this.dkeh_ftxg;}

	private String dkeh_ftxh = null;
	public void setDkeh_ftxh(String value) {  this.dkeh_ftxh = value; }
	public String getDkeh_ftxh() { return this.dkeh_ftxh;}

	private String dkeh_ftxi = null;
	public void setDkeh_ftxi(String value) {  this.dkeh_ftxi = value; }
	public String getDkeh_ftxi() { return this.dkeh_ftxi;}
	
	private String dkeh_ftxj = null;
	public void setDkeh_ftxj(String value) {  this.dkeh_ftxj = value; }
	public String getDkeh_ftxj() { return this.dkeh_ftxj;}

	private String dkeh_ftxk = null;
	public void setDkeh_ftxk(String value) {  this.dkeh_ftxk = value; }
	public String getDkeh_ftxk() { return this.dkeh_ftxk;}

	private String dkeh_ftxl = null;
	public void setDkeh_ftxl(String value) {  this.dkeh_ftxl = value; }
	public String getDkeh_ftxl() { return this.dkeh_ftxl;}

	private String dkeh_ftxm = null;
	public void setDkeh_ftxm(String value) {  this.dkeh_ftxm = value; }
	public String getDkeh_ftxm() { return this.dkeh_ftxm;}

	//YM records
	private String dkeh_ym21 = null;
	public void setDkeh_ym21(String value) {  this.dkeh_ym21 = value; }
	public String getDkeh_ym21() { return this.dkeh_ym21;}

	private String dkeh_ym23 = null;
	public void setDkeh_ym23(String value) {  this.dkeh_ym23 = value; }
	public String getDkeh_ym23() { return this.dkeh_ym23;}

	private String dkeh_ymvp = null;
	public void setDkeh_ymvp(String value) {  this.dkeh_ymvp = value; }
	public String getDkeh_ymvp() { return this.dkeh_ymvp;}

	private String dkeh_ymd1 = null;
	public void setDkeh_ymd1(String value) {  this.dkeh_ymd1 = value; }
	public String getDkeh_ymd1() { return this.dkeh_ymd1;}

	private String dkeh_ymd2 = null;
	public void setDkeh_ymd2(String value) {  this.dkeh_ymd2 = value; }
	public String getDkeh_ymd2() { return this.dkeh_ymd2;}

	private String dkeh_ymd3 = null;
	public void setDkeh_ymd3(String value) {  this.dkeh_ymd3 = value; }
	public String getDkeh_ymd3() { return this.dkeh_ymd3;}

	private String dkeh_ymb1 = null;
	public void setDkeh_ymb1(String value) {  this.dkeh_ymb1 = value; }
	public String getDkeh_ymb1() { return this.dkeh_ymb1;}

	private String dkeh_ymb2 = null;
	public void setDkeh_ymb2(String value) {  this.dkeh_ymb2 = value; }
	public String getDkeh_ymb2() { return this.dkeh_ymb2;}

	private String dkeh_ymb3 = null;
	public void setDkeh_ymb3(String value) {  this.dkeh_ymb3 = value; }
	public String getDkeh_ymb3() { return this.dkeh_ymb3;}

	private String dkeh_ymb4 = null;
	public void setDkeh_ymb4(String value) {  this.dkeh_ymb4 = value; }
	public String getDkeh_ymb4() { return this.dkeh_ymb4;}

	private String dkeh_yme1 = null;
	public void setDkeh_yme1(String value) {  this.dkeh_yme1 = value; }
	public String getDkeh_yme1() { return this.dkeh_yme1;}

	private String dkeh_yme2 = null;
	public void setDkeh_yme2(String value) {  this.dkeh_yme2 = value; }
	public String getDkeh_yme2() { return this.dkeh_yme2;}

	private String dkeh_yme3 = null;
	public void setDkeh_yme3(String value) {  this.dkeh_yme3 = value; }
	public String getDkeh_yme3() { return this.dkeh_yme3;}

	/**
	 * Used for java reflection in other classes
	 * @return
	 * @throws Exception
	 */
	
	public List<Field> getFields() throws Exception{
		Class cl = Class.forName(this.getClass().getCanonicalName());
		Field[] fields = cl.getDeclaredFields();
		List<Field> list = Arrays.asList(fields);
		
		return list;
	}

}
