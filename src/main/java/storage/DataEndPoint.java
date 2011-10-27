package storage;


import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class DataEndPoint {
	Object o;
	/*
	 * This takes an entry and stores it in the database.
	 * Since it's singned it can no longer be edited. 
	 *
	 */
	
	//A temporary in memory object storage {patientId}->{visit_app}->{type}->Set<Objects>
	private HashMap<String, HashMap<String, HashMap<String, Set<Object>>>> temp = new HashMap<String, HashMap<String, HashMap<String, Set<Object>>>>();
	private static DataEndPoint d;
	
	public static DataEndPoint getDataEndPoint(){
		if(d!=null){
			return d;
		}
		else{
			d = new DataEndPoint();
			return d;
		}
	}
	
	private DataEndPoint(){
		
	}
	
	public String SignEntry(Object o,String patientId, long visitId, String app){
		String type = o.getClass().getName();

		String visit = Long.toString(visitId);
		if(temp.containsKey(patientId)){
			if(temp.get(patientId).containsKey(visit+"_"+app)){
				if(temp.get(patientId).get(visitId).containsKey(type)){
					temp.get(patientId).get(visitId).get(type).add(o);
				}
				else{
					Set<Object> s = new HashSet<Object>();
					s.add(o);
					temp.get(patientId).get(visitId).put(type, s);
				}
			}
			else{
				HashMap<String, Set<Object>> h = new HashMap<String, Set<Object>>();
				Set<Object> s = new HashSet<Object>();
				s.add(o);
				h.put(type, s);				
				temp.get(patientId).put(visit+"_"+app, h);
			}
		}
		else{
			HashMap<String, Set<Object>> inner = new HashMap<String, Set<Object>>();
			HashMap<String, HashMap<String, Set<Object>>> outer = new HashMap<String, HashMap<String, Set<Object>>>();
			Set<Object> s = new HashSet<Object>();
			s.add(o);
			inner.put(type, s);
			outer.put(visit+"_"+app,inner);
			temp.put(patientId, outer);
		}
		//Write to dight
		//MedicalRecord mr = new MedicalRecord(o, type, app, patientId, visitId);
		
		return "";
	}
	
	/*
	 * Returns all signed entries for a specific patient id
	 */

	public Set<Object> getEntriesFromPatientId(String id){
		//Make a dight query
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
	public Set<Object> getEntriesFromVisitIdAndType(String patientId, long Visitid, String type){
		String visit = Long.toString(Visitid);
		Set<Object> s = temp.get(patientId).get(visit).get(type);
		//MedicalRecord mr = new MedicalRecord();
		//return mr.getObjectsFromTypeAndVisitId(type, id);
		return s;
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
