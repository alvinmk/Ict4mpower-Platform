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
import ict4mpower.childHealth.panels.development.Milestone;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Data class for the Development task
 * @author Joakim Lindskog
 *
 */
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
	/** Date of last edit */
	private Date date;
	
	private DevelopmentData() {}

	public List<Milestone> getMilestones() {
		return milestones;
	}

	public void setMilestones(List<Milestone> milestones) {
		this.milestones = milestones;
	}

	/**
	 * Gets the milestone for today (if there is one)
	 * @return today's milestone
	 */
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
	
	public DevelopmentData clone() {
		DevelopmentData data = new DevelopmentData();
		data.setDate(date);
		data.setMilestones(milestones);
		return data;
	}
}
