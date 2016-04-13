/**
 * 
 */
package no.systema.skat.skatimport.model.jsonjackson.topic;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;
import java.util.*;
import java.lang.reflect.Field;
/**
 * @author oscardelatorre
 * @date Jan 14, 2014
 * AS400 File: DKIH ; Format: DKIHR
 *
 */
public class JsonSkatImportSpecificTopicRecord extends JsonAbstractGrandFatherRecord{
	
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
		this.sumTotalAmountItemLinesStr = String.valueOf(this.sumTotalAmountItemLines);
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
	
	
	//in order to validate before a "send topic"
	private boolean validUpdate = false;
	public void setValidUpdate(boolean value) {  this.validUpdate = value; }
	public boolean isValidUpdate() {return this.validUpdate;}
	
	private String dkih_genb = "1";
	public void setDkih_genb(String value) {  this.dkih_genb = value; }
	public String getDkih_genb() {return this.dkih_genb;}
	
	//test indicator in production
	private String dkih_0035 = null;
	public void setDkih_0035(String value) {  this.dkih_0035 = value; }
	public String getDkih_0035() {return this.dkih_0035;}
		
	private String dkih_syav = null;
	public void setDkih_syav(String value) {  this.dkih_syav = value; }
	public String getDkih_syav() {return this.dkih_syav;}
	
	private String dkih_syop = null;
	public void setDkih_syop(String value) {  this.dkih_syop = value; }
	public String getDkih_syop() {return this.dkih_syop;}
	
	private String dkih_sysg = null;
	public void setDkih_sysg(String value) {  this.dkih_sysg = value; }
	public String getDkih_sysg() {return this.dkih_sysg;}
	
	private String dkih_syst = null;
	public void setDkih_syst(String value) {  this.dkih_syst = value; }
	public String getDkih_syst() {return this.dkih_syst;}
	
	private String dkih_sydt = null;
	public void setDkih_sydt(String value) {  this.dkih_sydt = value; }
	public String getDkih_sydt() {return this.dkih_sydt;}
	
	private String dkih_godt = null;
	public void setDkih_godt(String value) {  this.dkih_godt = value; }
	public String getDkih_godt() {return this.dkih_godt;}

	private String dkih_vadt = null;
	public void setDkih_vadt(String value) {  this.dkih_vadt = value; }
	public String getDkih_vadt() {return this.dkih_vadt;}
	
	private String dkih_sadt = null;
	public void setDkih_sadt(String value) {  this.dkih_sadt = value; }
	public String getDkih_sadt() {return this.dkih_sadt;}
	
	private String dkih_andt = null;
	public void setDkih_andt(String value) {  this.dkih_andt = value; }
	public String getDkih_andt() {return this.dkih_andt;}
	
	
	//--Tullid = ref.nr (som i Danmark föds hos tullverket. I Sverige skapar vi (Systema) detta nummer)
	private String dkih_07 = null;
	public void setDkih_07(String value) {  this.dkih_07 = value; }
	public String getDkih_07() {return this.dkih_07;}
	
	private String dkih_1004 = null;
	public void setDkih_1004(String value) {  this.dkih_1004 = value; }
	public String getDkih_1004() {return this.dkih_1004;}
	
	private String dkih_xref = null;
	public void setDkih_xref(String value) {  this.dkih_xref = value; }
	public String getDkih_xref() {return this.dkih_xref;}
	
	private String dkih_a = null;
	public void setDkih_a(String value) {  this.dkih_a = value; }
	public String getDkih_a() {return this.dkih_a;}
	
	private String dkih_dtm1 = null;
	public void setDkih_dtm1(String value) {  this.dkih_dtm1 = value; }
	public String getDkih_dtm1() {return this.dkih_dtm1;}
	
	
	private String dkih_dtm2 = null;
	public void setDkih_dtm2(String value) {  this.dkih_dtm2 = value; }
	public String getDkih_dtm2() {return this.dkih_dtm2;}
	
	
	
	private String dkih_ajou = null;
	public void setDkih_ajou(String value) {  this.dkih_ajou = value; }
	public String getDkih_ajou() {return this.dkih_ajou;}
	
	private String dkih_ensf = null;
	public void setDkih_ensf(String value) {  this.dkih_ensf = value; }
	public String getDkih_ensf() {return this.dkih_ensf;}
	
	private String dkih_bega = null;
	public void setDkih_bega(String value) {  this.dkih_bega = value; }
	public String getDkih_bega() {return this.dkih_bega;}
	
	private String dkih_begb = null;
	public void setDkih_begb(String value) {  this.dkih_begb = value; }
	public String getDkih_begb() {return this.dkih_begb;}
	
	private String dkih_begc = null;
	public void setDkih_begc(String value) {  this.dkih_begc = value; }
	public String getDkih_begc() {return this.dkih_begc;}
	
	private String dkih_begd = null;
	public void setDkih_begd(String value) {  this.dkih_begd = value; }
	public String getDkih_begd() {return this.dkih_begd;}
	
	private String dkih_r011 = null;
	public void setDkih_r011(String value) {  this.dkih_r011 = value; }
	public String getDkih_r011() {return this.dkih_r011;}

	private String dkih_r012 = null;
	public void setDkih_r012(String value) {  this.dkih_r012 = value; }
	public String getDkih_r012() {return this.dkih_r012;}

	private String dkih_aart = null;
	public void setDkih_aart(String value) {  this.dkih_aart = value; }
	public String getDkih_aart() {return this.dkih_aart;}

	//----Afsender
	private String dkih_avkn = null;
	public void setDkih_avkn(String value) {  this.dkih_avkn = value; }
	public String getDkih_avkn() {return this.dkih_avkn;}
	
	private String dkih_02a = null;
	public void setDkih_02a(String value) {  this.dkih_02a = value; }
	public String getDkih_02a() {return this.dkih_02a;}
	
	private String dkih_02b = null;
	public void setDkih_02b(String value) {  this.dkih_02b = value; }
	public String getDkih_02b() {return this.dkih_02b;}
	
	private String dkih_02c = null;
	public void setDkih_02c(String value) {  this.dkih_02c = value; }
	public String getDkih_02c() {return this.dkih_02c;}
	
	private String dkih_02d = null;
	public void setDkih_02d(String value) {  this.dkih_02d = value; }
	public String getDkih_02d() {return this.dkih_02d;}
	
	private String dkih_02e = null;
	public void setDkih_02e(String value) {  this.dkih_02e = value; }
	public String getDkih_02e() {return this.dkih_02e;}
	
	private String dkih_02f = null;
	public void setDkih_02f(String value) {  this.dkih_02f = value; }
	public String getDkih_02f() {return this.dkih_02f;}
	
	private String dkih_brut = null;
	public void setDkih_brut(String value) {  this.dkih_brut = value; }
	public String getDkih_brut() {return this.dkih_brut;}
	
	private String dkih_06 = null;
	public void setDkih_06(String value) {  this.dkih_06 = value; }
	public String getDkih_06() {return this.dkih_06;}
	
	private String dkih_07a = null;
	public void setDkih_07a(String value) {  this.dkih_07a = value; }
	public String getDkih_07a() {return this.dkih_07a;}
	
	private String dkih_07b = null;
	public void setDkih_07b(String value) {  this.dkih_07b = value; }
	public String getDkih_07b() {return this.dkih_07b;}
	
	private String dkih_07c = null;
	public void setDkih_07c(String value) {  this.dkih_07c = value; }
	public String getDkih_07c() {return this.dkih_07c;}
	
	private String dkih_07d = null;
	public void setDkih_07d(String value) {  this.dkih_07d = value; }
	public String getDkih_07d() {return this.dkih_07d;}
	
	
	//-----Modtager
	private String dkih_mokn = null;
	public void setDkih_mokn(String value) {  this.dkih_mokn = value; }
	public String getDkih_mokn() {return this.dkih_mokn;}
	
	private String dkih_08a = null;
	public void setDkih_08a(String value) {  this.dkih_08a = value; }
	public String getDkih_08a() {return this.dkih_08a;}
	
	private String dkih_08b = null;
	public void setDkih_08b(String value) {  this.dkih_08b = value; }
	public String getDkih_08b() {return this.dkih_08b;}
	
	private String dkih_08c = null;
	public void setDkih_08c(String value) {  this.dkih_08c = value; }
	public String getDkih_08c() {return this.dkih_08c;}
	
	private String dkih_08d = null;
	public void setDkih_08d(String value) {  this.dkih_08d = value; }
	public String getDkih_08d() {return this.dkih_08d;}
	
	private String dkih_08e = null;
	public void setDkih_08e(String value) {  this.dkih_08e = value; }
	public String getDkih_08e() {return this.dkih_08e;}
	
	private String dkih_08f = null;
	public void setDkih_08f(String value) {  this.dkih_08f = value; }
	public String getDkih_08f() {return this.dkih_08f;}
	
		

	private String dkih_rekn = null;
	public void setDkih_rekn(String value) {  this.dkih_rekn = value; }
	public String getDkih_rekn() {return this.dkih_rekn;}
	
	private String dkih_reeo = null;
	public void setDkih_reeo(String value) {  this.dkih_reeo = value; }
	public String getDkih_reeo() {return this.dkih_reeo;}

	//------Transportør
	private String dkih_trkn = null;
	public void setDkih_trkn(String value) {  this.dkih_trkn = value; }
	public String getDkih_trkn() {return this.dkih_trkn;}
	
	private String dkih_treo = null;
	public void setDkih_treo(String value) {  this.dkih_treo = value; }
	public String getDkih_treo() {return this.dkih_treo;}

	private String dkih_trna = null;
	public void setDkih_trna(String value) {  this.dkih_trna = value; }
	public String getDkih_trna() {return this.dkih_trna;}

	private String dkih_trga = null;
	public void setDkih_trga(String value) {  this.dkih_trga = value; }
	public String getDkih_trga() {return this.dkih_trga;}

	private String dkih_trpo = null;
	public void setDkih_trpo(String value) {  this.dkih_trpo = value; }
	public String getDkih_trpo() {return this.dkih_trpo;}
	
	private String dkih_trby = null;
	public void setDkih_trby(String value) {  this.dkih_trby = value; }
	public String getDkih_trby() {return this.dkih_trby;}
	
	private String dkih_trlk = null;
	public void setDkih_trlk(String value) {  this.dkih_trlk = value; }
	public String getDkih_trlk() {return this.dkih_trlk;}
	
	//------Underettes
	private String dkih_nikn = null;
	public void setDkih_nikn(String value) {  this.dkih_nikn = value; }
	public String getDkih_nikn() {return this.dkih_nikn;}
	
	private String dkih_nieo = null;
	public void setDkih_nieo(String value) {  this.dkih_nieo = value; }
	public String getDkih_nieo() {return this.dkih_nieo;}

	private String dkih_nina = null;
	public void setDkih_nina(String value) {  this.dkih_nina = value; }
	public String getDkih_nina() {return this.dkih_nina;}

	private String dkih_niga = null;
	public void setDkih_niga(String value) {  this.dkih_niga = value; }
	public String getDkih_niga() {return this.dkih_niga;}

	private String dkih_nipo = null;
	public void setDkih_nipo(String value) {  this.dkih_nipo = value; }
	public String getDkih_nipo() {return this.dkih_nipo;}
	
	private String dkih_niby = null;
	public void setDkih_niby(String value) {  this.dkih_niby = value; }
	public String getDkih_niby() {return this.dkih_niby;}
	
	private String dkih_nilk = null;
	public void setDkih_nilk(String value) {  this.dkih_nilk = value; }
	public String getDkih_nilk() {return this.dkih_nilk;}
		
	private String dkih_12 = null;
	public void setDkih_12(String value) {  this.dkih_12 = value; }
	public String getDkih_12() { return this.dkih_12;}
	
	private String dkih_12e = null;
	public void setDkih_12e(String value) {  this.dkih_12e = value; }
	public String getDkih_12e() { return this.dkih_12e;}
	
	private String dkih_14a = null;
	public void setDkih_14a(String value) {  this.dkih_14a = value; }
	public String getDkih_14a() { return this.dkih_14a;}
	
	private String dkih_14b = null;
	public void setDkih_14b(String value) {  this.dkih_14b = value; }
	public String getDkih_14b() { return this.dkih_14b;}
	
	private String dkih_15 = null;
	public void setDkih_15(String value) {  this.dkih_15 = value; }
	public String getDkih_15() { return this.dkih_15;}
	
	private String dkih_s17 = null;
	public void setDkih_s17(String value) {  this.dkih_s17 = value; }
	public String getDkih_s17() { return this.dkih_s17;}
	
	private String dkih_s18 = null;
	public void setDkih_s18(String value) {  this.dkih_s18 = value; }
	public String getDkih_s18() { return this.dkih_s18;}
	
	private String dkih_s28 = null;
	public void setDkih_s28(String value) {  this.dkih_s28 = value; }
	public String getDkih_s28() { return this.dkih_s28;}
	
	private String dkih_s32 = null;
	public void setDkih_s32(String value) {  this.dkih_s32 = value; }
	public String getDkih_s32() { return this.dkih_s32;}
	
	private String dkih_181 = null;
	public void setDkih_181(String value) {  this.dkih_181 = value; }
	public String getDkih_181() {return this.dkih_181;}
	
	private String dkih_18a = null;
	public void setDkih_18a(String value) {  this.dkih_18a = value; }
	public String getDkih_18a() {return this.dkih_18a;}
	
	private String dkih_211 = null;
	public void setDkih_211(String value) {  this.dkih_211 = value; }
	public String getDkih_211() { return this.dkih_211;}
	
	private String dkih_221 = null;
	public void setDkih_221(String value) {  this.dkih_221 = value; }
	public String getDkih_221() { return this.dkih_221;}

	private String dkih_221b = null;
	public void setDkih_221b(String value) {  this.dkih_221b = value; }
	public String getDkih_221b() { return this.dkih_221b;}
	
	private String dkih_221c = null;
	public void setDkih_221c(String value) {  this.dkih_221c = value; }
	public String getDkih_221c() { return this.dkih_221c;}
	
	private String dkih_222 = null;
	public void setDkih_222(String value) {  this.dkih_222 = value; }
	public String getDkih_222() { return this.dkih_222;}
	
	private Double dkih_222Dbl = 0.00D;
	public Double getDkih_222Dbl() {
		Double retval = dkih_222Dbl;
		try{
			if(this.dkih_222!=null){
				retval = Double.parseDouble(this.dkih_222.replace(",", "."));
			}
		}catch(Exception e){
			//Let it go
		}
		return retval;
	}
	
	private String dkih_25 = null;
	public void setDkih_25(String value) {  this.dkih_25 = value; }
	public String getDkih_25() { return this.dkih_25;}
	
	private String dkih_26 = null;
	public void setDkih_26(String value) {  this.dkih_26 = value; }
	public String getDkih_26() { return this.dkih_26;}
	
	private String dkih_s29 = null;
	public void setDkih_s29(String value) {  this.dkih_s29 = value; }
	public String getDkih_s29() { return this.dkih_s29;}
	
	private String dkih_201 = null;
	public void setDkih_201(String value) {  this.dkih_201 = value; }
	public String getDkih_201() { return this.dkih_201;}
	
	private String dkih_202 = null;
	public void setDkih_202(String value) {  this.dkih_202 = value; }
	public String getDkih_202() { return this.dkih_202;}
	
	private String dkih_301 = null;
	public void setDkih_301(String value) {  this.dkih_301 = value; }
	public String getDkih_301() { return this.dkih_301;}
	
	private String dkih_302 = null;
	public void setDkih_302(String value) {  this.dkih_302 = value; }
	public String getDkih_302() { return this.dkih_302;}
	
	private String dkih_303 = null;
	public void setDkih_303(String value) {  this.dkih_303 = value; }
	public String getDkih_303() { return this.dkih_303;}

	private String dkih_304 = null;
	public void setDkih_304(String value) {  this.dkih_304 = value; }
	public String getDkih_304() { return this.dkih_304;}

	private String dkih_49 = null;
	public void setDkih_49(String value) {  this.dkih_49 = value; }
	public String getDkih_49() { return this.dkih_49;}

	//Invoice records only internally and not for SKAT (AS400 db and not EDIFACT outbound)
	private String dkih_28a = null;
	public void setDkih_28a(String value) {  this.dkih_28a = value; }
	public String getDkih_28a() { return this.dkih_28a;}

	private String dkih_28b = null;
	public void setDkih_28b(String value) {  this.dkih_28b = value; }
	public String getDkih_28b() { return this.dkih_28b;}

	private String dkih_28c = null;
	public void setDkih_28c(String value) {  this.dkih_28c = value; }
	public String getDkih_28c() { return this.dkih_28c;}

	
	//---------------------------
	//TOLDVÆRDIDEKLARATION fields
	//---------------------------
	private String dkih_t04a = null;
	public void setDkih_t04a(String value) {  this.dkih_t04a = value; }
	public String getDkih_t04a() { return this.dkih_t04a;}
	
	private String dkih_t04b = null;
	public void setDkih_t04b(String value) {  this.dkih_t04b = value; }
	public String getDkih_t04b() { return this.dkih_t04b;}
	
	private String dkih_t05a = null;
	public void setDkih_t05a(String value) {  this.dkih_t05a = value; }
	public String getDkih_t05a() { return this.dkih_t05a;}
	
	private String dkih_t05b = null;
	public void setDkih_t05b(String value) {  this.dkih_t05b = value; }
	public String getDkih_t05b() { return this.dkih_t05b;}
	
	private String dkih_t06a = null;
	public void setDkih_t06a(String value) {  this.dkih_t06a = value; }
	public String getDkih_t06a() { return this.dkih_t06a;}
	
	private String dkih_t06b = null;
	public void setDkih_t06b(String value) {  this.dkih_t06b = value; }
	public String getDkih_t06b() { return this.dkih_t06b;}
	
	private String dkih_t07a = null;
	public void setDkih_t07a(String value) {  this.dkih_t07a = value; }
	public String getDkih_t07a() { return this.dkih_t07a;}
	
	private String dkih_t07b = null;
	public void setDkih_t07b(String value) {  this.dkih_t07b = value; }
	public String getDkih_t07b() { return this.dkih_t07b;}
	
	private String dkih_t07c = null;
	public void setDkih_t07c(String value) {  this.dkih_t07c = value; }
	public String getDkih_t07c() { return this.dkih_t07c;}

	private String dkih_t07d = null;
	public void setDkih_t07d(String value) {  this.dkih_t07d = value; }
	public String getDkih_t07d() { return this.dkih_t07d;}
	
	private String dkih_t08a = null;
	public void setDkih_t08a(String value) {  this.dkih_t08a = value; }
	public String getDkih_t08a() { return this.dkih_t08a;}
	
	private String dkih_t08b = null;
	public void setDkih_t08b(String value) {  this.dkih_t08b = value; }
	public String getDkih_t08b() { return this.dkih_t08b;}
	
	private String dkih_t08c = null;
	public void setDkih_t08c(String value) {  this.dkih_t08c = value; }
	public String getDkih_t08c() { return this.dkih_t08c;}
	
	private String dkih_t09a = null;
	public void setDkih_t09a(String value) {  this.dkih_t09a = value; }
	public String getDkih_t09a() { return this.dkih_t09a;}
	
	private String dkih_t09b = null;
	public void setDkih_t09b(String value) {  this.dkih_t09b = value; }
	public String getDkih_t09b() { return this.dkih_t09b;}
		

	
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
