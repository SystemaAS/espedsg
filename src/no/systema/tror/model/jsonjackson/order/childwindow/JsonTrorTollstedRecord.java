package no.systema.tror.model.jsonjackson.order.childwindow;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;

public class JsonTrorTollstedRecord extends JsonAbstractGrandFatherRecord  {

	private String ktssta;
	private String ktsuni;
	private String ktskod; // key
	private String ktsnav;
	private String ktspnr;
	private String ktstrt;
	private String ktssat; // decimal
	private String ktsxxx;
	
	public String getKtssta() {
		return ktssta;
	}

	public void setKtssta(String ktssta) {
		this.ktssta = ktssta;
	}

	public String getKtsuni() {
		return ktsuni;
	}

	public void setKtsuni(String ktsuni) {
		this.ktsuni = ktsuni;
	}

	public String getKtskod() {
		return ktskod;
	}

	public void setKtskod(String ktskod) {
		this.ktskod = ktskod;
	}

	public String getKtsnav() {
		return ktsnav;
	}

	public void setKtsnav(String ktsnav) {
		this.ktsnav = ktsnav;
	}

	public String getKtspnr() {
		return ktspnr;
	}

	public void setKtspnr(String ktspnr) {
		this.ktspnr = ktspnr;
	}

	public String getKtstrt() {
		return ktstrt;
	}

	public void setKtstrt(String ktstrt) {
		this.ktstrt = ktstrt;
	}

	public String getKtssat() {
		return ktssat;
	}

	public void setKtssat(String ktssat) {
		this.ktssat = ktssat;
	}

	public String getKtsxxx() {
		return ktsxxx;
	}

	public void setKtsxxx(String ktsxxx) {
		this.ktsxxx = ktsxxx;
	}

	/**
	 * User for java reflection in other classes
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
