package ict4mpower.childHealth.panels.medications;

import ict4mpower.childHealth.panels.StatusDuePanel;
import ict4mpower.childHealth.panels.StatusMissedPanel;
import ict4mpower.childHealth.panels.StatusPanel;
import ict4mpower.childHealth.panels.StatusTakenPanel;

import java.util.List;
import java.util.MissingResourceException;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.odlabs.wiquery.core.commons.WiQueryResourceManager;
import org.odlabs.wiquery.core.effects.EffectBehavior;
import org.odlabs.wiquery.ui.effects.EffectsHelper;
import org.odlabs.wiquery.ui.effects.HighlightEffect;

public class MedicationsPanel extends Panel {
	private static final long serialVersionUID = 3885944574671683382L;
	
	private EffectBehavior highlightEffect = new EffectBehavior(
			new HighlightEffect(HighlightEffect.Mode.show, "'#FDCD35'", 1800)) {
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

	public MedicationsPanel(String id, final IModel<Medicine> medicine, final ListView<Medicine> list,
			final Form<?> form, final GiveMedicationPanel medPanel) {
		super(id);
		
		// Add supplement values
		Label ageLabel = new Label("age", new PropertyModel<String>(medicine, "dueAge"));
		add(ageLabel);
		Label drugLabel = new Label("name", new PropertyModel<String>(medicine, "name"));
		add(drugLabel);
		Label doseLabel = new Label("dose", new PropertyModel<String>(medicine, "dosage"));
		add(doseLabel);
		StatusPanel statusPanel = null;
		String status = medicine.getObject().getStatus();
		if(status.contentEquals("missed")) {
			statusPanel = new StatusMissedPanel("status");
		}
		else if(status.contentEquals("taken")) {
			statusPanel = new StatusTakenPanel("status");
		}
		else {
			statusPanel = new StatusDuePanel("status", medicine.getObject().getDueDate());
		}
		add(statusPanel);
		
		Label label = new Label("dateGiven", new PropertyModel<String>(medicine, "givenDate"));
		label.setOutputMarkupPlaceholderTag(true);
		AjaxLink<String> button = new AjaxLink<String>("giveToday") {
			private static final long serialVersionUID = -3622074638128564454L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				List<Medicine> l = list.getModelObject();
				for(Medicine m : l) {
					if(m.equals(medicine.getObject())) {
						medPanel.setVisible(true);
						Object[] ageDef = null;
						List<? extends Object[]> ages = medPanel.getAgeChoice().getChoices();
						Object[] ageArray;
						for(Object[] choice : ages) {
							ageArray = medicine.getObject().getAccurateAgeArray();
							if(Math.abs((choice[1] == null ? 0 : (Float)choice[1]) - (ageArray[1] == null ? 0 : (float)(0.5f*Math.floor((Float)ageArray[1]/0.5f)))) < 0.01f && choice[0].equals(ageArray[0])) {
								ageDef = choice;
								break;
							}
						}
						String medDef = null;
						List<? extends String> medi = medPanel.getMedicineChoice().getChoices();
						for(String srm : medi) {
							// Get medicine name
							String medicineName = null;
							try {
								medicineName = medPanel.getString(srm);
							} catch(MissingResourceException e) {
								// Use the String as it is - custom entered name
								medicineName = srm;
							}
							if(medicineName.equalsIgnoreCase(medicine.getObject().getName())) {
								medDef = srm;
								break;
							}
						}
						String dosageDef = null;
						List<? extends String> dosi = medPanel.getDosageChoice().getChoices();
						for(String srm : dosi) {
							// Get dosage
							String dosageName = null;
							try {
								dosageName = medPanel.getString(srm);
							} catch(MissingResourceException e) {
								// Use the String as it is - custom entered name
								dosageName = srm;
							}
							if(dosageName.equalsIgnoreCase(medicine.getObject().getDosage())) {
								dosageDef = srm;
								break;
							}
						}
						medPanel.getAgeChoice().setDefaultModelObject(ageDef);
						medPanel.getMedicineChoice().setDefaultModelObject(medDef);
						medPanel.getDosageChoice().setDefaultModelObject(dosageDef);
						medPanel.getSerialNr().setDefaultModelObject(null);
						break;
					}
				}
				target.add(form);
				
				// Add highlight effect
				medPanel.getFieldset().add(highlightEffect);
				target.add(medPanel.getFieldset());
			}
		};
		button.setOutputMarkupPlaceholderTag(true);
		
		if(medicine.getObject().getGivenDate() != null) {
			label.setVisible(true);
			button.setVisible(false);
		}
		else {
			label.setVisible(false);
			button.setVisible(true);
		}
		add(label);
		add(button);
	}
}
