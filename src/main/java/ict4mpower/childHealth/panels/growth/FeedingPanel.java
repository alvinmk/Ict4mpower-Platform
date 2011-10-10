package ict4mpower.childHealth.panels.growth;

import ict4mpower.childHealth.SavingForm;
import ict4mpower.childHealth.StringResourceModelChoiceRenderer;
import ict4mpower.childHealth.panels.DivisionPanel;
import ict4mpower.childHealth.panels.SingleStringResourceData;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.StringResourceModel;

public class FeedingPanel extends DivisionPanel {
	private static final long serialVersionUID = 4179450386998797319L;
	
	private final StringResourceModel defaultChoice = new StringResourceModel("dropdown.choice.null", this, null);
	private final List<StringResourceModel> FEEDING = Arrays.asList(new StringResourceModel[] {
			defaultChoice,
			new StringResourceModel("breast", this, null),
			new StringResourceModel("replacement", this, null),
			new StringResourceModel("complementary", this, null),
			new StringResourceModel("mixed", this, null),
			new StringResourceModel("other", this, null)});

	public FeedingPanel(String id) {
		super(id, "title");
		
		FeedingForm form = new FeedingForm("form");
		add(form);
		
		setForm(form, this.getClass().getName()+"Frame");
	}
	
	private class FeedingForm extends SavingForm {
		private static final long serialVersionUID = 7663790124524711200L;

		public FeedingForm(String id) {
			super(id, FeedingForm.class.getName());
			
			SingleStringResourceData data = new SingleStringResourceData();
			
			add(new DropDownChoice<StringResourceModel>("feeding", new PropertyModel<StringResourceModel>(data, "model"), FEEDING, new StringResourceModelChoiceRenderer()) {
				private static final long serialVersionUID = 1L;
				
				@Override
				protected CharSequence getDefaultChoice(String arg0) {
					return defaultChoice.getObject();
				}
			});
		}
	}
}
