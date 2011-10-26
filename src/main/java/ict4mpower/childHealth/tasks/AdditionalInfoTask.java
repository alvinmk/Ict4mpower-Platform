package ict4mpower.childHealth.tasks;

import ict4mpower.childHealth.panels.additional.ReasonsPanel;

import tasks.Task;

public class AdditionalInfoTask extends Task {
	private static final long serialVersionUID = 4884749884686716502L;

	public AdditionalInfoTask(String name) {
		super(name);
		
		// Reasons for special care
		add(new ReasonsPanel("reasons"));
	}
}
