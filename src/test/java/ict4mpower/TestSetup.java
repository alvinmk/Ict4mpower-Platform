package ict4mpower;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import storage.DataEndPoint;

import models.PatientInfo;
import models.PatientInfo.Sex;

import ict4mpower.childHealth.data.GrowthData;
import ict4mpower.childHealth.panels.growth.Indicator;

/**
 * Class to set up some test values for the Child Health application in the database
 * @author Joakim Lindskog
 *
 */
public class TestSetup {
	private static DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	
	public static void main(String[] args) {
		// Add some indicators to growth indicators table
		GrowthData data = GrowthData.instance();
		PatientInfo pi;
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
			DataEndPoint.getDataEndPoint().signEntry(data, "1", 1L, "ChildHealth");
			DataEndPoint.getDataEndPoint().save();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
