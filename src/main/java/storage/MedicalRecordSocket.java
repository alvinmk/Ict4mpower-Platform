package storage;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import storage.dight.MedicalRecord;

//import storage.dight.MedicalRecord;

public class MedicalRecordSocket {
	/*
	 * This takes an entry and stores it in the database.
	 * Since it's singned it can no longer be edited. 
	 */
	public String SignEntry(Object o,String patientId, long visitId, String application){
		String type = o.getClass().getName();
		MedicalRecord mr = new MedicalRecord();
		return mr.newEntry(o, application, patientId, visitId);
	}
	
	/*
	 * Returns all signed entries for a specific patient id
	 */
	public Set<Object> getEntriesForPatientId(String patientId, String type, String application){
		//Make a dight query
		MedicalRecord mr = new MedicalRecord();
		return mr.getObjectsFromPatientId(patientId, type, application);
	}
	
	/*
	 * Returns all signed entries for a specific visit id
	 */
	public Set<Object> getVisitEntries(long visitId, String patientId, String type,  String application){
		MedicalRecord mr = new MedicalRecord();
		return mr.getObjectsForVisit(visitId, patientId, type, application);
	}
	
	
	/*
	 * Returns signed entries within a date range for a specific patient
	 */	
	public Set<Object> getEntriesFromDateRange(String patientId, Date low, Date high, String type, String application){
		MedicalRecord mr = new MedicalRecord();
		return mr.getObjectsFromDateRange(patientId, low, high, type, application);
	}

}
