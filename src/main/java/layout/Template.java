package layout;


import ict4mpower.AppSession;
import layoutPanels.MenuPanel;
import layoutPanels.ProcessPanel;
import layoutPanels.StatePanel;
import layoutPanels.UserPanel;

import org.apache.log4j.Logger;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import tasks.Task;
import tasks.TaskList;

/*
 * The basic strucutre of the template page and the components it contains.
 */
public class Template extends WebPage {
	private static final long serialVersionUID = 7656338216865551821L;
	static final Logger log = Logger.getLogger(Template.class);
	
	public Template(final PageParameters parameters) {
		add(new Label("title", new StringResourceModel("application_title", this, null)));
		
		//Parse the parameter and add the corresponding tab
		//If no argument set use first tab
		GoalsAndTasks gt = new GoalsAndTasks();
		TaskList taskList;
		
		String goal = (String) (parameters.get("goalname").toString() != null ? parameters.get("goalname").toString() : "none");

		// Add goal and task names to session
		AppSession session = (AppSession)getSession();
		session.setGoal(goal);
		session.setTask(parameters.get("taskname").toString());
		
		if(goal.equals("none")){
			add( new Label("task", ""));
			taskList = new TaskList();
		}
		else{
			
			String task = (String) (parameters.get("taskname").toString() != null ? parameters.get("taskname").toString() : "none");
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
		
		//The processpanel on top, uses the taskList to keep track of available tabs.
		ProcessPanel p = new ProcessPanel("process", parameters, taskList );
		add(p);
		
		MenuPanel m = new MenuPanel("menu", parameters, gt.getGoals());
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
	}
}
