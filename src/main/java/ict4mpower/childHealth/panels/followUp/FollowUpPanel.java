package ict4mpower.childHealth.panels.followUp;

import java.util.Date;

import org.apache.wicket.datetime.PatternDateConverter;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.util.convert.IConverter;
import org.odlabs.wiquery.ui.datepicker.DatePicker;

import ict4mpower.childHealth.SavingForm;
import ict4mpower.childHealth.ValidationClassBehavior;
import ict4mpower.childHealth.data.FollowUpData;
import ict4mpower.childHealth.panels.DivisionPanel;

public class FollowUpPanel extends DivisionPanel {
	private static final long serialVersionUID = -1172218871548889654L;
	
	private DatePicker<Date> date;
	private TextArea<String> message;

	public FollowUpPanel(String id) {
		super(id, "title");
		
		FollowUpForm form = new FollowUpForm("form");
		add(form);
		
		setForm(form, this);
	}
	
	private class FollowUpForm extends SavingForm {
		private static final long serialVersionUID = -2039157252043048544L;

		public FollowUpForm(String id) {
			super(id);
			
			FollowUpData data = FollowUpData.instance();
			
			// Feedback panel
			add(new FeedbackPanel("feedback"), false);
			
			// Date field
			date = new DatePicker<Date>("date", new PropertyModel<Date>(data, "date")) {
				private static final long serialVersionUID = 1L;

				@SuppressWarnings("unchecked")
				@Override
				public <C> IConverter<C> getConverter(Class<C> type) {
					return (IConverter<C>) new PatternDateConverter("dd/mm/yy", false);
				}
			};
			date.setDateFormat("dd/mm/yy");
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
