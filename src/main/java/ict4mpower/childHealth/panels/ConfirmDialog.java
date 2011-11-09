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
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

/**
 * A dialog to confirm an action
 * @author Joakim Lindskog
 *
 */
public class ConfirmDialog extends ModalWindow {
	private static final long serialVersionUID = -8957806444673982604L;
	
	private ConfirmDialogPanel panel;

	/**
	 * Constructor
	 * @param id component id
	 */
	public ConfirmDialog(String id) {
		super(id);
		
		this.panel = new ConfirmDialogPanel(getContentId(), this);
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
	 * Sets the label text
	 * @param string label text
	 */
	public void setLabel(String string) {
		panel.setLabel(string);
	}
	
	/**
	 * Adds a callback on a click on the confirm button
	 * @param callback a callback
	 */
	public void addOnSubmit(Callback callback) {
		panel.addOnSubmit(callback);
	}
}

/**
 * A panel for the confirm dialog
 * @author Joakim Lindskog
 *
 */
class ConfirmDialogPanel extends Panel {
	private static final long serialVersionUID = -8179931789513768591L;
	
	private Label label;
	private Button confirmButton;
	private Button cancelButton;
	private List<Callback> callbacks = new ArrayList<Callback>();
	private ConfirmDialogPanelForm form;

	/**
	 * Constructor
	 * @param id component id
	 * @param dialog the parent window
	 */
	public ConfirmDialogPanel(String id, ModalWindow dialog) {
		super(id);

		dialog.setInitialWidth(25);
		dialog.setInitialHeight(7);
		
		this.form = new ConfirmDialogPanelForm("form", dialog);
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
	 * Adds a callback on a click on the confirm button
	 * @param callback a callback
	 */
	public void addOnSubmit(Callback callback) {
		callbacks.add(callback);
	}

	/**
	 * A form for the confirm dialog panel
	 * @author Joakim Lindskog
	 *
	 */
	class ConfirmDialogPanelForm extends Form<Model<?>> {
		private static final long serialVersionUID = 1L;
		
		@SuppressWarnings("unused")
		private String labelText;

		/**
		 * Constructor
		 * @param id component id
		 * @param dialog parent window
		 */
		public ConfirmDialogPanelForm(String id, final ModalWindow dialog) {
			super(id);
			
			label = new Label("label_text", new PropertyModel<String>(this, "labelText"));
			label.setOutputMarkupId(true);
			add(label);
			
			confirmButton = new Button("confirmButton");
			confirmButton.add(new AjaxFormSubmitBehavior(this, "onclick") {
				private static final long serialVersionUID = 1L;

				@Override
				protected void onSubmit(AjaxRequestTarget target) {
					boolean success = false;
					List<Callback> rm = new ArrayList<Callback>();
					List<Callback> cbs = new ArrayList<Callback>(callbacks.size());
					for(Callback cb : callbacks) {
						cbs.add(cb);
					}
					for(Callback cb : cbs) {
						// Perform the callback
						success = cb.call(target);
						if(success) {
							// Remove if successful
							rm.add(cb);
						}
					}
					for(Callback c : rm) {
						callbacks.remove(c);
					}
					dialog.close(target);
				}

				@Override
				protected void onError(AjaxRequestTarget target) {}
			});
			add(confirmButton);
			
			cancelButton = new Button("cancelButton");
			cancelButton.add(new AjaxFormSubmitBehavior(this, "onclick") {
				private static final long serialVersionUID = 1L;

				@Override
				protected void onSubmit(AjaxRequestTarget target) {
					dialog.close(target);
				}

				@Override
				protected void onError(AjaxRequestTarget target) {}
			});
			cancelButton.setOutputMarkupPlaceholderTag(true);
			add(cancelButton);
		}

		public void setLabel(String string) {
			this.labelText = string;
		}
	}
}