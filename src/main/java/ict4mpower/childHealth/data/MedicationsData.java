/*
 *  This file is part of the ICT4MPOWER platform.
 *
 *  The ICT4MPOWER platform is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  The ICT4MPOWER platform is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with the ICT4MPOWER platform.  If not, see <http://www.gnu.org/licenses/>.
 */
package ict4mpower.childHealth.data;

import ict4mpower.childHealth.ChildHealthData;
import ict4mpower.childHealth.panels.medications.Medicine;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import models.BaseModel;

/**
 * Data class for the Medications task
 * @author Joakim Lindskog
 *
 */
public class MedicationsData extends BaseModel implements ChildHealthData {
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
	
	/** Date of last edit */
	private Date date;
	
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
	
	/**
	 * Gets a list of medicines from today
	 * @param meds the list of medicines to search
	 * @return a list of medicines from today or null if the supplied list is null
	 */
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

	public void setDate(Date date) {
		this.date = date;
	}
	
	public Date getDate() {
		return date;
	}

	public void reset() {
		setAntimalarial(null);
		setDate(null);
		setDeworming(null);
		setOtherMeds(null);
		setVitamins(null);
	}
	
	public MedicationsData clone() {
		MedicationsData data = new MedicationsData();
		data.setAntimalarial(antimalarial);
		data.setDate(date);
		data.setDeworming(deworming);
		data.setOtherMeds(otherMeds);
		data.setVitamins(vitamins);
		return data;
	}
}
