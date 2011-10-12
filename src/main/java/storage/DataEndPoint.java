package storage;

import java.util.HashSet;
import java.util.Set;

import storage.dight.MedicalRecord;

public class DataEndPoint {
	
	/*
	 * This takes an entry and stores it in the database.
	 * Since it's singned it can no longer be edited. 
	 */
	public String SignEntry(Object o,String patientId, long visitId, String app){
		String type = o.getClass().getName();
		//Write to dight
		MedicalRecord mr = new MedicalRecord(o, type, app, patientId, visitId);
		return "Id of Entry";
	}
	
	/*
	 * Returns all signed entries for a specific patient id
	 */
	public Set<Object> getEntryFromPatientId(String id){
		//Make a dight query
		Set<Object> s = new HashSet<Object>();
		return s;
	}
	
	/*
	 * Returns all signed entries for a specific visit id
	 */
	public Set<Object> getEntriesFromVisitId(long id){
		//Make a dight query
		Set<Object> s = new HashSet<Object>();
		return s;
	}

}
