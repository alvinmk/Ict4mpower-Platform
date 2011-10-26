package ict4mpower;

import java.util.List;

import junit.framework.TestCase;
import layout.GoalsAndTasks;
import layout.Template;
import layoutPanels.ClientPanel;
import layoutPanels.ContentPanel;
import layoutPanels.MenuPanel;
import layoutPanels.ProcessPanel;
import layoutPanels.StatePanel;
import layoutPanels.UserPanel;
import layoutPanels.VisitPanel;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.tester.ITestPanelSource;
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
	
	public void testState(){
		tester.startPanel(StatePanel.class);
		tester.assertContains("CLIENT NAME");
		tester.assertContains("WARNINGS: WARNINGS");
		tester.assertContains("DATE");
		tester.assertContains("VISIT SIGNED");
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
		tester.startPanel(ClientPanel.class);
		tester.assertContains("CLIENT NAME");
	}
	
	public void testVisitPanel(){
		VisitPanel v = new VisitPanel("visitPanel");
		tester.startComponent(v);
		tester.assertContains("DATE");
	}

	public void testUserPanel(){
		UserPanel u = new UserPanel("userPanel", "Current Application");
		tester.startComponent(u);
		tester.assertContains("Current Application ");
	}
	
	public void testMenu(){
		PageParameters pp = new PageParameters();
		GoalsAndTasks gt = new GoalsAndTasks();
		MenuPanel m = new MenuPanel("menu", pp, gt.getGoals());
		tester.startComponent(m);
		tester.assertContains("ChildHealth");
		tester.assertContains("Demographics");
	}

}