/*
 *  This file is part of the ICT4MPOWER platform.
 *
 *  The ICT4MPOWER platform is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  The ICT4MPOWER platform is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with the ICT4MPOWER platform.  If not, see <http://www.gnu.org/licenses/>.
 */
package layoutPanels;

import layout.Template;

import org.apache.log4j.Logger;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.*;
import org.apache.wicket.markup.html.link.*;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

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
		final String selectedTask = params.get("taskname").toString();
		final String selectedgoal=  params.get("goalname").toString();
		
		add(new ListView<String>("processlist", tasks.getTaskNames()) {
			private static final long serialVersionUID = 1L;
			// This method is called for each 'entry' in the list.
			@Override protected void populateItem(ListItem<String> item) {
				String task = item.getModelObject();
				log.info(task.toString());
				if( task.equals(selectedTask) ){
					item.add(new AttributeAppender("class", new Model<String>("selected"), " "));					
				}
				PageParameters pp = new PageParameters();
				pp.set("taskname",task);
				pp.set("goalname", selectedgoal);
				BookmarkablePageLink<Template> link = new BookmarkablePageLink<Template>("tasklink", Template.class, pp);
				//Add the label as a resourceModel for localization
				Label l = new Label("taskname", new ResourceModel((String) task));
				link.add(l);
				item.add(link);
			}		
		});
	}
}
