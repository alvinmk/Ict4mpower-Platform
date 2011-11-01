package ict4mpower.childHealth.panels.development;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import layout.Template;
import models.PatientInfo;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import ict4mpower.AppSession;
import ict4mpower.childHealth.SavingForm;
import ict4mpower.childHealth.data.DevelopmentData;
import ict4mpower.childHealth.data.FollowUpData;
import ict4mpower.childHealth.panels.DivisionPanel;

public class DevelopmentPanel extends DivisionPanel {
	private static final long serialVersionUID = -1172218871548889654L;
	
	private ListView<Milestone> list;
	private NextMilestonePanel nextPanel;
	private MilestoneTests current;
	
	// TODO Temporary
	private PatientInfo pi = ((AppSession)getSession()).getPatientInfo();
	private List<MilestoneTests> tests = Arrays.asList(
					new MilestoneTests(pi, Calendar.WEEK_OF_YEAR, 4, "Symmetric spontaneous motor skills",
							null, "Follows objects with eyes", null, this),
					new MilestoneTests(pi, Calendar.WEEK_OF_YEAR, 6, "Holds up the head lying on stomach<br/>Opens hands",
							null, "Smiles at parents<br/>Responds to sounds", null, this),
					new MilestoneTests(pi, Calendar.MONTH, 6, "Turns around<br/>Pulls self up towards a sitting position",
							"Transfers objects from one hand to the other",
							"Looks for the dropped object<br/>Makes double syllable sounds such as 'mumum' and 'dada'",
							null, this),
					new MilestoneTests(pi, Calendar.MONTH, 10, "Rises, walks with support",
							"Picks up objects with pincergrasp",
							"Hits two objects against each other",
							null, this)
			);

	public DevelopmentPanel(String id) {
		super(id, "title", false);
		
		DevelopmentForm form = new DevelopmentForm("form");
		add(form);
	}
	
	private class DevelopmentForm extends SavingForm {
		private static final long serialVersionUID = 54717230506400089L;

		public DevelopmentForm(String id) {
			super(id);
			
			//TODO Temporary
			List<Milestone> milestones = new ArrayList<Milestone>();
			try {
				milestones.add(new Milestone(tests.get(0), null, (short)0, (short)0, (short)0, (short)0, new Short[]{0,0}, new Short[]{0,0}));
				milestones.add(new Milestone(tests.get(1), null, (short)1, (short)0, (short)1, (short)0, new Short[]{0,0}, new Short[]{0,0}));
			} catch(Exception e) {
				//
			}
			
			DevelopmentData data = DevelopmentData.instance();
			// TODO Temporary
			if(data.getMilestones() == null) {
				data.setMilestones(milestones);
			}
			
			list = new ListView<Milestone>("milestones", new PropertyModel<List<Milestone>>(data, "milestones")) {
				private static final long serialVersionUID = 1L;

				@Override
				protected void populateItem(ListItem<Milestone> item) {
					item.add(new MilestonePanel("rowPanel", item.getModel()));
				}
			};
			add(list);
			
			//TODO Temporary
			current = tests.get(data.getMilestones().size()).clone();
			
			nextPanel = new NextMilestonePanel("nextMilestonePanel", this, DevelopmentPanel.this, current);
			nextPanel.setOutputMarkupId(true);
			add(nextPanel, false);
		}
		
		@Override
		protected void onSubmit() {
			super.onSubmit();
			
			MilestoneTests t = nextPanel.getTests();
			List<Milestone> l = list.getModelObject();
			
			// Add saved milestone to table
			if(l.size() < tests.size()) {
				l.add(new Milestone(tests.get(l.size()),
					new Date(),
					NextMilestonePanel.convert(t.grossChoice),
					NextMilestonePanel.convert(t.fineChoice),
					NextMilestonePanel.convert(t.commChoice),
					NextMilestonePanel.convert(t.cogChoice),
					new Short[]{NextMilestonePanel.convert(t.hearLeftChoice),NextMilestonePanel.convert(t.hearRightChoice)},
					new Short[]{NextMilestonePanel.convert(t.eyeLeftChoice),NextMilestonePanel.convert(t.eyeRightChoice)}));
				// Show next milestone in lower panel
				if(l.size() < tests.size()) {
					MilestoneTests mt = tests.get(l.size());
					nextPanel.getTests().resetChoices();
					current.setDueDate(mt.getDueDate());
					current.grossMotor = mt.grossMotor;
					current.fineMotor = mt.fineMotor;
					current.communication = mt.communication;
					current.cognitive = mt.cognitive;
				}
				else {
					// No more milestones
					nextPanel.setVisible(false);
				}
			}
		}
	}
}

class MilestonePanel extends Panel {
	private static final long serialVersionUID = 3885944576231374282L;

	public MilestonePanel(String id, IModel<Milestone> milestone) {
		super(id);
		
		// Add milestone values
		add(new Label("age", new Model<String>(milestone.getObject().tests.getDueAge())));
		Label grossLabel = new Label("gross_motor", new PropertyModel<String>(milestone, "tests.grossMotor"));
		setClass(grossLabel, milestone.getObject().grossMotor);
		grossLabel.setEscapeModelStrings(false);
		add(grossLabel);
		Label fineLabel = new Label("fine_motor", new PropertyModel<String>(milestone, "tests.fineMotor"));
		setClass(fineLabel, milestone.getObject().fineMotor);
		fineLabel.setEscapeModelStrings(false);
		add(fineLabel);
		Label commLabel = new Label("communication", new PropertyModel<String>(milestone, "tests.communication"));
		setClass(commLabel, milestone.getObject().communication);
		commLabel.setEscapeModelStrings(false);
		add(commLabel);
		Label cogLabel = new Label("cognitive", new PropertyModel<String>(milestone, "tests.cognitive"));
		setClass(cogLabel, milestone.getObject().cognitive);
		cogLabel.setEscapeModelStrings(false);
		add(cogLabel);
		Label hearLeftLabel = new Label("hearing_left", new StringResourceModel("left", this, null));
		setClass(hearLeftLabel, milestone.getObject().hearing[0]);
		add(hearLeftLabel);
		Label hearRightLabel = new Label("hearing_right", new StringResourceModel("right", this, null));
		setClass(hearRightLabel, milestone.getObject().hearing[1]);
		add(hearRightLabel);
		Label eyeLeftLabel = new Label("eyesight_left", new StringResourceModel("left", this, null));
		setClass(eyeLeftLabel, milestone.getObject().eyesight[0]);
		add(eyeLeftLabel);
		Label eyeRightLabel = new Label("eyesight_right", new StringResourceModel("right", this, null));
		setClass(eyeRightLabel, milestone.getObject().eyesight[1]);
		add(eyeRightLabel);
	}
	
	private void setClass(Label label, short value) {
		switch(value) {
			case 2:
				label.add(AttributeModifier.replace("class", "not_developed"));
				break;
			case 1:
				label.add(AttributeModifier.replace("class", "stays_behind"));
				break;
			default:
				label.add(AttributeModifier.replace("class", "well_developed"));
				break;
		}
	}
}

class NextMilestonePanel extends DivisionPanel {
	private static final long serialVersionUID = 2726451125512509536L;
	
	private static List<String> development;
	
	public static short convert(String model) {
		int len = development.size();
		for(int i=0; i<len; i++) {
			if(development.get(i).equals(model)) {
				return (short)(i);
			}
		}
		return (short)-1;
	}
	
	private MilestoneTests tests;
	
	private NextMilestoneChoice grossMotor;
	private NextMilestoneChoice fineMotor;
	private NextMilestoneChoice communication;
	private NextMilestoneChoice cognitive;
	private NextMilestoneChoice hearingLeft;
	private NextMilestoneChoice hearingRight;
	private NextMilestoneChoice eyesightLeft;
	private NextMilestoneChoice eyesightRight;

	public NextMilestonePanel(String id, SavingForm form, DivisionPanel panel, MilestoneTests tests) {
		super(id, "nextMilestone");
		
		this.tests = tests;
		
		if(development == null) {
			// Initiate choices list
			 development = Arrays.asList(
						"well_developed",
						"stays_behind",
						"not_developed"
					);
		}
		
		setForm(form, panel);
		
		Label ageLabel = new Label("age", new PropertyModel<String>(this.tests, "dueAge"));
		ageLabel.setOutputMarkupId(true);
		add(ageLabel);
		// Add link to follow-up
		final PageParameters pp = new PageParameters();
		pp.set("taskname", "FollowUpTask");
		pp.set("goalname", "ChildHealth");
		Link<Template> link = new Link<Template>("link") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				FollowUpData data = FollowUpData.instance();
				data.setDate(NextMilestonePanel.this.tests.getDueDate());
				setResponsePage(Template.class, pp);
			}
		};
		// Add link text
		Label dueDateLabel = new Label("dateLink", new PropertyModel<String>(this.tests, "dueDateString"));
		link.add(dueDateLabel);
		add(link);
		
		// Gross motor
		Label grossLabel = new Label("gross_motor", new PropertyModel<String>(this.tests, "grossMotor"));
		grossLabel.setEscapeModelStrings(false);
		add(grossLabel);
		this.grossMotor = new NextMilestoneChoice("select_gross",
				new PropertyModel<String>(tests, "grossChoice"), development);
		if(!grossLabel.getDefaultModelObjectAsString().isEmpty()) this.grossMotor.setRequired(true);
		else this.grossMotor.setEnabled(false);
		add(this.grossMotor);
		
		// Fine motor
		Label fineLabel = new Label("fine_motor", new PropertyModel<String>(this.tests, "fineMotor"));
		fineLabel.setEscapeModelStrings(false);
		add(fineLabel);
		this.fineMotor = new NextMilestoneChoice("select_fine",
				new PropertyModel<String>(tests, "fineChoice"), development);
		if(!fineLabel.getDefaultModelObjectAsString().isEmpty()) this.fineMotor.setRequired(true);
		else this.fineMotor.setEnabled(false);
		add(this.fineMotor);
		
		// Communication
		Label commLabel = new Label("communication", new PropertyModel<String>(tests, "communication"));
		commLabel.setEscapeModelStrings(false);
		add(commLabel);
		this.communication = new NextMilestoneChoice("select_comm",
				new PropertyModel<String>(tests, "commChoice"), development);
		if(!commLabel.getDefaultModelObjectAsString().isEmpty()) this.communication.setRequired(true);
		else this.communication.setEnabled(false);
		add(this.communication);
		
		// Cognitive
		Label cogLabel = new Label("cognitive", new PropertyModel<String>(tests, "cognitive"));
		cogLabel.setEscapeModelStrings(false);
		add(cogLabel);
		this.cognitive = new NextMilestoneChoice("select_cog",
				new PropertyModel<String>(tests, "cogChoice"), development);
		if(!cogLabel.getDefaultModelObjectAsString().isEmpty()) this.cognitive.setRequired(true);
		else this.cognitive.setEnabled(false);
		add(this.cognitive);
		
		// Hearing left
		Label hearLeftLabel = new Label("hearing_left", new StringResourceModel("left", this, null));
		add(hearLeftLabel);
		this.hearingLeft = new NextMilestoneChoice("select_hear_left",
				new PropertyModel<String>(tests, "hearLeftChoice"), development);
		this.hearingLeft.setRequired(true);
		add(this.hearingLeft);
		
		// Hearing right
		Label hearRightLabel = new Label("hearing_right", new StringResourceModel("right", this, null));
		add(hearRightLabel);
		this.hearingRight = new NextMilestoneChoice("select_hear_right",
				new PropertyModel<String>(tests, "hearRightChoice"), development);
		this.hearingRight.setRequired(true);
		add(this.hearingRight);
		
		// Eyesight left
		Label eyeLeftLabel = new Label("eyesight_left", new StringResourceModel("left", this, null));
		add(eyeLeftLabel);
		this.eyesightLeft = new NextMilestoneChoice("select_eye_left",
				new PropertyModel<String>(tests, "eyeLeftChoice"), development);
		this.eyesightLeft.setRequired(true);
		add(this.eyesightLeft);
		
		// Eyesight right
		Label eyeRightLabel = new Label("eyesight_right", new StringResourceModel("right", this, null));
		add(eyeRightLabel);
		this.eyesightRight = new NextMilestoneChoice("select_eye_right",
				new PropertyModel<String>(tests, "eyeRightChoice"), development);
		this.eyesightRight.setRequired(true);
		add(this.eyesightRight);
	}
	
	public MilestoneTests getTests() {
		return this.tests;
	}
	
	class NextMilestoneChoice extends DropDownChoice<String> {
		private static final long serialVersionUID = 2632359447755639947L;

		public NextMilestoneChoice(String id, IModel<String> model, List<String> list) {
			super(id, model, list);
			add(new AjaxFormComponentUpdatingBehavior("onchange") {
				private static final long serialVersionUID = 1L;

				protected void onUpdate(AjaxRequestTarget target) {
	            	// This updates the model, apparently
	            }
	        });
		}
		
		@Override
		protected boolean localizeDisplayValues() {
			return true;
		}
	}
}