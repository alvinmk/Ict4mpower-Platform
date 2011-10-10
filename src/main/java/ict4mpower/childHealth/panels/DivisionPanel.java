package ict4mpower.childHealth.panels;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormSubmitBehavior;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.util.value.ValueMap;
import org.odlabs.wiquery.core.events.Event;
import org.odlabs.wiquery.core.events.MouseEvent;
import org.odlabs.wiquery.core.events.WiQueryEventBehavior;
import org.odlabs.wiquery.core.javascript.JsScope;
import org.odlabs.wiquery.ui.dialog.Dialog;

/**
 * DivisionPanel is a specific part of a task
 * @author Joakim Lindskog
 *
 */
public class DivisionPanel extends Panel {
	private static final long serialVersionUID = 6902627708947338092L;
	
	private StringResourceModel title = null;
	private Button saveButton;
	private Dialog dialog;

	public DivisionPanel(String id, String titleId, boolean savable) {
		super(id);
		setOutputMarkupId(true);
		
		this.title = new StringResourceModel(titleId, this, null);
		add(new Label("title", this.title));
		
		saveButton = new Button("saveButton");
		saveButton.setVisible(savable);
		add(saveButton);
		
		dialog = new Dialog("dialog");
		dialog.setCloseText("OK");
		add(dialog);
	}
	
	public DivisionPanel(String id, String titleId) {
		this(id, titleId, true);
	}
	
	@Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);
		response.renderJavaScriptReference(new PackageResourceReference(DivisionPanel.class, "jquery.effects.core.js"));
		response.renderJavaScriptReference(new PackageResourceReference(DivisionPanel.class, "jquery.effects.highlight.js"));
	}
	
	public void setForm(final Form<?> form, final String frame) {
		saveButton.add(new AjaxFormSubmitBehavior(form, "onclick") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onError(AjaxRequestTarget target) {}

			@Override
			protected void onSubmit(AjaxRequestTarget target) {
				((ValueMap)form.getModelObject()).add("datePicker", form.get(0).getDefaultModelObject().toString());
			}
		});
		saveButton.add(new WiQueryEventBehavior(new Event(MouseEvent.CLICK) {
			private static final long serialVersionUID = 1L;

			@Override
            public JsScope callback() {
                return JsScope.quickScope(dialog.open().render()
                		+"$('"+frame+" fieldset').effect('highlight', {mode: 'ease-in'}, 3000);");
            }
		}));
	}
}
