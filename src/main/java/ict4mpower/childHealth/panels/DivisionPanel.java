package ict4mpower.childHealth.panels;

import ict4mpower.childHealth.SavingForm;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormValidatingBehavior;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.StringResourceModel;
import org.odlabs.wiquery.core.commons.WiQueryResourceManager;
import org.odlabs.wiquery.core.effects.EffectBehavior;
import org.odlabs.wiquery.core.effects.EffectSpeed;
import org.odlabs.wiquery.core.effects.fading.FadeOut;
import org.odlabs.wiquery.ui.effects.EffectsHelper;
import org.odlabs.wiquery.ui.effects.HighlightEffect;

/**
 * DivisionPanel is a specific part of a task
 * @author Joakim Lindskog
 *
 */
public class DivisionPanel extends Panel {
	private static final long serialVersionUID = 6902627708947338092L;
	
	protected Button saveButton;
	private Label saveLabel;
	private FeedbackPanel feedback;
	private WebMarkupContainer fieldset;
	private EffectBehavior highlightEffect = new EffectBehavior(new HighlightEffect(HighlightEffect.Mode.show, "'#77ED45'", 1500)) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public void contribute(
				WiQueryResourceManager wiQueryResourceManager) {
			EffectsHelper.highlight(wiQueryResourceManager);
		}
		
		public boolean isTemporary(Component component) {
			return true;
		}
	};
	private EffectBehavior fadeEffect = new EffectBehavior(new FadeOut(EffectSpeed.SLOW)) {
		private static final long serialVersionUID = 1L;
		
		public boolean isTemporary(Component component) {
			return true;
		}
	};

	/**
	 * Constructor
	 * @param id component id
	 * @param titleId the properties key for the title
	 * @param savable whether this panel can be saved or not
	 */
	public DivisionPanel(String id, String titleId, boolean savable) {
		super(id);
		setOutputMarkupId(true);
		
		fieldset = new WebMarkupContainer("fieldset");
		fieldset.setOutputMarkupId(true);
		super.add(fieldset);
		
		add(new Label("title", new StringResourceModel(titleId, this, null)));
		
		feedback = new FeedbackPanel("feedback");
		add(feedback);
		
		saveButton = new Button("saveButton");
		saveButton.setVisible(savable);
		super.add(saveButton);
		
		saveLabel = new Label("saveText", new StringResourceModel("saveText", this, null));
		saveLabel.setOutputMarkupPlaceholderTag(true);
		saveLabel.setVisible(false);
		add(saveLabel);
	}
	
	/**
	 * Constructor
	 * @param id component id
	 * @param titleId properties key for title
	 */
	public DivisionPanel(String id, String titleId) {
		this(id, titleId, true);
	}

	/**
	 * Connects the given form with the saving mechanism
	 * and the given panel with the save effects
	 * @param form form to save
	 * @param panel panel to give effects
	 */
	public void setForm(final SavingForm form, final DivisionPanel panel) {
		saveButton.add(new AjaxFormValidatingBehavior(form, "onclick") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onError(AjaxRequestTarget target) {
				target.add(form);
			}

			@Override
			protected void onSubmit(AjaxRequestTarget target) {
				panel.getFieldset().add(highlightEffect);
				panel.getSaveLabel().setVisible(true);
				panel.getSaveLabel().add(fadeEffect);
				target.add(panel.getFieldset());
				target.add(panel.getSaveLabel());
				target.add(form);
			}
		});
	}
	
	@Override
	public MarkupContainer add(Component... childs) {
		return fieldset.add(childs);
	}
	
	@Override
	public MarkupContainer addOrReplace(Component... childs) {
		return fieldset.addOrReplace(childs);
	}
	
	public WebMarkupContainer getFieldset() {
		return fieldset;
	}
	
	public Label getSaveLabel() {
		return saveLabel;
	}
}
