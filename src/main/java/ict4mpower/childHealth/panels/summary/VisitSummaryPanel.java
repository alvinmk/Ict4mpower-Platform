package ict4mpower.childHealth.panels.summary;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.model.Model;

import ict4mpower.AppSession;
import ict4mpower.childHealth.data.FollowUpData;
import ict4mpower.childHealth.data.GrowthData;
import ict4mpower.childHealth.panels.DivisionPanel;
import ict4mpower.childHealth.panels.growth.Indicator;

public class VisitSummaryPanel extends DivisionPanel {
	private static final long serialVersionUID = -1172223673548889654L;
	
	private DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	private DateFormat time = new SimpleDateFormat("HH:mm");

	public VisitSummaryPanel(String id) {
		super(id, "title");
		
		AppSession session = (AppSession)getSession();
		
		String goal = session.getGoal();
		
		// Growth
		GrowthData growth = (GrowthData) session.getAttribute(goal+":GrowthTask");
		CheckBox growthCheck = new CheckBox("growthCheck", new Model<Boolean>(growth != null));
		if(growth == null) growthCheck.setEnabled(false);
		add(growthCheck);
		Indicator indi = growth != null && growth.getIndicators() != null
				? growth.getIndicators().get(growth.getIndicators().size()-1) : null;
		if(indi != null) { // Check if last measurement was done today
			Calendar m = Calendar.getInstance();
			m.setTime(growth.getIndicators().get(growth.getIndicators().size()-1).getDateValue());
			Calendar today = Calendar.getInstance();
			if(m.get(Calendar.YEAR) != today.get(Calendar.YEAR) || m.get(Calendar.DAY_OF_YEAR) != today.get(Calendar.DAY_OF_YEAR)) {
				indi = null;
			}
		}
		add(new Label("growthHead", indi != null ? indi.getHeadCircumference() : "-"));
		add(new Label("growthLength", indi != null ? indi.getLength() : "-"));
		add(new Label("growthWeight", indi != null ? indi.getWeight() : "-"));
		add(new Label("growthFeeding", growth != null && growth.getFeeding() != null
				? getString(growth.getFeeding()) : "-"));
		add(new Label("growthPMTCT", growth != null && growth.getPmtct() != null
				? getString(growth.getPmtct()) : "-"));
		
		// Follow up
		FollowUpData followUp = (FollowUpData) session.getAttribute(goal+":FollowUpTask");
		CheckBox followUpCheck = new CheckBox("followUpCheck", new Model<Boolean>(followUp != null));
		if(followUp == null) followUpCheck.setEnabled(false);
		add(followUpCheck);
		add(new Label("followUpDate", followUp != null ? df.format(followUp.getDate()) : null));
		add(new Label("followUpTime", followUp != null ? time.format(followUp.getDate()) : null));
		add(new MultiLineLabel("followUpNote", followUp != null ? followUp.getMessage() : null));
	}
}
