package ict4mpower.childHealth.data;

import ict4mpower.childHealth.panels.immunization.Vaccination;

import java.io.Serializable;
import java.util.List;

public class ImmunizationData implements Serializable {
	private static final long serialVersionUID = -553200314531540733L;
	
	private static ImmunizationData instance = null;
	
	public static ImmunizationData instance() {
		if(instance == null) {
			instance = new ImmunizationData();
		}
		return instance;
	}

	private List<Vaccination> vaccinations;
	
	private ImmunizationData() {}

	public List<Vaccination> getVaccinations() {
		return vaccinations;
	}

	public void setVaccinations(List<Vaccination> vaccinations) {
		this.vaccinations = vaccinations;
	}
}
