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

import ict4mpower.childHealth.panels.medications.AntimalarialPanel;
import ict4mpower.childHealth.panels.medications.DeWormingPanel;
import ict4mpower.childHealth.panels.medications.OtherMedicationsPanel;
import ict4mpower.childHealth.panels.medications.VitaminASupplementationPanel;

import tasks.Task;

/**
 * Medications task
 * @author Joakim Lindskog
 *
 */
public class MedicationsTask extends Task {
	private static final long serialVersionUID = 7860744301857688767L;

	/**
	 * Constructor
	 * @param name the name of the task
	 */
	public MedicationsTask(String name) {
		super(name);
		
		// Vitamin A supplements
		add(new VitaminASupplementationPanel("vitaminA"));
		
		// De-worming treatment
		add(new DeWormingPanel("deworming"));
		
		// Antimalarial treatment
		add(new AntimalarialPanel("antimalarial"));
		
		// Other medications
		add(new OtherMedicationsPanel("otherMeds"));
	}
}
