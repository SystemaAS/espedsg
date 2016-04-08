/**
 * 
 */
package no.systema.skat.skatexport.model.jsonjackson.topic.items;

import java.lang.reflect.Field;
import java.util.*;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;

/**
 * 
 * @author oscardelatorre
 * @date Mar 17, 2014
 * 
 *
 */
public class JsonSkatExportSpecificTopicItemRecord  extends JsonAbstractGrandFatherRecord   {
	
	private String debugPrintlnAjax = null;
	public void setDebugPrintlnAjax(String value) {  this.debugPrintlnAjax = value; }
	public String getDebugPrintlnAjax() {return this.debugPrintlnAjax;}
	
	private boolean validTolltariff = true;
	public void setValidTolltariff(boolean value) {  this.validTolltariff = value; }
	public boolean isValidTolltariff() {return this.validTolltariff;}
	
	
	//Aux. attribute to pass some header values into a Validator for Items. (Angivelsesart)
	private String header_dkeh_aart = null;
	public void setHeader_dkeh_aart(String value) {  this.header_dkeh_aart = value; }
	public String getHeader_dkeh_aart() {return this.header_dkeh_aart;}
	
	//Aux. attribute to validate mangdenhet
	/*private String extraMangdEnhet = null;
	public void setExtraMangdEnhet(String value) {  this.extraMangdEnhet = value; }
	public String getExtraMangdEnhet() {return this.extraMangdEnhet;}
	*/
	
	private String dkev_syav = null;
	public void setDkev_syav(String value) {  this.dkev_syav = value; }
	public String getDkev_syav() {return this.dkev_syav;}
	
	private String dkev_syop = null;
	public void setDkev_syop(String value) {  this.dkev_syop = value; }
	public String getDkev_syop() {return this.dkev_syop;}
	
	private String dkev_syli = null;
	public void setDkev_syli(String value) {  this.dkev_syli = value; }
	public String getDkev_syli() {return this.dkev_syli;}
	
	
	private String dkev_311 = null;
	public void setDkev_311(String value) {  this.dkev_311 = value; }
	public String getDkev_311() {return this.dkev_311;}
	
	private String dkev_312a = null;
	public void setDkev_312a(String value) {  this.dkev_312a = value; }
	public String getDkev_312a() {return this.dkev_312a;}
	
	private String dkev_312b = null;
	public void setDkev_312b(String value) {  this.dkev_312b = value; }
	public String getDkev_312b() {return this.dkev_312b;}
	
	private String dkev_312c = null;
	public void setDkev_312c(String value) {  this.dkev_312c = value; }
	public String getDkev_312c() {return this.dkev_312c;}
	
	private String dkev_312d = null;
	public void setDkev_312d(String value) {  this.dkev_312d = value; }
	public String getDkev_312d() {return this.dkev_312d;}
	
	private String dkev_312e = null;
	public void setDkev_312e(String value) {  this.dkev_312e = value; }
	public String getDkev_312e() {return this.dkev_312e;}
	
	
	
	private String dkev_313 = "0";
	public void setDkev_313(String value) {
		if(value==null || "".equals(value)){
			this.dkev_313 = "0";
		}else{
			this.dkev_313 = value;
		}
	}
	public String getDkev_313() {
		if(this.dkev_313==null || "".equals(this.dkev_313)){
			return "0";
		}else{
			return this.dkev_313;
		}
	}
	
	private String dkev_314 = null;
	public void setDkev_314(String value) {  this.dkev_314 = value; }
	public String getDkev_314() {return this.dkev_314;}
	
	private String dkev_315 = null;
	public void setDkev_315(String value) {  this.dkev_315 = value; }
	public String getDkev_315() {return this.dkev_315;}
	
	private String dkev_32 = null;
	public void setDkev_32(String value) {  this.dkev_32 = value; }
	public String getDkev_32() { return this.dkev_32;}
	
	private String dkev_331 = null;
	public void setDkev_331(String value) {  this.dkev_331 = value; }
	public String getDkev_331() { return this.dkev_331;}
	
	private String dkev_332 = null;
	public void setDkev_332(String value) {  this.dkev_332 = value; }
	public String getDkev_332() { return this.dkev_332;}
	
	private String dkev_sikk = null;
	public void setDkev_sikk(String value) {  this.dkev_sikk = value; }
	public String getDkev_sikk() { return this.dkev_sikk;}
	
	private String dkev_34a = null;
	public void setDkev_34a(String value) {  this.dkev_34a = value; }
	public String getDkev_34a() { return this.dkev_34a;}
	
	private String dkev_35 = null;
	public void setDkev_35(String value) {  this.dkev_35 = value; }
	public String getDkev_35() { return this.dkev_35;}
	
	private String dkev_37 = null;
	public void setDkev_37(String value) {  this.dkev_37 = value; }
	public String getDkev_37() { return this.dkev_37;}
	
	private String dkev_38 = null;
	public void setDkev_38(String value) {  this.dkev_38 = value; }
	public String getDkev_38() { return this.dkev_38;}
	
	
	//New set
	private String dkev_401a = null;
	public void setDkev_401a(String value) {  this.dkev_401a = value; }
	public String getDkev_401a() { return this.dkev_401a; }
	private String dkev_402a = null;
	public void setDkev_402a(String value) {  this.dkev_402a = value; }
	public String getDkev_402a() { return this.dkev_402a; }
	private String dkev_403a = null;
	public void setDkev_403a(String value) {  this.dkev_403a = value; }
	public String getDkev_403a() { return this.dkev_403a; }

	private String dkev_401b = null;
	public void setDkev_401b(String value) {  this.dkev_401b = value; }
	public String getDkev_401b() { return this.dkev_401b; }
	private String dkev_402b = null;
	public void setDkev_402b(String value) {  this.dkev_402b = value; }
	public String getDkev_402b() { return this.dkev_402b; }
	private String dkev_403b = null;
	public void setDkev_403b(String value) {  this.dkev_403b = value; }
	public String getDkev_403b() { return this.dkev_403b; }
	
	private String dkev_401c = null;
	public void setDkev_401c(String value) {  this.dkev_401c = value; }
	public String getDkev_401c() { return this.dkev_401c; }
	private String dkev_402c = null;
	public void setDkev_402c(String value) {  this.dkev_402c = value; }
	public String getDkev_402c() { return this.dkev_402c; }
	private String dkev_403c = null;
	public void setDkev_403c(String value) {  this.dkev_403c = value; }
	public String getDkev_403c() { return this.dkev_403c; }

	private String dkev_401d = null;
	public void setDkev_401d(String value) {  this.dkev_401d = value; }
	public String getDkev_401d() { return this.dkev_401d; }
	private String dkev_402d = null;
	public void setDkev_402d(String value) {  this.dkev_402d = value; }
	public String getDkev_402d() { return this.dkev_402d; }
	private String dkev_403d = null;
	public void setDkev_403d(String value) {  this.dkev_403d = value; }
	public String getDkev_403d() { return this.dkev_403d; }

	private String dkev_401e = null;
	public void setDkev_401e(String value) {  this.dkev_401e = value; }
	public String getDkev_401e() { return this.dkev_401e; }
	private String dkev_402e = null;
	public void setDkev_402e(String value) {  this.dkev_402e = value; }
	public String getDkev_402e() { return this.dkev_402e; }
	private String dkev_403e = null;
	public void setDkev_403e(String value) {  this.dkev_403e = value; }
	public String getDkev_403e() { return this.dkev_403e; }

	private String dkev_401f = null;
	public void setDkev_401f(String value) {  this.dkev_401f = value; }
	public String getDkev_401f() { return this.dkev_401f; }
	private String dkev_402f = null;
	public void setDkev_402f(String value) {  this.dkev_402f = value; }
	public String getDkev_402f() { return this.dkev_402f; }
	private String dkev_403f = null;
	public void setDkev_403f(String value) {  this.dkev_403f = value; }
	public String getDkev_403f() { return this.dkev_403f; }

	private String dkev_401g = null;
	public void setDkev_401g(String value) {  this.dkev_401g = value; }
	public String getDkev_401g() { return this.dkev_401g; }
	private String dkev_402g = null;
	public void setDkev_402g(String value) {  this.dkev_402g = value; }
	public String getDkev_402g() { return this.dkev_402g; }
	private String dkev_403g = null;
	public void setDkev_403g(String value) {  this.dkev_403g = value; }
	public String getDkev_403g() { return this.dkev_403g; }

	private String dkev_401h = null;
	public void setDkev_401h(String value) {  this.dkev_401h = value; }
	public String getDkev_401h() { return this.dkev_401h; }
	private String dkev_402h = null;
	public void setDkev_402h(String value) {  this.dkev_402h = value; }
	public String getDkev_402h() { return this.dkev_402h; }
	private String dkev_403h = null;
	public void setDkev_403h(String value) {  this.dkev_403h = value; }
	public String getDkev_403h() { return this.dkev_403h; }

	private String dkev_401i = null;
	public void setDkev_401i(String value) {  this.dkev_401i = value; }
	public String getDkev_401i() { return this.dkev_401i; }
	private String dkev_402i = null;
	public void setDkev_402i(String value) {  this.dkev_402i = value; }
	public String getDkev_402i() { return this.dkev_402i; }
	private String dkev_403i = null;
	public void setDkev_403i(String value) {  this.dkev_403i = value; }
	public String getDkev_403i() { return this.dkev_403i; }

	private String dkev_401j = null;
	public void setDkev_401j(String value) {  this.dkev_401j = value; }
	public String getDkev_401j() { return this.dkev_401j; }
	private String dkev_402j = null;
	public void setDkev_402j(String value) {  this.dkev_402j = value; }
	public String getDkev_402j() { return this.dkev_402j; }
	private String dkev_403j = null;
	public void setDkev_403j(String value) {  this.dkev_403j = value; }
	public String getDkev_403j() { return this.dkev_403j; }

	private String dkev_401k = null;
	public void setDkev_401k(String value) {  this.dkev_401k = value; }
	public String getDkev_401k() { return this.dkev_401k; }
	private String dkev_402k = null;
	public void setDkev_402k(String value) {  this.dkev_402k = value; }
	public String getDkev_402k() { return this.dkev_402k; }
	private String dkev_403k = null;
	public void setDkev_403k(String value) {  this.dkev_403k = value; }
	public String getDkev_403k() { return this.dkev_403k; }

	private String dkev_401l = null;
	public void setDkev_401l(String value) {  this.dkev_401l = value; }
	public String getDkev_401l() { return this.dkev_401l; }
	private String dkev_402l = null;
	public void setDkev_402l(String value) {  this.dkev_402l = value; }
	public String getDkev_402l() { return this.dkev_402l; }
	private String dkev_403l = null;
	public void setDkev_403l(String value) {  this.dkev_403l = value; }
	public String getDkev_403l() { return this.dkev_403l; }

	private String dkev_401m = null;
	public void setDkev_401m(String value) {  this.dkev_401m = value; }
	public String getDkev_401m() { return this.dkev_401m; }
	private String dkev_402m = null;
	public void setDkev_402m(String value) {  this.dkev_402m = value; }
	public String getDkev_402m() { return this.dkev_402m; }
	private String dkev_403m = null;
	public void setDkev_403m(String value) {  this.dkev_403m = value; }
	public String getDkev_403m() { return this.dkev_403m; }

	private String dkev_401n = null;
	public void setDkev_401n(String value) {  this.dkev_401n = value; }
	public String getDkev_401n() { return this.dkev_401n; }
	private String dkev_402n = null;
	public void setDkev_402n(String value) {  this.dkev_402n = value; }
	public String getDkev_402n() { return this.dkev_402n; }
	private String dkev_403n = null;
	public void setDkev_403n(String value) {  this.dkev_403n = value; }
	public String getDkev_403n() { return this.dkev_403n; }

	private String dkev_401o = null;
	public void setDkev_401o(String value) {  this.dkev_401o = value; }
	public String getDkev_401o() { return this.dkev_401o; }
	private String dkev_402o = null;
	public void setDkev_402o(String value) {  this.dkev_402o = value; }
	public String getDkev_402o() { return this.dkev_402o; }
	private String dkev_403o = null;
	public void setDkev_403o(String value) {  this.dkev_403o = value; }
	public String getDkev_403o() { return this.dkev_403o; }

	private String dkev_401p = null;
	public void setDkev_401p(String value) {  this.dkev_401p = value; }
	public String getDkev_401p() { return this.dkev_401p; }
	private String dkev_402p = null;
	public void setDkev_402p(String value) {  this.dkev_402p = value; }
	public String getDkev_402p() { return this.dkev_402p; }
	private String dkev_403p = null;
	public void setDkev_403p(String value) {  this.dkev_403p = value; }
	public String getDkev_403p() { return this.dkev_403p; }

	private String dkev_401q = null;
	public void setDkev_401q(String value) {  this.dkev_401q = value; }
	public String getDkev_401q() { return this.dkev_401q; }
	private String dkev_402q = null;
	public void setDkev_402q(String value) {  this.dkev_402q = value; }
	public String getDkev_402q() { return this.dkev_402q; }
	private String dkev_403q = null;
	public void setDkev_403q(String value) {  this.dkev_403q = value; }
	public String getDkev_403q() { return this.dkev_403q; }

	private String dkev_401r = null;
	public void setDkev_401r(String value) {  this.dkev_401r = value; }
	public String getDkev_401r() { return this.dkev_401r; }
	private String dkev_402r = null;
	public void setDkev_402r(String value) {  this.dkev_402r = value; }
	public String getDkev_402r() { return this.dkev_402r; }
	private String dkev_403r = null;
	public void setDkev_403r(String value) {  this.dkev_403r = value; }
	public String getDkev_403r() { return this.dkev_403r; }

	private String dkev_401s = null;
	public void setDkev_401s(String value) {  this.dkev_401s = value; }
	public String getDkev_401s() { return this.dkev_401s; }
	private String dkev_402s = null;
	public void setDkev_402s(String value) {  this.dkev_402s = value; }
	public String getDkev_402s() { return this.dkev_402s; }
	private String dkev_403s = null;
	public void setDkev_403s(String value) {  this.dkev_403s = value; }
	public String getDkev_403s() { return this.dkev_403s; }

	private String dkev_401t = null;
	public void setDkev_401t(String value) {  this.dkev_401t = value; }
	public String getDkev_401t() { return this.dkev_401t; }
	private String dkev_402t = null;
	public void setDkev_402t(String value) {  this.dkev_402t = value; }
	public String getDkev_402t() { return this.dkev_402t; }
	private String dkev_403t = null;
	public void setDkev_403t(String value) {  this.dkev_403t = value; }
	public String getDkev_403t() { return this.dkev_403t; }

	private String dkev_401u = null;
	public void setDkev_401u(String value) {  this.dkev_401u = value; }
	public String getDkev_401u() { return this.dkev_401u; }
	private String dkev_402u = null;
	public void setDkev_402u(String value) {  this.dkev_402u = value; }
	public String getDkev_402u() { return this.dkev_402u; }
	private String dkev_403u = null;
	public void setDkev_403u(String value) {  this.dkev_403u = value; }
	public String getDkev_403u() { return this.dkev_403u; }

	private String dkev_401v = null;
	public void setDkev_401v(String value) {  this.dkev_401v = value; }
	public String getDkev_401v() { return this.dkev_401v; }
	private String dkev_402v = null;
	public void setDkev_402v(String value) {  this.dkev_402v = value; }
	public String getDkev_402v() { return this.dkev_402v; }
	private String dkev_403v = null;
	public void setDkev_403v(String value) {  this.dkev_403v = value; }
	public String getDkev_403v() { return this.dkev_403v; }

	private String dkev_401w = null;
	public void setDkev_401w(String value) {  this.dkev_401w = value; }
	public String getDkev_401w() { return this.dkev_401w; }
	private String dkev_402w = null;
	public void setDkev_402w(String value) {  this.dkev_402w = value; }
	public String getDkev_402w() { return this.dkev_402w; }
	private String dkev_403w = null;
	public void setDkev_403w(String value) {  this.dkev_403w = value; }
	public String getDkev_403w() { return this.dkev_403w; }

	private String dkev_401x = null;
	public void setDkev_401x(String value) {  this.dkev_401x = value; }
	public String getDkev_401x() { return this.dkev_401x; }
	private String dkev_402x = null;
	public void setDkev_402x(String value) {  this.dkev_402x = value; }
	public String getDkev_402x() { return this.dkev_402x; }
	private String dkev_403x = null;
	public void setDkev_403x(String value) {  this.dkev_403x = value; }
	public String getDkev_403x() { return this.dkev_403x; }

	private String dkev_401y = null;
	public void setDkev_401y(String value) {  this.dkev_401y = value; }
	public String getDkev_401y() { return this.dkev_401y; }
	private String dkev_402y = null;
	public void setDkev_402y(String value) {  this.dkev_402y = value; }
	public String getDkev_402y() { return this.dkev_402y; }
	private String dkev_403y = null;
	public void setDkev_403y(String value) {  this.dkev_403y = value; }
	public String getDkev_403y() { return this.dkev_403y; }

	
	private String dkev_411 = null;
	public void setDkev_411(String value) {  this.dkev_411 = value; }
	public String getDkev_411() { return this.dkev_411; }
	
	private String dkev_412 = null;
	public void setDkev_412(String value) {  this.dkev_412 = value; }
	public String getDkev_412() { return this.dkev_412; }
	
	private String dkev_42 = null;
	public void setDkev_42(String value) {  this.dkev_42 = value; }
	public String getDkev_42() { return this.dkev_42; }
	
	private String dkev_441 = null;
	public void setDkev_441(String value) {  this.dkev_441 = value; }
	public String getDkev_441() { return this.dkev_441; }
	
	/*Old
	private String dkev_442a = null;
	public void setDkev_442a(String value) {  this.dkev_442a = value; }
	public String getDkev_442a() { return this.dkev_442a; }
	
	private String dkev_442b = null;
	public void setDkev_442b(String value) {  this.dkev_442b = value; }
	public String getDkev_442b() { return this.dkev_442b; }
	end old */
	
	private String dkev_4421 = null;
	public void setdkev_4421(String value) {  this.dkev_4421 = value; }
	public String getdkev_4421() { return this.dkev_4421; }
	private String dkev_442a = null;
	public void setdkev_442a(String value) {  this.dkev_442a = value; }
	public String getdkev_442a() { return this.dkev_442a; }
	
	private String dkev_4422 = null;
	public void setdkev_4422(String value) {  this.dkev_4422 = value; }
	public String getdkev_4422() { return this.dkev_4422; }
	private String dkev_442b = null;
	public void setdkev_442b(String value) {  this.dkev_442b = value; }
	public String getdkev_442b() { return this.dkev_442b; }
	
	private String dkev_4423 = null;
	public void setdkev_4423(String value) {  this.dkev_4423 = value; }
	public String getdkev_4423() { return this.dkev_4423; }
	private String dkev_442c = null;
	public void setdkev_442c(String value) {  this.dkev_442c = value; }
	public String getdkev_442c() { return this.dkev_442c; }
	
	private String dkev_4424 = null;
	public void setdkev_4424(String value) {  this.dkev_4424 = value; }
	public String getdkev_4424() { return this.dkev_4424; }
	private String dkev_442d = null;
	public void setdkev_442d(String value) {  this.dkev_442d = value; }
	public String getdkev_442d() { return this.dkev_442d; }

	private String dkev_4425 = null;
	public void setdkev_4425(String value) {  this.dkev_4425 = value; }
	public String getdkev_4425() { return this.dkev_4425; }
	private String dkev_442e = null;
	public void setdkev_442e(String value) {  this.dkev_442e = value; }
	public String getdkev_442e() { return this.dkev_442e; }
	
	private String dkev_4426 = null;
	public void setdkev_4426(String value) {  this.dkev_4426 = value; }
	public String getdkev_4426() { return this.dkev_4426; }
	private String dkev_442f = null;
	public void setdkev_442f(String value) {  this.dkev_442f = value; }
	public String getdkev_442f() { return this.dkev_442f; }
	
	private String dkev_4427 = null;
	public void setdkev_4427(String value) {  this.dkev_4427 = value; }
	public String getdkev_4427() { return this.dkev_4427; }
	private String dkev_442g = null;
	public void setdkev_442g(String value) {  this.dkev_442g = value; }
	public String getdkev_442g() { return this.dkev_442g; }
	
	private String dkev_4428 = null;
	public void setdkev_4428(String value) {  this.dkev_4428 = value; }
	public String getdkev_4428() { return this.dkev_4428; }
	private String dkev_442h = null;
	public void setdkev_442h(String value) {  this.dkev_442h = value; }
	public String getdkev_442h() { return this.dkev_442h; }
	
	private String dkev_4429 = null;
	public void setdkev_4429(String value) {  this.dkev_4429 = value; }
	public String getdkev_4429() { return this.dkev_4429; }
	private String dkev_442i = null;
	public void setdkev_442i(String value) {  this.dkev_442i = value; }
	public String getdkev_442i() { return this.dkev_442i; }
	

	
	
	private String dkev_443 = null;
	public void setDkev_443(String value) {  this.dkev_443 = value; }
	public String getDkev_443() { return this.dkev_443; }
	
	private String dkev_444 = null;
	public void setDkev_444(String value) {  this.dkev_444 = value; }
	public String getDkev_444() { return this.dkev_444; }
	
	private String dkev_445a = null;
	public void setDkev_445a(String value) {  this.dkev_445a = value; }
	public String getDkev_445a() { return this.dkev_445a; }
	
	private String dkev_445b = null;
	public void setDkev_445b(String value) {  this.dkev_445b = value; }
	public String getDkev_445b() { return this.dkev_445b; }
	
	
	private String dkev_446a = null;
	public void setDkev_446a(String value) {  this.dkev_446a = value; }
	public String getDkev_446a() { return this.dkev_446a; }
	
	private String dkev_446b = null;
	public void setDkev_446b(String value) {  this.dkev_446b = value; }
	public String getDkev_446b() { return this.dkev_446b; }
	
	private String dkev_446c = null;
	public void setDkev_446c(String value) {  this.dkev_446c = value; }
	public String getDkev_446c() { return this.dkev_446c; }
	
	private String dkev_447 = null;
	public void setDkev_447(String value) {  this.dkev_447 = value; }
	public String getDkev_447() { return this.dkev_447; }

	private String dkev_448 = null;
	public void setDkev_448(String value) {  this.dkev_448 = value; }
	public String getDkev_448() { return this.dkev_448; }

	private String dkev_449a = null;
	public void setDkev_449a(String value) {  this.dkev_449a = value; }
	public String getDkev_449a() { return this.dkev_449a; }
	
	private String dkev_449b = null;
	public void setDkev_449b(String value) {  this.dkev_449b = value; }
	public String getDkev_449b() { return this.dkev_449b; }

	private String dkev_46 = null;
	public void setDkev_46(String value) {  this.dkev_46 = value; }
	public String getDkev_46() { return this.dkev_46; }

	private String dkev_49 = null;
	public void setDkev_49(String value) {  this.dkev_49 = value; }
	public String getDkev_49() { return this.dkev_49; }
	
	private String dkev_bem1 = null;
	public void setDkev_bem1(String value) {  this.dkev_bem1 = value; }
	public String getDkev_bem1() { return this.dkev_bem1;} 
	
	private String dkev_bem2 = null;
	public void setDkev_bem2(String value) {  this.dkev_bem2 = value; }
	public String getDkev_bem2() { return this.dkev_bem2;} 
	
	private String dkev_bem3 = null;
	public void setDkev_bem3(String value) {  this.dkev_bem3 = value; }
	public String getDkev_bem3() { return this.dkev_bem3;} 
	
	private String dkev_bem4 = null;
	public void setDkev_bem4(String value) {  this.dkev_bem4 = value; }
	public String getDkev_bem4() { return this.dkev_bem4;} 

	private String dkev_y63 = null;
	public void setDkev_y63(String value) {  this.dkev_y63 = value; }
	public String getDkev_y63() { return this.dkev_y63;} 

	private String dkev_y64 = null;
	public void setDkev_y64(String value) {  this.dkev_y64 = value; }
	public String getDkev_y64() { return this.dkev_y64;} 

	private String dkev_y71 = null;
	public void setDkev_y71(String value) {  this.dkev_y71 = value; }
	public String getDkev_y71() { return this.dkev_y71;} 

	private String dkev_y72 = null;
	public void setDkev_y72(String value) {  this.dkev_y72 = value; }
	public String getDkev_y72() { return this.dkev_y72;} 

	private String dkev_y73 = null;
	public void setDkev_y73(String value) {  this.dkev_y73 = value; }
	public String getDkev_y73() { return this.dkev_y73;} 

	private String dkev_y74 = null;
	public void setDkev_y74(String value) {  this.dkev_y74 = value; }
	public String getDkev_y74() { return this.dkev_y74;} 

	private String dkev_y75 = null;
	public void setDkev_y75(String value) {  this.dkev_y75 = value; }
	public String getDkev_y75() { return this.dkev_y75;} 

	private String dkev_y76a = null;
	public void setDkev_y76a(String value) {  this.dkev_y76a = value; }
	public String getDkev_y76a() { return this.dkev_y76a;} 

	private String dkev_y76b = null;
	public void setDkev_y76b(String value) {  this.dkev_y76b = value; }
	public String getDkev_y76b() { return this.dkev_y76b;} 

	private String dkev_y76c = null;
	public void setDkev_y76c(String value) {  this.dkev_y76c = value; }
	public String getDkev_y76c() { return this.dkev_y76c;} 

	private String dkev_y76d = null;
	public void setDkev_y76d(String value) {  this.dkev_y76d = value; }
	public String getDkev_y76d() { return this.dkev_y76d;} 

	private String dkev_y81a = null;
	public void setDkev_y81a(String value) {  this.dkev_y81a = value; }
	public String getDkev_y81a() { return this.dkev_y81a;} 

	private String dkev_y81b = null;
	public void setDkev_y81b(String value) {  this.dkev_y81b = value; }
	public String getDkev_y81b() { return this.dkev_y81b;} 

	private String dkev_y81c = null;
	public void setDkev_y81c(String value) {  this.dkev_y81c = value; }
	public String getDkev_y81c() { return this.dkev_y81c;} 

	private String dkev_y81d = null;
	public void setDkev_y81d(String value) {  this.dkev_y81d = value; }
	public String getDkev_y81d() { return this.dkev_y81d;} 

	private String dkev_y81e = null;
	public void setDkev_y81e(String value) {  this.dkev_y81e = value; }
	public String getDkev_y81e() { return this.dkev_y81e;} 

	private String dkev_y82a = null;
	public void setDkev_y82a(String value) {  this.dkev_y82a = value; }
	public String getDkev_y82a() { return this.dkev_y82a;} 

	private String dkev_y82b = null;
	public void setDkev_y82b(String value) {  this.dkev_y82b = value; }
	public String getDkev_y82b() { return this.dkev_y82b;} 

	private String dkev_y82c = null;
	public void setDkev_y82c(String value) {  this.dkev_y82c = value; }
	public String getDkev_y82c() { return this.dkev_y82c;} 

	private String dkev_y82d = null;
	public void setDkev_y82d(String value) {  this.dkev_y82d = value; }
	public String getDkev_y82d() { return this.dkev_y82d;} 

	private String dkev_y82e = null;
	public void setDkev_y82e(String value) {  this.dkev_y82e = value; }
	public String getDkev_y82e() { return this.dkev_y82e;} 
	
	private String dkev_y82f = null;
	public void setDkev_y82f(String value) {  this.dkev_y82f = value; }
	public String getDkev_y82f() { return this.dkev_y82f;} 

	private String dkev_y82g = null;
	public void setDkev_y82g(String value) {  this.dkev_y82g = value; }
	public String getDkev_y82g() { return this.dkev_y82g;} 

	private String dkev_y82h = null;
	public void setDkev_y82h(String value) {  this.dkev_y82h = value; }
	public String getDkev_y82h() { return this.dkev_y82h;} 
	
	private String dkev_28a = null;
	public void setDkev_28a(String value) {  this.dkev_28a = value; }
	public String getDkev_28a() { return this.dkev_28a;}
	
	private String dkev_28b = null;
	public void setDkev_28b(String value) {  this.dkev_28b = value; }
	public String getDkev_28b() { return this.dkev_28b;}
	
	private String dkev_28c = null;
	public void setDkev_28c(String value) {  this.dkev_28c = value; }
	public String getDkev_28c() { return this.dkev_28c;}
	
	private String dkev_x01 = null;
	public void setDkev_x01(String value) {  this.dkev_x01 = value; }
	public String getDkev_x01() { return this.dkev_x01;}
	
	private String dkev_x02 = null;
	public void setDkev_x02(String value) {  this.dkev_x02 = value; }
	public String getDkev_x02() { return this.dkev_x02;}
	
	private String dkev_x03 = null;
	public void setDkev_x03(String value) {  this.dkev_x03 = value; }
	public String getDkev_x03() { return this.dkev_x03;}
	
	private String dkev_x04 = null;
	public void setDkev_x04(String value) {  this.dkev_x04 = value; }
	public String getDkev_x04() { return this.dkev_x04;}
	
	private String dkev_x05 = null;
	public void setDkev_x05(String value) {  this.dkev_x05 = value; }
	public String getDkev_x05() { return this.dkev_x05;}
	
	private String dkev_x06 = null;
	public void setDkev_x06(String value) {  this.dkev_x06 = value; }
	public String getDkev_x06() { return this.dkev_x06;}
	
	private String dkev_x07 = null;
	public void setDkev_x07(String value) {  this.dkev_x07 = value; }
	public String getDkev_x07() { return this.dkev_x07;}
	
	private String dkev_err = null;
	public void setDkev_err(String value) {  this.dkev_err = value; }
	public String getDkev_err() { return this.dkev_err;}
	
	
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
