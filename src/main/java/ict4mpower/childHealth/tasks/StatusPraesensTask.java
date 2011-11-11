/*
 *  This file is part of the ICT4MPOWER platform.
 *
 *  The ICT4MPOWER platform is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  The ICT4MPOWER platform is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with the ICT4MPOWER platform.  If not, see <http://www.gnu.org/licenses/>.
 */
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
