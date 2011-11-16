package models;

import java.io.Serializable;

public class BaseModel implements Serializable{
	private String application;
	private Long visit;
	private String patientId;
	private String type;
	public String getApplication() {
		return application;
	}
	public void setApplication(String application) {
		this.application = application;
	}
	public Long getVisit() {
		return visit;
	}
	public void setVisit(Long visit) {
		this.visit = visit;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
}
