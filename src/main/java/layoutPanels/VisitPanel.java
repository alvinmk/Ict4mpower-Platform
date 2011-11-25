package layoutPanels;

import java.util.ArrayList;
import java.util.List;

import ict4mpower.AppSession;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;


public class VisitPanel extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VisitPanel(String id) {
		super(id);
		AppSession session = (AppSession) getSession();
		List<String> visitList = session.getAllVisits();
		if(visitList == null){
			visitList = new ArrayList<String>();
			visitList.add("No visit");
		}
		String currentVisit = session.getCurrentVisit();
		if(currentVisit == null){
			currentVisit = "No visit";
		}
		DropDownChoice<String> visits = new DropDownChoice<String>("visitList", new Model<String>(currentVisit), visitList){
			private static final long serialVersionUID = 6239600167733224223L;
			@Override
			protected void onSelectionChanged(String newSelection) {
				AppSession s = (AppSession) getSession();
				s.setCurrentVisit(newSelection);
				super.onSelectionChanged(newSelection);
			}
			
		};
		add(visits);
		add( new Label("visitStage", "VISIT SIGNED"));
	}
	
}
