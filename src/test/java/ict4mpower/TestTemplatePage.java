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
import pl.rabbitsoftware.EnhancedWicketTester;


/**
 * Simple test using the WicketTester
 */
public class TestTemplatePage extends TestCase
{
	private WicketTester tester;
	EnhancedWicketTester enhanced = new EnhancedWicketTester(tester);

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
	
	public void notestState(){
		tester.startComponentInPage(new StatePanel("statePanel"));
		tester.assertContains("CLIENT NAME");
		tester.assertContains("WARNINGS: WARNINGS");
		tester.assertContains("DATE");
		tester.assertContains("VISIT SIGNED");
	}

	
	public void testProcess(){		
		PageParameters pp = new PageParameters();
		GoalsAndTasks gt = new GoalsAndTasks();
		ProcessPanel p = new ProcessPanel("process", pp, gt.getGoals().getTasks("patients"));
		tester.startComponentInPage(p);	
		tester.assertContains("Task1");
		tester.assertContains("Task2");
	}

	public void testClientPanel(){
		tester.startComponentInPage(new ClientPanel("clientPanel"));
		tester.assertContains("CLIENT INFORMATION");
	}
	
	public void testVisitPanel(){
		VisitPanel v = new VisitPanel("visitPanel");
		tester.startComponentInPage(v);
		tester.assertContains("No visit");
	}

	public void testUserPanel(){
		UserPanel u = new UserPanel("userPanel", "Current Application");
		tester.startComponentInPage(u);
		tester.assertContains("Current Application");
	}
	
	public void testMenu(){
		PageParameters pp = new PageParameters();
		GoalsAndTasks gt = new GoalsAndTasks();
		MenuPanel m = new MenuPanel("menu", pp, gt.getGoals().getGoals());
		tester.startComponentInPage(m);
		tester.assertContains("patient");
		tester.assertContains("Overview");
	}

}