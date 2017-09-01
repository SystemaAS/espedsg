package no.systema.tror.model.jsonjackson.order.childwindow;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;

public class JsonTrorPostalCodeRecord extends JsonAbstractGrandFatherRecord {

	private String st2sta;
	private String st2kod; // key
	private String st2nvn;
	private String st2lk; // key
	private String stxxx;
	private int st2son;
	private String st2ko2;
	private String st2sol;
	private int st2pnr;
	private int st2soi;
	private String st2uni;
	
	public String getSt2sta() {
		return st2sta;
	}

	public void setSt2sta(String st2sta) {
		this.st2sta = st2sta;
	}

	public String getSt2kod() {
		return st2kod;
	}

	public void setSt2kod(String st2kod) {
		this.st2kod = st2kod;
	}

	public String getSt2nvn() {
		return st2nvn;
	}

	public void setSt2nvn(String st2nvn) {
		this.st2nvn = st2nvn;
	}

	public String getSt2lk() {
		return st2lk;
	}

	public void setSt2lk(String st2lk) {
		this.st2lk = st2lk;
	}

	public String getStxxx() {
		return stxxx;
	}

	public void setStxxx(String stxxx) {
		this.stxxx = stxxx;
	}

	public int getSt2son() {
		return st2son;
	}

	public void setSt2son(int st2son) {
		this.st2son = st2son;
	}

	public String getSt2ko2() {
		return st2ko2;
	}

	public void setSt2ko2(String st2ko2) {
		this.st2ko2 = st2ko2;
	}

	public String getSt2sol() {
		return st2sol;
	}

	public void setSt2sol(String st2sol) {
		this.st2sol = st2sol;
	}

	public int getSt2pnr() {
		return st2pnr;
	}

	public void setSt2pnr(int st2pnr) {
		this.st2pnr = st2pnr;
	}

	public int getSt2soi() {
		return st2soi;
	}

	public void setSt2soi(int st2soi) {
		this.st2soi = st2soi;
	}

	public String getSt2uni() {
		return st2uni;
	}

	public void setSt2uni(String st2uni) {
		this.st2uni = st2uni;
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
