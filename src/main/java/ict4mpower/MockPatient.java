package ict4mpower;

import java.util.ArrayList;
import java.util.List;

import Models.PatientInfo;

public class MockPatient {
	
	public PatientInfo pi;
	public ArrayList<String> visits;
	
	public MockPatient() {
		pi = new PatientInfo("Alvin Mattsson Kjellqvist", "1", "Allergic to peanuts");
		visits = new ArrayList<String>();
		
		visits.add("2010-03-01");
		visits.add("2009-08-23");
		visits.add("2010-07-05");
		visits.add("2008-11-16");
		
	}	

}
