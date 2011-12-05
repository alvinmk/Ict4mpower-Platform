package ict4mpower;

import junit.framework.TestCase;
import layout.GoalsAndTasks;
import layout.Template;
import layoutPanels.ClientPanel;
import layoutPanels.MenuPanel;
import layoutPanels.ProcessPanel;
import layoutPanels.StatePanel;
import layoutPanels.UserPanel;
import layoutPanels.VisitPanel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.tester.WicketTester;

import tasks.Goals;


/**
 * Simple test using the WicketTester
 */
public class TestTemplatePage extends TestCase
{
	private WicketTester tester;
	//EnhancedWicketTester enhanced = new EnhancedWicketTester(tester);

	@Override
	public void setUp()
	{
		tester = new WicketTester(new WicketApplication());
	}

	public void testRenderTemplatePage()
	{
		//start and render the test page
		tester.startPage(Template.class);

		//assert rendered page class
		tester.assertRenderedPage(Template.class);

		//assert rendered label component
		tester.assertLabel("task", "");
		
	}
	
	public void testState(){
		tester.startComponentInPage(new StatePanel("statePanel"));
		tester.assertContains("No patient selected");
		tester.assertContains("No warnings");
	}

	
	public void testProcess(){		
		PageParameters pp = new PageParameters();
		GoalsAndTasks gt = new GoalsAndTasks();
		ProcessPanel p = new ProcessPanel("process", pp, gt.getGoals().getTasks("ChildHealth"));
		tester.startComponentInPage(p);	
		tester.assertContains("DashboardTask");
		tester.assertContains("GrowthTask");
	}

	public void testClientPanel(){
		tester.startComponentInPage(new ClientPanel("clientPanel"));
		tester.assertContains("CLIENT INFORMATION");
	}
	
	public void testVisitPanel(){
		VisitPanel v = new VisitPanel("visitPanel");
		tester.startComponentInPage(v);
		tester.assertContains("0");
	}

	public void testUserPanel(){
		UserPanel u = new UserPanel("userPanel", "Current Application");
		tester.startComponentInPage(u);
		tester.assertContains("Current Application");
	}
	
	public void testMenu(){
		PageParameters pp = new PageParameters();
		GoalsAndTasks gt = new GoalsAndTasks();
		MenuPanel m = new MenuPanel("menu", pp, gt.getGoals());
		tester.startComponentInPage(m);
		GoalsAndTasks g = new GoalsAndTasks();
		for(String goal : g.getGoals().getGoals() ){
			tester.assertContains(goal);
		}
		
	}

}