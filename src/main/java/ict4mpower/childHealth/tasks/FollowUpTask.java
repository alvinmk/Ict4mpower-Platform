package ict4mpower.childHealth.tasks;

import ict4mpower.childHealth.panels.followUp.FollowUpPanel;
import tasks.Task;

public class FollowUpTask extends Task {
	private static final long serialVersionUID = 4884749884686716502L;

	public FollowUpTask(String name) {
		super(name);
		
		// Make follow-up visit
		add(new FollowUpPanel("followUp"));
	}
}
