package Mocks;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import models.PatientInfo;


public class MockPatient {
	private static SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	
	public PatientInfo pi;
	public List<String> visits;
	
	public MockPatient() {
		try {
			pi = new PatientInfo("Alvin Mattsson Kjellqvist", "1", "Allergic to peanuts", df.parse("01/06/2011"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		visits.add("2010-03-01");
		visits.add("2009-08-23");
		visits.add("2010-07-05");
		visits.add("2008-11-16");		
	}	

}
