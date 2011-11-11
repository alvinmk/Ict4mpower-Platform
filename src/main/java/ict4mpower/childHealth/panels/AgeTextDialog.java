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
package ict4mpower.childHealth.panels;

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

/**
 * A dialog for receiving new age and text values
 * @author Joakim Lindskog
 *
 */
public class AgeTextDialog extends ModalWindow {
	private static final long serialVersionUID = -2412496739475497485L;
	
	private AgeTextDialogPanel panel;

	/**
	 * Constructor
	 * @param id component id
	 */
	public AgeTextDialog(String id) {
		super(id);
		
		this.panel = new AgeTextDialogPanel(getContentId(), this);
		setContent(this.panel);
		
		setResizable(false);
		setWidthUnit("em");
		setHeightUnit("em");
	}

	/**
	 * Show error message
	 * @param target AjaxRequestTarget
	 * @param key properties key
	 */
	public void error(AjaxRequestTarget target, String key) {
		panel.error(target, key);
	}

	/**
	 * Gets the selected calendar field String
	 * @return the selected calendar field String
	 */
	public String getCalendarType() {
		return panel.getCalendarType();
	}

	/**
	 * Shows the calendar field dropdown
	 */
	public void showCalTypes() {
		panel.showCalTypes();
	}
	
	/**
	 * Hides the calendar field dropdown
	 */
	public void hideCalTypes() {
		panel.hideCalTypes();
	}

	/**
	 * Sets the label text
	 * @param string label text
	 */
	public void setLabel(String string) {
		panel.setLabel(string);
	}

	/**
	 * @return the entered text
	 */
	public String getText() {
		return panel.getText();
	}
	
	/**
	 * Adds a callback on a click on the confirm button
	 * @param callback a callback
	 */
	public void addOnSubmit(Callback callback) {
		panel.addOnSubmit(callback);
	}
	
	@Override
	public void show(AjaxRequestTarget target) {
		super.show(target);
		panel.setFocus(target);
	}
}

/**
 * Panel for the age text dialog
 * @author Joakim Lindskog
 *
 */
class AgeTextDialogPanel extends Panel {
	private static final long serialVersionUID = -8179931789513768591L;
	
	private Label label;
	private TextField<String> dialogText;
	private DropDownChoice<String> calType;
	private Map<String, String> calMap = new HashMap<String, String>();
	private Button confirmButton;
	private List<Callback> callbacks = new ArrayList<Callback>();
	private AgeTextDialogPanelForm form;

	/**
	 * Constructor
	 * @param id component id
	 * @param dialog parent window
	 */
	public AgeTextDialogPanel(String id, ModalWindow dialog) {
		super(id);
		
		calMap.put("age.weeks", "weeks");
		calMap.put("age.months", "months");
		calMap.put("age.years", "years");

		dialog.setInitialWidth(25);
		dialog.setInitialHeight(7);
		
		this.form = new AgeTextDialogPanelForm("form");
		add(this.form);
	}
	
	/**
	 * Show error message
	 * @param target AjaxRequestTarget
	 * @param key properties key
	 */
	public void error(AjaxRequestTarget target, String key) {
		setLabel(getString(key));
		target.add(label);
		target.add(dialogText);
		target.focusComponent(dialogText);
	}

	/**
	 * Sets the label text
	 * @param string label text
	 */
	public void setLabel(String string) {
		this.form.setLabel(string);
		this.label.modelChanged();
	}

	/**
	 * @return the entered text
	 */
	public String getText() {
		return dialogText.getConvertedInput();
	}
	
	/**
	 * Gets the selected calendar field String
	 * @return the selected calendar field String
	 */
	public String getCalendarType() {
		return calMap.get(calType.getConvertedInput());
	}
	
	/**
	 * Adds a callback on a click on the confirm button
	 * @param callback a callback
	 */
	public void addOnSubmit(Callback callback) {
		callbacks.add(callback);
	}
	
	public void setFocus(AjaxRequestTarget target) {
		target.focusComponent(dialogText);
	}
	
	/**
	 * Shows the calendar field dropdown
	 */
	public void showCalTypes() {
		calType.setVisible(true);
	}
	
	/**
	 * Hides the calendar field dropdown
	 */
	public void hideCalTypes() {
		calType.setVisible(false);
	}

	/**
	 * Form for the age text dialog panel
	 * @author Joakim Lindskog
	 *
	 */
	class AgeTextDialogPanelForm extends Form<Model<?>> {
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

		/**
		 * Constructor
		 * @param id component id
		 */
		public AgeTextDialogPanelForm(String id) {
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