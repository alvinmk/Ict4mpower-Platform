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
package ict4mpower.childHealth.panels.followUp;

import java.util.Date;

import org.apache.wicket.datetime.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.extensions.yui.calendar.DateTimeField;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;

import ict4mpower.childHealth.SavingForm;
import ict4mpower.childHealth.ValidationClassBehavior;
import ict4mpower.childHealth.data.FollowUpData;
import ict4mpower.childHealth.panels.DivisionPanel;

/**
 * Panel for the follow-up task
 * @author Joakim Lindskog
 *
 */
public class FollowUpPanel extends DivisionPanel {
	private static final long serialVersionUID = -1172218871548889654L;
	
	private DateTimeField date;
	private TextArea<String> message;

	/**
	 * Constructor
	 * @param id component id
	 */
	public FollowUpPanel(String id) {
		super(id, "title");
		
		FollowUpForm form = new FollowUpForm("form");
		add(form);
		
		setForm(form, this);
	}
	
	/**
	 * Form for the follow-up panel
	 * @author Joakim Lindskog
	 *
	 */
	private class FollowUpForm extends SavingForm {
		private static final long serialVersionUID = -2039157252043048544L;

		/**
		 * Constructor
		 * @param id component id
		 */
		public FollowUpForm(String id) {
			super(id);
			
			FollowUpData data = FollowUpData.instance();
			
			// Feedback panel
			add(new FeedbackPanel("feedback"), false);
			
			// Date field
			date = new DateTimeField("date", new PropertyModel<Date>(data, "date")) {
				private static final long serialVersionUID = 1L;
				
				@Override
				protected DateTextField newDateTextField(String id,
						PropertyModel<Date> model) {
					return DateTextField.forDatePattern(id, model, "dd/MM/yyyy");
				}
				
				@Override
				protected DatePicker newDatePicker() {
					return super.newDatePicker().setShowOnFieldClick(true);
				}
			};
			date.setRequired(true);
			date.add(new ValidationClassBehavior());
			add(date);
			
			// Text area
			message = new TextArea<String>("message", new PropertyModel<String>(data, "message"));
			message.setRequired(true);
			message.add(new ValidationClassBehavior());
			message.setOutputMarkupId(true);
			add(message);
		}
	}
	
	public void renderHead(IHeaderResponse iHeaderResponse) {
        super.renderHead(iHeaderResponse);
        iHeaderResponse.renderOnLoadJavaScript("document.getElementById('" + message.getMarkupId() + "').focus()");
    }
}
