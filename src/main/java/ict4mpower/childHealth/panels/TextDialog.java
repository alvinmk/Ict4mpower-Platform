package ict4mpower.childHealth.panels;

import ict4mpower.childHealth.Callback;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormSubmitBehavior;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

/**
 * A dialog to receive text input
 * @author Joakim Lindskog
 *
 */
public class TextDialog extends ModalWindow {
	private static final long serialVersionUID = -8957806444673982604L;
	
	private TextDialogPanel panel;

	/**
	 * Constructor
	 * @param id component id
	 */
	public TextDialog(String id) {
		super(id);
		
		this.panel = new TextDialogPanel(getContentId(), this);
		setContent(this.panel);
		
		setResizable(false);
		setWidthUnit("em");
		setHeightUnit("em");
	}

	/**
	 * Shows an error
	 * @param target AjaxRequestTarget
	 * @param key properties key
	 */
	public void error(AjaxRequestTarget target, String key) {
		panel.error(target, key);
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

/**
 * Panel for the text dialog
 * @author Joakim Lindskog
 *
 */
class TextDialogPanel extends Panel {
	private static final long serialVersionUID = -8179931789513768591L;
	
	private Label label;
	private TextField<String> dialogText;
	private Button confirmButton;
	private List<Callback> callbacks = new ArrayList<Callback>();
	private TextDialogPanelForm form;

	/**
	 * Constructor
	 * @param id component id
	 * @param dialog parent window
	 */
	public TextDialogPanel(String id, ModalWindow dialog) {
		super(id);

		dialog.setInitialWidth(25);
		dialog.setInitialHeight(7);
		
		this.form = new TextDialogPanelForm("form");
		add(this.form);
	}
	
	/**
	 * Shows an error message
	 * @param target AjaxRequestTarget
	 * @param key properties key
	 */
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

	/**
	 * Returns the input text
	 * @return the input text
	 */
	public String getText() {
		return dialogText.getConvertedInput();
	}
	
	/**
	 * Adds a callback to be performed when the confirm button is clicked
	 * @param callback callback
	 */
	public void addOnSubmit(Callback callback) {
		callbacks.add(callback);
	}
	
	public void setFocus(AjaxRequestTarget target) {
		target.focusComponent(dialogText);
	}

	class TextDialogPanelForm extends Form<Model<?>> {
		private static final long serialVersionUID = 1L;
		
		@SuppressWarnings("unused")
		private String labelText;

		public TextDialogPanelForm(String id) {
			super(id);
			
			// Message text
			label = new Label("label_text", new PropertyModel<String>(this, "labelText"));
			label.setOutputMarkupId(true);
			add(label);

			// Input field
			dialogText = new TextField<String>("text", new Model<String>());
			dialogText.setOutputMarkupId(true);
			add(dialogText);
			
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