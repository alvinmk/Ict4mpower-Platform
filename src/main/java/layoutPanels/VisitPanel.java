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

	/**
	 * 
	 */
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
