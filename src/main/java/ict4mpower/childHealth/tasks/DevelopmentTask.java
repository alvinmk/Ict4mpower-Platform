package ict4mpower.childHealth.tasks;

import ict4mpower.childHealth.panels.development.DevelopmentPanel;
import tasks.Task;

public class DevelopmentTask extends Task {
	private static final long serialVersionUID = -3665809588362188452L;

	public DevelopmentTask(String name) {
		super(name);
		
		add(new DevelopmentPanel("milestones"));
	}
}
