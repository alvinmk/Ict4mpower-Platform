package storage.dight;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import se.sics.dight.data.model.EntryId;
import se.sics.dight.data.model.Klass;
import se.sics.dight.data.model.Objekt;
import se.sics.dight.data.model.attributes.Attribute;
import se.sics.dight.data.model.templates.ValueTemplate;
import se.sics.dight.data.model.values.Value;
import se.sics.dight.data.security.Credential;
import se.sics.dight.storage.store.query.QueryResult;
public class MedicalRecord extends BaseRecord{
	private static MedicalRecordKlass klassContainer;

	public MedicalRecord(){
		klassContainer = new MedicalRecordKlass(e);
		setKlassContainer(klassContainer);
	}
	
	/*
	 * Creates a new entry and commits it to the DIGHT database. Returns the entry id.
	 */
	public String newEntry(Object data, String type, String app,String patientId, long visitId) {
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
		List<Credential> creds = null;
		try {
			DightSocket.CreateOperationResult(entry, creds, e);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return eid.getId().toString();
	}
	/* 
	 * Makes a query to DIGHT using visit and type and returns a set of matching objects
	 */
	public Set<Object> getObjectsFromTypeAndVisitId(String type, long visitId){
		HashMap<String, Object> h = new HashMap<String, Object>();
		Set<HashMap> s = new HashSet<HashMap>();
		Value typeVal = e.makeStringValue(type);
		h.put("attribute", klassContainer.type);
		h.put("value", typeVal);
		
		Value patientIdVal = e.makeBigintValue(visitId);
		h = new HashMap<String, Object>();
		h.put("attribute", klassContainer.visitId);
		h.put("value", visitId);
		s.add(h);		
		return attributesQueryToObject(s);
	}
	
	/*
	 * Returns all objects in a visit belonging to one patient
	 */
	public Set<Object> getObjectsFromVisitId(long visitId, String patientId){
		HashMap<String, Object> h = new HashMap<String, Object>();
		Set<HashMap> s = new HashSet<HashMap>();
		ValueTemplate Vvisit = e.makeBigintValueTemplate(visitId);
		ValueTemplate VpatientId = e.makeStringValueTemplate(patientId);
		h.put("attribute", klassContainer.visitId);
		h.put("value", Vvisit);
		s.add(h);
		h = new HashMap<String, Object>();
		h.put("attribute", klassContainer.patientId);
		h.put("value", VpatientId);
		s.add(h);
		return attributesQueryToObject(s);
	}
	
	/*
	 * Get all objects belonging to a specific patient
	 */
	public Set<Object> getObjectsFromPatientId(String patientId){
		HashMap<String, Object> h = new HashMap<String, Object>();
		Set<HashMap> s = new HashSet<HashMap>();
		ValueTemplate VPatientId = e.makeStringValueTemplate(patientId);
		h.put("attribute", klassContainer.patientId);
		h.put("value", VPatientId);
		s.add(h);		
		return attributesQueryToObject(s);
	}
	
	/*
	 * Get all objects for a patient betweens specific dates
	 */
	public Set<Object> getObjectsFromDateRange(String patientId, Date low, Date high){
		HashMap<String, Object> h = new HashMap<String, Object>();
		Set<HashMap> s = new HashSet<HashMap>();
		ValueTemplate vDateRange = e.makeDateValueRangeTemplate(low, high);
		h.put("attribute", klassContainer.date);
		h.put("value", vDateRange);
		Value visitVal = e.makeStringValue(patientId);
		h = new HashMap<String, Object>();
		h.put("attribute", klassContainer.visitId);
		h.put("value", visitVal);
		s.add(h);
		return attributesQueryToObject(s);
	}
}
