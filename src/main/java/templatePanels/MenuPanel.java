package templatePanels;


import java.util.List;

import org.apache.wicket.PageParameters;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

import template.Template;

public class MenuPanel extends Panel {
		private static final long serialVersionUID = 1L;

		public MenuPanel(String id, final String selectedTask, List<LinkButton> goals){
			super(id);
			add(new ListView<LinkButton>("processlist", goals) {
				private static final long serialVersionUID = 1L;
				// This method is called for each 'entry' in the list.
				@Override protected void populateItem(ListItem<LinkButton> item) {
					final LinkButton goal = (LinkButton)item.getModelObject();
					PageParameters pp = new PageParameters("goalname=" +goal.parameter);
					BookmarkablePageLink<Template> link = new BookmarkablePageLink<Template>("tasklink", Template.class, pp);
					Label l = new Label("taskname", goal.name);
					if(goal.name.equals(selectedTask)){
						l.add(new AttributeAppender("class", new Model<String>("selected"), " "));
					}
					link.add(l);
					item.add(link);
					
				}		
			});
			
		}
		
}
