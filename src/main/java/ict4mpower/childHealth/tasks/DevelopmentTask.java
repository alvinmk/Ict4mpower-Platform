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
