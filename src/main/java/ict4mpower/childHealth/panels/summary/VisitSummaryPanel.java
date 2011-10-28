package ict4mpower.childHealth.panels.summary;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.model.Model;

import ict4mpower.AppSession;
import ict4mpower.childHealth.SummaryCheck;
import ict4mpower.childHealth.data.CheckInfoData;
import ict4mpower.childHealth.data.FollowUpData;
import ict4mpower.childHealth.data.GrowthData;
import ict4mpower.childHealth.data.ImmunizationData;
import ict4mpower.childHealth.data.MedicationsData;
import ict4mpower.childHealth.data.StatusPraesensData;
import ict4mpower.childHealth.panels.DivisionPanel;
import ict4mpower.childHealth.panels.growth.Indicator;

public class VisitSummaryPanel extends DivisionPanel {
	private static final long serialVersionUID = -1172223673548889654L;
	
	private DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	private DateFormat time = new SimpleDateFormat("HH:mm");

	public VisitSummaryPanel(String id) {
		super(id, "title");
		
		AppSession session = (AppSession)getSession();
		
		String goal = session.getGoal()+":";
		
		// Growth
		GrowthData growth = (GrowthData) session.getAttribute(goal+"GrowthTask");
		add(new SummaryCheck("growthCheck", growth));
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
		
		// Immunization
		ImmunizationData imm = (ImmunizationData) session.getAttribute(goal+"ImmunizationTask");
		add(new SummaryCheck("immCheck", imm));
		// TODO Show info
		
		// Status praesens
		StatusPraesensData status = (StatusPraesensData) session.getAttribute(goal+"StatusPraesensTask");
		add(new SummaryCheck("statusCheck", status));
		add(new Label("statusComplaints", status != null ? status.getComplaintsText() : null));
		add(new Label("statusConclusion", status != null ? status.getConclusionText() : null));
		String problems = "";
		if(status != null && status.getRecentHealthProblems() != null) {
			for(CheckInfoData info : status.getRecentHealthProblems()) {
				if(info.isCheck()) problems += info.getLabel()
						+(info.getInfo() != null ? "\n &nbsp; &nbsp;"+info.getInfo() : "")+"\n\n";
			}
		}
		add(new MultiLineLabel("statusProblems", problems).setEscapeModelStrings(false));
		String checkUp = "";
		if(status != null && status.getCheckUp() != null) {
			for(CheckInfoData info : status.getCheckUp()) {
				if(info.isCheck()) checkUp += info.getLabel()
						+(info.getInfo() != null ? "\n &nbsp; &nbsp;"+info.getInfo() : "")+"\n\n";
			}
		}
		add(new MultiLineLabel("statusCheckUp", checkUp).setEscapeModelStrings(false));
		
		// Medications
		MedicationsData med = (MedicationsData) session.getAttribute(goal+"MedicationsTask");
		add(new SummaryCheck("medCheck", med));
		
		// Follow up
		FollowUpData followUp = (FollowUpData) session.getAttribute(goal+"FollowUpTask");
		add(new SummaryCheck("followUpCheck", followUp));
		add(new Label("followUpDate", followUp != null ? df.format(followUp.getDate()) : null));
		add(new Label("followUpTime", followUp != null ? time.format(followUp.getDate()) : null));
		add(new MultiLineLabel("followUpNote", followUp != null ? followUp.getMessage() : null));
	}
}
