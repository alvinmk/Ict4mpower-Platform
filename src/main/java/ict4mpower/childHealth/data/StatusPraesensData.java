package ict4mpower.childHealth.data;

import ict4mpower.childHealth.ChildHealthData;

import java.util.List;

public class StatusPraesensData implements ChildHealthData {
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
}
