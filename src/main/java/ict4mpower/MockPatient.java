package ict4mpower;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import models.PatientInfo;
import models.PatientInfo.Sex;

public class MockPatient {
	private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	
	public PatientInfo pi;
	public ArrayList<Long> visits;
	
	public MockPatient() {
		try {
			pi = new PatientInfo("Alvin Mattsson Kjellqvist", "1", "Allergic to peanuts",
					df.parse("2011-06-01"), Sex.MALE);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		visits = new ArrayList<Long>();
		
		try {
			visits.add(df.parse("2010-03-01").getTime());
			visits.add(df.parse("2009-08-23").getTime());
			visits.add(df.parse("2010-07-05").getTime());
			visits.add(df.parse("2008-11-16").getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}	

}
