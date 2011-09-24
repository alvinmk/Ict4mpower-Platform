package ict4mpower.childHealth.panels.growth;

import ict4mpower.childHealth.StringResourceModelChoiceRenderer;
import ict4mpower.childHealth.panels.DivisionPanel;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.StringResourceModel;

public class PMTCTRecordPanel extends DivisionPanel {
	private static final long serialVersionUID = 3074630914459300687L;
	
	private final StringResourceModel defaultChoice = new StringResourceModel("dropdown.choice.null", this, null);
	private final List<StringResourceModel> PMTCT = Arrays.asList(new StringResourceModel[] {
			defaultChoice,
			new StringResourceModel("t", this, null),
			new StringResourceModel("tr", this, null),
			new StringResourceModel("trr", this, null),
			new StringResourceModel("trrd", this, null),
			new StringResourceModel("trrdm", this, null),
			new StringResourceModel("trrdmdb", this, null)});

	public PMTCTRecordPanel(String id) {
		super(id, "title");
		// PMTCT drop down
		add(new DropDownChoice<StringResourceModel>("pmtct", PMTCT, new StringResourceModelChoiceRenderer()) {
			private static final long serialVersionUID = 1L;

			@Override
			protected CharSequence getDefaultChoice(String arg0) {
				return defaultChoice.getObject();
			}
		});
		// Other choices
		add(new Label("hivtest", new StringResourceModel("hivtest_results", this, null)));
		RadioChoice<StringResourceModel> rc1 = new RadioChoice<StringResourceModel>("hivtest_radio", Arrays.asList(new StringResourceModel[]{new StringResourceModel("hivtest_reactive", this, null), new StringResourceModel("hivtest_non-reactive", this, null)}),
				new StringResourceModelChoiceRenderer());
		rc1.setSuffix(" ");
		add(rc1);
		add(new Label("init_treatment", new StringResourceModel("init_treatment", this, null)));
		RadioChoice<StringResourceModel> rc2 = new RadioChoice<StringResourceModel>("init_treatment_radio", Arrays.asList(new StringResourceModel[]{new StringResourceModel("init_treatment_yes", this, null), new StringResourceModel("init_treatment_no", this, null)}),
				new StringResourceModelChoiceRenderer());
		rc2.setSuffix(" ");
		add(rc2);
		// Date of initiation of treatment
		add(new TextField<Object>("init_treatment_date"));
	}
}
