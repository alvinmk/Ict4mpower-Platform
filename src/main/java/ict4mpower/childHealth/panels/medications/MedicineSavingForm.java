package ict4mpower.childHealth.panels.medications;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.MissingResourceException;

import org.apache.wicket.markup.html.list.ListView;

import ict4mpower.AppSession;
import ict4mpower.childHealth.SavingForm;
import ict4mpower.childHealth.data.MedicationsData;

public class MedicineSavingForm extends SavingForm {
	private static final long serialVersionUID = -73635838347441944L;

	protected ListView<Medicine> list;
	protected GiveMedicationPanel medPanel;

	public MedicineSavingForm(String id) {
		super(id);
	}
	
	@Override
	protected void onSubmit() {
		super.onSubmit();
		
		// See if the submitted info matches an item in the list
		List<Medicine> l = list.getModelObject();

		// Get medicine name
		String medicineName = null;
		try {
			medicineName = getString(medPanel.getMedicineChoice().getConvertedInput());
		} catch(MissingResourceException e) {
			// Use the String as it is - custom entered name
			medicineName = medPanel.getMedicineChoice().getConvertedInput();
		}
		
		boolean found = false;
		for(Medicine m : l) {
			if(m.getDueAge(this).equals(medPanel.getAgeChoiceValue())
					&& m.getName().equals(medicineName)) {
				m.setGivenDate(new Date());
				m.setDosage(medPanel.getDosageChoice().getConvertedInput());
				m.setBatchNr(medPanel.getSerialNr().getConvertedInput());
				found = true;
			}
		}
		
		if(!found) {
			// Get due date from given due age
			int calField = -1;
			int calDiff = -1;
			String ageValue = medPanel.getAgeChoiceValue();
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
				NumberFormat nf = new DecimalFormat("0.#");
				double age = 0;
				Number nbr = 0;
				try {
					nbr = nf.parse(ageValue.substring(0, ageValue.indexOf("years")).trim());
					if(nbr instanceof Double) {
						age = (Double) nbr;
					}
					else {
						age = (Long) nbr;
					}
				} catch (ParseException e) {
					// Hopefully it won't get here, as it should be either a double (e.g. 3,5) or a long (e.g. 3)
				}
				if(Math.abs(Math.round(age) - age) < 0.01f) {
					calDiff = (int)age;
				}
				else {
					// Uneven years, convert to months
					calField = Calendar.MONTH;
					calDiff = (int) ((Math.floor(age) + (age%1f))*12);
				}
			}
			l.add(new Medicine(((AppSession)getSession()).getPatientInfo(),
					medicineName,
					calField,
					calDiff,
					new Date(),
					medPanel.getDosageChoice().getConvertedInput(),
					medPanel.getSerialNr().getConvertedInput()));
			Collections.sort(l);
		}
		
		MedicationsData.instance().setDate(new Date());
		
		medPanel.setVisible(false);
	}
}
