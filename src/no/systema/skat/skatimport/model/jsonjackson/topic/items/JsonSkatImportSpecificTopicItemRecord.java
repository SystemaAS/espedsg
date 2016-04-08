/**
 * 
 */
package no.systema.skat.skatimport.model.jsonjackson.topic.items;

import java.lang.reflect.Field;
import java.util.*;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;

/**
 * 
 * @author oscardelatorre
 * @date Jan 28, 2014
 * 
 *
 */
public class JsonSkatImportSpecificTopicItemRecord  extends JsonAbstractGrandFatherRecord   {
	
	private String debugPrintlnAjax = null;
	public void setDebugPrintlnAjax(String value) {  this.debugPrintlnAjax = value; }
	public String getDebugPrintlnAjax() {return this.debugPrintlnAjax;}
	
	private boolean validTolltariff = true;
	public void setValidTolltariff(boolean value) {  this.validTolltariff = value; }
	public boolean isValidTolltariff() {return this.validTolltariff;}
	
	
	private Integer sum_of_dkiv_313 = 0;
	public Integer getSum_of_dkiv_313() {
		int sum = 0;
		try{
			if(this.dkiv_313a!=null && !"".equals(this.dkiv_313a)){
				sum += Integer.parseInt(this.dkiv_313a);
			}
			if(this.dkiv_313b!=null && !"".equals(this.dkiv_313b)){
				sum += Integer.parseInt(this.dkiv_313b);
			}
			if(this.dkiv_313c!=null && !"".equals(this.dkiv_313c)){
				sum += Integer.parseInt(this.dkiv_313c);
			}
			if(this.dkiv_313d!=null && !"".equals(this.dkiv_313d)){
				sum += Integer.parseInt(this.dkiv_313d);
			}
			if(this.dkiv_313e!=null && !"".equals(this.dkiv_313e)){
				sum += Integer.parseInt(this.dkiv_313e);
			}
			this.sum_of_dkiv_313 = sum;
			
		}catch(NumberFormatException e){
			//continue
		}
		return this.sum_of_dkiv_313;
	}
	
	//Aux. attribute to pass some header values into a Validator for Items.
	/*private String header_svih_cont = null;
	public void setHeader_svih_cont(String value) {  this.header_svih_cont = value; }
	public String getHeader_svih_cont() {return this.header_svih_cont;}
	*/
	
	//Aux. attribute to pass some header values into a Validator for Items. (Angivelsesart)
	private String header_dkih_aart = null;
	public void setHeader_dkih_aart(String value) {  this.header_dkih_aart = value; }
	public String getHeader_dkih_aart() {return this.header_dkih_aart;}
	
	//Aux. attribute to validate mangdenhet
	/*private String extraMangdEnhet = null;
	public void setExtraMangdEnhet(String value) {  this.extraMangdEnhet = value; }
	public String getExtraMangdEnhet() {return this.extraMangdEnhet;}
	*/
	
	//Aspects on copy dialog box in order to validate
	private String copyLineStartLineNr = null;
	public void setCopyLineStartLineNr(String value) {  this.copyLineStartLineNr = value; }
	public String getCopyLineStartLineNr() {return this.copyLineStartLineNr;}
	
	private String copyLineEndLineNr = "99"; //default
	public void setCopyLineEndLineNr(String value) {  this.copyLineEndLineNr = value; }
	public String getCopyLineEndLineNr() {return this.copyLineEndLineNr;}

	
	private String dkiv_syav = null;
	public void setDkiv_syav(String value) {  this.dkiv_syav = value; }
	public String getDkiv_syav() {return this.dkiv_syav;}
	
	private String dkiv_syop = null;
	public void setDkiv_syop(String value) {  this.dkiv_syop = value; }
	public String getDkiv_syop() {return this.dkiv_syop;}
	
	private String dkiv_syli = null;
	public void setDkiv_syli(String value) {  this.dkiv_syli = value; }
	public String getDkiv_syli() {return this.dkiv_syli;}
	
	
	private String dkiv_311a = null;
	public void setDkiv_311a(String value) {  this.dkiv_311a = value; }
	public String getDkiv_311a() {return this.dkiv_311a;}
	
	private String dkiv_311b = null;
	public void setDkiv_311b(String value) {  this.dkiv_311b = value; }
	public String getDkiv_311b() {return this.dkiv_311b;}
	
	private String dkiv_311c = null;
	public void setDkiv_311c(String value) {  this.dkiv_311c = value; }
	public String getDkiv_311c() {return this.dkiv_311c;}
	
	private String dkiv_311d = null;
	public void setDkiv_311d(String value) {  this.dkiv_311d = value; }
	public String getDkiv_311d() {return this.dkiv_311d;}
	
	private String dkiv_311e = null;
	public void setDkiv_311e(String value) {  this.dkiv_311e = value; }
	public String getDkiv_311e() {return this.dkiv_311e;}
	
	private String dkiv_312a = null;
	public void setDkiv_312a(String value) {  this.dkiv_312a = value; }
	public String getDkiv_312a() {return this.dkiv_312a;}
	
	private String dkiv_312b = null;
	public void setDkiv_312b(String value) {  this.dkiv_312b = value; }
	public String getDkiv_312b() {return this.dkiv_312b;}
	
	private String dkiv_312c = null;
	public void setDkiv_312c(String value) {  this.dkiv_312c = value; }
	public String getDkiv_312c() {return this.dkiv_312c;}
	
	private String dkiv_312d = null;
	public void setDkiv_312d(String value) {  this.dkiv_312d = value; }
	public String getDkiv_312d() {return this.dkiv_312d;}
	
	private String dkiv_312e = null;
	public void setDkiv_312e(String value) {  this.dkiv_312e = value; }
	public String getDkiv_312e() {return this.dkiv_312e;}
	
	
	
	private String dkiv_313a = "0";
	public void setDkiv_313a(String value) {
		if(value==null || "".equals(value)){
			this.dkiv_313a = "0";
		}else{
			this.dkiv_313a = value;
		}
	}
	public String getDkiv_313a() {
		if(this.dkiv_313a==null || "".equals(this.dkiv_313a)){
			return "0";
		}else{
			return this.dkiv_313a;
		}
	}
	
	
	private String dkiv_313b = null;
	public void setDkiv_313b(String value) {  this.dkiv_313b = value; }
	public String getDkiv_313b() {return this.dkiv_313b;}
	
	private String dkiv_313c = null;
	public void setDkiv_313c(String value) {  this.dkiv_313c = value; }
	public String getDkiv_313c() {return this.dkiv_313c;}
	
	private String dkiv_313d = null;
	public void setDkiv_313d(String value) {  this.dkiv_313d = value; }
	public String getDkiv_313d() {return this.dkiv_313d;}
	
	private String dkiv_313e = null;
	public void setDkiv_313e(String value) {  this.dkiv_313e = value; }
	public String getDkiv_313e() {return this.dkiv_313e;}
	
	
	private String dkiv_314a = null;
	public void setDkiv_314a(String value) {  this.dkiv_314a = value; }
	public String getDkiv_314a() {return this.dkiv_314a;}
	
	private String dkiv_314b = null;
	public void setDkiv_314b(String value) {  this.dkiv_314b = value; }
	public String getDkiv_314b() {return this.dkiv_314b;}
	
	private String dkiv_314c = null;
	public void setDkiv_314c(String value) {  this.dkiv_314c = value; }
	public String getDkiv_314c() {return this.dkiv_314c;}
	
	private String dkiv_314d = null;
	public void setDkiv_314d(String value) {  this.dkiv_314d = value; }
	public String getDkiv_314d() {return this.dkiv_314d;}
	
	private String dkiv_314e = null;
	public void setDkiv_314e(String value) {  this.dkiv_314e = value; }
	public String getDkiv_314e() {return this.dkiv_314e;}
	
	private String dkiv_315a = null;
	public void setDkiv_315a(String value) {  this.dkiv_315a = value; }
	public String getDkiv_315a() {return this.dkiv_315a;}
	
	private String dkiv_315b = null;
	public void setDkiv_315b(String value) {  this.dkiv_315b = value; }
	public String getDkiv_315b() {return this.dkiv_315b;}
	
	private String dkiv_315c = null;
	public void setDkiv_315c(String value) {  this.dkiv_315c = value; }
	public String getDkiv_315c() {return this.dkiv_315c;}
	
	private String dkiv_315d = null;
	public void setDkiv_315d(String value) {  this.dkiv_315d = value; }
	public String getDkiv_315d() {return this.dkiv_315d;}
	
	private String dkiv_315e = null;
	public void setDkiv_315e(String value) {  this.dkiv_315e = value; }
	public String getDkiv_315e() {return this.dkiv_315e;}
	
	
	private String dkiv_32 = null;
	public void setDkiv_32(String value) {  this.dkiv_32 = value; }
	public String getDkiv_32() { return this.dkiv_32;}
	
	private String dkiv_331 = null;
	public void setDkiv_331(String value) {  this.dkiv_331 = value; }
	public String getDkiv_331() { return this.dkiv_331;}
	
	private String dkiv_332 = null;
	public void setDkiv_332(String value) {  this.dkiv_332 = value; }
	public String getDkiv_332() { return this.dkiv_332;}
	
	private String dkiv_333 = null;
	public void setDkiv_333(String value) {  this.dkiv_333 = value; }
	public String getDkiv_333() { return this.dkiv_333;}
	
	
	private String dkiv_sikk = null;
	public void setDkiv_sikk(String value) {  this.dkiv_sikk = value; }
	public String getDkiv_sikk() { return this.dkiv_sikk;}
	
	private String dkiv_34 = null;
	public void setDkiv_34(String value) {  this.dkiv_34 = value; }
	public String getDkiv_34() { return this.dkiv_34;}
	
	private String dkiv_35 = null;
	public void setDkiv_35(String value) {  this.dkiv_35 = value; }
	public String getDkiv_35() { return this.dkiv_35;}
	
	private String dkiv_36 = null;
	public void setDkiv_36(String value) {  this.dkiv_36 = value; }
	public String getDkiv_36() { return this.dkiv_36;}
	
	private String dkiv_37 = null;
	public void setDkiv_37(String value) {  this.dkiv_37 = value; }
	public String getDkiv_37() { return this.dkiv_37;}
	
	private String dkiv_38 = null;
	public void setDkiv_38(String value) {  this.dkiv_38 = value; }
	public String getDkiv_38() { return this.dkiv_38;}
	
	private String dkiv_39 = null;
	public void setDkiv_39(String value) {  this.dkiv_39 = value; }
	public String getDkiv_39() { return this.dkiv_39;}
	
	private String dkiv_s27 = null;
	public void setDkiv_s27(String value) {  this.dkiv_s27 = value; }
	public String getDkiv_s27() { return this.dkiv_s27;}
	
	private String dkiv_god4 = null;
	public void setDkiv_god4(String value) {  this.dkiv_god4 = value; }
	public String getDkiv_god4() { return this.dkiv_god4;}
	
	private String dkiv_401a = null;
	public void setDkiv_401a(String value) {  this.dkiv_401a = value; }
	public String getDkiv_401a() { return this.dkiv_401a; }
	private String dkiv_402a = null;
	public void setDkiv_402a(String value) {  this.dkiv_402a = value; }
	public String getDkiv_402a() { return this.dkiv_402a; }
	private String dkiv_403a = null;
	public void setDkiv_403a(String value) {  this.dkiv_403a = value; }
	public String getDkiv_403a() { return this.dkiv_403a; }

	private String dkiv_401b = null;
	public void setDkiv_401b(String value) {  this.dkiv_401b = value; }
	public String getDkiv_401b() { return this.dkiv_401b; }
	private String dkiv_402b = null;
	public void setDkiv_402b(String value) {  this.dkiv_402b = value; }
	public String getDkiv_402b() { return this.dkiv_402b; }
	private String dkiv_403b = null;
	public void setDkiv_403b(String value) {  this.dkiv_403b = value; }
	public String getDkiv_403b() { return this.dkiv_403b; }
	
	private String dkiv_401c = null;
	public void setDkiv_401c(String value) {  this.dkiv_401c = value; }
	public String getDkiv_401c() { return this.dkiv_401c; }
	private String dkiv_402c = null;
	public void setDkiv_402c(String value) {  this.dkiv_402c = value; }
	public String getDkiv_402c() { return this.dkiv_402c; }
	private String dkiv_403c = null;
	public void setDkiv_403c(String value) {  this.dkiv_403c = value; }
	public String getDkiv_403c() { return this.dkiv_403c; }

	private String dkiv_401d = null;
	public void setDkiv_401d(String value) {  this.dkiv_401d = value; }
	public String getDkiv_401d() { return this.dkiv_401d; }
	private String dkiv_402d = null;
	public void setDkiv_402d(String value) {  this.dkiv_402d = value; }
	public String getDkiv_402d() { return this.dkiv_402d; }
	private String dkiv_403d = null;
	public void setDkiv_403d(String value) {  this.dkiv_403d = value; }
	public String getDkiv_403d() { return this.dkiv_403d; }

	private String dkiv_401e = null;
	public void setDkiv_401e(String value) {  this.dkiv_401e = value; }
	public String getDkiv_401e() { return this.dkiv_401e; }
	private String dkiv_402e = null;
	public void setDkiv_402e(String value) {  this.dkiv_402e = value; }
	public String getDkiv_402e() { return this.dkiv_402e; }
	private String dkiv_403e = null;
	public void setDkiv_403e(String value) {  this.dkiv_403e = value; }
	public String getDkiv_403e() { return this.dkiv_403e; }

	private String dkiv_401f = null;
	public void setDkiv_401f(String value) {  this.dkiv_401f = value; }
	public String getDkiv_401f() { return this.dkiv_401f; }
	private String dkiv_402f = null;
	public void setDkiv_402f(String value) {  this.dkiv_402f = value; }
	public String getDkiv_402f() { return this.dkiv_402f; }
	private String dkiv_403f = null;
	public void setDkiv_403f(String value) {  this.dkiv_403f = value; }
	public String getDkiv_403f() { return this.dkiv_403f; }

	private String dkiv_401g = null;
	public void setDkiv_401g(String value) {  this.dkiv_401g = value; }
	public String getDkiv_401g() { return this.dkiv_401g; }
	private String dkiv_402g = null;
	public void setDkiv_402g(String value) {  this.dkiv_402g = value; }
	public String getDkiv_402g() { return this.dkiv_402g; }
	private String dkiv_403g = null;
	public void setDkiv_403g(String value) {  this.dkiv_403g = value; }
	public String getDkiv_403g() { return this.dkiv_403g; }

	private String dkiv_401h = null;
	public void setDkiv_401h(String value) {  this.dkiv_401h = value; }
	public String getDkiv_401h() { return this.dkiv_401h; }
	private String dkiv_402h = null;
	public void setDkiv_402h(String value) {  this.dkiv_402h = value; }
	public String getDkiv_402h() { return this.dkiv_402h; }
	private String dkiv_403h = null;
	public void setDkiv_403h(String value) {  this.dkiv_403h = value; }
	public String getDkiv_403h() { return this.dkiv_403h; }

	private String dkiv_401i = null;
	public void setDkiv_401i(String value) {  this.dkiv_401i = value; }
	public String getDkiv_401i() { return this.dkiv_401i; }
	private String dkiv_402i = null;
	public void setDkiv_402i(String value) {  this.dkiv_402i = value; }
	public String getDkiv_402i() { return this.dkiv_402i; }
	private String dkiv_403i = null;
	public void setDkiv_403i(String value) {  this.dkiv_403i = value; }
	public String getDkiv_403i() { return this.dkiv_403i; }

	private String dkiv_401j = null;
	public void setDkiv_401j(String value) {  this.dkiv_401j = value; }
	public String getDkiv_401j() { return this.dkiv_401j; }
	private String dkiv_402j = null;
	public void setDkiv_402j(String value) {  this.dkiv_402j = value; }
	public String getDkiv_402j() { return this.dkiv_402j; }
	private String dkiv_403j = null;
	public void setDkiv_403j(String value) {  this.dkiv_403j = value; }
	public String getDkiv_403j() { return this.dkiv_403j; }

	private String dkiv_401k = null;
	public void setDkiv_401k(String value) {  this.dkiv_401k = value; }
	public String getDkiv_401k() { return this.dkiv_401k; }
	private String dkiv_402k = null;
	public void setDkiv_402k(String value) {  this.dkiv_402k = value; }
	public String getDkiv_402k() { return this.dkiv_402k; }
	private String dkiv_403k = null;
	public void setDkiv_403k(String value) {  this.dkiv_403k = value; }
	public String getDkiv_403k() { return this.dkiv_403k; }

	private String dkiv_401l = null;
	public void setDkiv_401l(String value) {  this.dkiv_401l = value; }
	public String getDkiv_401l() { return this.dkiv_401l; }
	private String dkiv_402l = null;
	public void setDkiv_402l(String value) {  this.dkiv_402l = value; }
	public String getDkiv_402l() { return this.dkiv_402l; }
	private String dkiv_403l = null;
	public void setDkiv_403l(String value) {  this.dkiv_403l = value; }
	public String getDkiv_403l() { return this.dkiv_403l; }

	private String dkiv_401m = null;
	public void setDkiv_401m(String value) {  this.dkiv_401m = value; }
	public String getDkiv_401m() { return this.dkiv_401m; }
	private String dkiv_402m = null;
	public void setDkiv_402m(String value) {  this.dkiv_402m = value; }
	public String getDkiv_402m() { return this.dkiv_402m; }
	private String dkiv_403m = null;
	public void setDkiv_403m(String value) {  this.dkiv_403m = value; }
	public String getDkiv_403m() { return this.dkiv_403m; }

	private String dkiv_401n = null;
	public void setDkiv_401n(String value) {  this.dkiv_401n = value; }
	public String getDkiv_401n() { return this.dkiv_401n; }
	private String dkiv_402n = null;
	public void setDkiv_402n(String value) {  this.dkiv_402n = value; }
	public String getDkiv_402n() { return this.dkiv_402n; }
	private String dkiv_403n = null;
	public void setDkiv_403n(String value) {  this.dkiv_403n = value; }
	public String getDkiv_403n() { return this.dkiv_403n; }

	private String dkiv_401o = null;
	public void setDkiv_401o(String value) {  this.dkiv_401o = value; }
	public String getDkiv_401o() { return this.dkiv_401o; }
	private String dkiv_402o = null;
	public void setDkiv_402o(String value) {  this.dkiv_402o = value; }
	public String getDkiv_402o() { return this.dkiv_402o; }
	private String dkiv_403o = null;
	public void setDkiv_403o(String value) {  this.dkiv_403o = value; }
	public String getDkiv_403o() { return this.dkiv_403o; }

	private String dkiv_401p = null;
	public void setDkiv_401p(String value) {  this.dkiv_401p = value; }
	public String getDkiv_401p() { return this.dkiv_401p; }
	private String dkiv_402p = null;
	public void setDkiv_402p(String value) {  this.dkiv_402p = value; }
	public String getDkiv_402p() { return this.dkiv_402p; }
	private String dkiv_403p = null;
	public void setDkiv_403p(String value) {  this.dkiv_403p = value; }
	public String getDkiv_403p() { return this.dkiv_403p; }

	private String dkiv_401q = null;
	public void setDkiv_401q(String value) {  this.dkiv_401q = value; }
	public String getDkiv_401q() { return this.dkiv_401q; }
	private String dkiv_402q = null;
	public void setDkiv_402q(String value) {  this.dkiv_402q = value; }
	public String getDkiv_402q() { return this.dkiv_402q; }
	private String dkiv_403q = null;
	public void setDkiv_403q(String value) {  this.dkiv_403q = value; }
	public String getDkiv_403q() { return this.dkiv_403q; }

	private String dkiv_401r = null;
	public void setDkiv_401r(String value) {  this.dkiv_401r = value; }
	public String getDkiv_401r() { return this.dkiv_401r; }
	private String dkiv_402r = null;
	public void setDkiv_402r(String value) {  this.dkiv_402r = value; }
	public String getDkiv_402r() { return this.dkiv_402r; }
	private String dkiv_403r = null;
	public void setDkiv_403r(String value) {  this.dkiv_403r = value; }
	public String getDkiv_403r() { return this.dkiv_403r; }

	private String dkiv_401s = null;
	public void setDkiv_401s(String value) {  this.dkiv_401s = value; }
	public String getDkiv_401s() { return this.dkiv_401s; }
	private String dkiv_402s = null;
	public void setDkiv_402s(String value) {  this.dkiv_402s = value; }
	public String getDkiv_402s() { return this.dkiv_402s; }
	private String dkiv_403s = null;
	public void setDkiv_403s(String value) {  this.dkiv_403s = value; }
	public String getDkiv_403s() { return this.dkiv_403s; }

	private String dkiv_401t = null;
	public void setDkiv_401t(String value) {  this.dkiv_401t = value; }
	public String getDkiv_401t() { return this.dkiv_401t; }
	private String dkiv_402t = null;
	public void setDkiv_402t(String value) {  this.dkiv_402t = value; }
	public String getDkiv_402t() { return this.dkiv_402t; }
	private String dkiv_403t = null;
	public void setDkiv_403t(String value) {  this.dkiv_403t = value; }
	public String getDkiv_403t() { return this.dkiv_403t; }

	private String dkiv_401u = null;
	public void setDkiv_401u(String value) {  this.dkiv_401u = value; }
	public String getDkiv_401u() { return this.dkiv_401u; }
	private String dkiv_402u = null;
	public void setDkiv_402u(String value) {  this.dkiv_402u = value; }
	public String getDkiv_402u() { return this.dkiv_402u; }
	private String dkiv_403u = null;
	public void setDkiv_403u(String value) {  this.dkiv_403u = value; }
	public String getDkiv_403u() { return this.dkiv_403u; }

	private String dkiv_401v = null;
	public void setDkiv_401v(String value) {  this.dkiv_401v = value; }
	public String getDkiv_401v() { return this.dkiv_401v; }
	private String dkiv_402v = null;
	public void setDkiv_402v(String value) {  this.dkiv_402v = value; }
	public String getDkiv_402v() { return this.dkiv_402v; }
	private String dkiv_403v = null;
	public void setDkiv_403v(String value) {  this.dkiv_403v = value; }
	public String getDkiv_403v() { return this.dkiv_403v; }

	private String dkiv_401w = null;
	public void setDkiv_401w(String value) {  this.dkiv_401w = value; }
	public String getDkiv_401w() { return this.dkiv_401w; }
	private String dkiv_402w = null;
	public void setDkiv_402w(String value) {  this.dkiv_402w = value; }
	public String getDkiv_402w() { return this.dkiv_402w; }
	private String dkiv_403w = null;
	public void setDkiv_403w(String value) {  this.dkiv_403w = value; }
	public String getDkiv_403w() { return this.dkiv_403w; }

	private String dkiv_401x = null;
	public void setDkiv_401x(String value) {  this.dkiv_401x = value; }
	public String getDkiv_401x() { return this.dkiv_401x; }
	private String dkiv_402x = null;
	public void setDkiv_402x(String value) {  this.dkiv_402x = value; }
	public String getDkiv_402x() { return this.dkiv_402x; }
	private String dkiv_403x = null;
	public void setDkiv_403x(String value) {  this.dkiv_403x = value; }
	public String getDkiv_403x() { return this.dkiv_403x; }

	private String dkiv_401y = null;
	public void setDkiv_401y(String value) {  this.dkiv_401y = value; }
	public String getDkiv_401y() { return this.dkiv_401y; }
	private String dkiv_402y = null;
	public void setDkiv_402y(String value) {  this.dkiv_402y = value; }
	public String getDkiv_402y() { return this.dkiv_402y; }
	private String dkiv_403y = null;
	public void setDkiv_403y(String value) {  this.dkiv_403y = value; }
	public String getDkiv_403y() { return this.dkiv_403y; }

	
	private String dkiv_411 = null;
	public void setDkiv_411(String value) {  this.dkiv_411 = value; }
	public String getDkiv_411() { return this.dkiv_411; }
	
	private String dkiv_412 = null;
	public void setDkiv_412(String value) {  this.dkiv_412 = value; }
	public String getDkiv_412() { return this.dkiv_412; }
	
	private String dkiv_42 = null;
	public void setDkiv_42(String value) {  this.dkiv_42 = value; }
	public String getDkiv_42() { return this.dkiv_42; }
	
	private String dkiv_441 = null;
	public void setDkiv_441(String value) {  this.dkiv_441 = value; }
	public String getDkiv_441() { return this.dkiv_441; }
	
	
	private String dkiv_4421 = null;
	public void setDkiv_4421(String value) {  this.dkiv_4421 = value; }
	public String getDkiv_4421() { return this.dkiv_4421; }
	private String dkiv_442a = null;
	public void setDkiv_442a(String value) {  this.dkiv_442a = value; }
	public String getDkiv_442a() { return this.dkiv_442a; }
	
	private String dkiv_4422 = null;
	public void setDkiv_4422(String value) {  this.dkiv_4422 = value; }
	public String getDkiv_4422() { return this.dkiv_4422; }
	private String dkiv_442b = null;
	public void setDkiv_442b(String value) {  this.dkiv_442b = value; }
	public String getDkiv_442b() { return this.dkiv_442b; }
	
	private String dkiv_4423 = null;
	public void setDkiv_4423(String value) {  this.dkiv_4423 = value; }
	public String getDkiv_4423() { return this.dkiv_4423; }
	private String dkiv_442c = null;
	public void setDkiv_442c(String value) {  this.dkiv_442c = value; }
	public String getDkiv_442c() { return this.dkiv_442c; }
	
	private String dkiv_4424 = null;
	public void setDkiv_4424(String value) {  this.dkiv_4424 = value; }
	public String getDkiv_4424() { return this.dkiv_4424; }
	private String dkiv_442d = null;
	public void setDkiv_442d(String value) {  this.dkiv_442d = value; }
	public String getDkiv_442d() { return this.dkiv_442d; }

	private String dkiv_4425 = null;
	public void setDkiv_4425(String value) {  this.dkiv_4425 = value; }
	public String getDkiv_4425() { return this.dkiv_4425; }
	private String dkiv_442e = null;
	public void setDkiv_442e(String value) {  this.dkiv_442e = value; }
	public String getDkiv_442e() { return this.dkiv_442e; }
	
	private String dkiv_4426 = null;
	public void setDkiv_4426(String value) {  this.dkiv_4426 = value; }
	public String getDkiv_4426() { return this.dkiv_4426; }
	private String dkiv_442f = null;
	public void setDkiv_442f(String value) {  this.dkiv_442f = value; }
	public String getDkiv_442f() { return this.dkiv_442f; }
	
	private String dkiv_4427 = null;
	public void setDkiv_4427(String value) {  this.dkiv_4427 = value; }
	public String getDkiv_4427() { return this.dkiv_4427; }
	private String dkiv_442g = null;
	public void setDkiv_442g(String value) {  this.dkiv_442g = value; }
	public String getDkiv_442g() { return this.dkiv_442g; }
	
	private String dkiv_4428 = null;
	public void setDkiv_4428(String value) {  this.dkiv_4428 = value; }
	public String getDkiv_4428() { return this.dkiv_4428; }
	private String dkiv_442h = null;
	public void setDkiv_442h(String value) {  this.dkiv_442h = value; }
	public String getDkiv_442h() { return this.dkiv_442h; }
	
	private String dkiv_4429 = null;
	public void setDkiv_4429(String value) {  this.dkiv_4429 = value; }
	public String getDkiv_4429() { return this.dkiv_4429; }
	private String dkiv_442i = null;
	public void setDkiv_442i(String value) {  this.dkiv_442i = value; }
	public String getDkiv_442i() { return this.dkiv_442i; }
	
	
	private String dkiv_443 = null;
	public void setDkiv_443(String value) {  this.dkiv_443 = value; }
	public String getDkiv_443() { return this.dkiv_443; }
	
	private String dkiv_444a = null;
	public void setDkiv_444a(String value) {  this.dkiv_444a = value; }
	public String getDkiv_444a() { return this.dkiv_444a; }
	
	private String dkiv_444b = null;
	public void setDkiv_444b(String value) {  this.dkiv_444b = value; }
	public String getDkiv_444b() { return this.dkiv_444b; }
	
	//Item 44.6 --->Code
	private String dkiv_446ka = null;
	public void setDkiv_446ka(String value) {  this.dkiv_446ka = value; }
	public String getDkiv_446ka() { return this.dkiv_446ka; }

	private String dkiv_446kb = null;
	public void setDkiv_446kb(String value) {  this.dkiv_446kb = value; }
	public String getDkiv_446kb() { return this.dkiv_446kb; }

	private String dkiv_446kf = null;
	public void setDkiv_446kf(String value) {  this.dkiv_446kf = value; }
	public String getDkiv_446kf() { return this.dkiv_446kf; }

	private String dkiv_446kj = null;
	public void setDkiv_446kj(String value) {  this.dkiv_446kj = value; }
	public String getDkiv_446kj() { return this.dkiv_446kj; }

	private String dkiv_446kn = null;
	public void setDkiv_446kn(String value) {  this.dkiv_446kn = value; }
	public String getDkiv_446kn() { return this.dkiv_446kn; }

	private String dkiv_446kr = null;
	public void setDkiv_446kr(String value) {  this.dkiv_446kr = value; }
	public String getDkiv_446kr() { return this.dkiv_446kr; }

	

	//Item 44.6 --->Text (nomenclature t(x) where x=a-s)
	private String dkiv_446ta = null;
	public void setDkiv_446ta(String value) {  this.dkiv_446ta = value; }
	public String getDkiv_446ta() { return this.dkiv_446ta; }

	private String dkiv_446tb = null;
	public void setDkiv_446tb(String value) {  this.dkiv_446tb = value; }
	public String getDkiv_446tb() { return this.dkiv_446tb; }

	private String dkiv_446tc = null;
	public void setDkiv_446tc(String value) {  this.dkiv_446tc = value; }
	public String getDkiv_446tc() { return this.dkiv_446tc; }

	private String dkiv_446td = null;
	public void setDkiv_446td(String value) {  this.dkiv_446td = value; }
	public String getDkiv_446td() { return this.dkiv_446td; }

	private String dkiv_446te = null;
	public void setDkiv_446te(String value) {  this.dkiv_446te = value; }
	public String getDkiv_446te() { return this.dkiv_446te; }

	private String dkiv_446tf = null;
	public void setDkiv_446tf(String value) {  this.dkiv_446tf = value; }
	public String getDkiv_446tf() { return this.dkiv_446tf; }

	private String dkiv_446tg = null;
	public void setDkiv_446tg(String value) {  this.dkiv_446tg = value; }
	public String getDkiv_446tg() { return this.dkiv_446tg; }

	private String dkiv_446th = null;
	public void setDkiv_446th(String value) {  this.dkiv_446th = value; }
	public String getDkiv_446th() { return this.dkiv_446th; }

	private String dkiv_446ti = null;
	public void setDkiv_446ti(String value) {  this.dkiv_446ti = value; }
	public String getDkiv_446ti() { return this.dkiv_446ti; }

	private String dkiv_446tj = null;
	public void setDkiv_446tj(String value) {  this.dkiv_446tj = value; }
	public String getDkiv_446tj() { return this.dkiv_446tj; }

	private String dkiv_446tk = null;
	public void setDkiv_446tk(String value) {  this.dkiv_446tk = value; }
	public String getDkiv_446tk() { return this.dkiv_446tk; }

	private String dkiv_446tl = null;
	public void setDkiv_446tl(String value) {  this.dkiv_446tl = value; }
	public String getDkiv_446tl() { return this.dkiv_446tl; }

	private String dkiv_446tm = null;
	public void setDkiv_446tm(String value) {  this.dkiv_446tm = value; }
	public String getDkiv_446tm() { return this.dkiv_446tm; }

	private String dkiv_446tn = null;
	public void setDkiv_446tn(String value) {  this.dkiv_446tn = value; }
	public String getDkiv_446tn() { return this.dkiv_446tn; }

	private String dkiv_446to = null;
	public void setDkiv_446to(String value) {  this.dkiv_446to = value; }
	public String getDkiv_446to() { return this.dkiv_446to; }

	private String dkiv_446tp = null;
	public void setDkiv_446tp(String value) {  this.dkiv_446tp = value; }
	public String getDkiv_446tp() { return this.dkiv_446tp; }

	private String dkiv_446tq = null;
	public void setDkiv_446tq(String value) {  this.dkiv_446tq = value; }
	public String getDkiv_446tq() { return this.dkiv_446tq; }

	private String dkiv_446tr = null;
	public void setDkiv_446tr(String value) {  this.dkiv_446tr = value; }
	public String getDkiv_446tr() { return this.dkiv_446tr; }

	private String dkiv_446ts = null;
	public void setDkiv_446ts(String value) {  this.dkiv_446ts = value; }
	public String getDkiv_446ts() { return this.dkiv_446ts; }
	
	/*
	private String dkiv_446a = null;
	public void setDkiv_446a(String value) {  this.dkiv_446a = value; }
	public String getDkiv_446a() { return this.dkiv_446a; }
	
	private String dkiv_446b = null;
	public void setDkiv_446b(String value) {  this.dkiv_446b = value; }
	public String getDkiv_446b() { return this.dkiv_446b; }
	
	private String dkiv_446c = null;
	public void setDkiv_446c(String value) {  this.dkiv_446c = value; }
	public String getDkiv_446c() { return this.dkiv_446c; }
	
	private String dkiv_446d = null;
	public void setDkiv_446d(String value) {  this.dkiv_446d = value; }
	public String getDkiv_446d() { return this.dkiv_446d; }
	
	private String dkiv_446e = null;
	public void setDkiv_446e(String value) {  this.dkiv_446e = value; }
	public String getDkiv_446e() { return this.dkiv_446e; }
	*/
	
	
	
	private String dkiv_449a = null;
	public void setDkiv_449a(String value) {  this.dkiv_449a = value; }
	public String getDkiv_449a() { return this.dkiv_449a; }

	private String dkiv_449b = null;
	public void setDkiv_449b(String value) {  this.dkiv_449b = value; }
	public String getDkiv_449b() { return this.dkiv_449b; }

	private String dkiv_46 = null;
	public void setDkiv_46(String value) {  this.dkiv_46 = value; }
	public String getDkiv_46() { return this.dkiv_46; }

	//Avgifter
	private String dkiva_aba1 = null;
	public void setDkiva_aba1(String value) {  this.dkiva_aba1 = value; }
	public String getDkiva_aba1() { return this.dkiva_aba1;}
	
	private String dkiva_abk1 = null;
	public void setDkiva_abk1(String value) {  this.dkiva_abk1 = value; }
	public String getDkiva_abk1() { return this.dkiva_abk1;}
	
	private String dkiva_abg1 = null;
	public void setDkiva_abg1(String value) {  this.dkiva_abg1 = value; }
	public String getDkiva_abg1() { return this.dkiva_abg1;}
	
	private String dkiva_abs1 = null;
	public void setDkiva_abs1(String value) {  this.dkiva_abs1 = value; }
	public String getDkiva_abs1() { return this.dkiva_abs1;}
	
	private String dkiva_abx1 = null;
	public void setDkiva_abx1(String value) {  this.dkiva_abx1 = value; }
	public String getDkiva_abx1() { return this.dkiva_abx1;}
	
	private String dkiva_abb1 = null;
	public void setDkiva_abb1(String value) {  this.dkiva_abb1 = value; }
	public String getDkiva_abb1() { return this.dkiva_abb1;}

	
	private String dkiva_aba2 = null;
	public void setDkiva_aba2(String value) {  this.dkiva_aba2 = value; }
	public String getDkiva_aba2() { return this.dkiva_aba2;}
	
	private String dkiva_abk2 = null;
	public void setDkiva_abk2(String value) {  this.dkiva_abk2 = value; }
	public String getDkiva_abk2() { return this.dkiva_abk2;}
	
	private String dkiva_abg2 = null;
	public void setDkiva_abg2(String value) {  this.dkiva_abg2 = value; }
	public String getDkiva_abg2() { return this.dkiva_abg2;}
	
	private String dkiva_abs2 = null;
	public void setDkiva_abs2(String value) {  this.dkiva_abs2 = value; }
	public String getDkiva_abs2() { return this.dkiva_abs2;}
	
	private String dkiva_abx2 = null;
	public void setDkiva_abx2(String value) {  this.dkiva_abx2 = value; }
	public String getDkiva_abx2() { return this.dkiva_abx2;}
	
	private String dkiva_abb2 = null;
	public void setDkiva_abb2(String value) {  this.dkiva_abb2 = value; }
	public String getDkiva_abb2() { return this.dkiva_abb2;}
	

	private String dkiva_aba3 = null;
	public void setDkiva_aba3(String value) {  this.dkiva_aba3 = value; }
	public String getDkiva_aba3() { return this.dkiva_aba3;}
	
	private String dkiva_abk3 = null;
	public void setDkiva_abk3(String value) {  this.dkiva_abk3 = value; }
	public String getDkiva_abk3() { return this.dkiva_abk3;}
	
	private String dkiva_abg3 = null;
	public void setDkiva_abg3(String value) {  this.dkiva_abg3 = value; }
	public String getDkiva_abg3() { return this.dkiva_abg3;}
	
	private String dkiva_abs3 = null;
	public void setDkiva_abs3(String value) {  this.dkiva_abs3 = value; }
	public String getDkiva_abs3() { return this.dkiva_abs3;}
	
	private String dkiva_abx3 = null;
	public void setDkiva_abx3(String value) {  this.dkiva_abx3 = value; }
	public String getDkiva_abx3() { return this.dkiva_abx3;}
	
	private String dkiva_abb3 = null;
	public void setDkiva_abb3(String value) {  this.dkiva_abb3 = value; }
	public String getDkiva_abb3() { return this.dkiva_abb3;}

	
	private String dkiva_aba4 = null;
	public void setDkiva_aba4(String value) {  this.dkiva_aba4 = value; }
	public String getDkiva_aba4() { return this.dkiva_aba4;}
	
	private String dkiva_abk4 = null;
	public void setDkiva_abk4(String value) {  this.dkiva_abk4 = value; }
	public String getDkiva_abk4() { return this.dkiva_abk4;}
	
	private String dkiva_abg4 = null;
	public void setDkiva_abg4(String value) {  this.dkiva_abg4 = value; }
	public String getDkiva_abg4() { return this.dkiva_abg4;}
	
	private String dkiva_abs4 = null;
	public void setDkiva_abs4(String value) {  this.dkiva_abs4 = value; }
	public String getDkiva_abs4() { return this.dkiva_abs4;}
	
	private String dkiva_abx4 = null;
	public void setDkiva_abx4(String value) {  this.dkiva_abx4 = value; }
	public String getDkiva_abx4() { return this.dkiva_abx4;}
	
	private String dkiva_abb4 = null;
	public void setDkiva_abb4(String value) {  this.dkiva_abb4 = value; }
	public String getDkiva_abb4() { return this.dkiva_abb4;}

	
	private String dkiva_aba5 = null;
	public void setDkiva_aba5(String value) {  this.dkiva_aba5 = value; }
	public String getDkiva_aba5() { return this.dkiva_aba5;}
	
	private String dkiva_abk5 = null;
	public void setDkiva_abk5(String value) {  this.dkiva_abk5 = value; }
	public String getDkiva_abk5() { return this.dkiva_abk5;}
	
	private String dkiva_abg5 = null;
	public void setDkiva_abg5(String value) {  this.dkiva_abg5 = value; }
	public String getDkiva_abg5() { return this.dkiva_abg5;}
	
	private String dkiva_abs5 = null;
	public void setDkiva_abs5(String value) {  this.dkiva_abs5 = value; }
	public String getDkiva_abs5() { return this.dkiva_abs5;}
	
	private String dkiva_abx5 = null;
	public void setDkiva_abx5(String value) {  this.dkiva_abx5 = value; }
	public String getDkiva_abx5() { return this.dkiva_abx5;}
	
	private String dkiva_abb5 = null;
	public void setDkiva_abb5(String value) {  this.dkiva_abb5 = value; }
	public String getDkiva_abb5() { return this.dkiva_abb5;}
	
	
	//---------------------------
	//TOLDVÆRDIDEKLARATION fields
	//---------------------------
	private String dkiv_t11a = null;
	public void setDkiv_t11a(String value) {  this.dkiv_t11a = value; }
	public String getDkiv_t11a() { return this.dkiv_42;} //must get the "varans pris = dkiv_42 specifically (when validating)
	
	private String dkiv_t11b = null;
	public void setDkiv_t11b(String value) {  this.dkiv_t11b = value; }
	public String getDkiv_t11b() { return this.dkiv_t11b;}
	
	private String dkiv_t12 = null;
	public void setDkiv_t12(String value) {  this.dkiv_t12 = value; }
	public String getDkiv_t12() { return this.dkiv_t12;}
	
	private String dkiv_t13a = null;
	public void setDkiv_t13a(String value) {  this.dkiv_t13a = value; }
	public String getDkiv_t13a() { return this.dkiv_t13a;}
	
	private String dkiv_t13b = null;
	public void setDkiv_t13b(String value) {  this.dkiv_t13b = value; }
	public String getDkiv_t13b() { return this.dkiv_t13b;}
	
	private String dkiv_t13c = null;
	public void setDkiv_t13c(String value) {  this.dkiv_t13c = value; }
	public String getDkiv_t13c() { return this.dkiv_t13c;}
	
	private String dkiv_t14a = null;
	public void setDkiv_t14a(String value) {  this.dkiv_t14a = value; }
	public String getDkiv_t14a() { return this.dkiv_t14a;}
	
	private String dkiv_t14b = null;
	public void setDkiv_t14b(String value) {  this.dkiv_t14b = value; }
	public String getDkiv_t14b() { return this.dkiv_t14b;}
	
	private String dkiv_t14c = null;
	public void setDkiv_t14c(String value) {  this.dkiv_t14c = value; }
	public String getDkiv_t14c() { return this.dkiv_t14c;}
	
	private String dkiv_t14d = null;
	public void setDkiv_t14d(String value) {  this.dkiv_t14d = value; }
	public String getDkiv_t14d() { return this.dkiv_t14d;}

	private String dkiv_t15 = null;
	public void setDkiv_t15(String value) {  this.dkiv_t15 = value; }
	public String getDkiv_t15() { return this.dkiv_t15;}
	
	private String dkiv_t16 = null;
	public void setDkiv_t16(String value) {  this.dkiv_t16 = value; }
	public String getDkiv_t16() { return this.dkiv_t16;}
	
	
	private String dkiv_t17a = null;
	public void setDkiv_t17a(String value) {  this.dkiv_t17a = value; }
	public String getDkiv_t17a() { return this.dkiv_t17a;}
	
	private String dkiv_t17b = null;
	public void setDkiv_t17b(String value) {  this.dkiv_t17b = value; }
	public String getDkiv_t17b() { return this.dkiv_t17b;}
	
	private String dkiv_t17c = null;
	public void setDkiv_t17c(String value) {  this.dkiv_t17c = value; }
	public String getDkiv_t17c() { return this.dkiv_t17c;}
	
	private String dkiv_t18 = null;
	public void setDkiv_t18(String value) {  this.dkiv_t18 = value; }
	public String getDkiv_t18() { return this.dkiv_t18;}
			
	private String dkiv_t19 = null;
	public void setDkiv_t19(String value) {  this.dkiv_t19 = value; }
	public String getDkiv_t19() { return this.dkiv_t19;}
	
	private String dkiv_t20 = null;
	public void setDkiv_t20(String value) {  this.dkiv_t20 = value; }
	public String getDkiv_t20() { return this.dkiv_t20;}
	
	private String dkiv_t21a = null;
	public void setDkiv_t21a(String value) {  this.dkiv_t21a = value; }
	public String getDkiv_t21a() { return this.dkiv_t21a;}
	
	private String dkiv_t21b = null;
	public void setDkiv_t21b(String value) {  this.dkiv_t21b = value; }
	public String getDkiv_t21b() { return this.dkiv_t21b;}
	
	private String dkiv_t22 = null;
	public void setDkiv_t22(String value) {  this.dkiv_t22 = value; }
	public String getDkiv_t22() { return this.dkiv_t22;}
	
	private String dkiv_t23 = null;
	public void setDkiv_t23(String value) {  this.dkiv_t23 = value; }
	public String getDkiv_t23() { return this.dkiv_t23;}
	
	private String dkiv_t24 = null;
	public void setDkiv_t24(String value) {  this.dkiv_t24 = value; }
	public String getDkiv_t24() { return this.dkiv_t24;}
	
	
	private String dkiv_t25a = null;
	public void setDkiv_t25a(String value) {  this.dkiv_t25a = value; }
	public String getDkiv_t25a() { return this.dkiv_t25a;}
	
	private String dkiv_t25b = null;
	public void setDkiv_t25b(String value) {  this.dkiv_t25b = value; }
	public String getDkiv_t25b() { return this.dkiv_t25b;}
	
	private String dkiv_t25c = null;
	public void setDkiv_t25c(String value) {  this.dkiv_t25c = value; }
	public String getDkiv_t25c() { return this.dkiv_t25c;}
	
	private String dkiv_t25d = null;
	public void setDkiv_t25d(String value) {  this.dkiv_t25d = value; }
	public String getDkiv_t25d() { return this.dkiv_t25d;}
	
	private String dkiv_28a = null;
	public void setDkiv_28a(String value) {  this.dkiv_28a = value; }
	public String getDkiv_28a() { return this.dkiv_28a;}
	
	private String dkiv_28b = null;
	public void setDkiv_28b(String value) {  this.dkiv_28b = value; }
	public String getDkiv_28b() { return this.dkiv_28b;}
	
	private String dkiv_28c = null;
	public void setDkiv_28c(String value) {  this.dkiv_28c = value; }
	public String getDkiv_28c() { return this.dkiv_28c;}
	
	private String dkiv_x01 = null;
	public void setDkiv_x01(String value) {  this.dkiv_x01 = value; }
	public String getDkiv_x01() { return this.dkiv_x01;}
	
	private String dkiv_x02 = null;
	public void setDkiv_x02(String value) {  this.dkiv_x02 = value; }
	public String getDkiv_x02() { return this.dkiv_x02;}
	
	private String dkiv_x03 = null;
	public void setDkiv_x03(String value) {  this.dkiv_x03 = value; }
	public String getDkiv_x03() { return this.dkiv_x03;}
	
	private String dkiv_x04 = null;
	public void setDkiv_x04(String value) {  this.dkiv_x04 = value; }
	public String getDkiv_x04() { return this.dkiv_x04;}
	
	private String dkiv_x05 = null;
	public void setDkiv_x05(String value) {  this.dkiv_x05 = value; }
	public String getDkiv_x05() { return this.dkiv_x05;}
	
	private String dkiv_x06 = null;
	public void setDkiv_x06(String value) {  this.dkiv_x06 = value; }
	public String getDkiv_x06() { return this.dkiv_x06;}
	
	private String dkiv_x07 = null;
	public void setDkiv_x07(String value) {  this.dkiv_x07 = value; }
	public String getDkiv_x07() { return this.dkiv_x07;}
	
	
	private String dkiv_err = null;
	public void setDkiv_err(String value) {  this.dkiv_err = value; }
	public String getDkiv_err() { return this.dkiv_err;}
	
	
	/**
	 * 
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
