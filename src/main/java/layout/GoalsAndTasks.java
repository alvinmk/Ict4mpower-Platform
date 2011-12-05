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
package layout;

import ict4mpower.MockPatient;
import tasks.Goals;

public class GoalsAndTasks {
	private Goals g = new Goals();
	
	public GoalsAndTasks(){
		// Demographics
		g.addGoal("Demographics");
		g.addTask("Demographics", "AdditionalInfoTask");
		
		// Main part of Child Health application
		g.addGoal("ChildHealth");
		g.addTask("ChildHealth", "DashboardTask");
		g.addTask("ChildHealth", "GrowthTask");
		g.addTask("ChildHealth", "ImmunizationTask");
		g.addTask("ChildHealth", "StatusPraesensTask");
		g.addTask("ChildHealth", "MedicationsTask");
		g.addTask("ChildHealth", "DevelopmentTask");
		g.addTask("ChildHealth", "EducationTask");
		g.addTask("ChildHealth", "FollowUpTask");
		g.addTask("ChildHealth", "VisitSummaryTask");
	}
	
	public Goals getGoals(){
		return g;
	}
}
