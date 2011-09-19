package layoutPanels;


import java.util.*;

import layout.Template;

import org.apache.log4j.Logger;
import org.apache.wicket.PageParameters;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.*;
import org.apache.wicket.markup.html.link.*;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;

import authentication.OpenIdCallbackPage;

import tasks.Task;
import tasks.Task1;
import tasks.TaskList;
 
/**
 * Shows a process and it's contained tasks.
 * 
 * @author Alvin Mattsson Kjellqvist
 */
public class ProcessPanel extends Panel {
	private static final long serialVersionUID = 1L;
	static final Logger log = Logger.getLogger(ProcessPanel.class);
	
	public ProcessPanel(String id, PageParameters params, final TaskList tasks){
		super(id);
		log.info("Adding " +tasks.getTaskNames().size() +" To panel");
		final String selectedTask = params.getString("taskname");
		final String selectedgoal= params.getString("goalname");
		
		add(new ListView<String>("processlist", tasks.getTaskNames()) {
			private static final long serialVersionUID = 1L;
			// This method is called for each 'entry' in the list.
			@Override protected void populateItem(ListItem item) {
				String task = (String)item.getModelObject();
				log.info(task.toString());
				if( task.equals(selectedTask) ){
					
					item.add(new AttributeAppender("class", new Model<String>("selected"), " "));					
				}
				PageParameters pp = new PageParameters("taskname=" +task+",goalname="+selectedgoal);
				BookmarkablePageLink<Template> link = new BookmarkablePageLink<Template>("tasklink", Template.class, pp);
				//Add the label as a resourceModel for localization
				Label l = new Label("taskname", new ResourceModel((String) task));
				link.add(l);
				item.add(link);
				
			}		
		});
		
	}
}