package ict4mpower.childHealth.tasks;

import ict4mpower.childHealth.panels.education.EducationPanel;
import tasks.Task;

public class EducationTask extends Task {
	private static final long serialVersionUID = -5097289369108874682L;

	public EducationTask(String name) {
		super(name);
		
		add(new EducationPanel("education"));
	}
}
