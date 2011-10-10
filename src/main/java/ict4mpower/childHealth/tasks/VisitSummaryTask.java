package ict4mpower.childHealth.tasks;

import ict4mpower.childHealth.panels.summary.VisitSummaryPanel;
import tasks.Task;

public class VisitSummaryTask extends Task {
	private static final long serialVersionUID = 4884749884686716502L;

	public VisitSummaryTask(String name) {
		super(name);
		
		add(new VisitSummaryPanel("summary"));
	}
}
