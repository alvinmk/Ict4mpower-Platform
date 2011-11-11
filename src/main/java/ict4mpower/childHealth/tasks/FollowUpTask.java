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
