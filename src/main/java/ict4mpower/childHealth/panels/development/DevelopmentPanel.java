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
package ict4mpower.childHealth.panels.development;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;

import layout.Template;
import models.PatientInfo;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
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

import storage.ApplicationSocketTemp;
import storage.DataEndPoint;

import ict4mpower.AppSession;
import ict4mpower.childHealth.SavingForm;
import ict4mpower.childHealth.data.DevelopmentData;
import ict4mpower.childHealth.data.FollowUpData;
import ict4mpower.childHealth.panels.AgeModel;
import ict4mpower.childHealth.panels.DivisionPanel;

/**
 * Panel for showing the development of the child in milestones
 * @author Joakim Lindskog
 *
 */
public class DevelopmentPanel extends DivisionPanel {
	private static final long serialVersionUID = -1172218871548889654L;
	
	private ListView<Milestone> list;
	private NextMilestonePanel nextPanel;
	private MilestoneTests current;
	
	private List<MilestoneTests> tests = new ArrayList<MilestoneTests>();

	/**
	 * Constructor
	 * @param id component id
	 */
	@SuppressWarnings("unchecked")
	public DevelopmentPanel(String id) {
		super(id, "title", false);
		
		// Get milestone tests from db
		Set<Object> set = ApplicationSocketTemp.getApplicationSocketTemp().getData("ChildHealth", "MilestoneTests");
		PatientInfo pi = ((AppSession)getSession()).getPatientInfo();
		List<MilestoneTests> std = null;
		for(Object o : set) {
			// Only get first one - there should only be one
			std = (List<MilestoneTests>) o;
			break;
		}
		for(MilestoneTests t : std) {
			tests.add(new MilestoneTests(pi, t.getCalField(), t.getCalAdd(), t.grossMotor, t.fineMotor,
					t.communication, t.cognitive));
		}
		
		DevelopmentForm form = new DevelopmentForm("form");
		add(form);
	}
	
	/**
	 * Form for development panel
	 * @author Joakim Lindskog
	 *
	 */
	private class DevelopmentForm extends SavingForm {
		private static final long serialVersionUID = 54717230506400089L;

		/**
		 * Constructor
		 * @param id component id
		 */
		public DevelopmentForm(String id) {
			super(id);
			
			DevelopmentData data = DevelopmentData.instance();
			if(data.getMilestones() == null) {
				Date max = null;
				try {
					max = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/1800");
				} catch (ParseException e) {
					e.printStackTrace();
				}
				DevelopmentData dev = null;
				// Get from db
				Set<Serializable> set = DataEndPoint.getDataEndPoint().getEntriesFromPatientId(((AppSession)getSession()).getPatientInfo().getClientId());
				for(Object o : set) {
					if(o instanceof DevelopmentData) {
						dev = (DevelopmentData) o;
						if(dev.getMilestones() != null && dev.getDate().after(max)) {
							data.setMilestones(dev.getMilestones());
							max = dev.getDate();
						}
					}
				}
			}
			if(data.getMilestones() == null) {
				data.setMilestones(new ArrayList<Milestone>());
			}
			
			list = new ListView<Milestone>("milestones", new PropertyModel<List<Milestone>>(data, "milestones")) {
				private static final long serialVersionUID = 1L;

				@Override
				protected void populateItem(ListItem<Milestone> item) {
					item.add(new MilestonePanel("rowPanel", item.getModel(), DevelopmentPanel.this));
				}
			};
			add(list);
			
			if(data.getMilestones() == null) {
				current = tests.get(0).clone();
			}
			else if(data.getMilestones().size() < tests.size()) {
				current = tests.get(data.getMilestones().size()).clone();
			}
			else {
				current = tests.get(data.getMilestones().size()-1).clone();
			}
			
			nextPanel = new NextMilestonePanel("nextMilestonePanel", this, DevelopmentPanel.this, current);
			nextPanel.setOutputMarkupId(true);
			if(data.getMilestones() != null && data.getMilestones().size() >= tests.size()) {
				nextPanel.setVisible(false);
			}
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
			DevelopmentData.instance().setDate(new Date());
		}
	}
}

/**
 * Panel to show a completed milestone
 * @author Joakim Lindskog
 *
 */
class MilestonePanel extends Panel {
	private static final long serialVersionUID = 3885944576231374282L;

	/**
	 * Constructor
	 * @param id component id
	 * @param milestone model of milestone
	 * @param parent parent component
	 */
	public MilestonePanel(String id, IModel<Milestone> milestone, Component parent) {
		super(id);
		
		// Add milestone values
		add(new Label("age", new Model<String>(milestone.getObject().tests.getDueAge(parent))));
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
	
	/**
	 * Sets the class of the label
	 * @param label the label to set the class for
	 * @param value the value to use when determining the class to use
	 */
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

/**
 * Panel for entering a new milestone
 * @author Joakim Lindskog
 *
 */
class NextMilestonePanel extends DivisionPanel {
	private static final long serialVersionUID = 2726451125512509536L;
	
	private static List<String> development;
	
	/**
	 * Convert model value to a number
	 * "Well developed" = 0
	 * "Stays behind" = 1
	 * "Not developed" = 2
	 * @param model the model value
	 * @return a short value representing the model value
	 */
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

	/**
	 * Constructor
	 * @param id component id
	 * @param form form
	 * @param panel parent panel
	 * @param tests milestone tests to use
	 */
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
		
		Label ageLabel = new Label("age", new AgeModel<MilestoneTests>(
				new Model<MilestoneTests>(this.tests), panel));
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
	
	/**
	 * A DropDownChoice which updates its model on selection change
	 * @author Joakim Lindskog
	 *
	 */
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