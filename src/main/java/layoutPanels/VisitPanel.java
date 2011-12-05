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
import java.util.List;

import ict4mpower.AppSession;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;


public class VisitPanel extends Panel {
	private static final long serialVersionUID = 1L;

	public VisitPanel(String id) {
		super(id);
		AppSession session = (AppSession) getSession();
		List<Long> visitList = session.getAllVisits();
		if(visitList == null){
			visitList = new ArrayList<Long>();
			visitList.add(0l);
		}
		long currentVisit = session.getCurrentVisit();
		
		DropDownChoice<Long> visits = new DropDownChoice<Long>("visitList", new Model<Long>(currentVisit), visitList){
			private static final long serialVersionUID = 6239600167733224223L;
			@Override
			protected void onSelectionChanged(Long newSelection) {
				AppSession s = (AppSession) getSession();
				s.setCurrentVisit(newSelection);
				super.onSelectionChanged(newSelection);
			}
			
		};
		add(visits);
		add( new Label("visitStage", "VISIT SIGNED"));
	}
}
