package layout;

import ict4mpower.MockPatient;
import tasks.Goals;


public class GoalsAndTasks {
	private Goals g = new Goals();
	
	public GoalsAndTasks(){
				
		
		g.addGoal("patients");
		g.addTask("patients", "Task1");
		g.addTask("patients", "Task2");
		
		g.addGoal("Overview");
		g.addTask("Overview", "Task1");
			
			
	}
	
	public Goals getGoals(){
		return g;
	}
	
}
