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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import ict4mpower.AppSession;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;

public class VisitPanel extends Panel {
	private static final long serialVersionUID = 1L;

	public VisitPanel(String id) {
		super(id);
		AppSession session = (AppSession) getSession();
		List<Long> allVisits = session.getAllVisits();
		Collections.sort(allVisits);
		List<String> l = new ArrayList<String>();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		for(long ts : allVisits) {
			l.add(df.format(new Date(ts)));
		}
		if(l.isEmpty()){
			l.add("No visits available");
		}
		DropDownChoice<String> visits = new DropDownChoice<String>("visitList", new PropertyModel<String>(this, "session.currentVisit"), l);
		add(visits);
		add( new Label("visitStage", "VISIT SIGNED"));
	}
}
