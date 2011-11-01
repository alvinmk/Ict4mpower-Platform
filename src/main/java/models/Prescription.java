package models;

import java.io.Serializable;
import java.util.Date;

public class Prescription implements Serializable{
	private Date Start;
	private Date end;
	private String drug;
	private String dosage;
	private Boolean replaceble; 
	private int visitId;
	private String reason;
	public Date getStart() {
		return Start;
	}
	public void setStart(Date start) {
		Start = start;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	public String getDrug() {
		return drug;
	}
	public void setDrug(String drug) {
		this.drug = drug;
	}
	public String getDosage() {
		return dosage;
	}
	public void setDosage(String dosage) {
		this.dosage = dosage;
	}
	public Boolean getReplaceble() {
		return replaceble;
	}
	public void setReplaceble(Boolean replaceble) {
		this.replaceble = replaceble;
	}
	public int getVisitId() {
		return visitId;
	}
	public void setVisitId(int visitId) {
		this.visitId = visitId;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	
}
