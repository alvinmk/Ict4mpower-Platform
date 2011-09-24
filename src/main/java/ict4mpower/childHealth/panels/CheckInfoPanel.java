package ict4mpower.childHealth.panels;

import ict4mpower.childHealth.interfaces.ISavable;

import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;

public class CheckInfoPanel extends Panel implements ISavable {
	private static final long serialVersionUID = 827830474865500102L;

	public CheckInfoPanel(String id, String label) {
		super(id);
		
		// Add check box, label and text field
		// TODO Get saved information
		CheckBox check = new CheckBox("check");
		final TextField<String> input = new TextField<String>("info", String.class);
		input.setOutputMarkupPlaceholderTag(true);
		input.setVisible(false);
		check.add(new AjaxEventBehavior("onclick") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onEvent(AjaxRequestTarget target) {
				if(input.isVisible()) {
					input.setVisible(false);
				}
				else {
					input.setVisible(true);
					target.appendJavaScript("document.getElementById('" + input.getMarkupId() + "').focus();");
				}
				target.add(input);
			}
		});
		add(check);
		add(new Label("label", label));
		add(input);
	}
	
	public boolean save() {
		// TODO Implement save
		System.out.println("Saving check info...");
		return true;
	}
}
