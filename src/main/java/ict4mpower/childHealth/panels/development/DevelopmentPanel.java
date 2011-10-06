package ict4mpower.childHealth.panels.development;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.StringResourceModel;

import ict4mpower.childHealth.StringResourceModelChoiceRenderer;
import ict4mpower.childHealth.panels.DivisionPanel;

public class DevelopmentPanel extends DivisionPanel {
	private static final long serialVersionUID = -1172218871548889654L;
	
	private DateFormat df = new SimpleDateFormat("d/M/y");
	
	private List<MilestoneTests> tests = Arrays.asList(
			new MilestoneTests[]{
					new MilestoneTests(4, "Symmetric spontaneous motor skills",
							null, "Follows objects with eyes", null),
					new MilestoneTests(6, "Holds up the head lying on stomach<br/>Opens hands",
							null, "Smiles at parents<br/>Responds to sounds", null),
					new MilestoneTests(24, "Turns around<br/>Pulls self up towards a sitting position",
							"Transfers objects from one hand to the other",
							"Looks for the dropped object<br/>Makes double syllable sounds such as 'mumum' and 'dada'",
							null)
			});

	public DevelopmentPanel(String id) {
		super(id, "title", false);
		
		//TODO Temporary
		List<Milestone> milestones = null;
		try {
			milestones = Arrays.asList(new Milestone[]{
					new Milestone(tests.get(0), (short)0, (short)0, (short)0, (short)0, new Short[]{0,0}, new Short[]{0,0}),
					new Milestone(tests.get(1), (short)1, (short)0, (short)1, (short)0, new Short[]{0,0}, new Short[]{0,0}),
					new Milestone(tests.get(2), (short)2, (short)2, (short)0, (short)0, new Short[]{2,2}, new Short[]{2,1})
			});
		} catch(Exception e) {
			//
		}
			
		add(new ListView<Milestone>("milestones", milestones) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<Milestone> item) {
				Milestone indi = item.getModelObject();
				item.add(new MilestonePanel("rowPanel", indi));
			}
		});
		
		NextMilestonePanel vPanel = new NextMilestonePanel("nextMilestonePanel");
		add(vPanel);
	}
}

class Milestone implements Serializable {
	private static final long serialVersionUID = -9155283621010002742L;
	
	public MilestoneTests tests;
	public short grossMotor;
	public short fineMotor;
	public short communication;
	public short cognitive;
	public Short[] hearing;
	public Short[] eyesight;
	
	public Milestone(MilestoneTests tests, short grossMotor, short fineMotor,
			short communication, short cognitive, Short[] hearing, Short[] eyesight) {
		this.tests = tests;
		this.grossMotor = grossMotor;
		this.fineMotor = fineMotor;
		this.communication = communication;
		this.cognitive = cognitive;
		this.hearing = hearing;
		this.eyesight = eyesight;
	}
}

class MilestoneTests implements Serializable {
	private static final long serialVersionUID = 4401388786490151969L;
	
	public int age;
	public String grossMotor;
	public String fineMotor;
	public String communication;
	public String cognitive;
	
	public MilestoneTests(int age, String grossMotor, String fineMotor,
			String communication, String cognitive) {
		this.age = age;
		this.grossMotor = grossMotor;
		this.fineMotor = fineMotor;
		this.communication = communication;
		this.cognitive = cognitive;
	}
	
	public String getAge() {
		return this.age + " weeks";
	}
}

class MilestonePanel extends Panel {
	private static final long serialVersionUID = 3885944576231374282L;

	public MilestonePanel(String id, Milestone milestone) {
		super(id);
		
		// Add milestone values
		add(new Label("age", new Model<String>(milestone.tests.getAge())));
		Label grossLabel = new Label("gross_motor", new Model<String>(milestone.tests.grossMotor));
		setClass(grossLabel, milestone.grossMotor);
		grossLabel.setEscapeModelStrings(false);
		add(grossLabel);
		Label fineLabel = new Label("fine_motor", new Model<String>(milestone.tests.fineMotor));
		setClass(fineLabel, milestone.fineMotor);
		fineLabel.setEscapeModelStrings(false);
		add(fineLabel);
		Label commLabel = new Label("communication", new Model<String>(milestone.tests.communication));
		setClass(commLabel, milestone.communication);
		commLabel.setEscapeModelStrings(false);
		add(commLabel);
		Label cogLabel = new Label("cognitive", new Model<String>(milestone.tests.cognitive));
		setClass(cogLabel, milestone.cognitive);
		cogLabel.setEscapeModelStrings(false);
		add(cogLabel);
		Label hearLeftLabel = new Label("hearing_left", new StringResourceModel("left", this, null));
		setClass(hearLeftLabel, milestone.hearing[0]);
		add(hearLeftLabel);
		Label hearRightLabel = new Label("hearing_right", new StringResourceModel("right", this, null));
		setClass(hearRightLabel, milestone.hearing[1]);
		add(hearRightLabel);
		Label eyeLeftLabel = new Label("eyesight_left", new StringResourceModel("left", this, null));
		setClass(eyeLeftLabel, milestone.eyesight[0]);
		add(eyeLeftLabel);
		Label eyeRightLabel = new Label("eyesight_right", new StringResourceModel("right", this, null));
		setClass(eyeRightLabel, milestone.eyesight[1]);
		add(eyeRightLabel);
	}
	
	private void setClass(Label label, short value) {
		if(value == 2) {
			label.add(AttributeModifier.replace("class", "not_developed"));
		}
		else if(value == 1) {
			label.add(AttributeModifier.replace("class", "stays_behind"));
		}
		else {
			label.add(AttributeModifier.replace("class", "well_developed"));
		}
	}
}

class NextMilestonePanel extends DivisionPanel {
	private static final long serialVersionUID = 2726451125512509536L;
	
	private List<StringResourceModel> development = Arrays.asList(
			new StringResourceModel("default", this, null),
			new StringResourceModel("well_developed", this, null),
			new StringResourceModel("stays_behind", this, null),
			new StringResourceModel("not_developed", this, null)
		);
	
	private StringResourceModel defaultChoice = new StringResourceModel("default", this, null);

	public NextMilestonePanel(String id) {
		super(id, "nextMilestone");
		
		//TODO Temporary
		MilestoneTests tests = new MilestoneTests(40, "Rises, walks with support",
							"Picks up objects with pincergrasp",
							"Hits two objects against each other",
							null);
		
		StringResourceModelChoiceRenderer renderer = new StringResourceModelChoiceRenderer();
		
		add(new Label("age", new Model<String>(tests.getAge())));
		Label grossLabel = new Label("gross_motor", new Model<String>(tests.grossMotor));
		grossLabel.setEscapeModelStrings(false);
		add(grossLabel);
		add(new NextMilestoneChoice("select_gross", development, renderer));
		
		Label fineLabel = new Label("fine_motor", new Model<String>(tests.fineMotor));
		fineLabel.setEscapeModelStrings(false);
		add(fineLabel);
		add(new NextMilestoneChoice("select_fine", development, renderer));
		
		Label commLabel = new Label("communication", new Model<String>(tests.communication));
		commLabel.setEscapeModelStrings(false);
		add(commLabel);
		add(new NextMilestoneChoice("select_comm", development, renderer));
		
		Label cogLabel = new Label("cognitive", new Model<String>(tests.cognitive));
		cogLabel.setEscapeModelStrings(false);
		add(cogLabel);
		add(new NextMilestoneChoice("select_cog", development, renderer));
		
		Label hearLeftLabel = new Label("hearing_left", new StringResourceModel("left", this, null));
		add(hearLeftLabel);
		add(new NextMilestoneChoice("select_hear_left", development, renderer));
		
		Label hearRightLabel = new Label("hearing_right", new StringResourceModel("right", this, null));
		add(hearRightLabel);
		add(new NextMilestoneChoice("select_hear_right", development, renderer));
		
		Label eyeLeftLabel = new Label("eyesight_left", new StringResourceModel("left", this, null));
		add(eyeLeftLabel);
		add(new NextMilestoneChoice("select_eye_left", development, renderer));
		
		Label eyeRightLabel = new Label("eyesight_right", new StringResourceModel("right", this, null));
		add(eyeRightLabel);
		add(new NextMilestoneChoice("select_eye_right", development, renderer));
	}
	
	class NextMilestoneChoice extends DropDownChoice<StringResourceModel> {
		private static final long serialVersionUID = 2632359447755639947L;

		public NextMilestoneChoice(String id, List<StringResourceModel> list, StringResourceModelChoiceRenderer renderer) {
			super(id, list, renderer);
		}
		
		@Override
		protected CharSequence getDefaultChoice(String selectedValue) {
			return defaultChoice.getObject();
		}
	}
}