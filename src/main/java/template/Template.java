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
import templatePanels.WorkingObjectPanel;

public class Template extends WebPage {

	public Template(final PageParameters parameters) {
		//This is a temprary solution to keep track of which task and goal the user is in
		PageParameters previousparams = new PageParameters("goalname=" +parameters.getString("goalname")); 
		List<LinkButton> tasks = getTasks(); //Replace with your task list
		ProcessPanel p = new ProcessPanel("process", parameters, tasks );
		MenuPanel m = new MenuPanel("menu", parameters.getString("goalname"), tasks );
		UserPanel u = new UserPanel("userPanel");
		WorkingObjectPanel wp = new WorkingObjectPanel("workingObject");
		add(wp);
		add(u);
		add(p);
		add(m);
	}
	
	//Dummy code to the the template	
	private List<LinkButton> getTasks(){
		List<LinkButton> tasks = new ArrayList<LinkButton>();
		tasks.add(new LinkButton("Task1", "Task1"));
		tasks.add(new LinkButton("Task2", "Task2"));
		tasks.add(new LinkButton("Task3", "Task3"));
		return tasks;
	}
}
