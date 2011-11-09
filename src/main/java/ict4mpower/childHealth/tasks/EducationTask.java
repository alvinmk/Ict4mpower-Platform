package ict4mpower.childHealth.tasks;

import ict4mpower.childHealth.panels.education.EducationPanel;
import tasks.Task;

/**
 * Education task
 * @author Joakim Lindskog
 *
 */
public class EducationTask extends Task {
	private static final long serialVersionUID = -5097289369108874682L;

	/**
	 * Constructor
	 * @param name the name of the task
	 */
	public EducationTask(String name) {
		super(name);
		
		// Educational information
		add(new EducationPanel("education"));
	}
}
