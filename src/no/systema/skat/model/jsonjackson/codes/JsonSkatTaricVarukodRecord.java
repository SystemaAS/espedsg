/**
 * 
 */
package no.systema.skat.model.jsonjackson.codes;

/**
 * @author oscardelatorre
 * @date  Feb 4, 2014
 * 
 */
public class JsonSkatTaricVarukodRecord {
	private String dktara02 = null;
	public void setDktara02(String value){ this.dktara02 = value;}
	public String getDktara02(){ return this.dktara02; }
	
	private String dktara63 = null;
	public void setDktara63(String value){ this.dktara63 = value;}
	public String getDktara63(){ return this.dktara63; }
	
	private String dktara64 = null;
	public void setDktara64(String value){ this.dktara64 = value;}
	public String getDktara64(){ return this.dktara64; }
	
	private String dktara15 = "0.00";
	public void setDktara15(String value){ this.dktara15 = value;}
	public String getDktara15(){ return this.dktara15; }
	
	/*
	private String dktara15Formatted = "0.00";
	public String getDktara15Formatted(){
		if(this.getDktara15()!=null && !"".equals(this.getDktara15())){
			  if(this.getDktara15().length()==7){
				  String strInteger = this.getDktara15().substring(0,4);
				  String strDecimals = this.getDktara15().substring(3);
				  dktara15Formatted = strInteger + "." + strDecimals;
				  //logger.info("strDktara15:" + strDktara15);
			  }
		  }
		  this.setDktara15(dktara15Formatted);
		  return 
	}
	*/
	  
	  
	/*
	private String dkvs_txtk = null;
	public void setDkvs_txtk(String value){ this.dkvs_txtk = value;}
	public String getDkvs_txtk(){ return this.dkvs_txtk; }
	*/
}
