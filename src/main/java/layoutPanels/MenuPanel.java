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
				try{
					pp.set("taskname", goals.getTasks(goal).getTaskByNumber(0).getName());
				}catch(NullPointerException e){
					pp.set("taskName", "None set");
				}
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
