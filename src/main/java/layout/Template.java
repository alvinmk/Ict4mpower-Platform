package layout;


import ict4mpower.AppSession;
import ict4mpower.MockPatient;
import layoutPanels.MenuPanel;
import layoutPanels.ProcessPanel;
import layoutPanels.StatePanel;
import layoutPanels.UserPanel;

import org.apache.log4j.Logger;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
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
		AppSession session = (AppSession)getSession();
		
		//--Add some mock data, this will be removed--
		//Mock goals and tasks
		GoalsAndTasks gt = new GoalsAndTasks();
		//Mock session data
		MockPatient mock = new MockPatient();
		session.setUserID("Alvin");
		session.setAllVisits(mock.visits);
		session.setCurrentVisit(mock.visits.get(0));
		session.setPatientInfo(mock.pi);
		//--END OF MOCK DATA--
		
		TaskList taskList;
		//Parse the parameter and add the coresponding tab
		//If no argument set use first tab				
		String goal = (String) (parameters.get("goalname").toString() != null ? parameters.get("goalname").toString() : "none");
		
		// Add goal and task names to session
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
