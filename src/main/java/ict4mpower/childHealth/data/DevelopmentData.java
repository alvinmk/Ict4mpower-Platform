package ict4mpower.childHealth.data;

import ict4mpower.childHealth.panels.development.Milestone;

import java.io.Serializable;
import java.util.List;

public class DevelopmentData implements Serializable {
	private static final long serialVersionUID = -3866379165135838860L;

	private static DevelopmentData instance = null;
	
	public static DevelopmentData instance() {
		if(instance == null) {
			instance = new DevelopmentData();
		}
		return instance;
	}
	
	private List<Milestone> milestones;
	
	private DevelopmentData() {}

	public List<Milestone> getMilestones() {
		return milestones;
	}

	public void setMilestones(List<Milestone> milestones) {
		this.milestones = milestones;
	}
}
