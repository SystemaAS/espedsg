package no.systema.z.main.maintenance.controller.kund;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * Object for transfering data between jsp in Kunderegister
 * 
 * @author Fredrik Möller
 * @date Okt 31, 2016
 *
 */
public class KundeSessionParams implements Serializable {

	private static final long serialVersionUID = -1889823127739746766L;

	private String kundnr;
	private String knavn;
	private String firma;
	private String action;
	private String sonavn;
	
	public String getKnavn() {
		return knavn;
	}
	public void setKnavn(String knavn) {
		this.knavn = knavn;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getSonavn() {
		return sonavn;
	}
	public void setSonavn(String sonavn) {
		this.sonavn = sonavn;
	}
	public String getKundnr() {
		return kundnr;
	}
	public void setKundnr(String kundnr) {
		this.kundnr = kundnr;
	}
	public String getFirma() {
		return firma;
	}
	public void setFirma(String firma) {
		this.firma = firma;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
	
}
