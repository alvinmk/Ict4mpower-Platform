package storage.dight;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import models.BaseModel;
import se.sics.dight.data.model.EntryId;
import se.sics.dight.data.model.Objekt;
import se.sics.dight.data.model.templates.ValueTemplate;
import se.sics.dight.data.model.values.Value;
public class MedicalRecord extends BaseRecord{
	private static MedicalRecordKlass klassContainer;

	public MedicalRecord(){
		klassContainer = new MedicalRecordKlass(e);
		setKlassContainer(klassContainer);
	}
	
	/*
	 * Creates a new entry and commits it to the DIGHT database. Returns the entry id.
	 */
	public String newEntry(BaseModel data, String app,String patientId, long visitId) {
		String type = data.getClass().getSimpleName();		
		data.setApplication(app);
		data.setType(type);
		data.setVisit(visitId);
		Value vType = e.makeStringValue(type);
		Value vApp = e.makeStringValue(app);
		Value vPatientId = e.makeStringValue(patientId);
		Value vVisitId = e.makeBigintValue(visitId);
		Value vDate = e.makeDateValue(new Date());
		//Make an Entry id
		EntryId eid = klassContainer.generateUniqeEntryId();
		
		byte[] payload = null;
		try {
			payload = serialize(data);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
				
		Objekt entry = e.createObjekt(klassContainer.getKlass(), eid, payload );
		entry.setValue(klassContainer.date, vDate);
		entry.setValue(klassContainer.application, vApp);
		entry.setValue(klassContainer.patientId, vPatientId);
		entry.setValue(klassContainer.type, vType);
		entry.setValue(klassContainer.visitId, vVisitId);
		
		entry.commit(klassContainer.getEntryAuthenticationAlgorithm());
		try {
			DightSocket.CreateOperationResult(entry, cred, e);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return eid.getId().toString();
	}
	
	
	/*
	 * Returns all objects of a specific type for an application
	 */
	public Set<Object> getObjectsForVisit(long visitId, String patientId, String type, String application){
		HashMap<String, Object> h = new HashMap<String, Object>();
		Set<HashMap<String, Object>> s = new HashSet<HashMap<String, Object>>();
		ValueTemplate Vvisit = e.makeBigintValueTemplate(visitId);
		ValueTemplate VpatientId = e.makeStringValueTemplate(patientId);
		ValueTemplate Vapplication = e.makeStringValueTemplate(application);
		ValueTemplate Vtype = e.makeStringValueTemplate(type);
		h.put("attribute", klassContainer.visitId);
		h.put("value", Vvisit);
		s.add(h);
		h = new HashMap<String, Object>();
		h.put("attribute", klassContainer.patientId);
		h.put("value", VpatientId);
		s.add(h);
		
		h = new HashMap<String, Object>();
		h.put("attribute", klassContainer.type);
		h.put("value", Vtype);
		s.add(h);
		
		h = new HashMap<String, Object>();
		h.put("attribute", klassContainer.application);
		h.put("value", Vapplication);
		s.add(h);
		
		return attributesQueryToObject(s);
	}
	
	/*
	 * Get all objects belonging to a patient of a type in one applicaiton
	 */
	public Set<Object> getObjectsFromPatientId(String patientId, String type, String application){
		HashMap<String, Object> h = new HashMap<String, Object>();
		Set<HashMap<String, Object>> s = new HashSet<HashMap<String, Object>>();
		ValueTemplate VPatientId = e.makeStringValueTemplate(patientId);
		ValueTemplate Vapplication = e.makeStringValueTemplate(application);
		ValueTemplate Vtype = e.makeStringValueTemplate(type);
		
		h.put("attribute", klassContainer.patientId);
		h.put("value", VPatientId);
		s.add(h);		
		
		h = new HashMap<String, Object>();
		h.put("attribute", klassContainer.type);
		h.put("value", Vtype);
		s.add(h);
		
		h = new HashMap<String, Object>();
		h.put("attribute", klassContainer.application);
		h.put("value", Vapplication);
		s.add(h);
		
		return attributesQueryToObject(s);
	}
	
	/*
	 * Get all objects for a patient betweens specific dates
	 */
	public Set<Object> getObjectsFromDateRange(String patientId, Date low, Date high, String type, String application){
		HashMap<String, Object> h = new HashMap<String, Object>();
		Set<HashMap<String, Object>> s = new HashSet<HashMap<String, Object>>();
		
		ValueTemplate vDateRange = e.makeDateValueRangeTemplate(low, high);
		ValueTemplate Vapplication = e.makeStringValueTemplate(application);
		ValueTemplate Vtype = e.makeStringValueTemplate(type);
		
		h.put("attribute", klassContainer.date);
		h.put("value", vDateRange);
		Value visitVal = e.makeStringValue(patientId);
		
		h = new HashMap<String, Object>();
		h.put("attribute", klassContainer.visitId);
		h.put("value", visitVal);
		s.add(h);
		
		h = new HashMap<String, Object>();
		h.put("attribute", klassContainer.type);
		h.put("value", Vtype);
		s.add(h);
		
		h = new HashMap<String, Object>();
		h.put("attribute", klassContainer.application);
		h.put("value", Vapplication);
		s.add(h);
		
		return attributesQueryToObject(s);
	}
}
