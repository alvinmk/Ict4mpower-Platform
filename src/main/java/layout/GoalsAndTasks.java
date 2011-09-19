package layout;

import org.apache.log4j.Logger;

import authentication.OpenIdCallbackPage;
import tasks.Goals;
import tasks.Task;
import tasks.Task1;
import tasks.Task2;
import tasks.TaskList;

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
