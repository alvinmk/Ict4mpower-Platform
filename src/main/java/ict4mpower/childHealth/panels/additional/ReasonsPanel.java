package ict4mpower.childHealth.panels.additional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.wicket.extensions.markup.html.form.palette.Palette;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import ict4mpower.childHealth.SavingForm;
import ict4mpower.childHealth.data.AdditionalData;
import ict4mpower.childHealth.panels.DivisionPanel;

public class ReasonsPanel extends DivisionPanel {
	private static final long serialVersionUID = 2899319801604156038L;

	public ReasonsPanel(String id) {
		super(id, "title");
		
		add(new SpecialCareForm("form"));
	}
	
	class SpecialCareForm extends SavingForm {
		private static final long serialVersionUID = -3198391667064524025L;

		public SpecialCareForm(String id) {
			super(id);
			
			setForm(this, ReasonsPanel.this);
			
			// TODO Temporary
			ArrayList<String> list = new ArrayList<String>(Arrays.asList(new String[] {
					"birth_weight_low",
					"birth_defect",
					"other_handicaps",
					"fifth_child",
					"birth_close",
					"severe_jaundice",
					"undernourished_siblings",
					"mother_dead",
					"father_dead",
					"children_dead",
					"multiple_birth",
					"birth_asphyxia"
			}));
			
			AdditionalData data = AdditionalData.instance();
			
			// Reasons for special care
			Palette<String> palette = new Palette<String>("reasons",
					new PropertyModel<List<String>>(data, "reasons"),
					new Model<ArrayList<String>>(list),
					new ChoiceRenderer<String>() {
						private static final long serialVersionUID = 1L;
						
						@Override
						public Object getDisplayValue(String object) {
							return getString(object);
						}
						
						@Override
						public String getIdValue(String object, int index) {
							return object;
						}
			}, 13, false);
			add(palette);
		}
	}
}
