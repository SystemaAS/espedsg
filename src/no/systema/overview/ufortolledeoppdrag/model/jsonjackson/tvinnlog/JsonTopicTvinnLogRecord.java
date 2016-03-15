/**
 * 
 */
package no.systema.overview.ufortolledeoppdrag.model.jsonjackson.tvinnlog;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author oscardelatorre
 * @date Oct 23, 2013
 *
 */
public class JsonTopicTvinnLogRecord {
	
	
	private String mavd = null;
	public void setMavd(String value) {  this.mavd = value; }
	public String getMavd() {return this.mavd;}
	
	private String mtdn = null;
	public void setMtdn(String value) {  this.mtdn = value; }
	public String getMtdn() {return this.mtdn;}
	
	private String msn = null;
	public void setMsn(String value) {  this.msn = value; }
	public String getMsn() {return this.msn;}
	
	private String mmn = null;
	public void setMmn(String value) {  this.mmn = value; }
	public String getMmn() {return this.mmn;}
	
	private String msr = null;
	public void setMsr(String value) {  this.msr = value; }
	public String getMsr() {return this.msr;}
	
	private String mst = null;
	public void setMst(String value) {  this.mst = value; }
	public String getMst() {return this.mst;}
	
	private String m0004 = null;
	public void setM0004(String value) {  this.m0004 = value; }
	public String getM0004() {return this.m0004;}
	
	private String m0010 = null;
	public void setM0010(String value) {  this.m0010 = value; }
	public String getM0010() {return this.m0010;}
	
	private String mven = null;
	public void setMven(String value) {  this.mven = value; }
	public String getMven() {return this.mven;}
	
	private String m0068 = null;
	public void setM0068 (String value) {  this.m0068 = value; }
	public String getM0068() {return this.m0068;}
	
	private String m0068A = null;
	public void setM0068A (String value) {  this.m0068A = value; }
	public String getM0068A() {return this.m0068A;}

	private String m0068B = null;
	public void setM0068B (String value) {  this.m0068B = value; }
	public String getM0068B() {return this.m0068B;}
	
	private String m0068C = null;
	public void setM0068C (String value) {  this.m0068C = value; }
	public String getM0068C() {return this.m0068C;}
	
	private String m0035 = null;
	public void setM0035(String value) {  this.m0035 = value; }
	public String getM0035() {return this.m0035;}
	
	private String m0065 = null;
	public void setM0065(String value) {  this.m0065 = value; }
	public String getM0065() {return this.m0065;}
	
	private String m1225 = null;
	public void setM1225(String value) {  this.m1225 = value; }
	public String getM1225() {return this.m1225;}
	
	private String m3039E = null;
	public void setM3039E(String value) {  this.m3039E = value; }
	public String getM3039E() {return this.m3039E;}
	
	private String m1n07 = null;
	public void setM1n07(String value) {  this.m1n07 = value; }
	public String getM1n07() {return this.m1n07;}
	
	private String mdt = null;
	public void setMdt(String value) {  this.mdt = value; }
	public String getMdt() {return this.mdt;}
	
	private String mtm = null;
	public void setMtm(String value) {  this.mtm = value; }
	public String getMtm() {return this.mtm;}
	
	private String wtxt = null;
	public void setWtxt(String value) {  this.wtxt = value; }
	public String getWtxt() {return this.wtxt;}
	
	private String wmore = null;
	public void setWmore(String value) {  this.wmore = value; }
	public String getWmore() {return this.wmore;}
	
	private String wurl = null;
	public void setWurl(String value) {  this.wurl = value; }
	public String getWurl() {return this.wurl;}
	
	
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
	
	
	/* SPEC for all fields...
	 
	Jeg ser at CB i sin JSON har valgt JSON-feltnavn etter databasefeltnavnene fra fila EDIM (EDI meldings-logg) i SYSPED, men plukket ut kun de feltene han trengte i TDS ... 
For dokumentasjone legger jeg med fil/feltbeskrivelse - de med  CB bak fikk du allerede. 
Jeg har nå supplert med ALLE ANDRE for å være mest mulig generell for framtidig gjenbruk av TJGE005   
(men noen av de vil nok aldri skulle benyttes:=) 

Har lagt de inn i rekkefølge rett foran wtxt-tagen.. (wtxt og videre er hentet fra andre databasefiler...) 

merk 
dato/sekvens/versjonsnr = 
M0068A     Dekl.Dato                              203  210     8   8   0 S 
M0068B     Dekl.Sekvensnr                         211  216     6   6   0 S 
M0068C     Dekl.Versjonsnr                        217  218     2   2   0 S 


Library: SYENDPTF    File: EDIM        Format: EDIMR                         
Description: EDI  Message-log                                                 
Field      Description                       Start/end_pos  Byte Dig Dec Type 
M0004      Interchange_ID  Sender                   1   35    35         A             CB 
M0010      Interchange_ID  Receiver                36   70    35         A                  CB 
M0035      Testindicator                           71   71     1         A                  CB 
M0062      Message_reference                       72   85    14         A                   
M0065      Message_ID                              86   91     6         A                   CB 
M0068      Common Access Ref                       92  126    35         A                  CB 
M1001      Message name                           127  129     3         A                           
M1004      Message number                         130  164    35         A                   
M1225      Message Function                       165  167     3         A                   CB 
MSN        Interchange_no.                        168  176     9   9   0 S                   CB 
MMN        Message_no.                            177  185     9   9   0 S                   CB 
MSR        Send/Recive                            186  186     1         A                   CB 
MST        Status                                 187  187     1         A                CB 
MDT        Status_date                            188  195     8   8   0 S                CB 
MTM        Status_time                            196  201     6   6   0 S                 CB 
MVEN       Delay_code                             202  202     1         A                CB 
M0068A     Dekl.Dato                              203  210     8   8   0 S 
M0068B     Dekl.Sekvensnr                         211  216     6   6   0 S 
M0068C     Dekl.Versjonsnr                        217  218     2   2   0 S 
M0068D     Dekl.Dato                              219  226     8   8   0 S 
M0068E     Dekl.Sekvensnr                         227  232     6   6   0 S 
M0068F     Dekl.Versjonsnr                        233  234     2   2   0 S 
M2005B     Ønsket  Behandlingsdato                235  242     8   8   0 S 
M3039D     Deklarantens  Referanse                243  250     8   8   0 S 
M3039E     Ekspidisjons  Enhet                    251  256     6   6   0 S 
M5004D     Depositum  Beløp                       257  266    10  10   0 S 
M1N07      Meldingsfunksjon                       267  269     3         A 
M1N08      Meldingsfunksjon  Korrigert            270  271     2         A 
M9N01      Ekspidisjons  Prioritet                272  272     1   1   0 S 
MAVD       Avdeling                               273  276     4   4   0 S                 CB 
MTDN       Tolldeklarasjonsnr                     277  283     7   7   0 S                 CB 
MFFBNR     Fraktbrevnummer                        284  286     3   3   0 S 
--------------------------  L a s t   P a g e   ---------------------------               


 
	 
	*/ 


}
