package layout;

import tasks.Goals;


public class GoalsAndTasks {
	private Goals g = new Goals();
	
	public GoalsAndTasks(){
		g.addGoal("patients");
		g.addTask("patients", "Task1");
		g.addTask("patients", "Task2");
		
		g.addGoal("Overview");
		g.addTask("Overview", "Task1");
		
		g.addGoal("Child Health");
		g.addTask("Child Health", "DashboardTask");
		g.addTask("Child Health", "GrowthTask");
		g.addTask("Child Health", "ImmunizationTask");
		g.addTask("Child Health", "StatusPraesensTask");
		g.addTask("Child Health", "MedicationsTask");
		g.addTask("Child Health", "DevelopmentTask");
		g.addTask("Child Health", "EducationTask");
		g.addTask("Child Health", "FollowUpTask");
		g.addTask("Child Health", "VisitSummarTask");
	}
	
	public Goals getGoals(){
		return g;
	}
	
}
