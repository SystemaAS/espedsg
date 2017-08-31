package no.systema.tror.model.jsonjackson.order.childwindow;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;

public class JsonTrorCarrierRecord extends JsonAbstractGrandFatherRecord {

	private String vmfirm;
	private String vmtran; // key
	private String vmtrku; // key
	private String vmtrle; // key
	private String vmincr;
	private String vmnavn;
	private String vm0004;
	private String vm0010;
	private String vm0035;
	private String vmpref;
	private String vmrecn;
	private String vmrecm;
	private String vmlayo;
	private String vmlmom;
	private String vmm3om;
	private BigDecimal vmrab = new BigDecimal(0);
	private String vmalfa;
	private String vmtkat;
	private String vmstop;
	private String vmstda;
	private String vmtext;
	private String vmsnla;
	private String vmsnle;
	private String vmintp;
	
	public String getVmfirm() {
		return vmfirm;
	}

	public void setVmfirm(String vmfirm) {
		this.vmfirm = vmfirm;
	}

	public String getVmtran() {
		return vmtran;
	}

	public void setVmtran(String vmtran) {
		this.vmtran = vmtran;
	}

	public String getVmtrku() {
		return vmtrku;
	}

	public void setVmtrku(String vmtrku) {
		this.vmtrku = vmtrku;
	}

	public String getVmtrle() {
		return vmtrle;
	}

	public void setVmtrle(String vmtrle) {
		this.vmtrle = vmtrle;
	}

	public String getVmincr() {
		return vmincr;
	}

	public void setVmincr(String vmincr) {
		this.vmincr = vmincr;
	}

	public String getVmnavn() {
		return vmnavn;
	}

	public void setVmnavn(String vmnavn) {
		this.vmnavn = vmnavn;
	}

	public String getVm0004() {
		return vm0004;
	}

	public void setVm0004(String vm0004) {
		this.vm0004 = vm0004;
	}

	public String getVm0010() {
		return vm0010;
	}

	public void setVm0010(String vm0010) {
		this.vm0010 = vm0010;
	}

	public String getVm0035() {
		return vm0035;
	}

	public void setVm0035(String vm0035) {
		this.vm0035 = vm0035;
	}

	public String getVmpref() {
		return vmpref;
	}

	public void setVmpref(String vmpref) {
		this.vmpref = vmpref;
	}

	public String getVmrecn() {
		return vmrecn;
	}

	public void setVmrecn(String vmrecn) {
		this.vmrecn = vmrecn;
	}

	public String getVmrecm() {
		return vmrecm;
	}

	public void setVmrecm(String vmrecm) {
		this.vmrecm = vmrecm;
	}

	public String getVmlayo() {
		return vmlayo;
	}

	public void setVmlayo(String vmlayo) {
		this.vmlayo = vmlayo;
	}

	public String getVmlmom() {
		return vmlmom;
	}

	public void setVmlmom(String vmlmom) {
		this.vmlmom = vmlmom;
	}

	public String getVmm3om() {
		return vmm3om;
	}

	public void setVmm3om(String vmm3om) {
		this.vmm3om = vmm3om;
	}

	public BigDecimal getVmrab() {
		return vmrab;
	}

	public void setVmrab(BigDecimal vmrab) {
		this.vmrab = vmrab;
	}

	public String getVmalfa() {
		return vmalfa;
	}

	public void setVmalfa(String vmalfa) {
		this.vmalfa = vmalfa;
	}

	public String getVmtkat() {
		return vmtkat;
	}

	public void setVmtkat(String vmtkat) {
		this.vmtkat = vmtkat;
	}

	public String getVmstop() {
		return vmstop;
	}

	public void setVmstop(String vmstop) {
		this.vmstop = vmstop;
	}

	public String getVmstda() {
		return vmstda;
	}

	public void setVmstda(String vmstda) {
		this.vmstda = vmstda;
	}

	public String getVmtext() {
		return vmtext;
	}

	public void setVmtext(String vmtext) {
		this.vmtext = vmtext;
	}

	public String getVmsnla() {
		return vmsnla;
	}

	public void setVmsnla(String vmsnla) {
		this.vmsnla = vmsnla;
	}

	public String getVmsnle() {
		return vmsnle;
	}

	public void setVmsnle(String vmsnle) {
		this.vmsnle = vmsnle;
	}

	public String getVmintp() {
		return vmintp;
	}

	public void setVmintp(String vmintp) {
		this.vmintp = vmintp;
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
