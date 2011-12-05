package ict4mpower;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;

import org.apache.wicket.util.tester.WicketTester;

import storage.MedicalRecordSocket;

import models.PatientInfo;
import models.PatientInfo.Sex;

import ict4mpower.childHealth.data.GrowthData;
import ict4mpower.childHealth.data.MedicationsData;
import ict4mpower.childHealth.panels.growth.Indicator;
import ict4mpower.childHealth.panels.medications.Medicine;

/**
 * Class to set up some test values for the Child Health application in the database
 * @author Joakim Lindskog
 *
 */
public class TestSetup extends TestCase{
	private static DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	
	private WicketTester tester;
	//EnhancedWicketTester enhanced = new EnhancedWicketTester(tester);

	@Override
	public void setUp()
	{
		tester = new WicketTester(new WicketApplication());
	}
	
	public static void main(String[] args) {
		// Create mock patient
		MockPatient p = new MockPatient();
		
		// Add some indicators to growth indicators table
		GrowthData data = GrowthData.instance();
		PatientInfo pi;
		MedicalRecordSocket mrSocket = new MedicalRecordSocket();
		try {
			pi = new PatientInfo("Alvin Mattsson Kjellqvist", "1", "Allergic to peanuts",
					df.parse("01/06/2011"), Sex.MALE);
			List<Indicator> indicators = new ArrayList<Indicator>();
			indicators.add(new Indicator(pi, 37.2f, 54.5f, 4.5f, df.parse("05/07/2011")));
			indicators.add(new Indicator(pi, 39, 57.5f, 5.5f, df.parse("01/08/2011")));
			indicators.add(new Indicator(pi, 40, 60, 5.8f, df.parse("28/08/2011")));
			indicators.add(new Indicator(pi, 42, 64, 7f, df.parse("12/10/2011")));
			data.setIndicators(indicators);
			data.setDate(df.parse("01/01/1900"));
			
			mrSocket.SignEntry(data, "1", p.visits.get(0), "ChildHealth");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		// Add some other medications to the medications task
		MedicationsData medData = MedicationsData.instance();
		List<Medicine> meds = null;
		try {
			meds = new ArrayList<Medicine>(Arrays.asList(new Medicine[]{
					new Medicine("Other medicine 1", "Pills", "100 000 IU", "Had some problem", "Take 1 each day for 30 days", df.parse("01/08/2011")),
					new Medicine("Cough syrup", "Syrup", "100 000 IU", "Had a cough", "One tablespoon 3 times a day", df.parse("01/09/2011"))
			}));
			medData.setOtherMeds(meds);
			medData.setDate(df.parse("01/01/1900"));
			mrSocket.SignEntry(medData, "1", p.visits.get(0), "ChildHealth");
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void testSomething(){
	}
}
