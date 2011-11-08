package models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class PatientInfo implements Serializable {
	private static final long serialVersionUID = -1691781881788990803L;
	
	public enum Sex {
		FEMALE,
		MALE
	}
	private String name;
	private String clientId;
	private String warnings;
	private List<Prescription> prescriptions;
	private Date birthDate;
	private Sex sex;
	
	public PatientInfo(String name, String id, String warnings, Date birthDate, Sex sex){
		this.name = name;
		this.clientId = id;
		this.warnings = warnings;
		this.birthDate = birthDate;
		this.sex = sex;
	}
	
	public String getClientId() {
		return clientId;
	}
	
	public String getName() {
		return name;
	}

	public String getWarnings() {
		return warnings;
	}

	public void setWarnings(String warnings) {
		this.warnings = warnings;
	}

	public List<Prescription> getPrescriptions() {
		return prescriptions;
	}

	public void setPrescriptions(List<Prescription> prescriptions) {
		this.prescriptions = prescriptions;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birth) {
		this.birthDate = birth;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public Sex getSex() {
		return sex;
	}
}
