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

import java.util.List;

import models.BaseModel;

/**
 * Data class for the StatusPraesens task
 * @author Joakim Lindskog
 *
 */
public class StatusPraesensData extends BaseModel implements ChildHealthData {
	private static final long serialVersionUID = -7582569994675017144L;
	
	private static StatusPraesensData instance = null;
	
	public static StatusPraesensData instance() {
		if(instance == null) {
			instance = new StatusPraesensData();
		}
		return instance;
	}
	
	// Complaints
	private String complaintsText;
	
	// Recent health problems
	private List<CheckInfoData> recentHealthProblems;
	
	// Check-up
	private List<CheckInfoData> checkUp;
	
	// Conclusion
	private String conclusionText;
	
	private StatusPraesensData() {}

	public String getComplaintsText() {
		return complaintsText;
	}

	public void setComplaintsText(String complaintsText) {
		this.complaintsText = complaintsText;
	}

	public List<CheckInfoData> getRecentHealthProblems() {
		return recentHealthProblems;
	}

	public void setRecentHealthProblems(List<CheckInfoData> recentHealthProblems) {
		this.recentHealthProblems = recentHealthProblems;
	}

	public List<CheckInfoData> getCheckUp() {
		return checkUp;
	}

	public void setCheckUp(List<CheckInfoData> checkUp) {
		this.checkUp = checkUp;
	}

	public String getConclusionText() {
		return conclusionText;
	}

	public void setConclusionText(String conclusionText) {
		this.conclusionText = conclusionText;
	}

	public void reset() {
		setCheckUp(null);
		setComplaintsText(null);
		setConclusionText(null);
		setRecentHealthProblems(null);
	}
	
	public StatusPraesensData clone() {
		StatusPraesensData data = new StatusPraesensData();
		data.setCheckUp(checkUp);
		data.setComplaintsText(complaintsText);
		data.setConclusionText(conclusionText);
		data.setRecentHealthProblems(recentHealthProblems);
		return data;
	}
}
