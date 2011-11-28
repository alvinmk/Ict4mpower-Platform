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
package storage;

import java.io.Serializable;
import java.util.Set;

//import storage.dight.MeasurementRecord;


public class MeasurementRecordSocket {

	public Set<Object> getMesurmentByType(String measurement, String patienId){
		//MeasurementRecord m = new MeasurementRecord();
		//return m.getMeasurement(patienId, measurement);
		return null;
	}
	
	public String SignEntry(String measurement, String unit, String value, String patientId){
		//MeasurementRecord m = new MeasurementRecord();
		//return m.newEntry(measurement, unit, value, patientId);
		return null;
	}
}
