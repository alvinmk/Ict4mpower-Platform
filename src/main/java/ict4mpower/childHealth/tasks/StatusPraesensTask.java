package ict4mpower.childHealth.tasks;

import ict4mpower.childHealth.panels.status.CheckUpPanel;
import ict4mpower.childHealth.panels.status.ComplaintsPanel;
import ict4mpower.childHealth.panels.status.ConclusionPanel;
import ict4mpower.childHealth.panels.status.RecentHealthProblemsPanel;

import tasks.Task;

/**
 * Status praesens task
 * @author Joakim Lindskog
 *
 */
public class StatusPraesensTask extends Task {
	private static final long serialVersionUID = 2903452079635015272L;

	/**
	 * Constructor
	 * @param name the name of the task
	 */
	public StatusPraesensTask(String name) {
		super(name);
		
		// Complaints
		add(new ComplaintsPanel("complaints"));
		
		// Recent Health Problems
		add(new RecentHealthProblemsPanel("recent_problems"));
		
		// Check-up
		add(new CheckUpPanel("check_up"));
		
		// Conclusion
		add(new ConclusionPanel("conclusion"));
	}
}
