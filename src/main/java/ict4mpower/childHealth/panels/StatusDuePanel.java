package ict4mpower.childHealth.panels;

import ict4mpower.childHealth.data.FollowUpData;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.StringResourceModel;

import layout.Template;

/**
 * Panel to show that a vaccination or medication is due a certain time
 * @author Joakim Lindskog
 *
 */
public class StatusDuePanel extends StatusPanel {
	private static final long serialVersionUID = 3478275227783326633L;

	/**
	 * Constructor
	 * @param id component id
	 * @param dueDate due date
	 */
	public StatusDuePanel(String id, final Date dueDate) {
		super(id);
		
		DateModel model = new DateModel(dueDate);
		
		add(new Label("label", new StringResourceModel("due", new Model<DateModel>(model))));
		
		final PageParameters pp = new PageParameters();
		pp.set("taskname", "FollowUpTask");
		pp.set("goalname", "ChildHealth");
		Link<Template> link = new Link<Template>("link") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				FollowUpData data = FollowUpData.instance();
				data.setDate(dueDate);
				setResponsePage(Template.class, pp);
			}
		};
		// Add link text
		add(link);
	}
}

/**
 * Simple model for the date
 * @author Joakim Lindskog
 *
 */
class DateModel implements Serializable {
	private static final long serialVersionUID = -5696644918692417636L;
	
	private Date date;
	private DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	
	public DateModel(Date date) {
		this.date = date;
	}
	
	public String getDate() {
		return df.format(date);
	}
}
