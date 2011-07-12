package templatePanels;


import java.util.*;

import org.apache.wicket.PageParameters;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.*;
import org.apache.wicket.markup.html.link.*;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

import template.Template;
 
/**
 * Shows a process and it's contained tasks.
 * 
 * @author Alvin Mattsson Kjellqvist
 */
public class ProcessPanel extends Panel {
	private static final long serialVersionUID = 1L;

	public ProcessPanel(String id, PageParameters params, List<LinkButton> tasks){
		super(id);
		final String selectedTask = params.getString("taskname");
		final String selectedgoal= params.getString("goalname");
		add(new ListView<LinkButton>("processlist", tasks) {
			private static final long serialVersionUID = 1L;
			// This method is called for each 'entry' in the list.
			@Override protected void populateItem(ListItem<LinkButton> item) {
				final LinkButton task = (LinkButton)item.getModelObject();
				if(task.name.equals(selectedTask)){
					item.add(new AttributeAppender("class", new Model<String>("selected"), " "));
				}
				PageParameters pp = new PageParameters("taskname=" +task.parameter+",goalname="+selectedgoal);
				BookmarkablePageLink<Template> link = new BookmarkablePageLink<Template>("tasklink", Template.class, pp);
				link.add(new Label("taskname", task.name));
				item.add(link);
				
			}		
		});
		
	}
}