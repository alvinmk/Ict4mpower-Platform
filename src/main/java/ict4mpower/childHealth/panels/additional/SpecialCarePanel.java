package ict4mpower.childHealth.panels.additional;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.wicket.extensions.markup.html.form.palette.Palette;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.model.Model;

import ict4mpower.childHealth.SavingForm;
import ict4mpower.childHealth.panels.DivisionPanel;

public class SpecialCarePanel extends DivisionPanel {
	private static final long serialVersionUID = 2899319801604156038L;

	public SpecialCarePanel(String id) {
		super(id, "title");
		
		add(new SpecialCareForm("form"));
	}
	
	class SpecialCareForm extends SavingForm {
		private static final long serialVersionUID = -3198391667064524025L;

		public SpecialCareForm(String id) {
			super(id);
			
			setForm(this, SpecialCarePanel.this);
			
			// TODO Temporary
			ArrayList<String> list = new ArrayList<String>(Arrays.asList(new String[] {
					"birth_weight_low",
					"birth_defect",
					"other_handicaps",
					"fifth_child",
					"birth_close"
			}));
			
			// Reasons for special care
			Palette<String> palette = new Palette<String>("reasons",
					new Model<ArrayList<String>>(new ArrayList<String>()),
					new Model<ArrayList<String>>(list),
					new ChoiceRenderer<String>() {
						private static final long serialVersionUID = 1L;
						
						@Override
						public Object getDisplayValue(String object) {
							return object;
						}
			}, 13, false);
			add(palette);
		}
	}
}
