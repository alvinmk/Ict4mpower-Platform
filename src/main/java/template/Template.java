package template;


import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;

import authentication.OpenIdCallbackPage;

import tasks.Tab;
import tasks.Task1;
import tasks.Task2;
import tasks.TaskList;
import templatePanels.LinkButton;
import templatePanels.MenuPanel;
import templatePanels.ProcessPanel;
import templatePanels.StatePanel;
import templatePanels.UserPanel;

/*
 * The basic strucutre of the template page and the components it contains.
 */
public class Template extends WebPage {
	final Logger log = Logger.getLogger(Template.class);
	
	public Template(final PageParameters parameters) {
		List<LinkButton> tasks = getTasks(); //Replace with your task list
		List<LinkButton> goals = getGoals(); //Replace with your goal list
		TaskList taskList = new TaskList("Patient");
		Task1 t1 = new Task1("Task 1");
		taskList.addTask(t1);		
		Task2 t2 = new Task2("Task 2");
		taskList.addTask(t2);
		
		//Parse the parameter and add the coresponding tab
		//If no argument set use first tab
		int index = parameters.getInt("taskname") != FLAG_INITIALIZED ? parameters.getInt("taskname") : 0;
		Tab t = taskList.getTaskByNumber(index);
		add(t);
		
		
		
		ProcessPanel p = new ProcessPanel("process", parameters, taskList );
		add(p);
		
		MenuPanel m = new MenuPanel("menu", parameters, goals );
		add(m);
		
		//A panel that keeps track of the user and the users information
		UserPanel u = new UserPanel("userPanel", "Current Application");
		add(u);
		
		//A panel that keeps track of the applications state. Shows clients, visits and so on.
		StatePanel sp = new StatePanel("statePanel");
		add(sp);
	}
	
	//Dummy code to the the template	
	private List<LinkButton> getTasks(){
		List<LinkButton> tasks = new ArrayList<LinkButton>();
		tasks.add(new LinkButton("Task1", "Task1"));
		tasks.add(new LinkButton("Task2", "Task2"));
		tasks.add(new LinkButton("Task3", "Task3"));
		tasks.add(new LinkButton("Task4", "Task4"));
		tasks.add(new LinkButton("Task5", "Task5"));
		return tasks;
	}
	
	
	private List<LinkButton> getGoals(){
		List<LinkButton> tasks = new ArrayList<LinkButton>();
		tasks.add(new LinkButton("Patient", "Goal 1"));
		tasks.add(new LinkButton("Waiting List", "Goal 2"));
		tasks.add(new LinkButton("Appointment", "Goal 3"));
		return tasks;
	}
}
