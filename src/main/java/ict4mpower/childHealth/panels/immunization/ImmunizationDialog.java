package ict4mpower.childHealth.panels.immunization;

import ict4mpower.childHealth.Callback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormSubmitBehavior;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

public class ImmunizationDialog extends ModalWindow {
	private static final long serialVersionUID = -2412496739475497485L;
	
	private ImmunizationDialogPanel panel;

	public ImmunizationDialog(String id) {
		super(id);
		
		this.panel = new ImmunizationDialogPanel(getContentId(), this);
		setContent(this.panel);
		
		setResizable(false);
		setWidthUnit("em");
		setHeightUnit("em");
	}

	public void error(AjaxRequestTarget target, String key) {
		panel.error(target, key);
	}

	public String getCalendarType() {
		return panel.getCalendarType();
	}

	public void showCalTypes() {
		panel.showCalTypes();
	}
	
	public void hideCalTypes() {
		panel.hideCalTypes();
	}

	public void setLabel(String string) {
		panel.setLabel(string);
	}

	public String getText() {
		return panel.getText();
	}
	
	public void addOnSubmit(Callback callback) {
		panel.addOnSubmit(callback);
	}
	
	@Override
	public void show(AjaxRequestTarget target) {
		super.show(target);
		panel.setFocus(target);
	}
}

class ImmunizationDialogPanel extends Panel {
	private static final long serialVersionUID = -8179931789513768591L;
	
	private Label label;
	private TextField<String> dialogText;
	private DropDownChoice<String> calType;
	private Map<String, String> calMap = new HashMap<String, String>();
	private Button confirmButton;
	private List<Callback> callbacks = new ArrayList<Callback>();
	private ImmunizationDialogPanelForm form;

	public ImmunizationDialogPanel(String id, ModalWindow dialog) {
		super(id);
		
		calMap.put("age.weeks", "weeks");
		calMap.put("age.months", "months");
		calMap.put("age.years", "years");

		dialog.setInitialWidth(25);
		dialog.setInitialHeight(7);
		
		this.form = new ImmunizationDialogPanelForm("form");
		add(this.form);
	}
	
	public void error(AjaxRequestTarget target, String key) {
		setLabel(getString(key));
		target.add(label);
		target.add(dialogText);
		target.focusComponent(dialogText);
	}

	public void setLabel(String string) {
		this.form.setLabel(string);
		this.label.modelChanged();
	}

	public String getText() {
		return dialogText.getConvertedInput();
	}
	
	public String getCalendarType() {
		return calMap.get(calType.getConvertedInput());
	}
	
	public void addOnSubmit(Callback callback) {
		callbacks.add(callback);
	}
	
	public void setFocus(AjaxRequestTarget target) {
		target.focusComponent(dialogText);
	}
	
	public void showCalTypes() {
		calType.setVisible(true);
	}
	
	public void hideCalTypes() {
		calType.setVisible(false);
	}

	class ImmunizationDialogPanelForm extends Form<Model<?>> {
		private static final long serialVersionUID = 1L;
		
		@SuppressWarnings("unused")
		private String calDefault = "age.weeks";
		
		private final List<String> CAL_TYPES = Arrays.asList(
				new String[]{
					"age.weeks",
					"age.months",
					"age.years"
				});
		
		@SuppressWarnings("unused")
		private String labelText;

		public ImmunizationDialogPanelForm(String id) {
			super(id);
			
			label = new Label("label_text", new PropertyModel<String>(this, "labelText"));
			label.setOutputMarkupId(true);
			add(label);

			dialogText = new TextField<String>("text", new Model<String>());
			dialogText.setOutputMarkupId(true);
			add(dialogText);
			
			calType = new DropDownChoice<String>("calType",
					new PropertyModel<String>(this, "calDefault"),
					CAL_TYPES, new ChoiceRenderer<String>() {
						private static final long serialVersionUID = 1L;
				
						@Override
						public Object getDisplayValue(String object) {
							return getString(object);
						}
						
						@Override
						public String getIdValue(String object, int index) {
							return object;
						}
			});
			calType.setOutputMarkupPlaceholderTag(true);
			calType.setVisible(false);
			add(calType);
			
			confirmButton = new Button("confirmButton");
			confirmButton.add(new AjaxFormSubmitBehavior(this, "onclick") {
				private static final long serialVersionUID = 1L;

				@Override
				protected void onSubmit(AjaxRequestTarget target) {
					boolean success = false;
					List<Callback> rm = new ArrayList<Callback>();
					for(Callback cb : callbacks) {
						success = cb.call(target);
						if(success) {
							rm.add(cb);
						}
					}
					for(Callback c : rm) {
						callbacks.remove(c);
					}
					dialogText.setModelValue(new String[]{});
				}

				@Override
				protected void onError(AjaxRequestTarget target) {}
			});
			add(confirmButton);
		}

		public void setLabel(String string) {
			this.labelText = string;
		}
	}
}