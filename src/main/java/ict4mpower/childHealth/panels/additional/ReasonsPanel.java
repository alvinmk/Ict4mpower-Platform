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
package ict4mpower.childHealth.panels.additional;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.wicket.extensions.markup.html.form.palette.Palette;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import storage.MedicalRecordSocket;

import ict4mpower.AppSession;
import ict4mpower.childHealth.SavingForm;
import ict4mpower.childHealth.data.AdditionalData;
import ict4mpower.childHealth.panels.DivisionPanel;

/**
 * A panel for selecting reasons for special care
 * @author Joakim Lindskog
 *
 */
public class ReasonsPanel extends DivisionPanel {
	private static final long serialVersionUID = 2899319801604156038L;

	/**
	 * Constructor
	 * @param id component id
	 */
	public ReasonsPanel(String id) {
		super(id, "title");
		
		add(new SpecialCareForm("form"));
	}
	
	/**
	 * Form for reasons panel
	 * @author Joakim Lindskog
	 *
	 */
	class SpecialCareForm extends SavingForm {
		private static final long serialVersionUID = -3198391667064524025L;

		/**
		 * Constructor
		 * @param id component id
		 */
		public SpecialCareForm(String id) {
			super(id);
			
			setForm(this, ReasonsPanel.this);
			
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
			if(data.getReasons().isEmpty()) {
				Date max = null;
				try {
					max = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/1800");
				} catch (ParseException e) {
					e.printStackTrace();
				}
				AdditionalData ad = null;
				// Get from db
				MedicalRecordSocket socket = new MedicalRecordSocket();
				Set<Object> set = socket.getEntriesForPatientId(((AppSession)getSession()).getPatientInfo().getClientId(), AdditionalData.class.getSimpleName(), "ChildHealth");
				for(Object o : set) {
					ad = (AdditionalData) o;
					if(!ad.getReasons().isEmpty() && ad.getDate().after(max)) {
						data.setReasons(ad.getReasons());
							max = ad.getDate();
					}
				}
			}
			
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
		
		@Override
		protected void onSubmit() {
			super.onSubmit();
			
			AdditionalData data = AdditionalData.instance();
			data.setDate(new Date());
		}
	}
}