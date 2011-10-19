package ict4mpower.childHealth.panels;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormSubmitBehavior;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.odlabs.wiquery.core.events.Event;
import org.odlabs.wiquery.core.events.MouseEvent;
import org.odlabs.wiquery.core.events.WiQueryEventBehavior;
import org.odlabs.wiquery.core.javascript.JsScope;

/**
 * DivisionPanel is a specific part of a task
 * @author Joakim Lindskog
 *
 */
public class DivisionPanel extends Panel {
	private static final long serialVersionUID = 6902627708947338092L;
	
	private StringResourceModel title = null;
	private Button saveButton;
	private Label saveLabel;
	private String frame;

	public DivisionPanel(String id, String titleId, boolean savable) {
		super(id);
		setOutputMarkupId(true);
		
		this.title = new StringResourceModel(titleId, this, null);
		add(new Label("title", this.title));
		
		saveButton = new Button("saveButton");
		saveButton.setVisible(savable);
		add(saveButton);
		
		saveLabel = new Label("saveText", new StringResourceModel("saveText", this, null));
		add(saveLabel);
	}
	
	public DivisionPanel(String id, String titleId) {
		this(id, titleId, true);
	}
	
	@Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);
		if(frame != null) {
			// Import highlight effect
			response.renderJavaScriptReference(new PackageResourceReference(DivisionPanel.class, "jquery.effects.core.js"));
			response.renderJavaScriptReference(new PackageResourceReference(DivisionPanel.class, "jquery.effects.highlight.js"));
			// Set position of save feedback text
			response.renderOnDomReadyJavaScript("var fs = $('#"+frame+" fieldset');"
	        		+"$('#"+frame+"SaveText').offset({left: fs.position().left+"
	        		+"fs.width()/2, top: fs.position().top+fs.height()/2});");
		}
	}
	
	public void setForm(final Form<?> form, Panel panel) {
		// Get only class name
		frame = panel.getClass().getName().substring(panel.getClass().getName().lastIndexOf('.')+1)+"Frame";
		panel.add(AttributeAppender.replace("id", frame));
		saveLabel.add(AttributeAppender.replace("id", frame+"SaveText"));
		
		saveButton.add(new AjaxFormSubmitBehavior(form, "onclick") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onError(AjaxRequestTarget target) {}

			@Override
			protected void onSubmit(AjaxRequestTarget target) {}
		});
		saveButton.add(new WiQueryEventBehavior(new Event(MouseEvent.CLICK) {
			private static final long serialVersionUID = 1L;

			@Override
            public JsScope callback() {
                return JsScope.quickScope(
                		"var fs = $('#"+frame+" fieldset');"
                		+"var st = $('#"+frame+"SaveText');"
                		+"st.stop(true, true); fs.stop(true, true);"
                		+"fs.effect('highlight', {color: '#77ED45'}, 1500);"
                		+"st.show();"
                		+"st.fadeOut(1500);");
            }
		}));
	}
}
