package template;


import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.odlabs.wiquery.ui.dialog.Dialog;
import org.odlabs.wiquery.ui.accordion.Accordion;
import org.odlabs.wiquery.core.events.WiQueryEventBehavior;
import org.odlabs.wiquery.core.events.Event;
import org.odlabs.wiquery.core.events.MouseEvent;
import org.odlabs.wiquery.core.javascript.JsScope;

import templatePanels.LinkButton;
import templatePanels.MenuPanel;
import templatePanels.ProcessPanel;
import templatePanels.UserPanel;
import templatePanels.StatePanel;

public class Template extends WebPage {

	public Template(final PageParameters parameters) {
		//This is a temprary solution to keep track of which task and goal the user is in
		PageParameters previousparams = new PageParameters("goalname=" +parameters.getString("goalname")); 
		List<LinkButton> tasks = getTasks(); //Replace with your task list
		List<LinkButton> goals = getGoals(); //Replace with your goal list
		
		ProcessPanel p = new ProcessPanel("process", parameters, tasks );
		add(p);
		
		MenuPanel m = new MenuPanel("menu", parameters.getString("goalname"), goals );
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
		return tasks;
	}
	
	private List<LinkButton> getGoals(){
		List<LinkButton> tasks = new ArrayList<LinkButton>();
		tasks.add(new LinkButton("Goal 1", "Goal 1"));
		tasks.add(new LinkButton("Goal 2", "Goal 2"));
		tasks.add(new LinkButton("Goal 3", "Goal 3"));
		return tasks;
	}
}
