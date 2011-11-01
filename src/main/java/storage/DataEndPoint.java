package storage;


import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;
import java.util.HashMap;

//import storage.dight.MedicalRecord;

public class DataEndPoint {
	/*
	 * This takes an entry and stores it in the database.
	 * Since it's singed it can no longer be edited. 
	 *
	 */
	
	//A temporary in memory object storage {patientId}->{visit_app}->{type}->Set<Objects>y
	
	private static HashMap<String, HashMap<String, HashMap<String, Set<Serializable>>>> temp = new HashMap<String, HashMap<String, HashMap<String, Set<Serializable>>>>();
	private static DataEndPoint d;
	private HashMap<String, HashMap<String, String>> test = new HashMap<String, HashMap<String, String>>();
	
	public static DataEndPoint getDataEndPoint(){
		if(d!=null){
			return d;
		}
		else{
			d = new DataEndPoint();
			load();
			return d;
		}
	}
	
	private void printMap(){
		for(String key : temp.keySet()){
			
			for(String Ikey : temp.get(key).keySet()){
				
				for(String IIkey : temp.get(key).get(Ikey).keySet()){
					System.err.print(key);
					System.err.print("->{"+Ikey+"}->");
					System.err.print("{"+IIkey+"}->{\n");
					Set<Serializable> s = temp.get(key).get(Ikey).get(IIkey);
					for(Object o : s){
						System.err.println("           "+o.getClass().getName());
					}
					System.err.println("}");
				}
			}
			
		}
	}
	
	@SuppressWarnings("unchecked")
	private DataEndPoint(){
		ObjectInputStream ois = null;
		try {
			File file = new File("MedicalRecords.dat");
			if(file.exists()) {
			    FileInputStream fin = new FileInputStream(file);
			    ois = new ObjectInputStream(fin);
			    Object o = ois.readObject();
			    if(o instanceof HashMap) {
			    	temp = (HashMap<String, HashMap<String, HashMap<String, Set<Serializable>>>>) o;
			    }
			    else {
			    	System.err.println("Not hashmap "+o);
			    }
			    System.out.println("temp "+temp);
			}
		} catch(EOFException eof) {
			// There's nothing there!
			eof.printStackTrace();
		} catch (Exception e) { e.printStackTrace(); }
		finally {
			if(ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		printMap();
	}
	
	public void save(){
		printMap();
		HashMap<String, String> m1 = new HashMap<String, String>();
		m1.put("test", "test");
		m1.put("jsak", "lkfölka");
		test.put("ahsjk", m1);
		ObjectOutputStream oos = null;
		 try {
		      FileOutputStream fout = new FileOutputStream("MedicalRecords.dat");
		      oos = new ObjectOutputStream(fout);
		      oos.writeObject(temp);
		 }
		 catch (Exception e) { e.printStackTrace(); }
		 finally {
			 if(oos != null) {
				 try {
					oos.flush();
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			 }
		 }
		 
	}
	
	private static void load(){
		
	}
	
	public String signEntry(Serializable o, String patientId, long visitId, String app){
		String type = o.getClass().getName();

		String visit = Long.toString(visitId);
		if(temp.containsKey(patientId)){
			if(temp.get(patientId).containsKey(visit+"_"+app)){
				if(temp.get(patientId).get(visit+"_"+app).containsKey(type)){
					temp.get(patientId).get(visit+"_"+app).get(type).add(o);
					return "new object";
				}
				else{
					Set<Serializable> s = new HashSet<Serializable>();
					s.add(o);
					temp.get(patientId).get(visit+"_"+app).put(type, s);
					return "new type";
				}
			}
			else{
				HashMap<String, Set<Serializable>> h = new HashMap<String, Set<Serializable>>();
				Set<Serializable> s = new HashSet<Serializable>();
				s.add(o);
				h.put(type, s);				
				temp.get(patientId).put(visit+"_"+app, h);
				return "new visit and App";
			}
		}
		else{
			HashMap<String, Set<Serializable>> inner = new HashMap<String, Set<Serializable>>();
			HashMap<String, HashMap<String, Set<Serializable>>> outer = new HashMap<String, HashMap<String, Set<Serializable>>>();
			Set<Serializable> s = new HashSet<Serializable>();
			s.add(o);
			inner.put(type, s);
			outer.put(visit+"_"+app,inner);
			temp.put(patientId, outer);
			return "new patient";
		}
		//Write to dight		
		//MedicalRecord mr = new MedicalRecord();
		//return mr.newEntry(o, type, app, patientId, visitId);
	}
	
	/*
	 * Returns all signed entries for a specific patient id
	 */
	public Set<Serializable> getEntriesFromPatientId(String id){
		Set<Serializable> s = new HashSet<Serializable>();
		if(temp.get(id) != null) {
			for(Entry<String, HashMap<String, Set<Serializable>>> visits : temp.get(id).entrySet()) {
				for(Entry<String, Set<Serializable>> types : visits.getValue().entrySet()) {
					for(Serializable obj : types.getValue()) {
						s.add(obj);
					}
				}
			}
		}
		//Make a dight query
		//MedicalRecord mr = new MedicalRecord();
		//return mr.getObjectsFromPatientId(id);
		return s;
	}
	
	/*
	 * Returns all signed entries for a specific visit id
	 */
	public Set<Serializable> getEntriesFromVisitId(long id){
		//MedicalRecord mr = new MedicalRecord();
		//return mr.getObjectsFromVisitId(id);
		return null;
	}
	
	/*
	 * Returns all signed entries for a type and specific visit 
	 */
	public Set<Serializable> getEntriesFromVisitIdAndType(String patientId, String app, long Visitid, String type){
		String visit = Long.toString(Visitid);
		
		Set<Serializable> s = temp.get(patientId).get(visit+"_"+app).get(type);
		//MedicalRecord mr = new MedicalRecord();
		//return mr.getObjectsFromTypeAndVisitId(type, id);
		return s;
	}
	/*
	 * Returns signed entries within a date range for a specific patient
	 */	
	public Set<Serializable> getEntriesFromDateRange(String patientId, Date low, Date high){
		//MedicalRecord mr = new MedicalRecord();
		//return mr.getObjectsFromDateRange(patientId, low, high);
		return null;
	}

}
