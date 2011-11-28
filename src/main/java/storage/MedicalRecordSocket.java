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
import java.util.Date;
import java.util.Set;

//import storage.dight.MedicalRecord;

//import storage.dight.MedicalRecord;

public class MedicalRecordSocket {
	/*
	 * This takes an entry and stores it in the database.
	 * Since it's singned it can no longer be edited. 
	 */
	public String SignEntry(Object o,String patientId, long visitId, String app){
		String type = o.getClass().getName();
	//	MedicalRecord mr = new MedicalRecord();
	//	return mr.newEntry(o, type, app, patientId, visitId);
		return null;
	}
	
	/*
	 * Returns all signed entries for a specific patient id
	 */
	public Set<Object> getEntriesFromPatientId(String id){
		//Make a dight query
	//	MedicalRecord mr = new MedicalRecord();
	//	return mr.getObjectsFromPatientId(id);
		return null;
	}
	
	/*
	 * Returns all signed entries for a specific visit id
	 */
	public Set<Object> getVisitEntry(long visitId, String patientId){
		//MedicalRecord mr = new MedicalRecord();
		//return mr.getObjectsFromVisitId(visitId, patientId);
		return null;
	}
	
	/*
	 * Returns all signed entries for a type and specific visit 
	 */
	public Set<Object> getEntriesFromVisitIdAndType(String type, long id){
		//MedicalRecord mr = new MedicalRecord();
		//return mr.getObjectsFromTypeAndVisitId(type, id);
		return null;
	}
	/*
	 * Returns signed entries within a date range for a specific patient
	 */	
	public Set<Object> getEntriesFromDateRange(String patientId, Date low, Date high){
		//MedicalRecord mr = new MedicalRecord();
		//return mr.getObjectsFromDateRange(patientId, low, high);
		return null;
	}

}
