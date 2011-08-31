package templatePanels;


import java.util.*;

import org.apache.wicket.PageParameters;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.*;
import org.apache.wicket.markup.html.link.*;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

import tasks.Task;
import tasks.Task1;
import tasks.TaskList;
import template.Template;
 
/**
 * Shows a process and it's contained tasks.
 * 
 * @author Alvin Mattsson Kjellqvist
 */
public class ProcessPanel extends Panel {
	private static final long serialVersionUID = 1L;

	public ProcessPanel(String id, PageParameters params, final TaskList tasks){
		super(id);
		final String selectedTask = params.getString("taskname");
		final String selectedgoal= params.getString("goalname");
		add(new ListView<HashMap>("processlist", tasks.getNames()) {
			private static final long serialVersionUID = 1L;
			// This method is called for each 'entry' in the list.
			@Override protected void populateItem(ListItem item) {
				HashMap task = (HashMap)item.getModelObject();
				if( task.get("index").toString().equals(selectedTask) ){
					item.add(new AttributeAppender("class", new Model<String>("selected"), " "));					
				}
				PageParameters pp = new PageParameters("taskname=" +task.get("index")+",goalname="+selectedgoal);
				BookmarkablePageLink<Template> link = new BookmarkablePageLink<Template>("tasklink", Template.class, pp);
				link.add(new Label("taskname", (String) task.get("name") ));
				item.add(link);
				
			}		
		});
		
	}
}