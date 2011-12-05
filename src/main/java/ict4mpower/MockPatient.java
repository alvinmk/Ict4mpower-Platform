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
