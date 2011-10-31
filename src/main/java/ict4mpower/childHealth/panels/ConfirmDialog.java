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

public class ConfirmDialog extends ModalWindow {
	private static final long serialVersionUID = -8957806444673982604L;
	
	private ConfirmDialogPanel panel;

	public ConfirmDialog(String id) {
		super(id);
		
		this.panel = new ConfirmDialogPanel(getContentId(), this);
		setContent(this.panel);
		
		setResizable(false);
		setWidthUnit("em");
		setHeightUnit("em");
	}

	public void error(AjaxRequestTarget target, String key) {
		panel.error(target, key);
	}

	public void setLabel(String string) {
		panel.setLabel(string);
	}
	
	public void addOnSubmit(Callback callback) {
		panel.addOnSubmit(callback);
	}
}

class ConfirmDialogPanel extends Panel {
	private static final long serialVersionUID = -8179931789513768591L;
	
	private Label label;
	private Button confirmButton;
	private Button cancelButton;
	private List<Callback> callbacks = new ArrayList<Callback>();
	private ConfirmDialogPanelForm form;

	public ConfirmDialogPanel(String id, ModalWindow dialog) {
		super(id);

		dialog.setInitialWidth(25);
		dialog.setInitialHeight(7);
		
		this.form = new ConfirmDialogPanelForm("form", dialog);
		add(this.form);
	}
	
	public void error(AjaxRequestTarget target, String key) {
		setLabel(getString(key));
		target.add(label);
	}

	public void setLabel(String string) {
		this.form.setLabel(string);
		this.label.modelChanged();
	}
	
	public void addOnSubmit(Callback callback) {
		callbacks.add(callback);
	}

	class ConfirmDialogPanelForm extends Form<Model<?>> {
		private static final long serialVersionUID = 1L;
		
		@SuppressWarnings("unused")
		private String labelText;

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
					for(Callback cb : callbacks) {
						success = cb.call(target);
						if(success) {
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
			add(cancelButton);
		}

		public void setLabel(String string) {
			this.labelText = string;
		}
	}
}