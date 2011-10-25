package storage;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

//import storage.dight.MedicalRecord;

public class DataEndPoint {
	/*
	 * This takes an entry and stores it in the database.
	 * Since it's singned it can no longer be edited. 
	 */
	public String SignEntry(Object o,String patientId, long visitId, String app){
		String type = o.getClass().getName();
		//MedicalRecord mr = new MedicalRecord();
		//return mr.newEntry(o, type, app, patientId, visitId);
		return null;
	}
	
	/*
	 * Returns all signed entries for a specific patient id
	 */
	public Set<Object> getEntriesFromPatientId(String id){
		//Make a dight query
		//MedicalRecord mr = new MedicalRecord();
		//return mr.getObjectsFromPatientId(id);
		return null;
	}
	
	/*
	 * Returns all signed entries for a specific visit id
	 */
	public Set<Object> getEntriesFromVisitId(long id){
		//MedicalRecord mr = new MedicalRecord();
		//return mr.getObjectsFromVisitId(id);
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
