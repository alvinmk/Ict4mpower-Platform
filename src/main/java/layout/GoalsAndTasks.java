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
