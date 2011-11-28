package ict4mpower.childHealth.tasks;

import ict4mpower.childHealth.panels.immunization.ImmunizationSchedulePanel;

import tasks.Task;

/**
 * Immunization task
 * @author Joakim Lindskog
 *
 */
public class ImmunizationTask extends Task {
	private static final long serialVersionUID = 2901676079635015272L;

	/**
	 * Constructor
	 * @param name the name of the task
	 */
	public ImmunizationTask(String name) {
		super(name);
		
		// Immunization schedule
		add(new ImmunizationSchedulePanel("schedule"));
	}
}
