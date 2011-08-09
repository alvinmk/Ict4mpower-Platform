package templatePanels;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;
import template.Template;

public class UserPanel extends Panel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List Apps = Arrays.asList(new String[] { "Current Application", "Other Application", "Another Application" });
	public String selected ="";
	
	public UserPanel(String id, String selectedApplication) {
		super(id);
		selected = selectedApplication;
		add( new Label("username", "username"));
		DropDownChoice choice = new DropDownChoice("applicationSelect", new PropertyModel(this, "selected"), Apps);	
		add( choice);
		
		add( new Label("location", "Kampala, HC4"));
		add( new Button("exitApplication"){
			@Override
			public void onSubmit() {
				setResponsePage(Template.class);
			}
			
		});
	}

	

}
