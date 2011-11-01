package ict4mpower.childHealth.data;

import ict4mpower.childHealth.ChildHealthData;
import ict4mpower.childHealth.panels.development.Milestone;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DevelopmentData implements ChildHealthData {
	private static final long serialVersionUID = -3866379165135838860L;

	private static DevelopmentData instance = null;
	
	public static DevelopmentData instance() {
		if(instance == null) {
			instance = new DevelopmentData();
		}
		return instance;
	}
	
	private List<Milestone> milestones;
	private Date date;
	
	private DevelopmentData() {}

	public List<Milestone> getMilestones() {
		return milestones;
	}

	public void setMilestones(List<Milestone> milestones) {
		this.milestones = milestones;
	}

	public Milestone getTodaysMilestone() {
		if(milestones == null) return null;
		Calendar mCal = Calendar.getInstance();
		Calendar today = Calendar.getInstance();
		for(Milestone m : milestones) {
			if(m.date == null) continue;
			mCal.setTime(m.date);
			if(mCal.get(Calendar.YEAR) == today.get(Calendar.YEAR)
					&& mCal.get(Calendar.DAY_OF_YEAR) == today.get(Calendar.DAY_OF_YEAR)) {
				return m;
			}
		}
		return null;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDate() {
		return date;
	}

	public void reset() {
		setDate(null);
		setMilestones(null);
	}
}
