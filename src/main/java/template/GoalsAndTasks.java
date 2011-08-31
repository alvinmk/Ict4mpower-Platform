package template;

import tasks.Goals;
import tasks.Task1;
import tasks.Task2;
import tasks.TaskList;

public class GoalsAndTasks {
	private Goals g = new Goals();
	
	public GoalsAndTasks(){
				
		Task1 t1 = new Task1("Task 1");
		Task2 t2 = new Task2("Task 2");
		
		
		g.addGoal("patients");
		g.addTask("patients", t1);
		g.addTask("patients", t2);
		
		g.addGoal("Overview");
		g.addTask("Overview", t1);
			
	}
	
	public Goals getGoals(){
		return g;
	}
	
}
