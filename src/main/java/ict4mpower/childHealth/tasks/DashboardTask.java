package ict4mpower.childHealth.tasks;

import ict4mpower.childHealth.panels.dash.GrowthChartPanel;
import ict4mpower.childHealth.panels.dash.SpecialCarePanel;
import tasks.Task;

/**
 * Dashboard task
 * @author Joakim Lindskog
 *
 */
public class DashboardTask extends Task {
	private static final long serialVersionUID = 4884749884686716502L;

	/**
	 * Constructor
	 * @param name the name of the task
	 */
	public DashboardTask(String name) {
		super(name);
		
		// Growth chart
		add(new GrowthChartPanel("growth_chart"));
		
		// Reasons for special care
		add(new SpecialCarePanel("special_care"));
	}
}
