package ict4mpower.childHealth.data;

import ict4mpower.childHealth.panels.medications.Medicine;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MedicationsData implements Serializable {
	private static final long serialVersionUID = 4239968609396691757L;
	
	private static MedicationsData instance = null;
	
	public static MedicationsData instance() {
		if(instance == null) {
			instance = new MedicationsData();
		}
		return instance;
	}

	// Vitamin A
	private List<Medicine> vitamins;
	
	// De-worming
	private List<Medicine> deworming;
	
	// Anti-malarial
	private List<Medicine> antimalarial;
	
	// Other medication
	private List<Medicine> otherMeds;
	
	private MedicationsData() {}
	
	public List<Medicine> getVitamins() {
		return vitamins;
	}

	public void setVitamins(List<Medicine> vitamins) {
		this.vitamins = vitamins;
	}

	public List<Medicine> getDeworming() {
		return deworming;
	}

	public void setDeworming(List<Medicine> deworming) {
		this.deworming = deworming;
	}

	public List<Medicine> getAntimalarial() {
		return antimalarial;
	}

	public void setAntimalarial(List<Medicine> antimalarial) {
		this.antimalarial = antimalarial;
	}

	public List<Medicine> getOtherMeds() {
		return otherMeds;
	}

	public void setOtherMeds(List<Medicine> otherMeds) {
		this.otherMeds = otherMeds;
	}

	public List<Medicine> getVitaminsToday() {
		return getFromToday(vitamins);
	}

	public List<Medicine> getDewormingToday() {
		return getFromToday(deworming);
	}

	public List<Medicine> getAntimalarialToday() {
		return getFromToday(antimalarial);
	}

	public List<Medicine> getOtherToday() {
		return getFromToday(otherMeds);
	}
	
	private List<Medicine> getFromToday(List<Medicine> meds) {
		if(meds == null) return null;
		List<Medicine> list = new ArrayList<Medicine>();
		Calendar mCal = Calendar.getInstance();
		Calendar today = Calendar.getInstance();
		for(Medicine m : meds) {
			if(m.getGivenDate() == null) continue;
			mCal.setTime(m.getGivenDate());
			if(mCal.get(Calendar.YEAR) == today.get(Calendar.YEAR)
					&& mCal.get(Calendar.DAY_OF_YEAR) == today.get(Calendar.DAY_OF_YEAR)) {
				list.add(m);
			}
		}
		return list;
	}
}
