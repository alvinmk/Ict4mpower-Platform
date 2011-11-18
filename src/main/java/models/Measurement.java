package models;

import java.io.Serializable;
import java.util.Date;

public class Measurement implements Serializable{
	private static final long serialVersionUID = -6731657733712542402L;
	private Date date;
	private String patientId;
	private Double value;
	private String measurement;
	private String unit;
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getMeasurement() {
		return measurement;
	}
	public void setMeasurement(String measurement) {
		this.measurement = measurement;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}	
}