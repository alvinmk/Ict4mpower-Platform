package ict4mpower.childHealth.tasks;

import ict4mpower.childHealth.panels.development.DevelopmentPanel;
import tasks.Task;

/**
 * Development task
 * @author Joakim Lindskog
 *
 */
public class DevelopmentTask extends Task {
	private static final long serialVersionUID = -3665809588362188452L;

	/**
	 * Constructor
	 * @param name the name of the task
	 */
	public DevelopmentTask(String name) {
		super(name);
		
		// Development milestones
		add(new DevelopmentPanel("milestones"));
	}
}
