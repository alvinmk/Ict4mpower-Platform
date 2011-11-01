package ict4mpower.childHealth.panels.summary;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import layout.Template;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow.WindowClosedCallback;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import storage.DataEndPoint;

import ict4mpower.AppSession;
import ict4mpower.childHealth.Callback;
import ict4mpower.childHealth.ChildHealthData;
import ict4mpower.childHealth.SummaryCheck;
import ict4mpower.childHealth.data.AdditionalData;
import ict4mpower.childHealth.data.CheckInfoData;
import ict4mpower.childHealth.data.CheckableOption;
import ict4mpower.childHealth.data.DevelopmentData;
import ict4mpower.childHealth.data.EducationData;
import ict4mpower.childHealth.data.FollowUpData;
import ict4mpower.childHealth.data.GrowthData;
import ict4mpower.childHealth.data.ImmunizationData;
import ict4mpower.childHealth.data.MedicationsData;
import ict4mpower.childHealth.data.StatusPraesensData;
import ict4mpower.childHealth.panels.ConfirmDialog;
import ict4mpower.childHealth.panels.DivisionPanel;
import ict4mpower.childHealth.panels.development.Milestone;
import ict4mpower.childHealth.panels.growth.Indicator;
import ict4mpower.childHealth.panels.immunization.Vaccination;
import ict4mpower.childHealth.panels.medications.Medicine;

public class VisitSummaryPanel extends DivisionPanel {
	private static final long serialVersionUID = -1172223673548889654L;
	
	private DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	private DateFormat time = new SimpleDateFormat("HH:mm");
	private ConfirmDialog dialog;
	private boolean signed = false;

	public VisitSummaryPanel(String id) {
		super(id, "title");
		
		Object sgn = getSession().getAttribute("ChildHealth:VisitSummarySigned");
		if(sgn != null && (Boolean)sgn) {
			info(new StringResourceModel("visit_signed", this, null).getObject());
			getSession().setAttribute("ChildHealth:VisitSummarySigned", false);
		}
		
		dialog = new ConfirmDialog("dialog");
		dialog.setLabel("Are you certain you want to sign this visit?");
		dialog.setWindowClosedCallback(new WindowClosedCallback() {
			private static final long serialVersionUID = 1L;

			public void onClose(AjaxRequestTarget target) {
				System.out.println("Refreshed");
				if(signed) {
					signed = false;
					PageParameters pp = new PageParameters();
					pp.set("taskname", "VisitSummaryTask");
					pp.set("goalname", "ChildHealth");
					getSession().setAttribute("ChildHealth:VisitSummarySigned", true);
					VisitSummaryPanel.this.setResponsePage(Template.class, pp);
				}
			}
		});
		dialog.addOnSubmit(new Callback() {
			private static final long serialVersionUID = 1L;

			public boolean call(AjaxRequestTarget target) {
				if(saveVisit()) {
					signed = true;
					return true;
				}
				return false;
			}
		});
		add(dialog);
		
		saveButton.add(new AjaxFormComponentUpdatingBehavior("onclick") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				dialog.show(target);
			}
		});
		
		AppSession session = (AppSession)getSession();
		
		String goal = session.getGoal()+":";
		
		/*
		 * Growth
		 */
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
		add(new Label("growthHead", indi != null ? Float.toString(indi.getHeadCircumference()) : "-"));
		add(new Label("growthLength", indi != null ? Float.toString(indi.getLength()) : "-"));
		add(new Label("growthWeight", indi != null ? Float.toString(indi.getWeight()) : "-"));
		add(new Label("growthFeeding", growth != null && growth.getFeeding() != null
				? getString(growth.getFeeding()) : "-"));
		add(new Label("growthPMTCT", growth != null && growth.getPmtct() != null
				? getString(growth.getPmtct()) : "-"));
		add(new Label("growthHIVTest", growth != null && growth.getHivTestRadio() != null
				? getString(growth.getHivTestRadio()) : "-"));
		add(new MultiLineLabel("growthInitTreatment", growth != null && growth.getInitTreatmentRadio() != null
				? getString(growth.getInitTreatmentRadio())+"\n &nbsp; &nbsp;"+getString("growth_init_date")+": "
						+(growth.getInitTreatmentDate() != null ? df.format(growth.getInitTreatmentDate()) : "-")
						: "-").setEscapeModelStrings(false));
		
		/*
		 * Immunization
		 */
		ImmunizationData imm = (ImmunizationData) session.getAttribute(goal+"ImmunizationTask");
		add(new SummaryCheck("immCheck", imm));
		// Vaccinations
		String vacc = "";
		if(imm != null && imm.getVaccinationsToday() != null) {
			for(Vaccination v : imm.getVaccinationsToday()) {
				vacc += v.getVaccine()+"\n &nbsp; &nbsp;"+v.getDosage()+" - "+getString("serial_nr")+": "+v.getSerial_nr()+"\n";
			}
		}
		add(new MultiLineLabel("vaccinations", vacc).setEscapeModelStrings(false));
		
		/*
		 * Status praesens
		 */
		StatusPraesensData status = (StatusPraesensData) session.getAttribute(goal+"StatusPraesensTask");
		add(new SummaryCheck("statusCheck", status));
		// Complaints
		add(new Label("statusComplaints", status != null ? status.getComplaintsText() : null));
		// Conclusion
		add(new Label("statusConclusion", status != null ? status.getConclusionText() : null));
		// Recent health problems
		String problems = "";
		if(status != null && status.getRecentHealthProblems() != null) {
			for(CheckInfoData info : status.getRecentHealthProblems()) {
				if(info.isCheck()) problems += info.getLabel()
						+(info.getInfo() != null ? "\n &nbsp; &nbsp;"+info.getInfo() : "")+"\n";
			}
		}
		add(new MultiLineLabel("statusProblems", problems).setEscapeModelStrings(false));
		// Check-up
		String checkUp = "";
		if(status != null && status.getCheckUp() != null) {
			for(CheckInfoData info : status.getCheckUp()) {
				if(info.isCheck()) checkUp += info.getLabel()
						+(info.getInfo() != null ? "\n &nbsp; &nbsp;"+info.getInfo() : "")+"\n";
			}
		}
		add(new MultiLineLabel("statusCheckUp", checkUp).setEscapeModelStrings(false));
		
		/*
		 * Medications
		 */
		MedicationsData med = (MedicationsData) session.getAttribute(goal+"MedicationsTask");
		add(new SummaryCheck("medCheck", med));
		// Vitamin A supplement
		String vita = "";
		if(med != null && med.getVitaminsToday() != null) {
			for(Medicine m : med.getVitaminsToday()) {
				vita += m.getName()+"\n &nbsp; &nbsp;"+m.getDosage()+" - "+getString("batch_nr")+": "+m.getBatchNr()+"\n";
			}
		}
		add(new MultiLineLabel("medVitaminA", vita).setEscapeModelStrings(false));
		// De-worming medication
		String worm = "";
		if(med != null && med.getDewormingToday() != null) {
			for(Medicine m : med.getDewormingToday()) {
				worm += m.getName()+"\n &nbsp; &nbsp;"+m.getDosage()+" - "+getString("batch_nr")+": "+m.getBatchNr()+"\n";
			}
		}
		add(new MultiLineLabel("medDeWorming", worm).setEscapeModelStrings(false));
		// Antimalarial medication
		String anti = "";
		if(med != null && med.getAntimalarialToday() != null) {
			for(Medicine m : med.getAntimalarialToday()) {
				anti += m.getName()+"\n &nbsp; &nbsp;"+m.getDosage()+" - "+getString("batch_nr")+": "+m.getBatchNr()+"\n";
			}
		}
		add(new MultiLineLabel("medAntimalarial", anti).setEscapeModelStrings(false));
		// Other medication
		String other = "";
		if(med != null && med.getOtherToday() != null) {
			for(Medicine m : med.getOtherToday()) {
				other += m.getName()+"\n &nbsp; &nbsp;"+m.getDosage()+" - "+m.getInstructions()+"\n";
			}
		}
		add(new MultiLineLabel("medOther", other).setEscapeModelStrings(false));
		
		/*
		 * Development
		 */
		DevelopmentData dev = (DevelopmentData) session.getAttribute(goal+"DevelopmentTask");
		add(new SummaryCheck("devCheck", dev));
		String testWell = "", testBehind = "", testNot = "";
		if(dev != null) {
			Milestone m = dev.getTodaysMilestone();
			if(m != null) {
				Object[][] mile = new Object[][]{
						new Object[]{m.tests.grossMotor, m.grossMotor},
						new Object[]{m.tests.fineMotor, m.fineMotor},
						new Object[]{m.tests.communication, m.communication},
						new Object[]{m.tests.cognitive, m.cognitive},
						new Object[]{getString("eyesight")+" "+getString("left"), m.eyesight[0]},
						new Object[]{getString("eyesight")+" "+getString("right"), m.eyesight[1]},
						new Object[]{getString("hearing")+" "+getString("left"), m.hearing[0]},
						new Object[]{getString("hearing")+" "+getString("right"), m.hearing[1]}
				};
				for(Object[] o : mile) {
					switch((Short)o[1]) {
					case 0:
						// Well developed
						if(o[0] == null) break;
						testWell += o[0]+"\n";
						break;
					case 1:
						// Stays behind
						if(o[0] == null) break;
						testBehind += o[0]+"\n";
						break;
					case 2:
						// Not developed
						if(o[0] == null) break;
						testNot += o[0]+"\n";
						break;
					}
				}
			}
		}
		add(new MultiLineLabel("devWell", testWell).setEscapeModelStrings(false));
		add(new MultiLineLabel("devBehind", testBehind).setEscapeModelStrings(false));
		add(new MultiLineLabel("devNot", testNot).setEscapeModelStrings(false));
		
		/*
		 * Education
		 */
		EducationData edu = (EducationData) session.getAttribute(goal+"EducationTask");
		add(new SummaryCheck("education", edu));
		String fText = "", oText = "", iText = "";
		if(edu != null) {
			List<List<CheckableOption>> feeding = edu.getFeedingOptions();
			if(feeding != null) {
				for(List<CheckableOption> l : feeding) {
					for(CheckableOption co : l) {
						if(co.isChecked()) fText += getString(co.getOption())+"\n";
					}
				}
			}
			List<List<CheckableOption>> oral = edu.getOralOptions();
			if(oral != null) {
				for(List<CheckableOption> l : oral) {
					for(CheckableOption co : l) {
						if(co.isChecked()) oText += getString(co.getOption())+"\n";
					}
				}
			}
			List<List<CheckableOption>> infections = edu.getFeedingOptions();
			if(infections != null) {
				for(List<CheckableOption> l : infections) {
					for(CheckableOption co : l) {
						if(co.isChecked()) iText += getString(co.getOption())+"\n";
					}
				}
			}
		}
		add(new MultiLineLabel("feeding", fText));
		add(new MultiLineLabel("oral", oText));
		add(new MultiLineLabel("infections", iText));
		
		/*
		 * Additional info
		 */
		AdditionalData additional = (AdditionalData) session.getAttribute("Demographics:AdditionalInfoTask");
		add(new SummaryCheck("additionalCheck", additional));
		add(new MultiLineLabel("reasons", additional != null ? additional.getReasonsAsString(this) : null));
		
		/*
		 * Follow-up visit
		 */
		FollowUpData followUp = (FollowUpData) session.getAttribute(goal+"FollowUpTask");
		add(new SummaryCheck("followUpCheck", followUp));
		add(new Label("followUpDate", followUp != null ? df.format(followUp.getDate()) : null));
		add(new Label("followUpTime", followUp != null ? time.format(followUp.getDate()) : null));
		add(new MultiLineLabel("followUpNote", followUp != null ? followUp.getMessage() : null));
	}
	
	private boolean saveVisit() {
		AppSession session = (AppSession)getSession();
		String goal = session.getGoal()+":";
		DataEndPoint dep = DataEndPoint.getDataEndPoint();
		
		Serializable[] data = new Serializable[]{
				session.getAttribute(goal+"GrowthTask"),
				session.getAttribute(goal+"ImmunizationTask"),
				session.getAttribute(goal+"StatusPraesensTask"),
				session.getAttribute(goal+"MedicationsTask"),
				session.getAttribute(goal+"DevelopmentTask"),
				session.getAttribute(goal+"EducationTask"),
				session.getAttribute(goal+"FollowUpTask"),
				session.getAttribute("Demographics:AdditionalInfoTask")
		};
		for(Serializable s : data) {
			// TODO What is visit id?
			if(s != null) {
				dep.signEntry(s, session.getPatientInfo().getClientId(), /*session.getCurrentVisit()*/1L, "ChildHealth");
			}
		}
		dep.save();
		
		// Reset data
		session.replaceSession();
		for(Serializable s : data) {
			if(s != null) {
				((ChildHealthData)s).reset();
			}
		}
		return true;
	}
}
