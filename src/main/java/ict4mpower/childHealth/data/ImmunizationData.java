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
import ict4mpower.childHealth.panels.immunization.Vaccination;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import models.BaseModel;

/**
 * Data class for the Immunization task
 * @author Joakim Lindskog
 *
 */
public class ImmunizationData extends BaseModel implements ChildHealthData {
	private static final long serialVersionUID = -553200314531540733L;
	
	private static ImmunizationData instance = null;
	
	public static ImmunizationData instance() {
		if(instance == null) {
			instance = new ImmunizationData();
		}
		return instance;
	}

	private List<Vaccination> vaccinations;
	/** Date of last edit */
	private Date date;
	
	private ImmunizationData() {}

	public List<Vaccination> getVaccinations() {
		return vaccinations;
	}

	public void setVaccinations(List<Vaccination> vaccinations) {
		this.vaccinations = vaccinations;
	}

	/**
	 * Gets today's vaccinations (if any)
	 * @return today's vaccinations or null if vaccinations are null
	 */
	public List<Vaccination> getVaccinationsToday() {
		if(vaccinations == null) return null;
		List<Vaccination> list = new ArrayList<Vaccination>();
		Calendar mCal = Calendar.getInstance();
		Calendar today = Calendar.getInstance();
		for(Vaccination v : vaccinations) {
			if(v.getGivenDate() == null) continue;
			mCal.setTime(v.getGivenDateValue());
			if(mCal.get(Calendar.YEAR) == today.get(Calendar.YEAR)
					&& mCal.get(Calendar.DAY_OF_YEAR) == today.get(Calendar.DAY_OF_YEAR)) {
				list.add(v);
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
		setDate(null);
		setVaccinations(null);
	}
	
	public ImmunizationData clone() {
		ImmunizationData data = new ImmunizationData();
		data.setDate(date);
		data.setVaccinations(vaccinations);
		return data;
	}
}
