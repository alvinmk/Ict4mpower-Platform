package ict4mpower.childHealth.tasks;

import ict4mpower.childHealth.panels.growth.FeedingPanel;
import ict4mpower.childHealth.panels.growth.GrowthIndicatorsPanel;
import ict4mpower.childHealth.panels.growth.PMTCTRecordPanel;
import tasks.Task;

/**
 * Growth task
 * @author Joakim Lindskog
 *
 */
public class GrowthTask extends Task {
	private static final long serialVersionUID = 2326422988561164400L;

	/**
	 * Constructor
	 * @param name the name of the task
	 */
	public GrowthTask(String name) {
		super(name);
		
		// Growth table
		add(new GrowthIndicatorsPanel("growth"));
		
		// Feeding choices
		add(new FeedingPanel("feeding"));
		
		// PMTCT record
		add(new PMTCTRecordPanel("pmtct"));
	}
}
