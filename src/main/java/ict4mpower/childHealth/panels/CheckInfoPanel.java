package ict4mpower.childHealth.panels;

import ict4mpower.childHealth.data.CheckInfoData;

import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

public class CheckInfoPanel extends Panel {
	private static final long serialVersionUID = 827830474865500102L;

	public CheckInfoPanel(String id, IModel<CheckInfoData> model) {
		super(id);
		
		// Add check box, label and text field
		CheckBox check = new CheckBox("check", new PropertyModel<Boolean>(model, "check"));
		final TextField<String> input = new TextField<String>("info", new PropertyModel<String>(model, "info"), String.class);
		input.setOutputMarkupPlaceholderTag(true);
		input.setVisible(model.getObject().isCheck());
		check.add(new AjaxEventBehavior("onclick") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onEvent(AjaxRequestTarget target) {
				if(input.isVisible()) {
					input.setVisible(false);
				}
				else {
					input.setVisible(true);
					//target.appendJavaScript("document.getElementById('" + input.getMarkupId() + "').focus();");
					target.focusComponent(input);
				}
				target.add(input);
			}
		});
		add(check);
		add(new Label("label", model.getObject().getLabel()));
		add(input);
	}
}
