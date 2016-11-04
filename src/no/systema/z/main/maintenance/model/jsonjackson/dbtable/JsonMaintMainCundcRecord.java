package no.systema.z.main.maintenance.model.jsonjackson.dbtable;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;

public class JsonMaintMainCundcRecord extends JsonAbstractGrandFatherRecord {

	private String ccompn = null; // key
	private String cfirma = null; // key
	private String cconta = null; // key
	private String ctype = null;
	private String cphone = null;
	private String cmobil = null;
	private String cfax = null;
	private String cemail = null;
	private String clive = null;
	private String cprint = null;
	private String sonavn = null;
	private String cemne = null;
	private String cavd = null;
	private String cavdio = null;
	private String copd = null;
	private String copdio = null;
	private String cmerge = null;

	public String getCcompn() {
		return ccompn;
	}

	public void setCcompn(String ccompn) {
		this.ccompn = ccompn;
	}

	public String getCfirma() {
		return cfirma;
	}

	public void setCfirma(String cfirma) {
		this.cfirma = cfirma;
	}

	public String getCconta() {
		return cconta;
	}

	public void setCconta(String cconta) {
		this.cconta = cconta;
	}

	public String getCtype() {
		return ctype;
	}

	public void setCtype(String ctype) {
		this.ctype = ctype;
	}

	public String getCphone() {
		return cphone;
	}

	public void setCphone(String cphone) {
		this.cphone = cphone;
	}

	public String getCmobil() {
		return cmobil;
	}

	public void setCmobil(String cmobil) {
		this.cmobil = cmobil;
	}

	public String getCfax() {
		return cfax;
	}

	public void setCfax(String cfax) {
		this.cfax = cfax;
	}

	public String getCemail() {
		return cemail;
	}

	public void setCemail(String cemail) {
		this.cemail = cemail;
	}

	public String getClive() {
		return clive;
	}

	public void setClive(String clive) {
		this.clive = clive;
	}

	public String getCprint() {
		return cprint;
	}

	public void setCprint(String cprint) {
		this.cprint = cprint;
	}

	public String getSonavn() {
		return sonavn;
	}

	public void setSonavn(String sonavn) {
		this.sonavn = sonavn;
	}

	public String getCemne() {
		return cemne;
	}

	public void setCemne(String cemne) {
		this.cemne = cemne;
	}

	public String getCavd() {
		return cavd;
	}

	public void setCavd(String cavd) {
		this.cavd = cavd;
	}

	public String getCavdio() {
		return cavdio;
	}

	public void setCavdio(String cavdio) {
		this.cavdio = cavdio;
	}

	public String getCopd() {
		return copd;
	}

	public void setCopd(String copd) {
		this.copd = copd;
	}

	public String getCopdio() {
		return copdio;
	}

	public void setCopdio(String copdio) {
		this.copdio = copdio;
	}

	public String getCmerge() {
		return cmerge;
	}

	public void setCmerge(String cmerge) {
		this.cmerge = cmerge;
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Field> getFields() throws Exception {
		Class cl = Class.forName(this.getClass().getCanonicalName());
		Field[] fields = cl.getDeclaredFields();
		List<Field> list = Arrays.asList(fields);

		return list;
	}
}
