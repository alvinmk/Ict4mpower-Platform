package ict4mpower.childHealth.panels;

import ict4mpower.childHealth.interfaces.ISavable;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.StringResourceModel;

/**
 * DivisionPanel is a specific part of a task
 * @author Joakim Lindskog
 *
 */
public class DivisionPanel extends Panel implements ISavable {
	private static final long serialVersionUID = 6902627708947338092L;
	
	private StringResourceModel title = null;

	public DivisionPanel(String id, String titleId, boolean savable) {
		super(id);
		this.title = new StringResourceModel(titleId, this, null);
		add(new Label("title", this.title));
		AjaxLink<Object> saveButton = new AjaxLink<Object>("saveButton") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				save();
			}
		};
		saveButton.setVisible(savable);
		add(saveButton);
	}
	
	public DivisionPanel(String id, String titleId) {
		this(id, titleId, true);
	}
	
	public boolean save() {
		System.out.println("saving...");
		return false;
	}
}
