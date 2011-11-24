package models;

import java.io.Serializable;
import java.util.List;

public class PatientInfo extends BaseModel implements Serializable {
	private static final long serialVersionUID = -1691781881788990803L;
	private String name;
	private String clientId;
	private String warnings;
	private List<Prescription> prescriptions;
	
	public PatientInfo(String name, String id, String warnings){
		this.name = name;
		this.clientId = id;
		this.warnings = warnings;
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
}
