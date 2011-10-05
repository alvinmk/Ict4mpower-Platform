package ict4mpower.childHealth.tasks;

import ict4mpower.childHealth.panels.dash.GrowthChartPanel;
import ict4mpower.childHealth.panels.dash.SpecialCarePanel;
import tasks.Task;

public class DashboardTask extends Task {
	private static final long serialVersionUID = 4884749884686716502L;

	public DashboardTask(String name) {
		super(name);
		
		// Growth chart
		add(new GrowthChartPanel("growth_chart"));
		
		// Reasons for special care
		add(new SpecialCarePanel("special_care"));
	}
}
