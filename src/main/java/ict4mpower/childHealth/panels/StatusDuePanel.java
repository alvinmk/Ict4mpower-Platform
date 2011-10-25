package ict4mpower.childHealth.panels;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.StringResourceModel;

import layout.Template;

public class StatusDuePanel extends StatusPanel {
	private static final long serialVersionUID = 3478275227783326633L;

	public StatusDuePanel(String id, final Date dueDate) {
		super(id);
		
		DateModel model = new DateModel(dueDate);
		
		add(new Label("label", new StringResourceModel("due", new Model<DateModel>(model))));
		
		PageParameters pp = new PageParameters();
		pp.set("taskname", "FollowUpTask");
		pp.set("goalname", "ChildHealth");
		BookmarkablePageLink<Template> link = new BookmarkablePageLink<Template>("link", Template.class, pp);
		add(link);
	}
}

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
