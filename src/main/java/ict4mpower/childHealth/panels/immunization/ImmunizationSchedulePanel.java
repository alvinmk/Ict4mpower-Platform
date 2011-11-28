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
package ict4mpower.childHealth.panels.immunization;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.MissingResourceException;
import java.util.Set;

import models.PatientInfo;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.odlabs.wiquery.core.commons.WiQueryResourceManager;
import org.odlabs.wiquery.core.effects.EffectBehavior;
import org.odlabs.wiquery.ui.effects.EffectsHelper;
import org.odlabs.wiquery.ui.effects.HighlightEffect;

import storage.ApplicationSocketTemp;
import storage.DataEndPoint;

import ict4mpower.AppSession;
import ict4mpower.childHealth.SavingForm;
import ict4mpower.childHealth.data.ImmunizationData;
import ict4mpower.childHealth.panels.AgeModel;
import ict4mpower.childHealth.panels.DivisionPanel;
import ict4mpower.childHealth.panels.StatusDuePanel;
import ict4mpower.childHealth.panels.StatusMissedPanel;
import ict4mpower.childHealth.panels.StatusPanel;
import ict4mpower.childHealth.panels.StatusTakenPanel;

/**
 * Panel for the immunization schedule
 * @author Joakim Lindskog
 *
 */
public class ImmunizationSchedulePanel extends DivisionPanel {
	private static final long serialVersionUID = -1916771602507841446L;
	
	private ListView<Vaccination> list;
	private GiveVaccinationPanel vaccPanel;

	/**
	 * Constructor
	 * @param id component id
	 */
	public ImmunizationSchedulePanel(String id) {
		super(id, "title", false);
		
		setOutputMarkupId(true);
		
		add(new ImmunizationForm("form"));
	}
	
	/**
	 * Form for the immunization schedule panel
	 * @author Joakim Lindskog
	 *
	 */
	private class ImmunizationForm extends SavingForm {
		private static final long serialVersionUID = 8603618882539149364L;

		/**
		 * Constructor
		 * @param id component id
		 */
		@SuppressWarnings("unchecked")
		public ImmunizationForm(String id) {
			super(id);
			
			ImmunizationData data = ImmunizationData.instance();
			if(data.getVaccinations() == null) {
				// No data in session - get from db for patient
				Date max = null;
				try {
					max = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/1800");
				} catch (ParseException e) {
					e.printStackTrace();
				}
				ImmunizationData imd = null;
				Set<Serializable> set = DataEndPoint.getDataEndPoint().getEntriesFromPatientId(((AppSession)getSession()).getPatientInfo().getClientId());
				for(Object o : set) {
					if(o instanceof ImmunizationData) {
						imd = (ImmunizationData) o;
						if(imd.getVaccinations() != null && imd.getDate().after(max)) {
							data.setVaccinations(imd.getVaccinations());
							max = imd.getDate();
						}
					}
				}
			}
			if(data.getVaccinations() == null) {
				// No data in db for patient - get "starting vaccinations list" for patient
				Set<Object> v = ApplicationSocketTemp.getApplicationSocketTemp().getData("ChildHealth", "StandardVaccinations");
				List<Vaccination> va = null;
				List<Vaccination> vaccinations = new ArrayList<Vaccination>();
				PatientInfo pi = ((AppSession)getSession()).getPatientInfo();
				for(Object o : v) {
					// Only get first object in set - there should be only one
					va = (List<Vaccination>) o;
					break;
				}
				for(Vaccination vac : va) {
					vaccinations.add(new Vaccination(pi, vac.getVaccine(), vac.getCalField(), vac.getCalAdd(), null,
							vac.getDosage(), vac.getSerial_nr()));
				}
				data.setVaccinations(vaccinations);
			}
			
			// Add table items
			list = new ListView<Vaccination>("vaccinations", new PropertyModel<List<Vaccination>>(data, "vaccinations")) {
				private static final long serialVersionUID = 1L;

				@Override
				protected void populateItem(ListItem<Vaccination> item) {
					item.add(new VaccinationPanel("rowPanel", item.getModel(), list, ImmunizationForm.this, vaccPanel));
				}
			};
			list.setOutputMarkupId(true);
			add(list);
			
			// 'Give vaccination' panel
			vaccPanel = new GiveVaccinationPanel("giveVaccinePanel", this, ImmunizationSchedulePanel.this);
			
			// Add 'give vaccination' button
			add(new AjaxLink<Object>("giveVaccine") {
				private static final long serialVersionUID = 1L;

				@Override
				public void onClick(AjaxRequestTarget target) {
					vaccPanel.getAgeChoice().setDefaultModelObject(null);
					vaccPanel.getVaccineChoice().setDefaultModelObject(null);
					vaccPanel.getDosageChoice().setDefaultModelObject(null);
					vaccPanel.getSerialNr().setDefaultModelObject(null);
					vaccPanel.setVisible(true);
					target.add(vaccPanel);
				}
			}, false);
			
			// Add 'give vaccination' component
			vaccPanel.setOutputMarkupPlaceholderTag(true);
			vaccPanel.setVisible(false);
			add(vaccPanel, false);
		}
		
		@Override
		protected void onSubmit() {
			super.onSubmit();
			
			// See if the submitted info matches an item in the list
			List<Vaccination> l = list.getModelObject();
			if(l == null) {
				list.setModelObject(new ArrayList<Vaccination>());
				l = list.getModelObject();
			}

			// Get vaccine name
			String vaccineName = null;
			try {
				vaccineName = getString(vaccPanel.getVaccineChoice().getConvertedInput());
			} catch(MissingResourceException e) {
				// Use the String as it is - custom entered name
				vaccineName = vaccPanel.getVaccineChoice().getConvertedInput();
			}
			
			boolean found = false;
			for(Vaccination v : l) {
				if(v.getDueAge(this).equals(vaccPanel.getAgeChoiceValue())
						&& v.getVaccine().equals(vaccineName)) {
					v.setGivenDate(new Date());
					v.setDosage(vaccPanel.getDosageChoice().getConvertedInput());
					v.setSerial_nr(vaccPanel.getSerialNr().getConvertedInput());
					found = true;
				}
			}
			
			if(!found) {
				// Get due date from given due age
				int calField = -1;
				int calDiff = -1;
				String ageValue = vaccPanel.getAgeChoiceValue();
				if(ageValue.equals(getString("at_birth"))) {
					calField = Calendar.MONTH;
					calDiff = 0;
				}
				else if(ageValue.contains("weeks")) {
					calField = Calendar.WEEK_OF_YEAR;
					calDiff = Integer.parseInt(ageValue.substring(0, ageValue.indexOf("weeks")).trim());
				}
				else if(ageValue.contains("months")) {
					calField = Calendar.MONTH;
					calDiff = Integer.parseInt(ageValue.substring(0, ageValue.indexOf("months")).trim());
				}
				else if(ageValue.contains("years")) {
					calField = Calendar.YEAR;
					calDiff = Integer.parseInt(ageValue.substring(0, ageValue.indexOf("years")).trim());
				}
				l.add(new Vaccination(((AppSession)getSession()).getPatientInfo(),
						vaccineName,
						calField,
						calDiff,
						new Date(),
						vaccPanel.getDosageChoice().getConvertedInput(),
						vaccPanel.getSerialNr().getConvertedInput()));
				Collections.sort(l);
			}
			
			// Set date
			ImmunizationData.instance().setDate(new Date());
			
			vaccPanel.setVisible(false);
		}
	}
}

/**
 * Row panel for the vaccinations table
 * @author Joakim Lindskog
 *
 */
class VaccinationPanel extends Panel {
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

	/**
	 * Constructor
	 * @param id component id
	 * @param vaccination model of the vaccination
	 * @param list the list view of the vaccinations list
	 * @param form the form to use
	 * @param vaccPanel the give vaccination panel
	 */
	public VaccinationPanel(String id, final IModel<Vaccination> vaccination, final ListView<Vaccination> list,
			final Form<?> form, final GiveVaccinationPanel vaccPanel) {
		super(id);
		
		// Add vaccination values
		Label ageLabel = new Label("age", new AgeModel<Vaccination>(vaccination, this));
		add(ageLabel);
		Label vaccineLabel = new Label("vaccine", new PropertyModel<String>(vaccination, "vaccine"));
		add(vaccineLabel);
		StatusPanel statusPanel = null;
		String status = vaccination.getObject().getStatus();
		if(status.contentEquals("missed")) {
			statusPanel = new StatusMissedPanel("status");
		}
		else if(status.contentEquals("taken")) {
			statusPanel = new StatusTakenPanel("status");
		}
		else {
			statusPanel = new StatusDuePanel("status", vaccination.getObject().getDueDate());
		}
		add(statusPanel);
		
		Label label = new Label("dateGiven", new PropertyModel<String>(vaccination, "givenDate"));
		label.setOutputMarkupPlaceholderTag(true);
		AjaxLink<String> button = new AjaxLink<String>("giveToday") {
			private static final long serialVersionUID = -3622074638128564454L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				List<Vaccination> l = list.getModelObject();
				for(Vaccination v : l) {
					if(v.equals(vaccination.getObject())) {
						vaccPanel.setVisible(true);
						Object[] ageDef = null;
						List<? extends Object[]> ages = vaccPanel.getAgeChoice().getChoices();
						Object[] ageArray;
						for(Object[] choice : ages) {
							ageArray = vaccination.getObject().getAccurateAgeArray();
							if(((choice[1] == null && ageArray[1] == null) || choice[1] == (Integer)(int)Math.floor((Float)ageArray[1])) && choice[0].equals(ageArray[0])) {
								ageDef = choice;
								break;
							}
						}
						String vaccDef = null;
						List<? extends String> vacci = vaccPanel.getVaccineChoice().getChoices();
						for(String srm : vacci) {
							// Get vaccine name
							String vaccineName = null;
							try {
								vaccineName = vaccPanel.getString(srm);
							} catch(MissingResourceException e) {
								// Use the String as it is - custom entered name
								vaccineName = srm;
							}
							if(vaccineName.equalsIgnoreCase(vaccination.getObject().getVaccine())) {
								vaccDef = srm;
								break;
							}
						}
						vaccPanel.getAgeChoice().setDefaultModelObject(ageDef);
						vaccPanel.getVaccineChoice().setDefaultModelObject(vaccDef);
						vaccPanel.getDosageChoice().setDefaultModelObject(null);
						vaccPanel.getSerialNr().setDefaultModelObject(null);
						break;
					}
				}
				target.add(form);
				
				// Add highlight effect
				vaccPanel.getFieldset().add(highlightEffect);
				target.add(vaccPanel.getFieldset());
			}
		};
		button.setOutputMarkupPlaceholderTag(true);
		
		if(vaccination.getObject().getGivenDate() != null) {
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
