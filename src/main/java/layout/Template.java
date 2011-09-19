package layout;


import java.util.ArrayList;
import java.util.List;

import layoutPanels.LinkButton;
import layoutPanels.MenuPanel;
import layoutPanels.ProcessPanel;
import layoutPanels.StatePanel;
import layoutPanels.UserPanel;

import org.apache.log4j.Logger;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

import authentication.OpenIdCallbackPage;
import authentication.OpenIdConsumer;

import tasks.Task;
import tasks.Task1;
import tasks.Task2;
import tasks.TaskList;

/*
 * The basic strucutre of the template page and the components it contains.
 */
public class Template extends WebPage {
	static final Logger log = Logger.getLogger(Template.class);
	
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
			
			String task = parameters.getString("taskname") != null ? parameters.getString("taskname") : "none";
			log.debug("goal is " +goal +" Finding task " +task);
			taskList = gt.getGoals().getTasks(goal);
			Task t = taskList.getTaskPanel(task);
			if(t != null){
				add(t);
			}
			else{
				add( new Label("task", ""));
			}
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
		
		//A feedbackPanel
		FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
		
	    add(feedbackPanel);
	    info("Here we go!");
		
	}
	
	
}
