package ict4mpower.childHealth.data;

import ict4mpower.childHealth.ChildHealthData;
import ict4mpower.childHealth.panels.immunization.Vaccination;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Data class for the Immunization task
 * @author Joakim Lindskog
 *
 */
public class ImmunizationData implements ChildHealthData {
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
}
