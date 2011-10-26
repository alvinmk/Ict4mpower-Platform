package layoutPanels;

import java.util.ArrayList;
import java.util.Set;

import layout.Template;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import tasks.Goals;

public class MenuPanel extends Panel {
	private static final long serialVersionUID = 1L;

	public MenuPanel(String id, PageParameters params, final Goals goals){
		
		super(id);
		final String selectedGoal= params.get("goalname").toString();
		Set<String> goalNames = goals.getGoals();
		add(new ListView<String> ("processlist", new ArrayList<String>(goalNames) ) {
			private static final long serialVersionUID = 1L;
			// This method is called for each 'entry' in the list.
			@Override protected void populateItem(ListItem<String> item) {
				final String goal = (String) item.getModelObject();
				PageParameters pp = new PageParameters();
				pp.set("goalname", goal);
				pp.set("taskname", goals.getTasks(goal).getTaskByNumber(0).getName());
				BookmarkablePageLink<Template> link = new BookmarkablePageLink<Template>("tasklink", Template.class, pp);
				Label l = new Label("taskname", goal);
				if(goal.equals(selectedGoal)){
					l.add(new AttributeAppender("class", new Model<String>("selected"), " "));
				}
				link.add(l);
				item.add(link);
				
			}		
		});
		
	}
}
