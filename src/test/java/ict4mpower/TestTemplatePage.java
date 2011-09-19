package ict4mpower;

import junit.framework.TestCase;
import layout.Template;
import layoutPanels.UserPanel;

import org.apache.wicket.util.tester.WicketTester;


/**
 * Simple test using the WicketTester
 */
public class TestTemplatePage extends TestCase
{
	private WicketTester tester;

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
		tester.assertLabel("message", "If you see this message wicket is properly configured and running");
	}
	
	public void testUserPanel(){
		tester.startPanel(UserPanel.class);
		
	}
}
