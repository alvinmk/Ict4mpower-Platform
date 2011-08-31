package template;


import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

import authentication.OpenIdCallbackPage;

import tasks.Task;
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

		
		//Parse the parameter and add the coresponding tab
		//If no argument set use first tab
		GoalsAndTasks gt = new GoalsAndTasks();
		TaskList taskList;
		String goal = parameters.getString("goalname") != null ? parameters.getString("goalname") : "none";
		if(goal.equals("none")){
			add( new Label("task", ""));
			taskList = new TaskList();
			
		}
		else{
			log.info("goal is " +goal +" Finding task ");
			int index = parameters.getInt("taskname", -1) != -1  ? parameters.getInt("taskname") : 0;
			taskList = gt.getGoals().getTasks(goal);
			Task t = taskList.getTaskByNumber(index); 		
			add(t);
			
		}
		
		
		
		//The procespanel on top, uses the taskList to keep track of available tabs.
		ProcessPanel p = new ProcessPanel("process", parameters, taskList );
		add(p);
		
		MenuPanel m = new MenuPanel("menu", parameters, gt.getGoals().getGoals());
		add(m);
		
		//A panel that keeps track of the user and the users information
		UserPanel u = new UserPanel("userPanel", "Current Application");
		add(u);
		
		//A panel that keeps track of the applications state. Shows clients, visits and so on.
		StatePanel sp = new StatePanel("statePanel");
		add(sp);
	}
	
	
}
