package ict4mpower.childHealth.tasks;

import ict4mpower.childHealth.panels.followUp.FollowUpPanel;
import tasks.Task;

/**
 * Follow-up task
 * @author Joakim Lindskog
 *
 */
public class FollowUpTask extends Task {
	private static final long serialVersionUID = 4884749884686716502L;

	/**
	 * Constructor
	 * @param name the name of the task
	 */
	public FollowUpTask(String name) {
		super(name);
		
		// Make follow-up visit
		add(new FollowUpPanel("followUp"));
	}
}
